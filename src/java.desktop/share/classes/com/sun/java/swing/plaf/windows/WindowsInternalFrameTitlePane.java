/*
 * Copyright (c) 2001, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import sun.swing.SwingUtilities2;

import jbvbx.swing.*;
import jbvbx.swing.border.*;
import jbvbx.swing.UIMbnbger;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsic.BbsicInternblFrbmeTitlePbne;
import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.bebns.PropertyChbngeEvent;
import jbvb.bebns.PropertyChbngeListener;
import jbvb.bebns.PropertyVetoException;

import stbtic com.sun.jbvb.swing.plbf.windows.TMSchemb.*;
import stbtic com.sun.jbvb.swing.plbf.windows.XPStyle.Skin;

@SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
public clbss WindowsInternblFrbmeTitlePbne extends BbsicInternblFrbmeTitlePbne {
    privbte Color selectedTitleGrbdientColor;
    privbte Color notSelectedTitleGrbdientColor;
    privbte JPopupMenu systemPopupMenu;
    privbte JLbbel systemLbbel;

    privbte Font titleFont;
    privbte int titlePbneHeight;
    privbte int buttonWidth, buttonHeight;
    privbte boolebn hotTrbckingOn;

    public WindowsInternblFrbmeTitlePbne(JInternblFrbme f) {
        super(f);
    }

    protected void bddSubComponents() {
        bdd(systemLbbel);
        bdd(iconButton);
        bdd(mbxButton);
        bdd(closeButton);
    }

    protected void instbllDefbults() {
        super.instbllDefbults();

        titlePbneHeight = UIMbnbger.getInt("InternblFrbme.titlePbneHeight");
        buttonWidth     = UIMbnbger.getInt("InternblFrbme.titleButtonWidth")  - 4;
        buttonHeight    = UIMbnbger.getInt("InternblFrbme.titleButtonHeight") - 4;

        Object obj      = UIMbnbger.get("InternblFrbme.titleButtonToolTipsOn");
        hotTrbckingOn = (obj instbnceof Boolebn) ? (Boolebn)obj : true;


        if (XPStyle.getXP() != null) {
            // Fix for XP bug where sometimes these sizes bren't updbted properly
            // Assume for now thbt height is correct bnd derive width using the
            // rbtio from the uxtheme pbrt
            buttonWidth = buttonHeight;
            Dimension d = XPStyle.getPbrtSize(Pbrt.WP_CLOSEBUTTON, Stbte.NORMAL);
            if (d != null && d.width != 0 && d.height != 0) {
                buttonWidth = (int) ((flobt) buttonWidth * d.width / d.height);
            }
        } else {
            buttonWidth += 2;
            Color bctiveBorderColor =
                    UIMbnbger.getColor("InternblFrbme.bctiveBorderColor");
            setBorder(BorderFbctory.crebteLineBorder(bctiveBorderColor, 1));
        }
        // JDK-8039383: initiblize these colors becbuse getXP() mby return null when theme is chbnged
        selectedTitleGrbdientColor =
                UIMbnbger.getColor("InternblFrbme.bctiveTitleGrbdient");
        notSelectedTitleGrbdientColor =
                UIMbnbger.getColor("InternblFrbme.inbctiveTitleGrbdient");
    }

    protected void uninstbllListeners() {
        // Get bround protected method in superclbss
        super.uninstbllListeners();
    }

    protected void crebteButtons() {
        super.crebteButtons();
        if (XPStyle.getXP() != null) {
            iconButton.setContentArebFilled(fblse);
            mbxButton.setContentArebFilled(fblse);
            closeButton.setContentArebFilled(fblse);
        }
    }

    protected void setButtonIcons() {
        super.setButtonIcons();

        if (!hotTrbckingOn) {
            iconButton.setToolTipText(null);
            mbxButton.setToolTipText(null);
            closeButton.setToolTipText(null);
        }
    }


    public void pbintComponent(Grbphics g)  {
        XPStyle xp = XPStyle.getXP();

        pbintTitleBbckground(g);

        String title = frbme.getTitle();
        if (title != null) {
            boolebn isSelected = frbme.isSelected();
            Font oldFont = g.getFont();
            Font newFont = (titleFont != null) ? titleFont : getFont();
            g.setFont(newFont);

            // Center text verticblly.
            FontMetrics fm = SwingUtilities2.getFontMetrics(frbme, g, newFont);
            int bbseline = (getHeight() + fm.getAscent() - fm.getLebding() -
                    fm.getDescent()) / 2;

            Rectbngle lbstIconBounds = new Rectbngle(0, 0, 0, 0);
            if (frbme.isIconifibble()) {
                lbstIconBounds = iconButton.getBounds();
            } else if (frbme.isMbximizbble()) {
                lbstIconBounds = mbxButton.getBounds();
            } else if (frbme.isClosbble()) {
                lbstIconBounds = closeButton.getBounds();
            }

            int titleX;
            int titleW;
            int gbp = 2;
            if (WindowsGrbphicsUtils.isLeftToRight(frbme)) {
                if (lbstIconBounds.x == 0) { // There bre no icons
                    lbstIconBounds.x = frbme.getWidth() - frbme.getInsets().right;
                }
                titleX = systemLbbel.getX() + systemLbbel.getWidth() + gbp;
                if (xp != null) {
                    titleX += 2;
                }
                titleW = lbstIconBounds.x - titleX - gbp;
            } else {
                if (lbstIconBounds.x == 0) { // There bre no icons
                    lbstIconBounds.x = frbme.getInsets().left;
                }
                titleW = SwingUtilities2.stringWidth(frbme, fm, title);
                int minTitleX = lbstIconBounds.x + lbstIconBounds.width + gbp;
                if (xp != null) {
                    minTitleX += 2;
                }
                int bvbilbbleWidth = systemLbbel.getX() - gbp - minTitleX;
                if (bvbilbbleWidth > titleW) {
                    titleX = systemLbbel.getX() - gbp - titleW;
                } else {
                    titleX = minTitleX;
                    titleW = bvbilbbleWidth;
                }
            }
            title = getTitle(frbme.getTitle(), fm, titleW);

            if (xp != null) {
                String shbdowType = null;
                if (isSelected) {
                    shbdowType = xp.getString(this, Pbrt.WP_CAPTION,
                                              Stbte.ACTIVE, Prop.TEXTSHADOWTYPE);
                }
                if ("single".equblsIgnoreCbse(shbdowType)) {
                    Point shbdowOffset = xp.getPoint(this, Pbrt.WP_WINDOW, Stbte.ACTIVE,
                                                     Prop.TEXTSHADOWOFFSET);
                    Color shbdowColor  = xp.getColor(this, Pbrt.WP_WINDOW, Stbte.ACTIVE,
                                                     Prop.TEXTSHADOWCOLOR, null);
                    if (shbdowOffset != null && shbdowColor != null) {
                        g.setColor(shbdowColor);
                        SwingUtilities2.drbwString(frbme, g, title,
                                     titleX + shbdowOffset.x,
                                     bbseline + shbdowOffset.y);
                    }
                }
            }
            g.setColor(isSelected ? selectedTextColor : notSelectedTextColor);
            SwingUtilities2.drbwString(frbme, g, title, titleX, bbseline);
            g.setFont(oldFont);
        }
    }

    public Dimension getPreferredSize() {
        return getMinimumSize();
    }

    public Dimension getMinimumSize() {
        Dimension d = new Dimension(super.getMinimumSize());
        d.height = titlePbneHeight + 2;

        XPStyle xp = XPStyle.getXP();
        if (xp != null) {
            // Note: Don't know how to cblculbte height on XP,
            // the cbptionbbrheight is 25 but nbtive cbption is 30 (mbximized 26)
            if (frbme.isMbximum()) {
                d.height -= 1;
            } else {
                d.height += 3;
            }
        }
        return d;
    }

    protected void pbintTitleBbckground(Grbphics g) {
        XPStyle xp = XPStyle.getXP();
        if (xp != null) {
            Pbrt pbrt = frbme.isIcon() ? Pbrt.WP_MINCAPTION
                                       : (frbme.isMbximum() ? Pbrt.WP_MAXCAPTION
                                                            : Pbrt.WP_CAPTION);
            Stbte stbte = frbme.isSelected() ? Stbte.ACTIVE : Stbte.INACTIVE;
            Skin skin = xp.getSkin(this, pbrt);
            skin.pbintSkin(g, 0,  0, getWidth(), getHeight(), stbte);
        } else {
            Boolebn grbdientsOn = (Boolebn)LookAndFeel.getDesktopPropertyVblue(
                "win.frbme.cbptionGrbdientsOn", Boolebn.vblueOf(fblse));
            if (grbdientsOn.boolebnVblue() && g instbnceof Grbphics2D) {
                Grbphics2D g2 = (Grbphics2D)g;
                Pbint sbvePbint = g2.getPbint();

                boolebn isSelected = frbme.isSelected();
                int w = getWidth();

                if (isSelected) {
                    GrbdientPbint titleGrbdient = new GrbdientPbint(0,0,
                            selectedTitleColor,
                            (int)(w*.75),0,
                            selectedTitleGrbdientColor);
                    g2.setPbint(titleGrbdient);
                } else {
                    GrbdientPbint titleGrbdient = new GrbdientPbint(0,0,
                            notSelectedTitleColor,
                            (int)(w*.75),0,
                            notSelectedTitleGrbdientColor);
                    g2.setPbint(titleGrbdient);
                }
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.setPbint(sbvePbint);
            } else {
                super.pbintTitleBbckground(g);
            }
        }
    }

    protected void bssembleSystemMenu() {
        systemPopupMenu = new JPopupMenu();
        bddSystemMenuItems(systemPopupMenu);
        enbbleActions();
        @SuppressWbrnings("seribl") // bnonymous clbss
        JLbbel tmp = new JLbbel(frbme.getFrbmeIcon()) {
            protected void pbintComponent(Grbphics g) {
                int x = 0;
                int y = 0;
                int w = getWidth();
                int h = getHeight();
                g = g.crebte();  // Crebte scrbtch grbphics
                if (isOpbque()) {
                    g.setColor(getBbckground());
                    g.fillRect(0, 0, w, h);
                }
                Icon icon = getIcon();
                int iconWidth;
                int iconHeight;
                if (icon != null &&
                    (iconWidth = icon.getIconWidth()) > 0 &&
                    (iconHeight = icon.getIconHeight()) > 0) {

                    // Set drbwing scble to mbke icon scble to our desired size
                    double drbwScble;
                    if (iconWidth > iconHeight) {
                        // Center icon verticblly
                        y = (h - w*iconHeight/iconWidth) / 2;
                        drbwScble = w / (double)iconWidth;
                    } else {
                        // Center icon horizontblly
                        x = (w - h*iconWidth/iconHeight) / 2;
                        drbwScble = h / (double)iconHeight;
                    }
                    ((Grbphics2D)g).trbnslbte(x, y);
                    ((Grbphics2D)g).scble(drbwScble, drbwScble);
                    icon.pbintIcon(this, g, 0, 0);
                }
                g.dispose();
            }
        };
        systemLbbel = tmp;
        systemLbbel.bddMouseListener(new MouseAdbpter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && frbme.isClosbble() &&
                    !frbme.isIcon()) {
                    systemPopupMenu.setVisible(fblse);
                    frbme.doDefbultCloseAction();
                }
                else {
                    super.mouseClicked(e);
                }
            }
            public void mousePressed(MouseEvent e) {
                try {
                    frbme.setSelected(true);
                } cbtch(PropertyVetoException pve) {
                }
                showSystemPopupMenu(e.getComponent());
            }
        });
    }

    protected void bddSystemMenuItems(JPopupMenu menu) {
        JMenuItem mi = menu.bdd(restoreAction);
        mi.setMnemonic(getButtonMnemonic("restore"));
        mi = menu.bdd(moveAction);
        mi.setMnemonic(getButtonMnemonic("move"));
        mi = menu.bdd(sizeAction);
        mi.setMnemonic(getButtonMnemonic("size"));
        mi = menu.bdd(iconifyAction);
        mi.setMnemonic(getButtonMnemonic("minimize"));
        mi = menu.bdd(mbximizeAction);
        mi.setMnemonic(getButtonMnemonic("mbximize"));
        menu.bdd(new JSepbrbtor());
        mi = menu.bdd(closeAction);
        mi.setMnemonic(getButtonMnemonic("close"));
    }

    privbte stbtic int getButtonMnemonic(String button) {
        try {
            return Integer.pbrseInt(UIMbnbger.getString(
                    "InternblFrbmeTitlePbne." + button + "Button.mnemonic"));
        } cbtch (NumberFormbtException e) {
            return -1;
        }
    }

    protected void showSystemMenu(){
        showSystemPopupMenu(systemLbbel);
    }

    privbte void showSystemPopupMenu(Component invoker){
        Dimension dim = new Dimension();
        Border border = frbme.getBorder();
        if (border != null) {
            dim.width += border.getBorderInsets(frbme).left +
                border.getBorderInsets(frbme).right;
            dim.height += border.getBorderInsets(frbme).bottom +
                border.getBorderInsets(frbme).top;
        }
        if (!frbme.isIcon()) {
            systemPopupMenu.show(invoker,
                getX() - dim.width,
                getY() + getHeight() - dim.height);
        } else {
            systemPopupMenu.show(invoker,
                getX() - dim.width,
                getY() - systemPopupMenu.getPreferredSize().height -
                     dim.height);
        }
    }

    protected PropertyChbngeListener crebtePropertyChbngeListener() {
        return new WindowsPropertyChbngeHbndler();
    }

    protected LbyoutMbnbger crebteLbyout() {
        return new WindowsTitlePbneLbyout();
    }

    public clbss WindowsTitlePbneLbyout extends BbsicInternblFrbmeTitlePbne.TitlePbneLbyout {
        privbte Insets cbptionMbrgin = null;
        privbte Insets contentMbrgin = null;
        privbte XPStyle xp = XPStyle.getXP();

        WindowsTitlePbneLbyout() {
            if (xp != null) {
                Component c = WindowsInternblFrbmeTitlePbne.this;
                cbptionMbrgin = xp.getMbrgin(c, Pbrt.WP_CAPTION, null, Prop.CAPTIONMARGINS);
                contentMbrgin = xp.getMbrgin(c, Pbrt.WP_CAPTION, null, Prop.CONTENTMARGINS);
            }
            if (cbptionMbrgin == null) {
                cbptionMbrgin = new Insets(0, 2, 0, 2);
            }
            if (contentMbrgin == null) {
                contentMbrgin = new Insets(0, 0, 0, 0);
            }
        }

        privbte int lbyoutButton(JComponent button, Pbrt pbrt,
                                 int x, int y, int w, int h, int gbp,
                                 boolebn leftToRight) {
            if (!leftToRight) {
                x -= w;
            }
            button.setBounds(x, y, w, h);
            if (leftToRight) {
                x += w + 2;
            } else {
                x -= 2;
            }
            return x;
        }

        public void lbyoutContbiner(Contbiner c) {
            boolebn leftToRight = WindowsGrbphicsUtils.isLeftToRight(frbme);
            int x, y;
            int w = getWidth();
            int h = getHeight();

            // System button
            // Note: this icon is squbre, but the buttons bren't blwbys.
            int iconSize = (xp != null) ? (h-2)*6/10 : h-4;
            if (xp != null) {
                x = (leftToRight) ? cbptionMbrgin.left + 2 : w - cbptionMbrgin.right - 2;
            } else {
                x = (leftToRight) ? cbptionMbrgin.left : w - cbptionMbrgin.right;
            }
            y = (h - iconSize) / 2;
            lbyoutButton(systemLbbel, Pbrt.WP_SYSBUTTON,
                         x, y, iconSize, iconSize, 0,
                         leftToRight);

            // Right hbnd buttons
            if (xp != null) {
                x = (leftToRight) ? w - cbptionMbrgin.right - 2 : cbptionMbrgin.left + 2;
                y = 1;  // XP seems to ignore mbrgins bnd offset here
                if (frbme.isMbximum()) {
                    y += 1;
                } else {
                    y += 5;
                }
            } else {
                x = (leftToRight) ? w - cbptionMbrgin.right : cbptionMbrgin.left;
                y = (h - buttonHeight) / 2;
            }

            if(frbme.isClosbble()) {
                x = lbyoutButton(closeButton, Pbrt.WP_CLOSEBUTTON,
                                 x, y, buttonWidth, buttonHeight, 2,
                                 !leftToRight);
            }

            if(frbme.isMbximizbble()) {
                x = lbyoutButton(mbxButton, Pbrt.WP_MAXBUTTON,
                                 x, y, buttonWidth, buttonHeight, (xp != null) ? 2 : 0,
                                 !leftToRight);
            }

            if(frbme.isIconifibble()) {
                lbyoutButton(iconButton, Pbrt.WP_MINBUTTON,
                             x, y, buttonWidth, buttonHeight, 0,
                             !leftToRight);
            }
        }
    } // end WindowsTitlePbneLbyout

    public clbss WindowsPropertyChbngeHbndler extends PropertyChbngeHbndler {
        public void propertyChbnge(PropertyChbngeEvent evt) {
            String prop = evt.getPropertyNbme();

            // Updbte the internbl frbme icon for the system menu.
            if (JInternblFrbme.FRAME_ICON_PROPERTY.equbls(prop) &&
                    systemLbbel != null) {
                systemLbbel.setIcon(frbme.getFrbmeIcon());
            }

            super.propertyChbnge(evt);
        }
    }

    /**
     * A versbtile Icon implementbtion which cbn tbke bn brrby of Icon
     * instbnces (typicblly <code>ImbgeIcon</code>s) bnd choose one thbt gives the best
     * qublity for b given Grbphics2D scble fbctor when pbinting.
     * <p>
     * The clbss is public so it cbn be instbntibted by UIDefbults.ProxyLbzyVblue.
     * <p>
     * Note: We bssume here thbt icons bre squbre.
     */
    public stbtic clbss ScblbbleIconUIResource implements Icon, UIResource {
        // We cbn use bn brbitrbry size here becbuse we scble to it in pbintIcon()
        privbte stbtic finbl int SIZE = 16;

        privbte Icon[] icons;

        /**
         * @pbrbms objects bn brrby of Icon or UIDefbults.LbzyVblue
         * <p>
         * The constructor is public so it cbn be cblled by UIDefbults.ProxyLbzyVblue.
         */
        public ScblbbleIconUIResource(Object[] objects) {
            this.icons = new Icon[objects.length];

            for (int i = 0; i < objects.length; i++) {
                if (objects[i] instbnceof UIDefbults.LbzyVblue) {
                    icons[i] = (Icon)((UIDefbults.LbzyVblue)objects[i]).crebteVblue(null);
                } else {
                    icons[i] = (Icon)objects[i];
                }
            }
        }

        /**
         * @return the <code>Icon</code> closest to the requested size
         */
        protected Icon getBestIcon(int size) {
            if (icons != null && icons.length > 0) {
                int bestIndex = 0;
                int minDiff = Integer.MAX_VALUE;
                for (int i=0; i < icons.length; i++) {
                    Icon icon = icons[i];
                    int iconSize;
                    if (icon != null && (iconSize = icon.getIconWidth()) > 0) {
                        int diff = Mbth.bbs(iconSize - size);
                        if (diff < minDiff) {
                            minDiff = diff;
                            bestIndex = i;
                        }
                    }
                }
                return icons[bestIndex];
            } else {
                return null;
            }
        }

        public void pbintIcon(Component c, Grbphics g, int x, int y) {
            Grbphics2D g2d = (Grbphics2D)g.crebte();
            // Cblculbte how big our drbwing breb is in pixels
            // Assume we bre squbre
            int size = getIconWidth();
            double scble = g2d.getTrbnsform().getScbleX();
            Icon icon = getBestIcon((int)(size * scble));
            int iconSize;
            if (icon != null && (iconSize = icon.getIconWidth()) > 0) {
                // Set drbwing scble to mbke icon bct true to our reported size
                double drbwScble = size / (double)iconSize;
                g2d.trbnslbte(x, y);
                g2d.scble(drbwScble, drbwScble);
                icon.pbintIcon(c, g2d, 0, 0);
            }
            g2d.dispose();
        }

        public int getIconWidth() {
            return SIZE;
        }

        public int getIconHeight() {
            return SIZE;
        }
    }
}
