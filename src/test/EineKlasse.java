package test; /**
 * Aufbau der Klassen [1]
 * Die Klasse wird in der ersten Zeile deklariert. In diesem Fall legt "public" fest,
 * dass auch von außerhalb auf die Klasse zugegriffen werden kann.
 * Es folgt das Schlüsselwort class und der Name der Klasse.
 * In Java ist vorgeschrieben (Code-Conventionen), dass der erste Buchstabe des KlassennNamens groß geschrieben wird.
 * Abschließend kommen die {}-Klammern, die den Beginn und das Ende des eigentliche Programmcodes einschliessen.
 */

/**
 * Aufbau der Mein-Methode [2]
 * Die Main Methode ist der Startpunkt für die Ausführung des Codes.
 * Daher hat jede Java Anwendung eine solche Main-Methode. Die Main-Methode ist folgendermaßen aufgebaut:
 *
 *     public: Bedeutet, dass auch von anderen Klassen auf diese Methode zugegreifen können (Sichtbar/Öffentlich).
 *     static: Allgemeinert bedeute static, dass die Methode statisch ist, das bedeutet, sie ist unabhängig von der Klasse
 *     in der sie steht aufrufbar.
 *     void: legt fest, dass die Methode keinen Rückgabewert beinhaltet.
 *     main(): Ist der Name für die Main Methode.
 *     String[] args: Optionale Werte, die beim Aufruf des Java-Programms auf der Kommandozeile eingegeben werden,
 *     werden hierdurch in Form eines Arrays gespeichert.
 */
public class EineKlasse // [1]
{
    public static void main(String[] args) // [2]
    {
        int ergebnis;   // deklarieren einer Variablen
        int zahl1 = 5;  // deklarieren einer Variablen mit anschließender Wertzuweisung (Initialisierung)
        int zahl2 = 6;  // Siehe oben;

        ergebnis = zahl1 + zahl2; // Zuweisen des Ergebnisses aus der Addition zweier Variablen

        // Ausgabe des Rechenergebnisses als Fließtext
        System.out.println("Das Ergebnis aus der Summe von " +zahl1+ " und "+zahl2+" lautet "+ergebnis);
    }
}

// [1] https://studyflix.de/informatik/java-klassen-1902
// [2] https://studyflix.de/informatik/java-main-1790
