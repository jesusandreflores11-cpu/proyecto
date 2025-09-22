import java.util.LinkedList;
import java.util.List;

public class ListaTareas {
    private LinkedList<Tarea> lista = new LinkedList<>();

    public void insertar(Tarea tarea) {
        lista.add(tarea);
    }

    public boolean eliminar(String descripcion) {
        for (Tarea t : lista) {
            if (t.getDescripcion().equalsIgnoreCase(descripcion)) {
                lista.remove(t);
                return true;
            }
        }
        return false;
    }

    public List<Tarea> getAll() {
        return new ArrayList<>(lista);
    }
}