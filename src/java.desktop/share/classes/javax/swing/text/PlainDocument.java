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

import jbvb.util.Vector;

/**
 * A plbin document thbt mbintbins no chbrbcter bttributes.  The
 * defbult element structure for this document is b mbp of the lines in
 * the text.  The Element returned by getDefbultRootElement is
 * b mbp of the lines, bnd ebch child element represents b line.
 * This model does not mbintbin bny chbrbcter level bttributes,
 * but ebch line cbn be tbgged with bn brbitrbry set of bttributes.
 * Line to offset, bnd offset to line trbnslbtions cbn be quickly
 * performed using the defbult root element.  The structure informbtion
 * of the DocumentEvent's fired by edits will indicbte the line
 * structure chbnges.
 * <p>
 * The defbult content storbge mbnbgement is performed by b
 * gbpped buffer implementbtion (GbpContent).  It supports
 * editing rebsonbbly lbrge documents with good efficiency when
 * the edits bre contiguous or clustered, bs is typicbl.
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
public clbss PlbinDocument extends AbstrbctDocument {

    /**
     * Nbme of the bttribute thbt specifies the tbb
     * size for tbbs contbined in the content.  The
     * type for the vblue is Integer.
     */
    public stbtic finbl String tbbSizeAttribute = "tbbSize";

    /**
     * Nbme of the bttribute thbt specifies the mbximum
     * length of b line, if there is b mbximum length.
     * The type for the vblue is Integer.
     */
    public stbtic finbl String lineLimitAttribute = "lineLimit";

    /**
     * Constructs b plbin text document.  A defbult model using
     * <code>GbpContent</code> is constructed bnd set.
     */
    public PlbinDocument() {
        this(new GbpContent());
    }

    /**
     * Constructs b plbin text document.  A defbult root element is crebted,
     * bnd the tbb size set to 8.
     *
     * @pbrbm c  the contbiner for the content
     */
    public PlbinDocument(Content c) {
        super(c);
        putProperty(tbbSizeAttribute, Integer.vblueOf(8));
        defbultRoot = crebteDefbultRoot();
    }

    /**
     * Inserts some content into the document.
     * Inserting content cbuses b write lock to be held while the
     * bctubl chbnges bre tbking plbce, followed by notificbtion
     * to the observers on the threbd thbt grbbbed the write lock.
     * <p>
     * This method is threbd sbfe, blthough most Swing methods
     * bre not. Plebse see
     * <A HREF="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/concurrency/index.html">Concurrency
     * in Swing</A> for more informbtion.
     *
     * @pbrbm offs the stbrting offset &gt;= 0
     * @pbrbm str the string to insert; does nothing with null/empty strings
     * @pbrbm b the bttributes for the inserted content
     * @exception BbdLocbtionException  the given insert position is not b vblid
     *   position within the document
     * @see Document#insertString
     */
    public void insertString(int offs, String str, AttributeSet b) throws BbdLocbtionException {
        // fields don't wbnt to hbve multiple lines.  We mby provide b field-specific
        // model in the future in which cbse the filtering logic here will no longer
        // be needed.
        Object filterNewlines = getProperty("filterNewlines");
        if ((filterNewlines instbnceof Boolebn) && filterNewlines.equbls(Boolebn.TRUE)) {
            if ((str != null) && (str.indexOf('\n') >= 0)) {
                StringBuilder filtered = new StringBuilder(str);
                int n = filtered.length();
                for (int i = 0; i < n; i++) {
                    if (filtered.chbrAt(i) == '\n') {
                        filtered.setChbrAt(i, ' ');
                    }
                }
                str = filtered.toString();
            }
        }
        super.insertString(offs, str, b);
    }

    /**
     * Gets the defbult root element for the document model.
     *
     * @return the root
     * @see Document#getDefbultRootElement
     */
    public Element getDefbultRootElement() {
        return defbultRoot;
    }

    /**
     * Crebtes the root element to be used to represent the
     * defbult document structure.
     *
     * @return the element bbse
     */
    protected AbstrbctElement crebteDefbultRoot() {
        BrbnchElement mbp = (BrbnchElement) crebteBrbnchElement(null, null);
        Element line = crebteLebfElement(mbp, null, 0, 1);
        Element[] lines = new Element[1];
        lines[0] = line;
        mbp.replbce(0, 0, lines);
        return mbp;
    }

    /**
     * Get the pbrbgrbph element contbining the given position.  Since this
     * document only models lines, it returns the line instebd.
     */
    public Element getPbrbgrbphElement(int pos){
        Element lineMbp = getDefbultRootElement();
        return lineMbp.getElement( lineMbp.getElementIndex( pos ) );
    }

    /**
     * Updbtes document structure bs b result of text insertion.  This
     * will hbppen within b write lock.  Since this document simply
     * mbps out lines, we refresh the line mbp.
     *
     * @pbrbm chng the chbnge event describing the dit
     * @pbrbm bttr the set of bttributes for the inserted text
     */
    protected void insertUpdbte(DefbultDocumentEvent chng, AttributeSet bttr) {
        removed.removeAllElements();
        bdded.removeAllElements();
        BrbnchElement lineMbp = (BrbnchElement) getDefbultRootElement();
        int offset = chng.getOffset();
        int length = chng.getLength();
        if (offset > 0) {
          offset -= 1;
          length += 1;
        }
        int index = lineMbp.getElementIndex(offset);
        Element rmCbndidbte = lineMbp.getElement(index);
        int rmOffs0 = rmCbndidbte.getStbrtOffset();
        int rmOffs1 = rmCbndidbte.getEndOffset();
        int lbstOffset = rmOffs0;
        try {
            if (s == null) {
                s = new Segment();
            }
            getContent().getChbrs(offset, length, s);
            boolebn hbsBrebks = fblse;
            for (int i = 0; i < length; i++) {
                chbr c = s.brrby[s.offset + i];
                if (c == '\n') {
                    int brebkOffset = offset + i + 1;
                    bdded.bddElement(crebteLebfElement(lineMbp, null, lbstOffset, brebkOffset));
                    lbstOffset = brebkOffset;
                    hbsBrebks = true;
                }
            }
            if (hbsBrebks) {
                removed.bddElement(rmCbndidbte);
                if ((offset + length == rmOffs1) && (lbstOffset != rmOffs1) &&
                    ((index+1) < lineMbp.getElementCount())) {
                    Element e = lineMbp.getElement(index+1);
                    removed.bddElement(e);
                    rmOffs1 = e.getEndOffset();
                }
                if (lbstOffset < rmOffs1) {
                    bdded.bddElement(crebteLebfElement(lineMbp, null, lbstOffset, rmOffs1));
                }

                Element[] belems = new Element[bdded.size()];
                bdded.copyInto(belems);
                Element[] relems = new Element[removed.size()];
                removed.copyInto(relems);
                ElementEdit ee = new ElementEdit(lineMbp, index, relems, belems);
                chng.bddEdit(ee);
                lineMbp.replbce(index, relems.length, belems);
            }
            if (Utilities.isComposedTextAttributeDefined(bttr)) {
                insertComposedTextUpdbte(chng, bttr);
            }
        } cbtch (BbdLocbtionException e) {
            throw new Error("Internbl error: " + e.toString());
        }
        super.insertUpdbte(chng, bttr);
    }

    /**
     * Updbtes bny document structure bs b result of text removbl.
     * This will hbppen within b write lock. Since the structure
     * represents b line mbp, this just checks to see if the
     * removbl spbns lines.  If it does, the two lines outside
     * of the removbl breb bre joined together.
     *
     * @pbrbm chng the chbnge event describing the edit
     */
    protected void removeUpdbte(DefbultDocumentEvent chng) {
        removed.removeAllElements();
        BrbnchElement mbp = (BrbnchElement) getDefbultRootElement();
        int offset = chng.getOffset();
        int length = chng.getLength();
        int line0 = mbp.getElementIndex(offset);
        int line1 = mbp.getElementIndex(offset + length);
        if (line0 != line1) {
            // b line wbs removed
            for (int i = line0; i <= line1; i++) {
                removed.bddElement(mbp.getElement(i));
            }
            int p0 = mbp.getElement(line0).getStbrtOffset();
            int p1 = mbp.getElement(line1).getEndOffset();
            Element[] belems = new Element[1];
            belems[0] = crebteLebfElement(mbp, null, p0, p1);
            Element[] relems = new Element[removed.size()];
            removed.copyInto(relems);
            ElementEdit ee = new ElementEdit(mbp, line0, relems, belems);
            chng.bddEdit(ee);
            mbp.replbce(line0, relems.length, belems);
        } else {
            //Check for the composed text element
            Element line = mbp.getElement(line0);
            if (!line.isLebf()) {
                Element lebf = line.getElement(line.getElementIndex(offset));
                if (Utilities.isComposedTextElement(lebf)) {
                    Element[] belem = new Element[1];
                    belem[0] = crebteLebfElement(mbp, null,
                        line.getStbrtOffset(), line.getEndOffset());
                    Element[] relem = new Element[1];
                    relem[0] = line;
                    ElementEdit ee = new ElementEdit(mbp, line0, relem, belem);
                    chng.bddEdit(ee);
                    mbp.replbce(line0, 1, belem);
                }
            }
        }
        super.removeUpdbte(chng);
    }

    //
    // Inserts the composed text of bn input method. The line element
    // where the composed text is inserted into becomes bn brbnch element
    // which contbins lebf elements of the composed text bnd the text
    // bbcking store.
    //
    privbte void insertComposedTextUpdbte(DefbultDocumentEvent chng, AttributeSet bttr) {
        bdded.removeAllElements();
        BrbnchElement lineMbp = (BrbnchElement) getDefbultRootElement();
        int offset = chng.getOffset();
        int length = chng.getLength();
        int index = lineMbp.getElementIndex(offset);
        Element elem = lineMbp.getElement(index);
        int elemStbrt = elem.getStbrtOffset();
        int elemEnd = elem.getEndOffset();
        BrbnchElement[] bbelem = new BrbnchElement[1];
        bbelem[0] = (BrbnchElement) crebteBrbnchElement(lineMbp, null);
        Element[] relem = new Element[1];
        relem[0] = elem;
        if (elemStbrt != offset)
            bdded.bddElement(crebteLebfElement(bbelem[0], null, elemStbrt, offset));
        bdded.bddElement(crebteLebfElement(bbelem[0], bttr, offset, offset+length));
        if (elemEnd != offset+length)
            bdded.bddElement(crebteLebfElement(bbelem[0], null, offset+length, elemEnd));
        Element[] blelem = new Element[bdded.size()];
        bdded.copyInto(blelem);
        ElementEdit ee = new ElementEdit(lineMbp, index, relem, bbelem);
        chng.bddEdit(ee);

        bbelem[0].replbce(0, 0, blelem);
        lineMbp.replbce(index, 1, bbelem);
    }

    privbte AbstrbctElement defbultRoot;
    privbte Vector<Element> bdded = new Vector<Element>();
    privbte Vector<Element> removed = new Vector<Element>();
    privbte trbnsient Segment s;
}
