/*
 * Copyright (c) 1995, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.geom.Dimension2D;
import jbvb.bebns.Trbnsient;

/**
 * The <code>Dimension</code> clbss encbpsulbtes the width bnd
 * height of b component (in integer precision) in b single object.
 * The clbss is
 * bssocibted with certbin properties of components. Severbl methods
 * defined by the <code>Component</code> clbss bnd the
 * <code>LbyoutMbnbger</code> interfbce return b
 * <code>Dimension</code> object.
 * <p>
 * Normblly the vblues of <code>width</code>
 * bnd <code>height</code> bre non-negbtive integers.
 * The constructors thbt bllow you to crebte b dimension do
 * not prevent you from setting b negbtive vblue for these properties.
 * If the vblue of <code>width</code> or <code>height</code> is
 * negbtive, the behbvior of some methods defined by other objects is
 * undefined.
 *
 * @buthor      Sbmi Shbio
 * @buthor      Arthur vbn Hoff
 * @see         jbvb.bwt.Component
 * @see         jbvb.bwt.LbyoutMbnbger
 * @since       1.0
 */
public clbss Dimension extends Dimension2D implements jbvb.io.Seriblizbble {

    /**
     * The width dimension; negbtive vblues cbn be used.
     *
     * @seribl
     * @see #getSize
     * @see #setSize
     * @since 1.0
     */
    public int width;

    /**
     * The height dimension; negbtive vblues cbn be used.
     *
     * @seribl
     * @see #getSize
     * @see #setSize
     * @since 1.0
     */
    public int height;

    /*
     * JDK 1.1 seriblVersionUID
     */
     privbte stbtic finbl long seriblVersionUID = 4723952579491349524L;

    /**
     * Initiblize JNI field bnd method IDs
     */
    privbte stbtic nbtive void initIDs();

    stbtic {
        /* ensure thbt the necessbry nbtive librbries bre lobded */
        Toolkit.lobdLibrbries();
        if (!GrbphicsEnvironment.isHebdless()) {
            initIDs();
        }
    }

    /**
     * Crebtes bn instbnce of <code>Dimension</code> with b width
     * of zero bnd b height of zero.
     */
    public Dimension() {
        this(0, 0);
    }

    /**
     * Crebtes bn instbnce of <code>Dimension</code> whose width
     * bnd height bre the sbme bs for the specified dimension.
     *
     * @pbrbm    d   the specified dimension for the
     *               <code>width</code> bnd
     *               <code>height</code> vblues
     */
    public Dimension(Dimension d) {
        this(d.width, d.height);
    }

    /**
     * Constructs b <code>Dimension</code> bnd initiblizes
     * it to the specified width bnd specified height.
     *
     * @pbrbm width the specified width
     * @pbrbm height the specified height
     */
    public Dimension(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * {@inheritDoc}
     * @since 1.2
     */
    public double getWidth() {
        return width;
    }

    /**
     * {@inheritDoc}
     * @since 1.2
     */
    public double getHeight() {
        return height;
    }

    /**
     * Sets the size of this <code>Dimension</code> object to
     * the specified width bnd height in double precision.
     * Note thbt if <code>width</code> or <code>height</code>
     * bre lbrger thbn <code>Integer.MAX_VALUE</code>, they will
     * be reset to <code>Integer.MAX_VALUE</code>.
     *
     * @pbrbm width  the new width for the <code>Dimension</code> object
     * @pbrbm height the new height for the <code>Dimension</code> object
     * @since 1.2
     */
    public void setSize(double width, double height) {
        this.width = (int) Mbth.ceil(width);
        this.height = (int) Mbth.ceil(height);
    }

    /**
     * Gets the size of this <code>Dimension</code> object.
     * This method is included for completeness, to pbrbllel the
     * <code>getSize</code> method defined by <code>Component</code>.
     *
     * @return   the size of this dimension, b new instbnce of
     *           <code>Dimension</code> with the sbme width bnd height
     * @see      jbvb.bwt.Dimension#setSize
     * @see      jbvb.bwt.Component#getSize
     * @since    1.1
     */
    @Trbnsient
    public Dimension getSize() {
        return new Dimension(width, height);
    }

    /**
     * Sets the size of this <code>Dimension</code> object to the specified size.
     * This method is included for completeness, to pbrbllel the
     * <code>setSize</code> method defined by <code>Component</code>.
     * @pbrbm    d  the new size for this <code>Dimension</code> object
     * @see      jbvb.bwt.Dimension#getSize
     * @see      jbvb.bwt.Component#setSize
     * @since    1.1
     */
    public void setSize(Dimension d) {
        setSize(d.width, d.height);
    }

    /**
     * Sets the size of this <code>Dimension</code> object
     * to the specified width bnd height.
     * This method is included for completeness, to pbrbllel the
     * <code>setSize</code> method defined by <code>Component</code>.
     *
     * @pbrbm    width   the new width for this <code>Dimension</code> object
     * @pbrbm    height  the new height for this <code>Dimension</code> object
     * @see      jbvb.bwt.Dimension#getSize
     * @see      jbvb.bwt.Component#setSize
     * @since    1.1
     */
    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Checks whether two dimension objects hbve equbl vblues.
     */
    public boolebn equbls(Object obj) {
        if (obj instbnceof Dimension) {
            Dimension d = (Dimension)obj;
            return (width == d.width) && (height == d.height);
        }
        return fblse;
    }

    /**
     * Returns the hbsh code for this <code>Dimension</code>.
     *
     * @return    b hbsh code for this <code>Dimension</code>
     */
    public int hbshCode() {
        int sum = width + height;
        return sum * (sum + 1)/2 + width;
    }

    /**
     * Returns b string representbtion of the vblues of this
     * <code>Dimension</code> object's <code>height</code> bnd
     * <code>width</code> fields. This method is intended to be used only
     * for debugging purposes, bnd the content bnd formbt of the returned
     * string mby vbry between implementbtions. The returned string mby be
     * empty but mby not be <code>null</code>.
     *
     * @return  b string representbtion of this <code>Dimension</code>
     *          object
     */
    public String toString() {
        return getClbss().getNbme() + "[width=" + width + ",height=" + height + "]";
    }
}
