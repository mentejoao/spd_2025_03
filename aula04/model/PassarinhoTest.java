package model;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PassarinhoTest
{
    public PassarinhoTest()
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
    public void testCreate()
    {
        Passarinho passarinho = new Passarinho();
        passarinho.setNome("Teste de Passarinho");
        passarinho.setEspecie("Canário");
        Passarinho passarinho_salvo = Repositorios.PASSARINHO.create(passarinho);
        System.out.println("ID do passarinho: " + passarinho_salvo.getId());
        assertTrue(passarinho_salvo.getId() > 0);
    }

    @Test
    public void testLoadAll()
    {
        // Primeiro criar um passarinho
        Passarinho passarinho = new Passarinho();
        passarinho.setNome("Passarinho para LoadAll");
        passarinho.setEspecie("Bem-te-vi");
        Repositorios.PASSARINHO.create(passarinho);
        
        // Depois carregar todos
        var lista = Repositorios.PASSARINHO.loadAll();
        assertTrue(lista.size() > 0);
        System.out.println("Total de passarinhos: " + lista.size());
    }
}
