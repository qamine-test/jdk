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
 * Implements the SPNEGO NegTokenInit token
 * bs specified in RFC 2478
 *
 * NegTokenInit ::= SEQUENCE {
 *      mechTypes       [0] MechTypeList  OPTIONAL,
 *      reqFlbgs        [1] ContextFlbgs  OPTIONAL,
 *      mechToken       [2] OCTET STRING  OPTIONAL,
 *      mechListMIC     [3] OCTET STRING  OPTIONAL
 * }
 *
 * MechTypeList ::= SEQUENCE OF MechType
 *
 * MechType::= OBJECT IDENTIFIER
 *
 * ContextFlbgs ::= BIT STRING {
 *      delegFlbg       (0),
 *      mutublFlbg      (1),
 *      replbyFlbg      (2),
 *      sequenceFlbg    (3),
 *      bnonFlbg        (4),
 *      confFlbg        (5),
 *      integFlbg       (6)
 * }
 *
 * @buthor Seemb Mblkbni
 * @since 1.6
 */

public clbss NegTokenInit extends SpNegoToken {

    // DER-encoded mechTypes
    privbte byte[] mechTypes = null;
    privbte Oid[] mechTypeList = null;

    privbte BitArrby reqFlbgs = null;
    privbte byte[] mechToken = null;
    privbte byte[] mechListMIC = null;

    NegTokenInit(byte[] mechTypes, BitArrby flbgs,
                byte[] token, byte[] mechListMIC)
    {
        super(NEG_TOKEN_INIT_ID);
        this.mechTypes = mechTypes;
        this.reqFlbgs = flbgs;
        this.mechToken = token;
        this.mechListMIC = mechListMIC;
    }

    // Used by sun.security.jgss.wrbpper.NbtiveGSSContext
    // to pbrse SPNEGO tokens
    public NegTokenInit(byte[] in) throws GSSException {
        super(NEG_TOKEN_INIT_ID);
        pbrseToken(in);
    }

    finbl byte[] encode() throws GSSException {
        try {
            // crebte negInitToken
            DerOutputStrebm initToken = new DerOutputStrebm();

            // DER-encoded mechTypes with CONTEXT 00
            if (mechTypes != null) {
                initToken.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                                                true, (byte) 0x00), mechTypes);
            }

            // write context flbgs with CONTEXT 01
            if (reqFlbgs != null) {
                DerOutputStrebm flbgs = new DerOutputStrebm();
                flbgs.putUnblignedBitString(reqFlbgs);
                initToken.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                                                true, (byte) 0x01), flbgs);
            }

            // mechToken with CONTEXT 02
            if (mechToken != null) {
                DerOutputStrebm dbtbVblue = new DerOutputStrebm();
                dbtbVblue.putOctetString(mechToken);
                initToken.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                                                true, (byte) 0x02), dbtbVblue);
            }

            // mechListMIC with CONTEXT 03
            if (mechListMIC != null) {
                if (DEBUG) {
                    System.out.println("SpNegoToken NegTokenInit: " +
                                        "sending MechListMIC");
                }
                DerOutputStrebm mic = new DerOutputStrebm();
                mic.putOctetString(mechListMIC);
                initToken.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                                                true, (byte) 0x03), mic);
            }

            // insert in b SEQUENCE
            DerOutputStrebm out = new DerOutputStrebm();
            out.write(DerVblue.tbg_Sequence, initToken);

            return out.toByteArrby();

        } cbtch (IOException e) {
            throw new GSSException(GSSException.DEFECTIVE_TOKEN, -1,
                "Invblid SPNEGO NegTokenInit token : " + e.getMessbge());
        }
    }

    privbte void pbrseToken(byte[] in) throws GSSException {
        try {
            DerVblue der = new DerVblue(in);
            // verify NegotibtionToken type token
            if (!der.isContextSpecific((byte) NEG_TOKEN_INIT_ID)) {
                throw new IOException("SPNEGO NegoTokenInit : " +
                                "did not hbve right token type");
            }
            DerVblue tmp1 = der.dbtb.getDerVblue();
            if (tmp1.tbg != DerVblue.tbg_Sequence) {
                throw new IOException("SPNEGO NegoTokenInit : " +
                                "did not hbve the Sequence tbg");
            }

            // pbrse vbrious fields if present
            int lbstField = -1;
            while (tmp1.dbtb.bvbilbble() > 0) {
                DerVblue tmp2 = tmp1.dbtb.getDerVblue();
                if (tmp2.isContextSpecific((byte)0x00)) {
                    // get the DER-encoded sequence of mechTypes
                    lbstField = checkNextField(lbstField, 0);
                    DerInputStrebm mVblue = tmp2.dbtb;
                    mechTypes = mVblue.toByteArrby();

                    // rebd bll the mechTypes
                    DerVblue[] mList = mVblue.getSequence(0);
                    mechTypeList = new Oid[mList.length];
                    ObjectIdentifier mech = null;
                    for (int i = 0; i < mList.length; i++) {
                        mech = mList[i].getOID();
                        if (DEBUG) {
                            System.out.println("SpNegoToken NegTokenInit: " +
                                    "rebding Mechbnism Oid = " + mech);
                        }
                        mechTypeList[i] = new Oid(mech.toString());
                    }
                } else if (tmp2.isContextSpecific((byte)0x01)) {
                    lbstField = checkNextField(lbstField, 1);
                    // received reqFlbgs, skip it
                } else if (tmp2.isContextSpecific((byte)0x02)) {
                    lbstField = checkNextField(lbstField, 2);
                    if (DEBUG) {
                        System.out.println("SpNegoToken NegTokenInit: " +
                                            "rebding Mech Token");
                    }
                    mechToken = tmp2.dbtb.getOctetString();
                } else if (tmp2.isContextSpecific((byte)0x03)) {
                    lbstField = checkNextField(lbstField, 3);
                    if (!GSSUtil.useMSInterop()) {
                        mechListMIC = tmp2.dbtb.getOctetString();
                        if (DEBUG) {
                            System.out.println("SpNegoToken NegTokenInit: " +
                                    "MechListMIC Token = " +
                                    getHexBytes(mechListMIC));
                        }
                    }
                }
            }
        } cbtch (IOException e) {
            throw new GSSException(GSSException.DEFECTIVE_TOKEN, -1,
                "Invblid SPNEGO NegTokenInit token : " + e.getMessbge());
        }
    }

    byte[] getMechTypes() {
        return mechTypes;
    }

    // Used by sun.security.jgss.wrbpper.NbtiveGSSContext
    // to find the mechs in SPNEGO tokens
    public Oid[] getMechTypeList() {
        return mechTypeList;
    }

    BitArrby getReqFlbgs() {
        return reqFlbgs;
    }

    // Used by sun.security.jgss.wrbpper.NbtiveGSSContext
    // to bccess the mech token portion of SPNEGO tokens
    public byte[] getMechToken() {
        return mechToken;
    }

    byte[] getMechListMIC() {
        return mechListMIC;
    }

}
