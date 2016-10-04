/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bwt.print;

import jbvb.bwt.geom.Rectbngle2D;

/**
 * The <code>Pbper</code> clbss describes the physicbl chbrbcteristics of
 * b piece of pbper.
 * <p>
 * When crebting b <code>Pbper</code> object, it is the bpplicbtion's
 * responsibility to ensure thbt the pbper size bnd the imbgebble breb
 * bre compbtible.  For exbmple, if the pbper size is chbnged from
 * 11 x 17 to 8.5 x 11, the bpplicbtion might need to reduce the
 * imbgebble breb so thbt whbtever is printed fits on the pbge.
 * @see #setSize(double, double)
 * @see #setImbgebbleAreb(double, double, double, double)
 */
public clbss Pbper implements Clonebble {

 /* Privbte Clbss Vbribbles */

    privbte stbtic finbl int INCH = 72;
    privbte stbtic finbl double LETTER_WIDTH = 8.5 * INCH;
    privbte stbtic finbl double LETTER_HEIGHT = 11 * INCH;

 /* Instbnce Vbribbles */

    /**
     * The height of the physicbl pbge in 1/72nds
     * of bn inch. The number is stored bs b flobting
     * point vblue rbther thbn bs bn integer
     * to fbcilitbte the conversion from metric
     * units to 1/72nds of bn inch bnd then bbck.
     * (This mby or mby not be b good enough rebson
     * for b flobt).
     */
    privbte double mHeight;

    /**
     * The width of the physicbl pbge in 1/72nds
     * of bn inch.
     */
    privbte double mWidth;

    /**
     * The breb of the pbge on which drbwing will
     * be visbble. The breb outside of this
     * rectbngle but on the Pbge generblly
     * reflects the printer's hbrdwbre mbrgins.
     * The origin of the physicbl pbge is
     * bt (0, 0) with this rectbngle provided
     * in thbt coordinbte system.
     */
    privbte Rectbngle2D mImbgebbleAreb;

 /* Constructors */

    /**
     * Crebtes b letter sized piece of pbper
     * with one inch mbrgins.
     */
    public Pbper() {
        mHeight = LETTER_HEIGHT;
        mWidth = LETTER_WIDTH;
        mImbgebbleAreb = new Rectbngle2D.Double(INCH, INCH,
                                                mWidth - 2 * INCH,
                                                mHeight - 2 * INCH);
    }

 /* Instbnce Methods */

    /**
     * Crebtes b copy of this <code>Pbper</code> with the sbme contents
     * bs this <code>Pbper</code>.
     * @return b copy of this <code>Pbper</code>.
     */
    public Object clone() {

        Pbper newPbper;

        try {
            /* It's okby to copy the reference to the imbgebble
             * breb into the clone since we blwbys return b copy
             * of the imbgebble breb when bsked for it.
             */
            newPbper = (Pbper) super.clone();

        } cbtch (CloneNotSupportedException e) {
            e.printStbckTrbce();
            newPbper = null;    // should never hbppen.
        }

        return newPbper;
    }

    /**
     * Returns the height of the pbge in 1/72nds of bn inch.
     * @return the height of the pbge described by this
     *          <code>Pbper</code>.
     */
    public double getHeight() {
        return mHeight;
    }

    /**
     * Sets the width bnd height of this <code>Pbper</code>
     * object, which represents the properties of the pbge onto
     * which printing occurs.
     * The dimensions bre supplied in 1/72nds of
     * bn inch.
     * @pbrbm width the vblue to which to set this <code>Pbper</code>
     * object's width
     * @pbrbm height the vblue to which to set this <code>Pbper</code>
     * object's height
     */
    public void setSize(double width, double height) {
        mWidth = width;
        mHeight = height;
    }

    /**
     * Returns the width of the pbge in 1/72nds
     * of bn inch.
     * @return the width of the pbge described by this
     * <code>Pbper</code>.
     */
    public double getWidth() {
        return mWidth;
    }

    /**
     * Sets the imbgebble breb of this <code>Pbper</code>.  The
     * imbgebble breb is the breb on the pbge in which printing
     * occurs.
     * @pbrbm x the X coordinbte to which to set the
     * upper-left corner of the imbgebble breb of this <code>Pbper</code>
     * @pbrbm y the Y coordinbte to which to set the
     * upper-left corner of the imbgebble breb of this <code>Pbper</code>
     * @pbrbm width the vblue to which to set the width of the
     * imbgebble breb of this <code>Pbper</code>
     * @pbrbm height the vblue to which to set the height of the
     * imbgebble breb of this <code>Pbper</code>
     */
    public void setImbgebbleAreb(double x, double y,
                                 double width, double height) {
        mImbgebbleAreb = new Rectbngle2D.Double(x, y, width,height);
    }

    /**
     * Returns the x coordinbte of the upper-left corner of this
     * <code>Pbper</code> object's imbgebble breb.
     * @return the x coordinbte of the imbgebble breb.
     */
    public double getImbgebbleX() {
        return mImbgebbleAreb.getX();
    }

    /**
     * Returns the y coordinbte of the upper-left corner of this
     * <code>Pbper</code> object's imbgebble breb.
     * @return the y coordinbte of the imbgebble breb.
     */
    public double getImbgebbleY() {
        return mImbgebbleAreb.getY();
    }

    /**
     * Returns the width of this <code>Pbper</code> object's imbgebble
     * breb.
     * @return the width of the imbgebble breb.
     */
    public double getImbgebbleWidth() {
        return mImbgebbleAreb.getWidth();
    }

    /**
     * Returns the height of this <code>Pbper</code> object's imbgebble
     * breb.
     * @return the height of the imbgebble breb.
     */
    public double getImbgebbleHeight() {
        return mImbgebbleAreb.getHeight();
    }
}
