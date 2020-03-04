function grafica() {
    this.createTable = function () {
        cel0 = document.getElementById("0");
        cel1 = document.getElementById("1");
        cel2 = document.getElementById("2");
        cel3 = document.getElementById("3");
        cel4 = document.getElementById("4");
        cel5 = document.getElementById("5");
        cel6 = document.getElementById("6");
        cel7 = document.getElementById("7");
        cel8 = document.getElementById("8");
        span = document.getElementById("areaScrittura");
        campo = [false, false, false, false, false, false, false, false, false];
        CPU = [false, false, false, false, false, false, false, false, false];
        utente = [false, false, false, false, false, false, false, false, false];
        span.innerHTML = "INIZIA IL GIOCO :";
    };

    function intelligentMove(x, y) {
        for (var i = 0; i < 9; i += 3) {
            if (x[i] == true && x[i + 1] == true) {
                if (!y[i + 2])
                    return i + 2;
            }
            if (x[i + 1] == true && x[i + 2] == true) {
                if (!y[i])
                    return i;
            }
            if (x[i] == true && x[i + 2] == true) {
                if (!y[i + 1])
                    return i + 1;
            }
        }
        for (var i = 0; i < 3; i++) {
            if (x[i] == true && x[i + 3] == true) {
                if (!y[i + 6])
                    return i + 6;
            }
            if (x[i + 6] == true && x[i + 3] == true) {
                if (!y[i])
                    return i;
            }
            if (x[i + 6] == true && x[i] == true) {
                if (!y[i + 3])
                    return i + 3;
            }
        }
        if (x[0] == true && x[4] == true) {
            if (!y[8])
                return 8;
        }
        if (x[8] == true && x[4] == true) {
            if (!y[0])
                return 0;
        }
        if (x[2] == true && x[4] == true) {
            if (!y[6])
                return 6;
        }
        if (x[6] == true && x[4] == true) {
            if (!y[2])
                return 2;
        }
        if (x[6] == true && x[2] == true) {
            if (!y[4])
                return 4;
        }
        if (x[8] == true && x[0] == true) {
            if (!y[4])
                return 4;
        }
    }

    function cpu() {
        span.innerHTML = "TURNO DEL COMPUTER!";
        setTimeout(function () {
            var mossaFinita = false;
            while (!mossaFinita) {
                var rand = intelligentMove(CPU, utente);
                if (rand == undefined) {
                    rand = intelligentMove(utente, CPU);
                    if (rand == undefined) {
                        rand = Math.floor(Math.random() * 9);
                    }
                }
                if (campo[rand] == false) {
                    campo[rand] = true;
                    CPU[rand] = true;
                    mossaFinita = true;
                    var cel = document.getElementById(rand);
                    document.getElementById("img" + cel.id).src = "o.gif";
                    if (controllo(CPU)) {
                        alert("Vince la CPU!");
                        span.innerHTML = "VINCE LA MACCHINA!";
                    } else if (controlloFinito()) {
                        alert("Gioco finito : NON HA VINTO NESSUNO!");
                        span.innerHTML = "PARITà!";
                    } else {
                        span.innerHTML = "TURNO DELL'UTENTE!";
                    }
                }
            }
        }, 500);
    }

    function controlloFinito() {
        var finito = true;
        for (var i = 0; i < 9; i++) {
            if (campo[i] == false) {
                finito = false;
            }
        }
        return finito;
    }

    this.callback = function () {
        if (campo[this.id] == false) {
            document.getElementById("img" + this.id).src = "x.gif";
            campo[this.id] = true;
            utente[this.id] = true;
            if (controllo(utente)) {
                alert("Vince l'utente!");
                span.innerHTML = "HA VINTO L'UTENTE!";
				document.location.reload();
            } else if (controlloFinito()) {
                alert("Gioco finito : NON HA VINTO NESSUNO!");
                span.innerHTML = "PARITà!";
				document.location.reload();
            } else {
                cpu();
            }
        } else {
            span.innerHTML = "NON PUOI SELEZIONARE UNA CELLA GIà PRESA!";
            setTimeout(function () {
                span.innerHTML = "RIPRENDE IL GIOCO!";
            }, 1500);
        }
    };
    this.play = function () {
        if (Math.floor(Math.random() * 2) == 0) {
            cpu();
        }
        span.innerHTML = "TURNO DELL'UTENTE!";
        cel0.addEventListener("click", this.callback);
        cel1.addEventListener("click", this.callback);
        cel2.addEventListener("click", this.callback);
        cel3.addEventListener("click", this.callback);
        cel4.addEventListener("click", this.callback);
        cel5.addEventListener("click", this.callback);
        cel6.addEventListener("click", this.callback);
        cel7.addEventListener("click", this.callback);
        cel8.addEventListener("click", this.callback);
    };

    function controllo(x) {
        for (var i = 0; i < 9; i += 3) {//controllo orizzontale
            if (x[i] == true && x[i + 1] == true && x[i + 2] == true) {
                return true;
            }
        }
        for (var i = 0; i < 3; i++) {//controllo verticale
            if (x[i] == true && x[i + 3] == true && x[i + 6] == true) {
                return true;
            }
        }
        if (x[0] == true && x[4] == true && x[8] == true) {//controllo diagonale
            return true;
        }
        if (x[2] == true && x[4] == true && x[6] == true) {//controllo diagonale
            return true;
        }
        return false;
    }
}
;