import hanoi.Hanoi;
import hanoi.HanoiDisplayer;
import hanoi.gui.*;
import util.Iterateur;
import util.Pile;

/**
 * Classe Test permettant d'effectuer des tests pour les fonctionnalités d'une pile
 * ainsi que la résolution d'une tour de Hanoi
 * @author Müller Robin, Teixeira Carvalho Stéphane
 */
public class Test {

    public static void main(String[] args) {
        int nbElementPile = 4;
        System.out.println("Test de la création d'une pile\n");
        testPile(nbElementPile);

        System.out.println("Test tour de Hanoi");
        //Si aucun paramètre n'est passée l'application graphique est lancée
        if(args.length == 0){
            testHanoiApplication();
        }
        else {
            //Essaye de convertir le paramètre passée en entier
            try {
                int nbDisks = Integer.parseInt(args[0]);
                testHanoiInviteCommande(nbDisks);
            } catch (NumberFormatException num) {
                System.out.println("La valeur passée en paramètre n'est pas un nombre");
            }
        }
    }

    /**
     * Test les fonctionnalités de la classe pile
     * @param nbElement nombre d'éléments à créer dans la pile
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
        for(Object element : elements){
            sb.append(element).append(" ");
        }
        sb.append("]");
        System.out.println(sb.toString());

        System.out.println("Supression d'un élément : " + p.pop());
        System.out.println("Taille                  : " + p.size());

        System.out.println("Affichage des éléments de la pile sous forme du tableau : ");
        elements = p.getElements();
        sb = new StringBuilder();
        sb.append("[ ");
        for(Object element : elements){
            sb.append(element).append(" ");
        }
        sb.append("]");
        System.out.println(sb.toString());

        System.out.println("Parcours de la pile avec un Iterateur : ");
        Iterateur it = p.iterateur();
        sb = new StringBuilder();
        while(it.possedeSuivant())
            sb.append(it.suivant()).append(" ");
        System.out.println(sb.toString());
        System.out.println("Affichage de la pile à l'aide de la méthode ToString() : ");
        System.out.println(p.toString());

        System.out.println("Suppression des éléments de la pile : ");
        System.out.println(p.pop());
        System.out.println(p.pop());
        System.out.println(p.pop());
        System.out.println("Suppression dans une pile vide : ");
        System.out.println("Pile vide ? " + p.isEmpty());
        System.out.println("Taille    : " + p.size());
        System.out.println(p.pop() + "\n");
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
