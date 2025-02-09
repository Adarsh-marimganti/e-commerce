package com.projects.productservice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RandomTest {
    @Test
    void myTestCase() {
        int i = 1 + 1;
        assert i == 2;
        Assertions.assertEquals(2, i);
//        assertTrue(false);
    }
}