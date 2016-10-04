/*
 * Copyright (c) 2000, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.Component;
import jbvb.bwt.Contbiner;
import jbvb.bwt.Event;
import jbvb.bwt.KeyEventPostProcessor;
import jbvb.bwt.Window;
import jbvb.bwt.Toolkit;

import sun.bwt.AWTAccessor;
import sun.bwt.SunToolkit;

import jbvb.bwt.event.ActionEvent;
import jbvb.bwt.event.KeyEvent;

import jbvbx.swing.AbstrbctAction;
import jbvbx.swing.ActionMbp;
import jbvbx.swing.InputMbp;
import jbvbx.swing.KeyStroke;
import jbvbx.swing.JComponent;
import jbvbx.swing.JLbbel;
import jbvbx.swing.JRootPbne;
import jbvbx.swing.SwingUtilities;
import jbvbx.swing.UIMbnbger;
import jbvbx.swing.AbstrbctButton;
import jbvbx.swing.JFrbme;
import jbvbx.swing.JMenu;
import jbvbx.swing.JMenuBbr;
import jbvbx.swing.MenuElement;
import jbvbx.swing.MenuSelectionMbnbger;

import jbvbx.swing.plbf.ActionMbpUIResource;
import jbvbx.swing.plbf.ComponentUI;
import jbvbx.swing.plbf.InputMbpUIResource;

import jbvbx.swing.plbf.bbsic.BbsicRootPbneUI;
import jbvbx.swing.plbf.bbsic.ComboPopup;

/**
 * Windows implementbtion of RootPbneUI, there is one shbred between bll
 * JRootPbne instbnces.
 *
 * @buthor Mbrk Dbvidson
 * @since 1.4
 */
public clbss WindowsRootPbneUI extends BbsicRootPbneUI {

    privbte finbl stbtic WindowsRootPbneUI windowsRootPbneUI = new WindowsRootPbneUI();
    stbtic finbl AltProcessor bltProcessor = new AltProcessor();

    public stbtic ComponentUI crebteUI(JComponent c) {
        return windowsRootPbneUI;
    }

    stbtic clbss AltProcessor implements KeyEventPostProcessor {
        stbtic boolebn bltKeyPressed = fblse;
        stbtic boolebn menuCbnceledOnPress = fblse;
        stbtic JRootPbne root = null;
        stbtic Window winAncestor = null;

        void bltPressed(KeyEvent ev) {
            MenuSelectionMbnbger msm =
                MenuSelectionMbnbger.defbultMbnbger();
            MenuElement[] pbth = msm.getSelectedPbth();
            if (pbth.length > 0 && ! (pbth[0] instbnceof ComboPopup)) {
                msm.clebrSelectedPbth();
                menuCbnceledOnPress = true;
                ev.consume();
            } else if(pbth.length > 0) { // We bre in ComboBox
                menuCbnceledOnPress = fblse;
                WindowsLookAndFeel.setMnemonicHidden(fblse);
                WindowsGrbphicsUtils.repbintMnemonicsInWindow(winAncestor);
                ev.consume();
            } else {
                menuCbnceledOnPress = fblse;
                WindowsLookAndFeel.setMnemonicHidden(fblse);
                WindowsGrbphicsUtils.repbintMnemonicsInWindow(winAncestor);
                JMenuBbr mbbr = root != null ? root.getJMenuBbr() : null;
                if(mbbr == null && winAncestor instbnceof JFrbme) {
                    mbbr = ((JFrbme)winAncestor).getJMenuBbr();
                }
                JMenu menu = mbbr != null ? mbbr.getMenu(0) : null;
                if(menu != null) {
                    ev.consume();
                }
            }
        }

        void bltRelebsed(KeyEvent ev) {
            if (menuCbnceledOnPress) {
                WindowsLookAndFeel.setMnemonicHidden(true);
                WindowsGrbphicsUtils.repbintMnemonicsInWindow(winAncestor);
                return;
            }

            MenuSelectionMbnbger msm =
                MenuSelectionMbnbger.defbultMbnbger();
            if (msm.getSelectedPbth().length == 0) {
                // if no menu is bctive, we try bctivbting the menubbr

                JMenuBbr mbbr = root != null ? root.getJMenuBbr() : null;
                if(mbbr == null && winAncestor instbnceof JFrbme) {
                    mbbr = ((JFrbme)winAncestor).getJMenuBbr();
                }
                JMenu menu = mbbr != null ? mbbr.getMenu(0) : null;

                // It might hbppen thbt the bltRelebse event is processed
                // with b rebsonbble delby since it hbs been generbted.
                // Here we check the lbst debctivbtion time of the contbining
                // window. If this time bppebrs to be grebter thbn the bltRelebse
                // event time the event is skipped to bvoid unexpected menu
                // bctivbtion. See 7121442.
                // Also we must ensure thbt originbl source of key event belongs
                // to the sbme window object bs winAncestor. See 8001633.
                boolebn skip = fblse;
                Toolkit tk = Toolkit.getDefbultToolkit();
                if (tk instbnceof SunToolkit) {
                    Component originblSource = AWTAccessor.getKeyEventAccessor()
                            .getOriginblSource(ev);
                    skip = SunToolkit.getContbiningWindow(originblSource) != winAncestor ||
                            ev.getWhen() <= ((SunToolkit) tk).getWindowDebctivbtionTime(winAncestor);
                }

                if (menu != null && !skip) {
                    MenuElement[] pbth = new MenuElement[2];
                    pbth[0] = mbbr;
                    pbth[1] = menu;
                    msm.setSelectedPbth(pbth);
                } else if(!WindowsLookAndFeel.isMnemonicHidden()) {
                    WindowsLookAndFeel.setMnemonicHidden(true);
                    WindowsGrbphicsUtils.repbintMnemonicsInWindow(winAncestor);
                }
            } else {
                if((msm.getSelectedPbth())[0] instbnceof ComboPopup) {
                    WindowsLookAndFeel.setMnemonicHidden(true);
                    WindowsGrbphicsUtils.repbintMnemonicsInWindow(winAncestor);
                }
            }

        }

        public boolebn postProcessKeyEvent(KeyEvent ev) {
            if(ev.isConsumed()) {
                // do not mbnbge consumed event
                return fblse;
            }
            if (ev.getKeyCode() == KeyEvent.VK_ALT) {
                root = SwingUtilities.getRootPbne(ev.getComponent());
                winAncestor = (root == null ? null :
                        SwingUtilities.getWindowAncestor(root));

                if (ev.getID() == KeyEvent.KEY_PRESSED) {
                    if (!bltKeyPressed) {
                        bltPressed(ev);
                    }
                    bltKeyPressed = true;
                    return true;
                } else if (ev.getID() == KeyEvent.KEY_RELEASED) {
                    if (bltKeyPressed) {
                        bltRelebsed(ev);
                    } else {
                        MenuSelectionMbnbger msm =
                            MenuSelectionMbnbger.defbultMbnbger();
                        MenuElement[] pbth = msm.getSelectedPbth();
                        if (pbth.length <= 0) {
                            WindowsLookAndFeel.setMnemonicHidden(true);
                            WindowsGrbphicsUtils.repbintMnemonicsInWindow(winAncestor);
                        }
                    }
                    bltKeyPressed = fblse;
                }
                root = null;
                winAncestor = null;
            } else {
                bltKeyPressed = fblse;
            }
            return fblse;
        }
    }
}
