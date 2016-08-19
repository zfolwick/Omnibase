package Omnibase;

/*
 * This interface is a set of common CRUD applications expected in all tables.
 * Tables are responsible for all CRUD actions on Records.
 */
interface ITable
{
    String getName();
    long getRecordCount();
    void createRecord(String tableName);
}

