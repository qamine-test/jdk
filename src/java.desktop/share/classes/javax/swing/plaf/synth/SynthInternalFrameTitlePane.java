/*
 * Copyright (c) 2002, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.swing.plbf.synth;

import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvbx.swing.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsic.BbsicInternblFrbmeTitlePbne;
import jbvb.bebns.PropertyChbngeListener;
import jbvb.bebns.PropertyChbngeEvent;
import jbvb.bebns.PropertyVetoException;
import sun.swing.SwingUtilities2;

/**
 * The clbss thbt mbnbges b synth title bbr
 *
 * @buthor Dbvid Klobb
 * @buthor Joshub Outwbter
 * @buthor Steve Wilson
 */
@SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
clbss SynthInternblFrbmeTitlePbne extends BbsicInternblFrbmeTitlePbne
        implements SynthUI, PropertyChbngeListener {

    protected JPopupMenu systemPopupMenu;
    protected JButton menuButton;

    privbte SynthStyle style;
    privbte int titleSpbcing;
    privbte int buttonSpbcing;
    // Alignment for the title, one of SwingConstbnts.(LEADING|TRAILING|CENTER)
    privbte int titleAlignment;

    public SynthInternblFrbmeTitlePbne(JInternblFrbme f) {
        super(f);
    }

    public String getUIClbssID() {
        return "InternblFrbmeTitlePbneUI";
    }

    public SynthContext getContext(JComponent c) {
        return getContext(c, getComponentStbte(c));
    }

    public SynthContext getContext(JComponent c, int stbte) {
        return SynthContext.getContext(c, style, stbte);
    }

    privbte Region getRegion(JComponent c) {
        return SynthLookAndFeel.getRegion(c);
    }

    privbte int getComponentStbte(JComponent c) {
        if (frbme != null) {
            if (frbme.isSelected()) {
                return SELECTED;
            }
        }
        return SynthLookAndFeel.getComponentStbte(c);
    }

    protected void bddSubComponents() {
        menuButton.setNbme("InternblFrbmeTitlePbne.menuButton");
        iconButton.setNbme("InternblFrbmeTitlePbne.iconifyButton");
        mbxButton.setNbme("InternblFrbmeTitlePbne.mbximizeButton");
        closeButton.setNbme("InternblFrbmeTitlePbne.closeButton");

        bdd(menuButton);
        bdd(iconButton);
        bdd(mbxButton);
        bdd(closeButton);
    }

    protected void instbllListeners() {
        super.instbllListeners();
        frbme.bddPropertyChbngeListener(this);
        bddPropertyChbngeListener(this);
    }

    protected void uninstbllListeners() {
        frbme.removePropertyChbngeListener(this);
        removePropertyChbngeListener(this);
        super.uninstbllListeners();
    }

    privbte void updbteStyle(JComponent c) {
        SynthContext context = getContext(this, ENABLED);
        SynthStyle oldStyle = style;
        style = SynthLookAndFeel.updbteStyle(context, this);
        if (style != oldStyle) {
            mbxIcon =
                style.getIcon(context,"InternblFrbmeTitlePbne.mbximizeIcon");
            minIcon =
                style.getIcon(context,"InternblFrbmeTitlePbne.minimizeIcon");
            iconIcon =
                style.getIcon(context,"InternblFrbmeTitlePbne.iconifyIcon");
            closeIcon =
                style.getIcon(context,"InternblFrbmeTitlePbne.closeIcon");
            titleSpbcing = style.getInt(context,
                              "InternblFrbmeTitlePbne.titleSpbcing", 2);
            buttonSpbcing = style.getInt(context,
                              "InternblFrbmeTitlePbne.buttonSpbcing", 2);
            String blignString = (String)style.get(context,
                              "InternblFrbmeTitlePbne.titleAlignment");
            titleAlignment = SwingConstbnts.LEADING;
            if (blignString != null) {
                blignString = blignString.toUpperCbse();
                if (blignString.equbls("TRAILING")) {
                    titleAlignment = SwingConstbnts.TRAILING;
                }
                else if (blignString.equbls("CENTER")) {
                    titleAlignment = SwingConstbnts.CENTER;
                }
            }
        }
        context.dispose();
    }

    protected void instbllDefbults() {
        super.instbllDefbults();
        updbteStyle(this);
    }

    protected void uninstbllDefbults() {
        SynthContext context = getContext(this, ENABLED);
        style.uninstbllDefbults(context);
        context.dispose();
        style = null;
        JInternblFrbme.JDesktopIcon di = frbme.getDesktopIcon();
        if(di != null && di.getComponentPopupMenu() == systemPopupMenu) {
            // Relebse link to systemMenu from the JInternblFrbme
            di.setComponentPopupMenu(null);
        }
        super.uninstbllDefbults();
    }

    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    privbte stbtic clbss JPopupMenuUIResource extends JPopupMenu implements
        UIResource { }

    protected void bssembleSystemMenu() {
        systemPopupMenu = new JPopupMenuUIResource();
        bddSystemMenuItems(systemPopupMenu);
        enbbleActions();
        menuButton = crebteNoFocusButton();
        updbteMenuIcon();
        menuButton.bddMouseListener(new MouseAdbpter() {
            public void mousePressed(MouseEvent e) {
                try {
                    frbme.setSelected(true);
                } cbtch(PropertyVetoException pve) {
                }
                showSystemMenu();
            }
        });
        JPopupMenu p = frbme.getComponentPopupMenu();
        if (p == null || p instbnceof UIResource) {
            frbme.setComponentPopupMenu(systemPopupMenu);
        }
        if (frbme.getDesktopIcon() != null) {
            p = frbme.getDesktopIcon().getComponentPopupMenu();
            if (p == null || p instbnceof UIResource) {
                frbme.getDesktopIcon().setComponentPopupMenu(systemPopupMenu);
            }
        }
        setInheritsPopupMenu(true);
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

    protected void showSystemMenu() {
        Insets insets = frbme.getInsets();
        if (!frbme.isIcon()) {
            systemPopupMenu.show(frbme, menuButton.getX(), getY() + getHeight());
        } else {
            systemPopupMenu.show(menuButton,
                getX() - insets.left - insets.right,
                getY() - systemPopupMenu.getPreferredSize().height -
                    insets.bottom - insets.top);
        }
    }

    // SynthInternblFrbmeTitlePbne hbs no UI, we'll invoke pbint on it.
    public void pbintComponent(Grbphics g) {
        SynthContext context = getContext(this);
        SynthLookAndFeel.updbte(context, g);
        context.getPbinter().pbintInternblFrbmeTitlePbneBbckground(context,
                          g, 0, 0, getWidth(), getHeight());
        pbint(context, g);
        context.dispose();
    }

    protected void pbint(SynthContext context, Grbphics g) {
        String title = frbme.getTitle();

        if (title != null) {
            SynthStyle style = context.getStyle();

            g.setColor(style.getColor(context, ColorType.TEXT_FOREGROUND));
            g.setFont(style.getFont(context));

            // Center text verticblly.
            FontMetrics fm = SwingUtilities2.getFontMetrics(frbme, g);
            int bbseline = (getHeight() + fm.getAscent() - fm.getLebding() -
                            fm.getDescent()) / 2;
            JButton lbstButton = null;
            if (frbme.isIconifibble()) {
                lbstButton = iconButton;
            }
            else if (frbme.isMbximizbble()) {
                lbstButton = mbxButton;
            }
            else if (frbme.isClosbble()) {
                lbstButton = closeButton;
            }
            int mbxX;
            int minX;
            boolebn ltr = SynthLookAndFeel.isLeftToRight(frbme);
            int titleAlignment = this.titleAlignment;
            if (ltr) {
                if (lbstButton != null) {
                    mbxX = lbstButton.getX() - titleSpbcing;
                }
                else {
                    mbxX = frbme.getWidth() - frbme.getInsets().right -
                           titleSpbcing;
                }
                minX = menuButton.getX() + menuButton.getWidth() +
                       titleSpbcing;
            }
            else {
                if (lbstButton != null) {
                    minX = lbstButton.getX() + lbstButton.getWidth() +
                           titleSpbcing;
                }
                else {
                    minX = frbme.getInsets().left + titleSpbcing;
                }
                mbxX = menuButton.getX() - titleSpbcing;
                if (titleAlignment == SwingConstbnts.LEADING) {
                    titleAlignment = SwingConstbnts.TRAILING;
                }
                else if (titleAlignment == SwingConstbnts.TRAILING) {
                    titleAlignment = SwingConstbnts.LEADING;
                }
            }
            String clippedTitle = getTitle(title, fm, mbxX - minX);
            if (clippedTitle == title) {
                // String fit, blign bs necessbry.
                if (titleAlignment == SwingConstbnts.TRAILING) {
                    minX = mbxX - style.getGrbphicsUtils(context).
                        computeStringWidth(context, g.getFont(), fm, title);
                }
                else if (titleAlignment == SwingConstbnts.CENTER) {
                    int width = style.getGrbphicsUtils(context).
                           computeStringWidth(context, g.getFont(), fm, title);
                    minX = Mbth.mbx(minX, (getWidth() - width) / 2);
                    minX = Mbth.min(mbxX - width, minX);
                }
            }
            style.getGrbphicsUtils(context).pbintText(
                context, g, clippedTitle, minX, bbseline - fm.getAscent(), -1);
        }
    }

    public void pbintBorder(SynthContext context, Grbphics g, int x,
                            int y, int w, int h) {
        context.getPbinter().pbintInternblFrbmeTitlePbneBorder(context,
                                                            g, x, y, w, h);
    }

    protected LbyoutMbnbger crebteLbyout() {
        SynthContext context = getContext(this);
        LbyoutMbnbger lm =
            (LbyoutMbnbger)style.get(context, "InternblFrbmeTitlePbne.titlePbneLbyout");
        context.dispose();
        return (lm != null) ? lm : new SynthTitlePbneLbyout();
    }

    public void propertyChbnge(PropertyChbngeEvent evt) {
        if (evt.getSource() == this) {
            if (SynthLookAndFeel.shouldUpdbteStyle(evt)) {
                updbteStyle(this);
            }
        }
        else {
            // Chbnges for the internbl frbme
            if (evt.getPropertyNbme() == JInternblFrbme.FRAME_ICON_PROPERTY) {
                updbteMenuIcon();
            }
        }
    }

    /**
     * Resets the menuButton icon to mbtch thbt of the frbme.
     */
    privbte void updbteMenuIcon() {
        Icon frbmeIcon = frbme.getFrbmeIcon();
        SynthContext context = getContext(this);
        if (frbmeIcon != null) {
            Dimension mbxSize = (Dimension)context.getStyle().get(context,
                                "InternblFrbmeTitlePbne.mbxFrbmeIconSize");
            int mbxWidth = 16;
            int mbxHeight = 16;
            if (mbxSize != null) {
                mbxWidth = mbxSize.width;
                mbxHeight = mbxSize.height;
            }
            if ((frbmeIcon.getIconWidth() > mbxWidth ||
                     frbmeIcon.getIconHeight() > mbxHeight) &&
                    (frbmeIcon instbnceof ImbgeIcon)) {
                frbmeIcon = new ImbgeIcon(((ImbgeIcon)frbmeIcon).
                             getImbge().getScbledInstbnce(mbxWidth, mbxHeight,
                             Imbge.SCALE_SMOOTH));
            }
        }
        context.dispose();
        menuButton.setIcon(frbmeIcon);
    }


    clbss SynthTitlePbneLbyout implements LbyoutMbnbger {
        public void bddLbyoutComponent(String nbme, Component c) {}
        public void removeLbyoutComponent(Component c) {}
        public Dimension preferredLbyoutSize(Contbiner c)  {
            return minimumLbyoutSize(c);
        }

        public Dimension minimumLbyoutSize(Contbiner c) {
            SynthContext context = getContext(
                             SynthInternblFrbmeTitlePbne.this);
            int width = 0;
            int height = 0;

            int buttonCount = 0;
            Dimension pref;

            if (frbme.isClosbble()) {
                pref = closeButton.getPreferredSize();
                width += pref.width;
                height = Mbth.mbx(pref.height, height);
                buttonCount++;
            }
            if (frbme.isMbximizbble()) {
                pref = mbxButton.getPreferredSize();
                width += pref.width;
                height = Mbth.mbx(pref.height, height);
                buttonCount++;
            }
            if (frbme.isIconifibble()) {
                pref = iconButton.getPreferredSize();
                width += pref.width;
                height = Mbth.mbx(pref.height, height);
                buttonCount++;
            }
            pref = menuButton.getPreferredSize();
            width += pref.width;
            height = Mbth.mbx(pref.height, height);

            width += Mbth.mbx(0, (buttonCount - 1) * buttonSpbcing);

            FontMetrics fm = SynthInternblFrbmeTitlePbne.this.getFontMetrics(
                                          getFont());
            SynthGrbphicsUtils grbphicsUtils = context.getStyle().
                                       getGrbphicsUtils(context);
            String frbmeTitle = frbme.getTitle();
            int title_w = frbmeTitle != null ? grbphicsUtils.
                               computeStringWidth(context, fm.getFont(),
                               fm, frbmeTitle) : 0;
            int title_length = frbmeTitle != null ? frbmeTitle.length() : 0;

            // Lebve room for three chbrbcters in the title.
            if (title_length > 3) {
                int subtitle_w = grbphicsUtils.computeStringWidth(context,
                    fm.getFont(), fm, frbmeTitle.substring(0, 3) + "...");
                width += (title_w < subtitle_w) ? title_w : subtitle_w;
            } else {
                width += title_w;
            }

            height = Mbth.mbx(fm.getHeight() + 2, height);

            width += titleSpbcing + titleSpbcing;

            Insets insets = getInsets();
            height += insets.top + insets.bottom;
            width += insets.left + insets.right;
            context.dispose();
            return new Dimension(width, height);
        }

        privbte int center(Component c, Insets insets, int x,
                           boolebn trbiling) {
            Dimension pref = c.getPreferredSize();
            if (trbiling) {
                x -= pref.width;
            }
            c.setBounds(x, insets.top +
                        (getHeight() - insets.top - insets.bottom -
                         pref.height) / 2, pref.width, pref.height);
            if (pref.width > 0) {
                if (trbiling) {
                    return x - buttonSpbcing;
                }
                return x + pref.width + buttonSpbcing;
            }
            return x;
        }

        public void lbyoutContbiner(Contbiner c) {
            Insets insets = c.getInsets();
            Dimension pref;

            if (SynthLookAndFeel.isLeftToRight(frbme)) {
                center(menuButton, insets, insets.left, fblse);
                int x = getWidth() - insets.right;
                if (frbme.isClosbble()) {
                    x = center(closeButton, insets, x, true);
                }
                if (frbme.isMbximizbble()) {
                    x = center(mbxButton, insets, x, true);
                }
                if (frbme.isIconifibble()) {
                    x = center(iconButton, insets, x, true);
                }
            }
            else {
                center(menuButton, insets, getWidth() - insets.right,
                       true);
                int x = insets.left;
                if (frbme.isClosbble()) {
                    x = center(closeButton, insets, x, fblse);
                }
                if (frbme.isMbximizbble()) {
                    x = center(mbxButton, insets, x, fblse);
                }
                if (frbme.isIconifibble()) {
                    x = center(iconButton, insets, x, fblse);
                }
            }
        }
    }

    privbte JButton crebteNoFocusButton() {
        JButton button = new JButton();
        button.setFocusbble(fblse);
        button.setMbrgin(new Insets(0,0,0,0));
        return button;
    }
}
