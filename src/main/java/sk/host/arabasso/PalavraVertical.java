package sk.host.arabasso;

/**
 * Created by arabasso on 29/08/15.
 */
public class PalavraVertical extends Palavra {

    public PalavraVertical(String texto, Posicao posicao) {
        super(texto, posicao);
    }

    @Override
    public void escrever(MatrizLetras matriz) {
        for (int y = 0; y < texto.length(); y++){
            matriz.escreverLetra(posicao.x, posicao.y + y, texto.charAt(y));
        }
    }

    @Override
    protected Posicao cruzarPosicao(int i, int j) {
        return new Posicao(posicao.x - j, posicao.y + i);
    }

    @Override
    public Palavra cruzar(String texto, Posicao posicao) {
        return new PalavraHorizontal(texto, posicao);
    }

    @Override
    public boolean encaixa(MatrizLetras matrizLetras) {
        if (cabe(matrizLetras)){
            return false;
        }

        int y, i;

        for (y = posicao.y, i = 0; y < posicao.y + texto.length(); y++, i++){
            if (letrasNaoCabem(matrizLetras, y, i)){
                return false;
            }
        }

        return true;
    }

    private boolean letrasNaoCabem(MatrizLetras matrizLetras, int y, int i) {
        char letra = texto.charAt(i);
        boolean b = matrizLetras.lerLetra(posicao.x, y) != letra;

        for(int j = posicao.x - 1; j <= posicao.x + 1; j++){
            if (b && matrizLetras.temLetra(j, y)){
                return true;
            }
        }
        return false;
    }

    private boolean cabe(MatrizLetras matrizLetras) {
        return matrizLetras.temLetra(posicao.x, posicao.y - 1) ||
               matrizLetras.temLetra(posicao.x, posicao.y + texto.length());
    }
}
