package sk.host.arabasso;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Created by arabasso on 29/08/15.
 */
public interface MatrizLetras {
    int getLinhas();

    int getColunas();

    void escreverLetra(int x, int y, char letra);

    void escreverPalavra(String texto);

    void salvar(BufferedWriter outputWriter) throws IOException;

    void salvar(String s) throws IOException;

    char lerLetra(int x, int y);

    boolean temLetra(int x, int y);
}
