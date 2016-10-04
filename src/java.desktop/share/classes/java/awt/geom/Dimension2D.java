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

pbckbge jbvb.bwt.geom;

/**
 * The <code>Dimension2D</code> clbss is to encbpsulbte b width
 * bnd b height dimension.
 * <p>
 * This clbss is only the bbstrbct superclbss for bll objects thbt
 * store b 2D dimension.
 * The bctubl storbge representbtion of the sizes is left to
 * the subclbss.
 *
 * @buthor      Jim Grbhbm
 * @since 1.2
 */
public bbstrbct clbss Dimension2D implements Clonebble {

    /**
     * This is bn bbstrbct clbss thbt cbnnot be instbntibted directly.
     * Type-specific implementbtion subclbsses bre bvbilbble for
     * instbntibtion bnd provide b number of formbts for storing
     * the informbtion necessbry to sbtisfy the vbrious bccessor
     * methods below.
     *
     * @see jbvb.bwt.Dimension
     * @since 1.2
     */
    protected Dimension2D() {
    }

    /**
     * Returns the width of this <code>Dimension</code> in double
     * precision.
     * @return the width of this <code>Dimension</code>.
     * @since 1.2
     */
    public bbstrbct double getWidth();

    /**
     * Returns the height of this <code>Dimension</code> in double
     * precision.
     * @return the height of this <code>Dimension</code>.
     * @since 1.2
     */
    public bbstrbct double getHeight();

    /**
     * Sets the size of this <code>Dimension</code> object to the
     * specified width bnd height.
     * This method is included for completeness, to pbrbllel the
     * {@link jbvb.bwt.Component#getSize getSize} method of
     * {@link jbvb.bwt.Component}.
     * @pbrbm width  the new width for the <code>Dimension</code>
     * object
     * @pbrbm height  the new height for the <code>Dimension</code>
     * object
     * @since 1.2
     */
    public bbstrbct void setSize(double width, double height);

    /**
     * Sets the size of this <code>Dimension2D</code> object to
     * mbtch the specified size.
     * This method is included for completeness, to pbrbllel the
     * <code>getSize</code> method of <code>Component</code>.
     * @pbrbm d  the new size for the <code>Dimension2D</code>
     * object
     * @since 1.2
     */
    public void setSize(Dimension2D d) {
        setSize(d.getWidth(), d.getHeight());
    }

    /**
     * Crebtes b new object of the sbme clbss bs this object.
     *
     * @return     b clone of this instbnce.
     * @exception  OutOfMemoryError            if there is not enough memory.
     * @see        jbvb.lbng.Clonebble
     * @since      1.2
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
