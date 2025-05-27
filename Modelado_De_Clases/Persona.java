package Modelado_De_Clases;

import Interfaces.IDevolucion;

import java.util.*;

public abstract class Persona implements IDevolucion {
    private String nombre;
    private int DNI;
    private char sexo;
    HashSet<Ticket>listaTickets;

    /// El atributo de lista tickets se terminara cuando este armada la clase

    public Persona(String nombre, int DNI, char sexo) {
        this.nombre = nombre;
        this.DNI = DNI;
        this.sexo = sexo;
        this.listaTickets= new HashSet<>();
    }

    public Persona()
    {
        this.listaTickets= new HashSet<>();

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDNI() {
        return DNI;
    }

    public void setDNI(int DNI) {
        this.DNI = DNI;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public HashSet<Ticket> getListaTickets() {
        return listaTickets;
    }

    public void setListaTickets(HashSet<Ticket> listaTickets) {
        this.listaTickets = listaTickets;
    }

    /// METODOS DE LISTA TICKETS, EL CAJERO SOLO PUEDE CREAR Y BORRAR, MIENTRAS QUE EL CLIENTE SOLO VERLO, ESTA VALIDACION SE HACE DESPUES


    public void agregarTicket(Ticket ticket) {
        if (ticket == null) {
            throw new IllegalArgumentException("No se puede agregar un ticket nulo.");
        }

        if (listaTickets.contains(ticket)) {
            throw new IllegalStateException("El ticket ya fue agregado previamente.");
        }

        listaTickets.add(ticket);
    }

    public boolean borrarTicket(int codigo_de_barra) {
        if (codigo_de_barra <= 0) {
            throw new IllegalArgumentException("El código de barra debe ser positivo.");
        }

        for (Ticket t : listaTickets) {
            if (t.getCodigo_De_Barras() == codigo_de_barra) {
                listaTickets.remove(t);  // Puede lanzar ConcurrentModificationException en algunos casos
                return true;
            }
        }

        throw new NoSuchElementException("No se encontró ningún ticket con el código: " + codigo_de_barra);
    }

    public void verLista() {
        if (listaTickets.isEmpty()) {
            System.out.println("La lista de tickets está vacía.");
            return;
        }

        System.out.println("=== Tickets del usuario ===");
        for (Ticket t : listaTickets) {
            System.out.println(t);
        }
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return DNI == persona.DNI;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(DNI);
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", DNI=" + DNI +
                ", sexo=" + sexo +
                '}';
    }



}
