function Mazzo(semi, valori) {
    this.init(semi, valori);
}

Mazzo.prototype.init = function (semi, valori) {
    this._carte = [];
    this._semi = semi;
    this._valori = valori;
};

Mazzo.prototype.creaCarte = function (percorso, mazzo) {
    var maz= this;
    maz._semi.forEach(function (s) { // Initializza l'array
        maz._valori.forEach(function (v) {
            maz._carte.push(new Carta(s, v, percorso + s.getNome()[0] + ("0" + (v.getValore() + 1)).substring(String(v.getValore() + 1).length - 1) + ".jpg", Mazzo.COPERTA, mazzo));
        });
    });
    mescola();
    
    function mescola()
    {
        var i1, i2, temp;

        for(var i=0; i<250; i++)
        {
            i1= Math.round(39*Math.random());
            i2= Math.round(39*Math.random());
            while(i1===i2) i2= Math.round(39*Math.random());
            temp= maz._carte[i1];
            maz._carte[i1]= maz._carte[i2];
            maz._carte[i2]= temp;
        }
    };
};

Mazzo.prototype.getCarte = function () {
    return this._carte.length;
};

Mazzo.prototype.getCarta = function () {
    return this._carte.pop();
};

Mazzo.prototype.carta = function (seme, valore) {
    var carta = new Carta(seme, valore, "", "");
    return this._carte.find(function (c) {
        return c.equals(carta);
    });
};

Mazzo.prototype.aggiungi = function (carta)
{
    this._carte.push(carta);
};

Mazzo.prototype.getImmagineCima= function()
{
    var img;
    if(this._carte.length > 0)
    {
        img= this._carte[this._carte.length-1].immagine;
    }
    else img= ".\\Trevisane\\vuota.png";
    
    return img;
};

Mazzo.COPERTA = "Immagini/coperta.jpg";
Mazzo.SEMI = new Enum(["bastoni", "coppe", "denari", "spade"], "Seme");
Mazzo.VALORI = new Enum(["1", "2", "3", "4", "5", "6", "7", "8", "9", "10"], "Valore");

///classe Mazzo con inserimento ordinato


///classe Mazzo con inserimento in ordine decrescente
