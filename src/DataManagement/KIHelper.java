package DataManagement;

import java.util.ArrayList;

public class KIHelper {

    public boolean inProgress;  // Speichert, wenn ein Schiff gerade in Bearbeitung ist

    public Point start;  // Erste hit eines Schiffes
    public boolean left;  // falls die linke Seite des Schiffes abgearbeitet wurde
    public boolean right; // falls die rechte Seite des Schiffes abgearbeitet wurde
    public boolean top; // falls die obere Seite des Schiffes abgearbeitet wurde
    public boolean bottom; // falls die untere Seite des Schiffes abgearbeitet wurde

    public KIHelper()
    {
        this.inProgress = false;
        left = right = top = bottom = false;
    }
}
