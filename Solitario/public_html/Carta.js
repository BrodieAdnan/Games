function Carta(pseme, pvalore, pimmagine, pretro, pmazzo) {
    this._mazzo = pmazzo;
    this._seme = pseme;
    this._valore = pvalore;
    this._coperta = true;
    this._retro = pretro;
    this._immagine = pimmagine;
}

Carta.prototype = {
    setGira: function ()
    {
        this._coperta = !this._coperta;
    },

    toString: function ()
    {
        return this._seme.nome + "-" + this._valore.nome + "-" + this._mazzo;
    },

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
        return !this._coperta ? this._immagine : this._retro;
    }
};

