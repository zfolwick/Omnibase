package Omnibase;

import java.util.List;
import java.util.ArrayList;
/*
 * Each DatabaseConnection class implements the IDataConnection interface, so that
 * each of the database connection objects are seen as the same type.
 * IDataConnection classes perform CRUD operations on databases.
 * TODO: This should be an abstract class. There's too much code duplication between different Connection classes.
 */
class MongoConnection implements IDataConnection
{
    private String currentDatabase;
    private List<IDatabase> databases = new ArrayList<IDatabase>();
    public String getCurrentDatabase() { return this.currentDatabase; }

    public List<String> getDatabaseNames()
    {
        List<String> ret = new ArrayList<String>();
        for ( IDatabase t : databases )
        {
            ret.add(t.getName());
        }

        return ret;
    }

    /*
     * The constructor creates a connection to the Datababase.
     * TODO: implement
     */
    protected MongoConnection()
    {
        System.out.println("Creating new connection to Mongo database");
    }

    /*
     * Creates an empty mongo database using mongo code, and adds it to the list of databases used by the Mongo Connection.
     * TODO: Check if database exists first
     */
    public IDatabase createDatabase(String name)
    {
        System.out.println("Creating a new mongo database");
        IDatabase dbo = new MongoDatabase(name);
        this.databases.add( dbo );
        return dbo;
    }

    /*
     * Sets the current database to be the specified database
     * TODO: Check if database exists first
     */
    public IDatabase useDatabase(String name)
    {
        System.out.println("Setting the working database to perform database operations to: " + name);
        this.currentDatabase = name;
        return getMongoDatabase(name);
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
