package adalpari.github.com.kataagenda;

import static android.support.test.InstrumentationRegistry.getInstrumentation;

import android.content.Context;
import android.content.SharedPreferences;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import adalpari.github.com.kataagenda.model.Address;
import adalpari.github.com.kataagenda.model.Company;
import adalpari.github.com.kataagenda.model.Contact;

/**
 * Created by Adalberto Plaza on 21/09/2018.
 */
public class MainActivityTest {

    private Contact contact1;
    private Contact contact2;
    private AgendaHelper agendaHelper;
    private SharedPreferences sharedPreferences;

    @Before
    public void setUp() {
        final Address address = new Address("12345", "Elm Street");
        final Company company = new Company("Bundee", 98765432);
        contact1 = new Contact("1","Jhon", "JJhon", address, company);
        contact2 = new Contact("2","Jhon", "JJhon", address, company);

        sharedPreferences = getInstrumentation().getTargetContext().getSharedPreferences("KataAgenda", Context.MODE_PRIVATE);
        agendaHelper = new AgendaHelper(sharedPreferences);
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

        Contact retrievedContact = agendaHelper.getById(contact2.getId());

        Assert.assertEquals(null, retrievedContact);
    }

    @Test
    public void shouldRetrieveAllContacts() {
        agendaHelper.add(contact1);
        agendaHelper.add(contact2);

        List<Contact> contacts = agendaHelper.getAllContacts();

        Assert.assertEquals(2, contacts.size());
    }

    @After
    public void tearDown() {
        sharedPreferences.edit().clear();
    }
}