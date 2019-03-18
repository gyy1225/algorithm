

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;

import java.util.Random;

import java.util.Set;

import java.util.TreeSet;



public class NearestPoint  {

    /**

     * 最近点问题

     * @param S

     */

    public static dcPoint[] closestPoint(dcPoint [] S){



        dcPoint[] result = new dcPoint[2];

        /**

         * 0.首先，解决该问题的边界，当数组长度在一定范围内时直接求出最近点，蛮力求解 

         */

        double dmin = Double.POSITIVE_INFINITY;

        double tmpmin = 0;

        if(S.length <= 20){

            for(int i = 0; i < S.length; i ++){

                for(int j = i + 1; j < S.length; j ++){

                    tmpmin = Math.sqrt(Math.pow(S[i].getX() - S[j].getX(), 2)) + Math.pow(S[i].getY() - S[j].getY(), 2);

                    if(tmpmin < dmin){

                        dmin = tmpmin;

                        result[0] = S[i];

                        result[1] = S[j];

                    }

                }

            }

            return result;

        }



        /**

         *1.求所有点在X坐标的中位数 

         */

        int minX = (int) Double.POSITIVE_INFINITY;		//保证假设的初始最小值足够大

        int maxX = (int) Double.NEGATIVE_INFINITY;		//保证假设的初始最大值足够小

        for(int i = 0; i < S.length; i++){

            if(S[i].getX() < minX)

                minX = S[i].getX();

            if(S[i].getX() > maxX)

                maxX = S[i].getX();

        }

        int midX = (minX + maxX)/2;



        /**

         * 2.以midX为界将所有点分成两组分别存放在两个表中

         */

        ArrayList T1 = new ArrayList();

        ArrayList T2 = new ArrayList();

        for(int i = 0; i < S.length; i++){

            if(S[i].getX() <= midX)		//是否要=号？

                T1.add(S[i]);

            if(S[i].getX() > midX)

                T2.add(S[i]);

        }



        /**

         * 3.将两张表转化为数组类型，并分别按X坐标升序排列

         */

        dcPoint [] S1 = new dcPoint[T1.size()];

        dcPoint [] S2 = new dcPoint[T2.size()];



        T1.toArray(S1);

        T2.toArray(S2);



        mergeSort(S1, "x");		//按X坐标升序排列

        mergeSort(S2, "x");		//按X坐标升序排列



        /**

         * 4.求S1中的最近距离的两个点

         */

        dcPoint[] result1 = new dcPoint[2];

        result1 = closestPoint(S1);



        /**

         * 5.求S2中的最近距离的两个点

         */

        dcPoint[] result2 = new dcPoint[2];

        result2 = closestPoint(S2);



        /**

         * 6.求两最近距离的最小值

         */

        double d1 = Math.sqrt(Math.min(Math.pow(result1[0].getX() - result1[1].getX(), 2) + Math.pow(result1[0].getY() - result1[1].getY(), 2),

                Math.pow(result2[0].getX() - result2[1].getX(), 2) + Math.pow(result2[0].getY() - result2[1].getY(), 2)));



        if(Math.pow(result1[0].getX() - result1[1].getX(), 2) + Math.pow(result1[0].getY() - result1[1].getY(), 2) <

                Math.pow(result2[0].getX() - result2[1].getX(), 2) + Math.pow(result2[0].getY() - result2[1].getY(), 2))

            result = result1;

        else

            result = result2;



        /**

         * 7.在S1、S2中收集距离中线小于d1的点，分别存放于两个表中

         */

        ArrayList T3 = new ArrayList();

        ArrayList T4 = new ArrayList();

        for(int i = 0; i < S1.length; i++){

            if(midX - S1[i].getX() < d1)

                T3.add(S1[i]);

        }

        for(int i = 0; i < S2.length; i++){

            if(S2[i].getX() - midX < d1){

                T4.add(S2[i]);

            }

        }



        /**

         * 8.分别将表T3、T4转换为数组类型的S3、S4，并将其分别按Y坐标升序排列

         */

        dcPoint [] S3 = new dcPoint [T3.size()];

        dcPoint [] S4 = new dcPoint [T4.size()];

        T3.toArray(S3);

        T4.toArray(S4);



        mergeSort(S3, "y");

        mergeSort(S4, "y");



        /**

         * 求解S3、S4两者之间可能的更近（相比于d1）距离 , 以及构成该距离的点

         */

        double d =  Double.POSITIVE_INFINITY;

        for(int i = 0; i < S3.length; i ++){

            for(int j = 0; j < S4.length; j ++){

                if(Math.abs(S3[i].getY() - S4[j].getY()) < d1){

                    double tmp = Math.sqrt(Math.pow(S3[i].getX() - S4[j].getX(), 2) + Math.pow(S3[i].getY() - S4[j].getY(), 2));

                    if(tmp < d){

                        d = tmp;

                        result[0] = S3[i];

                        result[1] = S4[j];

                    }

                }

            }

        }



        return result;

    }

    static class ShapesPanel extends JPanel {
        public void SharpesPanel() {
            setBackground(Color.white);
        }
        public void paintComponent(Graphics g,dcPoint [] S){
            for(int i = 0; i < S.length; i ++){
                g.drawLine(S[i].getX(),S[i].getY(),S[i].getX()+1,S[i].getY()+1);
            }
        }
    }

    static class GraphicsDemo extends JFrame {
        public void GraphicsDemo(){
            setSize(300, 300);
            setVisible(true);
        }
    }

    private static void mergeSort(dcPoint[] a, String property){

        dcPoint[] tempArray = new dcPoint[a.length];

        mergeSort(a, tempArray, 0, a.length - 1, property);

    }



    private static void mergeSort(dcPoint[] a, dcPoint [] tempArray, int left, int right, String property){

        if(left < right){

            int center = (left + right) >> 1;

            //分治

            mergeSort(a, tempArray, left, center, property);

            mergeSort(a, tempArray, center + 1, right, property);

            //合并

            merge(a, tempArray, left, center + 1, right, property);

        }

    }



    private static void merge(dcPoint [] a, dcPoint [] tempArray, int leftPos, int rightPos, int rightEnd, String property){

        int leftEnd = rightPos - 1;

        int numOfElements = rightEnd - leftPos + 1;



        int tmpPos = leftPos;		//游标变量, 另两个游标变量分别是leftPos 和 rightPos



        while(leftPos <= leftEnd && rightPos <= rightEnd){

            if(property.equals("x")){

                if(a[leftPos].getX() <= a[rightPos].getX())

                    tempArray[tmpPos++] = a[leftPos++];

                else

                    tempArray[tmpPos++] = a[rightPos++];

            }else if(property.equals("y")){

                if(a[leftPos].getY() <= a[rightPos].getY())

                    tempArray[tmpPos++] = a[leftPos++];

                else

                    tempArray[tmpPos++] = a[rightPos++];

            }else

                throw new RuntimeException();

        }



        while(leftPos <= leftEnd)

            tempArray[tmpPos++] = a[leftPos++];

        while(rightPos <= rightEnd)

            tempArray[tmpPos++] = a[rightPos++];



        //将排好序的段落拷贝到原数组中

        System.arraycopy(tempArray, rightEnd-numOfElements+1, a, rightEnd-numOfElements+1, numOfElements);

    }



    public static void main(String[] args) {



        Set<dcPoint> testData = new TreeSet<dcPoint>();



        Random random = new Random();



        int x = 0;

        int y = 0;



        for(int i = 0;i < 100;i++){

            x = random.nextInt(500);

            y = random.nextInt(500);

            System.out.println("x:" + x + "  y:" + y);

            testData.add(new dcPoint(x, y));

        }



        dcPoint [] S = new dcPoint[testData.size()];

        S = (dcPoint[]) testData.toArray(S);
        Graphics graphics=new Graphics() {
            @Override
            public Graphics create() {
                return null;
            }

            @Override
            public void translate(int x, int y) {

            }

            @Override
            public Color getColor() {
                return null;
            }

            @Override
            public void setColor(Color c) {

            }

            @Override
            public void setPaintMode() {

            }

            @Override
            public void setXORMode(Color c1) {

            }

            @Override
            public Font getFont() {
                return null;
            }

            @Override
            public void setFont(Font font) {

            }

            @Override
            public FontMetrics getFontMetrics(Font f) {
                return null;
            }

            @Override
            public Rectangle getClipBounds() {
                return null;
            }

            @Override
            public void clipRect(int x, int y, int width, int height) {

            }

            @Override
            public void setClip(int x, int y, int width, int height) {

            }

            @Override
            public Shape getClip() {
                return null;
            }

            @Override
            public void setClip(Shape clip) {

            }

            @Override
            public void copyArea(int x, int y, int width, int height, int dx, int dy) {

            }

            @Override
            public void drawLine(int x1, int y1, int x2, int y2) {

            }

            @Override
            public void fillRect(int x, int y, int width, int height) {

            }

            @Override
            public void clearRect(int x, int y, int width, int height) {

            }

            @Override
            public void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {

            }

            @Override
            public void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {

            }

            @Override
            public void drawOval(int x, int y, int width, int height) {

            }

            @Override
            public void fillOval(int x, int y, int width, int height) {

            }

            @Override
            public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle) {

            }

            @Override
            public void fillArc(int x, int y, int width, int height, int startAngle, int arcAngle) {

            }

            @Override
            public void drawPolyline(int[] xPoints, int[] yPoints, int nPoints) {

            }

            @Override
            public void drawPolygon(int[] xPoints, int[] yPoints, int nPoints) {

            }

            @Override
            public void fillPolygon(int[] xPoints, int[] yPoints, int nPoints) {

            }

            @Override
            public void drawString(String str, int x, int y) {

            }

            @Override
            public void drawString(AttributedCharacterIterator iterator, int x, int y) {

            }

            @Override
            public boolean drawImage(Image img, int x, int y, ImageObserver observer) {
                return false;
            }

            @Override
            public boolean drawImage(Image img, int x, int y, int width, int height, ImageObserver observer) {
                return false;
            }

            @Override
            public boolean drawImage(Image img, int x, int y, Color bgcolor, ImageObserver observer) {
                return false;
            }

            @Override
            public boolean drawImage(Image img, int x, int y, int width, int height, Color bgcolor, ImageObserver observer) {
                return false;
            }

            @Override
            public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, ImageObserver observer) {
                return false;
            }

            @Override
            public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, Color bgcolor, ImageObserver observer) {
                return false;
            }

            @Override
            public void dispose() {

            }
        };
        GraphicsDemo myGraphicsFrame = new GraphicsDemo();
        NearestPoint.ShapesPanel shapesPanel =new NearestPoint.ShapesPanel();
        shapesPanel.paintComponent(graphics,S);
        myGraphicsFrame.getContentPane().add(shapesPanel);



        for(int i = 0; i < S.length; i ++){

            System.out.println("(" + S[i].getX() + ", " + S[i].getY() + ")");
            graphics.drawLine(S[i].getX(),S[i].getY(),S[i].getX()+1,S[i].getY()+1);
        }



        System.out.println(testData.size());

        System.out.println("蛮力法：");
        double d = 10000;
        for (int i = 0; i < S.length; i++) {
            for (int j = i+1; j < S.length; j++) {
                d = Math.min(d, Math.sqrt(Math.pow(S[i].getX()-S[j].getX(), 2)+Math.pow(S[i].getY()-S[j].getY(), 2)));
            }
        }
        System.out.println(d);
        System.out.println("分治法：");
        dcPoint [] result = new dcPoint [2];
        result = closestPoint(S);
        System.out.println("最近的两点分别是(" + result[0].getX() + ", " + result[0].getY()

                + ") 和 (" + result[1].getX() + ", " + result[1].getY() + "), 最近距离为："

                + Math.sqrt(Math.pow(result[0].getX() - result[1].getX(), 2) + Math.pow(result[0].getY() - result[1].getY(), 2)));




    }

}
