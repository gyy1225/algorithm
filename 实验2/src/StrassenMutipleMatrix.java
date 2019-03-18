import java.util.Scanner;

/**
 *
 *
 */
public class StrassenMutipleMatrix {

    public static void matrixSub(int[][] matrixA, int[][] matrixB, int[][] result){
        for(int i = 0; i < matrixA.length; i++)
            for(int j = 0; j < matrixA.length; j++)
                result[i][j] = matrixA[i][j] - matrixB[i][j];
    }
    public static void matrixAdd(int[][] matrixA, int[][] matrixB, int[][] result){
        for(int i = 0; i < matrixA.length; i++)
            for(int j = 0; j < matrixA.length; j++)
                result[i][j] = matrixA[i][j] + matrixB[i][j];
    }
    public static void printMatrix(int n,int [][] p){
        for (int i=0;i<n;i++){
            for (int j=0;j<n;j++
                 ) {
                if(j != n - 1) System.out.print(p[i][j] + " ");
                else           System.out.println(p[i][j]);
            }}
    }
    public static void Strassen(int N, int[][] matrixA, int[][] matrixB, int[][] result){
        if(N == 1){
            result[0][0] = matrixA[0][0] * matrixB[0][0];
            return;
        }
        int halfSize = N / 2;

        int[][] A = new int[halfSize][halfSize];
        int[][] B = new int[halfSize][halfSize];
        int[][] C = new int[halfSize][halfSize];
        int[][] D = new int[halfSize][halfSize];
        int[][] E = new int[halfSize][halfSize];
        int[][] F = new int[halfSize][halfSize];
        int[][] G = new int[halfSize][halfSize];
        int[][] H = new int[halfSize][halfSize];
        int[][] C1 = new int[halfSize][halfSize];
        int[][] C2 = new int[halfSize][halfSize];
        int[][] C3 = new int[halfSize][halfSize];
        int[][] C4 = new int[halfSize][halfSize];
        int[][] S1 = new int[halfSize][halfSize];
        int[][] S2 = new int[halfSize][halfSize];
        int[][] S3 = new int[halfSize][halfSize];
        int[][] S4 = new int[halfSize][halfSize];
        int[][] S5 = new int[halfSize][halfSize];
        int[][] S6 = new int[halfSize][halfSize];
        int[][] S7 = new int[halfSize][halfSize];
      /*  int[][] S1 = new int[halfSize][halfSize];
        int[][] S2 = new int[halfSize][halfSize];
        int[][] S3 = new int[halfSize][halfSize];
        int[][] S4 = new int[halfSize][halfSize];
        int[][] S5 = new int[halfSize][halfSize];
        int[][] S6 = new int[halfSize][halfSize];
        int[][] S7 = new int[halfSize][halfSize];*/

        int[][] tempA = new int[halfSize][halfSize];
        int[][] tempB = new int[halfSize][halfSize];
        for(int i = 0; i < halfSize; i++)
            for(int j = 0; j < halfSize; j++){
                A[i][j] = matrixA[i][j];
                B[i][j] = matrixA[i][halfSize + j];
                C[i][j] = matrixA[i + halfSize][j];
                D[i][j] = matrixA[i + halfSize][j + halfSize];

                E[i][j] = matrixB[i][j];
                F[i][j] = matrixB[i][halfSize + j];
                G[i][j] = matrixB[i + halfSize][j];
                H[i][j] = matrixB[i + halfSize][j + halfSize];
            }
        matrixSub(F,H,tempB);
        Strassen(halfSize,A,tempB,S1);


        matrixAdd(A,B,tempA);
        Strassen(halfSize,tempA,H,S2);
      //  printMatrix(halfSize,S2);

        matrixAdd(C,D,tempA);
        Strassen(halfSize,tempA,E,S3);
       // printMatrix(halfSize,S3);

        matrixSub(G,E,tempB);
        Strassen(halfSize,D,tempB,S4);
      //  printMatrix(halfSize,S4);

        matrixAdd(A,D,tempA);
        matrixAdd(E,H,tempB);
        Strassen(halfSize,tempA,tempB,S5);
       // printMatrix(halfSize,S5);

        matrixSub(B,D,tempA);
        matrixAdd(G,H,tempB);
        Strassen(halfSize,tempA,tempB,S6);

      //  printMatrix(halfSize,S6);
        matrixSub(A,C,tempA);
        matrixAdd(E,F,tempB);
        Strassen(halfSize,tempA,tempB,S7);
      //  printMatrix(halfSize,S7);

        matrixAdd(S5,S4,C1);
        matrixSub(C1,S2,C1);
        matrixAdd(C1,S6,C1);

        matrixAdd(S1,S2,C2);

        matrixAdd(S3,S4,C3);

        matrixAdd(S5,S1,C4);
        matrixSub(C4,S3,C4);
        matrixSub(C4,S7,C4);

        for(int i = 0; i < halfSize; i++)
            for(int j = 0; j < halfSize; j++){
                result[i][j] = C1[i][j];
                result[i][j + halfSize] = C2[i][j];
                result[i + halfSize][j] = C3[i][j];
                result[i + halfSize][j + halfSize] = C4[i][j];
            }
        if (halfSize==8){
            printMatrix(halfSize,S1);
            System.out.println();
            printMatrix(halfSize,S2);
            System.out.println();
            printMatrix(halfSize,S3);
            System.out.println();
            printMatrix(halfSize,S4);
            System.out.println();
            printMatrix(halfSize,S5);
            System.out.println();
            printMatrix(halfSize,S6);
            System.out.println();
            printMatrix(halfSize,S7);
            System.out.println();
        }
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner input = new Scanner(System.in);
        while(input.hasNext()){
            int n = input.nextInt();
            int[][] matrixA = new int[n][n];
            int[][] matrixB = new int[n][n];
            int[][] result = new int[n][n];

            for(int i = 0; i < n; i++)
                for(int j = 0; j < n; j++)
                    matrixA[i][j] = 1;
            for(int i = 0; i < n; i++)
                for(int j = 0; j < n; j++)
                    matrixB[i][j] = 1;
            Strassen(n,matrixA,matrixB,result);
          //  Strassen(n/2,A,tempB,S1);
         //  printMatrix(n/2,S1);

            for(int i = 0; i < n; i++)
                for(int j = 0; j < n; j++){
                    if(j != n - 1) System.out.print(result[i][j] + " ");
                    else           System.out.println(result[i][j]);
                }
        }
    }

}
