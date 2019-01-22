package restapi;

public class Ratelimit {

    private Resources resources;


    @Override
    public String toString() {
        return "Ratelimit{" +
                "resources=" + resources +
                '}';
    }

    public Resources getResources() {
        return resources;
    }

    public void setResources(Resources resources) {
        this.resources = resources;
    }
}
