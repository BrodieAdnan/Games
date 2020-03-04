package campominato;
import java.awt.Color;
import static zuclib.GraficaSemplice.*;

/**
 * @brief Classe Utily => contiene metodi utilizzati nella classe principale
 * @author Marco Andrea Limongelli
 */
public class Utility 
{
	
    /**
     * @brief Metodo che controlla se tutti i valori della matrice sono uguali
     * @param Matrice => matrice di interi compresi tra 0 e 3
     * @return vittoria => boolean che restituisce true se i valori sono tutti uguali, false se non lo sono
     */
    public static boolean controlloVittoria(int[][] Matrice, int lung)
    {
        int controllo= Matrice[0][0];
        boolean vittoria=true;
        
        for(int r=lung-1; r>= 0 && vittoria; r--)
        {
            for(int c=lung-1; c >= 0 && vittoria; c--)
            {
                if(Matrice[c][r]!=controllo)
                {
                    vittoria=false;
                }
            }
        }
        return vittoria;
    }
	
    /**
     * @brief funzione per calcolare le coordinate y della riga corrispondente della matrice
     * @param inizio => double, indica il punto da cui iniziano i quadrati della matrice sull'asse y
     * @param lato => double, lunghezza del lato di una cella
     * @param riga => variabile di tipo int che indica la riga della matrice
     * @return y => coordinata della riga nella finestra 
     */
    public static double rigaY(int riga, double lato, double inizio) 
    {
        double y;
        y = (inizio- (lato / 2)) - (lato * riga);
        return y;
    }
	
    /**
     * @brief Metodo con il quale, data la coordinata y, restituisce la posizione sulla matrice
     * @param inizio => double, indica il punto da cui iniziano i quadrati della matrice sull'asse y
     * @param y => double, coordinata nella schermata
     * @param lato => double, lunghezza del lato di una cella della matrice
     * @return riga => int, indica la riga della matrice in base alle coordinate
     */
    public static int yRiga(double y, double lato, double inizio)
    {
        int riga= (int) ((inizio-y)/lato);
        return riga;
    }
    
    /**
     * @brief funzione per calcolare le coordinate x della colonna corrispondente della matrice
     * @param inizio => double, indica il punto da cui iniziano i quadrati della matrice sull'asse x
     * @param lato => double, lunghezza del lato di una cella
     * @param colonna => variabile di tipo int che indica la colonna della matrice
     * @return x => coordinata della colonna nella finestra
     */
    public static double colonnaX(int colonna, double lato, double inizio) 
    {
        double x;
        x = inizio+(lato*colonna)+ lato/2;
        return x;
    }
	
    /**
     * @brief Metodo con il quale, data la coordinata x, restituisce la posizione sulla matrice
     * @param inizio => double, indica il punto da cui iniziano i quadrati della matrice sull'asse x
     * @param x => coordinata x del click del mouse
     * @param lato => lunghezza del lato della matrice
     * @return CodiceColore => valore intero compreso tra 0  3 che indica il numero del colore selezionato
     */
    public static int xColonna(double x, double lato, double inizio) 
    {
        int Colonna = (int) ((x-inizio)/lato);
        return Colonna;
    }
    
    /**
     * @brief funzione che disegna un quadrato colorato con un bordo nero
     * @param x => coordinata x dove verrà centrato il quadrato
     * @param y => coordinata y dove verrà centrato il quadrato
     * @param lato => lunghezza del lato del quadrato
     * @param c => colore con il quale verrà colorato
     */
    public static void disegnaCella(double x, double y, double lato, Color c)
    {
        quadratoPieno(x, y, lato, c);
        quadrato(x, y, lato);
    }
    
    /**
     * @brief Funzione che disegna la matrice con i quadrati vuoti
     * @param nRighe => int, numero di righe
     * @param nColonne => int, numero colonne
     * @param matrice=> matrice di tipo int
     * @param lato => variabile di tipo double
     */
    public static void disegnaMatrice(int [][]matrice, double lato, int nRighe, int nColonne)
    {
        quadratoPieno(0.5, 0.5, 1, BIANCO);
        
        double x = 0.25+(lato / 2);
        double y = 0.75-(lato / 2);

        for (int r = 0; r < nRighe; r++) 
        {
            for (int c = 0; c < nColonne; c++) 
            {               
                disegnaCella(x, y, lato, GRIGIO_CHIARO);
                x+=lato;
            }
            x = 0.25+(lato / 2);
            y -= lato;
        }         
    }
}
