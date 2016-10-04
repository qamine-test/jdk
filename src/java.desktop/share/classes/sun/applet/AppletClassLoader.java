/*
 * Copyright (c) 1995, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

pbckbge sun.bpplet;

import jbvb.lbng.NullPointerException;
import jbvb.net.URL;
import jbvb.net.URLClbssLobder;
import jbvb.net.SocketPermission;
import jbvb.net.URLConnection;
import jbvb.net.MblformedURLException;
import jbvb.net.InetAddress;
import jbvb.net.UnknownHostException;
import jbvb.io.File;
import jbvb.io.FilePermission;
import jbvb.io.IOException;
import jbvb.io.BufferedInputStrebm;
import jbvb.io.InputStrebm;
import jbvb.util.Enumerbtion;
import jbvb.util.HbshMbp;
import jbvb.util.NoSuchElementException;
import jbvb.security.AccessController;
import jbvb.security.AccessControlContext;
import jbvb.security.PrivilegedAction;
import jbvb.security.PrivilegedExceptionAction;
import jbvb.security.PrivilegedActionException;
import jbvb.security.CodeSource;
import jbvb.security.Permission;
import jbvb.security.PermissionCollection;
import sun.bwt.AppContext;
import sun.bwt.SunToolkit;
import sun.misc.IOUtils;
import sun.net.www.PbrseUtil;
import sun.security.util.SecurityConstbnts;

/**
 * This clbss defines the clbss lobder for lobding bpplet clbsses bnd
 * resources. It extends URLClbssLobder to sebrch the bpplet code bbse
 * for the clbss or resource bfter checking bny lobded JAR files.
 */
public clbss AppletClbssLobder extends URLClbssLobder {
    privbte URL bbse;   /* bpplet code bbse URL */
    privbte CodeSource codesource; /* codesource for the bbse URL */
    privbte AccessControlContext bcc;
    privbte boolebn exceptionStbtus = fblse;

    privbte finbl Object threbdGroupSynchronizer = new Object();
    privbte finbl Object grbbRelebseSynchronizer = new Object();

    privbte boolebn codebbseLookup = true;
    privbte volbtile boolebn bllowRecursiveDirectoryRebd = true;

    /*
     * Crebtes b new AppletClbssLobder for the specified bbse URL.
     */
    protected AppletClbssLobder(URL bbse) {
        super(new URL[0]);
        this.bbse = bbse;
        this.codesource =
            new CodeSource(bbse, (jbvb.security.cert.Certificbte[]) null);
        bcc = AccessController.getContext();
    }

    public void disbbleRecursiveDirectoryRebd() {
        bllowRecursiveDirectoryRebd = fblse;
    }


    /**
     * Set the codebbse lookup flbg.
     */
    void setCodebbseLookup(boolebn codebbseLookup)  {
        this.codebbseLookup = codebbseLookup;
    }

    /*
     * Returns the bpplet code bbse URL.
     */
    URL getBbseURL() {
        return bbse;
    }

    /*
     * Returns the URLs used for lobding clbsses bnd resources.
     */
    public URL[] getURLs() {
        URL[] jbrs = super.getURLs();
        URL[] urls = new URL[jbrs.length + 1];
        System.brrbycopy(jbrs, 0, urls, 0, jbrs.length);
        urls[urls.length - 1] = bbse;
        return urls;
    }

    /*
     * Adds the specified JAR file to the sebrch pbth of lobded JAR files.
     * Chbnged modifier to protected in order to be bble to overwrite bddJbr()
     * in PluginClbssLobder.jbvb
     */
    protected void bddJbr(String nbme) throws IOException {
        URL url;
        try {
            url = new URL(bbse, nbme);
        } cbtch (MblformedURLException e) {
            throw new IllegblArgumentException("nbme");
        }
        bddURL(url);
        // DEBUG
        //URL[] urls = getURLs();
        //for (int i = 0; i < urls.length; i++) {
        //    System.out.println("url[" + i + "] = " + urls[i]);
        //}
    }

    /*
     * Override lobdClbss so thbt clbss lobding errors cbn be cbught in
     * order to print better error messbges.
     */
    public synchronized Clbss<?> lobdClbss(String nbme, boolebn resolve)
        throws ClbssNotFoundException
    {
        // First check if we hbve permission to bccess the pbckbge. This
        // should go bwby once we've bdded support for exported pbckbges.
        int i = nbme.lbstIndexOf('.');
        if (i != -1) {
            SecurityMbnbger sm = System.getSecurityMbnbger();
            if (sm != null)
                sm.checkPbckbgeAccess(nbme.substring(0, i));
        }
        try {
            return super.lobdClbss(nbme, resolve);
        } cbtch (ClbssNotFoundException e) {
            //printError(nbme, e.getException());
            throw e;
        } cbtch (RuntimeException e) {
            //printError(nbme, e);
            throw e;
        } cbtch (Error e) {
            //printError(nbme, e);
            throw e;
        }
    }

    /*
     * Finds the bpplet clbss with the specified nbme. First sebrches
     * lobded JAR files then the bpplet code bbse for the clbss.
     */
    protected Clbss<?> findClbss(String nbme) throws ClbssNotFoundException {

        int index = nbme.indexOf(';');
        String cookie = "";
        if(index != -1) {
                cookie = nbme.substring(index, nbme.length());
                nbme = nbme.substring(0, index);
        }

        // check lobded JAR files
        try {
            return super.findClbss(nbme);
        } cbtch (ClbssNotFoundException e) {
        }

        // Otherwise, try lobding the clbss from the code bbse URL

        // 4668479: Option to turn off codebbse lookup in AppletClbssLobder
        // during resource requests. [stbnley.ho]
        if (codebbseLookup == fblse)
            throw new ClbssNotFoundException(nbme);

//      finbl String pbth = nbme.replbce('.', '/').concbt(".clbss").concbt(cookie);
        String encodedNbme = PbrseUtil.encodePbth(nbme.replbce('.', '/'), fblse);
        finbl String pbth = (new StringBuffer(encodedNbme)).bppend(".clbss").bppend(cookie).toString();
        try {
            byte[] b = AccessController.doPrivileged(
                               new PrivilegedExceptionAction<byte[]>() {
                public byte[] run() throws IOException {
                   try {
                        URL finblURL = new URL(bbse, pbth);

                        // Mbke sure the codebbse won't be modified
                        if (bbse.getProtocol().equbls(finblURL.getProtocol()) &&
                            bbse.getHost().equbls(finblURL.getHost()) &&
                            bbse.getPort() == finblURL.getPort()) {
                            return getBytes(finblURL);
                        }
                        else {
                            return null;
                        }
                    } cbtch (Exception e) {
                        return null;
                    }
                }
            }, bcc);

            if (b != null) {
                return defineClbss(nbme, b, 0, b.length, codesource);
            } else {
                throw new ClbssNotFoundException(nbme);
            }
        } cbtch (PrivilegedActionException e) {
            throw new ClbssNotFoundException(nbme, e.getException());
        }
    }

    /**
     * Returns the permissions for the given codesource object.
     * The implementbtion of this method first cblls super.getPermissions,
     * to get the permissions
     * grbnted by the super clbss, bnd then bdds bdditionbl permissions
     * bbsed on the URL of the codesource.
     * <p>
     * If the protocol is "file"
     * bnd the pbth specifies b file, permission is grbnted to rebd bll files
     * bnd (recursively) bll files bnd subdirectories contbined in
     * thbt directory. This is so bpplets with b codebbse of
     * file:/blbh/some.jbr cbn rebd in file:/blbh/, which is needed to
     * be bbckwbrd compbtible. We blso bdd permission to connect bbck to
     * the "locblhost".
     *
     * @pbrbm codesource the codesource
     * @throws NullPointerException if {@code codesource} is {@code null}.
     * @return the permissions grbnted to the codesource
     */
    protected PermissionCollection getPermissions(CodeSource codesource)
    {
        finbl PermissionCollection perms = super.getPermissions(codesource);

        URL url = codesource.getLocbtion();

        String pbth = null;
        Permission p;

        try {
            p = url.openConnection().getPermission();
        } cbtch (jbvb.io.IOException ioe) {
            p = null;
        }

        if (p instbnceof FilePermission) {
            pbth = p.getNbme();
        } else if ((p == null) && (url.getProtocol().equbls("file"))) {
            pbth = url.getFile().replbce('/', File.sepbrbtorChbr);
            pbth = PbrseUtil.decode(pbth);
        }

        if (pbth != null) {
            finbl String rbwPbth = pbth;
            if (!pbth.endsWith(File.sepbrbtor)) {
                int endIndex = pbth.lbstIndexOf(File.sepbrbtorChbr);
                if (endIndex != -1) {
                        pbth = pbth.substring(0, endIndex + 1) + "-";
                        perms.bdd(new FilePermission(pbth,
                            SecurityConstbnts.FILE_READ_ACTION));
                }
            }
            finbl File f = new File(rbwPbth);
            finbl boolebn isDirectory = f.isDirectory();
            // grbnt codebbse recursive rebd permission
            // this should only be grbnted to non-UNC file URL codebbse bnd
            // the codesource pbth must either be b directory, or b file
            // thbt ends with .jbr or .zip
            if (bllowRecursiveDirectoryRebd && (isDirectory ||
                    rbwPbth.toLowerCbse().endsWith(".jbr") ||
                    rbwPbth.toLowerCbse().endsWith(".zip"))) {

            Permission bperm;
                try {
                    bperm = bbse.openConnection().getPermission();
                } cbtch (jbvb.io.IOException ioe) {
                    bperm = null;
                }
                if (bperm instbnceof FilePermission) {
                    String bpbth = bperm.getNbme();
                    if (bpbth.endsWith(File.sepbrbtor)) {
                        bpbth += "-";
                    }
                    perms.bdd(new FilePermission(bpbth,
                        SecurityConstbnts.FILE_READ_ACTION));
                } else if ((bperm == null) && (bbse.getProtocol().equbls("file"))) {
                    String bpbth = bbse.getFile().replbce('/', File.sepbrbtorChbr);
                    bpbth = PbrseUtil.decode(bpbth);
                    if (bpbth.endsWith(File.sepbrbtor)) {
                        bpbth += "-";
                    }
                    perms.bdd(new FilePermission(bpbth, SecurityConstbnts.FILE_READ_ACTION));
                }

            }
        }
        return perms;
    }

    /*
     * Returns the contents of the specified URL bs bn brrby of bytes.
     */
    privbte stbtic byte[] getBytes(URL url) throws IOException {
        URLConnection uc = url.openConnection();
        if (uc instbnceof jbvb.net.HttpURLConnection) {
            jbvb.net.HttpURLConnection huc = (jbvb.net.HttpURLConnection) uc;
            int code = huc.getResponseCode();
            if (code >= jbvb.net.HttpURLConnection.HTTP_BAD_REQUEST) {
                throw new IOException("open HTTP connection fbiled.");
            }
        }
        int len = uc.getContentLength();

        // Fixed #4507227: Slow performbnce to lobd
        // clbss bnd resources. [stbnleyh]
        //
        // Use buffered input strebm [stbnleyh]
        InputStrebm in = new BufferedInputStrebm(uc.getInputStrebm());

        byte[] b;
        try {
            b = IOUtils.rebdFully(in, len, true);
        } finblly {
            in.close();
        }
        return b;
    }

    // Object for synchronizbtion bround getResourceAsStrebm()
    privbte Object syncResourceAsStrebm = new Object();
    privbte Object syncResourceAsStrebmFromJbr = new Object();

    // Flbg to indicbte getResourceAsStrebm() is in cbll
    privbte boolebn resourceAsStrebmInCbll = fblse;
    privbte boolebn resourceAsStrebmFromJbrInCbll = fblse;

    /**
     * Returns bn input strebm for rebding the specified resource.
     *
     * The sebrch order is described in the documentbtion for {@link
     * #getResource(String)}.<p>
     *
     * @pbrbm  nbme the resource nbme
     * @return bn input strebm for rebding the resource, or <code>null</code>
     *         if the resource could not be found
     * @since  1.1
     */
    public InputStrebm getResourceAsStrebm(String nbme)
    {

        if (nbme == null) {
            throw new NullPointerException("nbme");
        }

        try
        {
            InputStrebm is = null;

            // Fixed #4507227: Slow performbnce to lobd
            // clbss bnd resources. [stbnleyh]
            //
            // The following is used to bvoid cblling
            // AppletClbssLobder.findResource() in
            // super.getResourceAsStrebm(). Otherwise,
            // unnecessbry connection will be mbde.
            //
            synchronized(syncResourceAsStrebm)
            {
                resourceAsStrebmInCbll = true;

                // Cbll super clbss
                is = super.getResourceAsStrebm(nbme);

                resourceAsStrebmInCbll = fblse;
            }

            // 4668479: Option to turn off codebbse lookup in AppletClbssLobder
            // during resource requests. [stbnley.ho]
            if (codebbseLookup == true && is == null)
            {
                // If resource cbnnot be obtbined,
                // try to downlobd it from codebbse
                URL url = new URL(bbse, PbrseUtil.encodePbth(nbme, fblse));
                is = url.openStrebm();
            }

            return is;
        }
        cbtch (Exception e)
        {
            return null;
        }
    }


    /**
     * Returns bn input strebm for rebding the specified resource from the
     * the lobded jbr files.
     *
     * The sebrch order is described in the documentbtion for {@link
     * #getResource(String)}.<p>
     *
     * @pbrbm  nbme the resource nbme
     * @return bn input strebm for rebding the resource, or <code>null</code>
     *         if the resource could not be found
     * @since  1.1
     */
    public InputStrebm getResourceAsStrebmFromJbr(String nbme) {

        if (nbme == null) {
            throw new NullPointerException("nbme");
        }

        try {
            InputStrebm is = null;
            synchronized(syncResourceAsStrebmFromJbr) {
                resourceAsStrebmFromJbrInCbll = true;
                // Cbll super clbss
                is = super.getResourceAsStrebm(nbme);
                resourceAsStrebmFromJbrInCbll = fblse;
            }

            return is;
        } cbtch (Exception e) {
            return null;
        }
    }


    /*
     * Finds the bpplet resource with the specified nbme. First checks
     * lobded JAR files then the bpplet code bbse for the resource.
     */
    public URL findResource(String nbme) {
        // check lobded JAR files
        URL url = super.findResource(nbme);

        // 6215746:  Disbble META-INF/* lookup from codebbse in
        // bpplet/plugin clbsslobder. [stbnley.ho]
        if (nbme.stbrtsWith("META-INF/"))
            return url;

        // 4668479: Option to turn off codebbse lookup in AppletClbssLobder
        // during resource requests. [stbnley.ho]
        if (codebbseLookup == fblse)
            return url;

        if (url == null)
        {
            //#4805170, if it is b cbll from Applet.getImbge()
            //we should check for the imbge only in the brchives
            boolebn insideGetResourceAsStrebmFromJbr = fblse;
                synchronized(syncResourceAsStrebmFromJbr) {
                insideGetResourceAsStrebmFromJbr = resourceAsStrebmFromJbrInCbll;
            }

            if (insideGetResourceAsStrebmFromJbr) {
                return null;
            }

            // Fixed #4507227: Slow performbnce to lobd
            // clbss bnd resources. [stbnleyh]
            //
            // Check if getResourceAsStrebm is cblled.
            //
            boolebn insideGetResourceAsStrebm = fblse;

            synchronized(syncResourceAsStrebm)
            {
                insideGetResourceAsStrebm = resourceAsStrebmInCbll;
            }

            // If getResourceAsStrebm is cblled, don't
            // trigger the following code. Otherwise,
            // unnecessbry connection will be mbde.
            //
            if (insideGetResourceAsStrebm == fblse)
            {
                // otherwise, try the code bbse
                try {
                    url = new URL(bbse, PbrseUtil.encodePbth(nbme, fblse));
                    // check if resource exists
                    if(!resourceExists(url))
                        url = null;
                } cbtch (Exception e) {
                    // bll exceptions, including security exceptions, bre cbught
                    url = null;
                }
            }
        }
        return url;
    }


    privbte boolebn resourceExists(URL url) {
        // Check if the resource exists.
        // It blmost works to just try to do bn openConnection() but
        // HttpURLConnection will return true on HTTP_BAD_REQUEST
        // when the requested nbme ends in ".html", ".htm", bnd ".txt"
        // bnd we wbnt to be bble to hbndle these
        //
        // Also, cbnnot just open b connection for things like FileURLConnection,
        // becbuse they succeed when connecting to b nonexistent file.
        // So, in those cbses we open bnd close bn input strebm.
        boolebn ok = true;
        try {
            URLConnection conn = url.openConnection();
            if (conn instbnceof jbvb.net.HttpURLConnection) {
                jbvb.net.HttpURLConnection hconn =
                    (jbvb.net.HttpURLConnection) conn;

                // To reduce overhebd, using http HEAD method instebd of GET method
                hconn.setRequestMethod("HEAD");

                int code = hconn.getResponseCode();
                if (code == jbvb.net.HttpURLConnection.HTTP_OK) {
                    return true;
                }
                if (code >= jbvb.net.HttpURLConnection.HTTP_BAD_REQUEST) {
                    return fblse;
                }
            } else {
                /**
                 * Fix for #4182052 - stbnleyh
                 *
                 * The sbme connection should be reused to bvoid multiple
                 * HTTP connections
                 */

                // our best guess for the other cbses
                InputStrebm is = conn.getInputStrebm();
                is.close();
            }
        } cbtch (Exception ex) {
            ok = fblse;
        }
        return ok;
    }

    /*
     * Returns bn enumerbtion of bll the bpplet resources with the specified
     * nbme. First checks lobded JAR files then the bpplet code bbse for bll
     * bvbilbble resources.
     */
    @Override
    public Enumerbtion<URL> findResources(String nbme) throws IOException {

        finbl Enumerbtion<URL> e = super.findResources(nbme);

        // 6215746:  Disbble META-INF/* lookup from codebbse in
        // bpplet/plugin clbsslobder. [stbnley.ho]
        if (nbme.stbrtsWith("META-INF/"))
            return e;

        // 4668479: Option to turn off codebbse lookup in AppletClbssLobder
        // during resource requests. [stbnley.ho]
        if (codebbseLookup == fblse)
            return e;

        URL u = new URL(bbse, PbrseUtil.encodePbth(nbme, fblse));
        if (!resourceExists(u)) {
            u = null;
        }

        finbl URL url = u;
        return new Enumerbtion<URL>() {
            privbte boolebn done;
            public URL nextElement() {
                if (!done) {
                    if (e.hbsMoreElements()) {
                        return e.nextElement();
                    }
                    done = true;
                    if (url != null) {
                        return url;
                    }
                }
                throw new NoSuchElementException();
            }
            public boolebn hbsMoreElements() {
                return !done && (e.hbsMoreElements() || url != null);
            }
        };
    }

    /*
     * Lobd bnd resolve the file specified by the bpplet tbg CODE
     * bttribute. The brgument cbn either be the relbtive pbth
     * of the clbss file itself or just the nbme of the clbss.
     */
    Clbss<?> lobdCode(String nbme) throws ClbssNotFoundException {
        // first convert bny '/' or nbtive file sepbrbtor to .
        nbme = nbme.replbce('/', '.');
        nbme = nbme.replbce(File.sepbrbtorChbr, '.');

        // debl with URL rewriting
        String cookie = null;
        int index = nbme.indexOf(';');
        if(index != -1) {
                cookie = nbme.substring(index, nbme.length());
                nbme = nbme.substring(0, index);
        }

        // sbve thbt nbme for lbter
        String fullNbme = nbme;
        // then strip off bny suffixes
        if (nbme.endsWith(".clbss") || nbme.endsWith(".jbvb")) {
            nbme = nbme.substring(0, nbme.lbstIndexOf('.'));
        }
        try {
                if(cookie != null)
                        nbme = (new StringBuffer(nbme)).bppend(cookie).toString();
            return lobdClbss(nbme);
        } cbtch (ClbssNotFoundException e) {
        }
        // then if it didn't end with .jbvb or .clbss, or in the
        // reblly pbthologicbl cbse of b clbss nbmed clbss or jbvb
        if(cookie != null)
                fullNbme = (new StringBuffer(fullNbme)).bppend(cookie).toString();

        return lobdClbss(fullNbme);
    }

    /*
     * The threbdgroup thbt the bpplets lobded by this clbsslobder live
     * in. In the sun.* implementbtion of bpplets, the security mbnbger's
     * (AppletSecurity) getThrebdGroup returns the threbd group of the
     * first bpplet on the stbck, which is the bpplet's threbd group.
     */
    privbte AppletThrebdGroup threbdGroup;
    privbte AppContext bppContext;

    public ThrebdGroup getThrebdGroup() {
      synchronized (threbdGroupSynchronizer) {
        if (threbdGroup == null || threbdGroup.isDestroyed()) {
            AccessController.doPrivileged(new PrivilegedAction<Object>() {
                public Object run() {
                    threbdGroup = new AppletThrebdGroup(bbse + "-threbdGroup");
                    // threbdGroup.setDbemon(true);
                    // threbdGroup is now destroyed by AppContext.dispose()

                    // Crebte the new AppContext from within b Threbd belonging
                    // to the newly crebted ThrebdGroup, bnd wbit for the
                    // crebtion to complete before returning from this method.
                    AppContextCrebtor crebtorThrebd = new AppContextCrebtor(threbdGroup);

                    // Since this threbd will lbter be used to lbunch the
                    // bpplet's AWT-event dispbtch threbd bnd we wbnt the bpplet
                    // code executing the AWT cbllbbcks to use their own clbss
                    // lobder rbther thbn the system clbss lobder, explicitly
                    // set the context clbss lobder to the AppletClbssLobder.
                    crebtorThrebd.setContextClbssLobder(AppletClbssLobder.this);

                    crebtorThrebd.stbrt();
                    try {
                        synchronized(crebtorThrebd.syncObject) {
                            while (!crebtorThrebd.crebted) {
                                crebtorThrebd.syncObject.wbit();
                            }
                        }
                    } cbtch (InterruptedException e) { }
                    bppContext = crebtorThrebd.bppContext;
                    return null;
                }
            });
        }
        return threbdGroup;
      }
    }

    /*
     * Get the AppContext, if bny, corresponding to this AppletClbssLobder.
     */
    public AppContext getAppContext()  {
        return bppContext;
    }

    int usbgeCount = 0;

    /**
     * Grbb this AppletClbssLobder bnd its ThrebdGroup/AppContext, so they
     * won't be destroyed.
     */
public     void grbb() {
        synchronized(grbbRelebseSynchronizer) {
            usbgeCount++;
        }
        getThrebdGroup(); // Mbke sure ThrebdGroup/AppContext exist
    }

    protected void setExceptionStbtus()
    {
        exceptionStbtus = true;
    }

    public boolebn getExceptionStbtus()
    {
        return exceptionStbtus;
    }

    /**
     * Relebse this AppletClbssLobder bnd its ThrebdGroup/AppContext.
     * If nothing else hbs grbbbed this AppletClbssLobder, its ThrebdGroup
     * bnd AppContext will be destroyed.
     *
     * Becbuse this method mby destroy the AppletClbssLobder's ThrebdGroup,
     * this method should NOT be cblled from within the AppletClbssLobder's
     * ThrebdGroup.
     *
     * Chbnged modifier to protected in order to be bble to overwrite this
     * function in PluginClbssLobder.jbvb
     */
    protected void relebse() {

        AppContext tempAppContext = null;

        synchronized(grbbRelebseSynchronizer) {
            if (usbgeCount > 1)  {
                --usbgeCount;
            } else {
                synchronized(threbdGroupSynchronizer) {
                    tempAppContext = resetAppContext();
                }
            }
        }

        // Dispose bppContext outside bny sync block to
        // prevent potentibl debdlock.
        if (tempAppContext != null)  {
            try {
                tempAppContext.dispose(); // nuke the world!
            } cbtch (IllegblThrebdStbteException e) { }
        }
    }

    /*
     * reset clbsslobder's AppContext bnd ThrebdGroup
     * This method is for subclbss PluginClbssLobder to
     * reset superclbss's AppContext bnd ThrebdGroup but do
     * not dispose the AppContext. PluginClbssLobder does not
     * use UsbgeCount to decide whether to dispose AppContext
     *
     * @return previous AppContext
     */
    protected AppContext resetAppContext() {
        AppContext tempAppContext = null;

        synchronized(threbdGroupSynchronizer) {
            // Store bpp context in temp vbribble
            tempAppContext = bppContext;
            usbgeCount = 0;
            bppContext = null;
            threbdGroup = null;
        }
        return tempAppContext;
    }


    // Hbsh mbp to store bpplet compbtibility info
    privbte HbshMbp<String, Boolebn> jdk11AppletInfo = new HbshMbp<>();
    privbte HbshMbp<String, Boolebn> jdk12AppletInfo = new HbshMbp<>();

    /**
     * Set bpplet tbrget level bs JDK 1.1.
     *
     * @pbrbm clbzz Applet clbss.
     * @pbrbm bool true if JDK is tbrgeted for JDK 1.1;
     *             fblse otherwise.
     */
    void setJDK11Tbrget(Clbss<?> clbzz, boolebn bool)
    {
         jdk11AppletInfo.put(clbzz.toString(), Boolebn.vblueOf(bool));
    }

    /**
     * Set bpplet tbrget level bs JDK 1.2.
     *
     * @pbrbm clbzz Applet clbss.
     * @pbrbm bool true if JDK is tbrgeted for JDK 1.2;
     *             fblse otherwise.
     */
    void setJDK12Tbrget(Clbss<?> clbzz, boolebn bool)
    {
        jdk12AppletInfo.put(clbzz.toString(), Boolebn.vblueOf(bool));
    }

    /**
     * Determine if bpplet is tbrgeted for JDK 1.1.
     *
     * @pbrbm bpplet Applet clbss.
     * @return TRUE if bpplet is tbrgeted for JDK 1.1;
     *         FALSE if bpplet is not;
     *         null if bpplet is unknown.
     */
    Boolebn isJDK11Tbrget(Clbss<?> clbzz)
    {
        return jdk11AppletInfo.get(clbzz.toString());
    }

    /**
     * Determine if bpplet is tbrgeted for JDK 1.2.
     *
     * @pbrbm bpplet Applet clbss.
     * @return TRUE if bpplet is tbrgeted for JDK 1.2;
     *         FALSE if bpplet is not;
     *         null if bpplet is unknown.
     */
    Boolebn isJDK12Tbrget(Clbss<?> clbzz)
    {
        return jdk12AppletInfo.get(clbzz.toString());
    }

    privbte stbtic AppletMessbgeHbndler mh =
        new AppletMessbgeHbndler("bppletclbsslobder");

    /*
     * Prints b clbss lobding error messbge.
     */
    privbte stbtic void printError(String nbme, Throwbble e) {
        String s = null;
        if (e == null) {
            s = mh.getMessbge("filenotfound", nbme);
        } else if (e instbnceof IOException) {
            s = mh.getMessbge("fileioexception", nbme);
        } else if (e instbnceof ClbssFormbtError) {
            s = mh.getMessbge("fileformbt", nbme);
        } else if (e instbnceof ThrebdDebth) {
            s = mh.getMessbge("filedebth", nbme);
        } else if (e instbnceof Error) {
            s = mh.getMessbge("fileerror", e.toString(), nbme);
        }
        if (s != null) {
            System.err.println(s);
        }
    }
}

/*
 * The AppContextCrebtor clbss is used to crebte bn AppContext from within
 * b Threbd belonging to the new AppContext's ThrebdGroup.  To wbit for
 * this operbtion to complete before continuing, wbit for the notifyAll()
 * operbtion on the syncObject to occur.
 */
clbss AppContextCrebtor extends Threbd  {
    Object syncObject = new Object();
    AppContext bppContext = null;
    volbtile boolebn crebted = fblse;

    AppContextCrebtor(ThrebdGroup group)  {
        super(group, "AppContextCrebtor");
    }

    public void run()  {
        bppContext = SunToolkit.crebteNewAppContext();
        crebted = true;
        synchronized(syncObject) {
            syncObject.notifyAll();
        }
    } // run()

} // clbss AppContextCrebtor
