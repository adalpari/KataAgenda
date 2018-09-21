package adalpari.github.com.kataagenda.model;

/**
 * Created by Adalberto Plaza on 21/09/2018.
 */
public class Address {

    private String postalCode;
    private String streetName;

    public Address(String postalCode, String streetName) {
        this.postalCode = postalCode;
        this.streetName = streetName;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }
}
