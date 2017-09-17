package org.alesapps.hosttask.db;

/**
 * Created by Anatoliy Kozhayev on 07.09.2017.
 */
public class DBColumn {

    private String name;
    private String type;
    private Integer size;
    private boolean notNullable;

    public DBColumn() {
    }

    public DBColumn(String name, String typeName, Integer columnSize, boolean notNullable) {
        this.name = name;
        this.type = typeName;
        this.size = columnSize;
        this.notNullable = notNullable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public boolean isNotNullable() {
        return notNullable;
    }

    public void setNotNullable(boolean notNullable) {
        this.notNullable = notNullable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DBColumn)) return false;

        DBColumn dbColumn = (DBColumn) o;

        if (notNullable != dbColumn.notNullable) return false;
        if (name != null ? !name.equals(dbColumn.name) : dbColumn.name != null) return false;
        if (type != null ? !type.equals(dbColumn.type) : dbColumn.type != null) return false;
        return size != null ? size.equals(dbColumn.size) : dbColumn.size == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (size != null ? size.hashCode() : 0);
        result = 31 * result + (notNullable ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DBColumn{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", size=" + size +
                ", notNullable=" + notNullable +
                '}';
    }
}
