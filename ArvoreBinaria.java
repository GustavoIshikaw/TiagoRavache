//Contando o numer de nos



class No {
    char valor;
    No esquerda, direita;

    No(char valor) {
        this.valor = valor;
        esquerda = direita = null;
    }
}

public class ArvoreBinaria {

    public static int contarNos(No raiz) {
        if (raiz == null) {
            return 0;
        }
        return 1 + contarNos(raiz.esquerda) + contarNos(raiz.direita);
    }

    public static void main(String[] args) {
        No raiz = new No('A');
        raiz.esquerda = new No('B');
        raiz.direita = new No('C');
        raiz.esquerda.esquerda = new No('D');
        raiz.esquerda.direita = new No('E');
        raiz.direita.direita = new No('F');

        int totalNos = contarNos(raiz);
        System.out.println("Total de nós na árvore: " + totalNos);
    }
}
