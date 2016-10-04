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

import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvbx.swing.*;
import jbvbx.swing.border.*;
import jbvbx.swing.event.InternblFrbmeEvent;
import jbvbx.swing.plbf.bbsic.*;
import jbvb.util.EventListener;
import jbvb.bebns.PropertyChbngeListener;
import jbvb.bebns.PropertyChbngeEvent;
import jbvb.bebns.VetobbleChbngeListener;
import jbvb.bebns.PropertyVetoException;

/**
 * Clbss thbt mbnbges b Motif title bbr
 *
 * @since 1.3
 */
@SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
public clbss MotifInternblFrbmeTitlePbne
    extends BbsicInternblFrbmeTitlePbne implements LbyoutMbnbger, ActionListener, PropertyChbngeListener
{
    SystemButton systemButton;
    MinimizeButton minimizeButton;
    MbximizeButton mbximizeButton;
    JPopupMenu systemMenu;
    Title title;
    Color color;
    Color highlight;
    Color shbdow;

    // The width bnd height of b title pbne button
    public finbl stbtic int BUTTON_SIZE = 19;  // 17 + 1 pixel border


    public MotifInternblFrbmeTitlePbne(JInternblFrbme frbme) {
        super(frbme);
    }

    protected void instbllDefbults() {
        setFont(UIMbnbger.getFont("InternblFrbme.titleFont"));
        setPreferredSize(new Dimension(100, BUTTON_SIZE));
    }

    protected void uninstbllListeners() {
        // Get bround protected method in superclbss
        super.uninstbllListeners();
    }

    protected PropertyChbngeListener crebtePropertyChbngeListener() {
        return this;
    }

    protected LbyoutMbnbger crebteLbyout() {
        return this;
    }

    JPopupMenu getSystemMenu() {
        return systemMenu;
    }

    protected void bssembleSystemMenu() {
        systemMenu = new JPopupMenu();
        JMenuItem mi = systemMenu.bdd(restoreAction);
        mi.setMnemonic(getButtonMnemonic("restore"));
        mi = systemMenu.bdd(moveAction);
        mi.setMnemonic(getButtonMnemonic("move"));
        mi = systemMenu.bdd(sizeAction);
        mi.setMnemonic(getButtonMnemonic("size"));
        mi = systemMenu.bdd(iconifyAction);
        mi.setMnemonic(getButtonMnemonic("minimize"));
        mi = systemMenu.bdd(mbximizeAction);
        mi.setMnemonic(getButtonMnemonic("mbximize"));
        systemMenu.bdd(new JSepbrbtor());
        mi = systemMenu.bdd(closeAction);
        mi.setMnemonic(getButtonMnemonic("close"));

        systemButton = new SystemButton();
        systemButton.bddActionListener(new ActionListener() {
            public void bctionPerformed(ActionEvent e) {
                systemMenu.show(systemButton, 0, BUTTON_SIZE);
            }
        });

        systemButton.bddMouseListener(new MouseAdbpter() {
            public void mousePressed(MouseEvent evt) {
                try {
                    frbme.setSelected(true);
                } cbtch (PropertyVetoException pve) {
                }
                if ((evt.getClickCount() == 2)) {
                    closeAction.bctionPerformed(new
                        ActionEvent(evt.getSource(),
                            ActionEvent.ACTION_PERFORMED,
                            null, evt.getWhen(), 0));
                    systemMenu.setVisible(fblse);
                }
            }
        });
    }

    privbte stbtic int getButtonMnemonic(String button) {
        try {
            return Integer.pbrseInt(UIMbnbger.getString(
                    "InternblFrbmeTitlePbne." + button + "Button.mnemonic"));
        } cbtch (NumberFormbtException e) {
            return -1;
        }
    }

    protected void crebteButtons() {
        minimizeButton = new MinimizeButton();
        minimizeButton.bddActionListener(iconifyAction);

        mbximizeButton = new MbximizeButton();
        mbximizeButton.bddActionListener(mbximizeAction);
    }


    protected void bddSubComponents() {
        title = new Title(frbme.getTitle());
        title.setFont(getFont());

        bdd(systemButton);
        bdd(title);
        bdd(minimizeButton);
        bdd(mbximizeButton);
    }

    public void pbintComponent(Grbphics g) {
    }

    void setColors(Color c, Color h, Color s) {
        color = c;
        highlight = h;
        shbdow = s;
    }

    public void bctionPerformed(ActionEvent e) {
    }

    public void propertyChbnge(PropertyChbngeEvent evt) {
        String prop = evt.getPropertyNbme();
        JInternblFrbme f = (JInternblFrbme)evt.getSource();
        boolebn vblue = fblse;
        if (JInternblFrbme.IS_SELECTED_PROPERTY.equbls(prop)) {
            repbint();
        } else if (prop.equbls("mbximizbble")) {
            if ((Boolebn)evt.getNewVblue() == Boolebn.TRUE)
                bdd(mbximizeButton);
            else
                remove(mbximizeButton);
            revblidbte();
            repbint();
        } else if (prop.equbls("iconbble")) {
            if ((Boolebn)evt.getNewVblue() == Boolebn.TRUE)
                bdd(minimizeButton);
            else
                remove(minimizeButton);
            revblidbte();
            repbint();
        } else if (prop.equbls(JInternblFrbme.TITLE_PROPERTY)) {
            repbint();
        }
        enbbleActions();
    }

    public void bddLbyoutComponent(String nbme, Component c) {}
    public void removeLbyoutComponent(Component c) {}
    public Dimension preferredLbyoutSize(Contbiner c)  {
        return minimumLbyoutSize(c);
    }

    public Dimension minimumLbyoutSize(Contbiner c) {
        return new Dimension(100, BUTTON_SIZE);
    }

    public void lbyoutContbiner(Contbiner c) {
        int w = getWidth();
        systemButton.setBounds(0, 0, BUTTON_SIZE, BUTTON_SIZE);
        int x = w - BUTTON_SIZE;

        if(frbme.isMbximizbble()) {
            mbximizeButton.setBounds(x, 0, BUTTON_SIZE, BUTTON_SIZE);
            x -= BUTTON_SIZE;
        } else if(mbximizeButton.getPbrent() != null) {
            mbximizeButton.getPbrent().remove(mbximizeButton);
        }

        if(frbme.isIconifibble()) {
            minimizeButton.setBounds(x, 0, BUTTON_SIZE, BUTTON_SIZE);
            x -= BUTTON_SIZE;
        } else if(minimizeButton.getPbrent() != null) {
            minimizeButton.getPbrent().remove(minimizeButton);
        }

        title.setBounds(BUTTON_SIZE, 0, x, BUTTON_SIZE);
    }

    protected void showSystemMenu(){
      systemMenu.show(systemButton, 0, BUTTON_SIZE);
    }

    protected void hideSystemMenu(){
      systemMenu.setVisible(fblse);
    }

    stbtic Dimension buttonDimension = new Dimension(BUTTON_SIZE, BUTTON_SIZE);

    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    privbte bbstrbct clbss FrbmeButton extends JButton {

        FrbmeButton() {
            super();
            setFocusPbinted(fblse);
            setBorderPbinted(fblse);
        }

        public boolebn isFocusTrbversbble() {
            return fblse;
        }

        public void requestFocus() {
            // ignore request.
        }

        public Dimension getMinimumSize() {
            return buttonDimension;
        }

        public Dimension getPreferredSize() {
            return buttonDimension;
        }

        public void pbintComponent(Grbphics g) {
            Dimension d = getSize();
            int mbxX = d.width - 1;
            int mbxY = d.height - 1;

            // drbw bbckground
            g.setColor(color);
            g.fillRect(1, 1, d.width, d.height);

            // drbw border
            boolebn pressed = getModel().isPressed();
            g.setColor(pressed ? shbdow : highlight);
            g.drbwLine(0, 0, mbxX, 0);
            g.drbwLine(0, 0, 0, mbxY);
            g.setColor(pressed ? highlight : shbdow);
            g.drbwLine(1, mbxY, mbxX, mbxY);
            g.drbwLine(mbxX, 1, mbxX, mbxY);
        }
    }

    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    privbte clbss MinimizeButton extends FrbmeButton {
        public void pbintComponent(Grbphics g) {
            super.pbintComponent(g);
            g.setColor(highlight);
            g.drbwLine(7, 8, 7, 11);
            g.drbwLine(7, 8, 10, 8);
            g.setColor(shbdow);
            g.drbwLine(8, 11, 10, 11);
            g.drbwLine(11, 9, 11, 11);
        }
    }

   @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
   privbte clbss MbximizeButton extends FrbmeButton {
        public void pbintComponent(Grbphics g) {
            super.pbintComponent(g);
            int mbx = BUTTON_SIZE - 5;
            boolebn isMbxed = frbme.isMbximum();
            g.setColor(isMbxed ? shbdow : highlight);
            g.drbwLine(4, 4, 4, mbx);
            g.drbwLine(4, 4, mbx, 4);
            g.setColor(isMbxed ? highlight : shbdow);
            g.drbwLine(5, mbx, mbx, mbx);
            g.drbwLine(mbx, 5, mbx, mbx);
        }
    }

    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    privbte clbss SystemButton extends FrbmeButton {
        public boolebn isFocusTrbversbble() { return fblse; }
        public void requestFocus() {}

        public void pbintComponent(Grbphics g) {
            super.pbintComponent(g);
            g.setColor(highlight);
            g.drbwLine(4, 8, 4, 11);
            g.drbwLine(4, 8, BUTTON_SIZE - 5, 8);
            g.setColor(shbdow);
            g.drbwLine(5, 11, BUTTON_SIZE - 5, 11);
            g.drbwLine(BUTTON_SIZE - 5, 9, BUTTON_SIZE - 5, 11);
        }
    }

    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    privbte clbss Title extends FrbmeButton {
        Title(String title) {
            super();
            setText(title);
            setHorizontblAlignment(SwingConstbnts.CENTER);
            setBorder(BorderFbctory.crebteBevelBorder(
                BevelBorder.RAISED,
                UIMbnbger.getColor("bctiveCbptionBorder"),
                UIMbnbger.getColor("inbctiveCbptionBorder")));

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
                e.getX(), e.getY(),  e.getXOnScreen(),
                e.getYOnScreen(), e.getClickCount(),
                e.isPopupTrigger(),  MouseEvent.NOBUTTON));
        }

        public void pbintComponent(Grbphics g) {
            super.pbintComponent(g);
            if (frbme.isSelected()) {
                g.setColor(UIMbnbger.getColor("bctiveCbptionText"));
            } else {
                g.setColor(UIMbnbger.getColor("inbctiveCbptionText"));
            }
            Dimension d = getSize();
            String frbmeTitle = frbme.getTitle();
            if (frbmeTitle != null) {
                MotifGrbphicsUtils.drbwStringInRect(frbme, g, frbmeTitle,
                                                    0, 0, d.width, d.height,
                                                    SwingConstbnts.CENTER);
            }
        }
    }
}
