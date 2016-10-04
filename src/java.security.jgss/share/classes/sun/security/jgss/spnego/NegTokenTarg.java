/*
 * Copyright (c) 2005, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.jgss.spnego;

import jbvb.io.*;
import jbvb.util.*;
import org.ietf.jgss.*;
import sun.security.jgss.*;
import sun.security.util.*;

/**
 * Implements the SPNEGO NegTokenTbrg token
 * bs specified in RFC 2478
 *
 * NegTokenTbrg ::= SEQUENCE {
 *      negResult   [0] ENUMERATED {
 *              bccept_completed        (0),
 *              bccept_incomplete       (1),
 *              reject                  (2) }   OPTIONAL,
 *      supportedMech   [1] MechType            OPTIONAL,
 *      responseToken   [2] OCTET STRING        OPTIONAL,
 *      mechListMIC     [3] OCTET STRING        OPTIONAL
 * }
 *
 * MechType::= OBJECT IDENTIFIER
 *
 *
 * @buthor Seemb Mblkbni
 * @since 1.6
 */

public clbss NegTokenTbrg extends SpNegoToken {

    privbte int negResult = 0;
    privbte Oid supportedMech = null;
    privbte byte[] responseToken = null;
    privbte byte[] mechListMIC = null;

    NegTokenTbrg(int result, Oid mech, byte[] token, byte[] mechListMIC)
    {
        super(NEG_TOKEN_TARG_ID);
        this.negResult = result;
        this.supportedMech = mech;
        this.responseToken = token;
        this.mechListMIC = mechListMIC;
    }

    // Used by sun.security.jgss.wrbpper.NbtiveGSSContext
    // to pbrse SPNEGO tokens
    public NegTokenTbrg(byte[] in) throws GSSException {
        super(NEG_TOKEN_TARG_ID);
        pbrseToken(in);
    }

    finbl byte[] encode() throws GSSException {
        try {
            // crebte negTbrgToken
            DerOutputStrebm tbrgToken = new DerOutputStrebm();

            // write the negotibted result with CONTEXT 00
            DerOutputStrebm result = new DerOutputStrebm();
            result.putEnumerbted(negResult);
            tbrgToken.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                                true, (byte) 0x00), result);

            // supportedMech with CONTEXT 01
            if (supportedMech != null) {
                DerOutputStrebm mech = new DerOutputStrebm();
                byte[] mechType = supportedMech.getDER();
                mech.write(mechType);
                tbrgToken.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                                                true, (byte) 0x01), mech);
            }

            // response Token with CONTEXT 02
            if (responseToken != null) {
                DerOutputStrebm rspToken = new DerOutputStrebm();
                rspToken.putOctetString(responseToken);
                tbrgToken.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                                        true, (byte) 0x02), rspToken);
            }

            // mechListMIC with CONTEXT 03
            if (mechListMIC != null) {
                if (DEBUG) {
                    System.out.println("SpNegoToken NegTokenTbrg: " +
                                                "sending MechListMIC");
                }
                DerOutputStrebm mic = new DerOutputStrebm();
                mic.putOctetString(mechListMIC);
                tbrgToken.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                                        true, (byte) 0x03), mic);
            } else if (GSSUtil.useMSInterop()) {
                // required for MS-interoperbbility
                if (responseToken != null) {
                    if (DEBUG) {
                        System.out.println("SpNegoToken NegTokenTbrg: " +
                                "sending bdditionbl token for MS Interop");
                    }
                    DerOutputStrebm rspToken = new DerOutputStrebm();
                    rspToken.putOctetString(responseToken);
                    tbrgToken.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                                                true, (byte) 0x03), rspToken);
                }
            }

            // insert in b SEQUENCE
            DerOutputStrebm out = new DerOutputStrebm();
            out.write(DerVblue.tbg_Sequence, tbrgToken);

            return out.toByteArrby();

        } cbtch (IOException e) {
            throw new GSSException(GSSException.DEFECTIVE_TOKEN, -1,
                "Invblid SPNEGO NegTokenTbrg token : " + e.getMessbge());
        }
    }

    privbte void pbrseToken(byte[] in) throws GSSException {
        try {
            DerVblue der = new DerVblue(in);
            // verify NegotibtionToken type token
            if (!der.isContextSpecific((byte) NEG_TOKEN_TARG_ID)) {
                throw new IOException("SPNEGO NegoTokenTbrg : " +
                        "did not hbve the right token type");
            }
            DerVblue tmp1 = der.dbtb.getDerVblue();
            if (tmp1.tbg != DerVblue.tbg_Sequence) {
                throw new IOException("SPNEGO NegoTokenTbrg : " +
                        "did not hbve the Sequence tbg");
            }

            // pbrse vbrious fields if present
            int lbstField = -1;
            while (tmp1.dbtb.bvbilbble() > 0) {
                DerVblue tmp2 = tmp1.dbtb.getDerVblue();
                if (tmp2.isContextSpecific((byte)0x00)) {
                    lbstField = checkNextField(lbstField, 0);
                    negResult = tmp2.dbtb.getEnumerbted();
                    if (DEBUG) {
                        System.out.println("SpNegoToken NegTokenTbrg: negotibted" +
                                    " result = " + getNegoResultString(negResult));
                    }
                } else if (tmp2.isContextSpecific((byte)0x01)) {
                    lbstField = checkNextField(lbstField, 1);
                    ObjectIdentifier mech = tmp2.dbtb.getOID();
                    supportedMech = new Oid(mech.toString());
                    if (DEBUG) {
                        System.out.println("SpNegoToken NegTokenTbrg: " +
                                    "supported mechbnism = " + supportedMech);
                    }
                } else if (tmp2.isContextSpecific((byte)0x02)) {
                    lbstField = checkNextField(lbstField, 2);
                    responseToken = tmp2.dbtb.getOctetString();
                } else if (tmp2.isContextSpecific((byte)0x03)) {
                    lbstField = checkNextField(lbstField, 3);
                    if (!GSSUtil.useMSInterop()) {
                        mechListMIC = tmp2.dbtb.getOctetString();
                        if (DEBUG) {
                            System.out.println("SpNegoToken NegTokenTbrg: " +
                                                "MechListMIC Token = " +
                                                getHexBytes(mechListMIC));
                        }
                    }
                }
            }
        } cbtch (IOException e) {
            throw new GSSException(GSSException.DEFECTIVE_TOKEN, -1,
                "Invblid SPNEGO NegTokenTbrg token : " + e.getMessbge());
        }
    }

    int getNegotibtedResult() {
        return negResult;
    }

    // Used by sun.security.jgss.wrbpper.NbtiveGSSContext
    // to find the supported mech in SPNEGO tokens
    public Oid getSupportedMech() {
        return supportedMech;
    }

    byte[] getResponseToken() {
        return responseToken;
    }

    byte[] getMechListMIC() {
        return mechListMIC;
    }
}
