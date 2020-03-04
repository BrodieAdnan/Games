package gioco;

import java.util.*;
import static zuclib.Varie.*;

/**
 * *****************************************
 * GIOCO
 *
 * classe principale
 *
 * @author Gazi Adnan Latif
 * @brief gioco con un dado a più giocatori.
 * @date 11/04/2017 
 *******************************************
 */
public class Gioco {

    public static void main(String[] args) {
        String nome = aquisisciNome();
        Giocatore giocatore1 = new Giocatore(nome);
        String name = aquisisciNome();
        Giocatore giocatore2 = new Giocatore(name);
        Dado d = new Dado(1, 6);
        inizioGioco(giocatore1, giocatore2, d);
    }

    public static String aquisisciNome() {
        Scanner in = new Scanner(System.in);
        System.out.println("Inserisci il nome del giocatore :");
        String nome = in.next();
        return nome;
    }

    public static void inizioGioco(Giocatore g1, Giocatore g2, Dado d) {
        int contatore = 0;
        int uscita1 = 0;
        int uscita2 = 0;
        while (g1.getVinte() < 3 && g2.getVinte() < 3) {
            if (contatore % 2 == 0) {
                System.out.println("Turno "+(contatore+2)/2);
                System.out.println("Tocca a "+g2.getNome());
                uscita1 = g2.lancio(d);
                g2.incrementaLancio();
                out(uscita1);
            } else {
                System.out.println("Tocca a "+g1.getNome());
                uscita2 = g1.lancio(d);
                g1.incrementaLancio();
                out(uscita2);
                vincitore(uscita1, uscita2, g1, g2);
            }
            contatore++;
        }
        if (g1.getVinte()>g2.getVinte()) {
            finale(g1.getVinte(), g2.getVinte(), g1.getNome(), g2.getNome());
        } else {
            finale(g2.getVinte(), g1.getVinte(), g2.getNome(), g1.getNome());
        }
    }

    public static void out(int uscita) {
        System.out.println("Esce il numero "+ uscita);
    }
    
    public static void vincitore(int uno, int due, Giocatore g1, Giocatore g2){
        if (uno>due) {
            g2.invrementaVinte();
            System.out.println("Il numero più alto è quello di "+g2.getNome());
        } else {
            g1.invrementaVinte();
            System.out.println("Il numero più alto è quello di "+g1.getNome());
        }
    }
    public static void finale(int v, int p, String vincitore, String perdente){
        System.out.println("Alla fine vince " + vincitore);
        System.out.println(vincitore+" "+v+" : "+p+" "+perdente);
    }
}