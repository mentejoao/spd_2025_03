package model;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.Date;

public class ViveiroTest
{
    public ViveiroTest()
    {
    }

    @BeforeEach
    public void setUp()
    {
    }

    @AfterEach
    public void tearDown()
    {
    }

    @Test
    public void testCreateViveiro()
    {
        Viveiro viveiro = new Viveiro();
        viveiro.setNome("Viveiro Central");
        viveiro.setLocalizacao("Setor Norte do Zoológico");
        viveiro.setCapacidadeMaxima(50);
        viveiro.setArea(new BigDecimal("250.5"));
        viveiro.setDataCriacao(new Date());
        viveiro.setResponsavel("João Silva");
        viveiro.setTipoViveiro("Reprodução");
        
        Viveiro viveiro_salvo = Repositorios.VIVEIRO.create(viveiro);
        System.out.println("ID do viveiro: " + viveiro_salvo.getId());
        assertTrue(viveiro_salvo.getId() > 0);
    }

    @Test
    public void testRelacionamentoViveiroPassarinho()
    {
        // Criar um viveiro
        Viveiro viveiro = new Viveiro();
        viveiro.setNome("Viveiro de Canários");
        viveiro.setLocalizacao("Ala Leste");
        viveiro.setCapacidadeMaxima(20);
        viveiro.setArea(new BigDecimal("100.0"));
        viveiro.setDataCriacao(new Date());
        viveiro.setResponsavel("Maria Santos");
        viveiro.setTipoViveiro("Exposição");
        
        Viveiro viveiro_salvo = Repositorios.VIVEIRO.create(viveiro);
        
        // Criar um passarinho associado ao viveiro
        Passarinho passarinho = new Passarinho();
        passarinho.setSpecies("Canário");
        passarinho.setWeight(new BigDecimal("15.5"));
        passarinho.setMigration(new Date());
        passarinho.setViveiro(viveiro_salvo); // Estabelecer o relacionamento
        
        Passarinho passarinho_salvo = Repositorios.PASSARINHO.create(passarinho);
        
        // Verificar se o relacionamento foi estabelecido
        assertNotNull(passarinho_salvo.getViveiro());
        assertEquals(viveiro_salvo.getId(), passarinho_salvo.getViveiro().getId());
        System.out.println("Passarinho ID: " + passarinho_salvo.getId() + 
                          " está no viveiro: " + passarinho_salvo.getViveiro().getNome());
    }

    @Test
    public void testLoadAllViveiros()
    {
        // Criar alguns viveiros
        Viveiro viveiro1 = new Viveiro();
        viveiro1.setNome("Viveiro A");
        viveiro1.setLocalizacao("Setor A");
        viveiro1.setCapacidadeMaxima(30);
        viveiro1.setArea(new BigDecimal("150.0"));
        viveiro1.setDataCriacao(new Date());
        viveiro1.setResponsavel("Ana Costa");
        viveiro1.setTipoViveiro("Reabilitação");
        Repositorios.VIVEIRO.create(viveiro1);
        
        Viveiro viveiro2 = new Viveiro();
        viveiro2.setNome("Viveiro B");
        viveiro2.setLocalizacao("Setor B");
        viveiro2.setCapacidadeMaxima(25);
        viveiro2.setArea(new BigDecimal("120.0"));
        viveiro2.setDataCriacao(new Date());
        viveiro2.setResponsavel("Carlos Lima");
        viveiro2.setTipoViveiro("Reprodução");
        Repositorios.VIVEIRO.create(viveiro2);
        
        // Carregar todos os viveiros
        var lista = Repositorios.VIVEIRO.loadAll();
        assertTrue(lista.size() >= 2);
        System.out.println("Total de viveiros: " + lista.size());
        
        // Imprimir informações dos viveiros
        for (Viveiro v : lista) {
            System.out.println("Viveiro: " + v.getNome() + 
                              " - Capacidade: " + v.getCapacidadeMaxima() + 
                              " - Tipo: " + v.getTipoViveiro());
        }
    }
}
