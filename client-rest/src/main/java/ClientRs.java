import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.glassfish.jersey.client.ClientConfig;
import ru.shamma.dao.ProductDao;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class ClientRs {
    public static void main(String[] args) {
        Client client = ClientBuilder.newClient(new ClientConfig().register(JacksonJsonProvider.class));

        WebTarget webTarget = client.target("http://127.0.0.1:8080/first-jsf-app/api/products");

        Invocation.Builder builder = webTarget.request(MediaType.APPLICATION_JSON_TYPE);

        Response response = builder.get();

        List<ProductDao> productDaoList = response.readEntity(new GenericType<List<ProductDao>>() {
        });

        productDaoList.forEach(productDao -> System.out.println(productDao.getName() + " " + productDao.getPrice()));
    }
}
