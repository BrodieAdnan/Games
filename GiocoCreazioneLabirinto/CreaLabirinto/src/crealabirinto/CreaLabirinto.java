package crealabirinto;

import java.awt.Color;
import java.io.*;
import java.util.Scanner;
import static zuclib.GraficaSemplice.*;
import static zuclib.Varie.ritardo;

/**
 * @author Adnan Gazi Latif & Riccardo Fusaro
 * @date 10/01/2017
 * @brief Permettere all'utente di creare un labirinto e copiarlo su file di
 * testo.
 */
public class CreaLabirinto {

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.println("Dimmi la dimensione del labirinto (fortemente consigliato 19x19)");
        int dimensione = input.nextInt();
        boolean[][] matrice = new boolean[dimensione][dimensione];
        matrice = MatriceVuoto(dimensione);
        matrice = DisegnoVuoto(matrice, dimensione);
        matrice = ModificaDisegno(matrice);
        matrice = StampaPercorso(matrice);
        tasti(matrice);
    }

    public static boolean[][] MatriceVuoto(int lunghezza) {
        boolean colore = false;
        boolean[][] matriceVuoto = new boolean[lunghezza][lunghezza];
        for (int i = 0; i < lunghezza; i++) {
            for (int j = 0; j < lunghezza; j++) {
                matriceVuoto[i][j] = colore;
            }
        }
        return matriceVuoto;
    }

    public static boolean[][] DisegnoVuoto(boolean[][] matriceDisegno, int lung) {
        testo(0.5, 0.5, "Ora modifica il labirinto, alla fine premi FRECCIA DESTRA", 0, Color.BLACK);
        ritardo(3000);
        quadratoPieno(0.5, 0.5, 5, Color.WHITE);
        double x = 0.05;
        double y = 0.95;
        double l = 0.045;
        for (int r = 0; r < lung; r++) {
            for (int c = 0; c < lung; c++) {
                if (matriceDisegno[r][c] == false) {
                    quadratoPieno(x, y, l, Color.BLACK);
                }
                x += 0.05;
            }
            x = 0.05;
            y = y - 0.05;
        }
        return matriceDisegno;
    }

    public static boolean[][] ModificaDisegno(boolean[][] matricePieno) {
        double x = 0.05;
        double y = 0.95;
        double lato = 0.045;
        double mousex;
        double mousey;
        for (; !premutoTasto(FRECCIA_DX);) {
            if (mousePremuto()) {
                mousex = mouseX();
                mousey = mouseY();
                if (mousex >= 0.025 && mousey <= 0.975 && mousex <= 0.975 && mousey >= 0.025) {
                    while (x + 0.025 < mousex) {
                        x += 0.05;
                    }
                    while (y - 0.025 > mousey) {
                        y -= 0.05;
                    }
                    matricePieno = ModificaMatrice(matricePieno, x + 0.025, y - 0.025);
                    quadratoPieno(x, y, lato, Color.WHITE);
                }
            }
            x = 0.05;
            y = 0.95;
        }
        return matricePieno;
    }

    public static boolean[][] ModificaMatrice(boolean[][] matriceModifica, double x, double y) {
        int matriceColonne = (int) (x / 0.05);
        int matriceRighe = (int) (y / 0.05);
        matriceRighe = 18 - matriceRighe;
        matriceColonne -= 1;
        matriceModifica[matriceRighe][matriceColonne] = true;
        return matriceModifica;
    }

    public static boolean[][] StampaPercorso(boolean[][] matricePieno) throws IOException {
        BufferedWriter t = new BufferedWriter(new FileWriter("Percorso.txt"));
        for (int righe = 0; righe < matricePieno.length; righe++) {
            for (int colonne = 0; colonne < matricePieno[righe].length; colonne++) {
                if (matricePieno[righe][colonne] == true) {
                    t.write("0 ");
                } else {
                    t.write("1 ");
                }
            }
            t.newLine();
        }
        t.close();
        return matricePieno;
    }

    public static void tasti(boolean matri[][]) { // funzione che muove la figura nel labirinto.
        boolean bool = true;
        double lato = 0.05;
        double x = 0.1;
        double y = 0.1; // posizione di partenza della figura.
        int i = 1;
        int j = 17; // posizione di riferimento nella matrice.
        int contamosse = 0;

        quadratoPieno(0.95, 0.90, lato, Color.ORANGE); // posizione finale del labirinto.
        while (bool) {
            if (i == 18 && j == 1) { // quando la figura arriva alla posizione finale il labirinto si blocca.
                String cont = "";
                cont = " Hai vinto con " + contamosse + " mosse!";
                quadratoPieno(0.5, 0.5, 5, Color.WHITE);
                quadratoPieno(x, y, lato, Color.WHITE); // la figura diventa bianca.
                testo(0.5, 0.5, cont, 0, Color.BLACK);
                ritardo(5000);
                quadratoPieno(0.5, 0.5, 5, Color.WHITE);
                bool = !bool;
            } else {
                quadratoPieno(x, y, lato, Color.GREEN); // se non è nella posizione finale indica la posizione attuale con un quadrato rosso.
            }
            if (premutoTasto(FRECCIA_SU)) { // legge se premuto il tasto su.
                ritardo(150); // aspetta 125 millisec in modo che al prossimo loop il tasto non sia ancora premuto.
                if (matri[j - 1][i] == true) {
                    j -= 1; // cambia il valore di riferimento della matrice.
                    quadratoPieno(x, y, lato, Color.WHITE); // la posizione precedente ritona del colore di partenza.
                    contamosse++;
                    y += 0.05; // muove la coordinata facendola coorispondere alla posizione della matrice.
                }

            } else if (premutoTasto(FRECCIA_GIU)) { // legge se premuto il tasto giù.
                ritardo(150);
                if (matri[j + 1][i] == true) {
                    j += 1;
                    quadratoPieno(x, y, lato, Color.WHITE);
                    y -= 0.05;
                    contamosse++;
                }

            } else if (premutoTasto(FRECCIA_DX)) { // legge se premuto il tasto destra.
                ritardo(150);
                if (matri[j][i + 1] == true) {
                    i += 1;
                    quadratoPieno(x, y, lato, Color.WHITE);
                    x += 0.05;
                    contamosse++;
                }

            } else if (premutoTasto(FRECCIA_SX)) { // legge se premuto il tasto sinistra.
                ritardo(150);
                if (matri[j][i - 1] == true) {
                    i -= 1;
                    quadratoPieno(x, y, lato, Color.WHITE);
                    x -= 0.05;
                    contamosse++;
                }
            }
        }
    }
}
