import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public interface MediaManagement extends Remote {
    List<Media> getBooks() throws RemoteException;
    List<Media> getNews() throws RemoteException;
    boolean login(String username, String password) throws RemoteException;
    void play(int pos1, int pos2, int character) throws RemoteException;
    String getWinner() throws RemoteException;
    int[][] getTic() throws RemoteException;
    int getSlot() throws RemoteException;
}