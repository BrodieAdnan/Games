/********************************************
 *GIOCO CONOMETO
 *@author Gazi adnan Latif
 *@brief utilizzo della classe 'Cronometro'
 *@date 26/04/2017
*********************************************/

const int led = 13;
int randomN = 0;
int inserito;
bool fine = true;

void setup() {
  pinMode(led, OUTPUT);
  Serial.begin(9600);
  randomSeed(analogRead(0));
}

class Cronometro {
  public :
    Cronometro() {
      tempTot = 0;
      fermo = true;
    }
    void start() {
      if (fermo) {
        tempI = millis();
        fermo = false;
      }
    }
    void stop() {
      if (!fermo) {
        fermo = true;
        tempTot += ((millis() - tempI) / 1000);
      }
    }
    void reset() {
      if (fermo) {
        tempTot = 0;
      }
    }
    float time() {
      return tempTot;
    }
  private :
    float tempI;
    float tempTot;
    bool fermo;
};

void loop() {
  Cronometro crono;
  Serial.println("Inserisci il tuo numero compreso tra 0 e 10 e cerca di indovinare...");
  randomN = random(11);
  while (!Serial.available());
  inserito = Serial.parseInt();
  crono.start();
  while (fine) {
    if (inserito == randomN) {
      crono.stop();
      Serial.println("Hai indovinato!");
      Serial.println(crono.time());
      fine = false;
    } else {
      Serial.println("Numero SBAGLIATO, inserisc1 ancora...");
      while (!Serial.available());
      inserito = Serial.parseInt();
    }
  }
  delay(5000);
}

int inserimento() {
  inserito = Serial.parseInt();
  return inserito;
}
