import jakarta.inject.Inject;
import lombok.SneakyThrows;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Servlet", value = "/contacts")
class Servlet extends HttpServlet {
@Inject
private Service service;
    @SneakyThrows
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
      service.getContactById(1);
      service.getContacts();
      service.save(new Contact(4,"Robin","Marte","87765"));
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String requestURI = request.getRequestURI();
        System.out.println("Servlet");

        StringBuffer requestURL = request.getRequestURL();
        System.out.println(requestURI);
        System.out.println(requestURL);
        System.out.println("Servlet");
        PrintWriter writer = response.getWriter();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("javaee.contact");
        EntityManager entityManager = emf.createEntityManager();
        Contact entity = new Contact(12,"Lisa","Gora","283933");
        entity.setFirstName("ffff1");
        entity.setLastName("lllll1");
        entity.setPhone("4234221");
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        writer.flush();
        writer.close();
    }

}
