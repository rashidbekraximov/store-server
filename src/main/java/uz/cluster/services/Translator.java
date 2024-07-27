package uz.cluster.services;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;


public class Translator {

    public static void main(String[] args) {
        // Replace 'YOUR_API_KEY' with your actual API key
        Translate translate = TranslateOptions.newBuilder().setApiKey("AIzaSyAlwGgS676QpealR0qI7TAZujoIqm0hzz4").build().getService();

        String text = "推荐";
        String sourceLang = "zh"; // Chinese
        String targetLang = "uz"; // Uzbek

        Translation translation = translate.translate(text, Translate.TranslateOption.sourceLanguage(sourceLang), Translate.TranslateOption.targetLanguage(targetLang));

        System.out.println("Original: " + text);
        System.out.println("Translated: " + translation.getTranslatedText());
    }
}
