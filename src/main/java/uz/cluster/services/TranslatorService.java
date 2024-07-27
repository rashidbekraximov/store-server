package uz.cluster.services;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class TranslatorService {

    public String  translator(String text) {
        // Replace 'YOUR_API_KEY' with your actual API key
        Translate translate = TranslateOptions.newBuilder().setApiKey("AIzaSyAlwGgS676QpealR0qI7TAZujoIqm0hzz4").build().getService();

        String sourceLang = "zh"; // Chinese
        String targetLang = "uz"; // Uzbek

        Translation translation = translate.translate(text, Translate.TranslateOption.sourceLanguage(sourceLang), Translate.TranslateOption.targetLanguage(targetLang));

        System.out.println("Original: " + text);
        System.out.println("Translated: " + translation.getTranslatedText());
        return translation.getTranslatedText().replaceAll("&#39;","'");
    }
}
