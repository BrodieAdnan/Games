function Mazzo() {
    this.init();
}

Mazzo.prototype.init = function () {
    this.carte = [];
}

Mazzo.prototype.creaCarte = function (percorso, mazzo) {
    for (var i = 0; i < 4; i++) {//semi
        var seme = (i == 0) ? "bastoni" : (i == 1) ? "denari" : (i == 2) ? "spade" : "coppe";//seme corrente
        for (var j = 1; j <= 10; j++) {//valori
            this.carte.push(new Carta(seme, j, percorso + ((j == 10) ? (i + 1) : i) + ((j == 10) ? 0 : j) + ".jpg", "mazzo0"));
        }
    }
}

Mazzo.prototype.getCarte = function () {
    return this.carte.length;
}

Mazzo.prototype.getCarta = function () {
    return this.carte.pop();
}

Mazzo.prototype.carta = function (seme, valore) {
    var carta = new Carta(seme, valore, "", "");
    return this.carte.find(function (c) {
        return c.equals(carta)
    });
}

Mazzo.prototype.mescola = function () {
    var i1, i2, temp;
    for (var i = 0; i < 150; i++) {
        i1 = Math.round(39 * Math.random());
        i2 = Math.round(39 * Math.random());
        while (i1 === i2){
            i2 = Math.round(39 * Math.random());
        }
        temp = this.carte[i1];
        this.carte[i1] = this.carte[i2];
        this.carte[i2] = temp;
    }
}

Mazzo.prototype.addCarta = function (carta) {
    this.carte.push(carta);
}

Mazzo.COPERTA = "Trevisane\\ZCOPERTA.JPG";