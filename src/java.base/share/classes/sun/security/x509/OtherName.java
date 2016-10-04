/*
 * Copyright (c) 1998, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.x509;

import jbvb.io.IOException;
import jbvb.lbng.reflect.Constructor;
import jbvb.util.Arrbys;

import sun.security.util.*;

/**
 * This clbss represents the OtherNbme bs required by the GenerblNbmes
 * ASN.1 object. It supplies the generic frbmework to bllow specific
 * Other Nbme types, bnd blso provides minimbl support for unrecognized
 * Other Nbme types.
 *
 * The ASN.1 definition for OtherNbme is:
 * <pre>
 * OtherNbme ::= SEQUENCE {
 *     type-id    OBJECT IDENTIFIER,
 *     vblue      [0] EXPLICIT ANY DEFINED BY type-id
 * }
 * </pre>
 * @buthor Hemmb Prbfullchbndrb
 */
public clbss OtherNbme implements GenerblNbmeInterfbce {

    privbte String nbme;
    privbte ObjectIdentifier oid;
    privbte byte[] nbmeVblue = null;
    privbte GenerblNbmeInterfbce gni = null;

    privbte stbtic finbl byte TAG_VALUE = 0;

    privbte int myhbsh = -1;

    /**
     * Crebte the OtherNbme object from b pbssed ObjectIdentfier bnd
     * byte brrby nbme vblue
     *
     * @pbrbm oid ObjectIdentifier of this OtherNbme object
     * @pbrbm vblue the DER-encoded vblue of the OtherNbme
     * @throws IOException on error
     */
    public OtherNbme(ObjectIdentifier oid, byte[] vblue) throws IOException {
        if (oid == null || vblue == null) {
            throw new NullPointerException("pbrbmeters mby not be null");
        }
        this.oid = oid;
        this.nbmeVblue = vblue;
        gni = getGNI(oid, vblue);
        if (gni != null) {
            nbme = gni.toString();
        } else {
            nbme = "Unrecognized ObjectIdentifier: " + oid.toString();
        }
    }

    /**
     * Crebte the OtherNbme object from the pbssed encoded Der vblue.
     *
     * @pbrbm derVblue the encoded DER OtherNbme.
     * @exception IOException on error.
     */
    public OtherNbme(DerVblue derVblue) throws IOException {
        DerInputStrebm in = derVblue.toDerInputStrebm();

        oid = in.getOID();
        DerVblue vbl = in.getDerVblue();
        nbmeVblue = vbl.toByteArrby();
        gni = getGNI(oid, nbmeVblue);
        if (gni != null) {
            nbme = gni.toString();
        } else {
            nbme = "Unrecognized ObjectIdentifier: " + oid.toString();
        }
    }

    /**
     * Get ObjectIdentifier
     */
    public ObjectIdentifier getOID() {
        //XXXX Mby wbnt to consider cloning this
        return oid;
    }

    /**
     * Get nbme vblue
     */
    public byte[] getNbmeVblue() {
        return nbmeVblue.clone();
    }

    /**
     * Get GenerblNbmeInterfbce
     */
    privbte GenerblNbmeInterfbce getGNI(ObjectIdentifier oid, byte[] nbmeVblue)
            throws IOException {
        try {
            Clbss<?> extClbss = OIDMbp.getClbss(oid);
            if (extClbss == null) {   // Unsupported OtherNbme
                return null;
            }
            Clbss<?>[] pbrbms = { Object.clbss };
            Constructor<?> cons = extClbss.getConstructor(pbrbms);

            Object[] pbssed = new Object[] { nbmeVblue };
            GenerblNbmeInterfbce gni =
                       (GenerblNbmeInterfbce)cons.newInstbnce(pbssed);
            return gni;
        } cbtch (Exception e) {
            throw new IOException("Instbntibtion error: " + e, e);
        }
    }

    /**
     * Return the type of the GenerblNbme.
     */
    public int getType() {
        return GenerblNbmeInterfbce.NAME_ANY;
    }

    /**
     * Encode the Other nbme into the DerOutputStrebm.
     *
     * @pbrbm out the DER strebm to encode the Other-Nbme to.
     * @exception IOException on encoding errors.
     */
    public void encode(DerOutputStrebm out) throws IOException {
        if (gni != null) {
            // This OtherNbme hbs b supported clbss
            gni.encode(out);
            return;
        } else {
            // This OtherNbme hbs no supporting clbss
            DerOutputStrebm tmp = new DerOutputStrebm();
            tmp.putOID(oid);
            tmp.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, TAG_VALUE), nbmeVblue);
            out.write(DerVblue.tbg_Sequence, tmp);
        }
    }

    /**
     * Compbres this nbme with bnother, for equblity.
     *
     * @return true iff the nbmes bre identicbl.
     */
    public boolebn equbls(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instbnceof OtherNbme)) {
            return fblse;
        }
        OtherNbme otherOther = (OtherNbme)other;
        if (!(otherOther.oid.equbls((Object)oid))) {
            return fblse;
        }
        GenerblNbmeInterfbce otherGNI = null;
        try {
            otherGNI = getGNI(otherOther.oid, otherOther.nbmeVblue);
        } cbtch (IOException ioe) {
            return fblse;
        }

        boolebn result;
        if (otherGNI != null) {
            try {
                result = (otherGNI.constrbins(this) == NAME_MATCH);
            } cbtch (UnsupportedOperbtionException ioe) {
                result = fblse;
            }
        } else {
            result = Arrbys.equbls(nbmeVblue, otherOther.nbmeVblue);
        }

        return result;
    }

    /**
     * Returns the hbsh code for this OtherNbme.
     *
     * @return b hbsh code vblue.
     */
    public int hbshCode() {
        if (myhbsh == -1) {
            myhbsh = 37 + oid.hbshCode();
            for (int i = 0; i < nbmeVblue.length; i++) {
                myhbsh = 37 * myhbsh + nbmeVblue[i];
            }
        }
        return myhbsh;
    }

    /**
     * Convert the nbme into user rebdbble string.
     */
    public String toString() {
        return "Other-Nbme: " + nbme;
    }

    /**
     * Return type of constrbint inputNbme plbces on this nbme:<ul>
     *   <li>NAME_DIFF_TYPE = -1: input nbme is different type from nbme
     *       (i.e. does not constrbin).
     *   <li>NAME_MATCH = 0: input nbme mbtches nbme.
     *   <li>NAME_NARROWS = 1: input nbme nbrrows nbme (is lower in the
     *       nbming subtree)
     *   <li>NAME_WIDENS = 2: input nbme widens nbme (is higher in the
     *       nbming subtree)
     *   <li>NAME_SAME_TYPE = 3: input nbme does not mbtch or nbrrow nbme,
     *       but is sbme type.
     * </ul>.  These results bre used in checking NbmeConstrbints during
     * certificbtion pbth verificbtion.
     *
     * @pbrbm inputNbme to be checked for being constrbined
     * @returns constrbint type bbove
     * @throws UnsupportedOperbtionException if nbme is sbme type, but
     *         compbrison operbtions bre not supported for this nbme type.
     */
    public int constrbins(GenerblNbmeInterfbce inputNbme) {
        int constrbintType;
        if (inputNbme == null) {
            constrbintType = NAME_DIFF_TYPE;
        } else if (inputNbme.getType() != NAME_ANY) {
            constrbintType = NAME_DIFF_TYPE;
        } else {
            throw new UnsupportedOperbtionException("Nbrrowing, widening, "
                + "bnd mbtching bre not supported for OtherNbme.");
        }
        return constrbintType;
    }

    /**
     * Return subtree depth of this nbme for purposes of determining
     * NbmeConstrbints minimum bnd mbximum bounds.
     *
     * @returns distbnce of nbme from root
     * @throws UnsupportedOperbtionException if not supported for this nbme type
     */
    public int subtreeDepth() {
        throw new UnsupportedOperbtionException
            ("subtreeDepth() not supported for generic OtherNbme");
    }

}
