package ch.eiafr.mmmm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import motej.IrPoint;
import motej.Mote;
import motej.event.IrCameraEvent;
import motej.event.IrCameraListener;
import motej.request.ReportModeRequest;
import ch.eiafr.mmmm.messages.Tasks;
import ch.eiafr.mmmm.net.NetworkMessage.EventMessage.Source;
import ch.eiafr.mmmm.net.SocketThreadedTasksSender;

public class IrCamera {

	public static void main(String[] args) {

		// 127.0.0.1 4444
		if (args.length < 2) {
			System.err.println("usage: wiihand ipadress port");
			System.exit(0);
		}

		SimpleMoteFinder simpleMoteFinder = new SimpleMoteFinder();
		final Mote mote = simpleMoteFinder.findMote();
		mote.enableIrCamera();
		mote.setReportMode(ReportModeRequest.DATA_REPORT_0x36);

		mote.addIrCameraListener(new IRListener(args[0], Integer.parseInt(args[1]), Areas.AREAS, Areas.CENTER));

		try {
			Thread.sleep(600000000l);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			mote.setReportMode(ReportModeRequest.DATA_REPORT_0x30);
			mote.disableIrCamera();
			mote.disconnect();
		}
	}

	public static final class IRListener implements IrCameraListener {

		private final String ipadress;
		private final int port;
		private final List<Area> areas;
		private Area currentArea;

		public IRListener(final String ipadress, final int port, final List<Area> areas, final Area center) {
			this.ipadress = ipadress;
			this.port = port;
			this.areas = areas;
			this.currentArea = center;
		}

		@Override
		public void irImageChanged(IrCameraEvent evt) {

			IrPoint point = evt.getIrPoint(0);

			// It's already in the same area, nothing to do!
			if (currentArea.isIn(point)) {
				return;
			}

			// Received maximal value and current area is not center, set center
			// and send it.
			if (Area.isMaximalValue(point)) {
				return;
			}

			// Checks in which area it is, sets it and send area.
			for (Area area : areas) {
				if (area.isIn(point)) {
					currentArea = area;
					System.out.println(currentArea.getTask());
					new SocketThreadedTasksSender(ipadress, port, Tasks.getEventMessage(currentArea.getTask(), Source.WII_HEAD, 0)).execute();
					return;
				}
			}
		}
	}

	public static final class Areas {
		private Areas() {
			throw new AssertionError();
		}

		public static final double DEFAULT_SCALE = 0.20;
		public static final double BIGGER_SCALE = 0.60;

		public static final Area UP_RIGHT = new Area(10, 10, DEFAULT_SCALE, DEFAULT_SCALE, Tasks.SIGHT_UP_RIGHT);
		public static final Area UP = Area.buildAppendToX(UP_RIGHT, BIGGER_SCALE, DEFAULT_SCALE, Tasks.SIGHT_UP);
		public static final Area UP_LEFT = Area.buildAppendToX(UP, DEFAULT_SCALE, DEFAULT_SCALE, Tasks.SIGHT_UP_LEFT);

		public static final Area RIGHT = Area.buildAppendToY(UP_RIGHT, DEFAULT_SCALE, BIGGER_SCALE, Tasks.SIGHT_RIGHT);
		public static final Area CENTER = Area.buildAppendToX(RIGHT, BIGGER_SCALE, BIGGER_SCALE, Tasks.SIGHT_CENTER);
		public static final Area LEFT = Area.buildAppendToX(CENTER, DEFAULT_SCALE, DEFAULT_SCALE, Tasks.SIGHT_LEFT);
		
		public static final Area DOWN_RIGHT = Area.buildAppendToY(RIGHT, DEFAULT_SCALE, DEFAULT_SCALE, Tasks.SIGHT_DOWN_RIGHT);
		public static final Area DOWN = Area.buildAppendToX(DOWN_RIGHT, BIGGER_SCALE, DEFAULT_SCALE, Tasks.SIGHT_DOWN);
		public static final Area DOWN_LEFT = Area.buildAppendToX(DOWN, DEFAULT_SCALE, DEFAULT_SCALE, Tasks.SIGHT_DOWN_LEFT);

		public static final List<IrCamera.Area> AREAS = Collections.unmodifiableList(new ArrayList<IrCamera.Area>(Arrays.asList(new Area[] {
				UP_RIGHT, UP, UP_LEFT, RIGHT, CENTER, LEFT, DOWN_RIGHT, DOWN, DOWN_LEFT })));

	}

	public static final class Area {

		public static final double X_Y_LIMIT = 800.0;

		private final double xbegin;
		private final double ybegin;
		private final double xend;
		private final double yend;
		private final Tasks task;

		public Area(final double xbegin, final double ybegin, final double xscale, final double yscale, Tasks task) {
			this.xbegin = xbegin;
			this.ybegin = ybegin;
			this.task = task;
			this.xend = (xscale * X_Y_LIMIT) + xbegin;
			this.yend = (yscale * X_Y_LIMIT) + ybegin;

		}

		public static Area buildAppendToX(final Area area, final double xscale, final double yscale, final Tasks task) {
			return new Area(area.xend + 1, area.ybegin, xscale, yscale, task);
		}

		public static Area buildAppendToY(final Area area, final double xscale, final double yscale, final Tasks task) {
			return new Area(area.xbegin, area.yend + 1, xscale, yscale, task);
		}

		public Tasks getTask() {
			return task;
		}

		public boolean isIn(IrPoint point) {
			if (point.x <= xbegin) {
				return false;
			}
			if (point.x >= xend) {
				return false;
			}
			if (point.y <= ybegin) {
				return false;
			}
			if (point.y >= yend) {
				return false;
			}

			return true;
		}

		public static boolean isMaximalValue(IrPoint point) {
			return (Double.compare(point.x, X_Y_LIMIT) >= 0) && (Double.compare(point.y, X_Y_LIMIT) >= 0);
		}

		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("{ [").append(xbegin).append(", ").append(ybegin).append(" ],");
			sb.append(" [").append(xend).append(", ").append(yend).append(" ] }");
			return sb.toString();
		}
	}
}
