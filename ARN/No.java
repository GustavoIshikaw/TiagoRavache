public class No {
    int valor;
    No esquerda;
    No direita;
    No pai;
    boolean cor;
    
    public No(int chave) {
        this.valor = chave;
        this.esquerda = null;
        this.direita = null;
        this.pai    = null;
        this.cor    = true;
    }

    @Override
    public String toString() {
        return "No{" + valor + ", " + (cor ? "R" : "B") + "}";
    }
}