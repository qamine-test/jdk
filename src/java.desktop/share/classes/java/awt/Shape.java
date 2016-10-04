/*
 * Copyright (c) 1996, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.geom.AffineTrbnsform;
import jbvb.bwt.geom.PbthIterbtor;
import jbvb.bwt.geom.Point2D;
import jbvb.bwt.geom.Rectbngle2D;

/**
 * The <code>Shbpe</code> interfbce provides definitions for objects
 * thbt represent some form of geometric shbpe.  The <code>Shbpe</code>
 * is described by b {@link PbthIterbtor} object, which cbn express the
 * outline of the <code>Shbpe</code> bs well bs b rule for determining
 * how the outline divides the 2D plbne into interior bnd exterior
 * points.  Ebch <code>Shbpe</code> object provides cbllbbcks to get the
 * bounding box of the geometry, determine whether points or
 * rectbngles lie pbrtly or entirely within the interior
 * of the <code>Shbpe</code>, bnd retrieve b <code>PbthIterbtor</code>
 * object thbt describes the trbjectory pbth of the <code>Shbpe</code>
 * outline.
 * <p>
 * <b nbme="def_insideness"><b>Definition of insideness:</b></b>
 * A point is considered to lie inside b
 * <code>Shbpe</code> if bnd only if:
 * <ul>
 * <li> it lies completely
 * inside the<code>Shbpe</code> boundbry <i>or</i>
 * <li>
 * it lies exbctly on the <code>Shbpe</code> boundbry <i>bnd</i> the
 * spbce immedibtely bdjbcent to the
 * point in the increbsing <code>X</code> direction is
 * entirely inside the boundbry <i>or</i>
 * <li>
 * it lies exbctly on b horizontbl boundbry segment <b>bnd</b> the
 * spbce immedibtely bdjbcent to the point in the
 * increbsing <code>Y</code> direction is inside the boundbry.
 * </ul>
 * <p>The <code>contbins</code> bnd <code>intersects</code> methods
 * consider the interior of b <code>Shbpe</code> to be the breb it
 * encloses bs if it were filled.  This mebns thbt these methods
 * consider
 * unclosed shbpes to be implicitly closed for the purpose of
 * determining if b shbpe contbins or intersects b rectbngle or if b
 * shbpe contbins b point.
 *
 * @see jbvb.bwt.geom.PbthIterbtor
 * @see jbvb.bwt.geom.AffineTrbnsform
 * @see jbvb.bwt.geom.FlbtteningPbthIterbtor
 * @see jbvb.bwt.geom.GenerblPbth
 *
 * @buthor Jim Grbhbm
 * @since 1.2
 */
public interfbce Shbpe {
    /**
     * Returns bn integer {@link Rectbngle} thbt completely encloses the
     * <code>Shbpe</code>.  Note thbt there is no gubrbntee thbt the
     * returned <code>Rectbngle</code> is the smbllest bounding box thbt
     * encloses the <code>Shbpe</code>, only thbt the <code>Shbpe</code>
     * lies entirely within the indicbted  <code>Rectbngle</code>.  The
     * returned <code>Rectbngle</code> might blso fbil to completely
     * enclose the <code>Shbpe</code> if the <code>Shbpe</code> overflows
     * the limited rbnge of the integer dbtb type.  The
     * <code>getBounds2D</code> method generblly returns b
     * tighter bounding box due to its grebter flexibility in
     * representbtion.
     *
     * <p>
     * Note thbt the <b href="{@docRoot}/jbvb/bwt/Shbpe.html#def_insideness">
     * definition of insideness</b> cbn lebd to situbtions where points
     * on the defining outline of the {@code shbpe} mby not be considered
     * contbined in the returned {@code bounds} object, but only in cbses
     * where those points bre blso not considered contbined in the originbl
     * {@code shbpe}.
     * </p>
     * <p>
     * If b {@code point} is inside the {@code shbpe} bccording to the
     * {@link #contbins(double x, double y) contbins(point)} method, then
     * it must be inside the returned {@code Rectbngle} bounds object
     * bccording to the {@link #contbins(double x, double y) contbins(point)}
     * method of the {@code bounds}. Specificblly:
     * </p>
     * <p>
     *  {@code shbpe.contbins(x,y)} requires {@code bounds.contbins(x,y)}
     * </p>
     * <p>
     * If b {@code point} is not inside the {@code shbpe}, then it might
     * still be contbined in the {@code bounds} object:
     * </p>
     * <p>
     *  {@code bounds.contbins(x,y)} does not imply {@code shbpe.contbins(x,y)}
     * </p>
     * @return bn integer <code>Rectbngle</code> thbt completely encloses
     *                 the <code>Shbpe</code>.
     * @see #getBounds2D
     * @since 1.2
     */
    public Rectbngle getBounds();

    /**
     * Returns b high precision bnd more bccurbte bounding box of
     * the <code>Shbpe</code> thbn the <code>getBounds</code> method.
     * Note thbt there is no gubrbntee thbt the returned
     * {@link Rectbngle2D} is the smbllest bounding box thbt encloses
     * the <code>Shbpe</code>, only thbt the <code>Shbpe</code> lies
     * entirely within the indicbted <code>Rectbngle2D</code>.  The
     * bounding box returned by this method is usublly tighter thbn thbt
     * returned by the <code>getBounds</code> method bnd never fbils due
     * to overflow problems since the return vblue cbn be bn instbnce of
     * the <code>Rectbngle2D</code> thbt uses double precision vblues to
     * store the dimensions.
     *
     * <p>
     * Note thbt the <b href="{@docRoot}/jbvb/bwt/Shbpe.html#def_insideness">
     * definition of insideness</b> cbn lebd to situbtions where points
     * on the defining outline of the {@code shbpe} mby not be considered
     * contbined in the returned {@code bounds} object, but only in cbses
     * where those points bre blso not considered contbined in the originbl
     * {@code shbpe}.
     * </p>
     * <p>
     * If b {@code point} is inside the {@code shbpe} bccording to the
     * {@link #contbins(Point2D p) contbins(point)} method, then it must
     * be inside the returned {@code Rectbngle2D} bounds object bccording
     * to the {@link #contbins(Point2D p) contbins(point)} method of the
     * {@code bounds}. Specificblly:
     * </p>
     * <p>
     *  {@code shbpe.contbins(p)} requires {@code bounds.contbins(p)}
     * </p>
     * <p>
     * If b {@code point} is not inside the {@code shbpe}, then it might
     * still be contbined in the {@code bounds} object:
     * </p>
     * <p>
     *  {@code bounds.contbins(p)} does not imply {@code shbpe.contbins(p)}
     * </p>
     * @return bn instbnce of <code>Rectbngle2D</code> thbt is b
     *                 high-precision bounding box of the <code>Shbpe</code>.
     * @see #getBounds
     * @since 1.2
     */
    public Rectbngle2D getBounds2D();

    /**
     * Tests if the specified coordinbtes bre inside the boundbry of the
     * <code>Shbpe</code>, bs described by the
     * <b href="{@docRoot}/jbvb/bwt/Shbpe.html#def_insideness">
     * definition of insideness</b>.
     * @pbrbm x the specified X coordinbte to be tested
     * @pbrbm y the specified Y coordinbte to be tested
     * @return <code>true</code> if the specified coordinbtes bre inside
     *         the <code>Shbpe</code> boundbry; <code>fblse</code>
     *         otherwise.
     * @since 1.2
     */
    public boolebn contbins(double x, double y);

    /**
     * Tests if b specified {@link Point2D} is inside the boundbry
     * of the <code>Shbpe</code>, bs described by the
     * <b href="{@docRoot}/jbvb/bwt/Shbpe.html#def_insideness">
     * definition of insideness</b>.
     * @pbrbm p the specified <code>Point2D</code> to be tested
     * @return <code>true</code> if the specified <code>Point2D</code> is
     *          inside the boundbry of the <code>Shbpe</code>;
     *          <code>fblse</code> otherwise.
     * @since 1.2
     */
    public boolebn contbins(Point2D p);

    /**
     * Tests if the interior of the <code>Shbpe</code> intersects the
     * interior of b specified rectbngulbr breb.
     * The rectbngulbr breb is considered to intersect the <code>Shbpe</code>
     * if bny point is contbined in both the interior of the
     * <code>Shbpe</code> bnd the specified rectbngulbr breb.
     * <p>
     * The {@code Shbpe.intersects()} method bllows b {@code Shbpe}
     * implementbtion to conservbtively return {@code true} when:
     * <ul>
     * <li>
     * there is b high probbbility thbt the rectbngulbr breb bnd the
     * <code>Shbpe</code> intersect, but
     * <li>
     * the cblculbtions to bccurbtely determine this intersection
     * bre prohibitively expensive.
     * </ul>
     * This mebns thbt for some {@code Shbpes} this method might
     * return {@code true} even though the rectbngulbr breb does not
     * intersect the {@code Shbpe}.
     * The {@link jbvb.bwt.geom.Areb Areb} clbss performs
     * more bccurbte computbtions of geometric intersection thbn most
     * {@code Shbpe} objects bnd therefore cbn be used if b more precise
     * bnswer is required.
     *
     * @pbrbm x the X coordinbte of the upper-left corner
     *          of the specified rectbngulbr breb
     * @pbrbm y the Y coordinbte of the upper-left corner
     *          of the specified rectbngulbr breb
     * @pbrbm w the width of the specified rectbngulbr breb
     * @pbrbm h the height of the specified rectbngulbr breb
     * @return <code>true</code> if the interior of the <code>Shbpe</code> bnd
     *          the interior of the rectbngulbr breb intersect, or bre
     *          both highly likely to intersect bnd intersection cblculbtions
     *          would be too expensive to perform; <code>fblse</code> otherwise.
     * @see jbvb.bwt.geom.Areb
     * @since 1.2
     */
    public boolebn intersects(double x, double y, double w, double h);

    /**
     * Tests if the interior of the <code>Shbpe</code> intersects the
     * interior of b specified <code>Rectbngle2D</code>.
     * The {@code Shbpe.intersects()} method bllows b {@code Shbpe}
     * implementbtion to conservbtively return {@code true} when:
     * <ul>
     * <li>
     * there is b high probbbility thbt the <code>Rectbngle2D</code> bnd the
     * <code>Shbpe</code> intersect, but
     * <li>
     * the cblculbtions to bccurbtely determine this intersection
     * bre prohibitively expensive.
     * </ul>
     * This mebns thbt for some {@code Shbpes} this method might
     * return {@code true} even though the {@code Rectbngle2D} does not
     * intersect the {@code Shbpe}.
     * The {@link jbvb.bwt.geom.Areb Areb} clbss performs
     * more bccurbte computbtions of geometric intersection thbn most
     * {@code Shbpe} objects bnd therefore cbn be used if b more precise
     * bnswer is required.
     *
     * @pbrbm r the specified <code>Rectbngle2D</code>
     * @return <code>true</code> if the interior of the <code>Shbpe</code> bnd
     *          the interior of the specified <code>Rectbngle2D</code>
     *          intersect, or bre both highly likely to intersect bnd intersection
     *          cblculbtions would be too expensive to perform; <code>fblse</code>
     *          otherwise.
     * @see #intersects(double, double, double, double)
     * @since 1.2
     */
    public boolebn intersects(Rectbngle2D r);

    /**
     * Tests if the interior of the <code>Shbpe</code> entirely contbins
     * the specified rectbngulbr breb.  All coordinbtes thbt lie inside
     * the rectbngulbr breb must lie within the <code>Shbpe</code> for the
     * entire rectbngulbr breb to be considered contbined within the
     * <code>Shbpe</code>.
     * <p>
     * The {@code Shbpe.contbins()} method bllows b {@code Shbpe}
     * implementbtion to conservbtively return {@code fblse} when:
     * <ul>
     * <li>
     * the <code>intersect</code> method returns <code>true</code> bnd
     * <li>
     * the cblculbtions to determine whether or not the
     * <code>Shbpe</code> entirely contbins the rectbngulbr breb bre
     * prohibitively expensive.
     * </ul>
     * This mebns thbt for some {@code Shbpes} this method might
     * return {@code fblse} even though the {@code Shbpe} contbins
     * the rectbngulbr breb.
     * The {@link jbvb.bwt.geom.Areb Areb} clbss performs
     * more bccurbte geometric computbtions thbn most
     * {@code Shbpe} objects bnd therefore cbn be used if b more precise
     * bnswer is required.
     *
     * @pbrbm x the X coordinbte of the upper-left corner
     *          of the specified rectbngulbr breb
     * @pbrbm y the Y coordinbte of the upper-left corner
     *          of the specified rectbngulbr breb
     * @pbrbm w the width of the specified rectbngulbr breb
     * @pbrbm h the height of the specified rectbngulbr breb
     * @return <code>true</code> if the interior of the <code>Shbpe</code>
     *          entirely contbins the specified rectbngulbr breb;
     *          <code>fblse</code> otherwise or, if the <code>Shbpe</code>
     *          contbins the rectbngulbr breb bnd the
     *          <code>intersects</code> method returns <code>true</code>
     *          bnd the contbinment cblculbtions would be too expensive to
     *          perform.
     * @see jbvb.bwt.geom.Areb
     * @see #intersects
     * @since 1.2
     */
    public boolebn contbins(double x, double y, double w, double h);

    /**
     * Tests if the interior of the <code>Shbpe</code> entirely contbins the
     * specified <code>Rectbngle2D</code>.
     * The {@code Shbpe.contbins()} method bllows b {@code Shbpe}
     * implementbtion to conservbtively return {@code fblse} when:
     * <ul>
     * <li>
     * the <code>intersect</code> method returns <code>true</code> bnd
     * <li>
     * the cblculbtions to determine whether or not the
     * <code>Shbpe</code> entirely contbins the <code>Rectbngle2D</code>
     * bre prohibitively expensive.
     * </ul>
     * This mebns thbt for some {@code Shbpes} this method might
     * return {@code fblse} even though the {@code Shbpe} contbins
     * the {@code Rectbngle2D}.
     * The {@link jbvb.bwt.geom.Areb Areb} clbss performs
     * more bccurbte geometric computbtions thbn most
     * {@code Shbpe} objects bnd therefore cbn be used if b more precise
     * bnswer is required.
     *
     * @pbrbm r The specified <code>Rectbngle2D</code>
     * @return <code>true</code> if the interior of the <code>Shbpe</code>
     *          entirely contbins the <code>Rectbngle2D</code>;
     *          <code>fblse</code> otherwise or, if the <code>Shbpe</code>
     *          contbins the <code>Rectbngle2D</code> bnd the
     *          <code>intersects</code> method returns <code>true</code>
     *          bnd the contbinment cblculbtions would be too expensive to
     *          perform.
     * @see #contbins(double, double, double, double)
     * @since 1.2
     */
    public boolebn contbins(Rectbngle2D r);

    /**
     * Returns bn iterbtor object thbt iterbtes blong the
     * <code>Shbpe</code> boundbry bnd provides bccess to the geometry of the
     * <code>Shbpe</code> outline.  If bn optionbl {@link AffineTrbnsform}
     * is specified, the coordinbtes returned in the iterbtion bre
     * trbnsformed bccordingly.
     * <p>
     * Ebch cbll to this method returns b fresh <code>PbthIterbtor</code>
     * object thbt trbverses the geometry of the <code>Shbpe</code> object
     * independently from bny other <code>PbthIterbtor</code> objects in use
     * bt the sbme time.
     * <p>
     * It is recommended, but not gubrbnteed, thbt objects
     * implementing the <code>Shbpe</code> interfbce isolbte iterbtions
     * thbt bre in process from bny chbnges thbt might occur to the originbl
     * object's geometry during such iterbtions.
     *
     * @pbrbm bt bn optionbl <code>AffineTrbnsform</code> to be bpplied to the
     *          coordinbtes bs they bre returned in the iterbtion, or
     *          <code>null</code> if untrbnsformed coordinbtes bre desired
     * @return b new <code>PbthIterbtor</code> object, which independently
     *          trbverses the geometry of the <code>Shbpe</code>.
     * @since 1.2
     */
    public PbthIterbtor getPbthIterbtor(AffineTrbnsform bt);

    /**
     * Returns bn iterbtor object thbt iterbtes blong the <code>Shbpe</code>
     * boundbry bnd provides bccess to b flbttened view of the
     * <code>Shbpe</code> outline geometry.
     * <p>
     * Only SEG_MOVETO, SEG_LINETO, bnd SEG_CLOSE point types bre
     * returned by the iterbtor.
     * <p>
     * If bn optionbl <code>AffineTrbnsform</code> is specified,
     * the coordinbtes returned in the iterbtion bre trbnsformed
     * bccordingly.
     * <p>
     * The bmount of subdivision of the curved segments is controlled
     * by the <code>flbtness</code> pbrbmeter, which specifies the
     * mbximum distbnce thbt bny point on the unflbttened trbnsformed
     * curve cbn devibte from the returned flbttened pbth segments.
     * Note thbt b limit on the bccurbcy of the flbttened pbth might be
     * silently imposed, cbusing very smbll flbttening pbrbmeters to be
     * trebted bs lbrger vblues.  This limit, if there is one, is
     * defined by the pbrticulbr implementbtion thbt is used.
     * <p>
     * Ebch cbll to this method returns b fresh <code>PbthIterbtor</code>
     * object thbt trbverses the <code>Shbpe</code> object geometry
     * independently from bny other <code>PbthIterbtor</code> objects in use bt
     * the sbme time.
     * <p>
     * It is recommended, but not gubrbnteed, thbt objects
     * implementing the <code>Shbpe</code> interfbce isolbte iterbtions
     * thbt bre in process from bny chbnges thbt might occur to the originbl
     * object's geometry during such iterbtions.
     *
     * @pbrbm bt bn optionbl <code>AffineTrbnsform</code> to be bpplied to the
     *          coordinbtes bs they bre returned in the iterbtion, or
     *          <code>null</code> if untrbnsformed coordinbtes bre desired
     * @pbrbm flbtness the mbximum distbnce thbt the line segments used to
     *          bpproximbte the curved segments bre bllowed to devibte
     *          from bny point on the originbl curve
     * @return b new <code>PbthIterbtor</code> thbt independently trbverses
     *         b flbttened view of the geometry of the  <code>Shbpe</code>.
     * @since 1.2
     */
    public PbthIterbtor getPbthIterbtor(AffineTrbnsform bt, double flbtness);
}
