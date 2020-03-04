package gomokuprova;

import java.util.*;
import static zuclib.GraficaSemplice.*;
import static zuclib.Varie.ritardo;

/**
 * GOMOKU.
 *
 * @author Adnan Gazi Latif.
 * @data inizio 27/02/2017
 * @data fine 06/03/2017
 * @brief permette di giocare a Gomoku con due giocatori a turni alternati.
 */
public class GomokuProva {

    static Scanner input = new Scanner(System.in);//usata per inserire i nomi.
    static Oggetti p = new Oggetti(); //oggetto della classe Punto.

    public static void main(String[] args) {//gestisce tutti i metodi.
        nomiGiocatori();
        inizio();
        while (p.vittorie[0] < p.nPartite && p.vittorie[1] < p.nPartite) {
            riempiTavoliere();//riempita di zeri.
            disegna();
            lineeVerticali();//griglie decorative verticali.
            lineeOrizzontali();//griglie decorative orizzontali.
            caselleTurni();
            premiMouse();//inizio del gioco.
            sistema();//riporta alcune variabili ai valori di partenza.
        }
        finale();
    }

    public static void nomiGiocatori() {
        System.out.print("Inscerisci il nome del giocatore numero 1 : ");
        p.nomeGiocatore1 = input.next();
        System.out.print("Inscerisci il nome del giocatore numero 2 : ");
        p.nomeGiocatore2 = input.next();
        System.out.print("A qunate vittorie si arriva? ");
        p.nPartite = input.nextInt();
    }

    public static void riempiTavoliere() {
        for (int r = 0; r < p.dim; r++) {
            for (int c = 0; c < p.dim; c++) {
                p.tavoliere[r][c] = 0;
            }
        }
    }

    public static void disegna() {
        quadratoPieno(0.5, 0.5, 5, BLU);//sfondo.
        for (int r = 0; r < p.dim; r++) {
            for (int c = 0; c < p.dim; c++) {
                quadratoPieno(p.x, p.y, p.lato, MARRONE_CHIARO);
                p.x += p.lato;
            }
            p.x = 0.1;
            p.y -= p.lato;
        }
        p.y = 0.9;//le coordinate ritornano ai punti iniziali.
    }

    public static void lineeVerticali() {
        for (int i = 0; i < p.dim; i++) {
            linea(p.x, 0.9 + p.lato / 2, p.x, 0.1 + p.lato / 2, MARRONE, 0.001);
            p.x += p.lato;
        }
    }

    public static void lineeOrizzontali() {
        for (int i = 0; i < p.dim; i++) {
            linea(0.1 - p.lato / 2, p.y, 0.9 - p.lato / 2, p.y, MARRONE, 0.001);
            p.y -= p.lato;
        }
    }

    public static void caselleTurni() {//caselle esterne che indicano il punteggio.

        rettangoloPieno(0.935, 0.75, 0.12, 0.35, BIANCO);//casella primo giocatore.
        rettangolo(0.935, 0.75, 0.12, 0.35);
        testo(0.935, 0.85, p.nomeGiocatore1);
        testo(0.935, 0.8, "" + p.pedine[0]);//numero pedine rimaste.
        testo(0.935, 0.75, "Pedine");
        testo(0.935, 0.7, "Rimanste");

        rettangoloPieno(0.935, 0.35, 0.12, 0.35, NERO);//casella secondo giocatore.
        rettangolo(0.935, 0.35, 0.12, 0.35);
        testo(0.935, 0.45, p.nomeGiocatore2, 0, BIANCO);
        testo(0.935, 0.4, "" + p.pedine[1], 0, BIANCO);//numero pedine rimaste.
        testo(0.935, 0.35, "Pedine", 0, BIANCO);
        testo(0.935, 0.3, "Rimanste", 0, BIANCO);

        rettangoloPieno(0.5, 0.075, 0.5, 0.1, NERO);//punteggio partite.
        testo(0.5, 0.075, p.nomeGiocatore1 + " " + p.vittorie[0] + " - " + p.vittorie[1] + " " + p.nomeGiocatore2, 0, BIANCO);
    }

    public static void sovrapponi() {//numero pedine dei due giocatori.
        rettangoloPieno(0.935, 0.8, 0.05, 0.05, BIANCO);
        testo(0.935, 0.8, "" + p.pedine[0]);
        rettangoloPieno(0.935, 0.4, 0.05, 0.05, NERO);
        testo(0.935, 0.4, "" + p.pedine[1], 0, BIANCO);
    }

    public static void premiMouse() {
        while (!(verificato())) {
            if (!mousePremuto()) {
                while (!mousePremuto());
                if (mousePremuto()) {
                    while (mousePremuto());
                    p.coordinateXY[0] = mouseX() + p.lato / 2;
                    p.coordinateXY[1] = mouseY() - p.lato / 2;
                    if (p.coordinateXY[0] >= 0.1 && p.coordinateXY[0] <= 0.9 && p.coordinateXY[1] >= 0.1 && p.coordinateXY[1] <= 0.9) {
                        p.converti();
                        modificaDisegno();
                        modificaTavoliere();
                    }
                }
            }
        }
        fine();
        out();
    }

    public static boolean verificato() {
        for (int r = 0; r < p.dim; r++) {
            for (int c = 0; c < p.dim; c++) {
                if (ricorsione(r, c)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean ricorsione(int r, int c) {
        int col = (p.coloreDado == BIANCO) ? 1 : 2;
        if (alto(r, c, col)) {
            return true;
        } else if (d1(r, c, col)) {
            return true;
        } else if (d2(r, c, col)) {
            return true;
        } else if (d3(r, c, col)) {
            return true;
        } else if (d4(r, c, col)) {
            return true;
        } else if (basso(r, c, col)) {
            return true;
        } else if (destra(r, c, col)) {
            return true;
        } else if (sinistra(r, c, col)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean d1(int r, int c, int col) {
        try {
            for (int i = 0; i < 5; i++) {
                if (p.tavoliere[r][c] != col) {
                    return false;
                }
                r--;
                c--;
            }
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    public static boolean d2(int r, int c, int col) {
        try {
            for (int i = 0; i < 5; i++) {
                if (p.tavoliere[r][c] != col) {
                    return false;
                }
                r++;
                c++;
            }
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    public static boolean d3(int r, int c, int col) {
        try {
            for (int i = 0; i < 5; i++) {
                if (p.tavoliere[r][c] != col) {
                    return false;
                }
                r++;
                c--;
            }
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    public static boolean d4(int r, int c, int col) {
        try {
            for (int i = 0; i < 5; i++) {
                if (p.tavoliere[r][c] != col) {
                    return false;
                }
                r--;
                c++;
            }
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    public static boolean alto(int r, int c, int col) {
        try {
            for (int i = 0; i < 5; i++) {
                if (p.tavoliere[r][c] != col) {
                    return false;
                }
                r--;
            }
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    public static boolean basso(int r, int c, int col) {
        try {
            for (int i = 0; i < 5; i++) {
                if (p.tavoliere[r][c] != col) {
                    return false;
                }
                r++;
            }
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    public static boolean sinistra(int r, int c, int col) {
        try {
            for (int i = 0; i < 5; i++) {
                if (p.tavoliere[r][c] != col) {
                    return false;
                }
                c--;
            }
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    public static boolean destra(int r, int c, int col) {
        try {
            for (int i = 0; i < 5; i++) {
                if (p.tavoliere[r][c] != col) {
                    return false;
                }
                c++;
            }
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    public static void modificaTavoliere() {
        if (p.coloreDado == BIANCO) {
            p.tavoliere[p.coordinateRC[0]][p.coordinateRC[1]] = 1;//pallino bianco.
        } else {
            p.tavoliere[p.coordinateRC[0]][p.coordinateRC[1]] = 2;//pallino nero.
        }
    }

    public static void modificaDisegno() {
        p.riConverti();
        if (p.colore) {
            p.coloreDado = BIANCO;
            p.pedine[0]--;
        } else {
            p.coloreDado = NERO;
            p.pedine[1]--;
        }
        sovrapponi();
        cerchioPieno(p.coordinateXY[0], p.coordinateXY[1], p.lato / 3, p.coloreDado);
        cerchio(p.coordinateXY[0], p.coordinateXY[1], p.lato / 3);
        p.colore = !p.colore;
    }

    public static void out() {
        for (int r = 0; r < p.dim; r++) {
            for (int c = 0; c < p.dim; c++) {
                System.out.print(p.tavoliere[r][c] + " ");
            }
            System.out.println("");
        }
    }

    public static void fine() {
        quadratoPieno(0.5, 0.5, 5, BIANCO);
        if (p.coloreDado == BIANCO) {
            testo(0.5, 0.5, "Vince " + p.nomeGiocatore1 + "!");
            ritardo(1000);
            p.vittorie[0]++;
        } else {
            testo(0.5, 0.5, "Vince " + p.nomeGiocatore2 + "!");
            ritardo(1000);
            p.vittorie[1]++;
        }
    }

    public static void sistema() {
        p.pedine[1] = 98;
        p.pedine[0] = 98;
        p.x = 0.1;
        p.y = 0.9;
    }

    public static void finale() {
        quadratoPieno(0.5, 0.5, 5, NERO);
        if (p.vittorie[0] == p.nPartite) {
            testo(0.5, 0.5, "IL VINCITORE E' : " + p.nomeGiocatore1, 0, BIANCO);
        } else {
            testo(0.5, 0.5, "IL VINCITORE E' : " + p.nomeGiocatore2, 0, BIANCO);
        }
        testo(0.5, 0.45, "CHE VINCE " + p.vittorie[0] + " - " + p.vittorie[1], 0, BIANCO);
    }

    public static void inizio() {
        setFinestra(600, 600, "GOMOKU");
        quadratoPieno(0.5, 0.5, 5, NERO);
        testo(0.5, 0.5, "GOMOKU", 0, BIANCO);
        testo(0.5, 0.45, "vince il primo che riesce a disporre 5 pedine in fila", 0, BIANCO);
        ritardo(5000);
    }
}
