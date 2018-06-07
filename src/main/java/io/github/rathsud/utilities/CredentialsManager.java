package io.github.rathsud.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CredentialsManager
{
    final private static String CURSE_CONFIG = "src/main/resources/Curse.config";

    final private static Properties credentialProperties = import_credentials(CURSE_CONFIG);

    final public static String username = getProp(credentialProperties, "username");
    final public static String password = getProp(credentialProperties, "password");
    final public static String clientId = getProp(credentialProperties, "clientId");
    final public static String clientSecret = getProp(credentialProperties, "clientSecret");

    final public static String platform = getProp(credentialProperties, "platform");
    final public static String appId = getProp(credentialProperties, "appId");
    final public static String version = getProp(credentialProperties, "version");

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
            Grunt.LOG.error(ie);
            System.exit(StatusCodeManager.IMPORT_ERROR);
        }

        return p;
    }

    private static String getProp(Properties p, String key)
    {
        Grunt.LOG.debug(p.getProperty(key));

        return p.getProperty(key);
    }


}
