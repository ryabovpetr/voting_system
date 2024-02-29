package rpg.web.user;

import rpg.model.User;

import static rpg.utils.SecurityUtil.authUserId;

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
