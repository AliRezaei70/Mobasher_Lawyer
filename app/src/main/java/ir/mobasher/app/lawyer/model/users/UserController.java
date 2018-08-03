package ir.mobasher.app.lawyer.model.users;

import android.content.Context;
import com.j256.ormlite.dao.Dao;
import ir.mobasher.app.lawyer.core.DatabaseHelper;


/**
 * Created by ALI_REZAEI on 12/24/2017.
 */

public class UserController {
    public void addUser(User user, Context context) {
        try {
            Dao<User, Integer> userDao = DatabaseHelper.getInstance(context).getUsersDao();

            userDao.createOrUpdate(user);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
