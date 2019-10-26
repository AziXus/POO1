/**
 * Classe Test permettant d'effectuer des tests avec des opérations sur des matrices
 * @author Müller Robin, Teixeira Carvalho Stéphane
 */
public class Test {
    public static void main(String[] args){
        int modulo = 5;
        int[][] matrice1 = {{1,3,1,1},{3,2,4,2},{1,0,1,0}};
        int[][] matrice2 = {{1,4,2,3,2},{0,1,0,4,2},{0,0,2,0,2}};
        try {
            Matrice m1 = new Matrice(modulo, matrice1);
            Matrice m2 = new Matrice(modulo, matrice2);
            Matrice m3 = m1.addition(m2);
            Matrice m4 = m1.soustraction(m2);
            Matrice m5 = m1.multiplication(m2);
            System.out.println("The modulus is " + m1.getModulo());
            System.out.println("one : \n" + m1);
            System.out.println("two : \n" + m2);
            System.out.println(m3);
            System.out.println(m4);
            System.out.println(m5);
        }
        catch(RuntimeException erreur){
            System.out.println(erreur);
        }
    }
}
