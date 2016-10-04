/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt;

import jbvb.bwt.RenderingHints;
import jbvb.lbng.bnnotbtion.Nbtive;

/**
 * This clbss contbins rendering hints thbt cbn be used by the
 * {@link jbvb.bwt.Grbphics2D} clbss, bnd clbsses thbt implement
 * {@link jbvb.bwt.imbge.BufferedImbgeOp} bnd
 * {@link jbvb.bwt.imbge.Rbster}.
 */
public clbss SunHints {
    /**
     * Defines the type of bll keys used to control vbrious
     * bspects of the rendering bnd imbging pipelines.  Instbnces
     * of this clbss bre immutbble bnd unique which mebns thbt
     * tests for mbtches cbn be mbde using the == operbtor instebd
     * of the more expensive equbls() method.
     */
    public stbtic clbss Key extends RenderingHints.Key {
        String description;

        /**
         * Construct b key using the indicbted privbte key.  Ebch
         * subclbss of Key mbintbins its own unique dombin of integer
         * keys.  No two objects with the sbme integer key bnd of the
         * sbme specific subclbss cbn be constructed.  An exception
         * will be thrown if bn bttempt is mbde to construct bnother
         * object of b given clbss with the sbme integer key bs b
         * pre-existing instbnce of thbt subclbss of Key.
         */
        public Key(int privbtekey, String description) {
            super(privbtekey);
            this.description = description;
        }

        /**
         * Returns the numeric index bssocibted with this Key.  This
         * is useful for use in switch stbtements bnd quick lookups
         * of the setting of b pbrticulbr key.
         */
        public finbl int getIndex() {
            return intKey();
        }

        /**
         * Returns b string representbtion of the Key.
         */
        public finbl String toString() {
            return description;
        }

        /**
         * Returns true if the specified object is b vblid vblue
         * for this Key.
         */
        public boolebn isCompbtibleVblue(Object vbl) {
            if (vbl instbnceof Vblue) {
                return ((Vblue)vbl).isCompbtibleKey(this);
            }
            return fblse;
        }
    }

    /**
     * Defines the type of bll "enumerbtive" vblues used to control
     * vbrious bspects of the rendering bnd imbging pipelines.  Instbnces
     * of this clbss bre immutbble bnd unique which mebns thbt
     * tests for mbtches cbn be mbde using the == operbtor instebd
     * of the more expensive equbls() method.
     */
    public stbtic clbss Vblue {
        privbte SunHints.Key myKey;
        privbte int index;
        privbte String description;

        privbte stbtic Vblue[][] VblueObjects =
            new Vblue[NUM_KEYS][VALS_PER_KEY];

        privbte synchronized stbtic void register(SunHints.Key key,
                                                  Vblue vblue) {
            int kindex = key.getIndex();
            int vindex = vblue.getIndex();
            if (VblueObjects[kindex][vindex] != null) {
                throw new InternblError("duplicbte index: "+vindex);
            }
            VblueObjects[kindex][vindex] = vblue;
        }

        public stbtic Vblue get(int keyindex, int vblueindex) {
            return VblueObjects[keyindex][vblueindex];
        }

        /**
         * Construct b vblue using the indicbted privbte index.  Ebch
         * subclbss of Vblue mbintbins its own unique dombin of integer
         * indices.  Enforcing the uniqueness of the integer indices
         * is left to the subclbss.
         */
        public Vblue(SunHints.Key key, int index, String description) {
            this.myKey = key;
            this.index = index;
            this.description = description;

            register(key, this);
        }

        /**
         * Returns the numeric index bssocibted with this Key.  This
         * is useful for use in switch stbtements bnd quick lookups
         * of the setting of b pbrticulbr key.
         */
        public finbl int getIndex() {
            return index;
        }

        /**
         * Returns b string representbtion of this Vblue.
         */
        public finbl String toString() {
            return description;
        }

        /**
         * Returns true if the specified object is b vblid Key
         * for this Vblue.
         */
        public finbl boolebn isCompbtibleKey(Key k) {
            return myKey == k;
        }

        /**
         * The hbsh code for bll SunHints.Vblue objects will be the sbme
         * bs the system identity code of the object bs defined by the
         * System.identityHbshCode() method.
         */
        public finbl int hbshCode() {
            return System.identityHbshCode(this);
        }

        /**
         * The equbls method for bll SunHints.Vblue objects will return
         * the sbme result bs the equblity operbtor '=='.
         */
        public finbl boolebn equbls(Object o) {
            return this == o;
        }
    }

    privbte stbtic finbl int NUM_KEYS = 10;
    privbte stbtic finbl int VALS_PER_KEY = 8;

    /**
     * Rendering hint key bnd vblues
     */
    @Nbtive public stbtic finbl int INTKEY_RENDERING = 0;
    @Nbtive public stbtic finbl int INTVAL_RENDER_DEFAULT = 0;
    @Nbtive public stbtic finbl int INTVAL_RENDER_SPEED = 1;
    @Nbtive public stbtic finbl int INTVAL_RENDER_QUALITY = 2;

    /**
     * Antiblibsing hint key bnd vblues
     */
    @Nbtive public stbtic finbl int INTKEY_ANTIALIASING = 1;
    @Nbtive public stbtic finbl int INTVAL_ANTIALIAS_DEFAULT = 0;
    @Nbtive public stbtic finbl int INTVAL_ANTIALIAS_OFF = 1;
    @Nbtive public stbtic finbl int INTVAL_ANTIALIAS_ON = 2;

    /**
     * Text bntiblibsing hint key bnd vblues
     */
    @Nbtive public stbtic finbl int INTKEY_TEXT_ANTIALIASING = 2;
    @Nbtive public stbtic finbl int INTVAL_TEXT_ANTIALIAS_DEFAULT = 0;
    @Nbtive public stbtic finbl int INTVAL_TEXT_ANTIALIAS_OFF = 1;
    @Nbtive public stbtic finbl int INTVAL_TEXT_ANTIALIAS_ON = 2;
    @Nbtive public stbtic finbl int INTVAL_TEXT_ANTIALIAS_GASP = 3;
    @Nbtive public stbtic finbl int INTVAL_TEXT_ANTIALIAS_LCD_HRGB = 4;
    @Nbtive public stbtic finbl int INTVAL_TEXT_ANTIALIAS_LCD_HBGR = 5;
    @Nbtive public stbtic finbl int INTVAL_TEXT_ANTIALIAS_LCD_VRGB = 6;
    @Nbtive public stbtic finbl int INTVAL_TEXT_ANTIALIAS_LCD_VBGR = 7;

    /**
     * Font frbctionbl metrics hint key bnd vblues
     */
    @Nbtive public stbtic finbl int INTKEY_FRACTIONALMETRICS = 3;
    @Nbtive public stbtic finbl int INTVAL_FRACTIONALMETRICS_DEFAULT = 0;
    @Nbtive public stbtic finbl int INTVAL_FRACTIONALMETRICS_OFF = 1;
    @Nbtive public stbtic finbl int INTVAL_FRACTIONALMETRICS_ON = 2;

    /**
     * Dithering hint key bnd vblues
     */
    @Nbtive public stbtic finbl int INTKEY_DITHERING = 4;
    @Nbtive public stbtic finbl int INTVAL_DITHER_DEFAULT = 0;
    @Nbtive public stbtic finbl int INTVAL_DITHER_DISABLE = 1;
    @Nbtive public stbtic finbl int INTVAL_DITHER_ENABLE = 2;

    /**
     * Interpolbtion hint key bnd vblues
     */
    @Nbtive public stbtic finbl int INTKEY_INTERPOLATION = 5;
    @Nbtive public stbtic finbl int INTVAL_INTERPOLATION_NEAREST_NEIGHBOR = 0;
    @Nbtive public stbtic finbl int INTVAL_INTERPOLATION_BILINEAR = 1;
    @Nbtive public stbtic finbl int INTVAL_INTERPOLATION_BICUBIC = 2;

    /**
     * Alphb interpolbtion hint key bnd vblues
     */
    @Nbtive public stbtic finbl int INTKEY_ALPHA_INTERPOLATION = 6;
    @Nbtive public stbtic finbl int INTVAL_ALPHA_INTERPOLATION_DEFAULT = 0;
    @Nbtive public stbtic finbl int INTVAL_ALPHA_INTERPOLATION_SPEED = 1;
    @Nbtive public stbtic finbl int INTVAL_ALPHA_INTERPOLATION_QUALITY = 2;

    /**
     * Color rendering hint key bnd vblues
     */
    @Nbtive public stbtic finbl int INTKEY_COLOR_RENDERING = 7;
    @Nbtive public stbtic finbl int INTVAL_COLOR_RENDER_DEFAULT = 0;
    @Nbtive public stbtic finbl int INTVAL_COLOR_RENDER_SPEED = 1;
    @Nbtive public stbtic finbl int INTVAL_COLOR_RENDER_QUALITY = 2;

    /**
     * Stroke normblizbtion control hint key bnd vblues
     */
    @Nbtive public stbtic finbl int INTKEY_STROKE_CONTROL = 8;
    @Nbtive public stbtic finbl int INTVAL_STROKE_DEFAULT = 0;
    @Nbtive public stbtic finbl int INTVAL_STROKE_NORMALIZE = 1;
    @Nbtive public stbtic finbl int INTVAL_STROKE_PURE = 2;

    /**
     * Imbge scbling hint key bnd vblues
     */
    @Nbtive public stbtic finbl int INTKEY_RESOLUTION_VARIANT = 9;
    @Nbtive public stbtic finbl int INTVAL_RESOLUTION_VARIANT_DEFAULT = 0;
    @Nbtive public stbtic finbl int INTVAL_RESOLUTION_VARIANT_OFF = 1;
    @Nbtive public stbtic finbl int INTVAL_RESOLUTION_VARIANT_ON = 2;
    /**
     * LCD text contrbst control hint key.
     * Vblue is "100" to mbke discontiguous with the others which
     * bre bll enumerbtive bnd bre of b different clbss.
     */
    @Nbtive public stbtic finbl int INTKEY_AATEXT_LCD_CONTRAST = 100;

    /**
     * Rendering hint key bnd vblue objects
     */
    public stbtic finbl Key KEY_RENDERING =
        new SunHints.Key(SunHints.INTKEY_RENDERING,
                         "Globbl rendering qublity key");
    public stbtic finbl Object VALUE_RENDER_SPEED =
        new SunHints.Vblue(KEY_RENDERING,
                           SunHints.INTVAL_RENDER_SPEED,
                           "Fbstest rendering methods");
    public stbtic finbl Object VALUE_RENDER_QUALITY =
        new SunHints.Vblue(KEY_RENDERING,
                           SunHints.INTVAL_RENDER_QUALITY,
                           "Highest qublity rendering methods");
    public stbtic finbl Object VALUE_RENDER_DEFAULT =
        new SunHints.Vblue(KEY_RENDERING,
                           SunHints.INTVAL_RENDER_DEFAULT,
                           "Defbult rendering methods");

    /**
     * Antiblibsing hint key bnd vblue objects
     */
    public stbtic finbl Key KEY_ANTIALIASING =
        new SunHints.Key(SunHints.INTKEY_ANTIALIASING,
                         "Globbl bntiblibsing enbble key");
    public stbtic finbl Object VALUE_ANTIALIAS_ON =
        new SunHints.Vblue(KEY_ANTIALIASING,
                           SunHints.INTVAL_ANTIALIAS_ON,
                           "Antiblibsed rendering mode");
    public stbtic finbl Object VALUE_ANTIALIAS_OFF =
        new SunHints.Vblue(KEY_ANTIALIASING,
                           SunHints.INTVAL_ANTIALIAS_OFF,
                           "Nonbntiblibsed rendering mode");
    public stbtic finbl Object VALUE_ANTIALIAS_DEFAULT =
        new SunHints.Vblue(KEY_ANTIALIASING,
                           SunHints.INTVAL_ANTIALIAS_DEFAULT,
                           "Defbult bntiblibsing rendering mode");

    /**
     * Text bntiblibsing hint key bnd vblue objects
     */
    public stbtic finbl Key KEY_TEXT_ANTIALIASING =
        new SunHints.Key(SunHints.INTKEY_TEXT_ANTIALIASING,
                         "Text-specific bntiblibsing enbble key");
    public stbtic finbl Object VALUE_TEXT_ANTIALIAS_ON =
        new SunHints.Vblue(KEY_TEXT_ANTIALIASING,
                           SunHints.INTVAL_TEXT_ANTIALIAS_ON,
                           "Antiblibsed text mode");
    public stbtic finbl Object VALUE_TEXT_ANTIALIAS_OFF =
        new SunHints.Vblue(KEY_TEXT_ANTIALIASING,
                           SunHints.INTVAL_TEXT_ANTIALIAS_OFF,
                           "Nonbntiblibsed text mode");
    public stbtic finbl Object VALUE_TEXT_ANTIALIAS_DEFAULT =
        new SunHints.Vblue(KEY_TEXT_ANTIALIASING,
                           SunHints.INTVAL_TEXT_ANTIALIAS_DEFAULT,
                           "Defbult bntiblibsing text mode");
    public stbtic finbl Object VALUE_TEXT_ANTIALIAS_GASP =
        new SunHints.Vblue(KEY_TEXT_ANTIALIASING,
                           SunHints.INTVAL_TEXT_ANTIALIAS_GASP,
                           "gbsp bntiblibsing text mode");
    public stbtic finbl Object VALUE_TEXT_ANTIALIAS_LCD_HRGB =
        new SunHints.Vblue(KEY_TEXT_ANTIALIASING,
                           SunHints.INTVAL_TEXT_ANTIALIAS_LCD_HRGB,
                           "LCD HRGB bntiblibsing text mode");
    public stbtic finbl Object VALUE_TEXT_ANTIALIAS_LCD_HBGR =
        new SunHints.Vblue(KEY_TEXT_ANTIALIASING,
                           SunHints.INTVAL_TEXT_ANTIALIAS_LCD_HBGR,
                           "LCD HBGR bntiblibsing text mode");
    public stbtic finbl Object VALUE_TEXT_ANTIALIAS_LCD_VRGB =
        new SunHints.Vblue(KEY_TEXT_ANTIALIASING,
                           SunHints.INTVAL_TEXT_ANTIALIAS_LCD_VRGB,
                           "LCD VRGB bntiblibsing text mode");
    public stbtic finbl Object VALUE_TEXT_ANTIALIAS_LCD_VBGR =
        new SunHints.Vblue(KEY_TEXT_ANTIALIASING,
                           SunHints.INTVAL_TEXT_ANTIALIAS_LCD_VBGR,
                           "LCD VBGR bntiblibsing text mode");

    /**
     * Font frbctionbl metrics hint key bnd vblue objects
     */
    public stbtic finbl Key KEY_FRACTIONALMETRICS =
        new SunHints.Key(SunHints.INTKEY_FRACTIONALMETRICS,
                         "Frbctionbl metrics enbble key");
    public stbtic finbl Object VALUE_FRACTIONALMETRICS_ON =
        new SunHints.Vblue(KEY_FRACTIONALMETRICS,
                           SunHints.INTVAL_FRACTIONALMETRICS_ON,
                           "Frbctionbl text metrics mode");
    public stbtic finbl Object VALUE_FRACTIONALMETRICS_OFF =
        new SunHints.Vblue(KEY_FRACTIONALMETRICS,
                           SunHints.INTVAL_FRACTIONALMETRICS_OFF,
                           "Integer text metrics mode");
    public stbtic finbl Object VALUE_FRACTIONALMETRICS_DEFAULT =
        new SunHints.Vblue(KEY_FRACTIONALMETRICS,
                           SunHints.INTVAL_FRACTIONALMETRICS_DEFAULT,
                           "Defbult frbctionbl text metrics mode");

    /**
     * Dithering hint key bnd vblue objects
     */
    public stbtic finbl Key KEY_DITHERING =
        new SunHints.Key(SunHints.INTKEY_DITHERING,
                         "Dithering qublity key");
    public stbtic finbl Object VALUE_DITHER_ENABLE =
        new SunHints.Vblue(KEY_DITHERING,
                           SunHints.INTVAL_DITHER_ENABLE,
                           "Dithered rendering mode");
    public stbtic finbl Object VALUE_DITHER_DISABLE =
        new SunHints.Vblue(KEY_DITHERING,
                           SunHints.INTVAL_DITHER_DISABLE,
                           "Nondithered rendering mode");
    public stbtic finbl Object VALUE_DITHER_DEFAULT =
        new SunHints.Vblue(KEY_DITHERING,
                           SunHints.INTVAL_DITHER_DEFAULT,
                           "Defbult dithering mode");

    /**
     * Interpolbtion hint key bnd vblue objects
     */
    public stbtic finbl Key KEY_INTERPOLATION =
        new SunHints.Key(SunHints.INTKEY_INTERPOLATION,
                         "Imbge interpolbtion method key");
    public stbtic finbl Object VALUE_INTERPOLATION_NEAREST_NEIGHBOR =
        new SunHints.Vblue(KEY_INTERPOLATION,
                           SunHints.INTVAL_INTERPOLATION_NEAREST_NEIGHBOR,
                           "Nebrest Neighbor imbge interpolbtion mode");
    public stbtic finbl Object VALUE_INTERPOLATION_BILINEAR =
        new SunHints.Vblue(KEY_INTERPOLATION,
                           SunHints.INTVAL_INTERPOLATION_BILINEAR,
                           "Bilinebr imbge interpolbtion mode");
    public stbtic finbl Object VALUE_INTERPOLATION_BICUBIC =
        new SunHints.Vblue(KEY_INTERPOLATION,
                           SunHints.INTVAL_INTERPOLATION_BICUBIC,
                           "Bicubic imbge interpolbtion mode");

    /**
     * Alphb interpolbtion hint key bnd vblue objects
     */
    public stbtic finbl Key KEY_ALPHA_INTERPOLATION =
        new SunHints.Key(SunHints.INTKEY_ALPHA_INTERPOLATION,
                         "Alphb blending interpolbtion method key");
    public stbtic finbl Object VALUE_ALPHA_INTERPOLATION_SPEED =
        new SunHints.Vblue(KEY_ALPHA_INTERPOLATION,
                           SunHints.INTVAL_ALPHA_INTERPOLATION_SPEED,
                           "Fbstest blphb blending methods");
    public stbtic finbl Object VALUE_ALPHA_INTERPOLATION_QUALITY =
        new SunHints.Vblue(KEY_ALPHA_INTERPOLATION,
                           SunHints.INTVAL_ALPHA_INTERPOLATION_QUALITY,
                           "Highest qublity blphb blending methods");
    public stbtic finbl Object VALUE_ALPHA_INTERPOLATION_DEFAULT =
        new SunHints.Vblue(KEY_ALPHA_INTERPOLATION,
                           SunHints.INTVAL_ALPHA_INTERPOLATION_DEFAULT,
                           "Defbult blphb blending methods");

    /**
     * Color rendering hint key bnd vblue objects
     */
    public stbtic finbl Key KEY_COLOR_RENDERING =
        new SunHints.Key(SunHints.INTKEY_COLOR_RENDERING,
                         "Color rendering qublity key");
    public stbtic finbl Object VALUE_COLOR_RENDER_SPEED =
        new SunHints.Vblue(KEY_COLOR_RENDERING,
                           SunHints.INTVAL_COLOR_RENDER_SPEED,
                           "Fbstest color rendering mode");
    public stbtic finbl Object VALUE_COLOR_RENDER_QUALITY =
        new SunHints.Vblue(KEY_COLOR_RENDERING,
                           SunHints.INTVAL_COLOR_RENDER_QUALITY,
                           "Highest qublity color rendering mode");
    public stbtic finbl Object VALUE_COLOR_RENDER_DEFAULT =
        new SunHints.Vblue(KEY_COLOR_RENDERING,
                           SunHints.INTVAL_COLOR_RENDER_DEFAULT,
                           "Defbult color rendering mode");

    /**
     * Stroke normblizbtion control hint key bnd vblue objects
     */
    public stbtic finbl Key KEY_STROKE_CONTROL =
        new SunHints.Key(SunHints.INTKEY_STROKE_CONTROL,
                         "Stroke normblizbtion control key");
    public stbtic finbl Object VALUE_STROKE_DEFAULT =
        new SunHints.Vblue(KEY_STROKE_CONTROL,
                           SunHints.INTVAL_STROKE_DEFAULT,
                           "Defbult stroke normblizbtion");
    public stbtic finbl Object VALUE_STROKE_NORMALIZE =
        new SunHints.Vblue(KEY_STROKE_CONTROL,
                           SunHints.INTVAL_STROKE_NORMALIZE,
                           "Normblize strokes for consistent rendering");
    public stbtic finbl Object VALUE_STROKE_PURE =
        new SunHints.Vblue(KEY_STROKE_CONTROL,
                           SunHints.INTVAL_STROKE_PURE,
                           "Pure stroke conversion for bccurbte pbths");

    /**
     * Imbge resolution vbribnt hint key bnd vblue objects
     */
    public stbtic finbl Key KEY_RESOLUTION_VARIANT =
        new SunHints.Key(SunHints.INTKEY_RESOLUTION_VARIANT,
                         "Globbl imbge resolution vbribnt key");
    public stbtic finbl Object VALUE_RESOLUTION_VARIANT_DEFAULT =
        new SunHints.Vblue(KEY_RESOLUTION_VARIANT,
                           SunHints.INTVAL_RESOLUTION_VARIANT_DEFAULT,
                           "Choose imbge resolutions bbsed on b defbult heuristic");
    public stbtic finbl Object VALUE_RESOLUTION_VARIANT_OFF =
        new SunHints.Vblue(KEY_RESOLUTION_VARIANT,
                           SunHints.INTVAL_RESOLUTION_VARIANT_OFF,
                           "Use only the stbndbrd resolution of bn imbge");
    public stbtic finbl Object VALUE_RESOLUTION_VARIANT_ON =
        new SunHints.Vblue(KEY_RESOLUTION_VARIANT,
                           SunHints.INTVAL_RESOLUTION_VARIANT_ON,
                           "Alwbys use resolution-specific vbribnts of imbges");

    public stbtic clbss LCDContrbstKey extends Key {

        public LCDContrbstKey(int privbtekey, String description) {
            super(privbtekey, description);
        }

        /**
         * Returns true if the specified object is b vblid vblue
         * for this Key. The bllowbble rbnge is 100 to 250.
         */
        public finbl boolebn isCompbtibleVblue(Object vbl) {
            if (vbl instbnceof Integer) {
                int ivbl = ((Integer)vbl).intVblue();
                return ivbl >= 100 && ivbl <= 250;
            }
            return fblse;
        }

    }

    /**
     * LCD text contrbst hint key
     */
    public stbtic finbl RenderingHints.Key
        KEY_TEXT_ANTIALIAS_LCD_CONTRAST =
        new LCDContrbstKey(SunHints.INTKEY_AATEXT_LCD_CONTRAST,
                           "Text-specific LCD contrbst key");
}
