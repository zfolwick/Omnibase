package Omnibase;

import java.util.List;

class Main
{
    /*
     * this is an attempt at creating a fileSystem and Mongo database
     * These tests verify the architecture for creating fileSystem and Mongo databases, tables, and records.
     */
    public static void main(String[] args)
    {
        testCanConnectToMongoDatabase();
        testCanCreateFileSystemConnection();
        testCanCreateMongoDatabase();
        testConnectClientsCreatesDatabaseObject();
        testCanCreateNewMongoCollection();
        testConnectClientCanSetRequiredDatabase();
        testDatabaseObjectsCanCreateTables();
        testConnectClientsCanCreateMultipleDatabases();
        testTableCanCreateARecord();
        testTableCanCreateMultipleRecords();

    }

    private static void testTableCanCreateMultipleRecords()
    {
        ConnectClient d = new ConnectClient(new MongoConnection());
        d.createDatabase("MONGO_DATABASE");
        IDatabase dbo = d.useDatabase("MONGO_DATABASE");
        ITable tbl = dbo.createTable("MONGO_TABLE");
        tbl.createRecord("{\n\t\"field1\" : \"this is a mongo record\"\n}" );
        tbl.createRecord("{\n\t\"field1\" : \"a mongo record\"\n\t\"field2\":123123\n}" );
        tbl.createRecord("{\n\t\"field1\" : \"this is a mongo record\"\n\t\"field3\":[12,12312,232,0,3]\n}" );
        System.out.println(tbl.getRecordCount());
    }

    private static void testTableCanCreateARecord()
    {
        ConnectClient d = new ConnectClient(new MongoConnection());
        d.createDatabase("MONGO_DATABASE");
        IDatabase dbo = d.useDatabase("MONGO_DATABASE");
        ITable tbl = dbo.createTable("MONGO_TABLE");
        tbl.createRecord("{\n \"field1\" : \"this is a mongo record\"\n}" );
        System.out.println("> " + tbl.getRecordCount());
    }

    private static void testConnectClientsCanCreateMultipleDatabases()
    {
        ConnectClient d = new ConnectClient(new MongoConnection());
        d.createDatabase("MONGO_DATABASE");
        d.createDatabase("SECOND_MONGO_DATABASE");

        List<String> s = d.getDatabaseNames();
        for ( String str : s ) {
            System.out.println(str + " ");
        }

        System.out.println("");
    }
    private static void testDatabaseObjectsCanCreateTables()
    {
        ConnectClient d = new ConnectClient(new MongoConnection());
        d.createDatabase("MONGO_DATABASE");
        IDatabase dbo = d.useDatabase("MONGO_DATABASE");

        dbo.createTable("MONGO_TABLE");
        dbo.createTable("SECOND_MONGO_TABLE");

        List<String> s = dbo.getTableNames();
        for ( String str : s )
        System.out.println(str + " ");

        System.out.println("");
    }

    // Can set the database to use, and thus create a table without specifying the database.
    private static void testConnectClientCanSetRequiredDatabase()
    {
        ConnectClient d = new ConnectClient(new MongoConnection());
        d.createDatabase("MONGO_DATABASE");
        IDatabase dbo = d.useDatabase("MONGO_DATABASE");
        dbo.createTable("MONGO_TABLE");
    }

    private static void testCanCreateNewMongoCollection()
    {
        ConnectClient d = new ConnectClient(new MongoConnection());
        IDatabase dbo = d.createDatabase("MONGO_DATABASE");
        dbo.createTable("NEW_MONGO_COLLECTION");
    }
    // ConnectClients create database objects, They are not responsible for table creation
    private static void testConnectClientsCreatesDatabaseObject()
    {

        ConnectClient c = new ConnectClient(new MongoConnection());

        IDatabase dbo = c.createDatabase("MONGO_DATABASE");
    }

    // Creates a database using the same interface used to create any other database type
    private static void testCanCreateMongoDatabase()
    {
        ConnectClient d = new ConnectClient(new MongoConnection());

        d.createDatabase("MONGO_DATABASE");
    }

    // Creates a mongo connection using the same interface used to connect to fileSystem.
    private static void testCanConnectToMongoDatabase()
    {
        ConnectClient c = new ConnectClient(new MongoConnection());
    }

    private static void testCanCreateFileSystemConnection()
    {
        ConnectClient c1 = new ConnectClient(new FileSystemConnection());
    }

}

