package gioco;

/**
 * **********************************************************
 * DADO
 *
 * @author Gazi Adnan Latif
 * @brief oggetto dado: simula il lancio casuale di una dado.
 * @date 11/04/2017 ************************************************************
 */
public class Dado {

    private int esito;
    private Random r;
    private int inf;
    private int sup;

    public Dado(int inf, int sup) {
        this.inf = inf;
        this.sup = sup;
        r = new Random(inf, sup);
        esito = 1;
    }

    public int getEsito() {
        return esito;
    }

    public void lancio() {
        esito = r.next();
    }
}