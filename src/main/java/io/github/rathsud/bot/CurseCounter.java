package io.github.rathsud.bot;

import net.dean.jraw.RedditClient;
import net.dean.jraw.http.OkHttpNetworkAdapter;
import net.dean.jraw.http.UserAgent;
import net.dean.jraw.oauth.Credentials;
import net.dean.jraw.oauth.OAuthHelper;

import io.github.rathsud.utilities.CredentialsManager;

public class CurseCounter
{

    public static void main(String[] args)
    {

        Credentials credentials =
                Credentials.script(
                        CredentialsManager.username,
                        CredentialsManager.password,
                        CredentialsManager.clientId,
                        CredentialsManager.clientSecret);

        UserAgent userAgent =
                new UserAgent(CredentialsManager.platform,
                        CredentialsManager.appId,
                        CredentialsManager.version,
                        CredentialsManager.username);

        RedditClient reddit = OAuthHelper.automatic(
                new OkHttpNetworkAdapter(userAgent),
                credentials);

    }

}
