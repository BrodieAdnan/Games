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
        
        g.mostraCarta("mazzoPrincipale", mazzoPrincipale.getImmagineCima());
    }
    
    //evento mazzo scarti
    //evento ordinati
    //evento sfalsati
    
    function dragStart(nome)//evento dragstart
    {
        if(nome.indexOf("mazzoPrincipale")!==-1)mazzoScambio= mazzoPrincipale;
        else if(nome.indexOf("mazzoScarti")!==-1)mazzoScambio= mazzoScarti;
        else if(nome.indexOf("mazziOrdinati")!==-1)mazzoScambio= mazziOrdinati[nome[13]-'0'];
        else if(nome.indexOf("mazziDecrescenti")!==-1)mazzoScambio= mazziDecrescenti[nome[16]-'0'];
        idMazzoScambio= nome;
    }
    
    function eventoScarti(event)
    {
        event.preventDefault();
        if(mazzoScambio!==null && mazzoScambio!==mazzoScarti)
        {
            var c= mazzoScambio.getCarta();
            mazzoScarti.aggiungi(c);
            g.mostraCarta("mazzoScarti", c.immagine);
            
            g.mostraCarta(idMazzoScambio, mazzoScambio.getImmagineCima());
            
            debug();
            
            mazzoScambio=null;
            idMazzoScambio= null;
        }
    }
    
    function eventoOrdinato(event)
    {
        event.preventDefault();
        if(mazzoScambio!==null)
        {
            var c= mazzoScambio.getCarta();
            var mazzo= mazziOrdinati[event.target.id.charAt(13)];
            
            if(!mazzo.aggiungi(c))
            {
                mazzoScambio.aggiungi(c);
            }
            else
            {    
                g.mostraCarta(event.target.id, c.immagine);
                
                g.mostraCarta(idMazzoScambio, mazzoScambio.getImmagineCima());
                               
                controlloVittoria();
                debug();
                
                mazzoScambio=null;
                idMazzoScambio= null;
            }
        } 
    }
    
    function eventoDecrescente(event)
    {
        event.preventDefault();
        if(mazzoScambio!==null)
        {
            var c= mazzoScambio.getCarta();
            var mazzo=mazziDecrescenti[event.target.id.charAt(16)];
            
            if(!mazzo.aggiungi(c))
            {
                mazzoScambio.aggiungi(c);
            }
            else
            {
                g.mostraCarta(event.target.id, c.immagine);
                
                g.mostraCarta(idMazzoScambio, mazzoScambio.getImmagineCima());
                
                debug();
                
                mazzoScambio=null;
                idMazzoScambio= null;
            }
        } 
    }
    
    function controlloVittoria()
    {
        var vittoria= true;
        for(var i=0; i<mazziOrdinati.length  && vittoria; i++)
        {
            vittoria= mazziOrdinati[i].getCarte()===10;
        }
        if(vittoria && confirm("Hai vinto!\nVuoi rigiocare?")) window.location= ".\\index.html";
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
    g.scriviDebug("La situazione dei mazzi verrà mostrata dopo aver effettuato la prima mossa valida");
};