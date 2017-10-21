package examples.stateless;

import javax.ejb.Local;

@Local
public interface HelloService {

    public String sayHello(String name);
    
}
