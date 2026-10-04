import java.util.Objects;

public abstract class Shape {
    private final String id;
    private Renderer implementation;

    public Shape(String id, Renderer implementation) {
        this.id = Objects.requireNonNull(id);
        this.implementation = Objects.requireNonNull(implementation);
    }

    public String getId() {
        return id;
    }

    protected Renderer getImplementation() {
        return implementation;
    }

    public void setImplementation(Renderer implementation) {
        this.implementation = Objects.requireNonNull(implementation);
    }

    public abstract String execute();
}
