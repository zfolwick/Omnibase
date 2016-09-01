package com;

import java.util.List;
import java.util.ArrayList;

/*
 * Each DatabaseConnection class implements the IDataConnection interface, so that
 * each of the database connection objects are seen as the same type.
 * TODO: This should be an abstract class.  There's too much code duplication between different Connection classes.
 */
class FileSystemConnection implements IDataConnection
{
    private String currentDatabase;
    private List<IDatabase> databases;
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
    public FileSystemConnection()
    {
        System.out.println("Creating new connection to FileSystem database");
    }

    /*
     * Creates an empty file system database
     */
    public IDatabase createDatabase(String name)
    {
        System.out.println("Creating a new file system database (a.k.a. directory)");
        System.out.println("Creating a new mongo database");
        IDatabase dbo = new FileSystemDatabase(name);
        this.databases = new ArrayList<IDatabase>();
        this.databases.add( dbo );
        return dbo;
    }

    /*
     * deletes the specified database
     */
    public void dropDatabase(String name)
    {
        System.out.println("THIS FEATURE HASN'T BEEN IMPLEMENTED FOR FILESYSTEM DATABASES");
    }

    /*
     * Sets the current directory (database) to be the specified directory
     * TODO: return IDatabase object
     */
    public IDatabase useDatabase(String name)
    {
        System.out.println("Setting the working directory to create files in to: " + name);
        this.currentDatabase = name;
        return null;
    }
}


