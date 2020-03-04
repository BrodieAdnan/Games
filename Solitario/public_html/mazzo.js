function Mazzo(semi, valori) {
    var carte = [];
    this.creaCarte = function (percorso, mazzo) {
        semi.forEach(function (s) { // Initializza l'array
            valori.forEach(function (v) {
                carte.push(new Carta(s, v, percorso + s.getNome()[0] + ("0" + (v.getValore()+1)).substring(String(v.getValore()+1).length - 1) + ".jpg", Mazzo.COPERTA, mazzo));
            });
        });
        mescola();
    };

    this.getCarte = function () {
        return carte.length;
    };

    this.getCarta = function (i) {
        return carte.pop();
    };

    this.carta = function (seme,valore) {
        var carta=new Carta(seme,valore,"","");
        return carte.find(function(c){return c.equals(carta);});
    };
    
    function mescola(){
    }
}

Mazzo.COPERTA = "Immagini/Coperta.jpg";
Mazzo.SEMI = new Enum(["cuori", "quadri", "fiori", "picche"], "Seme");
Mazzo.VALORI = new Enum(["A", "2", "3", "4", "5", "6", "7", "8", "9", "K"], "Valore");
