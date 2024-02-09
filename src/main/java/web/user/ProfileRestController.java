package web.user;

import model.User;

import static utils.SecurityUtil.authUserId;

public class ProfileRestController extends AbstractUserController {
    @Override
    public User get(int id) {
        return super.get(authUserId());
    }

    @Override
    public void delete(int id) {
        super.delete(authUserId());
    }

    @Override
    public void update(User user, int id) {
        super.update(user, authUserId());
    }
}
