package sk.host.arabasso;

/**
 * Created by arabasso on 29/08/15.
 */
public class PalavraHorizontal extends Palavra {

    public PalavraHorizontal(String texto, Posicao posicao) {
        super(texto, posicao);
    }

    @Override
    public void escrever(MatrizLetras matriz) {
        for (int x = 0; x < texto.length(); x++){
            matriz.escreverLetra(posicao.x + x, posicao.y, texto.charAt(x));
        }
    }

    @Override
    protected Posicao cruzarPosicao(int i, int j) {
        return new Posicao(posicao.x + i, posicao.y - j);
    }

    @Override
    public Palavra cruzar(String texto, Posicao posicao) {
        return new PalavraVertical(texto, posicao);
    }

    @Override
    public boolean encaixa(MatrizLetras matrizLetras) {
        if (palavraCabe(matrizLetras)){
            return false;
        }

        int x, i;

        for (x = posicao.x, i = 0; x < posicao.x + texto.length(); x++, i++) {
            if (palavraNaoEncaixa(matrizLetras, x, i)){
                return false;
            }
        }

        return true;
    }

    private boolean palavraNaoEncaixa(MatrizLetras matrizLetras, int x, int i) {
        char letra = texto.charAt(i);
        boolean b = matrizLetras.lerLetra(x, posicao.y) != letra;

        for (int j = posicao.y - 1; j <= posicao.y + 1; j++) {
            if (b && matrizLetras.temLetra(x, j)) {
                return true;
            }
        }
        return false;
    }

    private boolean palavraCabe(MatrizLetras matrizLetras) {
        return matrizLetras.temLetra(posicao.x - 1, posicao.y) || matrizLetras.temLetra(posicao.x + texto.length(), posicao.y);
    }
}
