package manager.hbm;


import jakarta.persistence.Column;
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

}
