import hanoi.Hanoi;
import hanoi.HanoiDisplayer;
import hanoi.gui.JHanoi;

public class Main {

    public static void main(String[] args) {
        if(args.length == 0){
            System.out.println("Veuillez entrez un nombre de disque");
            return;
        }
        int nbDisks = 0;

        try {
            nbDisks = Integer.parseInt(args[0]);
        }catch (NumberFormatException num) {
            System.out.println("Le nombre passé en paramètre est incorrecte");
        }

        testHanoiInviteCommande(nbDisks);
        testHanoiApplication();
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
