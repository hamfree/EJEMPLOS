package examples.stateless;

import javax.ejb.Stateless;

@Stateless
public class HelloServiceBean implements HelloService {

    @Override
    public String sayHello(String name) {
        return "Hola, " + name;
    }
}
