package org.alesapps.hosttask.db;

import java.util.Collections;
import java.util.List;

/**
 * Created by Anatoliy Kozhayev on 07.09.2017.
 */
public class DBPrimaryKey {

    private String name;
    private List<String> columns;

    public DBPrimaryKey() {
    }

    public DBPrimaryKey(String name, List<String> columns) {
        this.name = name;
        this.columns = columns;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getColumns() {
        return (columns != null) ? columns : Collections.emptyList();
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DBPrimaryKey)) return false;

        DBPrimaryKey that = (DBPrimaryKey) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return columns != null ? columns.equals(that.columns) : that.columns == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (columns != null ? columns.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DBPrimaryKey{" +
                "name='" + name + '\'' +
                ", columns=" + columns +
                '}';
    }
}
