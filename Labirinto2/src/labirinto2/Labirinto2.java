/**
 * @ Disegna un labirinto e muove una figura al suo interno.
 * @ Author Gazi Adnan Latif
 * @ Date 4/1/2017
 * @ Param La figura si muove solo nelle caselle bianche.
 */
package labirinto2;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static zuclib.GraficaSemplice.*;
import static zuclib.Varie.ritardo;

public class Labirinto2 {

    public static void main(String[] args) {
        boolean[][] matrice = new boolean[19][19];
        matrice = lab(); // richiamo la funzione 'lab' e assegno i valori booleani alla matrice.
        disegno(matrice); //richiamo la funzione 'disegno'.
        tasti(matrice); //richiamo la funzione 'tasti'.
    }

    public static boolean[][] lab() { // funzione che riempie la matrice con i valori del file.
        Scanner input = null;
        try {
            input = new Scanner(new File("percorso.txt")); // imposto lo Scanner al file di testo 'Percorso' contenente i valori del labirinto.
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Labirinto2.class.getName()).log(Level.SEVERE, null, ex);
        }
        boolean mat[][] = new boolean[19][19]; // matrice da riempire.
        String st = "";
        for (int r = 0; r < mat.length; r++) { // ciclo che scorre le colonne.
            st = input.nextLine(); // nella stringa 'st' vengono riportati tutti i valori del file del labirinto.
            for (int c = 0; c < mat[r].length; c++) { // ciclo che scorre le righe.
                mat[r][c] = st.charAt(c) == '1'; // a ciascuna posizione della matrice viene assegnata il suo valore.
            }
        }
        return mat; // riporto la matrice riempita nel 'main'.
    }

    public static void disegno(boolean matrix[][]) { // funzione che crea il disegno del labirinto.
        double x = 0.05;
        double y = 0.95;
        double lato = 0.045;
        for (int i = 0; i < 19; i++) { // cilco che scorre le colonne.
            for (int j = 0; j < 19; j++) { // cilco che scorre le righe.
                if (matrix[i][j] == true) { // disegna un quadrato nero per ogni posizione 'falsa' e bianco per ogni posizione 'vera' nella matrice.
                    quadratoPieno(x, y, lato, Color.BLACK);
                }
                x += 0.05;
            }
            x = 0.05;
            y = y - 0.05;
        }
    }

    public static void tasti(boolean matri[][]) { // funzione che muove la figura nel labirinto.
        double lato = 0.05;
        double x = 0.1;
        double y = 0.1; // posizione di partenza della figura.
        int i = 1;
        int j = 17; // posizione di riferimento nella matrice.
        int cont = 0;
        quadratoPieno(0.95, 0.90, lato, Color.ORANGE); // posizione finale del labirinto.
        while (true) {
            if (i == 18 && j == 1) { // quando la figura arriva alla posizione finale il labirinto si blocca.
                String contmosse = "";
                contmosse = " Hai vinto con " + cont + " mosse!";
                quadratoPieno(0.5, 0.5, 5, Color.WHITE);
                testo(0.5, 0.5, contmosse, 0, Color.BLACK);
                ritardo(5000);
                quadratoPieno(0.5, 0.5, 5, Color.WHITE);
            } else {
                quadratoPieno(x, y, lato, ROSSO); // se non è nella posizione finale indica la posizione attuale con un quadrato rosso.
            }
            if (premutoTasto(FRECCIA_SU)) { // legge se premuto il tasto su.
                ritardo(150); // aspetta 125 millisec in modo che al prossimo loop il tasto non sia ancora premuto.
                if (matri[j - 1][i] == false) {
                    j -= 1; // cambia il valore di riferimento della matrice.
                    quadratoPieno(x, y, lato, Color.GRAY); // la posizione precedente ritona del colore di partenza.
                    y += 0.05; // muove la coordinata facendola coorispondere alla posizione della matrice.
                    cont++;
                }

            } else if (premutoTasto(FRECCIA_GIU)) { // legge se premuto il tasto giù.
                ritardo(150);
                if (matri[j + 1][i] == false) {
                    j += 1;
                    quadratoPieno(x, y, lato, Color.GRAY);
                    y -= 0.05;
                    cont++;
                }

            } else if (premutoTasto(FRECCIA_DX)) { // legge se premuto il tasto destra.
                ritardo(150);
                if (matri[j][i + 1] == false) {
                    i += 1;
                    quadratoPieno(x, y, lato, Color.GRAY);
                    x += 0.05;
                    cont++;
                }

            } else if (premutoTasto(FRECCIA_SX)) { // legge se premuto il tasto sinistra.
                ritardo(150);
                if (matri[j][i - 1] == false) {
                    i -= 1;
                    quadratoPieno(x, y, lato, Color.GRAY);
                    x -= 0.05;
                    cont++;
                }
            }
        }
    }
}
