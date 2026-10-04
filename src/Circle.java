public class Circle extends Shape {
    private final int radius;

    public Circle(String id, int radius, Renderer implementation) {
        super(id, implementation);
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    @Override
    public String execute() {
        return getImplementation().renderCircle(radius);
    }
}