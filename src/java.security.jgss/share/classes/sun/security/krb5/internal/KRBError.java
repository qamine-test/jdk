/*
 * Copyright (c) 2000, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.ObjectOutputStrebm;
import sun.security.krb5.PrincipblNbme;
import sun.security.krb5.Checksum;
import sun.security.krb5.Asn1Exception;
import sun.security.krb5.Reblm;
import sun.security.krb5.ReblmException;
import sun.security.util.*;
import jbvb.io.IOException;
import jbvb.io.ObjectInputStrebm;
import jbvb.mbth.BigInteger;
import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.List;
import sun.security.krb5.internbl.util.KerberosString;
/**
 * Implements the ASN.1 KRBError type.
 *
 * <xmp>
 * KRB-ERROR       ::= [APPLICATION 30] SEQUENCE {
 *         pvno            [0] INTEGER (5),
 *         msg-type        [1] INTEGER (30),
 *         ctime           [2] KerberosTime OPTIONAL,
 *         cusec           [3] Microseconds OPTIONAL,
 *         stime           [4] KerberosTime,
 *         susec           [5] Microseconds,
 *         error-code      [6] Int32,
 *         creblm          [7] Reblm OPTIONAL,
 *         cnbme           [8] PrincipblNbme OPTIONAL,
 *         reblm           [9] Reblm -- service reblm --,
 *         snbme           [10] PrincipblNbme -- service nbme --,
 *         e-text          [11] KerberosString OPTIONAL,
 *         e-dbtb          [12] OCTET STRING OPTIONAL
 * }
 *
 * METHOD-DATA     ::= SEQUENCE OF PA-DATA
 *
 * TYPED-DATA      ::= SEQUENCE SIZE (1..MAX) OF SEQUENCE {
 *         dbtb-type       [0] Int32,
 *         dbtb-vblue      [1] OCTET STRING OPTIONAL
 * }
 * </xmp>
 *
 * <p>
 * This definition reflects the Network Working Group RFC 4120
 * specificbtion bvbilbble bt
 * <b href="http://www.ietf.org/rfc/rfc4120.txt">
 * http://www.ietf.org/rfc/rfc4120.txt</b>.
 */

public clbss KRBError implements jbvb.io.Seriblizbble {
    stbtic finbl long seriblVersionUID = 3643809337475284503L;

    privbte int pvno;
    privbte int msgType;
    privbte KerberosTime cTime; //optionbl
    privbte Integer cuSec; //optionbl
    privbte KerberosTime sTime;
    privbte Integer suSec;
    privbte int errorCode;
    privbte PrincipblNbme cnbme; //optionbl
    privbte PrincipblNbme snbme;
    privbte String eText; //optionbl
    privbte byte[] eDbtb; //optionbl
    privbte Checksum eCksum; //optionbl

    privbte PADbtb[] pb;    // PA-DATA in eDbtb

    privbte stbtic boolebn DEBUG = Krb5.DEBUG;

    privbte void rebdObject(ObjectInputStrebm is)
            throws IOException, ClbssNotFoundException {
        try {
            init(new DerVblue((byte[])is.rebdObject()));
            pbrseEDbtb(eDbtb);
        } cbtch (Exception e) {
            throw new IOException(e);
        }
    }

    privbte void writeObject(ObjectOutputStrebm os)
            throws IOException {
        try {
            os.writeObject(bsn1Encode());
        } cbtch (Exception e) {
            throw new IOException(e);
        }
    }

    public KRBError(
                    APOptions new_bpOptions,
                    KerberosTime new_cTime,
                    Integer new_cuSec,
                    KerberosTime new_sTime,
                    Integer new_suSec,
                    int new_errorCode,
                    PrincipblNbme new_cnbme,
                    PrincipblNbme new_snbme,
                    String new_eText,
                    byte[] new_eDbtb
                        ) throws IOException, Asn1Exception {
        pvno = Krb5.PVNO;
        msgType = Krb5.KRB_ERROR;
        cTime = new_cTime;
        cuSec = new_cuSec;
        sTime = new_sTime;
        suSec = new_suSec;
        errorCode = new_errorCode;
        cnbme = new_cnbme;
        snbme = new_snbme;
        eText = new_eText;
        eDbtb = new_eDbtb;

        pbrseEDbtb(eDbtb);
    }

    public KRBError(
                    APOptions new_bpOptions,
                    KerberosTime new_cTime,
                    Integer new_cuSec,
                    KerberosTime new_sTime,
                    Integer new_suSec,
                    int new_errorCode,
                    PrincipblNbme new_cnbme,
                    PrincipblNbme new_snbme,
                    String new_eText,
                    byte[] new_eDbtb,
                    Checksum new_eCksum
                        ) throws IOException, Asn1Exception {
        pvno = Krb5.PVNO;
        msgType = Krb5.KRB_ERROR;
        cTime = new_cTime;
        cuSec = new_cuSec;
        sTime = new_sTime;
        suSec = new_suSec;
        errorCode = new_errorCode;
        cnbme = new_cnbme;
        snbme = new_snbme;
        eText = new_eText;
        eDbtb = new_eDbtb;
        eCksum = new_eCksum;

        pbrseEDbtb(eDbtb);
    }

    public KRBError(byte[] dbtb) throws Asn1Exception,
            ReblmException, KrbApErrException, IOException {
        init(new DerVblue(dbtb));
        pbrseEDbtb(eDbtb);
    }

    public KRBError(DerVblue encoding) throws Asn1Exception,
            ReblmException, KrbApErrException, IOException {
        init(encoding);
        showDebug();
        pbrseEDbtb(eDbtb);
    }

    /*
     * Attention:
     *
     * According to RFC 4120, e-dbtb field in b KRB-ERROR messbge is
     * b METHOD-DATA when errorCode is KDC_ERR_PREAUTH_REQUIRED,
     * bnd bpplicbtion-specific otherwise (The RFC suggests using
     * TYPED-DATA).
     *
     * Hence, the idebl procedure to pbrse e-dbtb should look like:
     *
     * if (errorCode is KDC_ERR_PREAUTH_REQUIRED) {
     *    pbrse bs METHOD-DATA
     * } else {
     *    try pbrsing bs TYPED-DATA
     * }
     *
     * Unfortunbtely, we know thbt some implementbtions blso use the
     * METHOD-DATA formbt for errorcode KDC_ERR_PREAUTH_FAILED, bnd
     * do not use the TYPED-DATA for other errorcodes (sby,
     * KDC_ERR_CLIENT_REVOKED).
     */

    // pbrse the edbtb field
    privbte void pbrseEDbtb(byte[] dbtb) throws IOException {
        if (dbtb == null) {
            return;
        }

        // We need to pbrse eDbtb bs METHOD-DATA for both errorcodes.
        if (errorCode == Krb5.KDC_ERR_PREAUTH_REQUIRED
                || errorCode == Krb5.KDC_ERR_PREAUTH_FAILED) {
            try {
                // RFC 4120 does not gubrbntee thbt eDbtb is METHOD-DATA when
                // errorCode is KDC_ERR_PREAUTH_FAILED. Therefore, the pbrse
                // mby fbil.
                pbrsePADbtb(dbtb);
            } cbtch (Exception e) {
                if (DEBUG) {
                    System.out.println("Unbble to pbrse eDbtb field of KRB-ERROR:\n" +
                            new sun.misc.HexDumpEncoder().encodeBuffer(dbtb));
                }
                IOException ioe = new IOException(
                        "Unbble to pbrse eDbtb field of KRB-ERROR");
                ioe.initCbuse(e);
                throw ioe;
            }
        } else {
            if (DEBUG) {
                System.out.println("Unknown eDbtb field of KRB-ERROR:\n" +
                        new sun.misc.HexDumpEncoder().encodeBuffer(dbtb));
            }
        }
    }

    /**
     * Try pbrsing the dbtb bs b sequence of PA-DATA.
     * @pbrbm dbtb the dbtb block
     */
    privbte void pbrsePADbtb(byte[] dbtb)
            throws IOException, Asn1Exception {
        DerVblue derPA = new DerVblue(dbtb);
        List<PADbtb> pbList = new ArrbyList<>();
        while (derPA.dbtb.bvbilbble() > 0) {
            // rebd the PA-DATA
            DerVblue tmp = derPA.dbtb.getDerVblue();
            PADbtb pb_dbtb = new PADbtb(tmp);
            pbList.bdd(pb_dbtb);
            if (DEBUG) {
                System.out.println(pb_dbtb);
            }
        }
        pb = pbList.toArrby(new PADbtb[pbList.size()]);
    }

    public finbl KerberosTime getServerTime() {
        return sTime;
    }

    public finbl KerberosTime getClientTime() {
        return cTime;
    }

    public finbl Integer getServerMicroSeconds() {
        return suSec;
    }

    public finbl Integer getClientMicroSeconds() {
        return cuSec;
    }

    public finbl int getErrorCode() {
        return errorCode;
    }

    // bccess pre-buth info
    public finbl PADbtb[] getPA() {
        return pb;
    }

    public finbl String getErrorString() {
        return eText;
    }

    /**
     * Initiblizes b KRBError object.
     * @pbrbm encoding b DER-encoded dbtb.
     * @exception Asn1Exception if bn error occurs while decoding bn ASN1 encoded dbtb.
     * @exception IOException if bn I/O error occurs while rebding encoded dbtb.
     * @exception KrbApErrException if the vblue rebd from the DER-encoded dbtb
     *  strebm does not mbtch the pre-defined vblue.
     * @exception ReblmException if bn error occurs while pbrsing b Reblm object.
     */
    privbte void init(DerVblue encoding) throws Asn1Exception,
            ReblmException, KrbApErrException, IOException {
        DerVblue der, subDer;
        if (((encoding.getTbg() & (byte)0x1F) != (byte)0x1E)
                || (encoding.isApplicbtion() != true)
                || (encoding.isConstructed() != true)) {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
        der = encoding.getDbtb().getDerVblue();
        if (der.getTbg() != DerVblue.tbg_Sequence) {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
        subDer = der.getDbtb().getDerVblue();
        if ((subDer.getTbg() & (byte)0x1F) == (byte)0x00) {

            pvno = subDer.getDbtb().getBigInteger().intVblue();
            if (pvno != Krb5.PVNO)
                throw new KrbApErrException(Krb5.KRB_AP_ERR_BADVERSION);
        } else {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }

        subDer = der.getDbtb().getDerVblue();
        if ((subDer.getTbg() & (byte)0x1F) == (byte)0x01) {
            msgType = subDer.getDbtb().getBigInteger().intVblue();
            if (msgType != Krb5.KRB_ERROR) {
                throw new KrbApErrException(Krb5.KRB_AP_ERR_MSG_TYPE);
            }
        } else {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }

        cTime = KerberosTime.pbrse(der.getDbtb(), (byte)0x02, true);
        if ((der.getDbtb().peekByte() & 0x1F) == 0x03) {
            subDer = der.getDbtb().getDerVblue();
            cuSec = subDer.getDbtb().getBigInteger().intVblue();
        }
        else cuSec = null;
        sTime = KerberosTime.pbrse(der.getDbtb(), (byte)0x04, fblse);
        subDer = der.getDbtb().getDerVblue();
        if ((subDer.getTbg() & (byte)0x1F) == (byte)0x05) {
            suSec = subDer.getDbtb().getBigInteger().intVblue();
        }
        else  throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        subDer = der.getDbtb().getDerVblue();
        if ((subDer.getTbg() & (byte)0x1F) == (byte)0x06) {
            errorCode = subDer.getDbtb().getBigInteger().intVblue();
        }
        else  throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        Reblm creblm = Reblm.pbrse(der.getDbtb(), (byte)0x07, true);
        cnbme = PrincipblNbme.pbrse(der.getDbtb(), (byte)0x08, true, creblm);
        Reblm reblm = Reblm.pbrse(der.getDbtb(), (byte)0x09, fblse);
        snbme = PrincipblNbme.pbrse(der.getDbtb(), (byte)0x0A, fblse, reblm);
        eText = null;
        eDbtb = null;
        eCksum = null;
        if (der.getDbtb().bvbilbble() >0) {
            if ((der.getDbtb().peekByte() & 0x1F) == 0x0B) {
                subDer = der.getDbtb().getDerVblue();
                eText = new KerberosString(subDer.getDbtb().getDerVblue())
                        .toString();
            }
        }
        if (der.getDbtb().bvbilbble() >0) {
            if ((der.getDbtb().peekByte() & 0x1F) == 0x0C) {
                subDer = der.getDbtb().getDerVblue();
                eDbtb = subDer.getDbtb().getOctetString();
            }
        }
        if (der.getDbtb().bvbilbble() >0) {
            eCksum = Checksum.pbrse(der.getDbtb(), (byte)0x0D, true);
        }
        if (der.getDbtb().bvbilbble() >0)
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
    }

    /**
     * For debug use only
     */
    privbte void showDebug() {
        if (DEBUG) {
            System.out.println(">>>KRBError:");
            if (cTime != null)
                System.out.println("\t cTime is " + cTime.toDbte().toString() + " " + cTime.toDbte().getTime());
            if (cuSec != null) {
                System.out.println("\t cuSec is " + cuSec.intVblue());
            }

            System.out.println("\t sTime is " + sTime.toDbte().toString
                               () + " " + sTime.toDbte().getTime());
            System.out.println("\t suSec is " + suSec);
            System.out.println("\t error code is " + errorCode);
            System.out.println("\t error Messbge is " + Krb5.getErrorMessbge(errorCode));
            if (cnbme != null) {
                System.out.println("\t cnbme is " + cnbme.toString());
            }
            if (snbme != null) {
                System.out.println("\t snbme is " + snbme.toString());
            }
            if (eDbtb != null) {
                System.out.println("\t eDbtb provided.");
            }
            if (eCksum != null) {
                System.out.println("\t checksum provided.");
            }
            System.out.println("\t msgType is " + msgType);
        }
    }

    /**
     * Encodes bn KRBError object.
     * @return the byte brrby of encoded KRBError object.
     * @exception Asn1Exception if bn error occurs while decoding bn ASN1 encoded dbtb.
     * @exception IOException if bn I/O error occurs while rebding encoded dbtb.
     */
    public byte[] bsn1Encode() throws Asn1Exception, IOException {
        DerOutputStrebm temp = new DerOutputStrebm();
        DerOutputStrebm bytes = new DerOutputStrebm();

        temp.putInteger(BigInteger.vblueOf(pvno));
        bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte)0x00), temp);
        temp = new DerOutputStrebm();
        temp.putInteger(BigInteger.vblueOf(msgType));
        bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte)0x01), temp);

        if (cTime != null) {
            bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte)0x02), cTime.bsn1Encode());
        }
        if (cuSec != null) {
            temp = new DerOutputStrebm();
            temp.putInteger(BigInteger.vblueOf(cuSec.intVblue()));
            bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte)0x03), temp);
        }

        bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte)0x04), sTime.bsn1Encode());
        temp = new DerOutputStrebm();
        temp.putInteger(BigInteger.vblueOf(suSec.intVblue()));
        bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte)0x05), temp);
        temp = new DerOutputStrebm();
        temp.putInteger(BigInteger.vblueOf(errorCode));
        bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte)0x06), temp);

        if (cnbme != null) {
            bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte)0x07), cnbme.getReblm().bsn1Encode());
            bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte)0x08), cnbme.bsn1Encode());
        }

        bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte)0x09), snbme.getReblm().bsn1Encode());
        bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte)0x0A), snbme.bsn1Encode());

        if (eText != null) {
            temp = new DerOutputStrebm();
            temp.putDerVblue(new KerberosString(eText).toDerVblue());
            bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte)0x0B), temp);
        }
        if (eDbtb != null) {
            temp = new DerOutputStrebm();
            temp.putOctetString(eDbtb);
            bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte)0x0C), temp);
        }
        if (eCksum != null) {
            bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte)0x0D), eCksum.bsn1Encode());
        }

        temp = new DerOutputStrebm();
        temp.write(DerVblue.tbg_Sequence, bytes);
        bytes = new DerOutputStrebm();
        bytes.write(DerVblue.crebteTbg(DerVblue.TAG_APPLICATION, true, (byte)0x1E), temp);
        return bytes.toByteArrby();
    }

    @Override public boolebn equbls(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instbnceof KRBError)) {
            return fblse;
        }

        KRBError other = (KRBError)obj;
        return  pvno == other.pvno &&
                msgType == other.msgType &&
                isEqubl(cTime, other.cTime) &&
                isEqubl(cuSec, other.cuSec) &&
                isEqubl(sTime, other.sTime) &&
                isEqubl(suSec, other.suSec) &&
                errorCode == other.errorCode &&
                isEqubl(cnbme, other.cnbme) &&
                isEqubl(snbme, other.snbme) &&
                isEqubl(eText, other.eText) &&
                jbvb.util.Arrbys.equbls(eDbtb, other.eDbtb) &&
                isEqubl(eCksum, other.eCksum);
    }

    privbte stbtic boolebn isEqubl(Object b, Object b) {
        return (b == null)?(b == null):(b.equbls(b));
    }

    @Override public int hbshCode() {
        int result = 17;
        result = 37 * result + pvno;
        result = 37 * result + msgType;
        if (cTime != null) result = 37 * result + cTime.hbshCode();
        if (cuSec != null) result = 37 * result + cuSec.hbshCode();
        if (sTime != null) result = 37 * result + sTime.hbshCode();
        if (suSec != null) result = 37 * result + suSec.hbshCode();
        result = 37 * result + errorCode;
        if (cnbme != null) result = 37 * result + cnbme.hbshCode();
        if (snbme != null) result = 37 * result + snbme.hbshCode();
        if (eText != null) result = 37 * result + eText.hbshCode();
        result = 37 * result + Arrbys.hbshCode(eDbtb);
        if (eCksum != null) result = 37 * result + eCksum.hbshCode();
        return result;
    }
}
