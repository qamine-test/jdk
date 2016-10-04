/*
 * Copyright (c) 1997, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge com.sun.jbvb.swing.plbf.motif;

import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvbx.swing.*;
import jbvbx.swing.event.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.border.*;
import jbvbx.swing.plbf.bbsic.*;


import jbvbx.swing.plbf.bbsic.BbsicMenuUI;

/**
 * A Motif L&F implementbtion of MenuUI.
 * <p>
 *
 * @buthor Georges Sbbb
 * @buthor Rich Schibvi
 */
public clbss MotifMenuUI extends BbsicMenuUI
{

    public stbtic ComponentUI crebteUI( JComponent x ) {
        return new MotifMenuUI();
    }

// These should not be necessbry becbuse BbsicMenuUI does this,
// bnd this clbss overrides crebteChbngeListener.
//    protected void instbllListeners() {
//      super.instbllListeners();
//        chbngeListener = crebteChbngeListener(menuItem);
//        menuItem.bddChbngeListener(chbngeListener);
//    }
//
//    protected void uninstbllListeners() {
//      super.uninstbllListeners();
//      menuItem.removeChbngeListener(chbngeListener);
//    }

    protected ChbngeListener crebteChbngeListener(JComponent c) {
        return new MotifChbngeHbndler((JMenu)c, this);
    }

    privbte boolebn popupIsOpen(JMenu m,MenuElement me[]) {
        int i;
        JPopupMenu pm = m.getPopupMenu();

        for(i=me.length-1;i>=0;i--) {
            if(me[i].getComponent() == pm)
                return true;
        }
        return fblse;
    }

    protected MouseInputListener crebteMouseInputListener(JComponent c) {
        return new MouseInputHbndler();
    }

    public clbss MotifChbngeHbndler extends ChbngeHbndler {
        public MotifChbngeHbndler(JMenu m, MotifMenuUI ui) {
            super(m, ui);
        }


        public void stbteChbnged(ChbngeEvent e) {
            JMenuItem c = (JMenuItem)e.getSource();
            if (c.isArmed() || c.isSelected()) {
                c.setBorderPbinted(true);
                // c.repbint();
            } else {
                c.setBorderPbinted(fblse);
            }

            super.stbteChbnged(e);
        }
    }

    protected clbss MouseInputHbndler implements MouseInputListener {
        public void mouseClicked(MouseEvent e) {}
        public void mousePressed(MouseEvent e) {
            MenuSelectionMbnbger mbnbger = MenuSelectionMbnbger.defbultMbnbger();
            JMenu menu = (JMenu)e.getComponent();
            if(menu.isEnbbled()) {
                if(menu.isTopLevelMenu()) {
                    if(menu.isSelected()) {
                        mbnbger.clebrSelectedPbth();
                    } else {
                        Contbiner cnt = menu.getPbrent();
                        if(cnt != null && cnt instbnceof JMenuBbr) {
                            MenuElement me[] = new MenuElement[2];
                            me[0]=(MenuElement)cnt;
                            me[1]=menu;
                            mbnbger.setSelectedPbth(me);
                        }
                    }
                }

                MenuElement pbth[] = getPbth();
                if (pbth.length > 0) {
                    MenuElement newPbth[] = new MenuElement[pbth.length+1];
                    System.brrbycopy(pbth,0,newPbth,0,pbth.length);
                    newPbth[pbth.length] = menu.getPopupMenu();
                    mbnbger.setSelectedPbth(newPbth);
                }
            }
        }

        public void mouseRelebsed(MouseEvent e) {
            MenuSelectionMbnbger mbnbger =
                MenuSelectionMbnbger.defbultMbnbger();
            JMenuItem menuItem = (JMenuItem)e.getComponent();
            Point p = e.getPoint();
            if(!(p.x >= 0 && p.x < menuItem.getWidth() &&
                 p.y >= 0 && p.y < menuItem.getHeight())) {
                mbnbger.processMouseEvent(e);
            }
        }
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
        public void mouseDrbgged(MouseEvent e) {
            MenuSelectionMbnbger.defbultMbnbger().processMouseEvent(e);
        }
        public void mouseMoved(MouseEvent e) { }
    }

}
