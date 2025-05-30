import java.io.*;

public class Task3 {
    public static void main(String[] args) {
        /*
        1024 байта (1 КБ)
        4096 байт (4 КБ)
        8192 байта (8 КБ)
        16384 байта (16 КБ)
        .
задача 3
По ссылке https://drive.google.com/file/d/1XZrLLuJlHK3n35NwQAnY9t1nSwZ3-piq/view?usp=sharing находится файл file.dat .
Сохраните этот файл  на диск (просто “ручками”, не из программы).
Ваша программа  должна записать в отдельный файл первые 601 байт, затем в отдельный файл записать следующие 57053 байта
 и оставшиеся 22494 байта записать в следующий файл. Если все сделано правильно, у вас должно получиться 3 файла.
         */

        String url = "https://drive.google.com/file/d/1XZrLLuJlHK3n35NwQAnY9t1nSwZ3-piq/view?usp=sharing";
        //Сохраняю в мой проект файл file.dat с помощью Task2.copyByArray
        Task2.copyingTimeByArray(url, "file.dat", 8192);

        splitIn3Files("file.dat");
    }

    public static void splitIn3Files(String file) {
        try (InputStream is = new FileInputStream(file);
             OutputStream os1 = new FileOutputStream("file.dat_1");
             OutputStream os2 = new FileOutputStream("file.dat_2");
             OutputStream os3 = new FileOutputStream("file.dat_3")) {

            byte[] buffer1 = new byte[601];
            int bytesRead1 = is.read(buffer1);
            os1.write(buffer1, 0, bytesRead1);

            byte[] buffer2 = new byte[57053];
            int bytesRead2 = is.read(buffer2);
            os2.write(buffer2, 0, bytesRead2);

            byte[] buffer3 = new byte[22494];
            int bytesRead3 = is.read(buffer3);
            os3.write(buffer3, 0, bytesRead3);

//            // Здесь использую while  чтобы дойти до конца file, если неизвестно оставшиеся ? байта
//            byte[] buffer3 = new byte[8192];
//            int bytesRead3;
//            while ((bytesRead3 = is.read(buffer3)) != -1) {
//                os3.write(buffer3, 0, bytesRead3);
//            }

        } catch (IOException e) {
            System.out.println("Ошибка : " + e.getMessage());
        }
    }

}
