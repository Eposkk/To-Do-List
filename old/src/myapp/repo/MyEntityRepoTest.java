package myapp.repo;


import myapp.data.MyEntity;
import org.junit.Test;

public class MyEntityRepoTest {

    @Test
    public void testThatWeCanReadMyEntityFromDatabase() {
        MyEntity e = new MyEntityRepo().getMyEntity("id");

        assertEquals(e.getName(), "name");
    }

}