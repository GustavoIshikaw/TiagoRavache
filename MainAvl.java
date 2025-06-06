public class MainAvl {
    public static void main(String[] args) {
        ArvoreAvl avl = new ArvoreAvl();

        int[] valores = {30, 20, 40, 10, 25, 35, 50, 5, 15, 27};
        for (int v : valores) {
            avl.inserir(v);
        }
        avl.imprimirEmOrdem();
        System.out.println("Altura: " + avl.altura());

        avl.inserir(17);
        avl.imprimirEmOrdem();
        System.out.println("Altura: " + avl.altura());

        avl.inserir(60);
        avl.imprimirEmOrdem();
        System.out.println("Altura: " + avl.altura());

        avl.inserir(22);
        avl.imprimirEmOrdem();
        System.out.println("Altura: " + avl.altura());

        avl.excluir(20);
        avl.imprimirEmOrdem();
        System.out.println("Altura: " + avl.altura());

        avl.excluir(30);
        avl.imprimirEmOrdem();
        System.out.println("Altura: " + avl.altura());

        avl.excluir(5);
        avl.imprimirEmOrdem();
        System.out.println("Altura: " + avl.altura());
    }
}