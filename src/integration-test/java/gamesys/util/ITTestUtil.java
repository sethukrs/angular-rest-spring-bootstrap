package gamesys.util;

import com.jayway.restassured.RestAssured;
import gamesys.config.SpringConfigIT;
import org.apache.commons.io.IOUtils;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.archive.importer.MavenImporter;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Locale;

import static com.google.common.base.Throwables.propagate;


public class ITTestUtil {

    private ITTestUtil() {}

    public static WebArchive createTestArchive() {
        return ShrinkWrap.create(MavenImporter.class)
                .loadPomFromFile("pom.xml", "integration-test")
                .importBuildOutput()
                .as(WebArchive.class)
                .addClasses(SpringConfigIT.class);
    }

    public static void setUpRestAssured(URL baseURL) {
        RestAssured.port = baseURL.getPort();
        RestAssured.baseURI = baseURL.getProtocol() + "://" + baseURL.getHost();
        RestAssured.basePath = baseURL.getPath();
    }

    public static String readResource(String path, Object... placeholders) {
        String unresolvedContent = readResource(path);
        return String.format(Locale.US, unresolvedContent, placeholders);
    }

    public static String readResource(String path) {
        try (InputStream is = ClassLoader.getSystemResourceAsStream(path)) {
            return IOUtils.toString(is);
        } catch (IOException e) {
            throw propagate(e);
        }
    }

}
