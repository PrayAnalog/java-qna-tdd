package restapi;

public class Resources {

    private Core core;

    public Core getCore() {
        return core;
    }

    public void setCore(Core core) {
        this.core = core;
    }

    @Override
    public String toString() {
        return "Resources{" +
                "core=" + core +
                '}';
    }
}
