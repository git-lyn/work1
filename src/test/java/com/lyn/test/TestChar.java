package com.lyn.test;

public class TestChar {
    public static void main(String[] args) {
        char c = 'A';
        System.out.println(c + 1);
        System.out.println('Z' + 1 );
//        char a = Character.valueOf('3');
        System.out.println((char)(65));
//        stack();
//        while (true){
//            new TestChar();
//        }
        System.out.println(TestChar.class.getClassLoader());
        System.out.println(TestChar.class.getClassLoader().getParent());
        System.out.println(TestChar.class.getClassLoader().getParent().getParent());

    }

    public static void stack(){
        stack();
    }
}
