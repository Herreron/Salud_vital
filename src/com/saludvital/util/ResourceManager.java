package com.saludvital.util;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.swing.ImageIcon;

public class ResourceManager {

    private static final String FONTS_PATH = "/fonts/";
    private static final String ICONS_PATH = "/icons/";
    private static final String REGULAR_FONT = "RobotoCondensed-Regular.ttf";
    private static final String BOLD_FONT = "RobotoCondensed-Bold.ttf";

    public static Font getRegularFont(float size) {
        Font font = loadFont(REGULAR_FONT, size);
        if (font != null) {
            return font;
        }
        return new Font("SansSerif", Font.PLAIN, Math.round(size));
    }

    public static Font getBoldFont(float size) {
        Font font = loadFont(BOLD_FONT, size);
        if (font != null) {
            return font;
        }
        return new Font("SansSerif", Font.BOLD, Math.round(size));
    }

    private static Font loadFont(String fileName, float size) {
        try (InputStream stream = ResourceManager.class.getResourceAsStream(FONTS_PATH + fileName)) {
            if (stream == null) {
                return null;
            }
            Font baseFont = Font.createFont(Font.TRUETYPE_FONT, stream);
            return baseFont.deriveFont(size);
        } catch (FontFormatException | IOException e) {
            return null;
        }
    }

    // Pasa -1 en width o height si deseas mantener la proporcion original
    public static ImageIcon loadIcon(String fileName, int width, int height) {
        URL resourceUrl = ResourceManager.class.getResource(ICONS_PATH + fileName);
        if (resourceUrl == null) {
            return null;
        }
        ImageIcon originalIcon = new ImageIcon(resourceUrl);
        Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }
}