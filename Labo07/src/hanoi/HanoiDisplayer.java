package hanoi;
/**
 * Classe gérant l'affichage de la classe Hanoi
 * @author Müller Robin, Teixeira Carvalho Stéphane
 */
public class HanoiDisplayer{
    /**
     * Affiche l'état du tour courant.
     * @param h tour de Hanoi à utiliser
     */
    public void display(Hanoi h){
        StringBuilder str = new StringBuilder();
        str.append("-- Turn: ").append(h.turn()).append("\n");
        str.append(h);
        System.out.println(str);
    }
}
