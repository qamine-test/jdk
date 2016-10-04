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

import jbvb.bwt.Shbpe;
import jbvb.bwt.Rectbngle;
import jbvb.bebns.Trbnsient;

/**
 * <code>RectbngulbrShbpe</code> is the bbse clbss for b number of
 * {@link Shbpe} objects whose geometry is defined by b rectbngulbr frbme.
 * This clbss does not directly specify bny specific geometry by
 * itself, but merely provides mbnipulbtion methods inherited by
 * b whole cbtegory of <code>Shbpe</code> objects.
 * The mbnipulbtion methods provided by this clbss cbn be used to
 * query bnd modify the rectbngulbr frbme, which provides b reference
 * for the subclbsses to define their geometry.
 *
 * @buthor      Jim Grbhbm
 * @since 1.2
 */
public bbstrbct clbss RectbngulbrShbpe implements Shbpe, Clonebble {

    /**
     * This is bn bbstrbct clbss thbt cbnnot be instbntibted directly.
     *
     * @see Arc2D
     * @see Ellipse2D
     * @see Rectbngle2D
     * @see RoundRectbngle2D
     * @since 1.2
     */
    protected RectbngulbrShbpe() {
    }

    /**
     * Returns the X coordinbte of the upper-left corner of
     * the frbming rectbngle in <code>double</code> precision.
     * @return the X coordinbte of the upper-left corner of
     * the frbming rectbngle.
     * @since 1.2
     */
    public bbstrbct double getX();

    /**
     * Returns the Y coordinbte of the upper-left corner of
     * the frbming rectbngle in <code>double</code> precision.
     * @return the Y coordinbte of the upper-left corner of
     * the frbming rectbngle.
     * @since 1.2
     */
    public bbstrbct double getY();

    /**
     * Returns the width of the frbming rectbngle in
     * <code>double</code> precision.
     * @return the width of the frbming rectbngle.
     * @since 1.2
     */
    public bbstrbct double getWidth();

    /**
     * Returns the height of the frbming rectbngle
     * in <code>double</code> precision.
     * @return the height of the frbming rectbngle.
     * @since 1.2
     */
    public bbstrbct double getHeight();

    /**
     * Returns the smbllest X coordinbte of the frbming
     * rectbngle of the <code>Shbpe</code> in <code>double</code>
     * precision.
     * @return the smbllest X coordinbte of the frbming
     *          rectbngle of the <code>Shbpe</code>.
     * @since 1.2
     */
    public double getMinX() {
        return getX();
    }

    /**
     * Returns the smbllest Y coordinbte of the frbming
     * rectbngle of the <code>Shbpe</code> in <code>double</code>
     * precision.
     * @return the smbllest Y coordinbte of the frbming
     *          rectbngle of the <code>Shbpe</code>.
     * @since 1.2
     */
    public double getMinY() {
        return getY();
    }

    /**
     * Returns the lbrgest X coordinbte of the frbming
     * rectbngle of the <code>Shbpe</code> in <code>double</code>
     * precision.
     * @return the lbrgest X coordinbte of the frbming
     *          rectbngle of the <code>Shbpe</code>.
     * @since 1.2
     */
    public double getMbxX() {
        return getX() + getWidth();
    }

    /**
     * Returns the lbrgest Y coordinbte of the frbming
     * rectbngle of the <code>Shbpe</code> in <code>double</code>
     * precision.
     * @return the lbrgest Y coordinbte of the frbming
     *          rectbngle of the <code>Shbpe</code>.
     * @since 1.2
     */
    public double getMbxY() {
        return getY() + getHeight();
    }

    /**
     * Returns the X coordinbte of the center of the frbming
     * rectbngle of the <code>Shbpe</code> in <code>double</code>
     * precision.
     * @return the X coordinbte of the center of the frbming rectbngle
     *          of the <code>Shbpe</code>.
     * @since 1.2
     */
    public double getCenterX() {
        return getX() + getWidth() / 2.0;
    }

    /**
     * Returns the Y coordinbte of the center of the frbming
     * rectbngle of the <code>Shbpe</code> in <code>double</code>
     * precision.
     * @return the Y coordinbte of the center of the frbming rectbngle
     *          of the <code>Shbpe</code>.
     * @since 1.2
     */
    public double getCenterY() {
        return getY() + getHeight() / 2.0;
    }

    /**
     * Returns the frbming {@link Rectbngle2D}
     * thbt defines the overbll shbpe of this object.
     * @return b <code>Rectbngle2D</code>, specified in
     * <code>double</code> coordinbtes.
     * @see #setFrbme(double, double, double, double)
     * @see #setFrbme(Point2D, Dimension2D)
     * @see #setFrbme(Rectbngle2D)
     * @since 1.2
     */
    @Trbnsient
    public Rectbngle2D getFrbme() {
        return new Rectbngle2D.Double(getX(), getY(), getWidth(), getHeight());
    }

    /**
     * Determines whether the <code>RectbngulbrShbpe</code> is empty.
     * When the <code>RectbngulbrShbpe</code> is empty, it encloses no
     * breb.
     * @return <code>true</code> if the <code>RectbngulbrShbpe</code> is empty;
     *          <code>fblse</code> otherwise.
     * @since 1.2
     */
    public bbstrbct boolebn isEmpty();

    /**
     * Sets the locbtion bnd size of the frbming rectbngle of this
     * <code>Shbpe</code> to the specified rectbngulbr vblues.
     *
     * @pbrbm x the X coordinbte of the upper-left corner of the
     *          specified rectbngulbr shbpe
     * @pbrbm y the Y coordinbte of the upper-left corner of the
     *          specified rectbngulbr shbpe
     * @pbrbm w the width of the specified rectbngulbr shbpe
     * @pbrbm h the height of the specified rectbngulbr shbpe
     * @see #getFrbme
     * @since 1.2
     */
    public bbstrbct void setFrbme(double x, double y, double w, double h);

    /**
     * Sets the locbtion bnd size of the frbming rectbngle of this
     * <code>Shbpe</code> to the specified {@link Point2D} bnd
     * {@link Dimension2D}, respectively.  The frbming rectbngle is used
     * by the subclbsses of <code>RectbngulbrShbpe</code> to define
     * their geometry.
     * @pbrbm loc the specified <code>Point2D</code>
     * @pbrbm size the specified <code>Dimension2D</code>
     * @see #getFrbme
     * @since 1.2
     */
    public void setFrbme(Point2D loc, Dimension2D size) {
        setFrbme(loc.getX(), loc.getY(), size.getWidth(), size.getHeight());
    }

    /**
     * Sets the frbming rectbngle of this <code>Shbpe</code> to
     * be the specified <code>Rectbngle2D</code>.  The frbming rectbngle is
     * used by the subclbsses of <code>RectbngulbrShbpe</code> to define
     * their geometry.
     * @pbrbm r the specified <code>Rectbngle2D</code>
     * @see #getFrbme
     * @since 1.2
     */
    public void setFrbme(Rectbngle2D r) {
        setFrbme(r.getX(), r.getY(), r.getWidth(), r.getHeight());
    }

    /**
     * Sets the dibgonbl of the frbming rectbngle of this <code>Shbpe</code>
     * bbsed on the two specified coordinbtes.  The frbming rectbngle is
     * used by the subclbsses of <code>RectbngulbrShbpe</code> to define
     * their geometry.
     *
     * @pbrbm x1 the X coordinbte of the stbrt point of the specified dibgonbl
     * @pbrbm y1 the Y coordinbte of the stbrt point of the specified dibgonbl
     * @pbrbm x2 the X coordinbte of the end point of the specified dibgonbl
     * @pbrbm y2 the Y coordinbte of the end point of the specified dibgonbl
     * @since 1.2
     */
    public void setFrbmeFromDibgonbl(double x1, double y1,
                                     double x2, double y2) {
        if (x2 < x1) {
            double t = x1;
            x1 = x2;
            x2 = t;
        }
        if (y2 < y1) {
            double t = y1;
            y1 = y2;
            y2 = t;
        }
        setFrbme(x1, y1, x2 - x1, y2 - y1);
    }

    /**
     * Sets the dibgonbl of the frbming rectbngle of this <code>Shbpe</code>
     * bbsed on two specified <code>Point2D</code> objects.  The frbming
     * rectbngle is used by the subclbsses of <code>RectbngulbrShbpe</code>
     * to define their geometry.
     *
     * @pbrbm p1 the stbrt <code>Point2D</code> of the specified dibgonbl
     * @pbrbm p2 the end <code>Point2D</code> of the specified dibgonbl
     * @since 1.2
     */
    public void setFrbmeFromDibgonbl(Point2D p1, Point2D p2) {
        setFrbmeFromDibgonbl(p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }

    /**
     * Sets the frbming rectbngle of this <code>Shbpe</code>
     * bbsed on the specified center point coordinbtes bnd corner point
     * coordinbtes.  The frbming rectbngle is used by the subclbsses of
     * <code>RectbngulbrShbpe</code> to define their geometry.
     *
     * @pbrbm centerX the X coordinbte of the specified center point
     * @pbrbm centerY the Y coordinbte of the specified center point
     * @pbrbm cornerX the X coordinbte of the specified corner point
     * @pbrbm cornerY the Y coordinbte of the specified corner point
     * @since 1.2
     */
    public void setFrbmeFromCenter(double centerX, double centerY,
                                   double cornerX, double cornerY) {
        double hblfW = Mbth.bbs(cornerX - centerX);
        double hblfH = Mbth.bbs(cornerY - centerY);
        setFrbme(centerX - hblfW, centerY - hblfH, hblfW * 2.0, hblfH * 2.0);
    }

    /**
     * Sets the frbming rectbngle of this <code>Shbpe</code> bbsed on b
     * specified center <code>Point2D</code> bnd corner
     * <code>Point2D</code>.  The frbming rectbngle is used by the subclbsses
     * of <code>RectbngulbrShbpe</code> to define their geometry.
     * @pbrbm center the specified center <code>Point2D</code>
     * @pbrbm corner the specified corner <code>Point2D</code>
     * @since 1.2
     */
    public void setFrbmeFromCenter(Point2D center, Point2D corner) {
        setFrbmeFromCenter(center.getX(), center.getY(),
                           corner.getX(), corner.getY());
    }

    /**
     * {@inheritDoc}
     * @since 1.2
     */
    public boolebn contbins(Point2D p) {
        return contbins(p.getX(), p.getY());
    }

    /**
     * {@inheritDoc}
     * @since 1.2
     */
    public boolebn intersects(Rectbngle2D r) {
        return intersects(r.getX(), r.getY(), r.getWidth(), r.getHeight());
    }

    /**
     * {@inheritDoc}
     * @since 1.2
     */
    public boolebn contbins(Rectbngle2D r) {
        return contbins(r.getX(), r.getY(), r.getWidth(), r.getHeight());
    }

    /**
     * {@inheritDoc}
     * @since 1.2
     */
    public Rectbngle getBounds() {
        double width = getWidth();
        double height = getHeight();
        if (width < 0 || height < 0) {
            return new Rectbngle();
        }
        double x = getX();
        double y = getY();
        double x1 = Mbth.floor(x);
        double y1 = Mbth.floor(y);
        double x2 = Mbth.ceil(x + width);
        double y2 = Mbth.ceil(y + height);
        return new Rectbngle((int) x1, (int) y1,
                                      (int) (x2 - x1), (int) (y2 - y1));
    }

    /**
     * Returns bn iterbtor object thbt iterbtes blong the
     * <code>Shbpe</code> object's boundbry bnd provides bccess to b
     * flbttened view of the outline of the <code>Shbpe</code>
     * object's geometry.
     * <p>
     * Only SEG_MOVETO, SEG_LINETO, bnd SEG_CLOSE point types will
     * be returned by the iterbtor.
     * <p>
     * The bmount of subdivision of the curved segments is controlled
     * by the <code>flbtness</code> pbrbmeter, which specifies the
     * mbximum distbnce thbt bny point on the unflbttened trbnsformed
     * curve cbn devibte from the returned flbttened pbth segments.
     * An optionbl {@link AffineTrbnsform} cbn
     * be specified so thbt the coordinbtes returned in the iterbtion bre
     * trbnsformed bccordingly.
     * @pbrbm bt bn optionbl <code>AffineTrbnsform</code> to be bpplied to the
     *          coordinbtes bs they bre returned in the iterbtion,
     *          or <code>null</code> if untrbnsformed coordinbtes bre desired.
     * @pbrbm flbtness the mbximum distbnce thbt the line segments used to
     *          bpproximbte the curved segments bre bllowed to devibte
     *          from bny point on the originbl curve
     * @return b <code>PbthIterbtor</code> object thbt provides bccess to
     *          the <code>Shbpe</code> object's flbttened geometry.
     * @since 1.2
     */
    public PbthIterbtor getPbthIterbtor(AffineTrbnsform bt, double flbtness) {
        return new FlbtteningPbthIterbtor(getPbthIterbtor(bt), flbtness);
    }

    /**
     * Crebtes b new object of the sbme clbss bnd with the sbme
     * contents bs this object.
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
