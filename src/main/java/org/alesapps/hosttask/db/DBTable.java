package org.alesapps.hosttask.db;

import java.util.Collections;
import java.util.List;

/**
 * Created by Anatoliy Kozhayev on 07.09.2017.
 */
public class DBTable {

    private String schemaName;
    private String tableName;
    private List<DBColumn> columns;
    private DBPrimaryKey primaryKey;

    public DBTable() {
    }

    public DBTable(String schemaName, String tableName, List<DBColumn> columns, DBPrimaryKey primaryKey) {
        this.schemaName = schemaName;
        this.tableName = tableName;
        this.columns = columns;
        this.primaryKey = primaryKey;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<DBColumn> getColumns() {
        return (columns != null) ? columns : Collections.emptyList();
    }

    public void setColumns(List<DBColumn> columns) {
        this.columns = columns;
    }

    public DBPrimaryKey getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(DBPrimaryKey primaryKey) {
        this.primaryKey = primaryKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DBTable)) return false;

        DBTable dbTable = (DBTable) o;

        if (schemaName != null ? !schemaName.equals(dbTable.schemaName) : dbTable.schemaName != null) return false;
        if (tableName != null ? !tableName.equals(dbTable.tableName) : dbTable.tableName != null) return false;
        if (columns != null ? !columns.equals(dbTable.columns) : dbTable.columns != null) return false;
        return primaryKey != null ? primaryKey.equals(dbTable.primaryKey) : dbTable.primaryKey == null;
    }

    @Override
    public int hashCode() {
        int result = schemaName != null ? schemaName.hashCode() : 0;
        result = 31 * result + (tableName != null ? tableName.hashCode() : 0);
        result = 31 * result + (columns != null ? columns.hashCode() : 0);
        result = 31 * result + (primaryKey != null ? primaryKey.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DBTable{" +
                "schemaName='" + schemaName + '\'' +
                ", tableName='" + tableName + '\'' +
                ", columns=" + columns +
                ", primaryKey=" + primaryKey +
                '}';
    }
}
