import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import sun.plugin2.message.Message;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.ws.Response;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class Service {

    @PersistenceContext
    EntityManager entityManager;

    Statement statement = null;
    Connection connection = null;

    public Service(Statement statement, Connection connection) {
        this.statement = statement;
        this.connection = connection;
    }

    public void save(Contact contact) {
        String sql = "INSERT INTO Users VALUES ('" + contact.getId() + "','" + contact.getFirstName() + "','"
                + contact.getLastName() + "','" + contact.getPhone() + "')";
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Contact added to person table");
    }

    public List<Contact> getContacts() throws SQLException {
        int z = 0;
        List<Contact> list = new ArrayList<>();
        ResultSet rs = statement.executeQuery("select count(*) FROM Users");
        rs.next();
        z = rs.getInt("count(*)");

        for (int i = 1; i < z; i++) {
            String sql = "select id,first_name,second_name,phone_number FROM Users where id= " + i;
            rs = statement.executeQuery(sql);
            Contact contact = new Contact(1, "Lana", "Forex", "363525");
            while (rs.next()) {
                contact.setId(rs.getInt("id"));
                contact.setFirstName(rs.getString("first_name"));
                contact.setLastName(rs.getString("second_name"));
                contact.setPhone(rs.getString("phone_number"));
            }
            list.add(contact);
            System.out.println("List is added");


        }

        return list;
    }

    public Contact getContactById(int id) throws SQLException {
        int k = 0;
        ResultSet rs = statement.executeQuery("select count(*) FROM Users");
        rs.next();
        k = rs.getInt("count(*)");
        String sql = "select id FROM Users where id= " + k;
        rs = statement.executeQuery(sql);
        Contact contact = new Contact(1, "Luisa", "Polski", "75438765");
        while (rs.next()) {
            contact.setId(rs.getInt("id"));
        }
        return contact;
    }

    public void delete(int id) {
        String str3 = "delete from Users where id=" + id;
        try {
            statement.executeUpdate(str3);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("Contact deleted to person table");

//where id
    }

//
//        @POST
//        @Path("/contacts")
//        public Contact receiveHello(@FormParam("name") String name, @FormParam("message") String message) {
//            //process parameters
//            return Contact.(200).build();
//        }
//
//        @POST
//        @Path("/saveObject")
//        @Consumes("application/json")
//        public Response saveMessage(Message message) {
//            Contact  contact =new Contact();
//            Contact  contact1 = ClientBuilder.newClient();
//            WebTarget target = contact1.target("http://www.example.com/api/customers");
//            String response = target.request(MediaType.APPLICATION_JSON)
//                    .accept(MediaType.TEXT_PLAIN_TYPE)
//                    .post(Entity.json(newCustomer), String.class);
//            return Response.status(200).entity("OK").build();
//        }
//    }
//        public void doBusinessLogic; () {
//
//
//        }

}


