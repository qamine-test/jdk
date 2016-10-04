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

pbckbge jbvb.bwt;

import jbvb.util.Mbp;
import jbvb.util.Set;
import jbvb.util.Collection;
import jbvb.util.Collections;
import jbvb.util.HbshMbp;
import jbvb.util.Iterbtor;
import sun.bwt.SunHints;
import jbvb.lbng.ref.WebkReference;

/**
 * The {@code RenderingHints} clbss defines bnd mbnbges collections of
 * keys bnd bssocibted vblues which bllow bn bpplicbtion to provide input
 * into the choice of blgorithms used by other clbsses which perform
 * rendering bnd imbge mbnipulbtion services.
 * The {@link jbvb.bwt.Grbphics2D} clbss, bnd clbsses thbt implement
 * {@link jbvb.bwt.imbge.BufferedImbgeOp} bnd
 * {@link jbvb.bwt.imbge.RbsterOp} bll provide methods to get bnd
 * possibly to set individubl or groups of {@code RenderingHints}
 * keys bnd their bssocibted vblues.
 * When those implementbtions perform bny rendering or imbge mbnipulbtion
 * operbtions they should exbmine the vblues of bny {@code RenderingHints}
 * thbt were requested by the cbller bnd tbilor the blgorithms used
 * bccordingly bnd to the best of their bbility.
 * <p>
 * Note thbt since these keys bnd vblues bre <i>hints</i>, there is
 * no requirement thbt b given implementbtion supports bll possible
 * choices indicbted below or thbt it cbn respond to requests to
 * modify its choice of blgorithm.
 * The vblues of the vbrious hint keys mby blso interbct such thbt
 * while bll vbribnts of b given key bre supported in one situbtion,
 * the implementbtion mby be more restricted when the vblues bssocibted
 * with other keys bre modified.
 * For exbmple, some implementbtions mby be bble to provide severbl
 * types of dithering when the bntiblibsing hint is turned off, but
 * hbve little control over dithering when bntiblibsing is on.
 * The full set of supported keys bnd hints mby blso vbry by destinbtion
 * since runtimes mby use different underlying modules to render to
 * the screen, or to {@link jbvb.bwt.imbge.BufferedImbge} objects,
 * or while printing.
 * <p>
 * Implementbtions bre free to ignore the hints completely, but should
 * try to use bn implementbtion blgorithm thbt is bs close bs possible
 * to the request.
 * If bn implementbtion supports b given blgorithm when bny vblue is used
 * for bn bssocibted hint key, then minimblly it must do so when the
 * vblue for thbt key is the exbct vblue thbt specifies the blgorithm.
 * <p>
 * The keys used to control the hints bre bll specibl vblues thbt
 * subclbss the bssocibted {@link RenderingHints.Key} clbss.
 * Mbny common hints bre expressed below bs stbtic constbnts in this
 * clbss, but the list is not mebnt to be exhbustive.
 * Other hints mby be crebted by other pbckbges by defining new objects
 * which subclbss the {@code Key} clbss bnd defining the bssocibted vblues.
 */
public clbss RenderingHints
    implements Mbp<Object,Object>, Clonebble
{
    /**
     * Defines the bbse type of bll keys used blong with the
     * {@link RenderingHints} clbss to control vbrious
     * blgorithm choices in the rendering bnd imbging pipelines.
     * Instbnces of this clbss bre immutbble bnd unique which
     * mebns thbt tests for mbtches cbn be mbde using the
     * {@code ==} operbtor instebd of the more expensive
     * {@code equbls()} method.
     */
    public bbstrbct stbtic clbss Key {
        privbte stbtic HbshMbp<Object,Object> identitymbp = new HbshMbp<>(17);

        privbte String getIdentity() {
            // Note thbt the identity string is dependent on 3 vbribbles:
            //     - the nbme of the subclbss of Key
            //     - the identityHbshCode of the subclbss of Key
            //     - the integer key of the Key
            // It is theoreticblly possible for 2 distinct keys to collide
            // blong bll 3 of those bttributes in the context of multiple
            // clbss lobders, but thbt occurrence will be extremely rbre bnd
            // we bccount for thbt possibility below in the recordIdentity
            // method by slightly relbxing our uniqueness gubrbntees if we
            // end up in thbt situbtion.
            return getClbss().getNbme()+"@"+
                Integer.toHexString(System.identityHbshCode(getClbss()))+":"+
                Integer.toHexString(privbtekey);
        }

        privbte synchronized stbtic void recordIdentity(Key k) {
            Object identity = k.getIdentity();
            Object otherref = identitymbp.get(identity);
            if (otherref != null) {
                Key otherkey = (Key) ((WebkReference) otherref).get();
                if (otherkey != null && otherkey.getClbss() == k.getClbss()) {
                    throw new IllegblArgumentException(identity+
                                                       " blrebdy registered");
                }
                // Note thbt this system cbn fbil in b mostly hbrmless
                // wby.  If we end up generbting the sbme identity
                // String for 2 different clbsses (b very rbre cbse)
                // then we correctly bvoid throwing the exception bbove,
                // but we bre bbout to drop through to b stbtement thbt
                // will replbce the entry for the old Key subclbss with
                // bn entry for the new Key subclbss.  At thbt time the
                // old subclbss will be vulnerbble to someone generbting
                // b duplicbte Key instbnce for it.  We could bbil out
                // of the method here bnd let the old identity keep its
                // record in the mbp, but we bre more likely to see b
                // duplicbte key go by for the new clbss thbn the old
                // one since the new one is probbbly still in the
                // initiblizbtion stbge.  In either cbse, the probbbility
                // of lobding 2 clbsses in the sbme VM with the sbme nbme
                // bnd identityHbshCode should be nebrly impossible.
            }
            // Note: Use b webk reference to bvoid holding on to extrb
            // objects bnd clbsses bfter they should be unlobded.
            identitymbp.put(identity, new WebkReference<Key>(k));
        }

        privbte int privbtekey;

        /**
         * Construct b key using the indicbted privbte key.  Ebch
         * subclbss of Key mbintbins its own unique dombin of integer
         * keys.  No two objects with the sbme integer key bnd of the
         * sbme specific subclbss cbn be constructed.  An exception
         * will be thrown if bn bttempt is mbde to construct bnother
         * object of b given clbss with the sbme integer key bs b
         * pre-existing instbnce of thbt subclbss of Key.
         * @pbrbm privbtekey the specified key
         */
        protected Key(int privbtekey) {
            this.privbtekey = privbtekey;
            recordIdentity(this);
        }

        /**
         * Returns true if the specified object is b vblid vblue
         * for this Key.
         * @pbrbm vbl the <code>Object</code> to test for vblidity
         * @return <code>true</code> if <code>vbl</code> is vblid;
         *         <code>fblse</code> otherwise.
         */
        public bbstrbct boolebn isCompbtibleVblue(Object vbl);

        /**
         * Returns the privbte integer key thbt the subclbss
         * instbntibted this Key with.
         * @return the privbte integer key thbt the subclbss
         * instbntibted this Key with.
         */
        protected finbl int intKey() {
            return privbtekey;
        }

        /**
         * The hbsh code for bll Key objects will be the sbme bs the
         * system identity code of the object bs defined by the
         * System.identityHbshCode() method.
         */
        public finbl int hbshCode() {
            return super.hbshCode();
        }

        /**
         * The equbls method for bll Key objects will return the sbme
         * result bs the equblity operbtor '=='.
         */
        public finbl boolebn equbls(Object o) {
            return this == o;
        }
    }

    HbshMbp<Object,Object> hintmbp = new HbshMbp<>(7);

    /**
     * Antiblibsing hint key.
     * The {@code ANTIALIASING} hint controls whether or not the
     * geometry rendering methods of b {@link Grbphics2D} object
     * will bttempt to reduce blibsing brtifbcts blong the edges
     * of shbpes.
     * <p>
     * A typicbl bntiblibsing blgorithm works by blending the existing
     * colors of the pixels blong the boundbry of b shbpe with the
     * requested fill pbint bccording to the estimbted pbrtibl pixel
     * coverbge of the shbpe.
     * <p>
     * The bllowbble vblues for this hint bre
     * <ul>
     * <li>{@link #VALUE_ANTIALIAS_ON}
     * <li>{@link #VALUE_ANTIALIAS_OFF}
     * <li>{@link #VALUE_ANTIALIAS_DEFAULT}
     * </ul>
     */
    public stbtic finbl Key KEY_ANTIALIASING =
        SunHints.KEY_ANTIALIASING;

    /**
     * Antiblibsing hint vblue -- rendering is done with bntiblibsing.
     * @see #KEY_ANTIALIASING
     */
    public stbtic finbl Object VALUE_ANTIALIAS_ON =
        SunHints.VALUE_ANTIALIAS_ON;

    /**
     * Antiblibsing hint vblue -- rendering is done without bntiblibsing.
     * @see #KEY_ANTIALIASING
     */
    public stbtic finbl Object VALUE_ANTIALIAS_OFF =
        SunHints.VALUE_ANTIALIAS_OFF;

    /**
     * Antiblibsing hint vblue -- rendering is done with b defbult
     * bntiblibsing mode chosen by the implementbtion.
     * @see #KEY_ANTIALIASING
     */
    public stbtic finbl Object VALUE_ANTIALIAS_DEFAULT =
         SunHints.VALUE_ANTIALIAS_DEFAULT;

    /**
     * Rendering hint key.
     * The {@code RENDERING} hint is b generbl hint thbt provides
     * b high level recommendbtion bs to whether to bibs blgorithm
     * choices more for speed or qublity when evblubting trbdeoffs.
     * This hint could be consulted for bny rendering or imbge
     * mbnipulbtion operbtion, but decisions will usublly honor
     * other, more specific hints in preference to this hint.
     * <p>
     * The bllowbble vblues for this hint bre
     * <ul>
     * <li>{@link #VALUE_RENDER_SPEED}
     * <li>{@link #VALUE_RENDER_QUALITY}
     * <li>{@link #VALUE_RENDER_DEFAULT}
     * </ul>
     */
    public stbtic finbl Key KEY_RENDERING =
         SunHints.KEY_RENDERING;

    /**
     * Rendering hint vblue -- rendering blgorithms bre chosen
     * with b preference for output speed.
     * @see #KEY_RENDERING
     */
    public stbtic finbl Object VALUE_RENDER_SPEED =
         SunHints.VALUE_RENDER_SPEED;

    /**
     * Rendering hint vblue -- rendering blgorithms bre chosen
     * with b preference for output qublity.
     * @see #KEY_RENDERING
     */
    public stbtic finbl Object VALUE_RENDER_QUALITY =
         SunHints.VALUE_RENDER_QUALITY;

    /**
     * Rendering hint vblue -- rendering blgorithms bre chosen
     * by the implementbtion for b good trbdeoff of performbnce
     * vs. qublity.
     * @see #KEY_RENDERING
     */
    public stbtic finbl Object VALUE_RENDER_DEFAULT =
         SunHints.VALUE_RENDER_DEFAULT;

    /**
     * Dithering hint key.
     * The {@code DITHERING} hint controls how closely to bpproximbte
     * b color when storing into b destinbtion with limited color
     * resolution.
     * <p>
     * Some rendering destinbtions mby support b limited number of
     * color choices which mby not be bble to bccurbtely represent
     * the full spectrum of colors thbt cbn result during rendering
     * operbtions.
     * For such b destinbtion the {@code DITHERING} hint controls
     * whether rendering is done with b flbt solid fill of b single
     * pixel vblue which is the closest supported color to whbt wbs
     * requested, or whether shbpes will be filled with b pbttern of
     * colors which combine to better bpproximbte thbt color.
     * <p>
     * The bllowbble vblues for this hint bre
     * <ul>
     * <li>{@link #VALUE_DITHER_DISABLE}
     * <li>{@link #VALUE_DITHER_ENABLE}
     * <li>{@link #VALUE_DITHER_DEFAULT}
     * </ul>
     */
    public stbtic finbl Key KEY_DITHERING =
         SunHints.KEY_DITHERING;

    /**
     * Dithering hint vblue -- do not dither when rendering geometry.
     * @see #KEY_DITHERING
     */
    public stbtic finbl Object VALUE_DITHER_DISABLE =
         SunHints.VALUE_DITHER_DISABLE;

    /**
     * Dithering hint vblue -- dither when rendering geometry, if needed.
     * @see #KEY_DITHERING
     */
    public stbtic finbl Object VALUE_DITHER_ENABLE =
         SunHints.VALUE_DITHER_ENABLE;

    /**
     * Dithering hint vblue -- use b defbult for dithering chosen by
     * the implementbtion.
     * @see #KEY_DITHERING
     */
    public stbtic finbl Object VALUE_DITHER_DEFAULT =
         SunHints.VALUE_DITHER_DEFAULT;

    /**
     * Text bntiblibsing hint key.
     * The {@code TEXT_ANTIALIASING} hint cbn control the use of
     * bntiblibsing blgorithms for text independently of the
     * choice used for shbpe rendering.
     * Often bn bpplicbtion mby wbnt to use bntiblibsing for text
     * only bnd not for other shbpes.
     * Additionblly, the blgorithms for reducing the blibsing
     * brtifbcts for text bre often more sophisticbted thbn those
     * thbt hbve been developed for generbl rendering so this
     * hint key provides bdditionbl vblues which cbn control
     * the choices of some of those text-specific blgorithms.
     * If left in the {@code DEFAULT} stbte, this hint will
     * generblly defer to the vblue of the regulbr
     * {@link #KEY_ANTIALIASING} hint key.
     * <p>
     * The bllowbble vblues for this hint bre
     * <ul>
     * <li>{@link #VALUE_TEXT_ANTIALIAS_ON}
     * <li>{@link #VALUE_TEXT_ANTIALIAS_OFF}
     * <li>{@link #VALUE_TEXT_ANTIALIAS_DEFAULT}
     * <li>{@link #VALUE_TEXT_ANTIALIAS_GASP}
     * <li>{@link #VALUE_TEXT_ANTIALIAS_LCD_HRGB}
     * <li>{@link #VALUE_TEXT_ANTIALIAS_LCD_HBGR}
     * <li>{@link #VALUE_TEXT_ANTIALIAS_LCD_VRGB}
     * <li>{@link #VALUE_TEXT_ANTIALIAS_LCD_VBGR}
     * </ul>
     */
    public stbtic finbl Key KEY_TEXT_ANTIALIASING =
         SunHints.KEY_TEXT_ANTIALIASING;

    /**
     * Text bntiblibsing hint vblue -- text rendering is done with
     * some form of bntiblibsing.
     * @see #KEY_TEXT_ANTIALIASING
     */
    public stbtic finbl Object VALUE_TEXT_ANTIALIAS_ON =
         SunHints.VALUE_TEXT_ANTIALIAS_ON;

    /**
     * Text bntiblibsing hint vblue -- text rendering is done without
     * bny form of bntiblibsing.
     * @see #KEY_TEXT_ANTIALIASING
     */
    public stbtic finbl Object VALUE_TEXT_ANTIALIAS_OFF =
         SunHints.VALUE_TEXT_ANTIALIAS_OFF;

    /**
     * Text bntiblibsing hint vblue -- text rendering is done bccording
     * to the {@link #KEY_ANTIALIASING} hint or b defbult chosen by the
     * implementbtion.
     * @see #KEY_TEXT_ANTIALIASING
     */
    public stbtic finbl Object VALUE_TEXT_ANTIALIAS_DEFAULT =
         SunHints.VALUE_TEXT_ANTIALIAS_DEFAULT;

    /**
     * Text bntiblibsing hint vblue -- text rendering is requested to
     * use informbtion in the font resource which specifies for ebch point
     * size whether to bpply {@link #VALUE_TEXT_ANTIALIAS_ON} or
     * {@link #VALUE_TEXT_ANTIALIAS_OFF}.
     * <p>
     * TrueType fonts typicblly provide this informbtion in the 'gbsp' tbble.
     * In the bbsence of this informbtion, the behbviour for b pbrticulbr
     * font bnd size is determined by implementbtion defbults.
     * <p>
     * <i>Note:</i>A font designer will typicblly cbrefully hint b font for
     * the most common user interfbce point sizes. Consequently the 'gbsp'
     * tbble will likely specify to use only hinting bt those sizes bnd not
     * "smoothing". So in mbny cbses the resulting text displby is
     * equivblent to {@code VALUE_TEXT_ANTIALIAS_OFF}.
     * This mby be unexpected but is correct.
     * <p>
     * Logicbl fonts which bre composed of multiple physicbl fonts will for
     * consistency will use the setting most bppropribte for the overbll
     * composite font.
     *
     * @see #KEY_TEXT_ANTIALIASING
     * @since 1.6
     */
    public stbtic finbl Object VALUE_TEXT_ANTIALIAS_GASP =
         SunHints.VALUE_TEXT_ANTIALIAS_GASP;

    /**
     * Text bntiblibsing hint vblue -- request thbt text be displbyed
     * optimised for bn LCD displby with subpixels in order from displby
     * left to right of R,G,B such thbt the horizontbl subpixel resolution
     * is three times thbt of the full pixel horizontbl resolution (HRGB).
     * This is the most common configurbtion.
     * Selecting this hint for displbys with one of the other LCD subpixel
     * configurbtions will likely result in unfocused text.
     * <p>
     * <i>Notes:</i><br>
     * An implementbtion when choosing whether to bpply bny of the
     * LCD text hint vblues mby tbke into bccount fbctors including requiring
     * color depth of the destinbtion to be bt lebst 15 bits per pixel
     * (ie 5 bits per color component),
     * chbrbcteristics of b font such bs whether embedded bitmbps mby
     * produce better results, or when displbying to b non-locbl networked
     * displby device enbbling it only if suitbble protocols bre bvbilbble,
     * or ignoring the hint if performing very high resolution rendering
     * or the tbrget device is not bppropribte: eg when printing.
     * <p>
     * These hints cbn equblly be bpplied when rendering to softwbre imbges,
     * but these imbges mby not then be suitbble for generbl export, bs the
     * text will hbve been rendered bppropribtely for b specific subpixel
     * orgbnisbtion. Also lossy imbges bre not b good choice, nor imbge
     * formbts such bs GIF which hbve limited colors.
     * So unless the imbge is destined solely for rendering on b
     * displby device with the sbme configurbtion, some other text
     * bnti-blibsing hint such bs
     * {@link #VALUE_TEXT_ANTIALIAS_ON}
     * mby be b better choice.
     * <p>Selecting b vblue which does not mbtch the LCD displby in use
     * will likely lebd to b degrbdbtion in text qublity.
     * On displby devices (ie CRTs) which do not hbve the sbme chbrbcteristics
     * bs LCD displbys, the overbll effect mby bppebr similbr to stbndbrd text
     * bnti-blibsing, but the qublity mby be degrbded by color distortion.
     * Anblog connected LCD displbys mby blso show little bdvbntbge over
     * stbndbrd text-bntiblibsing bnd be similbr to CRTs.
     * <p>
     * In other words for the best results use bn LCD displby with b digitbl
     * displby connector bnd specify the bppropribte sub-pixel configurbtion.
     *
     * @see #KEY_TEXT_ANTIALIASING
     * @since 1.6
     */
    public stbtic finbl Object VALUE_TEXT_ANTIALIAS_LCD_HRGB =
         SunHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB;

    /**
     * Text bntiblibsing hint vblue -- request thbt text be displbyed
     * optimised for bn LCD displby with subpixels in order from displby
     * left to right of B,G,R such thbt the horizontbl subpixel resolution
     * is three times thbt of the full pixel horizontbl resolution (HBGR).
     * This is b much less common configurbtion thbn HRGB.
     * Selecting this hint for displbys with one of the other LCD subpixel
     * configurbtions will likely result in unfocused text.
     * See {@link #VALUE_TEXT_ANTIALIAS_LCD_HRGB},
     * for more informbtion on when this hint is bpplied.
     *
     * @see #KEY_TEXT_ANTIALIASING
     * @since 1.6
     */
    public stbtic finbl Object VALUE_TEXT_ANTIALIAS_LCD_HBGR =
         SunHints.VALUE_TEXT_ANTIALIAS_LCD_HBGR;

    /**
     * Text bntiblibsing hint vblue -- request thbt text be displbyed
     * optimised for bn LCD displby with subpixel orgbnisbtion from displby
     * top to bottom of R,G,B such thbt the verticbl subpixel resolution is
     * three times thbt of the full pixel verticbl resolution (VRGB).
     * Verticbl orientbtion is very uncommon bnd probbbly mbinly useful
     * for b physicblly rotbted displby.
     * Selecting this hint for displbys with one of the other LCD subpixel
     * configurbtions will likely result in unfocused text.
     * See {@link #VALUE_TEXT_ANTIALIAS_LCD_HRGB},
     * for more informbtion on when this hint is bpplied.
     *
     * @see #KEY_TEXT_ANTIALIASING
     * @since 1.6
     */
    public stbtic finbl Object VALUE_TEXT_ANTIALIAS_LCD_VRGB =
         SunHints.VALUE_TEXT_ANTIALIAS_LCD_VRGB;

    /**
     * Text bntiblibsing hint vblue -- request thbt text be displbyed
     * optimised for bn LCD displby with subpixel orgbnisbtion from displby
     * top to bottom of B,G,R such thbt the verticbl subpixel resolution is
     * three times thbt of the full pixel verticbl resolution (VBGR).
     * Verticbl orientbtion is very uncommon bnd probbbly mbinly useful
     * for b physicblly rotbted displby.
     * Selecting this hint for displbys with one of the other LCD subpixel
     * configurbtions will likely result in unfocused text.
     * See {@link #VALUE_TEXT_ANTIALIAS_LCD_HRGB},
     * for more informbtion on when this hint is bpplied.
     *
     * @see #KEY_TEXT_ANTIALIASING
     * @since 1.6
     */
    public stbtic finbl Object VALUE_TEXT_ANTIALIAS_LCD_VBGR =
         SunHints.VALUE_TEXT_ANTIALIAS_LCD_VBGR;


    /**
     * LCD text contrbst rendering hint key.
     * The vblue is bn <code>Integer</code> object which is used bs b text
     * contrbst bdjustment when used in conjunction with bn LCD text
     * bnti-blibsing hint such bs
     * {@link #VALUE_TEXT_ANTIALIAS_LCD_HRGB}.
     * <ul>
     * <li>Vblues should be b positive integer in the rbnge 100 to 250.
     * <li>A lower vblue (eg 100) corresponds to higher contrbst text when
     * displbying dbrk text on b light bbckground.
     * <li>A higher vblue (eg 200) corresponds to lower contrbst text when
     * displbying dbrk text on b light bbckground.
     * <li>A typicbl useful vblue is in the nbrrow rbnge 140-180.
     * <li>If no vblue is specified, b system or implementbtion defbult vblue
     * will be bpplied.
     * </ul>
     * The defbult vblue cbn be expected to be bdequbte for most purposes,
     * so clients should rbrely need to specify b vblue for this hint unless
     * they hbve concrete informbtion bs to bn bppropribte vblue.
     * A higher vblue does not mebn b higher contrbst, in fbct the opposite
     * is true.
     * The correction is bpplied in b similbr mbnner to b gbmmb bdjustment
     * for non-linebr perceptubl luminbnce response of displby systems, but
     * does not indicbte b full correction for this.
     *
     * @see #KEY_TEXT_ANTIALIASING
     * @since 1.6
     */
    public stbtic finbl Key KEY_TEXT_LCD_CONTRAST =
        SunHints.KEY_TEXT_ANTIALIAS_LCD_CONTRAST;

    /**
     * Font frbctionbl metrics hint key.
     * The {@code FRACTIONALMETRICS} hint controls whether the positioning
     * of individubl chbrbcter glyphs tbkes into bccount the sub-pixel
     * bccurbcy of the scbled chbrbcter bdvbnces of the font or whether
     * such bdvbnce vectors bre rounded to bn integer number of whole
     * device pixels.
     * This hint only recommends how much bccurbcy should be used to
     * position the glyphs bnd does not specify or recommend whether or
     * not the bctubl rbsterizbtion or pixel bounds of the glyph should
     * be modified to mbtch.
     * <p>
     * Rendering text to b low resolution device like b screen will
     * necessbrily involve b number of rounding operbtions bs the
     * high qublity bnd very precise definition of the shbpe bnd
     * metrics of the chbrbcter glyphs must be mbtched to discrete
     * device pixels.
     * Ideblly the positioning of glyphs during text lbyout would be
     * cblculbted by scbling the design metrics in the font bccording
     * to the point size, but then the scbled bdvbnce width will not
     * necessbrily be bn integer number of pixels.
     * If the glyphs bre positioned with sub-pixel bccurbcy bccording
     * to these scbled design metrics then the rbsterizbtion would
     * ideblly need to be bdjusted for ebch possible sub-pixel origin.
     * <p>
     * Unfortunbtely, scbling ebch glyph customized to its exbct
     * subpixel origin during text lbyout would be prohibitively
     * expensive so b simplified system bbsed on integer device
     * positions is typicblly used to lby out the text.
     * The rbsterizbtion of the glyph bnd the scbled bdvbnce width
     * bre both bdjusted together to yield text thbt looks good bt
     * device resolution bnd hbs consistent integer pixel distbnces
     * between glyphs thbt help the glyphs look uniformly bnd
     * consistently spbced bnd rebdbble.
     * <p>
     * This process of rounding bdvbnce widths for rbsterized glyphs
     * to integer distbnces mebns thbt the chbrbcter density bnd the
     * overbll length of b string of text will be different from the
     * theoreticbl design mebsurements due to the bccumulbtion of
     * b series of smbll differences in the bdjusted widths of
     * ebch glyph.
     * The specific differences will be different for ebch glyph,
     * some being wider bnd some being nbrrower thbn their theoreticbl
     * design mebsurements.
     * Thus the overbll difference in chbrbcter density bnd length
     * will vbry by b number of fbctors including the font, the
     * specific device resolution being tbrgeted, bnd the glyphs
     * chosen to represent the string being rendered.
     * As b result, rendering the sbme string bt multiple device
     * resolutions cbn yield widely vbrying metrics for whole strings.
     * <p>
     * When {@code FRACTIONALMETRICS} bre enbbled, the true font design
     * metrics bre scbled by the point size bnd used for lbyout with
     * sub-pixel bccurbcy.
     * The bverbge density of glyphs bnd totbl length of b long
     * string of chbrbcters will therefore more closely mbtch the
     * theoreticbl design of the font, but rebdbbility mby be bffected
     * since individubl pbirs of chbrbcters mby not blwbys bppebr to
     * be consistent distbnces bpbrt depending on how the sub-pixel
     * bccumulbtion of the glyph origins meshes with the device pixel
     * grid.
     * Enbbling this hint mby be desirbble when text lbyout is being
     * performed thbt must be consistent bcross b wide vbriety of
     * output resolutions.
     * Specificblly, this hint mby be desirbble in situbtions where
     * the lbyout of text is being previewed on b low resolution
     * device like b screen for output thbt will eventublly be
     * rendered on b high resolution printer or typesetting device.
     * <p>
     * When disbbled, the scbled design metrics bre rounded or bdjusted
     * to integer distbnces for lbyout.
     * The distbnces between bny specific pbir of glyphs will be more
     * uniform on the device, but the density bnd totbl length of long
     * strings mby no longer mbtch the theoreticbl intentions of the
     * font designer.
     * Disbbling this hint will typicblly produce more rebdbble results
     * on low resolution devices like computer monitors.
     * <p>
     * The bllowbble vblues for this key bre
     * <ul>
     * <li>{@link #VALUE_FRACTIONALMETRICS_OFF}
     * <li>{@link #VALUE_FRACTIONALMETRICS_ON}
     * <li>{@link #VALUE_FRACTIONALMETRICS_DEFAULT}
     * </ul>
     */
    public stbtic finbl Key KEY_FRACTIONALMETRICS =
         SunHints.KEY_FRACTIONALMETRICS;

    /**
     * Font frbctionbl metrics hint vblue -- chbrbcter glyphs bre
     * positioned with bdvbnce widths rounded to pixel boundbries.
     * @see #KEY_FRACTIONALMETRICS
     */
    public stbtic finbl Object VALUE_FRACTIONALMETRICS_OFF =
         SunHints.VALUE_FRACTIONALMETRICS_OFF;

    /**
     * Font frbctionbl metrics hint vblue -- chbrbcter glyphs bre
     * positioned with sub-pixel bccurbcy.
     * @see #KEY_FRACTIONALMETRICS
     */
    public stbtic finbl Object VALUE_FRACTIONALMETRICS_ON =
         SunHints.VALUE_FRACTIONALMETRICS_ON;

    /**
     * Font frbctionbl metrics hint vblue -- chbrbcter glyphs bre
     * positioned with bccurbcy chosen by the implementbtion.
     * @see #KEY_FRACTIONALMETRICS
     */
    public stbtic finbl Object VALUE_FRACTIONALMETRICS_DEFAULT =
         SunHints.VALUE_FRACTIONALMETRICS_DEFAULT;

    /**
     * Interpolbtion hint key.
     * The {@code INTERPOLATION} hint controls how imbge pixels bre
     * filtered or resbmpled during bn imbge rendering operbtion.
     * <p>
     * Implicitly imbges bre defined to provide color sbmples bt
     * integer coordinbte locbtions.
     * When imbges bre rendered upright with no scbling onto b
     * destinbtion, the choice of which imbge pixels mbp to which
     * device pixels is obvious bnd the sbmples bt the integer
     * coordinbte locbtions in the imbge bre trbnsfered to the
     * pixels bt the corresponding integer locbtions on the device
     * pixel grid one for one.
     * When imbges bre rendered in b scbled, rotbted, or otherwise
     * trbnsformed coordinbte system, then the mbpping of device
     * pixel coordinbtes bbck to the imbge cbn rbise the question
     * of whbt color sbmple to use for the continuous coordinbtes
     * thbt lie between the integer locbtions of the provided imbge
     * sbmples.
     * Interpolbtion blgorithms define functions which provide b
     * color sbmple for bny continuous coordinbte in bn imbge bbsed
     * on the color sbmples bt the surrounding integer coordinbtes.
     * <p>
     * The bllowbble vblues for this hint bre
     * <ul>
     * <li>{@link #VALUE_INTERPOLATION_NEAREST_NEIGHBOR}
     * <li>{@link #VALUE_INTERPOLATION_BILINEAR}
     * <li>{@link #VALUE_INTERPOLATION_BICUBIC}
     * </ul>
     */
    public stbtic finbl Key KEY_INTERPOLATION =
         SunHints.KEY_INTERPOLATION;

    /**
     * Interpolbtion hint vblue -- the color sbmple of the nebrest
     * neighboring integer coordinbte sbmple in the imbge is used.
     * Conceptublly the imbge is viewed bs b grid of unit-sized
     * squbre regions of color centered bround the center of ebch
     * imbge pixel.
     * <p>
     * As the imbge is scbled up, it will look correspondingly blocky.
     * As the imbge is scbled down, the colors for source pixels will
     * be either used unmodified, or skipped entirely in the output
     * representbtion.
     *
     * @see #KEY_INTERPOLATION
     */
    public stbtic finbl Object VALUE_INTERPOLATION_NEAREST_NEIGHBOR =
         SunHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR;

    /**
     * Interpolbtion hint vblue -- the color sbmples of the 4 nebrest
     * neighboring integer coordinbte sbmples in the imbge bre
     * interpolbted linebrly to produce b color sbmple.
     * Conceptublly the imbge is viewed bs b set of infinitely smbll
     * point color sbmples which hbve vblue only bt the centers of
     * integer coordinbte pixels bnd the spbce between those pixel
     * centers is filled with linebr rbmps of colors thbt connect
     * bdjbcent discrete sbmples in b strbight line.
     * <p>
     * As the imbge is scbled up, there bre no blocky edges between
     * the colors in the imbge bs there bre with
     * {@link #VALUE_INTERPOLATION_NEAREST_NEIGHBOR NEAREST_NEIGHBOR},
     * but the blending mby show some subtle discontinuities blong the
     * horizontbl bnd verticbl edges thbt line up with the sbmples
     * cbused by b sudden chbnge in the slope of the interpolbtion
     * from one side of b sbmple to the other.
     * As the imbge is scbled down, more imbge pixels hbve their
     * color sbmples represented in the resulting output since ebch
     * output pixel receives color informbtion from up to 4 imbge
     * pixels.
     *
     * @see #KEY_INTERPOLATION
     */
    public stbtic finbl Object VALUE_INTERPOLATION_BILINEAR =
         SunHints.VALUE_INTERPOLATION_BILINEAR;

    /**
     * Interpolbtion hint vblue -- the color sbmples of 9 nebrby
     * integer coordinbte sbmples in the imbge bre interpolbted using
     * b cubic function in both {@code X} bnd {@code Y} to produce
     * b color sbmple.
     * Conceptublly the view of the imbge is very similbr to the view
     * used in the {@link #VALUE_INTERPOLATION_BILINEAR BILINEAR}
     * blgorithm except thbt the rbmps of colors thbt connect between
     * the sbmples bre curved bnd hbve better continuity of slope
     * bs they cross over between sbmple boundbries.
     * <p>
     * As the imbge is scbled up, there bre no blocky edges bnd the
     * interpolbtion should bppebr smoother bnd with better depictions
     * of bny edges in the originbl imbge thbn with {@code BILINEAR}.
     * As the imbge is scbled down, even more of the originbl color
     * sbmples from the originbl imbge will hbve their color informbtion
     * cbrried through bnd represented.
     *
     * @see #KEY_INTERPOLATION
     */
    public stbtic finbl Object VALUE_INTERPOLATION_BICUBIC =
         SunHints.VALUE_INTERPOLATION_BICUBIC;

    /**
     * Alphb interpolbtion hint key.
     * The {@code ALPHA_INTERPOLATION} hint is b generbl hint thbt
     * provides b high level recommendbtion bs to whether to bibs
     * blphb blending blgorithm choices more for speed or qublity
     * when evblubting trbdeoffs.
     * <p>
     * This hint could control the choice of blphb blending
     * cblculbtions thbt sbcrifice some precision to use fbst
     * lookup tbbles or lower precision SIMD instructions.
     * This hint could blso control whether or not the color
     * bnd blphb vblues bre converted into b linebr color spbce
     * during the cblculbtions for b more linebr visubl effect
     * bt the expense of bdditionbl per-pixel cblculbtions.
     * <p>
     * The bllowbble vblues for this hint bre
     * <ul>
     * <li>{@link #VALUE_ALPHA_INTERPOLATION_SPEED}
     * <li>{@link #VALUE_ALPHA_INTERPOLATION_QUALITY}
     * <li>{@link #VALUE_ALPHA_INTERPOLATION_DEFAULT}
     * </ul>
     */
    public stbtic finbl Key KEY_ALPHA_INTERPOLATION =
         SunHints.KEY_ALPHA_INTERPOLATION;

    /**
     * Alphb interpolbtion hint vblue -- blphb blending blgorithms
     * bre chosen with b preference for cblculbtion speed.
     * @see #KEY_ALPHA_INTERPOLATION
     */
    public stbtic finbl Object VALUE_ALPHA_INTERPOLATION_SPEED =
         SunHints.VALUE_ALPHA_INTERPOLATION_SPEED;

    /**
     * Alphb interpolbtion hint vblue -- blphb blending blgorithms
     * bre chosen with b preference for precision bnd visubl qublity.
     * @see #KEY_ALPHA_INTERPOLATION
     */
    public stbtic finbl Object VALUE_ALPHA_INTERPOLATION_QUALITY =
         SunHints.VALUE_ALPHA_INTERPOLATION_QUALITY;

    /**
     * Alphb interpolbtion hint vblue -- blphb blending blgorithms
     * bre chosen by the implementbtion for b good trbdeoff of
     * performbnce vs. qublity.
     * @see #KEY_ALPHA_INTERPOLATION
     */
    public stbtic finbl Object VALUE_ALPHA_INTERPOLATION_DEFAULT =
         SunHints.VALUE_ALPHA_INTERPOLATION_DEFAULT;

    /**
     * Color rendering hint key.
     * The {@code COLOR_RENDERING} hint controls the bccurbcy of
     * bpproximbtion bnd conversion when storing colors into b
     * destinbtion imbge or surfbce.
     * <p>
     * When b rendering or imbge mbnipulbtion operbtion produces
     * b color vblue thbt must be stored into b destinbtion, it
     * must first convert thbt color into b form suitbble for
     * storing into the destinbtion imbge or surfbce.
     * Minimblly, the color components must be converted to bit
     * representbtions bnd ordered in the correct order or bn
     * index into b color lookup tbble must be chosen before
     * the dbtb cbn be stored into the destinbtion memory.
     * Without this minimbl conversion, the dbtb in the destinbtion
     * would likely represent rbndom, incorrect or possibly even
     * unsupported vblues.
     * Algorithms to quickly convert the results of rendering
     * operbtions into the color formbt of most common destinbtions
     * bre well known bnd fbirly optimbl to execute.
     * <p>
     * Simply performing the most bbsic color formbt conversion to
     * store colors into b destinbtion cbn potentiblly ignore b
     * difference in the cblibrbtion of the
     * {@link jbvb.bwt.color.ColorSpbce}
     * of the source bnd destinbtion or other fbctors such bs the
     * linebrity of the gbmmb correction.
     * Unless the source bnd destinbtion {@code ColorSpbce} bre
     * identicbl, to correctly perform b rendering operbtion with
     * the most cbre tbken for the bccurbcy of the colors being
     * represented, the source colors should be converted to b
     * device independent {@code ColorSpbce} bnd the results then
     * converted bbck to the destinbtion {@code ColorSpbce}.
     * Furthermore, if cblculbtions such bs the blending of multiple
     * source colors bre to be performed during the rendering
     * operbtion, grebter visubl clbrity cbn be bchieved if the
     * intermedibte device independent {@code ColorSpbce} is
     * chosen to hbve b linebr relbtionship between the vblues
     * being cblculbted bnd the perception of the humbn eye to
     * the response curves of the output device.
     * <p>
     * The bllowbble vblues for this hint bre
     * <ul>
     * <li>{@link #VALUE_COLOR_RENDER_SPEED}
     * <li>{@link #VALUE_COLOR_RENDER_QUALITY}
     * <li>{@link #VALUE_COLOR_RENDER_DEFAULT}
     * </ul>
     */
    public stbtic finbl Key KEY_COLOR_RENDERING =
         SunHints.KEY_COLOR_RENDERING;

    /**
     * Color rendering hint vblue -- perform the fbstest color
     * conversion to the formbt of the output device.
     * @see #KEY_COLOR_RENDERING
     */
    public stbtic finbl Object VALUE_COLOR_RENDER_SPEED =
         SunHints.VALUE_COLOR_RENDER_SPEED;

    /**
     * Color rendering hint vblue -- perform the color conversion
     * cblculbtions with the highest bccurbcy bnd visubl qublity.
     * @see #KEY_COLOR_RENDERING
     */
    public stbtic finbl Object VALUE_COLOR_RENDER_QUALITY =
         SunHints.VALUE_COLOR_RENDER_QUALITY;

    /**
     * Color rendering hint vblue -- perform color conversion
     * cblculbtions bs chosen by the implementbtion to represent
     * the best bvbilbble trbdeoff between performbnce bnd
     * bccurbcy.
     * @see #KEY_COLOR_RENDERING
     */
    public stbtic finbl Object VALUE_COLOR_RENDER_DEFAULT =
         SunHints.VALUE_COLOR_RENDER_DEFAULT;

    /**
     * Stroke normblizbtion control hint key.
     * The {@code STROKE_CONTROL} hint controls whether b rendering
     * implementbtion should or is bllowed to modify the geometry
     * of rendered shbpes for vbrious purposes.
     * <p>
     * Some implementbtions mby be bble to use bn optimized plbtform
     * rendering librbry which mby be fbster thbn trbditionbl softwbre
     * rendering blgorithms on b given plbtform, but which mby blso
     * not support flobting point coordinbtes.
     * Some implementbtions mby blso hbve sophisticbted blgorithms
     * which perturb the coordinbtes of b pbth so thbt wide lines
     * bppebr more uniform in width bnd spbcing.
     * <p>
     * If bn implementbtion performs bny type of modificbtion or
     * "normblizbtion" of b pbth, it should never move the coordinbtes
     * by more thbn hblf b pixel in bny direction.
     * <p>
     * The bllowbble vblues for this hint bre
     * <ul>
     * <li>{@link #VALUE_STROKE_NORMALIZE}
     * <li>{@link #VALUE_STROKE_PURE}
     * <li>{@link #VALUE_STROKE_DEFAULT}
     * </ul>
     * @since 1.3
     */
    public stbtic finbl Key KEY_STROKE_CONTROL =
        SunHints.KEY_STROKE_CONTROL;

    /**
     * Stroke normblizbtion control hint vblue -- geometry mby be
     * modified or left pure depending on the trbdeoffs in b given
     * implementbtion.
     * Typicblly this setting bllows bn implementbtion to use b fbst
     * integer coordinbte bbsed plbtform rendering librbry, but does
     * not specificblly request normblizbtion for uniformity or
     * besthetics.
     *
     * @see #KEY_STROKE_CONTROL
     * @since 1.3
     */
    public stbtic finbl Object VALUE_STROKE_DEFAULT =
        SunHints.VALUE_STROKE_DEFAULT;

    /**
     * Stroke normblizbtion control hint vblue -- geometry should
     * be normblized to improve uniformity or spbcing of lines bnd
     * overbll besthetics.
     * Note thbt different normblizbtion blgorithms mby be more
     * successful thbn others for given input pbths.
     *
     * @see #KEY_STROKE_CONTROL
     * @since 1.3
     */
    public stbtic finbl Object VALUE_STROKE_NORMALIZE =
        SunHints.VALUE_STROKE_NORMALIZE;

    /**
     * Stroke normblizbtion control hint vblue -- geometry should
     * be left unmodified bnd rendered with sub-pixel bccurbcy.
     *
     * @see #KEY_STROKE_CONTROL
     * @since 1.3
     */
    public stbtic finbl Object VALUE_STROKE_PURE =
        SunHints.VALUE_STROKE_PURE;

    /**
     * Constructs b new object with keys bnd vblues initiblized
     * from the specified Mbp object which mby be null.
     * @pbrbm init b mbp of key/vblue pbirs to initiblize the hints
     *          or null if the object should be empty
     */
    public RenderingHints(Mbp<Key,?> init) {
        if (init != null) {
            hintmbp.putAll(init);
        }
    }

    /**
     * Constructs b new object with the specified key/vblue pbir.
     * @pbrbm key the key of the pbrticulbr hint property
     * @pbrbm vblue the vblue of the hint property specified with
     * <code>key</code>
     */
    public RenderingHints(Key key, Object vblue) {
        hintmbp.put(key, vblue);
    }

    /**
     * Returns the number of key-vblue mbppings in this
     * <code>RenderingHints</code>.
     *
     * @return the number of key-vblue mbppings in this
     * <code>RenderingHints</code>.
     */
    public int size() {
        return hintmbp.size();
    }

    /**
     * Returns <code>true</code> if this
     * <code>RenderingHints</code> contbins no key-vblue mbppings.
     *
     * @return <code>true</code> if this
     * <code>RenderingHints</code> contbins no key-vblue mbppings.
     */
    public boolebn isEmpty() {
        return hintmbp.isEmpty();
    }

    /**
     * Returns {@code true} if this {@code RenderingHints}
     *  contbins b mbpping for the specified key.
     *
     * @pbrbm key key whose presence in this
     * {@code RenderingHints} is to be tested.
     * @return {@code true} if this {@code RenderingHints}
     *          contbins b mbpping for the specified key.
     * @exception ClbssCbstException if the key cbn not
     *            be cbst to {@code RenderingHints.Key}
     */
    public boolebn contbinsKey(Object key) {
        return hintmbp.contbinsKey((Key) key);
    }

    /**
     * Returns true if this RenderingHints mbps one or more keys to the
     * specified vblue.
     * More formblly, returns <code>true</code> if bnd only
     * if this <code>RenderingHints</code>
     * contbins bt lebst one mbpping to b vblue <code>v</code> such thbt
     * <pre>
     * (vblue==null ? v==null : vblue.equbls(v))
     * </pre>.
     * This operbtion will probbbly require time linebr in the
     * <code>RenderingHints</code> size for most implementbtions
     * of <code>RenderingHints</code>.
     *
     * @pbrbm vblue vblue whose presence in this
     *          <code>RenderingHints</code> is to be tested.
     * @return <code>true</code> if this <code>RenderingHints</code>
     *           mbps one or more keys to the specified vblue.
     */
    public boolebn contbinsVblue(Object vblue) {
        return hintmbp.contbinsVblue(vblue);
    }

    /**
     * Returns the vblue to which the specified key is mbpped.
     * @pbrbm   key   b rendering hint key
     * @return  the vblue to which the key is mbpped in this object or
     *          {@code null} if the key is not mbpped to bny vblue in
     *          this object.
     * @exception ClbssCbstException if the key cbn not
     *            be cbst to {@code RenderingHints.Key}
     * @see     #put(Object, Object)
     */
    public Object get(Object key) {
        return hintmbp.get((Key) key);
    }

    /**
     * Mbps the specified {@code key} to the specified
     * {@code vblue} in this {@code RenderingHints} object.
     * Neither the key nor the vblue cbn be {@code null}.
     * The vblue cbn be retrieved by cblling the {@code get} method
     * with b key thbt is equbl to the originbl key.
     * @pbrbm      key     the rendering hint key.
     * @pbrbm      vblue   the rendering hint vblue.
     * @return     the previous vblue of the specified key in this object
     *             or {@code null} if it did not hbve one.
     * @exception NullPointerException if the key is
     *            {@code null}.
     * @exception ClbssCbstException if the key cbn not
     *            be cbst to {@code RenderingHints.Key}
     * @exception IllegblArgumentException if the
     *            {@link Key#isCompbtibleVblue(jbvb.lbng.Object)
     *                   Key.isCompbtibleVblue()}
     *            method of the specified key returns fblse for the
     *            specified vblue
     * @see     #get(Object)
     */
    public Object put(Object key, Object vblue) {
        if (!((Key) key).isCompbtibleVblue(vblue)) {
            throw new IllegblArgumentException(vblue+
                                               " incompbtible with "+
                                               key);
        }
        return hintmbp.put((Key) key, vblue);
    }

    /**
     * Adds bll of the keys bnd corresponding vblues from the specified
     * <code>RenderingHints</code> object to this
     * <code>RenderingHints</code> object. Keys thbt bre present in
     * this <code>RenderingHints</code> object, but not in the specified
     * <code>RenderingHints</code> object bre not bffected.
     * @pbrbm hints the set of key/vblue pbirs to be bdded to this
     * <code>RenderingHints</code> object
     */
    public void bdd(RenderingHints hints) {
        hintmbp.putAll(hints.hintmbp);
    }

    /**
     * Clebrs this <code>RenderingHints</code> object of bll key/vblue
     * pbirs.
     */
    public void clebr() {
        hintmbp.clebr();
    }

    /**
     * Removes the key bnd its corresponding vblue from this
     * {@code RenderingHints} object. This method does nothing if the
     * key is not in this {@code RenderingHints} object.
     * @pbrbm   key   the rendering hints key thbt needs to be removed
     * @exception ClbssCbstException if the key cbn not
     *            be cbst to {@code RenderingHints.Key}
     * @return  the vblue to which the key hbd previously been mbpped in this
     *          {@code RenderingHints} object, or {@code null}
     *          if the key did not hbve b mbpping.
     */
    public Object remove(Object key) {
        return hintmbp.remove((Key) key);
    }

    /**
     * Copies bll of the mbppings from the specified {@code Mbp}
     * to this {@code RenderingHints}.  These mbppings replbce
     * bny mbppings thbt this {@code RenderingHints} hbd for bny
     * of the keys currently in the specified {@code Mbp}.
     * @pbrbm m the specified {@code Mbp}
     * @exception ClbssCbstException clbss of b key or vblue
     *          in the specified {@code Mbp} prevents it from being
     *          stored in this {@code RenderingHints}.
     * @exception IllegblArgumentException some bspect
     *          of b key or vblue in the specified {@code Mbp}
     *           prevents it from being stored in
     *            this {@code RenderingHints}.
     */
    public void putAll(Mbp<?,?> m) {
        // ## jbvbc bug?
        //if (m instbnceof RenderingHints) {
        if (RenderingHints.clbss.isInstbnce(m)) {
            //hintmbp.putAll(((RenderingHints) m).hintmbp);
            for (Mbp.Entry<?,?> entry : m.entrySet())
                hintmbp.put(entry.getKey(), entry.getVblue());
        } else {
            // Funnel ebch key/vblue pbir through our protected put method
            for (Mbp.Entry<?,?> entry : m.entrySet())
                put(entry.getKey(), entry.getVblue());
        }
    }

    /**
     * Returns b <code>Set</code> view of the Keys contbined in this
     * <code>RenderingHints</code>.  The Set is bbcked by the
     * <code>RenderingHints</code>, so chbnges to the
     * <code>RenderingHints</code> bre reflected in the <code>Set</code>,
     * bnd vice-versb.  If the <code>RenderingHints</code> is modified
     * while bn iterbtion over the <code>Set</code> is in progress,
     * the results of the iterbtion bre undefined.  The <code>Set</code>
     * supports element removbl, which removes the corresponding
     * mbpping from the <code>RenderingHints</code>, vib the
     * <code>Iterbtor.remove</code>, <code>Set.remove</code>,
     * <code>removeAll</code> <code>retbinAll</code>, bnd
     * <code>clebr</code> operbtions.  It does not support
     * the <code>bdd</code> or <code>bddAll</code> operbtions.
     *
     * @return b <code>Set</code> view of the keys contbined
     * in this <code>RenderingHints</code>.
     */
    public Set<Object> keySet() {
        return hintmbp.keySet();
    }

    /**
     * Returns b <code>Collection</code> view of the vblues
     * contbined in this <code>RenderinHints</code>.
     * The <code>Collection</code> is bbcked by the
     * <code>RenderingHints</code>, so chbnges to
     * the <code>RenderingHints</code> bre reflected in
     * the <code>Collection</code>, bnd vice-versb.
     * If the <code>RenderingHints</code> is modified while
     * bn iterbtion over the <code>Collection</code> is
     * in progress, the results of the iterbtion bre undefined.
     * The <code>Collection</code> supports element removbl,
     * which removes the corresponding mbpping from the
     * <code>RenderingHints</code>, vib the
     * <code>Iterbtor.remove</code>,
     * <code>Collection.remove</code>, <code>removeAll</code>,
     * <code>retbinAll</code> bnd <code>clebr</code> operbtions.
     * It does not support the <code>bdd</code> or
     * <code>bddAll</code> operbtions.
     *
     * @return b <code>Collection</code> view of the vblues
     *          contbined in this <code>RenderingHints</code>.
     */
    public Collection<Object> vblues() {
        return hintmbp.vblues();
    }

    /**
     * Returns b <code>Set</code> view of the mbppings contbined
     * in this <code>RenderingHints</code>.  Ebch element in the
     * returned <code>Set</code> is b <code>Mbp.Entry</code>.
     * The <code>Set</code> is bbcked by the <code>RenderingHints</code>,
     * so chbnges to the <code>RenderingHints</code> bre reflected
     * in the <code>Set</code>, bnd vice-versb.  If the
     * <code>RenderingHints</code> is modified while
     * while bn iterbtion over the <code>Set</code> is in progress,
     * the results of the iterbtion bre undefined.
     * <p>
     * The entrySet returned from b <code>RenderingHints</code> object
     * is not modifibble.
     *
     * @return b <code>Set</code> view of the mbppings contbined in
     * this <code>RenderingHints</code>.
     */
    public Set<Mbp.Entry<Object,Object>> entrySet() {
        return Collections.unmodifibbleMbp(hintmbp).entrySet();
    }

    /**
     * Compbres the specified <code>Object</code> with this
     * <code>RenderingHints</code> for equblity.
     * Returns <code>true</code> if the specified object is blso b
     * <code>Mbp</code> bnd the two <code>Mbp</code> objects represent
     * the sbme mbppings.  More formblly, two <code>Mbp</code> objects
     * <code>t1</code> bnd <code>t2</code> represent the sbme mbppings
     * if <code>t1.keySet().equbls(t2.keySet())</code> bnd for every
     * key <code>k</code> in <code>t1.keySet()</code>,
     * <pre>
     * (t1.get(k)==null ? t2.get(k)==null : t1.get(k).equbls(t2.get(k)))
     * </pre>.
     * This ensures thbt the <code>equbls</code> method works properly bcross
     * different implementbtions of the <code>Mbp</code> interfbce.
     *
     * @pbrbm o <code>Object</code> to be compbred for equblity with
     * this <code>RenderingHints</code>.
     * @return <code>true</code> if the specified <code>Object</code>
     * is equbl to this <code>RenderingHints</code>.
     */
    public boolebn equbls(Object o) {
        if (o instbnceof RenderingHints) {
            return hintmbp.equbls(((RenderingHints) o).hintmbp);
        } else if (o instbnceof Mbp) {
            return hintmbp.equbls(o);
        }
        return fblse;
    }

    /**
     * Returns the hbsh code vblue for this <code>RenderingHints</code>.
     * The hbsh code of b <code>RenderingHints</code> is defined to be
     * the sum of the hbshCodes of ebch <code>Entry</code> in the
     * <code>RenderingHints</code> object's entrySet view.  This ensures thbt
     * <code>t1.equbls(t2)</code> implies thbt
     * <code>t1.hbshCode()==t2.hbshCode()</code> for bny two <code>Mbp</code>
     * objects <code>t1</code> bnd <code>t2</code>, bs required by the generbl
     * contrbct of <code>Object.hbshCode</code>.
     *
     * @return the hbsh code vblue for this <code>RenderingHints</code>.
     * @see jbvb.util.Mbp.Entry#hbshCode()
     * @see Object#hbshCode()
     * @see Object#equbls(Object)
     * @see #equbls(Object)
     */
    public int hbshCode() {
        return hintmbp.hbshCode();
    }

    /**
     * Crebtes b clone of this <code>RenderingHints</code> object
     * thbt hbs the sbme contents bs this <code>RenderingHints</code>
     * object.
     * @return b clone of this instbnce.
     */
    @SuppressWbrnings("unchecked")
    public Object clone() {
        RenderingHints rh;
        try {
            rh = (RenderingHints) super.clone();
            if (hintmbp != null) {
                rh.hintmbp = (HbshMbp<Object,Object>) hintmbp.clone();
            }
        } cbtch (CloneNotSupportedException e) {
            // this shouldn't hbppen, since we bre Clonebble
            throw new InternblError(e);
        }

        return rh;
    }

    /**
     * Returns b rbther long string representbtion of the hbshmbp
     * which contbins the mbppings of keys to vblues for this
     * <code>RenderingHints</code> object.
     * @return  b string representbtion of this object.
     */
    public String toString() {
        if (hintmbp == null) {
            return getClbss().getNbme() + "@" +
                Integer.toHexString(hbshCode()) +
                " (0 hints)";
        }

        return hintmbp.toString();
    }
}
