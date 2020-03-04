function MazzoOrdinato(semi, valori)
{
    this.init(semi, valori);
};
MazzoOrdinato.prototype = new Mazzo();
MazzoOrdinato.constructor = MazzoOrdinato;
MazzoOrdinato.prototype.getcarta= function()
{
    return this._carte.pop();
};
MazzoOrdinato.prototype.aggiungi = function (carta)
{
    var inserimento= false;
    if(carta.valore.valore===0 && this._carte.length===0)
    {
        this._carte.push(carta);
        inserimento= true;
    }
    else if(this._carte.length>0 && this._carte[this._carte.length-1].seme.valore===carta.seme.valore && this._carte[this._carte.length-1].valore.valore===(carta.valore.valore-1))
    {
        this._carte.push(carta);
        inserimento= true;
    }
    return inserimento;
};