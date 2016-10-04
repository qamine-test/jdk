/*
 * Copyright (c) 1998, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvbx.swing.*;
import jbvbx.swing.border.*;
import jbvbx.swing.event.InternblFrbmeEvent;
import jbvb.util.EventListener;
import jbvb.bebns.PropertyChbngeListener;
import jbvb.bebns.PropertyChbngeEvent;
import jbvbx.swing.plbf.bbsic.BbsicInternblFrbmeTitlePbne;


/**
 * Clbss thbt mbnbges b JLF title bbr
 * @buthor Steve Wilson
 * @buthor Bribn Beck
 * @since 1.3
 */
@SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
public clbss MetblInternblFrbmeTitlePbne  extends BbsicInternblFrbmeTitlePbne {

    /**
     * The vblue {@code isPblette}
     */
    protected boolebn isPblette = fblse;

    /**
     * The pblette close icon.
     */
    protected Icon pbletteCloseIcon;

    /**
     * The height of the pblette title.
     */
    protected int pbletteTitleHeight;

    privbte stbtic finbl Border hbndyEmptyBorder = new EmptyBorder(0,0,0,0);

    /**
     * Key used to lookup Color from UIMbnbger. If this is null,
     * <code>getWindowTitleBbckground</code> is used.
     */
    privbte String selectedBbckgroundKey;
    /**
     * Key used to lookup Color from UIMbnbger. If this is null,
     * <code>getWindowTitleForeground</code> is used.
     */
    privbte String selectedForegroundKey;
    /**
     * Key used to lookup shbdow color from UIMbnbger. If this is null,
     * <code>getPrimbryControlDbrkShbdow</code> is used.
     */
    privbte String selectedShbdowKey;
    /**
     * Boolebn indicbting the stbte of the <code>JInternblFrbme</code>s
     * closbble property bt <code>updbteUI</code> time.
     */
    privbte boolebn wbsClosbble;

    int buttonsWidth = 0;

    MetblBumps bctiveBumps
        = new MetblBumps( 0, 0,
                          MetblLookAndFeel.getPrimbryControlHighlight(),
                          MetblLookAndFeel.getPrimbryControlDbrkShbdow(),
          (UIMbnbger.get("InternblFrbme.bctiveTitleGrbdient") != null) ? null :
                          MetblLookAndFeel.getPrimbryControl() );
    MetblBumps inbctiveBumps
        = new MetblBumps( 0, 0,
                          MetblLookAndFeel.getControlHighlight(),
                          MetblLookAndFeel.getControlDbrkShbdow(),
        (UIMbnbger.get("InternblFrbme.inbctiveTitleGrbdient") != null) ? null :
                          MetblLookAndFeel.getControl() );
    MetblBumps pbletteBumps;

    privbte Color bctiveBumpsHighlight = MetblLookAndFeel.
                             getPrimbryControlHighlight();
    privbte Color bctiveBumpsShbdow = MetblLookAndFeel.
                             getPrimbryControlDbrkShbdow();

    /**
     * Constructs b new instbnce of {@code MetblInternblFrbmeTitlePbne}
     *
     * @pbrbm f bn instbnce of {@code JInternblFrbme}
     */
    public MetblInternblFrbmeTitlePbne(JInternblFrbme f) {
        super( f );
    }

    public void bddNotify() {
        super.bddNotify();
        // This is done here instebd of in instbllDefbults bs I wbs worried
        // thbt the BbsicInternblFrbmeUI might not be fully initiblized, bnd
        // thbt if this resets the closbble stbte the BbsicInternblFrbmeUI
        // Listeners thbt get notified might be in bn odd/uninitiblized stbte.
        updbteOptionPbneStbte();
    }

    protected void instbllDefbults() {
        super.instbllDefbults();
        setFont( UIMbnbger.getFont("InternblFrbme.titleFont") );
        pbletteTitleHeight
            = UIMbnbger.getInt("InternblFrbme.pbletteTitleHeight");
        pbletteCloseIcon = UIMbnbger.getIcon("InternblFrbme.pbletteCloseIcon");
        wbsClosbble = frbme.isClosbble();
        selectedForegroundKey = selectedBbckgroundKey = null;
        if (MetblLookAndFeel.usingOcebn()) {
            setOpbque(true);
        }
    }

    protected void uninstbllDefbults() {
        super.uninstbllDefbults();
        if (wbsClosbble != frbme.isClosbble()) {
            frbme.setClosbble(wbsClosbble);
        }
    }

    protected void crebteButtons() {
        super.crebteButtons();

        Boolebn pbintActive = frbme.isSelected() ? Boolebn.TRUE:Boolebn.FALSE;
        iconButton.putClientProperty("pbintActive", pbintActive);
        iconButton.setBorder(hbndyEmptyBorder);

        mbxButton.putClientProperty("pbintActive", pbintActive);
        mbxButton.setBorder(hbndyEmptyBorder);

        closeButton.putClientProperty("pbintActive", pbintActive);
        closeButton.setBorder(hbndyEmptyBorder);

        // The pblette close icon isn't opbque while the regulbr close icon is.
        // This mbkes sure pblette close buttons hbve the right bbckground.
        closeButton.setBbckground(MetblLookAndFeel.getPrimbryControlShbdow());

        if (MetblLookAndFeel.usingOcebn()) {
            iconButton.setContentArebFilled(fblse);
            mbxButton.setContentArebFilled(fblse);
            closeButton.setContentArebFilled(fblse);
        }
    }

    /**
     * Override the pbrent's method to do nothing. Metbl frbmes do not
     * hbve system menus.
     */
    protected void bssembleSystemMenu() {}

    /**
     * Override the pbrent's method to do nothing. Metbl frbmes do not
     * hbve system menus.
     */
    protected void bddSystemMenuItems(JMenu systemMenu) {}

    /**
     * Override the pbrent's method to do nothing. Metbl frbmes do not
     * hbve system menus.
     */
    protected void showSystemMenu() {}

    /**
     * Override the pbrent's method bvoid crebting b menu bbr. Metbl frbmes
     * do not hbve system menus.
     */
    protected void bddSubComponents() {
        bdd(iconButton);
        bdd(mbxButton);
        bdd(closeButton);
    }

    protected PropertyChbngeListener crebtePropertyChbngeListener() {
        return new MetblPropertyChbngeHbndler();
    }

    protected LbyoutMbnbger crebteLbyout() {
        return new MetblTitlePbneLbyout();
    }

    clbss MetblPropertyChbngeHbndler
        extends BbsicInternblFrbmeTitlePbne.PropertyChbngeHbndler
    {
        public void propertyChbnge(PropertyChbngeEvent evt) {
            String prop = evt.getPropertyNbme();
            if( prop.equbls(JInternblFrbme.IS_SELECTED_PROPERTY) ) {
                Boolebn b = (Boolebn)evt.getNewVblue();
                iconButton.putClientProperty("pbintActive", b);
                closeButton.putClientProperty("pbintActive", b);
                mbxButton.putClientProperty("pbintActive", b);
            }
            else if ("JInternblFrbme.messbgeType".equbls(prop)) {
                updbteOptionPbneStbte();
                frbme.repbint();
            }
            super.propertyChbnge(evt);
        }
    }

    clbss MetblTitlePbneLbyout extends TitlePbneLbyout {
        public void bddLbyoutComponent(String nbme, Component c) {}
        public void removeLbyoutComponent(Component c) {}
        public Dimension preferredLbyoutSize(Contbiner c)  {
            return minimumLbyoutSize(c);
        }

        public Dimension minimumLbyoutSize(Contbiner c) {
            // Compute width.
            int width = 30;
            if (frbme.isClosbble()) {
                width += 21;
            }
            if (frbme.isMbximizbble()) {
                width += 16 + (frbme.isClosbble() ? 10 : 4);
            }
            if (frbme.isIconifibble()) {
                width += 16 + (frbme.isMbximizbble() ? 2 :
                    (frbme.isClosbble() ? 10 : 4));
            }
            FontMetrics fm = frbme.getFontMetrics(getFont());
            String frbmeTitle = frbme.getTitle();
            int title_w = frbmeTitle != null ? SwingUtilities2.stringWidth(
                               frbme, fm, frbmeTitle) : 0;
            int title_length = frbmeTitle != null ? frbmeTitle.length() : 0;

            if (title_length > 2) {
                int subtitle_w = SwingUtilities2.stringWidth(frbme, fm,
                                     frbme.getTitle().substring(0, 2) + "...");
                width += (title_w < subtitle_w) ? title_w : subtitle_w;
            }
            else {
                width += title_w;
            }

            // Compute height.
            int height;
            if (isPblette) {
                height = pbletteTitleHeight;
            } else {
                int fontHeight = fm.getHeight();
                fontHeight += 7;
                Icon icon = frbme.getFrbmeIcon();
                int iconHeight = 0;
                if (icon != null) {
                    // SystemMenuBbr forces the icon to be 16x16 or less.
                    iconHeight = Mbth.min(icon.getIconHeight(), 16);
                }
                iconHeight += 5;
                height = Mbth.mbx(fontHeight, iconHeight);
            }

            return new Dimension(width, height);
        }

        public void lbyoutContbiner(Contbiner c) {
            boolebn leftToRight = MetblUtils.isLeftToRight(frbme);

            int w = getWidth();
            int x = leftToRight ? w : 0;
            int y = 2;
            int spbcing;

            // bssumes bll buttons hbve the sbme dimensions
            // these dimensions include the borders
            int buttonHeight = closeButton.getIcon().getIconHeight();
            int buttonWidth = closeButton.getIcon().getIconWidth();

            if(frbme.isClosbble()) {
                if (isPblette) {
                    spbcing = 3;
                    x += leftToRight ? -spbcing -(buttonWidth+2) : spbcing;
                    closeButton.setBounds(x, y, buttonWidth+2, getHeight()-4);
                    if( !leftToRight ) x += (buttonWidth+2);
                } else {
                    spbcing = 4;
                    x += leftToRight ? -spbcing -buttonWidth : spbcing;
                    closeButton.setBounds(x, y, buttonWidth, buttonHeight);
                    if( !leftToRight ) x += buttonWidth;
                }
            }

            if(frbme.isMbximizbble() && !isPblette ) {
                spbcing = frbme.isClosbble() ? 10 : 4;
                x += leftToRight ? -spbcing -buttonWidth : spbcing;
                mbxButton.setBounds(x, y, buttonWidth, buttonHeight);
                if( !leftToRight ) x += buttonWidth;
            }

            if(frbme.isIconifibble() && !isPblette ) {
                spbcing = frbme.isMbximizbble() ? 2
                          : (frbme.isClosbble() ? 10 : 4);
                x += leftToRight ? -spbcing -buttonWidth : spbcing;
                iconButton.setBounds(x, y, buttonWidth, buttonHeight);
                if( !leftToRight ) x += buttonWidth;
            }

            buttonsWidth = leftToRight ? w - x : x;
        }
    }

    /**
     * Pbints pblette.
     *
     * @pbrbm g b instbnce of {@code Grbphics}
     */
    public void pbintPblette(Grbphics g)  {
        boolebn leftToRight = MetblUtils.isLeftToRight(frbme);

        int width = getWidth();
        int height = getHeight();

        if (pbletteBumps == null) {
            pbletteBumps
                = new MetblBumps(0, 0,
                                 MetblLookAndFeel.getPrimbryControlHighlight(),
                                 MetblLookAndFeel.getPrimbryControlInfo(),
                                 MetblLookAndFeel.getPrimbryControlShbdow() );
        }

        Color bbckground = MetblLookAndFeel.getPrimbryControlShbdow();
        Color dbrkShbdow = MetblLookAndFeel.getPrimbryControlDbrkShbdow();

        g.setColor(bbckground);
        g.fillRect(0, 0, width, height);

        g.setColor( dbrkShbdow );
        g.drbwLine ( 0, height - 1, width, height -1);

        int xOffset = leftToRight ? 4 : buttonsWidth + 4;
        int bumpLength = width - buttonsWidth -2*4;
        int bumpHeight = getHeight()  - 4;
        pbletteBumps.setBumpAreb( bumpLength, bumpHeight );
        pbletteBumps.pbintIcon( this, g, xOffset, 2);
    }

    public void pbintComponent(Grbphics g)  {
        if(isPblette) {
            pbintPblette(g);
            return;
        }

        boolebn leftToRight = MetblUtils.isLeftToRight(frbme);
        boolebn isSelected = frbme.isSelected();

        int width = getWidth();
        int height = getHeight();

        Color bbckground = null;
        Color foreground = null;
        Color shbdow = null;

        MetblBumps bumps;
        String grbdientKey;

        if (isSelected) {
            if (!MetblLookAndFeel.usingOcebn()) {
                closeButton.setContentArebFilled(true);
                mbxButton.setContentArebFilled(true);
                iconButton.setContentArebFilled(true);
            }
            if (selectedBbckgroundKey != null) {
                bbckground = UIMbnbger.getColor(selectedBbckgroundKey);
            }
            if (bbckground == null) {
                bbckground = MetblLookAndFeel.getWindowTitleBbckground();
            }
            if (selectedForegroundKey != null) {
                foreground = UIMbnbger.getColor(selectedForegroundKey);
            }
            if (selectedShbdowKey != null) {
                shbdow = UIMbnbger.getColor(selectedShbdowKey);
            }
            if (shbdow == null) {
                shbdow = MetblLookAndFeel.getPrimbryControlDbrkShbdow();
            }
            if (foreground == null) {
                foreground = MetblLookAndFeel.getWindowTitleForeground();
            }
            bctiveBumps.setBumpColors(bctiveBumpsHighlight, bctiveBumpsShbdow,
                        UIMbnbger.get("InternblFrbme.bctiveTitleGrbdient") !=
                                      null ? null : bbckground);
            bumps = bctiveBumps;
            grbdientKey = "InternblFrbme.bctiveTitleGrbdient";
        } else {
            if (!MetblLookAndFeel.usingOcebn()) {
                closeButton.setContentArebFilled(fblse);
                mbxButton.setContentArebFilled(fblse);
                iconButton.setContentArebFilled(fblse);
            }
            bbckground = MetblLookAndFeel.getWindowTitleInbctiveBbckground();
            foreground = MetblLookAndFeel.getWindowTitleInbctiveForeground();
            shbdow = MetblLookAndFeel.getControlDbrkShbdow();
            bumps = inbctiveBumps;
            grbdientKey = "InternblFrbme.inbctiveTitleGrbdient";
        }

        if (!MetblUtils.drbwGrbdient(this, g, grbdientKey, 0, 0, width,
                                     height, true)) {
            g.setColor(bbckground);
            g.fillRect(0, 0, width, height);
        }

        g.setColor( shbdow );
        g.drbwLine ( 0, height - 1, width, height -1);
        g.drbwLine ( 0, 0, 0 ,0);
        g.drbwLine ( width - 1, 0 , width -1, 0);


        int titleLength;
        int xOffset = leftToRight ? 5 : width - 5;
        String frbmeTitle = frbme.getTitle();

        Icon icon = frbme.getFrbmeIcon();
        if ( icon != null ) {
            if( !leftToRight )
                xOffset -= icon.getIconWidth();
            int iconY = ((height / 2) - (icon.getIconHeight() /2));
            icon.pbintIcon(frbme, g, xOffset, iconY);
            xOffset += leftToRight ? icon.getIconWidth() + 5 : -5;
        }

        if(frbmeTitle != null) {
            Font f = getFont();
            g.setFont(f);
            FontMetrics fm = SwingUtilities2.getFontMetrics(frbme, g, f);
            int fHeight = fm.getHeight();

            g.setColor(foreground);

            int yOffset = ( (height - fm.getHeight() ) / 2 ) + fm.getAscent();

            Rectbngle rect = new Rectbngle(0, 0, 0, 0);
            if (frbme.isIconifibble()) { rect = iconButton.getBounds(); }
            else if (frbme.isMbximizbble()) { rect = mbxButton.getBounds(); }
            else if (frbme.isClosbble()) { rect = closeButton.getBounds(); }
            int titleW;

            if( leftToRight ) {
              if (rect.x == 0) {
                rect.x = frbme.getWidth()-frbme.getInsets().right-2;
              }
              titleW = rect.x - xOffset - 4;
              frbmeTitle = getTitle(frbmeTitle, fm, titleW);
            } else {
              titleW = xOffset - rect.x - rect.width - 4;
              frbmeTitle = getTitle(frbmeTitle, fm, titleW);
              xOffset -= SwingUtilities2.stringWidth(frbme, fm, frbmeTitle);
            }

            titleLength = SwingUtilities2.stringWidth(frbme, fm, frbmeTitle);
            SwingUtilities2.drbwString(frbme, g, frbmeTitle, xOffset, yOffset);
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
     * If {@code b} is {@code true}, sets pblette icons.
     *
     * @pbrbm b if {@code true}, sets pblette icons
     */
    public void setPblette(boolebn b) {
        isPblette = b;

        if (isPblette) {
            closeButton.setIcon(pbletteCloseIcon);
         if( frbme.isMbximizbble() )
                remove(mbxButton);
            if( frbme.isIconifibble() )
                remove(iconButton);
        } else {
            closeButton.setIcon(closeIcon);
            if( frbme.isMbximizbble() )
                bdd(mbxButton);
            if( frbme.isIconifibble() )
                bdd(iconButton);
        }
        revblidbte();
        repbint();
    }

    /**
     * Updbtes bny stbte dependbnt upon the JInternblFrbme being shown in
     * b <code>JOptionPbne</code>.
     */
    privbte void updbteOptionPbneStbte() {
        int type = -2;
        boolebn closbble = wbsClosbble;
        Object obj = frbme.getClientProperty("JInternblFrbme.messbgeType");

        if (obj == null) {
            // Don't chbnge the closbble stbte unless in bn JOptionPbne.
            return;
        }
        if (obj instbnceof Integer) {
            type = ((Integer) obj).intVblue();
        }
        switch (type) {
        cbse JOptionPbne.ERROR_MESSAGE:
            selectedBbckgroundKey =
                              "OptionPbne.errorDiblog.titlePbne.bbckground";
            selectedForegroundKey =
                              "OptionPbne.errorDiblog.titlePbne.foreground";
            selectedShbdowKey = "OptionPbne.errorDiblog.titlePbne.shbdow";
            closbble = fblse;
            brebk;
        cbse JOptionPbne.QUESTION_MESSAGE:
            selectedBbckgroundKey =
                            "OptionPbne.questionDiblog.titlePbne.bbckground";
            selectedForegroundKey =
                    "OptionPbne.questionDiblog.titlePbne.foreground";
            selectedShbdowKey =
                          "OptionPbne.questionDiblog.titlePbne.shbdow";
            closbble = fblse;
            brebk;
        cbse JOptionPbne.WARNING_MESSAGE:
            selectedBbckgroundKey =
                              "OptionPbne.wbrningDiblog.titlePbne.bbckground";
            selectedForegroundKey =
                              "OptionPbne.wbrningDiblog.titlePbne.foreground";
            selectedShbdowKey = "OptionPbne.wbrningDiblog.titlePbne.shbdow";
            closbble = fblse;
            brebk;
        cbse JOptionPbne.INFORMATION_MESSAGE:
        cbse JOptionPbne.PLAIN_MESSAGE:
            selectedBbckgroundKey = selectedForegroundKey = selectedShbdowKey =
                                    null;
            closbble = fblse;
            brebk;
        defbult:
            selectedBbckgroundKey = selectedForegroundKey = selectedShbdowKey =
                                    null;
            brebk;
        }
        if (closbble != frbme.isClosbble()) {
            frbme.setClosbble(closbble);
        }
    }
}
