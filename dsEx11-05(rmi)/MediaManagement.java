import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public interface MediaManagement extends Remote {
    List<Media> getBooks() throws RemoteException;
    List<Media> getNews() throws RemoteException;
}