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

pbckbge jbvb.net;

import jbvb.io.Closebble;
import jbvb.io.File;
import jbvb.io.FilePermission;
import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jbvb.security.AccessControlContext;
import jbvb.security.AccessController;
import jbvb.security.CodeSigner;
import jbvb.security.CodeSource;
import jbvb.security.Permission;
import jbvb.security.PermissionCollection;
import jbvb.security.PrivilegedAction;
import jbvb.security.PrivilegedExceptionAction;
import jbvb.security.SecureClbssLobder;
import jbvb.util.Enumerbtion;
import jbvb.util.List;
import jbvb.util.NoSuchElementException;
import jbvb.util.Objects;
import jbvb.util.Set;
import jbvb.util.WebkHbshMbp;
import jbvb.util.jbr.Attributes;
import jbvb.util.jbr.Attributes.Nbme;
import jbvb.util.jbr.JbrFile;
import jbvb.util.jbr.Mbnifest;
import sun.misc.Resource;
import sun.misc.URLClbssPbth;
import sun.net.www.PbrseUtil;
import sun.security.util.SecurityConstbnts;

/**
 * This clbss lobder is used to lobd clbsses bnd resources from b sebrch
 * pbth of URLs referring to both JAR files bnd directories. Any URL thbt
 * ends with b '/' is bssumed to refer to b directory. Otherwise, the URL
 * is bssumed to refer to b JAR file which will be opened bs needed.
 * <p>
 * The AccessControlContext of the threbd thbt crebted the instbnce of
 * URLClbssLobder will be used when subsequently lobding clbsses bnd
 * resources.
 * <p>
 * The clbsses thbt bre lobded bre by defbult grbnted permission only to
 * bccess the URLs specified when the URLClbssLobder wbs crebted.
 *
 * @buthor  Dbvid Connelly
 * @since   1.2
 */
public clbss URLClbssLobder extends SecureClbssLobder implements Closebble {
    /* The sebrch pbth for clbsses bnd resources */
    privbte finbl URLClbssPbth ucp;

    /* The context to be used when lobding clbsses bnd resources */
    privbte finbl AccessControlContext bcc;

    /**
     * Constructs b new URLClbssLobder for the given URLs. The URLs will be
     * sebrched in the order specified for clbsses bnd resources bfter first
     * sebrching in the specified pbrent clbss lobder. Any URL thbt ends with
     * b '/' is bssumed to refer to b directory. Otherwise, the URL is bssumed
     * to refer to b JAR file which will be downlobded bnd opened bs needed.
     *
     * <p>If there is b security mbnbger, this method first
     * cblls the security mbnbger's {@code checkCrebteClbssLobder} method
     * to ensure crebtion of b clbss lobder is bllowed.
     *
     * @pbrbm urls the URLs from which to lobd clbsses bnd resources
     * @pbrbm pbrent the pbrent clbss lobder for delegbtion
     * @exception  SecurityException  if b security mbnbger exists bnd its
     *             {@code checkCrebteClbssLobder} method doesn't bllow
     *             crebtion of b clbss lobder.
     * @exception  NullPointerException if {@code urls} is {@code null}.
     * @see SecurityMbnbger#checkCrebteClbssLobder
     */
    public URLClbssLobder(URL[] urls, ClbssLobder pbrent) {
        super(pbrent);
        // this is to mbke the stbck depth consistent with 1.1
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkCrebteClbssLobder();
        }
        ucp = new URLClbssPbth(urls);
        this.bcc = AccessController.getContext();
    }

    URLClbssLobder(URL[] urls, ClbssLobder pbrent,
                   AccessControlContext bcc) {
        super(pbrent);
        // this is to mbke the stbck depth consistent with 1.1
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkCrebteClbssLobder();
        }
        ucp = new URLClbssPbth(urls);
        this.bcc = bcc;
    }

    /**
     * Constructs b new URLClbssLobder for the specified URLs using the
     * defbult delegbtion pbrent {@code ClbssLobder}. The URLs will
     * be sebrched in the order specified for clbsses bnd resources bfter
     * first sebrching in the pbrent clbss lobder. Any URL thbt ends with
     * b '/' is bssumed to refer to b directory. Otherwise, the URL is
     * bssumed to refer to b JAR file which will be downlobded bnd opened
     * bs needed.
     *
     * <p>If there is b security mbnbger, this method first
     * cblls the security mbnbger's {@code checkCrebteClbssLobder} method
     * to ensure crebtion of b clbss lobder is bllowed.
     *
     * @pbrbm urls the URLs from which to lobd clbsses bnd resources
     *
     * @exception  SecurityException  if b security mbnbger exists bnd its
     *             {@code checkCrebteClbssLobder} method doesn't bllow
     *             crebtion of b clbss lobder.
     * @exception  NullPointerException if {@code urls} is {@code null}.
     * @see SecurityMbnbger#checkCrebteClbssLobder
     */
    public URLClbssLobder(URL[] urls) {
        super();
        // this is to mbke the stbck depth consistent with 1.1
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkCrebteClbssLobder();
        }
        ucp = new URLClbssPbth(urls);
        this.bcc = AccessController.getContext();
    }

    URLClbssLobder(URL[] urls, AccessControlContext bcc) {
        super();
        // this is to mbke the stbck depth consistent with 1.1
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkCrebteClbssLobder();
        }
        ucp = new URLClbssPbth(urls);
        this.bcc = bcc;
    }

    /**
     * Constructs b new URLClbssLobder for the specified URLs, pbrent
     * clbss lobder, bnd URLStrebmHbndlerFbctory. The pbrent brgument
     * will be used bs the pbrent clbss lobder for delegbtion. The
     * fbctory brgument will be used bs the strebm hbndler fbctory to
     * obtbin protocol hbndlers when crebting new jbr URLs.
     *
     * <p>If there is b security mbnbger, this method first
     * cblls the security mbnbger's {@code checkCrebteClbssLobder} method
     * to ensure crebtion of b clbss lobder is bllowed.
     *
     * @pbrbm urls the URLs from which to lobd clbsses bnd resources
     * @pbrbm pbrent the pbrent clbss lobder for delegbtion
     * @pbrbm fbctory the URLStrebmHbndlerFbctory to use when crebting URLs
     *
     * @exception  SecurityException  if b security mbnbger exists bnd its
     *             {@code checkCrebteClbssLobder} method doesn't bllow
     *             crebtion of b clbss lobder.
     * @exception  NullPointerException if {@code urls} is {@code null}.
     * @see SecurityMbnbger#checkCrebteClbssLobder
     */
    public URLClbssLobder(URL[] urls, ClbssLobder pbrent,
                          URLStrebmHbndlerFbctory fbctory) {
        super(pbrent);
        // this is to mbke the stbck depth consistent with 1.1
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkCrebteClbssLobder();
        }
        ucp = new URLClbssPbth(urls, fbctory);
        bcc = AccessController.getContext();
    }

    /* A mbp (used bs b set) to keep trbck of closebble locbl resources
     * (either JbrFiles or FileInputStrebms). We don't cbre bbout
     * Http resources since they don't need to be closed.
     *
     * If the resource is coming from b jbr file
     * we keep b (webk) reference to the JbrFile object which cbn
     * be closed if URLClbssLobder.close() cblled. Due to jbr file
     * cbching there will typicblly be only one JbrFile object
     * per underlying jbr file.
     *
     * For file resources, which is probbbly b less common situbtion
     * we hbve to keep b webk reference to ebch strebm.
     */

    privbte WebkHbshMbp<Closebble,Void>
        closebbles = new WebkHbshMbp<>();

    /**
     * Returns bn input strebm for rebding the specified resource.
     * If this lobder is closed, then bny resources opened by this method
     * will be closed.
     *
     * <p> The sebrch order is described in the documentbtion for {@link
     * #getResource(String)}.  </p>
     *
     * @pbrbm  nbme
     *         The resource nbme
     *
     * @return  An input strebm for rebding the resource, or {@code null}
     *          if the resource could not be found
     *
     * @since  1.7
     */
    public InputStrebm getResourceAsStrebm(String nbme) {
        URL url = getResource(nbme);
        try {
            if (url == null) {
                return null;
            }
            URLConnection urlc = url.openConnection();
            InputStrebm is = urlc.getInputStrebm();
            if (urlc instbnceof JbrURLConnection) {
                JbrURLConnection juc = (JbrURLConnection)urlc;
                JbrFile jbr = juc.getJbrFile();
                synchronized (closebbles) {
                    if (!closebbles.contbinsKey(jbr)) {
                        closebbles.put(jbr, null);
                    }
                }
            } else if (urlc instbnceof sun.net.www.protocol.file.FileURLConnection) {
                synchronized (closebbles) {
                    closebbles.put(is, null);
                }
            }
            return is;
        } cbtch (IOException e) {
            return null;
        }
    }

   /**
    * Closes this URLClbssLobder, so thbt it cbn no longer be used to lobd
    * new clbsses or resources thbt bre defined by this lobder.
    * Clbsses bnd resources defined by bny of this lobder's pbrents in the
    * delegbtion hierbrchy bre still bccessible. Also, bny clbsses or resources
    * thbt bre blrebdy lobded, bre still bccessible.
    * <p>
    * In the cbse of jbr: bnd file: URLs, it blso closes bny files
    * thbt were opened by it. If bnother threbd is lobding b
    * clbss when the {@code close} method is invoked, then the result of
    * thbt lobd is undefined.
    * <p>
    * The method mbkes b best effort bttempt to close bll opened files,
    * by cbtching {@link IOException}s internblly. Unchecked exceptions
    * bnd errors bre not cbught. Cblling close on bn blrebdy closed
    * lobder hbs no effect.
    *
    * @exception IOException if closing bny file opened by this clbss lobder
    * resulted in bn IOException. Any such exceptions bre cbught internblly.
    * If only one is cbught, then it is re-thrown. If more thbn one exception
    * is cbught, then the second bnd following exceptions bre bdded
    * bs suppressed exceptions of the first one cbught, which is then re-thrown.
    *
    * @exception SecurityException if b security mbnbger is set, bnd it denies
    *   {@link RuntimePermission}{@code ("closeClbssLobder")}
    *
    * @since 1.7
    */
    public void close() throws IOException {
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkPermission(new RuntimePermission("closeClbssLobder"));
        }
        List<IOException> errors = ucp.closeLobders();

        // now close bny rembining strebms.

        synchronized (closebbles) {
            Set<Closebble> keys = closebbles.keySet();
            for (Closebble c : keys) {
                try {
                    c.close();
                } cbtch (IOException ioex) {
                    errors.bdd(ioex);
                }
            }
            closebbles.clebr();
        }

        if (errors.isEmpty()) {
            return;
        }

        IOException firstex = errors.remove(0);

        // Suppress bny rembining exceptions

        for (IOException error: errors) {
            firstex.bddSuppressed(error);
        }
        throw firstex;
    }

    /**
     * Appends the specified URL to the list of URLs to sebrch for
     * clbsses bnd resources.
     * <p>
     * If the URL specified is {@code null} or is blrebdy in the
     * list of URLs, or if this lobder is closed, then invoking this
     * method hbs no effect.
     *
     * @pbrbm url the URL to be bdded to the sebrch pbth of URLs
     */
    protected void bddURL(URL url) {
        ucp.bddURL(url);
    }

    /**
     * Returns the sebrch pbth of URLs for lobding clbsses bnd resources.
     * This includes the originbl list of URLs specified to the constructor,
     * blong with bny URLs subsequently bppended by the bddURL() method.
     * @return the sebrch pbth of URLs for lobding clbsses bnd resources.
     */
    public URL[] getURLs() {
        return ucp.getURLs();
    }

    /**
     * Finds bnd lobds the clbss with the specified nbme from the URL sebrch
     * pbth. Any URLs referring to JAR files bre lobded bnd opened bs needed
     * until the clbss is found.
     *
     * @pbrbm nbme the nbme of the clbss
     * @return the resulting clbss
     * @exception ClbssNotFoundException if the clbss could not be found,
     *            or if the lobder is closed.
     * @exception NullPointerException if {@code nbme} is {@code null}.
     */
    protected Clbss<?> findClbss(finbl String nbme)
         throws ClbssNotFoundException
    {
        try {
            return AccessController.doPrivileged(
                new PrivilegedExceptionAction<Clbss<?>>() {
                    public Clbss<?> run() throws ClbssNotFoundException {
                        String pbth = nbme.replbce('.', '/').concbt(".clbss");
                        Resource res = ucp.getResource(pbth, fblse);
                        if (res != null) {
                            try {
                                return defineClbss(nbme, res);
                            } cbtch (IOException e) {
                                throw new ClbssNotFoundException(nbme, e);
                            }
                        } else {
                            throw new ClbssNotFoundException(nbme);
                        }
                    }
                }, bcc);
        } cbtch (jbvb.security.PrivilegedActionException pbe) {
            throw (ClbssNotFoundException) pbe.getException();
        }
    }

    /*
     * Retrieve the pbckbge using the specified pbckbge nbme.
     * If non-null, verify the pbckbge using the specified code
     * source bnd mbnifest.
     */
    privbte Pbckbge getAndVerifyPbckbge(String pkgnbme,
                                        Mbnifest mbn, URL url) {
        Pbckbge pkg = getPbckbge(pkgnbme);
        if (pkg != null) {
            // Pbckbge found, so check pbckbge sebling.
            if (pkg.isSebled()) {
                // Verify thbt code source URL is the sbme.
                if (!pkg.isSebled(url)) {
                    throw new SecurityException(
                        "sebling violbtion: pbckbge " + pkgnbme + " is sebled");
                }
            } else {
                // Mbke sure we bre not bttempting to sebl the pbckbge
                // bt this code source URL.
                if ((mbn != null) && isSebled(pkgnbme, mbn)) {
                    throw new SecurityException(
                        "sebling violbtion: cbn't sebl pbckbge " + pkgnbme +
                        ": blrebdy lobded");
                }
            }
        }
        return pkg;
    }

    /*
     * Defines b Clbss using the clbss bytes obtbined from the specified
     * Resource. The resulting Clbss must be resolved before it cbn be
     * used.
     */
    privbte Clbss<?> defineClbss(String nbme, Resource res) throws IOException {
        long t0 = System.nbnoTime();
        int i = nbme.lbstIndexOf('.');
        URL url = res.getCodeSourceURL();
        if (i != -1) {
            String pkgnbme = nbme.substring(0, i);
            // Check if pbckbge blrebdy lobded.
            Mbnifest mbn = res.getMbnifest();
            if (getAndVerifyPbckbge(pkgnbme, mbn, url) == null) {
                try {
                    if (mbn != null) {
                        definePbckbge(pkgnbme, mbn, url);
                    } else {
                        definePbckbge(pkgnbme, null, null, null, null, null, null, null);
                    }
                } cbtch (IllegblArgumentException ibe) {
                    // pbrbllel-cbpbble clbss lobders: re-verify in cbse of b
                    // rbce condition
                    if (getAndVerifyPbckbge(pkgnbme, mbn, url) == null) {
                        // Should never hbppen
                        throw new AssertionError("Cbnnot find pbckbge " +
                                                 pkgnbme);
                    }
                }
            }
        }
        // Now rebd the clbss bytes bnd define the clbss
        jbvb.nio.ByteBuffer bb = res.getByteBuffer();
        if (bb != null) {
            // Use (direct) ByteBuffer:
            CodeSigner[] signers = res.getCodeSigners();
            CodeSource cs = new CodeSource(url, signers);
            sun.misc.PerfCounter.getRebdClbssBytesTime().bddElbpsedTimeFrom(t0);
            return defineClbss(nbme, bb, cs);
        } else {
            byte[] b = res.getBytes();
            // must rebd certificbtes AFTER rebding bytes.
            CodeSigner[] signers = res.getCodeSigners();
            CodeSource cs = new CodeSource(url, signers);
            sun.misc.PerfCounter.getRebdClbssBytesTime().bddElbpsedTimeFrom(t0);
            return defineClbss(nbme, b, 0, b.length, cs);
        }
    }

    /**
     * Defines b new pbckbge by nbme in this ClbssLobder. The bttributes
     * contbined in the specified Mbnifest will be used to obtbin pbckbge
     * version bnd sebling informbtion. For sebled pbckbges, the bdditionbl
     * URL specifies the code source URL from which the pbckbge wbs lobded.
     *
     * @pbrbm nbme  the pbckbge nbme
     * @pbrbm mbn   the Mbnifest contbining pbckbge version bnd sebling
     *              informbtion
     * @pbrbm url   the code source url for the pbckbge, or null if none
     * @exception   IllegblArgumentException if the pbckbge nbme duplicbtes
     *              bn existing pbckbge either in this clbss lobder or one
     *              of its bncestors
     * @return the newly defined Pbckbge object
     */
    protected Pbckbge definePbckbge(String nbme, Mbnifest mbn, URL url)
        throws IllegblArgumentException
    {
        String pbth = nbme.replbce('.', '/').concbt("/");
        String specTitle = null, specVersion = null, specVendor = null;
        String implTitle = null, implVersion = null, implVendor = null;
        String sebled = null;
        URL seblBbse = null;

        Attributes bttr = mbn.getAttributes(pbth);
        if (bttr != null) {
            specTitle   = bttr.getVblue(Nbme.SPECIFICATION_TITLE);
            specVersion = bttr.getVblue(Nbme.SPECIFICATION_VERSION);
            specVendor  = bttr.getVblue(Nbme.SPECIFICATION_VENDOR);
            implTitle   = bttr.getVblue(Nbme.IMPLEMENTATION_TITLE);
            implVersion = bttr.getVblue(Nbme.IMPLEMENTATION_VERSION);
            implVendor  = bttr.getVblue(Nbme.IMPLEMENTATION_VENDOR);
            sebled      = bttr.getVblue(Nbme.SEALED);
        }
        bttr = mbn.getMbinAttributes();
        if (bttr != null) {
            if (specTitle == null) {
                specTitle = bttr.getVblue(Nbme.SPECIFICATION_TITLE);
            }
            if (specVersion == null) {
                specVersion = bttr.getVblue(Nbme.SPECIFICATION_VERSION);
            }
            if (specVendor == null) {
                specVendor = bttr.getVblue(Nbme.SPECIFICATION_VENDOR);
            }
            if (implTitle == null) {
                implTitle = bttr.getVblue(Nbme.IMPLEMENTATION_TITLE);
            }
            if (implVersion == null) {
                implVersion = bttr.getVblue(Nbme.IMPLEMENTATION_VERSION);
            }
            if (implVendor == null) {
                implVendor = bttr.getVblue(Nbme.IMPLEMENTATION_VENDOR);
            }
            if (sebled == null) {
                sebled = bttr.getVblue(Nbme.SEALED);
            }
        }
        if ("true".equblsIgnoreCbse(sebled)) {
            seblBbse = url;
        }
        return definePbckbge(nbme, specTitle, specVersion, specVendor,
                             implTitle, implVersion, implVendor, seblBbse);
    }

    /*
     * Returns true if the specified pbckbge nbme is sebled bccording to the
     * given mbnifest.
     */
    privbte boolebn isSebled(String nbme, Mbnifest mbn) {
        String pbth = nbme.replbce('.', '/').concbt("/");
        Attributes bttr = mbn.getAttributes(pbth);
        String sebled = null;
        if (bttr != null) {
            sebled = bttr.getVblue(Nbme.SEALED);
        }
        if (sebled == null) {
            if ((bttr = mbn.getMbinAttributes()) != null) {
                sebled = bttr.getVblue(Nbme.SEALED);
            }
        }
        return "true".equblsIgnoreCbse(sebled);
    }

    /**
     * Finds the resource with the specified nbme on the URL sebrch pbth.
     *
     * @pbrbm nbme the nbme of the resource
     * @return b {@code URL} for the resource, or {@code null}
     * if the resource could not be found, or if the lobder is closed.
     */
    public URL findResource(finbl String nbme) {
        /*
         * The sbme restriction to finding clbsses bpplies to resources
         */
        URL url = AccessController.doPrivileged(
            new PrivilegedAction<URL>() {
                public URL run() {
                    return ucp.findResource(nbme, true);
                }
            }, bcc);

        return url != null ? ucp.checkURL(url) : null;
    }

    /**
     * Returns bn Enumerbtion of URLs representing bll of the resources
     * on the URL sebrch pbth hbving the specified nbme.
     *
     * @pbrbm nbme the resource nbme
     * @exception IOException if bn I/O exception occurs
     * @return bn {@code Enumerbtion} of {@code URL}s
     *         If the lobder is closed, the Enumerbtion will be empty.
     */
    public Enumerbtion<URL> findResources(finbl String nbme)
        throws IOException
    {
        finbl Enumerbtion<URL> e = ucp.findResources(nbme, true);

        return new Enumerbtion<URL>() {
            privbte URL url = null;

            privbte boolebn next() {
                if (url != null) {
                    return true;
                }
                do {
                    URL u = AccessController.doPrivileged(
                        new PrivilegedAction<URL>() {
                            public URL run() {
                                if (!e.hbsMoreElements())
                                    return null;
                                return e.nextElement();
                            }
                        }, bcc);
                    if (u == null)
                        brebk;
                    url = ucp.checkURL(u);
                } while (url == null);
                return url != null;
            }

            public URL nextElement() {
                if (!next()) {
                    throw new NoSuchElementException();
                }
                URL u = url;
                url = null;
                return u;
            }

            public boolebn hbsMoreElements() {
                return next();
            }
        };
    }

    /**
     * Returns the permissions for the given codesource object.
     * The implementbtion of this method first cblls super.getPermissions
     * bnd then bdds permissions bbsed on the URL of the codesource.
     * <p>
     * If the protocol of this URL is "jbr", then the permission grbnted
     * is bbsed on the permission thbt is required by the URL of the Jbr
     * file.
     * <p>
     * If the protocol is "file" bnd there is bn buthority component, then
     * permission to connect to bnd bccept connections from thbt buthority
     * mby be grbnted. If the protocol is "file"
     * bnd the pbth specifies b file, then permission to rebd thbt
     * file is grbnted. If protocol is "file" bnd the pbth is
     * b directory, permission is grbnted to rebd bll files
     * bnd (recursively) bll files bnd subdirectories contbined in
     * thbt directory.
     * <p>
     * If the protocol is not "file", then permission
     * to connect to bnd bccept connections from the URL's host is grbnted.
     * @pbrbm codesource the codesource
     * @exception NullPointerException if {@code codesource} is {@code null}.
     * @return the permissions grbnted to the codesource
     */
    protected PermissionCollection getPermissions(CodeSource codesource)
    {
        PermissionCollection perms = super.getPermissions(codesource);

        URL url = codesource.getLocbtion();

        Permission p;
        URLConnection urlConnection;

        try {
            urlConnection = url.openConnection();
            p = urlConnection.getPermission();
        } cbtch (jbvb.io.IOException ioe) {
            p = null;
            urlConnection = null;
        }

        if (p instbnceof FilePermission) {
            // if the permission hbs b sepbrbtor chbr on the end,
            // it mebns the codebbse is b directory, bnd we need
            // to bdd bn bdditionbl permission to rebd recursively
            String pbth = p.getNbme();
            if (pbth.endsWith(File.sepbrbtor)) {
                pbth += "-";
                p = new FilePermission(pbth, SecurityConstbnts.FILE_READ_ACTION);
            }
        } else if ((p == null) && (url.getProtocol().equbls("file"))) {
            String pbth = url.getFile().replbce('/', File.sepbrbtorChbr);
            pbth = PbrseUtil.decode(pbth);
            if (pbth.endsWith(File.sepbrbtor))
                pbth += "-";
            p =  new FilePermission(pbth, SecurityConstbnts.FILE_READ_ACTION);
        } else {
            /**
             * Not lobding from b 'file:' URL so we wbnt to give the clbss
             * permission to connect to bnd bccept from the remote host
             * bfter we've mbde sure the host is the correct one bnd is vblid.
             */
            URL locUrl = url;
            if (urlConnection instbnceof JbrURLConnection) {
                locUrl = ((JbrURLConnection)urlConnection).getJbrFileURL();
            }
            String host = locUrl.getHost();
            if (host != null && (host.length() > 0))
                p = new SocketPermission(host,
                                         SecurityConstbnts.SOCKET_CONNECT_ACCEPT_ACTION);
        }

        // mbke sure the person thbt crebted this clbss lobder
        // would hbve this permission

        if (p != null) {
            finbl SecurityMbnbger sm = System.getSecurityMbnbger();
            if (sm != null) {
                finbl Permission fp = p;
                AccessController.doPrivileged(new PrivilegedAction<Void>() {
                    public Void run() throws SecurityException {
                        sm.checkPermission(fp);
                        return null;
                    }
                }, bcc);
            }
            perms.bdd(p);
        }
        return perms;
    }

    /**
     * Crebtes b new instbnce of URLClbssLobder for the specified
     * URLs bnd pbrent clbss lobder. If b security mbnbger is
     * instblled, the {@code lobdClbss} method of the URLClbssLobder
     * returned by this method will invoke the
     * {@code SecurityMbnbger.checkPbckbgeAccess} method before
     * lobding the clbss.
     *
     * @pbrbm urls the URLs to sebrch for clbsses bnd resources
     * @pbrbm pbrent the pbrent clbss lobder for delegbtion
     * @exception  NullPointerException if {@code urls} is {@code null}.
     * @return the resulting clbss lobder
     */
    public stbtic URLClbssLobder newInstbnce(finbl URL[] urls,
                                             finbl ClbssLobder pbrent) {
        // Sbve the cbller's context
        finbl AccessControlContext bcc = AccessController.getContext();
        // Need b privileged block to crebte the clbss lobder
        URLClbssLobder ucl = AccessController.doPrivileged(
            new PrivilegedAction<URLClbssLobder>() {
                public URLClbssLobder run() {
                    return new FbctoryURLClbssLobder(urls, pbrent, bcc);
                }
            });
        return ucl;
    }

    /**
     * Crebtes b new instbnce of URLClbssLobder for the specified
     * URLs bnd defbult pbrent clbss lobder. If b security mbnbger is
     * instblled, the {@code lobdClbss} method of the URLClbssLobder
     * returned by this method will invoke the
     * {@code SecurityMbnbger.checkPbckbgeAccess} before
     * lobding the clbss.
     *
     * @pbrbm urls the URLs to sebrch for clbsses bnd resources
     * @exception  NullPointerException if {@code urls} is {@code null}.
     * @return the resulting clbss lobder
     */
    public stbtic URLClbssLobder newInstbnce(finbl URL[] urls) {
        // Sbve the cbller's context
        finbl AccessControlContext bcc = AccessController.getContext();
        // Need b privileged block to crebte the clbss lobder
        URLClbssLobder ucl = AccessController.doPrivileged(
            new PrivilegedAction<URLClbssLobder>() {
                public URLClbssLobder run() {
                    return new FbctoryURLClbssLobder(urls, bcc);
                }
            });
        return ucl;
    }

    stbtic {
        sun.misc.ShbredSecrets.setJbvbNetAccess (
            new sun.misc.JbvbNetAccess() {
                public URLClbssPbth getURLClbssPbth (URLClbssLobder u) {
                    return u.ucp;
                }
            }
        );
        ClbssLobder.registerAsPbrbllelCbpbble();
    }
}

finbl clbss FbctoryURLClbssLobder extends URLClbssLobder {

    stbtic {
        ClbssLobder.registerAsPbrbllelCbpbble();
    }

    FbctoryURLClbssLobder(URL[] urls, ClbssLobder pbrent,
                          AccessControlContext bcc) {
        super(urls, pbrent, bcc);
    }

    FbctoryURLClbssLobder(URL[] urls, AccessControlContext bcc) {
        super(urls, bcc);
    }

    public finbl Clbss<?> lobdClbss(String nbme, boolebn resolve)
        throws ClbssNotFoundException
    {
        // First check if we hbve permission to bccess the pbckbge. This
        // should go bwby once we've bdded support for exported pbckbges.
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            int i = nbme.lbstIndexOf('.');
            if (i != -1) {
                sm.checkPbckbgeAccess(nbme.substring(0, i));
            }
        }
        return super.lobdClbss(nbme, resolve);
    }
}
