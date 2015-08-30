package sk.host.arabasso;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by arabasso on 29/08/15.
 */
public class MatrizEstaticaLetras implements MatrizLetras {
    private char [][] matriz;
    private List<Palavra> palavras = new ArrayList<>();

    public MatrizEstaticaLetras(int linhas, int colunas) {
        matriz = new char[linhas][colunas];

        inicializar();
    }

    private void inicializar() {
        for(int i = 0; i < matriz.length; i++){
            for(int j = 0; j < matriz[i].length; j++){
                matriz[i][j] = ' ';
            }
        }
    }

    @Override
    public int getLinhas() {
        return matriz.length;
    }

    @Override
    public int getColunas() {
        return matriz[0].length;
    }

    @Override
    public void escreverLetra(int x, int y, char letra){
        matriz[y][x] = letra;
    }

    @Override
    public void escreverPalavra(String texto) {
        if (naoHaPalavras()){
            escrevaPrimeiraPalavra(texto);
        }

        else{
            cruzarPalavras(texto);
        }
    }

    private boolean naoHaPalavras() {
        return palavras.isEmpty();
    }

    private void cruzarPalavras(String texto) {
        for (Palavra palavra : palavras){
            if (palavraFoiCruzada(texto, palavra)){
                break;
            }
        }
    }

    private boolean palavraFoiCruzada(String texto, Palavra palavra) {
        for (Posicao posicao : palavra.posicoesLetrasEmComum(texto)){
            Palavra p = palavra.cruzar(texto, posicao);

            if (p.encaixa(this)){
                palavras.add(p);

                p.escrever(this);

                return true;
            }
        }
        return false;
    }

    private void escrevaPrimeiraPalavra(String texto) {
        Posicao posicao = posicaoCentral(texto);

        Palavra palavra = new PalavraHorizontal(texto, posicao);

        palavras.add(palavra);

        palavra.escrever(this);
    }

    private Posicao posicaoCentral(String palavra) {
        int x = ((getColunas() - 1) - palavra.length()) / 2;
        int y = (getLinhas() - 1) / 2;

        return new Posicao(x, y);
    }

    @Override
    public void salvar(BufferedWriter outputWriter) throws IOException {
        Posicao cantoSuperiorEsquerdo = cantoSuperiorEsquerdo();
        Posicao cantoInferiorDireito = cantoInferiorDireito();

        for (int i = cantoSuperiorEsquerdo.y; i <= cantoInferiorDireito.y; i++) {
            for (int j = cantoSuperiorEsquerdo.x; j <= cantoInferiorDireito.x; j++) {
                outputWriter.write(matriz[i][j]);
            }

            outputWriter.newLine();
        }

        outputWriter.flush();
    }

    @Override
    public void salvar(String s) throws IOException {
        BufferedWriter outputWriter = new BufferedWriter(new FileWriter(s));

        salvar(outputWriter);

        outputWriter.close();
    }

    private Posicao cantoSuperiorEsquerdo() {
        boolean feito;

        feito = false;
        int y;

        for(y = 0; !feito && y < matriz.length; y++){
            for(int i = 0; i < matriz[y].length; i++) {
                if (matriz[y][i] != ' '){
                    feito = true;
                    y--;

                    break;
                }
            }
        }

        feito = false;
        int x;

        for(x = 0; !feito && x < matriz.length; x++){
            for(int i = 0; i < matriz[0].length; i++) {
                if (matriz[i][x] != ' '){
                    feito = true;
                    x--;

                    break;
                }
            }
        }

        return new Posicao(x, y);
    }

    private Posicao cantoInferiorDireito() {
        boolean feito;

        feito = false;
        int y1;

        for(y1 = matriz.length - 1; !feito && y1 >= 0; y1--){
            for(int i = matriz[y1].length - 1; i >= 0; i--) {
                if (matriz[y1][i] != ' '){
                    feito = true;
                    y1++;

                    break;
                }
            }
        }

        feito = false;
        int x1;

        for(x1 = matriz.length - 1; !feito && x1 >= 0; x1--){
            for(int i = matriz[0].length - 1; i >= 0; i--) {
                if (matriz[i][x1] != ' '){
                    feito = true;
                    x1++;

                    break;
                }
            }
        }

        return new Posicao(x1, y1);
    }

    @Override
    public char lerLetra(int x, int y) {
        return matriz[y][x];
    }

    @Override
    public boolean temLetra(int x, int y) {
        return matriz[y][x] != ' ';
    }
}
