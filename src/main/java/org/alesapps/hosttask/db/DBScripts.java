package org.alesapps.hosttask.db;

import org.slf4j.Logger;

import static org.alesapps.hosttask.db.DBUtils.COLUMN_NAME_QUOTE;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by Anatoliy Kozhayev on 07.09.2017.
 */
public class DBScripts {

    private static final Logger LOG = getLogger(DBScripts.class);

    public static String getCreate(String schemaName, String tableName) {
        DBTable dbTable = DBUtils.getDatabaseTable(schemaName, tableName);

        StringBuilder result = new StringBuilder();

        if (dbTable.getColumns().size() > 0) {
            result.append("CREATE TABLE ").append(dbTable.getTableName()).append(" (\n");

            boolean firstLine = true;
            for (DBColumn column : dbTable.getColumns()) {
                if (firstLine) {
                    firstLine = false;
                } else {
                    result.append(",\n");
                }
                result.append(COLUMN_NAME_QUOTE).append(column.getName()).append(COLUMN_NAME_QUOTE)
                        .append(" ").append(column.getType()).append("(").append(column.getSize()).append(")");
                if (column.isNotNullable()) {
                    result.append(" NOT NULL");
                }
            }

            DBPrimaryKey primaryKey = dbTable.getPrimaryKey();
            if (primaryKey.getColumns().size() > 0) {
                result.append(",\nCONSTRAINT");
                if (primaryKey.getName() != null) {
                    result.append(" ").append(primaryKey.getName());
                }
                result.append(" PRIMARY KEY (");
                for (int i = 0; i < primaryKey.getColumns().size(); i++) {
                    result.append(COLUMN_NAME_QUOTE).append(primaryKey.getColumns().get(i)).append(COLUMN_NAME_QUOTE);
                    if (i != primaryKey.getColumns().size() - 1) {
                        result.append(", ");
                    }
                }
                result.append(")");
            }
            result.append("\n);");
            return result.toString();
        }
        return null;
    }

    public static String getSelect(String schemaName, String tableName) {
        DBTable dbTable = DBUtils.getDatabaseTable(schemaName, tableName);

        StringBuilder result = new StringBuilder();

        if (dbTable.getColumns().size() > 0) {
            result.append("SELECT ");

            boolean firstLine = true;
            for (DBColumn column : dbTable.getColumns()) {
                if (firstLine) {
                    firstLine = false;
                } else {
                    result.append(", ");
                }
                result.append(column.getName());
            }

            result.append(" FROM ").append(dbTable.getTableName());

            DBPrimaryKey primaryKey = dbTable.getPrimaryKey();
            if (primaryKey.getColumns().size() > 0) {
                result.append(" WHERE ");
                for (int i = 0; i < primaryKey.getColumns().size(); i++) {
                    result.append(primaryKey.getColumns().get(i)).append("=?");
                    if (i != primaryKey.getColumns().size() - 1) {
                        result.append(" AND ");
                    }
                }
            }
            return result.toString();
        }
        return null;
    }

    public static String getUpdate(String schemaName, String tableName) {
        DBTable dbTable = DBUtils.getDatabaseTable(schemaName, tableName);

        StringBuilder result = new StringBuilder();

        if (dbTable.getColumns().size() > 0) {
            result.append("UPDATE ").append(dbTable.getTableName()).append(" SET ");

            boolean firstLine = true;
            for (DBColumn column : dbTable.getColumns()) {
                if (firstLine) {
                    firstLine = false;
                } else {
                    result.append(", ");
                }
                result.append(column.getName()).append("=?");
            }

            DBPrimaryKey primaryKey = dbTable.getPrimaryKey();
            if (primaryKey.getColumns().size() > 0) {
                result.append(" WHERE ");
                for (int i = 0; i < primaryKey.getColumns().size(); i++) {
                    result.append(primaryKey.getColumns().get(i)).append("=?");
                    if (i != primaryKey.getColumns().size() - 1) {
                        result.append(" AND ");
                    }
                }
            }
            return result.toString();
        }
        return null;
    }
}
