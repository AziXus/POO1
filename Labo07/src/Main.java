import hanoi.Hanoi;
import hanoi.HanoiDisplayer;
import hanoi.gui.JHanoi;
import util.Iterateur;
import util.Pile;

public class Main {

    public static void main(String[] args) {
        testPile(4);
        System.out.println("Test lancment Hanoi");
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
     * @param nbElement nombre de disque qu'aura la tour de hanoi
     */
    private static void testPile(int nbElement) {
        Pile p = new Pile();
        System.out.println("Pile vide ? " + p.isEmpty());
        System.out.println("Taille    : " + p.size());
        for(int i = 0; i < nbElement; i++){
            System.out.println("Ajout de l'élément : " + i);
            p.push(i);
        }
        System.out.println("Pile vide ? " + p.isEmpty());
        System.out.println("Taille    : " + p.size());

        System.out.println("Affichage des éléments de la pile sous forme du tableau : ");
        Object[] elements = p.getElements();
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        for(int i = 0; i < elements.length; i++){
            sb.append(elements[i] + " ");
        }
        sb.append("]");
        System.out.println(sb.toString());

        System.out.println("Supression d'un élément : " + p.pop());
        System.out.println("Taille    : " + p.size());

        System.out.println("Affichage des éléments de la pile sous forme du tableau : ");
        elements = p.getElements();
        sb = new StringBuilder();
        sb.append("[ ");
        for(int i = 0; i < elements.length; i++){
            sb.append(elements[i] + " ");
        }
        sb.append("]");
        System.out.println(sb.toString());

        System.out.println("Parcours de la pile avec un Iterateur");
        Iterateur it = p.iterateur();
        sb = new StringBuilder();
        while(it.possedeSuivant())
            sb.append(it.suivant() + " ");
        System.out.println(sb.toString());

        System.out.println(p.toString());
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
