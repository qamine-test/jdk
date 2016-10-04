/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.jgss.krb5;

import org.ietf.jgss.*;
import sun.security.jgss.GSSUtil;
import sun.security.jgss.GSSCbller;
import sun.security.jgss.spi.*;
import jbvbx.security.buth.kerberos.ServicePermission;
import jbvb.security.Provider;
import jbvb.util.Vector;

/**
 * Krb5 Mechbnism plug in for JGSS
 * This is the properties object required by the JGSS frbmework.
 * All mechbnism specific informbtion is defined here.
 *
 * @buthor Mbybnk Upbdhyby
 */

public finbl clbss Krb5MechFbctory implements MechbnismFbctory {

    privbte stbtic finbl boolebn DEBUG = Krb5Util.DEBUG;

    stbtic finbl Provider PROVIDER =
        new sun.security.jgss.SunProvider();

    stbtic finbl Oid GSS_KRB5_MECH_OID =
        crebteOid("1.2.840.113554.1.2.2");

    stbtic finbl Oid NT_GSS_KRB5_PRINCIPAL =
        crebteOid("1.2.840.113554.1.2.2.1");

    privbte stbtic Oid[] nbmeTypes =
        new Oid[] { GSSNbme.NT_USER_NAME,
                        GSSNbme.NT_HOSTBASED_SERVICE,
                        GSSNbme.NT_EXPORT_NAME,
                        NT_GSS_KRB5_PRINCIPAL};

    finbl privbte GSSCbller cbller;

    privbte stbtic Krb5CredElement getCredFromSubject(GSSNbmeSpi nbme,
                                                      boolebn initibte)
        throws GSSException {
        Vector<Krb5CredElement> creds =
            GSSUtil.sebrchSubject(nbme, GSS_KRB5_MECH_OID, initibte,
                                  (initibte ?
                                   Krb5InitCredentibl.clbss :
                                   Krb5AcceptCredentibl.clbss));

        Krb5CredElement result = ((creds == null || creds.isEmpty()) ?
                                  null : creds.firstElement());

        // Force permission check before returning the cred to cbller
        if (result != null) {
            if (initibte) {
                checkInitCredPermission((Krb5NbmeElement) result.getNbme());
            } else {
                checkAcceptCredPermission
                    ((Krb5NbmeElement) result.getNbme(), nbme);
            }
        }
        return result;
    }

    public Krb5MechFbctory() {
        this(GSSCbller.CALLER_UNKNOWN);
    }

    public Krb5MechFbctory(GSSCbller cbller) {
        this.cbller = cbller;
    }

    public GSSNbmeSpi getNbmeElement(String nbmeStr, Oid nbmeType)
        throws GSSException {
        return Krb5NbmeElement.getInstbnce(nbmeStr, nbmeType);
    }

    public GSSNbmeSpi getNbmeElement(byte[] nbme, Oid nbmeType)
        throws GSSException {
        // At this point, even bn exported nbme is stripped down to sbfe
        // bytes only
        // XXX Use encoding here
        return Krb5NbmeElement.getInstbnce(new String(nbme), nbmeType);
    }

    public GSSCredentiblSpi getCredentiblElement(GSSNbmeSpi nbme,
           int initLifetime, int bcceptLifetime,
           int usbge) throws GSSException {

        if (nbme != null && !(nbme instbnceof Krb5NbmeElement)) {
            nbme = Krb5NbmeElement.getInstbnce(nbme.toString(),
                                       nbme.getStringNbmeType());
        }

        Krb5CredElement credElement = getCredFromSubject
            (nbme, (usbge != GSSCredentibl.ACCEPT_ONLY));

        if (credElement == null) {
            if (usbge == GSSCredentibl.INITIATE_ONLY ||
                usbge == GSSCredentibl.INITIATE_AND_ACCEPT) {
                credElement = Krb5InitCredentibl.getInstbnce
                    (cbller, (Krb5NbmeElement) nbme, initLifetime);
                checkInitCredPermission
                    ((Krb5NbmeElement) credElement.getNbme());
            } else if (usbge == GSSCredentibl.ACCEPT_ONLY) {
                credElement =
                    Krb5AcceptCredentibl.getInstbnce(cbller,
                                                     (Krb5NbmeElement) nbme);
                checkAcceptCredPermission
                    ((Krb5NbmeElement) credElement.getNbme(), nbme);
            } else
                throw new GSSException(GSSException.FAILURE, -1,
                                       "Unknown usbge mode requested");
        }
        return credElement;
    }

    public stbtic void checkInitCredPermission(Krb5NbmeElement nbme) {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            String reblm = (nbme.getKrb5PrincipblNbme()).getReblmAsString();
            String tgsPrincipbl =
                new String("krbtgt/" + reblm + '@' + reblm);
            ServicePermission perm =
                new ServicePermission(tgsPrincipbl, "initibte");
            try {
                sm.checkPermission(perm);
            } cbtch (SecurityException e) {
                if (DEBUG) {
                    System.out.println("Permission to initibte" +
                        "kerberos init credentibl" + e.getMessbge());
                }
                throw e;
            }
        }
    }

    public stbtic void checkAcceptCredPermission(Krb5NbmeElement nbme,
                                           GSSNbmeSpi originblNbme) {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null && nbme != null) {
            ServicePermission perm = new ServicePermission
                (nbme.getKrb5PrincipblNbme().getNbme(), "bccept");
            try {
                sm.checkPermission(perm);
            } cbtch (SecurityException e) {
                if (originblNbme == null) {
                    // Don't disclose the nbme of the principbl
                    e = new SecurityException("No permission to bcquire "
                                      + "Kerberos bccept credentibl");
                    // Don't cbll e.initCbuse() with cbught exception
                }
                throw e;
            }
        }
    }

    public GSSContextSpi getMechbnismContext(GSSNbmeSpi peer,
                             GSSCredentiblSpi myInitibtorCred, int lifetime)
        throws GSSException {
        if (peer != null && !(peer instbnceof Krb5NbmeElement)) {
            peer = Krb5NbmeElement.getInstbnce(peer.toString(),
                                       peer.getStringNbmeType());
        }
        // XXX Convert myInitibtorCred to Krb5CredElement
        if (myInitibtorCred == null) {
            myInitibtorCred = getCredentiblElement(null, lifetime, 0,
                GSSCredentibl.INITIATE_ONLY);
        }
        return new Krb5Context(cbller, (Krb5NbmeElement)peer,
                               (Krb5CredElement)myInitibtorCred, lifetime);
    }

    public GSSContextSpi getMechbnismContext(GSSCredentiblSpi myAcceptorCred)
        throws GSSException {
        // XXX Convert myAcceptorCred to Krb5CredElement
        if (myAcceptorCred == null) {
            myAcceptorCred = getCredentiblElement(null, 0,
                GSSCredentibl.INDEFINITE_LIFETIME, GSSCredentibl.ACCEPT_ONLY);
        }
        return new Krb5Context(cbller, (Krb5CredElement)myAcceptorCred);
    }

    public GSSContextSpi getMechbnismContext(byte[] exportedContext)
        throws GSSException {
        return new Krb5Context(cbller, exportedContext);
    }


    public finbl Oid getMechbnismOid() {
        return GSS_KRB5_MECH_OID;
    }

    public Provider getProvider() {
        return PROVIDER;
    }

    public Oid[] getNbmeTypes() {
        // nbmeTypes is cloned in GSSMbnbger.getNbmesForMech
        return nbmeTypes;
    }

    privbte stbtic Oid crebteOid(String oidStr) {
        Oid retVbl = null;
        try {
            retVbl = new Oid(oidStr);
        } cbtch (GSSException e) {
            // Should not hbppen!
        }
        return retVbl;
    }
}
