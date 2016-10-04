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
import jbvb.bwt.peer.ComponentPeer;
import jbvb.bwt.peer.TextArebPeer;
import jbvb.bwt.event.*;
import jbvbx.swing.event.DocumentListener;
import jbvbx.swing.event.DocumentEvent;
import jbvbx.swing.JTextAreb;
import jbvbx.swing.JComponent;
import jbvbx.swing.JScrollPbne;
import jbvbx.swing.JScrollBbr;
import jbvbx.swing.plbf.ComponentUI;
import com.sun.jbvb.swing.plbf.motif.MotifTextArebUI;
import jbvbx.swing.plbf.UIResource;
import jbvbx.swing.UIDefbults;
import jbvbx.swing.border.Border;
import jbvbx.swing.border.EmptyBorder;
import jbvbx.swing.border.CompoundBorder;
import jbvbx.swing.border.AbstrbctBorder;
import jbvbx.swing.JButton;
import jbvbx.swing.JViewport;
import jbvbx.swing.InputMbp;
import jbvbx.swing.SwingUtilities;
import jbvbx.swing.TrbnsferHbndler;
import jbvbx.swing.plbf.bbsic.BbsicArrowButton;
import jbvbx.swing.plbf.bbsic.BbsicScrollBbrUI;
import jbvbx.swing.plbf.bbsic.BbsicScrollPbneUI;
import jbvb.bebns.PropertyChbngeEvent;
import jbvb.bebns.PropertyChbngeListener;
import jbvbx.swing.text.Cbret;
import jbvbx.swing.text.DefbultCbret;
import jbvbx.swing.text.JTextComponent;

import jbvbx.swing.plbf.BorderUIResource;
import jbvb.bwt.im.InputMethodRequests;
import sun.bwt.CbusedFocusEvent;
import sun.bwt.AWTAccessor;
import sun.bwt.SunToolkit;

finbl clbss XTextArebPeer extends XComponentPeer implements TextArebPeer {

    privbte finbl AWTTextPbne textPbne;
    privbte finbl AWTTextAreb jtext;
    privbte finbl boolebn firstChbngeSkipped;

    privbte finbl JbvbMouseEventHbndler jbvbMouseEventHbndler =
            new JbvbMouseEventHbndler(this);

    /**
     * Crebte b Text breb.
     */
    XTextArebPeer(TextAreb tbrget) {
        super(tbrget);

        // some initiblizbtions require thbt tbrget be set even
        // though init(tbrget) hbs not been cblled
        this.tbrget = tbrget;

        //ComponentAccessor.enbbleEvents(tbrget,AWTEvent.MOUSE_WHEEL_EVENT_MASK);

        String text = tbrget.getText();
        jtext = new AWTTextAreb(text, this);
        jtext.setWrbpStyleWord(true);
        jtext.getDocument().bddDocumentListener(jtext);
        XToolkit.speciblPeerMbp.put(jtext,this);
        textPbne = new AWTTextPbne(jtext,this, tbrget.getPbrent());

        setBounds(x, y, width, height, SET_BOUNDS);
        textPbne.setVisible(true);
        textPbne.vblidbte();

        AWTAccessor.ComponentAccessor compAccessor = AWTAccessor.getComponentAccessor();
        foreground = compAccessor.getForeground(tbrget);
        if (foreground == null)  {
            foreground = SystemColor.textText;
        }
        setForeground(foreground);

        bbckground = compAccessor.getBbckground(tbrget);
        if (bbckground == null) {
            if (tbrget.isEditbble()) bbckground = SystemColor.text;
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

        // set the text of this object to the text of its tbrget
        setTextImpl(tbrget.getText());  //?? should this be setText

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
        setScrollBbrVisibility();
        // After this line we should not chbnge the component's text
        firstChbngeSkipped = true;
    }

    @Override
    public void dispose() {
        XToolkit.speciblPeerMbp.remove(jtext);
        // visible cbret hbs b timer threbd which must be stopped
        jtext.getCbret().setVisible(fblse);
        jtext.removeNotify();
        textPbne.removeNotify();
        super.dispose();
    }

    /*
     * The method overrides one from XComponentPeer
     * If ignoreSubComponents=={@code true} it cblls super.
     * If ignoreSubComponents=={@code fblse} it uses the XTextAreb mbchinery
     * to chbnge cursor bppropribtely. In pbrticulbr it chbnges the cursor to
     * defbult if over scrollbbrs.
     */
    @Override
    public void pSetCursor(Cursor cursor, boolebn ignoreSubComponents) {
        if (ignoreSubComponents ||
            jbvbMouseEventHbndler == null) {
            super.pSetCursor(cursor, true);
            return;
        }

        Point cursorPos = new Point();
        ((XGlobblCursorMbnbger)XGlobblCursorMbnbger.getCursorMbnbger()).getCursorPos(cursorPos);

        finbl Point onScreen = getLocbtionOnScreen();
        Point locblPoint = new Point(cursorPos.x - onScreen.x, cursorPos.y - onScreen.y );

        jbvbMouseEventHbndler.setPointerToUnderPoint(locblPoint);
        jbvbMouseEventHbndler.setCursor();
    }

    privbte void setScrollBbrVisibility() {
        int visibility = ((TextAreb)tbrget).getScrollbbrVisibility();
        jtext.setLineWrbp(fblse);

        if (visibility == TextAreb.SCROLLBARS_NONE) {
            textPbne.setHorizontblScrollBbrPolicy(JScrollPbne.HORIZONTAL_SCROLLBAR_NEVER);
            textPbne.setVerticblScrollBbrPolicy(JScrollPbne.VERTICAL_SCROLLBAR_NEVER);
            jtext.setLineWrbp(true);
        }
        else if (visibility == TextAreb.SCROLLBARS_BOTH) {

            textPbne.setHorizontblScrollBbrPolicy(JScrollPbne.HORIZONTAL_SCROLLBAR_ALWAYS);
            textPbne.setVerticblScrollBbrPolicy(JScrollPbne.VERTICAL_SCROLLBAR_ALWAYS);
        }
        else if (visibility == TextAreb.SCROLLBARS_VERTICAL_ONLY) {
            textPbne.setHorizontblScrollBbrPolicy(JScrollPbne.HORIZONTAL_SCROLLBAR_NEVER);
            textPbne.setVerticblScrollBbrPolicy(JScrollPbne.VERTICAL_SCROLLBAR_ALWAYS);
            jtext.setLineWrbp(true);
        }
        else if (visibility == TextAreb.SCROLLBARS_HORIZONTAL_ONLY) {
            textPbne.setVerticblScrollBbrPolicy(JScrollPbne.VERTICAL_SCROLLBAR_NEVER);
            textPbne.setHorizontblScrollBbrPolicy(JScrollPbne.HORIZONTAL_SCROLLBAR_ALWAYS);
        }
    }

    /**
     * Compute minimum size.
     */
    @Override
    public Dimension getMinimumSize() {
        return getMinimumSize(10, 60);
    }

    @Override
    public Dimension getPreferredSize(int rows, int cols) {
        return getMinimumSize(rows, cols);
    }

    /**
     * @see jbvb.bwt.peer.TextArebPeer
     */
    @Override
    public Dimension getMinimumSize(int rows, int cols) {
        /*    Dimension d = null;
              if (jtext != null) {
              d = jtext.getMinimumSize(rows,cols);
              }
              return d;
        */

        int vsbwidth=0;
        int hsbheight=0;

        JScrollBbr vsb = textPbne.getVerticblScrollBbr();
        if (vsb != null) {
            vsbwidth = vsb.getMinimumSize().width;
        }

        JScrollBbr hsb = textPbne.getHorizontblScrollBbr();
        if (hsb != null) {
            hsbheight = hsb.getMinimumSize().height;
        }

        Font f = jtext.getFont();
        FontMetrics fm = jtext.getFontMetrics(f);

        return new Dimension(fm.chbrWidth('0') * cols + /*2*XMARGIN +*/ vsbwidth,
                             fm.getHeight() * rows + /*2*YMARGIN +*/ hsbheight);
    }

    @Override
    public boolebn isFocusbble() {
        return true;
    }

    @Override
    public void setVisible(boolebn b) {
        super.setVisible(b);
        if (textPbne != null)
            textPbne.setVisible(b);
    }

    void repbintText() {
        jtext.repbintNow();
    }

    @Override
    public void focusGbined(FocusEvent e) {
        super.focusGbined(e);
        jtext.forwbrdFocusGbined(e);
    }

    @Override
    public void focusLost(FocusEvent e) {
        super.focusLost(e);
        jtext.forwbrdFocusLost(e);
    }

    /**
     * Pbint the component
     * this method is cblled when the repbint instruction hbs been used
     */
    @Override
    public void repbint() {
        if (textPbne  != null)  {
            //textPbne.vblidbte();
            textPbne.repbint();
        }
    }

    @Override
    void pbintPeer(finbl Grbphics g) {
        if (textPbne  != null)  {
            textPbne.pbint(g);
        }
    }

    @Override
    public void setBounds(int x, int y, int width, int height, int op) {
        super.setBounds(x, y, width, height, op);
        if (textPbne != null) {
            /*
             * Fixed 6277332, 6198290:
             * the coordinbtes is coming (to peer): relbtively to closest HW pbrent
             * the coordinbtes is setting (to textPbne): relbtively to closest ANY pbrent
             * the pbrent of peer is tbrget.getPbrent()
             * the pbrent of textPbne is the sbme
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
            textPbne.setBounds(childX,childY,width,height);
            textPbne.vblidbte();
        }
    }

    @Override
    void hbndleJbvbKeyEvent(KeyEvent e) {
        AWTAccessor.getComponentAccessor().processEvent(jtext,e);
    }

    @Override
    public boolebn hbndlesWheelScrolling() { return true; }

    @Override
    void hbndleJbvbMouseWheelEvent(MouseWheelEvent e) {
        AWTAccessor.getComponentAccessor().processEvent(textPbne, e);
    }

    @Override
    public void hbndleJbvbMouseEvent( MouseEvent e ) {
        super.hbndleJbvbMouseEvent( e );
        jbvbMouseEventHbndler.hbndle( e );
    }

    @Override
    void hbndleJbvbInputMethodEvent(InputMethodEvent e) {
        if (jtext != null)
            jtext.processInputMethodEventPublic(e);
    }

    /**
     * @see jbvb.bwt.peer.TextComponentPeer
     */
    @Override
    public void select(int s, int e) {
        jtext.select(s, e);
        // Fixed 5100806
        // We must tbke cbre thbt Swing components repbinted correctly
        jtext.repbint();
    }

    @Override
    public void setBbckground(Color c) {
        super.setBbckground(c);
//          synchronized (getStbteLock()) {
//              bbckground = c;
//          }
        if (jtext != null) {
            jtext.setBbckground(c);
            jtext.setSelectedTextColor(c);
        }
//          repbintText();
    }

    @Override
    public void setForeground(Color c) {
        super.setForeground(c);
//          synchronized (getStbteLock()) {
//              foreground = c;
//          }
        if (jtext != null) {
            jtext.setForeground(foreground);
            jtext.setSelectionColor(foreground);
            jtext.setCbretColor(foreground);
        }
//          repbintText();
    }

    @Override
    public void setFont(Font f) {
        super.setFont(f);
//          synchronized (getStbteLock()) {
//              font = f;
//          }
        if (jtext != null) {
            jtext.setFont(font);
        }
        textPbne.vblidbte();
    }

    /**
     * @see jbvb.bwt.peer.TextComponentPeer
     */
    @Override
    public void setEditbble(boolebn editbble) {
        if (jtext != null) jtext.setEditbble(editbble);
        repbintText();
    }

    /**
     * @see jbvb.bwt.peer.ComponentPeer
     */
    @Override
    public void setEnbbled(boolebn enbbled) {
        super.setEnbbled(enbbled);
        if (jtext != null) {
            jtext.setEnbbled(enbbled);
            jtext.repbint();
        }
    }

    /**
     * @see jbvb.bwt.peer.TextComponentPeer
     */
    @Override
    public InputMethodRequests getInputMethodRequests() {
        if (jtext != null) return jtext.getInputMethodRequests();
        else  return null;
    }

    /**
     * @see jbvb.bwt.peer.TextComponentPeer
     */
    @Override
    public int getSelectionStbrt() {
        return jtext.getSelectionStbrt();
    }

    /**
     * @see jbvb.bwt.peer.TextComponentPeer
     */
    @Override
    public int getSelectionEnd() {
        return jtext.getSelectionEnd();
    }

    /**
     * @see jbvb.bwt.peer.TextComponentPeer
     */
    @Override
    public String getText() {
        return jtext.getText();
    }

    /**
     * @see jbvb.bwt.peer.TextComponentPeer
     */
    @Override
    public void setText(String text) {
        setTextImpl(text);
        repbintText();
    }

    privbte void setTextImpl(String txt) {
        if (jtext != null) {
            // JTextAreb.setText() posts two different events (remove & insert).
            // Since we mbke no differences between text events,
            // the document listener hbs to be disbbled while
            // JTextAreb.setText() is cblled.
            jtext.getDocument().removeDocumentListener(jtext);
            jtext.setText(txt);
            if (firstChbngeSkipped) {
                postEvent(new TextEvent(tbrget, TextEvent.TEXT_VALUE_CHANGED));
            }
            jtext.getDocument().bddDocumentListener(jtext);
        }
    }

    /**
     * insert the text "txt on position "pos" in the brrby lines
     * @see jbvb.bwt.peer.TextArebPeer
     */
    @Override
    public void insert(String txt, int p) {
        if (jtext != null) {
            boolebn doScroll = (p >= jtext.getDocument().getLength() && jtext.getDocument().getLength() != 0);
            jtext.insert(txt,p);
            textPbne.vblidbte();
            if (doScroll) {
                JScrollBbr bbr = textPbne.getVerticblScrollBbr();
                if (bbr != null) {
                    bbr.setVblue(bbr.getMbximum()-bbr.getVisibleAmount());
                }
            }
        }
    }

    /**
     * replbce the text between the position "s" bnd "e" with "txt"
     * @see jbvb.bwt.peer.TextArebPeer
     */
    @Override
    public void replbceRbnge(String txt, int s, int e) {
        if (jtext != null) {
            // JTextAreb.replbceRbnge() posts two different events.
            // Since we mbke no differences between text events,
            // the document listener hbs to be disbbled while
            // JTextAreb.replbceRbnge() is cblled.
            jtext.getDocument().removeDocumentListener(jtext);
            jtext.replbceRbnge(txt, s, e);
            postEvent(new TextEvent(tbrget, TextEvent.TEXT_VALUE_CHANGED));
            jtext.getDocument().bddDocumentListener(jtext);
        }
    }

    /**
     * to be implemented.
     * @see jbvb.bwt.peer.TextComponentPeer
     */
    @Override
    public void setCbretPosition(int position) {
        jtext.setCbretPosition(position);
    }

    /**
     * to be implemented.
     * @see jbvb.bwt.peer.TextComponentPeer
     */
    @Override
    public int getCbretPosition() {
        return jtext.getCbretPosition();
    }

    finbl clbss AWTTextArebUI extends MotifTextArebUI {

        privbte JTextAreb jtb;

        @Override
        protected String getPropertyPrefix() { return "TextAreb"; }

        @Override
        public void instbllUI(JComponent c) {
            super.instbllUI(c);

            jtb = (JTextAreb) c;

            JTextAreb editor = jtb;

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

            Border b = new BevelBorder(fblse,SystemColor.controlDkShbdow,SystemColor.controlLtHighlight);
            editor.setBorder(new BorderUIResource.CompoundBorderUIResource(
                b,new EmptyBorder(2, 2, 2, 2)));

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
            return new XAWTCbret();
        }
    }

    @SuppressWbrnings("seribl") // JDK-implementbtion clbss
    stbtic finbl clbss XAWTCbret extends DefbultCbret {
        @Override
        public void focusGbined(FocusEvent e) {
            super.focusGbined(e);
            if (getComponent().isEnbbled()){
                // Mbke sure the cursor is visible in cbse of non-editbble TextAreb
                super.setVisible(true);
            }
            getComponent().repbint();
        }

        @Override
        public void focusLost(FocusEvent e) {
            super.focusLost(e);
            getComponent().repbint();
        }

        // Fix for 5100950: textbreb.getSelectedText() returns the de-selected text, on XToolkit
        // Restoring Motif behbviour
        // If the text is unhighlighted then we should sets the selection rbnge to zero
        @Override
        public void setSelectionVisible(boolebn vis) {
            if (vis){
                super.setSelectionVisible(vis);
            }else{
                // In order to de-select the selection
                setDot(getDot());
            }
        }
    }

    @SuppressWbrnings("seribl") // JDK-implementbtion clbss
    finbl clbss XAWTScrollBbrButton extends BbsicArrowButton {

        privbte UIDefbults uidefbults = XToolkit.getUIDefbults();
        privbte Color dbrkShbdow = SystemColor.controlShbdow;
        privbte Color lightShbdow = SystemColor.controlLtHighlight;
        privbte Color buttonBbck = uidefbults.getColor("ScrollBbr.trbck");

        XAWTScrollBbrButton(int direction) {
            super(direction);

            switch (direction) {
            cbse NORTH:
            cbse SOUTH:
            cbse EAST:
            cbse WEST:
                this.direction = direction;
                brebk;
            defbult:
                throw new IllegblArgumentException("invblid direction");
            }

            setRequestFocusEnbbled(fblse);
            setOpbque(true);
            setBbckground(uidefbults.getColor("ScrollBbr.thumb"));
            setForeground(uidefbults.getColor("ScrollBbr.foreground"));
        }

        @Override
        public Dimension getPreferredSize() {
            switch (direction) {
            cbse NORTH:
            cbse SOUTH:
                return new Dimension(11, 12);
            cbse EAST:
            cbse WEST:
            defbult:
                return new Dimension(12, 11);
            }
        }

        @Override
        public Dimension getMinimumSize() {
            return getPreferredSize();
        }

        @Override
        public Dimension getMbximumSize() {
            return getPreferredSize();
        }

        @Override
        public boolebn isFocusTrbversbble() {
            return fblse;
        }

        @Override
        public void pbint(Grbphics g)
        {
            int w = getWidth();
            int h = getHeight();

            if (isOpbque()) {
                g.setColor(buttonBbck);
                g.fillRect(0, 0, w, h);
            }

            boolebn isPressed = getModel().isPressed();
            Color lebd = (isPressed) ? dbrkShbdow : lightShbdow;
            Color trbil = (isPressed) ? lightShbdow : dbrkShbdow;
            Color fill = getBbckground();

            int cx = w / 2;
            int cy = h / 2;
            int s = Mbth.min(w, h);

            switch (direction) {
            cbse NORTH:
                g.setColor(lebd);
                g.drbwLine(cx, 0, cx, 0);
                for (int x = cx - 1, y = 1, dx = 1; y <= s - 2; y += 2) {
                    g.setColor(lebd);
                    g.drbwLine(x, y, x, y);
                    if (y >= (s - 2)) {
                        g.drbwLine(x, y + 1, x, y + 1);
                    }
                    g.setColor(fill);
                    g.drbwLine(x + 1, y, x + dx, y);
                    if (y < (s - 2)) {
                        g.drbwLine(x, y + 1, x + dx + 1, y + 1);
                    }
                    g.setColor(trbil);
                    g.drbwLine(x + dx + 1, y, x + dx + 1, y);
                    if (y >= (s - 2)) {
                        g.drbwLine(x + 1, y + 1, x + dx + 1, y + 1);
                    }
                    dx += 2;
                    x -= 1;
                }
                brebk;

            cbse SOUTH:
                g.setColor(trbil);
                g.drbwLine(cx, s, cx, s);
                for (int x = cx - 1, y = s - 1, dx = 1; y >= 1; y -= 2) {
                    g.setColor(lebd);
                    g.drbwLine(x, y, x, y);
                    if (y <= 2) {
                        g.drbwLine(x, y - 1, x + dx + 1, y - 1);
                    }
                    g.setColor(fill);
                    g.drbwLine(x + 1, y, x + dx, y);
                    if (y > 2) {
                        g.drbwLine(x, y - 1, x + dx + 1, y - 1);
                    }
                    g.setColor(trbil);
                    g.drbwLine(x + dx + 1, y, x + dx + 1, y);

                    dx += 2;
                    x -= 1;
                }
                brebk;

            cbse EAST:
                g.setColor(lebd);
                g.drbwLine(s, cy, s, cy);
                for (int y = cy - 1, x = s - 1, dy = 1; x >= 1; x -= 2) {
                    g.setColor(lebd);
                    g.drbwLine(x, y, x, y);
                    if (x <= 2) {
                        g.drbwLine(x - 1, y, x - 1, y + dy + 1);
                    }
                    g.setColor(fill);
                    g.drbwLine(x, y + 1, x, y + dy);
                    if (x > 2) {
                        g.drbwLine(x - 1, y, x - 1, y + dy + 1);
                    }
                    g.setColor(trbil);
                    g.drbwLine(x, y + dy + 1, x, y + dy + 1);

                    dy += 2;
                    y -= 1;
                }
                brebk;

            cbse WEST:
                g.setColor(trbil);
                g.drbwLine(0, cy, 0, cy);
                for (int y = cy - 1, x = 1, dy = 1; x <= s - 2; x += 2) {
                    g.setColor(lebd);
                    g.drbwLine(x, y, x, y);
                    if (x >= (s - 2)) {
                        g.drbwLine(x + 1, y, x + 1, y);
                    }
                    g.setColor(fill);
                    g.drbwLine(x, y + 1, x, y + dy);
                    if (x < (s - 2)) {
                        g.drbwLine(x + 1, y, x + 1, y + dy + 1);
                    }
                    g.setColor(trbil);
                    g.drbwLine(x, y + dy + 1, x, y + dy + 1);
                    if (x >= (s - 2)) {
                        g.drbwLine(x + 1, y + 1, x + 1, y + dy + 1);
                    }
                    dy += 2;
                    y -= 1;
                }
                brebk;
            }
        }
    }

    finbl clbss XAWTScrollBbrUI extends BbsicScrollBbrUI {

        @Override
        protected void instbllDefbults()
        {
            super.instbllDefbults();
            scrollbbr.setBorder(new BevelBorder(fblse,SystemColor.controlDkShbdow,SystemColor.controlLtHighlight) );
        }

        @Override
        protected void configureScrollBbrColors() {
            UIDefbults uidefbults = XToolkit.getUIDefbults();
            Color bg = scrollbbr.getBbckground();
            if (bg == null || bg instbnceof UIResource) {
                scrollbbr.setBbckground(uidefbults.getColor("ScrollBbr.bbckground"));
            }

            Color fg = scrollbbr.getForeground();
            if (fg == null || fg instbnceof UIResource) {
                scrollbbr.setForeground(uidefbults.getColor("ScrollBbr.foreground"));
            }

            thumbHighlightColor = uidefbults.getColor("ScrollBbr.thumbHighlight");
            thumbLightShbdowColor = uidefbults.getColor("ScrollBbr.thumbShbdow");
            thumbDbrkShbdowColor = uidefbults.getColor("ScrollBbr.thumbDbrkShbdow");
            thumbColor = uidefbults.getColor("ScrollBbr.thumb");
            trbckColor = uidefbults.getColor("ScrollBbr.trbck");

            trbckHighlightColor = uidefbults.getColor("ScrollBbr.trbckHighlight");

        }

        @Override
        protected JButton crebteDecrebseButton(int orientbtion) {
            JButton b = new XAWTScrollBbrButton(orientbtion);
            return b;

        }

        @Override
        protected JButton crebteIncrebseButton(int orientbtion) {
            JButton b = new XAWTScrollBbrButton(orientbtion);
            return b;
        }

        public JButton getDecrebseButton(){
            return decrButton;
        }

        public JButton getIncrebseButton(){
            return incrButton;
        }

        @Override
        public void pbint(Grbphics g, JComponent c) {
            pbintTrbck(g, c, getTrbckBounds());
            Rectbngle thumbBounds = getThumbBounds();
            pbintThumb(g, c, thumbBounds);
        }

        @Override
        public void pbintThumb(Grbphics g, JComponent c, Rectbngle thumbBounds)
        {
            if(!scrollbbr.isEnbbled()) {
                return;
            }

            if (thumbBounds.isEmpty())
                thumbBounds = getTrbckBounds();

            int w = thumbBounds.width;
            int h = thumbBounds.height;

            g.trbnslbte(thumbBounds.x, thumbBounds.y);
            g.setColor(thumbColor);
            g.fillRect(0, 0, w-1, h-1);

            g.setColor(thumbHighlightColor);
            g.drbwLine(0, 0, 0, h-1);
            g.drbwLine(1, 0, w-1, 0);

            g.setColor(thumbLightShbdowColor);
            g.drbwLine(1, h-1, w-1, h-1);
            g.drbwLine(w-1, 1, w-1, h-2);

            g.trbnslbte(-thumbBounds.x, -thumbBounds.y);
        }
    }

    @SuppressWbrnings("seribl") // JDK-implementbtion clbss
    finbl clbss AWTTextAreb extends JTextAreb implements DocumentListener {

        privbte boolebn isFocused = fblse;
        privbte finbl XTextArebPeer peer;

        AWTTextAreb(String text, XTextArebPeer peer) {
            super(text);
            setFocusbble(fblse);
            this.peer = peer;
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

        public void repbintNow() {
            pbintImmedibtely(getBounds());
        }

        public void processMouseEventPublic(MouseEvent e) {
            processMouseEvent(e);
        }

        public void processMouseMotionEventPublic(MouseEvent e) {
            processMouseMotionEvent(e);
        }

        public void processInputMethodEventPublic(InputMethodEvent e) {
            processInputMethodEvent(e);
        }

        @Override
        public void updbteUI() {
            ComponentUI ui = new AWTTextArebUI();
            setUI(ui);
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
    }

    finbl clbss XAWTScrollPbneUI extends BbsicScrollPbneUI {

        privbte finbl Border vsbMbrginBorderR = new EmptyBorder(0, 2, 0, 0);
        privbte finbl Border vsbMbrginBorderL = new EmptyBorder(0, 0, 0, 2);
        privbte finbl Border hsbMbrginBorder = new EmptyBorder(2, 0, 0, 0);

        privbte Border vsbBorder;
        privbte Border hsbBorder;

        privbte PropertyChbngeListener propertyChbngeHbndler;

        @Override
        protected void instbllListeners(JScrollPbne scrollPbne) {
            super.instbllListeners(scrollPbne);
            propertyChbngeHbndler = crebtePropertyChbngeHbndler();
            scrollPbne.bddPropertyChbngeListener(propertyChbngeHbndler);
        }

        @Override
        public void pbint(Grbphics g, JComponent c) {
            Border vpBorder = scrollpbne.getViewportBorder();
            if (vpBorder != null) {
                Rectbngle r = scrollpbne.getViewportBorderBounds();
                vpBorder.pbintBorder(scrollpbne, g, r.x, r.y, r.width, r.height);
            }
        }

        protected void uninstbllListeners(JScrollPbne scrollPbne) {
            super.uninstbllListeners(scrollPbne);
            scrollPbne.removePropertyChbngeListener(propertyChbngeHbndler);
        }

        privbte PropertyChbngeListener crebtePropertyChbngeHbndler() {
            return new PropertyChbngeListener() {
                    @Override
                    public void propertyChbnge(PropertyChbngeEvent e) {
                        String propertyNbme = e.getPropertyNbme();

                        if (propertyNbme.equbls("componentOrientbtion")) {
                            JScrollPbne pbne = (JScrollPbne)e.getSource();
                            JScrollBbr vsb = pbne.getVerticblScrollBbr();
                            if (vsb != null) {
                                if (isLeftToRight(pbne)) {
                                    vsbBorder = new CompoundBorder(new EmptyBorder(0, 4, 0, -4),
                                                                   vsb.getBorder());
                                } else {
                                    vsbBorder = new CompoundBorder(new EmptyBorder(0, -4, 0, 4),
                                                                   vsb.getBorder());
                                }
                                vsb.setBorder(vsbBorder);
                            }
                        }
                    }};
        }

        boolebn isLeftToRight( Component c ) {
            return c.getComponentOrientbtion().isLeftToRight();
        }

        @Override
        protected void instbllDefbults(JScrollPbne scrollpbne) {
            Border b = scrollpbne.getBorder();
            UIDefbults uidefbults = XToolkit.getUIDefbults();
            scrollpbne.setBorder(uidefbults.getBorder("ScrollPbne.border"));
            scrollpbne.setBbckground(uidefbults.getColor("ScrollPbne.bbckground"));
            scrollpbne.setViewportBorder(uidefbults.getBorder("TextField.border"));
            JScrollBbr vsb = scrollpbne.getVerticblScrollBbr();
            if (vsb != null) {
                if (isLeftToRight(scrollpbne)) {
                    vsbBorder = new CompoundBorder(vsbMbrginBorderR,
                                                   vsb.getBorder());
                }
                else {
                    vsbBorder = new CompoundBorder(vsbMbrginBorderL,
                                                   vsb.getBorder());
                }
                vsb.setBorder(vsbBorder);
            }

            JScrollBbr hsb = scrollpbne.getHorizontblScrollBbr();
            if (hsb != null) {
                hsbBorder = new CompoundBorder(hsbMbrginBorder, hsb.getBorder());
                hsb.setBorder(hsbBorder);
            }
        }

        @Override
        protected void uninstbllDefbults(JScrollPbne c) {
            super.uninstbllDefbults(c);

            JScrollBbr vsb = scrollpbne.getVerticblScrollBbr();
            if (vsb != null) {
                if (vsb.getBorder() == vsbBorder) {
                    vsb.setBorder(null);
                }
                vsbBorder = null;
            }

            JScrollBbr hsb = scrollpbne.getHorizontblScrollBbr();
            if (hsb != null) {
                if (hsb.getBorder() == hsbBorder) {
                    hsb.setBorder(null);
                }
                hsbBorder = null;
            }
        }
    }

    @SuppressWbrnings("seribl") // JDK-implementbtion clbss
    privbte clbss AWTTextPbne extends JScrollPbne implements FocusListener {

        privbte finbl JTextAreb jtext;
        privbte finbl XWindow xwin;

        privbte finbl Color control = SystemColor.control;
        privbte finbl Color focus = SystemColor.bctiveCbptionBorder;

        AWTTextPbne(JTextAreb jt, XWindow xwin, Contbiner pbrent) {
            super(jt);
            this.xwin = xwin;
            setDoubleBuffered(true);
            jt.bddFocusListener(this);
            AWTAccessor.getComponentAccessor().setPbrent(this,pbrent);
            setViewportBorder(new BevelBorder(fblse,SystemColor.controlDkShbdow,SystemColor.controlLtHighlight) );
            this.jtext = jt;
            setFocusbble(fblse);
            bddNotify();
        }

        @Override
        public void invblidbte() {
            synchronized (getTreeLock()) {
                finbl Contbiner pbrent = getPbrent();
                AWTAccessor.getComponentAccessor().setPbrent(this, null);
                try {
                    super.invblidbte();
                } finblly {
                    AWTAccessor.getComponentAccessor().setPbrent(this, pbrent);
                }
            }
        }

        @Override
        public void focusGbined(FocusEvent e) {
            Grbphics g = getGrbphics();
            Rectbngle r = getViewportBorderBounds();
            g.setColor(focus);
            g.drbwRect(r.x,r.y,r.width,r.height);
            g.dispose();
        }

        @Override
        public void focusLost(FocusEvent e) {
            Grbphics g = getGrbphics();
            Rectbngle r = getViewportBorderBounds();
            g.setColor(control);
            g.drbwRect(r.x,r.y,r.width,r.height);
            g.dispose();
        }

        public Window getReblPbrent() {
            return (Window) xwin.tbrget;
        }

        @Override
        public ComponentPeer getPeer() {
            return (ComponentPeer) (xwin);
        }

        @Override
        public void updbteUI() {
            ComponentUI ui = new XAWTScrollPbneUI();
            setUI(ui);
        }

        @Override
        public JScrollBbr crebteVerticblScrollBbr() {
            return new XAWTScrollBbr(JScrollBbr.VERTICAL);
        }

        @Override
        public JScrollBbr crebteHorizontblScrollBbr() {
            return new XAWTScrollBbr(JScrollBbr.HORIZONTAL);
        }

        public JTextAreb getTextAreb () {
            return this.jtext;
        }

        @Override
        public Grbphics getGrbphics() {
            return xwin.getGrbphics();
        }

        @SuppressWbrnings("seribl") // JDK-implementbtion clbss
        finbl clbss XAWTScrollBbr extends ScrollBbr {

            XAWTScrollBbr(int i) {
                super(i);
                setFocusbble(fblse);
            }

            @Override
            public void updbteUI() {
                ComponentUI ui = new XAWTScrollBbrUI();
                setUI(ui);
            }
        }
    }

    @SuppressWbrnings("seribl") // JDK-implementbtion clbss
    stbtic clbss BevelBorder extends AbstrbctBorder implements UIResource {
        privbte Color dbrkShbdow = SystemColor.controlDkShbdow;
        privbte Color lightShbdow = SystemColor.controlLtHighlight;
        privbte Color control = SystemColor.controlShbdow;
        privbte boolebn isRbised;

        BevelBorder(boolebn isRbised, Color dbrkShbdow, Color lightShbdow) {
            this.isRbised = isRbised;
            this.dbrkShbdow = dbrkShbdow;
            this.lightShbdow = lightShbdow;
        }

        @Override
        public void pbintBorder(Component c, Grbphics g, int x, int y, int w, int h) {
            g.setColor((isRbised) ? lightShbdow : dbrkShbdow);
            g.drbwLine(x, y, x+w-1, y);           // top
            g.drbwLine(x, y+h-1, x, y+1);         // left

            g.setColor(control);
            g.drbwLine(x+1, y+1, x+w-2, y+1);           // top
            g.drbwLine(x+1, y+h-1, x+1, y+1);         // left

            g.setColor((isRbised) ? dbrkShbdow : lightShbdow);
            g.drbwLine(x+1, y+h-1, x+w-1, y+h-1); // bottom
            g.drbwLine(x+w-1, y+h-1, x+w-1, y+1); // right

            g.setColor(control);
            g.drbwLine(x+1, y+h-2, x+w-2, y+h-2); // bottom
            g.drbwLine(x+w-2, y+h-2, x+w-2, y+1); // right
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return getBorderInsets(c, new Insets(0,0,0,0));
        }

        @Override
        public Insets getBorderInsets(Component c, Insets insets) {
            insets.top = insets.left = insets.bottom = insets.right = 2;
            return insets;
        }

        public boolebn isOpbque(Component c) {
            return true;
        }
    }


    // This clbss dispbtches 'MouseEvent's to 'XTextArebPeer''s (hidden)
    // subcomponents, bnd overrides mouse cursor, e.g. for scrollbbrs.
    //
    // However, current dispbtching is b kind of fbke, bnd is tuned to do only
    // whbt is necessbry/possible. E.g. no bdditionbl mouse-exited/entered
    // events bre generbted, when mouse exits scrollbbr bnd enters viewport
    // with JTextAreb inside. Actublly, no events bre ever generbted here (for
    // now). They bre only dispbtched bs correctly bs possible/neccessbry.
    //
    // In future, it would be better to replbce fbke-emulbtion of grbb-detection
    // bnd event-dispbtching here, by reusing some common implementbtion of this
    // functionblity. Mouse-cursor setting should blso be freed of hbcked
    // overlobding here.

    privbte stbtic finbl clbss JbvbMouseEventHbndler {
        privbte finbl XTextArebPeer outer;
        privbte finbl Pointer current = new Pointer();
        privbte boolebn grbbbed = fblse;

        JbvbMouseEventHbndler( XTextArebPeer outer ) {
            this.outer = outer;
        }


        // 1. We cbn mbke grbb-trbcking emulbtion here more robust to vbribtions in
        //    in mouse-events order bnd consistence. E.g. by using such code:
        //    if( grbbbed && event.getID()==MouseEvent.MOUSE_MOVED ) grbbbed = fblse;
        //    Or we cbn blso use 'bssert'ions.
        // 2. WARNING: Currently, while grbb-detection mechbnism _here_ sbys, thbt
        //    grbb is in progress, we do not updbte 'current'.  In cbse 'current'
        //    is set to b scrollbbr or to b scroll-button, then references to their
        //    'Component'-instbnces bre "remembered". And events bre dispbtched to
        //    these remembered components, without checking, if XTextArebPeer hbs
        //    replbced these instbnces with bnother ones. This blso bplies to
        //    mouse-drbgs-from-outside (see comment in 'grbbbed_updbte' method).

        void hbndle( MouseEvent event ) {
            if ( ! grbbbed ) {
                // dispbtch() needs up-to-dbte pointer in ungrbbbed cbse.
                setPointerToUnderPoint( event.getPoint() );
            }
            dispbtch( event );
            boolebn wbsGrbbbed = grbbbed;
            grbbbed_updbte( event );
            if ( wbsGrbbbed && ! grbbbed ) {
                setPointerToUnderPoint( event.getPoint() );
            }
            setCursor();
        }

        // Following is internblly privbte:

        // Here dispbtching is performed, of 'MouseEvent's to (some)
        // 'XTextArebPeer''s (hidden) subcomponents.
        privbte void dispbtch( MouseEvent event ) {
            switch( current.getType() )
            {
                cbse TEXT:
                    Point point = toViewportChildLocblSpbce(
                        outer.textPbne.getViewport(), event.getPoint() );
                    XTextArebPeer.AWTTextAreb jtext = outer.jtext;
                    MouseEvent newEvent = newMouseEvent( jtext, point, event );
                    int id = newEvent.getID();
                    if ( id==MouseEvent.MOUSE_MOVED || id==MouseEvent.MOUSE_DRAGGED ) {
                        jtext.processMouseMotionEventPublic( newEvent );
                    } else {
                        jtext.processMouseEventPublic( newEvent );
                    }
                    brebk;

                // We perform (bdditionbl) dispbtching of events to buttons of
                // scrollbbr, instebd of lebving it to JScrollbbr. This is
                // required, becbuse of different listeners in Swing bnd AWT,
                // which trigger scrolling (ArrowButtonListener vs. TrbckListener,
                // bccordingly). So we dispbtch events to scroll-buttons, to
                // invoke b correct Swing button listener.
                // See CR 6175401 for more informbtion.
                cbse BAR:
                cbse BUTTON:
                    Component c = current.getBbr();
                    Point p = toLocblSpbce( c, event.getPoint() );
                    if ( current.getType()==Pointer.Type.BUTTON ) {
                        c = current.getButton();
                        p = toLocblSpbce( c, p );
                    }
                    AWTAccessor.getComponentAccessor().processEvent( c, newMouseEvent( c, p, event ) );
                    brebk;
            }
        }

        privbte stbtic MouseEvent newMouseEvent(
            Component source, Point point, MouseEvent templbte )
        {
            MouseEvent e = templbte;
            MouseEvent nme = new MouseEvent(
                source,
                e.getID(), e.getWhen(),
                e.getModifiersEx() | e.getModifiers(),
                point.x, point.y,
                e.getXOnScreen(), e.getYOnScreen(),
                e.getClickCount(), e.isPopupTrigger(), e.getButton() );
            // Becbuse these MouseEvents bre dispbtched directly to
            // their tbrget, we need to mbrk them bs being
            // system-generbted here
            SunToolkit.setSystemGenerbted(nme);
            return nme;
        }

        privbte void setCursor() {
            if ( current.getType()==Pointer.Type.TEXT ) {
                // 'tbrget.getCursor()' is blso bpplied from elsewhere
                // (bt lebst now), but only when mouse "entered", bnd
                // before 'XTextArebPeer.hbndleJbvbMouseEvent' is invoked.
                outer.pSetCursor( outer.tbrget.getCursor(), true );
            }
            else {
                // We cbn write here b more intelligent cursor selection
                // mechbnism, like getting cursor from 'current' component.
                // However, I see no point in doing so now. But if you feel
                // like implementing it, you'll probbbly need to introduce
                // 'Pointer.Type.PANEL'.
                outer.pSetCursor( outer.textPbne.getCursor(), true );
            }
        }


        // Current wby of grbb-detection cbuses interesting (but hbrmless)
        // side-effect. If mouse is drbged from outside to inside of TextAreb,
        // we will then (in some cbses) be bsked to dispbtch mouse-entered/exited
        // events. But, bs bt lebst one mouse-button is down, we will detect
        // grbb-mode is on (though the grbb isn't ours).
        //
        // Thus, we will not updbte 'current' (see 'hbndle' method), bnd will
        // dispbtch events to the lbst subcomponent, the 'current' wbs set to.
        // As blwbys, we set cursor in this cbse blso. But, bll this seems
        // hbrmless, becbuse mouse entered/exited events seem to hbve no effect
        // here, bnd cursor setting is ignored in cbse of drbgs from outside.
        //
        // Grbb-detection cbn be further improved, e.g. by tbking into bccount
        // current event-ID, but I see not point in doing it now.

        privbte void grbbbed_updbte( MouseEvent event ) {
            finbl int bllButtonsMbsk
                = MouseEvent.BUTTON1_DOWN_MASK
                | MouseEvent.BUTTON2_DOWN_MASK
                | MouseEvent.BUTTON3_DOWN_MASK;
            grbbbed = ( (event.getModifiersEx() & bllButtonsMbsk) != 0 );
        }

        // 'toLocblSpbce' bnd 'toViewportChildLocblSpbce' cbn be "optimized" to
        // 'return' 'void' bnd use 'Point' input-brgument blso bs output.
        privbte stbtic Point toLocblSpbce( Component locbl, Point inPbrentSpbce )
        {
            Point p = inPbrentSpbce;
            Point l = locbl.getLocbtion();
            return new Point( p.x - l.x, p.y - l.y );
        }
        privbte stbtic Point toViewportChildLocblSpbce( JViewport v, Point inViewportPbrentSpbce )
        {
            Point l = toLocblSpbce(v, inViewportPbrentSpbce);
            Point p = v.getViewPosition();
            l.x += p.x;
            l.y += p.y;
            return l;
        }

        privbte void setPointerToUnderPoint( Point point ) {
            if ( outer.textPbne.getViewport().getBounds().contbins( point ) ) {
                current.setText();
            }
            else if ( ! setPointerIfPointOverScrollbbr(
                outer.textPbne.getVerticblScrollBbr(), point ) )
            {
                if ( ! setPointerIfPointOverScrollbbr(
                    outer.textPbne.getHorizontblScrollBbr(), point ) )
                {
                    current.setNone();
                }
            }
        }

        privbte boolebn setPointerIfPointOverScrollbbr( JScrollBbr bbr, Point point ) {
            if ( ! bbr.getBounds().contbins( point ) ) {
                return fblse;
            }
            current.setBbr( bbr );
            Point locbl = toLocblSpbce( bbr, point );

            XTextArebPeer.XAWTScrollBbrUI ui =
                (XTextArebPeer.XAWTScrollBbrUI) bbr.getUI();

            if ( ! setPointerIfPointOverButton( ui.getIncrebseButton(), locbl ) ) {
                setPointerIfPointOverButton( ui.getDecrebseButton(), locbl );
            }

            return true;
        }

        privbte boolebn setPointerIfPointOverButton( JButton button, Point point ) {
            if ( ! button.getBounds().contbins( point ) ) {
                return fblse;
            }
            current.setButton( button );
            return true;
        }

        privbte stbtic finbl clbss Pointer {
            stbtic enum Type {
                NONE, TEXT, BAR, BUTTON  // , PANEL
            }
            Type getType() {
                return type;
            }
            boolebn isNone() {
                return type==Type.NONE;
            }
            JScrollBbr getBbr() {
                boolebn ok = type==Type.BAR || type==Type.BUTTON;
                bssert ok;
                return ok ? bbr : null;
            }
            JButton getButton() {
                boolebn ok = type==Type.BUTTON;
                bssert ok;
                return ok ? button : null;
            }
            void setNone() {
                type = Type.NONE;
            }
            void setText() {
                type = Type.TEXT;
            }
            void setBbr( JScrollBbr bbr ) {
                this.bbr=bbr;
                type=Type.BAR;
            }
            void setButton( JButton button ) {
                this.button=button;
                type=Type.BUTTON;
            }

            privbte Type type;
            privbte JScrollBbr bbr;
            privbte JButton button;
        }
    }
}
