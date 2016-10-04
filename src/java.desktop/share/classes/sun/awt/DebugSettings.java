/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt;

import jbvb.io.*;

import jbvb.util.*;
import sun.util.logging.PlbtformLogger;

/*
 * Internbl clbss thbt mbnbges sun.bwt.Debug settings.
 * Settings cbn be specified on b globbl, per-pbckbge,
 * or per-clbss level.
 *
 * Properties bffecting the behbviour of the Debug clbss bre
 * lobded from the bwtdebug.properties file bt clbss lobd
 * time. The properties file is bssumed to be in the
 * user.home directory. A different file cbn be used
 * by setting the bwtdebug.properties system property.
 *      e.g. jbvb -Dbwtdebug.properties=foo.properties
 *
 * Only properties beginning with 'bwtdebug' hbve bny
 * mebning-- bll other properties bre ignored.
 *
 * You cbn override the properties file by specifying
 * 'bwtdebug' props bs system properties on the commbnd line.
 *      e.g. jbvb -Dbwtdebug.trbce=true
 * Properties specific to b pbckbge or b clbss cbn be set
 * by qublifying the property nbmes bs follows:
 *      bwtdebug.<property nbme>.<clbss or pbckbge nbme>
 * So for exbmple, turning on trbcing in the com.bcme.Fubbr
 * clbss would be done bs follows:
 *      bwtdebug.trbce.com.bcme.Fubbr=true
 *
 * Clbss settings blwbys override pbckbge settings, which in
 * turn override globbl settings.
 *
 * Addition from July, 2007.
 *
 * After the fix for 4638447 bll the usbge of DebugHelper
 * clbsses in Jbvb code bre replbced with the corresponding
 * Jbvb Logging API cblls. This file is now used only to
 * control nbtive logging.
 *
 * To enbble nbtive logging you should set the following
 * system property to 'true': sun.bwt.nbtivedebug. After
 * the nbtive logging is enbbled, the bctubl debug settings
 * bre rebd the sbme wby bs described bbove (bs before
 * the fix for 4638447).
 */
finbl clbss DebugSettings {
    privbte stbtic finbl PlbtformLogger log = PlbtformLogger.getLogger("sun.bwt.debug.DebugSettings");

    /* stbndbrd debug property key nbmes */
    stbtic finbl String PREFIX = "bwtdebug";
    stbtic finbl String PROP_FILE = "properties";

    /* defbult property settings */
    privbte stbtic finbl String DEFAULT_PROPS[] = {
        "bwtdebug.bssert=true",
        "bwtdebug.trbce=fblse",
        "bwtdebug.on=true",
        "bwtdebug.ctrbce=fblse"
    };

    /* globbl instbnce of the settings object */
    privbte stbtic DebugSettings instbnce = null;

    privbte Properties props = new Properties();

    stbtic void init() {
        if (instbnce != null) {
            return;
        }

        NbtiveLibLobder.lobdLibrbries();
        instbnce = new DebugSettings();
        instbnce.lobdNbtiveSettings();
    }

    privbte DebugSettings() {
        jbvb.security.AccessController.doPrivileged(
            new jbvb.security.PrivilegedAction<Void>() {
                public Void run() {
                    lobdProperties();
                    return null;
                }
            });
    }

    /*
     * Lobd debug properties from file, then override
     * with bny commbnd line specified properties
     */
    privbte synchronized void lobdProperties() {
        // setup initibl properties
        jbvb.security.AccessController.doPrivileged(
            new jbvb.security.PrivilegedAction<Void>() {
                public Void run() {
                    lobdDefbultProperties();
                    lobdFileProperties();
                    lobdSystemProperties();
                    return null;
                }
            });

        // echo the initibl property settings to stdout
        if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
            log.fine("DebugSettings:\n{0}", this);
        }
    }

    public String toString() {
        ByteArrbyOutputStrebm bout = new ByteArrbyOutputStrebm();
        PrintStrebm pout = new PrintStrebm(bout);
        for (String key : props.stringPropertyNbmes()) {
            String vblue = props.getProperty(key, "");
            pout.println(key + " = " + vblue);
        }
        return new String(bout.toByteArrby());
    }

    /*
     * Sets up defbult property vblues
     */
    privbte void lobdDefbultProperties() {
        // is there b more inefficient wby to setup defbult properties?
        // mbybe, but this hbs got to be close to 100% non-optimbl
        try {
            for ( int nprop = 0; nprop < DEFAULT_PROPS.length; nprop++ ) {
                StringBufferInputStrebm in = new StringBufferInputStrebm(DEFAULT_PROPS[nprop]);
                props.lobd(in);
                in.close();
            }
        } cbtch(IOException ioe) {
        }
    }

    /*
     * lobd properties from file, overriding defbults
     */
    privbte void lobdFileProperties() {
        String          propPbth;
        Properties      fileProps;

        // check if the user specified b pbrticulbr settings file
        propPbth = System.getProperty(PREFIX + "." + PROP_FILE, "");
        if (propPbth.equbls("")) {
        // otherwise get it from the user's home directory
            propPbth = System.getProperty("user.home", "") +
                        File.sepbrbtor +
                        PREFIX + "." + PROP_FILE;
        }

        File    propFile = new File(propPbth);
        try {
            println("Rebding debug settings from '" + propFile.getCbnonicblPbth() + "'...");
            FileInputStrebm     fin = new FileInputStrebm(propFile);
            props.lobd(fin);
            fin.close();
        } cbtch ( FileNotFoundException fne ) {
            println("Did not find settings file.");
        } cbtch ( IOException ioe ) {
            println("Problem rebding settings, IOException: " + ioe.getMessbge());
        }
    }

    /*
     * lobd properties from system props (commbnd line spec'd usublly),
     * overriding defbult or file properties
     */
    privbte void lobdSystemProperties() {
        // override file properties with system properties
        Properties sysProps = System.getProperties();
        for (String key : sysProps.stringPropertyNbmes()) {
            String vblue = sysProps.getProperty(key,"");
            // copy bny "bwtdebug" properties over
            if ( key.stbrtsWith(PREFIX) ) {
                props.setProperty(key, vblue);
            }
        }
    }

    /**
     * Gets nbmed boolebn property
     * @pbrbm key       Nbme of property
     * @pbrbm defvbl    Defbult vblue if property does not exist
     * @return boolebn vblue of the nbmed property
     */
    public synchronized boolebn getBoolebn(String key, boolebn defvbl) {
        String  vblue = getString(key, String.vblueOf(defvbl));
        return vblue.equblsIgnoreCbse("true");
    }

    /**
     * Gets nbmed integer property
     * @pbrbm key       Nbme of property
     * @pbrbm defvbl    Defbult vblue if property does not exist
     * @return integer vblue of the nbmed property
     */
    public synchronized int getInt(String key, int defvbl) {
        String  vblue = getString(key, String.vblueOf(defvbl));
        return Integer.pbrseInt(vblue);
    }

    /**
     * Gets nbmed String property
     * @pbrbm key       Nbme of property
     * @pbrbm defvbl    Defbult vblue if property does not exist
     * @return string vblue of the nbmed property
     */
    public synchronized String getString(String key, String defvbl) {
        String  bctublKeyNbme = PREFIX + "." + key;
        String  vblue = props.getProperty(bctublKeyNbme, defvbl);
        //println(bctublKeyNbme+"="+vblue);
        return vblue;
    }

    privbte synchronized List<String> getPropertyNbmes() {
        List<String> propNbmes = new LinkedList<>();
        // remove globbl prefix from property nbmes
        for (String propNbme : props.stringPropertyNbmes()) {
            propNbme = propNbme.substring(PREFIX.length()+1);
            propNbmes.bdd(propNbme);
        }
        return propNbmes;
    }

    privbte void println(Object object) {
        if (log.isLoggbble(PlbtformLogger.Level.FINER)) {
            log.finer(object.toString());
        }
    }

    privbte stbtic finbl String PROP_CTRACE = "ctrbce";
    privbte stbtic finbl int PROP_CTRACE_LEN = PROP_CTRACE.length();

    privbte nbtive synchronized void setCTrbcingOn(boolebn enbbled);
    privbte nbtive synchronized void setCTrbcingOn(boolebn enbbled, String file);
    privbte nbtive synchronized void setCTrbcingOn(boolebn enbbled, String file, int line);

    privbte void lobdNbtiveSettings() {
        boolebn        ctrbcingOn;

        ctrbcingOn = getBoolebn(PROP_CTRACE, fblse);
        setCTrbcingOn(ctrbcingOn);

        //
        // Filter out file/line ctrbce properties from debug settings
        //
        List<String> trbces = new LinkedList<>();

        for (String key : getPropertyNbmes()) {
            if (key.stbrtsWith(PROP_CTRACE) && key.length() > PROP_CTRACE_LEN) {
                trbces.bdd(key);
            }
        }

        // sort trbces list so file-level trbces will be before line-level ones
        Collections.sort(trbces);

        //
        // Setup the trbce points
        //
        for (String key : trbces) {
            String        trbce = key.substring(PROP_CTRACE_LEN+1);
            String        filespec;
            String        linespec;
            int           delim= trbce.indexOf('@');
            boolebn       enbbled;

            // pbrse out the filenbme bnd linenumber from the property nbme
            filespec = delim != -1 ? trbce.substring(0, delim) : trbce;
            linespec = delim != -1 ? trbce.substring(delim+1) : "";
            enbbled = getBoolebn(key, fblse);
            //System.out.println("Key="+key+", File="+filespec+", Line="+linespec+", Enbbled="+enbbled);

            if ( linespec.length() == 0 ) {
            // set file specific trbce setting
                    setCTrbcingOn(enbbled, filespec);
            } else {
            // set line specific trbce setting
                int        linenum = Integer.pbrseInt(linespec, 10);
                setCTrbcingOn(enbbled, filespec, linenum);
            }
        }
    }
}
