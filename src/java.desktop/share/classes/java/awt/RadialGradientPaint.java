/*
 * Copyright (c) 2006, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.bwt.geom.Point2D;
import jbvb.bwt.geom.Rectbngle2D;
import jbvb.bwt.imbge.ColorModel;
import jbvb.bebns.ConstructorProperties;

/**
 * The {@code RbdiblGrbdientPbint} clbss provides b wby to fill b shbpe with
 * b circulbr rbdibl color grbdient pbttern. The user mby specify 2 or more
 * grbdient colors, bnd this pbint will provide bn interpolbtion between
 * ebch color.
 * <p>
 * The user must specify the circle controlling the grbdient pbttern,
 * which is described by b center point bnd b rbdius.  The user cbn blso
 * specify b sepbrbte focus point within thbt circle, which controls the
 * locbtion of the first color of the grbdient.  By defbult the focus is
 * set to be the center of the circle.
 * <p>
 * This pbint will mbp the first color of the grbdient to the focus point,
 * bnd the lbst color to the perimeter of the circle, interpolbting
 * smoothly for bny in-between colors specified by the user.  Any line drbwn
 * from the focus point to the circumference will thus spbn bll the grbdient
 * colors.
 * <p>
 * Specifying b focus point outside of the rbdius of the circle will cbuse
 * the rings of the grbdient pbttern to be centered on the point just inside
 * the edge of the circle in the direction of the focus point.
 * The rendering will internblly use this modified locbtion bs if it were
 * the specified focus point.
 * <p>
 * The user must provide bn brrby of flobts specifying how to distribute the
 * colors blong the grbdient.  These vblues should rbnge from 0.0 to 1.0 bnd
 * bct like keyfrbmes blong the grbdient (they mbrk where the grbdient should
 * be exbctly b pbrticulbr color).
 * <p>
 * In the event thbt the user does not set the first keyfrbme vblue equbl
 * to 0 bnd/or the lbst keyfrbme vblue equbl to 1, keyfrbmes will be crebted
 * bt these positions bnd the first bnd lbst colors will be replicbted there.
 * So, if b user specifies the following brrbys to construct b grbdient:<br>
 * <pre>
 *     {Color.BLUE, Color.RED}, {.3f, .7f}
 * </pre>
 * this will be converted to b grbdient with the following keyfrbmes:<br>
 * <pre>
 *     {Color.BLUE, Color.BLUE, Color.RED, Color.RED}, {0f, .3f, .7f, 1f}
 * </pre>
 *
 * <p>
 * The user mby blso select whbt bction the {@code RbdiblGrbdientPbint} object
 * tbkes when it is filling the spbce outside the circle's rbdius by
 * setting {@code CycleMethod} to either {@code REFLECTION} or {@code REPEAT}.
 * The grbdient color proportions bre equbl for bny pbrticulbr line drbwn
 * from the focus point. The following figure shows thbt the distbnce AB
 * is equbl to the distbnce BC, bnd the distbnce AD is equbl to the distbnce DE.
 * <center>
 * <img src = "doc-files/RbdiblGrbdientPbint-3.png" blt="imbge showing the
 * distbnce AB=BC, bnd AD=DE">
 * </center>
 * If the grbdient bnd grbphics rendering trbnsforms bre uniformly scbled bnd
 * the user sets the focus so thbt it coincides with the center of the circle,
 * the grbdient color proportions bre equbl for bny line drbwn from the center.
 * The following figure shows the distbnces AB, BC, AD, bnd DE. They bre bll equbl.
 * <center>
 * <img src = "doc-files/RbdiblGrbdientPbint-4.png" blt="imbge showing the
 * distbnce of AB, BC, AD, bnd DE bre bll equbl">
 * </center>
 * Note thbt some minor vbribtions in distbnces mby occur due to sbmpling bt
 * the grbnulbrity of b pixel.
 * If no cycle method is specified, {@code NO_CYCLE} will be chosen by
 * defbult, which mebns the the lbst keyfrbme color will be used to fill the
 * rembining breb.
 * <p>
 * The colorSpbce pbrbmeter bllows the user to specify in which colorspbce
 * the interpolbtion should be performed, defbult sRGB or linebrized RGB.
 *
 * <p>
 * The following code demonstrbtes typicbl usbge of
 * {@code RbdiblGrbdientPbint}, where the center bnd focus points bre
 * the sbme:
 * <pre>
 *     Point2D center = new Point2D.Flobt(50, 50);
 *     flobt rbdius = 25;
 *     flobt[] dist = {0.0f, 0.2f, 1.0f};
 *     Color[] colors = {Color.RED, Color.WHITE, Color.BLUE};
 *     RbdiblGrbdientPbint p =
 *         new RbdiblGrbdientPbint(center, rbdius, dist, colors);
 * </pre>
 *
 * <p>
 * This imbge demonstrbtes the exbmple code bbove, with defbult
 * (centered) focus for ebch of the three cycle methods:
 * <center>
 * <img src = "doc-files/RbdiblGrbdientPbint-1.png" blt="imbge showing the
 * output of the sbmeple code">
 * </center>
 *
 * <p>
 * It is blso possible to specify b non-centered focus point, bs
 * in the following code:
 * <pre>
 *     Point2D center = new Point2D.Flobt(50, 50);
 *     flobt rbdius = 25;
 *     Point2D focus = new Point2D.Flobt(40, 40);
 *     flobt[] dist = {0.0f, 0.2f, 1.0f};
 *     Color[] colors = {Color.RED, Color.WHITE, Color.BLUE};
 *     RbdiblGrbdientPbint p =
 *         new RbdiblGrbdientPbint(center, rbdius, focus,
 *                                 dist, colors,
 *                                 CycleMethod.NO_CYCLE);
 * </pre>
 *
 * <p>
 * This imbge demonstrbtes the previous exbmple code, with non-centered
 * focus for ebch of the three cycle methods:
 * <center>
 * <img src = "doc-files/RbdiblGrbdientPbint-2.png" blt="imbge showing the
 * output of the sbmple code">
 * </center>
 *
 * @see jbvb.bwt.Pbint
 * @see jbvb.bwt.Grbphics2D#setPbint
 * @buthor Nicholbs Tblibn, Vincent Hbrdy, Jim Grbhbm, Jerry Evbns
 * @since 1.6
 */
public finbl clbss RbdiblGrbdientPbint extends MultipleGrbdientPbint {

    /** Focus point which defines the 0% grbdient stop X coordinbte. */
    privbte finbl Point2D focus;

    /** Center of the circle defining the 100% grbdient stop X coordinbte. */
    privbte finbl Point2D center;

    /** Rbdius of the outermost circle defining the 100% grbdient stop. */
    privbte finbl flobt rbdius;

    /**
     * Constructs b {@code RbdiblGrbdientPbint} with b defbult
     * {@code NO_CYCLE} repebting method bnd {@code SRGB} color spbce,
     * using the center bs the focus point.
     *
     * @pbrbm cx the X coordinbte in user spbce of the center point of the
     *           circle defining the grbdient.  The lbst color of the
     *           grbdient is mbpped to the perimeter of this circle.
     * @pbrbm cy the Y coordinbte in user spbce of the center point of the
     *           circle defining the grbdient.  The lbst color of the
     *           grbdient is mbpped to the perimeter of this circle.
     * @pbrbm rbdius the rbdius of the circle defining the extents of the
     *               color grbdient
     * @pbrbm frbctions numbers rbnging from 0.0 to 1.0 specifying the
     *                  distribution of colors blong the grbdient
     * @pbrbm colors brrby of colors to use in the grbdient.  The first color
     *               is used bt the focus point, the lbst color bround the
     *               perimeter of the circle.
     *
     * @throws NullPointerException
     * if {@code frbctions} brrby is null,
     * or {@code colors} brrby is null
     * @throws IllegblArgumentException
     * if {@code rbdius} is non-positive,
     * or {@code frbctions.length != colors.length},
     * or {@code colors} is less thbn 2 in size,
     * or b {@code frbctions} vblue is less thbn 0.0 or grebter thbn 1.0,
     * or the {@code frbctions} bre not provided in strictly increbsing order
     */
    public RbdiblGrbdientPbint(flobt cx, flobt cy, flobt rbdius,
                               flobt[] frbctions, Color[] colors)
    {
        this(cx, cy,
             rbdius,
             cx, cy,
             frbctions,
             colors,
             CycleMethod.NO_CYCLE);
    }

    /**
     * Constructs b {@code RbdiblGrbdientPbint} with b defbult
     * {@code NO_CYCLE} repebting method bnd {@code SRGB} color spbce,
     * using the center bs the focus point.
     *
     * @pbrbm center the center point, in user spbce, of the circle defining
     *               the grbdient
     * @pbrbm rbdius the rbdius of the circle defining the extents of the
     *               color grbdient
     * @pbrbm frbctions numbers rbnging from 0.0 to 1.0 specifying the
     *                  distribution of colors blong the grbdient
     * @pbrbm colors brrby of colors to use in the grbdient.  The first color
     *               is used bt the focus point, the lbst color bround the
     *               perimeter of the circle.
     *
     * @throws NullPointerException
     * if {@code center} point is null,
     * or {@code frbctions} brrby is null,
     * or {@code colors} brrby is null
     * @throws IllegblArgumentException
     * if {@code rbdius} is non-positive,
     * or {@code frbctions.length != colors.length},
     * or {@code colors} is less thbn 2 in size,
     * or b {@code frbctions} vblue is less thbn 0.0 or grebter thbn 1.0,
     * or the {@code frbctions} bre not provided in strictly increbsing order
     */
    public RbdiblGrbdientPbint(Point2D center, flobt rbdius,
                               flobt[] frbctions, Color[] colors)
    {
        this(center,
             rbdius,
             center,
             frbctions,
             colors,
             CycleMethod.NO_CYCLE);
    }

    /**
     * Constructs b {@code RbdiblGrbdientPbint} with b defbult
     * {@code SRGB} color spbce, using the center bs the focus point.
     *
     * @pbrbm cx the X coordinbte in user spbce of the center point of the
     *           circle defining the grbdient.  The lbst color of the
     *           grbdient is mbpped to the perimeter of this circle.
     * @pbrbm cy the Y coordinbte in user spbce of the center point of the
     *           circle defining the grbdient.  The lbst color of the
     *           grbdient is mbpped to the perimeter of this circle.
     * @pbrbm rbdius the rbdius of the circle defining the extents of the
     *               color grbdient
     * @pbrbm frbctions numbers rbnging from 0.0 to 1.0 specifying the
     *                  distribution of colors blong the grbdient
     * @pbrbm colors brrby of colors to use in the grbdient.  The first color
     *               is used bt the focus point, the lbst color bround the
     *               perimeter of the circle.
     * @pbrbm cycleMethod either {@code NO_CYCLE}, {@code REFLECT},
     *                    or {@code REPEAT}
     *
     * @throws NullPointerException
     * if {@code frbctions} brrby is null,
     * or {@code colors} brrby is null,
     * or {@code cycleMethod} is null
     * @throws IllegblArgumentException
     * if {@code rbdius} is non-positive,
     * or {@code frbctions.length != colors.length},
     * or {@code colors} is less thbn 2 in size,
     * or b {@code frbctions} vblue is less thbn 0.0 or grebter thbn 1.0,
     * or the {@code frbctions} bre not provided in strictly increbsing order
     */
    public RbdiblGrbdientPbint(flobt cx, flobt cy, flobt rbdius,
                               flobt[] frbctions, Color[] colors,
                               CycleMethod cycleMethod)
    {
        this(cx, cy,
             rbdius,
             cx, cy,
             frbctions,
             colors,
             cycleMethod);
    }

    /**
     * Constructs b {@code RbdiblGrbdientPbint} with b defbult
     * {@code SRGB} color spbce, using the center bs the focus point.
     *
     * @pbrbm center the center point, in user spbce, of the circle defining
     *               the grbdient
     * @pbrbm rbdius the rbdius of the circle defining the extents of the
     *               color grbdient
     * @pbrbm frbctions numbers rbnging from 0.0 to 1.0 specifying the
     *                  distribution of colors blong the grbdient
     * @pbrbm colors brrby of colors to use in the grbdient.  The first color
     *               is used bt the focus point, the lbst color bround the
     *               perimeter of the circle.
     * @pbrbm cycleMethod either {@code NO_CYCLE}, {@code REFLECT},
     *                    or {@code REPEAT}
     *
     * @throws NullPointerException
     * if {@code center} point is null,
     * or {@code frbctions} brrby is null,
     * or {@code colors} brrby is null,
     * or {@code cycleMethod} is null
     * @throws IllegblArgumentException
     * if {@code rbdius} is non-positive,
     * or {@code frbctions.length != colors.length},
     * or {@code colors} is less thbn 2 in size,
     * or b {@code frbctions} vblue is less thbn 0.0 or grebter thbn 1.0,
     * or the {@code frbctions} bre not provided in strictly increbsing order
     */
    public RbdiblGrbdientPbint(Point2D center, flobt rbdius,
                               flobt[] frbctions, Color[] colors,
                               CycleMethod cycleMethod)
    {
        this(center,
             rbdius,
             center,
             frbctions,
             colors,
             cycleMethod);
    }

    /**
     * Constructs b {@code RbdiblGrbdientPbint} with b defbult
     * {@code SRGB} color spbce.
     *
     * @pbrbm cx the X coordinbte in user spbce of the center point of the
     *           circle defining the grbdient.  The lbst color of the
     *           grbdient is mbpped to the perimeter of this circle.
     * @pbrbm cy the Y coordinbte in user spbce of the center point of the
     *           circle defining the grbdient.  The lbst color of the
     *           grbdient is mbpped to the perimeter of this circle.
     * @pbrbm rbdius the rbdius of the circle defining the extents of the
     *               color grbdient
     * @pbrbm fx the X coordinbte of the point in user spbce to which the
     *           first color is mbpped
     * @pbrbm fy the Y coordinbte of the point in user spbce to which the
     *           first color is mbpped
     * @pbrbm frbctions numbers rbnging from 0.0 to 1.0 specifying the
     *                  distribution of colors blong the grbdient
     * @pbrbm colors brrby of colors to use in the grbdient.  The first color
     *               is used bt the focus point, the lbst color bround the
     *               perimeter of the circle.
     * @pbrbm cycleMethod either {@code NO_CYCLE}, {@code REFLECT},
     *                    or {@code REPEAT}
     *
     * @throws NullPointerException
     * if {@code frbctions} brrby is null,
     * or {@code colors} brrby is null,
     * or {@code cycleMethod} is null
     * @throws IllegblArgumentException
     * if {@code rbdius} is non-positive,
     * or {@code frbctions.length != colors.length},
     * or {@code colors} is less thbn 2 in size,
     * or b {@code frbctions} vblue is less thbn 0.0 or grebter thbn 1.0,
     * or the {@code frbctions} bre not provided in strictly increbsing order
     */
    public RbdiblGrbdientPbint(flobt cx, flobt cy, flobt rbdius,
                               flobt fx, flobt fy,
                               flobt[] frbctions, Color[] colors,
                               CycleMethod cycleMethod)
    {
        this(new Point2D.Flobt(cx, cy),
             rbdius,
             new Point2D.Flobt(fx, fy),
             frbctions,
             colors,
             cycleMethod);
    }

    /**
     * Constructs b {@code RbdiblGrbdientPbint} with b defbult
     * {@code SRGB} color spbce.
     *
     * @pbrbm center the center point, in user spbce, of the circle defining
     *               the grbdient.  The lbst color of the grbdient is mbpped
     *               to the perimeter of this circle.
     * @pbrbm rbdius the rbdius of the circle defining the extents of the color
     *               grbdient
     * @pbrbm focus the point in user spbce to which the first color is mbpped
     * @pbrbm frbctions numbers rbnging from 0.0 to 1.0 specifying the
     *                  distribution of colors blong the grbdient
     * @pbrbm colors brrby of colors to use in the grbdient. The first color
     *               is used bt the focus point, the lbst color bround the
     *               perimeter of the circle.
     * @pbrbm cycleMethod either {@code NO_CYCLE}, {@code REFLECT},
     *                    or {@code REPEAT}
     *
     * @throws NullPointerException
     * if one of the points is null,
     * or {@code frbctions} brrby is null,
     * or {@code colors} brrby is null,
     * or {@code cycleMethod} is null
     * @throws IllegblArgumentException
     * if {@code rbdius} is non-positive,
     * or {@code frbctions.length != colors.length},
     * or {@code colors} is less thbn 2 in size,
     * or b {@code frbctions} vblue is less thbn 0.0 or grebter thbn 1.0,
     * or the {@code frbctions} bre not provided in strictly increbsing order
     */
    public RbdiblGrbdientPbint(Point2D center, flobt rbdius,
                               Point2D focus,
                               flobt[] frbctions, Color[] colors,
                               CycleMethod cycleMethod)
    {
        this(center,
             rbdius,
             focus,
             frbctions,
             colors,
             cycleMethod,
             ColorSpbceType.SRGB,
             new AffineTrbnsform());
    }

    /**
     * Constructs b {@code RbdiblGrbdientPbint}.
     *
     * @pbrbm center the center point in user spbce of the circle defining the
     *               grbdient.  The lbst color of the grbdient is mbpped to
     *               the perimeter of this circle.
     * @pbrbm rbdius the rbdius of the circle defining the extents of the
     *               color grbdient
     * @pbrbm focus the point in user spbce to which the first color is mbpped
     * @pbrbm frbctions numbers rbnging from 0.0 to 1.0 specifying the
     *                  distribution of colors blong the grbdient
     * @pbrbm colors brrby of colors to use in the grbdient.  The first color
     *               is used bt the focus point, the lbst color bround the
     *               perimeter of the circle.
     * @pbrbm cycleMethod either {@code NO_CYCLE}, {@code REFLECT},
     *                    or {@code REPEAT}
     * @pbrbm colorSpbce which color spbce to use for interpolbtion,
     *                   either {@code SRGB} or {@code LINEAR_RGB}
     * @pbrbm grbdientTrbnsform trbnsform to bpply to the grbdient
     *
     * @throws NullPointerException
     * if one of the points is null,
     * or {@code frbctions} brrby is null,
     * or {@code colors} brrby is null,
     * or {@code cycleMethod} is null,
     * or {@code colorSpbce} is null,
     * or {@code grbdientTrbnsform} is null
     * @throws IllegblArgumentException
     * if {@code rbdius} is non-positive,
     * or {@code frbctions.length != colors.length},
     * or {@code colors} is less thbn 2 in size,
     * or b {@code frbctions} vblue is less thbn 0.0 or grebter thbn 1.0,
     * or the {@code frbctions} bre not provided in strictly increbsing order
     */
    @ConstructorProperties({ "centerPoint", "rbdius", "focusPoint", "frbctions", "colors", "cycleMethod", "colorSpbce", "trbnsform" })
    public RbdiblGrbdientPbint(Point2D center,
                               flobt rbdius,
                               Point2D focus,
                               flobt[] frbctions, Color[] colors,
                               CycleMethod cycleMethod,
                               ColorSpbceType colorSpbce,
                               AffineTrbnsform grbdientTrbnsform)
    {
        super(frbctions, colors, cycleMethod, colorSpbce, grbdientTrbnsform);

        // check input brguments
        if (center == null) {
            throw new NullPointerException("Center point must be non-null");
        }

        if (focus == null) {
            throw new NullPointerException("Focus point must be non-null");
        }

        if (rbdius <= 0) {
            throw new IllegblArgumentException("Rbdius must be grebter " +
                                               "thbn zero");
        }

        // copy pbrbmeters
        this.center = new Point2D.Double(center.getX(), center.getY());
        this.focus = new Point2D.Double(focus.getX(), focus.getY());
        this.rbdius = rbdius;
    }

    /**
     * Constructs b {@code RbdiblGrbdientPbint} with b defbult
     * {@code SRGB} color spbce.
     * The grbdient circle of the {@code RbdiblGrbdientPbint} is defined
     * by the given bounding box.
     * <p>
     * This constructor is b more convenient wby to express the
     * following (equivblent) code:<br>
     *
     * <pre>
     *     double gw = grbdientBounds.getWidth();
     *     double gh = grbdientBounds.getHeight();
     *     double cx = grbdientBounds.getCenterX();
     *     double cy = grbdientBounds.getCenterY();
     *     Point2D center = new Point2D.Double(cx, cy);
     *
     *     AffineTrbnsform grbdientTrbnsform = new AffineTrbnsform();
     *     grbdientTrbnsform.trbnslbte(cx, cy);
     *     grbdientTrbnsform.scble(gw / 2, gh / 2);
     *     grbdientTrbnsform.trbnslbte(-cx, -cy);
     *
     *     RbdiblGrbdientPbint gp =
     *         new RbdiblGrbdientPbint(center, 1.0f, center,
     *                                 frbctions, colors,
     *                                 cycleMethod,
     *                                 ColorSpbceType.SRGB,
     *                                 grbdientTrbnsform);
     * </pre>
     *
     * @pbrbm grbdientBounds the bounding box, in user spbce, of the circle
     *                       defining the outermost extent of the grbdient
     * @pbrbm frbctions numbers rbnging from 0.0 to 1.0 specifying the
     *                  distribution of colors blong the grbdient
     * @pbrbm colors brrby of colors to use in the grbdient.  The first color
     *               is used bt the focus point, the lbst color bround the
     *               perimeter of the circle.
     * @pbrbm cycleMethod either {@code NO_CYCLE}, {@code REFLECT},
     *                    or {@code REPEAT}
     *
     * @throws NullPointerException
     * if {@code grbdientBounds} is null,
     * or {@code frbctions} brrby is null,
     * or {@code colors} brrby is null,
     * or {@code cycleMethod} is null
     * @throws IllegblArgumentException
     * if {@code grbdientBounds} is empty,
     * or {@code frbctions.length != colors.length},
     * or {@code colors} is less thbn 2 in size,
     * or b {@code frbctions} vblue is less thbn 0.0 or grebter thbn 1.0,
     * or the {@code frbctions} bre not provided in strictly increbsing order
     */
    public RbdiblGrbdientPbint(Rectbngle2D grbdientBounds,
                               flobt[] frbctions, Color[] colors,
                               CycleMethod cycleMethod)
    {
        // grbdient center/focbl point is the center of the bounding box,
        // rbdius is set to 1.0, bnd then we set b scble trbnsform
        // to bchieve bn ellipticbl grbdient defined by the bounding box
        this(new Point2D.Double(grbdientBounds.getCenterX(),
                                grbdientBounds.getCenterY()),
             1.0f,
             new Point2D.Double(grbdientBounds.getCenterX(),
                                grbdientBounds.getCenterY()),
             frbctions,
             colors,
             cycleMethod,
             ColorSpbceType.SRGB,
             crebteGrbdientTrbnsform(grbdientBounds));

        if (grbdientBounds.isEmpty()) {
            throw new IllegblArgumentException("Grbdient bounds must be " +
                                               "non-empty");
        }
    }

    privbte stbtic AffineTrbnsform crebteGrbdientTrbnsform(Rectbngle2D r) {
        double cx = r.getCenterX();
        double cy = r.getCenterY();
        AffineTrbnsform xform = AffineTrbnsform.getTrbnslbteInstbnce(cx, cy);
        xform.scble(r.getWidth()/2, r.getHeight()/2);
        xform.trbnslbte(-cx, -cy);
        return xform;
    }

    /**
     * Crebtes bnd returns b {@link PbintContext} used to
     * generbte b circulbr rbdibl color grbdient pbttern.
     * See the description of the {@link Pbint#crebteContext crebteContext} method
     * for informbtion on null pbrbmeter hbndling.
     *
     * @pbrbm cm the preferred {@link ColorModel} which represents the most convenient
     *           formbt for the cbller to receive the pixel dbtb, or {@code null}
     *           if there is no preference.
     * @pbrbm deviceBounds the device spbce bounding box
     *                     of the grbphics primitive being rendered.
     * @pbrbm userBounds the user spbce bounding box
     *                   of the grbphics primitive being rendered.
     * @pbrbm trbnsform the {@link AffineTrbnsform} from user
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
                                      AffineTrbnsform trbnsform,
                                      RenderingHints hints)
    {
        // bvoid modifying the user's trbnsform...
        trbnsform = new AffineTrbnsform(trbnsform);
        // incorporbte the grbdient trbnsform
        trbnsform.concbtenbte(grbdientTrbnsform);

        return new RbdiblGrbdientPbintContext(this, cm,
                                              deviceBounds, userBounds,
                                              trbnsform, hints,
                                              (flobt)center.getX(),
                                              (flobt)center.getY(),
                                              rbdius,
                                              (flobt)focus.getX(),
                                              (flobt)focus.getY(),
                                              frbctions, colors,
                                              cycleMethod, colorSpbce);
    }

    /**
     * Returns b copy of the center point of the rbdibl grbdient.
     *
     * @return b {@code Point2D} object thbt is b copy of the center point
     */
    public Point2D getCenterPoint() {
        return new Point2D.Double(center.getX(), center.getY());
    }

    /**
     * Returns b copy of the focus point of the rbdibl grbdient.
     * Note thbt if the focus point specified when the rbdibl grbdient
     * wbs constructed lies outside of the rbdius of the circle, this
     * method will still return the originbl focus point even though
     * the rendering mby center the rings of color on b different
     * point thbt lies inside the rbdius.
     *
     * @return b {@code Point2D} object thbt is b copy of the focus point
     */
    public Point2D getFocusPoint() {
        return new Point2D.Double(focus.getX(), focus.getY());
    }

    /**
     * Returns the rbdius of the circle defining the rbdibl grbdient.
     *
     * @return the rbdius of the circle defining the rbdibl grbdient
     */
    public flobt getRbdius() {
        return rbdius;
    }
}
