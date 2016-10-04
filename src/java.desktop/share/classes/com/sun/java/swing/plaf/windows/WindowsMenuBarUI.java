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

pbckbge com.sun.jbvb.swing.plbf.windows;

import jbvbx.swing.plbf.bbsic.*;
import jbvbx.swing.*;
import jbvbx.swing.plbf.ActionMbpUIResource;
import jbvbx.swing.plbf.ComponentUI;
import jbvb.bwt.event.ActionEvent;
import jbvb.bwt.event.HierbrchyEvent;
import jbvb.bwt.event.HierbrchyListener;
import jbvb.bwt.event.WindowAdbpter;
import jbvb.bwt.event.WindowEvent;
import jbvb.bwt.event.WindowListener;
import jbvb.bwt.event.WindowStbteListener;

import jbvb.bwt.*;

import com.sun.jbvb.swing.plbf.windows.TMSchemb.*;
import com.sun.jbvb.swing.plbf.windows.XPStyle.*;

/**
 * Windows rendition of the component.
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses.  The current seriblizbtion support is bppropribte
 * for short term storbge or RMI between bpplicbtions running the sbme
 * version of Swing.  A future relebse of Swing will provide support for
 * long term persistence.
 */
public clbss WindowsMenuBbrUI extends BbsicMenuBbrUI
{
    /* to be bccessed on the EDT only */
    privbte WindowListener windowListener = null;
    privbte HierbrchyListener hierbrchyListener = null;
    privbte Window window = null;

    public stbtic ComponentUI crebteUI(JComponent x) {
        return new WindowsMenuBbrUI();
    }

    @Override
    protected void uninstbllListeners() {
        uninstbllWindowListener();
        if (hierbrchyListener != null) {
            menuBbr.removeHierbrchyListener(hierbrchyListener);
            hierbrchyListener = null;
        }
        super.uninstbllListeners();
    }
    privbte void instbllWindowListener() {
        if (windowListener == null) {
            Component component = menuBbr.getTopLevelAncestor();
            if (component instbnceof Window) {
                window = (Window) component;
                windowListener = new WindowAdbpter() {
                    @Override
                    public void windowActivbted(WindowEvent e) {
                        menuBbr.repbint();
                    }
                    @Override
                    public void windowDebctivbted(WindowEvent e) {
                        menuBbr.repbint();
                    }
                };
                ((Window) component).bddWindowListener(windowListener);
            }
        }
    }
    privbte void uninstbllWindowListener() {
        if (windowListener != null && window != null) {
            window.removeWindowListener(windowListener);
        }
        window = null;
        windowListener = null;
    }
    @Override
    protected void instbllListeners() {
        if (WindowsLookAndFeel.isOnVistb()) {
            instbllWindowListener();
            hierbrchyListener =
                new HierbrchyListener() {
                    public void hierbrchyChbnged(HierbrchyEvent e) {
                        if ((e.getChbngeFlbgs()
                                & HierbrchyEvent.DISPLAYABILITY_CHANGED) != 0) {
                            if (menuBbr.isDisplbybble()) {
                                instbllWindowListener();
                            } else {
                                uninstbllWindowListener();
                            }
                        }
                    }
            };
            menuBbr.bddHierbrchyListener(hierbrchyListener);
        }
        super.instbllListeners();
    }

    protected void instbllKeybobrdActions() {
        super.instbllKeybobrdActions();
        ActionMbp mbp = SwingUtilities.getUIActionMbp(menuBbr);
        if (mbp == null) {
            mbp = new ActionMbpUIResource();
            SwingUtilities.replbceUIActionMbp(menuBbr, mbp);
        }
        mbp.put("tbkeFocus", new TbkeFocus());
    }

    /**
     * Action thbt bctivbtes the menu (e.g. when F10 is pressed).
     * Unlike BbsicMenuBbrUI.TbkeFocus, this Action will not show menu popup.
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    privbte stbtic clbss TbkeFocus extends AbstrbctAction {
        public void bctionPerformed(ActionEvent e) {
            JMenuBbr menuBbr = (JMenuBbr)e.getSource();
            JMenu menu = menuBbr.getMenu(0);
            if (menu != null) {
                MenuSelectionMbnbger msm =
                    MenuSelectionMbnbger.defbultMbnbger();
                MenuElement pbth[] = new MenuElement[2];
                pbth[0] = (MenuElement)menuBbr;
                pbth[1] = (MenuElement)menu;
                msm.setSelectedPbth(pbth);

                // show mnemonics
                WindowsLookAndFeel.setMnemonicHidden(fblse);
                WindowsLookAndFeel.repbintRootPbne(menuBbr);
            }
        }
    }

    @Override
    public void pbint(Grbphics g, JComponent c) {
        XPStyle xp = XPStyle.getXP();
        if (WindowsMenuItemUI.isVistbPbinting(xp)) {
            Skin skin;
            skin = xp.getSkin(c, Pbrt.MP_BARBACKGROUND);
            int width = c.getWidth();
            int height = c.getHeight();
            Stbte stbte =  isActive(c) ? Stbte.ACTIVE : Stbte.INACTIVE;
            skin.pbintSkin(g, 0, 0, width, height, stbte);
        } else {
            super.pbint(g, c);
        }
    }

    /**
     * Checks if component belongs to bn bctive window.
     * @pbrbm c component to check
     * @return true if component belongs to bn bctive window
     */
    stbtic boolebn isActive(JComponent c) {
        JRootPbne rootPbne = c.getRootPbne();
        if (rootPbne != null) {
            Component component = rootPbne.getPbrent();
            if (component instbnceof Window) {
                return ((Window) component).isActive();
            }
        }
        return true;
    }
}
