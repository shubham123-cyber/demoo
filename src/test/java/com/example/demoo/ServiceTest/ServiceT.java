package com.example.demoo.ServiceTest;

import com.example.demoo.DemoService.DemoServ;
import com.example.demoo.Model.Entitymodel;
import com.example.demoo.Repo.Sql;
import jakarta.persistence.Entity;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ServiceT {
//    @Autowired
//    DemoServ demoServ;
    @Mock
    Sql sql;
    @InjectMocks
    DemoServ serv;
//    @Mock
//    Sql s;

    @BeforeEach
    void setup() {

        Entitymodel entity = Entitymodel.builder()
                .idd(1)
                .you("aaaa")
                .build();

    }

    @Test
    public void GetAllTest() {
        Entitymodel entitymodel = Entitymodel.builder()
                .idd(2)
                .you("bbbb").build();
        Mockito.when(sql.findAll()).thenReturn(List.of(entitymodel, new Entitymodel(1, "ghj")));


        MatcherAssert.assertThat(serv.GetAll(), Matchers.hasSize(2));
        Mockito.verify(sql, Mockito.times(1)).findAll();
        Mockito.verifyNoMoreInteractions(sql);



    }
    @Test
    public void when_save_then_save() {
        Entitymodel entity = Entitymodel.builder()
                .idd(1)
                .you("aaaa")
                .build();
        Mockito.when(sql.save(entity)).thenReturn(entity);
        serv.Save(entity);
        Mockito.verify(sql,Mockito.times(1)).save(Mockito.any(Entitymodel.class));
        Mockito.verifyNoMoreInteractions(sql);
    }
    @Test
    public void getbyidTest() {
        Entitymodel entity = Entitymodel.builder()
                .idd(1)
                .you("aaaa")
                .build();

        Mockito.when(sql.findById(Mockito.anyInt())).thenReturn(Optional.of(entity));
        serv.getByIdd(Mockito.anyInt());
        Mockito.verify(sql,Mockito.times(1)).findById(Mockito.anyInt());
        Mockito.verifyNoMoreInteractions(sql);

    }

    @Test
    public void delbyidTest() {
        Entitymodel entity = Entitymodel.builder()
                .idd(1)
                .you("aaaa")
                .build();
        Mockito.doNothing().when(sql).deleteById(Mockito.anyInt());
        serv.DelById(Mockito.anyInt());
        Mockito.verify(sql,Mockito.times(1)).deleteById(Mockito.anyInt());
        Mockito.verifyNoMoreInteractions(sql);
    }
}



//        Notice that we are using the assertThat() method to assert the conditions using the AssertJ library.
//
//                Mockito @Mock annotation is useful when we want to use the mocked object at multiple places.
//
//        When we want to inject a mocked object into another mocked object, we can use @InjectMocks annotation. @InjectMock creates the mock object of the class and injects the mocks that are marked with the annotations @Mock into it.
//


//        Mockito verify() method can be used to test number of method invocations too. We can test exact number of times, at least once, at least, at most number of invocation times for a mocked method.
//                We can use verifyNoMoreInteractions() after all the verify() method calls to make sure everything is verified. If any method verification is still left, it will fail and provide proper message.
//        verifyZeroInteractions() behavior is same as verifyNoMoreInteractions() method.
//                We can use inOrder() method to verify the order of method invocation. We can skip a method invocation but the methods being verified must be in the same order.
//                Let’s look at some of the mockito verify method examples.
//
//        Mockito verify() simple example
//        @Test
//        void test() {
//            List<String> mockList = mock(List.class);
//            mockList.add("Pankaj");
//            mockList.size();
//
//            verify(mockList).add("Pankaj");
//        }
//        Above verify method will pass if add("Pankaj") is called only once on the mocked list object. It’s the same as calling with times(1) argument with verify method.
//
//        verify(mockList, times(1)).size();
//        If we want to make sure a method is called but we don’t care about the argument, then we can use ArgumentMatchers with verify method.
//
//                verify(mockList).add(anyString());
//        verify(mockList).add(any(String.class));
//        verify(mockList).add(ArgumentMatchers.any(String.class));
//        Note that org.mockito.Mockito class provides static methods for most of the useful methods in the Mockito framework, this helps us in writing fluent code by importing them using import static.
//
//        Mockito verify with number of times
//        Mockito verify() method is overloaded, the second one is verify(T mock, VerificationMode mode). We can use it to verify for the invocation count.
//
//                verify(mockList, times(1)).size(); //same as normal verify method
//        verify(mockList, atLeastOnce()).size(); // must be called at least once
//        verify(mockList, atMost(2)).size(); // must be called at most 2 times
//        verify(mockList, atLeast(1)).size(); // must be called at least once
//        verify(mockList, never()).clear(); // must never be called
//        verifyNoMoreInteractions()
//        This method can be used after all the verify methods to make sure that all the interactions are verified. It will fail the test if there are any unverified interactions on the mocked object.
//
//                verifyZeroInteractions()
//        verifyZeroInteractions() method behavior is same as verifyNoMoreInteractions() method.
//
//                Map mockMap = mock(Map.class);
//        Set mockSet = mock(Set.class);
//        verify(mockList).isEmpty();
//        verifyZeroInteractions(mockList, mockMap, mockSet);
//        Mockito verify only method call
//        If we want to verify that only one method is being called, then we can use only() with verify method.
//                Map mockMap = mock(Map.class);
//        mockMap.isEmpty();
//        verify(mockMap, only()).isEmpty();
//        Mockito Verify Order of Invocation
//        We can use InOrder to verify the order of invocation. We can skip any method to verify, but the methods being verified must be invoked in the same order.
//
//                InOrder inOrder = inOrder(mockList, mockMap);
//        inOrder.verify(mockList).add("Pankaj");
//        inOrder.verify(mockList, calls(1)).size();
//        inOrder.verify(mockList).isEmpty();
//        inOrder.verify(mockMap).isEmpty();
//        Summary
//        Mockito verify() methods can be used to make sure the mock object methods are being called. If any method call is deleted by mistake, then verify method will throw an error.


