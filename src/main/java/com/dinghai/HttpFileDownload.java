package com.dinghai;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.file.Files;

public class HttpFileDownload {

    public static void download(File file, String imgUrl) throws Exception {
        URL url = new URL(imgUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(1000);


        try (FileOutputStream fileOutputStream = new FileOutputStream(file);){

            byte[] bytes = new byte[1024];
            InputStream inputStream = conn.getInputStream();
            int len = inputStream.read(bytes);
            while (len > 0) {
                fileOutputStream.write(bytes, 0, len);
                len = inputStream.read(bytes);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            conn.disconnect();
        }
    }



}
