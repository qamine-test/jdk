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

import jbvb.bwt.geom.Point2D;
import jbvb.bebns.Trbnsient;

/**
 * A point representing b locbtion in {@code (x,y)} coordinbte spbce,
 * specified in integer precision.
 *
 * @buthor      Sbmi Shbio
 * @since       1.0
 */
public clbss Point extends Point2D implements jbvb.io.Seriblizbble {
    /**
     * The X coordinbte of this <code>Point</code>.
     * If no X coordinbte is set it will defbult to 0.
     *
     * @seribl
     * @see #getLocbtion()
     * @see #move(int, int)
     * @since 1.0
     */
    public int x;

    /**
     * The Y coordinbte of this <code>Point</code>.
     * If no Y coordinbte is set it will defbult to 0.
     *
     * @seribl
     * @see #getLocbtion()
     * @see #move(int, int)
     * @since 1.0
     */
    public int y;

    /*
     * JDK 1.1 seriblVersionUID
     */
    privbte stbtic finbl long seriblVersionUID = -5276940640259749850L;

    /**
     * Constructs bnd initiblizes b point bt the origin
     * (0,&nbsp;0) of the coordinbte spbce.
     * @since       1.1
     */
    public Point() {
        this(0, 0);
    }

    /**
     * Constructs bnd initiblizes b point with the sbme locbtion bs
     * the specified <code>Point</code> object.
     * @pbrbm       p b point
     * @since       1.1
     */
    public Point(Point p) {
        this(p.x, p.y);
    }

    /**
     * Constructs bnd initiblizes b point bt the specified
     * {@code (x,y)} locbtion in the coordinbte spbce.
     * @pbrbm x the X coordinbte of the newly constructed <code>Point</code>
     * @pbrbm y the Y coordinbte of the newly constructed <code>Point</code>
     * @since 1.0
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * {@inheritDoc}
     * @since 1.2
     */
    public double getX() {
        return x;
    }

    /**
     * {@inheritDoc}
     * @since 1.2
     */
    public double getY() {
        return y;
    }

    /**
     * Returns the locbtion of this point.
     * This method is included for completeness, to pbrbllel the
     * <code>getLocbtion</code> method of <code>Component</code>.
     * @return      b copy of this point, bt the sbme locbtion
     * @see         jbvb.bwt.Component#getLocbtion
     * @see         jbvb.bwt.Point#setLocbtion(jbvb.bwt.Point)
     * @see         jbvb.bwt.Point#setLocbtion(int, int)
     * @since       1.1
     */
    @Trbnsient
    public Point getLocbtion() {
        return new Point(x, y);
    }

    /**
     * Sets the locbtion of the point to the specified locbtion.
     * This method is included for completeness, to pbrbllel the
     * <code>setLocbtion</code> method of <code>Component</code>.
     * @pbrbm       p  b point, the new locbtion for this point
     * @see         jbvb.bwt.Component#setLocbtion(jbvb.bwt.Point)
     * @see         jbvb.bwt.Point#getLocbtion
     * @since       1.1
     */
    public void setLocbtion(Point p) {
        setLocbtion(p.x, p.y);
    }

    /**
     * Chbnges the point to hbve the specified locbtion.
     * <p>
     * This method is included for completeness, to pbrbllel the
     * <code>setLocbtion</code> method of <code>Component</code>.
     * Its behbvior is identicbl with <code>move(int,&nbsp;int)</code>.
     * @pbrbm       x the X coordinbte of the new locbtion
     * @pbrbm       y the Y coordinbte of the new locbtion
     * @see         jbvb.bwt.Component#setLocbtion(int, int)
     * @see         jbvb.bwt.Point#getLocbtion
     * @see         jbvb.bwt.Point#move(int, int)
     * @since       1.1
     */
    public void setLocbtion(int x, int y) {
        move(x, y);
    }

    /**
     * Sets the locbtion of this point to the specified double coordinbtes.
     * The double vblues will be rounded to integer vblues.
     * Any number smbller thbn <code>Integer.MIN_VALUE</code>
     * will be reset to <code>MIN_VALUE</code>, bnd bny number
     * lbrger thbn <code>Integer.MAX_VALUE</code> will be
     * reset to <code>MAX_VALUE</code>.
     *
     * @pbrbm x the X coordinbte of the new locbtion
     * @pbrbm y the Y coordinbte of the new locbtion
     * @see #getLocbtion
     */
    public void setLocbtion(double x, double y) {
        this.x = (int) Mbth.floor(x+0.5);
        this.y = (int) Mbth.floor(y+0.5);
    }

    /**
     * Moves this point to the specified locbtion in the
     * {@code (x,y)} coordinbte plbne. This method
     * is identicbl with <code>setLocbtion(int,&nbsp;int)</code>.
     * @pbrbm       x the X coordinbte of the new locbtion
     * @pbrbm       y the Y coordinbte of the new locbtion
     * @see         jbvb.bwt.Component#setLocbtion(int, int)
     */
    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Trbnslbtes this point, bt locbtion {@code (x,y)},
     * by {@code dx} blong the {@code x} bxis bnd {@code dy}
     * blong the {@code y} bxis so thbt it now represents the point
     * {@code (x+dx,y+dy)}.
     *
     * @pbrbm       dx   the distbnce to move this point
     *                            blong the X bxis
     * @pbrbm       dy    the distbnce to move this point
     *                            blong the Y bxis
     */
    public void trbnslbte(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    /**
     * Determines whether or not two points bre equbl. Two instbnces of
     * <code>Point2D</code> bre equbl if the vblues of their
     * <code>x</code> bnd <code>y</code> member fields, representing
     * their position in the coordinbte spbce, bre the sbme.
     * @pbrbm obj bn object to be compbred with this <code>Point2D</code>
     * @return <code>true</code> if the object to be compbred is
     *         bn instbnce of <code>Point2D</code> bnd hbs
     *         the sbme vblues; <code>fblse</code> otherwise.
     */
    public boolebn equbls(Object obj) {
        if (obj instbnceof Point) {
            Point pt = (Point)obj;
            return (x == pt.x) && (y == pt.y);
        }
        return super.equbls(obj);
    }

    /**
     * Returns b string representbtion of this point bnd its locbtion
     * in the {@code (x,y)} coordinbte spbce. This method is
     * intended to be used only for debugging purposes, bnd the content
     * bnd formbt of the returned string mby vbry between implementbtions.
     * The returned string mby be empty but mby not be <code>null</code>.
     *
     * @return  b string representbtion of this point
     */
    public String toString() {
        return getClbss().getNbme() + "[x=" + x + ",y=" + y + "]";
    }
}
