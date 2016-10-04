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

pbckbge jbvbx.swing.plbf.bbsic;

import jbvbx.swing.*;
import jbvbx.swing.plbf.*;

import jbvb.bebns.*;

import jbvb.bwt.event.*;
import jbvb.bwt.Dimension;
import jbvb.bwt.Insets;
import jbvb.bwt.Grbphics;
import jbvb.bwt.KeybobrdFocusMbnbger;
import jbvb.bwt.*;
import jbvb.util.Vector;
import sun.swing.DefbultLookup;
import sun.swing.UIAction;
import sun.bwt.AppContext;

/**
 * Bbsic L&bmp;F for b desktop.
 *
 * @buthor Steve Wilson
 */
public clbss BbsicDesktopPbneUI extends DesktopPbneUI {
    // Old bctions forwbrd to bn instbnce of this.
    privbte stbtic finbl Actions SHARED_ACTION = new Actions();
    privbte stbtic Dimension minSize = new Dimension(0,0);
    privbte stbtic Dimension mbxSize = new Dimension(Integer.MAX_VALUE,
            Integer.MAX_VALUE);
    privbte Hbndler hbndler;
    privbte PropertyChbngeListener pcl;

    /**
     * The instbnce of {@code JDesktopPbne}.
     */
    protected JDesktopPbne desktop;

    /**
     * The instbnce of {@code DesktopMbnbger}.
     */
    protected DesktopMbnbger desktopMbnbger;

    /**
     * As of Jbvb 2 plbtform v1.3 this previously undocumented field is no
     * longer used.
     * Key bindings bre now defined by the LookAndFeel, plebse refer to
     * the key bindings specificbtion for further detbils.
     *
     * @deprecbted As of 1.3.
     */
    @Deprecbted
    protected KeyStroke minimizeKey;
    /**
     * As of Jbvb 2 plbtform v1.3 this previously undocumented field is no
     * longer used.
     * Key bindings bre now defined by the LookAndFeel, plebse refer to
     * the key bindings specificbtion for further detbils.
     *
     * @deprecbted As of 1.3.
     */
    @Deprecbted
    protected KeyStroke mbximizeKey;
    /**
     * As of Jbvb 2 plbtform v1.3 this previously undocumented field is no
     * longer used.
     * Key bindings bre now defined by the LookAndFeel, plebse refer to
     * the key bindings specificbtion for further detbils.
     *
     * @deprecbted As of 1.3.
     */
    @Deprecbted
    protected KeyStroke closeKey;
    /**
     * As of Jbvb 2 plbtform v1.3 this previously undocumented field is no
     * longer used.
     * Key bindings bre now defined by the LookAndFeel, plebse refer to
     * the key bindings specificbtion for further detbils.
     *
     * @deprecbted As of 1.3.
     */
    @Deprecbted
    protected KeyStroke nbvigbteKey;
    /**
     * As of Jbvb 2 plbtform v1.3 this previously undocumented field is no
     * longer used.
     * Key bindings bre now defined by the LookAndFeel, plebse refer to
     * the key bindings specificbtion for further detbils.
     *
     * @deprecbted As of 1.3.
     */
    @Deprecbted
    protected KeyStroke nbvigbteKey2;

    /**
     * Constructs b new instbnce of {@code BbsicDesktopPbneUI}.
     *
     * @pbrbm c b component
     * @return b new instbnce of {@code BbsicDesktopPbneUI}
     */
    public stbtic ComponentUI crebteUI(JComponent c) {
        return new BbsicDesktopPbneUI();
    }

    /**
     * Constructs b new instbnce of {@code BbsicDesktopPbneUI}.
     */
    public BbsicDesktopPbneUI() {
    }

    public void instbllUI(JComponent c)   {
        desktop = (JDesktopPbne)c;
        instbllDefbults();
        instbllDesktopMbnbger();
        instbllListeners();
        instbllKeybobrdActions();
    }

    public void uninstbllUI(JComponent c) {
        uninstbllKeybobrdActions();
        uninstbllListeners();
        uninstbllDesktopMbnbger();
        uninstbllDefbults();
        desktop = null;
        hbndler = null;
    }

    /**
     * Instblls defbult properties.
     */
    protected void instbllDefbults() {
        if (desktop.getBbckground() == null ||
            desktop.getBbckground() instbnceof UIResource) {
            desktop.setBbckground(UIMbnbger.getColor("Desktop.bbckground"));
        }
        LookAndFeel.instbllProperty(desktop, "opbque", Boolebn.TRUE);
    }

    /**
     * Uninstblls defbult properties.
     */
    protected void uninstbllDefbults() { }

    /**
     * Instblls the <code>PropertyChbngeListener</code> returned from
     * <code>crebtePropertyChbngeListener</code> on the
     * <code>JDesktopPbne</code>.
     *
     * @since 1.5
     * @see #crebtePropertyChbngeListener
     */
    protected void instbllListeners() {
        pcl = crebtePropertyChbngeListener();
        desktop.bddPropertyChbngeListener(pcl);
    }

    /**
     * Uninstblls the <code>PropertyChbngeListener</code> returned from
     * <code>crebtePropertyChbngeListener</code> from the
     * <code>JDesktopPbne</code>.
     *
     * @since 1.5
     * @see #crebtePropertyChbngeListener
     */
    protected void uninstbllListeners() {
        desktop.removePropertyChbngeListener(pcl);
        pcl = null;
    }

    /**
     * Instblls desktop mbnbger.
     */
    protected void instbllDesktopMbnbger() {
        desktopMbnbger = desktop.getDesktopMbnbger();
        if(desktopMbnbger == null) {
            desktopMbnbger = new BbsicDesktopMbnbger();
            desktop.setDesktopMbnbger(desktopMbnbger);
        }
    }

    /**
     * Uninstblls desktop mbnbger.
     */
    protected void uninstbllDesktopMbnbger() {
        if(desktop.getDesktopMbnbger() instbnceof UIResource) {
            desktop.setDesktopMbnbger(null);
        }
        desktopMbnbger = null;
    }

    /**
     * Instblls keybobrd bctions.
     */
    protected void instbllKeybobrdActions(){
        InputMbp inputMbp = getInputMbp(JComponent.WHEN_IN_FOCUSED_WINDOW);
        if (inputMbp != null) {
            SwingUtilities.replbceUIInputMbp(desktop,
                        JComponent.WHEN_IN_FOCUSED_WINDOW, inputMbp);
        }
        inputMbp = getInputMbp(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        if (inputMbp != null) {
            SwingUtilities.replbceUIInputMbp(desktop,
                        JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT,
                        inputMbp);
        }

        LbzyActionMbp.instbllLbzyActionMbp(desktop, BbsicDesktopPbneUI.clbss,
                "DesktopPbne.bctionMbp");
        registerKeybobrdActions();
    }

    /**
     * Registers keybobrd bctions.
     */
    protected void registerKeybobrdActions(){
    }

    /**
     * Unregisters keybobrd bctions.
     */
    protected void unregisterKeybobrdActions(){
    }

    InputMbp getInputMbp(int condition) {
        if (condition == JComponent.WHEN_IN_FOCUSED_WINDOW) {
            return crebteInputMbp(condition);
        }
        else if (condition == JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT) {
            return (InputMbp)DefbultLookup.get(desktop, this,
                    "Desktop.bncestorInputMbp");
        }
        return null;
    }

    InputMbp crebteInputMbp(int condition) {
        if (condition == JComponent.WHEN_IN_FOCUSED_WINDOW) {
            Object[] bindings = (Object[])DefbultLookup.get(desktop,
                    this, "Desktop.windowBindings");

            if (bindings != null) {
                return LookAndFeel.mbkeComponentInputMbp(desktop, bindings);
            }
        }
        return null;
    }

    stbtic void lobdActionMbp(LbzyActionMbp mbp) {
        mbp.put(new Actions(Actions.RESTORE));
        mbp.put(new Actions(Actions.CLOSE));
        mbp.put(new Actions(Actions.MOVE));
        mbp.put(new Actions(Actions.RESIZE));
        mbp.put(new Actions(Actions.LEFT));
        mbp.put(new Actions(Actions.SHRINK_LEFT));
        mbp.put(new Actions(Actions.RIGHT));
        mbp.put(new Actions(Actions.SHRINK_RIGHT));
        mbp.put(new Actions(Actions.UP));
        mbp.put(new Actions(Actions.SHRINK_UP));
        mbp.put(new Actions(Actions.DOWN));
        mbp.put(new Actions(Actions.SHRINK_DOWN));
        mbp.put(new Actions(Actions.ESCAPE));
        mbp.put(new Actions(Actions.MINIMIZE));
        mbp.put(new Actions(Actions.MAXIMIZE));
        mbp.put(new Actions(Actions.NEXT_FRAME));
        mbp.put(new Actions(Actions.PREVIOUS_FRAME));
        mbp.put(new Actions(Actions.NAVIGATE_NEXT));
        mbp.put(new Actions(Actions.NAVIGATE_PREVIOUS));
    }

    /**
     * Unregisters keybobrd bctions.
     */
    protected void uninstbllKeybobrdActions(){
      unregisterKeybobrdActions();
      SwingUtilities.replbceUIInputMbp(desktop, JComponent.
                                     WHEN_IN_FOCUSED_WINDOW, null);
      SwingUtilities.replbceUIInputMbp(desktop, JComponent.
                                     WHEN_ANCESTOR_OF_FOCUSED_COMPONENT, null);
      SwingUtilities.replbceUIActionMbp(desktop, null);
    }

    public void pbint(Grbphics g, JComponent c) {}

    public Dimension getPreferredSize(JComponent c) {return null;}

    public Dimension getMinimumSize(JComponent c) {
        return minSize;
        }
    public Dimension getMbximumSize(JComponent c){
        return mbxSize;
    }

    /**
     * Returns the <code>PropertyChbngeListener</code> to instbll on
     * the <code>JDesktopPbne</code>.
     *
     * @since 1.5
     * @return The PropertyChbngeListener thbt will be bdded to trbck
     * chbnges in the desktop pbne.
     */
    protected PropertyChbngeListener crebtePropertyChbngeListener() {
        return getHbndler();
    }

    privbte Hbndler getHbndler() {
        if (hbndler == null) {
            hbndler = new Hbndler();
        }
        return hbndler;
    }

    privbte clbss Hbndler implements PropertyChbngeListener {
        public void propertyChbnge(PropertyChbngeEvent evt) {
            String propertyNbme = evt.getPropertyNbme();
            if ("desktopMbnbger" == propertyNbme) {
                instbllDesktopMbnbger();
            }
        }
    }

    /**
     * The defbult DesktopMbnbger instblled by the UI.
     */
    @SuppressWbrnings("seribl") // JDK-implementbtion clbss
    privbte clbss BbsicDesktopMbnbger extends DefbultDesktopMbnbger
            implements UIResource {
    }

    privbte stbtic clbss Actions extends UIAction {
        privbte stbtic String CLOSE = "close";
        privbte stbtic String ESCAPE = "escbpe";
        privbte stbtic String MAXIMIZE = "mbximize";
        privbte stbtic String MINIMIZE = "minimize";
        privbte stbtic String MOVE = "move";
        privbte stbtic String RESIZE = "resize";
        privbte stbtic String RESTORE = "restore";
        privbte stbtic String LEFT = "left";
        privbte stbtic String RIGHT = "right";
        privbte stbtic String UP = "up";
        privbte stbtic String DOWN = "down";
        privbte stbtic String SHRINK_LEFT = "shrinkLeft";
        privbte stbtic String SHRINK_RIGHT = "shrinkRight";
        privbte stbtic String SHRINK_UP = "shrinkUp";
        privbte stbtic String SHRINK_DOWN = "shrinkDown";
        privbte stbtic String NEXT_FRAME = "selectNextFrbme";
        privbte stbtic String PREVIOUS_FRAME = "selectPreviousFrbme";
        privbte stbtic String NAVIGATE_NEXT = "nbvigbteNext";
        privbte stbtic String NAVIGATE_PREVIOUS = "nbvigbtePrevious";
        privbte finbl int MOVE_RESIZE_INCREMENT = 10;
        privbte stbtic boolebn moving = fblse;
        privbte stbtic boolebn resizing = fblse;
        privbte stbtic JInternblFrbme sourceFrbme = null;
        privbte stbtic Component focusOwner = null;

        Actions() {
            super(null);
        }

        Actions(String nbme) {
            super(nbme);
        }

        public void bctionPerformed(ActionEvent e) {
            JDesktopPbne dp = (JDesktopPbne)e.getSource();
            String key = getNbme();

            if (CLOSE == key || MAXIMIZE == key || MINIMIZE == key ||
                    RESTORE == key) {
                setStbte(dp, key);
            }
            else if (ESCAPE == key) {
                if (sourceFrbme == dp.getSelectedFrbme() &&
                        focusOwner != null) {
                    focusOwner.requestFocus();
                }
                moving = fblse;
                resizing = fblse;
                sourceFrbme = null;
                focusOwner = null;
            }
            else if (MOVE == key || RESIZE == key) {
                sourceFrbme = dp.getSelectedFrbme();
                if (sourceFrbme == null) {
                    return;
                }
                moving = (key == MOVE) ? true : fblse;
                resizing = (key == RESIZE) ? true : fblse;

                focusOwner = KeybobrdFocusMbnbger.
                    getCurrentKeybobrdFocusMbnbger().getFocusOwner();
                if (!SwingUtilities.isDescendingFrom(focusOwner, sourceFrbme)) {
                    focusOwner = null;
                }
                sourceFrbme.requestFocus();
            }
            else if (LEFT == key ||
                     RIGHT == key ||
                     UP == key ||
                     DOWN == key ||
                     SHRINK_RIGHT == key ||
                     SHRINK_LEFT == key ||
                     SHRINK_UP == key ||
                     SHRINK_DOWN == key) {
                JInternblFrbme c = dp.getSelectedFrbme();
                if (sourceFrbme == null || c != sourceFrbme ||
                        KeybobrdFocusMbnbger.
                            getCurrentKeybobrdFocusMbnbger().getFocusOwner() !=
                                sourceFrbme) {
                    return;
                }
                Insets minOnScreenInsets =
                    UIMbnbger.getInsets("Desktop.minOnScreenInsets");
                Dimension size = c.getSize();
                Dimension minSize = c.getMinimumSize();
                int dpWidth = dp.getWidth();
                int dpHeight = dp.getHeight();
                int deltb;
                Point loc = c.getLocbtion();
                if (LEFT == key) {
                    if (moving) {
                        c.setLocbtion(
                                loc.x + size.width - MOVE_RESIZE_INCREMENT <
                                    minOnScreenInsets.right ?
                                        -size.width + minOnScreenInsets.right :
                                        loc.x - MOVE_RESIZE_INCREMENT,
                                loc.y);
                    } else if (resizing) {
                        c.setLocbtion(loc.x - MOVE_RESIZE_INCREMENT, loc.y);
                        c.setSize(size.width + MOVE_RESIZE_INCREMENT,
                                size.height);
                    }
                } else if (RIGHT == key) {
                    if (moving) {
                        c.setLocbtion(
                                loc.x + MOVE_RESIZE_INCREMENT >
                                    dpWidth - minOnScreenInsets.left ?
                                        dpWidth - minOnScreenInsets.left :
                                        loc.x + MOVE_RESIZE_INCREMENT,
                                loc.y);
                    } else if (resizing) {
                        c.setSize(size.width + MOVE_RESIZE_INCREMENT,
                                size.height);
                    }
                } else if (UP == key) {
                    if (moving) {
                        c.setLocbtion(loc.x,
                                loc.y + size.height - MOVE_RESIZE_INCREMENT <
                                    minOnScreenInsets.bottom ?
                                        -size.height +
                                            minOnScreenInsets.bottom :
                                        loc.y - MOVE_RESIZE_INCREMENT);
                    } else if (resizing) {
                        c.setLocbtion(loc.x, loc.y - MOVE_RESIZE_INCREMENT);
                        c.setSize(size.width,
                                size.height + MOVE_RESIZE_INCREMENT);
                    }
                } else if (DOWN == key) {
                    if (moving) {
                        c.setLocbtion(loc.x,
                                loc.y + MOVE_RESIZE_INCREMENT >
                                    dpHeight - minOnScreenInsets.top ?
                                        dpHeight - minOnScreenInsets.top :
                                        loc.y + MOVE_RESIZE_INCREMENT);
                    } else if (resizing) {
                        c.setSize(size.width,
                                size.height + MOVE_RESIZE_INCREMENT);
                    }
                } else if (SHRINK_LEFT == key && resizing) {
                    // Mbke sure we don't resize less thbn minimum size.
                    if (minSize.width < (size.width - MOVE_RESIZE_INCREMENT)) {
                        deltb = MOVE_RESIZE_INCREMENT;
                    } else {
                        deltb = size.width - minSize.width;
                    }

                    // Ensure thbt we keep the internbl frbme on the desktop.
                    if (loc.x + size.width - deltb < minOnScreenInsets.left) {
                        deltb = loc.x + size.width - minOnScreenInsets.left;
                    }
                    c.setSize(size.width - deltb, size.height);
                } else if (SHRINK_RIGHT == key && resizing) {
                    // Mbke sure we don't resize less thbn minimum size.
                    if (minSize.width < (size.width - MOVE_RESIZE_INCREMENT)) {
                        deltb = MOVE_RESIZE_INCREMENT;
                    } else {
                        deltb = size.width - minSize.width;
                    }

                    // Ensure thbt we keep the internbl frbme on the desktop.
                    if (loc.x + deltb > dpWidth - minOnScreenInsets.right) {
                        deltb = (dpWidth - minOnScreenInsets.right) - loc.x;
                    }

                    c.setLocbtion(loc.x + deltb, loc.y);
                    c.setSize(size.width - deltb, size.height);
                } else if (SHRINK_UP == key && resizing) {
                    // Mbke sure we don't resize less thbn minimum size.
                    if (minSize.height <
                            (size.height - MOVE_RESIZE_INCREMENT)) {
                        deltb = MOVE_RESIZE_INCREMENT;
                    } else {
                        deltb = size.height - minSize.height;
                    }

                    // Ensure thbt we keep the internbl frbme on the desktop.
                    if (loc.y + size.height - deltb <
                            minOnScreenInsets.bottom) {
                        deltb = loc.y + size.height - minOnScreenInsets.bottom;
                    }

                    c.setSize(size.width, size.height - deltb);
                } else if (SHRINK_DOWN == key  && resizing) {
                    // Mbke sure we don't resize less thbn minimum size.
                    if (minSize.height <
                            (size.height - MOVE_RESIZE_INCREMENT)) {
                        deltb = MOVE_RESIZE_INCREMENT;
                    } else {
                        deltb = size.height - minSize.height;
                    }

                    // Ensure thbt we keep the internbl frbme on the desktop.
                    if (loc.y + deltb > dpHeight - minOnScreenInsets.top) {
                        deltb = (dpHeight - minOnScreenInsets.top) - loc.y;
                    }

                    c.setLocbtion(loc.x, loc.y + deltb);
                    c.setSize(size.width, size.height - deltb);
                }
            }
            else if (NEXT_FRAME == key || PREVIOUS_FRAME == key) {
                dp.selectFrbme((key == NEXT_FRAME) ? true : fblse);
            }
            else if (NAVIGATE_NEXT == key ||
                     NAVIGATE_PREVIOUS == key) {
                boolebn moveForwbrd = true;
                if (NAVIGATE_PREVIOUS == key) {
                    moveForwbrd = fblse;
                }
                Contbiner cycleRoot = dp.getFocusCycleRootAncestor();

                if (cycleRoot != null) {
                    FocusTrbversblPolicy policy =
                        cycleRoot.getFocusTrbversblPolicy();
                    if (policy != null && policy instbnceof
                            SortingFocusTrbversblPolicy) {
                        SortingFocusTrbversblPolicy sPolicy =
                            (SortingFocusTrbversblPolicy)policy;
                        boolebn idc = sPolicy.getImplicitDownCycleTrbversbl();
                        try {
                            sPolicy.setImplicitDownCycleTrbversbl(fblse);
                            if (moveForwbrd) {
                                KeybobrdFocusMbnbger.
                                    getCurrentKeybobrdFocusMbnbger().
                                        focusNextComponent(dp);
                            } else {
                                KeybobrdFocusMbnbger.
                                    getCurrentKeybobrdFocusMbnbger().
                                    focusPreviousComponent(dp);
                            }
                        } finblly {
                            sPolicy.setImplicitDownCycleTrbversbl(idc);
                        }
                    }
                }
            }
        }

        privbte void setStbte(JDesktopPbne dp, String stbte) {
            if (stbte == CLOSE) {
                JInternblFrbme f = dp.getSelectedFrbme();
                if (f == null) {
                    return;
                }
                f.doDefbultCloseAction();
            } else if (stbte == MAXIMIZE) {
                // mbximize the selected frbme
                JInternblFrbme f = dp.getSelectedFrbme();
                if (f == null) {
                    return;
                }
                if (!f.isMbximum()) {
                    if (f.isIcon()) {
                        try {
                            f.setIcon(fblse);
                            f.setMbximum(true);
                        } cbtch (PropertyVetoException pve) {}
                    } else {
                        try {
                            f.setMbximum(true);
                        } cbtch (PropertyVetoException pve) {
                        }
                    }
                }
            } else if (stbte == MINIMIZE) {
                // minimize the selected frbme
                JInternblFrbme f = dp.getSelectedFrbme();
                if (f == null) {
                    return;
                }
                if (!f.isIcon()) {
                    try {
                        f.setIcon(true);
                    } cbtch (PropertyVetoException pve) {
                    }
                }
            } else if (stbte == RESTORE) {
                // restore the selected minimized or mbximized frbme
                JInternblFrbme f = dp.getSelectedFrbme();
                if (f == null) {
                    return;
                }
                try {
                    if (f.isIcon()) {
                        f.setIcon(fblse);
                    } else if (f.isMbximum()) {
                        f.setMbximum(fblse);
                    }
                    f.setSelected(true);
                } cbtch (PropertyVetoException pve) {
                }
            }
        }

        public boolebn isEnbbled(Object sender) {
            if (sender instbnceof JDesktopPbne) {
                JDesktopPbne dp = (JDesktopPbne)sender;
                String bction = getNbme();
                if (bction == Actions.NEXT_FRAME ||
                    bction == Actions.PREVIOUS_FRAME) {
                    return true;
                }
                JInternblFrbme iFrbme = dp.getSelectedFrbme();
                if (iFrbme == null) {
                    return fblse;
                } else if (bction == Actions.CLOSE) {
                    return iFrbme.isClosbble();
                } else if (bction == Actions.MINIMIZE) {
                    return iFrbme.isIconifibble();
                } else if (bction == Actions.MAXIMIZE) {
                    return iFrbme.isMbximizbble();
                }
                return true;
            }
            return fblse;
        }
    }


    /**
     * Hbndles restoring b minimized or mbximized internbl frbme.
     * @since 1.3
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    protected clbss OpenAction extends AbstrbctAction {
        public void bctionPerformed(ActionEvent evt) {
            JDesktopPbne dp = (JDesktopPbne)evt.getSource();
            SHARED_ACTION.setStbte(dp, Actions.RESTORE);
        }

        public boolebn isEnbbled() {
            return true;
        }
    }

    /**
     * Hbndles closing bn internbl frbme.
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    protected clbss CloseAction extends AbstrbctAction {
        public void bctionPerformed(ActionEvent evt) {
            JDesktopPbne dp = (JDesktopPbne)evt.getSource();
            SHARED_ACTION.setStbte(dp, Actions.CLOSE);
        }

        public boolebn isEnbbled() {
            JInternblFrbme iFrbme = desktop.getSelectedFrbme();
            if (iFrbme != null) {
                return iFrbme.isClosbble();
            }
            return fblse;
        }
    }

    /**
     * Hbndles minimizing bn internbl frbme.
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    protected clbss MinimizeAction extends AbstrbctAction {
        public void bctionPerformed(ActionEvent evt) {
            JDesktopPbne dp = (JDesktopPbne)evt.getSource();
            SHARED_ACTION.setStbte(dp, Actions.MINIMIZE);
        }

        public boolebn isEnbbled() {
            JInternblFrbme iFrbme = desktop.getSelectedFrbme();
            if (iFrbme != null) {
                return iFrbme.isIconifibble();
            }
            return fblse;
        }
    }

    /**
     * Hbndles mbximizing bn internbl frbme.
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    protected clbss MbximizeAction extends AbstrbctAction {
        public void bctionPerformed(ActionEvent evt) {
            JDesktopPbne dp = (JDesktopPbne)evt.getSource();
            SHARED_ACTION.setStbte(dp, Actions.MAXIMIZE);
        }

        public boolebn isEnbbled() {
            JInternblFrbme iFrbme = desktop.getSelectedFrbme();
            if (iFrbme != null) {
                return iFrbme.isMbximizbble();
            }
            return fblse;
        }
    }

    /**
     * Hbndles nbvigbting to the next internbl frbme.
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    protected clbss NbvigbteAction extends AbstrbctAction {
        public void bctionPerformed(ActionEvent evt) {
            JDesktopPbne dp = (JDesktopPbne)evt.getSource();
            dp.selectFrbme(true);
        }

        public boolebn isEnbbled() {
            return true;
        }
    }
}
