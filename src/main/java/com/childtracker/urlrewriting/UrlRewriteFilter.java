/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.childtracker.urlrewriting;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Dinesh Karpe <contact@dineshkarpe.me>
 */
@WebFilter(filterName = "UrlRewriteFilter", urlPatterns = {"/*"})
public class UrlRewriteFilter implements Filter {

    private FilterConfig filterConfig = null;
//    private static final Logger logger = Logger.getLogger(UrlRewriteFilter.class);
    @Inject
    private PageMeta pageMeta;
    ResourceBundle menuProperties = ResourceBundle.getBundle("menus");
    ResourceBundle linkProperties = ResourceBundle.getBundle("links");
    ResourceBundle titleProperties = ResourceBundle.getBundle("page-titles");
    @Inject
    private UrlRouting urlRouting;

    String userselectedfolder;
    private String[] stringParameter;

    public UrlRewriteFilter() {
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {

    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {

    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     * @throws IOException if an input/output error occurs
     * @throws ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        String urlString;

        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        HttpSession session = servletRequest.getSession(true);
        Boolean authorized = (Boolean) session.getAttribute("authorized");
        Boolean admin = (Boolean) session.getAttribute("admin");
        if (authorized == null) {
            authorized = false;
        }
        if (admin == null) {
            admin = false;
        }

        /*
         * Uncomment to implement https redirection
         * Remove the port number before actual deployment
         if (servletRequest.getScheme().equals("http")) {
         String url = "https://" + servletRequest.getServerName()
         + ":8181" + servletRequest.getContextPath() + servletRequest.getServletPath();
         if (servletRequest.getPathInfo() != null) {
         url += servletRequest.getPathInfo();
         }
         servletResponse.sendRedirect(url);
         return;
         }
         */
//        pageMeta.setLocale(servletRequest.getLocale().getLanguage());
        String Path = servletRequest.getParameter("path");
        urlString = servletRequest.getRequestURI();

//        logger.info("Routing URL: " + urlString);
        if (urlString.startsWith("/")) {
            urlString = urlString.substring(1);
        }

        String[] args = urlString.split("/");

        /* Ignore URLs ending in the specified form */
        if (urlString.matches(".*(css|jpg|png|gif|js)")) {
            chain.doFilter(request, response);
            return;
        }

        urlString = "/" + urlString;

        if (urlRouting.containsMapping(urlString)) {
            try {
                pageMeta.setTitle(titleProperties.getString(urlRouting.getLinksKey(urlString)));
            } catch (MissingResourceException ex) {
                //do nothing
            }
            pageMeta.setSelectedMenu(urlRouting.getLinksKey(urlString));

            forward(urlString, request, response);

            return;
        }

        doBeforeProcessing(request, response);
        Throwable problem = null;

        try {
            chain.doFilter(request, response);
        } catch (IOException | ServletException t) {
            problem = t;
        }

        doAfterProcessing(request, response);
        if (problem
                != null) {
            if (problem instanceof ServletException) {
                throw (ServletException) problem;
            }
            if (problem instanceof IOException) {
                throw (IOException) problem;
            }
            sendProcessingError(problem, response);
        }
    }

    private void forward(String urlString, ServletRequest request, ServletResponse response) throws ServletException, IOException {

        HttpServletRequest servletRequest = (HttpServletRequest) request;
        final String linksForwardMapping;
        linksForwardMapping = ".forward";
        switch (urlString) {
            case "/dashboard":
                request.setAttribute("css", "table.css");
                break;
            default:
                request.setAttribute("css", "Default");
                break;
        }
        request.getRequestDispatcher(linkProperties.getString(urlRouting.getLinksKey(urlString) + linksForwardMapping)).forward(request, response);
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    @Override
    public void destroy() {
    }

    /**
     * Init method for this filter
     *
     * @param filterConfig
     */
    @Override
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {

        }
    }

    /**
     * @
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("Bhiwandicity()");
        }
        StringBuilder sb = new StringBuilder("BgiwandicityFIlter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");

                PrintStream ps = new PrintStream(response.getOutputStream());
                try (PrintWriter pw = new PrintWriter(ps)) {
                    pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                    // PENDING! Localize this for next official release
                    pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                    pw.print(stackTrace);
                    pw.print("</pre></body>\n</html>"); //NOI18N
                } //NOI18N

                response.getOutputStream().close();
            } catch (IOException ex) {
//                logger.error(ex, ex);
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                response.getOutputStream().close();
            } catch (IOException ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (IOException ex) {
//            logger.error(ex);
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

    private void ensureLogin(HttpServletResponse response) throws Exception {
        //TODO: Write worflow for appropriate redirects in case secure page is being
        //accessed without login
    }
}
