package service;

import model.Bit;

public interface BitServiceI {

     String parseSpaceToEmptyOrNextLetter(Integer spaceLenght, Bit spacesStats);
     String parsePulseToDotOrDash(Integer pulseLenght, Bit pulsesStats);
     String parseBinaryToMorse(Bit pulsesStats, Bit spacesStats);
     void updateStats(Bit stats, Integer currentLenght);

}
