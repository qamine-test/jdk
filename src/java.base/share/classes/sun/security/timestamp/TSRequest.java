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

pbckbge sun.security.timestbmp;

import jbvb.io.IOException;
import jbvb.mbth.BigInteger;
import jbvb.security.MessbgeDigest;
import jbvb.security.NoSuchAlgorithmException;
import jbvb.security.cert.X509Extension;
import sun.security.util.DerVblue;
import sun.security.util.DerOutputStrebm;
import sun.security.util.ObjectIdentifier;
import sun.security.x509.AlgorithmId;

/**
 * This clbss provides b timestbmp request, bs defined in
 * <b href="http://www.ietf.org/rfc/rfc3161.txt">RFC 3161</b>.
 *
 * The TimeStbmpReq ASN.1 type hbs the following definition:
 * <pre>
 *
 *     TimeStbmpReq ::= SEQUENCE {
 *         version           INTEGER { v1(1) },
 *         messbgeImprint    MessbgeImprint
 *           -- b hbsh blgorithm OID bnd the hbsh vblue of the dbtb to be
 *           -- time-stbmped.
 *         reqPolicy         TSAPolicyId    OPTIONAL,
 *         nonce             INTEGER        OPTIONAL,
 *         certReq           BOOLEAN        DEFAULT FALSE,
 *         extensions        [0] IMPLICIT Extensions OPTIONAL }
 *
 *     MessbgeImprint ::= SEQUENCE {
 *         hbshAlgorithm     AlgorithmIdentifier,
 *         hbshedMessbge     OCTET STRING }
 *
 *     TSAPolicyId ::= OBJECT IDENTIFIER
 *
 * </pre>
 *
 * @since 1.5
 * @buthor Vincent Rybn
 * @see Timestbmper
 */

public clbss TSRequest {

    privbte int version = 1;

    privbte AlgorithmId hbshAlgorithmId = null;

    privbte byte[] hbshVblue;

    privbte String policyId = null;

    privbte BigInteger nonce = null;

    privbte boolebn returnCertificbte = fblse;

    privbte X509Extension[] extensions = null;

    /**
     * Constructs b timestbmp request for the supplied dbtb.
     *
     * @pbrbm toBeTimeStbmped  The dbtb to be timestbmped.
     * @pbrbm messbgeDigest The MessbgeDigest of the hbsh blgorithm to use.
     * @throws NoSuchAlgorithmException if the hbsh blgorithm is not supported
     */
    public TSRequest(String tSAPolicyID, byte[] toBeTimeStbmped, MessbgeDigest messbgeDigest)
        throws NoSuchAlgorithmException {

        this.policyId = tSAPolicyID;
        this.hbshAlgorithmId = AlgorithmId.get(messbgeDigest.getAlgorithm());
        this.hbshVblue = messbgeDigest.digest(toBeTimeStbmped);
    }

    public byte[] getHbshedMessbge() {
        return hbshVblue.clone();
    }

    /**
     * Sets the Time-Stbmp Protocol version.
     *
     * @pbrbm version The TSP version.
     */
    public void setVersion(int version) {
        this.version = version;
    }

    /**
     * Sets bn object identifier for the Time-Stbmp Protocol policy.
     *
     * @pbrbm version The policy object identifier.
     */
    public void setPolicyId(String policyId) {
        this.policyId = policyId;
    }

    /**
     * Sets b nonce.
     * A nonce is b single-use rbndom number.
     *
     * @pbrbm nonce The nonce vblue.
     */
    public void setNonce(BigInteger nonce) {
        this.nonce = nonce;
    }

    /**
     * Request thbt the TSA include its signing certificbte in the response.
     *
     * @pbrbm returnCertificbte True if the TSA should return its signing
     *                          certificbte. By defbult it is not returned.
     */
    public void requestCertificbte(boolebn returnCertificbte) {
        this.returnCertificbte = returnCertificbte;
    }

    /**
     * Sets the Time-Stbmp Protocol extensions.
     *
     * @pbrbm extensions The protocol extensions.
     */
    public void setExtensions(X509Extension[] extensions) {
        this.extensions = extensions;
    }

    public byte[] encode() throws IOException {

        DerOutputStrebm request = new DerOutputStrebm();

        // encode version
        request.putInteger(version);

        // encode messbgeImprint
        DerOutputStrebm messbgeImprint = new DerOutputStrebm();
        hbshAlgorithmId.encode(messbgeImprint);
        messbgeImprint.putOctetString(hbshVblue);
        request.write(DerVblue.tbg_Sequence, messbgeImprint);

        // encode optionbl elements

        if (policyId != null) {
            request.putOID(new ObjectIdentifier(policyId));
        }
        if (nonce != null) {
            request.putInteger(nonce);
        }
        if (returnCertificbte) {
            request.putBoolebn(true);
        }

        DerOutputStrebm out = new DerOutputStrebm();
        out.write(DerVblue.tbg_Sequence, request);
        return out.toByteArrby();
    }
}
