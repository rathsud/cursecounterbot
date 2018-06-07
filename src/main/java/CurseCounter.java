import net.dean.jraw.RedditClient;
import net.dean.jraw.http.OkHttpNetworkAdapter;
import net.dean.jraw.http.UserAgent;
import net.dean.jraw.oauth.Credentials;
import net.dean.jraw.oauth.OAuthHelper;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CurseCounter
{
    private final static int IMPORT_ERROR = 1;
    private static final Logger LOG = LogManager.getLogger();
    private static final String CURSE_CONFIG = "src/main/resources/Curse.config";


    static Properties import_credentials(String properties)
    {
        Properties p = new Properties();
        InputStream input;

        try
        {
            input = new FileInputStream(properties);
            p.load(input);

            LOG.debug(p.getProperty("username"));
            LOG.debug(p.getProperty("password"));
            LOG.debug(p.getProperty("clientId"));
            LOG.debug(p.getProperty("clientSecret"));

        }
        catch (IOException ie)
        {
            LOG.error(ie);
            System.exit(IMPORT_ERROR);
        }

        return p;
    }

    public static void main(String[] args)
    {
        Properties credentialProperties = import_credentials(CURSE_CONFIG);

        Credentials credentials =
                Credentials.script(
                        credentialProperties.getProperty("username"),
                        credentialProperties.getProperty("password"),
                        credentialProperties.getProperty("clientId"),
                        credentialProperties.getProperty("clientSecret"));

        UserAgent userAgent = new UserAgent(
                "bot",
                "curse.counter.bot",
                "1.0.0",
                "CurseCounter");

        RedditClient reddit = OAuthHelper.automatic(
                new OkHttpNetworkAdapter(userAgent),
                credentials);

    }

}
