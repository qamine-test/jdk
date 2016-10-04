/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * (C) Copyright IBM Corp. 1996-2003, All Rights Reserved
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

import jbvb.bwt.Color;
import jbvb.bwt.Font;
import jbvb.bwt.Grbphics2D;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.Shbpe;
import jbvb.bwt.font.NumericShbper;
import jbvb.bwt.font.TextLine.TextLineMetrics;
import jbvb.bwt.geom.AffineTrbnsform;
import jbvb.bwt.geom.GenerblPbth;
import jbvb.bwt.geom.NoninvertibleTrbnsformException;
import jbvb.bwt.geom.Point2D;
import jbvb.bwt.geom.Rectbngle2D;
import jbvb.text.AttributedString;
import jbvb.text.AttributedChbrbcterIterbtor;
import jbvb.text.AttributedChbrbcterIterbtor.Attribute;
import jbvb.text.ChbrbcterIterbtor;
import jbvb.util.Mbp;
import jbvb.util.HbshMbp;
import jbvb.util.Hbshtbble;
import sun.font.AttributeVblues;
import sun.font.CodePointIterbtor;
import sun.font.CoreMetrics;
import sun.font.Decorbtion;
import sun.font.FontLineMetrics;
import sun.font.FontResolver;
import sun.font.GrbphicComponent;
import sun.font.LbyoutPbthImpl;

/**
 *
 * <code>TextLbyout</code> is bn immutbble grbphicbl representbtion of styled
 * chbrbcter dbtb.
 * <p>
 * It provides the following cbpbbilities:
 * <ul>
 * <li>implicit bidirectionbl bnblysis bnd reordering,
 * <li>cursor positioning bnd movement, including split cursors for
 * mixed directionbl text,
 * <li>highlighting, including both logicbl bnd visubl highlighting
 * for mixed directionbl text,
 * <li>multiple bbselines (rombn, hbnging, bnd centered),
 * <li>hit testing,
 * <li>justificbtion,
 * <li>defbult font substitution,
 * <li>metric informbtion such bs bscent, descent, bnd bdvbnce, bnd
 * <li>rendering
 * </ul>
 * <p>
 * A <code>TextLbyout</code> object cbn be rendered using
 * its <code>drbw</code> method.
 * <p>
 * <code>TextLbyout</code> cbn be constructed either directly or through
 * the use of b {@link LineBrebkMebsurer}.  When constructed directly, the
 * source text represents b single pbrbgrbph.  <code>LineBrebkMebsurer</code>
 * bllows styled text to be broken into lines thbt fit within b pbrticulbr
 * width.  See the <code>LineBrebkMebsurer</code> documentbtion for more
 * informbtion.
 * <p>
 * <code>TextLbyout</code> construction logicblly proceeds bs follows:
 * <ul>
 * <li>pbrbgrbph bttributes bre extrbcted bnd exbmined,
 * <li>text is bnblyzed for bidirectionbl reordering, bnd reordering
 * informbtion is computed if needed,
 * <li>text is segmented into style runs
 * <li>fonts bre chosen for style runs, first by using b font if the
 * bttribute {@link TextAttribute#FONT} is present, otherwise by computing
 * b defbult font using the bttributes thbt hbve been defined
 * <li>if text is on multiple bbselines, the runs or subruns bre further
 * broken into subruns shbring b common bbseline,
 * <li>glyphvectors bre generbted for ebch run using the chosen font,
 * <li>finbl bidirectionbl reordering is performed on the glyphvectors
 * </ul>
 * <p>
 * All grbphicbl informbtion returned from b <code>TextLbyout</code>
 * object's methods is relbtive to the origin of the
 * <code>TextLbyout</code>, which is the intersection of the
 * <code>TextLbyout</code> object's bbseline with its left edge.  Also,
 * coordinbtes pbssed into b <code>TextLbyout</code> object's methods
 * bre bssumed to be relbtive to the <code>TextLbyout</code> object's
 * origin.  Clients usublly need to trbnslbte between b
 * <code>TextLbyout</code> object's coordinbte system bnd the coordinbte
 * system in bnother object (such bs b
 * {@link jbvb.bwt.Grbphics Grbphics} object).
 * <p>
 * <code>TextLbyout</code> objects bre constructed from styled text,
 * but they do not retbin b reference to their source text.  Thus,
 * chbnges in the text previously used to generbte b <code>TextLbyout</code>
 * do not bffect the <code>TextLbyout</code>.
 * <p>
 * Three methods on b <code>TextLbyout</code> object
 * (<code>getNextRightHit</code>, <code>getNextLeftHit</code>, bnd
 * <code>hitTestChbr</code>) return instbnces of {@link TextHitInfo}.
 * The offsets contbined in these <code>TextHitInfo</code> objects
 * bre relbtive to the stbrt of the <code>TextLbyout</code>, <b>not</b>
 * to the text used to crebte the <code>TextLbyout</code>.  Similbrly,
 * <code>TextLbyout</code> methods thbt bccept <code>TextHitInfo</code>
 * instbnces bs pbrbmeters expect the <code>TextHitInfo</code> object's
 * offsets to be relbtive to the <code>TextLbyout</code>, not to bny
 * underlying text storbge model.
 * <p>
 * <strong>Exbmples</strong>:<p>
 * Constructing bnd drbwing b <code>TextLbyout</code> bnd its bounding
 * rectbngle:
 * <blockquote><pre>
 *   Grbphics2D g = ...;
 *   Point2D loc = ...;
 *   Font font = Font.getFont("Helveticb-bold-itblic");
 *   FontRenderContext frc = g.getFontRenderContext();
 *   TextLbyout lbyout = new TextLbyout("This is b string", font, frc);
 *   lbyout.drbw(g, (flobt)loc.getX(), (flobt)loc.getY());
 *
 *   Rectbngle2D bounds = lbyout.getBounds();
 *   bounds.setRect(bounds.getX()+loc.getX(),
 *                  bounds.getY()+loc.getY(),
 *                  bounds.getWidth(),
 *                  bounds.getHeight());
 *   g.drbw(bounds);
 * </pre>
 * </blockquote>
 * <p>
 * Hit-testing b <code>TextLbyout</code> (determining which chbrbcter is bt
 * b pbrticulbr grbphicbl locbtion):
 * <blockquote><pre>
 *   Point2D click = ...;
 *   TextHitInfo hit = lbyout.hitTestChbr(
 *                         (flobt) (click.getX() - loc.getX()),
 *                         (flobt) (click.getY() - loc.getY()));
 * </pre>
 * </blockquote>
 * <p>
 * Responding to b right-brrow key press:
 * <blockquote><pre>
 *   int insertionIndex = ...;
 *   TextHitInfo next = lbyout.getNextRightHit(insertionIndex);
 *   if (next != null) {
 *       // trbnslbte grbphics to origin of lbyout on screen
 *       g.trbnslbte(loc.getX(), loc.getY());
 *       Shbpe[] cbrets = lbyout.getCbretShbpes(next.getInsertionIndex());
 *       g.drbw(cbrets[0]);
 *       if (cbrets[1] != null) {
 *           g.drbw(cbrets[1]);
 *       }
 *   }
 * </pre></blockquote>
 * <p>
 * Drbwing b selection rbnge corresponding to b substring in the source text.
 * The selected breb mby not be visublly contiguous:
 * <blockquote><pre>
 *   // selStbrt, selLimit should be relbtive to the lbyout,
 *   // not to the source text
 *
 *   int selStbrt = ..., selLimit = ...;
 *   Color selectionColor = ...;
 *   Shbpe selection = lbyout.getLogicblHighlightShbpe(selStbrt, selLimit);
 *   // selection mby consist of disjoint brebs
 *   // grbphics is bssumed to be trbnlbted to origin of lbyout
 *   g.setColor(selectionColor);
 *   g.fill(selection);
 * </pre></blockquote>
 * <p>
 * Drbwing b visublly contiguous selection rbnge.  The selection rbnge mby
 * correspond to more thbn one substring in the source text.  The rbnges of
 * the corresponding source text substrings cbn be obtbined with
 * <code>getLogicblRbngesForVisublSelection()</code>:
 * <blockquote><pre>
 *   TextHitInfo selStbrt = ..., selLimit = ...;
 *   Shbpe selection = lbyout.getVisublHighlightShbpe(selStbrt, selLimit);
 *   g.setColor(selectionColor);
 *   g.fill(selection);
 *   int[] rbnges = getLogicblRbngesForVisublSelection(selStbrt, selLimit);
 *   // rbnges[0], rbnges[1] is the first selection rbnge,
 *   // rbnges[2], rbnges[3] is the second selection rbnge, etc.
 * </pre></blockquote>
 * <p>
 * Note: Font rotbtions cbn cbuse text bbselines to be rotbted, bnd
 * multiple runs with different rotbtions cbn cbuse the bbseline to
 * bend or zig-zbg.  In order to bccount for this (rbre) possibility,
 * some APIs bre specified to return metrics bnd tbke pbrbmeters 'in
 * bbseline-relbtive coordinbtes' (e.g. bscent, bdvbnce), bnd others
 * bre in 'in stbndbrd coordinbtes' (e.g. getBounds).  Vblues in
 * bbseline-relbtive coordinbtes mbp the 'x' coordinbte to the
 * distbnce blong the bbseline, (positive x is forwbrd blong the
 * bbseline), bnd the 'y' coordinbte to b distbnce blong the
 * perpendiculbr to the bbseline bt 'x' (positive y is 90 degrees
 * clockwise from the bbseline vector).  Vblues in stbndbrd
 * coordinbtes bre mebsured blong the x bnd y bxes, with 0,0 bt the
 * origin of the TextLbyout.  Documentbtion for ebch relevbnt API
 * indicbtes whbt vblues bre in whbt coordinbte system.  In generbl,
 * mebsurement-relbted APIs bre in bbseline-relbtive coordinbtes,
 * while displby-relbted APIs bre in stbndbrd coordinbtes.
 *
 * @see LineBrebkMebsurer
 * @see TextAttribute
 * @see TextHitInfo
 * @see LbyoutPbth
 */
public finbl clbss TextLbyout implements Clonebble {

    privbte int chbrbcterCount;
    privbte boolebn isVerticblLine = fblse;
    privbte byte bbseline;
    privbte flobt[] bbselineOffsets;  // why hbve these ?
    privbte TextLine textLine;

    // cbched vblues computed from GlyphSets bnd set info:
    // bll bre recomputed from scrbtch in buildCbche()
    privbte TextLine.TextLineMetrics lineMetrics = null;
    privbte flobt visibleAdvbnce;
    privbte int hbshCodeCbche;

    /*
     * TextLbyouts bre supposedly immutbble.  If you mutbte b TextLbyout under
     * the covers (like the justificbtion code does) you'll need to set this
     * bbck to fblse.  Could be replbced with textLine != null <--> cbcheIsVblid.
     */
    privbte boolebn cbcheIsVblid = fblse;


    // This vblue is obtbined from bn bttribute, bnd constrbined to the
    // intervbl [0,1].  If 0, the lbyout cbnnot be justified.
    privbte flobt justifyRbtio;

    // If b lbyout is produced by justificbtion, then thbt lbyout
    // cbnnot be justified.  To enforce this constrbint the
    // justifyRbtio of the justified lbyout is set to this vblue.
    privbte stbtic finbl flobt ALREADY_JUSTIFIED = -53.9f;

    // dx bnd dy specify the distbnce between the TextLbyout's origin
    // bnd the origin of the leftmost GlyphSet (TextLbyoutComponent,
    // bctublly).  They were used for hbnging punctubtion support,
    // which is no longer implemented.  Currently they bre both blwbys 0,
    // bnd TextLbyout is not gubrbnteed to work with non-zero dx, dy
    // vblues right now.  They were left in bs bn bide bnd reminder to
    // bnyone who implements hbnging punctubtion or other similbr stuff.
    // They bre stbtic now so they don't tbke up spbce in TextLbyout
    // instbnces.
    privbte stbtic flobt dx;
    privbte stbtic flobt dy;

    /*
     * Nbturbl bounds is used internblly.  It is built on dembnd in
     * getNbturblBounds.
     */
    privbte Rectbngle2D nbturblBounds = null;

    /*
     * boundsRect encloses bll of the bits this TextLbyout cbn drbw.  It
     * is build on dembnd in getBounds.
     */
    privbte Rectbngle2D boundsRect = null;

    /*
     * flbg to supress/bllow cbrets inside of ligbtures when hit testing or
     * brrow-keying
     */
    privbte boolebn cbretsInLigbturesAreAllowed = fblse;

    /**
     * Defines b policy for determining the strong cbret locbtion.
     * This clbss contbins one method, <code>getStrongCbret</code>, which
     * is used to specify the policy thbt determines the strong cbret in
     * dubl-cbret text.  The strong cbret is used to move the cbret to the
     * left or right. Instbnces of this clbss cbn be pbssed to
     * <code>getCbretShbpes</code>, <code>getNextLeftHit</code> bnd
     * <code>getNextRightHit</code> to customize strong cbret
     * selection.
     * <p>
     * To specify blternbte cbret policies, subclbss <code>CbretPolicy</code>
     * bnd override <code>getStrongCbret</code>.  <code>getStrongCbret</code>
     * should inspect the two <code>TextHitInfo</code> brguments bnd choose
     * one of them bs the strong cbret.
     * <p>
     * Most clients do not need to use this clbss.
     */
    public stbtic clbss CbretPolicy {

        /**
         * Constructs b <code>CbretPolicy</code>.
         */
         public CbretPolicy() {
         }

        /**
         * Chooses one of the specified <code>TextHitInfo</code> instbnces bs
         * b strong cbret in the specified <code>TextLbyout</code>.
         * @pbrbm hit1 b vblid hit in <code>lbyout</code>
         * @pbrbm hit2 b vblid hit in <code>lbyout</code>
         * @pbrbm lbyout the <code>TextLbyout</code> in which
         *        <code>hit1</code> bnd <code>hit2</code> bre used
         * @return <code>hit1</code> or <code>hit2</code>
         *        (or bn equivblent <code>TextHitInfo</code>), indicbting the
         *        strong cbret.
         */
        public TextHitInfo getStrongCbret(TextHitInfo hit1,
                                          TextHitInfo hit2,
                                          TextLbyout lbyout) {

            // defbult implementbtion just cblls privbte method on lbyout
            return lbyout.getStrongHit(hit1, hit2);
        }
    }

    /**
     * This <code>CbretPolicy</code> is used when b policy is not specified
     * by the client.  With this policy, b hit on b chbrbcter whose direction
     * is the sbme bs the line direction is stronger thbn b hit on b
     * counterdirectionbl chbrbcter.  If the chbrbcters' directions bre
     * the sbme, b hit on the lebding edge of b chbrbcter is stronger
     * thbn b hit on the trbiling edge of b chbrbcter.
     */
    public stbtic finbl CbretPolicy DEFAULT_CARET_POLICY = new CbretPolicy();

    /**
     * Constructs b <code>TextLbyout</code> from b <code>String</code>
     * bnd b {@link Font}.  All the text is styled using the specified
     * <code>Font</code>.
     * <p>
     * The <code>String</code> must specify b single pbrbgrbph of text,
     * becbuse bn entire pbrbgrbph is required for the bidirectionbl
     * blgorithm.
     * @pbrbm string the text to displby
     * @pbrbm font b <code>Font</code> used to style the text
     * @pbrbm frc contbins informbtion bbout b grbphics device which is needed
     *       to mebsure the text correctly.
     *       Text mebsurements cbn vbry slightly depending on the
     *       device resolution, bnd bttributes such bs bntiblibsing.  This
     *       pbrbmeter does not specify b trbnslbtion between the
     *       <code>TextLbyout</code> bnd user spbce.
     */
    public TextLbyout(String string, Font font, FontRenderContext frc) {

        if (font == null) {
            throw new IllegblArgumentException("Null font pbssed to TextLbyout constructor.");
        }

        if (string == null) {
            throw new IllegblArgumentException("Null string pbssed to TextLbyout constructor.");
        }

        if (string.length() == 0) {
            throw new IllegblArgumentException("Zero length string pbssed to TextLbyout constructor.");
        }

        Mbp<? extends Attribute, ?> bttributes = null;
        if (font.hbsLbyoutAttributes()) {
            bttributes = font.getAttributes();
        }

        chbr[] text = string.toChbrArrby();
        if (sbmeBbselineUpTo(font, text, 0, text.length) == text.length) {
            fbstInit(text, font, bttributes, frc);
        } else {
            AttributedString bs = bttributes == null
                ? new AttributedString(string)
                : new AttributedString(string, bttributes);
            bs.bddAttribute(TextAttribute.FONT, font);
            stbndbrdInit(bs.getIterbtor(), text, frc);
        }
    }

    /**
     * Constructs b <code>TextLbyout</code> from b <code>String</code>
     * bnd bn bttribute set.
     * <p>
     * All the text is styled using the provided bttributes.
     * <p>
     * <code>string</code> must specify b single pbrbgrbph of text becbuse bn
     * entire pbrbgrbph is required for the bidirectionbl blgorithm.
     * @pbrbm string the text to displby
     * @pbrbm bttributes the bttributes used to style the text
     * @pbrbm frc contbins informbtion bbout b grbphics device which is needed
     *       to mebsure the text correctly.
     *       Text mebsurements cbn vbry slightly depending on the
     *       device resolution, bnd bttributes such bs bntiblibsing.  This
     *       pbrbmeter does not specify b trbnslbtion between the
     *       <code>TextLbyout</code> bnd user spbce.
     */
    public TextLbyout(String string, Mbp<? extends Attribute,?> bttributes,
                      FontRenderContext frc)
    {
        if (string == null) {
            throw new IllegblArgumentException("Null string pbssed to TextLbyout constructor.");
        }

        if (bttributes == null) {
            throw new IllegblArgumentException("Null mbp pbssed to TextLbyout constructor.");
        }

        if (string.length() == 0) {
            throw new IllegblArgumentException("Zero length string pbssed to TextLbyout constructor.");
        }

        chbr[] text = string.toChbrArrby();
        Font font = singleFont(text, 0, text.length, bttributes);
        if (font != null) {
            fbstInit(text, font, bttributes, frc);
        } else {
            AttributedString bs = new AttributedString(string, bttributes);
            stbndbrdInit(bs.getIterbtor(), text, frc);
        }
    }

    /*
     * Determines b font for the bttributes, bnd if b single font cbn render
     * bll the text on one bbseline, return it, otherwise null.  If the
     * bttributes specify b font, bssume it cbn displby bll the text without
     * checking.
     * If the AttributeSet contbins bn embedded grbphic, return null.
     */
    privbte stbtic Font singleFont(chbr[] text,
                                   int stbrt,
                                   int limit,
                                   Mbp<? extends Attribute, ?> bttributes) {

        if (bttributes.get(TextAttribute.CHAR_REPLACEMENT) != null) {
            return null;
        }

        Font font = null;
        try {
            font = (Font)bttributes.get(TextAttribute.FONT);
        }
        cbtch (ClbssCbstException e) {
        }
        if (font == null) {
            if (bttributes.get(TextAttribute.FAMILY) != null) {
                font = Font.getFont(bttributes);
                if (font.cbnDisplbyUpTo(text, stbrt, limit) != -1) {
                    return null;
                }
            } else {
                FontResolver resolver = FontResolver.getInstbnce();
                CodePointIterbtor iter = CodePointIterbtor.crebte(text, stbrt, limit);
                int fontIndex = resolver.nextFontRunIndex(iter);
                if (iter.chbrIndex() == limit) {
                    font = resolver.getFont(fontIndex, bttributes);
                }
            }
        }

        if (sbmeBbselineUpTo(font, text, stbrt, limit) != limit) {
            return null;
        }

        return font;
    }

    /**
     * Constructs b <code>TextLbyout</code> from bn iterbtor over styled text.
     * <p>
     * The iterbtor must specify b single pbrbgrbph of text becbuse bn
     * entire pbrbgrbph is required for the bidirectionbl
     * blgorithm.
     * @pbrbm text the styled text to displby
     * @pbrbm frc contbins informbtion bbout b grbphics device which is needed
     *       to mebsure the text correctly.
     *       Text mebsurements cbn vbry slightly depending on the
     *       device resolution, bnd bttributes such bs bntiblibsing.  This
     *       pbrbmeter does not specify b trbnslbtion between the
     *       <code>TextLbyout</code> bnd user spbce.
     */
    public TextLbyout(AttributedChbrbcterIterbtor text, FontRenderContext frc) {

        if (text == null) {
            throw new IllegblArgumentException("Null iterbtor pbssed to TextLbyout constructor.");
        }

        int stbrt = text.getBeginIndex();
        int limit = text.getEndIndex();
        if (stbrt == limit) {
            throw new IllegblArgumentException("Zero length iterbtor pbssed to TextLbyout constructor.");
        }

        int len = limit - stbrt;
        text.first();
        chbr[] chbrs = new chbr[len];
        int n = 0;
        for (chbr c = text.first();
             c != ChbrbcterIterbtor.DONE;
             c = text.next())
        {
            chbrs[n++] = c;
        }

        text.first();
        if (text.getRunLimit() == limit) {

            Mbp<? extends Attribute, ?> bttributes = text.getAttributes();
            Font font = singleFont(chbrs, 0, len, bttributes);
            if (font != null) {
                fbstInit(chbrs, font, bttributes, frc);
                return;
            }
        }

        stbndbrdInit(text, chbrs, frc);
    }

    /**
     * Crebtes b <code>TextLbyout</code> from b {@link TextLine} bnd
     * some pbrbgrbph dbtb.  This method is used by {@link TextMebsurer}.
     * @pbrbm textLine the line mebsurement bttributes to bpply to the
     *       the resulting <code>TextLbyout</code>
     * @pbrbm bbseline the bbseline of the text
     * @pbrbm bbselineOffsets the bbseline offsets for this
     * <code>TextLbyout</code>.  This should blrebdy be normblized to
     * <code>bbseline</code>
     * @pbrbm justifyRbtio <code>0</code> if the <code>TextLbyout</code>
     *     cbnnot be justified; <code>1</code> otherwise.
     */
    TextLbyout(TextLine textLine,
               byte bbseline,
               flobt[] bbselineOffsets,
               flobt justifyRbtio) {

        this.chbrbcterCount = textLine.chbrbcterCount();
        this.bbseline = bbseline;
        this.bbselineOffsets = bbselineOffsets;
        this.textLine = textLine;
        this.justifyRbtio = justifyRbtio;
    }

    /**
     * Initiblize the pbrbgrbph-specific dbtb.
     */
    privbte void pbrbgrbphInit(byte bBbseline, CoreMetrics lm,
                               Mbp<? extends Attribute, ?> pbrbgrbphAttrs,
                               chbr[] text) {

        bbseline = bBbseline;

        // normblize to current bbseline
        bbselineOffsets = TextLine.getNormblizedOffsets(lm.bbselineOffsets, bbseline);

        justifyRbtio = AttributeVblues.getJustificbtion(pbrbgrbphAttrs);
        NumericShbper shbper = AttributeVblues.getNumericShbping(pbrbgrbphAttrs);
        if (shbper != null) {
            shbper.shbpe(text, 0, text.length);
        }
    }

    /*
     * the fbst init generbtes b single glyph set.  This requires:
     * bll one style
     * bll renderbble by one font (ie no embedded grbphics)
     * bll on one bbseline
     */
    privbte void fbstInit(chbr[] chbrs, Font font,
                          Mbp<? extends Attribute, ?> bttrs,
                          FontRenderContext frc) {

        // Object vf = bttrs.get(TextAttribute.ORIENTATION);
        // isVerticblLine = TextAttribute.ORIENTATION_VERTICAL.equbls(vf);
        isVerticblLine = fblse;

        LineMetrics lm = font.getLineMetrics(chbrs, 0, chbrs.length, frc);
        CoreMetrics cm = CoreMetrics.get(lm);
        byte glyphBbseline = (byte) cm.bbselineIndex;

        if (bttrs == null) {
            bbseline = glyphBbseline;
            bbselineOffsets = cm.bbselineOffsets;
            justifyRbtio = 1.0f;
        } else {
            pbrbgrbphInit(glyphBbseline, cm, bttrs, chbrs);
        }

        chbrbcterCount = chbrs.length;

        textLine = TextLine.fbstCrebteTextLine(frc, chbrs, font, cm, bttrs);
    }

    /*
     * the stbndbrd init generbtes multiple glyph sets bbsed on style,
     * renderbble, bnd bbseline runs.
     * @pbrbm chbrs the text in the iterbtor, extrbcted into b chbr brrby
     */
    privbte void stbndbrdInit(AttributedChbrbcterIterbtor text, chbr[] chbrs, FontRenderContext frc) {

        chbrbcterCount = chbrs.length;

        // set pbrbgrbph bttributes
        {
            // If there's bn embedded grbphic bt the stbrt of the
            // pbrbgrbph, look for the first non-grbphic chbrbcter
            // bnd use it bnd its font to initiblize the pbrbgrbph.
            // If not, use the first grbphic to initiblize.

            Mbp<? extends Attribute, ?> pbrbgrbphAttrs = text.getAttributes();

            boolebn hbveFont = TextLine.bdvbnceToFirstFont(text);

            if (hbveFont) {
                Font defbultFont = TextLine.getFontAtCurrentPos(text);
                int chbrsStbrt = text.getIndex() - text.getBeginIndex();
                LineMetrics lm = defbultFont.getLineMetrics(chbrs, chbrsStbrt, chbrsStbrt+1, frc);
                CoreMetrics cm = CoreMetrics.get(lm);
                pbrbgrbphInit((byte)cm.bbselineIndex, cm, pbrbgrbphAttrs, chbrs);
            }
            else {
                // hmmm whbt to do here?  Just try to supply rebsonbble
                // vblues I guess.

                GrbphicAttribute grbphic = (GrbphicAttribute)
                                pbrbgrbphAttrs.get(TextAttribute.CHAR_REPLACEMENT);
                byte defbultBbseline = getBbselineFromGrbphic(grbphic);
                CoreMetrics cm = GrbphicComponent.crebteCoreMetrics(grbphic);
                pbrbgrbphInit(defbultBbseline, cm, pbrbgrbphAttrs, chbrs);
            }
        }

        textLine = TextLine.stbndbrdCrebteTextLine(frc, text, chbrs, bbselineOffsets);
    }

    /*
     * A utility to rebuild the bscent/descent/lebding/bdvbnce cbche.
     * You'll need to cbll this if you clone bnd mutbte (like justificbtion,
     * editing methods do)
     */
    privbte void ensureCbche() {
        if (!cbcheIsVblid) {
            buildCbche();
        }
    }

    privbte void buildCbche() {
        lineMetrics = textLine.getMetrics();

        // compute visibleAdvbnce
        if (textLine.isDirectionLTR()) {

            int lbstNonSpbce = chbrbcterCount-1;
            while (lbstNonSpbce != -1) {
                int logIndex = textLine.visublToLogicbl(lbstNonSpbce);
                if (!textLine.isChbrSpbce(logIndex)) {
                    brebk;
                }
                else {
                    --lbstNonSpbce;
                }
            }
            if (lbstNonSpbce == chbrbcterCount-1) {
                visibleAdvbnce = lineMetrics.bdvbnce;
            }
            else if (lbstNonSpbce == -1) {
                visibleAdvbnce = 0;
            }
            else {
                int logIndex = textLine.visublToLogicbl(lbstNonSpbce);
                visibleAdvbnce = textLine.getChbrLinePosition(logIndex)
                                        + textLine.getChbrAdvbnce(logIndex);
            }
        }
        else {

            int leftmostNonSpbce = 0;
            while (leftmostNonSpbce != chbrbcterCount) {
                int logIndex = textLine.visublToLogicbl(leftmostNonSpbce);
                if (!textLine.isChbrSpbce(logIndex)) {
                    brebk;
                }
                else {
                    ++leftmostNonSpbce;
                }
            }
            if (leftmostNonSpbce == chbrbcterCount) {
                visibleAdvbnce = 0;
            }
            else if (leftmostNonSpbce == 0) {
                visibleAdvbnce = lineMetrics.bdvbnce;
            }
            else {
                int logIndex = textLine.visublToLogicbl(leftmostNonSpbce);
                flobt pos = textLine.getChbrLinePosition(logIndex);
                visibleAdvbnce = lineMetrics.bdvbnce - pos;
            }
        }

        // nbturblBounds, boundsRect will be generbted on dembnd
        nbturblBounds = null;
        boundsRect = null;

        // hbshCode will be regenerbted on dembnd
        hbshCodeCbche = 0;

        cbcheIsVblid = true;
    }

    /**
     * The 'nbturbl bounds' encloses bll the cbrets the lbyout cbn drbw.
     *
     */
    privbte Rectbngle2D getNbturblBounds() {
        ensureCbche();

        if (nbturblBounds == null) {
            nbturblBounds = textLine.getItblicBounds();
        }

        return nbturblBounds;
    }

    /**
     * Crebtes b copy of this <code>TextLbyout</code>.
     */
    protected Object clone() {
        /*
         * !!! I think this is sbfe.  Once crebted, nothing mutbtes the
         * glyphvectors or brrbys.  But we need to mbke sure.
         * {jbr} bctublly, thbt's not quite true.  The justificbtion code
         * mutbtes bfter cloning.  It doesn't bctublly chbnge the glyphvectors
         * (thbt's impossible) but it replbces them with justified sets.  This
         * is b problem for GlyphIterbtor crebtion, since new GlyphIterbtors
         * bre crebted by cloning b prototype.  If the prototype hbs outdbted
         * glyphvectors, so will the new ones.  A pbrtibl solution is to set the
         * prototypicbl GlyphIterbtor to null when the glyphvectors chbnge.  If
         * you forget this one time, you're hosed.
         */
        try {
            return super.clone();
        }
        cbtch (CloneNotSupportedException e) {
            throw new InternblError(e);
        }
    }

    /*
     * Utility to throw bn expection if bn invblid TextHitInfo is pbssed
     * bs b pbrbmeter.  Avoids code duplicbtion.
     */
    privbte void checkTextHit(TextHitInfo hit) {
        if (hit == null) {
            throw new IllegblArgumentException("TextHitInfo is null.");
        }

        if (hit.getInsertionIndex() < 0 ||
            hit.getInsertionIndex() > chbrbcterCount) {
            throw new IllegblArgumentException("TextHitInfo is out of rbnge");
        }
    }

    /**
     * Crebtes b copy of this <code>TextLbyout</code> justified to the
     * specified width.
     * <p>
     * If this <code>TextLbyout</code> hbs blrebdy been justified, bn
     * exception is thrown.  If this <code>TextLbyout</code> object's
     * justificbtion rbtio is zero, b <code>TextLbyout</code> identicbl
     * to this <code>TextLbyout</code> is returned.
     * @pbrbm justificbtionWidth the width to use when justifying the line.
     * For best results, it should not be too different from the current
     * bdvbnce of the line.
     * @return b <code>TextLbyout</code> justified to the specified width.
     * @exception Error if this lbyout hbs blrebdy been justified, bn Error is
     * thrown.
     */
    public TextLbyout getJustifiedLbyout(flobt justificbtionWidth) {

        if (justificbtionWidth <= 0) {
            throw new IllegblArgumentException("justificbtionWidth <= 0 pbssed to TextLbyout.getJustifiedLbyout()");
        }

        if (justifyRbtio == ALREADY_JUSTIFIED) {
            throw new Error("Cbn't justify bgbin.");
        }

        ensureCbche(); // mbke sure textLine is not null

        // defbult justificbtion rbnge to exclude trbiling logicbl whitespbce
        int limit = chbrbcterCount;
        while (limit > 0 && textLine.isChbrWhitespbce(limit-1)) {
            --limit;
        }

        TextLine newLine = textLine.getJustifiedLine(justificbtionWidth, justifyRbtio, 0, limit);
        if (newLine != null) {
            return new TextLbyout(newLine, bbseline, bbselineOffsets, ALREADY_JUSTIFIED);
        }

        return this;
    }

    /**
     * Justify this lbyout.  Overridden by subclbssers to control justificbtion
     * (if there were subclbssers, thbt is...)
     *
     * The lbyout will only justify if the pbrbgrbph bttributes (from the
     * source text, possibly defbulted by the lbyout bttributes) indicbte b
     * non-zero justificbtion rbtio.  The text will be justified to the
     * indicbted width.  The current implementbtion blso bdjusts hbnging
     * punctubtion bnd trbiling whitespbce to overhbng the justificbtion width.
     * Once justified, the lbyout mby not be rejustified.
     * <p>
     * Some code mby rely on immutbblity of lbyouts.  Subclbssers should not
     * cbll this directly, but instebd should cbll getJustifiedLbyout, which
     * will cbll this method on b clone of this lbyout, preserving
     * the originbl.
     *
     * @pbrbm justificbtionWidth the width to use when justifying the line.
     * For best results, it should not be too different from the current
     * bdvbnce of the line.
     * @see #getJustifiedLbyout(flobt)
     */
    protected void hbndleJustify(flobt justificbtionWidth) {
      // never cblled
    }


    /**
     * Returns the bbseline for this <code>TextLbyout</code>.
     * The bbseline is one of the vblues defined in <code>Font</code>,
     * which bre rombn, centered bnd hbnging.  Ascent bnd descent bre
     * relbtive to this bbseline.  The <code>bbselineOffsets</code>
     * bre blso relbtive to this bbseline.
     * @return the bbseline of this <code>TextLbyout</code>.
     * @see #getBbselineOffsets()
     * @see Font
     */
    public byte getBbseline() {
        return bbseline;
    }

    /**
     * Returns the offsets brrby for the bbselines used for this
     * <code>TextLbyout</code>.
     * <p>
     * The brrby is indexed by one of the vblues defined in
     * <code>Font</code>, which bre rombn, centered bnd hbnging.  The
     * vblues bre relbtive to this <code>TextLbyout</code> object's
     * bbseline, so thbt <code>getBbselineOffsets[getBbseline()] == 0</code>.
     * Offsets bre bdded to the position of the <code>TextLbyout</code>
     * object's bbseline to get the position for the new bbseline.
     * @return the offsets brrby contbining the bbselines used for this
     *    <code>TextLbyout</code>.
     * @see #getBbseline()
     * @see Font
     */
    public flobt[] getBbselineOffsets() {
        flobt[] offsets = new flobt[bbselineOffsets.length];
        System.brrbycopy(bbselineOffsets, 0, offsets, 0, offsets.length);
        return offsets;
    }

    /**
     * Returns the bdvbnce of this <code>TextLbyout</code>.
     * The bdvbnce is the distbnce from the origin to the bdvbnce of the
     * rightmost (bottommost) chbrbcter.  This is in bbseline-relbtive
     * coordinbtes.
     * @return the bdvbnce of this <code>TextLbyout</code>.
     */
    public flobt getAdvbnce() {
        ensureCbche();
        return lineMetrics.bdvbnce;
    }

    /**
     * Returns the bdvbnce of this <code>TextLbyout</code>, minus trbiling
     * whitespbce.  This is in bbseline-relbtive coordinbtes.
     * @return the bdvbnce of this <code>TextLbyout</code> without the
     *      trbiling whitespbce.
     * @see #getAdvbnce()
     */
    public flobt getVisibleAdvbnce() {
        ensureCbche();
        return visibleAdvbnce;
    }

    /**
     * Returns the bscent of this <code>TextLbyout</code>.
     * The bscent is the distbnce from the top (right) of the
     * <code>TextLbyout</code> to the bbseline.  It is blwbys either
     * positive or zero.  The bscent is sufficient to
     * bccommodbte superscripted text bnd is the mbximum of the sum of the
     * bscent, offset, bnd bbseline of ebch glyph.  The bscent is
     * the mbximum bscent from the bbseline of bll the text in the
     * TextLbyout.  It is in bbseline-relbtive coordinbtes.
     * @return the bscent of this <code>TextLbyout</code>.
     */
    public flobt getAscent() {
        ensureCbche();
        return lineMetrics.bscent;
    }

    /**
     * Returns the descent of this <code>TextLbyout</code>.
     * The descent is the distbnce from the bbseline to the bottom (left) of
     * the <code>TextLbyout</code>.  It is blwbys either positive or zero.
     * The descent is sufficient to bccommodbte subscripted text bnd is the
     * mbximum of the sum of the descent, offset, bnd bbseline of ebch glyph.
     * This is the mbximum descent from the bbseline of bll the text in
     * the TextLbyout.  It is in bbseline-relbtive coordinbtes.
     * @return the descent of this <code>TextLbyout</code>.
     */
    public flobt getDescent() {
        ensureCbche();
        return lineMetrics.descent;
    }

    /**
     * Returns the lebding of the <code>TextLbyout</code>.
     * The lebding is the suggested interline spbcing for this
     * <code>TextLbyout</code>.  This is in bbseline-relbtive
     * coordinbtes.
     * <p>
     * The lebding is computed from the lebding, descent, bnd bbseline
     * of bll glyphvectors in the <code>TextLbyout</code>.  The blgorithm
     * is roughly bs follows:
     * <blockquote><pre>
     * mbxD = 0;
     * mbxDL = 0;
     * for (GlyphVector g in bll glyphvectors) {
     *    mbxD = mbx(mbxD, g.getDescent() + offsets[g.getBbseline()]);
     *    mbxDL = mbx(mbxDL, g.getDescent() + g.getLebding() +
     *                       offsets[g.getBbseline()]);
     * }
     * return mbxDL - mbxD;
     * </pre></blockquote>
     * @return the lebding of this <code>TextLbyout</code>.
     */
    public flobt getLebding() {
        ensureCbche();
        return lineMetrics.lebding;
    }

    /**
     * Returns the bounds of this <code>TextLbyout</code>.
     * The bounds bre in stbndbrd coordinbtes.
     * <p>Due to rbsterizbtion effects, this bounds might not enclose bll of the
     * pixels rendered by the TextLbyout.</p>
     * It might not coincide exbctly with the bscent, descent,
     * origin or bdvbnce of the <code>TextLbyout</code>.
     * @return b {@link Rectbngle2D} thbt is the bounds of this
     *        <code>TextLbyout</code>.
     */
    public Rectbngle2D getBounds() {
        ensureCbche();

        if (boundsRect == null) {
            Rectbngle2D vb = textLine.getVisublBounds();
            if (dx != 0 || dy != 0) {
                vb.setRect(vb.getX() - dx,
                           vb.getY() - dy,
                           vb.getWidth(),
                           vb.getHeight());
            }
            boundsRect = vb;
        }

        Rectbngle2D bounds = new Rectbngle2D.Flobt();
        bounds.setRect(boundsRect);

        return bounds;
    }

    /**
     * Returns the pixel bounds of this <code>TextLbyout</code> when
     * rendered in b grbphics with the given
     * <code>FontRenderContext</code> bt the given locbtion.  The
     * grbphics render context need not be the sbme bs the
     * <code>FontRenderContext</code> used to crebte this
     * <code>TextLbyout</code>, bnd cbn be null.  If it is null, the
     * <code>FontRenderContext</code> of this <code>TextLbyout</code>
     * is used.
     * @pbrbm frc the <code>FontRenderContext</code> of the <code>Grbphics</code>.
     * @pbrbm x the x-coordinbte bt which to render this <code>TextLbyout</code>.
     * @pbrbm y the y-coordinbte bt which to render this <code>TextLbyout</code>.
     * @return b <code>Rectbngle</code> bounding the pixels thbt would be bffected.
     * @see GlyphVector#getPixelBounds
     * @since 1.6
     */
    public Rectbngle getPixelBounds(FontRenderContext frc, flobt x, flobt y) {
        return textLine.getPixelBounds(frc, x, y);
    }

    /**
     * Returns <code>true</code> if this <code>TextLbyout</code> hbs
     * b left-to-right bbse direction or <code>fblse</code> if it hbs
     * b right-to-left bbse direction.  The <code>TextLbyout</code>
     * hbs b bbse direction of either left-to-right (LTR) or
     * right-to-left (RTL).  The bbse direction is independent of the
     * bctubl direction of text on the line, which mby be either LTR,
     * RTL, or mixed. Left-to-right lbyouts by defbult should position
     * flush left.  If the lbyout is on b tbbbed line, the
     * tbbs run left to right, so thbt logicblly successive lbyouts position
     * left to right.  The opposite is true for RTL lbyouts. By defbult they
     * should position flush left, bnd tbbs run right-to-left.
     * @return <code>true</code> if the bbse direction of this
     *         <code>TextLbyout</code> is left-to-right; <code>fblse</code>
     *         otherwise.
     */
    public boolebn isLeftToRight() {
        return textLine.isDirectionLTR();
    }

    /**
     * Returns <code>true</code> if this <code>TextLbyout</code> is verticbl.
     * @return <code>true</code> if this <code>TextLbyout</code> is verticbl;
     *      <code>fblse</code> otherwise.
     */
    public boolebn isVerticbl() {
        return isVerticblLine;
    }

    /**
     * Returns the number of chbrbcters represented by this
     * <code>TextLbyout</code>.
     * @return the number of chbrbcters in this <code>TextLbyout</code>.
     */
    public int getChbrbcterCount() {
        return chbrbcterCount;
    }

    /*
     * cbrets bnd hit testing
     *
     * Positions on b text line bre represented by instbnces of TextHitInfo.
     * Any TextHitInfo with chbrbcterOffset between 0 bnd chbrbcterCount-1,
     * inclusive, represents b vblid position on the line.  Additionblly,
     * [-1, trbiling] bnd [chbrbcterCount, lebding] bre vblid positions, bnd
     * represent positions bt the logicbl stbrt bnd end of the line,
     * respectively.
     *
     * The chbrbcterOffsets in TextHitInfo's used bnd returned by TextLbyout
     * bre relbtive to the beginning of the text lbyout, not necessbrily to
     * the beginning of the text storbge the client is using.
     *
     *
     * Every vblid TextHitInfo hbs either one or two cbrets bssocibted with it.
     * A cbret is b visubl locbtion in the TextLbyout indicbting where text bt
     * the TextHitInfo will be displbyed on screen.  If b TextHitInfo
     * represents b locbtion on b directionbl boundbry, then there bre two
     * possible visible positions for newly inserted text.  Consider the
     * following exbmple, in which cbpitbl letters indicbte right-to-left text,
     * bnd the overbll line direction is left-to-right:
     *
     * Text Storbge: [ b, b, C, D, E, f ]
     * Displby:        b b E D C f
     *
     * The text hit info (1, t) represents the trbiling side of 'b'.  If 'q',
     * b left-to-right chbrbcter is inserted into the text storbge bt this
     * locbtion, it will be displbyed between the 'b' bnd the 'E':
     *
     * Text Storbge: [ b, b, q, C, D, E, f ]
     * Displby:        b b q E D C f
     *
     * However, if b 'W', which is right-to-left, is inserted into the storbge
     * bfter 'b', the storbge bnd displby will be:
     *
     * Text Storbge: [ b, b, W, C, D, E, f ]
     * Displby:        b b E D C W f
     *
     * So, for the originbl text storbge, two cbrets should be displbyed for
     * locbtion (1, t): one visublly between 'b' bnd 'E' bnd one visublly
     * between 'C' bnd 'f'.
     *
     *
     * When two cbrets bre displbyed for b TextHitInfo, one cbret is the
     * 'strong' cbret bnd the other is the 'webk' cbret.  The strong cbret
     * indicbtes where bn inserted chbrbcter will be displbyed when thbt
     * chbrbcter's direction is the sbme bs the direction of the TextLbyout.
     * The webk cbret shows where bn chbrbcter inserted chbrbcter will be
     * displbyed when the chbrbcter's direction is opposite thbt of the
     * TextLbyout.
     *
     *
     * Clients should not be overly concerned with the detbils of correct
     * cbret displby. TextLbyout.getCbretShbpes(TextHitInfo) will return bn
     * brrby of two pbths representing where cbrets should be displbyed.
     * The first pbth in the brrby is the strong cbret; the second element,
     * if non-null, is the webk cbret.  If the second element is null,
     * then there is no webk cbret for the given TextHitInfo.
     *
     *
     * Since text cbn be visublly reordered, logicblly consecutive
     * TextHitInfo's mby not be visublly consecutive.  One implicbtion of this
     * is thbt b client cbnnot tell from inspecting b TextHitInfo whether the
     * hit represents the first (or lbst) cbret in the lbyout.  Clients
     * cbn cbll getVisublOtherHit();  if the visubl compbnion is
     * (-1, TRAILING) or (chbrbcterCount, LEADING), then the hit is bt the
     * first (lbst) cbret position in the lbyout.
     */

    privbte flobt[] getCbretInfo(int cbret,
                                 Rectbngle2D bounds,
                                 flobt[] info) {

        flobt top1X, top2X;
        flobt bottom1X, bottom2X;

        if (cbret == 0 || cbret == chbrbcterCount) {

            flobt pos;
            int logIndex;
            if (cbret == chbrbcterCount) {
                logIndex = textLine.visublToLogicbl(chbrbcterCount-1);
                pos = textLine.getChbrLinePosition(logIndex)
                                        + textLine.getChbrAdvbnce(logIndex);
            }
            else {
                logIndex = textLine.visublToLogicbl(cbret);
                pos = textLine.getChbrLinePosition(logIndex);
            }
            flobt bngle = textLine.getChbrAngle(logIndex);
            flobt shift = textLine.getChbrShift(logIndex);
            pos += bngle * shift;
            top1X = top2X = pos + bngle*textLine.getChbrAscent(logIndex);
            bottom1X = bottom2X = pos - bngle*textLine.getChbrDescent(logIndex);
        }
        else {

            {
                int logIndex = textLine.visublToLogicbl(cbret-1);
                flobt bngle1 = textLine.getChbrAngle(logIndex);
                flobt pos1 = textLine.getChbrLinePosition(logIndex)
                                    + textLine.getChbrAdvbnce(logIndex);
                if (bngle1 != 0) {
                    pos1 += bngle1 * textLine.getChbrShift(logIndex);
                    top1X = pos1 + bngle1*textLine.getChbrAscent(logIndex);
                    bottom1X = pos1 - bngle1*textLine.getChbrDescent(logIndex);
                }
                else {
                    top1X = bottom1X = pos1;
                }
            }
            {
                int logIndex = textLine.visublToLogicbl(cbret);
                flobt bngle2 = textLine.getChbrAngle(logIndex);
                flobt pos2 = textLine.getChbrLinePosition(logIndex);
                if (bngle2 != 0) {
                    pos2 += bngle2*textLine.getChbrShift(logIndex);
                    top2X = pos2 + bngle2*textLine.getChbrAscent(logIndex);
                    bottom2X = pos2 - bngle2*textLine.getChbrDescent(logIndex);
                }
                else {
                    top2X = bottom2X = pos2;
                }
            }
        }

        flobt topX = (top1X + top2X) / 2;
        flobt bottomX = (bottom1X + bottom2X) / 2;

        if (info == null) {
            info = new flobt[2];
        }

        if (isVerticblLine) {
            info[1] = (flobt) ((topX - bottomX) / bounds.getWidth());
            info[0] = (flobt) (topX + (info[1]*bounds.getX()));
        }
        else {
            info[1] = (flobt) ((topX - bottomX) / bounds.getHeight());
            info[0] = (flobt) (bottomX + (info[1]*bounds.getMbxY()));
        }

        return info;
    }

    /**
     * Returns informbtion bbout the cbret corresponding to <code>hit</code>.
     * The first element of the brrby is the intersection of the cbret with
     * the bbseline, bs b distbnce blong the bbseline. The second element
     * of the brrby is the inverse slope (run/rise) of the cbret, mebsured
     * with respect to the bbseline bt thbt point.
     * <p>
     * This method is mebnt for informbtionbl use.  To displby cbrets, it
     * is better to use <code>getCbretShbpes</code>.
     * @pbrbm hit b hit on b chbrbcter in this <code>TextLbyout</code>
     * @pbrbm bounds the bounds to which the cbret info is constructed.
     *     The bounds is in bbseline-relbtive coordinbtes.
     * @return b two-element brrby contbining the position bnd slope of
     * the cbret.  The returned cbret info is in bbseline-relbtive coordinbtes.
     * @see #getCbretShbpes(int, Rectbngle2D, TextLbyout.CbretPolicy)
     * @see Font#getItblicAngle
     */
    public flobt[] getCbretInfo(TextHitInfo hit, Rectbngle2D bounds) {
        ensureCbche();
        checkTextHit(hit);

        return getCbretInfoTestInternbl(hit, bounds);
    }

    // this version provides extrb info in the flobt brrby
    // the first two vblues bre bs bbove
    // the next four vblues bre the endpoints of the cbret, bs computed
    // using the hit chbrbcter's offset (bbseline + ssoffset) bnd
    // nbturbl bscent bnd descent.
    // these  vblues bre trimmed to the bounds where required to fit,
    // but otherwise independent of it.
    privbte flobt[] getCbretInfoTestInternbl(TextHitInfo hit, Rectbngle2D bounds) {
        ensureCbche();
        checkTextHit(hit);

        flobt[] info = new flobt[6];

        // get old dbtb first
        getCbretInfo(hitToCbret(hit), bounds, info);

        // then bdd our new dbtb
        double ibngle, ixbbse, p1x, p1y, p2x, p2y;

        int chbrix = hit.getChbrIndex();
        boolebn lebd = hit.isLebdingEdge();
        boolebn ltr = textLine.isDirectionLTR();
        boolebn horiz = !isVerticbl();

        if (chbrix == -1 || chbrix == chbrbcterCount) {
            // !!! note: wbnt non-shifted, bbseline bscent bnd descent here!
            // TextLine should return bppropribte line metrics object for these vblues
            TextLineMetrics m = textLine.getMetrics();
            boolebn low = ltr == (chbrix == -1);
            ibngle = 0;
            if (horiz) {
                p1x = p2x = low ? 0 : m.bdvbnce;
                p1y = -m.bscent;
                p2y = m.descent;
            } else {
                p1y = p2y = low ? 0 : m.bdvbnce;
                p1x = m.descent;
                p2x = m.bscent;
            }
        } else {
            CoreMetrics thiscm = textLine.getCoreMetricsAt(chbrix);
            ibngle = thiscm.itblicAngle;
            ixbbse = textLine.getChbrLinePosition(chbrix, lebd);
            if (thiscm.bbselineIndex < 0) {
                // this is b grbphic, no itblics, use entire line height for cbret
                TextLineMetrics m = textLine.getMetrics();
                if (horiz) {
                    p1x = p2x = ixbbse;
                    if (thiscm.bbselineIndex == GrbphicAttribute.TOP_ALIGNMENT) {
                        p1y = -m.bscent;
                        p2y = p1y + thiscm.height;
                    } else {
                        p2y = m.descent;
                        p1y = p2y - thiscm.height;
                    }
                } else {
                    p1y = p2y = ixbbse;
                    p1x = m.descent;
                    p2x = m.bscent;
                    // !!! top/bottom bdjustment not implemented for verticbl
                }
            } else {
                flobt bo = bbselineOffsets[thiscm.bbselineIndex];
                if (horiz) {
                    ixbbse += ibngle * thiscm.ssOffset;
                    p1x = ixbbse + ibngle * thiscm.bscent;
                    p2x = ixbbse - ibngle * thiscm.descent;
                    p1y = bo - thiscm.bscent;
                    p2y = bo + thiscm.descent;
                } else {
                    ixbbse -= ibngle * thiscm.ssOffset;
                    p1y = ixbbse + ibngle * thiscm.bscent;
                    p2y = ixbbse - ibngle * thiscm.descent;
                    p1x = bo + thiscm.bscent;
                    p2x = bo + thiscm.descent;
                }
            }
        }

        info[2] = (flobt)p1x;
        info[3] = (flobt)p1y;
        info[4] = (flobt)p2x;
        info[5] = (flobt)p2y;

        return info;
    }

    /**
     * Returns informbtion bbout the cbret corresponding to <code>hit</code>.
     * This method is b convenience overlobd of <code>getCbretInfo</code> bnd
     * uses the nbturbl bounds of this <code>TextLbyout</code>.
     * @pbrbm hit b hit on b chbrbcter in this <code>TextLbyout</code>
     * @return the informbtion bbout b cbret corresponding to b hit.  The
     *     returned cbret info is in bbseline-relbtive coordinbtes.
     */
    public flobt[] getCbretInfo(TextHitInfo hit) {

        return getCbretInfo(hit, getNbturblBounds());
    }

    /**
     * Returns b cbret index corresponding to <code>hit</code>.
     * Cbrets bre numbered from left to right (top to bottom) stbrting from
     * zero. This blwbys plbces cbrets next to the chbrbcter hit, on the
     * indicbted side of the chbrbcter.
     * @pbrbm hit b hit on b chbrbcter in this <code>TextLbyout</code>
     * @return b cbret index corresponding to the specified hit.
     */
    privbte int hitToCbret(TextHitInfo hit) {

        int hitIndex = hit.getChbrIndex();

        if (hitIndex < 0) {
            return textLine.isDirectionLTR() ? 0 : chbrbcterCount;
        } else if (hitIndex >= chbrbcterCount) {
            return textLine.isDirectionLTR() ? chbrbcterCount : 0;
        }

        int visIndex = textLine.logicblToVisubl(hitIndex);

        if (hit.isLebdingEdge() != textLine.isChbrLTR(hitIndex)) {
            ++visIndex;
        }

        return visIndex;
    }

    /**
     * Given b cbret index, return b hit whose cbret is bt the index.
     * The hit is NOT gubrbnteed to be strong!!!
     *
     * @pbrbm cbret b cbret index.
     * @return b hit on this lbyout whose strong cbret is bt the requested
     * index.
     */
    privbte TextHitInfo cbretToHit(int cbret) {

        if (cbret == 0 || cbret == chbrbcterCount) {

            if ((cbret == chbrbcterCount) == textLine.isDirectionLTR()) {
                return TextHitInfo.lebding(chbrbcterCount);
            }
            else {
                return TextHitInfo.trbiling(-1);
            }
        }
        else {

            int chbrIndex = textLine.visublToLogicbl(cbret);
            boolebn lebding = textLine.isChbrLTR(chbrIndex);

            return lebding? TextHitInfo.lebding(chbrIndex)
                            : TextHitInfo.trbiling(chbrIndex);
        }
    }

    privbte boolebn cbretIsVblid(int cbret) {

        if (cbret == chbrbcterCount || cbret == 0) {
            return true;
        }

        int offset = textLine.visublToLogicbl(cbret);

        if (!textLine.isChbrLTR(offset)) {
            offset = textLine.visublToLogicbl(cbret-1);
            if (textLine.isChbrLTR(offset)) {
                return true;
            }
        }

        // At this point, the lebding edge of the chbrbcter
        // bt offset is bt the given cbret.

        return textLine.cbretAtOffsetIsVblid(offset);
    }

    /**
     * Returns the hit for the next cbret to the right (bottom); if there
     * is no such hit, returns <code>null</code>.
     * If the hit chbrbcter index is out of bounds, bn
     * {@link IllegblArgumentException} is thrown.
     * @pbrbm hit b hit on b chbrbcter in this lbyout
     * @return b hit whose cbret bppebrs bt the next position to the
     * right (bottom) of the cbret of the provided hit or <code>null</code>.
     */
    public TextHitInfo getNextRightHit(TextHitInfo hit) {
        ensureCbche();
        checkTextHit(hit);

        int cbret = hitToCbret(hit);

        if (cbret == chbrbcterCount) {
            return null;
        }

        do {
            ++cbret;
        } while (!cbretIsVblid(cbret));

        return cbretToHit(cbret);
    }

    /**
     * Returns the hit for the next cbret to the right (bottom); if no
     * such hit, returns <code>null</code>.  The hit is to the right of
     * the strong cbret bt the specified offset, bs determined by the
     * specified policy.
     * The returned hit is the stronger of the two possible
     * hits, bs determined by the specified policy.
     * @pbrbm offset bn insertion offset in this <code>TextLbyout</code>.
     * Cbnnot be less thbn 0 or grebter thbn this <code>TextLbyout</code>
     * object's chbrbcter count.
     * @pbrbm policy the policy used to select the strong cbret
     * @return b hit whose cbret bppebrs bt the next position to the
     * right (bottom) of the cbret of the provided hit, or <code>null</code>.
     */
    public TextHitInfo getNextRightHit(int offset, CbretPolicy policy) {

        if (offset < 0 || offset > chbrbcterCount) {
            throw new IllegblArgumentException("Offset out of bounds in TextLbyout.getNextRightHit()");
        }

        if (policy == null) {
            throw new IllegblArgumentException("Null CbretPolicy pbssed to TextLbyout.getNextRightHit()");
        }

        TextHitInfo hit1 = TextHitInfo.bfterOffset(offset);
        TextHitInfo hit2 = hit1.getOtherHit();

        TextHitInfo nextHit = getNextRightHit(policy.getStrongCbret(hit1, hit2, this));

        if (nextHit != null) {
            TextHitInfo otherHit = getVisublOtherHit(nextHit);
            return policy.getStrongCbret(otherHit, nextHit, this);
        }
        else {
            return null;
        }
    }

    /**
     * Returns the hit for the next cbret to the right (bottom); if no
     * such hit, returns <code>null</code>.  The hit is to the right of
     * the strong cbret bt the specified offset, bs determined by the
     * defbult policy.
     * The returned hit is the stronger of the two possible
     * hits, bs determined by the defbult policy.
     * @pbrbm offset bn insertion offset in this <code>TextLbyout</code>.
     * Cbnnot be less thbn 0 or grebter thbn the <code>TextLbyout</code>
     * object's chbrbcter count.
     * @return b hit whose cbret bppebrs bt the next position to the
     * right (bottom) of the cbret of the provided hit, or <code>null</code>.
     */
    public TextHitInfo getNextRightHit(int offset) {

        return getNextRightHit(offset, DEFAULT_CARET_POLICY);
    }

    /**
     * Returns the hit for the next cbret to the left (top); if no such
     * hit, returns <code>null</code>.
     * If the hit chbrbcter index is out of bounds, bn
     * <code>IllegblArgumentException</code> is thrown.
     * @pbrbm hit b hit on b chbrbcter in this <code>TextLbyout</code>.
     * @return b hit whose cbret bppebrs bt the next position to the
     * left (top) of the cbret of the provided hit, or <code>null</code>.
     */
    public TextHitInfo getNextLeftHit(TextHitInfo hit) {
        ensureCbche();
        checkTextHit(hit);

        int cbret = hitToCbret(hit);

        if (cbret == 0) {
            return null;
        }

        do {
            --cbret;
        } while(!cbretIsVblid(cbret));

        return cbretToHit(cbret);
    }

    /**
     * Returns the hit for the next cbret to the left (top); if no
     * such hit, returns <code>null</code>.  The hit is to the left of
     * the strong cbret bt the specified offset, bs determined by the
     * specified policy.
     * The returned hit is the stronger of the two possible
     * hits, bs determined by the specified policy.
     * @pbrbm offset bn insertion offset in this <code>TextLbyout</code>.
     * Cbnnot be less thbn 0 or grebter thbn this <code>TextLbyout</code>
     * object's chbrbcter count.
     * @pbrbm policy the policy used to select the strong cbret
     * @return b hit whose cbret bppebrs bt the next position to the
     * left (top) of the cbret of the provided hit, or <code>null</code>.
     */
    public TextHitInfo getNextLeftHit(int offset, CbretPolicy policy) {

        if (policy == null) {
            throw new IllegblArgumentException("Null CbretPolicy pbssed to TextLbyout.getNextLeftHit()");
        }

        if (offset < 0 || offset > chbrbcterCount) {
            throw new IllegblArgumentException("Offset out of bounds in TextLbyout.getNextLeftHit()");
        }

        TextHitInfo hit1 = TextHitInfo.bfterOffset(offset);
        TextHitInfo hit2 = hit1.getOtherHit();

        TextHitInfo nextHit = getNextLeftHit(policy.getStrongCbret(hit1, hit2, this));

        if (nextHit != null) {
            TextHitInfo otherHit = getVisublOtherHit(nextHit);
            return policy.getStrongCbret(otherHit, nextHit, this);
        }
        else {
            return null;
        }
    }

    /**
     * Returns the hit for the next cbret to the left (top); if no
     * such hit, returns <code>null</code>.  The hit is to the left of
     * the strong cbret bt the specified offset, bs determined by the
     * defbult policy.
     * The returned hit is the stronger of the two possible
     * hits, bs determined by the defbult policy.
     * @pbrbm offset bn insertion offset in this <code>TextLbyout</code>.
     * Cbnnot be less thbn 0 or grebter thbn this <code>TextLbyout</code>
     * object's chbrbcter count.
     * @return b hit whose cbret bppebrs bt the next position to the
     * left (top) of the cbret of the provided hit, or <code>null</code>.
     */
    public TextHitInfo getNextLeftHit(int offset) {

        return getNextLeftHit(offset, DEFAULT_CARET_POLICY);
    }

    /**
     * Returns the hit on the opposite side of the specified hit's cbret.
     * @pbrbm hit the specified hit
     * @return b hit thbt is on the opposite side of the specified hit's
     *    cbret.
     */
    public TextHitInfo getVisublOtherHit(TextHitInfo hit) {

        ensureCbche();
        checkTextHit(hit);

        int hitChbrIndex = hit.getChbrIndex();

        int chbrIndex;
        boolebn lebding;

        if (hitChbrIndex == -1 || hitChbrIndex == chbrbcterCount) {

            int visIndex;
            if (textLine.isDirectionLTR() == (hitChbrIndex == -1)) {
                visIndex = 0;
            }
            else {
                visIndex = chbrbcterCount-1;
            }

            chbrIndex = textLine.visublToLogicbl(visIndex);

            if (textLine.isDirectionLTR() == (hitChbrIndex == -1)) {
                // bt left end
                lebding = textLine.isChbrLTR(chbrIndex);
            }
            else {
                // bt right end
                lebding = !textLine.isChbrLTR(chbrIndex);
            }
        }
        else {

            int visIndex = textLine.logicblToVisubl(hitChbrIndex);

            boolebn movedToRight;
            if (textLine.isChbrLTR(hitChbrIndex) == hit.isLebdingEdge()) {
                --visIndex;
                movedToRight = fblse;
            }
            else {
                ++visIndex;
                movedToRight = true;
            }

            if (visIndex > -1 && visIndex < chbrbcterCount) {
                chbrIndex = textLine.visublToLogicbl(visIndex);
                lebding = movedToRight == textLine.isChbrLTR(chbrIndex);
            }
            else {
                chbrIndex =
                    (movedToRight == textLine.isDirectionLTR())? chbrbcterCount : -1;
                lebding = chbrIndex == chbrbcterCount;
            }
        }

        return lebding? TextHitInfo.lebding(chbrIndex) :
                                TextHitInfo.trbiling(chbrIndex);
    }

    privbte double[] getCbretPbth(TextHitInfo hit, Rectbngle2D bounds) {
        flobt[] info = getCbretInfo(hit, bounds);
        return new double[] { info[2], info[3], info[4], info[5] };
    }

    /**
     * Return bn brrby of four flobts corresponding the endpoints of the cbret
     * x0, y0, x1, y1.
     *
     * This crebtes b line blong the slope of the cbret intersecting the
     * bbseline bt the cbret
     * position, bnd extending from bscent bbove the bbseline to descent below
     * it.
     */
    privbte double[] getCbretPbth(int cbret, Rectbngle2D bounds,
                                  boolebn clipToBounds) {

        flobt[] info = getCbretInfo(cbret, bounds, null);

        double pos = info[0];
        double slope = info[1];

        double x0, y0, x1, y1;
        double x2 = -3141.59, y2 = -2.7; // vblues bre there to mbke compiler hbppy

        double left = bounds.getX();
        double right = left + bounds.getWidth();
        double top = bounds.getY();
        double bottom = top + bounds.getHeight();

        boolebn threePoints = fblse;

        if (isVerticblLine) {

            if (slope >= 0) {
                x0 = left;
                x1 = right;
            }
            else {
                x1 = left;
                x0 = right;
            }

            y0 = pos + x0 * slope;
            y1 = pos + x1 * slope;

            // y0 <= y1, blwbys

            if (clipToBounds) {
                if (y0 < top) {
                    if (slope <= 0 || y1 <= top) {
                        y0 = y1 = top;
                    }
                    else {
                        threePoints = true;
                        y0 = top;
                        y2 = top;
                        x2 = x1 + (top-y1)/slope;
                        if (y1 > bottom) {
                            y1 = bottom;
                        }
                    }
                }
                else if (y1 > bottom) {
                    if (slope >= 0 || y0 >= bottom) {
                        y0 = y1 = bottom;
                    }
                    else {
                        threePoints = true;
                        y1 = bottom;
                        y2 = bottom;
                        x2 = x0 + (bottom-x1)/slope;
                    }
                }
            }

        }
        else {

            if (slope >= 0) {
                y0 = bottom;
                y1 = top;
            }
            else {
                y1 = bottom;
                y0 = top;
            }

            x0 = pos - y0 * slope;
            x1 = pos - y1 * slope;

            // x0 <= x1, blwbys

            if (clipToBounds) {
                if (x0 < left) {
                    if (slope <= 0 || x1 <= left) {
                        x0 = x1 = left;
                    }
                    else {
                        threePoints = true;
                        x0 = left;
                        x2 = left;
                        y2 = y1 - (left-x1)/slope;
                        if (x1 > right) {
                            x1 = right;
                        }
                    }
                }
                else if (x1 > right) {
                    if (slope >= 0 || x0 >= right) {
                        x0 = x1 = right;
                    }
                    else {
                        threePoints = true;
                        x1 = right;
                        x2 = right;
                        y2 = y0 - (right-x0)/slope;
                    }
                }
            }
        }

        return threePoints?
                    new double[] { x0, y0, x2, y2, x1, y1 } :
                    new double[] { x0, y0, x1, y1 };
    }


    privbte stbtic GenerblPbth pbthToShbpe(double[] pbth, boolebn close, LbyoutPbthImpl lp) {
        GenerblPbth result = new GenerblPbth(GenerblPbth.WIND_EVEN_ODD, pbth.length);
        result.moveTo((flobt)pbth[0], (flobt)pbth[1]);
        for (int i = 2; i < pbth.length; i += 2) {
            result.lineTo((flobt)pbth[i], (flobt)pbth[i+1]);
        }
        if (close) {
            result.closePbth();
        }

        if (lp != null) {
            result = (GenerblPbth)lp.mbpShbpe(result);
        }
        return result;
    }

    /**
     * Returns b {@link Shbpe} representing the cbret bt the specified
     * hit inside the specified bounds.
     * @pbrbm hit the hit bt which to generbte the cbret
     * @pbrbm bounds the bounds of the <code>TextLbyout</code> to use
     *    in generbting the cbret.  The bounds is in bbseline-relbtive
     *    coordinbtes.
     * @return b <code>Shbpe</code> representing the cbret.  The returned
     *    shbpe is in stbndbrd coordinbtes.
     */
    public Shbpe getCbretShbpe(TextHitInfo hit, Rectbngle2D bounds) {
        ensureCbche();
        checkTextHit(hit);

        if (bounds == null) {
            throw new IllegblArgumentException("Null Rectbngle2D pbssed to TextLbyout.getCbret()");
        }

        return pbthToShbpe(getCbretPbth(hit, bounds), fblse, textLine.getLbyoutPbth());
    }

    /**
     * Returns b <code>Shbpe</code> representing the cbret bt the specified
     * hit inside the nbturbl bounds of this <code>TextLbyout</code>.
     * @pbrbm hit the hit bt which to generbte the cbret
     * @return b <code>Shbpe</code> representing the cbret.  The returned
     *     shbpe is in stbndbrd coordinbtes.
     */
    public Shbpe getCbretShbpe(TextHitInfo hit) {

        return getCbretShbpe(hit, getNbturblBounds());
    }

    /**
     * Return the "stronger" of the TextHitInfos.  The TextHitInfos
     * should be logicbl or visubl counterpbrts.  They bre not
     * checked for vblidity.
     */
    privbte finbl TextHitInfo getStrongHit(TextHitInfo hit1, TextHitInfo hit2) {

        // right now we're using the following rule for strong hits:
        // A hit on b chbrbcter with b lower level
        // is stronger thbn one on b chbrbcter with b higher level.
        // If this rule ties, the hit on the lebding edge of b chbrbcter wins.
        // If THIS rule ties, hit1 wins.  Both rules shouldn't tie, unless the
        // infos bren't counterpbrts of some sort.

        byte hit1Level = getChbrbcterLevel(hit1.getChbrIndex());
        byte hit2Level = getChbrbcterLevel(hit2.getChbrIndex());

        if (hit1Level == hit2Level) {
            if (hit2.isLebdingEdge() && !hit1.isLebdingEdge()) {
                return hit2;
            }
            else {
                return hit1;
            }
        }
        else {
            return (hit1Level < hit2Level)? hit1 : hit2;
        }
    }

    /**
     * Returns the level of the chbrbcter bt <code>index</code>.
     * Indices -1 bnd <code>chbrbcterCount</code> bre bssigned the bbse
     * level of this <code>TextLbyout</code>.
     * @pbrbm index the index of the chbrbcter from which to get the level
     * @return the level of the chbrbcter bt the specified index.
     */
    public byte getChbrbcterLevel(int index) {

        // hmm, bllow indices bt endpoints?  For now, yes.
        if (index < -1 || index > chbrbcterCount) {
            throw new IllegblArgumentException("Index is out of rbnge in getChbrbcterLevel.");
        }

        ensureCbche();
        if (index == -1 || index == chbrbcterCount) {
             return (byte) (textLine.isDirectionLTR()? 0 : 1);
        }

        return textLine.getChbrLevel(index);
    }

    /**
     * Returns two pbths corresponding to the strong bnd webk cbret.
     * @pbrbm offset bn offset in this <code>TextLbyout</code>
     * @pbrbm bounds the bounds to which to extend the cbrets.  The
     * bounds is in bbseline-relbtive coordinbtes.
     * @pbrbm policy the specified <code>CbretPolicy</code>
     * @return bn brrby of two pbths.  Element zero is the strong
     * cbret.  If there bre two cbrets, element one is the webk cbret,
     * otherwise it is <code>null</code>. The returned shbpes
     * bre in stbndbrd coordinbtes.
     */
    public Shbpe[] getCbretShbpes(int offset, Rectbngle2D bounds, CbretPolicy policy) {

        ensureCbche();

        if (offset < 0 || offset > chbrbcterCount) {
            throw new IllegblArgumentException("Offset out of bounds in TextLbyout.getCbretShbpes()");
        }

        if (bounds == null) {
            throw new IllegblArgumentException("Null Rectbngle2D pbssed to TextLbyout.getCbretShbpes()");
        }

        if (policy == null) {
            throw new IllegblArgumentException("Null CbretPolicy pbssed to TextLbyout.getCbretShbpes()");
        }

        Shbpe[] result = new Shbpe[2];

        TextHitInfo hit = TextHitInfo.bfterOffset(offset);

        int hitCbret = hitToCbret(hit);

        LbyoutPbthImpl lp = textLine.getLbyoutPbth();
        Shbpe hitShbpe = pbthToShbpe(getCbretPbth(hit, bounds), fblse, lp);
        TextHitInfo otherHit = hit.getOtherHit();
        int otherCbret = hitToCbret(otherHit);

        if (hitCbret == otherCbret) {
            result[0] = hitShbpe;
        }
        else { // more thbn one cbret
            Shbpe otherShbpe = pbthToShbpe(getCbretPbth(otherHit, bounds), fblse, lp);

            TextHitInfo strongHit = policy.getStrongCbret(hit, otherHit, this);
            boolebn hitIsStrong = strongHit.equbls(hit);

            if (hitIsStrong) {// then other is webk
                result[0] = hitShbpe;
                result[1] = otherShbpe;
            }
            else {
                result[0] = otherShbpe;
                result[1] = hitShbpe;
            }
        }

        return result;
    }

    /**
     * Returns two pbths corresponding to the strong bnd webk cbret.
     * This method is b convenience overlobd of <code>getCbretShbpes</code>
     * thbt uses the defbult cbret policy.
     * @pbrbm offset bn offset in this <code>TextLbyout</code>
     * @pbrbm bounds the bounds to which to extend the cbrets.  This is
     *     in bbseline-relbtive coordinbtes.
     * @return two pbths corresponding to the strong bnd webk cbret bs
     *    defined by the <code>DEFAULT_CARET_POLICY</code>.  These bre
     *    in stbndbrd coordinbtes.
     */
    public Shbpe[] getCbretShbpes(int offset, Rectbngle2D bounds) {
        // {sfb} pbrbmeter checking is done in overlobded version
        return getCbretShbpes(offset, bounds, DEFAULT_CARET_POLICY);
    }

    /**
     * Returns two pbths corresponding to the strong bnd webk cbret.
     * This method is b convenience overlobd of <code>getCbretShbpes</code>
     * thbt uses the defbult cbret policy bnd this <code>TextLbyout</code>
     * object's nbturbl bounds.
     * @pbrbm offset bn offset in this <code>TextLbyout</code>
     * @return two pbths corresponding to the strong bnd webk cbret bs
     *    defined by the <code>DEFAULT_CARET_POLICY</code>.  These bre
     *    in stbndbrd coordinbtes.
     */
    public Shbpe[] getCbretShbpes(int offset) {
        // {sfb} pbrbmeter checking is done in overlobded version
        return getCbretShbpes(offset, getNbturblBounds(), DEFAULT_CARET_POLICY);
    }

    // A utility to return b pbth enclosing the given pbth
    // Pbth0 must be left or top of pbth1
    // {jbr} no bssumptions bbout size of pbth0, pbth1 bnymore.
    privbte GenerblPbth boundingShbpe(double[] pbth0, double[] pbth1) {

        // Reblly, we wbnt the pbth to be b convex hull bround bll of the
        // points in pbth0 bnd pbth1.  But we cbn get by with less thbn
        // thbt.  We do need to prevent the two segments which
        // join pbth0 to pbth1 from crossing ebch other.  So, if we
        // trbverse pbth0 from top to bottom, we'll trbverse pbth1 from
        // bottom to top (bnd vice versb).

        GenerblPbth result = pbthToShbpe(pbth0, fblse, null);

        boolebn sbmeDirection;

        if (isVerticblLine) {
            sbmeDirection = (pbth0[1] > pbth0[pbth0.length-1]) ==
                            (pbth1[1] > pbth1[pbth1.length-1]);
        }
        else {
            sbmeDirection = (pbth0[0] > pbth0[pbth0.length-2]) ==
                            (pbth1[0] > pbth1[pbth1.length-2]);
        }

        int stbrt;
        int limit;
        int increment;

        if (sbmeDirection) {
            stbrt = pbth1.length-2;
            limit = -2;
            increment = -2;
        }
        else {
            stbrt = 0;
            limit = pbth1.length;
            increment = 2;
        }

        for (int i = stbrt; i != limit; i += increment) {
            result.lineTo((flobt)pbth1[i], (flobt)pbth1[i+1]);
        }

        result.closePbth();

        return result;
    }

    // A utility to convert b pbir of cbrets into b bounding pbth
    // {jbr} Shbpe is never outside of bounds.
    privbte GenerblPbth cbretBoundingShbpe(int cbret0,
                                           int cbret1,
                                           Rectbngle2D bounds) {

        if (cbret0 > cbret1) {
            int temp = cbret0;
            cbret0 = cbret1;
            cbret1 = temp;
        }

        return boundingShbpe(getCbretPbth(cbret0, bounds, true),
                             getCbretPbth(cbret1, bounds, true));
    }

    /*
     * A utility to return the pbth bounding the breb to the left (top) of the
     * lbyout.
     * Shbpe is never outside of bounds.
     */
    privbte GenerblPbth leftShbpe(Rectbngle2D bounds) {

        double[] pbth0;
        if (isVerticblLine) {
            pbth0 = new double[] { bounds.getX(), bounds.getY(),
                                       bounds.getX() + bounds.getWidth(),
                                       bounds.getY() };
        } else {
            pbth0 = new double[] { bounds.getX(),
                                       bounds.getY() + bounds.getHeight(),
                                       bounds.getX(), bounds.getY() };
        }

        double[] pbth1 = getCbretPbth(0, bounds, true);

        return boundingShbpe(pbth0, pbth1);
    }

    /*
     * A utility to return the pbth bounding the breb to the right (bottom) of
     * the lbyout.
     */
    privbte GenerblPbth rightShbpe(Rectbngle2D bounds) {
        double[] pbth1;
        if (isVerticblLine) {
            pbth1 = new double[] {
                bounds.getX(),
                bounds.getY() + bounds.getHeight(),
                bounds.getX() + bounds.getWidth(),
                bounds.getY() + bounds.getHeight()
            };
        } else {
            pbth1 = new double[] {
                bounds.getX() + bounds.getWidth(),
                bounds.getY() + bounds.getHeight(),
                bounds.getX() + bounds.getWidth(),
                bounds.getY()
            };
        }

        double[] pbth0 = getCbretPbth(chbrbcterCount, bounds, true);

        return boundingShbpe(pbth0, pbth1);
    }

    /**
     * Returns the logicbl rbnges of text corresponding to b visubl selection.
     * @pbrbm firstEndpoint bn endpoint of the visubl rbnge
     * @pbrbm secondEndpoint the other endpoint of the visubl rbnge.
     * This endpoint cbn be less thbn <code>firstEndpoint</code>.
     * @return bn brrby of integers representing stbrt/limit pbirs for the
     * selected rbnges.
     * @see #getVisublHighlightShbpe(TextHitInfo, TextHitInfo, Rectbngle2D)
     */
    public int[] getLogicblRbngesForVisublSelection(TextHitInfo firstEndpoint,
                                                    TextHitInfo secondEndpoint) {
        ensureCbche();

        checkTextHit(firstEndpoint);
        checkTextHit(secondEndpoint);

        // !!! probbbly wbnt to optimize for bll LTR text

        boolebn[] included = new boolebn[chbrbcterCount];

        int stbrtIndex = hitToCbret(firstEndpoint);
        int limitIndex = hitToCbret(secondEndpoint);

        if (stbrtIndex > limitIndex) {
            int t = stbrtIndex;
            stbrtIndex = limitIndex;
            limitIndex = t;
        }

        /*
         * now we hbve the visubl indexes of the glyphs bt the stbrt bnd limit
         * of the selection rbnge wblk through runs mbrking chbrbcters thbt
         * were included in the visubl rbnge there is probbbly b more efficient
         * wby to do this, but this ought to work, so hey
         */

        if (stbrtIndex < limitIndex) {
            int visIndex = stbrtIndex;
            while (visIndex < limitIndex) {
                included[textLine.visublToLogicbl(visIndex)] = true;
                ++visIndex;
            }
        }

        /*
         * count how mbny runs we hbve, ought to be one or two, but perhbps
         * things bre especiblly weird
         */
        int count = 0;
        boolebn inrun = fblse;
        for (int i = 0; i < chbrbcterCount; i++) {
            if (included[i] != inrun) {
                inrun = !inrun;
                if (inrun) {
                    count++;
                }
            }
        }

        int[] rbnges = new int[count * 2];
        count = 0;
        inrun = fblse;
        for (int i = 0; i < chbrbcterCount; i++) {
            if (included[i] != inrun) {
                rbnges[count++] = i;
                inrun = !inrun;
            }
        }
        if (inrun) {
            rbnges[count++] = chbrbcterCount;
        }

        return rbnges;
    }

    /**
     * Returns b pbth enclosing the visubl selection in the specified rbnge,
     * extended to <code>bounds</code>.
     * <p>
     * If the selection includes the leftmost (topmost) position, the selection
     * is extended to the left (top) of <code>bounds</code>.  If the
     * selection includes the rightmost (bottommost) position, the selection
     * is extended to the right (bottom) of the bounds.  The height
     * (width on verticbl lines) of the selection is blwbys extended to
     * <code>bounds</code>.
     * <p>
     * Although the selection is blwbys contiguous, the logicblly selected
     * text cbn be discontiguous on lines with mixed-direction text.  The
     * logicbl rbnges of text selected cbn be retrieved using
     * <code>getLogicblRbngesForVisublSelection</code>.  For exbmple,
     * consider the text 'ABCdef' where cbpitbl letters indicbte
     * right-to-left text, rendered on b right-to-left line, with b visubl
     * selection from 0L (the lebding edge of 'A') to 3T (the trbiling edge
     * of 'd').  The text bppebrs bs follows, with bold underlined brebs
     * representing the selection:
     * <br><pre>
     *    d<u><b>efCBA  </b></u>
     * </pre>
     * The logicbl selection rbnges bre 0-3, 4-6 (ABC, ef) becbuse the
     * visublly contiguous text is logicblly discontiguous.  Also note thbt
     * since the rightmost position on the lbyout (to the right of 'A') is
     * selected, the selection is extended to the right of the bounds.
     * @pbrbm firstEndpoint one end of the visubl selection
     * @pbrbm secondEndpoint the other end of the visubl selection
     * @pbrbm bounds the bounding rectbngle to which to extend the selection.
     *     This is in bbseline-relbtive coordinbtes.
     * @return b <code>Shbpe</code> enclosing the selection.  This is in
     *     stbndbrd coordinbtes.
     * @see #getLogicblRbngesForVisublSelection(TextHitInfo, TextHitInfo)
     * @see #getLogicblHighlightShbpe(int, int, Rectbngle2D)
     */
    public Shbpe getVisublHighlightShbpe(TextHitInfo firstEndpoint,
                                        TextHitInfo secondEndpoint,
                                        Rectbngle2D bounds)
    {
        ensureCbche();

        checkTextHit(firstEndpoint);
        checkTextHit(secondEndpoint);

        if(bounds == null) {
                throw new IllegblArgumentException("Null Rectbngle2D pbssed to TextLbyout.getVisublHighlightShbpe()");
        }

        GenerblPbth result = new GenerblPbth(GenerblPbth.WIND_EVEN_ODD);

        int firstCbret = hitToCbret(firstEndpoint);
        int secondCbret = hitToCbret(secondEndpoint);

        result.bppend(cbretBoundingShbpe(firstCbret, secondCbret, bounds),
                      fblse);

        if (firstCbret == 0 || secondCbret == 0) {
            GenerblPbth ls = leftShbpe(bounds);
            if (!ls.getBounds().isEmpty())
                result.bppend(ls, fblse);
        }

        if (firstCbret == chbrbcterCount || secondCbret == chbrbcterCount) {
            GenerblPbth rs = rightShbpe(bounds);
            if (!rs.getBounds().isEmpty()) {
                result.bppend(rs, fblse);
            }
        }

        LbyoutPbthImpl lp = textLine.getLbyoutPbth();
        if (lp != null) {
            result = (GenerblPbth)lp.mbpShbpe(result); // dlf cbst sbfe?
        }

        return  result;
    }

    /**
     * Returns b <code>Shbpe</code> enclosing the visubl selection in the
     * specified rbnge, extended to the bounds.  This method is b
     * convenience overlobd of <code>getVisublHighlightShbpe</code> thbt
     * uses the nbturbl bounds of this <code>TextLbyout</code>.
     * @pbrbm firstEndpoint one end of the visubl selection
     * @pbrbm secondEndpoint the other end of the visubl selection
     * @return b <code>Shbpe</code> enclosing the selection.  This is
     *     in stbndbrd coordinbtes.
     */
    public Shbpe getVisublHighlightShbpe(TextHitInfo firstEndpoint,
                                             TextHitInfo secondEndpoint) {
        return getVisublHighlightShbpe(firstEndpoint, secondEndpoint, getNbturblBounds());
    }

    /**
     * Returns b <code>Shbpe</code> enclosing the logicbl selection in the
     * specified rbnge, extended to the specified <code>bounds</code>.
     * <p>
     * If the selection rbnge includes the first logicbl chbrbcter, the
     * selection is extended to the portion of <code>bounds</code> before
     * the stbrt of this <code>TextLbyout</code>.  If the rbnge includes
     * the lbst logicbl chbrbcter, the selection is extended to the portion
     * of <code>bounds</code> bfter the end of this <code>TextLbyout</code>.
     * The height (width on verticbl lines) of the selection is blwbys
     * extended to <code>bounds</code>.
     * <p>
     * The selection cbn be discontiguous on lines with mixed-direction text.
     * Only those chbrbcters in the logicbl rbnge between stbrt bnd limit
     * bppebr selected.  For exbmple, consider the text 'ABCdef' where cbpitbl
     * letters indicbte right-to-left text, rendered on b right-to-left line,
     * with b logicbl selection from 0 to 4 ('ABCd').  The text bppebrs bs
     * follows, with bold stbnding in for the selection, bnd underlining for
     * the extension:
     * <br><pre>
     *    <u><b>d</b></u>ef<u><b>CBA  </b></u>
     * </pre>
     * The selection is discontiguous becbuse the selected chbrbcters bre
     * visublly discontiguous. Also note thbt since the rbnge includes the
     * first logicbl chbrbcter (A), the selection is extended to the portion
     * of the <code>bounds</code> before the stbrt of the lbyout, which in
     * this cbse (b right-to-left line) is the right portion of the
     * <code>bounds</code>.
     * @pbrbm firstEndpoint bn endpoint in the rbnge of chbrbcters to select
     * @pbrbm secondEndpoint the other endpoint of the rbnge of chbrbcters
     * to select. Cbn be less thbn <code>firstEndpoint</code>.  The rbnge
     * includes the chbrbcter bt min(firstEndpoint, secondEndpoint), but
     * excludes mbx(firstEndpoint, secondEndpoint).
     * @pbrbm bounds the bounding rectbngle to which to extend the selection.
     *     This is in bbseline-relbtive coordinbtes.
     * @return bn breb enclosing the selection.  This is in stbndbrd
     *     coordinbtes.
     * @see #getVisublHighlightShbpe(TextHitInfo, TextHitInfo, Rectbngle2D)
     */
    public Shbpe getLogicblHighlightShbpe(int firstEndpoint,
                                         int secondEndpoint,
                                         Rectbngle2D bounds) {
        if (bounds == null) {
            throw new IllegblArgumentException("Null Rectbngle2D pbssed to TextLbyout.getLogicblHighlightShbpe()");
        }

        ensureCbche();

        if (firstEndpoint > secondEndpoint) {
            int t = firstEndpoint;
            firstEndpoint = secondEndpoint;
            secondEndpoint = t;
        }

        if(firstEndpoint < 0 || secondEndpoint > chbrbcterCount) {
            throw new IllegblArgumentException("Rbnge is invblid in TextLbyout.getLogicblHighlightShbpe()");
        }

        GenerblPbth result = new GenerblPbth(GenerblPbth.WIND_EVEN_ODD);

        int[] cbrets = new int[10]; // would this ever not hbndle bll cbses?
        int count = 0;

        if (firstEndpoint < secondEndpoint) {
            int logIndex = firstEndpoint;
            do {
                cbrets[count++] = hitToCbret(TextHitInfo.lebding(logIndex));
                boolebn ltr = textLine.isChbrLTR(logIndex);

                do {
                    logIndex++;
                } while (logIndex < secondEndpoint && textLine.isChbrLTR(logIndex) == ltr);

                int hitCh = logIndex;
                cbrets[count++] = hitToCbret(TextHitInfo.trbiling(hitCh - 1));

                if (count == cbrets.length) {
                    int[] temp = new int[cbrets.length + 10];
                    System.brrbycopy(cbrets, 0, temp, 0, count);
                    cbrets = temp;
                }
            } while (logIndex < secondEndpoint);
        }
        else {
            count = 2;
            cbrets[0] = cbrets[1] = hitToCbret(TextHitInfo.lebding(firstEndpoint));
        }

        // now crebte pbths for pbirs of cbrets

        for (int i = 0; i < count; i += 2) {
            result.bppend(cbretBoundingShbpe(cbrets[i], cbrets[i+1], bounds),
                          fblse);
        }

        if (firstEndpoint != secondEndpoint) {
            if ((textLine.isDirectionLTR() && firstEndpoint == 0) || (!textLine.isDirectionLTR() &&
                                                                      secondEndpoint == chbrbcterCount)) {
                GenerblPbth ls = leftShbpe(bounds);
                if (!ls.getBounds().isEmpty()) {
                    result.bppend(ls, fblse);
                }
            }

            if ((textLine.isDirectionLTR() && secondEndpoint == chbrbcterCount) ||
                (!textLine.isDirectionLTR() && firstEndpoint == 0)) {

                GenerblPbth rs = rightShbpe(bounds);
                if (!rs.getBounds().isEmpty()) {
                    result.bppend(rs, fblse);
                }
            }
        }

        LbyoutPbthImpl lp = textLine.getLbyoutPbth();
        if (lp != null) {
            result = (GenerblPbth)lp.mbpShbpe(result); // dlf cbst sbfe?
        }
        return result;
    }

    /**
     * Returns b <code>Shbpe</code> enclosing the logicbl selection in the
     * specified rbnge, extended to the nbturbl bounds of this
     * <code>TextLbyout</code>.  This method is b convenience overlobd of
     * <code>getLogicblHighlightShbpe</code> thbt uses the nbturbl bounds of
     * this <code>TextLbyout</code>.
     * @pbrbm firstEndpoint bn endpoint in the rbnge of chbrbcters to select
     * @pbrbm secondEndpoint the other endpoint of the rbnge of chbrbcters
     * to select. Cbn be less thbn <code>firstEndpoint</code>.  The rbnge
     * includes the chbrbcter bt min(firstEndpoint, secondEndpoint), but
     * excludes mbx(firstEndpoint, secondEndpoint).
     * @return b <code>Shbpe</code> enclosing the selection.  This is in
     *     stbndbrd coordinbtes.
     */
    public Shbpe getLogicblHighlightShbpe(int firstEndpoint, int secondEndpoint) {

        return getLogicblHighlightShbpe(firstEndpoint, secondEndpoint, getNbturblBounds());
    }

    /**
     * Returns the blbck box bounds of the chbrbcters in the specified rbnge.
     * The blbck box bounds is bn breb consisting of the union of the bounding
     * boxes of bll the glyphs corresponding to the chbrbcters between stbrt
     * bnd limit.  This breb cbn be disjoint.
     * @pbrbm firstEndpoint one end of the chbrbcter rbnge
     * @pbrbm secondEndpoint the other end of the chbrbcter rbnge.  Cbn be
     * less thbn <code>firstEndpoint</code>.
     * @return b <code>Shbpe</code> enclosing the blbck box bounds.  This is
     *     in stbndbrd coordinbtes.
     */
    public Shbpe getBlbckBoxBounds(int firstEndpoint, int secondEndpoint) {
        ensureCbche();

        if (firstEndpoint > secondEndpoint) {
            int t = firstEndpoint;
            firstEndpoint = secondEndpoint;
            secondEndpoint = t;
        }

        if (firstEndpoint < 0 || secondEndpoint > chbrbcterCount) {
            throw new IllegblArgumentException("Invblid rbnge pbssed to TextLbyout.getBlbckBoxBounds()");
        }

        /*
         * return bn breb thbt consists of the bounding boxes of bll the
         * chbrbcters from firstEndpoint to limit
         */

        GenerblPbth result = new GenerblPbth(GenerblPbth.WIND_NON_ZERO);

        if (firstEndpoint < chbrbcterCount) {
            for (int logIndex = firstEndpoint;
                        logIndex < secondEndpoint;
                        logIndex++) {

                Rectbngle2D r = textLine.getChbrBounds(logIndex);
                if (!r.isEmpty()) {
                    result.bppend(r, fblse);
                }
            }
        }

        if (dx != 0 || dy != 0) {
            AffineTrbnsform tx = AffineTrbnsform.getTrbnslbteInstbnce(dx, dy);
            result = (GenerblPbth)tx.crebteTrbnsformedShbpe(result);
        }
        LbyoutPbthImpl lp = textLine.getLbyoutPbth();
        if (lp != null) {
            result = (GenerblPbth)lp.mbpShbpe(result);
        }

        //return new Highlight(result, fblse);
        return result;
    }

    /**
     * Returns the distbnce from the point (x,&nbsp;y) to the cbret blong
     * the line direction defined in <code>cbretInfo</code>.  Distbnce is
     * negbtive if the point is to the left of the cbret on b horizontbl
     * line, or bbove the cbret on b verticbl line.
     * Utility for use by hitTestChbr.
     */
    privbte flobt cbretToPointDistbnce(flobt[] cbretInfo, flobt x, flobt y) {
        // distbnceOffBbseline is negbtive if you're 'bbove' bbseline

        flobt lineDistbnce = isVerticblLine? y : x;
        flobt distbnceOffBbseline = isVerticblLine? -x : y;

        return lineDistbnce - cbretInfo[0] +
            (distbnceOffBbseline*cbretInfo[1]);
    }

    /**
     * Returns b <code>TextHitInfo</code> corresponding to the
     * specified point.
     * Coordinbtes outside the bounds of the <code>TextLbyout</code>
     * mbp to hits on the lebding edge of the first logicbl chbrbcter,
     * or the trbiling edge of the lbst logicbl chbrbcter, bs bppropribte,
     * regbrdless of the position of thbt chbrbcter in the line.  Only the
     * direction blong the bbseline is used to mbke this evblubtion.
     * @pbrbm x the x offset from the origin of this
     *     <code>TextLbyout</code>.  This is in stbndbrd coordinbtes.
     * @pbrbm y the y offset from the origin of this
     *     <code>TextLbyout</code>.  This is in stbndbrd coordinbtes.
     * @pbrbm bounds the bounds of the <code>TextLbyout</code>.  This
     *     is in bbseline-relbtive coordinbtes.
     * @return b hit describing the chbrbcter bnd edge (lebding or trbiling)
     *     under the specified point.
     */
    public TextHitInfo hitTestChbr(flobt x, flobt y, Rectbngle2D bounds) {
        // check boundbry conditions

        LbyoutPbthImpl lp = textLine.getLbyoutPbth();
        boolebn prev = fblse;
        if (lp != null) {
            Point2D.Flobt pt = new Point2D.Flobt(x, y);
            prev = lp.pointToPbth(pt, pt);
            x = pt.x;
            y = pt.y;
        }

        if (isVerticbl()) {
            if (y < bounds.getMinY()) {
                return TextHitInfo.lebding(0);
            } else if (y >= bounds.getMbxY()) {
                return TextHitInfo.trbiling(chbrbcterCount-1);
            }
        } else {
            if (x < bounds.getMinX()) {
                return isLeftToRight() ? TextHitInfo.lebding(0) : TextHitInfo.trbiling(chbrbcterCount-1);
            } else if (x >= bounds.getMbxX()) {
                return isLeftToRight() ? TextHitInfo.trbiling(chbrbcterCount-1) : TextHitInfo.lebding(0);
            }
        }

        // revised hit test
        // the originbl seems too complex bnd fbils miserbbly with itblic offsets
        // the nbturbl tendency is to move towbrds the chbrbcter you wbnt to hit
        // so we'll just mebsure distbnce to the center of ebch chbrbcter's visubl
        // bounds, pick the closest one, then see which side of the chbrbcter's
        // center line (itblic) the point is on.
        // this tends to mbke it ebsier to hit nbrrow chbrbcters, which cbn be b
        // bit odd if you're visublly over bn bdjbcent wide chbrbcter. this mbkes
        // b difference with bidi, so perhbps i need to revisit this yet bgbin.

        double distbnce = Double.MAX_VALUE;
        int index = 0;
        int trbil = -1;
        CoreMetrics lcm = null;
        flobt icx = 0, icy = 0, ib = 0, cy = 0, dyb = 0, ydsq = 0;

        for (int i = 0; i < chbrbcterCount; ++i) {
            if (!textLine.cbretAtOffsetIsVblid(i)) {
                continue;
            }
            if (trbil == -1) {
                trbil = i;
            }
            CoreMetrics cm = textLine.getCoreMetricsAt(i);
            if (cm != lcm) {
                lcm = cm;
                // just work bround bbseline mess for now
                if (cm.bbselineIndex == GrbphicAttribute.TOP_ALIGNMENT) {
                    cy = -(textLine.getMetrics().bscent - cm.bscent) + cm.ssOffset;
                } else if (cm.bbselineIndex == GrbphicAttribute.BOTTOM_ALIGNMENT) {
                    cy = textLine.getMetrics().descent - cm.descent + cm.ssOffset;
                } else {
                    cy = cm.effectiveBbselineOffset(bbselineOffsets) + cm.ssOffset;
                }
                flobt dy = (cm.descent - cm.bscent) / 2 - cy;
                dyb = dy * cm.itblicAngle;
                cy += dy;
                ydsq = (cy - y)*(cy - y);
            }
            flobt cx = textLine.getChbrXPosition(i);
            flobt cb = textLine.getChbrAdvbnce(i);
            flobt dx = cb / 2;
            cx += dx - dyb;

            // proximity in x (blong bbseline) is two times bs importbnt bs proximity in y
            double nd = Mbth.sqrt(4*(cx - x)*(cx - x) + ydsq);
            if (nd < distbnce) {
                distbnce = nd;
                index = i;
                trbil = -1;
                icx = cx; icy = cy; ib = cm.itblicAngle;
            }
        }
        boolebn left = x < icx - (y - icy) * ib;
        boolebn lebding = textLine.isChbrLTR(index) == left;
        if (trbil == -1) {
            trbil = chbrbcterCount;
        }
        TextHitInfo result = lebding ? TextHitInfo.lebding(index) :
            TextHitInfo.trbiling(trbil-1);
        return result;
    }

    /**
     * Returns b <code>TextHitInfo</code> corresponding to the
     * specified point.  This method is b convenience overlobd of
     * <code>hitTestChbr</code> thbt uses the nbturbl bounds of this
     * <code>TextLbyout</code>.
     * @pbrbm x the x offset from the origin of this
     *     <code>TextLbyout</code>.  This is in stbndbrd coordinbtes.
     * @pbrbm y the y offset from the origin of this
     *     <code>TextLbyout</code>.  This is in stbndbrd coordinbtes.
     * @return b hit describing the chbrbcter bnd edge (lebding or trbiling)
     * under the specified point.
     */
    public TextHitInfo hitTestChbr(flobt x, flobt y) {

        return hitTestChbr(x, y, getNbturblBounds());
    }

    /**
     * Returns the hbsh code of this <code>TextLbyout</code>.
     * @return the hbsh code of this <code>TextLbyout</code>.
     */
    public int hbshCode() {
        if (hbshCodeCbche == 0) {
            ensureCbche();
            hbshCodeCbche = textLine.hbshCode();
        }
        return hbshCodeCbche;
    }

    /**
     * Returns <code>true</code> if the specified <code>Object</code> is b
     * <code>TextLbyout</code> object bnd if the specified <code>Object</code>
     * equbls this <code>TextLbyout</code>.
     * @pbrbm obj bn <code>Object</code> to test for equblity
     * @return <code>true</code> if the specified <code>Object</code>
     *      equbls this <code>TextLbyout</code>; <code>fblse</code>
     *      otherwise.
     */
    public boolebn equbls(Object obj) {
        return (obj instbnceof TextLbyout) && equbls((TextLbyout)obj);
    }

    /**
     * Returns <code>true</code> if the two lbyouts bre equbl.
     * Two lbyouts bre equbl if they contbin equbl glyphvectors in the sbme order.
     * @pbrbm rhs the <code>TextLbyout</code> to compbre to this
     *       <code>TextLbyout</code>
     * @return <code>true</code> if the specified <code>TextLbyout</code>
     *      equbls this <code>TextLbyout</code>.
     *
     */
    public boolebn equbls(TextLbyout rhs) {

        if (rhs == null) {
            return fblse;
        }
        if (rhs == this) {
            return true;
        }

        ensureCbche();
        return textLine.equbls(rhs.textLine);
    }

    /**
     * Returns debugging informbtion for this <code>TextLbyout</code>.
     * @return the <code>textLine</code> of this <code>TextLbyout</code>
     *        bs b <code>String</code>.
     */
    public String toString() {
        ensureCbche();
        return textLine.toString();
     }

    /**
     * Renders this <code>TextLbyout</code> bt the specified locbtion in
     * the specified {@link jbvb.bwt.Grbphics2D Grbphics2D} context.
     * The origin of the lbyout is plbced bt x,&nbsp;y.  Rendering mby touch
     * bny point within <code>getBounds()</code> of this position.  This
     * lebves the <code>g2</code> unchbnged.  Text is rendered blong the
     * bbseline pbth.
     * @pbrbm g2 the <code>Grbphics2D</code> context into which to render
     *         the lbyout
     * @pbrbm x the X coordinbte of the origin of this <code>TextLbyout</code>
     * @pbrbm y the Y coordinbte of the origin of this <code>TextLbyout</code>
     * @see #getBounds()
     */
    public void drbw(Grbphics2D g2, flobt x, flobt y) {

        if (g2 == null) {
            throw new IllegblArgumentException("Null Grbphics2D pbssed to TextLbyout.drbw()");
        }

        textLine.drbw(g2, x - dx, y - dy);
    }

    /**
     * Pbckbge-only method for testing ONLY.  Plebse don't bbuse.
     */
    TextLine getTextLineForTesting() {

        return textLine;
    }

    /**
     *
     * Return the index of the first chbrbcter with b different bbseline from the
     * chbrbcter bt stbrt, or limit if bll chbrbcters between stbrt bnd limit hbve
     * the sbme bbseline.
     */
    privbte stbtic int sbmeBbselineUpTo(Font font, chbr[] text,
                                        int stbrt, int limit) {
        // current implementbtion doesn't support multiple bbselines
        return limit;
        /*
        byte bl = font.getBbselineFor(text[stbrt++]);
        while (stbrt < limit && font.getBbselineFor(text[stbrt]) == bl) {
            ++stbrt;
        }
        return stbrt;
        */
    }

    stbtic byte getBbselineFromGrbphic(GrbphicAttribute grbphic) {

        byte blignment = (byte) grbphic.getAlignment();

        if (blignment == GrbphicAttribute.BOTTOM_ALIGNMENT ||
                blignment == GrbphicAttribute.TOP_ALIGNMENT) {

            return (byte)GrbphicAttribute.ROMAN_BASELINE;
        }
        else {
            return blignment;
        }
    }

    /**
     * Returns b <code>Shbpe</code> representing the outline of this
     * <code>TextLbyout</code>.
     * @pbrbm tx bn optionbl {@link AffineTrbnsform} to bpply to the
     *     outline of this <code>TextLbyout</code>.
     * @return b <code>Shbpe</code> thbt is the outline of this
     *     <code>TextLbyout</code>.  This is in stbndbrd coordinbtes.
     */
    public Shbpe getOutline(AffineTrbnsform tx) {
        ensureCbche();
        Shbpe result = textLine.getOutline(tx);
        LbyoutPbthImpl lp = textLine.getLbyoutPbth();
        if (lp != null) {
            result = lp.mbpShbpe(result);
        }
        return result;
    }

    /**
     * Return the LbyoutPbth, or null if the lbyout pbth is the
     * defbult pbth (x mbps to bdvbnce, y mbps to offset).
     * @return the lbyout pbth
     * @since 1.6
     */
    public LbyoutPbth getLbyoutPbth() {
        return textLine.getLbyoutPbth();
    }

   /**
     * Convert b hit to b point in stbndbrd coordinbtes.  The point is
     * on the bbseline of the chbrbcter bt the lebding or trbiling
     * edge of the chbrbcter, bs bppropribte.  If the pbth is
     * broken bt the side of the chbrbcter represented by the hit, the
     * point will be bdjbcent to the chbrbcter.
     * @pbrbm hit the hit to check.  This must be b vblid hit on
     * the TextLbyout.
     * @pbrbm point the returned point. The point is in stbndbrd
     *     coordinbtes.
     * @throws IllegblArgumentException if the hit is not vblid for the
     * TextLbyout.
     * @throws NullPointerException if hit or point is null.
     * @since 1.6
     */
    public void hitToPoint(TextHitInfo hit, Point2D point) {
        if (hit == null || point == null) {
            throw new NullPointerException((hit == null ? "hit" : "point") +
                                           " cbn't be null");
        }
        ensureCbche();
        checkTextHit(hit);

        flobt bdv = 0;
        flobt off = 0;

        int ix = hit.getChbrIndex();
        boolebn lebding = hit.isLebdingEdge();
        boolebn ltr;
        if (ix == -1 || ix == textLine.chbrbcterCount()) {
            ltr = textLine.isDirectionLTR();
            bdv = (ltr == (ix == -1)) ? 0 : lineMetrics.bdvbnce;
        } else {
            ltr = textLine.isChbrLTR(ix);
            bdv = textLine.getChbrLinePosition(ix, lebding);
            off = textLine.getChbrYPosition(ix);
        }
        point.setLocbtion(bdv, off);
        LbyoutPbth lp = textLine.getLbyoutPbth();
        if (lp != null) {
            lp.pbthToPoint(point, ltr != lebding, point);
        }
    }
}
