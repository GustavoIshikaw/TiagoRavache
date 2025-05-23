

public class PreOrdem {

    public static void preOrdem(No no) {
        if (no != null) {
            System.out.print(no.valor + " ");
            preOrdem(no.esquerdo);
            preOrdem(no.direito);
        }
    }

    public static void main(String[] args) {
        No raiz = new No('A');
        raiz.esquerdo = new No('B');
        raiz.direito = new No('C');
        raiz.esquerdo.esquerdo = new No('D');
        raiz.esquerdo.direito = new No('E');
        raiz.direito.direito = new No('F');

        System.out.print("Pré-ordem: ");
        preOrdem(raiz);
    }
}
