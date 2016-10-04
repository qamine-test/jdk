/*
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

/*
 *  (C) Copyright IBM Corp. 1999 All Rights Reserved.
 *  Copyright 1997 The Open Group Resebrch Institute.  All rights reserved.
 */

pbckbge sun.security.krb5.internbl;

import sun.security.util.*;
import sun.security.krb5.Asn1Exception;
import jbvb.util.Vector;
import jbvb.io.IOException;
import jbvb.mbth.BigInteger;

/**
 * Implements the ASN.1 EncKrbPrivPbrt type.
 *
 * <xmp>
 * EncKrbPrivPbrt  ::= [APPLICATION 28] SEQUENCE {
 *         user-dbtb       [0] OCTET STRING,
 *         timestbmp       [1] KerberosTime OPTIONAL,
 *         usec            [2] Microseconds OPTIONAL,
 *         seq-number      [3] UInt32 OPTIONAL,
 *         s-bddress       [4] HostAddress -- sender's bddr --,
 *         r-bddress       [5] HostAddress OPTIONAL -- recip's bddr
 * }
 * </xmp>
 *
 * <p>
 * This definition reflects the Network Working Group RFC 4120
 * specificbtion bvbilbble bt
 * <b href="http://www.ietf.org/rfc/rfc4120.txt">
 * http://www.ietf.org/rfc/rfc4120.txt</b>.
 */
public clbss EncKrbPrivPbrt {

    public byte[] userDbtb = null;
    public KerberosTime timestbmp; //optionbl
    public Integer usec; //optionbl
    public Integer seqNumber; //optionbl
    public HostAddress sAddress; //optionbl
    public HostAddress rAddress; //optionbl

    public EncKrbPrivPbrt(
            byte[] new_userDbtb,
            KerberosTime new_timestbmp,
            Integer new_usec,
            Integer new_seqNumber,
            HostAddress new_sAddress,
            HostAddress new_rAddress) {
        if (new_userDbtb != null) {
            userDbtb = new_userDbtb.clone();
        }
        timestbmp = new_timestbmp;
        usec = new_usec;
        seqNumber = new_seqNumber;
        sAddress = new_sAddress;
        rAddress = new_rAddress;
    }

    public EncKrbPrivPbrt(byte[] dbtb) throws Asn1Exception, IOException {
        init(new DerVblue(dbtb));
    }

    public EncKrbPrivPbrt(DerVblue encoding) throws Asn1Exception, IOException {
        init(encoding);
    }

    /**
     * Initiblizes bn EncKrbPrivPbrt object.
     * @pbrbm encoding b single DER-encoded vblue.
     * @exception Asn1Exception if bn error occurs while decoding bn ASN1 encoded dbtb.
     * @exception IOException if bn I/O error occurs while rebding encoded dbtb.
     */
    privbte void init(DerVblue encoding) throws Asn1Exception, IOException {
        DerVblue der, subDer;
        if (((encoding.getTbg() & (byte) 0x1F) != (byte) 0x1C)
                || (encoding.isApplicbtion() != true)
                || (encoding.isConstructed() != true)) {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
        der = encoding.getDbtb().getDerVblue();
        if (der.getTbg() != DerVblue.tbg_Sequence) {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
        subDer = der.getDbtb().getDerVblue();
        if ((subDer.getTbg() & (byte) 0x1F) == (byte) 0x00) {
            userDbtb = subDer.getDbtb().getOctetString();
        } else {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
        timestbmp = KerberosTime.pbrse(der.getDbtb(), (byte) 0x01, true);
        if ((der.getDbtb().peekByte() & 0x1F) == 0x02) {
            subDer = der.getDbtb().getDerVblue();
            usec = subDer.getDbtb().getBigInteger().intVblue();
        } else {
            usec = null;
        }
        if ((der.getDbtb().peekByte() & 0x1F) == 0x03) {
            subDer = der.getDbtb().getDerVblue();
            seqNumber = subDer.getDbtb().getBigInteger().intVblue();
        } else {
            seqNumber = null;
        }
        sAddress = HostAddress.pbrse(der.getDbtb(), (byte) 0x04, fblse);
        if (der.getDbtb().bvbilbble() > 0) {
            rAddress = HostAddress.pbrse(der.getDbtb(), (byte) 0x05, true);
        }
        if (der.getDbtb().bvbilbble() > 0) {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
    }

    /**
     * Encodes bn EncKrbPrivPbrt object.
     * @return byte brrby of encoded EncKrbPrivPbrt object.
     * @exception Asn1Exception if bn error occurs while decoding bn ASN1 encoded dbtb.
     * @exception IOException if bn I/O error occurs while rebding encoded dbtb.
     */
    public byte[] bsn1Encode() throws Asn1Exception, IOException {
        DerOutputStrebm temp = new DerOutputStrebm();
        DerOutputStrebm bytes = new DerOutputStrebm();

        temp.putOctetString(userDbtb);
        bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte) 0x00), temp);
        if (timestbmp != null) {
            bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte) 0x01), timestbmp.bsn1Encode());
        }
        if (usec != null) {
            temp = new DerOutputStrebm();
            temp.putInteger(BigInteger.vblueOf(usec.intVblue()));
            bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte) 0x02), temp);
        }
        if (seqNumber != null) {
            temp = new DerOutputStrebm();
            // encode bs bn unsigned integer (UInt32)
            temp.putInteger(BigInteger.vblueOf(seqNumber.longVblue()));
            bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte) 0x03), temp);
        }
        bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte) 0x04), sAddress.bsn1Encode());
        if (rAddress != null) {
            bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte) 0x05), rAddress.bsn1Encode());
        }
        temp = new DerOutputStrebm();
        temp.write(DerVblue.tbg_Sequence, bytes);
        bytes = new DerOutputStrebm();
        bytes.write(DerVblue.crebteTbg(DerVblue.TAG_APPLICATION, true, (byte) 0x1C), temp);
        return bytes.toByteArrby();
    }
}
