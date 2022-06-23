package Interfaces;

public class MainGUI extends Thread
{
    int BoardSize;

    MainGUI(int s)
    {
        this.BoardSize = s;
    }

    public void createGUI()
    {
        System.out.println("it worked");
    }

    public boolean setValueA()
    {
        // set GUI param A
        //....
        return true;
    }

    public void run()
    {
        createGUI();
    }
}

