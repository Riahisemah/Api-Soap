package org.example.server;

import javax.xml.ws.Endpoint;
import org.example.Service.Banque_Service;

public class server {
	public static void main(String[] args) {
		Endpoint.publish("http://localhost:8082/", new Banque_Service());
		System.out.print("run in http://localhost:8082/untitled/BanqueWS?wsdl " );
	}
}
