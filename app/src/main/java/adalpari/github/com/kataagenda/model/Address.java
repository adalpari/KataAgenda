package adalpari.github.com.kataagenda.model;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(postalCode, address.postalCode) &&
                Objects.equals(streetName, address.streetName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(postalCode, streetName);
    }
}
