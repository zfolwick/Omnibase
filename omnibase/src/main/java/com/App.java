package com;

/*
 * this is an attempt at creating a fileSystem and Mongo database
 * These tests verify the architecture for creating fileSystem and Mongo databases, tables, and records.
 */
public class App
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        testCanConnectToMongoDatabase();
    }

    // Creates a mongo connection using the same interface used to connect to fileSystem.
    private static void testCanConnectToMongoDatabase()
    {
        ConnectClient c = new ConnectClient(new MongoConnection());
    }
}
