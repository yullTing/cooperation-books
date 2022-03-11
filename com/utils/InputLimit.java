package com.utils;

//键盘录入限制 - 工具类

import java.util.Random;
import java.util.Scanner;

public class InputLimit {
    public static final String ANSI_RESET = "\u001B[0m";//重置
    public static final String ANSI_RED = "\u001B[31m";//红色字体
    public static final String ANSI_GREEN = "\u001B[32m";//绿色字体
    public static final String ANSI_PURPLE = "\u001B[35m";//紫红色字体
    public static final String ANSI_CYAN = "\u001B[36m";//蓝色字体
    public static final String ANSI_CLOSE = "\033[0m";//关闭所有属性

    //测试
    /*public static void main(String[] args) {
        PurpleFont("hello,world!);
    }*/

    //(基本)限制仅可录入字符串：要求不能输入空格
    public static String InputString() {
        String str = null;
        for (; ; ) {
            Scanner sc = new Scanner(System.in);
            str = sc.nextLine().trim();
            if (str.length() == 0) {
                Warn("(请勿输入空格)重新输入：");
            } else break;
        }
        return str;
    }

    //(菜单)限制仅可录入数字：1、2
    public static char InputMenuTwo() {
        char menuIndex;
        for (; ; ) {
            String str = InputString(1);
            menuIndex = str.charAt(0);
            if (menuIndex != '1' && menuIndex != '2') {
                Warn("(菜单选择错误)请重新输入：");
            } else break;
        }
        return menuIndex;
    }

    //(菜单)限制仅可录入数字：1、2、3、4
    public static char InputMenuFour() {
        char menuIndex;
        for (; ; ) {
            String str = InputString(1);
            menuIndex = str.charAt(0);
            if (menuIndex != '1' && menuIndex != '2' && menuIndex != '3' && menuIndex != '4') {
                Warn("(菜单选择错误)请重新输入：");
            } else break;
        }
        return menuIndex;
    }

    //(菜单)限制仅可录入数字：1、2、3、4、5
    public static char InputMenuFive() {
        char menuIndex;
        for (; ; ) {
            String str = InputString(1);
            menuIndex = str.charAt(0);
            if (menuIndex != '1' && menuIndex != '2' && menuIndex != '3' && menuIndex != '4' && menuIndex != '5') {
                Warn("(菜单选择错误)请重新输入：");
            } else break;
        }
        return menuIndex;
    }

    //(菜单)限制仅可录入数字：1、2、3、4、5、6
    public static char InputMenuSix() {
        char menuIndex;
        for (; ; ) {
            String str = InputString(1);
            menuIndex = str.charAt(0);
            if (menuIndex != '1' && menuIndex != '2' && menuIndex != '3' && menuIndex != '4' && menuIndex != '5' && menuIndex != '6') {
                Warn("(菜单选择错误)请重新输入：");
            } else break;
        }
        return menuIndex;
    }

    //(菜单)限制仅可录入数字：1、2、3、4、5、6、7
    public static char InputMenuSeven() {
        char menuIndex;
        for (; ; ) {
            String str = InputString(1);
            menuIndex = str.charAt(0);
            if (menuIndex != '1' && menuIndex != '2' && menuIndex != '3' && menuIndex != '4' && menuIndex != '5' && menuIndex != '6' && menuIndex != '7') {
                Warn("(菜单选择错误)请重新输入：");
            } else break;
        }
        return menuIndex;
    }

    //(姓名、密码等限制字符串长度)限制仅可录入一定长度的字符串，且不能输入空格
    public static String InputString(int length) {
        String str = null;
        for (; ; ) {
            str = InputString();
            if (str.length() > length) {
                Warn("(最多可输入" + length + "个字符)请重新输入：");
            } else break;
        }
        return str;
    }

    //(选择)限制仅可录入y、n、Y、N
    public static char InputChoice() {
        char choice;
        for (; ; ) {
            String str = InputString().toUpperCase();
            choice = str.charAt(0);
            if (choice != 'Y' && choice != 'N') {
                Warn("(选择输入错误)请重新输入：");
            } else break;
        }
        return choice;
    }

    //限制只能输入1-10000的纯数字
    public static int InputNumber() {
        int i;
        for (; ; ) {
            String str = InputString();
            try {
                i = Integer.parseInt(str);
                if (i <= 10000 && i >= 1) {
                    break;
                } else {
                    Warn("请输入1-10000以内的数字：");
                }
            } catch (NumberFormatException e) {
                Warn("(输入错误)请重新输入：");
            }
        }
        return i;
    }

    //限制只能输入1-limit的纯数字
    public static int InputMaxNum(int limit) {
        int i;
        for (; ; ) {
            String str = InputString(limit);
            try {
                i = Integer.parseInt(str);
                if (i <= limit && i >= 1) {
                    break;
                } else {
                    Warn("请输入1-" + limit + "以内的数字：");
                }
            } catch (NumberFormatException e) {
                Warn("(输入错误)请重新输入：");
            }
        }
        return i;
    }

    //限制输入的字符串替换成 Double
    public static double InputDouble() {
        double result;
        for (; ; ) {
            String input1 = InputString();
            try {
                double input2 = Double.parseDouble(input1);
                if (input2 >= 0.0 && input2 <= 9999.0) {
                    result = input2;
                    break;
                } else {
                    Warn("(输入错误)请重新输入：");
                }
            } catch (NumberFormatException e) {
                Warn("(输入错误)请重新输入：");
            }
        }
        return result;
    }

    //限制年龄的输入
    public static int InputAge(int maxAge) {
        return InputMaxNum(maxAge);
    }


    //限制输入的字符串必须是 Date类型
    public static String InputDate() {
        String result;
        for (; ; ) {
            String input = InputString();

            boolean isMatches = input.matches("([1-9]?[0-9]{3})[-]([0-1]?[0-9]{1})[-]([0-3]?[0-9]{1})");
            if (isMatches) {
                result = input;
                break;
            } else {
                Warn("日期格式输入错误！请重新输入：");
            }
        }
        return result;
    }

    //限制字符串的前四位必须是ISBN
    public static String InputISBN() {
        String string, result;
        for (; ; ) {
            string = InputString();
            boolean isSW = string.startsWith("ISBN");
//            boolean isMatches = string.matches("ISBN");
            if (isSW) {
                result = string;
                break;
            } else {
                Warn("请输入ISBN开头的图书编号：");
            }
        }
        return result;
    }

    //限制只能输入11位的电话号码，返回的仍旧是 String 类数据
    public static String InputTel() {
        String result, input;
        for (; ; ) {
            input = InputString();
            boolean isMatches = input.matches("[1][0-9]{10}");
            if (isMatches) {
                result = input;
                break;
            } else {
                Warn("请输入11位的电话号码：");
            }
        }
        return result;
    }

    //限制只能输入 男/女 两种字符
    public static String InputGender(){
        String result,input;
        for (; ;){
            input = InputString(1);
            if (input.equals("男") || input.equals("女")){
                result = input;
                break;
            } else {
                Warn("请输入 男/女 两种字符：");
            }
        }
        return result;
    }

    //修改年龄的输入限制
    public static int ModifyAge(int defaultInt){
        int resultAge;
        int ageLimit = 120;
        for (; ; ) {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine().trim();
            if (input.length() == 0) {
                resultAge = defaultInt;
                break;
            } else {
                try {
                    int input2 = Integer.parseInt(input);
                    if (input2 >= 1 && input2 <= ageLimit) {
                        resultAge = input2;
                        break;
                    } else {
                        Warn("请输入1-" + ageLimit + "以内的数字：");
                    }
                } catch (NumberFormatException e) {
                    Warn("(输入错误)请重新输入：");
                }
            }
        }
        return resultAge;
    }


    //修改罚金：数据类型double
    public static double ModifyDouble(double defaultString) {
        double result = 0;
        for (; ; ) {
            Scanner sc = new Scanner(System.in);
            String input1 = sc.nextLine().trim();
            if (input1.length() == 0) {
                result = defaultString;
                break;
            } else {
                try {
                    double input2 = Double.parseDouble(input1);
                    if (input2 >= 0.0 && input2 <= 999.0) {
                        result = input2;
                        break;
                    } else {
                        Warn("(输入错误)请重新输入：");
                    }
                } catch (NumberFormatException e) {
                    Warn("(输入错误)请重新输入：");
                }
            }
        }
        return result;
    }

    //修改读者类型对应的名称：数据类型String
    public static String ModifyString(String defaultString) {
        String result;
        for (; ; ) {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine().trim();
            if (input.length() == 0) {
                result = defaultString;
                break;
            } else {
                result = input;
                break;
            }
        }
        return result;
    }

    //修改读者类型对应的借书数量和天数：数据类型int
    public static int ModifyInt(int defaultString) {
        int result;
        int i;
        for (; ; ) {
            Scanner sc = new Scanner(System.in);
            String input1 = sc.nextLine().trim();
            if (input1.length() == 0) {
                result = defaultString;
                break;
            } else {
                /*boolean isMatches = input1.matches("[0-9]");
                if (isMatches){
                    Notice("匹配成功！" + input1);
                    result = Integer.parseInt(input1);
                    break;
                } else {
                    Warn("(输入错误)请重新输入：");
                }*/
                try {
                    int input2 = Integer.parseInt(input1);
                    if (input2 >= 0 && input2 <= 99999999) {
                        result = input2;
                        break;
                    } else {
                        Warn("(输入错误)请重新输入：");
                    }
                } catch (NumberFormatException e) {
                    Warn("(输入错误)请重新输入：");
                }
            }
        }
        return result;
    }

    //修改图书总数量
    public static int ModifyBooksAmount(int defaultString, int canBorrowAmount){
        int result;
        int i;
        for (; ; ) {
            Scanner sc = new Scanner(System.in);
            String input1 = sc.nextLine().trim();
            if (input1.length() == 0) {
                result = defaultString;
                break;
            } else {
                try {
                    int input2 = Integer.parseInt(input1);
                    if (input2 >= 0 && input2 <= 999) {
                        if (input2 <= canBorrowAmount){
                            Warn("图书总数量不能小于等于已借阅数量！请重新输入：");
                        } else {
                            result = input2;
                            break;
                        }
                    } else {
                        Warn("(输入错误)请重新输入：");
                    }
                } catch (NumberFormatException e) {
                    Warn("(输入错误)请重新输入：");
                }
            }
        }
        return result;
    }

    //修改图书出版日期：数据类型Date
    public static String ModifyDate(String defaultString) {
        String result;
        for (; ; ) {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine().trim();
            if (input.length() == 0) {
                result = defaultString;
                break;
            } else {
                boolean isMatches = input.matches("([1-9]?[0-9]{3})[-]([0-1]?[0-9]{1})[-]([0-3]?[0-9]{1})");
                if (isMatches) {
                    result = input;
                    break;
                } else {
                    Warn("日期格式输入错误！请重新输入：");
                }
            }
        }
        return result;
    }

    //修改联系电话：返回数据类型String
    public static String ModifyTel(String defaultString) {
        String result, input;
        for (; ; ) {
            Scanner sc = new Scanner(System.in);
            input = sc.nextLine().trim();
            if (input.length() == 0) {
                result = defaultString;
                break;
            } else {
                boolean isMatches = input.matches("[1][0-9]{10}");
                if (isMatches) {
                    result = input;
                    break;
                } else {
                    Warn("请输入11位的电话号码：");
                }
            }
        }
        return result;
    }

    //修改数据，限制返回的 String 字符串长度
    public static String ModifyStringLength(String defaultString, int length) {
        String result, input;
        for (; ; ) {
            Scanner sc = new Scanner(System.in);
            input = sc.nextLine().trim();
            if (input.length() == 0) {
                result = defaultString;
                break;
            } else if (input.length() > length) {
                Warn("仅限输入" + length + "位字符：");
            } else {
                result = input;
                break;
            }
        }
        return result;
    }

    //修改读者性别：限制只能输入 回车/男/女
    public static String ModifyGender(String defaultString){
        String result,input;
        for (; ;) {
            Scanner sc = new Scanner(System.in);
            input = sc.nextLine().trim();
            if (input.length()==0){
                result = defaultString;
                break;
            } else {
                if (input.equals("男") || input.equals("女")){
                    result = input;
                    break;
                } else {
                    Warn("请输入 男/女 两种字符：");
                }
            }
        }
        return result;
    }

    //红色字体
    public static void Warn(String string) {
        System.out.println(ANSI_RED + string);
        System.out.println(ANSI_CLOSE);
    }

    //绿色字体
    public static void Notice(String string) {
        System.out.println(ANSI_GREEN + string);
        System.out.println(ANSI_CLOSE);
    }

    //蓝色字体
    public static void BlueFont(String string){
        System.out.print(ANSI_CYAN + string);
        System.out.println(ANSI_CLOSE);
    }

    //紫红色字体
    public static void PurpleFont(String string){
        System.out.print(ANSI_PURPLE + string);
        System.out.println(ANSI_CLOSE);
    }


    //进度条
    public static void loadSpecialEffects() throws InterruptedException {
        System.out.println("请稍等：");
        for (int i1 = 1; i1 <= 100; i1++) {
            System.out.print("加载中：" + i1 + "%");
            Thread.sleep(new Random().nextInt(25) + 1);
            if (i1 == 100) {
                Thread.sleep(50);
            }
            System.out.print("\r");
        }
    }
}
