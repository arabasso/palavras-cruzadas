package sk.host.arabasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arabasso on 29/08/15.
 */
public abstract class Palavra {
    public final String texto;
    public final Posicao posicao;

    public Palavra(String texto, Posicao posicao) {
        this.texto = texto;
        this.posicao = posicao;
    }

    public List<Posicao> posicoesLetrasEmComum(String palavra) {
        ArrayList<Posicao> posicoes = new ArrayList<>();

        for(int i = 0; i < texto.length(); i++){
            for(int j = 0; j < palavra.length(); j++){
                if (texto.charAt(i) == palavra.charAt(j)){
                    Posicao p = cruzarPosicao(i, j);

                    posicoes.add(p);
                }
            }
        }

        return posicoes;
    }

    public abstract Palavra cruzar(String texto, Posicao posicao);
    public abstract void escrever(MatrizLetras matriz);
    public abstract boolean encaixa(MatrizLetras matrizLetras);
    protected abstract Posicao cruzarPosicao(int i, int j);
    // Teste Ola GIT
}
