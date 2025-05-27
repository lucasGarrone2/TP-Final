package View;

import Json.JsonUtiles;
import Modelado_De_Clases.Cajero;
import Enum.Turno;
import Modelado_De_Clases.Cliente;
import Modelado_De_Clases.Ticket;
import org.json.JSONArray;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MenuDePrueba {
static JSONArray jsonArray = new JSONArray();
    public static void prueba()
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate date = LocalDate.parse("20/4/2004", formatter);
        LocalDateTime fecha = date.atStartOfDay();


        Cajero cajero = new Cajero("Lucas",31313, 'H', "luquitas123","123", Turno.MAÑANA, 39994);
        cajero.toJSON();


        jsonArray.put(cajero.toJSON());
        JsonUtiles.grabarUnJson(jsonArray, "cajero.json");


        Cliente cliente = new Cliente("Juan", 3223, 'M', "lucas@gmail", "rivadavia", "Efectivo");
        jsonArray.put(cliente.toJSON());
        JsonUtiles.grabarUnJson(jsonArray, "cliente.json");


        Ticket ticket = new Ticket(242424, cliente, cajero, fecha, 4000.0);
        jsonArray.put(ticket.toJSON());
        JsonUtiles.grabarUnJson(jsonArray, "ticket.json");

        cliente.verLista();

        cliente.verLista();










    }





}
