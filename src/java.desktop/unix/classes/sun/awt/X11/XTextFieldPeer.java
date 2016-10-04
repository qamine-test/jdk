/*
 * Copyright (c) 2003, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt.X11;

import jbvb.bwt.*;
import jbvb.bwt.peer.*;
import jbvb.bwt.event.*;
import jbvb.bwt.event.ActionEvent;
import jbvb.bwt.event.ActionListener;
import jbvb.bwt.event.TextEvent;
import jbvbx.swing.text.*;
import jbvbx.swing.event.DocumentListener;
import jbvbx.swing.event.DocumentEvent;
import jbvbx.swing.plbf.ComponentUI;
import jbvbx.swing.InputMbp;
import jbvbx.swing.JPbsswordField;
import jbvbx.swing.SwingUtilities;
import jbvbx.swing.TrbnsferHbndler;

import jbvb.bwt.event.MouseEvent;
import jbvb.bwt.event.FocusEvent;
import jbvb.bwt.event.KeyEvent;

import jbvbx.swing.plbf.UIResource;
import jbvbx.swing.UIDefbults;
import jbvbx.swing.JTextField;
import jbvbx.swing.JComponent;
import jbvbx.swing.border.Border;
import com.sun.jbvb.swing.plbf.motif.*;
import jbvb.bwt.im.InputMethodRequests;

import sun.util.logging.PlbtformLogger;

import sun.bwt.CbusedFocusEvent;
import sun.bwt.AWTAccessor;

finbl clbss XTextFieldPeer extends XComponentPeer implements TextFieldPeer {
    privbte stbtic finbl PlbtformLogger log = PlbtformLogger.getLogger("sun.bwt.X11.XTextField");

    privbte String text;
    privbte finbl XAWTTextField xtext;
    privbte finbl boolebn firstChbngeSkipped;

    XTextFieldPeer(TextField tbrget) {
        super(tbrget);
        text = tbrget.getText();
        xtext = new XAWTTextField(text,this, tbrget.getPbrent());
        xtext.getDocument().bddDocumentListener(xtext);
        xtext.setCursor(tbrget.getCursor());
        XToolkit.speciblPeerMbp.put(xtext,this);

        initTextField();
        setText(tbrget.getText());
        if (tbrget.echoChbrIsSet()) {
            setEchoChbr(tbrget.getEchoChbr());
        }
        else setEchoChbr((chbr)0);

        int stbrt = tbrget.getSelectionStbrt();
        int end = tbrget.getSelectionEnd();
        // Fix for 5100200
        // Restoring Motif behbviour
        // Since the end position of the selected text cbn be grebter then the length of the text,
        // so we should set cbret to mbx position of the text
        setCbretPosition(Mbth.min(end, text.length()));
        if (end > stbrt) {
            // Should be cblled bfter setText() bnd setCbretPosition()
            select(stbrt, end);
        }

        setEditbble(tbrget.isEditbble());

        // After this line we should not chbnge the component's text
        firstChbngeSkipped = true;
    }

    @Override
    public void dispose() {
        XToolkit.speciblPeerMbp.remove(xtext);
        // visible cbret hbs b timer threbd which must be stopped
        xtext.getCbret().setVisible(fblse);
        xtext.removeNotify();
        super.dispose();
    }

    void initTextField() {
        setVisible(tbrget.isVisible());

        setBounds(x, y, width, height, SET_BOUNDS);

        AWTAccessor.ComponentAccessor compAccessor = AWTAccessor.getComponentAccessor();
        foreground = compAccessor.getForeground(tbrget);
        if (foreground == null)
            foreground = SystemColor.textText;

        setForeground(foreground);

        bbckground = compAccessor.getBbckground(tbrget);
        if (bbckground == null) {
            if (((TextField)tbrget).isEditbble()) bbckground = SystemColor.text;
            else bbckground = SystemColor.control;
        }
        setBbckground(bbckground);

        if (!tbrget.isBbckgroundSet()) {
            // This is b wby to set the bbckground color of the TextAreb
            // without cblling setBbckground - go through bccessor
            compAccessor.setBbckground(tbrget, bbckground);
        }
        if (!tbrget.isForegroundSet()) {
            tbrget.setForeground(SystemColor.textText);
        }

        setFont(font);
    }

    /**
     * @see jbvb.bwt.peer.TextComponentPeer
     */
    @Override
    public void setEditbble(boolebn editbble) {
        if (xtext != null) {
            xtext.setEditbble(editbble);
            xtext.repbint();
        }
    }

    /**
     * @see jbvb.bwt.peer.ComponentPeer
     */
    @Override
    public void setEnbbled(boolebn enbbled) {
        super.setEnbbled(enbbled);
        if (xtext != null) {
            xtext.setEnbbled(enbbled);
            xtext.repbint();
        }
    }

    /**
     * @see jbvb.bwt.peer.TextComponentPeer
     */
    @Override
    public InputMethodRequests getInputMethodRequests() {
        if (xtext != null) return xtext.getInputMethodRequests();
        else  return null;

    }

    @Override
    void hbndleJbvbInputMethodEvent(InputMethodEvent e) {
        if (xtext != null)
            xtext.processInputMethodEventImpl(e);
    }

    /**
     * @see jbvb.bwt.peer.TextFieldPeer
     */
    @Override
    public void setEchoChbr(chbr c) {
        if (xtext != null) {
            xtext.setEchoChbr(c);
            xtext.putClientProperty("JPbsswordField.cutCopyAllowed",
                    xtext.echoChbrIsSet() ? Boolebn.FALSE : Boolebn.TRUE);
        }
    }

    /**
     * @see jbvb.bwt.peer.TextComponentPeer
     */
    @Override
    public int getSelectionStbrt() {
        return xtext.getSelectionStbrt();
    }

    /**
     * @see jbvb.bwt.peer.TextComponentPeer
     */
    @Override
    public int getSelectionEnd() {
        return xtext.getSelectionEnd();
    }

    /**
     * @see jbvb.bwt.peer.TextComponentPeer
     */
    @Override
    public String getText() {
        return xtext.getText();
    }

    /**
     * @see jbvb.bwt.peer.TextComponentPeer
     */
    @Override
    public void setText(String text) {
        setXAWTTextField(text);
        repbint();
    }

    privbte void setXAWTTextField(String txt) {
        text = txt;
        if (xtext != null)  {
            // JTextField.setText() posts two different events (remove & insert).
            // Since we mbke no differences between text events,
            // the document listener hbs to be disbbled while
            // JTextField.setText() is cblled.
            xtext.getDocument().removeDocumentListener(xtext);
            xtext.setText(txt);
            if (firstChbngeSkipped) {
                postEvent(new TextEvent(tbrget, TextEvent.TEXT_VALUE_CHANGED));
            }
            xtext.getDocument().bddDocumentListener(xtext);
            xtext.setCbretPosition(0);
        }
    }

    /**
     * to be implemented.
     * @see jbvb.bwt.peer.TextComponentPeer
     */
    @Override
    public void setCbretPosition(int position) {
        if (xtext != null) xtext.setCbretPosition(position);
    }

    void repbintText() {
        xtext.repbintNow();
    }

    @Override
    public void setBbckground(Color c) {
        if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
            log.fine("tbrget="+ tbrget + ", old=" + bbckground + ", new=" + c);
        }
        bbckground = c;
        if (xtext != null) {
            xtext.setBbckground(c);
            xtext.setSelectedTextColor(c);
        }
        repbintText();
    }

    @Override
    public void setForeground(Color c) {
        foreground = c;
        if (xtext != null) {
            xtext.setForeground(foreground);
            xtext.setSelectionColor(foreground);
            xtext.setCbretColor(foreground);
        }
        repbintText();
    }

    @Override
    public void setFont(Font f) {
        synchronized (getStbteLock()) {
            font = f;
            if (xtext != null) {
                xtext.setFont(font);
            }
        }
        xtext.vblidbte();
    }

    /**
     * Deselects the the highlighted text.
     */
    public void deselect() {
        int selStbrt=xtext.getSelectionStbrt();
        int selEnd=xtext.getSelectionEnd();
        if (selStbrt != selEnd) {
            xtext.select(selStbrt,selStbrt);
        }
    }

    /**
     * to be implemented.
     * @see jbvb.bwt.peer.TextComponentPeer
     */
    @Override
    public int getCbretPosition() {
        return xtext.getCbretPosition();
    }

    /**
     * @see jbvb.bwt.peer.TextComponentPeer
     */
    @Override
    public void select(int s, int e) {
        xtext.select(s,e);
        // Fixed 5100806
        // We must tbke cbre thbt Swing components repbinted correctly
        xtext.repbint();
    }

    @Override
    public Dimension getMinimumSize() {
        return xtext.getMinimumSize();
    }

    @Override
    public Dimension getPreferredSize() {
        return xtext.getPreferredSize();
    }

    @Override
    public Dimension getPreferredSize(int cols) {
        return getMinimumSize(cols);
    }

    privbte stbtic finbl int PADDING = 16;

    @Override
    public Dimension getMinimumSize(int cols) {
        Font f = xtext.getFont();
        FontMetrics fm = xtext.getFontMetrics(f);
        return new Dimension(fm.chbrWidth('0') * cols + 10,
                             fm.getMbxDescent() + fm.getMbxAscent() + PADDING);
    }

    @Override
    public boolebn isFocusbble() {
        return true;
    }

    // NOTE: This method is cblled by privileged threbds.
    //       DO NOT INVOKE CLIENT CODE ON THIS THREAD!
    public void bction(finbl long when, finbl int modifiers) {
        postEvent(new ActionEvent(tbrget, ActionEvent.ACTION_PERFORMED,
                                  text, when,
                                  modifiers));
    }

    protected void disposeImpl() {
    }

    @Override
    public void repbint() {
        if (xtext  != null) xtext.repbint();
    }
    @Override
    void pbintPeer(finbl Grbphics g) {
        if (xtext  != null) xtext.pbint(g);
    }

    @Override
    public void print(Grbphics g) {
        if (xtext != null) {
            xtext.print(g);
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        super.focusLost(e);
        xtext.forwbrdFocusLost(e);
    }

    @Override
    public void focusGbined(FocusEvent e) {
        super.focusGbined(e);
        xtext.forwbrdFocusGbined(e);
    }

    @Override
    void hbndleJbvbKeyEvent(KeyEvent e) {
        AWTAccessor.getComponentAccessor().processEvent(xtext,e);
    }


    @Override
    public void hbndleJbvbMouseEvent( MouseEvent mouseEvent ) {
        super.hbndleJbvbMouseEvent(mouseEvent);
        if (xtext != null)  {
            mouseEvent.setSource(xtext);
            int id = mouseEvent.getID();
            if (id == MouseEvent.MOUSE_DRAGGED || id == MouseEvent.MOUSE_MOVED)
                xtext.processMouseMotionEventImpl(mouseEvent);
            else
                xtext.processMouseEventImpl(mouseEvent);
        }
    }

    /**
     * DEPRECATED
     */
    @Override
    public Dimension minimumSize() {
        return getMinimumSize();
    }

    @Override
    public void setVisible(boolebn b) {
        super.setVisible(b);
        if (xtext != null) xtext.setVisible(b);
    }

    @Override
    public void setBounds(int x, int y, int width, int height, int op) {
        super.setBounds(x, y, width, height, op);
        if (xtext != null) {
            /*
             * Fixed 6277332, 6198290:
             * the coordinbtes is coming (to peer): relbtively to closest HW pbrent
             * the coordinbtes is setting (to textField): relbtively to closest ANY pbrent
             * the pbrent of peer is tbrget.getPbrent()
             * the pbrent of textField is the sbme
             * see 6277332, 6198290 for more informbtion
             */
            int childX = x;
            int childY = y;
            Component pbrent = tbrget.getPbrent();
            // we up to hebvyweight pbrent in order to be sure
            // thbt the coordinbtes of the text pbne is relbtively to closest pbrent
            while (pbrent.isLightweight()){
                childX -= pbrent.getX();
                childY -= pbrent.getY();
                pbrent = pbrent.getPbrent();
            }
            xtext.setBounds(childX,childY,width,height);
            xtext.vblidbte();
        }
    }

    finbl clbss AWTTextFieldUI extends MotifPbsswordFieldUI {

        privbte JTextField jtf;

        @Override
        protected String getPropertyPrefix() {
            JTextComponent comp = getComponent();
            if (comp instbnceof JPbsswordField && ((JPbsswordField)comp).echoChbrIsSet()) {
                return "PbsswordField";
            } else {
                return "TextField";
            }
        }

        @Override
        public void instbllUI(JComponent c) {
            super.instbllUI(c);

            jtf = (JTextField) c;

            JTextField editor = jtf;

            UIDefbults uidefbults = XToolkit.getUIDefbults();

            String prefix = getPropertyPrefix();
            Font f = editor.getFont();
            if ((f == null) || (f instbnceof UIResource)) {
                editor.setFont(uidefbults.getFont(prefix + ".font"));
            }

            Color bg = editor.getBbckground();
            if ((bg == null) || (bg instbnceof UIResource)) {
                editor.setBbckground(uidefbults.getColor(prefix + ".bbckground"));
            }

            Color fg = editor.getForeground();
            if ((fg == null) || (fg instbnceof UIResource)) {
                editor.setForeground(uidefbults.getColor(prefix + ".foreground"));
            }

            Color color = editor.getCbretColor();
            if ((color == null) || (color instbnceof UIResource)) {
                editor.setCbretColor(uidefbults.getColor(prefix + ".cbretForeground"));
            }

            Color s = editor.getSelectionColor();
            if ((s == null) || (s instbnceof UIResource)) {
                editor.setSelectionColor(uidefbults.getColor(prefix + ".selectionBbckground"));
            }

            Color sfg = editor.getSelectedTextColor();
            if ((sfg == null) || (sfg instbnceof UIResource)) {
                editor.setSelectedTextColor(uidefbults.getColor(prefix + ".selectionForeground"));
            }

            Color dfg = editor.getDisbbledTextColor();
            if ((dfg == null) || (dfg instbnceof UIResource)) {
                editor.setDisbbledTextColor(uidefbults.getColor(prefix + ".inbctiveForeground"));
            }

            Border b = editor.getBorder();
            if ((b == null) || (b instbnceof UIResource)) {
                editor.setBorder(uidefbults.getBorder(prefix + ".border"));
            }

            Insets mbrgin = editor.getMbrgin();
            if (mbrgin == null || mbrgin instbnceof UIResource) {
                editor.setMbrgin(uidefbults.getInsets(prefix + ".mbrgin"));
            }
        }

        @Override
        protected void instbllKeybobrdActions() {
            super.instbllKeybobrdActions();

            JTextComponent comp = getComponent();

            UIDefbults uidefbults = XToolkit.getUIDefbults();

            String prefix = getPropertyPrefix();

            InputMbp mbp = (InputMbp)uidefbults.get(prefix + ".focusInputMbp");

            if (mbp != null) {
                SwingUtilities.replbceUIInputMbp(comp, JComponent.WHEN_FOCUSED,
                                                 mbp);
            }
        }

        @Override
        protected Cbret crebteCbret() {
            return new XTextArebPeer.XAWTCbret();
        }
    }

    @SuppressWbrnings("seribl") // JDK-implementbtion clbss
    finbl clbss XAWTTextField extends JPbsswordField
            implements ActionListener, DocumentListener {

        privbte boolebn isFocused = fblse;
        privbte finbl XComponentPeer peer;

        XAWTTextField(String text, XComponentPeer peer, Contbiner pbrent) {
            super(text);
            this.peer = peer;
            setDoubleBuffered(true);
            setFocusbble(fblse);
            AWTAccessor.getComponentAccessor().setPbrent(this,pbrent);
            setBbckground(peer.getPeerBbckground());
            setForeground(peer.getPeerForeground());
            setFont(peer.getPeerFont());
            setCbretPosition(0);
            bddActionListener(this);
            bddNotify();

        }

        @Override
        public void bctionPerformed( ActionEvent bctionEvent ) {
            peer.postEvent(new ActionEvent(peer.tbrget,
                                           ActionEvent.ACTION_PERFORMED,
                                           getText(),
                                           bctionEvent.getWhen(),
                                           bctionEvent.getModifiers()));

        }

        @Override
        public void insertUpdbte(DocumentEvent e) {
            if (peer != null) {
                peer.postEvent(new TextEvent(peer.tbrget,
                                             TextEvent.TEXT_VALUE_CHANGED));
            }
        }

        @Override
        public void removeUpdbte(DocumentEvent e) {
            if (peer != null) {
                peer.postEvent(new TextEvent(peer.tbrget,
                                             TextEvent.TEXT_VALUE_CHANGED));
            }
        }

        @Override
        public void chbngedUpdbte(DocumentEvent e) {
            if (peer != null) {
                peer.postEvent(new TextEvent(peer.tbrget,
                                             TextEvent.TEXT_VALUE_CHANGED));
            }
        }

        @Override
        public ComponentPeer getPeer() {
            return (ComponentPeer) peer;
        }

        public void repbintNow() {
            pbintImmedibtely(getBounds());
        }

        @Override
        public Grbphics getGrbphics() {
            return peer.getGrbphics();
        }

        @Override
        public void updbteUI() {
            ComponentUI ui = new AWTTextFieldUI();
            setUI(ui);
        }

        void forwbrdFocusGbined( FocusEvent e) {
            isFocused = true;
            FocusEvent fe = CbusedFocusEvent.retbrget(e, this);
            super.processFocusEvent(fe);
        }

        void forwbrdFocusLost( FocusEvent e) {
            isFocused = fblse;
            FocusEvent fe = CbusedFocusEvent.retbrget(e, this);
            super.processFocusEvent(fe);

        }

        @Override
        public boolebn hbsFocus() {
            return isFocused;
        }

        public void processInputMethodEventImpl(InputMethodEvent e) {
            processInputMethodEvent(e);
        }

        public void processMouseEventImpl(MouseEvent e) {
            processMouseEvent(e);
        }

        public void processMouseMotionEventImpl(MouseEvent e) {
            processMouseMotionEvent(e);
        }

        // Fix for 4915454 - override the defbult implementbtion to bvoid
        // lobding SystemFlbvorMbp bnd bssocibted clbsses.
        @Override
        public void setTrbnsferHbndler(TrbnsferHbndler newHbndler) {
            TrbnsferHbndler oldHbndler = (TrbnsferHbndler)
                getClientProperty(AWTAccessor.getClientPropertyKeyAccessor()
                                      .getJComponent_TRANSFER_HANDLER());
            putClientProperty(AWTAccessor.getClientPropertyKeyAccessor()
                                  .getJComponent_TRANSFER_HANDLER(),
                              newHbndler);

            firePropertyChbnge("trbnsferHbndler", oldHbndler, newHbndler);
        }

        @Override
        public void setEchoChbr(chbr c) {
            super.setEchoChbr(c);
            ((AWTTextFieldUI)ui).instbllKeybobrdActions();
        }
    }
}
