function Gestore() {
    this.mazzo = new Mazzo();
    this.mazzo.creaCarte("Trevisane\\");
    this.mazzo.mescola();
    this.grafica = new Grafica();
    this.crescente = new Array(4);
    this.crescente.fill(new MazzoCrescente());
    this.decrescente = new Array(5);
    this.decrescente.fill(new MazzoDecrescente());
    this.scarti = new Mazzo();
    this.grafica.start(this.mazzo, this.scarti, this.crescente, this.decrescente);
}