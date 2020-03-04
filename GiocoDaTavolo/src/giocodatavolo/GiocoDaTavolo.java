package giocodatavolo;

import java.util.*;
import static zuclib.GraficaSemplice.*;
import static zuclib.Varie.*;

/**
 * ********************************
 * NOME
 *
 * classe principale
 *
 * @author Gazi Adnan Latif
 * @brief 'da mettere'.
 * @date 16/05/2017 
 * *******************************
 */

public class GiocoDaTavolo {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Inserisci il tuo nome:");
        String nome = in.next();
        Giocatore utente = new Giocatore(nome);
        Giocatore CPU = new Giocatore("CPU");
        Tavoliere t = new Tavoliere(10, 10);
        t.disegno("Gioco Da Tavolo");
        partenza(t, utente, CPU);
    }

    /**
     * **********************************************
     * PARTENZA
     *
     * @author Gazi Adnan Latif
     * @brief gestisce la partenza casuale del gioco.
     * **********************************************
     */
    public static void partenza(Tavoliere t, Giocatore utente, Giocatore CPU) {
        ///random start: inizia il giocatore che genera il numero casuale più alto.
        boolean whoStart=(utente.lancio(0, 10)>CPU.lancio(0, 10))?true:false;
        while(true){
            if (whoStart) {
                t.indicaTurno(utente.getNome());
                giocatore(t, utente);
                t.indicaTurno(CPU.getNome());
                CPU(t, CPU);
            } else {
                t.indicaTurno(CPU.getNome());
                CPU(t, CPU);
                t.indicaTurno(utente.getNome());
                giocatore(t, utente);
            }
        }
    }

    /**
     * *************************************
     * GIOCATORE
     *
     * @author Gazi Adnan Latif
     * @brief gestisce la mossa dell'utente.
     * *************************************
     */
    public static void giocatore(Tavoliere t, Giocatore utente) {
        while (!mousePremuto());
        if (mousePremuto()) {
            double mouseX = mouseX();
            double mouseY = mouseY();
            t.compreso(mouseX, mouseY);
            utente.incrementaLancio();
            if (t.controlla()) {
                t.vittoria(utente);
            }
        }
    }

    /**
     * *******************************
     * CPU
     *
     * @author Gazi Adnan Latif
     * @brief mossa casuale della CPU.
     * *******************************
     */
    public static void CPU(Tavoliere t, Giocatore CPU) {
        t.casualCPU(CPU.lancio(0, 9), CPU.lancio(0, 9));
        CPU.incrementaLancio();
        if (t.controlla()) {
            t.vittoria(CPU);
        }
    }
}
