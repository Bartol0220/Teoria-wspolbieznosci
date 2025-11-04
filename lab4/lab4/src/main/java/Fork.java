public class Fork {
    private boolean isUsed;

    public Fork(){
        isUsed = false;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void useFork(){
        isUsed = true;
    }

    public void relaseFork(){
        isUsed = false;
    }
}
