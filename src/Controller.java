import java.util.Observable;
import java.util.Observer;

/**
 * Created by Ludde on 2015-10-02.
 */
public class Controller  {

    private Model model;

    public Controller(Model model) {
        this.model = model;
    }

    public void playRow(int col) {
        try {
            model.playRow(col);
        } catch (InvalidMoveException e) {
            //Not a valid move;
        	System.out.println("not a free row");
        }

    }

    public void changePlayerName(String player, int index) {

    }

    public void resetGame() {

    }


}
