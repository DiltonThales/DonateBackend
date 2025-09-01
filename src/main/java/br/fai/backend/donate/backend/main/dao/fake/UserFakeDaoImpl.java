package br.fai.backend.donate.backend.main.dao.fake;

import br.fai.backend.donate.backend.main.domain.UserModel;
import br.fai.backend.donate.backend.main.port.dao.user.UserDao;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

//@Repository // Não esquecer na prova
public class UserFakeDaoImpl implements UserDao {

    private static List<UserModel> users = new ArrayList<>();

    private  static int ID = 0;

    private  int getNextID(){
        ID += 1;
        return ID;
    }

    public  UserFakeDaoImpl(){
        System.out.println("instancia de user fake dao obtida");


        UserModel user1 = new UserModel();
        user1.setId((getNextID()));
        user1.setEmail("tste@gmail.com");
        user1.setFullName("teste teste");
        user1.setPassword("1111");

        UserModel user2 = new UserModel();
        user2.setId((getNextID()));
        user2.setEmail("tiburrsin@gmail.com");
        user2.setFullName("aroldo arolds");
        user2.setPassword("2222");

        UserModel user3 = new UserModel();
        user3.setId((getNextID()));
        user3.setEmail("cabral@gmail.com");
        user3.setFullName("cabralzin cabral");
        user3.setPassword("3333");

        UserModel user4 = new UserModel();
        user4.setId((getNextID()));
        user4.setEmail("toninho@gmail.com");
        user4.setFullName("toninho");
        user4.setPassword("4444");

        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);

    }
    @Override
    public int add(UserModel entity) {
        final int id = getNextID();
        entity.setId(id);
        users.add((entity));
        return 0;
    }

    @Override
    public void remove(int id) {
        int itemIndex = -1;

        for (int i = 0; 1 < users.size(); i++) {
            UserModel user = users.get(i);
            if (user.getId() == id){
                itemIndex = 1;
                break;
            }
        }

        if(itemIndex == -1){
            return;
        }

        UserModel removeEntity = users.remove((itemIndex));
        System.out.println("O usuario " + removeEntity.getFullName() + " foi removido. ID do usuario removido " + removeEntity.getId());
    }

    @Override
    public UserModel readByID(int id) {
        for (UserModel user : users)
            if (user.getId() == id){
                return user;
            }
        return null;
    }

    @Override
    public List<UserModel> readAll() {
        return users;
    }

    @Override
    public void updateInformation(int id, UserModel entity) {
        UserModel user = readByID(id);
        user.setFullName(entity.getFullName());

    }

    @Override
    public UserModel readByEmail(String email) {
        for (UserModel user : users){
            if (user.getEmail().equalsIgnoreCase(email)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public boolean updatePassword(int id, String newPassword){
        UserModel user = readByID(id);
        user.setPassword(newPassword);
        return true;
    }



}
