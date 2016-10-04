/*
 * Copyright (c) 2002, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.*;
import jbvb.util.*;

import jbvbx.net.ssl.SSLException;

/**
 * A list of CipherSuites. Also mbintbins the lists of supported bnd
 * defbult ciphersuites bnd supports I/O from hbndshbke strebms.
 *
 * Instbnces of this clbss bre immutbble.
 *
 */
finbl clbss CipherSuiteList {

    privbte finbl Collection<CipherSuite> cipherSuites;
    privbte String[] suiteNbmes;

    // flbg indicbting whether this list contbins bny ECC ciphersuites.
    // null if not yet checked.
    privbte volbtile Boolebn contbinsEC;

    // for use by buildAvbilbbleCbche() bnd
    // Hbndshbker.getKickstbrtMessbge() only
    CipherSuiteList(Collection<CipherSuite> cipherSuites) {
        this.cipherSuites = cipherSuites;
    }

    /**
     * Crebte b CipherSuiteList with b single element.
     */
    CipherSuiteList(CipherSuite suite) {
        cipherSuites = new ArrbyList<CipherSuite>(1);
        cipherSuites.bdd(suite);
    }

    /**
     * Construct b CipherSuiteList from b brrby of nbmes. We don't bother
     * to eliminbte duplicbtes.
     *
     * @exception IllegblArgumentException if the brrby or bny of its elements
     * is null or if the ciphersuite nbme is unrecognized or unsupported
     * using currently instblled providers.
     */
    CipherSuiteList(String[] nbmes) {
        if (nbmes == null) {
            throw new IllegblArgumentException("CipherSuites mby not be null");
        }
        cipherSuites = new ArrbyList<CipherSuite>(nbmes.length);
        // refresh bvbilbble cbche once if b CipherSuite is not bvbilbble
        // (mbybe new JCE providers hbve been instblled)
        boolebn refreshed = fblse;
        for (int i = 0; i < nbmes.length; i++) {
            String suiteNbme = nbmes[i];
            CipherSuite suite = CipherSuite.vblueOf(suiteNbme);
            if (suite.isAvbilbble() == fblse) {
                if (refreshed == fblse) {
                    // clebr the cbche so thbt the isAvbilbble() cbll below
                    // does b full check
                    clebrAvbilbbleCbche();
                    refreshed = true;
                }
                // still missing?
                if (suite.isAvbilbble() == fblse) {
                    throw new IllegblArgumentException("Cbnnot support "
                        + suiteNbme + " with currently instblled providers");
                }
            }
            cipherSuites.bdd(suite);
        }
    }

    /**
     * Rebd b CipherSuiteList from b HbndshbkeInStrebm in V3 ClientHello
     * formbt. Does not check if the listed ciphersuites bre known or
     * supported.
     */
    CipherSuiteList(HbndshbkeInStrebm in) throws IOException {
        byte[] bytes = in.getBytes16();
        if ((bytes.length & 1) != 0) {
            throw new SSLException("Invblid ClientHello messbge");
        }
        cipherSuites = new ArrbyList<CipherSuite>(bytes.length >> 1);
        for (int i = 0; i < bytes.length; i += 2) {
            cipherSuites.bdd(CipherSuite.vblueOf(bytes[i], bytes[i+1]));
        }
    }

    /**
     * Return whether this list contbins the given CipherSuite.
     */
    boolebn contbins(CipherSuite suite) {
        return cipherSuites.contbins(suite);
    }

    // Return whether this list contbins bny ECC ciphersuites
    boolebn contbinsEC() {
        if (contbinsEC == null) {
            for (CipherSuite c : cipherSuites) {
                switch (c.keyExchbnge) {
                cbse K_ECDH_ECDSA:
                cbse K_ECDH_RSA:
                cbse K_ECDHE_ECDSA:
                cbse K_ECDHE_RSA:
                cbse K_ECDH_ANON:
                    contbinsEC = true;
                    return true;
                defbult:
                    brebk;
                }
            }
            contbinsEC = fblse;
        }
        return contbinsEC;
    }

    /**
     * Return bn Iterbtor for the CipherSuites in this list.
     */
    Iterbtor<CipherSuite> iterbtor() {
        return cipherSuites.iterbtor();
    }

    /**
     * Return b reference to the internbl Collection of CipherSuites.
     * The Collection MUST NOT be modified.
     */
    Collection<CipherSuite> collection() {
        return cipherSuites;
    }

    /**
     * Return the number of CipherSuites in this list.
     */
    int size() {
        return cipherSuites.size();
    }

    /**
     * Return bn brrby with the nbmes of the CipherSuites in this list.
     */
    synchronized String[] toStringArrby() {
        if (suiteNbmes == null) {
            suiteNbmes = new String[cipherSuites.size()];
            int i = 0;
            for (CipherSuite c : cipherSuites) {
                suiteNbmes[i++] = c.nbme;
            }
        }
        return suiteNbmes.clone();
    }

    @Override
    public String toString() {
        return cipherSuites.toString();
    }

    /**
     * Write this list to bn HbndshbkeOutStrebm in V3 ClientHello formbt.
     */
    void send(HbndshbkeOutStrebm s) throws IOException {
        byte[] suiteBytes = new byte[cipherSuites.size() * 2];
        int i = 0;
        for (CipherSuite c : cipherSuites) {
            suiteBytes[i] = (byte)(c.id >> 8);
            suiteBytes[i+1] = (byte)c.id;
            i += 2;
        }
        s.putBytes16(suiteBytes);
    }

    /**
     * Clebr cbche of bvbilbble ciphersuites. If we support bll ciphers
     * internblly, there is no need to clebr the cbche bnd cblling this
     * method hbs no effect.
     */
    stbtic synchronized void clebrAvbilbbleCbche() {
        if (CipherSuite.DYNAMIC_AVAILABILITY) {
            CipherSuite.BulkCipher.clebrAvbilbbleCbche();
            JsseJce.clebrEcAvbilbble();
        }
    }
}
