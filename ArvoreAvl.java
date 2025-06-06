public class ArvoreAvl {
    NoAvl raiz;

    public ArvoreAvl() {
        this.raiz = null;
    }

    public void inserir(int chave) {
        raiz = inserirRec(raiz, chave);
    }

    public void excluir(int chave) {
        raiz = excluirRec(raiz, chave);
    }

    public int altura() {
        return getAltura(raiz);
    }

    public void imprimirEmOrdem() {
        imprimirEmOrdemRec(raiz);
        System.out.println();
    }

    private NoAvl inserirRec(NoAvl no, int chave) {
        if (no == null) {
            return new NoAvl(chave);
        }
        if (chave < no.chave) {
            no.esquerda = inserirRec(no.esquerda, chave);
        } else if (chave > no.chave) {
            no.direita = inserirRec(no.direita, chave);
        } else {
            return no;
        }
        no.altura = 1 + Math.max(getAltura(no.esquerda), getAltura(no.direita));
        int fb = getBalance(no);
        if (fb > 1 && chave < no.esquerda.chave) {
            return rotacaoDireita(no);
        }
        if (fb < -1 && chave > no.direita.chave) {
            return rotacaoEsquerda(no);
        }
        if (fb > 1 && chave > no.esquerda.chave) {
            no.esquerda = rotacaoEsquerda(no.esquerda);
            return rotacaoDireita(no);
        }
        if (fb < -1 && chave < no.direita.chave) {
            no.direita = rotacaoDireita(no.direita);
            return rotacaoEsquerda(no);
        }
        return no;
    }

    private NoAvl excluirRec(NoAvl no, int chave) {
        if (no == null) {
            return null;
        }
        if (chave < no.chave) {
            no.esquerda = excluirRec(no.esquerda, chave);
        } else if (chave > no.chave) {
            no.direita = excluirRec(no.direita, chave);
        } else {
            if (no.esquerda == null || no.direita == null) {
                NoAvl temp = (no.esquerda != null) ? no.esquerda : no.direita;
                if (temp == null) {
                    no = null;
                } else {
                    no = temp;
                }
            } else {
                NoAvl temp = noMinimo(no.direita);
                no.chave = temp.chave;
                no.direita = excluirRec(no.direita, temp.chave);
            }
        }
        if (no == null) {
            return null;
        }
        no.altura = 1 + Math.max(getAltura(no.esquerda), getAltura(no.direita));
        int fb = getBalance(no);
        if (fb > 1 && getBalance(no.esquerda) >= 0) {
            return rotacaoDireita(no);
        }
        if (fb > 1 && getBalance(no.esquerda) < 0) {
            no.esquerda = rotacaoEsquerda(no.esquerda);
            return rotacaoDireita(no);
        }
        if (fb < -1 && getBalance(no.direita) <= 0) {
            return rotacaoEsquerda(no);
        }
        if (fb < -1 && getBalance(no.direita) > 0) {
            no.direita = rotacaoDireita(no.direita);
            return rotacaoEsquerda(no);
        }
        return no;
    }

    private NoAvl noMinimo(NoAvl node) {
        NoAvl atual = node;
        while (atual.esquerda != null) {
            atual = atual.esquerda;
        }
        return atual;
    }

    private int getAltura(NoAvl no) {
        return (no == null) ? 0 : no.altura;
    }

    private int getBalance(NoAvl no) {
        return (no == null) ? 0 : getAltura(no.esquerda) - getAltura(no.direita);
    }

    private NoAvl rotacaoDireita(NoAvl y) {
        NoAvl x = y.esquerda;
        NoAvl T2 = x.direita;
        x.direita = y;
        y.esquerda = T2;
        y.altura = 1 + Math.max(getAltura(y.esquerda), getAltura(y.direita));
        x.altura = 1 + Math.max(getAltura(x.esquerda), getAltura(x.direita));
        return x;
    }

    private NoAvl rotacaoEsquerda(NoAvl x) {
        NoAvl y = x.direita;
        NoAvl T2 = y.esquerda;
        y.esquerda = x;
        x.direita = T2;
        x.altura = 1 + Math.max(getAltura(x.esquerda), getAltura(x.direita));
        y.altura = 1 + Math.max(getAltura(y.esquerda), getAltura(y.direita));
        return y;
    }

    private void imprimirEmOrdemRec(NoAvl no) {
        if (no != null) {
            imprimirEmOrdemRec(no.esquerda);
            System.out.print(no.chave + " ");
            imprimirEmOrdemRec(no.direita);
        }
    }
}
