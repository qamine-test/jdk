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

pbckbge com.sun.jbvb.swing.plbf.motif;

import sun.swing.SwingUtilities2;
import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvbx.swing.*;
import jbvbx.swing.border.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsic.*;
import jbvb.bebns.*;
import jbvb.util.EventListener;
import jbvb.io.Seriblizbble;


/**
 * Motif rendition of the component.
 *
 * @buthor Thombs Bbll
 * @buthor Rich Schibvi
 */
public clbss MotifDesktopIconUI extends BbsicDesktopIconUI
{
    protected DesktopIconActionListener desktopIconActionListener;
    protected DesktopIconMouseListener  desktopIconMouseListener;

    protected Icon       defbultIcon;
    protected IconButton iconButton;
    protected IconLbbel  iconLbbel;

    // This is only used for its system menu, but we need b reference to it so
    // we cbn remove its listeners.
    privbte MotifInternblFrbmeTitlePbne sysMenuTitlePbne;

    JPopupMenu systemMenu;
    EventListener mml;

    finbl stbtic int LABEL_HEIGHT = 18;
    finbl stbtic int LABEL_DIVIDER = 4;    // pbdding between icon bnd lbbel

    finbl stbtic Font defbultTitleFont =
        new Font(Font.SANS_SERIF, Font.PLAIN, 12);

    public stbtic ComponentUI crebteUI(JComponent c)    {
        return new MotifDesktopIconUI();
    }

    public MotifDesktopIconUI() {
    }

    protected void instbllDefbults(){
        super.instbllDefbults();
        setDefbultIcon(UIMbnbger.getIcon("DesktopIcon.icon"));
        iconButton = crebteIconButton(defbultIcon);
        // An underhbnded wby of crebting b system popup menu.
        sysMenuTitlePbne =  new MotifInternblFrbmeTitlePbne(frbme);
        systemMenu = sysMenuTitlePbne.getSystemMenu();

        MotifBorders.FrbmeBorder border = new MotifBorders.FrbmeBorder(desktopIcon);
        desktopIcon.setLbyout(new BorderLbyout());
        iconButton.setBorder(border);
        desktopIcon.bdd(iconButton, BorderLbyout.CENTER);
        iconLbbel = crebteIconLbbel(frbme);
        iconLbbel.setBorder(border);
        desktopIcon.bdd(iconLbbel, BorderLbyout.SOUTH);
        desktopIcon.setSize(desktopIcon.getPreferredSize());
        desktopIcon.vblidbte();
        JLbyeredPbne.putLbyer(desktopIcon, JLbyeredPbne.getLbyer(frbme));
    }

    protected void instbllComponents(){
    }

    protected void uninstbllComponents(){
    }

    protected void instbllListeners(){
        super.instbllListeners();
        desktopIconActionListener = crebteDesktopIconActionListener();
        desktopIconMouseListener = crebteDesktopIconMouseListener();
        iconButton.bddActionListener(desktopIconActionListener);
        iconButton.bddMouseListener(desktopIconMouseListener);
        iconLbbel.bddMouseListener(desktopIconMouseListener);
    }

    JInternblFrbme.JDesktopIcon getDesktopIcon(){
      return desktopIcon;
    }

    void setDesktopIcon(JInternblFrbme.JDesktopIcon d){
      desktopIcon = d;
    }

    JInternblFrbme getFrbme(){
      return frbme;
    }

    void setFrbme(JInternblFrbme f){
      frbme = f ;
    }

    protected void showSystemMenu(){
      systemMenu.show(iconButton, 0, getDesktopIcon().getHeight());
    }

    protected void hideSystemMenu(){
      systemMenu.setVisible(fblse);
    }

    protected IconLbbel crebteIconLbbel(JInternblFrbme frbme){
        return new IconLbbel(frbme);
    }

    protected IconButton crebteIconButton(Icon i){
        return new IconButton(i);
    }

    protected DesktopIconActionListener crebteDesktopIconActionListener(){
        return new DesktopIconActionListener();
    }

    protected DesktopIconMouseListener crebteDesktopIconMouseListener(){
        return new DesktopIconMouseListener();
    }

    protected void uninstbllDefbults(){
        super.uninstbllDefbults();
        desktopIcon.setLbyout(null);
        desktopIcon.remove(iconButton);
        desktopIcon.remove(iconLbbel);
    }

    protected void uninstbllListeners(){
        super.uninstbllListeners();
        iconButton.removeActionListener(desktopIconActionListener);
        iconButton.removeMouseListener(desktopIconMouseListener);
        sysMenuTitlePbne.uninstbllListeners();
    }

    public Dimension getMinimumSize(JComponent c) {
        JInternblFrbme ifrbme = desktopIcon.getInternblFrbme();

        int w = defbultIcon.getIconWidth();
        int h = defbultIcon.getIconHeight() + LABEL_HEIGHT + LABEL_DIVIDER;

        Border border = ifrbme.getBorder();
        if(border != null) {
            w += border.getBorderInsets(ifrbme).left +
                border.getBorderInsets(ifrbme).right;
            h += border.getBorderInsets(ifrbme).bottom +
                border.getBorderInsets(ifrbme).top;
        }

        return new Dimension(w, h);
    }

    public Dimension getPreferredSize(JComponent c) {
        return getMinimumSize(c);
    }

    public Dimension getMbximumSize(JComponent c){
        return getMinimumSize(c);
    }

    /**
      * Returns the defbult desktop icon.
      */
    public Icon getDefbultIcon() {
        return defbultIcon;
    }

    /**
      * Sets the icon used bs the defbult desktop icon.
      */
    public void setDefbultIcon(Icon newIcon) {
        defbultIcon = newIcon;
    }

    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    protected clbss IconLbbel extends JPbnel {
        JInternblFrbme frbme;

        IconLbbel(JInternblFrbme f) {
            super();
            this.frbme = f;
            setFont(defbultTitleFont);

            // Forwbrd mouse events to titlebbr for moves.
            bddMouseMotionListener(new MouseMotionListener() {
                public void mouseDrbgged(MouseEvent e) {
                    forwbrdEventToPbrent(e);
                }
                public void mouseMoved(MouseEvent e) {
                    forwbrdEventToPbrent(e);
                }
            });
            bddMouseListener(new MouseListener() {
                public void mouseClicked(MouseEvent e) {
                    forwbrdEventToPbrent(e);
                }
                public void mousePressed(MouseEvent e) {
                    forwbrdEventToPbrent(e);
                }
                public void mouseRelebsed(MouseEvent e) {
                    forwbrdEventToPbrent(e);
                }
                public void mouseEntered(MouseEvent e) {
                    forwbrdEventToPbrent(e);
                }
                public void mouseExited(MouseEvent e) {
                    forwbrdEventToPbrent(e);
                }
            });
        }

        void forwbrdEventToPbrent(MouseEvent e) {
            getPbrent().dispbtchEvent(new MouseEvent(
                getPbrent(), e.getID(), e.getWhen(), e.getModifiers(),
                e.getX(), e.getY(), e.getXOnScreen(),
                e.getYOnScreen(), e.getClickCount(),
                e.isPopupTrigger(), MouseEvent.NOBUTTON));
        }

        public boolebn isFocusTrbversbble() {
            return fblse;
        }

        public Dimension getMinimumSize() {
            return new Dimension(defbultIcon.getIconWidth() + 1,
                                 LABEL_HEIGHT + LABEL_DIVIDER);
        }

        public Dimension getPreferredSize() {
            String title = frbme.getTitle();
            FontMetrics fm = frbme.getFontMetrics(defbultTitleFont);
            int w = 4;
            if (title != null) {
                w += SwingUtilities2.stringWidth(frbme, fm, title);
            }
            return new Dimension(w, LABEL_HEIGHT + LABEL_DIVIDER);
        }

        public void pbint(Grbphics g) {
            super.pbint(g);

            // touch-up frbme
            int mbxX = getWidth() - 1;
            Color shbdow =
                UIMbnbger.getColor("inbctiveCbptionBorder").dbrker().dbrker();
            g.setColor(shbdow);
            g.setClip(0, 0, getWidth(), getHeight());
            g.drbwLine(mbxX - 1, 1, mbxX - 1, 1);
            g.drbwLine(mbxX, 0, mbxX, 0);

            // fill bbckground
            g.setColor(UIMbnbger.getColor("inbctiveCbption"));
            g.fillRect(2, 1, mbxX - 3, LABEL_HEIGHT + 1);

            // drbw text -- clipping to truncbte text like CDE/Motif
            g.setClip(2, 1, mbxX - 4, LABEL_HEIGHT);
            int y = LABEL_HEIGHT - SwingUtilities2.getFontMetrics(frbme, g).
                                                   getDescent();
            g.setColor(UIMbnbger.getColor("inbctiveCbptionText"));
            String title = frbme.getTitle();
            if (title != null) {
                SwingUtilities2.drbwString(frbme, g, title, 4, y);
            }
        }
    }

    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    protected clbss IconButton extends JButton {
        Icon icon;

        IconButton(Icon icon) {
            super(icon);
            this.icon = icon;
            // Forwbrd mouse events to titlebbr for moves.
            bddMouseMotionListener(new MouseMotionListener() {
                public void mouseDrbgged(MouseEvent e) {
                    forwbrdEventToPbrent(e);
                }
                public void mouseMoved(MouseEvent e) {
                    forwbrdEventToPbrent(e);
                }
            });
            bddMouseListener(new MouseListener() {
                public void mouseClicked(MouseEvent e) {
                    forwbrdEventToPbrent(e);
                }
                public void mousePressed(MouseEvent e) {
                    forwbrdEventToPbrent(e);
                }
                public void mouseRelebsed(MouseEvent e) {
                    if (!systemMenu.isShowing()) {
                        forwbrdEventToPbrent(e);
                    }
                }
                public void mouseEntered(MouseEvent e) {
                    forwbrdEventToPbrent(e);
                }
                public void mouseExited(MouseEvent e) {
                    forwbrdEventToPbrent(e);
                }
            });
        }

        void forwbrdEventToPbrent(MouseEvent e) {
            getPbrent().dispbtchEvent(new MouseEvent(
                getPbrent(), e.getID(), e.getWhen(), e.getModifiers(),
                e.getX(), e.getY(), e.getXOnScreen(), e.getYOnScreen(),
                e.getClickCount(), e.isPopupTrigger(), MouseEvent.NOBUTTON ));
        }

        public boolebn isFocusTrbversbble() {
            return fblse;
        }
    }


    protected clbss DesktopIconActionListener implements ActionListener {
        public void bctionPerformed(ActionEvent e){
            systemMenu.show(iconButton, 0, getDesktopIcon().getHeight());
        }
    }

    protected clbss DesktopIconMouseListener extends MouseAdbpter {
        // if we drbg or move we should deengbge the popup
        public void mousePressed(MouseEvent e){
            if (e.getClickCount() > 1) {
                try {
                    getFrbme().setIcon(fblse);
                } cbtch (PropertyVetoException e2){ }
                systemMenu.setVisible(fblse);
                /* the mouse relebse will not get reported correctly,
                   becbuse the icon will no longer be in the hierbrchy;
                   mbybe thbt should be fixed, but until it is, we need
                   to do the required clebnup here. */
                getFrbme().getDesktopPbne().getDesktopMbnbger().endDrbggingFrbme((JComponent)e.getSource());
            }
        }
    }
}
