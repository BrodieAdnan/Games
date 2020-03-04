package battaglianavale;

/**
 * ***********************************************************
 * GIOCATORE
 * 
 * classe secondaria
 * 
 * @author Gazi Adnan Latif
 * @brief oggetto giocatore: simula il gioco per più giocatori.
 * @date 16/05/2017
 * ************************************************************
 */
public class Giocatore {

	private String nome;
	private int lanciEffettuati;
	private int partiteVinte;

	public Giocatore(String nome) {
		this.nome = nome;
		lanciEffettuati = 0;
		partiteVinte = 0;
	}

	public void incrementaLancio() {
		lanciEffettuati++;
	}

	public void invrementaVinte() {
		partiteVinte++;
	}

	public String getNome() {
		return nome;
	}

	public int getVinte() {
		return partiteVinte;
	}

	public int getLanci() { //aggiunto all'oggetto iniziale.
		return lanciEffettuati;
	}

	public int lancio(int inf, int sup) {
		return (int) Math.floor(Math.random() * (sup - inf + 1) + 1);
	}
}
