import java.io.*;
import java.net.URL;

public class Task2 {
    public static void main(String[] args) {
        /*
        задача 2
Возьмите какой-нибудь большой файл (например, видео запись).  Используя FileInputStream и FileOutputStream,
реализуйте 2 метода копирования этого файла. В первом случае используйте побайтное чтение\запись.
 Во втором случае чтение запись через массив. Сравните время копирования.
PS внимательно сравните размер исходного файла и копии, убедитесь, что копия полностью соответствует оригиналу.
         */
        String url = "https://www.ait-tr.de/blog/categories/tech-blog";
        String outputFileNameByte = "copy_byte.txt";
        String outputFileNameArray = "copy_array.txt";
        System.out.println("copyByByte Время: " + copyingTimeByByte(url, outputFileNameByte)); //copyByByte Время: 2488 мс
        System.out.println("copyByArray Время: " +copyingTimeByArray( url, outputFileNameArray, 8192));//copyByArray Время: 219 мс
    }


    public static long copyingTimeByByte(String url, String copyFileName) {
        long startTime = System.nanoTime();
        try (InputStream fis = new URL(url).openStream();
             OutputStream fos = new FileOutputStream(copyFileName)) {
            copyByByte(fis, fos);
        } catch (IOException e) {
            System.out.println("Ошибка при копировании файла: " + e.getMessage());
        }
        long endTime = System.nanoTime();
        return  (endTime - startTime) / 1_000_000;
    }

    public static long copyingTimeByArray(String url, String copyFileName, int byteSize) {
        long startTime = System.nanoTime();
        try (InputStream fis = new URL(url).openStream();
             OutputStream fos = new FileOutputStream(copyFileName)) {
            copyByArray(fis, fos, byteSize);
        } catch (IOException e) {
            System.out.println("Ошибка при копировании файла: " + e.getMessage());
        }
        long endTime = System.nanoTime();
return (endTime - startTime) / 1_000_000;
    }

    public static void copyByByte(InputStream is, OutputStream os) throws IOException {
        int data;
        while ((data = is.read()) != -1) {
            os.write(data);
        }
    }

    public static void copyByArray(InputStream is, OutputStream os, int byteSize) throws IOException {
        byte[] buffer = new byte[byteSize];
        int bytesRead;
        while ((bytesRead = is.read(buffer)) != -1) {
            os.write(buffer, 0, bytesRead);
        }
    }
}