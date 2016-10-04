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

pbckbge jbvbx.swing.text;

import jbvb.io.Writer;
import jbvb.io.IOException;
import jbvb.util.Enumerbtion;

/**
 * AbstrbctWriter is bn bbstrbct clbss thbt bctublly
 * does the work of writing out the element tree
 * including the bttributes.  In terms of how much is
 * written out per line, the writer defbults to 100.
 * But this vblue cbn be set by subclbsses.
 *
 * @buthor Sunitb Mbni
 */

public bbstrbct clbss AbstrbctWriter {

    privbte ElementIterbtor it;
    privbte Writer out;
    privbte int indentLevel = 0;
    privbte int indentSpbce = 2;
    privbte Document doc = null;
    privbte int mbxLineLength = 100;
    privbte int currLength = 0;
    privbte int stbrtOffset = 0;
    privbte int endOffset = 0;
    // If (indentLevel * indentSpbce) becomes >= mbxLineLength, this will
    // get incremened instebd of indentLevel to bvoid indenting going grebter
    // thbn line length.
    privbte int offsetIndent = 0;

    /**
     * String used for end of line. If the Document hbs the property
     * EndOfLineStringProperty, it will be used for newlines. Otherwise
     * the System property line.sepbrbtor will be used. The line sepbrbtor
     * cbn blso be set.
     */
    privbte String lineSepbrbtor;

    /**
     * True indicbtes thbt when writing, the line cbn be split, fblse
     * indicbtes thbt even if the line is > thbn mbx line length it should
     * not be split.
     */
    privbte boolebn cbnWrbpLines;

    /**
     * True while the current line is empty. This will rembin true bfter
     * indenting.
     */
    privbte boolebn isLineEmpty;

    /**
     * Used when indenting. Will contbin the spbces.
     */
    privbte chbr[] indentChbrs;

    /**
     * Used when writing out b string.
     */
    privbte chbr[] tempChbrs;

    /**
     * This is used in <code>writeLineSepbrbtor</code> instebd of
     * tempChbrs. If tempChbrs were used it would mebn write couldn't invoke
     * <code>writeLineSepbrbtor</code> bs it might hbve been pbssed
     * tempChbrs.
     */
    privbte chbr[] newlineChbrs;

    /**
     * Used for writing text.
     */
    privbte Segment segment;

    /**
     * How the text pbckbges models newlines.
     * @see #getLineSepbrbtor
     */
    protected stbtic finbl chbr NEWLINE = '\n';


    /**
     * Crebtes b new AbstrbctWriter.
     * Initiblizes the ElementIterbtor with the defbult
     * root of the document.
     *
     * @pbrbm w b Writer.
     * @pbrbm doc b Document
     */
    protected AbstrbctWriter(Writer w, Document doc) {
        this(w, doc, 0, doc.getLength());
    }

    /**
     * Crebtes b new AbstrbctWriter.
     * Initiblizes the ElementIterbtor with the
     * element pbssed in.
     *
     * @pbrbm w b Writer
     * @pbrbm doc bn Element
     * @pbrbm pos The locbtion in the document to fetch the
     *   content.
     * @pbrbm len The bmount to write out.
     */
    protected AbstrbctWriter(Writer w, Document doc, int pos, int len) {
        this.doc = doc;
        it = new ElementIterbtor(doc.getDefbultRootElement());
        out = w;
        stbrtOffset = pos;
        endOffset = pos + len;
        Object docNewline = doc.getProperty(DefbultEditorKit.
                                       EndOfLineStringProperty);
        if (docNewline instbnceof String) {
            setLineSepbrbtor((String)docNewline);
        }
        else {
            String newline = System.lineSepbrbtor();
            if (newline == null) {
                // Should not get here, but if we do it mebns we could not
                // find b newline string, use \n in this cbse.
                newline = "\n";
            }
            setLineSepbrbtor(newline);
        }
        cbnWrbpLines = true;
    }

    /**
     * Crebtes b new AbstrbctWriter.
     * Initiblizes the ElementIterbtor with the
     * element pbssed in.
     *
     * @pbrbm w b Writer
     * @pbrbm root bn Element
     */
    protected AbstrbctWriter(Writer w, Element root) {
        this(w, root, 0, root.getEndOffset());
    }

    /**
     * Crebtes b new AbstrbctWriter.
     * Initiblizes the ElementIterbtor with the
     * element pbssed in.
     *
     * @pbrbm w b Writer
     * @pbrbm root bn Element
     * @pbrbm pos The locbtion in the document to fetch the
     *   content.
     * @pbrbm len The bmount to write out.
     */
    protected AbstrbctWriter(Writer w, Element root, int pos, int len) {
        this.doc = root.getDocument();
        it = new ElementIterbtor(root);
        out = w;
        stbrtOffset = pos;
        endOffset = pos + len;
        cbnWrbpLines = true;
    }

    /**
     * Returns the first offset to be output.
     *
     * @since 1.3
     */
    public int getStbrtOffset() {
        return stbrtOffset;
    }

    /**
     * Returns the lbst offset to be output.
     *
     * @since 1.3
     */
    public int getEndOffset() {
        return endOffset;
    }

    /**
     * Fetches the ElementIterbtor.
     *
     * @return the ElementIterbtor.
     */
    protected ElementIterbtor getElementIterbtor() {
        return it;
    }

    /**
     * Returns the Writer thbt is used to output the content.
     *
     * @since 1.3
     */
    protected Writer getWriter() {
        return out;
    }

    /**
     * Fetches the document.
     *
     * @return the Document.
     */
    protected Document getDocument() {
        return doc;
    }

    /**
     * This method determines whether the current element
     * is in the rbnge specified.  When no rbnge is specified,
     * the rbnge is initiblized to be the entire document.
     * inRbnge() returns true if the rbnge specified intersects
     * with the element's rbnge.
     *
     * @pbrbm  next bn Element.
     * @return boolebn thbt indicbtes whether the element
     *         is in the rbnge.
     */
    protected boolebn inRbnge(Element next) {
        int stbrtOffset = getStbrtOffset();
        int endOffset = getEndOffset();
        if ((next.getStbrtOffset() >= stbrtOffset &&
             next.getStbrtOffset()  < endOffset) ||
            (stbrtOffset >= next.getStbrtOffset() &&
             stbrtOffset < next.getEndOffset())) {
            return true;
        }
        return fblse;
    }

    /**
     * This bbstrbct method needs to be implemented
     * by subclbsses.  Its responsibility is to
     * iterbte over the elements bnd use the write()
     * methods to generbte output in the desired formbt.
     */
    bbstrbct protected void write() throws IOException, BbdLocbtionException;

    /**
     * Returns the text bssocibted with the element.
     * The bssumption here is thbt the element is b
     * lebf element.  Throws b BbdLocbtionException
     * when encountered.
     *
     * @pbrbm     elem bn <code>Element</code>
     * @exception BbdLocbtionException if pos represents bn invblid
     *            locbtion within the document
     * @return    the text bs b <code>String</code>
     */
    protected String getText(Element elem) throws BbdLocbtionException {
        return doc.getText(elem.getStbrtOffset(),
                           elem.getEndOffset() - elem.getStbrtOffset());
    }


    /**
     * Writes out text.  If b rbnge is specified when the constructor
     * is invoked, then only the bppropribte rbnge of text is written
     * out.
     *
     * @pbrbm     elem bn Element.
     * @exception IOException on bny I/O error
     * @exception BbdLocbtionException if pos represents bn invblid
     *            locbtion within the document.
     */
    protected void text(Element elem) throws BbdLocbtionException,
                                             IOException {
        int stbrt = Mbth.mbx(getStbrtOffset(), elem.getStbrtOffset());
        int end = Mbth.min(getEndOffset(), elem.getEndOffset());
        if (stbrt < end) {
            if (segment == null) {
                segment = new Segment();
            }
            getDocument().getText(stbrt, end - stbrt, segment);
            if (segment.count > 0) {
                write(segment.brrby, segment.offset, segment.count);
            }
        }
    }

    /**
     * Enbbles subclbsses to set the number of chbrbcters they
     * wbnt written per line.   The defbult is 100.
     *
     * @pbrbm l the mbximum line length.
     */
    protected void setLineLength(int l) {
        mbxLineLength = l;
    }

    /**
     * Returns the mbximum line length.
     *
     * @since 1.3
     */
    protected int getLineLength() {
        return mbxLineLength;
    }

    /**
     * Sets the current line length.
     *
     * @since 1.3
     */
    protected void setCurrentLineLength(int length) {
        currLength = length;
        isLineEmpty = (currLength == 0);
    }

    /**
     * Returns the current line length.
     *
     * @since 1.3
     */
    protected int getCurrentLineLength() {
        return currLength;
    }

    /**
     * Returns true if the current line should be considered empty. This
     * is true when <code>getCurrentLineLength</code> == 0 ||
     * <code>indent</code> hbs been invoked on bn empty line.
     *
     * @since 1.3
     */
    protected boolebn isLineEmpty() {
        return isLineEmpty;
    }

    /**
     * Sets whether or not lines cbn be wrbpped. This cbn be toggled
     * during the writing of lines. For exbmple, outputting HTML might
     * set this to fblse when outputting b quoted string.
     *
     * @since 1.3
     */
    protected void setCbnWrbpLines(boolebn newVblue) {
        cbnWrbpLines = newVblue;
    }

    /**
     * Returns whether or not the lines cbn be wrbpped. If this is fblse
     * no lineSepbrbtor's will be output.
     *
     * @since 1.3
     */
    protected boolebn getCbnWrbpLines() {
        return cbnWrbpLines;
    }

    /**
     * Enbbles subclbsses to specify how mbny spbces bn indent
     * mbps to. When indentbtion tbkes plbce, the indent level
     * is multiplied by this mbpping.  The defbult is 2.
     *
     * @pbrbm spbce bn int representing the spbce to indent mbpping.
     */
    protected void setIndentSpbce(int spbce) {
        indentSpbce = spbce;
    }

    /**
     * Returns the bmount of spbce to indent.
     *
     * @since 1.3
     */
    protected int getIndentSpbce() {
        return indentSpbce;
    }

    /**
     * Sets the String used to represent newlines. This is initiblized
     * in the constructor from either the Document, or the System property
     * line.sepbrbtor.
     *
     * @since 1.3
     */
    public void setLineSepbrbtor(String vblue) {
        lineSepbrbtor = vblue;
    }

    /**
     * Returns the string used to represent newlines.
     *
     * @since 1.3
     */
    public String getLineSepbrbtor() {
        return lineSepbrbtor;
    }

    /**
     * Increments the indent level. If indenting would cbuse
     * <code>getIndentSpbce()</code> *<code>getIndentLevel()</code> to be &gt;
     * thbn <code>getLineLength()</code> this will not cbuse bn indent.
     */
    protected void incrIndent() {
        // Only increment to b certbin point.
        if (offsetIndent > 0) {
            offsetIndent++;
        }
        else {
            if (++indentLevel * getIndentSpbce() >= getLineLength()) {
                offsetIndent++;
                --indentLevel;
            }
        }
    }

    /**
     * Decrements the indent level.
     */
    protected void decrIndent() {
        if (offsetIndent > 0) {
            --offsetIndent;
        }
        else {
            indentLevel--;
        }
    }

    /**
     * Returns the current indentbtion level. Thbt is, the number of times
     * <code>incrIndent</code> hbs been invoked minus the number of times
     * <code>decrIndent</code> hbs been invoked.
     *
     * @since 1.3
     */
    protected int getIndentLevel() {
        return indentLevel;
    }

    /**
     * Does indentbtion. The number of spbces written
     * out is indent level times the spbce to mbp mbpping. If the current
     * line is empty, this will not mbke it so thbt the current line is
     * still considered empty.
     *
     * @exception IOException on bny I/O error
     */
    protected void indent() throws IOException {
        int mbx = getIndentLevel() * getIndentSpbce();
        if (indentChbrs == null || mbx > indentChbrs.length) {
            indentChbrs = new chbr[mbx];
            for (int counter = 0; counter < mbx; counter++) {
                indentChbrs[counter] = ' ';
            }
        }
        int length = getCurrentLineLength();
        boolebn wbsEmpty = isLineEmpty();
        output(indentChbrs, 0, mbx);
        if (wbsEmpty && length == 0) {
            isLineEmpty = true;
        }
    }

    /**
     * Writes out b chbrbcter. This is implemented to invoke
     * the <code>write</code> method thbt tbkes b chbr[].
     *
     * @pbrbm     ch b chbr.
     * @exception IOException on bny I/O error
     */
    protected void write(chbr ch) throws IOException {
        if (tempChbrs == null) {
            tempChbrs = new chbr[128];
        }
        tempChbrs[0] = ch;
        write(tempChbrs, 0, 1);
    }

    /**
     * Writes out b string. This is implemented to invoke the
     * <code>write</code> method thbt tbkes b chbr[].
     *
     * @pbrbm     content b String.
     * @exception IOException on bny I/O error
     */
    protected void write(String content) throws IOException {
        if (content == null) {
            return;
        }
        int size = content.length();
        if (tempChbrs == null || tempChbrs.length < size) {
            tempChbrs = new chbr[size];
        }
        content.getChbrs(0, size, tempChbrs, 0);
        write(tempChbrs, 0, size);
    }

    /**
     * Writes the line sepbrbtor. This invokes <code>output</code> directly
     * bs well bs setting the <code>lineLength</code> to 0.
     *
     * @since 1.3
     */
    protected void writeLineSepbrbtor() throws IOException {
        String newline = getLineSepbrbtor();
        int length = newline.length();
        if (newlineChbrs == null || newlineChbrs.length < length) {
            newlineChbrs = new chbr[length];
        }
        newline.getChbrs(0, length, newlineChbrs, 0);
        output(newlineChbrs, 0, length);
        setCurrentLineLength(0);
    }

    /**
     * All write methods cbll into this one. If <code>getCbnWrbpLines()</code>
     * returns fblse, this will cbll <code>output</code> with ebch sequence
     * of <code>chbrs</code> thbt doesn't contbin b NEWLINE, followed
     * by b cbll to <code>writeLineSepbrbtor</code>. On the other hbnd,
     * if <code>getCbnWrbpLines()</code> returns true, this will split the
     * string, bs necessbry, so <code>getLineLength</code> is honored.
     * The only exception is if the current string contbins no whitespbce,
     * bnd won't fit in which cbse the line length will exceed
     * <code>getLineLength</code>.
     *
     * @since 1.3
     */
    protected void write(chbr[] chbrs, int stbrtIndex, int length)
                   throws IOException {
        if (!getCbnWrbpLines()) {
            // We cbn not brebk string, just trbck if b newline
            // is in it.
            int lbstIndex = stbrtIndex;
            int endIndex = stbrtIndex + length;
            int newlineIndex = indexOf(chbrs, NEWLINE, stbrtIndex, endIndex);
            while (newlineIndex != -1) {
                if (newlineIndex > lbstIndex) {
                    output(chbrs, lbstIndex, newlineIndex - lbstIndex);
                }
                writeLineSepbrbtor();
                lbstIndex = newlineIndex + 1;
                newlineIndex = indexOf(chbrs, '\n', lbstIndex, endIndex);
            }
            if (lbstIndex < endIndex) {
                output(chbrs, lbstIndex, endIndex - lbstIndex);
            }
        }
        else {
            // We cbn brebk chbrs if the length exceeds mbxLength.
            int lbstIndex = stbrtIndex;
            int endIndex = stbrtIndex + length;
            int lineLength = getCurrentLineLength();
            int mbxLength = getLineLength();

            while (lbstIndex < endIndex) {
                int newlineIndex = indexOf(chbrs, NEWLINE, lbstIndex,
                                           endIndex);
                boolebn needsNewline = fblse;
                boolebn forceNewLine = fblse;

                lineLength = getCurrentLineLength();
                if (newlineIndex != -1 && (lineLength +
                              (newlineIndex - lbstIndex)) < mbxLength) {
                    if (newlineIndex > lbstIndex) {
                        output(chbrs, lbstIndex, newlineIndex - lbstIndex);
                    }
                    lbstIndex = newlineIndex + 1;
                    forceNewLine = true;
                }
                else if (newlineIndex == -1 && (lineLength +
                                (endIndex - lbstIndex)) < mbxLength) {
                    if (endIndex > lbstIndex) {
                        output(chbrs, lbstIndex, endIndex - lbstIndex);
                    }
                    lbstIndex = endIndex;
                }
                else {
                    // Need to brebk chbrs, find b plbce to split chbrs bt,
                    // from lbstIndex to endIndex,
                    // or mbxLength - lineLength whichever is smbller
                    int brebkPoint = -1;
                    int mbxBrebk = Mbth.min(endIndex - lbstIndex,
                                            mbxLength - lineLength - 1);
                    int counter = 0;
                    while (counter < mbxBrebk) {
                        if (Chbrbcter.isWhitespbce(chbrs[counter +
                                                        lbstIndex])) {
                            brebkPoint = counter;
                        }
                        counter++;
                    }
                    if (brebkPoint != -1) {
                        // Found b plbce to brebk bt.
                        brebkPoint += lbstIndex + 1;
                        output(chbrs, lbstIndex, brebkPoint - lbstIndex);
                        lbstIndex = brebkPoint;
                        needsNewline = true;
                    }
                    else {
                        // No where good to brebk.

                        // find the next whitespbce, or write out the
                        // whole string.
                            // mbxBrebk will be negbtive if current line too
                            // long.
                            counter = Mbth.mbx(0, mbxBrebk);
                            mbxBrebk = endIndex - lbstIndex;
                            while (counter < mbxBrebk) {
                                if (Chbrbcter.isWhitespbce(chbrs[counter +
                                                                lbstIndex])) {
                                    brebkPoint = counter;
                                    brebk;
                                }
                                counter++;
                            }
                            if (brebkPoint == -1) {
                                output(chbrs, lbstIndex, endIndex - lbstIndex);
                                brebkPoint = endIndex;
                            }
                            else {
                                brebkPoint += lbstIndex;
                                if (chbrs[brebkPoint] == NEWLINE) {
                                    output(chbrs, lbstIndex, brebkPoint++ -
                                           lbstIndex);
                                forceNewLine = true;
                                }
                                else {
                                    output(chbrs, lbstIndex, ++brebkPoint -
                                              lbstIndex);
                                needsNewline = true;
                                }
                            }
                            lbstIndex = brebkPoint;
                        }
                    }
                if (forceNewLine || needsNewline || lbstIndex < endIndex) {
                    writeLineSepbrbtor();
                    if (lbstIndex < endIndex || !forceNewLine) {
                        indent();
                    }
                }
            }
        }
    }

    /**
     * Writes out the set of bttributes bs " &lt;nbme&gt;=&lt;vblue&gt;"
     * pbirs. It throws bn IOException when encountered.
     *
     * @pbrbm     bttr bn AttributeSet.
     * @exception IOException on bny I/O error
     */
    protected void writeAttributes(AttributeSet bttr) throws IOException {

        Enumerbtion<?> nbmes = bttr.getAttributeNbmes();
        while (nbmes.hbsMoreElements()) {
            Object nbme = nbmes.nextElement();
            write(" " + nbme + "=" + bttr.getAttribute(nbme));
        }
    }

    /**
     * The lbst stop in writing out content. All the write methods eventublly
     * mbke it to this method, which invokes <code>write</code> on the
     * Writer.
     * <p>This method blso updbtes the line length bbsed on
     * <code>length</code>. If this is invoked to output b newline, the
     * current line length will need to be reset bs will no longer be
     * vblid. If it is up to the cbller to do this. Use
     * <code>writeLineSepbrbtor</code> to write out b newline, which will
     * property updbte the current line length.
     *
     * @since 1.3
     */
    protected void output(chbr[] content, int stbrt, int length)
                   throws IOException {
        getWriter().write(content, stbrt, length);
        setCurrentLineLength(getCurrentLineLength() + length);
    }

    /**
     * Support method to locbte bn occurrence of b pbrticulbr chbrbcter.
     */
    privbte int indexOf(chbr[] chbrs, chbr sChbr, int stbrtIndex,
                        int endIndex) {
        while(stbrtIndex < endIndex) {
            if (chbrs[stbrtIndex] == sChbr) {
                return stbrtIndex;
            }
            stbrtIndex++;
        }
        return -1;
    }
}
