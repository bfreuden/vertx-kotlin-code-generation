package org.kotlin.test

import org.junit.Assert.assertEquals
import org.junit.Test
import test.generated.simpleClassName

class AnnotationTest {
    @Test fun testSimple() {
        assertEquals("Entity", Entity("hello world").simpleClassName)
    }

    @Test fun testVertx() {
        assertEquals("Entity", Entity("hello world").simpleClassName)
    }
}
