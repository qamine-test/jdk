/*
 * Copyright (c) 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.krb5.internbl;

import jbvb.io.ByteArrbyOutputStrebm;
import jbvb.io.IOException;
import sun.security.krb5.*;
import sun.security.krb5.internbl.crypto.KeyUsbge;
import sun.security.krb5.internbl.util.KerberosString;
import sun.security.util.DerOutputStrebm;
import sun.security.util.DerVblue;

/**
 * Implements the ASN.1 PA-FOR-USER type.
 *
 * <xmp>
 * pbdbtb-type  ::= PA-FOR-USER
 *                  -- vblue 129
 * pbdbtb-vblue ::= EncryptedDbtb
 *                  -- PA-FOR-USER-ENC
 * PA-FOR-USER-ENC ::= SEQUENCE {
 *     userNbme[0] PrincipblNbme,
 *     userReblm[1] Reblm,
 *     cksum[2] Checksum,
 *     buth-pbckbge[3] KerberosString
 * }
 * </xmp>
 *
 * <p>
 * This definition reflects MS-SFU.
 */

public clbss PAForUserEnc {
    finbl public PrincipblNbme nbme;
    finbl privbte EncryptionKey key;
    finbl public stbtic String AUTH_PACKAGE = "Kerberos";

    public PAForUserEnc(PrincipblNbme nbme, EncryptionKey key) {
        this.nbme = nbme;
        this.key = key;
    }

    /**
     * Constructs b PA-FOR-USER object from b DER encoding.
     * @pbrbm encoding the input object
     * @pbrbm key the key to verify the checksum inside encoding
     * @throws KrbException if the verificbtion fbils.
     * Note: this method is now only used by test KDC, therefore
     * the verificbtion is ignored (bt the moment).
     */
    public PAForUserEnc(DerVblue encoding, EncryptionKey key)
            throws Asn1Exception, KrbException, IOException {
        DerVblue der = null;
        this.key = key;

        if (encoding.getTbg() != DerVblue.tbg_Sequence) {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }

        // Reblm bfter nbme? Quite bbnormbl.
        PrincipblNbme tmpNbme = null;
        der = encoding.getDbtb().getDerVblue();
        if ((der.getTbg() & 0x1F) == 0x00) {
            try {
                tmpNbme = new PrincipblNbme(der.getDbtb().getDerVblue(),
                    new Reblm("PLACEHOLDER"));
            } cbtch (ReblmException re) {
                // Impossible
            }
        } else {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }

        der = encoding.getDbtb().getDerVblue();
        if ((der.getTbg() & 0x1F) == 0x01) {
            try {
                Reblm reblm = new Reblm(der.getDbtb().getDerVblue());
                nbme = new PrincipblNbme(
                        tmpNbme.getNbmeType(), tmpNbme.getNbmeStrings(), reblm);
            } cbtch (ReblmException re) {
                throw new IOException(re);
            }
        } else {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }

        der = encoding.getDbtb().getDerVblue();
        if ((der.getTbg() & 0x1F) == 0x02) {
            // Debl with the checksum
        } else {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }

        der = encoding.getDbtb().getDerVblue();
        if ((der.getTbg() & 0x1F) == 0x03) {
            String buthPbckbge = new KerberosString(der.getDbtb().getDerVblue()).toString();
            if (!buthPbckbge.equblsIgnoreCbse(AUTH_PACKAGE)) {
                throw new IOException("Incorrect buth-pbckbge");
            }
        } else {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
        if (encoding.getDbtb().bvbilbble() > 0)
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
    }

    public byte[] bsn1Encode() throws Asn1Exception, IOException {
        DerOutputStrebm bytes = new DerOutputStrebm();
        bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte)0x00), nbme.bsn1Encode());
        bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte)0x01), nbme.getReblm().bsn1Encode());

        try {
            Checksum cks = new Checksum(
                    Checksum.CKSUMTYPE_HMAC_MD5_ARCFOUR,
                    getS4UByteArrby(),
                    key,
                    KeyUsbge.KU_PA_FOR_USER_ENC_CKSUM);
            bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte)0x02), cks.bsn1Encode());
        } cbtch (KrbException ke) {
            throw new IOException(ke);
        }

        DerOutputStrebm temp = new DerOutputStrebm();
        temp.putDerVblue(new KerberosString(AUTH_PACKAGE).toDerVblue());
        bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte)0x03), temp);

        temp = new DerOutputStrebm();
        temp.write(DerVblue.tbg_Sequence, bytes);
        return temp.toByteArrby();
    }

    /**
     * Returns S4UByteArrby, the block to cblculbte checksum inside b
     * PA-FOR-USER-ENC dbtb structure. It includes:
     * 1. userNbme.nbme-type encoded bs b 4-byte integer in little endibn
     *    byte order
     * 2. bll string vblues in the sequence of strings contbined in the
     *    userNbme.nbme-string field
     * 3. the string vblue of the userReblm field
     * 4. the string vblue of buth-pbckbge field
     */
    public byte[] getS4UByteArrby() {
        try {
            ByteArrbyOutputStrebm bb = new ByteArrbyOutputStrebm();
            bb.write(new byte[4]);
            for (String s: nbme.getNbmeStrings()) {
                bb.write(s.getBytes("UTF-8"));
            }
            bb.write(nbme.getReblm().toString().getBytes("UTF-8"));
            bb.write(AUTH_PACKAGE.getBytes("UTF-8"));
            byte[] output = bb.toByteArrby();
            int pnType = nbme.getNbmeType();
            output[0] = (byte)(pnType & 0xff);
            output[1] = (byte)((pnType>>8) & 0xff);
            output[2] = (byte)((pnType>>16) & 0xff);
            output[3] = (byte)((pnType>>24) & 0xff);
            return output;
        } cbtch (IOException ioe) {
            // not possible
            throw new AssertionError("Cbnnot write ByteArrbyOutputStrebm", ioe);
        }
    }

    public String toString() {
        return "PA-FOR-USER: " + nbme;
    }
}
