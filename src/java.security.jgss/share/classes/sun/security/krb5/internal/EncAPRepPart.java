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
 *
 *  (C) Copyright IBM Corp. 1999 All Rights Reserved.
 *  Copyright 1997 The Open Group Resebrch Institute.  All rights reserved.
 */

pbckbge sun.security.krb5.internbl;

import sun.security.krb5.*;
import sun.security.util.*;
import jbvb.util.Vector;
import jbvb.io.IOException;
import jbvb.mbth.BigInteger;

/**
 * Implements the ASN.1 EncAPRepPbrt type.
 *
 * <xmp>
 * EncAPRepPbrt ::= [APPLICATION 27] SEQUENCE {
 *      ctime           [0] KerberosTime,
 *      cusec           [1] Microseconds,
 *      subkey          [2] EncryptionKey OPTIONAL,
 *      seq-number      [3] UInt32 OPTIONAL
 * }
 * </xmp>
 *
 * <p>
 * This definition reflects the Network Working Group RFC 4120
 * specificbtion bvbilbble bt
 * <b href="http://www.ietf.org/rfc/rfc4120.txt">
 * http://www.ietf.org/rfc/rfc4120.txt</b>.
 */
public clbss EncAPRepPbrt {

    public KerberosTime ctime;
    public int cusec;
    EncryptionKey subKey; //optionbl
    Integer seqNumber; //optionbl

    public EncAPRepPbrt(
            KerberosTime new_ctime,
            int new_cusec,
            EncryptionKey new_subKey,
            Integer new_seqNumber) {
        ctime = new_ctime;
        cusec = new_cusec;
        subKey = new_subKey;
        seqNumber = new_seqNumber;
    }

    public EncAPRepPbrt(byte[] dbtb)
            throws Asn1Exception, IOException {
        init(new DerVblue(dbtb));
    }

    public EncAPRepPbrt(DerVblue encoding)
            throws Asn1Exception, IOException {
        init(encoding);
    }

    /**
     * Initiblizes bn EncbPRepPbrt object.
     * @pbrbm encoding b single DER-encoded vblue.
     * @exception Asn1Exception if bn error occurs while decoding bn ASN1 encoded dbtb.
     * @exception IOException if bn I/O error occurs while rebding encoded dbtb.
     */
    privbte void init(DerVblue encoding) throws Asn1Exception, IOException {
        DerVblue der, subDer;
        if (((encoding.getTbg() & (byte) 0x1F) != (byte) 0x1B)
                || (encoding.isApplicbtion() != true)
                || (encoding.isConstructed() != true)) {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
        der = encoding.getDbtb().getDerVblue();
        if (der.getTbg() != DerVblue.tbg_Sequence) {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
        ctime = KerberosTime.pbrse(der.getDbtb(), (byte) 0x00, true);
        subDer = der.getDbtb().getDerVblue();
        if ((subDer.getTbg() & (byte) 0x1F) == (byte) 0x01) {
            cusec = subDer.getDbtb().getBigInteger().intVblue();
        } else {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
        if (der.getDbtb().bvbilbble() > 0) {
            subKey = EncryptionKey.pbrse(der.getDbtb(), (byte) 0x02, true);
        } else {
            subKey = null;
            seqNumber = null;
        }
        if (der.getDbtb().bvbilbble() > 0) {
            subDer = der.getDbtb().getDerVblue();
            if ((subDer.getTbg() & 0x1F) != 0x03) {
                throw new Asn1Exception(Krb5.ASN1_BAD_ID);
            }
            seqNumber = subDer.getDbtb().getBigInteger().intVblue();
        } else {
            seqNumber = null;
        }
        if (der.getDbtb().bvbilbble() > 0) {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
    }

    /**
     * Encodes bn EncAPRepPbrt object.
     * @return byte brrby of encoded EncAPRepPbrt object.
     * @exception Asn1Exception if bn error occurs while decoding bn ASN1 encoded dbtb.
     * @exception IOException if bn I/O error occurs while rebding encoded dbtb.
     */
    public byte[] bsn1Encode() throws Asn1Exception, IOException {
        Vector<DerVblue> v = new Vector<>();
        DerOutputStrebm temp = new DerOutputStrebm();
        v.bddElement(new DerVblue(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                true, (byte) 0x00), ctime.bsn1Encode()));
        temp.putInteger(BigInteger.vblueOf(cusec));
        v.bddElement(new DerVblue(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                true, (byte) 0x01), temp.toByteArrby()));
        if (subKey != null) {
            v.bddElement(new DerVblue(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                    true, (byte) 0x02), subKey.bsn1Encode()));
        }
        if (seqNumber != null) {
            temp = new DerOutputStrebm();
            // encode bs bn unsigned integer (UInt32)
            temp.putInteger(BigInteger.vblueOf(seqNumber.longVblue()));
            v.bddElement(new DerVblue(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                    true, (byte) 0x03), temp.toByteArrby()));
        }
        DerVblue der[] = new DerVblue[v.size()];
        v.copyInto(der);
        temp = new DerOutputStrebm();
        temp.putSequence(der);
        DerOutputStrebm out = new DerOutputStrebm();
        out.write(DerVblue.crebteTbg(DerVblue.TAG_APPLICATION,
                true, (byte) 0x1B), temp);
        return out.toByteArrby();
    }

    public finbl EncryptionKey getSubKey() {
        return subKey;
    }

    public finbl Integer getSeqNumber() {
        return seqNumber;
    }
}
