package com.ncr.geocode.cache;

import com.ncr.geocode.models.Address;
import org.junit.Test;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class LinkedHashMapCacheTest {

    LinkedHashMapCache<String, Address> linkedHashMapCache = new LinkedHashMapCache(2);

    @Test
    public void testGetWhenContainsKey() {
        String key = "foo";
        Address expectedAddress = new Address("bar", OffsetDateTime.now(), 42f, 9001f);

        linkedHashMapCache.put(key, expectedAddress);
        Address actualAddress = linkedHashMapCache.get(key);

        assertEquals(expectedAddress, actualAddress);
    }

   @Test(expected = NoSuchElementException.class)
    public void testGetWhenDoesNotContainKey() {
        linkedHashMapCache.get("foo");
    }

    @Test
    public void testList() {
        Address expectedAddress1 = new Address(UUID.randomUUID().toString(), OffsetDateTime.now(), 42f, 9001f);
        Address expectedAddress2 = new Address(UUID.randomUUID().toString(), OffsetDateTime.now(), 42f, 9001f);
        Address expectedAddress3 = new Address(UUID.randomUUID().toString(), OffsetDateTime.now(), 42f, 9001f);

        linkedHashMapCache.put("foo", expectedAddress1);
        linkedHashMapCache.put("bar", expectedAddress2);
        linkedHashMapCache.put("baz", expectedAddress3);

        List<Address> addresses = linkedHashMapCache.list();

        assertEquals(expectedAddress2, addresses.get(0));
        assertEquals(expectedAddress3, addresses.get(1));
    }
}
