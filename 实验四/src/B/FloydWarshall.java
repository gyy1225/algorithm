package B;

public class FloydWarshall {


    /**
     * 邻接矩阵
     */

    private int[][] matrix;

    /**
     * 表示正无穷
     */

    private int MAX_WEIGHT = Integer.MAX_VALUE;

    /**
     * 路径矩阵
     */

    private int[][] pathMatirx;

    /**
     * 前驱表
     */

    private int[][] preTable;


    /**
     * 创建图2
     */

    private void createGraph2(int index) {

        matrix = new int[index][index];


        int[] v1 = {0, 3, 8, MAX_WEIGHT, -4};

        int[] v2 = {MAX_WEIGHT, 0, MAX_WEIGHT, 1, 7};

        int[] v3 = {MAX_WEIGHT, 4, 0, MAX_WEIGHT, MAX_WEIGHT};

        int[] v4 = {2, MAX_WEIGHT, -5, 0, MAX_WEIGHT};

        int[] v5 = {MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 6, 0};


        matrix[0] = v1;

        matrix[1] = v2;

        matrix[2] = v3;

        matrix[3] = v4;

        matrix[4] = v5;


    }


    public void floyd() {

        //路径矩阵（D），表示顶点到顶点的最短路径权值之和的矩阵，初始时，就是图的邻接矩阵。

        pathMatirx = new int[matrix.length][matrix.length];

        //前驱表（P），P[m][n] 的值为 m到n的最短路径的前驱顶点，如果是直连，值为n。也就是初始值

        preTable = new int[matrix.length][matrix.length];


        //初始化D,P

        for (int i = 0; i < matrix.length; i++) {

            for (int j = 0; j < matrix.length; j++) {

                pathMatirx[i][j] = matrix[i][j];

                preTable[i][j] = j;

            }

        }


        //循环 中间经过顶点

        for (int k = 0; k < matrix.length; k++) {

            //循环所有路径

            for (int m = 0; m < matrix.length; m++) {


                for (int n = 0; n < matrix.length; n++) {


                    int mn = pathMatirx[m][n];

                    int mk = pathMatirx[m][k];

                    int kn = pathMatirx[k][n];

                    int addedPath = (mk == MAX_WEIGHT || kn == MAX_WEIGHT) ? MAX_WEIGHT : mk + kn;


                    if (mn > addedPath) {

                        //如果经过k顶点路径比原两点路径更短，将两点间权值设为更小的一个

                        pathMatirx[m][n] = addedPath;

                        //前驱设置为经过下标为k的顶点

                        preTable[m][n] = preTable[m][k];

                    }


                }

            }

        }

    }


    /**
     * 打印 所有最短路径
     */

    public void print() {


        for (int m = 0; m < matrix.length; m++) {

            for (int n = m + 1; n < matrix.length; n++) {


                int k = preTable[m][n];

                System.out.print("(" + m + "," + n + ")" + pathMatirx[m][n] + ":  ");

                System.out.print(m);

                while (k != n) {

                    System.out.print("->" + k);

                    k = preTable[k][n];

                }


                System.out.println("->" + n);

            }

            System.out.println();

        }
        for (int m = 4; m >=0; m--) {

            for (int n = m - 1; n>=0; n--) {

                int k = preTable[m][n];

                System.out.print("(" + m + "," + n + ")" + pathMatirx[m][n] + ":  ");

                System.out.print(m);

                while (k != n) {

                    System.out.print("->" + k);

                    k = preTable[k][n];

                }


                System.out.println("->" + n);

            }

            System.out.println();

        }

        for (int i = 0; i < pathMatirx.length; i++) {
            for (int j = 0; j < pathMatirx.length; j++) {
              /* if (matrix[i][j]==MAX_WEIGHT){

               }*/
                System.out.print(pathMatirx[i][j] + "  ");

            }
            System.out.println();
        }

    }


    public static void main(String[] args) {

        FloydWarshall floyd = new FloydWarshall();

        floyd.createGraph2(5);

//        floyd.createGraph1(6);


        floyd.floyd();


        floyd.print();


    }
}
