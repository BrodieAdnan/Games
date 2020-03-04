package battaglianavale;

import static java.lang.Double.*;
import static zuclib.GraficaSemplice.*;
import static zuclib.Varie.ritardo;

/**
 * ********************************************************************************
 * OGGETTO TAVOLIERE
 *
 * classe secondaria
 *
 * @author Gazi Adnan Latif
 * @brief disegna un tavola da gioco e permette di modificare lo stato delle celle.
 * @date 16/05/2017
 * ********************************************************************************
 */

public class Tavoliere {//campo.

	private boolean[][] tavoliere;
	private Nave navi;
	private double x;
	private double y;
	private double latoH;
	private double latoL;
	private int altezza;
	private int larghezza;
	private int[] sol;
	int naviMancanti;

	///costruttore
	public Tavoliere(int altezza, int larghezza) {
		this.altezza = altezza;
		this.larghezza = larghezza;
		tavoliere = new boolean[altezza][larghezza];
		x = 0.05;
		y = 0.95;
		latoH = 0.9 / altezza;
		latoL = 0.9 / larghezza;
		sol = new int[2];
		navi = new Nave();
	}

	/**
	 * **************************************
	 * DISEGNO
	 * @author Gazi Adnan Latif
	 * @brief disegna sulla finestrala matrice.
	 * ***************************************
	 */
	public void disegno(String nomeFinestra) {
		setFinestra(500, 500, nomeFinestra);
		quadratoPieno(0.5, 0.5, 5, ARANCIONE);
		x += latoL / 2;
		y -= latoH / 2;
		for (int r = 0; r < altezza; r++) {
			for (int c = 0; c < larghezza; c++) {
				rettangoloPieno(x, y, latoL, latoH, NERO);
				rettangoloPieno(x, y, latoL - 0.001, latoH, BIANCO);
				rettangolo(x, y, latoL, latoH);
				x += latoL;
			}
			x = 0.05 + latoL / 2;
			y -= latoH;
		}
		y = 0.9; //ritorna alla posizione di partenza.
		indicaTurno("INIZIA IL GIOCO", "Mancano " + naviMancanti + " navi da affondare");
		out();
	}

	/**
	 * ***************************************************************
	 * COMPRESO
	 * @author Gazi Adnan Latif
	 * @brief verifica che il click ricada dentro l'area della matrice.
	 * ****************************************************************
	 */
	public void compreso(double mx, double my) {
		sol[0] = (int) ((my - 0.05) / latoH);
		sol[1] = (int) ((mx - 0.05) / latoL);
		if (mx >= 0.05 && mx <= 0.95 && (my >= 1 - (latoH * altezza + latoH + latoH / 2) && my <= 0.95) && !tavoliere[sol[0]][sol[1]] && navi.giveCampo()[sol[0]][sol[1]]) {
			tavoliere[sol[0]][sol[1]] = true;
			navi.colpito(sol[0], sol[1]);
			copri();
			naviMancanti--;
			indicaTurno("Colpito!", "Mancano " + naviMancanti + " navi da affondare");
		} else {
			indicaTurno("Missile finito in mare", "Mancano " + naviMancanti + " navi da affondare");
			cover();
		}
	}

	/**
	 * *************************************
	 * COPRI
	 * @author Gazi Adnan Latif
	 * @brief copertura della cella premuta.
	 * *************************************
	 */
	public void copri() {
		linea(latoL * sol[1] + latoL / 2, latoH * sol[0] + 0.1 + latoH / 2, latoL * sol[1] + 0.1 + latoL / 2, latoH * sol[0] + latoH / 2, ROSSO, 0.01);
		linea(latoL * sol[1] + 0.1 + latoL / 2, latoH * sol[0] + 0.1 + latoH / 2, latoL * sol[1] + latoL / 2, latoH * sol[0] + latoH / 2, ROSSO, 0.01);
	}

	public void cover() {
		cerchioPieno(latoH * sol[1] + 0.095, latoL * sol[0] + 0.095, 0.04, BLU);
		ritardo(100);
		cerchioPieno(latoH * sol[1] + 0.095, latoL * sol[0] + 0.095, 0.04, BIANCO);
	}

	/**
	 * ****************************************
	 * CONTROLLA
	 * @author Gazi Adnan Latif
	 * @brief controlla se c'è un vincitore.
	 * @return boolean: vittoria del giocatore.
	 * ****************************************
	 */
	public boolean controlla() {
		boolean fine = false;
		if (navi.tuttoAffondato()) {
			fine = true;
		}
		return fine;
	}

	/**
	 * **********************************************
	 * VITTORIA
	 * @author Gazi Adnan Latif
	 * @brief interrompe il gioco e indica chi vince.
	 * **********************************************
	 */
	public void vittoria(Giocatore vincente) {
		quadratoPieno(0.5, 0.5, 5, BIANCO);
		testo(0.5, 0.6, "Hai vinto " + vincente.getNome() + "!");
		vincente.invrementaVinte();
		testo(0.5, 0.55, "Hai affondato tutte le navi con " + vincente.getLanci() + " mosse!");
		///il gioco viene bloccato con un ritardo infinito.
		ritardo((int) POSITIVE_INFINITY);
	}

	/**
	 * **************************
	 * INDICA TURNO
	 * @author Gazi Adnan Latif
	 * @brief indica a chi tocca.
	 * **************************
	 */
	public void indicaTurno(String turn, String affondati) {
		rettangoloPieno(0.5, 0.97, 0.6, 0.04, VERDE);
		testo(0.5, 0.97, turn);
		rettangoloPieno(0.5, 0.03, 0.6, 0.04, CIANO);
		testo(0.5, 0.03, affondati);
	}

	/**
	 * ****************************************
	 * CREA CAMPO
	 * @author Gazi Adnan Latif
	 * @brief riempie il campo con alcune navi.
	 * ****************************************
	 */
	public void creaCampo(int n, int max) {
		naviMancanti = 3;
		int s = 0;
		for (int i = 0; i < n; i++) {
			///param: dimensione della nave.
			s = (int) Math.floor(Math.random() * max);
			navi.aggiungiNave(s);
			naviMancanti += s;
		}
	}

	/**
	 * ********************************************
	 * OUT
	 * @author Gazi Adnan Latif
	 * @brief output del campo avversario nascosto.
	 * ********************************************
	 */
	public void out() {
		for (int r = 9; r >= 0; r--) {
			for (int c = 0; c < 10; c++) {
				System.out.print(navi.giveCampo()[r][c] + " ");
			}
			System.out.println("");
		}
	}
}
