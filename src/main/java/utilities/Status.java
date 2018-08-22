package utilities;

public enum Status {
    SUCCESS ("Success"),
    ERROR ("Error");

    private String status;

    Status(String stat) {
        this.status = stat;
    }

    public String getStatus() {
        return status;
    }
}
