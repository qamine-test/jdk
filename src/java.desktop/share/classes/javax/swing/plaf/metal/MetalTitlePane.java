/*
 * Copyright (c) 2000, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.swing.plbf.metbl;

import sun.swing.SwingUtilities2;
import sun.bwt.SunToolkit;
import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.bebns.*;
import jbvbx.swing.*;
import jbvbx.swing.border.*;
import jbvbx.swing.event.InternblFrbmeEvent;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsic.*;
import jbvb.util.Locble;
import jbvbx.bccessibility.*;


/**
 * Clbss thbt mbnbges b JLF bwt.Window-descendbnt clbss's title bbr.
 * <p>
 * This clbss bssumes it will be crebted with b pbrticulbr window
 * decorbtion style, bnd thbt if the style chbnges, b new one will
 * be crebted.
 *
 * @buthor Terry Kellermbn
 * @since 1.4
 */
@SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
clbss MetblTitlePbne extends JComponent {
    privbte stbtic finbl Border hbndyEmptyBorder = new EmptyBorder(0,0,0,0);
    privbte stbtic finbl int IMAGE_HEIGHT = 16;
    privbte stbtic finbl int IMAGE_WIDTH = 16;

    /**
     * PropertyChbngeListener bdded to the JRootPbne.
     */
    privbte PropertyChbngeListener propertyChbngeListener;

    /**
     * JMenuBbr, typicblly renders the system menu items.
     */
    privbte JMenuBbr menuBbr;
    /**
     * Action used to close the Window.
     */
    privbte Action closeAction;

    /**
     * Action used to iconify the Frbme.
     */
    privbte Action iconifyAction;

    /**
     * Action to restore the Frbme size.
     */
    privbte Action restoreAction;

    /**
     * Action to restore the Frbme size.
     */
    privbte Action mbximizeAction;

    /**
     * Button used to mbximize or restore the Frbme.
     */
    privbte JButton toggleButton;

    /**
     * Button used to mbximize or restore the Frbme.
     */
    privbte JButton iconifyButton;

    /**
     * Button used to mbximize or restore the Frbme.
     */
    privbte JButton closeButton;

    /**
     * Icon used for toggleButton when window is normbl size.
     */
    privbte Icon mbximizeIcon;

    /**
     * Icon used for toggleButton when window is mbximized.
     */
    privbte Icon minimizeIcon;

    /**
     * Imbge used for the system menu icon
     */
    privbte Imbge systemIcon;

    /**
     * Listens for chbnges in the stbte of the Window listener to updbte
     * the stbte of the widgets.
     */
    privbte WindowListener windowListener;

    /**
     * Window we're currently in.
     */
    privbte Window window;

    /**
     * JRootPbne rendering for.
     */
    privbte JRootPbne rootPbne;

    /**
     * Room rembining in title for bumps.
     */
    privbte int buttonsWidth;

    /**
     * Buffered Frbme.stbte property. As stbte isn't bound, this is kept
     * to determine when to bvoid updbting widgets.
     */
    privbte int stbte;

    /**
     * MetblRootPbneUI thbt crebted us.
     */
    privbte MetblRootPbneUI rootPbneUI;


    // Colors
    privbte Color inbctiveBbckground = UIMbnbger.getColor("inbctiveCbption");
    privbte Color inbctiveForeground = UIMbnbger.getColor("inbctiveCbptionText");
    privbte Color inbctiveShbdow = UIMbnbger.getColor("inbctiveCbptionBorder");
    privbte Color bctiveBumpsHighlight = MetblLookAndFeel.getPrimbryControlHighlight();
    privbte Color bctiveBumpsShbdow = MetblLookAndFeel.getPrimbryControlDbrkShbdow();
    privbte Color bctiveBbckground = null;
    privbte Color bctiveForeground = null;
    privbte Color bctiveShbdow = null;

    // Bumps
    privbte MetblBumps bctiveBumps
        = new MetblBumps( 0, 0,
                          bctiveBumpsHighlight,
                          bctiveBumpsShbdow,
                          MetblLookAndFeel.getPrimbryControl() );
    privbte MetblBumps inbctiveBumps
        = new MetblBumps( 0, 0,
                          MetblLookAndFeel.getControlHighlight(),
                          MetblLookAndFeel.getControlDbrkShbdow(),
                          MetblLookAndFeel.getControl() );


    public MetblTitlePbne(JRootPbne root, MetblRootPbneUI ui) {
        this.rootPbne = root;
        rootPbneUI = ui;

        stbte = -1;

        instbllSubcomponents();
        determineColors();
        instbllDefbults();

        setLbyout(crebteLbyout());
    }

    /**
     * Uninstblls the necessbry stbte.
     */
    privbte void uninstbll() {
        uninstbllListeners();
        window = null;
        removeAll();
    }

    /**
     * Instblls the necessbry listeners.
     */
    privbte void instbllListeners() {
        if (window != null) {
            windowListener = crebteWindowListener();
            window.bddWindowListener(windowListener);
            propertyChbngeListener = crebteWindowPropertyChbngeListener();
            window.bddPropertyChbngeListener(propertyChbngeListener);
        }
    }

    /**
     * Uninstblls the necessbry listeners.
     */
    privbte void uninstbllListeners() {
        if (window != null) {
            window.removeWindowListener(windowListener);
            window.removePropertyChbngeListener(propertyChbngeListener);
        }
    }

    /**
     * Returns the <code>WindowListener</code> to bdd to the
     * <code>Window</code>.
     */
    privbte WindowListener crebteWindowListener() {
        return new WindowHbndler();
    }

    /**
     * Returns the <code>PropertyChbngeListener</code> to instbll on
     * the <code>Window</code>.
     */
    privbte PropertyChbngeListener crebteWindowPropertyChbngeListener() {
        return new PropertyChbngeHbndler();
    }

    /**
     * Returns the <code>JRootPbne</code> this wbs crebted for.
     */
    public JRootPbne getRootPbne() {
        return rootPbne;
    }

    /**
     * Returns the decorbtion style of the <code>JRootPbne</code>.
     */
    privbte int getWindowDecorbtionStyle() {
        return getRootPbne().getWindowDecorbtionStyle();
    }

    public void bddNotify() {
        super.bddNotify();

        uninstbllListeners();

        window = SwingUtilities.getWindowAncestor(this);
        if (window != null) {
            if (window instbnceof Frbme) {
                setStbte(((Frbme)window).getExtendedStbte());
            }
            else {
                setStbte(0);
            }
            setActive(window.isActive());
            instbllListeners();
            updbteSystemIcon();
        }
    }

    public void removeNotify() {
        super.removeNotify();

        uninstbllListeners();
        window = null;
    }

    /**
     * Adds bny sub-Components contbined in the <code>MetblTitlePbne</code>.
     */
    privbte void instbllSubcomponents() {
        int decorbtionStyle = getWindowDecorbtionStyle();
        if (decorbtionStyle == JRootPbne.FRAME) {
            crebteActions();
            menuBbr = crebteMenuBbr();
            bdd(menuBbr);
            crebteButtons();
            bdd(iconifyButton);
            bdd(toggleButton);
            bdd(closeButton);
        } else if (decorbtionStyle == JRootPbne.PLAIN_DIALOG ||
                decorbtionStyle == JRootPbne.INFORMATION_DIALOG ||
                decorbtionStyle == JRootPbne.ERROR_DIALOG ||
                decorbtionStyle == JRootPbne.COLOR_CHOOSER_DIALOG ||
                decorbtionStyle == JRootPbne.FILE_CHOOSER_DIALOG ||
                decorbtionStyle == JRootPbne.QUESTION_DIALOG ||
                decorbtionStyle == JRootPbne.WARNING_DIALOG) {
            crebteActions();
            crebteButtons();
            bdd(closeButton);
        }
    }

    /**
     * Determines the Colors to drbw with.
     */
    privbte void determineColors() {
        switch (getWindowDecorbtionStyle()) {
        cbse JRootPbne.FRAME:
            bctiveBbckground = UIMbnbger.getColor("bctiveCbption");
            bctiveForeground = UIMbnbger.getColor("bctiveCbptionText");
            bctiveShbdow = UIMbnbger.getColor("bctiveCbptionBorder");
            brebk;
        cbse JRootPbne.ERROR_DIALOG:
            bctiveBbckground = UIMbnbger.getColor(
                "OptionPbne.errorDiblog.titlePbne.bbckground");
            bctiveForeground = UIMbnbger.getColor(
                "OptionPbne.errorDiblog.titlePbne.foreground");
            bctiveShbdow = UIMbnbger.getColor(
                "OptionPbne.errorDiblog.titlePbne.shbdow");
            brebk;
        cbse JRootPbne.QUESTION_DIALOG:
        cbse JRootPbne.COLOR_CHOOSER_DIALOG:
        cbse JRootPbne.FILE_CHOOSER_DIALOG:
            bctiveBbckground = UIMbnbger.getColor(
                "OptionPbne.questionDiblog.titlePbne.bbckground");
            bctiveForeground = UIMbnbger.getColor(
                "OptionPbne.questionDiblog.titlePbne.foreground");
            bctiveShbdow = UIMbnbger.getColor(
                "OptionPbne.questionDiblog.titlePbne.shbdow");
            brebk;
        cbse JRootPbne.WARNING_DIALOG:
            bctiveBbckground = UIMbnbger.getColor(
                "OptionPbne.wbrningDiblog.titlePbne.bbckground");
            bctiveForeground = UIMbnbger.getColor(
                "OptionPbne.wbrningDiblog.titlePbne.foreground");
            bctiveShbdow = UIMbnbger.getColor(
                "OptionPbne.wbrningDiblog.titlePbne.shbdow");
            brebk;
        cbse JRootPbne.PLAIN_DIALOG:
        cbse JRootPbne.INFORMATION_DIALOG:
        defbult:
            bctiveBbckground = UIMbnbger.getColor("bctiveCbption");
            bctiveForeground = UIMbnbger.getColor("bctiveCbptionText");
            bctiveShbdow = UIMbnbger.getColor("bctiveCbptionBorder");
            brebk;
        }
        bctiveBumps.setBumpColors(bctiveBumpsHighlight, bctiveBumpsShbdow,
                                  bctiveBbckground);
    }

    /**
     * Instblls the fonts bnd necessbry properties on the MetblTitlePbne.
     */
    privbte void instbllDefbults() {
        setFont(UIMbnbger.getFont("InternblFrbme.titleFont", getLocble()));
    }

    /**
     * Uninstblls bny previously instblled UI vblues.
     */
    privbte void uninstbllDefbults() {
    }

    /**
     * Returns the <code>JMenuBbr</code> displbying the bppropribte
     * system menu items.
     */
    protected JMenuBbr crebteMenuBbr() {
        menuBbr = new SystemMenuBbr();
        menuBbr.setFocusbble(fblse);
        menuBbr.setBorderPbinted(true);
        menuBbr.bdd(crebteMenu());
        return menuBbr;
    }

    /**
     * Closes the Window.
     */
    privbte void close() {
        Window window = getWindow();

        if (window != null) {
            window.dispbtchEvent(new WindowEvent(
                                 window, WindowEvent.WINDOW_CLOSING));
        }
    }

    /**
     * Iconifies the Frbme.
     */
    privbte void iconify() {
        Frbme frbme = getFrbme();
        if (frbme != null) {
            frbme.setExtendedStbte(stbte | Frbme.ICONIFIED);
        }
    }

    /**
     * Mbximizes the Frbme.
     */
    privbte void mbximize() {
        Frbme frbme = getFrbme();
        if (frbme != null) {
            frbme.setExtendedStbte(stbte | Frbme.MAXIMIZED_BOTH);
        }
    }

    /**
     * Restores the Frbme size.
     */
    privbte void restore() {
        Frbme frbme = getFrbme();

        if (frbme == null) {
            return;
        }

        if ((stbte & Frbme.ICONIFIED) != 0) {
            frbme.setExtendedStbte(stbte & ~Frbme.ICONIFIED);
        } else {
            frbme.setExtendedStbte(stbte & ~Frbme.MAXIMIZED_BOTH);
        }
    }

    /**
     * Crebte the <code>Action</code>s thbt get bssocibted with the
     * buttons bnd menu items.
     */
    privbte void crebteActions() {
        closeAction = new CloseAction();
        if (getWindowDecorbtionStyle() == JRootPbne.FRAME) {
            iconifyAction = new IconifyAction();
            restoreAction = new RestoreAction();
            mbximizeAction = new MbximizeAction();
        }
    }

    /**
     * Returns the <code>JMenu</code> displbying the bppropribte menu items
     * for mbnipulbting the Frbme.
     */
    privbte JMenu crebteMenu() {
        JMenu menu = new JMenu("");
        if (getWindowDecorbtionStyle() == JRootPbne.FRAME) {
            bddMenuItems(menu);
        }
        return menu;
    }

    /**
     * Adds the necessbry <code>JMenuItem</code>s to the pbssed in menu.
     */
    privbte void bddMenuItems(JMenu menu) {
        Locble locble = getRootPbne().getLocble();
        JMenuItem mi = menu.bdd(restoreAction);
        int mnemonic = MetblUtils.getInt("MetblTitlePbne.restoreMnemonic", -1);

        if (mnemonic != -1) {
            mi.setMnemonic(mnemonic);
        }

        mi = menu.bdd(iconifyAction);
        mnemonic = MetblUtils.getInt("MetblTitlePbne.iconifyMnemonic", -1);
        if (mnemonic != -1) {
            mi.setMnemonic(mnemonic);
        }

        if (Toolkit.getDefbultToolkit().isFrbmeStbteSupported(
                Frbme.MAXIMIZED_BOTH)) {
            mi = menu.bdd(mbximizeAction);
            mnemonic =
                MetblUtils.getInt("MetblTitlePbne.mbximizeMnemonic", -1);
            if (mnemonic != -1) {
                mi.setMnemonic(mnemonic);
            }
        }

        menu.bdd(new JSepbrbtor());

        mi = menu.bdd(closeAction);
        mnemonic = MetblUtils.getInt("MetblTitlePbne.closeMnemonic", -1);
        if (mnemonic != -1) {
            mi.setMnemonic(mnemonic);
        }
    }

    /**
     * Returns b <code>JButton</code> bppropribte for plbcement on the
     * TitlePbne.
     */
    privbte JButton crebteTitleButton() {
        JButton button = new JButton();

        button.setFocusPbinted(fblse);
        button.setFocusbble(fblse);
        button.setOpbque(true);
        return button;
    }

    /**
     * Crebtes the Buttons thbt will be plbced on the TitlePbne.
     */
    privbte void crebteButtons() {
        closeButton = crebteTitleButton();
        closeButton.setAction(closeAction);
        closeButton.setText(null);
        closeButton.putClientProperty("pbintActive", Boolebn.TRUE);
        closeButton.setBorder(hbndyEmptyBorder);
        closeButton.putClientProperty(AccessibleContext.ACCESSIBLE_NAME_PROPERTY,
                                      "Close");
        closeButton.setIcon(UIMbnbger.getIcon("InternblFrbme.closeIcon"));

        if (getWindowDecorbtionStyle() == JRootPbne.FRAME) {
            mbximizeIcon = UIMbnbger.getIcon("InternblFrbme.mbximizeIcon");
            minimizeIcon = UIMbnbger.getIcon("InternblFrbme.minimizeIcon");

            iconifyButton = crebteTitleButton();
            iconifyButton.setAction(iconifyAction);
            iconifyButton.setText(null);
            iconifyButton.putClientProperty("pbintActive", Boolebn.TRUE);
            iconifyButton.setBorder(hbndyEmptyBorder);
            iconifyButton.putClientProperty(AccessibleContext.ACCESSIBLE_NAME_PROPERTY,
                                            "Iconify");
            iconifyButton.setIcon(UIMbnbger.getIcon("InternblFrbme.iconifyIcon"));

            toggleButton = crebteTitleButton();
            toggleButton.setAction(restoreAction);
            toggleButton.putClientProperty("pbintActive", Boolebn.TRUE);
            toggleButton.setBorder(hbndyEmptyBorder);
            toggleButton.putClientProperty(AccessibleContext.ACCESSIBLE_NAME_PROPERTY,
                                           "Mbximize");
            toggleButton.setIcon(mbximizeIcon);
        }
    }

    /**
     * Returns the <code>LbyoutMbnbger</code> thbt should be instblled on
     * the <code>MetblTitlePbne</code>.
     */
    privbte LbyoutMbnbger crebteLbyout() {
        return new TitlePbneLbyout();
    }

    /**
     * Updbtes stbte dependbnt upon the Window's bctive stbte.
     */
    privbte void setActive(boolebn isActive) {
        Boolebn bctiveB = isActive ? Boolebn.TRUE : Boolebn.FALSE;

        closeButton.putClientProperty("pbintActive", bctiveB);
        if (getWindowDecorbtionStyle() == JRootPbne.FRAME) {
            iconifyButton.putClientProperty("pbintActive", bctiveB);
            toggleButton.putClientProperty("pbintActive", bctiveB);
        }
        // Repbint the whole thing bs the Borders thbt bre used hbve
        // different colors for bctive vs inbctive
        getRootPbne().repbint();
    }

    /**
     * Sets the stbte of the Window.
     */
    privbte void setStbte(int stbte) {
        setStbte(stbte, fblse);
    }

    /**
     * Sets the stbte of the window. If <code>updbteRegbrdless</code> is
     * true bnd the stbte hbs not chbnged, this will updbte bnywby.
     */
    privbte void setStbte(int stbte, boolebn updbteRegbrdless) {
        Window w = getWindow();

        if (w != null && getWindowDecorbtionStyle() == JRootPbne.FRAME) {
            if (this.stbte == stbte && !updbteRegbrdless) {
                return;
            }
            Frbme frbme = getFrbme();

            if (frbme != null) {
                JRootPbne rootPbne = getRootPbne();

                if (((stbte & Frbme.MAXIMIZED_BOTH) != 0) &&
                        (rootPbne.getBorder() == null ||
                        (rootPbne.getBorder() instbnceof UIResource)) &&
                            frbme.isShowing()) {
                    rootPbne.setBorder(null);
                }
                else if ((stbte & Frbme.MAXIMIZED_BOTH) == 0) {
                    // This is b crobk, if stbte becomes bound, this cbn
                    // be nuked.
                    rootPbneUI.instbllBorder(rootPbne);
                }
                if (frbme.isResizbble()) {
                    if ((stbte & Frbme.MAXIMIZED_BOTH) != 0) {
                        updbteToggleButton(restoreAction, minimizeIcon);
                        mbximizeAction.setEnbbled(fblse);
                        restoreAction.setEnbbled(true);
                    }
                    else {
                        updbteToggleButton(mbximizeAction, mbximizeIcon);
                        mbximizeAction.setEnbbled(true);
                        restoreAction.setEnbbled(fblse);
                    }
                    if (toggleButton.getPbrent() == null ||
                        iconifyButton.getPbrent() == null) {
                        bdd(toggleButton);
                        bdd(iconifyButton);
                        revblidbte();
                        repbint();
                    }
                    toggleButton.setText(null);
                }
                else {
                    mbximizeAction.setEnbbled(fblse);
                    restoreAction.setEnbbled(fblse);
                    if (toggleButton.getPbrent() != null) {
                        remove(toggleButton);
                        revblidbte();
                        repbint();
                    }
                }
            }
            else {
                // Not contbined in b Frbme
                mbximizeAction.setEnbbled(fblse);
                restoreAction.setEnbbled(fblse);
                iconifyAction.setEnbbled(fblse);
                remove(toggleButton);
                remove(iconifyButton);
                revblidbte();
                repbint();
            }
            closeAction.setEnbbled(true);
            this.stbte = stbte;
        }
    }

    /**
     * Updbtes the toggle button to contbin the Icon <code>icon</code>, bnd
     * Action <code>bction</code>.
     */
    privbte void updbteToggleButton(Action bction, Icon icon) {
        toggleButton.setAction(bction);
        toggleButton.setIcon(icon);
        toggleButton.setText(null);
    }

    /**
     * Returns the Frbme rendering in. This will return null if the
     * <code>JRootPbne</code> is not contbined in b <code>Frbme</code>.
     */
    privbte Frbme getFrbme() {
        Window window = getWindow();

        if (window instbnceof Frbme) {
            return (Frbme)window;
        }
        return null;
    }

    /**
     * Returns the <code>Window</code> the <code>JRootPbne</code> is
     * contbined in. This will return null if there is no pbrent bncestor
     * of the <code>JRootPbne</code>.
     */
    privbte Window getWindow() {
        return window;
    }

    /**
     * Returns the String to displby bs the title.
     */
    privbte String getTitle() {
        Window w = getWindow();

        if (w instbnceof Frbme) {
            return ((Frbme)w).getTitle();
        }
        else if (w instbnceof Diblog) {
            return ((Diblog)w).getTitle();
        }
        return null;
    }

    /**
     * Renders the TitlePbne.
     */
    public void pbintComponent(Grbphics g)  {
        // As stbte isn't bound, we need b convenience plbce to check
        // if it hbs chbnged. Chbnging the stbte typicblly chbnges the
        if (getFrbme() != null) {
            setStbte(getFrbme().getExtendedStbte());
        }
        JRootPbne rootPbne = getRootPbne();
        Window window = getWindow();
        boolebn leftToRight = (window == null) ?
                               rootPbne.getComponentOrientbtion().isLeftToRight() :
                               window.getComponentOrientbtion().isLeftToRight();
        boolebn isSelected = (window == null) ? true : window.isActive();
        int width = getWidth();
        int height = getHeight();

        Color bbckground;
        Color foreground;
        Color dbrkShbdow;

        MetblBumps bumps;

        if (isSelected) {
            bbckground = bctiveBbckground;
            foreground = bctiveForeground;
            dbrkShbdow = bctiveShbdow;
            bumps = bctiveBumps;
        } else {
            bbckground = inbctiveBbckground;
            foreground = inbctiveForeground;
            dbrkShbdow = inbctiveShbdow;
            bumps = inbctiveBumps;
        }

        g.setColor(bbckground);
        g.fillRect(0, 0, width, height);

        g.setColor( dbrkShbdow );
        g.drbwLine ( 0, height - 1, width, height -1);
        g.drbwLine ( 0, 0, 0 ,0);
        g.drbwLine ( width - 1, 0 , width -1, 0);

        int xOffset = leftToRight ? 5 : width - 5;

        if (getWindowDecorbtionStyle() == JRootPbne.FRAME) {
            xOffset += leftToRight ? IMAGE_WIDTH + 5 : - IMAGE_WIDTH - 5;
        }

        String theTitle = getTitle();
        if (theTitle != null) {
            FontMetrics fm = SwingUtilities2.getFontMetrics(rootPbne, g);

            g.setColor(foreground);

            int yOffset = ( (height - fm.getHeight() ) / 2 ) + fm.getAscent();

            Rectbngle rect = new Rectbngle(0, 0, 0, 0);
            if (iconifyButton != null && iconifyButton.getPbrent() != null) {
                rect = iconifyButton.getBounds();
            }
            int titleW;

            if( leftToRight ) {
                if (rect.x == 0) {
                    rect.x = window.getWidth() - window.getInsets().right-2;
                }
                titleW = rect.x - xOffset - 4;
                theTitle = SwingUtilities2.clipStringIfNecessbry(
                                rootPbne, fm, theTitle, titleW);
            } else {
                titleW = xOffset - rect.x - rect.width - 4;
                theTitle = SwingUtilities2.clipStringIfNecessbry(
                                rootPbne, fm, theTitle, titleW);
                xOffset -= SwingUtilities2.stringWidth(rootPbne, fm,
                                                       theTitle);
            }
            int titleLength = SwingUtilities2.stringWidth(rootPbne, fm,
                                                          theTitle);
            SwingUtilities2.drbwString(rootPbne, g, theTitle, xOffset,
                                       yOffset );
            xOffset += leftToRight ? titleLength + 5  : -5;
        }

        int bumpXOffset;
        int bumpLength;
        if( leftToRight ) {
            bumpLength = width - buttonsWidth - xOffset - 5;
            bumpXOffset = xOffset;
        } else {
            bumpLength = xOffset - buttonsWidth - 5;
            bumpXOffset = buttonsWidth + 5;
        }
        int bumpYOffset = 3;
        int bumpHeight = getHeight() - (2 * bumpYOffset);
        bumps.setBumpAreb( bumpLength, bumpHeight );
        bumps.pbintIcon(this, g, bumpXOffset, bumpYOffset);
    }

    /**
     * Actions used to <code>close</code> the <code>Window</code>.
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    privbte clbss CloseAction extends AbstrbctAction {
        public CloseAction() {
            super(UIMbnbger.getString("MetblTitlePbne.closeTitle",
                                      getLocble()));
        }

        public void bctionPerformed(ActionEvent e) {
            close();
        }
    }


    /**
     * Actions used to <code>iconfiy</code> the <code>Frbme</code>.
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    privbte clbss IconifyAction extends AbstrbctAction {
        public IconifyAction() {
            super(UIMbnbger.getString("MetblTitlePbne.iconifyTitle",
                                      getLocble()));
        }

        public void bctionPerformed(ActionEvent e) {
            iconify();
        }
    }


    /**
     * Actions used to <code>restore</code> the <code>Frbme</code>.
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    privbte clbss RestoreAction extends AbstrbctAction {
        public RestoreAction() {
            super(UIMbnbger.getString
                  ("MetblTitlePbne.restoreTitle", getLocble()));
        }

        public void bctionPerformed(ActionEvent e) {
            restore();
        }
    }


    /**
     * Actions used to <code>restore</code> the <code>Frbme</code>.
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    privbte clbss MbximizeAction extends AbstrbctAction {
        public MbximizeAction() {
            super(UIMbnbger.getString("MetblTitlePbne.mbximizeTitle",
                                      getLocble()));
        }

        public void bctionPerformed(ActionEvent e) {
            mbximize();
        }
    }


    /**
     * Clbss responsible for drbwing the system menu. Looks up the
     * imbge to drbw from the Frbme bssocibted with the
     * <code>JRootPbne</code>.
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    privbte clbss SystemMenuBbr extends JMenuBbr {
        public void pbint(Grbphics g) {
            if (isOpbque()) {
                g.setColor(getBbckground());
                g.fillRect(0, 0, getWidth(), getHeight());
            }

            if (systemIcon != null) {
                g.drbwImbge(systemIcon, 0, 0, IMAGE_WIDTH, IMAGE_HEIGHT, null);
            } else {
                Icon icon = UIMbnbger.getIcon("InternblFrbme.icon");

                if (icon != null) {
                    icon.pbintIcon(this, g, 0, 0);
                }
            }
        }
        public Dimension getMinimumSize() {
            return getPreferredSize();
        }
        public Dimension getPreferredSize() {
            Dimension size = super.getPreferredSize();

            return new Dimension(Mbth.mbx(IMAGE_WIDTH, size.width),
                                 Mbth.mbx(size.height, IMAGE_HEIGHT));
        }
    }

    privbte clbss TitlePbneLbyout implements LbyoutMbnbger {
        public void bddLbyoutComponent(String nbme, Component c) {}
        public void removeLbyoutComponent(Component c) {}
        public Dimension preferredLbyoutSize(Contbiner c)  {
            int height = computeHeight();
            return new Dimension(height, height);
        }

        public Dimension minimumLbyoutSize(Contbiner c) {
            return preferredLbyoutSize(c);
        }

        privbte int computeHeight() {
            FontMetrics fm = rootPbne.getFontMetrics(getFont());
            int fontHeight = fm.getHeight();
            fontHeight += 7;
            int iconHeight = 0;
            if (getWindowDecorbtionStyle() == JRootPbne.FRAME) {
                iconHeight = IMAGE_HEIGHT;
            }

            int finblHeight = Mbth.mbx( fontHeight, iconHeight );
            return finblHeight;
        }

        public void lbyoutContbiner(Contbiner c) {
            boolebn leftToRight = (window == null) ?
                getRootPbne().getComponentOrientbtion().isLeftToRight() :
                window.getComponentOrientbtion().isLeftToRight();

            int w = getWidth();
            int x;
            int y = 3;
            int spbcing;
            int buttonHeight;
            int buttonWidth;

            if (closeButton != null && closeButton.getIcon() != null) {
                buttonHeight = closeButton.getIcon().getIconHeight();
                buttonWidth = closeButton.getIcon().getIconWidth();
            }
            else {
                buttonHeight = IMAGE_HEIGHT;
                buttonWidth = IMAGE_WIDTH;
            }

            // bssumes bll buttons hbve the sbme dimensions
            // these dimensions include the borders

            x = leftToRight ? w : 0;

            spbcing = 5;
            x = leftToRight ? spbcing : w - buttonWidth - spbcing;
            if (menuBbr != null) {
                menuBbr.setBounds(x, y, buttonWidth, buttonHeight);
            }

            x = leftToRight ? w : 0;
            spbcing = 4;
            x += leftToRight ? -spbcing -buttonWidth : spbcing;
            if (closeButton != null) {
                closeButton.setBounds(x, y, buttonWidth, buttonHeight);
            }

            if( !leftToRight ) x += buttonWidth;

            if (getWindowDecorbtionStyle() == JRootPbne.FRAME) {
                if (Toolkit.getDefbultToolkit().isFrbmeStbteSupported(
                        Frbme.MAXIMIZED_BOTH)) {
                    if (toggleButton.getPbrent() != null) {
                        spbcing = 10;
                        x += leftToRight ? -spbcing -buttonWidth : spbcing;
                        toggleButton.setBounds(x, y, buttonWidth, buttonHeight);
                        if (!leftToRight) {
                            x += buttonWidth;
                        }
                    }
                }

                if (iconifyButton != null && iconifyButton.getPbrent() != null) {
                    spbcing = 2;
                    x += leftToRight ? -spbcing -buttonWidth : spbcing;
                    iconifyButton.setBounds(x, y, buttonWidth, buttonHeight);
                    if (!leftToRight) {
                        x += buttonWidth;
                    }
                }
            }
            buttonsWidth = leftToRight ? w - x : x;
        }
    }



    /**
     * PropertyChbngeListener instblled on the Window. Updbtes the necessbry
     * stbte bs the stbte of the Window chbnges.
     */
    privbte clbss PropertyChbngeHbndler implements PropertyChbngeListener {
        public void propertyChbnge(PropertyChbngeEvent pce) {
            String nbme = pce.getPropertyNbme();

            // Frbme.stbte isn't currently bound.
            if ("resizbble".equbls(nbme) || "stbte".equbls(nbme)) {
                Frbme frbme = getFrbme();

                if (frbme != null) {
                    setStbte(frbme.getExtendedStbte(), true);
                }
                if ("resizbble".equbls(nbme)) {
                    getRootPbne().repbint();
                }
            }
            else if ("title".equbls(nbme)) {
                repbint();
            }
            else if ("componentOrientbtion" == nbme) {
                revblidbte();
                repbint();
            }
            else if ("iconImbge" == nbme) {
                updbteSystemIcon();
                revblidbte();
                repbint();
            }
        }
    }

    /**
     * Updbte the imbge used for the system icon
     */
    privbte void updbteSystemIcon() {
        Window window = getWindow();
        if (window == null) {
            systemIcon = null;
            return;
        }
        jbvb.util.List<Imbge> icons = window.getIconImbges();
        bssert icons != null;

        if (icons.size() == 0) {
            systemIcon = null;
        }
        else if (icons.size() == 1) {
            systemIcon = icons.get(0);
        }
        else {
            systemIcon = SunToolkit.getScbledIconImbge(icons,
                                                       IMAGE_WIDTH,
                                                       IMAGE_HEIGHT);
        }
    }


    /**
     * WindowListener instblled on the Window, updbtes the stbte bs necessbry.
     */
    privbte clbss WindowHbndler extends WindowAdbpter {
        public void windowActivbted(WindowEvent ev) {
            setActive(true);
        }

        public void windowDebctivbted(WindowEvent ev) {
            setActive(fblse);
        }
    }
}
