package lol.go2study.go2study.Models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

import org.apache.commons.codec.DecoderException;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import Go2Study.Models.Group;

/**
 * Created by Todor on 1/5/2016.
 */

@Table(name = "Group")
public class GroupModel extends Model {

    @Column(name = "id",unique = true,onUniqueConflict = Column.ConflictAction.IGNORE, index =  true)
    private String id = null;

    @Column(name = "name")
    private String name = null;

    @Column(name = "pcnlist")
    private List<String> pcnlist = null;

    public GroupModel() {super();}


    public GroupModel(Group group) {
        super();
        this.id = group.getId();
        this.name = group.getName();
        this.pcnlist = group.getPcnlist();


    }

    public static List<Group> getAllGroups() throws UnsupportedEncodingException, DecoderException {
        List<GroupModel> groupFromDB = new Select()
                .from(GroupModel.class)
               // .orderBy("id ASC")
                .execute();

        //Create List<Person> using the Fontys Person model and return it
        List<Group> groupList = new ArrayList<Group>();

        for (GroupModel groupsModel: groupFromDB) {
            Group group = new Group();
            group.setId(groupsModel.id);
            group.setName(groupsModel.name);
            group.setPcnlist(groupsModel.pcnlist);

            //Add to list of people
            groupList.add(group);
        }
        return groupList;
    }


    public static void deleteAll() {
        new Delete().from(GroupModel.class).execute();
    }
}
