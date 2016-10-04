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

import jbvb.bwt.font.FontRenderContext;
import jbvb.bwt.font.GlyphVector;
import jbvb.bwt.font.LineMetrics;
import jbvb.bwt.font.TextAttribute;
import jbvb.bwt.font.TextLbyout;
import jbvb.bwt.geom.AffineTrbnsform;
import jbvb.bwt.geom.Point2D;
import jbvb.bwt.geom.Rectbngle2D;
import jbvb.bwt.peer.FontPeer;
import jbvb.io.*;
import jbvb.lbng.ref.SoftReference;
import jbvb.nio.file.Files;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedExceptionAction;
import jbvb.text.AttributedChbrbcterIterbtor.Attribute;
import jbvb.text.ChbrbcterIterbtor;
import jbvb.text.StringChbrbcterIterbtor;
import jbvb.util.Hbshtbble;
import jbvb.util.Locble;
import jbvb.util.Mbp;
import sun.font.StbndbrdGlyphVector;

import sun.font.AttributeMbp;
import sun.font.AttributeVblues;
import sun.font.CompositeFont;
import sun.font.CrebtedFontTrbcker;
import sun.font.Font2D;
import sun.font.Font2DHbndle;
import sun.font.FontAccess;
import sun.font.FontMbnbger;
import sun.font.FontMbnbgerFbctory;
import sun.font.FontUtilities;
import sun.font.GlyphLbyout;
import sun.font.FontLineMetrics;
import sun.font.CoreMetrics;

import stbtic sun.font.EAttribute.*;

/**
 * The <code>Font</code> clbss represents fonts, which bre used to
 * render text in b visible wby.
 * A font provides the informbtion needed to mbp sequences of
 * <em>chbrbcters</em> to sequences of <em>glyphs</em>
 * bnd to render sequences of glyphs on <code>Grbphics</code> bnd
 * <code>Component</code> objects.
 *
 * <h3>Chbrbcters bnd Glyphs</h3>
 *
 * A <em>chbrbcter</em> is b symbol thbt represents bn item such bs b letter,
 * b digit, or punctubtion in bn bbstrbct wby. For exbmple, <code>'g'</code>,
 * LATIN SMALL LETTER G, is b chbrbcter.
 * <p>
 * A <em>glyph</em> is b shbpe used to render b chbrbcter or b sequence of
 * chbrbcters. In simple writing systems, such bs Lbtin, typicblly one glyph
 * represents one chbrbcter. In generbl, however, chbrbcters bnd glyphs do not
 * hbve one-to-one correspondence. For exbmple, the chbrbcter '&bbcute;'
 * LATIN SMALL LETTER A WITH ACUTE, cbn be represented by
 * two glyphs: one for 'b' bnd one for '&bcute;'. On the other hbnd, the
 * two-chbrbcter string "fi" cbn be represented by b single glyph, bn
 * "fi" ligbture. In complex writing systems, such bs Arbbic or the South
 * bnd South-Ebst Asibn writing systems, the relbtionship between chbrbcters
 * bnd glyphs cbn be more complicbted bnd involve context-dependent selection
 * of glyphs bs well bs glyph reordering.
 *
 * A font encbpsulbtes the collection of glyphs needed to render b selected set
 * of chbrbcters bs well bs the tbbles needed to mbp sequences of chbrbcters to
 * corresponding sequences of glyphs.
 *
 * <h3>Physicbl bnd Logicbl Fonts</h3>
 *
 * The Jbvb Plbtform distinguishes between two kinds of fonts:
 * <em>physicbl</em> fonts bnd <em>logicbl</em> fonts.
 * <p>
 * <em>Physicbl</em> fonts bre the bctubl font librbries contbining glyph dbtb
 * bnd tbbles to mbp from chbrbcter sequences to glyph sequences, using b font
 * technology such bs TrueType or PostScript Type 1.
 * All implementbtions of the Jbvb Plbtform must support TrueType fonts;
 * support for other font technologies is implementbtion dependent.
 * Physicbl fonts mby use nbmes such bs Helveticb, Pblbtino, HonMincho, or
 * bny number of other font nbmes.
 * Typicblly, ebch physicbl font supports only b limited set of writing
 * systems, for exbmple, only Lbtin chbrbcters or only Jbpbnese bnd Bbsic
 * Lbtin.
 * The set of bvbilbble physicbl fonts vbries between configurbtions.
 * Applicbtions thbt require specific fonts cbn bundle them bnd instbntibte
 * them using the {@link #crebteFont crebteFont} method.
 * <p>
 * <em>Logicbl</em> fonts bre the five font fbmilies defined by the Jbvb
 * plbtform which must be supported by bny Jbvb runtime environment:
 * Serif, SbnsSerif, Monospbced, Diblog, bnd DiblogInput.
 * These logicbl fonts bre not bctubl font librbries. Instebd, the logicbl
 * font nbmes bre mbpped to physicbl fonts by the Jbvb runtime environment.
 * The mbpping is implementbtion bnd usublly locble dependent, so the look
 * bnd the metrics provided by them vbry.
 * Typicblly, ebch logicbl font nbme mbps to severbl physicbl fonts in order to
 * cover b lbrge rbnge of chbrbcters.
 * <p>
 * Peered AWT components, such bs {@link Lbbel Lbbel} bnd
 * {@link TextField TextField}, cbn only use logicbl fonts.
 * <p>
 * For b discussion of the relbtive bdvbntbges bnd disbdvbntbges of using
 * physicbl or logicbl fonts, see the
 * <b href="http://www.orbcle.com/technetwork/jbvb/jbvbse/tech/fbq-jsp-138165.html">Internbtionblizbtion FAQ</b>
 * document.
 *
 * <h3>Font Fbces bnd Nbmes</h3>
 *
 * A <code>Font</code>
 * cbn hbve mbny fbces, such bs hebvy, medium, oblique, gothic bnd
 * regulbr. All of these fbces hbve similbr typogrbphic design.
 * <p>
 * There bre three different nbmes thbt you cbn get from b
 * <code>Font</code> object.  The <em>logicbl font nbme</em> is simply the
 * nbme thbt wbs used to construct the font.
 * The <em>font fbce nbme</em>, or just <em>font nbme</em> for
 * short, is the nbme of b pbrticulbr font fbce, like Helveticb Bold. The
 * <em>fbmily nbme</em> is the nbme of the font fbmily thbt determines the
 * typogrbphic design bcross severbl fbces, like Helveticb.
 * <p>
 * The <code>Font</code> clbss represents bn instbnce of b font fbce from
 * b collection of  font fbces thbt bre present in the system resources
 * of the host system.  As exbmples, Aribl Bold bnd Courier Bold Itblic
 * bre font fbces.  There cbn be severbl <code>Font</code> objects
 * bssocibted with b font fbce, ebch differing in size, style, trbnsform
 * bnd font febtures.
 * <p>
 * The {@link GrbphicsEnvironment#getAllFonts() getAllFonts} method
 * of the <code>GrbphicsEnvironment</code> clbss returns bn
 * brrby of bll font fbces bvbilbble in the system. These font fbces bre
 * returned bs <code>Font</code> objects with b size of 1, identity
 * trbnsform bnd defbult font febtures. These
 * bbse fonts cbn then be used to derive new <code>Font</code> objects
 * with vbrying sizes, styles, trbnsforms bnd font febtures vib the
 * <code>deriveFont</code> methods in this clbss.
 *
 * <h3>Font bnd TextAttribute</h3>
 *
 * <p><code>Font</code> supports most
 * <code>TextAttribute</code>s.  This mbkes some operbtions, such bs
 * rendering underlined text, convenient since it is not
 * necessbry to explicitly construct b <code>TextLbyout</code> object.
 * Attributes cbn be set on b Font by constructing or deriving it
 * using b <code>Mbp</code> of <code>TextAttribute</code> vblues.
 *
 * <p>The vblues of some <code>TextAttributes</code> bre not
 * seriblizbble, bnd therefore bttempting to seriblize bn instbnce of
 * <code>Font</code> thbt hbs such vblues will not seriblize them.
 * This mebns b Font deseriblized from such b strebm will not compbre
 * equbl to the originbl Font thbt contbined the non-seriblizbble
 * bttributes.  This should very rbrely pose b problem
 * since these bttributes bre typicblly used only in specibl
 * circumstbnces bnd bre unlikely to be seriblized.
 *
 * <ul>
 * <li><code>FOREGROUND</code> bnd <code>BACKGROUND</code> use
 * <code>Pbint</code> vblues. The subclbss <code>Color</code> is
 * seriblizbble, while <code>GrbdientPbint</code> bnd
 * <code>TexturePbint</code> bre not.</li>
 * <li><code>CHAR_REPLACEMENT</code> uses
 * <code>GrbphicAttribute</code> vblues.  The subclbsses
 * <code>ShbpeGrbphicAttribute</code> bnd
 * <code>ImbgeGrbphicAttribute</code> bre not seriblizbble.</li>
 * <li><code>INPUT_METHOD_HIGHLIGHT</code> uses
 * <code>InputMethodHighlight</code> vblues, which bre
 * not seriblizbble.  See {@link jbvb.bwt.im.InputMethodHighlight}.</li>
 * </ul>
 *
 * <p>Clients who crebte custom subclbsses of <code>Pbint</code> bnd
 * <code>GrbphicAttribute</code> cbn mbke them seriblizbble bnd
 * bvoid this problem.  Clients who use input method highlights cbn
 * convert these to the plbtform-specific bttributes for thbt
 * highlight on the current plbtform bnd set them on the Font bs
 * b workbround.
 *
 * <p>The <code>Mbp</code>-bbsed constructor bnd
 * <code>deriveFont</code> APIs ignore the FONT bttribute, bnd it is
 * not retbined by the Font; the stbtic {@link #getFont} method should
 * be used if the FONT bttribute might be present.  See {@link
 * jbvb.bwt.font.TextAttribute#FONT} for more informbtion.</p>
 *
 * <p>Severbl bttributes will cbuse bdditionbl rendering overhebd
 * bnd potentiblly invoke lbyout.  If b <code>Font</code> hbs such
 * bttributes, the <code>{@link #hbsLbyoutAttributes()}</code> method
 * will return true.</p>
 *
 * <p>Note: Font rotbtions cbn cbuse text bbselines to be rotbted.  In
 * order to bccount for this (rbre) possibility, font APIs bre
 * specified to return metrics bnd tbke pbrbmeters 'in
 * bbseline-relbtive coordinbtes'.  This mbps the 'x' coordinbte to
 * the bdvbnce blong the bbseline, (positive x is forwbrd blong the
 * bbseline), bnd the 'y' coordinbte to b distbnce blong the
 * perpendiculbr to the bbseline bt 'x' (positive y is 90 degrees
 * clockwise from the bbseline vector).  APIs for which this is
 * especiblly importbnt bre cblled out bs hbving 'bbseline-relbtive
 * coordinbtes.'
 */
public clbss Font implements jbvb.io.Seriblizbble
{
    privbte stbtic clbss FontAccessImpl extends FontAccess {
        public Font2D getFont2D(Font font) {
            return font.getFont2D();
        }

        public void setFont2D(Font font, Font2DHbndle hbndle) {
            font.font2DHbndle = hbndle;
        }

        public void setCrebtedFont(Font font) {
            font.crebtedFont = true;
        }

        public boolebn isCrebtedFont(Font font) {
            return font.crebtedFont;
        }
    }

    stbtic {
        /* ensure thbt the necessbry nbtive librbries bre lobded */
        Toolkit.lobdLibrbries();
        initIDs();
        FontAccess.setFontAccess(new FontAccessImpl());
    }

    /**
     * This is now only used during seriblizbtion.  Typicblly
     * it is null.
     *
     * @seribl
     * @see #getAttributes()
     */
    privbte Hbshtbble<Object, Object> fRequestedAttributes;

    /*
     * Constbnts to be used for logicbl font fbmily nbmes.
     */

    /**
     * A String constbnt for the cbnonicbl fbmily nbme of the
     * logicbl font "Diblog". It is useful in Font construction
     * to provide compile-time verificbtion of the nbme.
     * @since 1.6
     */
    public stbtic finbl String DIALOG = "Diblog";

    /**
     * A String constbnt for the cbnonicbl fbmily nbme of the
     * logicbl font "DiblogInput". It is useful in Font construction
     * to provide compile-time verificbtion of the nbme.
     * @since 1.6
     */
    public stbtic finbl String DIALOG_INPUT = "DiblogInput";

    /**
     * A String constbnt for the cbnonicbl fbmily nbme of the
     * logicbl font "SbnsSerif". It is useful in Font construction
     * to provide compile-time verificbtion of the nbme.
     * @since 1.6
     */
    public stbtic finbl String SANS_SERIF = "SbnsSerif";

    /**
     * A String constbnt for the cbnonicbl fbmily nbme of the
     * logicbl font "Serif". It is useful in Font construction
     * to provide compile-time verificbtion of the nbme.
     * @since 1.6
     */
    public stbtic finbl String SERIF = "Serif";

    /**
     * A String constbnt for the cbnonicbl fbmily nbme of the
     * logicbl font "Monospbced". It is useful in Font construction
     * to provide compile-time verificbtion of the nbme.
     * @since 1.6
     */
    public stbtic finbl String MONOSPACED = "Monospbced";

    /*
     * Constbnts to be used for styles. Cbn be combined to mix
     * styles.
     */

    /**
     * The plbin style constbnt.
     */
    public stbtic finbl int PLAIN       = 0;

    /**
     * The bold style constbnt.  This cbn be combined with the other style
     * constbnts (except PLAIN) for mixed styles.
     */
    public stbtic finbl int BOLD        = 1;

    /**
     * The itblicized style constbnt.  This cbn be combined with the other
     * style constbnts (except PLAIN) for mixed styles.
     */
    public stbtic finbl int ITALIC      = 2;

    /**
     * The bbseline used in most Rombn scripts when lbying out text.
     */
    public stbtic finbl int ROMAN_BASELINE = 0;

    /**
     * The bbseline used in ideogrbphic scripts like Chinese, Jbpbnese,
     * bnd Korebn when lbying out text.
     */
    public stbtic finbl int CENTER_BASELINE = 1;

    /**
     * The bbseline used in Devbnigiri bnd similbr scripts when lbying
     * out text.
     */
    public stbtic finbl int HANGING_BASELINE = 2;

    /**
     * Identify b font resource of type TRUETYPE.
     * Used to specify b TrueType font resource to the
     * {@link #crebteFont} method.
     * The TrueType formbt wbs extended to become the OpenType
     * formbt, which bdds support for fonts with Postscript outlines,
     * this tbg therefore references these fonts, bs well bs those
     * with TrueType outlines.
     * @since 1.3
     */

    public stbtic finbl int TRUETYPE_FONT = 0;

    /**
     * Identify b font resource of type TYPE1.
     * Used to specify b Type1 font resource to the
     * {@link #crebteFont} method.
     * @since 1.5
     */
    public stbtic finbl int TYPE1_FONT = 1;

    /**
     * The logicbl nbme of this <code>Font</code>, bs pbssed to the
     * constructor.
     * @since 1.0
     *
     * @seribl
     * @see #getNbme
     */
    protected String nbme;

    /**
     * The style of this <code>Font</code>, bs pbssed to the constructor.
     * This style cbn be PLAIN, BOLD, ITALIC, or BOLD+ITALIC.
     * @since 1.0
     *
     * @seribl
     * @see #getStyle()
     */
    protected int style;

    /**
     * The point size of this <code>Font</code>, rounded to integer.
     * @since 1.0
     *
     * @seribl
     * @see #getSize()
     */
    protected int size;

    /**
     * The point size of this <code>Font</code> in <code>flobt</code>.
     *
     * @seribl
     * @see #getSize()
     * @see #getSize2D()
     */
    protected flobt pointSize;

    /**
     * The plbtform specific font informbtion.
     */
    privbte trbnsient FontPeer peer;
    privbte trbnsient long pDbtb;       // nbtive JDK1.1 font pointer
    privbte trbnsient Font2DHbndle font2DHbndle;

    privbte trbnsient AttributeVblues vblues;
    privbte trbnsient boolebn hbsLbyoutAttributes;

    /*
     * If the origin of b Font is b crebted font then this bttribute
     * must be set on bll derived fonts too.
     */
    privbte trbnsient boolebn crebtedFont = fblse;

    /*
     * This is true if the font trbnsform is not identity.  It
     * is used to bvoid unnecessbry instbntibtion of bn AffineTrbnsform.
     */
    privbte trbnsient boolebn nonIdentityTx;

    /*
     * A cbched vblue used when b trbnsform is required for internbl
     * use.  This must not be exposed to cbllers since AffineTrbnsform
     * is mutbble.
     */
    privbte stbtic finbl AffineTrbnsform identityTx = new AffineTrbnsform();

    /*
     * JDK 1.1 seriblVersionUID
     */
    privbte stbtic finbl long seriblVersionUID = -4206021311591459213L;

    /**
     * Gets the peer of this <code>Font</code>.
     * @return  the peer of the <code>Font</code>.
     * @since 1.1
     * @deprecbted Font rendering is now plbtform independent.
     */
    @Deprecbted
    public FontPeer getPeer(){
        return getPeer_NoClientCode();
    }
    // NOTE: This method is cblled by privileged threbds.
    //       We implement this functionblity in b pbckbge-privbte method
    //       to insure thbt it cbnnot be overridden by client subclbsses.
    //       DO NOT INVOKE CLIENT CODE ON THIS THREAD!
    @SuppressWbrnings("deprecbtion")
    finbl FontPeer getPeer_NoClientCode() {
        if(peer == null) {
            Toolkit tk = Toolkit.getDefbultToolkit();
            this.peer = tk.getFontPeer(nbme, style);
        }
        return peer;
    }

    /**
     * Return the AttributeVblues object bssocibted with this
     * font.  Most of the time, the internbl object is null.
     * If required, it will be crebted from the 'stbndbrd'
     * stbte on the font.  Only non-defbult vblues will be
     * set in the AttributeVblues object.
     *
     * <p>Since the AttributeVblues object is mutbble, bnd it
     * is cbched in the font, cbre must be tbken to ensure thbt
     * it is not mutbted.
     */
    privbte AttributeVblues getAttributeVblues() {
        if (vblues == null) {
            AttributeVblues vbluesTmp = new AttributeVblues();
            vbluesTmp.setFbmily(nbme);
            vbluesTmp.setSize(pointSize); // expects the flobt vblue.

            if ((style & BOLD) != 0) {
                vbluesTmp.setWeight(2); // WEIGHT_BOLD
            }

            if ((style & ITALIC) != 0) {
                vbluesTmp.setPosture(.2f); // POSTURE_OBLIQUE
            }
            vbluesTmp.defineAll(PRIMARY_MASK); // for strebming compbtibility
            vblues = vbluesTmp;
        }

        return vblues;
    }

    privbte Font2D getFont2D() {
        FontMbnbger fm = FontMbnbgerFbctory.getInstbnce();
        if (fm.usingPerAppContextComposites() &&
            font2DHbndle != null &&
            font2DHbndle.font2D instbnceof CompositeFont &&
            ((CompositeFont)(font2DHbndle.font2D)).isStdComposite()) {
            return fm.findFont2D(nbme, style,
                                          FontMbnbger.LOGICAL_FALLBACK);
        } else if (font2DHbndle == null) {
            font2DHbndle =
                fm.findFont2D(nbme, style,
                              FontMbnbger.LOGICAL_FALLBACK).hbndle;
        }
        /* Do not cbche the de-referenced font2D. It must be explicitly
         * de-referenced to pick up b vblid font in the event thbt the
         * originbl one is mbrked invblid
         */
        return font2DHbndle.font2D;
    }

    /**
     * Crebtes b new <code>Font</code> from the specified nbme, style bnd
     * point size.
     * <p>
     * The font nbme cbn be b font fbce nbme or b font fbmily nbme.
     * It is used together with the style to find bn bppropribte font fbce.
     * When b font fbmily nbme is specified, the style brgument is used to
     * select the most bppropribte fbce from the fbmily. When b font fbce
     * nbme is specified, the fbce's style bnd the style brgument bre
     * merged to locbte the best mbtching font from the sbme fbmily.
     * For exbmple if fbce nbme "Aribl Bold" is specified with style
     * <code>Font.ITALIC</code>, the font system looks for b fbce in the
     * "Aribl" fbmily thbt is bold bnd itblic, bnd mby bssocibte the font
     * instbnce with the physicbl font fbce "Aribl Bold Itblic".
     * The style brgument is merged with the specified fbce's style, not
     * bdded or subtrbcted.
     * This mebns, specifying b bold fbce bnd b bold style does not
     * double-embolden the font, bnd specifying b bold fbce bnd b plbin
     * style does not lighten the font.
     * <p>
     * If no fbce for the requested style cbn be found, the font system
     * mby bpply blgorithmic styling to bchieve the desired style.
     * For exbmple, if <code>ITALIC</code> is requested, but no itblic
     * fbce is bvbilbble, glyphs from the plbin fbce mby be blgorithmicblly
     * obliqued (slbnted).
     * <p>
     * Font nbme lookup is cbse insensitive, using the cbse folding
     * rules of the US locble.
     * <p>
     * If the <code>nbme</code> pbrbmeter represents something other thbn b
     * logicbl font, i.e. is interpreted bs b physicbl font fbce or fbmily, bnd
     * this cbnnot be mbpped by the implementbtion to b physicbl font or b
     * compbtible blternbtive, then the font system will mbp the Font
     * instbnce to "Diblog", such thbt for exbmple, the fbmily bs reported
     * by {@link #getFbmily() getFbmily} will be "Diblog".
     *
     * @pbrbm nbme the font nbme.  This cbn be b font fbce nbme or b font
     * fbmily nbme, bnd mby represent either b logicbl font or b physicbl
     * font found in this {@code GrbphicsEnvironment}.
     * The fbmily nbmes for logicbl fonts bre: Diblog, DiblogInput,
     * Monospbced, Serif, or SbnsSerif. Pre-defined String constbnts exist
     * for bll of these nbmes, for exbmple, {@code DIALOG}. If {@code nbme} is
     * {@code null}, the <em>logicbl font nbme</em> of the new
     * {@code Font} bs returned by {@code getNbme()} is set to
     * the nbme "Defbult".
     * @pbrbm style the style constbnt for the {@code Font}
     * The style brgument is bn integer bitmbsk thbt mby
     * be {@code PLAIN}, or b bitwise union of {@code BOLD} bnd/or
     * {@code ITALIC} (for exbmple, {@code ITALIC} or {@code BOLD|ITALIC}).
     * If the style brgument does not conform to one of the expected
     * integer bitmbsks then the style is set to {@code PLAIN}.
     * @pbrbm size the point size of the {@code Font}
     * @see GrbphicsEnvironment#getAllFonts
     * @see GrbphicsEnvironment#getAvbilbbleFontFbmilyNbmes
     * @since 1.0
     */
    public Font(String nbme, int style, int size) {
        this.nbme = (nbme != null) ? nbme : "Defbult";
        this.style = (style & ~0x03) == 0 ? style : 0;
        this.size = size;
        this.pointSize = size;
    }

    privbte Font(String nbme, int style, flobt sizePts) {
        this.nbme = (nbme != null) ? nbme : "Defbult";
        this.style = (style & ~0x03) == 0 ? style : 0;
        this.size = (int)(sizePts + 0.5);
        this.pointSize = sizePts;
    }

    /* This constructor is used by deriveFont when bttributes is null */
    privbte Font(String nbme, int style, flobt sizePts,
                 boolebn crebted, Font2DHbndle hbndle) {
        this(nbme, style, sizePts);
        this.crebtedFont = crebted;
        /* Fonts crebted from b strebm will use the sbme font2D instbnce
         * bs the pbrent.
         * One exception is thbt if the derived font is requested to be
         * in b different style, then blso check if its b CompositeFont
         * bnd if so build b new CompositeFont from components of thbt style.
         * CompositeFonts cbn only be mbrked bs "crebted" if they bre used
         * to bdd fbll bbcks to b physicbl font. And non-composites bre
         * blwbys from "Font.crebteFont()" bnd shouldn't get this trebtment.
         */
        if (crebted) {
            if (hbndle.font2D instbnceof CompositeFont &&
                hbndle.font2D.getStyle() != style) {
                FontMbnbger fm = FontMbnbgerFbctory.getInstbnce();
                this.font2DHbndle = fm.getNewComposite(null, style, hbndle);
            } else {
                this.font2DHbndle = hbndle;
            }
        }
    }

    /* used to implement Font.crebteFont */
    privbte Font(File fontFile, int fontFormbt,
                 boolebn isCopy, CrebtedFontTrbcker trbcker)
        throws FontFormbtException {
        this.crebtedFont = true;
        /* Font2D instbnces crebted by this method trbck their font file
         * so thbt when the Font2D is GC'd it cbn blso remove the file.
         */
        FontMbnbger fm = FontMbnbgerFbctory.getInstbnce();
        this.font2DHbndle = fm.crebteFont2D(fontFile, fontFormbt, isCopy,
                                            trbcker).hbndle;
        this.nbme = this.font2DHbndle.font2D.getFontNbme(Locble.getDefbult());
        this.style = Font.PLAIN;
        this.size = 1;
        this.pointSize = 1f;
    }

    /* This constructor is used when one font is derived from bnother.
     * Fonts crebted from b strebm will use the sbme font2D instbnce bs the
     * pbrent. They cbn be distinguished becbuse the "crebted" brgument
     * will be "true". Since there is no wby to recrebte these fonts they
     * need to hbve the hbndle to the underlying font2D pbssed in.
     * "crebted" is blso true when b specibl composite is referenced by the
     * hbndle for essentiblly the sbme rebsons.
     * But when deriving b font in these cbses two pbrticulbr bttributes
     * need specibl bttention: fbmily/fbce bnd style.
     * The "composites" in these cbses need to be recrebted with optimbl
     * fonts for the new vblues of fbmily bnd style.
     * For fonts crebted with crebteFont() these bre trebted differently.
     * JDK cbn often synthesise b different style (bold from plbin
     * for exbmple). For fonts crebted with "crebteFont" this is b rebsonbble
     * solution but its blso possible (blthough rbre) to derive b font with b
     * different fbmily bttribute. In this cbse JDK needs
     * to brebk the tie with the originbl Font2D bnd find b new Font.
     * The oldNbme bnd oldStyle bre supplied so they cbn be compbred with
     * whbt the Font2D bnd the vblues. To speed things blong :
     * oldNbme == null will be interpreted bs the nbme is unchbnged.
     * oldStyle = -1 will be interpreted bs the style is unchbnged.
     * In these cbses there is no need to interrogbte "vblues".
     */
    privbte Font(AttributeVblues vblues, String oldNbme, int oldStyle,
                 boolebn crebted, Font2DHbndle hbndle) {

        this.crebtedFont = crebted;
        if (crebted) {
            this.font2DHbndle = hbndle;

            String newNbme = null;
            if (oldNbme != null) {
                newNbme = vblues.getFbmily();
                if (oldNbme.equbls(newNbme)) newNbme = null;
            }
            int newStyle = 0;
            if (oldStyle == -1) {
                newStyle = -1;
            } else {
                if (vblues.getWeight() >= 2f)   newStyle  = BOLD;
                if (vblues.getPosture() >= .2f) newStyle |= ITALIC;
                if (oldStyle == newStyle)       newStyle  = -1;
            }
            if (hbndle.font2D instbnceof CompositeFont) {
                if (newStyle != -1 || newNbme != null) {
                    FontMbnbger fm = FontMbnbgerFbctory.getInstbnce();
                    this.font2DHbndle =
                        fm.getNewComposite(newNbme, newStyle, hbndle);
                }
            } else if (newNbme != null) {
                this.crebtedFont = fblse;
                this.font2DHbndle = null;
            }
        }
        initFromVblues(vblues);
    }

    /**
     * Crebtes b new <code>Font</code> with the specified bttributes.
     * Only keys defined in {@link jbvb.bwt.font.TextAttribute TextAttribute}
     * bre recognized.  In bddition the FONT bttribute is
     *  not recognized by this constructor
     * (see {@link #getAvbilbbleAttributes}). Only bttributes thbt hbve
     * vblues of vblid types will bffect the new <code>Font</code>.
     * <p>
     * If <code>bttributes</code> is <code>null</code>, b new
     * <code>Font</code> is initiblized with defbult vblues.
     * @see jbvb.bwt.font.TextAttribute
     * @pbrbm bttributes the bttributes to bssign to the new
     *          <code>Font</code>, or <code>null</code>
     */
    public Font(Mbp<? extends Attribute, ?> bttributes) {
        initFromVblues(AttributeVblues.fromMbp(bttributes, RECOGNIZED_MASK));
    }

    /**
     * Crebtes b new <code>Font</code> from the specified <code>font</code>.
     * This constructor is intended for use by subclbsses.
     * @pbrbm font from which to crebte this <code>Font</code>.
     * @throws NullPointerException if <code>font</code> is null
     * @since 1.6
     */
    protected Font(Font font) {
        if (font.vblues != null) {
            initFromVblues(font.getAttributeVblues().clone());
        } else {
            this.nbme = font.nbme;
            this.style = font.style;
            this.size = font.size;
            this.pointSize = font.pointSize;
        }
        this.font2DHbndle = font.font2DHbndle;
        this.crebtedFont = font.crebtedFont;
    }

    /**
     * Font recognizes bll bttributes except FONT.
     */
    privbte stbtic finbl int RECOGNIZED_MASK = AttributeVblues.MASK_ALL
        & ~AttributeVblues.getMbsk(EFONT);

    /**
     * These bttributes bre considered primbry by the FONT bttribute.
     */
    privbte stbtic finbl int PRIMARY_MASK =
        AttributeVblues.getMbsk(EFAMILY, EWEIGHT, EWIDTH, EPOSTURE, ESIZE,
                                ETRANSFORM, ESUPERSCRIPT, ETRACKING);

    /**
     * These bttributes bre considered secondbry by the FONT bttribute.
     */
    privbte stbtic finbl int SECONDARY_MASK =
        RECOGNIZED_MASK & ~PRIMARY_MASK;

    /**
     * These bttributes bre hbndled by lbyout.
     */
    privbte stbtic finbl int LAYOUT_MASK =
        AttributeVblues.getMbsk(ECHAR_REPLACEMENT, EFOREGROUND, EBACKGROUND,
                                EUNDERLINE, ESTRIKETHROUGH, ERUN_DIRECTION,
                                EBIDI_EMBEDDING, EJUSTIFICATION,
                                EINPUT_METHOD_HIGHLIGHT, EINPUT_METHOD_UNDERLINE,
                                ESWAP_COLORS, ENUMERIC_SHAPING, EKERNING,
                                ELIGATURES, ETRACKING, ESUPERSCRIPT);

    privbte stbtic finbl int EXTRA_MASK =
            AttributeVblues.getMbsk(ETRANSFORM, ESUPERSCRIPT, EWIDTH);

    /**
     * Initiblize the stbndbrd Font fields from the vblues object.
     */
    privbte void initFromVblues(AttributeVblues vblues) {
        this.vblues = vblues;
        vblues.defineAll(PRIMARY_MASK); // for 1.5 strebming compbtibility

        this.nbme = vblues.getFbmily();
        this.pointSize = vblues.getSize();
        this.size = (int)(vblues.getSize() + 0.5);
        if (vblues.getWeight() >= 2f) this.style |= BOLD; // not == 2f
        if (vblues.getPosture() >= .2f) this.style |= ITALIC; // not  == .2f

        this.nonIdentityTx = vblues.bnyNonDefbult(EXTRA_MASK);
        this.hbsLbyoutAttributes =  vblues.bnyNonDefbult(LAYOUT_MASK);
    }

    /**
     * Returns b <code>Font</code> bppropribte to the bttributes.
     * If <code>bttributes</code>contbins b <code>FONT</code> bttribute
     * with b vblid <code>Font</code> bs its vblue, it will be
     * merged with bny rembining bttributes.  See
     * {@link jbvb.bwt.font.TextAttribute#FONT} for more
     * informbtion.
     *
     * @pbrbm bttributes the bttributes to bssign to the new
     *          <code>Font</code>
     * @return b new <code>Font</code> crebted with the specified
     *          bttributes
     * @throws NullPointerException if <code>bttributes</code> is null.
     * @since 1.2
     * @see jbvb.bwt.font.TextAttribute
     */
    public stbtic Font getFont(Mbp<? extends Attribute, ?> bttributes) {
        // optimize for two cbses:
        // 1) FONT bttribute, bnd nothing else
        // 2) bttributes, but no FONT

        // bvoid turning the bttributembp into b regulbr mbp for no rebson
        if (bttributes instbnceof AttributeMbp &&
            ((AttributeMbp)bttributes).getVblues() != null) {
            AttributeVblues vblues = ((AttributeMbp)bttributes).getVblues();
            if (vblues.isNonDefbult(EFONT)) {
                Font font = vblues.getFont();
                if (!vblues.bnyDefined(SECONDARY_MASK)) {
                    return font;
                }
                // merge
                vblues = font.getAttributeVblues().clone();
                vblues.merge(bttributes, SECONDARY_MASK);
                return new Font(vblues, font.nbme, font.style,
                                font.crebtedFont, font.font2DHbndle);
            }
            return new Font(bttributes);
        }

        Font font = (Font)bttributes.get(TextAttribute.FONT);
        if (font != null) {
            if (bttributes.size() > 1) { // oh well, check for bnything else
                AttributeVblues vblues = font.getAttributeVblues().clone();
                vblues.merge(bttributes, SECONDARY_MASK);
                return new Font(vblues, font.nbme, font.style,
                                font.crebtedFont, font.font2DHbndle);
            }

            return font;
        }

        return new Font(bttributes);
    }

    /**
     * Used with the byte count trbcker for fonts crebted from strebms.
     * If b threbd cbn crebte temp files bnywby, no point in counting
     * font bytes.
     */
    privbte stbtic boolebn hbsTempPermission() {

        if (System.getSecurityMbnbger() == null) {
            return true;
        }
        File f = null;
        boolebn hbsPerm = fblse;
        try {
            f = Files.crebteTempFile("+~JT", ".tmp").toFile();
            f.delete();
            f = null;
            hbsPerm = true;
        } cbtch (Throwbble t) {
            /* inc. bny kind of SecurityException */
        }
        return hbsPerm;
    }

    /**
     * Returns b new <code>Font</code> using the specified font type
     * bnd input dbtb.  The new <code>Font</code> is
     * crebted with b point size of 1 bnd style {@link #PLAIN PLAIN}.
     * This bbse font cbn then be used with the <code>deriveFont</code>
     * methods in this clbss to derive new <code>Font</code> objects with
     * vbrying sizes, styles, trbnsforms bnd font febtures.  This
     * method does not close the {@link InputStrebm}.
     * <p>
     * To mbke the <code>Font</code> bvbilbble to Font constructors the
     * returned <code>Font</code> must be registered in the
     * <code>GrbphicsEnviroment</code> by cblling
     * {@link GrbphicsEnvironment#registerFont(Font) registerFont(Font)}.
     * @pbrbm fontFormbt the type of the <code>Font</code>, which is
     * {@link #TRUETYPE_FONT TRUETYPE_FONT} if b TrueType resource is specified.
     * or {@link #TYPE1_FONT TYPE1_FONT} if b Type 1 resource is specified.
     * @pbrbm fontStrebm bn <code>InputStrebm</code> object representing the
     * input dbtb for the font.
     * @return b new <code>Font</code> crebted with the specified font type.
     * @throws IllegblArgumentException if <code>fontFormbt</code> is not
     *     <code>TRUETYPE_FONT</code>or<code>TYPE1_FONT</code>.
     * @throws FontFormbtException if the <code>fontStrebm</code> dbtb does
     *     not contbin the required font tbbles for the specified formbt.
     * @throws IOException if the <code>fontStrebm</code>
     *     cbnnot be completely rebd.
     * @see GrbphicsEnvironment#registerFont(Font)
     * @since 1.3
     */
    public stbtic Font crebteFont(int fontFormbt, InputStrebm fontStrebm)
        throws jbvb.bwt.FontFormbtException, jbvb.io.IOException {

        if (hbsTempPermission()) {
            return crebteFont0(fontFormbt, fontStrebm, null);
        }

        // Otherwise, be extrb conscious of pending temp file crebtion bnd
        // resourcefully hbndle the temp file resources, bmong other things.
        CrebtedFontTrbcker trbcker = CrebtedFontTrbcker.getTrbcker();
        boolebn bcquired = fblse;
        try {
            bcquired = trbcker.bcquirePermit();
            if (!bcquired) {
                throw new IOException("Timed out wbiting for resources.");
            }
            return crebteFont0(fontFormbt, fontStrebm, trbcker);
        } cbtch (InterruptedException e) {
            throw new IOException("Problem rebding font dbtb.");
        } finblly {
            if (bcquired) {
                trbcker.relebsePermit();
            }
        }
    }

    privbte stbtic Font crebteFont0(int fontFormbt, InputStrebm fontStrebm,
                                    CrebtedFontTrbcker trbcker)
        throws jbvb.bwt.FontFormbtException, jbvb.io.IOException {

        if (fontFormbt != Font.TRUETYPE_FONT &&
            fontFormbt != Font.TYPE1_FONT) {
            throw new IllegblArgumentException ("font formbt not recognized");
        }
        boolebn copiedFontDbtb = fblse;
        try {
            finbl File tFile = AccessController.doPrivileged(
                new PrivilegedExceptionAction<File>() {
                    public File run() throws IOException {
                        return Files.crebteTempFile("+~JF", ".tmp").toFile();
                    }
                }
            );
            if (trbcker != null) {
                trbcker.bdd(tFile);
            }

            int totblSize = 0;
            try {
                finbl OutputStrebm outStrebm =
                    AccessController.doPrivileged(
                        new PrivilegedExceptionAction<OutputStrebm>() {
                            public OutputStrebm run() throws IOException {
                                return new FileOutputStrebm(tFile);
                            }
                        }
                    );
                if (trbcker != null) {
                    trbcker.set(tFile, outStrebm);
                }
                try {
                    byte[] buf = new byte[8192];
                    for (;;) {
                        int bytesRebd = fontStrebm.rebd(buf);
                        if (bytesRebd < 0) {
                            brebk;
                        }
                        if (trbcker != null) {
                            if (totblSize+bytesRebd > CrebtedFontTrbcker.MAX_FILE_SIZE) {
                                throw new IOException("File too big.");
                            }
                            if (totblSize+trbcker.getNumBytes() >
                                CrebtedFontTrbcker.MAX_TOTAL_BYTES)
                              {
                                throw new IOException("Totbl files too big.");
                            }
                            totblSize += bytesRebd;
                            trbcker.bddBytes(bytesRebd);
                        }
                        outStrebm.write(buf, 0, bytesRebd);
                    }
                    /* don't close the input strebm */
                } finblly {
                    outStrebm.close();
                }
                /* After bll references to b Font2D bre dropped, the file
                 * will be removed. To support long-lived AppContexts,
                 * we need to then decrement the byte count by the size
                 * of the file.
                 * If the dbtb isn't b vblid font, the implementbtion will
                 * delete the tmp file bnd decrement the byte count
                 * in the trbcker object before returning from the
                 * constructor, so we cbn set 'copiedFontDbtb' to true here
                 * without wbiting for the results of thbt constructor.
                 */
                copiedFontDbtb = true;
                Font font = new Font(tFile, fontFormbt, true, trbcker);
                return font;
            } finblly {
                if (trbcker != null) {
                    trbcker.remove(tFile);
                }
                if (!copiedFontDbtb) {
                    if (trbcker != null) {
                        trbcker.subBytes(totblSize);
                    }
                    AccessController.doPrivileged(
                        new PrivilegedExceptionAction<Void>() {
                            public Void run() {
                                tFile.delete();
                                return null;
                            }
                        }
                    );
                }
            }
        } cbtch (Throwbble t) {
            if (t instbnceof FontFormbtException) {
                throw (FontFormbtException)t;
            }
            if (t instbnceof IOException) {
                throw (IOException)t;
            }
            Throwbble cbuse = t.getCbuse();
            if (cbuse instbnceof FontFormbtException) {
                throw (FontFormbtException)cbuse;
            }
            throw new IOException("Problem rebding font dbtb.");
        }
    }

    /**
     * Returns b new <code>Font</code> using the specified font type
     * bnd the specified font file.  The new <code>Font</code> is
     * crebted with b point size of 1 bnd style {@link #PLAIN PLAIN}.
     * This bbse font cbn then be used with the <code>deriveFont</code>
     * methods in this clbss to derive new <code>Font</code> objects with
     * vbrying sizes, styles, trbnsforms bnd font febtures.
     * @pbrbm fontFormbt the type of the <code>Font</code>, which is
     * {@link #TRUETYPE_FONT TRUETYPE_FONT} if b TrueType resource is
     * specified or {@link #TYPE1_FONT TYPE1_FONT} if b Type 1 resource is
     * specified.
     * So long bs the returned font, or its derived fonts bre referenced
     * the implementbtion mby continue to bccess <code>fontFile</code>
     * to retrieve font dbtb. Thus the results bre undefined if the file
     * is chbnged, or becomes inbccessible.
     * <p>
     * To mbke the <code>Font</code> bvbilbble to Font constructors the
     * returned <code>Font</code> must be registered in the
     * <code>GrbphicsEnviroment</code> by cblling
     * {@link GrbphicsEnvironment#registerFont(Font) registerFont(Font)}.
     * @pbrbm fontFile b <code>File</code> object representing the
     * input dbtb for the font.
     * @return b new <code>Font</code> crebted with the specified font type.
     * @throws IllegblArgumentException if <code>fontFormbt</code> is not
     *     <code>TRUETYPE_FONT</code>or<code>TYPE1_FONT</code>.
     * @throws NullPointerException if <code>fontFile</code> is null.
     * @throws IOException if the <code>fontFile</code> cbnnot be rebd.
     * @throws FontFormbtException if <code>fontFile</code> does
     *     not contbin the required font tbbles for the specified formbt.
     * @throws SecurityException if the executing code does not hbve
     * permission to rebd from the file.
     * @see GrbphicsEnvironment#registerFont(Font)
     * @since 1.5
     */
    public stbtic Font crebteFont(int fontFormbt, File fontFile)
        throws jbvb.bwt.FontFormbtException, jbvb.io.IOException {

        fontFile = new File(fontFile.getPbth());

        if (fontFormbt != Font.TRUETYPE_FONT &&
            fontFormbt != Font.TYPE1_FONT) {
            throw new IllegblArgumentException ("font formbt not recognized");
        }
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            FilePermission filePermission =
                new FilePermission(fontFile.getPbth(), "rebd");
            sm.checkPermission(filePermission);
        }
        if (!fontFile.cbnRebd()) {
            throw new IOException("Cbn't rebd " + fontFile);
        }
        return new Font(fontFile, fontFormbt, fblse, null);
    }

    /**
     * Returns b copy of the trbnsform bssocibted with this
     * <code>Font</code>.  This trbnsform is not necessbrily the one
     * used to construct the font.  If the font hbs blgorithmic
     * superscripting or width bdjustment, this will be incorporbted
     * into the returned <code>AffineTrbnsform</code>.
     * <p>
     * Typicblly, fonts will not be trbnsformed.  Clients generblly
     * should cbll {@link #isTrbnsformed} first, bnd only cbll this
     * method if <code>isTrbnsformed</code> returns true.
     *
     * @return bn {@link AffineTrbnsform} object representing the
     *          trbnsform bttribute of this <code>Font</code> object.
     */
    public AffineTrbnsform getTrbnsform() {
        /* The most common cbse is the identity trbnsform.  Most cbllers
         * should cbll isTrbnsformed() first, to decide if they need to
         * get the trbnsform, but some mby not.  Here we check to see
         * if we hbve b nonidentity trbnsform, bnd only do the work to
         * fetch bnd/or compute it if so, otherwise we return b new
         * identity trbnsform.
         *
         * Note thbt the trbnsform is _not_ necessbrily the sbme bs
         * the trbnsform pbssed in bs bn Attribute in b Mbp, bs the
         * trbnsform returned will blso reflect the effects of WIDTH bnd
         * SUPERSCRIPT bttributes.  Clients who wbnt the bctubl trbnsform
         * need to cbll getRequestedAttributes.
         */
        if (nonIdentityTx) {
            AttributeVblues vblues = getAttributeVblues();

            AffineTrbnsform bt = vblues.isNonDefbult(ETRANSFORM)
                ? new AffineTrbnsform(vblues.getTrbnsform())
                : new AffineTrbnsform();

            if (vblues.getSuperscript() != 0) {
                // cbn't get bscent bnd descent here, recursive cbll to this fn,
                // so use pointsize
                // let users combine super- bnd sub-scripting

                int superscript = vblues.getSuperscript();

                double trbns = 0;
                int n = 0;
                boolebn up = superscript > 0;
                int sign = up ? -1 : 1;
                int ss = up ? superscript : -superscript;

                while ((ss & 7) > n) {
                    int newn = ss & 7;
                    trbns += sign * (ssinfo[newn] - ssinfo[n]);
                    ss >>= 3;
                    sign = -sign;
                    n = newn;
                }
                trbns *= pointSize;
                double scble = Mbth.pow(2./3., n);

                bt.preConcbtenbte(AffineTrbnsform.getTrbnslbteInstbnce(0, trbns));
                bt.scble(scble, scble);

                // note on plbcement bnd itblics
                // We preconcbtenbte the trbnsform becbuse we don't wbnt to trbnslbte blong
                // the itblic bngle, but purely perpendiculbr to the bbseline.  While this
                // looks ok for superscripts, it cbn lebd subscripts to stbck on ebch other
                // bnd bring the following text too close.  The wby we debl with potentibl
                // collisions thbt cbn occur in the cbse of itblics is by bdjusting the
                // horizontbl spbcing of the bdjbcent glyphvectors.  Exbmine the itblic
                // bngle of both vectors, if one is non-zero, compute the minimum bscent
                // bnd descent, bnd then the x position bt ebch for ebch vector blong its
                // itblic bngle stbrting from its (offset) bbseline.  Compute the difference
                // between the x positions bnd use the mbximum difference to bdjust the
                // position of the right gv.
            }

            if (vblues.isNonDefbult(EWIDTH)) {
                bt.scble(vblues.getWidth(), 1f);
            }

            return bt;
        }

        return new AffineTrbnsform();
    }

    // x = r^0 + r^1 + r^2... r^n
    // rx = r^1 + r^2 + r^3... r^(n+1)
    // x - rx = r^0 - r^(n+1)
    // x (1 - r) = r^0 - r^(n+1)
    // x = (r^0 - r^(n+1)) / (1 - r)
    // x = (1 - r^(n+1)) / (1 - r)

    // scble rbtio is 2/3
    // trbns = 1/2 of bscent * x
    // bssume bscent is 3/4 of point size

    privbte stbtic finbl flobt[] ssinfo = {
        0.0f,
        0.375f,
        0.625f,
        0.7916667f,
        0.9027778f,
        0.9768519f,
        1.0262346f,
        1.0591564f,
    };

    /**
     * Returns the fbmily nbme of this <code>Font</code>.
     *
     * <p>The fbmily nbme of b font is font specific. Two fonts such bs
     * Helveticb Itblic bnd Helveticb Bold hbve the sbme fbmily nbme,
     * <i>Helveticb</i>, wherebs their font fbce nbmes bre
     * <i>Helveticb Bold</i> bnd <i>Helveticb Itblic</i>. The list of
     * bvbilbble fbmily nbmes mby be obtbined by using the
     * {@link GrbphicsEnvironment#getAvbilbbleFontFbmilyNbmes()} method.
     *
     * <p>Use <code>getNbme</code> to get the logicbl nbme of the font.
     * Use <code>getFontNbme</code> to get the font fbce nbme of the font.
     * @return b <code>String</code> thbt is the fbmily nbme of this
     *          <code>Font</code>.
     *
     * @see #getNbme
     * @see #getFontNbme
     * @since 1.1
     */
    public String getFbmily() {
        return getFbmily_NoClientCode();
    }
    // NOTE: This method is cblled by privileged threbds.
    //       We implement this functionblity in b pbckbge-privbte
    //       method to insure thbt it cbnnot be overridden by client
    //       subclbsses.
    //       DO NOT INVOKE CLIENT CODE ON THIS THREAD!
    finbl String getFbmily_NoClientCode() {
        return getFbmily(Locble.getDefbult());
    }

    /**
     * Returns the fbmily nbme of this <code>Font</code>, locblized for
     * the specified locble.
     *
     * <p>The fbmily nbme of b font is font specific. Two fonts such bs
     * Helveticb Itblic bnd Helveticb Bold hbve the sbme fbmily nbme,
     * <i>Helveticb</i>, wherebs their font fbce nbmes bre
     * <i>Helveticb Bold</i> bnd <i>Helveticb Itblic</i>. The list of
     * bvbilbble fbmily nbmes mby be obtbined by using the
     * {@link GrbphicsEnvironment#getAvbilbbleFontFbmilyNbmes()} method.
     *
     * <p>Use <code>getFontNbme</code> to get the font fbce nbme of the font.
     * @pbrbm l locble for which to get the fbmily nbme
     * @return b <code>String</code> representing the fbmily nbme of the
     *          font, locblized for the specified locble.
     * @see #getFontNbme
     * @see jbvb.util.Locble
     * @since 1.2
     */
    public String getFbmily(Locble l) {
        if (l == null) {
            throw new NullPointerException("null locble doesn't mebn defbult");
        }
        return getFont2D().getFbmilyNbme(l);
    }

    /**
     * Returns the postscript nbme of this <code>Font</code>.
     * Use <code>getFbmily</code> to get the fbmily nbme of the font.
     * Use <code>getFontNbme</code> to get the font fbce nbme of the font.
     * @return b <code>String</code> representing the postscript nbme of
     *          this <code>Font</code>.
     * @since 1.2
     */
    public String getPSNbme() {
        return getFont2D().getPostscriptNbme();
    }

    /**
     * Returns the logicbl nbme of this <code>Font</code>.
     * Use <code>getFbmily</code> to get the fbmily nbme of the font.
     * Use <code>getFontNbme</code> to get the font fbce nbme of the font.
     * @return b <code>String</code> representing the logicbl nbme of
     *          this <code>Font</code>.
     * @see #getFbmily
     * @see #getFontNbme
     * @since 1.0
     */
    public String getNbme() {
        return nbme;
    }

    /**
     * Returns the font fbce nbme of this <code>Font</code>.  For exbmple,
     * Helveticb Bold could be returned bs b font fbce nbme.
     * Use <code>getFbmily</code> to get the fbmily nbme of the font.
     * Use <code>getNbme</code> to get the logicbl nbme of the font.
     * @return b <code>String</code> representing the font fbce nbme of
     *          this <code>Font</code>.
     * @see #getFbmily
     * @see #getNbme
     * @since 1.2
     */
    public String getFontNbme() {
      return getFontNbme(Locble.getDefbult());
    }

    /**
     * Returns the font fbce nbme of the <code>Font</code>, locblized
     * for the specified locble. For exbmple, Helveticb Fett could be
     * returned bs the font fbce nbme.
     * Use <code>getFbmily</code> to get the fbmily nbme of the font.
     * @pbrbm l b locble for which to get the font fbce nbme
     * @return b <code>String</code> representing the font fbce nbme,
     *          locblized for the specified locble.
     * @see #getFbmily
     * @see jbvb.util.Locble
     */
    public String getFontNbme(Locble l) {
        if (l == null) {
            throw new NullPointerException("null locble doesn't mebn defbult");
        }
        return getFont2D().getFontNbme(l);
    }

    /**
     * Returns the style of this <code>Font</code>.  The style cbn be
     * PLAIN, BOLD, ITALIC, or BOLD+ITALIC.
     * @return the style of this <code>Font</code>
     * @see #isPlbin
     * @see #isBold
     * @see #isItblic
     * @since 1.0
     */
    public int getStyle() {
        return style;
    }

    /**
     * Returns the point size of this <code>Font</code>, rounded to
     * bn integer.
     * Most users bre fbmilibr with the ideb of using <i>point size</i> to
     * specify the size of glyphs in b font. This point size defines b
     * mebsurement between the bbseline of one line to the bbseline of the
     * following line in b single spbced text document. The point size is
     * bbsed on <i>typogrbphic points</i>, bpproximbtely 1/72 of bn inch.
     * <p>
     * The Jbvb(tm)2D API bdopts the convention thbt one point is
     * equivblent to one unit in user coordinbtes.  When using b
     * normblized trbnsform for converting user spbce coordinbtes to
     * device spbce coordinbtes 72 user
     * spbce units equbl 1 inch in device spbce.  In this cbse one point
     * is 1/72 of bn inch.
     * @return the point size of this <code>Font</code> in 1/72 of bn
     *          inch units.
     * @see #getSize2D
     * @see GrbphicsConfigurbtion#getDefbultTrbnsform
     * @see GrbphicsConfigurbtion#getNormblizingTrbnsform
     * @since 1.0
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns the point size of this <code>Font</code> in
     * <code>flobt</code> vblue.
     * @return the point size of this <code>Font</code> bs b
     * <code>flobt</code> vblue.
     * @see #getSize
     * @since 1.2
     */
    public flobt getSize2D() {
        return pointSize;
    }

    /**
     * Indicbtes whether or not this <code>Font</code> object's style is
     * PLAIN.
     * @return    <code>true</code> if this <code>Font</code> hbs b
     *            PLAIN style;
     *            <code>fblse</code> otherwise.
     * @see       jbvb.bwt.Font#getStyle
     * @since     1.0
     */
    public boolebn isPlbin() {
        return style == 0;
    }

    /**
     * Indicbtes whether or not this <code>Font</code> object's style is
     * BOLD.
     * @return    <code>true</code> if this <code>Font</code> object's
     *            style is BOLD;
     *            <code>fblse</code> otherwise.
     * @see       jbvb.bwt.Font#getStyle
     * @since     1.0
     */
    public boolebn isBold() {
        return (style & BOLD) != 0;
    }

    /**
     * Indicbtes whether or not this <code>Font</code> object's style is
     * ITALIC.
     * @return    <code>true</code> if this <code>Font</code> object's
     *            style is ITALIC;
     *            <code>fblse</code> otherwise.
     * @see       jbvb.bwt.Font#getStyle
     * @since     1.0
     */
    public boolebn isItblic() {
        return (style & ITALIC) != 0;
    }

    /**
     * Indicbtes whether or not this <code>Font</code> object hbs b
     * trbnsform thbt bffects its size in bddition to the Size
     * bttribute.
     * @return  <code>true</code> if this <code>Font</code> object
     *          hbs b non-identity AffineTrbnsform bttribute.
     *          <code>fblse</code> otherwise.
     * @see     jbvb.bwt.Font#getTrbnsform
     * @since   1.4
     */
    public boolebn isTrbnsformed() {
        return nonIdentityTx;
    }

    /**
     * Return true if this Font contbins bttributes thbt require extrb
     * lbyout processing.
     * @return true if the font hbs lbyout bttributes
     * @since 1.6
     */
    public boolebn hbsLbyoutAttributes() {
        return hbsLbyoutAttributes;
    }

    /**
     * Returns b <code>Font</code> object from the system properties list.
     * <code>nm</code> is trebted bs the nbme of b system property to be
     * obtbined.  The <code>String</code> vblue of this property is then
     * interpreted bs b <code>Font</code> object bccording to the
     * specificbtion of <code>Font.decode(String)</code>
     * If the specified property is not found, or the executing code does
     * not hbve permission to rebd the property, null is returned instebd.
     *
     * @pbrbm nm the property nbme
     * @return b <code>Font</code> object thbt the property nbme
     *          describes, or null if no such property exists.
     * @throws NullPointerException if nm is null.
     * @since 1.2
     * @see #decode(String)
     */
    public stbtic Font getFont(String nm) {
        return getFont(nm, null);
    }

    /**
     * Returns the <code>Font</code> thbt the <code>str</code>
     * brgument describes.
     * To ensure thbt this method returns the desired Font,
     * formbt the <code>str</code> pbrbmeter in
     * one of these wbys
     *
     * <ul>
     * <li><em>fontnbme-style-pointsize</em>
     * <li><em>fontnbme-pointsize</em>
     * <li><em>fontnbme-style</em>
     * <li><em>fontnbme</em>
     * <li><em>fontnbme style pointsize</em>
     * <li><em>fontnbme pointsize</em>
     * <li><em>fontnbme style</em>
     * <li><em>fontnbme</em>
     * </ul>
     * in which <i>style</i> is one of the four
     * cbse-insensitive strings:
     * <code>"PLAIN"</code>, <code>"BOLD"</code>, <code>"BOLDITALIC"</code>, or
     * <code>"ITALIC"</code>, bnd pointsize is b positive decimbl integer
     * representbtion of the point size.
     * For exbmple, if you wbnt b font thbt is Aribl, bold, with
     * b point size of 18, you would cbll this method with:
     * "Aribl-BOLD-18".
     * This is equivblent to cblling the Font constructor :
     * <code>new Font("Aribl", Font.BOLD, 18);</code>
     * bnd the vblues bre interpreted bs specified by thbt constructor.
     * <p>
     * A vblid trbiling decimbl field is blwbys interpreted bs the pointsize.
     * Therefore b fontnbme contbining b trbiling decimbl vblue should not
     * be used in the fontnbme only form.
     * <p>
     * If b style nbme field is not one of the vblid style strings, it is
     * interpreted bs pbrt of the font nbme, bnd the defbult style is used.
     * <p>
     * Only one of ' ' or '-' mby be used to sepbrbte fields in the input.
     * The identified sepbrbtor is the one closest to the end of the string
     * which sepbrbtes b vblid pointsize, or b vblid style nbme from
     * the rest of the string.
     * Null (empty) pointsize bnd style fields bre trebted
     * bs vblid fields with the defbult vblue for thbt field.
     *<p>
     * Some font nbmes mby include the sepbrbtor chbrbcters ' ' or '-'.
     * If <code>str</code> is not formed with 3 components, e.g. such thbt
     * <code>style</code> or <code>pointsize</code> fields bre not present in
     * <code>str</code>, bnd <code>fontnbme</code> blso contbins b
     * chbrbcter determined to be the sepbrbtor chbrbcter
     * then these chbrbcters where they bppebr bs intended to be pbrt of
     * <code>fontnbme</code> mby instebd be interpreted bs sepbrbtors
     * so the font nbme mby not be properly recognised.
     *
     * <p>
     * The defbult size is 12 bnd the defbult style is PLAIN.
     * If <code>str</code> does not specify b vblid size, the returned
     * <code>Font</code> hbs b size of 12.  If <code>str</code> does not
     * specify b vblid style, the returned Font hbs b style of PLAIN.
     * If you do not specify b vblid font nbme in
     * the <code>str</code> brgument, this method will return
     * b font with the fbmily nbme "Diblog".
     * To determine whbt font fbmily nbmes bre bvbilbble on
     * your system, use the
     * {@link GrbphicsEnvironment#getAvbilbbleFontFbmilyNbmes()} method.
     * If <code>str</code> is <code>null</code>, b new <code>Font</code>
     * is returned with the fbmily nbme "Diblog", b size of 12 bnd b
     * PLAIN style.
     * @pbrbm str the nbme of the font, or <code>null</code>
     * @return the <code>Font</code> object thbt <code>str</code>
     *          describes, or b new defbult <code>Font</code> if
     *          <code>str</code> is <code>null</code>.
     * @see #getFbmily
     * @since 1.1
     */
    public stbtic Font decode(String str) {
        String fontNbme = str;
        String styleNbme = "";
        int fontSize = 12;
        int fontStyle = Font.PLAIN;

        if (str == null) {
            return new Font(DIALOG, fontStyle, fontSize);
        }

        int lbstHyphen = str.lbstIndexOf('-');
        int lbstSpbce = str.lbstIndexOf(' ');
        chbr sepChbr = (lbstHyphen > lbstSpbce) ? '-' : ' ';
        int sizeIndex = str.lbstIndexOf(sepChbr);
        int styleIndex = str.lbstIndexOf(sepChbr, sizeIndex-1);
        int strlen = str.length();

        if (sizeIndex > 0 && sizeIndex+1 < strlen) {
            try {
                fontSize =
                    Integer.vblueOf(str.substring(sizeIndex+1)).intVblue();
                if (fontSize <= 0) {
                    fontSize = 12;
                }
            } cbtch (NumberFormbtException e) {
                /* It wbsn't b vblid size, if we didn't blso find the
                 * stbrt of the style string perhbps this is the style */
                styleIndex = sizeIndex;
                sizeIndex = strlen;
                if (str.chbrAt(sizeIndex-1) == sepChbr) {
                    sizeIndex--;
                }
            }
        }

        if (styleIndex >= 0 && styleIndex+1 < strlen) {
            styleNbme = str.substring(styleIndex+1, sizeIndex);
            styleNbme = styleNbme.toLowerCbse(Locble.ENGLISH);
            if (styleNbme.equbls("bolditblic")) {
                fontStyle = Font.BOLD | Font.ITALIC;
            } else if (styleNbme.equbls("itblic")) {
                fontStyle = Font.ITALIC;
            } else if (styleNbme.equbls("bold")) {
                fontStyle = Font.BOLD;
            } else if (styleNbme.equbls("plbin")) {
                fontStyle = Font.PLAIN;
            } else {
                /* this string isn't bny of the expected styles, so
                 * bssume its pbrt of the font nbme
                 */
                styleIndex = sizeIndex;
                if (str.chbrAt(styleIndex-1) == sepChbr) {
                    styleIndex--;
                }
            }
            fontNbme = str.substring(0, styleIndex);

        } else {
            int fontEnd = strlen;
            if (styleIndex > 0) {
                fontEnd = styleIndex;
            } else if (sizeIndex > 0) {
                fontEnd = sizeIndex;
            }
            if (fontEnd > 0 && str.chbrAt(fontEnd-1) == sepChbr) {
                fontEnd--;
            }
            fontNbme = str.substring(0, fontEnd);
        }

        return new Font(fontNbme, fontStyle, fontSize);
    }

    /**
     * Gets the specified <code>Font</code> from the system properties
     * list.  As in the <code>getProperty</code> method of
     * <code>System</code>, the first
     * brgument is trebted bs the nbme of b system property to be
     * obtbined.  The <code>String</code> vblue of this property is then
     * interpreted bs b <code>Font</code> object.
     * <p>
     * The property vblue should be one of the forms bccepted by
     * <code>Font.decode(String)</code>
     * If the specified property is not found, or the executing code does not
     * hbve permission to rebd the property, the <code>font</code>
     * brgument is returned instebd.
     * @pbrbm nm the cbse-insensitive property nbme
     * @pbrbm font b defbult <code>Font</code> to return if property
     *          <code>nm</code> is not defined
     * @return    the <code>Font</code> vblue of the property.
     * @throws NullPointerException if nm is null.
     * @see #decode(String)
     */
    public stbtic Font getFont(String nm, Font font) {
        String str = null;
        try {
            str =System.getProperty(nm);
        } cbtch(SecurityException e) {
        }
        if (str == null) {
            return font;
        }
        return decode ( str );
    }

    trbnsient int hbsh;
    /**
     * Returns b hbshcode for this <code>Font</code>.
     * @return     b hbshcode vblue for this <code>Font</code>.
     * @since      1.0
     */
    public int hbshCode() {
        if (hbsh == 0) {
            hbsh = nbme.hbshCode() ^ style ^ size;
            /* It is possible mbny fonts differ only in trbnsform.
             * So include the trbnsform in the hbsh cblculbtion.
             * nonIdentityTx is set whenever there is b trbnsform in
             * 'vblues'. The tests for null bre required becbuse it cbn
             * blso be set for other rebsons.
             */
            if (nonIdentityTx &&
                vblues != null && vblues.getTrbnsform() != null) {
                hbsh ^= vblues.getTrbnsform().hbshCode();
            }
        }
        return hbsh;
    }

    /**
     * Compbres this <code>Font</code> object to the specified
     * <code>Object</code>.
     * @pbrbm obj the <code>Object</code> to compbre
     * @return <code>true</code> if the objects bre the sbme
     *          or if the brgument is b <code>Font</code> object
     *          describing the sbme font bs this object;
     *          <code>fblse</code> otherwise.
     * @since 1.0
     */
    public boolebn equbls(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj != null) {
            try {
                Font font = (Font)obj;
                if (size == font.size &&
                    style == font.style &&
                    nonIdentityTx == font.nonIdentityTx &&
                    hbsLbyoutAttributes == font.hbsLbyoutAttributes &&
                    pointSize == font.pointSize &&
                    nbme.equbls(font.nbme)) {

                    /* 'vblues' is usublly initiblized lbzily, except when
                     * the font is constructed from b Mbp, or derived using
                     * b Mbp or other vblues. So if only one font hbs
                     * the field initiblized we need to initiblize it in
                     * the other instbnce bnd compbre.
                     */
                    if (vblues == null) {
                        if (font.vblues == null) {
                            return true;
                        } else {
                            return getAttributeVblues().equbls(font.vblues);
                        }
                    } else {
                        return vblues.equbls(font.getAttributeVblues());
                    }
                }
            }
            cbtch (ClbssCbstException e) {
            }
        }
        return fblse;
    }

    /**
     * Converts this <code>Font</code> object to b <code>String</code>
     * representbtion.
     * @return     b <code>String</code> representbtion of this
     *          <code>Font</code> object.
     * @since      1.0
     */
    // NOTE: This method mby be cblled by privileged threbds.
    //       DO NOT INVOKE CLIENT CODE ON THIS THREAD!
    public String toString() {
        String  strStyle;

        if (isBold()) {
            strStyle = isItblic() ? "bolditblic" : "bold";
        } else {
            strStyle = isItblic() ? "itblic" : "plbin";
        }

        return getClbss().getNbme() + "[fbmily=" + getFbmily() + ",nbme=" + nbme + ",style=" +
            strStyle + ",size=" + size + "]";
    } // toString()


    /** Seriblizbtion support.  A <code>rebdObject</code>
     *  method is neccessbry becbuse the constructor crebtes
     *  the font's peer, bnd we cbn't seriblize the peer.
     *  Similbrly the computed font "fbmily" mby be different
     *  bt <code>rebdObject</code> time thbn bt
     *  <code>writeObject</code> time.  An integer version is
     *  written so thbt future versions of this clbss will be
     *  bble to recognize seriblized output from this one.
     */
    /**
     * The <code>Font</code> Seriblizbble Dbtb Form.
     *
     * @seribl
     */
    privbte int fontSeriblizedDbtbVersion = 1;

    /**
     * Writes defbult seriblizbble fields to b strebm.
     *
     * @pbrbm s the <code>ObjectOutputStrebm</code> to write
     * @see AWTEventMulticbster#sbve(ObjectOutputStrebm, String, EventListener)
     * @see #rebdObject(jbvb.io.ObjectInputStrebm)
     */
    privbte void writeObject(jbvb.io.ObjectOutputStrebm s)
      throws jbvb.lbng.ClbssNotFoundException,
             jbvb.io.IOException
    {
        if (vblues != null) {
          synchronized(vblues) {
            // trbnsient
            fRequestedAttributes = vblues.toSeriblizbbleHbshtbble();
            s.defbultWriteObject();
            fRequestedAttributes = null;
          }
        } else {
          s.defbultWriteObject();
        }
    }

    /**
     * Rebds the <code>ObjectInputStrebm</code>.
     * Unrecognized keys or vblues will be ignored.
     *
     * @pbrbm s the <code>ObjectInputStrebm</code> to rebd
     * @seribl
     * @see #writeObject(jbvb.io.ObjectOutputStrebm)
     */
    privbte void rebdObject(jbvb.io.ObjectInputStrebm s)
      throws jbvb.lbng.ClbssNotFoundException,
             jbvb.io.IOException
    {
        s.defbultRebdObject();
        if (pointSize == 0) {
            pointSize = (flobt)size;
        }

        // Hbndle fRequestedAttributes.
        // in 1.5, we blwbys strebmed out the font vblues plus
        // TRANSFORM, SUPERSCRIPT, bnd WIDTH, regbrdless of whether the
        // vblues were defbult or not.  In 1.6 we only strebm out
        // defined vblues.  So, 1.6 strebms in from b 1.5 strebm,
        // it check ebch of these vblues bnd 'undefines' it if the
        // vblue is the defbult.

        if (fRequestedAttributes != null) {
            vblues = getAttributeVblues(); // init
            AttributeVblues extrbs =
                AttributeVblues.fromSeriblizbbleHbshtbble(fRequestedAttributes);
            if (!AttributeVblues.is16Hbshtbble(fRequestedAttributes)) {
                extrbs.unsetDefbult(); // if legbcy strebm, undefine these
            }
            vblues = getAttributeVblues().merge(extrbs);
            this.nonIdentityTx = vblues.bnyNonDefbult(EXTRA_MASK);
            this.hbsLbyoutAttributes =  vblues.bnyNonDefbult(LAYOUT_MASK);

            fRequestedAttributes = null; // don't need it bny more
        }
    }

    /**
     * Returns the number of glyphs in this <code>Font</code>. Glyph codes
     * for this <code>Font</code> rbnge from 0 to
     * <code>getNumGlyphs()</code> - 1.
     * @return the number of glyphs in this <code>Font</code>.
     * @since 1.2
     */
    public int getNumGlyphs() {
        return  getFont2D().getNumGlyphs();
    }

    /**
     * Returns the glyphCode which is used when this <code>Font</code>
     * does not hbve b glyph for b specified unicode code point.
     * @return the glyphCode of this <code>Font</code>.
     * @since 1.2
     */
    public int getMissingGlyphCode() {
        return getFont2D().getMissingGlyphCode();
    }

    /**
     * Returns the bbseline bppropribte for displbying this chbrbcter.
     * <p>
     * Lbrge fonts cbn support different writing systems, bnd ebch system cbn
     * use b different bbseline.
     * The chbrbcter brgument determines the writing system to use. Clients
     * should not bssume bll chbrbcters use the sbme bbseline.
     *
     * @pbrbm c b chbrbcter used to identify the writing system
     * @return the bbseline bppropribte for the specified chbrbcter.
     * @see LineMetrics#getBbselineOffsets
     * @see #ROMAN_BASELINE
     * @see #CENTER_BASELINE
     * @see #HANGING_BASELINE
     * @since 1.2
     */
    public byte getBbselineFor(chbr c) {
        return getFont2D().getBbselineFor(c);
    }

    /**
     * Returns b mbp of font bttributes bvbilbble in this
     * <code>Font</code>.  Attributes include things like ligbtures bnd
     * glyph substitution.
     * @return the bttributes mbp of this <code>Font</code>.
     */
    public Mbp<TextAttribute,?> getAttributes(){
        return new AttributeMbp(getAttributeVblues());
    }

    /**
     * Returns the keys of bll the bttributes supported by this
     * <code>Font</code>.  These bttributes cbn be used to derive other
     * fonts.
     * @return bn brrby contbining the keys of bll the bttributes
     *          supported by this <code>Font</code>.
     * @since 1.2
     */
    public Attribute[] getAvbilbbleAttributes() {
        // FONT is not supported by Font

        Attribute bttributes[] = {
            TextAttribute.FAMILY,
            TextAttribute.WEIGHT,
            TextAttribute.WIDTH,
            TextAttribute.POSTURE,
            TextAttribute.SIZE,
            TextAttribute.TRANSFORM,
            TextAttribute.SUPERSCRIPT,
            TextAttribute.CHAR_REPLACEMENT,
            TextAttribute.FOREGROUND,
            TextAttribute.BACKGROUND,
            TextAttribute.UNDERLINE,
            TextAttribute.STRIKETHROUGH,
            TextAttribute.RUN_DIRECTION,
            TextAttribute.BIDI_EMBEDDING,
            TextAttribute.JUSTIFICATION,
            TextAttribute.INPUT_METHOD_HIGHLIGHT,
            TextAttribute.INPUT_METHOD_UNDERLINE,
            TextAttribute.SWAP_COLORS,
            TextAttribute.NUMERIC_SHAPING,
            TextAttribute.KERNING,
            TextAttribute.LIGATURES,
            TextAttribute.TRACKING,
        };

        return bttributes;
    }

    /**
     * Crebtes b new <code>Font</code> object by replicbting this
     * <code>Font</code> object bnd bpplying b new style bnd size.
     * @pbrbm style the style for the new <code>Font</code>
     * @pbrbm size the size for the new <code>Font</code>
     * @return b new <code>Font</code> object.
     * @since 1.2
     */
    public Font deriveFont(int style, flobt size){
        if (vblues == null) {
            return new Font(nbme, style, size, crebtedFont, font2DHbndle);
        }
        AttributeVblues newVblues = getAttributeVblues().clone();
        int oldStyle = (this.style != style) ? this.style : -1;
        bpplyStyle(style, newVblues);
        newVblues.setSize(size);
        return new Font(newVblues, null, oldStyle, crebtedFont, font2DHbndle);
    }

    /**
     * Crebtes b new <code>Font</code> object by replicbting this
     * <code>Font</code> object bnd bpplying b new style bnd trbnsform.
     * @pbrbm style the style for the new <code>Font</code>
     * @pbrbm trbns the <code>AffineTrbnsform</code> bssocibted with the
     * new <code>Font</code>
     * @return b new <code>Font</code> object.
     * @throws IllegblArgumentException if <code>trbns</code> is
     *         <code>null</code>
     * @since 1.2
     */
    public Font deriveFont(int style, AffineTrbnsform trbns){
        AttributeVblues newVblues = getAttributeVblues().clone();
        int oldStyle = (this.style != style) ? this.style : -1;
        bpplyStyle(style, newVblues);
        bpplyTrbnsform(trbns, newVblues);
        return new Font(newVblues, null, oldStyle, crebtedFont, font2DHbndle);
    }

    /**
     * Crebtes b new <code>Font</code> object by replicbting the current
     * <code>Font</code> object bnd bpplying b new size to it.
     * @pbrbm size the size for the new <code>Font</code>.
     * @return b new <code>Font</code> object.
     * @since 1.2
     */
    public Font deriveFont(flobt size){
        if (vblues == null) {
            return new Font(nbme, style, size, crebtedFont, font2DHbndle);
        }
        AttributeVblues newVblues = getAttributeVblues().clone();
        newVblues.setSize(size);
        return new Font(newVblues, null, -1, crebtedFont, font2DHbndle);
    }

    /**
     * Crebtes b new <code>Font</code> object by replicbting the current
     * <code>Font</code> object bnd bpplying b new trbnsform to it.
     * @pbrbm trbns the <code>AffineTrbnsform</code> bssocibted with the
     * new <code>Font</code>
     * @return b new <code>Font</code> object.
     * @throws IllegblArgumentException if <code>trbns</code> is
     *         <code>null</code>
     * @since 1.2
     */
    public Font deriveFont(AffineTrbnsform trbns){
        AttributeVblues newVblues = getAttributeVblues().clone();
        bpplyTrbnsform(trbns, newVblues);
        return new Font(newVblues, null, -1, crebtedFont, font2DHbndle);
    }

    /**
     * Crebtes b new <code>Font</code> object by replicbting the current
     * <code>Font</code> object bnd bpplying b new style to it.
     * @pbrbm style the style for the new <code>Font</code>
     * @return b new <code>Font</code> object.
     * @since 1.2
     */
    public Font deriveFont(int style){
        if (vblues == null) {
           return new Font(nbme, style, size, crebtedFont, font2DHbndle);
        }
        AttributeVblues newVblues = getAttributeVblues().clone();
        int oldStyle = (this.style != style) ? this.style : -1;
        bpplyStyle(style, newVblues);
        return new Font(newVblues, null, oldStyle, crebtedFont, font2DHbndle);
    }

    /**
     * Crebtes b new <code>Font</code> object by replicbting the current
     * <code>Font</code> object bnd bpplying b new set of font bttributes
     * to it.
     *
     * @pbrbm bttributes b mbp of bttributes enbbled for the new
     * <code>Font</code>
     * @return b new <code>Font</code> object.
     * @since 1.2
     */
    public Font deriveFont(Mbp<? extends Attribute, ?> bttributes) {
        if (bttributes == null) {
            return this;
        }
        AttributeVblues newVblues = getAttributeVblues().clone();
        newVblues.merge(bttributes, RECOGNIZED_MASK);

        return new Font(newVblues, nbme, style, crebtedFont, font2DHbndle);
    }

    /**
     * Checks if this <code>Font</code> hbs b glyph for the specified
     * chbrbcter.
     *
     * <p> <b>Note:</b> This method cbnnot hbndle <b
     * href="../../jbvb/lbng/Chbrbcter.html#supplementbry"> supplementbry
     * chbrbcters</b>. To support bll Unicode chbrbcters, including
     * supplementbry chbrbcters, use the {@link #cbnDisplby(int)}
     * method or <code>cbnDisplbyUpTo</code> methods.
     *
     * @pbrbm c the chbrbcter for which b glyph is needed
     * @return <code>true</code> if this <code>Font</code> hbs b glyph for this
     *          chbrbcter; <code>fblse</code> otherwise.
     * @since 1.2
     */
    public boolebn cbnDisplby(chbr c){
        return getFont2D().cbnDisplby(c);
    }

    /**
     * Checks if this <code>Font</code> hbs b glyph for the specified
     * chbrbcter.
     *
     * @pbrbm codePoint the chbrbcter (Unicode code point) for which b glyph
     *        is needed.
     * @return <code>true</code> if this <code>Font</code> hbs b glyph for the
     *          chbrbcter; <code>fblse</code> otherwise.
     * @throws IllegblArgumentException if the code point is not b vblid Unicode
     *          code point.
     * @see Chbrbcter#isVblidCodePoint(int)
     * @since 1.5
     */
    public boolebn cbnDisplby(int codePoint) {
        if (!Chbrbcter.isVblidCodePoint(codePoint)) {
            throw new IllegblArgumentException("invblid code point: " +
                                               Integer.toHexString(codePoint));
        }
        return getFont2D().cbnDisplby(codePoint);
    }

    /**
     * Indicbtes whether or not this <code>Font</code> cbn displby b
     * specified <code>String</code>.  For strings with Unicode encoding,
     * it is importbnt to know if b pbrticulbr font cbn displby the
     * string. This method returns bn offset into the <code>String</code>
     * <code>str</code> which is the first chbrbcter this
     * <code>Font</code> cbnnot displby without using the missing glyph
     * code. If the <code>Font</code> cbn displby bll chbrbcters, -1 is
     * returned.
     * @pbrbm str b <code>String</code> object
     * @return bn offset into <code>str</code> thbt points
     *          to the first chbrbcter in <code>str</code> thbt this
     *          <code>Font</code> cbnnot displby; or <code>-1</code> if
     *          this <code>Font</code> cbn displby bll chbrbcters in
     *          <code>str</code>.
     * @since 1.2
     */
    public int cbnDisplbyUpTo(String str) {
        Font2D font2d = getFont2D();
        int len = str.length();
        for (int i = 0; i < len; i++) {
            chbr c = str.chbrAt(i);
            if (font2d.cbnDisplby(c)) {
                continue;
            }
            if (!Chbrbcter.isHighSurrogbte(c)) {
                return i;
            }
            if (!font2d.cbnDisplby(str.codePointAt(i))) {
                return i;
            }
            i++;
        }
        return -1;
    }

    /**
     * Indicbtes whether or not this <code>Font</code> cbn displby
     * the chbrbcters in the specified <code>text</code>
     * stbrting bt <code>stbrt</code> bnd ending bt
     * <code>limit</code>.  This method is b convenience overlobd.
     * @pbrbm text the specified brrby of <code>chbr</code> vblues
     * @pbrbm stbrt the specified stbrting offset (in
     *              <code>chbr</code>s) into the specified brrby of
     *              <code>chbr</code> vblues
     * @pbrbm limit the specified ending offset (in
     *              <code>chbr</code>s) into the specified brrby of
     *              <code>chbr</code> vblues
     * @return bn offset into <code>text</code> thbt points
     *          to the first chbrbcter in <code>text</code> thbt this
     *          <code>Font</code> cbnnot displby; or <code>-1</code> if
     *          this <code>Font</code> cbn displby bll chbrbcters in
     *          <code>text</code>.
     * @since 1.2
     */
    public int cbnDisplbyUpTo(chbr[] text, int stbrt, int limit) {
        Font2D font2d = getFont2D();
        for (int i = stbrt; i < limit; i++) {
            chbr c = text[i];
            if (font2d.cbnDisplby(c)) {
                continue;
            }
            if (!Chbrbcter.isHighSurrogbte(c)) {
                return i;
            }
            if (!font2d.cbnDisplby(Chbrbcter.codePointAt(text, i, limit))) {
                return i;
            }
            i++;
        }
        return -1;
    }

    /**
     * Indicbtes whether or not this <code>Font</code> cbn displby the
     * text specified by the <code>iter</code> stbrting bt
     * <code>stbrt</code> bnd ending bt <code>limit</code>.
     *
     * @pbrbm iter  b {@link ChbrbcterIterbtor} object
     * @pbrbm stbrt the specified stbrting offset into the specified
     *              <code>ChbrbcterIterbtor</code>.
     * @pbrbm limit the specified ending offset into the specified
     *              <code>ChbrbcterIterbtor</code>.
     * @return bn offset into <code>iter</code> thbt points
     *          to the first chbrbcter in <code>iter</code> thbt this
     *          <code>Font</code> cbnnot displby; or <code>-1</code> if
     *          this <code>Font</code> cbn displby bll chbrbcters in
     *          <code>iter</code>.
     * @since 1.2
     */
    public int cbnDisplbyUpTo(ChbrbcterIterbtor iter, int stbrt, int limit) {
        Font2D font2d = getFont2D();
        chbr c = iter.setIndex(stbrt);
        for (int i = stbrt; i < limit; i++, c = iter.next()) {
            if (font2d.cbnDisplby(c)) {
                continue;
            }
            if (!Chbrbcter.isHighSurrogbte(c)) {
                return i;
            }
            chbr c2 = iter.next();
            // c2 could be ChbrbcterIterbtor.DONE which is not b low surrogbte.
            if (!Chbrbcter.isLowSurrogbte(c2)) {
                return i;
            }
            if (!font2d.cbnDisplby(Chbrbcter.toCodePoint(c, c2))) {
                return i;
            }
            i++;
        }
        return -1;
    }

    /**
     * Returns the itblic bngle of this <code>Font</code>.  The itblic bngle
     * is the inverse slope of the cbret which best mbtches the posture of this
     * <code>Font</code>.
     * @see TextAttribute#POSTURE
     * @return the bngle of the ITALIC style of this <code>Font</code>.
     */
    public flobt getItblicAngle() {
        return getItblicAngle(null);
    }

    /* The FRC hints don't bffect the vblue of the itblic bngle but
     * we need to pbss them in to look up b strike.
     * If we cbn pbss in ones blrebdy being used it cbn prevent bn extrb
     * strike from being bllocbted. Note thbt since itblic bngle is
     * b property of the font, the font trbnsform is needed not the
     * device trbnsform. Finblly, this is privbte but the only cbller of this
     * in the JDK - bnd the only likely cbller - is in this sbme clbss.
     */
    privbte flobt getItblicAngle(FontRenderContext frc) {
        Object bb, fm;
        if (frc == null) {
            bb = RenderingHints.VALUE_TEXT_ANTIALIAS_OFF;
            fm = RenderingHints.VALUE_FRACTIONALMETRICS_OFF;
        } else {
            bb = frc.getAntiAlibsingHint();
            fm = frc.getFrbctionblMetricsHint();
        }
        return getFont2D().getItblicAngle(this, identityTx, bb, fm);
    }

    /**
     * Checks whether or not this <code>Font</code> hbs uniform
     * line metrics.  A logicbl <code>Font</code> might be b
     * composite font, which mebns thbt it is composed of different
     * physicbl fonts to cover different code rbnges.  Ebch of these
     * fonts might hbve different <code>LineMetrics</code>.  If the
     * logicbl <code>Font</code> is b single
     * font then the metrics would be uniform.
     * @return <code>true</code> if this <code>Font</code> hbs
     * uniform line metrics; <code>fblse</code> otherwise.
     */
    public boolebn hbsUniformLineMetrics() {
        return fblse;   // REMIND blwbys sbfe, but prevents cbller optimize
    }

    privbte trbnsient SoftReference<FontLineMetrics> flmref;
    privbte FontLineMetrics defbultLineMetrics(FontRenderContext frc) {
        FontLineMetrics flm = null;
        if (flmref == null
            || (flm = flmref.get()) == null
            || !flm.frc.equbls(frc)) {

            /* The device trbnsform in the frc is not used in obtbining line
             * metrics, blthough it probbbly should be: REMIND find why not?
             * The font trbnsform is used but its bpplied in getFontMetrics, so
             * just pbss identity here
             */
            flobt [] metrics = new flobt[8];
            getFont2D().getFontMetrics(this, identityTx,
                                       frc.getAntiAlibsingHint(),
                                       frc.getFrbctionblMetricsHint(),
                                       metrics);
            flobt bscent  = metrics[0];
            flobt descent = metrics[1];
            flobt lebding = metrics[2];
            flobt ssOffset = 0;
            if (vblues != null && vblues.getSuperscript() != 0) {
                ssOffset = (flobt)getTrbnsform().getTrbnslbteY();
                bscent -= ssOffset;
                descent += ssOffset;
            }
            flobt height = bscent + descent + lebding;

            int bbselineIndex = 0; // need rebl index, bssumes rombn for everything
            // need rebl bbselines eventublly
            flobt[] bbselineOffsets = { 0, (descent/2f - bscent) / 2f, -bscent };

            flobt strikethroughOffset = metrics[4];
            flobt strikethroughThickness = metrics[5];

            flobt underlineOffset = metrics[6];
            flobt underlineThickness = metrics[7];

            flobt itblicAngle = getItblicAngle(frc);

            if (isTrbnsformed()) {
                AffineTrbnsform ctx = vblues.getChbrTrbnsform(); // extrbct rotbtion
                if (ctx != null) {
                    Point2D.Flobt pt = new Point2D.Flobt();
                    pt.setLocbtion(0, strikethroughOffset);
                    ctx.deltbTrbnsform(pt, pt);
                    strikethroughOffset = pt.y;
                    pt.setLocbtion(0, strikethroughThickness);
                    ctx.deltbTrbnsform(pt, pt);
                    strikethroughThickness = pt.y;
                    pt.setLocbtion(0, underlineOffset);
                    ctx.deltbTrbnsform(pt, pt);
                    underlineOffset = pt.y;
                    pt.setLocbtion(0, underlineThickness);
                    ctx.deltbTrbnsform(pt, pt);
                    underlineThickness = pt.y;
                }
            }
            strikethroughOffset += ssOffset;
            underlineOffset += ssOffset;

            CoreMetrics cm = new CoreMetrics(bscent, descent, lebding, height,
                                             bbselineIndex, bbselineOffsets,
                                             strikethroughOffset, strikethroughThickness,
                                             underlineOffset, underlineThickness,
                                             ssOffset, itblicAngle);

            flm = new FontLineMetrics(0, cm, frc);
            flmref = new SoftReference<FontLineMetrics>(flm);
        }

        return (FontLineMetrics)flm.clone();
    }

    /**
     * Returns b {@link LineMetrics} object crebted with the specified
     * <code>String</code> bnd {@link FontRenderContext}.
     * @pbrbm str the specified <code>String</code>
     * @pbrbm frc the specified <code>FontRenderContext</code>
     * @return b <code>LineMetrics</code> object crebted with the
     * specified <code>String</code> bnd {@link FontRenderContext}.
     */
    public LineMetrics getLineMetrics( String str, FontRenderContext frc) {
        FontLineMetrics flm = defbultLineMetrics(frc);
        flm.numchbrs = str.length();
        return flm;
    }

    /**
     * Returns b <code>LineMetrics</code> object crebted with the
     * specified brguments.
     * @pbrbm str the specified <code>String</code>
     * @pbrbm beginIndex the initibl offset of <code>str</code>
     * @pbrbm limit the end offset of <code>str</code>
     * @pbrbm frc the specified <code>FontRenderContext</code>
     * @return b <code>LineMetrics</code> object crebted with the
     * specified brguments.
     */
    public LineMetrics getLineMetrics( String str,
                                    int beginIndex, int limit,
                                    FontRenderContext frc) {
        FontLineMetrics flm = defbultLineMetrics(frc);
        int numChbrs = limit - beginIndex;
        flm.numchbrs = (numChbrs < 0)? 0: numChbrs;
        return flm;
    }

    /**
     * Returns b <code>LineMetrics</code> object crebted with the
     * specified brguments.
     * @pbrbm chbrs bn brrby of chbrbcters
     * @pbrbm beginIndex the initibl offset of <code>chbrs</code>
     * @pbrbm limit the end offset of <code>chbrs</code>
     * @pbrbm frc the specified <code>FontRenderContext</code>
     * @return b <code>LineMetrics</code> object crebted with the
     * specified brguments.
     */
    public LineMetrics getLineMetrics(chbr [] chbrs,
                                    int beginIndex, int limit,
                                    FontRenderContext frc) {
        FontLineMetrics flm = defbultLineMetrics(frc);
        int numChbrs = limit - beginIndex;
        flm.numchbrs = (numChbrs < 0)? 0: numChbrs;
        return flm;
    }

    /**
     * Returns b <code>LineMetrics</code> object crebted with the
     * specified brguments.
     * @pbrbm ci the specified <code>ChbrbcterIterbtor</code>
     * @pbrbm beginIndex the initibl offset in <code>ci</code>
     * @pbrbm limit the end offset of <code>ci</code>
     * @pbrbm frc the specified <code>FontRenderContext</code>
     * @return b <code>LineMetrics</code> object crebted with the
     * specified brguments.
     */
    public LineMetrics getLineMetrics(ChbrbcterIterbtor ci,
                                    int beginIndex, int limit,
                                    FontRenderContext frc) {
        FontLineMetrics flm = defbultLineMetrics(frc);
        int numChbrs = limit - beginIndex;
        flm.numchbrs = (numChbrs < 0)? 0: numChbrs;
        return flm;
    }

    /**
     * Returns the logicbl bounds of the specified <code>String</code> in
     * the specified <code>FontRenderContext</code>.  The logicbl bounds
     * contbins the origin, bscent, bdvbnce, bnd height, which includes
     * the lebding.  The logicbl bounds does not blwbys enclose bll the
     * text.  For exbmple, in some lbngubges bnd in some fonts, bccent
     * mbrks cbn be positioned bbove the bscent or below the descent.
     * To obtbin b visubl bounding box, which encloses bll the text,
     * use the {@link TextLbyout#getBounds() getBounds} method of
     * <code>TextLbyout</code>.
     * <p>Note: The returned bounds is in bbseline-relbtive coordinbtes
     * (see {@link jbvb.bwt.Font clbss notes}).
     * @pbrbm str the specified <code>String</code>
     * @pbrbm frc the specified <code>FontRenderContext</code>
     * @return b {@link Rectbngle2D} thbt is the bounding box of the
     * specified <code>String</code> in the specified
     * <code>FontRenderContext</code>.
     * @see FontRenderContext
     * @see Font#crebteGlyphVector
     * @since 1.2
     */
    public Rectbngle2D getStringBounds( String str, FontRenderContext frc) {
        chbr[] brrby = str.toChbrArrby();
        return getStringBounds(brrby, 0, brrby.length, frc);
    }

   /**
     * Returns the logicbl bounds of the specified <code>String</code> in
     * the specified <code>FontRenderContext</code>.  The logicbl bounds
     * contbins the origin, bscent, bdvbnce, bnd height, which includes
     * the lebding.  The logicbl bounds does not blwbys enclose bll the
     * text.  For exbmple, in some lbngubges bnd in some fonts, bccent
     * mbrks cbn be positioned bbove the bscent or below the descent.
     * To obtbin b visubl bounding box, which encloses bll the text,
     * use the {@link TextLbyout#getBounds() getBounds} method of
     * <code>TextLbyout</code>.
     * <p>Note: The returned bounds is in bbseline-relbtive coordinbtes
     * (see {@link jbvb.bwt.Font clbss notes}).
     * @pbrbm str the specified <code>String</code>
     * @pbrbm beginIndex the initibl offset of <code>str</code>
     * @pbrbm limit the end offset of <code>str</code>
     * @pbrbm frc the specified <code>FontRenderContext</code>
     * @return b <code>Rectbngle2D</code> thbt is the bounding box of the
     * specified <code>String</code> in the specified
     * <code>FontRenderContext</code>.
     * @throws IndexOutOfBoundsException if <code>beginIndex</code> is
     *         less thbn zero, or <code>limit</code> is grebter thbn the
     *         length of <code>str</code>, or <code>beginIndex</code>
     *         is grebter thbn <code>limit</code>.
     * @see FontRenderContext
     * @see Font#crebteGlyphVector
     * @since 1.2
     */
    public Rectbngle2D getStringBounds( String str,
                                    int beginIndex, int limit,
                                        FontRenderContext frc) {
        String substr = str.substring(beginIndex, limit);
        return getStringBounds(substr, frc);
    }

   /**
     * Returns the logicbl bounds of the specified brrby of chbrbcters
     * in the specified <code>FontRenderContext</code>.  The logicbl
     * bounds contbins the origin, bscent, bdvbnce, bnd height, which
     * includes the lebding.  The logicbl bounds does not blwbys enclose
     * bll the text.  For exbmple, in some lbngubges bnd in some fonts,
     * bccent mbrks cbn be positioned bbove the bscent or below the
     * descent.  To obtbin b visubl bounding box, which encloses bll the
     * text, use the {@link TextLbyout#getBounds() getBounds} method of
     * <code>TextLbyout</code>.
     * <p>Note: The returned bounds is in bbseline-relbtive coordinbtes
     * (see {@link jbvb.bwt.Font clbss notes}).
     * @pbrbm chbrs bn brrby of chbrbcters
     * @pbrbm beginIndex the initibl offset in the brrby of
     * chbrbcters
     * @pbrbm limit the end offset in the brrby of chbrbcters
     * @pbrbm frc the specified <code>FontRenderContext</code>
     * @return b <code>Rectbngle2D</code> thbt is the bounding box of the
     * specified brrby of chbrbcters in the specified
     * <code>FontRenderContext</code>.
     * @throws IndexOutOfBoundsException if <code>beginIndex</code> is
     *         less thbn zero, or <code>limit</code> is grebter thbn the
     *         length of <code>chbrs</code>, or <code>beginIndex</code>
     *         is grebter thbn <code>limit</code>.
     * @see FontRenderContext
     * @see Font#crebteGlyphVector
     * @since 1.2
     */
    public Rectbngle2D getStringBounds(chbr [] chbrs,
                                    int beginIndex, int limit,
                                       FontRenderContext frc) {
        if (beginIndex < 0) {
            throw new IndexOutOfBoundsException("beginIndex: " + beginIndex);
        }
        if (limit > chbrs.length) {
            throw new IndexOutOfBoundsException("limit: " + limit);
        }
        if (beginIndex > limit) {
            throw new IndexOutOfBoundsException("rbnge length: " +
                                                (limit - beginIndex));
        }

        // this code should be in textlbyout
        // quick check for simple text, bssume GV ok to use if simple

        boolebn simple = vblues == null ||
            (vblues.getKerning() == 0 && vblues.getLigbtures() == 0 &&
              vblues.getBbselineTrbnsform() == null);
        if (simple) {
            simple = ! FontUtilities.isComplexText(chbrs, beginIndex, limit);
        }

        if (simple) {
            GlyphVector gv = new StbndbrdGlyphVector(this, chbrs, beginIndex,
                                                     limit - beginIndex, frc);
            return gv.getLogicblBounds();
        } else {
            // need chbr brrby constructor on textlbyout
            String str = new String(chbrs, beginIndex, limit - beginIndex);
            TextLbyout tl = new TextLbyout(str, this, frc);
            return new Rectbngle2D.Flobt(0, -tl.getAscent(), tl.getAdvbnce(),
                                         tl.getAscent() + tl.getDescent() +
                                         tl.getLebding());
        }
    }

   /**
     * Returns the logicbl bounds of the chbrbcters indexed in the
     * specified {@link ChbrbcterIterbtor} in the
     * specified <code>FontRenderContext</code>.  The logicbl bounds
     * contbins the origin, bscent, bdvbnce, bnd height, which includes
     * the lebding.  The logicbl bounds does not blwbys enclose bll the
     * text.  For exbmple, in some lbngubges bnd in some fonts, bccent
     * mbrks cbn be positioned bbove the bscent or below the descent.
     * To obtbin b visubl bounding box, which encloses bll the text,
     * use the {@link TextLbyout#getBounds() getBounds} method of
     * <code>TextLbyout</code>.
     * <p>Note: The returned bounds is in bbseline-relbtive coordinbtes
     * (see {@link jbvb.bwt.Font clbss notes}).
     * @pbrbm ci the specified <code>ChbrbcterIterbtor</code>
     * @pbrbm beginIndex the initibl offset in <code>ci</code>
     * @pbrbm limit the end offset in <code>ci</code>
     * @pbrbm frc the specified <code>FontRenderContext</code>
     * @return b <code>Rectbngle2D</code> thbt is the bounding box of the
     * chbrbcters indexed in the specified <code>ChbrbcterIterbtor</code>
     * in the specified <code>FontRenderContext</code>.
     * @see FontRenderContext
     * @see Font#crebteGlyphVector
     * @since 1.2
     * @throws IndexOutOfBoundsException if <code>beginIndex</code> is
     *         less thbn the stbrt index of <code>ci</code>, or
     *         <code>limit</code> is grebter thbn the end index of
     *         <code>ci</code>, or <code>beginIndex</code> is grebter
     *         thbn <code>limit</code>
     */
    public Rectbngle2D getStringBounds(ChbrbcterIterbtor ci,
                                    int beginIndex, int limit,
                                       FontRenderContext frc) {
        int stbrt = ci.getBeginIndex();
        int end = ci.getEndIndex();

        if (beginIndex < stbrt) {
            throw new IndexOutOfBoundsException("beginIndex: " + beginIndex);
        }
        if (limit > end) {
            throw new IndexOutOfBoundsException("limit: " + limit);
        }
        if (beginIndex > limit) {
            throw new IndexOutOfBoundsException("rbnge length: " +
                                                (limit - beginIndex));
        }

        chbr[]  brr = new chbr[limit - beginIndex];

        ci.setIndex(beginIndex);
        for(int idx = 0; idx < brr.length; idx++) {
            brr[idx] = ci.current();
            ci.next();
        }

        return getStringBounds(brr,0,brr.length,frc);
    }

    /**
     * Returns the bounds for the chbrbcter with the mbximum
     * bounds bs defined in the specified <code>FontRenderContext</code>.
     * <p>Note: The returned bounds is in bbseline-relbtive coordinbtes
     * (see {@link jbvb.bwt.Font clbss notes}).
     * @pbrbm frc the specified <code>FontRenderContext</code>
     * @return b <code>Rectbngle2D</code> thbt is the bounding box
     * for the chbrbcter with the mbximum bounds.
     */
    public Rectbngle2D getMbxChbrBounds(FontRenderContext frc) {
        flobt [] metrics = new flobt[4];

        getFont2D().getFontMetrics(this, frc, metrics);

        return new Rectbngle2D.Flobt(0, -metrics[0],
                                metrics[3],
                                metrics[0] + metrics[1] + metrics[2]);
    }

    /**
     * Crebtes b {@link jbvb.bwt.font.GlyphVector GlyphVector} by
     * mbpping chbrbcters to glyphs one-to-one bbsed on the
     * Unicode cmbp in this <code>Font</code>.  This method does no other
     * processing besides the mbpping of glyphs to chbrbcters.  This
     * mebns thbt this method is not useful for some scripts, such
     * bs Arbbic, Hebrew, Thbi, bnd Indic, thbt require reordering,
     * shbping, or ligbture substitution.
     * @pbrbm frc the specified <code>FontRenderContext</code>
     * @pbrbm str the specified <code>String</code>
     * @return b new <code>GlyphVector</code> crebted with the
     * specified <code>String</code> bnd the specified
     * <code>FontRenderContext</code>.
     */
    public GlyphVector crebteGlyphVector(FontRenderContext frc, String str)
    {
        return (GlyphVector)new StbndbrdGlyphVector(this, str, frc);
    }

    /**
     * Crebtes b {@link jbvb.bwt.font.GlyphVector GlyphVector} by
     * mbpping chbrbcters to glyphs one-to-one bbsed on the
     * Unicode cmbp in this <code>Font</code>.  This method does no other
     * processing besides the mbpping of glyphs to chbrbcters.  This
     * mebns thbt this method is not useful for some scripts, such
     * bs Arbbic, Hebrew, Thbi, bnd Indic, thbt require reordering,
     * shbping, or ligbture substitution.
     * @pbrbm frc the specified <code>FontRenderContext</code>
     * @pbrbm chbrs the specified brrby of chbrbcters
     * @return b new <code>GlyphVector</code> crebted with the
     * specified brrby of chbrbcters bnd the specified
     * <code>FontRenderContext</code>.
     */
    public GlyphVector crebteGlyphVector(FontRenderContext frc, chbr[] chbrs)
    {
        return (GlyphVector)new StbndbrdGlyphVector(this, chbrs, frc);
    }

    /**
     * Crebtes b {@link jbvb.bwt.font.GlyphVector GlyphVector} by
     * mbpping the specified chbrbcters to glyphs one-to-one bbsed on the
     * Unicode cmbp in this <code>Font</code>.  This method does no other
     * processing besides the mbpping of glyphs to chbrbcters.  This
     * mebns thbt this method is not useful for some scripts, such
     * bs Arbbic, Hebrew, Thbi, bnd Indic, thbt require reordering,
     * shbping, or ligbture substitution.
     * @pbrbm frc the specified <code>FontRenderContext</code>
     * @pbrbm ci the specified <code>ChbrbcterIterbtor</code>
     * @return b new <code>GlyphVector</code> crebted with the
     * specified <code>ChbrbcterIterbtor</code> bnd the specified
     * <code>FontRenderContext</code>.
     */
    public GlyphVector crebteGlyphVector(   FontRenderContext frc,
                                            ChbrbcterIterbtor ci)
    {
        return (GlyphVector)new StbndbrdGlyphVector(this, ci, frc);
    }

    /**
     * Crebtes b {@link jbvb.bwt.font.GlyphVector GlyphVector} by
     * mbpping chbrbcters to glyphs one-to-one bbsed on the
     * Unicode cmbp in this <code>Font</code>.  This method does no other
     * processing besides the mbpping of glyphs to chbrbcters.  This
     * mebns thbt this method is not useful for some scripts, such
     * bs Arbbic, Hebrew, Thbi, bnd Indic, thbt require reordering,
     * shbping, or ligbture substitution.
     * @pbrbm frc the specified <code>FontRenderContext</code>
     * @pbrbm glyphCodes the specified integer brrby
     * @return b new <code>GlyphVector</code> crebted with the
     * specified integer brrby bnd the specified
     * <code>FontRenderContext</code>.
     */
    public GlyphVector crebteGlyphVector(   FontRenderContext frc,
                                            int [] glyphCodes)
    {
        return (GlyphVector)new StbndbrdGlyphVector(this, glyphCodes, frc);
    }

    /**
     * Returns b new <code>GlyphVector</code> object, performing full
     * lbyout of the text if possible.  Full lbyout is required for
     * complex text, such bs Arbbic or Hindi.  Support for different
     * scripts depends on the font bnd implementbtion.
     * <p>
     * Lbyout requires bidi bnblysis, bs performed by
     * <code>Bidi</code>, bnd should only be performed on text thbt
     * hbs b uniform direction.  The direction is indicbted in the
     * flbgs pbrbmeter,by using LAYOUT_RIGHT_TO_LEFT to indicbte b
     * right-to-left (Arbbic bnd Hebrew) run direction, or
     * LAYOUT_LEFT_TO_RIGHT to indicbte b left-to-right (English)
     * run direction.
     * <p>
     * In bddition, some operbtions, such bs Arbbic shbping, require
     * context, so thbt the chbrbcters bt the stbrt bnd limit cbn hbve
     * the proper shbpes.  Sometimes the dbtb in the buffer outside
     * the provided rbnge does not hbve vblid dbtb.  The vblues
     * LAYOUT_NO_START_CONTEXT bnd LAYOUT_NO_LIMIT_CONTEXT cbn be
     * bdded to the flbgs pbrbmeter to indicbte thbt the text before
     * stbrt, or bfter limit, respectively, should not be exbmined
     * for context.
     * <p>
     * All other vblues for the flbgs pbrbmeter bre reserved.
     *
     * @pbrbm frc the specified <code>FontRenderContext</code>
     * @pbrbm text the text to lbyout
     * @pbrbm stbrt the stbrt of the text to use for the <code>GlyphVector</code>
     * @pbrbm limit the limit of the text to use for the <code>GlyphVector</code>
     * @pbrbm flbgs control flbgs bs described bbove
     * @return b new <code>GlyphVector</code> representing the text between
     * stbrt bnd limit, with glyphs chosen bnd positioned so bs to best represent
     * the text
     * @throws ArrbyIndexOutOfBoundsException if stbrt or limit is
     * out of bounds
     * @see jbvb.text.Bidi
     * @see #LAYOUT_LEFT_TO_RIGHT
     * @see #LAYOUT_RIGHT_TO_LEFT
     * @see #LAYOUT_NO_START_CONTEXT
     * @see #LAYOUT_NO_LIMIT_CONTEXT
     * @since 1.4
     */
    public GlyphVector lbyoutGlyphVector(FontRenderContext frc,
                                         chbr[] text,
                                         int stbrt,
                                         int limit,
                                         int flbgs) {

        GlyphLbyout gl = GlyphLbyout.get(null); // !!! no custom lbyout engines
        StbndbrdGlyphVector gv = gl.lbyout(this, frc, text,
                                           stbrt, limit-stbrt, flbgs, null);
        GlyphLbyout.done(gl);
        return gv;
    }

    /**
     * A flbg to lbyoutGlyphVector indicbting thbt text is left-to-right bs
     * determined by Bidi bnblysis.
     */
    public stbtic finbl int LAYOUT_LEFT_TO_RIGHT = 0;

    /**
     * A flbg to lbyoutGlyphVector indicbting thbt text is right-to-left bs
     * determined by Bidi bnblysis.
     */
    public stbtic finbl int LAYOUT_RIGHT_TO_LEFT = 1;

    /**
     * A flbg to lbyoutGlyphVector indicbting thbt text in the chbr brrby
     * before the indicbted stbrt should not be exbmined.
     */
    public stbtic finbl int LAYOUT_NO_START_CONTEXT = 2;

    /**
     * A flbg to lbyoutGlyphVector indicbting thbt text in the chbr brrby
     * bfter the indicbted limit should not be exbmined.
     */
    public stbtic finbl int LAYOUT_NO_LIMIT_CONTEXT = 4;


    privbte stbtic void bpplyTrbnsform(AffineTrbnsform trbns, AttributeVblues vblues) {
        if (trbns == null) {
            throw new IllegblArgumentException("trbnsform must not be null");
        }
        vblues.setTrbnsform(trbns);
    }

    privbte stbtic void bpplyStyle(int style, AttributeVblues vblues) {
        // WEIGHT_BOLD, WEIGHT_REGULAR
        vblues.setWeight((style & BOLD) != 0 ? 2f : 1f);
        // POSTURE_OBLIQUE, POSTURE_REGULAR
        vblues.setPosture((style & ITALIC) != 0 ? .2f : 0f);
    }

    /*
     * Initiblize JNI field bnd method IDs
     */
    privbte stbtic nbtive void initIDs();
}
