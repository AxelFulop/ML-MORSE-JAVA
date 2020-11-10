package service;


import controller.ControllerTest;
import exception.NotAcceptedException;
import model.Input.InputText;
import model.Output.OutputText;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.impl.MorseService;

import static org.junit.Assert.assertEquals;



@SpringBootTest
public class ServiceTest {

    private static final String MORSE_HOLA_MELI_200 = ".... --- .-.. .-  -- . .-.. ..";
    private static final String MORSE_HOLA_MELI_400 = "..211.. 2--- .-.. .-  -- . .-.. ..";
    private static final String HOLA_MELI_TEXT_200 = "HOLA MELI";
    private static final String HOLA_MELI_TEXT_400 = "..-- HOLA MELI";
    private static final String HOLA_MELI_BIN_200 = "000000001101101100111000001111110001111110011111100000001110111111110111011100000001100011111100000111111001111110000000110000110111111110111011100000011011100000000000";
    private static final String HOLA_MELI_BIN_400 = "..--00000000110110110011100ASD00011111100011111100111111000000011101111111101110\n" +
            "111000000011000111111000001111110011111100000001100001101111111DD1011101110\n" +
            "0000011011100000000000....";


    private MorseService morseService = new MorseService();


    @Test
    public void translateToHuman_200() throws NotAcceptedException {
        OutputText outputText = morseService.translate2Human(MORSE_HOLA_MELI_200);
        assertEquals(HOLA_MELI_TEXT_200,outputText.getRespose());
        assertEquals(200, outputText.getCode());
    }

    @Test(expected = NotAcceptedException.class)
    public void translateToHuman_400() throws NotAcceptedException {
        OutputText outputText = morseService.translate2Human(MORSE_HOLA_MELI_400);
    }

    @Test
    public void translateToMorse_200() throws NotAcceptedException {
        OutputText outputText = morseService.translate2Morse(HOLA_MELI_TEXT_200);
        assertEquals(MORSE_HOLA_MELI_200,outputText.getRespose());
        assertEquals(200, outputText.getCode());
    }

    @Test(expected = NotAcceptedException.class)
    public void translateToMorse_400() throws NotAcceptedException {
        OutputText outputText = morseService.translate2Human(HOLA_MELI_TEXT_400);
    }

    @Test
    public void decodeBinToMorse_200() throws NotAcceptedException {
        InputText inputText = new InputText(HOLA_MELI_BIN_200);
        OutputText outputText = morseService.decodeBits2Morse(inputText);
        assertEquals(MORSE_HOLA_MELI_200,outputText.getRespose());
        assertEquals(200, outputText.getCode());
    }

    @Test(expected = NotAcceptedException.class)
    public void decodeBinToMorse_400() throws NotAcceptedException {
        OutputText outputText = morseService.translate2Human(HOLA_MELI_BIN_400);
    }
}
