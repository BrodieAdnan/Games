function Grafica(scambio) {
	divTabella = document.getElementById("campoGioco");
	tabella = disegnaTabella(4, 10, scambio);
	var div = document.createElement("div");
	div.id = "posMazzo";
	div.addEventListener("click", scambio);
	divTabella.appendChild(div);
	var immagine = document.createElement("img");
	immagine.id = "cartaGioco";
	div.appendChild(immagine);

	function disegnaTabella(righe, colonne, scambio) {
		document.body.appendChild(divTabella);
		var tabella = document.createElement("table");
		for (var r = 0; r < righe; r++) {
			var riga = tabella.insertRow(r);
			for (var c = 0; c < colonne; c++) {
				var colonna = riga.insertCell(c);
				var immagine = document.createElement("img");
				immagine.src = Mazzo.COPERTA;
				immagine.style.height = "100px";
				immagine.style.width = "70px";
				immagine.coordinate = {riga: r, colonna: c};
				colonna.appendChild(immagine);
			}
		}
		tabella.addEventListener("click", scambio);
		divTabella.appendChild(tabella);
		return tabella;
	}

	this.mostra = function (carta) {
		var img = document.getElementById("cartaGioco");
		img.src = carta.immagine;
	}
}