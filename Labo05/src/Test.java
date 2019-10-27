/**
 * Classe Test permettant d'effectuer des tests avec des opérations sur des matrices
 * @author Müller Robin, Teixeira Carvalho Stéphane
 */
public class Test {
    public static void main(String[] args){
        int[][] matrice1 = {{1,3,1,1},{3,2,4,2},{1,0,1,0}};
        int[][] matrice2 = {{1,4,2,3,2},{0,1,0,4,2},{0,0,2,0,2}};
        int[][] matriceInvalide = {{10000,4,2,3,2},{0,1,0,4,2},{0,0,2,0,2}};

        System.out.println("Test en utilisant les matrices de la donnee");
        testMatrice(matrice1, matrice2, 5);
        System.out.println("----------");

        System.out.println("Test en utilisant une matrice invalide");
        testMatrice(matriceInvalide, matrice2, 5);
        System.out.println("----------");

        testMatriceAleatoire(3, 4, 3, 5, 5);
        System.out.println("----------");
        testMatriceAleatoire(3, 4, 3, 5, -1);
        System.out.println("----------");
        testMatriceOverflow();
    }

    /**
     * Test le programme avec deux matrices aléatoires
     * @param n1 nombre de lignes de la matrice 1
     * @param m1 nombre de colonnes de la matrice 1
     * @param n2 nombre de lignes de la matrice 2
     * @param m2 nombre de colonnes de la matrice 2
     * @param mod modulo des deux matrices
     */
    private static void testMatriceAleatoire(int n1, int m1, int n2, int m2, int mod) {
        System.out.println("Test en utilisant deux matrices aleatoires");
        System.out.println("The modulus is " + mod);

        try {
            Matrice matrice1 = new Matrice(m1, n1, mod);
            Matrice matrice2 = new Matrice(m2, n2, mod);

            System.out.println("one : \n" + matrice1);
            System.out.println("two : \n" + matrice2);

            System.out.println("one + two : \n" + matrice1.addition(matrice2));
            System.out.println("one - two : \n" + matrice1.soustraction(matrice2));
            System.out.println("one x two : \n" + matrice1.multiplication(matrice2));
        } catch(RuntimeException erreur){
            System.out.println(erreur);
        }
    }

    /**
     * Test le programme en utilisant les matrices de la donnée
     */
    private static void testMatrice(int[][] matrice1, int[][] matrice2, int modulo) {
        System.out.println("Test matrice");

        try {
            Matrice m1 = new Matrice(modulo, matrice1);
            Matrice m2 = new Matrice(modulo, matrice2);
            System.out.println("The modulus is " + m1.getModulo());
            System.out.println("one : \n" + m1);
            System.out.println("two : \n" + m2);

            System.out.println("one + two : \n" + m1.addition(m2));
            System.out.println("one - two : \n" + m1.soustraction(m2));
            System.out.println("one x two : \n" + m1.multiplication(m2));
        } catch(RuntimeException erreur){
            System.out.println(erreur);
        }
    }

    /**
     * Test l'overflow des matrices
     */
    private static void testMatriceOverflow() {
        System.out.println("Test overflow matrice");

        int modulo = Integer.MAX_VALUE;

        int[][] matrice1 = {{modulo - 1, 0}, {0, 0}};
        int[][] matrice2 = {{2, 0}, {0, 0}};

        Matrice m1 = new Matrice(modulo, matrice1);
        Matrice m2 = new Matrice(modulo, matrice2);

        try {
            m1.addition(m2);
        } catch (ArithmeticException e) {
            System.out.println("Overflow lors de l'addition (" + e + ")");
        }

        try {
            m1.multiplication(m2);
        } catch (ArithmeticException e) {
            System.out.println("Overflow lors de la multiplication (" + e + ")");
        }

    }
}
