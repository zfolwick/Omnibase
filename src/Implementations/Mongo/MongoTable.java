package Omnibase;

/*
 * Each Table class implements the ITable interface, so that
 * each of the Table objects are seen as the same type.
 * Table objects are responsible for CRUD operations on Tables.
 */
class MongoTable implements ITable
{
    private String name;
    private long countOfDocuments;

    public String getName()
    {
        return this.name;
    }

    public long getRecordCount()
    {
        return this.countOfDocuments;
    }

    /*
     * creates a mongo Collection
     */
    public MongoTable(String name)
    {
        this.name = name;
    }

    /*
     *.adds a json-formatted document to the Collection.  Increments the total number of records in the Collection.
     * TODO: validate the incoming json is correct.
     * TODO: create a thread-safe incrementing function
     */
    public void createRecord(String record)
    {
        System.out.println("creating a new json document");
        System.out.println(record);
        //Call to MongoRecord class to create new record
        IRecord rcd = new MongoRecord(record);
        this.countOfDocuments++;

    }
}

