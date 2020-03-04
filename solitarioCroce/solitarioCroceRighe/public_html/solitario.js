window.onload= function()
{
    var g= new Gestore();
    //sg.giocaAutomaticamente();
};

function Grafica(FeventoMazzoScarti, dragStart, FeventoOrdinato, FeventoDecrescente)
{
    function disegnaCampo()
    {
        var divCampoGioco= document.createElement("div");
        var tab1= creaTabella(1, 4, FeventoOrdinato, "mazziOrdinati");
        var tab2= creaTabella(1, 5, FeventoDecrescente, "mazziDecrescenti");
        divCampoGioco.appendChild(tab1);
        divCampoGioco.appendChild(tab2);
        
        var divMazzi= document.createElement("div");
        var tabMazzi= document.createElement("table");
        var riga= tabMazzi.insertRow(-1);
        var colonna= riga.insertCell(-1);
        var immagine= document.createElement("img");
        immagine.id="mazzoPrincipale";
        immagine.draggable= true;
        immagine.addEventListener("dragstart", dragStart);
        aggiungiEventi(colonna);
        immagine.addEventListener("dragstart", dragStart);
        colonna.appendChild(immagine);
        
        colonna= riga.insertCell(-1);
        immagine= document.createElement("img");
        aggiungiEventi(colonna, FeventoMazzoScarti);
        immagine.id= "mazzoScarti";
        immagine.draggable= true;
        immagine.addEventListener("dragstart", dragStart);
        colonna.appendChild(immagine);
        
        var divDebug= document.createElement("div");
        divDebug.id= "debug";
        
        divMazzi.appendChild(tabMazzi);
        divCampoGioco.appendChild(divMazzi);
        
        document.body.appendChild(divCampoGioco);
        creaTastoRigioca();
        document.body.appendChild(divDebug);
    }
    
    function creaTabella(righe, colonne, Fevento, nome)
    {
        var divTabella= document.createElement("div");     
        var tabella = document.createElement("table");

        var riga;
        var colonna;
        var immagine;

        for (var r = 0; r < righe; r++)
        {
            riga = tabella.insertRow(-1);
            for (var c = 0; c < colonne; c++)
            {
                colonna = riga.insertCell(-1);
                immagine= document.createElement("img");
                colonna.id=nome+c;
                immagine.draggable= true;
                immagine.id=nome+c+"img";
                immagine.addEventListener("dragstart", dragStart);
                aggiungiEventi(colonna, Fevento);
                colonna.appendChild(immagine);
            }
        }
        divTabella.appendChild(tabella);
        return divTabella;
    };
    
    function aggiungiEventi(elemento, Fevento)
    {
        elemento.addEventListener("dragover", allowDrop);
        if(Fevento !== undefined) elemento.addEventListener("drop", Fevento);
        else elemento.addEventListener("drop", function(event){event.preventDefault();});
    }
    
    function allowDrop(event) {event.preventDefault();}//evento dragover
    
    this.mostraCarta= function(id, srcImmagine)
    {
        var img= document.getElementById(id);
        img.src= srcImmagine;
    };
    
    function creaTastoRigioca()
    {
        var divRigioca= document.createElement("div");
        divRigioca.id= "divRigioca";
        var bottone= document.createElement("INPUT");
        bottone.setAttribute("type", "button");
        bottone.value="Ricomincia";
        bottone.id="bottoneRigioca";
        bottone.onclick= function(){window.location= ".\\index.html";};
        divRigioca.appendChild(bottone);
        document.body.appendChild(divRigioca);
    }
    
    this.scriviDebug= function(testo)
    {
        document.getElementById("debug").innerHTML= testo;
    };
    
    disegnaCampo();
};

function Gestore()
{
    var mazzoPrincipale;
    var mazzoScarti;
    var mazziOrdinati;
    var mazziDecrescenti;
    var mazzoScambio= null;
    var idMazzoScambio= null;
    var g= new Grafica(eventoScarti, dragStart, eventoOrdinato, eventoDecrescente);
    
    function inizializzaMazzi()
    {
        mazzoScarti= new Mazzo((new Enum([], "Seme"), new Enum([], "Valore")));
        mazzoPrincipale= new Mazzo(Mazzo.SEMI, Mazzo.VALORI);
        mazzoPrincipale.creaCarte(".\\Trevisane\\");
        
        mazziOrdinati= [4];
        for(var i=0; i<4; i++)
        {
            mazziOrdinati[i]= new MazzoOrdinato((new Enum([], "Seme"), new Enum([], "Valore")));
        }
        
        mazziDecrescenti= [5];
        for(var i=0; i<5; i++)
        {
            mazziDecrescenti[i]= new MazzoDecrescente((new Enum([], "Seme"), new Enum([], "Valore")));
        }
        
        var c= mazzoPrincipale.getCarta();
        g.mostraCarta("mazzoPrincipale", c.immagine);
        mazzoPrincipale.aggiungi(c);
    }
    
    //evento mazzo scarti
    //evento ordinati
    //evento sfalsati
    
    function dragStart(event)//evento dragstart
    {
        if(event.target.id.indexOf("mazzoPrincipale")!==-1)mazzoScambio= mazzoPrincipale;
        else if(event.target.id.indexOf("mazzoScarti")!==-1)mazzoScambio= mazzoScarti;
        else if(event.target.id.indexOf("mazziOrdinati")!==-1)mazzoScambio= mazziOrdinati[event.target.id[13]-'0'];
        else if(event.target.id.indexOf("mazziDecrescenti")!==-1)mazzoScambio= mazziDecrescenti[event.target.id[16]-'0'];
        idMazzoScambio= event.target.id;
    }
    
    function eventoScarti(event)
    {
        event.preventDefault();
        if(mazzoScambio!==null && mazzoScambio!==mazzoScarti)
        {
            var c= mazzoScambio.getCarta();
            mazzoScarti.aggiungi(c);
            g.mostraCarta("mazzoScarti", c.immagine);
            
            if(mazzoScambio.getCarte()>0)
                {
                    c= mazzoScambio.getCarta();
                    mazzoScambio.aggiungi(c);
                    g.mostraCarta(idMazzoScambio, c.immagine);
                }
            else g.mostraCarta(idMazzoScambio, ".\\Trevisane\\vuota.gif");
            
            mazzoScambio=null;
            idMazzoScambio= null;
        }
        debug();
    }
    
    function eventoOrdinato(event)
    {
        event.preventDefault();
        if(mazzoScambio!==null)
        {
            var c= mazzoScambio.getCarta();
            var mazzo= mazziOrdinati[event.target.id.charAt(event.target.id.length-1)];
            if(mazzo===undefined) mazzo= mazziOrdinati[event.target.id.charAt(13)];
            if(!mazzo.aggiungi(c))
            {
                mazzoScambio.aggiungi(c);
            }
            else
            {
                if(event.target.id.indexOf("img")>=0) g.mostraCarta(event.target.id, c.immagine);
                else g.mostraCarta(event.target.id+"img", c.immagine);
                
                if(mazzoScambio.getCarte()>0)
                {
                    c= mazzoScambio.getCarta();
                    mazzoScambio.aggiungi(c);
                    g.mostraCarta(idMazzoScambio, c.immagine);
                }
                else g.mostraCarta(idMazzoScambio, ".\\Trevisane\\vuota.gif");
                                
                if(controlloVittoria())
                {
                    if(confirm("Hai vinto!\nVuoi rigiocare?"))window.location= ".\\index.html";
                }
                
                mazzoScambio=null;
                idMazzoScambio= null;
            }
        } 
        debug();
    }
    
    function eventoDecrescente(event)
    {
        event.preventDefault();
        if(mazzoScambio!==null)
        {
            var c= mazzoScambio.getCarta();
            var mazzo= mazziDecrescenti[event.target.id.charAt(event.target.id.length-1)];
            if(mazzo===undefined) mazzo= mazziDecrescenti[event.target.id.charAt(16)];
            if(!mazzo.aggiungi(c))
            {
                mazzoScambio.aggiungi(c);
            }
            else
            {
                if(event.target.id.indexOf("img")>0) g.mostraCarta(event.target.id, c.immagine);
                else g.mostraCarta(event.target.id+"img", c.immagine);
                
                if(mazzoScambio.getCarte()>0)
                {
                    c= mazzoScambio.getCarta();
                    mazzoScambio.aggiungi(c);
                    g.mostraCarta(idMazzoScambio, c.immagine);
                }
                else g.mostraCarta(idMazzoScambio, ".\\Trevisane\\vuota.gif");
                
                mazzoScambio=null;
                idMazzoScambio= null;
            }
        }
        debug();
    }
    
    function controlloVittoria()
    {
        var vittoria= true;
        for(var i=0; i<mazziOrdinati.length  && vittoria; i++)
        {
            vittoria= mazziOrdinati[0].getCarte()===10;
        }
        return vittoria;
    }
    
    function debug()
    {
        var testo= "mazzoPrincipale: "+mazzoPrincipale.getCarte()+"<br>";
        testo+= "mazzoScarti: "+ mazzoScarti.getCarte()+"<br>";
        for(var i=0; i<4; i++) testo+="mazzoOrdinato "+i+": "+mazziOrdinati[i].getCarte()+"<br>";
        for(var i=0; i<5; i++) testo+="mazzoDecrescente "+i+": "+mazziDecrescenti[i].getCarte()+"<br>";
        g.scriviDebug(testo);
    }
    
    inizializzaMazzi();
};