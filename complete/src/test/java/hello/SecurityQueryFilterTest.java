package hello;

import leap.core.annotation.Inject;
import leap.core.junit.AppTestBase;
import leap.lang.New;
import leap.orm.dao.Dao;
import org.junit.Test;

public class SecurityQueryFilterTest extends AppTestBase {

    protected @Inject Dao dao;

    @Test
    public void testSimpleQueryFilter() {
        //with query filter
        dao.createSqlQuery("select * from user u where name = :name", New.hashMap("name", "not_exists")).list();

        //without query filter
        dao.createSqlQuery("select * from user u where name = :name", New.hashMap("name", "not_exists"))
            .withoutQueryFilter()
            .list();
    }

}
