public class Tarea {
    private String descripcion;
    private String departamento;
    private int urgencia;
    private Empleado empleadoAsignado;
    private Tarea tareaPrevia;

    public Tarea(String descripcion, String departamento, int urgencia, Empleado empleadoAsignado) {
        this.descripcion = descripcion;
        this.departamento = departamento;
        this.urgencia = urgencia;
        this.empleadoAsignado = empleadoAsignado;
        this.tareaPrevia = null;
    }

    public String getDescripcion() { return descripcion; }
    public String getDepartamento() { return departamento; }
    public int getUrgencia() { return urgencia; }
    public Empleado getEmpleadoAsignado() { return empleadoAsignado; }
    public Tarea getTareaPrevia() { return tareaPrevia; }

    public void setEmpleadoAsignado(Empleado empleadoAsignado) { this.empleadoAsignado = empleadoAsignado; }
    public void setTareaPrevia(Tarea tareaPrevia) { this.tareaPrevia = tareaPrevia; }

    @Override
    public String toString() {
        String urgTxt = switch (urgencia) {
            case 1 -> "Alta";
            case 2 -> "Media";
            default -> "Baja";
        };
        String infoEmpleado = (empleadoAsignado != null) ? " Asignado a: " + empleadoAsignado.getNombre() + " (ID: " + empleadoAsignado.getId() + ")" : "";
        String dependenciaTxt = (tareaPrevia != null) ? " -> Depende de: " + tareaPrevia.getDescripcion() : "";
        return "[" + departamento + "] (" + urgTxt + ") -> " + descripcion + infoEmpleado + dependenciaTxt;
    }
}