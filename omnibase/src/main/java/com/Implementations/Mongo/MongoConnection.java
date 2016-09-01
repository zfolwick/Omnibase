package com;

import java.util.List;
import java.util.ArrayList;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/*
 * Each DataConnection class implements the IDataConnection interface, so that
 * each of the database connection objects are seen as the same type.
 * IDataConnection classes perform CRUD operations on databases.
 * TODO: This should be an abstract class. There's too much code duplication between different Connection classes.
 */
class MongoConnection implements IDataConnection
{
    private IDatabase currentDatabase;
    private DB currentMongoDatabase;
    // TODO: the currentConnection needs to be an interface?
    private MongoClient currentConnection;
    private List<IDatabase> databases = new ArrayList<IDatabase>();
    private List<String> databaseNames = new ArrayList<String>();

    public String getCurrentDatabase() { return this.currentDatabase.getName(); }
    public DB getCurrentMongoDatabase() { return this.currentMongoDatabase; }
    public List<String> getDatabaseNames() { return this.databaseNames; }

    /*
     * The constructor creates a connection to the Datababase.
     * Connect to a MongoDB instance running on the localhost on the default port 27017
     */
    public MongoConnection()
    {
        this.currentConnection = new MongoClient();
        this.databaseNames = this.currentConnection.getDatabaseNames();
        for ( String n : this.databaseNames ) {
            DB existingDB = this.currentConnection.getDB(n);
            this.databases.add( new com.MongoDatabase(n, existingDB));
        }
    }

    /*
     * Creates an empty mongo database using mongo code, and adds it to the list of databases used by the Mongo Connection.
     * In order to create a database, it must have at least one table with one record inside of it.
     */
    public IDatabase createDatabase(String name)
    {
        // Create the database in mongo
        this.currentMongoDatabase = this.currentConnection.getDB(name);
        this.currentDatabase = new com.MongoDatabase( this.currentMongoDatabase.getName(), this.currentMongoDatabase );
        this.currentDatabase.createTable(MongoConstants.DUMMY_COLLECTION);

        // Create the IDatabase object
        IDatabase dbo = new com.MongoDatabase(name, this.currentMongoDatabase);
        this.databases.add( dbo );
        this.databaseNames.add(name);
        return dbo;
    }

    /*
     * deletes the specified database from IDatabase as well as from the target db connection.
     * For mongo, this means that we iterate through all the collections in the target database, dropping
     * each one.  At that point, when no objects exist in the database, then there will be no database.
     */
    public void dropDatabase(String name)
    {
        this.databases.remove(name);
        this.databaseNames.remove(name);
        this.currentConnection.getDB(name).dropDatabase();

        if ( name.equals(this.currentDatabase))
        {
            System.out.println("the current database is " + name + ".  Setting to null.");
            this.currentDatabase = null;
        }
    }

    /*
     * Sets the current database to be the specified database
     */
    public IDatabase useDatabase(String name)
    {
        DB db = this.currentConnection.getDB(name);
        this.currentDatabase = convertMongoToIDatabase(db);
        this.currentMongoDatabase = db;
        return this.currentDatabase;
    }

    /*
     * Maps vendor MongoDatabase object to IDatabase, and adds it to the list of databases in the currentConnection.
     */
    private IDatabase convertMongoToIDatabase(DB db)
    {
        com.MongoDatabase localDb = new com.MongoDatabase(db.getName(), db);
        this.databases.add(localDb);
        this.databaseNames.add(db.getName());

        return localDb;
    }

    /*
     * Returns the database in the Collection of databases held in this mongoCollection object that matches the input name.
     */
    private IDatabase getMongoDatabase(String name)
    {
        for ( IDatabase db : databases )
        {
            if ( db.getName().equals(name) )
                return db;
        }

        return null;
    }
}
