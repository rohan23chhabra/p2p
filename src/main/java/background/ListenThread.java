package background;

import core.User;

public class ListenThread extends Thread {

    private User user;
    public ListenThread(User user) {
        this.user = user;
    }

    @Override
    public void run() {

    }


}
