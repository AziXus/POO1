public class Test {
    public static void main(String[] args){
        Matrice m1 = new Matrice(4,3,2);
        Matrice m2 = new Matrice(5,6,2);
        Operation op = new Soustraction();
        Matrice m3 = m1.addition(m2);
        System.out.println(m1);
        System.out.println(m2);
        System.out.println(m3);
    }
}
