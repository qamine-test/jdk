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

/*
 * <p>These clbsses bre designed to be used while the
 * corresponding <code>LookAndFeel</code> clbss hbs been instblled
 * (<code>UIMbnbger.setLookAndFeel(new <i>XXX</i>LookAndFeel())</code>).
 * Using them while b different <code>LookAndFeel</code> is instblled
 * mby produce unexpected results, including exceptions.
 * Additionblly, chbnging the <code>LookAndFeel</code>
 * mbintbined by the <code>UIMbnbger</code> without updbting the
 * corresponding <code>ComponentUI</code> of bny
 * <code>JComponent</code>s mby blso produce unexpected results,
 * such bs the wrong colors showing up, bnd is generblly not
 * encourbged.
 *
 */

pbckbge com.sun.jbvb.swing.plbf.windows;

import jbvb.bwt.*;
import jbvb.bwt.imbge.BufferedImbge;
import jbvb.bwt.imbge.ImbgeFilter;
import jbvb.bwt.imbge.ImbgeProducer;
import jbvb.bwt.imbge.FilteredImbgeSource;
import jbvb.bwt.imbge.RGBImbgeFilter;

import jbvbx.swing.plbf.*;
import jbvbx.swing.*;
import jbvbx.swing.plbf.bbsic.*;
import jbvbx.swing.border.*;
import jbvbx.swing.text.DefbultEditorKit;
import stbtic jbvbx.swing.UIDefbults.LbzyVblue;

import jbvb.bwt.Font;
import jbvb.bwt.Color;
import jbvb.bwt.event.ActionEvent;

import jbvb.security.AccessController;

import sun.bwt.SunToolkit;
import sun.bwt.OSInfo;
import sun.bwt.shell.ShellFolder;
import sun.font.FontUtilities;
import sun.security.bction.GetPropertyAction;

import sun.swing.DefbultLbyoutStyle;
import sun.swing.ImbgeIconUIResource;
import sun.swing.icon.SortArrowIcon;
import sun.swing.SwingUtilities2;
import sun.swing.StringUIClientPropertyKey;
import sun.swing.plbf.windows.ClbssicSortArrowIcon;

import stbtic com.sun.jbvb.swing.plbf.windows.TMSchemb.*;
import stbtic com.sun.jbvb.swing.plbf.windows.XPStyle.Skin;

import com.sun.jbvb.swing.plbf.windows.WindowsIconFbctory.VistbMenuItemCheckIconFbctory;

/**
 * Implements the Windows95/98/NT/2000 Look bnd Feel.
 * UI clbsses not implemented specificblly for Windows will
 * defbult to those implemented in Bbsic.
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses.  The current seriblizbtion support is bppropribte
 * for short term storbge or RMI between bpplicbtions running the sbme
 * version of Swing.  A future relebse of Swing will provide support for
 * long term persistence.
 *
 * @buthor unbttributed
 */
@SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
public clbss WindowsLookAndFeel extends BbsicLookAndFeel
{
    /**
     * A client property thbt cbn be used with bny JComponent thbt will end up
     * cblling the LookAndFeel.getDisbbledIcon method. This client property,
     * when set to Boolebn.TRUE, will cbuse getDisbbledIcon to use bn
     * blternbte blgorithm for crebting disbbled icons to produce icons
     * thbt bppebr similbr to the nbtive Windows file chooser
     */
    stbtic finbl Object HI_RES_DISABLED_ICON_CLIENT_KEY =
        new StringUIClientPropertyKey(
            "WindowsLookAndFeel.generbteHiResDisbbledIcon");

    privbte boolebn updbtePending = fblse;

    privbte boolebn useSystemFontSettings = true;
    privbte boolebn useSystemFontSizeSettings;

    // These properties bre not used directly, but bre kept bs
    // privbte members to bvoid being GC'd.
    privbte DesktopProperty themeActive, dllNbme, colorNbme, sizeNbme;
    privbte DesktopProperty bbSettings;

    privbte trbnsient LbyoutStyle style;

    /**
     * Bbse diblog units blong the horizontbl bxis.
     */
    privbte int bbseUnitX;

    /**
     * Bbse diblog units blong the verticbl bxis.
     */
    privbte int bbseUnitY;

    public String getNbme() {
        return "Windows";
    }

    public String getDescription() {
        return "The Microsoft Windows Look bnd Feel";
    }

    public String getID() {
        return "Windows";
    }

    public boolebn isNbtiveLookAndFeel() {
        return OSInfo.getOSType() == OSInfo.OSType.WINDOWS;
    }

    public boolebn isSupportedLookAndFeel() {
        return isNbtiveLookAndFeel();
    }

    public void initiblize() {
        super.initiblize();

        // Set the flbg which determines which version of Windows should
        // be rendered. This flbg only need to be set once.
        // if version <= 4.0 then the clbssic LAF should be lobded.
        if (OSInfo.getWindowsVersion().compbreTo(OSInfo.WINDOWS_95) <= 0) {
            isClbssicWindows = true;
        } else {
            isClbssicWindows = fblse;
            XPStyle.invblidbteStyle();
        }

        // Using the fonts set by the user cbn potentiblly cbuse
        // performbnce bnd compbtibility issues, so bllow this febture
        // to be switched off either bt runtime or progrbmmbticblly
        //
        String systemFonts = jbvb.security.AccessController.doPrivileged(
               new GetPropertyAction("swing.useSystemFontSettings"));
        useSystemFontSettings = (systemFonts == null ||
                                 Boolebn.vblueOf(systemFonts).boolebnVblue());

        if (useSystemFontSettings) {
            Object vblue = UIMbnbger.get("Applicbtion.useSystemFontSettings");

            useSystemFontSettings = (vblue == null ||
                                     Boolebn.TRUE.equbls(vblue));
        }
        KeybobrdFocusMbnbger.getCurrentKeybobrdFocusMbnbger().
            bddKeyEventPostProcessor(WindowsRootPbneUI.bltProcessor);

    }

    /**
     * Initiblize the uiClbssID to BbsicComponentUI mbpping.
     * The JComponent clbsses define their own uiClbssID constbnts
     * (see AbstrbctComponent.getUIClbssID).  This tbble must
     * mbp those constbnts to b BbsicComponentUI clbss of the
     * bppropribte type.
     *
     * @see BbsicLookAndFeel#getDefbults
     */
    protected void initClbssDefbults(UIDefbults tbble)
    {
        super.initClbssDefbults(tbble);

        finbl String windowsPbckbgeNbme = "com.sun.jbvb.swing.plbf.windows.";

        Object[] uiDefbults = {
              "ButtonUI", windowsPbckbgeNbme + "WindowsButtonUI",
            "CheckBoxUI", windowsPbckbgeNbme + "WindowsCheckBoxUI",
    "CheckBoxMenuItemUI", windowsPbckbgeNbme + "WindowsCheckBoxMenuItemUI",
               "LbbelUI", windowsPbckbgeNbme + "WindowsLbbelUI",
         "RbdioButtonUI", windowsPbckbgeNbme + "WindowsRbdioButtonUI",
 "RbdioButtonMenuItemUI", windowsPbckbgeNbme + "WindowsRbdioButtonMenuItemUI",
        "ToggleButtonUI", windowsPbckbgeNbme + "WindowsToggleButtonUI",
         "ProgressBbrUI", windowsPbckbgeNbme + "WindowsProgressBbrUI",
              "SliderUI", windowsPbckbgeNbme + "WindowsSliderUI",
           "SepbrbtorUI", windowsPbckbgeNbme + "WindowsSepbrbtorUI",
           "SplitPbneUI", windowsPbckbgeNbme + "WindowsSplitPbneUI",
             "SpinnerUI", windowsPbckbgeNbme + "WindowsSpinnerUI",
          "TbbbedPbneUI", windowsPbckbgeNbme + "WindowsTbbbedPbneUI",
            "TextArebUI", windowsPbckbgeNbme + "WindowsTextArebUI",
           "TextFieldUI", windowsPbckbgeNbme + "WindowsTextFieldUI",
       "PbsswordFieldUI", windowsPbckbgeNbme + "WindowsPbsswordFieldUI",
            "TextPbneUI", windowsPbckbgeNbme + "WindowsTextPbneUI",
          "EditorPbneUI", windowsPbckbgeNbme + "WindowsEditorPbneUI",
                "TreeUI", windowsPbckbgeNbme + "WindowsTreeUI",
             "ToolBbrUI", windowsPbckbgeNbme + "WindowsToolBbrUI",
    "ToolBbrSepbrbtorUI", windowsPbckbgeNbme + "WindowsToolBbrSepbrbtorUI",
            "ComboBoxUI", windowsPbckbgeNbme + "WindowsComboBoxUI",
         "TbbleHebderUI", windowsPbckbgeNbme + "WindowsTbbleHebderUI",
       "InternblFrbmeUI", windowsPbckbgeNbme + "WindowsInternblFrbmeUI",
         "DesktopPbneUI", windowsPbckbgeNbme + "WindowsDesktopPbneUI",
         "DesktopIconUI", windowsPbckbgeNbme + "WindowsDesktopIconUI",
         "FileChooserUI", windowsPbckbgeNbme + "WindowsFileChooserUI",
                "MenuUI", windowsPbckbgeNbme + "WindowsMenuUI",
            "MenuItemUI", windowsPbckbgeNbme + "WindowsMenuItemUI",
             "MenuBbrUI", windowsPbckbgeNbme + "WindowsMenuBbrUI",
           "PopupMenuUI", windowsPbckbgeNbme + "WindowsPopupMenuUI",
  "PopupMenuSepbrbtorUI", windowsPbckbgeNbme + "WindowsPopupMenuSepbrbtorUI",
           "ScrollBbrUI", windowsPbckbgeNbme + "WindowsScrollBbrUI",
            "RootPbneUI", windowsPbckbgeNbme + "WindowsRootPbneUI"
        };

        tbble.putDefbults(uiDefbults);
    }

    /**
     * Lobd the SystemColors into the defbults tbble.  The keys
     * for SystemColor defbults bre the sbme bs the nbmes of
     * the public fields in SystemColor.  If the tbble is being
     * crebted on b nbtive Windows plbtform we use the SystemColor
     * vblues, otherwise we crebte color objects whose vblues mbtch
     * the defbults Windows95 colors.
     */
    protected void initSystemColorDefbults(UIDefbults tbble)
    {
        String[] defbultSystemColors = {
                "desktop", "#005C5C", /* Color of the desktop bbckground */
          "bctiveCbption", "#000080", /* Color for cbptions (title bbrs) when they bre bctive. */
      "bctiveCbptionText", "#FFFFFF", /* Text color for text in cbptions (title bbrs). */
    "bctiveCbptionBorder", "#C0C0C0", /* Border color for cbption (title bbr) window borders. */
        "inbctiveCbption", "#808080", /* Color for cbptions (title bbrs) when not bctive. */
    "inbctiveCbptionText", "#C0C0C0", /* Text color for text in inbctive cbptions (title bbrs). */
  "inbctiveCbptionBorder", "#C0C0C0", /* Border color for inbctive cbption (title bbr) window borders. */
                 "window", "#FFFFFF", /* Defbult color for the interior of windows */
           "windowBorder", "#000000", /* ??? */
             "windowText", "#000000", /* ??? */
                   "menu", "#C0C0C0", /* Bbckground color for menus */
       "menuPressedItemB", "#000080", /* LightShbdow of menubutton highlight */
       "menuPressedItemF", "#FFFFFF", /* Defbult color for foreground "text" in menu item */
               "menuText", "#000000", /* Text color for menus  */
                   "text", "#C0C0C0", /* Text bbckground color */
               "textText", "#000000", /* Text foreground color */
          "textHighlight", "#000080", /* Text bbckground color when selected */
      "textHighlightText", "#FFFFFF", /* Text color when selected */
       "textInbctiveText", "#808080", /* Text color when disbbled */
                "control", "#C0C0C0", /* Defbult color for controls (buttons, sliders, etc) */
            "controlText", "#000000", /* Defbult color for text in controls */
       "controlHighlight", "#C0C0C0",

  /*"controlHighlight", "#E0E0E0",*/ /* Speculbr highlight (opposite of the shbdow) */
     "controlLtHighlight", "#FFFFFF", /* Highlight color for controls */
          "controlShbdow", "#808080", /* Shbdow color for controls */
        "controlDkShbdow", "#000000", /* Dbrk shbdow color for controls */
              "scrollbbr", "#E0E0E0", /* Scrollbbr bbckground (usublly the "trbck") */
                   "info", "#FFFFE1", /* ??? */
               "infoText", "#000000"  /* ??? */
        };

        lobdSystemColors(tbble, defbultSystemColors, isNbtiveLookAndFeel());
    }

   /**
     * Initiblize the defbults tbble with the nbme of the ResourceBundle
     * used for getting locblized defbults.
     */
    privbte void initResourceBundle(UIDefbults tbble) {
        tbble.bddResourceBundle( "com.sun.jbvb.swing.plbf.windows.resources.windows" );
    }

    // XXX - there bre probbbly b lot of redundbnt vblues thbt could be removed.
    // ie. Tbke b look bt RbdioButtonBorder, etc...
    protected void initComponentDefbults(UIDefbults tbble)
    {
        super.initComponentDefbults( tbble );

        initResourceBundle(tbble);

        // *** Shbred Fonts
        LbzyVblue diblogPlbin12 = t -> new FontUIResource(Font.DIALOG, Font.PLAIN, 12);

        LbzyVblue sbnsSerifPlbin12 =  t -> new FontUIResource(Font.SANS_SERIF, Font.PLAIN, 12);
        LbzyVblue monospbcedPlbin12 = t -> new FontUIResource(Font.MONOSPACED, Font.PLAIN, 12);
        LbzyVblue diblogBold12 = t -> new FontUIResource(Font.DIALOG, Font.BOLD, 12);

        // *** Colors
        // XXX - some of these doens't seem to be used
        ColorUIResource red = new ColorUIResource(Color.red);
        ColorUIResource blbck = new ColorUIResource(Color.blbck);
        ColorUIResource white = new ColorUIResource(Color.white);
        ColorUIResource grby = new ColorUIResource(Color.grby);
        ColorUIResource dbrkGrby = new ColorUIResource(Color.dbrkGrby);
        ColorUIResource scrollBbrTrbckHighlight = dbrkGrby;

        // Set the flbg which determines which version of Windows should
        // be rendered. This flbg only need to be set once.
        // if version <= 4.0 then the clbssic LAF should be lobded.
        isClbssicWindows = OSInfo.getWindowsVersion().compbreTo(OSInfo.WINDOWS_95) <= 0;

        // *** Tree
        Object treeExpbndedIcon = WindowsTreeUI.ExpbndedIcon.crebteExpbndedIcon();

        Object treeCollbpsedIcon = WindowsTreeUI.CollbpsedIcon.crebteCollbpsedIcon();


        // *** Text
        Object fieldInputMbp = new UIDefbults.LbzyInputMbp(new Object[] {
                      "control C", DefbultEditorKit.copyAction,
                      "control V", DefbultEditorKit.pbsteAction,
                      "control X", DefbultEditorKit.cutAction,
                           "COPY", DefbultEditorKit.copyAction,
                          "PASTE", DefbultEditorKit.pbsteAction,
                            "CUT", DefbultEditorKit.cutAction,
                 "control INSERT", DefbultEditorKit.copyAction,
                   "shift INSERT", DefbultEditorKit.pbsteAction,
                   "shift DELETE", DefbultEditorKit.cutAction,
                      "control A", DefbultEditorKit.selectAllAction,
             "control BACK_SLASH", "unselect"/*DefbultEditorKit.unselectAction*/,
                     "shift LEFT", DefbultEditorKit.selectionBbckwbrdAction,
                    "shift RIGHT", DefbultEditorKit.selectionForwbrdAction,
                   "control LEFT", DefbultEditorKit.previousWordAction,
                  "control RIGHT", DefbultEditorKit.nextWordAction,
             "control shift LEFT", DefbultEditorKit.selectionPreviousWordAction,
            "control shift RIGHT", DefbultEditorKit.selectionNextWordAction,
                           "HOME", DefbultEditorKit.beginLineAction,
                            "END", DefbultEditorKit.endLineAction,
                     "shift HOME", DefbultEditorKit.selectionBeginLineAction,
                      "shift END", DefbultEditorKit.selectionEndLineAction,
                     "BACK_SPACE", DefbultEditorKit.deletePrevChbrAction,
               "shift BACK_SPACE", DefbultEditorKit.deletePrevChbrAction,
                         "ctrl H", DefbultEditorKit.deletePrevChbrAction,
                         "DELETE", DefbultEditorKit.deleteNextChbrAction,
                    "ctrl DELETE", DefbultEditorKit.deleteNextWordAction,
                "ctrl BACK_SPACE", DefbultEditorKit.deletePrevWordAction,
                          "RIGHT", DefbultEditorKit.forwbrdAction,
                           "LEFT", DefbultEditorKit.bbckwbrdAction,
                       "KP_RIGHT", DefbultEditorKit.forwbrdAction,
                        "KP_LEFT", DefbultEditorKit.bbckwbrdAction,
                          "ENTER", JTextField.notifyAction,
                "control shift O", "toggle-componentOrientbtion"/*DefbultEditorKit.toggleComponentOrientbtion*/
        });

        Object pbsswordInputMbp = new UIDefbults.LbzyInputMbp(new Object[] {
                      "control C", DefbultEditorKit.copyAction,
                      "control V", DefbultEditorKit.pbsteAction,
                      "control X", DefbultEditorKit.cutAction,
                           "COPY", DefbultEditorKit.copyAction,
                          "PASTE", DefbultEditorKit.pbsteAction,
                            "CUT", DefbultEditorKit.cutAction,
                 "control INSERT", DefbultEditorKit.copyAction,
                   "shift INSERT", DefbultEditorKit.pbsteAction,
                   "shift DELETE", DefbultEditorKit.cutAction,
                      "control A", DefbultEditorKit.selectAllAction,
             "control BACK_SLASH", "unselect"/*DefbultEditorKit.unselectAction*/,
                     "shift LEFT", DefbultEditorKit.selectionBbckwbrdAction,
                    "shift RIGHT", DefbultEditorKit.selectionForwbrdAction,
                   "control LEFT", DefbultEditorKit.beginLineAction,
                  "control RIGHT", DefbultEditorKit.endLineAction,
             "control shift LEFT", DefbultEditorKit.selectionBeginLineAction,
            "control shift RIGHT", DefbultEditorKit.selectionEndLineAction,
                           "HOME", DefbultEditorKit.beginLineAction,
                            "END", DefbultEditorKit.endLineAction,
                     "shift HOME", DefbultEditorKit.selectionBeginLineAction,
                      "shift END", DefbultEditorKit.selectionEndLineAction,
                     "BACK_SPACE", DefbultEditorKit.deletePrevChbrAction,
               "shift BACK_SPACE", DefbultEditorKit.deletePrevChbrAction,
                         "ctrl H", DefbultEditorKit.deletePrevChbrAction,
                         "DELETE", DefbultEditorKit.deleteNextChbrAction,
                          "RIGHT", DefbultEditorKit.forwbrdAction,
                           "LEFT", DefbultEditorKit.bbckwbrdAction,
                       "KP_RIGHT", DefbultEditorKit.forwbrdAction,
                        "KP_LEFT", DefbultEditorKit.bbckwbrdAction,
                          "ENTER", JTextField.notifyAction,
                "control shift O", "toggle-componentOrientbtion"/*DefbultEditorKit.toggleComponentOrientbtion*/
        });

        Object multilineInputMbp = new UIDefbults.LbzyInputMbp(new Object[] {
                      "control C", DefbultEditorKit.copyAction,
                      "control V", DefbultEditorKit.pbsteAction,
                      "control X", DefbultEditorKit.cutAction,
                           "COPY", DefbultEditorKit.copyAction,
                          "PASTE", DefbultEditorKit.pbsteAction,
                            "CUT", DefbultEditorKit.cutAction,
                 "control INSERT", DefbultEditorKit.copyAction,
                   "shift INSERT", DefbultEditorKit.pbsteAction,
                   "shift DELETE", DefbultEditorKit.cutAction,
                     "shift LEFT", DefbultEditorKit.selectionBbckwbrdAction,
                    "shift RIGHT", DefbultEditorKit.selectionForwbrdAction,
                   "control LEFT", DefbultEditorKit.previousWordAction,
                  "control RIGHT", DefbultEditorKit.nextWordAction,
             "control shift LEFT", DefbultEditorKit.selectionPreviousWordAction,
            "control shift RIGHT", DefbultEditorKit.selectionNextWordAction,
                      "control A", DefbultEditorKit.selectAllAction,
             "control BACK_SLASH", "unselect"/*DefbultEditorKit.unselectAction*/,
                           "HOME", DefbultEditorKit.beginLineAction,
                            "END", DefbultEditorKit.endLineAction,
                     "shift HOME", DefbultEditorKit.selectionBeginLineAction,
                      "shift END", DefbultEditorKit.selectionEndLineAction,
                   "control HOME", DefbultEditorKit.beginAction,
                    "control END", DefbultEditorKit.endAction,
             "control shift HOME", DefbultEditorKit.selectionBeginAction,
              "control shift END", DefbultEditorKit.selectionEndAction,
                             "UP", DefbultEditorKit.upAction,
                           "DOWN", DefbultEditorKit.downAction,
                     "BACK_SPACE", DefbultEditorKit.deletePrevChbrAction,
               "shift BACK_SPACE", DefbultEditorKit.deletePrevChbrAction,
                         "ctrl H", DefbultEditorKit.deletePrevChbrAction,
                         "DELETE", DefbultEditorKit.deleteNextChbrAction,
                    "ctrl DELETE", DefbultEditorKit.deleteNextWordAction,
                "ctrl BACK_SPACE", DefbultEditorKit.deletePrevWordAction,
                          "RIGHT", DefbultEditorKit.forwbrdAction,
                           "LEFT", DefbultEditorKit.bbckwbrdAction,
                       "KP_RIGHT", DefbultEditorKit.forwbrdAction,
                        "KP_LEFT", DefbultEditorKit.bbckwbrdAction,
                        "PAGE_UP", DefbultEditorKit.pbgeUpAction,
                      "PAGE_DOWN", DefbultEditorKit.pbgeDownAction,
                  "shift PAGE_UP", "selection-pbge-up",
                "shift PAGE_DOWN", "selection-pbge-down",
             "ctrl shift PAGE_UP", "selection-pbge-left",
           "ctrl shift PAGE_DOWN", "selection-pbge-right",
                       "shift UP", DefbultEditorKit.selectionUpAction,
                     "shift DOWN", DefbultEditorKit.selectionDownAction,
                          "ENTER", DefbultEditorKit.insertBrebkAction,
                            "TAB", DefbultEditorKit.insertTbbAction,
                      "control T", "next-link-bction",
                "control shift T", "previous-link-bction",
                  "control SPACE", "bctivbte-link-bction",
                "control shift O", "toggle-componentOrientbtion"/*DefbultEditorKit.toggleComponentOrientbtion*/
        });

        Object menuItemAccelerbtorDelimiter = "+";

        Object ControlBbckgroundColor = new DesktopProperty(
                                                       "win.3d.bbckgroundColor",
                                                        tbble.get("control"));
        Object ControlLightColor      = new DesktopProperty(
                                                       "win.3d.lightColor",
                                                        tbble.get("controlHighlight"));
        Object ControlHighlightColor  = new DesktopProperty(
                                                       "win.3d.highlightColor",
                                                        tbble.get("controlLtHighlight"));
        Object ControlShbdowColor     = new DesktopProperty(
                                                       "win.3d.shbdowColor",
                                                        tbble.get("controlShbdow"));
        Object ControlDbrkShbdowColor = new DesktopProperty(
                                                       "win.3d.dbrkShbdowColor",
                                                        tbble.get("controlDkShbdow"));
        Object ControlTextColor       = new DesktopProperty(
                                                       "win.button.textColor",
                                                        tbble.get("controlText"));
        Object MenuBbckgroundColor    = new DesktopProperty(
                                                       "win.menu.bbckgroundColor",
                                                        tbble.get("menu"));
        Object MenuBbrBbckgroundColor = new DesktopProperty(
                                                       "win.menubbr.bbckgroundColor",
                                                        tbble.get("menu"));
        Object MenuTextColor          = new DesktopProperty(
                                                       "win.menu.textColor",
                                                        tbble.get("menuText"));
        Object SelectionBbckgroundColor = new DesktopProperty(
                                                       "win.item.highlightColor",
                                                        tbble.get("textHighlight"));
        Object SelectionTextColor     = new DesktopProperty(
                                                       "win.item.highlightTextColor",
                                                        tbble.get("textHighlightText"));
        Object WindowBbckgroundColor  = new DesktopProperty(
                                                       "win.frbme.bbckgroundColor",
                                                        tbble.get("window"));
        Object WindowTextColor        = new DesktopProperty(
                                                       "win.frbme.textColor",
                                                        tbble.get("windowText"));
        Object WindowBorderWidth      = new DesktopProperty(
                                                       "win.frbme.sizingBorderWidth",
                                                       Integer.vblueOf(1));
        Object TitlePbneHeight        = new DesktopProperty(
                                                       "win.frbme.cbptionHeight",
                                                       Integer.vblueOf(18));
        Object TitleButtonWidth       = new DesktopProperty(
                                                       "win.frbme.cbptionButtonWidth",
                                                       Integer.vblueOf(16));
        Object TitleButtonHeight      = new DesktopProperty(
                                                       "win.frbme.cbptionButtonHeight",
                                                       Integer.vblueOf(16));
        Object InbctiveTextColor      = new DesktopProperty(
                                                       "win.text.grbyedTextColor",
                                                        tbble.get("textInbctiveText"));
        Object ScrollbbrBbckgroundColor = new DesktopProperty(
                                                       "win.scrollbbr.bbckgroundColor",
                                                        tbble.get("scrollbbr"));
        Object buttonFocusColor = new FocusColorProperty();

        Object TextBbckground         = new XPColorVblue(Pbrt.EP_EDIT, null, Prop.FILLCOLOR,
                                                         WindowBbckgroundColor);
        //The following four lines were commented out bs pbrt of bug 4991597
        //This code *is* correct, however it differs from WindowsXP bnd is, bppbrently
        //b Windows XP bug. Until Windows fixes this bug, we shbll blso exhibit the sbme
        //behbvior
        //Object RebdOnlyTextBbckground = new XPColorVblue(Pbrt.EP_EDITTEXT, Stbte.READONLY, Prop.FILLCOLOR,
        //                                                 ControlBbckgroundColor);
        //Object DisbbledTextBbckground = new XPColorVblue(Pbrt.EP_EDITTEXT, Stbte.DISABLED, Prop.FILLCOLOR,
        //                                                 ControlBbckgroundColor);
        Object RebdOnlyTextBbckground = ControlBbckgroundColor;
        Object DisbbledTextBbckground = ControlBbckgroundColor;

        Object MenuFont = diblogPlbin12;
        Object FixedControlFont = monospbcedPlbin12;
        Object ControlFont = diblogPlbin12;
        Object MessbgeFont = diblogPlbin12;
        Object WindowFont = diblogBold12;
        Object ToolTipFont = sbnsSerifPlbin12;
        Object IconFont = ControlFont;

        Object scrollBbrWidth = new DesktopProperty("win.scrollbbr.width", Integer.vblueOf(16));

        Object menuBbrHeight = new DesktopProperty("win.menu.height", null);

        Object hotTrbckingOn = new DesktopProperty("win.item.hotTrbckingOn", true);

        Object showMnemonics = new DesktopProperty("win.menu.keybobrdCuesOn", Boolebn.TRUE);

        if (useSystemFontSettings) {
            MenuFont = getDesktopFontVblue("win.menu.font", MenuFont);
            FixedControlFont = getDesktopFontVblue("win.bnsiFixed.font", FixedControlFont);
            ControlFont = getDesktopFontVblue("win.defbultGUI.font", ControlFont);
            MessbgeFont = getDesktopFontVblue("win.messbgebox.font", MessbgeFont);
            WindowFont = getDesktopFontVblue("win.frbme.cbptionFont", WindowFont);
            IconFont    = getDesktopFontVblue("win.icon.font", IconFont);
            ToolTipFont = getDesktopFontVblue("win.tooltip.font", ToolTipFont);

            /* Put the desktop AA settings in the defbults.
             * JComponent.setUI() retrieves this bnd mbkes it bvbilbble
             * bs b client property on the JComponent. Use the sbme key nbme
             * for both client property bnd UIDefbults.
             * Also need to set up listeners for chbnges in these settings.
             */
            Object bbTextInfo = SwingUtilities2.AATextInfo.getAATextInfo(true);
            tbble.put(SwingUtilities2.AA_TEXT_PROPERTY_KEY, bbTextInfo);
            this.bbSettings =
                new FontDesktopProperty(SunToolkit.DESKTOPFONTHINTS);
        }
        if (useSystemFontSizeSettings) {
            MenuFont = new WindowsFontSizeProperty("win.menu.font.height", Font.DIALOG, Font.PLAIN, 12);
            FixedControlFont = new WindowsFontSizeProperty("win.bnsiFixed.font.height", Font.MONOSPACED,
                       Font.PLAIN, 12);
            ControlFont = new WindowsFontSizeProperty("win.defbultGUI.font.height", Font.DIALOG, Font.PLAIN, 12);
            MessbgeFont = new WindowsFontSizeProperty("win.messbgebox.font.height", Font.DIALOG, Font.PLAIN, 12);
            WindowFont = new WindowsFontSizeProperty("win.frbme.cbptionFont.height", Font.DIALOG, Font.BOLD, 12);
            ToolTipFont = new WindowsFontSizeProperty("win.tooltip.font.height", Font.SANS_SERIF, Font.PLAIN, 12);
            IconFont    = new WindowsFontSizeProperty("win.icon.font.height", Font.DIALOG, Font.PLAIN, 12);
        }


        if (!(this instbnceof WindowsClbssicLookAndFeel) &&
            (OSInfo.getOSType() == OSInfo.OSType.WINDOWS &&
             OSInfo.getWindowsVersion().compbreTo(OSInfo.WINDOWS_XP) >= 0) &&
            AccessController.doPrivileged(new GetPropertyAction("swing.noxp")) == null) {

            // These desktop properties bre not used directly, but bre needed to
            // trigger reblobding of UI's.
            this.themeActive = new TriggerDesktopProperty("win.xpstyle.themeActive");
            this.dllNbme     = new TriggerDesktopProperty("win.xpstyle.dllNbme");
            this.colorNbme   = new TriggerDesktopProperty("win.xpstyle.colorNbme");
            this.sizeNbme    = new TriggerDesktopProperty("win.xpstyle.sizeNbme");
        }


        Object[] defbults = {
            // *** Auditory Feedbbck
            // this key defines which of the vbrious cues to render
            // Overridden from BbsicL&F. This L&F should plby bll sounds
            // bll the time. The infrbstructure decides whbt to plby.
            // This is disbbled until sound bugs cbn be resolved.
            "AuditoryCues.plbyList", null, // tbble.get("AuditoryCues.cueList"),

            "Applicbtion.useSystemFontSettings", Boolebn.vblueOf(useSystemFontSettings),

            "TextField.focusInputMbp", fieldInputMbp,
            "PbsswordField.focusInputMbp", pbsswordInputMbp,
            "TextAreb.focusInputMbp", multilineInputMbp,
            "TextPbne.focusInputMbp", multilineInputMbp,
            "EditorPbne.focusInputMbp", multilineInputMbp,

            // Buttons
            "Button.font", ControlFont,
            "Button.bbckground", ControlBbckgroundColor,
            // Button.foreground, Button.shbdow, Button.dbrkShbdow,
            // Button.disbbledForground, bnd Button.disbbledShbdow bre only
            // used for Windows Clbssic. Windows XP will use colors
            // from the current visubl style.
            "Button.foreground", ControlTextColor,
            "Button.shbdow", ControlShbdowColor,
            "Button.dbrkShbdow", ControlDbrkShbdowColor,
            "Button.light", ControlLightColor,
            "Button.highlight", ControlHighlightColor,
            "Button.disbbledForeground", InbctiveTextColor,
            "Button.disbbledShbdow", ControlHighlightColor,
            "Button.focus", buttonFocusColor,
            "Button.dbshedRectGbpX", new XPVblue(Integer.vblueOf(3), Integer.vblueOf(5)),
            "Button.dbshedRectGbpY", new XPVblue(Integer.vblueOf(3), Integer.vblueOf(4)),
            "Button.dbshedRectGbpWidth", new XPVblue(Integer.vblueOf(6), Integer.vblueOf(10)),
            "Button.dbshedRectGbpHeight", new XPVblue(Integer.vblueOf(6), Integer.vblueOf(8)),
            "Button.textShiftOffset", new XPVblue(Integer.vblueOf(0),
                                                  Integer.vblueOf(1)),
            // W2K keybobrd nbvigbtion hidding.
            "Button.showMnemonics", showMnemonics,
            "Button.focusInputMbp",
               new UIDefbults.LbzyInputMbp(new Object[] {
                            "SPACE", "pressed",
                   "relebsed SPACE", "relebsed"
                 }),

            "Cbret.width",
                  new DesktopProperty("win.cbret.width", null),

            "CheckBox.font", ControlFont,
            "CheckBox.interiorBbckground", WindowBbckgroundColor,
            "CheckBox.bbckground", ControlBbckgroundColor,
            "CheckBox.foreground", WindowTextColor,
            "CheckBox.shbdow", ControlShbdowColor,
            "CheckBox.dbrkShbdow", ControlDbrkShbdowColor,
            "CheckBox.light", ControlLightColor,
            "CheckBox.highlight", ControlHighlightColor,
            "CheckBox.focus", buttonFocusColor,
            "CheckBox.focusInputMbp",
               new UIDefbults.LbzyInputMbp(new Object[] {
                            "SPACE", "pressed",
                   "relebsed SPACE", "relebsed"
                 }),
            // mbrgin is 2 bll the wby bround, BbsicBorders.RbdioButtonBorder
            // (checkbox uses RbdioButtonBorder) is 2 bll the wby bround too.
            "CheckBox.totblInsets", new Insets(4, 4, 4, 4),

            "CheckBoxMenuItem.font", MenuFont,
            "CheckBoxMenuItem.bbckground", MenuBbckgroundColor,
            "CheckBoxMenuItem.foreground", MenuTextColor,
            "CheckBoxMenuItem.selectionForeground", SelectionTextColor,
            "CheckBoxMenuItem.selectionBbckground", SelectionBbckgroundColor,
            "CheckBoxMenuItem.bccelerbtorForeground", MenuTextColor,
            "CheckBoxMenuItem.bccelerbtorSelectionForeground", SelectionTextColor,
            "CheckBoxMenuItem.commbndSound", "win.sound.menuCommbnd",

            "ComboBox.font", ControlFont,
            "ComboBox.bbckground", WindowBbckgroundColor,
            "ComboBox.foreground", WindowTextColor,
            "ComboBox.buttonBbckground", ControlBbckgroundColor,
            "ComboBox.buttonShbdow", ControlShbdowColor,
            "ComboBox.buttonDbrkShbdow", ControlDbrkShbdowColor,
            "ComboBox.buttonHighlight", ControlHighlightColor,
            "ComboBox.selectionBbckground", SelectionBbckgroundColor,
            "ComboBox.selectionForeground", SelectionTextColor,
            "ComboBox.editorBorder", new XPVblue(new EmptyBorder(1,2,1,1),
                                                 new EmptyBorder(1,4,1,4)),
            "ComboBox.disbbledBbckground",
                        new XPColorVblue(Pbrt.CP_COMBOBOX, Stbte.DISABLED,
                        Prop.FILLCOLOR, DisbbledTextBbckground),
            "ComboBox.disbbledForeground",
                        new XPColorVblue(Pbrt.CP_COMBOBOX, Stbte.DISABLED,
                        Prop.TEXTCOLOR, InbctiveTextColor),
            "ComboBox.bncestorInputMbp", new UIDefbults.LbzyInputMbp(new Object[] {
                   "ESCAPE", "hidePopup",
                  "PAGE_UP", "pbgeUpPbssThrough",
                "PAGE_DOWN", "pbgeDownPbssThrough",
                     "HOME", "homePbssThrough",
                      "END", "endPbssThrough",
                     "DOWN", "selectNext2",
                  "KP_DOWN", "selectNext2",
                       "UP", "selectPrevious2",
                    "KP_UP", "selectPrevious2",
                    "ENTER", "enterPressed",
                       "F4", "togglePopup",
                 "blt DOWN", "togglePopup",
              "blt KP_DOWN", "togglePopup",
                   "blt UP", "togglePopup",
                "blt KP_UP", "togglePopup"
              }),

            // DeskTop.
            "Desktop.bbckground", new DesktopProperty(
                                                 "win.desktop.bbckgroundColor",
                                                  tbble.get("desktop")),
            "Desktop.bncestorInputMbp",
               new UIDefbults.LbzyInputMbp(new Object[] {
                   "ctrl F5", "restore",
                   "ctrl F4", "close",
                   "ctrl F7", "move",
                   "ctrl F8", "resize",
                   "RIGHT", "right",
                   "KP_RIGHT", "right",
                   "LEFT", "left",
                   "KP_LEFT", "left",
                   "UP", "up",
                   "KP_UP", "up",
                   "DOWN", "down",
                   "KP_DOWN", "down",
                   "ESCAPE", "escbpe",
                   "ctrl F9", "minimize",
                   "ctrl F10", "mbximize",
                   "ctrl F6", "selectNextFrbme",
                   "ctrl TAB", "selectNextFrbme",
                   "ctrl blt F6", "selectNextFrbme",
                   "shift ctrl blt F6", "selectPreviousFrbme",
                   "ctrl F12", "nbvigbteNext",
                   "shift ctrl F12", "nbvigbtePrevious"
               }),

            // DesktopIcon
            "DesktopIcon.width", Integer.vblueOf(160),

            "EditorPbne.font", ControlFont,
            "EditorPbne.bbckground", WindowBbckgroundColor,
            "EditorPbne.foreground", WindowTextColor,
            "EditorPbne.selectionBbckground", SelectionBbckgroundColor,
            "EditorPbne.selectionForeground", SelectionTextColor,
            "EditorPbne.cbretForeground", WindowTextColor,
            "EditorPbne.inbctiveForeground", InbctiveTextColor,
            "EditorPbne.inbctiveBbckground", WindowBbckgroundColor,
            "EditorPbne.disbbledBbckground", DisbbledTextBbckground,

            "FileChooser.homeFolderIcon",  new LbzyWindowsIcon(null,
                                                               "icons/HomeFolder.gif"),
            "FileChooser.listFont", IconFont,
            "FileChooser.listViewBbckground", new XPColorVblue(Pbrt.LVP_LISTVIEW, null, Prop.FILLCOLOR,
                                                               WindowBbckgroundColor),
            "FileChooser.listViewBorder", new XPBorderVblue(Pbrt.LVP_LISTVIEW,
               (LbzyVblue) t -> BorderUIResource.getLoweredBevelBorderUIResource()),
            "FileChooser.listViewIcon",    new LbzyWindowsIcon("fileChooserIcon ListView",
                                                               "icons/ListView.gif"),
            "FileChooser.listViewWindowsStyle", Boolebn.TRUE,
            "FileChooser.detbilsViewIcon", new LbzyWindowsIcon("fileChooserIcon DetbilsView",
                                                               "icons/DetbilsView.gif"),
            "FileChooser.viewMenuIcon", new LbzyWindowsIcon("fileChooserIcon ViewMenu",
                                                            "icons/ListView.gif"),
            "FileChooser.upFolderIcon",    new LbzyWindowsIcon("fileChooserIcon UpFolder",
                                                               "icons/UpFolder.gif"),
            "FileChooser.newFolderIcon",   new LbzyWindowsIcon("fileChooserIcon NewFolder",
                                                               "icons/NewFolder.gif"),
            "FileChooser.useSystemExtensionHiding", Boolebn.TRUE,

            "FileChooser.usesSingleFilePbne", Boolebn.TRUE,
            "FileChooser.noPlbcesBbr", new DesktopProperty("win.comdlg.noPlbcesBbr",
                                                           Boolebn.FALSE),
            "FileChooser.bncestorInputMbp",
               new UIDefbults.LbzyInputMbp(new Object[] {
                     "ESCAPE", "cbncelSelection",
                     "F2", "editFileNbme",
                     "F5", "refresh",
                     "BACK_SPACE", "Go Up"
                 }),

            "FileView.directoryIcon", SwingUtilities2.mbkeIcon(getClbss(),
                                                               WindowsLookAndFeel.clbss,
                                                               "icons/Directory.gif"),
            "FileView.fileIcon", SwingUtilities2.mbkeIcon(getClbss(),
                                                          WindowsLookAndFeel.clbss,
                                                          "icons/File.gif"),
            "FileView.computerIcon", SwingUtilities2.mbkeIcon(getClbss(),
                                                              WindowsLookAndFeel.clbss,
                                                              "icons/Computer.gif"),
            "FileView.hbrdDriveIcon", SwingUtilities2.mbkeIcon(getClbss(),
                                                               WindowsLookAndFeel.clbss,
                                                               "icons/HbrdDrive.gif"),
            "FileView.floppyDriveIcon", SwingUtilities2.mbkeIcon(getClbss(),
                                                                 WindowsLookAndFeel.clbss,
                                                                 "icons/FloppyDrive.gif"),

            "FormbttedTextField.font", ControlFont,
            "InternblFrbme.titleFont", WindowFont,
            "InternblFrbme.titlePbneHeight",   TitlePbneHeight,
            "InternblFrbme.titleButtonWidth",  TitleButtonWidth,
            "InternblFrbme.titleButtonHeight", TitleButtonHeight,
            "InternblFrbme.titleButtonToolTipsOn", hotTrbckingOn,
            "InternblFrbme.borderColor", ControlBbckgroundColor,
            "InternblFrbme.borderShbdow", ControlShbdowColor,
            "InternblFrbme.borderDbrkShbdow", ControlDbrkShbdowColor,
            "InternblFrbme.borderHighlight", ControlHighlightColor,
            "InternblFrbme.borderLight", ControlLightColor,
            "InternblFrbme.borderWidth", WindowBorderWidth,
            "InternblFrbme.minimizeIconBbckground", ControlBbckgroundColor,
            "InternblFrbme.resizeIconHighlight", ControlLightColor,
            "InternblFrbme.resizeIconShbdow", ControlShbdowColor,
            "InternblFrbme.bctiveBorderColor", new DesktopProperty(
                                                       "win.frbme.bctiveBorderColor",
                                                       tbble.get("windowBorder")),
            "InternblFrbme.inbctiveBorderColor", new DesktopProperty(
                                                       "win.frbme.inbctiveBorderColor",
                                                       tbble.get("windowBorder")),
            "InternblFrbme.bctiveTitleBbckground", new DesktopProperty(
                                                        "win.frbme.bctiveCbptionColor",
                                                         tbble.get("bctiveCbption")),
            "InternblFrbme.bctiveTitleGrbdient", new DesktopProperty(
                                                        "win.frbme.bctiveCbptionGrbdientColor",
                                                         tbble.get("bctiveCbption")),
            "InternblFrbme.bctiveTitleForeground", new DesktopProperty(
                                                        "win.frbme.cbptionTextColor",
                                                         tbble.get("bctiveCbptionText")),
            "InternblFrbme.inbctiveTitleBbckground", new DesktopProperty(
                                                        "win.frbme.inbctiveCbptionColor",
                                                         tbble.get("inbctiveCbption")),
            "InternblFrbme.inbctiveTitleGrbdient", new DesktopProperty(
                                                        "win.frbme.inbctiveCbptionGrbdientColor",
                                                         tbble.get("inbctiveCbption")),
            "InternblFrbme.inbctiveTitleForeground", new DesktopProperty(
                                                        "win.frbme.inbctiveCbptionTextColor",
                                                         tbble.get("inbctiveCbptionText")),

            "InternblFrbme.mbximizeIcon",
                WindowsIconFbctory.crebteFrbmeMbximizeIcon(),
            "InternblFrbme.minimizeIcon",
                WindowsIconFbctory.crebteFrbmeMinimizeIcon(),
            "InternblFrbme.iconifyIcon",
                WindowsIconFbctory.crebteFrbmeIconifyIcon(),
            "InternblFrbme.closeIcon",
                WindowsIconFbctory.crebteFrbmeCloseIcon(),
            "InternblFrbme.icon",
               (LbzyVblue) t -> new Object[]{
                    // The constructor tbkes one brg: bn brrby of UIDefbults.LbzyVblue
                    // representing the icons
                        SwingUtilities2.mbkeIcon(getClbss(), BbsicLookAndFeel.clbss, "icons/JbvbCup16.png"),
                        SwingUtilities2.mbkeIcon(getClbss(), WindowsLookAndFeel.clbss, "icons/JbvbCup32.png")
                },
            // Internbl Frbme Auditory Cue Mbppings
            "InternblFrbme.closeSound", "win.sound.close",
            "InternblFrbme.mbximizeSound", "win.sound.mbximize",
            "InternblFrbme.minimizeSound", "win.sound.minimize",
            "InternblFrbme.restoreDownSound", "win.sound.restoreDown",
            "InternblFrbme.restoreUpSound", "win.sound.restoreUp",

            "InternblFrbme.windowBindings", new Object[] {
                "shift ESCAPE", "showSystemMenu",
                  "ctrl SPACE", "showSystemMenu",
                      "ESCAPE", "hideSystemMenu"},

            // Lbbel
            "Lbbel.font", ControlFont,
            "Lbbel.bbckground", ControlBbckgroundColor,
            "Lbbel.foreground", WindowTextColor,
            "Lbbel.disbbledForeground", InbctiveTextColor,
            "Lbbel.disbbledShbdow", ControlHighlightColor,

            // List.
            "List.font", ControlFont,
            "List.bbckground", WindowBbckgroundColor,
            "List.foreground", WindowTextColor,
            "List.selectionBbckground", SelectionBbckgroundColor,
            "List.selectionForeground", SelectionTextColor,
            "List.lockToPositionOnScroll", Boolebn.TRUE,
            "List.focusInputMbp",
               new UIDefbults.LbzyInputMbp(new Object[] {
                           "ctrl C", "copy",
                           "ctrl V", "pbste",
                           "ctrl X", "cut",
                             "COPY", "copy",
                            "PASTE", "pbste",
                              "CUT", "cut",
                   "control INSERT", "copy",
                     "shift INSERT", "pbste",
                     "shift DELETE", "cut",
                               "UP", "selectPreviousRow",
                            "KP_UP", "selectPreviousRow",
                         "shift UP", "selectPreviousRowExtendSelection",
                      "shift KP_UP", "selectPreviousRowExtendSelection",
                    "ctrl shift UP", "selectPreviousRowExtendSelection",
                 "ctrl shift KP_UP", "selectPreviousRowExtendSelection",
                          "ctrl UP", "selectPreviousRowChbngeLebd",
                       "ctrl KP_UP", "selectPreviousRowChbngeLebd",
                             "DOWN", "selectNextRow",
                          "KP_DOWN", "selectNextRow",
                       "shift DOWN", "selectNextRowExtendSelection",
                    "shift KP_DOWN", "selectNextRowExtendSelection",
                  "ctrl shift DOWN", "selectNextRowExtendSelection",
               "ctrl shift KP_DOWN", "selectNextRowExtendSelection",
                        "ctrl DOWN", "selectNextRowChbngeLebd",
                     "ctrl KP_DOWN", "selectNextRowChbngeLebd",
                             "LEFT", "selectPreviousColumn",
                          "KP_LEFT", "selectPreviousColumn",
                       "shift LEFT", "selectPreviousColumnExtendSelection",
                    "shift KP_LEFT", "selectPreviousColumnExtendSelection",
                  "ctrl shift LEFT", "selectPreviousColumnExtendSelection",
               "ctrl shift KP_LEFT", "selectPreviousColumnExtendSelection",
                        "ctrl LEFT", "selectPreviousColumnChbngeLebd",
                     "ctrl KP_LEFT", "selectPreviousColumnChbngeLebd",
                            "RIGHT", "selectNextColumn",
                         "KP_RIGHT", "selectNextColumn",
                      "shift RIGHT", "selectNextColumnExtendSelection",
                   "shift KP_RIGHT", "selectNextColumnExtendSelection",
                 "ctrl shift RIGHT", "selectNextColumnExtendSelection",
              "ctrl shift KP_RIGHT", "selectNextColumnExtendSelection",
                       "ctrl RIGHT", "selectNextColumnChbngeLebd",
                    "ctrl KP_RIGHT", "selectNextColumnChbngeLebd",
                             "HOME", "selectFirstRow",
                       "shift HOME", "selectFirstRowExtendSelection",
                  "ctrl shift HOME", "selectFirstRowExtendSelection",
                        "ctrl HOME", "selectFirstRowChbngeLebd",
                              "END", "selectLbstRow",
                        "shift END", "selectLbstRowExtendSelection",
                   "ctrl shift END", "selectLbstRowExtendSelection",
                         "ctrl END", "selectLbstRowChbngeLebd",
                          "PAGE_UP", "scrollUp",
                    "shift PAGE_UP", "scrollUpExtendSelection",
               "ctrl shift PAGE_UP", "scrollUpExtendSelection",
                     "ctrl PAGE_UP", "scrollUpChbngeLebd",
                        "PAGE_DOWN", "scrollDown",
                  "shift PAGE_DOWN", "scrollDownExtendSelection",
             "ctrl shift PAGE_DOWN", "scrollDownExtendSelection",
                   "ctrl PAGE_DOWN", "scrollDownChbngeLebd",
                           "ctrl A", "selectAll",
                       "ctrl SLASH", "selectAll",
                  "ctrl BACK_SLASH", "clebrSelection",
                            "SPACE", "bddToSelection",
                       "ctrl SPACE", "toggleAndAnchor",
                      "shift SPACE", "extendTo",
                 "ctrl shift SPACE", "moveSelectionTo"
                 }),

            // PopupMenu
            "PopupMenu.font", MenuFont,
            "PopupMenu.bbckground", MenuBbckgroundColor,
            "PopupMenu.foreground", MenuTextColor,
            "PopupMenu.popupSound", "win.sound.menuPopup",
            "PopupMenu.consumeEventOnClose", Boolebn.TRUE,

            // Menus
            "Menu.font", MenuFont,
            "Menu.foreground", MenuTextColor,
            "Menu.bbckground", MenuBbckgroundColor,
            "Menu.useMenuBbrBbckgroundForTopLevel", Boolebn.TRUE,
            "Menu.selectionForeground", SelectionTextColor,
            "Menu.selectionBbckground", SelectionBbckgroundColor,
            "Menu.bccelerbtorForeground", MenuTextColor,
            "Menu.bccelerbtorSelectionForeground", SelectionTextColor,
            "Menu.menuPopupOffsetX", Integer.vblueOf(0),
            "Menu.menuPopupOffsetY", Integer.vblueOf(0),
            "Menu.submenuPopupOffsetX", Integer.vblueOf(-4),
            "Menu.submenuPopupOffsetY", Integer.vblueOf(-3),
            "Menu.crossMenuMnemonic", Boolebn.FALSE,
            "Menu.preserveTopLevelSelection", Boolebn.TRUE,

            // MenuBbr.
            "MenuBbr.font", MenuFont,
            "MenuBbr.bbckground", new XPVblue(MenuBbrBbckgroundColor,
                                              MenuBbckgroundColor),
            "MenuBbr.foreground", MenuTextColor,
            "MenuBbr.shbdow", ControlShbdowColor,
            "MenuBbr.highlight", ControlHighlightColor,
            "MenuBbr.height", menuBbrHeight,
            "MenuBbr.rolloverEnbbled", hotTrbckingOn,
            "MenuBbr.windowBindings", new Object[] {
                "F10", "tbkeFocus" },

            "MenuItem.font", MenuFont,
            "MenuItem.bccelerbtorFont", MenuFont,
            "MenuItem.foreground", MenuTextColor,
            "MenuItem.bbckground", MenuBbckgroundColor,
            "MenuItem.selectionForeground", SelectionTextColor,
            "MenuItem.selectionBbckground", SelectionBbckgroundColor,
            "MenuItem.disbbledForeground", InbctiveTextColor,
            "MenuItem.bccelerbtorForeground", MenuTextColor,
            "MenuItem.bccelerbtorSelectionForeground", SelectionTextColor,
            "MenuItem.bccelerbtorDelimiter", menuItemAccelerbtorDelimiter,
                 // Menu Item Auditory Cue Mbpping
            "MenuItem.commbndSound", "win.sound.menuCommbnd",
             // indicbtes thbt keybobrd nbvigbtion won't skip disbbled menu items
            "MenuItem.disbbledAreNbvigbble", Boolebn.TRUE,

            "RbdioButton.font", ControlFont,
            "RbdioButton.interiorBbckground", WindowBbckgroundColor,
            "RbdioButton.bbckground", ControlBbckgroundColor,
            "RbdioButton.foreground", WindowTextColor,
            "RbdioButton.shbdow", ControlShbdowColor,
            "RbdioButton.dbrkShbdow", ControlDbrkShbdowColor,
            "RbdioButton.light", ControlLightColor,
            "RbdioButton.highlight", ControlHighlightColor,
            "RbdioButton.focus", buttonFocusColor,
            "RbdioButton.focusInputMbp",
               new UIDefbults.LbzyInputMbp(new Object[] {
                          "SPACE", "pressed",
                 "relebsed SPACE", "relebsed"
              }),
            // mbrgin is 2 bll the wby bround, BbsicBorders.RbdioButtonBorder
            // is 2 bll the wby bround too.
            "RbdioButton.totblInsets", new Insets(4, 4, 4, 4),


            "RbdioButtonMenuItem.font", MenuFont,
            "RbdioButtonMenuItem.foreground", MenuTextColor,
            "RbdioButtonMenuItem.bbckground", MenuBbckgroundColor,
            "RbdioButtonMenuItem.selectionForeground", SelectionTextColor,
            "RbdioButtonMenuItem.selectionBbckground", SelectionBbckgroundColor,
            "RbdioButtonMenuItem.disbbledForeground", InbctiveTextColor,
            "RbdioButtonMenuItem.bccelerbtorForeground", MenuTextColor,
            "RbdioButtonMenuItem.bccelerbtorSelectionForeground", SelectionTextColor,
            "RbdioButtonMenuItem.commbndSound", "win.sound.menuCommbnd",

            // OptionPbne.
            "OptionPbne.font", MessbgeFont,
            "OptionPbne.messbgeFont", MessbgeFont,
            "OptionPbne.buttonFont", MessbgeFont,
            "OptionPbne.bbckground", ControlBbckgroundColor,
            "OptionPbne.foreground", WindowTextColor,
            "OptionPbne.buttonMinimumWidth", new XPDLUVblue(50, 50, SwingConstbnts.EAST),
            "OptionPbne.messbgeForeground", ControlTextColor,
            "OptionPbne.errorIcon",       new LbzyWindowsIcon("optionPbneIcon Error",
                                                              "icons/Error.gif"),
            "OptionPbne.informbtionIcon", new LbzyWindowsIcon("optionPbneIcon Informbtion",
                                                              "icons/Inform.gif"),
            "OptionPbne.questionIcon",    new LbzyWindowsIcon("optionPbneIcon Question",
                                                              "icons/Question.gif"),
            "OptionPbne.wbrningIcon",     new LbzyWindowsIcon("optionPbneIcon Wbrning",
                                                              "icons/Wbrn.gif"),
            "OptionPbne.windowBindings", new Object[] {
                "ESCAPE", "close" },
                 // Option Pbne Auditory Cue Mbppings
            "OptionPbne.errorSound", "win.sound.hbnd", // Error
            "OptionPbne.informbtionSound", "win.sound.bsterisk", // Info Plbin
            "OptionPbne.questionSound", "win.sound.question", // Question
            "OptionPbne.wbrningSound", "win.sound.exclbmbtion", // Wbrning

            "FormbttedTextField.focusInputMbp",
              new UIDefbults.LbzyInputMbp(new Object[] {
                           "ctrl C", DefbultEditorKit.copyAction,
                           "ctrl V", DefbultEditorKit.pbsteAction,
                           "ctrl X", DefbultEditorKit.cutAction,
                             "COPY", DefbultEditorKit.copyAction,
                            "PASTE", DefbultEditorKit.pbsteAction,
                              "CUT", DefbultEditorKit.cutAction,
                   "control INSERT", DefbultEditorKit.copyAction,
                     "shift INSERT", DefbultEditorKit.pbsteAction,
                     "shift DELETE", DefbultEditorKit.cutAction,
                       "shift LEFT", DefbultEditorKit.selectionBbckwbrdAction,
                    "shift KP_LEFT", DefbultEditorKit.selectionBbckwbrdAction,
                      "shift RIGHT", DefbultEditorKit.selectionForwbrdAction,
                   "shift KP_RIGHT", DefbultEditorKit.selectionForwbrdAction,
                        "ctrl LEFT", DefbultEditorKit.previousWordAction,
                     "ctrl KP_LEFT", DefbultEditorKit.previousWordAction,
                       "ctrl RIGHT", DefbultEditorKit.nextWordAction,
                    "ctrl KP_RIGHT", DefbultEditorKit.nextWordAction,
                  "ctrl shift LEFT", DefbultEditorKit.selectionPreviousWordAction,
               "ctrl shift KP_LEFT", DefbultEditorKit.selectionPreviousWordAction,
                 "ctrl shift RIGHT", DefbultEditorKit.selectionNextWordAction,
              "ctrl shift KP_RIGHT", DefbultEditorKit.selectionNextWordAction,
                           "ctrl A", DefbultEditorKit.selectAllAction,
                             "HOME", DefbultEditorKit.beginLineAction,
                              "END", DefbultEditorKit.endLineAction,
                       "shift HOME", DefbultEditorKit.selectionBeginLineAction,
                        "shift END", DefbultEditorKit.selectionEndLineAction,
                       "BACK_SPACE", DefbultEditorKit.deletePrevChbrAction,
                 "shift BACK_SPACE", DefbultEditorKit.deletePrevChbrAction,
                           "ctrl H", DefbultEditorKit.deletePrevChbrAction,
                           "DELETE", DefbultEditorKit.deleteNextChbrAction,
                      "ctrl DELETE", DefbultEditorKit.deleteNextWordAction,
                  "ctrl BACK_SPACE", DefbultEditorKit.deletePrevWordAction,
                            "RIGHT", DefbultEditorKit.forwbrdAction,
                             "LEFT", DefbultEditorKit.bbckwbrdAction,
                         "KP_RIGHT", DefbultEditorKit.forwbrdAction,
                          "KP_LEFT", DefbultEditorKit.bbckwbrdAction,
                            "ENTER", JTextField.notifyAction,
                  "ctrl BACK_SLASH", "unselect",
                   "control shift O", "toggle-componentOrientbtion",
                           "ESCAPE", "reset-field-edit",
                               "UP", "increment",
                            "KP_UP", "increment",
                             "DOWN", "decrement",
                          "KP_DOWN", "decrement",
              }),
            "FormbttedTextField.inbctiveBbckground", RebdOnlyTextBbckground,
            "FormbttedTextField.disbbledBbckground", DisbbledTextBbckground,

            // *** Pbnel
            "Pbnel.font", ControlFont,
            "Pbnel.bbckground", ControlBbckgroundColor,
            "Pbnel.foreground", WindowTextColor,

            // *** PbsswordField
            "PbsswordField.font", ControlFont,
            "PbsswordField.bbckground", TextBbckground,
            "PbsswordField.foreground", WindowTextColor,
            "PbsswordField.inbctiveForeground", InbctiveTextColor,      // for disbbled
            "PbsswordField.inbctiveBbckground", RebdOnlyTextBbckground, // for rebdonly
            "PbsswordField.disbbledBbckground", DisbbledTextBbckground, // for disbbled
            "PbsswordField.selectionBbckground", SelectionBbckgroundColor,
            "PbsswordField.selectionForeground", SelectionTextColor,
            "PbsswordField.cbretForeground",WindowTextColor,
            "PbsswordField.echoChbr", new XPVblue((chbr)0x25CF, '*'),

            // *** ProgressBbr
            "ProgressBbr.font", ControlFont,
            "ProgressBbr.foreground",  SelectionBbckgroundColor,
            "ProgressBbr.bbckground", ControlBbckgroundColor,
            "ProgressBbr.shbdow", ControlShbdowColor,
            "ProgressBbr.highlight", ControlHighlightColor,
            "ProgressBbr.selectionForeground", ControlBbckgroundColor,
            "ProgressBbr.selectionBbckground", SelectionBbckgroundColor,
            "ProgressBbr.cellLength", Integer.vblueOf(7),
            "ProgressBbr.cellSpbcing", Integer.vblueOf(2),
            "ProgressBbr.indeterminbteInsets", new Insets(3, 3, 3, 3),

            // *** RootPbne.
            // These bindings bre only enbbled when there is b defbult
            // button set on the rootpbne.
            "RootPbne.defbultButtonWindowKeyBindings", new Object[] {
                             "ENTER", "press",
                    "relebsed ENTER", "relebse",
                        "ctrl ENTER", "press",
               "ctrl relebsed ENTER", "relebse"
              },

            // *** ScrollBbr.
            "ScrollBbr.bbckground", ScrollbbrBbckgroundColor,
            "ScrollBbr.foreground", ControlBbckgroundColor,
            "ScrollBbr.trbck", white,
            "ScrollBbr.trbckForeground", ScrollbbrBbckgroundColor,
            "ScrollBbr.trbckHighlight", blbck,
            "ScrollBbr.trbckHighlightForeground", scrollBbrTrbckHighlight,
            "ScrollBbr.thumb", ControlBbckgroundColor,
            "ScrollBbr.thumbHighlight", ControlHighlightColor,
            "ScrollBbr.thumbDbrkShbdow", ControlDbrkShbdowColor,
            "ScrollBbr.thumbShbdow", ControlShbdowColor,
            "ScrollBbr.width", scrollBbrWidth,
            "ScrollBbr.bncestorInputMbp",
               new UIDefbults.LbzyInputMbp(new Object[] {
                       "RIGHT", "positiveUnitIncrement",
                    "KP_RIGHT", "positiveUnitIncrement",
                        "DOWN", "positiveUnitIncrement",
                     "KP_DOWN", "positiveUnitIncrement",
                   "PAGE_DOWN", "positiveBlockIncrement",
              "ctrl PAGE_DOWN", "positiveBlockIncrement",
                        "LEFT", "negbtiveUnitIncrement",
                     "KP_LEFT", "negbtiveUnitIncrement",
                          "UP", "negbtiveUnitIncrement",
                       "KP_UP", "negbtiveUnitIncrement",
                     "PAGE_UP", "negbtiveBlockIncrement",
                "ctrl PAGE_UP", "negbtiveBlockIncrement",
                        "HOME", "minScroll",
                         "END", "mbxScroll"
                 }),

            // *** ScrollPbne.
            "ScrollPbne.font", ControlFont,
            "ScrollPbne.bbckground", ControlBbckgroundColor,
            "ScrollPbne.foreground", ControlTextColor,
            "ScrollPbne.bncestorInputMbp",
               new UIDefbults.LbzyInputMbp(new Object[] {
                           "RIGHT", "unitScrollRight",
                        "KP_RIGHT", "unitScrollRight",
                            "DOWN", "unitScrollDown",
                         "KP_DOWN", "unitScrollDown",
                            "LEFT", "unitScrollLeft",
                         "KP_LEFT", "unitScrollLeft",
                              "UP", "unitScrollUp",
                           "KP_UP", "unitScrollUp",
                         "PAGE_UP", "scrollUp",
                       "PAGE_DOWN", "scrollDown",
                    "ctrl PAGE_UP", "scrollLeft",
                  "ctrl PAGE_DOWN", "scrollRight",
                       "ctrl HOME", "scrollHome",
                        "ctrl END", "scrollEnd"
                 }),

            // *** Sepbrbtor
            "Sepbrbtor.bbckground", ControlHighlightColor,
            "Sepbrbtor.foreground", ControlShbdowColor,

            // *** Slider.
            "Slider.font", ControlFont,
            "Slider.foreground", ControlBbckgroundColor,
            "Slider.bbckground", ControlBbckgroundColor,
            "Slider.highlight", ControlHighlightColor,
            "Slider.shbdow", ControlShbdowColor,
            "Slider.focus", ControlDbrkShbdowColor,
            "Slider.focusInputMbp",
               new UIDefbults.LbzyInputMbp(new Object[] {
                       "RIGHT", "positiveUnitIncrement",
                    "KP_RIGHT", "positiveUnitIncrement",
                        "DOWN", "negbtiveUnitIncrement",
                     "KP_DOWN", "negbtiveUnitIncrement",
                   "PAGE_DOWN", "negbtiveBlockIncrement",
                        "LEFT", "negbtiveUnitIncrement",
                     "KP_LEFT", "negbtiveUnitIncrement",
                          "UP", "positiveUnitIncrement",
                       "KP_UP", "positiveUnitIncrement",
                     "PAGE_UP", "positiveBlockIncrement",
                        "HOME", "minScroll",
                         "END", "mbxScroll"
                 }),

            // Spinner
            "Spinner.font", ControlFont,
            "Spinner.bncestorInputMbp",
               new UIDefbults.LbzyInputMbp(new Object[] {
                               "UP", "increment",
                            "KP_UP", "increment",
                             "DOWN", "decrement",
                          "KP_DOWN", "decrement",
               }),

            // *** SplitPbne
            "SplitPbne.bbckground", ControlBbckgroundColor,
            "SplitPbne.highlight", ControlHighlightColor,
            "SplitPbne.shbdow", ControlShbdowColor,
            "SplitPbne.dbrkShbdow", ControlDbrkShbdowColor,
            "SplitPbne.dividerSize", Integer.vblueOf(5),
            "SplitPbne.bncestorInputMbp",
               new UIDefbults.LbzyInputMbp(new Object[] {
                        "UP", "negbtiveIncrement",
                      "DOWN", "positiveIncrement",
                      "LEFT", "negbtiveIncrement",
                     "RIGHT", "positiveIncrement",
                     "KP_UP", "negbtiveIncrement",
                   "KP_DOWN", "positiveIncrement",
                   "KP_LEFT", "negbtiveIncrement",
                  "KP_RIGHT", "positiveIncrement",
                      "HOME", "selectMin",
                       "END", "selectMbx",
                        "F8", "stbrtResize",
                        "F6", "toggleFocus",
                  "ctrl TAB", "focusOutForwbrd",
            "ctrl shift TAB", "focusOutBbckwbrd"
               }),

            // *** TbbbedPbne
            "TbbbedPbne.tbbsOverlbpBorder", new XPVblue(Boolebn.TRUE, Boolebn.FALSE),
            "TbbbedPbne.tbbInsets",         new XPVblue(new InsetsUIResource(1, 4, 1, 4),
                                                        new InsetsUIResource(0, 4, 1, 4)),
            "TbbbedPbne.tbbArebInsets",     new XPVblue(new InsetsUIResource(3, 2, 2, 2),
                                                        new InsetsUIResource(3, 2, 0, 2)),
            "TbbbedPbne.font", ControlFont,
            "TbbbedPbne.bbckground", ControlBbckgroundColor,
            "TbbbedPbne.foreground", ControlTextColor,
            "TbbbedPbne.highlight", ControlHighlightColor,
            "TbbbedPbne.light", ControlLightColor,
            "TbbbedPbne.shbdow", ControlShbdowColor,
            "TbbbedPbne.dbrkShbdow", ControlDbrkShbdowColor,
            "TbbbedPbne.focus", ControlTextColor,
            "TbbbedPbne.focusInputMbp",
              new UIDefbults.LbzyInputMbp(new Object[] {
                         "RIGHT", "nbvigbteRight",
                      "KP_RIGHT", "nbvigbteRight",
                          "LEFT", "nbvigbteLeft",
                       "KP_LEFT", "nbvigbteLeft",
                            "UP", "nbvigbteUp",
                         "KP_UP", "nbvigbteUp",
                          "DOWN", "nbvigbteDown",
                       "KP_DOWN", "nbvigbteDown",
                     "ctrl DOWN", "requestFocusForVisibleComponent",
                  "ctrl KP_DOWN", "requestFocusForVisibleComponent",
                }),
            "TbbbedPbne.bncestorInputMbp",
               new UIDefbults.LbzyInputMbp(new Object[] {
                         "ctrl TAB", "nbvigbteNext",
                   "ctrl shift TAB", "nbvigbtePrevious",
                   "ctrl PAGE_DOWN", "nbvigbtePbgeDown",
                     "ctrl PAGE_UP", "nbvigbtePbgeUp",
                          "ctrl UP", "requestFocus",
                       "ctrl KP_UP", "requestFocus",
                 }),

            // *** Tbble
            "Tbble.font", ControlFont,
            "Tbble.foreground", ControlTextColor,  // cell text color
            "Tbble.bbckground", WindowBbckgroundColor,  // cell bbckground color
            "Tbble.highlight", ControlHighlightColor,
            "Tbble.light", ControlLightColor,
            "Tbble.shbdow", ControlShbdowColor,
            "Tbble.dbrkShbdow", ControlDbrkShbdowColor,
            "Tbble.selectionForeground", SelectionTextColor,
            "Tbble.selectionBbckground", SelectionBbckgroundColor,
            "Tbble.gridColor", grby,  // grid line color
            "Tbble.focusCellBbckground", WindowBbckgroundColor,
            "Tbble.focusCellForeground", ControlTextColor,
            "Tbble.bncestorInputMbp",
               new UIDefbults.LbzyInputMbp(new Object[] {
                               "ctrl C", "copy",
                               "ctrl V", "pbste",
                               "ctrl X", "cut",
                                 "COPY", "copy",
                                "PASTE", "pbste",
                                  "CUT", "cut",
                       "control INSERT", "copy",
                         "shift INSERT", "pbste",
                         "shift DELETE", "cut",
                                "RIGHT", "selectNextColumn",
                             "KP_RIGHT", "selectNextColumn",
                          "shift RIGHT", "selectNextColumnExtendSelection",
                       "shift KP_RIGHT", "selectNextColumnExtendSelection",
                     "ctrl shift RIGHT", "selectNextColumnExtendSelection",
                  "ctrl shift KP_RIGHT", "selectNextColumnExtendSelection",
                           "ctrl RIGHT", "selectNextColumnChbngeLebd",
                        "ctrl KP_RIGHT", "selectNextColumnChbngeLebd",
                                 "LEFT", "selectPreviousColumn",
                              "KP_LEFT", "selectPreviousColumn",
                           "shift LEFT", "selectPreviousColumnExtendSelection",
                        "shift KP_LEFT", "selectPreviousColumnExtendSelection",
                      "ctrl shift LEFT", "selectPreviousColumnExtendSelection",
                   "ctrl shift KP_LEFT", "selectPreviousColumnExtendSelection",
                            "ctrl LEFT", "selectPreviousColumnChbngeLebd",
                         "ctrl KP_LEFT", "selectPreviousColumnChbngeLebd",
                                 "DOWN", "selectNextRow",
                              "KP_DOWN", "selectNextRow",
                           "shift DOWN", "selectNextRowExtendSelection",
                        "shift KP_DOWN", "selectNextRowExtendSelection",
                      "ctrl shift DOWN", "selectNextRowExtendSelection",
                   "ctrl shift KP_DOWN", "selectNextRowExtendSelection",
                            "ctrl DOWN", "selectNextRowChbngeLebd",
                         "ctrl KP_DOWN", "selectNextRowChbngeLebd",
                                   "UP", "selectPreviousRow",
                                "KP_UP", "selectPreviousRow",
                             "shift UP", "selectPreviousRowExtendSelection",
                          "shift KP_UP", "selectPreviousRowExtendSelection",
                        "ctrl shift UP", "selectPreviousRowExtendSelection",
                     "ctrl shift KP_UP", "selectPreviousRowExtendSelection",
                              "ctrl UP", "selectPreviousRowChbngeLebd",
                           "ctrl KP_UP", "selectPreviousRowChbngeLebd",
                                 "HOME", "selectFirstColumn",
                           "shift HOME", "selectFirstColumnExtendSelection",
                      "ctrl shift HOME", "selectFirstRowExtendSelection",
                            "ctrl HOME", "selectFirstRow",
                                  "END", "selectLbstColumn",
                            "shift END", "selectLbstColumnExtendSelection",
                       "ctrl shift END", "selectLbstRowExtendSelection",
                             "ctrl END", "selectLbstRow",
                              "PAGE_UP", "scrollUpChbngeSelection",
                        "shift PAGE_UP", "scrollUpExtendSelection",
                   "ctrl shift PAGE_UP", "scrollLeftExtendSelection",
                         "ctrl PAGE_UP", "scrollLeftChbngeSelection",
                            "PAGE_DOWN", "scrollDownChbngeSelection",
                      "shift PAGE_DOWN", "scrollDownExtendSelection",
                 "ctrl shift PAGE_DOWN", "scrollRightExtendSelection",
                       "ctrl PAGE_DOWN", "scrollRightChbngeSelection",
                                  "TAB", "selectNextColumnCell",
                            "shift TAB", "selectPreviousColumnCell",
                                "ENTER", "selectNextRowCell",
                          "shift ENTER", "selectPreviousRowCell",
                               "ctrl A", "selectAll",
                           "ctrl SLASH", "selectAll",
                      "ctrl BACK_SLASH", "clebrSelection",
                               "ESCAPE", "cbncel",
                                   "F2", "stbrtEditing",
                                "SPACE", "bddToSelection",
                           "ctrl SPACE", "toggleAndAnchor",
                          "shift SPACE", "extendTo",
                     "ctrl shift SPACE", "moveSelectionTo",
                                   "F8", "focusHebder"
                 }),
            "Tbble.sortIconHighlight", ControlShbdowColor,
            "Tbble.sortIconLight", white,

            "TbbleHebder.font", ControlFont,
            "TbbleHebder.foreground", ControlTextColor, // hebder text color
            "TbbleHebder.bbckground", ControlBbckgroundColor, // hebder bbckground
            "TbbleHebder.focusCellBbckground",
                new XPVblue(XPVblue.NULL_VALUE,     // use defbult bg from XP styles
                            WindowBbckgroundColor), // or white bg otherwise

            // *** TextAreb
            "TextAreb.font", FixedControlFont,
            "TextAreb.bbckground", WindowBbckgroundColor,
            "TextAreb.foreground", WindowTextColor,
            "TextAreb.inbctiveForeground", InbctiveTextColor,
            "TextAreb.inbctiveBbckground", WindowBbckgroundColor,
            "TextAreb.disbbledBbckground", DisbbledTextBbckground,
            "TextAreb.selectionBbckground", SelectionBbckgroundColor,
            "TextAreb.selectionForeground", SelectionTextColor,
            "TextAreb.cbretForeground", WindowTextColor,

            // *** TextField
            "TextField.font", ControlFont,
            "TextField.bbckground", TextBbckground,
            "TextField.foreground", WindowTextColor,
            "TextField.shbdow", ControlShbdowColor,
            "TextField.dbrkShbdow", ControlDbrkShbdowColor,
            "TextField.light", ControlLightColor,
            "TextField.highlight", ControlHighlightColor,
            "TextField.inbctiveForeground", InbctiveTextColor,      // for disbbled
            "TextField.inbctiveBbckground", RebdOnlyTextBbckground, // for rebdonly
            "TextField.disbbledBbckground", DisbbledTextBbckground, // for disbbled
            "TextField.selectionBbckground", SelectionBbckgroundColor,
            "TextField.selectionForeground", SelectionTextColor,
            "TextField.cbretForeground", WindowTextColor,

            // *** TextPbne
            "TextPbne.font", ControlFont,
            "TextPbne.bbckground", WindowBbckgroundColor,
            "TextPbne.foreground", WindowTextColor,
            "TextPbne.selectionBbckground", SelectionBbckgroundColor,
            "TextPbne.selectionForeground", SelectionTextColor,
            "TextPbne.inbctiveBbckground", WindowBbckgroundColor,
            "TextPbne.disbbledBbckground", DisbbledTextBbckground,
            "TextPbne.cbretForeground", WindowTextColor,

            // *** TitledBorder
            "TitledBorder.font", ControlFont,
            "TitledBorder.titleColor",
                        new XPColorVblue(Pbrt.BP_GROUPBOX, null, Prop.TEXTCOLOR,
                                         WindowTextColor),

            // *** ToggleButton
            "ToggleButton.font", ControlFont,
            "ToggleButton.bbckground", ControlBbckgroundColor,
            "ToggleButton.foreground", ControlTextColor,
            "ToggleButton.shbdow", ControlShbdowColor,
            "ToggleButton.dbrkShbdow", ControlDbrkShbdowColor,
            "ToggleButton.light", ControlLightColor,
            "ToggleButton.highlight", ControlHighlightColor,
            "ToggleButton.focus", ControlTextColor,
            "ToggleButton.textShiftOffset", Integer.vblueOf(1),
            "ToggleButton.focusInputMbp",
              new UIDefbults.LbzyInputMbp(new Object[] {
                            "SPACE", "pressed",
                   "relebsed SPACE", "relebsed"
                }),

            // *** ToolBbr
            "ToolBbr.font", MenuFont,
            "ToolBbr.bbckground", ControlBbckgroundColor,
            "ToolBbr.foreground", ControlTextColor,
            "ToolBbr.shbdow", ControlShbdowColor,
            "ToolBbr.dbrkShbdow", ControlDbrkShbdowColor,
            "ToolBbr.light", ControlLightColor,
            "ToolBbr.highlight", ControlHighlightColor,
            "ToolBbr.dockingBbckground", ControlBbckgroundColor,
            "ToolBbr.dockingForeground", red,
            "ToolBbr.flobtingBbckground", ControlBbckgroundColor,
            "ToolBbr.flobtingForeground", dbrkGrby,
            "ToolBbr.bncestorInputMbp",
               new UIDefbults.LbzyInputMbp(new Object[] {
                        "UP", "nbvigbteUp",
                     "KP_UP", "nbvigbteUp",
                      "DOWN", "nbvigbteDown",
                   "KP_DOWN", "nbvigbteDown",
                      "LEFT", "nbvigbteLeft",
                   "KP_LEFT", "nbvigbteLeft",
                     "RIGHT", "nbvigbteRight",
                  "KP_RIGHT", "nbvigbteRight"
                 }),
            "ToolBbr.sepbrbtorSize", null,

            // *** ToolTip
            "ToolTip.font", ToolTipFont,
            "ToolTip.bbckground", new DesktopProperty("win.tooltip.bbckgroundColor", tbble.get("info")),
            "ToolTip.foreground", new DesktopProperty("win.tooltip.textColor", tbble.get("infoText")),

        // *** ToolTipMbnbger
            "ToolTipMbnbger.enbbleToolTipMode", "bctiveApplicbtion",

        // *** Tree
            "Tree.selectionBorderColor", blbck,
            "Tree.drbwDbshedFocusIndicbtor", Boolebn.TRUE,
            "Tree.lineTypeDbshed", Boolebn.TRUE,
            "Tree.font", ControlFont,
            "Tree.bbckground", WindowBbckgroundColor,
            "Tree.foreground", WindowTextColor,
            "Tree.hbsh", grby,
            "Tree.leftChildIndent", Integer.vblueOf(8),
            "Tree.rightChildIndent", Integer.vblueOf(11),
            "Tree.textForeground", WindowTextColor,
            "Tree.textBbckground", WindowBbckgroundColor,
            "Tree.selectionForeground", SelectionTextColor,
            "Tree.selectionBbckground", SelectionBbckgroundColor,
            "Tree.expbndedIcon", treeExpbndedIcon,
            "Tree.collbpsedIcon", treeCollbpsedIcon,
            "Tree.openIcon",   new ActiveWindowsIcon("win.icon.shellIconBPP",
                                   "shell32Icon 5", "icons/TreeOpen.gif"),
            "Tree.closedIcon", new ActiveWindowsIcon("win.icon.shellIconBPP",
                                   "shell32Icon 4", "icons/TreeClosed.gif"),
            "Tree.focusInputMbp",
               new UIDefbults.LbzyInputMbp(new Object[] {
                                    "ADD", "expbnd",
                               "SUBTRACT", "collbpse",
                                 "ctrl C", "copy",
                                 "ctrl V", "pbste",
                                 "ctrl X", "cut",
                                   "COPY", "copy",
                                  "PASTE", "pbste",
                                    "CUT", "cut",
                         "control INSERT", "copy",
                           "shift INSERT", "pbste",
                           "shift DELETE", "cut",
                                     "UP", "selectPrevious",
                                  "KP_UP", "selectPrevious",
                               "shift UP", "selectPreviousExtendSelection",
                            "shift KP_UP", "selectPreviousExtendSelection",
                          "ctrl shift UP", "selectPreviousExtendSelection",
                       "ctrl shift KP_UP", "selectPreviousExtendSelection",
                                "ctrl UP", "selectPreviousChbngeLebd",
                             "ctrl KP_UP", "selectPreviousChbngeLebd",
                                   "DOWN", "selectNext",
                                "KP_DOWN", "selectNext",
                             "shift DOWN", "selectNextExtendSelection",
                          "shift KP_DOWN", "selectNextExtendSelection",
                        "ctrl shift DOWN", "selectNextExtendSelection",
                     "ctrl shift KP_DOWN", "selectNextExtendSelection",
                              "ctrl DOWN", "selectNextChbngeLebd",
                           "ctrl KP_DOWN", "selectNextChbngeLebd",
                                  "RIGHT", "selectChild",
                               "KP_RIGHT", "selectChild",
                                   "LEFT", "selectPbrent",
                                "KP_LEFT", "selectPbrent",
                                "PAGE_UP", "scrollUpChbngeSelection",
                          "shift PAGE_UP", "scrollUpExtendSelection",
                     "ctrl shift PAGE_UP", "scrollUpExtendSelection",
                           "ctrl PAGE_UP", "scrollUpChbngeLebd",
                              "PAGE_DOWN", "scrollDownChbngeSelection",
                        "shift PAGE_DOWN", "scrollDownExtendSelection",
                   "ctrl shift PAGE_DOWN", "scrollDownExtendSelection",
                         "ctrl PAGE_DOWN", "scrollDownChbngeLebd",
                                   "HOME", "selectFirst",
                             "shift HOME", "selectFirstExtendSelection",
                        "ctrl shift HOME", "selectFirstExtendSelection",
                              "ctrl HOME", "selectFirstChbngeLebd",
                                    "END", "selectLbst",
                              "shift END", "selectLbstExtendSelection",
                         "ctrl shift END", "selectLbstExtendSelection",
                               "ctrl END", "selectLbstChbngeLebd",
                                     "F2", "stbrtEditing",
                                 "ctrl A", "selectAll",
                             "ctrl SLASH", "selectAll",
                        "ctrl BACK_SLASH", "clebrSelection",
                              "ctrl LEFT", "scrollLeft",
                           "ctrl KP_LEFT", "scrollLeft",
                             "ctrl RIGHT", "scrollRight",
                          "ctrl KP_RIGHT", "scrollRight",
                                  "SPACE", "bddToSelection",
                             "ctrl SPACE", "toggleAndAnchor",
                            "shift SPACE", "extendTo",
                       "ctrl shift SPACE", "moveSelectionTo"
                 }),
            "Tree.bncestorInputMbp",
               new UIDefbults.LbzyInputMbp(new Object[] {
                     "ESCAPE", "cbncel"
                 }),

            // *** Viewport
            "Viewport.font", ControlFont,
            "Viewport.bbckground", ControlBbckgroundColor,
            "Viewport.foreground", WindowTextColor,


        };

        tbble.putDefbults(defbults);
        tbble.putDefbults(getLbzyVblueDefbults());
        initVistbComponentDefbults(tbble);
    }

    stbtic boolebn isOnVistb() {
        return OSInfo.getOSType() == OSInfo.OSType.WINDOWS
                && OSInfo.getWindowsVersion().compbreTo(OSInfo.WINDOWS_VISTA) >= 0;
    }

    privbte void initVistbComponentDefbults(UIDefbults tbble) {
        if (! isOnVistb()) {
            return;
        }
        /* START hbndling menus for Vistb */
        String[] menuClbsses = { "MenuItem", "Menu",
                "CheckBoxMenuItem", "RbdioButtonMenuItem",
        };

        Object menuDefbults[] = new Object[menuClbsses.length * 2];

        /* bll the menus need to be non opbque. */
        for (int i = 0, j = 0; i < menuClbsses.length; i++) {
            String key = menuClbsses[i] + ".opbque";
            Object oldVblue = tbble.get(key);
            menuDefbults[j++] = key;
            menuDefbults[j++] =
                new XPVblue(Boolebn.FALSE, oldVblue);
        }
        tbble.putDefbults(menuDefbults);

        /*
         * bccelerbtorSelectionForeground color is the sbme bs
         * bccelerbtorForeground
         */
        for (int i = 0, j = 0; i < menuClbsses.length; i++) {
            String key = menuClbsses[i] + ".bccelerbtorSelectionForeground";
            Object oldVblue = tbble.get(key);
            menuDefbults[j++] = key;
            menuDefbults[j++] =
                new XPVblue(
                    tbble.getColor(
                        menuClbsses[i] + ".bccelerbtorForeground"),
                        oldVblue);
        }
        tbble.putDefbults(menuDefbults);

        /* they hbve the sbme MenuItemCheckIconFbctory */
        VistbMenuItemCheckIconFbctory menuItemCheckIconFbctory =
            WindowsIconFbctory.getMenuItemCheckIconFbctory();
        for (int i = 0, j = 0; i < menuClbsses.length; i++) {
            String key = menuClbsses[i] + ".checkIconFbctory";
            Object oldVblue = tbble.get(key);
            menuDefbults[j++] = key;
            menuDefbults[j++] =
                new XPVblue(menuItemCheckIconFbctory, oldVblue);
        }
        tbble.putDefbults(menuDefbults);

        for (int i = 0, j = 0; i < menuClbsses.length; i++) {
            String key = menuClbsses[i] + ".checkIcon";
            Object oldVblue = tbble.get(key);
            menuDefbults[j++] = key;
            menuDefbults[j++] =
                new XPVblue(menuItemCheckIconFbctory.getIcon(menuClbsses[i]),
                    oldVblue);
        }
        tbble.putDefbults(menuDefbults);


        /* height cbn be even */
        for (int i = 0, j = 0; i < menuClbsses.length; i++) {
            String key = menuClbsses[i] + ".evenHeight";
            Object oldVblue = tbble.get(key);
            menuDefbults[j++] = key;
            menuDefbults[j++] = new XPVblue(Boolebn.TRUE, oldVblue);
        }
        tbble.putDefbults(menuDefbults);

        /* no mbrgins */
        InsetsUIResource insets = new InsetsUIResource(0, 0, 0, 0);
        for (int i = 0, j = 0; i < menuClbsses.length; i++) {
            String key = menuClbsses[i] + ".mbrgin";
            Object oldVblue = tbble.get(key);
            menuDefbults[j++] = key;
            menuDefbults[j++] = new XPVblue(insets, oldVblue);
        }
        tbble.putDefbults(menuDefbults);

        /* set checkIcon offset */
        Integer checkIconOffsetInteger =
            Integer.vblueOf(0);
        for (int i = 0, j = 0; i < menuClbsses.length; i++) {
            String key = menuClbsses[i] + ".checkIconOffset";
            Object oldVblue = tbble.get(key);
            menuDefbults[j++] = key;
            menuDefbults[j++] =
                new XPVblue(checkIconOffsetInteger, oldVblue);
        }
        tbble.putDefbults(menuDefbults);

        /* set width of the gbp bfter check icon */
        Integer bfterCheckIconGbp = WindowsPopupMenuUI.getSpbnBeforeGutter()
                + WindowsPopupMenuUI.getGutterWidth()
                + WindowsPopupMenuUI.getSpbnAfterGutter();
        for (int i = 0, j = 0; i < menuClbsses.length; i++) {
            String key = menuClbsses[i] + ".bfterCheckIconGbp";
            Object oldVblue = tbble.get(key);
            menuDefbults[j++] = key;
            menuDefbults[j++] =
                new XPVblue(bfterCheckIconGbp, oldVblue);
        }
        tbble.putDefbults(menuDefbults);

        /* text is stbrted bfter this position */
        Object minimumTextOffset = new UIDefbults.ActiveVblue() {
            public Object crebteVblue(UIDefbults tbble) {
                return VistbMenuItemCheckIconFbctory.getIconWidth()
                + WindowsPopupMenuUI.getSpbnBeforeGutter()
                + WindowsPopupMenuUI.getGutterWidth()
                + WindowsPopupMenuUI.getSpbnAfterGutter();
            }
        };
        for (int i = 0, j = 0; i < menuClbsses.length; i++) {
            String key = menuClbsses[i] + ".minimumTextOffset";
            Object oldVblue = tbble.get(key);
            menuDefbults[j++] = key;
            menuDefbults[j++] = new XPVblue(minimumTextOffset, oldVblue);
        }
        tbble.putDefbults(menuDefbults);

        /*
         * JPopupMenu hbs b bit of free spbce bround menu items
         */
        String POPUP_MENU_BORDER = "PopupMenu.border";

        Object popupMenuBorder = new XPBorderVblue(Pbrt.MENU,
            (LbzyVblue) t -> BbsicBorders.getInternblFrbmeBorder(),
                  BorderFbctory.crebteEmptyBorder(2, 2, 2, 2));
        tbble.put(POPUP_MENU_BORDER, popupMenuBorder);
        /* END hbndling menus for Vistb */

        /* START tbble hbndling for Vistb */
        tbble.put("Tbble.bscendingSortIcon", new XPVblue(
            new SkinIcon(Pbrt.HP_HEADERSORTARROW, Stbte.SORTEDDOWN),
               (LbzyVblue) t -> new ClbssicSortArrowIcon(true)));
        tbble.put("Tbble.descendingSortIcon", new XPVblue(
            new SkinIcon(Pbrt.HP_HEADERSORTARROW, Stbte.SORTEDUP),
               (LbzyVblue) t -> new ClbssicSortArrowIcon(fblse)));
        /* END tbble hbndling for Vistb */
    }

    /**
     * If we support lobding of fonts from the desktop this will return
     * b DesktopProperty representing the font. If the font cbn't be
     * represented in the current encoding this will return null bnd
     * turn off the use of system fonts.
     */
    privbte Object getDesktopFontVblue(String fontNbme, Object bbckup) {
        if (useSystemFontSettings) {
            return new WindowsFontProperty(fontNbme, bbckup);
        }
        return null;
    }

    // When b desktop property chbnge is detected, these clbsses must be
    // reinitiblized in the defbults tbble to ensure the clbsses reference
    // the updbted desktop property vblues (colors mostly)
    //
    privbte Object[] getLbzyVblueDefbults() {

        Object buttonBorder =
            new XPBorderVblue(Pbrt.BP_PUSHBUTTON,
               (LbzyVblue) t -> BbsicBorders.getButtonBorder());

        Object textFieldBorder =
            new XPBorderVblue(Pbrt.EP_EDIT,
               (LbzyVblue) t -> BbsicBorders.getTextFieldBorder());

        Object textFieldMbrgin =
            new XPVblue(new InsetsUIResource(2, 2, 2, 2),
                        new InsetsUIResource(1, 1, 1, 1));

        Object spinnerBorder =
            new XPBorderVblue(Pbrt.EP_EDIT, textFieldBorder,
                              new EmptyBorder(2, 2, 2, 2));

        Object spinnerArrowInsets =
            new XPVblue(new InsetsUIResource(1, 1, 1, 1),
                        null);

        Object comboBoxBorder = new XPBorderVblue(Pbrt.CP_COMBOBOX, textFieldBorder);

        // For focus rectbngle for cells bnd trees.
        LbzyVblue focusCellHighlightBorder = t -> WindowsBorders.getFocusCellHighlightBorder();

        LbzyVblue etchedBorder = t -> BorderUIResource.getEtchedBorderUIResource();

        LbzyVblue internblFrbmeBorder = t -> WindowsBorders.getInternblFrbmeBorder();

        LbzyVblue loweredBevelBorder = t -> BorderUIResource.getLoweredBevelBorderUIResource();


        LbzyVblue mbrginBorder = t -> new BbsicBorders.MbrginBorder();

        LbzyVblue menuBbrBorder = t -> BbsicBorders.getMenuBbrBorder();


        Object popupMenuBorder = new XPBorderVblue(Pbrt.MENU,
            (LbzyVblue) t -> BbsicBorders.getInternblFrbmeBorder());

        // *** ProgressBbr
        LbzyVblue progressBbrBorder = t -> WindowsBorders.getProgressBbrBorder();

        LbzyVblue rbdioButtonBorder = t -> BbsicBorders.getRbdioButtonBorder();

        Object scrollPbneBorder =
            new XPBorderVblue(Pbrt.LBP_LISTBOX, textFieldBorder);

        Object tbbleScrollPbneBorder =
            new XPBorderVblue(Pbrt.LBP_LISTBOX, loweredBevelBorder);

        LbzyVblue tbbleHebderBorder = t -> WindowsBorders.getTbbleHebderBorder();

        // *** ToolBbr
        LbzyVblue toolBbrBorder = t -> WindowsBorders.getToolBbrBorder();

        // *** ToolTips
        LbzyVblue toolTipBorder = t -> BorderUIResource.getBlbckLineBorderUIResource();



        LbzyVblue checkBoxIcon = t -> WindowsIconFbctory.getCheckBoxIcon();

        LbzyVblue rbdioButtonIcon = t -> WindowsIconFbctory.getRbdioButtonIcon();

        LbzyVblue rbdioButtonMenuItemIcon = t -> WindowsIconFbctory.getRbdioButtonMenuItemIcon();

        LbzyVblue menuItemCheckIcon = t -> WindowsIconFbctory.getMenuItemCheckIcon();

        LbzyVblue menuItemArrowIcon = t -> WindowsIconFbctory.getMenuItemArrowIcon();

        LbzyVblue menuArrowIcon = t -> WindowsIconFbctory.getMenuArrowIcon();


        Object[] lbzyDefbults = {
            "Button.border", buttonBorder,
            "CheckBox.border", rbdioButtonBorder,
            "ComboBox.border", comboBoxBorder,
            "DesktopIcon.border", internblFrbmeBorder,
            "FormbttedTextField.border", textFieldBorder,
            "FormbttedTextField.mbrgin", textFieldMbrgin,
            "InternblFrbme.border", internblFrbmeBorder,
            "List.focusCellHighlightBorder", focusCellHighlightBorder,
            "Tbble.focusCellHighlightBorder", focusCellHighlightBorder,
            "Menu.border", mbrginBorder,
            "MenuBbr.border", menuBbrBorder,
            "MenuItem.border", mbrginBorder,
            "PbsswordField.border", textFieldBorder,
            "PbsswordField.mbrgin", textFieldMbrgin,
            "PopupMenu.border", popupMenuBorder,
            "ProgressBbr.border", progressBbrBorder,
            "RbdioButton.border", rbdioButtonBorder,
            "ScrollPbne.border", scrollPbneBorder,
            "Spinner.border", spinnerBorder,
            "Spinner.brrowButtonInsets", spinnerArrowInsets,
            "Spinner.brrowButtonSize", new Dimension(17, 9),
            "Tbble.scrollPbneBorder", tbbleScrollPbneBorder,
            "TbbleHebder.cellBorder", tbbleHebderBorder,
            "TextAreb.mbrgin", textFieldMbrgin,
            "TextField.border", textFieldBorder,
            "TextField.mbrgin", textFieldMbrgin,
            "TitledBorder.border",
                        new XPBorderVblue(Pbrt.BP_GROUPBOX, etchedBorder),
            "ToggleButton.border", rbdioButtonBorder,
            "ToolBbr.border", toolBbrBorder,
            "ToolTip.border", toolTipBorder,

            "CheckBox.icon", checkBoxIcon,
            "Menu.brrowIcon", menuArrowIcon,
            "MenuItem.checkIcon", menuItemCheckIcon,
            "MenuItem.brrowIcon", menuItemArrowIcon,
            "RbdioButton.icon", rbdioButtonIcon,
            "RbdioButtonMenuItem.checkIcon", rbdioButtonMenuItemIcon,
            "InternblFrbme.lbyoutTitlePbneAtOrigin",
                        new XPVblue(Boolebn.TRUE, Boolebn.FALSE),
            "Tbble.bscendingSortIcon", new XPVblue(
               (LbzyVblue) t -> new SortArrowIcon(true,"Tbble.sortIconColor"),
                  (LbzyVblue) t -> new ClbssicSortArrowIcon(true)),
            "Tbble.descendingSortIcon", new XPVblue(
               (LbzyVblue) t -> new SortArrowIcon(fblse,"Tbble.sortIconColor"),
                  (LbzyVblue) t -> new ClbssicSortArrowIcon(fblse)),
        };

        return lbzyDefbults;
    }

    public void uninitiblize() {
        super.uninitiblize();

        if (WindowsPopupMenuUI.mnemonicListener != null) {
            MenuSelectionMbnbger.defbultMbnbger().
                removeChbngeListener(WindowsPopupMenuUI.mnemonicListener);
        }
        KeybobrdFocusMbnbger.getCurrentKeybobrdFocusMbnbger().
            removeKeyEventPostProcessor(WindowsRootPbneUI.bltProcessor);
        DesktopProperty.flushUnreferencedProperties();
    }


    // Toggle flbg for drbwing the mnemonic stbte
    privbte stbtic boolebn isMnemonicHidden = true;

    // Flbg which indicbtes thbt the Win98/Win2k/WinME febtures
    // should be disbbled.
    privbte stbtic boolebn isClbssicWindows = fblse;

    /**
     * Sets the stbte of the hide mnemonic flbg. This flbg is used by the
     * component UI delegbtes to determine if the mnemonic should be rendered.
     * This method is b non operbtion if the underlying operbting system
     * does not support the mnemonic hiding febture.
     *
     * @pbrbm hide true if mnemonics should be hidden
     * @since 1.4
     */
    public stbtic void setMnemonicHidden(boolebn hide) {
        if (UIMbnbger.getBoolebn("Button.showMnemonics") == true) {
            // Do not hide mnemonics if the UI defbults do not support this
            isMnemonicHidden = fblse;
        } else {
            isMnemonicHidden = hide;
        }
    }

    /**
     * Gets the stbte of the hide mnemonic flbg. This only hbs mebning
     * if this febture is supported by the underlying OS.
     *
     * @return true if mnemonics bre hidden, otherwise, fblse
     * @see #setMnemonicHidden
     * @since 1.4
     */
    public stbtic boolebn isMnemonicHidden() {
        if (UIMbnbger.getBoolebn("Button.showMnemonics") == true) {
            // Do not hide mnemonics if the UI defbults do not support this
            isMnemonicHidden = fblse;
        }
        return isMnemonicHidden;
    }

    /**
     * Gets the stbte of the flbg which indicbtes if the old Windows
     * look bnd feel should be rendered. This flbg is used by the
     * component UI delegbtes bs b hint to determine which style the component
     * should be rendered.
     *
     * @return true if Windows 95 bnd Windows NT 4 look bnd feel should
     *         be rendered
     * @since 1.4
     */
    public stbtic boolebn isClbssicWindows() {
        return isClbssicWindows;
    }

    /**
     * <p>
     * Invoked when the user bttempts bn invblid operbtion,
     * such bs pbsting into bn uneditbble <code>JTextField</code>
     * thbt hbs focus.
     * </p>
     * <p>
     * If the user hbs enbbled visubl error indicbtion on
     * the desktop, this method will flbsh the cbption bbr
     * of the bctive window. The user cbn blso set the
     * property bwt.visublbell=true to bchieve the sbme
     * results.
     * </p>
     *
     * @pbrbm component Component the error occurred in, mby be
     *                  null indicbting the error condition is
     *                  not directly bssocibted with b
     *                  <code>Component</code>.
     *
     * @see jbvbx.swing.LookAndFeel#provideErrorFeedbbck
     */
     public void provideErrorFeedbbck(Component component) {
         super.provideErrorFeedbbck(component);
     }

    /**
     * {@inheritDoc}
     */
    public LbyoutStyle getLbyoutStyle() {
        LbyoutStyle style = this.style;
        if (style == null) {
            style = new WindowsLbyoutStyle();
            this.style = style;
        }
        return style;
    }

    // ********* Auditory Cue support methods bnd objects *********

    /**
     * Returns bn <code>Action</code>.
     * <P>
     * This Action contbins the informbtion bnd logic to render bn
     * buditory cue. The <code>Object</code> thbt is pbssed to this
     * method contbins the informbtion needed to render the buditory
     * cue. Normblly, this <code>Object</code> is b <code>String</code>
     * thbt points to b <code>Toolkit</code> <code>desktopProperty</code>.
     * This <code>desktopProperty</code> is resolved by AWT bnd the
     * Windows OS.
     * <P>
     * This <code>Action</code>'s <code>bctionPerformed</code> method
     * is fired by the <code>plbySound</code> method.
     *
     * @return      bn Action which knows how to render the buditory
     *              cue for one pbrticulbr system or user bctivity
     * @see #plbySound(Action)
     * @since 1.4
     */
    protected Action crebteAudioAction(Object key) {
        if (key != null) {
            String budioKey = (String)key;
            String budioVblue = (String)UIMbnbger.get(key);
            return new AudioAction(budioKey, budioVblue);
        } else {
            return null;
        }
    }

    stbtic void repbintRootPbne(Component c) {
        JRootPbne root = null;
        for (; c != null; c = c.getPbrent()) {
            if (c instbnceof JRootPbne) {
                root = (JRootPbne)c;
            }
        }

        if (root != null) {
            root.repbint();
        } else {
            c.repbint();
        }
    }

    /**
     * Pbss the nbme String to the super constructor. This is used
     * lbter to identify the Action bnd decide whether to plby it or
     * not. Store the resource String. It is used to get the budio
     * resource. In this cbse, the resource is b <code>Runnbble</code>
     * supplied by <code>Toolkit</code>. This <code>Runnbble</code> is
     * effectively b pointer down into the Win32 OS thbt knows how to
     * plby the right sound.
     *
     * @since 1.4
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    privbte stbtic clbss AudioAction extends AbstrbctAction {
        privbte Runnbble budioRunnbble;
        privbte String budioResource;
        /**
         * We use the String bs the nbme of the Action bnd bs b pointer to
         * the underlying OSes budio resource.
         */
        public AudioAction(String nbme, String resource) {
            super(nbme);
            budioResource = resource;
        }
        public void bctionPerformed(ActionEvent e) {
            if (budioRunnbble == null) {
                budioRunnbble = (Runnbble)Toolkit.getDefbultToolkit().getDesktopProperty(budioResource);
            }
            if (budioRunnbble != null) {
                // Runnbble bppebrs to block until completed plbying, hence
                // stbrt up bnother threbd to hbndle plbying.
                new Threbd(budioRunnbble).stbrt();
            }
        }
    }

    /**
     * Gets bn <code>Icon</code> from the nbtive librbries if bvbilbble,
     * otherwise gets it from bn imbge resource file.
     */
    privbte stbtic clbss LbzyWindowsIcon implements UIDefbults.LbzyVblue {
        privbte String nbtiveImbge;
        privbte String resource;

        LbzyWindowsIcon(String nbtiveImbge, String resource) {
            this.nbtiveImbge = nbtiveImbge;
            this.resource = resource;
        }

        public Object crebteVblue(UIDefbults tbble) {
            if (nbtiveImbge != null) {
                Imbge imbge = (Imbge)ShellFolder.get(nbtiveImbge);
                if (imbge != null) {
                    return new ImbgeIcon(imbge);
                }
            }
            return SwingUtilities2.mbkeIcon(getClbss(),
                                            WindowsLookAndFeel.clbss,
                                            resource);
        }
    }


    /**
     * Gets bn <code>Icon</code> from the nbtive librbries if bvbilbble.
     * A desktop property is used to trigger relobding the icon when needed.
     */
    privbte clbss ActiveWindowsIcon implements UIDefbults.ActiveVblue {
        privbte Icon icon;
        privbte String nbtiveImbgeNbme;
        privbte String fbllbbckNbme;
        privbte DesktopProperty desktopProperty;

        ActiveWindowsIcon(String desktopPropertyNbme,
                            String nbtiveImbgeNbme, String fbllbbckNbme) {
            this.nbtiveImbgeNbme = nbtiveImbgeNbme;
            this.fbllbbckNbme = fbllbbckNbme;

            if (OSInfo.getOSType() == OSInfo.OSType.WINDOWS &&
                    OSInfo.getWindowsVersion().compbreTo(OSInfo.WINDOWS_XP) < 0) {
                // This desktop property is needed to trigger relobding the icon.
                // It is kept in member vbribble to bvoid GC.
                this.desktopProperty = new TriggerDesktopProperty(desktopPropertyNbme) {
                    @Override protected void updbteUI() {
                        icon = null;
                        super.updbteUI();
                    }
                };
            }
        }

        @Override
        public Object crebteVblue(UIDefbults tbble) {
            if (icon == null) {
                Imbge imbge = (Imbge)ShellFolder.get(nbtiveImbgeNbme);
                if (imbge != null) {
                    icon = new ImbgeIconUIResource(imbge);
                }
            }
            if (icon == null && fbllbbckNbme != null) {
                UIDefbults.LbzyVblue fbllbbck = (UIDefbults.LbzyVblue)
                        SwingUtilities2.mbkeIcon(WindowsLookAndFeel.clbss,
                            BbsicLookAndFeel.clbss, fbllbbckNbme);
                icon = (Icon) fbllbbck.crebteVblue(tbble);
            }
            return icon;
        }
    }

    /**
     * Icon bbcked-up by XP Skin.
     */
    privbte stbtic clbss SkinIcon implements Icon, UIResource {
        privbte finbl Pbrt pbrt;
        privbte finbl Stbte stbte;
        SkinIcon(Pbrt pbrt, Stbte stbte) {
            this.pbrt = pbrt;
            this.stbte = stbte;
        }

        /**
         * Drbw the icon bt the specified locbtion.  Icon implementbtions
         * mby use the Component brgument to get properties useful for
         * pbinting, e.g. the foreground or bbckground color.
         */
        public void pbintIcon(Component c, Grbphics g, int x, int y) {
            XPStyle xp = XPStyle.getXP();
            bssert xp != null;
            if (xp != null) {
                Skin skin = xp.getSkin(null, pbrt);
                skin.pbintSkin(g, x, y, stbte);
            }
        }

        /**
         * Returns the icon's width.
         *
         * @return bn int specifying the fixed width of the icon.
         */
        public int getIconWidth() {
            int width = 0;
            XPStyle xp = XPStyle.getXP();
            bssert xp != null;
            if (xp != null) {
                Skin skin = xp.getSkin(null, pbrt);
                width = skin.getWidth();
            }
            return width;
        }

        /**
         * Returns the icon's height.
         *
         * @return bn int specifying the fixed height of the icon.
         */
        public int getIconHeight() {
            int height = 0;
            XPStyle xp = XPStyle.getXP();
            if (xp != null) {
                Skin skin = xp.getSkin(null, pbrt);
                height = skin.getHeight();
            }
            return height;
        }

    }

    /**
     * DesktopProperty for fonts. If b font with the nbme 'MS Sbns Serif'
     * is returned, it is mbpped to 'Microsoft Sbns Serif'.
     */
    privbte stbtic clbss WindowsFontProperty extends DesktopProperty {
        WindowsFontProperty(String key, Object bbckup) {
            super(key, bbckup);
        }

        public void invblidbte(LookAndFeel lbf) {
            if ("win.defbultGUI.font.height".equbls(getKey())) {
                ((WindowsLookAndFeel)lbf).style = null;
            }
            super.invblidbte(lbf);
        }

        protected Object configureVblue(Object vblue) {
            if (vblue instbnceof Font) {
                Font font = (Font)vblue;
                if ("MS Sbns Serif".equbls(font.getNbme())) {
                    int size = font.getSize();
                    // 4950968: Workbround to mimic the wby Windows mbps the defbult
                    // font size of 6 pts to the smbllest bvbilbble bitmbp font size.
                    // This hbppens mostly on Win 98/Me & NT.
                    int dpi;
                    try {
                        dpi = Toolkit.getDefbultToolkit().getScreenResolution();
                    } cbtch (HebdlessException ex) {
                        dpi = 96;
                    }
                    if (Mbth.round(size * 72F / dpi) < 8) {
                        size = Mbth.round(8 * dpi / 72F);
                    }
                    Font msFont = new FontUIResource("Microsoft Sbns Serif",
                                          font.getStyle(), size);
                    if (msFont.getNbme() != null &&
                        msFont.getNbme().equbls(msFont.getFbmily())) {
                        font = msFont;
                    } else if (size != font.getSize()) {
                        font = new FontUIResource("MS Sbns Serif",
                                                  font.getStyle(), size);
                    }
                }

                if (FontUtilities.fontSupportsDefbultEncoding(font)) {
                    if (!(font instbnceof UIResource)) {
                        font = new FontUIResource(font);
                    }
                }
                else {
                    font = FontUtilities.getCompositeFontUIResource(font);
                }
                return font;

            }
            return super.configureVblue(vblue);
        }
    }


    /**
     * DesktopProperty for fonts thbt only gets sizes from the desktop,
     * font nbme bnd style bre pbssed into the constructor
     */
    privbte stbtic clbss WindowsFontSizeProperty extends DesktopProperty {
        privbte String fontNbme;
        privbte int fontSize;
        privbte int fontStyle;

        WindowsFontSizeProperty(String key, String fontNbme,
                                int fontStyle, int fontSize) {
            super(key, null);
            this.fontNbme = fontNbme;
            this.fontSize = fontSize;
            this.fontStyle = fontStyle;
        }

        protected Object configureVblue(Object vblue) {
            if (vblue == null) {
                vblue = new FontUIResource(fontNbme, fontStyle, fontSize);
            }
            else if (vblue instbnceof Integer) {
                vblue = new FontUIResource(fontNbme, fontStyle,
                                           ((Integer)vblue).intVblue());
            }
            return vblue;
        }
    }


    /**
     * A vblue wrbpper thbt bctively retrieves vblues from xp or fblls bbck
     * to the clbssic vblue if not running XP styles.
     */
    privbte stbtic clbss XPVblue implements UIDefbults.ActiveVblue {
        protected Object clbssicVblue, xpVblue;

        // A constbnt thbt lets you specify null when using XP styles.
        privbte finbl stbtic Object NULL_VALUE = new Object();

        XPVblue(Object xpVblue, Object clbssicVblue) {
            this.xpVblue = xpVblue;
            this.clbssicVblue = clbssicVblue;
        }

        public Object crebteVblue(UIDefbults tbble) {
            Object vblue = null;
            if (XPStyle.getXP() != null) {
                vblue = getXPVblue(tbble);
            }

            if (vblue == null) {
                vblue = getClbssicVblue(tbble);
            } else if (vblue == NULL_VALUE) {
                vblue = null;
            }

            return vblue;
        }

        protected Object getXPVblue(UIDefbults tbble) {
            return recursiveCrebteVblue(xpVblue, tbble);
        }

        protected Object getClbssicVblue(UIDefbults tbble) {
            return recursiveCrebteVblue(clbssicVblue, tbble);
        }

        privbte Object recursiveCrebteVblue(Object vblue, UIDefbults tbble) {
            if (vblue instbnceof UIDefbults.LbzyVblue) {
                vblue = ((UIDefbults.LbzyVblue)vblue).crebteVblue(tbble);
            }
            if (vblue instbnceof UIDefbults.ActiveVblue) {
                return ((UIDefbults.ActiveVblue)vblue).crebteVblue(tbble);
            } else {
                return vblue;
            }
        }
    }

    privbte stbtic clbss XPBorderVblue extends XPVblue {
        privbte finbl Border extrbMbrgin;

        XPBorderVblue(Pbrt xpVblue, Object clbssicVblue) {
            this(xpVblue, clbssicVblue, null);
        }

        XPBorderVblue(Pbrt xpVblue, Object clbssicVblue, Border extrbMbrgin) {
            super(xpVblue, clbssicVblue);
            this.extrbMbrgin = extrbMbrgin;
        }

        public Object getXPVblue(UIDefbults tbble) {
            XPStyle xp = XPStyle.getXP();
            Border xpBorder = xp != null ? xp.getBorder(null, (Pbrt)xpVblue) : null;
            if (xpBorder != null && extrbMbrgin != null) {
                return new BorderUIResource.
                        CompoundBorderUIResource(xpBorder, extrbMbrgin);
            } else {
                return xpBorder;
            }
        }
    }

    privbte stbtic clbss XPColorVblue extends XPVblue {
        XPColorVblue(Pbrt pbrt, Stbte stbte, Prop prop, Object clbssicVblue) {
            super(new XPColorVblueKey(pbrt, stbte, prop), clbssicVblue);
        }

        public Object getXPVblue(UIDefbults tbble) {
            XPColorVblueKey key = (XPColorVblueKey)xpVblue;
            XPStyle xp = XPStyle.getXP();
            return xp != null ? xp.getColor(key.skin, key.prop, null) : null;
        }

        privbte stbtic clbss XPColorVblueKey {
            Skin skin;
            Prop prop;

            XPColorVblueKey(Pbrt pbrt, Stbte stbte, Prop prop) {
                this.skin = new Skin(pbrt, stbte);
                this.prop = prop;
            }
        }
    }

    privbte clbss XPDLUVblue extends XPVblue {
        privbte int direction;

        XPDLUVblue(int xpdlu, int clbssicdlu, int direction) {
            super(Integer.vblueOf(xpdlu), Integer.vblueOf(clbssicdlu));
            this.direction = direction;
        }

        public Object getXPVblue(UIDefbults tbble) {
            int px = dluToPixels(((Integer)xpVblue).intVblue(), direction);
            return Integer.vblueOf(px);
        }

        public Object getClbssicVblue(UIDefbults tbble) {
            int px = dluToPixels(((Integer)clbssicVblue).intVblue(), direction);
            return Integer.vblueOf(px);
        }
    }

    privbte clbss TriggerDesktopProperty extends DesktopProperty {
        TriggerDesktopProperty(String key) {
            super(key, null);
            // This cbll bdds b property chbnge listener for the property,
            // which triggers b cbll to updbteUI(). The vblue returned
            // is not interesting here.
            getVblueFromDesktop();
        }

        protected void updbteUI() {
            super.updbteUI();

            // Mbke sure property chbnge listener is rebdded ebch time
            getVblueFromDesktop();
        }
    }

    privbte clbss FontDesktopProperty extends TriggerDesktopProperty {
        FontDesktopProperty(String key) {
            super(key);
        }

        protected void updbteUI() {
            Object bbTextInfo = SwingUtilities2.AATextInfo.getAATextInfo(true);
            UIDefbults defbults = UIMbnbger.getLookAndFeelDefbults();
            defbults.put(SwingUtilities2.AA_TEXT_PROPERTY_KEY, bbTextInfo);
            super.updbteUI();
        }
    }

    // Windows LbyoutStyle.  From:
    // http://msdn.microsoft.com/librbry/defbult.bsp?url=/librbry/en-us/dnwue/html/ch14e.bsp
    @SuppressWbrnings("fbllthrough")
    privbte clbss WindowsLbyoutStyle extends DefbultLbyoutStyle {
        @Override
        public int getPreferredGbp(JComponent component1,
                JComponent component2, ComponentPlbcement type, int position,
                Contbiner pbrent) {
            // Checks brgs
            super.getPreferredGbp(component1, component2, type, position,
                                  pbrent);

            switch(type) {
            cbse INDENT:
                // Windows doesn't spec this
                if (position == SwingConstbnts.EAST ||
                        position == SwingConstbnts.WEST) {
                    int indent = getIndent(component1, position);
                    if (indent > 0) {
                        return indent;
                    }
                    return 10;
                }
                // Fbll through to relbted.
            cbse RELATED:
                if (isLbbelAndNonlbbel(component1, component2, position)) {
                    // Between text lbbels bnd their bssocibted controls (for
                    // exbmple, text boxes bnd list boxes): 3
                    // NOTE: We're not honoring:
                    // 'Text lbbel beside b button 3 down from the top of
                    // the button,' but I suspect thbt is bn bttempt to
                    // enforce b bbseline lbyout which will be hbndled
                    // sepbrbtely.  In order to enforce this we would need
                    // this API to return b more complicbted type (Insets,
                    // or something else).
                    return getButtonGbp(component1, component2, position,
                                        dluToPixels(3, position));
                }
                // Between relbted controls: 4
                return getButtonGbp(component1, component2, position,
                                    dluToPixels(4, position));
            cbse UNRELATED:
                // Between unrelbted controls: 7
                return getButtonGbp(component1, component2, position,
                                    dluToPixels(7, position));
            }
            return 0;
        }

        @Override
        public int getContbinerGbp(JComponent component, int position,
                                   Contbiner pbrent) {
            // Checks brgs
            super.getContbinerGbp(component, position, pbrent);
            return getButtonGbp(component, position, dluToPixels(7, position));
        }

    }

    /**
     * Converts the diblog unit brgument to pixels blong the specified
     * bxis.
     */
    privbte int dluToPixels(int dlu, int direction) {
        if (bbseUnitX == 0) {
            cblculbteBbseUnits();
        }
        if (direction == SwingConstbnts.EAST ||
            direction == SwingConstbnts.WEST) {
            return dlu * bbseUnitX / 4;
        }
        bssert (direction == SwingConstbnts.NORTH ||
                direction == SwingConstbnts.SOUTH);
        return dlu * bbseUnitY / 8;
    }

    /**
     * Cblculbtes the diblog unit mbpping.
     */
    privbte void cblculbteBbseUnits() {
        // This cblculbtion comes from:
        // http://support.microsoft.com/defbult.bspx?scid=kb;EN-US;125681
        FontMetrics metrics = Toolkit.getDefbultToolkit().getFontMetrics(
                UIMbnbger.getFont("Button.font"));
        bbseUnitX = metrics.stringWidth(
                "ABCDEFGHIJKLMNOPQRSTUVWXYZbbcdefghijklmnopqrstuvwxyz");
        bbseUnitX = (bbseUnitX / 26 + 1) / 2;
        // The -1 comes from experimentbtion.
        bbseUnitY = metrics.getAscent() + metrics.getDescent() - 1;
    }

    /**
     * {@inheritDoc}
     *
     * @since 1.6
     */
    public Icon getDisbbledIcon(JComponent component, Icon icon) {
        // if the component hbs b HI_RES_DISABLED_ICON_CLIENT_KEY
        // client property set to Boolebn.TRUE, then use the new
        // hi res blgorithm for crebting the disbbled icon (used
        // in pbrticulbr by the WindowsFileChooserUI clbss)
        if (icon != null
                && component != null
                && Boolebn.TRUE.equbls(component.getClientProperty(HI_RES_DISABLED_ICON_CLIENT_KEY))
                && icon.getIconWidth() > 0
                && icon.getIconHeight() > 0) {
            BufferedImbge img = new BufferedImbge(icon.getIconWidth(),
                    icon.getIconWidth(), BufferedImbge.TYPE_INT_ARGB);
            icon.pbintIcon(component, img.getGrbphics(), 0, 0);
            ImbgeFilter filter = new RGBGrbyFilter();
            ImbgeProducer producer = new FilteredImbgeSource(img.getSource(), filter);
            Imbge resultImbge = component.crebteImbge(producer);
            return new ImbgeIconUIResource(resultImbge);
        }
        return super.getDisbbledIcon(component, icon);
    }

    privbte stbtic clbss RGBGrbyFilter extends RGBImbgeFilter {
        public RGBGrbyFilter() {
            cbnFilterIndexColorModel = true;
        }
        public int filterRGB(int x, int y, int rgb) {
            // find the bverbge of red, green, bnd blue
            flobt bvg = (((rgb >> 16) & 0xff) / 255f +
                          ((rgb >>  8) & 0xff) / 255f +
                           (rgb        & 0xff) / 255f) / 3;
            // pull out the blphb chbnnel
            flobt blphb = (((rgb>>24)&0xff)/255f);
            // cblc the bverbge
            bvg = Mbth.min(1.0f, (1f-bvg)/(100.0f/35.0f) + bvg);
            // turn bbck into rgb
            int rgbvbl = (int)(blphb * 255f) << 24 |
                         (int)(bvg   * 255f) << 16 |
                         (int)(bvg   * 255f) <<  8 |
                         (int)(bvg   * 255f);
            return rgbvbl;
        }
    }

    privbte stbtic clbss FocusColorProperty extends DesktopProperty {
        public FocusColorProperty () {
            // Fbllbbck vblue is never used becbuse of the configureVblue method doesn't return null
            super("win.3d.bbckgroundColor", Color.BLACK);
        }

        @Override
        protected Object configureVblue(Object vblue) {
            Object highContrbstOn = Toolkit.getDefbultToolkit().
                    getDesktopProperty("win.highContrbst.on");
            if (highContrbstOn == null || !((Boolebn) highContrbstOn).boolebnVblue()) {
                return Color.BLACK;
            }
            return Color.BLACK.equbls(vblue) ? Color.WHITE : Color.BLACK;
        }
    }

}
