package com;

class MongoConstants
{
    private MongoConstants() { }; // prevents instantiation.

    /* the Dummy collection is produced to ensure a mongo collection is created. When a real collection is made, the dummy can go away. */
    public static final String DUMMY_COLLECTION = "DummyCollection";

}
