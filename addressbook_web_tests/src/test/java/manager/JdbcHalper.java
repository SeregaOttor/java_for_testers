package manager;

import model.ContactData;
import model.GroupData;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcHalper extends HelperBase{
    public JdbcHalper(ApplicationManager manager){
        super(manager);
    }

    public List<GroupData> getGroupList() {
        var groups = new ArrayList<GroupData>();
        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook","root", "");
             var statement = conn.createStatement();
            var result = statement.executeQuery("select group_id,group_name,group_header,group_footer from group_list"))
        {
            while (result.next()) {
                groups.add(new GroupData()
                        .withId(result.getString("group_id"))
                        .withName(result.getString("group_name"))
                        .withHeader(result.getString("group_header"))
                        .withFooter(result.getString("group_footer"))
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return groups;
    }


    public List<ContactData> getContactList() {
        var address = new ArrayList<ContactData>();
        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook","root", "");
             var statement = conn.createStatement();
             var result = statement.executeQuery("select id,firstname,middlename,lastname,nickname from addressbook"))
        {
            while (result.next()) {
                address.add(new ContactData()
                        .withId(result.getString("id"))
                        .withFirst(result.getString("firstname"))
                        .withMiddle(result.getString("middlename"))
                        .withLast(result.getString("lastname"))
                        .withNick(result.getString("nickname"))
                        //.withPhoto(result.getString("src/test/resources/images/bzz.jpg"))
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return address;
    }

    public void cheakConsistency() {
        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook","root", "");
             var statement = conn.createStatement();
             var result = statement.executeQuery("select * from address_in_groups ag left join addressbook ad on ag.id = ad.id where ad.id is null"))
        {
            if (result.next()) {
                throw new IllegalStateException("DB is corrupted");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
