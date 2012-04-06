/**
 * 
 */
package ch.eiafr.mmmm.gui;

/**
 * @author yannickjemmely
 * 
 */
public enum ServerAddress {
	INSTANCE;
	private String address;
	private int port;

	private ServerAddress() {
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @param port
	 *            the port to set
	 */
	public void setPort(int port) {
		this.port = port;
	}
}
