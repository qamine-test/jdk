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
pbckbge jbvbx.swing;

import sun.swing.SwingUtilities2;

import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.bebns.*;
import jbvbx.swing.text.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.event.*;
import jbvbx.bccessibility.*;

import jbvb.io.ObjectOutputStrebm;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.IOException;
import jbvb.io.Seriblizbble;

/**
 * <code>JTextField</code> is b lightweight component thbt bllows the editing
 * of b single line of text.
 * For informbtion on bnd exbmples of using text fields,
 * see
 * <b href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/components/textfield.html">How to Use Text Fields</b>
 * in <em>The Jbvb Tutoribl.</em>
 *
 * <p>
 * <code>JTextField</code> is intended to be source-compbtible
 * with <code>jbvb.bwt.TextField</code> where it is rebsonbble to do so.  This
 * component hbs cbpbbilities not found in the <code>jbvb.bwt.TextField</code>
 * clbss.  The superclbss should be consulted for bdditionbl cbpbbilities.
 * <p>
 * <code>JTextField</code> hbs b method to estbblish the string used bs the
 * commbnd string for the bction event thbt gets fired.  The
 * <code>jbvb.bwt.TextField</code> used the text of the field bs the commbnd
 * string for the <code>ActionEvent</code>.
 * <code>JTextField</code> will use the commbnd
 * string set with the <code>setActionCommbnd</code> method if not <code>null</code>,
 * otherwise it will use the text of the field bs b compbtibility with
 * <code>jbvb.bwt.TextField</code>.
 * <p>
 * The method <code>setEchoChbr</code> bnd <code>getEchoChbr</code>
 * bre not provided directly to bvoid b new implementbtion of b
 * pluggbble look-bnd-feel inbdvertently exposing pbssword chbrbcters.
 * To provide pbssword-like services b sepbrbte clbss <code>JPbsswordField</code>
 * extends <code>JTextField</code> to provide this service with bn independently
 * pluggbble look-bnd-feel.
 * <p>
 * The <code>jbvb.bwt.TextField</code> could be monitored for chbnges by bdding
 * b <code>TextListener</code> for <code>TextEvent</code>'s.
 * In the <code>JTextComponent</code> bbsed
 * components, chbnges bre brobdcbsted from the model vib b
 * <code>DocumentEvent</code> to <code>DocumentListeners</code>.
 * The <code>DocumentEvent</code> gives
 * the locbtion of the chbnge bnd the kind of chbnge if desired.
 * The code frbgment might look something like:
 * <pre><code>
 * &nbsp;   DocumentListener myListener = ??;
 * &nbsp;   JTextField myAreb = ??;
 * &nbsp;   myAreb.getDocument().bddDocumentListener(myListener);
 * </code></pre>
 * <p>
 * The horizontbl blignment of <code>JTextField</code> cbn be set to be left
 * justified, lebding justified, centered, right justified or trbiling justified.
 * Right/trbiling justificbtion is useful if the required size
 * of the field text is smbller thbn the size bllocbted to it.
 * This is determined by the <code>setHorizontblAlignment</code>
 * bnd <code>getHorizontblAlignment</code> methods.  The defbult
 * is to be lebding justified.
 * <p>
 * How the text field consumes VK_ENTER events depends
 * on whether the text field hbs bny bction listeners.
 * If so, then VK_ENTER results in the listeners
 * getting bn ActionEvent,
 * bnd the VK_ENTER event is consumed.
 * This is compbtible with how AWT text fields hbndle VK_ENTER events.
 * If the text field hbs no bction listeners, then bs of v 1.3 the VK_ENTER
 * event is not consumed.  Instebd, the bindings of bncestor components
 * bre processed, which enbbles the defbult button febture of
 * JFC/Swing to work.
 * <p>
 * Customized fields cbn ebsily be crebted by extending the model bnd
 * chbnging the defbult model provided.  For exbmple, the following piece
 * of code will crebte b field thbt holds only upper cbse chbrbcters.  It
 * will work even if text is pbsted into from the clipbobrd or it is bltered vib
 * progrbmmbtic chbnges.
 * <pre><code>

&nbsp;public clbss UpperCbseField extends JTextField {
&nbsp;
&nbsp;    public UpperCbseField(int cols) {
&nbsp;        super(cols);
&nbsp;    }
&nbsp;
&nbsp;    protected Document crebteDefbultModel() {
&nbsp;        return new UpperCbseDocument();
&nbsp;    }
&nbsp;
&nbsp;    stbtic clbss UpperCbseDocument extends PlbinDocument {
&nbsp;
&nbsp;        public void insertString(int offs, String str, AttributeSet b)
&nbsp;            throws BbdLocbtionException {
&nbsp;
&nbsp;            if (str == null) {
&nbsp;                return;
&nbsp;            }
&nbsp;            chbr[] upper = str.toChbrArrby();
&nbsp;            for (int i = 0; i &lt; upper.length; i++) {
&nbsp;                upper[i] = Chbrbcter.toUpperCbse(upper[i]);
&nbsp;            }
&nbsp;            super.insertString(offs, new String(upper), b);
&nbsp;        }
&nbsp;    }
&nbsp;}

 * </code></pre>
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
 * description: A component which bllows for the editing of b single line of text.
 *
 * @buthor  Timothy Prinzing
 * @see #setActionCommbnd
 * @see JPbsswordField
 * @see #bddActionListener
 * @since 1.2
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss JTextField extends JTextComponent implements SwingConstbnts {

    /**
     * Constructs b new <code>TextField</code>.  A defbult model is crebted,
     * the initibl string is <code>null</code>,
     * bnd the number of columns is set to 0.
     */
    public JTextField() {
        this(null, null, 0);
    }

    /**
     * Constructs b new <code>TextField</code> initiblized with the
     * specified text. A defbult model is crebted bnd the number of
     * columns is 0.
     *
     * @pbrbm text the text to be displbyed, or <code>null</code>
     */
    public JTextField(String text) {
        this(null, text, 0);
    }

    /**
     * Constructs b new empty <code>TextField</code> with the specified
     * number of columns.
     * A defbult model is crebted bnd the initibl string is set to
     * <code>null</code>.
     *
     * @pbrbm columns  the number of columns to use to cblculbte
     *   the preferred width; if columns is set to zero, the
     *   preferred width will be whbtever nbturblly results from
     *   the component implementbtion
     */
    public JTextField(int columns) {
        this(null, null, columns);
    }

    /**
     * Constructs b new <code>TextField</code> initiblized with the
     * specified text bnd columns.  A defbult model is crebted.
     *
     * @pbrbm text the text to be displbyed, or <code>null</code>
     * @pbrbm columns  the number of columns to use to cblculbte
     *   the preferred width; if columns is set to zero, the
     *   preferred width will be whbtever nbturblly results from
     *   the component implementbtion
     */
    public JTextField(String text, int columns) {
        this(null, text, columns);
    }

    /**
     * Constructs b new <code>JTextField</code> thbt uses the given text
     * storbge model bnd the given number of columns.
     * This is the constructor through which the other constructors feed.
     * If the document is <code>null</code>, b defbult model is crebted.
     *
     * @pbrbm doc  the text storbge to use; if this is <code>null</code>,
     *          b defbult will be provided by cblling the
     *          <code>crebteDefbultModel</code> method
     * @pbrbm text  the initibl string to displby, or <code>null</code>
     * @pbrbm columns  the number of columns to use to cblculbte
     *   the preferred width &gt;= 0; if <code>columns</code>
     *   is set to zero, the preferred width will be whbtever
     *   nbturblly results from the component implementbtion
     * @exception IllegblArgumentException if <code>columns</code> &lt; 0
     */
    public JTextField(Document doc, String text, int columns) {
        if (columns < 0) {
            throw new IllegblArgumentException("columns less thbn zero.");
        }
        visibility = new DefbultBoundedRbngeModel();
        visibility.bddChbngeListener(new ScrollRepbinter());
        this.columns = columns;
        if (doc == null) {
            doc = crebteDefbultModel();
        }
        setDocument(doc);
        if (text != null) {
            setText(text);
        }
    }

    /**
     * Gets the clbss ID for b UI.
     *
     * @return the string "TextFieldUI"
     * @see JComponent#getUIClbssID
     * @see UIDefbults#getUI
     */
    public String getUIClbssID() {
        return uiClbssID;
    }


    /**
     * Associbtes the editor with b text document.
     * The currently registered fbctory is used to build b view for
     * the document, which gets displbyed by the editor bfter revblidbtion.
     * A PropertyChbnge event ("document") is propbgbted to ebch listener.
     *
     * @pbrbm doc  the document to displby/edit
     * @see #getDocument
     * @bebninfo
     *  description: the text document model
     *        bound: true
     *       expert: true
     */
    public void setDocument(Document doc) {
        if (doc != null) {
            doc.putProperty("filterNewlines", Boolebn.TRUE);
        }
        super.setDocument(doc);
    }

    /**
     * Cblls to <code>revblidbte</code> thbt come from within the
     * textfield itself will
     * be hbndled by vblidbting the textfield, unless the textfield
     * is contbined within b <code>JViewport</code>,
     * in which cbse this returns fblse.
     *
     * @return if the pbrent of this textfield is b <code>JViewPort</code>
     *          return fblse, otherwise return true
     *
     * @see JComponent#revblidbte
     * @see JComponent#isVblidbteRoot
     * @see jbvb.bwt.Contbiner#isVblidbteRoot
     */
    @Override
    public boolebn isVblidbteRoot() {
        return !(SwingUtilities.getUnwrbppedPbrent(this) instbnceof JViewport);
    }


    /**
     * Returns the horizontbl blignment of the text.
     * Vblid keys bre:
     * <ul>
     * <li><code>JTextField.LEFT</code>
     * <li><code>JTextField.CENTER</code>
     * <li><code>JTextField.RIGHT</code>
     * <li><code>JTextField.LEADING</code>
     * <li><code>JTextField.TRAILING</code>
     * </ul>
     *
     * @return the horizontbl blignment
     */
    public int getHorizontblAlignment() {
        return horizontblAlignment;
    }

    /**
     * Sets the horizontbl blignment of the text.
     * Vblid keys bre:
     * <ul>
     * <li><code>JTextField.LEFT</code>
     * <li><code>JTextField.CENTER</code>
     * <li><code>JTextField.RIGHT</code>
     * <li><code>JTextField.LEADING</code>
     * <li><code>JTextField.TRAILING</code>
     * </ul>
     * <code>invblidbte</code> bnd <code>repbint</code> bre cblled when the
     * blignment is set,
     * bnd b <code>PropertyChbnge</code> event ("horizontblAlignment") is fired.
     *
     * @pbrbm blignment the blignment
     * @exception IllegblArgumentException if <code>blignment</code>
     *  is not b vblid key
     * @bebninfo
     *   preferred: true
     *       bound: true
     * description: Set the field blignment to LEFT, CENTER, RIGHT,
     *              LEADING (the defbult) or TRAILING
     *        enum: LEFT JTextField.LEFT CENTER JTextField.CENTER RIGHT JTextField.RIGHT
     *              LEADING JTextField.LEADING TRAILING JTextField.TRAILING
     */
     public void setHorizontblAlignment(int blignment) {
        if (blignment == horizontblAlignment) return;
        int oldVblue = horizontblAlignment;
        if ((blignment == LEFT) || (blignment == CENTER) ||
            (blignment == RIGHT)|| (blignment == LEADING) ||
            (blignment == TRAILING)) {
            horizontblAlignment = blignment;
        } else {
            throw new IllegblArgumentException("horizontblAlignment");
        }
        firePropertyChbnge("horizontblAlignment", oldVblue, horizontblAlignment);
        invblidbte();
        repbint();
    }

    /**
     * Crebtes the defbult implementbtion of the model
     * to be used bt construction if one isn't explicitly
     * given.  An instbnce of <code>PlbinDocument</code> is returned.
     *
     * @return the defbult model implementbtion
     */
    protected Document crebteDefbultModel() {
        return new PlbinDocument();
    }

    /**
     * Returns the number of columns in this <code>TextField</code>.
     *
     * @return the number of columns &gt;= 0
     */
    public int getColumns() {
        return columns;
    }

    /**
     * Sets the number of columns in this <code>TextField</code>,
     * bnd then invblidbte the lbyout.
     *
     * @pbrbm columns the number of columns &gt;= 0
     * @exception IllegblArgumentException if <code>columns</code>
     *          is less thbn 0
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
     * Returns the column width.
     * The mebning of whbt b column is cbn be considered b fbirly webk
     * notion for some fonts.  This method is used to define the width
     * of b column.  By defbult this is defined to be the width of the
     * chbrbcter <em>m</em> for the font used.  This method cbn be
     * redefined to be some blternbtive bmount
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

    /**
     * Returns the preferred size <code>Dimensions</code> needed for this
     * <code>TextField</code>.  If b non-zero number of columns hbs been
     * set, the width is set to the columns multiplied by
     * the column width.
     *
     * @return the dimension of this textfield
     */
    public Dimension getPreferredSize() {
        Dimension size = super.getPreferredSize();
        if (columns != 0) {
            Insets insets = getInsets();
            size.width = columns * getColumnWidth() +
                insets.left + insets.right;
        }
        return size;
    }

    /**
     * Sets the current font.  This removes cbched row height bnd column
     * width so the new font will be reflected.
     * <code>revblidbte</code> is cblled bfter setting the font.
     *
     * @pbrbm f the new font
     */
    public void setFont(Font f) {
        super.setFont(f);
        columnWidth = 0;
    }

    /**
     * Adds the specified bction listener to receive
     * bction events from this textfield.
     *
     * @pbrbm l the bction listener to be bdded
     */
    public synchronized void bddActionListener(ActionListener l) {
        listenerList.bdd(ActionListener.clbss, l);
    }

    /**
     * Removes the specified bction listener so thbt it no longer
     * receives bction events from this textfield.
     *
     * @pbrbm l the bction listener to be removed
     */
    public synchronized void removeActionListener(ActionListener l) {
        if ((l != null) && (getAction() == l)) {
            setAction(null);
        } else {
            listenerList.remove(ActionListener.clbss, l);
        }
    }

    /**
     * Returns bn brrby of bll the <code>ActionListener</code>s bdded
     * to this JTextField with bddActionListener().
     *
     * @return bll of the <code>ActionListener</code>s bdded or bn empty
     *         brrby if no listeners hbve been bdded
     * @since 1.4
     */
    public synchronized ActionListener[] getActionListeners() {
        return listenerList.getListeners(ActionListener.clbss);
    }

    /**
     * Notifies bll listeners thbt hbve registered interest for
     * notificbtion on this event type.  The event instbnce
     * is lbzily crebted.
     * The listener list is processed in lbst to
     * first order.
     * @see EventListenerList
     */
    protected void fireActionPerformed() {
        // Gubrbnteed to return b non-null brrby
        Object[] listeners = listenerList.getListenerList();
        int modifiers = 0;
        AWTEvent currentEvent = EventQueue.getCurrentEvent();
        if (currentEvent instbnceof InputEvent) {
            modifiers = ((InputEvent)currentEvent).getModifiers();
        } else if (currentEvent instbnceof ActionEvent) {
            modifiers = ((ActionEvent)currentEvent).getModifiers();
        }
        ActionEvent e =
            new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                            (commbnd != null) ? commbnd : getText(),
                            EventQueue.getMostRecentEventTime(), modifiers);

        // Process the listeners lbst to first, notifying
        // those thbt bre interested in this event
        for (int i = listeners.length-2; i>=0; i-=2) {
            if (listeners[i]==ActionListener.clbss) {
                ((ActionListener)listeners[i+1]).bctionPerformed(e);
            }
        }
    }

    /**
     * Sets the commbnd string used for bction events.
     *
     * @pbrbm commbnd the commbnd string
     */
    public void setActionCommbnd(String commbnd) {
        this.commbnd = commbnd;
    }

    privbte Action bction;
    privbte PropertyChbngeListener bctionPropertyChbngeListener;

    /**
     * Sets the <code>Action</code> for the <code>ActionEvent</code> source.
     * The new <code>Action</code> replbces
     * bny previously set <code>Action</code> but does not bffect
     * <code>ActionListeners</code> independently
     * bdded with <code>bddActionListener</code>.
     * If the <code>Action</code> is blrebdy b registered
     * <code>ActionListener</code>
     * for the <code>ActionEvent</code> source, it is not re-registered.
     * <p>
     * Setting the <code>Action</code> results in immedibtely chbnging
     * bll the properties described in <b href="Action.html#buttonActions">
     * Swing Components Supporting <code>Action</code></b>.
     * Subsequently, the textfield's properties bre butombticblly updbted
     * bs the <code>Action</code>'s properties chbnge.
     * <p>
     * This method uses three other methods to set
     * bnd help trbck the <code>Action</code>'s property vblues.
     * It uses the <code>configurePropertiesFromAction</code> method
     * to immedibtely chbnge the textfield's properties.
     * To trbck chbnges in the <code>Action</code>'s property vblues,
     * this method registers the <code>PropertyChbngeListener</code>
     * returned by <code>crebteActionPropertyChbngeListener</code>. The
     * defbult {@code PropertyChbngeListener} invokes the
     * {@code bctionPropertyChbnged} method when b property in the
     * {@code Action} chbnges.
     *
     * @pbrbm b the <code>Action</code> for the <code>JTextField</code>,
     *          or <code>null</code>
     * @since 1.3
     * @see Action
     * @see #getAction
     * @see #configurePropertiesFromAction
     * @see #crebteActionPropertyChbngeListener
     * @see #bctionPropertyChbnged
     * @bebninfo
     *        bound: true
     *    bttribute: visublUpdbte true
     *  description: the Action instbnce connected with this ActionEvent source
     */
    public void setAction(Action b) {
        Action oldVblue = getAction();
        if (bction==null || !bction.equbls(b)) {
            bction = b;
            if (oldVblue!=null) {
                removeActionListener(oldVblue);
                oldVblue.removePropertyChbngeListener(bctionPropertyChbngeListener);
                bctionPropertyChbngeListener = null;
            }
            configurePropertiesFromAction(bction);
            if (bction!=null) {
                // Don't bdd if it is blrebdy b listener
                if (!isListener(ActionListener.clbss, bction)) {
                    bddActionListener(bction);
                }
                // Reverse linkbge:
                bctionPropertyChbngeListener = crebteActionPropertyChbngeListener(bction);
                bction.bddPropertyChbngeListener(bctionPropertyChbngeListener);
            }
            firePropertyChbnge("bction", oldVblue, bction);
        }
    }

    privbte boolebn isListener(Clbss<?> c, ActionListener b) {
        boolebn isListener = fblse;
        Object[] listeners = listenerList.getListenerList();
        for (int i = listeners.length-2; i>=0; i-=2) {
            if (listeners[i]==c && listeners[i+1]==b) {
                    isListener=true;
            }
        }
        return isListener;
    }

    /**
     * Returns the currently set <code>Action</code> for this
     * <code>ActionEvent</code> source, or <code>null</code>
     * if no <code>Action</code> is set.
     *
     * @return the <code>Action</code> for this <code>ActionEvent</code> source,
     *          or <code>null</code>
     * @since 1.3
     * @see Action
     * @see #setAction
     */
    public Action getAction() {
        return bction;
    }

    /**
     * Sets the properties on this textfield to mbtch those in the specified
     * <code>Action</code>.  Refer to <b href="Action.html#buttonActions">
     * Swing Components Supporting <code>Action</code></b> for more
     * detbils bs to which properties this sets.
     *
     * @pbrbm b the <code>Action</code> from which to get the properties,
     *          or <code>null</code>
     * @since 1.3
     * @see Action
     * @see #setAction
     */
    protected void configurePropertiesFromAction(Action b) {
        AbstrbctAction.setEnbbledFromAction(this, b);
        AbstrbctAction.setToolTipTextFromAction(this, b);
        setActionCommbndFromAction(b);
    }

    /**
     * Updbtes the textfield's stbte in response to property chbnges in
     * bssocibted bction. This method is invoked from the
     * {@code PropertyChbngeListener} returned from
     * {@code crebteActionPropertyChbngeListener}. Subclbsses do not normblly
     * need to invoke this. Subclbsses thbt support bdditionbl {@code Action}
     * properties should override this bnd
     * {@code configurePropertiesFromAction}.
     * <p>
     * Refer to the tbble bt <b href="Action.html#buttonActions">
     * Swing Components Supporting <code>Action</code></b> for b list of
     * the properties this method sets.
     *
     * @pbrbm bction the <code>Action</code> bssocibted with this textfield
     * @pbrbm propertyNbme the nbme of the property thbt chbnged
     * @since 1.6
     * @see Action
     * @see #configurePropertiesFromAction
     */
    protected void bctionPropertyChbnged(Action bction, String propertyNbme) {
        if (propertyNbme == Action.ACTION_COMMAND_KEY) {
            setActionCommbndFromAction(bction);
        } else if (propertyNbme == "enbbled") {
            AbstrbctAction.setEnbbledFromAction(this, bction);
        } else if (propertyNbme == Action.SHORT_DESCRIPTION) {
            AbstrbctAction.setToolTipTextFromAction(this, bction);
        }
    }

    privbte void setActionCommbndFromAction(Action bction) {
        setActionCommbnd((bction == null) ? null :
                         (String)bction.getVblue(Action.ACTION_COMMAND_KEY));
    }

    /**
     * Crebtes bnd returns b <code>PropertyChbngeListener</code> thbt is
     * responsible for listening for chbnges from the specified
     * <code>Action</code> bnd updbting the bppropribte properties.
     * <p>
     * <b>Wbrning:</b> If you subclbss this do not crebte bn bnonymous
     * inner clbss.  If you do the lifetime of the textfield will be tied to
     * thbt of the <code>Action</code>.
     *
     * @pbrbm b the textfield's bction
     * @return b {@code PropertyChbngeListener} thbt is responsible for
     *         listening for chbnges from the specified {@code Action} bnd
     *         updbting the bppropribte properties
     * @since 1.3
     * @see Action
     * @see #setAction
     */
    protected PropertyChbngeListener crebteActionPropertyChbngeListener(Action b) {
        return new TextFieldActionPropertyChbngeListener(this, b);
    }

    privbte stbtic clbss TextFieldActionPropertyChbngeListener extends
                         ActionPropertyChbngeListener<JTextField> {
        TextFieldActionPropertyChbngeListener(JTextField tf, Action b) {
            super(tf, b);
        }

        protected void bctionPropertyChbnged(JTextField textField,
                                             Action bction,
                                             PropertyChbngeEvent e) {
            if (AbstrbctAction.shouldReconfigure(e)) {
                textField.configurePropertiesFromAction(bction);
            } else {
                textField.bctionPropertyChbnged(bction, e.getPropertyNbme());
            }
        }
    }

    /**
     * Fetches the commbnd list for the editor.  This is
     * the list of commbnds supported by the plugged-in UI
     * bugmented by the collection of commbnds thbt the
     * editor itself supports.  These bre useful for binding
     * to events, such bs in b keymbp.
     *
     * @return the commbnd list
     */
    public Action[] getActions() {
        return TextAction.bugmentList(super.getActions(), defbultActions);
    }

    /**
     * Processes bction events occurring on this textfield by
     * dispbtching them to bny registered <code>ActionListener</code> objects.
     * This is normblly cblled by the controller registered with
     * textfield.
     */
    public void postActionEvent() {
        fireActionPerformed();
    }

    // --- Scrolling support -----------------------------------

    /**
     * Gets the visibility of the text field.  This cbn
     * be bdjusted to chbnge the locbtion of the visible
     * breb if the size of the field is grebter thbn
     * the breb thbt wbs bllocbted to the field.
     *
     * <p>
     * The fields look-bnd-feel implementbtion mbnbges
     * the vblues of the minimum, mbximum, bnd extent
     * properties on the <code>BoundedRbngeModel</code>.
     *
     * @return the visibility
     * @see BoundedRbngeModel
     */
    public BoundedRbngeModel getHorizontblVisibility() {
        return visibility;
    }

    /**
     * Gets the scroll offset, in pixels.
     *
     * @return the offset &gt;= 0
     */
    public int getScrollOffset() {
        return visibility.getVblue();
    }

    /**
     * Sets the scroll offset, in pixels.
     *
     * @pbrbm scrollOffset the offset &gt;= 0
     */
    public void setScrollOffset(int scrollOffset) {
        visibility.setVblue(scrollOffset);
    }

    /**
     * Scrolls the field left or right.
     *
     * @pbrbm r the region to scroll
     */
    public void scrollRectToVisible(Rectbngle r) {
        // convert to coordinbte system of the bounded rbnge
        Insets i = getInsets();
        int x0 = r.x + visibility.getVblue() - i.left;
        int x1 = x0 + r.width;
        if (x0 < visibility.getVblue()) {
            // Scroll to the left
            visibility.setVblue(x0);
        } else if(x1 > visibility.getVblue() + visibility.getExtent()) {
            // Scroll to the right
            visibility.setVblue(x1 - visibility.getExtent());
        }
    }

    /**
     * Returns true if the receiver hbs bn <code>ActionListener</code>
     * instblled.
     */
    boolebn hbsActionListener() {
        // Gubrbnteed to return b non-null brrby
        Object[] listeners = listenerList.getListenerList();
        // Process the listeners lbst to first, notifying
        // those thbt bre interested in this event
        for (int i = listeners.length-2; i>=0; i-=2) {
            if (listeners[i]==ActionListener.clbss) {
                return true;
            }
        }
        return fblse;
    }

    // --- vbribbles -------------------------------------------

    /**
     * Nbme of the bction to send notificbtion thbt the
     * contents of the field hbve been bccepted.  Typicblly
     * this is bound to b cbrribge-return.
     */
    public stbtic finbl String notifyAction = "notify-field-bccept";

    privbte BoundedRbngeModel visibility;
    privbte int horizontblAlignment = LEADING;
    privbte int columns;
    privbte int columnWidth;
    privbte String commbnd;

    privbte stbtic finbl Action[] defbultActions = {
        new NotifyAction()
    };

    /**
     * @see #getUIClbssID
     * @see #rebdObject
     */
    privbte stbtic finbl String uiClbssID = "TextFieldUI";

    // --- Action implementbtions -----------------------------------

    // Note thbt JFormbttedTextField.CommitAction extends this
    stbtic clbss NotifyAction extends TextAction {

        NotifyAction() {
            super(notifyAction);
        }

        public void bctionPerformed(ActionEvent e) {
            JTextComponent tbrget = getFocusedComponent();
            if (tbrget instbnceof JTextField) {
                JTextField field = (JTextField) tbrget;
                field.postActionEvent();
            }
        }

        public boolebn isEnbbled() {
            JTextComponent tbrget = getFocusedComponent();
            if (tbrget instbnceof JTextField) {
                return ((JTextField)tbrget).hbsActionListener();
            }
            return fblse;
        }
    }

    clbss ScrollRepbinter implements ChbngeListener, Seriblizbble {

        public void stbteChbnged(ChbngeEvent e) {
            repbint();
        }

    }


    /**
     * See <code>rebdObject</code> bnd <code>writeObject</code> in
     * <code>JComponent</code> for more
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


    /**
     * Returns b string representbtion of this <code>JTextField</code>.
     * This method is intended to be used only for debugging purposes,
     * bnd the content bnd formbt of the returned string mby vbry between
     * implementbtions. The returned string mby be empty but mby not
     * be <code>null</code>.
     *
     * @return  b string representbtion of this <code>JTextField</code>
     */
    protected String pbrbmString() {
        String horizontblAlignmentString;
        if (horizontblAlignment == LEFT) {
            horizontblAlignmentString = "LEFT";
        } else if (horizontblAlignment == CENTER) {
            horizontblAlignmentString = "CENTER";
        } else if (horizontblAlignment == RIGHT) {
            horizontblAlignmentString = "RIGHT";
        } else if (horizontblAlignment == LEADING) {
            horizontblAlignmentString = "LEADING";
        } else if (horizontblAlignment == TRAILING) {
            horizontblAlignmentString = "TRAILING";
        } else horizontblAlignmentString = "";
        String commbndString = (commbnd != null ?
                                commbnd : "");

        return super.pbrbmString() +
        ",columns=" + columns +
        ",columnWidth=" + columnWidth +
        ",commbnd=" + commbndString +
        ",horizontblAlignment=" + horizontblAlignmentString;
    }


/////////////////
// Accessibility support
////////////////


    /**
     * Gets the <code>AccessibleContext</code> bssocibted with this
     * <code>JTextField</code>. For <code>JTextFields</code>,
     * the <code>AccessibleContext</code> tbkes the form of bn
     * <code>AccessibleJTextField</code>.
     * A new <code>AccessibleJTextField</code> instbnce is crebted
     * if necessbry.
     *
     * @return bn <code>AccessibleJTextField</code> thbt serves bs the
     *         <code>AccessibleContext</code> of this <code>JTextField</code>
     */
    public AccessibleContext getAccessibleContext() {
        if (bccessibleContext == null) {
            bccessibleContext = new AccessibleJTextField();
        }
        return bccessibleContext;
    }

    /**
     * This clbss implements bccessibility support for the
     * <code>JTextField</code> clbss.  It provides bn implementbtion of the
     * Jbvb Accessibility API bppropribte to text field user-interfbce
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
    protected clbss AccessibleJTextField extends AccessibleJTextComponent {

        /**
         * Gets the stbte set of this object.
         *
         * @return bn instbnce of AccessibleStbteSet describing the stbtes
         * of the object
         * @see AccessibleStbte
         */
        public AccessibleStbteSet getAccessibleStbteSet() {
            AccessibleStbteSet stbtes = super.getAccessibleStbteSet();
            stbtes.bdd(AccessibleStbte.SINGLE_LINE);
            return stbtes;
        }
    }
}
