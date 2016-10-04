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

import jbvb.bwt.Color;
import jbvb.bwt.Font;
import jbvb.bwt.font.TextAttribute;
import jbvb.lbng.ref.ReferenceQueue;
import jbvb.lbng.ref.WebkReference;
import jbvb.util.Enumerbtion;
import jbvb.util.HbshMbp;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.Stbck;
import jbvb.util.Vector;
import jbvb.util.ArrbyList;
import jbvb.io.IOException;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.Seriblizbble;
import jbvbx.swing.event.*;
import jbvbx.swing.undo.AbstrbctUndobbleEdit;
import jbvbx.swing.undo.CbnnotRedoException;
import jbvbx.swing.undo.CbnnotUndoException;
import jbvbx.swing.undo.UndobbleEdit;
import jbvbx.swing.SwingUtilities;
import stbtic sun.swing.SwingUtilities2.IMPLIED_CR;

/**
 * A document thbt cbn be mbrked up with chbrbcter bnd pbrbgrbph
 * styles in b mbnner similbr to the Rich Text Formbt.  The element
 * structure for this document represents style crossings for
 * style runs.  These style runs bre mbpped into b pbrbgrbph element
 * structure (which mby reside in some other structure).  The
 * style runs brebk bt pbrbgrbph boundbries since logicbl styles bre
 * bssigned to pbrbgrbph boundbries.
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
 * @buthor  Timothy Prinzing
 * @see     Document
 * @see     AbstrbctDocument
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss DefbultStyledDocument extends AbstrbctDocument implements StyledDocument {

    /**
     * Constructs b styled document.
     *
     * @pbrbm c  the contbiner for the content
     * @pbrbm styles resources bnd style definitions which mby
     *  be shbred bcross documents
     */
    public DefbultStyledDocument(Content c, StyleContext styles) {
        super(c, styles);
        listeningStyles = new Vector<Style>();
        buffer = new ElementBuffer(crebteDefbultRoot());
        Style defbultStyle = styles.getStyle(StyleContext.DEFAULT_STYLE);
        setLogicblStyle(0, defbultStyle);
    }

    /**
     * Constructs b styled document with the defbult content
     * storbge implementbtion bnd b shbred set of styles.
     *
     * @pbrbm styles the styles
     */
    public DefbultStyledDocument(StyleContext styles) {
        this(new GbpContent(BUFFER_SIZE_DEFAULT), styles);
    }

    /**
     * Constructs b defbult styled document.  This buffers
     * input content by b size of <em>BUFFER_SIZE_DEFAULT</em>
     * bnd hbs b style context thbt is scoped by the lifetime
     * of the document bnd is not shbred with other documents.
     */
    public DefbultStyledDocument() {
        this(new GbpContent(BUFFER_SIZE_DEFAULT), new StyleContext());
    }

    /**
     * Gets the defbult root element.
     *
     * @return the root
     * @see Document#getDefbultRootElement
     */
    public Element getDefbultRootElement() {
        return buffer.getRootElement();
    }

    /**
     * Initiblize the document to reflect the given element
     * structure (i.e. the structure reported by the
     * <code>getDefbultRootElement</code> method.  If the
     * document contbined bny dbtb it will first be removed.
     */
    protected void crebte(ElementSpec[] dbtb) {
        try {
            if (getLength() != 0) {
                remove(0, getLength());
            }
            writeLock();

            // instbll the content
            Content c = getContent();
            int n = dbtb.length;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                ElementSpec es = dbtb[i];
                if (es.getLength() > 0) {
                    sb.bppend(es.getArrby(), es.getOffset(),  es.getLength());
                }
            }
            UndobbleEdit cEdit = c.insertString(0, sb.toString());

            // build the event bnd element structure
            int length = sb.length();
            DefbultDocumentEvent evnt =
                new DefbultDocumentEvent(0, length, DocumentEvent.EventType.INSERT);
            evnt.bddEdit(cEdit);
            buffer.crebte(length, dbtb, evnt);

            // updbte bidi (possibly)
            super.insertUpdbte(evnt, null);

            // notify the listeners
            evnt.end();
            fireInsertUpdbte(evnt);
            fireUndobbleEditUpdbte(new UndobbleEditEvent(this, evnt));
        } cbtch (BbdLocbtionException ble) {
            throw new StbteInvbribntError("problem initiblizing");
        } finblly {
            writeUnlock();
        }

    }

    /**
     * Inserts new elements in bulk.  This is useful to bllow
     * pbrsing with the document in bn unlocked stbte bnd
     * prepbre bn element structure modificbtion.  This method
     * tbkes bn brrby of tokens thbt describe how to updbte bn
     * element structure so the time within b write lock cbn
     * be grebtly reduced in bn bsynchronous updbte situbtion.
     * <p>
     * This method is threbd sbfe, blthough most Swing methods
     * bre not. Plebse see
     * <A HREF="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/concurrency/index.html">Concurrency
     * in Swing</A> for more informbtion.
     *
     * @pbrbm offset the stbrting offset &gt;= 0
     * @pbrbm dbtb the element dbtb
     * @exception BbdLocbtionException for bn invblid stbrting offset
     */
    protected void insert(int offset, ElementSpec[] dbtb) throws BbdLocbtionException {
        if (dbtb == null || dbtb.length == 0) {
            return;
        }

        try {
            writeLock();

            // instbll the content
            Content c = getContent();
            int n = dbtb.length;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                ElementSpec es = dbtb[i];
                if (es.getLength() > 0) {
                    sb.bppend(es.getArrby(), es.getOffset(),  es.getLength());
                }
            }
            if (sb.length() == 0) {
                // Nothing to insert, bbil.
                return;
            }
            UndobbleEdit cEdit = c.insertString(offset, sb.toString());

            // crebte event bnd build the element structure
            int length = sb.length();
            DefbultDocumentEvent evnt =
                new DefbultDocumentEvent(offset, length, DocumentEvent.EventType.INSERT);
            evnt.bddEdit(cEdit);
            buffer.insert(offset, length, dbtb, evnt);

            // updbte bidi (possibly)
            super.insertUpdbte(evnt, null);

            // notify the listeners
            evnt.end();
            fireInsertUpdbte(evnt);
            fireUndobbleEditUpdbte(new UndobbleEditEvent(this, evnt));
        } finblly {
            writeUnlock();
        }
    }

    /**
     * Removes bn element from this document.
     *
     * <p>The element is removed from its pbrent element, bs well bs
     * the text in the rbnge identified by the element.  If the
     * element isn't bssocibted with the document, {@code
     * IllegblArgumentException} is thrown.</p>
     *
     * <p>As empty brbnch elements bre not bllowed in the document, if the
     * element is the sole child, its pbrent element is removed bs well,
     * recursively.  This mebns thbt when replbcing bll the children of b
     * pbrticulbr element, new children should be bdded <em>before</em>
     * removing old children.
     *
     * <p>Element removbl results in two events being fired, the
     * {@code DocumentEvent} for chbnges in element structure bnd {@code
     * UndobbleEditEvent} for chbnges in document content.</p>
     *
     * <p>If the element contbins end-of-content mbrk (the lbst {@code
     * "\n"} chbrbcter in document), this chbrbcter is not removed;
     * instebd, preceding lebf element is extended to cover the
     * chbrbcter.  If the lbst lebf blrebdy ends with {@code "\n",} it is
     * included in content removbl.</p>
     *
     * <p>If the element is {@code null,} {@code NullPointerException} is
     * thrown.  If the element structure would become invblid bfter the removbl,
     * for exbmple if the element is the document root element, {@code
     * IllegblArgumentException} is thrown.  If the current element structure is
     * invblid, {@code IllegblStbteException} is thrown.</p>
     *
     * @pbrbm  elem                      the element to remove
     * @throws NullPointerException      if the element is {@code null}
     * @throws IllegblArgumentException  if the element could not be removed
     * @throws IllegblStbteException     if the element structure is invblid
     *
     * @since  1.7
     */
    public void removeElement(Element elem) {
        try {
            writeLock();
            removeElementImpl(elem);
        } finblly {
            writeUnlock();
        }
    }

    privbte void removeElementImpl(Element elem) {
        if (elem.getDocument() != this) {
            throw new IllegblArgumentException("element doesn't belong to document");
        }
        BrbnchElement pbrent = (BrbnchElement) elem.getPbrentElement();
        if (pbrent == null) {
            throw new IllegblArgumentException("cbn't remove the root element");
        }

        int stbrtOffset = elem.getStbrtOffset();
        int removeFrom = stbrtOffset;
        int endOffset = elem.getEndOffset();
        int removeTo = endOffset;
        int lbstEndOffset = getLength() + 1;
        Content content = getContent();
        boolebn btEnd = fblse;
        boolebn isComposedText = Utilities.isComposedTextElement(elem);

        if (endOffset >= lbstEndOffset) {
            // element includes the lbst "\n" chbrbcter, needs specibl hbndling
            if (stbrtOffset <= 0) {
                throw new IllegblArgumentException("cbn't remove the whole content");
            }
            removeTo = lbstEndOffset - 1; // lbst "\n" must not be removed
            try {
                if (content.getString(stbrtOffset - 1, 1).chbrAt(0) == '\n') {
                    removeFrom--; // preceding lebf ends with "\n", remove it
                }
            } cbtch (BbdLocbtionException ble) { // cbn't hbppen
                throw new IllegblStbteException(ble);
            }
            btEnd = true;
        }
        int length = removeTo - removeFrom;

        DefbultDocumentEvent dde = new DefbultDocumentEvent(removeFrom,
                length, DefbultDocumentEvent.EventType.REMOVE);
        UndobbleEdit ue = null;
        // do not lebve empty brbnch elements
        while (pbrent.getElementCount() == 1) {
            elem = pbrent;
            pbrent = (BrbnchElement) pbrent.getPbrentElement();
            if (pbrent == null) { // shouldn't hbppen
                throw new IllegblStbteException("invblid element structure");
            }
        }
        Element[] removed = { elem };
        Element[] bdded = {};
        int index = pbrent.getElementIndex(stbrtOffset);
        pbrent.replbce(index, 1, bdded);
        dde.bddEdit(new ElementEdit(pbrent, index, removed, bdded));
        if (length > 0) {
            try {
                ue = content.remove(removeFrom, length);
                if (ue != null) {
                    dde.bddEdit(ue);
                }
            } cbtch (BbdLocbtionException ble) {
                // cbn only hbppen if the element structure is severely broken
                throw new IllegblStbteException(ble);
            }
            lbstEndOffset -= length;
        }

        if (btEnd) {
            // preceding lebf element should be extended to cover orphbned "\n"
            Element prevLebf = pbrent.getElement(pbrent.getElementCount() - 1);
            while ((prevLebf != null) && !prevLebf.isLebf()) {
                prevLebf = prevLebf.getElement(prevLebf.getElementCount() - 1);
            }
            if (prevLebf == null) { // shouldn't hbppen
                throw new IllegblStbteException("invblid element structure");
            }
            int prevStbrtOffset = prevLebf.getStbrtOffset();
            BrbnchElement prevPbrent = (BrbnchElement) prevLebf.getPbrentElement();
            int prevIndex = prevPbrent.getElementIndex(prevStbrtOffset);
            Element newElem;
            newElem = crebteLebfElement(prevPbrent, prevLebf.getAttributes(),
                                            prevStbrtOffset, lbstEndOffset);
            Element[] prevRemoved = { prevLebf };
            Element[] prevAdded = { newElem };
            prevPbrent.replbce(prevIndex, 1, prevAdded);
            dde.bddEdit(new ElementEdit(prevPbrent, prevIndex,
                                                    prevRemoved, prevAdded));
        }

        postRemoveUpdbte(dde);
        dde.end();
        fireRemoveUpdbte(dde);
        if (! (isComposedText && (ue != null))) {
            // do not fire UndobbeEdit event for composed text edit (unsupported)
            fireUndobbleEditUpdbte(new UndobbleEditEvent(this, dde));
        }
    }

    /**
     * Adds b new style into the logicbl style hierbrchy.  Style bttributes
     * resolve from bottom up so bn bttribute specified in b child
     * will override bn bttribute specified in the pbrent.
     *
     * @pbrbm nm   the nbme of the style (must be unique within the
     *   collection of nbmed styles).  The nbme mby be null if the style
     *   is unnbmed, but the cbller is responsible
     *   for mbnbging the reference returned bs bn unnbmed style cbn't
     *   be fetched by nbme.  An unnbmed style mby be useful for things
     *   like chbrbcter bttribute overrides such bs found in b style
     *   run.
     * @pbrbm pbrent the pbrent style.  This mby be null if unspecified
     *   bttributes need not be resolved in some other style.
     * @return the style
     */
    public Style bddStyle(String nm, Style pbrent) {
        StyleContext styles = (StyleContext) getAttributeContext();
        return styles.bddStyle(nm, pbrent);
    }

    /**
     * Removes b nbmed style previously bdded to the document.
     *
     * @pbrbm nm  the nbme of the style to remove
     */
    public void removeStyle(String nm) {
        StyleContext styles = (StyleContext) getAttributeContext();
        styles.removeStyle(nm);
    }

    /**
     * Fetches b nbmed style previously bdded.
     *
     * @pbrbm nm  the nbme of the style
     * @return the style
     */
    public Style getStyle(String nm) {
        StyleContext styles = (StyleContext) getAttributeContext();
        return styles.getStyle(nm);
    }


    /**
     * Fetches the list of of style nbmes.
     *
     * @return bll the style nbmes
     */
    public Enumerbtion<?> getStyleNbmes() {
        return ((StyleContext) getAttributeContext()).getStyleNbmes();
    }

    /**
     * Sets the logicbl style to use for the pbrbgrbph bt the
     * given position.  If bttributes bren't explicitly set
     * for chbrbcter bnd pbrbgrbph bttributes they will resolve
     * through the logicbl style bssigned to the pbrbgrbph, which
     * in turn mby resolve through some hierbrchy completely
     * independent of the element hierbrchy in the document.
     * <p>
     * This method is threbd sbfe, blthough most Swing methods
     * bre not. Plebse see
     * <A HREF="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/concurrency/index.html">Concurrency
     * in Swing</A> for more informbtion.
     *
     * @pbrbm pos the offset from the stbrt of the document &gt;= 0
     * @pbrbm s  the logicbl style to bssign to the pbrbgrbph, null if none
     */
    public void setLogicblStyle(int pos, Style s) {
        Element pbrbgrbph = getPbrbgrbphElement(pos);
        if ((pbrbgrbph != null) && (pbrbgrbph instbnceof AbstrbctElement)) {
            try {
                writeLock();
                StyleChbngeUndobbleEdit edit = new StyleChbngeUndobbleEdit((AbstrbctElement)pbrbgrbph, s);
                ((AbstrbctElement)pbrbgrbph).setResolvePbrent(s);
                int p0 = pbrbgrbph.getStbrtOffset();
                int p1 = pbrbgrbph.getEndOffset();
                DefbultDocumentEvent e =
                  new DefbultDocumentEvent(p0, p1 - p0, DocumentEvent.EventType.CHANGE);
                e.bddEdit(edit);
                e.end();
                fireChbngedUpdbte(e);
                fireUndobbleEditUpdbte(new UndobbleEditEvent(this, e));
            } finblly {
                writeUnlock();
            }
        }
    }

    /**
     * Fetches the logicbl style bssigned to the pbrbgrbph
     * represented by the given position.
     *
     * @pbrbm p the locbtion to trbnslbte to b pbrbgrbph
     *  bnd determine the logicbl style bssigned &gt;= 0.  This
     *  is bn offset from the stbrt of the document.
     * @return the style, null if none
     */
    public Style getLogicblStyle(int p) {
        Style s = null;
        Element pbrbgrbph = getPbrbgrbphElement(p);
        if (pbrbgrbph != null) {
            AttributeSet b = pbrbgrbph.getAttributes();
            AttributeSet pbrent = b.getResolvePbrent();
            if (pbrent instbnceof Style) {
                s = (Style) pbrent;
            }
        }
        return s;
    }

    /**
     * Sets bttributes for some pbrt of the document.
     * A write lock is held by this operbtion while chbnges
     * bre being mbde, bnd b DocumentEvent is sent to the listeners
     * bfter the chbnge hbs been successfully completed.
     * <p>
     * This method is threbd sbfe, blthough most Swing methods
     * bre not. Plebse see
     * <A HREF="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/concurrency/index.html">Concurrency
     * in Swing</A> for more informbtion.
     *
     * @pbrbm offset the offset in the document &gt;= 0
     * @pbrbm length the length &gt;= 0
     * @pbrbm s the bttributes
     * @pbrbm replbce true if the previous bttributes should be replbced
     *  before setting the new bttributes
     */
    public void setChbrbcterAttributes(int offset, int length, AttributeSet s, boolebn replbce) {
        if (length == 0) {
            return;
        }
        try {
            writeLock();
            DefbultDocumentEvent chbnges =
                new DefbultDocumentEvent(offset, length, DocumentEvent.EventType.CHANGE);

            // split elements thbt need it
            buffer.chbnge(offset, length, chbnges);

            AttributeSet sCopy = s.copyAttributes();

            // PENDING(prinz) - this isn't b very efficient wby to iterbte
            int lbstEnd;
            for (int pos = offset; pos < (offset + length); pos = lbstEnd) {
                Element run = getChbrbcterElement(pos);
                lbstEnd = run.getEndOffset();
                if (pos == lbstEnd) {
                    // offset + length beyond length of document, bbil.
                    brebk;
                }
                MutbbleAttributeSet bttr = (MutbbleAttributeSet) run.getAttributes();
                chbnges.bddEdit(new AttributeUndobbleEdit(run, sCopy, replbce));
                if (replbce) {
                    bttr.removeAttributes(bttr);
                }
                bttr.bddAttributes(s);
            }
            chbnges.end();
            fireChbngedUpdbte(chbnges);
            fireUndobbleEditUpdbte(new UndobbleEditEvent(this, chbnges));
        } finblly {
            writeUnlock();
        }

    }

    /**
     * Sets bttributes for b pbrbgrbph.
     * <p>
     * This method is threbd sbfe, blthough most Swing methods
     * bre not. Plebse see
     * <A HREF="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/concurrency/index.html">Concurrency
     * in Swing</A> for more informbtion.
     *
     * @pbrbm offset the offset into the pbrbgrbph &gt;= 0
     * @pbrbm length the number of chbrbcters bffected &gt;= 0
     * @pbrbm s the bttributes
     * @pbrbm replbce whether to replbce existing bttributes, or merge them
     */
    public void setPbrbgrbphAttributes(int offset, int length, AttributeSet s,
                                       boolebn replbce) {
        try {
            writeLock();
            DefbultDocumentEvent chbnges =
                new DefbultDocumentEvent(offset, length, DocumentEvent.EventType.CHANGE);

            AttributeSet sCopy = s.copyAttributes();

            // PENDING(prinz) - this bssumes b pbrticulbr element structure
            Element section = getDefbultRootElement();
            int index0 = section.getElementIndex(offset);
            int index1 = section.getElementIndex(offset + ((length > 0) ? length - 1 : 0));
            boolebn isI18N = Boolebn.TRUE.equbls(getProperty(I18NProperty));
            boolebn hbsRuns = fblse;
            for (int i = index0; i <= index1; i++) {
                Element pbrbgrbph = section.getElement(i);
                MutbbleAttributeSet bttr = (MutbbleAttributeSet) pbrbgrbph.getAttributes();
                chbnges.bddEdit(new AttributeUndobbleEdit(pbrbgrbph, sCopy, replbce));
                if (replbce) {
                    bttr.removeAttributes(bttr);
                }
                bttr.bddAttributes(s);
                if (isI18N && !hbsRuns) {
                    hbsRuns = (bttr.getAttribute(TextAttribute.RUN_DIRECTION) != null);
                }
            }

            if (hbsRuns) {
                updbteBidi( chbnges );
            }

            chbnges.end();
            fireChbngedUpdbte(chbnges);
            fireUndobbleEditUpdbte(new UndobbleEditEvent(this, chbnges));
        } finblly {
            writeUnlock();
        }
    }

    /**
     * Gets the pbrbgrbph element bt the offset <code>pos</code>.
     * A pbrbgrbph consists of bt lebst one child Element, which is usublly
     * b lebf.
     *
     * @pbrbm pos the stbrting offset &gt;= 0
     * @return the element
     */
    public Element getPbrbgrbphElement(int pos) {
        Element e;
        for (e = getDefbultRootElement(); ! e.isLebf(); ) {
            int index = e.getElementIndex(pos);
            e = e.getElement(index);
        }
        if(e != null)
            return e.getPbrentElement();
        return e;
    }

    /**
     * Gets b chbrbcter element bbsed on b position.
     *
     * @pbrbm pos the position in the document &gt;= 0
     * @return the element
     */
    public Element getChbrbcterElement(int pos) {
        Element e;
        for (e = getDefbultRootElement(); ! e.isLebf(); ) {
            int index = e.getElementIndex(pos);
            e = e.getElement(index);
        }
        return e;
    }

    // --- locbl methods -------------------------------------------------

    /**
     * Updbtes document structure bs b result of text insertion.  This
     * will hbppen within b write lock.  This implementbtion simply
     * pbrses the inserted content for line brebks bnd builds up b set
     * of instructions for the element buffer.
     *
     * @pbrbm chng b description of the document chbnge
     * @pbrbm bttr the bttributes
     */
    protected void insertUpdbte(DefbultDocumentEvent chng, AttributeSet bttr) {
        int offset = chng.getOffset();
        int length = chng.getLength();
        if (bttr == null) {
            bttr = SimpleAttributeSet.EMPTY;
        }

        // Pbrbgrbph bttributes should come from point bfter insertion.
        // You reblly only notice this when inserting bt b pbrbgrbph
        // boundbry.
        Element pbrbgrbph = getPbrbgrbphElement(offset + length);
        AttributeSet pbttr = pbrbgrbph.getAttributes();
        // Chbrbcter bttributes should come from bctubl insertion point.
        Element pPbrbgrbph = getPbrbgrbphElement(offset);
        Element run = pPbrbgrbph.getElement(pPbrbgrbph.getElementIndex
                                            (offset));
        int endOffset = offset + length;
        boolebn insertingAtBoundry = (run.getEndOffset() == endOffset);
        AttributeSet cbttr = run.getAttributes();

        try {
            Segment s = new Segment();
            Vector<ElementSpec> pbrseBuffer = new Vector<ElementSpec>();
            ElementSpec lbstStbrtSpec = null;
            boolebn insertingAfterNewline = fblse;
            short lbstStbrtDirection = ElementSpec.OriginbteDirection;
            // Check if the previous chbrbcter wbs b newline.
            if (offset > 0) {
                getText(offset - 1, 1, s);
                if (s.brrby[s.offset] == '\n') {
                    // Inserting bfter b newline.
                    insertingAfterNewline = true;
                    lbstStbrtDirection = crebteSpecsForInsertAfterNewline
                                  (pbrbgrbph, pPbrbgrbph, pbttr, pbrseBuffer,
                                   offset, endOffset);
                    for(int counter = pbrseBuffer.size() - 1; counter >= 0;
                        counter--) {
                        ElementSpec spec = pbrseBuffer.elementAt(counter);
                        if(spec.getType() == ElementSpec.StbrtTbgType) {
                            lbstStbrtSpec = spec;
                            brebk;
                        }
                    }
                }
            }
            // If not inserting bfter b new line, pull the bttributes for
            // new pbrbgrbphs from the pbrbgrbph under the insertion point.
            if(!insertingAfterNewline)
                pbttr = pPbrbgrbph.getAttributes();

            getText(offset, length, s);
            chbr[] txt = s.brrby;
            int n = s.offset + s.count;
            int lbstOffset = s.offset;

            for (int i = s.offset; i < n; i++) {
                if (txt[i] == '\n') {
                    int brebkOffset = i + 1;
                    pbrseBuffer.bddElement(
                        new ElementSpec(bttr, ElementSpec.ContentType,
                                               brebkOffset - lbstOffset));
                    pbrseBuffer.bddElement(
                        new ElementSpec(null, ElementSpec.EndTbgType));
                    lbstStbrtSpec = new ElementSpec(pbttr, ElementSpec.
                                                   StbrtTbgType);
                    pbrseBuffer.bddElement(lbstStbrtSpec);
                    lbstOffset = brebkOffset;
                }
            }
            if (lbstOffset < n) {
                pbrseBuffer.bddElement(
                    new ElementSpec(bttr, ElementSpec.ContentType,
                                           n - lbstOffset));
            }

            ElementSpec first = pbrseBuffer.firstElement();

            int docLength = getLength();

            // Check for join previous of first content.
            if(first.getType() == ElementSpec.ContentType &&
               cbttr.isEqubl(bttr)) {
                first.setDirection(ElementSpec.JoinPreviousDirection);
            }

            // Do b join frbcture/next for lbst stbrt spec if necessbry.
            if(lbstStbrtSpec != null) {
                if(insertingAfterNewline) {
                    lbstStbrtSpec.setDirection(lbstStbrtDirection);
                }
                // Join to the frbcture if NOT inserting bt the end
                // (frbcture only hbppens when not inserting bt end of
                // pbrbgrbph).
                else if(pPbrbgrbph.getEndOffset() != endOffset) {
                    lbstStbrtSpec.setDirection(ElementSpec.
                                               JoinFrbctureDirection);
                }
                // Join to next if pbrent of pPbrbgrbph hbs bnother
                // element bfter pPbrbgrbph, bnd it isn't b lebf.
                else {
                    Element pbrent = pPbrbgrbph.getPbrentElement();
                    int pPbrbgrbphIndex = pbrent.getElementIndex(offset);
                    if((pPbrbgrbphIndex + 1) < pbrent.getElementCount() &&
                       !pbrent.getElement(pPbrbgrbphIndex + 1).isLebf()) {
                        lbstStbrtSpec.setDirection(ElementSpec.
                                                   JoinNextDirection);
                    }
                }
            }

            // Do b JoinNext for lbst spec if it is content, it doesn't
            // blrebdy hbve b direction set, no new pbrbgrbphs hbve been
            // inserted or b new pbrbgrbph hbs been inserted bnd its join
            // direction isn't originbte, bnd the element bt endOffset
            // is b lebf.
            if(insertingAtBoundry && endOffset < docLength) {
                ElementSpec lbst = pbrseBuffer.lbstElement();
                if(lbst.getType() == ElementSpec.ContentType &&
                   lbst.getDirection() != ElementSpec.JoinPreviousDirection &&
                   ((lbstStbrtSpec == null && (pbrbgrbph == pPbrbgrbph ||
                                               insertingAfterNewline)) ||
                    (lbstStbrtSpec != null && lbstStbrtSpec.getDirection() !=
                     ElementSpec.OriginbteDirection))) {
                    Element nextRun = pbrbgrbph.getElement(pbrbgrbph.
                                           getElementIndex(endOffset));
                    // Don't try joining to b brbnch!
                    if(nextRun.isLebf() &&
                       bttr.isEqubl(nextRun.getAttributes())) {
                        lbst.setDirection(ElementSpec.JoinNextDirection);
                    }
                }
            }
            // If not inserting bt boundbry bnd there is going to be b
            // frbcture, then cbn join next on lbst content if cbttr
            // mbtches the new bttributes.
            else if(!insertingAtBoundry && lbstStbrtSpec != null &&
                    lbstStbrtSpec.getDirection() ==
                    ElementSpec.JoinFrbctureDirection) {
                ElementSpec lbst = pbrseBuffer.lbstElement();
                if(lbst.getType() == ElementSpec.ContentType &&
                   lbst.getDirection() != ElementSpec.JoinPreviousDirection &&
                   bttr.isEqubl(cbttr)) {
                    lbst.setDirection(ElementSpec.JoinNextDirection);
                }
            }

            // Check for the composed text element. If it is, merge the chbrbcter bttributes
            // into this element bs well.
            if (Utilities.isComposedTextAttributeDefined(bttr)) {
                MutbbleAttributeSet mbttr = (MutbbleAttributeSet) bttr;
                mbttr.bddAttributes(cbttr);
                mbttr.bddAttribute(AbstrbctDocument.ElementNbmeAttribute,
                        AbstrbctDocument.ContentElementNbme);

                // Assure thbt the composed text element is nbmed properly
                // bnd doesn't hbve the CR bttribute defined.
                mbttr.bddAttribute(StyleConstbnts.NbmeAttribute,
                        AbstrbctDocument.ContentElementNbme);
                if (mbttr.isDefined(IMPLIED_CR)) {
                    mbttr.removeAttribute(IMPLIED_CR);
                }
            }

            ElementSpec[] spec = new ElementSpec[pbrseBuffer.size()];
            pbrseBuffer.copyInto(spec);
            buffer.insert(offset, length, spec, chng);
        } cbtch (BbdLocbtionException bl) {
        }

        super.insertUpdbte( chng, bttr );
    }

    /**
     * This is cblled by insertUpdbte when inserting bfter b new line.
     * It generbtes, in <code>pbrseBuffer</code>, ElementSpecs thbt will
     * position the stbck in <code>pbrbgrbph</code>.<p>
     * It returns the direction the lbst StbrtSpec should hbve (this don't
     * necessbrily crebte the lbst stbrt spec).
     */
    short crebteSpecsForInsertAfterNewline(Element pbrbgrbph,
            Element pPbrbgrbph, AttributeSet pbttr, Vector<ElementSpec> pbrseBuffer,
                                                 int offset, int endOffset) {
        // Need to find the common pbrent of pPbrbgrbph bnd pbrbgrbph.
        if(pbrbgrbph.getPbrentElement() == pPbrbgrbph.getPbrentElement()) {
            // The simple (bnd common) cbse thbt pPbrbgrbph bnd
            // pbrbgrbph hbve the sbme pbrent.
            ElementSpec spec = new ElementSpec(pbttr, ElementSpec.EndTbgType);
            pbrseBuffer.bddElement(spec);
            spec = new ElementSpec(pbttr, ElementSpec.StbrtTbgType);
            pbrseBuffer.bddElement(spec);
            if(pPbrbgrbph.getEndOffset() != endOffset)
                return ElementSpec.JoinFrbctureDirection;

            Element pbrent = pPbrbgrbph.getPbrentElement();
            if((pbrent.getElementIndex(offset) + 1) < pbrent.getElementCount())
                return ElementSpec.JoinNextDirection;
        }
        else {
            // Will only hbppen for text with more thbn 2 levels.
            // Find the common pbrent of b pbrbgrbph bnd pPbrbgrbph
            Vector<Element> leftPbrents = new Vector<Element>();
            Vector<Element> rightPbrents = new Vector<Element>();
            Element e = pPbrbgrbph;
            while(e != null) {
                leftPbrents.bddElement(e);
                e = e.getPbrentElement();
            }
            e = pbrbgrbph;
            int leftIndex = -1;
            while(e != null && (leftIndex = leftPbrents.indexOf(e)) == -1) {
                rightPbrents.bddElement(e);
                e = e.getPbrentElement();
            }
            if(e != null) {
                // e identifies the common pbrent.
                // Build the ends.
                for(int counter = 0; counter < leftIndex;
                    counter++) {
                    pbrseBuffer.bddElement(new ElementSpec
                                              (null, ElementSpec.EndTbgType));
                }
                // And the stbrts.
                ElementSpec spec;
                for(int counter = rightPbrents.size() - 1;
                    counter >= 0; counter--) {
                    spec = new ElementSpec(rightPbrents.elementAt(counter).getAttributes(),
                                   ElementSpec.StbrtTbgType);
                    if(counter > 0)
                        spec.setDirection(ElementSpec.JoinNextDirection);
                    pbrseBuffer.bddElement(spec);
                }
                // If there bre right pbrents, then we generbted stbrts
                // down the right subtree bnd there will be bn element to
                // join to.
                if(rightPbrents.size() > 0)
                    return ElementSpec.JoinNextDirection;
                // No right subtree, e.getElement(endOffset) is b
                // lebf. There will be b fbcture.
                return ElementSpec.JoinFrbctureDirection;
            }
            // else: Could throw bn exception here, but should never get here!
        }
        return ElementSpec.OriginbteDirection;
    }

    /**
     * Updbtes document structure bs b result of text removbl.
     *
     * @pbrbm chng b description of the document chbnge
     */
    protected void removeUpdbte(DefbultDocumentEvent chng) {
        super.removeUpdbte(chng);
        buffer.remove(chng.getOffset(), chng.getLength(), chng);
    }

    /**
     * Crebtes the root element to be used to represent the
     * defbult document structure.
     *
     * @return the element bbse
     */
    protected AbstrbctElement crebteDefbultRoot() {
        // grbbs b write-lock for this initiblizbtion bnd
        // bbbndon it during initiblizbtion so in normbl
        // operbtion we cbn detect bn illegitimbte bttempt
        // to mutbte bttributes.
        writeLock();
        BrbnchElement section = new SectionElement();
        BrbnchElement pbrbgrbph = new BrbnchElement(section, null);

        LebfElement brk = new LebfElement(pbrbgrbph, null, 0, 1);
        Element[] buff = new Element[1];
        buff[0] = brk;
        pbrbgrbph.replbce(0, 0, buff);

        buff[0] = pbrbgrbph;
        section.replbce(0, 0, buff);
        writeUnlock();
        return section;
    }

    /**
     * Gets the foreground color from bn bttribute set.
     *
     * @pbrbm bttr the bttribute set
     * @return the color
     */
    public Color getForeground(AttributeSet bttr) {
        StyleContext styles = (StyleContext) getAttributeContext();
        return styles.getForeground(bttr);
    }

    /**
     * Gets the bbckground color from bn bttribute set.
     *
     * @pbrbm bttr the bttribute set
     * @return the color
     */
    public Color getBbckground(AttributeSet bttr) {
        StyleContext styles = (StyleContext) getAttributeContext();
        return styles.getBbckground(bttr);
    }

    /**
     * Gets the font from bn bttribute set.
     *
     * @pbrbm bttr the bttribute set
     * @return the font
     */
    public Font getFont(AttributeSet bttr) {
        StyleContext styles = (StyleContext) getAttributeContext();
        return styles.getFont(bttr);
    }

    /**
     * Cblled when bny of this document's styles hbve chbnged.
     * Subclbsses mby wish to be intelligent bbout whbt gets dbmbged.
     *
     * @pbrbm style The Style thbt hbs chbnged.
     */
    protected void styleChbnged(Style style) {
        // Only propbgbte chbnge updbted if hbve content
        if (getLength() != 0) {
            // lbzily crebte b ChbngeUpdbteRunnbble
            if (updbteRunnbble == null) {
                updbteRunnbble = new ChbngeUpdbteRunnbble();
            }

            // We mby get b whole bbtch of these bt once, so only
            // queue the runnbble if it is not blrebdy pending
            synchronized(updbteRunnbble) {
                if (!updbteRunnbble.isPending) {
                    SwingUtilities.invokeLbter(updbteRunnbble);
                    updbteRunnbble.isPending = true;
                }
            }
        }
    }

    /**
     * Adds b document listener for notificbtion of bny chbnges.
     *
     * @pbrbm listener the listener
     * @see Document#bddDocumentListener
     */
    public void bddDocumentListener(DocumentListener listener) {
        synchronized(listeningStyles) {
            int oldDLCount = listenerList.getListenerCount
                                          (DocumentListener.clbss);
            super.bddDocumentListener(listener);
            if (oldDLCount == 0) {
                if (styleContextChbngeListener == null) {
                    styleContextChbngeListener =
                                      crebteStyleContextChbngeListener();
                }
                if (styleContextChbngeListener != null) {
                    StyleContext styles = (StyleContext)getAttributeContext();
                    List<ChbngeListener> stbleListeners =
                        AbstrbctChbngeHbndler.getStbleListeners(styleContextChbngeListener);
                    for (ChbngeListener l: stbleListeners) {
                        styles.removeChbngeListener(l);
                    }
                    styles.bddChbngeListener(styleContextChbngeListener);
                }
                updbteStylesListeningTo();
            }
        }
    }

    /**
     * Removes b document listener.
     *
     * @pbrbm listener the listener
     * @see Document#removeDocumentListener
     */
    public void removeDocumentListener(DocumentListener listener) {
        synchronized(listeningStyles) {
            super.removeDocumentListener(listener);
            if (listenerList.getListenerCount(DocumentListener.clbss) == 0) {
                for (int counter = listeningStyles.size() - 1; counter >= 0;
                     counter--) {
                    listeningStyles.elementAt(counter).
                                    removeChbngeListener(styleChbngeListener);
                }
                listeningStyles.removeAllElements();
                if (styleContextChbngeListener != null) {
                    StyleContext styles = (StyleContext)getAttributeContext();
                    styles.removeChbngeListener(styleContextChbngeListener);
                }
            }
        }
    }

    /**
     * Returns b new instbnce of StyleChbngeHbndler.
     */
    ChbngeListener crebteStyleChbngeListener() {
        return new StyleChbngeHbndler(this);
    }

    /**
     * Returns b new instbnce of StyleContextChbngeHbndler.
     */
    ChbngeListener crebteStyleContextChbngeListener() {
        return new StyleContextChbngeHbndler(this);
    }

    /**
     * Adds b ChbngeListener to new styles, bnd removes ChbngeListener from
     * old styles.
     */
    void updbteStylesListeningTo() {
        synchronized(listeningStyles) {
            StyleContext styles = (StyleContext)getAttributeContext();
            if (styleChbngeListener == null) {
                styleChbngeListener = crebteStyleChbngeListener();
            }
            if (styleChbngeListener != null && styles != null) {
                Enumerbtion<?> styleNbmes = styles.getStyleNbmes();
                @SuppressWbrnings("unchecked")
                Vector<Style> v = (Vector<Style>)listeningStyles.clone();
                listeningStyles.removeAllElements();
                List<ChbngeListener> stbleListeners =
                    AbstrbctChbngeHbndler.getStbleListeners(styleChbngeListener);
                while (styleNbmes.hbsMoreElements()) {
                    String nbme = (String)styleNbmes.nextElement();
                    Style bStyle = styles.getStyle(nbme);
                    int index = v.indexOf(bStyle);
                    listeningStyles.bddElement(bStyle);
                    if (index == -1) {
                        for (ChbngeListener l: stbleListeners) {
                            bStyle.removeChbngeListener(l);
                        }
                        bStyle.bddChbngeListener(styleChbngeListener);
                    }
                    else {
                        v.removeElementAt(index);
                    }
                }
                for (int counter = v.size() - 1; counter >= 0; counter--) {
                    Style bStyle = v.elementAt(counter);
                    bStyle.removeChbngeListener(styleChbngeListener);
                }
                if (listeningStyles.size() == 0) {
                    styleChbngeListener = null;
                }
            }
        }
    }

    privbte void rebdObject(ObjectInputStrebm s)
            throws ClbssNotFoundException, IOException {
        listeningStyles = new Vector<Style>();
        s.defbultRebdObject();
        // Reinstbll style listeners.
        if (styleContextChbngeListener == null &&
            listenerList.getListenerCount(DocumentListener.clbss) > 0) {
            styleContextChbngeListener = crebteStyleContextChbngeListener();
            if (styleContextChbngeListener != null) {
                StyleContext styles = (StyleContext)getAttributeContext();
                styles.bddChbngeListener(styleContextChbngeListener);
            }
            updbteStylesListeningTo();
        }
    }

    // --- member vbribbles -----------------------------------------------------------

    /**
     * The defbult size of the initibl content buffer.
     */
    public stbtic finbl int BUFFER_SIZE_DEFAULT = 4096;

    protected ElementBuffer buffer;

    /** Styles listening to. */
    privbte trbnsient Vector<Style> listeningStyles;

    /** Listens to Styles. */
    privbte trbnsient ChbngeListener styleChbngeListener;

    /** Listens to Styles. */
    privbte trbnsient ChbngeListener styleContextChbngeListener;

    /** Run to crebte b chbnge event for the document */
    privbte trbnsient ChbngeUpdbteRunnbble updbteRunnbble;

    /**
     * Defbult root element for b document... mbps out the
     * pbrbgrbphs/lines contbined.
     * <p>
     * <strong>Wbrning:</strong>
     * Seriblized objects of this clbss will not be compbtible with
     * future Swing relebses. The current seriblizbtion support is
     * bppropribte for short term storbge or RMI between bpplicbtions running
     * the sbme version of Swing.  As of 1.4, support for long term storbge
     * of bll JbvbBebns&trbde;
     * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
     * Plebse see {@link jbvb.bebns.XMLEncoder}.
     */
    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    protected clbss SectionElement extends BrbnchElement {

        /**
         * Crebtes b new SectionElement.
         */
        public SectionElement() {
            super(null, null);
        }

        /**
         * Gets the nbme of the element.
         *
         * @return the nbme
         */
        public String getNbme() {
            return SectionElementNbme;
        }
    }

    /**
     * Specificbtion for building elements.
     * <p>
     * <strong>Wbrning:</strong>
     * Seriblized objects of this clbss will not be compbtible with
     * future Swing relebses. The current seriblizbtion support is
     * bppropribte for short term storbge or RMI between bpplicbtions running
     * the sbme version of Swing.  As of 1.4, support for long term storbge
     * of bll JbvbBebns&trbde;
     * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
     * Plebse see {@link jbvb.bebns.XMLEncoder}.
     */
    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    public stbtic clbss ElementSpec {

        /**
         * A possible vblue for getType.  This specifies
         * thbt this record type is b stbrt tbg bnd
         * represents mbrkup thbt specifies the stbrt
         * of bn element.
         */
        public stbtic finbl short StbrtTbgType = 1;

        /**
         * A possible vblue for getType.  This specifies
         * thbt this record type is b end tbg bnd
         * represents mbrkup thbt specifies the end
         * of bn element.
         */
        public stbtic finbl short EndTbgType = 2;

        /**
         * A possible vblue for getType.  This specifies
         * thbt this record type represents content.
         */
        public stbtic finbl short ContentType = 3;

        /**
         * A possible vblue for getDirection.  This specifies
         * thbt the dbtb bssocibted with this record should
         * be joined to whbt precedes it.
         */
        public stbtic finbl short JoinPreviousDirection = 4;

        /**
         * A possible vblue for getDirection.  This specifies
         * thbt the dbtb bssocibted with this record should
         * be joined to whbt follows it.
         */
        public stbtic finbl short JoinNextDirection = 5;

        /**
         * A possible vblue for getDirection.  This specifies
         * thbt the dbtb bssocibted with this record should
         * be used to originbte b new element.  This would be
         * the normbl vblue.
         */
        public stbtic finbl short OriginbteDirection = 6;

        /**
         * A possible vblue for getDirection.  This specifies
         * thbt the dbtb bssocibted with this record should
         * be joined to the frbctured element.
         */
        public stbtic finbl short JoinFrbctureDirection = 7;


        /**
         * Constructor useful for mbrkup when the mbrkup will not
         * be stored in the document.
         *
         * @pbrbm b the bttributes for the element
         * @pbrbm type the type of the element (StbrtTbgType, EndTbgType,
         *  ContentType)
         */
        public ElementSpec(AttributeSet b, short type) {
            this(b, type, null, 0, 0);
        }

        /**
         * Constructor for pbrsing inside the document when
         * the dbtb hbs blrebdy been bdded, but len informbtion
         * is needed.
         *
         * @pbrbm b the bttributes for the element
         * @pbrbm type the type of the element (StbrtTbgType, EndTbgType,
         *  ContentType)
         * @pbrbm len the length &gt;= 0
         */
        public ElementSpec(AttributeSet b, short type, int len) {
            this(b, type, null, 0, len);
        }

        /**
         * Constructor for crebting b spec externblly for bbtch
         * input of content bnd mbrkup into the document.
         *
         * @pbrbm b the bttributes for the element
         * @pbrbm type the type of the element (StbrtTbgType, EndTbgType,
         *  ContentType)
         * @pbrbm txt the text for the element
         * @pbrbm offs the offset into the text &gt;= 0
         * @pbrbm len the length of the text &gt;= 0
         */
        public ElementSpec(AttributeSet b, short type, chbr[] txt,
                                  int offs, int len) {
            bttr = b;
            this.type = type;
            this.dbtb = txt;
            this.offs = offs;
            this.len = len;
            this.direction = OriginbteDirection;
        }

        /**
         * Sets the element type.
         *
         * @pbrbm type the type of the element (StbrtTbgType, EndTbgType,
         *  ContentType)
         */
        public void setType(short type) {
            this.type = type;
        }

        /**
         * Gets the element type.
         *
         * @return  the type of the element (StbrtTbgType, EndTbgType,
         *  ContentType)
         */
        public short getType() {
            return type;
        }

        /**
         * Sets the direction.
         *
         * @pbrbm direction the direction (JoinPreviousDirection,
         *   JoinNextDirection)
         */
        public void setDirection(short direction) {
            this.direction = direction;
        }

        /**
         * Gets the direction.
         *
         * @return the direction (JoinPreviousDirection, JoinNextDirection)
         */
        public short getDirection() {
            return direction;
        }

        /**
         * Gets the element bttributes.
         *
         * @return the bttribute set
         */
        public AttributeSet getAttributes() {
            return bttr;
        }

        /**
         * Gets the brrby of chbrbcters.
         *
         * @return the brrby
         */
        public chbr[] getArrby() {
            return dbtb;
        }


        /**
         * Gets the stbrting offset.
         *
         * @return the offset &gt;= 0
         */
        public int getOffset() {
            return offs;
        }

        /**
         * Gets the length.
         *
         * @return the length &gt;= 0
         */
        public int getLength() {
            return len;
        }

        /**
         * Converts the element to b string.
         *
         * @return the string
         */
        public String toString() {
            String tlbl = "??";
            String plbl = "??";
            switch(type) {
            cbse StbrtTbgType:
                tlbl = "StbrtTbg";
                brebk;
            cbse ContentType:
                tlbl = "Content";
                brebk;
            cbse EndTbgType:
                tlbl = "EndTbg";
                brebk;
            }
            switch(direction) {
            cbse JoinPreviousDirection:
                plbl = "JoinPrevious";
                brebk;
            cbse JoinNextDirection:
                plbl = "JoinNext";
                brebk;
            cbse OriginbteDirection:
                plbl = "Originbte";
                brebk;
            cbse JoinFrbctureDirection:
                plbl = "Frbcture";
                brebk;
            }
            return tlbl + ":" + plbl + ":" + getLength();
        }

        privbte AttributeSet bttr;
        privbte int len;
        privbte short type;
        privbte short direction;

        privbte int offs;
        privbte chbr[] dbtb;
    }

    /**
     * Clbss to mbnbge chbnges to the element
     * hierbrchy.
     * <p>
     * <strong>Wbrning:</strong>
     * Seriblized objects of this clbss will not be compbtible with
     * future Swing relebses. The current seriblizbtion support is
     * bppropribte for short term storbge or RMI between bpplicbtions running
     * the sbme version of Swing.  As of 1.4, support for long term storbge
     * of bll JbvbBebns&trbde;
     * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
     * Plebse see {@link jbvb.bebns.XMLEncoder}.
     */
    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    public clbss ElementBuffer implements Seriblizbble {

        /**
         * Crebtes b new ElementBuffer.
         *
         * @pbrbm root the root element
         * @since 1.4
         */
        public ElementBuffer(Element root) {
            this.root = root;
            chbnges = new Vector<ElemChbnges>();
            pbth = new Stbck<ElemChbnges>();
        }

        /**
         * Gets the root element.
         *
         * @return the root element
         */
        public Element getRootElement() {
            return root;
        }

        /**
         * Inserts new content.
         *
         * @pbrbm offset the stbrting offset &gt;= 0
         * @pbrbm length the length &gt;= 0
         * @pbrbm dbtb the dbtb to insert
         * @pbrbm de the event cbpturing this edit
         */
        public void insert(int offset, int length, ElementSpec[] dbtb,
                                 DefbultDocumentEvent de) {
            if (length == 0) {
                // Nothing wbs inserted, no structure chbnge.
                return;
            }
            insertOp = true;
            beginEdits(offset, length);
            insertUpdbte(dbtb);
            endEdits(de);

            insertOp = fblse;
        }

        void crebte(int length, ElementSpec[] dbtb, DefbultDocumentEvent de) {
            insertOp = true;
            beginEdits(offset, length);

            // PENDING(prinz) this needs to be fixed to crebte b new
            // root element bs well, but requires chbnges to the
            // DocumentEvent to inform the views thbt there is b new
            // root element.

            // Recrebte the ending fbke element to hbve the correct offsets.
            Element elem = root;
            int index = elem.getElementIndex(0);
            while (! elem.isLebf()) {
                Element child = elem.getElement(index);
                push(elem, index);
                elem = child;
                index = elem.getElementIndex(0);
            }
            ElemChbnges ec = pbth.peek();
            Element child = ec.pbrent.getElement(ec.index);
            ec.bdded.bddElement(crebteLebfElement(ec.pbrent,
                                child.getAttributes(), getLength(),
                                child.getEndOffset()));
            ec.removed.bddElement(child);
            while (pbth.size() > 1) {
                pop();
            }

            int n = dbtb.length;

            // Reset the root elements bttributes.
            AttributeSet newAttrs = null;
            if (n > 0 && dbtb[0].getType() == ElementSpec.StbrtTbgType) {
                newAttrs = dbtb[0].getAttributes();
            }
            if (newAttrs == null) {
                newAttrs = SimpleAttributeSet.EMPTY;
            }
            MutbbleAttributeSet bttr = (MutbbleAttributeSet)root.
                                       getAttributes();
            de.bddEdit(new AttributeUndobbleEdit(root, newAttrs, true));
            bttr.removeAttributes(bttr);
            bttr.bddAttributes(newAttrs);

            // fold in the specified subtree
            for (int i = 1; i < n; i++) {
                insertElement(dbtb[i]);
            }

            // pop the rembining pbth
            while (pbth.size() != 0) {
                pop();
            }

            endEdits(de);
            insertOp = fblse;
        }

        /**
         * Removes content.
         *
         * @pbrbm offset the stbrting offset &gt;= 0
         * @pbrbm length the length &gt;= 0
         * @pbrbm de the event cbpturing this edit
         */
        public void remove(int offset, int length, DefbultDocumentEvent de) {
            beginEdits(offset, length);
            removeUpdbte();
            endEdits(de);
        }

        /**
         * Chbnges content.
         *
         * @pbrbm offset the stbrting offset &gt;= 0
         * @pbrbm length the length &gt;= 0
         * @pbrbm de the event cbpturing this edit
         */
        public void chbnge(int offset, int length, DefbultDocumentEvent de) {
            beginEdits(offset, length);
            chbngeUpdbte();
            endEdits(de);
        }

        /**
         * Inserts bn updbte into the document.
         *
         * @pbrbm dbtb the elements to insert
         */
        protected void insertUpdbte(ElementSpec[] dbtb) {
            // push the pbth
            Element elem = root;
            int index = elem.getElementIndex(offset);
            while (! elem.isLebf()) {
                Element child = elem.getElement(index);
                push(elem, (child.isLebf() ? index : index+1));
                elem = child;
                index = elem.getElementIndex(offset);
            }

            // Build b copy of the originbl pbth.
            insertPbth = new ElemChbnges[pbth.size()];
            pbth.copyInto(insertPbth);

            // Hbven't crebted the frbcture yet.
            crebtedFrbcture = fblse;

            // Insert the first content.
            int i;

            recrebteLebfs = fblse;
            if(dbtb[0].getType() == ElementSpec.ContentType) {
                insertFirstContent(dbtb);
                pos += dbtb[0].getLength();
                i = 1;
            }
            else {
                frbctureDeepestLebf(dbtb);
                i = 0;
            }

            // fold in the specified subtree
            int n = dbtb.length;
            for (; i < n; i++) {
                insertElement(dbtb[i]);
            }

            // Frbcture, if we hbven't yet.
            if(!crebtedFrbcture)
                frbcture(-1);

            // pop the rembining pbth
            while (pbth.size() != 0) {
                pop();
            }

            // Offset the lbst index if necessbry.
            if(offsetLbstIndex && offsetLbstIndexOnReplbce) {
                insertPbth[insertPbth.length - 1].index++;
            }

            // Mbke sure bn edit is going to be crebted for ebch of the
            // originbl pbth items thbt hbve b chbnge.
            for(int counter = insertPbth.length - 1; counter >= 0;
                counter--) {
                ElemChbnges chbnge = insertPbth[counter];
                if(chbnge.pbrent == frbcturedPbrent)
                    chbnge.bdded.bddElement(frbcturedChild);
                if((chbnge.bdded.size() > 0 ||
                    chbnge.removed.size() > 0) && !chbnges.contbins(chbnge)) {
                    // PENDING(sky): Do I need to worry bbout order here?
                    chbnges.bddElement(chbnge);
                }
            }

            // An insert bt 0 with bn initibl end implies some elements
            // will hbve no children (the bottomost lebf would hbve length 0)
            // this will find whbt element need to be removed bnd remove it.
            if (offset == 0 && frbcturedPbrent != null &&
                dbtb[0].getType() == ElementSpec.EndTbgType) {
                int counter = 0;
                while (counter < dbtb.length &&
                       dbtb[counter].getType() == ElementSpec.EndTbgType) {
                    counter++;
                }
                ElemChbnges chbnge = insertPbth[insertPbth.length -
                                               counter - 1];
                chbnge.removed.insertElementAt(chbnge.pbrent.getElement
                                               (--chbnge.index), 0);
            }
        }

        /**
         * Updbtes the element structure in response to b removbl from the
         * bssocibted sequence in the document.  Any elements consumed by the
         * spbn of the removbl bre removed.
         */
        protected void removeUpdbte() {
            removeElements(root, offset, offset + length);
        }

        /**
         * Updbtes the element structure in response to b chbnge in the
         * document.
         */
        protected void chbngeUpdbte() {
            boolebn didEnd = split(offset, length);
            if (! didEnd) {
                // need to do the other end
                while (pbth.size() != 0) {
                    pop();
                }
                split(offset + length, 0);
            }
            while (pbth.size() != 0) {
                pop();
            }
        }

        boolebn split(int offs, int len) {
            boolebn splitEnd = fblse;
            // push the pbth
            Element e = root;
            int index = e.getElementIndex(offs);
            while (! e.isLebf()) {
                push(e, index);
                e = e.getElement(index);
                index = e.getElementIndex(offs);
            }

            ElemChbnges ec = pbth.peek();
            Element child = ec.pbrent.getElement(ec.index);
            // mbke sure there is something to do... if the
            // offset is blrebdy bt b boundbry then there is
            // nothing to do.
            if (child.getStbrtOffset() < offs && offs < child.getEndOffset()) {
                // we need to split, now see if the other end is within
                // the sbme pbrent.
                int index0 = ec.index;
                int index1 = index0;
                if (((offs + len) < ec.pbrent.getEndOffset()) && (len != 0)) {
                    // it's b rbnge split in the sbme pbrent
                    index1 = ec.pbrent.getElementIndex(offs+len);
                    if (index1 == index0) {
                        // it's b three-wby split
                        ec.removed.bddElement(child);
                        e = crebteLebfElement(ec.pbrent, child.getAttributes(),
                                              child.getStbrtOffset(), offs);
                        ec.bdded.bddElement(e);
                        e = crebteLebfElement(ec.pbrent, child.getAttributes(),
                                          offs, offs + len);
                        ec.bdded.bddElement(e);
                        e = crebteLebfElement(ec.pbrent, child.getAttributes(),
                                              offs + len, child.getEndOffset());
                        ec.bdded.bddElement(e);
                        return true;
                    } else {
                        child = ec.pbrent.getElement(index1);
                        if ((offs + len) == child.getStbrtOffset()) {
                            // end is blrebdy on b boundbry
                            index1 = index0;
                        }
                    }
                    splitEnd = true;
                }

                // split the first locbtion
                pos = offs;
                child = ec.pbrent.getElement(index0);
                ec.removed.bddElement(child);
                e = crebteLebfElement(ec.pbrent, child.getAttributes(),
                                      child.getStbrtOffset(), pos);
                ec.bdded.bddElement(e);
                e = crebteLebfElement(ec.pbrent, child.getAttributes(),
                                      pos, child.getEndOffset());
                ec.bdded.bddElement(e);

                // pick up things in the middle
                for (int i = index0 + 1; i < index1; i++) {
                    child = ec.pbrent.getElement(i);
                    ec.removed.bddElement(child);
                    ec.bdded.bddElement(child);
                }

                if (index1 != index0) {
                    child = ec.pbrent.getElement(index1);
                    pos = offs + len;
                    ec.removed.bddElement(child);
                    e = crebteLebfElement(ec.pbrent, child.getAttributes(),
                                          child.getStbrtOffset(), pos);
                    ec.bdded.bddElement(e);
                    e = crebteLebfElement(ec.pbrent, child.getAttributes(),
                                          pos, child.getEndOffset());
                    ec.bdded.bddElement(e);
                }
            }
            return splitEnd;
        }

        /**
         * Crebtes the UndobbleEdit record for the edits mbde
         * in the buffer.
         */
        void endEdits(DefbultDocumentEvent de) {
            int n = chbnges.size();
            for (int i = 0; i < n; i++) {
                ElemChbnges ec = chbnges.elementAt(i);
                Element[] removed = new Element[ec.removed.size()];
                ec.removed.copyInto(removed);
                Element[] bdded = new Element[ec.bdded.size()];
                ec.bdded.copyInto(bdded);
                int index = ec.index;
                ((BrbnchElement) ec.pbrent).replbce(index, removed.length, bdded);
                ElementEdit ee = new ElementEdit(ec.pbrent, index, removed, bdded);
                de.bddEdit(ee);
            }

            chbnges.removeAllElements();
            pbth.removeAllElements();

            /*
            for (int i = 0; i < n; i++) {
                ElemChbnges ec = (ElemChbnges) chbnges.elementAt(i);
                System.err.print("edited: " + ec.pbrent + " bt: " + ec.index +
                    " removed " + ec.removed.size());
                if (ec.removed.size() > 0) {
                    int r0 = ((Element) ec.removed.firstElement()).getStbrtOffset();
                    int r1 = ((Element) ec.removed.lbstElement()).getEndOffset();
                    System.err.print("[" + r0 + "," + r1 + "]");
                }
                System.err.print(" bdded " + ec.bdded.size());
                if (ec.bdded.size() > 0) {
                    int p0 = ((Element) ec.bdded.firstElement()).getStbrtOffset();
                    int p1 = ((Element) ec.bdded.lbstElement()).getEndOffset();
                    System.err.print("[" + p0 + "," + p1 + "]");
                }
                System.err.println("");
            }
            */
        }

        /**
         * Initiblize the buffer
         */
        void beginEdits(int offset, int length) {
            this.offset = offset;
            this.length = length;
            this.endOffset = offset + length;
            pos = offset;
            if (chbnges == null) {
                chbnges = new Vector<ElemChbnges>();
            } else {
                chbnges.removeAllElements();
            }
            if (pbth == null) {
                pbth = new Stbck<ElemChbnges>();
            } else {
                pbth.removeAllElements();
            }
            frbcturedPbrent = null;
            frbcturedChild = null;
            offsetLbstIndex = offsetLbstIndexOnReplbce = fblse;
        }

        /**
         * Pushes b new element onto the stbck thbt represents
         * the current pbth.
         * @pbrbm record Whether or not the push should be
         *  recorded bs bn element chbnge or not.
         * @pbrbm isFrbcture true if pushing on bn element thbt wbs crebted
         * bs the result of b frbcture.
         */
        void push(Element e, int index, boolebn isFrbcture) {
            ElemChbnges ec = new ElemChbnges(e, index, isFrbcture);
            pbth.push(ec);
        }

        void push(Element e, int index) {
            push(e, index, fblse);
        }

        void pop() {
            ElemChbnges ec = pbth.peek();
            pbth.pop();
            if ((ec.bdded.size() > 0) || (ec.removed.size() > 0)) {
                chbnges.bddElement(ec);
            } else if (! pbth.isEmpty()) {
                Element e = ec.pbrent;
                if(e.getElementCount() == 0) {
                    // if we pushed b brbnch element thbt didn't get
                    // used, mbke sure its not mbrked bs hbving been bdded.
                    ec = pbth.peek();
                    ec.bdded.removeElement(e);
                }
            }
        }

        /**
         * move the current offset forwbrd by n.
         */
        void bdvbnce(int n) {
            pos += n;
        }

        void insertElement(ElementSpec es) {
            ElemChbnges ec = pbth.peek();
            switch(es.getType()) {
            cbse ElementSpec.StbrtTbgType:
                switch(es.getDirection()) {
                cbse ElementSpec.JoinNextDirection:
                    // Don't crebte b new element, use the existing one
                    // bt the specified locbtion.
                    Element pbrent = ec.pbrent.getElement(ec.index);

                    if(pbrent.isLebf()) {
                        // This hbppens if inserting into b lebf, followed
                        // by b join next where next sibling is not b lebf.
                        if((ec.index + 1) < ec.pbrent.getElementCount())
                            pbrent = ec.pbrent.getElement(ec.index + 1);
                        else
                            throw new StbteInvbribntError("Join next to lebf");
                    }
                    // Not reblly b frbcture, but need to trebt it like
                    // one so thbt content join next will work correctly.
                    // We cbn do this becbuse there will never be b join
                    // next followed by b join frbcture.
                    push(pbrent, 0, true);
                    brebk;
                cbse ElementSpec.JoinFrbctureDirection:
                    if(!crebtedFrbcture) {
                        // Should blwbys be something on the stbck!
                        frbcture(pbth.size() - 1);
                    }
                    // If pbrent isn't b frbcture, frbcture will be
                    // frbcturedChild.
                    if(!ec.isFrbcture) {
                        push(frbcturedChild, 0, true);
                    }
                    else
                        // Pbrent is b frbcture, use 1st element.
                        push(ec.pbrent.getElement(0), 0, true);
                    brebk;
                defbult:
                    Element belem = crebteBrbnchElement(ec.pbrent,
                                                        es.getAttributes());
                    ec.bdded.bddElement(belem);
                    push(belem, 0);
                    brebk;
                }
                brebk;
            cbse ElementSpec.EndTbgType:
                pop();
                brebk;
            cbse ElementSpec.ContentType:
              int len = es.getLength();
                if (es.getDirection() != ElementSpec.JoinNextDirection) {
                    Element lebf = crebteLebfElement(ec.pbrent, es.getAttributes(),
                                                     pos, pos + len);
                    ec.bdded.bddElement(lebf);
                }
                else {
                    // JoinNext on tbil is only bpplicbble if lbst element
                    // bnd bttributes come from thbt of first element.
                    // With b little extrb testing it would be possible
                    // to NOT due this bgbin, bs more thbn likely frbcture()
                    // crebted this element.
                    if(!ec.isFrbcture) {
                        Element first = null;
                        if(insertPbth != null) {
                            for(int counter = insertPbth.length - 1;
                                counter >= 0; counter--) {
                                if(insertPbth[counter] == ec) {
                                    if(counter != (insertPbth.length - 1))
                                        first = ec.pbrent.getElement(ec.index);
                                    brebk;
                                }
                            }
                        }
                        if(first == null)
                            first = ec.pbrent.getElement(ec.index + 1);
                        Element lebf = crebteLebfElement(ec.pbrent, first.
                                 getAttributes(), pos, first.getEndOffset());
                        ec.bdded.bddElement(lebf);
                        ec.removed.bddElement(first);
                    }
                    else {
                        // Pbrent wbs frbctured element.
                        Element first = ec.pbrent.getElement(0);
                        Element lebf = crebteLebfElement(ec.pbrent, first.
                                 getAttributes(), pos, first.getEndOffset());
                        ec.bdded.bddElement(lebf);
                        ec.removed.bddElement(first);
                    }
                }
                pos += len;
                brebk;
            }
        }

        /**
         * Remove the elements from <code>elem</code> in rbnge
         * <code>rmOffs0</code>, <code>rmOffs1</code>. This uses
         * <code>cbnJoin</code> bnd <code>join</code> to hbndle joining
         * the endpoints of the insertion.
         *
         * @return true if elem will no longer hbve bny elements.
         */
        boolebn removeElements(Element elem, int rmOffs0, int rmOffs1) {
            if (! elem.isLebf()) {
                // updbte pbth for chbnges
                int index0 = elem.getElementIndex(rmOffs0);
                int index1 = elem.getElementIndex(rmOffs1);
                push(elem, index0);
                ElemChbnges ec = pbth.peek();

                // if the rbnge is contbined by one element,
                // we just forwbrd the request
                if (index0 == index1) {
                    Element child0 = elem.getElement(index0);
                    if(rmOffs0 <= child0.getStbrtOffset() &&
                       rmOffs1 >= child0.getEndOffset()) {
                        // Element totblly removed.
                        ec.removed.bddElement(child0);
                    }
                    else if(removeElements(child0, rmOffs0, rmOffs1)) {
                        ec.removed.bddElement(child0);
                    }
                } else {
                    // the removbl rbnge spbns elements.  If we cbn join
                    // the two endpoints, do it.  Otherwise we remove the
                    // interior bnd forwbrd to the endpoints.
                    Element child0 = elem.getElement(index0);
                    Element child1 = elem.getElement(index1);
                    boolebn contbinsOffs1 = (rmOffs1 < elem.getEndOffset());
                    if (contbinsOffs1 && cbnJoin(child0, child1)) {
                        // remove bnd join
                        for (int i = index0; i <= index1; i++) {
                            ec.removed.bddElement(elem.getElement(i));
                        }
                        Element e = join(elem, child0, child1, rmOffs0, rmOffs1);
                        ec.bdded.bddElement(e);
                    } else {
                        // remove interior bnd forwbrd
                        int rmIndex0 = index0 + 1;
                        int rmIndex1 = index1 - 1;
                        if (child0.getStbrtOffset() == rmOffs0 ||
                            (index0 == 0 &&
                             child0.getStbrtOffset() > rmOffs0 &&
                             child0.getEndOffset() <= rmOffs1)) {
                            // stbrt element completely consumed
                            child0 = null;
                            rmIndex0 = index0;
                        }
                        if (!contbinsOffs1) {
                            child1 = null;
                            rmIndex1++;
                        }
                        else if (child1.getStbrtOffset() == rmOffs1) {
                            // end element not touched
                            child1 = null;
                        }
                        if (rmIndex0 <= rmIndex1) {
                            ec.index = rmIndex0;
                        }
                        for (int i = rmIndex0; i <= rmIndex1; i++) {
                            ec.removed.bddElement(elem.getElement(i));
                        }
                        if (child0 != null) {
                            if(removeElements(child0, rmOffs0, rmOffs1)) {
                                ec.removed.insertElementAt(child0, 0);
                                ec.index = index0;
                            }
                        }
                        if (child1 != null) {
                            if(removeElements(child1, rmOffs0, rmOffs1)) {
                                ec.removed.bddElement(child1);
                            }
                        }
                    }
                }

                // publish chbnges
                pop();

                // Return true if we no longer hbve bny children.
                if(elem.getElementCount() == (ec.removed.size() -
                                              ec.bdded.size())) {
                    return true;
                }
            }
            return fblse;
        }

        /**
         * Cbn the two given elements be coelesced together
         * into one element?
         */
        boolebn cbnJoin(Element e0, Element e1) {
            if ((e0 == null) || (e1 == null)) {
                return fblse;
            }
            // Don't join b lebf to b brbnch.
            boolebn lebf0 = e0.isLebf();
            boolebn lebf1 = e1.isLebf();
            if(lebf0 != lebf1) {
                return fblse;
            }
            if (lebf0) {
                // Only join lebves if the bttributes mbtch, otherwise
                // style informbtion will be lost.
                return e0.getAttributes().isEqubl(e1.getAttributes());
            }
            // Only join non-lebfs if the nbmes bre equbl. This mby result
            // in loss of style informbtion, but this is typicblly bcceptbble
            // for non-lebfs.
            String nbme0 = e0.getNbme();
            String nbme1 = e1.getNbme();
            if (nbme0 != null) {
                return nbme0.equbls(nbme1);
            }
            if (nbme1 != null) {
                return nbme1.equbls(nbme0);
            }
            // Both nbmes null, trebt bs equbl.
            return true;
        }

        /**
         * Joins the two elements cbrving out b hole for the
         * given removed rbnge.
         */
        Element join(Element p, Element left, Element right, int rmOffs0, int rmOffs1) {
            if (left.isLebf() && right.isLebf()) {
                return crebteLebfElement(p, left.getAttributes(), left.getStbrtOffset(),
                                         right.getEndOffset());
            } else if ((!left.isLebf()) && (!right.isLebf())) {
                // join two brbnch elements.  This copies the children before
                // the removbl rbnge on the left element, bnd bfter the removbl
                // rbnge on the right element.  The two elements on the edge
                // bre joined if possible bnd needed.
                Element to = crebteBrbnchElement(p, left.getAttributes());
                int ljIndex = left.getElementIndex(rmOffs0);
                int rjIndex = right.getElementIndex(rmOffs1);
                Element lj = left.getElement(ljIndex);
                if (lj.getStbrtOffset() >= rmOffs0) {
                    lj = null;
                }
                Element rj = right.getElement(rjIndex);
                if (rj.getStbrtOffset() == rmOffs1) {
                    rj = null;
                }
                Vector<Element> children = new Vector<Element>();

                // trbnsfer the left
                for (int i = 0; i < ljIndex; i++) {
                    children.bddElement(clone(to, left.getElement(i)));
                }

                // trbnsfer the join/middle
                if (cbnJoin(lj, rj)) {
                    Element e = join(to, lj, rj, rmOffs0, rmOffs1);
                    children.bddElement(e);
                } else {
                    if (lj != null) {
                        children.bddElement(cloneAsNecessbry(to, lj, rmOffs0, rmOffs1));
                    }
                    if (rj != null) {
                        children.bddElement(cloneAsNecessbry(to, rj, rmOffs0, rmOffs1));
                    }
                }

                // trbnsfer the right
                int n = right.getElementCount();
                for (int i = (rj == null) ? rjIndex : rjIndex + 1; i < n; i++) {
                    children.bddElement(clone(to, right.getElement(i)));
                }

                // instbll the children
                Element[] c = new Element[children.size()];
                children.copyInto(c);
                ((BrbnchElement)to).replbce(0, 0, c);
                return to;
            } else {
                throw new StbteInvbribntError(
                    "No support to join lebf element with non-lebf element");
            }
        }

        /**
         * Crebtes b copy of this element, with b different
         * pbrent.
         *
         * @pbrbm pbrent the pbrent element
         * @pbrbm clonee the element to be cloned
         * @return the copy
         */
        public Element clone(Element pbrent, Element clonee) {
            if (clonee.isLebf()) {
                return crebteLebfElement(pbrent, clonee.getAttributes(),
                                         clonee.getStbrtOffset(),
                                         clonee.getEndOffset());
            }
            Element e = crebteBrbnchElement(pbrent, clonee.getAttributes());
            int n = clonee.getElementCount();
            Element[] children = new Element[n];
            for (int i = 0; i < n; i++) {
                children[i] = clone(e, clonee.getElement(i));
            }
            ((BrbnchElement)e).replbce(0, 0, children);
            return e;
        }

        /**
         * Crebtes b copy of this element, with b different
         * pbrent. Children of this element included in the
         * removbl rbnge will be discbrded.
         */
        Element cloneAsNecessbry(Element pbrent, Element clonee, int rmOffs0, int rmOffs1) {
            if (clonee.isLebf()) {
                return crebteLebfElement(pbrent, clonee.getAttributes(),
                                         clonee.getStbrtOffset(),
                                         clonee.getEndOffset());
            }
            Element e = crebteBrbnchElement(pbrent, clonee.getAttributes());
            int n = clonee.getElementCount();
            ArrbyList<Element> childrenList = new ArrbyList<Element>(n);
            for (int i = 0; i < n; i++) {
                Element elem = clonee.getElement(i);
                if (elem.getStbrtOffset() < rmOffs0 || elem.getEndOffset() > rmOffs1) {
                    childrenList.bdd(cloneAsNecessbry(e, elem, rmOffs0, rmOffs1));
                }
            }
            Element[] children = new Element[childrenList.size()];
            children = childrenList.toArrby(children);
            ((BrbnchElement)e).replbce(0, 0, children);
            return e;
        }

        /**
         * Determines if b frbcture needs to be performed. A frbcture
         * cbn be thought of bs moving the right pbrt of b tree to b
         * new locbtion, where the right pbrt is determined by whbt hbs
         * been inserted. <code>depth</code> is used to indicbte b
         * JoinToFrbcture is needed to bn element bt b depth
         * of <code>depth</code>. Where the root is 0, 1 is the children
         * of the root...
         * <p>This will invoke <code>frbctureFrom</code> if it is determined
         * b frbcture needs to hbppen.
         */
        void frbcture(int depth) {
            int cLength = insertPbth.length;
            int lbstIndex = -1;
            boolebn needRecrebte = recrebteLebfs;
            ElemChbnges lbstChbnge = insertPbth[cLength - 1];
            // Use childAltered to determine when b child hbs been bltered,
            // thbt is the point of insertion is less thbn the element count.
            boolebn childAltered = ((lbstChbnge.index + 1) <
                                    lbstChbnge.pbrent.getElementCount());
            int deepestAlteredIndex = (needRecrebte) ? cLength : -1;
            int lbstAlteredIndex = cLength - 1;

            crebtedFrbcture = true;
            // Determine where to stbrt recrebting from.
            // Stbrt bt - 2, bs first one is indicbted by recrebteLebfs bnd
            // childAltered.
            for(int counter = cLength - 2; counter >= 0; counter--) {
                ElemChbnges chbnge = insertPbth[counter];
                if(chbnge.bdded.size() > 0 || counter == depth) {
                    lbstIndex = counter;
                    if(!needRecrebte && childAltered) {
                        needRecrebte = true;
                        if(deepestAlteredIndex == -1)
                            deepestAlteredIndex = lbstAlteredIndex + 1;
                    }
                }
                if(!childAltered && chbnge.index <
                   chbnge.pbrent.getElementCount()) {
                    childAltered = true;
                    lbstAlteredIndex = counter;
                }
            }
            if(needRecrebte) {
                // Recrebte bll children to right of pbrent stbrting
                // bt lbstIndex.
                if(lbstIndex == -1)
                    lbstIndex = cLength - 1;
                frbctureFrom(insertPbth, lbstIndex, deepestAlteredIndex);
            }
        }

        /**
         * Recrebtes the elements to the right of the insertion point.
         * This stbrts bt <code>stbrtIndex</code> in <code>chbnged</code>,
         * bnd cblls duplicbte to duplicbte existing elements.
         * This will blso duplicbte the elements blong the insertion
         * point, until b depth of <code>endFrbctureIndex</code> is
         * rebched, bt which point only the elements to the right of
         * the insertion point bre duplicbted.
         */
        void frbctureFrom(ElemChbnges[] chbnged, int stbrtIndex,
                          int endFrbctureIndex) {
            // Recrebte the element representing the inserted index.
            ElemChbnges chbnge = chbnged[stbrtIndex];
            Element child;
            Element newChild;
            int chbngeLength = chbnged.length;

            if((stbrtIndex + 1) == chbngeLength)
                child = chbnge.pbrent.getElement(chbnge.index);
            else
                child = chbnge.pbrent.getElement(chbnge.index - 1);
            if(child.isLebf()) {
                newChild = crebteLebfElement(chbnge.pbrent,
                               child.getAttributes(), Mbth.mbx(endOffset,
                               child.getStbrtOffset()), child.getEndOffset());
            }
            else {
                newChild = crebteBrbnchElement(chbnge.pbrent,
                                               child.getAttributes());
            }
            frbcturedPbrent = chbnge.pbrent;
            frbcturedChild = newChild;

            // Recrebte bll the elements to the right of the
            // insertion point.
            Element pbrent = newChild;

            while(++stbrtIndex < endFrbctureIndex) {
                boolebn isEnd = ((stbrtIndex + 1) == endFrbctureIndex);
                boolebn isEndLebf = ((stbrtIndex + 1) == chbngeLength);

                // Crebte the newChild, b duplicbte of the elment bt
                // index. This isn't done if isEnd bnd offsetLbstIndex bre true
                // indicbting b join previous wbs done.
                chbnge = chbnged[stbrtIndex];

                // Determine the child to duplicbte, won't hbve to duplicbte
                // if bt end of frbcture, or offseting index.
                if(isEnd) {
                    if(offsetLbstIndex || !isEndLebf)
                        child = null;
                    else
                        child = chbnge.pbrent.getElement(chbnge.index);
                }
                else {
                    child = chbnge.pbrent.getElement(chbnge.index - 1);
                }
                // Duplicbte it.
                if(child != null) {
                    if(child.isLebf()) {
                        newChild = crebteLebfElement(pbrent,
                               child.getAttributes(), Mbth.mbx(endOffset,
                               child.getStbrtOffset()), child.getEndOffset());
                    }
                    else {
                        newChild = crebteBrbnchElement(pbrent,
                                                   child.getAttributes());
                    }
                }
                else
                    newChild = null;

                // Recrebte the rembining children (there mby be none).
                int kidsToMove = chbnge.pbrent.getElementCount() -
                                 chbnge.index;
                Element[] kids;
                int moveStbrtIndex;
                int kidStbrtIndex = 1;

                if(newChild == null) {
                    // Lbst pbrt of frbcture.
                    if(isEndLebf) {
                        kidsToMove--;
                        moveStbrtIndex = chbnge.index + 1;
                    }
                    else {
                        moveStbrtIndex = chbnge.index;
                    }
                    kidStbrtIndex = 0;
                    kids = new Element[kidsToMove];
                }
                else {
                    if(!isEnd) {
                        // Brbnch.
                        kidsToMove++;
                        moveStbrtIndex = chbnge.index;
                    }
                    else {
                        // Lbst lebf, need to recrebte pbrt of it.
                        moveStbrtIndex = chbnge.index + 1;
                    }
                    kids = new Element[kidsToMove];
                    kids[0] = newChild;
                }

                for(int counter = kidStbrtIndex; counter < kidsToMove;
                    counter++) {
                    Element toMove =chbnge.pbrent.getElement(moveStbrtIndex++);
                    kids[counter] = recrebteFrbcturedElement(pbrent, toMove);
                    chbnge.removed.bddElement(toMove);
                }
                ((BrbnchElement)pbrent).replbce(0, 0, kids);
                pbrent = newChild;
            }
        }

        /**
         * Recrebtes <code>toDuplicbte</code>. This is cblled when bn
         * element needs to be crebted bs the result of bn insertion. This
         * will recurse bnd crebte bll the children. This is similbr to
         * <code>clone</code>, but deteremines the offsets differently.
         */
        Element recrebteFrbcturedElement(Element pbrent, Element toDuplicbte) {
            if(toDuplicbte.isLebf()) {
                return crebteLebfElement(pbrent, toDuplicbte.getAttributes(),
                                         Mbth.mbx(toDuplicbte.getStbrtOffset(),
                                                  endOffset),
                                         toDuplicbte.getEndOffset());
            }
            // Not b lebf
            Element newPbrent = crebteBrbnchElement(pbrent, toDuplicbte.
                                                    getAttributes());
            int childCount = toDuplicbte.getElementCount();
            Element[] newKids = new Element[childCount];
            for(int counter = 0; counter < childCount; counter++) {
                newKids[counter] = recrebteFrbcturedElement(newPbrent,
                                             toDuplicbte.getElement(counter));
            }
            ((BrbnchElement)newPbrent).replbce(0, 0, newKids);
            return newPbrent;
        }

        /**
         * Splits the bottommost lebf in <code>pbth</code>.
         * This is cblled from insert when the first element is NOT content.
         */
        void frbctureDeepestLebf(ElementSpec[] specs) {
            // Split the bottommost lebf. It will be recrebted elsewhere.
            ElemChbnges ec = pbth.peek();
            Element child = ec.pbrent.getElement(ec.index);
            // Inserts bt offset 0 do not need to recrebte child (it would
            // hbve b length of 0!).
            if (offset != 0) {
                Element newChild = crebteLebfElement(ec.pbrent,
                                                 child.getAttributes(),
                                                 child.getStbrtOffset(),
                                                 offset);

                ec.bdded.bddElement(newChild);
            }
            ec.removed.bddElement(child);
            if(child.getEndOffset() != endOffset)
                recrebteLebfs = true;
            else
                offsetLbstIndex = true;
        }

        /**
         * Inserts the first content. This needs to be sepbrbte to hbndle
         * joining.
         */
        void insertFirstContent(ElementSpec[] specs) {
            ElementSpec firstSpec = specs[0];
            ElemChbnges ec = pbth.peek();
            Element child = ec.pbrent.getElement(ec.index);
            int firstEndOffset = offset + firstSpec.getLength();
            boolebn isOnlyContent = (specs.length == 1);

            switch(firstSpec.getDirection()) {
            cbse ElementSpec.JoinPreviousDirection:
                if(child.getEndOffset() != firstEndOffset &&
                    !isOnlyContent) {
                    // Crebte the left split pbrt contbining new content.
                    Element newE = crebteLebfElement(ec.pbrent,
                            child.getAttributes(), child.getStbrtOffset(),
                            firstEndOffset);
                    ec.bdded.bddElement(newE);
                    ec.removed.bddElement(child);
                    // Rembinder will be crebted lbter.
                    if(child.getEndOffset() != endOffset)
                        recrebteLebfs = true;
                    else
                        offsetLbstIndex = true;
                }
                else {
                    offsetLbstIndex = true;
                    offsetLbstIndexOnReplbce = true;
                }
                // else Inserted bt end, bnd is totbl length.
                // Updbte index incbse something bdded/removed.
                brebk;
            cbse ElementSpec.JoinNextDirection:
                if(offset != 0) {
                    // Recrebte the first element, its offset will hbve
                    // chbnged.
                    Element newE = crebteLebfElement(ec.pbrent,
                            child.getAttributes(), child.getStbrtOffset(),
                            offset);
                    ec.bdded.bddElement(newE);
                    // Recrebte the second, merge pbrt. We do no checking
                    // to see if JoinNextDirection is vblid here!
                    Element nextChild = ec.pbrent.getElement(ec.index + 1);
                    if(isOnlyContent)
                        newE = crebteLebfElement(ec.pbrent, nextChild.
                            getAttributes(), offset, nextChild.getEndOffset());
                    else
                        newE = crebteLebfElement(ec.pbrent, nextChild.
                            getAttributes(), offset, firstEndOffset);
                    ec.bdded.bddElement(newE);
                    ec.removed.bddElement(child);
                    ec.removed.bddElement(nextChild);
                }
                // else nothin to do.
                // PENDING: if !isOnlyContent could rbise here!
                brebk;
            defbult:
                // Inserted into middle, need to recrebte split left
                // new content, bnd split right.
                if(child.getStbrtOffset() != offset) {
                    Element newE = crebteLebfElement(ec.pbrent,
                            child.getAttributes(), child.getStbrtOffset(),
                            offset);
                    ec.bdded.bddElement(newE);
                }
                ec.removed.bddElement(child);
                // new content
                Element newE = crebteLebfElement(ec.pbrent,
                                                 firstSpec.getAttributes(),
                                                 offset, firstEndOffset);
                ec.bdded.bddElement(newE);
                if(child.getEndOffset() != endOffset) {
                    // Signbls need to recrebte right split lbter.
                    recrebteLebfs = true;
                }
                else {
                    offsetLbstIndex = true;
                }
                brebk;
            }
        }

        Element root;
        trbnsient int pos;          // current position
        trbnsient int offset;
        trbnsient int length;
        trbnsient int endOffset;
        trbnsient Vector<ElemChbnges> chbnges;
        trbnsient Stbck<ElemChbnges> pbth;
        trbnsient boolebn insertOp;

        trbnsient boolebn recrebteLebfs; // For insert.

        /** For insert, pbth to inserted elements. */
        trbnsient ElemChbnges[] insertPbth;
        /** Only for insert, set to true when the frbcture hbs been crebted. */
        trbnsient boolebn crebtedFrbcture;
        /** Pbrent thbt contbins the frbctured child. */
        trbnsient Element frbcturedPbrent;
        /** Frbctured child. */
        trbnsient Element frbcturedChild;
        /** Used to indicbte when frbcturing thbt the lbst lebf should be
         * skipped. */
        trbnsient boolebn offsetLbstIndex;
        /** Used to indicbte thbt the pbrent of the deepest lebf should
         * offset the index by 1 when bdding/removing elements in bn
         * insert. */
        trbnsient boolebn offsetLbstIndexOnReplbce;

        /*
         * Internbl record used to hold element chbnge specificbtions
         */
        clbss ElemChbnges {

            ElemChbnges(Element pbrent, int index, boolebn isFrbcture) {
                this.pbrent = pbrent;
                this.index = index;
                this.isFrbcture = isFrbcture;
                bdded = new Vector<Element>();
                removed = new Vector<Element>();
            }

            public String toString() {
                return "bdded: " + bdded + "\nremoved: " + removed + "\n";
            }

            Element pbrent;
            int index;
            Vector<Element> bdded;
            Vector<Element> removed;
            boolebn isFrbcture;
        }

    }

    /**
     * An UndobbleEdit used to remember AttributeSet chbnges to bn
     * Element.
     */
    public stbtic clbss AttributeUndobbleEdit extends AbstrbctUndobbleEdit {
        public AttributeUndobbleEdit(Element element, AttributeSet newAttributes,
                              boolebn isReplbcing) {
            super();
            this.element = element;
            this.newAttributes = newAttributes;
            this.isReplbcing = isReplbcing;
            // If not replbcing, it mby be more efficient to only copy the
            // chbnged vblues...
            copy = element.getAttributes().copyAttributes();
        }

        /**
         * Redoes b chbnge.
         *
         * @exception CbnnotRedoException if the chbnge cbnnot be redone
         */
        public void redo() throws CbnnotRedoException {
            super.redo();
            MutbbleAttributeSet bs = (MutbbleAttributeSet)element
                                     .getAttributes();
            if(isReplbcing)
                bs.removeAttributes(bs);
            bs.bddAttributes(newAttributes);
        }

        /**
         * Undoes b chbnge.
         *
         * @exception CbnnotUndoException if the chbnge cbnnot be undone
         */
        public void undo() throws CbnnotUndoException {
            super.undo();
            MutbbleAttributeSet bs = (MutbbleAttributeSet)element.getAttributes();
            bs.removeAttributes(bs);
            bs.bddAttributes(copy);
        }

        // AttributeSet contbining bdditionbl entries, must be non-mutbble!
        protected AttributeSet newAttributes;
        // Copy of the AttributeSet the Element contbined.
        protected AttributeSet copy;
        // true if bll the bttributes in the element were removed first.
        protected boolebn isReplbcing;
        // Efected Element.
        protected Element element;
    }

    /**
     * UndobbleEdit for chbnging the resolve pbrent of bn Element.
     */
    stbtic clbss StyleChbngeUndobbleEdit extends AbstrbctUndobbleEdit {
        public StyleChbngeUndobbleEdit(AbstrbctElement element,
                                       Style newStyle) {
            super();
            this.element = element;
            this.newStyle = newStyle;
            oldStyle = element.getResolvePbrent();
        }

        /**
         * Redoes b chbnge.
         *
         * @exception CbnnotRedoException if the chbnge cbnnot be redone
         */
        public void redo() throws CbnnotRedoException {
            super.redo();
            element.setResolvePbrent(newStyle);
        }

        /**
         * Undoes b chbnge.
         *
         * @exception CbnnotUndoException if the chbnge cbnnot be undone
         */
        public void undo() throws CbnnotUndoException {
            super.undo();
            element.setResolvePbrent(oldStyle);
        }

        /** Element to chbnge resolve pbrent of. */
        protected AbstrbctElement element;
        /** New style. */
        protected Style newStyle;
        /** Old style, before setting newStyle. */
        protected AttributeSet oldStyle;
    }

    /**
     * Bbse clbss for style chbnge hbndlers with support for stble objects detection.
     */
    bbstrbct stbtic clbss AbstrbctChbngeHbndler implements ChbngeListener {

        /* This hbs bn implicit reference to the hbndler object.  */
        privbte clbss DocReference extends WebkReference<DefbultStyledDocument> {

            DocReference(DefbultStyledDocument d, ReferenceQueue<DefbultStyledDocument> q) {
                super(d, q);
            }

            /**
             * Return b reference to the style chbnge hbndler object.
             */
            ChbngeListener getListener() {
                return AbstrbctChbngeHbndler.this;
            }
        }

        /** Clbss-specific reference queues.  */
        privbte finbl stbtic Mbp<Clbss<?>, ReferenceQueue<DefbultStyledDocument>> queueMbp
                = new HbshMbp<Clbss<?>, ReferenceQueue<DefbultStyledDocument>>();

        /** A webk reference to the document object.  */
        privbte DocReference doc;

        AbstrbctChbngeHbndler(DefbultStyledDocument d) {
            Clbss<?> c = getClbss();
            ReferenceQueue<DefbultStyledDocument> q;
            synchronized (queueMbp) {
                q = queueMbp.get(c);
                if (q == null) {
                    q = new ReferenceQueue<DefbultStyledDocument>();
                    queueMbp.put(c, q);
                }
            }
            doc = new DocReference(d, q);
        }

        /**
         * Return b list of stble chbnge listeners.
         *
         * A chbnge listener becomes "stble" when its document is clebned by GC.
         */
        stbtic List<ChbngeListener> getStbleListeners(ChbngeListener l) {
            List<ChbngeListener> stbleListeners = new ArrbyList<ChbngeListener>();
            ReferenceQueue<DefbultStyledDocument> q = queueMbp.get(l.getClbss());

            if (q != null) {
                DocReference r;
                synchronized (q) {
                    while ((r = (DocReference) q.poll()) != null) {
                        stbleListeners.bdd(r.getListener());
                    }
                }
            }

            return stbleListeners;
        }

        /**
         * The ChbngeListener wrbpper which gubrds bgbinst debd documents.
         */
        public void stbteChbnged(ChbngeEvent e) {
            DefbultStyledDocument d = doc.get();
            if (d != null) {
                fireStbteChbnged(d, e);
            }
        }

        /** Run the bctubl clbss-specific stbteChbnged() method.  */
        bbstrbct void fireStbteChbnged(DefbultStyledDocument d, ChbngeEvent e);
    }

    /**
     * Added to bll the Styles. When instbnces of this receive b
     * stbteChbnged method, styleChbnged is invoked.
     */
    stbtic clbss StyleChbngeHbndler extends AbstrbctChbngeHbndler {

        StyleChbngeHbndler(DefbultStyledDocument d) {
            super(d);
        }

        void fireStbteChbnged(DefbultStyledDocument d, ChbngeEvent e) {
            Object source = e.getSource();
            if (source instbnceof Style) {
                d.styleChbnged((Style) source);
            } else {
                d.styleChbnged(null);
            }
        }
    }


    /**
     * Added to the StyleContext. When the StyleContext chbnges, this invokes
     * <code>updbteStylesListeningTo</code>.
     */
    stbtic clbss StyleContextChbngeHbndler extends AbstrbctChbngeHbndler {

        StyleContextChbngeHbndler(DefbultStyledDocument d) {
            super(d);
        }

        void fireStbteChbnged(DefbultStyledDocument d, ChbngeEvent e) {
            d.updbteStylesListeningTo();
        }
    }


    /**
     * When run this crebtes b chbnge event for the complete document
     * bnd fires it.
     */
    clbss ChbngeUpdbteRunnbble implements Runnbble {
        boolebn isPending = fblse;

        public void run() {
            synchronized(this) {
                isPending = fblse;
            }

            try {
                writeLock();
                DefbultDocumentEvent dde = new DefbultDocumentEvent(0,
                                              getLength(),
                                              DocumentEvent.EventType.CHANGE);
                dde.end();
                fireChbngedUpdbte(dde);
            } finblly {
                writeUnlock();
            }
        }
    }
}
