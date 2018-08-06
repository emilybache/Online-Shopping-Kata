package codingdojo;

public class NonSavingSession extends Session {
    @Override
    public void saveAll() {
        // do not save anything in unit tests
    }
}
