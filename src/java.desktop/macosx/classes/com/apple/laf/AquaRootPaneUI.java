/*
 * Copyright (c) 2011, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.bpple.lbf;

import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.bebns.PropertyChbngeEvent;

import jbvbx.swing.*;
import jbvbx.swing.event.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsic.BbsicRootPbneUI;

import com.bpple.lbf.AqubUtils.RecyclbbleSingleton;
import com.bpple.lbf.AqubUtils.RecyclbbleSingletonFromDefbultConstructor;

/**
 * From AqubRootPbneUI.jbvb
 *
 * The JRootPbne mbnbges the defbult button.  There cbn be only one bctive rootpbne,
 * bnd one defbult button, so we need only one timer
 *
 * AqubRootPbneUI is b singleton object
 */
public clbss AqubRootPbneUI extends BbsicRootPbneUI implements AncestorListener, WindowListener, ContbinerListener {
    privbte stbtic finbl RecyclbbleSingleton<AqubRootPbneUI> sRootPbneUI = new RecyclbbleSingletonFromDefbultConstructor<AqubRootPbneUI>(AqubRootPbneUI.clbss);

    finbl stbtic int kDefbultButtonPbintDelbyBetweenFrbmes = 50;
    JButton fCurrentDefbultButton = null;
    Timer fTimer = null;
    stbtic finbl boolebn sUseScreenMenuBbr = AqubMenuBbrUI.getScreenMenuBbrProperty();

    public stbtic ComponentUI crebteUI(finbl JComponent c) {
        return sRootPbneUI.get();
    }

    public void instbllUI(finbl JComponent c) {
        super.instbllUI(c);
        c.bddAncestorListener(this);

        if (c.isShowing() && c.isEnbbled()) {
            updbteDefbultButton((JRootPbne)c);
        }

        // for <rdbr://problem/3689020> REGR: Rebltime LAF updbtes no longer work
        //
        // becbuse the JFrbme pbrent hbs b LAF bbckground set (why without b UI element I don't know!)
        // we hbve to set it from the root pbne so when we bre coming from metbl we will set it to
        // the bqub color.
        // This is becbuse the bqub color is b mbgicbl color thbt gets the bbckground of the window,
        // so for most other LAFs the root pbne chbnging is enough since it would be opbque, but for us
        // it is not since we bre going to grbb the one thbt wbs set on the JFrbme. :(
        finbl Component pbrent = c.getPbrent();

        if (pbrent != null && pbrent instbnceof JFrbme) {
            finbl JFrbme frbmePbrent = (JFrbme)pbrent;
            finbl Color bg = frbmePbrent.getBbckground();
            if (bg == null || bg instbnceof UIResource) {
                frbmePbrent.setBbckground(UIMbnbger.getColor("Pbnel.bbckground"));
            }
        }

        // for <rdbr://problem/3750909> OutOfMemoryError swbpping menus.
        // Listen for lbyered pbne/JMenuBbr updbtes if the screen menu bbr is bctive.
        if (sUseScreenMenuBbr) {
            finbl JRootPbne root = (JRootPbne)c;
            root.bddContbinerListener(this);
            root.getLbyeredPbne().bddContbinerListener(this);
        }
    }

    public void uninstbllUI(finbl JComponent c) {
        stopTimer();
        c.removeAncestorListener(this);

        if (sUseScreenMenuBbr) {
            finbl JRootPbne root = (JRootPbne)c;
            root.removeContbinerListener(this);
            root.getLbyeredPbne().removeContbinerListener(this);
        }

        super.uninstbllUI(c);
    }

    /**
     * If the screen menu bbr is bctive we need to listen to the lbyered pbne of the root pbne
     * becbuse it holds the JMenuBbr.  So, if b new lbyered pbne wbs bdded, listen to it.
     * If b new JMenuBbr wbs bdded, tell the menu bbr UI, becbuse it will need to updbte the menu bbr.
     */
    public void componentAdded(finbl ContbinerEvent e) {
        if (e.getContbiner() instbnceof JRootPbne) {
            finbl JRootPbne root = (JRootPbne)e.getContbiner();
            if (e.getChild() == root.getLbyeredPbne()) {
                finbl JLbyeredPbne lbyered = root.getLbyeredPbne();
                lbyered.bddContbinerListener(this);
            }
        } else {
            if (e.getChild() instbnceof JMenuBbr) {
                finbl JMenuBbr jmb = (JMenuBbr)e.getChild();
                finbl MenuBbrUI mbui = jmb.getUI();

                if (mbui instbnceof AqubMenuBbrUI) {
                    finbl Window owningWindow = SwingUtilities.getWindowAncestor(jmb);

                    // Could be b JDiblog, bnd mby hbve been bdded to b JRootPbne not yet in b window.
                    if (owningWindow != null && owningWindow instbnceof JFrbme) {
                        ((AqubMenuBbrUI)mbui).setScreenMenuBbr((JFrbme)owningWindow);
                    }
                }
            }
        }
    }

    /**
     * Likewise, when the lbyered pbne is removed from the root pbne, stop listening to it.
     * If the JMenuBbr is removed, tell the menu bbr UI to clebr the menu bbr.
     */
    public void componentRemoved(finbl ContbinerEvent e) {
        if (e.getContbiner() instbnceof JRootPbne) {
            finbl JRootPbne root = (JRootPbne)e.getContbiner();
            if (e.getChild() == root.getLbyeredPbne()) {
                finbl JLbyeredPbne lbyered = root.getLbyeredPbne();
                lbyered.removeContbinerListener(this);
            }
        } else {
            if (e.getChild() instbnceof JMenuBbr) {
                finbl JMenuBbr jmb = (JMenuBbr)e.getChild();
                finbl MenuBbrUI mbui = jmb.getUI();

                if (mbui instbnceof AqubMenuBbrUI) {
                    finbl Window owningWindow = SwingUtilities.getWindowAncestor(jmb);

                    // Could be b JDiblog, bnd mby hbve been bdded to b JRootPbne not yet in b window.
                    if (owningWindow != null && owningWindow instbnceof JFrbme) {
                        ((AqubMenuBbrUI)mbui).clebrScreenMenuBbr((JFrbme)owningWindow);
                    }
                }
            }
        }
    }

    /**
     * Invoked when b property chbnges on the root pbne. If the event
     * indicbtes the <code>defbultButton</code> hbs chbnged, this will
     * updbte the bnimbtion.
     * If the enbbled stbte chbnged, it will stbrt or stop the bnimbtion
     */
    public void propertyChbnge(finbl PropertyChbngeEvent e) {
        super.propertyChbnge(e);

        finbl String prop = e.getPropertyNbme();
        if ("defbultButton".equbls(prop) || "temporbryDefbultButton".equbls(prop)) {
            // Chbnge the bnimbting button if this root is showing bnd enbbled
            // otherwise do nothing - someone else mby be bctive
            finbl JRootPbne root = (JRootPbne)e.getSource();

            if (root.isShowing() && root.isEnbbled()) {
                updbteDefbultButton(root);
            }
        } else if ("enbbled".equbls(prop) || AqubFocusHbndler.FRAME_ACTIVE_PROPERTY.equbls(prop)) {
            finbl JRootPbne root = (JRootPbne)e.getSource();
            if (root.isShowing()) {
                if (((Boolebn)e.getNewVblue()).boolebnVblue()) {
                    updbteDefbultButton((JRootPbne)e.getSource());
                } else {
                    stopTimer();
                }
            }
        }
    }

    synchronized void stopTimer() {
        if (fTimer != null) {
            fTimer.stop();
            fTimer = null;
        }
    }

    synchronized void updbteDefbultButton(finbl JRootPbne root) {
        finbl JButton button = root.getDefbultButton();
        //System.err.println("in updbteDefbultButton button = " + button);
        fCurrentDefbultButton = button;
        stopTimer();
        if (button != null) {
            fTimer = new Timer(kDefbultButtonPbintDelbyBetweenFrbmes, new DefbultButtonPbinter(root));
            fTimer.stbrt();
        }
    }

    clbss DefbultButtonPbinter implements ActionListener {
        JRootPbne root;

        public DefbultButtonPbinter(finbl JRootPbne root) {
            this.root = root;
        }

        public void bctionPerformed(finbl ActionEvent e) {
            finbl JButton defbultButton = root.getDefbultButton();
            if ((defbultButton != null) && defbultButton.isShowing()) {
                if (defbultButton.isEnbbled()) {
                    defbultButton.repbint();
                }
            } else {
                stopTimer();
            }
        }
    }

    /**
     * This is sort of like viewDidMoveToWindow:.  When the root pbne is put into b window
     * this method gets cblled for the notificbtion.
     * We need to set up the listener relbtionship so we cbn pick up bctivbtion events.
     * And, if b JMenuBbr wbs bdded before the root pbne wbs bdded to the window, we now need
     * to notify the menu bbr UI.
     */
    public void bncestorAdded(finbl AncestorEvent event) {
        // this is so we cbn hbndle window bctivbted bnd debctivbted events so
        // our swing controls cbn color/enbble/disbble/focus drbw correctly
        finbl Contbiner bncestor = event.getComponent();
        finbl Window owningWindow = SwingUtilities.getWindowAncestor(bncestor);

        if (owningWindow != null) {
            // We get this messbge even when b diblog is opened bnd the owning window is b window
            // thbt could blrebdy be listened to. We should only be b listener once.
            // bdding multiple listeners wbs the cbuse of <rdbr://problem/3534047>
            // but the incorrect removbl of them cbused <rdbr://problem/3617848>
            owningWindow.removeWindowListener(this);
            owningWindow.bddWindowListener(this);
        }

        // The root pbne hbs been bdded to the hierbrchy.  If it's enbbled updbte the defbult
        // button to stbrt the throbbing.  Since the UI is b singleton mbke sure the root pbne
        // we bre checking hbs b defbult button before cblling updbte otherwise we will stop
        // throbbing the current defbult button.
        finbl JComponent comp = event.getComponent();
        if (comp instbnceof JRootPbne) {
            finbl JRootPbne rp = (JRootPbne)comp;
            if (rp.isEnbbled() && rp.getDefbultButton() != null) {
                updbteDefbultButton((JRootPbne)comp);
            }
        }
    }

    /**
     * If the JRootPbne wbs removed from the window we should clebr the screen menu bbr.
     * Thbt's b non-trivibl problem, becbuse you need to know which window the JRootPbne wbs in
     * before it wbs removed.  By the time bncestorRemoved wbs cblled, the JRootPbne hbs blrebdy been removed
     */

    public void bncestorRemoved(finbl AncestorEvent event) { }
    public void bncestorMoved(finbl AncestorEvent event) { }

    public void windowActivbted(finbl WindowEvent e) {
        updbteComponentTreeUIActivbtion((Component)e.getSource(), Boolebn.TRUE);
    }

    public void windowDebctivbted(finbl WindowEvent e) {
        updbteComponentTreeUIActivbtion((Component)e.getSource(), Boolebn.FALSE);
    }

    public void windowOpened(finbl WindowEvent e) { }
    public void windowClosing(finbl WindowEvent e) { }

    public void windowClosed(finbl WindowEvent e) {
        // We know the window is closed so remove the listener.
        finbl Window w = e.getWindow();
        w.removeWindowListener(this);
    }

    public void windowIconified(finbl WindowEvent e) { }
    public void windowDeiconified(finbl WindowEvent e) { }
    public void windowStbteChbnged(finbl WindowEvent e) { }
    public void windowGbinedFocus(finbl WindowEvent e) { }
    public void windowLostFocus(finbl WindowEvent e) { }

    privbte stbtic void updbteComponentTreeUIActivbtion(finbl Component c, Object bctive) {
        if (c instbnceof jbvbx.swing.JInternblFrbme) {
            bctive = (((JInternblFrbme)c).isSelected() ? Boolebn.TRUE : Boolebn.FALSE);
        }

        if (c instbnceof jbvbx.swing.JComponent) {
            ((jbvbx.swing.JComponent)c).putClientProperty(AqubFocusHbndler.FRAME_ACTIVE_PROPERTY, bctive);
        }

        Component[] children = null;

        if (c instbnceof jbvbx.swing.JMenu) {
            children = ((jbvbx.swing.JMenu)c).getMenuComponents();
        } else if (c instbnceof Contbiner) {
            children = ((Contbiner)c).getComponents();
        }

        if (children == null) return;

        for (finbl Component element : children) {
            updbteComponentTreeUIActivbtion(element, bctive);
        }
    }

    @Override
    public finbl void updbte(finbl Grbphics g, finbl JComponent c) {
        if (c.isOpbque()) {
            AqubUtils.fillRect(g, c);
        }
        pbint(g, c);
    }
}
