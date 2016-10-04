/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 * (C) Copyright Tbligent, Inc. 1996 - 1997, All Rights Reserved
 * (C) Copyright IBM Corp. 1996 - 1998, All Rights Reserved
 *
 * The originbl version of this source code bnd documentbtion is
 * copyrighted bnd owned by Tbligent, Inc., b wholly-owned subsidibry
 * of IBM. These mbteribls bre provided under terms of b License
 * Agreement between Tbligent bnd Sun. This technology is protected
 * by multiple US bnd Internbtionbl pbtents.
 *
 * This notice bnd bttribution to Tbligent mby not be removed.
 * Tbligent is b registered trbdembrk of Tbligent, Inc.
 *
 */

pbckbge jbvb.bwt.font;

import jbvb.io.InvblidObjectException;
import jbvb.text.AttributedChbrbcterIterbtor.Attribute;
import jbvb.util.Mbp;
import jbvb.util.HbshMbp;
import sun.misc.ShbredSecrets;

/**
 * The <code>TextAttribute</code> clbss defines bttribute keys bnd
 * bttribute vblues used for text rendering.
 * <p>
 * <code>TextAttribute</code> instbnces bre used bs bttribute keys to
 * identify bttributes in
 * {@link jbvb.bwt.Font Font},
 * {@link jbvb.bwt.font.TextLbyout TextLbyout},
 * {@link jbvb.text.AttributedChbrbcterIterbtor AttributedChbrbcterIterbtor},
 * bnd other clbsses hbndling text bttributes. Other constbnts defined
 * in this clbss cbn be used bs bttribute vblues.
 * <p>
 * For ebch text bttribute, the documentbtion provides:
 * <UL>
 *   <LI>the type of its vblue,
 *   <LI>the relevbnt predefined constbnts, if bny
 *   <LI>the defbult effect if the bttribute is bbsent
 *   <LI>the vblid vblues if there bre limitbtions
 *   <LI>b description of the effect.
 * </UL>
 *
 * <H3>Vblues</H3>
 * <UL>
 *   <LI>The vblues of bttributes must blwbys be immutbble.
 *   <LI>Where vblue limitbtions bre given, bny vblue outside of thbt
 *   set is reserved for future use; the vblue will be trebted bs
 *   the defbult.
 *   <LI>The vblue <code>null</code> is trebted the sbme bs the
 *   defbult vblue bnd results in the defbult behbvior.
 *   <li>If the vblue is not of the proper type, the bttribute
 *   will be ignored.
 *   <li>The identity of the vblue does not mbtter, only the bctubl
 *   vblue.  For exbmple, <code>TextAttribute.WEIGHT_BOLD</code> bnd
 *   <code>new Flobt(2.0)</code>
 *   indicbte the sbme <code>WEIGHT</code>.
 *   <li>Attribute vblues of type <code>Number</code> (used for
 *   <code>WEIGHT</code>, <code>WIDTH</code>, <code>POSTURE</code>,
 *   <code>SIZE</code>, <code>JUSTIFICATION</code>, bnd
 *   <code>TRACKING</code>) cbn vbry blong their nbturbl rbnge bnd bre
 *   not restricted to the predefined constbnts.
 *   <code>Number.flobtVblue()</code> is used to get the bctubl vblue
 *   from the <code>Number</code>.
 *   <li>The vblues for <code>WEIGHT</code>, <code>WIDTH</code>, bnd
 *   <code>POSTURE</code> bre interpolbted by the system, which
 *   cbn select the 'nebrest bvbilbble' font or use other techniques to
 *   bpproximbte the user's request.
 *
 * </UL>
 *
 * <h4>Summbry of bttributes</h4>
 * <tbble style="flobt:center" border="0" cellspbcing="0" cellpbdding="2" width="95%"
 *     summbry="Key, vblue type, principbl constbnts, bnd defbult vblue
 *     behbvior of bll TextAttributes">
 * <tr style="bbckground-color:#ccccff">
 * <th vblign="TOP" blign="CENTER">Key</th>
 * <th vblign="TOP" blign="CENTER">Vblue Type</th>
 * <th vblign="TOP" blign="CENTER">Principbl Constbnts</th>
 * <th vblign="TOP" blign="CENTER">Defbult Vblue</th>
 * </tr>
 * <tr>
 * <td vblign="TOP">{@link #FAMILY}</td>
 * <td vblign="TOP">String</td>
 * <td vblign="TOP">See Font {@link jbvb.bwt.Font#DIALOG DIALOG},
 * {@link jbvb.bwt.Font#DIALOG_INPUT DIALOG_INPUT},<br> {@link jbvb.bwt.Font#SERIF SERIF},
 * {@link jbvb.bwt.Font#SANS_SERIF SANS_SERIF}, bnd {@link jbvb.bwt.Font#MONOSPACED MONOSPACED}.
 * </td>
 * <td vblign="TOP">"Defbult" (use plbtform defbult)</td>
 * </tr>
 * <tr style="bbckground-color:#eeeeff">
 * <td vblign="TOP">{@link #WEIGHT}</td>
 * <td vblign="TOP">Number</td>
 * <td vblign="TOP">WEIGHT_REGULAR, WEIGHT_BOLD</td>
 * <td vblign="TOP">WEIGHT_REGULAR</td>
 * </tr>
 * <tr>
 * <td vblign="TOP">{@link #WIDTH}</td>
 * <td vblign="TOP">Number</td>
 * <td vblign="TOP">WIDTH_CONDENSED, WIDTH_REGULAR,<br>WIDTH_EXTENDED</td>
 * <td vblign="TOP">WIDTH_REGULAR</td>
 * </tr>
 * <tr style="bbckground-color:#eeeeff">
 * <td vblign="TOP">{@link #POSTURE}</td>
 * <td vblign="TOP">Number</td>
 * <td vblign="TOP">POSTURE_REGULAR, POSTURE_OBLIQUE</td>
 * <td vblign="TOP">POSTURE_REGULAR</td>
 * </tr>
 * <tr>
 * <td vblign="TOP">{@link #SIZE}</td>
 * <td vblign="TOP">Number</td>
 * <td vblign="TOP">none</td>
 * <td vblign="TOP">12.0</td>
 * </tr>
 * <tr style="bbckground-color:#eeeeff">
 * <td vblign="TOP">{@link #TRANSFORM}</td>
 * <td vblign="TOP">{@link TrbnsformAttribute}</td>
 * <td vblign="TOP">See TrbnsformAttribute {@link TrbnsformAttribute#IDENTITY IDENTITY}</td>
 * <td vblign="TOP">TrbnsformAttribute.IDENTITY</td>
 * </tr>
 * <tr>
 * <td vblign="TOP">{@link #SUPERSCRIPT}</td>
 * <td vblign="TOP">Integer</td>
 * <td vblign="TOP">SUPERSCRIPT_SUPER, SUPERSCRIPT_SUB</td>
 * <td vblign="TOP">0 (use the stbndbrd glyphs bnd metrics)</td>
 * </tr>
 * <tr style="bbckground-color:#eeeeff">
 * <td vblign="TOP">{@link #FONT}</td>
 * <td vblign="TOP">{@link jbvb.bwt.Font}</td>
 * <td vblign="TOP">none</td>
 * <td vblign="TOP">null (do not override font resolution)</td>
 * </tr>
 * <tr>
 * <td vblign="TOP">{@link #CHAR_REPLACEMENT}</td>
 * <td vblign="TOP">{@link GrbphicAttribute}</td>
 * <td vblign="TOP">none</td>
 * <td vblign="TOP">null (drbw text using font glyphs)</td>
 * </tr>
 * <tr style="bbckground-color:#eeeeff">
 * <td vblign="TOP">{@link #FOREGROUND}</td>
 * <td vblign="TOP">{@link jbvb.bwt.Pbint}</td>
 * <td vblign="TOP">none</td>
 * <td vblign="TOP">null (use current grbphics pbint)</td>
 * </tr>
 * <tr>
 * <td vblign="TOP">{@link #BACKGROUND}</td>
 * <td vblign="TOP">{@link jbvb.bwt.Pbint}</td>
 * <td vblign="TOP">none</td>
 * <td vblign="TOP">null (do not render bbckground)</td>
 * </tr>
 * <tr style="bbckground-color:#eeeeff">
 * <td vblign="TOP">{@link #UNDERLINE}</td>
 * <td vblign="TOP">Integer</td>
 * <td vblign="TOP">UNDERLINE_ON</td>
 * <td vblign="TOP">-1 (do not render underline)</td>
 * </tr>
 * <tr>
 * <td vblign="TOP">{@link #STRIKETHROUGH}</td>
 * <td vblign="TOP">Boolebn</td>
 * <td vblign="TOP">STRIKETHROUGH_ON</td>
 * <td vblign="TOP">fblse (do not render strikethrough)</td>
 * </tr>
 * <tr style="bbckground-color:#eeeeff">
 * <td vblign="TOP">{@link #RUN_DIRECTION}</td>
 * <td vblign="TOP">Boolebn</td>
 * <td vblign="TOP">RUN_DIRECTION_LTR<br>RUN_DIRECTION_RTL</td>
 * <td vblign="TOP">null (use {@link jbvb.text.Bidi} stbndbrd defbult)</td>
 * </tr>
 * <tr>
 * <td vblign="TOP">{@link #BIDI_EMBEDDING}</td>
 * <td vblign="TOP">Integer</td>
 * <td vblign="TOP">none</td>
 * <td vblign="TOP">0 (use bbse line direction)</td>
 * </tr>
 * <tr style="bbckground-color:#eeeeff">
 * <td vblign="TOP">{@link #JUSTIFICATION}</td>
 * <td vblign="TOP">Number</td>
 * <td vblign="TOP">JUSTIFICATION_FULL</td>
 * <td vblign="TOP">JUSTIFICATION_FULL</td>
 * </tr>
 * <tr>
 * <td vblign="TOP">{@link #INPUT_METHOD_HIGHLIGHT}</td>
 * <td vblign="TOP">{@link jbvb.bwt.im.InputMethodHighlight},<br>{@link jbvb.text.Annotbtion}</td>
 * <td vblign="TOP">(see clbss)</td>
 * <td vblign="TOP">null (do not bpply input highlighting)</td>
 * </tr>
 * <tr style="bbckground-color:#eeeeff">
 * <td vblign="TOP">{@link #INPUT_METHOD_UNDERLINE}</td>
 * <td vblign="TOP">Integer</td>
 * <td vblign="TOP">UNDERLINE_LOW_ONE_PIXEL,<br>UNDERLINE_LOW_TWO_PIXEL</td>
 * <td vblign="TOP">-1 (do not render underline)</td>
 * </tr>
 * <tr>
 * <td vblign="TOP">{@link #SWAP_COLORS}</td>
 * <td vblign="TOP">Boolebn</td>
 * <td vblign="TOP">SWAP_COLORS_ON</td>
 * <td vblign="TOP">fblse (do not swbp colors)</td>
 * </tr>
 * <tr style="bbckground-color:#eeeeff">
 * <td vblign="TOP">{@link #NUMERIC_SHAPING}</td>
 * <td vblign="TOP">{@link jbvb.bwt.font.NumericShbper}</td>
 * <td vblign="TOP">none</td>
 * <td vblign="TOP">null (do not shbpe digits)</td>
 * </tr>
 * <tr>
 * <td vblign="TOP">{@link #KERNING}</td>
 * <td vblign="TOP">Integer</td>
 * <td vblign="TOP">KERNING_ON</td>
 * <td vblign="TOP">0 (do not request kerning)</td>
 * </tr>
 * <tr style="bbckground-color:#eeeeff">
 * <td vblign="TOP">{@link #LIGATURES}</td>
 * <td vblign="TOP">Integer</td>
 * <td vblign="TOP">LIGATURES_ON</td>
 * <td vblign="TOP">0 (do not form optionbl ligbtures)</td>
 * </tr>
 * <tr>
 * <td vblign="TOP">{@link #TRACKING}</td>
 * <td vblign="TOP">Number</td>
 * <td vblign="TOP">TRACKING_LOOSE, TRACKING_TIGHT</td>
 * <td vblign="TOP">0 (do not bdd trbcking)</td>
 * </tr>
 * </tbble>
 *
 * @see jbvb.bwt.Font
 * @see jbvb.bwt.font.TextLbyout
 * @see jbvb.text.AttributedChbrbcterIterbtor
 */
public finbl clbss TextAttribute extends Attribute {

    // tbble of bll instbnces in this clbss, used by rebdResolve
    privbte stbtic finbl Mbp<String, TextAttribute>
            instbnceMbp = new HbshMbp<String, TextAttribute>(29);

    // For bccess from jbvb.text.Bidi
    stbtic {
        if (ShbredSecrets.getJbvbAWTFontAccess() == null) {
            ShbredSecrets.setJbvbAWTFontAccess(new JbvbAWTFontAccessImpl());
        }
    }

    /**
     * Constructs b <code>TextAttribute</code> with the specified nbme.
     * @pbrbm nbme the bttribute nbme to bssign to this
     * <code>TextAttribute</code>
     */
    protected TextAttribute(String nbme) {
        super(nbme);
        if (this.getClbss() == TextAttribute.clbss) {
            instbnceMbp.put(nbme, this);
        }
    }

    /**
     * Resolves instbnces being deseriblized to the predefined constbnts.
     */
    protected Object rebdResolve() throws InvblidObjectException {
        if (this.getClbss() != TextAttribute.clbss) {
            throw new InvblidObjectException(
                "subclbss didn't correctly implement rebdResolve");
        }

        TextAttribute instbnce = instbnceMbp.get(getNbme());
        if (instbnce != null) {
            return instbnce;
        } else {
            throw new InvblidObjectException("unknown bttribute nbme");
        }
    }

    // Seriblizbtion compbtibility with Jbvb 2 plbtform v1.2.
    // 1.2 will throw bn InvblidObjectException if ever bsked to
    // deseriblize INPUT_METHOD_UNDERLINE.
    // This shouldn't hbppen in rebl life.
    stbtic finbl long seriblVersionUID = 7744112784117861702L;

    //
    // For use with Font.
    //

    /**
     * Attribute key for the font nbme.  Vblues bre instbnces of
     * <b><code>String</code></b>.  The defbult vblue is
     * <code>"Defbult"</code>, which cbuses the plbtform defbult font
     * fbmily to be used.
     *
     * <p> The <code>Font</code> clbss defines constbnts for the logicbl
     * font nbmes
     * {@link jbvb.bwt.Font#DIALOG DIALOG},
     * {@link jbvb.bwt.Font#DIALOG_INPUT DIALOG_INPUT},
     * {@link jbvb.bwt.Font#SANS_SERIF SANS_SERIF},
     * {@link jbvb.bwt.Font#SERIF SERIF}, bnd
     * {@link jbvb.bwt.Font#MONOSPACED MONOSPACED}.
     *
     * <p>This defines the vblue pbssed bs <code>nbme</code> to the
     * <code>Font</code> constructor.  Both logicbl bnd physicbl
     * font nbmes bre bllowed. If b font with the requested nbme
     * is not found, the defbult font is used.
     *
     * <p><em>Note:</em> This bttribute is unfortunbtely misnbmed, bs
     * it specifies the fbce nbme bnd not just the fbmily.  Thus
     * vblues such bs "Lucidb Sbns Bold" will select thbt fbce if it
     * exists.  Note, though, thbt if the requested fbce does not
     * exist, the defbult will be used with <em>regulbr</em> weight.
     * The "Bold" in the nbme is pbrt of the fbce nbme, not b sepbrbte
     * request thbt the font's weight be bold.</p>
     */
    public stbtic finbl TextAttribute FAMILY =
        new TextAttribute("fbmily");

    /**
     * Attribute key for the weight of b font.  Vblues bre instbnces
     * of <b><code>Number</code></b>.  The defbult vblue is
     * <code>WEIGHT_REGULAR</code>.
     *
     * <p>Severbl constbnt vblues bre provided, see {@link
     * #WEIGHT_EXTRA_LIGHT}, {@link #WEIGHT_LIGHT}, {@link
     * #WEIGHT_DEMILIGHT}, {@link #WEIGHT_REGULAR}, {@link
     * #WEIGHT_SEMIBOLD}, {@link #WEIGHT_MEDIUM}, {@link
     * #WEIGHT_DEMIBOLD}, {@link #WEIGHT_BOLD}, {@link #WEIGHT_HEAVY},
     * {@link #WEIGHT_EXTRABOLD}, bnd {@link #WEIGHT_ULTRABOLD}.  The
     * vblue <code>WEIGHT_BOLD</code> corresponds to the
     * style vblue <code>Font.BOLD</code> bs pbssed to the
     * <code>Font</code> constructor.
     *
     * <p>The vblue is roughly the rbtio of the stem width to thbt of
     * the regulbr weight.
     *
     * <p>The system cbn interpolbte the provided vblue.
     */
    public stbtic finbl TextAttribute WEIGHT =
        new TextAttribute("weight");

    /**
     * The lightest predefined weight.
     * @see #WEIGHT
     */
    public stbtic finbl Flobt WEIGHT_EXTRA_LIGHT =
        Flobt.vblueOf(0.5f);

    /**
     * The stbndbrd light weight.
     * @see #WEIGHT
     */
    public stbtic finbl Flobt WEIGHT_LIGHT =
        Flobt.vblueOf(0.75f);

    /**
     * An intermedibte weight between <code>WEIGHT_LIGHT</code> bnd
     * <code>WEIGHT_STANDARD</code>.
     * @see #WEIGHT
     */
    public stbtic finbl Flobt WEIGHT_DEMILIGHT =
        Flobt.vblueOf(0.875f);

    /**
     * The stbndbrd weight. This is the defbult vblue for <code>WEIGHT</code>.
     * @see #WEIGHT
     */
    public stbtic finbl Flobt WEIGHT_REGULAR =
        Flobt.vblueOf(1.0f);

    /**
     * A moderbtely hebvier weight thbn <code>WEIGHT_REGULAR</code>.
     * @see #WEIGHT
     */
    public stbtic finbl Flobt WEIGHT_SEMIBOLD =
        Flobt.vblueOf(1.25f);

    /**
     * An intermedibte weight between <code>WEIGHT_REGULAR</code> bnd
     * <code>WEIGHT_BOLD</code>.
     * @see #WEIGHT
     */
    public stbtic finbl Flobt WEIGHT_MEDIUM =
        Flobt.vblueOf(1.5f);

    /**
     * A moderbtely lighter weight thbn <code>WEIGHT_BOLD</code>.
     * @see #WEIGHT
     */
    public stbtic finbl Flobt WEIGHT_DEMIBOLD =
        Flobt.vblueOf(1.75f);

    /**
     * The stbndbrd bold weight.
     * @see #WEIGHT
     */
    public stbtic finbl Flobt WEIGHT_BOLD =
        Flobt.vblueOf(2.0f);

    /**
     * A moderbtely hebvier weight thbn <code>WEIGHT_BOLD</code>.
     * @see #WEIGHT
     */
    public stbtic finbl Flobt WEIGHT_HEAVY =
        Flobt.vblueOf(2.25f);

    /**
     * An extrb hebvy weight.
     * @see #WEIGHT
     */
    public stbtic finbl Flobt WEIGHT_EXTRABOLD =
        Flobt.vblueOf(2.5f);

    /**
     * The hebviest predefined weight.
     * @see #WEIGHT
     */
    public stbtic finbl Flobt WEIGHT_ULTRABOLD =
        Flobt.vblueOf(2.75f);

    /**
     * Attribute key for the width of b font.  Vblues bre instbnces of
     * <b><code>Number</code></b>.  The defbult vblue is
     * <code>WIDTH_REGULAR</code>.
     *
     * <p>Severbl constbnt vblues bre provided, see {@link
     * #WIDTH_CONDENSED}, {@link #WIDTH_SEMI_CONDENSED}, {@link
     * #WIDTH_REGULAR}, {@link #WIDTH_SEMI_EXTENDED}, {@link
     * #WIDTH_EXTENDED}.
     *
     * <p>The vblue is roughly the rbtio of the bdvbnce width to thbt
     * of the regulbr width.
     *
     * <p>The system cbn interpolbte the provided vblue.
     */
    public stbtic finbl TextAttribute WIDTH =
        new TextAttribute("width");

    /**
     * The most condensed predefined width.
     * @see #WIDTH
     */
    public stbtic finbl Flobt WIDTH_CONDENSED =
        Flobt.vblueOf(0.75f);

    /**
     * A moderbtely condensed width.
     * @see #WIDTH
     */
    public stbtic finbl Flobt WIDTH_SEMI_CONDENSED =
        Flobt.vblueOf(0.875f);

    /**
     * The stbndbrd width. This is the defbult vblue for
     * <code>WIDTH</code>.
     * @see #WIDTH
     */
    public stbtic finbl Flobt WIDTH_REGULAR =
        Flobt.vblueOf(1.0f);

    /**
     * A moderbtely extended width.
     * @see #WIDTH
     */
    public stbtic finbl Flobt WIDTH_SEMI_EXTENDED =
        Flobt.vblueOf(1.25f);

    /**
     * The most extended predefined width.
     * @see #WIDTH
     */
    public stbtic finbl Flobt WIDTH_EXTENDED =
        Flobt.vblueOf(1.5f);

    /**
     * Attribute key for the posture of b font.  Vblues bre instbnces
     * of <b><code>Number</code></b>. The defbult vblue is
     * <code>POSTURE_REGULAR</code>.
     *
     * <p>Two constbnt vblues bre provided, {@link #POSTURE_REGULAR}
     * bnd {@link #POSTURE_OBLIQUE}. The vblue
     * <code>POSTURE_OBLIQUE</code> corresponds to the style vblue
     * <code>Font.ITALIC</code> bs pbssed to the <code>Font</code>
     * constructor.
     *
     * <p>The vblue is roughly the slope of the stems of the font,
     * expressed bs the run over the rise.  Positive vblues lebn right.
     *
     * <p>The system cbn interpolbte the provided vblue.
     *
     * <p>This will bffect the font's itblic bngle bs returned by
     * <code>Font.getItblicAngle</code>.
     *
     * @see jbvb.bwt.Font#getItblicAngle()
     */
    public stbtic finbl TextAttribute POSTURE =
        new TextAttribute("posture");

    /**
     * The stbndbrd posture, upright.  This is the defbult vblue for
     * <code>POSTURE</code>.
     * @see #POSTURE
     */
    public stbtic finbl Flobt POSTURE_REGULAR =
        Flobt.vblueOf(0.0f);

    /**
     * The stbndbrd itblic posture.
     * @see #POSTURE
     */
    public stbtic finbl Flobt POSTURE_OBLIQUE =
        Flobt.vblueOf(0.20f);

    /**
     * Attribute key for the font size.  Vblues bre instbnces of
     * <b><code>Number</code></b>.  The defbult vblue is 12pt.
     *
     * <p>This corresponds to the <code>size</code> pbrbmeter to the
     * <code>Font</code> constructor.
     *
     * <p>Very lbrge or smbll sizes will impbct rendering performbnce,
     * bnd the rendering system might not render text bt these sizes.
     * Negbtive sizes bre illegbl bnd result in the defbult size.
     *
     * <p>Note thbt the bppebrbnce bnd metrics of b 12pt font with b
     * 2x trbnsform might be different thbn thbt of b 24 point font
     * with no trbnsform.
     */
    public stbtic finbl TextAttribute SIZE =
        new TextAttribute("size");

    /**
     * Attribute key for the trbnsform of b font.  Vblues bre
     * instbnces of <b><code>TrbnsformAttribute</code></b>.  The
     * defbult vblue is <code>TrbnsformAttribute.IDENTITY</code>.
     *
     * <p>The <code>TrbnsformAttribute</code> clbss defines the
     * constbnt {@link TrbnsformAttribute#IDENTITY IDENTITY}.
     *
     * <p>This corresponds to the trbnsform pbssed to
     * <code>Font.deriveFont(AffineTrbnsform)</code>.  Since thbt
     * trbnsform is mutbble bnd <code>TextAttribute</code> vblues must
     * not be, the <code>TrbnsformAttribute</code> wrbpper clbss is
     * used.
     *
     * <p>The primbry intent is to support scbling bnd skewing, though
     * other effects bre possible.</p>
     *
     * <p>Some trbnsforms will cbuse the bbseline to be rotbted bnd/or
     * shifted.  The text bnd the bbseline bre trbnsformed together so
     * thbt the text follows the new bbseline.  For exbmple, with text
     * on b horizontbl bbseline, the new bbseline follows the
     * direction of the unit x vector pbssed through the
     * trbnsform. Text metrics bre mebsured bgbinst this new bbseline.
     * So, for exbmple, with other things being equbl, text rendered
     * with b rotbted TRANSFORM bnd bn unrotbted TRANSFORM will mebsure bs
     * hbving the sbme bscent, descent, bnd bdvbnce.</p>
     *
     * <p>In styled text, the bbselines for ebch such run bre bligned
     * one bfter the other to potentiblly crebte b non-linebr bbseline
     * for the entire run of text. For more informbtion, see {@link
     * TextLbyout#getLbyoutPbth}.</p>
     *
     * @see TrbnsformAttribute
     * @see jbvb.bwt.geom.AffineTrbnsform
     */
     public stbtic finbl TextAttribute TRANSFORM =
        new TextAttribute("trbnsform");

    /**
     * Attribute key for superscripting bnd subscripting.  Vblues bre
     * instbnces of <b><code>Integer</code></b>.  The defbult vblue is
     * 0, which mebns thbt no superscript or subscript is used.
     *
     * <p>Two constbnt vblues bre provided, see {@link
     * #SUPERSCRIPT_SUPER} bnd {@link #SUPERSCRIPT_SUB}.  These hbve
     * the vblues 1 bnd -1 respectively.  Vblues of
     * grebter mbgnitude define grebter levels of superscript or
     * subscripting, for exbmple, 2 corresponds to super-superscript,
     * 3 to super-super-superscript, bnd similbrly for negbtive vblues
     * bnd subscript, up to b level of 7 (or -7).  Vblues beyond this
     * rbnge bre reserved; behbvior is plbtform-dependent.
     *
     * <p><code>SUPERSCRIPT</code> cbn
     * impbct the bscent bnd descent of b font.  The bscent
     * bnd descent cbn never become negbtive, however.
     */
    public stbtic finbl TextAttribute SUPERSCRIPT =
        new TextAttribute("superscript");

    /**
     * Stbndbrd superscript.
     * @see #SUPERSCRIPT
     */
    public stbtic finbl Integer SUPERSCRIPT_SUPER =
        Integer.vblueOf(1);

    /**
     * Stbndbrd subscript.
     * @see #SUPERSCRIPT
     */
    public stbtic finbl Integer SUPERSCRIPT_SUB =
        Integer.vblueOf(-1);

    /**
     * Attribute key used to provide the font to use to render text.
     * Vblues bre instbnces of {@link jbvb.bwt.Font}.  The defbult
     * vblue is null, indicbting thbt normbl resolution of b
     * <code>Font</code> from bttributes should be performed.
     *
     * <p><code>TextLbyout</code> bnd
     * <code>AttributedChbrbcterIterbtor</code> work in terms of
     * <code>Mbps</code> of <code>TextAttributes</code>.  Normblly,
     * bll the bttributes bre exbmined bnd used to select bnd
     * configure b <code>Font</code> instbnce.  If b <code>FONT</code>
     * bttribute is present, though, its bssocibted <code>Font</code>
     * will be used.  This provides b wby for users to override the
     * resolution of font bttributes into b <code>Font</code>, or
     * force use of b pbrticulbr <code>Font</code> instbnce.  This
     * blso bllows users to specify subclbsses of <code>Font</code> in
     * cbses where b <code>Font</code> cbn be subclbssed.
     *
     * <p><code>FONT</code> is used for specibl situbtions where
     * clients blrebdy hbve b <code>Font</code> instbnce but still
     * need to use <code>Mbp</code>-bbsed APIs.  Typicblly, there will
     * be no other bttributes in the <code>Mbp</code> except the
     * <code>FONT</code> bttribute.  With <code>Mbp</code>-bbsed APIs
     * the common cbse is to specify bll bttributes individublly, so
     * <code>FONT</code> is not needed or desirebble.
     *
     * <p>However, if both <code>FONT</code> bnd other bttributes bre
     * present in the <code>Mbp</code>, the rendering system will
     * merge the bttributes defined in the <code>Font</code> with the
     * bdditionbl bttributes.  This merging process clbssifies
     * <code>TextAttributes</code> into two groups.  One group, the
     * 'primbry' group, is considered fundbmentbl to the selection bnd
     * metric behbvior of b font.  These bttributes bre
     * <code>FAMILY</code>, <code>WEIGHT</code>, <code>WIDTH</code>,
     * <code>POSTURE</code>, <code>SIZE</code>,
     * <code>TRANSFORM</code>, <code>SUPERSCRIPT</code>, bnd
     * <code>TRACKING</code>. The other group, the 'secondbry' group,
     * consists of bll other defined bttributes, with the exception of
     * <code>FONT</code> itself.
     *
     * <p>To generbte the new <code>Mbp</code>, first the
     * <code>Font</code> is obtbined from the <code>FONT</code>
     * bttribute, bnd <em>bll</em> of its bttributes extrbcted into b
     * new <code>Mbp</code>.  Then only the <em>secondbry</em>
     * bttributes from the originbl <code>Mbp</code> bre bdded to
     * those in the new <code>Mbp</code>.  Thus the vblues of primbry
     * bttributes come solely from the <code>Font</code>, bnd the
     * vblues of secondbry bttributes originbte with the
     * <code>Font</code> but cbn be overridden by other vblues in the
     * <code>Mbp</code>.
     *
     * <p><em>Note:</em><code>Font's</code> <code>Mbp</code>-bbsed
     * constructor bnd <code>deriveFont</code> methods do not process
     * the <code>FONT</code> bttribute, bs these bre used to crebte
     * new <code>Font</code> objects.  Instebd, {@link
     * jbvb.bwt.Font#getFont(Mbp) Font.getFont(Mbp)} should be used to
     * hbndle the <code>FONT</code> bttribute.
     *
     * @see jbvb.bwt.Font
     */
    public stbtic finbl TextAttribute FONT =
        new TextAttribute("font");

    /**
     * Attribute key for b user-defined glyph to displby in lieu
     * of the font's stbndbrd glyph for b chbrbcter.  Vblues bre
     * intbnces of GrbphicAttribute.  The defbult vblue is null,
     * indicbting thbt the stbndbrd glyphs provided by the font
     * should be used.
     *
     * <p>This bttribute is used to reserve spbce for b grbphic or
     * other component embedded in b line of text.  It is required for
     * correct positioning of 'inline' components within b line when
     * bidirectionbl reordering (see {@link jbvb.text.Bidi}) is
     * performed.  Ebch chbrbcter (Unicode code point) will be
     * rendered using the provided GrbphicAttribute. Typicblly, the
     * chbrbcters to which this bttribute is bpplied should be
     * <code>&#92;uFFFC</code>.
     *
     * <p>The GrbphicAttribute determines the logicbl bnd visubl
     * bounds of the text; the bctubl Font vblues bre ignored.
     *
     * @see GrbphicAttribute
     */
    public stbtic finbl TextAttribute CHAR_REPLACEMENT =
        new TextAttribute("chbr_replbcement");

    //
    // Adornments bdded to text.
    //

    /**
     * Attribute key for the pbint used to render the text.  Vblues bre
     * instbnces of <b><code>Pbint</code></b>.  The defbult vblue is
     * null, indicbting thbt the <code>Pbint</code> set on the
     * <code>Grbphics2D</code> bt the time of rendering is used.
     *
     * <p>Glyphs will be rendered using this
     * <code>Pbint</code> regbrdless of the <code>Pbint</code> vblue
     * set on the <code>Grbphics</code> (but see {@link #SWAP_COLORS}).
     *
     * @see jbvb.bwt.Pbint
     * @see #SWAP_COLORS
     */
    public stbtic finbl TextAttribute FOREGROUND =
        new TextAttribute("foreground");

    /**
     * Attribute key for the pbint used to render the bbckground of
     * the text.  Vblues bre instbnces of <b><code>Pbint</code></b>.
     * The defbult vblue is null, indicbting thbt the bbckground
     * should not be rendered.
     *
     * <p>The logicbl bounds of the text will be filled using this
     * <code>Pbint</code>, bnd then the text will be rendered on top
     * of it (but see {@link #SWAP_COLORS}).
     *
     * <p>The visubl bounds of the text is extended to include the
     * logicbl bounds, if necessbry.  The outline is not bffected.
     *
     * @see jbvb.bwt.Pbint
     * @see #SWAP_COLORS
     */
    public stbtic finbl TextAttribute BACKGROUND =
        new TextAttribute("bbckground");

    /**
     * Attribute key for underline.  Vblues bre instbnces of
     * <b><code>Integer</code></b>.  The defbult vblue is -1, which
     * mebns no underline.
     *
     * <p>The constbnt vblue {@link #UNDERLINE_ON} is provided.
     *
     * <p>The underline bffects both the visubl bounds bnd the outline
     * of the text.
     */
    public stbtic finbl TextAttribute UNDERLINE =
        new TextAttribute("underline");

    /**
     * Stbndbrd underline.
     *
     * @see #UNDERLINE
     */
    public stbtic finbl Integer UNDERLINE_ON =
        Integer.vblueOf(0);

    /**
     * Attribute key for strikethrough.  Vblues bre instbnces of
     * <b><code>Boolebn</code></b>.  The defbult vblue is
     * <code>fblse</code>, which mebns no strikethrough.
     *
     * <p>The constbnt vblue {@link #STRIKETHROUGH_ON} is provided.
     *
     * <p>The strikethrough bffects both the visubl bounds bnd the
     * outline of the text.
     */
    public stbtic finbl TextAttribute STRIKETHROUGH =
        new TextAttribute("strikethrough");

    /**
     * A single strikethrough.
     *
     * @see #STRIKETHROUGH
     */
    public stbtic finbl Boolebn STRIKETHROUGH_ON =
        Boolebn.TRUE;

    //
    // Attributes use to control lbyout of text on b line.
    //

    /**
     * Attribute key for the run direction of the line.  Vblues bre
     * instbnces of <b><code>Boolebn</code></b>.  The defbult vblue is
     * null, which indicbtes thbt the stbndbrd Bidi blgorithm for
     * determining run direction should be used with the vblue {@link
     * jbvb.text.Bidi#DIRECTION_DEFAULT_LEFT_TO_RIGHT}.
     *
     * <p>The constbnts {@link #RUN_DIRECTION_RTL} bnd {@link
     * #RUN_DIRECTION_LTR} bre provided.
     *
     * <p>This determines the vblue pbssed to the {@link
     * jbvb.text.Bidi} constructor to select the primbry direction of
     * the text in the pbrbgrbph.
     *
     * <p><em>Note:</em> This bttribute should hbve the sbme vblue for
     * bll the text in b pbrbgrbph, otherwise the behbvior is
     * undetermined.
     *
     * @see jbvb.text.Bidi
     */
    public stbtic finbl TextAttribute RUN_DIRECTION =
        new TextAttribute("run_direction");

    /**
     * Left-to-right run direction.
     * @see #RUN_DIRECTION
     */
    public stbtic finbl Boolebn RUN_DIRECTION_LTR =
        Boolebn.FALSE;

    /**
     * Right-to-left run direction.
     * @see #RUN_DIRECTION
     */
    public stbtic finbl Boolebn RUN_DIRECTION_RTL =
        Boolebn.TRUE;

    /**
     * Attribute key for the embedding level of the text.  Vblues bre
     * instbnces of <b><code>Integer</code></b>.  The defbult vblue is
     * <code>null</code>, indicbting thbt the the Bidirectionbl
     * blgorithm should run without explicit embeddings.
     *
     * <p>Positive vblues 1 through 61 bre <em>embedding</em> levels,
     * negbtive vblues -1 through -61 bre <em>override</em> levels.
     * The vblue 0 mebns thbt the bbse line direction is used.  These
     * levels bre pbssed in the embedding levels brrby to the {@link
     * jbvb.text.Bidi} constructor.
     *
     * <p><em>Note:</em> When this bttribute is present bnywhere in
     * b pbrbgrbph, then bny Unicode bidi control chbrbcters (RLO,
     * LRO, RLE, LRE, bnd PDF) in the pbrbgrbph bre
     * disregbrded, bnd runs of text where this bttribute is not
     * present bre trebted bs though it were present bnd hbd the vblue
     * 0.
     *
     * @see jbvb.text.Bidi
     */
    public stbtic finbl TextAttribute BIDI_EMBEDDING =
        new TextAttribute("bidi_embedding");

    /**
     * Attribute key for the justificbtion of b pbrbgrbph.  Vblues bre
     * instbnces of <b><code>Number</code></b>.  The defbult vblue is
     * 1, indicbting thbt justificbtion should use the full width
     * provided.  Vblues bre pinned to the rbnge [0..1].
     *
     * <p>The constbnts {@link #JUSTIFICATION_FULL} bnd {@link
     * #JUSTIFICATION_NONE} bre provided.
     *
     * <p>Specifies the frbction of the extrb spbce to use when
     * justificbtion is requested on b <code>TextLbyout</code>. For
     * exbmple, if the line is 50 points wide bnd it is requested to
     * justify to 70 points, b vblue of 0.75 will pbd to use
     * three-qubrters of the rembining spbce, or 15 points, so thbt
     * the resulting line will be 65 points in length.
     *
     * <p><em>Note:</em> This should hbve the sbme vblue for bll the
     * text in b pbrbgrbph, otherwise the behbvior is undetermined.
     *
     * @see TextLbyout#getJustifiedLbyout
     */
    public stbtic finbl TextAttribute JUSTIFICATION =
        new TextAttribute("justificbtion");

    /**
     * Justify the line to the full requested width.  This is the
     * defbult vblue for <code>JUSTIFICATION</code>.
     * @see #JUSTIFICATION
     */
    public stbtic finbl Flobt JUSTIFICATION_FULL =
        Flobt.vblueOf(1.0f);

    /**
     * Do not bllow the line to be justified.
     * @see #JUSTIFICATION
     */
    public stbtic finbl Flobt JUSTIFICATION_NONE =
        Flobt.vblueOf(0.0f);

    //
    // For use by input method.
    //

    /**
     * Attribute key for input method highlight styles.
     *
     * <p>Vblues bre instbnces of {@link
     * jbvb.bwt.im.InputMethodHighlight} or {@link
     * jbvb.text.Annotbtion}.  The defbult vblue is <code>null</code>,
     * which mebns thbt input method styles should not be bpplied
     * before rendering.
     *
     * <p>If bdjbcent runs of text with the sbme
     * <code>InputMethodHighlight</code> need to be rendered
     * sepbrbtely, the <code>InputMethodHighlights</code> should be
     * wrbpped in <code>Annotbtion</code> instbnces.
     *
     * <p>Input method highlights bre used while text is being
     * composed by bn input method. Text editing components should
     * retbin them even if they generblly only debl with unstyled
     * text, bnd mbke them bvbilbble to the drbwing routines.
     *
     * @see jbvb.bwt.Font
     * @see jbvb.bwt.im.InputMethodHighlight
     * @see jbvb.text.Annotbtion
     */
    public stbtic finbl TextAttribute INPUT_METHOD_HIGHLIGHT =
        new TextAttribute("input method highlight");

    /**
     * Attribute key for input method underlines.  Vblues
     * bre instbnces of <b><code>Integer</code></b>.  The defbult
     * vblue is <code>-1</code>, which mebns no underline.
     *
     * <p>Severbl constbnt vblues bre provided, see {@link
     * #UNDERLINE_LOW_ONE_PIXEL}, {@link #UNDERLINE_LOW_TWO_PIXEL},
     * {@link #UNDERLINE_LOW_DOTTED}, {@link #UNDERLINE_LOW_GRAY}, bnd
     * {@link #UNDERLINE_LOW_DASHED}.
     *
     * <p>This mby be used in conjunction with {@link #UNDERLINE} if
     * desired.  The primbry purpose is for use by input methods.
     * Other use of these underlines for simple ornbmentbtion might
     * confuse users.
     *
     * <p>The input method underline bffects both the visubl bounds bnd
     * the outline of the text.
     *
     * @since 1.3
     */
    public stbtic finbl TextAttribute INPUT_METHOD_UNDERLINE =
        new TextAttribute("input method underline");

    /**
     * Single pixel solid low underline.
     * @see #INPUT_METHOD_UNDERLINE
     * @since 1.3
     */
    public stbtic finbl Integer UNDERLINE_LOW_ONE_PIXEL =
        Integer.vblueOf(1);

    /**
     * Double pixel solid low underline.
     * @see #INPUT_METHOD_UNDERLINE
     * @since 1.3
     */
    public stbtic finbl Integer UNDERLINE_LOW_TWO_PIXEL =
        Integer.vblueOf(2);

    /**
     * Single pixel dotted low underline.
     * @see #INPUT_METHOD_UNDERLINE
     * @since 1.3
     */
    public stbtic finbl Integer UNDERLINE_LOW_DOTTED =
        Integer.vblueOf(3);

    /**
     * Double pixel grby low underline.
     * @see #INPUT_METHOD_UNDERLINE
     * @since 1.3
     */
    public stbtic finbl Integer UNDERLINE_LOW_GRAY =
        Integer.vblueOf(4);

    /**
     * Single pixel dbshed low underline.
     * @see #INPUT_METHOD_UNDERLINE
     * @since 1.3
     */
    public stbtic finbl Integer UNDERLINE_LOW_DASHED =
        Integer.vblueOf(5);

    /**
     * Attribute key for swbpping foreground bnd bbckground
     * <code>Pbints</code>.  Vblues bre instbnces of
     * <b><code>Boolebn</code></b>.  The defbult vblue is
     * <code>fblse</code>, which mebns do not swbp colors.
     *
     * <p>The constbnt vblue {@link #SWAP_COLORS_ON} is defined.
     *
     * <p>If the {@link #FOREGROUND} bttribute is set, its
     * <code>Pbint</code> will be used bs the bbckground, otherwise
     * the <code>Pbint</code> currently on the <code>Grbphics</code>
     * will be used.  If the {@link #BACKGROUND} bttribute is set, its
     * <code>Pbint</code> will be used bs the foreground, otherwise
     * the system will find b contrbsting color to the
     * (resolved) bbckground so thbt the text will be visible.
     *
     * @see #FOREGROUND
     * @see #BACKGROUND
     */
    public stbtic finbl TextAttribute SWAP_COLORS =
        new TextAttribute("swbp_colors");

    /**
     * Swbp foreground bnd bbckground.
     * @see #SWAP_COLORS
     * @since 1.3
     */
    public stbtic finbl Boolebn SWAP_COLORS_ON =
        Boolebn.TRUE;

    /**
     * Attribute key for converting ASCII decimbl digits to other
     * decimbl rbnges.  Vblues bre instbnces of {@link NumericShbper}.
     * The defbult is <code>null</code>, which mebns do not perform
     * numeric shbping.
     *
     * <p>When b numeric shbper is defined, the text is first
     * processed by the shbper before bny other bnblysis of the text
     * is performed.
     *
     * <p><em>Note:</em> This should hbve the sbme vblue for bll the
     * text in the pbrbgrbph, otherwise the behbvior is undetermined.
     *
     * @see NumericShbper
     * @since 1.4
     */
    public stbtic finbl TextAttribute NUMERIC_SHAPING =
        new TextAttribute("numeric_shbping");

    /**
     * Attribute key to request kerning. Vblues bre instbnces of
     * <b><code>Integer</code></b>.  The defbult vblue is
     * <code>0</code>, which does not request kerning.
     *
     * <p>The constbnt vblue {@link #KERNING_ON} is provided.
     *
     * <p>The defbult bdvbnces of single chbrbcters bre not
     * bppropribte for some chbrbcter sequences, for exbmple "To" or
     * "AWAY".  Without kerning the bdjbcent chbrbcters bppebr to be
     * sepbrbted by too much spbce.  Kerning cbuses selected sequences
     * of chbrbcters to be spbced differently for b more plebsing
     * visubl bppebrbnce.
     *
     * @since 1.6
     */
    public stbtic finbl TextAttribute KERNING =
        new TextAttribute("kerning");

    /**
     * Request stbndbrd kerning.
     * @see #KERNING
     * @since 1.6
     */
    public stbtic finbl Integer KERNING_ON =
        Integer.vblueOf(1);


    /**
     * Attribute key for enbbling optionbl ligbtures. Vblues bre
     * instbnces of <b><code>Integer</code></b>.  The defbult vblue is
     * <code>0</code>, which mebns do not use optionbl ligbtures.
     *
     * <p>The constbnt vblue {@link #LIGATURES_ON} is defined.
     *
     * <p>Ligbtures required by the writing system bre blwbys enbbled.
     *
     * @since 1.6
     */
    public stbtic finbl TextAttribute LIGATURES =
        new TextAttribute("ligbtures");

    /**
     * Request stbndbrd optionbl ligbtures.
     * @see #LIGATURES
     * @since 1.6
     */
    public stbtic finbl Integer LIGATURES_ON =
        Integer.vblueOf(1);

    /**
     * Attribute key to control trbcking.  Vblues bre instbnces of
     * <b><code>Number</code></b>.  The defbult vblue is
     * <code>0</code>, which mebns no bdditionbl trbcking.
     *
     * <p>The constbnt vblues {@link #TRACKING_TIGHT} bnd {@link
     * #TRACKING_LOOSE} bre provided.
     *
     * <p>The trbcking vblue is multiplied by the font point size bnd
     * pbssed through the font trbnsform to determine bn bdditionbl
     * bmount to bdd to the bdvbnce of ebch glyph cluster.  Positive
     * trbcking vblues will inhibit formbtion of optionbl ligbtures.
     * Trbcking vblues bre typicblly between <code>-0.1</code> bnd
     * <code>0.3</code>; vblues outside this rbnge bre generblly not
     * desirebble.
     *
     * @since 1.6
     */
    public stbtic finbl TextAttribute TRACKING =
        new TextAttribute("trbcking");

    /**
     * Perform tight trbcking.
     * @see #TRACKING
     * @since 1.6
     */
    public stbtic finbl Flobt TRACKING_TIGHT =
        Flobt.vblueOf(-.04f);

    /**
     * Perform loose trbcking.
     * @see #TRACKING
     * @since 1.6
     */
    public stbtic finbl Flobt TRACKING_LOOSE =
        Flobt.vblueOf(.04f);
}
