package de.grobox.transportr;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class collectionTest {
    private collection col;
    @Before
    public void setup() throws Exception{
        col=new collection();
    }
    @Test
    public void add() {
        int pre=col.mList.size();
        assertEquals(pre+1,col.add("just for a test"));
//        assertEquals(pre+1,col.add(""));
    }

    @Test
    public void del() {
        col.add("123");
        col.add("456");
        int pre=col.mList.size();
        assertEquals(pre-1,col.del(0));
//        assertEquals(pre-1,col.del(-1));
    }
}