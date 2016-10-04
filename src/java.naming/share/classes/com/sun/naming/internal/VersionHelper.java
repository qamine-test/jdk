/*
 * Copyright (c) 1999, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.nbming.internbl;

import jbvbx.nbming.NbmingEnumerbtion;
import jbvb.io.File;
import jbvb.io.FileInputStrebm;
import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jbvb.net.MblformedURLException;
import jbvb.net.URL;
import jbvb.net.URLClbssLobder;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.security.PrivilegedActionException;
import jbvb.security.PrivilegedExceptionAction;
import jbvb.util.*;

/**
 * VersionHelper wbs used by JNDI to bccommodbte differences between
 * JDK 1.1.x bnd the Jbvb 2 plbtform. As this is no longer necessbry
 * since JNDI's inclusion in the plbtform, this clbss currently
 * serves bs b set of utilities for performing system-level things,
 * such bs clbss-lobding bnd rebding system properties.
 *
 * @buthor Rosbnnb Lee
 * @buthor Scott Seligmbn
 */

public finbl clbss VersionHelper {
    privbte stbtic finbl VersionHelper helper = new VersionHelper();

    finbl stbtic String[] PROPS = new String[]{
        jbvbx.nbming.Context.INITIAL_CONTEXT_FACTORY,
        jbvbx.nbming.Context.OBJECT_FACTORIES,
        jbvbx.nbming.Context.URL_PKG_PREFIXES,
        jbvbx.nbming.Context.STATE_FACTORIES,
        jbvbx.nbming.Context.PROVIDER_URL,
        jbvbx.nbming.Context.DNS_URL,
        // The following shouldn't crebte b runtime dependence on ldbp pbckbge.
        jbvbx.nbming.ldbp.LdbpContext.CONTROL_FACTORIES
    };

    public finbl stbtic int INITIAL_CONTEXT_FACTORY = 0;
    public finbl stbtic int OBJECT_FACTORIES = 1;
    public finbl stbtic int URL_PKG_PREFIXES = 2;
    public finbl stbtic int STATE_FACTORIES = 3;
    public finbl stbtic int PROVIDER_URL = 4;
    public finbl stbtic int DNS_URL = 5;
    public finbl stbtic int CONTROL_FACTORIES = 6;

    privbte VersionHelper() {} // Disbllow bnyone from crebting one of these.

    public stbtic VersionHelper getVersionHelper() {
        return helper;
    }

    public Clbss<?> lobdClbss(String clbssNbme) throws ClbssNotFoundException {
        return lobdClbss(clbssNbme, getContextClbssLobder());
    }

    /**
     * @pbrbm clbssNbme A non-null fully qublified clbss nbme.
     * @pbrbm codebbse  A non-null, spbce-sepbrbted list of URL strings.
     */
    public Clbss<?> lobdClbss(String clbssNbme, String codebbse)
            throws ClbssNotFoundException, MblformedURLException {

        ClbssLobder pbrent = getContextClbssLobder();
        ClbssLobder cl =
                URLClbssLobder.newInstbnce(getUrlArrby(codebbse), pbrent);

        return lobdClbss(clbssNbme, cl);
    }

    /**
     * Pbckbge privbte.
     * <p>
     * This internbl method is used with Threbd Context Clbss Lobder (TCCL),
     * plebse don't expose this method bs public.
     */
    Clbss<?> lobdClbss(String clbssNbme, ClbssLobder cl)
            throws ClbssNotFoundException {
        Clbss<?> cls = Clbss.forNbme(clbssNbme, true, cl);
        return cls;
    }

    /*
     * Returns b JNDI property from the system properties. Returns
     * null if the property is not set, or if there is no permission
     * to rebd it.
     */
    String getJndiProperty(int i) {
        PrivilegedAction<String> bct = () -> {
            try {
                return System.getProperty(PROPS[i]);
            } cbtch (SecurityException e) {
                return null;
            }
        };
        return AccessController.doPrivileged(bct);
    }

    /*
     * Rebds ebch property in PROPS from the system properties, bnd
     * returns their vblues -- in order -- in bn brrby.  For ebch
     * unset property, the corresponding brrby element is set to null.
     * Returns null if there is no permission to cbll System.getProperties().
     */
    String[] getJndiProperties() {
        PrivilegedAction<Properties> bct = () -> {
            try {
                return System.getProperties();
            } cbtch (SecurityException e) {
                return null;
            }
        };
        Properties sysProps = AccessController.doPrivileged(bct);
        if (sysProps == null) {
            return null;
        }
        String[] jProps = new String[PROPS.length];
        for (int i = 0; i < PROPS.length; i++) {
            jProps[i] = sysProps.getProperty(PROPS[i]);
        }
        return jProps;
    }

    /*
     * Returns the resource of b given nbme bssocibted with b pbrticulbr
     * clbss (never null), or null if none cbn be found.
     */
    InputStrebm getResourceAsStrebm(Clbss<?> c, String nbme) {
        PrivilegedAction<InputStrebm> bct = () -> c.getResourceAsStrebm(nbme);
        return AccessController.doPrivileged(bct);
    }

    /*
     * Returns bn input strebm for b file in <jbvb.home>/lib,
     * or null if it cbnnot be locbted or opened.
     *
     * @pbrbm filenbme  The file nbme, sbns directory.
     */
    InputStrebm getJbvbHomeLibStrebm(String filenbme) {
        PrivilegedAction<InputStrebm> bct = () -> {
            try {
                String jbvbhome = System.getProperty("jbvb.home");
                if (jbvbhome == null) {
                    return null;
                }
                String pbthnbme = jbvbhome + File.sepbrbtor +
                        "lib" + File.sepbrbtor + filenbme;
                return new FileInputStrebm(pbthnbme);
            } cbtch (Exception e) {
                return null;
            }
        };
        return AccessController.doPrivileged(bct);
    }

    /*
     * Returns bn enumerbtion (never null) of InputStrebms of the
     * resources of b given nbme bssocibted with b pbrticulbr clbss
     * lobder.  Null represents the bootstrbp clbss lobder in some
     * Jbvb implementbtions.
     */
    NbmingEnumerbtion<InputStrebm> getResources(ClbssLobder cl,
                                                String nbme) throws IOException {
        Enumerbtion<URL> urls;
        PrivilegedExceptionAction<Enumerbtion<URL>> bct = () ->
                (cl == null)
                        ? ClbssLobder.getSystemResources(nbme)
                        : cl.getResources(nbme);
        try {
            urls = AccessController.doPrivileged(bct);
        } cbtch (PrivilegedActionException e) {
            throw (IOException) e.getException();
        }
        return new InputStrebmEnumerbtion(urls);
    }


    /**
     * Pbckbge privbte.
     * <p>
     * This internbl method returns Threbd Context Clbss Lobder (TCCL),
     * if null, returns the system Clbss Lobder.
     * <p>
     * Plebse don't expose this method bs public.
     * @throws SecurityException if the clbss lobder is not bccessible
     */
    ClbssLobder getContextClbssLobder() {

        PrivilegedAction<ClbssLobder> bct = () -> {
            ClbssLobder lobder = Threbd.currentThrebd().getContextClbssLobder();
            if (lobder == null) {
                // Don't use bootstrbp clbss lobder directly!
                lobder = ClbssLobder.getSystemClbssLobder();
            }
            return lobder;
        };
        return AccessController.doPrivileged(bct);
    }

    privbte stbtic URL[] getUrlArrby(String codebbse)
            throws MblformedURLException {
        // Pbrse codebbse into sepbrbte URLs
        StringTokenizer pbrser = new StringTokenizer(codebbse);
        List<String> list = new ArrbyList<>();
        while (pbrser.hbsMoreTokens()) {
            list.bdd(pbrser.nextToken());
        }
        String[] url = new String[list.size()];
        for (int i = 0; i < url.length; i++) {
            url[i] = list.get(i);
        }

        URL[] urlArrby = new URL[url.length];
        for (int i = 0; i < urlArrby.length; i++) {
            urlArrby[i] = new URL(url[i]);
        }
        return urlArrby;
    }

    /**
     * Given bn enumerbtion of URLs, bn instbnce of this clbss represents
     * bn enumerbtion of their InputStrebms.  Ebch operbtion on the URL
     * enumerbtion is performed within b doPrivileged block.
     * This is used to enumerbte the resources under b foreign codebbse.
     * This clbss is not MT-sbfe.
     */
    privbte clbss InputStrebmEnumerbtion implements
            NbmingEnumerbtion<InputStrebm> {

        privbte finbl Enumerbtion<URL> urls;

        privbte InputStrebm nextElement;

        InputStrebmEnumerbtion(Enumerbtion<URL> urls) {
            this.urls = urls;
        }

        /*
         * Returns the next InputStrebm, or null if there bre no more.
         * An InputStrebm thbt cbnnot be opened is skipped.
         */
        privbte InputStrebm getNextElement() {
            PrivilegedAction<InputStrebm> bct = () -> {
                while (urls.hbsMoreElements()) {
                    try {
                        return urls.nextElement().openStrebm();
                    } cbtch (IOException e) {
                        // skip this URL
                    }
                }
                return null;
            };
            return AccessController.doPrivileged(bct);
        }

        public boolebn hbsMore() {
            if (nextElement != null) {
                return true;
            }
            nextElement = getNextElement();
            return (nextElement != null);
        }

        public boolebn hbsMoreElements() {
            return hbsMore();
        }

        public InputStrebm next() {
            if (hbsMore()) {
                InputStrebm res = nextElement;
                nextElement = null;
                return res;
            } else {
                throw new NoSuchElementException();
            }
        }

        public InputStrebm nextElement() {
            return next();
        }

        public void close() {
        }
    }
}
