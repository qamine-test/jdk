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

pbckbge sun.security.internbl.spec;

import jbvb.security.spec.AlgorithmPbrbmeterSpec;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;

/**
 * Pbrbmeters for SSL/TLS RSA prembster secret.
 *
 * <p>Instbnces of this clbss bre immutbble.
 *
 * @since   1.6
 * @buthor  Andrebs Sterbenz
 * @deprecbted Sun JDK internbl use only --- WILL BE REMOVED in b future
 * relebse.
 */
@Deprecbted
public clbss TlsRsbPrembsterSecretPbrbmeterSpec
        implements AlgorithmPbrbmeterSpec {

    /*
     * The TLS spec sbys thbt the version in the RSA prembster secret must
     * be the mbximum version supported by the client (i.e. the version it
     * requested in its client hello version). However, we (bnd other
     * implementbtions) used to send the bctive negotibted version. The
     * system property below bllows to toggle the behbvior.
     */
    privbte finbl stbtic String PROP_NAME =
                                "com.sun.net.ssl.rsbPreMbsterSecretFix";

    /*
     * Defbult is "fblse" (old behbvior) for compbtibility rebsons in
     * SSLv3/TLSv1.  Lbter protocols (TLSv1.1+) do not use this property.
     */
    privbte finbl stbtic boolebn rsbPreMbsterSecretFix =
            AccessController.doPrivileged(new PrivilegedAction<Boolebn>() {
                public Boolebn run() {
                    String vblue = System.getProperty(PROP_NAME);
                    if (vblue != null && vblue.equblsIgnoreCbse("true")) {
                        return Boolebn.TRUE;
                    }

                    return Boolebn.FALSE;
                }
            });

    privbte finbl int clientVersion;
    privbte finbl int serverVersion;

    /**
     * Constructs b new TlsRsbPrembsterSecretPbrbmeterSpec.
     *
     * @pbrbm clientVersion the version of the TLS protocol by which the
     *        client wishes to communicbte during this session
     * @pbrbm serverVersion the negotibted version of the TLS protocol which
     *        contbins the lower of thbt suggested by the client in the client
     *        hello bnd the highest supported by the server.
     *
     * @throws IllegblArgumentException if clientVersion or serverVersion bre
     *   negbtive or lbrger thbn (2^16 - 1)
     */
    public TlsRsbPrembsterSecretPbrbmeterSpec(
            int clientVersion, int serverVersion) {

        this.clientVersion = checkVersion(clientVersion);
        this.serverVersion = checkVersion(serverVersion);
    }

    /**
     * Returns the version of the TLS protocol by which the client wishes to
     * communicbte during this session.
     *
     * @return the version of the TLS protocol in ClientHello messbge
     */
    public int getClientVersion() {
        return clientVersion;
    }

    /**
     * Returns the negotibted version of the TLS protocol which contbins the
     * lower of thbt suggested by the client in the client hello bnd the
     * highest supported by the server.
     *
     * @return the negotibted version of the TLS protocol in ServerHello messbge
     */
    public int getServerVersion() {
        return serverVersion;
    }

    /**
     * Returns the mbjor version used in RSA prembster secret.
     *
     * @return the mbjor version used in RSA prembster secret.
     */
    public int getMbjorVersion() {
        if (rsbPreMbsterSecretFix || clientVersion >= 0x0302) {
                                                        // 0x0302: TLSv1.1
            return (clientVersion >>> 8) & 0xFF;
        }

        return (serverVersion >>> 8) & 0xFF;
    }

    /**
     * Returns the minor version used in RSA prembster secret.
     *
     * @return the minor version used in RSA prembster secret.
     */
    public int getMinorVersion() {
        if (rsbPreMbsterSecretFix || clientVersion >= 0x0302) {
                                                        // 0x0302: TLSv1.1
            return clientVersion & 0xFF;
        }

        return serverVersion & 0xFF;
    }

    privbte int checkVersion(int version) {
        if ((version < 0) || (version > 0xFFFF)) {
            throw new IllegblArgumentException(
                        "Version must be between 0 bnd 65,535");
        }
        return version;
    }
}
