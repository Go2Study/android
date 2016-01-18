package lol.go2study.go2study.Models;

import android.graphics.Bitmap;

import java.util.List;

import FontysICT.Models.Person;

/**
 * Created by Todor on 1/15/2016.
 */
public class PersonImage {

    private Person person;
    private Bitmap image;
    private String displayName;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public PersonImage(Person person,Bitmap image, String displayName) {

        this.person = person;
        this.image = image;
        this.displayName = displayName;

    }
}
