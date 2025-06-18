public class ArvoreRubroNegra extends ArvoreBalanceada<No> {
    private static final boolean RED   = true;
    private static final boolean BLACK = false;

    @Override
    protected No rotacionarEsquerda(No x) {
        No y = x.direita;
        No yEsq = y.esquerda;
        y.esquerda = x;
        x.direita = yEsq;
        y.pai = x.pai;
        x.pai = y;
        if (yEsq != null) yEsq.pai = x;
        return y;
    }

    @Override
    protected No rotacionarDireita(No y) {
        No x = y.esquerda;
        No xDir = x.direita;
        x.direita = y;
        y.esquerda = xDir;
        x.pai = y.pai;
        y.pai = x;
        if (xDir != null) xDir.pai = y;
        return x;
    }

    private void transplantar(No antigo, No novo) {
        if (antigo.pai == null) {
            raiz = novo;
        } else if (antigo == antigo.pai.esquerda) {
            antigo.pai.esquerda = novo;
        } else {
            antigo.pai.direita = novo;
        }
        if (novo != null) {
            novo.pai = antigo.pai;
        }
    }

    @Override
    protected No inserirRecursivo(No node, int chave) {
        if (node == null) return new No(chave);
        if (chave < node.valor) {
            node.esquerda = inserirRecursivo(node.esquerda, chave);
            node.esquerda.pai = node;
        } else if (chave > node.valor) {
            node.direita = inserirRecursivo(node.direita, chave);
            node.direita.pai = node;
        }
        return node;
    }

    @Override
    protected void aposInsercao(No node, int chave) {
        corrigirInsercao(node);
    }

    private void corrigirInsercao(No z) {
        while (z.pai != null && z.pai.cor == RED) {
            if (z.pai == z.pai.pai.esquerda) {
                No tio = z.pai.pai.direita;
                if (tio != null && tio.cor == RED) {
                    z.pai.cor = BLACK;
                    tio.cor  = BLACK;
                    z.pai.pai.cor = RED;
                    z = z.pai.pai;
                } else {
                    if (z == z.pai.direita) {
                        z = z.pai;
                        rotacionarEsquerda(z);
                    }
                    z.pai.cor = BLACK;
                    z.pai.pai.cor = RED;
                    rotacionarDireita(z.pai.pai);
                }
            } else {
                No tio = z.pai.pai.esquerda;
                if (tio != null && tio.cor == RED) {
                    z.pai.cor = BLACK;
                    tio.cor  = BLACK;
                    z.pai.pai.cor = RED;
                    z = z.pai.pai;
                } else {
                    if (z == z.pai.esquerda) {
                        z = z.pai;
                        rotacionarDireita(z);
                    }
                    z.pai.cor = BLACK;
                    z.pai.pai.cor = RED;
                    rotacionarEsquerda(z.pai.pai);
                }
            }
        }
        ((No)raiz).cor = BLACK;
    }

    @Override
    protected No removerRecursivo(No node, int chave) {
        if (node == null) return null;
        if (chave < node.valor) {
            node.esquerda = removerRecursivo(node.esquerda, chave);
        } else if (chave > node.valor) {
            node.direita = removerRecursivo(node.direita, chave);
        } else {
            No y = node;
            boolean corOriginal = y.cor;
            No x;
            if (node.esquerda == null) {
                x = node.direita;
                transplantar(node, node.direita);
            } else if (node.direita == null) {
                x = node.esquerda;
                transplantar(node, node.esquerda);
            } else {
                y = localizarMin(node.direita);
                corOriginal = y.cor;
                x = y.direita;
                if (y.pai != node) {
                    transplantar(y, y.direita);
                    y.direita = node.direita;
                    if (y.direita != null) y.direita.pai = y;
                }
                transplantar(node, y);
                y.esquerda = node.esquerda;
                if (y.esquerda != null) y.esquerda.pai = y;
                y.cor = node.cor;
            }
            if (corOriginal == BLACK && x != null) {
                corrigirRemocao(x);
            }
        }
        return node;
    }

    @Override
    protected void aposRemocao(No node, int chave) {
    }

    private void corrigirRemocao(No x) {
        while (x != raiz && obterCor(x) == BLACK) {
            if (x == x.pai.esquerda) {
                No w = x.pai.direita;
                if (obterCor(w) == RED) {
                    w.cor = BLACK;
                    x.pai.cor = RED;
                    rotacionarEsquerda(x.pai);
                    w = x.pai.direita;
                }
                if (obterCor(w.esquerda) == BLACK && obterCor(w.direita) == BLACK) {
                    w.cor = RED;
                    x = x.pai;
                } else {
                    if (obterCor(w.direita) == BLACK) {
                        if (w.esquerda != null) w.esquerda.cor = BLACK;
                        w.cor = RED;
                        rotacionarDireita(w);
                        w = x.pai.direita;
                    }
                    w.cor = x.pai.cor;
                    x.pai.cor = BLACK;
                    if (w.direita != null) w.direita.cor = BLACK;
                    rotacionarEsquerda(x.pai);
                    x = (No) raiz;
                }
            } else {
                No w = x.pai.esquerda;
                if (obterCor(w) == RED) {
                    w.cor = BLACK;
                    x.pai.cor = RED;
                    rotacionarDireita(x.pai);
                    w = x.pai.esquerda;
                }
                if (obterCor(w.direita) == BLACK && obterCor(w.esquerda) == BLACK) {
                    w.cor = RED;
                    x = x.pai;
                } else {
                    if (obterCor(w.esquerda) == BLACK) {
                        if (w.direita != null) w.direita.cor = BLACK;
                        w.cor = RED;
                        rotacionarEsquerda(w);
                        w = x.pai.esquerda;
                    }
                    w.cor = x.pai.cor;
                    x.pai.cor = BLACK;
                    if (w.esquerda != null) w.esquerda.cor = BLACK;
                    rotacionarDireita(x.pai);
                    x = (No) raiz;
                }
            }
        }
        if (x != null) x.cor = BLACK;
    }

    private boolean obterCor(No no) {
        return (no == null) ? BLACK : no.cor;
    }

    public void emOrdem() {
        percorrerEmOrdem((No) raiz);
        System.out.println();
    }

    private void percorrerEmOrdem(No node) {
        if (node != null) {
            percorrerEmOrdem(node.esquerda);
            System.out.print(node.valor + (node.cor == RED ? "R " : "B "));
            percorrerEmOrdem(node.direita);
        }
    }

    private No localizarMin(No n) {
        while (n.esquerda != null) n = n.esquerda;
        return n;
    }
}