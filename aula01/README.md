### Classe Passarinho
```java
@DatabaseTable(tableName = "passarinho")
public class Passarinho
{
    @DatabaseField(generatedId = true)
    private int id;
    
    @DatabaseField
    private String species;
    
    @DatabaseField
    private BigDecimal weight;
    
    @DatabaseField(dataType=DataType.DATE)
    private Date migration;

    // Método para imprimir a data de migração formatada
    public String printMigration() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(migration);
    }

    // Início dos Getters e Setters

    /** GET Method for id */
    public int getId() {
        return this.id;
    }

    /** SET Method for id */
    public void setId(int id) {
        this.id = id;
    }

    /** GET Method for species */
    public String getSpecies() {
        return this.species;
    }

    /** SET Method for species */
    public void setSpecies(String species) {
        this.species = species;
    }

    /** GET Method for weight */
    public BigDecimal getWeight() {
        return this.weight;
    }

    /** SET Method for weight */
    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    /** GET Method for migration */
    public Date getMigration() {
        return this.migration;
    }

    /** SET Method for migration */
    public void setMigration(Date migration) {
        this.migration = migration;
    }

    // Fim dos Getters e Setters

} // End class
```


### Classe Database
```java
public class Database
{
   private String databaseName = null;
   private JdbcConnectionSource connection = null;
   
   public Database(String databaseName) {
       this.databaseName = databaseName;
   }    
   
   public JdbcConnectionSource getConnection() throws SQLException {
      if ( databaseName == null ) {
          throw new SQLException("database name is null");
      }
      if ( connection == null ) {
          try {
              connection = new JdbcConnectionSource("jdbc:sqlite:"+databaseName);             
            } catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                System.exit(0);
            }
            System.out.println("Opened database successfully");
      }
      return connection;
   }
   
   public void close() {
       if ( connection != null ) {
           try {
               connection.close();
               this.connection = null;
           } catch (java.lang.Exception e) {
               System.err.println(e);
           }
       }
   }
}
```

### Classe PassarinhoRepository (ponte entre a entity (model) e o database class, que possui DAO e ORM)
```java
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
```
