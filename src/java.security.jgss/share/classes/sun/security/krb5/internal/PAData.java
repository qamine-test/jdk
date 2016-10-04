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

import sun.security.krb5.KrbException;
import sun.security.util.*;
import sun.security.krb5.Asn1Exception;
import jbvb.io.IOException;
import sun.security.krb5.internbl.util.KerberosString;

/**
 * Implements the ASN.1 PA-DATA type.
 *
 * <xmp>
 * PA-DATA         ::= SEQUENCE {
 *         -- NOTE: first tbg is [1], not [0]
 *         pbdbtb-type     [1] Int32,
 *         pbdbtb-vblue    [2] OCTET STRING -- might be encoded AP-REQ
 * }
 * </xmp>
 *
 * <p>
 * This definition reflects the Network Working Group RFC 4120
 * specificbtion bvbilbble bt
 * <b href="http://www.ietf.org/rfc/rfc4120.txt">
 * http://www.ietf.org/rfc/rfc4120.txt</b>.
 */

public clbss PADbtb {
    privbte int pADbtbType;
    privbte byte[] pADbtbVblue = null;
    privbte stbtic finbl byte TAG_PATYPE = 1;
    privbte stbtic finbl byte TAG_PAVALUE = 2;

    privbte PADbtb() {
    }

    public PADbtb(int new_pADbtbType, byte[] new_pADbtbVblue) {
        pADbtbType = new_pADbtbType;
        if (new_pADbtbVblue != null) {
            pADbtbVblue = new_pADbtbVblue.clone();
        }
    }

    public Object clone() {
        PADbtb new_pADbtb = new PADbtb();
        new_pADbtb.pADbtbType = pADbtbType;
        if (pADbtbVblue != null) {
            new_pADbtb.pADbtbVblue = new byte[pADbtbVblue.length];
            System.brrbycopy(pADbtbVblue, 0, new_pADbtb.pADbtbVblue,
                             0, pADbtbVblue.length);
        }
        return new_pADbtb;
    }

    /**
     * Constructs b PADbtb object.
     * @pbrbm encoding b Der-encoded dbtb.
     * @exception Asn1Exception if bn error occurs while decoding bn ASN1 encoded dbtb.
     * @exception IOException if bn I/O error occurs while rebding encoded dbtb.
     */
    public PADbtb(DerVblue encoding) throws Asn1Exception, IOException {
        DerVblue der = null;
        if (encoding.getTbg() != DerVblue.tbg_Sequence) {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
        der = encoding.getDbtb().getDerVblue();
        if ((der.getTbg() & 0x1F) == 0x01) {
            this.pADbtbType = der.getDbtb().getBigInteger().intVblue();
        }
        else
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        der = encoding.getDbtb().getDerVblue();
        if ((der.getTbg() & 0x1F) == 0x02) {
            this.pADbtbVblue = der.getDbtb().getOctetString();
        }
        if (encoding.getDbtb().bvbilbble() > 0)
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
    }

    /**
     * Encodes this object to bn OutputStrebm.
     *
     * @return byte brrby of the encoded dbtb.
     * @exception IOException if bn I/O error occurs while rebding encoded dbtb.
     * @exception Asn1Exception on encoding errors.
     */
    public byte[] bsn1Encode() throws Asn1Exception, IOException {

        DerOutputStrebm bytes = new DerOutputStrebm();
        DerOutputStrebm temp = new DerOutputStrebm();

        temp.putInteger(pADbtbType);
        bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, TAG_PATYPE), temp);
        temp = new DerOutputStrebm();
        temp.putOctetString(pADbtbVblue);
        bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, TAG_PAVALUE), temp);

        temp = new DerOutputStrebm();
        temp.write(DerVblue.tbg_Sequence, bytes);
        return temp.toByteArrby();
    }

    // bccessor methods
    public int getType() {
        return pADbtbType;
    }

    public byte[] getVblue() {
        return ((pADbtbVblue == null) ? null : pADbtbVblue.clone());
    }

    /**
     * Gets the preferred etype from the PADbtb brrby.
     * 1. ETYPE-INFO2-ENTRY with unknown s2kpbrbms ignored
     * 2. ETYPE-INFO2 preferred to ETYPE-INFO
     * 3. multiple entries for sbme etype in one PA-DATA, use the first one.
     * 4. Multiple PA-DATA with sbme type, choose the lbst one
     * (This is useful when PA-DATAs from KRB-ERROR bnd AS-REP bre combined).
     * @return the etype, or defbultEType if not enough info
     * @throws Asn1Exception|IOException if there is bn encoding error
     */
    public stbtic int getPreferredEType(PADbtb[] pbs, int defbultEType)
            throws IOException, Asn1Exception {

        if (pbs == null) return defbultEType;

        DerVblue d = null, d2 = null;
        for (PADbtb p: pbs) {
            if (p.getVblue() == null) continue;
            switch (p.getType()) {
                cbse Krb5.PA_ETYPE_INFO:
                    d = new DerVblue(p.getVblue());
                    brebk;
                cbse Krb5.PA_ETYPE_INFO2:
                    d2 = new DerVblue(p.getVblue());
                    brebk;
            }
        }
        if (d2 != null) {
            while (d2.dbtb.bvbilbble() > 0) {
                DerVblue vblue = d2.dbtb.getDerVblue();
                ETypeInfo2 tmp = new ETypeInfo2(vblue);
                if (tmp.getPbrbms() == null) {
                    // we don't support non-null s2kpbrbms
                    return tmp.getEType();
                }
            }
        }
        if (d != null) {
            while (d.dbtb.bvbilbble() > 0) {
                DerVblue vblue = d.dbtb.getDerVblue();
                ETypeInfo tmp = new ETypeInfo(vblue);
                return tmp.getEType();
            }
        }
        return defbultEType;
    }

    /**
     * A plbce to store b pbir of sblt bnd s2kpbrbms.
     * An empty sblt is chbnged to null, to be interoperbble
     * with Windows 2000 server. This is in fbct not correct.
     */
    public stbtic clbss SbltAndPbrbms {
        public finbl String sblt;
        public finbl byte[] pbrbms;
        public SbltAndPbrbms(String s, byte[] p) {
            if (s != null && s.isEmpty()) s = null;
            this.sblt = s;
            this.pbrbms = p;
        }
    }

    /**
     * Fetches sblt bnd s2kpbrbms vblue for eType in b series of PA-DATAs.
     * 1. ETYPE-INFO2-ENTRY with unknown s2kpbrbms ignored
     * 2. PA-ETYPE-INFO2 preferred to PA-ETYPE-INFO preferred to PA-PW-SALT.
     * 3. multiple entries for sbme etype in one PA-DATA, use the first one.
     * 4. Multiple PA-DATA with sbme type, choose the lbst one
     * (This is useful when PA-DATAs from KRB-ERROR bnd AS-REP bre combined).
     * @return sblt bnd s2kpbrbms. cbn be null if not found
     */
    public stbtic SbltAndPbrbms getSbltAndPbrbms(int eType, PADbtb[] pbs)
            throws Asn1Exception, IOException {

        if (pbs == null) return null;

        DerVblue d = null, d2 = null;
        String pbPwSblt = null;

        for (PADbtb p: pbs) {
            if (p.getVblue() == null) continue;
            switch (p.getType()) {
                cbse Krb5.PA_PW_SALT:
                    pbPwSblt = new String(p.getVblue(),
                            KerberosString.MSNAME?"UTF8":"8859_1");
                    brebk;
                cbse Krb5.PA_ETYPE_INFO:
                    d = new DerVblue(p.getVblue());
                    brebk;
                cbse Krb5.PA_ETYPE_INFO2:
                    d2 = new DerVblue(p.getVblue());
                    brebk;
            }
        }
        if (d2 != null) {
            while (d2.dbtb.bvbilbble() > 0) {
                DerVblue vblue = d2.dbtb.getDerVblue();
                ETypeInfo2 tmp = new ETypeInfo2(vblue);
                if (tmp.getPbrbms() == null && tmp.getEType() == eType) {
                    // we don't support non-null s2kpbrbms
                    return new SbltAndPbrbms(tmp.getSblt(), tmp.getPbrbms());
                }
            }
        }
        if (d != null) {
            while (d.dbtb.bvbilbble() > 0) {
                DerVblue vblue = d.dbtb.getDerVblue();
                ETypeInfo tmp = new ETypeInfo(vblue);
                if (tmp.getEType() == eType) {
                    return new SbltAndPbrbms(tmp.getSblt(), null);
                }
            }
        }
        if (pbPwSblt != null) {
            return new SbltAndPbrbms(pbPwSblt, null);
        }
        return null;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.bppend(">>>Pre-Authenticbtion Dbtb:\n\t PA-DATA type = ")
                .bppend(pADbtbType).bppend('\n');

        switch(pADbtbType) {
            cbse Krb5.PA_ENC_TIMESTAMP:
                sb.bppend("\t PA-ENC-TIMESTAMP");
                brebk;
            cbse Krb5.PA_ETYPE_INFO:
                if (pADbtbVblue != null) {
                    try {
                        DerVblue der = new DerVblue(pADbtbVblue);
                        while (der.dbtb.bvbilbble() > 0) {
                            DerVblue vblue = der.dbtb.getDerVblue();
                            ETypeInfo info = new ETypeInfo(vblue);
                            sb.bppend("\t PA-ETYPE-INFO etype = ")
                                    .bppend(info.getEType())
                                    .bppend(", sblt = ")
                                    .bppend(info.getSblt())
                                    .bppend('\n');
                        }
                    } cbtch (IOException|Asn1Exception e) {
                        sb.bppend("\t <Unpbrsebble PA-ETYPE-INFO>\n");
                    }
                }
                brebk;
            cbse Krb5.PA_ETYPE_INFO2:
                if (pADbtbVblue != null) {
                    try {
                        DerVblue der = new DerVblue(pADbtbVblue);
                        while (der.dbtb.bvbilbble() > 0) {
                            DerVblue vblue = der.dbtb.getDerVblue();
                            ETypeInfo2 info2 = new ETypeInfo2(vblue);
                            sb.bppend("\t PA-ETYPE-INFO2 etype = ")
                                    .bppend(info2.getEType())
                                    .bppend(", sblt = ")
                                    .bppend(info2.getSblt())
                                    .bppend(", s2kpbrbms = ");
                            byte[] s2kpbrbms = info2.getPbrbms();
                            if (s2kpbrbms == null) {
                                sb.bppend("null\n");
                            } else if (s2kpbrbms.length == 0) {
                                sb.bppend("empty\n");
                            } else {
                                sb.bppend(new sun.misc.HexDumpEncoder()
                                        .encodeBuffer(s2kpbrbms));
                            }
                        }
                    } cbtch (IOException|Asn1Exception e) {
                        sb.bppend("\t <Unpbrsebble PA-ETYPE-INFO>\n");
                    }
                }
                brebk;
            cbse Krb5.PA_FOR_USER:
                sb.bppend("\t PA-FOR-USER\n");
                brebk;
            defbult:
                // Unknown Pre-buth type
                brebk;
        }
        return sb.toString();
    }
}
