public class NoAvl {
    int chave;
    NoAvl esquerda, direita;
    int altura;

    public NoAvl(int chave) {
        this.chave = chave;
        this.esquerda = null;
        this.direita = null;
        this.altura = 1;
    }
}