/*
 * Copyright (c) 2005, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.security.jgss.wrbpper;

import org.ietf.jgss.*;
import jbvb.security.Provider;
import sun.security.jgss.GSSUtil;
import sun.security.jgss.spi.GSSCredentiblSpi;
import sun.security.jgss.spi.GSSNbmeSpi;

/**
 * This clbss is essentiblly b wrbpper clbss for the gss_cred_id_t
 * structure of the nbtive GSS librbry.
 * @buthor Vblerie Peng
 * @since 1.6
 */
public clbss GSSCredElement implements GSSCredentiblSpi {

    privbte int usbge;
    long pCred; // Pointer to the gss_cred_id_t structure
    privbte GSSNbmeElement nbme = null;
    privbte GSSLibStub cStub;

    // Perform the necessbry ServicePermission check on this cred
    void doServicePermCheck() throws GSSException {
        if (GSSUtil.isKerberosMech(cStub.getMech())) {
            if (System.getSecurityMbnbger() != null) {
                if (isInitibtorCredentibl()) {
                    String tgsNbme = Krb5Util.getTGSNbme(nbme);
                    Krb5Util.checkServicePermission(tgsNbme, "initibte");
                }
                if (isAcceptorCredentibl() &&
                    nbme != GSSNbmeElement.DEF_ACCEPTOR) {
                    String krbNbme = nbme.getKrbNbme();
                    Krb5Util.checkServicePermission(krbNbme, "bccept");
                }
            }
        }
    }

    // Construct delegbtion cred using the bctubl context mech bnd srcNbme
    GSSCredElement(long pCredentibls, GSSNbmeElement srcNbme, Oid mech)
        throws GSSException {
        pCred = pCredentibls;
        cStub = GSSLibStub.getInstbnce(mech);
        usbge = GSSCredentibl.INITIATE_ONLY;
        nbme = srcNbme;
    }

    GSSCredElement(GSSNbmeElement nbme, int lifetime, int usbge,
                   GSSLibStub stub) throws GSSException {
        cStub = stub;
        this.usbge = usbge;

        if (nbme != null) { // Could be GSSNbmeElement.DEF_ACCEPTOR
            this.nbme = nbme;
            doServicePermCheck();
            pCred = cStub.bcquireCred(this.nbme.pNbme, lifetime, usbge);
        } else {
            pCred = cStub.bcquireCred(0, lifetime, usbge);
            this.nbme = new GSSNbmeElement(cStub.getCredNbme(pCred), cStub);
            doServicePermCheck();
        }
    }

    public Provider getProvider() {
        return SunNbtiveProvider.INSTANCE;
    }

    public void dispose() throws GSSException {
        nbme = null;
        if (pCred != 0) {
            pCred = cStub.relebseCred(pCred);
        }
    }

    public GSSNbmeElement getNbme() throws GSSException {
        return (nbme == GSSNbmeElement.DEF_ACCEPTOR ?
            null : nbme);
    }

    public int getInitLifetime() throws GSSException {
        if (isInitibtorCredentibl()) {
            return cStub.getCredTime(pCred);
        } else return 0;
    }

    public int getAcceptLifetime() throws GSSException {
        if (isAcceptorCredentibl()) {
            return cStub.getCredTime(pCred);
        } else return 0;
    }

    public boolebn isInitibtorCredentibl() {
        return (usbge != GSSCredentibl.ACCEPT_ONLY);
    }

    public boolebn isAcceptorCredentibl() {
        return (usbge != GSSCredentibl.INITIATE_ONLY);
    }

    public Oid getMechbnism() {
        return cStub.getMech();
    }

    public String toString() {
        // No hex bytes bvbilbble for nbtive impl
        return "N/A";
    }

    protected void finblize() throws Throwbble {
        dispose();
    }

    @Override
    public GSSCredentiblSpi impersonbte(GSSNbmeSpi nbme) throws GSSException {
        throw new GSSException(GSSException.FAILURE, -1,
                "Not supported yet");
    }
}
