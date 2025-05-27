package Modelado_De_Clases;

import Json.JsonUtiles;
import netscape.javascript.JSException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;

public class Cliente extends Persona{
    private String email;
    private String direccion;
    private String Metodo_De_Pago;

    public Cliente(String nombre, int DNI, char sexo, String email, String direccion, String metodo_De_Pago) {
        super(nombre, DNI, sexo);
        this.email = email;
        this.direccion = direccion;
        Metodo_De_Pago = metodo_De_Pago;

    }

    public Cliente() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getMetodo_De_Pago() {
        return Metodo_De_Pago;
    }

    public void setMetodo_De_Pago(String metodo_De_Pago) {
        Metodo_De_Pago = metodo_De_Pago;
    }

    @Override
    public String toString() {
        return "Cliente {\n" +
                "  email: '" + email + "',\n" +
                "  direccion: '" + direccion + "',\n" +
                "  metodoDePago: '" + Metodo_De_Pago + "',\n" +
                "  " + super.toString() + "\n" +
                "}";
    }

    public JSONObject toJSON()
    {
        JSONObject json = new JSONObject();
        try {
            json.put("nombre",getNombre());
            json.put("dni",getDNI());
            json.put("sexo",getSexo());
            json.put("email", getEmail());
            json.put("direccion",getDireccion());
            json.put("metodo de pago",getMetodo_De_Pago());

            /// FALTA PARA PODER PASAR LA LISTA DE TICKETS AL JSON

            JSONArray jsonArray= new JSONArray();
            for(Ticket t : listaTickets)
            {
                jsonArray.put(t.toJSON());
            }
            json.put("ticket",jsonArray);

        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        return json;
    }

    public static Cliente desdeJSON(JSONObject jsonObject)
    {
        Cliente cliente = new Cliente();

        try {
        cliente.setNombre(jsonObject.getString("nombre"));
        cliente.setDNI(jsonObject.getInt("dni"));
        cliente.setSexo(jsonObject.getString("sexo").charAt(0));
        cliente.setEmail(jsonObject.getString("email"));
        cliente.setDireccion(jsonObject.getString("direccion"));
        cliente.setMetodo_De_Pago(jsonObject.getString("metodo de pago"));

        /// FALTA PASAR LA LISTA DE TICKETS
            if (jsonObject.has("tickets")) {
                JSONArray jsonTickets = jsonObject.getJSONArray("tickets");
                for (int i = 0; i < jsonTickets.length(); i++) {
                    JSONObject jsonTicket = jsonTickets.getJSONObject(i);
                    Ticket ticket = Ticket.desdeJSON(jsonTicket);  // ← asume que Ticket tiene desdeJSON()
                    cliente.agregarTicket(ticket);
                }
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    return cliente;
    }

    @Override
    public void gestionarDevolucion(int id_producto) {
        /// Esto seria asi?
        System.out.println("Buenas tardes, vengo a devolver el producto" + id_producto);
    }
}
