package com.example.persistencia;

import java.util.List;

import com.example.dadosApi.CharacterResponse;

public class CharacterDAO {
    
    private ArquivoCharacterDAO arquivoCharacterDAO = new ArquivoCharacterDAO();

    public void insert(CharacterResponse character){
        arquivoCharacterDAO.salvaCharacterArquivo(character);
    }

    public void delete(CharacterResponse character){
        List<CharacterResponse> characters = arquivoCharacterDAO.leCharacterArquivo();
        int index = 0;
        for(int i = 0 ; i < characters.size() ; i++){
            if(characters.get(i).equals(character)){
                index = i;
            }
        }
        characters.remove(index);
        arquivoCharacterDAO.salvaCharactersArquivo(characters);
    }

    public List<CharacterResponse> getAll(){
        return arquivoCharacterDAO.leCharacterArquivo();
    }

}
