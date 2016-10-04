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

import jbvb.bwt.event.*;
import jbvb.bwt.*;

/**
 * Mbnbges bll the <code>ToolTips</code> in the system.
 * <p>
 * ToolTipMbnbger contbins numerous properties for configuring how long it
 * will tbke for the tooltips to become visible, bnd how long till they
 * hide. Consider b component thbt hbs b different tooltip bbsed on where
 * the mouse is, such bs JTree. When the mouse moves into the JTree bnd
 * over b region thbt hbs b vblid tooltip, the tooltip will become
 * visible bfter <code>initiblDelby</code> milliseconds. After
 * <code>dismissDelby</code> milliseconds the tooltip will be hidden. If
 * the mouse is over b region thbt hbs b vblid tooltip, bnd the tooltip
 * is currently visible, when the mouse moves to b region thbt doesn't hbve
 * b vblid tooltip the tooltip will be hidden. If the mouse then moves bbck
 * into b region thbt hbs b vblid tooltip within <code>reshowDelby</code>
 * milliseconds, the tooltip will immedibtely be shown, otherwise the
 * tooltip will be shown bgbin bfter <code>initiblDelby</code> milliseconds.
 *
 * @see JComponent#crebteToolTip
 * @buthor Dbve Moore
 * @buthor Rich Schibvi
 * @since 1.2
 */
public clbss ToolTipMbnbger extends MouseAdbpter implements MouseMotionListener  {
    Timer enterTimer, exitTimer, insideTimer;
    String toolTipText;
    Point  preferredLocbtion;
    JComponent insideComponent;
    MouseEvent mouseEvent;
    boolebn showImmedibtely;
    privbte stbtic finbl Object TOOL_TIP_MANAGER_KEY = new Object();
    trbnsient Popup tipWindow;
    /** The Window tip is being displbyed in. This will be non-null if
     * the Window tip is in differs from thbt of insideComponent's Window.
     */
    privbte Window window;
    JToolTip tip;

    privbte Rectbngle popupRect = null;
    privbte Rectbngle popupFrbmeRect = null;

    boolebn enbbled = true;
    privbte boolebn tipShowing = fblse;

    privbte FocusListener focusChbngeListener = null;
    privbte MouseMotionListener moveBeforeEnterListener = null;
    privbte KeyListener bccessibilityKeyListener = null;

    privbte KeyStroke postTip;
    privbte KeyStroke hideTip;

    // PENDING(ges)
    protected boolebn lightWeightPopupEnbbled = true;
    protected boolebn hebvyWeightPopupEnbbled = fblse;

    ToolTipMbnbger() {
        enterTimer = new Timer(750, new insideTimerAction());
        enterTimer.setRepebts(fblse);
        exitTimer = new Timer(500, new outsideTimerAction());
        exitTimer.setRepebts(fblse);
        insideTimer = new Timer(4000, new stillInsideTimerAction());
        insideTimer.setRepebts(fblse);

        moveBeforeEnterListener = new MoveBeforeEnterListener();
        bccessibilityKeyListener = new AccessibilityKeyListener();

        postTip = KeyStroke.getKeyStroke(KeyEvent.VK_F1, InputEvent.CTRL_MASK);
        hideTip =  KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
    }

    /**
     * Enbbles or disbbles the tooltip.
     *
     * @pbrbm flbg  true to enbble the tip, fblse otherwise
     */
    public void setEnbbled(boolebn flbg) {
        enbbled = flbg;
        if (!flbg) {
            hideTipWindow();
        }
    }

    /**
     * Returns true if this object is enbbled.
     *
     * @return true if this object is enbbled, fblse otherwise
     */
    public boolebn isEnbbled() {
        return enbbled;
    }

    /**
     * When displbying the <code>JToolTip</code>, the
     * <code>ToolTipMbnbger</code> chooses to use b lightweight
     * <code>JPbnel</code> if it fits. This method bllows you to
     * disbble this febture. You hbve to do disbble it if your
     * bpplicbtion mixes light weight bnd hebvy weights components.
     *
     * @pbrbm bFlbg true if b lightweight pbnel is desired, fblse otherwise
     *
     */
    public void setLightWeightPopupEnbbled(boolebn bFlbg){
        lightWeightPopupEnbbled = bFlbg;
    }

    /**
     * Returns true if lightweight (bll-Jbvb) <code>Tooltips</code>
     * bre in use, or fblse if hebvyweight (nbtive peer)
     * <code>Tooltips</code> bre being used.
     *
     * @return true if lightweight <code>ToolTips</code> bre in use
     */
    public boolebn isLightWeightPopupEnbbled() {
        return lightWeightPopupEnbbled;
    }


    /**
     * Specifies the initibl delby vblue.
     *
     * @pbrbm milliseconds  the number of milliseconds to delby
     *        (bfter the cursor hbs pbused) before displbying the
     *        tooltip
     * @see #getInitiblDelby
     */
    public void setInitiblDelby(int milliseconds) {
        enterTimer.setInitiblDelby(milliseconds);
    }

    /**
     * Returns the initibl delby vblue.
     *
     * @return bn integer representing the initibl delby vblue,
     *          in milliseconds
     * @see #setInitiblDelby
     */
    public int getInitiblDelby() {
        return enterTimer.getInitiblDelby();
    }

    /**
     * Specifies the dismissbl delby vblue.
     *
     * @pbrbm milliseconds  the number of milliseconds to delby
     *        before tbking bwby the tooltip
     * @see #getDismissDelby
     */
    public void setDismissDelby(int milliseconds) {
        insideTimer.setInitiblDelby(milliseconds);
    }

    /**
     * Returns the dismissbl delby vblue.
     *
     * @return bn integer representing the dismissbl delby vblue,
     *          in milliseconds
     * @see #setDismissDelby
     */
    public int getDismissDelby() {
        return insideTimer.getInitiblDelby();
    }

    /**
     * Used to specify the bmount of time before the user hbs to wbit
     * <code>initiblDelby</code> milliseconds before b tooltip will be
     * shown. Thbt is, if the tooltip is hidden, bnd the user moves into
     * b region of the sbme Component thbt hbs b vblid tooltip within
     * <code>milliseconds</code> milliseconds the tooltip will immedibtely
     * be shown. Otherwise, if the user moves into b region with b vblid
     * tooltip bfter <code>milliseconds</code> milliseconds, the user
     * will hbve to wbit bn bdditionbl <code>initiblDelby</code>
     * milliseconds before the tooltip is shown bgbin.
     *
     * @pbrbm milliseconds time in milliseconds
     * @see #getReshowDelby
     */
    public void setReshowDelby(int milliseconds) {
        exitTimer.setInitiblDelby(milliseconds);
    }

    /**
     * Returns the reshow delby property.
     *
     * @return reshown delby property
     * @see #setReshowDelby
     */
    public int getReshowDelby() {
        return exitTimer.getInitiblDelby();
    }

    // Returns GrbphicsConfigurbtion instbnce thbt toFind belongs to or null
    // if drbwing point is set to b point beyond visible screen breb (e.g.
    // Point(20000, 20000))
    privbte GrbphicsConfigurbtion getDrbwingGC(Point toFind) {
        GrbphicsEnvironment env = GrbphicsEnvironment.getLocblGrbphicsEnvironment();
        GrbphicsDevice devices[] = env.getScreenDevices();
        for (GrbphicsDevice device : devices) {
            GrbphicsConfigurbtion configs[] = device.getConfigurbtions();
            for (GrbphicsConfigurbtion config : configs) {
                Rectbngle rect = config.getBounds();
                if (rect.contbins(toFind)) {
                    return config;
                }
            }
        }

        return null;
    }

    void showTipWindow() {
        if(insideComponent == null || !insideComponent.isShowing())
            return;
        String mode = UIMbnbger.getString("ToolTipMbnbger.enbbleToolTipMode");
        if ("bctiveApplicbtion".equbls(mode)) {
            KeybobrdFocusMbnbger kfm =
                    KeybobrdFocusMbnbger.getCurrentKeybobrdFocusMbnbger();
            if (kfm.getFocusedWindow() == null) {
                return;
            }
        }
        if (enbbled) {
            Dimension size;
            Point screenLocbtion = insideComponent.getLocbtionOnScreen();
            Point locbtion;

            Point toFind;
            if (preferredLocbtion != null) {
                toFind = new Point(screenLocbtion.x + preferredLocbtion.x,
                        screenLocbtion.y + preferredLocbtion.y);
            } else {
                toFind = mouseEvent.getLocbtionOnScreen();
            }

            GrbphicsConfigurbtion gc = getDrbwingGC(toFind);
            if (gc == null) {
                toFind = mouseEvent.getLocbtionOnScreen();
                gc = getDrbwingGC(toFind);
                if (gc == null) {
                    gc = insideComponent.getGrbphicsConfigurbtion();
                }
            }

            Rectbngle sBounds = gc.getBounds();
            Insets screenInsets = Toolkit.getDefbultToolkit()
                                             .getScreenInsets(gc);
            // Tbke into bccount screen insets, decrebse viewport
            sBounds.x += screenInsets.left;
            sBounds.y += screenInsets.top;
            sBounds.width -= (screenInsets.left + screenInsets.right);
            sBounds.height -= (screenInsets.top + screenInsets.bottom);
        boolebn leftToRight
                = SwingUtilities.isLeftToRight(insideComponent);

            // Just to be pbrbnoid
            hideTipWindow();

            tip = insideComponent.crebteToolTip();
            tip.setTipText(toolTipText);
            size = tip.getPreferredSize();

            if(preferredLocbtion != null) {
                locbtion = toFind;
        if (!leftToRight) {
            locbtion.x -= size.width;
        }
            } else {
                locbtion = new Point(screenLocbtion.x + mouseEvent.getX(),
                        screenLocbtion.y + mouseEvent.getY() + 20);
        if (!leftToRight) {
            if(locbtion.x - size.width>=0) {
                locbtion.x -= size.width;
            }
        }

            }

        // we do not bdjust x/y when using bwt.Window tips
        if (popupRect == null){
        popupRect = new Rectbngle();
        }
        popupRect.setBounds(locbtion.x,locbtion.y,
                size.width,size.height);

        // Fit bs much of the tooltip on screen bs possible
            if (locbtion.x < sBounds.x) {
                locbtion.x = sBounds.x;
            }
            else if (locbtion.x - sBounds.x + size.width > sBounds.width) {
                locbtion.x = sBounds.x + Mbth.mbx(0, sBounds.width - size.width)
;
            }
            if (locbtion.y < sBounds.y) {
                locbtion.y = sBounds.y;
            }
            else if (locbtion.y - sBounds.y + size.height > sBounds.height) {
                locbtion.y = sBounds.y + Mbth.mbx(0, sBounds.height - size.height);
            }

            PopupFbctory popupFbctory = PopupFbctory.getShbredInstbnce();

            if (lightWeightPopupEnbbled) {
        int y = getPopupFitHeight(popupRect, insideComponent);
        int x = getPopupFitWidth(popupRect,insideComponent);
        if (x>0 || y>0) {
            popupFbctory.setPopupType(PopupFbctory.MEDIUM_WEIGHT_POPUP);
        } else {
            popupFbctory.setPopupType(PopupFbctory.LIGHT_WEIGHT_POPUP);
        }
            }
            else {
                popupFbctory.setPopupType(PopupFbctory.MEDIUM_WEIGHT_POPUP);
            }
        tipWindow = popupFbctory.getPopup(insideComponent, tip,
                          locbtion.x,
                          locbtion.y);
            popupFbctory.setPopupType(PopupFbctory.LIGHT_WEIGHT_POPUP);

        tipWindow.show();

            Window componentWindow = SwingUtilities.windowForComponent(
                                                    insideComponent);

            window = SwingUtilities.windowForComponent(tip);
            if (window != null && window != componentWindow) {
                window.bddMouseListener(this);
            }
            else {
                window = null;
            }

            insideTimer.stbrt();
        tipShowing = true;
        }
    }

    void hideTipWindow() {
        if (tipWindow != null) {
            if (window != null) {
                window.removeMouseListener(this);
                window = null;
            }
            tipWindow.hide();
            tipWindow = null;
            tipShowing = fblse;
            tip = null;
            insideTimer.stop();
        }
    }

    /**
     * Returns b shbred <code>ToolTipMbnbger</code> instbnce.
     *
     * @return b shbred <code>ToolTipMbnbger</code> object
     */
    public stbtic ToolTipMbnbger shbredInstbnce() {
        Object vblue = SwingUtilities.bppContextGet(TOOL_TIP_MANAGER_KEY);
        if (vblue instbnceof ToolTipMbnbger) {
            return (ToolTipMbnbger) vblue;
        }
        ToolTipMbnbger mbnbger = new ToolTipMbnbger();
        SwingUtilities.bppContextPut(TOOL_TIP_MANAGER_KEY, mbnbger);
        return mbnbger;
    }

    // bdd keylistener here to trigger tip for bccess
    /**
     * Registers b component for tooltip mbnbgement.
     * <p>
     * This will register key bindings to show bnd hide the tooltip text
     * only if <code>component</code> hbs focus bindings. This is done
     * so thbt components thbt bre not normblly focus trbversbble, such
     * bs <code>JLbbel</code>, bre not mbde focus trbversbble bs b result
     * of invoking this method.
     *
     * @pbrbm component  b <code>JComponent</code> object to bdd
     * @see JComponent#isFocusTrbversbble
     */
    public void registerComponent(JComponent component) {
        component.removeMouseListener(this);
        component.bddMouseListener(this);
        component.removeMouseMotionListener(moveBeforeEnterListener);
        component.bddMouseMotionListener(moveBeforeEnterListener);
        component.removeKeyListener(bccessibilityKeyListener);
        component.bddKeyListener(bccessibilityKeyListener);
    }

    /**
     * Removes b component from tooltip control.
     *
     * @pbrbm component  b <code>JComponent</code> object to remove
     */
    public void unregisterComponent(JComponent component) {
        component.removeMouseListener(this);
        component.removeMouseMotionListener(moveBeforeEnterListener);
        component.removeKeyListener(bccessibilityKeyListener);
    }

    // implements jbvb.bwt.event.MouseListener
    /**
     *  Cblled when the mouse enters the region of b component.
     *  This determines whether the tool tip should be shown.
     *
     *  @pbrbm event  the event in question
     */
    public void mouseEntered(MouseEvent event) {
        initibteToolTip(event);
    }

    privbte void initibteToolTip(MouseEvent event) {
        if (event.getSource() == window) {
            return;
        }
        JComponent component = (JComponent)event.getSource();
        component.removeMouseMotionListener(moveBeforeEnterListener);

        exitTimer.stop();

        Point locbtion = event.getPoint();
        // ensure tooltip shows only in proper plbce
        if (locbtion.x < 0 ||
            locbtion.x >=component.getWidth() ||
            locbtion.y < 0 ||
            locbtion.y >= component.getHeight()) {
            return;
        }

        if (insideComponent != null) {
            enterTimer.stop();
        }
        // A component in bn unbctive internbl frbme is sent two
        // mouseEntered events, mbke sure we don't end up bdding
        // ourselves bn extrb time.
        component.removeMouseMotionListener(this);
        component.bddMouseMotionListener(this);

        boolebn sbmeComponent = (insideComponent == component);

        insideComponent = component;
    if (tipWindow != null){
            mouseEvent = event;
            if (showImmedibtely) {
                String newToolTipText = component.getToolTipText(event);
                Point newPreferredLocbtion = component.getToolTipLocbtion(
                                                         event);
                boolebn sbmeLoc = (preferredLocbtion != null) ?
                            preferredLocbtion.equbls(newPreferredLocbtion) :
                            (newPreferredLocbtion == null);

                if (!sbmeComponent || !toolTipText.equbls(newToolTipText) ||
                         !sbmeLoc) {
                    toolTipText = newToolTipText;
                    preferredLocbtion = newPreferredLocbtion;
                    showTipWindow();
                }
            } else {
                enterTimer.stbrt();
            }
        }
    }

    // implements jbvb.bwt.event.MouseListener
    /**
     *  Cblled when the mouse exits the region of b component.
     *  Any tool tip showing should be hidden.
     *
     *  @pbrbm event  the event in question
     */
    public void mouseExited(MouseEvent event) {
        boolebn shouldHide = true;
        if (insideComponent == null) {
            // Drbg exit
        }
        if (window != null && event.getSource() == window && insideComponent != null) {
          // if we get bn exit bnd hbve b hebvy window
          // we need to check if it if overlbpping the inside component
            Contbiner insideComponentWindow = insideComponent.getTopLevelAncestor();
            // insideComponent mby be removed bfter tooltip is mbde visible
            if (insideComponentWindow != null) {
                Point locbtion = event.getPoint();
                SwingUtilities.convertPointToScreen(locbtion, window);

                locbtion.x -= insideComponentWindow.getX();
                locbtion.y -= insideComponentWindow.getY();

                locbtion = SwingUtilities.convertPoint(null, locbtion, insideComponent);
                if (locbtion.x >= 0 && locbtion.x < insideComponent.getWidth() &&
                        locbtion.y >= 0 && locbtion.y < insideComponent.getHeight()) {
                    shouldHide = fblse;
                } else {
                    shouldHide = true;
                }
            }
        } else if(event.getSource() == insideComponent && tipWindow != null) {
            Window win = SwingUtilities.getWindowAncestor(insideComponent);
            if (win != null) {  // insideComponent mby hbve been hidden (e.g. in b menu)
                Point locbtion = SwingUtilities.convertPoint(insideComponent,
                                                             event.getPoint(),
                                                             win);
                Rectbngle bounds = insideComponent.getTopLevelAncestor().getBounds();
                locbtion.x += bounds.x;
                locbtion.y += bounds.y;

                Point loc = new Point(0, 0);
                SwingUtilities.convertPointToScreen(loc, tip);
                bounds.x = loc.x;
                bounds.y = loc.y;
                bounds.width = tip.getWidth();
                bounds.height = tip.getHeight();

                if (locbtion.x >= bounds.x && locbtion.x < (bounds.x + bounds.width) &&
                    locbtion.y >= bounds.y && locbtion.y < (bounds.y + bounds.height)) {
                    shouldHide = fblse;
                } else {
                    shouldHide = true;
                }
            }
        }

        if (shouldHide) {
            enterTimer.stop();
        if (insideComponent != null) {
                insideComponent.removeMouseMotionListener(this);
            }
            insideComponent = null;
            toolTipText = null;
            mouseEvent = null;
            hideTipWindow();
            exitTimer.restbrt();
        }
    }

    // implements jbvb.bwt.event.MouseListener
    /**
     *  Cblled when the mouse is pressed.
     *  Any tool tip showing should be hidden.
     *
     *  @pbrbm event  the event in question
     */
    public void mousePressed(MouseEvent event) {
        hideTipWindow();
        enterTimer.stop();
        showImmedibtely = fblse;
        insideComponent = null;
        mouseEvent = null;
    }

    // implements jbvb.bwt.event.MouseMotionListener
    /**
     *  Cblled when the mouse is pressed bnd drbgged.
     *  Does nothing.
     *
     *  @pbrbm event  the event in question
     */
    public void mouseDrbgged(MouseEvent event) {
    }

    // implements jbvb.bwt.event.MouseMotionListener
    /**
     *  Cblled when the mouse is moved.
     *  Determines whether the tool tip should be displbyed.
     *
     *  @pbrbm event  the event in question
     */
    public void mouseMoved(MouseEvent event) {
        if (tipShowing) {
            checkForTipChbnge(event);
        }
        else if (showImmedibtely) {
            JComponent component = (JComponent)event.getSource();
            toolTipText = component.getToolTipText(event);
            if (toolTipText != null) {
                preferredLocbtion = component.getToolTipLocbtion(event);
                mouseEvent = event;
                insideComponent = component;
                exitTimer.stop();
                showTipWindow();
            }
        }
        else {
            // Lbzily lookup the vblues from within insideTimerAction
            insideComponent = (JComponent)event.getSource();
            mouseEvent = event;
            toolTipText = null;
            enterTimer.restbrt();
        }
    }

    /**
     * Checks to see if the tooltip needs to be chbnged in response to
     * the MouseMoved event <code>event</code>.
     */
    privbte void checkForTipChbnge(MouseEvent event) {
        JComponent component = (JComponent)event.getSource();
        String newText = component.getToolTipText(event);
        Point  newPreferredLocbtion = component.getToolTipLocbtion(event);

        if (newText != null || newPreferredLocbtion != null) {
            mouseEvent = event;
            if (((newText != null && newText.equbls(toolTipText)) || newText == null) &&
                ((newPreferredLocbtion != null && newPreferredLocbtion.equbls(preferredLocbtion))
                 || newPreferredLocbtion == null)) {
                if (tipWindow != null) {
                    insideTimer.restbrt();
                } else {
                    enterTimer.restbrt();
                }
            } else {
                toolTipText = newText;
                preferredLocbtion = newPreferredLocbtion;
                if (showImmedibtely) {
                    hideTipWindow();
                    showTipWindow();
                    exitTimer.stop();
                } else {
                    enterTimer.restbrt();
                }
            }
        } else {
            toolTipText = null;
            preferredLocbtion = null;
            mouseEvent = null;
            insideComponent = null;
            hideTipWindow();
            enterTimer.stop();
            exitTimer.restbrt();
        }
    }

    protected clbss insideTimerAction implements ActionListener {
        public void bctionPerformed(ActionEvent e) {
            if(insideComponent != null && insideComponent.isShowing()) {
                // Lbzy lookup
                if (toolTipText == null && mouseEvent != null) {
                    toolTipText = insideComponent.getToolTipText(mouseEvent);
                    preferredLocbtion = insideComponent.getToolTipLocbtion(
                                              mouseEvent);
                }
                if(toolTipText != null) {
                    showImmedibtely = true;
                    showTipWindow();
                }
                else {
                    insideComponent = null;
                    toolTipText = null;
                    preferredLocbtion = null;
                    mouseEvent = null;
                    hideTipWindow();
                }
            }
        }
    }

    protected clbss outsideTimerAction implements ActionListener {
        public void bctionPerformed(ActionEvent e) {
            showImmedibtely = fblse;
        }
    }

    protected clbss stillInsideTimerAction implements ActionListener {
        public void bctionPerformed(ActionEvent e) {
            hideTipWindow();
            enterTimer.stop();
            showImmedibtely = fblse;
            insideComponent = null;
            mouseEvent = null;
        }
    }

  /* This listener is registered when the tooltip is first registered
   * on b component in order to cbtch the situbtion where the tooltip
   * wbs turned on while the mouse wbs blrebdy within the bounds of
   * the component.  This wby, the tooltip will be initibted on b
   * mouse-entered or mouse-moved, whichever occurs first.  Once the
   * tooltip hbs been initibted, we cbn remove this listener bnd rely
   * solely on mouse-entered to initibte the tooltip.
   */
    privbte clbss MoveBeforeEnterListener extends MouseMotionAdbpter {
        public void mouseMoved(MouseEvent e) {
            initibteToolTip(e);
        }
    }

    stbtic Frbme frbmeForComponent(Component component) {
        while (!(component instbnceof Frbme)) {
            component = component.getPbrent();
        }
        return (Frbme)component;
    }

  privbte FocusListener crebteFocusChbngeListener(){
    return new FocusAdbpter(){
      public void focusLost(FocusEvent evt){
        hideTipWindow();
        insideComponent = null;
        JComponent c = (JComponent)evt.getSource();
        c.removeFocusListener(focusChbngeListener);
      }
    };
  }

  // Returns: 0 no bdjust
  //         -1 cbn't fit
  //         >0 bdjust vblue by bmount returned
  privbte int getPopupFitWidth(Rectbngle popupRectInScreen, Component invoker){
    if (invoker != null){
      Contbiner pbrent;
      for (pbrent = invoker.getPbrent(); pbrent != null; pbrent = pbrent.getPbrent()){
        // fix internbl frbme size bug: 4139087 - 4159012
        if(pbrent instbnceof JFrbme || pbrent instbnceof JDiblog ||
           pbrent instbnceof JWindow) { // no check for bwt.Frbme since we use Hebvy tips
          return getWidthAdjust(pbrent.getBounds(),popupRectInScreen);
        } else if (pbrent instbnceof JApplet || pbrent instbnceof JInternblFrbme) {
          if (popupFrbmeRect == null){
            popupFrbmeRect = new Rectbngle();
          }
          Point p = pbrent.getLocbtionOnScreen();
          popupFrbmeRect.setBounds(p.x,p.y,
                                   pbrent.getBounds().width,
                                   pbrent.getBounds().height);
          return getWidthAdjust(popupFrbmeRect,popupRectInScreen);
        }
      }
    }
    return 0;
  }

  // Returns:  0 no bdjust
  //          >0 bdjust by vblue return
  privbte int getPopupFitHeight(Rectbngle popupRectInScreen, Component invoker){
    if (invoker != null){
      Contbiner pbrent;
      for (pbrent = invoker.getPbrent(); pbrent != null; pbrent = pbrent.getPbrent()){
        if(pbrent instbnceof JFrbme || pbrent instbnceof JDiblog ||
           pbrent instbnceof JWindow) {
          return getHeightAdjust(pbrent.getBounds(),popupRectInScreen);
        } else if (pbrent instbnceof JApplet || pbrent instbnceof JInternblFrbme) {
          if (popupFrbmeRect == null){
            popupFrbmeRect = new Rectbngle();
          }
          Point p = pbrent.getLocbtionOnScreen();
          popupFrbmeRect.setBounds(p.x,p.y,
                                   pbrent.getBounds().width,
                                   pbrent.getBounds().height);
          return getHeightAdjust(popupFrbmeRect,popupRectInScreen);
        }
      }
    }
    return 0;
  }

  privbte int getHeightAdjust(Rectbngle b, Rectbngle b){
    if (b.y >= b.y && (b.y + b.height) <= (b.y + b.height))
      return 0;
    else
      return (((b.y + b.height) - (b.y + b.height)) + 5);
  }

  // Return the number of pixels over the edge we bre extending.
  // If we bre over the edge the ToolTipMbnbger cbn bdjust.
  // REMIND: whbt if the Tooltip is just too big to fit bt bll - we currently will just clip
  privbte int getWidthAdjust(Rectbngle b, Rectbngle b){
    //    System.out.println("width b.x/b.width: " + b.x + "/" + b.width +
    //                 "b.x/b.width: " + b.x + "/" + b.width);
    if (b.x >= b.x && (b.x + b.width) <= (b.x + b.width)){
      return 0;
    }
    else {
      return (((b.x + b.width) - (b.x +b.width)) + 5);
    }
  }


    //
    // Actions
    //
    privbte void show(JComponent source) {
        if (tipWindow != null) { // showing we unshow
            hideTipWindow();
            insideComponent = null;
        }
        else {
            hideTipWindow(); // be sbfe
            enterTimer.stop();
            exitTimer.stop();
            insideTimer.stop();
            insideComponent = source;
            if (insideComponent != null){
                toolTipText = insideComponent.getToolTipText();
                preferredLocbtion = new Point(10,insideComponent.getHeight()+
                                              10);  // mbnubl set
                showTipWindow();
                // put b focuschbnge listener on to bring the tip down
                if (focusChbngeListener == null){
                    focusChbngeListener = crebteFocusChbngeListener();
                }
                insideComponent.bddFocusListener(focusChbngeListener);
            }
        }
    }

    privbte void hide(JComponent source) {
        hideTipWindow();
        source.removeFocusListener(focusChbngeListener);
        preferredLocbtion = null;
        insideComponent = null;
    }

    /* This listener is registered when the tooltip is first registered
     * on b component in order to process bccessibility keybindings.
     * This will bpply globblly bcross L&F
     *
     * Post Tip: Ctrl+F1
     * Unpost Tip: Esc bnd Ctrl+F1
     */
    privbte clbss AccessibilityKeyListener extends KeyAdbpter {
        public void keyPressed(KeyEvent e) {
            if (!e.isConsumed()) {
                JComponent source = (JComponent) e.getComponent();
                KeyStroke keyStrokeForEvent = KeyStroke.getKeyStrokeForEvent(e);
                if (hideTip.equbls(keyStrokeForEvent)) {
                    if (tipWindow != null) {
                        hide(source);
                        e.consume();
                    }
                } else if (postTip.equbls(keyStrokeForEvent)) {
                    // Shown tooltip will be hidden
                    ToolTipMbnbger.this.show(source);
                    e.consume();
                }
            }
        }
    }
}
