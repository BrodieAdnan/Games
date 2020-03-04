package gomokuprova;

import java.awt.Color;

/**
 * Punto.
 *
 * @author Adnan Gazi Latif.
 * @brief crea l'oggeto punto che racchiude le variabili e i metodi di
 * conversione.
 */
public class Oggetti {

    /**
     * CONTENITORE DI VARIABILI.
     */
    int dim = 14;
    int[][] tavoliere = new int[dim][dim];
    double x = 0.1;
    double y = 0.9;
    double lato = (y - x) / dim;
    int[] coordinateRC = new int[2];//pos 0 = y; pos 1 = x;
    double[] coordinateXY = new double[2];//pos 0 = x; pos 1 = y;
    Color coloreDado;
    String nomeGiocatore1;
    String nomeGiocatore2;
    int[] pedine = new int[]{98, 98};
    int[] vittorie = new int[]{0, 0};
    boolean colore = true;
    int nPartite;

    /**
     * ConversioneXYRC.
     *
     * @author Adnan Gazi Latif.
     * @brief crea l'oggeto conversioneXYRC che converte le coordinate della
     * finestra in coordinate della matrice acquisite il punto cliccato nel
     * costtruttore.
     */
    public void converti() {
        coordinateRC[1] = (int) ((coordinateXY[0] - 0.1) / lato);
        coordinateRC[0] = (dim-1) - (int) ((coordinateXY[1] - 0.1) / lato);
    }

    /**
     * RiConversione.
     *
     * @author Adnan Gazi Latif.
     * @brief crea l'oggeto che ri converte le coordinate della matrice in punti
     * x e y della finestra acquisite le coordinate del tavoliere nel
     * costruttore.
     */
    public void riConverti() {
        coordinateXY[0] = lato * coordinateRC[1] + 0.1;
        coordinateXY[1] = lato * (dim - coordinateRC[0]) + 0.1;
    }
}
