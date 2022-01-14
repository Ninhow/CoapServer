/*
 * Copyright (c) 2018 NetSec Lab - University of Parma (Italy)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND. IN NO EVENT
 * SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 *
 * Author(s):
 * Luca Veltri (luca.veltri@unipr.it)
 */

package it.unipr.netsec.mjcoap.coap.provider;


import it.unipr.netsec.mjcoap.coap.message.CoapMessage;


/** Listen for CoapProvider events.
  */
public interface CoapProviderListener {
	
	/** When a new CoAP message is received.
	  * @param coap_provider the CoAP provider
	  * @param msg the received CoAP message */
	public void onReceivedMessage(CoapProvider coap_provider, CoapMessage msg);

	/** When CoapProvider terminates.
	* @param coap_provider the CoAP provider
	* @param error the error that caused the termination or <i>null</i> */
	//public void onServiceTerminated(CoapProvider coap_provider, Exception error);

}
