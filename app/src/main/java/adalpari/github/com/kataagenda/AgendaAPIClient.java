package adalpari.github.com.kataagenda;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import adalpari.github.com.kataagenda.model.Address;
import adalpari.github.com.kataagenda.model.Company;
import adalpari.github.com.kataagenda.model.Contact;

/**
 * Created by Adalberto Plaza on 21/09/2018.
 */
public class AgendaAPIClient {

    public List<Contact> getAll() {
        List<Contact> contacts = new ArrayList<>();

        for (int i=0; i<100; i++) {
            String generatedName = generateName();
            Address address = new Address("12345", "Elm Street");
            Company company = new Company("Bundee", 98765432);
            contacts.add(new Contact(i+"", generatedName, generatedName, address, company));
        }

        return contacts;
    }

    private String generateName() {
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        return new String(array, Charset.forName("UTF-8"));
    }
}
