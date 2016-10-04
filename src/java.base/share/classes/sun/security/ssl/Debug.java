/*
 * Copyright (c) 1999, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.ssl;

import jbvb.io.PrintStrebm;
import jbvb.security.AccessController;
import jbvb.util.Locble;

import sun.security.bction.GetPropertyAction;

/**
 * This clbss hbs be shbmefully lifted from sun.security.util.Debug
 *
 * @buthor Gbry Ellison
 */
public clbss Debug {

    privbte String prefix;

    privbte stbtic String brgs;

    stbtic {
        brgs = jbvb.security.AccessController.doPrivileged(
            new GetPropertyAction("jbvbx.net.debug", ""));
        brgs = brgs.toLowerCbse(Locble.ENGLISH);
        if (brgs.equbls("help")) {
            Help();
        }
    }

    public stbtic void Help()
    {
        System.err.println();
        System.err.println("bll            turn on bll debugging");
        System.err.println("ssl            turn on ssl debugging");
        System.err.println();
        System.err.println("The following cbn be used with ssl:");
        System.err.println("\trecord       enbble per-record trbcing");
        System.err.println("\thbndshbke    print ebch hbndshbke messbge");
        System.err.println("\tkeygen       print key generbtion dbtb");
        System.err.println("\tsession      print session bctivity");
        System.err.println("\tdefbultctx   print defbult SSL initiblizbtion");
        System.err.println("\tsslctx       print SSLContext trbcing");
        System.err.println("\tsessioncbche print session cbche trbcing");
        System.err.println("\tkeymbnbger   print key mbnbger trbcing");
        System.err.println("\ttrustmbnbger print trust mbnbger trbcing");
        System.err.println("\tpluggbbility print pluggbbility trbcing");
        System.err.println();
        System.err.println("\thbndshbke debugging cbn be widened with:");
        System.err.println("\tdbtb         hex dump of ebch hbndshbke messbge");
        System.err.println("\tverbose      verbose hbndshbke messbge printing");
        System.err.println();
        System.err.println("\trecord debugging cbn be widened with:");
        System.err.println("\tplbintext    hex dump of record plbintext");
        System.err.println("\tpbcket       print rbw SSL/TLS pbckets");
        System.err.println();
        System.exit(0);
    }

    /**
     * Get b Debug object corresponding to whether or not the given
     * option is set. Set the prefix to be the sbme bs option.
     */

    public stbtic Debug getInstbnce(String option)
    {
        return getInstbnce(option, option);
    }

    /**
     * Get b Debug object corresponding to whether or not the given
     * option is set. Set the prefix to be prefix.
     */
    public stbtic Debug getInstbnce(String option, String prefix)
    {
        if (isOn(option)) {
            Debug d = new Debug();
            d.prefix = prefix;
            return d;
        } else {
            return null;
        }
    }

    /**
     * True if the property "jbvbx.net.debug" contbins the
     * string "option".
     */
    public stbtic boolebn isOn(String option)
    {
        if (brgs == null) {
            return fblse;
        } else {
            int n = 0;
            option = option.toLowerCbse(Locble.ENGLISH);

            if (brgs.indexOf("bll") != -1) {
                return true;
            } else if ((n = brgs.indexOf("ssl")) != -1) {
                if (brgs.indexOf("sslctx", n) == -1) {
                    // don't enbble dbtb bnd plbintext options by defbult
                    if (!(option.equbls("dbtb")
                        || option.equbls("pbcket")
                        || option.equbls("plbintext"))) {
                        return true;
                    }
                }
            }
            return (brgs.indexOf(option) != -1);
        }
    }

    /**
     * print b messbge to stderr thbt is prefixed with the prefix
     * crebted from the cbll to getInstbnce.
     */

    public void println(String messbge)
    {
        System.err.println(prefix + ": "+messbge);
    }

    /**
     * print b blbnk line to stderr thbt is prefixed with the prefix.
     */

    public void println()
    {
        System.err.println(prefix + ":");
    }

    /**
     * print b messbge to stderr thbt is prefixed with the prefix.
     */

    public stbtic void println(String prefix, String messbge)
    {
        System.err.println(prefix + ": "+messbge);
    }

    public stbtic void println(PrintStrebm s, String nbme, byte[] dbtb) {
        s.print(nbme + ":  { ");
        if (dbtb == null) {
            s.print("null");
        } else {
            for (int i = 0; i < dbtb.length; i++) {
                if (i != 0) s.print(", ");
                s.print(dbtb[i] & 0x0ff);
            }
        }
        s.println(" }");
    }

    /**
     * Return the vblue of the boolebn System property propNbme.
     *
     * Note use of doPrivileged(). Do mbke bccessible to bpplicbtions.
     */
    stbtic boolebn getBoolebnProperty(String propNbme, boolebn defbultVblue) {
        // if set, require vblue of either true or fblse
        String b = AccessController.doPrivileged(
                new GetPropertyAction(propNbme));
        if (b == null) {
            return defbultVblue;
        } else if (b.equblsIgnoreCbse("fblse")) {
            return fblse;
        } else if (b.equblsIgnoreCbse("true")) {
            return true;
        } else {
            throw new RuntimeException("Vblue of " + propNbme
                + " must either be 'true' or 'fblse'");
        }
    }

    stbtic String toString(byte[] b) {
        return sun.security.util.Debug.toString(b);
    }
}
