package com.coding.graychain_practical.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.coding.graychain_practical.entity.Loan;
import com.coding.graychain_practical.entity.LoanAggregate;
import com.coding.graychain_practical.service.LoanService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(MockitoJUnitRunner.class)
public class LoanControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private LoanController loanController;

    @Mock
    private LoanService loanService;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(loanController).build();
    }

    @Test
    public void testGetAllLoans() throws Exception {
        List<Loan> loans = new ArrayList<>();
       
        Mockito.when(loanService.getLoans()).thenReturn(loans);

        mockMvc.perform(MockMvcRequestBuilders.get("/loans/all"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
    }

    @Test
    public void testAddLoan() throws Exception {
        Loan loan = new Loan();
       
        Mockito.when(loanService.addLoan(Mockito.any(Loan.class))).thenReturn(loan);

        mockMvc.perform(MockMvcRequestBuilders.post("/loans/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(loan)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }
    
    @Test
    public void testAddLoanBadRequest() throws Exception {
        Loan loan = new Loan();

        mockMvc.perform(MockMvcRequestBuilders.post("/loans/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(loan.toString()))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();
    }

    @Test
    public void testGetLoanByLoanId() throws Exception {
        String loanId = "L1";
        Loan loan = new Loan();

        Mockito.when(loanService.getLoanByLoanId(loanId)).thenReturn(loan);

        mockMvc.perform(MockMvcRequestBuilders.get("/loans/{loanId}", loanId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
    }
    
    @Test
    public void testGetLoanByLoanIdFailure() throws Exception {
        String loanId = "L11";

        mockMvc.perform(MockMvcRequestBuilders.get("/loans/{loanId}", loanId))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();
    }
    
    @Test
    public void testGetLoanByLoanIdClientError() throws Exception {
        String loanId = "L11";

        mockMvc.perform(MockMvcRequestBuilders.get("/loans/{}", loanId))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andReturn();
    }

    @Test
    public void testGetLoansByCustomerId() throws Exception {
        String customerId = "C1";
        List<Loan> loans = new ArrayList<>();
     
        Mockito.when(loanService.getLoanByCustomerId(customerId)).thenReturn(loans);

        mockMvc.perform(MockMvcRequestBuilders.get("/loans/customer/{customerId}", customerId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))                
                .andReturn();
    }

    @Test
    public void testGetLoansByLenderId() throws Exception {
        String lenderId = "LEN1";
        List<Loan> loans = new ArrayList<>();

        Mockito.when(loanService.getLoanByLenderId(lenderId)).thenReturn(loans);

        mockMvc.perform(MockMvcRequestBuilders.get("/loans/lender/{lenderId}", lenderId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))                
                .andReturn();
    }

    @Test
    public void testGetAggregateLoansByLender() throws Exception {
        List<LoanAggregate> aggregates = new ArrayList<>();
       
        Mockito.when(loanService.getAggregateLoansByLender()).thenReturn(aggregates);

        mockMvc.perform(MockMvcRequestBuilders.get("/loans/aggregate/lender"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))                
                .andReturn();
    }

    @Test
    public void testGetAggregateLoansByCustomer() throws Exception {
        List<LoanAggregate> aggregates = new ArrayList<>();
        
        Mockito.when(loanService.getAggregateLoansByCustomer()).thenReturn(aggregates);

        mockMvc.perform(MockMvcRequestBuilders.get("/loans/aggregate/customer"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))                
                .andReturn();
    }

    @Test
    public void testGetAggregateLoansByInterest() throws Exception {
        List<LoanAggregate> aggregates = new ArrayList<>();
       
        Mockito.when(loanService.getAggregateLoansByInterest()).thenReturn(aggregates);

        mockMvc.perform(MockMvcRequestBuilders.get("/loans/aggregate/interest"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))                
                .andReturn();
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
