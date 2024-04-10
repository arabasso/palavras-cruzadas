import org.junit.Before;
import org.junit.Test;
import sk.host.arabasso.*;

import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by arabasso on 28/08/15.
 */

// JAVA 

public class PalavrasCruzadasTests {
    public static final String PALAVRA_COMPUTADOR = "COMPUTADOR";
    public static final String PALAVRA_PROCESSADOR = "PROCESSADOR";
    private String [] listaPalavras = new String[]{
        PALAVRA_COMPUTADOR, PALAVRA_PROCESSADOR, "MONITOR", "IMPRESSORA",
        "TECLADO", "CONTROLE", "MEMORIA"
    };

    private List<String> palavras;
    private MatrizLetras matriz;
    private Posicao posicaoHorizontal;
    private PalavraHorizontal palavraComputadorHorizontal;
    private PalavraVertical palavraComputadorVertical;

    @Before
    public void setUp() throws Exception {
        InputStream inputStream = PalavrasCruzadas.class.getResourceAsStream("/Palavras.txt");

        palavras = PalavrasCruzadas.carregarArquivo(inputStream);
        matriz = new MatrizEstaticaLetras(100, 100);
        posicaoHorizontal = new Posicao(50, 50);
        palavraComputadorHorizontal = new PalavraHorizontal(PALAVRA_COMPUTADOR, posicaoHorizontal);
        palavraComputadorVertical = new PalavraVertical(PALAVRA_COMPUTADOR, posicaoHorizontal);
    }

    @Test
    public void criarMatriz100Por100() throws Exception {
        assertEquals(100, matriz.getLinhas());
        assertEquals(100, matriz.getColunas());
    }

    @Test
    public void palavraEncaixaColunaMatriz() throws Exception {
        assertTrue(palavraComputadorHorizontal.encaixa(matriz));
    }

    @Test
    public void lerLetraMatriz() throws Exception {
        assertEquals(' ', matriz.lerLetra(50, 50));
    }

    @Test
    public void lerEscreverLetraMatriz() throws Exception {
        matriz.escreverLetra(50, 50, 'A');

        assertEquals('A', matriz.lerLetra(50, 50));
    }

    @Test
    public void naoTemLetraPosicaoMatriz() throws Exception {
        assertFalse(matriz.temLetra(50, 50));
    }

    @Test
    public void temLetraPosicaoMatriz() throws Exception {
        matriz.escreverLetra(50, 50, 'A');

        assertTrue(matriz.temLetra(50, 50));
    }

    @Test
    public void palavraNaoEncaixaColunaMatriz() throws Exception {
        matriz.escreverLetra(50, 50, 'A');

        assertFalse(palavraComputadorHorizontal.encaixa(matriz));
    }

    @Test
    public void palavraEncaixaLinhaMatriz() throws Exception {
        assertTrue(palavraComputadorHorizontal.encaixa(matriz));
    }

    @Test
    public void palavraNaoEncaixaLinhaMatriz() throws Exception {
        matriz.escreverLetra(50, 50, 'A');

        assertFalse(palavraComputadorHorizontal.encaixa(matriz));
    }

    @Test
    public void palavraNaoTemLetrasEmComum() throws Exception {
        assertEquals(0, palavraComputadorHorizontal.posicoesLetrasEmComum("BZWYK").size());
    }

    @Test
    public void palavraTemLetrasEmComumHorizontal() throws Exception {
        List<Posicao> posicoes = palavraComputadorHorizontal.posicoesLetrasEmComum(PALAVRA_PROCESSADOR);

        assertArrayEquals(new Posicao[]{
                new Posicao(50, 47),
                new Posicao(51, 48),
                new Posicao(51, 41),
                new Posicao(53, 50),
                new Posicao(56, 43),
                new Posicao(57, 42),
                new Posicao(58, 48),
                new Posicao(58, 41),
                new Posicao(59, 49),
                new Posicao(59, 40)
        }, posicoes.toArray());
    }

    @Test
    public void palavraTemLetrasEmComumVertical() throws Exception {
        List<Posicao> posicoes = palavraComputadorVertical.posicoesLetrasEmComum(PALAVRA_PROCESSADOR);

        assertArrayEquals(new Posicao[]{
                new Posicao(47, 50),
                new Posicao(48, 51),
                new Posicao(41, 51),
                new Posicao(50, 53),
                new Posicao(43, 56),
                new Posicao(42, 57),
                new Posicao(48, 58),
                new Posicao(41, 58),
                new Posicao(49, 59),
                new Posicao(40, 59)
        }, posicoes.toArray());
    }
}
