# ML-MORSE-JAVA

Challenge Backend Mercado Libre

Dependencias base:
 * Java 13

# MorseController

* decodeMorseToText: Metodo que recibe un texto en codigo morse y devuelve la traduccion en español.
* endpoint: /2Text
* method: Post
* Body: {"text" : ".... --- .-.. .-  -- . .-.. .."}
* Response OK: { code:200, response: 'HOLA MELI'}
* Response NO OK: {  "code": 400, response: "El texto ingresado no es del tipo morse"}

En caso de que el texto ingresado no sea morse, se produce una excepcion.

------------********---------------
* decodeTextToMorse: Metodo que recibe un texto en español y devuelve la traduccion en codigo morse.
* endpoint: /2Morse
* method: Post
* Body: {"text" : "HOLA MELI"}
* Response OK: { code:200, response: ".... --- .-.. .-  -- . .-.. .."}
* Response NO OK: { "code": 400, response: "El texto ingresado no es del tipo texto"}

En caso de que el texto ingresado no sea texto puro, se produce una excepcion.

------------********--------------
* decodeBinToMorse: Metodo que recibe un texto en binario y devuelve la tradducion en codigo morse.
* endpoint: /bin2Morse
* method: Post
* Body: {"text" : {"00000000110110110011100000111111000111111001111110000111011111111011101110000000110001111110000011111100111110000000110000110111111110111011100000011011100000000000"}
* Response OK: { code:200, response: ".... --- .-.. .-  -- . .-.. .."}
* Response NO OK: { "code": 400, response: "El texto ingresado no es del tipo binario"}

En caso de que el texto ingresado no sea binario puro, se produce una excepcion.


# Forma de uso local:

* Transformar morse a texto (separar palabras por doble espacio)
$ curl -X POST "localhost:8080/translate/2Text" -d "{text: '.... --- .-.. .- -- . .-.. ..'}"
{ code:200, response: 'HOLA MELI'}

* Transformar texto a morse
$ curl -X POST "localhost:8080/translate/2Morse" -d "{text: 'HOLA MELI'}"
{ code:200, response: '.... --- .-.. .- -- . .-.. ..'}
 
 # Forma de uso web:

* Transformar morse a texto (separar palabras por doble espacio)
$ curl -X POST "http://meli-challenge.us-east-1.elasticbeanstalk.com/translate/2Text" -d "{text: '.... --- .-.. .- -- . .-.. ..'}"
{ code:200, response: 'HOLA MELI'}

(Si da error) => usar curl -i -X POST -H "Content-Type: application/json" -d '{"text": ".... --- .-.. .- -- . .-.. .."}'  http://meli-challenge.us-east-1.elasticbeanstalk.com/translate/2Text
{ code:200, response: 'HOLA MELI'}


* Transformar texto a morse
$ curl -X POST "http://meli-challenge.us-east-1.elasticbeanstalk.com/translate/2Morse" -d "{text: 'HOLA MELI'}"
{ code:200, response: '.... --- .-.. .- -- . .-.. ..'}

(Si da error) => usar curl -i -X POST -H "Content-Type: application/json" -d '{"text": "HOLA MELI"}'  http://meli-challenge.us-east-1.elasticbeanstalk.com/translate/2Morse
{ code:200, response: '.... --- .-.. .- -- . .-.. ..'}
  
# Notas
 * En caso de ingresar un caracter NO valido dentro de los esperados, se disparará un Exception. En caso de estar siendo ejecutado desde la API devolverá un Bad Request (Error 406, code 400).
 
 * Se utiliza como fin de mensaje una pausa prolongada.
 

