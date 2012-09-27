/**
 * 
 */
package com.grant.jsip.core.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.grant.jsip.core.SIPRequest;
import com.grant.jsip.interfaces.RequestMethod;

/**
 * @author sean.madden
 *
 */
public class SIPRequestTest {

	private SIPRequest req;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		req = new SIPRequest();
	}

	@Test
	public void testBlankRequest() {
		assertEquals("Blank request failed", req.toString(), "OPTIONS / SIP/2.0\r\n");
	} 
	
	@Test
	public void testRegisterRequest(){
		req = new SIPRequest(RequestMethod.REGISTER, "/");
		 
		assertEquals("Register request failed", req.toString(), "REGISTER / SIP/2.0\r\n");
	}
	
	@Test
	public void testRegisterPath(){
		req = new SIPRequest(RequestMethod.REGISTER, "/testMethod");
		assertEquals("Register path failed", req.toString(), "REGISTER /testMethod SIP/2.0\r\n");
	}
	
	@Test
	public void testRegisterOneHeader(){
		req = new SIPRequest(RequestMethod.REGISTER, "/testMethod");
		req.addHeader("TestHeader", "TestValue");
		
		StringBuffer buf = new StringBuffer();
		buf.append("REGISTER /testMethod SIP/2.0\r\n");
		buf.append("TestHeader=TestValue\r\n");
		
		assertEquals("Register One Header failed", req.toString(), buf.toString());
	}

}
