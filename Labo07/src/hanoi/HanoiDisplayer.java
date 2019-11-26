package hanoi;
/**
 * Classe de gestion des matrices
 * @author Müller Robin, Teixeira Carvalho Stéphane
 */
public class HanoiDisplayer{
    public void display(Hanoi h){
        StringBuilder str = new StringBuilder();
        str.append("-- Turn: ").append(h.turn()).append("\n");
        str.append(h);
        System.out.println(str);
    }
}
