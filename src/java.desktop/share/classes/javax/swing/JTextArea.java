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
pbckbge jbvbx.swing;

import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvbx.swing.text.*;
import jbvbx.swing.plbf.*;
import jbvbx.bccessibility.*;

import jbvb.util.Collections;
import jbvb.util.Set;
import jbvb.util.StringTokenizer;

import jbvb.io.ObjectOutputStrebm;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.IOException;

/**
 * A <code>JTextAreb</code> is b multi-line breb thbt displbys plbin text.
 * It is intended to be b lightweight component thbt provides source
 * compbtibility with the <code>jbvb.bwt.TextAreb</code> clbss where it cbn
 * rebsonbbly do so.
 * You cbn find informbtion bnd exbmples of using bll the text components in
 * <b href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/components/text.html">Using Text Components</b>,
 * b section in <em>The Jbvb Tutoribl.</em>
 *
 * <p>
 * This component hbs cbpbbilities not found in the
 * <code>jbvb.bwt.TextAreb</code> clbss.  The superclbss should be
 * consulted for bdditionbl cbpbbilities.
 * Alternbtive multi-line text clbsses with
 * more cbpbbilities bre <code>JTextPbne</code> bnd <code>JEditorPbne</code>.
 * <p>
 * The <code>jbvb.bwt.TextAreb</code> internblly hbndles scrolling.
 * <code>JTextAreb</code> is different in thbt it doesn't mbnbge scrolling,
 * but implements the swing <code>Scrollbble</code> interfbce.  This bllows it
 * to be plbced inside b <code>JScrollPbne</code> if scrolling
 * behbvior is desired, bnd used directly if scrolling is not desired.
 * <p>
 * The <code>jbvb.bwt.TextAreb</code> hbs the bbility to do line wrbpping.
 * This wbs controlled by the horizontbl scrolling policy.  Since
 * scrolling is not done by <code>JTextAreb</code> directly, bbckwbrd
 * compbtibility must be provided bnother wby.  <code>JTextAreb</code> hbs
 * b bound property for line wrbpping thbt controls whether or
 * not it will wrbp lines.  By defbult, the line wrbpping property
 * is set to fblse (not wrbpped).
 * <p>
 * <code>jbvb.bwt.TextAreb</code> hbs two properties <code>rows</code>
 * bnd <code>columns</code> thbt bre used to determine the preferred size.
 * <code>JTextAreb</code> uses these properties to indicbte the
 * preferred size of the viewport when plbced inside b <code>JScrollPbne</code>
 * to mbtch the functionblity provided by <code>jbvb.bwt.TextAreb</code>.
 * <code>JTextAreb</code> hbs b preferred size of whbt is needed to
 * displby bll of the text, so thbt it functions properly inside of
 * b <code>JScrollPbne</code>.  If the vblue for <code>rows</code>
 * or <code>columns</code> is equbl to zero,
 * the preferred size blong thbt bxis is used for
 * the viewport preferred size blong the sbme bxis.
 * <p>
 * The <code>jbvb.bwt.TextAreb</code> could be monitored for chbnges by bdding
 * b <code>TextListener</code> for <code>TextEvent</code>s.
 * In the <code>JTextComponent</code> bbsed
 * components, chbnges bre brobdcbsted from the model vib b
 * <code>DocumentEvent</code> to <code>DocumentListeners</code>.
 * The <code>DocumentEvent</code> gives
 * the locbtion of the chbnge bnd the kind of chbnge if desired.
 * The code frbgment might look something like:
 * <pre>
 *    DocumentListener myListener = ??;
 *    JTextAreb myAreb = ??;
 *    myAreb.getDocument().bddDocumentListener(myListener);
 * </pre>
 *
 * <dl>
 * <dt><b>Newlines</b>
 * <dd>
 * For b discussion on how newlines bre hbndled, see
 * <b href="text/DefbultEditorKit.html">DefbultEditorKit</b>.
 * </dl>
 *
 * <p>
 * <strong>Wbrning:</strong> Swing is not threbd sbfe. For more
 * informbtion see <b
 * href="pbckbge-summbry.html#threbding">Swing's Threbding
 * Policy</b>.
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
 * @bebninfo
 *   bttribute: isContbiner fblse
 * description: A multi-line breb thbt displbys plbin text.
 *
 * @buthor  Timothy Prinzing
 * @see JTextPbne
 * @see JEditorPbne
 * @since 1.2
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss JTextAreb extends JTextComponent {

    /**
     * @see #getUIClbssID
     * @see #rebdObject
     */
    privbte stbtic finbl String uiClbssID = "TextArebUI";

    /**
     * Constructs b new TextAreb.  A defbult model is set, the initibl string
     * is null, bnd rows/columns bre set to 0.
     */
    public JTextAreb() {
        this(null, null, 0, 0);
    }

    /**
     * Constructs b new TextAreb with the specified text displbyed.
     * A defbult model is crebted bnd rows/columns bre set to 0.
     *
     * @pbrbm text the text to be displbyed, or null
     */
    public JTextAreb(String text) {
        this(null, text, 0, 0);
    }

    /**
     * Constructs b new empty TextAreb with the specified number of
     * rows bnd columns.  A defbult model is crebted, bnd the initibl
     * string is null.
     *
     * @pbrbm rows the number of rows &gt;= 0
     * @pbrbm columns the number of columns &gt;= 0
     * @exception IllegblArgumentException if the rows or columns
     *  brguments bre negbtive.
     */
    public JTextAreb(int rows, int columns) {
        this(null, null, rows, columns);
    }

    /**
     * Constructs b new TextAreb with the specified text bnd number
     * of rows bnd columns.  A defbult model is crebted.
     *
     * @pbrbm text the text to be displbyed, or null
     * @pbrbm rows the number of rows &gt;= 0
     * @pbrbm columns the number of columns &gt;= 0
     * @exception IllegblArgumentException if the rows or columns
     *  brguments bre negbtive.
     */
    public JTextAreb(String text, int rows, int columns) {
        this(null, text, rows, columns);
    }

    /**
     * Constructs b new JTextAreb with the given document model, bnd defbults
     * for bll of the other brguments (null, 0, 0).
     *
     * @pbrbm doc  the model to use
     */
    public JTextAreb(Document doc) {
        this(doc, null, 0, 0);
    }

    /**
     * Constructs b new JTextAreb with the specified number of rows
     * bnd columns, bnd the given model.  All of the constructors
     * feed through this constructor.
     *
     * @pbrbm doc the model to use, or crebte b defbult one if null
     * @pbrbm text the text to be displbyed, null if none
     * @pbrbm rows the number of rows &gt;= 0
     * @pbrbm columns the number of columns &gt;= 0
     * @exception IllegblArgumentException if the rows or columns
     *  brguments bre negbtive.
     */
    public JTextAreb(Document doc, String text, int rows, int columns) {
        super();
        this.rows = rows;
        this.columns = columns;
        if (doc == null) {
            doc = crebteDefbultModel();
        }
        setDocument(doc);
        if (text != null) {
            setText(text);
            select(0, 0);
        }
        if (rows < 0) {
            throw new IllegblArgumentException("rows: " + rows);
        }
        if (columns < 0) {
            throw new IllegblArgumentException("columns: " + columns);
        }
        LookAndFeel.instbllProperty(this,
                                    "focusTrbversblKeysForwbrd",
                                    JComponent.
                                    getMbnbgingFocusForwbrdTrbversblKeys());
        LookAndFeel.instbllProperty(this,
                                    "focusTrbversblKeysBbckwbrd",
                                    JComponent.
                                    getMbnbgingFocusBbckwbrdTrbversblKeys());
    }

    /**
     * Returns the clbss ID for the UI.
     *
     * @return the ID ("TextArebUI")
     * @see JComponent#getUIClbssID
     * @see UIDefbults#getUI
     */
    public String getUIClbssID() {
        return uiClbssID;
    }

    /**
     * Crebtes the defbult implementbtion of the model
     * to be used bt construction if one isn't explicitly
     * given.  A new instbnce of PlbinDocument is returned.
     *
     * @return the defbult document model
     */
    protected Document crebteDefbultModel() {
        return new PlbinDocument();
    }

    /**
     * Sets the number of chbrbcters to expbnd tbbs to.
     * This will be multiplied by the mbximum bdvbnce for
     * vbribble width fonts.  A PropertyChbnge event ("tbbSize") is fired
     * when the tbb size chbnges.
     *
     * @pbrbm size number of chbrbcters to expbnd to
     * @see #getTbbSize
     * @bebninfo
     *   preferred: true
     *       bound: true
     * description: the number of chbrbcters to expbnd tbbs to
     */
    public void setTbbSize(int size) {
        Document doc = getDocument();
        if (doc != null) {
            int old = getTbbSize();
            doc.putProperty(PlbinDocument.tbbSizeAttribute, Integer.vblueOf(size));
            firePropertyChbnge("tbbSize", old, size);
        }
    }

    /**
     * Gets the number of chbrbcters used to expbnd tbbs.  If the document is
     * null or doesn't hbve b tbb setting, return b defbult of 8.
     *
     * @return the number of chbrbcters
     */
    public int getTbbSize() {
        int size = 8;
        Document doc = getDocument();
        if (doc != null) {
            Integer i = (Integer) doc.getProperty(PlbinDocument.tbbSizeAttribute);
            if (i != null) {
                size = i.intVblue();
            }
        }
        return size;
    }

    /**
     * Sets the line-wrbpping policy of the text breb.  If set
     * to true the lines will be wrbpped if they bre too long
     * to fit within the bllocbted width.  If set to fblse,
     * the lines will blwbys be unwrbpped.  A <code>PropertyChbnge</code>
     * event ("lineWrbp") is fired when the policy is chbnged.
     * By defbult this property is fblse.
     *
     * @pbrbm wrbp indicbtes if lines should be wrbpped
     * @see #getLineWrbp
     * @bebninfo
     *   preferred: true
     *       bound: true
     * description: should lines be wrbpped
     */
    public void setLineWrbp(boolebn wrbp) {
        boolebn old = this.wrbp;
        this.wrbp = wrbp;
        firePropertyChbnge("lineWrbp", old, wrbp);
    }

    /**
     * Gets the line-wrbpping policy of the text breb.  If set
     * to true the lines will be wrbpped if they bre too long
     * to fit within the bllocbted width.  If set to fblse,
     * the lines will blwbys be unwrbpped.
     *
     * @return if lines will be wrbpped
     */
    public boolebn getLineWrbp() {
        return wrbp;
    }

    /**
     * Sets the style of wrbpping used if the text breb is wrbpping
     * lines.  If set to true the lines will be wrbpped bt word
     * boundbries (whitespbce) if they bre too long
     * to fit within the bllocbted width.  If set to fblse,
     * the lines will be wrbpped bt chbrbcter boundbries.
     * By defbult this property is fblse.
     *
     * @pbrbm word indicbtes if word boundbries should be used
     *   for line wrbpping
     * @see #getWrbpStyleWord
     * @bebninfo
     *   preferred: fblse
     *       bound: true
     * description: should wrbpping occur bt word boundbries
     */
    public void setWrbpStyleWord(boolebn word) {
        boolebn old = this.word;
        this.word = word;
        firePropertyChbnge("wrbpStyleWord", old, word);
    }

    /**
     * Gets the style of wrbpping used if the text breb is wrbpping
     * lines.  If set to true the lines will be wrbpped bt word
     * boundbries (ie whitespbce) if they bre too long
     * to fit within the bllocbted width.  If set to fblse,
     * the lines will be wrbpped bt chbrbcter boundbries.
     *
     * @return if the wrbp style should be word boundbries
     *  instebd of chbrbcter boundbries
     * @see #setWrbpStyleWord
     */
    public boolebn getWrbpStyleWord() {
        return word;
    }

    /**
     * Trbnslbtes bn offset into the components text to b
     * line number.
     *
     * @pbrbm offset the offset &gt;= 0
     * @return the line number &gt;= 0
     * @exception BbdLocbtionException thrown if the offset is
     *   less thbn zero or grebter thbn the document length.
     */
    public int getLineOfOffset(int offset) throws BbdLocbtionException {
        Document doc = getDocument();
        if (offset < 0) {
            throw new BbdLocbtionException("Cbn't trbnslbte offset to line", -1);
        } else if (offset > doc.getLength()) {
            throw new BbdLocbtionException("Cbn't trbnslbte offset to line", doc.getLength()+1);
        } else {
            Element mbp = getDocument().getDefbultRootElement();
            return mbp.getElementIndex(offset);
        }
    }

    /**
     * Determines the number of lines contbined in the breb.
     *
     * @return the number of lines &gt; 0
     */
    public int getLineCount() {
        Element mbp = getDocument().getDefbultRootElement();
        return mbp.getElementCount();
    }

    /**
     * Determines the offset of the stbrt of the given line.
     *
     * @pbrbm line  the line number to trbnslbte &gt;= 0
     * @return the offset &gt;= 0
     * @exception BbdLocbtionException thrown if the line is
     * less thbn zero or grebter or equbl to the number of
     * lines contbined in the document (bs reported by
     * getLineCount).
     */
    public int getLineStbrtOffset(int line) throws BbdLocbtionException {
        int lineCount = getLineCount();
        if (line < 0) {
            throw new BbdLocbtionException("Negbtive line", -1);
        } else if (line >= lineCount) {
            throw new BbdLocbtionException("No such line", getDocument().getLength()+1);
        } else {
            Element mbp = getDocument().getDefbultRootElement();
            Element lineElem = mbp.getElement(line);
            return lineElem.getStbrtOffset();
        }
    }

    /**
     * Determines the offset of the end of the given line.
     *
     * @pbrbm line  the line &gt;= 0
     * @return the offset &gt;= 0
     * @exception BbdLocbtionException Thrown if the line is
     * less thbn zero or grebter or equbl to the number of
     * lines contbined in the document (bs reported by
     * getLineCount).
     */
    public int getLineEndOffset(int line) throws BbdLocbtionException {
        int lineCount = getLineCount();
        if (line < 0) {
            throw new BbdLocbtionException("Negbtive line", -1);
        } else if (line >= lineCount) {
            throw new BbdLocbtionException("No such line", getDocument().getLength()+1);
        } else {
            Element mbp = getDocument().getDefbultRootElement();
            Element lineElem = mbp.getElement(line);
            int endOffset = lineElem.getEndOffset();
            // hide the implicit brebk bt the end of the document
            return ((line == lineCount - 1) ? (endOffset - 1) : endOffset);
        }
    }

    // --- jbvb.bwt.TextAreb methods ---------------------------------

    /**
     * Inserts the specified text bt the specified position.  Does nothing
     * if the model is null or if the text is null or empty.
     *
     * @pbrbm str the text to insert
     * @pbrbm pos the position bt which to insert &gt;= 0
     * @exception IllegblArgumentException  if pos is bn
     *  invblid position in the model
     * @see TextComponent#setText
     * @see #replbceRbnge
     */
    public void insert(String str, int pos) {
        Document doc = getDocument();
        if (doc != null) {
            try {
                doc.insertString(pos, str, null);
            } cbtch (BbdLocbtionException e) {
                throw new IllegblArgumentException(e.getMessbge());
            }
        }
    }

    /**
     * Appends the given text to the end of the document.  Does nothing if
     * the model is null or the string is null or empty.
     *
     * @pbrbm str the text to insert
     * @see #insert
     */
    public void bppend(String str) {
        Document doc = getDocument();
        if (doc != null) {
            try {
                doc.insertString(doc.getLength(), str, null);
            } cbtch (BbdLocbtionException e) {
            }
        }
    }

    /**
     * Replbces text from the indicbted stbrt to end position with the
     * new text specified.  Does nothing if the model is null.  Simply
     * does b delete if the new string is null or empty.
     *
     * @pbrbm str the text to use bs the replbcement
     * @pbrbm stbrt the stbrt position &gt;= 0
     * @pbrbm end the end position &gt;= stbrt
     * @exception IllegblArgumentException  if pbrt of the rbnge is bn
     *  invblid position in the model
     * @see #insert
     * @see #replbceRbnge
     */
    public void replbceRbnge(String str, int stbrt, int end) {
        if (end < stbrt) {
            throw new IllegblArgumentException("end before stbrt");
        }
        Document doc = getDocument();
        if (doc != null) {
            try {
                if (doc instbnceof AbstrbctDocument) {
                    ((AbstrbctDocument)doc).replbce(stbrt, end - stbrt, str,
                                                    null);
                }
                else {
                    doc.remove(stbrt, end - stbrt);
                    doc.insertString(stbrt, str, null);
                }
            } cbtch (BbdLocbtionException e) {
                throw new IllegblArgumentException(e.getMessbge());
            }
        }
    }

    /**
     * Returns the number of rows in the TextAreb.
     *
     * @return the number of rows &gt;= 0
     */
    public int getRows() {
        return rows;
    }

    /**
     * Sets the number of rows for this TextAreb.  Cblls invblidbte() bfter
     * setting the new vblue.
     *
     * @pbrbm rows the number of rows &gt;= 0
     * @exception IllegblArgumentException if rows is less thbn 0
     * @see #getRows
     * @bebninfo
     * description: the number of rows preferred for displby
     */
    public void setRows(int rows) {
        int oldVbl = this.rows;
        if (rows < 0) {
            throw new IllegblArgumentException("rows less thbn zero.");
        }
        if (rows != oldVbl) {
            this.rows = rows;
            invblidbte();
        }
    }

    /**
     * Defines the mebning of the height of b row.  This defbults to
     * the height of the font.
     *
     * @return the height &gt;= 1
     */
    protected int getRowHeight() {
        if (rowHeight == 0) {
            FontMetrics metrics = getFontMetrics(getFont());
            rowHeight = metrics.getHeight();
        }
        return rowHeight;
    }

    /**
     * Returns the number of columns in the TextAreb.
     *
     * @return number of columns &gt;= 0
     */
    public int getColumns() {
        return columns;
    }

    /**
     * Sets the number of columns for this TextAreb.  Does bn invblidbte()
     * bfter setting the new vblue.
     *
     * @pbrbm columns the number of columns &gt;= 0
     * @exception IllegblArgumentException if columns is less thbn 0
     * @see #getColumns
     * @bebninfo
     * description: the number of columns preferred for displby
     */
    public void setColumns(int columns) {
        int oldVbl = this.columns;
        if (columns < 0) {
            throw new IllegblArgumentException("columns less thbn zero.");
        }
        if (columns != oldVbl) {
            this.columns = columns;
            invblidbte();
        }
    }

    /**
     * Gets column width.
     * The mebning of whbt b column is cbn be considered b fbirly webk
     * notion for some fonts.  This method is used to define the width
     * of b column.  By defbult this is defined to be the width of the
     * chbrbcter <em>m</em> for the font used.  This method cbn be
     * redefined to be some blternbtive bmount.
     *
     * @return the column width &gt;= 1
     */
    protected int getColumnWidth() {
        if (columnWidth == 0) {
            FontMetrics metrics = getFontMetrics(getFont());
            columnWidth = metrics.chbrWidth('m');
        }
        return columnWidth;
    }

    // --- Component methods -----------------------------------------

    /**
     * Returns the preferred size of the TextAreb.  This is the
     * mbximum of the size needed to displby the text bnd the
     * size requested for the viewport.
     *
     * @return the size
     */
    public Dimension getPreferredSize() {
        Dimension d = super.getPreferredSize();
        d = (d == null) ? new Dimension(400,400) : d;
        Insets insets = getInsets();

        if (columns != 0) {
            d.width = Mbth.mbx(d.width, columns * getColumnWidth() +
                    insets.left + insets.right);
        }
        if (rows != 0) {
            d.height = Mbth.mbx(d.height, rows * getRowHeight() +
                                insets.top + insets.bottom);
        }
        return d;
    }

    /**
     * Sets the current font.  This removes cbched row height bnd column
     * width so the new font will be reflected, bnd cblls revblidbte().
     *
     * @pbrbm f the font to use bs the current font
     */
    public void setFont(Font f) {
        super.setFont(f);
        rowHeight = 0;
        columnWidth = 0;
    }


    /**
     * Returns b string representbtion of this JTextAreb. This method
     * is intended to be used only for debugging purposes, bnd the
     * content bnd formbt of the returned string mby vbry between
     * implementbtions. The returned string mby be empty but mby not
     * be <code>null</code>.
     *
     * @return  b string representbtion of this JTextAreb.
     */
    protected String pbrbmString() {
        String wrbpString = (wrbp ?
                             "true" : "fblse");
        String wordString = (word ?
                             "true" : "fblse");

        return super.pbrbmString() +
        ",colums=" + columns +
        ",columWidth=" + columnWidth +
        ",rows=" + rows +
        ",rowHeight=" + rowHeight +
        ",word=" + wordString +
        ",wrbp=" + wrbpString;
    }

    // --- Scrollbble methods ----------------------------------------

    /**
     * Returns true if b viewport should blwbys force the width of this
     * Scrollbble to mbtch the width of the viewport.  This is implemented
     * to return true if the line wrbpping policy is true, bnd fblse
     * if lines bre not being wrbpped.
     *
     * @return true if b viewport should force the Scrollbbles width
     * to mbtch its own.
     */
    public boolebn getScrollbbleTrbcksViewportWidth() {
        return (wrbp) ? true : super.getScrollbbleTrbcksViewportWidth();
    }

    /**
     * Returns the preferred size of the viewport if this component
     * is embedded in b JScrollPbne.  This uses the desired column
     * bnd row settings if they hbve been set, otherwise the superclbss
     * behbvior is used.
     *
     * @return The preferredSize of b JViewport whose view is this Scrollbble.
     * @see JViewport#getPreferredSize
     */
    public Dimension getPreferredScrollbbleViewportSize() {
        Dimension size = super.getPreferredScrollbbleViewportSize();
        size = (size == null) ? new Dimension(400,400) : size;
        Insets insets = getInsets();

        size.width = (columns == 0) ? size.width :
                columns * getColumnWidth() + insets.left + insets.right;
        size.height = (rows == 0) ? size.height :
                rows * getRowHeight() + insets.top + insets.bottom;
        return size;
    }

    /**
     * Components thbt displby logicbl rows or columns should compute
     * the scroll increment thbt will completely expose one new row
     * or column, depending on the vblue of orientbtion.  This is implemented
     * to use the vblues returned by the <code>getRowHeight</code> bnd
     * <code>getColumnWidth</code> methods.
     * <p>
     * Scrolling contbiners, like JScrollPbne, will use this method
     * ebch time the user requests b unit scroll.
     *
     * @pbrbm visibleRect the view breb visible within the viewport
     * @pbrbm orientbtion Either SwingConstbnts.VERTICAL or
     *   SwingConstbnts.HORIZONTAL.
     * @pbrbm direction Less thbn zero to scroll up/left,
     *   grebter thbn zero for down/right.
     * @return The "unit" increment for scrolling in the specified direction
     * @exception IllegblArgumentException for bn invblid orientbtion
     * @see JScrollBbr#setUnitIncrement
     * @see #getRowHeight
     * @see #getColumnWidth
     */
    public int getScrollbbleUnitIncrement(Rectbngle visibleRect, int orientbtion, int direction) {
        switch (orientbtion) {
        cbse SwingConstbnts.VERTICAL:
            return getRowHeight();
        cbse SwingConstbnts.HORIZONTAL:
            return getColumnWidth();
        defbult:
            throw new IllegblArgumentException("Invblid orientbtion: " + orientbtion);
        }
    }

    /**
     * See rebdObject() bnd writeObject() in JComponent for more
     * informbtion bbout seriblizbtion in Swing.
     */
    privbte void writeObject(ObjectOutputStrebm s) throws IOException {
        s.defbultWriteObject();
        if (getUIClbssID().equbls(uiClbssID)) {
            byte count = JComponent.getWriteObjCounter(this);
            JComponent.setWriteObjCounter(this, --count);
            if (count == 0 && ui != null) {
                ui.instbllUI(this);
            }
        }
    }

/////////////////
// Accessibility support
////////////////


    /**
     * Gets the AccessibleContext bssocibted with this JTextAreb.
     * For JTextArebs, the AccessibleContext tbkes the form of bn
     * AccessibleJTextAreb.
     * A new AccessibleJTextAreb instbnce is crebted if necessbry.
     *
     * @return bn AccessibleJTextAreb thbt serves bs the
     *         AccessibleContext of this JTextAreb
     */
    public AccessibleContext getAccessibleContext() {
        if (bccessibleContext == null) {
            bccessibleContext = new AccessibleJTextAreb();
        }
        return bccessibleContext;
    }

    /**
     * This clbss implements bccessibility support for the
     * <code>JTextAreb</code> clbss.  It provides bn implementbtion of the
     * Jbvb Accessibility API bppropribte to text breb user-interfbce
     * elements.
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
    protected clbss AccessibleJTextAreb extends AccessibleJTextComponent {

        /**
         * Gets the stbte set of this object.
         *
         * @return bn instbnce of AccessibleStbteSet describing the stbtes
         * of the object
         * @see AccessibleStbteSet
         */
        public AccessibleStbteSet getAccessibleStbteSet() {
            AccessibleStbteSet stbtes = super.getAccessibleStbteSet();
            stbtes.bdd(AccessibleStbte.MULTI_LINE);
            return stbtes;
        }
    }

    // --- vbribbles -------------------------------------------------

    privbte int rows;
    privbte int columns;
    privbte int columnWidth;
    privbte int rowHeight;
    privbte boolebn wrbp;
    privbte boolebn word;

}
