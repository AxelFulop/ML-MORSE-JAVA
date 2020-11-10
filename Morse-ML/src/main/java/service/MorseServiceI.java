package service;

import exception.NotAcceptedException;
import model.Input.InputText;
import model.Output.OutputText;
import org.springframework.stereotype.Service;


@Service
public interface MorseServiceI {

    OutputText translate2Morse(String text) throws  NotAcceptedException;
    OutputText translate2Human(String morse) throws NotAcceptedException;
    OutputText decodeBits2Morse(InputText inputText) throws  NotAcceptedException;
}
