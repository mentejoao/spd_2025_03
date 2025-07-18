package model;

public class Repositorios {
    public static Database database = new Database("app.sqlite");
    public static final Repositorio<Passarinho, Integer> PASSARINHO =
        new Repositorio<>(database, Passarinho.class);
    public static final Repositorio<Viveiro, Integer> VIVEIRO =
        new Repositorio<>(database, Viveiro.class);
}
