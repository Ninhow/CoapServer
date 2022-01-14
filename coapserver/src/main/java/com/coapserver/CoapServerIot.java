package com.coapserver;

import java.net.SocketException;

import it.unipr.netsec.mjcoap.coap.message.*;
import it.unipr.netsec.mjcoap.coap.server.*;
import it.unipr.netsec.mjcoap.coap.server.AbstractCoapServer;


import com.profesorfalken.jsensors.JSensors;
import com.profesorfalken.jsensors.model.components.Components;
import com.profesorfalken.jsensors.model.components.Cpu;
import com.profesorfalken.jsensors.model.components.Gpu;

import java.util.List;

public class CoapServerIot extends AbstractCoapServer{

    public CoapServerIot() throws SocketException{

    }

    @Override
	protected void handleGetRequest(CoapRequest req) {
        System.out.println("GET REQUESTED");
		String resource_name = req.getRequestUriPath();
		if (resource_name.equals("/temp")) {
			CoapResponse resp = CoapMessageFactory.createResponse(req,CoapResponseCode._2_05_Content);
			resp.setPayload(CoapResource.FORMAT_TEXT_PLAIN_UTF8, getGpuTemp().getBytes());
			respond(req,resp);
		}
		else {
			CoapResponse resp = CoapMessageFactory.createResponse(req,CoapResponseCode._4_04_Not_Found);
			respond(req,resp);
		}
	}

    public static String getGpuTemp() {
        Components components = JSensors.get.components();
        List<Gpu> gpus = components.gpus;

        return gpus.get(0).sensors.temperatures.get(0).value.toString();
    }


}
