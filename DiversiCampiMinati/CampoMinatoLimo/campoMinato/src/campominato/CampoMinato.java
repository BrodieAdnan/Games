package campominato;

import java.util.Scanner;
import static zuclib.GraficaSemplice.*;
import static zuclib.Varie.*;

/**
 * @brief gioco campo minato
 * @author Marco Andrea Limongelli
 * @date 12 febbraio 2017
 */
public class CampoMinato {

    static Scanner input = new Scanner(System.in);

    /**
     * @bref metodo che inizializza e crea una matrice in base al livello
     * selezionato
     * @param livello => stringa, inidica il livello del gioco, in base al
     * livello cambia la grandezza della matrice e il numero di bombe
     * @return matrice inizializzata in base al livello selezionato
     */
    public static int[][] inizializzaMatrice(String livello) {
        int[][] matrice = new int[0][0];
        int nMine = 0;

        switch (livello) {
            case ("principiante"):
                matrice = new int[9][9];
                nMine = 10;
                break;
            case ("intermedio"):
                matrice = new int[16][16];
                nMine = 40;
                break;
            case ("personalizzato"):
                int righe;
                int colonne;
                righe = colonne = grandezzaPersonalizzata();

                matrice = new int[righe][colonne];
                nMine = righe * 2 + 10;
                break;
        }
        int[][] posMine = posizionaMine(matrice, nMine);
        return matrice;
    }

    /**
     * @brief metodo per selezionare la grandezza personalizzata del campo di
     * gioco. La matrice risulterà sempre un quadrato, quindi viene chiesto un
     * solo numero di celle.
     * @return righe=> int, indica il numero di righe e colonne che formeranno
     * il campo di gioco
     */
    public static int grandezzaPersonalizzata() {
        int colonne;
        int righe;
        righe = colonne = 10;
        boolean fine = false;

        quadratoPieno(0.5, 0.5, 1, GRIGIO);

        rettangoloPieno(0.5, 0.5, 0.30, 0.20, NERO);
        rettangoloPieno(0.5, 0.5, 0.25, 0.15, BIANCO);
        testo(0.5, 0.5, "" + righe);
        ///<disegno del segnalino del valore selezionato

        quadratoPieno(0.675, 0.5, 0.10, NERO);
        quadratoPieno(0.675, 0.5, 0.05, BIANCO);
        testo(0.675, 0.5, "+");
        ///<disegno del pulsante per aumentare il valore

        quadratoPieno(0.325, 0.5, 0.10, NERO);
        quadratoPieno(0.325, 0.5, 0.05, BIANCO);
        testo(0.325, 0.5, "-");
        ///<disegno del pulsante per diminuire il valore

        rettangoloPieno(0.5, 0.25, 0.20, 0.10, VERDE_CHIARO);
        testo(0.5, 0.25, "CONFERMA");
        ///<disegno del pulsante per confermare il valore selezionato

        while (!fine) {
            if (mousePremuto()) {
                double x = mouseX();
                double y = mouseY();
                if (x >= 0.40 && x <= 0.60 && y >= 0.15 && y <= 0.30)//se nel pulsante "conferma"
                {
                    fine = true;
                } else if (x >= 0.65 && x <= 0.7 && y >= 0.475 && y <= 0.525 && righe < 24)//se nel pulsante "+"
                {
                    righe++;
                    System.out.println(righe);
                } else if (x >= 0.275 && x <= 0.325 && y >= 0.475 && y <= 0.525 && righe > 10)//se nel pulsante "-"
                {
                    righe--;
                    System.out.println(righe);
                }
                rettangoloPieno(0.5, 0.5, 0.25, 0.15, BIANCO);
                testo(0.5, 0.5, "" + righe);
            }
            ritardo(50);
        }
        return righe;
    }

    /**
     * @brief metodo per posizionare le mine nella matrice in modo casuale
     * @param matrice => matrice campo di gioco
     * @param nMine => int, numero di mine da posizionare
     * @return => matrice contenente le coordinate riga colonna in cui sono
     * state posizionate nella matrice
     */
    public static int[][] posizionaMine(int[][] matrice, int nMine) {
        int[][] posMine = new int[2][nMine];

        for (int i = 0; i < nMine;) {
            int r = (int) (Math.random() * matrice.length);
            int c = (int) (Math.random() * matrice[0].length);
            posMine[0][i] = r;
            posMine[1][i] = c;

            if (diverso(posMine[0], i, r) && diverso(posMine[1], i, c)) {
                matrice[r][c] = -1;
                numeriMineAdiacenti(matrice, r, c);
                i++;
            }
        }
        return posMine;
    }

    /**
     * @brief metodo per verificare se la posizione casuale della mina è diversa
     * dalle posizioni delle mine precedentemente piazzate
     * @param array => array di int, rappresenta i valori delle righe in cui
     * sono state piazzate le bombe
     * @param posAttuale => int, posizione massima per scansionare l'array
     * @param valore => int, indica il valore della riga che deve essere diverso
     * dagli altri
     * @return diverso => boolean, true se il valore è diverso dagli altri,
     * false se non lo è
     */
    public static boolean diverso(int[] array, int posAttuale, int valore) {
        boolean diverso = true;

        for (int i = 0; i < posAttuale && !diverso; i++) {
            if (array[i] == valore) {
                diverso = false;
            }
        }
        return diverso;
    }

    /**
     * @brief metodo per inserire i numeri che indicano quante mine adiacenti ha
     * una cella
     * @param matrice => matrice di int, matrice del gioco
     * @param r => int, coordinata riga
     * @param c => int, coordinata colonna
     */
    public static void numeriMineAdiacenti(int[][] matrice, int r, int c) {
        if (r >= 0 && r < matrice.length && c >= 0 && c < matrice.length) {
            if (r - 1 >= 0 && c - 1 >= 0 && matrice[r - 1][c - 1] != -1) {
                matrice[r - 1][c - 1] += 1;
            }
            if (r - 1 >= 0 && matrice[r - 1][c] != -1) {
                matrice[r - 1][c] += 1;
            }
            if (r - 1 >= 0 && c + 1 < matrice.length && matrice[r - 1][c + 1] != -1) {
                matrice[r - 1][c + 1] += 1;
            }
            if (c - 1 >= 0 && matrice[r][c - 1] != -1) {
                matrice[r][c - 1] += 1;
            }
            if (c + 1 < matrice.length && matrice[r][c + 1] != -1) {
                matrice[r][c + 1] += 1;
            }
            if (r + 1 < matrice.length && c - 1 >= 0 && matrice[r + 1][c - 1] != -1) {
                matrice[r + 1][c - 1] += 1;
            }
            if (r + 1 < matrice.length && matrice[r + 1][c] != -1) {
                matrice[r + 1][c] += 1;
            }
            if (r + 1 < matrice.length && c + 1 < matrice.length && matrice[r + 1][c + 1] != -1) {
                matrice[r + 1][c + 1] += 1;
            }
        }
    }

    /**
     * @brief metodo princpale dove si svolge il gioco
     * @param matrice => matrice di int, matrice del gioco
     * @param lato => double, lunghezza del lato di una cella
     * @param lungR => int, numero di righe
     * @param lungC => int, numero di colonne
     * @return => vittoria => boolean, true se il giocatore ha vinto, false se
     * ha perso
     */
    public static boolean campoMinato(int[][] matrice, double lato, int lungR, int lungC) {
        boolean vittoria = false;
        int colonna;
        int riga;
        double xDis;
        double yDis;
        boolean fine = false;

        while (fine == false) {
            if (mousePremuto()) {
                double x = mouseX();
                double y = mouseY();
                //<lettura coordinate puntatore del mouse

                riga = Utility.yRiga(y, lato, 0.75);
                colonna = Utility.xColonna(x, lato, 0.25);
                //<calcolo riga e colonna della matrice rispetto alle coordinate               

                if (scopri(matrice, riga, colonna, lato, lungR, lungC) == true) {
                    fine = true;
                }

            } else if (premutoTasto(32) && mouseX() > 0.25 && mouseX() < 0.75 && mouseY() > 0.25 && mouseY() < 0.75)//se è premuta la barra spaziatrice ed il mouse è dentro alla matrice
            {
                riga = Utility.yRiga(mouseY(), lato, 0.75);
                colonna = Utility.xColonna(mouseX(), lato, 0.25);

                segnaMine(riga, colonna, lato);
            }
            ritardo(50);
        }
        return vittoria;
    }

    /**
     * @brief metodo che scopre le celle
     * @param matrice => matrice di int, matrice del gioco
     * @param r => int, coordinata della riga
     * @param c => int, coordinata della colonna
     * @param lato => double, lunghezza del lato di una cella
     * @param lungR => int, numero di righe
     * @param lungC => int, numero di colonne
     * @return bomba => boolean, true se si becca una bomba, false se non si
     * becca
     */
    public static boolean scopri(int[][] matrice, int r, int c, double lato, int lungR, int lungC) {
        boolean bomba = false;
        if (r >= 0 && r < lungR && c >= 0 && c < lungC) {
            double x = Utility.colonnaX(c, lato, 0.25);
            double y = Utility.rigaY(r, lato, 0.75);
            if (matrice[r][c] > 0) {
                Utility.disegnaCella(x, y, lato, BIANCO);
                testo(x, y, "" + matrice[r][c]);
                matrice[r][c] = -1;
            } else if (matrice[r][c] == -1) {
                Utility.disegnaCella(x, y, lato, ROSSO);
                bomba = true;
            } else {
                scopriNulle(matrice, r, c, lungR, lungC, lato);
            }
        }
        return bomba;
    }

    /**
     * @brief metodo ricorsivo che scopre le celle nulle e le prime che
     * contengono un 1
     * @param matrice => matrice di int, matrice del gioco
     * @param r => int, coordinata della riga
     * @param c => int, coordinata della colonna
     * @param lungR => int, numero di righe
     * @param lungC => int, numero di colonne
     * @param lato => double, lunghezza del lato di una cella
     */
    public static void scopriNulle(int[][] matrice, int r, int c, int lungR, int lungC, double lato) {
        if (r >= 0 && r < lungR && c >= 0 && c < lungC && (matrice[r][c] == 1 || matrice[r][c] == 0)) {
            double x = Utility.colonnaX(c, lato, 0.25);
            double y = Utility.rigaY(r, lato, 0.75);

            Utility.disegnaCella(x, y, lato, BIANCO);
            if (matrice[r][c] == 1) {;
                testo(x, y, "" + 1);
            }
            matrice[r][c] = -1;

            scopriNulle(matrice, r - 1, c - 1, lungR, lungC, lato);
            scopriNulle(matrice, r - 1, c, lungR, lungC, lato);
            scopriNulle(matrice, r - 1, c + 1, lungR, lungC, lato);
            scopriNulle(matrice, r, c - 1, lungR, lungC, lato);
            scopriNulle(matrice, r, c + 1, lungR, lungC, lato);
            scopriNulle(matrice, r + 1, c - 1, lungR, lungC, lato);
            scopriNulle(matrice, r + 1, c, lungR, lungC, lato);
            scopriNulle(matrice, r + 1, c + 1, lungR, lungC, lato);
        }
    }

    /**
     * @brief metodo per selezionare la difficoltà del gioco
     * @return difficoltà => String, contine la difficoltà selezionata
     */
    public static String selezioneDifficoltà() {
        boolean risposta = false;
        String difficoltà = "";
        quadratoPieno(0.5, 0.5, 1, GRIGIO_CHIARO);

        quadratoPieno(0.5, 0.75, 0.40, BIANCO);
        testo(0.5, 0.75, "PRINCIPIANTE");

        quadratoPieno(0.25, 0.25, 0.40, BIANCO);
        testo(0.25, 0.25, "INTERMEDIO");

        quadratoPieno(0.75, 0.25, 0.40, BIANCO);
        testo(0.75, 0.25, "PERSONALIZZATO");

        while (risposta == false) {
            double x = mouseX();
            double y = mouseY();

            if (mousePremuto()) {
                if (x >= 0.05 && x <= 0.45 && y >= 0.05 && y <= 0.45) {
                    difficoltà = "intermedio";
                    risposta = true;
                } else if (x >= 0.55 && x <= 0.95 && y >= 0.05 && y <= 0.45) {
                    difficoltà = "personalizzato";
                    risposta = true;
                } else if (x >= 0.30 && x <= 0.70 && y >= 0.55 && y <= 0.95) {
                    difficoltà = "principiante";
                    risposta = true;
                }
            }
        }
        return difficoltà;
    }

    /**
     * @brief metodo per segnare le mine. Le mine vengono segnate mediante il
     * posizionamento del mouse sopra alla cella e successivamente, (senza
     * premere il tasto destro) premendo la barra spaziatrice, la cella viene
     * colorata di arancione.
     * @param r => int, coordinata della riga
     * @param c => int, coordinata della colonna
     * @param lato => double, lunghezza del lato di una cella
     */
    public static void segnaMine(int r, int c, double lato) {
        double x = Utility.colonnaX(c, lato, 0.25);
        double y = Utility.rigaY(r, lato, 0.75);

        Utility.disegnaCella(x, y, lato, ARANCIONE);
    }

    public static void main(String[] args) {
        int[][] matrice = inizializzaMatrice(selezioneDifficoltà());
        int righe = matrice.length;
        int colonne = matrice[0].length;
        double lato = 0.5 / matrice.length;

        for (int r = 0; r < righe; r++) {
            for (int c = 0; c < colonne; c++) {
                System.out.print(matrice[r][c] + " ");
            }
            System.out.println("");
        }

        Utility.disegnaMatrice(matrice, lato, righe, colonne);
        if (campoMinato(matrice, lato, righe, colonne) == true) {
            System.out.println("VITTORIA");
        } else {
            System.out.println("SCONFITTA");
        }
    }
}
