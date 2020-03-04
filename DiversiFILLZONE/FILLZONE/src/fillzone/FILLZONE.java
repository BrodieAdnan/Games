package fillzone;

import java.awt.Color;
import java.util.*;
import static zuclib.GraficaSemplice.*;

/**
 * *************************************************
 * FILLZONE
 *
 * @author Adnan Gazi Latif & Riccardo Fusaro
 * @data inizio 24/01/2017
 * @data fine 31/01/2017
 * @brief permette all'utente di giocare a FILLZONE.
 * @version 4. **************************************************
 */
public class FILLZONE {

    static int dimensione = 20;
    static int[][] matrice = new int[dimensione][dimensione];
    static Color colori[] = {VERDE, ROSSO, BIANCO, BLU}; //array che contiene i colori.
    static double lato = (0.80) / dimensione; //adattamento a schermo.
    static double x = 0.10;
    static double y = 0.80;
    static int scelto; //colore premuto.

    public static void main(String[] args) { //attiva solo le funzioni.
        quadratoPieno(0, 0, 5, CIANO); //sfondo celeste.
        riempiMatrice();
        disegnaMatrice();
        sceltaColori();
        colorePreso(30); //vengono passate il numero delle mosse.
    }

    public static void riempiMatrice() { //riempimento con nuemeri casuali tra 0 e 3.
        for (int r = 0; r < dimensione; r++) {
            for (int c = 0; c < dimensione; c++) {
                matrice[r][c] = (int) (Math.random() * (colori.length));
            }
        }
    }

    public static void disegnaMatrice() { //disegno sulla finestra.
        for (int r = 0; r < dimensione; r++) {
            for (int c = 0; c < dimensione; c++) {
                quadratoPieno(x, y, lato, colori[matrice[r][c]]);
                quadrato(x, y, lato);
                x += lato;
            }
            x = 0.10;
            y -= lato;
        }
        y = 0.80; //ritorna alla posizione di partenza.
    }

    public static void sceltaColori() { //tabella 'scelta dei colori'.
        double sceltaX = 0.125;
        double sceltaY = 0.92;
        rettangolo(sceltaX + 0.075, 0.92, 0.25, 0.10);
        for (int r = 0; r < colori.length; r++) {
            quadratoPieno(sceltaX, sceltaY, 0.05, colori[r]);
            quadrato(sceltaX, sceltaY, 0.05);
            sceltaX += 0.05;
        }
    }

    public static void colorePreso(int mosse) {
        double mousex;
        double mousey;
        int colonne;
        int righe;
        while (mosse >= 0 && !completato()) { //fino a quando ci sono mosse disponibili e non è ancora finito il gioco.
            if (mousePremuto()) {
                while (mousePremuto());
                mousex = mouseX();
                mousey = mouseY();
                colonne = 0;
                righe = 0;
                if (mousex >= 0.100 && mousex <= 0.150 && mousey >= 0.895 && mousey <= 0.945) { //prima casella : verde.
                    scelto = 0; //colore scelto.
                    mosse--;
                    ricorsione(righe, colonne, matrice[0][0], scelto);
                } else if (mousex >= 0.150 && mousex <= 0.200 && mousey >= 0.895 && mousey <= 0.945) { //seconda casella : rosso.
                    scelto = 1;
                    mosse--;
                    ricorsione(righe, colonne, matrice[0][0], scelto);
                } else if (mousex >= 0.200 && mousex <= 0.250 && mousey >= 0.895 && mousey <= 0.945) { //terza casella : bianco.
                    scelto = 2;
                    mosse--;
                    ricorsione(righe, colonne, matrice[0][0], scelto);
                } else if (mousex >= 0.250 && mousex <= 0.300 && mousey >= 0.895 && mousey <= 0.945) { //quarta casella : blu.
                    scelto = 3;
                    mosse--;
                    ricorsione(righe, colonne, matrice[0][0], scelto); //si attiva la ricorsione solo quando si preme su una casella di scelta.
                }
                quadratoPieno(0.75, 0.975, 0.225, CIANO); //sfondo che copre la scritta precedente.
                String s = "Hai ancora : " + mosse;
                testo(0.75, 0.9, s); //scritta del conto alla rovescia.
                if (mosse == 0 || completato()) { //quando si arriva alla fine del gioco.
                    fineGioco(mosse, completato());
                }
            }
        }
    }

    public static boolean completato() { //verifica che la matrice non sia completa.
        boolean completata = true;
        int colore = matrice[0][0];
        for (int r = dimensione - 1; r >= 0; r--) { //scorre dal fondo per trovare subito se falso.
            for (int c = dimensione - 1; c >= 0; c--) {
                if (matrice[r][c] != colore) {
                    completata = false;
                    break; //termina appena una casella è diversa dalla casella iniziale.
                }
            }
        }
        return completata;
    }

    public static double getCentroX(int colonna) { //converte le colonne in coordinata x.
        return ((colonna * lato) + 0.1);
    }

    public static double getCentroY(int riga) { //converte le colonne in coordinata y.
        return (0.88 - (riga + 2) * lato);
    }

    public static void disegnaQuadrato(double x, double y, double lato) { //colora solo le caselle che devono cambiare.
        quadratoPieno(x, y, lato, colori[scelto]);
        quadrato(x, y, lato);
    }

    public static void ricorsione(int riga, int colonna, int coloreIniziale, int scelto) {

        try { //senza try & catch, va in Index.Out.Of.Bound
            if (matrice[riga][colonna] == coloreIniziale) {
                matrice[riga][colonna] = scelto;
                disegnaQuadrato(getCentroX(colonna), getCentroY(riga), lato); //cambia colori.
                ricorsione(riga - 1, colonna, coloreIniziale, scelto); //analizza la cella in alto.
                ricorsione(riga + 1, colonna, coloreIniziale, scelto);//analizza la cella in basso.
                ricorsione(riga, colonna - 1, coloreIniziale, scelto);//analizza la cella a sinistra.
                ricorsione(riga, colonna + 1, coloreIniziale, scelto);//analizza la cella a destra.
            }
        } catch (Exception e) {

        }
    }

    public static void fineGioco(int mosse, boolean fine) { //dice se si vince o si perde.
        quadratoPieno(0, 0, 5, BIANCO);
        if (mosse == 0) {
            testo(0.5, 0.5, "HAI PERSO");
        } else {
            testo(0.5, 0.5, "HAI VINTO CON : " + (30 - mosse) + " mosse");
        }
    }
}
