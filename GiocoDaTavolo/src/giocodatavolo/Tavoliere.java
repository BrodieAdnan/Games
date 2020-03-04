package giocodatavolo;
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

public class Tavoliere {

    private boolean[][] tavoliere;
    private double x;
    private double y;
    private double latoH;
    private double latoL;
    private int altezza;
    private int larghezza;
    private int[] sol;

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
    }
    
    /*****************************************
    * DISEGNO
    *@author Gazi Adnan Latif
    *@brief disegna sulla finestrala matrice.
    *****************************************/
    public void disegno(String nomeFinestra) {
        setFinestra(700, 700, nomeFinestra);
        quadratoPieno(0.5, 0.5, 5, coloreACaso());
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
    }

    /*****************************************************************
    * COMPRESO
    *@author Gazi Adnan Latif
    *@brief verifica che il click ricada dentro l'area della matrice.
    *****************************************************************/
    public void compreso(double mx, double my) {
        sol[0] = (int) ((my - 0.05) / latoH);
        sol[1] = (int) ((mx - 0.05) / latoL);
        if (mx >= 0.05 && mx <= 0.95 && (my >= 1 - (latoH * altezza + latoH + latoH / 2) && my <= 0.95) && !tavoliere[sol[0]][sol[1]]) {
            tavoliere[sol[0]][sol[1]]=true;
            copri();
        } else {
            indicaTurno("Turno saltato");
        }
    }

    /*************************************
    * COPRI
    *@author Gazi Adnan Latif
    *@brief copertura della cella premuta.
    **************************************/
    public void copri() {
        rettangoloPieno(latoL * sol[1] + 0.05 + latoL / 2, latoH * sol[0] + 0.05 + latoH / 2, latoL, latoH, NERO);
    }
    
    /****************************************
    * CONTROLLA
    *@author Gazi Adnan Latif
    *@brief controlla se c'è un vincitore.
    *@return boolean: vittoria del giocatore.
    *****************************************/
    public boolean controlla(){
        ///quì ci va il codice.
        return false;
    }
    
    /**********************************************
    * VITTORIA
    *@author Gazi Adnan Latif
    *@brief interrompe il gioco e indica chi vince.
    ***********************************************/
    public void vittoria(Giocatore vincente){
        quadratoPieno(0.5, 0.5, 5, BIANCO);
        testo(0.5, 0.6, "IL VINCITORE è "+vincente.getNome());
        vincente.invrementaVinte();
        testo(0.5, 0.5, ""+vincente.getVinte()+" a 0 con "+vincente.getLanci()+" mosse!");
        ///il gioco viene bloccato con un ritardo infinito.
        ritardo((int)POSITIVE_INFINITY);
    }
    
    /*************************************************
    * CASUAL CPU
    *@author Gazi Adnan Latif
    *@brief copertura di una cella casuale per la CPU.
    **************************************************/
    public void casualCPU(int r, int c){
        try{
        tavoliere[r][c]=true;
        rettangoloPieno(latoL * c + 0.05 + latoL / 2, latoH * r + 0.05 + latoH / 2, latoL, latoH, BLU);
        }catch(Exception e){indicaTurno("Turno saltato");}
    }
    
    /**************************
    * INDICA TURNO
    *@author Gazi Adnan Latif
    *@brief indica a chi tocca.
    **************************/
    public void indicaTurno(String turn){
        rettangoloPieno(0.5, 0.97, 0.6, 0.04, ROSSO);
        testo(0.5, 0.97, ""+turn);
        ritardo(1000);
    }
}
