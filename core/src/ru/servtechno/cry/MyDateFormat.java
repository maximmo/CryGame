package ru.servtechno.cry;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyDateFormat
{
    public static void main(String[] args)
    {
        // Создаем Шаблон вывода Даты
        DateFormat TIMESTAMP = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // Выводим Дату по шаблону
        System.out.println(TIMESTAMP.format(new Date()));
    }
}