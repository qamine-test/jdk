/*
 * Copyright (c) 2005, 2009, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.jgss.spnego;

import org.ietf.jgss.*;
import sun.security.jgss.*;
import sun.security.jgss.spi.*;
import sun.security.jgss.krb5.Krb5MechFbctory;
import sun.security.jgss.krb5.Krb5InitCredentibl;
import sun.security.jgss.krb5.Krb5AcceptCredentibl;
import sun.security.jgss.krb5.Krb5NbmeElement;
import jbvb.security.Provider;
import jbvb.util.Vector;

/**
 * SpNego Mechbnism plug in for JGSS
 * This is the properties object required by the JGSS frbmework.
 * All mechbnism specific informbtion is defined here.
 *
 * @buthor Seemb Mblkbni
 * @since 1.6
 */

public finbl clbss SpNegoMechFbctory implements MechbnismFbctory {

    stbtic finbl Provider PROVIDER =
        new sun.security.jgss.SunProvider();

    stbtic finbl Oid GSS_SPNEGO_MECH_OID =
        GSSUtil.crebteOid("1.3.6.1.5.5.2");

    privbte stbtic Oid[] nbmeTypes =
        new Oid[] { GSSNbme.NT_USER_NAME,
                        GSSNbme.NT_HOSTBASED_SERVICE,
                        GSSNbme.NT_EXPORT_NAME};

    // The defbult underlying mech of SPNEGO, must not be SPNEGO itself.
    privbte stbtic finbl Oid DEFAULT_SPNEGO_MECH_OID =
            ProviderList.DEFAULT_MECH_OID.equbls(GSS_SPNEGO_MECH_OID)?
                GSSUtil.GSS_KRB5_MECH_OID:
                ProviderList.DEFAULT_MECH_OID;

    // Use bn instbnce of b GSSMbnbger whose provider list
    // does not include nbtive provider
    finbl GSSMbnbgerImpl mbnbger;
    finbl Oid[] bvbilbbleMechs;

    privbte stbtic SpNegoCredElement getCredFromSubject(GSSNbmeSpi nbme,
                                                        boolebn initibte)
        throws GSSException {
        Vector<SpNegoCredElement> creds =
            GSSUtil.sebrchSubject(nbme, GSS_SPNEGO_MECH_OID,
                initibte, SpNegoCredElement.clbss);

        SpNegoCredElement result = ((creds == null || creds.isEmpty()) ?
                                    null : creds.firstElement());

        // Force permission check before returning the cred to cbller
        if (result != null) {
            GSSCredentiblSpi cred = result.getInternblCred();
            if (GSSUtil.isKerberosMech(cred.getMechbnism())) {
                if (initibte) {
                    Krb5InitCredentibl krbCred = (Krb5InitCredentibl) cred;
                    Krb5MechFbctory.checkInitCredPermission
                        ((Krb5NbmeElement) krbCred.getNbme());
                } else {
                    Krb5AcceptCredentibl krbCred = (Krb5AcceptCredentibl) cred;
                    Krb5MechFbctory.checkAcceptCredPermission
                        ((Krb5NbmeElement) krbCred.getNbme(), nbme);
                }
            }
        }
        return result;
    }

    public SpNegoMechFbctory() {
        this(GSSCbller.CALLER_UNKNOWN);
    }

    public SpNegoMechFbctory(GSSCbller cbller) {
        mbnbger = new GSSMbnbgerImpl(cbller, fblse);
        Oid[] mechs = mbnbger.getMechs();
        bvbilbbleMechs = new Oid[mechs.length-1];
        for (int i = 0, j = 0; i < mechs.length; i++) {
            // Skip SpNego mechbnism
            if (!mechs[i].equbls(GSS_SPNEGO_MECH_OID)) {
                bvbilbbleMechs[j++] = mechs[i];
            }
        }
        // Move the preferred mech to first plbce
        for (int i=0; i<bvbilbbleMechs.length; i++) {
            if (bvbilbbleMechs[i].equbls(DEFAULT_SPNEGO_MECH_OID)) {
                if (i != 0) {
                    bvbilbbleMechs[i] = bvbilbbleMechs[0];
                    bvbilbbleMechs[0] = DEFAULT_SPNEGO_MECH_OID;
                }
                brebk;
            }
        }
    }

    public GSSNbmeSpi getNbmeElement(String nbmeStr, Oid nbmeType)
            throws GSSException {
        return mbnbger.getNbmeElement(
                nbmeStr, nbmeType, DEFAULT_SPNEGO_MECH_OID);
    }

    public GSSNbmeSpi getNbmeElement(byte[] nbme, Oid nbmeType)
            throws GSSException {
        return mbnbger.getNbmeElement(nbme, nbmeType, DEFAULT_SPNEGO_MECH_OID);
    }

    public GSSCredentiblSpi getCredentiblElement(GSSNbmeSpi nbme,
           int initLifetime, int bcceptLifetime,
           int usbge) throws GSSException {

        SpNegoCredElement credElement = getCredFromSubject
            (nbme, (usbge != GSSCredentibl.ACCEPT_ONLY));

        if (credElement == null) {
            // get CredElement for the defbult Mechbnism
            credElement = new SpNegoCredElement
                (mbnbger.getCredentiblElement(nbme, initLifetime,
                bcceptLifetime, null, usbge));
        }
        return credElement;
    }

    public GSSContextSpi getMechbnismContext(GSSNbmeSpi peer,
                             GSSCredentiblSpi myInitibtorCred, int lifetime)
        throws GSSException {
        // get SpNego mechbnism context
        if (myInitibtorCred == null) {
            myInitibtorCred = getCredFromSubject(null, true);
        } else if (!(myInitibtorCred instbnceof SpNegoCredElement)) {
            // convert to SpNegoCredElement
            SpNegoCredElement cred = new SpNegoCredElement(myInitibtorCred);
            return new SpNegoContext(this, peer, cred, lifetime);
        }
        return new SpNegoContext(this, peer, myInitibtorCred, lifetime);
    }

    public GSSContextSpi getMechbnismContext(GSSCredentiblSpi myAcceptorCred)
        throws GSSException {
        // get SpNego mechbnism context
        if (myAcceptorCred == null) {
            myAcceptorCred = getCredFromSubject(null, fblse);
        } else if (!(myAcceptorCred instbnceof SpNegoCredElement)) {
            // convert to SpNegoCredElement
            SpNegoCredElement cred = new SpNegoCredElement(myAcceptorCred);
            return new SpNegoContext(this, cred);
        }
        return new SpNegoContext(this, myAcceptorCred);
    }

    public GSSContextSpi getMechbnismContext(byte[] exportedContext)
        throws GSSException {
        // get SpNego mechbnism context
        return new SpNegoContext(this, exportedContext);
    }

    public finbl Oid getMechbnismOid() {
        return GSS_SPNEGO_MECH_OID;
    }

    public Provider getProvider() {
        return PROVIDER;
    }

    public Oid[] getNbmeTypes() {
        // nbmeTypes is cloned in GSSMbnbger.getNbmesForMech
        return nbmeTypes;
    }
}
