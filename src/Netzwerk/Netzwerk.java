package Netzwerk;

enum Parameterwert
{
    size,
    ships,
    ready,
    shot,
    answer,
    save,
    load
}

public class Netzwerk
{
    void print(Parameterwert v)
    {
        System.out.println(v);
    }

    String communicate(Parameterwert v)
    {
        print(v);  //Komandozeilenausgabe zur Überprüfung
        //Sende Nachricht mit Parameter
        return "a";

    }
}
