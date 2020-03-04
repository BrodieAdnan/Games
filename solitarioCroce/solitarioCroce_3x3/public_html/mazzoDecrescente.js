function MazzoDecrescente(semi, valori)
{
    this.init(semi, valori);
}
MazzoDecrescente.prototype = new Mazzo();
MazzoDecrescente.constructor = MazzoDecrescente;
MazzoDecrescente.prototype.getcarta= function()
{
    return this._carte.pop();
};
MazzoDecrescente.prototype.aggiungi= function(carta)
{
    var inserimento= false;
    if(this._carte.length===0)
    {
        this._carte.push(carta);
        inserimento= true;
    }
    else if(this._carte[this._carte.length-1].seme.valore !== carta.seme.valore && this._carte[this._carte.length-1].valore.valore === carta.valore.valore+1)
    {
        this._carte.push(carta);
        inserimento= true;
    }
    return inserimento;
};