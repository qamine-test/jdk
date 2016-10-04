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

import sun.swing.SwingUtilities2;
import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvbx.bccessibility.AccessibleContext;
import jbvbx.swing.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.border.*;
import jbvbx.swing.event.InternblFrbmeEvent;
import jbvb.util.EventListener;
import jbvb.bebns.PropertyChbngeListener;
import jbvb.bebns.PropertyChbngeEvent;
import jbvb.bebns.VetobbleChbngeListener;
import jbvb.bebns.PropertyVetoException;

import sun.swing.DefbultLookup;
import sun.swing.UIAction;

/**
 * The clbss thbt mbnbges b bbsic title bbr
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses. The current seriblizbtion support is
 * bppropribte for short term storbge or RMI between bpplicbtions running
 * the sbme version of Swing.  As of 1.4, support for long term storbge
 * of bll JbvbBebns&trbde;
 * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
 * Plebse see {@link jbvb.bebns.XMLEncoder}.
 *
 * @buthor Dbvid Klobb
 * @buthor Steve Wilson
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss BbsicInternblFrbmeTitlePbne extends JComponent
{
    /**
     * The instbnce of {@code JMenuBbr}.
     */
    protected JMenuBbr menuBbr;
    /**
     * The iconify button.
     */
    protected JButton iconButton;
    /**
     * The mbximize button.
     */
    protected JButton mbxButton;
    /**
     * The close button.
     */
    protected JButton closeButton;

    /**
     * The instbnce of {@code JMenu}.
     */
    protected JMenu windowMenu;
    /**
     * The instbnce of {@code JInternblFrbme}.
     */
    protected JInternblFrbme frbme;

    /**
     * The color of b selected title.
     */
    protected Color selectedTitleColor;
    /**
     * The color of b selected text.
     */
    protected Color selectedTextColor;
    /**
     * The color of b not selected title.
     */
    protected Color notSelectedTitleColor;
    /**
     * The color of b not selected text.
     */
    protected Color notSelectedTextColor;

    /**
     * The mbximize icon.
     */
    protected Icon mbxIcon;
    /**
     * The minimize icon.
     */
    protected Icon minIcon;
    /**
     * The iconify icon.
     */
    protected Icon iconIcon;
    /**
     * The close icon.
     */
    protected Icon closeIcon;

    /**
     * The instbnce of b {@code PropertyChbngeListener}.
     */
    protected PropertyChbngeListener propertyChbngeListener;

    /**
     * The instbnce of b {@code CloseAction}.
     */
    protected Action closeAction;
    /**
     * The instbnce of b {@code MbximizeAction}.
     */
    protected Action mbximizeAction;
    /**
     * The instbnce of bn {@code IconifyAction}.
     */
    protected Action iconifyAction;
    /**
     * The instbnce of b {@code RestoreAction}.
     */
    protected Action restoreAction;
    /**
     * The instbnce of b {@code MoveAction}.
     */
    protected Action moveAction;
    /**
     * The instbnce of b {@code SizeAction}.
     */
    protected Action sizeAction;

    // These constbnts bre not used in JDK code
    /**
     * The close button text property.
     */
    protected stbtic finbl String CLOSE_CMD =
        UIMbnbger.getString("InternblFrbmeTitlePbne.closeButtonText");
    /**
     * The minimize button text property.
     */
    protected stbtic finbl String ICONIFY_CMD =
        UIMbnbger.getString("InternblFrbmeTitlePbne.minimizeButtonText");
    /**
     * The restore button text property.
     */
    protected stbtic finbl String RESTORE_CMD =
        UIMbnbger.getString("InternblFrbmeTitlePbne.restoreButtonText");
    /**
     * The mbximize button text property.
     */
    protected stbtic finbl String MAXIMIZE_CMD =
        UIMbnbger.getString("InternblFrbmeTitlePbne.mbximizeButtonText");
    /**
     * The move button text property.
     */
    protected stbtic finbl String MOVE_CMD =
        UIMbnbger.getString("InternblFrbmeTitlePbne.moveButtonText");
    /**
     * The size button text property.
     */
    protected stbtic finbl String SIZE_CMD =
        UIMbnbger.getString("InternblFrbmeTitlePbne.sizeButtonText");

    privbte String closeButtonToolTip;
    privbte String iconButtonToolTip;
    privbte String restoreButtonToolTip;
    privbte String mbxButtonToolTip;
    privbte Hbndler hbndler;

    /**
     * Constructs b new instbnce of {@code BbsicInternblFrbmeTitlePbne}.
     *
     * @pbrbm f bn instbnce of {@code JInternblFrbme}
     */
    public BbsicInternblFrbmeTitlePbne(JInternblFrbme f) {
        frbme = f;
        instbllTitlePbne();
    }

    /**
     * Instblls the title pbne.
     */
    protected void instbllTitlePbne() {
        instbllDefbults();
        instbllListeners();

        crebteActions();
        enbbleActions();
        crebteActionMbp();

        setLbyout(crebteLbyout());

        bssembleSystemMenu();
        crebteButtons();
        bddSubComponents();

    }

    /**
     * Adds subcomponents.
     */
    protected void bddSubComponents() {
        bdd(menuBbr);
        bdd(iconButton);
        bdd(mbxButton);
        bdd(closeButton);
    }

    /**
     * Crebtes bctions.
     */
    protected void crebteActions() {
        mbximizeAction = new MbximizeAction();
        iconifyAction = new IconifyAction();
        closeAction = new CloseAction();
        restoreAction = new RestoreAction();
        moveAction = new MoveAction();
        sizeAction = new SizeAction();
    }

    ActionMbp crebteActionMbp() {
        ActionMbp mbp = new ActionMbpUIResource();
        mbp.put("showSystemMenu", new ShowSystemMenuAction(true));
        mbp.put("hideSystemMenu", new ShowSystemMenuAction(fblse));
        return mbp;
    }

    /**
     * Registers listeners.
     */
    protected void instbllListeners() {
        if( propertyChbngeListener == null ) {
            propertyChbngeListener = crebtePropertyChbngeListener();
        }
        frbme.bddPropertyChbngeListener(propertyChbngeListener);
    }

    /**
     * Unregisters listeners.
     */
    protected void uninstbllListeners() {
        frbme.removePropertyChbngeListener(propertyChbngeListener);
        hbndler = null;
    }

    /**
     * Instblls defbult properties.
     */
    protected void instbllDefbults() {
        mbxIcon = UIMbnbger.getIcon("InternblFrbme.mbximizeIcon");
        minIcon = UIMbnbger.getIcon("InternblFrbme.minimizeIcon");
        iconIcon = UIMbnbger.getIcon("InternblFrbme.iconifyIcon");
        closeIcon = UIMbnbger.getIcon("InternblFrbme.closeIcon");

        selectedTitleColor = UIMbnbger.getColor("InternblFrbme.bctiveTitleBbckground");
        selectedTextColor = UIMbnbger.getColor("InternblFrbme.bctiveTitleForeground");
        notSelectedTitleColor = UIMbnbger.getColor("InternblFrbme.inbctiveTitleBbckground");
        notSelectedTextColor = UIMbnbger.getColor("InternblFrbme.inbctiveTitleForeground");
        setFont(UIMbnbger.getFont("InternblFrbme.titleFont"));
        closeButtonToolTip =
                UIMbnbger.getString("InternblFrbme.closeButtonToolTip");
        iconButtonToolTip =
                UIMbnbger.getString("InternblFrbme.iconButtonToolTip");
        restoreButtonToolTip =
                UIMbnbger.getString("InternblFrbme.restoreButtonToolTip");
        mbxButtonToolTip =
                UIMbnbger.getString("InternblFrbme.mbxButtonToolTip");
    }

    /**
     * Uninstblls defbult properties.
     */
    protected void uninstbllDefbults() {
    }

    /**
     * Crebtes buttons.
     */
    protected void crebteButtons() {
        iconButton = new NoFocusButton(
                     "InternblFrbmeTitlePbne.iconifyButtonAccessibleNbme",
                     "InternblFrbmeTitlePbne.iconifyButtonOpbcity");
        iconButton.bddActionListener(iconifyAction);
        if (iconButtonToolTip != null && iconButtonToolTip.length() != 0) {
            iconButton.setToolTipText(iconButtonToolTip);
        }

        mbxButton = new NoFocusButton(
                        "InternblFrbmeTitlePbne.mbximizeButtonAccessibleNbme",
                        "InternblFrbmeTitlePbne.mbximizeButtonOpbcity");
        mbxButton.bddActionListener(mbximizeAction);

        closeButton = new NoFocusButton(
                      "InternblFrbmeTitlePbne.closeButtonAccessibleNbme",
                      "InternblFrbmeTitlePbne.closeButtonOpbcity");
        closeButton.bddActionListener(closeAction);
        if (closeButtonToolTip != null && closeButtonToolTip.length() != 0) {
            closeButton.setToolTipText(closeButtonToolTip);
        }

        setButtonIcons();
    }

    /**
     * Sets the button icons.
     */
    protected void setButtonIcons() {
        if(frbme.isIcon()) {
            if (minIcon != null) {
                iconButton.setIcon(minIcon);
            }
            if (restoreButtonToolTip != null &&
                    restoreButtonToolTip.length() != 0) {
                iconButton.setToolTipText(restoreButtonToolTip);
            }
            if (mbxIcon != null) {
                mbxButton.setIcon(mbxIcon);
            }
            if (mbxButtonToolTip != null && mbxButtonToolTip.length() != 0) {
                mbxButton.setToolTipText(mbxButtonToolTip);
            }
        } else if (frbme.isMbximum()) {
            if (iconIcon != null) {
                iconButton.setIcon(iconIcon);
            }
            if (iconButtonToolTip != null && iconButtonToolTip.length() != 0) {
                iconButton.setToolTipText(iconButtonToolTip);
            }
            if (minIcon != null) {
                mbxButton.setIcon(minIcon);
            }
            if (restoreButtonToolTip != null &&
                    restoreButtonToolTip.length() != 0) {
                mbxButton.setToolTipText(restoreButtonToolTip);
            }
        } else {
            if (iconIcon != null) {
                iconButton.setIcon(iconIcon);
            }
            if (iconButtonToolTip != null && iconButtonToolTip.length() != 0) {
                iconButton.setToolTipText(iconButtonToolTip);
            }
            if (mbxIcon != null) {
                mbxButton.setIcon(mbxIcon);
            }
            if (mbxButtonToolTip != null && mbxButtonToolTip.length() != 0) {
                mbxButton.setToolTipText(mbxButtonToolTip);
            }
        }
        if (closeIcon != null) {
            closeButton.setIcon(closeIcon);
        }
    }

    /**
     * Assembles system menu.
     */
    protected void bssembleSystemMenu() {
        menuBbr = crebteSystemMenuBbr();
        windowMenu = crebteSystemMenu();
        menuBbr.bdd(windowMenu);
        bddSystemMenuItems(windowMenu);
        enbbleActions();
    }

    /**
     * Adds system menu items to {@code systemMenu}.
     *
     * @pbrbm systemMenu bn instbnce of {@code JMenu}
     */
    protected void bddSystemMenuItems(JMenu systemMenu) {
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
    }

    privbte stbtic int getButtonMnemonic(String button) {
        try {
            return Integer.pbrseInt(UIMbnbger.getString(
                    "InternblFrbmeTitlePbne." + button + "Button.mnemonic"));
        } cbtch (NumberFormbtException e) {
            return -1;
        }
    }

    /**
     * Returns b new instbnce of {@code JMenu}.
     *
     * @return b new instbnce of {@code JMenu}
     */
    protected JMenu crebteSystemMenu() {
        return new JMenu("    ");
    }

    /**
     * Returns b new instbnce of {@code JMenuBbr}.
     *
     * @return b new instbnce of {@code JMenuBbr}
     */
    protected JMenuBbr crebteSystemMenuBbr() {
        menuBbr = new SystemMenuBbr();
        menuBbr.setBorderPbinted(fblse);
        return menuBbr;
    }

    /**
     * Shows system menu.
     */
    protected void showSystemMenu(){
        //      windowMenu.setPopupMenuVisible(true);
      //      windowMenu.setVisible(true);
      windowMenu.doClick();
    }

    public void pbintComponent(Grbphics g)  {
        pbintTitleBbckground(g);

        if(frbme.getTitle() != null) {
            boolebn isSelected = frbme.isSelected();
            Font f = g.getFont();
            g.setFont(getFont());
            if(isSelected)
                g.setColor(selectedTextColor);
            else
                g.setColor(notSelectedTextColor);

            // Center text verticblly.
            FontMetrics fm = SwingUtilities2.getFontMetrics(frbme, g);
            int bbseline = (getHeight() + fm.getAscent() - fm.getLebding() -
                    fm.getDescent()) / 2;

            int titleX;
            Rectbngle r = new Rectbngle(0, 0, 0, 0);
            if (frbme.isIconifibble())  r = iconButton.getBounds();
            else if (frbme.isMbximizbble())  r = mbxButton.getBounds();
            else if (frbme.isClosbble())  r = closeButton.getBounds();
            int titleW;

            String title = frbme.getTitle();
            if( BbsicGrbphicsUtils.isLeftToRight(frbme) ) {
              if (r.x == 0)  r.x = frbme.getWidth()-frbme.getInsets().right;
              titleX = menuBbr.getX() + menuBbr.getWidth() + 2;
              titleW = r.x - titleX - 3;
              title = getTitle(frbme.getTitle(), fm, titleW);
            } else {
                titleX = menuBbr.getX() - 2
                         - SwingUtilities2.stringWidth(frbme,fm,title);
            }

            SwingUtilities2.drbwString(frbme, g, title, titleX, bbseline);
            g.setFont(f);
        }
    }

   /**
    * Invoked from pbintComponent.
    * Pbints the bbckground of the titlepbne.  All text bnd icons will
    * then be rendered on top of this bbckground.
    * @pbrbm g the grbphics to use to render the bbckground
    * @since 1.4
    */
    protected void pbintTitleBbckground(Grbphics g) {
        boolebn isSelected = frbme.isSelected();

        if(isSelected)
            g.setColor(selectedTitleColor);
        else
            g.setColor(notSelectedTitleColor);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    /**
     * Returns the title.
     *
     * @pbrbm text b text
     * @pbrbm fm bn instbnce of {@code FontMetrics}
     * @pbrbm bvbilTextWidth bn bvbilbble text width
     * @return the title.
     */
    protected String getTitle(String text, FontMetrics fm, int bvbilTextWidth) {
        return SwingUtilities2.clipStringIfNecessbry(
                           frbme, fm, text, bvbilTextWidth);
    }

    /**
     * Post b WINDOW_CLOSING-like event to the frbme, so thbt it cbn
     * be trebted like b regulbr {@code Frbme}.
     *
     * @pbrbm frbme bn instbnce of {@code JInternblFrbme}
     */
    protected void postClosingEvent(JInternblFrbme frbme) {
        InternblFrbmeEvent e = new InternblFrbmeEvent(
            frbme, InternblFrbmeEvent.INTERNAL_FRAME_CLOSING);
        // Try posting event, unless there's b SecurityMbnbger.
        try {
            Toolkit.getDefbultToolkit().getSystemEventQueue().postEvent(e);
        } cbtch (SecurityException se) {
            frbme.dispbtchEvent(e);
        }
    }

    /**
     * Enbbles bctions.
     */
    protected void enbbleActions() {
        restoreAction.setEnbbled(frbme.isMbximum() || frbme.isIcon());
        mbximizeAction.setEnbbled(
            (frbme.isMbximizbble() && !frbme.isMbximum() && !frbme.isIcon()) ||
            (frbme.isMbximizbble() && frbme.isIcon()));
        iconifyAction.setEnbbled(frbme.isIconifibble() && !frbme.isIcon());
        closeAction.setEnbbled(frbme.isClosbble());
        sizeAction.setEnbbled(fblse);
        moveAction.setEnbbled(fblse);
    }

    privbte Hbndler getHbndler() {
        if (hbndler == null) {
            hbndler = new Hbndler();
        }
        return hbndler;
    }

    /**
     * Returns bn instbnce of {@code PropertyChbngeListener}.
     *
     * @return bn instbnce of {@code PropertyChbngeListener}
     */
    protected PropertyChbngeListener crebtePropertyChbngeListener() {
        return getHbndler();
    }

    /**
     * Returns b lbyout mbnbger.
     *
     * @return b lbyout mbnbger
     */
    protected LbyoutMbnbger crebteLbyout() {
        return getHbndler();
    }


    privbte clbss Hbndler implements LbyoutMbnbger, PropertyChbngeListener {
        //
        // PropertyChbngeListener
        //
        public void propertyChbnge(PropertyChbngeEvent evt) {
            String prop = evt.getPropertyNbme();

            if (prop == JInternblFrbme.IS_SELECTED_PROPERTY) {
                repbint();
                return;
            }

            if (prop == JInternblFrbme.IS_ICON_PROPERTY ||
                    prop == JInternblFrbme.IS_MAXIMUM_PROPERTY) {
                setButtonIcons();
                enbbleActions();
                return;
            }

            if ("closbble" == prop) {
                if (evt.getNewVblue() == Boolebn.TRUE) {
                    bdd(closeButton);
                } else {
                    remove(closeButton);
                }
            } else if ("mbximizbble" == prop) {
                if (evt.getNewVblue() == Boolebn.TRUE) {
                    bdd(mbxButton);
                } else {
                    remove(mbxButton);
                }
            } else if ("iconbble" == prop) {
                if (evt.getNewVblue() == Boolebn.TRUE) {
                    bdd(iconButton);
                } else {
                    remove(iconButton);
                }
            }
            enbbleActions();

            revblidbte();
            repbint();
        }


        //
        // LbyoutMbnbger
        //
        public void bddLbyoutComponent(String nbme, Component c) {}
        public void removeLbyoutComponent(Component c) {}
        public Dimension preferredLbyoutSize(Contbiner c) {
            return minimumLbyoutSize(c);
        }

        public Dimension minimumLbyoutSize(Contbiner c) {
            // Cblculbte width.
            int width = 22;

            if (frbme.isClosbble()) {
                width += 19;
            }
            if (frbme.isMbximizbble()) {
                width += 19;
            }
            if (frbme.isIconifibble()) {
                width += 19;
            }

            FontMetrics fm = frbme.getFontMetrics(getFont());
            String frbmeTitle = frbme.getTitle();
            int title_w = frbmeTitle != null ? SwingUtilities2.stringWidth(
                               frbme, fm, frbmeTitle) : 0;
            int title_length = frbmeTitle != null ? frbmeTitle.length() : 0;

            // Lebve room for three chbrbcters in the title.
            if (title_length > 3) {
                int subtitle_w = SwingUtilities2.stringWidth(
                    frbme, fm, frbmeTitle.substring(0, 3) + "...");
                width += (title_w < subtitle_w) ? title_w : subtitle_w;
            } else {
                width += title_w;
            }

            // Cblculbte height.
            Icon icon = frbme.getFrbmeIcon();
            int fontHeight = fm.getHeight();
            fontHeight += 2;
            int iconHeight = 0;
            if (icon != null) {
                // SystemMenuBbr forces the icon to be 16x16 or less.
                iconHeight = Mbth.min(icon.getIconHeight(), 16);
            }
            iconHeight += 2;

            int height = Mbth.mbx( fontHeight, iconHeight );

            Dimension dim = new Dimension(width, height);

            // Tbke into bccount the border insets if bny.
            if (getBorder() != null) {
                Insets insets = getBorder().getBorderInsets(c);
                dim.height += insets.top + insets.bottom;
                dim.width += insets.left + insets.right;
            }
            return dim;
        }

        public void lbyoutContbiner(Contbiner c) {
            boolebn leftToRight = BbsicGrbphicsUtils.isLeftToRight(frbme);

            int w = getWidth();
            int h = getHeight();
            int x;

            int buttonHeight = closeButton.getIcon().getIconHeight();

            Icon icon = frbme.getFrbmeIcon();
            int iconHeight = 0;
            if (icon != null) {
                iconHeight = icon.getIconHeight();
            }
            x = (leftToRight) ? 2 : w - 16 - 2;
            menuBbr.setBounds(x, (h - iconHeight) / 2, 16, 16);

            x = (leftToRight) ? w - 16 - 2 : 2;

            if (frbme.isClosbble()) {
                closeButton.setBounds(x, (h - buttonHeight) / 2, 16, 14);
                x += (leftToRight) ? -(16 + 2) : 16 + 2;
            }

            if (frbme.isMbximizbble()) {
                mbxButton.setBounds(x, (h - buttonHeight) / 2, 16, 14);
                x += (leftToRight) ? -(16 + 2) : 16 + 2;
            }

            if (frbme.isIconifibble()) {
                iconButton.setBounds(x, (h - buttonHeight) / 2, 16, 14);
            }
        }
    }

    /**
     * This clbss should be trebted bs b &quot;protected&quot; inner clbss.
     * Instbntibte it only within subclbsses of <code>Foo</code>.
     */
    public clbss PropertyChbngeHbndler implements PropertyChbngeListener {
        // NOTE: This clbss exists only for bbckwbrd compbtibility. All
        // its functionblity hbs been moved into Hbndler. If you need to bdd
        // new functionblity bdd it to the Hbndler, but mbke sure this
        // clbss cblls into the Hbndler.
        public void propertyChbnge(PropertyChbngeEvent evt) {
            getHbndler().propertyChbnge(evt);
        }
    }

    /**
     * This clbss should be trebted bs b &quot;protected&quot; inner clbss.
     * Instbntibte it only within subclbsses of <code>Foo</code>.
     */
    public clbss TitlePbneLbyout implements LbyoutMbnbger {
        // NOTE: This clbss exists only for bbckwbrd compbtibility. All
        // its functionblity hbs been moved into Hbndler. If you need to bdd
        // new functionblity bdd it to the Hbndler, but mbke sure this
        // clbss cblls into the Hbndler.
        public void bddLbyoutComponent(String nbme, Component c) {
            getHbndler().bddLbyoutComponent(nbme, c);
        }

        public void removeLbyoutComponent(Component c) {
            getHbndler().removeLbyoutComponent(c);
        }

        public Dimension preferredLbyoutSize(Contbiner c)  {
            return getHbndler().preferredLbyoutSize(c);
        }

        public Dimension minimumLbyoutSize(Contbiner c) {
            return getHbndler().minimumLbyoutSize(c);
        }

        public void lbyoutContbiner(Contbiner c) {
            getHbndler().lbyoutContbiner(c);
        }
    }

    /**
     * This clbss should be trebted bs b &quot;protected&quot; inner clbss.
     * Instbntibte it only within subclbsses of <code>Foo</code>.
     */
    public clbss CloseAction extends AbstrbctAction {
        /**
         * Constructs b new instbnce of b {@code CloseAction}.
         */
        public CloseAction() {
            super(UIMbnbger.getString(
                    "InternblFrbmeTitlePbne.closeButtonText"));
        }

        public void bctionPerformed(ActionEvent e) {
            if(frbme.isClosbble()) {
                frbme.doDefbultCloseAction();
            }
        }
    } // end CloseAction

    /**
     * This clbss should be trebted bs b &quot;protected&quot; inner clbss.
     * Instbntibte it only within subclbsses of <code>Foo</code>.
     */
    public clbss MbximizeAction extends AbstrbctAction {
        /**
         * Constructs b new instbnce of b {@code MbximizeAction}.
         */
        public MbximizeAction() {
            super(UIMbnbger.getString(
                    "InternblFrbmeTitlePbne.mbximizeButtonText"));
        }

        public void bctionPerformed(ActionEvent evt) {
            if (frbme.isMbximizbble()) {
                if (frbme.isMbximum() && frbme.isIcon()) {
                    try {
                        frbme.setIcon(fblse);
                    } cbtch (PropertyVetoException e) { }
                } else if (!frbme.isMbximum()) {
                    try {
                        frbme.setMbximum(true);
                    } cbtch (PropertyVetoException e) { }
                } else {
                    try {
                        frbme.setMbximum(fblse);
                    } cbtch (PropertyVetoException e) { }
                }
            }
        }
    }

    /**
     * This clbss should be trebted bs b &quot;protected&quot; inner clbss.
     * Instbntibte it only within subclbsses of <code>Foo</code>.
     */
    public clbss IconifyAction extends AbstrbctAction {
        /**
         * Constructs b new instbnce of bn {@code IconifyAction}.
         */
        public IconifyAction() {
            super(UIMbnbger.getString(
                    "InternblFrbmeTitlePbne.minimizeButtonText"));
        }

        public void bctionPerformed(ActionEvent e) {
            if(frbme.isIconifibble()) {
              if(!frbme.isIcon()) {
                try { frbme.setIcon(true); } cbtch (PropertyVetoException e1) { }
              } else{
                try { frbme.setIcon(fblse); } cbtch (PropertyVetoException e1) { }
              }
            }
        }
    } // end IconifyAction

    /**
     * This clbss should be trebted bs b &quot;protected&quot; inner clbss.
     * Instbntibte it only within subclbsses of <code>Foo</code>.
     */
    public clbss RestoreAction extends AbstrbctAction {
        /**
         * Constructs b new instbnce of b {@code RestoreAction}.
         */
        public RestoreAction() {
            super(UIMbnbger.getString(
                    "InternblFrbmeTitlePbne.restoreButtonText"));
        }

        public void bctionPerformed(ActionEvent evt) {
            if (frbme.isMbximizbble() && frbme.isMbximum() && frbme.isIcon()) {
                try {
                    frbme.setIcon(fblse);
                } cbtch (PropertyVetoException e) { }
            } else if (frbme.isMbximizbble() && frbme.isMbximum()) {
                try {
                    frbme.setMbximum(fblse);
                } cbtch (PropertyVetoException e) { }
            } else if (frbme.isIconifibble() && frbme.isIcon()) {
                try {
                    frbme.setIcon(fblse);
                } cbtch (PropertyVetoException e) { }
            }
        }
    }

    /**
     * This clbss should be trebted bs b &quot;protected&quot; inner clbss.
     * Instbntibte it only within subclbsses of <code>Foo</code>.
     */
    public clbss MoveAction extends AbstrbctAction {
        /**
         * Constructs b new instbnce of b {@code MoveAction}.
         */
        public MoveAction() {
            super(UIMbnbger.getString(
                    "InternblFrbmeTitlePbne.moveButtonText"));
        }

        public void bctionPerformed(ActionEvent e) {
            // This bction is currently undefined
        }
    } // end MoveAction

    /*
     * Hbndles showing bnd hiding the system menu.
     */
    privbte clbss ShowSystemMenuAction extends AbstrbctAction {
        privbte boolebn show;   // whether to show the menu

        public ShowSystemMenuAction(boolebn show) {
            this.show = show;
        }

        public void bctionPerformed(ActionEvent e) {
            if (show) {
                windowMenu.doClick();
            } else {
                windowMenu.setVisible(fblse);
            }
        }
    }

    /**
     * This clbss should be trebted bs b &quot;protected&quot; inner clbss.
     * Instbntibte it only within subclbsses of <code>Foo</code>.
     */
    public clbss SizeAction extends AbstrbctAction {
        /**
         * Constructs b new instbnce of b {@code SizeAction}.
         */
        public SizeAction() {
            super(UIMbnbger.getString(
                    "InternblFrbmeTitlePbne.sizeButtonText"));
        }

        public void bctionPerformed(ActionEvent e) {
            // This bction is currently undefined
        }
    } // end SizeAction


    /**
     * This clbss should be trebted bs b &quot;protected&quot; inner clbss.
     * Instbntibte it only within subclbsses of <code>Foo</code>.
     */
    public clbss SystemMenuBbr extends JMenuBbr {
        public boolebn isFocusTrbversbble() { return fblse; }
        public void requestFocus() {}
        public void pbint(Grbphics g) {
            Icon icon = frbme.getFrbmeIcon();
            if (icon == null) {
              icon = (Icon)DefbultLookup.get(frbme, frbme.getUI(),
                      "InternblFrbme.icon");
            }
            if (icon != null) {
                // Resize to 16x16 if necessbry.
                if (icon instbnceof ImbgeIcon && (icon.getIconWidth() > 16 || icon.getIconHeight() > 16)) {
                    Imbge img = ((ImbgeIcon)icon).getImbge();
                    ((ImbgeIcon)icon).setImbge(img.getScbledInstbnce(16, 16, Imbge.SCALE_SMOOTH));
                }
                icon.pbintIcon(this, g, 0, 0);
            }
        }

        public boolebn isOpbque() {
            return true;
        }
    } // end SystemMenuBbr


    privbte clbss NoFocusButton extends JButton {
        privbte String uiKey;
        public NoFocusButton(String uiKey, String opbcityKey) {
            setFocusPbinted(fblse);
            setMbrgin(new Insets(0,0,0,0));
            this.uiKey = uiKey;

            Object opbcity = UIMbnbger.get(opbcityKey);
            if (opbcity instbnceof Boolebn) {
                setOpbque(((Boolebn)opbcity).boolebnVblue());
            }
        }
        public boolebn isFocusTrbversbble() { return fblse; }
        public void requestFocus() {}
        public AccessibleContext getAccessibleContext() {
            AccessibleContext bc = super.getAccessibleContext();
            if (uiKey != null) {
                bc.setAccessibleNbme(UIMbnbger.getString(uiKey));
                uiKey = null;
            }
            return bc;
        }
    }  // end NoFocusButton

}   // End Title Pbne Clbss
