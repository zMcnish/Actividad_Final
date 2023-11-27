public class Contacto {
    private String nombre;
    private String numeroCedula;

    public Contacto(String nombre, String numeroCedula) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        if (!esNumeroCedulaValido(numeroCedula)) {
            throw new IllegalArgumentException("Número de cédula inválido.");
        }
        this.nombre = nombre;
        this.numeroCedula = numeroCedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNumeroCedula() {
        return numeroCedula;
    }

    public void setNumeroCedula(String numeroCedula) {
        if (!esNumeroCedulaValido(numeroCedula)) {
            throw new IllegalArgumentException("Número de cédula inválido.");
        }
        this.numeroCedula = numeroCedula;
    }

    private boolean esNumeroCedulaValido(String numeroCedula) {
        return numeroCedula != null && numeroCedula.matches("[0-9]+") && (numeroCedula.length() == 9 || numeroCedula.length() == 10);
    }

    @Override
    public String toString() {
        return "Contacto{Nombre: '" + nombre + "', Número de Cédula: '" + numeroCedula + "'}";
    }
}
