package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Input.InputText;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static  org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = ControllerTest.class)
public class ControllerTest {

    private static  final  String TRANSLATE_2TEXT = "/translate/2Text";
    private static  final  String TRANSLATE_2MORSE = "/translate/2Morse";
    private static  final  String TRANSLATE_DECODEBINARY2MORSE = "/bin2Morse";

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private MorseController morseController;

    ObjectMapper jsonMapper = new ObjectMapper();
    
    private static final String MORSE_HOLA_MELI = ".... --- .-.. .-  -- . .-.. ..";
    private static final String HOLA_MELI_TEXT = "HOLA MELI";


    @Before
    public void  setUp(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(morseController).build();
    }

    @Test
    public void toTextTest() throws Exception {

        InputText inputText = new InputText();
        inputText.setText(MORSE_HOLA_MELI);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.
                post(TRANSLATE_2TEXT).
                contentType("application/json").
                content(jsonMapper.writeValueAsString(inputText))).
                andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        assertTrue(result.getResponse().getStatus() == 200);

    }




}
