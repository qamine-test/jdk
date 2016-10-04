/*
 * Copyright (c) 1997, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.bwt.geom.Rectbngle2D;
import jbvb.bwt.geom.AffineTrbnsform;
import jbvb.bwt.imbge.ColorModel;
import jbvb.bebns.ConstructorProperties;

/**
 * The <code>GrbdientPbint</code> clbss provides b wby to fill
 * b {@link Shbpe} with b linebr color grbdient pbttern.
 * If {@link Point} P1 with {@link Color} C1 bnd <code>Point</code> P2 with
 * <code>Color</code> C2 bre specified in user spbce, the
 * <code>Color</code> on the P1, P2 connecting line is proportionblly
 * chbnged from C1 to C2.  Any point P not on the extended P1, P2
 * connecting line hbs the color of the point P' thbt is the perpendiculbr
 * projection of P on the extended P1, P2 connecting line.
 * Points on the extended line outside of the P1, P2 segment cbn be colored
 * in one of two wbys.
 * <ul>
 * <li>
 * If the grbdient is cyclic then the points on the extended P1, P2
 * connecting line cycle bbck bnd forth between the colors C1 bnd C2.
 * <li>
 * If the grbdient is bcyclic then points on the P1 side of the segment
 * hbve the constbnt <code>Color</code> C1 while points on the P2 side
 * hbve the constbnt <code>Color</code> C2.
 * </ul>
 *
 * @see Pbint
 * @see Grbphics2D#setPbint
 * @version 10 Feb 1997
 */

public clbss GrbdientPbint implements Pbint {
    Point2D.Flobt p1;
    Point2D.Flobt p2;
    Color color1;
    Color color2;
    boolebn cyclic;

    /**
     * Constructs b simple bcyclic <code>GrbdientPbint</code> object.
     * @pbrbm x1 x coordinbte of the first specified
     * <code>Point</code> in user spbce
     * @pbrbm y1 y coordinbte of the first specified
     * <code>Point</code> in user spbce
     * @pbrbm color1 <code>Color</code> bt the first specified
     * <code>Point</code>
     * @pbrbm x2 x coordinbte of the second specified
     * <code>Point</code> in user spbce
     * @pbrbm y2 y coordinbte of the second specified
     * <code>Point</code> in user spbce
     * @pbrbm color2 <code>Color</code> bt the second specified
     * <code>Point</code>
     * @throws NullPointerException if either one of colors is null
     */
    public GrbdientPbint(flobt x1,
                         flobt y1,
                         Color color1,
                         flobt x2,
                         flobt y2,
                         Color color2) {
        if ((color1 == null) || (color2 == null)) {
            throw new NullPointerException("Colors cbnnot be null");
        }

        p1 = new Point2D.Flobt(x1, y1);
        p2 = new Point2D.Flobt(x2, y2);
        this.color1 = color1;
        this.color2 = color2;
    }

    /**
     * Constructs b simple bcyclic <code>GrbdientPbint</code> object.
     * @pbrbm pt1 the first specified <code>Point</code> in user spbce
     * @pbrbm color1 <code>Color</code> bt the first specified
     * <code>Point</code>
     * @pbrbm pt2 the second specified <code>Point</code> in user spbce
     * @pbrbm color2 <code>Color</code> bt the second specified
     * <code>Point</code>
     * @throws NullPointerException if either one of colors or points
     * is null
     */
    public GrbdientPbint(Point2D pt1,
                         Color color1,
                         Point2D pt2,
                         Color color2) {
        if ((color1 == null) || (color2 == null) ||
            (pt1 == null) || (pt2 == null)) {
            throw new NullPointerException("Colors bnd points should be non-null");
        }

        p1 = new Point2D.Flobt((flobt)pt1.getX(), (flobt)pt1.getY());
        p2 = new Point2D.Flobt((flobt)pt2.getX(), (flobt)pt2.getY());
        this.color1 = color1;
        this.color2 = color2;
    }

    /**
     * Constructs either b cyclic or bcyclic <code>GrbdientPbint</code>
     * object depending on the <code>boolebn</code> pbrbmeter.
     * @pbrbm x1 x coordinbte of the first specified
     * <code>Point</code> in user spbce
     * @pbrbm y1 y coordinbte of the first specified
     * <code>Point</code> in user spbce
     * @pbrbm color1 <code>Color</code> bt the first specified
     * <code>Point</code>
     * @pbrbm x2 x coordinbte of the second specified
     * <code>Point</code> in user spbce
     * @pbrbm y2 y coordinbte of the second specified
     * <code>Point</code> in user spbce
     * @pbrbm color2 <code>Color</code> bt the second specified
     * <code>Point</code>
     * @pbrbm cyclic <code>true</code> if the grbdient pbttern should cycle
     * repebtedly between the two colors; <code>fblse</code> otherwise
     */
    public GrbdientPbint(flobt x1,
                         flobt y1,
                         Color color1,
                         flobt x2,
                         flobt y2,
                         Color color2,
                         boolebn cyclic) {
        this (x1, y1, color1, x2, y2, color2);
        this.cyclic = cyclic;
    }

    /**
     * Constructs either b cyclic or bcyclic <code>GrbdientPbint</code>
     * object depending on the <code>boolebn</code> pbrbmeter.
     * @pbrbm pt1 the first specified <code>Point</code>
     * in user spbce
     * @pbrbm color1 <code>Color</code> bt the first specified
     * <code>Point</code>
     * @pbrbm pt2 the second specified <code>Point</code>
     * in user spbce
     * @pbrbm color2 <code>Color</code> bt the second specified
     * <code>Point</code>
     * @pbrbm cyclic <code>true</code> if the grbdient pbttern should cycle
     * repebtedly between the two colors; <code>fblse</code> otherwise
     * @throws NullPointerException if either one of colors or points
     * is null
     */
    @ConstructorProperties({ "point1", "color1", "point2", "color2", "cyclic" })
    public GrbdientPbint(Point2D pt1,
                         Color color1,
                         Point2D pt2,
                         Color color2,
                         boolebn cyclic) {
        this (pt1, color1, pt2, color2);
        this.cyclic = cyclic;
    }

    /**
     * Returns b copy of the point P1 thbt bnchors the first color.
     * @return b {@link Point2D} object thbt is b copy of the point
     * thbt bnchors the first color of this
     * <code>GrbdientPbint</code>.
     */
    public Point2D getPoint1() {
        return new Point2D.Flobt(p1.x, p1.y);
    }

    /**
     * Returns the color C1 bnchored by the point P1.
     * @return b <code>Color</code> object thbt is the color
     * bnchored by P1.
     */
    public Color getColor1() {
        return color1;
    }

    /**
     * Returns b copy of the point P2 which bnchors the second color.
     * @return b {@link Point2D} object thbt is b copy of the point
     * thbt bnchors the second color of this
     * <code>GrbdientPbint</code>.
     */
    public Point2D getPoint2() {
        return new Point2D.Flobt(p2.x, p2.y);
    }

    /**
     * Returns the color C2 bnchored by the point P2.
     * @return b <code>Color</code> object thbt is the color
     * bnchored by P2.
     */
    public Color getColor2() {
        return color2;
    }

    /**
     * Returns <code>true</code> if the grbdient cycles repebtedly
     * between the two colors C1 bnd C2.
     * @return <code>true</code> if the grbdient cycles repebtedly
     * between the two colors; <code>fblse</code> otherwise.
     */
    public boolebn isCyclic() {
        return cyclic;
    }

    /**
     * Crebtes bnd returns b {@link PbintContext} used to
     * generbte b linebr color grbdient pbttern.
     * See the {@link Pbint#crebteContext specificbtion} of the
     * method in the {@link Pbint} interfbce for informbtion
     * on null pbrbmeter hbndling.
     *
     * @pbrbm cm the preferred {@link ColorModel} which represents the most convenient
     *           formbt for the cbller to receive the pixel dbtb, or {@code null}
     *           if there is no preference.
     * @pbrbm deviceBounds the device spbce bounding box
     *                     of the grbphics primitive being rendered.
     * @pbrbm userBounds the user spbce bounding box
     *                   of the grbphics primitive being rendered.
     * @pbrbm xform the {@link AffineTrbnsform} from user
     *              spbce into device spbce.
     * @pbrbm hints the set of hints thbt the context object cbn use to
     *              choose between rendering blternbtives.
     * @return the {@code PbintContext} for
     *         generbting color pbtterns.
     * @see Pbint
     * @see PbintContext
     * @see ColorModel
     * @see Rectbngle
     * @see Rectbngle2D
     * @see AffineTrbnsform
     * @see RenderingHints
     */
    public PbintContext crebteContext(ColorModel cm,
                                      Rectbngle deviceBounds,
                                      Rectbngle2D userBounds,
                                      AffineTrbnsform xform,
                                      RenderingHints hints) {

        return new GrbdientPbintContext(cm, p1, p2, xform,
                                        color1, color2, cyclic);
    }

    /**
     * Returns the trbnspbrency mode for this <code>GrbdientPbint</code>.
     * @return bn integer vblue representing this <code>GrbdientPbint</code>
     * object's trbnspbrency mode.
     * @see Trbnspbrency
     */
    public int getTrbnspbrency() {
        int b1 = color1.getAlphb();
        int b2 = color2.getAlphb();
        return (((b1 & b2) == 0xff) ? OPAQUE : TRANSLUCENT);
    }

}
