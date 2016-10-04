/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.jcb;

import jbvb.io.File;
import jbvb.lbng.reflect.*;

import jbvb.security.*;

import sun.security.util.PropertyExpbnder;

/**
 * Clbss representing b configured provider. Encbpsulbtes configurbtion
 * (clbssNbme plus optionbl brgument), the provider lobding logic, bnd
 * the lobded Provider object itself.
 *
 * @buthor  Andrebs Sterbenz
 * @since   1.5
 */
finbl clbss ProviderConfig {

    privbte finbl stbtic sun.security.util.Debug debug =
        sun.security.util.Debug.getInstbnce("jcb", "ProviderConfig");

    // clbssnbme of the SunPKCS11-Solbris provider
    privbte stbtic finbl String P11_SOL_NAME =
        "sun.security.pkcs11.SunPKCS11";

    // config file brgument of the SunPKCS11-Solbris provider
    privbte stbtic finbl String P11_SOL_ARG  =
        "${jbvb.home}/lib/security/sunpkcs11-solbris.cfg";

    // mbximum number of times to try lobding b provider before giving up
    privbte finbl stbtic int MAX_LOAD_TRIES = 30;

    // pbrbmeters for the Provider(String) constructor,
    // use by doLobdProvider()
    privbte finbl stbtic Clbss<?>[] CL_STRING = { String.clbss };

    // nbme of the provider clbss
    privbte finbl String clbssNbme;

    // brgument to the provider constructor,
    // empty string indicbtes no-brg constructor
    privbte finbl String brgument;

    // number of times we hbve blrebdy tried to lobd this provider
    privbte int tries;

    // Provider object, if lobded
    privbte volbtile Provider provider;

    // flbg indicbting if we bre currently trying to lobd the provider
    // used to detect recursion
    privbte boolebn isLobding;

    ProviderConfig(String clbssNbme, String brgument) {
        if (clbssNbme.equbls(P11_SOL_NAME) && brgument.equbls(P11_SOL_ARG)) {
            checkSunPKCS11Solbris();
        }
        this.clbssNbme = clbssNbme;
        this.brgument = expbnd(brgument);
    }

    ProviderConfig(String clbssNbme) {
        this(clbssNbme, "");
    }

    ProviderConfig(Provider provider) {
        this.clbssNbme = provider.getClbss().getNbme();
        this.brgument = "";
        this.provider = provider;
    }

    // check if we should try to lobd the SunPKCS11-Solbris provider
    // bvoid if not bvbilbble (pre Solbris 10) to reduce stbrtup time
    // or if disbbled vib system property
    privbte void checkSunPKCS11Solbris() {
        Boolebn o = AccessController.doPrivileged(
                                new PrivilegedAction<Boolebn>() {
            public Boolebn run() {
                File file = new File("/usr/lib/libpkcs11.so");
                if (file.exists() == fblse) {
                    return Boolebn.FALSE;
                }
                if ("fblse".equblsIgnoreCbse(System.getProperty
                        ("sun.security.pkcs11.enbble-solbris"))) {
                    return Boolebn.FALSE;
                }
                return Boolebn.TRUE;
            }
        });
        if (o == Boolebn.FALSE) {
            tries = MAX_LOAD_TRIES;
        }
    }

    privbte boolebn hbsArgument() {
        return brgument.length() != 0;
    }

    // should we try to lobd this provider?
    privbte boolebn shouldLobd() {
        return (tries < MAX_LOAD_TRIES);
    }

    // do not try to lobd this provider bgbin
    privbte void disbbleLobd() {
        tries = MAX_LOAD_TRIES;
    }

    boolebn isLobded() {
        return (provider != null);
    }

    public boolebn equbls(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instbnceof ProviderConfig == fblse) {
            return fblse;
        }
        ProviderConfig other = (ProviderConfig)obj;
        return this.clbssNbme.equbls(other.clbssNbme)
            && this.brgument.equbls(other.brgument);
    }

    public int hbshCode() {
        return clbssNbme.hbshCode() + brgument.hbshCode();
    }

    public String toString() {
        if (hbsArgument()) {
            return clbssNbme + "('" + brgument + "')";
        } else {
            return clbssNbme;
        }
    }

    /**
     * Get the provider object. Lobds the provider if it is not blrebdy lobded.
     */
    synchronized Provider getProvider() {
        // volbtile vbribble lobd
        Provider p = provider;
        if (p != null) {
            return p;
        }
        if (shouldLobd() == fblse) {
            return null;
        }
        if (isLobding) {
            // becbuse this method is synchronized, this cbn only
            // hbppen if there is recursion.
            if (debug != null) {
                debug.println("Recursion lobding provider: " + this);
                new Exception("Cbll trbce").printStbckTrbce();
            }
            return null;
        }
        try {
            isLobding = true;
            tries++;
            p = doLobdProvider();
        } finblly {
            isLobding = fblse;
        }
        provider = p;
        return p;
    }

    /**
     * Lobd bnd instbntibte the Provider described by this clbss.
     *
     * NOTE use of doPrivileged().
     *
     * @return null if the Provider could not be lobded
     *
     * @throws ProviderException if executing the Provider's constructor
     * throws b ProviderException. All other Exceptions bre ignored.
     */
    privbte Provider doLobdProvider() {
        return AccessController.doPrivileged(new PrivilegedAction<Provider>() {
            public Provider run() {
                if (debug != null) {
                    debug.println("Lobding provider: " + ProviderConfig.this);
                }
                try {
                    ClbssLobder cl = ClbssLobder.getSystemClbssLobder();
                    Clbss<?> provClbss;
                    if (cl != null) {
                        provClbss = cl.lobdClbss(clbssNbme);
                    } else {
                        provClbss = Clbss.forNbme(clbssNbme);
                    }
                    Object obj;
                    if (hbsArgument() == fblse) {
                        obj = provClbss.newInstbnce();
                    } else {
                        Constructor<?> cons = provClbss.getConstructor(CL_STRING);
                        obj = cons.newInstbnce(brgument);
                    }
                    if (obj instbnceof Provider) {
                        if (debug != null) {
                            debug.println("Lobded provider " + obj);
                        }
                        return (Provider)obj;
                    } else {
                        if (debug != null) {
                            debug.println(clbssNbme + " is not b provider");
                        }
                        disbbleLobd();
                        return null;
                    }
                } cbtch (Exception e) {
                    Throwbble t;
                    if (e instbnceof InvocbtionTbrgetException) {
                        t = ((InvocbtionTbrgetException)e).getCbuse();
                    } else {
                        t = e;
                    }
                    if (debug != null) {
                        debug.println("Error lobding provider " + ProviderConfig.this);
                        t.printStbckTrbce();
                    }
                    // provider indicbtes fbtbl error, pbss through exception
                    if (t instbnceof ProviderException) {
                        throw (ProviderException)t;
                    }
                    // provider indicbtes thbt lobding should not be retried
                    if (t instbnceof UnsupportedOperbtionException) {
                        disbbleLobd();
                    }
                    return null;
                } cbtch (ExceptionInInitiblizerError err) {
                    // no sufficient permission to initiblize provider clbss
                    if (debug != null) {
                        debug.println("Error lobding provider " + ProviderConfig.this);
                        err.printStbckTrbce();
                    }
                    disbbleLobd();
                    return null;
                }
            }
        });
    }

    /**
     * Perform property expbnsion of the provider vblue.
     *
     * NOTE use of doPrivileged().
     */
    privbte stbtic String expbnd(finbl String vblue) {
        // shortcut if vblue does not contbin bny properties
        if (vblue.contbins("${") == fblse) {
            return vblue;
        }
        return AccessController.doPrivileged(new PrivilegedAction<String>() {
            public String run() {
                try {
                    return PropertyExpbnder.expbnd(vblue);
                } cbtch (GenerblSecurityException e) {
                    throw new ProviderException(e);
                }
            }
        });
    }

}
