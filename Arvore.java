import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Arvore {

    No raiz;

    public int contarNos(No node) {
        if (node == null) return 0;
        return 1 + contarNos(node.esquerda) + contarNos(node.direita);
    }

    public void buscaPreOrdem(No node) {
        if (node != null) {
            System.out.print(node.valor + " ");
            buscaPreOrdem(node.esquerda);
            buscaPreOrdem(node.direita);
        }
    }

    public void buscaEmOrdem(No node) {
        if (node != null) {
            buscaEmOrdem(node.esquerda);
            System.out.print(node.valor + " ");
            buscaEmOrdem(node.direita);
        }
    }

    public void buscaPosOrdem(No node) {
        if (node != null) {
            buscaPosOrdem(node.esquerda);
            buscaPosOrdem(node.direita);
            System.out.println(node.valor + " ");
        }
    }

    public void buscaEmNivel() {
        if (raiz == null) return;

        Queue<No> fila = new LinkedList<>();
        fila.add(raiz);

        while (!fila.isEmpty()) {
            No atual = fila.poll();
            System.out.println(atual.valor + " ");

            if (atual.esquerda != null) fila.add(atual.esquerda);
            if (atual.direita != null) fila.add(atual.direita);
        }
    }

    void posOrdemIterativo(No raiz) {
        if (raiz == null) return;

        Stack<No> pilha1 = new Stack<>();
        Stack<No> pilha2 = new Stack<>();

        pilha1.push(raiz);

        while (!pilha1.isEmpty()) {
            No atual = pilha1.pop();
            pilha2.push(atual);

            if (atual.esquerda != null) pilha1.push(atual.esquerda);
            if (atual.direita != null) pilha1.push(atual.direita);
        }

        while (!pilha2.isEmpty()) {
            System.out.print(pilha2.pop().valor + " ");
        }
    }

    void emOrdemIterativo(No raiz) {
        Stack<No> pilha = new Stack<>();
        No atual = raiz;

        while (atual != null || !pilha.isEmpty()) {
            while (atual != null) {
                pilha.push(atual);
                atual = atual.esquerda;
            }

            atual = pilha.pop();
            System.out.print(atual.valor + " ");
            atual = atual.direita;
        }
    }

    public int contarNosIterativo() {
        if (raiz == null) return 0;

        Queue<No> fila = new LinkedList<>();
        fila.add(raiz);
        int contador = 0;

        while (!fila.isEmpty()) {
            No atual = fila.poll();
            contador++;

            if (atual.esquerda != null) fila.add(atual.esquerda);
            if (atual.direita != null) fila.add(atual.direita);
        }

        return contador;
    }

    public int contarFolhasIterativo() {
        if (raiz == null) return 0;

        Queue<No> fila = new LinkedList<>();
        fila.add(raiz);
        int contador = 0;

        while (!fila.isEmpty()) {
            No atual = fila.poll();

            if (atual.esquerda == null && atual.direita == null) {
                contador++;
            }

            if (atual.esquerda != null) fila.add(atual.esquerda);
            if (atual.direita != null) fila.add(atual.direita);
        }

        return contador;
    }

    public int contarNosComPilha(No raiz) {
        if (raiz == null) return 0;

        Stack<No> pilha = new Stack<>();
        pilha.push(raiz);

        int contador = 0;

        while(!pilha.isEmpty()) {
            No atual = pilha.pop();
            contador++;

            if (atual.direita != null) pilha.push(atual.direita);
            if (atual.esquerda != null) pilha.push(atual.esquerda);
        }

        return contador;
    }

    public int contarNosComFila(No raiz) {
        if (raiz == null) {
            return 0;
        }

        Queue<No> fila = new LinkedList<>();
        fila.offer(raiz);

        int contador = 0;

        while (!fila.isEmpty()) {
            No atual = fila.poll();
            contador++;

            if (atual.esquerda != null) {
                fila.offer(atual.esquerda);
            }
            if (atual.direita != null) {
                fila.offer(atual.direita);
            }
        }

        return contador;
    }
}