package com.example.morzeovka;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.HashMap;
import java.util.Map;

public class HelloController {

    @FXML
    private TextField textInput;

    @FXML
    private TextArea morseOutput;
    
    private static final Map<Character, String> MORSE_CODE = new HashMap<>();
    static {
        MORSE_CODE.put('A', ".-");
        MORSE_CODE.put('B', "-...");
        MORSE_CODE.put('C', "-.-.");
        MORSE_CODE.put('D', "-..");
        MORSE_CODE.put('E', ".");
        MORSE_CODE.put('F', "..-.");
        MORSE_CODE.put('G', "--.");
        MORSE_CODE.put('H', "....");
        MORSE_CODE.put('I', "..");
        MORSE_CODE.put('J', ".---");
        MORSE_CODE.put('K', "-.-");
        MORSE_CODE.put('L', ".-..");
        MORSE_CODE.put('M', "--");
        MORSE_CODE.put('N', "-.");
        MORSE_CODE.put('O', "---");
        MORSE_CODE.put('P', ".--.");
        MORSE_CODE.put('Q', "--.-");
        MORSE_CODE.put('R', ".-.");
        MORSE_CODE.put('S', "...");
        MORSE_CODE.put('T', "-");
        MORSE_CODE.put('U', "..-");
        MORSE_CODE.put('V', "...-");
        MORSE_CODE.put('W', ".--");
        MORSE_CODE.put('X', "-..-");
        MORSE_CODE.put('Y', "-.--");
        MORSE_CODE.put('Z', "--..");
        MORSE_CODE.put('0', "-----");
        MORSE_CODE.put('1', ".----");
        MORSE_CODE.put('2', "..---");
        MORSE_CODE.put('3', "...--");
        MORSE_CODE.put('4', "....-");
        MORSE_CODE.put('5', ".....");
        MORSE_CODE.put('6', "-....");
        MORSE_CODE.put('7', "--...");
        MORSE_CODE.put('8', "---..");
        MORSE_CODE.put('9', "----.");
        MORSE_CODE.put(' ', " ");
    }

    private String textToMorse(String text) {
        StringBuilder morse = new StringBuilder();
        for (char c : text.toUpperCase().toCharArray()) {
            if (MORSE_CODE.containsKey(c)) {
                morse.append(MORSE_CODE.get(c)).append("|");
            }
        }
        return morse.toString();
    }

    @FXML
    private void translate() {
        String inputText = textInput.getText();
        String morseText = textToMorse(inputText);
        morseOutput.setText(morseText);
    }
}
