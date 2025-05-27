package Modelado_De_Clases;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.Objects;

public class Ticket {
    private int codigo_De_Barras;  ///No puede ser random por que si no no se puede hacer para buscarlo (ver igualmente alguna otra forma)
    private Cliente cliente;
    private Cajero cajero;
    private LocalDateTime fecha;

    private Double precio;
    /// ACA ABAJO LAS CLASES QUE FALTAN PARA QUE ESTE COMPLETA LA CLASE TICKET
    //Supermercado supermercado;
    //List<Producto> listaProducto;


    public Ticket(int codigo_De_Barras, Cliente cliente, Cajero cajero, LocalDateTime fecha, Double precio) {
        this.codigo_De_Barras = codigo_De_Barras;
        this.cliente = cliente;
        this.cajero = cajero;
        this.fecha = fecha;
        this.precio = precio;
        //this.supermercado= supermercado;
        //this.listaProducto= new ArrayList();
    }

    public Ticket() {

    }

    /*  public Ticket()
      {
          //this.listaProducto= new ArrayList();
      }
  */
    public int getCodigo_De_Barras() {
        return codigo_De_Barras;
    }

    public void setCodigo_De_Barras(int codigo_De_Barras) {
        this.codigo_De_Barras = codigo_De_Barras;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cajero getCajero() {
        return cajero;
    }

    public void setCajero(Cajero cajero) {
        this.cajero = cajero;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }


    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    //getters y setters de super y productos

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(codigo_De_Barras, ticket.codigo_De_Barras);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(codigo_De_Barras);
    }
    @Override
    public String toString() {
        return "Ticket {\n" +
                "  codigoDeBarras: " + codigo_De_Barras + ",\n" +
                "  cliente: " + cliente + ",\n" +
                "  cajero: " + cajero + ",\n" +
                "  fecha: " + fecha + ",\n" +
                "  precio: " + precio + "\n" +
                "}";
        //faltan los datos de super y productos
    }

    public JSONObject toJSON()
    {
        JSONObject json= new JSONObject();
        try {
            json.put("codigoDeBarras",getCodigo_De_Barras());
            json.put("cliente", getCliente());
            json.put("cajero",getCajero());
            json.put("fecha",getFecha());
            json.put("precio",getPrecio());
            //faltan los datos de super y productos
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        return json;
    }

    public static Ticket desdeJSON(JSONObject jsonObject)
    {
        Ticket nuevoTicket= new Ticket();
        try {
            nuevoTicket.setCodigo_De_Barras(jsonObject.getInt("codigoDeBarras"));
            nuevoTicket.setCliente(Cliente.desdeJSON(jsonObject.getJSONObject("cliente")));
            nuevoTicket.setCajero(Cajero.desdeJSON(jsonObject.getJSONObject("cajero")));
            nuevoTicket.setFecha(LocalDateTime.parse(jsonObject.getString("fecha")));
            nuevoTicket.setPrecio(jsonObject.getDouble("precio"));
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

       return nuevoTicket;
    }
}
