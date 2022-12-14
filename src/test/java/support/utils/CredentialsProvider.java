package support.utils;

import models.Credentials;

import static support.utils.FileUtils.getObjectFromResourceFile;

public final class CredentialsProvider {

    private static final String DEFAULT_CREDENTIALS_PATH = "credentials.json";

    public static Credentials getDefaultCredentials() {
        return getObjectFromResourceFile(DEFAULT_CREDENTIALS_PATH, Credentials.class);
    }

}
