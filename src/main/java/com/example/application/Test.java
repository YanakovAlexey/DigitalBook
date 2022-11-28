package com.example.application;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.lang.reflect.InvocationTargetException;

public class Test {

    public static String TEST_TEXT = """
            Давно выяснено, что при оценке дизайна и композиции читаемый текст мешает сосредоточиться. 
            Lorem Ipsum используют потому, что тот обеспечивает более или менее стандартное заполнение шаблона, 
            а также реальное распределение букв и пробелов в абзацах, которое не получается при простой дубликации 
            "Здесь ваш текст.. Здесь ваш текст.. Здесь ваш текст.." 
            Многие программы электронной вёрстки и редакторы HTML используют Lorem Ipsum в качестве текста по умолчанию, 
            так что поиск по ключевым словам "lorem ipsum" сразу показывает, как много веб-страниц всё ещё дожидаются своего настоящего рождения. 
            За прошедшие годы текст Lorem Ipsum получил много версий. Некоторые версии появились по ошибке, некоторые - намеренно (например, юмористические варианты).
            """;

    public static void main(String[] args) throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeAndWait(() -> {
            var frame = myFrame();
            frame.setVisible(true);
        });
    }

    private static int countCharsInRect(Rectangle2D.Double rect) {
        Font font = new Font("Arial", Font.PLAIN, 14);
        var charSize = font.getMaxCharBounds(new FontRenderContext(null, false, false));
        var square = rect.width * rect.height;
        var charSquare = charSize.getWidth() * charSize.getHeight();
        int count = (int) (square / charSquare);
        return count;
    }

    private static JFrame myFrame() {
        Font font = new Font("Arial", Font.PLAIN, 14);

        JFrame frame = new JFrame();
        frame.setBounds(new Rectangle(200,200, 300, 500));
        frame.setVisible(true);

        TextArea label = new TextArea();
        label.setBounds(new Rectangle(0,0, 300, 500));
        label.setFont(font);
        label.setText(TEST_TEXT);

        frame.add(label);
        return frame;
    }
}
