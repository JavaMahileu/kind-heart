package com.epam.edu.kh.tests;

import java.io.IOException;

import org.junit.Test;
import org.springframework.security.acls.model.NotFoundException;

import com.epam.edu.kh.business.entity.Record;
import com.epam.edu.kh.business.scanner.JsonScannerOfResponseVK;

import static org.junit.Assert.*;

public class Tests {

    private JsonScannerOfResponseVK json = new JsonScannerOfResponseVK();

    @Test
    public final void test1() throws NullPointerException, IOException {

        Record rec1 = new Record();
        rec1.setUserName("�����");
        Record rec2 = json
                .parseJsonOfResponse("http://vk.com/id265302295?w=wall265302295_72");
        assertEquals(rec1.getUserName(), rec2.getUserName());
    }

    @Test
    public final void test2() throws NullPointerException, IOException {

        Record rec1 = json
                .parseJsonOfResponse("http://vk.com/wall-24502885_168300");
        Record rec2 = json
                .parseJsonOfResponse("http://vk.com/wall-24502885_168295");
        assertEquals(rec1.getUserName(), rec2.getUserName());
    }

    @Test
    public final void test3() throws NullPointerException, IOException {

        assertEquals("-24502885_168300",
                json.parseLink("http://vk.com/wall-24502885_168300"));
    }

    @Test(expected = NotFoundException.class)
    public final void test4() throws NullPointerException, IOException {

        assertFalse("-24502885_168300".equals(json
                .parseLink("http://vk.com/wallwqerqweqwe_168300")));
    }

    @Test(expected = NotFoundException.class)
    public final void test5() throws NullPointerException, IOException {

        assertEquals("-24502885_168300",
                json.parseLink("http://vk.com/wa-24502885_168300"));
    }

    @Test(expected = NotFoundException.class)
    public final void test6() throws NullPointerException, IOException {

        Record rec1 = new Record();
        Record rec2 = new Record();

        rec1.setSourceUrl("http://vk.com/wa-24502885_168300");
        rec2.setSourceUrl("http://vk.com/wa-24502885_168300");
        assertTrue(rec1.equals(rec2));
    }

    @Test
    public final void test7() throws NullPointerException, IOException {

        Record rec1 = json
                .parseJsonOfResponse("http://vk.com/wall-24502885_168295");
        Record rec2 = json
                .parseJsonOfResponse("http://vk.com/id265302295?w=wall265302295_72");
        assertFalse(rec1.getMessage().equals(rec2.getMessage()));
    }
}