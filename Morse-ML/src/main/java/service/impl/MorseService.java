package service.impl;

import com.google.common.collect.Lists;
import exception.NotAcceptedException;
import model.Bit;
import model.Input.InputText;
import model.Output.OutputText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.BitServiceI;
import service.MorseServiceI;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


@Service
public class MorseService implements MorseServiceI {


    private static final Map<String, String> morseDict = new HashMap<String, String>() {
        {
            put(".-", "A");
            put("-...", "B");
            put("-.-.", "C");
            put("-..", "D");
            put(".", "E");
            put("..-.", "F");
            put("--.", "G");
            put("....", "H");
            put("..", "I");
            put(".---", "J");
            put("-.-", "K");
            put(".-..", "L");
            put("--", "M");
            put("-.", "N");
            put("---", "O");
            put(".--.", "P");
            put("--.-", "Q");
            put(".-.", "R");
            put("...", "S");
            put("-", "T");
            put("..-", "U");
            put("...-", "V");
            put(".--", "W");
            put("-..-", "X");
            put("-.--", "Y");
            put("--..", "Z");
            put("-----", "0");
            put(".----", "1");
            put("..---", "2");
            put("...--", "3");
            put("....-", "4");
            put(".....", "5");
            put("-....", "6");
            put("--...", "7");
            put("---..", "8");
            put("----.", "9");
            put(".-.-.-", ".");
            put(" ", " ");
        }
    };

    @Autowired
    BitServiceI bitService;


    @Override
    public OutputText translate2Human(String morse) throws NotAcceptedException {
        if (this.isNotMorse(morse))
            throw new NotAcceptedException("El text ingresado no es morse");
        String change = morse.trim();
        String[] morse_pulses_master = change.split(" ");
        String text = "";
        for (String morse_pulses : morse_pulses_master) {
            for (String pulse : morse_pulses.split(" ")) {
                String morseChar = morseDict.get(pulse);
                text += pulse.equals("") ? " " : morseChar;
            }
        }
        return new OutputText(200, text);
    }

    @Override
    public OutputText translate2Morse(String text) throws NotAcceptedException {
        Pattern pattern = Pattern.compile("^[A-Za-z0-9 _]*[A-Za-z0-9][A-Za-z0-9 _]*$");
        Map<String, String> inverseMorseDict = morseDict.entrySet().stream().collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
        if (!text.matches(pattern.pattern()))
            throw new NotAcceptedException("El texto ingresado no es valido");
        StringBuilder morse_text = new StringBuilder();
        for (char word : text.toUpperCase().toCharArray()) {
            if (inverseMorseDict.containsKey("" + word))
                morse_text.append(inverseMorseDict.get("" + word));
            if (!inverseMorseDict.get("" + word).equals(" "))
                morse_text.append(" ");
        }
        return new OutputText(200, morse_text.toString().trim());
    }

    @Override
    public OutputText decodeBits2Morse(InputText inputText) throws NotAcceptedException {

        String text = inputText.getText();
        if (isNotBinary(text))
            throw new NotAcceptedException("El texto ingresado no es del tipo binario");
        Character prevChar = text.charAt(0);
        int currentLenght = 0;
        Bit pulsesStats = new Bit();
        Bit spacesStats;
        if (inputText.areSpaceStatsValid()) {
            spacesStats = inputText.getSpaceStats();
        } else {
            spacesStats = new Bit();
        }
        for (Character c : Lists.charactersOf(text)) {
            if (c.equals('0')) {
                if (c.equals(prevChar)) {
                    currentLenght++;
                } else {
                    bitService.updateStats(pulsesStats, currentLenght);
                    prevChar = c;
                    currentLenght = 1;
                }
            } else if (c.equals('1')) {
                if (c.equals(prevChar)) {
                    currentLenght++;
                } else {
                    if (!pulsesStats.getLengths().isEmpty()) {//ignore first space lenght from stats
                        bitService.updateStats(spacesStats, currentLenght);
                    }
                    prevChar = c;
                    currentLenght = 1;
                }
            }
        }
        if (prevChar.equals('0')) {
            spacesStats.getLengths().add(++currentLenght); //ignore last space lenght from stats
        } else {
            bitService.updateStats(pulsesStats, ++currentLenght);
        }

        String morse = bitService.parseBinaryToMorse(pulsesStats, spacesStats);

        return new OutputText(200, morse);
    }

    public boolean isNotMorse(String morse) {
        return morse.trim().matches(".*[a-zA-Z0-9].*");
    }


    public boolean isNotBinary(String bin) {
        return !bin.trim().matches("^[01]+$");
    }

}

