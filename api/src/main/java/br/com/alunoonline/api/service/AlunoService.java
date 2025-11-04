package br.com.alunoonline.api.service;

import br.com.alunoonline.api.model.Aluno;
import br.com.alunoonline.api.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {
    @Autowired //injeção de dependencia
    private AlunoRepository alunoRepository;

    public void criarAluno(Aluno aluno) {
        alunoRepository.save(aluno);

    }

    public List<Aluno> buscarTodosAlunos() {
        return alunoRepository.findAll();
    }

    public Optional<Aluno> buscarAlunoPorId(Long id) {
        return alunoRepository.findById(id);
    }

    public void deletarAlunoPorId(Long id){
        alunoRepository.deleteById(id);
    }
    public void atualizarAlunoPorId(Long id, Aluno alunoAtualizado){
        // primeiro passo : ver se o aluno existe no BD
        Optional<Aluno> alunoDoBd = buscarAlunoPorId(id);

        //se nao existir:
        if (alunoDoBd.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Aluno não encontrado no BD");
        }
        // se existir aluno com esse Id
        // armazenar em uma variavel para editar depois
        Aluno alunoParaEditar = alunoDoBd.get();

        // com esse aluno editavel acima
        // os sets necessarios para atualizar os atributos
        alunoParaEditar.setNomeCompleto(alunoAtualizado.getNomeCompleto());
        alunoParaEditar.setEmail(alunoParaEditar.getEmail());
        alunoParaEditar.setCpf(alunoParaEditar.getCpf());

        //levar para o repository
        alunoRepository.save(alunoParaEditar);

    }
}