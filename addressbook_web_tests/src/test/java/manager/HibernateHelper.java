package manager;

import manager.hbm.ConInGrRecord;
import manager.hbm.ContactRecord;
import manager.hbm.GroupRecord;
import model.ContactData;
import model.GroupData;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.stream.Collectors;

public class HibernateHelper extends HelperBase {
    private SessionFactory sessionFactory;

    public HibernateHelper(ApplicationManager manager) {
        super(manager);

        sessionFactory = new Configuration()
                .addAnnotatedClass(ConInGrRecord.class)
                .addAnnotatedClass(ContactRecord.class)
                .addAnnotatedClass(GroupRecord.class)
                .setProperty(AvailableSettings.URL, "jdbc:mysql://localhost/addressbook?zeroDateTimeBehavior=convertToNull")
                .setProperty(AvailableSettings.USER, "root")
                .setProperty(AvailableSettings.PASS, "")
                .buildSessionFactory();
    }


    static List<GroupData> convertGroupList(List<GroupRecord> records) {
        return records.stream().map(HibernateHelper::convert).collect(Collectors.toList());
    }

    private static GroupData convert(GroupRecord record) {
        return new GroupData("" + record.id, record.name, record.header, record.footer);
    }

    private static GroupRecord convert(GroupData data) {
        var id = data.id();
        if ("".equals(id)) {
            id = "0";
        }
        return new GroupRecord(Integer.parseInt(id), data.name(), data.header(), data.footer());
    }

    public List<GroupData> getGroupList() {
        return convertGroupList(sessionFactory.fromSession(session -> {
            return session.createQuery("from GroupRecord", GroupRecord.class).list();
        }));
    }

    public long getGroupCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*) from GroupRecord", long.class).getSingleResult();
        });
    }

    public void createGroup(GroupData groupData) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convert(groupData));
            session.getTransaction().commit();
        });

    }
    public List<ContactData> getContactInGroup(GroupData group) {
        return sessionFactory.fromSession(session -> {
            return convertContactList(session.get(GroupRecord.class, group.id()).contacts);
        });
    }


    //Contract
    static List<ContactData> convertContactList(List<ContactRecord> records) {
        return records.stream().map(HibernateHelper::convert).collect(Collectors.toList());
    }

    private static ContactData convert(ContactRecord record) {
        //return new AddressData("" + record.id, record.first, record.middle, record.last, record.nick);
        return new ContactData().withId("" + record.id)
                .withFirst(record.first)
                .withMiddle(record.middle)
                .withLast(record.last)
                .withNick(record.nick)
                .withAddress(record.address)
                .withHome(record.home)
                .withMobile(record.mobile)
                .withWork(record.work)
                .withSecondary(record.secondary)
                .withEmail(record.email)
                .withEmail2(record.email2)
                .withEmail3(record.email3);
    }

    private static ContactRecord convert(ContactData data) {
        var id = data.id();
        if ("".equals(id)) {
            id = "0";
        }
        return new ContactRecord(Integer.parseInt(id), data.first(), data.middle(), data.last(), data.nick());
    }

    public List<ContactData> getContactList() {
        return sessionFactory.fromSession(session -> {
            return convertContactList(session.createQuery("from ContactRecord", ContactRecord.class).list());
        });
    }

    public long getContactCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count(*) from ContactRecord", long.class).getSingleResult();
        });
    }
    public long getContactInGroupCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count(cigr.id) from ConInGrRecord cigr join ContactRecord cr on cigr.id = cr.id", long.class).getSingleResult();
        });
    }
    public long getContactNotInGroupCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count(ab.id) from ContactRecord ab left join ConInGrRecord aig on ab.id=aig.id WHERE aig.id is null", long.class).getSingleResult();
        });
    }
    public List<ContactData> getContactNotInGroup() {
        return sessionFactory.fromSession(session -> {
            return convertContactList(session.createQuery("FROM ContactRecord ab left join ConInGrRecord aig on ab.id=aig.id WHERE aig.id is null", ContactRecord.class).list());
        });
    }

    public void createAddress(ContactData addressData) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convert(addressData));
            session.getTransaction().commit();
        });
    }
}
