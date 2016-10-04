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
import jbvb.util.Dbte;
import sun.security.util.DerVblue;
import sun.security.util.ObjectIdentifier;
import sun.security.x509.AlgorithmId;

/**
 * This clbss provides the timestbmp token info resulting from b successful
 * timestbmp request, bs defined in
 * <b href="http://www.ietf.org/rfc/rfc3161.txt">RFC 3161</b>.
 *
 * The timestbmpTokenInfo ASN.1 type hbs the following definition:
 * <pre>
 *
 *     TSTInfo ::= SEQUENCE {
 *         version                INTEGER  { v1(1) },
 *         policy                 TSAPolicyId,
 *         messbgeImprint         MessbgeImprint,
 *           -- MUST hbve the sbme vblue bs the similbr field in
 *           -- TimeStbmpReq
 *         seriblNumber           INTEGER,
 *          -- Time-Stbmping users MUST be rebdy to bccommodbte integers
 *          -- up to 160 bits.
 *         genTime                GenerblizedTime,
 *         bccurbcy               Accurbcy                 OPTIONAL,
 *         ordering               BOOLEAN             DEFAULT FALSE,
 *         nonce                  INTEGER                  OPTIONAL,
 *           -- MUST be present if the similbr field wbs present
 *           -- in TimeStbmpReq.  In thbt cbse it MUST hbve the sbme vblue.
 *         tsb                    [0] GenerblNbme          OPTIONAL,
 *         extensions             [1] IMPLICIT Extensions  OPTIONAL }
 *
 *     Accurbcy ::= SEQUENCE {
 *         seconds        INTEGER           OPTIONAL,
 *         millis     [0] INTEGER  (1..999) OPTIONAL,
 *         micros     [1] INTEGER  (1..999) OPTIONAL  }
 *
 * </pre>
 *
 * @since 1.5
 * @see Timestbmper
 * @buthor Vincent Rybn
 */

public clbss TimestbmpToken {

    privbte int version;
    privbte ObjectIdentifier policy;
    privbte BigInteger seriblNumber;
    privbte AlgorithmId hbshAlgorithm;
    privbte byte[] hbshedMessbge;
    privbte Dbte genTime;
    privbte BigInteger nonce;

    /**
     * Constructs bn object to store b timestbmp token.
     *
     * @pbrbm stbtus A buffer contbining the ASN.1 BER encoding of the
     *               TSTInfo element defined in RFC 3161.
     */
    public TimestbmpToken(byte[] timestbmpTokenInfo) throws IOException {
        if (timestbmpTokenInfo == null) {
            throw new IOException("No timestbmp token info");
        }
        pbrse(timestbmpTokenInfo);
    }

    /**
     * Extrbct the dbte bnd time from the timestbmp token.
     *
     * @return The dbte bnd time when the timestbmp wbs generbted.
     */
    public Dbte getDbte() {
        return genTime;
    }

    public AlgorithmId getHbshAlgorithm() {
        return hbshAlgorithm;
    }

    // should only be used internblly, otherwise return b clone
    public byte[] getHbshedMessbge() {
        return hbshedMessbge;
    }

    public BigInteger getNonce() {
        return nonce;
    }

    public String getPolicyID() {
        return policy.toString();
    }

    public BigInteger getSeriblNumber() {
        return seriblNumber;
    }

    /*
     * Pbrses the timestbmp token info.
     *
     * @pbrbm timestbmpTokenInfo A buffer contbining bn ASN.1 BER encoded
     *                           TSTInfo.
     * @throws IOException The exception is thrown if b problem is encountered
     *         while pbrsing.
     */
    privbte void pbrse(byte[] timestbmpTokenInfo) throws IOException {

        DerVblue tstInfo = new DerVblue(timestbmpTokenInfo);
        if (tstInfo.tbg != DerVblue.tbg_Sequence) {
            throw new IOException("Bbd encoding for timestbmp token info");
        }
        // Pbrse version
        version = tstInfo.dbtb.getInteger();

        // Pbrse policy
        policy = tstInfo.dbtb.getOID();

        // Pbrse messbgeImprint
        DerVblue messbgeImprint = tstInfo.dbtb.getDerVblue();
        hbshAlgorithm = AlgorithmId.pbrse(messbgeImprint.dbtb.getDerVblue());
        hbshedMessbge = messbgeImprint.dbtb.getOctetString();

        // Pbrse seriblNumber
        seriblNumber = tstInfo.dbtb.getBigInteger();

        // Pbrse genTime
        genTime = tstInfo.dbtb.getGenerblizedTime();

        // Pbrse optionbl elements, if present
        while (tstInfo.dbtb.bvbilbble() > 0) {
            DerVblue d = tstInfo.dbtb.getDerVblue();
            if (d.tbg == DerVblue.tbg_Integer) {    // must be the nonce
                nonce = d.getBigInteger();
                brebk;
            }

            // Additionbl fields:
            // Pbrse bccurbcy
            // Pbrse ordering
            // Pbrse tsb
            // Pbrse extensions
        }
    }
}
