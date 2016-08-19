package Omnibase;

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
     * TODO: Implement thread-safe increment.
     */
    public void createRecord(String tableName)
    {
        this.countOfRecords++;
    }
}
