import hanoi.Hanoi;
import hanoi.HanoiDisplayer;
import hanoi.gui.JHanoi;
import util.Iterateur;
import util.Pile;

public class Main {

    public static void main(String[] args) {
        Pile list = new Pile();

        list.push("A");
        list.push("B");
        list.push("C");
        list.push("D");
        list.push("E");

        // ListIterator to traverse the list
        Iterateur iterator = list.iterateur();

        // Traversing the list in forward direction
        System.out.println("Displaying list elements : ");

        while (iterator.possedeSuivant())
            System.out.print(iterator.suivant() + " ");
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
        try {
            Hanoi h = new Hanoi(nbDisks);
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
