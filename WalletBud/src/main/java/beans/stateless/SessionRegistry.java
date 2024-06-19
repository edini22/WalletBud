package beans.stateless;

import jakarta.ejb.Lock;
import jakarta.ejb.LockType;
import jakarta.ejb.Singleton;
import jakarta.websocket.Session;
import wb.walletbud.User;

import java.util.*;

@Singleton
public class SessionRegistry {

    private final Map<Session, User> sessions = new HashMap<>();

    @Lock(LockType.READ)
    public Map<Session, User> getAll() {
        return Collections.unmodifiableMap(sessions);
    }

    @Lock(LockType.WRITE)
    public void add(Session session, User user) {
        sessions.put(session, user);
    }

    @Lock(LockType.WRITE)
    public void remove(Session session) {
        sessions.remove(session);
    }
}