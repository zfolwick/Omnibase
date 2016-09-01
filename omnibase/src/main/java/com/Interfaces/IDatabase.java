package com;

import java.util.List;
/*
 * This interface is a set of common CRUD applications expected in all databases.
 * Databases are responsible for all CRUD actions on Tables.
 */
interface IDatabase
{
    String getName();
    ITable createTable(String tableName);
    List<String> getTableNames();
}
