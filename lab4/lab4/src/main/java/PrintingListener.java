public class PrintingListener implements PhilospherListener {
    @Override
    public void newInformation(String information) {
        System.out.println(information);
    }

    @Override
    public void newImportantInformation(String information) {
        System.out.println(information);
    }
}
