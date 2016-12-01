package ru.sbt;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Sania on 09.09.2016.
 */

public class Solution2222 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str;
        String[] arr;
        int k;
        boolean f = false;
        str = scanner.nextLine();
        k = scanner.nextInt();
        arr = str.split("[,]");
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].length() >= k) {
                if (f) System.out.print("," + arr[i]);
                else {
                    System.out.print(arr[i]);
                    f = true;
                }
            }
        }
        if (str.endsWith(",") && k == 0) System.out.println(",");
    }
}

