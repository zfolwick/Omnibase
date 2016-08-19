package Omnibase;

import java.util.List;
/*
 * The ConnectClient implements the standard interface regardless of the database strategy used. It provides a
 * common set of methods within which users may interface with a database.  The methods here are the only methods
 * a consumer of this class should need in order to interact with multiple different data sources.
 */
class ConnectClient
{
    /*
     * Consumers need to connect to a data store.  This could be mongo, oracle, fileSystem, Cassandra... whatever.
     * This data connection strategy is what is acted upon in this class.
     */
    private IDataConnection strategy;

    /*
     * The data connection strategy is set at the time the user wants to create a connection.
     */
    public ConnectClient(IDataConnection clientToConnect)
    {
        this.strategy = clientToConnect;
    }

    /*
     * Creates a database with a name
     */
    public IDatabase createDatabase(String name)
    {
        return this.strategy.createDatabase(name);
    }

    /*
     * Returns an IDatabase object that can then be used to perform database operations
     */
    public IDatabase useDatabase(String name)
    {
        return this.strategy.useDatabase(name);
    }

    /*
     * gets a list<string> of all the databases
     */
    public List<String> getDatabaseNames()
    {
        return this.strategy.getDatabaseNames();
    }
}
