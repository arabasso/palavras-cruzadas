package sk.host.arabasso;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by arabasso on 29/08/15.
 */
public class PalavrasCruzadas {
    public static void main(String ... args){
        try{
            InputStream inputStream = (args.length == 1)
                    ? new FileInputStream(args[0])
                    : PalavrasCruzadas.class.getResourceAsStream("/Palavras.txt");

            List<String> palavras = carregarArquivo(inputStream);
            MatrizLetras matriz = new MatrizEstaticaLetras(100, 100);

            for (String palavra : palavras){
                matriz.escreverPalavra(palavra);
            }

            matriz.salvar(new BufferedWriter(new OutputStreamWriter(System.out)));
        }

        catch(Exception ex){
            System.out.println("Exceção: " + ex);
        }
    }

    public static List<String> carregarArquivo(InputStream stream) {
        InputStreamReader in = new InputStreamReader(stream, StandardCharsets.UTF_8);

        BufferedReader bufferedReader = new BufferedReader(in);

        return bufferedReader.lines().collect(Collectors.toList());
    }
}
