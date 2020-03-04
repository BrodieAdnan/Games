package battaglianavale;

import java.util.*;
import static zuclib.GraficaSemplice.*;

/**
 * ************************************************************
 * BATTAGLIA NAVALE
 * 
 * classe principale
 *
 * @author Gazi Adnan Latif
 * @brief permette di giocare alla battaglia navale monoutente.
 * @date 16/05/2017
 * ************************************************************
 */
public class BattagliaNavale {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Inserisci il tuo nome:");
		String nome = in.next();//pippo pinco pallo.
		Giocatore utente = new Giocatore(nome);
		Tavoliere t = new Tavoliere(10, 10);
		t.creaCampo(10, 3);// 10 navi nel campo di lunghezza massima 3.
		t.disegno("Battaglia Navale");
		giocatore(t, utente);
	}

	/**
	 * *************************************
	 * GIOCATORE
	 * @author Gazi Adnan Latif
	 * @brief gestisce la mossa dell'utente.
	 * *************************************
	 */
	public static void giocatore(Tavoliere t, Giocatore utente) {
		for (;;) {
			while (!mousePremuto());
			if (mousePremuto()) {
				while (mousePremuto());
				double mouseX = mouseX();
				double mouseY = mouseY();
				t.compreso(mouseX, mouseY);
				utente.incrementaLancio();
				if (!t.controlla()) {
					t.vittoria(utente);
				}
			}
		}
	}
}
