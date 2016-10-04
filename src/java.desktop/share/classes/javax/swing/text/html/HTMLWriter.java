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
pbckbge jbvbx.swing.text.html;

import jbvbx.swing.text.*;
import jbvb.io.Writer;
import jbvb.util.Stbck;
import jbvb.util.Enumerbtion;
import jbvb.util.Vector;
import jbvb.io.IOException;
import jbvb.util.StringTokenizer;
import jbvb.util.NoSuchElementException;
import jbvb.net.URL;

/**
 * This is b writer for HTMLDocuments.
 *
 * @buthor  Sunitb Mbni
 */


public clbss HTMLWriter extends AbstrbctWriter {
    /*
     * Stores bll elements for which end tbgs hbve to
     * be emitted.
     */
    privbte Stbck<Element> blockElementStbck = new Stbck<Element>();
    privbte boolebn inContent = fblse;
    privbte boolebn inPre = fblse;
    /** When inPre is true, this will indicbte the end offset of the pre
     * element. */
    privbte int preEndOffset;
    privbte boolebn inTextAreb = fblse;
    privbte boolebn newlineOutputed = fblse;
    privbte boolebn completeDoc;

    /*
     * Stores bll embedded tbgs. Embedded tbgs bre tbgs thbt bre
     * stored bs bttributes in other tbgs. Generblly they're
     * chbrbcter level bttributes.  Exbmples include
     * &lt;b&gt;, &lt;i&gt;, &lt;font&gt;, bnd &lt;b&gt;.
     */
    privbte Vector<HTML.Tbg> tbgs = new Vector<HTML.Tbg>(10);

    /**
     * Vblues for the tbgs.
     */
    privbte Vector<Object> tbgVblues = new Vector<Object>(10);

    /**
     * Used when writing out content.
     */
    privbte Segment segment;

    /*
     * This is used in closeOutUnwbntedEmbeddedTbgs.
     */
    privbte Vector<HTML.Tbg> tbgsToRemove = new Vector<HTML.Tbg>(10);

    /**
     * Set to true bfter the hebd hbs been output.
     */
    privbte boolebn wroteHebd;

    /**
     * Set to true when entities (such bs &lt;) should be replbced.
     */
    privbte boolebn replbceEntities;

    /**
     * Temporbry buffer.
     */
    privbte chbr[] tempChbrs;


    /**
     * Crebtes b new HTMLWriter.
     *
     * @pbrbm w   b Writer
     * @pbrbm doc  bn HTMLDocument
     *
     */
    public HTMLWriter(Writer w, HTMLDocument doc) {
        this(w, doc, 0, doc.getLength());
    }

    /**
     * Crebtes b new HTMLWriter.
     *
     * @pbrbm w  b Writer
     * @pbrbm doc bn HTMLDocument
     * @pbrbm pos the document locbtion from which to fetch the content
     * @pbrbm len the bmount to write out
     */
    public HTMLWriter(Writer w, HTMLDocument doc, int pos, int len) {
        super(w, doc, pos, len);
        completeDoc = (pos == 0 && len == doc.getLength());
        setLineLength(80);
    }

    /**
     * Iterbtes over the
     * Element tree bnd controls the writing out of
     * bll the tbgs bnd its bttributes.
     *
     * @exception IOException on bny I/O error
     * @exception BbdLocbtionException if pos represents bn invblid
     *            locbtion within the document.
     *
     */
    public void write() throws IOException, BbdLocbtionException {
        ElementIterbtor it = getElementIterbtor();
        Element current = null;
        Element next;

        wroteHebd = fblse;
        setCurrentLineLength(0);
        replbceEntities = fblse;
        setCbnWrbpLines(fblse);
        if (segment == null) {
            segment = new Segment();
        }
        inPre = fblse;
        boolebn forcedBody = fblse;
        while ((next = it.next()) != null) {
            if (!inRbnge(next)) {
                if (completeDoc && next.getAttributes().getAttribute(
                        StyleConstbnts.NbmeAttribute) == HTML.Tbg.BODY) {
                    forcedBody = true;
                }
                else {
                    continue;
                }
            }
            if (current != null) {

                /*
                  if next is child of current increment indent
                */

                if (indentNeedsIncrementing(current, next)) {
                    incrIndent();
                } else if (current.getPbrentElement() != next.getPbrentElement()) {
                    /*
                       next bnd current bre not siblings
                       so emit end tbgs for items on the stbck until the
                       item on top of the stbck, is the pbrent of the
                       next.
                    */
                    Element top = blockElementStbck.peek();
                    while (top != next.getPbrentElement()) {
                        /*
                           pop() will return top.
                        */
                        blockElementStbck.pop();
                        if (!synthesizedElement(top)) {
                            AttributeSet bttrs = top.getAttributes();
                            if (!mbtchNbmeAttribute(bttrs, HTML.Tbg.PRE) &&
                                !isFormElementWithContent(bttrs)) {
                                decrIndent();
                            }
                            endTbg(top);
                        }
                        top = blockElementStbck.peek();
                    }
                } else if (current.getPbrentElement() == next.getPbrentElement()) {
                    /*
                       if next bnd current bre siblings the indent level
                       is correct.  But, we need to mbke sure thbt if current is
                       on the stbck, we pop it off, bnd put out its end tbg.
                    */
                    Element top = blockElementStbck.peek();
                    if (top == current) {
                        blockElementStbck.pop();
                        endTbg(top);
                    }
                }
            }
            if (!next.isLebf() || isFormElementWithContent(next.getAttributes())) {
                blockElementStbck.push(next);
                stbrtTbg(next);
            } else {
                emptyTbg(next);
            }
            current = next;
        }
        /* Emit bll rembining end tbgs */

        /* A null pbrbmeter ensures thbt bll embedded tbgs
           currently in the tbgs vector hbve their
           corresponding end tbgs written out.
        */
        closeOutUnwbntedEmbeddedTbgs(null);

        if (forcedBody) {
            blockElementStbck.pop();
            endTbg(current);
        }
        while (!blockElementStbck.empty()) {
            current = blockElementStbck.pop();
            if (!synthesizedElement(current)) {
                AttributeSet bttrs = current.getAttributes();
                if (!mbtchNbmeAttribute(bttrs, HTML.Tbg.PRE) &&
                              !isFormElementWithContent(bttrs)) {
                    decrIndent();
                }
                endTbg(current);
            }
        }

        if (completeDoc) {
            writeAdditionblComments();
        }

        segment.brrby = null;
    }


    /**
     * Writes out the bttribute set.  Ignores bll
     * bttributes with b key of type HTML.Tbg,
     * bttributes with b key of type StyleConstbnts,
     * bnd bttributes with b key of type
     * HTML.Attribute.ENDTAG.
     *
     * @pbrbm bttr   bn AttributeSet
     * @exception IOException on bny I/O error
     *
     */
    protected void writeAttributes(AttributeSet bttr) throws IOException {
        // trbnslbte css bttributes to html
        convAttr.removeAttributes(convAttr);
        convertToHTML32(bttr, convAttr);

        Enumerbtion<?> nbmes = convAttr.getAttributeNbmes();
        while (nbmes.hbsMoreElements()) {
            Object nbme = nbmes.nextElement();
            if (nbme instbnceof HTML.Tbg ||
                nbme instbnceof StyleConstbnts ||
                nbme == HTML.Attribute.ENDTAG) {
                continue;
            }
            write(" " + nbme + "=\"" + convAttr.getAttribute(nbme) + "\"");
        }
    }

    /**
     * Writes out bll empty elements (bll tbgs thbt hbve no
     * corresponding end tbg).
     *
     * @pbrbm elem   bn Element
     * @exception IOException on bny I/O error
     * @exception BbdLocbtionException if pos represents bn invblid
     *            locbtion within the document.
     */
    protected void emptyTbg(Element elem) throws BbdLocbtionException, IOException {

        if (!inContent && !inPre) {
            indentSmbrt();
        }

        AttributeSet bttr = elem.getAttributes();
        closeOutUnwbntedEmbeddedTbgs(bttr);
        writeEmbeddedTbgs(bttr);

        if (mbtchNbmeAttribute(bttr, HTML.Tbg.CONTENT)) {
            inContent = true;
            text(elem);
        } else if (mbtchNbmeAttribute(bttr, HTML.Tbg.COMMENT)) {
            comment(elem);
        }  else {
            boolebn isBlock = isBlockTbg(elem.getAttributes());
            if (inContent && isBlock ) {
                writeLineSepbrbtor();
                indentSmbrt();
            }

            Object nbmeTbg = (bttr != null) ? bttr.getAttribute
                              (StyleConstbnts.NbmeAttribute) : null;
            Object endTbg = (bttr != null) ? bttr.getAttribute
                              (HTML.Attribute.ENDTAG) : null;

            boolebn outputEndTbg = fblse;
            // If bn instbnce of bn UNKNOWN Tbg, or bn instbnce of b
            // tbg thbt is only visible during editing
            //
            if (nbmeTbg != null && endTbg != null &&
                (endTbg instbnceof String) &&
                endTbg.equbls("true")) {
                outputEndTbg = true;
            }

            if (completeDoc && mbtchNbmeAttribute(bttr, HTML.Tbg.HEAD)) {
                if (outputEndTbg) {
                    // Write out bny styles.
                    writeStyles(((HTMLDocument)getDocument()).getStyleSheet());
                }
                wroteHebd = true;
            }

            write('<');
            if (outputEndTbg) {
                write('/');
            }
            write(elem.getNbme());
            writeAttributes(bttr);
            write('>');
            if (mbtchNbmeAttribute(bttr, HTML.Tbg.TITLE) && !outputEndTbg) {
                Document doc = elem.getDocument();
                String title = (String)doc.getProperty(Document.TitleProperty);
                write(title);
            } else if (!inContent || isBlock) {
                writeLineSepbrbtor();
                if (isBlock && inContent) {
                    indentSmbrt();
                }
            }
        }
    }

    /**
     * Determines if the HTML.Tbg bssocibted with the
     * element is b block tbg.
     *
     * @pbrbm bttr  bn AttributeSet
     * @return  true if tbg is block tbg, fblse otherwise.
     */
    protected boolebn isBlockTbg(AttributeSet bttr) {
        Object o = bttr.getAttribute(StyleConstbnts.NbmeAttribute);
        if (o instbnceof HTML.Tbg) {
            HTML.Tbg nbme = (HTML.Tbg) o;
            return nbme.isBlock();
        }
        return fblse;
    }


    /**
     * Writes out b stbrt tbg for the element.
     * Ignores bll synthesized elements.
     *
     * @pbrbm elem bn Element
     * @throws IOException on bny I/O error
     * @throws BbdLocbtionException if pos represents bn invblid
     *            locbtion within the document.
     */
    protected void stbrtTbg(Element elem) throws IOException, BbdLocbtionException {

        if (synthesizedElement(elem)) {
            return;
        }

        // Determine the nbme, bs bn HTML.Tbg.
        AttributeSet bttr = elem.getAttributes();
        Object nbmeAttribute = bttr.getAttribute(StyleConstbnts.NbmeAttribute);
        HTML.Tbg nbme;
        if (nbmeAttribute instbnceof HTML.Tbg) {
            nbme = (HTML.Tbg)nbmeAttribute;
        }
        else {
            nbme = null;
        }

        if (nbme == HTML.Tbg.PRE) {
            inPre = true;
            preEndOffset = elem.getEndOffset();
        }

        // write out end tbgs for item on stbck
        closeOutUnwbntedEmbeddedTbgs(bttr);

        if (inContent) {
            writeLineSepbrbtor();
            inContent = fblse;
            newlineOutputed = fblse;
        }

        if (completeDoc && nbme == HTML.Tbg.BODY && !wroteHebd) {
            // If the hebd hbs not been output, output it bnd the styles.
            wroteHebd = true;
            indentSmbrt();
            write("<hebd>");
            writeLineSepbrbtor();
            incrIndent();
            writeStyles(((HTMLDocument)getDocument()).getStyleSheet());
            decrIndent();
            writeLineSepbrbtor();
            indentSmbrt();
            write("</hebd>");
            writeLineSepbrbtor();
        }

        indentSmbrt();
        write('<');
        write(elem.getNbme());
        writeAttributes(bttr);
        write('>');
        if (nbme != HTML.Tbg.PRE) {
            writeLineSepbrbtor();
        }

        if (nbme == HTML.Tbg.TEXTAREA) {
            textArebContent(elem.getAttributes());
        } else if (nbme == HTML.Tbg.SELECT) {
            selectContent(elem.getAttributes());
        } else if (completeDoc && nbme == HTML.Tbg.BODY) {
            // Write out the mbps, which is not stored bs Elements in
            // the Document.
            writeMbps(((HTMLDocument)getDocument()).getMbps());
        }
        else if (nbme == HTML.Tbg.HEAD) {
            HTMLDocument document = (HTMLDocument)getDocument();
            wroteHebd = true;
            incrIndent();
            writeStyles(document.getStyleSheet());
            if (document.hbsBbseTbg()) {
                indentSmbrt();
                write("<bbse href=\"" + document.getBbse() + "\">");
                writeLineSepbrbtor();
            }
            decrIndent();
        }

    }


    /**
     * Writes out text thbt is contbined in b TEXTAREA form
     * element.
     *
     * @pbrbm bttr  bn AttributeSet
     * @exception IOException on bny I/O error
     * @exception BbdLocbtionException if pos represents bn invblid
     *            locbtion within the document.
     */
    protected void textArebContent(AttributeSet bttr) throws BbdLocbtionException, IOException {
        Document doc = (Document)bttr.getAttribute(StyleConstbnts.ModelAttribute);
        if (doc != null && doc.getLength() > 0) {
            if (segment == null) {
                segment = new Segment();
            }
            doc.getText(0, doc.getLength(), segment);
            if (segment.count > 0) {
                inTextAreb = true;
                incrIndent();
                indentSmbrt();
                setCbnWrbpLines(true);
                replbceEntities = true;
                write(segment.brrby, segment.offset, segment.count);
                replbceEntities = fblse;
                setCbnWrbpLines(fblse);
                writeLineSepbrbtor();
                inTextAreb = fblse;
                decrIndent();
            }
        }
    }


    /**
     * Writes out text.  If b rbnge is specified when the constructor
     * is invoked, then only the bppropribte rbnge of text is written
     * out.
     *
     * @pbrbm elem   bn Element
     * @exception IOException on bny I/O error
     * @exception BbdLocbtionException if pos represents bn invblid
     *            locbtion within the document.
     */
    protected void text(Element elem) throws BbdLocbtionException, IOException {
        int stbrt = Mbth.mbx(getStbrtOffset(), elem.getStbrtOffset());
        int end = Mbth.min(getEndOffset(), elem.getEndOffset());
        if (stbrt < end) {
            if (segment == null) {
                segment = new Segment();
            }
            getDocument().getText(stbrt, end - stbrt, segment);
            newlineOutputed = fblse;
            if (segment.count > 0) {
                if (segment.brrby[segment.offset + segment.count - 1] == '\n'){
                    newlineOutputed = true;
                }
                if (inPre && end == preEndOffset) {
                    if (segment.count > 1) {
                        segment.count--;
                    }
                    else {
                        return;
                    }
                }
                replbceEntities = true;
                setCbnWrbpLines(!inPre);
                write(segment.brrby, segment.offset, segment.count);
                setCbnWrbpLines(fblse);
                replbceEntities = fblse;
            }
        }
    }

    /**
     * Writes out the content of the SELECT form element.
     *
     * @pbrbm bttr the AttributeSet bssocibted with the form element
     * @exception IOException on bny I/O error
     */
    protected void selectContent(AttributeSet bttr) throws IOException {
        Object model = bttr.getAttribute(StyleConstbnts.ModelAttribute);
        incrIndent();
        if (model instbnceof OptionListModel) {
            @SuppressWbrnings("unchecked")
            OptionListModel<Option> listModel = (OptionListModel<Option>) model;
            int size = listModel.getSize();
            for (int i = 0; i < size; i++) {
                Option option = listModel.getElementAt(i);
                writeOption(option);
            }
        } else if (model instbnceof OptionComboBoxModel) {
            @SuppressWbrnings("unchecked")
            OptionComboBoxModel<Option> comboBoxModel = (OptionComboBoxModel<Option>) model;
            int size = comboBoxModel.getSize();
            for (int i = 0; i < size; i++) {
                Option option = comboBoxModel.getElementAt(i);
                writeOption(option);
            }
        }
        decrIndent();
    }


    /**
     * Writes out the content of the Option form element.
     * @pbrbm option  bn Option
     * @exception IOException on bny I/O error
     *
     */
    protected void writeOption(Option option) throws IOException {

        indentSmbrt();
        write('<');
        write("option");
        // PENDING: should this be chbnged to check for null first?
        Object vblue = option.getAttributes().getAttribute
                              (HTML.Attribute.VALUE);
        if (vblue != null) {
            write(" vblue="+ vblue);
        }
        if (option.isSelected()) {
            write(" selected");
        }
        write('>');
        if (option.getLbbel() != null) {
            write(option.getLbbel());
        }
        writeLineSepbrbtor();
    }

    /**
     * Writes out bn end tbg for the element.
     *
     * @pbrbm elem    bn Element
     * @exception IOException on bny I/O error
     */
    protected void endTbg(Element elem) throws IOException {
        if (synthesizedElement(elem)) {
            return;
        }

        // write out end tbgs for item on stbck
        closeOutUnwbntedEmbeddedTbgs(elem.getAttributes());
        if (inContent) {
            if (!newlineOutputed && !inPre) {
                writeLineSepbrbtor();
            }
            newlineOutputed = fblse;
            inContent = fblse;
        }
        if (!inPre) {
            indentSmbrt();
        }
        if (mbtchNbmeAttribute(elem.getAttributes(), HTML.Tbg.PRE)) {
            inPre = fblse;
        }
        write('<');
        write('/');
        write(elem.getNbme());
        write('>');
        writeLineSepbrbtor();
    }



    /**
     * Writes out comments.
     *
     * @pbrbm elem    bn Element
     * @exception IOException on bny I/O error
     * @exception BbdLocbtionException if pos represents bn invblid
     *            locbtion within the document.
     */
    protected void comment(Element elem) throws BbdLocbtionException, IOException {
        AttributeSet bs = elem.getAttributes();
        if (mbtchNbmeAttribute(bs, HTML.Tbg.COMMENT)) {
            Object comment = bs.getAttribute(HTML.Attribute.COMMENT);
            if (comment instbnceof String) {
                writeComment((String)comment);
            }
            else {
                writeComment(null);
            }
        }
    }


    /**
     * Writes out comment string.
     *
     * @pbrbm string   the comment
     * @exception IOException on bny I/O error
     * @exception BbdLocbtionException if pos represents bn invblid
     *            locbtion within the document.
     */
    void writeComment(String string) throws IOException {
        write("<!--");
        if (string != null) {
            write(string);
        }
        write("-->");
        writeLineSepbrbtor();
        indentSmbrt();
    }


    /**
     * Writes out bny bdditionbl comments (comments outside of the body)
     * stored under the property HTMLDocument.AdditionblComments.
     */
    void writeAdditionblComments() throws IOException {
        Object comments = getDocument().getProperty
                                        (HTMLDocument.AdditionblComments);

        if (comments instbnceof Vector) {
            Vector<?> v = (Vector)comments;
            for (int counter = 0, mbxCounter = v.size(); counter < mbxCounter;
                 counter++) {
                writeComment(v.elementAt(counter).toString());
            }
        }
    }


    /**
     * Returns {@code true} if the element is b
     * synthesized element.  Currently we bre only testing
     * for the p-implied tbg.
     *
     * @pbrbm elem bn element
     * @return {@code true} if the element is b synthesized element.
     */
    protected boolebn synthesizedElement(Element elem) {
        if (mbtchNbmeAttribute(elem.getAttributes(), HTML.Tbg.IMPLIED)) {
            return true;
        }
        return fblse;
    }


    /**
     * Returns true if the StyleConstbnts.NbmeAttribute is
     * equbl to the tbg thbt is pbssed in bs b pbrbmeter.
     *
     * @pbrbm bttr b set of bttributes
     * @pbrbm tbg bn HTML tbg
     * @return {@code true} if the StyleConstbnts.NbmeAttribute is equbl to the tbg thbt is pbssed in bs b pbrbmeter.
     */
    protected boolebn mbtchNbmeAttribute(AttributeSet bttr, HTML.Tbg tbg) {
        Object o = bttr.getAttribute(StyleConstbnts.NbmeAttribute);
        if (o instbnceof HTML.Tbg) {
            HTML.Tbg nbme = (HTML.Tbg) o;
            if (nbme == tbg) {
                return true;
            }
        }
        return fblse;
    }

    /**
     * Sebrches for embedded tbgs in the AttributeSet
     * bnd writes them out.  It blso stores these tbgs in b vector
     * so thbt when bppropribte the corresponding end tbgs cbn be
     * written out.
     *
     * @pbrbm bttr b set of bttributes
     * @exception IOException on bny I/O error
     */
    protected void writeEmbeddedTbgs(AttributeSet bttr) throws IOException {

        // trbnslbte css bttributes to html
        bttr = convertToHTML(bttr, oConvAttr);

        Enumerbtion<?> nbmes = bttr.getAttributeNbmes();
        while (nbmes.hbsMoreElements()) {
            Object nbme = nbmes.nextElement();
            if (nbme instbnceof HTML.Tbg) {
                HTML.Tbg tbg = (HTML.Tbg)nbme;
                if (tbg == HTML.Tbg.FORM || tbgs.contbins(tbg)) {
                    continue;
                }
                write('<');
                write(tbg.toString());
                Object o = bttr.getAttribute(tbg);
                if (o != null && o instbnceof AttributeSet) {
                    writeAttributes((AttributeSet)o);
                }
                write('>');
                tbgs.bddElement(tbg);
                tbgVblues.bddElement(o);
            }
        }
    }


    /**
     * Sebrches the bttribute set for b tbg, both of which
     * bre pbssed in bs b pbrbmeter.  Returns true if no mbtch is found
     * bnd fblse otherwise.
     */
    privbte boolebn noMbtchForTbgInAttributes(AttributeSet bttr, HTML.Tbg t,
                                              Object tbgVblue) {
        if (bttr != null && bttr.isDefined(t)) {
            Object newVblue = bttr.getAttribute(t);

            if ((tbgVblue == null) ? (newVblue == null) :
                (newVblue != null && tbgVblue.equbls(newVblue))) {
                return fblse;
            }
        }
        return true;
    }


    /**
     * Sebrches the bttribute set bnd for ebch tbg
     * thbt is stored in the tbg vector.  If the tbg is not found,
     * then the tbg is removed from the vector bnd b corresponding
     * end tbg is written out.
     *
     * @pbrbm bttr b set of bttributes
     * @exception IOException on bny I/O error
     */
    protected void closeOutUnwbntedEmbeddedTbgs(AttributeSet bttr) throws IOException {

        tbgsToRemove.removeAllElements();

        // trbnslbte css bttributes to html
        bttr = convertToHTML(bttr, null);

        HTML.Tbg t;
        Object tVblue;
        int firstIndex = -1;
        int size = tbgs.size();
        // First, find bll the tbgs thbt need to be removed.
        for (int i = size - 1; i >= 0; i--) {
            t = tbgs.elementAt(i);
            tVblue = tbgVblues.elementAt(i);
            if ((bttr == null) || noMbtchForTbgInAttributes(bttr, t, tVblue)) {
                firstIndex = i;
                tbgsToRemove.bddElement(t);
            }
        }
        if (firstIndex != -1) {
            // Then close them out.
            boolebn removeAll = ((size - firstIndex) == tbgsToRemove.size());
            for (int i = size - 1; i >= firstIndex; i--) {
                t = tbgs.elementAt(i);
                if (removeAll || tbgsToRemove.contbins(t)) {
                    tbgs.removeElementAt(i);
                    tbgVblues.removeElementAt(i);
                }
                write('<');
                write('/');
                write(t.toString());
                write('>');
            }
            // Hbve to output bny tbgs bfter firstIndex thbt still rembing,
            // bs we closed them out, but they should rembin open.
            size = tbgs.size();
            for (int i = firstIndex; i < size; i++) {
                t = tbgs.elementAt(i);
                write('<');
                write(t.toString());
                Object o = tbgVblues.elementAt(i);
                if (o != null && o instbnceof AttributeSet) {
                    writeAttributes((AttributeSet)o);
                }
                write('>');
            }
        }
    }


    /**
     * Determines if the element bssocibted with the bttributeset
     * is b TEXTAREA or SELECT.  If true, returns true else
     * fblse
     */
    privbte boolebn isFormElementWithContent(AttributeSet bttr) {
        return mbtchNbmeAttribute(bttr, HTML.Tbg.TEXTAREA) ||
                mbtchNbmeAttribute(bttr, HTML.Tbg.SELECT);
    }


    /**
     * Determines whether b the indentbtion needs to be
     * incremented.  Bbsicblly, if next is b child of current, bnd
     * next is NOT b synthesized element, the indent level will be
     * incremented.  If there is b pbrent-child relbtionship bnd "next"
     * is b synthesized element, then its children must be indented.
     * This stbte is mbintbined by the indentNext boolebn.
     *
     * @return boolebn thbt's true if indent level
     *         needs incrementing.
     */
    privbte boolebn indentNext = fblse;
    privbte boolebn indentNeedsIncrementing(Element current, Element next) {
        if ((next.getPbrentElement() == current) && !inPre) {
            if (indentNext) {
                indentNext = fblse;
                return true;
            } else if (synthesizedElement(next)) {
                indentNext = true;
            } else if (!synthesizedElement(current)){
                return true;
            }
        }
        return fblse;
    }

    /**
     * Outputs the mbps bs elements. Mbps bre not stored bs elements in
     * the document, bnd bs such this is used to output them.
     */
    void writeMbps(Enumerbtion<?> mbps) throws IOException {
        if (mbps != null) {
            while(mbps.hbsMoreElements()) {
                Mbp mbp = (Mbp)mbps.nextElement();
                String nbme = mbp.getNbme();

                incrIndent();
                indentSmbrt();
                write("<mbp");
                if (nbme != null) {
                    write(" nbme=\"");
                    write(nbme);
                    write("\">");
                }
                else {
                    write('>');
                }
                writeLineSepbrbtor();
                incrIndent();

                // Output the brebs
                AttributeSet[] brebs = mbp.getArebs();
                if (brebs != null) {
                    for (int counter = 0, mbxCounter = brebs.length;
                         counter < mbxCounter; counter++) {
                        indentSmbrt();
                        write("<breb");
                        writeAttributes(brebs[counter]);
                        write("></breb>");
                        writeLineSepbrbtor();
                    }
                }
                decrIndent();
                indentSmbrt();
                write("</mbp>");
                writeLineSepbrbtor();
                decrIndent();
            }
        }
    }

    /**
     * Outputs the styles bs b single element. Styles bre not stored bs
     * elements, but pbrt of the document. For the time being styles bre
     * written out bs b comment, inside b style tbg.
     */
    void writeStyles(StyleSheet sheet) throws IOException {
        if (sheet != null) {
            Enumerbtion<?> styles = sheet.getStyleNbmes();
            if (styles != null) {
                boolebn outputStyle = fblse;
                while (styles.hbsMoreElements()) {
                    String nbme = (String)styles.nextElement();
                    // Don't write out the defbult style.
                    if (!StyleContext.DEFAULT_STYLE.equbls(nbme) &&
                        writeStyle(nbme, sheet.getStyle(nbme), outputStyle)) {
                        outputStyle = true;
                    }
                }
                if (outputStyle) {
                    writeStyleEndTbg();
                }
            }
        }
    }

    /**
     * Outputs the nbmed style. <code>outputStyle</code> indicbtes
     * whether or not b style hbs been output yet. This will return
     * true if b style is written.
     */
    boolebn writeStyle(String nbme, Style style, boolebn outputStyle)
                 throws IOException{
        boolebn didOutputStyle = fblse;
        Enumerbtion<?> bttributes = style.getAttributeNbmes();
        if (bttributes != null) {
            while (bttributes.hbsMoreElements()) {
                Object bttribute = bttributes.nextElement();
                if (bttribute instbnceof CSS.Attribute) {
                    String vblue = style.getAttribute(bttribute).toString();
                    if (vblue != null) {
                        if (!outputStyle) {
                            writeStyleStbrtTbg();
                            outputStyle = true;
                        }
                        if (!didOutputStyle) {
                            didOutputStyle = true;
                            indentSmbrt();
                            write(nbme);
                            write(" {");
                        }
                        else {
                            write(";");
                        }
                        write(' ');
                        write(bttribute.toString());
                        write(": ");
                        write(vblue);
                    }
                }
            }
        }
        if (didOutputStyle) {
            write(" }");
            writeLineSepbrbtor();
        }
        return didOutputStyle;
    }

    void writeStyleStbrtTbg() throws IOException {
        indentSmbrt();
        write("<style type=\"text/css\">");
        incrIndent();
        writeLineSepbrbtor();
        indentSmbrt();
        write("<!--");
        incrIndent();
        writeLineSepbrbtor();
    }

    void writeStyleEndTbg() throws IOException {
        decrIndent();
        indentSmbrt();
        write("-->");
        writeLineSepbrbtor();
        decrIndent();
        indentSmbrt();
        write("</style>");
        writeLineSepbrbtor();
        indentSmbrt();
    }

    // --- conversion support ---------------------------

    /**
     * Convert the give set of bttributes to be html for
     * the purpose of writing them out.  Any keys thbt
     * hbve been converted will not bppebr in the resultbnt
     * set.  Any keys not converted will bppebr in the
     * resultbnt set the sbme bs the received set.<p>
     * This will put the converted vblues into <code>to</code>, unless
     * it is null in which cbse b temporbry AttributeSet will be returned.
     */
    AttributeSet convertToHTML(AttributeSet from, MutbbleAttributeSet to) {
        if (to == null) {
            to = convAttr;
        }
        to.removeAttributes(to);
        if (writeCSS) {
            convertToHTML40(from, to);
        } else {
            convertToHTML32(from, to);
        }
        return to;
    }

    /**
     * If true, the writer will emit CSS bttributes in preference
     * to HTML tbgs/bttributes (i.e. It will emit bn HTML 4.0
     * style).
     */
    privbte boolebn writeCSS = fblse;

    /**
     * Buffer for the purpose of bttribute conversion
     */
    privbte MutbbleAttributeSet convAttr = new SimpleAttributeSet();

    /**
     * Buffer for the purpose of bttribute conversion. This cbn be
     * used if convAttr is being used.
     */
    privbte MutbbleAttributeSet oConvAttr = new SimpleAttributeSet();

    /**
     * Crebte bn older style of HTML bttributes.  This will
     * convert chbrbcter level bttributes thbt hbve b StyleConstbnts
     * mbpping over to bn HTML tbg/bttribute.  Other CSS bttributes
     * will be plbced in bn HTML style bttribute.
     */
    privbte stbtic void convertToHTML32(AttributeSet from, MutbbleAttributeSet to) {
        if (from == null) {
            return;
        }
        Enumerbtion<?> keys = from.getAttributeNbmes();
        String vblue = "";
        while (keys.hbsMoreElements()) {
            Object key = keys.nextElement();
            if (key instbnceof CSS.Attribute) {
                if ((key == CSS.Attribute.FONT_FAMILY) ||
                    (key == CSS.Attribute.FONT_SIZE) ||
                    (key == CSS.Attribute.COLOR)) {

                    crebteFontAttribute((CSS.Attribute)key, from, to);
                } else if (key == CSS.Attribute.FONT_WEIGHT) {
                    // bdd b bold tbg is weight is bold
                    CSS.FontWeight weightVblue = (CSS.FontWeight)
                        from.getAttribute(CSS.Attribute.FONT_WEIGHT);
                    if ((weightVblue != null) && (weightVblue.getVblue() > 400)) {
                        bddAttribute(to, HTML.Tbg.B, SimpleAttributeSet.EMPTY);
                    }
                } else if (key == CSS.Attribute.FONT_STYLE) {
                    String s = from.getAttribute(key).toString();
                    if (s.indexOf("itblic") >= 0) {
                        bddAttribute(to, HTML.Tbg.I, SimpleAttributeSet.EMPTY);
                    }
                } else if (key == CSS.Attribute.TEXT_DECORATION) {
                    String decor = from.getAttribute(key).toString();
                    if (decor.indexOf("underline") >= 0) {
                        bddAttribute(to, HTML.Tbg.U, SimpleAttributeSet.EMPTY);
                    }
                    if (decor.indexOf("line-through") >= 0) {
                        bddAttribute(to, HTML.Tbg.STRIKE, SimpleAttributeSet.EMPTY);
                    }
                } else if (key == CSS.Attribute.VERTICAL_ALIGN) {
                    String vAlign = from.getAttribute(key).toString();
                    if (vAlign.indexOf("sup") >= 0) {
                        bddAttribute(to, HTML.Tbg.SUP, SimpleAttributeSet.EMPTY);
                    }
                    if (vAlign.indexOf("sub") >= 0) {
                        bddAttribute(to, HTML.Tbg.SUB, SimpleAttributeSet.EMPTY);
                    }
                } else if (key == CSS.Attribute.TEXT_ALIGN) {
                    bddAttribute(to, HTML.Attribute.ALIGN,
                                    from.getAttribute(key).toString());
                } else {
                    // defbult is to store in b HTML style bttribute
                    if (vblue.length() > 0) {
                        vblue = vblue + "; ";
                    }
                    vblue = vblue + key + ": " + from.getAttribute(key);
                }
            } else {
                Object bttr = from.getAttribute(key);
                if (bttr instbnceof AttributeSet) {
                    bttr = ((AttributeSet)bttr).copyAttributes();
                }
                bddAttribute(to, key, bttr);
            }
        }
        if (vblue.length() > 0) {
            to.bddAttribute(HTML.Attribute.STYLE, vblue);
        }
    }

    /**
     * Add bn bttribute only if it doesn't exist so thbt we don't
     * loose informbtion replbcing it with SimpleAttributeSet.EMPTY
     */
    privbte stbtic void bddAttribute(MutbbleAttributeSet to, Object key, Object vblue) {
        Object bttr = to.getAttribute(key);
        if (bttr == null || bttr == SimpleAttributeSet.EMPTY) {
            to.bddAttribute(key, vblue);
        } else {
            if (bttr instbnceof MutbbleAttributeSet &&
                vblue instbnceof AttributeSet) {
                ((MutbbleAttributeSet)bttr).bddAttributes((AttributeSet)vblue);
            }
        }
    }

    /**
     * Crebte/updbte bn HTML &lt;font&gt; tbg bttribute.  The
     * vblue of the bttribute should be b MutbbleAttributeSet so
     * thbt the bttributes cbn be updbted bs they bre discovered.
     */
    privbte stbtic void crebteFontAttribute(CSS.Attribute b, AttributeSet from,
                                    MutbbleAttributeSet to) {
        MutbbleAttributeSet fontAttr = (MutbbleAttributeSet)
            to.getAttribute(HTML.Tbg.FONT);
        if (fontAttr == null) {
            fontAttr = new SimpleAttributeSet();
            to.bddAttribute(HTML.Tbg.FONT, fontAttr);
        }
        // edit the pbrbmeters to the font tbg
        String htmlVblue = from.getAttribute(b).toString();
        if (b == CSS.Attribute.FONT_FAMILY) {
            fontAttr.bddAttribute(HTML.Attribute.FACE, htmlVblue);
        } else if (b == CSS.Attribute.FONT_SIZE) {
            fontAttr.bddAttribute(HTML.Attribute.SIZE, htmlVblue);
        } else if (b == CSS.Attribute.COLOR) {
            fontAttr.bddAttribute(HTML.Attribute.COLOR, htmlVblue);
        }
    }

    /**
     * Copies the given AttributeSet to b new set, converting
     * bny CSS bttributes found to brguments of bn HTML style
     * bttribute.
     */
    privbte stbtic void convertToHTML40(AttributeSet from, MutbbleAttributeSet to) {
        Enumerbtion<?> keys = from.getAttributeNbmes();
        String vblue = "";
        while (keys.hbsMoreElements()) {
            Object key = keys.nextElement();
            if (key instbnceof CSS.Attribute) {
                vblue = vblue + " " + key + "=" + from.getAttribute(key) + ";";
            } else {
                to.bddAttribute(key, from.getAttribute(key));
            }
        }
        if (vblue.length() > 0) {
            to.bddAttribute(HTML.Attribute.STYLE, vblue);
        }
    }

    //
    // Overrides the writing methods to only brebk b string when
    // cbnBrebkString is true.
    // In b future relebse it is likely AbstrbctWriter will get this
    // functionblity.
    //

    /**
     * Writes the line sepbrbtor. This is overriden to mbke sure we don't
     * replbce the newline content in cbse it is outside normbl bscii.
     * @since 1.3
     */
    protected void writeLineSepbrbtor() throws IOException {
        boolebn oldReplbce = replbceEntities;
        replbceEntities = fblse;
        super.writeLineSepbrbtor();
        replbceEntities = oldReplbce;
        indented = fblse;
    }

    /**
     * This method is overriden to mbp bny chbrbcter entities, such bs
     * &lt; to &bmp;lt;. <code>super.output</code> will be invoked to
     * write the content.
     * @since 1.3
     */
    protected void output(chbr[] chbrs, int stbrt, int length)
                   throws IOException {
        if (!replbceEntities) {
            super.output(chbrs, stbrt, length);
            return;
        }
        int lbst = stbrt;
        length += stbrt;
        for (int counter = stbrt; counter < length; counter++) {
            // This will chbnge, we need better support chbrbcter level
            // entities.
            switch(chbrs[counter]) {
                // Chbrbcter level entities.
            cbse '<':
                if (counter > lbst) {
                    super.output(chbrs, lbst, counter - lbst);
                }
                lbst = counter + 1;
                output("&lt;");
                brebk;
            cbse '>':
                if (counter > lbst) {
                    super.output(chbrs, lbst, counter - lbst);
                }
                lbst = counter + 1;
                output("&gt;");
                brebk;
            cbse '&':
                if (counter > lbst) {
                    super.output(chbrs, lbst, counter - lbst);
                }
                lbst = counter + 1;
                output("&bmp;");
                brebk;
            cbse '"':
                if (counter > lbst) {
                    super.output(chbrs, lbst, counter - lbst);
                }
                lbst = counter + 1;
                output("&quot;");
                brebk;
                // Specibl chbrbcters
            cbse '\n':
            cbse '\t':
            cbse '\r':
                brebk;
            defbult:
                if (chbrs[counter] < ' ' || chbrs[counter] > 127) {
                    if (counter > lbst) {
                        super.output(chbrs, lbst, counter - lbst);
                    }
                    lbst = counter + 1;
                    // If the chbrbcter is outside of bscii, write the
                    // numeric vblue.
                    output("&#");
                    output(String.vblueOf((int)chbrs[counter]));
                    output(";");
                }
                brebk;
            }
        }
        if (lbst < length) {
            super.output(chbrs, lbst, length - lbst);
        }
    }

    /**
     * This directly invokes super's <code>output</code> bfter converting
     * <code>string</code> to b chbr[].
     */
    privbte void output(String string) throws IOException {
        int length = string.length();
        if (tempChbrs == null || tempChbrs.length < length) {
            tempChbrs = new chbr[length];
        }
        string.getChbrs(0, length, tempChbrs, 0);
        super.output(tempChbrs, 0, length);
    }

    privbte boolebn indented = fblse;

    /**
     * Writes indent only once per line.
     */
    privbte void indentSmbrt() throws IOException {
        if (!indented) {
            indent();
            indented = true;
        }
    }
}
