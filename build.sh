#!/usr/bin/env bash

MAIN='src/Test/Main.java'
INTERFACES='src/Interfaces/IDataConnection.java src/Interfaces/IDatabase.java src/Interfaces/ITable.java src/Interfaces/IRecord.java'
CONNECT_STRATEGY='src/ConnectClient.java '

# Implementations
MONGO='src/Mongo/MongoConnection.java src/Mongo/MongoDatabase.java src/Mongo/MongoTable.java src/Mongo/MongoRecord.java'
FILE_SYSTEM='src/FileSystem/FileSystemConnection.java src/FileSystem/FileSystemDatabase.java src/FileSystem/FileSystemTable.java'


rm -rf Omnibase/

javac -d . $MAIN $INTERFACES $CONNECT_STRATEGY $MONGO $FILE_SYSTEM
