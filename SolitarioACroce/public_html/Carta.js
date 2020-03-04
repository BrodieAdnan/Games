function Carta(pseme, pvalore, pimmagine, pmazzo) {
    this._seme = pseme;
    this._valore = pvalore;
    this._immagine = pimmagine;
    this._mazzo = pmazzo;
}

Carta.prototype = {
    equals: function (carta)
    {
        return this._seme.equals(carta.seme) && this._valore.equals(carta.valore);
    },

    get seme()
    {
        return this._seme;
    },

    get valore()
    {
        return this._valore;
    },

    get coperta()
    {
        return this._coperta;
    },

    get immagine()
    {
        return this._immagine;
    },

    get mazzo()
    {
        return this._mazzo;
    }
};

