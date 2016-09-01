package com;

import java.util.List;
import java.util.ArrayList;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
/*
 * Each Database class implements the IDatabase interface, so that
 * each of the database objects are seen as the same type.
 * Database objects are responsible for CRUD operations on Tables.
 */
class MongoDatabase implements IDatabase
{
    private String databaseName;
    private IDatabase currentDatabase;
    private DB currentMongoDatabase;
    private MongoClient currentConnection;
    private List<ITable> tables = new ArrayList<ITable>();
    private boolean deletedDummyCollection;

    public String getName() { return this.currentMongoDatabase.getName(); }


    public List<String> getTableNames()
    {
        return new ArrayList<String>(this.currentMongoDatabase.getCollectionNames());
    }

    /*
     * creates a new mongo database within mongo with the given name, and a reference to the actual mongo database is called.
     * When a new database is created in Mongo, it doesn't show up until we actually do something with it, so we add a dummy collection,
     * which we will then delete later.
     */
    public MongoDatabase(String name, DB db)
    {
        if ( db == null ) {
            System.out.println(">>>>>>>>>>OOOPS!");
        }

        this.databaseName = name;
        this.currentMongoDatabase = db;
        this.deletedDummyCollection = false;
    }

    /*
     * Creates a new table with a name inside the currently used database
     */
    public ITable createTable(String name)
    {
        // First, check to see if the dummy table exists.
        DBCollection dummy = this.currentMongoDatabase.getCollection(MongoConstants.DUMMY_COLLECTION);
        if ( hasCollection(dummy) )
        {
            // It exists, so remove it.
            dummy.drop();
        }

        DB db = this.currentMongoDatabase;
        ITable tbl = new com.MongoTable( db, name );

        this.tables.add( tbl );
        return tbl;
    }

    private boolean hasCollection( DBCollection collection )
    {
        if ( deletedDummyCollection )
            return false;

        if ( collection == null )
            return false;

        return true;
    }
}
