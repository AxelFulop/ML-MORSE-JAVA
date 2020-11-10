package controller;

import exception.NotAcceptedException;
import model.Input.InputText;
import model.Output.OutputText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import service.MorseServiceI;
import service.impl.MorseService;

@RestController
@RequestMapping(value = "/translate")
public class MorseController {

    @Autowired
    MorseService morseService;

    OutputText outputText = null;

    HttpStatus httpStatus;

    @PostMapping("/2Text")
    private ResponseEntity decodeMorseToText(@RequestBody InputText text) throws NotAcceptedException {

        try {
            outputText = morseService.translate2Human(text.getText());
            httpStatus = HttpStatus.OK;
        } catch (NotAcceptedException ex) {
            outputText = new OutputText(400, ex.getMessage());
            httpStatus = HttpStatus.NOT_ACCEPTABLE;
        }
        return new ResponseEntity<>(outputText, httpStatus);

    }

    @PostMapping("/2Morse")
    private ResponseEntity decodeTextToMorse(@RequestBody InputText text) throws NotAcceptedException {
        try {
            outputText = morseService.translate2Morse(text.getText());
            httpStatus = HttpStatus.OK;
        } catch (NotAcceptedException ex) {
            outputText = new OutputText(400, ex.getMessage());
            httpStatus = HttpStatus.NOT_ACCEPTABLE;
        }
        return new ResponseEntity<>(outputText, httpStatus);
    }

    @PostMapping("/bin2Morse")
    private ResponseEntity decodeBitsToMorse(@RequestBody InputText text) throws NotAcceptedException {
        try {
            outputText = morseService.decodeBits2Morse(text);
            httpStatus = HttpStatus.OK;
        } catch (NotAcceptedException ex) {
            outputText = new OutputText(400, ex.getMessage());
            httpStatus = HttpStatus.NOT_ACCEPTABLE;
        }
        return new ResponseEntity<>(outputText, httpStatus);
    }

}
