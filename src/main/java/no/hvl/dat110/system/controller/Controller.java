package no.hvl.dat110.system.controller;

import no.hvl.dat110.TODO;
import no.hvl.dat110.rpc.RPCClient;
import no.hvl.dat110.rpc.RPCClientStopStub;

public class Controller {

	private static int N = 5;

	public static void main(String[] args) {

		DisplayStub display;
		SensorStub sensor;

		RPCClient displayclient, sensorclient;

		System.out.println("Controller starting ...");

		// create RPC clients for the system
		displayclient = new RPCClient(Common.DISPLAYHOST, Common.DISPLAYPORT);
		sensorclient = new RPCClient(Common.SENSORHOST, Common.SENSORPORT);

		// setup stop methods in the RPC middleware
		RPCClientStopStub stopdisplay = new RPCClientStopStub(displayclient);
		RPCClientStopStub stopsensor = new RPCClientStopStub(sensorclient);


		// create local display and sensor stub objects
		display = new DisplayStub(displayclient);
		sensor = new SensorStub(sensorclient);

		// connect to sensor and display RPC servers - using the RPCClients
		displayclient.connect();
		sensorclient.connect();

		// read value from sensor using RPC and write to display using RPC
		for (int i = 0; i < N; i++) {

			int temp = sensor.read(); // Read temperature from the sensor stub
			System.out.println("Read from sensor: " + temp);

			display.write(String.valueOf(temp)); // Write temperature to the display stub
		}
		stopdisplay.stop();
		stopsensor.stop();

		displayclient.disconnect();
		sensorclient.disconnect();

		System.out.println("Controller stopping ...");
	}
}
