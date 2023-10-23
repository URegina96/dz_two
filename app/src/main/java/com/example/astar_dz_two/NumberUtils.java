package com.example.astar_dz_two;

public class NumberUtils {  
    public static boolean isNumber(String value) { // шаг - 27 Метод isNumber(String value) проверяет, является ли переданное значение числом. Он принимает строковое значение и возвращает логическое значение (true или false), указывающее, является ли значение числом
        boolean isNumber = true; //  Эта переменная будет использоваться для хранения информации о том, является ли переданное значение числом
        try { //  используем конструкцию try-catch для попытки преобразовать строку value в целое число, используя метод Integer.parseInt()
            Integer.parseInt(value);
        } catch (NumberFormatException e) {
            isNumber = false;
        }
        return isNumber;
    }

    public static int stringToInt(String value) {   // шаг - 28   Метод stringToInt(String value) преобразует строковое значение в целое число и возвращает его. Он также принимает строковое значение и использует метод Integer.parseInt() для преобразования этого значения в целое число
        try {
            return Integer.parseInt(value); //  метод Integer.parseInt(value), который пытается преобразовать строку value в целое число. Если преобразование проходит успешно, то значение преобразованного числа возвращается из метода
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
