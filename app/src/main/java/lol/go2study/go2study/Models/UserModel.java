package lol.go2study.go2study.Models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.codec.DecoderException;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import FontysICT.Models.Person;
import Go2Study.Models.User;

/**
 * Created by Todor on 1/5/2016.
 */
@Table(name = "Users")
public class UserModel extends Model {

    @Column(name = "pcn",index = true)
    private String pcn = null;

    @Column(name = "firstName")
    private String firstName = null;

    @Column(name = "lastName")
    private String lastName = null;

    @Column(name = "displayName")
    private String displayName = null;

    @Column(name = "className")
    private String className = null;

    @Column(name = "email")
    private String email = null;


    @Column(name = "photo")
    private Object photo = null;

    public UserModel() {
        super();
    }

    public UserModel(User u) {
        super();
        this.pcn = u.getPcn();
        this.firstName = u.getFirstName();
        this.lastName = u.getLastName();
        this.displayName = u.getDisplayName();
        this.className = u.getClassName();
        this.email = u.getEmail();
        this.photo = u.getPhoto();

    }

    public static List<User> getAllUsers() throws UnsupportedEncodingException, DecoderException {
        List<UserModel> usersFromDB = new Select()
                .from(UserModel.class)
                .orderBy("firstName ASC")
                .execute();

        //Create List<Person> using the Fontys Person model and return it
        List<User> userList = new ArrayList<User>();
        for (UserModel u: usersFromDB) {
            User user = new User();
            user.setPcn(u.pcn);
            user.setFirstName(u.firstName);
            user.setLastName(u.lastName);
            user.setDisplayName(u.displayName);
            user.setClassName(u.className);
            user.setEmail(u.email);
            user.setPhoto(u.photo);

            //Base64 encode the thumbnailData

            //Add to list of people
            userList.add(user);
        }
        return userList;
    }



}
