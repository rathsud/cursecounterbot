package io.github.rathsud.bot;

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
    final private static int IMPORT_ERROR = 1;
    final private static Logger LOG = LogManager.getLogger();
    final private static String CURSE_CONFIG = "src/main/resources/Curse.config";

    final static Properties credentialProperties = import_credentials(CURSE_CONFIG);

    final static String username = getProp(credentialProperties, "username");
    final static String password = getProp(credentialProperties, "password");
    final static String clientId = getProp(credentialProperties, "clientId");
    final static String clientSecret = getProp(credentialProperties, "clientSecret");

    final static String platform = getProp(credentialProperties, "platform");
    final static String appId = getProp(credentialProperties, "appId");
    final static String version = getProp(credentialProperties, "version");


    final private static Properties import_credentials(String properties)
    {
        Properties p = new Properties();
        InputStream input;

        try
        {
            input = new FileInputStream(properties);
            p.load(input);
        }
        catch (IOException ie)
        {
            LOG.error(ie);
            System.exit(IMPORT_ERROR);
        }

        return p;
    }

    private static String getProp(Properties p, String key)
    {
        /* TODO: Decouple into utilities class */

        LOG.debug(p.getProperty(key));

        return p.getProperty(key);
    }

    public static void main(String[] args)
    {

        Credentials credentials =
                Credentials.script(username, password, clientId, clientSecret);

        UserAgent userAgent =
                new UserAgent(platform, appId, version, username);

        RedditClient reddit = OAuthHelper.automatic(
                new OkHttpNetworkAdapter(userAgent),
                credentials);

    }

}
