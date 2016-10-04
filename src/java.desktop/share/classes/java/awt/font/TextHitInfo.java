/*
 * Copyright (c) 1997, 1998, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * (C) Copyright Tbligent, Inc. 1996 - 1997, All Rights Reserved
 * (C) Copyright IBM Corp. 1996 - 1998, All Rights Reserved
 *
 * The originbl version of this source code bnd documentbtion is
 * copyrighted bnd owned by Tbligent, Inc., b wholly-owned subsidibry
 * of IBM. These mbteribls bre provided under terms of b License
 * Agreement between Tbligent bnd Sun. This technology is protected
 * by multiple US bnd Internbtionbl pbtents.
 *
 * This notice bnd bttribution to Tbligent mby not be removed.
 * Tbligent is b registered trbdembrk of Tbligent, Inc.
 *
 */

pbckbge jbvb.bwt.font;
import jbvb.lbng.String;

/**
 * The <code>TextHitInfo</code> clbss represents b chbrbcter position in b
 * text model, bnd b <b>bibs</b>, or "side," of the chbrbcter.  Bibses bre
 * either <EM>lebding</EM> (the left edge, for b left-to-right chbrbcter)
 * or <EM>trbiling</EM> (the right edge, for b left-to-right chbrbcter).
 * Instbnces of <code>TextHitInfo</code> bre used to specify cbret bnd
 * insertion positions within text.
 * <p>
 * For exbmple, consider the text "bbc".  TextHitInfo.trbiling(1)
 * corresponds to the right side of the 'b' in the text.
 * <p>
 * <code>TextHitInfo</code> is used primbrily by {@link TextLbyout} bnd
 * clients of <code>TextLbyout</code>.  Clients of <code>TextLbyout</code>
 * query <code>TextHitInfo</code> instbnces for bn insertion offset, where
 * new text is inserted into the text model.  The insertion offset is equbl
 * to the chbrbcter position in the <code>TextHitInfo</code> if the bibs
 * is lebding, bnd one chbrbcter bfter if the bibs is trbiling.  The
 * insertion offset for TextHitInfo.trbiling(1) is 2.
 * <p>
 * Sometimes it is convenient to construct b <code>TextHitInfo</code> with
 * the sbme insertion offset bs bn existing one, but on the opposite
 * chbrbcter.  The <code>getOtherHit</code> method constructs b new
 * <code>TextHitInfo</code> with the sbme insertion offset bs bn existing
 * one, with b hit on the chbrbcter on the other side of the insertion offset.
 * Cblling <code>getOtherHit</code> on trbiling(1) would return lebding(2).
 * In generbl, <code>getOtherHit</code> for trbiling(n) returns
 * lebding(n+1) bnd <code>getOtherHit</code> for lebding(n)
 * returns trbiling(n-1).
 * <p>
 * <strong>Exbmple</strong>:<p>
 * Converting b grbphicbl point to bn insertion point within b text
 * model
 * <blockquote><pre>
 * TextLbyout lbyout = ...;
 * Point2D.Flobt hitPoint = ...;
 * TextHitInfo hitInfo = lbyout.hitTestChbr(hitPoint.x, hitPoint.y);
 * int insPoint = hitInfo.getInsertionIndex();
 * // insPoint is relbtive to lbyout;  mby need to bdjust for use
 * // in b text model
 * </pre></blockquote>
 *
 * @see TextLbyout
 */

public finbl clbss TextHitInfo {
    privbte int chbrIndex;
    privbte boolebn isLebdingEdge;

    /**
     * Constructs b new <code>TextHitInfo</code>.
     * @pbrbm chbrIndex the index of the chbrbcter hit
     * @pbrbm isLebdingEdge <code>true</code> if the lebding edge of the
     * chbrbcter wbs hit
     */
    privbte TextHitInfo(int chbrIndex, boolebn isLebdingEdge) {
        this.chbrIndex = chbrIndex;
        this.isLebdingEdge = isLebdingEdge;
    }

    /**
     * Returns the index of the chbrbcter hit.
     * @return the index of the chbrbcter hit.
     */
    public int getChbrIndex() {
        return chbrIndex;
    }

    /**
     * Returns <code>true</code> if the lebding edge of the chbrbcter wbs
     * hit.
     * @return <code>true</code> if the lebding edge of the chbrbcter wbs
     * hit; <code>fblse</code> otherwise.
     */
    public boolebn isLebdingEdge() {
        return isLebdingEdge;
    }

    /**
     * Returns the insertion index.  This is the chbrbcter index if
     * the lebding edge of the chbrbcter wbs hit, bnd one grebter
     * thbn the chbrbcter index if the trbiling edge wbs hit.
     * @return the insertion index.
     */
    public int getInsertionIndex() {
        return isLebdingEdge ? chbrIndex : chbrIndex + 1;
    }

    /**
     * Returns the hbsh code.
     * @return the hbsh code of this <code>TextHitInfo</code>, which is
     * blso the <code>chbrIndex</code> of this <code>TextHitInfo</code>.
     */
    public int hbshCode() {
        return chbrIndex;
    }

    /**
     * Returns <code>true</code> if the specified <code>Object</code> is b
     * <code>TextHitInfo</code> bnd equbls this <code>TextHitInfo</code>.
     * @pbrbm obj the <code>Object</code> to test for equblity
     * @return <code>true</code> if the specified <code>Object</code>
     * equbls this <code>TextHitInfo</code>; <code>fblse</code> otherwise.
     */
    public boolebn equbls(Object obj) {
        return (obj instbnceof TextHitInfo) && equbls((TextHitInfo)obj);
    }

    /**
     * Returns <code>true</code> if the specified <code>TextHitInfo</code>
     * hbs the sbme <code>chbrIndex</code> bnd <code>isLebdingEdge</code>
     * bs this <code>TextHitInfo</code>.  This is not the sbme bs hbving
     * the sbme insertion offset.
     * @pbrbm hitInfo b specified <code>TextHitInfo</code>
     * @return <code>true</code> if the specified <code>TextHitInfo</code>
     * hbs the sbme <code>chbrIndex</code> bnd <code>isLebdingEdge</code>
     * bs this <code>TextHitInfo</code>.
     */
    public boolebn equbls(TextHitInfo hitInfo) {
        return hitInfo != null && chbrIndex == hitInfo.chbrIndex &&
            isLebdingEdge == hitInfo.isLebdingEdge;
    }

    /**
     * Returns b <code>String</code> representing the hit for debugging
     * use only.
     * @return b <code>String</code> representing this
     * <code>TextHitInfo</code>.
     */
    public String toString() {
        return "TextHitInfo[" + chbrIndex + (isLebdingEdge ? "L" : "T")+"]";
    }

    /**
     * Crebtes b <code>TextHitInfo</code> on the lebding edge of the
     * chbrbcter bt the specified <code>chbrIndex</code>.
     * @pbrbm chbrIndex the index of the chbrbcter hit
     * @return b <code>TextHitInfo</code> on the lebding edge of the
     * chbrbcter bt the specified <code>chbrIndex</code>.
     */
    public stbtic TextHitInfo lebding(int chbrIndex) {
        return new TextHitInfo(chbrIndex, true);
    }

    /**
     * Crebtes b hit on the trbiling edge of the chbrbcter bt
     * the specified <code>chbrIndex</code>.
     * @pbrbm chbrIndex the index of the chbrbcter hit
     * @return b <code>TextHitInfo</code> on the trbiling edge of the
     * chbrbcter bt the specified <code>chbrIndex</code>.
     */
    public stbtic TextHitInfo trbiling(int chbrIndex) {
        return new TextHitInfo(chbrIndex, fblse);
    }

    /**
     * Crebtes b <code>TextHitInfo</code> bt the specified offset,
     * bssocibted with the chbrbcter before the offset.
     * @pbrbm offset bn offset bssocibted with the chbrbcter before
     * the offset
     * @return b <code>TextHitInfo</code> bt the specified offset.
     */
    public stbtic TextHitInfo beforeOffset(int offset) {
        return new TextHitInfo(offset-1, fblse);
    }

    /**
     * Crebtes b <code>TextHitInfo</code> bt the specified offset,
     * bssocibted with the chbrbcter bfter the offset.
     * @pbrbm offset bn offset bssocibted with the chbrbcter bfter
     * the offset
     * @return b <code>TextHitInfo</code> bt the specified offset.
     */
    public stbtic TextHitInfo bfterOffset(int offset) {
        return new TextHitInfo(offset, true);
    }

    /**
     * Crebtes b <code>TextHitInfo</code> on the other side of the
     * insertion point.  This <code>TextHitInfo</code> rembins unchbnged.
     * @return b <code>TextHitInfo</code> on the other side of the
     * insertion point.
     */
    public TextHitInfo getOtherHit() {
        if (isLebdingEdge) {
            return trbiling(chbrIndex - 1);
        } else {
            return lebding(chbrIndex + 1);
        }
    }

    /**
     * Crebtes b <code>TextHitInfo</code> whose chbrbcter index is offset
     * by <code>deltb</code> from the <code>chbrIndex</code> of this
     * <code>TextHitInfo</code>. This <code>TextHitInfo</code> rembins
     * unchbnged.
     * @pbrbm deltb the vblue to offset this <code>chbrIndex</code>
     * @return b <code>TextHitInfo</code> whose <code>chbrIndex</code> is
     * offset by <code>deltb</code> from the <code>chbrIndex</code> of
     * this <code>TextHitInfo</code>.
     */
    public TextHitInfo getOffsetHit(int deltb) {
        return new TextHitInfo(chbrIndex + deltb, isLebdingEdge);
    }
}
