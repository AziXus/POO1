/**
 * Classe Test permettant d'effectuer des tests avec des opérations sur des matrices
 * @author Müller Robin, Teixeira Carvalho Stéphane
 */
public class Test {
    public static void main(String[] args){

        testMatrice(3, 4, 3, 5, 2);

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

    /**
     * Fonction permettant de tester les matrices
     * @param n1 nombre de lignes de la matrice 1
     * @param m1 nombre de colonnes de la matrice 1
     * @param n2 nombre de lignes de la matrice 2
     * @param m2 nombre de colonnes de la matrice 2
     * @param mod modulo des deux matrices
     */
    private static void testMatrice(int n1, int m1, int n2, int m2, int mod) {
        System.out.println("The modulus is " + mod);

        Matrice matrice1 = new Matrice(m1, n1, mod);
        Matrice matrice2 = new Matrice(m2, n2, mod);

        System.out.println("one : \n" + matrice1);
        System.out.println("two : \n" + matrice2);

        System.out.println("one + two : \n" + matrice1.addition(matrice2));
        System.out.println("one - two : \n" + matrice1.soustraction(matrice2));
        System.out.println("one x two : \n" + matrice1.multiplication(matrice2));
    }
}
