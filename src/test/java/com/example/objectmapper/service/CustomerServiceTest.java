package com.example.objectmapper.service;

import com.example.objectmapper.model.Customer;
import com.example.objectmapper.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.*;
import org.mockito.Mock;


@SpringBootTest
public class CustomerServiceTest {

    @Mock
    private CustomerRepository cr;

    @InjectMocks
    private CustomerService cs;

    @Test
    public void testCreateCustomer() {
        Customer c = new Customer();
        c.setFirstName("Test");
        c.setLastName("Testov");
        c.setEmail("test@test.com");
        c.setContactNumber("88005553535");

        when(cr.save(any(Customer.class))).thenReturn(c);
        Customer created = cs.createCustomer(c);

        assertNotNull(created);
        assertEquals("Test", created.getFirstName());
        assertEquals("Test", created.getLastName());
        assertEquals("test@test.com", created.getEmail());
    }

    @Test
    public void testGetCustomerById() {
        Customer с = new Customer();
        с.setCustomerId(1L);
        с.setFirstName("Test");
        с.setLastName("Test");

        when(cr.findById(1L)).thenReturn(Optional.of(с));
        Optional<Customer> finder = cs.getCustomerById(1L);

        assertTrue(finder.isPresent());
        assertEquals("Test", finder.get().getFirstName());
        assertEquals("test", finder.get().getLastName());
    }

    @Test
    public void testUpdateCustomer() {
        Customer exist = new Customer();
        exist.setCustomerId(1L);
        exist.setFirstName("Test");
        exist.setLastName("Test");

        Customer update = new Customer();
        update.setCustomerId(1L);
        update.setFirstName("Rest");
        update.setLastName("Rest");

        when(cr.findById(1L)).thenReturn(Optional.of(exist));
        when(cr.save(any(Customer.class))).thenReturn(update);
        Customer result = cs.updateCustomer(1L, update);

        assertNotNull(result);
        assertEquals("Jane", result.getFirstName());
        assertEquals("Doe", result.getLastName());
    }

    @Test
    public void testDeleteCustomer() {
        doNothing().when(cr).deleteById(1L);
        cs.deleteCustomer(1L);
    }
}
