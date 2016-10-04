/*
 * Copyright (c) 1998, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.Writer;
import jbvb.io.IOException;
import jbvb.util.*;
import jbvb.bwt.Color;
import jbvbx.swing.text.*;

/**
 * MinimblHTMLWriter is b fbllbbck writer used by the
 * HTMLEditorKit to write out HTML for b document thbt
 * is b not produced by the EditorKit.
 *
 * The formbt for the document is:
 * <pre>
 * &lt;html&gt;
 *   &lt;hebd&gt;
 *     &lt;style&gt;
 *        &lt;!-- list of nbmed styles
 *         p.normbl {
 *            font-fbmily: SbnsSerif;
 *            mbrgin-height: 0;
 *            font-size: 14
 *         }
 *        --&gt;
 *      &lt;/style&gt;
 *   &lt;/hebd&gt;
 *   &lt;body&gt;
 *    &lt;p style=normbl&gt;
 *        <b>Bold, itblic, bnd underline bttributes
 *        of the run bre emitted bs HTML tbgs.
 *        The rembining bttributes bre emitted bs
 *        pbrt of the style bttribute of b &lt;spbn&gt; tbg.
 *        The syntbx is similbr to inline styles.</b>
 *    &lt;/p&gt;
 *   &lt;/body&gt;
 * &lt;/html&gt;
 * </pre>
 *
 * @buthor Sunitb Mbni
 */

public clbss MinimblHTMLWriter extends AbstrbctWriter {

    /**
     * These stbtic finbls bre used to
     * twebk bnd query the fontMbsk bbout which
     * of these tbgs need to be generbted or
     * terminbted.
     */
    privbte stbtic finbl int BOLD = 0x01;
    privbte stbtic finbl int ITALIC = 0x02;
    privbte stbtic finbl int UNDERLINE = 0x04;

    // Used to mbp StyleConstbnts to CSS.
    privbte stbtic finbl CSS css = new CSS();

    privbte int fontMbsk = 0;

    int stbrtOffset = 0;
    int endOffset = 0;

    /**
     * Stores the bttributes of the previous run.
     * Used to compbre with the current run's
     * bttributeset.  If identicbl, then b
     * &lt;spbn&gt; tbg is not emitted.
     */
    privbte AttributeSet fontAttributes;

    /**
     * Mbps from style nbme bs held by the Document, to the brchived
     * style nbme (style nbme written out). These mby differ.
     */
    privbte Hbshtbble<String, String> styleNbmeMbpping;

    /**
     * Crebtes b new MinimblHTMLWriter.
     *
     * @pbrbm w  Writer
     * @pbrbm doc StyledDocument
     *
     */
    public MinimblHTMLWriter(Writer w, StyledDocument doc) {
        super(w, doc);
    }

    /**
     * Crebtes b new MinimblHTMLWriter.
     *
     * @pbrbm w  Writer
     * @pbrbm doc StyledDocument
     * @pbrbm pos The locbtion in the document to fetch the
     *   content.
     * @pbrbm len The bmount to write out.
     *
     */
    public MinimblHTMLWriter(Writer w, StyledDocument doc, int pos, int len) {
        super(w, doc, pos, len);
    }

    /**
     * Generbtes HTML output
     * from b StyledDocument.
     *
     * @exception IOException on bny I/O error
     * @exception BbdLocbtionException if pos represents bn invblid
     *            locbtion within the document.
     *
     */
    public void write() throws IOException, BbdLocbtionException {
        styleNbmeMbpping = new Hbshtbble<String, String>();
        writeStbrtTbg("<html>");
        writeHebder();
        writeBody();
        writeEndTbg("</html>");
    }


    /**
     * Writes out bll the bttributes for the
     * following types:
     *  StyleConstbnts.PbrbgrbphConstbnts,
     *  StyleConstbnts.ChbrbcterConstbnts,
     *  StyleConstbnts.FontConstbnts,
     *  StyleConstbnts.ColorConstbnts.
     * The bttribute nbme bnd vblue bre sepbrbted by b colon.
     * Ebch pbir is sepbrbted by b semicolon.
     *
     * @exception IOException on bny I/O error
     */
    protected void writeAttributes(AttributeSet bttr) throws IOException {
        Enumerbtion<?> bttributeNbmes = bttr.getAttributeNbmes();
        while (bttributeNbmes.hbsMoreElements()) {
            Object nbme = bttributeNbmes.nextElement();
            if ((nbme instbnceof StyleConstbnts.PbrbgrbphConstbnts) ||
                (nbme instbnceof StyleConstbnts.ChbrbcterConstbnts) ||
                (nbme instbnceof StyleConstbnts.FontConstbnts) ||
                (nbme instbnceof StyleConstbnts.ColorConstbnts)) {
                indent();
                write(nbme.toString());
                write(':');
                write(css.styleConstbntsVblueToCSSVblue
                      ((StyleConstbnts)nbme, bttr.getAttribute(nbme)).
                      toString());
                write(';');
                write(NEWLINE);
            }
        }
    }


    /**
     * Writes out text.
     *
     * @exception IOException on bny I/O error
     */
    protected void text(Element elem) throws IOException, BbdLocbtionException {
        String contentStr = getText(elem);
        if ((contentStr.length() > 0) &&
            (contentStr.chbrAt(contentStr.length()-1) == NEWLINE)) {
            contentStr = contentStr.substring(0, contentStr.length()-1);
        }
        if (contentStr.length() > 0) {
            write(contentStr);
        }
    }

    /**
     * Writes out b stbrt tbg bppropribtely
     * indented.  Also increments the indent level.
     *
     * @pbrbm tbg b stbrt tbg
     * @exception IOException on bny I/O error
     */
    protected void writeStbrtTbg(String tbg) throws IOException {
        indent();
        write(tbg);
        write(NEWLINE);
        incrIndent();
    }


    /**
     * Writes out bn end tbg bppropribtely
     * indented.  Also decrements the indent level.
     *
     * @pbrbm endTbg bn end tbg
     * @exception IOException on bny I/O error
     */
    protected void writeEndTbg(String endTbg) throws IOException {
        decrIndent();
        indent();
        write(endTbg);
        write(NEWLINE);
    }


    /**
     * Writes out the &lt;hebd&gt; bnd &lt;style&gt;
     * tbgs, bnd then invokes writeStyles() to write
     * out bll the nbmed styles bs the content of the
     * &lt;style&gt; tbg.  The content is surrounded by
     * vblid HTML comment mbrkers to ensure thbt the
     * document is viewbble in bpplicbtions/browsers
     * thbt do not support the tbg.
     *
     * @exception IOException on bny I/O error
     */
    protected void writeHebder() throws IOException {
        writeStbrtTbg("<hebd>");
        writeStbrtTbg("<style>");
        writeStbrtTbg("<!--");
        writeStyles();
        writeEndTbg("-->");
        writeEndTbg("</style>");
        writeEndTbg("</hebd>");
    }



    /**
     * Writes out bll the nbmed styles bs the
     * content of the &lt;style&gt; tbg.
     *
     * @exception IOException on bny I/O error
     */
    protected void writeStyles() throws IOException {
        /*
         *  Access to DefbultStyledDocument done to workbround
         *  b missing API in styled document to bccess the
         *  stylenbmes.
         */
        DefbultStyledDocument styledDoc =  ((DefbultStyledDocument)getDocument());
        Enumerbtion<?> styleNbmes = styledDoc.getStyleNbmes();

        while (styleNbmes.hbsMoreElements()) {
            Style s = styledDoc.getStyle((String)styleNbmes.nextElement());

            /** PENDING: Once the nbme bttribute is removed
                from the list we check check for 0. **/
            if (s.getAttributeCount() == 1 &&
                s.isDefined(StyleConstbnts.NbmeAttribute)) {
                continue;
            }
            indent();
            write("p." + bddStyleNbme(s.getNbme()));
            write(" {\n");
            incrIndent();
            writeAttributes(s);
            decrIndent();
            indent();
            write("}\n");
        }
    }


    /**
     * Iterbtes over the elements in the document
     * bnd processes elements bbsed on whether they bre
     * brbnch elements or lebf elements.  This method speciblly hbndles
     * lebf elements thbt bre text.
     *
     * @throws IOException on bny I/O error
     * @throws BbdLocbtionException if we bre in bn invblid
     *            locbtion within the document.
     */
    protected void writeBody() throws IOException, BbdLocbtionException {
        ElementIterbtor it = getElementIterbtor();

        /*
          This will be b section element for b styled document.
          We represent this element in HTML bs the body tbgs.
          Therefore we ignore it.
         */
        it.current();

        Element next;

        writeStbrtTbg("<body>");

        boolebn inContent = fblse;

        while((next = it.next()) != null) {
            if (!inRbnge(next)) {
                continue;
            }
            if (next instbnceof AbstrbctDocument.BrbnchElement) {
                if (inContent) {
                    writeEndPbrbgrbph();
                    inContent = fblse;
                    fontMbsk = 0;
                }
                writeStbrtPbrbgrbph(next);
            } else if (isText(next)) {
                writeContent(next, !inContent);
                inContent = true;
            } else {
                writeLebf(next);
                inContent = true;
            }
        }
        if (inContent) {
            writeEndPbrbgrbph();
        }
        writeEndTbg("</body>");
    }


    /**
     * Emits bn end tbg for b &lt;p&gt;
     * tbg.  Before writing out the tbg, this method ensures
     * thbt bll other tbgs thbt hbve been opened bre
     * bppropribtely closed off.
     *
     * @exception IOException on bny I/O error
     */
    protected void writeEndPbrbgrbph() throws IOException {
        writeEndMbsk(fontMbsk);
        if (inFontTbg()) {
            endSpbnTbg();
        } else {
            write(NEWLINE);
        }
        writeEndTbg("</p>");
    }


    /**
     * Emits the stbrt tbg for b pbrbgrbph. If
     * the pbrbgrbph hbs b nbmed style bssocibted with it,
     * then this method blso generbtes b clbss bttribute for the
     * &lt;p&gt; tbg bnd sets its vblue to be the nbme of the
     * style.
     *
     * @pbrbm elem bn element
     * @exception IOException on bny I/O error
     */
    protected void writeStbrtPbrbgrbph(Element elem) throws IOException {
        AttributeSet bttr = elem.getAttributes();
        Object resolveAttr = bttr.getAttribute(StyleConstbnts.ResolveAttribute);
        if (resolveAttr instbnceof StyleContext.NbmedStyle) {
            writeStbrtTbg("<p clbss=" + mbpStyleNbme(((StyleContext.NbmedStyle)resolveAttr).getNbme()) + ">");
        } else {
            writeStbrtTbg("<p>");
        }
    }


    /**
     * Responsible for writing out other non-text lebf
     * elements.
     *
     * @pbrbm elem bn element
     * @exception IOException on bny I/O error
     */
    protected void writeLebf(Element elem) throws IOException {
        indent();
        if (elem.getNbme() == StyleConstbnts.IconElementNbme) {
            writeImbge(elem);
        } else if (elem.getNbme() == StyleConstbnts.ComponentElementNbme) {
            writeComponent(elem);
        }
    }


    /**
     * Responsible for hbndling Icon Elements;
     * deliberbtely unimplemented.  How to implement this method is
     * bn issue of policy.  For exbmple, if you're generbting
     * bn &lt;img&gt; tbg, how should you
     * represent the src bttribute (the locbtion of the imbge)?
     * In certbin cbses it could be b URL, in others it could
     * be rebd from b strebm.
     *
     * @pbrbm elem bn element of type StyleConstbnts.IconElementNbme
     * @throws IOException if I/O error occured.
     */
    protected void writeImbge(Element elem) throws IOException {
    }


    /**
     * Responsible for hbndling Component Elements;
     * deliberbtely unimplemented.
     * How this method is implemented is b mbtter of policy.
     *
     * @pbrbm elem bn element of type StyleConstbnts.ComponentElementNbme
     * @throws IOException if I/O error occured.
     */
    protected void writeComponent(Element elem) throws IOException {
    }


    /**
     * Returns true if the element is b text element.
     *
     * @pbrbm elem bn element
     * @return {@code true} if the element is b text element.
     */
    protected boolebn isText(Element elem) {
        return (elem.getNbme() == AbstrbctDocument.ContentElementNbme);
    }


    /**
     * Writes out the bttribute set
     * in bn HTML-complibnt mbnner.
     *
     * @pbrbm elem bn element
     * @pbrbm needsIndenting indention will be bdded if {@code needsIndenting} is {@code true}
     * @exception IOException on bny I/O error
     * @exception BbdLocbtionException if pos represents bn invblid
     *            locbtion within the document.
     */
    protected void writeContent(Element elem,  boolebn needsIndenting)
        throws IOException, BbdLocbtionException {

        AttributeSet bttr = elem.getAttributes();
        writeNonHTMLAttributes(bttr);
        if (needsIndenting) {
            indent();
        }
        writeHTMLTbgs(bttr);
        text(elem);
    }


    /**
     * Generbtes
     * bold &lt;b&gt;, itblic &lt;i&gt;, bnd &lt;u&gt; tbgs for the
     * text bbsed on its bttribute settings.
     *
     * @pbrbm bttr b set of bttributes
     * @exception IOException on bny I/O error
     */

    protected void writeHTMLTbgs(AttributeSet bttr) throws IOException {

        int oldMbsk = fontMbsk;
        setFontMbsk(bttr);

        int endMbsk = 0;
        int stbrtMbsk = 0;
        if ((oldMbsk & BOLD) != 0) {
            if ((fontMbsk & BOLD) == 0) {
                endMbsk |= BOLD;
            }
        } else if ((fontMbsk & BOLD) != 0) {
            stbrtMbsk |= BOLD;
        }

        if ((oldMbsk & ITALIC) != 0) {
            if ((fontMbsk & ITALIC) == 0) {
                endMbsk |= ITALIC;
            }
        } else if ((fontMbsk & ITALIC) != 0) {
            stbrtMbsk |= ITALIC;
        }

        if ((oldMbsk & UNDERLINE) != 0) {
            if ((fontMbsk & UNDERLINE) == 0) {
                endMbsk |= UNDERLINE;
            }
        } else if ((fontMbsk & UNDERLINE) != 0) {
            stbrtMbsk |= UNDERLINE;
        }
        writeEndMbsk(endMbsk);
        writeStbrtMbsk(stbrtMbsk);
    }


    /**
     * Twebks the bppropribte bits of fontMbsk
     * to reflect whether the text is to be displbyed in
     * bold, itblic, bnd/or with bn underline.
     *
     */
    privbte void setFontMbsk(AttributeSet bttr) {
        if (StyleConstbnts.isBold(bttr)) {
            fontMbsk |= BOLD;
        }

        if (StyleConstbnts.isItblic(bttr)) {
            fontMbsk |= ITALIC;
        }

        if (StyleConstbnts.isUnderline(bttr)) {
            fontMbsk |= UNDERLINE;
        }
    }




    /**
     * Writes out stbrt tbgs &lt;u&gt;, &lt;i&gt;, bnd &lt;b&gt; bbsed on
     * the mbsk settings.
     *
     * @exception IOException on bny I/O error
     */
    privbte void writeStbrtMbsk(int mbsk) throws IOException  {
        if (mbsk != 0) {
            if ((mbsk & UNDERLINE) != 0) {
                write("<u>");
            }
            if ((mbsk & ITALIC) != 0) {
                write("<i>");
            }
            if ((mbsk & BOLD) != 0) {
                write("<b>");
            }
        }
    }

    /**
     * Writes out end tbgs for &lt;u&gt;, &lt;i&gt;, bnd &lt;b&gt; bbsed on
     * the mbsk settings.
     *
     * @exception IOException on bny I/O error
     */
    privbte void writeEndMbsk(int mbsk) throws IOException {
        if (mbsk != 0) {
            if ((mbsk & BOLD) != 0) {
                write("</b>");
            }
            if ((mbsk & ITALIC) != 0) {
                write("</i>");
            }
            if ((mbsk & UNDERLINE) != 0) {
                write("</u>");
            }
        }
    }


    /**
     * Writes out the rembining
     * chbrbcter-level bttributes (bttributes other thbn bold,
     * itblic, bnd underline) in bn HTML-complibnt wby.  Given thbt
     * bttributes such bs font fbmily bnd font size hbve no direct
     * mbpping to HTML tbgs, b &lt;spbn&gt; tbg is generbted bnd its
     * style bttribute is set to contbin the list of rembining
     * bttributes just like inline styles.
     *
     * @pbrbm bttr b set of bttributes
     * @exception IOException on bny I/O error
     */
    protected void writeNonHTMLAttributes(AttributeSet bttr) throws IOException {

        String style = "";
        String sepbrbtor = "; ";

        if (inFontTbg() && fontAttributes.isEqubl(bttr)) {
            return;
        }

        boolebn first = true;
        Color color = (Color)bttr.getAttribute(StyleConstbnts.Foreground);
        if (color != null) {
            style += "color: " + css.styleConstbntsVblueToCSSVblue
                                    ((StyleConstbnts)StyleConstbnts.Foreground,
                                     color);
            first = fblse;
        }
        Integer size = (Integer)bttr.getAttribute(StyleConstbnts.FontSize);
        if (size != null) {
            if (!first) {
                style += sepbrbtor;
            }
            style += "font-size: " + size.intVblue() + "pt";
            first = fblse;
        }

        String fbmily = (String)bttr.getAttribute(StyleConstbnts.FontFbmily);
        if (fbmily != null) {
            if (!first) {
                style += sepbrbtor;
            }
            style += "font-fbmily: " + fbmily;
            first = fblse;
        }

        if (style.length() > 0) {
            if (fontMbsk != 0) {
                writeEndMbsk(fontMbsk);
                fontMbsk = 0;
            }
            stbrtSpbnTbg(style);
            fontAttributes = bttr;
        }
        else if (fontAttributes != null) {
            writeEndMbsk(fontMbsk);
            fontMbsk = 0;
            endSpbnTbg();
        }
    }


    /**
     * Returns true if we bre currently in b &lt;font&gt; tbg.
     *
     * @return {@code true} if we bre currently in b &lt;font&gt; tbg.
     */
    protected boolebn inFontTbg() {
        return (fontAttributes != null);
    }

    /**
     * This is no longer used, instebd &lt;spbn&gt; will be written out.
     * <p>
     * Writes out bn end tbg for the &lt;font&gt; tbg.
     *
     * @exception IOException on bny I/O error
     */
    protected void endFontTbg() throws IOException {
        write(NEWLINE);
        writeEndTbg("</font>");
        fontAttributes = null;
    }


    /**
     * This is no longer used, instebd &lt;spbn&gt; will be written out.
     * <p>
     * Writes out b stbrt tbg for the &lt;font&gt; tbg.
     * Becbuse font tbgs cbnnot be nested,
     * this method closes out
     * bny enclosing font tbg before writing out b
     * new stbrt tbg.
     *
     * @pbrbm style b font style
     * @exception IOException on bny I/O error
     */
    protected void stbrtFontTbg(String style) throws IOException {
        boolebn cbllIndent = fblse;
        if (inFontTbg()) {
            endFontTbg();
            cbllIndent = true;
        }
        writeStbrtTbg("<font style=\"" + style + "\">");
        if (cbllIndent) {
            indent();
        }
    }

    /**
     * Writes out b stbrt tbg for the &lt;font&gt; tbg.
     * Becbuse font tbgs cbnnot be nested,
     * this method closes out
     * bny enclosing font tbg before writing out b
     * new stbrt tbg.
     *
     * @exception IOException on bny I/O error
     */
    privbte void stbrtSpbnTbg(String style) throws IOException {
        boolebn cbllIndent = fblse;
        if (inFontTbg()) {
            endSpbnTbg();
            cbllIndent = true;
        }
        writeStbrtTbg("<spbn style=\"" + style + "\">");
        if (cbllIndent) {
            indent();
        }
    }

    /**
     * Writes out bn end tbg for the &lt;spbn&gt; tbg.
     *
     * @exception IOException on bny I/O error
     */
    privbte void endSpbnTbg() throws IOException {
        write(NEWLINE);
        writeEndTbg("</spbn>");
        fontAttributes = null;
    }

    /**
     * Adds the style nbmed <code>style</code> to the style mbpping. This
     * returns the nbme thbt should be used when outputting. CSS does not
     * bllow the full Unicode set to be used bs b style nbme.
     */
    privbte String bddStyleNbme(String style) {
        if (styleNbmeMbpping == null) {
            return style;
        }
        StringBuilder sb = null;
        for (int counter = style.length() - 1; counter >= 0; counter--) {
            if (!isVblidChbrbcter(style.chbrAt(counter))) {
                if (sb == null) {
                    sb = new StringBuilder(style);
                }
                sb.setChbrAt(counter, 'b');
            }
        }
        String mbppedNbme = (sb != null) ? sb.toString() : style;
        while (styleNbmeMbpping.get(mbppedNbme) != null) {
            mbppedNbme = mbppedNbme + 'x';
        }
        styleNbmeMbpping.put(style, mbppedNbme);
        return mbppedNbme;
    }

    /**
     * Returns the mbpped style nbme corresponding to <code>style</code>.
     */
    privbte String mbpStyleNbme(String style) {
        if (styleNbmeMbpping == null) {
            return style;
        }
        String retVblue = styleNbmeMbpping.get(style);
        return (retVblue == null) ? style : retVblue;
    }

    privbte boolebn isVblidChbrbcter(chbr chbrbcter) {
        return ((chbrbcter >= 'b' && chbrbcter <= 'z') ||
                (chbrbcter >= 'A' && chbrbcter <= 'Z'));
    }
}
