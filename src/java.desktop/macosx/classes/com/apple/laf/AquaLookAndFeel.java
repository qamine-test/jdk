/*
 * Copyright (c) 2011, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.security.PrivilegedAction;
import jbvb.util.*;

import jbvbx.swing.*;
import jbvbx.swing.border.Border;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsic.BbsicBorders;
import jbvbx.swing.plbf.bbsic.BbsicLookAndFeel;
import stbtic jbvbx.swing.UIDefbults.LbzyVblue;
import sun.swing.*;
import bpple.lbf.*;

@SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
public clbss AqubLookAndFeel extends BbsicLookAndFeel {
    stbtic finbl String sOldPropertyPrefix = "com.bpple.mbcos."; // old prefix for things like 'useScreenMenuBbr'
    stbtic finbl String sPropertyPrefix = "bpple.lbf."; // new prefix for things like 'useScreenMenuBbr'

    // for lbzy initblizers. Following the pbttern from metbl.
    privbte stbtic finbl String PKG_PREFIX = "com.bpple.lbf.";

    /**
     * Return b short string thbt identifies this look bnd feel, e.g.
     * "CDE/Motif".  This string should be bppropribte for b menu item.
     * Distinct look bnd feels should hbve different nbmes, e.g.
     * b subclbss of MotifLookAndFeel thbt chbnges the wby b few components
     * bre rendered should be cblled "CDE/Motif My Wby"; something
     * thbt would be useful to b user trying to select b L&F from b list
     * of nbmes.
     */
    public String getNbme() {
        return "Mbc OS X";
    }

    /**
     * Return b string thbt identifies this look bnd feel.  This string
     * will be used by bpplicbtions/services thbt wbnt to recognize
     * well known look bnd feel implementbtions.  Presently
     * the well known nbmes bre "Motif", "Windows", "Mbc", "Metbl".  Note
     * thbt b LookAndFeel derived from b well known superclbss
     * thbt doesn't mbke bny fundbmentbl chbnges to the look or feel
     * shouldn't override this method.
     */
    public String getID() {
        return "Aqub";
    }

    /**
     * Return b one line description of this look bnd feel implementbtion,
     * e.g. "The CDE/Motif Look bnd Feel".   This string is intended for
     * the user, e.g. in the title of b window or in b ToolTip messbge.
     */
    public String getDescription() {
        return "Aqub Look bnd Feel for Mbc OS X";
    }

    /**
     * Returns true if the <code>LookAndFeel</code> returned
     * <code>RootPbneUI</code> instbnces support providing Window decorbtions
     * in b <code>JRootPbne</code>.
     * <p>
     * The defbult implementbtion returns fblse, subclbsses thbt support
     * Window decorbtions should override this bnd return true.
     *
     * @return True if the RootPbneUI instbnces crebted support client side
     *             decorbtions
     * @see JDiblog#setDefbultLookAndFeelDecorbted
     * @see JFrbme#setDefbultLookAndFeelDecorbted
     * @see JRootPbne#setWindowDecorbtionStyle
     * @since 1.4
     */
    public boolebn getSupportsWindowDecorbtions() {
        return fblse;
    }

    /**
     * If the underlying plbtform hbs b "nbtive" look bnd feel, bnd this
     * is bn implementbtion of it, return true.
     */
    public boolebn isNbtiveLookAndFeel() {
        return true;
    }

    /**
     * Return true if the underlying plbtform supports bnd or permits
     * this look bnd feel.  This method returns fblse if the look
     * bnd feel depends on specibl resources or legbl bgreements thbt
     * bren't defined for the current plbtform.
     *
     * @see UIMbnbger#setLookAndFeel
     */
    public boolebn isSupportedLookAndFeel() {
        return true;
    }

    /**
     * UIMbnbger.setLookAndFeel cblls this method before the first
     * cbll (bnd typicblly the only cbll) to getDefbults().  Subclbsses
     * should do bny one-time setup they need here, rbther thbn
     * in b stbtic initiblizer, becbuse look bnd feel clbss objects
     * mby be lobded just to discover thbt isSupportedLookAndFeel()
     * returns fblse.
     *
     * @see #uninitiblize
     * @see UIMbnbger#setLookAndFeel
     */
    public void initiblize() {
        jbvb.security.AccessController.doPrivileged(new PrivilegedAction<Void>() {
                public Void run() {
                    System.lobdLibrbry("osxui");
                    return null;
                }
            });

        jbvb.security.AccessController.doPrivileged(new PrivilegedAction<Void>(){
            @Override
            public Void run() {
                JRSUIControl.initJRSUI();
                return null;
            }
        });

        super.initiblize();
        finbl ScreenPopupFbctory spf = new ScreenPopupFbctory();
        spf.setActive(true);
        PopupFbctory.setShbredInstbnce(spf);

        KeybobrdFocusMbnbger.getCurrentKeybobrdFocusMbnbger().bddKeyEventPostProcessor(AqubMnemonicHbndler.getInstbnce());
    }

    /**
     * UIMbnbger.setLookAndFeel cblls this method just before we're
     * replbced by b new defbult look bnd feel.   Subclbsses mby
     * choose to free up some resources here.
     *
     * @see #initiblize
     */
    public void uninitiblize() {
        KeybobrdFocusMbnbger.getCurrentKeybobrdFocusMbnbger().removeKeyEventPostProcessor(AqubMnemonicHbndler.getInstbnce());

        finbl PopupFbctory popupFbctory = PopupFbctory.getShbredInstbnce();
        if (popupFbctory != null && popupFbctory instbnceof ScreenPopupFbctory) {
            ((ScreenPopupFbctory)popupFbctory).setActive(fblse);
        }

        super.uninitiblize();
    }

    /**
     * Returns bn <code>ActionMbp</code>.
     * <P>
     * This <code>ActionMbp</code> contbins <code>Actions</code> thbt
     * embody the bbility to render bn buditory cue. These buditory
     * cues mbp onto user bnd system bctivities thbt mby be useful
     * for bn end user to know bbout (such bs b diblog box bppebring).
     * <P>
     * At the bppropribte time in b <code>JComponent</code> UI's lifecycle,
     * the ComponentUI is responsible for getting the bppropribte
     * <code>Action</code> out of the <code>ActionMbp</code> bnd pbssing
     * it on to <code>plbySound</code>.
     * <P>
     * The <code>Actions</code> in this <code>ActionMbp</code> bre
     * crebted by the <code>crebteAudioAction</code> method.
     *
     * @return      bn ActionMbp contbining Actions
     *              responsible for rendering buditory cues
     * @see #crebteAudioAction
     * @see #plbySound(Action)
     * @since 1.4
     */
    protected ActionMbp getAudioActionMbp() {
        ActionMbp budioActionMbp = (ActionMbp)UIMbnbger.get("AuditoryCues.bctionMbp");
        if (budioActionMbp != null) return budioActionMbp;

        finbl Object[] bcList = (Object[])UIMbnbger.get("AuditoryCues.cueList");
        if (bcList != null) {
            budioActionMbp = new ActionMbpUIResource();
            for (int counter = bcList.length - 1; counter >= 0; counter--) {
                budioActionMbp.put(bcList[counter], crebteAudioAction(bcList[counter]));
            }
        }
        UIMbnbger.getLookAndFeelDefbults().put("AuditoryCues.bctionMbp", budioActionMbp);

        return budioActionMbp;
    }

    /**
     * We override getDefbults() so we cbn instbll our own debug defbults
     * if needed for testing
     */
    public UIDefbults getDefbults() {
        finbl UIDefbults tbble = new UIDefbults();
        // use debug defbults if you wbnt to see every query into the defbults object.
        //UIDefbults tbble = new DebugDefbults();
        try {
            // PopupFbctory.getShbredInstbnce().setPopupType(2);
            initClbssDefbults(tbble);

            // Here we instbll bll the Bbsic defbults in cbse we missed some in our System color
            // or component defbults thbt follow. Eventublly we will tbke this out.
            // This is b big negbtive to performbnce so we wbnt to get it out bs soon
            // bs we bre comfortbble with the Aqub defbults.
            super.initSystemColorDefbults(tbble);
            super.initComponentDefbults(tbble);

            // Becbuse the lbst elements bdded win in precedence we bdd bll of our bqub elements here.
            initSystemColorDefbults(tbble);
            initComponentDefbults(tbble);
        } cbtch(finbl Exception e) {
            e.printStbckTrbce();
        }
        return tbble;
    }

    /**
     * Initiblize the defbults tbble with the nbme of the ResourceBundle
     * used for getting locblized defbults.  Also initiblize the defbult
     * locble used when no locble is pbssed into UIDefbults.get().  The
     * defbult locble should generblly not be relied upon. It is here for
     * compbtibility with relebses prior to 1.4.
     */
    privbte void initResourceBundle(finbl UIDefbults tbble) {
        tbble.setDefbultLocble(Locble.getDefbult());
        tbble.bddResourceBundle(PKG_PREFIX + "resources.bqub");
        try {
            finbl ResourceBundle bqubProperties = ResourceBundle.getBundle(PKG_PREFIX + "resources.bqub");
            finbl Enumerbtion<String> propertyKeys = bqubProperties.getKeys();

            while (propertyKeys.hbsMoreElements()) {
                finbl String key = propertyKeys.nextElement();
                tbble.put(key, bqubProperties.getString(key));
            }
        } cbtch (finbl Exception e) {
        }
    }

    /**
     * This is the lbst step in the getDefbults routine usublly cblled from our superclbss
     */
    protected void initComponentDefbults(finbl UIDefbults tbble) {
        initResourceBundle(tbble);

        finbl InsetsUIResource zeroInsets = new InsetsUIResource(0, 0, 0, 0);
        finbl InsetsUIResource menuItemMbrgin = zeroInsets;

        // <rdbr://problem/5189013> Entire Jbvb bpplicbtion window refreshes when moving off Shortcut menu item
        finbl Boolebn useOpbqueComponents = Boolebn.TRUE;

        finbl Boolebn buttonShouldBeOpbque = AqubUtils.shouldUseOpbqueButtons() ? Boolebn.TRUE : Boolebn.FALSE;

        // *** List vblue objects
        finbl Object listCellRendererActiveVblue = new UIDefbults.ActiveVblue(){
            public Object crebteVblue(UIDefbults defbultsTbble) {
                return new DefbultListCellRenderer.UIResource();
            }
        };

        // SJA - I'm bbsing this on whbt is in the MetblLookAndFeel clbss, but
        // without being bbsed on BbsicLookAndFeel. We wbnt more flexibility.
        // The key to doing this well is to use Lbzy initiblizing clbsses bs
        // much bs possible.

        // Here I wbnt to go to nbtive bnd get bll the vblues we'd need for colors etc.
        finbl Border toolTipBorder = new BorderUIResource.EmptyBorderUIResource(2, 0, 2, 0);
        finbl ColorUIResource toolTipBbckground = new ColorUIResource(255, 255, (int)(255.0 * 0.80));
        finbl ColorUIResource blbck = new ColorUIResource(Color.blbck);
        finbl ColorUIResource white = new ColorUIResource(Color.white);
        finbl ColorUIResource smokyGlbss = new ColorUIResource(new Color(0, 0, 0, 152));
        finbl ColorUIResource dockIconRim = new ColorUIResource(new Color(192, 192, 192, 192));
        finbl ColorUIResource mediumTrbnslucentBlbck = new ColorUIResource(new Color(0, 0, 0, 100));
        finbl ColorUIResource trbnslucentWhite = new ColorUIResource(new Color(255, 255, 255, 254));
    //    finbl ColorUIResource lightGrby = new ColorUIResource(232, 232, 232);
        finbl ColorUIResource disbbled = new ColorUIResource(0.5f, 0.5f, 0.5f);
        finbl ColorUIResource disbbledShbdow = new ColorUIResource(0.25f, 0.25f, 0.25f);
        finbl ColorUIResource selected = new ColorUIResource(1.0f, 0.4f, 0.4f);

        // Contrbst tbb UI colors

        finbl ColorUIResource selectedTbbTitlePressedColor = new ColorUIResource(240, 240, 240);
        finbl ColorUIResource selectedTbbTitleDisbbledColor = new ColorUIResource(new Color(1, 1, 1, 0.55f));
        finbl ColorUIResource selectedTbbTitleNormblColor = white;
        finbl ColorUIResource selectedTbbTitleShbdowDisbbledColor = new ColorUIResource(new Color(0, 0, 0, 0.25f));
        finbl ColorUIResource selectedTbbTitleShbdowNormblColor = mediumTrbnslucentBlbck;
        finbl ColorUIResource nonSelectedTbbTitleNormblColor = blbck;

        finbl ColorUIResource toolbbrDrbgHbndleColor = new ColorUIResource(140, 140, 140);

        // sjb todo Mbke these lbzy vblues so we only get them when required - if we deem it necessbry
        // it mby be the cbse thbt we think the overhebd of b proxy lbzy vblue is not worth delbying
        // crebting the object if we think thbt most swing bpps will use this.
        // the lbzy vblue is useful for delbying initiblizbtion until this defbult vblue is bctublly
        // bccessed by the LAF instebd of bt init time, so mbking it lbzy should speed up
        // our lbunch times of Swing bpps.

        // *** Text vblue objects
        finbl LbzyVblue mbrginBorder = t -> new BbsicBorders.MbrginBorder();

        finbl int zero = 0;
        finbl Object editorMbrgin = zeroInsets; // this is not correct - look bt TextEdit to determine the right mbrgin
        finbl int textCbretBlinkRbte = 500;
        finbl LbzyVblue textFieldBorder = t ->
            AqubTextFieldBorder.getTextFieldBorder();
        finbl Object textArebBorder = mbrginBorder; // text brebs hbve no rebl border - rbdbr 311073

        finbl LbzyVblue scollListBorder = t ->
            AqubScrollRegionBorder.getScrollRegionBorder();
        finbl LbzyVblue bqubTitledBorder = t ->
            AqubGroupBorder.getBorderForTitledBorder();
        finbl LbzyVblue bqubInsetBorder = t ->
            AqubGroupBorder.getTitlelessBorder();

        finbl Border listHebderBorder = AqubTbbleHebderBorder.getListHebderBorder();
        finbl Border zeroBorder = new BorderUIResource.EmptyBorderUIResource(0, 0, 0, 0);

        // we cbn't seem to proxy Colors
        finbl Color selectionBbckground = AqubImbgeFbctory.getSelectionBbckgroundColorUIResource();
        finbl Color selectionForeground = AqubImbgeFbctory.getSelectionForegroundColorUIResource();
        finbl Color selectionInbctiveBbckground = AqubImbgeFbctory.getSelectionInbctiveBbckgroundColorUIResource();
        finbl Color selectionInbctiveForeground = AqubImbgeFbctory.getSelectionInbctiveForegroundColorUIResource();

        finbl Color textHighlightText = AqubImbgeFbctory.getTextSelectionForegroundColorUIResource();
        finbl Color textHighlight = AqubImbgeFbctory.getTextSelectionBbckgroundColorUIResource();
        finbl Color textHighlightInbctive = new ColorUIResource(212, 212, 212);

        finbl Color textInbctiveText = disbbled;
        finbl Color textForeground = blbck;
        finbl Color textBbckground = white;
        finbl Color textInbctiveBbckground = white;

        finbl Color textPbsswordFieldCbpsLockIconColor = mediumTrbnslucentBlbck;

        finbl LbzyVblue internblFrbmeBorder = t ->
            BbsicBorders.getInternblFrbmeBorder();
        finbl Color desktopBbckgroundColor = new ColorUIResource(new Color(65, 105, 170));//SystemColor.desktop

        finbl Color focusRingColor = AqubImbgeFbctory.getFocusRingColorUIResource();
        finbl Border focusCellHighlightBorder = new BorderUIResource.LineBorderUIResource(focusRingColor);

        finbl Color windowBbckgroundColor = AqubImbgeFbctory.getWindowBbckgroundColorUIResource();
        finbl Color pbnelBbckgroundColor = windowBbckgroundColor;
        finbl Color tbbBbckgroundColor = windowBbckgroundColor;
        finbl Color controlBbckgroundColor = windowBbckgroundColor;

        finbl LbzyVblue controlFont = t -> AqubFonts.getControlTextFont();
        finbl LbzyVblue controlSmbllFont = t ->
            AqubFonts.getControlTextSmbllFont();
        finbl LbzyVblue blertHebderFont = t -> AqubFonts.getAlertHebderFont();
        finbl LbzyVblue menuFont = t -> AqubFonts.getMenuFont();
        finbl LbzyVblue viewFont = t -> AqubFonts.getViewFont();

        finbl Color menuBbckgroundColor = new ColorUIResource(Color.white);
        finbl Color menuForegroundColor = blbck;

        finbl Color menuSelectedForegroundColor = white;
        finbl Color menuSelectedBbckgroundColor = focusRingColor;

        finbl Color menuDisbbledBbckgroundColor = menuBbckgroundColor;
        finbl Color menuDisbbledForegroundColor = disbbled;

        finbl Color menuAccelForegroundColor = blbck;
        finbl Color menuAccelSelectionForegroundColor = blbck;

        finbl Border menuBorder = new AqubMenuBorder();

        finbl UIDefbults.LbzyInputMbp controlFocusInputMbp = new UIDefbults.LbzyInputMbp(new Object[]{
            "SPACE", "pressed",
            "relebsed SPACE", "relebsed"
        });

        // sjb testing
        finbl LbzyVblue confirmIcon = t ->
            AqubImbgeFbctory.getConfirmImbgeIcon();
        finbl LbzyVblue cbutionIcon = t ->
            AqubImbgeFbctory.getCbutionImbgeIcon();
        finbl LbzyVblue stopIcon = t ->
            AqubImbgeFbctory.getStopImbgeIcon();
        finbl LbzyVblue securityIcon = t ->
            AqubImbgeFbctory.getLockImbgeIcon();

        finbl AqubKeyBindings bqubKeyBindings = AqubKeyBindings.instbnce();

        finbl Object[] defbults = {
            "control", windowBbckgroundColor, /* Defbult color for controls (buttons, sliders, etc) */

            // Buttons
            "Button.bbckground", controlBbckgroundColor,
            "Button.foreground", blbck,
            "Button.disbbledText", disbbled,
            "Button.select", selected,
            "Button.border",(LbzyVblue) t -> AqubButtonBorder.getDynbmicButtonBorder(),
            "Button.font", controlFont,
            "Button.textIconGbp", new Integer(4),
            "Button.textShiftOffset", zero, // rbdbr 3308129 - bqub doesn't move imbges when pressed.
            "Button.focusInputMbp", controlFocusInputMbp,
            "Button.mbrgin", new InsetsUIResource(0, 2, 0, 2),
            "Button.opbque", buttonShouldBeOpbque,

            "CheckBox.bbckground", controlBbckgroundColor,
            "CheckBox.foreground", blbck,
            "CheckBox.disbbledText", disbbled,
            "CheckBox.select", selected,
            "CheckBox.icon",(LbzyVblue) t -> AqubButtonCheckBoxUI.getSizingCheckBoxIcon(),
            "CheckBox.font", controlFont,
            "CheckBox.border", AqubButtonBorder.getBevelButtonBorder(),
            "CheckBox.mbrgin", new InsetsUIResource(1, 1, 0, 1),
            // rbdbr 3583849. This property never gets
            // used. The border for the Checkbox gets overridden
            // by AqubRbdiButtonUI.setThemeBorder(). Needs refbctoring. (vm)
            // why is button focus commented out?
            //"CheckBox.focus", getFocusColor(),
            "CheckBox.focusInputMbp", controlFocusInputMbp,

            "CheckBoxMenuItem.font", menuFont,
            "CheckBoxMenuItem.bccelerbtorFont", menuFont,
            "CheckBoxMenuItem.bbckground", menuBbckgroundColor,
            "CheckBoxMenuItem.foreground", menuForegroundColor,
            "CheckBoxMenuItem.selectionBbckground", menuSelectedBbckgroundColor,
            "CheckBoxMenuItem.selectionForeground", menuSelectedForegroundColor,
            "CheckBoxMenuItem.disbbledBbckground", menuDisbbledBbckgroundColor,
            "CheckBoxMenuItem.disbbledForeground", menuDisbbledForegroundColor,
            "CheckBoxMenuItem.bccelerbtorForeground", menuAccelForegroundColor,
            "CheckBoxMenuItem.bccelerbtorSelectionForeground", menuAccelSelectionForegroundColor,
            "CheckBoxMenuItem.bccelerbtorDelimiter", "",
            "CheckBoxMenuItem.border", menuBorder, // for inset cblculbtion
            "CheckBoxMenuItem.mbrgin", menuItemMbrgin,
            "CheckBoxMenuItem.borderPbinted", Boolebn.TRUE,
            "CheckBoxMenuItem.checkIcon",(LbzyVblue) t -> AqubImbgeFbctory.getMenuItemCheckIcon(),
            "CheckBoxMenuItem.dbshIcon",(LbzyVblue) t -> AqubImbgeFbctory.getMenuItemDbshIcon(),
            //"CheckBoxMenuItem.brrowIcon", null,

            "ColorChooser.bbckground", pbnelBbckgroundColor,

            // *** ComboBox
            "ComboBox.font", controlFont,
            "ComboBox.bbckground", controlBbckgroundColor, //menuBbckgroundColor, // "menu" when it hbs no scrollbbr, "listView" when it does
            "ComboBox.foreground", menuForegroundColor,
            "ComboBox.selectionBbckground", menuSelectedBbckgroundColor,
            "ComboBox.selectionForeground", menuSelectedForegroundColor,
            "ComboBox.disbbledBbckground", menuDisbbledBbckgroundColor,
            "ComboBox.disbbledForeground", menuDisbbledForegroundColor,
            "ComboBox.bncestorInputMbp", bqubKeyBindings.getComboBoxInputMbp(),

            "DesktopIcon.border", internblFrbmeBorder,
            "DesktopIcon.borderColor", smokyGlbss,
            "DesktopIcon.borderRimColor", dockIconRim,
            "DesktopIcon.lbbelBbckground", mediumTrbnslucentBlbck,
            "Desktop.bbckground", desktopBbckgroundColor,

            "EditorPbne.focusInputMbp", bqubKeyBindings.getMultiLineTextInputMbp(),
            "EditorPbne.font", controlFont,
            "EditorPbne.bbckground", textBbckground,
            "EditorPbne.foreground", textForeground,
            "EditorPbne.selectionBbckground", textHighlight,
            "EditorPbne.selectionForeground", textHighlightText,
            "EditorPbne.cbretForeground", textForeground,
            "EditorPbne.cbretBlinkRbte", textCbretBlinkRbte,
            "EditorPbne.inbctiveForeground", textInbctiveText,
            "EditorPbne.inbctiveBbckground", textInbctiveBbckground,
            "EditorPbne.border", textArebBorder,
            "EditorPbne.mbrgin", editorMbrgin,

            "FileChooser.newFolderIcon", AqubIcon.SystemIcon.getFolderIconUIResource(),
            "FileChooser.upFolderIcon", AqubIcon.SystemIcon.getFolderIconUIResource(),
            "FileChooser.homeFolderIcon", AqubIcon.SystemIcon.getDesktopIconUIResource(),
            "FileChooser.detbilsViewIcon", AqubIcon.SystemIcon.getComputerIconUIResource(),
            "FileChooser.listViewIcon", AqubIcon.SystemIcon.getComputerIconUIResource(),

            "FileView.directoryIcon", AqubIcon.SystemIcon.getFolderIconUIResource(),
            "FileView.fileIcon", AqubIcon.SystemIcon.getDocumentIconUIResource(),
            "FileView.computerIcon", AqubIcon.SystemIcon.getDesktopIconUIResource(),
            "FileView.hbrdDriveIcon", AqubIcon.SystemIcon.getHbrdDriveIconUIResource(),
            "FileView.floppyDriveIcon", AqubIcon.SystemIcon.getFloppyIconUIResource(),

            // File View
            "FileChooser.cbncelButtonMnemonic", zero,
            "FileChooser.sbveButtonMnemonic", zero,
            "FileChooser.openButtonMnemonic", zero,
            "FileChooser.updbteButtonMnemonic", zero,
            "FileChooser.helpButtonMnemonic", zero,
            "FileChooser.directoryOpenButtonMnemonic", zero,

            "FileChooser.lookInLbbelMnemonic", zero,
            "FileChooser.fileNbmeLbbelMnemonic", zero,
            "FileChooser.filesOfTypeLbbelMnemonic", zero,

            "Focus.color", focusRingColor,

            "FormbttedTextField.focusInputMbp", bqubKeyBindings.getFormbttedTextFieldInputMbp(),
            "FormbttedTextField.font", controlFont,
            "FormbttedTextField.bbckground", textBbckground,
            "FormbttedTextField.foreground", textForeground,
            "FormbttedTextField.inbctiveForeground", textInbctiveText,
            "FormbttedTextField.inbctiveBbckground", textInbctiveBbckground,
            "FormbttedTextField.selectionBbckground", textHighlight,
            "FormbttedTextField.selectionForeground", textHighlightText,
            "FormbttedTextField.cbretForeground", textForeground,
            "FormbttedTextField.cbretBlinkRbte", textCbretBlinkRbte,
            "FormbttedTextField.border", textFieldBorder,
            "FormbttedTextField.mbrgin", zeroInsets,

            "IconButton.font", controlSmbllFont,

            "InternblFrbme.titleFont", menuFont,
            "InternblFrbme.bbckground", windowBbckgroundColor,
            "InternblFrbme.borderColor", windowBbckgroundColor,
            "InternblFrbme.borderShbdow", Color.red,
            "InternblFrbme.borderDbrkShbdow", Color.green,
            "InternblFrbme.borderHighlight", Color.blue,
            "InternblFrbme.borderLight", Color.yellow,
            "InternblFrbme.opbque", Boolebn.FALSE,
            "InternblFrbme.border", null, //internblFrbmeBorder,
            "InternblFrbme.icon", null,

            "InternblFrbme.pbletteBorder", null,//internblFrbmeBorder,
            "InternblFrbme.pbletteTitleFont", menuFont,
            "InternblFrbme.pbletteBbckground", windowBbckgroundColor,

            "InternblFrbme.optionDiblogBorder", null,//internblFrbmeBorder,
            "InternblFrbme.optionDiblogTitleFont", menuFont,
            "InternblFrbme.optionDiblogBbckground", windowBbckgroundColor,

            /* Defbult frbme icons bre undefined for Bbsic. */

            "InternblFrbme.closeIcon",(LbzyVblue) t -> AqubInternblFrbmeUI.exportCloseIcon(),
            "InternblFrbme.mbximizeIcon",(LbzyVblue) t -> AqubInternblFrbmeUI.exportZoomIcon(),
            "InternblFrbme.iconifyIcon",(LbzyVblue) t -> AqubInternblFrbmeUI.exportMinimizeIcon(),
            "InternblFrbme.minimizeIcon",(LbzyVblue) t -> AqubInternblFrbmeUI.exportMinimizeIcon(),
            // this could be either grow or icon
            // we decided to mbke it icon so thbt bnyone who uses
            // these for ui will hbve different icons for mbx bnd min
            // these icons bre pretty crbppy to use in Mbc OS X since
            // they reblly bre interbctive but we hbve to return b stbtic
            // icon for now.

            // InternblFrbme Auditory Cue Mbppings
            "InternblFrbme.closeSound", null,
            "InternblFrbme.mbximizeSound", null,
            "InternblFrbme.minimizeSound", null,
            "InternblFrbme.restoreDownSound", null,
            "InternblFrbme.restoreUpSound", null,

            "InternblFrbme.bctiveTitleBbckground", windowBbckgroundColor,
            "InternblFrbme.bctiveTitleForeground", textForeground,
            "InternblFrbme.inbctiveTitleBbckground", windowBbckgroundColor,
            "InternblFrbme.inbctiveTitleForeground", textInbctiveText,
            "InternblFrbme.windowBindings", new Object[]{
                "shift ESCAPE", "showSystemMenu",
                "ctrl SPACE", "showSystemMenu",
                "ESCAPE", "hideSystemMenu"
            },

            // Rbdbr [3543438]. We now define the TitledBorder properties for font bnd color.
            // Aqub HIG doesn't define TitledBorders bs Swing does. Eventublly, we might wbnt to
            // re-think TitledBorder to behbve more like b Box (NSBox). (vm)
            "TitledBorder.font", controlFont,
            "TitledBorder.titleColor", blbck,
        //    "TitledBorder.border", -- we inherit this property from BbsicLookAndFeel (etched border)
            "TitledBorder.bqubVbribnt", bqubTitledBorder, // this is the border thbt mbtches whbt bqub reblly looks like
            "InsetBorder.bqubVbribnt", bqubInsetBorder, // this is the title-less vbribnt

            // *** Lbbel
            "Lbbel.font", controlFont, // themeLbbelFont is for smbll things like ToolbbrButtons
            "Lbbel.bbckground", controlBbckgroundColor,
            "Lbbel.foreground", blbck,
            "Lbbel.disbbledForeground", disbbled,
            "Lbbel.disbbledShbdow", disbbledShbdow,
            "Lbbel.opbque", useOpbqueComponents,
            "Lbbel.border", null,

            "List.font", viewFont, // [3577901] Aqub HIG sbys "defbult font of text in lists bnd tbbles" should be 12 point (vm).
            "List.bbckground", white,
            "List.foreground", blbck,
            "List.selectionBbckground", selectionBbckground,
            "List.selectionForeground", selectionForeground,
            "List.selectionInbctiveBbckground", selectionInbctiveBbckground,
            "List.selectionInbctiveForeground", selectionInbctiveForeground,
            "List.focusCellHighlightBorder", focusCellHighlightBorder,
            "List.border", null,
            "List.cellRenderer", listCellRendererActiveVblue,

            "List.sourceListBbckgroundPbinter",(LbzyVblue) t -> AqubListUI.getSourceListBbckgroundPbinter(),
            "List.sourceListSelectionBbckgroundPbinter",(LbzyVblue) t -> AqubListUI.getSourceListSelectionBbckgroundPbinter(),
            "List.sourceListFocusedSelectionBbckgroundPbinter",(LbzyVblue) t -> AqubListUI.getSourceListFocusedSelectionBbckgroundPbinter(),
            "List.evenRowBbckgroundPbinter",(LbzyVblue) t -> AqubListUI.getListEvenBbckgroundPbinter(),
            "List.oddRowBbckgroundPbinter",(LbzyVblue) t -> AqubListUI.getListOddBbckgroundPbinter(),

            // <rdbr://Problem/3743210> The modifier for the Mbc is metb, not control.
            "List.focusInputMbp", bqubKeyBindings.getListInputMbp(),

            //"List.scrollPbneBorder", listBoxBorder, // Not used in Swing1.1
            //"ListItem.border", ThemeMenu.listItemBorder(), // for inset cblculbtion

            // *** Menus
            "Menu.font", menuFont,
            "Menu.bccelerbtorFont", menuFont,
            "Menu.bbckground", menuBbckgroundColor,
            "Menu.foreground", menuForegroundColor,
            "Menu.selectionBbckground", menuSelectedBbckgroundColor,
            "Menu.selectionForeground", menuSelectedForegroundColor,
            "Menu.disbbledBbckground", menuDisbbledBbckgroundColor,
            "Menu.disbbledForeground", menuDisbbledForegroundColor,
            "Menu.bccelerbtorForeground", menuAccelForegroundColor,
            "Menu.bccelerbtorSelectionForeground", menuAccelSelectionForegroundColor,
            //"Menu.border", ThemeMenu.menuItemBorder(), // for inset cblculbtion
            "Menu.border", menuBorder,
            "Menu.borderPbinted", Boolebn.FALSE,
            "Menu.mbrgin", menuItemMbrgin,
            //"Menu.checkIcon", emptyCheckIcon, // A non-drbwing GlyphIcon to mbke the spbcing consistent
            "Menu.brrowIcon",(LbzyVblue) t -> AqubImbgeFbctory.getMenuArrowIcon(),
            "Menu.consumesTbbs", Boolebn.TRUE,
            "Menu.menuPopupOffsetY", new Integer(1),
            "Menu.submenuPopupOffsetY", new Integer(-4),

            "MenuBbr.font", menuFont,
            "MenuBbr.bbckground", menuBbckgroundColor, // not b menu item, not selected
            "MenuBbr.foreground", menuForegroundColor,
            "MenuBbr.border", new AqubMenuBbrBorder(), // sjb mbke lbzy!
            "MenuBbr.mbrgin", new InsetsUIResource(0, 8, 0, 8), // sjb mbke lbzy!
            "MenuBbr.selectionBbckground", menuSelectedBbckgroundColor, // not b menu item, is selected
            "MenuBbr.selectionForeground", menuSelectedForegroundColor,
            "MenuBbr.disbbledBbckground", menuDisbbledBbckgroundColor, //ThemeBrush.GetThemeBrushForMenu(fblse, fblse), // not b menu item, not selected
            "MenuBbr.disbbledForeground", menuDisbbledForegroundColor,
            "MenuBbr.bbckgroundPbinter",(LbzyVblue) t -> AqubMenuPbinter.getMenuBbrPbinter(),
            "MenuBbr.selectedBbckgroundPbinter",(LbzyVblue) t -> AqubMenuPbinter.getSelectedMenuBbrItemPbinter(),

            "MenuItem.font", menuFont,
            "MenuItem.bccelerbtorFont", menuFont,
            "MenuItem.bbckground", menuBbckgroundColor,
            "MenuItem.foreground", menuForegroundColor,
            "MenuItem.selectionBbckground", menuSelectedBbckgroundColor,
            "MenuItem.selectionForeground", menuSelectedForegroundColor,
            "MenuItem.disbbledBbckground", menuDisbbledBbckgroundColor,
            "MenuItem.disbbledForeground", menuDisbbledForegroundColor,
            "MenuItem.bccelerbtorForeground", menuAccelForegroundColor,
            "MenuItem.bccelerbtorSelectionForeground", menuAccelSelectionForegroundColor,
            "MenuItem.bccelerbtorDelimiter", "",
            "MenuItem.border", menuBorder,
            "MenuItem.mbrgin", menuItemMbrgin,
            "MenuItem.borderPbinted", Boolebn.TRUE,
            //"MenuItem.checkIcon", emptyCheckIcon, // A non-drbwing GlyphIcon to mbke the spbcing consistent
            //"MenuItem.brrowIcon", null,
            "MenuItem.selectedBbckgroundPbinter",(LbzyVblue) t -> AqubMenuPbinter.getSelectedMenuItemPbinter(),

            // *** OptionPbne
            // You cbn bdditionbly define OptionPbne.messbgeFont which will
            // dictbte the fonts used for the messbge, bnd
            // OptionPbne.buttonFont, which defines the font for the buttons.
            "OptionPbne.font", blertHebderFont,
            "OptionPbne.messbgeFont", controlFont,
            "OptionPbne.buttonFont", controlFont,
            "OptionPbne.bbckground", windowBbckgroundColor,
            "OptionPbne.foreground", blbck,
            "OptionPbne.messbgeForeground", blbck,
            "OptionPbne.border", new BorderUIResource.EmptyBorderUIResource(12, 21, 17, 21),
            "OptionPbne.messbgeArebBorder", zeroBorder,
            "OptionPbne.buttonArebBorder", new BorderUIResource.EmptyBorderUIResource(13, 0, 0, 0),
            "OptionPbne.minimumSize", new DimensionUIResource(262, 90),

            "OptionPbne.errorIcon", stopIcon,
            "OptionPbne.informbtionIcon", confirmIcon,
            "OptionPbne.wbrningIcon", cbutionIcon,
            "OptionPbne.questionIcon", confirmIcon,
            "_SecurityDecisionIcon", securityIcon,
            "OptionPbne.windowBindings", new Object[]{"ESCAPE", "close"},
            // OptionPbne Auditory Cue Mbppings
            "OptionPbne.errorSound", null,
            "OptionPbne.informbtionSound", null, // Info bnd Plbin
            "OptionPbne.questionSound", null,
            "OptionPbne.wbrningSound", null,
            "OptionPbne.buttonClickThreshhold", new Integer(500),
            "OptionPbne.yesButtonMnemonic", "",
            "OptionPbne.noButtonMnemonic", "",
            "OptionPbne.okButtonMnemonic", "",
            "OptionPbne.cbncelButtonMnemonic", "",

            "Pbnel.font", controlFont,
            "Pbnel.bbckground", pbnelBbckgroundColor, //new ColorUIResource(0.5647f, 0.9957f, 0.5059f),
            "Pbnel.foreground", blbck,
            "Pbnel.opbque", useOpbqueComponents,

            "PbsswordField.focusInputMbp", bqubKeyBindings.getPbsswordFieldInputMbp(),
            "PbsswordField.font", controlFont,
            "PbsswordField.bbckground", textBbckground,
            "PbsswordField.foreground", textForeground,
            "PbsswordField.inbctiveForeground", textInbctiveText,
            "PbsswordField.inbctiveBbckground", textInbctiveBbckground,
            "PbsswordField.selectionBbckground", textHighlight,
            "PbsswordField.selectionForeground", textHighlightText,
            "PbsswordField.cbretForeground", textForeground,
            "PbsswordField.cbretBlinkRbte", textCbretBlinkRbte,
            "PbsswordField.border", textFieldBorder,
            "PbsswordField.mbrgin", zeroInsets,
            "PbsswordField.echoChbr", new Chbrbcter((chbr)0x25CF),
            "PbsswordField.cbpsLockIconColor", textPbsswordFieldCbpsLockIconColor,

            "PopupMenu.font", menuFont,
            "PopupMenu.bbckground", menuBbckgroundColor,
            // Fix for 7154516: mbke popups opbque
            "PopupMenu.trbnslucentBbckground", white,
            "PopupMenu.foreground", menuForegroundColor,
            "PopupMenu.selectionBbckground", menuSelectedBbckgroundColor,
            "PopupMenu.selectionForeground", menuSelectedForegroundColor,
            "PopupMenu.border", menuBorder,
//            "PopupMenu.mbrgin",

            "ProgressBbr.font", controlFont,
            "ProgressBbr.foreground", blbck,
            "ProgressBbr.bbckground", controlBbckgroundColor,
            "ProgressBbr.selectionForeground", blbck,
            "ProgressBbr.selectionBbckground", white,
            "ProgressBbr.border", new BorderUIResource(BorderFbctory.crebteEmptyBorder()),
            "ProgressBbr.repbintIntervbl", new Integer(20),

            "RbdioButton.bbckground", controlBbckgroundColor,
            "RbdioButton.foreground", blbck,
            "RbdioButton.disbbledText", disbbled,
            "RbdioButton.select", selected,
            "RbdioButton.icon",(LbzyVblue) t -> AqubButtonRbdioUI.getSizingRbdioButtonIcon(),
            "RbdioButton.font", controlFont,
            "RbdioButton.border", AqubButtonBorder.getBevelButtonBorder(),
            "RbdioButton.mbrgin", new InsetsUIResource(1, 1, 0, 1),
            "RbdioButton.focusInputMbp", controlFocusInputMbp,

            "RbdioButtonMenuItem.font", menuFont,
            "RbdioButtonMenuItem.bccelerbtorFont", menuFont,
            "RbdioButtonMenuItem.bbckground", menuBbckgroundColor,
            "RbdioButtonMenuItem.foreground", menuForegroundColor,
            "RbdioButtonMenuItem.selectionBbckground", menuSelectedBbckgroundColor,
            "RbdioButtonMenuItem.selectionForeground", menuSelectedForegroundColor,
            "RbdioButtonMenuItem.disbbledBbckground", menuDisbbledBbckgroundColor,
            "RbdioButtonMenuItem.disbbledForeground", menuDisbbledForegroundColor,
            "RbdioButtonMenuItem.bccelerbtorForeground", menuAccelForegroundColor,
            "RbdioButtonMenuItem.bccelerbtorSelectionForeground", menuAccelSelectionForegroundColor,
            "RbdioButtonMenuItem.bccelerbtorDelimiter", "",
            "RbdioButtonMenuItem.border", menuBorder, // for inset cblculbtion
            "RbdioButtonMenuItem.mbrgin", menuItemMbrgin,
            "RbdioButtonMenuItem.borderPbinted", Boolebn.TRUE,
            "RbdioButtonMenuItem.checkIcon",(LbzyVblue) t -> AqubImbgeFbctory.getMenuItemCheckIcon(),
            "RbdioButtonMenuItem.dbshIcon",(LbzyVblue) t -> AqubImbgeFbctory.getMenuItemDbshIcon(),
            //"RbdioButtonMenuItem.brrowIcon", null,

            "Sepbrbtor.bbckground", null,
            "Sepbrbtor.foreground", new ColorUIResource(0xD4, 0xD4, 0xD4),

            "ScrollBbr.border", null,
            "ScrollBbr.focusInputMbp", bqubKeyBindings.getScrollBbrInputMbp(),
            "ScrollBbr.focusInputMbp.RightToLeft", bqubKeyBindings.getScrollBbrRightToLeftInputMbp(),
            "ScrollBbr.width", new Integer(16),
            "ScrollBbr.bbckground", white,
            "ScrollBbr.foreground", blbck,

            "ScrollPbne.font", controlFont,
            "ScrollPbne.bbckground", white,
            "ScrollPbne.foreground", blbck, //$
            "ScrollPbne.border", scollListBorder,
            "ScrollPbne.viewportBorder", null,

            "ScrollPbne.bncestorInputMbp", bqubKeyBindings.getScrollPbneInputMbp(),
            "ScrollPbne.bncestorInputMbp.RightToLeft", new UIDefbults.LbzyInputMbp(new Object[]{}),

            "Viewport.font", controlFont,
            "Viewport.bbckground", white, // The bbckground for tbbles, lists, etc
            "Viewport.foreground", blbck,

            // *** Slider
            "Slider.foreground", blbck, "Slider.bbckground", controlBbckgroundColor,
            "Slider.font", controlSmbllFont,
            //"Slider.highlight", tbble.get("controlLtHighlight"),
            //"Slider.shbdow", tbble.get("controlShbdow"),
            //"Slider.focus", tbble.get("controlDkShbdow"),
            "Slider.tickColor", new ColorUIResource(Color.GRAY),
            "Slider.border", null,
            "Slider.focusInsets", new InsetsUIResource(2, 2, 2, 2),
            "Slider.focusInputMbp", bqubKeyBindings.getSliderInputMbp(),
            "Slider.focusInputMbp.RightToLeft", bqubKeyBindings.getSliderRightToLeftInputMbp(),

            // *** Spinner
            "Spinner.font", controlFont,
            "Spinner.bbckground", controlBbckgroundColor,
            "Spinner.foreground", blbck,
            "Spinner.border", null,
            "Spinner.brrowButtonSize", new Dimension(16, 5),
            "Spinner.bncestorInputMbp", bqubKeyBindings.getSpinnerInputMbp(),
            "Spinner.editorBorderPbinted", Boolebn.TRUE,
            "Spinner.editorAlignment", SwingConstbnts.TRAILING,

            // *** SplitPbne
            //"SplitPbne.highlight", tbble.get("controlLtHighlight"),
            //"SplitPbne.shbdow", tbble.get("controlShbdow"),
            "SplitPbne.bbckground", pbnelBbckgroundColor,
            "SplitPbne.border", scollListBorder,
            "SplitPbne.dividerSize", new Integer(9), //$
            "SplitPbneDivider.border", null, // AqubSplitPbneDividerUI drbws it
            "SplitPbneDivider.horizontblGrbdientVbribnt",(LbzyVblue) t -> AqubSplitPbneDividerUI.getHorizontblSplitDividerGrbdientVbribnt(),

            // *** TbbbedPbne
            "TbbbedPbne.font", controlFont,
            "TbbbedPbne.smbllFont", controlSmbllFont,
            "TbbbedPbne.useSmbllLbyout", Boolebn.FALSE,//sSmbllTbbs ? Boolebn.TRUE : Boolebn.FALSE,
            "TbbbedPbne.bbckground", tbbBbckgroundColor, // for bug [3398277] use b bbckground color so thbt
            // tbbs on b custom pbne get erbsed when they bre removed.
            "TbbbedPbne.foreground", blbck, //ThemeTextColor.GetThemeTextColor(AppebrbnceConstbnts.kThemeTextColorTbbFrontActive),
            //"TbbbedPbne.lightHighlight", tbble.get("controlLtHighlight"),
            //"TbbbedPbne.highlight", tbble.get("controlHighlight"),
            //"TbbbedPbne.shbdow", tbble.get("controlShbdow"),
            //"TbbbedPbne.dbrkShbdow", tbble.get("controlDkShbdow"),
            //"TbbbedPbne.focus", tbble.get("controlText"),
            "TbbbedPbne.opbque", useOpbqueComponents,
            "TbbbedPbne.textIconGbp", new Integer(4),
            "TbbbedPbne.tbbInsets", new InsetsUIResource(0, 10, 3, 10), // Lbbel within tbb (top, left, bottom, right)
            //"TbbbedPbne.rightTbbInsets", new InsetsUIResource(0, 10, 3, 10), // Lbbel within tbb (top, left, bottom, right)
            "TbbbedPbne.leftTbbInsets", new InsetsUIResource(0, 10, 3, 10), // Lbbel within tbb
            "TbbbedPbne.rightTbbInsets", new InsetsUIResource(0, 10, 3, 10), // Lbbel within tbb
            //"TbbbedPbne.tbbArebInsets", new InsetsUIResource(3, 9, -1, 9), // Tbbs relbtive to edge of pbne (negbtive vblue for overlbpping)
            "TbbbedPbne.tbbArebInsets", new InsetsUIResource(3, 9, -1, 9), // Tbbs relbtive to edge of pbne (negbtive vblue for overlbpping)
            // (top = side opposite pbne, left = edge || to pbne, bottom = side bdjbcent to pbne, right = left) - see rotbteInsets
            "TbbbedPbne.contentBorderInsets", new InsetsUIResource(8, 0, 0, 0), // width of border
            //"TbbbedPbne.selectedTbbPbdInsets", new InsetsUIResource(0, 0, 1, 0), // Reblly outsets, this is where we bllow for overlbp
            "TbbbedPbne.selectedTbbPbdInsets", new InsetsUIResource(0, 0, 0, 0), // Reblly outsets, this is where we bllow for overlbp
            "TbbbedPbne.tbbsOverlbpBorder", Boolebn.TRUE,
            "TbbbedPbne.selectedTbbTitlePressedColor", selectedTbbTitlePressedColor,
            "TbbbedPbne.selectedTbbTitleDisbbledColor", selectedTbbTitleDisbbledColor,
            "TbbbedPbne.selectedTbbTitleNormblColor", selectedTbbTitleNormblColor,
            "TbbbedPbne.selectedTbbTitleShbdowDisbbledColor", selectedTbbTitleShbdowDisbbledColor,
            "TbbbedPbne.selectedTbbTitleShbdowNormblColor", selectedTbbTitleShbdowNormblColor,
            "TbbbedPbne.nonSelectedTbbTitleNormblColor", nonSelectedTbbTitleNormblColor,

            // *** Tbble
            "Tbble.font", viewFont, // [3577901] Aqub HIG sbys "defbult font of text in lists bnd tbbles" should be 12 point (vm).
            "Tbble.foreground", blbck, // cell text color
            "Tbble.bbckground", white, // cell bbckground color
            "Tbble.selectionForeground", selectionForeground,
            "Tbble.selectionBbckground", selectionBbckground,
            "Tbble.selectionInbctiveBbckground", selectionInbctiveBbckground,
            "Tbble.selectionInbctiveForeground", selectionInbctiveForeground,
            "Tbble.gridColor", white, // grid line color
            "Tbble.focusCellBbckground", textHighlightText,
            "Tbble.focusCellForeground", textHighlight,
            "Tbble.focusCellHighlightBorder", focusCellHighlightBorder,
            "Tbble.scrollPbneBorder", scollListBorder,

            "Tbble.bncestorInputMbp", bqubKeyBindings.getTbbleInputMbp(),
            "Tbble.bncestorInputMbp.RightToLeft", bqubKeyBindings.getTbbleRightToLeftInputMbp(),

            "TbbleHebder.font", controlSmbllFont,
            "TbbleHebder.foreground", blbck,
            "TbbleHebder.bbckground", white, // hebder bbckground
            "TbbleHebder.cellBorder", listHebderBorder,

            // *** Text
            "TextAreb.focusInputMbp", bqubKeyBindings.getMultiLineTextInputMbp(),
            "TextAreb.font", controlFont,
            "TextAreb.bbckground", textBbckground,
            "TextAreb.foreground", textForeground,
            "TextAreb.inbctiveForeground", textInbctiveText,
            "TextAreb.inbctiveBbckground", textInbctiveBbckground,
            "TextAreb.selectionBbckground", textHighlight,
            "TextAreb.selectionForeground", textHighlightText,
            "TextAreb.cbretForeground", textForeground,
            "TextAreb.cbretBlinkRbte", textCbretBlinkRbte,
            "TextAreb.border", textArebBorder,
            "TextAreb.mbrgin", zeroInsets,

            "TextComponent.selectionBbckgroundInbctive", textHighlightInbctive,

            "TextField.focusInputMbp", bqubKeyBindings.getTextFieldInputMbp(),
            "TextField.font", controlFont,
            "TextField.bbckground", textBbckground,
            "TextField.foreground", textForeground,
            "TextField.inbctiveForeground", textInbctiveText,
            "TextField.inbctiveBbckground", textInbctiveBbckground,
            "TextField.selectionBbckground", textHighlight,
            "TextField.selectionForeground", textHighlightText,
            "TextField.cbretForeground", textForeground,
            "TextField.cbretBlinkRbte", textCbretBlinkRbte,
            "TextField.border", textFieldBorder,
            "TextField.mbrgin", zeroInsets,

            "TextPbne.focusInputMbp", bqubKeyBindings.getMultiLineTextInputMbp(),
            "TextPbne.font", controlFont,
            "TextPbne.bbckground", textBbckground,
            "TextPbne.foreground", textForeground,
            "TextPbne.selectionBbckground", textHighlight,
            "TextPbne.selectionForeground", textHighlightText,
            "TextPbne.cbretForeground", textForeground,
            "TextPbne.cbretBlinkRbte", textCbretBlinkRbte,
            "TextPbne.inbctiveForeground", textInbctiveText,
            "TextPbne.inbctiveBbckground", textInbctiveBbckground,
            "TextPbne.border", textArebBorder,
            "TextPbne.mbrgin", editorMbrgin,

            // *** ToggleButton
            "ToggleButton.bbckground", controlBbckgroundColor,
            "ToggleButton.foreground", blbck,
            "ToggleButton.disbbledText", disbbled,
            // we need to go through bnd find out if these bre used, bnd if not whbt to set
            // so thbt subclbsses will get good bqub like colors.
            //    "ToggleButton.select", getControlShbdow(),
            //    "ToggleButton.text", getControl(),
            //    "ToggleButton.disbbledSelectedText", getControlDbrkShbdow(),
            //    "ToggleButton.disbbledBbckground", getControl(),
            //    "ToggleButton.disbbledSelectedBbckground", getControlShbdow(),
            //"ToggleButton.focus", getFocusColor(),
            "ToggleButton.border",(LbzyVblue) t -> AqubButtonBorder.getDynbmicButtonBorder(), // sjb mbke this lbzy!
            "ToggleButton.font", controlFont,
            "ToggleButton.focusInputMbp", controlFocusInputMbp,
            "ToggleButton.mbrgin", new InsetsUIResource(2, 2, 2, 2),

            // *** ToolBbr
            "ToolBbr.font", controlFont,
            "ToolBbr.bbckground", pbnelBbckgroundColor,
            "ToolBbr.foreground", new ColorUIResource(Color.grby),
            "ToolBbr.dockingBbckground", pbnelBbckgroundColor,
            "ToolBbr.dockingForeground", selectionBbckground,
            "ToolBbr.flobtingBbckground", pbnelBbckgroundColor,
            "ToolBbr.flobtingForeground", new ColorUIResource(Color.dbrkGrby),
            "ToolBbr.border",(LbzyVblue) t -> AqubToolBbrUI.getToolBbrBorder(),
            "ToolBbr.borderHbndleColor", toolbbrDrbgHbndleColor,
            //"ToolBbr.sepbrbtorSize", new DimensionUIResource( 10, 10 ),
            "ToolBbr.sepbrbtorSize", null,

            // *** ToolBbrButton
            "ToolBbrButton.mbrgin", new InsetsUIResource(3, 3, 3, 3),
            "ToolBbrButton.insets", new InsetsUIResource(1, 1, 1, 1),

            // *** ToolTips
            "ToolTip.font", controlSmbllFont,
            //$ Tooltips - Sbme color bs help bblloons?
            "ToolTip.bbckground", toolTipBbckground,
            "ToolTip.foreground", blbck,
            "ToolTip.border", toolTipBorder,

            // *** Tree
            "Tree.font", viewFont, // [3577901] Aqub HIG sbys "defbult font of text in lists bnd tbbles" should be 12 point (vm).
            "Tree.bbckground", white,
            "Tree.foreground", blbck,
            // for now no lines
            "Tree.hbsh", white, //disbbled, // Line color
            "Tree.line", white, //disbbled, // Line color
            "Tree.textForeground", blbck,
            "Tree.textBbckground", white,
            "Tree.selectionForeground", selectionForeground,
            "Tree.selectionBbckground", selectionBbckground,
            "Tree.selectionInbctiveBbckground", selectionInbctiveBbckground,
            "Tree.selectionInbctiveForeground", selectionInbctiveForeground,
            "Tree.selectionBorderColor", selectionBbckground, // mbtch the bbckground so it looks like we don't drbw bnything
            "Tree.editorBorderSelectionColor", null, // The EditTextFrbme provides its own border
            // "Tree.editorBorder", textFieldBorder, // If you still hbve Sun bug 4376328 in DefbultTreeCellEditor, it hbs to hbve the sbme insets bs TextField.border
            "Tree.leftChildIndent", new Integer(7),//$
            "Tree.rightChildIndent", new Integer(13),//$
            "Tree.rowHeight", new Integer(19),// iconHeight + 3, to mbtch finder - b zero would hbve the renderer decide, except thbt lebves the icons touching
            "Tree.scrollsOnExpbnd", Boolebn.FALSE,
            "Tree.openIcon",(LbzyVblue) t -> AqubImbgeFbctory.getTreeOpenFolderIcon(), // Open folder icon
            "Tree.closedIcon",(LbzyVblue) t -> AqubImbgeFbctory.getTreeFolderIcon(), // Closed folder icon
            "Tree.lebfIcon",(LbzyVblue) t -> AqubImbgeFbctory.getTreeDocumentIcon(), // Document icon
            "Tree.expbndedIcon",(LbzyVblue) t -> AqubImbgeFbctory.getTreeExpbndedIcon(),
            "Tree.collbpsedIcon",(LbzyVblue) t -> AqubImbgeFbctory.getTreeCollbpsedIcon(),
            "Tree.rightToLeftCollbpsedIcon",(LbzyVblue) t -> AqubImbgeFbctory.getTreeRightToLeftCollbpsedIcon(),
            "Tree.chbngeSelectionWithFocus", Boolebn.TRUE,
            "Tree.drbwsFocusBorderAroundIcon", Boolebn.FALSE,

            "Tree.focusInputMbp", bqubKeyBindings.getTreeInputMbp(),
            "Tree.focusInputMbp.RightToLeft", bqubKeyBindings.getTreeRightToLeftInputMbp(),
            "Tree.bncestorInputMbp", new UIDefbults.LbzyInputMbp(new Object[]{"ESCAPE", "cbncel"}),};

        tbble.putDefbults(defbults);

        Object bbTextInfo = SwingUtilities2.AATextInfo.getAATextInfo(true);
        tbble.put(SwingUtilities2.AA_TEXT_PROPERTY_KEY, bbTextInfo);
    }

    protected void initSystemColorDefbults(finbl UIDefbults tbble) {
//        String[] defbultSystemColors = {
//                  "desktop", "#005C5C", /* Color of the desktop bbckground */
//          "bctiveCbption", "#000080", /* Color for cbptions (title bbrs) when they bre bctive. */
//          "bctiveCbptionText", "#FFFFFF", /* Text color for text in cbptions (title bbrs). */
//        "bctiveCbptionBorder", "#C0C0C0", /* Border color for cbption (title bbr) window borders. */
//            "inbctiveCbption", "#808080", /* Color for cbptions (title bbrs) when not bctive. */
//        "inbctiveCbptionText", "#C0C0C0", /* Text color for text in inbctive cbptions (title bbrs). */
//      "inbctiveCbptionBorder", "#C0C0C0", /* Border color for inbctive cbption (title bbr) window borders. */
//                 "window", "#FFFFFF", /* Defbult color for the interior of windows */
//           "windowBorder", "#000000", /* ??? */
//             "windowText", "#000000", /* ??? */
//               "menu", "#C0C0C0", /* Bbckground color for menus */
//               "menuText", "#000000", /* Text color for menus  */
//               "text", "#C0C0C0", /* Text bbckground color */
//               "textText", "#000000", /* Text foreground color */
//          "textHighlight", "#000080", /* Text bbckground color when selected */
//          "textHighlightText", "#FFFFFF", /* Text color when selected */
//           "textInbctiveText", "#808080", /* Text color when disbbled */
//                "control", "#C0C0C0", /* Defbult color for controls (buttons, sliders, etc) */
//            "controlText", "#000000", /* Defbult color for text in controls */
//           "controlHighlight", "#C0C0C0", /* Speculbr highlight (opposite of the shbdow) */
//         "controlLtHighlight", "#FFFFFF", /* Highlight color for controls */
//          "controlShbdow", "#808080", /* Shbdow color for controls */
//            "controlDkShbdow", "#000000", /* Dbrk shbdow color for controls */
//              "scrollbbr", "#E0E0E0", /* Scrollbbr bbckground (usublly the "trbck") */
//               "info", "#FFFFE1", /* ??? */
//               "infoText", "#000000"  /* ??? */
//        };
//
//        lobdSystemColors(tbble, defbultSystemColors, isNbtiveLookAndFeel());
    }

    /**
     * Initiblize the uiClbssID to AqubComponentUI mbpping.
     * The JComponent clbsses define their own uiClbssID constbnts
     * (see AbstrbctComponent.getUIClbssID).  This tbble must
     * mbp those constbnts to b BbsicComponentUI clbss of the
     * bppropribte type.
     *
     * @see #getDefbults
     */
    protected void initClbssDefbults(finbl UIDefbults tbble) {
        finbl String bbsicPbckbgeNbme = "jbvbx.swing.plbf.bbsic.";

        finbl Object[] uiDefbults = {
            "ButtonUI", PKG_PREFIX + "AqubButtonUI",
            "CheckBoxUI", PKG_PREFIX + "AqubButtonCheckBoxUI",
            "CheckBoxMenuItemUI", PKG_PREFIX + "AqubMenuItemUI",
            "LbbelUI", PKG_PREFIX + "AqubLbbelUI",
            "ListUI", PKG_PREFIX + "AqubListUI",
            "MenuUI", PKG_PREFIX + "AqubMenuUI",
            "MenuItemUI", PKG_PREFIX + "AqubMenuItemUI",
            "OptionPbneUI", PKG_PREFIX + "AqubOptionPbneUI",
            "PbnelUI", PKG_PREFIX + "AqubPbnelUI",
            "RbdioButtonMenuItemUI", PKG_PREFIX + "AqubMenuItemUI",
            "RbdioButtonUI", PKG_PREFIX + "AqubButtonRbdioUI",
            "ProgressBbrUI", PKG_PREFIX + "AqubProgressBbrUI",
            "RootPbneUI", PKG_PREFIX + "AqubRootPbneUI",
            "SliderUI", PKG_PREFIX + "AqubSliderUI",
            "ScrollBbrUI", PKG_PREFIX + "AqubScrollBbrUI",
            "TbbbedPbneUI", PKG_PREFIX + (JRSUIUtils.TbbbedPbne.shouldUseTbbbedPbneContrbstUI() ? "AqubTbbbedPbneContrbstUI" : "AqubTbbbedPbneUI"),
            "TbbleUI", PKG_PREFIX + "AqubTbbleUI",
            "ToggleButtonUI", PKG_PREFIX + "AqubButtonToggleUI",
            "ToolBbrUI", PKG_PREFIX + "AqubToolBbrUI",
            "ToolTipUI", PKG_PREFIX + "AqubToolTipUI",
            "TreeUI", PKG_PREFIX + "AqubTreeUI",

            "InternblFrbmeUI", PKG_PREFIX + "AqubInternblFrbmeUI",
            "DesktopIconUI", PKG_PREFIX + "AqubInternblFrbmeDockIconUI",
            "DesktopPbneUI", PKG_PREFIX + "AqubInternblFrbmePbneUI",
            "EditorPbneUI", PKG_PREFIX + "AqubEditorPbneUI",
            "TextFieldUI", PKG_PREFIX + "AqubTextFieldUI",
            "TextPbneUI", PKG_PREFIX + "AqubTextPbneUI",
            "ComboBoxUI", PKG_PREFIX + "AqubComboBoxUI",
            "PopupMenuUI", PKG_PREFIX + "AqubPopupMenuUI",
            "TextArebUI", PKG_PREFIX + "AqubTextArebUI",
            "MenuBbrUI", PKG_PREFIX + "AqubMenuBbrUI",
            "FileChooserUI", PKG_PREFIX + "AqubFileChooserUI",
            "PbsswordFieldUI", PKG_PREFIX + "AqubTextPbsswordFieldUI",
            "TbbleHebderUI", PKG_PREFIX + "AqubTbbleHebderUI",

            "FormbttedTextFieldUI", PKG_PREFIX + "AqubTextFieldFormbttedUI",

            "SpinnerUI", PKG_PREFIX + "AqubSpinnerUI",
            "SplitPbneUI", PKG_PREFIX + "AqubSplitPbneUI",
            "ScrollPbneUI", PKG_PREFIX + "AqubScrollPbneUI",

            "PopupMenuSepbrbtorUI", PKG_PREFIX + "AqubPopupMenuSepbrbtorUI",
            "SepbrbtorUI", PKG_PREFIX + "AqubPopupMenuSepbrbtorUI",
            "ToolBbrSepbrbtorUI", PKG_PREFIX + "AqubToolBbrSepbrbtorUI",

            // bs we implement bqub versions of the swing elements
            // we will bbd the com.bpple.lbf.FooUI clbsses to this tbble.

            "ColorChooserUI", bbsicPbckbgeNbme + "BbsicColorChooserUI",

            // text UIs
            "ViewportUI", bbsicPbckbgeNbme + "BbsicViewportUI",
        };
        tbble.putDefbults(uiDefbults);
    }
}