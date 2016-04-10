/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.childtracker.urlrewriting;

//import com.childtracker.config.AppConfigInitBean;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.DependsOn;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.primefaces.json.JSONObject;
//import org.json.JSONObject;

/**
 *
 * @author Dinesh Karpe <contact@dineshkarpe.me>
 */
@Startup
@Singleton
//@DependsOn("AppConfigInitBean")
public class UrlRouting {

    private static final Logger logger = LogManager.getLogger(UrlRouting.class);
    private final Map<String, String> routingMap = new HashMap<>();
    ResourceBundle linksBundle = ResourceBundle.getBundle("links");
//
//    @Inject
//    private AppConfigInitBean appConfigInitBean;
    @PostConstruct
    private void init() {
        
        logger.info("Starting routing parsing");
        
        /**
         * google Map api key load here
         */
        
        
        for (String key : linksBundle.keySet()) {

            /* If current item corresponds to 'item.forward' then skip */
            if (!key.equals("forward") && key.endsWith("forward")) {
                continue;
            }

            /* If 'item' does not have mapping 'item.forward' then skip */
            if (!linksBundle.containsKey(key + ".forward") && !linksBundle.containsKey(key + ".mobile.forward")) {
                continue;
            }

            if (!routingMap.containsKey(linksBundle.getString(key))) {
                routingMap.put(linksBundle.getString(key), key);
            } else {
                routingMap.clear();
                return;
            }
        }
    }

    public String getLinksKey(String prettyUrl) {
        return routingMap.get(prettyUrl);
    }

    public String getPrettyUrl(final String bundleKey) {
        return linksBundle.getString(bundleKey);
    }

    public boolean containsMapping(String prettyUrl) {
        return routingMap.containsKey(prettyUrl);
    }

    public void route404(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(linksBundle.getString("404page.forward")).forward(request, response);
    }
//    public JSONObject getAppConfigJson(){
//        return appConfigInitBean.loadDefaultAppConfigs();
//    }
}
