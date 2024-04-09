package Users;

public class ActiveProvider {

    private static ActiveProvider active_provider = null;
    public String number;
    private ActiveProvider(String number) { this.number = number; }

    public static synchronized void login(String number) {
        if (active_provider == null) active_provider = new ActiveProvider(number);
        else active_provider.number = number;
    }

    public static synchronized ActiveProvider getInstance() {
        if (active_provider == null) throw new IllegalStateException("Provider not initialized");
        return active_provider;
    }
}