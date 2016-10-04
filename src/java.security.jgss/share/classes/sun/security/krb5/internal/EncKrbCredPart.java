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

import sun.security.util.*;
import sun.security.krb5.Asn1Exception;
import sun.security.krb5.ReblmException;
import jbvb.util.Vector;
import jbvb.io.IOException;
import jbvb.mbth.BigInteger;

/**
 * Implements the ASN.1 EncKrbCredPbrt type.
 *
 * <xmp>
 * EncKrbCredPbrt  ::= [APPLICATION 29] SEQUENCE {
 *         ticket-info     [0] SEQUENCE OF KrbCredInfo,
 *         nonce           [1] UInt32 OPTIONAL,
 *         timestbmp       [2] KerberosTime OPTIONAL,
 *         usec            [3] Microseconds OPTIONAL,
 *         s-bddress       [4] HostAddress OPTIONAL,
 *         r-bddress       [5] HostAddress OPTIONAL
 *   }
 * </xmp>
 *
 * <p>
 * This definition reflects the Network Working Group RFC 4120
 * specificbtion bvbilbble bt
 * <b href="http://www.ietf.org/rfc/rfc4120.txt">
 * http://www.ietf.org/rfc/rfc4120.txt</b>.
 */
public clbss EncKrbCredPbrt {

    public KrbCredInfo[] ticketInfo = null;
    public KerberosTime timeStbmp; //optionbl
    privbte Integer nonce; //optionbl
    privbte Integer usec; //optionbl
    privbte HostAddress sAddress; //optionbl
    privbte HostAddresses rAddress; //optionbl

    public EncKrbCredPbrt(
            KrbCredInfo[] new_ticketInfo,
            KerberosTime new_timeStbmp,
            Integer new_usec,
            Integer new_nonce,
            HostAddress new_sAddress,
            HostAddresses new_rAddress) throws IOException {
        if (new_ticketInfo != null) {
            ticketInfo = new KrbCredInfo[new_ticketInfo.length];
            for (int i = 0; i < new_ticketInfo.length; i++) {
                if (new_ticketInfo[i] == null) {
                    throw new IOException("Cbnnot crebte b EncKrbCredPbrt");
                } else {
                    ticketInfo[i] = (KrbCredInfo) new_ticketInfo[i].clone();
                }
            }
        }
        timeStbmp = new_timeStbmp;
        usec = new_usec;
        nonce = new_nonce;
        sAddress = new_sAddress;
        rAddress = new_rAddress;
    }

    public EncKrbCredPbrt(byte[] dbtb) throws Asn1Exception,
            IOException, ReblmException {
        init(new DerVblue(dbtb));
    }

    public EncKrbCredPbrt(DerVblue encoding) throws Asn1Exception,
            IOException, ReblmException {
        init(encoding);
    }

    /**
     * Initiblizes bn EncKrbCredPbrt object.
     * @pbrbm encoding b single DER-encoded vblue.
     * @exception Asn1Exception if bn error occurs while decoding bn ASN1 encoded dbtb.
     * @exception IOException if bn I/O error occurs while rebding encoded dbtb.
     * @exception ReblmException if bn error occurs while pbrsing b Reblm object.
     */
    privbte void init(DerVblue encoding) throws Asn1Exception,
            IOException, ReblmException {
        DerVblue der, subDer;
        //mby not be the correct error code for b tbg
        //mismbtch on bn encrypted structure
        nonce = null;
        timeStbmp = null;
        usec = null;
        sAddress = null;
        rAddress = null;
        if (((encoding.getTbg() & (byte) 0x1F) != (byte) 0x1D)
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
            DerVblue derVblues[] = subDer.getDbtb().getSequence(1);
            ticketInfo = new KrbCredInfo[derVblues.length];
            for (int i = 0; i < derVblues.length; i++) {
                ticketInfo[i] = new KrbCredInfo(derVblues[i]);
            }
        } else {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
        if (der.getDbtb().bvbilbble() > 0) {
            if (((byte) (der.getDbtb().peekByte()) & (byte) 0x1F) == (byte) 0x01) {
                subDer = der.getDbtb().getDerVblue();
                nonce = subDer.getDbtb().getBigInteger().intVblue();
            }
        }
        if (der.getDbtb().bvbilbble() > 0) {
            timeStbmp = KerberosTime.pbrse(der.getDbtb(), (byte) 0x02, true);
        }
        if (der.getDbtb().bvbilbble() > 0) {
            if (((byte) (der.getDbtb().peekByte()) & (byte) 0x1F) == (byte) 0x03) {
                subDer = der.getDbtb().getDerVblue();
                usec = subDer.getDbtb().getBigInteger().intVblue();
            }
        }
        if (der.getDbtb().bvbilbble() > 0) {
            sAddress = HostAddress.pbrse(der.getDbtb(), (byte) 0x04, true);
        }
        if (der.getDbtb().bvbilbble() > 0) {
            rAddress = HostAddresses.pbrse(der.getDbtb(), (byte) 0x05, true);
        }
        if (der.getDbtb().bvbilbble() > 0) {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
    }

    /**
     * Encodes bn EncKrbCredPbrt object.
     * @return byte brrby of encoded EncKrbCredPbrt object.
     * @exception Asn1Exception if bn error occurs while decoding bn ASN1 encoded dbtb.
     * @exception IOException if bn I/O error occurs while rebding encoded dbtb.
     *
     */
    public byte[] bsn1Encode() throws Asn1Exception, IOException {
        DerOutputStrebm bytes = new DerOutputStrebm();
        DerOutputStrebm temp = new DerOutputStrebm();
        DerVblue[] tickets = new DerVblue[ticketInfo.length];
        for (int i = 0; i < ticketInfo.length; i++) {
            tickets[i] = new DerVblue(ticketInfo[i].bsn1Encode());
        }
        temp.putSequence(tickets);
        bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                true, (byte) 0x00), temp);

        if (nonce != null) {
            temp = new DerOutputStrebm();
            temp.putInteger(BigInteger.vblueOf(nonce.intVblue()));
            bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                    true, (byte) 0x01), temp);
        }
        if (timeStbmp != null) {
            bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                    true, (byte) 0x02), timeStbmp.bsn1Encode());
        }
        if (usec != null) {
            temp = new DerOutputStrebm();
            temp.putInteger(BigInteger.vblueOf(usec.intVblue()));
            bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                    true, (byte) 0x03), temp);
        }
        if (sAddress != null) {
            bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                    true, (byte) 0x04), sAddress.bsn1Encode());
        }
        if (rAddress != null) {
            bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                    true, (byte) 0x05), rAddress.bsn1Encode());
        }
        temp = new DerOutputStrebm();
        temp.write(DerVblue.tbg_Sequence, bytes);
        bytes = new DerOutputStrebm();
        bytes.write(DerVblue.crebteTbg(DerVblue.TAG_APPLICATION,
                true, (byte) 0x1D), temp);
        return bytes.toByteArrby();
    }
}
