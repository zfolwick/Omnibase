package Omnibase;

/*
 * Each Record class implements the IRecord interface, so that
 * each of the Record objects are seen as the same type.
 * Record objects are responsible for CRUD operations on Data.
 */
class MongoRecord implements IRecord
{
    /*
     * creates a mongo document.
     */
    public MongoRecord(String name)
    {
        System.out.println("Creating a new Mongo document");
    }

}

