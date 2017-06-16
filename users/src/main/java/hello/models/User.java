package hello.models;

import leap.core.doc.annotation.Doc;
import leap.lang.meta.annotation.Filterable;
import leap.lang.meta.annotation.Sortable;
import leap.orm.annotation.Column;
import leap.orm.annotation.Id;
import leap.orm.annotation.Table;
import leap.orm.model.Model;

@Table(name = "users")
public class User extends Model {
    @Id(generator = "shortid")
    @Sortable
    @Filterable
    @Doc("用户id")
    private String id;

    @Column
    @Sortable
    @Filterable
    @Doc("用户姓名")
    private String name;

    @Column
    @Sortable
    @Filterable
    @Doc("群组id")
    private String groupId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}