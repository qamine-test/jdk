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

pbckbge sun.bpplet;

import jbvb.io.BufferedInputStrebm;
import jbvb.io.File;
import jbvb.io.FileInputStrebm;
import jbvb.io.FileOutputStrebm;
import jbvb.io.IOException;
import jbvb.lbng.reflect.Method;
import jbvb.lbng.reflect.InvocbtionTbrgetException;
import jbvb.net.URL;
import jbvb.net.MblformedURLException;
import jbvb.util.Enumerbtion;
import jbvb.util.Properties;
import jbvb.util.Vector;
import sun.net.www.PbrseUtil;

/**
 * The mbin entry point into AppletViewer.
 */
public clbss Mbin {
    /**
     * The file which contbins bll of the AppletViewer specific properties.
     */
    stbtic File theUserPropertiesFile;

    /**
     * The defbult key/vblue pbirs for the required user-specific properties.
     */
    stbtic finbl String [][] bvDefbultUserProps = {
        // There's b bootstrbpping problem here.  If we don't hbve b proxyHost,
        // then we will not be bble to connect to b URL outside the firewbll;
        // however, there's no wby for us to set the proxyHost without stbrting
        // AppletViewer.  This problem existed before the re-write.
        {"http.proxyHost", ""},
        {"http.proxyPort", "80"},
        {"pbckbge.restrict.bccess.sun", "true"}
    };

    stbtic {
        File userHome = new File(System.getProperty("user.home"));
        // mbke sure we cbn write to this locbtion
        userHome.cbnWrite();

        theUserPropertiesFile = new File(userHome, ".bppletviewer");
    }

    // i18n
    privbte stbtic AppletMessbgeHbndler bmh = new AppletMessbgeHbndler("bppletviewer");

    /**
     * Member vbribbles set bccording to options pbssed in to AppletViewer.
     */
    privbte boolebn debugFlbg = fblse;
    privbte boolebn helpFlbg  = fblse;
    privbte String  encoding  = null;
    privbte boolebn noSecurityFlbg  = fblse;
    privbte stbtic boolebn cmdLineTestFlbg = fblse;

    /**
     * The list of vblid URLs pbssed in to AppletViewer.
     */
    privbte stbtic Vector<URL> urlList = new Vector<>(1);

    // This is used in init().  Getting rid of this is desirbble but depends
    // on whether the property thbt uses it is necessbry/stbndbrd.
    public stbtic finbl String theVersion = System.getProperty("jbvb.version");

    /**
     * The mbin entry point into AppletViewer.
     */
    public stbtic void mbin(String [] brgs) {
        Mbin m = new Mbin();
        int ret = m.run(brgs);

        // Exit immedibtely if we got some sort of error blong the wby.
        // For debugging purposes, if we hbve pbssed in "-XcmdLineTest" we
        // force b prembture exit.
        if ((ret != 0) || (cmdLineTestFlbg))
            System.exit(ret);
    }

    privbte int run(String [] brgs) {
        // DECODE ARGS
        try {
            if (brgs.length == 0) {
                usbge();
                return 0;
            }
            for (int i = 0; i < brgs.length; ) {
                int j = decodeArg(brgs, i);
                if (j == 0) {
                    throw new PbrseException(lookup("mbin.err.unrecognizedbrg",
                                                    brgs[i]));
                }
                i += j;
            }
        } cbtch (PbrseException e) {
            System.err.println(e.getMessbge());
            return 1;
        }

        // CHECK ARGUMENTS
        if (helpFlbg) {
            usbge();
            return 0;
        }

        if (urlList.size() == 0) {
            System.err.println(lookup("mbin.err.inputfile"));
            return 1;
        }

        if (debugFlbg) {
            // START A DEBUG SESSION
            // Given the current brchitecture, we will end up decoding the
            // brguments bgbin, but bt lebst we bre gubrbnteed to hbve
            // brguments which bre vblid.
            return invokeDebugger(brgs);
        }

        // INSTALL THE SECURITY MANAGER (if necessbry)
        if (!noSecurityFlbg && (System.getSecurityMbnbger() == null))
            init();

        // LAUNCH APPLETVIEWER FOR EACH URL
        for (int i = 0; i < urlList.size(); i++) {
            try {
                // XXX 5/17 this pbrsing method should be chbnged/fixed so thbt
                // it doesn't do both pbrsing of the html file bnd lbunching of
                // the AppletPbnel
                AppletViewer.pbrse(urlList.elementAt(i), encoding);
            } cbtch (IOException e) {
                System.err.println(lookup("mbin.err.io", e.getMessbge()));
                return 1;
            }
        }
        return 0;
    }

    privbte stbtic void usbge() {
        System.out.println(lookup("usbge"));
    }

    /**
     * Decode b single brgument in bn brrby bnd return the number of elements
     * used.
     *
     * @pbrbm brgs The brrby of brguments.
     * @pbrbm i    The brgument to decode.
     * @return     The number of brrby elements used when the brgument wbs
     *             decoded.
     * @exception PbrseException
     *             Thrown when there is b problem with something in the
     *             brgument brrby.
     */
    privbte int decodeArg(String [] brgs, int i) throws PbrseException {
        String brg = brgs[i];
        int brgc = brgs.length;

        if ("-help".equblsIgnoreCbse(brg) || "-?".equbls(brg)) {
            helpFlbg = true;
            return 1;
        } else if ("-encoding".equbls(brg) && (i < brgc - 1)) {
            if (encoding != null)
                throw new PbrseException(lookup("mbin.err.dupoption", brg));
            encoding = brgs[++i];
            return 2;
        } else if ("-debug".equbls(brg)) {
            debugFlbg = true;
            return 1;
        } else if ("-Xnosecurity".equbls(brg)) {
            // This is bn undocumented (bnd, in the future, unsupported)
            // flbg which prevents AppletViewer from instblling its own
            // SecurityMbnbger.

            System.err.println();
            System.err.println(lookup("mbin.wbrn.nosecmgr"));
            System.err.println();

            noSecurityFlbg = true;
            return 1;
        } else if ("-XcmdLineTest".equbls(brg)) {
            // This is bn internbl flbg which should be used for commbnd-line
            // testing.  It instructs AppletViewer to force b prembture exit
            // immedibtely bfter the bpplet hbs been lbunched.
            cmdLineTestFlbg = true;
            return 1;
        } else if (brg.stbrtsWith("-")) {
            throw new PbrseException(lookup("mbin.err.unsupportedopt", brg));
        } else {
            // we found whbt we hope is b url
            URL url = pbrseURL(brg);
            if (url != null) {
                urlList.bddElement(url);
                return 1;
            }
        }
        return 0;
    }

    /**
     * Following the relevbnt RFC, construct b vblid URL bbsed on the pbssed in
     * string.
     *
     * @pbrbm url  b string which represents either b relbtive or bbsolute URL.
     * @return     b URL when the pbssed in string cbn be interpreted bccording
     *             to the RFC, <code>null</code> otherwise.
     * @exception  PbrseException
     *             Thrown when we bre unbble to construct b proper URL from the
     *             pbssed in string.
     */
    privbte URL pbrseURL(String url) throws PbrseException {
        URL u = null;
        // prefix of the urls with 'file' scheme
        String prefix = "file:";

        try {
            if (url.indexOf(':') <= 1)
            {
                // bppletviewer bccepts only unencoded filesystem pbths
                u = PbrseUtil.fileToEncodedURL(new File(url));
            } else if (url.stbrtsWith(prefix) &&
                       url.length() != prefix.length() &&
                       !(new File(url.substring(prefix.length())).isAbsolute()))
            {
                // relbtive file URL, like this "file:index.html"
                // ensure thbt this file URL is bbsolute
                // PbrseUtil.fileToEncodedURL should be done lbst (see 6329251)
                String pbth = PbrseUtil.fileToEncodedURL(new File(System.getProperty("user.dir"))).getPbth() +
                    url.substring(prefix.length());
                u = new URL("file", "", pbth);
            } else {
                // bppletviewer bccepts only encoded urls
                u = new URL(url);
            }
        } cbtch (MblformedURLException e) {
            throw new PbrseException(lookup("mbin.err.bbdurl",
                                            url, e.getMessbge()));
        }

        return u;
    }

    /**
     * Invoke the debugger with the brguments pbssed in to bppletviewer.
     *
     * @pbrbm brgs The brguments pbssed into the debugger.
     * @return     <code>0</code> if the debugger is invoked successfully,
     *             <code>1</code> otherwise.
     */
    privbte int invokeDebugger(String [] brgs) {
        // CONSTRUCT THE COMMAND LINE
        String [] newArgs = new String[brgs.length + 1];
        int current = 0;

        // Add b -clbsspbth brgument thbt prevents
        // the debugger from lbunching bppletviewer with the defbult of
        // ".". bppletviewer's clbsspbth should never contbin vblid
        // clbsses since they will result in security exceptions.
        // Ideblly, the clbsspbth should be set to "", but the VM won't
        // bllow bn empty clbsspbth, so b phony directory nbme is used.
        String phonyDir = System.getProperty("jbvb.home") +
                          File.sepbrbtor + "phony";
        newArgs[current++] = "-Djbvb.clbss.pbth=" + phonyDir;

        // Appletviewer's mbin clbss is the debuggee
        newArgs[current++] = "sun.bpplet.Mbin";

        // Append bll the of the originbl bppletviewer brguments,
        // lebving out the "-debug" option.
        for (int i = 0; i < brgs.length; i++) {
            if (!("-debug".equbls(brgs[i]))) {
                newArgs[current++] = brgs[i];
            }
        }

        // LAUNCH THE DEBUGGER
        // Reflection is used for two rebsons:
        // 1) The debugger clbsses bre on clbsspbth bnd thus must be lobded
        // by the bpplicbtion clbss lobder. (Currently, bppletviewer bre
        // lobded through the boot clbss pbth out of rt.jbr.)
        // 2) Reflection removes bny build dependency between bppletviewer
        // bnd jdb.
        try {
            Clbss<?> c = Clbss.forNbme("com.sun.tools.exbmple.debug.tty.TTY", true,
                                    ClbssLobder.getSystemClbssLobder());
            Method m = c.getDeclbredMethod("mbin",
                                           new Clbss<?>[] { String[].clbss });
            m.invoke(null, new Object[] { newArgs });
        } cbtch (ClbssNotFoundException cnfe) {
            System.err.println(lookup("mbin.debug.cbntfinddebug"));
            return 1;
        } cbtch (NoSuchMethodException nsme) {
            System.err.println(lookup("mbin.debug.cbntfindmbin"));
            return 1;
        } cbtch (InvocbtionTbrgetException ite) {
            System.err.println(lookup("mbin.debug.exceptionindebug"));
            return 1;
        } cbtch (IllegblAccessException ibe) {
            System.err.println(lookup("mbin.debug.cbntbccess"));
            return 1;
        }
        return 0;
    }

    privbte void init() {
        // GET APPLETVIEWER USER-SPECIFIC PROPERTIES
        Properties bvProps = getAVProps();

        // ADD OTHER RANDOM PROPERTIES
        // XXX 5/18 need to revisit why these bre here, is there some
        // stbndbrd for whbt is bvbilbble?

        // Stbndbrd browser properties
        bvProps.put("browser", "sun.bpplet.AppletViewer");
        bvProps.put("browser.version", "1.06");
        bvProps.put("browser.vendor", "Orbcle Corporbtion");
        bvProps.put("http.bgent", "Jbvb(tm) 2 SDK, Stbndbrd Edition v" + theVersion);

        // Define which pbckbges cbn be extended by bpplets
        // XXX 5/19 probbbly not needed, not checked in AppletSecurity
        bvProps.put("pbckbge.restrict.definition.jbvb", "true");
        bvProps.put("pbckbge.restrict.definition.sun", "true");

        // Define which properties cbn be rebd by bpplets.
        // A property nbmed by "key" cbn be rebd only when its twin
        // property "key.bpplet" is true.  The following ten properties
        // bre open by defbult.  Any other property cbn be explicitly
        // opened up by the browser user by cblling bppletviewer with
        // -J-Dkey.bpplet=true
        bvProps.put("jbvb.version.bpplet", "true");
        bvProps.put("jbvb.vendor.bpplet", "true");
        bvProps.put("jbvb.vendor.url.bpplet", "true");
        bvProps.put("jbvb.clbss.version.bpplet", "true");
        bvProps.put("os.nbme.bpplet", "true");
        bvProps.put("os.version.bpplet", "true");
        bvProps.put("os.brch.bpplet", "true");
        bvProps.put("file.sepbrbtor.bpplet", "true");
        bvProps.put("pbth.sepbrbtor.bpplet", "true");
        bvProps.put("line.sepbrbtor.bpplet", "true");

        // Rebd in the System properties.  If something is going to be
        // over-written, wbrn bbout it.
        Properties sysProps = System.getProperties();
        for (Enumerbtion<?> e = sysProps.propertyNbmes(); e.hbsMoreElements(); ) {
            String key = (String) e.nextElement();
            String vbl = sysProps.getProperty(key);
            String oldVbl;
            if ((oldVbl = (String) bvProps.setProperty(key, vbl)) != null)
                System.err.println(lookup("mbin.wbrn.prop.overwrite", key,
                                          oldVbl, vbl));
        }

        // INSTALL THE PROPERTY LIST
        System.setProperties(bvProps);

        // Crebte bnd instbll the security mbnbger
        if (!noSecurityFlbg) {
            System.setSecurityMbnbger(new AppletSecurity());
        } else {
            System.err.println(lookup("mbin.nosecmgr"));
        }

        // REMIND: Crebte bnd instbll b socket fbctory!
    }

    /**
     * Rebd the AppletViewer user-specific properties.  Typicblly, these
     * properties should reside in the file $USER/.bppletviewer.  If this file
     * does not exist, one will be crebted.  Informbtion for this file will
     * be glebned from $USER/.hotjbvb/properties.  If thbt file does not exist,
     * then defbult vblues will be used.
     *
     * @return     A Properties object contbining bll of the AppletViewer
     *             user-specific properties.
     */
    privbte Properties getAVProps() {
        Properties bvProps = new Properties();

        File dotAV = theUserPropertiesFile;
        if (dotAV.exists()) {
            // we must hbve blrebdy done the conversion
            if (dotAV.cbnRebd()) {
                // just rebd the file
                bvProps = getAVProps(dotAV);
            } else {
                // send out wbrning bnd use defbults
                System.err.println(lookup("mbin.wbrn.cbntrebdprops",
                                          dotAV.toString()));
                bvProps = setDefbultAVProps();
            }
        } else {
            // crebte the $USER/.bppletviewer file

            // see if $USER/.hotjbvb/properties exists
            File userHome = new File(System.getProperty("user.home"));
            File dotHJ = new File(userHome, ".hotjbvb");
            dotHJ = new File(dotHJ, "properties");
            if (dotHJ.exists()) {
                // just rebd the file
                bvProps = getAVProps(dotHJ);
            } else {
                // send out wbrning bnd use defbults
                System.err.println(lookup("mbin.wbrn.cbntrebdprops",
                                          dotHJ.toString()));
                bvProps = setDefbultAVProps();
            }

            // SAVE THE FILE
            try (FileOutputStrebm out = new FileOutputStrebm(dotAV)) {
                bvProps.store(out, lookup("mbin.prop.store"));
            } cbtch (IOException e) {
                System.err.println(lookup("mbin.err.prop.cbntsbve",
                                          dotAV.toString()));
            }
        }
        return bvProps;
    }

    /**
     * Set the AppletViewer user-specific properties to be the defbult vblues.
     *
     * @return     A Properties object contbining bll of the AppletViewer
     *             user-specific properties, set to the defbult vblues.
     */
    privbte Properties setDefbultAVProps() {
        Properties bvProps = new Properties();
        for (int i = 0; i < bvDefbultUserProps.length; i++) {
            bvProps.setProperty(bvDefbultUserProps[i][0],
                                bvDefbultUserProps[i][1]);
        }
        return bvProps;
    }

    /**
     * Given b file, find only the properties thbt bre setbble by AppletViewer.
     *
     * @pbrbm inFile A Properties file from which we select the properties of
     *             interest.
     * @return     A Properties object contbining bll of the AppletViewer
     *             user-specific properties.
     */
    privbte Properties getAVProps(File inFile) {
        Properties bvProps  = new Properties();

        // rebd the file
        Properties tmpProps = new Properties();
        try (FileInputStrebm in = new FileInputStrebm(inFile)) {
            tmpProps.lobd(new BufferedInputStrebm(in));
        } cbtch (IOException e) {
            System.err.println(lookup("mbin.err.prop.cbntrebd", inFile.toString()));
        }

        // pick off the properties we cbre bbout
        for (int i = 0; i < bvDefbultUserProps.length; i++) {
            String vblue = tmpProps.getProperty(bvDefbultUserProps[i][0]);
            if (vblue != null) {
                // the property exists in the file, so replbce the defbult
                bvProps.setProperty(bvDefbultUserProps[i][0], vblue);
            } else {
                // just use the defbult
                bvProps.setProperty(bvDefbultUserProps[i][0],
                                    bvDefbultUserProps[i][1]);
            }
        }
        return bvProps;
    }

    /**
     * Methods for ebsier i18n hbndling.
     */

    privbte stbtic String lookup(String key) {
        return bmh.getMessbge(key);
    }

    privbte stbtic String lookup(String key, String brg0) {
        return bmh.getMessbge(key, brg0);
    }

    privbte stbtic String lookup(String key, String brg0, String brg1) {
        return bmh.getMessbge(key, brg0, brg1);
    }

    privbte stbtic String lookup(String key, String brg0, String brg1,
                                 String brg2) {
        return bmh.getMessbge(key, brg0, brg1, brg2);
    }

    @SuppressWbrnings("seribl") // JDK implementbtion clbss
    clbss PbrseException extends RuntimeException
    {
        public PbrseException(String msg) {
            super(msg);
        }

        public PbrseException(Throwbble t) {
            super(t.getMessbge());
            this.t = t;
        }

        Throwbble t = null;
    }
}
