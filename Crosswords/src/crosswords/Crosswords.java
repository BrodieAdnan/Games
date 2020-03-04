package crosswords;

import java.util.*;

/*
 * CROSSWORDS
 *
 * @brief Prepara la tavoliera per le Crosswords.
 * @author Adnan Gazi & Riccardo Fusaro
 * @date 04/04/2017
 */
public class Crosswords {
	public static void main(String[] args) {
		Scanner in=new Scanner (System.in);
		System.out.print("Inserisci la dimensione del tuo tavolo : ");
		int dimensione=in.nextInt();
		Tavoliere t = new Tavoliere(dimensione);
		t.disegno();
		
	}

}
