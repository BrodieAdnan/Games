function Grafica() {
    this.disegnaTavolo();
}

Grafica.prototype.start = function (m, s, c, d) {
    mazzo = m;
    scarti = s;
    crescente = c;
    decrescente = d;
    cartaPescata = new Carta();
    pesca();
}

function vittoria() {
    var div = document.getElementById("body");
    var divo = document.createElement("div");
    divo.style.height = "100%";
    divo.style.width = "100%";
    divo.style.border = "3px solid";
    divo.style.background = "white";
    var scritta = document.createElement("h1");
    scritta.innerHTML = "Complimenti, Hai vinto!";
    divo.appendChild(scritta);
    div.appendChild(divo);
    window.setTimeout(ricomincia, 1000);
}

function ricomincia() {
    document.location.reload();
}

function pesca() {
    if (mazzo.getCarte() > 0) {
        cartaPescata = mazzo.getCarta();
        this.posizionaNuovaCarta(cartaPescata);
    } else {
        vittoria();
    }
}

function posizionaNuovaCarta(carta) {
    var mazzoDiv = document.getElementById("mazzo0");
    mazzoDiv.appendChild(createImg(carta));
}

function createImg(carta) {
    var immagine = document.createElement("img");
    immagine.src = carta.immagine;
    immagine.id = carta.immagine;
    immagine.draggable = true;
    immagine.addEventListener("dragstart", drag);
    return immagine;
}

Grafica.prototype.disegnaMazzi = function (n, div, id) {
    var tabella = document.createElement("table");
    var riga = tabella.insertRow(0);
    for (var i = 0; i < n; i++) {
        if (id == "mazzo") {
            var colonna = riga.insertCell(i);
            var cella = document.createElement("div");
            cella.id = id + i;
            cella.style.height = "200px";
            cella.style.width = "100px";
            cella.style.border = "1px solid";
            cella.style.background = "white";
            cella.ondrop = "drop(event)";
            cella.ondragover = "allowDrop(event)";
            colonna.appendChild(cella);
        } else {
            var colonna = riga.insertCell(i);
            var cella = document.createElement("div");
            cella.id = id + i;
            cella.style.height = "200px";
            cella.style.width = "100px";
            cella.style.border = "1px solid";
            cella.style.background = "white";
            cella.ondrop = "drop(event)";
            cella.ondragover = "allowDrop(event)";
//            var img = document.createElement("img");
//            img.src = "Trevisane\\ZCOPERTA.JPG";
//            img.draggable = true;
//            img.addEventListener("dragstart", drag);
//            cella.appendChild(img);
            colonna.appendChild(cella);
        }
    }
    div.appendChild(tabella);
}

Grafica.prototype.disegnaTavolo = function () {
    var tavolo = document.getElementById("tavoloDaGioco");
    var scritta = document.createElement("h1");
    scritta.innerHTML = "Benvenuto nel Solitario A Croce";
    tavolo.appendChild(scritta);
    var scritta1 = document.createElement("h1");
    scritta1.innerHTML = "Mazzo Crescente";
    tavolo.appendChild(scritta1);
    this.disegnaMazzi(4, tavolo, "crescente");
    var scritta2 = document.createElement("h1");
    scritta2.innerHTML = "Mazzo Decrescente";
    tavolo.appendChild(scritta2);
    this.disegnaMazzi(5, tavolo, "decrescente");
    var scritta3 = document.createElement("h1");
    scritta3.innerHTML = "Mazzi Carte e Scarti";
    tavolo.appendChild(scritta3);
    this.disegnaMazzi(1, tavolo, "scarto");
    this.disegnaMazzi(1, tavolo, "mazzo");
}

function allowDrop(ev) {
    ev.preventDefault();
}

function drag(ev) {
    cartaPescata = mazzo.getCarta();
    ev.dataTransfer.setData("text", ev.target.id);
}

function drop(ev) {
    ev.preventDefault();
    var data = ev.dataTransfer.getData("text");
    ev.target.appendChild(document.getElementById(data));
    if (posiziona(ev)) {
        pesca();
    }
}

function posiziona(ev) {
    var mazzo = ev.target.id[0];
    var numero = ev.target.id[ev.target.id.length - 1];
    console.log(ev.target.id + " " + mazzo + " " + numero);
    //valido solo per il primo inserimento per ogni mazzo.
    if (mazzo == "s") {//scarti
        scarti.addCarta(cartaPescata);
        console.log("inserito negli scarti");
    } else if (mazzo == "c") {//crescente
        crescente[numero].addCarta(cartaPescata);
        console.log("inserito nel mazzo crescente");
    } else if (mazzo == "d") {//decrescente
        decrescente[numero].addCarta(cartaPescata);
        console.log("inserito nel mazzo decrescente");
    } 
    //utilizzati per tutti i seguenti inserimenti per ogni mazzo.
    else {
        var cartaPrec=new Carta();
        cartaPrec=document.getElementById(ev.target.id);//fornita la carta precedente.
        var m = cartaPrec.immagine;
        console.log(m);
        console.log(ev.target.id);
    }
    return true;//condizione: se carta proveniente dal mazzo originale.
}