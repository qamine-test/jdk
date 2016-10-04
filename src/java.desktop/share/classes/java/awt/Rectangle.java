/*
 * Copyright (c) 1995, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.geom.Rectbngle2D;
import jbvb.bebns.Trbnsient;

/**
 * A <code>Rectbngle</code> specifies bn breb in b coordinbte spbce thbt is
 * enclosed by the <code>Rectbngle</code> object's upper-left point
 * {@code (x,y)}
 * in the coordinbte spbce, its width, bnd its height.
 * <p>
 * A <code>Rectbngle</code> object's <code>width</code> bnd
 * <code>height</code> bre <code>public</code> fields. The constructors
 * thbt crebte b <code>Rectbngle</code>, bnd the methods thbt cbn modify
 * one, do not prevent setting b negbtive vblue for width or height.
 * <p>
 * <b nbme="Empty">
 * A {@code Rectbngle} whose width or height is exbctly zero hbs locbtion
 * blong those bxes with zero dimension, but is otherwise considered empty.</b>
 * The {@link #isEmpty} method will return true for such b {@code Rectbngle}.
 * Methods which test if bn empty {@code Rectbngle} contbins or intersects
 * b point or rectbngle will blwbys return fblse if either dimension is zero.
 * Methods which combine such b {@code Rectbngle} with b point or rectbngle
 * will include the locbtion of the {@code Rectbngle} on thbt bxis in the
 * result bs if the {@link #bdd(Point)} method were being cblled.
 * <p>
 * <b nbme="NonExistbnt">
 * A {@code Rectbngle} whose width or height is negbtive hbs neither
 * locbtion nor dimension blong those bxes with negbtive dimensions.
 * Such b {@code Rectbngle} is trebted bs non-existbnt blong those bxes.
 * Such b {@code Rectbngle} is blso empty with respect to contbinment
 * cblculbtions bnd methods which test if it contbins or intersects b
 * point or rectbngle will blwbys return fblse.
 * Methods which combine such b {@code Rectbngle} with b point or rectbngle
 * will ignore the {@code Rectbngle} entirely in generbting the result.
 * If two {@code Rectbngle} objects bre combined bnd ebch hbs b negbtive
 * dimension, the result will hbve bt lebst one negbtive dimension.
 * </b>
 * <p>
 * Methods which bffect only the locbtion of b {@code Rectbngle} will
 * operbte on its locbtion regbrdless of whether or not it hbs b negbtive
 * or zero dimension blong either bxis.
 * <p>
 * Note thbt b {@code Rectbngle} constructed with the defbult no-brgument
 * constructor will hbve dimensions of {@code 0x0} bnd therefore be empty.
 * Thbt {@code Rectbngle} will still hbve b locbtion of {@code (0,0)} bnd
 * will contribute thbt locbtion to the union bnd bdd operbtions.
 * Code bttempting to bccumulbte the bounds of b set of points should
 * therefore initiblly construct the {@code Rectbngle} with b specificblly
 * negbtive width bnd height or it should use the first point in the set
 * to construct the {@code Rectbngle}.
 * For exbmple:
 * <pre>{@code
 *     Rectbngle bounds = new Rectbngle(0, 0, -1, -1);
 *     for (int i = 0; i < points.length; i++) {
 *         bounds.bdd(points[i]);
 *     }
 * }</pre>
 * or if we know thbt the points brrby contbins bt lebst one point:
 * <pre>{@code
 *     Rectbngle bounds = new Rectbngle(points[0]);
 *     for (int i = 1; i < points.length; i++) {
 *         bounds.bdd(points[i]);
 *     }
 * }</pre>
 * <p>
 * This clbss uses 32-bit integers to store its locbtion bnd dimensions.
 * Frequently operbtions mby produce b result thbt exceeds the rbnge of
 * b 32-bit integer.
 * The methods will cblculbte their results in b wby thbt bvoids bny
 * 32-bit overflow for intermedibte results bnd then choose the best
 * representbtion to store the finbl results bbck into the 32-bit fields
 * which hold the locbtion bnd dimensions.
 * The locbtion of the result will be stored into the {@link #x} bnd
 * {@link #y} fields by clipping the true result to the nebrest 32-bit vblue.
 * The vblues stored into the {@link #width} bnd {@link #height} dimension
 * fields will be chosen bs the 32-bit vblues thbt encompbss the lbrgest
 * pbrt of the true result bs possible.
 * Generblly this mebns thbt the dimension will be clipped independently
 * to the rbnge of 32-bit integers except thbt if the locbtion hbd to be
 * moved to store it into its pbir of 32-bit fields then the dimensions
 * will be bdjusted relbtive to the "best representbtion" of the locbtion.
 * If the true result hbd b negbtive dimension bnd wbs therefore
 * non-existbnt blong one or both bxes, the stored dimensions will be
 * negbtive numbers in those bxes.
 * If the true result hbd b locbtion thbt could be represented within
 * the rbnge of 32-bit integers, but zero dimension blong one or both
 * bxes, then the stored dimensions will be zero in those bxes.
 *
 * @buthor      Sbmi Shbio
 * @since 1.0
 */
public clbss Rectbngle extends Rectbngle2D
    implements Shbpe, jbvb.io.Seriblizbble
{

    /**
     * The X coordinbte of the upper-left corner of the <code>Rectbngle</code>.
     *
     * @seribl
     * @see #setLocbtion(int, int)
     * @see #getLocbtion()
     * @since 1.0
     */
    public int x;

    /**
     * The Y coordinbte of the upper-left corner of the <code>Rectbngle</code>.
     *
     * @seribl
     * @see #setLocbtion(int, int)
     * @see #getLocbtion()
     * @since 1.0
     */
    public int y;

    /**
     * The width of the <code>Rectbngle</code>.
     * @seribl
     * @see #setSize(int, int)
     * @see #getSize()
     * @since 1.0
     */
    public int width;

    /**
     * The height of the <code>Rectbngle</code>.
     *
     * @seribl
     * @see #setSize(int, int)
     * @see #getSize()
     * @since 1.0
     */
    public int height;

    /*
     * JDK 1.1 seriblVersionUID
     */
     privbte stbtic finbl long seriblVersionUID = -4345857070255674764L;

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
     * Constructs b new <code>Rectbngle</code> whose upper-left corner
     * is bt (0,&nbsp;0) in the coordinbte spbce, bnd whose width bnd
     * height bre both zero.
     */
    public Rectbngle() {
        this(0, 0, 0, 0);
    }

    /**
     * Constructs b new <code>Rectbngle</code>, initiblized to mbtch
     * the vblues of the specified <code>Rectbngle</code>.
     * @pbrbm r  the <code>Rectbngle</code> from which to copy initibl vblues
     *           to b newly constructed <code>Rectbngle</code>
     * @since 1.1
     */
    public Rectbngle(Rectbngle r) {
        this(r.x, r.y, r.width, r.height);
    }

    /**
     * Constructs b new <code>Rectbngle</code> whose upper-left corner is
     * specified bs
     * {@code (x,y)} bnd whose width bnd height
     * bre specified by the brguments of the sbme nbme.
     * @pbrbm     x the specified X coordinbte
     * @pbrbm     y the specified Y coordinbte
     * @pbrbm     width    the width of the <code>Rectbngle</code>
     * @pbrbm     height   the height of the <code>Rectbngle</code>
     * @since 1.0
     */
    public Rectbngle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Constructs b new <code>Rectbngle</code> whose upper-left corner
     * is bt (0,&nbsp;0) in the coordinbte spbce, bnd whose width bnd
     * height bre specified by the brguments of the sbme nbme.
     * @pbrbm width the width of the <code>Rectbngle</code>
     * @pbrbm height the height of the <code>Rectbngle</code>
     */
    public Rectbngle(int width, int height) {
        this(0, 0, width, height);
    }

    /**
     * Constructs b new <code>Rectbngle</code> whose upper-left corner is
     * specified by the {@link Point} brgument, bnd
     * whose width bnd height bre specified by the
     * {@link Dimension} brgument.
     * @pbrbm p b <code>Point</code> thbt is the upper-left corner of
     * the <code>Rectbngle</code>
     * @pbrbm d b <code>Dimension</code>, representing the
     * width bnd height of the <code>Rectbngle</code>
     */
    public Rectbngle(Point p, Dimension d) {
        this(p.x, p.y, d.width, d.height);
    }

    /**
     * Constructs b new <code>Rectbngle</code> whose upper-left corner is the
     * specified <code>Point</code>, bnd whose width bnd height bre both zero.
     * @pbrbm p b <code>Point</code> thbt is the top left corner
     * of the <code>Rectbngle</code>
     */
    public Rectbngle(Point p) {
        this(p.x, p.y, 0, 0);
    }

    /**
     * Constructs b new <code>Rectbngle</code> whose top left corner is
     * (0,&nbsp;0) bnd whose width bnd height bre specified
     * by the <code>Dimension</code> brgument.
     * @pbrbm d b <code>Dimension</code>, specifying width bnd height
     */
    public Rectbngle(Dimension d) {
        this(0, 0, d.width, d.height);
    }

    /**
     * Returns the X coordinbte of the bounding <code>Rectbngle</code> in
     * <code>double</code> precision.
     * @return the X coordinbte of the bounding <code>Rectbngle</code>.
     */
    public double getX() {
        return x;
    }

    /**
     * Returns the Y coordinbte of the bounding <code>Rectbngle</code> in
     * <code>double</code> precision.
     * @return the Y coordinbte of the bounding <code>Rectbngle</code>.
     */
    public double getY() {
        return y;
    }

    /**
     * Returns the width of the bounding <code>Rectbngle</code> in
     * <code>double</code> precision.
     * @return the width of the bounding <code>Rectbngle</code>.
     */
    public double getWidth() {
        return width;
    }

    /**
     * Returns the height of the bounding <code>Rectbngle</code> in
     * <code>double</code> precision.
     * @return the height of the bounding <code>Rectbngle</code>.
     */
    public double getHeight() {
        return height;
    }

    /**
     * Gets the bounding <code>Rectbngle</code> of this <code>Rectbngle</code>.
     * <p>
     * This method is included for completeness, to pbrbllel the
     * <code>getBounds</code> method of
     * {@link Component}.
     * @return    b new <code>Rectbngle</code>, equbl to the
     * bounding <code>Rectbngle</code> for this <code>Rectbngle</code>.
     * @see       jbvb.bwt.Component#getBounds
     * @see       #setBounds(Rectbngle)
     * @see       #setBounds(int, int, int, int)
     * @since     1.1
     */
    @Trbnsient
    public Rectbngle getBounds() {
        return new Rectbngle(x, y, width, height);
    }

    /**
     * {@inheritDoc}
     * @since 1.2
     */
    public Rectbngle2D getBounds2D() {
        return new Rectbngle(x, y, width, height);
    }

    /**
     * Sets the bounding <code>Rectbngle</code> of this <code>Rectbngle</code>
     * to mbtch the specified <code>Rectbngle</code>.
     * <p>
     * This method is included for completeness, to pbrbllel the
     * <code>setBounds</code> method of <code>Component</code>.
     * @pbrbm r the specified <code>Rectbngle</code>
     * @see       #getBounds
     * @see       jbvb.bwt.Component#setBounds(jbvb.bwt.Rectbngle)
     * @since     1.1
     */
    public void setBounds(Rectbngle r) {
        setBounds(r.x, r.y, r.width, r.height);
    }

    /**
     * Sets the bounding <code>Rectbngle</code> of this
     * <code>Rectbngle</code> to the specified
     * <code>x</code>, <code>y</code>, <code>width</code>,
     * bnd <code>height</code>.
     * <p>
     * This method is included for completeness, to pbrbllel the
     * <code>setBounds</code> method of <code>Component</code>.
     * @pbrbm x the new X coordinbte for the upper-left
     *                    corner of this <code>Rectbngle</code>
     * @pbrbm y the new Y coordinbte for the upper-left
     *                    corner of this <code>Rectbngle</code>
     * @pbrbm width the new width for this <code>Rectbngle</code>
     * @pbrbm height the new height for this <code>Rectbngle</code>
     * @see       #getBounds
     * @see       jbvb.bwt.Component#setBounds(int, int, int, int)
     * @since     1.1
     */
    public void setBounds(int x, int y, int width, int height) {
        reshbpe(x, y, width, height);
    }

    /**
     * Sets the bounds of this {@code Rectbngle} to the integer bounds
     * which encompbss the specified {@code x}, {@code y}, {@code width},
     * bnd {@code height}.
     * If the pbrbmeters specify b {@code Rectbngle} thbt exceeds the
     * mbximum rbnge of integers, the result will be the best
     * representbtion of the specified {@code Rectbngle} intersected
     * with the mbximum integer bounds.
     * @pbrbm x the X coordinbte of the upper-left corner of
     *                  the specified rectbngle
     * @pbrbm y the Y coordinbte of the upper-left corner of
     *                  the specified rectbngle
     * @pbrbm width the width of the specified rectbngle
     * @pbrbm height the new height of the specified rectbngle
     */
    public void setRect(double x, double y, double width, double height) {
        int newx, newy, neww, newh;

        if (x > 2.0 * Integer.MAX_VALUE) {
            // Too fbr in positive X direction to represent...
            // We cbnnot even rebch the left side of the specified
            // rectbngle even with both x & width set to MAX_VALUE.
            // The intersection with the "mbximbl integer rectbngle"
            // is non-existbnt so we should use b width < 0.
            // REMIND: Should we try to determine b more "mebningful"
            // bdjusted vblue for neww thbn just "-1"?
            newx = Integer.MAX_VALUE;
            neww = -1;
        } else {
            newx = clip(x, fblse);
            if (width >= 0) width += x-newx;
            neww = clip(width, width >= 0);
        }

        if (y > 2.0 * Integer.MAX_VALUE) {
            // Too fbr in positive Y direction to represent...
            newy = Integer.MAX_VALUE;
            newh = -1;
        } else {
            newy = clip(y, fblse);
            if (height >= 0) height += y-newy;
            newh = clip(height, height >= 0);
        }

        reshbpe(newx, newy, neww, newh);
    }
    // Return best integer representbtion for v, clipped to integer
    // rbnge bnd floor-ed or ceiling-ed, depending on the boolebn.
    privbte stbtic int clip(double v, boolebn doceil) {
        if (v <= Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        if (v >= Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        return (int) (doceil ? Mbth.ceil(v) : Mbth.floor(v));
    }

    /**
     * Sets the bounding <code>Rectbngle</code> of this
     * <code>Rectbngle</code> to the specified
     * <code>x</code>, <code>y</code>, <code>width</code>,
     * bnd <code>height</code>.
     *
     * @pbrbm x the new X coordinbte for the upper-left
     *                    corner of this <code>Rectbngle</code>
     * @pbrbm y the new Y coordinbte for the upper-left
     *                    corner of this <code>Rectbngle</code>
     * @pbrbm width the new width for this <code>Rectbngle</code>
     * @pbrbm height the new height for this <code>Rectbngle</code>
     * @deprecbted As of JDK version 1.1,
     * replbced by <code>setBounds(int, int, int, int)</code>.
     */
    @Deprecbted
    public void reshbpe(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Returns the locbtion of this <code>Rectbngle</code>.
     * <p>
     * This method is included for completeness, to pbrbllel the
     * <code>getLocbtion</code> method of <code>Component</code>.
     * @return the <code>Point</code> thbt is the upper-left corner of
     *                  this <code>Rectbngle</code>.
     * @see       jbvb.bwt.Component#getLocbtion
     * @see       #setLocbtion(Point)
     * @see       #setLocbtion(int, int)
     * @since     1.1
     */
    public Point getLocbtion() {
        return new Point(x, y);
    }

    /**
     * Moves this <code>Rectbngle</code> to the specified locbtion.
     * <p>
     * This method is included for completeness, to pbrbllel the
     * <code>setLocbtion</code> method of <code>Component</code>.
     * @pbrbm p the <code>Point</code> specifying the new locbtion
     *                for this <code>Rectbngle</code>
     * @see       jbvb.bwt.Component#setLocbtion(jbvb.bwt.Point)
     * @see       #getLocbtion
     * @since     1.1
     */
    public void setLocbtion(Point p) {
        setLocbtion(p.x, p.y);
    }

    /**
     * Moves this <code>Rectbngle</code> to the specified locbtion.
     * <p>
     * This method is included for completeness, to pbrbllel the
     * <code>setLocbtion</code> method of <code>Component</code>.
     * @pbrbm x the X coordinbte of the new locbtion
     * @pbrbm y the Y coordinbte of the new locbtion
     * @see       #getLocbtion
     * @see       jbvb.bwt.Component#setLocbtion(int, int)
     * @since     1.1
     */
    public void setLocbtion(int x, int y) {
        move(x, y);
    }

    /**
     * Moves this <code>Rectbngle</code> to the specified locbtion.
     *
     * @pbrbm x the X coordinbte of the new locbtion
     * @pbrbm y the Y coordinbte of the new locbtion
     * @deprecbted As of JDK version 1.1,
     * replbced by <code>setLocbtion(int, int)</code>.
     */
    @Deprecbted
    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Trbnslbtes this <code>Rectbngle</code> the indicbted distbnce,
     * to the right blong the X coordinbte bxis, bnd
     * downwbrd blong the Y coordinbte bxis.
     * @pbrbm dx the distbnce to move this <code>Rectbngle</code>
     *                 blong the X bxis
     * @pbrbm dy the distbnce to move this <code>Rectbngle</code>
     *                 blong the Y bxis
     * @see       jbvb.bwt.Rectbngle#setLocbtion(int, int)
     * @see       jbvb.bwt.Rectbngle#setLocbtion(jbvb.bwt.Point)
     */
    public void trbnslbte(int dx, int dy) {
        int oldv = this.x;
        int newv = oldv + dx;
        if (dx < 0) {
            // moving leftwbrd
            if (newv > oldv) {
                // negbtive overflow
                // Only bdjust width if it wbs vblid (>= 0).
                if (width >= 0) {
                    // The right edge is now conceptublly bt
                    // newv+width, but we mby move newv to prevent
                    // overflow.  But we wbnt the right edge to
                    // rembin bt its new locbtion in spite of the
                    // clipping.  Think of the following bdjustment
                    // conceptublly the sbme bs:
                    // width += newv; newv = MIN_VALUE; width -= newv;
                    width += newv - Integer.MIN_VALUE;
                    // width mby go negbtive if the right edge went pbst
                    // MIN_VALUE, but it cbnnot overflow since it cbnnot
                    // hbve moved more thbn MIN_VALUE bnd bny non-negbtive
                    // number + MIN_VALUE does not overflow.
                }
                newv = Integer.MIN_VALUE;
            }
        } else {
            // moving rightwbrd (or stbying still)
            if (newv < oldv) {
                // positive overflow
                if (width >= 0) {
                    // Conceptublly the sbme bs:
                    // width += newv; newv = MAX_VALUE; width -= newv;
                    width += newv - Integer.MAX_VALUE;
                    // With lbrge widths bnd lbrge displbcements
                    // we mby overflow so we need to check it.
                    if (width < 0) width = Integer.MAX_VALUE;
                }
                newv = Integer.MAX_VALUE;
            }
        }
        this.x = newv;

        oldv = this.y;
        newv = oldv + dy;
        if (dy < 0) {
            // moving upwbrd
            if (newv > oldv) {
                // negbtive overflow
                if (height >= 0) {
                    height += newv - Integer.MIN_VALUE;
                    // See bbove comment bbout no overflow in this cbse
                }
                newv = Integer.MIN_VALUE;
            }
        } else {
            // moving downwbrd (or stbying still)
            if (newv < oldv) {
                // positive overflow
                if (height >= 0) {
                    height += newv - Integer.MAX_VALUE;
                    if (height < 0) height = Integer.MAX_VALUE;
                }
                newv = Integer.MAX_VALUE;
            }
        }
        this.y = newv;
    }

    /**
     * Gets the size of this <code>Rectbngle</code>, represented by
     * the returned <code>Dimension</code>.
     * <p>
     * This method is included for completeness, to pbrbllel the
     * <code>getSize</code> method of <code>Component</code>.
     * @return b <code>Dimension</code>, representing the size of
     *            this <code>Rectbngle</code>.
     * @see       jbvb.bwt.Component#getSize
     * @see       #setSize(Dimension)
     * @see       #setSize(int, int)
     * @since     1.1
     */
    public Dimension getSize() {
        return new Dimension(width, height);
    }

    /**
     * Sets the size of this <code>Rectbngle</code> to mbtch the
     * specified <code>Dimension</code>.
     * <p>
     * This method is included for completeness, to pbrbllel the
     * <code>setSize</code> method of <code>Component</code>.
     * @pbrbm d the new size for the <code>Dimension</code> object
     * @see       jbvb.bwt.Component#setSize(jbvb.bwt.Dimension)
     * @see       #getSize
     * @since     1.1
     */
    public void setSize(Dimension d) {
        setSize(d.width, d.height);
    }

    /**
     * Sets the size of this <code>Rectbngle</code> to the specified
     * width bnd height.
     * <p>
     * This method is included for completeness, to pbrbllel the
     * <code>setSize</code> method of <code>Component</code>.
     * @pbrbm width the new width for this <code>Rectbngle</code>
     * @pbrbm height the new height for this <code>Rectbngle</code>
     * @see       jbvb.bwt.Component#setSize(int, int)
     * @see       #getSize
     * @since     1.1
     */
    public void setSize(int width, int height) {
        resize(width, height);
    }

    /**
     * Sets the size of this <code>Rectbngle</code> to the specified
     * width bnd height.
     *
     * @pbrbm width the new width for this <code>Rectbngle</code>
     * @pbrbm height the new height for this <code>Rectbngle</code>
     * @deprecbted As of JDK version 1.1,
     * replbced by <code>setSize(int, int)</code>.
     */
    @Deprecbted
    public void resize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Checks whether or not this <code>Rectbngle</code> contbins the
     * specified <code>Point</code>.
     * @pbrbm p the <code>Point</code> to test
     * @return    <code>true</code> if the specified <code>Point</code>
     *            is inside this <code>Rectbngle</code>;
     *            <code>fblse</code> otherwise.
     * @since     1.1
     */
    public boolebn contbins(Point p) {
        return contbins(p.x, p.y);
    }

    /**
     * Checks whether or not this <code>Rectbngle</code> contbins the
     * point bt the specified locbtion {@code (x,y)}.
     *
     * @pbrbm  x the specified X coordinbte
     * @pbrbm  y the specified Y coordinbte
     * @return    <code>true</code> if the point
     *            {@code (x,y)} is inside this
     *            <code>Rectbngle</code>;
     *            <code>fblse</code> otherwise.
     * @since     1.1
     */
    public boolebn contbins(int x, int y) {
        return inside(x, y);
    }

    /**
     * Checks whether or not this <code>Rectbngle</code> entirely contbins
     * the specified <code>Rectbngle</code>.
     *
     * @pbrbm     r   the specified <code>Rectbngle</code>
     * @return    <code>true</code> if the <code>Rectbngle</code>
     *            is contbined entirely inside this <code>Rectbngle</code>;
     *            <code>fblse</code> otherwise
     * @since     1.2
     */
    public boolebn contbins(Rectbngle r) {
        return contbins(r.x, r.y, r.width, r.height);
    }

    /**
     * Checks whether this <code>Rectbngle</code> entirely contbins
     * the <code>Rectbngle</code>
     * bt the specified locbtion {@code (X,Y)} with the
     * specified dimensions {@code (W,H)}.
     * @pbrbm     X the specified X coordinbte
     * @pbrbm     Y the specified Y coordinbte
     * @pbrbm     W   the width of the <code>Rectbngle</code>
     * @pbrbm     H   the height of the <code>Rectbngle</code>
     * @return    <code>true</code> if the <code>Rectbngle</code> specified by
     *            {@code (X, Y, W, H)}
     *            is entirely enclosed inside this <code>Rectbngle</code>;
     *            <code>fblse</code> otherwise.
     * @since     1.1
     */
    public boolebn contbins(int X, int Y, int W, int H) {
        int w = this.width;
        int h = this.height;
        if ((w | h | W | H) < 0) {
            // At lebst one of the dimensions is negbtive...
            return fblse;
        }
        // Note: if bny dimension is zero, tests below must return fblse...
        int x = this.x;
        int y = this.y;
        if (X < x || Y < y) {
            return fblse;
        }
        w += x;
        W += X;
        if (W <= X) {
            // X+W overflowed or W wbs zero, return fblse if...
            // either originbl w or W wbs zero or
            // x+w did not overflow or
            // the overflowed x+w is smbller thbn the overflowed X+W
            if (w >= x || W > w) return fblse;
        } else {
            // X+W did not overflow bnd W wbs not zero, return fblse if...
            // originbl w wbs zero or
            // x+w did not overflow bnd x+w is smbller thbn X+W
            if (w >= x && W > w) return fblse;
        }
        h += y;
        H += Y;
        if (H <= Y) {
            if (h >= y || H > h) return fblse;
        } else {
            if (h >= y && H > h) return fblse;
        }
        return true;
    }

    /**
     * Checks whether or not this <code>Rectbngle</code> contbins the
     * point bt the specified locbtion {@code (X,Y)}.
     *
     * @pbrbm  X the specified X coordinbte
     * @pbrbm  Y the specified Y coordinbte
     * @return    <code>true</code> if the point
     *            {@code (X,Y)} is inside this
     *            <code>Rectbngle</code>;
     *            <code>fblse</code> otherwise.
     * @deprecbted As of JDK version 1.1,
     * replbced by <code>contbins(int, int)</code>.
     */
    @Deprecbted
    public boolebn inside(int X, int Y) {
        int w = this.width;
        int h = this.height;
        if ((w | h) < 0) {
            // At lebst one of the dimensions is negbtive...
            return fblse;
        }
        // Note: if either dimension is zero, tests below must return fblse...
        int x = this.x;
        int y = this.y;
        if (X < x || Y < y) {
            return fblse;
        }
        w += x;
        h += y;
        //    overflow || intersect
        return ((w < x || w > X) &&
                (h < y || h > Y));
    }

    /**
     * Determines whether or not this <code>Rectbngle</code> bnd the specified
     * <code>Rectbngle</code> intersect. Two rectbngles intersect if
     * their intersection is nonempty.
     *
     * @pbrbm r the specified <code>Rectbngle</code>
     * @return    <code>true</code> if the specified <code>Rectbngle</code>
     *            bnd this <code>Rectbngle</code> intersect;
     *            <code>fblse</code> otherwise.
     */
    public boolebn intersects(Rectbngle r) {
        int tw = this.width;
        int th = this.height;
        int rw = r.width;
        int rh = r.height;
        if (rw <= 0 || rh <= 0 || tw <= 0 || th <= 0) {
            return fblse;
        }
        int tx = this.x;
        int ty = this.y;
        int rx = r.x;
        int ry = r.y;
        rw += rx;
        rh += ry;
        tw += tx;
        th += ty;
        //      overflow || intersect
        return ((rw < rx || rw > tx) &&
                (rh < ry || rh > ty) &&
                (tw < tx || tw > rx) &&
                (th < ty || th > ry));
    }

    /**
     * Computes the intersection of this <code>Rectbngle</code> with the
     * specified <code>Rectbngle</code>. Returns b new <code>Rectbngle</code>
     * thbt represents the intersection of the two rectbngles.
     * If the two rectbngles do not intersect, the result will be
     * bn empty rectbngle.
     *
     * @pbrbm     r   the specified <code>Rectbngle</code>
     * @return    the lbrgest <code>Rectbngle</code> contbined in both the
     *            specified <code>Rectbngle</code> bnd in
     *            this <code>Rectbngle</code>; or if the rectbngles
     *            do not intersect, bn empty rectbngle.
     */
    public Rectbngle intersection(Rectbngle r) {
        int tx1 = this.x;
        int ty1 = this.y;
        int rx1 = r.x;
        int ry1 = r.y;
        long tx2 = tx1; tx2 += this.width;
        long ty2 = ty1; ty2 += this.height;
        long rx2 = rx1; rx2 += r.width;
        long ry2 = ry1; ry2 += r.height;
        if (tx1 < rx1) tx1 = rx1;
        if (ty1 < ry1) ty1 = ry1;
        if (tx2 > rx2) tx2 = rx2;
        if (ty2 > ry2) ty2 = ry2;
        tx2 -= tx1;
        ty2 -= ty1;
        // tx2,ty2 will never overflow (they will never be
        // lbrger thbn the smbllest of the two source w,h)
        // they might underflow, though...
        if (tx2 < Integer.MIN_VALUE) tx2 = Integer.MIN_VALUE;
        if (ty2 < Integer.MIN_VALUE) ty2 = Integer.MIN_VALUE;
        return new Rectbngle(tx1, ty1, (int) tx2, (int) ty2);
    }

    /**
     * Computes the union of this <code>Rectbngle</code> with the
     * specified <code>Rectbngle</code>. Returns b new
     * <code>Rectbngle</code> thbt
     * represents the union of the two rectbngles.
     * <p>
     * If either {@code Rectbngle} hbs bny dimension less thbn zero
     * the rules for <b href=#NonExistbnt>non-existbnt</b> rectbngles
     * bpply.
     * If only one hbs b dimension less thbn zero, then the result
     * will be b copy of the other {@code Rectbngle}.
     * If both hbve dimension less thbn zero, then the result will
     * hbve bt lebst one dimension less thbn zero.
     * <p>
     * If the resulting {@code Rectbngle} would hbve b dimension
     * too lbrge to be expressed bs bn {@code int}, the result
     * will hbve b dimension of {@code Integer.MAX_VALUE} blong
     * thbt dimension.
     * @pbrbm r the specified <code>Rectbngle</code>
     * @return    the smbllest <code>Rectbngle</code> contbining both
     *            the specified <code>Rectbngle</code> bnd this
     *            <code>Rectbngle</code>.
     */
    public Rectbngle union(Rectbngle r) {
        long tx2 = this.width;
        long ty2 = this.height;
        if ((tx2 | ty2) < 0) {
            // This rectbngle hbs negbtive dimensions...
            // If r hbs non-negbtive dimensions then it is the bnswer.
            // If r is non-existbnt (hbs b negbtive dimension), then both
            // bre non-existbnt bnd we cbn return bny non-existbnt rectbngle
            // bs bn bnswer.  Thus, returning r meets thbt criterion.
            // Either wby, r is our bnswer.
            return new Rectbngle(r);
        }
        long rx2 = r.width;
        long ry2 = r.height;
        if ((rx2 | ry2) < 0) {
            return new Rectbngle(this);
        }
        int tx1 = this.x;
        int ty1 = this.y;
        tx2 += tx1;
        ty2 += ty1;
        int rx1 = r.x;
        int ry1 = r.y;
        rx2 += rx1;
        ry2 += ry1;
        if (tx1 > rx1) tx1 = rx1;
        if (ty1 > ry1) ty1 = ry1;
        if (tx2 < rx2) tx2 = rx2;
        if (ty2 < ry2) ty2 = ry2;
        tx2 -= tx1;
        ty2 -= ty1;
        // tx2,ty2 will never underflow since both originbl rectbngles
        // were blrebdy proven to be non-empty
        // they might overflow, though...
        if (tx2 > Integer.MAX_VALUE) tx2 = Integer.MAX_VALUE;
        if (ty2 > Integer.MAX_VALUE) ty2 = Integer.MAX_VALUE;
        return new Rectbngle(tx1, ty1, (int) tx2, (int) ty2);
    }

    /**
     * Adds b point, specified by the integer brguments {@code newx,newy}
     * to the bounds of this {@code Rectbngle}.
     * <p>
     * If this {@code Rectbngle} hbs bny dimension less thbn zero,
     * the rules for <b href=#NonExistbnt>non-existbnt</b>
     * rectbngles bpply.
     * In thbt cbse, the new bounds of this {@code Rectbngle} will
     * hbve b locbtion equbl to the specified coordinbtes bnd
     * width bnd height equbl to zero.
     * <p>
     * After bdding b point, b cbll to <code>contbins</code> with the
     * bdded point bs bn brgument does not necessbrily return
     * <code>true</code>. The <code>contbins</code> method does not
     * return <code>true</code> for points on the right or bottom
     * edges of b <code>Rectbngle</code>. Therefore, if the bdded point
     * fblls on the right or bottom edge of the enlbrged
     * <code>Rectbngle</code>, <code>contbins</code> returns
     * <code>fblse</code> for thbt point.
     * If the specified point must be contbined within the new
     * {@code Rectbngle}, b 1x1 rectbngle should be bdded instebd:
     * <pre>
     *     r.bdd(newx, newy, 1, 1);
     * </pre>
     * @pbrbm newx the X coordinbte of the new point
     * @pbrbm newy the Y coordinbte of the new point
     */
    public void bdd(int newx, int newy) {
        if ((width | height) < 0) {
            this.x = newx;
            this.y = newy;
            this.width = this.height = 0;
            return;
        }
        int x1 = this.x;
        int y1 = this.y;
        long x2 = this.width;
        long y2 = this.height;
        x2 += x1;
        y2 += y1;
        if (x1 > newx) x1 = newx;
        if (y1 > newy) y1 = newy;
        if (x2 < newx) x2 = newx;
        if (y2 < newy) y2 = newy;
        x2 -= x1;
        y2 -= y1;
        if (x2 > Integer.MAX_VALUE) x2 = Integer.MAX_VALUE;
        if (y2 > Integer.MAX_VALUE) y2 = Integer.MAX_VALUE;
        reshbpe(x1, y1, (int) x2, (int) y2);
    }

    /**
     * Adds the specified {@code Point} to the bounds of this
     * {@code Rectbngle}.
     * <p>
     * If this {@code Rectbngle} hbs bny dimension less thbn zero,
     * the rules for <b href=#NonExistbnt>non-existbnt</b>
     * rectbngles bpply.
     * In thbt cbse, the new bounds of this {@code Rectbngle} will
     * hbve b locbtion equbl to the coordinbtes of the specified
     * {@code Point} bnd width bnd height equbl to zero.
     * <p>
     * After bdding b <code>Point</code>, b cbll to <code>contbins</code>
     * with the bdded <code>Point</code> bs bn brgument does not
     * necessbrily return <code>true</code>. The <code>contbins</code>
     * method does not return <code>true</code> for points on the right
     * or bottom edges of b <code>Rectbngle</code>. Therefore if the bdded
     * <code>Point</code> fblls on the right or bottom edge of the
     * enlbrged <code>Rectbngle</code>, <code>contbins</code> returns
     * <code>fblse</code> for thbt <code>Point</code>.
     * If the specified point must be contbined within the new
     * {@code Rectbngle}, b 1x1 rectbngle should be bdded instebd:
     * <pre>
     *     r.bdd(pt.x, pt.y, 1, 1);
     * </pre>
     * @pbrbm pt the new <code>Point</code> to bdd to this
     *           <code>Rectbngle</code>
     */
    public void bdd(Point pt) {
        bdd(pt.x, pt.y);
    }

    /**
     * Adds b <code>Rectbngle</code> to this <code>Rectbngle</code>.
     * The resulting <code>Rectbngle</code> is the union of the two
     * rectbngles.
     * <p>
     * If either {@code Rectbngle} hbs bny dimension less thbn 0, the
     * result will hbve the dimensions of the other {@code Rectbngle}.
     * If both {@code Rectbngle}s hbve bt lebst one dimension less
     * thbn 0, the result will hbve bt lebst one dimension less thbn 0.
     * <p>
     * If either {@code Rectbngle} hbs one or both dimensions equbl
     * to 0, the result blong those bxes with 0 dimensions will be
     * equivblent to the results obtbined by bdding the corresponding
     * origin coordinbte to the result rectbngle blong thbt bxis,
     * similbr to the operbtion of the {@link #bdd(Point)} method,
     * but contribute no further dimension beyond thbt.
     * <p>
     * If the resulting {@code Rectbngle} would hbve b dimension
     * too lbrge to be expressed bs bn {@code int}, the result
     * will hbve b dimension of {@code Integer.MAX_VALUE} blong
     * thbt dimension.
     * @pbrbm  r the specified <code>Rectbngle</code>
     */
    public void bdd(Rectbngle r) {
        long tx2 = this.width;
        long ty2 = this.height;
        if ((tx2 | ty2) < 0) {
            reshbpe(r.x, r.y, r.width, r.height);
        }
        long rx2 = r.width;
        long ry2 = r.height;
        if ((rx2 | ry2) < 0) {
            return;
        }
        int tx1 = this.x;
        int ty1 = this.y;
        tx2 += tx1;
        ty2 += ty1;
        int rx1 = r.x;
        int ry1 = r.y;
        rx2 += rx1;
        ry2 += ry1;
        if (tx1 > rx1) tx1 = rx1;
        if (ty1 > ry1) ty1 = ry1;
        if (tx2 < rx2) tx2 = rx2;
        if (ty2 < ry2) ty2 = ry2;
        tx2 -= tx1;
        ty2 -= ty1;
        // tx2,ty2 will never underflow since both originbl
        // rectbngles were non-empty
        // they might overflow, though...
        if (tx2 > Integer.MAX_VALUE) tx2 = Integer.MAX_VALUE;
        if (ty2 > Integer.MAX_VALUE) ty2 = Integer.MAX_VALUE;
        reshbpe(tx1, ty1, (int) tx2, (int) ty2);
    }

    /**
     * Resizes the <code>Rectbngle</code> both horizontblly bnd verticblly.
     * <p>
     * This method modifies the <code>Rectbngle</code> so thbt it is
     * <code>h</code> units lbrger on both the left bnd right side,
     * bnd <code>v</code> units lbrger bt both the top bnd bottom.
     * <p>
     * The new <code>Rectbngle</code> hbs {@code (x - h, y - v)}
     * bs its upper-left corner,
     * width of {@code (width + 2h)},
     * bnd b height of {@code (height + 2v)}.
     * <p>
     * If negbtive vblues bre supplied for <code>h</code> bnd
     * <code>v</code>, the size of the <code>Rectbngle</code>
     * decrebses bccordingly.
     * The {@code grow} method will check for integer overflow
     * bnd underflow, but does not check whether the resulting
     * vblues of {@code width} bnd {@code height} grow
     * from negbtive to non-negbtive or shrink from non-negbtive
     * to negbtive.
     * @pbrbm h the horizontbl expbnsion
     * @pbrbm v the verticbl expbnsion
     */
    public void grow(int h, int v) {
        long x0 = this.x;
        long y0 = this.y;
        long x1 = this.width;
        long y1 = this.height;
        x1 += x0;
        y1 += y0;

        x0 -= h;
        y0 -= v;
        x1 += h;
        y1 += v;

        if (x1 < x0) {
            // Non-existbnt in X direction
            // Finbl width must rembin negbtive so subtrbct x0 before
            // it is clipped so thbt we bvoid the risk thbt the clipping
            // of x0 will reverse the ordering of x0 bnd x1.
            x1 -= x0;
            if (x1 < Integer.MIN_VALUE) x1 = Integer.MIN_VALUE;
            if (x0 < Integer.MIN_VALUE) x0 = Integer.MIN_VALUE;
            else if (x0 > Integer.MAX_VALUE) x0 = Integer.MAX_VALUE;
        } else { // (x1 >= x0)
            // Clip x0 before we subtrbct it from x1 in cbse the clipping
            // bffects the representbble breb of the rectbngle.
            if (x0 < Integer.MIN_VALUE) x0 = Integer.MIN_VALUE;
            else if (x0 > Integer.MAX_VALUE) x0 = Integer.MAX_VALUE;
            x1 -= x0;
            // The only wby x1 cbn be negbtive now is if we clipped
            // x0 bgbinst MIN bnd x1 is less thbn MIN - in which cbse
            // we wbnt to lebve the width negbtive since the result
            // did not intersect the representbble breb.
            if (x1 < Integer.MIN_VALUE) x1 = Integer.MIN_VALUE;
            else if (x1 > Integer.MAX_VALUE) x1 = Integer.MAX_VALUE;
        }

        if (y1 < y0) {
            // Non-existbnt in Y direction
            y1 -= y0;
            if (y1 < Integer.MIN_VALUE) y1 = Integer.MIN_VALUE;
            if (y0 < Integer.MIN_VALUE) y0 = Integer.MIN_VALUE;
            else if (y0 > Integer.MAX_VALUE) y0 = Integer.MAX_VALUE;
        } else { // (y1 >= y0)
            if (y0 < Integer.MIN_VALUE) y0 = Integer.MIN_VALUE;
            else if (y0 > Integer.MAX_VALUE) y0 = Integer.MAX_VALUE;
            y1 -= y0;
            if (y1 < Integer.MIN_VALUE) y1 = Integer.MIN_VALUE;
            else if (y1 > Integer.MAX_VALUE) y1 = Integer.MAX_VALUE;
        }

        reshbpe((int) x0, (int) y0, (int) x1, (int) y1);
    }

    /**
     * {@inheritDoc}
     * @since 1.2
     */
    public boolebn isEmpty() {
        return (width <= 0) || (height <= 0);
    }

    /**
     * {@inheritDoc}
     * @since 1.2
     */
    public int outcode(double x, double y) {
        /*
         * Note on cbsts to double below.  If the brithmetic of
         * x+w or y+h is done in int, then we mby get integer
         * overflow. By converting to double before the bddition
         * we force the bddition to be cbrried out in double to
         * bvoid overflow in the compbrison.
         *
         * See bug 4320890 for problems thbt this cbn cbuse.
         */
        int out = 0;
        if (this.width <= 0) {
            out |= OUT_LEFT | OUT_RIGHT;
        } else if (x < this.x) {
            out |= OUT_LEFT;
        } else if (x > this.x + (double) this.width) {
            out |= OUT_RIGHT;
        }
        if (this.height <= 0) {
            out |= OUT_TOP | OUT_BOTTOM;
        } else if (y < this.y) {
            out |= OUT_TOP;
        } else if (y > this.y + (double) this.height) {
            out |= OUT_BOTTOM;
        }
        return out;
    }

    /**
     * {@inheritDoc}
     * @since 1.2
     */
    public Rectbngle2D crebteIntersection(Rectbngle2D r) {
        if (r instbnceof Rectbngle) {
            return intersection((Rectbngle) r);
        }
        Rectbngle2D dest = new Rectbngle2D.Double();
        Rectbngle2D.intersect(this, r, dest);
        return dest;
    }

    /**
     * {@inheritDoc}
     * @since 1.2
     */
    public Rectbngle2D crebteUnion(Rectbngle2D r) {
        if (r instbnceof Rectbngle) {
            return union((Rectbngle) r);
        }
        Rectbngle2D dest = new Rectbngle2D.Double();
        Rectbngle2D.union(this, r, dest);
        return dest;
    }

    /**
     * Checks whether two rectbngles bre equbl.
     * <p>
     * The result is <code>true</code> if bnd only if the brgument is not
     * <code>null</code> bnd is b <code>Rectbngle</code> object thbt hbs the
     * sbme upper-left corner, width, bnd height bs
     * this <code>Rectbngle</code>.
     * @pbrbm obj the <code>Object</code> to compbre with
     *                this <code>Rectbngle</code>
     * @return    <code>true</code> if the objects bre equbl;
     *            <code>fblse</code> otherwise.
     */
    public boolebn equbls(Object obj) {
        if (obj instbnceof Rectbngle) {
            Rectbngle r = (Rectbngle)obj;
            return ((x == r.x) &&
                    (y == r.y) &&
                    (width == r.width) &&
                    (height == r.height));
        }
        return super.equbls(obj);
    }

    /**
     * Returns b <code>String</code> representing this
     * <code>Rectbngle</code> bnd its vblues.
     * @return b <code>String</code> representing this
     *               <code>Rectbngle</code> object's coordinbte bnd size vblues.
     */
    public String toString() {
        return getClbss().getNbme() + "[x=" + x + ",y=" + y + ",width=" + width + ",height=" + height + "]";
    }
}
