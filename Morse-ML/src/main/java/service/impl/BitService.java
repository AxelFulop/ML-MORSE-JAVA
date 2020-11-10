package service.impl;

import model.Bit;
import org.springframework.stereotype.Service;
import service.BitServiceI;

@Service
public class BitService implements BitServiceI {

    @Override
    public String parseBinaryToMorse(Bit pulsesStats, Bit spacesStats) {
        StringBuilder morse = new StringBuilder();
        for (int i = 0; i < pulsesStats.getLengths().size(); i++) {
            morse.append(parsePulseToDotOrDash(pulsesStats.getLengths().get(i), pulsesStats));
            morse.append(parseSpaceToEmptyOrNextLetter(spacesStats.getLengths().get(i), spacesStats));
        }
        return morse.toString().trim();
    }

    @Override
    public String parseSpaceToEmptyOrNextLetter(Integer spaceLenght, Bit spacesStats) {
        int diffWithMaxLenght = Math.abs(spaceLenght - spacesStats.getMaxLenght()),
                diffWithMinLenght = Math.abs(spaceLenght - spacesStats.getMinLenght());

        if (diffWithMaxLenght <= diffWithMinLenght) {
            return " ";
        } else {
            return "";
        }
    }

    @Override
    public String parsePulseToDotOrDash(Integer pulseLenght, Bit pulsesStats) {
        int diffWithMaxLenght = Math.abs(pulseLenght - pulsesStats.getMaxLenght()),
                diffWithMinLenght = Math.abs(pulseLenght - pulsesStats.getMinLenght());

        if (diffWithMaxLenght < diffWithMinLenght) {
            return "-";
        } else {
            return ".";
        }
    }

    @Override
    public void updateStats(Bit stats, Integer currentLenght) {
        stats.getLengths().add(currentLenght);
        stats.setMinLenght(Math.min(stats.getMinLenght(), currentLenght));
        stats.setMaxLenght(Math.max(stats.getMaxLenght(), currentLenght));
    }
}
