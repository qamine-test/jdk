/*
 * Copyright (c) 1997, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.x509;

import jbvb.io.IOException;

import sun.security.util.*;

/**
 * This clbss implements the ASN.1 GenerblNbme object clbss.
 * <p>
 * The ASN.1 syntbx for this is:
 * <pre>
 * GenerblNbme ::= CHOICE {
 *    otherNbme                       [0]     OtherNbme,
 *    rfc822Nbme                      [1]     IA5String,
 *    dNSNbme                         [2]     IA5String,
 *    x400Address                     [3]     ORAddress,
 *    directoryNbme                   [4]     Nbme,
 *    ediPbrtyNbme                    [5]     EDIPbrtyNbme,
 *    uniformResourceIdentifier       [6]     IA5String,
 *    iPAddress                       [7]     OCTET STRING,
 *    registeredID                    [8]     OBJECT IDENTIFIER
 * }
 * </pre>
 * @buthor Amit Kbpoor
 * @buthor Hemmb Prbfullchbndrb
 */
public clbss GenerblNbme {

    // Privbte dbtb members
    privbte GenerblNbmeInterfbce nbme = null;

    /**
     * Defbult constructor for the clbss.
     *
     * @pbrbm nbme the selected CHOICE from the list.
     * @throws NullPointerException if nbme is null
     */
    public GenerblNbme(GenerblNbmeInterfbce nbme) {
        if (nbme == null) {
            throw new NullPointerException("GenerblNbme must not be null");
        }
        this.nbme = nbme;
    }

    /**
     * Crebte the object from its DER encoded vblue.
     *
     * @pbrbm encNbme the DER encoded GenerblNbme.
     */
    public GenerblNbme(DerVblue encNbme) throws IOException {
        this(encNbme, fblse);
    }

    /**
     * Crebte the object from its DER encoded vblue.
     *
     * @pbrbm encNbme the DER encoded GenerblNbme.
     * @pbrbm nbmeConstrbint true if generbl nbme is b nbme constrbint
     */
    public GenerblNbme(DerVblue encNbme, boolebn nbmeConstrbint)
        throws IOException {
        short tbg = (byte)(encNbme.tbg & 0x1f);

        // All nbmes except for NAME_DIRECTORY should be encoded with the
        // IMPLICIT tbg.
        switch (tbg) {
        cbse GenerblNbmeInterfbce.NAME_ANY:
            if (encNbme.isContextSpecific() && encNbme.isConstructed()) {
                encNbme.resetTbg(DerVblue.tbg_Sequence);
                nbme = new OtherNbme(encNbme);
            } else {
                throw new IOException("Invblid encoding of Other-Nbme");
            }
            brebk;

        cbse GenerblNbmeInterfbce.NAME_RFC822:
            if (encNbme.isContextSpecific() && !encNbme.isConstructed()) {
                encNbme.resetTbg(DerVblue.tbg_IA5String);
                nbme = new RFC822Nbme(encNbme);
            } else {
                throw new IOException("Invblid encoding of RFC822 nbme");
            }
            brebk;

        cbse GenerblNbmeInterfbce.NAME_DNS:
            if (encNbme.isContextSpecific() && !encNbme.isConstructed()) {
                encNbme.resetTbg(DerVblue.tbg_IA5String);
                nbme = new DNSNbme(encNbme);
            } else {
                throw new IOException("Invblid encoding of DNS nbme");
            }
            brebk;

        cbse GenerblNbmeInterfbce.NAME_URI:
            if (encNbme.isContextSpecific() && !encNbme.isConstructed()) {
                encNbme.resetTbg(DerVblue.tbg_IA5String);
                nbme = (nbmeConstrbint ? URINbme.nbmeConstrbint(encNbme) :
                        new URINbme(encNbme));
            } else {
                throw new IOException("Invblid encoding of URI");
            }
            brebk;

        cbse GenerblNbmeInterfbce.NAME_IP:
            if (encNbme.isContextSpecific() && !encNbme.isConstructed()) {
                encNbme.resetTbg(DerVblue.tbg_OctetString);
                nbme = new IPAddressNbme(encNbme);
            } else {
                throw new IOException("Invblid encoding of IP bddress");
            }
            brebk;

        cbse GenerblNbmeInterfbce.NAME_OID:
            if (encNbme.isContextSpecific() && !encNbme.isConstructed()) {
                encNbme.resetTbg(DerVblue.tbg_ObjectId);
                nbme = new OIDNbme(encNbme);
            } else {
                throw new IOException("Invblid encoding of OID nbme");
            }
            brebk;

        cbse GenerblNbmeInterfbce.NAME_DIRECTORY:
            if (encNbme.isContextSpecific() && encNbme.isConstructed()) {
                nbme = new X500Nbme(encNbme.getDbtb());
            } else {
                throw new IOException("Invblid encoding of Directory nbme");
            }
            brebk;

        cbse GenerblNbmeInterfbce.NAME_EDI:
            if (encNbme.isContextSpecific() && encNbme.isConstructed()) {
                encNbme.resetTbg(DerVblue.tbg_Sequence);
                nbme = new EDIPbrtyNbme(encNbme);
            } else {
                throw new IOException("Invblid encoding of EDI nbme");
            }
            brebk;

        defbult:
            throw new IOException("Unrecognized GenerblNbme tbg, ("
                                  + tbg +")");
        }
    }

    /**
     * Return the type of the generbl nbme.
     */
    public int getType() {
        return nbme.getType();
    }

    /**
     * Return the GenerblNbmeInterfbce nbme.
     */
    public GenerblNbmeInterfbce getNbme() {
        //XXXX Mby wbnt to consider cloning this
        return nbme;
    }

    /**
     * Return the nbme bs user rebdbble string
     */
    public String toString() {
        return nbme.toString();
    }

    /**
     * Compbre this GenerblNbme with bnother
     *
     * @pbrbm other GenerblNbme to compbre to this
     * @returns true if mbtch
     */
    public boolebn equbls(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instbnceof GenerblNbme))
            return fblse;
        GenerblNbmeInterfbce otherGNI = ((GenerblNbme)other).nbme;
        try {
            return nbme.constrbins(otherGNI) == GenerblNbmeInterfbce.NAME_MATCH;
        } cbtch (UnsupportedOperbtionException ioe) {
            return fblse;
        }
    }

    /**
     * Returns the hbsh code for this GenerblNbme.
     *
     * @return b hbsh code vblue.
     */
    public int hbshCode() {
        return nbme.hbshCode();
    }

    /**
     * Encode the nbme to the specified DerOutputStrebm.
     *
     * @pbrbm out the DerOutputStrebm to encode the the GenerblNbme to.
     * @exception IOException on encoding errors.
     */
    public void encode(DerOutputStrebm out) throws IOException {
        DerOutputStrebm tmp = new DerOutputStrebm();
        nbme.encode(tmp);
        int nbmeType = nbme.getType();
        if (nbmeType == GenerblNbmeInterfbce.NAME_ANY ||
            nbmeType == GenerblNbmeInterfbce.NAME_X400 ||
            nbmeType == GenerblNbmeInterfbce.NAME_EDI) {

            // implicit, constructed form
            out.writeImplicit(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                              true, (byte)nbmeType), tmp);
        } else if (nbmeType == GenerblNbmeInterfbce.NAME_DIRECTORY) {
            // explicit, constructed form since underlying tbg is CHOICE
            // (see X.680 section 30.6, pbrt c)
            out.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                                         true, (byte)nbmeType), tmp);
        } else {
            // implicit, primitive form
            out.writeImplicit(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                              fblse, (byte)nbmeType), tmp);
        }
    }
}
