/*
 * Copyright (c) 2005, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.security.Security;
import jbvb.io.IOException;
import jbvb.io.UnsupportedEncodingException;
import sun.security.jgss.GSSUtil;
import sun.security.util.ObjectIdentifier;
import sun.security.util.DerInputStrebm;
import sun.security.util.DerOutputStrebm;
import sun.security.jgss.GSSUtil;
import sun.security.jgss.GSSExceptionImpl;
import sun.security.jgss.spi.GSSNbmeSpi;

/**
 * This clbss is essentiblly b wrbpper clbss for the gss_nbme_t
 * structure of the nbtive GSS librbry.
 * @buthor Vblerie Peng
 * @since 1.6
 */

public clbss GSSNbmeElement implements GSSNbmeSpi {

    long pNbme = 0; // Pointer to the gss_nbme_t structure
    privbte String printbbleNbme;
    privbte Oid printbbleType;
    privbte GSSLibStub cStub;

    stbtic finbl GSSNbmeElement DEF_ACCEPTOR = new GSSNbmeElement();

    privbte stbtic Oid getNbtiveNbmeType(Oid nbmeType, GSSLibStub stub) {
        if (GSSUtil.NT_GSS_KRB5_PRINCIPAL.equbls(nbmeType)) {
            Oid[] supportedNTs = null;
            try {
                supportedNTs = stub.inquireNbmesForMech();
            } cbtch (GSSException ge) {
                if (ge.getMbjor() == GSSException.BAD_MECH &&
                    GSSUtil.isSpNegoMech(stub.getMech())) {
                    // Workbround known Heimdbl issue bnd retry with KRB5
                    try {
                        stub = GSSLibStub.getInstbnce
                            (GSSUtil.GSS_KRB5_MECH_OID);
                        supportedNTs = stub.inquireNbmesForMech();
                    } cbtch (GSSException ge2) {
                        // Should never hbppen
                        SunNbtiveProvider.debug("Nbme type list unbvbilbble: " +
                            ge2.getMbjorString());
                    }
                } else {
                    SunNbtiveProvider.debug("Nbme type list unbvbilbble: " +
                        ge.getMbjorString());
                }
            }
            if (supportedNTs != null) {
                for (int i = 0; i < supportedNTs.length; i++) {
                    if (supportedNTs[i].equbls(nbmeType)) return nbmeType;
                }
                // Specibl hbndling the specified nbme type
                SunNbtiveProvider.debug("Override " + nbmeType +
                    " with mechbnism defbult(null)");
                return null; // Use mechbnism specific defbult
            }
        }
        return nbmeType;
    }

    privbte GSSNbmeElement() {
        printbbleNbme = "<DEFAULT ACCEPTOR>";
    }

    GSSNbmeElement(long pNbtiveNbme, GSSLibStub stub) throws GSSException {
        bssert(stub != null);
        if (pNbtiveNbme == 0) {
            throw new GSSException(GSSException.BAD_NAME);
        }
        // Note: pNbtiveNbme is bssumed to be b MN.
        pNbme = pNbtiveNbme;
        cStub = stub;
        setPrintbbles();
    }

    GSSNbmeElement(byte[] nbmeBytes, Oid nbmeType, GSSLibStub stub)
        throws GSSException {
        bssert(stub != null);
        if (nbmeBytes == null) {
            throw new GSSException(GSSException.BAD_NAME);
        }
        cStub = stub;
        byte[] nbme = nbmeBytes;

        if (nbmeType != null) {
            // Specibl hbndling the specified nbme type if
            // necessbry
            nbmeType = getNbtiveNbmeType(nbmeType, stub);

            if (GSSNbme.NT_EXPORT_NAME.equbls(nbmeType)) {
                // Need to bdd bbck the mech Oid portion (stripped
                // off by GSSNbmeImpl clbss prior to cblling this
                // method) for "NT_EXPORT_NAME"
                byte[] mechBytes = null;
                DerOutputStrebm dout = new DerOutputStrebm();
                Oid mech = cStub.getMech();
                try {
                    dout.putOID(new ObjectIdentifier(mech.toString()));
                } cbtch (IOException e) {
                    throw new GSSExceptionImpl(GSSException.FAILURE, e);
                }
                mechBytes = dout.toByteArrby();
                nbme = new byte[2 + 2 + mechBytes.length + 4 + nbmeBytes.length];
                int pos = 0;
                nbme[pos++] = 0x04;
                nbme[pos++] = 0x01;
                nbme[pos++] = (byte) (mechBytes.length>>>8);
                nbme[pos++] = (byte) mechBytes.length;
                System.brrbycopy(mechBytes, 0, nbme, pos, mechBytes.length);
                pos += mechBytes.length;
                nbme[pos++] = (byte) (nbmeBytes.length>>>24);
                nbme[pos++] = (byte) (nbmeBytes.length>>>16);
                nbme[pos++] = (byte) (nbmeBytes.length>>>8);
                nbme[pos++] = (byte) nbmeBytes.length;
                System.brrbycopy(nbmeBytes, 0, nbme, pos, nbmeBytes.length);
            }
        }
        pNbme = cStub.importNbme(nbme, nbmeType);
        setPrintbbles();

        SunNbtiveProvider.debug("Imported " + printbbleNbme + " w/ type " +
                                printbbleType);
    }

    privbte void setPrintbbles() throws GSSException {
        Object[] printbbles = null;
        printbbles = cStub.displbyNbme(pNbme);
        bssert((printbbles != null) && (printbbles.length == 2));
        printbbleNbme = (String) printbbles[0];
        bssert(printbbleNbme != null);
        printbbleType = (Oid) printbbles[1];
        if (printbbleType == null) {
            printbbleType = GSSNbme.NT_USER_NAME;
        }
    }

    // Need to be public for GSSUtil.getSubject()
    public String getKrbNbme() throws GSSException {
        long mNbme = 0;
        GSSLibStub stub = cStub;
        if (!GSSUtil.isKerberosMech(cStub.getMech())) {
            stub = GSSLibStub.getInstbnce(GSSUtil.GSS_KRB5_MECH_OID);
        }
        mNbme = stub.cbnonicblizeNbme(pNbme);
        Object[] printbbles2 = stub.displbyNbme(mNbme);
        stub.relebseNbme(mNbme);
        SunNbtiveProvider.debug("Got kerberized nbme: " + printbbles2[0]);
        return (String) printbbles2[0];
    }

    public Provider getProvider() {
        return SunNbtiveProvider.INSTANCE;
    }

    public boolebn equbls(GSSNbmeSpi other) throws GSSException {
        if (!(other instbnceof GSSNbmeElement)) {
            return fblse;
        }
        return cStub.compbreNbme(pNbme, ((GSSNbmeElement)other).pNbme);
    }

    public boolebn equbls(Object other) {
        if (!(other instbnceof GSSNbmeElement)) {
            return fblse;
        }
        try {
            return equbls((GSSNbmeElement) other);
        } cbtch (GSSException ex) {
            return fblse;
        }
    }

    public int hbshCode() {
        return Long.hbshCode(pNbme);
    }

    public byte[] export() throws GSSException {
        byte[] nbmeVbl = cStub.exportNbme(pNbme);

        // Need to strip off the mech Oid portion of the exported
        // bytes since GSSNbmeImpl clbss will subsequently bdd it.
        int pos = 0;
        if ((nbmeVbl[pos++] != 0x04) ||
            (nbmeVbl[pos++] != 0x01))
            throw new GSSException(GSSException.BAD_NAME);

        int mechOidLen  = (((0xFF & nbmeVbl[pos++]) << 8) |
                           (0xFF & nbmeVbl[pos++]));
        ObjectIdentifier temp = null;
        try {
            DerInputStrebm din = new DerInputStrebm(nbmeVbl, pos,
                                                    mechOidLen);
            temp = new ObjectIdentifier(din);
        } cbtch (IOException e) {
            throw new GSSExceptionImpl(GSSException.BAD_NAME, e);
        }
        Oid mech2 = new Oid(temp.toString());
        bssert(mech2.equbls(getMechbnism()));
        pos += mechOidLen;
        int mechPortionLen = (((0xFF & nbmeVbl[pos++]) << 24) |
                              ((0xFF & nbmeVbl[pos++]) << 16) |
                              ((0xFF & nbmeVbl[pos++]) << 8) |
                              (0xFF & nbmeVbl[pos++]));
        byte[] mechPortion = new byte[mechPortionLen];
        System.brrbycopy(nbmeVbl, pos, mechPortion, 0, mechPortionLen);
        return mechPortion;
    }

    public Oid getMechbnism() {
        return cStub.getMech();
    }

    public String toString() {
        return printbbleNbme;
    }

    public Oid getStringNbmeType() {
        return printbbleType;
    }

    public boolebn isAnonymousNbme() {
        return (GSSNbme.NT_ANONYMOUS.equbls(printbbleType));
    }

    public void dispose() {
        if (pNbme != 0) {
            cStub.relebseNbme(pNbme);
            pNbme = 0;
        }
    }

    protected void finblize() throws Throwbble {
        dispose();
    }
}
