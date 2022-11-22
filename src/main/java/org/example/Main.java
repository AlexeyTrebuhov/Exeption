// �������� ����������, ������� ����� ����������� � ������������ ��������� ������ � ������������ �������, ����������� ��������:
//������� ��� �������� ������������ ������������� ���
//������� ������:
//�������, ���, �������� - ������
//����_�������� - ������ ������� dd.mm.yyyy
//�����_�������� - ����� ����������� ����� ��� ��������������
//��� - ������ ��������� f ��� m.
//���������� ������ ��������� ��������� ������ �� ����������. ���� ���������� �� ��������� � ���������, ������� ��� ������, ���������� ��� � �������� ������������ ���������, ��� �� ���� ������ � ������ ������, ��� ���������.
//���������� ������ ���������� ���������� ���������� �������� � �������� �� ��� ��������� ���������. ���� ������� ������ �� ���������, ����� ������� ����������, ��������������� ���� ��������. ����� ������������ ���������� ���� java � ������� ����. ���������� ������ ���� ��������� ����������, ������������ �������� ��������� � �����������, ��� ������ �������.
//���� �� ������� � ���������� �����, ������ ��������� ���� � ���������, ������ �������, � ���� � ���� ������ ������ ���������� ���������� ������, ����
//<�������><���><��������><������������> <�������������><���>
//������������ ������ ���������� � ���� � ��� �� ����, � ��������� ������.
//�� �������� ������� ���������� � ������.
//��� ������������� �������� � �������-������� � ����, ���������� ������ ���� ��������� ����������, ������������ ������ ������� ��������� ������.

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

        // ������� ������ � �������� �������� � �������� ������� ������������ ��� ������
        String[] stringArray = new String[]{text1, text2, text3, text4, text5, text6};
        Integer[] indexes = new Integer[6];
        Arrays.setAll(indexes, i -> i);
        List<Integer> random = Arrays.asList(indexes);
        Collections.shuffle(random);

        String[] stringArray1 = new String[6];// ������� ������� ������ ��� ���������� ����� �������

        for (int i = 0; i < 6; i++) {
            System.out.print(stringArray[random.get(i)] + "\n");// ����������� ������������ ����������� ����� ������
            stringArray1[i] = stringArray[random.get(i)];
        }

        int sumerror = 0; // ��� ������� ������ ������������ ��� ����� ������

        // ���������� ������������ ������ ������ ����� ������
        System.out.print("\nSeparate data with a space:\n");
        Scanner scanner = new Scanner(System.in);
        String x = scanner.nextLine();
        scanner.close();

        //��������� ������ ������ �� ������� "������"
        int sum = 0; // ������� ������������ �����
        String[] subStr;
        String delimeter = " ";
        subStr = x.split(delimeter);
        for (int i = 0; i < subStr.length; i++) {
            sum = sum + 1;
        }

        // ������� ������, ���� ������ ������ ��� ������ ������, ��� �����
        if (sum < 6) {
            throw new RuntimeException(sum + "You entered little data");
        }
        if (sum > 6) {
            throw new RuntimeException(sum + "You entered a lot of data");
        }
        System.out.println();
        String[] stringArrayout = new String[6];// ������ � ����������� �������� ������ ��� ���������� � ������ � ��������� ����

        // ��������� ������������ ������ �� ������������ ��������

        for (int i = 0; i < 6; i++) {

            if (Objects.equals(stringArray1[i], text6)) {                                 // ���� �����, �� ��������� �������� ������� ������
                if ((subStr[i].contains("F")) | (subStr[i].contains("M"))) {// ��������� ������������ ����� ������� F ��� M
                    System.out.print("Gender = OK\n");
                    textout6 = subStr[i];                                   //���� ������� ������� ���������, ���������� ��������� ������ ��� textout6
                } else {
                    System.out.print("Gender = Error. Repeat data entry\n");
                    sumerror = 1;
                }
            }

            if (Objects.equals(stringArray1[i], text5)) {
                String regex = "\\d+";                                      // ������ ������ ��� �������� �� �����
                if (subStr[i].matches(regex)) {                               // ��������� ������������ ����� ����
                    System.out.print("Phone number = OK\n");
                    textout5 = subStr[i];
                } else {
                    System.out.print("Phone number = Error. Repeat data entry\n");
                    sumerror = 1;
                }
            }

            if (Objects.equals(stringArray1[i], text4)) {
                if ((subStr[i].matches("^(0?[1-9]|[12]\\d|3[01])[- /.](0?[1-9]|1[012])[- /.](19|20)?\\d{2}$"))) {//������ �� ����
                    System.out.print("Date birth = OK\n");
                    textout4 = subStr[i];
                } else {
                    System.out.print("Date birth = Error. Repeat data entry\n");
                    sumerror = 1;
                }
            }

            if (Objects.equals(stringArray1[i], text1)) {                                // ��������� ���
                if ((subStr[i].matches("^[A-Za-z]+$"))) {            // ������ ��� �������� �� ������� ������
                    System.out.print("First name = OK\n");
                    textout1 = subStr[i];
                } else {
                    System.out.print("First name = Error. Repeat data entry\n");
                    sumerror = 1;
                }
            }

            if (Objects.equals(stringArray1[i], text2)) {                               // ��������� ��������
                if ((subStr[i].matches("^[A-Za-z]+$"))) {           // ������ ��� �������� �� ������� ������
                    System.out.print("Last name = OK\n");
                    textout2 = subStr[i];
                } else {
                    System.out.print("Last name = Error. Repeat data entry\n");
                    sumerror = 1;
                }
            }

            if (Objects.equals(stringArray1[i], text3)) {                                // ��������� �������
                if ((subStr[i].matches("^[A-Za-z]+$"))) {            // ������ ��� �������� �� ������� ������
                    System.out.print("Patronymic name = OK\n");
                    textout3 = subStr[i];
                } else {
                    System.out.print("Patronymic name = Error. Repeat data entry\n");
                    sumerror = 1;
                }
            }
        }

        //�������� ������ ��������� ������ ��� ������ � ���� � ��� �������, ����� ����� � ������� ������
        stringArrayout[0] = textout1;
        stringArrayout[1] = textout2;
        stringArrayout[2] = textout3;
        stringArrayout[3] = textout4;
        stringArrayout[4] = textout5;
        stringArrayout[5] = textout6;

        System.out.println();

        if (sumerror == 1) {                                          // ���������� ����� �������� �� ������������ �����
            System.out.print("Programm closed. Start again\n");       // ���� ���������� - ��������� ���������
        } else {                                                      // ���� ������ ��� - ���������� � ���� ������, ��������� �������������

            //������� ���� � ������, ������ �� ��������� ������ � ������� ( stringArrayout[2] <- textout3 <- stringArray1[i]
            PrintWriter writer = new PrintWriter((new FileWriter(stringArrayout[2], true))); // true - ����� ���������� �� ����.������

            writer.println("First Name = " + stringArrayout[0] + " Last Name = " + stringArrayout[1] + " Patronymic = " + stringArrayout[2] +
                    " Date birth = " + stringArrayout[3] + " Phone = " + stringArrayout[4] + " Gender = " + stringArrayout[5] + " \n");
            writer.close();

            // ��������� ������ ��� ���������� ������
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
