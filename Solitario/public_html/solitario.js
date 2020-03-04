/* global mazzo, Mazzo, campoGioco, g, tabella, cartaPescata, divTabella */

window.onload= function()
{
    var g= new Gestore();
};

semi=["fiori", "quadri", "picche", "cuori"];
semiSimboli=["♣", "♦", "♠", "♥"];

function Grafica(evento, evento2)
{  
    function creaTabella(righe, colonne, evento)
    {
        document.body.appendChild(divTabella);       
        var tabella = document.createElement("table");

        var riga;
        var colonna;
        var immagine;

        for (var r = 0; r < righe; r++)
        {
            riga = tabella.insertRow(-1);
            for (var c = 0; c < colonne-1; c++)
            {
                colonna = riga.insertCell(-1);
                immagine= document.createElement("img");
                immagine.src= Mazzo.COPERTA;
                immagine.coordinate={riga: r, colonna: c};
                colonna.appendChild(immagine);
            }
            colonna= riga.insertCell(-1); 
            immagine= document.createElement("img");
            immagine.id="RE"+semi[r];
            immagine.src= "Immagini\\Nulla.jpg";
            colonna.appendChild(immagine);
            
            colonna= riga.insertCell(-1);
            immagine= document.createElement("span");
            immagine.innerHTML= semiSimboli[r];
            immagine.style.fontSize= "80px";
            immagine.style.padding= "10px";
            if(r%2!==0)immagine.style.color= "red";
            if(r===1 || r===2)immagine.style.padding= "15px";
            colonna.appendChild(immagine); 
        }
        tabella.addEventListener(evento.tipo, evento.callback);
        divTabella.appendChild(tabella); 
        return tabella;
    };
    
    this.mostraTutteLeCarte= function(campoGioco)
    {
        this.mostraCartaInGioco({immagine: "Immagini\\sfondo.jpg"});
        
        for (var r= 0; r< campoGioco.length; r++)
        {
            for (var c= 0; c< campoGioco[0].length; c++) 
            {
                if(campoGioco[r][c].immagine.indexOf(Mazzo.COPERTA)>=0) campoGioco[r][c].setGira();
                this.giraCarta(r, c, campoGioco[r][c]);
            }
        }
        cambiaScrittaBottone();
    };
    
    this.giraCarta= function(r, c, carta)
    {
        var celle;
        celle= tabella.rows[r].cells;
        celle[c].childNodes[0].src= carta.immagine;
    };
    
    function creaPosMazzo()
    {
        var divPosMazzo= document.createElement("div");
        divPosMazzo.id= "posMazzo";
        divPosMazzo.addEventListener(evento2.tipo, evento2.callback);
        divTabella.appendChild(divPosMazzo); 
        var immagine= immagine= document.createElement("img");
        immagine.id= "cartaGioco";
        divPosMazzo.appendChild(immagine);
    }
    
    this.mostraCartaInGioco= function(carta)
    {
        var immagineCartaPescata= document.getElementById("cartaGioco");
        immagineCartaPescata.src= carta.immagine;
    };

    function creaTitolo()
    {
        var divTitolo= document.createElement("div");
        divTitolo.id= "titolo";
        var titolo= document.createElement("img");
        titolo.src=".\\Immagini\\scritta.png";
        divTitolo.appendChild(titolo);
        divTabella.appendChild(divTitolo);
    }
    
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
        divTabella.appendChild(divRigioca);
    }
    
    function cambiaScrittaBottone()
    {
        document.getElementById("bottoneRigioca").value= "Rigioca";
    };
   
    divTabella= document.createElement("div");
    divTabella.id= "campoGioco";
    creaTitolo();
    tabella= creaTabella(4, 10, evento);
    creaPosMazzo();
    creaTastoRigioca();
}

function Gestore()
{   
    campoGioco= new Matrice(4, 9);
    mazzo= new Mazzo(Mazzo.SEMI, Mazzo.VALORI);
    mazzo.creaCarte(".\\Immagini\\");
    g= new Grafica({tipo:"click", callback: scambio}, {tipo:"click", callback: posizionaRe});
    cartaPescata= pescaCarta();
    disponiCarte();
    
    function disponiCarte()
    {
        for (var r=  0; r< campoGioco.length; r++) 
        {
            for (var c = 0; c< campoGioco[0].length; c++) 
            {
                campoGioco[r][c]= mazzo.getCarta();
                campoGioco[r][c].alreadyChange= false;
            }
        }
    };
    
    function pescaCarta()
    {
        if(mazzo.getCarte()>0)
        {
            var carta= mazzo.getCarta();
            carta.setGira();
            g.mostraCartaInGioco(carta);
            return carta;
        }
        else return controlloVittoria();
    };
    
    function scambio(event)
    {
        var r= event.target.coordinate.riga;
        var c= event.target.coordinate.colonna;
        if(!campoGioco[r][c].alreadyChange && cartaPescata.valore.valore!==9)
        {
            campoGioco[r][c].alreadyChange= true;
            cartaPescata.alreadyChange= true;
            var temp= campoGioco[r][c]; 
            campoGioco[r][c]= cartaPescata;
            if(event.target.src.indexOf(Mazzo.COPERTA)>=0) temp.setGira();
            cartaPescata= temp;
            g.giraCarta(r, c, campoGioco[r][c]);
            g.mostraCartaInGioco(cartaPescata);
        }
    };  
    
    function posizionaRe(event)
    {
        if(cartaPescata.valore.valore===9)
        {
            var immagine= document.getElementById("RE"+cartaPescata.seme.nome);
            immagine.src= cartaPescata.immagine;
            cartaPescata= pescaCarta();
        }
    };
    
    function controlloVittoria()
    {
        var vittoria= true;
        
        for (var r=0; r<campoGioco.length && vittoria; r++) 
        {
            for (var c=0; c<campoGioco[0].length && vittoria; c++) 
            {
                vittoria= campoGioco[r][c].valore.valore===c && campoGioco[r][c].seme.nome===semi[r];
            }      
        }
        g.mostraTutteLeCarte(campoGioco);
        if(vittoria) alert("Hai vinto!");
        else alert("Hai perso");
    };
};

function Matrice(righe, colonne)
{
    var matrix= new Array(righe);
    
    for(var r=0; r<righe; r++)
    {
        for(var c=0; c<colonne; c++)
        {
            matrix[r]= new Array(colonne);
        }
    }
    return matrix;
};