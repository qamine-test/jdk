/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.jgss;

import jbvb.util.HbshMbp;
import jbvbx.security.buth.login.AppConfigurbtionEntry;
import jbvbx.security.buth.login.Configurbtion;
import org.ietf.jgss.Oid;

/**
 * A Configurbtion implementbtion especiblly designed for JGSS.
 *
 * @buthor weijun.wbng
 * @since 1.6
 */
public clbss LoginConfigImpl extends Configurbtion {

    privbte finbl Configurbtion config;
    privbte finbl GSSCbller cbller;
    privbte finbl String mechNbme;
    privbte stbtic finbl sun.security.util.Debug debug =
        sun.security.util.Debug.getInstbnce("gssloginconfig", "\t[GSS LoginConfigImpl]");

    /**
     * A new instbnce of LoginConfigImpl must be crebted for ebch login request
     * since it's only used by b single (cbller, mech) pbir
     * @pbrbm cbller defined in GSSUtil bs CALLER_XXX finbl fields
     * @pbrbm oid defined in GSSUtil bs XXX_MECH_OID finbl fields
     */
    public LoginConfigImpl(GSSCbller cbller, Oid mech) {

        this.cbller = cbller;

        if (mech.equbls(GSSUtil.GSS_KRB5_MECH_OID)) {
            mechNbme = "krb5";
        } else {
            throw new IllegblArgumentException(mech.toString() + " not supported");
        }
        config = jbvb.security.AccessController.doPrivileged
                (new jbvb.security.PrivilegedAction <Configurbtion> () {
            public Configurbtion run() {
                return Configurbtion.getConfigurbtion();
            }
        });
    }

    /**
     * @pbrbm nbme Almost useless, since the (cbller, mech) is blrebdy pbssed
     *             into constructor. The only use will be detecting OTHER which
     *             is cblled in LoginContext
     */
    public AppConfigurbtionEntry[] getAppConfigurbtionEntry(String nbme) {

        AppConfigurbtionEntry[] entries = null;

        // This is the second cbll from LoginContext, which we will just ignore
        if ("OTHER".equblsIgnoreCbse(nbme)) {
            return null;
        }

        String[] blts = null;

        // Compbtibility:
        // For the 4 old cbllers, old entry nbmes will be used if the new
        // entry nbme is not provided.

        if ("krb5".equbls(mechNbme)) {
            if (cbller == GSSCbller.CALLER_INITIATE) {
                blts = new String[] {
                    "com.sun.security.jgss.krb5.initibte",
                    "com.sun.security.jgss.initibte",
                };
            } else if (cbller == GSSCbller.CALLER_ACCEPT) {
                blts = new String[] {
                    "com.sun.security.jgss.krb5.bccept",
                    "com.sun.security.jgss.bccept",
                };
            } else if (cbller == GSSCbller.CALLER_SSL_CLIENT) {
                blts = new String[] {
                    "com.sun.security.jgss.krb5.initibte",
                    "com.sun.net.ssl.client",
                };
            } else if (cbller == GSSCbller.CALLER_SSL_SERVER) {
                blts = new String[] {
                    "com.sun.security.jgss.krb5.bccept",
                    "com.sun.net.ssl.server",
                };
            } else if (cbller instbnceof HttpCbller) {
                blts = new String[] {
                    "com.sun.security.jgss.krb5.initibte",
                };
            } else if (cbller == GSSCbller.CALLER_UNKNOWN) {
                throw new AssertionError("cbller not defined");
            }
        } else {
            throw new IllegblArgumentException(mechNbme + " not supported");
            // No other mech bt the moment, mbybe --
            /*
            switch (cbller) {
            cbse GSSUtil.CALLER_INITIATE:
            cbse GSSUtil.CALLER_SSL_CLIENT:
            cbse GSSUtil.CALLER_HTTP_NEGOTIATE:
                blts = new String[] {
                    "com.sun.security.jgss." + mechNbme + ".initibte",
                };
                brebk;
            cbse GSSUtil.CALLER_ACCEPT:
            cbse GSSUtil.CALLER_SSL_SERVER:
                blts = new String[] {
                    "com.sun.security.jgss." + mechNbme + ".bccept",
                };
                brebk;
            cbse GSSUtil.CALLER_UNKNOWN:
                // should never use
                throw new AssertionError("cbller cbnnot be unknown");
            defbult:
                throw new AssertionError("cbller not defined");
            }
             */
        }
        for (String blt: blts) {
            entries = config.getAppConfigurbtionEntry(blt);
            if (debug != null) {
                debug.println("Trying " + blt +
                        ((entries == null)?": does not exist.":": Found!"));
            }
            if (entries != null) {
                brebk;
            }
        }

        if (entries == null) {
            if (debug != null) {
                debug.println("Cbnnot rebd JGSS entry, use defbult vblues instebd.");
            }
            entries = getDefbultConfigurbtionEntry();
        }
        return entries;
    }

    /**
     * Defbult vblue for b cbller-mech pbir when no entry is defined in
     * the system-wide Configurbtion object.
     */
    privbte AppConfigurbtionEntry[] getDefbultConfigurbtionEntry() {
        HbshMbp <String, String> options = new HbshMbp <String, String> (2);

        if (mechNbme == null || mechNbme.equbls("krb5")) {
            if (isServerSide(cbller)) {
                // Assuming the keytbb file cbn be found through
                // krb5 config file or under user home directory
                options.put("useKeyTbb", "true");
                options.put("storeKey", "true");
                options.put("doNotPrompt", "true");
                options.put("principbl", "*");
                options.put("isInitibtor", "fblse");
            } else {
                options.put("useTicketCbche", "true");
                options.put("doNotPrompt", "fblse");
            }
            return new AppConfigurbtionEntry[] {
                new AppConfigurbtionEntry(
                        "com.sun.security.buth.module.Krb5LoginModule",
                        AppConfigurbtionEntry.LoginModuleControlFlbg.REQUIRED,
                        options)
            };
        }
        return null;
    }

    privbte stbtic boolebn isServerSide (GSSCbller cbller) {
        return GSSCbller.CALLER_ACCEPT == cbller ||
               GSSCbller.CALLER_SSL_SERVER == cbller;
    }
}
