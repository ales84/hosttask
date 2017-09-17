package org.alesapps.hosttask.db;

import org.slf4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by Anatoliy Kozhayev on 07.09.2017.
 */
public class DBUtils {

    public static final String COLUMN_NAME_QUOTE = "\"";

    private static final Logger LOG = getLogger(DBUtils.class);

    private static final String DATABASE_DRIVER = "org.postgresql.Driver";
    private static final String DATABASE_URL = "jdbc:postgresql://%s:%s/%s";

    public static Connection getDatabaseConnection() {
        Properties properties = new Properties();
        String databaseHost;
        String databasePort;
        String databaseName;
        String databaseUserName;
        String databasePassword;
        try (InputStream inputStream = new FileInputStream(System.getenv("SQLWEBTASK_ROOT") + "/db.properties")) {
            properties.load(inputStream);
            databaseHost = properties.getProperty("database.host");
            databasePort = properties.getProperty("database.port");
            databaseName = properties.getProperty("database.name");
            databaseUserName = properties.getProperty("database.username");
            databasePassword = properties.getProperty("database.password");
        } catch (IOException e) {
            LOG.debug("Unable to retrieve database properties: {}", e.getMessage());
            return null;
        }
        try {
            Class.forName(DATABASE_DRIVER);
        } catch (ClassNotFoundException e) {
            LOG.debug("Unable to get database driver: {}" + e.getMessage());
            return null;
        }
        Connection connection;
        try {
            connection = DriverManager.getConnection(
                    String.format(DATABASE_URL, databaseHost, databasePort, databaseName),
                    databaseUserName, databasePassword);
        } catch (SQLException e) {
            LOG.debug("Unable to connect to database: {}" + e.getMessage());
            return null;
        }
        return connection;
    }

    public static DBTable getDatabaseTable(String schemaName, String tableName) {
        if ((schemaName == null) || tableName == null) {
            LOG.debug("Empty data");
            return null;
        }
        DBTable dbTable = new DBTable(schemaName, tableName, null, null);
        try (Connection connection = getDatabaseConnection()) {
            if (connection != null) {
                DatabaseMetaData databaseMetaData = connection.getMetaData();
                ResultSet rs = databaseMetaData.getTables(null, schemaName, tableName, null);
                if (!rs.next()) {
                    LOG.debug("Unable to find schema={}, table={}", schemaName, tableName);
                    rs.close();
                } else {
                    do {
                        if ("TABLE".equalsIgnoreCase(rs.getString("TABLE_TYPE"))
                                && tableName.equalsIgnoreCase(rs.getString("TABLE_NAME"))) {

                            List<DBColumn> columns = new ArrayList<>();
                            ResultSet tableMetaData = databaseMetaData.getColumns(null, schemaName, tableName, null);
                            while (tableMetaData.next()) {
                                String columnName = tableMetaData.getString("COLUMN_NAME");
                                String columnType = tableMetaData.getString("TYPE_NAME");
                                int columnSize = tableMetaData.getInt("COLUMN_SIZE");
                                boolean columnNotNullable = "NO".equalsIgnoreCase(tableMetaData.getString("IS_NULLABLE"));
                                columns.add(new DBColumn(columnName, columnType, columnSize, columnNotNullable));
                            }
                            tableMetaData.close();
                            dbTable.setColumns(columns);

                            List<String> primaryKeyColumns = new ArrayList<>();
                            ResultSet primaryKeys = databaseMetaData.getPrimaryKeys(null, schemaName, tableName);
                            String primaryKeyName = null;
                            while (primaryKeys.next()) {
                                String keyName = primaryKeys.getString("PK_NAME");
                                String keyColumnName = primaryKeys.getString("COLUMN_NAME");
                                primaryKeyName = keyName;
                                primaryKeyColumns.add(keyColumnName);
                            }
                            dbTable.setPrimaryKey(new DBPrimaryKey(primaryKeyName, primaryKeyColumns));
                        }
                    }
                    while (rs.next());
                    rs.close();
                }
            }
        } catch (SQLException e) {
            LOG.debug("Unable to retrieve data from database: {}", e.getMessage());
        }
        return dbTable;
    }

}
