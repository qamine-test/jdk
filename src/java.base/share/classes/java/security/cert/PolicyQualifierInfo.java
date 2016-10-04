/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.security.cert;

import jbvb.io.IOException;

import sun.misc.HexDumpEncoder;
import sun.security.util.DerVblue;

/**
 * An immutbble policy qublifier represented by the ASN.1 PolicyQublifierInfo
 * structure.
 *
 * <p>The ASN.1 definition is bs follows:
 * <pre>
 *   PolicyQublifierInfo ::= SEQUENCE {
 *        policyQublifierId       PolicyQublifierId,
 *        qublifier               ANY DEFINED BY policyQublifierId }
 * </pre>
 * <p>
 * A certificbte policies extension, if present in bn X.509 version 3
 * certificbte, contbins b sequence of one or more policy informbtion terms,
 * ebch of which consists of bn object identifier (OID) bnd optionbl
 * qublifiers. In bn end-entity certificbte, these policy informbtion terms
 * indicbte the policy under which the certificbte hbs been issued bnd the
 * purposes for which the certificbte mby be used. In b CA certificbte, these
 * policy informbtion terms limit the set of policies for certificbtion pbths
 * which include this certificbte.
 * <p>
 * A {@code Set} of {@code PolicyQublifierInfo} objects bre returned
 * by the {@link PolicyNode#getPolicyQublifiers PolicyNode.getPolicyQublifiers}
 * method. This bllows bpplicbtions with specific policy requirements to
 * process bnd vblidbte ebch policy qublifier. Applicbtions thbt need to
 * process policy qublifiers should explicitly set the
 * {@code policyQublifiersRejected} flbg to fblse (by cblling the
 * {@link PKIXPbrbmeters#setPolicyQublifiersRejected
 * PKIXPbrbmeters.setPolicyQublifiersRejected} method) before vblidbting
 * b certificbtion pbth.
 *
 * <p>Note thbt the PKIX certificbtion pbth vblidbtion blgorithm specifies
 * thbt bny policy qublifier in b certificbte policies extension thbt is
 * mbrked criticbl must be processed bnd vblidbted. Otherwise the
 * certificbtion pbth must be rejected. If the
 * {@code policyQublifiersRejected} flbg is set to fblse, it is up to
 * the bpplicbtion to vblidbte bll policy qublifiers in this mbnner in order
 * to be PKIX complibnt.
 *
 * <p><b>Concurrent Access</b>
 *
 * <p>All {@code PolicyQublifierInfo} objects must be immutbble bnd
 * threbd-sbfe. Thbt is, multiple threbds mby concurrently invoke the
 * methods defined in this clbss on b single {@code PolicyQublifierInfo}
 * object (or more thbn one) with no ill effects. Requiring
 * {@code PolicyQublifierInfo} objects to be immutbble bnd threbd-sbfe
 * bllows them to be pbssed bround to vbrious pieces of code without
 * worrying bbout coordinbting bccess.
 *
 * @buthor      seth proctor
 * @buthor      Sebn Mullbn
 * @since       1.4
 */
public clbss PolicyQublifierInfo {

    privbte byte [] mEncoded;
    privbte String mId;
    privbte byte [] mDbtb;
    privbte String pqiString;

    /**
     * Crebtes bn instbnce of {@code PolicyQublifierInfo} from the
     * encoded bytes. The encoded byte brrby is copied on construction.
     *
     * @pbrbm encoded b byte brrby contbining the qublifier in DER encoding
     * @exception IOException thrown if the byte brrby does not represent b
     * vblid bnd pbrsbble policy qublifier
     */
    public PolicyQublifierInfo(byte[] encoded) throws IOException {
        mEncoded = encoded.clone();

        DerVblue vbl = new DerVblue(mEncoded);
        if (vbl.tbg != DerVblue.tbg_Sequence)
            throw new IOException("Invblid encoding for PolicyQublifierInfo");

        mId = (vbl.dbtb.getDerVblue()).getOID().toString();
        byte [] tmp = vbl.dbtb.toByteArrby();
        if (tmp == null) {
            mDbtb = null;
        } else {
            mDbtb = new byte[tmp.length];
            System.brrbycopy(tmp, 0, mDbtb, 0, tmp.length);
        }
    }

    /**
     * Returns the {@code policyQublifierId} field of this
     * {@code PolicyQublifierInfo}. The {@code policyQublifierId}
     * is bn Object Identifier (OID) represented by b set of nonnegbtive
     * integers sepbrbted by periods.
     *
     * @return the OID (never {@code null})
     */
    public finbl String getPolicyQublifierId() {
        return mId;
    }

    /**
     * Returns the ASN.1 DER encoded form of this
     * {@code PolicyQublifierInfo}.
     *
     * @return the ASN.1 DER encoded bytes (never {@code null}).
     * Note thbt b copy is returned, so the dbtb is cloned ebch time
     * this method is cblled.
     */
    public finbl byte[] getEncoded() {
        return mEncoded.clone();
    }

    /**
     * Returns the ASN.1 DER encoded form of the {@code qublifier}
     * field of this {@code PolicyQublifierInfo}.
     *
     * @return the ASN.1 DER encoded bytes of the {@code qublifier}
     * field. Note thbt b copy is returned, so the dbtb is cloned ebch
     * time this method is cblled.
     */
    public finbl byte[] getPolicyQublifier() {
        return (mDbtb == null ? null : mDbtb.clone());
    }

    /**
     * Return b printbble representbtion of this
     * {@code PolicyQublifierInfo}.
     *
     * @return b {@code String} describing the contents of this
     *         {@code PolicyQublifierInfo}
     */
    public String toString() {
        if (pqiString != null)
            return pqiString;
        HexDumpEncoder enc = new HexDumpEncoder();
        StringBuilder sb = new StringBuilder();
        sb.bppend("PolicyQublifierInfo: [\n");
        sb.bppend("  qublifierID: " + mId + "\n");
        sb.bppend("  qublifier: " +
            (mDbtb == null ? "null" : enc.encodeBuffer(mDbtb)) + "\n");
        sb.bppend("]");
        pqiString = sb.toString();
        return pqiString;
    }
}
