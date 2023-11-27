import java.util.*;
import java.io.*;

public class GestorContactos {
    private List<Contacto> contactos;
    private final String ARCHIVO = "contactos.txt";

    public GestorContactos() {
        contactos = new ArrayList<>();
        cargarContactos();
    }

    public void agregarContacto(String nombre, String numeroCedula) throws IOException {
        Contacto nuevoContacto = new Contacto(nombre, numeroCedula);
        contactos.add(nuevoContacto);
        guardarContactos();
    }

    public Contacto buscarContacto(String nombre) {
        return contactos.stream()
                        .filter(c -> c.getNombre().equalsIgnoreCase(nombre))
                        .findFirst()
                        .orElse(null);
    }

    public boolean actualizarContacto(String nombre, String nuevoNumeroCedula) throws IOException {
        Contacto contacto = buscarContacto(nombre);
        if (contacto != null) {
            contacto.setNumeroCedula(nuevoNumeroCedula);
            guardarContactos();
            return true;
        }
        return false;
    }

    public boolean eliminarContacto(String nombre) throws IOException {
        Contacto contacto = buscarContacto(nombre);
        if (contacto != null) {
            contactos.remove(contacto);
            guardarContactos();
            return true;
        }
        return false;
    }

    private void cargarContactos() {
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                contactos.add(new Contacto(datos[0], datos[1]));
            }
        } catch (IOException e) {
            System.err.println("Error al cargar contactos: " + e.getMessage());
        }
    }

    private void guardarContactos() throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO))) {
            for (Contacto c : contactos) {
                bw.write(c.getNombre() + "," + c.getNumeroCedula());
                bw.newLine();
            }
        }
    }
}
