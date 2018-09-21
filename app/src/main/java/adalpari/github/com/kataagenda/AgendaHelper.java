package adalpari.github.com.kataagenda;

import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import adalpari.github.com.kataagenda.model.Contact;

/**
 * Created by Adalberto Plaza on 21/09/2018.
 */
public class AgendaHelper {

    private final SharedPreferences sharedPreferences;

    public AgendaHelper(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public void add(Contact contact) {
        String serializedContact = toJson(contact);
        sharedPreferences.edit().putString(contact.getId(), serializedContact).commit();
    }

    public @Nullable Contact getById(String id) {
        String serialisedContact = sharedPreferences.getString(id, "");

        if (serialisedContact != null && serialisedContact.length() > 0) {
            return fromJson(serialisedContact);
        } else {
            return null;
        }
    }

    public List<Contact> getAllContacts() {
        Map<String, ?> allContacts = sharedPreferences.getAll();

        List<Contact> contacts = new ArrayList<>();

        for (Map.Entry<String, ?> entry : allContacts.entrySet()) {
            if (entry.getValue() instanceof String) {
                String serialisedContact = (String) entry.getValue();
                contacts.add(fromJson(serialisedContact));
            }
        }

        return contacts;
    }

    private String toJson(Contact contact) {
        return new Gson().toJson(contact);
    }

    private Contact fromJson(String serialisedContact) {
        return new Gson().fromJson(serialisedContact, Contact.class);
    }
}
