package examples;

import model.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Classe para testar o CRUD do Passarinho com relacionamento ao Viveiro
 * Execute esta classe para verificar se o relacionamento está funcionando
 */
public class TestePassarinhoComViveiro {
    
    public static void main(String[] args) {
        System.out.println("=== Teste CRUD Passarinho com Viveiro ===\n");
        
        // 1. Criar alguns viveiros primeiro
        System.out.println("1. Criando viveiros...");
        Viveiro viveiro1 = new Viveiro();
        viveiro1.setNome("Viveiro Tropical");
        viveiro1.setLocalizacao("Setor A");
        viveiro1.setDataCriacao(new Date());
        
        Viveiro viveiro2 = new Viveiro();
        viveiro2.setNome("Viveiro Temperado");
        viveiro2.setLocalizacao("Setor B");
        viveiro2.setDataCriacao(new Date());
        
        // Salvar viveiros
        Repositorios.VIVEIRO.save(viveiro1);
        Repositorios.VIVEIRO.save(viveiro2);
        
        System.out.println("Viveiros criados:");
        System.out.println("- " + viveiro1.getNome() + " (ID: " + viveiro1.getId() + ")");
        System.out.println("- " + viveiro2.getNome() + " (ID: " + viveiro2.getId() + ")");
        
        // 2. Criar passarinhos e associar aos viveiros
        System.out.println("\n2. Criando passarinhos com viveiros...");
        
        Passarinho p1 = new Passarinho();
        p1.setSpecies("Bem-te-vi");
        p1.setWeight(new BigDecimal("25.5"));
        p1.setMigration(new Date());
        p1.setViveiro(viveiro1);
        
        Passarinho p2 = new Passarinho();
        p2.setSpecies("Sabiá");
        p2.setWeight(new BigDecimal("30.0"));
        p2.setMigration(new Date());
        p2.setViveiro(viveiro2);
        
        Passarinho p3 = new Passarinho();
        p3.setSpecies("Curió");
        p3.setWeight(new BigDecimal("18.2"));
        p3.setMigration(new Date());
        p3.setViveiro(viveiro1);
        
        // Salvar passarinhos
        Repositorios.PASSARINHO.save(p1);
        Repositorios.PASSARINHO.save(p2);
        Repositorios.PASSARINHO.save(p3);
        
        System.out.println("Passarinhos criados:");
        System.out.println("- " + p1.getSpecies() + " no " + p1.getViveiro().getNome() + " (ID: " + p1.getId() + ")");
        System.out.println("- " + p2.getSpecies() + " no " + p2.getViveiro().getNome() + " (ID: " + p2.getId() + ")");
        System.out.println("- " + p3.getSpecies() + " no " + p3.getViveiro().getNome() + " (ID: " + p3.getId() + ")");
        
        // 3. Listar todos os passarinhos e seus viveiros
        System.out.println("\n3. Listando todos os passarinhos e seus viveiros:");
        for (Passarinho p : Repositorios.PASSARINHO.loadAll()) {
            String viveiroNome = p.getViveiro() != null ? p.getViveiro().getNome() : "Sem viveiro";
            System.out.println("- " + p.getSpecies() + " (ID: " + p.getId() + ") -> " + viveiroNome);
        }
        
        // 4. Testar atualização de viveiro
        System.out.println("\n4. Testando atualização de viveiro...");
        p1.setViveiro(viveiro2);
        Repositorios.PASSARINHO.save(p1);
        System.out.println("- " + p1.getSpecies() + " movido para " + p1.getViveiro().getNome());
        
        // 5. Verificar se a atualização funcionou
        System.out.println("\n5. Verificando atualização:");
        Passarinho p1Atualizado = Repositorios.PASSARINHO.findById(p1.getId());
        if (p1Atualizado != null && p1Atualizado.getViveiro() != null) {
            System.out.println("- " + p1Atualizado.getSpecies() + " está no " + p1Atualizado.getViveiro().getNome());
        }
        
        System.out.println("\n=== Teste concluído com sucesso! ===");
        System.out.println("Agora você pode executar a interface JavaFX e o viveiro deve aparecer corretamente.");
    }
}
