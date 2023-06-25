package com.example.persistencia;

import com.example.dadosApi.CharacterResponse;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ArquivoCharacterDAO{

    private final String caminho = "demo/src/main/java/com/example/persistencia/files/characters.csv";
    private static EditorTexto arquivo = new EditorTexto();

    private String toCSV(CharacterResponse characterResponse){
        String p = "";

        p+= characterResponse.getChar_id() + "; ";
        p+= characterResponse.getName() + "; ";
        p+= characterResponse.getBirthday() + "; ";
        p+= Arrays.toString(characterResponse.getOccupation()) + "; ";
        p+= characterResponse.getImg() + "; ";
        p+= characterResponse.getStatus() + "; ";
        p+= characterResponse.getNickname() + ";";
        p+= Arrays.toString(characterResponse.getAppearance()) + "; ";
        p+= characterResponse.getPortrayed() + "; ";
        p+= characterResponse.getCategory() + "; ";
        p+= Arrays.toString(characterResponse.getBetter_call_saul_appearance());

        return p;
    }

    private CharacterResponse fromCSV (String linhaCSV){

        String[] atributos = linhaCSV.split(";");

        CharacterResponse characterResponse = new CharacterResponse();
        characterResponse.setChar_id(Integer.valueOf(atributos[0]));
        characterResponse.setName(atributos[1]);
        characterResponse.setBirthday(atributos[2]);
        characterResponse.setOccupation(atributos[3].split(","));
        characterResponse.setImg(atributos[4]);
        characterResponse.setStatus(atributos[5]);
        characterResponse.setNickname(atributos[6]);
        characterResponse.setAppearance(atributos[7].split(","));
        characterResponse.setPortrayed(atributos[8]);
        characterResponse.setCategory(atributos[9]);
        characterResponse.setBetter_call_saul_appearance(atributos[10].split(","));

        return characterResponse;
    }

    private List<String> listaCharacterToString(List<CharacterResponse> characterResponse){

        List<String> arquivo = new LinkedList<String>();
        for(CharacterResponse character : characterResponse){
            arquivo.add(toCSV(character));
        }

        return arquivo;
    }

    private List<CharacterResponse> stringToListaCharacter(List<String> arquivo){
        List<CharacterResponse> characters = new LinkedList<CharacterResponse>();
        for(String linha : arquivo){
            characters.add(fromCSV(linha));
        }
        return characters;
    }

    public List<CharacterResponse> leCharacterArquivo(){
        return stringToListaCharacter(arquivo.leTexto(caminho));
    }

    public void salvaCharactersArquivo(List<CharacterResponse> character){
        arquivo.gravaTexto(caminho, listaCharacterToString(character));
    }

    public void salvaCharacterArquivo(CharacterResponse character){
        arquivo.gravaTexto(caminho, toCSV(character));
    }

}
