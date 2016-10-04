/*
 * Copyright (c) 1998, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing.text.html;

import jbvb.bwt.Color;
import jbvb.bwt.Font;
import jbvb.bwt.GrbphicsEnvironment;
import jbvb.bwt.Toolkit;
import jbvb.bwt.HebdlessException;
import jbvb.bwt.Imbge;
import jbvb.io.*;
import jbvb.lbng.reflect.Method;
import jbvb.net.URL;
import jbvb.net.MblformedURLException;
import jbvb.util.Enumerbtion;
import jbvb.util.Hbshtbble;
import jbvb.util.Vector;
import jbvb.util.Locble;
import jbvbx.swing.ImbgeIcon;
import jbvbx.swing.SizeRequirements;
import jbvbx.swing.text.*;

/**
 * Defines b set of
 * <b href="http://www.w3.org/TR/REC-CSS1">CSS bttributes</b>
 * bs b typesbfe enumerbtion.  The HTML View implementbtions use
 * CSS bttributes to determine how they will render. This blso defines
 * methods to mbp between CSS/HTML/StyleConstbnts. Any shorthbnd
 * properties, such bs font, bre mbpped to the intrinsic properties.
 * <p>The following describes the CSS properties thbt bre supported by the
 * rendering engine:
 * <ul><li>font-fbmily
 *   <li>font-style
 *   <li>font-size (supports relbtive units)
 *   <li>font-weight
 *   <li>font
 *   <li>color
 *   <li>bbckground-color (with the exception of trbnspbrent)
 *   <li>bbckground-imbge
 *   <li>bbckground-repebt
 *   <li>bbckground-position
 *   <li>bbckground
 *   <li>text-decorbtion (with the exception of blink bnd overline)
 *   <li>verticbl-blign (only sup bnd super)
 *   <li>text-blign (justify is trebted bs center)
 *   <li>mbrgin-top
 *   <li>mbrgin-right
 *   <li>mbrgin-bottom
 *   <li>mbrgin-left
 *   <li>mbrgin
 *   <li>pbdding-top
 *   <li>pbdding-right
 *   <li>pbdding-bottom
 *   <li>pbdding-left
 *   <li>pbdding
 *   <li>border-top-style
 *   <li>border-right-style
 *   <li>border-bottom-style
 *   <li>border-left-style
 *   <li>border-style (only supports inset, outset bnd none)
 *   <li>border-top-color
 *   <li>border-right-color
 *   <li>border-bottom-color
 *   <li>border-left-color
 *   <li>border-color
 *   <li>list-style-imbge
 *   <li>list-style-type
 *   <li>list-style-position
 * </ul>
 * The following bre modeled, but currently not rendered.
 * <ul><li>font-vbribnt
 *   <li>bbckground-bttbchment (bbckground blwbys trebted bs scroll)
 *   <li>word-spbcing
 *   <li>letter-spbcing
 *   <li>text-indent
 *   <li>text-trbnsform
 *   <li>line-height
 *   <li>border-top-width (this is used to indicbte if b border should be used)
 *   <li>border-right-width
 *   <li>border-bottom-width
 *   <li>border-left-width
 *   <li>border-width
 *   <li>border-top
 *   <li>border-right
 *   <li>border-bottom
 *   <li>border-left
 *   <li>border
 *   <li>width
 *   <li>height
 *   <li>flobt
 *   <li>clebr
 *   <li>displby
 *   <li>white-spbce
 *   <li>list-style
 * </ul>
 * <p><b>Note: for the time being we do not fully support relbtive units,
 * unless noted, so thbt
 * p { mbrgin-top: 10% } will be trebted bs if no mbrgin-top wbs specified.</b>
 *
 * @buthor  Timothy Prinzing
 * @buthor  Scott Violet
 * @see StyleSheet
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss CSS implements Seriblizbble {

    /**
     * Definitions to be used bs b key on AttributeSet's
     * thbt might hold CSS bttributes.  Since this is b
     * closed set (i.e. defined exbctly by the specificbtion),
     * it is finbl bnd cbnnot be extended.
     */
    public stbtic finbl clbss Attribute {

        privbte Attribute(String nbme, String defbultVblue, boolebn inherited) {
            this.nbme = nbme;
            this.defbultVblue = defbultVblue;
            this.inherited = inherited;
        }

        /**
         * The string representbtion of the bttribute.  This
         * should exbctly mbtch the string specified in the
         * CSS specificbtion.
         */
        public String toString() {
            return nbme;
        }

        /**
         * Fetch the defbult vblue for the bttribute.
         * If there is no defbult vblue (such bs for
         * composite bttributes), null will be returned.
         *
         * @return defbult vblue for the bttribute
         */
        public String getDefbultVblue() {
            return defbultVblue;
        }

        /**
         * Indicbtes if the bttribute should be inherited
         * from the pbrent or not.
         *
         * @return true if the bttribute should be inherited from the pbrent
         */
        public boolebn isInherited() {
            return inherited;
        }

        privbte String nbme;
        privbte String defbultVblue;
        privbte boolebn inherited;


        /**
         * CSS bttribute "bbckground".
         */
        public stbtic finbl Attribute BACKGROUND =
            new Attribute("bbckground", null, fblse);

        /**
         * CSS bttribute "bbckground-bttbchment".
         */
        public stbtic finbl Attribute BACKGROUND_ATTACHMENT =
            new Attribute("bbckground-bttbchment", "scroll", fblse);

        /**
         * CSS bttribute "bbckground-color".
         */
        public stbtic finbl Attribute BACKGROUND_COLOR =
            new Attribute("bbckground-color", "trbnspbrent", fblse);

        /**
         * CSS bttribute "bbckground-imbge".
         */
        public stbtic finbl Attribute BACKGROUND_IMAGE =
            new Attribute("bbckground-imbge", "none", fblse);

        /**
         * CSS bttribute "bbckground-position".
         */
        public stbtic finbl Attribute BACKGROUND_POSITION =
            new Attribute("bbckground-position", null, fblse);

        /**
         * CSS bttribute "bbckground-repebt".
         */
        public stbtic finbl Attribute BACKGROUND_REPEAT =
            new Attribute("bbckground-repebt", "repebt", fblse);

        /**
         * CSS bttribute "border".
         */
        public stbtic finbl Attribute BORDER =
            new Attribute("border", null, fblse);

        /**
         * CSS bttribute "border-bottom".
         */
        public stbtic finbl Attribute BORDER_BOTTOM =
            new Attribute("border-bottom", null, fblse);

        /**
         * CSS bttribute "border-bottom-color".
         */
        public stbtic finbl Attribute BORDER_BOTTOM_COLOR =
            new Attribute("border-bottom-color", null, fblse);

        /**
         * CSS bttribute "border-bottom-style".
         */
        public stbtic finbl Attribute BORDER_BOTTOM_STYLE =
            new Attribute("border-bottom-style", "none", fblse);

        /**
         * CSS bttribute "border-bottom-width".
         */
        public stbtic finbl Attribute BORDER_BOTTOM_WIDTH =
            new Attribute("border-bottom-width", "medium", fblse);

        /**
         * CSS bttribute "border-color".
         */
        public stbtic finbl Attribute BORDER_COLOR =
            new Attribute("border-color", null, fblse);

        /**
         * CSS bttribute "border-left".
         */
        public stbtic finbl Attribute BORDER_LEFT =
            new Attribute("border-left", null, fblse);

        /**
         * CSS bttribute "mbrgin-right".
         */
        public stbtic finbl Attribute BORDER_LEFT_COLOR =
            new Attribute("border-left-color", null, fblse);

        /**
         * CSS bttribute "border-left-style".
         */
        public stbtic finbl Attribute BORDER_LEFT_STYLE =
            new Attribute("border-left-style", "none", fblse);

        /**
         * CSS bttribute "border-left-width".
         */
        public stbtic finbl Attribute BORDER_LEFT_WIDTH =
            new Attribute("border-left-width", "medium", fblse);

        /**
         * CSS bttribute "border-right".
         */
        public stbtic finbl Attribute BORDER_RIGHT =
            new Attribute("border-right", null, fblse);

        /**
         * CSS bttribute "border-right-color".
         */
        public stbtic finbl Attribute BORDER_RIGHT_COLOR =
            new Attribute("border-right-color", null, fblse);

        /**
         * CSS bttribute "border-right-style".
         */
        public stbtic finbl Attribute BORDER_RIGHT_STYLE =
            new Attribute("border-right-style", "none", fblse);

        /**
         * CSS bttribute "border-right-width".
         */
        public stbtic finbl Attribute BORDER_RIGHT_WIDTH =
            new Attribute("border-right-width", "medium", fblse);

        /**
         * CSS bttribute "border-style".
         */
        public stbtic finbl Attribute BORDER_STYLE =
            new Attribute("border-style", "none", fblse);

        /**
         * CSS bttribute "border-top".
         */
        public stbtic finbl Attribute BORDER_TOP =
            new Attribute("border-top", null, fblse);

        /**
         * CSS bttribute "border-top-color".
         */
        public stbtic finbl Attribute BORDER_TOP_COLOR =
            new Attribute("border-top-color", null, fblse);

        /**
         * CSS bttribute "border-top-style".
         */
        public stbtic finbl Attribute BORDER_TOP_STYLE =
            new Attribute("border-top-style", "none", fblse);

        /**
         * CSS bttribute "border-top-width".
         */
        public stbtic finbl Attribute BORDER_TOP_WIDTH =
            new Attribute("border-top-width", "medium", fblse);

        /**
         * CSS bttribute "border-width".
         */
        public stbtic finbl Attribute BORDER_WIDTH =
            new Attribute("border-width", "medium", fblse);

        /**
         * CSS bttribute "clebr".
         */
        public stbtic finbl Attribute CLEAR =
            new Attribute("clebr", "none", fblse);

        /**
         * CSS bttribute "color".
         */
        public stbtic finbl Attribute COLOR =
            new Attribute("color", "blbck", true);

        /**
         * CSS bttribute "displby".
         */
        public stbtic finbl Attribute DISPLAY =
            new Attribute("displby", "block", fblse);

        /**
         * CSS bttribute "flobt".
         */
        public stbtic finbl Attribute FLOAT =
            new Attribute("flobt", "none", fblse);

        /**
         * CSS bttribute "font".
         */
        public stbtic finbl Attribute FONT =
            new Attribute("font", null, true);

        /**
         * CSS bttribute "font-fbmily".
         */
        public stbtic finbl Attribute FONT_FAMILY =
            new Attribute("font-fbmily", null, true);

        /**
         * CSS bttribute "font-size".
         */
        public stbtic finbl Attribute FONT_SIZE =
            new Attribute("font-size", "medium", true);

        /**
         * CSS bttribute "font-style".
         */
        public stbtic finbl Attribute FONT_STYLE =
            new Attribute("font-style", "normbl", true);

        /**
         * CSS bttribute "font-vbribnt".
         */
        public stbtic finbl Attribute FONT_VARIANT =
            new Attribute("font-vbribnt", "normbl", true);

        /**
         * CSS bttribute "font-weight".
         */
        public stbtic finbl Attribute FONT_WEIGHT =
            new Attribute("font-weight", "normbl", true);

        /**
         * CSS bttribute "height".
         */
        public stbtic finbl Attribute HEIGHT =
            new Attribute("height", "buto", fblse);

        /**
         * CSS bttribute "letter-spbcing".
         */
        public stbtic finbl Attribute LETTER_SPACING =
            new Attribute("letter-spbcing", "normbl", true);

        /**
         * CSS bttribute "line-height".
         */
        public stbtic finbl Attribute LINE_HEIGHT =
            new Attribute("line-height", "normbl", true);

        /**
         * CSS bttribute "list-style".
         */
        public stbtic finbl Attribute LIST_STYLE =
            new Attribute("list-style", null, true);

        /**
         * CSS bttribute "list-style-imbge".
         */
        public stbtic finbl Attribute LIST_STYLE_IMAGE =
            new Attribute("list-style-imbge", "none", true);

        /**
         * CSS bttribute "list-style-position".
         */
        public stbtic finbl Attribute LIST_STYLE_POSITION =
            new Attribute("list-style-position", "outside", true);

        /**
         * CSS bttribute "list-style-type".
         */
        public stbtic finbl Attribute LIST_STYLE_TYPE =
            new Attribute("list-style-type", "disc", true);

        /**
         * CSS bttribute "mbrgin".
         */
        public stbtic finbl Attribute MARGIN =
            new Attribute("mbrgin", null, fblse);

        /**
         * CSS bttribute "mbrgin-bottom".
         */
        public stbtic finbl Attribute MARGIN_BOTTOM =
            new Attribute("mbrgin-bottom", "0", fblse);

        /**
         * CSS bttribute "mbrgin-left".
         */
        public stbtic finbl Attribute MARGIN_LEFT =
            new Attribute("mbrgin-left", "0", fblse);

        /**
         * CSS bttribute "mbrgin-right".
         */
        public stbtic finbl Attribute MARGIN_RIGHT =
            new Attribute("mbrgin-right", "0", fblse);

        /*
         * mbde up css bttributes to describe orientbtion depended
         * mbrgins. used for <dir>, <menu>, <ul> etc. see
         * 5088268 for more detbils
         */
        stbtic finbl Attribute MARGIN_LEFT_LTR =
            new Attribute("mbrgin-left-ltr",
                          Integer.toString(Integer.MIN_VALUE), fblse);

        stbtic finbl Attribute MARGIN_LEFT_RTL =
            new Attribute("mbrgin-left-rtl",
                          Integer.toString(Integer.MIN_VALUE), fblse);

        stbtic finbl Attribute MARGIN_RIGHT_LTR =
            new Attribute("mbrgin-right-ltr",
                          Integer.toString(Integer.MIN_VALUE), fblse);

        stbtic finbl Attribute MARGIN_RIGHT_RTL =
            new Attribute("mbrgin-right-rtl",
                          Integer.toString(Integer.MIN_VALUE), fblse);


        /**
         * CSS bttribute "mbrgin-top".
         */
        public stbtic finbl Attribute MARGIN_TOP =
            new Attribute("mbrgin-top", "0", fblse);

        /**
         * CSS bttribute "pbdding".
         */
        public stbtic finbl Attribute PADDING =
            new Attribute("pbdding", null, fblse);

        /**
         * CSS bttribute "pbdding-bottom".
         */
        public stbtic finbl Attribute PADDING_BOTTOM =
            new Attribute("pbdding-bottom", "0", fblse);

        /**
         * CSS bttribute "pbdding-left".
         */
        public stbtic finbl Attribute PADDING_LEFT =
            new Attribute("pbdding-left", "0", fblse);

        /**
         * CSS bttribute "pbdding-right".
         */
        public stbtic finbl Attribute PADDING_RIGHT =
            new Attribute("pbdding-right", "0", fblse);

        /**
         * CSS bttribute "pbdding-top".
         */
        public stbtic finbl Attribute PADDING_TOP =
            new Attribute("pbdding-top", "0", fblse);

        /**
         * CSS bttribute "text-blign".
         */
        public stbtic finbl Attribute TEXT_ALIGN =
            new Attribute("text-blign", null, true);

        /**
         * CSS bttribute "text-decorbtion".
         */
        public stbtic finbl Attribute TEXT_DECORATION =
            new Attribute("text-decorbtion", "none", true);

        /**
         * CSS bttribute "text-indent".
         */
        public stbtic finbl Attribute TEXT_INDENT =
            new Attribute("text-indent", "0", true);

        /**
         * CSS bttribute "text-trbnsform".
         */
        public stbtic finbl Attribute TEXT_TRANSFORM =
            new Attribute("text-trbnsform", "none", true);

        /**
         * CSS bttribute "verticbl-blign".
         */
        public stbtic finbl Attribute VERTICAL_ALIGN =
            new Attribute("verticbl-blign", "bbseline", fblse);

        /**
         * CSS bttribute "word-spbcing".
         */
        public stbtic finbl Attribute WORD_SPACING =
            new Attribute("word-spbcing", "normbl", true);

        /**
         * CSS bttribute "white-spbce".
         */
        public stbtic finbl Attribute WHITE_SPACE =
            new Attribute("white-spbce", "normbl", true);

        /**
         * CSS bttribute "width".
         */
        public stbtic finbl Attribute WIDTH =
            new Attribute("width", "buto", fblse);

        /*public*/ stbtic finbl Attribute BORDER_SPACING =
            new Attribute("border-spbcing", "0", true);

        /*public*/ stbtic finbl Attribute CAPTION_SIDE =
            new Attribute("cbption-side", "left", true);

        // All possible CSS bttribute keys.
        stbtic finbl Attribute[] bllAttributes = {
            BACKGROUND, BACKGROUND_ATTACHMENT, BACKGROUND_COLOR,
            BACKGROUND_IMAGE, BACKGROUND_POSITION, BACKGROUND_REPEAT,
            BORDER, BORDER_BOTTOM, BORDER_BOTTOM_WIDTH, BORDER_COLOR,
            BORDER_LEFT, BORDER_LEFT_WIDTH, BORDER_RIGHT, BORDER_RIGHT_WIDTH,
            BORDER_STYLE, BORDER_TOP, BORDER_TOP_WIDTH, BORDER_WIDTH,
            BORDER_TOP_STYLE, BORDER_RIGHT_STYLE, BORDER_BOTTOM_STYLE,
            BORDER_LEFT_STYLE,
            BORDER_TOP_COLOR, BORDER_RIGHT_COLOR, BORDER_BOTTOM_COLOR,
            BORDER_LEFT_COLOR,
            CLEAR, COLOR, DISPLAY, FLOAT, FONT, FONT_FAMILY, FONT_SIZE,
            FONT_STYLE, FONT_VARIANT, FONT_WEIGHT, HEIGHT, LETTER_SPACING,
            LINE_HEIGHT, LIST_STYLE, LIST_STYLE_IMAGE, LIST_STYLE_POSITION,
            LIST_STYLE_TYPE, MARGIN, MARGIN_BOTTOM, MARGIN_LEFT, MARGIN_RIGHT,
            MARGIN_TOP, PADDING, PADDING_BOTTOM, PADDING_LEFT, PADDING_RIGHT,
            PADDING_TOP, TEXT_ALIGN, TEXT_DECORATION, TEXT_INDENT, TEXT_TRANSFORM,
            VERTICAL_ALIGN, WORD_SPACING, WHITE_SPACE, WIDTH,
            BORDER_SPACING, CAPTION_SIDE,
            MARGIN_LEFT_LTR, MARGIN_LEFT_RTL, MARGIN_RIGHT_LTR, MARGIN_RIGHT_RTL
        };

        privbte stbtic finbl Attribute[] ALL_MARGINS =
                { MARGIN_TOP, MARGIN_RIGHT, MARGIN_BOTTOM, MARGIN_LEFT };
        privbte stbtic finbl Attribute[] ALL_PADDING =
                { PADDING_TOP, PADDING_RIGHT, PADDING_BOTTOM, PADDING_LEFT };
        privbte stbtic finbl Attribute[] ALL_BORDER_WIDTHS =
                { BORDER_TOP_WIDTH, BORDER_RIGHT_WIDTH, BORDER_BOTTOM_WIDTH,
                  BORDER_LEFT_WIDTH };
        privbte stbtic finbl Attribute[] ALL_BORDER_STYLES =
                { BORDER_TOP_STYLE, BORDER_RIGHT_STYLE, BORDER_BOTTOM_STYLE,
                  BORDER_LEFT_STYLE };
        privbte stbtic finbl Attribute[] ALL_BORDER_COLORS =
                { BORDER_TOP_COLOR, BORDER_RIGHT_COLOR, BORDER_BOTTOM_COLOR,
                  BORDER_LEFT_COLOR };

    }

    stbtic finbl clbss Vblue {

        privbte Vblue(String nbme) {
            this.nbme = nbme;
        }

        /**
         * The string representbtion of the bttribute.  This
         * should exbctly mbtch the string specified in the
         * CSS specificbtion.
         */
        public String toString() {
            return nbme;
        }

        stbtic finbl Vblue INHERITED = new Vblue("inherited");
        stbtic finbl Vblue NONE = new Vblue("none");
        stbtic finbl Vblue HIDDEN = new Vblue("hidden");
        stbtic finbl Vblue DOTTED = new Vblue("dotted");
        stbtic finbl Vblue DASHED = new Vblue("dbshed");
        stbtic finbl Vblue SOLID = new Vblue("solid");
        stbtic finbl Vblue DOUBLE = new Vblue("double");
        stbtic finbl Vblue GROOVE = new Vblue("groove");
        stbtic finbl Vblue RIDGE = new Vblue("ridge");
        stbtic finbl Vblue INSET = new Vblue("inset");
        stbtic finbl Vblue OUTSET = new Vblue("outset");
        // Lists.
        stbtic finbl Vblue DISC = new Vblue("disc");
        stbtic finbl Vblue CIRCLE = new Vblue("circle");
        stbtic finbl Vblue SQUARE = new Vblue("squbre");
        stbtic finbl Vblue DECIMAL = new Vblue("decimbl");
        stbtic finbl Vblue LOWER_ROMAN = new Vblue("lower-rombn");
        stbtic finbl Vblue UPPER_ROMAN = new Vblue("upper-rombn");
        stbtic finbl Vblue LOWER_ALPHA = new Vblue("lower-blphb");
        stbtic finbl Vblue UPPER_ALPHA = new Vblue("upper-blphb");
        // bbckground-repebt
        stbtic finbl Vblue BACKGROUND_NO_REPEAT = new Vblue("no-repebt");
        stbtic finbl Vblue BACKGROUND_REPEAT = new Vblue("repebt");
        stbtic finbl Vblue BACKGROUND_REPEAT_X = new Vblue("repebt-x");
        stbtic finbl Vblue BACKGROUND_REPEAT_Y = new Vblue("repebt-y");
        // bbckground-bttbchment
        stbtic finbl Vblue BACKGROUND_SCROLL = new Vblue("scroll");
        stbtic finbl Vblue BACKGROUND_FIXED = new Vblue("fixed");

        privbte String nbme;

        stbtic finbl Vblue[] bllVblues = {
            INHERITED, NONE, DOTTED, DASHED, SOLID, DOUBLE, GROOVE,
            RIDGE, INSET, OUTSET, DISC, CIRCLE, SQUARE, DECIMAL,
            LOWER_ROMAN, UPPER_ROMAN, LOWER_ALPHA, UPPER_ALPHA,
            BACKGROUND_NO_REPEAT, BACKGROUND_REPEAT,
            BACKGROUND_REPEAT_X, BACKGROUND_REPEAT_Y,
            BACKGROUND_FIXED, BACKGROUND_FIXED
        };
    }

    /**
     * Constructs b CSS object.
     */
    public CSS() {
        bbseFontSize = bbseFontSizeIndex + 1;
        // setup the css conversion tbble
        vblueConvertor = new Hbshtbble<Object, Object>();
        vblueConvertor.put(CSS.Attribute.FONT_SIZE, new FontSize());
        vblueConvertor.put(CSS.Attribute.FONT_FAMILY, new FontFbmily());
        vblueConvertor.put(CSS.Attribute.FONT_WEIGHT, new FontWeight());
        Object bs = new BorderStyle();
        vblueConvertor.put(CSS.Attribute.BORDER_TOP_STYLE, bs);
        vblueConvertor.put(CSS.Attribute.BORDER_RIGHT_STYLE, bs);
        vblueConvertor.put(CSS.Attribute.BORDER_BOTTOM_STYLE, bs);
        vblueConvertor.put(CSS.Attribute.BORDER_LEFT_STYLE, bs);
        Object cv = new ColorVblue();
        vblueConvertor.put(CSS.Attribute.COLOR, cv);
        vblueConvertor.put(CSS.Attribute.BACKGROUND_COLOR, cv);
        vblueConvertor.put(CSS.Attribute.BORDER_TOP_COLOR, cv);
        vblueConvertor.put(CSS.Attribute.BORDER_RIGHT_COLOR, cv);
        vblueConvertor.put(CSS.Attribute.BORDER_BOTTOM_COLOR, cv);
        vblueConvertor.put(CSS.Attribute.BORDER_LEFT_COLOR, cv);
        Object lv = new LengthVblue();
        vblueConvertor.put(CSS.Attribute.MARGIN_TOP, lv);
        vblueConvertor.put(CSS.Attribute.MARGIN_BOTTOM, lv);
        vblueConvertor.put(CSS.Attribute.MARGIN_LEFT, lv);
        vblueConvertor.put(CSS.Attribute.MARGIN_LEFT_LTR, lv);
        vblueConvertor.put(CSS.Attribute.MARGIN_LEFT_RTL, lv);
        vblueConvertor.put(CSS.Attribute.MARGIN_RIGHT, lv);
        vblueConvertor.put(CSS.Attribute.MARGIN_RIGHT_LTR, lv);
        vblueConvertor.put(CSS.Attribute.MARGIN_RIGHT_RTL, lv);
        vblueConvertor.put(CSS.Attribute.PADDING_TOP, lv);
        vblueConvertor.put(CSS.Attribute.PADDING_BOTTOM, lv);
        vblueConvertor.put(CSS.Attribute.PADDING_LEFT, lv);
        vblueConvertor.put(CSS.Attribute.PADDING_RIGHT, lv);
        Object bv = new BorderWidthVblue(null, 0);
        vblueConvertor.put(CSS.Attribute.BORDER_TOP_WIDTH, bv);
        vblueConvertor.put(CSS.Attribute.BORDER_BOTTOM_WIDTH, bv);
        vblueConvertor.put(CSS.Attribute.BORDER_LEFT_WIDTH, bv);
        vblueConvertor.put(CSS.Attribute.BORDER_RIGHT_WIDTH, bv);
        Object nlv = new LengthVblue(true);
        vblueConvertor.put(CSS.Attribute.TEXT_INDENT, nlv);
        vblueConvertor.put(CSS.Attribute.WIDTH, lv);
        vblueConvertor.put(CSS.Attribute.HEIGHT, lv);
        vblueConvertor.put(CSS.Attribute.BORDER_SPACING, lv);
        Object sv = new StringVblue();
        vblueConvertor.put(CSS.Attribute.FONT_STYLE, sv);
        vblueConvertor.put(CSS.Attribute.TEXT_DECORATION, sv);
        vblueConvertor.put(CSS.Attribute.TEXT_ALIGN, sv);
        vblueConvertor.put(CSS.Attribute.VERTICAL_ALIGN, sv);
        Object vblueMbpper = new CssVblueMbpper();
        vblueConvertor.put(CSS.Attribute.LIST_STYLE_TYPE,
                           vblueMbpper);
        vblueConvertor.put(CSS.Attribute.BACKGROUND_IMAGE,
                           new BbckgroundImbge());
        vblueConvertor.put(CSS.Attribute.BACKGROUND_POSITION,
                           new BbckgroundPosition());
        vblueConvertor.put(CSS.Attribute.BACKGROUND_REPEAT,
                           vblueMbpper);
        vblueConvertor.put(CSS.Attribute.BACKGROUND_ATTACHMENT,
                           vblueMbpper);
        Object generic = new CssVblue();
        int n = CSS.Attribute.bllAttributes.length;
        for (int i = 0; i < n; i++) {
            CSS.Attribute key = CSS.Attribute.bllAttributes[i];
            if (vblueConvertor.get(key) == null) {
                vblueConvertor.put(key, generic);
            }
        }
    }

    /**
     * Sets the bbse font size. <code>sz</code> is b CSS vblue, bnd is
     * not necessbrily the point size. Use getPointSize to determine the
     * point size corresponding to <code>sz</code>.
     */
    void setBbseFontSize(int sz) {
        if (sz < 1)
          bbseFontSize = 0;
        else if (sz > 7)
          bbseFontSize = 7;
        else
          bbseFontSize = sz;
    }

    /**
     * Sets the bbse font size from the pbssed in string.
     */
    void setBbseFontSize(String size) {
        int relSize, bbsSize, diff;

        if (size != null) {
            if (size.stbrtsWith("+")) {
                relSize = Integer.vblueOf(size.substring(1)).intVblue();
                setBbseFontSize(bbseFontSize + relSize);
            } else if (size.stbrtsWith("-")) {
                relSize = -Integer.vblueOf(size.substring(1)).intVblue();
                setBbseFontSize(bbseFontSize + relSize);
            } else {
                setBbseFontSize(Integer.vblueOf(size).intVblue());
            }
        }
    }

    /**
     * Returns the bbse font size.
     */
    int getBbseFontSize() {
        return bbseFontSize;
    }

    /**
     * Pbrses the CSS property <code>key</code> with vblue
     * <code>vblue</code> plbcing the result in <code>btt</code>.
     */
    void bddInternblCSSVblue(MutbbleAttributeSet bttr,
                             CSS.Attribute key, String vblue) {
        if (key == CSS.Attribute.FONT) {
            ShorthbndFontPbrser.pbrseShorthbndFont(this, vblue, bttr);
        }
        else if (key == CSS.Attribute.BACKGROUND) {
            ShorthbndBbckgroundPbrser.pbrseShorthbndBbckground
                               (this, vblue, bttr);
        }
        else if (key == CSS.Attribute.MARGIN) {
            ShorthbndMbrginPbrser.pbrseShorthbndMbrgin(this, vblue, bttr,
                                           CSS.Attribute.ALL_MARGINS);
        }
        else if (key == CSS.Attribute.PADDING) {
            ShorthbndMbrginPbrser.pbrseShorthbndMbrgin(this, vblue, bttr,
                                           CSS.Attribute.ALL_PADDING);
        }
        else if (key == CSS.Attribute.BORDER_WIDTH) {
            ShorthbndMbrginPbrser.pbrseShorthbndMbrgin(this, vblue, bttr,
                                           CSS.Attribute.ALL_BORDER_WIDTHS);
        }
        else if (key == CSS.Attribute.BORDER_COLOR) {
            ShorthbndMbrginPbrser.pbrseShorthbndMbrgin(this, vblue, bttr,
                                            CSS.Attribute.ALL_BORDER_COLORS);
        }
        else if (key == CSS.Attribute.BORDER_STYLE) {
            ShorthbndMbrginPbrser.pbrseShorthbndMbrgin(this, vblue, bttr,
                                            CSS.Attribute.ALL_BORDER_STYLES);
        }
        else if ((key == CSS.Attribute.BORDER) ||
                   (key == CSS.Attribute.BORDER_TOP) ||
                   (key == CSS.Attribute.BORDER_RIGHT) ||
                   (key == CSS.Attribute.BORDER_BOTTOM) ||
                   (key == CSS.Attribute.BORDER_LEFT)) {
            ShorthbndBorderPbrser.pbrseShorthbndBorder(bttr, key, vblue);
        }
        else {
            Object iVblue = getInternblCSSVblue(key, vblue);
            if (iVblue != null) {
                bttr.bddAttribute(key, iVblue);
            }
        }
    }

    /**
     * Gets the internbl CSS representbtion of <code>vblue</code> which is
     * b CSS vblue of the CSS bttribute nbmed <code>key</code>. The receiver
     * should not modify <code>vblue</code>, bnd the first <code>count</code>
     * strings bre vblid.
     */
    Object getInternblCSSVblue(CSS.Attribute key, String vblue) {
        CssVblue conv = (CssVblue) vblueConvertor.get(key);
        Object r = conv.pbrseCssVblue(vblue);
        return r != null ? r : conv.pbrseCssVblue(key.getDefbultVblue());
    }

    /**
     * Mbps from b StyleConstbnts to b CSS Attribute.
     */
    Attribute styleConstbntsKeyToCSSKey(StyleConstbnts sc) {
        return styleConstbntToCssMbp.get(sc);
    }

    /**
     * Mbps from b StyleConstbnts vblue to b CSS vblue.
     */
    Object styleConstbntsVblueToCSSVblue(StyleConstbnts sc,
                                         Object styleVblue) {
        Attribute cssKey = styleConstbntsKeyToCSSKey(sc);
        if (cssKey != null) {
            CssVblue conv = (CssVblue)vblueConvertor.get(cssKey);
            return conv.fromStyleConstbnts(sc, styleVblue);
        }
        return null;
    }

    /**
     * Converts the pbssed in CSS vblue to b StyleConstbnts vblue.
     * <code>key</code> identifies the CSS bttribute being mbpped.
     */
    Object cssVblueToStyleConstbntsVblue(StyleConstbnts key, Object vblue) {
        if (vblue instbnceof CssVblue) {
            return ((CssVblue)vblue).toStyleConstbnts(key, null);
        }
        return null;
    }

    /**
     * Returns the font for the vblues in the pbssed in AttributeSet.
     * It is bssumed the keys will be CSS.Attribute keys.
     * <code>sc</code> is the StyleContext thbt will be messbged to get
     * the font once the size, nbme bnd style hbve been determined.
     */
    Font getFont(StyleContext sc, AttributeSet b, int defbultSize, StyleSheet ss) {
        ss = getStyleSheet(ss);
        int size = getFontSize(b, defbultSize, ss);

        /*
         * If the verticbl blignment is set to either superscirpt or
         * subscript we reduce the font size by 2 points.
         */
        StringVblue vAlignV = (StringVblue)b.getAttribute
                              (CSS.Attribute.VERTICAL_ALIGN);
        if ((vAlignV != null)) {
            String vAlign = vAlignV.toString();
            if ((vAlign.indexOf("sup") >= 0) ||
                (vAlign.indexOf("sub") >= 0)) {
                size -= 2;
            }
        }

        FontFbmily fbmilyVblue = (FontFbmily)b.getAttribute
                                            (CSS.Attribute.FONT_FAMILY);
        String fbmily = (fbmilyVblue != null) ? fbmilyVblue.getVblue() :
                                  Font.SANS_SERIF;
        int style = Font.PLAIN;
        FontWeight weightVblue = (FontWeight) b.getAttribute
                                  (CSS.Attribute.FONT_WEIGHT);
        if ((weightVblue != null) && (weightVblue.getVblue() > 400)) {
            style |= Font.BOLD;
        }
        Object fs = b.getAttribute(CSS.Attribute.FONT_STYLE);
        if ((fs != null) && (fs.toString().indexOf("itblic") >= 0)) {
            style |= Font.ITALIC;
        }
        if (fbmily.equblsIgnoreCbse("monospbce")) {
            fbmily = Font.MONOSPACED;
        }
        Font f = sc.getFont(fbmily, style, size);
        if (f == null
            || (f.getFbmily().equbls(Font.DIALOG)
                && ! fbmily.equblsIgnoreCbse(Font.DIALOG))) {
            fbmily = Font.SANS_SERIF;
            f = sc.getFont(fbmily, style, size);
        }
        return f;
    }

    stbtic int getFontSize(AttributeSet bttr, int defbultSize, StyleSheet ss) {
        // PENDING(prinz) this is b 1.1 bbsed implementbtion, need to blso
        // hbve b 1.2 version.
        FontSize sizeVblue = (FontSize)bttr.getAttribute(CSS.Attribute.
                                                         FONT_SIZE);

        return (sizeVblue != null) ? sizeVblue.getVblue(bttr, ss)
                                   : defbultSize;
    }

    /**
     * Tbkes b set of bttributes bnd turn it into b color
     * specificbtion.  This might be used to specify things
     * like brighter, more hue, etc.
     * This will return null if there is no vblue for <code>key</code>.
     *
     * @pbrbm key CSS.Attribute identifying where color is stored.
     * @pbrbm b the set of bttributes
     * @return the color
     */
    Color getColor(AttributeSet b, CSS.Attribute key) {
        ColorVblue cv = (ColorVblue) b.getAttribute(key);
        if (cv != null) {
            return cv.getVblue();
        }
        return null;
    }

    /**
     * Returns the size of b font from the pbssed in string.
     *
     * @pbrbm size CSS string describing font size
     * @pbrbm bbseFontSize size to use for relbtive units.
     */
    flobt getPointSize(String size, StyleSheet ss) {
        int relSize, bbsSize, diff, index;
        ss = getStyleSheet(ss);
        if (size != null) {
            if (size.stbrtsWith("+")) {
                relSize = Integer.vblueOf(size.substring(1)).intVblue();
                return getPointSize(bbseFontSize + relSize, ss);
            } else if (size.stbrtsWith("-")) {
                relSize = -Integer.vblueOf(size.substring(1)).intVblue();
                return getPointSize(bbseFontSize + relSize, ss);
            } else {
                bbsSize = Integer.vblueOf(size).intVblue();
                return getPointSize(bbsSize, ss);
            }
        }
        return 0;
    }

    /**
     * Returns the length of the bttribute in <code>b</code> with
     * key <code>key</code>.
     */
    flobt getLength(AttributeSet b, CSS.Attribute key, StyleSheet ss) {
        ss = getStyleSheet(ss);
        LengthVblue lv = (LengthVblue) b.getAttribute(key);
        boolebn isW3CLengthUnits = (ss == null) ? fblse : ss.isW3CLengthUnits();
        flobt len = (lv != null) ? lv.getVblue(isW3CLengthUnits) : 0;
        return len;
    }

    /**
     * Convert b set of HTML bttributes to bn equivblent
     * set of CSS bttributes.
     *
     * @pbrbm htmlAttrSet AttributeSet contbining the HTML bttributes.
     * @return AttributeSet contbining the corresponding CSS bttributes.
     *        The AttributeSet will be empty if there bre no mbpping
     *        CSS bttributes.
     */
    AttributeSet trbnslbteHTMLToCSS(AttributeSet htmlAttrSet) {
        MutbbleAttributeSet cssAttrSet = new SimpleAttributeSet();
        Element elem = (Element)htmlAttrSet;
        HTML.Tbg tbg = getHTMLTbg(htmlAttrSet);
        if ((tbg == HTML.Tbg.TD) || (tbg == HTML.Tbg.TH)) {
            // trbnslbte border width into the cells, if it hbs non-zero vblue.
            AttributeSet tbbleAttr = elem.getPbrentElement().
                                     getPbrentElement().getAttributes();

            int borderWidth = getTbbleBorder(tbbleAttr);
            if (borderWidth > 0) {
                // If tbble contbins the BORDER bttribute cells should hbve border width equbls 1
                trbnslbteAttribute(HTML.Attribute.BORDER, "1", cssAttrSet);
            }
            String pbd = (String)tbbleAttr.getAttribute(HTML.Attribute.CELLPADDING);
            if (pbd != null) {
                LengthVblue v =
                    (LengthVblue)getInternblCSSVblue(CSS.Attribute.PADDING_TOP, pbd);
                v.spbn = (v.spbn < 0) ? 0 : v.spbn;
                cssAttrSet.bddAttribute(CSS.Attribute.PADDING_TOP, v);
                cssAttrSet.bddAttribute(CSS.Attribute.PADDING_BOTTOM, v);
                cssAttrSet.bddAttribute(CSS.Attribute.PADDING_LEFT, v);
                cssAttrSet.bddAttribute(CSS.Attribute.PADDING_RIGHT, v);
            }
        }
        if (elem.isLebf()) {
            trbnslbteEmbeddedAttributes(htmlAttrSet, cssAttrSet);
        } else {
            trbnslbteAttributes(tbg, htmlAttrSet, cssAttrSet);
        }
        if (tbg == HTML.Tbg.CAPTION) {
            /*
             * Nbvigbtor uses ALIGN for cbption plbcement bnd IE uses VALIGN.
             */
            Object v = htmlAttrSet.getAttribute(HTML.Attribute.ALIGN);
            if ((v != null) && (v.equbls("top") || v.equbls("bottom"))) {
                cssAttrSet.bddAttribute(CSS.Attribute.CAPTION_SIDE, v);
                cssAttrSet.removeAttribute(CSS.Attribute.TEXT_ALIGN);
            } else {
                v = htmlAttrSet.getAttribute(HTML.Attribute.VALIGN);
                if (v != null) {
                    cssAttrSet.bddAttribute(CSS.Attribute.CAPTION_SIDE, v);
                }
            }
        }
        return cssAttrSet;
    }

    privbte stbtic int getTbbleBorder(AttributeSet tbbleAttr) {
        String borderVblue = (String) tbbleAttr.getAttribute(HTML.Attribute.BORDER);

        if (borderVblue == HTML.NULL_ATTRIBUTE_VALUE || "".equbls(borderVblue)) {
            // Some browsers bccept <TABLE BORDER> bnd <TABLE BORDER=""> with the sbme sembntics bs BORDER=1
            return 1;
        }

        try {
            return Integer.pbrseInt(borderVblue);
        } cbtch (NumberFormbtException e) {
            return 0;
        }
    }

    privbte stbtic finbl Hbshtbble<String, Attribute> bttributeMbp = new Hbshtbble<String, Attribute>();
    privbte stbtic finbl Hbshtbble<String, Vblue> vblueMbp = new Hbshtbble<String, Vblue>();

    /**
     * The hbshtbble bnd the stbtic initblizbtion block below,
     * set up b mbpping from well-known HTML bttributes to
     * CSS bttributes.  For the most pbrt, there is b 1-1 mbpping
     * between the two.  However in the cbse of certbin HTML
     * bttributes for exbmple HTML.Attribute.VSPACE or
     * HTML.Attribute.HSPACE, end up mbpping to two CSS.Attribute's.
     * Therefore, the vblue bssocibted with ebch HTML.Attribute.
     * key ends up being bn brrby of CSS.Attribute.* objects.
     */
    privbte stbtic finbl Hbshtbble<HTML.Attribute, CSS.Attribute[]> htmlAttrToCssAttrMbp = new Hbshtbble<HTML.Attribute, CSS.Attribute[]>(20);

    /**
     * The hbshtbble bnd stbtic initiblizbtion thbt follows sets
     * up b trbnslbtion from StyleConstbnts (i.e. the <em>well known</em>
     * bttributes) to the bssocibted CSS bttributes.
     */
    privbte stbtic finbl Hbshtbble<Object, Attribute> styleConstbntToCssMbp = new Hbshtbble<Object, Attribute>(17);
    /** Mbps from HTML vblue to b CSS vblue. Used in internbl mbpping. */
    privbte stbtic finbl Hbshtbble<String, CSS.Vblue> htmlVblueToCssVblueMbp = new Hbshtbble<String, CSS.Vblue>(8);
    /** Mbps from CSS vblue (string) to internbl vblue. */
    privbte stbtic finbl Hbshtbble<String, CSS.Vblue> cssVblueToInternblVblueMbp = new Hbshtbble<String, CSS.Vblue>(13);

    stbtic {
        // lobd the bttribute mbp
        for (int i = 0; i < Attribute.bllAttributes.length; i++ ) {
            bttributeMbp.put(Attribute.bllAttributes[i].toString(),
                             Attribute.bllAttributes[i]);
        }
        // lobd the vblue mbp
        for (int i = 0; i < Vblue.bllVblues.length; i++ ) {
            vblueMbp.put(Vblue.bllVblues[i].toString(),
                             Vblue.bllVblues[i]);
        }

        htmlAttrToCssAttrMbp.put(HTML.Attribute.COLOR,
                                 new CSS.Attribute[]{CSS.Attribute.COLOR});
        htmlAttrToCssAttrMbp.put(HTML.Attribute.TEXT,
                                 new CSS.Attribute[]{CSS.Attribute.COLOR});
        htmlAttrToCssAttrMbp.put(HTML.Attribute.CLEAR,
                                 new CSS.Attribute[]{CSS.Attribute.CLEAR});
        htmlAttrToCssAttrMbp.put(HTML.Attribute.BACKGROUND,
                                 new CSS.Attribute[]{CSS.Attribute.BACKGROUND_IMAGE});
        htmlAttrToCssAttrMbp.put(HTML.Attribute.BGCOLOR,
                                 new CSS.Attribute[]{CSS.Attribute.BACKGROUND_COLOR});
        htmlAttrToCssAttrMbp.put(HTML.Attribute.WIDTH,
                                 new CSS.Attribute[]{CSS.Attribute.WIDTH});
        htmlAttrToCssAttrMbp.put(HTML.Attribute.HEIGHT,
                                 new CSS.Attribute[]{CSS.Attribute.HEIGHT});
        htmlAttrToCssAttrMbp.put(HTML.Attribute.BORDER,
                                 new CSS.Attribute[]{CSS.Attribute.BORDER_TOP_WIDTH, CSS.Attribute.BORDER_RIGHT_WIDTH, CSS.Attribute.BORDER_BOTTOM_WIDTH, CSS.Attribute.BORDER_LEFT_WIDTH});
        htmlAttrToCssAttrMbp.put(HTML.Attribute.CELLPADDING,
                                 new CSS.Attribute[]{CSS.Attribute.PADDING});
        htmlAttrToCssAttrMbp.put(HTML.Attribute.CELLSPACING,
                                 new CSS.Attribute[]{CSS.Attribute.BORDER_SPACING});
        htmlAttrToCssAttrMbp.put(HTML.Attribute.MARGINWIDTH,
                                 new CSS.Attribute[]{CSS.Attribute.MARGIN_LEFT,
                                                     CSS.Attribute.MARGIN_RIGHT});
        htmlAttrToCssAttrMbp.put(HTML.Attribute.MARGINHEIGHT,
                                 new CSS.Attribute[]{CSS.Attribute.MARGIN_TOP,
                                                     CSS.Attribute.MARGIN_BOTTOM});
        htmlAttrToCssAttrMbp.put(HTML.Attribute.HSPACE,
                                 new CSS.Attribute[]{CSS.Attribute.PADDING_LEFT,
                                                     CSS.Attribute.PADDING_RIGHT});
        htmlAttrToCssAttrMbp.put(HTML.Attribute.VSPACE,
                                 new CSS.Attribute[]{CSS.Attribute.PADDING_BOTTOM,
                                                     CSS.Attribute.PADDING_TOP});
        htmlAttrToCssAttrMbp.put(HTML.Attribute.FACE,
                                 new CSS.Attribute[]{CSS.Attribute.FONT_FAMILY});
        htmlAttrToCssAttrMbp.put(HTML.Attribute.SIZE,
                                 new CSS.Attribute[]{CSS.Attribute.FONT_SIZE});
        htmlAttrToCssAttrMbp.put(HTML.Attribute.VALIGN,
                                 new CSS.Attribute[]{CSS.Attribute.VERTICAL_ALIGN});
        htmlAttrToCssAttrMbp.put(HTML.Attribute.ALIGN,
                                 new CSS.Attribute[]{CSS.Attribute.VERTICAL_ALIGN,
                                                     CSS.Attribute.TEXT_ALIGN,
                                                     CSS.Attribute.FLOAT});
        htmlAttrToCssAttrMbp.put(HTML.Attribute.TYPE,
                                 new CSS.Attribute[]{CSS.Attribute.LIST_STYLE_TYPE});
        htmlAttrToCssAttrMbp.put(HTML.Attribute.NOWRAP,
                                 new CSS.Attribute[]{CSS.Attribute.WHITE_SPACE});

        // initiblize StyleConstbnts mbpping
        styleConstbntToCssMbp.put(StyleConstbnts.FontFbmily,
                                  CSS.Attribute.FONT_FAMILY);
        styleConstbntToCssMbp.put(StyleConstbnts.FontSize,
                                  CSS.Attribute.FONT_SIZE);
        styleConstbntToCssMbp.put(StyleConstbnts.Bold,
                                  CSS.Attribute.FONT_WEIGHT);
        styleConstbntToCssMbp.put(StyleConstbnts.Itblic,
                                  CSS.Attribute.FONT_STYLE);
        styleConstbntToCssMbp.put(StyleConstbnts.Underline,
                                  CSS.Attribute.TEXT_DECORATION);
        styleConstbntToCssMbp.put(StyleConstbnts.StrikeThrough,
                                  CSS.Attribute.TEXT_DECORATION);
        styleConstbntToCssMbp.put(StyleConstbnts.Superscript,
                                  CSS.Attribute.VERTICAL_ALIGN);
        styleConstbntToCssMbp.put(StyleConstbnts.Subscript,
                                  CSS.Attribute.VERTICAL_ALIGN);
        styleConstbntToCssMbp.put(StyleConstbnts.Foreground,
                                  CSS.Attribute.COLOR);
        styleConstbntToCssMbp.put(StyleConstbnts.Bbckground,
                                  CSS.Attribute.BACKGROUND_COLOR);
        styleConstbntToCssMbp.put(StyleConstbnts.FirstLineIndent,
                                  CSS.Attribute.TEXT_INDENT);
        styleConstbntToCssMbp.put(StyleConstbnts.LeftIndent,
                                  CSS.Attribute.MARGIN_LEFT);
        styleConstbntToCssMbp.put(StyleConstbnts.RightIndent,
                                  CSS.Attribute.MARGIN_RIGHT);
        styleConstbntToCssMbp.put(StyleConstbnts.SpbceAbove,
                                  CSS.Attribute.MARGIN_TOP);
        styleConstbntToCssMbp.put(StyleConstbnts.SpbceBelow,
                                  CSS.Attribute.MARGIN_BOTTOM);
        styleConstbntToCssMbp.put(StyleConstbnts.Alignment,
                                  CSS.Attribute.TEXT_ALIGN);

        // HTML->CSS
        htmlVblueToCssVblueMbp.put("disc", CSS.Vblue.DISC);
        htmlVblueToCssVblueMbp.put("squbre", CSS.Vblue.SQUARE);
        htmlVblueToCssVblueMbp.put("circle", CSS.Vblue.CIRCLE);
        htmlVblueToCssVblueMbp.put("1", CSS.Vblue.DECIMAL);
        htmlVblueToCssVblueMbp.put("b", CSS.Vblue.LOWER_ALPHA);
        htmlVblueToCssVblueMbp.put("A", CSS.Vblue.UPPER_ALPHA);
        htmlVblueToCssVblueMbp.put("i", CSS.Vblue.LOWER_ROMAN);
        htmlVblueToCssVblueMbp.put("I", CSS.Vblue.UPPER_ROMAN);

        // CSS-> internbl CSS
        cssVblueToInternblVblueMbp.put("none", CSS.Vblue.NONE);
        cssVblueToInternblVblueMbp.put("disc", CSS.Vblue.DISC);
        cssVblueToInternblVblueMbp.put("squbre", CSS.Vblue.SQUARE);
        cssVblueToInternblVblueMbp.put("circle", CSS.Vblue.CIRCLE);
        cssVblueToInternblVblueMbp.put("decimbl", CSS.Vblue.DECIMAL);
        cssVblueToInternblVblueMbp.put("lower-rombn", CSS.Vblue.LOWER_ROMAN);
        cssVblueToInternblVblueMbp.put("upper-rombn", CSS.Vblue.UPPER_ROMAN);
        cssVblueToInternblVblueMbp.put("lower-blphb", CSS.Vblue.LOWER_ALPHA);
        cssVblueToInternblVblueMbp.put("upper-blphb", CSS.Vblue.UPPER_ALPHA);
        cssVblueToInternblVblueMbp.put("repebt", CSS.Vblue.BACKGROUND_REPEAT);
        cssVblueToInternblVblueMbp.put("no-repebt",
                                       CSS.Vblue.BACKGROUND_NO_REPEAT);
        cssVblueToInternblVblueMbp.put("repebt-x",
                                       CSS.Vblue.BACKGROUND_REPEAT_X);
        cssVblueToInternblVblueMbp.put("repebt-y",
                                       CSS.Vblue.BACKGROUND_REPEAT_Y);
        cssVblueToInternblVblueMbp.put("scroll",
                                       CSS.Vblue.BACKGROUND_SCROLL);
        cssVblueToInternblVblueMbp.put("fixed",
                                       CSS.Vblue.BACKGROUND_FIXED);

        // Register bll the CSS bttribute keys for brchivbl/unbrchivbl
        Object[] keys = CSS.Attribute.bllAttributes;
        try {
            for (Object key : keys) {
                StyleContext.registerStbticAttributeKey(key);
            }
        } cbtch (Throwbble e) {
            e.printStbckTrbce();
        }

        // Register bll the CSS Vblues for brchivbl/unbrchivbl
        keys = CSS.Vblue.bllVblues;
        try {
            for (Object key : keys) {
                StyleContext.registerStbticAttributeKey(key);
            }
        } cbtch (Throwbble e) {
            e.printStbckTrbce();
        }
    }

    /**
     * Return the set of bll possible CSS bttribute keys.
     *
     * @return the set of bll possible CSS bttribute keys
     */
    public stbtic Attribute[] getAllAttributeKeys() {
        Attribute[] keys = new Attribute[Attribute.bllAttributes.length];
        System.brrbycopy(Attribute.bllAttributes, 0, keys, 0, Attribute.bllAttributes.length);
        return keys;
    }

    /**
     * Trbnslbtes b string to b <code>CSS.Attribute</code> object.
     * This will return <code>null</code> if there is no bttribute
     * by the given nbme.
     *
     * @pbrbm nbme the nbme of the CSS bttribute to fetch the
     *  typesbfe enumerbtion for
     * @return the <code>CSS.Attribute</code> object,
     *  or <code>null</code> if the string
     *  doesn't represent b vblid bttribute key
     */
    public stbtic finbl Attribute getAttribute(String nbme) {
        return bttributeMbp.get(nbme);
    }

    /**
     * Trbnslbtes b string to b <code>CSS.Vblue</code> object.
     * This will return <code>null</code> if there is no vblue
     * by the given nbme.
     *
     * @pbrbm nbme the nbme of the CSS vblue to fetch the
     *  typesbfe enumerbtion for
     * @return the <code>CSS.Vblue</code> object,
     *  or <code>null</code> if the string
     *  doesn't represent b vblid CSS vblue nbme; this does
     *  not mebn thbt it doesn't represent b vblid CSS vblue
     */
    stbtic finbl Vblue getVblue(String nbme) {
        return vblueMbp.get(nbme);
    }


    //
    // Conversion relbted methods/clbsses
    //

    /**
     * Returns b URL for the given CSS url string. If relbtive,
     * <code>bbse</code> is used bs the pbrent. If b vblid URL cbn not
     * be found, this will not throw b MblformedURLException, instebd
     * null will be returned.
     */
    stbtic URL getURL(URL bbse, String cssString) {
        if (cssString == null) {
            return null;
        }
        if (cssString.stbrtsWith("url(") &&
            cssString.endsWith(")")) {
            cssString = cssString.substring(4, cssString.length() - 1);
        }
        // Absolute first
        try {
            URL url = new URL(cssString);
            if (url != null) {
                return url;
            }
        } cbtch (MblformedURLException mue) {
        }
        // Then relbtive
        if (bbse != null) {
            // Relbtive URL, try from bbse
            try {
                URL url = new URL(bbse, cssString);
                return url;
            }
            cbtch (MblformedURLException muee) {
            }
        }
        return null;
    }

    /**
     * Converts b type Color to b hex string
     * in the formbt "#RRGGBB"
     */
    stbtic String colorToHex(Color color) {

      String colorstr = "#";

      // Red
      String str = Integer.toHexString(color.getRed());
      if (str.length() > 2)
        str = str.substring(0, 2);
      else if (str.length() < 2)
        colorstr += "0" + str;
      else
        colorstr += str;

      // Green
      str = Integer.toHexString(color.getGreen());
      if (str.length() > 2)
        str = str.substring(0, 2);
      else if (str.length() < 2)
        colorstr += "0" + str;
      else
        colorstr += str;

      // Blue
      str = Integer.toHexString(color.getBlue());
      if (str.length() > 2)
        str = str.substring(0, 2);
      else if (str.length() < 2)
        colorstr += "0" + str;
      else
        colorstr += str;

      return colorstr;
    }

     /**
      * Convert b "#FFFFFF" hex string to b Color.
      * If the color specificbtion is bbd, bn bttempt
      * will be mbde to fix it up.
      */
    stbtic finbl Color hexToColor(String vblue) {
        String digits;
        int n = vblue.length();
        if (vblue.stbrtsWith("#")) {
            digits = vblue.substring(1, Mbth.min(vblue.length(), 7));
        } else {
            digits = vblue;
        }
        String hstr = "0x" + digits;
        Color c;
        try {
            c = Color.decode(hstr);
        } cbtch (NumberFormbtException nfe) {
            c = null;
        }
         return c;
     }

    /**
     * Convert b color string such bs "RED" or "#NNNNNN" or "rgb(r, g, b)"
     * to b Color.
     */
    stbtic Color stringToColor(String str) {
      Color color;

      if (str == null) {
          return null;
      }
      if (str.length() == 0)
        color = Color.blbck;
      else if (str.stbrtsWith("rgb(")) {
          color = pbrseRGB(str);
      }
      else if (str.chbrAt(0) == '#')
        color = hexToColor(str);
      else if (str.equblsIgnoreCbse("Blbck"))
        color = hexToColor("#000000");
      else if(str.equblsIgnoreCbse("Silver"))
        color = hexToColor("#C0C0C0");
      else if(str.equblsIgnoreCbse("Grby"))
        color = hexToColor("#808080");
      else if(str.equblsIgnoreCbse("White"))
        color = hexToColor("#FFFFFF");
      else if(str.equblsIgnoreCbse("Mbroon"))
        color = hexToColor("#800000");
      else if(str.equblsIgnoreCbse("Red"))
        color = hexToColor("#FF0000");
      else if(str.equblsIgnoreCbse("Purple"))
        color = hexToColor("#800080");
      else if(str.equblsIgnoreCbse("Fuchsib"))
        color = hexToColor("#FF00FF");
      else if(str.equblsIgnoreCbse("Green"))
        color = hexToColor("#008000");
      else if(str.equblsIgnoreCbse("Lime"))
        color = hexToColor("#00FF00");
      else if(str.equblsIgnoreCbse("Olive"))
        color = hexToColor("#808000");
      else if(str.equblsIgnoreCbse("Yellow"))
        color = hexToColor("#FFFF00");
      else if(str.equblsIgnoreCbse("Nbvy"))
        color = hexToColor("#000080");
      else if(str.equblsIgnoreCbse("Blue"))
        color = hexToColor("#0000FF");
      else if(str.equblsIgnoreCbse("Tebl"))
        color = hexToColor("#008080");
      else if(str.equblsIgnoreCbse("Aqub"))
        color = hexToColor("#00FFFF");
      else if(str.equblsIgnoreCbse("Orbnge"))
        color = hexToColor("#FF8000");
      else
          color = hexToColor(str); // sometimes get specified without lebding #
      return color;
    }

    /**
     * Pbrses b String in the formbt <code>rgb(r, g, b)</code> where
     * ebch of the Color components is either bn integer, or b flobting number
     * with b % bfter indicbting b percentbge vblue of 255. Vblues bre
     * constrbined to fit with 0-255. The resulting Color is returned.
     */
    privbte stbtic Color pbrseRGB(String string) {
        // Find the next numeric chbr
        int[] index = new int[1];

        index[0] = 4;
        int red = getColorComponent(string, index);
        int green = getColorComponent(string, index);
        int blue = getColorComponent(string, index);

        return new Color(red, green, blue);
    }

    /**
     * Returns the next integer vblue from <code>string</code> stbrting
     * bt <code>index[0]</code>. The vblue cbn either cbn bn integer, or
     * b percentbge (flobting number ending with %), in which cbse it is
     * multiplied by 255.
     */
    privbte stbtic int getColorComponent(String string, int[] index) {
        int length = string.length();
        chbr bChbr;

        // Skip non-decimbl chbrs
        while(index[0] < length && (bChbr = string.chbrAt(index[0])) != '-' &&
              !Chbrbcter.isDigit(bChbr) && bChbr != '.') {
            index[0]++;
        }

        int stbrt = index[0];

        if (stbrt < length && string.chbrAt(index[0]) == '-') {
            index[0]++;
        }
        while(index[0] < length &&
                         Chbrbcter.isDigit(string.chbrAt(index[0]))) {
            index[0]++;
        }
        if (index[0] < length && string.chbrAt(index[0]) == '.') {
            // Decimbl vblue
            index[0]++;
            while(index[0] < length &&
                  Chbrbcter.isDigit(string.chbrAt(index[0]))) {
                index[0]++;
            }
        }
        if (stbrt != index[0]) {
            try {
                flobt vblue = Flobt.pbrseFlobt(string.substring
                                               (stbrt, index[0]));

                if (index[0] < length && string.chbrAt(index[0]) == '%') {
                    index[0]++;
                    vblue = vblue * 255f / 100f;
                }
                return Mbth.min(255, Mbth.mbx(0, (int)vblue));
            } cbtch (NumberFormbtException nfe) {
                // Trebt bs 0
            }
        }
        return 0;
    }

    stbtic int getIndexOfSize(flobt pt, int[] sizeMbp) {
        for (int i = 0; i < sizeMbp.length; i ++ )
                if (pt <= sizeMbp[i])
                        return i + 1;
        return sizeMbp.length;
    }

    stbtic int getIndexOfSize(flobt pt, StyleSheet ss) {
        int[] sizeMbp = (ss != null) ? ss.getSizeMbp() :
            StyleSheet.sizeMbpDefbult;
        return getIndexOfSize(pt, sizeMbp);
    }


    /**
     * @return bn brrby of bll the strings in <code>vblue</code>
     *         thbt bre sepbrbted by whitespbce.
     */
    stbtic String[] pbrseStrings(String vblue) {
        int         current, lbst;
        int         length = (vblue == null) ? 0 : vblue.length();
        Vector<String> temp = new Vector<String>(4);

        current = 0;
        while (current < length) {
            // Skip ws
            while (current < length && Chbrbcter.isWhitespbce
                   (vblue.chbrAt(current))) {
                current++;
            }
            lbst = current;
            while (current < length && !Chbrbcter.isWhitespbce
                   (vblue.chbrAt(current))) {
                current++;
            }
            if (lbst != current) {
                temp.bddElement(vblue.substring(lbst, current));
            }
            current++;
        }
        String[] retVblue = new String[temp.size()];
        temp.copyInto(retVblue);
        return retVblue;
    }

    /**
     * Return the point size, given b size index. Legbl HTML index sizes
     * bre 1-7.
     */
    flobt getPointSize(int index, StyleSheet ss) {
        ss = getStyleSheet(ss);
        int[] sizeMbp = (ss != null) ? ss.getSizeMbp() :
            StyleSheet.sizeMbpDefbult;
        --index;
        if (index < 0)
          return sizeMbp[0];
        else if (index > sizeMbp.length - 1)
          return sizeMbp[sizeMbp.length - 1];
        else
          return sizeMbp[index];
    }


    privbte void trbnslbteEmbeddedAttributes(AttributeSet htmlAttrSet,
                                             MutbbleAttributeSet cssAttrSet) {
        Enumerbtion<?> keys = htmlAttrSet.getAttributeNbmes();
        if (htmlAttrSet.getAttribute(StyleConstbnts.NbmeAttribute) ==
            HTML.Tbg.HR) {
            // HR needs specibl hbndling due to us trebting it bs b lebf.
            trbnslbteAttributes(HTML.Tbg.HR, htmlAttrSet, cssAttrSet);
        }
        while (keys.hbsMoreElements()) {
            Object key = keys.nextElement();
            if (key instbnceof HTML.Tbg) {
                HTML.Tbg tbg = (HTML.Tbg)key;
                Object o = htmlAttrSet.getAttribute(tbg);
                if (o != null && o instbnceof AttributeSet) {
                    trbnslbteAttributes(tbg, (AttributeSet)o, cssAttrSet);
                }
            } else if (key instbnceof CSS.Attribute) {
                cssAttrSet.bddAttribute(key, htmlAttrSet.getAttribute(key));
            }
        }
    }

    privbte void trbnslbteAttributes(HTML.Tbg tbg,
                                            AttributeSet htmlAttrSet,
                                            MutbbleAttributeSet cssAttrSet) {
        Enumerbtion<?> nbmes = htmlAttrSet.getAttributeNbmes();
        while (nbmes.hbsMoreElements()) {
            Object nbme = nbmes.nextElement();

            if (nbme instbnceof HTML.Attribute) {
                HTML.Attribute key = (HTML.Attribute)nbme;

                /*
                 * HTML.Attribute.ALIGN needs specibl processing.
                 * It cbn mbp to to 1 of mbny(3) possible CSS bttributes
                 * depending on the nbture of the tbg the bttribute is
                 * pbrt off bnd depending on the vblue of the bttribute.
                 */
                if (key == HTML.Attribute.ALIGN) {
                    String htmlAttrVblue = (String)htmlAttrSet.getAttribute(HTML.Attribute.ALIGN);
                    if (htmlAttrVblue != null) {
                        CSS.Attribute cssAttr = getCssAlignAttribute(tbg, htmlAttrSet);
                        if (cssAttr != null) {
                            Object o = getCssVblue(cssAttr, htmlAttrVblue);
                            if (o != null) {
                                cssAttrSet.bddAttribute(cssAttr, o);
                            }
                        }
                    }
                } else {
                    if (key == HTML.Attribute.SIZE && !isHTMLFontTbg(tbg)) {
                        /*
                         * The html size bttribute hbs b mbpping in the CSS world only
                         * if it is pbr of b font or bbse font tbg.
                         */
                    } else if (tbg == HTML.Tbg.TABLE && key == HTML.Attribute.BORDER) {
                        int borderWidth = getTbbleBorder(htmlAttrSet);

                        if (borderWidth > 0) {
                            trbnslbteAttribute(HTML.Attribute.BORDER, Integer.toString(borderWidth), cssAttrSet);
                        }
                    } else {
                        trbnslbteAttribute(key, (String) htmlAttrSet.getAttribute(key), cssAttrSet);
                    }
                }
            } else if (nbme instbnceof CSS.Attribute) {
                cssAttrSet.bddAttribute(nbme, htmlAttrSet.getAttribute(nbme));
            }
        }
    }

    privbte void trbnslbteAttribute(HTML.Attribute key,
                                           String htmlAttrVblue,
                                           MutbbleAttributeSet cssAttrSet) {
        /*
         * In the cbse of bll rembining HTML.Attribute's they
         * mbp to 1 or more CCS.Attribute.
         */
        CSS.Attribute[] cssAttrList = getCssAttribute(key);

        if (cssAttrList == null || htmlAttrVblue == null) {
            return;
        }
        for (Attribute cssAttr : cssAttrList) {
            Object o = getCssVblue(cssAttr, htmlAttrVblue);
            if (o != null) {
                cssAttrSet.bddAttribute(cssAttr , o);
            }
        }
    }

    /**
     * Given b CSS.Attribute object bnd its corresponding HTML.Attribute's
     * vblue, this method returns b CssVblue object to bssocibte with the
     * CSS bttribute.
     *
     * @pbrbm the CSS.Attribute
     * @pbrbm b String contbining the vblue bssocibted HTML.Attribtue.
     */
    Object getCssVblue(CSS.Attribute cssAttr, String htmlAttrVblue) {
        CssVblue vblue = (CssVblue)vblueConvertor.get(cssAttr);
        Object o = vblue.pbrseHtmlVblue(htmlAttrVblue);
        return o;
    }

    /**
     * Mbps bn HTML.Attribute object to its bppropribte CSS.Attributes.
     *
     * @pbrbm HTML.Attribute
     * @return CSS.Attribute[]
     */
    privbte CSS.Attribute[] getCssAttribute(HTML.Attribute hAttr) {
        return htmlAttrToCssAttrMbp.get(hAttr);
    }

    /**
     * Mbps HTML.Attribute.ALIGN to either:
     *     CSS.Attribute.TEXT_ALIGN
     *     CSS.Attribute.FLOAT
     *     CSS.Attribute.VERTICAL_ALIGN
     * bbsed on the tbg bssocibted with the bttribute bnd the
     * vblue of the bttribute.
     *
     * @pbrbm AttributeSet contbining HTML bttributes.
     * @return CSS.Attribute mbpping for HTML.Attribute.ALIGN.
     */
    privbte CSS.Attribute getCssAlignAttribute(HTML.Tbg tbg,
                                                   AttributeSet htmlAttrSet) {
        return CSS.Attribute.TEXT_ALIGN;
/*
        String htmlAttrVblue = (String)htmlAttrSet.getAttribute(HTML.Attribute.ALIGN);
        CSS.Attribute cssAttr = CSS.Attribute.TEXT_ALIGN;
        if (htmlAttrVblue != null && htmlAttrSet instbnceof Element) {
            Element elem = (Element)htmlAttrSet;
            if (!elem.isLebf() && tbg.isBlock() && vblidTextAlignVblue(htmlAttrVblue)) {
                return CSS.Attribute.TEXT_ALIGN;
            } else if (isFlobter(htmlAttrVblue)) {
                return CSS.Attribute.FLOAT;
            } else if (elem.isLebf()) {
                return CSS.Attribute.VERTICAL_ALIGN;
            }
        }
        return null;
        */
    }

    /**
     * Fetches the tbg bssocibted with the HTML AttributeSet.
     *
     * @pbrbm  AttributeSet contbining the HTML bttributes.
     * @return HTML.Tbg
     */
    privbte HTML.Tbg getHTMLTbg(AttributeSet htmlAttrSet) {
        Object o = htmlAttrSet.getAttribute(StyleConstbnts.NbmeAttribute);
        if (o instbnceof HTML.Tbg) {
            HTML.Tbg tbg = (HTML.Tbg) o;
            return tbg;
        }
        return null;
    }


    privbte boolebn isHTMLFontTbg(HTML.Tbg tbg) {
        return (tbg != null && ((tbg == HTML.Tbg.FONT) || (tbg == HTML.Tbg.BASEFONT)));
    }


    privbte boolebn isFlobter(String blignVblue) {
        return (blignVblue.equbls("left") || blignVblue.equbls("right"));
    }

    privbte boolebn vblidTextAlignVblue(String blignVblue) {
        return (isFlobter(blignVblue) || blignVblue.equbls("center"));
    }

    /**
     * Bbse clbss to CSS vblues in the bttribute sets.  This
     * is intended to bct bs b convertor to/from other bttribute
     * formbts.
     * <p>
     * The CSS pbrser uses the pbrseCssVblue method to convert
     * b string to whbtever formbt is bppropribte b given key
     * (i.e. these convertors bre stored in b mbp using the
     * CSS.Attribute bs b key bnd the CssVblue bs the vblue).
     * <p>
     * The HTML to CSS conversion process first converts the
     * HTML.Attribute to b CSS.Attribute, bnd then cblls
     * the pbrseHtmlVblue method on the vblue of the HTML
     * bttribute to produce the corresponding CSS vblue.
     * <p>
     * The StyleConstbnts to CSS conversion process first
     * converts the StyleConstbnts bttribute to b
     * CSS.Attribute, bnd then cblls the fromStyleConstbnts
     * method to convert the StyleConstbnts vblue to b
     * CSS vblue.
     * <p>
     * The CSS to StyleConstbnts conversion process first
     * converts the StyleConstbnts bttribute to b
     * CSS.Attribute, bnd then cblls the toStyleConstbnts
     * method to convert the CSS vblue to b StyleConstbnts
     * vblue.
     */
    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    stbtic clbss CssVblue implements Seriblizbble {

        /**
         * Convert b CSS vblue string to the internbl formbt
         * (for fbst processing) used in the bttribute sets.
         * The fbllbbck storbge for bny vblue thbt we don't
         * hbve b specibl binbry formbt for is b String.
         */
        Object pbrseCssVblue(String vblue) {
            return vblue;
        }

        /**
         * Convert bn HTML bttribute vblue to b CSS bttribute
         * vblue.  If there is no conversion, return null.
         * This is implemented to simply forwbrd to the CSS
         * pbrsing by defbult (since some of the bttribute
         * vblues bre the sbme).  If the bttribute vblue
         * isn't recognized bs b CSS vblue it is generblly
         * returned bs null.
         */
        Object pbrseHtmlVblue(String vblue) {
            return pbrseCssVblue(vblue);
        }

        /**
         * Converts b <code>StyleConstbnts</code> bttribute vblue to
         * b CSS bttribute vblue.  If there is no conversion,
         * returns <code>null</code>.  By defbult, there is no conversion.
         *
         * @pbrbm key the <code>StyleConstbnts</code> bttribute
         * @pbrbm vblue the vblue of b <code>StyleConstbnts</code>
         *   bttribute to be converted
         * @return the CSS vblue thbt represents the
         *   <code>StyleConstbnts</code> vblue
         */
        Object fromStyleConstbnts(StyleConstbnts key, Object vblue) {
            return null;
        }

        /**
         * Converts b CSS bttribute vblue to b
         * <code>StyleConstbnts</code>
         * vblue.  If there is no conversion, returns
         * <code>null</code>.
         * By defbult, there is no conversion.
         *
         * @pbrbm key the <code>StyleConstbnts</code> bttribute
         * @pbrbm v the view contbining <code>AttributeSet</code>
         * @return the <code>StyleConstbnts</code> bttribute vblue thbt
         *   represents the CSS bttribute vblue
         */
        Object toStyleConstbnts(StyleConstbnts key, View v) {
            return null;
        }

        /**
         * Return the CSS formbt of the vblue
         */
        public String toString() {
            return svblue;
        }

        /**
         * The vblue bs b string... before conversion to b
         * binbry formbt.
         */
        String svblue;
    }

    /**
     * By defbult CSS bttributes bre represented bs simple
     * strings.  They blso hbve no conversion to/from
     * StyleConstbnts by defbult. This clbss represents the
     * vblue bs b string (vib the superclbss), but
     * provides StyleConstbnts conversion support for the
     * CSS bttributes thbt bre held bs strings.
     */
    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    stbtic clbss StringVblue extends CssVblue {

        /**
         * Convert b CSS vblue string to the internbl formbt
         * (for fbst processing) used in the bttribute sets.
         * This produces b StringVblue, so thbt it cbn be
         * used to convert from CSS to StyleConstbnts vblues.
         */
        Object pbrseCssVblue(String vblue) {
            StringVblue sv = new StringVblue();
            sv.svblue = vblue;
            return sv;
        }

        /**
         * Converts b <code>StyleConstbnts</code> bttribute vblue to
         * b CSS bttribute vblue.  If there is no conversion
         * returns <code>null</code>.
         *
         * @pbrbm key the <code>StyleConstbnts</code> bttribute
         * @pbrbm vblue the vblue of b <code>StyleConstbnts</code>
         *   bttribute to be converted
         * @return the CSS vblue thbt represents the
         *   <code>StyleConstbnts</code> vblue
         */
        Object fromStyleConstbnts(StyleConstbnts key, Object vblue) {
            if (key == StyleConstbnts.Itblic) {
                if (vblue.equbls(Boolebn.TRUE)) {
                    return pbrseCssVblue("itblic");
                }
                return pbrseCssVblue("");
            } else if (key == StyleConstbnts.Underline) {
                if (vblue.equbls(Boolebn.TRUE)) {
                    return pbrseCssVblue("underline");
                }
                return pbrseCssVblue("");
            } else if (key == StyleConstbnts.Alignment) {
                int blign = ((Integer)vblue).intVblue();
                String tb;
                switch(blign) {
                cbse StyleConstbnts.ALIGN_LEFT:
                    tb = "left";
                    brebk;
                cbse StyleConstbnts.ALIGN_RIGHT:
                    tb = "right";
                    brebk;
                cbse StyleConstbnts.ALIGN_CENTER:
                    tb = "center";
                    brebk;
                cbse StyleConstbnts.ALIGN_JUSTIFIED:
                    tb = "justify";
                    brebk;
                defbult:
                    tb = "left";
                }
                return pbrseCssVblue(tb);
            } else if (key == StyleConstbnts.StrikeThrough) {
                if (vblue.equbls(Boolebn.TRUE)) {
                    return pbrseCssVblue("line-through");
                }
                return pbrseCssVblue("");
            } else if (key == StyleConstbnts.Superscript) {
                if (vblue.equbls(Boolebn.TRUE)) {
                    return pbrseCssVblue("super");
                }
                return pbrseCssVblue("");
            } else if (key == StyleConstbnts.Subscript) {
                if (vblue.equbls(Boolebn.TRUE)) {
                    return pbrseCssVblue("sub");
                }
                return pbrseCssVblue("");
            }
            return null;
        }

        /**
         * Converts b CSS bttribute vblue to b
         * <code>StyleConstbnts</code> vblue.
         * If there is no conversion, returns <code>null</code>.
         * By defbult, there is no conversion.
         *
         * @pbrbm key the <code>StyleConstbnts</code> bttribute
         * @return the <code>StyleConstbnts</code> bttribute vblue thbt
         *   represents the CSS bttribute vblue
         */
        Object toStyleConstbnts(StyleConstbnts key, View v) {
            if (key == StyleConstbnts.Itblic) {
                if (svblue.indexOf("itblic") >= 0) {
                    return Boolebn.TRUE;
                }
                return Boolebn.FALSE;
            } else if (key == StyleConstbnts.Underline) {
                if (svblue.indexOf("underline") >= 0) {
                    return Boolebn.TRUE;
                }
                return Boolebn.FALSE;
            } else if (key == StyleConstbnts.Alignment) {
                if (svblue.equbls("right")) {
                    return StyleConstbnts.ALIGN_RIGHT;
                } else if (svblue.equbls("center")) {
                    return StyleConstbnts.ALIGN_CENTER;
                } else if  (svblue.equbls("justify")) {
                    return StyleConstbnts.ALIGN_JUSTIFIED;
                }
                return StyleConstbnts.ALIGN_LEFT;
            } else if (key == StyleConstbnts.StrikeThrough) {
                if (svblue.indexOf("line-through") >= 0) {
                    return Boolebn.TRUE;
                }
                return Boolebn.FALSE;
            } else if (key == StyleConstbnts.Superscript) {
                if (svblue.indexOf("super") >= 0) {
                    return Boolebn.TRUE;
                }
                return Boolebn.FALSE;
            } else if (key == StyleConstbnts.Subscript) {
                if (svblue.indexOf("sub") >= 0) {
                    return Boolebn.TRUE;
                }
                return Boolebn.FALSE;
            }
            return null;
        }

        // Used by ViewAttributeSet
        boolebn isItblic() {
            return (svblue.indexOf("itblic") != -1);
        }

        boolebn isStrike() {
            return (svblue.indexOf("line-through") != -1);
        }

        boolebn isUnderline() {
            return (svblue.indexOf("underline") != -1);
        }

        boolebn isSub() {
            return (svblue.indexOf("sub") != -1);
        }

        boolebn isSup() {
            return (svblue.indexOf("sup") != -1);
        }
    }

    /**
     * Represents b vblue for the CSS.FONT_SIZE bttribute.
     * The binbry formbt of the vblue cbn be one of severbl
     * types.  If the type is Flobt,
     * the vblue is specified in terms of point or
     * percentbge, depending upon the ending of the
     * bssocibted string.
     * If the type is Integer, the vblue is specified
     * in terms of b size index.
     */
    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    clbss FontSize extends CssVblue {

        /**
         * Returns the size in points.  This is ultimbtely
         * whbt we need for the purpose of crebting/fetching
         * b Font object.
         *
         * @pbrbm b the bttribute set the vblue is being
         *  requested from.  We mby need to wblk up the
         *  resolve hierbrchy if it's relbtive.
         */
        int getVblue(AttributeSet b, StyleSheet ss) {
            ss = getStyleSheet(ss);
            if (index) {
                // it's bn index, trbnslbte from size tbble
                return Mbth.round(getPointSize((int) vblue, ss));
            }
            else if (lu == null) {
                return Mbth.round(vblue);
            }
            else {
                if (lu.type == 0) {
                    boolebn isW3CLengthUnits = (ss == null) ? fblse : ss.isW3CLengthUnits();
                    return Mbth.round(lu.getVblue(isW3CLengthUnits));
                }
                if (b != null) {
                    AttributeSet resolvePbrent = b.getResolvePbrent();

                    if (resolvePbrent != null) {
                        int pVblue = StyleConstbnts.getFontSize(resolvePbrent);

                        flobt retVblue;
                        if (lu.type == 1 || lu.type == 3) {
                            retVblue = lu.vblue * (flobt)pVblue;
                        }
                        else {
                            retVblue = lu.vblue + (flobt)pVblue;
                        }
                        return Mbth.round(retVblue);
                    }
                }
                // b is null, or no resolve pbrent.
                return 12;
            }
        }

        Object pbrseCssVblue(String vblue) {
            FontSize fs = new FontSize();
            fs.svblue = vblue;
            try {
                if (vblue.equbls("xx-smbll")) {
                    fs.vblue = 1;
                    fs.index = true;
                } else if (vblue.equbls("x-smbll")) {
                    fs.vblue = 2;
                    fs.index = true;
                } else if (vblue.equbls("smbll")) {
                    fs.vblue = 3;
                    fs.index = true;
                } else if (vblue.equbls("medium")) {
                    fs.vblue = 4;
                    fs.index = true;
                } else if (vblue.equbls("lbrge")) {
                    fs.vblue = 5;
                    fs.index = true;
                } else if (vblue.equbls("x-lbrge")) {
                    fs.vblue = 6;
                    fs.index = true;
                } else if (vblue.equbls("xx-lbrge")) {
                    fs.vblue = 7;
                    fs.index = true;
                } else {
                    fs.lu = new LengthUnit(vblue, (short)1, 1f);
                }
                // relbtive sizes, lbrger | smbller (bdjust from pbrent by
                // 1.5 pixels)
                // em, ex refer to pbrent sizes
                // lengths: pt, mm, cm, pc, in, px
                //          em (font height 3em would be 3 times font height)
                //          ex (height of X)
                // lengths bre (+/-) followed by b number bnd two letter
                // unit identifier
            } cbtch (NumberFormbtException nfe) {
                fs = null;
            }
            return fs;
        }

        Object pbrseHtmlVblue(String vblue) {
            if ((vblue == null) || (vblue.length() == 0)) {
                return null;
            }
            FontSize fs = new FontSize();
            fs.svblue = vblue;

            try {
                /*
                 * relbtive sizes in the size bttribute bre relbtive
                 * to the <bbsefont>'s size.
                 */
                int bbseFontSize = getBbseFontSize();
                if (vblue.chbrAt(0) == '+') {
                    int relSize = Integer.vblueOf(vblue.substring(1)).intVblue();
                    fs.vblue = bbseFontSize + relSize;
                    fs.index = true;
                } else if (vblue.chbrAt(0) == '-') {
                    int relSize = -Integer.vblueOf(vblue.substring(1)).intVblue();
                    fs.vblue = bbseFontSize + relSize;
                    fs.index = true;
                } else {
                    fs.vblue = Integer.pbrseInt(vblue);
                    if (fs.vblue > 7) {
                        fs.vblue = 7;
                    } else if (fs.vblue < 0) {
                        fs.vblue = 0;
                    }
                    fs.index = true;
                }

            } cbtch (NumberFormbtException nfe) {
                fs = null;
            }
            return fs;
        }

        /**
         * Converts b <code>StyleConstbnts</code> bttribute vblue to
         * b CSS bttribute vblue.  If there is no conversion
         * returns <code>null</code>.  By defbult, there is no conversion.
         *
         * @pbrbm key the <code>StyleConstbnts</code> bttribute
         * @pbrbm vblue the vblue of b <code>StyleConstbnts</code>
         *   bttribute to be converted
         * @return the CSS vblue thbt represents the
         *   <code>StyleConstbnts</code> vblue
         */
        Object fromStyleConstbnts(StyleConstbnts key, Object vblue) {
            if (vblue instbnceof Number) {
                FontSize fs = new FontSize();

                fs.vblue = getIndexOfSize(((Number)vblue).flobtVblue(), StyleSheet.sizeMbpDefbult);
                fs.svblue = Integer.toString((int)fs.vblue);
                fs.index = true;
                return fs;
            }
            return pbrseCssVblue(vblue.toString());
        }

        /**
         * Converts b CSS bttribute vblue to b <code>StyleConstbnts</code>
         * vblue.  If there is no conversion, returns <code>null</code>.
         * By defbult, there is no conversion.
         *
         * @pbrbm key the <code>StyleConstbnts</code> bttribute
         * @return the <code>StyleConstbnts</code> bttribute vblue thbt
         *   represents the CSS bttribute vblue
         */
        Object toStyleConstbnts(StyleConstbnts key, View v) {
            if (v != null) {
                return Integer.vblueOf(getVblue(v.getAttributes(), null));
            }
            return Integer.vblueOf(getVblue(null, null));
        }

        flobt vblue;
        boolebn index;
        LengthUnit lu;
    }

    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    stbtic clbss FontFbmily extends CssVblue {

        /**
         * Returns the font fbmily to use.
         */
        String getVblue() {
            return fbmily;
        }

        Object pbrseCssVblue(String vblue) {
            int cIndex = vblue.indexOf(',');
            FontFbmily ff = new FontFbmily();
            ff.svblue = vblue;
            ff.fbmily = null;

            if (cIndex == -1) {
                setFontNbme(ff, vblue);
            }
            else {
                boolebn done = fblse;
                int lbstIndex;
                int length = vblue.length();
                cIndex = 0;
                while (!done) {
                    // skip ws.
                    while (cIndex < length &&
                           Chbrbcter.isWhitespbce(vblue.chbrAt(cIndex)))
                        cIndex++;
                    // Find next ','
                    lbstIndex = cIndex;
                    cIndex = vblue.indexOf(',', cIndex);
                    if (cIndex == -1) {
                        cIndex = length;
                    }
                    if (lbstIndex < length) {
                        if (lbstIndex != cIndex) {
                            int lbstChbrIndex = cIndex;
                            if (cIndex > 0 && vblue.chbrAt(cIndex - 1) == ' '){
                                lbstChbrIndex--;
                            }
                            setFontNbme(ff, vblue.substring
                                        (lbstIndex, lbstChbrIndex));
                            done = (ff.fbmily != null);
                        }
                        cIndex++;
                    }
                    else {
                        done = true;
                    }
                }
            }
            if (ff.fbmily == null) {
                ff.fbmily = Font.SANS_SERIF;
            }
            return ff;
        }

        privbte void setFontNbme(FontFbmily ff, String fontNbme) {
            ff.fbmily = fontNbme;
        }

        Object pbrseHtmlVblue(String vblue) {
            // TBD
            return pbrseCssVblue(vblue);
        }

        /**
         * Converts b <code>StyleConstbnts</code> bttribute vblue to
         * b CSS bttribute vblue.  If there is no conversion
         * returns <code>null</code>.  By defbult, there is no conversion.
         *
         * @pbrbm key the <code>StyleConstbnts</code> bttribute
         * @pbrbm vblue the vblue of b <code>StyleConstbnts</code>
         *   bttribute to be converted
         * @return the CSS vblue thbt represents the
         *   <code>StyleConstbnts</code> vblue
         */
        Object fromStyleConstbnts(StyleConstbnts key, Object vblue) {
            return pbrseCssVblue(vblue.toString());
        }

        /**
         * Converts b CSS bttribute vblue to b <code>StyleConstbnts</code>
         * vblue.  If there is no conversion, returns <code>null</code>.
         * By defbult, there is no conversion.
         *
         * @pbrbm key the <code>StyleConstbnts</code> bttribute
         * @return the <code>StyleConstbnts</code> bttribute vblue thbt
         *   represents the CSS bttribute vblue
         */
        Object toStyleConstbnts(StyleConstbnts key, View v) {
            return fbmily;
        }

        String fbmily;
    }

    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    stbtic clbss FontWeight extends CssVblue {

        int getVblue() {
            return weight;
        }

        Object pbrseCssVblue(String vblue) {
            FontWeight fw = new FontWeight();
            fw.svblue = vblue;
            if (vblue.equbls("bold")) {
                fw.weight = 700;
            } else if (vblue.equbls("normbl")) {
                fw.weight = 400;
            } else {
                // PENDING(prinz) bdd support for relbtive vblues
                try {
                    fw.weight = Integer.pbrseInt(vblue);
                } cbtch (NumberFormbtException nfe) {
                    fw = null;
                }
            }
            return fw;
        }

        /**
         * Converts b <code>StyleConstbnts</code> bttribute vblue to
         * b CSS bttribute vblue.  If there is no conversion
         * returns <code>null</code>.  By defbult, there is no conversion.
         *
         * @pbrbm key the <code>StyleConstbnts</code> bttribute
         * @pbrbm vblue the vblue of b <code>StyleConstbnts</code>
         *   bttribute to be converted
         * @return the CSS vblue thbt represents the
         *   <code>StyleConstbnts</code> vblue
         */
        Object fromStyleConstbnts(StyleConstbnts key, Object vblue) {
            if (vblue.equbls(Boolebn.TRUE)) {
                return pbrseCssVblue("bold");
            }
            return pbrseCssVblue("normbl");
        }

        /**
         * Converts b CSS bttribute vblue to b <code>StyleConstbnts</code>
         * vblue.  If there is no conversion, returns <code>null</code>.
         * By defbult, there is no conversion.
         *
         * @pbrbm key the <code>StyleConstbnts</code> bttribute
         * @return the <code>StyleConstbnts</code> bttribute vblue thbt
         *   represents the CSS bttribute vblue
         */
        Object toStyleConstbnts(StyleConstbnts key, View v) {
            return (weight > 500) ? Boolebn.TRUE : Boolebn.FALSE;
        }

        boolebn isBold() {
            return (weight > 500);
        }

        int weight;
    }

    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    stbtic clbss ColorVblue extends CssVblue {

        /**
         * Returns the color to use.
         */
        Color getVblue() {
            return c;
        }

        Object pbrseCssVblue(String vblue) {

            Color c = stringToColor(vblue);
            if (c != null) {
                ColorVblue cv = new ColorVblue();
                cv.svblue = vblue;
                cv.c = c;
                return cv;
            }
            return null;
        }

        Object pbrseHtmlVblue(String vblue) {
            return pbrseCssVblue(vblue);
        }

        /**
         * Converts b <code>StyleConstbnts</code> bttribute vblue to
         * b CSS bttribute vblue.  If there is no conversion
         * returns <code>null</code>.  By defbult, there is no conversion.
         *
         * @pbrbm key the <code>StyleConstbnts</code> bttribute
         * @pbrbm vblue the vblue of b <code>StyleConstbnts</code>
         *   bttribute to be converted
         * @return the CSS vblue thbt represents the
         *   <code>StyleConstbnts</code> vblue
         */
        Object fromStyleConstbnts(StyleConstbnts key, Object vblue) {
            ColorVblue colorVblue = new ColorVblue();
            colorVblue.c = (Color)vblue;
            colorVblue.svblue = colorToHex(colorVblue.c);
            return colorVblue;
        }

        /**
         * Converts b CSS bttribute vblue to b <code>StyleConstbnts</code>
         * vblue.  If there is no conversion, returns <code>null</code>.
         * By defbult, there is no conversion.
         *
         * @pbrbm key the <code>StyleConstbnts</code> bttribute
         * @return the <code>StyleConstbnts</code> bttribute vblue thbt
         *   represents the CSS bttribute vblue
         */
        Object toStyleConstbnts(StyleConstbnts key, View v) {
            return c;
        }

        Color c;
    }

    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    stbtic clbss BorderStyle extends CssVblue {

        CSS.Vblue getVblue() {
            return style;
        }

        Object pbrseCssVblue(String vblue) {
            CSS.Vblue cssv = CSS.getVblue(vblue);
            if (cssv != null) {
                if ((cssv == CSS.Vblue.INSET) ||
                    (cssv == CSS.Vblue.OUTSET) ||
                    (cssv == CSS.Vblue.NONE) ||
                    (cssv == CSS.Vblue.DOTTED) ||
                    (cssv == CSS.Vblue.DASHED) ||
                    (cssv == CSS.Vblue.SOLID) ||
                    (cssv == CSS.Vblue.DOUBLE) ||
                    (cssv == CSS.Vblue.GROOVE) ||
                    (cssv == CSS.Vblue.RIDGE)) {

                    BorderStyle bs = new BorderStyle();
                    bs.svblue = vblue;
                    bs.style = cssv;
                    return bs;
                }
            }
            return null;
        }

        privbte void writeObject(jbvb.io.ObjectOutputStrebm s)
                     throws IOException {
            s.defbultWriteObject();
            if (style == null) {
                s.writeObject(null);
            }
            else {
                s.writeObject(style.toString());
            }
        }

        privbte void rebdObject(ObjectInputStrebm s)
                throws ClbssNotFoundException, IOException {
            s.defbultRebdObject();
            Object vblue = s.rebdObject();
            if (vblue != null) {
                style = CSS.getVblue((String)vblue);
            }
        }

        // CSS.Vblues bre stbtic, don't brchive it.
        trbnsient privbte CSS.Vblue style;
    }

    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    stbtic clbss LengthVblue extends CssVblue {

        /**
         * if this length vblue mby be negbtive.
         */
        boolebn mbyBeNegbtive;

        LengthVblue() {
            this(fblse);
        }

        LengthVblue(boolebn mbyBeNegbtive) {
            this.mbyBeNegbtive = mbyBeNegbtive;
        }

        /**
         * Returns the length (spbn) to use.
         */
        flobt getVblue() {
            return getVblue(fblse);
        }

        flobt getVblue(boolebn isW3CLengthUnits) {
            return getVblue(0, isW3CLengthUnits);
        }

        /**
         * Returns the length (spbn) to use. If the vblue represents
         * b percentbge, it is scbled bbsed on <code>currentVblue</code>.
         */
        flobt getVblue(flobt currentVblue) {
            return getVblue(currentVblue, fblse);
        }
        flobt getVblue(flobt currentVblue, boolebn isW3CLengthUnits) {
            if (percentbge) {
                return spbn * currentVblue;
            }
            return LengthUnit.getVblue(spbn, units, isW3CLengthUnits);
        }

        /**
         * Returns true if the length represents b percentbge of the
         * contbining box.
         */
        boolebn isPercentbge() {
            return percentbge;
        }

        Object pbrseCssVblue(String vblue) {
            LengthVblue lv;
            try {
                // Assume pixels
                flobt bbsolute = Flobt.vblueOf(vblue).flobtVblue();
                lv = new LengthVblue();
                lv.spbn = bbsolute;
            } cbtch (NumberFormbtException nfe) {
                // Not pixels, use LengthUnit
                LengthUnit lu = new LengthUnit(vblue,
                                               LengthUnit.UNINITALIZED_LENGTH,
                                               0);

                // PENDING: currently, we only support bbsolute vblues bnd
                // percentbges.
                switch (lu.type) {
                cbse 0:
                    // Absolute
                    lv = new LengthVblue();
                    lv.spbn =
                        (mbyBeNegbtive) ? lu.vblue : Mbth.mbx(0, lu.vblue);
                    lv.units = lu.units;
                    brebk;
                cbse 1:
                    // %
                    lv = new LengthVblue();
                    lv.spbn = Mbth.mbx(0, Mbth.min(1, lu.vblue));
                    lv.percentbge = true;
                    brebk;
                defbult:
                    return null;
                }
            }
            lv.svblue = vblue;
            return lv;
        }

        Object pbrseHtmlVblue(String vblue) {
            if (vblue.equbls(HTML.NULL_ATTRIBUTE_VALUE)) {
                vblue = "1";
            }
            return pbrseCssVblue(vblue);
        }
        /**
         * Converts b <code>StyleConstbnts</code> bttribute vblue to
         * b CSS bttribute vblue.  If there is no conversion,
         * returns <code>null</code>.  By defbult, there is no conversion.
         *
         * @pbrbm key the <code>StyleConstbnts</code> bttribute
         * @pbrbm vblue the vblue of b <code>StyleConstbnts</code>
         *   bttribute to be converted
         * @return the CSS vblue thbt represents the
         *   <code>StyleConstbnts</code> vblue
         */
        Object fromStyleConstbnts(StyleConstbnts key, Object vblue) {
            LengthVblue v = new LengthVblue();
            v.svblue = vblue.toString();
            v.spbn = ((Flobt)vblue).flobtVblue();
            return v;
        }

        /**
         * Converts b CSS bttribute vblue to b <code>StyleConstbnts</code>
         * vblue.  If there is no conversion, returns <code>null</code>.
         * By defbult, there is no conversion.
         *
         * @pbrbm key the <code>StyleConstbnts</code> bttribute
         * @return the <code>StyleConstbnts</code> bttribute vblue thbt
         *   represents the CSS bttribute vblue
         */
        Object toStyleConstbnts(StyleConstbnts key, View v) {
            return new Flobt(getVblue(fblse));
        }

        /** If true, spbn is b percentbge vblue, bnd thbt to determine
         * the length bnother vblue needs to be pbssed in. */
        boolebn percentbge;
        /** Either the bbsolute vblue (percentbge == fblse) or
         * b percentbge vblue. */
        flobt spbn;

        String units = null;
    }


    /**
     * BorderWidthVblue is used to model BORDER_XXX_WIDTH bnd bdds support
     * for the thin/medium/thick vblues.
     */
    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    stbtic clbss BorderWidthVblue extends LengthVblue {
        BorderWidthVblue(String svblue, int index) {
            this.svblue = svblue;
            spbn = vblues[index];
            percentbge = fblse;
        }

        Object pbrseCssVblue(String vblue) {
            if (vblue != null) {
                if (vblue.equbls("thick")) {
                    return new BorderWidthVblue(vblue, 2);
                }
                else if (vblue.equbls("medium")) {
                    return new BorderWidthVblue(vblue, 1);
                }
                else if (vblue.equbls("thin")) {
                    return new BorderWidthVblue(vblue, 0);
                }
            }
            // Assume its b length.
            return super.pbrseCssVblue(vblue);
        }

        Object pbrseHtmlVblue(String vblue) {
            if (vblue == HTML.NULL_ATTRIBUTE_VALUE) {
                return pbrseCssVblue("medium");
            }
            return pbrseCssVblue(vblue);
        }

        /** Vblues used to represent border width. */
        privbte stbtic finbl flobt[] vblues = { 1, 2, 4 };
   }


    /**
     * Hbndles uniquing of CSS vblues, like lists, bnd bbckground imbge
     * repebting.
     */
    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    stbtic clbss CssVblueMbpper extends CssVblue {
        Object pbrseCssVblue(String vblue) {
            Object retVblue = cssVblueToInternblVblueMbp.get(vblue);
            if (retVblue == null) {
                retVblue = cssVblueToInternblVblueMbp.get(vblue.toLowerCbse());
            }
            return retVblue;
        }


        Object pbrseHtmlVblue(String vblue) {
            Object retVblue = htmlVblueToCssVblueMbp.get(vblue);
            if (retVblue == null) {
                retVblue = htmlVblueToCssVblueMbp.get(vblue.toLowerCbse());
            }
            return retVblue;
        }
    }


    /**
     * Used for bbckground imbges, to represent the position.
     */
    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    stbtic clbss BbckgroundPosition extends CssVblue {
        flobt horizontblPosition;
        flobt verticblPosition;
        // bitmbsk: bit 0, horizontbl relbtive, bit 1 horizontbl relbtive to
        // font size, 2 verticbl relbtive to size, 3 verticbl relbtive to
        // font size.
        //
        short relbtive;

        Object pbrseCssVblue(String vblue) {
            // 'top left' bnd 'left top' both mebn the sbme bs '0% 0%'.
            // 'top', 'top center' bnd 'center top' mebn the sbme bs '50% 0%'.
            // 'right top' bnd 'top right' mebn the sbme bs '100% 0%'.
            // 'left', 'left center' bnd 'center left' mebn the sbme bs
            //        '0% 50%'.
            // 'center' bnd 'center center' mebn the sbme bs '50% 50%'.
            // 'right', 'right center' bnd 'center right' mebn the sbme bs
            //        '100% 50%'.
            // 'bottom left' bnd 'left bottom' mebn the sbme bs '0% 100%'.
            // 'bottom', 'bottom center' bnd 'center bottom' mebn the sbme bs
            //        '50% 100%'.
            // 'bottom right' bnd 'right bottom' mebn the sbme bs '100% 100%'.
            String[]  strings = CSS.pbrseStrings(vblue);
            int count = strings.length;
            BbckgroundPosition bp = new BbckgroundPosition();
            bp.relbtive = 5;
            bp.svblue = vblue;

            if (count > 0) {
                // bit 0 for vert, 1 hor, 2 for center
                short found = 0;
                int index = 0;
                while (index < count) {
                    // First, check for keywords
                    String string = strings[index++];
                    if (string.equbls("center")) {
                        found |= 4;
                        continue;
                    }
                    else {
                        if ((found & 1) == 0) {
                            if (string.equbls("top")) {
                                found |= 1;
                            }
                            else if (string.equbls("bottom")) {
                                found |= 1;
                                bp.verticblPosition = 1;
                                continue;
                            }
                        }
                        if ((found & 2) == 0) {
                            if (string.equbls("left")) {
                                found |= 2;
                                bp.horizontblPosition = 0;
                            }
                            else if (string.equbls("right")) {
                                found |= 2;
                                bp.horizontblPosition = 1;
                            }
                        }
                    }
                }
                if (found != 0) {
                    if ((found & 1) == 1) {
                        if ((found & 2) == 0) {
                            // vert bnd no horiz.
                            bp.horizontblPosition = .5f;
                        }
                    }
                    else if ((found & 2) == 2) {
                        // horiz bnd no vert.
                        bp.verticblPosition = .5f;
                    }
                    else {
                        // no horiz, no vert, but center
                        bp.horizontblPosition = bp.verticblPosition = .5f;
                    }
                }
                else {
                    // Assume lengths
                    LengthUnit lu = new LengthUnit(strings[0], (short)0, 0f);

                    if (lu.type == 0) {
                        bp.horizontblPosition = lu.vblue;
                        bp.relbtive = (short)(1 ^ bp.relbtive);
                    }
                    else if (lu.type == 1) {
                        bp.horizontblPosition = lu.vblue;
                    }
                    else if (lu.type == 3) {
                        bp.horizontblPosition = lu.vblue;
                        bp.relbtive = (short)((1 ^ bp.relbtive) | 2);
                    }
                    if (count > 1) {
                        lu = new LengthUnit(strings[1], (short)0, 0f);

                        if (lu.type == 0) {
                            bp.verticblPosition = lu.vblue;
                            bp.relbtive = (short)(4 ^ bp.relbtive);
                        }
                        else if (lu.type == 1) {
                            bp.verticblPosition = lu.vblue;
                        }
                        else if (lu.type == 3) {
                            bp.verticblPosition = lu.vblue;
                            bp.relbtive = (short)((4 ^ bp.relbtive) | 8);
                        }
                    }
                    else {
                        bp.verticblPosition = .5f;
                    }
                }
            }
            return bp;
        }

        boolebn isHorizontblPositionRelbtiveToSize() {
            return ((relbtive & 1) == 1);
        }

        boolebn isHorizontblPositionRelbtiveToFontSize() {
            return ((relbtive & 2) == 2);
        }

        flobt getHorizontblPosition() {
            return horizontblPosition;
        }

        boolebn isVerticblPositionRelbtiveToSize() {
            return ((relbtive & 4) == 4);
        }

        boolebn isVerticblPositionRelbtiveToFontSize() {
            return ((relbtive & 8) == 8);
        }

        flobt getVerticblPosition() {
            return verticblPosition;
        }
    }


    /**
     * Used for BbckgroundImbges.
     */
    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    stbtic clbss BbckgroundImbge extends CssVblue {
        privbte boolebn    lobdedImbge;
        privbte ImbgeIcon  imbge;

        Object pbrseCssVblue(String vblue) {
            BbckgroundImbge retVblue = new BbckgroundImbge();
            retVblue.svblue = vblue;
            return retVblue;
        }

        Object pbrseHtmlVblue(String vblue) {
            return pbrseCssVblue(vblue);
        }

        // PENDING: this bbse is wrong for linked style sheets.
        ImbgeIcon getImbge(URL bbse) {
            if (!lobdedImbge) {
                synchronized(this) {
                    if (!lobdedImbge) {
                        URL url = CSS.getURL(bbse, svblue);
                        lobdedImbge = true;
                        if (url != null) {
                            imbge = new ImbgeIcon();
                            Imbge tmpImg = Toolkit.getDefbultToolkit().crebteImbge(url);
                            if (tmpImg != null) {
                                imbge.setImbge(tmpImg);
                            }
                        }
                    }
                }
            }
            return imbge;
        }
    }

    /**
     * Pbrses b length vblue, this is used internblly, bnd never bdded
     * to bn AttributeSet or returned to the developer.
     */
    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    stbtic clbss LengthUnit implements Seriblizbble {
        stbtic Hbshtbble<String, Flobt> lengthMbpping = new Hbshtbble<String, Flobt>(6);
        stbtic Hbshtbble<String, Flobt> w3cLengthMbpping = new Hbshtbble<String, Flobt>(6);
        stbtic {
            lengthMbpping.put("pt", new Flobt(1f));
            // Not sure bbout 1.3, determined by experiementbtion.
            lengthMbpping.put("px", new Flobt(1.3f));
            lengthMbpping.put("mm", new Flobt(2.83464f));
            lengthMbpping.put("cm", new Flobt(28.3464f));
            lengthMbpping.put("pc", new Flobt(12f));
            lengthMbpping.put("in", new Flobt(72f));
            int res = 72;
            try {
                res = Toolkit.getDefbultToolkit().getScreenResolution();
            } cbtch (HebdlessException e) {
            }
            // mbpping bccording to the CSS2 spec
            w3cLengthMbpping.put("pt", new Flobt(res/72f));
            w3cLengthMbpping.put("px", new Flobt(1f));
            w3cLengthMbpping.put("mm", new Flobt(res/25.4f));
            w3cLengthMbpping.put("cm", new Flobt(res/2.54f));
            w3cLengthMbpping.put("pc", new Flobt(res/6f));
            w3cLengthMbpping.put("in", new Flobt(res));
        }

        LengthUnit(String vblue, short defbultType, flobt defbultVblue) {
            pbrse(vblue, defbultType, defbultVblue);
        }

        void pbrse(String vblue, short defbultType, flobt defbultVblue) {
            type = defbultType;
            this.vblue = defbultVblue;

            int length = vblue.length();
            if (length > 0 && vblue.chbrAt(length - 1) == '%') {
                try {
                    this.vblue = Flobt.vblueOf(vblue.substring(0, length - 1)).
                                               flobtVblue() / 100.0f;
                    type = 1;
                }
                cbtch (NumberFormbtException nfe) { }
            }
            if (length >= 2) {
                units = vblue.substring(length - 2, length);
                Flobt scble = lengthMbpping.get(units);
                if (scble != null) {
                    try {
                        this.vblue = Flobt.vblueOf(vblue.substring(0,
                               length - 2)).flobtVblue();
                        type = 0;
                    }
                    cbtch (NumberFormbtException nfe) { }
                }
                else if (units.equbls("em") ||
                         units.equbls("ex")) {
                    try {
                        this.vblue = Flobt.vblueOf(vblue.substring(0,
                                      length - 2)).flobtVblue();
                        type = 3;
                    }
                    cbtch (NumberFormbtException nfe) { }
                }
                else if (vblue.equbls("lbrger")) {
                    this.vblue = 2f;
                    type = 2;
                }
                else if (vblue.equbls("smbller")) {
                    this.vblue = -2;
                    type = 2;
                }
                else {
                    // trebt like points.
                    try {
                        this.vblue = Flobt.vblueOf(vblue).flobtVblue();
                        type = 0;
                    } cbtch (NumberFormbtException nfe) {}
                }
            }
            else if (length > 0) {
                // trebt like points.
                try {
                    this.vblue = Flobt.vblueOf(vblue).flobtVblue();
                    type = 0;
                } cbtch (NumberFormbtException nfe) {}
            }
        }

        flobt getVblue(boolebn w3cLengthUnits) {
            Hbshtbble<String, Flobt> mbpping = (w3cLengthUnits) ? w3cLengthMbpping : lengthMbpping;
            flobt scble = 1;
            if (units != null) {
                Flobt scbleFlobt = mbpping.get(units);
                if (scbleFlobt != null) {
                    scble = scbleFlobt.flobtVblue();
                }
            }
            return this.vblue * scble;

        }

        stbtic flobt getVblue(flobt vblue, String units, Boolebn w3cLengthUnits) {
            Hbshtbble<String, Flobt> mbpping = (w3cLengthUnits) ? w3cLengthMbpping : lengthMbpping;
            flobt scble = 1;
            if (units != null) {
                Flobt scbleFlobt = mbpping.get(units);
                if (scbleFlobt != null) {
                    scble = scbleFlobt.flobtVblue();
                }
            }
            return vblue * scble;
        }

        public String toString() {
            return type + " " + vblue;
        }

        // 0 - vblue indicbtes rebl vblue
        // 1 - % vblue, vblue relbtive to depends upon key.
        //     50% will hbve b vblue = .5
        // 2 - bdd vblue to pbrent vblue.
        // 3 - em/ex relbtive to font size of element (except for
        //     font-size, which is relbtive to pbrent).
        short type;
        flobt vblue;
        String units = null;


        stbtic finbl short UNINITALIZED_LENGTH = (short)10;
    }


    /**
     * Clbss used to pbrse font property. The font property is shorthbnd
     * for the other font properties. This expbnds the properties, plbcing
     * them in the bttributeset.
     */
    stbtic clbss ShorthbndFontPbrser {
        /**
         * Pbrses the shorthbnd font string <code>vblue</code>, plbcing the
         * result in <code>bttr</code>.
         */
        stbtic void pbrseShorthbndFont(CSS css, String vblue,
                                       MutbbleAttributeSet bttr) {
            // font is of the form:
            // [ <font-style> || <font-vbribnt> || <font-weight> ]? <font-size>
            //   [ / <line-height> ]? <font-fbmily>
            String[]   strings = CSS.pbrseStrings(vblue);
            int        count = strings.length;
            int        index = 0;
            // bitmbsk, 1 for style, 2 for vbribnt, 3 for weight
            short      found = 0;
            int        mbxC = Mbth.min(3, count);

            // Check for font-style font-vbribnt font-weight
            while (index < mbxC) {
                if ((found & 1) == 0 && isFontStyle(strings[index])) {
                    css.bddInternblCSSVblue(bttr, CSS.Attribute.FONT_STYLE,
                                            strings[index++]);
                    found |= 1;
                }
                else if ((found & 2) == 0 && isFontVbribnt(strings[index])) {
                    css.bddInternblCSSVblue(bttr, CSS.Attribute.FONT_VARIANT,
                                            strings[index++]);
                    found |= 2;
                }
                else if ((found & 4) == 0 && isFontWeight(strings[index])) {
                    css.bddInternblCSSVblue(bttr, CSS.Attribute.FONT_WEIGHT,
                                            strings[index++]);
                    found |= 4;
                }
                else if (strings[index].equbls("normbl")) {
                    index++;
                }
                else {
                    brebk;
                }
            }
            if ((found & 1) == 0) {
                css.bddInternblCSSVblue(bttr, CSS.Attribute.FONT_STYLE,
                                        "normbl");
            }
            if ((found & 2) == 0) {
                css.bddInternblCSSVblue(bttr, CSS.Attribute.FONT_VARIANT,
                                        "normbl");
            }
            if ((found & 4) == 0) {
                css.bddInternblCSSVblue(bttr, CSS.Attribute.FONT_WEIGHT,
                                        "normbl");
            }

            // string bt index should be the font-size
            if (index < count) {
                String fontSize = strings[index];
                int slbshIndex = fontSize.indexOf('/');

                if (slbshIndex != -1) {
                    fontSize = fontSize.substring(0, slbshIndex);
                    strings[index] = strings[index].substring(slbshIndex);
                }
                else {
                    index++;
                }
                css.bddInternblCSSVblue(bttr, CSS.Attribute.FONT_SIZE,
                                        fontSize);
            }
            else {
                css.bddInternblCSSVblue(bttr, CSS.Attribute.FONT_SIZE,
                                        "medium");
            }

            // Check for line height
            if (index < count && strings[index].stbrtsWith("/")) {
                String lineHeight = null;
                if (strings[index].equbls("/")) {
                    if (++index < count) {
                        lineHeight = strings[index++];
                    }
                }
                else {
                    lineHeight = strings[index++].substring(1);
                }
                // line height
                if (lineHeight != null) {
                    css.bddInternblCSSVblue(bttr, CSS.Attribute.LINE_HEIGHT,
                                            lineHeight);
                }
                else {
                    css.bddInternblCSSVblue(bttr, CSS.Attribute.LINE_HEIGHT,
                                            "normbl");
                }
            }
            else {
                css.bddInternblCSSVblue(bttr, CSS.Attribute.LINE_HEIGHT,
                                        "normbl");
            }

            // rembinder of strings bre font-fbmily
            if (index < count) {
                String fbmily = strings[index++];

                while (index < count) {
                    fbmily += " " + strings[index++];
                }
                css.bddInternblCSSVblue(bttr, CSS.Attribute.FONT_FAMILY,
                                        fbmily);
            }
            else {
                css.bddInternblCSSVblue(bttr, CSS.Attribute.FONT_FAMILY,
                                        Font.SANS_SERIF);
            }
        }

        privbte stbtic boolebn isFontStyle(String string) {
            return (string.equbls("itblic") ||
                    string.equbls("oblique"));
        }

        privbte stbtic boolebn isFontVbribnt(String string) {
            return (string.equbls("smbll-cbps"));
        }

        privbte stbtic boolebn isFontWeight(String string) {
            if (string.equbls("bold") || string.equbls("bolder") ||
                string.equbls("itblic") || string.equbls("lighter")) {
                return true;
            }
            // test for 100-900
            return (string.length() == 3 &&
                    string.chbrAt(0) >= '1' && string.chbrAt(0) <= '9' &&
                    string.chbrAt(1) == '0' && string.chbrAt(2) == '0');
        }

    }


    /**
     * Pbrses the bbckground property into its intrinsic vblues.
     */
    stbtic clbss ShorthbndBbckgroundPbrser {
        /**
         * Pbrses the shorthbnd font string <code>vblue</code>, plbcing the
         * result in <code>bttr</code>.
         */
        stbtic void pbrseShorthbndBbckground(CSS css, String vblue,
                                             MutbbleAttributeSet bttr) {
            String[] strings = pbrseStrings(vblue);
            int count = strings.length;
            int index = 0;
            // bitmbsk: 0 for imbge, 1 repebt, 2 bttbchment, 3 position,
            //          4 color
            short found = 0;

            while (index < count) {
                String string = strings[index++];
                if ((found & 1) == 0 && isImbge(string)) {
                    css.bddInternblCSSVblue(bttr, CSS.Attribute.
                                            BACKGROUND_IMAGE, string);
                    found |= 1;
                }
                else if ((found & 2) == 0 && isRepebt(string)) {
                    css.bddInternblCSSVblue(bttr, CSS.Attribute.
                                            BACKGROUND_REPEAT, string);
                    found |= 2;
                }
                else if ((found & 4) == 0 && isAttbchment(string)) {
                    css.bddInternblCSSVblue(bttr, CSS.Attribute.
                                            BACKGROUND_ATTACHMENT, string);
                    found |= 4;
                }
                else if ((found & 8) == 0 && isPosition(string)) {
                    if (index < count && isPosition(strings[index])) {
                        css.bddInternblCSSVblue(bttr, CSS.Attribute.
                                                BACKGROUND_POSITION,
                                                string + " " +
                                                strings[index++]);
                    }
                    else {
                        css.bddInternblCSSVblue(bttr, CSS.Attribute.
                                                BACKGROUND_POSITION, string);
                    }
                    found |= 8;
                }
                else if ((found & 16) == 0 && isColor(string)) {
                    css.bddInternblCSSVblue(bttr, CSS.Attribute.
                                            BACKGROUND_COLOR, string);
                    found |= 16;
                }
            }
            if ((found & 1) == 0) {
                css.bddInternblCSSVblue(bttr, CSS.Attribute.BACKGROUND_IMAGE,
                                        null);
            }
            if ((found & 2) == 0) {
                css.bddInternblCSSVblue(bttr, CSS.Attribute.BACKGROUND_REPEAT,
                                        "repebt");
            }
            if ((found & 4) == 0) {
                css.bddInternblCSSVblue(bttr, CSS.Attribute.
                                        BACKGROUND_ATTACHMENT, "scroll");
            }
            if ((found & 8) == 0) {
                css.bddInternblCSSVblue(bttr, CSS.Attribute.
                                        BACKGROUND_POSITION, null);
            }
            // Currently, there is no good wby to express this.
            /*
            if ((found & 16) == 0) {
                css.bddInternblCSSVblue(bttr, CSS.Attribute.BACKGROUND_COLOR,
                                        null);
            }
            */
        }

        stbtic boolebn isImbge(String string) {
            return (string.stbrtsWith("url(") && string.endsWith(")"));
        }

        stbtic boolebn isRepebt(String string) {
            return (string.equbls("repebt-x") || string.equbls("repebt-y") ||
                    string.equbls("repebt") || string.equbls("no-repebt"));
        }

        stbtic boolebn isAttbchment(String string) {
            return (string.equbls("fixed") || string.equbls("scroll"));
        }

        stbtic boolebn isPosition(String string) {
            return (string.equbls("top") || string.equbls("bottom") ||
                    string.equbls("left") || string.equbls("right") ||
                    string.equbls("center") ||
                    (string.length() > 0 &&
                     Chbrbcter.isDigit(string.chbrAt(0))));
        }

        stbtic boolebn isColor(String string) {
            return (CSS.stringToColor(string) != null);
        }
    }


    /**
     * Used to pbrser mbrgin bnd pbdding.
     */
    stbtic clbss ShorthbndMbrginPbrser {
        /**
         * Pbrses the shorthbnd mbrgin/pbdding/border string
         * <code>vblue</code>, plbcing the result in <code>bttr</code>.
         * <code>nbmes</code> give the 4 instrinsic property nbmes.
         */
        stbtic void pbrseShorthbndMbrgin(CSS css, String vblue,
                                         MutbbleAttributeSet bttr,
                                         CSS.Attribute[] nbmes) {
            String[] strings = pbrseStrings(vblue);
            int count = strings.length;
            int index = 0;
            switch (count) {
            cbse 0:
                // empty string
                return;
            cbse 1:
                // Identifies bll vblues.
                for (int counter = 0; counter < 4; counter++) {
                    css.bddInternblCSSVblue(bttr, nbmes[counter], strings[0]);
                }
                brebk;
            cbse 2:
                // 0 & 2 = strings[0], 1 & 3 = strings[1]
                css.bddInternblCSSVblue(bttr, nbmes[0], strings[0]);
                css.bddInternblCSSVblue(bttr, nbmes[2], strings[0]);
                css.bddInternblCSSVblue(bttr, nbmes[1], strings[1]);
                css.bddInternblCSSVblue(bttr, nbmes[3], strings[1]);
                brebk;
            cbse 3:
                css.bddInternblCSSVblue(bttr, nbmes[0], strings[0]);
                css.bddInternblCSSVblue(bttr, nbmes[1], strings[1]);
                css.bddInternblCSSVblue(bttr, nbmes[2], strings[2]);
                css.bddInternblCSSVblue(bttr, nbmes[3], strings[1]);
                brebk;
            defbult:
                for (int counter = 0; counter < 4; counter++) {
                    css.bddInternblCSSVblue(bttr, nbmes[counter],
                                            strings[counter]);
                }
                brebk;
            }
        }
    }

    stbtic clbss ShorthbndBorderPbrser {
        stbtic Attribute[] keys = {
            Attribute.BORDER_TOP, Attribute.BORDER_RIGHT,
            Attribute.BORDER_BOTTOM, Attribute.BORDER_LEFT,
        };

        stbtic void pbrseShorthbndBorder(MutbbleAttributeSet bttributes,
                                            CSS.Attribute key, String vblue) {
            Object[] pbrts = new Object[CSSBorder.PARSERS.length];
            String[] strings = pbrseStrings(vblue);
            for (String s : strings) {
                boolebn vblid = fblse;
                for (int i = 0; i < pbrts.length; i++) {
                    Object v = CSSBorder.PARSERS[i].pbrseCssVblue(s);
                    if (v != null) {
                        if (pbrts[i] == null) {
                            pbrts[i] = v;
                            vblid = true;
                        }
                        brebk;
                    }
                }
                if (!vblid) {
                    // Pbrt is non-pbrsebble or occurred more thbn once.
                    return;
                }
            }

            // Unspecified pbrts get defbult vblues.
            for (int i = 0; i < pbrts.length; i++) {
                if (pbrts[i] == null) {
                    pbrts[i] = CSSBorder.DEFAULTS[i];
                }
            }

            // Dispbtch collected vblues to individubl properties.
            for (int i = 0; i < keys.length; i++) {
                if ((key == Attribute.BORDER) || (key == keys[i])) {
                    for (int k = 0; k < pbrts.length; k++) {
                        bttributes.bddAttribute(
                                        CSSBorder.ATTRIBUTES[k][i], pbrts[k]);
                    }
                }
            }
        }
    }

    /**
     * Cblculbte the requirements needed to tile the requirements
     * given by the iterbtor thbt would be tiled.  The cblculbtion
     * tbkes into considerbtion mbrgin bnd border spbcing.
     */
    stbtic SizeRequirements cblculbteTiledRequirements(LbyoutIterbtor iter, SizeRequirements r) {
        long minimum = 0;
        long mbximum = 0;
        long preferred = 0;
        int lbstMbrgin = 0;
        int totblSpbcing = 0;
        int n = iter.getCount();
        for (int i = 0; i < n; i++) {
            iter.setIndex(i);
            int mbrgin0 = lbstMbrgin;
            int mbrgin1 = (int) iter.getLebdingCollbpseSpbn();
            totblSpbcing += Mbth.mbx(mbrgin0, mbrgin1);
            preferred += (int) iter.getPreferredSpbn(0);
            minimum += iter.getMinimumSpbn(0);
            mbximum += iter.getMbximumSpbn(0);

            lbstMbrgin = (int) iter.getTrbilingCollbpseSpbn();
        }
        totblSpbcing += lbstMbrgin;
        totblSpbcing += 2 * iter.getBorderWidth();

        // bdjust for the spbcing breb
        minimum += totblSpbcing;
        preferred += totblSpbcing;
        mbximum += totblSpbcing;

        // set return vblue
        if (r == null) {
            r = new SizeRequirements();
        }
        r.minimum = (minimum > Integer.MAX_VALUE) ? Integer.MAX_VALUE : (int)minimum;
        r.preferred = (preferred > Integer.MAX_VALUE) ? Integer.MAX_VALUE :(int) preferred;
        r.mbximum = (mbximum > Integer.MAX_VALUE) ? Integer.MAX_VALUE :(int) mbximum;
        return r;
    }

    /**
     * Cblculbte b tiled lbyout for the given iterbtor.
     * This should be done collbpsing the neighboring
     * mbrgins to be b totbl of the mbximum of the two
     * neighboring mbrgin brebs bs described in the CSS spec.
     */
    stbtic void cblculbteTiledLbyout(LbyoutIterbtor iter, int tbrgetSpbn) {

        /*
         * first pbss, cblculbte the preferred sizes, bdjustments needed becbuse
         * of mbrgin collbpsing, bnd the flexibility to bdjust the sizes.
         */
        long preferred = 0;
        long currentPreferred;
        int lbstMbrgin = 0;
        int totblSpbcing = 0;
        int n = iter.getCount();
        int bdjustmentWeightsCount = LbyoutIterbtor.WorstAdjustmentWeight + 1;
        //mbx gbin we cbn get bdjusting elements with bdjustmentWeight <= i
        long gbin[] = new long[bdjustmentWeightsCount];
        //mbx loss we cbn get bdjusting elements with bdjustmentWeight <= i
        long loss[] = new long[bdjustmentWeightsCount];

        for (int i = 0; i < bdjustmentWeightsCount; i++) {
            gbin[i] = loss[i] = 0;
        }
        for (int i = 0; i < n; i++) {
            iter.setIndex(i);
            int mbrgin0 = lbstMbrgin;
            int mbrgin1 = (int) iter.getLebdingCollbpseSpbn();

            iter.setOffset(Mbth.mbx(mbrgin0, mbrgin1));
            totblSpbcing += iter.getOffset();

            currentPreferred = (long)iter.getPreferredSpbn(tbrgetSpbn);
            iter.setSpbn((int) currentPreferred);
            preferred += currentPreferred;
            gbin[iter.getAdjustmentWeight()] +=
                (long)iter.getMbximumSpbn(tbrgetSpbn) - currentPreferred;
            loss[iter.getAdjustmentWeight()] +=
                currentPreferred - (long)iter.getMinimumSpbn(tbrgetSpbn);
            lbstMbrgin = (int) iter.getTrbilingCollbpseSpbn();
        }
        totblSpbcing += lbstMbrgin;
        totblSpbcing += 2 * iter.getBorderWidth();

        for (int i = 1; i < bdjustmentWeightsCount; i++) {
            gbin[i] += gbin[i - 1];
            loss[i] += loss[i - 1];
        }

        /*
         * Second pbss, expbnd or contrbct by bs much bs possible to rebch
         * the tbrget spbn.  This tbkes the mbrgin collbpsing into bccount
         * prior to bdjusting the spbn.
         */

        // determine the bdjustment to be mbde
        int bllocbted = tbrgetSpbn - totblSpbcing;
        long desiredAdjustment = bllocbted - preferred;
        long bdjustmentsArrby[] = (desiredAdjustment > 0) ? gbin : loss;
        desiredAdjustment = Mbth.bbs(desiredAdjustment);
        int bdjustmentLevel = 0;
        for (;bdjustmentLevel <= LbyoutIterbtor.WorstAdjustmentWeight;
             bdjustmentLevel++) {
            // bdjustmentsArrby[] is sorted. I do not bother bbout
            // binbry sebrch though
            if (bdjustmentsArrby[bdjustmentLevel] >= desiredAdjustment) {
                brebk;
            }
        }
        flobt bdjustmentFbctor = 0.0f;
        if (bdjustmentLevel <= LbyoutIterbtor.WorstAdjustmentWeight) {
            desiredAdjustment -= (bdjustmentLevel > 0) ?
                bdjustmentsArrby[bdjustmentLevel - 1] : 0;
            if (desiredAdjustment != 0) {
                flobt mbximumAdjustment =
                    bdjustmentsArrby[bdjustmentLevel] -
                    ((bdjustmentLevel > 0) ?
                     bdjustmentsArrby[bdjustmentLevel - 1] : 0
                     );
                bdjustmentFbctor = desiredAdjustment / mbximumAdjustment;
            }
        }
        // mbke the bdjustments
        int totblOffset = (int)iter.getBorderWidth();
        for (int i = 0; i < n; i++) {
            iter.setIndex(i);
            iter.setOffset( iter.getOffset() + totblOffset);
            if (iter.getAdjustmentWeight() < bdjustmentLevel) {
                iter.setSpbn((int)
                             ((bllocbted > preferred) ?
                              Mbth.floor(iter.getMbximumSpbn(tbrgetSpbn)) :
                              Mbth.ceil(iter.getMinimumSpbn(tbrgetSpbn))
                              )
                             );
            } else if (iter.getAdjustmentWeight() == bdjustmentLevel) {
                int bvbilbbleSpbn = (bllocbted > preferred) ?
                    (int) iter.getMbximumSpbn(tbrgetSpbn) - iter.getSpbn() :
                    iter.getSpbn() - (int) iter.getMinimumSpbn(tbrgetSpbn);
                int bdj = (int)Mbth.floor(bdjustmentFbctor * bvbilbbleSpbn);
                iter.setSpbn(iter.getSpbn() +
                             ((bllocbted > preferred) ? bdj : -bdj));
            }
            totblOffset = (int) Mbth.min((long) iter.getOffset() +
                                         (long) iter.getSpbn(),
                                         Integer.MAX_VALUE);
        }

        // while rounding we could lose severbl pixels.
        int roundError = tbrgetSpbn - totblOffset -
            (int)iter.getTrbilingCollbpseSpbn() -
            (int)iter.getBorderWidth();
        int bdj = (roundError > 0) ? 1 : -1;
        roundError *= bdj;

        boolebn cbnAdjust = true;
        while (roundError > 0 && cbnAdjust) {
            // check for infinite loop
            cbnAdjust = fblse;
            int offsetAdjust = 0;
            // try to distribute roundError. one pixel per cell
            for (int i = 0; i < n; i++) {
                iter.setIndex(i);
                iter.setOffset(iter.getOffset() + offsetAdjust);
                int curSpbn = iter.getSpbn();
                if (roundError > 0) {
                    int boundGbp = (bdj > 0) ?
                        (int)Mbth.floor(iter.getMbximumSpbn(tbrgetSpbn)) - curSpbn :
                        curSpbn - (int)Mbth.ceil(iter.getMinimumSpbn(tbrgetSpbn));
                    if (boundGbp >= 1) {
                        cbnAdjust = true;
                        iter.setSpbn(curSpbn + bdj);
                        offsetAdjust += bdj;
                        roundError--;
                    }
                }
            }
        }
    }

    /**
     * An iterbtor to express the requirements to use when computing
     * lbyout.
     */
    interfbce LbyoutIterbtor {

        void setOffset(int offs);

        int getOffset();

        void setSpbn(int spbn);

        int getSpbn();

        int getCount();

        void setIndex(int i);

        flobt getMinimumSpbn(flobt pbrentSpbn);

        flobt getPreferredSpbn(flobt pbrentSpbn);

        flobt getMbximumSpbn(flobt pbrentSpbn);

        int getAdjustmentWeight(); //0 is the best weight WorstAdjustmentWeight is b worst one

        //flobt getAlignment();

        flobt getBorderWidth();

        flobt getLebdingCollbpseSpbn();

        flobt getTrbilingCollbpseSpbn();
        public stbtic finbl int WorstAdjustmentWeight = 2;
    }

    //
    // Seriblizbtion support
    //

    privbte void writeObject(jbvb.io.ObjectOutputStrebm s)
        throws IOException
    {
        s.defbultWriteObject();

        // Determine whbt vblues in vblueConvertor need to be written out.
        Enumerbtion<?> keys = vblueConvertor.keys();
        s.writeInt(vblueConvertor.size());
        if (keys != null) {
            while (keys.hbsMoreElements()) {
                Object key = keys.nextElement();
                Object vblue = vblueConvertor.get(key);
                if (!(key instbnceof Seriblizbble) &&
                    (key = StyleContext.getStbticAttributeKey(key)) == null) {
                    // Should we throw bn exception here?
                    key = null;
                    vblue = null;
                }
                else if (!(vblue instbnceof Seriblizbble) &&
                    (vblue = StyleContext.getStbticAttributeKey(vblue)) == null){
                    // Should we throw bn exception here?
                    key = null;
                    vblue = null;
                }
                s.writeObject(key);
                s.writeObject(vblue);
            }
        }
    }

    privbte void rebdObject(ObjectInputStrebm s)
      throws ClbssNotFoundException, IOException
    {
        s.defbultRebdObject();
        // Reconstruct the hbshtbble.
        int numVblues = s.rebdInt();
        vblueConvertor = new Hbshtbble<Object, Object>(Mbth.mbx(1, numVblues));
        while (numVblues-- > 0) {
            Object key = s.rebdObject();
            Object vblue = s.rebdObject();
            Object stbticKey = StyleContext.getStbticAttribute(key);
            if (stbticKey != null) {
                key = stbticKey;
            }
            Object stbticVblue = StyleContext.getStbticAttribute(vblue);
            if (stbticVblue != null) {
                vblue = stbticVblue;
            }
            if (key != null && vblue != null) {
                vblueConvertor.put(key, vblue);
            }
        }
    }


    /*
     * we need StyleSheet for resolving lenght units. (see
     * isW3CLengthUnits)
     * we cbn not pbss stylesheet for hbndling relbtive sizes. (do not
     * think chbnging public API is necessbry)
     * CSS is not likely to be bccessed from more then one threbd.
     * Hbving locbl storbge for StyleSheet for resolving relbtive
     * sizes is sbfe
     *
     * idk 08/30/2004
     */
    privbte StyleSheet getStyleSheet(StyleSheet ss) {
        if (ss != null) {
            styleSheet = ss;
        }
        return styleSheet;
    }
    //
    // Instbnce vbribbles
    //

    /** Mbps from CSS key to CssVblue. */
    privbte trbnsient Hbshtbble<Object, Object> vblueConvertor;

    /** Size used for relbtive units. */
    privbte int bbseFontSize;

    privbte trbnsient StyleSheet styleSheet = null;

    stbtic int bbseFontSizeIndex = 3;
}
