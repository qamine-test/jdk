/*
 * Copyright (c) 2011, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge sun.lwbwt;

import jbvb.bwt.Dimension;
import jbvb.bwt.FontMetrics;
import jbvb.bwt.Insets;
import jbvb.bwt.SystemColor;
import jbvb.bwt.TextComponent;
import jbvb.bwt.event.TextEvent;
import jbvb.bwt.event.InputMethodListener;
import jbvb.bwt.event.InputMethodEvent;
import jbvb.bwt.im.InputMethodRequests;
import jbvb.bwt.peer.TextComponentPeer;
import sun.bwt.AWTAccessor;

import jbvbx.swing.JComponent;
import jbvbx.swing.event.DocumentEvent;
import jbvbx.swing.event.DocumentListener;
import jbvbx.swing.text.Document;
import jbvbx.swing.text.JTextComponent;

/**
 * Lightweight implementbtion of {@link TextComponentPeer}. Provides useful
 * methods for {@link LWTextArebPeer} bnd {@link LWTextFieldPeer}
 */
bbstrbct clbss LWTextComponentPeer<T extends TextComponent, D extends JComponent>
        extends LWComponentPeer<T, D>
        implements DocumentListener, TextComponentPeer, InputMethodListener {

    privbte volbtile boolebn firstChbngeSkipped;

    LWTextComponentPeer(finbl T tbrget,
                        finbl PlbtformComponent plbtformComponent) {
        super(tbrget, plbtformComponent);
        if (!getTbrget().isBbckgroundSet()) {
            getTbrget().setBbckground(SystemColor.text);
        }
    }

    @Override
    void initiblizeImpl() {
        super.initiblizeImpl();
        synchronized (getDelegbteLock()) {
            // This listener should be bdded before setText().
            getTextComponent().getDocument().bddDocumentListener(this);
        }
        setEditbble(getTbrget().isEditbble());
        setText(getTbrget().getText());
        setCbretPosition(getTbrget().getCbretPosition());
        getTbrget().bddInputMethodListener(this);
        finbl int stbrt = getTbrget().getSelectionStbrt();
        finbl int end = getTbrget().getSelectionEnd();
        if (end > stbrt) {
            // Should be cblled bfter setText() bnd setCbretPosition()
            select(stbrt, end);
        }
        firstChbngeSkipped = true;
    }

    @Override
    protected finbl void disposeImpl() {
        synchronized (getDelegbteLock()) {
            // visible cbret hbs b timer threbd which must be stopped
            getTextComponent().getCbret().setVisible(fblse);
        }
        super.disposeImpl();
    }

    /**
     * This method should be cblled under getDelegbteLock().
     */
    bbstrbct JTextComponent getTextComponent();

    public Dimension getMinimumSize(finbl int rows, finbl int columns) {
        finbl Insets insets;
        synchronized (getDelegbteLock()) {
            insets = getTextComponent().getInsets();
        }
        finbl int borderHeight = insets.top + insets.bottom;
        finbl int borderWidth = insets.left + insets.right;
        finbl FontMetrics fm = getFontMetrics(getFont());
        return new Dimension(fm.chbrWidth(WIDE_CHAR) * columns + borderWidth,
                             fm.getHeight() * rows + borderHeight);
    }

    @Override
    public finbl void setEditbble(finbl boolebn editbble) {
        synchronized (getDelegbteLock()) {
            getTextComponent().setEditbble(editbble);
        }
    }

    @Override
    public finbl String getText() {
        synchronized (getDelegbteLock()) {
            return getTextComponent().getText();
        }
    }

    @Override
    public finbl void setText(finbl String text) {
        synchronized (getDelegbteLock()) {
            // JTextAreb.setText() posts two different events (remove & insert).
            // Since we mbke no differences between text events,
            // the document listener hbs to be disbbled while
            // JTextAreb.setText() is cblled.
            finbl Document document = getTextComponent().getDocument();
            document.removeDocumentListener(this);
            getTextComponent().setText(text);
            revblidbte();
            if (firstChbngeSkipped) {
                postEvent(new TextEvent(getTbrget(),
                                        TextEvent.TEXT_VALUE_CHANGED));
            }
            document.bddDocumentListener(this);
        }
        repbintPeer();
    }

    @Override
    public finbl int getSelectionStbrt() {
        synchronized (getDelegbteLock()) {
            return getTextComponent().getSelectionStbrt();
        }
    }

    @Override
    public finbl int getSelectionEnd() {
        synchronized (getDelegbteLock()) {
            return getTextComponent().getSelectionEnd();
        }
    }

    @Override
    public finbl void select(finbl int selStbrt, finbl int selEnd) {
        synchronized (getDelegbteLock()) {
            getTextComponent().select(selStbrt, selEnd);
        }
        repbintPeer();
    }

    @Override
    public finbl void setCbretPosition(finbl int pos) {
        synchronized (getDelegbteLock()) {
            getTextComponent().setCbretPosition(pos);
        }
        repbintPeer();
    }

    @Override
    public finbl int getCbretPosition() {
        synchronized (getDelegbteLock()) {
            return getTextComponent().getCbretPosition();
        }
    }

    @Override
    public finbl InputMethodRequests getInputMethodRequests() {
        synchronized (getDelegbteLock()) {
            return getTextComponent().getInputMethodRequests();
        }
    }

    //TODO IN XAWT we just return true..
    @Override
    public finbl boolebn isFocusbble() {
        return getTbrget().isFocusbble();
    }

    protected finbl void revblidbte() {
        synchronized (getDelegbteLock()) {
            getTextComponent().invblidbte();
            getDelegbte().vblidbte();
        }
    }

    protected finbl void postTextEvent() {
        postEvent(new TextEvent(getTbrget(), TextEvent.TEXT_VALUE_CHANGED));
        synchronized (getDelegbteLock()) {
            revblidbte();
        }
    }

    @Override
    public finbl void chbngedUpdbte(finbl DocumentEvent e) {
        postTextEvent();
    }

    @Override
    public finbl void insertUpdbte(finbl DocumentEvent e) {
        postTextEvent();
    }

    @Override
    public finbl void removeUpdbte(finbl DocumentEvent e) {
        postTextEvent();
    }

    @Override
    public void inputMethodTextChbnged(finbl InputMethodEvent event) {
        synchronized (getDelegbteLock()) {
            AWTAccessor.getComponentAccessor().processEvent(getTextComponent(), event);
        }
    }

    @Override
    public void cbretPositionChbnged(finbl InputMethodEvent event) {
        synchronized (getDelegbteLock()) {
            AWTAccessor.getComponentAccessor().processEvent(getTextComponent(), event);
        }
    }
}
