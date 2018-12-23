package pl.petrusiewicz.ReservationSystem.model;

public class Telephone {

    private int internalNumber;
    private String externalNumber;
    private ConnectionInterface connectionInterface;

    public int getInternalNumber() {
        return internalNumber;
    }

    public void setInternalNumber(int internalNumber) {
        this.internalNumber = internalNumber;
    }

    public String getExternalNumber() {
        return externalNumber;
    }

    public void setExternalNumber(String externalNumber) {
        this.externalNumber = externalNumber;
    }

    public ConnectionInterface getConnectionInterface() {
        return connectionInterface;
    }

    public void setConnectionInterface(ConnectionInterface connectionInterface) {
        this.connectionInterface = connectionInterface;
    }
}
