import hanoi.Hanoi;
import hanoi.HanoiDisplayer;
import hanoi.gui.JHanoi;

public class Main {

    public static void main(String[] args) {
        HanoiDisplayer hDisplay = new HanoiDisplayer();
        Hanoi h = new Hanoi(3, hDisplay);
        h.solve();

        new JHanoi();
    }
}
