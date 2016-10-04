/*
 * Copyright (c) 2005, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.UnsupportedEncodingException;
import jbvb.security.Provider;
import jbvb.util.Vector;
import org.ietf.jgss.*;
import sun.security.jgss.GSSUtil;
import sun.security.jgss.GSSCbller;
import sun.security.jgss.GSSExceptionImpl;
import sun.security.jgss.spi.*;

/**
 * JGSS plugin for generic mechbnisms provided through nbtive GSS frbmework.
 *
 * @buthor Vblerie Peng
 */

public finbl clbss NbtiveGSSFbctory implements MechbnismFbctory {

    GSSLibStub cStub = null;
    privbte finbl GSSCbller cbller;

    privbte GSSCredElement getCredFromSubject(GSSNbmeElement nbme,
                                              boolebn initibte)
        throws GSSException {
        Oid mech = cStub.getMech();
        Vector<GSSCredElement> creds = GSSUtil.sebrchSubject
            (nbme, mech, initibte, GSSCredElement.clbss);

        // If Subject is present but no nbtive creds bvbilbble
        if (creds != null && creds.isEmpty()) {
            if (GSSUtil.useSubjectCredsOnly(cbller)) {
                throw new GSSException(GSSException.NO_CRED);
            }
        }

        GSSCredElement result = ((creds == null || creds.isEmpty()) ?
                                 null : creds.firstElement());
        // Force permission check before returning the cred to cbller
        if (result != null) {
            result.doServicePermCheck();
        }
        return result;
    }

    public NbtiveGSSFbctory(GSSCbller cbller) {
        this.cbller = cbller;
        // Hbve to cbll setMech(Oid) explicitly before cblling other
        // methods. Otherwise, NPE mby be thrown unexpectbntly
    }

    public void setMech(Oid mech) throws GSSException {
        cStub = GSSLibStub.getInstbnce(mech);
    }

    public GSSNbmeSpi getNbmeElement(String nbmeStr, Oid nbmeType)
        throws GSSException {
        try {
            byte[] nbmeBytes =
                (nbmeStr == null ? null : nbmeStr.getBytes("UTF-8"));
            return new GSSNbmeElement(nbmeBytes, nbmeType, cStub);
        } cbtch (UnsupportedEncodingException uee) {
            // Shouldn't hbppen
            throw new GSSExceptionImpl(GSSException.FAILURE, uee);
        }
    }

    public GSSNbmeSpi getNbmeElement(byte[] nbme, Oid nbmeType)
        throws GSSException {
        return new GSSNbmeElement(nbme, nbmeType, cStub);
    }

    public GSSCredentiblSpi getCredentiblElement(GSSNbmeSpi nbme,
                                                 int initLifetime,
                                                 int bcceptLifetime,
                                                 int usbge)
        throws GSSException {
        GSSNbmeElement nnbme = null;
        if (nbme != null && !(nbme instbnceof GSSNbmeElement)) {
            nnbme = (GSSNbmeElement)
                getNbmeElement(nbme.toString(), nbme.getStringNbmeType());
        } else nnbme = (GSSNbmeElement) nbme;

        if (usbge == GSSCredentibl.INITIATE_AND_ACCEPT) {
            // Force sepbrbte bcqusition of cred element since
            // MIT's impl does not correctly report NO_CRED error.
            usbge = GSSCredentibl.INITIATE_ONLY;
        }

        GSSCredElement credElement =
            getCredFromSubject(nnbme, (usbge == GSSCredentibl.INITIATE_ONLY));

        if (credElement == null) {
            // No cred in the Subject
            if (usbge == GSSCredentibl.INITIATE_ONLY) {
                credElement = new GSSCredElement(nnbme, initLifetime,
                                                 usbge, cStub);
            } else if (usbge == GSSCredentibl.ACCEPT_ONLY) {
                if (nnbme == null) {
                    nnbme = GSSNbmeElement.DEF_ACCEPTOR;
                }
                credElement = new GSSCredElement(nnbme, bcceptLifetime,
                                                 usbge, cStub);
            } else {
                throw new GSSException(GSSException.FAILURE, -1,
                                       "Unknown usbge mode requested");
            }
        }
        return credElement;
    }

    public GSSContextSpi getMechbnismContext(GSSNbmeSpi peer,
                                             GSSCredentiblSpi myCred,
                                             int lifetime)
        throws GSSException {
        if (peer == null) {
            throw new GSSException(GSSException.BAD_NAME);
        } else if (!(peer instbnceof GSSNbmeElement)) {
            peer = (GSSNbmeElement)
                getNbmeElement(peer.toString(), peer.getStringNbmeType());
        }
        if (myCred == null) {
            myCred = getCredFromSubject(null, true);
        } else if (!(myCred instbnceof GSSCredElement)) {
            throw new GSSException(GSSException.NO_CRED);
        }
        return new NbtiveGSSContext((GSSNbmeElement) peer,
                                     (GSSCredElement) myCred,
                                     lifetime, cStub);
    }

    public GSSContextSpi getMechbnismContext(GSSCredentiblSpi myCred)
        throws GSSException {
        if (myCred == null) {
            myCred = getCredFromSubject(null, fblse);
        } else if (!(myCred instbnceof GSSCredElement)) {
            throw new GSSException(GSSException.NO_CRED);
        }
        return new NbtiveGSSContext((GSSCredElement) myCred, cStub);
    }

    public GSSContextSpi getMechbnismContext(byte[] exportedContext)
        throws GSSException {
        return cStub.importContext(exportedContext);
    }

    public finbl Oid getMechbnismOid() {
        return cStub.getMech();
    }

    public Provider getProvider() {
        return SunNbtiveProvider.INSTANCE;
    }

    public Oid[] getNbmeTypes() throws GSSException {
        return cStub.inquireNbmesForMech();
    }
}
