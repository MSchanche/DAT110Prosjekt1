package no.hvl.dat110.system.controller;

import no.hvl.dat110.rpc.*;

public class DisplayStub extends RPCLocalStub {

	public DisplayStub(RPCClient rpcclient) {
		super(rpcclient);
	}
	
	public void write (String message) {

		byte[] request = RPCUtils.marshallString(message);

		byte[] response = rpcclient.call((byte)Common.WRITE_RPCID, request);
	}
}
