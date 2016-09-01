package com;

import java.util.List;
import java.util.ArrayList;

/*
 * Each Database class implements the IDatabase interface, so that
 * each of the database objects are seen as the same type.
 * Database objects are responsible for CRUD operations on Tables.
 */
class FileSystemDatabase implements IDatabase
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
     * creates a new FileSystem database within FileSystem with the given name
     */
    public FileSystemDatabase(String name)
    {
        // FileSystem specific code goes here.
        System.out.println("creating new FileSystem database");
        this.databaseName = name;
    }

    /*
     * Creates a new table with a name inside the currently used database
     * TODO: Check if table exists first
     */
    public ITable createTable(String name)
    {
        System.out.println("Creating new FileSystem Table name: " + name + ", in database " + this.databaseName);

        ITable tbl = new FileSystemTable(name);
        this.tables.add( tbl );
        return tbl;
    }
}


