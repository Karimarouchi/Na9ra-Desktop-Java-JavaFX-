package tn.TheInformants.entities;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;


public class CertificateGenerator {

        public static InputStream generateCertificate(String htmlTemplate) {
            try {
                String apiKey = "iK_nc4z45nvGnL1enrXj";
                URL url = new URL("https://docraptor.com/docs");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestProperty("Authorization", "Basic " + java.util.Base64.getEncoder().encodeToString((apiKey + ":").getBytes()));

                JSONObject json = new JSONObject();
                json.put("document_content", htmlTemplate);
                json.put("type", "pdf");
                json.put("test", true);

                conn.setDoOutput(true);
                try (OutputStream os = conn.getOutputStream()) {
                    byte[] input = json.toString().getBytes(StandardCharsets.UTF_8);
                    os.write(input, 0, input.length);
                }

                int responseCode = conn.getResponseCode();
                System.out.println("Response Code: " + responseCode);

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    return conn.getInputStream();
                } else {
                    System.out.println("Failed to generate certificate");
                    return null;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
}



