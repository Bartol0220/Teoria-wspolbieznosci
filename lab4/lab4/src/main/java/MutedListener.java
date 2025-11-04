public class MutedListener implements PhilospherListener {
    @Override
    public void newInformation(String information) {
        return;
    }

    @Override
    public void newImportantInformation(String information) {
        System.out.println(information);
    }
}
