public class Forks {
    private final Fork[] forksArray;

    public Forks(int numberOfPhilosophers){
        forksArray = new Fork[numberOfPhilosophers];

        for(int i = 0; i < numberOfPhilosophers; i++){
            forksArray[i] = new Fork();
        }
    }

    public Fork getFork(int index){
        return forksArray[index];
    }
}
