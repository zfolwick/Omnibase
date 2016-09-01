package com;

/*
 * Each Table class implements the ITable interface, so that
 * each of the Table objects are seen as the same type.
 * Table objects are responsible for CRUD operations on Tables.
 */
class FileSystemTable implements ITable
{
    private String name;

    private long countOfRecords;

    public long getRecordCount()
    {
        return this.countOfRecords;
    }

    public String getName()
    {
        return this.name;
    }

    public FileSystemTable(String name)
    {
        this.name = name;
    }

    /*
     * deletes a record (file system object) that has the given field name.
     * This is the equivalent of deleting a whole record in Oracle.
     * // TODO: implement
     */
    public void deleteRecord(String key, String val)
    {
    }

    /*
     * TODO: Implement thread-safe increment.
     */
    public void createRecord( String key, String val )
    {
        this.countOfRecords++;
    }
}
