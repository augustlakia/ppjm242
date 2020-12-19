package web.services;

import web.model.User;

import java.util.List;

public interface UserServ {

    public void add(User user);

    public List<User> getUsersList();

    public void Update(User user);

    public void Delete(int id);

}
