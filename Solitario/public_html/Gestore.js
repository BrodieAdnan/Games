window.onload = function (){
	new Gestore();
};

function CreaTavoloDaGioco(righe, colonne) {
	var tavoloDaGioco = new Array(righe);
	for (var r = 0; r < righe; r++) {
		for (var c = 0; c < colonne; c++) {
			tavoloDaGioco[r] = new Array(colonne);
		}
	}
	return tavoloDaGioco;
}

function Gestore(){
	tavolo = new CreaTavoloDaGioco(4, 10);
	mazzoCarte = new Mazzo(Mazzo.SEMI, Mazzo.VALORI);
	mazzoCarte.creaCarte("Immagini/");
	grafica = new Grafica(switchCards);
	newCarta = pesca();
	posiziona();

	function switchCards(event) {
		var r = event.target.coordinate.riga;
		var c = event.target.coordinate.colonna;
		tavolo[r][c].alreadyChange = true;
		newCarta.alreadyChange = true;
		var temp = tavolo[r][c];
		tavolo[r][c] = newCarta;
		if (event.target.src.indexOf(Mazzo.COPERTA) >= 0){
			temp.setGira();
		}
		newCarta = temp;
		var celle;
		celle = tabella.rows[r].cells;
		celle[c].childNodes[0].src = tavolo[r][c].immagine;
		grafica.mostra(newCarta);
	}
	
	function posiziona() {
		for (var r = 0; r < tavolo.length; r++) {
			for (var c = 0; c < tavolo[0].length; c++) {
				tavolo[r][c] = mazzoCarte.getCarta();
				tavolo[r][c].alreadyChange = false;
			}
		}
	}
	
	function pesca() {
		if (mazzoCarte.getCarte() > 0) {
			var cartaPescata = mazzoCarte.getCarta();
			cartaPescata.setGira();
			grafica.mostra(cartaPescata);
			return cartaPescata;
		}
	}
}
