package com.example.bsu;

import com.example.bsu.controller.MathController.MathController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;

import static org.junit.Assert.assertEquals;

public class MathControllerTest extends Mockito {

    @Test
    public void testDoublingANumber() throws Exception {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        double randomNumber = Math.random();
        JSONObject requestJSON = new JSONObject();
        JSONObject expectedJSON = new JSONObject();

        requestJSON.put("number", randomNumber);
        expectedJSON.put("number", randomNumber * 2);

        when(resp.getWriter()).thenReturn(pw);
        when(req.getReader()).thenReturn(new BufferedReader(new StringReader(requestJSON.toString())));
        when(req.getPathInfo()).thenReturn("/twice");
        new MathController().doPost(req, resp);

        verify(resp).setStatus(HttpServletResponse.SC_OK);
        assertEquals(expectedJSON.toString(), sw.toString());
    }
}
