package com.oopsmails.springboot.unittest5.domain;

import com.oopsmails.GetterSetterTestUtils;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PersonPojoTest {
    @Test
    void testGettersAndSetters() throws Exception {
        GetterSetterTestUtils.test(PersonPojo.class);
    }

    @Test
    void testEqualsAndHashCode() {
        EqualsVerifier.forClass(PersonPojo.class)
                .suppress(Warning.NONFINAL_FIELDS, Warning.STRICT_INHERITANCE)
                .verify();
    }

    @Test
    void testToString() {
        PersonPojo person = new PersonPojo();
        person.setName("Alice");
        person.setAge(30);

        String toString = person.toString();
        assertThat(toString).contains("Alice", "30");
    }
}