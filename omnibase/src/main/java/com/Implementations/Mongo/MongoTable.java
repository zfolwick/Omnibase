package com;

import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
/*
 * Each Table class implements the ITable interface, so that
 * each of the Table objects are seen as the same type.
 * Table objects are responsible for CRUD operations on Tables.
 */
class MongoTable implements ITable
{
    private String name;
    private long countOfDocuments;
    private DBCollection currentMongoTable;
    private BasicDBObject dummyRecord = new BasicDBObject("dummykey92", "dummyValue42");
    private boolean dummyRecordRemoved = false;

    public String getName()
    {
        return this.name;
    }

    public long getRecordCount()
    {
        return this.countOfDocuments;
    }

    /*
     * creates a mongo Collection in the given database, with the name given in the arguments, and inserts a dummy record.
     */
    public MongoTable(DB database, String collectionName )
    {
        this.name = collectionName;
        DBCollection collection = database.getCollection(this.name);
        collection.insert(dummyRecord);
        this.currentMongoTable = collection;
    }

    /*
     * deletes a record (mongo document) that has the given field name.
     * This is the equivalent of deleting a whole record in oracle.
     * // TODO: implement
     */
    public void deleteRecord(String key, String val)
    {
    }

    /*
     *.adds a json-formatted document to the Collection.  Increments the total number of records in the Collection.
     * TODO: create a thread-safe incrementing function
     * TODO: rename to insertRecord
     */
    public void createRecord(String key, String val)
    {
        // Check if the dummy record is present
        if ( hasDummyRecord() )
        {
            // remove it
            this.dummyRecordRemoved = true;
            this.currentMongoTable.remove(dummyRecord);
        }

        // insert the record
        this.currentMongoTable.insert(new BasicDBObject(key, val));
        this.countOfDocuments++;
    }

    private boolean hasDummyRecord()
    {
        if ( this.dummyRecordRemoved || this.countOfDocuments > 1 )
            return false;

        DBCursor dbCursor = this.currentMongoTable.find(dummyRecord);

        if ( dbCursor.one().get("dummykey92").equals("dummyValue42") )
            return true;

        return false;

    }
}
