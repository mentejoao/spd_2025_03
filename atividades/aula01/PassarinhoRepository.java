import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.Dao;
import java.sql.SQLException;
import com.j256.ormlite.table.TableUtils;
import java.util.List;
import java.util.ArrayList;

public class PassarinhoRepository
{
    private static Database database;
    private static Dao<Passarinho, Integer> dao;
    private List<Passarinho> loadedPassarinhos;
    private Passarinho loadedPassarinho; 
    
    public PassarinhoRepository(Database database) {
        PassarinhoRepository.setDatabase(database);
        loadedPassarinhos = new ArrayList<Passarinho>();
    }
    
    public static void setDatabase(Database database) {
        PassarinhoRepository.database = database;
        try {
            dao = DaoManager.createDao(database.getConnection(), Passarinho.class);
            TableUtils.createTableIfNotExists(database.getConnection(), Passarinho.class);
        }
        catch(SQLException e) {
            System.out.println(e);
        }            
    }
    
    public Passarinho create(Passarinho passarinho) {
        int nrows = 0;
        try {
            nrows = dao.create(passarinho);
            if (nrows == 0)
                throw new SQLException("Error: object not saved");
            this.loadedPassarinho = passarinho;
            loadedPassarinhos.add(passarinho);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return passarinho;
    }    

    public void update(Passarinho passarinho) {
        try {
            dao.update(passarinho);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void delete(Passarinho passarinho) {
        try {
            dao.delete(passarinho);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public Passarinho loadFromId(int id) {
        try {
            this.loadedPassarinho = dao.queryForId(id);
            if (this.loadedPassarinho != null)
                this.loadedPassarinhos.add(this.loadedPassarinho);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return this.loadedPassarinho;
    }    
    
    public List<Passarinho> loadAll() {
        try {
            this.loadedPassarinhos = dao.queryForAll();
            if (this.loadedPassarinhos.size() != 0)
                this.loadedPassarinho = this.loadedPassarinhos.get(0);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return this.loadedPassarinhos;
    }

}
