package com.aclem43.lsma;

import org.springframework.core.io.Resource;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

public class IndexHtmlAwarePathResourceResolver extends PathResourceResolver {

    private final Set<String> folderWithIndexHtml;

    public IndexHtmlAwarePathResourceResolver(Set<String> folderWithIndexHtml) {
        super();
        this.folderWithIndexHtml = new TreeSet<>((o1, o2) -> {
            int ret = o2.length() - o1.length();
            if (ret == 0) {
                return o1.compareTo(o2);
            }
            return ret;
        });
        this.folderWithIndexHtml.addAll(folderWithIndexHtml);
    }

    @Override
    protected Resource getResource(String aResourcePath, Resource location) throws IOException {
        String resourcePath = aResourcePath;

        if (this.folderWithIndexHtml.contains(resourcePath)) {
            return super.getResource(resourcePath + "/index.html", location);
        }
        if (!this.hasExtension(resourcePath)) {
            // resourcePath can be an url like app/subfolder/xx/12 which
            // need to be matched
            // with app/index.html
            for (String canditate : this.folderWithIndexHtml) {
                if (resourcePath.startsWith(canditate)) {
                    return super.getResource(canditate + "/index.html", location);
                }
            }
        }
        return super.getResource(resourcePath, location);
    }

    public boolean hasExtension(String path) {
        int idx = path.lastIndexOf(".");
        if (idx < 0) {
            return false;
        }
        // checking that the ".' is after the last '/'
        int idxS = path.lastIndexOf("/");
        if (idx > idxS) {
            return true;
        }
        return false;
    }

}
