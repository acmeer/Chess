package main;

import java.util.Scanner;

/**
 * 五子棋
 * 15*15
 */
public class GoBang {

    public static String BLACK = "●";
    public static String WHITE = "o";

    public static void main(final String[] args) {

        //棋盘 (0:空 1:黑 2:白)
        final int[][] a = new int[15][15];
        a[7][7] = 1;
        //画棋盘
        checkerboard(a);
        System.out.println("PLEASE ENTER WHITE:");
        final Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            final int x = scanner.nextInt();
            final int y = scanner.nextInt();
            if (a[x][y] != 0) {
                System.out.println("ERROR,please input again");
                continue;
            }
            a[x][y] = 2;
            checkerboard(a);
            //判断是否胜利
            if (gameOver(a, x, y, 2) == 1) {
                System.out.println("WHITE SUCCESS");
                return;
            }
            //随机输入黑子
            randomInput(a);
            checkerboard(a);
            //判断是否胜利
            if (gameOver(a, x, y, 1) == 1) {
                System.out.println("BLACK SUCCESS");
                return;
            }
            System.out.println("PLEASE ENTER WHITE:");
        }
    }

    //画棋盘
    private static void checkerboard(final int[][] a){
        for(int j=14;j>=0;j--){
            if(j<10) {
                System.out.print(" " + j + " ");
            }else{
                System.out.print(j + " ");
            }
            for (int i=0;i<15;i++){
                if (a[i][j] == 0){
                    System.out.print("  ");
                }else if(a[i][j] == 1){
                    System.out.print(BLACK + " ");
                }else{
                    System.out.print(WHITE + " ");
                }
            }
            System.out.println();
        }
    }

    //随机输入
    private static int[][] randomInput(final int[][] a) {
        final int x = (int) ((Math.random() * 15) / 1);
        final int y = (int) ((Math.random() * 15) / 1);
        if (a[x][y] != 0) {
            return randomInput(a);
        }
        System.out.println("BLACK:" + x + " " + y);
        a[x][y] = 1;
        return a;
    }

    //判断是否胜利
    private static int gameOver(final int[][] a, final int x, final int y, final int color) {
        //横排
        int sum = 1;
        for (int i = 1; i <= 5; i++) {
            if (a[x - i][y] != color || x - i < 0) {
                break;
            }
            sum++;
        }
        for (int i = 1; i <= 5; i++) {
            if (a[x + i][y] != color || x + i < 0) {
                break;
            }
            sum++;
        }
        if (sum == 5) return 1;
        //竖排
        sum = 1;
        for (int i = 1; i <= 5; i++) {
            if (a[x][y - i] != color || y - i < 0) {
                break;
            }
            sum++;
        }
        for (int i = 1; i <= 5; i++) {
            if (a[x][y + i] != color || y + i < 0) {
                break;
            }
            sum++;
        }
        if (sum == 5) return 1;
        // /排
        sum = 1;
        for (int i = 1; i <= 5; i++) {
            if (a[x - i][y - i] != color || x - i < 0 || y - i < 0) {
                break;
            }
            sum++;
        }
        for (int i = 1; i <= 5; i++) {
            if (a[x + i][y + i] != color || x + i < 0 || y + i < 0) {
                break;
            }
            sum++;
        }
        if (sum == 5) return 1;
        // \排
        sum = 1;
        for (int i = 1; i <= 5; i++) {
            if (a[x - i][y + i] != color || x - i < 0 || y + i < 0) {
                break;
            }
            sum++;
        }
        for (int i = 1; i <= 5; i++) {
            if (a[x + i][y - i] != color || x + i < 0 || y - i < 0) {
                break;
            }
            sum++;
        }
        return sum == 5 ? 1 : 0;
    }

}
