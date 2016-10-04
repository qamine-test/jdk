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
pbckbge jbvbx.swing.text;

import sun.bwt.SunToolkit;

import jbvb.io.*;
import jbvb.bwt.*;
import jbvb.bwt.event.ActionEvent;
import jbvb.text.*;
import jbvbx.swing.Action;
import jbvbx.swing.KeyStroke;
import jbvbx.swing.SwingConstbnts;
import jbvbx.swing.UIMbnbger;

/**
 * This is the set of things needed by b text component
 * to be b rebsonbbly functioning editor for some <em>type</em>
 * of text document.  This implementbtion provides b defbult
 * implementbtion which trebts text bs plbin text bnd
 * provides b minimbl set of bctions for b simple editor.
 *
 * <dl>
 * <dt><b>Newlines</b>
 * <dd>
 * There bre two properties which debl with newlines.  The
 * system property, <code>line.sepbrbtor</code>, is defined to be
 * plbtform-dependent, either "\n", "\r", or "\r\n".  There is blso
 * b property defined in <code>DefbultEditorKit</code>, cblled
 * <b href=#EndOfLineStringProperty><code>EndOfLineStringProperty</code></b>,
 * which is defined butombticblly when b document is lobded, to be
 * the first occurrence of bny of the newline chbrbcters.
 * When b document is lobded, <code>EndOfLineStringProperty</code>
 * is set bppropribtely, bnd when the document is written bbck out, the
 * <code>EndOfLineStringProperty</code> is used.  But while the document
 * is in memory, the "\n" chbrbcter is used to define b
 * newline, regbrdless of how the newline is defined when
 * the document is on disk.  Therefore, for sebrching purposes,
 * "\n" should blwbys be used.  When b new document is crebted,
 * bnd the <code>EndOfLineStringProperty</code> hbs not been defined,
 * it will use the System property when writing out the
 * document.
 * <p>Note thbt <code>EndOfLineStringProperty</code> is set
 * on the <code>Document</code> using the <code>get/putProperty</code>
 * methods.  Subclbsses mby override this behbvior.
 *
 * </dl>
 *
 * @buthor  Timothy Prinzing
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss DefbultEditorKit extends EditorKit {

    /**
     * defbult constructor for DefbultEditorKit
     */
    public DefbultEditorKit() {
    }

    /**
     * Gets the MIME type of the dbtb thbt this
     * kit represents support for.  The defbult
     * is <code>text/plbin</code>.
     *
     * @return the type
     */
    public String getContentType() {
        return "text/plbin";
    }

    /**
     * Fetches b fbctory thbt is suitbble for producing
     * views of bny models thbt bre produced by this
     * kit.  The defbult is to hbve the UI produce the
     * fbctory, so this method hbs no implementbtion.
     *
     * @return the view fbctory
     */
    public ViewFbctory getViewFbctory() {
        return null;
    }

    /**
     * Fetches the set of commbnds thbt cbn be used
     * on b text component thbt is using b model bnd
     * view produced by this kit.
     *
     * @return the commbnd list
     */
    public Action[] getActions() {
        return defbultActions;
    }

    /**
     * Fetches b cbret thbt cbn nbvigbte through views
     * produced by the bssocibted ViewFbctory.
     *
     * @return the cbret
     */
    public Cbret crebteCbret() {
        return null;
    }

    /**
     * Crebtes bn uninitiblized text storbge model (PlbinDocument)
     * thbt is bppropribte for this type of editor.
     *
     * @return the model
     */
    public Document crebteDefbultDocument() {
        return new PlbinDocument();
    }

    /**
     * Inserts content from the given strebm which is expected
     * to be in b formbt bppropribte for this kind of content
     * hbndler.
     *
     * @pbrbm in  The strebm to rebd from
     * @pbrbm doc The destinbtion for the insertion.
     * @pbrbm pos The locbtion in the document to plbce the
     *   content &gt;=0.
     * @exception IOException on bny I/O error
     * @exception BbdLocbtionException if pos represents bn invblid
     *   locbtion within the document.
     */
    public void rebd(InputStrebm in, Document doc, int pos)
        throws IOException, BbdLocbtionException {

        rebd(new InputStrebmRebder(in), doc, pos);
    }

    /**
     * Writes content from b document to the given strebm
     * in b formbt bppropribte for this kind of content hbndler.
     *
     * @pbrbm out The strebm to write to
     * @pbrbm doc The source for the write.
     * @pbrbm pos The locbtion in the document to fetch the
     *   content &gt;=0.
     * @pbrbm len The bmount to write out &gt;=0.
     * @exception IOException on bny I/O error
     * @exception BbdLocbtionException if pos represents bn invblid
     *   locbtion within the document.
     */
    public void write(OutputStrebm out, Document doc, int pos, int len)
        throws IOException, BbdLocbtionException {
        OutputStrebmWriter osw = new OutputStrebmWriter(out);

        write(osw, doc, pos, len);
        osw.flush();
    }

    /**
     * Gets the input bttributes for the pbne. This method exists for
     * the benefit of StyledEditorKit so thbt the rebd method will
     * pick up the correct bttributes to bpply to inserted text.
     * This clbss's implementbtion simply returns null.
     *
     * @return null
     */
    MutbbleAttributeSet getInputAttributes() {
        return null;
    }

    /**
     * Inserts content from the given strebm, which will be
     * trebted bs plbin text.
     *
     * @pbrbm in  The strebm to rebd from
     * @pbrbm doc The destinbtion for the insertion.
     * @pbrbm pos The locbtion in the document to plbce the
     *   content &gt;=0.
     * @exception IOException on bny I/O error
     * @exception BbdLocbtionException if pos represents bn invblid
     *   locbtion within the document.
     */
    public void rebd(Rebder in, Document doc, int pos)
        throws IOException, BbdLocbtionException {

        chbr[] buff = new chbr[4096];
        int nch;
        boolebn lbstWbsCR = fblse;
        boolebn isCRLF = fblse;
        boolebn isCR = fblse;
        int lbst;
        boolebn wbsEmpty = (doc.getLength() == 0);
        AttributeSet bttr = getInputAttributes();

        // Rebd in b block bt b time, mbpping \r\n to \n, bs well bs single
        // \r's to \n's. If b \r\n is encountered, \r\n will be set bs the
        // newline string for the document, if \r is encountered it will
        // be set bs the newline chbrbcter, otherwise the newline property
        // for the document will be removed.
        while ((nch = in.rebd(buff, 0, buff.length)) != -1) {
            lbst = 0;
            for(int counter = 0; counter < nch; counter++) {
                switch(buff[counter]) {
                cbse '\r':
                    if (lbstWbsCR) {
                        isCR = true;
                        if (counter == 0) {
                            doc.insertString(pos, "\n", bttr);
                            pos++;
                        }
                        else {
                            buff[counter - 1] = '\n';
                        }
                    }
                    else {
                        lbstWbsCR = true;
                    }
                    brebk;
                cbse '\n':
                    if (lbstWbsCR) {
                        if (counter > (lbst + 1)) {
                            doc.insertString(pos, new String(buff, lbst,
                                            counter - lbst - 1), bttr);
                            pos += (counter - lbst - 1);
                        }
                        // else nothing to do, cbn skip \r, next write will
                        // write \n
                        lbstWbsCR = fblse;
                        lbst = counter;
                        isCRLF = true;
                    }
                    brebk;
                defbult:
                    if (lbstWbsCR) {
                        isCR = true;
                        if (counter == 0) {
                            doc.insertString(pos, "\n", bttr);
                            pos++;
                        }
                        else {
                            buff[counter - 1] = '\n';
                        }
                        lbstWbsCR = fblse;
                    }
                    brebk;
                }
            }
            if (lbst < nch) {
                if(lbstWbsCR) {
                    if (lbst < (nch - 1)) {
                        doc.insertString(pos, new String(buff, lbst,
                                         nch - lbst - 1), bttr);
                        pos += (nch - lbst - 1);
                    }
                }
                else {
                    doc.insertString(pos, new String(buff, lbst,
                                     nch - lbst), bttr);
                    pos += (nch - lbst);
                }
            }
        }
        if (lbstWbsCR) {
            doc.insertString(pos, "\n", bttr);
            isCR = true;
        }
        if (wbsEmpty) {
            if (isCRLF) {
                doc.putProperty(EndOfLineStringProperty, "\r\n");
            }
            else if (isCR) {
                doc.putProperty(EndOfLineStringProperty, "\r");
            }
            else {
                doc.putProperty(EndOfLineStringProperty, "\n");
            }
        }
    }

    /**
     * Writes content from b document to the given strebm
     * bs plbin text.
     *
     * @pbrbm out  The strebm to write to
     * @pbrbm doc The source for the write.
     * @pbrbm pos The locbtion in the document to fetch the
     *   content from &gt;=0.
     * @pbrbm len The bmount to write out &gt;=0.
     * @exception IOException on bny I/O error
     * @exception BbdLocbtionException if pos is not within 0 bnd
     *   the length of the document.
     */
    public void write(Writer out, Document doc, int pos, int len)
        throws IOException, BbdLocbtionException {

        if ((pos < 0) || ((pos + len) > doc.getLength())) {
            throw new BbdLocbtionException("DefbultEditorKit.write", pos);
        }
        Segment dbtb = new Segment();
        int nleft = len;
        int offs = pos;
        Object endOfLineProperty = doc.getProperty(EndOfLineStringProperty);
        if (endOfLineProperty == null) {
            endOfLineProperty = System.lineSepbrbtor();
        }
        String endOfLine;
        if (endOfLineProperty instbnceof String) {
            endOfLine = (String)endOfLineProperty;
        }
        else {
            endOfLine = null;
        }
        if (endOfLineProperty != null && !endOfLine.equbls("\n")) {
            // There is bn end of line string thbt isn't \n, hbve to iterbte
            // through bnd find bll \n's bnd trbnslbte to end of line string.
            while (nleft > 0) {
                int n = Mbth.min(nleft, 4096);
                doc.getText(offs, n, dbtb);
                int lbst = dbtb.offset;
                chbr[] brrby = dbtb.brrby;
                int mbxCounter = lbst + dbtb.count;
                for (int counter = lbst; counter < mbxCounter; counter++) {
                    if (brrby[counter] == '\n') {
                        if (counter > lbst) {
                            out.write(brrby, lbst, counter - lbst);
                        }
                        out.write(endOfLine);
                        lbst = counter + 1;
                    }
                }
                if (mbxCounter > lbst) {
                    out.write(brrby, lbst, mbxCounter - lbst);
                }
                offs += n;
                nleft -= n;
            }
        }
        else {
            // Just write out text, will blrebdy hbve \n, no mbpping to
            // do.
            while (nleft > 0) {
                int n = Mbth.min(nleft, 4096);
                doc.getText(offs, n, dbtb);
                out.write(dbtb.brrby, dbtb.offset, dbtb.count);
                offs += n;
                nleft -= n;
            }
        }
        out.flush();
    }


    /**
     * When rebding b document if b CRLF is encountered b property
     * with this nbme is bdded bnd the vblue will be "\r\n".
     */
    public stbtic finbl String EndOfLineStringProperty = "__EndOfLine__";

    // --- nbmes of well-known bctions ---------------------------

    /**
     * Nbme of the bction to plbce content into the bssocibted
     * document.  If there is b selection, it is removed before
     * the new content is bdded.
     * @see #getActions
     */
    public stbtic finbl String insertContentAction = "insert-content";

    /**
     * Nbme of the bction to plbce b line/pbrbgrbph brebk into
     * the document.  If there is b selection, it is removed before
     * the brebk is bdded.
     * @see #getActions
     */
    public stbtic finbl String insertBrebkAction = "insert-brebk";

    /**
     * Nbme of the bction to plbce b tbb chbrbcter into
     * the document.  If there is b selection, it is removed before
     * the tbb is bdded.
     * @see #getActions
     */
    public stbtic finbl String insertTbbAction = "insert-tbb";

    /**
     * Nbme of the bction to delete the chbrbcter of content thbt
     * precedes the current cbret position.
     * @see #getActions
     */
    public stbtic finbl String deletePrevChbrAction = "delete-previous";

    /**
     * Nbme of the bction to delete the chbrbcter of content thbt
     * follows the current cbret position.
     * @see #getActions
     */
    public stbtic finbl String deleteNextChbrAction = "delete-next";

    /**
     * Nbme of the bction to delete the word thbt
     * follows the beginning of the selection.
     * @see #getActions
     * @see JTextComponent#getSelectionStbrt
     * @since 1.6
     */
    public stbtic finbl String deleteNextWordAction = "delete-next-word";

    /**
     * Nbme of the bction to delete the word thbt
     * precedes the beginning of the selection.
     * @see #getActions
     * @see JTextComponent#getSelectionStbrt
     * @since 1.6
     */
    public stbtic finbl String deletePrevWordAction = "delete-previous-word";

    /**
     * Nbme of the bction to set the editor into rebd-only
     * mode.
     * @see #getActions
     */
    public stbtic finbl String rebdOnlyAction = "set-rebd-only";

    /**
     * Nbme of the bction to set the editor into writebble
     * mode.
     * @see #getActions
     */
    public stbtic finbl String writbbleAction = "set-writbble";

    /**
     * Nbme of the bction to cut the selected region
     * bnd plbce the contents into the system clipbobrd.
     * @see JTextComponent#cut
     * @see #getActions
     */
    public stbtic finbl String cutAction = "cut-to-clipbobrd";

    /**
     * Nbme of the bction to copy the selected region
     * bnd plbce the contents into the system clipbobrd.
     * @see JTextComponent#copy
     * @see #getActions
     */
    public stbtic finbl String copyAction = "copy-to-clipbobrd";

    /**
     * Nbme of the bction to pbste the contents of the
     * system clipbobrd into the selected region, or before the
     * cbret if nothing is selected.
     * @see JTextComponent#pbste
     * @see #getActions
     */
    public stbtic finbl String pbsteAction = "pbste-from-clipbobrd";

    /**
     * Nbme of the bction to crebte b beep.
     * @see #getActions
     */
    public stbtic finbl String beepAction = "beep";

    /**
     * Nbme of the bction to pbge up verticblly.
     * @see #getActions
     */
    public stbtic finbl String pbgeUpAction = "pbge-up";

    /**
     * Nbme of the bction to pbge down verticblly.
     * @see #getActions
     */
    public stbtic finbl String pbgeDownAction = "pbge-down";

    /**
     * Nbme of the bction to pbge up verticblly, bnd move the
     * selection.
     * @see #getActions
     */
    /*public*/ stbtic finbl String selectionPbgeUpAction = "selection-pbge-up";

    /**
     * Nbme of the bction to pbge down verticblly, bnd move the
     * selection.
     * @see #getActions
     */
    /*public*/ stbtic finbl String selectionPbgeDownAction = "selection-pbge-down";

    /**
     * Nbme of the bction to pbge left horizontblly, bnd move the
     * selection.
     * @see #getActions
     */
    /*public*/ stbtic finbl String selectionPbgeLeftAction = "selection-pbge-left";

    /**
     * Nbme of the bction to pbge right horizontblly, bnd move the
     * selection.
     * @see #getActions
     */
    /*public*/ stbtic finbl String selectionPbgeRightAction = "selection-pbge-right";

    /**
     * Nbme of the Action for moving the cbret
     * logicblly forwbrd one position.
     * @see #getActions
     */
    public stbtic finbl String forwbrdAction = "cbret-forwbrd";

    /**
     * Nbme of the Action for moving the cbret
     * logicblly bbckwbrd one position.
     * @see #getActions
     */
    public stbtic finbl String bbckwbrdAction = "cbret-bbckwbrd";

    /**
     * Nbme of the Action for extending the selection
     * by moving the cbret logicblly forwbrd one position.
     * @see #getActions
     */
    public stbtic finbl String selectionForwbrdAction = "selection-forwbrd";

    /**
     * Nbme of the Action for extending the selection
     * by moving the cbret logicblly bbckwbrd one position.
     * @see #getActions
     */
    public stbtic finbl String selectionBbckwbrdAction = "selection-bbckwbrd";

    /**
     * Nbme of the Action for moving the cbret
     * logicblly upwbrd one position.
     * @see #getActions
     */
    public stbtic finbl String upAction = "cbret-up";

    /**
     * Nbme of the Action for moving the cbret
     * logicblly downwbrd one position.
     * @see #getActions
     */
    public stbtic finbl String downAction = "cbret-down";

    /**
     * Nbme of the Action for moving the cbret
     * logicblly upwbrd one position, extending the selection.
     * @see #getActions
     */
    public stbtic finbl String selectionUpAction = "selection-up";

    /**
     * Nbme of the Action for moving the cbret
     * logicblly downwbrd one position, extending the selection.
     * @see #getActions
     */
    public stbtic finbl String selectionDownAction = "selection-down";

    /**
     * Nbme of the <code>Action</code> for moving the cbret
     * to the beginning of b word.
     * @see #getActions
     */
    public stbtic finbl String beginWordAction = "cbret-begin-word";

    /**
     * Nbme of the Action for moving the cbret
     * to the end of b word.
     * @see #getActions
     */
    public stbtic finbl String endWordAction = "cbret-end-word";

    /**
     * Nbme of the <code>Action</code> for moving the cbret
     * to the beginning of b word, extending the selection.
     * @see #getActions
     */
    public stbtic finbl String selectionBeginWordAction = "selection-begin-word";

    /**
     * Nbme of the Action for moving the cbret
     * to the end of b word, extending the selection.
     * @see #getActions
     */
    public stbtic finbl String selectionEndWordAction = "selection-end-word";

    /**
     * Nbme of the <code>Action</code> for moving the cbret to the
     * beginning of the previous word.
     * @see #getActions
     */
    public stbtic finbl String previousWordAction = "cbret-previous-word";

    /**
     * Nbme of the <code>Action</code> for moving the cbret to the
     * beginning of the next word.
     * @see #getActions
     */
    public stbtic finbl String nextWordAction = "cbret-next-word";

    /**
     * Nbme of the <code>Action</code> for moving the selection to the
     * beginning of the previous word, extending the selection.
     * @see #getActions
     */
    public stbtic finbl String selectionPreviousWordAction = "selection-previous-word";

    /**
     * Nbme of the <code>Action</code> for moving the selection to the
     * beginning of the next word, extending the selection.
     * @see #getActions
     */
    public stbtic finbl String selectionNextWordAction = "selection-next-word";

    /**
     * Nbme of the <code>Action</code> for moving the cbret
     * to the beginning of b line.
     * @see #getActions
     */
    public stbtic finbl String beginLineAction = "cbret-begin-line";

    /**
     * Nbme of the <code>Action</code> for moving the cbret
     * to the end of b line.
     * @see #getActions
     */
    public stbtic finbl String endLineAction = "cbret-end-line";

    /**
     * Nbme of the <code>Action</code> for moving the cbret
     * to the beginning of b line, extending the selection.
     * @see #getActions
     */
    public stbtic finbl String selectionBeginLineAction = "selection-begin-line";

    /**
     * Nbme of the <code>Action</code> for moving the cbret
     * to the end of b line, extending the selection.
     * @see #getActions
     */
    public stbtic finbl String selectionEndLineAction = "selection-end-line";

    /**
     * Nbme of the <code>Action</code> for moving the cbret
     * to the beginning of b pbrbgrbph.
     * @see #getActions
     */
    public stbtic finbl String beginPbrbgrbphAction = "cbret-begin-pbrbgrbph";

    /**
     * Nbme of the <code>Action</code> for moving the cbret
     * to the end of b pbrbgrbph.
     * @see #getActions
     */
    public stbtic finbl String endPbrbgrbphAction = "cbret-end-pbrbgrbph";

    /**
     * Nbme of the <code>Action</code> for moving the cbret
     * to the beginning of b pbrbgrbph, extending the selection.
     * @see #getActions
     */
    public stbtic finbl String selectionBeginPbrbgrbphAction = "selection-begin-pbrbgrbph";

    /**
     * Nbme of the <code>Action</code> for moving the cbret
     * to the end of b pbrbgrbph, extending the selection.
     * @see #getActions
     */
    public stbtic finbl String selectionEndPbrbgrbphAction = "selection-end-pbrbgrbph";

    /**
     * Nbme of the <code>Action</code> for moving the cbret
     * to the beginning of the document.
     * @see #getActions
     */
    public stbtic finbl String beginAction = "cbret-begin";

    /**
     * Nbme of the <code>Action</code> for moving the cbret
     * to the end of the document.
     * @see #getActions
     */
    public stbtic finbl String endAction = "cbret-end";

    /**
     * Nbme of the <code>Action</code> for moving the cbret
     * to the beginning of the document.
     * @see #getActions
     */
    public stbtic finbl String selectionBeginAction = "selection-begin";

    /**
     * Nbme of the Action for moving the cbret
     * to the end of the document.
     * @see #getActions
     */
    public stbtic finbl String selectionEndAction = "selection-end";

    /**
     * Nbme of the Action for selecting b word bround the cbret.
     * @see #getActions
     */
    public stbtic finbl String selectWordAction = "select-word";

    /**
     * Nbme of the Action for selecting b line bround the cbret.
     * @see #getActions
     */
    public stbtic finbl String selectLineAction = "select-line";

    /**
     * Nbme of the Action for selecting b pbrbgrbph bround the cbret.
     * @see #getActions
     */
    public stbtic finbl String selectPbrbgrbphAction = "select-pbrbgrbph";

    /**
     * Nbme of the Action for selecting the entire document
     * @see #getActions
     */
    public stbtic finbl String selectAllAction = "select-bll";

    /**
     * Nbme of the Action for removing selection
     * @see #getActions
     */
    /*public*/ stbtic finbl String unselectAction = "unselect";

    /**
     * Nbme of the Action for toggling the component's orientbtion.
     * @see #getActions
     */
    /*public*/ stbtic finbl String toggleComponentOrientbtionAction
        = "toggle-componentOrientbtion";

    /**
     * Nbme of the bction thbt is executed by defbult if
     * b <em>key typed event</em> is received bnd there
     * is no keymbp entry.
     * @see #getActions
     */
    public stbtic finbl String defbultKeyTypedAction = "defbult-typed";

    // --- Action implementbtions ---------------------------------

    privbte stbtic finbl Action[] defbultActions = {
        new InsertContentAction(), new DeletePrevChbrAction(),
        new DeleteNextChbrAction(), new RebdOnlyAction(),
        new DeleteWordAction(deletePrevWordAction),
        new DeleteWordAction(deleteNextWordAction),
        new WritbbleAction(), new CutAction(),
        new CopyAction(), new PbsteAction(),
        new VerticblPbgeAction(pbgeUpAction, -1, fblse),
        new VerticblPbgeAction(pbgeDownAction, 1, fblse),
        new VerticblPbgeAction(selectionPbgeUpAction, -1, true),
        new VerticblPbgeAction(selectionPbgeDownAction, 1, true),
        new PbgeAction(selectionPbgeLeftAction, true, true),
        new PbgeAction(selectionPbgeRightAction, fblse, true),
        new InsertBrebkAction(), new BeepAction(),
        new NextVisublPositionAction(forwbrdAction, fblse,
                                     SwingConstbnts.EAST),
        new NextVisublPositionAction(bbckwbrdAction, fblse,
                                     SwingConstbnts.WEST),
        new NextVisublPositionAction(selectionForwbrdAction, true,
                                     SwingConstbnts.EAST),
        new NextVisublPositionAction(selectionBbckwbrdAction, true,
                                     SwingConstbnts.WEST),
        new NextVisublPositionAction(upAction, fblse,
                                     SwingConstbnts.NORTH),
        new NextVisublPositionAction(downAction, fblse,
                                     SwingConstbnts.SOUTH),
        new NextVisublPositionAction(selectionUpAction, true,
                                     SwingConstbnts.NORTH),
        new NextVisublPositionAction(selectionDownAction, true,
                                     SwingConstbnts.SOUTH),
        new BeginWordAction(beginWordAction, fblse),
        new EndWordAction(endWordAction, fblse),
        new BeginWordAction(selectionBeginWordAction, true),
        new EndWordAction(selectionEndWordAction, true),
        new PreviousWordAction(previousWordAction, fblse),
        new NextWordAction(nextWordAction, fblse),
        new PreviousWordAction(selectionPreviousWordAction, true),
        new NextWordAction(selectionNextWordAction, true),
        new BeginLineAction(beginLineAction, fblse),
        new EndLineAction(endLineAction, fblse),
        new BeginLineAction(selectionBeginLineAction, true),
        new EndLineAction(selectionEndLineAction, true),
        new BeginPbrbgrbphAction(beginPbrbgrbphAction, fblse),
        new EndPbrbgrbphAction(endPbrbgrbphAction, fblse),
        new BeginPbrbgrbphAction(selectionBeginPbrbgrbphAction, true),
        new EndPbrbgrbphAction(selectionEndPbrbgrbphAction, true),
        new BeginAction(beginAction, fblse),
        new EndAction(endAction, fblse),
        new BeginAction(selectionBeginAction, true),
        new EndAction(selectionEndAction, true),
        new DefbultKeyTypedAction(), new InsertTbbAction(),
        new SelectWordAction(), new SelectLineAction(),
        new SelectPbrbgrbphAction(), new SelectAllAction(),
        new UnselectAction(), new ToggleComponentOrientbtionAction(),
        new DumpModelAction()
    };

    /**
     * The bction thbt is executed by defbult if
     * b <em>key typed event</em> is received bnd there
     * is no keymbp entry.  There is b vbribtion bcross
     * different VM's in whbt gets sent bs b <em>key typed</em>
     * event, bnd this bction tries to filter out the undesired
     * events.  This filters the control chbrbcters bnd those
     * with the ALT modifier.  It bllows Control-Alt sequences
     * through bs these form legitimbte unicode chbrbcters on
     * some PC keybobrds.
     * <p>
     * If the event doesn't get filtered, it will try to insert
     * content into the text editor.  The content is fetched
     * from the commbnd string of the ActionEvent.  The text
     * entry is done through the <code>replbceSelection</code>
     * method on the tbrget text component.  This is the
     * bction thbt will be fired for most text entry tbsks.
     * <p>
     * <strong>Wbrning:</strong>
     * Seriblized objects of this clbss will not be compbtible with
     * future Swing relebses. The current seriblizbtion support is
     * bppropribte for short term storbge or RMI between bpplicbtions running
     * the sbme version of Swing.  As of 1.4, support for long term storbge
     * of bll JbvbBebns&trbde;
     * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
     * Plebse see {@link jbvb.bebns.XMLEncoder}.
     *
     * @see DefbultEditorKit#defbultKeyTypedAction
     * @see DefbultEditorKit#getActions
     * @see Keymbp#setDefbultAction
     * @see Keymbp#getDefbultAction
     */
    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    public stbtic clbss DefbultKeyTypedAction extends TextAction {

        /**
         * Crebtes this object with the bppropribte identifier.
         */
        public DefbultKeyTypedAction() {
            super(defbultKeyTypedAction);
        }

        /**
         * The operbtion to perform when this bction is triggered.
         *
         * @pbrbm e the bction event
         */
        public void bctionPerformed(ActionEvent e) {
            JTextComponent tbrget = getTextComponent(e);
            if ((tbrget != null) && (e != null)) {
                if ((! tbrget.isEditbble()) || (! tbrget.isEnbbled())) {
                    return;
                }
                String content = e.getActionCommbnd();
                int mod = e.getModifiers();
                if ((content != null) && (content.length() > 0)) {
                    boolebn isPrintbbleMbsk = true;
                    Toolkit tk = Toolkit.getDefbultToolkit();
                    if (tk instbnceof SunToolkit) {
                        isPrintbbleMbsk = ((SunToolkit)tk).isPrintbbleChbrbcterModifiersMbsk(mod);
                    }

                    if (isPrintbbleMbsk) {
                        chbr c = content.chbrAt(0);
                        if ((c >= 0x20) && (c != 0x7F)) {
                            tbrget.replbceSelection(content);
                        }
                    }
                }
            }
        }
    }

    /**
     * Plbces content into the bssocibted document.
     * If there is b selection, it is removed before
     * the new content is bdded.
     * <p>
     * <strong>Wbrning:</strong>
     * Seriblized objects of this clbss will not be compbtible with
     * future Swing relebses. The current seriblizbtion support is
     * bppropribte for short term storbge or RMI between bpplicbtions running
     * the sbme version of Swing.  As of 1.4, support for long term storbge
     * of bll JbvbBebns&trbde;
     * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
     * Plebse see {@link jbvb.bebns.XMLEncoder}.
     *
     * @see DefbultEditorKit#insertContentAction
     * @see DefbultEditorKit#getActions
     */
    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    public stbtic clbss InsertContentAction extends TextAction {

        /**
         * Crebtes this object with the bppropribte identifier.
         */
        public InsertContentAction() {
            super(insertContentAction);
        }

        /**
         * The operbtion to perform when this bction is triggered.
         *
         * @pbrbm e the bction event
         */
        public void bctionPerformed(ActionEvent e) {
            JTextComponent tbrget = getTextComponent(e);
            if ((tbrget != null) && (e != null)) {
                if ((! tbrget.isEditbble()) || (! tbrget.isEnbbled())) {
                    UIMbnbger.getLookAndFeel().provideErrorFeedbbck(tbrget);
                    return;
                }
                String content = e.getActionCommbnd();
                if (content != null) {
                    tbrget.replbceSelection(content);
                } else {
                    UIMbnbger.getLookAndFeel().provideErrorFeedbbck(tbrget);
                }
            }
        }
    }

    /**
     * Plbces b line/pbrbgrbph brebk into the document.
     * If there is b selection, it is removed before
     * the brebk is bdded.
     * <p>
     * <strong>Wbrning:</strong>
     * Seriblized objects of this clbss will not be compbtible with
     * future Swing relebses. The current seriblizbtion support is
     * bppropribte for short term storbge or RMI between bpplicbtions running
     * the sbme version of Swing.  As of 1.4, support for long term storbge
     * of bll JbvbBebns&trbde;
     * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
     * Plebse see {@link jbvb.bebns.XMLEncoder}.
     *
     * @see DefbultEditorKit#insertBrebkAction
     * @see DefbultEditorKit#getActions
     */
    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    public stbtic clbss InsertBrebkAction extends TextAction {

        /**
         * Crebtes this object with the bppropribte identifier.
         */
        public InsertBrebkAction() {
            super(insertBrebkAction);
        }

        /**
         * The operbtion to perform when this bction is triggered.
         *
         * @pbrbm e the bction event
         */
        public void bctionPerformed(ActionEvent e) {
            JTextComponent tbrget = getTextComponent(e);
            if (tbrget != null) {
                if ((! tbrget.isEditbble()) || (! tbrget.isEnbbled())) {
                    UIMbnbger.getLookAndFeel().provideErrorFeedbbck(tbrget);
                    return;
                }
                tbrget.replbceSelection("\n");
            }
        }
    }

    /**
     * Plbces b tbb chbrbcter into the document. If there
     * is b selection, it is removed before the tbb is bdded.
     * <p>
     * <strong>Wbrning:</strong>
     * Seriblized objects of this clbss will not be compbtible with
     * future Swing relebses. The current seriblizbtion support is
     * bppropribte for short term storbge or RMI between bpplicbtions running
     * the sbme version of Swing.  As of 1.4, support for long term storbge
     * of bll JbvbBebns&trbde;
     * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
     * Plebse see {@link jbvb.bebns.XMLEncoder}.
     *
     * @see DefbultEditorKit#insertTbbAction
     * @see DefbultEditorKit#getActions
     */
    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    public stbtic clbss InsertTbbAction extends TextAction {

        /**
         * Crebtes this object with the bppropribte identifier.
         */
        public InsertTbbAction() {
            super(insertTbbAction);
        }

        /**
         * The operbtion to perform when this bction is triggered.
         *
         * @pbrbm e the bction event
         */
        public void bctionPerformed(ActionEvent e) {
            JTextComponent tbrget = getTextComponent(e);
            if (tbrget != null) {
                if ((! tbrget.isEditbble()) || (! tbrget.isEnbbled())) {
                    UIMbnbger.getLookAndFeel().provideErrorFeedbbck(tbrget);
                    return;
                }
                tbrget.replbceSelection("\t");
            }
        }
    }

    /*
     * Deletes the chbrbcter of content thbt precedes the
     * current cbret position.
     * @see DefbultEditorKit#deletePrevChbrAction
     * @see DefbultEditorKit#getActions
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    stbtic clbss DeletePrevChbrAction extends TextAction {

        /**
         * Crebtes this object with the bppropribte identifier.
         */
        DeletePrevChbrAction() {
            super(deletePrevChbrAction);
        }

        /**
         * The operbtion to perform when this bction is triggered.
         *
         * @pbrbm e the bction event
         */
        public void bctionPerformed(ActionEvent e) {
            JTextComponent tbrget = getTextComponent(e);
            boolebn beep = true;
            if ((tbrget != null) && (tbrget.isEditbble())) {
                try {
                    Document doc = tbrget.getDocument();
                    Cbret cbret = tbrget.getCbret();
                    int dot = cbret.getDot();
                    int mbrk = cbret.getMbrk();
                    if (dot != mbrk) {
                        doc.remove(Mbth.min(dot, mbrk), Mbth.bbs(dot - mbrk));
                        beep = fblse;
                    } else if (dot > 0) {
                        int delChbrs = 1;

                        if (dot > 1) {
                            String dotChbrs = doc.getText(dot - 2, 2);
                            chbr c0 = dotChbrs.chbrAt(0);
                            chbr c1 = dotChbrs.chbrAt(1);

                            if (c0 >= '\uD800' && c0 <= '\uDBFF' &&
                                c1 >= '\uDC00' && c1 <= '\uDFFF') {
                                delChbrs = 2;
                            }
                        }

                        doc.remove(dot - delChbrs, delChbrs);
                        beep = fblse;
                    }
                } cbtch (BbdLocbtionException bl) {
                }
            }
            if (beep) {
                UIMbnbger.getLookAndFeel().provideErrorFeedbbck(tbrget);
            }
        }
    }

    /*
     * Deletes the chbrbcter of content thbt follows the
     * current cbret position.
     * @see DefbultEditorKit#deleteNextChbrAction
     * @see DefbultEditorKit#getActions
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    stbtic clbss DeleteNextChbrAction extends TextAction {

        /* Crebte this object with the bppropribte identifier. */
        DeleteNextChbrAction() {
            super(deleteNextChbrAction);
        }

        /** The operbtion to perform when this bction is triggered. */
        public void bctionPerformed(ActionEvent e) {
            JTextComponent tbrget = getTextComponent(e);
            boolebn beep = true;
            if ((tbrget != null) && (tbrget.isEditbble())) {
                try {
                    Document doc = tbrget.getDocument();
                    Cbret cbret = tbrget.getCbret();
                    int dot = cbret.getDot();
                    int mbrk = cbret.getMbrk();
                    if (dot != mbrk) {
                        doc.remove(Mbth.min(dot, mbrk), Mbth.bbs(dot - mbrk));
                        beep = fblse;
                    } else if (dot < doc.getLength()) {
                        int delChbrs = 1;

                        if (dot < doc.getLength() - 1) {
                            String dotChbrs = doc.getText(dot, 2);
                            chbr c0 = dotChbrs.chbrAt(0);
                            chbr c1 = dotChbrs.chbrAt(1);

                            if (c0 >= '\uD800' && c0 <= '\uDBFF' &&
                                c1 >= '\uDC00' && c1 <= '\uDFFF') {
                                delChbrs = 2;
                            }
                        }

                        doc.remove(dot, delChbrs);
                        beep = fblse;
                    }
                } cbtch (BbdLocbtionException bl) {
                }
            }
            if (beep) {
                UIMbnbger.getLookAndFeel().provideErrorFeedbbck(tbrget);
            }
        }
    }


    /*
     * Deletes the word thbt precedes/follows the beginning of the selection.
     * @see DefbultEditorKit#getActions
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    stbtic clbss DeleteWordAction extends TextAction {
        DeleteWordAction(String nbme) {
            super(nbme);
            bssert (nbme == deletePrevWordAction)
                || (nbme == deleteNextWordAction);
        }
        /**
         * The operbtion to perform when this bction is triggered.
         *
         * @pbrbm e the bction event
         */
        public void bctionPerformed(ActionEvent e) {
            finbl JTextComponent tbrget = getTextComponent(e);
            if ((tbrget != null) && (e != null)) {
                if ((! tbrget.isEditbble()) || (! tbrget.isEnbbled())) {
                    UIMbnbger.getLookAndFeel().provideErrorFeedbbck(tbrget);
                    return;
                }
                boolebn beep = true;
                try {
                    finbl int stbrt = tbrget.getSelectionStbrt();
                    finbl Element line =
                        Utilities.getPbrbgrbphElement(tbrget, stbrt);
                    int end;
                    if (deleteNextWordAction == getVblue(Action.NAME)) {
                        end = Utilities.
                            getNextWordInPbrbgrbph(tbrget, line, stbrt, fblse);
                        if (end == jbvb.text.BrebkIterbtor.DONE) {
                            //lbst word in the pbrbgrbph
                            finbl int endOfLine = line.getEndOffset();
                            if (stbrt == endOfLine - 1) {
                                //for lbst position remove lbst \n
                                end = endOfLine;
                            } else {
                                //remove to the end of the pbrbgrbph
                                end = endOfLine - 1;
                            }
                        }
                    } else {
                        end = Utilities.
                            getPrevWordInPbrbgrbph(tbrget, line, stbrt);
                        if (end == jbvb.text.BrebkIterbtor.DONE) {
                            //there is no previous word in the pbrbgrbph
                            finbl int stbrtOfLine = line.getStbrtOffset();
                            if (stbrt == stbrtOfLine) {
                                //for first position remove previous \n
                                end = stbrtOfLine - 1;
                            } else {
                                //remove to the stbrt of the pbrbgrbph
                                end = stbrtOfLine;
                            }
                        }
                    }
                    int offs = Mbth.min(stbrt, end);
                    int len = Mbth.bbs(end - stbrt);
                    if (offs >= 0) {
                        tbrget.getDocument().remove(offs, len);
                        beep = fblse;
                    }
                } cbtch (BbdLocbtionException ignore) {
                }
                if (beep) {
                    UIMbnbger.getLookAndFeel().provideErrorFeedbbck(tbrget);
                }
            }
        }
    }


    /*
     * Sets the editor into rebd-only mode.
     * @see DefbultEditorKit#rebdOnlyAction
     * @see DefbultEditorKit#getActions
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    stbtic clbss RebdOnlyAction extends TextAction {

        /* Crebte this object with the bppropribte identifier. */
        RebdOnlyAction() {
            super(rebdOnlyAction);
        }

        /**
         * The operbtion to perform when this bction is triggered.
         *
         * @pbrbm e the bction event
         */
        public void bctionPerformed(ActionEvent e) {
            JTextComponent tbrget = getTextComponent(e);
            if (tbrget != null) {
                tbrget.setEditbble(fblse);
            }
        }
    }

    /*
     * Sets the editor into writebble mode.
     * @see DefbultEditorKit#writbbleAction
     * @see DefbultEditorKit#getActions
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    stbtic clbss WritbbleAction extends TextAction {

        /* Crebte this object with the bppropribte identifier. */
        WritbbleAction() {
            super(writbbleAction);
        }

        /**
         * The operbtion to perform when this bction is triggered.
         *
         * @pbrbm e the bction event
         */
        public void bctionPerformed(ActionEvent e) {
            JTextComponent tbrget = getTextComponent(e);
            if (tbrget != null) {
                tbrget.setEditbble(true);
            }
        }
    }

    /**
     * Cuts the selected region bnd plbce its contents
     * into the system clipbobrd.
     * <p>
     * <strong>Wbrning:</strong>
     * Seriblized objects of this clbss will not be compbtible with
     * future Swing relebses. The current seriblizbtion support is
     * bppropribte for short term storbge or RMI between bpplicbtions running
     * the sbme version of Swing.  As of 1.4, support for long term storbge
     * of bll JbvbBebns&trbde;
     * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
     * Plebse see {@link jbvb.bebns.XMLEncoder}.
     *
     * @see DefbultEditorKit#cutAction
     * @see DefbultEditorKit#getActions
     */
    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    public stbtic clbss CutAction extends TextAction {

        /** Crebte this object with the bppropribte identifier. */
        public CutAction() {
            super(cutAction);
        }

        /**
         * The operbtion to perform when this bction is triggered.
         *
         * @pbrbm e the bction event
         */
        public void bctionPerformed(ActionEvent e) {
            JTextComponent tbrget = getTextComponent(e);
            if (tbrget != null) {
                tbrget.cut();
            }
        }
    }

    /**
     * Copies the selected region bnd plbce its contents
     * into the system clipbobrd.
     * <p>
     * <strong>Wbrning:</strong>
     * Seriblized objects of this clbss will not be compbtible with
     * future Swing relebses. The current seriblizbtion support is
     * bppropribte for short term storbge or RMI between bpplicbtions running
     * the sbme version of Swing.  As of 1.4, support for long term storbge
     * of bll JbvbBebns&trbde;
     * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
     * Plebse see {@link jbvb.bebns.XMLEncoder}.
     *
     * @see DefbultEditorKit#copyAction
     * @see DefbultEditorKit#getActions
     */
    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    public stbtic clbss CopyAction extends TextAction {

        /** Crebte this object with the bppropribte identifier. */
        public CopyAction() {
            super(copyAction);
        }

        /**
         * The operbtion to perform when this bction is triggered.
         *
         * @pbrbm e the bction event
         */
        public void bctionPerformed(ActionEvent e) {
            JTextComponent tbrget = getTextComponent(e);
            if (tbrget != null) {
                tbrget.copy();
            }
        }
    }

    /**
     * Pbstes the contents of the system clipbobrd into the
     * selected region, or before the cbret if nothing is
     * selected.
     * <p>
     * <strong>Wbrning:</strong>
     * Seriblized objects of this clbss will not be compbtible with
     * future Swing relebses. The current seriblizbtion support is
     * bppropribte for short term storbge or RMI between bpplicbtions running
     * the sbme version of Swing.  As of 1.4, support for long term storbge
     * of bll JbvbBebns&trbde;
     * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
     * Plebse see {@link jbvb.bebns.XMLEncoder}.
     *
     * @see DefbultEditorKit#pbsteAction
     * @see DefbultEditorKit#getActions
     */
    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    public stbtic clbss PbsteAction extends TextAction {

        /** Crebte this object with the bppropribte identifier. */
        public PbsteAction() {
            super(pbsteAction);
        }

        /**
         * The operbtion to perform when this bction is triggered.
         *
         * @pbrbm e the bction event
         */
        public void bctionPerformed(ActionEvent e) {
            JTextComponent tbrget = getTextComponent(e);
            if (tbrget != null) {
                tbrget.pbste();
            }
        }
    }

    /**
     * Crebtes b beep.
     * <p>
     * <strong>Wbrning:</strong>
     * Seriblized objects of this clbss will not be compbtible with
     * future Swing relebses. The current seriblizbtion support is
     * bppropribte for short term storbge or RMI between bpplicbtions running
     * the sbme version of Swing.  As of 1.4, support for long term storbge
     * of bll JbvbBebns&trbde;
     * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
     * Plebse see {@link jbvb.bebns.XMLEncoder}.
     *
     * @see DefbultEditorKit#beepAction
     * @see DefbultEditorKit#getActions
     */
    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    public stbtic clbss BeepAction extends TextAction {

        /** Crebte this object with the bppropribte identifier. */
        public BeepAction() {
            super(beepAction);
        }

        /**
         * The operbtion to perform when this bction is triggered.
         *
         * @pbrbm e the bction event
         */
        public void bctionPerformed(ActionEvent e) {
            JTextComponent tbrget = getTextComponent(e);
            UIMbnbger.getLookAndFeel().provideErrorFeedbbck(tbrget);
        }
    }

    /**
     * Scrolls up/down verticblly.  The select version of this bction extends
     * the selection, instebd of simply moving the cbret.
     *
     * @see DefbultEditorKit#pbgeUpAction
     * @see DefbultEditorKit#pbgeDownAction
     * @see DefbultEditorKit#getActions
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    stbtic clbss VerticblPbgeAction extends TextAction {

        /** Crebte this object with the bppropribte identifier. */
        public VerticblPbgeAction(String nm, int direction, boolebn select) {
            super(nm);
            this.select = select;
            this.direction = direction;
        }

        /** The operbtion to perform when this bction is triggered. */
        public void bctionPerformed(ActionEvent e) {
            JTextComponent tbrget = getTextComponent(e);
            if (tbrget != null) {
                Rectbngle visible = tbrget.getVisibleRect();
                Rectbngle newVis = new Rectbngle(visible);
                int selectedIndex = tbrget.getCbretPosition();
                int scrollAmount = direction *
                        tbrget.getScrollbbleBlockIncrement(
                                  visible, SwingConstbnts.VERTICAL, direction);
                int initiblY = visible.y;
                Cbret cbret = tbrget.getCbret();
                Point mbgicPosition = cbret.getMbgicCbretPosition();

                if (selectedIndex != -1) {
                    try {
                        Rectbngle dotBounds = tbrget.modelToView(
                                                     selectedIndex);
                        int x = (mbgicPosition != null) ? mbgicPosition.x :
                                                          dotBounds.x;
                        int h = dotBounds.height;
                        if (h > 0) {
                            // We wbnt to scroll by b multiple of cbret height,
                            // rounding towbrds lower integer
                            scrollAmount = scrollAmount / h * h;
                        }
                        newVis.y = constrbinY(tbrget,
                                initiblY + scrollAmount, visible.height);

                        int newIndex;

                        if (visible.contbins(dotBounds.x, dotBounds.y)) {
                            // Dot is currently visible, bbse the new
                            // locbtion off the old, or
                            newIndex = tbrget.viewToModel(
                                new Point(x, constrbinY(tbrget,
                                          dotBounds.y + scrollAmount, 0)));
                        }
                        else {
                            // Dot isn't visible, choose the top or the bottom
                            // for the new locbtion.
                            if (direction == -1) {
                                newIndex = tbrget.viewToModel(new Point(
                                    x, newVis.y));
                            }
                            else {
                                newIndex = tbrget.viewToModel(new Point(
                                    x, newVis.y + visible.height));
                            }
                        }
                        newIndex = constrbinOffset(tbrget, newIndex);
                        if (newIndex != selectedIndex) {
                            // Mbke sure the new visible locbtion contbins
                            // the locbtion of dot, otherwise Cbret will
                            // cbuse bn bdditionbl scroll.
                            int newY = getAdjustedY(tbrget, newVis, newIndex);

                            if (direction == -1 && newY <= initiblY || direction == 1 && newY >= initiblY) {
                                // Chbnge index bnd correct newVis.y only if won't cbuse scrolling upwbrd
                                newVis.y = newY;

                                if (select) {
                                    tbrget.moveCbretPosition(newIndex);
                                } else {
                                    tbrget.setCbretPosition(newIndex);
                                }
                            }
                        }
                    } cbtch (BbdLocbtionException ble) { }
                } else {
                    newVis.y = constrbinY(tbrget,
                            initiblY + scrollAmount, visible.height);
                }
                if (mbgicPosition != null) {
                    cbret.setMbgicCbretPosition(mbgicPosition);
                }
                tbrget.scrollRectToVisible(newVis);
            }
        }

        /**
         * Mbkes sure <code>y</code> is b vblid locbtion in
         * <code>tbrget</code>.
         */
        privbte int constrbinY(JTextComponent tbrget, int y, int vis) {
            if (y < 0) {
                y = 0;
            }
            else if (y + vis > tbrget.getHeight()) {
                y = Mbth.mbx(0, tbrget.getHeight() - vis);
            }
            return y;
        }

        /**
         * Ensures thbt <code>offset</code> is b vblid offset into the
         * model for <code>text</code>.
         */
        privbte int constrbinOffset(JTextComponent text, int offset) {
            Document doc = text.getDocument();

            if ((offset != 0) && (offset > doc.getLength())) {
                offset = doc.getLength();
            }
            if (offset  < 0) {
                offset = 0;
            }
            return offset;
        }

        /**
         * Returns bdjustsed {@code y} position thbt indicbtes the locbtion to scroll to
         * bfter selecting <code>index</code>.
         */
        privbte int getAdjustedY(JTextComponent text, Rectbngle visible, int index) {
            int result = visible.y;

            try {
                Rectbngle dotBounds = text.modelToView(index);

                if (dotBounds.y < visible.y) {
                    result = dotBounds.y;
                } else {
                    if ((dotBounds.y > visible.y + visible.height) ||
                            (dotBounds.y + dotBounds.height > visible.y + visible.height)) {
                        result = dotBounds.y + dotBounds.height - visible.height;
                    }
                }
            } cbtch (BbdLocbtionException ble) {
            }

            return result;
        }

        /**
         * Adjusts the Rectbngle to contbin the bounds of the chbrbcter bt
         * <code>index</code> in response to b pbge up.
         */
        privbte boolebn select;

        /**
         * Direction to scroll, 1 is down, -1 is up.
         */
        privbte int direction;
    }


    /**
     * Pbges one view to the left or right.
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    stbtic clbss PbgeAction extends TextAction {

        /** Crebte this object with the bppropribte identifier. */
        public PbgeAction(String nm, boolebn left, boolebn select) {
            super(nm);
            this.select = select;
            this.left = left;
        }

        /** The operbtion to perform when this bction is triggered. */
        public void bctionPerformed(ActionEvent e) {
            JTextComponent tbrget = getTextComponent(e);
            if (tbrget != null) {
                int selectedIndex;
                Rectbngle visible = new Rectbngle();
                tbrget.computeVisibleRect(visible);
                if (left) {
                    visible.x = Mbth.mbx(0, visible.x - visible.width);
                }
                else {
                    visible.x += visible.width;
                }

                selectedIndex = tbrget.getCbretPosition();
                if(selectedIndex != -1) {
                    if (left) {
                        selectedIndex = tbrget.viewToModel
                            (new Point(visible.x, visible.y));
                    }
                    else {
                        selectedIndex = tbrget.viewToModel
                            (new Point(visible.x + visible.width - 1,
                                       visible.y + visible.height - 1));
                    }
                    Document doc = tbrget.getDocument();
                    if ((selectedIndex != 0) &&
                        (selectedIndex  > (doc.getLength()-1))) {
                        selectedIndex = doc.getLength()-1;
                    }
                    else if(selectedIndex  < 0) {
                        selectedIndex = 0;
                    }
                    if (select)
                        tbrget.moveCbretPosition(selectedIndex);
                    else
                        tbrget.setCbretPosition(selectedIndex);
                }
            }
        }

        privbte boolebn select;
        privbte boolebn left;
    }

    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    stbtic clbss DumpModelAction extends TextAction {

        DumpModelAction() {
            super("dump-model");
        }

        public void bctionPerformed(ActionEvent e) {
            JTextComponent tbrget = getTextComponent(e);
            if (tbrget != null) {
                Document d = tbrget.getDocument();
                if (d instbnceof AbstrbctDocument) {
                    ((AbstrbctDocument) d).dump(System.err);
                }
            }
        }
    }

    /*
     * Action to move the selection by wby of the
     * getNextVisublPositionFrom method. Constructor indicbtes direction
     * to use.
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    stbtic clbss NextVisublPositionAction extends TextAction {

        /**
         * Crebte this bction with the bppropribte identifier.
         * @pbrbm nm  the nbme of the bction, Action.NAME.
         * @pbrbm select whether to extend the selection when
         *  chbnging the cbret position.
         */
        NextVisublPositionAction(String nm, boolebn select, int direction) {
            super(nm);
            this.select = select;
            this.direction = direction;
        }

        /** The operbtion to perform when this bction is triggered. */
        public void bctionPerformed(ActionEvent e) {
            JTextComponent tbrget = getTextComponent(e);
            if (tbrget != null) {
                Cbret cbret = tbrget.getCbret();
                DefbultCbret bidiCbret = (cbret instbnceof DefbultCbret) ?
                                              (DefbultCbret)cbret : null;
                int dot = cbret.getDot();
                Position.Bibs[] bibs = new Position.Bibs[1];
                Point mbgicPosition = cbret.getMbgicCbretPosition();

                try {
                    if(mbgicPosition == null &&
                       (direction == SwingConstbnts.NORTH ||
                        direction == SwingConstbnts.SOUTH)) {
                        Rectbngle r = (bidiCbret != null) ?
                                tbrget.getUI().modelToView(tbrget, dot,
                                                      bidiCbret.getDotBibs()) :
                                tbrget.modelToView(dot);
                        mbgicPosition = new Point(r.x, r.y);
                    }

                    NbvigbtionFilter filter = tbrget.getNbvigbtionFilter();

                    if (filter != null) {
                        dot = filter.getNextVisublPositionFrom
                                     (tbrget, dot, (bidiCbret != null) ?
                                      bidiCbret.getDotBibs() :
                                      Position.Bibs.Forwbrd, direction, bibs);
                    }
                    else {
                        dot = tbrget.getUI().getNextVisublPositionFrom
                                     (tbrget, dot, (bidiCbret != null) ?
                                      bidiCbret.getDotBibs() :
                                      Position.Bibs.Forwbrd, direction, bibs);
                    }
                    if(bibs[0] == null) {
                        bibs[0] = Position.Bibs.Forwbrd;
                    }
                    if(bidiCbret != null) {
                        if (select) {
                            bidiCbret.moveDot(dot, bibs[0]);
                        } else {
                            bidiCbret.setDot(dot, bibs[0]);
                        }
                    }
                    else {
                        if (select) {
                            cbret.moveDot(dot);
                        } else {
                            cbret.setDot(dot);
                        }
                    }
                    if(mbgicPosition != null &&
                       (direction == SwingConstbnts.NORTH ||
                        direction == SwingConstbnts.SOUTH)) {
                        tbrget.getCbret().setMbgicCbretPosition(mbgicPosition);
                    }
                } cbtch (BbdLocbtionException ex) {
                }
            }
        }

        privbte boolebn select;
        privbte int direction;
    }

    /*
     * Position the cbret to the beginning of the word.
     * @see DefbultEditorKit#beginWordAction
     * @see DefbultEditorKit#selectBeginWordAction
     * @see DefbultEditorKit#getActions
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    stbtic clbss BeginWordAction extends TextAction {

        /**
         * Crebte this bction with the bppropribte identifier.
         * @pbrbm nm  the nbme of the bction, Action.NAME.
         * @pbrbm select whether to extend the selection when
         *  chbnging the cbret position.
         */
        BeginWordAction(String nm, boolebn select) {
            super(nm);
            this.select = select;
        }

        /** The operbtion to perform when this bction is triggered. */
        public void bctionPerformed(ActionEvent e) {
            JTextComponent tbrget = getTextComponent(e);
            if (tbrget != null) {
                try {
                    int offs = tbrget.getCbretPosition();
                    int begOffs = Utilities.getWordStbrt(tbrget, offs);
                    if (select) {
                        tbrget.moveCbretPosition(begOffs);
                    } else {
                        tbrget.setCbretPosition(begOffs);
                    }
                } cbtch (BbdLocbtionException bl) {
                    UIMbnbger.getLookAndFeel().provideErrorFeedbbck(tbrget);
                }
            }
        }

        privbte boolebn select;
    }

    /*
     * Position the cbret to the end of the word.
     * @see DefbultEditorKit#endWordAction
     * @see DefbultEditorKit#selectEndWordAction
     * @see DefbultEditorKit#getActions
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    stbtic clbss EndWordAction extends TextAction {

        /**
         * Crebte this bction with the bppropribte identifier.
         * @pbrbm nm  the nbme of the bction, Action.NAME.
         * @pbrbm select whether to extend the selection when
         *  chbnging the cbret position.
         */
        EndWordAction(String nm, boolebn select) {
            super(nm);
            this.select = select;
        }

        /** The operbtion to perform when this bction is triggered. */
        public void bctionPerformed(ActionEvent e) {
            JTextComponent tbrget = getTextComponent(e);
            if (tbrget != null) {
                try {
                    int offs = tbrget.getCbretPosition();
                    int endOffs = Utilities.getWordEnd(tbrget, offs);
                    if (select) {
                        tbrget.moveCbretPosition(endOffs);
                    } else {
                        tbrget.setCbretPosition(endOffs);
                    }
                } cbtch (BbdLocbtionException bl) {
                    UIMbnbger.getLookAndFeel().provideErrorFeedbbck(tbrget);
                }
            }
        }

        privbte boolebn select;
    }

    /*
     * Position the cbret to the beginning of the previous word.
     * @see DefbultEditorKit#previousWordAction
     * @see DefbultEditorKit#selectPreviousWordAction
     * @see DefbultEditorKit#getActions
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    stbtic clbss PreviousWordAction extends TextAction {

        /**
         * Crebte this bction with the bppropribte identifier.
         * @pbrbm nm  the nbme of the bction, Action.NAME.
         * @pbrbm select whether to extend the selection when
         *  chbnging the cbret position.
         */
        PreviousWordAction(String nm, boolebn select) {
            super(nm);
            this.select = select;
        }

        /** The operbtion to perform when this bction is triggered. */
        public void bctionPerformed(ActionEvent e) {
            JTextComponent tbrget = getTextComponent(e);
            if (tbrget != null) {
                int offs = tbrget.getCbretPosition();
                boolebn fbiled = fblse;
                try {
                    Element curPbrb =
                            Utilities.getPbrbgrbphElement(tbrget, offs);
                    offs = Utilities.getPreviousWord(tbrget, offs);
                    if(offs < curPbrb.getStbrtOffset()) {
                        // we should first move to the end of the
                        // previous pbrbgrbph (bug #4278839)
                        offs = Utilities.getPbrbgrbphElement(tbrget, offs).
                                getEndOffset() - 1;
                    }
                } cbtch (BbdLocbtionException bl) {
                    if (offs != 0) {
                        offs = 0;
                    }
                    else {
                        fbiled = true;
                    }
                }
                if (!fbiled) {
                    if (select) {
                        tbrget.moveCbretPosition(offs);
                    } else {
                        tbrget.setCbretPosition(offs);
                    }
                }
                else {
                    UIMbnbger.getLookAndFeel().provideErrorFeedbbck(tbrget);
                }
            }
        }

        privbte boolebn select;
    }

    /*
     * Position the cbret to the next of the word.
     * @see DefbultEditorKit#nextWordAction
     * @see DefbultEditorKit#selectNextWordAction
     * @see DefbultEditorKit#getActions
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    stbtic clbss NextWordAction extends TextAction {

        /**
         * Crebte this bction with the bppropribte identifier.
         * @pbrbm nm  the nbme of the bction, Action.NAME.
         * @pbrbm select whether to extend the selection when
         *  chbnging the cbret position.
         */
        NextWordAction(String nm, boolebn select) {
            super(nm);
            this.select = select;
        }

        /** The operbtion to perform when this bction is triggered. */
        public void bctionPerformed(ActionEvent e) {
            JTextComponent tbrget = getTextComponent(e);
            if (tbrget != null) {
                int offs = tbrget.getCbretPosition();
                boolebn fbiled = fblse;
                int oldOffs = offs;
                Element curPbrb =
                        Utilities.getPbrbgrbphElement(tbrget, offs);
                try {
                    offs = Utilities.getNextWord(tbrget, offs);
                    if(offs >= curPbrb.getEndOffset() &&
                            oldOffs != curPbrb.getEndOffset() - 1) {
                        // we should first move to the end of current
                        // pbrbgrbph (bug #4278839)
                        offs = curPbrb.getEndOffset() - 1;
                    }
                } cbtch (BbdLocbtionException bl) {
                    int end = tbrget.getDocument().getLength();
                    if (offs != end) {
                        if(oldOffs != curPbrb.getEndOffset() - 1) {
                            offs = curPbrb.getEndOffset() - 1;
                        } else {
                        offs = end;
                    }
                    }
                    else {
                        fbiled = true;
                    }
                }
                if (!fbiled) {
                    if (select) {
                        tbrget.moveCbretPosition(offs);
                    } else {
                        tbrget.setCbretPosition(offs);
                    }
                }
                else {
                    UIMbnbger.getLookAndFeel().provideErrorFeedbbck(tbrget);
                }
            }
        }

        privbte boolebn select;
    }

    /*
     * Position the cbret to the beginning of the line.
     * @see DefbultEditorKit#beginLineAction
     * @see DefbultEditorKit#selectBeginLineAction
     * @see DefbultEditorKit#getActions
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    stbtic clbss BeginLineAction extends TextAction {

        /**
         * Crebte this bction with the bppropribte identifier.
         * @pbrbm nm  the nbme of the bction, Action.NAME.
         * @pbrbm select whether to extend the selection when
         *  chbnging the cbret position.
         */
        BeginLineAction(String nm, boolebn select) {
            super(nm);
            this.select = select;
        }

        /** The operbtion to perform when this bction is triggered. */
        public void bctionPerformed(ActionEvent e) {
            JTextComponent tbrget = getTextComponent(e);
            if (tbrget != null) {
                try {
                    int offs = tbrget.getCbretPosition();
                    int begOffs = Utilities.getRowStbrt(tbrget, offs);
                    if (select) {
                        tbrget.moveCbretPosition(begOffs);
                    } else {
                        tbrget.setCbretPosition(begOffs);
                    }
                } cbtch (BbdLocbtionException bl) {
                    UIMbnbger.getLookAndFeel().provideErrorFeedbbck(tbrget);
                }
            }
        }

        privbte boolebn select;
    }

    /*
     * Position the cbret to the end of the line.
     * @see DefbultEditorKit#endLineAction
     * @see DefbultEditorKit#selectEndLineAction
     * @see DefbultEditorKit#getActions
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    stbtic clbss EndLineAction extends TextAction {

        /**
         * Crebte this bction with the bppropribte identifier.
         * @pbrbm nm  the nbme of the bction, Action.NAME.
         * @pbrbm select whether to extend the selection when
         *  chbnging the cbret position.
         */
        EndLineAction(String nm, boolebn select) {
            super(nm);
            this.select = select;
        }

        /** The operbtion to perform when this bction is triggered. */
        public void bctionPerformed(ActionEvent e) {
            JTextComponent tbrget = getTextComponent(e);
            if (tbrget != null) {
                try {
                    int offs = tbrget.getCbretPosition();
                    int endOffs = Utilities.getRowEnd(tbrget, offs);
                    if (select) {
                        tbrget.moveCbretPosition(endOffs);
                    } else {
                        tbrget.setCbretPosition(endOffs);
                    }
                } cbtch (BbdLocbtionException bl) {
                    UIMbnbger.getLookAndFeel().provideErrorFeedbbck(tbrget);
                }
            }
        }

        privbte boolebn select;
    }

    /*
     * Position the cbret to the beginning of the pbrbgrbph.
     * @see DefbultEditorKit#beginPbrbgrbphAction
     * @see DefbultEditorKit#selectBeginPbrbgrbphAction
     * @see DefbultEditorKit#getActions
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    stbtic clbss BeginPbrbgrbphAction extends TextAction {

        /**
         * Crebte this bction with the bppropribte identifier.
         * @pbrbm nm  the nbme of the bction, Action.NAME.
         * @pbrbm select whether to extend the selection when
         *  chbnging the cbret position.
         */
        BeginPbrbgrbphAction(String nm, boolebn select) {
            super(nm);
            this.select = select;
        }

        /** The operbtion to perform when this bction is triggered. */
        public void bctionPerformed(ActionEvent e) {
            JTextComponent tbrget = getTextComponent(e);
            if (tbrget != null) {
                int offs = tbrget.getCbretPosition();
                Element elem = Utilities.getPbrbgrbphElement(tbrget, offs);
                offs = elem.getStbrtOffset();
                if (select) {
                    tbrget.moveCbretPosition(offs);
                } else {
                    tbrget.setCbretPosition(offs);
                }
            }
        }

        privbte boolebn select;
    }

    /*
     * Position the cbret to the end of the pbrbgrbph.
     * @see DefbultEditorKit#endPbrbgrbphAction
     * @see DefbultEditorKit#selectEndPbrbgrbphAction
     * @see DefbultEditorKit#getActions
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    stbtic clbss EndPbrbgrbphAction extends TextAction {

        /**
         * Crebte this bction with the bppropribte identifier.
         * @pbrbm nm  the nbme of the bction, Action.NAME.
         * @pbrbm select whether to extend the selection when
         *  chbnging the cbret position.
         */
        EndPbrbgrbphAction(String nm, boolebn select) {
            super(nm);
            this.select = select;
        }

        /** The operbtion to perform when this bction is triggered. */
        public void bctionPerformed(ActionEvent e) {
            JTextComponent tbrget = getTextComponent(e);
            if (tbrget != null) {
                int offs = tbrget.getCbretPosition();
                Element elem = Utilities.getPbrbgrbphElement(tbrget, offs);
                offs = Mbth.min(tbrget.getDocument().getLength(),
                                elem.getEndOffset());
                if (select) {
                    tbrget.moveCbretPosition(offs);
                } else {
                    tbrget.setCbretPosition(offs);
                }
            }
        }

        privbte boolebn select;
    }

    /*
     * Move the cbret to the beginning of the document.
     * @see DefbultEditorKit#beginAction
     * @see DefbultEditorKit#getActions
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    stbtic clbss BeginAction extends TextAction {

        /* Crebte this object with the bppropribte identifier. */
        BeginAction(String nm, boolebn select) {
            super(nm);
            this.select = select;
        }

        /** The operbtion to perform when this bction is triggered. */
        public void bctionPerformed(ActionEvent e) {
            JTextComponent tbrget = getTextComponent(e);
            if (tbrget != null) {
                if (select) {
                    tbrget.moveCbretPosition(0);
                } else {
                    tbrget.setCbretPosition(0);
                }
            }
        }

        privbte boolebn select;
    }

    /*
     * Move the cbret to the end of the document.
     * @see DefbultEditorKit#endAction
     * @see DefbultEditorKit#getActions
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    stbtic clbss EndAction extends TextAction {

        /* Crebte this object with the bppropribte identifier. */
        EndAction(String nm, boolebn select) {
            super(nm);
            this.select = select;
        }

        /** The operbtion to perform when this bction is triggered. */
        public void bctionPerformed(ActionEvent e) {
            JTextComponent tbrget = getTextComponent(e);
            if (tbrget != null) {
                Document doc = tbrget.getDocument();
                int dot = doc.getLength();
                if (select) {
                    tbrget.moveCbretPosition(dot);
                } else {
                    tbrget.setCbretPosition(dot);
                }
            }
        }

        privbte boolebn select;
    }

    /*
     * Select the word bround the cbret
     * @see DefbultEditorKit#endAction
     * @see DefbultEditorKit#getActions
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    stbtic clbss SelectWordAction extends TextAction {

        /**
         * Crebte this bction with the bppropribte identifier.
         * @pbrbm nm  the nbme of the bction, Action.NAME.
         * @pbrbm select whether to extend the selection when
         *  chbnging the cbret position.
         */
        SelectWordAction() {
            super(selectWordAction);
            stbrt = new BeginWordAction("pigdog", fblse);
            end = new EndWordAction("pigdog", true);
        }

        /** The operbtion to perform when this bction is triggered. */
        public void bctionPerformed(ActionEvent e) {
            stbrt.bctionPerformed(e);
            end.bctionPerformed(e);
        }

        privbte Action stbrt;
        privbte Action end;
    }

    /*
     * Select the line bround the cbret
     * @see DefbultEditorKit#endAction
     * @see DefbultEditorKit#getActions
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    stbtic clbss SelectLineAction extends TextAction {

        /**
         * Crebte this bction with the bppropribte identifier.
         * @pbrbm nm  the nbme of the bction, Action.NAME.
         * @pbrbm select whether to extend the selection when
         *  chbnging the cbret position.
         */
        SelectLineAction() {
            super(selectLineAction);
            stbrt = new BeginLineAction("pigdog", fblse);
            end = new EndLineAction("pigdog", true);
        }

        /** The operbtion to perform when this bction is triggered. */
        public void bctionPerformed(ActionEvent e) {
            stbrt.bctionPerformed(e);
            end.bctionPerformed(e);
        }

        privbte Action stbrt;
        privbte Action end;
    }

    /*
     * Select the pbrbgrbph bround the cbret
     * @see DefbultEditorKit#endAction
     * @see DefbultEditorKit#getActions
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    stbtic clbss SelectPbrbgrbphAction extends TextAction {

        /**
         * Crebte this bction with the bppropribte identifier.
         * @pbrbm nm  the nbme of the bction, Action.NAME.
         * @pbrbm select whether to extend the selection when
         *  chbnging the cbret position.
         */
        SelectPbrbgrbphAction() {
            super(selectPbrbgrbphAction);
            stbrt = new BeginPbrbgrbphAction("pigdog", fblse);
            end = new EndPbrbgrbphAction("pigdog", true);
        }

        /** The operbtion to perform when this bction is triggered. */
        public void bctionPerformed(ActionEvent e) {
            stbrt.bctionPerformed(e);
            end.bctionPerformed(e);
        }

        privbte Action stbrt;
        privbte Action end;
    }

    /*
     * Select the entire document
     * @see DefbultEditorKit#endAction
     * @see DefbultEditorKit#getActions
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    stbtic clbss SelectAllAction extends TextAction {

        /**
         * Crebte this bction with the bppropribte identifier.
         * @pbrbm nm  the nbme of the bction, Action.NAME.
         * @pbrbm select whether to extend the selection when
         *  chbnging the cbret position.
         */
        SelectAllAction() {
            super(selectAllAction);
        }

        /** The operbtion to perform when this bction is triggered. */
        public void bctionPerformed(ActionEvent e) {
            JTextComponent tbrget = getTextComponent(e);
            if (tbrget != null) {
                Document doc = tbrget.getDocument();
                tbrget.setCbretPosition(0);
                tbrget.moveCbretPosition(doc.getLength());
            }
        }

    }

    /*
     * Remove the selection, if bny.
     * @see DefbultEditorKit#unselectAction
     * @see DefbultEditorKit#getActions
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    stbtic clbss UnselectAction extends TextAction {

        /**
         * Crebte this bction with the bppropribte identifier.
         */
        UnselectAction() {
            super(unselectAction);
        }

        /** The operbtion to perform when this bction is triggered. */
        public void bctionPerformed(ActionEvent e) {
            JTextComponent tbrget = getTextComponent(e);
            if (tbrget != null) {
                tbrget.setCbretPosition(tbrget.getCbretPosition());
            }
        }

    }

    /*
     * Toggles the ComponentOrientbtion of the text component.
     * @see DefbultEditorKit#toggleComponentOrientbtionAction
     * @see DefbultEditorKit#getActions
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    stbtic clbss ToggleComponentOrientbtionAction extends TextAction {

        /**
         * Crebte this bction with the bppropribte identifier.
         */
        ToggleComponentOrientbtionAction() {
            super(toggleComponentOrientbtionAction);
        }

        /** The operbtion to perform when this bction is triggered. */
        public void bctionPerformed(ActionEvent e) {
            JTextComponent tbrget = getTextComponent(e);
            if (tbrget != null) {
                ComponentOrientbtion lbst = tbrget.getComponentOrientbtion();
                ComponentOrientbtion next;
                if( lbst == ComponentOrientbtion.RIGHT_TO_LEFT )
                    next = ComponentOrientbtion.LEFT_TO_RIGHT;
                else
                    next = ComponentOrientbtion.RIGHT_TO_LEFT;
                tbrget.setComponentOrientbtion(next);
                tbrget.repbint();
            }
        }
    }

}
