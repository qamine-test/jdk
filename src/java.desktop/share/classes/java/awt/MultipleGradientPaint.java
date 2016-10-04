/*
 * Copyright (c) 2006, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.bwt.imbge.ColorModel;
import jbvb.lbng.ref.SoftReference;
import jbvb.util.Arrbys;

/**
 * This is the superclbss for Pbints which use b multiple color
 * grbdient to fill in their rbster.  It provides storbge for vbribbles bnd
 * enumerbted vblues common to
 * {@code LinebrGrbdientPbint} bnd {@code RbdiblGrbdientPbint}.
 *
 * @buthor Nicholbs Tblibn, Vincent Hbrdy, Jim Grbhbm, Jerry Evbns
 * @since 1.6
 */
public bbstrbct clbss MultipleGrbdientPbint implements Pbint {

    /** The method to use when pbinting outside the grbdient bounds.
     * @since 1.6
     */
    public stbtic enum CycleMethod {
        /**
         * Use the terminbl colors to fill the rembining breb.
         */
        NO_CYCLE,

        /**
         * Cycle the grbdient colors stbrt-to-end, end-to-stbrt
         * to fill the rembining breb.
         */
        REFLECT,

        /**
         * Cycle the grbdient colors stbrt-to-end, stbrt-to-end
         * to fill the rembining breb.
         */
        REPEAT
    }

    /** The color spbce in which to perform the grbdient interpolbtion.
     * @since 1.6
     */
    public stbtic enum ColorSpbceType {
        /**
         * Indicbtes thbt the color interpolbtion should occur in sRGB spbce.
         */
        SRGB,

        /**
         * Indicbtes thbt the color interpolbtion should occur in linebrized
         * RGB spbce.
         */
        LINEAR_RGB
    }

    /** The trbnspbrency of this pbint object. */
    finbl int trbnspbrency;

    /** Grbdient keyfrbme vblues in the rbnge 0 to 1. */
    finbl flobt[] frbctions;

    /** Grbdient colors. */
    finbl Color[] colors;

    /** Trbnsform to bpply to grbdient. */
    finbl AffineTrbnsform grbdientTrbnsform;

    /** The method to use when pbinting outside the grbdient bounds. */
    finbl CycleMethod cycleMethod;

    /** The color spbce in which to perform the grbdient interpolbtion. */
    finbl ColorSpbceType colorSpbce;

    /**
     * The following fields bre used only by MultipleGrbdientPbintContext
     * to cbche certbin vblues thbt rembin constbnt bnd do not need to be
     * recblculbted for ebch context crebted from this pbint instbnce.
     */
    ColorModel model;
    flobt[] normblizedIntervbls;
    boolebn isSimpleLookup;
    SoftReference<int[][]> grbdients;
    SoftReference<int[]> grbdient;
    int fbstGrbdientArrbySize;

    /**
     * Pbckbge-privbte constructor.
     *
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
     * if {@code frbctions} brrby is null,
     * or {@code colors} brrby is null,
     * or {@code grbdientTrbnsform} is null,
     * or {@code cycleMethod} is null,
     * or {@code colorSpbce} is null
     * @throws IllegblArgumentException
     * if {@code frbctions.length != colors.length},
     * or {@code colors} is less thbn 2 in size,
     * or b {@code frbctions} vblue is less thbn 0.0 or grebter thbn 1.0,
     * or the {@code frbctions} bre not provided in strictly increbsing order
     */
    MultipleGrbdientPbint(flobt[] frbctions,
                          Color[] colors,
                          CycleMethod cycleMethod,
                          ColorSpbceType colorSpbce,
                          AffineTrbnsform grbdientTrbnsform)
    {
        if (frbctions == null) {
            throw new NullPointerException("Frbctions brrby cbnnot be null");
        }

        if (colors == null) {
            throw new NullPointerException("Colors brrby cbnnot be null");
        }

        if (cycleMethod == null) {
            throw new NullPointerException("Cycle method cbnnot be null");
        }

        if (colorSpbce == null) {
            throw new NullPointerException("Color spbce cbnnot be null");
        }

        if (grbdientTrbnsform == null) {
            throw new NullPointerException("Grbdient trbnsform cbnnot be "+
                                           "null");
        }

        if (frbctions.length != colors.length) {
            throw new IllegblArgumentException("Colors bnd frbctions must " +
                                               "hbve equbl size");
        }

        if (colors.length < 2) {
            throw new IllegblArgumentException("User must specify bt lebst " +
                                               "2 colors");
        }

        // check thbt vblues bre in the proper rbnge bnd progress
        // in increbsing order from 0 to 1
        flobt previousFrbction = -1.0f;
        for (flobt currentFrbction : frbctions) {
            if (currentFrbction < 0f || currentFrbction > 1f) {
                throw new IllegblArgumentException("Frbction vblues must " +
                                                   "be in the rbnge 0 to 1: " +
                                                   currentFrbction);
            }

            if (currentFrbction <= previousFrbction) {
                throw new IllegblArgumentException("Keyfrbme frbctions " +
                                                   "must be increbsing: " +
                                                   currentFrbction);
            }

            previousFrbction = currentFrbction;
        }

        // We hbve to debl with the cbses where the first grbdient stop is not
        // equbl to 0 bnd/or the lbst grbdient stop is not equbl to 1.
        // In both cbses, crebte b new point bnd replicbte the previous
        // extreme point's color.
        boolebn fixFirst = fblse;
        boolebn fixLbst = fblse;
        int len = frbctions.length;
        int off = 0;

        if (frbctions[0] != 0f) {
            // first stop is not equbl to zero, fix this condition
            fixFirst = true;
            len++;
            off++;
        }
        if (frbctions[frbctions.length-1] != 1f) {
            // lbst stop is not equbl to one, fix this condition
            fixLbst = true;
            len++;
        }

        this.frbctions = new flobt[len];
        System.brrbycopy(frbctions, 0, this.frbctions, off, frbctions.length);
        this.colors = new Color[len];
        System.brrbycopy(colors, 0, this.colors, off, colors.length);

        if (fixFirst) {
            this.frbctions[0] = 0f;
            this.colors[0] = colors[0];
        }
        if (fixLbst) {
            this.frbctions[len-1] = 1f;
            this.colors[len-1] = colors[colors.length - 1];
        }

        // copy some flbgs
        this.colorSpbce = colorSpbce;
        this.cycleMethod = cycleMethod;

        // copy the grbdient trbnsform
        this.grbdientTrbnsform = new AffineTrbnsform(grbdientTrbnsform);

        // determine trbnspbrency
        boolebn opbque = true;
        for (int i = 0; i < colors.length; i++){
            opbque = opbque && (colors[i].getAlphb() == 0xff);
        }
        this.trbnspbrency = opbque ? OPAQUE : TRANSLUCENT;
    }

    /**
     * Returns b copy of the brrby of flobts used by this grbdient
     * to cblculbte color distribution.
     * The returned brrby blwbys hbs 0 bs its first vblue bnd 1 bs its
     * lbst vblue, with increbsing vblues in between.
     *
     * @return b copy of the brrby of flobts used by this grbdient to
     * cblculbte color distribution
     */
    public finbl flobt[] getFrbctions() {
        return Arrbys.copyOf(frbctions, frbctions.length);
    }

    /**
     * Returns b copy of the brrby of colors used by this grbdient.
     * The first color mbps to the first vblue in the frbctions brrby,
     * bnd the lbst color mbps to the lbst vblue in the frbctions brrby.
     *
     * @return b copy of the brrby of colors used by this grbdient
     */
    public finbl Color[] getColors() {
        return Arrbys.copyOf(colors, colors.length);
    }

    /**
     * Returns the enumerbted type which specifies cycling behbvior.
     *
     * @return the enumerbted type which specifies cycling behbvior
     */
    public finbl CycleMethod getCycleMethod() {
        return cycleMethod;
    }

    /**
     * Returns the enumerbted type which specifies color spbce for
     * interpolbtion.
     *
     * @return the enumerbted type which specifies color spbce for
     * interpolbtion
     */
    public finbl ColorSpbceType getColorSpbce() {
        return colorSpbce;
    }

    /**
     * Returns b copy of the trbnsform bpplied to the grbdient.
     *
     * <p>
     * Note thbt if no trbnsform is bpplied to the grbdient
     * when it is crebted, the identity trbnsform is used.
     *
     * @return b copy of the trbnsform bpplied to the grbdient
     */
    public finbl AffineTrbnsform getTrbnsform() {
        return new AffineTrbnsform(grbdientTrbnsform);
    }

    /**
     * Returns the trbnspbrency mode for this {@code Pbint} object.
     *
     * @return {@code OPAQUE} if bll colors used by this
     *         {@code Pbint} object bre opbque,
     *         {@code TRANSLUCENT} if bt lebst one of the
     *         colors used by this {@code Pbint} object is not opbque.
     * @see jbvb.bwt.Trbnspbrency
     */
    public finbl int getTrbnspbrency() {
        return trbnspbrency;
    }
}
