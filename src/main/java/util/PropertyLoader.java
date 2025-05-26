package util;

import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = PropertyLoader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("config.properties が見つかりません");
            }
            properties.load(input);
        } catch (Exception e) {
            throw new RuntimeException("設定ファイルの読み込みに失敗しました", e);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}
