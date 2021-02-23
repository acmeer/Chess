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
            if (checkingInputOne(a, x, y) == 0) {
                System.out.println("ERROR,please input again");
                continue;
            }
            a[x][y] = 2;
            checkerboard(a);
            //判断是否胜利
            if (gameOver(a, x, y, 2, 5) == 1) {
                System.out.println("WHITE SUCCESS");
                return;
            }
            //随机输入黑子
            InputOne(a, x, y);
            checkerboard(a);
            //判断是否胜利
            if (gameOver(a, x, y, 1, 5) == 1) {
                System.out.println("BLACK SUCCESS");
                return;
            }
            System.out.println("PLEASE ENTER WHITE:");
        }
    }

    //画棋盘
    private static void checkerboard(final int[][] a) {
        for (int j = 14; j >= 0; j--) {
            if (j < 10) {
                System.out.print(" " + j + " ");
            } else {
                System.out.print(j + " ");
            }
            for (int i = 0; i < 15; i++) {
                if (a[i][j] == 0) {
                    System.out.print("  ");
                } else if (a[i][j] == 1) {
                    System.out.print(BLACK + " ");
                } else {
                    System.out.print(WHITE + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    //输入方式：当白子三个连成一排就堵
    private static int[][] InputOne(final int[][] a, final int whiteX, final int whiteY) {
//        if (gameOver(a, whiteX, whiteY, 2, 3) == 1) {
//            if (gameOverOne(a, whiteX, whiteY, 2, 3)) {
//
//            }
//        } else {
        final int x = (int) ((Math.random() * 15) / 1);
        final int y = (int) ((Math.random() * 15) / 1);
        if (a[x][y] != 0 || !(checkingInputTwo(a, x, y, 1))) {
            return InputOne(a, whiteX, whiteY);
        }
        System.out.println("BLACK:" + x + " " + y);
        a[x][y] = 1;
        return a;
//        }
    }

    //检查输入范围是否正确
    private static int checkingInputOne(final int[][] a, final int x, final int y) {
        return (a[x][y] != 0 || x > 14 || y > 14) ? 0 : 1;
    }

    //检查周围n个格子内是否有棋子
    private static boolean checkingInputTwo(final int[][] a, final int x, final int y, final int n) {
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 && j == 0) continue;
                if (x + i <= 14 && y + i <= 14 && a[x + i][y + i] != 0) {
                    return true;
                } else if (x - i >= 0 && y + i <= 14 && a[x - i][y + i] != 0) {
                    return true;
                } else if (x - i >= 0 && y - i >= 0 && a[x - i][y - i] != 0) {
                    return true;
                } else if (x + i <= 14 && y - i >= 0 && a[x + i][y - i] != 0) {
                    return true;
                }
            }
        }
        return false;
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

    //判断是否连成n个(1:是 0:否)
    private static int gameOver(final int[][] a, final int x, final int y, final int color, final int n) {
        if (gameOverOne(a, x, y, color, n)) return 1;
        if (gameOverTwo(a, x, y, color, n)) return 1;
        if (gameOverThree(a, x, y, color, n)) return 1;
        if (gameOverFour(a, x, y, color, n)) return 1;
        return 0;
    }

    //判断是否连成n个(1:是 0:否)-横排
    private static boolean gameOverOne(final int[][] a, final int x, final int y, final int color, final int n) {
        int sum = 1;
        for (int i = 1; i <= n; i++) {
            if (a[x - i][y] != color || x - i < 0) {
                break;
            }
            sum++;
        }
        for (int i = 1; i <= n; i++) {
            if (a[x + i][y] != color || x + i < 0) {
                break;
            }
            sum++;
        }
        return sum == n;
    }

    //判断是否连成n个(1:是 0:否)-竖排
    private static boolean gameOverTwo(final int[][] a, final int x, final int y, final int color, final int n) {
        int sum = 1;
        for (int i = 1; i <= n; i++) {
            if (a[x][y - i] != color || y - i < 0) {
                break;
            }
            sum++;
        }
        for (int i = 1; i <= n; i++) {
            if (a[x][y + i] != color || y + i < 0) {
                break;
            }
            sum++;
        }
        return sum == n;
    }

    //判断是否连成n个(1:是 0:否)-/排
    private static boolean gameOverThree(final int[][] a, final int x, final int y, final int color, final int n) {
        int sum = 1;
        for (int i = 1; i <= n; i++) {
            if (a[x - i][y - i] != color || x - i < 0 || y - i < 0) {
                break;
            }
            sum++;
        }
        for (int i = 1; i <= n; i++) {
            if (a[x + i][y + i] != color || x + i < 0 || y + i < 0) {
                break;
            }
            sum++;
        }
        return sum == n;
    }

    //判断是否连成n个(1:是 0:否)-\排
    private static boolean gameOverFour(final int[][] a, final int x, final int y, final int color, final int n) {
        int sum = 1;
        for (int i = 1; i <= n; i++) {
            if (a[x - i][y - i] != color || x - i < 0 || y - i < 0) {
                break;
            }
            sum++;
        }
        for (int i = 1; i <= n; i++) {
            if (a[x + i][y + i] != color || x + i < 0 || y + i < 0) {
                break;
            }
            sum++;
        }
        return sum == n;
    }


}
