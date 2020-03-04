package campominato;

import java.util.Scanner;
import static zuclib.GraficaSemplice.*;

/**
 CAMPO MINATO.

 @author Adnan Gazi Latif.
 @data inizio 1/02/2017
 @data fine 13/02/2017
 @brief permette di giocare a campo minato.
 */
public class CampoMinato {

	static Scanner input = new Scanner(System.in);
	static int[][] matrixBombe;
	static int[][] matrixAdiacenti;
	static int righe;
	static int colonne;
	static int bombe;
	static double x = 0.05;
	static double y = 0.95;
	static double latoH;
	static double[][] appoggioX;
	static double[][] appoggioY;

	public static void main(String[] args) {
		difficoltà();
		creazione();
		riempiMatriceBombe();
		riempiBombe();
		bombeAdiacenti();
		copertura();
		inizio();
	}

	public static void difficoltà() {
		System.out.println("Inscerisci la difficoltà : principiante, intermedio, esperto, personalizzato "); //scelta della difficoltà.
		String difficoltà = input.next();
		if (difficoltà.equals("principiante")) {
			righe = 9;
			colonne = 9;
			bombe = 10;
		} else if (difficoltà.equals("intermedio")) {
			righe = 16;
			colonne = 16;
			bombe = 40;
		} else if (difficoltà.equals("esperto")) {
			righe = 16;
			colonne = 30;
			bombe = 99;
		} else if (difficoltà.equals("personalizzato")) {
			do { //prende prima i valori personalizzati e dopo li verifica.
				System.out.println("Quante righe vuoi ? (max:24 min:9)");
				righe = input.nextInt();
				System.out.println("Quante colonne vuoi ? (max:30 min:9)");
				colonne = input.nextInt();
				System.out.println("Quante bombe vuoi ? (max: 667 min:10)");
				bombe = input.nextInt();
			} while ((righe < 9 || righe > 24) || (colonne < 9 || colonne > 30) || (bombe < 1 || bombe > 667));
		}
	}

	public static void creazione() {
		matrixBombe = new int[righe][colonne];
		matrixAdiacenti = new int[righe][colonne];
		appoggioX = new double[righe][colonne];
		appoggioY = new double[righe][colonne];
	}

	public static void riempiMatriceBombe() {
		for (int i = 0; i < righe; i++) {
			for (int j = 0; j < colonne; j++) {
				matrixBombe[i][j] = 0;
			}
		}
	}

	public static void out(int[][] matrice) {
		for (int r = 0; r < righe; r++) {
			for (int c = 0; c < colonne; c++) {
				System.out.print(matrice[r][c]);
			}
			System.out.println("");
		}
		System.out.println("");
	}

	public static void riempiBombe() {
		for (; bombe > 0;) {
			int randomR = (int) (Math.random() * righe);
			int randomC = (int) (Math.random() * colonne);
			if (matrixBombe[randomR][randomC] == 0) {
				matrixBombe[randomR][randomC] = 1;
				bombe--;
			}
		}
		out(matrixBombe);
	}

	public static void bombeAdiacenti() {

		for (int r = 0; r < righe; r++) {
			for (int c = 0; c < colonne; c++) {
				int contBombe = 0;
				contBombe += controllaAdiacenti(r - 1, c - 1);
				contBombe += controllaAdiacenti(r - 1, c);
				contBombe += controllaAdiacenti(r - 1, c + 1);
				contBombe += controllaAdiacenti(r, c + 1);
				contBombe += controllaAdiacenti(r, c - 1);
				contBombe += controllaAdiacenti(r + 1, c - 1);
				contBombe += controllaAdiacenti(r + 1, c);
				contBombe += controllaAdiacenti(r + 1, c + 1);
				matrixAdiacenti[r][c] = contBombe;
			}
		}
		out(matrixAdiacenti);
	}

	public static int controllaAdiacenti(int r, int c) {
		int cont = 0;
		try {
			if (matrixBombe[r][c] == 1) {
				cont++;
			}
		} catch (Exception e) {

		}
		return cont;
	}

	public static void copertura() {
		setFinestra(600, 600, "Campo Minato");
		quadratoPieno(0.5, 0.5, 5, CIANO);
		latoH = 0.9 / colonne;
		x += latoH / 2;
		y -= latoH / 2;
		for (int r = 0; r < righe; r++) {
			for (int c = 0; c < colonne; c++) {
				quadratoPieno(x, y, latoH, GRIGIO);
				quadratoPieno(x, y, latoH - (latoH / 4), GRIGIO_CHIARO);
				quadrato(x, y, latoH);
				appoggio(r, c);
				x += latoH;
			}
			x = 0.05 + latoH / 2;
			y -= latoH;
		}
		y = 0.9; //ritorna alla posizione di partenza.
	}

	public static void appoggio(int r, int c) {
		appoggioX[r][c] = x;
		appoggioY[r][c] = y;
	}

	public static void inizio() {
		int cont = 0;
		while (true) {
			if (mousePremuto()) {
				while (mousePremuto());
				cont++;
				double mouseX = mouseX();
				double mouseY = mouseY();
				if (mouseX >= 0.05 && mouseX <= 0.95 && (mouseY >= 1 - (latoH * righe + latoH + latoH / 2) && mouseY <= 0.95)) {
					int col = casellaX(mouseX);
					int rig = casellaY(mouseY);
					sovrapposizione(rig, col);
					partenza(rig, col);
					fineAlternativa(cont);
					fine(rig, col, cont);
				}
			}
		}
	}

	public static int casellaX(double mouseX) {
		int col = -1;
		double contenuto = 0;
		for (int r = 0; r < righe; r++) {
			for (int c = 0; c < colonne; c++) {
				contenuto = appoggioX[r][c];
				contenuto -= latoH / 2;
				if (mouseX >= contenuto && mouseX <= contenuto + latoH) {
					col = c;
				}
			}
		}
		return col;
	}

	public static int casellaY(double mouseY) {
		int rig = -1;
		double contenuto = 0;
		for (int r = 0; r < righe; r++) {
			for (int c = 0; c < colonne; c++) {
				contenuto = appoggioY[r][c];
				contenuto -= latoH / 2;
				if (mouseY >= contenuto && mouseY <= contenuto + latoH) {
					rig = r;
				}
			}
		}
		return rig;
	}

	public static void sovrapposizione(int r, int c) {
		try {
			if (matrixBombe[r][c] == 0) {
				String s = "" + matrixAdiacenti[r][c];
				quadratoPieno(appoggioX[r][c], appoggioY[r][c], latoH, BIANCO);
				quadrato(appoggioX[r][c], appoggioY[r][c], latoH);
				testo(appoggioX[r][c], appoggioY[r][c], s);
			}
		} catch (Exception e) {
		}
	}

	public static void partenza(int r, int c) {
		try {
			if (matrixAdiacenti[r][c] == 0 && (matrixBombe[r][c] == 0)) {
				sovrapposizione(r, c);
				matrixBombe[r][c] = -1;
				partenza(r - 1, c);
				partenza(r, c + 1);
				partenza(r + 1, c);
				partenza(r, c - 1);
			}
		} catch (Exception e) {
		}
	}

	public static void fine(int r, int c, int cont) {
		if (matrixBombe[r][c] == 1) {
			quadratoPieno(0.5, 0.5, 5, BIANCO);
			testo(0.5, 0.5, "Hai perso con : " + cont + " mosse utilizzate");
			out(matrixBombe);
		}
	}

	public static void fineAlternativa(int cont) { //non funzionante.
		boolean fine = true;
		for (int r = 0; r < righe; r++) {
			for (int c = 0; c < colonne; c++) {
				if (matrixBombe[r][c] == 0) {
					fine = false;
				}
			}
		}
		if (fine) {
			quadratoPieno(0.5, 0.5, 5, BIANCO);
			testo(0.5, 0.5, "Hai vinto con : " + cont + " mosse utilizzate");
		}
	}
}
