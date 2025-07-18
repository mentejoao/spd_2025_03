import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.List;

public class PassarinhoJsonSerializer {

    private Gson gson = new Gson();

    public String toJson(List<Passarinho> lista) {
        return gson.toJson(lista);
    }

    public List<Passarinho> fromJson(String json) {
        return gson.fromJson(json, new TypeToken<List<Passarinho>>(){}.getType());
    }

    public String toJson(Passarinho p) {
        return gson.toJson(p);
    }

    public Passarinho fromJsonSingle(String json) {
        return gson.fromJson(json, Passarinho.class);
    }
}
