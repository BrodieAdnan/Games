function Grafica(FeventoMazzoScarti, dragStart, FeventoOrdinato, FeventoDecrescente)
{
    function disegnaCampo()
    {
        disegnaTitolo(".\\Trevisane\\titolo.png");
        
        var divCampoGioco= document.createElement("div"); 
        divCampoGioco.id= "divCampoGioco";
        divCampoGioco.appendChild(creaTabellaCroce());
        
        var divMazzi= document.createElement("div");
        divMazzi.id="divMazzi";
        
        var tabMazzi= document.createElement("table");
        var riga= tabMazzi.insertRow(-1);
        var colonna= riga.insertCell(-1);
        var immagine= document.createElement("img");
        immagine.id="mazzoPrincipale";
        immagine.draggable= true;
        aggiungiEventi(immagine);
        colonna.appendChild(immagine);
        
        colonna= riga.insertCell(-1);
        immagine= document.createElement("img");
        immagine.id= "mazzoScarti";
        immagine.draggable= true;
        immagine.src= ".\\Trevisane\\vuota.png";
        aggiungiEventi(immagine, FeventoMazzoScarti);
        colonna.appendChild(immagine);
        
        var divDebug= document.createElement("div");
        divDebug.id= "debug";
        
        divMazzi.appendChild(tabMazzi);
        divMazzi.appendChild(creaTastoRigioca());
        divMazzi.appendChild(divDebug);
        divCampoGioco.appendChild(divMazzi);
        
        document.body.appendChild(divCampoGioco);
    }
    
    function disegnaTitolo(percorso)
    {
        var divTitolo= document.createElement("div");
        divTitolo.id= "divTitolo";
        
        var titolo= document.createElement("img");
        titolo.id="titolo";
        titolo.src= percorso;
        divTitolo.appendChild(titolo);
        document.body.appendChild(divTitolo);
    }
    
    function creaTabellaCroce()
    {   
        var divTabella= document.createElement("div");
        divTabella.id="divTabellaMazzi";
        
        var tabella = document.createElement("table");
        var riga;
        var colonna;
        var immagine;
        
        riga = tabella.insertRow(-1);
        inserisciCella(riga, "mazziDecrescenti0", FeventoDecrescente);
        inserisciCella(riga, "mazziOrdinati0", FeventoOrdinato);
        inserisciCella(riga, "mazziDecrescenti1", FeventoDecrescente);
        
        riga = tabella.insertRow(-1);
        inserisciCella(riga, "mazziOrdinati1", FeventoOrdinato);
        inserisciCella(riga, "mazziDecrescenti2", FeventoDecrescente);
        inserisciCella(riga, "mazziOrdinati2", FeventoOrdinato);
        
        riga = tabella.insertRow(-1);
        inserisciCella(riga, "mazziDecrescenti3", FeventoDecrescente);
        inserisciCella(riga, "mazziOrdinati3", FeventoOrdinato);
        inserisciCella(riga, "mazziDecrescenti4", FeventoDecrescente);
        
        divTabella.appendChild(tabella);
        return divTabella;
        
        function inserisciCella(riga, nome, Fevento)
        {
            colonna = riga.insertCell(-1);
            immagine= document.createElement("img");
            colonna.id=nome;
            immagine.draggable= true;
            immagine.id=nome+"img";
            immagine.src= ".\\Trevisane\\vuota.png";           
            aggiungiEventi(immagine, Fevento);
            colonna.appendChild(immagine);
        }
    }
    
    function aggiungiEventi(elemento, Fevento)
    {
        elemento.addEventListener("dragover", allowDrop);
        elemento.addEventListener("dragstart", function(){dragStart(elemento.id);});
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
        return divRigioca;
    }
    
    this.scriviDebug= function(testo)
    {
        document.getElementById("debug").innerHTML= testo;
    };
    
    disegnaCampo();
};