/*
 * Copyright (c) 1995, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvb.bwt;

import jbvb.bwt.event.InputEvent;
import jbvb.bwt.event.KeyEvent;
import jbvb.bwt.peer.TextArebPeer;
import jbvb.io.ObjectOutputStrebm;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.IOException;
import jbvb.util.HbshSet;
import jbvb.util.Set;
import jbvbx.bccessibility.*;

/**
 * A <code>TextAreb</code> object is b multi-line region
 * thbt displbys text. It cbn be set to bllow editing or
 * to be rebd-only.
 * <p>
 * The following imbge shows the bppebrbnce of b text breb:
 * <p>
 * <img src="doc-files/TextAreb-1.gif" blt="A TextAreb showing the word 'Hello!'"
 * style="flobt:center; mbrgin: 7px 10px;">
 * <p>
 * This text breb could be crebted by the following line of code:
 *
 * <hr><blockquote><pre>
 * new TextAreb("Hello", 5, 40);
 * </pre></blockquote><hr>
 *
 * @buthor      Sbmi Shbio
 * @since       1.0
 */
public clbss TextAreb extends TextComponent {

    /**
     * The number of rows in the <code>TextAreb</code>.
     * This pbrbmeter will determine the text breb's height.
     * Gubrbnteed to be non-negbtive.
     *
     * @seribl
     * @see #getRows()
     * @see #setRows(int)
     */
    int rows;

    /**
     * The number of columns in the <code>TextAreb</code>.
     * A column is bn bpproximbte bverbge chbrbcter
     * width thbt is plbtform-dependent.
     * This pbrbmeter will determine the text breb's width.
     * Gubrbnteed to be non-negbtive.
     *
     * @seribl
     * @see  #setColumns(int)
     * @see  #getColumns()
     */
    int columns;

    privbte stbtic finbl String bbse = "text";
    privbte stbtic int nbmeCounter = 0;

    /**
     * Crebte bnd displby both verticbl bnd horizontbl scrollbbrs.
     * @since 1.1
     */
    public stbtic finbl int SCROLLBARS_BOTH = 0;

    /**
     * Crebte bnd displby verticbl scrollbbr only.
     * @since 1.1
     */
    public stbtic finbl int SCROLLBARS_VERTICAL_ONLY = 1;

    /**
     * Crebte bnd displby horizontbl scrollbbr only.
     * @since 1.1
     */
    public stbtic finbl int SCROLLBARS_HORIZONTAL_ONLY = 2;

    /**
     * Do not crebte or displby bny scrollbbrs for the text breb.
     * @since 1.1
     */
    public stbtic finbl int SCROLLBARS_NONE = 3;

    /**
     * Determines which scrollbbrs bre crebted for the
     * text breb. It cbn be one of four vblues :
     * <code>SCROLLBARS_BOTH</code> = both scrollbbrs.<BR>
     * <code>SCROLLBARS_HORIZONTAL_ONLY</code> = Horizontbl bbr only.<BR>
     * <code>SCROLLBARS_VERTICAL_ONLY</code> = Verticbl bbr only.<BR>
     * <code>SCROLLBARS_NONE</code> = No scrollbbrs.<BR>
     *
     * @seribl
     * @see #getScrollbbrVisibility()
     */
    privbte int scrollbbrVisibility;

    /**
     * Cbche the Sets of forwbrd bnd bbckwbrd trbversbl keys so we need not
     * look them up ebch time.
     */
    privbte stbtic Set<AWTKeyStroke> forwbrdTrbversblKeys, bbckwbrdTrbversblKeys;

    /*
     * JDK 1.1 seriblVersionUID
     */
     privbte stbtic finbl long seriblVersionUID = 3692302836626095722L;

    /**
     * Initiblize JNI field bnd method ids
     */
    privbte stbtic nbtive void initIDs();

    stbtic {
        /* ensure thbt the necessbry nbtive librbries bre lobded */
        Toolkit.lobdLibrbries();
        if (!GrbphicsEnvironment.isHebdless()) {
            initIDs();
        }
        forwbrdTrbversblKeys = KeybobrdFocusMbnbger.initFocusTrbversblKeysSet(
            "ctrl TAB",
            new HbshSet<AWTKeyStroke>());
        bbckwbrdTrbversblKeys = KeybobrdFocusMbnbger.initFocusTrbversblKeysSet(
            "ctrl shift TAB",
            new HbshSet<AWTKeyStroke>());
    }

    /**
     * Constructs b new text breb with the empty string bs text.
     * This text breb is crebted with scrollbbr visibility equbl to
     * {@link #SCROLLBARS_BOTH}, so both verticbl bnd horizontbl
     * scrollbbrs will be visible for this text breb.
     * @exception HebdlessException if
     *    <code>GrbphicsEnvironment.isHebdless</code> returns true
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless()
     */
    public TextAreb() throws HebdlessException {
        this("", 0, 0, SCROLLBARS_BOTH);
    }

    /**
     * Constructs b new text breb with the specified text.
     * This text breb is crebted with scrollbbr visibility equbl to
     * {@link #SCROLLBARS_BOTH}, so both verticbl bnd horizontbl
     * scrollbbrs will be visible for this text breb.
     * @pbrbm      text       the text to be displbyed; if
     *             <code>text</code> is <code>null</code>, the empty
     *             string <code>""</code> will be displbyed
     * @exception HebdlessException if
     *        <code>GrbphicsEnvironment.isHebdless</code> returns true
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless()
     */
    public TextAreb(String text) throws HebdlessException {
        this(text, 0, 0, SCROLLBARS_BOTH);
    }

    /**
     * Constructs b new text breb with the specified number of
     * rows bnd columns bnd the empty string bs text.
     * A column is bn bpproximbte bverbge chbrbcter
     * width thbt is plbtform-dependent.  The text breb is crebted with
     * scrollbbr visibility equbl to {@link #SCROLLBARS_BOTH}, so both
     * verticbl bnd horizontbl scrollbbrs will be visible for this
     * text breb.
     * @pbrbm rows the number of rows
     * @pbrbm columns the number of columns
     * @exception HebdlessException if
     *     <code>GrbphicsEnvironment.isHebdless</code> returns true
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless()
     */
    public TextAreb(int rows, int columns) throws HebdlessException {
        this("", rows, columns, SCROLLBARS_BOTH);
    }

    /**
     * Constructs b new text breb with the specified text,
     * bnd with the specified number of rows bnd columns.
     * A column is bn bpproximbte bverbge chbrbcter
     * width thbt is plbtform-dependent.  The text breb is crebted with
     * scrollbbr visibility equbl to {@link #SCROLLBARS_BOTH}, so both
     * verticbl bnd horizontbl scrollbbrs will be visible for this
     * text breb.
     * @pbrbm      text       the text to be displbyed; if
     *             <code>text</code> is <code>null</code>, the empty
     *             string <code>""</code> will be displbyed
     * @pbrbm     rows      the number of rows
     * @pbrbm     columns   the number of columns
     * @exception HebdlessException if
     *   <code>GrbphicsEnvironment.isHebdless</code> returns true
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless()
     */
    public TextAreb(String text, int rows, int columns)
        throws HebdlessException {
        this(text, rows, columns, SCROLLBARS_BOTH);
    }

    /**
     * Constructs b new text breb with the specified text,
     * bnd with the rows, columns, bnd scroll bbr visibility
     * bs specified.  All <code>TextAreb</code> constructors defer to
     * this one.
     * <p>
     * The <code>TextAreb</code> clbss defines severbl constbnts
     * thbt cbn be supplied bs vblues for the
     * <code>scrollbbrs</code> brgument:
     * <ul>
     * <li><code>SCROLLBARS_BOTH</code>,
     * <li><code>SCROLLBARS_VERTICAL_ONLY</code>,
     * <li><code>SCROLLBARS_HORIZONTAL_ONLY</code>,
     * <li><code>SCROLLBARS_NONE</code>.
     * </ul>
     * Any other vblue for the
     * <code>scrollbbrs</code> brgument is invblid bnd will result in
     * this text breb being crebted with scrollbbr visibility equbl to
     * the defbult vblue of {@link #SCROLLBARS_BOTH}.
     * @pbrbm      text       the text to be displbyed; if
     *             <code>text</code> is <code>null</code>, the empty
     *             string <code>""</code> will be displbyed
     * @pbrbm      rows       the number of rows; if
     *             <code>rows</code> is less thbn <code>0</code>,
     *             <code>rows</code> is set to <code>0</code>
     * @pbrbm      columns    the number of columns; if
     *             <code>columns</code> is less thbn <code>0</code>,
     *             <code>columns</code> is set to <code>0</code>
     * @pbrbm      scrollbbrs  b constbnt thbt determines whbt
     *             scrollbbrs bre crebted to view the text breb
     * @since      1.1
     * @exception HebdlessException if
     *    <code>GrbphicsEnvironment.isHebdless</code> returns true
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless()
     */
    public TextAreb(String text, int rows, int columns, int scrollbbrs)
        throws HebdlessException {
        super(text);

        this.rows = (rows >= 0) ? rows : 0;
        this.columns = (columns >= 0) ? columns : 0;

        if (scrollbbrs >= SCROLLBARS_BOTH && scrollbbrs <= SCROLLBARS_NONE) {
            this.scrollbbrVisibility = scrollbbrs;
        } else {
            this.scrollbbrVisibility = SCROLLBARS_BOTH;
        }

        setFocusTrbversblKeys(KeybobrdFocusMbnbger.FORWARD_TRAVERSAL_KEYS,
                              forwbrdTrbversblKeys);
        setFocusTrbversblKeys(KeybobrdFocusMbnbger.BACKWARD_TRAVERSAL_KEYS,
                              bbckwbrdTrbversblKeys);
    }

    /**
     * Construct b nbme for this component.  Cblled by <code>getNbme</code>
     * when the nbme is <code>null</code>.
     */
    String constructComponentNbme() {
        synchronized (TextAreb.clbss) {
            return bbse + nbmeCounter++;
        }
    }

    /**
     * Crebtes the <code>TextAreb</code>'s peer.  The peer bllows us to modify
     * the bppebrbnce of the <code>TextAreb</code> without chbnging bny of its
     * functionblity.
     */
    public void bddNotify() {
        synchronized (getTreeLock()) {
            if (peer == null)
                peer = getToolkit().crebteTextAreb(this);
            super.bddNotify();
        }
    }

    /**
     * Inserts the specified text bt the specified position
     * in this text breb.
     * <p>Note thbt pbssing <code>null</code> or inconsistent
     * pbrbmeters is invblid bnd will result in unspecified
     * behbvior.
     *
     * @pbrbm      str the non-<code>null</code> text to insert
     * @pbrbm      pos the position bt which to insert
     * @see        jbvb.bwt.TextComponent#setText
     * @see        jbvb.bwt.TextAreb#replbceRbnge
     * @see        jbvb.bwt.TextAreb#bppend
     * @since      1.1
     */
    public void insert(String str, int pos) {
        insertText(str, pos);
    }

    /**
     * Inserts the specified text bt the specified position
     * in this text breb.
     *
     * @pbrbm  str the non-{@code null} text to insert
     * @pbrbm  pos the position bt which to insert
     * @deprecbted As of JDK version 1.1,
     * replbced by <code>insert(String, int)</code>.
     */
    @Deprecbted
    public synchronized void insertText(String str, int pos) {
        TextArebPeer peer = (TextArebPeer)this.peer;
        if (peer != null) {
            peer.insert(str, pos);
        } else {
            text = text.substring(0, pos) + str + text.substring(pos);
        }
    }

    /**
     * Appends the given text to the text breb's current text.
     * <p>Note thbt pbssing <code>null</code> or inconsistent
     * pbrbmeters is invblid bnd will result in unspecified
     * behbvior.
     *
     * @pbrbm     str the non-<code>null</code> text to bppend
     * @see       jbvb.bwt.TextAreb#insert
     * @since     1.1
     */
    public void bppend(String str) {
        bppendText(str);
    }

    /**
     * Appends the given text to the text breb's current text.
     *
     * @pbrbm  str the text to bppend
     * @deprecbted As of JDK version 1.1,
     * replbced by <code>bppend(String)</code>.
     */
    @Deprecbted
    public synchronized void bppendText(String str) {
        if (peer != null) {
            insertText(str, getText().length());
        } else {
            text = text + str;
        }
    }

    /**
     * Replbces text between the indicbted stbrt bnd end positions
     * with the specified replbcement text.  The text bt the end
     * position will not be replbced.  The text bt the stbrt
     * position will be replbced (unless the stbrt position is the
     * sbme bs the end position).
     * The text position is zero-bbsed.  The inserted substring mby be
     * of b different length thbn the text it replbces.
     * <p>Note thbt pbssing <code>null</code> or inconsistent
     * pbrbmeters is invblid bnd will result in unspecified
     * behbvior.
     *
     * @pbrbm     str      the non-<code>null</code> text to use bs
     *                     the replbcement
     * @pbrbm     stbrt    the stbrt position
     * @pbrbm     end      the end position
     * @see       jbvb.bwt.TextAreb#insert
     * @since     1.1
     */
    public void replbceRbnge(String str, int stbrt, int end) {
        replbceText(str, stbrt, end);
    }

    /**
     * Replbces b rbnge of chbrbcters between
     * the indicbted stbrt bnd end positions
     * with the specified replbcement text (the text bt the end
     * position will not be replbced).
     *
     * @pbrbm  str the non-{@code null} text to use bs
     *         the replbcement
     * @pbrbm  stbrt the stbrt position
     * @pbrbm  end the end position
     * @deprecbted As of JDK version 1.1,
     * replbced by <code>replbceRbnge(String, int, int)</code>.
     */
    @Deprecbted
    public synchronized void replbceText(String str, int stbrt, int end) {
        TextArebPeer peer = (TextArebPeer)this.peer;
        if (peer != null) {
            peer.replbceRbnge(str, stbrt, end);
        } else {
            text = text.substring(0, stbrt) + str + text.substring(end);
        }
    }

    /**
     * Returns the number of rows in the text breb.
     * @return    the number of rows in the text breb
     * @see       #setRows(int)
     * @see       #getColumns()
     * @since     1.0
     */
    public int getRows() {
        return rows;
    }

    /**
     * Sets the number of rows for this text breb.
     * @pbrbm       rows   the number of rows
     * @see         #getRows()
     * @see         #setColumns(int)
     * @exception   IllegblArgumentException   if the vblue
     *                 supplied for <code>rows</code>
     *                 is less thbn <code>0</code>
     * @since       1.1
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
     * Returns the number of columns in this text breb.
     * @return    the number of columns in the text breb
     * @see       #setColumns(int)
     * @see       #getRows()
     */
    public int getColumns() {
        return columns;
    }

    /**
     * Sets the number of columns for this text breb.
     * @pbrbm       columns   the number of columns
     * @see         #getColumns()
     * @see         #setRows(int)
     * @exception   IllegblArgumentException   if the vblue
     *                 supplied for <code>columns</code>
     *                 is less thbn <code>0</code>
     * @since       1.1
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
     * Returns bn enumerbted vblue thbt indicbtes which scroll bbrs
     * the text breb uses.
     * <p>
     * The <code>TextAreb</code> clbss defines four integer constbnts
     * thbt bre used to specify which scroll bbrs bre bvbilbble.
     * <code>TextAreb</code> hbs one constructor thbt gives the
     * bpplicbtion discretion over scroll bbrs.
     *
     * @return     bn integer thbt indicbtes which scroll bbrs bre used
     * @see        jbvb.bwt.TextAreb#SCROLLBARS_BOTH
     * @see        jbvb.bwt.TextAreb#SCROLLBARS_VERTICAL_ONLY
     * @see        jbvb.bwt.TextAreb#SCROLLBARS_HORIZONTAL_ONLY
     * @see        jbvb.bwt.TextAreb#SCROLLBARS_NONE
     * @see        jbvb.bwt.TextAreb#TextAreb(jbvb.lbng.String, int, int, int)
     * @since      1.1
     */
    public int getScrollbbrVisibility() {
        return scrollbbrVisibility;
    }


    /**
     * Determines the preferred size of b text breb with the specified
     * number of rows bnd columns.
     * @pbrbm     rows   the number of rows
     * @pbrbm     columns   the number of columns
     * @return    the preferred dimensions required to displby
     *                       the text breb with the specified
     *                       number of rows bnd columns
     * @see       jbvb.bwt.Component#getPreferredSize
     * @since     1.1
     */
    public Dimension getPreferredSize(int rows, int columns) {
        return preferredSize(rows, columns);
    }

    /**
     * Determines the preferred size of the text breb with the specified
     * number of rows bnd columns.
     *
     * @pbrbm  rows the number of rows
     * @pbrbm  columns the number of columns
     * @return the preferred dimensions needed for the text breb
     * @deprecbted As of JDK version 1.1,
     * replbced by <code>getPreferredSize(int, int)</code>.
     */
    @Deprecbted
    public Dimension preferredSize(int rows, int columns) {
        synchronized (getTreeLock()) {
            TextArebPeer peer = (TextArebPeer)this.peer;
            return (peer != null) ?
                       peer.getPreferredSize(rows, columns) :
                       super.preferredSize();
        }
    }

    /**
     * Determines the preferred size of this text breb.
     * @return    the preferred dimensions needed for this text breb
     * @see       jbvb.bwt.Component#getPreferredSize
     * @since     1.1
     */
    public Dimension getPreferredSize() {
        return preferredSize();
    }

    /**
     * @deprecbted As of JDK version 1.1,
     * replbced by <code>getPreferredSize()</code>.
     */
    @Deprecbted
    public Dimension preferredSize() {
        synchronized (getTreeLock()) {
            return ((rows > 0) && (columns > 0)) ?
                        preferredSize(rows, columns) :
                        super.preferredSize();
        }
    }

    /**
     * Determines the minimum size of b text breb with the specified
     * number of rows bnd columns.
     * @pbrbm     rows   the number of rows
     * @pbrbm     columns   the number of columns
     * @return    the minimum dimensions required to displby
     *                       the text breb with the specified
     *                       number of rows bnd columns
     * @see       jbvb.bwt.Component#getMinimumSize
     * @since     1.1
     */
    public Dimension getMinimumSize(int rows, int columns) {
        return minimumSize(rows, columns);
    }

    /**
     * Determines the minimum size of the text breb with the specified
     * number of rows bnd columns.
     *
     * @pbrbm  rows the number of rows
     * @pbrbm  columns the number of columns
     * @return the minimum size for the text breb
     * @deprecbted As of JDK version 1.1,
     * replbced by <code>getMinimumSize(int, int)</code>.
     */
    @Deprecbted
    public Dimension minimumSize(int rows, int columns) {
        synchronized (getTreeLock()) {
            TextArebPeer peer = (TextArebPeer)this.peer;
            return (peer != null) ?
                       peer.getMinimumSize(rows, columns) :
                       super.minimumSize();
        }
    }

    /**
     * Determines the minimum size of this text breb.
     * @return    the preferred dimensions needed for this text breb
     * @see       jbvb.bwt.Component#getPreferredSize
     * @since     1.1
     */
    public Dimension getMinimumSize() {
        return minimumSize();
    }

    /**
     * @deprecbted As of JDK version 1.1,
     * replbced by <code>getMinimumSize()</code>.
     */
    @Deprecbted
    public Dimension minimumSize() {
        synchronized (getTreeLock()) {
            return ((rows > 0) && (columns > 0)) ?
                        minimumSize(rows, columns) :
                        super.minimumSize();
        }
    }

    /**
     * Returns b string representing the stbte of this <code>TextAreb</code>.
     * This method is intended to be used only for debugging purposes, bnd the
     * content bnd formbt of the returned string mby vbry between
     * implementbtions. The returned string mby be empty but mby not be
     * <code>null</code>.
     *
     * @return      the pbrbmeter string of this text breb
     */
    protected String pbrbmString() {
        String sbVisStr;
        switch (scrollbbrVisibility) {
            cbse SCROLLBARS_BOTH:
                sbVisStr = "both";
                brebk;
            cbse SCROLLBARS_VERTICAL_ONLY:
                sbVisStr = "verticbl-only";
                brebk;
            cbse SCROLLBARS_HORIZONTAL_ONLY:
                sbVisStr = "horizontbl-only";
                brebk;
            cbse SCROLLBARS_NONE:
                sbVisStr = "none";
                brebk;
            defbult:
                sbVisStr = "invblid displby policy";
        }

        return super.pbrbmString() + ",rows=" + rows +
            ",columns=" + columns +
          ",scrollbbrVisibility=" + sbVisStr;
    }


    /*
     * Seriblizbtion support.
     */
    /**
     * The textAreb Seriblized Dbtb Version.
     *
     * @seribl
     */
    privbte int textArebSeriblizedDbtbVersion = 2;

    /**
     * Rebd the ObjectInputStrebm.
     * @exception HebdlessException if
     * <code>GrbphicsEnvironment.isHebdless()</code> returns
     * <code>true</code>
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     */
    privbte void rebdObject(ObjectInputStrebm s)
      throws ClbssNotFoundException, IOException, HebdlessException
    {
        // HebdlessException will be thrown by TextComponent's rebdObject
        s.defbultRebdObject();

        // Mbke sure the stbte we just rebd in for columns, rows,
        // bnd scrollbbrVisibility hbs legbl vblues
        if (columns < 0) {
            columns = 0;
        }
        if (rows < 0) {
            rows = 0;
        }

        if ((scrollbbrVisibility < SCROLLBARS_BOTH) ||
            (scrollbbrVisibility > SCROLLBARS_NONE)) {
            this.scrollbbrVisibility = SCROLLBARS_BOTH;
        }

        if (textArebSeriblizedDbtbVersion < 2) {
            setFocusTrbversblKeys(KeybobrdFocusMbnbger.FORWARD_TRAVERSAL_KEYS,
                                  forwbrdTrbversblKeys);
            setFocusTrbversblKeys(KeybobrdFocusMbnbger.BACKWARD_TRAVERSAL_KEYS,
                                  bbckwbrdTrbversblKeys);
        }
    }


/////////////////
// Accessibility support
////////////////


    /**
     * Returns the <code>AccessibleContext</code> bssocibted with
     * this <code>TextAreb</code>. For text brebs, the
     * <code>AccessibleContext</code> tbkes the form of bn
     * <code>AccessibleAWTTextAreb</code>.
     * A new <code>AccessibleAWTTextAreb</code> instbnce is crebted if necessbry.
     *
     * @return bn <code>AccessibleAWTTextAreb</code> thbt serves bs the
     *         <code>AccessibleContext</code> of this <code>TextAreb</code>
     * @since 1.3
     */
    public AccessibleContext getAccessibleContext() {
        if (bccessibleContext == null) {
            bccessibleContext = new AccessibleAWTTextAreb();
        }
        return bccessibleContext;
    }

    /**
     * This clbss implements bccessibility support for the
     * <code>TextAreb</code> clbss.  It provides bn implementbtion of the
     * Jbvb Accessibility API bppropribte to text breb user-interfbce elements.
     * @since 1.3
     */
    protected clbss AccessibleAWTTextAreb extends AccessibleAWTTextComponent
    {
        /*
         * JDK 1.3 seriblVersionUID
         */
        privbte stbtic finbl long seriblVersionUID = 3472827823632144419L;

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


}
