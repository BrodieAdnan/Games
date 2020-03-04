package gioco;

/**
 * **************************************************************
 * RANDOM
 *
 * @author Gazi Adnan Latif
 * @brief oggetto random: genera casualmente una faccia del dado.
 * @date 11/04/2017
 * ****************************************************************
 */
public class Random {

    private int inf;
    private int sup;

    public Random(int inf, int sup) {
        this.inf = inf;
        this.sup = sup;
    }

    public int next() {
        return (int) Math.floor(Math.random() * (sup - inf) + 1);
    }
}