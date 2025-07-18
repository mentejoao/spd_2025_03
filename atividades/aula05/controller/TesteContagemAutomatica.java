package controller;

import model.*;
import java.util.List;

/**
 * Classe para testar a funcionalidade de contagem automática de alunos.
 * Demonstra como a implementação funciona.
 */
public class TesteContagemAutomatica {
    
    public static void main(String[] args) {
        System.out.println("=== Teste de Contagem Automática de Alunos ===");
        
        // Criar instâncias dos repositórios
        Repositorio<Disciplina, Integer> disciplinaRepo = Repositorios.DISCIPLINA;
        Repositorio<Turma, Integer> turmaRepo = Repositorios.TURMA;
        Repositorio<Estudante, Integer> estudanteRepo = Repositorios.ESTUDANTE;
        Repositorio<Matricula, Integer> matriculaRepo = Repositorios.MATRICULA;
        
        try {
            // 1. Criar uma disciplina
            Disciplina disciplina = new Disciplina();
            disciplina.setCodigo("TESTE001");
            disciplina.setTitulo("Disciplina de Teste");
            disciplina.setEmenta("Ementa de teste");
            disciplina = disciplinaRepo.create(disciplina);
            System.out.println("Disciplina criada: " + disciplina.getCodigo());
            
            // 2. Criar uma turma
            Turma turma = new Turma();
            turma.setCodigo("TURMA001");
            turma.setDisciplina(disciplina);
            turma.setVagasDisponiveis(30);
            turma.setAlunosMatriculados(0);
            turma = turmaRepo.create(turma);
            System.out.println("Turma criada: " + turma.getCodigo() + " - Alunos: " + turma.getAlunosMatriculados());
            
            // 3. Criar estudantes
            Estudante estudante1 = new Estudante();
            estudante1.setNomeCompleto("João Silva");
            estudante1.setDataDeNascimento("1990-01-01");
            estudante1.setMatricula(12345);
            estudante1 = estudanteRepo.create(estudante1);
            
            Estudante estudante2 = new Estudante();
            estudante2.setNomeCompleto("Maria Santos");
            estudante2.setDataDeNascimento("1992-05-15");
            estudante2.setMatricula(67890);
            estudante2 = estudanteRepo.create(estudante2);
            
            System.out.println("Estudantes criados");
            
            // 4. Simular contagem manual (como seria feito pelo MatriculaController)
            MatriculaController controller = new MatriculaController();
            
            // Criar matrículas
            Matricula matricula1 = new Matricula();
            matricula1.setEstudante(estudante1);
            matricula1.setTurma(turma);
            matricula1.setStatus(StatusMatricula.CONFIRMADA);
            matricula1 = matriculaRepo.create(matricula1);
            
            Matricula matricula2 = new Matricula();
            matricula2.setEstudante(estudante2);
            matricula2.setTurma(turma);
            matricula2.setStatus(StatusMatricula.SOLICITADA);
            matricula2 = matriculaRepo.create(matricula2);
            
            // Simular atualização da contagem
            System.out.println("\nSimulando contagem automática...");
            
            // Verificar turma após matrículas
            turma = turmaRepo.loadFromId(turma.getId());
            System.out.println("Turma após matrículas - Alunos: " + turma.getAlunosMatriculados());
            
            // Limpar dados de teste
            matriculaRepo.delete(matricula1);
            matriculaRepo.delete(matricula2);
            estudanteRepo.delete(estudante1);
            estudanteRepo.delete(estudante2);
            turmaRepo.delete(turma);
            disciplinaRepo.delete(disciplina);
            
            System.out.println("Teste concluído com sucesso!");
            
        } catch (Exception e) {
            System.err.println("Erro durante o teste: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
