package org.example.hotel;

import org.json.JSONObject;
import java.io.InputStream;

public class Language {
    private static JSONObject translations;
    private static String currentLanguage = "en";

    static {
        loadLanguage(currentLanguage);
    }

    public static void loadLanguage(String langCode) {
        currentLanguage = langCode;

        translations = FileOperations.loadObject("local/local_" + langCode + ".json");
    }

    public static String get(String key) {
        return translations.optString(key, key); // Returns key itself if not found
    }

    public static String getCurrentLanguage() {
        return currentLanguage;
    }
}
