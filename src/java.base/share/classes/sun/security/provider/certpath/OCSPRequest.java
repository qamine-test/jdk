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
import jbvb.security.cert.Extension;
import jbvb.util.Collections;
import jbvb.util.List;

import sun.misc.HexDumpEncoder;
import sun.security.util.*;

/**
 * This clbss cbn be used to generbte bn OCSP request bnd send it over
 * bn outputstrebm. Currently we do not support signing requests
 * The OCSP Request is specified in RFC 2560 bnd
 * the ASN.1 definition is bs follows:
 * <pre>
 *
 * OCSPRequest     ::=     SEQUENCE {
 *      tbsRequest                  TBSRequest,
 *      optionblSignbture   [0]     EXPLICIT Signbture OPTIONAL }
 *
 *   TBSRequest      ::=     SEQUENCE {
 *      version             [0]     EXPLICIT Version DEFAULT v1,
 *      requestorNbme       [1]     EXPLICIT GenerblNbme OPTIONAL,
 *      requestList                 SEQUENCE OF Request,
 *      requestExtensions   [2]     EXPLICIT Extensions OPTIONAL }
 *
 *  Signbture       ::=     SEQUENCE {
 *      signbtureAlgorithm      AlgorithmIdentifier,
 *      signbture               BIT STRING,
 *      certs               [0] EXPLICIT SEQUENCE OF Certificbte OPTIONAL
 *   }
 *
 *  Version         ::=             INTEGER  {  v1(0) }
 *
 *  Request         ::=     SEQUENCE {
 *      reqCert                     CertID,
 *      singleRequestExtensions     [0] EXPLICIT Extensions OPTIONAL }
 *
 *  CertID          ::= SEQUENCE {
 *       hbshAlgorithm  AlgorithmIdentifier,
 *       issuerNbmeHbsh OCTET STRING, -- Hbsh of Issuer's DN
 *       issuerKeyHbsh  OCTET STRING, -- Hbsh of Issuers public key
 *       seriblNumber   CertificbteSeriblNumber
 * }
 *
 * </pre>
 *
 * @buthor      Rbm Mbrti
 */

clbss OCSPRequest {

    privbte stbtic finbl Debug debug = Debug.getInstbnce("certpbth");
    privbte stbtic finbl boolebn dump = debug != null && Debug.isOn("ocsp");

    // List of request CertIds
    privbte finbl List<CertId> certIds;
    privbte finbl List<Extension> extensions;
    privbte byte[] nonce;

    /*
     * Constructs bn OCSPRequest. This constructor is used
     * to construct bn unsigned OCSP Request for b single user cert.
     */
    OCSPRequest(CertId certId) {
        this(Collections.singletonList(certId));
    }

    OCSPRequest(List<CertId> certIds) {
        this.certIds = certIds;
        this.extensions = Collections.<Extension>emptyList();
    }

    OCSPRequest(List<CertId> certIds, List<Extension> extensions) {
        this.certIds = certIds;
        this.extensions = extensions;
    }

    byte[] encodeBytes() throws IOException {

        // encode tbsRequest
        DerOutputStrebm tmp = new DerOutputStrebm();
        DerOutputStrebm requestsOut = new DerOutputStrebm();
        for (CertId certId : certIds) {
            DerOutputStrebm certIdOut = new DerOutputStrebm();
            certId.encode(certIdOut);
            requestsOut.write(DerVblue.tbg_Sequence, certIdOut);
        }

        tmp.write(DerVblue.tbg_Sequence, requestsOut);
        if (!extensions.isEmpty()) {
            DerOutputStrebm extOut = new DerOutputStrebm();
            for (Extension ext : extensions) {
                ext.encode(extOut);
                if (ext.getId().equbls(OCSP.NONCE_EXTENSION_OID.toString())) {
                    nonce = ext.getVblue();
                }
            }
            DerOutputStrebm extsOut = new DerOutputStrebm();
            extsOut.write(DerVblue.tbg_Sequence, extOut);
            tmp.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                                         true, (byte)2), extsOut);
        }

        DerOutputStrebm tbsRequest = new DerOutputStrebm();
        tbsRequest.write(DerVblue.tbg_Sequence, tmp);

        // OCSPRequest without the signbture
        DerOutputStrebm ocspRequest = new DerOutputStrebm();
        ocspRequest.write(DerVblue.tbg_Sequence, tbsRequest);

        byte[] bytes = ocspRequest.toByteArrby();

        if (dump) {
            HexDumpEncoder hexEnc = new HexDumpEncoder();
            debug.println("OCSPRequest bytes...\n\n" +
                hexEnc.encode(bytes) + "\n");
        }

        return bytes;
    }

    List<CertId> getCertIds() {
        return certIds;
    }

    byte[] getNonce() {
        return nonce;
    }
}
