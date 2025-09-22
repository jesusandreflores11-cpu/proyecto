import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.List;

public class RestauranteAppGUI extends JFrame {
    private final PilaTareas pila;
    private final ColaPrioridades cola;
    private final ListaTareas lista;
    private final RestaurantePro restaurante;

    private final JTextArea areaPila = new JTextArea(12, 30);
    private final JTextArea areaCola = new JTextArea(12, 30);
    private final JTextArea areaLista = new JTextArea(12, 30);
    private final JTextArea areaInfoEmpleado = new JTextArea(5, 25);

    private JTextField txtDesc = new JTextField(20);
    private JTextField txtEmpleadoBuscar = new JTextField(15);
    private ButtonGroup grupoUrgencia = new ButtonGroup();
    private JRadioButton rbAlta = new JRadioButton("Alta (1)");
    private JRadioButton rbMedia = new JRadioButton("Media (2)");
    private JRadioButton rbBaja = new JRadioButton("Baja (3)");
    private String departamentoSeleccionado = "";
    private Tarea tareaDependiente;

    public RestauranteAppGUI(RestaurantePro restaurante) {
        this.restaurante = restaurante;
        this.pila = restaurante.getPila();
        this.cola = restaurante.getCola();
        this.lista = restaurante.getLista();

        setTitle("Restaurante - Gestión de Tareas PRO");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel panelEntrada = new JPanel(new GridBagLayout());
        panelEntrada.setBorder(BorderFactory.createTitledBorder("Crear nueva tarea"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelEntrada.add(new JLabel("Descripción de la tarea:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        panelEntrada.add(txtDesc, gbc);
        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelEntrada.add(new JLabel("Departamento:"), gbc);
        gbc.gridx = 1;
        JButton btnCocina = new JButton("Cocina");
        panelEntrada.add(btnCocina, gbc);
        gbc.gridx = 2;
        JButton btnBarra = new JButton("Barra");
        panelEntrada.add(btnBarra, gbc);
        gbc.gridx = 3;
        JButton btnCaja = new JButton("Caja");
        panelEntrada.add(btnCaja, gbc);
        gbc.gridx = 4;
        JButton btnOtro = new JButton("Otro");
        panelEntrada.add(btnOtro, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panelEntrada.add(new JLabel("Nivel de urgencia:"), gbc);
        gbc.gridx = 1;
        panelEntrada.add(rbAlta, gbc);
        grupoUrgencia.add(rbAlta);
        gbc.gridx = 2;
        panelEntrada.add(rbMedia, gbc);
        grupoUrgencia.add(rbMedia);
        gbc.gridx = 3;
        panelEntrada.add(rbBaja, gbc);
        grupoUrgencia.add(rbBaja);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 5;
        JButton btnDependencia = new JButton("Agregar Dependencia (opcional)");
        panelEntrada.add(btnDependencia, gbc);
        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 5;
        JPanel panelBotonesAgregar = new JPanel(new GridLayout(1, 3, 5, 5));
        JButton btnPila = new JButton("Agregar Urgente (*Pila*)");
        JButton btnCola = new JButton("Agregar Programada (*Cola*)");
        JButton btnLista = new JButton("Agregar a Departamento (*Lista*)");
        panelBotonesAgregar.add(btnPila);
        panelBotonesAgregar.add(btnCola);
        panelBotonesAgregar.add(btnLista);
        panelEntrada.add(panelBotonesAgregar, gbc);
        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        panelEntrada.add(new JLabel("ID del empleado:"), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        panelEntrada.add(txtEmpleadoBuscar, gbc);
        gbc.gridx = 3;
        gbc.gridwidth = 2;
        JButton btnBuscarEmpleado = new JButton("Buscar Empleado");
        panelEntrada.add(btnBuscarEmpleado, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 5;
        areaInfoEmpleado.setEditable(false);
        JScrollPane scrollInfoEmpleado = new JScrollPane(areaInfoEmpleado);
        scrollInfoEmpleado.setBorder(BorderFactory.createTitledBorder("Información del Empleado"));
        panelEntrada.add(scrollInfoEmpleado, gbc);
        
        JPanel panelVista = new JPanel(new GridLayout(1, 3, 10, 10));
        areaPila.setEditable(false);
        areaCola.setEditable(false);
        areaLista.setEditable(false);
        
        panelVista.add(new JScrollPane(areaPila));
        panelVista.add(new JScrollPane(areaCola));
        panelVista.add(new JScrollPane(areaLista));
        panelVista.setBorder(BorderFactory.createTitledBorder("Tareas pendientes"));

        JPanel panelAcciones = new JPanel(new FlowLayout());
        JButton btnAtenderPila = new JButton("Atender Urgente (*Pila*)");
        JButton btnAtenderCola = new JButton("Atender Programada (*Cola*)");
        JButton btnEliminarLista = new JButton("Eliminar de Departamento (*Lista*)");
        JButton btnVerEmpleados = new JButton("Ver Empleados");
        
        panelAcciones.add(btnAtenderPila);
        panelAcciones.add(btnAtenderCola);
        panelAcciones.add(btnEliminarLista);
        panelAcciones.add(btnVerEmpleados);
        panelAcciones.setBorder(BorderFactory.createTitledBorder("Acciones"));

        add(panelEntrada, BorderLayout.NORTH);
        add(panelVista, BorderLayout.CENTER);
        add(panelAcciones, BorderLayout.SOUTH);

        ActionListener agregar = e -> {
            String desc = txtDesc.getText().trim();
            int urg = 0;

            if (desc.isEmpty() || departamentoSeleccionado.isEmpty() || grupoUrgencia.getSelection() == null) {
                JOptionPane.showMessageDialog(this, "Debe completar todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (rbAlta.isSelected()) urg = 1;
            else if (rbMedia.isSelected()) urg = 2;
            else urg = 3;

            Empleado empleadoAsignado = restaurante.asignarEmpleado(departamentoSeleccionado);
            if (empleadoAsignado == null) {
                JOptionPane.showMessageDialog(this, "No hay empleados disponibles en ese departamento.");
                return;
            }

            Tarea t = new Tarea(desc, departamentoSeleccionado, urg, empleadoAsignado);
            if (tareaDependiente != null) {
                 t.setTareaPrevia(tareaDependiente);
                 tareaDependiente = null;
                 JOptionPane.showMessageDialog(this, "Tarea agregada con dependencia a: " + t.getTareaPrevia().getDescripcion());
            }
            
            if (e.getSource() == btnPila) pila.push(t);
            else if (e.getSource() == btnCola) cola.add(t);
            else if (e.getSource() == btnLista) lista.insertar(t);

            txtDesc.setText("");
            grupoUrgencia.clearSelection();
            departamentoSeleccionado = "";
            actualizarAreas();
        };

        btnPila.addActionListener(agregar);
        btnCola.addActionListener(agregar);
        btnLista.addActionListener(agregar);
        
        btnDependencia.addActionListener(e -> {
            String descPrevia = JOptionPane.showInputDialog(this, "Ingrese la descripción de la tarea previa:");
            if (descPrevia != null && !descPrevia.trim().isEmpty()) {
                tareaDependiente = restaurante.buscarTareaPorDescripcion(descPrevia);
                if (tareaDependiente != null) {
                    JOptionPane.showMessageDialog(this, "Dependencia establecida con: " + tareaDependiente.getDescripcion());
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontró la tarea previa.");
                }
            }
        });

        ActionListener seleccionarDepto = e -> {
            JButton boton = (JButton) e.getSource();
            if (boton.getText().equals("Otro")) {
                Object[] opciones = {"Crear nuevo empleado", "Asignar de cualquier departamento"};
                int seleccion = JOptionPane.showOptionDialog(this,
                        "¿Desea crear un nuevo empleado para esta tarea o asignar uno existente?",
                        "Departamento 'Otro'", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                        null, opciones, opciones[0]);

                if (seleccion == JOptionPane.YES_OPTION) {
                    String nombreEmpleado = JOptionPane.showInputDialog(this, "Ingrese el nombre del nuevo empleado:");
                    if (nombreEmpleado != null && !nombreEmpleado.trim().isEmpty()) {
                        restaurante.crearEmpleadoTemporal(nombreEmpleado.trim());
                        departamentoSeleccionado = "Otro";
                    }
                } else if (seleccion == JOptionPane.NO_OPTION) {
                    departamentoSeleccionado = "Otro";
                }
            } else {
                departamentoSeleccionado = boton.getText();
            }
        };

        btnCocina.addActionListener(seleccionarDepto);
        btnBarra.addActionListener(seleccionarDepto);
        btnCaja.addActionListener(seleccionarDepto);
        btnOtro.addActionListener(seleccionarDepto);

        btnAtenderPila.addActionListener(e -> {
            Tarea t = pila.pop();
            if (t == null) {
                JOptionPane.showMessageDialog(this, "No hay tareas urgentes pendientes.");
            } else {
                JOptionPane.showMessageDialog(this, "Atendiendo tarea urgente: " + t);
                restaurante.liberarEmpleado(t.getEmpleadoAsignado());
            }
            actualizarAreas();
        });

        btnAtenderCola.addActionListener(e -> {
            Tarea t = cola.poll();
            if (t == null) {
                JOptionPane.showMessageDialog(this, "No hay tareas programadas pendientes.");
            } else if (t.getTareaPrevia() != null) {
                JOptionPane.showMessageDialog(this, "No se puede atender la tarea '" + t.getDescripcion() + "' hasta que se complete su tarea previa.");
                cola.add(t);
            } else {
                JOptionPane.showMessageDialog(this, "Atendiendo tarea programada: " + t);
                restaurante.liberarEmpleado(t.getEmpleadoAsignado());
            }
            actualizarAreas();
        });

        btnEliminarLista.addActionListener(e -> {
            String desc = JOptionPane.showInputDialog(this, "Ingrese la descripción exacta de la tarea a eliminar:");
            if (desc != null) {
                Tarea tareaEliminar = restaurante.buscarTareaPorDescripcion(desc);
                if (tareaEliminar != null) {
                    restaurante.liberarEmpleado(tareaEliminar.getEmpleadoAsignado());
                    if (lista.eliminar(desc)) {
                        JOptionPane.showMessageDialog(this, "Tarea eliminada correctamente.");
                    } else {
                        JOptionPane.showMessageDialog(this, "No se encontró la tarea en la lista.");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontró la tarea en ninguna de las estructuras.");
                }
            }
            actualizarAreas();
        });

        btnBuscarEmpleado.addActionListener(e -> {
            String query = txtEmpleadoBuscar.getText().trim();
            if (query.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe ingresar un ID para buscar.");
                return;
            }

            Empleado empleadoEncontrado = restaurante.buscarEmpleado(query);

            if (empleadoEncontrado != null) {
                areaInfoEmpleado.setText("Empleado encontrado:\n" +
                        "ID: " + empleadoEncontrado.getId() + "\n" +
                        "Nombre: " + empleadoEncontrado.getNombre() + "\n" +
                        "Departamento: " + empleadoEncontrado.getDepartamento() + "\n" +
                        "Estado: " + (empleadoEncontrado.isOcupado() ? "Ocupado" : "Disponible"));
            } else {
                areaInfoEmpleado.setText("Empleado no encontrado.");
            }
        });

        btnVerEmpleados.addActionListener(e -> {
            List<Empleado> todosEmpleados = restaurante.getTodosLosEmpleados();
            StringBuilder infoEmpleados = new StringBuilder();

            infoEmpleados.append(String.format("%-10s %-20s %-15s %s\n", "ID", "Nombre", "Departamento", "Estatus"));
            infoEmpleados.append("-----------------------------------------------------------\n");

            for (Empleado emp : todosEmpleados) {
                String estatus = emp.isOcupado() ? "Ocupado" : "Disponible";
                infoEmpleados.append(String.format("%-10s %-20s %-15s %s\n", emp.getId(), emp.getNombre(), emp.getDepartamento(), estatus));
            }

            JTextArea textArea = new JTextArea(infoEmpleados.toString());
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(500, 300));

            JOptionPane.showMessageDialog(this, scrollPane, "Lista de Empleados", JOptionPane.PLAIN_MESSAGE);
        });

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void actualizarAreas() {
        areaPila.setText("TAREAS URGENTES (*Pila*)\n");
        for (Tarea t : pila.getAll()) areaPila.append(" • " + t + "\n");

        areaCola.setText("TAREAS PROGRAMADAS (*Cola de Prioridad*)\n");
        for (Tarea t : cola) areaCola.append(" • " + t + "\n");

        areaLista.setText("TAREAS POR DEPARTAMENTO (*Lista*)\n");
        for (Tarea t : lista.getAll()) areaLista.append(" • " + t + "\n");
    }
}