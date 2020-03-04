package battaglianavale;

/**
 * *********************************************
 * OGGETTO NAVE
 *
 * classe secondaria
 *
 * @author Gazi Adnan Latif
 * @brief simula le caratteristiche di una nave.
 * @date 16/05/2017
 * *********************************************
 */

public class Nave {
	
	private boolean[][] campo;

	public Nave() {
		campo = new boolean[10][10];
	}

	/**
	 * *********************************
	 * AGGIUNGI NAVE
	 * @author Gazi Adnan Latif
	 * @brief aggiunta random di n navi.
	 * *********************************
	 */
	public void aggiungiNave(int dim) {
		int r = (int) Math.floor(Math.random() * 10);
		int c = (int) Math.floor(Math.random() * 10);
		if (!campo[r][c]) {
			campo[r][c] = true;
		}
		for (int i = 1; i < dim; i++) {
			if (r - 1 > 0 && !campo[r - 1][c]) {
				campo[r - 1][c] = true;
				r -= 1;
			} else if (r + 1 < 10 && !campo[r + 1][c]) {
				campo[r + 1][c] = true;
				r += 1;
			} else if (c - 1 > 0 && !campo[r][c - 1]) {
				campo[r][c - 1] = true;
				c -= 1;
			} else if (c + 1 < 10 && !campo[r][c + 1]) {
				campo[r][c + 1] = true;
				c += 1;
			}
		}
	}

	public boolean[][] giveCampo() {
		return campo;
	}

	/**
	 * *************************************************
	 * COLPITO
	 * @author Gazi Adnan Latif
	 * @brief modifica il contenuto della cella colpita.
	 * *************************************************
	 */
	public void colpito(int r, int c) {
		campo[r][c] = false;
	}

	/**
	 * ***********************************************
	 * TUTTO AFFONDATO
	 * @author Gazi Adnan Latif
	 * @brief controlla se ci altre navi da affondare.
	 * ***********************************************
	 */
	public boolean tuttoAffondato() {
		boolean giocoFinito = false;
		for (int r = 0; r < 10; r++) {
			for (int c = 0; c < 10; c++) {
				if (campo[r][c]) {
					giocoFinito = true;
				}
			}
		}
		return giocoFinito;
	}
}
