function Enum(obj, classe) {
    var proto = {
        equals: function (elem) {
            if (elem === null) {
                throw new Exception("L'enumerato non � un oggetto");
            };
            if (elem.constructor !== this.constructor) {
                throw new Exception("L'oggetto non � un " + this.constructor);
            };
            return this.valore === elem.valore;
        },

        getValore: function () {
            return this.valore;
        },

        getNome: function () {
            return this.nome;
        }
    };
    this.enum = {};
    for (var el=0; el<obj.length; el++) {
        this.enum[obj[el]] = Object.create(proto);
        this.enum[obj[el]].valore = el;
        this.enum[obj[el]].nome = obj[el];
        this.enum[obj[el]].constructor = classe;
    }
}

Enum.prototype = {
    getElem: function (nome) {
        if (this.enum[nome]) {
            return this.enum[nome];
        }
        throw new Exception("Enumerato inesistente");
    },

    equals: function (enumerato) {
		if (enumerato===null || !enumerato){
			throw new Exception("Parametro di equals non fornito");
		}
		if (this.constructor!==enumerato.constructor){
			throw new Exception("Parametro di tipo differente");
		}
      return this.valore===enumerato.valore;
    },

    forEach: function (f, c) {
        for (var i in this.enum)
            f.call(c, this.enum[i]);
    }
};

