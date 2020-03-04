function MazzoCrescente() {
    this.seme;
    this.valore;
    this.n = 0;
    this.init();

    this.addCarta = function (carta) {
        if (this.n == 0) {
            this.seme = carta.seme;
            this.valore = carta.valore;
            this.carte.push(carta);
            this.n++;
        } else if (carta.seme !== this.seme && carta.valore <= this.valore) {
            this.carte.push(carta);
            this.n++;
            this.seme = carta.seme;
            this.valore = carta.valore;
        }
    }

    this.getCarta = function () {
        this.n--;
        return this.carte.pop();
    }
}

MazzoCrescente.prototype = new Mazzo();
MazzoCrescente.constructor = MazzoCrescente;