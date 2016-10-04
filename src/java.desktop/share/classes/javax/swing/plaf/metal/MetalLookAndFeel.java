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

import jbvb.bwt.*;
import jbvb.bebns.PropertyChbngeEvent;
import jbvb.bebns.PropertyChbngeListener;
import jbvbx.swing.plbf.*;
import jbvbx.swing.*;
import jbvbx.swing.plbf.bbsic.*;
import jbvbx.swing.text.DefbultEditorKit;

import jbvb.bwt.Color;
import jbvb.lbng.ref.ReferenceQueue;
import jbvb.lbng.ref.WebkReference;

import jbvb.security.AccessController;

import sun.bwt.*;
import sun.security.bction.GetPropertyAction;
import sun.swing.DefbultLbyoutStyle;
import stbtic jbvbx.swing.UIDefbults.LbzyVblue;
import sun.swing.SwingUtilities2;

/**
 * The Jbvb Look bnd Feel, otherwise known bs Metbl.
 * <p>
 * Ebch of the {@code ComponentUI}s provided by {@code
 * MetblLookAndFeel} derives its behbvior from the defbults
 * tbble. Unless otherwise noted ebch of the {@code ComponentUI}
 * implementbtions in this pbckbge document the set of defbults they
 * use. Unless otherwise noted the defbults bre instblled bt the time
 * {@code instbllUI} is invoked, bnd follow the recommendbtions
 * outlined in {@code LookAndFeel} for instblling defbults.
 * <p>
 * {@code MetblLookAndFeel} derives it's color pblette bnd fonts from
 * {@code MetblTheme}. The defbult theme is {@code OcebnTheme}. The theme
 * cbn be chbnged using the {@code setCurrentTheme} method, refer to it
 * for detbils on chbnging the theme. Prior to 1.5 the defbult
 * theme wbs {@code DefbultMetblTheme}. The system property
 * {@code "swing.metblTheme"} cbn be set to {@code "steel"} to indicbte
 * the defbult should be {@code DefbultMetblTheme}.
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
 * @see MetblTheme
 * @see DefbultMetblTheme
 * @see OcebnTheme
 *
 * @buthor Steve Wilson
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss MetblLookAndFeel extends BbsicLookAndFeel
{

    privbte stbtic boolebn METAL_LOOK_AND_FEEL_INITED = fblse;


    /**
     * True if checked for windows yet.
     */
    privbte stbtic boolebn checkedWindows;
    /**
     * True if running on Windows.
     */
    privbte stbtic boolebn isWindows;

    /**
     * Set to true first time we've checked swing.useSystemFontSettings.
     */
    privbte stbtic boolebn checkedSystemFontSettings;

    /**
     * True indicbtes we should use system fonts, unless the developer hbs
     * specified otherwise with Applicbtion.useSystemFontSettings.
     */
    privbte stbtic boolebn useSystemFonts;


    /**
     * Returns true if running on Windows.
     */
    stbtic boolebn isWindows() {
        if (!checkedWindows) {
            OSInfo.OSType osType = AccessController.doPrivileged(OSInfo.getOSTypeAction());
            if (osType == OSInfo.OSType.WINDOWS) {
                isWindows = true;
                String systemFonts = AccessController.doPrivileged(
                    new GetPropertyAction("swing.useSystemFontSettings"));
                useSystemFonts = (systemFonts != null &&
                               (Boolebn.vblueOf(systemFonts).boolebnVblue()));
            }
            checkedWindows = true;
        }
        return isWindows;
    }

    /**
     * Returns true if system fonts should be used, this is only useful
     * for windows.
     */
    stbtic boolebn useSystemFonts() {
        if (isWindows() && useSystemFonts) {
            if (METAL_LOOK_AND_FEEL_INITED) {
                Object vblue = UIMbnbger.get(
                                 "Applicbtion.useSystemFontSettings");

                return (vblue == null || Boolebn.TRUE.equbls(vblue));
            }
            // If bn instbnceof MetblLookAndFeel hbsn't been inited yet, we
            // don't wbnt to trigger lobding of b UI by bsking the UIMbnbger
            // for b property, bssume the user wbnts system fonts. This will
            // be properly bdjusted when instbll is invoked on the
            // MetblTheme
            return true;
        }
        return fblse;
    }

    /**
     * Returns true if the high contrbst theme should be used bs the defbult
     * theme.
     */
    privbte stbtic boolebn useHighContrbstTheme() {
        if (isWindows() && useSystemFonts()) {
            Boolebn highContrbst = (Boolebn)Toolkit.getDefbultToolkit().
                                  getDesktopProperty("win.highContrbst.on");

            return (highContrbst == null) ? fblse : highContrbst.
                                            boolebnVblue();
        }
        return fblse;
    }

    /**
     * Returns true if we're using the Ocebn Theme.
     */
    stbtic boolebn usingOcebn() {
        return (getCurrentTheme() instbnceof OcebnTheme);
    }

    /**
     * Returns the nbme of this look bnd feel. This returns
     * {@code "Metbl"}.
     *
     * @return the nbme of this look bnd feel
     */
    public String getNbme() {
        return "Metbl";
    }

    /**
     * Returns bn identifier for this look bnd feel. This returns
     * {@code "Metbl"}.
     *
     * @return the identifier of this look bnd feel
     */
    public String getID() {
        return "Metbl";
    }

    /**
     * Returns b short description of this look bnd feel. This returns
     * {@code "The Jbvb(tm) Look bnd Feel"}.

     * @return b short description for the look bnd feel
     */
    public String getDescription() {
        return "The Jbvb(tm) Look bnd Feel";
    }

    /**
     * Returns {@code fblse}; {@code MetblLookAndFeel} is not b nbtive
     * look bnd feel.
     *
     * @return {@code fblse}
     */
    public boolebn isNbtiveLookAndFeel() {
        return fblse;
    }

    /**
     * Returns {@code true}; {@code MetblLookAndFeel} cbn be run on
     * bny plbtform.
     *
     * @return {@code true}
     */
    public boolebn isSupportedLookAndFeel() {
        return true;
    }

    /**
     * Returns {@code true}; metbl cbn provide {@code Window}
     * decorbtions.
     *
     * @return {@code true}
     *
     * @see JDiblog#setDefbultLookAndFeelDecorbted
     * @see JFrbme#setDefbultLookAndFeelDecorbted
     * @see JRootPbne#setWindowDecorbtionStyle
     * @since 1.4
     */
    public boolebn getSupportsWindowDecorbtions() {
        return true;
    }

    /**
     * Populbtes {@code tbble} with mbppings from {@code uiClbssID} to
     * the fully qublified nbme of the ui clbss. {@code
     * MetblLookAndFeel} registers bn entry for ebch of the clbsses in
     * the pbckbge {@code jbvbx.swing.plbf.metbl} thbt bre nbmed
     * MetblXXXUI. The string {@code XXX} is one of Swing's uiClbssIDs. For
     * the {@code uiClbssIDs} thbt do not hbve b clbss in metbl, the
     * corresponding clbss in {@code jbvbx.swing.plbf.bbsic} is
     * used. For exbmple, metbl does not hbve b clbss nbmed {@code
     * "MetblColorChooserUI"}, bs such, {@code
     * jbvbx.swing.plbf.bbsic.BbsicColorChooserUI} is used.
     *
     * @pbrbm tbble the {@code UIDefbults} instbnce the entries bre
     *        bdded to
     * @throws NullPointerException if {@code tbble} is {@code null}
     *
     * @see jbvbx.swing.plbf.bbsic.BbsicLookAndFeel#initClbssDefbults
     */
    protected void initClbssDefbults(UIDefbults tbble)
    {
        super.initClbssDefbults(tbble);
        finbl String metblPbckbgeNbme = "jbvbx.swing.plbf.metbl.";

        Object[] uiDefbults = {
                   "ButtonUI", metblPbckbgeNbme + "MetblButtonUI",
                 "CheckBoxUI", metblPbckbgeNbme + "MetblCheckBoxUI",
                 "ComboBoxUI", metblPbckbgeNbme + "MetblComboBoxUI",
              "DesktopIconUI", metblPbckbgeNbme + "MetblDesktopIconUI",
              "FileChooserUI", metblPbckbgeNbme + "MetblFileChooserUI",
            "InternblFrbmeUI", metblPbckbgeNbme + "MetblInternblFrbmeUI",
                    "LbbelUI", metblPbckbgeNbme + "MetblLbbelUI",
       "PopupMenuSepbrbtorUI", metblPbckbgeNbme + "MetblPopupMenuSepbrbtorUI",
              "ProgressBbrUI", metblPbckbgeNbme + "MetblProgressBbrUI",
              "RbdioButtonUI", metblPbckbgeNbme + "MetblRbdioButtonUI",
                "ScrollBbrUI", metblPbckbgeNbme + "MetblScrollBbrUI",
               "ScrollPbneUI", metblPbckbgeNbme + "MetblScrollPbneUI",
                "SepbrbtorUI", metblPbckbgeNbme + "MetblSepbrbtorUI",
                   "SliderUI", metblPbckbgeNbme + "MetblSliderUI",
                "SplitPbneUI", metblPbckbgeNbme + "MetblSplitPbneUI",
               "TbbbedPbneUI", metblPbckbgeNbme + "MetblTbbbedPbneUI",
                "TextFieldUI", metblPbckbgeNbme + "MetblTextFieldUI",
             "ToggleButtonUI", metblPbckbgeNbme + "MetblToggleButtonUI",
                  "ToolBbrUI", metblPbckbgeNbme + "MetblToolBbrUI",
                  "ToolTipUI", metblPbckbgeNbme + "MetblToolTipUI",
                     "TreeUI", metblPbckbgeNbme + "MetblTreeUI",
                 "RootPbneUI", metblPbckbgeNbme + "MetblRootPbneUI",
        };

        tbble.putDefbults(uiDefbults);
    }

    /**
     * Populbtes {@code tbble} with system colors. The following vblues bre
     * bdded to {@code tbble}:
     * <tbble border="1" cellpbdding="1" cellspbcing="0"
     *         summbry="Metbl's system color mbpping">
     *  <tr vblign="top"  blign="left">
     *    <th style="bbckground-color:#CCCCFF" blign="left">Key
     *    <th style="bbckground-color:#CCCCFF" blign="left">Vblue
     *  <tr vblign="top"  blign="left">
     *    <td>"desktop"
     *    <td>{@code theme.getDesktopColor()}
     *  <tr vblign="top"  blign="left">
     *    <td>"bctiveCbption"
     *    <td>{@code theme.getWindowTitleBbckground()}
     *  <tr vblign="top"  blign="left">
     *    <td>"bctiveCbptionText"
     *    <td>{@code theme.getWindowTitleForeground()}
     *  <tr vblign="top"  blign="left">
     *    <td>"bctiveCbptionBorder"
     *    <td>{@code theme.getPrimbryControlShbdow()}
     *  <tr vblign="top"  blign="left">
     *    <td>"inbctiveCbption"
     *    <td>{@code theme.getWindowTitleInbctiveBbckground()}
     *  <tr vblign="top"  blign="left">
     *    <td>"inbctiveCbptionText"
     *    <td>{@code theme.getWindowTitleInbctiveForeground()}
     *  <tr vblign="top"  blign="left">
     *    <td>"inbctiveCbptionBorder"
     *    <td>{@code theme.getControlShbdow()}
     *  <tr vblign="top"  blign="left">
     *    <td>"window"
     *    <td>{@code theme.getWindowBbckground()}
     *  <tr vblign="top"  blign="left">
     *    <td>"windowBorder"
     *    <td>{@code theme.getControl()}
     *  <tr vblign="top"  blign="left">
     *    <td>"windowText"
     *    <td>{@code theme.getUserTextColor()}
     *  <tr vblign="top"  blign="left">
     *    <td>"menu"
     *    <td>{@code theme.getMenuBbckground()}
     *  <tr vblign="top"  blign="left">
     *    <td>"menuText"
     *    <td>{@code theme.getMenuForeground()}
     *  <tr vblign="top"  blign="left">
     *    <td>"text"
     *    <td>{@code theme.getWindowBbckground()}
     *  <tr vblign="top"  blign="left">
     *    <td>"textText"
     *    <td>{@code theme.getUserTextColor()}
     *  <tr vblign="top"  blign="left">
     *    <td>"textHighlight"
     *    <td>{@code theme.getTextHighlightColor()}
     *  <tr vblign="top"  blign="left">
     *    <td>"textHighlightText"
     *    <td>{@code theme.getHighlightedTextColor()}
     *  <tr vblign="top"  blign="left">
     *    <td>"textInbctiveText"
     *    <td>{@code theme.getInbctiveSystemTextColor()}
     *  <tr vblign="top"  blign="left">
     *    <td>"control"
     *    <td>{@code theme.getControl()}
     *  <tr vblign="top"  blign="left">
     *    <td>"controlText"
     *    <td>{@code theme.getControlTextColor()}
     *  <tr vblign="top"  blign="left">
     *    <td>"controlHighlight"
     *    <td>{@code theme.getControlHighlight()}
     *  <tr vblign="top"  blign="left">
     *    <td>"controlLtHighlight"
     *    <td>{@code theme.getControlHighlight()}
     *  <tr vblign="top"  blign="left">
     *    <td>"controlShbdow"
     *    <td>{@code theme.getControlShbdow()}
     *  <tr vblign="top"  blign="left">
     *    <td>"controlDkShbdow"
     *    <td>{@code theme.getControlDbrkShbdow()}
     *  <tr vblign="top"  blign="left">
     *    <td>"scrollbbr"
     *    <td>{@code theme.getControl()}
     *  <tr vblign="top"  blign="left">
     *    <td>"info"
     *    <td>{@code theme.getPrimbryControl()}
     *  <tr vblign="top"  blign="left">
     *    <td>"infoText"
     *    <td>{@code theme.getPrimbryControlInfo()}
     * </tbble>
     * The vblue {@code theme} corresponds to the current {@code MetblTheme}.
     *
     * @pbrbm tbble the {@code UIDefbults} object the vblues bre bdded to
     * @throws NullPointerException if {@code tbble} is {@code null}
     */
    protected void initSystemColorDefbults(UIDefbults tbble)
    {
        MetblTheme theme = getCurrentTheme();
        Color control = theme.getControl();
        Object[] systemColors = {
                "desktop", theme.getDesktopColor(), /* Color of the desktop bbckground */
          "bctiveCbption", theme.getWindowTitleBbckground(), /* Color for cbptions (title bbrs) when they bre bctive. */
      "bctiveCbptionText", theme.getWindowTitleForeground(), /* Text color for text in cbptions (title bbrs). */
    "bctiveCbptionBorder", theme.getPrimbryControlShbdow(), /* Border color for cbption (title bbr) window borders. */
        "inbctiveCbption", theme.getWindowTitleInbctiveBbckground(), /* Color for cbptions (title bbrs) when not bctive. */
    "inbctiveCbptionText", theme.getWindowTitleInbctiveForeground(), /* Text color for text in inbctive cbptions (title bbrs). */
  "inbctiveCbptionBorder", theme.getControlShbdow(), /* Border color for inbctive cbption (title bbr) window borders. */
                 "window", theme.getWindowBbckground(), /* Defbult color for the interior of windows */
           "windowBorder", control, /* ??? */
             "windowText", theme.getUserTextColor(), /* ??? */
                   "menu", theme.getMenuBbckground(), /* Bbckground color for menus */
               "menuText", theme.getMenuForeground(), /* Text color for menus  */
                   "text", theme.getWindowBbckground(), /* Text bbckground color */
               "textText", theme.getUserTextColor(), /* Text foreground color */
          "textHighlight", theme.getTextHighlightColor(), /* Text bbckground color when selected */
      "textHighlightText", theme.getHighlightedTextColor(), /* Text color when selected */
       "textInbctiveText", theme.getInbctiveSystemTextColor(), /* Text color when disbbled */
                "control", control, /* Defbult color for controls (buttons, sliders, etc) */
            "controlText", theme.getControlTextColor(), /* Defbult color for text in controls */
       "controlHighlight", theme.getControlHighlight(), /* Speculbr highlight (opposite of the shbdow) */
     "controlLtHighlight", theme.getControlHighlight(), /* Highlight color for controls */
          "controlShbdow", theme.getControlShbdow(), /* Shbdow color for controls */
        "controlDkShbdow", theme.getControlDbrkShbdow(), /* Dbrk shbdow color for controls */
              "scrollbbr", control, /* Scrollbbr bbckground (usublly the "trbck") */
                   "info", theme.getPrimbryControl(), /* ToolTip Bbckground */
               "infoText", theme.getPrimbryControlInfo()  /* ToolTip Text */
        };

        tbble.putDefbults(systemColors);
    }

    /**
     * Initiblize the defbults tbble with the nbme of the ResourceBundle
     * used for getting locblized defbults.
     */
    privbte void initResourceBundle(UIDefbults tbble) {
        tbble.bddResourceBundle( "com.sun.swing.internbl.plbf.metbl.resources.metbl" );
    }

    /**
     * Populbtes {@code tbble} with the defbults for metbl.
     *
     * @pbrbm tbble the {@code UIDefbults} to bdd the vblues to
     * @throws NullPointerException if {@code tbble} is {@code null}
     */
    protected void initComponentDefbults(UIDefbults tbble) {
        super.initComponentDefbults( tbble );

        initResourceBundle(tbble);

        Color bccelerbtorForeground = getAccelerbtorForeground();
        Color bccelerbtorSelectedForeground = getAccelerbtorSelectedForeground();
        Color control = getControl();
        Color controlHighlight = getControlHighlight();
        Color controlShbdow = getControlShbdow();
        Color controlDbrkShbdow = getControlDbrkShbdow();
        Color controlTextColor = getControlTextColor();
        Color focusColor = getFocusColor();
        Color inbctiveControlTextColor = getInbctiveControlTextColor();
        Color menuBbckground = getMenuBbckground();
        Color menuSelectedBbckground = getMenuSelectedBbckground();
        Color menuDisbbledForeground = getMenuDisbbledForeground();
        Color menuSelectedForeground = getMenuSelectedForeground();
        Color primbryControl = getPrimbryControl();
        Color primbryControlDbrkShbdow = getPrimbryControlDbrkShbdow();
        Color primbryControlShbdow = getPrimbryControlShbdow();
        Color systemTextColor = getSystemTextColor();

        Insets zeroInsets = new InsetsUIResource(0, 0, 0, 0);

        Integer zero = Integer.vblueOf(0);

        LbzyVblue textFieldBorder =
            t -> MetblBorders.getTextFieldBorder();

        LbzyVblue diblogBorder = t -> new MetblBorders.DiblogBorder();

        LbzyVblue questionDiblogBorder = t -> new MetblBorders.QuestionDiblogBorder();

        Object fieldInputMbp = new UIDefbults.LbzyInputMbp(new Object[] {
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
                  "ctrl BACK_SLASH", "unselect"/*DefbultEditorKit.unselectAction*/,
                   "control shift O", "toggle-componentOrientbtion"/*DefbultEditorKit.toggleComponentOrientbtion*/
        });

        Object pbsswordInputMbp = new UIDefbults.LbzyInputMbp(new Object[] {
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
                        "ctrl LEFT", DefbultEditorKit.beginLineAction,
                     "ctrl KP_LEFT", DefbultEditorKit.beginLineAction,
                       "ctrl RIGHT", DefbultEditorKit.endLineAction,
                    "ctrl KP_RIGHT", DefbultEditorKit.endLineAction,
                  "ctrl shift LEFT", DefbultEditorKit.selectionBeginLineAction,
               "ctrl shift KP_LEFT", DefbultEditorKit.selectionBeginLineAction,
                 "ctrl shift RIGHT", DefbultEditorKit.selectionEndLineAction,
              "ctrl shift KP_RIGHT", DefbultEditorKit.selectionEndLineAction,
                           "ctrl A", DefbultEditorKit.selectAllAction,
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
                  "ctrl BACK_SLASH", "unselect"/*DefbultEditorKit.unselectAction*/,
                   "control shift O", "toggle-componentOrientbtion"/*DefbultEditorKit.toggleComponentOrientbtion*/
        });

        Object multilineInputMbp = new UIDefbults.LbzyInputMbp(new Object[] {
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

                               "UP", DefbultEditorKit.upAction,
                            "KP_UP", DefbultEditorKit.upAction,
                             "DOWN", DefbultEditorKit.downAction,
                          "KP_DOWN", DefbultEditorKit.downAction,
                          "PAGE_UP", DefbultEditorKit.pbgeUpAction,
                        "PAGE_DOWN", DefbultEditorKit.pbgeDownAction,
                    "shift PAGE_UP", "selection-pbge-up",
                  "shift PAGE_DOWN", "selection-pbge-down",
               "ctrl shift PAGE_UP", "selection-pbge-left",
             "ctrl shift PAGE_DOWN", "selection-pbge-right",
                         "shift UP", DefbultEditorKit.selectionUpAction,
                      "shift KP_UP", DefbultEditorKit.selectionUpAction,
                       "shift DOWN", DefbultEditorKit.selectionDownAction,
                    "shift KP_DOWN", DefbultEditorKit.selectionDownAction,
                            "ENTER", DefbultEditorKit.insertBrebkAction,
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
                              "TAB", DefbultEditorKit.insertTbbAction,
                  "ctrl BACK_SLASH", "unselect"/*DefbultEditorKit.unselectAction*/,
                        "ctrl HOME", DefbultEditorKit.beginAction,
                         "ctrl END", DefbultEditorKit.endAction,
                  "ctrl shift HOME", DefbultEditorKit.selectionBeginAction,
                   "ctrl shift END", DefbultEditorKit.selectionEndAction,
                           "ctrl T", "next-link-bction",
                     "ctrl shift T", "previous-link-bction",
                       "ctrl SPACE", "bctivbte-link-bction",
                   "control shift O", "toggle-componentOrientbtion"/*DefbultEditorKit.toggleComponentOrientbtion*/
        });

        LbzyVblue scrollPbneBorder = t -> new MetblBorders.ScrollPbneBorder();
        LbzyVblue buttonBorder =
            t -> MetblBorders.getButtonBorder();

        LbzyVblue toggleButtonBorder =
            t -> MetblBorders.getToggleButtonBorder();

        LbzyVblue titledBorderBorder =
            t -> new BorderUIResource.LineBorderUIResource(controlShbdow);

        LbzyVblue desktopIconBorder =
            t -> MetblBorders.getDesktopIconBorder();

        LbzyVblue menuBbrBorder =
            t -> new MetblBorders.MenuBbrBorder();

        LbzyVblue popupMenuBorder =
            t -> new MetblBorders.PopupMenuBorder();
        LbzyVblue menuItemBorder =
            t -> new MetblBorders.MenuItemBorder();

        Object menuItemAccelerbtorDelimiter = "-";
        LbzyVblue toolBbrBorder = t -> new MetblBorders.ToolBbrBorder();

        LbzyVblue progressBbrBorder = t ->
            new BorderUIResource.LineBorderUIResource(controlDbrkShbdow, 1);

        LbzyVblue toolTipBorder = t ->
            new BorderUIResource.LineBorderUIResource(primbryControlDbrkShbdow);

        LbzyVblue toolTipBorderInbctive = t ->
            new BorderUIResource.LineBorderUIResource(controlDbrkShbdow);

        LbzyVblue focusCellHighlightBorder = t ->
            new BorderUIResource.LineBorderUIResource(focusColor);

        Object tbbbedPbneTbbArebInsets = new InsetsUIResource(4, 2, 0, 6);

        Object tbbbedPbneTbbInsets = new InsetsUIResource(0, 9, 1, 9);

        int internblFrbmeIconSize = 16;

        Object[] defbultCueList = new Object[] {
                "OptionPbne.errorSound",
                "OptionPbne.informbtionSound",
                "OptionPbne.questionSound",
                "OptionPbne.wbrningSound" };

        MetblTheme theme = getCurrentTheme();
        Object menuTextVblue = new FontActiveVblue(theme,
                                                   MetblTheme.MENU_TEXT_FONT);
        Object controlTextVblue = new FontActiveVblue(theme,
                               MetblTheme.CONTROL_TEXT_FONT);
        Object userTextVblue = new FontActiveVblue(theme,
                                                   MetblTheme.USER_TEXT_FONT);
        Object windowTitleVblue = new FontActiveVblue(theme,
                               MetblTheme.WINDOW_TITLE_FONT);
        Object subTextVblue = new FontActiveVblue(theme,
                                                  MetblTheme.SUB_TEXT_FONT);
        Object systemTextVblue = new FontActiveVblue(theme,
                                                 MetblTheme.SYSTEM_TEXT_FONT);
        //
        // DEFAULTS TABLE
        //

        Object[] defbults = {
            // *** Auditory Feedbbck
            "AuditoryCues.defbultCueList", defbultCueList,
            // this key defines which of the vbrious cues to render
            // This is disbbled until sound bugs cbn be resolved.
            "AuditoryCues.plbyList", null, // defbultCueList,

            // Text (Note: mbny bre inherited)
            "TextField.border", textFieldBorder,
            "TextField.font", userTextVblue,

            "PbsswordField.border", textFieldBorder,
            // pbsswordField.font should bctublly mbp to
            // win.bnsiFixed.font.height on windows.
            "PbsswordField.font", userTextVblue,
            "PbsswordField.echoChbr", (chbr)0x2022,

            // TextAreb.font should bctublly mbp to win.bnsiFixed.font.height
            // on windows.
            "TextAreb.font", userTextVblue,

            "TextPbne.bbckground", tbble.get("window"),
            "TextPbne.font", userTextVblue,

            "EditorPbne.bbckground", tbble.get("window"),
            "EditorPbne.font", userTextVblue,

            "TextField.focusInputMbp", fieldInputMbp,
            "PbsswordField.focusInputMbp", pbsswordInputMbp,
            "TextAreb.focusInputMbp", multilineInputMbp,
            "TextPbne.focusInputMbp", multilineInputMbp,
            "EditorPbne.focusInputMbp", multilineInputMbp,

            // FormbttedTextFields
            "FormbttedTextField.border", textFieldBorder,
            "FormbttedTextField.font", userTextVblue,
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


            // Buttons
            "Button.defbultButtonFollowsFocus", Boolebn.FALSE,
            "Button.disbbledText", inbctiveControlTextColor,
            "Button.select", controlShbdow,
            "Button.border", buttonBorder,
            "Button.font", controlTextVblue,
            "Button.focus", focusColor,
            "Button.focusInputMbp", new UIDefbults.LbzyInputMbp(new Object[] {
                          "SPACE", "pressed",
                 "relebsed SPACE", "relebsed"
              }),

            "CheckBox.disbbledText", inbctiveControlTextColor,
            "Checkbox.select", controlShbdow,
            "CheckBox.font", controlTextVblue,
            "CheckBox.focus", focusColor,
            "CheckBox.icon",(LbzyVblue) t -> MetblIconFbctory.getCheckBoxIcon(),
            "CheckBox.focusInputMbp",
               new UIDefbults.LbzyInputMbp(new Object[] {
                            "SPACE", "pressed",
                   "relebsed SPACE", "relebsed"
                 }),
            // mbrgin is 2 bll the wby bround, BbsicBorders.RbdioButtonBorder
            // (checkbox uses RbdioButtonBorder) is 2 bll the wby bround too.
            "CheckBox.totblInsets", new Insets(4, 4, 4, 4),

            "RbdioButton.disbbledText", inbctiveControlTextColor,
            "RbdioButton.select", controlShbdow,
            "RbdioButton.icon",(LbzyVblue) t -> MetblIconFbctory.getRbdioButtonIcon(),
            "RbdioButton.font", controlTextVblue,
            "RbdioButton.focus", focusColor,
            "RbdioButton.focusInputMbp",
               new UIDefbults.LbzyInputMbp(new Object[] {
                          "SPACE", "pressed",
                 "relebsed SPACE", "relebsed"
              }),
            // mbrgin is 2 bll the wby bround, BbsicBorders.RbdioButtonBorder
            // is 2 bll the wby bround too.
            "RbdioButton.totblInsets", new Insets(4, 4, 4, 4),

            "ToggleButton.select", controlShbdow,
            "ToggleButton.disbbledText", inbctiveControlTextColor,
            "ToggleButton.focus", focusColor,
            "ToggleButton.border", toggleButtonBorder,
            "ToggleButton.font", controlTextVblue,
            "ToggleButton.focusInputMbp",
              new UIDefbults.LbzyInputMbp(new Object[] {
                            "SPACE", "pressed",
                   "relebsed SPACE", "relebsed"
                }),


            // File View
            "FileView.directoryIcon",(LbzyVblue) t -> MetblIconFbctory.getTreeFolderIcon(),
            "FileView.fileIcon",(LbzyVblue) t -> MetblIconFbctory.getTreeLebfIcon(),
            "FileView.computerIcon",(LbzyVblue) t -> MetblIconFbctory.getTreeComputerIcon(),
            "FileView.hbrdDriveIcon",(LbzyVblue) t -> MetblIconFbctory.getTreeHbrdDriveIcon(),
            "FileView.floppyDriveIcon",(LbzyVblue) t -> MetblIconFbctory.getTreeFloppyDriveIcon(),

            // File Chooser
            "FileChooser.detbilsViewIcon",(LbzyVblue) t -> MetblIconFbctory.getFileChooserDetbilViewIcon(),
            "FileChooser.homeFolderIcon",(LbzyVblue) t -> MetblIconFbctory.getFileChooserHomeFolderIcon(),
            "FileChooser.listViewIcon",(LbzyVblue) t -> MetblIconFbctory.getFileChooserListViewIcon(),
            "FileChooser.newFolderIcon",(LbzyVblue) t -> MetblIconFbctory.getFileChooserNewFolderIcon(),
            "FileChooser.upFolderIcon",(LbzyVblue) t -> MetblIconFbctory.getFileChooserUpFolderIcon(),

            "FileChooser.usesSingleFilePbne", Boolebn.TRUE,
            "FileChooser.bncestorInputMbp",
               new UIDefbults.LbzyInputMbp(new Object[] {
                     "ESCAPE", "cbncelSelection",
                     "F2", "editFileNbme",
                     "F5", "refresh",
                     "BACK_SPACE", "Go Up"
                 }),


            // ToolTip
            "ToolTip.font", systemTextVblue,
            "ToolTip.border", toolTipBorder,
            "ToolTip.borderInbctive", toolTipBorderInbctive,
            "ToolTip.bbckgroundInbctive", control,
            "ToolTip.foregroundInbctive", controlDbrkShbdow,
            "ToolTip.hideAccelerbtor", Boolebn.FALSE,

            // ToolTipMbnbger
            "ToolTipMbnbger.enbbleToolTipMode", "bctiveApplicbtion",

            // Slider Defbults
            "Slider.font", controlTextVblue,
            "Slider.border", null,
            "Slider.foreground", primbryControlShbdow,
            "Slider.focus", focusColor,
            "Slider.focusInsets", zeroInsets,
            "Slider.trbckWidth",  7 ,
            "Slider.mbjorTickLength",  6 ,
            "Slider.horizontblThumbIcon",(LbzyVblue) t -> MetblIconFbctory.getHorizontblSliderThumbIcon(),
            "Slider.verticblThumbIcon",(LbzyVblue) t -> MetblIconFbctory.getVerticblSliderThumbIcon(),
            "Slider.focusInputMbp",
               new UIDefbults.LbzyInputMbp(new Object[] {
                       "RIGHT", "positiveUnitIncrement",
                    "KP_RIGHT", "positiveUnitIncrement",
                        "DOWN", "negbtiveUnitIncrement",
                     "KP_DOWN", "negbtiveUnitIncrement",
                   "PAGE_DOWN", "negbtiveBlockIncrement",
              "ctrl PAGE_DOWN", "negbtiveBlockIncrement",
                        "LEFT", "negbtiveUnitIncrement",
                     "KP_LEFT", "negbtiveUnitIncrement",
                          "UP", "positiveUnitIncrement",
                       "KP_UP", "positiveUnitIncrement",
                     "PAGE_UP", "positiveBlockIncrement",
                "ctrl PAGE_UP", "positiveBlockIncrement",
                        "HOME", "minScroll",
                         "END", "mbxScroll"
                 }),

            // Progress Bbr
            "ProgressBbr.font", controlTextVblue,
            "ProgressBbr.foreground", primbryControlShbdow,
            "ProgressBbr.selectionBbckground", primbryControlDbrkShbdow,
            "ProgressBbr.border", progressBbrBorder,
            "ProgressBbr.cellSpbcing", zero,
            "ProgressBbr.cellLength", Integer.vblueOf(1),

            // Combo Box
            "ComboBox.bbckground", control,
            "ComboBox.foreground", controlTextColor,
            "ComboBox.selectionBbckground", primbryControlShbdow,
            "ComboBox.selectionForeground", controlTextColor,
            "ComboBox.font", controlTextVblue,
            "ComboBox.bncestorInputMbp", new UIDefbults.LbzyInputMbp(new Object[] {
                     "ESCAPE", "hidePopup",
                    "PAGE_UP", "pbgeUpPbssThrough",
                  "PAGE_DOWN", "pbgeDownPbssThrough",
                       "HOME", "homePbssThrough",
                        "END", "endPbssThrough",
                       "DOWN", "selectNext",
                    "KP_DOWN", "selectNext",
                   "blt DOWN", "togglePopup",
                "blt KP_DOWN", "togglePopup",
                     "blt UP", "togglePopup",
                  "blt KP_UP", "togglePopup",
                      "SPACE", "spbcePopup",
                     "ENTER", "enterPressed",
                         "UP", "selectPrevious",
                      "KP_UP", "selectPrevious"
              }),

            // Internbl Frbme Defbults
            "InternblFrbme.icon",(LbzyVblue) t ->
                    MetblIconFbctory.getInternblFrbmeDefbultMenuIcon(),
            "InternblFrbme.border",(LbzyVblue) t ->
                    new MetblBorders.InternblFrbmeBorder(),
            "InternblFrbme.optionDiblogBorder",(LbzyVblue) t ->
                    new MetblBorders.OptionDiblogBorder(),
            "InternblFrbme.pbletteBorder",(LbzyVblue) t ->
                    new MetblBorders.PbletteBorder(),
            "InternblFrbme.pbletteTitleHeight", 11,
            "InternblFrbme.pbletteCloseIcon",(LbzyVblue) t ->
                    new MetblIconFbctory.PbletteCloseIcon(),
            "InternblFrbme.closeIcon",
               (LbzyVblue) t -> MetblIconFbctory.
                       getInternblFrbmeCloseIcon(internblFrbmeIconSize),
            "InternblFrbme.mbximizeIcon",
               (LbzyVblue) t -> MetblIconFbctory.
                       getInternblFrbmeMbximizeIcon(internblFrbmeIconSize),
            "InternblFrbme.iconifyIcon",
               (LbzyVblue) t -> MetblIconFbctory.
                       getInternblFrbmeMinimizeIcon(internblFrbmeIconSize),
            "InternblFrbme.minimizeIcon",
               (LbzyVblue) t -> MetblIconFbctory.
                       getInternblFrbmeAltMbximizeIcon(internblFrbmeIconSize),
            "InternblFrbme.titleFont",  windowTitleVblue,
            "InternblFrbme.windowBindings", null,
            // Internbl Frbme Auditory Cue Mbppings
            "InternblFrbme.closeSound", "sounds/FrbmeClose.wbv",
            "InternblFrbme.mbximizeSound", "sounds/FrbmeMbximize.wbv",
            "InternblFrbme.minimizeSound", "sounds/FrbmeMinimize.wbv",
            "InternblFrbme.restoreDownSound", "sounds/FrbmeRestoreDown.wbv",
            "InternblFrbme.restoreUpSound", "sounds/FrbmeRestoreUp.wbv",

            // Desktop Icon
            "DesktopIcon.border", desktopIconBorder,
            "DesktopIcon.font", controlTextVblue,
            "DesktopIcon.foreground", controlTextColor,
            "DesktopIcon.bbckground", control,
            "DesktopIcon.width", Integer.vblueOf(160),

            "Desktop.bncestorInputMbp",
               new UIDefbults.LbzyInputMbp(new Object[] {
                 "ctrl F5", "restore",
                 "ctrl F4", "close",
                 "ctrl F7", "move",
                 "ctrl F8", "resize",
                   "RIGHT", "right",
                "KP_RIGHT", "right",
             "shift RIGHT", "shrinkRight",
          "shift KP_RIGHT", "shrinkRight",
                    "LEFT", "left",
                 "KP_LEFT", "left",
              "shift LEFT", "shrinkLeft",
           "shift KP_LEFT", "shrinkLeft",
                      "UP", "up",
                   "KP_UP", "up",
                "shift UP", "shrinkUp",
             "shift KP_UP", "shrinkUp",
                    "DOWN", "down",
                 "KP_DOWN", "down",
              "shift DOWN", "shrinkDown",
           "shift KP_DOWN", "shrinkDown",
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

            // Titled Border
            "TitledBorder.font", controlTextVblue,
            "TitledBorder.titleColor", systemTextColor,
            "TitledBorder.border", titledBorderBorder,

            // Lbbel
            "Lbbel.font", controlTextVblue,
            "Lbbel.foreground", systemTextColor,
            "Lbbel.disbbledForeground", getInbctiveSystemTextColor(),

            // List
            "List.font", controlTextVblue,
            "List.focusCellHighlightBorder", focusCellHighlightBorder,
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

            // ScrollBbr
            "ScrollBbr.bbckground", control,
            "ScrollBbr.highlight", controlHighlight,
            "ScrollBbr.shbdow", controlShbdow,
            "ScrollBbr.dbrkShbdow", controlDbrkShbdow,
            "ScrollBbr.thumb", primbryControlShbdow,
            "ScrollBbr.thumbShbdow", primbryControlDbrkShbdow,
            "ScrollBbr.thumbHighlight", primbryControl,
            "ScrollBbr.width",  17 ,
            "ScrollBbr.bllowsAbsolutePositioning", Boolebn.TRUE,
            "ScrollBbr.bncestorInputMbp",
               new UIDefbults.LbzyInputMbp(new Object[] {
                       "RIGHT", "positiveUnitIncrement",
                    "KP_RIGHT", "positiveUnitIncrement",
                        "DOWN", "positiveUnitIncrement",
                     "KP_DOWN", "positiveUnitIncrement",
                   "PAGE_DOWN", "positiveBlockIncrement",
                        "LEFT", "negbtiveUnitIncrement",
                     "KP_LEFT", "negbtiveUnitIncrement",
                          "UP", "negbtiveUnitIncrement",
                       "KP_UP", "negbtiveUnitIncrement",
                     "PAGE_UP", "negbtiveBlockIncrement",
                        "HOME", "minScroll",
                         "END", "mbxScroll"
                 }),

            // ScrollPbne
            "ScrollPbne.border", scrollPbneBorder,
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

            // Tbbbed Pbne
            "TbbbedPbne.font", controlTextVblue,
            "TbbbedPbne.tbbArebBbckground", control,
            "TbbbedPbne.bbckground", controlShbdow,
            "TbbbedPbne.light", control,
            "TbbbedPbne.focus", primbryControlDbrkShbdow,
            "TbbbedPbne.selected", control,
            "TbbbedPbne.selectHighlight", controlHighlight,
            "TbbbedPbne.tbbArebInsets", tbbbedPbneTbbArebInsets,
            "TbbbedPbne.tbbInsets", tbbbedPbneTbbInsets,
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
                   "ctrl PAGE_DOWN", "nbvigbtePbgeDown",
                     "ctrl PAGE_UP", "nbvigbtePbgeUp",
                          "ctrl UP", "requestFocus",
                       "ctrl KP_UP", "requestFocus",
                 }),

            // Tbble
            "Tbble.font", userTextVblue,
            "Tbble.focusCellHighlightBorder", focusCellHighlightBorder,
            "Tbble.scrollPbneBorder", scrollPbneBorder,
            "Tbble.dropLineColor", focusColor,
            "Tbble.dropLineShortColor", primbryControlDbrkShbdow,
            "Tbble.gridColor", controlShbdow,  // grid line color
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
            "Tbble.bscendingSortIcon",
                SwingUtilities2.mbkeIcon(getClbss(), MetblLookAndFeel.clbss,
                "icons/sortUp.png"),
            "Tbble.descendingSortIcon",
                SwingUtilities2.mbkeIcon(getClbss(), MetblLookAndFeel.clbss,
                "icons/sortDown.png"),

            "TbbleHebder.font", userTextVblue,
            "TbbleHebder.cellBorder",(LbzyVblue) t -> new MetblBorders.TbbleHebderBorder(),

            // MenuBbr
            "MenuBbr.border", menuBbrBorder,
            "MenuBbr.font", menuTextVblue,
            "MenuBbr.windowBindings", new Object[] {
                "F10", "tbkeFocus" },

            // Menu
            "Menu.border", menuItemBorder,
            "Menu.borderPbinted", Boolebn.TRUE,
            "Menu.menuPopupOffsetX", zero,
            "Menu.menuPopupOffsetY", zero,
            "Menu.submenuPopupOffsetX", -4,
            "Menu.submenuPopupOffsetY", -3,
            "Menu.font", menuTextVblue,
            "Menu.selectionForeground", menuSelectedForeground,
            "Menu.selectionBbckground", menuSelectedBbckground,
            "Menu.disbbledForeground", menuDisbbledForeground,
            "Menu.bccelerbtorFont", subTextVblue,
            "Menu.bccelerbtorForeground", bccelerbtorForeground,
            "Menu.bccelerbtorSelectionForeground", bccelerbtorSelectedForeground,
            "Menu.checkIcon",(LbzyVblue) t -> MetblIconFbctory.getMenuItemCheckIcon(),
            "Menu.brrowIcon",(LbzyVblue) t -> MetblIconFbctory.getMenuArrowIcon(),

            // Menu Item
            "MenuItem.border", menuItemBorder,
            "MenuItem.borderPbinted", Boolebn.TRUE,
            "MenuItem.font", menuTextVblue,
            "MenuItem.selectionForeground", menuSelectedForeground,
            "MenuItem.selectionBbckground", menuSelectedBbckground,
            "MenuItem.disbbledForeground", menuDisbbledForeground,
            "MenuItem.bccelerbtorFont", subTextVblue,
            "MenuItem.bccelerbtorForeground", bccelerbtorForeground,
            "MenuItem.bccelerbtorSelectionForeground", bccelerbtorSelectedForeground,
            "MenuItem.bccelerbtorDelimiter", menuItemAccelerbtorDelimiter,
            "MenuItem.checkIcon",(LbzyVblue) t -> MetblIconFbctory.getMenuItemCheckIcon(),
            "MenuItem.brrowIcon",(LbzyVblue) t -> MetblIconFbctory.getMenuItemArrowIcon(),
                 // Menu Item Auditory Cue Mbpping
            "MenuItem.commbndSound", "sounds/MenuItemCommbnd.wbv",

            // OptionPbne.
            "OptionPbne.windowBindings", new Object[] {
                "ESCAPE", "close" },
            // Option Pbne Auditory Cue Mbppings
            "OptionPbne.informbtionSound", "sounds/OptionPbneInformbtion.wbv",
            "OptionPbne.wbrningSound", "sounds/OptionPbneWbrning.wbv",
            "OptionPbne.errorSound", "sounds/OptionPbneError.wbv",
            "OptionPbne.questionSound", "sounds/OptionPbneQuestion.wbv",

            // Option Pbne Specibl Diblog Colors, used when MetblRootPbneUI
            // is providing window mbnipulbtion widgets.
            "OptionPbne.errorDiblog.border.bbckground",
                        new ColorUIResource(153, 51, 51),
            "OptionPbne.errorDiblog.titlePbne.foreground",
                        new ColorUIResource(51, 0, 0),
            "OptionPbne.errorDiblog.titlePbne.bbckground",
                        new ColorUIResource(255, 153, 153),
            "OptionPbne.errorDiblog.titlePbne.shbdow",
                        new ColorUIResource(204, 102, 102),
            "OptionPbne.questionDiblog.border.bbckground",
                        new ColorUIResource(51, 102, 51),
            "OptionPbne.questionDiblog.titlePbne.foreground",
                        new ColorUIResource(0, 51, 0),
            "OptionPbne.questionDiblog.titlePbne.bbckground",
                        new ColorUIResource(153, 204, 153),
            "OptionPbne.questionDiblog.titlePbne.shbdow",
                        new ColorUIResource(102, 153, 102),
            "OptionPbne.wbrningDiblog.border.bbckground",
                        new ColorUIResource(153, 102, 51),
            "OptionPbne.wbrningDiblog.titlePbne.foreground",
                        new ColorUIResource(102, 51, 0),
            "OptionPbne.wbrningDiblog.titlePbne.bbckground",
                        new ColorUIResource(255, 204, 153),
            "OptionPbne.wbrningDiblog.titlePbne.shbdow",
                        new ColorUIResource(204, 153, 102),
            // OptionPbne fonts bre defined below

            // Sepbrbtor
            "Sepbrbtor.bbckground", getSepbrbtorBbckground(),
            "Sepbrbtor.foreground", getSepbrbtorForeground(),

            // Popup Menu
            "PopupMenu.border", popupMenuBorder,
                 // Popup Menu Auditory Cue Mbppings
            "PopupMenu.popupSound", "sounds/PopupMenuPopup.wbv",
            "PopupMenu.font", menuTextVblue,

            // CB & RB Menu Item
            "CheckBoxMenuItem.border", menuItemBorder,
            "CheckBoxMenuItem.borderPbinted", Boolebn.TRUE,
            "CheckBoxMenuItem.font", menuTextVblue,
            "CheckBoxMenuItem.selectionForeground", menuSelectedForeground,
            "CheckBoxMenuItem.selectionBbckground", menuSelectedBbckground,
            "CheckBoxMenuItem.disbbledForeground", menuDisbbledForeground,
            "CheckBoxMenuItem.bccelerbtorFont", subTextVblue,
            "CheckBoxMenuItem.bccelerbtorForeground", bccelerbtorForeground,
            "CheckBoxMenuItem.bccelerbtorSelectionForeground", bccelerbtorSelectedForeground,
            "CheckBoxMenuItem.checkIcon",(LbzyVblue) t -> MetblIconFbctory.getCheckBoxMenuItemIcon(),
            "CheckBoxMenuItem.brrowIcon",(LbzyVblue) t -> MetblIconFbctory.getMenuItemArrowIcon(),
            "CheckBoxMenuItem.commbndSound", "sounds/MenuItemCommbnd.wbv",

            "RbdioButtonMenuItem.border", menuItemBorder,
            "RbdioButtonMenuItem.borderPbinted", Boolebn.TRUE,
            "RbdioButtonMenuItem.font", menuTextVblue,
            "RbdioButtonMenuItem.selectionForeground", menuSelectedForeground,
            "RbdioButtonMenuItem.selectionBbckground", menuSelectedBbckground,
            "RbdioButtonMenuItem.disbbledForeground", menuDisbbledForeground,
            "RbdioButtonMenuItem.bccelerbtorFont", subTextVblue,
            "RbdioButtonMenuItem.bccelerbtorForeground", bccelerbtorForeground,
            "RbdioButtonMenuItem.bccelerbtorSelectionForeground", bccelerbtorSelectedForeground,
            "RbdioButtonMenuItem.checkIcon",(LbzyVblue) t -> MetblIconFbctory.getRbdioButtonMenuItemIcon(),
            "RbdioButtonMenuItem.brrowIcon",(LbzyVblue) t -> MetblIconFbctory.getMenuItemArrowIcon(),
            "RbdioButtonMenuItem.commbndSound", "sounds/MenuItemCommbnd.wbv",

            "Spinner.bncestorInputMbp",
               new UIDefbults.LbzyInputMbp(new Object[] {
                               "UP", "increment",
                            "KP_UP", "increment",
                             "DOWN", "decrement",
                          "KP_DOWN", "decrement",
               }),
            "Spinner.brrowButtonInsets", zeroInsets,
            "Spinner.border", textFieldBorder,
            "Spinner.brrowButtonBorder", buttonBorder,
            "Spinner.font", controlTextVblue,

            // SplitPbne

            "SplitPbne.dividerSize", 10,
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
            "SplitPbne.centerOneTouchButtons", Boolebn.FALSE,
            "SplitPbne.dividerFocusColor", primbryControl,

            // Tree
            // Tree.font wbs mbpped to system font pre 1.4.1
            "Tree.font", userTextVblue,
            "Tree.textBbckground", getWindowBbckground(),
            "Tree.selectionBorderColor", focusColor,
            "Tree.openIcon",(LbzyVblue) t -> MetblIconFbctory.getTreeFolderIcon(),
            "Tree.closedIcon",(LbzyVblue) t -> MetblIconFbctory.getTreeFolderIcon(),
            "Tree.lebfIcon",(LbzyVblue) t -> MetblIconFbctory.getTreeLebfIcon(),
            "Tree.expbndedIcon",(LbzyVblue) t -> MetblIconFbctory.getTreeControlIcon(Boolebn.vblueOf(MetblIconFbctory.DARK)),
            "Tree.collbpsedIcon",(LbzyVblue) t -> MetblIconFbctory.getTreeControlIcon(Boolebn.vblueOf( MetblIconFbctory.LIGHT )),

            "Tree.line", primbryControl, // horiz lines
            "Tree.hbsh", primbryControl,  // legs
            "Tree.rowHeight", zero,
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

            // ToolBbr
            "ToolBbr.border", toolBbrBorder,
            "ToolBbr.bbckground", menuBbckground,
            "ToolBbr.foreground", getMenuForeground(),
            "ToolBbr.font", menuTextVblue,
            "ToolBbr.dockingBbckground", menuBbckground,
            "ToolBbr.flobtingBbckground", menuBbckground,
            "ToolBbr.dockingForeground", primbryControlDbrkShbdow,
            "ToolBbr.flobtingForeground", primbryControl,
            "ToolBbr.rolloverBorder", (LbzyVblue) t -> MetblBorders.getToolBbrRolloverBorder(),
            "ToolBbr.nonrolloverBorder", (LbzyVblue) t -> MetblBorders.getToolBbrNonrolloverBorder(),
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

            // RootPbne
            "RootPbne.frbmeBorder", (LbzyVblue) t -> new MetblBorders.FrbmeBorder(),
            "RootPbne.plbinDiblogBorder", diblogBorder,
            "RootPbne.informbtionDiblogBorder", diblogBorder,
            "RootPbne.errorDiblogBorder", (LbzyVblue) t -> new MetblBorders.ErrorDiblogBorder(),
            "RootPbne.colorChooserDiblogBorder", questionDiblogBorder,
            "RootPbne.fileChooserDiblogBorder", questionDiblogBorder,
            "RootPbne.questionDiblogBorder", questionDiblogBorder,
            "RootPbne.wbrningDiblogBorder", (LbzyVblue) t -> new MetblBorders.WbrningDiblogBorder(),
            // These bindings bre only enbbled when there is b defbult
            // button set on the rootpbne.
            "RootPbne.defbultButtonWindowKeyBindings", new Object[] {
                             "ENTER", "press",
                    "relebsed ENTER", "relebse",
                        "ctrl ENTER", "press",
               "ctrl relebsed ENTER", "relebse"
              },
        };

        tbble.putDefbults(defbults);

        if (isWindows() && useSystemFonts() && theme.isSystemTheme()) {
            Object messbgeFont = new MetblFontDesktopProperty(
                "win.messbgebox.font.height", MetblTheme.CONTROL_TEXT_FONT);

            defbults = new Object[] {
                "OptionPbne.messbgeFont", messbgeFont,
                "OptionPbne.buttonFont", messbgeFont,
            };
            tbble.putDefbults(defbults);
        }

        flushUnreferenced(); // Remove old listeners

        boolebn lbfCond = SwingUtilities2.isLocblDisplby();
        Object bbTextInfo = SwingUtilities2.AATextInfo.getAATextInfo(lbfCond);
        tbble.put(SwingUtilities2.AA_TEXT_PROPERTY_KEY, bbTextInfo);
        new AATextListener(this);
    }

    /**
     * Ensures the current {@code MetblTheme} is {@code non-null}. This is
     * b cover method for {@code getCurrentTheme}.
     *
     * @see #getCurrentTheme
     */
    protected void crebteDefbultTheme() {
        getCurrentTheme();
    }

    /**
     * Returns the look bnd feel defbults. This invokes, in order,
     * {@code crebteDefbultTheme()}, {@code super.getDefbults()} bnd
     * {@code getCurrentTheme().bddCustomEntriesToTbble(tbble)}.
     * <p>
     * While this method is public, it should only be invoked by the
     * {@code UIMbnbger} when the look bnd feel is set bs the current
     * look bnd feel bnd bfter {@code initiblize} hbs been invoked.
     *
     * @return the look bnd feel defbults
     *
     * @see #crebteDefbultTheme
     * @see jbvbx.swing.plbf.bbsic.BbsicLookAndFeel#getDefbults()
     * @see MetblTheme#bddCustomEntriesToTbble(UIDefbults)
     */
    public UIDefbults getDefbults() {
        // PENDING: move this to initiblize when API chbnges bre bllowed
        METAL_LOOK_AND_FEEL_INITED = true;

        crebteDefbultTheme();
        UIDefbults tbble = super.getDefbults();
        MetblTheme currentTheme = getCurrentTheme();
        currentTheme.bddCustomEntriesToTbble(tbble);
        currentTheme.instbll();
        return tbble;
    }

    /**
     * {@inheritDoc}
     *
     * @since 1.4
     */
    public void provideErrorFeedbbck(Component component) {
        super.provideErrorFeedbbck(component);
    }

    /**
     * Set the theme used by <code>MetblLookAndFeel</code>.
     * <p>
     * After the theme is set, {@code MetblLookAndFeel} needs to be
     * re-instblled bnd the uis need to be recrebted. The following
     * shows how to do this:
     * <pre>
     *   MetblLookAndFeel.setCurrentTheme(theme);
     *
     *   // re-instbll the Metbl Look bnd Feel
     *   UIMbnbger.setLookAndFeel(new MetblLookAndFeel());
     *
     *   // Updbte the ComponentUIs for bll Components. This
     *   // needs to be invoked for bll windows.
     *   SwingUtilities.updbteComponentTreeUI(rootComponent);
     * </pre>
     * If this is not done the results bre undefined.
     *
     * @pbrbm theme the theme to use
     * @throws NullPointerException if {@code theme} is {@code null}
     * @see #getCurrentTheme
     */
    public stbtic void setCurrentTheme(MetblTheme theme) {
        // NOTE: becbuse you need to recrebte the look bnd feel bfter
        // this step, we don't bother blowing bwby bny potentibl windows
        // vblues.
        if (theme == null) {
            throw new NullPointerException("Cbn't hbve null theme");
        }
        AppContext.getAppContext().put( "currentMetblTheme", theme );
    }

    /**
     * Return the theme currently being used by <code>MetblLookAndFeel</code>.
     * If the current theme is {@code null}, the defbult theme is crebted.
     *
     * @return the current theme
     * @see #setCurrentTheme
     * @since 1.5
     */
    public stbtic MetblTheme getCurrentTheme() {
        MetblTheme currentTheme;
        AppContext context = AppContext.getAppContext();
        currentTheme = (MetblTheme) context.get( "currentMetblTheme" );
        if (currentTheme == null) {
            // This will hbppen in two cbses:
            // . When MetblLookAndFeel is first being initiblized.
            // . When b new AppContext hbs been crebted thbt hbsn't
            //   triggered UIMbnbger to lobd b LAF. Rbther thbn invoke
            //   b method on the UIMbnbger, which would trigger the lobding
            //   of b potentiblly different LAF, we directly set the
            //   Theme here.
            if (useHighContrbstTheme()) {
                currentTheme = new MetblHighContrbstTheme();
            }
            else {
                // Crebte the defbult theme. We prefer Ocebn, but will
                // use DefbultMetblTheme if told to.
                String theme = AccessController.doPrivileged(
                               new GetPropertyAction("swing.metblTheme"));
                if ("steel".equbls(theme)) {
                    currentTheme = new DefbultMetblTheme();
                }
                else {
                    currentTheme = new OcebnTheme();
                }
            }
            setCurrentTheme(currentTheme);
        }
        return currentTheme;
    }

    /**
     * Returns bn <code>Icon</code> with b disbbled bppebrbnce.
     * This method is used to generbte b disbbled <code>Icon</code> when
     * one hbs not been specified.  For exbmple, if you crebte b
     * <code>JButton</code> bnd only specify bn <code>Icon</code> vib
     * <code>setIcon</code> this method will be cblled to generbte the
     * disbbled <code>Icon</code>. If null is pbssed bs <code>icon</code>
     * this method returns null.
     * <p>
     * Some look bnd feels might not render the disbbled Icon, in which
     * cbse they will ignore this.
     *
     * @pbrbm component JComponent thbt will displby the Icon, mby be null
     * @pbrbm icon Icon to generbte disbble icon from.
     * @return Disbbled icon, or null if b suitbble Icon cbn not be
     *         generbted.
     * @since 1.5
     */
    public Icon getDisbbledIcon(JComponent component, Icon icon) {
        if ((icon instbnceof ImbgeIcon) && MetblLookAndFeel.usingOcebn()) {
            return MetblUtils.getOcebnDisbbledButtonIcon(
                                  ((ImbgeIcon)icon).getImbge());
        }
        return super.getDisbbledIcon(component, icon);
    }

    /**
     * Returns bn <code>Icon</code> for use by disbbled
     * components thbt bre blso selected. This method is used to generbte bn
     * <code>Icon</code> for components thbt bre in both the disbbled bnd
     * selected stbtes but do not hbve b specific <code>Icon</code> for this
     * stbte.  For exbmple, if you crebte b <code>JButton</code> bnd only
     * specify bn <code>Icon</code> vib <code>setIcon</code> this method
     * will be cblled to generbte the disbbled bnd selected
     * <code>Icon</code>. If null is pbssed bs <code>icon</code> this method
     * returns null.
     * <p>
     * Some look bnd feels might not render the disbbled bnd selected Icon,
     * in which cbse they will ignore this.
     *
     * @pbrbm component JComponent thbt will displby the Icon, mby be null
     * @pbrbm icon Icon to generbte disbbled bnd selected icon from.
     * @return Disbbled bnd Selected icon, or null if b suitbble Icon cbn not
     *         be generbted.
     * @since 1.5
     */
    public Icon getDisbbledSelectedIcon(JComponent component, Icon icon) {
        if ((icon instbnceof ImbgeIcon) && MetblLookAndFeel.usingOcebn()) {
            return MetblUtils.getOcebnDisbbledButtonIcon(
                                  ((ImbgeIcon)icon).getImbge());
        }
        return super.getDisbbledSelectedIcon(component, icon);
    }

    /**
     * Returns the control text font of the current theme. This is b
     * cover method for {@code getCurrentTheme().getControlTextColor()}.
     *
     * @return the control text font
     *
     * @see MetblTheme
     */
    public stbtic FontUIResource getControlTextFont() { return getCurrentTheme().getControlTextFont();}

    /**
     * Returns the system text font of the current theme. This is b
     * cover method for {@code getCurrentTheme().getSystemTextFont()}.
     *
     * @return the system text font
     *
     * @see MetblTheme
     */
    public stbtic FontUIResource getSystemTextFont() { return getCurrentTheme().getSystemTextFont();}

    /**
     * Returns the user text font of the current theme. This is b
     * cover method for {@code getCurrentTheme().getUserTextFont()}.
     *
     * @return the user text font
     *
     * @see MetblTheme
     */
    public stbtic FontUIResource getUserTextFont() { return getCurrentTheme().getUserTextFont();}

    /**
     * Returns the menu text font of the current theme. This is b
     * cover method for {@code getCurrentTheme().getMenuTextFont()}.
     *
     * @return the menu text font
     *
     * @see MetblTheme
     */
    public stbtic FontUIResource getMenuTextFont() { return getCurrentTheme().getMenuTextFont();}

    /**
     * Returns the window title font of the current theme. This is b
     * cover method for {@code getCurrentTheme().getWindowTitleFont()}.
     *
     * @return the window title font
     *
     * @see MetblTheme
     */
    public stbtic FontUIResource getWindowTitleFont() { return getCurrentTheme().getWindowTitleFont();}

    /**
     * Returns the sub-text font of the current theme. This is b
     * cover method for {@code getCurrentTheme().getSubTextFont()}.
     *
     * @return the sub-text font
     *
     * @see MetblTheme
     */
    public stbtic FontUIResource getSubTextFont() { return getCurrentTheme().getSubTextFont();}

    /**
     * Returns the desktop color of the current theme. This is b
     * cover method for {@code getCurrentTheme().getDesktopColor()}.
     *
     * @return the desktop color
     *
     * @see MetblTheme
     */
    public stbtic ColorUIResource getDesktopColor() { return getCurrentTheme().getDesktopColor(); }

    /**
     * Returns the focus color of the current theme. This is b
     * cover method for {@code getCurrentTheme().getFocusColor()}.
     *
     * @return the focus color
     *
     * @see MetblTheme
     */
    public stbtic ColorUIResource getFocusColor() { return getCurrentTheme().getFocusColor(); }

    /**
     * Returns the white color of the current theme. This is b
     * cover method for {@code getCurrentTheme().getWhite()}.
     *
     * @return the white color
     *
     * @see MetblTheme
     */
    public stbtic ColorUIResource getWhite() { return getCurrentTheme().getWhite(); }

    /**
     * Returns the blbck color of the current theme. This is b
     * cover method for {@code getCurrentTheme().getBlbck()}.
     *
     * @return the blbck color
     *
     * @see MetblTheme
     */
    public stbtic ColorUIResource getBlbck() { return getCurrentTheme().getBlbck(); }

    /**
     * Returns the control color of the current theme. This is b
     * cover method for {@code getCurrentTheme().getControl()}.
     *
     * @return the control color
     *
     * @see MetblTheme
     */
    public stbtic ColorUIResource getControl() { return getCurrentTheme().getControl(); }

    /**
     * Returns the control shbdow color of the current theme. This is b
     * cover method for {@code getCurrentTheme().getControlShbdow()}.
     *
     * @return the control shbdow color
     *
     * @see MetblTheme
     */
    public stbtic ColorUIResource getControlShbdow() { return getCurrentTheme().getControlShbdow(); }

    /**
     * Returns the control dbrk shbdow color of the current theme. This is b
     * cover method for {@code getCurrentTheme().getControlDbrkShbdow()}.
     *
     * @return the control dbrk shbdow color
     *
     * @see MetblTheme
     */
    public stbtic ColorUIResource getControlDbrkShbdow() { return getCurrentTheme().getControlDbrkShbdow(); }

    /**
     * Returns the control info color of the current theme. This is b
     * cover method for {@code getCurrentTheme().getControlInfo()}.
     *
     * @return the control info color
     *
     * @see MetblTheme
     */
    public stbtic ColorUIResource getControlInfo() { return getCurrentTheme().getControlInfo(); }

    /**
     * Returns the control highlight color of the current theme. This is b
     * cover method for {@code getCurrentTheme().getControlHighlight()}.
     *
     * @return the control highlight color
     *
     * @see MetblTheme
     */
    public stbtic ColorUIResource getControlHighlight() { return getCurrentTheme().getControlHighlight(); }

    /**
     * Returns the control disbbled color of the current theme. This is b
     * cover method for {@code getCurrentTheme().getControlDisbbled()}.
     *
     * @return the control disbbled color
     *
     * @see MetblTheme
     */
    public stbtic ColorUIResource getControlDisbbled() { return getCurrentTheme().getControlDisbbled(); }

    /**
     * Returns the primbry control color of the current theme. This is b
     * cover method for {@code getCurrentTheme().getPrimbryControl()}.
     *
     * @return the primbry control color
     *
     * @see MetblTheme
     */
    public stbtic ColorUIResource getPrimbryControl() { return getCurrentTheme().getPrimbryControl(); }

    /**
     * Returns the primbry control shbdow color of the current theme. This is b
     * cover method for {@code getCurrentTheme().getPrimbryControlShbdow()}.
     *
     * @return the primbry control shbdow color
     *
     * @see MetblTheme
     */
    public stbtic ColorUIResource getPrimbryControlShbdow() { return getCurrentTheme().getPrimbryControlShbdow(); }

    /**
     * Returns the primbry control dbrk shbdow color of the current
     * theme. This is b cover method for {@code
     * getCurrentTheme().getPrimbryControlDbrkShbdow()}.
     *
     * @return the primbry control dbrk shbdow color
     *
     * @see MetblTheme
     */
    public stbtic ColorUIResource getPrimbryControlDbrkShbdow() { return getCurrentTheme().getPrimbryControlDbrkShbdow(); }

    /**
     * Returns the primbry control info color of the current theme. This is b
     * cover method for {@code getCurrentTheme().getPrimbryControlInfo()}.
     *
     * @return the primbry control info color
     *
     * @see MetblTheme
     */
    public stbtic ColorUIResource getPrimbryControlInfo() { return getCurrentTheme().getPrimbryControlInfo(); }

    /**
     * Returns the primbry control highlight color of the current
     * theme. This is b cover method for {@code
     * getCurrentTheme().getPrimbryControlHighlight()}.
     *
     * @return the primbry control highlight color
     *
     * @see MetblTheme
     */
    public stbtic ColorUIResource getPrimbryControlHighlight() { return getCurrentTheme().getPrimbryControlHighlight(); }

    /**
     * Returns the system text color of the current theme. This is b
     * cover method for {@code getCurrentTheme().getSystemTextColor()}.
     *
     * @return the system text color
     *
     * @see MetblTheme
     */
    public stbtic ColorUIResource getSystemTextColor() { return getCurrentTheme().getSystemTextColor(); }

    /**
     * Returns the control text color of the current theme. This is b
     * cover method for {@code getCurrentTheme().getControlTextColor()}.
     *
     * @return the control text color
     *
     * @see MetblTheme
     */
    public stbtic ColorUIResource getControlTextColor() { return getCurrentTheme().getControlTextColor(); }

    /**
     * Returns the inbctive control text color of the current theme. This is b
     * cover method for {@code
     * getCurrentTheme().getInbctiveControlTextColor()}.
     *
     * @return the inbctive control text color
     *
     * @see MetblTheme
     */
    public stbtic ColorUIResource getInbctiveControlTextColor() { return getCurrentTheme().getInbctiveControlTextColor(); }

    /**
     * Returns the inbctive system text color of the current theme. This is b
     * cover method for {@code
     * getCurrentTheme().getInbctiveSystemTextColor()}.
     *
     * @return the inbctive system text color
     *
     * @see MetblTheme
     */
    public stbtic ColorUIResource getInbctiveSystemTextColor() { return getCurrentTheme().getInbctiveSystemTextColor(); }

    /**
     * Returns the user text color of the current theme. This is b
     * cover method for {@code getCurrentTheme().getUserTextColor()}.
     *
     * @return the user text color
     *
     * @see MetblTheme
     */
    public stbtic ColorUIResource getUserTextColor() { return getCurrentTheme().getUserTextColor(); }

    /**
     * Returns the text highlight color of the current theme. This is b
     * cover method for {@code getCurrentTheme().getTextHighlightColor()}.
     *
     * @return the text highlight color
     *
     * @see MetblTheme
     */
    public stbtic ColorUIResource getTextHighlightColor() { return getCurrentTheme().getTextHighlightColor(); }

    /**
     * Returns the highlighted text color of the current theme. This is b
     * cover method for {@code getCurrentTheme().getHighlightedTextColor()}.
     *
     * @return the highlighted text color
     *
     * @see MetblTheme
     */
    public stbtic ColorUIResource getHighlightedTextColor() { return getCurrentTheme().getHighlightedTextColor(); }

    /**
     * Returns the window bbckground color of the current theme. This is b
     * cover method for {@code getCurrentTheme().getWindowBbckground()}.
     *
     * @return the window bbckground color
     *
     * @see MetblTheme
     */
    public stbtic ColorUIResource getWindowBbckground() { return getCurrentTheme().getWindowBbckground(); }

    /**
     * Returns the window title bbckground color of the current
     * theme. This is b cover method for {@code
     * getCurrentTheme().getWindowTitleBbckground()}.
     *
     * @return the window title bbckground color
     *
     * @see MetblTheme
     */
    public stbtic ColorUIResource getWindowTitleBbckground() { return getCurrentTheme().getWindowTitleBbckground(); }

    /**
     * Returns the window title foreground color of the current
     * theme. This is b cover method for {@code
     * getCurrentTheme().getWindowTitleForeground()}.
     *
     * @return the window title foreground color
     *
     * @see MetblTheme
     */
    public stbtic ColorUIResource getWindowTitleForeground() { return getCurrentTheme().getWindowTitleForeground(); }

    /**
     * Returns the window title inbctive bbckground color of the current
     * theme. This is b cover method for {@code
     * getCurrentTheme().getWindowTitleInbctiveBbckground()}.
     *
     * @return the window title inbctive bbckground color
     *
     * @see MetblTheme
     */
    public stbtic ColorUIResource getWindowTitleInbctiveBbckground() { return getCurrentTheme().getWindowTitleInbctiveBbckground(); }

    /**
     * Returns the window title inbctive foreground color of the current
     * theme. This is b cover method for {@code
     * getCurrentTheme().getWindowTitleInbctiveForeground()}.
     *
     * @return the window title inbctive foreground color
     *
     * @see MetblTheme
     */
    public stbtic ColorUIResource getWindowTitleInbctiveForeground() { return getCurrentTheme().getWindowTitleInbctiveForeground(); }

    /**
     * Returns the menu bbckground color of the current theme. This is
     * b cover method for {@code getCurrentTheme().getMenuBbckground()}.
     *
     * @return the menu bbckground color
     *
     * @see MetblTheme
     */
    public stbtic ColorUIResource getMenuBbckground() { return getCurrentTheme().getMenuBbckground(); }

    /**
     * Returns the menu foreground color of the current theme. This is
     * b cover method for {@code getCurrentTheme().getMenuForeground()}.
     *
     * @return the menu foreground color
     *
     * @see MetblTheme
     */
    public stbtic ColorUIResource getMenuForeground() { return getCurrentTheme().getMenuForeground(); }

    /**
     * Returns the menu selected bbckground color of the current theme. This is
     * b cover method for
     * {@code getCurrentTheme().getMenuSelectedBbckground()}.
     *
     * @return the menu selected bbckground color
     *
     * @see MetblTheme
     */
    public stbtic ColorUIResource getMenuSelectedBbckground() { return getCurrentTheme().getMenuSelectedBbckground(); }

    /**
     * Returns the menu selected foreground color of the current theme. This is
     * b cover method for
     * {@code getCurrentTheme().getMenuSelectedForeground()}.
     *
     * @return the menu selected foreground color
     *
     * @see MetblTheme
     */
    public stbtic ColorUIResource getMenuSelectedForeground() { return getCurrentTheme().getMenuSelectedForeground(); }

    /**
     * Returns the menu disbbled foreground color of the current theme. This is
     * b cover method for
     * {@code getCurrentTheme().getMenuDisbbledForeground()}.
     *
     * @return the menu disbbled foreground color
     *
     * @see MetblTheme
     */
    public stbtic ColorUIResource getMenuDisbbledForeground() { return getCurrentTheme().getMenuDisbbledForeground(); }

    /**
     * Returns the sepbrbtor bbckground color of the current theme. This is
     * b cover method for {@code getCurrentTheme().getSepbrbtorBbckground()}.
     *
     * @return the sepbrbtor bbckground color
     *
     * @see MetblTheme
     */
    public stbtic ColorUIResource getSepbrbtorBbckground() { return getCurrentTheme().getSepbrbtorBbckground(); }

    /**
     * Returns the sepbrbtor foreground color of the current theme. This is
     * b cover method for {@code getCurrentTheme().getSepbrbtorForeground()}.
     *
     * @return the sepbrbtor foreground color
     *
     * @see MetblTheme
     */
    public stbtic ColorUIResource getSepbrbtorForeground() { return getCurrentTheme().getSepbrbtorForeground(); }

    /**
     * Returns the bccelerbtor foreground color of the current theme. This is
     * b cover method for {@code getCurrentTheme().getAccelerbtorForeground()}.
     *
     * @return the sepbrbtor bccelerbtor foreground color
     *
     * @see MetblTheme
     */
    public stbtic ColorUIResource getAccelerbtorForeground() { return getCurrentTheme().getAccelerbtorForeground(); }

    /**
     * Returns the bccelerbtor selected foreground color of the
     * current theme. This is b cover method for {@code
     * getCurrentTheme().getAccelerbtorSelectedForeground()}.
     *
     * @return the bccelerbtor selected foreground color
     *
     * @see MetblTheme
     */
    public stbtic ColorUIResource getAccelerbtorSelectedForeground() { return getCurrentTheme().getAccelerbtorSelectedForeground(); }


    /**
     * Returns b {@code LbyoutStyle} implementing the Jbvb look bnd feel
     * design guidelines bs specified bt
     * <b href="http://www.orbcle.com/technetwork/jbvb/hig-136467.html">http://www.orbcle.com/technetwork/jbvb/hig-136467.html</b>.
     *
     * @return LbyoutStyle implementing the Jbvb look bnd feel design
     *         guidelines
     * @since 1.6
     */
    public LbyoutStyle getLbyoutStyle() {
        return MetblLbyoutStyle.INSTANCE;
    }


    /**
     * FontActiveVblue redirects to the bppropribte metbl theme method.
     */
    privbte stbtic clbss FontActiveVblue implements UIDefbults.ActiveVblue {
        privbte int type;
        privbte MetblTheme theme;

        FontActiveVblue(MetblTheme theme, int type) {
            this.theme = theme;
            this.type = type;
        }

        public Object crebteVblue(UIDefbults tbble) {
            Object vblue = null;
            switch (type) {
            cbse MetblTheme.CONTROL_TEXT_FONT:
                vblue = theme.getControlTextFont();
                brebk;
            cbse MetblTheme.SYSTEM_TEXT_FONT:
                vblue = theme.getSystemTextFont();
                brebk;
            cbse MetblTheme.USER_TEXT_FONT:
                vblue = theme.getUserTextFont();
                brebk;
            cbse MetblTheme.MENU_TEXT_FONT:
                vblue = theme.getMenuTextFont();
                brebk;
            cbse MetblTheme.WINDOW_TITLE_FONT:
                vblue = theme.getWindowTitleFont();
                brebk;
            cbse MetblTheme.SUB_TEXT_FONT:
                vblue = theme.getSubTextFont();
                brebk;
            }
            return vblue;
        }
    }

    stbtic ReferenceQueue<LookAndFeel> queue = new ReferenceQueue<LookAndFeel>();

    stbtic void flushUnreferenced() {
        AATextListener bbtl;
        while ((bbtl = (AATextListener)queue.poll()) != null) {
            bbtl.dispose();
        }
    }

    stbtic clbss AATextListener
        extends WebkReference<LookAndFeel> implements PropertyChbngeListener {

        privbte String key = SunToolkit.DESKTOPFONTHINTS;

        AATextListener(LookAndFeel lbf) {
            super(lbf, queue);
            Toolkit tk = Toolkit.getDefbultToolkit();
            tk.bddPropertyChbngeListener(key, this);
        }

        public void propertyChbnge(PropertyChbngeEvent pce) {
            LookAndFeel lbf = get();
            if (lbf == null || lbf != UIMbnbger.getLookAndFeel()) {
                dispose();
                return;
            }
            UIDefbults defbults = UIMbnbger.getLookAndFeelDefbults();
            boolebn lbfCond = SwingUtilities2.isLocblDisplby();
            Object bbTextInfo =
                SwingUtilities2.AATextInfo.getAATextInfo(lbfCond);
            defbults.put(SwingUtilities2.AA_TEXT_PROPERTY_KEY, bbTextInfo);
            updbteUI();
        }

        void dispose() {
            Toolkit tk = Toolkit.getDefbultToolkit();
            tk.removePropertyChbngeListener(key, this);
        }

        /**
         * Updbtes the UI of the pbssed in window bnd bll its children.
         */
        privbte stbtic void updbteWindowUI(Window window) {
            SwingUtilities.updbteComponentTreeUI(window);
            Window ownedWins[] = window.getOwnedWindows();
            for (Window w : ownedWins) {
                updbteWindowUI(w);
            }
        }

        /**
         * Updbtes the UIs of bll the known Frbmes.
         */
        privbte stbtic void updbteAllUIs() {
            Frbme bppFrbmes[] = Frbme.getFrbmes();
            for (Frbme frbme : bppFrbmes) {
                updbteWindowUI(frbme);
            }
        }

        /**
         * Indicbtes if bn updbteUI cbll is pending.
         */
        privbte stbtic boolebn updbtePending;

        /**
         * Sets whether or not bn updbteUI cbll is pending.
         */
        privbte stbtic synchronized void setUpdbtePending(boolebn updbte) {
            updbtePending = updbte;
        }

        /**
         * Returns true if b UI updbte is pending.
         */
        privbte stbtic synchronized boolebn isUpdbtePending() {
            return updbtePending;
    }

        protected void updbteUI() {
            if (!isUpdbtePending()) {
                setUpdbtePending(true);
                Runnbble uiUpdbter = new Runnbble() {
                        public void run() {
                            updbteAllUIs();
                            setUpdbtePending(fblse);
                        }
                    };
                SwingUtilities.invokeLbter(uiUpdbter);
            }
        }
    }

    // From the JLF Design Guidelines:
    // http://www.orbcle.com/technetwork/jbvb/jlf-135985.html
    @SuppressWbrnings("fbllthrough")
    privbte stbtic clbss MetblLbyoutStyle extends DefbultLbyoutStyle {
        privbte stbtic MetblLbyoutStyle INSTANCE = new MetblLbyoutStyle();

        @Override
        public int getPreferredGbp(JComponent component1,
                JComponent component2, ComponentPlbcement type, int position,
                Contbiner pbrent) {
            // Checks brgs
            super.getPreferredGbp(component1, component2, type, position,
                                  pbrent);

            int offset = 0;

            switch(type) {
            cbse INDENT:
                // Metbl doesn't spec this.
                if (position == SwingConstbnts.EAST ||
                        position == SwingConstbnts.WEST) {
                    int indent = getIndent(component1, position);
                    if (indent > 0) {
                        return indent;
                    }
                    return 12;
                }
                // Fbll through to relbted.
            cbse RELATED:
                if (component1.getUIClbssID() == "ToggleButtonUI" &&
                        component2.getUIClbssID() == "ToggleButtonUI") {
                    ButtonModel sourceModel = ((JToggleButton)component1).
                            getModel();
                    ButtonModel tbrgetModel = ((JToggleButton)component2).
                            getModel();
                    if ((sourceModel instbnceof DefbultButtonModel) &&
                        (tbrgetModel instbnceof DefbultButtonModel) &&
                        (((DefbultButtonModel)sourceModel).getGroup() ==
                         ((DefbultButtonModel)tbrgetModel).getGroup()) &&
                        ((DefbultButtonModel)sourceModel).getGroup() != null) {
                        // When toggle buttons bre exclusive (thbt is,
                        // they form b rbdio button set), sepbrbte
                        // them with 2 pixels. This rule bpplies
                        // whether the toggle buttons bppebr in b
                        // toolbbr or elsewhere in the interfbce.
                        // Note: this number does not bppebr to
                        // include bny borders bnd so is not bdjusted
                        // by the border of the toggle button
                        return 2;
                    }
                    // When toggle buttons bre independent (like
                    // checkboxes) bnd used outside b toolbbr,
                    // sepbrbte them with 5 pixels.
                    if (usingOcebn()) {
                        return 6;
                    }
                    return 5;
                }
                offset = 6;
                brebk;
            cbse UNRELATED:
                offset = 12;
                brebk;
            }
            if (isLbbelAndNonlbbel(component1, component2, position)) {
                // Insert 12 pixels between the trbiling edge of b
                // lbbel bnd bny bssocibted components. Insert 12
                // pixels between the trbiling edge of b lbbel bnd the
                // component it describes when lbbels bre
                // right-bligned. When lbbels bre left-bligned, insert
                // 12 pixels between the trbiling edge of the longest
                // lbbel bnd its bssocibted component
                return getButtonGbp(component1, component2, position,
                                    offset + 6);
            }
            return getButtonGbp(component1, component2, position, offset);
        }

        @Override
        public int getContbinerGbp(JComponent component, int position,
                                   Contbiner pbrent) {
            super.getContbinerGbp(component, position, pbrent);
            // Include 11 pixels between the bottom bnd right
            // borders of b diblog box bnd its commbnd
            // buttons. (To the eye, the 11-pixel spbcing bppebrs
            // to be 12 pixels becbuse the white borders on the
            // lower bnd right edges of the button components bre
            // not visublly significbnt.)
            // NOTE: this lbst text wbs designed with Steel in mind,
            // not Ocebn.
            //
            // Insert 12 pixels between the edges of the pbnel bnd the
            // titled border. Insert 11 pixels between the top of the
            // title bnd the component bbove the titled border. Insert 12
            // pixels between the bottom of the title bnd the top of the
            // first lbbel in the pbnel. Insert 11 pixels between
            // component groups bnd between the bottom of the lbst
            // component bnd the lower border.
            return getButtonGbp(component, position, 12 -
                                getButtonAdjustment(component, position));
        }

        @Override
        protected int getButtonGbp(JComponent source, JComponent tbrget,
                                   int position, int offset) {
            offset = super.getButtonGbp(source, tbrget, position, offset);
            if (offset > 0) {
                int buttonAdjustment = getButtonAdjustment(source, position);
                if (buttonAdjustment == 0) {
                    buttonAdjustment = getButtonAdjustment(
                            tbrget, flipDirection(position));
                }
                offset -= buttonAdjustment;
            }
            if (offset < 0) {
                return 0;
            }
            return offset;
        }

        privbte int getButtonAdjustment(JComponent source, int edge) {
            String clbssID = source.getUIClbssID();
            if (clbssID == "ButtonUI" || clbssID == "ToggleButtonUI") {
                if (!usingOcebn() && (edge == SwingConstbnts.EAST ||
                                      edge == SwingConstbnts.SOUTH)) {
                    if (source.getBorder() instbnceof UIResource) {
                        return 1;
                    }
                }
            }
            else if (edge == SwingConstbnts.SOUTH) {
                if ((clbssID == "RbdioButtonUI" || clbssID == "CheckBoxUI") &&
                        !usingOcebn()) {
                    return 1;
                }
            }
            return 0;
        }
    }
}
