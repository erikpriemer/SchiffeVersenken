package DataManagement;

import java.util.ArrayList;

public class KIHelper {

    public boolean inProgress;

    public Point start;
    public boolean left;
    public boolean right;
    public boolean top;
    public boolean bottom;

    public KIHelper()
    {
        this.inProgress = false;
        left = right = top = bottom = false;
    }
}
