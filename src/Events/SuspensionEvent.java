package Events;

public class SuspensionEvent {
    private boolean suspended;

    public SuspensionEvent(boolean isSuspended) {
        this.suspended = isSuspended;
    }

    public boolean isSuspended() {
        return suspended;
    }

    public void setSuspended(boolean suspended) {
        this.suspended = suspended;
    }
}
