package crosswords;

import static zuclib.GraficaSemplice.*;


/*
 * CROSSWORDS
 *
 * @brief Prepara la tavoliera per le Crosswords.
 * @author Adnan Gazi & Riccardo Fusaro
 * @date 04/04/2017
 */
public class Tavoliere {
	private Parola[] tavolo;
	private double x;
	private double y;
	private double lato;
	private int dimensione;

	Tavoliere(int dimensione) {
		this.dimensione = dimensione;
		tavolo = new Parola[dimensione];
		x = 0.05;
		y = 0.95;
		lato = 0.9 / dimensione;
	}

	void disegno() {
		setFinestra(600, 600, "CrossWords");
		quadratoPieno(0.5, 0.5, 5, BLU);
		x += lato / 2;
		y -= lato / 2;
		for (int r = 0; r < dimensione; r++) {
			for (int c = 0; c < dimensione; c++) {
				quadratoPieno(x, y, lato, NERO);
				quadratoPieno(x, y, lato - (lato / 10), BIANCO);
				quadrato(x, y, lato);
				x += lato;
			}
			x = 0.05 + lato / 2;
			y -= lato;
		}
		y = 0.9; //ritorna alla posizione di partenza.
	}

	void seleziona() {
		while (true) {
			if (mousePremuto()) {
				while (mousePremuto());
				double mouseX = mouseX();
				double mouseY = mouseY();
				if (mouseX >= 0.05 && mouseX <= 0.95 && (mouseY >= 1 - (lato * dimensione + lato + lato / 2) && mouseY <= 0.95)) {
					
				}
			}
		}
	}
}
