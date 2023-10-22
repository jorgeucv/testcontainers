package com.example.springbootapp;

import com.example.springbootapp.repository.PriceRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityManagerFactory;
import javax.persistence.metamodel.EntityType;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class AnotherIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Environment env;

    @Autowired
    private PriceRepository priceRepository;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Test
    void testEnv() {
        log.error(env.getProperty("developer"));
        for (EntityType<?> entityType : entityManagerFactory.getMetamodel().getEntities()) {
            log.error(entityType.getJavaType().getCanonicalName());
        }

        int pricesCount = priceRepository.findAll().size();

        assertEquals(env.getActiveProfiles()[0], "integration-test" );
        assertEquals(pricesCount, 4);
    }

    @Test
    public void testQueryPrice_case1() throws Exception {
        log.info("\\o/ ".repeat(1));
        mockMvc.perform(get("/prices/query?productId=35455&brandId=1&date=2020-06-14T10:00:00"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.date", is("2020-06-14T10:00:00")))
                .andExpect(jsonPath("$.amount", is(35.5)))
                .andExpect(jsonPath("$.currency", is("EUR")))
                .andExpect(jsonPath("$.productId", is(35455)))
                .andExpect(jsonPath("$.brandId", is(1)))
                .andExpect(jsonPath("$.priceList", is("Price List 1")))
                .andExpect(jsonPath("$.priority", is(0)))
                .andExpect(jsonPath("$.startDate", is("2020-06-14T00:00:00")))
                .andExpect(jsonPath("$.endDate", is("2020-12-31T23:59:59")));
    }

    @Test
    public void testQueryPrice_case2() throws Exception {
        log.info("\\o/ ".repeat(2));
        mockMvc.perform(get("/prices/query?productId=35455&brandId=1&date=2020-06-14T16:00:00"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.date", is("2020-06-14T16:00:00")))
                .andExpect(jsonPath("$.amount", is(25.45)))
                .andExpect(jsonPath("$.currency", is("EUR")))
                .andExpect(jsonPath("$.productId", is(35455)))
                .andExpect(jsonPath("$.brandId", is(1)))
                .andExpect(jsonPath("$.priceList", is("Price List 2")))
                .andExpect(jsonPath("$.priority", is(1)))
                .andExpect(jsonPath("$.startDate", is("2020-06-14T15:00:00")))
                .andExpect(jsonPath("$.endDate", is("2020-06-14T18:30:00")));
    }

}
