import java.util.*;
import java.io.*;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.table.TableUtils;
import java.sql.SQLException;

import javax.xml.bind.JAXBException;

public class PassarinhoRepository {
    private static Database database;
    private static Dao<Passarinho, Integer> dao;
    private List<Passarinho> loadedPassarinhos;
    private Passarinho loadedPassarinho;

    public PassarinhoRepository(Database database) {
        PassarinhoRepository.setDatabase(database);
        loadedPassarinhos = new ArrayList<>();
    }

    public static void setDatabase(Database database) {
        PassarinhoRepository.database = database;
        try {
            dao = DaoManager.createDao(database.getConnection(), Passarinho.class);
            TableUtils.createTableIfNotExists(database.getConnection(), Passarinho.class);
        } catch (SQLException e) {
            System.out.println("Erro ao inicializar DAO: " + e);
        }
    }

    public Passarinho create(Passarinho passarinho) {
        try {
            int nrows = dao.create(passarinho);
            if (nrows == 0) throw new SQLException("Erro: objeto não salvo.");
            this.loadedPassarinho = passarinho;
            loadedPassarinhos.add(passarinho);
        } catch (SQLException e) {
            System.out.println("Erro ao criar passarinho: " + e);
        }
        return passarinho;
    }

    public void update(Passarinho passarinho) {
        try {
            dao.update(passarinho);
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar passarinho: " + e);
        }
    }

    public void delete(Passarinho passarinho) {
        try {
            dao.delete(passarinho);
        } catch (SQLException e) {
            System.out.println("Erro ao deletar passarinho: " + e);
        }
    }

    public Passarinho loadFromId(int id) {
        try {
            this.loadedPassarinho = dao.queryForId(id);
            if (this.loadedPassarinho != null)
                this.loadedPassarinhos.add(this.loadedPassarinho);
        } catch (SQLException e) {
            System.out.println("Erro ao carregar por ID: " + e);
        }
        return this.loadedPassarinho;
    }

    public List<Passarinho> loadAll() {
        try {
            this.loadedPassarinhos = dao.queryForAll();
            if (!this.loadedPassarinhos.isEmpty())
                this.loadedPassarinho = this.loadedPassarinhos.get(0);
        } catch (SQLException e) {
            System.out.println("Erro ao carregar todos: " + e);
        }
        return this.loadedPassarinhos;
    }

    public String dumpData(String formato) {
        try {
            List<Passarinho> p = loadedPassarinhos != null ? loadedPassarinhos : loadAll();
            if (formato.equalsIgnoreCase("json")) {
                return new PassarinhoJsonSerializer().toJson(p);
            } else if (formato.equalsIgnoreCase("xml")) {
                return new PassarinhoXmlSerializer().toXml(p);
            }
        } catch (Exception e) {
            System.out.println("Erro no dumpData: " + e);
        }
        return "";
    }

    public boolean dumpFile(String formato, File arquivo) {
        try (FileWriter fw = new FileWriter(arquivo)) {
            fw.write(dumpData(formato));
            return true;
        } catch (IOException e) {
            System.out.println("Erro ao escrever arquivo: " + e);
        }
        return false;
    }

    public Passarinho createFromJSON(String json) {
        try {
            Passarinho p = new PassarinhoJsonSerializer().fromJsonSingle(json);
            return create(p);
        } catch (Exception e) {
            System.out.println("Erro no createFromJSON: " + e);
            return null;
        }
    }

    public Passarinho createFromXML(String xml) {
        try {
            Passarinho p = new PassarinhoXmlSerializer().fromXml(xml);
            return create(p);
        } catch (JAXBException e) {
            System.out.println("Erro no createFromXML: " + e);
            return null;
        }
    }

    public int importData(String formato, String conteudo) {
        int count = 0;
        try {
            Passarinho p = null;
            if (formato.equalsIgnoreCase("json")) {
                p = new PassarinhoJsonSerializer().fromJsonSingle(conteudo);
            } else if (formato.equalsIgnoreCase("xml")) {
                p = new PassarinhoXmlSerializer().fromXml(conteudo);
            }

            if (p != null) {
                create(p);
                count = 1;
            }
        } catch (Exception e) {
            System.out.println("Erro no importData: " + e);
        }
        return count;
    }

    public int importFile(String formato, File arquivo) {
        try {
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = new BufferedReader(new FileReader(arquivo));
            String linha;
            while ((linha = reader.readLine()) != null) {
                sb.append(linha).append("\n");
            }
            reader.close();
            return importData(formato, sb.toString());
        } catch (IOException e) {
            System.out.println("Erro no importFile: " + e);
        }
        return 0;
    }
    
    public int importFromPath(String formato, String caminho){
        File arquivo = new File(caminho);
        return importFile(formato, arquivo);
    }
}
