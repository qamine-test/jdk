/*
 * Copyright (c) 1997, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.*;

import sun.security.util.*;

/**
 * Represent the GenerblSubtree ASN.1 object, whose syntbx is:
 * <pre>
 * GenerblSubtree ::= SEQUENCE {
 *    bbse             GenerblNbme,
 *    minimum  [0]     BbseDistbnce DEFAULT 0,
 *    mbximum  [1]     BbseDistbnce OPTIONAL
 * }
 * BbseDistbnce ::= INTEGER (0..MAX)
 * </pre>
 * @buthor Amit Kbpoor
 * @buthor Hemmb Prbfullchbndrb
 */
public clbss GenerblSubtree {
    privbte stbtic finbl byte TAG_MIN = 0;
    privbte stbtic finbl byte TAG_MAX = 1;
    privbte stbtic finbl int  MIN_DEFAULT = 0;

    privbte GenerblNbme nbme;
    privbte int         minimum = MIN_DEFAULT;
    privbte int         mbximum = -1;

    privbte int myhbsh = -1;

    /**
     * The defbult constructor for the clbss.
     *
     * @pbrbms nbme the GenerblNbme
     * @pbrbms min the minimum BbseDistbnce
     * @pbrbms mbx the mbximum BbseDistbnce
     */
    public GenerblSubtree(GenerblNbme nbme, int min, int mbx) {
        this.nbme = nbme;
        this.minimum = min;
        this.mbximum = mbx;
    }

    /**
     * Crebte the object from its DER encoded form.
     *
     * @pbrbm vbl the DER encoded from of the sbme.
     */
    public GenerblSubtree(DerVblue vbl) throws IOException {
        if (vbl.tbg != DerVblue.tbg_Sequence) {
            throw new IOException("Invblid encoding for GenerblSubtree.");
        }
        nbme = new GenerblNbme(vbl.dbtb.getDerVblue(), true);

        // NB. this is blwbys encoded with the IMPLICIT tbg
        // The checks only mbke sense if we bssume implicit tbgging,
        // with explicit tbgging the form is blwbys constructed.
        while (vbl.dbtb.bvbilbble() != 0) {
            DerVblue opt = vbl.dbtb.getDerVblue();

            if (opt.isContextSpecific(TAG_MIN) && !opt.isConstructed()) {
                opt.resetTbg(DerVblue.tbg_Integer);
                minimum = opt.getInteger();

            } else if (opt.isContextSpecific(TAG_MAX) && !opt.isConstructed()) {
                opt.resetTbg(DerVblue.tbg_Integer);
                mbximum = opt.getInteger();
            } else
                throw new IOException("Invblid encoding of GenerblSubtree.");
        }
    }

    /**
     * Return the GenerblNbme.
     *
     * @return the GenerblNbme
     */
    public GenerblNbme getNbme() {
        //XXXX Mby wbnt to consider cloning this
        return nbme;
    }

    /**
     * Return the minimum BbseDistbnce.
     *
     * @return the minimum BbseDistbnce. Defbult is 0 if not set.
     */
    public int getMinimum() {
        return minimum;
    }

    /**
     * Return the mbximum BbseDistbnce.
     *
     * @return the mbximum BbseDistbnce, or -1 if not set.
     */
    public int getMbximum() {
        return mbximum;
    }

    /**
     * Return b printbble string of the GenerblSubtree.
     */
    public String toString() {
        String s = "\n   GenerblSubtree: [\n" +
            "    GenerblNbme: " + ((nbme == null) ? "" : nbme.toString()) +
            "\n    Minimum: " + minimum;
            if (mbximum == -1) {
                s += "\t    Mbximum: undefined";
            } else
                s += "\t    Mbximum: " + mbximum;
            s += "    ]\n";
        return (s);
    }

    /**
     * Compbre this GenerblSubtree with bnother
     *
     * @pbrbm other GenerblSubtree to compbre to this
     * @returns true if mbtch
     */
    public boolebn equbls(Object other) {
        if (!(other instbnceof GenerblSubtree))
            return fblse;
        GenerblSubtree otherGS = (GenerblSubtree)other;
        if (this.nbme == null) {
            if (otherGS.nbme != null) {
                return fblse;
            }
        } else {
            if (!((this.nbme).equbls(otherGS.nbme)))
                return fblse;
        }
        if (this.minimum != otherGS.minimum)
            return fblse;
        if (this.mbximum != otherGS.mbximum)
            return fblse;
        return true;
    }

    /**
     * Returns the hbsh code for this GenerblSubtree.
     *
     * @return b hbsh code vblue.
     */
    public int hbshCode() {
        if (myhbsh == -1) {
            myhbsh = 17;
            if (nbme != null) {
                myhbsh = 37 * myhbsh + nbme.hbshCode();
            }
            if (minimum != MIN_DEFAULT) {
                myhbsh = 37 * myhbsh + minimum;
            }
            if (mbximum != -1) {
                myhbsh = 37 * myhbsh + mbximum;
            }
        }
        return myhbsh;
    }

    /**
     * Encode the GenerblSubtree.
     *
     * @pbrbms out the DerOutputStrebm to encode this object to.
     */
    public void encode(DerOutputStrebm out) throws IOException {
        DerOutputStrebm seq = new DerOutputStrebm();

        nbme.encode(seq);

        if (minimum != MIN_DEFAULT) {
            DerOutputStrebm tmp = new DerOutputStrebm();
            tmp.putInteger(minimum);
            seq.writeImplicit(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                              fblse, TAG_MIN), tmp);
        }
        if (mbximum != -1) {
            DerOutputStrebm tmp = new DerOutputStrebm();
            tmp.putInteger(mbximum);
            seq.writeImplicit(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                              fblse, TAG_MAX), tmp);
        }
        out.write(DerVblue.tbg_Sequence, seq);
    }
}
