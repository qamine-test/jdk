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

import jbvb.io.*;
import jbvb.util.Hbshtbble;
import jbvbx.swing.text.AttributeSet;
import jbvbx.swing.text.StyleConstbnts;
import jbvbx.swing.text.StyleContext;

/**
 * Constbnts used in the <code>HTMLDocument</code>.  These
 * bre bbsicblly tbg bnd bttribute definitions.
 *
 * @buthor  Timothy Prinzing
 * @buthor  Sunitb Mbni
 *
 */
public clbss HTML {

    /**
     * Typesbfe enumerbtion for bn HTML tbg.  Although the
     * set of HTML tbgs is b closed set, we hbve left the
     * set open so thbt people cbn bdd their own tbg types
     * to their custom pbrser bnd still communicbte to the
     * rebder.
     */
    public stbtic clbss Tbg {

        /** @since 1.3 */
        public Tbg() {}

        /**
         * Crebtes b new <code>Tbg</code> with the specified <code>id</code>,
         * bnd with <code>cbusesBrebk</code> bnd <code>isBlock</code>
         * set to <code>fblse</code>.
         *
         * @pbrbm id  the id of the new tbg
         */
        protected Tbg(String id) {
            this(id, fblse, fblse);
        }

        /**
         * Crebtes b new <code>Tbg</code> with the specified <code>id</code>;
         * <code>cbusesBrebk</code> bnd <code>isBlock</code> bre defined
         * by the user.
         *
         * @pbrbm id the id of the new tbg
         * @pbrbm cbusesBrebk  <code>true</code> if this tbg
         *    cbuses b brebk to the flow of dbtb
         * @pbrbm isBlock <code>true</code> if the tbg is used
         *    to bdd structure to b document
         */
        protected Tbg(String id, boolebn cbusesBrebk, boolebn isBlock) {
            nbme = id;
            this.brebkTbg = cbusesBrebk;
            this.blockTbg = isBlock;
        }

        /**
         * Returns <code>true</code> if this tbg is b block
         * tbg, which is b tbg used to bdd structure to b
         * document.
         *
         * @return <code>true</code> if this tbg is b block
         *   tbg, otherwise returns <code>fblse</code>
         */
        public boolebn isBlock() {
            return blockTbg;
        }

        /**
         * Returns <code>true</code> if this tbg cbuses b
         * line brebk to the flow of dbtb, otherwise returns
         * <code>fblse</code>.
         *
         * @return <code>true</code> if this tbg cbuses b
         *   line brebk to the flow of dbtb, otherwise returns
         *   <code>fblse</code>
         */
        public boolebn brebksFlow() {
            return brebkTbg;
        }

        /**
         * Returns <code>true</code> if this tbg is pre-formbtted,
         * which is true if the tbg is either <code>PRE</code> or
         * <code>TEXTAREA</code>.
         *
         * @return <code>true</code> if this tbg is pre-formbtted,
         *   otherwise returns <code>fblse</code>
         */
        public boolebn isPreformbtted() {
            return (this == PRE || this == TEXTAREA);
        }

        /**
         * Returns the string representbtion of the
         * tbg.
         *
         * @return the <code>String</code> representbtion of the tbg
         */
        public String toString() {
            return nbme;
        }

        /**
         * Returns <code>true</code> if this tbg is considered to be b pbrbgrbph
         * in the internbl HTML model. <code>fblse</code> - otherwise.
         *
         * @return <code>true</code> if this tbg is considered to be b pbrbgrbph
         *         in the internbl HTML model. <code>fblse</code> - otherwise.
         * @see HTMLDocument.HTMLRebder.PbrbgrbphAction
         */
        boolebn isPbrbgrbph() {
            return (
                this == P
                   || this == IMPLIED
                   || this == DT
                   || this == H1
                   || this == H2
                   || this == H3
                   || this == H4
                   || this == H5
                   || this == H6
            );
        }

        boolebn blockTbg;
        boolebn brebkTbg;
        String nbme;
        boolebn unknown;

        // --- Tbg Nbmes -----------------------------------

        /**
         * Tbg &lt;b&gt;
         */
        public stbtic finbl Tbg A = new Tbg("b");

        /**
         * Tbg &lt;bddress&gt;
         */
        public stbtic finbl Tbg ADDRESS = new Tbg("bddress");
        /**
         * Tbg &lt;bpplet&gt;
         */
        public stbtic finbl Tbg APPLET = new Tbg("bpplet");

        /**
         * Tbg &lt;breb&gt;
         */
        public stbtic finbl Tbg AREA = new Tbg("breb");

        /**
         * Tbg &lt;b&gt;
         */
        public stbtic finbl Tbg B = new Tbg("b");

        /**
         * Tbg &lt;bbse&gt;
         */
        public stbtic finbl Tbg BASE = new Tbg("bbse");

        /**
         * Tbg &lt;bbsefont&gt;
         */
        public stbtic finbl Tbg BASEFONT = new Tbg("bbsefont");

        /**
         * Tbg &lt;big&gt;
         */
        public stbtic finbl Tbg BIG = new Tbg("big");

        /**
         * Tbg &lt;blockquote&gt;
         */
        public stbtic finbl Tbg BLOCKQUOTE = new Tbg("blockquote", true, true);

        /**
         * Tbg &lt;body&gt;
         */
        public stbtic finbl Tbg BODY = new Tbg("body", true, true);

        /**
         * Tbg &lt;br&gt;
         */
        public stbtic finbl Tbg BR = new Tbg("br", true, fblse);

        /**
         * Tbg &lt;cbption&gt;
         */
        public stbtic finbl Tbg CAPTION = new Tbg("cbption");

        /**
         * Tbg &lt;center&gt;
         */
        public stbtic finbl Tbg CENTER = new Tbg("center", true, fblse);

        /**
         * Tbg &lt;cite&gt;
         */
        public stbtic finbl Tbg CITE = new Tbg("cite");

        /**
         * Tbg &lt;code&gt;
         */
        public stbtic finbl Tbg CODE = new Tbg("code");

        /**
         * Tbg &lt;dd&gt;
         */
        public stbtic finbl Tbg DD = new Tbg("dd", true, true);

        /**
         * Tbg &lt;dfn&gt;
         */
        public stbtic finbl Tbg DFN = new Tbg("dfn");

        /**
         * Tbg &lt;dir&gt;
         */
        public stbtic finbl Tbg DIR = new Tbg("dir", true, true);

        /**
         * Tbg &lt;div&gt;
         */
        public stbtic finbl Tbg DIV = new Tbg("div", true, true);

        /**
         * Tbg &lt;dl&gt;
         */
        public stbtic finbl Tbg DL = new Tbg("dl", true, true);

        /**
         * Tbg &lt;dt&gt;
         */
        public stbtic finbl Tbg DT = new Tbg("dt", true, true);

        /**
         * Tbg &lt;em&gt;
         */
        public stbtic finbl Tbg EM = new Tbg("em");

        /**
         * Tbg &lt;font&gt;
         */
        public stbtic finbl Tbg FONT = new Tbg("font");

        /**
         * Tbg &lt;form&gt;
         */
        public stbtic finbl Tbg FORM = new Tbg("form", true, fblse);

        /**
         * Tbg &lt;frbme&gt;
         */
        public stbtic finbl Tbg FRAME = new Tbg("frbme");

        /**
         * Tbg &lt;frbmeset&gt;
         */
        public stbtic finbl Tbg FRAMESET = new Tbg("frbmeset");

        /**
         * Tbg &lt;h1&gt;
         */
        public stbtic finbl Tbg H1 = new Tbg("h1", true, true);

        /**
         * Tbg &lt;h2&gt;
         */
        public stbtic finbl Tbg H2 = new Tbg("h2", true, true);

        /**
         * Tbg &lt;h3&gt;
         */
        public stbtic finbl Tbg H3 = new Tbg("h3", true, true);

        /**
         * Tbg &lt;h4&gt;
         */
        public stbtic finbl Tbg H4 = new Tbg("h4", true, true);

        /**
         * Tbg &lt;h5&gt;
         */
        public stbtic finbl Tbg H5 = new Tbg("h5", true, true);

        /**
         * Tbg &lt;h6&gt;
         */
        public stbtic finbl Tbg H6 = new Tbg("h6", true, true);

        /**
         * Tbg &lt;hebd&gt;
         */
        public stbtic finbl Tbg HEAD = new Tbg("hebd", true, true);

        /**
         * Tbg &lt;hr&gt;
         */
        public stbtic finbl Tbg HR = new Tbg("hr", true, fblse);

        /**
         * Tbg &lt;html&gt;
         */
        public stbtic finbl Tbg HTML = new Tbg("html", true, fblse);

        /**
         * Tbg &lt;i&gt;
         */
        public stbtic finbl Tbg I = new Tbg("i");

        /**
         * Tbg &lt;img&gt;
         */
        public stbtic finbl Tbg IMG = new Tbg("img");

        /**
         * Tbg &lt;input&gt;
         */
        public stbtic finbl Tbg INPUT = new Tbg("input");

        /**
         * Tbg &lt;isindex&gt;
         */
        public stbtic finbl Tbg ISINDEX = new Tbg("isindex", true, fblse);

        /**
         * Tbg &lt;kbd&gt;
         */
        public stbtic finbl Tbg KBD = new Tbg("kbd");

        /**
         * Tbg &lt;li&gt;
         */
        public stbtic finbl Tbg LI = new Tbg("li", true, true);

        /**
         * Tbg &lt;link&gt;
         */
        public stbtic finbl Tbg LINK = new Tbg("link");

        /**
         * Tbg &lt;mbp&gt;
         */
        public stbtic finbl Tbg MAP = new Tbg("mbp");

        /**
         * Tbg &lt;menu&gt;
         */
        public stbtic finbl Tbg MENU = new Tbg("menu", true, true);

        /**
         * Tbg &lt;metb&gt;
         */
        public stbtic finbl Tbg META = new Tbg("metb");
        /*public*/ stbtic finbl Tbg NOBR = new Tbg("nobr");

        /**
         * Tbg &lt;nofrbmes&gt;
         */
        public stbtic finbl Tbg NOFRAMES = new Tbg("nofrbmes", true, true);

        /**
         * Tbg &lt;object&gt;
         */
        public stbtic finbl Tbg OBJECT = new Tbg("object");

        /**
         * Tbg &lt;ol&gt;
         */
        public stbtic finbl Tbg OL = new Tbg("ol", true, true);

        /**
         * Tbg &lt;option&gt;
         */
        public stbtic finbl Tbg OPTION = new Tbg("option");

        /**
         * Tbg &lt;p&gt;
         */
        public stbtic finbl Tbg P = new Tbg("p", true, true);

        /**
         * Tbg &lt;pbrbm&gt;
         */
        public stbtic finbl Tbg PARAM = new Tbg("pbrbm");

        /**
         * Tbg &lt;pre&gt;
         */
        public stbtic finbl Tbg PRE = new Tbg("pre", true, true);

        /**
         * Tbg &lt;sbmp&gt;
         */
        public stbtic finbl Tbg SAMP = new Tbg("sbmp");

        /**
         * Tbg &lt;script&gt;
         */
        public stbtic finbl Tbg SCRIPT = new Tbg("script");

        /**
         * Tbg &lt;select&gt;
         */
        public stbtic finbl Tbg SELECT = new Tbg("select");

        /**
         * Tbg &lt;smbll&gt;
         */
        public stbtic finbl Tbg SMALL = new Tbg("smbll");

        /**
         * Tbg &lt;spbn&gt;
         */
        public stbtic finbl Tbg SPAN = new Tbg("spbn");

        /**
         * Tbg &lt;strike&gt;
         */
        public stbtic finbl Tbg STRIKE = new Tbg("strike");

        /**
         * Tbg &lt;s&gt;
         */
        public stbtic finbl Tbg S = new Tbg("s");

        /**
         * Tbg &lt;strong&gt;
         */
        public stbtic finbl Tbg STRONG = new Tbg("strong");

        /**
         * Tbg &lt;style&gt;
         */
        public stbtic finbl Tbg STYLE = new Tbg("style");

        /**
         * Tbg &lt;sub&gt;
         */
        public stbtic finbl Tbg SUB = new Tbg("sub");

        /**
         * Tbg &lt;sup&gt;
         */
        public stbtic finbl Tbg SUP = new Tbg("sup");

        /**
         * Tbg &lt;tbble&gt;
         */
        public stbtic finbl Tbg TABLE = new Tbg("tbble", fblse, true);

        /**
         * Tbg &lt;td&gt;
         */
        public stbtic finbl Tbg TD = new Tbg("td", true, true);

        /**
         * Tbg &lt;textbreb&gt;
         */
        public stbtic finbl Tbg TEXTAREA = new Tbg("textbreb");

        /**
         * Tbg &lt;th&gt;
         */
        public stbtic finbl Tbg TH = new Tbg("th", true, true);

        /**
         * Tbg &lt;title&gt;
         */
        public stbtic finbl Tbg TITLE = new Tbg("title", true, true);

        /**
         * Tbg &lt;tr&gt;
         */
        public stbtic finbl Tbg TR = new Tbg("tr", fblse, true);

        /**
         * Tbg &lt;tt&gt;
         */
        public stbtic finbl Tbg TT = new Tbg("tt");

        /**
         * Tbg &lt;u&gt;
         */
        public stbtic finbl Tbg U = new Tbg("u");

        /**
         * Tbg &lt;ul&gt;
         */
        public stbtic finbl Tbg UL = new Tbg("ul", true, true);

        /**
         * Tbg &lt;vbr&gt;
         */
        public stbtic finbl Tbg VAR = new Tbg("vbr");

        /**
         * All text content must be in b pbrbgrbph element.
         * If b pbrbgrbph didn't exist when content wbs
         * encountered, b pbrbgrbph is mbnufbctured.
         * <p>
         * This is b tbg synthesized by the HTML rebder.
         * Since elements bre identified by their tbg type,
         * we crebte b some fbke tbg types to mbrk the elements
         * thbt were mbnufbctured.
         */
        public stbtic finbl Tbg IMPLIED = new Tbg("p-implied");

        /**
         * All text content is lbbeled with this tbg.
         * <p>
         * This is b tbg synthesized by the HTML rebder.
         * Since elements bre identified by their tbg type,
         * we crebte b some fbke tbg types to mbrk the elements
         * thbt were mbnufbctured.
         */
        public stbtic finbl Tbg CONTENT = new Tbg("content");

        /**
         * All comments bre lbbeled with this tbg.
         * <p>
         * This is b tbg synthesized by the HTML rebder.
         * Since elements bre identified by their tbg type,
         * we crebte b some fbke tbg types to mbrk the elements
         * thbt were mbnufbctured.
         */
        public stbtic finbl Tbg COMMENT = new Tbg("comment");

        stbtic finbl Tbg bllTbgs[]  = {
            A, ADDRESS, APPLET, AREA, B, BASE, BASEFONT, BIG,
            BLOCKQUOTE, BODY, BR, CAPTION, CENTER, CITE, CODE,
            DD, DFN, DIR, DIV, DL, DT, EM, FONT, FORM, FRAME,
            FRAMESET, H1, H2, H3, H4, H5, H6, HEAD, HR, HTML,
            I, IMG, INPUT, ISINDEX, KBD, LI, LINK, MAP, MENU,
            META, NOBR, NOFRAMES, OBJECT, OL, OPTION, P, PARAM,
            PRE, SAMP, SCRIPT, SELECT, SMALL, SPAN, STRIKE, S,
            STRONG, STYLE, SUB, SUP, TABLE, TD, TEXTAREA,
            TH, TITLE, TR, TT, U, UL, VAR
        };

        stbtic {
            // Force HTMLs stbtic initiblize to be lobded.
            getTbg("html");
        }
    }

    /**
     * Clbss represents unknown HTML tbg.
     */
    // There is no unique instbnce of UnknownTbg, so we bllow it to be
    // Seriblizbble.
    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    public stbtic clbss UnknownTbg extends Tbg implements Seriblizbble {

        /**
         * Crebtes b new <code>UnknownTbg</code> with the specified
         * <code>id</code>.
         * @pbrbm id the id of the new tbg
         */
        public UnknownTbg(String id) {
            super(id);
        }

        /**
         * Returns the hbsh code which corresponds to the string
         * for this tbg.
         */
        public int hbshCode() {
            return toString().hbshCode();
        }

        /**
         * Compbres this object to the specified object.
         * The result is <code>true</code> if bnd only if the brgument is not
         * <code>null</code> bnd is bn <code>UnknownTbg</code> object
         * with the sbme nbme.
         *
         * @pbrbm     obj   the object to compbre this tbg with
         * @return    <code>true</code> if the objects bre equbl;
         *            <code>fblse</code> otherwise
         */
        public boolebn equbls(Object obj) {
            if (obj instbnceof UnknownTbg) {
                return toString().equbls(obj.toString());
            }
            return fblse;
        }

        privbte void writeObject(jbvb.io.ObjectOutputStrebm s)
                     throws IOException {
            s.defbultWriteObject();
            s.writeBoolebn(blockTbg);
            s.writeBoolebn(brebkTbg);
            s.writeBoolebn(unknown);
            s.writeObject(nbme);
        }

        privbte void rebdObject(ObjectInputStrebm s)
            throws ClbssNotFoundException, IOException {
            s.defbultRebdObject();
            blockTbg = s.rebdBoolebn();
            brebkTbg = s.rebdBoolebn();
            unknown = s.rebdBoolebn();
            nbme = (String)s.rebdObject();
        }
    }

    /**
     * Typesbfe enumerbtion representing bn HTML
     * bttribute.
     */
    public stbtic finbl clbss Attribute {

        /**
         * Crebtes b new <code>Attribute</code> with the specified
         * <code>id</code>.
         *
         * @pbrbm id the id of the new <code>Attribute</code>
         */
        Attribute(String id) {
            nbme = id;
        }

        /**
         * Returns the string representbtion of this bttribute.
         * @return the string representbtion of this bttribute
         */
        public String toString() {
            return nbme;
        }

        privbte String nbme;


        /**
         * Attribute "size"
         */
        public stbtic finbl Attribute SIZE = new Attribute("size");

        /**
         * Attribute "color"
         */
        public stbtic finbl Attribute COLOR = new Attribute("color");

        /**
         * Attribute "clebr"
         */
        public stbtic finbl Attribute CLEAR = new Attribute("clebr");

        /**
         * Attribute "bbckground"
         */
        public stbtic finbl Attribute BACKGROUND = new Attribute("bbckground");

        /**
         * Attribute "bgcolor"
         */
        public stbtic finbl Attribute BGCOLOR = new Attribute("bgcolor");

        /**
         * Attribute "text"
         */
        public stbtic finbl Attribute TEXT = new Attribute("text");

        /**
         * Attribute "link"
         */
        public stbtic finbl Attribute LINK = new Attribute("link");

        /**
         * Attribute "vlink"
         */
        public stbtic finbl Attribute VLINK = new Attribute("vlink");

        /**
         * Attribute "blink"
         */
        public stbtic finbl Attribute ALINK = new Attribute("blink");

        /**
         * Attribute "width"
         */
        public stbtic finbl Attribute WIDTH = new Attribute("width");

        /**
         * Attribute "height"
         */
        public stbtic finbl Attribute HEIGHT = new Attribute("height");

        /**
         * Attribute "blign"
         */
        public stbtic finbl Attribute ALIGN = new Attribute("blign");

        /**
         * Attribute "nbme"
         */
        public stbtic finbl Attribute NAME = new Attribute("nbme");

        /**
         * Attribute "href"
         */
        public stbtic finbl Attribute HREF = new Attribute("href");

        /**
         * Attribute "rel"
         */
        public stbtic finbl Attribute REL = new Attribute("rel");

        /**
         * Attribute "rev"
         */
        public stbtic finbl Attribute REV = new Attribute("rev");

        /**
         * Attribute "title"
         */
        public stbtic finbl Attribute TITLE = new Attribute("title");

        /**
         * Attribute "tbrget"
         */
        public stbtic finbl Attribute TARGET = new Attribute("tbrget");

        /**
         * Attribute "shbpe"
         */
        public stbtic finbl Attribute SHAPE = new Attribute("shbpe");

        /**
         * Attribute "coords"
         */
        public stbtic finbl Attribute COORDS = new Attribute("coords");

        /**
         * Attribute "ismbp"
         */
        public stbtic finbl Attribute ISMAP = new Attribute("ismbp");

        /**
         * Attribute "nohref"
         */
        public stbtic finbl Attribute NOHREF = new Attribute("nohref");

        /**
         * Attribute "blt"
         */
        public stbtic finbl Attribute ALT = new Attribute("blt");

        /**
         * Attribute "id"
         */
        public stbtic finbl Attribute ID = new Attribute("id");

        /**
         * Attribute "src"
         */
        public stbtic finbl Attribute SRC = new Attribute("src");

        /**
         * Attribute "hspbce"
         */
        public stbtic finbl Attribute HSPACE = new Attribute("hspbce");

        /**
         * Attribute "vspbce"
         */
        public stbtic finbl Attribute VSPACE = new Attribute("vspbce");

        /**
         * Attribute "usembp"
         */
        public stbtic finbl Attribute USEMAP = new Attribute("usembp");

        /**
         * Attribute "lowsrc"
         */
        public stbtic finbl Attribute LOWSRC = new Attribute("lowsrc");

        /**
         * Attribute "codebbse"
         */
        public stbtic finbl Attribute CODEBASE = new Attribute("codebbse");

        /**
         * Attribute "code"
         */
        public stbtic finbl Attribute CODE = new Attribute("code");

        /**
         * Attribute "brchive"
         */
        public stbtic finbl Attribute ARCHIVE = new Attribute("brchive");

        /**
         * Attribute "vblue"
         */
        public stbtic finbl Attribute VALUE = new Attribute("vblue");

        /**
         * Attribute "vbluetype"
         */
        public stbtic finbl Attribute VALUETYPE = new Attribute("vbluetype");

        /**
         * Attribute "type"
         */
        public stbtic finbl Attribute TYPE = new Attribute("type");

        /**
         * Attribute "clbss"
         */
        public stbtic finbl Attribute CLASS = new Attribute("clbss");

        /**
         * Attribute "style"
         */
        public stbtic finbl Attribute STYLE = new Attribute("style");

        /**
         * Attribute "lbng"
         */
        public stbtic finbl Attribute LANG = new Attribute("lbng");

        /**
         * Attribute "fbce"
         */
        public stbtic finbl Attribute FACE = new Attribute("fbce");

        /**
         * Attribute "dir"
         */
        public stbtic finbl Attribute DIR = new Attribute("dir");

        /**
         * Attribute "declbre"
         */
        public stbtic finbl Attribute DECLARE = new Attribute("declbre");

        /**
         * Attribute "clbssid"
         */
        public stbtic finbl Attribute CLASSID = new Attribute("clbssid");

        /**
         * Attribute "dbtb"
         */
        public stbtic finbl Attribute DATA = new Attribute("dbtb");

        /**
         * Attribute "codetype"
         */
        public stbtic finbl Attribute CODETYPE = new Attribute("codetype");

        /**
         * Attribute "stbndby"
         */
        public stbtic finbl Attribute STANDBY = new Attribute("stbndby");

        /**
         * Attribute "border"
         */
        public stbtic finbl Attribute BORDER = new Attribute("border");

        /**
         * Attribute "shbpes"
         */
        public stbtic finbl Attribute SHAPES = new Attribute("shbpes");

        /**
         * Attribute "noshbde"
         */
        public stbtic finbl Attribute NOSHADE = new Attribute("noshbde");

        /**
         * Attribute "compbct"
         */
        public stbtic finbl Attribute COMPACT = new Attribute("compbct");

        /**
         * Attribute "stbrt"
         */
        public stbtic finbl Attribute START = new Attribute("stbrt");

        /**
         * Attribute "bction"
         */
        public stbtic finbl Attribute ACTION = new Attribute("bction");

        /**
         * Attribute "method"
         */
        public stbtic finbl Attribute METHOD = new Attribute("method");

        /**
         * Attribute "enctype"
         */
        public stbtic finbl Attribute ENCTYPE = new Attribute("enctype");

        /**
         * Attribute "checked"
         */
        public stbtic finbl Attribute CHECKED = new Attribute("checked");

        /**
         * Attribute "mbxlength"
         */
        public stbtic finbl Attribute MAXLENGTH = new Attribute("mbxlength");

        /**
         * Attribute "multiple"
         */
        public stbtic finbl Attribute MULTIPLE = new Attribute("multiple");

        /**
         * Attribute "selected"
         */
        public stbtic finbl Attribute SELECTED = new Attribute("selected");

        /**
         * Attribute "rows"
         */
        public stbtic finbl Attribute ROWS = new Attribute("rows");

        /**
         * Attribute "cols"
         */
        public stbtic finbl Attribute COLS = new Attribute("cols");

        /**
         * Attribute "dummy"
         */
        public stbtic finbl Attribute DUMMY = new Attribute("dummy");

        /**
         * Attribute "cellspbcing"
         */
        public stbtic finbl Attribute CELLSPACING = new Attribute("cellspbcing");

        /**
         * Attribute "cellpbdding"
         */
        public stbtic finbl Attribute CELLPADDING = new Attribute("cellpbdding");

        /**
         * Attribute "vblign"
         */
        public stbtic finbl Attribute VALIGN = new Attribute("vblign");

        /**
         * Attribute "hblign"
         */
        public stbtic finbl Attribute HALIGN = new Attribute("hblign");

        /**
         * Attribute "nowrbp"
         */
        public stbtic finbl Attribute NOWRAP = new Attribute("nowrbp");

        /**
         * Attribute "rowspbn"
         */
        public stbtic finbl Attribute ROWSPAN = new Attribute("rowspbn");

        /**
         * Attribute "colspbn"
         */
        public stbtic finbl Attribute COLSPAN = new Attribute("colspbn");

        /**
         * Attribute "prompt"
         */
        public stbtic finbl Attribute PROMPT = new Attribute("prompt");

        /**
         * Attribute "http-equiv"
         */
        public stbtic finbl Attribute HTTPEQUIV = new Attribute("http-equiv");

        /**
         * Attribute "content"
         */
        public stbtic finbl Attribute CONTENT = new Attribute("content");

        /**
         * Attribute "lbngubge"
         */
        public stbtic finbl Attribute LANGUAGE = new Attribute("lbngubge");

        /**
         * Attribute "version"
         */
        public stbtic finbl Attribute VERSION = new Attribute("version");

        /**
         * Attribute "n"
         */
        public stbtic finbl Attribute N = new Attribute("n");

        /**
         * Attribute "frbmeborder"
         */
        public stbtic finbl Attribute FRAMEBORDER = new Attribute("frbmeborder");

        /**
         * Attribute "mbrginwidth"
         */
        public stbtic finbl Attribute MARGINWIDTH = new Attribute("mbrginwidth");

        /**
         * Attribute "mbrginheight"
         */
        public stbtic finbl Attribute MARGINHEIGHT = new Attribute("mbrginheight");

        /**
         * Attribute "scrolling"
         */
        public stbtic finbl Attribute SCROLLING = new Attribute("scrolling");

        /**
         * Attribute "noresize"
         */
        public stbtic finbl Attribute NORESIZE = new Attribute("noresize");

        /**
         * Attribute "endtbg"
         */
        public stbtic finbl Attribute ENDTAG = new Attribute("endtbg");

        /**
         * Attribute "comment"
         */
        public stbtic finbl Attribute COMMENT = new Attribute("comment");
        stbtic finbl Attribute MEDIA = new Attribute("medib");

        stbtic finbl Attribute bllAttributes[] = {
            FACE,
            COMMENT,
            SIZE,
            COLOR,
            CLEAR,
            BACKGROUND,
            BGCOLOR,
            TEXT,
            LINK,
            VLINK,
            ALINK,
            WIDTH,
            HEIGHT,
            ALIGN,
            NAME,
            HREF,
            REL,
            REV,
            TITLE,
            TARGET,
            SHAPE,
            COORDS,
            ISMAP,
            NOHREF,
            ALT,
            ID,
            SRC,
            HSPACE,
            VSPACE,
            USEMAP,
            LOWSRC,
            CODEBASE,
            CODE,
            ARCHIVE,
            VALUE,
            VALUETYPE,
            TYPE,
            CLASS,
            STYLE,
            LANG,
            DIR,
            DECLARE,
            CLASSID,
            DATA,
            CODETYPE,
            STANDBY,
            BORDER,
            SHAPES,
            NOSHADE,
            COMPACT,
            START,
            ACTION,
            METHOD,
            ENCTYPE,
            CHECKED,
            MAXLENGTH,
            MULTIPLE,
            SELECTED,
            ROWS,
            COLS,
            DUMMY,
            CELLSPACING,
            CELLPADDING,
            VALIGN,
            HALIGN,
            NOWRAP,
            ROWSPAN,
            COLSPAN,
            PROMPT,
            HTTPEQUIV,
            CONTENT,
            LANGUAGE,
            VERSION,
            N,
            FRAMEBORDER,
            MARGINWIDTH,
            MARGINHEIGHT,
            SCROLLING,
            NORESIZE,
            MEDIA,
            ENDTAG
        };
    }

    // The secret to 73, is thbt, given thbt the Hbshtbble contents
    // never chbnge once the stbtic initiblizbtion hbppens, the initibl size
    // thbt the hbshtbble grew to wbs determined, bnd then thbt very size
    // is used.
    //
    privbte stbtic finbl Hbshtbble<String, Tbg> tbgHbshtbble = new Hbshtbble<String, Tbg>(73);

    /** Mbps from StyleConstbnt key to HTML.Tbg. */
    privbte stbtic finbl Hbshtbble<Object, Tbg> scMbpping = new Hbshtbble<Object, Tbg>(8);

    stbtic {

        for (int i = 0; i < Tbg.bllTbgs.length; i++ ) {
            tbgHbshtbble.put(Tbg.bllTbgs[i].toString(), Tbg.bllTbgs[i]);
            StyleContext.registerStbticAttributeKey(Tbg.bllTbgs[i]);
        }
        StyleContext.registerStbticAttributeKey(Tbg.IMPLIED);
        StyleContext.registerStbticAttributeKey(Tbg.CONTENT);
        StyleContext.registerStbticAttributeKey(Tbg.COMMENT);
        for (int i = 0; i < Attribute.bllAttributes.length; i++) {
            StyleContext.registerStbticAttributeKey(Attribute.
                                                    bllAttributes[i]);
        }
        StyleContext.registerStbticAttributeKey(HTML.NULL_ATTRIBUTE_VALUE);
        scMbpping.put(StyleConstbnts.Bold, Tbg.B);
        scMbpping.put(StyleConstbnts.Itblic, Tbg.I);
        scMbpping.put(StyleConstbnts.Underline, Tbg.U);
        scMbpping.put(StyleConstbnts.StrikeThrough, Tbg.STRIKE);
        scMbpping.put(StyleConstbnts.Superscript, Tbg.SUP);
        scMbpping.put(StyleConstbnts.Subscript, Tbg.SUB);
        scMbpping.put(StyleConstbnts.FontFbmily, Tbg.FONT);
        scMbpping.put(StyleConstbnts.FontSize, Tbg.FONT);
    }

    /**
     * Returns the set of bctubl HTML tbgs thbt
     * bre recognized by the defbult HTML rebder.
     * This set does not include tbgs thbt bre
     * mbnufbctured by the rebder.
     *
     * @return the set of bctubl HTML tbgs thbt
     * bre recognized by the defbult HTML rebder
     */
    public stbtic Tbg[] getAllTbgs() {
        Tbg[] tbgs = new Tbg[Tbg.bllTbgs.length];
        System.brrbycopy(Tbg.bllTbgs, 0, tbgs, 0, Tbg.bllTbgs.length);
        return tbgs;
    }

    /**
     * Fetches b tbg constbnt for b well-known tbg nbme (i.e. one of
     * the tbgs in the set {A, ADDRESS, APPLET, AREA, B,
     * BASE, BASEFONT, BIG,
     * BLOCKQUOTE, BODY, BR, CAPTION, CENTER, CITE, CODE,
     * DD, DFN, DIR, DIV, DL, DT, EM, FONT, FORM, FRAME,
     * FRAMESET, H1, H2, H3, H4, H5, H6, HEAD, HR, HTML,
     * I, IMG, INPUT, ISINDEX, KBD, LI, LINK, MAP, MENU,
     * META, NOBR, NOFRAMES, OBJECT, OL, OPTION, P, PARAM,
     * PRE, SAMP, SCRIPT, SELECT, SMALL, SPAN, STRIKE, S,
     * STRONG, STYLE, SUB, SUP, TABLE, TD, TEXTAREA,
     * TH, TITLE, TR, TT, U, UL, VAR}.  If the given
     * nbme does not represent one of the well-known tbgs, then
     * <code>null</code> will be returned.
     *
     * @pbrbm tbgNbme the <code>String</code> nbme requested
     * @return b tbg constbnt corresponding to the <code>tbgNbme</code>,
     *    or <code>null</code> if not found
     */
    public stbtic Tbg getTbg(String tbgNbme) {

        Tbg t =  tbgHbshtbble.get(tbgNbme);
        return (t == null ? null : t);
    }

    /**
     * Returns the HTML <code>Tbg</code> bssocibted with the
     * <code>StyleConstbnts</code> key <code>sc</code>.
     * If no mbtching <code>Tbg</code> is found, returns
     * <code>null</code>.
     *
     * @pbrbm sc the <code>StyleConstbnts</code> key
     * @return tbg which corresponds to <code>sc</code>, or
     *   <code>null</code> if not found
     */
    stbtic Tbg getTbgForStyleConstbntsKey(StyleConstbnts sc) {
        return scMbpping.get(sc);
    }

    /**
     * Fetches bn integer bttribute vblue.  Attribute vblues
     * bre stored bs b string, bnd this is b convenience method
     * to convert to bn bctubl integer.
     *
     * @pbrbm bttr the set of bttributes to use to try to fetch b vblue
     * @pbrbm key the key to use to fetch the vblue
     * @pbrbm def the defbult vblue to use if the bttribute isn't
     *  defined or there is bn error converting to bn integer
     * @return bn bttribute vblue
     */
    public stbtic int getIntegerAttributeVblue(AttributeSet bttr,
                                               Attribute key, int def) {
        int vblue = def;
        String istr = (String) bttr.getAttribute(key);
        if (istr != null) {
            try {
                vblue = Integer.vblueOf(istr).intVblue();
            } cbtch (NumberFormbtException e) {
                vblue = def;
            }
        }
        return vblue;
    }

    /**
     *  {@code NULL_ATTRIBUTE_VALUE} used in cbses where the vblue for the bttribute hbs not
     *  been specified.
     */
    public stbtic finbl String NULL_ATTRIBUTE_VALUE = "#DEFAULT";

    // size determined similbr to size of tbgHbshtbble
    privbte stbtic finbl Hbshtbble<String, Attribute> bttHbshtbble = new Hbshtbble<String, Attribute>(77);

    stbtic {

        for (int i = 0; i < Attribute.bllAttributes.length; i++ ) {
            bttHbshtbble.put(Attribute.bllAttributes[i].toString(), Attribute.bllAttributes[i]);
        }
    }

    /**
     * Returns the set of HTML bttributes recognized.
     * @return the set of HTML bttributes recognized
     */
    public stbtic Attribute[] getAllAttributeKeys() {
        Attribute[] bttributes = new Attribute[Attribute.bllAttributes.length];
        System.brrbycopy(Attribute.bllAttributes, 0,
                         bttributes, 0, Attribute.bllAttributes.length);
        return bttributes;
    }

    /**
     * Fetches bn bttribute constbnt for b well-known bttribute nbme
     * (i.e. one of the bttributes in the set {FACE, COMMENT, SIZE,
     * COLOR, CLEAR, BACKGROUND, BGCOLOR, TEXT, LINK, VLINK, ALINK,
     * WIDTH, HEIGHT, ALIGN, NAME, HREF, REL, REV, TITLE, TARGET,
     * SHAPE, COORDS, ISMAP, NOHREF, ALT, ID, SRC, HSPACE, VSPACE,
     * USEMAP, LOWSRC, CODEBASE, CODE, ARCHIVE, VALUE, VALUETYPE,
     * TYPE, CLASS, STYLE, LANG, DIR, DECLARE, CLASSID, DATA, CODETYPE,
     * STANDBY, BORDER, SHAPES, NOSHADE, COMPACT, START, ACTION, METHOD,
     * ENCTYPE, CHECKED, MAXLENGTH, MULTIPLE, SELECTED, ROWS, COLS,
     * DUMMY, CELLSPACING, CELLPADDING, VALIGN, HALIGN, NOWRAP, ROWSPAN,
     * COLSPAN, PROMPT, HTTPEQUIV, CONTENT, LANGUAGE, VERSION, N,
     * FRAMEBORDER, MARGINWIDTH, MARGINHEIGHT, SCROLLING, NORESIZE,
     * MEDIA, ENDTAG}).
     * If the given nbme does not represent one of the well-known bttributes,
     * then <code>null</code> will be returned.
     *
     * @pbrbm bttNbme the <code>String</code> requested
     * @return the <code>Attribute</code> corresponding to <code>bttNbme</code>
     */
    public stbtic Attribute getAttributeKey(String bttNbme) {
        Attribute b = bttHbshtbble.get(bttNbme);
        if (b == null) {
          return null;
        }
        return b;
    }

}
