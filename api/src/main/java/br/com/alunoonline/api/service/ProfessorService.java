package br.com.alunoonline.api.service;


import br.com.alunoonline.api.model.Professor;
import br.com.alunoonline.api.repository.AlunoRepository;
import br.com.alunoonline.api.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    public void criarProfessor(Professor prof){
        professorRepository.save(prof);
    }

    public List<Professor> buscarTodosProfessor(){
        return professorRepository.findAll();
    }

    public Optional<Professor> buscarProfessorPorId(Long id){
        return professorRepository.findById(id);
    }

    public void deletarProfessorPorId(Long id){
        professorRepository.deleteById(id);
    }


    public void atualizarProfessorPorId(Long id, Professor atualizarProfessor){
        Optional<Professor> professorBancoDeDados = buscarProfessorPorId(id);

        if(professorBancoDeDados.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Professor não encontrado");
        }
        else{
            Professor professorParaEditar = professorBancoDeDados.get();

            professorParaEditar.setNomeProfessor(atualizarProfessor.getNomeProfessor());
            professorParaEditar.setEmail(atualizarProfessor.getEmail());
            professorParaEditar.setCpf(atualizarProfessor.getCpf());

            professorRepository.save(professorParaEditar);
        }


    }


}