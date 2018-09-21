package adalpari.github.com.kataagenda.model;

import java.util.Objects;

/**
 * Created by Adalberto Plaza on 21/09/2018.
 */
public class Company {

    private String name;
    private int phone;

    public Company(String name, int phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return phone == company.phone &&
                Objects.equals(name, company.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, phone);
    }
}
