package com;

import java.util.List;
import java.util.ArrayList;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
/**
 * Unit test for simple App.
 */
public class BasicMongoTest
    extends TestCase
{
    static final String expectedDBName = "MONGO_DATABASE";

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public BasicMongoTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( BasicMongoTest.class );
    }

    // Can set the database to use, and thus create a table without specifying the database.
    public static void testConnectClientCanSetRequiredDatabase()
    {
        com.ConnectClient d = new com.ConnectClient(new com.MongoConnection());
        IDatabase dbo = d.useDatabase(expectedDBName);
        assertTrue(dbo.getName() == expectedDBName);
    }

    // To create a database in mongo, it only is created when something is in there, so we create a dummytable with a dummyrecord.
    public static void testConnectClientCanCreateAndDropDatabase()
    {
        // Setup
        com.ConnectClient d = new com.ConnectClient(new com.MongoConnection());
        int originalDatabaseNumber = d.getDatabaseNames().size();

        // Testable statement
        IDatabase dbo = d.createDatabase(expectedDBName);
        int newSize = d.getDatabaseNames().size();

        assertTrue("Expected database to be created", newSize == originalDatabaseNumber + 1);
        assertTrue(dbo.getName() == expectedDBName);

        // Clean up
        d.dropDatabase(expectedDBName);
        assertTrue("Expected database to be deleted", d.getDatabaseNames().size() == originalDatabaseNumber);
    }

    // In mongo, a collection MUST be created in order to create a database, so there will always be at least 1
    // table.  This test validates that the dummy table is removed and replaced when a new collection comes online, and that we can get all the table names.
    // In doing so, we also verify that we can drop a table.
    public static void testConnectClienCanCreateATable()
    {
        com.ConnectClient d = new com.ConnectClient(new com.MongoConnection());
        IDatabase dbo = d.createDatabase(expectedDBName);
        ITable table = dbo.createTable("NEW_MONGO_TABLE");

        assertTrue(table.getName() == "NEW_MONGO_TABLE");

        List<String> s = dbo.getTableNames();
        assertTrue("Expected 2 collections in the test database " + expectedDBName, s.size() == 2);

        d.dropDatabase(expectedDBName);
    }

    public static void testTableCanCreateRecords()
    {
        com.ConnectClient d = new com.ConnectClient(new com.MongoConnection());
        IDatabase dbo = d.createDatabase(expectedDBName);
        ITable table = dbo.createTable("NEW_MONGO_TABLE");
        // This is the first record.  It should delete the dummy record and input it's own.
        table.createRecord( "field1", "this is a mongo record" );

        assertTrue("Expected a dummy record to be removed", table.getRecordCount() == 1);

        table.createRecord("field2", "here's another record");
        assertTrue("Expected two records in the database", table.getRecordCount() == 2);

        d.dropDatabase(expectedDBName);
    }
}
