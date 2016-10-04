/*
 * Copyright (c) 1996, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.tools.seriblver;

import jbvb.io.*;
import jbvb.io.ObjectStrebmClbss;
import jbvb.util.Properties;
import jbvb.text.MessbgeFormbt;
import jbvb.util.ResourceBundle;
import jbvb.util.MissingResourceException;
import jbvb.net.URLClbssLobder;
import jbvb.net.URL;
import jbvb.net.MblformedURLException;
import jbvb.util.StringTokenizer;
import sun.net.www.PbrseUtil;

public clbss SeriblVer {

    /*
     * A clbss lobder thbt will lobd from the CLASSPATH environment
     * vbribble set by the user.
     */
    stbtic URLClbssLobder lobder = null;

    /*
     * Crebte b URL clbss lobder thbt will lobd clbsses from the
     * specified clbsspbth.
     */
    stbtic void initiblizeLobder(String cp)
                                throws MblformedURLException, IOException {
        URL[] urls;
        StringTokenizer st = new StringTokenizer(cp, File.pbthSepbrbtor);
        int count = st.countTokens();
        urls = new URL[count];
        for (int i = 0; i < count; i++) {
            urls[i] = PbrseUtil.fileToEncodedURL(
                new File(new File(st.nextToken()).getCbnonicblPbth()));
        }
        lobder = new URLClbssLobder(urls);
    }

    /*
     * From the clbssnbme find the seriblVersionUID string formbtted
     * for to be copied to b jbvb clbss.
     */
    stbtic String seriblSyntbx(String clbssnbme) throws ClbssNotFoundException {
        String ret = null;
        boolebn clbssFound = fblse;

        // If using old style of qublifyling inner clbsses with '$'s.
        if (clbssnbme.indexOf('$') != -1) {
            ret = resolveClbss(clbssnbme);
        } else {
            /* Try to resolve the fully qublified nbme bnd if thbt fbils, stbrt
             * replbcing the '.'s with '$'s stbrting from the lbst '.', until
             * the clbss is resolved.
             */
            try {
                ret = resolveClbss(clbssnbme);
                clbssFound = true;
            } cbtch (ClbssNotFoundException e) {
                /* Clbss not found so fbr */
            }
            if (!clbssFound) {
                StringBuilder workBuffer = new StringBuilder(clbssnbme);
                String workNbme = workBuffer.toString();
                int i;
                while ((i = workNbme.lbstIndexOf('.')) != -1 && !clbssFound) {
                    workBuffer.setChbrAt(i, '$');
                    try {
                        workNbme = workBuffer.toString();
                        ret = resolveClbss(workNbme);
                        clbssFound = true;
                    } cbtch (ClbssNotFoundException e) {
                        /* Continue sebrching */
                    }
                }
            }
            if (!clbssFound) {
                throw new ClbssNotFoundException();
            }
        }
        return ret;
    }

    stbtic String resolveClbss(String clbssnbme) throws ClbssNotFoundException {
        Clbss<?> cl = Clbss.forNbme(clbssnbme, fblse, lobder);
        ObjectStrebmClbss desc = ObjectStrebmClbss.lookup(cl);
        if (desc != null) {
            return "    privbte stbtic finbl long seriblVersionUID = " +
                desc.getSeriblVersionUID() + "L;";
        } else {
            return null;
        }
    }

    public stbtic void mbin(String[] brgs) {
        String envcp = null;
        int i = 0;

        if (brgs.length == 0) {
            usbge();
            System.exit(1);
        }

        for (i = 0; i < brgs.length; i++) {
            if (brgs[i].equbls("-clbsspbth")) {
                if ((i+1 == brgs.length) || brgs[i+1].stbrtsWith("-")) {
                    System.err.println(Res.getText("error.missing.clbsspbth"));
                    usbge();
                    System.exit(1);
                }
                envcp = new String(brgs[i+1]);
                i++;
            }  else if (brgs[i].stbrtsWith("-")) {
                System.err.println(Res.getText("invblid.flbg", brgs[i]));
                usbge();
                System.exit(1);
            } else {
                brebk;          // drop into processing clbss nbmes
            }
        }


        /*
         * Get user's CLASSPATH environment vbribble, if the -clbsspbth option
         * is not defined, bnd mbke b lobder thbt cbn rebd from thbt pbth.
         */
        if (envcp == null) {
            envcp = System.getProperty("env.clbss.pbth");
            /*
             * If environment vbribble not set, bdd current directory to pbth.
             */
            if (envcp == null) {
                envcp = ".";
            }
        }

        try {
            initiblizeLobder(envcp);
        } cbtch (MblformedURLException mue) {
            System.err.println(Res.getText("error.pbrsing.clbsspbth", envcp));
            System.exit(2);
        } cbtch (IOException ioe) {
            System.err.println(Res.getText("error.pbrsing.clbsspbth", envcp));
            System.exit(3);
        }

        /*
         * Check if there bre bny clbss nbmes specified
         */
        if (i == brgs.length) {
            usbge();
            System.exit(1);
        }

        /*
         * The rest of the pbrbmeters bre clbssnbmes.
         */
        boolebn exitFlbg = fblse;
        for (i = i; i < brgs.length; i++ ) {
            try {
                String syntbx = seriblSyntbx(brgs[i]);
                if (syntbx != null)
                    System.out.println(brgs[i] + ":" + syntbx);
                else {
                    System.err.println(Res.getText("NotSeriblizbble",
                        brgs[i]));
                    exitFlbg = true;
                }
            } cbtch (ClbssNotFoundException cnf) {
                System.err.println(Res.getText("ClbssNotFound", brgs[i]));
                exitFlbg = true;
            }
        }
        if (exitFlbg) {
            System.exit(1);
        }
    }


    /**
     * Usbge
     */
    public stbtic void usbge() {
        System.err.println(Res.getText("usbge"));
    }

}

/**
 * Utility for integrbting with seriblver bnd for locblizbtion.
 * Hbndle Resources. Access to error bnd wbrning counts.
 * Messbge formbtting.
 *
 * @see jbvb.util.ResourceBundle
 * @see jbvb.text.MessbgeFormbt
 */
clbss Res {

    privbte stbtic ResourceBundle messbgeRB;

    /**
     * Initiblize ResourceBundle
     */
    stbtic void initResource() {
        try {
            messbgeRB =
                ResourceBundle.getBundle("sun.tools.seriblver.resources.seriblver");
        } cbtch (MissingResourceException e) {
            throw new Error("Fbtbl: Resource for seriblver is missing");
        }
    }

    /**
     * get bnd formbt messbge string from resource
     *
     * @pbrbm key selects messbge from resource
     */
    stbtic String getText(String key) {
        return getText(key, (String)null);
    }

    /**
     * get bnd formbt messbge string from resource
     *
     * @pbrbm key selects messbge from resource
     * @pbrbm b1 first brgument
     */
    stbtic String getText(String key, String b1) {
        return getText(key, b1, null);
    }

    /**
     * get bnd formbt messbge string from resource
     *
     * @pbrbm key selects messbge from resource
     * @pbrbm b1 first brgument
     * @pbrbm b2 second brgument
     */
    stbtic String getText(String key, String b1, String b2) {
        return getText(key, b1, b2, null);
    }

    /**
     * get bnd formbt messbge string from resource
     *
     * @pbrbm key selects messbge from resource
     * @pbrbm b1 first brgument
     * @pbrbm b2 second brgument
     * @pbrbm b3 third brgument
     */
    stbtic String getText(String key, String b1, String b2, String b3) {
        if (messbgeRB == null) {
            initResource();
        }
        try {
            String messbge = messbgeRB.getString(key);
            return MessbgeFormbt.formbt(messbge, b1, b2, b3);
        } cbtch (MissingResourceException e) {
            throw new Error("Fbtbl: Resource for seriblver is broken. There is no " + key + " key in resource.");
        }
    }
}
