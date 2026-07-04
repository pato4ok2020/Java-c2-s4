package by.vsu;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static final char CONTENT_TYPE_SEPARATOR = ';';
    public static final String CHARSET_PROPERTY_PREFIX = "charset=";
    public static final int AUD_ID = 440;

    public static void main(String[] args) throws IOException {
        List<Rate> rates = getAllRates();
        System.out.println("=== все курсы ===".toUpperCase());
        for (Rate rate : rates) {
            System.out.println(rate);
        }
        System.out.println();

        Rate AUDRate = getAUDRate();
        double AUDOfficialRate = AUDRate.getOfficialRate();
        System.out.println(
                "=== только те валюты, стоимость за 1 единицу которых меньше, чем стоимость Австралийского доллара ===".toUpperCase()
        );
        List<Rate> otherRates = new ArrayList<>();
        for (Rate rate : rates) {
            double officialRate = rate.getOfficialRate() / rate.getScale();
            if (officialRate < AUDOfficialRate) {
                System.out.println(rate.getName() + " - " + officialRate);
            } else if (rate.getId() != AUD_ID) {
                otherRates.add(rate);
            }
        }
        System.out.println();

        System.out.println(
                "=== оставшиеся курсы ===".toUpperCase()
        );
        for (Rate rate : otherRates) {
            double officialRate = rate.getOfficialRate() / rate.getScale();
            System.out.println(rate.getName() + " - " + officialRate);
        }
    }

    public static List<Rate> getAllRates() throws IOException {
        String spec = "https://api.nbrb.by/exrates/rates?periodicity=0";
        URL url = new URL(spec);
        URLConnection connection = url.openConnection();

        try (InputStream is = connection.getInputStream();) {
            ObjectMapper mapper = new ObjectMapper();
            JavaType type = mapper.getTypeFactory().constructCollectionType(ArrayList.class, Rate.class);
            return mapper.readValue(is, type);
        }
    }

    public static Rate getAUDRate() throws IOException {
        String spec = "https://api.nbrb.by/exrates/rates/" + AUD_ID;
        URL url = new URL(spec);
        URLConnection connection = url.openConnection();

        try (InputStream is = connection.getInputStream()) {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(is, Rate.class);
        }
    }
}