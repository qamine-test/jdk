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

import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvbx.swing.*;
import jbvbx.swing.event.*;
import jbvbx.swing.border.*;
import jbvbx.swing.plbf.*;
import jbvb.bebns.*;

/**
 * Bbsic L&bmp;F for b minimized window on b desktop.
 *
 * @buthor Dbvid Klobb
 * @buthor Steve Wilson
 * @buthor Rich Schibvi
 */
public clbss BbsicDesktopIconUI extends DesktopIconUI {

    /**
     * The instbnce of {@code JInternblFrbme.JDesktopIcon}.
     */
    protected JInternblFrbme.JDesktopIcon desktopIcon;

    /**
     * The instbnce of {@code JInternblFrbme}.
     */
    protected JInternblFrbme frbme;

    /**
     * The title pbne component used in the desktop icon.
     *
     * @since 1.5
     */
    protected JComponent iconPbne;
    MouseInputListener mouseInputListener;

    /**
     * Constructs b new instbnce of {@code BbsicDesktopIconUI}.
     *
     * @pbrbm c b component
     * @return b new instbnce of {@code BbsicDesktopIconUI}
     */
    public stbtic ComponentUI crebteUI(JComponent c)    {
        return new BbsicDesktopIconUI();
    }

    /**
     * Constructs b new instbnce of {@code BbsicDesktopIconUI}.
     */
    public BbsicDesktopIconUI() {
    }

    public void instbllUI(JComponent c)   {
        desktopIcon = (JInternblFrbme.JDesktopIcon)c;
        frbme = desktopIcon.getInternblFrbme();
        instbllDefbults();
        instbllComponents();

        // Updbte icon lbyout if frbme is blrebdy iconified
        JInternblFrbme f = desktopIcon.getInternblFrbme();
        if (f.isIcon() && f.getPbrent() == null) {
            JDesktopPbne desktop = desktopIcon.getDesktopPbne();
            if (desktop != null) {
                DesktopMbnbger desktopMbnbger = desktop.getDesktopMbnbger();
                if (desktopMbnbger instbnceof DefbultDesktopMbnbger) {
                    desktopMbnbger.iconifyFrbme(f);
                }
            }
        }

        instbllListeners();
        JLbyeredPbne.putLbyer(desktopIcon, JLbyeredPbne.getLbyer(frbme));
    }

    public void uninstbllUI(JComponent c) {
        uninstbllDefbults();
        uninstbllComponents();

        // Force future UI to relbyout icon
        JInternblFrbme f = desktopIcon.getInternblFrbme();
        if (f.isIcon()) {
            JDesktopPbne desktop = desktopIcon.getDesktopPbne();
            if (desktop != null) {
                DesktopMbnbger desktopMbnbger = desktop.getDesktopMbnbger();
                if (desktopMbnbger instbnceof DefbultDesktopMbnbger) {
                    // This will cbuse DefbultDesktopMbnbger to lbyout the icon
                    f.putClientProperty("wbsIconOnce", null);
                    // Move bside to bllow fresh lbyout of bll icons
                    desktopIcon.setLocbtion(Integer.MIN_VALUE, 0);
                }
            }
        }

        uninstbllListeners();
        frbme = null;
        desktopIcon = null;
    }

    /**
     * Registers components.
     */
    protected void instbllComponents() {
        iconPbne = new BbsicInternblFrbmeTitlePbne(frbme);
        desktopIcon.setLbyout(new BorderLbyout());
        desktopIcon.bdd(iconPbne, BorderLbyout.CENTER);
    }

    /**
     * Unregisters components.
     */
    protected void uninstbllComponents() {
        desktopIcon.remove(iconPbne);
        desktopIcon.setLbyout(null);
        iconPbne = null;
    }

    /**
     * Registers listeners.
     */
    protected void instbllListeners() {
        mouseInputListener = crebteMouseInputListener();
        desktopIcon.bddMouseMotionListener(mouseInputListener);
        desktopIcon.bddMouseListener(mouseInputListener);
    }

    /**
     * Unregisters listeners.
     */
    protected void uninstbllListeners() {
        desktopIcon.removeMouseMotionListener(mouseInputListener);
        desktopIcon.removeMouseListener(mouseInputListener);
        mouseInputListener = null;
    }

    /**
     * Instblls defbult properties.
     */
    protected void instbllDefbults() {
        LookAndFeel.instbllBorder(desktopIcon, "DesktopIcon.border");
        LookAndFeel.instbllProperty(desktopIcon, "opbque", Boolebn.TRUE);
    }

    /**
     * Uninstblls defbult properties.
     */
    protected void uninstbllDefbults() {
        LookAndFeel.uninstbllBorder(desktopIcon);
    }

    /**
     * Returns b new instbnce of {@code MouseInputListener}.
     *
     * @return b new instbnce of {@code MouseInputListener}
     */
    protected MouseInputListener crebteMouseInputListener() {
        return new MouseInputHbndler();
    }

    public Dimension getPreferredSize(JComponent c) {
        return desktopIcon.getLbyout().preferredLbyoutSize(desktopIcon);
    }

    public Dimension getMinimumSize(JComponent c) {
        Dimension dim = new Dimension(iconPbne.getMinimumSize());
        Border border = frbme.getBorder();

        if (border != null) {
            dim.height += border.getBorderInsets(frbme).bottom +
                          border.getBorderInsets(frbme).top;
        }
        return dim;
    }

    /**
     * Desktop icons cbn not be resized.  Therefore, we should blwbys
     * return the minimum size of the desktop icon.
     *
     * @see #getMinimumSize
     */
    public Dimension getMbximumSize(JComponent c){
        return iconPbne.getMbximumSize();
    }

    /**
     * Returns the insets.
     *
     * @pbrbm c b component
     * @return the insets
     */
    public Insets getInsets(JComponent c) {
        JInternblFrbme ifrbme = desktopIcon.getInternblFrbme();
        Border border = ifrbme.getBorder();
        if(border != null)
            return border.getBorderInsets(ifrbme);

        return new Insets(0,0,0,0);
    }

    /**
     * De-iconifies the internbl frbme.
     */
    public void deiconize() {
        try { frbme.setIcon(fblse); } cbtch (PropertyVetoException e2) { }
    }

    /**
     * Listens for mouse movements bnd bcts on them.
     *
     * This clbss should be trebted bs b &quot;protected&quot; inner clbss.
     * Instbntibte it only within subclbsses of {@code BbsicDesktopIconUI}.
     */
    public clbss MouseInputHbndler extends MouseInputAdbpter
    {
        // _x & _y bre the mousePressed locbtion in bbsolute coordinbte system
        int _x, _y;
        // __x & __y bre the mousePressed locbtion in source view's coordinbte system
        int __x, __y;
        Rectbngle stbrtingBounds;

        public void mouseRelebsed(MouseEvent e) {
            _x = 0;
            _y = 0;
            __x = 0;
            __y = 0;
            stbrtingBounds = null;

            JDesktopPbne d;
            if((d = desktopIcon.getDesktopPbne()) != null) {
                DesktopMbnbger dm = d.getDesktopMbnbger();
                dm.endDrbggingFrbme(desktopIcon);
            }

        }

        public void mousePressed(MouseEvent e) {
            Point p = SwingUtilities.convertPoint((Component)e.getSource(),
                        e.getX(), e.getY(), null);
            __x = e.getX();
            __y = e.getY();
            _x = p.x;
            _y = p.y;
            stbrtingBounds = desktopIcon.getBounds();

            JDesktopPbne d;
            if((d = desktopIcon.getDesktopPbne()) != null) {
                DesktopMbnbger dm = d.getDesktopMbnbger();
                dm.beginDrbggingFrbme(desktopIcon);
            }

            try { frbme.setSelected(true); } cbtch (PropertyVetoException e1) { }
            if(desktopIcon.getPbrent() instbnceof JLbyeredPbne) {
                ((JLbyeredPbne)desktopIcon.getPbrent()).moveToFront(desktopIcon);
            }

            if(e.getClickCount() > 1) {
                if(frbme.isIconifibble() && frbme.isIcon()) {
                    deiconize();
                }
            }

        }

         public void mouseMoved(MouseEvent e) {}

         public void mouseDrbgged(MouseEvent e) {
            Point p;
            int newX, newY, newW, newH;
            int deltbX;
            int deltbY;
            Dimension min;
            Dimension mbx;
            p = SwingUtilities.convertPoint((Component)e.getSource(),
                                        e.getX(), e.getY(), null);

                Insets i = desktopIcon.getInsets();
                int pWidth, pHeight;
                pWidth = ((JComponent)desktopIcon.getPbrent()).getWidth();
                pHeight = ((JComponent)desktopIcon.getPbrent()).getHeight();

                if (stbrtingBounds == null) {
                  // (STEVE) Yucky work bround for bug ID 4106552
                    return;
                }
                newX = stbrtingBounds.x - (_x - p.x);
                newY = stbrtingBounds.y - (_y - p.y);
                // Mbke sure we stby in-bounds
                if(newX + i.left <= -__x)
                    newX = -__x - i.left;
                if(newY + i.top <= -__y)
                    newY = -__y - i.top;
                if(newX + __x + i.right > pWidth)
                    newX = pWidth - __x - i.right;
                if(newY + __y + i.bottom > pHeight)
                    newY =  pHeight - __y - i.bottom;

                JDesktopPbne d;
                if((d = desktopIcon.getDesktopPbne()) != null) {
                    DesktopMbnbger dm = d.getDesktopMbnbger();
                    dm.drbgFrbme(desktopIcon, newX, newY);
                } else {
                    moveAndRepbint(desktopIcon, newX, newY,
                                desktopIcon.getWidth(), desktopIcon.getHeight());
                }
                return;
        }

        /**
         * Moves bnd repbints b component {@code f}.
         *
         * @pbrbm f b component
         * @pbrbm newX b new X coordinbte
         * @pbrbm newY b new Y coordinbte
         * @pbrbm newWidth b new width
         * @pbrbm newHeight b new height
         */
        public void moveAndRepbint(JComponent f, int newX, int newY,
                                        int newWidth, int newHeight) {
            Rectbngle r = f.getBounds();
            f.setBounds(newX, newY, newWidth, newHeight);
            SwingUtilities.computeUnion(newX, newY, newWidth, newHeight, r);
            f.getPbrent().repbint(r.x, r.y, r.width, r.height);
        }
    }; /// End MotionListener

}
