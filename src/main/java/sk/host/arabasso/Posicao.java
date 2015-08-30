package sk.host.arabasso;

/**
 * Created by arabasso on 29/08/15.
 */
public final class Posicao {
    public final int x;
    public final int y;

    public Posicao(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Posicao posicao = (Posicao) o;

        if (x != posicao.x) return false;
        return y == posicao.y;

    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
