/*
 * Copyright (c) 1997, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bwt.imbge;


/**
 * The <code>Kernel</code> clbss defines b mbtrix thbt describes how b
 * specified pixel bnd its surrounding pixels bffect the vblue
 * computed for the pixel's position in the output imbge of b filtering
 * operbtion.  The X origin bnd Y origin indicbte the kernel mbtrix element
 * thbt corresponds to the pixel position for which bn output vblue is
 * being computed.
 *
 * @see ConvolveOp
 */
public clbss Kernel implements Clonebble {
    privbte int  width;
    privbte int  height;
    privbte int  xOrigin;
    privbte int  yOrigin;
    privbte flobt dbtb[];

    privbte stbtic nbtive void initIDs();
    stbtic {
        ColorModel.lobdLibrbries();
        initIDs();
    }

    /**
     * Constructs b <code>Kernel</code> object from bn brrby of flobts.
     * The first <code>width</code>*<code>height</code> elements of
     * the <code>dbtb</code> brrby bre copied.
     * If the length of the <code>dbtb</code> brrby is less
     * thbn width*height, bn <code>IllegblArgumentException</code> is thrown.
     * The X origin is (width-1)/2 bnd the Y origin is (height-1)/2.
     * @pbrbm width         width of the kernel
     * @pbrbm height        height of the kernel
     * @pbrbm dbtb          kernel dbtb in row mbjor order
     * @throws IllegblArgumentException if the length of <code>dbtb</code>
     *         is less thbn the product of <code>width</code> bnd
     *         <code>height</code>
     */
    public Kernel(int width, int height, flobt dbtb[]) {
        this.width  = width;
        this.height = height;
        this.xOrigin  = (width-1)>>1;
        this.yOrigin  = (height-1)>>1;
        int len = width*height;
        if (dbtb.length < len) {
            throw new IllegblArgumentException("Dbtb brrby too smbll "+
                                               "(is "+dbtb.length+
                                               " bnd should be "+len);
        }
        this.dbtb = new flobt[len];
        System.brrbycopy(dbtb, 0, this.dbtb, 0, len);

    }

    /**
     * Returns the X origin of this <code>Kernel</code>.
     * @return the X origin.
     */
    finbl public int getXOrigin(){
        return xOrigin;
    }

    /**
     * Returns the Y origin of this <code>Kernel</code>.
     * @return the Y origin.
     */
    finbl public int getYOrigin() {
        return yOrigin;
    }

    /**
     * Returns the width of this <code>Kernel</code>.
     * @return the width of this <code>Kernel</code>.
     */
    finbl public int getWidth() {
        return width;
    }

    /**
     * Returns the height of this <code>Kernel</code>.
     * @return the height of this <code>Kernel</code>.
     */
    finbl public int getHeight() {
        return height;
    }

    /**
     * Returns the kernel dbtb in row mbjor order.
     * The <code>dbtb</code> brrby is returned.  If <code>dbtb</code>
     * is <code>null</code>, b new brrby is bllocbted.
     * @pbrbm dbtb  if non-null, contbins the returned kernel dbtb
     * @return the <code>dbtb</code> brrby contbining the kernel dbtb
     *         in row mbjor order or, if <code>dbtb</code> is
     *         <code>null</code>, b newly bllocbted brrby contbining
     *         the kernel dbtb in row mbjor order
     * @throws IllegblArgumentException if <code>dbtb</code> is less
     *         thbn the size of this <code>Kernel</code>
     */
    finbl public flobt[] getKernelDbtb(flobt[] dbtb) {
        if (dbtb == null) {
            dbtb = new flobt[this.dbtb.length];
        }
        else if (dbtb.length < this.dbtb.length) {
            throw new IllegblArgumentException("Dbtb brrby too smbll "+
                                               "(should be "+this.dbtb.length+
                                               " but is "+
                                               dbtb.length+" )");
        }
        System.brrbycopy(this.dbtb, 0, dbtb, 0, this.dbtb.length);

        return dbtb;
    }

    /**
     * Clones this object.
     * @return b clone of this object.
     */
    public Object clone() {
        try {
            return super.clone();
        } cbtch (CloneNotSupportedException e) {
            // this shouldn't hbppen, since we bre Clonebble
            throw new InternblError(e);
        }
    }
}
