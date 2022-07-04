package Database;
import DataManagement.GameData;

import java.io.*;
import java.util.List;

public class ScoreHandler {
    ObjectOutputStream objectOutputStream;
    FileOutputStream fileOutputStream;
    ObjectInputStream objectInputStream;
    FileInputStream fileInputStream;
    File f;

    public ScoreHandler()
    {

        objectOutputStream = null;
        fileOutputStream = null;
        fileInputStream = null;
        f = new File("Database/score.ser");
    }

    // Speichere daten ab
    public void serialization(GameData data) throws IOException { //TODO fix NotSerializableException problem

        fileOutputStream = new FileOutputStream(f, true);
        objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(data);

    }

    // lade gespeicherte Daten
    public GameData deserialization() throws IOException, ClassNotFoundException {

        fileInputStream = new FileInputStream(f);
        objectInputStream = new ObjectInputStream(fileInputStream);
        GameData readCase = (GameData) objectInputStream.readObject();
        return readCase;

    }

}
