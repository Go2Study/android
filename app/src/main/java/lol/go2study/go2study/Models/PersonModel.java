package lol.go2study.go2study.Models;

import android.graphics.Bitmap;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import FontysICT.Models.Person;

/**
 * Created by Todor on 1/4/2016.
 */
@Table(name = "People")
public class PersonModel extends Model {
    // If name is omitted, then the field name is used.
    @Column(name = "firstName")
    public String firstName;

    @Column(name = "lastName")
    public String lastName;

    @Column(name = "pcn" ,unique = true)
    public String pcn;

    @Column(name = "office")
    public String office;

    @Column(name = "email")
    public String email;


    @Column(name = "department")
    public String department;

    @Column(name = "mobileNumber")
    public String mobileNumber;

    @Column(name = "photo")
    public String photo;

    @Column(name = "thumbnailDataa")
    public String thumbnailDataa;

    public PersonModel() {
        super();
    }


    public PersonModel(Person p) {
        super();
        this.firstName = p.getGivenName();
        this.lastName = p.getSurName();
        this.pcn = p.getId();
        this.email = p.getMail();
        this.office = p.getOffice();
        this.department = p.getDepartment();
        this.photo = p.getPhoto();
        this.mobileNumber = p.getMobileNumber();
        this.thumbnailDataa = p.getThumbnailData();
        //this.telephoneNumber = p.getTelephoneNumber();
    }

    public static List<Person> getAllPeople() throws UnsupportedEncodingException, DecoderException {
        List<PersonModel> peopleFromDB = new Select()
                .from(PersonModel.class)
                .orderBy("firstName ASC")
                .execute();

        //Create List<Person> using the Fontys Person model and return it
        List<Person> peopleList = new ArrayList<Person>();
        for (PersonModel p: peopleFromDB) {
            Person person = new Person();
            person.setGivenName(p.firstName);
            person.setSurName(p.lastName);
            // person.setTelephoneNumber(p.telephoneNumber);
            person.setMail(p.email);
            person.setOffice(p.office);
            person.setDepartment(p.department);
            person.setId(p.pcn);
            person.setPhoto(p.photo);
            person.setThumbnailData(p.thumbnailDataa);

            //Add to list of people
            peopleList.add(person);
        }
        return peopleList;
    }

    public static void deleteAll()
    {
        new Delete().from(PersonModel.class).execute();
    }
}
