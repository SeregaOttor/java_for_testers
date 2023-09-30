package test;

import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupRemovalTests extends TestBase{
    @Test
    public void canRemoveGroup() {
        if (!app.groups().isGroupPresent()){
            app.groups().createGroup(new GroupData("groups name", "group header", "group footer"));
        }
        app.groups().removeGroup();
    }

}
