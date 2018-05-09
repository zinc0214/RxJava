package ayhan.com.rxjavapractice.observable;

/**
 * Created by han-ayeon on 2018. 4. 2..
 */

public class Order {

    private String id;

    public Order(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Order Id : " + id;
    }
}
