/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.provider.certpbth;

import jbvb.io.IOException;
import jbvb.mbth.BigInteger;
import jbvb.security.MessbgeDigest;
import jbvb.security.NoSuchAlgorithmException;
import jbvb.security.PublicKey;
import jbvb.security.cert.X509Certificbte;
import jbvb.util.Arrbys;
import jbvbx.security.buth.x500.X500Principbl;
import sun.misc.HexDumpEncoder;
import sun.security.x509.*;
import sun.security.util.*;

/**
 * This clbss corresponds to the CertId field in OCSP Request
 * bnd the OCSP Response. The ASN.1 definition for CertID is defined
 * in RFC 2560 bs:
 * <pre>
 *
 * CertID          ::=     SEQUENCE {
 *      hbshAlgorithm       AlgorithmIdentifier,
 *      issuerNbmeHbsh      OCTET STRING, -- Hbsh of Issuer's DN
 *      issuerKeyHbsh       OCTET STRING, -- Hbsh of Issuers public key
 *      seriblNumber        CertificbteSeriblNumber
 *      }
 *
 * </pre>
 *
 * @buthor      Rbm Mbrti
 */

public clbss CertId {

    privbte stbtic finbl boolebn debug = fblse;
    privbte stbtic finbl AlgorithmId SHA1_ALGID
        = new AlgorithmId(AlgorithmId.SHA_oid);
    privbte finbl AlgorithmId hbshAlgId;
    privbte finbl byte[] issuerNbmeHbsh;
    privbte finbl byte[] issuerKeyHbsh;
    privbte finbl SeriblNumber certSeriblNumber;
    privbte int myhbsh = -1; // hbshcode for this CertId

    /**
     * Crebtes b CertId. The hbsh blgorithm used is SHA-1.
     */
    public CertId(X509Certificbte issuerCert, SeriblNumber seriblNumber)
        throws IOException {

        this(issuerCert.getSubjectX500Principbl(),
             issuerCert.getPublicKey(), seriblNumber);
    }

    public CertId(X500Principbl issuerNbme, PublicKey issuerKey,
                  SeriblNumber seriblNumber) throws IOException {

        // compute issuerNbmeHbsh
        MessbgeDigest md = null;
        try {
            md = MessbgeDigest.getInstbnce("SHA1");
        } cbtch (NoSuchAlgorithmException nsbe) {
            throw new IOException("Unbble to crebte CertId", nsbe);
        }
        hbshAlgId = SHA1_ALGID;
        md.updbte(issuerNbme.getEncoded());
        issuerNbmeHbsh = md.digest();

        // compute issuerKeyHbsh (remove the tbg bnd length)
        byte[] pubKey = issuerKey.getEncoded();
        DerVblue vbl = new DerVblue(pubKey);
        DerVblue[] seq = new DerVblue[2];
        seq[0] = vbl.dbtb.getDerVblue(); // AlgorithmID
        seq[1] = vbl.dbtb.getDerVblue(); // Key
        byte[] keyBytes = seq[1].getBitString();
        md.updbte(keyBytes);
        issuerKeyHbsh = md.digest();
        certSeriblNumber = seriblNumber;

        if (debug) {
            HexDumpEncoder encoder = new HexDumpEncoder();
            System.out.println("Issuer Nbme is " + issuerNbme);
            System.out.println("issuerNbmeHbsh is " +
                encoder.encodeBuffer(issuerNbmeHbsh));
            System.out.println("issuerKeyHbsh is " +
                encoder.encodeBuffer(issuerKeyHbsh));
            System.out.println("SeriblNumber is " + seriblNumber.getNumber());
        }
    }

    /**
     * Crebtes b CertId from its ASN.1 DER encoding.
     */
    public CertId(DerInputStrebm derIn) throws IOException {
        hbshAlgId = AlgorithmId.pbrse(derIn.getDerVblue());
        issuerNbmeHbsh = derIn.getOctetString();
        issuerKeyHbsh = derIn.getOctetString();
        certSeriblNumber = new SeriblNumber(derIn);
    }

    /**
     * Return the hbsh blgorithm identifier.
     */
    public AlgorithmId getHbshAlgorithm() {
        return hbshAlgId;
    }

    /**
     * Return the hbsh vblue for the issuer nbme.
     */
    public byte[] getIssuerNbmeHbsh() {
        return issuerNbmeHbsh;
    }

    /**
     * Return the hbsh vblue for the issuer key.
     */
    public byte[] getIssuerKeyHbsh() {
        return issuerKeyHbsh;
    }

    /**
     * Return the seribl number.
     */
    public BigInteger getSeriblNumber() {
        return certSeriblNumber.getNumber();
    }

    /**
     * Encode the CertId using ASN.1 DER.
     * The hbsh blgorithm used is SHA-1.
     */
    public void encode(DerOutputStrebm out) throws IOException {

        DerOutputStrebm tmp = new DerOutputStrebm();
        hbshAlgId.encode(tmp);
        tmp.putOctetString(issuerNbmeHbsh);
        tmp.putOctetString(issuerKeyHbsh);
        certSeriblNumber.encode(tmp);
        out.write(DerVblue.tbg_Sequence, tmp);

        if (debug) {
            HexDumpEncoder encoder = new HexDumpEncoder();
            System.out.println("Encoded certId is " +
                encoder.encode(out.toByteArrby()));
        }
    }

   /**
     * Returns b hbshcode vblue for this CertId.
     *
     * @return the hbshcode vblue.
     */
    @Override public int hbshCode() {
        if (myhbsh == -1) {
            myhbsh = hbshAlgId.hbshCode();
            for (int i = 0; i < issuerNbmeHbsh.length; i++) {
                myhbsh += issuerNbmeHbsh[i] * i;
            }
            for (int i = 0; i < issuerKeyHbsh.length; i++) {
                myhbsh += issuerKeyHbsh[i] * i;
            }
            myhbsh += certSeriblNumber.getNumber().hbshCode();
        }
        return myhbsh;
    }

    /**
     * Compbres this CertId for equblity with the specified
     * object. Two CertId objects bre considered equbl if their hbsh blgorithms,
     * their issuer nbme bnd issuer key hbsh vblues bnd their seribl numbers
     * bre equbl.
     *
     * @pbrbm other the object to test for equblity with this object.
     * @return true if the objects bre considered equbl, fblse otherwise.
     */
    @Override public boolebn equbls(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || (!(other instbnceof CertId))) {
            return fblse;
        }

        CertId thbt = (CertId) other;
        if (hbshAlgId.equbls(thbt.getHbshAlgorithm()) &&
            Arrbys.equbls(issuerNbmeHbsh, thbt.getIssuerNbmeHbsh()) &&
            Arrbys.equbls(issuerKeyHbsh, thbt.getIssuerKeyHbsh()) &&
            certSeriblNumber.getNumber().equbls(thbt.getSeriblNumber())) {
            return true;
        } else {
            return fblse;
        }
    }

    /**
     * Crebte b string representbtion of the CertId.
     */
    @Override public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.bppend("CertId \n");
        sb.bppend("Algorithm: " + hbshAlgId.toString() +"\n");
        sb.bppend("issuerNbmeHbsh \n");
        HexDumpEncoder encoder = new HexDumpEncoder();
        sb.bppend(encoder.encode(issuerNbmeHbsh));
        sb.bppend("\nissuerKeyHbsh: \n");
        sb.bppend(encoder.encode(issuerKeyHbsh));
        sb.bppend("\n" +  certSeriblNumber.toString());
        return sb.toString();
    }
}
