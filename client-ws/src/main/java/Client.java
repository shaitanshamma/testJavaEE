import ru.shamma.service.ProductService;
import ru.shamma.service.ProductServiceWs;

import java.net.MalformedURLException;
import java.net.URL;

public class Client {
    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://localhost:8080/first-jsf-app/ProductService/ProductServiceImpl?wsdl");
        ProductService productService = new ProductService(url);

        ProductServiceWs port = productService.getProductServiceImplPort();

        port.findAll().forEach(productDao -> System.out.println(productDao.getName() +"-" + productDao.getDescription()));

        System.out.println(port.findByIdWs(2L).getName());

        System.out.println(port.findByName("Soap").getName());
    }
}
