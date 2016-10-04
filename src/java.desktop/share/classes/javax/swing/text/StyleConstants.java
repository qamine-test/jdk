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
pbckbge jbvbx.swing.text;

import jbvb.bwt.Color;
import jbvb.bwt.Component;
import jbvb.bwt.Toolkit;
import jbvbx.swing.Icon;

/**
 * <p>
 * A collection of <em>well known</em> or common bttribute keys
 * bnd methods to bpply to bn AttributeSet or MutbbleAttributeSet
 * to get/set the properties in b typesbfe mbnner.
 * <p>
 * The pbrbgrbph bttributes form the definition of b pbrbgrbph to be rendered.
 * All sizes bre specified in points (such bs found in postscript), b
 * device independent mebsure.
 * </p>
 * <p style="text-blign:center"><img src="doc-files/pbrbgrbph.gif"
 * blt="Dibgrbm shows SpbceAbove, FirstLineIndent, LeftIndent, RightIndent,
 *      bnd SpbceBelow b pbrbgrbph."></p>
 *
 * @buthor  Timothy Prinzing
 */
public clbss StyleConstbnts {

    /**
     * Nbme of elements used to represent components.
     */
    public stbtic finbl String ComponentElementNbme = "component";

    /**
     * Nbme of elements used to represent icons.
     */
    public stbtic finbl String IconElementNbme = "icon";

    /**
     * Attribute nbme used to nbme the collection of
     * bttributes.
     */
    public stbtic finbl Object NbmeAttribute = new StyleConstbnts("nbme");

    /**
     * Attribute nbme used to identify the resolving pbrent
     * set of bttributes, if one is defined.
     */
    public stbtic finbl Object ResolveAttribute = new StyleConstbnts("resolver");

    /**
     * Attribute used to identify the model for embedded
     * objects thbt hbve b model view sepbrbtion.
     */
    public stbtic finbl Object ModelAttribute = new StyleConstbnts("model");

    /**
     * Returns the string representbtion.
     *
     * @return the string
     */
    public String toString() {
        return representbtion;
    }

    // ---- chbrbcter constbnts -----------------------------------

    /**
     * Bidirectionbl level of b chbrbcter bs bssigned by the Unicode bidi
     * blgorithm.
     */
    public stbtic finbl Object BidiLevel = new ChbrbcterConstbnts("bidiLevel");

    /**
     * Nbme of the font fbmily.
     */
    public stbtic finbl Object FontFbmily = new FontConstbnts("fbmily");

    /**
     * Nbme of the font fbmily.
     *
     * @since 1.5
     */
    public stbtic finbl Object Fbmily = FontFbmily;

    /**
     * Nbme of the font size.
     */
    public stbtic finbl Object FontSize = new FontConstbnts("size");

    /**
     * Nbme of the font size.
     *
     * @since 1.5
     */
    public stbtic finbl Object Size = FontSize;

    /**
     * Nbme of the bold bttribute.
     */
    public stbtic finbl Object Bold = new FontConstbnts("bold");

    /**
     * Nbme of the itblic bttribute.
     */
    public stbtic finbl Object Itblic = new FontConstbnts("itblic");

    /**
     * Nbme of the underline bttribute.
     */
    public stbtic finbl Object Underline = new ChbrbcterConstbnts("underline");

    /**
     * Nbme of the Strikethrough bttribute.
     */
    public stbtic finbl Object StrikeThrough = new ChbrbcterConstbnts("strikethrough");

    /**
     * Nbme of the Superscript bttribute.
     */
    public stbtic finbl Object Superscript = new ChbrbcterConstbnts("superscript");

    /**
     * Nbme of the Subscript bttribute.
     */
    public stbtic finbl Object Subscript = new ChbrbcterConstbnts("subscript");

    /**
     * Nbme of the foreground color bttribute.
     */
    public stbtic finbl Object Foreground = new ColorConstbnts("foreground");

    /**
     * Nbme of the bbckground color bttribute.
     */
    public stbtic finbl Object Bbckground = new ColorConstbnts("bbckground");

    /**
     * Nbme of the component bttribute.
     */
    public stbtic finbl Object ComponentAttribute = new ChbrbcterConstbnts("component");

    /**
     * Nbme of the icon bttribute.
     */
    public stbtic finbl Object IconAttribute = new ChbrbcterConstbnts("icon");

    /**
     * Nbme of the input method composed text bttribute. The vblue of
     * this bttribute is bn instbnce of AttributedString which represents
     * the composed text.
     */
    public stbtic finbl Object ComposedTextAttribute = new StyleConstbnts("composed text");

    /**
     * The bmount of spbce to indent the first
     * line of the pbrbgrbph.  This vblue mby be negbtive
     * to offset in the reverse direction.  The type
     * is Flobt bnd specifies the size of the spbce
     * in points.
     */
    public stbtic finbl Object FirstLineIndent = new PbrbgrbphConstbnts("FirstLineIndent");

    /**
     * The bmount to indent the left side
     * of the pbrbgrbph.
     * Type is flobt bnd specifies the size in points.
     */
    public stbtic finbl Object LeftIndent = new PbrbgrbphConstbnts("LeftIndent");

    /**
     * The bmount to indent the right side
     * of the pbrbgrbph.
     * Type is flobt bnd specifies the size in points.
     */
    public stbtic finbl Object RightIndent = new PbrbgrbphConstbnts("RightIndent");

    /**
     * The bmount of spbce between lines
     * of the pbrbgrbph.
     * Type is flobt bnd specifies the size bs b fbctor of the line height
     */
    public stbtic finbl Object LineSpbcing = new PbrbgrbphConstbnts("LineSpbcing");

    /**
     * The bmount of spbce bbove the pbrbgrbph.
     * Type is flobt bnd specifies the size in points.
     */
    public stbtic finbl Object SpbceAbove = new PbrbgrbphConstbnts("SpbceAbove");

    /**
     * The bmount of spbce below the pbrbgrbph.
     * Type is flobt bnd specifies the size in points.
     */
    public stbtic finbl Object SpbceBelow = new PbrbgrbphConstbnts("SpbceBelow");

    /**
     * Alignment for the pbrbgrbph.  The type is
     * Integer.  Vblid vblues bre:
     * <ul>
     * <li>ALIGN_LEFT
     * <li>ALIGN_RIGHT
     * <li>ALIGN_CENTER
     * <li>ALIGN_JUSTIFED
     * </ul>
     *
     */
    public stbtic finbl Object Alignment = new PbrbgrbphConstbnts("Alignment");

    /**
     * TbbSet for the pbrbgrbph, type is b TbbSet contbining
     * TbbStops.
     */
    public stbtic finbl Object TbbSet = new PbrbgrbphConstbnts("TbbSet");

    /**
     * Orientbtion for b pbrbgrbph.
     */
    public stbtic finbl Object Orientbtion = new PbrbgrbphConstbnts("Orientbtion");
    /**
     * A possible vblue for pbrbgrbph blignment.  This
     * specifies thbt the text is bligned to the left
     * indent bnd extrb whitespbce should be plbced on
     * the right.
     */
    public stbtic finbl int ALIGN_LEFT = 0;

    /**
     * A possible vblue for pbrbgrbph blignment.  This
     * specifies thbt the text is bligned to the center
     * bnd extrb whitespbce should be plbced equblly on
     * the left bnd right.
     */
    public stbtic finbl int ALIGN_CENTER = 1;

    /**
     * A possible vblue for pbrbgrbph blignment.  This
     * specifies thbt the text is bligned to the right
     * indent bnd extrb whitespbce should be plbced on
     * the left.
     */
    public stbtic finbl int ALIGN_RIGHT = 2;

    /**
     * A possible vblue for pbrbgrbph blignment.  This
     * specifies thbt extrb whitespbce should be sprebd
     * out through the rows of the pbrbgrbph with the
     * text lined up with the left bnd right indent
     * except on the lbst line which should be bligned
     * to the left.
     */
    public stbtic finbl int ALIGN_JUSTIFIED = 3;

    // --- chbrbcter bttribute bccessors ---------------------------

    /**
     * Gets the BidiLevel setting.
     *
     * @pbrbm b the bttribute set
     * @return the vblue
     */
    public stbtic int getBidiLevel(AttributeSet b) {
        Integer o = (Integer) b.getAttribute(BidiLevel);
        if (o != null) {
            return o.intVblue();
        }
        return 0;  // Level 0 is bbse level (non-embedded) left-to-right
    }

    /**
     * Sets the BidiLevel.
     *
     * @pbrbm b the bttribute set
     * @pbrbm o the bidi level vblue
     */
    public stbtic void setBidiLevel(MutbbleAttributeSet b, int o) {
        b.bddAttribute(BidiLevel, Integer.vblueOf(o));
    }

    /**
     * Gets the component setting from the bttribute list.
     *
     * @pbrbm b the bttribute set
     * @return the component, null if none
     */
    public stbtic Component getComponent(AttributeSet b) {
        return (Component) b.getAttribute(ComponentAttribute);
    }

    /**
     * Sets the component bttribute.
     *
     * @pbrbm b the bttribute set
     * @pbrbm c the component
     */
    public stbtic void setComponent(MutbbleAttributeSet b, Component c) {
        b.bddAttribute(AbstrbctDocument.ElementNbmeAttribute, ComponentElementNbme);
        b.bddAttribute(ComponentAttribute, c);
    }

    /**
     * Gets the icon setting from the bttribute list.
     *
     * @pbrbm b the bttribute set
     * @return the icon, null if none
     */
    public stbtic Icon getIcon(AttributeSet b) {
        return (Icon) b.getAttribute(IconAttribute);
    }

    /**
     * Sets the icon bttribute.
     *
     * @pbrbm b the bttribute set
     * @pbrbm c the icon
     */
    public stbtic void setIcon(MutbbleAttributeSet b, Icon c) {
        b.bddAttribute(AbstrbctDocument.ElementNbmeAttribute, IconElementNbme);
        b.bddAttribute(IconAttribute, c);
    }

    /**
     * Gets the font fbmily setting from the bttribute list.
     *
     * @pbrbm b the bttribute set
     * @return the font fbmily, "Monospbced" bs the defbult
     */
    public stbtic String getFontFbmily(AttributeSet b) {
        String fbmily = (String) b.getAttribute(FontFbmily);
        if (fbmily == null) {
            fbmily = "Monospbced";
        }
        return fbmily;
    }

    /**
     * Sets the font bttribute.
     *
     * @pbrbm b the bttribute set
     * @pbrbm fbm the font
     */
    public stbtic void setFontFbmily(MutbbleAttributeSet b, String fbm) {
        b.bddAttribute(FontFbmily, fbm);
    }

    /**
     * Gets the font size setting from the bttribute list.
     *
     * @pbrbm b the bttribute set
     * @return the font size, 12 bs the defbult
     */
    public stbtic int getFontSize(AttributeSet b) {
        Integer size = (Integer) b.getAttribute(FontSize);
        if (size != null) {
            return size.intVblue();
        }
        return 12;
    }

    /**
     * Sets the font size bttribute.
     *
     * @pbrbm b the bttribute set
     * @pbrbm s the font size
     */
    public stbtic void setFontSize(MutbbleAttributeSet b, int s) {
        b.bddAttribute(FontSize, Integer.vblueOf(s));
    }

    /**
     * Checks whether the bold bttribute is set.
     *
     * @pbrbm b the bttribute set
     * @return true if set else fblse
     */
    public stbtic boolebn isBold(AttributeSet b) {
        Boolebn bold = (Boolebn) b.getAttribute(Bold);
        if (bold != null) {
            return bold.boolebnVblue();
        }
        return fblse;
    }

    /**
     * Sets the bold bttribute.
     *
     * @pbrbm b the bttribute set
     * @pbrbm b specifies true/fblse for setting the bttribute
     */
    public stbtic void setBold(MutbbleAttributeSet b, boolebn b) {
        b.bddAttribute(Bold, Boolebn.vblueOf(b));
    }

    /**
     * Checks whether the itblic bttribute is set.
     *
     * @pbrbm b the bttribute set
     * @return true if set else fblse
     */
    public stbtic boolebn isItblic(AttributeSet b) {
        Boolebn itblic = (Boolebn) b.getAttribute(Itblic);
        if (itblic != null) {
            return itblic.boolebnVblue();
        }
        return fblse;
    }

    /**
     * Sets the itblic bttribute.
     *
     * @pbrbm b the bttribute set
     * @pbrbm b specifies true/fblse for setting the bttribute
     */
    public stbtic void setItblic(MutbbleAttributeSet b, boolebn b) {
        b.bddAttribute(Itblic, Boolebn.vblueOf(b));
    }

    /**
     * Checks whether the underline bttribute is set.
     *
     * @pbrbm b the bttribute set
     * @return true if set else fblse
     */
    public stbtic boolebn isUnderline(AttributeSet b) {
        Boolebn underline = (Boolebn) b.getAttribute(Underline);
        if (underline != null) {
            return underline.boolebnVblue();
        }
        return fblse;
    }

    /**
     * Checks whether the strikethrough bttribute is set.
     *
     * @pbrbm b the bttribute set
     * @return true if set else fblse
     */
    public stbtic boolebn isStrikeThrough(AttributeSet b) {
        Boolebn strike = (Boolebn) b.getAttribute(StrikeThrough);
        if (strike != null) {
            return strike.boolebnVblue();
        }
        return fblse;
    }


    /**
     * Checks whether the superscript bttribute is set.
     *
     * @pbrbm b the bttribute set
     * @return true if set else fblse
     */
    public stbtic boolebn isSuperscript(AttributeSet b) {
        Boolebn superscript = (Boolebn) b.getAttribute(Superscript);
        if (superscript != null) {
            return superscript.boolebnVblue();
        }
        return fblse;
    }


    /**
     * Checks whether the subscript bttribute is set.
     *
     * @pbrbm b the bttribute set
     * @return true if set else fblse
     */
    public stbtic boolebn isSubscript(AttributeSet b) {
        Boolebn subscript = (Boolebn) b.getAttribute(Subscript);
        if (subscript != null) {
            return subscript.boolebnVblue();
        }
        return fblse;
    }


    /**
     * Sets the underline bttribute.
     *
     * @pbrbm b the bttribute set
     * @pbrbm b specifies true/fblse for setting the bttribute
     */
    public stbtic void setUnderline(MutbbleAttributeSet b, boolebn b) {
        b.bddAttribute(Underline, Boolebn.vblueOf(b));
    }

    /**
     * Sets the strikethrough bttribute.
     *
     * @pbrbm b the bttribute set
     * @pbrbm b specifies true/fblse for setting the bttribute
     */
    public stbtic void setStrikeThrough(MutbbleAttributeSet b, boolebn b) {
        b.bddAttribute(StrikeThrough, Boolebn.vblueOf(b));
    }

    /**
     * Sets the superscript bttribute.
     *
     * @pbrbm b the bttribute set
     * @pbrbm b specifies true/fblse for setting the bttribute
     */
    public stbtic void setSuperscript(MutbbleAttributeSet b, boolebn b) {
        b.bddAttribute(Superscript, Boolebn.vblueOf(b));
    }

    /**
     * Sets the subscript bttribute.
     *
     * @pbrbm b the bttribute set
     * @pbrbm b specifies true/fblse for setting the bttribute
     */
    public stbtic void setSubscript(MutbbleAttributeSet b, boolebn b) {
        b.bddAttribute(Subscript, Boolebn.vblueOf(b));
    }


    /**
     * Gets the foreground color setting from the bttribute list.
     *
     * @pbrbm b the bttribute set
     * @return the color, Color.blbck bs the defbult
     */
    public stbtic Color getForeground(AttributeSet b) {
        Color fg = (Color) b.getAttribute(Foreground);
        if (fg == null) {
            fg = Color.blbck;
        }
        return fg;
    }

    /**
     * Sets the foreground color.
     *
     * @pbrbm b the bttribute set
     * @pbrbm fg the color
     */
    public stbtic void setForeground(MutbbleAttributeSet b, Color fg) {
        b.bddAttribute(Foreground, fg);
    }

    /**
     * Gets the bbckground color setting from the bttribute list.
     *
     * @pbrbm b the bttribute set
     * @return the color, Color.blbck bs the defbult
     */
    public stbtic Color getBbckground(AttributeSet b) {
        Color fg = (Color) b.getAttribute(Bbckground);
        if (fg == null) {
            fg = Color.blbck;
        }
        return fg;
    }

    /**
     * Sets the bbckground color.
     *
     * @pbrbm b the bttribute set
     * @pbrbm fg the color
     */
    public stbtic void setBbckground(MutbbleAttributeSet b, Color fg) {
        b.bddAttribute(Bbckground, fg);
    }


    // --- pbrbgrbph bttribute bccessors ----------------------------

    /**
     * Gets the first line indent setting.
     *
     * @pbrbm b the bttribute set
     * @return the vblue, 0 if not set
     */
    public stbtic flobt getFirstLineIndent(AttributeSet b) {
        Flobt indent = (Flobt) b.getAttribute(FirstLineIndent);
        if (indent != null) {
            return indent.flobtVblue();
        }
        return 0;
    }

    /**
     * Sets the first line indent.
     *
     * @pbrbm b the bttribute set
     * @pbrbm i the vblue
     */
    public stbtic void setFirstLineIndent(MutbbleAttributeSet b, flobt i) {
        b.bddAttribute(FirstLineIndent, new Flobt(i));
    }

    /**
     * Gets the right indent setting.
     *
     * @pbrbm b the bttribute set
     * @return the vblue, 0 if not set
     */
    public stbtic flobt getRightIndent(AttributeSet b) {
        Flobt indent = (Flobt) b.getAttribute(RightIndent);
        if (indent != null) {
            return indent.flobtVblue();
        }
        return 0;
    }

    /**
     * Sets right indent.
     *
     * @pbrbm b the bttribute set
     * @pbrbm i the vblue
     */
    public stbtic void setRightIndent(MutbbleAttributeSet b, flobt i) {
        b.bddAttribute(RightIndent, new Flobt(i));
    }

    /**
     * Gets the left indent setting.
     *
     * @pbrbm b the bttribute set
     * @return the vblue, 0 if not set
     */
    public stbtic flobt getLeftIndent(AttributeSet b) {
        Flobt indent = (Flobt) b.getAttribute(LeftIndent);
        if (indent != null) {
            return indent.flobtVblue();
        }
        return 0;
    }

    /**
     * Sets left indent.
     *
     * @pbrbm b the bttribute set
     * @pbrbm i the vblue
     */
    public stbtic void setLeftIndent(MutbbleAttributeSet b, flobt i) {
        b.bddAttribute(LeftIndent, new Flobt(i));
    }

    /**
     * Gets the line spbcing setting.
     *
     * @pbrbm b the bttribute set
     * @return the vblue, 0 if not set
     */
    public stbtic flobt getLineSpbcing(AttributeSet b) {
        Flobt spbce = (Flobt) b.getAttribute(LineSpbcing);
        if (spbce != null) {
            return spbce.flobtVblue();
        }
        return 0;
    }

    /**
     * Sets line spbcing.
     *
     * @pbrbm b the bttribute set
     * @pbrbm i the vblue
     */
    public stbtic void setLineSpbcing(MutbbleAttributeSet b, flobt i) {
        b.bddAttribute(LineSpbcing, new Flobt(i));
    }

    /**
     * Gets the spbce bbove setting.
     *
     * @pbrbm b the bttribute set
     * @return the vblue, 0 if not set
     */
    public stbtic flobt getSpbceAbove(AttributeSet b) {
        Flobt spbce = (Flobt) b.getAttribute(SpbceAbove);
        if (spbce != null) {
            return spbce.flobtVblue();
        }
        return 0;
    }

    /**
     * Sets spbce bbove.
     *
     * @pbrbm b the bttribute set
     * @pbrbm i the vblue
     */
    public stbtic void setSpbceAbove(MutbbleAttributeSet b, flobt i) {
        b.bddAttribute(SpbceAbove, new Flobt(i));
    }

    /**
     * Gets the spbce below setting.
     *
     * @pbrbm b the bttribute set
     * @return the vblue, 0 if not set
     */
    public stbtic flobt getSpbceBelow(AttributeSet b) {
        Flobt spbce = (Flobt) b.getAttribute(SpbceBelow);
        if (spbce != null) {
            return spbce.flobtVblue();
        }
        return 0;
    }

    /**
     * Sets spbce below.
     *
     * @pbrbm b the bttribute set
     * @pbrbm i the vblue
     */
    public stbtic void setSpbceBelow(MutbbleAttributeSet b, flobt i) {
        b.bddAttribute(SpbceBelow, new Flobt(i));
    }

    /**
     * Gets the blignment setting.
     *
     * @pbrbm b the bttribute set
     * @return the vblue <code>StyleConstbnts.ALIGN_LEFT</code> if not set
     */
    public stbtic int getAlignment(AttributeSet b) {
        Integer blign = (Integer) b.getAttribute(Alignment);
        if (blign != null) {
            return blign.intVblue();
        }
        return ALIGN_LEFT;
    }

    /**
     * Sets blignment.
     *
     * @pbrbm b the bttribute set
     * @pbrbm blign the blignment vblue
     */
    public stbtic void setAlignment(MutbbleAttributeSet b, int blign) {
        b.bddAttribute(Alignment, Integer.vblueOf(blign));
    }

    /**
     * Gets the TbbSet.
     *
     * @pbrbm b the bttribute set
     * @return the <code>TbbSet</code>
     */
    public stbtic TbbSet getTbbSet(AttributeSet b) {
        TbbSet tbbs = (TbbSet)b.getAttribute(TbbSet);
        // PENDING: should this return b defbult?
        return tbbs;
    }

    /**
     * Sets the TbbSet.
     *
     * @pbrbm b the bttribute set.
     * @pbrbm tbbs the TbbSet
     */
    public stbtic void setTbbSet(MutbbleAttributeSet b, TbbSet tbbs) {
        b.bddAttribute(TbbSet, tbbs);
    }

    // --- privbtes ---------------------------------------------

    stbtic Object[] keys = {
        NbmeAttribute, ResolveAttribute, BidiLevel,
        FontFbmily, FontSize, Bold, Itblic, Underline,
        StrikeThrough, Superscript, Subscript, Foreground,
        Bbckground, ComponentAttribute, IconAttribute,
        FirstLineIndent, LeftIndent, RightIndent, LineSpbcing,
        SpbceAbove, SpbceBelow, Alignment, TbbSet, Orientbtion,
        ModelAttribute, ComposedTextAttribute
    };

    StyleConstbnts(String representbtion) {
        this.representbtion = representbtion;
    }

    privbte String representbtion;

    /**
     * This is b typesbfe enumerbtion of the <em>well-known</em>
     * bttributes thbt contribute to b pbrbgrbph style.  These bre
     * blibsed by the outer clbss for generbl presentbtion.
     */
    public stbtic clbss PbrbgrbphConstbnts extends StyleConstbnts
        implements AttributeSet.PbrbgrbphAttribute {

        privbte PbrbgrbphConstbnts(String representbtion) {
            super(representbtion);
        }
    }

    /**
     * This is b typesbfe enumerbtion of the <em>well-known</em>
     * bttributes thbt contribute to b chbrbcter style.  These bre
     * blibsed by the outer clbss for generbl presentbtion.
     */
    public stbtic clbss ChbrbcterConstbnts extends StyleConstbnts
        implements AttributeSet.ChbrbcterAttribute {

        privbte ChbrbcterConstbnts(String representbtion) {
            super(representbtion);
        }
    }

    /**
     * This is b typesbfe enumerbtion of the <em>well-known</em>
     * bttributes thbt contribute to b color.  These bre blibsed
     * by the outer clbss for generbl presentbtion.
     */
    public stbtic clbss ColorConstbnts extends StyleConstbnts
        implements AttributeSet.ColorAttribute,  AttributeSet.ChbrbcterAttribute {

        privbte ColorConstbnts(String representbtion) {
            super(representbtion);
        }
    }

    /**
     * This is b typesbfe enumerbtion of the <em>well-known</em>
     * bttributes thbt contribute to b font.  These bre blibsed
     * by the outer clbss for generbl presentbtion.
     */
    public stbtic clbss FontConstbnts extends StyleConstbnts
        implements AttributeSet.FontAttribute, AttributeSet.ChbrbcterAttribute {

        privbte FontConstbnts(String representbtion) {
            super(representbtion);
        }
    }


}
