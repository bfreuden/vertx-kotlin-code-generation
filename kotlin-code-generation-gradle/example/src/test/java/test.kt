package org.kotlin.test

import org.junit.Assert.assertEquals
import org.junit.Test

class AnnotationTest {
    @Test fun testSimple() {
        assertEquals("Entity", "Entity")
    }
}
