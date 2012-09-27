/**
 * 
 */
package com.grant.jsip.core;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import com.grant.jsip.interfaces.RequestMethod;

/**
 * @author sean.madden
 *
 */
public class SIPRequest {

	private RequestMethod _method = RequestMethod.OPTIONS;
	private String _uri = "/";
	public static final String SIP_VERSION = "SIP/2.0";
	private static final String CRLF = "\r\n"; 
	
	private HashMap<String, Vector<String>> _headers = new HashMap<String, Vector<String>>();
	
	public SIPRequest(){
		
	}
	 
	public SIPRequest(RequestMethod _method, String _uri) {
		super();
		this._method = _method;
		this._uri = _uri;
	}

	/**
	 * Returns a list of the available headers in the request
	 * 
	 * @return Set containing the headers in this request
	 */
	public Set<String> getHeaders(){
		return Collections.unmodifiableSet(_headers.keySet());
	}
	
	/**
	 * Returns a list of the values for the specified header option 
	 * 
	 * @param header the header to pull values for
	 * @return A list of the values
	 */
	public List<String> getHeaderValues(String header){
		if(!_headers.containsKey(header)){
			return new Vector<String>();
		}
		return Collections.unmodifiableList(_headers.get(header));
	}
	
	/**
	 * Adds a header to the list of headers in this request
	 * 
	 * @param header header to add
	 * @param value value to associate with it.
	 */
	public void addHeader(String header, String value){
		if(!_headers.containsKey(header)){
			_headers.put(header, new Vector<String>());
		}
		_headers.get(header).add(value);
	}
	
	public RequestMethod get_method() {
		return _method;
	}

	public void set_method(RequestMethod _method) {
		this._method = _method;
	}

	public String get_uri() {
		return _uri;
	}

	public void set_uri(String _uri) {
		this._uri = _uri;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_method == null) ? 0 : _method.hashCode());
		result = prime * result + ((_uri == null) ? 0 : _uri.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SIPRequest other = (SIPRequest) obj;
		if (_method != other._method)
			return false;
		if (_uri == null) {
			if (other._uri != null)
				return false;
		} else if (!_uri.equals(other._uri))
			return false;
		return true;
	}

	public String toString(){
		StringBuffer bld = new StringBuffer();
		bld.append(_method);
		bld.append(' ');
		bld.append(_uri);
		bld.append(' ');
		bld.append(SIP_VERSION);
		bld.append(CRLF);
		
		for(Map.Entry<String, Vector<String>> e : _headers.entrySet()){
			for(String s : e.getValue()){
				bld.append(e.getKey());
				bld.append('=');
				bld.append(s);
				bld.append(CRLF);
			}
		}
		
		return bld.toString();
	}
}
