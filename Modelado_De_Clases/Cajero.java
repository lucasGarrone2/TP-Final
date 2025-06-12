package Modelado_De_Clases;
import Enum.Turno;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Cajero extends Persona{
    private String user;
    private String password;
    Turno turno;
    private int nr_empleado;

    public Cajero(String nombre, int DNI, char sexo, String user, String password, Turno turno, int nr_empleado) {
        super(nombre, DNI, sexo);
        this.user = user;
        this.password = password;
        this.turno = turno;
        this.nr_empleado = nr_empleado;


    }
    public Cajero()
    {

    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    /// No va getPassword por seguridad

    public void setPassword(String password) {
        this.password = password;
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    public int getNr_empleado() {
        return nr_empleado;
    }

    public void setNr_empleado(int nr_empleado) {
        this.nr_empleado = nr_empleado;
    }





    @Override
    public String toString() {
        return "Cajero {\n" +
                "  user: '" + user + "',\n" +
                "  turno: " + turno + ",\n" +
                "  nr_empleado: " + nr_empleado + ",\n" +
                "  " + super.toString() + "\n" +
                "}";
    }


    public JSONObject toJSON()
    {
        JSONObject json= new JSONObject();
        try {
            json.put("user",getUser());
            json.put("turno",getTurno());
            json.put("nr_empleado",getNr_empleado());
            /// ACA VA LA LISTA
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

    public static Cajero desdeJSON(JSONObject jsonObject)
    {
        Cajero cajero= new Cajero();
        try {
            cajero.setUser(jsonObject.getString("user"));
            cajero.setTurno(Turno.valueOf(jsonObject.getString("turno")));
            cajero.setNr_empleado(jsonObject.getInt("nr_empleado"));
            cajero.setNombre(jsonObject.getString("nombre"));
            cajero.setDNI(jsonObject.getInt("dni"));
            cajero.setSexo(jsonObject.getString("sexo").charAt(0));

            /// FALTA LA LISTA
            if (jsonObject.has("tickets")) {
                JSONArray jsonTickets = jsonObject.getJSONArray("tickets");
                for (int i = 0; i < jsonTickets.length(); i++) {
                    JSONObject jsonTicket = jsonTickets.getJSONObject(i);
                    Ticket ticket = Ticket.desdeJSON(jsonTicket);  // ← asume que Ticket tiene desdeJSON()
                    cajero.agregarTicket(ticket);
                }
            }

        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        return cajero;
    }

    @Override
    public void gestionarDevolucion(int id_producto) {
        /// Esto es asi?
        System.out.println("Gracias por su devolucion");
    }
}
