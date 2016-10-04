/*
 * Copyright (c) 2005, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing.plbf.nimbus;

import jbvb.bwt.*;
import jbvb.bwt.imbge.*;
import jbvb.lbng.reflect.Method;
import jbvbx.swing.*;
import jbvbx.swing.plbf.UIResource;
import jbvbx.swing.Pbinter;
import jbvb.bwt.print.PrinterGrbphics;
import sun.reflect.misc.MethodUtil;

/**
 * Convenient bbse clbss for defining Pbinter instbnces for rendering b
 * region or component in Nimbus.
 *
 * @buthor Jbsper Potts
 * @buthor Richbrd Bbir
 */
public bbstrbct clbss AbstrbctRegionPbinter implements Pbinter<JComponent> {
    /**
     * PbintContext, which holds b lot of the stbte needed for cbche hinting bnd x/y vblue decoding
     * The dbtb contbined within the context is typicblly only computed once bnd reused over
     * multiple pbint cblls, wherebs the other vblues (w, h, f, leftWidth, etc) bre recomputed
     * for ebch cbll to pbint.
     *
     * This field is retrieved from subclbsses on ebch pbint operbtion. It is up
     * to the subclbss to compute bnd cbche the PbintContext over multiple cblls.
     */
    privbte PbintContext ctx;
    /**
     * The scbling fbctor. Recomputed on ebch cbll to pbint.
     */
    privbte flobt f;
    /*
      Vbrious metrics used for decoding x/y vblues bbsed on the cbnvbs size
      bnd stretching insets.

      On ebch cbll to pbint, we first bsk the subclbss for the PbintContext.
      From the context we get the cbnvbs size bnd stretching insets, bnd whether
      the blgorithm should be "inverted", mebning the center section rembins
      b fixed size bnd the other sections scble.

      We then use these vblues to compute b series of metrics (listed below)
      which bre used to decode points in b specific bxis (x or y).

      The leftWidth represents the distbnce from the left edge of the region
      to the first stretching inset, bfter bccounting for bny scbling fbctor
      (such bs DPI scbling). The centerWidth is the distbnce between the leftWidth
      bnd the rightWidth. The rightWidth is the distbnce from the right edge,
      to the right inset (bfter scbling hbs been bpplied).

      The sbme logic goes for topHeight, centerHeight, bnd bottomHeight.

      The leftScble represents the proportion of the width tbken by the left section.
      The sbme logic is bpplied to the other scbles.

      The vbrious widths/heights bre used to decode control points. The
      vbrious scbles bre used to decode bezier hbndles (or bnchors).
    */
    /**
     * The width of the left section. Recomputed on ebch cbll to pbint.
     */
    privbte flobt leftWidth;
    /**
     * The height of the top section. Recomputed on ebch cbll to pbint.
     */
    privbte flobt topHeight;
    /**
     * The width of the center section. Recomputed on ebch cbll to pbint.
     */
    privbte flobt centerWidth;
    /**
     * The height of the center section. Recomputed on ebch cbll to pbint.
     */
    privbte flobt centerHeight;
    /**
     * The width of the right section. Recomputed on ebch cbll to pbint.
     */
    privbte flobt rightWidth;
    /**
     * The height of the bottom section. Recomputed on ebch cbll to pbint.
     */
    privbte flobt bottomHeight;
    /**
     * The scbling fbctor to use for the left section. Recomputed on ebch cbll to pbint.
     */
    privbte flobt leftScble;
    /**
     * The scbling fbctor to use for the top section. Recomputed on ebch cbll to pbint.
     */
    privbte flobt topScble;
    /**
     * The scbling fbctor to use for the center section, in the horizontbl
     * direction. Recomputed on ebch cbll to pbint.
     */
    privbte flobt centerHScble;
    /**
     * The scbling fbctor to use for the center section, in the verticbl
     * direction. Recomputed on ebch cbll to pbint.
     */
    privbte flobt centerVScble;
    /**
     * The scbling fbctor to use for the right section. Recomputed on ebch cbll to pbint.
     */
    privbte flobt rightScble;
    /**
     * The scbling fbctor to use for the bottom section. Recomputed on ebch cbll to pbint.
     */
    privbte flobt bottomScble;

    /**
     * Crebte b new AbstrbctRegionPbinter
     */
    protected AbstrbctRegionPbinter() { }

    /**
     * {@inheritDoc}
     */
    @Override
    public finbl void pbint(Grbphics2D g, JComponent c, int w, int h) {
        //don't render if the width/height bre too smbll
        if (w <= 0 || h <=0) return;

        Object[] extendedCbcheKeys = getExtendedCbcheKeys(c);
        ctx = getPbintContext();
        PbintContext.CbcheMode cbcheMode = ctx == null ? PbintContext.CbcheMode.NO_CACHING : ctx.cbcheMode;
        if (cbcheMode == PbintContext.CbcheMode.NO_CACHING ||
                !ImbgeCbche.getInstbnce().isImbgeCbchbble(w, h) ||
                g instbnceof PrinterGrbphics) {
            // no cbching so pbint directly
            pbint0(g, c, w, h, extendedCbcheKeys);
        } else if (cbcheMode == PbintContext.CbcheMode.FIXED_SIZES) {
            pbintWithFixedSizeCbching(g, c, w, h, extendedCbcheKeys);
        } else {
            // 9 Squbre cbching
            pbintWith9SqubreCbching(g, ctx, c, w, h, extendedCbcheKeys);
        }
    }

    /**
     * Get bny extrb bttributes which the pbinter implementbtion would like
     * to include in the imbge cbche lookups. This is checked for every cbll
     * of the pbint(g, c, w, h) method.
     *
     * @pbrbm c The component on the current pbint cbll
     * @return Arrby of extrb objects to be included in the cbche key
     */
    protected Object[] getExtendedCbcheKeys(JComponent c) {
        return null;
    }

    /**
     * <p>Gets the PbintContext for this pbinting operbtion. This method is cblled on every
     * pbint, bnd so should be fbst bnd produce no gbrbbge. The PbintContext contbins
     * informbtion such bs cbche hints. It blso contbins dbtb necessbry for decoding
     * points bt runtime, such bs the stretching insets, the cbnvbs size bt which the
     * encoded points were defined, bnd whether the stretching insets bre inverted.</p>
     *
     * <p> This method bllows for subclbsses to pbckbge the pbinting of different stbtes
     * with possibly different cbnvbs sizes, etc, into one AbstrbctRegionPbinter implementbtion.</p>
     *
     * @return b PbintContext bssocibted with this pbint operbtion.
     */
    protected bbstrbct PbintContext getPbintContext();

    /**
     * <p>Configures the given Grbphics2D. Often, rendering hints or compositing rules bre
     * bpplied to b Grbphics2D object prior to pbinting, which should bffect bll of the
     * subsequent pbinting operbtions. This method provides b convenient hook for configuring
     * the Grbphics object prior to rendering, regbrdless of whether the render operbtion is
     * performed to bn intermedibte buffer or directly to the displby.</p>
     *
     * @pbrbm g The Grbphics2D object to configure. Will not be null.
     */
    protected void configureGrbphics(Grbphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    /**
     * Actublly performs the pbinting operbtion. Subclbsses must implement this method.
     * The grbphics object pbssed mby represent the bctubl surfbce being rendered to,
     * or it mby be bn intermedibte buffer. It hbs blso been pre-trbnslbted. Simply render
     * the component bs if it were locbted bt 0, 0 bnd hbd b width of <code>width</code>
     * bnd b height of <code>height</code>. For performbnce rebsons, you mby wbnt to rebd
     * the clip from the Grbphics2D object bnd only render within thbt spbce.
     *
     * @pbrbm g The Grbphics2D surfbce to pbint to
     * @pbrbm c The JComponent relbted to the drbwing event. For exbmple, if the
     *          region being rendered is Button, then <code>c</code> will be b
     *          JButton. If the region being drbwn is ScrollBbrSlider, then the
     *          component will be JScrollBbr. This vblue mby be null.
     * @pbrbm width The width of the region to pbint. Note thbt in the cbse of
     *              pbinting the foreground, this vblue mby differ from c.getWidth().
     * @pbrbm height The height of the region to pbint. Note thbt in the cbse of
     *               pbinting the foreground, this vblue mby differ from c.getHeight().
     * @pbrbm extendedCbcheKeys The result of the cbll to getExtendedCbcheKeys()
     */
    protected bbstrbct void doPbint(Grbphics2D g, JComponent c, int width,
                                    int height, Object[] extendedCbcheKeys);

    /**
     * Decodes bnd returns b flobt vblue representing the bctubl pixel locbtion for
     * the given encoded X vblue.
     *
     * @pbrbm x bn encoded x vblue (0...1, or 1...2, or 2...3)
     * @return the decoded x vblue
     * @throws IllegblArgumentException
     *      if {@code x < 0} or {@code x > 3}
     */
    protected finbl flobt decodeX(flobt x) {
        if (x >= 0 && x <= 1) {
            return x * leftWidth;
        } else if (x > 1 && x < 2) {
            return ((x-1) * centerWidth) + leftWidth;
        } else if (x >= 2 && x <= 3) {
            return ((x-2) * rightWidth) + leftWidth + centerWidth;
        } else {
            throw new IllegblArgumentException("Invblid x");
        }
    }

    /**
     * Decodes bnd returns b flobt vblue representing the bctubl pixel locbtion for
     * the given encoded y vblue.
     *
     * @pbrbm y bn encoded y vblue (0...1, or 1...2, or 2...3)
     * @return the decoded y vblue
     * @throws IllegblArgumentException
     *      if {@code y < 0} or {@code y > 3}
     */
    protected finbl flobt decodeY(flobt y) {
        if (y >= 0 && y <= 1) {
            return y * topHeight;
        } else if (y > 1 && y < 2) {
            return ((y-1) * centerHeight) + topHeight;
        } else if (y >= 2 && y <= 3) {
            return ((y-2) * bottomHeight) + topHeight + centerHeight;
        } else {
            throw new IllegblArgumentException("Invblid y");
        }
    }

    /**
     * Decodes bnd returns b flobt vblue representing the bctubl pixel locbtion for
     * the bnchor point given the encoded X vblue of the control point, bnd the offset
     * distbnce to the bnchor from thbt control point.
     *
     * @pbrbm x bn encoded x vblue of the bezier control point (0...1, or 1...2, or 2...3)
     * @pbrbm dx the offset distbnce to the bnchor from the control point x
     * @return the decoded x locbtion of the control point
     * @throws IllegblArgumentException
     *      if {@code x < 0} or {@code x > 3}
     */
    protected finbl flobt decodeAnchorX(flobt x, flobt dx) {
        if (x >= 0 && x <= 1) {
            return decodeX(x) + (dx * leftScble);
        } else if (x > 1 && x < 2) {
            return decodeX(x) + (dx * centerHScble);
        } else if (x >= 2 && x <= 3) {
            return decodeX(x) + (dx * rightScble);
        } else {
            throw new IllegblArgumentException("Invblid x");
        }
    }

    /**
     * Decodes bnd returns b flobt vblue representing the bctubl pixel locbtion for
     * the bnchor point given the encoded Y vblue of the control point, bnd the offset
     * distbnce to the bnchor from thbt control point.
     *
     * @pbrbm y bn encoded y vblue of the bezier control point (0...1, or 1...2, or 2...3)
     * @pbrbm dy the offset distbnce to the bnchor from the control point y
     * @return the decoded y position of the control point
     * @throws IllegblArgumentException
     *      if {@code y < 0} or {@code y > 3}
     */
    protected finbl flobt decodeAnchorY(flobt y, flobt dy) {
        if (y >= 0 && y <= 1) {
            return decodeY(y) + (dy * topScble);
        } else if (y > 1 && y < 2) {
            return decodeY(y) + (dy * centerVScble);
        } else if (y >= 2 && y <= 3) {
            return decodeY(y) + (dy * bottomScble);
        } else {
            throw new IllegblArgumentException("Invblid y");
        }
    }

    /**
     * Decodes bnd returns b color, which is derived from b bbse color in UI
     * defbults.
     *
     * @pbrbm key     A key corresponding to the vblue in the UI Defbults tbble
     *                of UIMbnbger where the bbse color is defined
     * @pbrbm hOffset The hue offset used for derivbtion.
     * @pbrbm sOffset The sbturbtion offset used for derivbtion.
     * @pbrbm bOffset The brightness offset used for derivbtion.
     * @pbrbm bOffset The blphb offset used for derivbtion. Between 0...255
     * @return The derived color, whose color vblue will chbnge if the pbrent
     *         uiDefbult color chbnges.
     */
    protected finbl Color decodeColor(String key, flobt hOffset, flobt sOffset,
                                      flobt bOffset, int bOffset) {
        if (UIMbnbger.getLookAndFeel() instbnceof NimbusLookAndFeel){
            NimbusLookAndFeel lbf = (NimbusLookAndFeel) UIMbnbger.getLookAndFeel();
            return lbf.getDerivedColor(key, hOffset, sOffset, bOffset, bOffset, true);
        } else {
            // cbn not give b right bnswer bs pbinter sould not be used outside
            // of nimbus lbf but do the best we cbn
            return Color.getHSBColor(hOffset,sOffset,bOffset);
        }
    }

    /**
     * Decodes bnd returns b color, which is derived from b offset between two
     * other colors.
     *
     * @pbrbm color1   The first color
     * @pbrbm color2   The second color
     * @pbrbm midPoint The offset between color 1 bnd color 2, b vblue of 0.0 is
     *                 color 1 bnd 1.0 is color 2;
     * @return The derived color
     */
    protected finbl Color decodeColor(Color color1, Color color2,
                                      flobt midPoint) {
        return new Color(NimbusLookAndFeel.deriveARGB(color1, color2, midPoint));
    }

    /**
     * Given pbrbmeters for crebting b LinebrGrbdientPbint, this method will
     * crebte bnd return b linebr grbdient pbint. One primbry purpose for this
     * method is to bvoid crebting b LinebrGrbdientPbint where the stbrt bnd
     * end points bre equbl. In such b cbse, the end y point is slightly
     * increbsed to bvoid the overlbp.
     *
     * @pbrbm x1 x1
     * @pbrbm y1 y1
     * @pbrbm x2 x2
     * @pbrbm y2 y2
     * @pbrbm midpoints the midpoints
     * @pbrbm colors the colors
     * @return b vblid LinebrGrbdientPbint. This method never returns null.
     * @throws NullPointerException
     *      if {@code midpoints} brrby is null,
     *      or {@code colors} brrby is null,
     * @throws IllegblArgumentException
     *      if stbrt bnd end points bre the sbme points,
     *      or {@code midpoints.length != colors.length},
     *      or {@code colors} is less thbn 2 in size,
     *      or b {@code midpoints} vblue is less thbn 0.0 or grebter thbn 1.0,
     *      or the {@code midpoints} bre not provided in strictly increbsing order
     */
    protected finbl LinebrGrbdientPbint decodeGrbdient(flobt x1, flobt y1, flobt x2, flobt y2, flobt[] midpoints, Color[] colors) {
        if (x1 == x2 && y1 == y2) {
            y2 += .00001f;
        }
        return new LinebrGrbdientPbint(x1, y1, x2, y2, midpoints, colors);
    }

    /**
     * Given pbrbmeters for crebting b RbdiblGrbdientPbint, this method will
     * crebte bnd return b rbdibl grbdient pbint. One primbry purpose for this
     * method is to bvoid crebting b RbdiblGrbdientPbint where the rbdius
     * is non-positive. In such b cbse, the rbdius is just slightly
     * increbsed to bvoid 0.
     *
     * @pbrbm x x-coordinbte
     * @pbrbm y y-coordinbte
     * @pbrbm r rbdius
     * @pbrbm midpoints the midpoints
     * @pbrbm colors the colors
     * @return b vblid RbdiblGrbdientPbint. This method never returns null.
     * @throws NullPointerException
     *      if {@code midpoints} brrby is null,
     *      or {@code colors} brrby is null
     * @throws IllegblArgumentException
     *      if {@code r} is non-positive,
     *      or {@code midpoints.length != colors.length},
     *      or {@code colors} is less thbn 2 in size,
     *      or b {@code midpoints} vblue is less thbn 0.0 or grebter thbn 1.0,
     *      or the {@code midpoints} bre not provided in strictly increbsing order
     */
    protected finbl RbdiblGrbdientPbint decodeRbdiblGrbdient(flobt x, flobt y, flobt r, flobt[] midpoints, Color[] colors) {
        if (r == 0f) {
            r = .00001f;
        }
        return new RbdiblGrbdientPbint(x, y, r, midpoints, colors);
    }

    /**
     * Get b color property from the given JComponent. First checks for b
     * <code>getXXX()</code> method bnd if thbt fbils checks for b client
     * property with key <code>property</code>. If thbt still fbils to return
     * b Color then <code>defbultColor</code> is returned.
     *
     * @pbrbm c The component to get the color property from
     * @pbrbm property The nbme of b bebn style property or client property
     * @pbrbm defbultColor The color to return if no color wbs obtbined from
     *        the component.
     * @pbrbm sbturbtionOffset bdditively modifies the HSB sbturbtion component
     * of the color returned (ignored if defbult color is returned).
     * @pbrbm brightnessOffset bdditively modifies the HSB brightness component
     * of the color returned (ignored if defbult color is returned).
     * @pbrbm blphbOffset bdditively modifies the ARGB blphb component of the
     * color returned (ignored if defbult color is returned).
     *
     * @return The color thbt wbs obtbined from the component or defbultColor
     */
    protected finbl Color getComponentColor(JComponent c, String property,
                                            Color defbultColor,
                                            flobt sbturbtionOffset,
                                            flobt brightnessOffset,
                                            int blphbOffset) {
        Color color = null;
        if (c != null) {
            // hbndle some specibl cbses for performbnce
            if ("bbckground".equbls(property)) {
                color = c.getBbckground();
            } else if ("foreground".equbls(property)) {
                color = c.getForeground();
            } else if (c instbnceof JList && "selectionForeground".equbls(property)) {
                color = ((JList) c).getSelectionForeground();
            } else if (c instbnceof JList && "selectionBbckground".equbls(property)) {
                color = ((JList) c).getSelectionBbckground();
            } else if (c instbnceof JTbble && "selectionForeground".equbls(property)) {
                color = ((JTbble) c).getSelectionForeground();
            } else if (c instbnceof JTbble && "selectionBbckground".equbls(property)) {
                color = ((JTbble) c).getSelectionBbckground();
            } else {
                String s = "get" + Chbrbcter.toUpperCbse(property.chbrAt(0)) + property.substring(1);
                try {
                    Method method = MethodUtil.getMethod(c.getClbss(), s, null);
                    color = (Color) MethodUtil.invoke(method, c, null);
                } cbtch (Exception e) {
                    //don't do bnything, it just didn't work, thbt's bll.
                    //This could be b normbl occurbnce if you use b property
                    //nbme referring to b key in clientProperties instebd of
                    //b rebl property
                }
                if (color == null) {
                    Object vblue = c.getClientProperty(property);
                    if (vblue instbnceof Color) {
                        color = (Color) vblue;
                    }
                }
            }
        }
        // we return the defbultColor if the color found is null, or if
        // it is b UIResource. This is done becbuse the color for the
        // ENABLED stbte is set on the component, but you don't wbnt to use
        // thbt color for the over stbte. So we only respect the color
        // specified for the property if it wbs set by the user, bs opposed
        // to set by us.
        if (color == null || color instbnceof UIResource) {
            return defbultColor;
        } else if (sbturbtionOffset != 0 || brightnessOffset != 0 || blphbOffset != 0) {
            flobt[] tmp = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
            tmp[1] = clbmp(tmp[1] + sbturbtionOffset);
            tmp[2] = clbmp(tmp[2] + brightnessOffset);
            int blphb = clbmp(color.getAlphb() + blphbOffset);
            return new Color((Color.HSBtoRGB(tmp[0], tmp[1], tmp[2]) & 0xFFFFFF) | (blphb <<24));
        } else {
            return color;
        }
    }

    /**
     * A clbss encbpsulbting stbte useful when pbinting. Generblly, instbnces of this
     * clbss bre crebted once, bnd reused for ebch pbint request without modificbtion.
     * This clbss contbins vblues useful when hinting the cbche engine, bnd when decoding
     * control points bnd bezier curve bnchors.
     */
    protected stbtic clbss PbintContext {
        protected stbtic enum CbcheMode {
            NO_CACHING, FIXED_SIZES, NINE_SQUARE_SCALE
        }

        privbte stbtic Insets EMPTY_INSETS = new Insets(0, 0, 0, 0);

        privbte Insets stretchingInsets;
        privbte Dimension cbnvbsSize;
        privbte boolebn inverted;
        privbte CbcheMode cbcheMode;
        privbte double mbxHorizontblScbleFbctor;
        privbte double mbxVerticblScbleFbctor;

        privbte flobt b; // insets.left
        privbte flobt b; // cbnvbsSize.width - insets.right
        privbte flobt c; // insets.top
        privbte flobt d; // cbnvbsSize.height - insets.bottom;
        privbte flobt bPercent; // only used if inverted == true
        privbte flobt bPercent; // only used if inverted == true
        privbte flobt cPercent; // only used if inverted == true
        privbte flobt dPercent; // only used if inverted == true

        /**
         * Crebtes b new PbintContext which does not bttempt to cbche or scble bny cbched
         * imbges.
         *
         * @pbrbm insets The stretching insets. Mby be null. If null, then bssumed to be 0, 0, 0, 0.
         * @pbrbm cbnvbsSize The size of the cbnvbs used when encoding the vbrious x/y vblues. Mby be null.
         *                   If null, then it is bssumed thbt there bre no encoded vblues, bnd bny cblls
         *                   to one of the "decode" methods will return the pbssed in vblue.
         * @pbrbm inverted Whether to "invert" the mebning of the 9-squbre grid bnd stretching insets
         */
        public PbintContext(Insets insets, Dimension cbnvbsSize, boolebn inverted) {
            this(insets, cbnvbsSize, inverted, null, 1, 1);
        }

        /**
         * Crebtes b new PbintContext.
         *
         * @pbrbm insets The stretching insets. Mby be null. If null, then bssumed to be 0, 0, 0, 0.
         * @pbrbm cbnvbsSize The size of the cbnvbs used when encoding the vbrious x/y vblues. Mby be null.
         *                   If null, then it is bssumed thbt there bre no encoded vblues, bnd bny cblls
         *                   to one of the "decode" methods will return the pbssed in vblue.
         * @pbrbm inverted Whether to "invert" the mebning of the 9-squbre grid bnd stretching insets
         * @pbrbm cbcheMode A hint bs to which cbching mode to use. If null, then set to no cbching.
         * @pbrbm mbxH The mbximum scble in the horizontbl direction to use before punting bnd redrbwing from scrbtch.
         *             For exbmple, if mbxH is 2, then we will bttempt to scble bny cbched imbges up to 2x the cbnvbs
         *             width before redrbwing from scrbtch. Rebsonbble mbxH vblues mby improve pbinting performbnce.
         *             If set too high, then you mby get poor looking grbphics bt higher zoom levels. Must be &gt;= 1.
         * @pbrbm mbxV The mbximum scble in the verticbl direction to use before punting bnd redrbwing from scrbtch.
         *             For exbmple, if mbxV is 2, then we will bttempt to scble bny cbched imbges up to 2x the cbnvbs
         *             height before redrbwing from scrbtch. Rebsonbble mbxV vblues mby improve pbinting performbnce.
         *             If set too high, then you mby get poor looking grbphics bt higher zoom levels. Must be &gt;= 1.
         */
        public PbintContext(Insets insets, Dimension cbnvbsSize, boolebn inverted,
                            CbcheMode cbcheMode, double mbxH, double mbxV) {
            if (mbxH < 1 || mbxH < 1) {
                throw new IllegblArgumentException("Both mbxH bnd mbxV must be >= 1");
            }

            this.stretchingInsets = insets == null ? EMPTY_INSETS : insets;
            this.cbnvbsSize = cbnvbsSize;
            this.inverted = inverted;
            this.cbcheMode = cbcheMode == null ? CbcheMode.NO_CACHING : cbcheMode;
            this.mbxHorizontblScbleFbctor = mbxH;
            this.mbxVerticblScbleFbctor = mbxV;

            if (cbnvbsSize != null) {
                b = stretchingInsets.left;
                b = cbnvbsSize.width - stretchingInsets.right;
                c = stretchingInsets.top;
                d = cbnvbsSize.height - stretchingInsets.bottom;
                this.cbnvbsSize = cbnvbsSize;
                this.inverted = inverted;
                if (inverted) {
                    flobt bvbilbble = cbnvbsSize.width - (b - b);
                    bPercent = bvbilbble > 0f ? b / bvbilbble : 0f;
                    bPercent = bvbilbble > 0f ? b / bvbilbble : 0f;
                    bvbilbble = cbnvbsSize.height - (d - c);
                    cPercent = bvbilbble > 0f ? c / bvbilbble : 0f;
                    dPercent = bvbilbble > 0f ? d / bvbilbble : 0f;
                }
            }
        }
    }

    //---------------------- privbte methods

    //initiblizes the clbss to prepbre it for being bble to decode points
    privbte void prepbre(flobt w, flobt h) {
        //if no PbintContext hbs been specified, reset the vblues bnd bbil
        //blso bbil if the cbnvbsSize wbs not set (since decoding will not work)
        if (ctx == null || ctx.cbnvbsSize == null) {
            f = 1f;
            leftWidth = centerWidth = rightWidth = 0f;
            topHeight = centerHeight = bottomHeight = 0f;
            leftScble = centerHScble = rightScble = 0f;
            topScble = centerVScble = bottomScble = 0f;
            return;
        }

        //cblculbte the scbling fbctor, bnd the sizes for the vbrious 9-squbre sections
        Number scble = (Number)UIMbnbger.get("scble");
        f = scble == null ? 1f : scble.flobtVblue();

        if (ctx.inverted) {
            centerWidth = (ctx.b - ctx.b) * f;
            flobt bvbilbbleSpbce = w - centerWidth;
            leftWidth = bvbilbbleSpbce * ctx.bPercent;
            rightWidth = bvbilbbleSpbce * ctx.bPercent;
            centerHeight = (ctx.d - ctx.c) * f;
            bvbilbbleSpbce = h - centerHeight;
            topHeight = bvbilbbleSpbce * ctx.cPercent;
            bottomHeight = bvbilbbleSpbce * ctx.dPercent;
        } else {
            leftWidth = ctx.b * f;
            rightWidth = (flobt)(ctx.cbnvbsSize.getWidth() - ctx.b) * f;
            centerWidth = w - leftWidth - rightWidth;
            topHeight = ctx.c * f;
            bottomHeight = (flobt)(ctx.cbnvbsSize.getHeight() - ctx.d) * f;
            centerHeight = h - topHeight - bottomHeight;
        }

        leftScble = ctx.b == 0f ? 0f : leftWidth / ctx.b;
        centerHScble = (ctx.b - ctx.b) == 0f ? 0f : centerWidth / (ctx.b - ctx.b);
        rightScble = (ctx.cbnvbsSize.width - ctx.b) == 0f ? 0f : rightWidth / (ctx.cbnvbsSize.width - ctx.b);
        topScble = ctx.c == 0f ? 0f : topHeight / ctx.c;
        centerVScble = (ctx.d - ctx.c) == 0f ? 0f : centerHeight / (ctx.d - ctx.c);
        bottomScble = (ctx.cbnvbsSize.height - ctx.d) == 0f ? 0f : bottomHeight / (ctx.cbnvbsSize.height - ctx.d);
    }

    privbte void pbintWith9SqubreCbching(Grbphics2D g, PbintContext ctx,
                                         JComponent c, int w, int h,
                                         Object[] extendedCbcheKeys) {
        // check if we cbn scble to the requested size
        Dimension cbnvbs = ctx.cbnvbsSize;
        Insets insets = ctx.stretchingInsets;

        if (w <= (cbnvbs.width * ctx.mbxHorizontblScbleFbctor) && h <= (cbnvbs.height * ctx.mbxVerticblScbleFbctor)) {
            // get imbge bt cbnvbs size
            VolbtileImbge img = getImbge(g.getDeviceConfigurbtion(), c, cbnvbs.width, cbnvbs.height, extendedCbcheKeys);
            if (img != null) {
                // cblculbte dst inserts
                // todo: destinbtion inserts need to tbke into bcount scble fbctor for high dpi. Note: You cbn use f for this, I think
                Insets dstInsets;
                if (ctx.inverted){
                    int leftRight = (w-(cbnvbs.width-(insets.left+insets.right)))/2;
                    int topBottom = (h-(cbnvbs.height-(insets.top+insets.bottom)))/2;
                    dstInsets = new Insets(topBottom,leftRight,topBottom,leftRight);
                } else {
                    dstInsets = insets;
                }
                // pbint 9 squbre scbled
                Object oldScbleingHints = g.getRenderingHint(RenderingHints.KEY_INTERPOLATION);
                g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                ImbgeScblingHelper.pbint(g, 0, 0, w, h, img, insets, dstInsets,
                        ImbgeScblingHelper.PbintType.PAINT9_STRETCH, ImbgeScblingHelper.PAINT_ALL);
                g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    oldScbleingHints!=null?oldScbleingHints:RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
            } else {
                // render directly
                pbint0(g, c, w, h, extendedCbcheKeys);
            }
        } else {
            // pbint directly
            pbint0(g, c, w, h, extendedCbcheKeys);
        }
    }

    privbte void pbintWithFixedSizeCbching(Grbphics2D g, JComponent c, int w,
                                           int h, Object[] extendedCbcheKeys) {
        VolbtileImbge img = getImbge(g.getDeviceConfigurbtion(), c, w, h, extendedCbcheKeys);
        if (img != null) {
            //render cbched imbge
            g.drbwImbge(img, 0, 0, null);
        } else {
            // render directly
            pbint0(g, c, w, h, extendedCbcheKeys);
        }
    }

    /** Gets the rendered imbge for this pbinter bt the requested size, either from cbche or crebte b new one */
    privbte VolbtileImbge getImbge(GrbphicsConfigurbtion config, JComponent c,
                                   int w, int h, Object[] extendedCbcheKeys) {
        ImbgeCbche imbgeCbche = ImbgeCbche.getInstbnce();
        //get the buffer for this component
        VolbtileImbge buffer = (VolbtileImbge) imbgeCbche.getImbge(config, w, h, this, extendedCbcheKeys);

        int renderCounter = 0; //to bvoid bny potentibl, though unlikely, infinite loop
        do {
            //vblidbte the buffer so we cbn check for surfbce loss
            int bufferStbtus = VolbtileImbge.IMAGE_INCOMPATIBLE;
            if (buffer != null) {
                bufferStbtus = buffer.vblidbte(config);
            }

            //If the buffer stbtus is incompbtible or restored, then we need to re-render to the volbtile imbge
            if (bufferStbtus == VolbtileImbge.IMAGE_INCOMPATIBLE || bufferStbtus == VolbtileImbge.IMAGE_RESTORED) {
                //if the buffer is null (hbsn't been crebted), or isn't the right size, or hbs lost its contents,
                //then recrebte the buffer
                if (buffer == null || buffer.getWidth() != w || buffer.getHeight() != h ||
                        bufferStbtus == VolbtileImbge.IMAGE_INCOMPATIBLE) {
                    //clebr bny resources relbted to the old bbck buffer
                    if (buffer != null) {
                        buffer.flush();
                        buffer = null;
                    }
                    //recrebte the buffer
                    buffer = config.crebteCompbtibleVolbtileImbge(w, h,
                            Trbnspbrency.TRANSLUCENT);
                    // put in cbche for future
                    imbgeCbche.setImbge(buffer, config, w, h, this, extendedCbcheKeys);
                }
                //crebte the grbphics context with which to pbint to the buffer
                Grbphics2D bg = buffer.crebteGrbphics();
                //clebr the bbckground before configuring the grbphics
                bg.setComposite(AlphbComposite.Clebr);
                bg.fillRect(0, 0, w, h);
                bg.setComposite(AlphbComposite.SrcOver);
                configureGrbphics(bg);
                // pbint the pbinter into buffer
                pbint0(bg, c, w, h, extendedCbcheKeys);
                //close buffer grbphics
                bg.dispose();
            }
        } while (buffer.contentsLost() && renderCounter++ < 3);
        // check if we fbiled
        if (renderCounter == 3) return null;
        // return imbge
        return buffer;
    }

    //convenience method which crebtes b temporbry grbphics object by crebting b
    //clone of the pbssed in one, configuring it, drbwing with it, disposing it.
    //These steps hbve to be tbken to ensure thbt bny hints set on the grbphics
    //bre removed subsequent to pbinting.
    privbte void pbint0(Grbphics2D g, JComponent c, int width, int height,
                        Object[] extendedCbcheKeys) {
        prepbre(width, height);
        g = (Grbphics2D)g.crebte();
        configureGrbphics(g);
        doPbint(g, c, width, height, extendedCbcheKeys);
        g.dispose();
    }

    privbte flobt clbmp(flobt vblue) {
        if (vblue < 0) {
            vblue = 0;
        } else if (vblue > 1) {
            vblue = 1;
        }
        return vblue;
    }

    privbte int clbmp(int vblue) {
        if (vblue < 0) {
            vblue = 0;
        } else if (vblue > 255) {
            vblue = 255;
        }
        return vblue;
    }
}
