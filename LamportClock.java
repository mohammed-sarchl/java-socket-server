// Lamport logical clock: one per process.
// Rules from the lecture (with d = 1):
//   Every event (internal or send):  C = C + d
//   Receive event:                   C = max(C, C_msg); C = C + d
public class LamportClock {
	private int time = 0;

	// Internal event or send event: tick the clock and return the new value.
	// For a send event, the returned value is attached to the message.
	public synchronized int tick() {
		time = time + 1;
		return time;
	}

	// Receive event: take the max of the local clock and the message clock, then tick.
	public synchronized int receive(int messageTime) {
		time = Math.max(time, messageTime);
		time = time + 1;
		return time;
	}

	public synchronized int getTime() {
		return time;
	}
}
