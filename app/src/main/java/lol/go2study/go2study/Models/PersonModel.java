package lol.go2study.go2study.Models;

import android.graphics.Bitmap;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

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

    @Column(name = "pcn")
    public String pcn;

    @Column(name = "office")
    public String office;

    @Column(name = "email")
    public String email;

    @Column(name = "department")
    public String department;

    @Column(name = "telephoneNumber")
    public String telephoneNumber;

    @Column(name = "mobileNumber")
    public String mobileNumber;

    @Column(name = "photo")
    public String photo;

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
        this.telephoneNumber = p.getTelephoneNumber();
    }

    public static List<Person> getAll() {
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
            person.setTelephoneNumber(p.telephoneNumber);
            person.setMail(p.email);
            person.setOffice(p.office);
            person.setDepartment(p.department);
            person.setId(p.pcn);
            person.setPhoto(p.photo);

            //Add to list of people
            peopleList.add(person);
        }
        return peopleList;
    }
}
