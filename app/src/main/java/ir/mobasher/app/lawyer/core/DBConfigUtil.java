package ir.mobasher.app.lawyer.core;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;
import java.io.IOException;
import java.sql.SQLException;
import ir.mobasher.app.lawyer.model.users.User;

public class DBConfigUtil extends OrmLiteConfigUtil {
    private static final Class<?>[] classes = new Class[] {User.class};

    public static void main(String[] args) throws IOException, SQLException {
        writeConfigFile("ormlite_config.txt",classes);
    }

}
