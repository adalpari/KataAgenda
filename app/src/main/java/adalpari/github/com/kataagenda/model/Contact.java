package adalpari.github.com.kataagenda.model;

/**
 * Created by Adalberto Plaza on 21/09/2018.
 */
public class Contact {

    private String id;
    private String name;
    private String userName;
    private Address address;
    private Company company;

    public Contact(String id, String name, String userName, Address address, Company company) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.address = address;
        this.company = company;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
