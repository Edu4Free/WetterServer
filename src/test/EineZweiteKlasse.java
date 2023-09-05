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

import java.util.Scanner;   // Importieren der System-Klasse Scanner

public class EineZweiteKlasse // [1]
{
    public static void main(String[] args) // [2]
    {
        // Erzeugen eines Objektes von der Klasse Scanner. Der Name des Objektes ist 'eingabe'.
        // Mit diesem Objekt können über die Tastatur Zahlen und Zeichenketten (Texte) eingegeben werden.
        Scanner eingabe = new Scanner(System.in);

        int ergebnis=88, zahl1=1, zahl2=4; // deklarieren von 3 Integer-Variablen (ganze Zahlen)
        String name;                // Deklarieren einer Variable für Zeichenketten

        System.out.println("Bitte gib die 1. Zahl ein:"); // Aufforderung die 1. Zahl einzugeben
        //zahl1 = eingabe.nextInt();  // Aufruf der Methode 'nextInt' um eine Zahl aus dem Tastaturpuffer auszulesen

        System.out.println("Bitte gib die 2. Zahl ein:"); // Aufforderung die 2. Zahl einzugeben
        //zahl2 = eingabe.nextInt(); // Aufruf der Methode 'nextInt' um eine Zahl aus dem Tastaturpuffer auszulesen

        System.out.println("Und nun gib deinen Namen ein:"); // Aufforderung de Namen einzugeben
        name = eingabe.nextLine(); // Aufruf der Methode 'next' um einen Text aus dem Tastaturpuffer auszulesen //nextLine();?!

        //ergebnis = zahl1 + zahl2; // Zuweisen des Ergebnisses aus der Addition zweier Variablen

        // Ausgabe des Namens und Rechenergebnisses als Fließtext
        System.out.println("Hallo "+name+", das Ergebnis aus der Summe von " +zahl1+ " und "+zahl2+" lautet "+ergebnis);

        eingabe.close(); // Schließen des Objektes 'eingabe'
    }
}

// [1] https://studyflix.de/informatik/java-klassen-1902
// [2] https://studyflix.de/informatik/java-main-1790