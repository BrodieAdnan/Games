package fillzonegioco;

import java.awt.Color;
import static zuclib.GraficaSemplice.*;

/**
 * *************************************************
 * FILLZONE
 *
 * @author Adnan Gazi Latif & Riccardo Fusaro
 * @data inizio 24/01/2017
 * @data fine 31/01/2017
 * @brief permette all'utente di giocare a FILLZONE.
 * @version 2. **************************************
 */
public class FillZoneGioco {

    static int[][] matrice = new int[16][16]; //creato la matrice nell'ambiente globale.
    static double x;
    static double y;
    static double lato = 0.05; //lato definito nell'ambiente globale perchè viene utillizzato da più metodi.
    static int scelto; //colore cliccato dal mouse sulla finestra.
    static Color colori[] = {VERDE, ROSSO, BIANCO, BLU};

    public static void main(String[] args) { //da qui vengono solo attivati i metodi.
        quadratoPieno(0, 0, 5, CIANO); //sfondo celeste.
        riempiMatrice();
        disegnaMatrice();
        sceltaColori();
        colorePreso(50);
    }

    public static void riempiMatrice() { //riempie la matrice in ordine casuale da 0 a 3.
        int random;
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                random = (int) (Math.random() * 4);
                matrice[i][j] = random;
            }
        }
    }

    public static void disegnaMatrice() { //disegna sulla finestra i quadrati colorati in base alla matrice.
        x = 0.10;
        y = 0.80;
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                quadratoPieno(x, y, lato, colori[matrice[i][j]]);
                quadrato(x, y, lato); //bordo esterno.
                x += lato;
            }
            x = 0.10;
            y -= lato;
        }
    }

    public static void sceltaColori() { //tabella di scelta dei colori.
        x += 0.025; //x=0.125;
        y = 0.92;
        rettangolo(x + 0.075, 0.92, 0.25, 0.10); //rettangolo decorativo esterno.
        for (int i = 0; i < 4; i++) { //disegna i 4 colori che possono essere scelti.
            quadratoPieno(x, y, lato, colori[i]);
            quadrato(x, y, lato);
            x += 0.05;
        }
    }

    public static void colorePreso(int mosse) {
        double mousex;
        double mousey;
        int colonne;
        int righe;
        int mosseRimanenti = mosse;
        while (mosseRimanenti >= 0 && !completato()) {
            if (mousePremuto()) {
                while (mousePremuto());
                mousex = mouseX();
                mousey = mouseY();
                colonne = 0;
                righe = 0;
                if (mousex >= 0.100 && mousex <= 0.150 && mousey >= 0.895 && mousey <= 0.945) {
                    scelto = 0;
                } else if (mousex >= 0.150 && mousex <= 0.200 && mousey >= 0.895 && mousey <= 0.945) {
                    scelto = 1;
                } else if (mousex >= 0.200 && mousex <= 0.250 && mousey >= 0.895 && mousey <= 0.945) {
                    scelto = 2;
                } else if (mousex >= 0.250 && mousex <= 0.300 && mousey >= 0.895 && mousey <= 0.945) {
                    scelto = 3;
                }
                System.out.println(scelto);
                ricorsione(righe, colonne, matrice[0][0], scelto);
                disegnaMatrice();
            }
        }
    }

    public static boolean completato() {
        boolean completata = true;
        int colore = matrice[0][0];
        for (int r = matrice.length - 1; r >= 0; r--) {
            for (int c = matrice.length - 1; c >= 0; c--) {
                if (matrice[r][c] != colore) {
                    completata = false;
                    break;
                }
            }
        }
        return completata;
    }

    /*public static double getCentroX(int colonna){
	 return 
	 }
	
	 public static double getCentroY(int colonna){
	 return 
	 }*/
    public static void ricorsione(int riga, int colonna, int coloreIniziale, int scelto) {

        try {
            if (matrice[riga][colonna] == coloreIniziale) {
                matrice[riga][colonna] = scelto;
                //disegnaQuadrato(getCentroX(colonna), getCentroY(riga), lato );
                ricorsione(riga - 1, colonna, coloreIniziale, scelto);
                ricorsione(riga + 1, colonna, coloreIniziale, scelto);
                ricorsione(riga, colonna - 1, coloreIniziale, scelto);
                ricorsione(riga, colonna + 1, coloreIniziale, scelto);
            }
        } catch (Exception e) {

        }
    }
}
