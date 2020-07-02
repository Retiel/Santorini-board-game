package it.polimi.ingsw.PSP33.utils;

/**
 * Connection class used to hold connection informations
 */
public class Connection {

    /**
     * IP
     */
    private final String server;

    /**
     * Port
     */
    private final int port;

    public Connection(String server, int port) {
        this.server = server;
        this.port = port;
    }

    public String getServer() {
        return server;
    }

    public int getPort() {
        return port;
    }
}
