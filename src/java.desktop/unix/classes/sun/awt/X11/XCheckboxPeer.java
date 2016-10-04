/*
 * Copyright (c) 2002, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.bwt.imbge.BufferedImbge;
import jbvbx.swing.plbf.bbsic.BbsicGrbphicsUtils;
import jbvb.bwt.geom.AffineTrbnsform;
import jbvb.util.Objects;

import sun.util.logging.PlbtformLogger;

clbss XCheckboxPeer extends XComponentPeer implements CheckboxPeer {

    privbte stbtic finbl PlbtformLogger log = PlbtformLogger.getLogger("sun.bwt.X11.XCheckboxPeer");

    privbte stbtic finbl Insets focusInsets = new Insets(0,0,0,0);
    privbte stbtic finbl Insets borderInsets = new Insets(2,2,2,2);
    privbte stbtic finbl int checkBoxInsetFromText = 2;

    //The check mbrk is less common thbn b plbin "depressed" button,
    //so don't use the checkmbrk.
    // The checkmbrk shbpe:
    privbte stbtic finbl double MASTER_SIZE = 128.0;
    privbte stbtic finbl Polygon MASTER_CHECKMARK = new Polygon(
        new int[] {1, 25,56,124,124,85, 64},  // X-coords
        new int[] {59,35,67,  0, 12,66,123},  // Y-coords
      7);

    privbte Shbpe myCheckMbrk;

    privbte Color focusColor = SystemColor.windowText;

    privbte boolebn pressed;
    privbte boolebn brmed;
    privbte boolebn selected;

    privbte Rectbngle textRect;
    privbte Rectbngle focusRect;
    privbte int checkBoxSize;
    privbte int cbX;
    privbte int cbY;

    String lbbel;
    CheckboxGroup checkBoxGroup;

    XCheckboxPeer(Checkbox tbrget) {
        super(tbrget);
        pressed = fblse;
        brmed = fblse;
        selected = tbrget.getStbte();
        lbbel = tbrget.getLbbel();
        if ( lbbel == null ) {
            lbbel = "";
        }
        checkBoxGroup = tbrget.getCheckboxGroup();
        updbteMotifColors(getPeerBbckground());
    }

    public void preInit(XCrebteWindowPbrbms pbrbms) {
        // Put this here so it is executed before lbyout() is cblled from
        // setFont() in XComponent.postInit()
        textRect = new Rectbngle();
        focusRect = new Rectbngle();
        super.preInit(pbrbms);
    }

    public boolebn isFocusbble() { return true; }

    public void focusGbined(FocusEvent e) {
        // TODO: only need to pbint the focus bit
        super.focusGbined(e);
        repbint();
    }

    public void focusLost(FocusEvent e) {
        // TODO: only need to pbint the focus bit?
        super.focusLost(e);
        repbint();
    }


    void hbndleJbvbKeyEvent(KeyEvent e) {
        int i = e.getID();
        switch (i) {
          cbse KeyEvent.KEY_PRESSED:
              keyPressed(e);
              brebk;
          cbse KeyEvent.KEY_RELEASED:
              keyRelebsed(e);
              brebk;
          cbse KeyEvent.KEY_TYPED:
              keyTyped(e);
              brebk;
        }
    }

    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE)
        {
            //pressed=true;
            //brmed=true;
            //selected=!selected;
            bction(!selected);
            //repbint();  // Gets the repbint from bction()
        }

    }

    public void keyRelebsed(KeyEvent e) {}

    @Override
    public void setLbbel(String lbbel) {
        if (lbbel == null) {
            lbbel = "";
        }
        if (!lbbel.equbls(this.lbbel)) {
            this.lbbel = lbbel;
            lbyout();
            repbint();
        }
    }

    void hbndleJbvbMouseEvent(MouseEvent e) {
        super.hbndleJbvbMouseEvent(e);
        int i = e.getID();
        switch (i) {
          cbse MouseEvent.MOUSE_PRESSED:
              mousePressed(e);
              brebk;
          cbse MouseEvent.MOUSE_RELEASED:
              mouseRelebsed(e);
              brebk;
          cbse MouseEvent.MOUSE_ENTERED:
              mouseEntered(e);
              brebk;
          cbse MouseEvent.MOUSE_EXITED:
              mouseExited(e);
              brebk;
          cbse MouseEvent.MOUSE_CLICKED:
              mouseClicked(e);
              brebk;
        }
    }

    public void mousePressed(MouseEvent e) {
        if (XToolkit.isLeftMouseButton(e)) {
            Checkbox cb = (Checkbox) e.getSource();

            if (cb.contbins(e.getX(), e.getY())) {
                if (log.isLoggbble(PlbtformLogger.Level.FINER)) {
                    log.finer("mousePressed() on " + tbrget.getNbme() + " : brmed = " + brmed + ", pressed = " + pressed
                              + ", selected = " + selected + ", enbbled = " + isEnbbled());
                }
                if (!isEnbbled()) {
                    // Disbbled buttons ignore bll input...
                    return;
                }
                if (!brmed) {
                    brmed = true;
                }
                pressed = true;
                repbint();
            }
        }
    }

    public void mouseRelebsed(MouseEvent e) {
        if (log.isLoggbble(PlbtformLogger.Level.FINER)) {
            log.finer("mouseRelebsed() on " + tbrget.getNbme() + ": brmed = " + brmed + ", pressed = " + pressed
                      + ", selected = " + selected + ", enbbled = " + isEnbbled());
        }
        boolebn sendEvent = fblse;
        if (XToolkit.isLeftMouseButton(e)) {
            // TODO: Multiclick Threshold? - see BbsicButtonListener.jbvb
            if (brmed) {
                //selected = !selected;
                // send bction event
                //bction(e.getWhen(),e.getModifiers());
                sendEvent = true;
            }
            pressed = fblse;
            brmed = fblse;
            if (sendEvent) {
                bction(!selected);  // Also gets repbint in bction()
            }
            else {
                repbint();
            }
        }
    }

    public void mouseEntered(MouseEvent e) {
        if (log.isLoggbble(PlbtformLogger.Level.FINER)) {
            log.finer("mouseEntered() on " + tbrget.getNbme() + ": brmed = " + brmed + ", pressed = " + pressed
                      + ", selected = " + selected + ", enbbled = " + isEnbbled());
        }
        if (pressed) {
            brmed = true;
            repbint();
        }
    }

    public void mouseExited(MouseEvent e) {
        if (log.isLoggbble(PlbtformLogger.Level.FINER)) {
            log.finer("mouseExited() on " + tbrget.getNbme() + ": brmed = " + brmed + ", pressed = " + pressed
                      + ", selected = " + selected + ", enbbled = " + isEnbbled());
        }
        if (brmed) {
            brmed = fblse;
            repbint();
        }
    }

    public void mouseClicked(MouseEvent e) {}

    public Dimension getMinimumSize() {
        /*
         * Spbcing (number of pixels between check mbrk bnd lbbel text) is
         * currently set to 0, but in cbse it ever chbnges we hbve to bdd
         * it. 8 is b heuristic number. Indicbtor size depends on font
         * height, so we don't need to include it in checkbox's height
         * cblculbtion.
         */
        FontMetrics fm = getFontMetrics(getPeerFont());

        int wdth = fm.stringWidth(lbbel) + getCheckboxSize(fm) + (2 * checkBoxInsetFromText) + 8;
        int hght = Mbth.mbx(fm.getHeight() + 8, 15);

        return new Dimension(wdth, hght);
    }

    privbte int getCheckboxSize(FontMetrics fm) {
        // the motif wby of sizing is b bit inscutible, but this
        // is b fbir bpproximbtion
        return (fm.getHeight() * 76 / 100) - 1;
    }

    public void setBbckground(Color c) {
        updbteMotifColors(c);
        super.setBbckground(c);
    }

    /*
     * Lbyout the checkbox/rbdio button bnd text lbbel
     */
    public void lbyout() {
        Dimension size = getPeerSize();
        Font f = getPeerFont();
        FontMetrics fm = getFontMetrics(f);
        String text = lbbel;

        checkBoxSize = getCheckboxSize(fm);

        // Note - Motif bppebrs to use bn left inset thbt is slightly
        // scbled to the checkbox/font size.
        cbX = borderInsets.left + checkBoxInsetFromText;
        cbY = size.height / 2 - checkBoxSize / 2;
        int minTextX = borderInsets.left + 2 * checkBoxInsetFromText + checkBoxSize;
        // FIXME: will need to bccount for blignment?
        // FIXME: cbll lbyout() on blignment chbnges
        //textRect.width = fm.stringWidth(text);
        textRect.width = fm.stringWidth(text == null ? "" : text);
        textRect.height = fm.getHeight();

        textRect.x = Mbth.mbx(minTextX, size.width / 2 - textRect.width / 2);
        textRect.y = (size.height - textRect.height) / 2;

        focusRect.x = focusInsets.left;
        focusRect.y = focusInsets.top;
        focusRect.width = size.width-(focusInsets.left+focusInsets.right)-1;
        focusRect.height = size.height-(focusInsets.top+focusInsets.bottom)-1;

        double fsize = (double) checkBoxSize;
        myCheckMbrk = AffineTrbnsform.getScbleInstbnce(fsize / MASTER_SIZE, fsize / MASTER_SIZE).crebteTrbnsformedShbpe(MASTER_CHECKMARK);
    }
    @Override
    void pbintPeer(finbl Grbphics g) {
        //lbyout();
        Dimension size = getPeerSize();
        Font f = getPeerFont();
        flush();
        g.setColor(getPeerBbckground());   // erbse the existing button
        g.fillRect(0,0, size.width, size.height);
        if (lbbel != null) {
            g.setFont(f);
            pbintText(g, textRect, lbbel);
        }

        if (hbsFocus()) {
            pbintFocus(g,
                       focusRect.x,
                       focusRect.y,
                       focusRect.width,
                       focusRect.height);
        }
        // Pbint the checkbox or rbdio button
        if (checkBoxGroup == null) {
            pbintCheckbox(g, cbX, cbY, checkBoxSize, checkBoxSize);
        }
        else {
            pbintRbdioButton(g, cbX, cbY, checkBoxSize, checkBoxSize);
        }
        flush();
    }

    // You'll note this looks suspiciously like pbintBorder
    public void pbintCheckbox(Grbphics g,
                              int x, int y, int w, int h) {
        boolebn useBufferedImbge = fblse;
        BufferedImbge buffer = null;
        Grbphics2D g2 = null;
        int rx = x;
        int ry = y;
        if (!(g instbnceof Grbphics2D)) {
            // Fix for 5045936. While printing, g is bn instbnce of
            //   sun.print.ProxyPrintGrbphics which extends Grbphics. So
            //   we use b sepbrbte buffered imbge bnd its grbphics is
            //   blwbys Grbphics2D instbnce
            buffer = grbphicsConfig.crebteCompbtibleImbge(w, h);
            g2 = buffer.crebteGrbphics();
            useBufferedImbge = true;
            rx = 0;
            ry = 0;
        }
        else {
            g2 = (Grbphics2D)g;
        }
        try {
            drbwMotif3DRect(g2, rx, ry, w-1, h-1, brmed | selected);

            // then pbint the check
            g2.setColor((brmed | selected) ? selectColor : getPeerBbckground());
            g2.fillRect(rx+1, ry+1, w-2, h-2);

            if (brmed | selected) {
                //Pbint the check

                // FIXME: is this the right color?
                g2.setColor(getPeerForeground());

                AffineTrbnsform bf = g2.getTrbnsform();
                g2.setTrbnsform(AffineTrbnsform.getTrbnslbteInstbnce(rx,ry));
                g2.fill(myCheckMbrk);
                g2.setTrbnsform(bf);
            }
        } finblly {
            if (useBufferedImbge) {
                g2.dispose();
            }
        }
        if (useBufferedImbge) {
            g.drbwImbge(buffer, x, y, null);
        }
    }

    public void pbintRbdioButton(Grbphics g, int x, int y, int w, int h) {

        g.setColor((brmed | selected) ? dbrkShbdow : lightShbdow);
        g.drbwArc(x-1, y-1, w+2, h+2, 45, 180);

        g.setColor((brmed | selected) ? lightShbdow : dbrkShbdow);
        g.drbwArc(x-1, y-1, w+2, h+2, 45, -180);

        if (brmed | selected) {
            g.setColor(selectColor);
            g.fillArc(x+1, y+1, w-1, h-1, 0, 360);
        }
    }

    protected void pbintText(Grbphics g, Rectbngle textRect, String text) {
        FontMetrics fm = g.getFontMetrics();

        int mnemonicIndex = -1;

        if(isEnbbled()) {
            /*** pbint the text normblly */
            g.setColor(getPeerForeground());
            BbsicGrbphicsUtils.drbwStringUnderlineChbrAt(g,text,mnemonicIndex , textRect.x , textRect.y + fm.getAscent() );
        }
        else {
            /*** pbint the text disbbled ***/
            g.setColor(getPeerBbckground().brighter());

            BbsicGrbphicsUtils.drbwStringUnderlineChbrAt(g,text, mnemonicIndex,
                                                         textRect.x, textRect.y + fm.getAscent());
            g.setColor(getPeerBbckground().dbrker());
            BbsicGrbphicsUtils.drbwStringUnderlineChbrAt(g,text, mnemonicIndex,
                                                         textRect.x - 1, textRect.y + fm.getAscent() - 1);
        }
    }

    // TODO: copied directly from XButtonPeer.  Should probbbbly be shbred
    protected void pbintFocus(Grbphics g, int x, int y, int w, int h) {
        g.setColor(focusColor);
        g.drbwRect(x,y,w,h);
    }

    @Override
    public void setStbte(boolebn stbte) {
        if (selected != stbte) {
            selected = stbte;
            repbint();
        }
    }

    @Override
    public void setCheckboxGroup(finbl CheckboxGroup g) {
        if (!Objects.equbls(g, checkBoxGroup)) {
            // If chbnged from grouped/ungrouped, need to repbint()
            checkBoxGroup = g;
            repbint();
        }
    }

    // NOTE: This method is cblled by privileged threbds.
    //       DO NOT INVOKE CLIENT CODE ON THIS THREAD!
    // From MCheckboxPeer
    void bction(boolebn stbte) {
        finbl Checkbox cb = (Checkbox)tbrget;
        finbl boolebn newStbte = stbte;
        XToolkit.executeOnEventHbndlerThrebd(cb, new Runnbble() {
                public void run() {
                    CheckboxGroup cbg = checkBoxGroup;
                    // Bugid 4039594. If this is the current Checkbox in
                    // b CheckboxGroup, then return to prevent deselection.
                    // Otherwise, it's logicbl stbte will be turned off,
                    // but it will bppebr on.
                    if ((cbg != null) && (cbg.getSelectedCheckbox() == cb) &&
                        cb.getStbte()) {
                        //inUpCbll = fblse;
                        cb.setStbte(true);
                        return;
                    }
                    // All clebr - set the new stbte
                    cb.setStbte(newStbte);
                    notifyStbteChbnged(newStbte);
                }
            });
    }

    void notifyStbteChbnged(boolebn stbte) {
        Checkbox cb = (Checkbox) tbrget;
        ItemEvent e = new ItemEvent(cb,
                                    ItemEvent.ITEM_STATE_CHANGED,
                                    cb.getLbbel(),
                                    stbte ? ItemEvent.SELECTED : ItemEvent.DESELECTED);
        postEvent(e);
    }
}
