package de.grobox.transportr;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class historyTest {

    private history his;
    @Before
    public void setup() throws Exception{
        his=new history();
    }
    @Test
    public void addTest() throws Exception{
        int pre=his.mList.size();
//        assertEquals(pre+1,his.add("just for a test"));
        assertEquals(pre+1,his.add(""));
    }
    @Test
    public void delTest() throws Exception{
        his.add("123");
        his.add("456");
        int pre=his.mList.size();
//        assertEquals(pre-1,his.del(0));
        assertEquals(pre-1,his.del(-1));
    }
    @Test
    public void bookmarkTest() throws Exception{
        his.add("asdf");
        his.add("qwert");
        int pre=his.bList.size();
//        assertEquals(pre+1,his.bookmark(0));
        assertEquals(pre+1,his.bookmark(-1));
    }
}