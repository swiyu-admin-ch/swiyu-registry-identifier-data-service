package ch.admin.bj.swiyu.registry.identifier.data.infrastructure.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ActiveProfiles("test")
@AutoConfigureMockMvc
class DidControllerIT {

    private static final String ENTRY_DID_URL = "/api/v1/did/";

    @Autowired
    protected MockMvc mvc;

    @Test
    void testCheckDid_invalidId_response() throws Exception {
        mvc.perform(get(ENTRY_DID_URL + "unknown/did.json")).andExpect(status().is4xxClientError());
    }

    @Test
    void testCheckDid_unknownId_response() throws Exception {
        mvc
            .perform(get(ENTRY_DID_URL + "00000000-0000-0000-0000-00000000000a/did.json"))
            .andExpect(status().isNotFound());
    }

    @Test
    void testCheckDidWeb_statusIsSetup_response() throws Exception {
        mvc
            .perform(get(ENTRY_DID_URL + "00000000-0000-0000-0000-000000000000/did.json"))
            .andExpect(status().isTooEarly());
    }

    @Test
    void testCheckDidTdw_statusIsSetup_response() throws Exception {
        mvc
            .perform(get(ENTRY_DID_URL + "00000000-0000-0000-0000-000000000000/did.jsonl"))
            .andExpect(status().isTooEarly());
    }

    @Test
    void testCheckDid_validDidTdw_response() throws Exception {
        var id = "00000000-0000-0000-0000-000000000001";
        mvc.perform(get(ENTRY_DID_URL + id + "/did.json")).andExpect(status().isNotFound());
        mvc
            .perform(get(ENTRY_DID_URL + id + "/did.jsonl"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/jsonl+json"))
            .andExpect(content().string("dummy_json_log"));
    }

    @Test
    void testCheckDid_validDidWeb_response() throws Exception {
        var id = "00000000-0000-0000-0000-000000000002";
        mvc
            .perform(get(ENTRY_DID_URL + id + "/did.json"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(content().string("{\"json\":\"code\"}"));
        mvc.perform(get(ENTRY_DID_URL + id + "/did.jsonl")).andExpect(status().isNotFound());
    }

    @Test
    void testCheckDid_validDidWebAndDidTdw_response() throws Exception {
        var id = "00000000-0000-0000-0000-000000000003";
        mvc
            .perform(get(ENTRY_DID_URL + id + "/did.json"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(content().string("{\"json\":\"code\"}"));
        mvc
            .perform(get(ENTRY_DID_URL + id + "/did.jsonl"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/jsonl+json"))
            .andExpect(content().string("dummy_json_log"));
    }

    @Test
    void testCheckDid_statusIsDisabled_response() throws Exception {
        mvc
            .perform(get(ENTRY_DID_URL + "00000000-0000-0000-0000-000000000004/did.json"))
            .andExpect(status().isTooEarly());
    }

    @Test
    void testCheckDid_statusIsDeactivated_response() throws Exception {
        mvc
            .perform(get(ENTRY_DID_URL + "00000000-0000-0000-0000-000000000005/did.json"))
            .andExpect(status().isTooEarly());
    }
}
