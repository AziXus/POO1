import hanoi.Hanoi;
import hanoi.HanoiDisplayer;
import hanoi.gui.JHanoi;

public class Main {

    public static void main(String[] args) {
        if(args.length == 0){
            testHanoiApplication();
        }
        else {
            try {
                int nbDisks = Integer.parseInt(args[0]);
                testHanoiInviteCommande(nbDisks);
            } catch (NumberFormatException num) {
                System.out.println("La valeur passée en paramètre n'est pas un nombre");
            }
        }
    }

    /**
     * Test le programme de résolution de tour de hanoi en invite de commande
     * @param nbDisks nombre de disque qu'aura la tour de hanoi
     */
    private static void testHanoiInviteCommande(int nbDisks) {
        HanoiDisplayer hDisplay = new HanoiDisplayer();
        try {
            Hanoi h = new Hanoi(nbDisks, hDisplay);
            h.solve();
        }catch(RuntimeException erreur){
            System.out.println(erreur);
        }
    }

    /**
     * Test l'affichage de l'application graphique de la tour de hanoi
     */
    private static void testHanoiApplication(){
        new JHanoi();
    }
}
