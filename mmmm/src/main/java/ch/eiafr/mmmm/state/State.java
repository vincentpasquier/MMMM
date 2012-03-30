package ch.eiafr.mmmm.state;

import ch.eiafr.mmmm.messages.Executable;
import ch.eiafr.mmmm.messages.Tasks;

public interface State extends Executable {
	State update(Tasks task);
}
