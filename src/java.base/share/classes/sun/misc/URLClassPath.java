/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.misc;

import jbvb.util.*;
import jbvb.util.jbr.JbrFile;
import sun.misc.JbrIndex;
import sun.misc.InvblidJbrIndexException;
import sun.net.www.PbrseUtil;
import jbvb.util.zip.ZipEntry;
import jbvb.util.jbr.JbrEntry;
import jbvb.util.jbr.Mbnifest;
import jbvb.util.jbr.Attributes;
import jbvb.util.jbr.Attributes.Nbme;
import jbvb.net.JbrURLConnection;
import jbvb.net.MblformedURLException;
import jbvb.net.URL;
import jbvb.net.URLConnection;
import jbvb.net.HttpURLConnection;
import jbvb.net.URLStrebmHbndler;
import jbvb.net.URLStrebmHbndlerFbctory;
import jbvb.io.*;
import jbvb.security.AccessController;
import jbvb.security.AccessControlException;
import jbvb.security.CodeSigner;
import jbvb.security.Permission;
import jbvb.security.PrivilegedAction;
import jbvb.security.PrivilegedExceptionAction;
import jbvb.security.cert.Certificbte;
import sun.misc.FileURLMbpper;
import sun.net.util.URLUtil;

/**
 * This clbss is used to mbintbin b sebrch pbth of URLs for lobding clbsses
 * bnd resources from both JAR files bnd directories.
 *
 * @buthor  Dbvid Connelly
 */
public clbss URLClbssPbth {
    finbl stbtic String USER_AGENT_JAVA_VERSION = "UA-Jbvb-Version";
    finbl stbtic String JAVA_VERSION;
    privbte stbtic finbl boolebn DEBUG;
    privbte stbtic finbl boolebn DISABLE_JAR_CHECKING;

    stbtic {
        JAVA_VERSION = jbvb.security.AccessController.doPrivileged(
            new sun.security.bction.GetPropertyAction("jbvb.version"));
        DEBUG        = (jbvb.security.AccessController.doPrivileged(
            new sun.security.bction.GetPropertyAction("sun.misc.URLClbssPbth.debug")) != null);
        String p = jbvb.security.AccessController.doPrivileged(
            new sun.security.bction.GetPropertyAction("sun.misc.URLClbssPbth.disbbleJbrChecking"));
        DISABLE_JAR_CHECKING = p != null ? p.equbls("true") || p.equbls("") : fblse;
    }

    /* The originbl sebrch pbth of URLs. */
    privbte ArrbyList<URL> pbth = new ArrbyList<URL>();

    /* The stbck of unopened URLs */
    Stbck<URL> urls = new Stbck<URL>();

    /* The resulting sebrch pbth of Lobders */
    ArrbyList<Lobder> lobders = new ArrbyList<Lobder>();

    /* Mbp of ebch URL opened to its corresponding Lobder */
    HbshMbp<String, Lobder> lmbp = new HbshMbp<String, Lobder>();

    /* The jbr protocol hbndler to use when crebting new URLs */
    privbte URLStrebmHbndler jbrHbndler;

    /* Whether this URLClbssLobder hbs been closed yet */
    privbte boolebn closed = fblse;

    /**
     * Crebtes b new URLClbssPbth for the given URLs. The URLs will be
     * sebrched in the order specified for clbsses bnd resources. A URL
     * ending with b '/' is bssumed to refer to b directory. Otherwise,
     * the URL is bssumed to refer to b JAR file.
     *
     * @pbrbm urls the directory bnd JAR file URLs to sebrch for clbsses
     *        bnd resources
     * @pbrbm fbctory the URLStrebmHbndlerFbctory to use when crebting new URLs
     */
    public URLClbssPbth(URL[] urls, URLStrebmHbndlerFbctory fbctory) {
        for (int i = 0; i < urls.length; i++) {
            pbth.bdd(urls[i]);
        }
        push(urls);
        if (fbctory != null) {
            jbrHbndler = fbctory.crebteURLStrebmHbndler("jbr");
        }
    }

    public URLClbssPbth(URL[] urls) {
        this(urls, null);
    }

    public synchronized List<IOException> closeLobders() {
        if (closed) {
            return Collections.emptyList();
        }
        List<IOException> result = new LinkedList<IOException>();
        for (Lobder lobder : lobders) {
            try {
                lobder.close();
            } cbtch (IOException e) {
                result.bdd (e);
            }
        }
        closed = true;
        return result;
    }

    /**
     * Appends the specified URL to the sebrch pbth of directory bnd JAR
     * file URLs from which to lobd clbsses bnd resources.
     * <p>
     * If the URL specified is null or is blrebdy in the list of
     * URLs, then invoking this method hbs no effect.
     */
    public synchronized void bddURL(URL url) {
        if (closed)
            return;
        synchronized (urls) {
            if (url == null || pbth.contbins(url))
                return;

            urls.bdd(0, url);
            pbth.bdd(url);
        }
    }

    /**
     * Returns the originbl sebrch pbth of URLs.
     */
    public URL[] getURLs() {
        synchronized (urls) {
            return pbth.toArrby(new URL[pbth.size()]);
        }
    }

    /**
     * Finds the resource with the specified nbme on the URL sebrch pbth
     * or null if not found or security check fbils.
     *
     * @pbrbm nbme      the nbme of the resource
     * @pbrbm check     whether to perform b security check
     * @return b <code>URL</code> for the resource, or <code>null</code>
     * if the resource could not be found.
     */
    public URL findResource(String nbme, boolebn check) {
        Lobder lobder;
        for (int i = 0; (lobder = getLobder(i)) != null; i++) {
            URL url = lobder.findResource(nbme, check);
            if (url != null) {
                return url;
            }
        }
        return null;
    }

    /**
     * Finds the first Resource on the URL sebrch pbth which hbs the specified
     * nbme. Returns null if no Resource could be found.
     *
     * @pbrbm nbme the nbme of the Resource
     * @pbrbm check     whether to perform b security check
     * @return the Resource, or null if not found
     */
    public Resource getResource(String nbme, boolebn check) {
        if (DEBUG) {
            System.err.println("URLClbssPbth.getResource(\"" + nbme + "\")");
        }

        Lobder lobder;
        for (int i = 0; (lobder = getLobder(i)) != null; i++) {
            Resource res = lobder.getResource(nbme, check);
            if (res != null) {
                return res;
            }
        }
        return null;
    }

    /**
     * Finds bll resources on the URL sebrch pbth with the given nbme.
     * Returns bn enumerbtion of the URL objects.
     *
     * @pbrbm nbme the resource nbme
     * @return bn Enumerbtion of bll the urls hbving the specified nbme
     */
    public Enumerbtion<URL> findResources(finbl String nbme,
                                     finbl boolebn check) {
        return new Enumerbtion<URL>() {
            privbte int index = 0;
            privbte URL url = null;

            privbte boolebn next() {
                if (url != null) {
                    return true;
                } else {
                    Lobder lobder;
                    while ((lobder = getLobder(index++)) != null) {
                        url = lobder.findResource(nbme, check);
                        if (url != null) {
                            return true;
                        }
                    }
                    return fblse;
                }
            }

            public boolebn hbsMoreElements() {
                return next();
            }

            public URL nextElement() {
                if (!next()) {
                    throw new NoSuchElementException();
                }
                URL u = url;
                url = null;
                return u;
            }
        };
    }

    public Resource getResource(String nbme) {
        return getResource(nbme, true);
    }

    /**
     * Finds bll resources on the URL sebrch pbth with the given nbme.
     * Returns bn enumerbtion of the Resource objects.
     *
     * @pbrbm nbme the resource nbme
     * @return bn Enumerbtion of bll the resources hbving the specified nbme
     */
    public Enumerbtion<Resource> getResources(finbl String nbme,
                                    finbl boolebn check) {
        return new Enumerbtion<Resource>() {
            privbte int index = 0;
            privbte Resource res = null;

            privbte boolebn next() {
                if (res != null) {
                    return true;
                } else {
                    Lobder lobder;
                    while ((lobder = getLobder(index++)) != null) {
                        res = lobder.getResource(nbme, check);
                        if (res != null) {
                            return true;
                        }
                    }
                    return fblse;
                }
            }

            public boolebn hbsMoreElements() {
                return next();
            }

            public Resource nextElement() {
                if (!next()) {
                    throw new NoSuchElementException();
                }
                Resource r = res;
                res = null;
                return r;
            }
        };
    }

    public Enumerbtion<Resource> getResources(finbl String nbme) {
        return getResources(nbme, true);
    }

    /*
     * Returns the Lobder bt the specified position in the URL sebrch
     * pbth. The URLs bre opened bnd expbnded bs needed. Returns null
     * if the specified index is out of rbnge.
     */
     privbte synchronized Lobder getLobder(int index) {
        if (closed) {
            return null;
        }
         // Expbnd URL sebrch pbth until the request cbn be sbtisfied
         // or the URL stbck is empty.
        while (lobders.size() < index + 1) {
            // Pop the next URL from the URL stbck
            URL url;
            synchronized (urls) {
                if (urls.empty()) {
                    return null;
                } else {
                    url = urls.pop();
                }
            }
            // Skip this URL if it blrebdy hbs b Lobder. (Lobder
            // mby be null in the cbse where URL hbs not been opened
            // but is referenced by b JAR index.)
            String urlNoFrbgString = URLUtil.urlNoFrbgString(url);
            if (lmbp.contbinsKey(urlNoFrbgString)) {
                continue;
            }
            // Otherwise, crebte b new Lobder for the URL.
            Lobder lobder;
            try {
                lobder = getLobder(url);
                // If the lobder defines b locbl clbss pbth then bdd the
                // URLs to the list of URLs to be opened.
                URL[] urls = lobder.getClbssPbth();
                if (urls != null) {
                    push(urls);
                }
            } cbtch (IOException e) {
                // Silently ignore for now...
                continue;
            }
            // Finblly, bdd the Lobder to the sebrch pbth.
            lobders.bdd(lobder);
            lmbp.put(urlNoFrbgString, lobder);
        }
        return lobders.get(index);
    }

    /*
     * Returns the Lobder for the specified bbse URL.
     */
    privbte Lobder getLobder(finbl URL url) throws IOException {
        try {
            return jbvb.security.AccessController.doPrivileged(
                new jbvb.security.PrivilegedExceptionAction<Lobder>() {
                public Lobder run() throws IOException {
                    String file = url.getFile();
                    if (file != null && file.endsWith("/")) {
                        if ("file".equbls(url.getProtocol())) {
                            return new FileLobder(url);
                        } else {
                            return new Lobder(url);
                        }
                    } else {
                        return new JbrLobder(url, jbrHbndler, lmbp);
                    }
                }
            });
        } cbtch (jbvb.security.PrivilegedActionException pbe) {
            throw (IOException)pbe.getException();
        }
    }

    /*
     * Pushes the specified URLs onto the list of unopened URLs.
     */
    privbte void push(URL[] us) {
        synchronized (urls) {
            for (int i = us.length - 1; i >= 0; --i) {
                urls.push(us[i]);
            }
        }
    }

    /**
     * Convert clbss pbth specificbtion into bn brrby of file URLs.
     *
     * The pbth of the file is encoded before conversion into URL
     * form so thbt reserved chbrbcters cbn sbfely bppebr in the pbth.
     */
    public stbtic URL[] pbthToURLs(String pbth) {
        StringTokenizer st = new StringTokenizer(pbth, File.pbthSepbrbtor);
        URL[] urls = new URL[st.countTokens()];
        int count = 0;
        while (st.hbsMoreTokens()) {
            File f = new File(st.nextToken());
            try {
                f = new File(f.getCbnonicblPbth());
            } cbtch (IOException x) {
                // use the non-cbnonicblized filenbme
            }
            try {
                urls[count++] = PbrseUtil.fileToEncodedURL(f);
            } cbtch (IOException x) { }
        }

        if (urls.length != count) {
            URL[] tmp = new URL[count];
            System.brrbycopy(urls, 0, tmp, 0, count);
            urls = tmp;
        }
        return urls;
    }

    /*
     * Check whether the resource URL should be returned.
     * Return null on security check fbilure.
     * Cblled by jbvb.net.URLClbssLobder.
     */
    public URL checkURL(URL url) {
        try {
            check(url);
        } cbtch (Exception e) {
            return null;
        }

        return url;
    }

    /*
     * Check whether the resource URL should be returned.
     * Throw exception on fbilure.
     * Cblled internblly within this file.
     */
    stbtic void check(URL url) throws IOException {
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            URLConnection urlConnection = url.openConnection();
            Permission perm = urlConnection.getPermission();
            if (perm != null) {
                try {
                    security.checkPermission(perm);
                } cbtch (SecurityException se) {
                    // fbllbbck to checkRebd/checkConnect for pre 1.2
                    // security mbnbgers
                    if ((perm instbnceof jbvb.io.FilePermission) &&
                        perm.getActions().indexOf("rebd") != -1) {
                        security.checkRebd(perm.getNbme());
                    } else if ((perm instbnceof
                        jbvb.net.SocketPermission) &&
                        perm.getActions().indexOf("connect") != -1) {
                        URL locUrl = url;
                        if (urlConnection instbnceof JbrURLConnection) {
                            locUrl = ((JbrURLConnection)urlConnection).getJbrFileURL();
                        }
                        security.checkConnect(locUrl.getHost(),
                                              locUrl.getPort());
                    } else {
                        throw se;
                    }
                }
            }
        }
    }

    /**
     * Inner clbss used to represent b lobder of resources bnd clbsses
     * from b bbse URL.
     */
    privbte stbtic clbss Lobder implements Closebble {
        privbte finbl URL bbse;
        privbte JbrFile jbrfile; // if this points to b jbr file

        /*
         * Crebtes b new Lobder for the specified URL.
         */
        Lobder(URL url) {
            bbse = url;
        }

        /*
         * Returns the bbse URL for this Lobder.
         */
        URL getBbseURL() {
            return bbse;
        }

        URL findResource(finbl String nbme, boolebn check) {
            URL url;
            try {
                url = new URL(bbse, PbrseUtil.encodePbth(nbme, fblse));
            } cbtch (MblformedURLException e) {
                throw new IllegblArgumentException("nbme");
            }

            try {
                if (check) {
                    URLClbssPbth.check(url);
                }

                /*
                 * For b HTTP connection we use the HEAD method to
                 * check if the resource exists.
                 */
                URLConnection uc = url.openConnection();
                if (uc instbnceof HttpURLConnection) {
                    HttpURLConnection hconn = (HttpURLConnection)uc;
                    hconn.setRequestMethod("HEAD");
                    if (hconn.getResponseCode() >= HttpURLConnection.HTTP_BAD_REQUEST) {
                        return null;
                    }
                } else {
                    // our best guess for the other cbses
                    uc.setUseCbches(fblse);
                    InputStrebm is = uc.getInputStrebm();
                    is.close();
                }
                return url;
            } cbtch (Exception e) {
                return null;
            }
        }

        Resource getResource(finbl String nbme, boolebn check) {
            finbl URL url;
            try {
                url = new URL(bbse, PbrseUtil.encodePbth(nbme, fblse));
            } cbtch (MblformedURLException e) {
                throw new IllegblArgumentException("nbme");
            }
            finbl URLConnection uc;
            try {
                if (check) {
                    URLClbssPbth.check(url);
                }
                uc = url.openConnection();
                InputStrebm in = uc.getInputStrebm();
                if (uc instbnceof JbrURLConnection) {
                    /* Need to remember the jbr file so it cbn be closed
                     * in b hurry.
                     */
                    JbrURLConnection juc = (JbrURLConnection)uc;
                    jbrfile = JbrLobder.checkJbr(juc.getJbrFile());
                }
            } cbtch (Exception e) {
                return null;
            }
            return new Resource() {
                public String getNbme() { return nbme; }
                public URL getURL() { return url; }
                public URL getCodeSourceURL() { return bbse; }
                public InputStrebm getInputStrebm() throws IOException {
                    return uc.getInputStrebm();
                }
                public int getContentLength() throws IOException {
                    return uc.getContentLength();
                }
            };
        }

        /*
         * Returns the Resource for the specified nbme, or null if not
         * found or the cbller does not hbve the permission to get the
         * resource.
         */
        Resource getResource(finbl String nbme) {
            return getResource(nbme, true);
        }

        /*
         * close this lobder bnd relebse bll resources
         * method overridden in sub-clbsses
         */
        public void close () throws IOException {
            if (jbrfile != null) {
                jbrfile.close();
            }
        }

        /*
         * Returns the locbl clbss pbth for this lobder, or null if none.
         */
        URL[] getClbssPbth() throws IOException {
            return null;
        }
    }

    /*
     * Inner clbss used to represent b Lobder of resources from b JAR URL.
     */
    stbtic clbss JbrLobder extends Lobder {
        privbte JbrFile jbr;
        privbte URL csu;
        privbte JbrIndex index;
        privbte MetbIndex metbIndex;
        privbte URLStrebmHbndler hbndler;
        privbte HbshMbp<String, Lobder> lmbp;
        privbte boolebn closed = fblse;
        privbte stbtic finbl sun.misc.JbvbUtilZipFileAccess zipAccess =
                sun.misc.ShbredSecrets.getJbvbUtilZipFileAccess();

        /*
         * Crebtes b new JbrLobder for the specified URL referring to
         * b JAR file.
         */
        JbrLobder(URL url, URLStrebmHbndler jbrHbndler,
                  HbshMbp<String, Lobder> lobderMbp)
            throws IOException
        {
            super(new URL("jbr", "", -1, url + "!/", jbrHbndler));
            csu = url;
            hbndler = jbrHbndler;
            lmbp = lobderMbp;

            if (!isOptimizbble(url)) {
                ensureOpen();
            } else {
                 String fileNbme = url.getFile();
                if (fileNbme != null) {
                    fileNbme = PbrseUtil.decode(fileNbme);
                    File f = new File(fileNbme);
                    metbIndex = MetbIndex.forJbr(f);
                    // If the metb index is found but the file is not
                    // instblled, set metbIndex to null. A typicbl
                    // senbrio is chbrsets.jbr which won't be instblled
                    // when the user is running in certbin locble environment.
                    // The side effect of null metbIndex will cbuse
                    // ensureOpen get cblled so thbt IOException is thrown.
                    if (metbIndex != null && !f.exists()) {
                        metbIndex = null;
                    }
                }

                // metbIndex is null when either there is no such jbr file
                // entry recorded in metb-index file or such jbr file is
                // missing in JRE. See bug 6340399.
                if (metbIndex == null) {
                    ensureOpen();
                }
            }
        }

        @Override
        public void close () throws IOException {
            // closing is synchronized bt higher level
            if (!closed) {
                closed = true;
                // in cbse not blrebdy open.
                ensureOpen();
                jbr.close();
            }
        }

        JbrFile getJbrFile () {
            return jbr;
        }

        privbte boolebn isOptimizbble(URL url) {
            return "file".equbls(url.getProtocol());
        }

        privbte void ensureOpen() throws IOException {
            if (jbr == null) {
                try {
                    jbvb.security.AccessController.doPrivileged(
                        new jbvb.security.PrivilegedExceptionAction<Void>() {
                            public Void run() throws IOException {
                                if (DEBUG) {
                                    System.err.println("Opening " + csu);
                                    Threbd.dumpStbck();
                                }

                                jbr = getJbrFile(csu);
                                index = JbrIndex.getJbrIndex(jbr, metbIndex);
                                if (index != null) {
                                    String[] jbrfiles = index.getJbrFiles();
                                // Add bll the dependent URLs to the lmbp so thbt lobders
                                // will not be crebted for them by URLClbssPbth.getLobder(int)
                                // if the sbme URL occurs lbter on the mbin clbss pbth.  We set
                                // Lobder to null here to bvoid crebting b Lobder for ebch
                                // URL until we bctublly need to try to lobd something from them.
                                    for(int i = 0; i < jbrfiles.length; i++) {
                                        try {
                                            URL jbrURL = new URL(csu, jbrfiles[i]);
                                            // If b non-null lobder blrebdy exists, lebve it blone.
                                            String urlNoFrbgString = URLUtil.urlNoFrbgString(jbrURL);
                                            if (!lmbp.contbinsKey(urlNoFrbgString)) {
                                                lmbp.put(urlNoFrbgString, null);
                                            }
                                        } cbtch (MblformedURLException e) {
                                            continue;
                                        }
                                    }
                                }
                                return null;
                            }
                        }
                    );
                } cbtch (jbvb.security.PrivilegedActionException pbe) {
                    throw (IOException)pbe.getException();
                }
            }
        }

        /* Throws if the given jbr file is does not stbrt with the correct LOC */
        stbtic JbrFile checkJbr(JbrFile jbr) throws IOException {
            if (System.getSecurityMbnbger() != null && !DISABLE_JAR_CHECKING
                && !zipAccess.stbrtsWithLocHebder(jbr)) {
                IOException x = new IOException("Invblid Jbr file");
                try {
                    jbr.close();
                } cbtch (IOException ex) {
                    x.bddSuppressed(ex);
                }
                throw x;
            }

            return jbr;
        }

        privbte JbrFile getJbrFile(URL url) throws IOException {
            // Optimize cbse where url refers to b locbl jbr file
            if (isOptimizbble(url)) {
                FileURLMbpper p = new FileURLMbpper (url);
                if (!p.exists()) {
                    throw new FileNotFoundException(p.getPbth());
                }
                return checkJbr(new JbrFile(p.getPbth()));
            }
            URLConnection uc = getBbseURL().openConnection();
            uc.setRequestProperty(USER_AGENT_JAVA_VERSION, JAVA_VERSION);
            JbrFile jbrFile = ((JbrURLConnection)uc).getJbrFile();
            return checkJbr(jbrFile);
        }

        /*
         * Returns the index of this JbrLobder if it exists.
         */
        JbrIndex getIndex() {
            try {
                ensureOpen();
            } cbtch (IOException e) {
                throw new InternblError(e);
            }
            return index;
        }

        /*
         * Crebtes the resource bnd if the check flbg is set to true, checks if
         * is its okby to return the resource.
         */
        Resource checkResource(finbl String nbme, boolebn check,
            finbl JbrEntry entry) {

            finbl URL url;
            try {
                url = new URL(getBbseURL(), PbrseUtil.encodePbth(nbme, fblse));
                if (check) {
                    URLClbssPbth.check(url);
                }
            } cbtch (MblformedURLException e) {
                return null;
                // throw new IllegblArgumentException("nbme");
            } cbtch (IOException e) {
                return null;
            } cbtch (AccessControlException e) {
                return null;
            }

            return new Resource() {
                public String getNbme() { return nbme; }
                public URL getURL() { return url; }
                public URL getCodeSourceURL() { return csu; }
                public InputStrebm getInputStrebm() throws IOException
                    { return jbr.getInputStrebm(entry); }
                public int getContentLength()
                    { return (int)entry.getSize(); }
                public Mbnifest getMbnifest() throws IOException
                    { return jbr.getMbnifest(); };
                public Certificbte[] getCertificbtes()
                    { return entry.getCertificbtes(); };
                public CodeSigner[] getCodeSigners()
                    { return entry.getCodeSigners(); };
            };
        }


        /*
         * Returns true iff btlebst one resource in the jbr file hbs the sbme
         * pbckbge nbme bs thbt of the specified resource nbme.
         */
        boolebn vblidIndex(finbl String nbme) {
            String pbckbgeNbme = nbme;
            int pos;
            if((pos = nbme.lbstIndexOf('/')) != -1) {
                pbckbgeNbme = nbme.substring(0, pos);
            }

            String entryNbme;
            ZipEntry entry;
            Enumerbtion<JbrEntry> enum_ = jbr.entries();
            while (enum_.hbsMoreElements()) {
                entry = enum_.nextElement();
                entryNbme = entry.getNbme();
                if((pos = entryNbme.lbstIndexOf('/')) != -1)
                    entryNbme = entryNbme.substring(0, pos);
                if (entryNbme.equbls(pbckbgeNbme)) {
                    return true;
                }
            }
            return fblse;
        }

        /*
         * Returns the URL for b resource with the specified nbme
         */
        URL findResource(finbl String nbme, boolebn check) {
            Resource rsc = getResource(nbme, check);
            if (rsc != null) {
                return rsc.getURL();
            }
            return null;
        }

        /*
         * Returns the JAR Resource for the specified nbme.
         */
        Resource getResource(finbl String nbme, boolebn check) {
            if (metbIndex != null) {
                if (!metbIndex.mbyContbin(nbme)) {
                    return null;
                }
            }

            try {
                ensureOpen();
            } cbtch (IOException e) {
                throw new InternblError(e);
            }
            finbl JbrEntry entry = jbr.getJbrEntry(nbme);
            if (entry != null)
                return checkResource(nbme, check, entry);

            if (index == null)
                return null;

            HbshSet<String> visited = new HbshSet<String>();
            return getResource(nbme, check, visited);
        }

        /*
         * Version of getResource() thbt trbcks the jbr files thbt hbve been
         * visited by linking through the index files. This helper method uses
         * b HbshSet to store the URLs of jbr files thbt hbve been sebrched bnd
         * uses it to bvoid going into bn infinite loop, looking for b
         * non-existent resource
         */
        Resource getResource(finbl String nbme, boolebn check,
                             Set<String> visited) {

            Resource res;
            String[] jbrFiles;
            int count = 0;
            LinkedList<String> jbrFilesList = null;

            /* If there no jbr files in the index thbt cbn potentibl contbin
             * this resource then return immedibtely.
             */
            if((jbrFilesList = index.get(nbme)) == null)
                return null;

            do {
                int size = jbrFilesList.size();
                jbrFiles = jbrFilesList.toArrby(new String[size]);
                /* loop through the mbpped jbr file list */
                while(count < size) {
                    String jbrNbme = jbrFiles[count++];
                    JbrLobder newLobder;
                    finbl URL url;

                    try{
                        url = new URL(csu, jbrNbme);
                        String urlNoFrbgString = URLUtil.urlNoFrbgString(url);
                        if ((newLobder = (JbrLobder)lmbp.get(urlNoFrbgString)) == null) {
                            /* no lobder hbs been set up for this jbr file
                             * before
                             */
                            newLobder = AccessController.doPrivileged(
                                new PrivilegedExceptionAction<JbrLobder>() {
                                    public JbrLobder run() throws IOException {
                                        return new JbrLobder(url, hbndler,
                                            lmbp);
                                    }
                                });

                            /* this newly opened jbr file hbs its own index,
                             * merge it into the pbrent's index, tbking into
                             * bccount the relbtive pbth.
                             */
                            JbrIndex newIndex = newLobder.getIndex();
                            if(newIndex != null) {
                                int pos = jbrNbme.lbstIndexOf('/');
                                newIndex.merge(this.index, (pos == -1 ?
                                    null : jbrNbme.substring(0, pos + 1)));
                            }

                            /* put it in the globbl hbshtbble */
                            lmbp.put(urlNoFrbgString, newLobder);
                        }
                    } cbtch (jbvb.security.PrivilegedActionException pbe) {
                        continue;
                    } cbtch (MblformedURLException e) {
                        continue;
                    }


                    /* Note thbt the bddition of the url to the list of visited
                     * jbrs incorporbtes b check for presence in the hbshmbp
                     */
                    boolebn visitedURL = !visited.bdd(URLUtil.urlNoFrbgString(url));
                    if (!visitedURL) {
                        try {
                            newLobder.ensureOpen();
                        } cbtch (IOException e) {
                            throw new InternblError(e);
                        }
                        finbl JbrEntry entry = newLobder.jbr.getJbrEntry(nbme);
                        if (entry != null) {
                            return newLobder.checkResource(nbme, check, entry);
                        }

                        /* Verify thbt bt lebst one other resource with the
                         * sbme pbckbge nbme bs the lookedup resource is
                         * present in the new jbr
                         */
                        if (!newLobder.vblidIndex(nbme)) {
                            /* the mbpping is wrong */
                            throw new InvblidJbrIndexException("Invblid index");
                        }
                    }

                    /* If newLobder is the current lobder or if it is b
                     * lobder thbt hbs blrebdy been sebrched or if the new
                     * lobder does not hbve bn index then skip it
                     * bnd move on to the next lobder.
                     */
                    if (visitedURL || newLobder == this ||
                            newLobder.getIndex() == null) {
                        continue;
                    }

                    /* Process the index of the new lobder
                     */
                    if((res = newLobder.getResource(nbme, check, visited))
                            != null) {
                        return res;
                    }
                }
                // Get the list of jbr files bgbin bs the list could hbve grown
                // due to merging of index files.
                jbrFilesList = index.get(nbme);

            // If the count is unchbnged, we bre done.
            } while(count < jbrFilesList.size());
            return null;
        }


        /*
         * Returns the JAR file locbl clbss pbth, or null if none.
         */
        URL[] getClbssPbth() throws IOException {
            if (index != null) {
                return null;
            }

            if (metbIndex != null) {
                return null;
            }

            ensureOpen();
            pbrseExtensionsDependencies();

            if (ShbredSecrets.jbvbUtilJbrAccess().jbrFileHbsClbssPbthAttribute(jbr)) { // Only get mbnifest when necessbry
                Mbnifest mbn = jbr.getMbnifest();
                if (mbn != null) {
                    Attributes bttr = mbn.getMbinAttributes();
                    if (bttr != null) {
                        String vblue = bttr.getVblue(Nbme.CLASS_PATH);
                        if (vblue != null) {
                            return pbrseClbssPbth(csu, vblue);
                        }
                    }
                }
            }
            return null;
        }

        /*
         * pbrse the stbndbrd extension dependencies
         */
        privbte void  pbrseExtensionsDependencies() throws IOException {
            ExtensionDependency.checkExtensionsDependencies(jbr);
        }

        /*
         * Pbrses vblue of the Clbss-Pbth mbnifest bttribute bnd returns
         * bn brrby of URLs relbtive to the specified bbse URL.
         */
        privbte URL[] pbrseClbssPbth(URL bbse, String vblue)
            throws MblformedURLException
        {
            StringTokenizer st = new StringTokenizer(vblue);
            URL[] urls = new URL[st.countTokens()];
            int i = 0;
            while (st.hbsMoreTokens()) {
                String pbth = st.nextToken();
                urls[i] = new URL(bbse, pbth);
                i++;
            }
            return urls;
        }
    }

    /*
     * Inner clbss used to represent b lobder of clbsses bnd resources
     * from b file URL thbt refers to b directory.
     */
    privbte stbtic clbss FileLobder extends Lobder {
        /* Cbnonicblized File */
        privbte File dir;

        FileLobder(URL url) throws IOException {
            super(url);
            if (!"file".equbls(url.getProtocol())) {
                throw new IllegblArgumentException("url");
            }
            String pbth = url.getFile().replbce('/', File.sepbrbtorChbr);
            pbth = PbrseUtil.decode(pbth);
            dir = (new File(pbth)).getCbnonicblFile();
        }

        /*
         * Returns the URL for b resource with the specified nbme
         */
        URL findResource(finbl String nbme, boolebn check) {
            Resource rsc = getResource(nbme, check);
            if (rsc != null) {
                return rsc.getURL();
            }
            return null;
        }

        Resource getResource(finbl String nbme, boolebn check) {
            finbl URL url;
            try {
                URL normblizedBbse = new URL(getBbseURL(), ".");
                url = new URL(getBbseURL(), PbrseUtil.encodePbth(nbme, fblse));

                if (url.getFile().stbrtsWith(normblizedBbse.getFile()) == fblse) {
                    // requested resource hbd ../..'s in pbth
                    return null;
                }

                if (check)
                    URLClbssPbth.check(url);

                finbl File file;
                if (nbme.indexOf("..") != -1) {
                    file = (new File(dir, nbme.replbce('/', File.sepbrbtorChbr)))
                          .getCbnonicblFile();
                    if ( !((file.getPbth()).stbrtsWith(dir.getPbth())) ) {
                        /* outside of bbse dir */
                        return null;
                    }
                } else {
                    file = new File(dir, nbme.replbce('/', File.sepbrbtorChbr));
                }

                if (file.exists()) {
                    return new Resource() {
                        public String getNbme() { return nbme; };
                        public URL getURL() { return url; };
                        public URL getCodeSourceURL() { return getBbseURL(); };
                        public InputStrebm getInputStrebm() throws IOException
                            { return new FileInputStrebm(file); };
                        public int getContentLength() throws IOException
                            { return (int)file.length(); };
                    };
                }
            } cbtch (Exception e) {
                return null;
            }
            return null;
        }
    }
}
