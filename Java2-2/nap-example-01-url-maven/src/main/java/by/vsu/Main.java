package by.vsu;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class Main {
    public static final char CONTENT_TYPE_SEPARATOR = ';';
    public static final String CHARSET_PROPERTY_PREFIX = "charset=";

    public static String parseCharset(URLConnection connection) {
        String charset = connection.getContentEncoding();
        if(charset == null) {
            /*
             * Если поле Content-Encoding не установлено, а чаще всего
             * так и бывает. В таком случае кодировка может быть указана
             * в поле Content-Type, которое как правило имеет формат
             * <MIME тип>[; charset=<кодировка>]
             * например
             * application/json; charset=utf-8
             */
            String contentType = connection.getContentType();
            if(contentType != null) {
                /*
                 * Сначала необходимо извлечь вторую часть строки (справа от символа ';'),
                 * если она присутствует в поле Content-Type
                 */
                int semicolonPosition = contentType.indexOf(CONTENT_TYPE_SEPARATOR);
                if(semicolonPosition != -1) {
                    String charsetProperty = contentType.substring(semicolonPosition + 2);
                    /*
                     * Проверяем, действительно ли правая часть -- это кодировка
                     */
                    if(charsetProperty.startsWith(CHARSET_PROPERTY_PREFIX)) {
                        /*
                         * Извлекаем значение кодировки
                         */
                        charset = charsetProperty.substring(CHARSET_PROPERTY_PREFIX.length());
                    }
                }
            }
        }
        return charset;
    }

    public static void main(String[] args) throws IOException {
        // USD - 431
        // JPY - 508
//        String spec = "https://api.nbrb.by/exrates/rates/431";
        String spec = "https://api.nbrb.by/exrates/rates/508?ondate=2026-01-17";
        URL url = new URL(spec);
        System.out.printf("Protocol: \"%s\"\n", url.getProtocol());
        System.out.printf("Host: \"%s\"\n", url.getHost());
        System.out.printf("Port: %d\n", url.getPort());
        System.out.printf("File: \"%s\"\n", url.getFile());
        System.out.printf("Path: \"%s\"\n", url.getPath());
        System.out.printf("Query: \"%s\"\n", url.getQuery());

        URLConnection connection = url.openConnection();

        System.out.println("Connection OK");
        System.out.printf("Content-Encoding: \"%s\"\n", connection.getContentEncoding());
        System.out.printf("Content-Length: %d\n", connection.getContentLength());
        System.out.printf("Content-Type: \"%s\"\n", connection.getContentType());
        String charset = parseCharset(connection);
        System.out.printf("Charset: \"%s\"\n", charset);

        try (InputStream is = connection.getInputStream()) {
            ObjectMapper mapper = new ObjectMapper();
            Rate rate = mapper.readValue(is, Rate.class);
            System.out.println(rate);
            System.out.println("Курс: " + rate.getOfficialRate());
        }
    }
}