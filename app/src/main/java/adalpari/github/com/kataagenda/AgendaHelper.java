package adalpari.github.com.kataagenda;

import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import com.google.gson.Gson;

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
        sharedPreferences.edit().putString(contact.getId(), toJson(contact));
    }

    public @Nullable Contact getById(String id) {
        String serialisedContact = sharedPreferences.getString(id, "");

        if (serialisedContact != null && serialisedContact.length() > 0) {
            return fromJson(serialisedContact);
        } else {
            return null;
        }
    }

    private String toJson(Contact contact) {
        return new Gson().toJson(this);
    }

    private Contact fromJson(String serialisedContact) {
        return new Gson().fromJson(serialisedContact, Contact.class);
    }
}
