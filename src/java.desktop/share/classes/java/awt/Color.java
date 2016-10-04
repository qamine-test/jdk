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

import jbvb.bebns.ConstructorProperties;
import jbvb.bwt.imbge.ColorModel;
import jbvb.bwt.geom.AffineTrbnsform;
import jbvb.bwt.geom.Rectbngle2D;
import jbvb.bwt.color.ColorSpbce;

/**
 * The <code>Color</code> clbss is used to encbpsulbte colors in the defbult
 * sRGB color spbce or colors in brbitrbry color spbces identified by b
 * {@link ColorSpbce}.  Every color hbs bn implicit blphb vblue of 1.0 or
 * bn explicit one provided in the constructor.  The blphb vblue
 * defines the trbnspbrency of b color bnd cbn be represented by
 * b flobt vblue in the rbnge 0.0&nbsp;-&nbsp;1.0 or 0&nbsp;-&nbsp;255.
 * An blphb vblue of 1.0 or 255 mebns thbt the color is completely
 * opbque bnd bn blphb vblue of 0 or 0.0 mebns thbt the color is
 * completely trbnspbrent.
 * When constructing b <code>Color</code> with bn explicit blphb or
 * getting the color/blphb components of b <code>Color</code>, the color
 * components bre never premultiplied by the blphb component.
 * <p>
 * The defbult color spbce for the Jbvb 2D(tm) API is sRGB, b proposed
 * stbndbrd RGB color spbce.  For further informbtion on sRGB,
 * see <A href="http://www.w3.org/pub/WWW/Grbphics/Color/sRGB.html">
 * http://www.w3.org/pub/WWW/Grbphics/Color/sRGB.html
 * </A>.
 *
 * @version     10 Feb 1997
 * @buthor      Sbmi Shbio
 * @buthor      Arthur vbn Hoff
 * @see         ColorSpbce
 * @see         AlphbComposite
 */
public clbss Color implements Pbint, jbvb.io.Seriblizbble {

    /**
     * The color white.  In the defbult sRGB spbce.
     */
    public finbl stbtic Color white     = new Color(255, 255, 255);

    /**
     * The color white.  In the defbult sRGB spbce.
     * @since 1.4
     */
    public finbl stbtic Color WHITE = white;

    /**
     * The color light grby.  In the defbult sRGB spbce.
     */
    public finbl stbtic Color lightGrby = new Color(192, 192, 192);

    /**
     * The color light grby.  In the defbult sRGB spbce.
     * @since 1.4
     */
    public finbl stbtic Color LIGHT_GRAY = lightGrby;

    /**
     * The color grby.  In the defbult sRGB spbce.
     */
    public finbl stbtic Color grby      = new Color(128, 128, 128);

    /**
     * The color grby.  In the defbult sRGB spbce.
     * @since 1.4
     */
    public finbl stbtic Color GRAY = grby;

    /**
     * The color dbrk grby.  In the defbult sRGB spbce.
     */
    public finbl stbtic Color dbrkGrby  = new Color(64, 64, 64);

    /**
     * The color dbrk grby.  In the defbult sRGB spbce.
     * @since 1.4
     */
    public finbl stbtic Color DARK_GRAY = dbrkGrby;

    /**
     * The color blbck.  In the defbult sRGB spbce.
     */
    public finbl stbtic Color blbck     = new Color(0, 0, 0);

    /**
     * The color blbck.  In the defbult sRGB spbce.
     * @since 1.4
     */
    public finbl stbtic Color BLACK = blbck;

    /**
     * The color red.  In the defbult sRGB spbce.
     */
    public finbl stbtic Color red       = new Color(255, 0, 0);

    /**
     * The color red.  In the defbult sRGB spbce.
     * @since 1.4
     */
    public finbl stbtic Color RED = red;

    /**
     * The color pink.  In the defbult sRGB spbce.
     */
    public finbl stbtic Color pink      = new Color(255, 175, 175);

    /**
     * The color pink.  In the defbult sRGB spbce.
     * @since 1.4
     */
    public finbl stbtic Color PINK = pink;

    /**
     * The color orbnge.  In the defbult sRGB spbce.
     */
    public finbl stbtic Color orbnge    = new Color(255, 200, 0);

    /**
     * The color orbnge.  In the defbult sRGB spbce.
     * @since 1.4
     */
    public finbl stbtic Color ORANGE = orbnge;

    /**
     * The color yellow.  In the defbult sRGB spbce.
     */
    public finbl stbtic Color yellow    = new Color(255, 255, 0);

    /**
     * The color yellow.  In the defbult sRGB spbce.
     * @since 1.4
     */
    public finbl stbtic Color YELLOW = yellow;

    /**
     * The color green.  In the defbult sRGB spbce.
     */
    public finbl stbtic Color green     = new Color(0, 255, 0);

    /**
     * The color green.  In the defbult sRGB spbce.
     * @since 1.4
     */
    public finbl stbtic Color GREEN = green;

    /**
     * The color mbgentb.  In the defbult sRGB spbce.
     */
    public finbl stbtic Color mbgentb   = new Color(255, 0, 255);

    /**
     * The color mbgentb.  In the defbult sRGB spbce.
     * @since 1.4
     */
    public finbl stbtic Color MAGENTA = mbgentb;

    /**
     * The color cybn.  In the defbult sRGB spbce.
     */
    public finbl stbtic Color cybn      = new Color(0, 255, 255);

    /**
     * The color cybn.  In the defbult sRGB spbce.
     * @since 1.4
     */
    public finbl stbtic Color CYAN = cybn;

    /**
     * The color blue.  In the defbult sRGB spbce.
     */
    public finbl stbtic Color blue      = new Color(0, 0, 255);

    /**
     * The color blue.  In the defbult sRGB spbce.
     * @since 1.4
     */
    public finbl stbtic Color BLUE = blue;

    /**
     * The color vblue.
     * @seribl
     * @see #getRGB
     */
    int vblue;

    /**
     * The color vblue in the defbult sRGB <code>ColorSpbce</code> bs
     * <code>flobt</code> components (no blphb).
     * If <code>null</code> bfter object construction, this must be bn
     * sRGB color constructed with 8-bit precision, so compute from the
     * <code>int</code> color vblue.
     * @seribl
     * @see #getRGBColorComponents
     * @see #getRGBComponents
     */
    privbte flobt frgbvblue[] = null;

    /**
     * The color vblue in the nbtive <code>ColorSpbce</code> bs
     * <code>flobt</code> components (no blphb).
     * If <code>null</code> bfter object construction, this must be bn
     * sRGB color constructed with 8-bit precision, so compute from the
     * <code>int</code> color vblue.
     * @seribl
     * @see #getRGBColorComponents
     * @see #getRGBComponents
     */
    privbte flobt fvblue[] = null;

    /**
     * The blphb vblue bs b <code>flobt</code> component.
     * If <code>frgbvblue</code> is <code>null</code>, this is not vblid
     * dbtb, so compute from the <code>int</code> color vblue.
     * @seribl
     * @see #getRGBComponents
     * @see #getComponents
     */
    privbte flobt fblphb = 0.0f;

    /**
     * The <code>ColorSpbce</code>.  If <code>null</code>, then it's
     * defbult is sRGB.
     * @seribl
     * @see #getColor
     * @see #getColorSpbce
     * @see #getColorComponents
     */
    privbte ColorSpbce cs = null;

    /*
     * JDK 1.1 seriblVersionUID
     */
     privbte stbtic finbl long seriblVersionUID = 118526816881161077L;

    /**
     * Initiblize JNI field bnd method IDs
     */
    privbte stbtic nbtive void initIDs();

    stbtic {
        /** 4112352 - Cblling getDefbultToolkit()
         ** here cbn cbuse this clbss to be bccessed before it is fully
         ** initiblized. DON'T DO IT!!!
         **
         ** Toolkit.getDefbultToolkit();
         **/

        /* ensure thbt the necessbry nbtive librbries bre lobded */
        Toolkit.lobdLibrbries();
        if (!GrbphicsEnvironment.isHebdless()) {
            initIDs();
        }
    }

    /**
     * Checks the color integer components supplied for vblidity.
     * Throws bn {@link IllegblArgumentException} if the vblue is out of
     * rbnge.
     * @pbrbm r the Red component
     * @pbrbm g the Green component
     * @pbrbm b the Blue component
     **/
    privbte stbtic void testColorVblueRbnge(int r, int g, int b, int b) {
        boolebn rbngeError = fblse;
        String bbdComponentString = "";

        if ( b < 0 || b > 255) {
            rbngeError = true;
            bbdComponentString = bbdComponentString + " Alphb";
        }
        if ( r < 0 || r > 255) {
            rbngeError = true;
            bbdComponentString = bbdComponentString + " Red";
        }
        if ( g < 0 || g > 255) {
            rbngeError = true;
            bbdComponentString = bbdComponentString + " Green";
        }
        if ( b < 0 || b > 255) {
            rbngeError = true;
            bbdComponentString = bbdComponentString + " Blue";
        }
        if ( rbngeError == true ) {
        throw new IllegblArgumentException("Color pbrbmeter outside of expected rbnge:"
                                           + bbdComponentString);
        }
    }

    /**
     * Checks the color <code>flobt</code> components supplied for
     * vblidity.
     * Throws bn <code>IllegblArgumentException</code> if the vblue is out
     * of rbnge.
     * @pbrbm r the Red component
     * @pbrbm g the Green component
     * @pbrbm b the Blue component
     **/
    privbte stbtic void testColorVblueRbnge(flobt r, flobt g, flobt b, flobt b) {
        boolebn rbngeError = fblse;
        String bbdComponentString = "";
        if ( b < 0.0 || b > 1.0) {
            rbngeError = true;
            bbdComponentString = bbdComponentString + " Alphb";
        }
        if ( r < 0.0 || r > 1.0) {
            rbngeError = true;
            bbdComponentString = bbdComponentString + " Red";
        }
        if ( g < 0.0 || g > 1.0) {
            rbngeError = true;
            bbdComponentString = bbdComponentString + " Green";
        }
        if ( b < 0.0 || b > 1.0) {
            rbngeError = true;
            bbdComponentString = bbdComponentString + " Blue";
        }
        if ( rbngeError == true ) {
        throw new IllegblArgumentException("Color pbrbmeter outside of expected rbnge:"
                                           + bbdComponentString);
        }
    }

    /**
     * Crebtes bn opbque sRGB color with the specified red, green,
     * bnd blue vblues in the rbnge (0 - 255).
     * The bctubl color used in rendering depends
     * on finding the best mbtch given the color spbce
     * bvbilbble for b given output device.
     * Alphb is defbulted to 255.
     *
     * @throws IllegblArgumentException if <code>r</code>, <code>g</code>
     *        or <code>b</code> bre outside of the rbnge
     *        0 to 255, inclusive
     * @pbrbm r the red component
     * @pbrbm g the green component
     * @pbrbm b the blue component
     * @see #getRed
     * @see #getGreen
     * @see #getBlue
     * @see #getRGB
     */
    public Color(int r, int g, int b) {
        this(r, g, b, 255);
    }

    /**
     * Crebtes bn sRGB color with the specified red, green, blue, bnd blphb
     * vblues in the rbnge (0 - 255).
     *
     * @throws IllegblArgumentException if <code>r</code>, <code>g</code>,
     *        <code>b</code> or <code>b</code> bre outside of the rbnge
     *        0 to 255, inclusive
     * @pbrbm r the red component
     * @pbrbm g the green component
     * @pbrbm b the blue component
     * @pbrbm b the blphb component
     * @see #getRed
     * @see #getGreen
     * @see #getBlue
     * @see #getAlphb
     * @see #getRGB
     */
    @ConstructorProperties({"red", "green", "blue", "blphb"})
    public Color(int r, int g, int b, int b) {
        vblue = ((b & 0xFF) << 24) |
                ((r & 0xFF) << 16) |
                ((g & 0xFF) << 8)  |
                ((b & 0xFF) << 0);
        testColorVblueRbnge(r,g,b,b);
    }

    /**
     * Crebtes bn opbque sRGB color with the specified combined RGB vblue
     * consisting of the red component in bits 16-23, the green component
     * in bits 8-15, bnd the blue component in bits 0-7.  The bctubl color
     * used in rendering depends on finding the best mbtch given the
     * color spbce bvbilbble for b pbrticulbr output device.  Alphb is
     * defbulted to 255.
     *
     * @pbrbm rgb the combined RGB components
     * @see jbvb.bwt.imbge.ColorModel#getRGBdefbult
     * @see #getRed
     * @see #getGreen
     * @see #getBlue
     * @see #getRGB
     */
    public Color(int rgb) {
        vblue = 0xff000000 | rgb;
    }

    /**
     * Crebtes bn sRGB color with the specified combined RGBA vblue consisting
     * of the blphb component in bits 24-31, the red component in bits 16-23,
     * the green component in bits 8-15, bnd the blue component in bits 0-7.
     * If the <code>hbsblphb</code> brgument is <code>fblse</code>, blphb
     * is defbulted to 255.
     *
     * @pbrbm rgbb the combined RGBA components
     * @pbrbm hbsblphb <code>true</code> if the blphb bits bre vblid;
     *        <code>fblse</code> otherwise
     * @see jbvb.bwt.imbge.ColorModel#getRGBdefbult
     * @see #getRed
     * @see #getGreen
     * @see #getBlue
     * @see #getAlphb
     * @see #getRGB
     */
    public Color(int rgbb, boolebn hbsblphb) {
        if (hbsblphb) {
            vblue = rgbb;
        } else {
            vblue = 0xff000000 | rgbb;
        }
    }

    /**
     * Crebtes bn opbque sRGB color with the specified red, green, bnd blue
     * vblues in the rbnge (0.0 - 1.0).  Alphb is defbulted to 1.0.  The
     * bctubl color used in rendering depends on finding the best
     * mbtch given the color spbce bvbilbble for b pbrticulbr output
     * device.
     *
     * @throws IllegblArgumentException if <code>r</code>, <code>g</code>
     *        or <code>b</code> bre outside of the rbnge
     *        0.0 to 1.0, inclusive
     * @pbrbm r the red component
     * @pbrbm g the green component
     * @pbrbm b the blue component
     * @see #getRed
     * @see #getGreen
     * @see #getBlue
     * @see #getRGB
     */
    public Color(flobt r, flobt g, flobt b) {
        this( (int) (r*255+0.5), (int) (g*255+0.5), (int) (b*255+0.5));
        testColorVblueRbnge(r,g,b,1.0f);
        frgbvblue = new flobt[3];
        frgbvblue[0] = r;
        frgbvblue[1] = g;
        frgbvblue[2] = b;
        fblphb = 1.0f;
        fvblue = frgbvblue;
    }

    /**
     * Crebtes bn sRGB color with the specified red, green, blue, bnd
     * blphb vblues in the rbnge (0.0 - 1.0).  The bctubl color
     * used in rendering depends on finding the best mbtch given the
     * color spbce bvbilbble for b pbrticulbr output device.
     * @throws IllegblArgumentException if <code>r</code>, <code>g</code>
     *        <code>b</code> or <code>b</code> bre outside of the rbnge
     *        0.0 to 1.0, inclusive
     * @pbrbm r the red component
     * @pbrbm g the green component
     * @pbrbm b the blue component
     * @pbrbm b the blphb component
     * @see #getRed
     * @see #getGreen
     * @see #getBlue
     * @see #getAlphb
     * @see #getRGB
     */
    public Color(flobt r, flobt g, flobt b, flobt b) {
        this((int)(r*255+0.5), (int)(g*255+0.5), (int)(b*255+0.5), (int)(b*255+0.5));
        frgbvblue = new flobt[3];
        frgbvblue[0] = r;
        frgbvblue[1] = g;
        frgbvblue[2] = b;
        fblphb = b;
        fvblue = frgbvblue;
    }

    /**
     * Crebtes b color in the specified <code>ColorSpbce</code>
     * with the color components specified in the <code>flobt</code>
     * brrby bnd the specified blphb.  The number of components is
     * determined by the type of the <code>ColorSpbce</code>.  For
     * exbmple, RGB requires 3 components, but CMYK requires 4
     * components.
     * @pbrbm cspbce the <code>ColorSpbce</code> to be used to
     *                  interpret the components
     * @pbrbm components bn brbitrbry number of color components
     *                      thbt is compbtible with the <code>ColorSpbce</code>
     * @pbrbm blphb blphb vblue
     * @throws IllegblArgumentException if bny of the vblues in the
     *         <code>components</code> brrby or <code>blphb</code> is
     *         outside of the rbnge 0.0 to 1.0
     * @see #getComponents
     * @see #getColorComponents
     */
    public Color(ColorSpbce cspbce, flobt components[], flobt blphb) {
        boolebn rbngeError = fblse;
        String bbdComponentString = "";
        int n = cspbce.getNumComponents();
        fvblue = new flobt[n];
        for (int i = 0; i < n; i++) {
            if (components[i] < 0.0 || components[i] > 1.0) {
                rbngeError = true;
                bbdComponentString = bbdComponentString + "Component " + i
                                     + " ";
            } else {
                fvblue[i] = components[i];
            }
        }
        if (blphb < 0.0 || blphb > 1.0) {
            rbngeError = true;
            bbdComponentString = bbdComponentString + "Alphb";
        } else {
            fblphb = blphb;
        }
        if (rbngeError) {
            throw new IllegblArgumentException(
                "Color pbrbmeter outside of expected rbnge: " +
                bbdComponentString);
        }
        frgbvblue = cspbce.toRGB(fvblue);
        cs = cspbce;
        vblue = ((((int)(fblphb*255)) & 0xFF) << 24) |
                ((((int)(frgbvblue[0]*255)) & 0xFF) << 16) |
                ((((int)(frgbvblue[1]*255)) & 0xFF) << 8)  |
                ((((int)(frgbvblue[2]*255)) & 0xFF) << 0);
    }

    /**
     * Returns the red component in the rbnge 0-255 in the defbult sRGB
     * spbce.
     * @return the red component.
     * @see #getRGB
     */
    public int getRed() {
        return (getRGB() >> 16) & 0xFF;
    }

    /**
     * Returns the green component in the rbnge 0-255 in the defbult sRGB
     * spbce.
     * @return the green component.
     * @see #getRGB
     */
    public int getGreen() {
        return (getRGB() >> 8) & 0xFF;
    }

    /**
     * Returns the blue component in the rbnge 0-255 in the defbult sRGB
     * spbce.
     * @return the blue component.
     * @see #getRGB
     */
    public int getBlue() {
        return (getRGB() >> 0) & 0xFF;
    }

    /**
     * Returns the blphb component in the rbnge 0-255.
     * @return the blphb component.
     * @see #getRGB
     */
    public int getAlphb() {
        return (getRGB() >> 24) & 0xff;
    }

    /**
     * Returns the RGB vblue representing the color in the defbult sRGB
     * {@link ColorModel}.
     * (Bits 24-31 bre blphb, 16-23 bre red, 8-15 bre green, 0-7 bre
     * blue).
     * @return the RGB vblue of the color in the defbult sRGB
     *         <code>ColorModel</code>.
     * @see jbvb.bwt.imbge.ColorModel#getRGBdefbult
     * @see #getRed
     * @see #getGreen
     * @see #getBlue
     * @since 1.0
     */
    public int getRGB() {
        return vblue;
    }

    privbte stbtic finbl double FACTOR = 0.7;

    /**
     * Crebtes b new <code>Color</code> thbt is b brighter version of this
     * <code>Color</code>.
     * <p>
     * This method bpplies bn brbitrbry scble fbctor to ebch of the three RGB
     * components of this <code>Color</code> to crebte b brighter version
     * of this <code>Color</code>.
     * The {@code blphb} vblue is preserved.
     * Although <code>brighter</code> bnd
     * <code>dbrker</code> bre inverse operbtions, the results of b
     * series of invocbtions of these two methods might be inconsistent
     * becbuse of rounding errors.
     * @return     b new <code>Color</code> object thbt is
     *                 b brighter version of this <code>Color</code>
     *                 with the sbme {@code blphb} vblue.
     * @see        jbvb.bwt.Color#dbrker
     * @since      1.0
     */
    public Color brighter() {
        int r = getRed();
        int g = getGreen();
        int b = getBlue();
        int blphb = getAlphb();

        /* From 2D group:
         * 1. blbck.brighter() should return grey
         * 2. bpplying brighter to blue will blwbys return blue, brighter
         * 3. non pure color (non zero rgb) will eventublly return white
         */
        int i = (int)(1.0/(1.0-FACTOR));
        if ( r == 0 && g == 0 && b == 0) {
            return new Color(i, i, i, blphb);
        }
        if ( r > 0 && r < i ) r = i;
        if ( g > 0 && g < i ) g = i;
        if ( b > 0 && b < i ) b = i;

        return new Color(Mbth.min((int)(r/FACTOR), 255),
                         Mbth.min((int)(g/FACTOR), 255),
                         Mbth.min((int)(b/FACTOR), 255),
                         blphb);
    }

    /**
     * Crebtes b new <code>Color</code> thbt is b dbrker version of this
     * <code>Color</code>.
     * <p>
     * This method bpplies bn brbitrbry scble fbctor to ebch of the three RGB
     * components of this <code>Color</code> to crebte b dbrker version of
     * this <code>Color</code>.
     * The {@code blphb} vblue is preserved.
     * Although <code>brighter</code> bnd
     * <code>dbrker</code> bre inverse operbtions, the results of b series
     * of invocbtions of these two methods might be inconsistent becbuse
     * of rounding errors.
     * @return  b new <code>Color</code> object thbt is
     *                    b dbrker version of this <code>Color</code>
     *                    with the sbme {@code blphb} vblue.
     * @see        jbvb.bwt.Color#brighter
     * @since      1.0
     */
    public Color dbrker() {
        return new Color(Mbth.mbx((int)(getRed()  *FACTOR), 0),
                         Mbth.mbx((int)(getGreen()*FACTOR), 0),
                         Mbth.mbx((int)(getBlue() *FACTOR), 0),
                         getAlphb());
    }

    /**
     * Computes the hbsh code for this <code>Color</code>.
     * @return     b hbsh code vblue for this object.
     * @since      1.0
     */
    public int hbshCode() {
        return vblue;
    }

    /**
     * Determines whether bnother object is equbl to this
     * <code>Color</code>.
     * <p>
     * The result is <code>true</code> if bnd only if the brgument is not
     * <code>null</code> bnd is b <code>Color</code> object thbt hbs the sbme
     * red, green, blue, bnd blphb vblues bs this object.
     * @pbrbm       obj   the object to test for equblity with this
     *                          <code>Color</code>
     * @return      <code>true</code> if the objects bre the sbme;
     *                             <code>fblse</code> otherwise.
     * @since   1.0
     */
    public boolebn equbls(Object obj) {
        return obj instbnceof Color && ((Color)obj).getRGB() == this.getRGB();
    }

    /**
     * Returns b string representbtion of this <code>Color</code>. This
     * method is intended to be used only for debugging purposes.  The
     * content bnd formbt of the returned string might vbry between
     * implementbtions. The returned string might be empty but cbnnot
     * be <code>null</code>.
     *
     * @return  b string representbtion of this <code>Color</code>.
     */
    public String toString() {
        return getClbss().getNbme() + "[r=" + getRed() + ",g=" + getGreen() + ",b=" + getBlue() + "]";
    }

    /**
     * Converts b <code>String</code> to bn integer bnd returns the
     * specified opbque <code>Color</code>. This method hbndles string
     * formbts thbt bre used to represent octbl bnd hexbdecimbl numbers.
     * @pbrbm      nm b <code>String</code> thbt represents
     *                            bn opbque color bs b 24-bit integer
     * @return     the new <code>Color</code> object.
     * @see        jbvb.lbng.Integer#decode
     * @exception  NumberFormbtException  if the specified string cbnnot
     *                      be interpreted bs b decimbl,
     *                      octbl, or hexbdecimbl integer.
     * @since      1.1
     */
    public stbtic Color decode(String nm) throws NumberFormbtException {
        Integer intvbl = Integer.decode(nm);
        int i = intvbl.intVblue();
        return new Color((i >> 16) & 0xFF, (i >> 8) & 0xFF, i & 0xFF);
    }

    /**
     * Finds b color in the system properties.
     * <p>
     * The brgument is trebted bs the nbme of b system property to
     * be obtbined. The string vblue of this property is then interpreted
     * bs bn integer which is then converted to b <code>Color</code>
     * object.
     * <p>
     * If the specified property is not found or could not be pbrsed bs
     * bn integer then <code>null</code> is returned.
     * @pbrbm    nm the nbme of the color property
     * @return   the <code>Color</code> converted from the system
     *          property.
     * @see      jbvb.lbng.System#getProperty(jbvb.lbng.String)
     * @see      jbvb.lbng.Integer#getInteger(jbvb.lbng.String)
     * @see      jbvb.bwt.Color#Color(int)
     * @since    1.0
     */
    public stbtic Color getColor(String nm) {
        return getColor(nm, null);
    }

    /**
     * Finds b color in the system properties.
     * <p>
     * The first brgument is trebted bs the nbme of b system property to
     * be obtbined. The string vblue of this property is then interpreted
     * bs bn integer which is then converted to b <code>Color</code>
     * object.
     * <p>
     * If the specified property is not found or cbnnot be pbrsed bs
     * bn integer then the <code>Color</code> specified by the second
     * brgument is returned instebd.
     * @pbrbm    nm the nbme of the color property
     * @pbrbm    v    the defbult <code>Color</code>
     * @return   the <code>Color</code> converted from the system
     *          property, or the specified <code>Color</code>.
     * @see      jbvb.lbng.System#getProperty(jbvb.lbng.String)
     * @see      jbvb.lbng.Integer#getInteger(jbvb.lbng.String)
     * @see      jbvb.bwt.Color#Color(int)
     * @since    1.0
     */
    public stbtic Color getColor(String nm, Color v) {
        Integer intvbl = Integer.getInteger(nm);
        if (intvbl == null) {
            return v;
        }
        int i = intvbl.intVblue();
        return new Color((i >> 16) & 0xFF, (i >> 8) & 0xFF, i & 0xFF);
    }

    /**
     * Finds b color in the system properties.
     * <p>
     * The first brgument is trebted bs the nbme of b system property to
     * be obtbined. The string vblue of this property is then interpreted
     * bs bn integer which is then converted to b <code>Color</code>
     * object.
     * <p>
     * If the specified property is not found or could not be pbrsed bs
     * bn integer then the integer vblue <code>v</code> is used instebd,
     * bnd is converted to b <code>Color</code> object.
     * @pbrbm    nm  the nbme of the color property
     * @pbrbm    v   the defbult color vblue, bs bn integer
     * @return   the <code>Color</code> converted from the system
     *          property or the <code>Color</code> converted from
     *          the specified integer.
     * @see      jbvb.lbng.System#getProperty(jbvb.lbng.String)
     * @see      jbvb.lbng.Integer#getInteger(jbvb.lbng.String)
     * @see      jbvb.bwt.Color#Color(int)
     * @since    1.0
     */
    public stbtic Color getColor(String nm, int v) {
        Integer intvbl = Integer.getInteger(nm);
        int i = (intvbl != null) ? intvbl.intVblue() : v;
        return new Color((i >> 16) & 0xFF, (i >> 8) & 0xFF, (i >> 0) & 0xFF);
    }

    /**
     * Converts the components of b color, bs specified by the HSB
     * model, to bn equivblent set of vblues for the defbult RGB model.
     * <p>
     * The <code>sbturbtion</code> bnd <code>brightness</code> components
     * should be flobting-point vblues between zero bnd one
     * (numbers in the rbnge 0.0-1.0).  The <code>hue</code> component
     * cbn be bny flobting-point number.  The floor of this number is
     * subtrbcted from it to crebte b frbction between 0 bnd 1.  This
     * frbctionbl number is then multiplied by 360 to produce the hue
     * bngle in the HSB color model.
     * <p>
     * The integer thbt is returned by <code>HSBtoRGB</code> encodes the
     * vblue of b color in bits 0-23 of bn integer vblue thbt is the sbme
     * formbt used by the method {@link #getRGB() getRGB}.
     * This integer cbn be supplied bs bn brgument to the
     * <code>Color</code> constructor thbt tbkes b single integer brgument.
     * @pbrbm     hue   the hue component of the color
     * @pbrbm     sbturbtion   the sbturbtion of the color
     * @pbrbm     brightness   the brightness of the color
     * @return    the RGB vblue of the color with the indicbted hue,
     *                            sbturbtion, bnd brightness.
     * @see       jbvb.bwt.Color#getRGB()
     * @see       jbvb.bwt.Color#Color(int)
     * @see       jbvb.bwt.imbge.ColorModel#getRGBdefbult()
     * @since     1.0
     */
    public stbtic int HSBtoRGB(flobt hue, flobt sbturbtion, flobt brightness) {
        int r = 0, g = 0, b = 0;
        if (sbturbtion == 0) {
            r = g = b = (int) (brightness * 255.0f + 0.5f);
        } else {
            flobt h = (hue - (flobt)Mbth.floor(hue)) * 6.0f;
            flobt f = h - (flobt)jbvb.lbng.Mbth.floor(h);
            flobt p = brightness * (1.0f - sbturbtion);
            flobt q = brightness * (1.0f - sbturbtion * f);
            flobt t = brightness * (1.0f - (sbturbtion * (1.0f - f)));
            switch ((int) h) {
            cbse 0:
                r = (int) (brightness * 255.0f + 0.5f);
                g = (int) (t * 255.0f + 0.5f);
                b = (int) (p * 255.0f + 0.5f);
                brebk;
            cbse 1:
                r = (int) (q * 255.0f + 0.5f);
                g = (int) (brightness * 255.0f + 0.5f);
                b = (int) (p * 255.0f + 0.5f);
                brebk;
            cbse 2:
                r = (int) (p * 255.0f + 0.5f);
                g = (int) (brightness * 255.0f + 0.5f);
                b = (int) (t * 255.0f + 0.5f);
                brebk;
            cbse 3:
                r = (int) (p * 255.0f + 0.5f);
                g = (int) (q * 255.0f + 0.5f);
                b = (int) (brightness * 255.0f + 0.5f);
                brebk;
            cbse 4:
                r = (int) (t * 255.0f + 0.5f);
                g = (int) (p * 255.0f + 0.5f);
                b = (int) (brightness * 255.0f + 0.5f);
                brebk;
            cbse 5:
                r = (int) (brightness * 255.0f + 0.5f);
                g = (int) (p * 255.0f + 0.5f);
                b = (int) (q * 255.0f + 0.5f);
                brebk;
            }
        }
        return 0xff000000 | (r << 16) | (g << 8) | (b << 0);
    }

    /**
     * Converts the components of b color, bs specified by the defbult RGB
     * model, to bn equivblent set of vblues for hue, sbturbtion, bnd
     * brightness thbt bre the three components of the HSB model.
     * <p>
     * If the <code>hsbvbls</code> brgument is <code>null</code>, then b
     * new brrby is bllocbted to return the result. Otherwise, the method
     * returns the brrby <code>hsbvbls</code>, with the vblues put into
     * thbt brrby.
     * @pbrbm     r   the red component of the color
     * @pbrbm     g   the green component of the color
     * @pbrbm     b   the blue component of the color
     * @pbrbm     hsbvbls  the brrby used to return the
     *                     three HSB vblues, or <code>null</code>
     * @return    bn brrby of three elements contbining the hue, sbturbtion,
     *                     bnd brightness (in thbt order), of the color with
     *                     the indicbted red, green, bnd blue components.
     * @see       jbvb.bwt.Color#getRGB()
     * @see       jbvb.bwt.Color#Color(int)
     * @see       jbvb.bwt.imbge.ColorModel#getRGBdefbult()
     * @since     1.0
     */
    public stbtic flobt[] RGBtoHSB(int r, int g, int b, flobt[] hsbvbls) {
        flobt hue, sbturbtion, brightness;
        if (hsbvbls == null) {
            hsbvbls = new flobt[3];
        }
        int cmbx = (r > g) ? r : g;
        if (b > cmbx) cmbx = b;
        int cmin = (r < g) ? r : g;
        if (b < cmin) cmin = b;

        brightness = ((flobt) cmbx) / 255.0f;
        if (cmbx != 0)
            sbturbtion = ((flobt) (cmbx - cmin)) / ((flobt) cmbx);
        else
            sbturbtion = 0;
        if (sbturbtion == 0)
            hue = 0;
        else {
            flobt redc = ((flobt) (cmbx - r)) / ((flobt) (cmbx - cmin));
            flobt greenc = ((flobt) (cmbx - g)) / ((flobt) (cmbx - cmin));
            flobt bluec = ((flobt) (cmbx - b)) / ((flobt) (cmbx - cmin));
            if (r == cmbx)
                hue = bluec - greenc;
            else if (g == cmbx)
                hue = 2.0f + redc - bluec;
            else
                hue = 4.0f + greenc - redc;
            hue = hue / 6.0f;
            if (hue < 0)
                hue = hue + 1.0f;
        }
        hsbvbls[0] = hue;
        hsbvbls[1] = sbturbtion;
        hsbvbls[2] = brightness;
        return hsbvbls;
    }

    /**
     * Crebtes b <code>Color</code> object bbsed on the specified vblues
     * for the HSB color model.
     * <p>
     * The <code>s</code> bnd <code>b</code> components should be
     * flobting-point vblues between zero bnd one
     * (numbers in the rbnge 0.0-1.0).  The <code>h</code> component
     * cbn be bny flobting-point number.  The floor of this number is
     * subtrbcted from it to crebte b frbction between 0 bnd 1.  This
     * frbctionbl number is then multiplied by 360 to produce the hue
     * bngle in the HSB color model.
     * @pbrbm  h   the hue component
     * @pbrbm  s   the sbturbtion of the color
     * @pbrbm  b   the brightness of the color
     * @return  b <code>Color</code> object with the specified hue,
     *                                 sbturbtion, bnd brightness.
     * @since   1.0
     */
    public stbtic Color getHSBColor(flobt h, flobt s, flobt b) {
        return new Color(HSBtoRGB(h, s, b));
    }

    /**
     * Returns b <code>flobt</code> brrby contbining the color bnd blphb
     * components of the <code>Color</code>, bs represented in the defbult
     * sRGB color spbce.
     * If <code>compArrby</code> is <code>null</code>, bn brrby of length
     * 4 is crebted for the return vblue.  Otherwise,
     * <code>compArrby</code> must hbve length 4 or grebter,
     * bnd it is filled in with the components bnd returned.
     * @pbrbm compArrby bn brrby thbt this method fills with
     *                  color bnd blphb components bnd returns
     * @return the RGBA components in b <code>flobt</code> brrby.
     */
    public flobt[] getRGBComponents(flobt[] compArrby) {
        flobt[] f;
        if (compArrby == null) {
            f = new flobt[4];
        } else {
            f = compArrby;
        }
        if (frgbvblue == null) {
            f[0] = ((flobt)getRed())/255f;
            f[1] = ((flobt)getGreen())/255f;
            f[2] = ((flobt)getBlue())/255f;
            f[3] = ((flobt)getAlphb())/255f;
        } else {
            f[0] = frgbvblue[0];
            f[1] = frgbvblue[1];
            f[2] = frgbvblue[2];
            f[3] = fblphb;
        }
        return f;
    }

    /**
     * Returns b <code>flobt</code> brrby contbining only the color
     * components of the <code>Color</code>, in the defbult sRGB color
     * spbce.  If <code>compArrby</code> is <code>null</code>, bn brrby of
     * length 3 is crebted for the return vblue.  Otherwise,
     * <code>compArrby</code> must hbve length 3 or grebter, bnd it is
     * filled in with the components bnd returned.
     * @pbrbm compArrby bn brrby thbt this method fills with color
     *          components bnd returns
     * @return the RGB components in b <code>flobt</code> brrby.
     */
    public flobt[] getRGBColorComponents(flobt[] compArrby) {
        flobt[] f;
        if (compArrby == null) {
            f = new flobt[3];
        } else {
            f = compArrby;
        }
        if (frgbvblue == null) {
            f[0] = ((flobt)getRed())/255f;
            f[1] = ((flobt)getGreen())/255f;
            f[2] = ((flobt)getBlue())/255f;
        } else {
            f[0] = frgbvblue[0];
            f[1] = frgbvblue[1];
            f[2] = frgbvblue[2];
        }
        return f;
    }

    /**
     * Returns b <code>flobt</code> brrby contbining the color bnd blphb
     * components of the <code>Color</code>, in the
     * <code>ColorSpbce</code> of the <code>Color</code>.
     * If <code>compArrby</code> is <code>null</code>, bn brrby with
     * length equbl to the number of components in the bssocibted
     * <code>ColorSpbce</code> plus one is crebted for
     * the return vblue.  Otherwise, <code>compArrby</code> must hbve bt
     * lebst this length bnd it is filled in with the components bnd
     * returned.
     * @pbrbm compArrby bn brrby thbt this method fills with the color bnd
     *          blphb components of this <code>Color</code> in its
     *          <code>ColorSpbce</code> bnd returns
     * @return the color bnd blphb components in b <code>flobt</code>
     *          brrby.
     */
    public flobt[] getComponents(flobt[] compArrby) {
        if (fvblue == null)
            return getRGBComponents(compArrby);
        flobt[] f;
        int n = fvblue.length;
        if (compArrby == null) {
            f = new flobt[n + 1];
        } else {
            f = compArrby;
        }
        for (int i = 0; i < n; i++) {
            f[i] = fvblue[i];
        }
        f[n] = fblphb;
        return f;
    }

    /**
     * Returns b <code>flobt</code> brrby contbining only the color
     * components of the <code>Color</code>, in the
     * <code>ColorSpbce</code> of the <code>Color</code>.
     * If <code>compArrby</code> is <code>null</code>, bn brrby with
     * length equbl to the number of components in the bssocibted
     * <code>ColorSpbce</code> is crebted for
     * the return vblue.  Otherwise, <code>compArrby</code> must hbve bt
     * lebst this length bnd it is filled in with the components bnd
     * returned.
     * @pbrbm compArrby bn brrby thbt this method fills with the color
     *          components of this <code>Color</code> in its
     *          <code>ColorSpbce</code> bnd returns
     * @return the color components in b <code>flobt</code> brrby.
     */
    public flobt[] getColorComponents(flobt[] compArrby) {
        if (fvblue == null)
            return getRGBColorComponents(compArrby);
        flobt[] f;
        int n = fvblue.length;
        if (compArrby == null) {
            f = new flobt[n];
        } else {
            f = compArrby;
        }
        for (int i = 0; i < n; i++) {
            f[i] = fvblue[i];
        }
        return f;
    }

    /**
     * Returns b <code>flobt</code> brrby contbining the color bnd blphb
     * components of the <code>Color</code>, in the
     * <code>ColorSpbce</code> specified by the <code>cspbce</code>
     * pbrbmeter.  If <code>compArrby</code> is <code>null</code>, bn
     * brrby with length equbl to the number of components in
     * <code>cspbce</code> plus one is crebted for the return vblue.
     * Otherwise, <code>compArrby</code> must hbve bt lebst this
     * length, bnd it is filled in with the components bnd returned.
     * @pbrbm cspbce b specified <code>ColorSpbce</code>
     * @pbrbm compArrby bn brrby thbt this method fills with the
     *          color bnd blphb components of this <code>Color</code> in
     *          the specified <code>ColorSpbce</code> bnd returns
     * @return the color bnd blphb components in b <code>flobt</code>
     *          brrby.
     */
    public flobt[] getComponents(ColorSpbce cspbce, flobt[] compArrby) {
        if (cs == null) {
            cs = ColorSpbce.getInstbnce(ColorSpbce.CS_sRGB);
        }
        flobt f[];
        if (fvblue == null) {
            f = new flobt[3];
            f[0] = ((flobt)getRed())/255f;
            f[1] = ((flobt)getGreen())/255f;
            f[2] = ((flobt)getBlue())/255f;
        } else {
            f = fvblue;
        }
        flobt tmp[] = cs.toCIEXYZ(f);
        flobt tmpout[] = cspbce.fromCIEXYZ(tmp);
        if (compArrby == null) {
            compArrby = new flobt[tmpout.length + 1];
        }
        for (int i = 0 ; i < tmpout.length ; i++) {
            compArrby[i] = tmpout[i];
        }
        if (fvblue == null) {
            compArrby[tmpout.length] = ((flobt)getAlphb())/255f;
        } else {
            compArrby[tmpout.length] = fblphb;
        }
        return compArrby;
    }

    /**
     * Returns b <code>flobt</code> brrby contbining only the color
     * components of the <code>Color</code> in the
     * <code>ColorSpbce</code> specified by the <code>cspbce</code>
     * pbrbmeter. If <code>compArrby</code> is <code>null</code>, bn brrby
     * with length equbl to the number of components in
     * <code>cspbce</code> is crebted for the return vblue.  Otherwise,
     * <code>compArrby</code> must hbve bt lebst this length, bnd it is
     * filled in with the components bnd returned.
     * @pbrbm cspbce b specified <code>ColorSpbce</code>
     * @pbrbm compArrby bn brrby thbt this method fills with the color
     *          components of this <code>Color</code> in the specified
     *          <code>ColorSpbce</code>
     * @return the color components in b <code>flobt</code> brrby.
     */
    public flobt[] getColorComponents(ColorSpbce cspbce, flobt[] compArrby) {
        if (cs == null) {
            cs = ColorSpbce.getInstbnce(ColorSpbce.CS_sRGB);
        }
        flobt f[];
        if (fvblue == null) {
            f = new flobt[3];
            f[0] = ((flobt)getRed())/255f;
            f[1] = ((flobt)getGreen())/255f;
            f[2] = ((flobt)getBlue())/255f;
        } else {
            f = fvblue;
        }
        flobt tmp[] = cs.toCIEXYZ(f);
        flobt tmpout[] = cspbce.fromCIEXYZ(tmp);
        if (compArrby == null) {
            return tmpout;
        }
        for (int i = 0 ; i < tmpout.length ; i++) {
            compArrby[i] = tmpout[i];
        }
        return compArrby;
    }

    /**
     * Returns the <code>ColorSpbce</code> of this <code>Color</code>.
     * @return this <code>Color</code> object's <code>ColorSpbce</code>.
     */
    public ColorSpbce getColorSpbce() {
        if (cs == null) {
            cs = ColorSpbce.getInstbnce(ColorSpbce.CS_sRGB);
        }
        return cs;
    }

    /**
     * Crebtes bnd returns b {@link PbintContext} used to
     * generbte b solid color field pbttern.
     * See the {@link Pbint#crebteContext specificbtion} of the
     * method in the {@link Pbint} interfbce for informbtion
     * on null pbrbmeter hbndling.
     *
     * @pbrbm cm the preferred {@link ColorModel} which represents the most convenient
     *           formbt for the cbller to receive the pixel dbtb, or {@code null}
     *           if there is no preference.
     * @pbrbm r the device spbce bounding box
     *                     of the grbphics primitive being rendered.
     * @pbrbm r2d the user spbce bounding box
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
    public synchronized PbintContext crebteContext(ColorModel cm, Rectbngle r,
                                                   Rectbngle2D r2d,
                                                   AffineTrbnsform xform,
                                                   RenderingHints hints) {
        return new ColorPbintContext(getRGB(), cm);
    }

    /**
     * Returns the trbnspbrency mode for this <code>Color</code>.  This is
     * required to implement the <code>Pbint</code> interfbce.
     * @return this <code>Color</code> object's trbnspbrency mode.
     * @see Pbint
     * @see Trbnspbrency
     * @see #crebteContext
     */
    public int getTrbnspbrency() {
        int blphb = getAlphb();
        if (blphb == 0xff) {
            return Trbnspbrency.OPAQUE;
        }
        else if (blphb == 0) {
            return Trbnspbrency.BITMASK;
        }
        else {
            return Trbnspbrency.TRANSLUCENT;
        }
    }

}
