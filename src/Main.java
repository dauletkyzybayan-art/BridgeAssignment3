public class Main {
    private static int passed;
    private static int total;

    public static void main(String[] args) {
        if (args.length != 1 || !args[0].equals("--demo")) {
            System.out.println("Usage: java -cp out Main --demo");
            return;
        }

        Renderer vector = new VectorRenderer();
        Renderer raster = new RasterRenderer();

        Circle circleVector = new Circle("circle-1", 2, vector);
        Circle circleRaster = new Circle("circle-1", 2, raster);
        Square squareVector = new Square("square-1", 3, vector);
        Square squareRaster = new Square("square-1", 3, raster);

        check("T1", "Circle + VectorRenderer",
                circleVector.execute(), "VECTOR circle radius=2");

        check("T2", "Circle + RasterRenderer",
                circleRaster.execute(), "RASTER circle radius=2");

        check("T3", "Square + VectorRenderer",
                squareVector.execute(), "VECTOR square side=3");

        check("T4", "Square + RasterRenderer",
                squareRaster.execute(), "RASTER square side=3");

        checkRuntimeSwitch();
        Renderer ascii = new AsciiRenderer();

        check("T6", "Circle + AsciiRenderer",
                new Circle("circle-1", 2, ascii).execute(),
                "ASCII circle radius=2");

        check("T7", "Square + AsciiRenderer",
                new Square("square-1", 3, ascii).execute(),
                "ASCII square side=3");
        System.out.println("SUMMARY: " + passed + "/" + total + " PASS");

        if (passed != total) {
            System.exit(1);
        }
    }

    private static void checkRuntimeSwitch() {
        Circle circle = new Circle("switch-circle", 2, new VectorRenderer());
        Circle original = circle;

        String originalId = circle.getId();
        int originalRadius = circle.getRadius();
        String before = circle.execute();

        circle.setImplementation(new RasterRenderer());
        String after = circle.execute();

        boolean sameObject = circle == original;
        boolean stateUnchanged = originalId.equals(circle.getId())
                && originalRadius == circle.getRadius();

        String actual = "sameObject=" + sameObject
                + " | stateUnchanged=" + stateUnchanged
                + " | before=" + before
                + " | after=" + after;

        String expected = "sameObject=true | stateUnchanged=true"
                + " | before=VECTOR circle radius=2"
                + " | after=RASTER circle radius=2";

        check("T5", "Circle: VectorRenderer -> RasterRenderer",
                actual, expected);
    }

    private static void check(String testId, String classes,
                              String actual, String expected) {
        total++;
        boolean success = actual.equals(expected);

        if (success) {
            passed++;
        }

        System.out.println(testId + " " + (success ? "PASS" : "FAIL")
                + " | " + classes + " | result=" + actual);

        if (!success) {
            System.out.println(" expected=" + expected);
        }
    }
}