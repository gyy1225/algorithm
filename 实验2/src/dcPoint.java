

public class dcPoint implements Cloneable, Comparable<dcPoint>{

    public dcPoint() {

        x = 0;

        y = 0;

    }



    public dcPoint(int x, int y) {

        this.x = x;

        this.y = y;

    }



    public void setX(int x) {

        this.x = x;

    }



    public void setY(int y) {

        this.y = y;

    }



    public int getX() {

        return x;

    }



    public int getY() {

        return y;

    }



    private int x;

    private int y;



    @Override

    public int compareTo(dcPoint o) {

        if(x == o.getX() && y == o.getY())

            return 0;

        else

            return 1;

    }

}

