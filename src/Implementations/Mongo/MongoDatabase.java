package Omnibase;

import java.util.List;
import java.util.ArrayList;
/*
 * Each Database class implements the IDatabase interface, so that
 * each of the database objects are seen as the same type.
 * Database objects are responsible for CRUD operations on Tables.
 */
class MongoDatabase implements IDatabase
{
    private String databaseName;
    private List<ITable> tables = new ArrayList<ITable>();

    public String getName()
    {
        return this.databaseName;
    }

    public List<String> getTableNames()
    {
        List<String> ret = new ArrayList<String>();
        for ( ITable t : tables )
        {
            ret.add(t.getName());
        }

        return ret;
    }

    /*
     * creates a new mongo database within mongo with the given name
     */
    public MongoDatabase(String name)
    {
        // mongo specific code goes here.
        System.out.println("creating new mongo database");
        this.databaseName = name;
    }

    /*
     * Creates a new table with a name inside the currently used database
     * TODO: Check if table exists first
     */
    public ITable createTable(String name)
    {
        System.out.println("Creating new Mongo Table name: " + name + ", in database " + this.databaseName);

        ITable tbl = new MongoTable(name);
        this.tables.add( tbl );
        return tbl;
    }
}

