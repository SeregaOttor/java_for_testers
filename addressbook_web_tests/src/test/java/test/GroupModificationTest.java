package test;

import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupModificationTest extends TestBase{
    @Test
    void canModifyGroup() {
        if (app.groups().getCount() == 0){
            app.groups().createGroup(new GroupData("groups name", "group header", "group footer"));
        }
        app.groups().modifyGroup(new GroupData().withName("modified name"));
    }
}