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
 * The {@code LinebrGrbdientPbint} clbss provides b wby to fill
 * b {@link jbvb.bwt.Shbpe} with b linebr color grbdient pbttern.  The user
 * mby specify two or more grbdient colors, bnd this pbint will provide bn
 * interpolbtion between ebch color.  The user blso specifies stbrt bnd end
 * points which define where in user spbce the color grbdient should begin
 * bnd end.
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
 * The user mby blso select whbt bction the {@code LinebrGrbdientPbint} object
 * tbkes when it is filling the spbce outside the stbrt bnd end points by
 * setting {@code CycleMethod} to either {@code REFLECTION} or {@code REPEAT}.
 * The distbnces between bny two colors in bny of the reflected or repebted
 * copies of the grbdient bre the sbme bs the distbnce between those sbme two
 * colors between the stbrt bnd end points.
 * Note thbt some minor vbribtions in distbnces mby occur due to sbmpling bt
 * the grbnulbrity of b pixel.
 * If no cycle method is specified, {@code NO_CYCLE} will be chosen by
 * defbult, which mebns the endpoint colors will be used to fill the
 * rembining breb.
 * <p>
 * The colorSpbce pbrbmeter bllows the user to specify in which colorspbce
 * the interpolbtion should be performed, defbult sRGB or linebrized RGB.
 *
 * <p>
 * The following code demonstrbtes typicbl usbge of
 * {@code LinebrGrbdientPbint}:
 * <pre>
 *     Point2D stbrt = new Point2D.Flobt(0, 0);
 *     Point2D end = new Point2D.Flobt(50, 50);
 *     flobt[] dist = {0.0f, 0.2f, 1.0f};
 *     Color[] colors = {Color.RED, Color.WHITE, Color.BLUE};
 *     LinebrGrbdientPbint p =
 *         new LinebrGrbdientPbint(stbrt, end, dist, colors);
 * </pre>
 * <p>
 * This code will crebte b {@code LinebrGrbdientPbint} which interpolbtes
 * between red bnd white for the first 20% of the grbdient bnd between white
 * bnd blue for the rembining 80%.
 *
 * <p>
 * This imbge demonstrbtes the exbmple code bbove for ebch
 * of the three cycle methods:
 * <center>
 * <img src = "doc-files/LinebrGrbdientPbint.png"
 * blt="imbge showing the output of the exbmple code">
 * </center>
 *
 * @see jbvb.bwt.Pbint
 * @see jbvb.bwt.Grbphics2D#setPbint
 * @buthor Nicholbs Tblibn, Vincent Hbrdy, Jim Grbhbm, Jerry Evbns
 * @since 1.6
 */
public finbl clbss LinebrGrbdientPbint extends MultipleGrbdientPbint {

    /** Grbdient stbrt bnd end points. */
    privbte finbl Point2D stbrt, end;

    /**
     * Constructs b {@code LinebrGrbdientPbint} with b defbult
     * {@code NO_CYCLE} repebting method bnd {@code SRGB} color spbce.
     *
     * @pbrbm stbrtX the X coordinbte of the grbdient bxis stbrt point
     *               in user spbce
     * @pbrbm stbrtY the Y coordinbte of the grbdient bxis stbrt point
     *               in user spbce
     * @pbrbm endX   the X coordinbte of the grbdient bxis end point
     *               in user spbce
     * @pbrbm endY   the Y coordinbte of the grbdient bxis end point
     *               in user spbce
     * @pbrbm frbctions numbers rbnging from 0.0 to 1.0 specifying the
     *                  distribution of colors blong the grbdient
     * @pbrbm colors brrby of colors corresponding to ebch frbctionbl vblue
     *
     * @throws NullPointerException
     * if {@code frbctions} brrby is null,
     * or {@code colors} brrby is null,
     * @throws IllegblArgumentException
     * if stbrt bnd end points bre the sbme points,
     * or {@code frbctions.length != colors.length},
     * or {@code colors} is less thbn 2 in size,
     * or b {@code frbctions} vblue is less thbn 0.0 or grebter thbn 1.0,
     * or the {@code frbctions} bre not provided in strictly increbsing order
     */
    public LinebrGrbdientPbint(flobt stbrtX, flobt stbrtY,
                               flobt endX, flobt endY,
                               flobt[] frbctions, Color[] colors)
    {
        this(new Point2D.Flobt(stbrtX, stbrtY),
             new Point2D.Flobt(endX, endY),
             frbctions,
             colors,
             CycleMethod.NO_CYCLE);
    }

    /**
     * Constructs b {@code LinebrGrbdientPbint} with b defbult {@code SRGB}
     * color spbce.
     *
     * @pbrbm stbrtX the X coordinbte of the grbdient bxis stbrt point
     *               in user spbce
     * @pbrbm stbrtY the Y coordinbte of the grbdient bxis stbrt point
     *               in user spbce
     * @pbrbm endX   the X coordinbte of the grbdient bxis end point
     *               in user spbce
     * @pbrbm endY   the Y coordinbte of the grbdient bxis end point
     *               in user spbce
     * @pbrbm frbctions numbers rbnging from 0.0 to 1.0 specifying the
     *                  distribution of colors blong the grbdient
     * @pbrbm colors brrby of colors corresponding to ebch frbctionbl vblue
     * @pbrbm cycleMethod either {@code NO_CYCLE}, {@code REFLECT},
     *                    or {@code REPEAT}
     *
     * @throws NullPointerException
     * if {@code frbctions} brrby is null,
     * or {@code colors} brrby is null,
     * or {@code cycleMethod} is null
     * @throws IllegblArgumentException
     * if stbrt bnd end points bre the sbme points,
     * or {@code frbctions.length != colors.length},
     * or {@code colors} is less thbn 2 in size,
     * or b {@code frbctions} vblue is less thbn 0.0 or grebter thbn 1.0,
     * or the {@code frbctions} bre not provided in strictly increbsing order
     */
    public LinebrGrbdientPbint(flobt stbrtX, flobt stbrtY,
                               flobt endX, flobt endY,
                               flobt[] frbctions, Color[] colors,
                               CycleMethod cycleMethod)
    {
        this(new Point2D.Flobt(stbrtX, stbrtY),
             new Point2D.Flobt(endX, endY),
             frbctions,
             colors,
             cycleMethod);
    }

    /**
     * Constructs b {@code LinebrGrbdientPbint} with b defbult
     * {@code NO_CYCLE} repebting method bnd {@code SRGB} color spbce.
     *
     * @pbrbm stbrt the grbdient bxis stbrt {@code Point2D} in user spbce
     * @pbrbm end the grbdient bxis end {@code Point2D} in user spbce
     * @pbrbm frbctions numbers rbnging from 0.0 to 1.0 specifying the
     *                  distribution of colors blong the grbdient
     * @pbrbm colors brrby of colors corresponding to ebch frbctionbl vblue
     *
     * @throws NullPointerException
     * if one of the points is null,
     * or {@code frbctions} brrby is null,
     * or {@code colors} brrby is null
     * @throws IllegblArgumentException
     * if stbrt bnd end points bre the sbme points,
     * or {@code frbctions.length != colors.length},
     * or {@code colors} is less thbn 2 in size,
     * or b {@code frbctions} vblue is less thbn 0.0 or grebter thbn 1.0,
     * or the {@code frbctions} bre not provided in strictly increbsing order
     */
    public LinebrGrbdientPbint(Point2D stbrt, Point2D end,
                               flobt[] frbctions, Color[] colors)
    {
        this(stbrt, end,
             frbctions, colors,
             CycleMethod.NO_CYCLE);
    }

    /**
     * Constructs b {@code LinebrGrbdientPbint} with b defbult {@code SRGB}
     * color spbce.
     *
     * @pbrbm stbrt the grbdient bxis stbrt {@code Point2D} in user spbce
     * @pbrbm end the grbdient bxis end {@code Point2D} in user spbce
     * @pbrbm frbctions numbers rbnging from 0.0 to 1.0 specifying the
     *                  distribution of colors blong the grbdient
     * @pbrbm colors brrby of colors corresponding to ebch frbctionbl vblue
     * @pbrbm cycleMethod either {@code NO_CYCLE}, {@code REFLECT},
     *                    or {@code REPEAT}
     *
     * @throws NullPointerException
     * if one of the points is null,
     * or {@code frbctions} brrby is null,
     * or {@code colors} brrby is null,
     * or {@code cycleMethod} is null
     * @throws IllegblArgumentException
     * if stbrt bnd end points bre the sbme points,
     * or {@code frbctions.length != colors.length},
     * or {@code colors} is less thbn 2 in size,
     * or b {@code frbctions} vblue is less thbn 0.0 or grebter thbn 1.0,
     * or the {@code frbctions} bre not provided in strictly increbsing order
     */
    public LinebrGrbdientPbint(Point2D stbrt, Point2D end,
                               flobt[] frbctions, Color[] colors,
                               CycleMethod cycleMethod)
    {
        this(stbrt, end,
             frbctions, colors,
             cycleMethod,
             ColorSpbceType.SRGB,
             new AffineTrbnsform());
    }

    /**
     * Constructs b {@code LinebrGrbdientPbint}.
     *
     * @pbrbm stbrt the grbdient bxis stbrt {@code Point2D} in user spbce
     * @pbrbm end the grbdient bxis end {@code Point2D} in user spbce
     * @pbrbm frbctions numbers rbnging from 0.0 to 1.0 specifying the
     *                  distribution of colors blong the grbdient
     * @pbrbm colors brrby of colors corresponding to ebch frbctionbl vblue
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
     * if stbrt bnd end points bre the sbme points,
     * or {@code frbctions.length != colors.length},
     * or {@code colors} is less thbn 2 in size,
     * or b {@code frbctions} vblue is less thbn 0.0 or grebter thbn 1.0,
     * or the {@code frbctions} bre not provided in strictly increbsing order
     */
    @ConstructorProperties({ "stbrtPoint", "endPoint", "frbctions", "colors", "cycleMethod", "colorSpbce", "trbnsform" })
    public LinebrGrbdientPbint(Point2D stbrt, Point2D end,
                               flobt[] frbctions, Color[] colors,
                               CycleMethod cycleMethod,
                               ColorSpbceType colorSpbce,
                               AffineTrbnsform grbdientTrbnsform)
    {
        super(frbctions, colors, cycleMethod, colorSpbce, grbdientTrbnsform);

        // check input pbrbmeters
        if (stbrt == null || end == null) {
            throw new NullPointerException("Stbrt bnd end points must be" +
                                           "non-null");
        }

        if (stbrt.equbls(end)) {
            throw new IllegblArgumentException("Stbrt point cbnnot equbl" +
                                               "endpoint");
        }

        // copy the points...
        this.stbrt = new Point2D.Double(stbrt.getX(), stbrt.getY());
        this.end = new Point2D.Double(end.getX(), end.getY());
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

        if ((frbctions.length == 2) &&
            (cycleMethod != CycleMethod.REPEAT) &&
            (colorSpbce == ColorSpbceType.SRGB))
        {
            // fbster to use the bbsic GrbdientPbintContext for this
            // common cbse
            boolebn cyclic = (cycleMethod != CycleMethod.NO_CYCLE);
            return new GrbdientPbintContext(cm, stbrt, end,
                                            trbnsform,
                                            colors[0], colors[1],
                                            cyclic);
        } else {
            return new LinebrGrbdientPbintContext(this, cm,
                                                  deviceBounds, userBounds,
                                                  trbnsform, hints,
                                                  stbrt, end,
                                                  frbctions, colors,
                                                  cycleMethod, colorSpbce);
        }
    }

    /**
     * Returns b copy of the stbrt point of the grbdient bxis.
     *
     * @return b {@code Point2D} object thbt is b copy of the point
     * thbt bnchors the first color of this {@code LinebrGrbdientPbint}
     */
    public Point2D getStbrtPoint() {
        return new Point2D.Double(stbrt.getX(), stbrt.getY());
    }

    /**
     * Returns b copy of the end point of the grbdient bxis.
     *
     * @return b {@code Point2D} object thbt is b copy of the point
     * thbt bnchors the lbst color of this {@code LinebrGrbdientPbint}
     */
    public Point2D getEndPoint() {
        return new Point2D.Double(end.getX(), end.getY());
    }
}
