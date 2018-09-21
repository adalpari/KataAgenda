package adalpari.github.com.kataagenda;

import static android.support.test.InstrumentationRegistry.getInstrumentation;

import android.content.Context;
import android.content.SharedPreferences;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import adalpari.github.com.kataagenda.model.Address;
import adalpari.github.com.kataagenda.model.Company;
import adalpari.github.com.kataagenda.model.Contact;

/**
 * Created by Adalberto Plaza on 21/09/2018.
 */
public class MainActivityTest {

    private Contact contact;
    private AgendaHelper agendaHelper;
    private SharedPreferences sharedPreferences;

    @Before
    public void setUp() {
        final Address address = new Address("12345", "Elm Street");
        final Company company = new Company("Bundee", 98765432);
        contact = new Contact("1","Jhon", "JJhon", address, company);

        sharedPreferences = getInstrumentation().getTargetContext().getSharedPreferences("KataAgenda", Context.MODE_PRIVATE);
        agendaHelper = new AgendaHelper(sharedPreferences);
    }

    @Test
    public void shouldAddContact() {
        agendaHelper.add(contact);

        Contact retreivedContact = agendaHelper.getById(contact.getId());

        Assert.assertEquals(contact, retreivedContact);
    }

    @After
    public void tearDown() {
        sharedPreferences.edit().clear();
    }
}