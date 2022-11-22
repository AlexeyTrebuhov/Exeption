// Напишите приложение, которое будет запрашивать у пользователя следующие данные в произвольном порядке, разделенные пробелом:
//Фамилия Имя Отчество датарождения номертелефона пол
//Форматы данных:
//фамилия, имя, отчество - строки
//дата_рождения - строка формата dd.mm.yyyy
//номер_телефона - целое беззнаковое число без форматирования
//пол - символ латиницей f или m.
//Приложение должно проверить введенные данные по количеству. Если количество не совпадает с требуемым, вернуть код ошибки, обработать его и показать пользователю сообщение, что он ввел меньше и больше данных, чем требуется.
//Приложение должно попытаться распарсить полученные значения и выделить из них требуемые параметры. Если форматы данных не совпадают, нужно бросить исключение, соответствующее типу проблемы. Можно использовать встроенные типы java и создать свои. Исключение должно быть корректно обработано, пользователю выведено сообщение с информацией, что именно неверно.
//Если всё введено и обработано верно, должен создаться файл с названием, равным фамилии, в него в одну строку должны записаться полученные данные, вида
//<Фамилия><Имя><Отчество><датарождения> <номертелефона><пол>
//Однофамильцы должны записаться в один и тот же файл, в отдельные строки.
//Не забудьте закрыть соединение с файлом.
//При возникновении проблемы с чтением-записью в файл, исключение должно быть корректно обработано, пользователь должен увидеть стектрейс ошибки.

package org.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        System.out.print("Survey program started\n\n");

        var text1 = "Enter First Name (text) ";
        var text2 = "Enter Last Name (text) ";
        var text3 = "Enter Patronymic (text) ";
        var text4 = "Enter date of birth (dd.mm.yyyy) ";
        var text5 = "Enter phone number (integer) ";
        var text6 = "Enter gender (Enter F or M) ";

        var textout1 = "";
        var textout2 = "";
        var textout3 = "";
        var textout4 = "";
        var textout5 = "";
        var textout6 = "";

        // Создаем массив с текстами запросов и рандомно выводим пользователю для чтения
        String[] stringArray = new String[]{text1, text2, text3, text4, text5, text6};
        Integer[] indexes = new Integer[6];
        Arrays.setAll(indexes, i -> i);
        List<Integer> random = Arrays.asList(indexes);
        Collections.shuffle(random);

        String[] stringArray1 = new String[6];// создали рабочий массив для заполнения после рандома

        for (int i = 0; i < 6; i++) {
            System.out.print(stringArray[random.get(i)] + "\n");// распечатали пользователю очередность ввода данных
            stringArray1[i] = stringArray[random.get(i)];
        }

        int sumerror = 0; // это счетчик ошибок пользователя при вводе данных

        // Предлагаем пользователю ввести данные через пробел
        System.out.print("\nSeparate data with a space:\n");
        Scanner scanner = new Scanner(System.in);
        String x = scanner.nextLine();
        scanner.close();

        //Введенную строку парсим по символу "пробел"
        int sum = 0; // счетчик распарсенных строк
        String[] subStr;
        String delimeter = " ";
        subStr = x.split(delimeter);
        for (int i = 0; i < subStr.length; i++) {
            sum = sum + 1;
        }

        // выводим ошибку, если ведено больше или меньше данных, чем нужно
        if (sum < 6) {
            throw new RuntimeException(sum + "You entered little data");
        }
        if (sum > 6) {
            throw new RuntimeException(sum + "You entered a lot of data");
        }
        System.out.println();
        String[] stringArrayout = new String[6];// задали и приготовили итоговый массив для наполнения и записи в текстовый файл

        // проверяем распарсенные строки на соответствие форматам

        for (int i = 0; i < 6; i++) {

            if (Objects.equals(stringArray1[i], text6)) {                                 // ищем текст, на основании которого вводили данные
                if ((subStr[i].contains("F")) | (subStr[i].contains("M"))) {// проверяем правильность ввода символа F или M
                    System.out.print("Gender = OK\n");
                    textout6 = subStr[i];                                   //если символы введены правильно, запоминаем введенные данные как textout6
                } else {
                    System.out.print("Gender = Error. Repeat data entry\n");
                    sumerror = 1;
                }
            }

            if (Objects.equals(stringArray1[i], text5)) {
                String regex = "\\d+";                                      // задаем шаблон для проверки на цифру
                if (subStr[i].matches(regex)) {                               // проверяем правильность ввода цифр
                    System.out.print("Phone number = OK\n");
                    textout5 = subStr[i];
                } else {
                    System.out.print("Phone number = Error. Repeat data entry\n");
                    sumerror = 1;
                }
            }

            if (Objects.equals(stringArray1[i], text4)) {
                if ((subStr[i].matches("^(0?[1-9]|[12]\\d|3[01])[- /.](0?[1-9]|1[012])[- /.](19|20)?\\d{2}$"))) {//шаблон на дату
                    System.out.print("Date birth = OK\n");
                    textout4 = subStr[i];
                } else {
                    System.out.print("Date birth = Error. Repeat data entry\n");
                    sumerror = 1;
                }
            }

            if (Objects.equals(stringArray1[i], text1)) {                                // проверяем имя
                if ((subStr[i].matches("^[A-Za-z]+$"))) {            // шаблон для проверки на символы текста
                    System.out.print("First name = OK\n");
                    textout1 = subStr[i];
                } else {
                    System.out.print("First name = Error. Repeat data entry\n");
                    sumerror = 1;
                }
            }

            if (Objects.equals(stringArray1[i], text2)) {                               // проверяем отчество
                if ((subStr[i].matches("^[A-Za-z]+$"))) {           // шаблон для проверки на символы текста
                    System.out.print("Last name = OK\n");
                    textout2 = subStr[i];
                } else {
                    System.out.print("Last name = Error. Repeat data entry\n");
                    sumerror = 1;
                }
            }

            if (Objects.equals(stringArray1[i], text3)) {                                // проверяем фамилию
                if ((subStr[i].matches("^[A-Za-z]+$"))) {            // шаблон для проверки на символы текста
                    System.out.print("Patronymic name = OK\n");
                    textout3 = subStr[i];
                } else {
                    System.out.print("Patronymic name = Error. Repeat data entry\n");
                    sumerror = 1;
                }
            }
        }

        //Собираем массив найденных данных для записи в файл в том порядке, какой задан в условии задачи
        stringArrayout[0] = textout1;
        stringArrayout[1] = textout2;
        stringArrayout[2] = textout3;
        stringArrayout[3] = textout4;
        stringArrayout[4] = textout5;
        stringArrayout[5] = textout6;

        System.out.println();

        if (sumerror == 1) {                                          // завершение цикла проверки на правильность ввода
            System.out.print("Programm closed. Start again\n");       // если ошибкиесть - завершаем программу
        } else {                                                      // если ошибок нет - записываем в файл данные, введенные пользователем

            //Создаем файл с именем, взятым из введенных данных о фамилии ( stringArrayout[2] <- textout3 <- stringArray1[i]
            PrintWriter writer = new PrintWriter((new FileWriter(stringArrayout[2], true))); // true - чтобы дописывать на след.строке

            writer.println("First Name = " + stringArrayout[0] + " Last Name = " + stringArrayout[1] + " Patronymic = " + stringArrayout[2] +
                    " Date birth = " + stringArrayout[3] + " Phone = " + stringArrayout[4] + " Gender = " + stringArrayout[5] + " \n");
            writer.close();

            // Считываем только что записанные данные
            StringBuilder sb = new StringBuilder();
            try (BufferedReader reader = Files.newBufferedReader(Paths.get(stringArrayout[2]))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append(System.lineSeparator());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(sb);
        }
    }
}
