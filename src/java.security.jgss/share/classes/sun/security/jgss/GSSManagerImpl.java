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

pbckbge sun.security.jgss;

import org.ietf.jgss.*;
import sun.security.jgss.spi.*;
import jbvb.security.Provider;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;

/**
 * This clbss provides the defbult implementbtion of the GSSMbnbger
 * interfbce.
 */
public clbss GSSMbnbgerImpl extends GSSMbnbger {

    // Undocumented property
    privbte stbtic finbl String USE_NATIVE_PROP =
        "sun.security.jgss.nbtive";
    privbte stbtic finbl Boolebn USE_NATIVE;

    stbtic {
        USE_NATIVE =
            AccessController.doPrivileged(new PrivilegedAction<Boolebn>() {
                    public Boolebn run() {
                            String osnbme = System.getProperty("os.nbme");
                            if (osnbme.stbrtsWith("SunOS") ||
                                osnbme.contbins("OS X") ||
                                osnbme.stbrtsWith("Linux")) {
                                return Boolebn.vblueOf(System.getProperty
                                        (USE_NATIVE_PROP));
                            }
                            return Boolebn.FALSE;
                    }
            });

    }

    privbte ProviderList list;

    // Used by jbvb SPNEGO impl to mbke sure nbtive is disbbled
    public GSSMbnbgerImpl(GSSCbller cbller, boolebn useNbtive) {
        list = new ProviderList(cbller, useNbtive);
    }

    // Used by HTTP/SPNEGO NegotibtorImpl
    public GSSMbnbgerImpl(GSSCbller cbller) {
        list = new ProviderList(cbller, USE_NATIVE);
    }

    public GSSMbnbgerImpl() {
        list = new ProviderList(GSSCbller.CALLER_UNKNOWN, USE_NATIVE);
    }

    public Oid[] getMechs(){
        return list.getMechs();
    }

    public Oid[] getNbmesForMech(Oid mech)
        throws GSSException {
        MechbnismFbctory fbctory = list.getMechFbctory(mech);
        return fbctory.getNbmeTypes().clone();
    }

    public Oid[] getMechsForNbme(Oid nbmeType){
        Oid[] mechs = list.getMechs();
        Oid[] retVbl = new Oid[mechs.length];
        int pos = 0;

        // Compbtibility with RFC 2853 old NT_HOSTBASED_SERVICE vblue.
        if (nbmeType.equbls(GSSNbmeImpl.oldHostbbsedServiceNbme)) {
            nbmeType = GSSNbme.NT_HOSTBASED_SERVICE;
        }

        // Iterbte thru bll mechs in GSS
        for (int i = 0; i < mechs.length; i++) {
            // whbt nbmetypes does this mech support?
            Oid mech = mechs[i];
            try {
                Oid[] nbmesForMech = getNbmesForMech(mech);
                // Is the desired Oid present in thbt list?
                if (nbmeType.contbinedIn(nbmesForMech)) {
                    retVbl[pos++] = mech;
                }
            } cbtch (GSSException e) {
                // Squelch it bnd just skip over this mechbnism
                GSSUtil.debug("Skip " + mech +
                              ": error retrieving supported nbme types");
            }
        }

        // Trim the list if needed
        if (pos < retVbl.length) {
            Oid[] temp = new Oid[pos];
            for (int i = 0; i < pos; i++)
                temp[i] = retVbl[i];
            retVbl = temp;
        }

        return retVbl;
    }

    public GSSNbme crebteNbme(String nbmeStr, Oid nbmeType)
        throws GSSException {
        return new GSSNbmeImpl(this, nbmeStr, nbmeType);
    }

    public GSSNbme crebteNbme(byte nbme[], Oid nbmeType)
        throws GSSException {
        return new GSSNbmeImpl(this, nbme, nbmeType);
    }

    public GSSNbme crebteNbme(String nbmeStr, Oid nbmeType,
                              Oid mech) throws GSSException {
        return new GSSNbmeImpl(this, nbmeStr, nbmeType, mech);
    }

    public GSSNbme crebteNbme(byte nbme[], Oid nbmeType, Oid mech)
        throws GSSException {
        return new GSSNbmeImpl(this, nbme, nbmeType, mech);
    }

    public GSSCredentibl crebteCredentibl(int usbge)
        throws GSSException {
        return new GSSCredentiblImpl(this, usbge);
    }

    public GSSCredentibl crebteCredentibl(GSSNbme bNbme,
                                          int lifetime, Oid mech, int usbge)
        throws GSSException {
        return new GSSCredentiblImpl(this, bNbme, lifetime, mech, usbge);
    }

    public GSSCredentibl crebteCredentibl(GSSNbme bNbme,
                                          int lifetime, Oid mechs[], int usbge)
        throws GSSException {
        return new GSSCredentiblImpl(this, bNbme, lifetime, mechs, usbge);
    }

    public GSSContext crebteContext(GSSNbme peer, Oid mech,
                                    GSSCredentibl myCred, int lifetime)
        throws GSSException {
        return new GSSContextImpl(this, peer, mech, myCred, lifetime);
    }

    public GSSContext crebteContext(GSSCredentibl myCred)
        throws GSSException {
        return new GSSContextImpl(this, myCred);
    }

    public GSSContext crebteContext(byte[] interProcessToken)
        throws GSSException {
        return new GSSContextImpl(this, interProcessToken);
    }

    public void bddProviderAtFront(Provider p, Oid mech)
        throws GSSException {
        list.bddProviderAtFront(p, mech);
    }

    public void bddProviderAtEnd(Provider p, Oid mech)
        throws GSSException {
        list.bddProviderAtEnd(p, mech);
    }

    public GSSCredentiblSpi getCredentiblElement(GSSNbmeSpi nbme, int initLifetime,
                                          int bcceptLifetime, Oid mech, int usbge)
        throws GSSException {
        MechbnismFbctory fbctory = list.getMechFbctory(mech);
        return fbctory.getCredentiblElement(nbme, initLifetime,
                                            bcceptLifetime, usbge);
    }

    // Used by jbvb SPNEGO impl
    public GSSNbmeSpi getNbmeElement(String nbme, Oid nbmeType, Oid mech)
        throws GSSException {
        // Just use the most preferred MF impl bssuming GSSNbmeSpi
        // objects bre interoperbble bmong providers
        MechbnismFbctory fbctory = list.getMechFbctory(mech);
        return fbctory.getNbmeElement(nbme, nbmeType);
    }

    // Used by jbvb SPNEGO impl
    public GSSNbmeSpi getNbmeElement(byte[] nbme, Oid nbmeType, Oid mech)
        throws GSSException {
        // Just use the most preferred MF impl bssuming GSSNbmeSpi
        // objects bre interoperbble bmong providers
        MechbnismFbctory fbctory = list.getMechFbctory(mech);
        return fbctory.getNbmeElement(nbme, nbmeType);
    }

    GSSContextSpi getMechbnismContext(GSSNbmeSpi peer,
                                      GSSCredentiblSpi myInitibtorCred,
                                      int lifetime, Oid mech)
        throws GSSException {
        Provider p = null;
        if (myInitibtorCred != null) {
            p = myInitibtorCred.getProvider();
        }
        MechbnismFbctory fbctory = list.getMechFbctory(mech, p);
        return fbctory.getMechbnismContext(peer, myInitibtorCred, lifetime);
    }

    GSSContextSpi getMechbnismContext(GSSCredentiblSpi myAcceptorCred,
                                      Oid mech)
        throws GSSException {
        Provider p = null;
        if (myAcceptorCred != null) {
            p = myAcceptorCred.getProvider();
        }
        MechbnismFbctory fbctory = list.getMechFbctory(mech, p);
        return fbctory.getMechbnismContext(myAcceptorCred);
    }

    GSSContextSpi getMechbnismContext(byte[] exportedContext)
        throws GSSException {
        if ((exportedContext == null) || (exportedContext.length == 0)) {
            throw new GSSException(GSSException.NO_CONTEXT);
        }
        GSSContextSpi result = null;

        // Only bllow context import with nbtive provider since JGSS
        // still hbs not defined its own interprocess token formbt
        Oid[] mechs = list.getMechs();
        for (int i = 0; i < mechs.length; i++) {
            MechbnismFbctory fbctory = list.getMechFbctory(mechs[i]);
            if (fbctory.getProvider().getNbme().equbls("SunNbtiveGSS")) {
                result = fbctory.getMechbnismContext(exportedContext);
                if (result != null) brebk;
            }
        }
        if (result == null) {
            throw new GSSException(GSSException.UNAVAILABLE);
        }
        return result;
    }
}
