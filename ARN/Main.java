public class Main {
    public static void main(String[] args) {
        ArvoreRubroNegra arvore = new ArvoreRubroNegra();
        int[] listaChaves = {10, 20, 30, 15, 5, 25};

        for (int chave : listaChaves) {
            arvore.inserir(chave);
        }

        System.out.println("== Árvore após inserções (em ordem) ==");
        arvore.emOrdem();
        
        arvore.remover(15);
        arvore.remover(10);

        System.out.println("== Árvore após remoções (em ordem) ==");
        arvore.emOrdem();
    }
}