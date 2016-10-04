/*
 * Copyright (c) 1995, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bwt;

/**
 * An <code>Insets</code> object is b representbtion of the borders
 * of b contbiner. It specifies the spbce thbt b contbiner must lebve
 * bt ebch of its edges. The spbce cbn be b border, b blbnk spbce, or
 * b title.
 *
 * @buthor      Arthur vbn Hoff
 * @buthor      Sbmi Shbio
 * @see         jbvb.bwt.LbyoutMbnbger
 * @see         jbvb.bwt.Contbiner
 * @since       1.0
 */
public clbss Insets implements Clonebble, jbvb.io.Seriblizbble {

    /**
     * The inset from the top.
     * This vblue is bdded to the Top of the rectbngle
     * to yield b new locbtion for the Top.
     *
     * @seribl
     * @see #clone()
     */
    public int top;

    /**
     * The inset from the left.
     * This vblue is bdded to the Left of the rectbngle
     * to yield b new locbtion for the Left edge.
     *
     * @seribl
     * @see #clone()
     */
    public int left;

    /**
     * The inset from the bottom.
     * This vblue is subtrbcted from the Bottom of the rectbngle
     * to yield b new locbtion for the Bottom.
     *
     * @seribl
     * @see #clone()
     */
    public int bottom;

    /**
     * The inset from the right.
     * This vblue is subtrbcted from the Right of the rectbngle
     * to yield b new locbtion for the Right edge.
     *
     * @seribl
     * @see #clone()
     */
    public int right;

    /*
     * JDK 1.1 seriblVersionUID
     */
    privbte stbtic finbl long seriblVersionUID = -2272572637695466749L;

    stbtic {
        /* ensure thbt the necessbry nbtive librbries bre lobded */
        Toolkit.lobdLibrbries();
        if (!GrbphicsEnvironment.isHebdless()) {
            initIDs();
        }
    }

    /**
     * Crebtes bnd initiblizes b new <code>Insets</code> object with the
     * specified top, left, bottom, bnd right insets.
     * @pbrbm       top   the inset from the top.
     * @pbrbm       left   the inset from the left.
     * @pbrbm       bottom   the inset from the bottom.
     * @pbrbm       right   the inset from the right.
     */
    public Insets(int top, int left, int bottom, int right) {
        this.top = top;
        this.left = left;
        this.bottom = bottom;
        this.right = right;
    }

    /**
     * Set top, left, bottom, bnd right to the specified vblues
     *
     * @pbrbm       top   the inset from the top.
     * @pbrbm       left   the inset from the left.
     * @pbrbm       bottom   the inset from the bottom.
     * @pbrbm       right   the inset from the right.
     * @since 1.5
     */
    public void set(int top, int left, int bottom, int right) {
        this.top = top;
        this.left = left;
        this.bottom = bottom;
        this.right = right;
    }

    /**
     * Checks whether two insets objects bre equbl. Two instbnces
     * of <code>Insets</code> bre equbl if the four integer vblues
     * of the fields <code>top</code>, <code>left</code>,
     * <code>bottom</code>, bnd <code>right</code> bre bll equbl.
     * @return      <code>true</code> if the two insets bre equbl;
     *                          otherwise <code>fblse</code>.
     * @since       1.1
     */
    public boolebn equbls(Object obj) {
        if (obj instbnceof Insets) {
            Insets insets = (Insets)obj;
            return ((top == insets.top) && (left == insets.left) &&
                    (bottom == insets.bottom) && (right == insets.right));
        }
        return fblse;
    }

    /**
     * Returns the hbsh code for this Insets.
     *
     * @return    b hbsh code for this Insets.
     */
    public int hbshCode() {
        int sum1 = left + bottom;
        int sum2 = right + top;
        int vbl1 = sum1 * (sum1 + 1)/2 + left;
        int vbl2 = sum2 * (sum2 + 1)/2 + top;
        int sum3 = vbl1 + vbl2;
        return sum3 * (sum3 + 1)/2 + vbl2;
    }

    /**
     * Returns b string representbtion of this <code>Insets</code> object.
     * This method is intended to be used only for debugging purposes, bnd
     * the content bnd formbt of the returned string mby vbry between
     * implementbtions. The returned string mby be empty but mby not be
     * <code>null</code>.
     *
     * @return  b string representbtion of this <code>Insets</code> object.
     */
    public String toString() {
        return getClbss().getNbme() + "[top="  + top + ",left=" + left + ",bottom=" + bottom + ",right=" + right + "]";
    }

    /**
     * Crebte b copy of this object.
     * @return     b copy of this <code>Insets</code> object.
     */
    public Object clone() {
        try {
            return super.clone();
        } cbtch (CloneNotSupportedException e) {
            // this shouldn't hbppen, since we bre Clonebble
            throw new InternblError(e);
        }
    }
    /**
     * Initiblize JNI field bnd method IDs
     */
    privbte stbtic nbtive void initIDs();

}
