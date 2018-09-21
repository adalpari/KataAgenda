package adalpari.github.com.kataagenda;

import static android.support.test.InstrumentationRegistry.getInstrumentation;

import android.content.Context;
import android.content.SharedPreferences;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
public class AgendaHelperTest {

    private Contact contact1;
    private Contact contact2;
    private Address address;
    private Company company;

    private AgendaHelper agendaHelper;
    private SharedPreferences sharedPreferences;

    private AgendaApiClientMock agendaApiClientMock;

    @Before
    public void setUp() {
        address = new Address("12345", "Elm Street");
        company = new Company("Bundee", 98765432);
        contact1 = new Contact("1","Jhon", "JJhon", address, company);
        contact2 = new Contact("2","Mike", "MMike", address, company);

        agendaApiClientMock = new AgendaApiClientMock();

        sharedPreferences = getInstrumentation().getTargetContext().getSharedPreferences("KataAgenda", Context.MODE_PRIVATE);
        agendaHelper = new AgendaHelper(sharedPreferences, agendaApiClientMock);
    }

    @Test
    public void shouldAddAndGetContact() {
        agendaHelper.add(contact1);

        Contact retrievedContact = agendaHelper.getById(contact1.getId());

        Assert.assertEquals(contact1, retrievedContact);
    }

    @Test
    public void shouldReturnNullForNonExistentContact() {
        agendaHelper.add(contact1);

        Contact retrievedContact = agendaHelper.getById("-9");

        Assert.assertNull(retrievedContact);
    }

    @Test
    public void shouldRetrieveAllContacts() {
        agendaHelper.add(contact1);
        agendaHelper.add(contact2);

        List<Contact> contacts = agendaHelper.getAllContacts();

        Assert.assertEquals(2, contacts.size());
    }

    @Test
    public void shouldRetrieveEmptyContactsWhenThereAreNot() {
        agendaHelper.deleteAllContacts();

        List<Contact> contacts = agendaHelper.getAllContacts();

        Assert.assertEquals(0, contacts.size());
    }

    @Test
    public void shouldRemoveAllContacts() {
        agendaHelper.add(contact1);
        agendaHelper.add(contact2);

        agendaHelper.deleteAllContacts();
        List<Contact> contacts = agendaHelper.getAllContacts();

        Assert.assertEquals(0, contacts.size());
    }

    @Test
    public void shouldReturnNumberOfContactsContaining() {
        String matchWith = "j";
        int contactsMatching = 0;

        for (int i=0; i<100; i++) {
            String generatedName = generateName();
            Contact contact = new Contact(i+"", generatedName, generatedName, address, company);
            agendaHelper.add(contact);

            if (generatedName.contains(matchWith)) {
                contactsMatching++;
            }
        }

        int actualMatchedContacts = agendaHelper.getNumberOfContactsContaining(matchWith);

        Assert.assertEquals(contactsMatching, actualMatchedContacts);
    }

    @Test
    public void syncShouldAddAllContacts() {
        agendaHelper.syncContacts();

        List<Contact> contacts = agendaHelper.getAllContacts();

        Assert.assertEquals(2, contacts.size());
    }

    private String generateName() {
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        return new String(array, Charset.forName("UTF-8"));
    }

    @After
    public void tearDown() {
        sharedPreferences.edit().clear().commit();
    }

    class AgendaApiClientMock extends AgendaAPIClient {
        @Override
        public List<Contact> getAll() {
            List<Contact> contacts = new ArrayList<>();
            contacts.add(contact1);
            contacts.add(contact2);

            return contacts;
        }
    }
}