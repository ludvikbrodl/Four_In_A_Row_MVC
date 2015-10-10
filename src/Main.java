/**
 * Starts the Four In A Row Model, View and Controller and sets up the appropriate connections.
 * @author Ludde
 *
 */
public class Main {

    /**
     * @param args not used
     */
    public static void main(String[] args) {
        Model m = new  Model();
        Controller c = new Controller(m);
        View v = new View(c, m);
        m.addObserver(v);
        v.open();
    }
}
