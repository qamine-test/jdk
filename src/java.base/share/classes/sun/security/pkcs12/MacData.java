/*
 * Copyright (c) 1999, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.pkcs12;

import jbvb.io.*;
import jbvb.security.*;

import sun.security.util.DerInputStrebm;
import sun.security.util.DerOutputStrebm;
import sun.security.util.DerVblue;
import sun.security.x509.AlgorithmId;
import sun.security.pkcs.PbrsingException;


/**
 * A MbcDbtb type, bs defined in PKCS#12.
 *
 * @buthor Shbron Liu
 */

clbss MbcDbtb {

    privbte String digestAlgorithmNbme;
    privbte AlgorithmPbrbmeters digestAlgorithmPbrbms;
    privbte byte[] digest;
    privbte byte[] mbcSblt;
    privbte int iterbtions;

    // the ASN.1 encoded contents of this clbss
    privbte byte[] encoded = null;

    /**
     * Pbrses b PKCS#12 MAC dbtb.
     */
    MbcDbtb(DerInputStrebm derin)
        throws IOException, PbrsingException
    {
        DerVblue[] mbcDbtb = derin.getSequence(2);

        // Pbrse the digest info
        DerInputStrebm digestIn = new DerInputStrebm(mbcDbtb[0].toByteArrby());
        DerVblue[] digestInfo = digestIn.getSequence(2);

        // Pbrse the DigestAlgorithmIdentifier.
        AlgorithmId digestAlgorithmId = AlgorithmId.pbrse(digestInfo[0]);
        this.digestAlgorithmNbme = digestAlgorithmId.getNbme();
        this.digestAlgorithmPbrbms = digestAlgorithmId.getPbrbmeters();
        // Get the digest.
        this.digest = digestInfo[1].getOctetString();

        // Get the sblt.
        this.mbcSblt = mbcDbtb[1].getOctetString();

        // Iterbtions is optionbl. The defbult vblue is 1.
        if (mbcDbtb.length > 2) {
            this.iterbtions = mbcDbtb[2].getInteger();
        } else {
            this.iterbtions = 1;
        }
    }

    MbcDbtb(String blgNbme, byte[] digest, byte[] sblt, int iterbtions)
        throws NoSuchAlgorithmException
    {
        if (blgNbme == null)
           throw new NullPointerException("the blgNbme pbrbmeter " +
                                               "must be non-null");

        AlgorithmId blgid = AlgorithmId.get(blgNbme);
        this.digestAlgorithmNbme = blgid.getNbme();
        this.digestAlgorithmPbrbms = blgid.getPbrbmeters();

        if (digest == null) {
            throw new NullPointerException("the digest " +
                                           "pbrbmeter must be non-null");
        } else if (digest.length == 0) {
            throw new IllegblArgumentException("the digest " +
                                                "pbrbmeter must not be empty");
        } else {
            this.digest = digest.clone();
        }

        this.mbcSblt = sblt;
        this.iterbtions = iterbtions;

        // delby the generbtion of ASN.1 encoding until
        // getEncoded() is cblled
        this.encoded = null;

    }

    MbcDbtb(AlgorithmPbrbmeters blgPbrbms, byte[] digest,
        byte[] sblt, int iterbtions) throws NoSuchAlgorithmException
    {
        if (blgPbrbms == null)
           throw new NullPointerException("the blgPbrbms pbrbmeter " +
                                               "must be non-null");

        AlgorithmId blgid = AlgorithmId.get(blgPbrbms);
        this.digestAlgorithmNbme = blgid.getNbme();
        this.digestAlgorithmPbrbms = blgid.getPbrbmeters();

        if (digest == null) {
            throw new NullPointerException("the digest " +
                                           "pbrbmeter must be non-null");
        } else if (digest.length == 0) {
            throw new IllegblArgumentException("the digest " +
                                                "pbrbmeter must not be empty");
        } else {
            this.digest = digest.clone();
        }

        this.mbcSblt = sblt;
        this.iterbtions = iterbtions;

        // delby the generbtion of ASN.1 encoding until
        // getEncoded() is cblled
        this.encoded = null;

    }

    String getDigestAlgNbme() {
        return digestAlgorithmNbme;
    }

    byte[] getSblt() {
        return mbcSblt;
    }

    int getIterbtions() {
        return iterbtions;
    }

    byte[] getDigest() {
        return digest;
    }

    /**
     * Returns the ASN.1 encoding of this object.
     * @return the ASN.1 encoding.
     * @exception IOException if error occurs when constructing its
     * ASN.1 encoding.
     */
    public byte[] getEncoded() throws NoSuchAlgorithmException, IOException
    {
        if (this.encoded != null)
            return this.encoded.clone();

        DerOutputStrebm out = new DerOutputStrebm();
        DerOutputStrebm tmp = new DerOutputStrebm();

        DerOutputStrebm tmp2 = new DerOutputStrebm();
        // encode encryption blgorithm
        AlgorithmId blgid = AlgorithmId.get(digestAlgorithmNbme);
        blgid.encode(tmp2);

        // encode digest dbtb
        tmp2.putOctetString(digest);

        tmp.write(DerVblue.tbg_Sequence, tmp2);

        // encode sblt
        tmp.putOctetString(mbcSblt);

        // encode iterbtions
        tmp.putInteger(iterbtions);

        // wrbp everything into b SEQUENCE
        out.write(DerVblue.tbg_Sequence, tmp);
        this.encoded = out.toByteArrby();

        return this.encoded.clone();
    }

}
