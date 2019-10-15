public class Test {
    public static void main(String[] args){
        int[][] matrice1 = {{1,3,1,1},{3,2,4,2},{1,0,1,0}};
        int[][] matrice2 = {{1,0,2,3,2},{0,1,0,4,2},{0,0,2,0,2}};
        Matrice m1 = new Matrice(5, matrice1);
        Matrice m2 = new Matrice(5,matrice2);
        Matrice m3 = m1.addition(m2);
        Matrice m4 = m1.soustraction(m2);
        Matrice m5 = m1.multiplication(m2);
        System.out.println(m1);
        System.out.println(m2);
        System.out.println(m3);
        System.out.println(m4);
        System.out.println(m5);
    }
}
