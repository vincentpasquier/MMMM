package ch.eiafr.mmmm;

import motej.Mote;
import motej.event.IrCameraEvent;
import motej.event.IrCameraListener;

public class WiiHand {
	public static void main(String[] args) {
		System.out.println("Hello world");
		Mote mote = new Mote("E84ECECD5999");
		mote.addIrCameraListener(new IrCameraListener() {

			@Override
			public void irImageChanged(IrCameraEvent arg0) {
				System.out.println(arg0.toString());
			}
		});
		//
		// final ArrayList<Mote> motes = new ArrayList<Mote>();
		// MoteFinderListener listener = new MoteFinderListener() {
		//
		// public void moteFound(Mote mote) {
		// System.out.println("Found mote: " + mote.getBluetoothAddress());
		// mote.rumble(2000l);
		// motes.add(mote);
		// }
		//
		// };
		//
		// MoteFinder finder = MoteFinder.getMoteFinder();
		// finder.addMoteFinderListener(listener);
		//
		// finder.startDiscovery();
		// try {
		// Thread.sleep(2000);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// //finder.stopDiscovery();
	}
}
