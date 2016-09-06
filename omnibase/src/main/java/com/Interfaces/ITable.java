package com;

/*
 * This interface is a set of common CRUD applications expected in all tables.
 * Tables are responsible for all CRUD actions on Records.
 */
interface ITable
{
    String getName();
    long getRecordCount();
    void createRecord( String key, String val );
    void deleteRecord( String key, String val );
    /*
     * Actions a table can perform:
     * - create a new record - this means that a table can apply a key-value pair, with the value taking on either strongly typed, or not, and may be null or empty, while the key cannot.
     * - delete a record - this means that a table can remove all values in the document or row, and then remove the row, or delete the document.
     * - update a value - this means that, in a key-value relationship, that a value is updated in an existing record (document or row)
     * - update a key - this is equivalent of changing a column or a field name.
     * - create a value - this means that, in a key-value relationship, that a value is created in an new record (document or row), and either initialized to null, or something else.
     * - create a key - this is equivalent of adding a column or a field name.
     * - delete a value - this means that, in a key-value relationship, that a value is set to null in an existing record (document or row).
     * - delete a key - this is equivalent of removing a column or a field name.
     * - get a value - this means that, in a key-value relationship, that a value is retrieved from an existing record (document or row), and returned to the caller
     * - get a key - this is equivalent of retrieving a column or a field's
     *
     *   These are the basic CRUD operations and need to be abstracted in a way that can be implemented in the same way across all databases.
     *   In other words, there exists some basic unit of datum that is stored to the hard disk, requiring a pointer to that datum.  This takes on different vocabulary in different databases.
     *   In Mongo:
     *   Pointer: field.
     *   Datum: value
     *   Record: document; e.g., a set of fields and values.
     *   Table: collection; e.g., a set of Records
     *
     *   In Oracle:
     *   Pointer: Column
     *   Datum: Value in a row
     *   Record: a row in the table, e.g., a dictionary of columns, and values.
     *   Table: The set of columns, as well as every value for each column.  e.g., an array where e is assigned to the name of the column.
     *
     *   In Mongo:
     *   - Collections do not know about what is inside documents, unless they are indices.  This is equivalent to not knowing all the keys, except those with a special property.
     *   - An entire record is immediately known based upon a single index lookup, whereas in oracle or a relational database, there are a lot more operations to perform before returning a record.
     *
     *   In Oracle:
     *   - Tables know all the column names.  This is equivalent to knowing all the keys, and they know the properties of those keys.
     *   - Columns could be thought of as a set of arrays required to be the same size. Getting a single record from a database would require looking up the required value in the key array(s)
     *   - Rows are defined to be the value of an array at index i.
     *
     *   Queries:
     *   - A query is a request made to a database connection.  It will return all information possible, given certain filters on the data.
     *   - A database connection executes queries against it's db connection.  It passes the query into it's local parsing engine and returns an expected query result.
     *
     */
}

