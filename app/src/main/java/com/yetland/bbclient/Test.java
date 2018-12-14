package com.yetland.bbclient;

import java.util.HashMap;
import java.util.Map;

/**
 * @author YETLAND
 * @date 2018/12/6 11:01
 */
public class Test {
    public static void main(String[] args) {

//        String s1 = "111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111";
//        String s2 = "111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111";
        String s1 = "123";
        String s2 = "1";
        deal(s1, s2);
        deal2(s1, s2);
    }


    public static String deal(String num1, String num2) {
        long start = System.currentTimeMillis();

        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        char[] s1c = num1.toCharArray();
        char[] s2c = num2.toCharArray();

        int length1 = num1.length();
        int length2 = num2.length();
        Map<Integer, Integer> result = new HashMap<>(length1 + length2);


        for (int i = 0; i < length1; i++) {
            for (int j = 0; j < length2; j++) {
                // 位数和
                int sum = i + j;
                if (!result.containsKey(sum)) {
                    int value1 = Integer.valueOf(String.valueOf(s1c[length1 - i - 1]));
                    int value2 = Integer.valueOf(String.valueOf(s2c[length2 - j - 1]));
                    int valueSum = value1 * value2;
                    // 如果还有数据
                    for (int k = i + 1; k < length1; k++) {
                        for (int l = 0; l < length2; l++) {

                            if ((k + l) == sum) {
                                value1 = Integer.valueOf(String.valueOf(s1c[length1 - k - 1]));
                                value2 = Integer.valueOf(String.valueOf(s2c[length2 - l - 1]));
                                valueSum += value1 * value2;
                            }
                        }
                    }
                    result.put(sum, valueSum);
                }
            }
        }

        for (Map.Entry<Integer, Integer> integerIntegerEntry : result.entrySet()) {
            int key = integerIntegerEntry.getKey();
            int value = integerIntegerEntry.getValue();
            int ss = value / 10;
            if (result.containsKey(key + 1)) {
                result.put(key, value % 10);
                result.put(key + 1, result.get(key + 1) + ss);
            } else {
                value = result.get(key);
                if (value >= 10) {
                    result.put(key, value % 10);
                    result.put(key + 1, value / 10);
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<Integer, Integer> integerIntegerEntry : result.entrySet()) {
            stringBuilder.append(integerIntegerEntry.getValue());
        }
        long end = System.currentTimeMillis();
        System.out.print("time = " + (end - start) + "\n");
        return new StringBuffer(stringBuilder.toString()).reverse().toString();
    }

    public static String deal2(String num1, String num2) {

        long start = System.currentTimeMillis();
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        int length1 = num1.length();
        int length2 = num2.length();
        int length = length1 + length2;

        StringBuilder stringBuffer = new StringBuilder();
        int res = 0;
        for (int i = 0; i < length - 1; i++) {
            int value = 0;
            for (int j = 0; j < length1; j++) {
                int k = (i - j);
                if (k >= 0 && k < length2) {
                    int value1 = (int) num1.charAt(length1 - 1 - j) - 48;
                    int value2 = (int) num2.charAt(length2 - 1 - k) - 48;
                    System.out.print("value1 = " + value1 + "\n");
                    System.out.print("value2 = " + value2 + "\n");
                    value += (value1 * value2);
                }
            }
            value += res;
            res = value / 10;
            stringBuffer.append(value % 10);

            if (i == length - 2) {
                if (value >= 10) {
                    stringBuffer.append(value / 10);
                }
            }
        }

        long end = System.currentTimeMillis();
        System.out.print("time = " + (end - start) + "\n");
        System.out.print("result = " + stringBuffer.reverse().toString() + "\n");
        return stringBuffer.reverse().toString();

    }

    public static String deal3(String num1, String num2) {
        if (num1.isEmpty() || num2.isEmpty()
                || (num1.length() == 1 && num1.charAt(0) == '0')
                || (num2.length() == 1 && num2.charAt(0) == '0')) {
            return "0";
        }
        int len1 = num1.length();
        int len2 = num2.length();
        int[] ans = new int[len1 + len2 + 1];
        for (int i = 0; i < len1; i++) {
            int a = num1.charAt(i) - '0';
            for (int j = 0; j < len2; j++) {
                int b = num2.charAt(j) - '0';
                ans[len1 + len2 - i - j - 2] += a * b;
            }
        }
        StringBuffer res = new StringBuffer();
        for (int i = 0; i < len1 + len2; i++) {
            res.append(ans[i] % 10);
            ans[i + 1] += ans[i] / 10;
        }
        String result = res.reverse().toString();
        if (result.startsWith("0")) {
            result = result.substring(1, len1 + len2);
        }
        return result;
    }

}
