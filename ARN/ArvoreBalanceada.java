public abstract class ArvoreBalanceada<N> {
    protected N raiz;

    public void inserir(int chave) {
        raiz = inserirRecursivo(raiz, chave);
        aposInsercao(raiz, chave);
    }

    protected abstract N inserirRecursivo(N node, int chave);
    protected abstract void aposInsercao(N node, int chave);

    public void remover(int chave) {
        raiz = removerRecursivo(raiz, chave);
        aposRemocao(raiz, chave);
    }

    protected abstract N removerRecursivo(N node, int chave);
    protected abstract void aposRemocao(N node, int chave);

    protected abstract N rotacionarEsquerda(N x);
    protected abstract N rotacionarDireita(N y);
}