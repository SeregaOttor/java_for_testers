package manager.hbm;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "address_in_groups")
public class ConInGrRecord {

    @Id
    public int id;
    @Id
    public int group_id;


    public ConInGrRecord() {

    }
    public ConInGrRecord(int id, int group_id) {
        this.id = id;
        this.group_id = group_id;
    }
}

