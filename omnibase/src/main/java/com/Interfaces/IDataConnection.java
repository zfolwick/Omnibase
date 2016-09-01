package com;

import java.util.List;
/*
 * This interface is a set of common CRUD applications expected in all databases.
 * IDataConnection classes perform CRUD operations on databases.
 */
interface IDataConnection
{
    String getCurrentDatabase();
    List<String> getDatabaseNames();

    IDatabase createDatabase(String namme);
    void dropDatabase(String name);
    IDatabase useDatabase(String name);
}

