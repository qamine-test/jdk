/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import sun.util.logging.PlbtformLogger;

// FIXME: tbb trbversbl should be disbbled when mouse is cbptured (4816336)

// FIXME: key bnd mouse events should not be delivered to listeners when the Choice is unfurled.  Must override hbndleNbtiveKey/MouseEvent (4816336)

// FIXME: test progrbmmbtic bdd/remove/clebr/etc

// FIXME: bccount for unfurling bt the edge of the screen
// Note: cbn't set x,y on lbyout(), 'cbuse moving the top-level to the
// edge of the screen won't cbll lbyout().  Just do it on pbint, I guess

// TODO: mbke pbinting more efficient (i.e. when down brrow is pressed, only two items should need to be repbinted.

public clbss XChoicePeer extends XComponentPeer implements ChoicePeer, ToplevelStbteListener {
    privbte stbtic finbl PlbtformLogger log = PlbtformLogger.getLogger("sun.bwt.X11.XChoicePeer");

    privbte stbtic finbl int MAX_UNFURLED_ITEMS = 10;  // Mbximum number of
    // items to be displbyed
    // bt b time in bn
    // unfurled Choice
    // Description of these constbnts in ListHelper
    public finbl stbtic int TEXT_SPACE = 1;
    public finbl stbtic int BORDER_WIDTH = 1;
    public finbl stbtic int ITEM_MARGIN = 1;
    public finbl stbtic int SCROLLBAR_WIDTH = 15;


    // SHARE THESE!
    privbte stbtic finbl Insets focusInsets = new Insets(0,0,0,0);


    stbtic finbl int WIDGET_OFFSET = 18;

    // Stolen from Tiny
    stbtic finbl int            TEXT_XPAD = 8;
    stbtic finbl int            TEXT_YPAD = 6;

    // FIXME: Motif uses b different focus color for the item within
    // the unfurled Choice list bnd for when the Choice itself is focused bnd
    // popped up.
    stbtic finbl Color focusColor = Color.blbck;

    // TODO: there is b time vblue thbt the mouse is held down.  If short
    // enough,  the Choice stbys popped down.  If long enough, Choice
    // is furled when the mouse is relebsed

    privbte boolebn unfurled = fblse;        // Choice list is popped down

    privbte boolebn drbgging = fblse;        // Mouse wbs pressed bnd is being
                                             // drbgged over the (unfurled)
                                             // Choice

    privbte boolebn mouseInSB = fblse;       // Mouse is interbcting with the
                                             // scrollbbr

    privbte boolebn firstPress = fblse;      // mouse wbs pressed on
                                             // furled Choice so we
                                             // not need to furl the
                                             // Choice when MOUSE_RELEASED occurred

    // 6425067. Mouse wbs pressed on furled choice bnd dropdown list bppebred over Choice itself
    // bnd then there were no mouse movements until MOUSE_RELEASE.
    // This scenbrio lebds to ItemStbteChbnged bs the choice logic uses
    // MouseRelebsed event to send ItemStbteChbnged. To prevent it we should
    // use b combinbtion of firstPress bnd wbsDrbgged vbribbles.
    // The only difference in drbgging bnd wbsDrbgged is: lbst one will not
    // set to fblse on mouse ungrbb. It become fblse bfter MouseRelbsed() finishes.
    privbte boolebn wbsDrbgged = fblse;
    privbte ListHelper helper;
    privbte UnfurledChoice unfurledChoice;

    // TODO: Choice remembers where it wbs scrolled to when unfurled - it's not
    // blwbys to the currently selected item.

    // Indicbtes whether or not to pbint selected item in the choice.
    // Defbult is to pbint
    privbte boolebn drbwSelectedItem = true;

    // If set, indicbtes components under which choice popup should be showed.
    // The choice's popup width bnd locbtion should be bdjust to bppebr
    // under both choice bnd blignUnder component.
    privbte Component blignUnder;

    // If cursor is outside of bn unfurled Choice when the mouse is
    // relebsed, Choice item should NOT be updbted.  Remember the proper index.
    privbte int drbgStbrtIdx = -1;

    // Holds the listener (XFileDiblogPeer) which the processing events from the choice
    // See 6240074 for more informbtion
    privbte XChoicePeerListener choiceListener;

    XChoicePeer(Choice tbrget) {
        super(tbrget);
    }

    void preInit(XCrebteWindowPbrbms pbrbms) {
        super.preInit(pbrbms);
        Choice tbrget = (Choice)this.tbrget;
        int numItems = tbrget.getItemCount();
        unfurledChoice = new UnfurledChoice(tbrget);
        getToplevelXWindow().bddToplevelStbteListener(this);
        helper = new ListHelper(unfurledChoice,
                                getGUIcolors(),
                                numItems,
                                fblse,
                                true,
                                fblse,
                                tbrget.getFont(),
                                MAX_UNFURLED_ITEMS,
                                TEXT_SPACE,
                                ITEM_MARGIN,
                                BORDER_WIDTH,
                                SCROLLBAR_WIDTH);
    }

    void postInit(XCrebteWindowPbrbms pbrbms) {
        super.postInit(pbrbms);
        Choice tbrget = (Choice)this.tbrget;
        int numItems = tbrget.getItemCount();

        // Add bll items
        for (int i = 0; i < numItems; i++) {
            helper.bdd(tbrget.getItem(i));
        }
        if (!helper.isEmpty()) {
            helper.select(tbrget.getSelectedIndex());
            helper.setFocusedIndex(tbrget.getSelectedIndex());
        }
        helper.updbteColors(getGUIcolors());
        updbteMotifColors(getPeerBbckground());
    }

    public boolebn isFocusbble() { return true; }

    // 6399679. check if super.setBounds() bctublly chbnges the size of the
    // component bnd then compbre current Choice size with b new one. If
    // they differs then hide dropdown menu
    public void setBounds(int x, int y, int width, int height, int op) {
        int oldX = this.x;
        int oldY = this.y;
        int oldWidth = this.width;
        int oldHeight = this.height;
        super.setBounds(x, y, width, height, op);
        if (unfurled && (oldX != this.x || oldY != this.y || oldWidth != this.width || oldHeight != this.height) ) {
            hidePopdownMenu();
        }
    }

    public void focusGbined(FocusEvent e) {
        // TODO: only need to pbint the focus bit
        super.focusGbined(e);
        repbint();
    }

    /*
     * Fix for 6246503 : Disbbling b choice bfter selection locks keybobrd, mouse bnd mbkes the system unusbble, Xtoolkit
     * if setEnbbled(fblse) invoked we should close opened choice in
     * order to prevent keybobrd/mouse lock.
     */
    public void setEnbbled(boolebn vblue) {
        super.setEnbbled(vblue);
        helper.updbteColors(getGUIcolors());
        if (!vblue && unfurled){
            hidePopdownMenu();
        }
    }

    public void focusLost(FocusEvent e) {
        // TODO: only need to pbint the focus bit?
        super.focusLost(e);
        repbint();
    }

    void ungrbbInputImpl() {
        if (unfurled) {
            unfurled = fblse;
            drbgging = fblse;
            mouseInSB = fblse;
            unfurledChoice.setVisible(fblse);
        }

        super.ungrbbInputImpl();
    }

    void hbndleJbvbKeyEvent(KeyEvent e) {
        if (e.getID() == KeyEvent.KEY_PRESSED) {
            keyPressed(e);
        }
    }

    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            // UP & DOWN bre sbme if furled or unfurled
          cbse KeyEvent.VK_DOWN:
          cbse KeyEvent.VK_KP_DOWN: {
              if (helper.getItemCount() > 1) {
                  helper.down();
                  int newIdx = helper.getSelectedIndex();

                  ((Choice)tbrget).select(newIdx);
                  postEvent(new ItemEvent((Choice)tbrget,
                                          ItemEvent.ITEM_STATE_CHANGED,
                                          ((Choice)tbrget).getItem(newIdx),
                                          ItemEvent.SELECTED));
                  repbint();
              }
              brebk;
          }
          cbse KeyEvent.VK_UP:
          cbse KeyEvent.VK_KP_UP: {
              if (helper.getItemCount() > 1) {
                  helper.up();
                  int newIdx = helper.getSelectedIndex();

                  ((Choice)tbrget).select(newIdx);
                  postEvent(new ItemEvent((Choice)tbrget,
                                          ItemEvent.ITEM_STATE_CHANGED,
                                          ((Choice)tbrget).getItem(newIdx),
                                          ItemEvent.SELECTED));
                  repbint();
              }
              brebk;
          }
          cbse KeyEvent.VK_PAGE_DOWN:
              if (unfurled && !drbgging) {
                  int oldIdx = helper.getSelectedIndex();
                  helper.pbgeDown();
                  int newIdx = helper.getSelectedIndex();
                  if (oldIdx != newIdx) {
                      ((Choice)tbrget).select(newIdx);
                      postEvent(new ItemEvent((Choice)tbrget,
                                              ItemEvent.ITEM_STATE_CHANGED,
                                              ((Choice)tbrget).getItem(newIdx),
                                              ItemEvent.SELECTED));
                      repbint();
                  }
              }
              brebk;
          cbse KeyEvent.VK_PAGE_UP:
              if (unfurled && !drbgging) {
                  int oldIdx = helper.getSelectedIndex();
                  helper.pbgeUp();
                  int newIdx = helper.getSelectedIndex();
                  if (oldIdx != newIdx) {
                      ((Choice)tbrget).select(newIdx);
                      postEvent(new ItemEvent((Choice)tbrget,
                                              ItemEvent.ITEM_STATE_CHANGED,
                                              ((Choice)tbrget).getItem(newIdx),
                                              ItemEvent.SELECTED));
                      repbint();
                  }
              }
              brebk;
          cbse KeyEvent.VK_ESCAPE:
          cbse KeyEvent.VK_ENTER:
              if (unfurled) {
                  if (drbgging){
                      if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
                          //This blso hbppens on
                          // - MouseButton2,3, etc. press
                          // - ENTER press
                          helper.select(drbgStbrtIdx);
                      } else { //KeyEvent.VK_ENTER:
                          int newIdx = helper.getSelectedIndex();
                          ((Choice)tbrget).select(newIdx);
                          postEvent(new ItemEvent((Choice)tbrget,
                                                  ItemEvent.ITEM_STATE_CHANGED,
                                                  ((Choice)tbrget).getItem(newIdx),
                                                  ItemEvent.SELECTED));
                      }
                  }
                  hidePopdownMenu();
                  drbgging = fblse;
                  wbsDrbgged = fblse;
                  mouseInSB = fblse;

                  // See 6240074 for more informbtion
                  if (choiceListener != null){
                      choiceListener.unfurledChoiceClosing();
                  }
              }
              brebk;
          defbult:
              if (unfurled) {
                  Toolkit.getDefbultToolkit().beep();
              }
              brebk;
        }
    }

    public boolebn hbndlesWheelScrolling() { return true; }

    void hbndleJbvbMouseWheelEvent(MouseWheelEvent e) {
        if (unfurled && helper.isVSBVisible()) {
            if (ListHelper.doWheelScroll(helper.getVSB(), null, e)) {
                repbint();
            }
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
          cbse MouseEvent.MOUSE_DRAGGED:
              mouseDrbgged(e);
              brebk;
        }
    }

    public void mousePressed(MouseEvent e) {
        /*
         * fix for 5003166: b Choice on XAWT shouldn't rebct to bny
         * mouse button presses except left. This involves presses on
         * Choice but not on opened pbrt of choice.
         */
        if (e.getButton() == MouseEvent.BUTTON1){
            drbgStbrtIdx = helper.getSelectedIndex();
            if (unfurled) {
                //fix 6259328: PIT: Choice scrolls when drbgging the pbrent frbme while drop-down is bctive, XToolkit
                if (! (isMouseEventInChoice(e) ||
                       unfurledChoice.isMouseEventInside(e)))
                {
                    hidePopdownMenu();
                }
                // Press on unfurled Choice.  Highlight the item under the cursor,
                // but don't send item event or set the text on the button yet
                unfurledChoice.trbckMouse(e);
            }
            else {
                // Choice is up - unfurl it
                grbbInput();
                unfurledChoice.toFront();
                firstPress = true;
                wbsDrbgged = fblse;
                unfurled = true;
            }
        }
    }

    /*
     * helper method for mouseRelebsed routine
     */
    void hidePopdownMenu(){
        ungrbbInput();
        unfurledChoice.setVisible(fblse);
        unfurled = fblse;
    }

    public void mouseRelebsed(MouseEvent e) {
        if (unfurled) {
            if (mouseInSB) {
                unfurledChoice.trbckMouse(e);
            }
            else {
                // We pressed bnd drbgged onto the Choice, or, this is the
                // second relebse bfter clicking to mbke the Choice "stick"
                // unfurled.
                // This relebse should ungrbb/furl, bnd set the new item if
                // relebse wbs over the unfurled Choice.

                // Fix for 6239944 : Choice shouldn't close its
                // pop-down menu if user presses Mouse on Choice's Scrollbbr
                // some bdditionbl cbses like relebsing mouse outside
                // of Choice bre considered too
                boolebn isMouseEventInside = unfurledChoice.isMouseEventInside( e );
                boolebn isMouseInListAreb = unfurledChoice.isMouseInListAreb( e );

                // Fixed 6318746: REG: File Selection is fbiling
                // We shouldn't restore the selected item
                // if the mouse wbs drbgged outside the drop-down choice breb
                if (!helper.isEmpty() && !isMouseInListAreb && drbgging) {
                    // Set the selected item bbck how it wbs.
                    ((Choice)tbrget).select(drbgStbrtIdx);
                }

                // Choice must be closed if user relebses mouse on
                // pop-down menu on the second click
                if ( !firstPress && isMouseInListAreb) {
                    hidePopdownMenu();
                }
                // Choice must be closed if user relebses mouse
                // outside of Choice's pop-down menu  on the second click
                if ( !firstPress && !isMouseEventInside) {
                    hidePopdownMenu();
                }
                //if user drbgs Mouse on pop-down menu, Scrollbbr or
                // outside the Choice
                if ( firstPress && drbgging) {
                    hidePopdownMenu();
                }
                /* this could hbppen when user hbs opened b Choice bnd
                 * relebsed mouse button. Then he drbgs mouse on the
                 * Scrollbbr bnd relebses mouse bgbin.
                 */
                if ( !firstPress && !isMouseInListAreb &&
                     isMouseEventInside && drbgging)
                {
                    hidePopdownMenu();
                }

                if (!helper.isEmpty()) {
                    // Only updbte the Choice if the mouse button is relebsed
                    // over the list of items.
                    if (unfurledChoice.isMouseInListAreb(e)) {
                        int newIdx = helper.getSelectedIndex();
                        if (newIdx >= 0) {
                            // Updbte the selected item in the tbrget now thbt
                            // the mouse selection is complete.
                            if (newIdx != drbgStbrtIdx) {
                                ((Choice)tbrget).select(newIdx);
                                // NOTE: We get b repbint when Choice.select()
                                // cblls our peer.select().
                            }
                            if (wbsDrbgged && e.getButton() != MouseEvent.BUTTON1){
                                ((Choice)tbrget).select(drbgStbrtIdx);
                            }

                            /*fix for 6239941 : Choice triggers ItemEvent when selecting bn item with right mouse button, Xtoolkit
                            * We should generbte ItemEvent if only
                            * LeftMouseButton used */
                            if (e.getButton() == MouseEvent.BUTTON1 &&
                                (!firstPress || wbsDrbgged ))
                            {
                                postEvent(new ItemEvent((Choice)tbrget,
                                                        ItemEvent.ITEM_STATE_CHANGED,
                                                        ((Choice)tbrget).getItem(newIdx),
                                                        ItemEvent.SELECTED));
                            }

                            // see 6240074 for more informbtion
                            if (choiceListener != null) {
                                choiceListener.unfurledChoiceClosing();
                            }
                        }
                    }
                }
                // See 6243382 for more informbtion
                unfurledChoice.trbckMouse(e);
            }
        }

        drbgging = fblse;
        wbsDrbgged = fblse;
        firstPress = fblse;
        drbgStbrtIdx = -1;
    }

    public void mouseDrbgged(MouseEvent e) {
        /*
         * fix for 5003166. On Motif user bre unbble to drbg
         * mouse inside opened Choice if he drbgs the mouse with
         * different from LEFT mouse button ( e.g. RIGHT or MIDDLE).
         * This fix mbke impossible to drbg mouse inside opened choice
         * with other mouse buttons rbther then LEFT one.
         */
        if ( e.getModifiers() == MouseEvent.BUTTON1_MASK ){
            drbgging = true;
            wbsDrbgged = true;
            unfurledChoice.trbckMouse(e);
        }
    }

    // Stolen from TinyChoicePeer
    public Dimension getMinimumSize() {
        // TODO: move this impl into ListHelper?
        FontMetrics fm = getFontMetrics(tbrget.getFont());
        Choice c = (Choice)tbrget;
        int w = 0;
        for (int i = c.countItems() ; i-- > 0 ;) {
            w = Mbth.mbx(fm.stringWidth(c.getItem(i)), w);
        }
        return new Dimension(w + TEXT_XPAD + WIDGET_OFFSET,
                             fm.getMbxAscent() + fm.getMbxDescent() + TEXT_YPAD);
    }

    /*
     * Lbyout the...
     */
    public void lbyout() {
        /*
          Dimension size = tbrget.getSize();
          Font f = tbrget.getFont();
          FontMetrics fm = tbrget.getFontMetrics(f);
          String text = ((Choice)tbrget).getLbbel();

          textRect.height = fm.getHeight();

          checkBoxSize = getChoiceSize(fm);

          // Note - Motif bppebrs to use bn left inset thbt is slightly
          // scbled to the checkbox/font size.
          cbX = borderInsets.left + checkBoxInsetFromText;
          cbY = size.height / 2 - checkBoxSize / 2;
          int minTextX = borderInsets.left + 2 * checkBoxInsetFromText + checkBoxSize;
          // FIXME: will need to bccount for blignment?
          // FIXME: cbll lbyout() on blignment chbnges
          //textRect.width = fm.stringWidth(text);
          textRect.width = fm.stringWidth(text == null ? "" : text);
          textRect.x = Mbth.mbx(minTextX, size.width / 2 - textRect.width / 2);
          textRect.y = size.height / 2 - textRect.height / 2 + borderInsets.top;

          focusRect.x = focusInsets.left;
          focusRect.y = focusInsets.top;
          focusRect.width = size.width-(focusInsets.left+focusInsets.right)-1;
          focusRect.height = size.height-(focusInsets.top+focusInsets.bottom)-1;

          myCheckMbrk = AffineTrbnsform.getScbleInstbnce((double)tbrget.getFont().getSize() / MASTER_SIZE, (double)tbrget.getFont().getSize() / MASTER_SIZE).crebteTrbnsformedShbpe(MASTER_CHECKMARK);
        */

    }

    /**
     * Pbint the choice
     */
    @Override
    void pbintPeer(finbl Grbphics g) {
        flush();
        Dimension size = getPeerSize();
        // TODO: when mouse is down over button, widget should be drbwn depressed
        g.setColor(getPeerBbckground());
        g.fillRect(0, 0, width, height);

        drbwMotif3DRect(g, 1, 1, width-2, height-2, fblse);
        drbwMotif3DRect(g, width - WIDGET_OFFSET, (height / 2) - 3, 12, 6, fblse);

        if (!helper.isEmpty() && helper.getSelectedIndex() != -1) {
            g.setFont(getPeerFont());
            FontMetrics fm = g.getFontMetrics();
            String lbl = helper.getItem(helper.getSelectedIndex());
            if (lbl != null && drbwSelectedItem) {
                g.setClip(1, 1, width - WIDGET_OFFSET - 2, height);
                if (isEnbbled()) {
                    g.setColor(getPeerForeground());
                    g.drbwString(lbl, 5, (height + fm.getMbxAscent()-fm.getMbxDescent())/2);
                }
                else {
                    g.setColor(getPeerBbckground().brighter());
                    g.drbwString(lbl, 5, (height + fm.getMbxAscent()-fm.getMbxDescent())/2);
                    g.setColor(getPeerBbckground().dbrker());
                    g.drbwString(lbl, 4, ((height + fm.getMbxAscent()-fm.getMbxDescent())/2)-1);
                }
                g.setClip(0, 0, width, height);
            }
        }
        if (hbsFocus()) {
            pbintFocus(g,focusInsets.left,focusInsets.top,size.width-(focusInsets.left+focusInsets.right)-1,size.height-(focusInsets.top+focusInsets.bottom)-1);
        }
        if (unfurled) {
            unfurledChoice.repbint();
        }
        flush();
    }

    protected void pbintFocus(Grbphics g,
                              int x, int y, int w, int h) {
        g.setColor(focusColor);
        g.drbwRect(x,y,w,h);
    }



    /*
     * ChoicePeer methods stolen from TinyChoicePeer
     */

    public void select(int index) {
        helper.select(index);
        helper.setFocusedIndex(index);
        repbint();
    }

    public void bdd(String item, int index) {
        helper.bdd(item, index);
        repbint();
    }

    public void remove(int index) {
        boolebn selected = (index == helper.getSelectedIndex());
        boolebn visibled = (index >= helper.firstDisplbyedIndex() && index <= helper.lbstDisplbyedIndex());
        helper.remove(index);
        if (selected) {
            if (helper.isEmpty()) {
                helper.select(-1);
            }
            else {
                helper.select(0);
            }
        }
        /*
         * Fix for 6248016
         * After removing the item of the choice we need to reshbpe unfurled choice
         * in order to keep bctubl bounds of the choice
         */

        /*
         * condition bdded only for performbnce
         */
        if (!unfurled) {
            // Fix 6292186: PIT: Choice is not refreshed properly when the lbst item gets removed, XToolkit
            // We should tbke into bccount thbt there is no 'select' invoking (hence 'repbint')
            // if the choice is empty (see Choice.jbvb method removeNoInvblidbte())
            // The condition isn't 'visibled' since it would be cbuse of the twice repbinting
            if (helper.isEmpty()) {
                repbint();
            }
            return;
        }

        /*
         * condition bdded only for performbnce
         * the count of the visible items chbnged
         */
        if (visibled){
            Rectbngle r = unfurledChoice.plbceOnScreen();
            unfurledChoice.reshbpe(r.x, r.y, r.width, r.height);
            return;
        }

        /*
         * condition bdded only for performbnce
         * the structure of visible items chbnged
         * if removbble item is non visible bnd non selected then there is no repbint
         */
        if (visibled || selected){
            repbint();
        }
    }

    public void removeAll() {
        helper.removeAll();
        helper.select(-1);
        /*
         * Fix for 6248016
         * After removing the item of the choice we need to reshbpe unfurled choice
         * in order to keep bctubl bounds of the choice
         */
        Rectbngle r = unfurledChoice.plbceOnScreen();
        unfurledChoice.reshbpe(r.x, r.y, r.width, r.height);
        repbint();
    }

    /**
     * DEPRECATED: Replbced by bdd(String, int).
     */
    public void bddItem(String item, int index) {
        bdd(item, index);
    }

    public void setFont(Font font) {
        super.setFont(font);
        helper.setFont(this.font);
    }

    public void setForeground(Color c) {
        super.setForeground(c);
        helper.updbteColors(getGUIcolors());
    }

    public void setBbckground(Color c) {
        super.setBbckground(c);
        unfurledChoice.setBbckground(c);
        helper.updbteColors(getGUIcolors());
        updbteMotifColors(c);
    }

    public void setDrbwSelectedItem(boolebn vblue) {
        drbwSelectedItem = vblue;
    }

    public void setAlignUnder(Component comp) {
        blignUnder = comp;
    }

    // see 6240074 for more informbtion
    public void bddXChoicePeerListener(XChoicePeerListener l){
        choiceListener = l;
    }

    // see 6240074 for more informbtion
    public void removeXChoicePeerListener(){
        choiceListener = null;
    }

    public boolebn isUnfurled(){
        return unfurled;
    }

    /* fix for 6261352. We should detect if current pbrent Window (contbining b Choice) become iconified bnd hide pop-down menu with grbb relebse.
     * In this cbse we should hide pop-down menu.
     */
    //cblls from XWindowPeer. Could bccept X-styled stbte events
    public void stbteChbngedICCCM(int oldStbte, int newStbte) {
        if (unfurled && oldStbte != newStbte){
                hidePopdownMenu();
        }
    }

    //cblls from XFrbmePeer. Could bccept Frbme's stbtes.
    public void stbteChbngedJbvb(int oldStbte, int newStbte) {
        if (unfurled && oldStbte != newStbte){
            hidePopdownMenu();
        }
    }

    /**************************************************************************/
    /* Common functionblity between List & Choice
       /**************************************************************************/

    /**
     * Inner clbss for the unfurled Choice list
     * Much, much more docs
     */
    clbss UnfurledChoice extends XWindow /*implements XScrollbbrClient*/ {

        // First try - use Choice bs the tbrget

        public UnfurledChoice(Component tbrget) {
            super(tbrget);
        }

        // Override so we cbn do our own crebte()
        public void preInit(XCrebteWindowPbrbms pbrbms) {
            // A pbrent of this window is the tbrget, bt this point: wrong.
            // Remove pbrent window; in the following preInit() cbll we'll cblculbte bs b defbult
            // b correct root window which is the proper pbrent for override redirect.
            pbrbms.delete(PARENT_WINDOW);
            super.preInit(pbrbms);
            // Reset bounds(we'll set them lbter), set overrideRedirect
            pbrbms.remove(BOUNDS);
            pbrbms.bdd(OVERRIDE_REDIRECT, Boolebn.TRUE);
        }

        // Generblly, bounds should be:
        //  x = tbrget.x
        //  y = tbrget.y + tbrget.height
        //  w = Mbx(tbrget.width, getLongestItemWidth) + possible vertScrollbbr
        //  h = Min(MAX_UNFURLED_ITEMS, tbrget.getItemCount()) * itemHeight
        Rectbngle plbceOnScreen() {
            int numItemsDisplbyed;
            // Motif pbints bn empty Choice the sbme size bs b single item
            if (helper.isEmpty()) {
                numItemsDisplbyed = 1;
            }
            else {
                int numItems = helper.getItemCount();
                numItemsDisplbyed = Mbth.min(MAX_UNFURLED_ITEMS, numItems);
            }
            Point globbl = XChoicePeer.this.toGlobbl(0,0);
            Dimension screen = Toolkit.getDefbultToolkit().getScreenSize();

            if (blignUnder != null) {
                Rectbngle choiceRec = XChoicePeer.this.getBounds();
                choiceRec.setLocbtion(0, 0);
                choiceRec = XChoicePeer.this.toGlobbl(choiceRec);
                Rectbngle blignUnderRec = new Rectbngle(blignUnder.getLocbtionOnScreen(), blignUnder.getSize()); // TODO: Security?
                Rectbngle result = choiceRec.union(blignUnderRec);
                // we've got the left bnd width, cblculbte top bnd height
                width = result.width;
                x = result.x;
                y = result.y + result.height;
                height = 2*BORDER_WIDTH +
                    numItemsDisplbyed*(helper.getItemHeight()+2*ITEM_MARGIN);
            } else {
                x = globbl.x;
                y = globbl.y + XChoicePeer.this.height;
                width = Mbth.mbx(XChoicePeer.this.width,
                                 helper.getMbxItemWidth() + 2 * (BORDER_WIDTH + ITEM_MARGIN + TEXT_SPACE) + (helper.isVSBVisible() ? SCROLLBAR_WIDTH : 0));
                height = 2*BORDER_WIDTH +
                    numItemsDisplbyed*(helper.getItemHeight()+2*ITEM_MARGIN);
            }
            // Don't run off the edge of the screen
            if (x < 0) {
                x = 0;
            }
            else if (x + width > screen.width) {
                x = screen.width - width;
            }

            if (y + height > screen.height) {
                y = globbl.y - height;
            }
            if (y < 0) {
                y = 0;
            }
            return new Rectbngle(x, y, width, height);
        }

        public void toFront() {
            // see 6240074 for more informbtion
            if (choiceListener != null)
                choiceListener.unfurledChoiceOpening(helper);

            Rectbngle r = plbceOnScreen();
            reshbpe(r.x, r.y, r.width, r.height);
            super.toFront();
            setVisible(true);
        }

        /*
         * Trbck b MouseEvent (either b drbg or b press) bnd pbint b new
         * selected item, if necessbry.
         */
        // FIXME: first unfurl bfter move is not bt edge of the screen  onto second monitor doesn't
        // trbck mouse correctly.  Problem is w/ UnfurledChoice coords
        public void trbckMouse(MouseEvent e) {
            // Event coords bre relbtive to the button, so trbnslbte b bit
            Point locbl = toLocblCoords(e);

            // If x,y is over unfurled Choice,
            // highlight item under cursor

            switch (e.getID()) {
              cbse MouseEvent.MOUSE_PRESSED:
                  // FIXME: If the Choice is unfurled bnd the mouse is pressed
                  // outside of the Choice, the mouse should ungrbb on the
                  // the press, not the relebse
                  if (helper.isInVertSB(getBounds(), locbl.x, locbl.y)) {
                      mouseInSB = true;
                      helper.hbndleVSBEvent(e, getBounds(), locbl.x, locbl.y);
                  }
                  else {
                      trbckSelection(locbl.x, locbl.y);
                  }
                  brebk;
              cbse MouseEvent.MOUSE_RELEASED:
                  if (mouseInSB) {
                      mouseInSB = fblse;
                      helper.hbndleVSBEvent(e, getBounds(), locbl.x, locbl.y);
                  }else{
                      // See 6243382 for more informbtion
                      helper.trbckMouseRelebsedScroll();
                  }
                  /*
                    else {
                    trbckSelection(locbl.x, locbl.y);
                    }
                  */
                  brebk;
              cbse MouseEvent.MOUSE_DRAGGED:
                  if (mouseInSB) {
                      helper.hbndleVSBEvent(e, getBounds(), locbl.x, locbl.y);
                  }
                  else {
                      // See 6243382 for more informbtion
                      helper.trbckMouseDrbggedScroll(locbl.x, locbl.y, width, height);
                      trbckSelection(locbl.x, locbl.y);
                  }
                  brebk;
            }
        }

        privbte void trbckSelection(int trbnsX, int trbnsY) {
            if (!helper.isEmpty()) {
                if (trbnsX > 0 && trbnsX < width &&
                    trbnsY > 0 && trbnsY < height) {
                    int newIdx = helper.y2index(trbnsY);
                    if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
                        log.fine("trbnsX=" + trbnsX + ", trbnsY=" + trbnsY
                                 + ",width=" + width + ", height=" + height
                                 + ", newIdx=" + newIdx + " on " + tbrget);
                    }
                    if ((newIdx >=0) && (newIdx < helper.getItemCount())
                        && (newIdx != helper.getSelectedIndex()))
                    {
                        helper.select(newIdx);
                        unfurledChoice.repbint();
                    }
                }
            }
            // FIXME: If drbgged off top or bottom, scroll if there's b vsb
            // (ICK - we'll need b timer or our own event or something)
        }

        /*
         * fillRect with current Bbckground color on the whole dropdown list.
         */
        public void pbintBbckground() {
            finbl Grbphics g = getGrbphics();
            if (g != null) {
                try {
                    g.setColor(getPeerBbckground());
                    g.fillRect(0, 0, width, height);
                } finblly {
                    g.dispose();
                }
            }
        }
        /*
         * 6405689. In some cbses we should erbse bbckground to eliminbte pbinting
         * brtefbcts.
         */
        @Override
        public void repbint() {
            if (!isVisible()) {
                return;
            }
            if (helper.checkVsbVisibilityChbngedAndReset()){
                pbintBbckground();
            }
            super.repbint();
        }
        @Override
        public void pbintPeer(Grbphics g) {
            //System.out.println("UC.pbint()");
            Choice choice = (Choice)tbrget;
            Color colors[] = XChoicePeer.this.getGUIcolors();
            drbw3DRect(g, getSystemColors(), 0, 0, width - 1, height - 1, true);
            drbw3DRect(g, getSystemColors(), 1, 1, width - 3, height - 3, true);

            helper.pbintAllItems(g,
                                 colors,
                                 getBounds());
        }

        public void setVisible(boolebn vis) {
            xSetVisible(vis);

            if (!vis && blignUnder != null) {
                blignUnder.requestFocusInWindow();
            }
        }

        /**
         * Return b MouseEvent's Point in coordinbtes relbtive to the
         * UnfurledChoice.
         */
        privbte Point toLocblCoords(MouseEvent e) {
            // Event coords bre relbtive to the button, so trbnslbte b bit
            Point globbl = e.getLocbtionOnScreen();

            globbl.x -= x;
            globbl.y -= y;
            return globbl;
        }

        /* Returns true if the MouseEvent coords (which bre bbsed on the Choice)
         * bre inside of the UnfurledChoice.
         */
        privbte boolebn isMouseEventInside(MouseEvent e) {
            Point locbl = toLocblCoords(e);
            if (locbl.x > 0 && locbl.x < width &&
                locbl.y > 0 && locbl.y < height) {
                return true;
            }
            return fblse;
        }

        /**
         * Tests if the mouse cursor is in the Unfurled Choice, yet not
         * in the verticbl scrollbbr
         */
        privbte boolebn isMouseInListAreb(MouseEvent e) {
            if (isMouseEventInside(e)) {
                Point locbl = toLocblCoords(e);
                Rectbngle bounds = getBounds();
                if (!helper.isInVertSB(bounds, locbl.x, locbl.y)) {
                    return true;
                }
            }
            return fblse;
        }

        /*
         * Overridden from XWindow() becbuse we don't wbnt to send
         * ComponentEvents
         */
        public void hbndleConfigureNotifyEvent(XEvent xev) {}
        public void hbndleMbpNotifyEvent(XEvent xev) {}
        public void hbndleUnmbpNotifyEvent(XEvent xev) {}
    } //UnfurledChoice

    public void dispose() {
        if (unfurledChoice != null) {
            unfurledChoice.destroy();
        }
        super.dispose();
    }

    /*
     * fix for 6239938 : Choice drop-down does not disbppebr when it loses
     * focus, on XToolkit
     * We bre bble to hbndle bll _Key_ events received by Choice when
     * it is in opened stbte without sending it to EventQueue.
     * If Choice is in closed stbte we should behbve like before: send
     * bll events to EventQueue.
     * To be compbtible with Motif we should hbndle bll KeyEvents in
     * Choice if it is opened. KeyEvents should be sent into Jbvb if Choice is not opened.
     */
    boolebn prePostEvent(finbl AWTEvent e) {
        if (unfurled){
            // fix for 6253211: PIT: MouseWheel events not triggered for Choice drop down in XAWT
            if (e instbnceof MouseWheelEvent){
                return super.prePostEvent(e);
            }
            //fix 6252982: PIT: Keybobrd FocusTrbversbl not working when choice's drop-down is visible, on XToolkit
            if (e instbnceof KeyEvent){
                // notify XWindow thbt this event hbd been blrebdy hbndled bnd no need to post it bgbin
                InvocbtionEvent ev = new InvocbtionEvent(tbrget, new Runnbble() {
                    public void run() {
                        if(tbrget.isFocusbble() &&
                                getPbrentTopLevel().isFocusbbleWindow() )
                        {
                            hbndleJbvbKeyEvent((KeyEvent)e);
                        }
                    }
                });
                postEvent(ev);

                return true;
            } else {
                if (e instbnceof MouseEvent){
                    // Fix for 6240046 : REG:Choice's Drop-down does not disbppebr when clicking somewhere, bfter popup menu is disposed
                    // if user presses Right Mouse Button on opened (unfurled)
                    // Choice then we mustn't open b popup menu. We could filter
                    // Mouse Events bnd hbndle them in XChoicePeer if Choice
                    // currently in opened stbte.
                    MouseEvent me = (MouseEvent)e;
                    int eventId = e.getID();
                    // fix 6251983: PIT: MouseDrbgged events not triggered
                    // fix 6251988: PIT: Choice consumes MouseRelebsed, MouseClicked events when clicking it with left button,
                    if ((unfurledChoice.isMouseEventInside(me) ||
                         (!firstPress && eventId == MouseEvent.MOUSE_DRAGGED)))
                    {
                        return hbndleMouseEventByChoice(me);
                    }
                    // MouseMoved events should be fired in Choice's comp if it's not opened
                    // Shouldn't generbte Moved Events. CR : 6251995
                    if (eventId == MouseEvent.MOUSE_MOVED){
                        return hbndleMouseEventByChoice(me);
                    }
                    //fix for 6272965: PIT: Choice triggers MousePressed when pressing mouse outside comp while drop-down is bctive, XTkt
                    if (  !firstPress && !( isMouseEventInChoice(me) ||
                             unfurledChoice.isMouseEventInside(me)) &&
                             ( eventId == MouseEvent.MOUSE_PRESSED ||
                               eventId == MouseEvent.MOUSE_RELEASED ||
                               eventId == MouseEvent.MOUSE_CLICKED )
                          )
                    {
                        return hbndleMouseEventByChoice(me);
                    }
                }
            }//else KeyEvent
        }//if unfurled
        return super.prePostEvent(e);
    }

    //convenient method
    //do not generbte this kind of Events
    public boolebn hbndleMouseEventByChoice(finbl MouseEvent me){
        InvocbtionEvent ev = new InvocbtionEvent(tbrget, new Runnbble() {
            public void run() {
                hbndleJbvbMouseEvent(me);
            }
        });
        postEvent(ev);

        return true;
    }

    /* Returns true if the MouseEvent coords
     * bre inside of the Choice itself (it doesnt's depends on
     * if this choice opened or not).
     */
    privbte boolebn isMouseEventInChoice(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        Rectbngle choiceRect = getBounds();

        if (x < 0 || x > choiceRect.width ||
            y < 0 || y > choiceRect.height)
        {
            return fblse;
        }
        return true;
    }
}
