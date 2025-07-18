package examples;

import model.Passarinho;
import model.Viveiro;
import model.Repositorios;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Exemplo demonstrando como o relacionamento Viveiro-Passarinho 
 * funciona corretamente na interface
 */
public class TesteRelacionamentoInterface {
    
    public static void main(String[] args) {
        System.out.println("=== TESTE RELACIONAMENTO VIVEIRO-PASSARINHO ===\n");
        
        // 1. Criar alguns viveiros primeiro
        System.out.println("1. Criando viveiros...");
        
        Viveiro viveiro1 = new Viveiro();
        viveiro1.setNome("Viveiro Tropical");
        viveiro1.setLocalizacao("Setor Norte");
        viveiro1.setCapacidadeMaxima(20);
        viveiro1.setArea(new BigDecimal("100.50"));
        viveiro1.setDataCriacao(new Date());
        viveiro1.setResponsavel("João Silva");
        viveiro1.setTipoViveiro("Reprodução");
        
        Viveiro viveiro1Salvo = Repositorios.VIVEIRO.create(viveiro1);
        System.out.println("   Viveiro criado: " + viveiro1Salvo.getNome() + " (ID: " + viveiro1Salvo.getId() + ")");
        
        Viveiro viveiro2 = new Viveiro();
        viveiro2.setNome("Viveiro Amazônico");
        viveiro2.setLocalizacao("Setor Sul");
        viveiro2.setCapacidadeMaxima(30);
        viveiro2.setArea(new BigDecimal("150.75"));
        viveiro2.setDataCriacao(new Date());
        viveiro2.setResponsavel("Maria Santos");
        viveiro2.setTipoViveiro("Conservação");
        
        Viveiro viveiro2Salvo = Repositorios.VIVEIRO.create(viveiro2);
        System.out.println("   Viveiro criado: " + viveiro2Salvo.getNome() + " (ID: " + viveiro2Salvo.getId() + ")");
        
        // 2. Criar passarinhos associados aos viveiros
        System.out.println("\n2. Criando passarinhos com viveiros...");
        
        Passarinho passarinho1 = new Passarinho();
        passarinho1.setSpecies("Canário Belga");
        passarinho1.setWeight(new BigDecimal("15.5"));
        passarinho1.setMigration(new Date());
        passarinho1.setViveiro(viveiro1Salvo); // Associar ao primeiro viveiro
        
        Passarinho passarinho1Salvo = Repositorios.PASSARINHO.create(passarinho1);
        System.out.println("   Passarinho criado: " + passarinho1Salvo.getSpecies() + 
                          " no viveiro: " + passarinho1Salvo.getViveiro().getNome());
        
        Passarinho passarinho2 = new Passarinho();
        passarinho2.setSpecies("Bem-te-vi");
        passarinho2.setWeight(new BigDecimal("22.0"));
        passarinho2.setMigration(new Date());
        passarinho2.setViveiro(viveiro2Salvo); // Associar ao segundo viveiro
        
        Passarinho passarinho2Salvo = Repositorios.PASSARINHO.create(passarinho2);
        System.out.println("   Passarinho criado: " + passarinho2Salvo.getSpecies() + 
                          " no viveiro: " + passarinho2Salvo.getViveiro().getNome());
        
        Passarinho passarinho3 = new Passarinho();
        passarinho3.setSpecies("Sabiá-laranjeira");
        passarinho3.setWeight(new BigDecimal("28.5"));
        passarinho3.setMigration(new Date());
        passarinho3.setViveiro(viveiro1Salvo); // Mais um no primeiro viveiro
        
        Passarinho passarinho3Salvo = Repositorios.PASSARINHO.create(passarinho3);
        System.out.println("   Passarinho criado: " + passarinho3Salvo.getSpecies() + 
                          " no viveiro: " + passarinho3Salvo.getViveiro().getNome());
        
        // 3. Listar todos os passarinhos com seus viveiros
        System.out.println("\n3. Listando todos os passarinhos e seus viveiros:");
        List<Passarinho> todos = Repositorios.PASSARINHO.loadAll();
        for (Passarinho p : todos) {
            String viveiroNome = (p.getViveiro() != null) ? p.getViveiro().getNome() : "Sem viveiro";
            System.out.println("   " + p.getSpecies() + " (ID: " + p.getId() + ") está no " + viveiroNome);
        }
        
        // 4. Mostrar como usar na interface
        System.out.println("\n4. COMO USAR NA INTERFACE JAVAFX:");
        System.out.println("   a) Vá para a aba 'Viveiro' e crie alguns viveiros primeiro");
        System.out.println("   b) Anote os nomes dos viveiros criados");
        System.out.println("   c) Vá para a aba 'Passarinho'");
        System.out.println("   d) Clique 'Adicionar'");
        System.out.println("   e) Preencha os campos: Espécie, Peso, Data de Migração");
        System.out.println("   f) No campo 'Viveiro', digite EXATAMENTE o nome do viveiro");
        System.out.println("      (ex: 'Viveiro Tropical')");
        System.out.println("   g) Clique 'Salvar'");
        System.out.println("   h) O passarinho aparecerá na tabela com o nome do viveiro");
        
        System.out.println("\n5. VIVEIROS DISPONÍVEIS PARA TESTE:");
        List<Viveiro> viveirosTeste = Repositorios.VIVEIRO.loadAll();
        for (Viveiro v : viveirosTeste) {
            System.out.println("   - \"" + v.getNome() + "\" (copie este nome exato)");
        }
        
        System.out.println("\n=== TESTE CONCLUÍDO ===");
        System.out.println("Agora execute a interface JavaFX e teste o relacionamento!");
    }
}
