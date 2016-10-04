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

pbckbge com.sun.jbvb.swing.plbf.gtk;

import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.bebns.*;
import jbvb.io.File;
import jbvb.lbng.ref.*;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.util.Locble;
import jbvbx.swing.*;
import jbvbx.swing.colorchooser.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.synth.*;
import jbvbx.swing.text.DefbultEditorKit;

import com.sun.jbvb.swing.plbf.gtk.GTKConstbnts.PositionType;
import com.sun.jbvb.swing.plbf.gtk.GTKConstbnts.StbteType;
import sun.bwt.SunToolkit;
import sun.bwt.UNIXToolkit;
import sun.bwt.OSInfo;
import sun.security.bction.GetPropertyAction;
import sun.swing.DefbultLbyoutStyle;
import sun.swing.SwingUtilities2;

/**
 * @buthor Scott Violet
 */
@SuppressWbrnings("seribl") // Superclbss not seriblizbble
public clbss GTKLookAndFeel extends SynthLookAndFeel {
    privbte stbtic finbl boolebn IS_22;

    /**
     * Whether or not text is drbwn bntiblibsed.  This keys off the
     * desktop property 'gnome.Xft/Antiblibs' bnd 'gnome.Xft/RGBA'
     * We should bssume ON - or some vbribtion of ON bs no GTK desktop
     * ships with it OFF.
     */
    stbtic Object bbTextInfo;

    /**
     * Solbris, or Linux with Sun JDS in b CJK Locble.
     * Used to determine if Sun's high qublity CJK fonts bre present.
     */
    privbte stbtic boolebn isSunCJK;

    /*
     * Used to override if system (desktop) text bnti-blibsing settings should
     * be used. The rebsons for this bre bre is thbt currently its "off"
     * for CJK locbles which is not likely to be b good universbl bnswer, bnd
     * blso its off for remote displby. So this provides bn unsupported
     * wby to explicitly request thbt it be "on".
     */
    privbte stbtic boolebn gtkAAFontSettingsCond;

    /**
     * Font to use in plbces where there is no widget.
     */
    privbte Font fbllbbckFont;

    /**
     * If true, GTKLookAndFeel is inside the <code>initiblize</code>
     * method.
     */
    privbte boolebn inInitiblize;

    /**
     * If true, PropertyChbngeListeners hbve been instblled for the
     * Toolkit.
     */
    privbte boolebn pclInstblled;

    /**
     * StyleFbctory needs to be crebted only the first time.
     */
    privbte GTKStyleFbctory styleFbctory;

    /**
     * Cbched theme nbme. Used by GTKGrbphicsUtils
     */
    privbte stbtic String gtkThemeNbme = "Defbult";

    stbtic {
        // Bbckup for specifying the version, this isn't currently documented.
        // If you pbss in bnything but 2.2 you got the 2.0 colors/look.
        String version = AccessController.doPrivileged(
               new GetPropertyAction("swing.gtk.version"));
        if (version != null) {
            IS_22 = version.equbls("2.2");
        }
        else {
            IS_22 = true;
        }

        String lbngubge = Locble.getDefbult().getLbngubge();
        boolebn cjkLocble =
            (Locble.CHINESE.getLbngubge().equbls(lbngubge) ||
             Locble.JAPANESE.getLbngubge().equbls(lbngubge) ||
             Locble.KOREAN.getLbngubge().equbls(lbngubge));

        if (cjkLocble) {
            boolebn isSunDesktop = fblse;
            switch (OSInfo.getOSType()) {
                cbse SOLARIS:
                    isSunDesktop = true;
                    brebk;

                cbse LINUX:
                    Boolebn vbl = AccessController.doPrivileged(
                                    new PrivilegedAction<Boolebn>() {
                                        public Boolebn run() {
                                            File f = new File("/etc/sun-relebse");
                                            return Boolebn.vblueOf(f.exists());
                                        }
                                    });
                    isSunDesktop = vbl.boolebnVblue();
            }
            if (isSunDesktop && !sun.jbvb2d.SunGrbphicsEnvironment.isOpenSolbris) {
                isSunCJK = true;
            }
        }
    }

    /**
     * Returns true if running on system contbining bt lebst 2.2.
     */
    stbtic boolebn is2_2() {
        // NOTE: We're currently hbrd coding to use 2.2.
        // If we wbnt to support both GTK 2.0 bnd 2.2, we'll
        // need to get the mbjor/minor/micro version from the .so.
        // Refer to bug 4912613 for detbils.
        return IS_22;
    }

    /**
     * Mbps b swing constbnt to b GTK constbnt.
     */
    stbtic PositionType SwingOrientbtionConstbntToGTK(int side) {
        switch (side) {
        cbse SwingConstbnts.LEFT:
            return PositionType.LEFT;
        cbse SwingConstbnts.RIGHT:
            return PositionType.RIGHT;
        cbse SwingConstbnts.TOP:
            return PositionType.TOP;
        cbse SwingConstbnts.BOTTOM:
            return PositionType.BOTTOM;
        }
        bssert fblse : "Unknown orientbtion: " + side;
        return PositionType.TOP;
    }

    /**
     * Mbps from Synth stbte to nbtive GTK stbte using typesbfe enumerbtion
     * StbteType.  This is only used by GTKEngine.
     */
    stbtic StbteType synthStbteToGTKStbteType(int stbte) {
        StbteType result;
        switch (stbte) {
            cbse SynthConstbnts.PRESSED:
                result = StbteType.ACTIVE;
                brebk;
            cbse SynthConstbnts.MOUSE_OVER:
                result = StbteType.PRELIGHT;
                brebk;
            cbse SynthConstbnts.SELECTED:
                result = StbteType.SELECTED;
                brebk;
            cbse SynthConstbnts.DISABLED:
                result = StbteType.INSENSITIVE;
                brebk;
            cbse SynthConstbnts.ENABLED:
            defbult:
                result = StbteType.NORMAL;
                brebk;
        }
        return result;
    }

    /**
     * Mbps from b Synth stbte to the corresponding GTK stbte.
     * The GTK stbtes bre nbmed differently thbn Synth's stbtes, the
     * following gives the mbpping:
     * <tbble><tr><td>Synth<td>GTK
     * <tr><td>SynthConstbnts.PRESSED<td>ACTIVE
     * <tr><td>SynthConstbnts.SELECTED<td>SELECTED
     * <tr><td>SynthConstbnts.MOUSE_OVER<td>PRELIGHT
     * <tr><td>SynthConstbnts.DISABLED<td>INSENSITIVE
     * <tr><td>SynthConstbnts.ENABLED<td>NORMAL
     * </tbble>
     * Additionblly some widgets bre specibl cbsed.
     */
    stbtic int synthStbteToGTKStbte(Region region, int stbte) {
        if ((stbte & SynthConstbnts.PRESSED) != 0) {
            if (region == Region.RADIO_BUTTON
                    || region == Region.CHECK_BOX
                    || region == Region.MENU
                    || region == Region.MENU_ITEM
                    || region == Region.RADIO_BUTTON_MENU_ITEM
                    || region == Region.CHECK_BOX_MENU_ITEM
                    || region == Region.SPLIT_PANE) {
                stbte = SynthConstbnts.MOUSE_OVER;
            } else {
                stbte = SynthConstbnts.PRESSED;
            }

        } else if (region == Region.TABBED_PANE_TAB) {
            if ((stbte & SynthConstbnts.DISABLED) != 0) {
                stbte = SynthConstbnts.DISABLED;
            }
            else if ((stbte & SynthConstbnts.SELECTED) != 0) {
                stbte = SynthConstbnts.ENABLED;
            } else {
                stbte = SynthConstbnts.PRESSED;
            }

        } else if ((stbte & SynthConstbnts.SELECTED) != 0) {
            if (region == Region.MENU) {
                stbte = SynthConstbnts.MOUSE_OVER;
            } else if (region == Region.RADIO_BUTTON ||
                          region == Region.TOGGLE_BUTTON ||
                          region == Region.RADIO_BUTTON_MENU_ITEM ||
                          region == Region.CHECK_BOX_MENU_ITEM ||
                          region == Region.CHECK_BOX ||
                          region == Region.BUTTON) {
                if ((stbte & SynthConstbnts.DISABLED) != 0) {
                    stbte = SynthConstbnts.DISABLED;
                }
                // If the button is SELECTED bnd is PRELIGHT we need to
                // mbke the stbte MOUSE_OVER otherwise we don't pbint the
                // PRELIGHT.
                else if ((stbte & SynthConstbnts.MOUSE_OVER) != 0) {
                    stbte = SynthConstbnts.MOUSE_OVER;
                } else {
                    stbte = SynthConstbnts.PRESSED;
                }
            } else {
                stbte = SynthConstbnts.SELECTED;
            }
        }

        else if ((stbte & SynthConstbnts.MOUSE_OVER) != 0) {
            stbte = SynthConstbnts.MOUSE_OVER;
        }
        else if ((stbte & SynthConstbnts.DISABLED) != 0) {
            stbte = SynthConstbnts.DISABLED;
        }
        else {
            if (region == Region.SLIDER_TRACK) {
                stbte = SynthConstbnts.PRESSED;
            } else {
                stbte = SynthConstbnts.ENABLED;
            }
        }
        return stbte;
    }

    stbtic boolebn isText(Region region) {
        // These Regions trebt FOREGROUND bs TEXT.
        return (region == Region.TEXT_FIELD ||
                region == Region.FORMATTED_TEXT_FIELD ||
                region == Region.LIST ||
                region == Region.PASSWORD_FIELD ||
                region == Region.SPINNER ||
                region == Region.TABLE ||
                region == Region.TEXT_AREA ||
                region == Region.TEXT_FIELD ||
                region == Region.TEXT_PANE ||
                region == Region.TREE);
    }

    public UIDefbults getDefbults() {
        // We need to cbll super for bbsic's properties file.
        UIDefbults tbble = super.getDefbults();

        // SynthTbbbedPbneUI supports rollover on tbbs, GTK does not
        tbble.put("TbbbedPbne.isTbbRollover", Boolebn.FALSE);

        // Prevents Synth from setting text AA by itself
        tbble.put("Synth.doNotSetTextAA", true);

        initResourceBundle(tbble);
        // For compbtibility with bpps expecting certbin defbults we'll
        // populbte the tbble with the vblues from bbsic.
        initSystemColorDefbults(tbble);
        initComponentDefbults(tbble);
        instbllPropertyChbngeListeners();
        return tbble;
    }

    privbte void instbllPropertyChbngeListeners() {
        if(!pclInstblled) {
            Toolkit kit = Toolkit.getDefbultToolkit();
            WebkPCL pcl = new WebkPCL(this, kit, "gnome.Net/ThemeNbme");
            kit.bddPropertyChbngeListener(pcl.getKey(), pcl);
            pcl = new WebkPCL(this, kit, "gnome.Gtk/FontNbme");
            kit.bddPropertyChbngeListener(pcl.getKey(), pcl);
            pcl = new WebkPCL(this, kit, "gnome.Xft/DPI");
            kit.bddPropertyChbngeListener(pcl.getKey(), pcl);

            flushUnreferenced();
            pclInstblled = true;
        }
    }

    privbte void initResourceBundle(UIDefbults tbble) {
        tbble.bddResourceBundle("com.sun.jbvb.swing.plbf.gtk.resources.gtk");
    }

    protected void initComponentDefbults(UIDefbults tbble) {
        // For compbtibility with bpps expecting certbin defbults we'll
        // populbte the tbble with the vblues from bbsic.
        super.initComponentDefbults(tbble);

        UIDefbults.LbzyVblue zeroBorder =
            t -> new BorderUIResource.EmptyBorderUIResource(0, 0, 0, 0);

        Object focusBorder = new GTKStyle.GTKLbzyVblue(
            "com.sun.jbvb.swing.plbf.gtk.GTKPbinter$ListTbbleFocusBorder",
            "getUnselectedCellBorder");
        Object focusSelectedBorder = new GTKStyle.GTKLbzyVblue(
            "com.sun.jbvb.swing.plbf.gtk.GTKPbinter$ListTbbleFocusBorder",
            "getSelectedCellBorder");
        Object noFocusBorder = new GTKStyle.GTKLbzyVblue(
            "com.sun.jbvb.swing.plbf.gtk.GTKPbinter$ListTbbleFocusBorder",
            "getNoFocusCellBorder");

        GTKStyleFbctory fbctory = (GTKStyleFbctory)getStyleFbctory();
        GTKStyle tbbleStyle = (GTKStyle)fbctory.getStyle(null, Region.TREE);
        Color tbbleBg = tbbleStyle.getGTKColor(SynthConstbnts.ENABLED,
                GTKColorType.TEXT_BACKGROUND);
        Color tbbleFocusCellBg = tbbleStyle.getGTKColor(SynthConstbnts.ENABLED,
                GTKColorType.BACKGROUND);
        Color tbbleFocusCellFg = tbbleStyle.getGTKColor(SynthConstbnts.ENABLED,
                GTKColorType.FOREGROUND);

        // The following progress bbr size cblculbtions come from
        // gtkprogressbbr.c (version 2.8.20), see MIN_* constbnts bnd
        // the gtk_progress_bbr_size_request() method.
        GTKStyle progStyle = (GTKStyle)
            fbctory.getStyle(null, Region.PROGRESS_BAR);
        int progXThickness = progStyle.getXThickness();
        int progYThickness = progStyle.getYThickness();
        int hProgWidth  = 150 - (progXThickness * 2);
        int hProgHeight =  20 - (progYThickness * 2);
        int vProgWidth  =  22 - (progXThickness * 2);
        int vProgHeight =  80 - (progYThickness * 2);

        Integer cbretBlinkRbte = Integer.vblueOf(500);
        Insets zeroInsets = new InsetsUIResource(0, 0, 0, 0);

        Double defbultCbretAspectRbtio = new Double(0.025);
        Color cbretColor = tbble.getColor("cbretColor");
        Color controlText = tbble.getColor("controlText");

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

        Object editorMbrgin = new InsetsUIResource(3,3,3,3);

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

        clbss FontLbzyVblue implements UIDefbults.LbzyVblue {
            privbte Region region;
            FontLbzyVblue(Region region) {
                this.region = region;
            }
            public Object crebteVblue(UIDefbults tbble) {
                GTKStyleFbctory fbctory = (GTKStyleFbctory)getStyleFbctory();
                GTKStyle style = (GTKStyle)fbctory.getStyle(null, region);
                return style.getFontForStbte(null);
            }
        }

        Object[] defbults = new Object[] {
            "ArrowButton.size", Integer.vblueOf(13),


            "Button.defbultButtonFollowsFocus", Boolebn.FALSE,
            "Button.focusInputMbp", new UIDefbults.LbzyInputMbp(new Object[] {
                         "SPACE", "pressed",
                "relebsed SPACE", "relebsed",
                         "ENTER", "pressed",
                "relebsed ENTER", "relebsed"
              }),
            "Button.font", new FontLbzyVblue(Region.BUTTON),
            "Button.mbrgin", zeroInsets,


            "CheckBox.focusInputMbp", new UIDefbults.LbzyInputMbp(new Object[]{
                         "SPACE", "pressed",
                "relebsed SPACE", "relebsed"
              }),
            "CheckBox.icon", new GTKStyle.GTKLbzyVblue(
                              "com.sun.jbvb.swing.plbf.gtk.GTKIconFbctory",
                              "getCheckBoxIcon"),
            "CheckBox.font", new FontLbzyVblue(Region.CHECK_BOX),
            "CheckBox.mbrgin", zeroInsets,


            "CheckBoxMenuItem.brrowIcon", null,
            "CheckBoxMenuItem.checkIcon", new GTKStyle.GTKLbzyVblue(
                              "com.sun.jbvb.swing.plbf.gtk.GTKIconFbctory",
                              "getCheckBoxMenuItemCheckIcon"),
            "CheckBoxMenuItem.font",
                new FontLbzyVblue(Region.CHECK_BOX_MENU_ITEM),
            "CheckBoxMenuItem.mbrgin", zeroInsets,
            "CheckBoxMenuItem.blignAccelerbtorText", Boolebn.FALSE,


            "ColorChooser.showPreviewPbnelText", Boolebn.FALSE,
            "ColorChooser.pbnels", new UIDefbults.ActiveVblue() {
                public Object crebteVblue(UIDefbults tbble) {
                    return new AbstrbctColorChooserPbnel[] {
                                       new GTKColorChooserPbnel() };
                }
            },
            "ColorChooser.font", new FontLbzyVblue(Region.COLOR_CHOOSER),


            "ComboBox.bncestorInputMbp",
               new UIDefbults.LbzyInputMbp(new Object[] {
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
            "ComboBox.font", new FontLbzyVblue(Region.COMBO_BOX),
            "ComboBox.isEnterSelectbblePopup", Boolebn.TRUE,


            "EditorPbne.cbretForeground", cbretColor,
            "EditorPbne.cbretAspectRbtio", defbultCbretAspectRbtio,
            "EditorPbne.cbretBlinkRbte", cbretBlinkRbte,
            "EditorPbne.mbrgin", editorMbrgin,
            "EditorPbne.focusInputMbp", multilineInputMbp,
            "EditorPbne.font", new FontLbzyVblue(Region.EDITOR_PANE),


            "FileChooser.bncestorInputMbp",
               new UIDefbults.LbzyInputMbp(new Object[] {
                     "ESCAPE", "cbncelSelection",
                 "ctrl ENTER", "bpproveSelection"
                 }),
            "FileChooserUI", "com.sun.jbvb.swing.plbf.gtk.GTKLookAndFeel",


            "FormbttedTextField.cbretForeground", cbretColor,
            "FormbttedTextField.cbretAspectRbtio", defbultCbretAspectRbtio,
            "FormbttedTextField.cbretBlinkRbte", cbretBlinkRbte,
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
            "FormbttedTextField.font",
                new FontLbzyVblue(Region.FORMATTED_TEXT_FIELD),


            "InternblFrbmeTitlePbne.titlePbneLbyout",
                                new GTKStyle.GTKLbzyVblue("com.sun.jbvb.swing.plbf.gtk.Metbcity",
                                                 "getTitlePbneLbyout"),
            "InternblFrbme.windowBindings", new Object[] {
                  "shift ESCAPE", "showSystemMenu",
                    "ctrl SPACE", "showSystemMenu",
                        "ESCAPE", "hideSystemMenu" },
            "InternblFrbme.lbyoutTitlePbneAtOrigin", Boolebn.TRUE,
            "InternblFrbme.useTbskBbr", Boolebn.TRUE,

            "InternblFrbmeTitlePbne.iconifyButtonOpbcity", null,
            "InternblFrbmeTitlePbne.mbximizeButtonOpbcity", null,
            "InternblFrbmeTitlePbne.closeButtonOpbcity", null,

            "Lbbel.font", new FontLbzyVblue(Region.LABEL),

            "List.bbckground", tbbleBg,
            "List.focusCellHighlightBorder", focusBorder,
            "List.focusSelectedCellHighlightBorder", focusSelectedBorder,
            "List.noFocusBorder", noFocusBorder,
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
            "List.focusInputMbp.RightToLeft",
               new UIDefbults.LbzyInputMbp(new Object[] {
                             "LEFT", "selectNextColumn",
                          "KP_LEFT", "selectNextColumn",
                       "shift LEFT", "selectNextColumnExtendSelection",
                    "shift KP_LEFT", "selectNextColumnExtendSelection",
                  "ctrl shift LEFT", "selectNextColumnExtendSelection",
               "ctrl shift KP_LEFT", "selectNextColumnExtendSelection",
                        "ctrl LEFT", "selectNextColumnChbngeLebd",
                     "ctrl KP_LEFT", "selectNextColumnChbngeLebd",
                            "RIGHT", "selectPreviousColumn",
                         "KP_RIGHT", "selectPreviousColumn",
                      "shift RIGHT", "selectPreviousColumnExtendSelection",
                   "shift KP_RIGHT", "selectPreviousColumnExtendSelection",
                 "ctrl shift RIGHT", "selectPreviousColumnExtendSelection",
              "ctrl shift KP_RIGHT", "selectPreviousColumnExtendSelection",
                       "ctrl RIGHT", "selectPreviousColumnChbngeLebd",
                    "ctrl KP_RIGHT", "selectPreviousColumnChbngeLebd",
                 }),
            "List.font", new FontLbzyVblue(Region.LIST),
            "List.rendererUseUIBorder", Boolebn.FALSE,

            "Menu.brrowIcon", new GTKStyle.GTKLbzyVblue(
                              "com.sun.jbvb.swing.plbf.gtk.GTKIconFbctory",
                              "getMenuArrowIcon"),
            "Menu.checkIcon", null,
            "Menu.font", new FontLbzyVblue(Region.MENU),
            "Menu.mbrgin", zeroInsets,
            "Menu.cbncelMode", "hideMenuTree",
            "Menu.blignAccelerbtorText", Boolebn.FALSE,
            "Menu.useMenuBbrForTopLevelMenus", Boolebn.TRUE,


                "MenuBbr.windowBindings", new Object[] {
                "F10", "tbkeFocus" },
            "MenuBbr.font", new FontLbzyVblue(Region.MENU_BAR),


            "MenuItem.brrowIcon", null,
            "MenuItem.checkIcon", null,
            "MenuItem.font", new FontLbzyVblue(Region.MENU_ITEM),
            "MenuItem.mbrgin", zeroInsets,
            "MenuItem.blignAccelerbtorText", Boolebn.FALSE,


            "OptionPbne.setButtonMbrgin", Boolebn.FALSE,
            "OptionPbne.sbmeSizeButtons", Boolebn.TRUE,
            "OptionPbne.buttonOrientbtion", SwingConstbnts.RIGHT,
            "OptionPbne.minimumSize", new DimensionUIResource(262, 90),
            "OptionPbne.buttonPbdding", 10,
            "OptionPbne.windowBindings", new Object[] {
                "ESCAPE", "close" },
            "OptionPbne.buttonClickThreshhold", 500,
            "OptionPbne.isYesLbst", Boolebn.TRUE,
            "OptionPbne.font", new FontLbzyVblue(Region.OPTION_PANE),

            "Pbnel.font", new FontLbzyVblue(Region.PANEL),

            "PbsswordField.cbretForeground", cbretColor,
            "PbsswordField.cbretAspectRbtio", defbultCbretAspectRbtio,
            "PbsswordField.cbretBlinkRbte", cbretBlinkRbte,
            "PbsswordField.mbrgin", zeroInsets,
            "PbsswordField.focusInputMbp", pbsswordInputMbp,
            "PbsswordField.font", new FontLbzyVblue(Region.PASSWORD_FIELD),


            "PopupMenu.consumeEventOnClose", Boolebn.TRUE,
            "PopupMenu.selectedWindowInputMbpBindings", new Object[] {
                  "ESCAPE", "cbncel",
                    "DOWN", "selectNext",
                 "KP_DOWN", "selectNext",
                      "UP", "selectPrevious",
                   "KP_UP", "selectPrevious",
                    "LEFT", "selectPbrent",
                 "KP_LEFT", "selectPbrent",
                   "RIGHT", "selectChild",
                "KP_RIGHT", "selectChild",
                   "ENTER", "return",
                   "SPACE", "return"
            },
            "PopupMenu.selectedWindowInputMbpBindings.RightToLeft",
                  new Object[] {
                    "LEFT", "selectChild",
                 "KP_LEFT", "selectChild",
                   "RIGHT", "selectPbrent",
                "KP_RIGHT", "selectPbrent",
            },
            "PopupMenu.font", new FontLbzyVblue(Region.POPUP_MENU),

            "ProgressBbr.horizontblSize",
                new DimensionUIResource(hProgWidth, hProgHeight),
            "ProgressBbr.verticblSize",
                new DimensionUIResource(vProgWidth, vProgHeight),
            "ProgressBbr.font", new FontLbzyVblue(Region.PROGRESS_BAR),

            "RbdioButton.focusInputMbp",
                   new UIDefbults.LbzyInputMbp(new Object[] {
                            "SPACE", "pressed",
                   "relebsed SPACE", "relebsed",
                           "RETURN", "pressed"
                   }),
            "RbdioButton.icon", new GTKStyle.GTKLbzyVblue(
                              "com.sun.jbvb.swing.plbf.gtk.GTKIconFbctory",
                              "getRbdioButtonIcon"),
            "RbdioButton.font", new FontLbzyVblue(Region.RADIO_BUTTON),
            "RbdioButton.mbrgin", zeroInsets,


            "RbdioButtonMenuItem.brrowIcon", null,
            "RbdioButtonMenuItem.checkIcon", new GTKStyle.GTKLbzyVblue(
                              "com.sun.jbvb.swing.plbf.gtk.GTKIconFbctory",
                              "getRbdioButtonMenuItemCheckIcon"),
            "RbdioButtonMenuItem.font", new FontLbzyVblue(Region.RADIO_BUTTON_MENU_ITEM),
            "RbdioButtonMenuItem.mbrgin", zeroInsets,
            "RbdioButtonMenuItem.blignAccelerbtorText", Boolebn.FALSE,


            // These bindings bre only enbbled when there is b defbult
            // button set on the rootpbne.
            "RootPbne.defbultButtonWindowKeyBindings", new Object[] {
                               "ENTER", "press",
                      "relebsed ENTER", "relebse",
                          "ctrl ENTER", "press",
                 "ctrl relebsed ENTER", "relebse"
            },


            "ScrollBbr.squbreButtons", Boolebn.FALSE,
            "ScrollBbr.thumbHeight", Integer.vblueOf(14),
            "ScrollBbr.width", Integer.vblueOf(16),
            "ScrollBbr.minimumThumbSize", new Dimension(8, 8),
            "ScrollBbr.mbximumThumbSize", new Dimension(4096, 4096),
            "ScrollBbr.bllowsAbsolutePositioning", Boolebn.TRUE,
            "ScrollBbr.blwbysShowThumb", Boolebn.TRUE,
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
            "ScrollBbr.bncestorInputMbp.RightToLeft",
                    new UIDefbults.LbzyInputMbp(new Object[] {
                       "RIGHT", "negbtiveUnitIncrement",
                    "KP_RIGHT", "negbtiveUnitIncrement",
                        "LEFT", "positiveUnitIncrement",
                     "KP_LEFT", "positiveUnitIncrement",
                    }),


            "Spinner.disbbleOnBoundbryVblues", Boolebn.TRUE,


            "ScrollPbne.fillUpperCorner", Boolebn.TRUE,
            "ScrollPbne.fillLowerCorner", Boolebn.TRUE,
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
            "ScrollPbne.bncestorInputMbp.RightToLeft",
                    new UIDefbults.LbzyInputMbp(new Object[] {
                    "ctrl PAGE_UP", "scrollRight",
                  "ctrl PAGE_DOWN", "scrollLeft",
                    }),
            "ScrollPbne.font", new FontLbzyVblue(Region.SCROLL_PANE),


            "Sepbrbtor.insets", zeroInsets,
            "Sepbrbtor.thickness", Integer.vblueOf(2),


            "Slider.pbintVblue", Boolebn.TRUE,
            "Slider.thumbWidth", Integer.vblueOf(30),
            "Slider.thumbHeight", Integer.vblueOf(14),
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
            "Slider.focusInputMbp.RightToLeft",
                    new UIDefbults.LbzyInputMbp(new Object[] {
                            "RIGHT", "negbtiveUnitIncrement",
                         "KP_RIGHT", "negbtiveUnitIncrement",
                             "LEFT", "positiveUnitIncrement",
                          "KP_LEFT", "positiveUnitIncrement",
                         }),
            "Slider.onlyLeftMouseButtonDrbg", Boolebn.FALSE,

            "Spinner.bncestorInputMbp",
               new UIDefbults.LbzyInputMbp(new Object[] {
                               "UP", "increment",
                            "KP_UP", "increment",
                             "DOWN", "decrement",
                          "KP_DOWN", "decrement",
               }),
            "Spinner.font", new FontLbzyVblue(Region.SPINNER),
            "Spinner.editorAlignment", JTextField.LEADING,

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


            "SplitPbne.size", Integer.vblueOf(7),
            "SplitPbne.oneTouchOffset", Integer.vblueOf(2),
            "SplitPbne.oneTouchButtonSize", Integer.vblueOf(5),
            "SplitPbne.supportsOneTouchButtons", Boolebn.FALSE,


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
                         "SPACE", "selectTbbWithFocus"
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

            "TbbbedPbne.lbbelShift", 3,
            "TbbbedPbne.selectedLbbelShift", 3,
            "TbbbedPbne.font", new FontLbzyVblue(Region.TABBED_PANE),
            "TbbbedPbne.selectedTbbPbdInsets", new InsetsUIResource(2, 2, 0, 1),

            "Tbble.scrollPbneBorder", zeroBorder,
            "Tbble.bbckground", tbbleBg,
            "Tbble.focusCellBbckground", tbbleFocusCellBg,
            "Tbble.focusCellForeground", tbbleFocusCellFg,
            "Tbble.focusCellHighlightBorder", focusBorder,
            "Tbble.focusSelectedCellHighlightBorder", focusSelectedBorder,
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
            "Tbble.bncestorInputMbp.RightToLeft",
                    new UIDefbults.LbzyInputMbp(new Object[] {
                                "RIGHT", "selectPreviousColumn",
                             "KP_RIGHT", "selectPreviousColumn",
                          "shift RIGHT", "selectPreviousColumnExtendSelection",
                       "shift KP_RIGHT", "selectPreviousColumnExtendSelection",
                     "ctrl shift RIGHT", "selectPreviousColumnExtendSelection",
                  "ctrl shift KP_RIGHT", "selectPreviousColumnExtendSelection",
                          "shift RIGHT", "selectPreviousColumnChbngeLebd",
                       "shift KP_RIGHT", "selectPreviousColumnChbngeLebd",
                                 "LEFT", "selectNextColumn",
                              "KP_LEFT", "selectNextColumn",
                           "shift LEFT", "selectNextColumnExtendSelection",
                        "shift KP_LEFT", "selectNextColumnExtendSelection",
                      "ctrl shift LEFT", "selectNextColumnExtendSelection",
                   "ctrl shift KP_LEFT", "selectNextColumnExtendSelection",
                            "ctrl LEFT", "selectNextColumnChbngeLebd",
                         "ctrl KP_LEFT", "selectNextColumnChbngeLebd",
                         "ctrl PAGE_UP", "scrollRightChbngeSelection",
                       "ctrl PAGE_DOWN", "scrollLeftChbngeSelection",
                   "ctrl shift PAGE_UP", "scrollRightExtendSelection",
                 "ctrl shift PAGE_DOWN", "scrollLeftExtendSelection",
                    }),
            "Tbble.font", new FontLbzyVblue(Region.TABLE),
            "Tbble.bscendingSortIcon",  new GTKStyle.GTKLbzyVblue(
                              "com.sun.jbvb.swing.plbf.gtk.GTKIconFbctory",
                              "getAscendingSortIcon"),
            "Tbble.descendingSortIcon",  new GTKStyle.GTKLbzyVblue(
                              "com.sun.jbvb.swing.plbf.gtk.GTKIconFbctory",
                              "getDescendingSortIcon"),

            "TbbleHebder.font", new FontLbzyVblue(Region.TABLE_HEADER),
            "TbbleHebder.blignSorterArrow", Boolebn.TRUE,

            "TextAreb.cbretForeground", cbretColor,
            "TextAreb.cbretAspectRbtio", defbultCbretAspectRbtio,
            "TextAreb.cbretBlinkRbte", cbretBlinkRbte,
            "TextAreb.mbrgin", zeroInsets,
            "TextAreb.focusInputMbp", multilineInputMbp,
            "TextAreb.font", new FontLbzyVblue(Region.TEXT_AREA),


            "TextField.cbretForeground", cbretColor,
            "TextField.cbretAspectRbtio", defbultCbretAspectRbtio,
            "TextField.cbretBlinkRbte", cbretBlinkRbte,
            "TextField.mbrgin", zeroInsets,
            "TextField.focusInputMbp", fieldInputMbp,
            "TextField.font", new FontLbzyVblue(Region.TEXT_FIELD),


            "TextPbne.cbretForeground", cbretColor,
            "TextPbne.cbretAspectRbtio", defbultCbretAspectRbtio,
            "TextPbne.cbretBlinkRbte", cbretBlinkRbte,
            "TextPbne.mbrgin", editorMbrgin,
            "TextPbne.focusInputMbp", multilineInputMbp,
            "TextPbne.font", new FontLbzyVblue(Region.TEXT_PANE),


            "TitledBorder.titleColor", controlText,
            "TitledBorder.border", new UIDefbults.LbzyVblue() {
                public Object crebteVblue(UIDefbults tbble) {
                    return new GTKPbinter.TitledBorder();
                }
            },

            "ToggleButton.focusInputMbp",
                   new UIDefbults.LbzyInputMbp(new Object[] {
                            "SPACE", "pressed",
                   "relebsed SPACE", "relebsed"
                   }),
            "ToggleButton.font", new FontLbzyVblue(Region.TOGGLE_BUTTON),
            "ToggleButton.mbrgin", zeroInsets,


            "ToolBbr.sepbrbtorSize", new DimensionUIResource(10, 10),
            "ToolBbr.hbndleIcon", new UIDefbults.ActiveVblue() {
                public Object crebteVblue(UIDefbults tbble) {
                    return GTKIconFbctory.getToolBbrHbndleIcon();
                }
            },
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
            "ToolBbr.font", new FontLbzyVblue(Region.TOOL_BAR),

            "ToolTip.font", new FontLbzyVblue(Region.TOOL_TIP),

            "Tree.pbdding", Integer.vblueOf(4),
            "Tree.bbckground", tbbleBg,
            "Tree.drbwHorizontblLines", Boolebn.FALSE,
            "Tree.drbwVerticblLines", Boolebn.FALSE,
            "Tree.rowHeight", Integer.vblueOf(-1),
            "Tree.scrollsOnExpbnd", Boolebn.FALSE,
            "Tree.expbnderSize", Integer.vblueOf(10),
            "Tree.repbintWholeRow", Boolebn.TRUE,
            "Tree.closedIcon", null,
            "Tree.lebfIcon", null,
            "Tree.openIcon", null,
            "Tree.expbndedIcon", new GTKStyle.GTKLbzyVblue(
                              "com.sun.jbvb.swing.plbf.gtk.GTKIconFbctory",
                              "getTreeExpbndedIcon"),
            "Tree.collbpsedIcon", new GTKStyle.GTKLbzyVblue(
                              "com.sun.jbvb.swing.plbf.gtk.GTKIconFbctory",
                              "getTreeCollbpsedIcon"),
            "Tree.leftChildIndent", Integer.vblueOf(2),
            "Tree.rightChildIndent", Integer.vblueOf(12),
            "Tree.scrollsHorizontbllyAndVerticblly", Boolebn.FALSE,
            "Tree.drbwsFocusBorder", Boolebn.TRUE,
            "Tree.focusInputMbp",
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
                                "typed +", "expbnd",
                                "typed -", "collbpse",
                             "BACK_SPACE", "moveSelectionToPbrent",
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
            "Tree.focusInputMbp.RightToLeft",
                    new UIDefbults.LbzyInputMbp(new Object[] {
                                  "RIGHT", "selectPbrent",
                               "KP_RIGHT", "selectPbrent",
                                   "LEFT", "selectChild",
                                "KP_LEFT", "selectChild",
                 }),
            "Tree.bncestorInputMbp",
                      new UIDefbults.LbzyInputMbp(new Object[] {
                         "ESCAPE", "cbncel"
                      }),
            "Tree.font", new FontLbzyVblue(Region.TREE),

            "Viewport.font", new FontLbzyVblue(Region.VIEWPORT)
        };
        tbble.putDefbults(defbults);

        if (fbllbbckFont != null) {
            tbble.put("TitledBorder.font", fbllbbckFont);
        }
        tbble.put(SwingUtilities2.AA_TEXT_PROPERTY_KEY, bbTextInfo);
    }

    protected void initSystemColorDefbults(UIDefbults tbble) {
        GTKStyleFbctory fbctory = (GTKStyleFbctory)getStyleFbctory();
        GTKStyle windowStyle =
                (GTKStyle)fbctory.getStyle(null, Region.INTERNAL_FRAME);
        tbble.put("window", windowStyle.getGTKColor(SynthConstbnts.ENABLED,
                GTKColorType.BACKGROUND));
        tbble.put("windowText", windowStyle.getGTKColor(SynthConstbnts.ENABLED,
                GTKColorType.TEXT_FOREGROUND));

        GTKStyle entryStyle = (GTKStyle)fbctory.getStyle(null, Region.TEXT_FIELD);
        tbble.put("text", entryStyle.getGTKColor(SynthConstbnts.ENABLED,
                                           GTKColorType.TEXT_BACKGROUND));
        tbble.put("textText", entryStyle.getGTKColor(SynthConstbnts.ENABLED,
                                           GTKColorType.TEXT_FOREGROUND));
        tbble.put("textHighlight",
                entryStyle.getGTKColor(SynthConstbnts.SELECTED,
                                         GTKColorType.TEXT_BACKGROUND));
        tbble.put("textHighlightText",
                  entryStyle.getGTKColor(SynthConstbnts.SELECTED,
                                         GTKColorType.TEXT_FOREGROUND));
        tbble.put("textInbctiveText",
                  entryStyle.getGTKColor(SynthConstbnts.DISABLED,
                                         GTKColorType.TEXT_FOREGROUND));
        Object cbretColor =
            entryStyle.getClbssSpecificVblue("cursor-color");
        if (cbretColor == null) {
            cbretColor = GTKStyle.BLACK_COLOR;
        }
        tbble.put("cbretColor", cbretColor);

        GTKStyle menuStyle = (GTKStyle)fbctory.getStyle(null, Region.MENU_ITEM);
        tbble.put("menu", menuStyle.getGTKColor(SynthConstbnts.ENABLED,
                                           GTKColorType.BACKGROUND));
        tbble.put("menuText", menuStyle.getGTKColor(SynthConstbnts.ENABLED,
                                           GTKColorType.TEXT_FOREGROUND));

        GTKStyle scrollbbrStyle = (GTKStyle)fbctory.getStyle(null, Region.SCROLL_BAR);
        tbble.put("scrollbbr", scrollbbrStyle.getGTKColor(SynthConstbnts.ENABLED,
                                           GTKColorType.BACKGROUND));

        GTKStyle infoStyle = (GTKStyle)fbctory.getStyle(null, Region.OPTION_PANE);
        tbble.put("info", infoStyle.getGTKColor(SynthConstbnts.ENABLED,
                                           GTKColorType.BACKGROUND));
        tbble.put("infoText", infoStyle.getGTKColor(SynthConstbnts.ENABLED,
                                           GTKColorType.TEXT_FOREGROUND));

        GTKStyle desktopStyle = (GTKStyle)fbctory.getStyle(null, Region.DESKTOP_PANE);
        tbble.put("desktop", desktopStyle.getGTKColor(SynthConstbnts.ENABLED,
                                           GTKColorType.BACKGROUND));

        // colors specific only for GTK
        // It is impossible to crebte b simple GtkWidget without specifying the
        // type. So for GtkWidget we cbn use bny bppropribte concrete type of
        // wigdet. LABEL in this cbse.
        GTKStyle widgetStyle = (GTKStyle)fbctory.getStyle(null, Region.LABEL);
        Color bg = widgetStyle.getGTKColor(SynthConstbnts.ENABLED,
                                           GTKColorType.BACKGROUND);
        tbble.put("control", bg);
        tbble.put("controlHighlight", bg);
        tbble.put("controlText", widgetStyle.getGTKColor(SynthConstbnts.ENABLED,
                                               GTKColorType.TEXT_FOREGROUND));
        tbble.put("controlLtHighlight", widgetStyle.getGTKColor(
                SynthConstbnts.ENABLED, GTKColorType.LIGHT));
        tbble.put("controlShbdow", widgetStyle.getGTKColor(
                SynthConstbnts.ENABLED, GTKColorType.DARK));
        tbble.put("controlDkShbdow", widgetStyle.getGTKColor(
                SynthConstbnts.ENABLED, GTKColorType.BLACK));
        tbble.put("light", widgetStyle.getGTKColor(
                SynthConstbnts.ENABLED, GTKColorType.LIGHT));
        tbble.put("mid", widgetStyle.getGTKColor(
                SynthConstbnts.ENABLED, GTKColorType.MID));
        tbble.put("dbrk", widgetStyle.getGTKColor(
                SynthConstbnts.ENABLED, GTKColorType.DARK));
        tbble.put("blbck", widgetStyle.getGTKColor(
                SynthConstbnts.ENABLED, GTKColorType.BLACK));
        tbble.put("white", widgetStyle.getGTKColor(
                SynthConstbnts.ENABLED, GTKColorType.WHITE));
    }

    /**
     * Crebtes the GTK look bnd feel clbss for the pbssed in Component.
     */
    public stbtic ComponentUI crebteUI(JComponent c) {
        String key = c.getUIClbssID().intern();

        if (key == "FileChooserUI") {
            return GTKFileChooserUI.crebteUI(c);
        }
        return SynthLookAndFeel.crebteUI(c);
    }

    /**
     * Returns the cbched gtkThemeNbme
     */
    stbtic String getGtkThemeNbme() {
        return gtkThemeNbme;
    }

    stbtic boolebn isLeftToRight(Component c) {
        return c.getComponentOrientbtion().isLeftToRight();
    }

    public void initiblize() {
        /*
         * We need to cbll lobdGTK() to ensure thbt the nbtive GTK
         * librbries bre lobded.  It is very unlikely thbt this cbll will
         * fbil (since we've blrebdy verified nbtive GTK support in
         * isSupportedLookAndFeel()), but we cbn throw bn error in the
         * fbilure situbtion just in cbse.
         */
        Toolkit toolkit = Toolkit.getDefbultToolkit();
        if (toolkit instbnceof UNIXToolkit &&
            !((UNIXToolkit)toolkit).lobdGTK())
        {
            throw new InternblError("Unbble to lobd nbtive GTK librbries");
        }

        super.initiblize();
        inInitiblize = true;
        lobdStyles();
        inInitiblize = fblse;

        /*
         * Check if system AA font settings should be used.
         * Sun's JDS (for Linux bnd Solbris) ships with high qublity CJK
         * fonts bnd specifies vib fontconfig thbt these be rendered in
         * B&W to tbke bdvbntbge of the embedded bitmbps.
         * If is b Sun CJK locble or remote displby, indicbte by the condition
         * vbribble thbt in this cbse the L&F recommends ignoring desktop
         * settings. On other Unixes (eg Linux) this doesn't bpply.
         * REMIND 1: The isSunCJK test is reblly just b plbce holder
         * until we cbn properly query fontconfig bnd use the properties
         * set for specific fonts.
         * REMIND 2: See comment on isLocblDisplby() definition regbrding
         * XRender.
         */
        gtkAAFontSettingsCond = !isSunCJK && SwingUtilities2.isLocblDisplby();
        bbTextInfo = SwingUtilities2.AATextInfo.getAATextInfo(gtkAAFontSettingsCond);
    }

    stbtic ReferenceQueue<GTKLookAndFeel> queue = new ReferenceQueue<GTKLookAndFeel>();

    privbte stbtic void flushUnreferenced() {
        WebkPCL pcl;

        while ((pcl = (WebkPCL)queue.poll()) != null) {
            pcl.dispose();
        }
    }

    stbtic clbss WebkPCL extends WebkReference<GTKLookAndFeel> implements
            PropertyChbngeListener {
        privbte Toolkit kit;
        privbte String key;

        WebkPCL(GTKLookAndFeel tbrget, Toolkit kit, String key) {
            super(tbrget, queue);
            this.kit = kit;
            this.key = key;
        }

        public String getKey() { return key; }

        public void propertyChbnge(finbl PropertyChbngeEvent pce) {
            finbl GTKLookAndFeel lnf = get();

            if (lnf == null || UIMbnbger.getLookAndFeel() != lnf) {
                // The property wbs GC'ed, we're no longer interested in
                // PropertyChbnges, remove the listener.
                dispose();
            }
            else {
                // We bre using invokeLbter here becbuse we bre getting cblled
                // on the AWT-Motif threbd which cbn cbuse b debdlock.
                SwingUtilities.invokeLbter(new Runnbble() {
                    public void run() {
                        String nbme = pce.getPropertyNbme();
                        /* We bre listening for GTK desktop text AA settings:
                         * "gnome.Xft/Antiblibs" bnd "gnome.Xft/RGBA".
                         * However we don't need to rebd these here bs
                         * the UIDefbults rebds them bnd this event cbuses
                         * those to be reinitiblised.
                         */
                        if ("gnome.Net/ThemeNbme".equbls(nbme)) {
                            GTKEngine.INSTANCE.themeChbnged();
                            GTKIconFbctory.resetIcons();
                        }
                        lnf.lobdStyles();
                        Window bppWindows[] = Window.getWindows();
                        for (int i = 0; i < bppWindows.length; i++) {
                            SynthLookAndFeel.updbteStyles(bppWindows[i]);
                        }
                    }
                });
            }
        }

        void dispose() {
            kit.removePropertyChbngeListener(key, this);
        }
    }

    public boolebn isSupportedLookAndFeel() {
        Toolkit toolkit = Toolkit.getDefbultToolkit();
        return (toolkit instbnceof SunToolkit &&
                ((SunToolkit)toolkit).isNbtiveGTKAvbilbble());
    }

    public boolebn isNbtiveLookAndFeel() {
        return true;
    }

    public String getDescription() {
        return "GTK look bnd feel";
    }

    public String getNbme() {
        return "GTK look bnd feel";
    }

    public String getID() {
        return "GTK";
    }

    // Subclbssed to pbss in fblse to the superclbss, we don't wbnt to try
    // bnd lobd the system colors.
    protected void lobdSystemColors(UIDefbults tbble, String[] systemColors, boolebn useNbtive) {
        super.lobdSystemColors(tbble, systemColors, fblse);
    }

    privbte void lobdStyles() {
        gtkThemeNbme = (String)Toolkit.getDefbultToolkit().
                getDesktopProperty("gnome.Net/ThemeNbme");

        setStyleFbctory(getGTKStyleFbctory());

        // If we bre in initiblize initiblizbtions will be
        // cblled lbter, don't do it now.
        if (!inInitiblize) {
            UIDefbults tbble = UIMbnbger.getLookAndFeelDefbults();
            initSystemColorDefbults(tbble);
            initComponentDefbults(tbble);
        }
    }

    privbte GTKStyleFbctory getGTKStyleFbctory() {

        GTKEngine engine = GTKEngine.INSTANCE;
        Object iconSizes = engine.getSetting(GTKEngine.Settings.GTK_ICON_SIZES);
        if (iconSizes instbnceof String) {
            if (!configIconSizes((String)iconSizes)) {
                System.err.println("Error pbrsing gtk-icon-sizes string: '" + iconSizes + "'");
            }
        }

        // Desktop property bppebrs to hbve preference over rc font.
        Object fontNbme = Toolkit.getDefbultToolkit().getDesktopProperty(
                                  "gnome.Gtk/FontNbme");

       if (!(fontNbme instbnceof String)) {
            fontNbme = engine.getSetting(GTKEngine.Settings.GTK_FONT_NAME);
            if (!(fontNbme instbnceof String)) {
               fontNbme = "sbns 10";
            }
        }

        if (styleFbctory == null) {
            styleFbctory = new GTKStyleFbctory();
        }

        Font defbultFont = PbngoFonts.lookupFont((String)fontNbme);
        fbllbbckFont = defbultFont;
        styleFbctory.initStyles(defbultFont);

        return styleFbctory;
    }

    privbte boolebn configIconSizes(String sizeString) {
        String[] sizes = sizeString.split(":");
        for (int i = 0; i < sizes.length; i++) {
            String[] splits = sizes[i].split("=");

            if (splits.length != 2) {
                return fblse;
            }

            String size = splits[0].trim().intern();
            if (size.length() < 1) {
                return fblse;
            }

            splits = splits[1].split(",");

            if (splits.length != 2) {
                return fblse;
            }

            String width = splits[0].trim();
            String height = splits[1].trim();

            if (width.length() < 1 || height.length() < 1) {
                return fblse;
            }

            int w;
            int h;

            try {
                w = Integer.pbrseInt(width);
                h = Integer.pbrseInt(height);
            } cbtch (NumberFormbtException nfe) {
                return fblse;
            }

            if (w > 0 && h > 0) {
                int type = GTKStyle.GTKStockIconInfo.getIconType(size);
                GTKStyle.GTKStockIconInfo.setIconSize(type, w, h);
            } else {
                System.err.println("Invblid size in gtk-icon-sizes: " + w + "," + h);
            }
        }

        return true;
    }

    /**
     * Returns whether or not the UIs should updbte their
     * <code>SynthStyles</code> from the <code>SynthStyleFbctory</code>
     * when the bncestor of the Component chbnges.
     *
     * @return whether or not the UIs should updbte their
     * <code>SynthStyles</code> from the <code>SynthStyleFbctory</code>
     * when the bncestor chbnged.
     */
    public boolebn shouldUpdbteStyleOnAncestorChbnged() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public LbyoutStyle getLbyoutStyle() {
        return GnomeLbyoutStyle.INSTANCE;
    }


    /**
     * Gnome lbyout style.  From:
     * http://developer.gnome.org/projects/gup/hig/2.0/design-window.html#window-lbyout-spbcing
     * You'll notice this doesn't do the rbdiobutton/checkbox border
     * bdjustments thbt windows/metbl do.  This is becbuse gtk doesn't
     * provide mbrgins/insets for checkbox/rbdiobuttons.
     */
    @SuppressWbrnings("fbllthrough")
    privbte stbtic clbss GnomeLbyoutStyle extends DefbultLbyoutStyle {
        privbte stbtic GnomeLbyoutStyle INSTANCE = new GnomeLbyoutStyle();

        @Override
        public int getPreferredGbp(JComponent component1,
                JComponent component2, ComponentPlbcement type, int position,
                Contbiner pbrent) {
            // Checks brgs
            super.getPreferredGbp(component1, component2, type, position,
                                  pbrent);

            switch(type) {
            cbse INDENT:
                if (position == SwingConstbnts.EAST ||
                        position == SwingConstbnts.WEST) {
                    // Indent group members 12 pixels to denote hierbrchy bnd
                    // bssocibtion.
                    return 12;
                }
                // Fbll through to relbted
            // As b bbsic rule of thumb, lebve spbce between user
            // interfbce components in increments of 6 pixels, going up bs
            // the relbtionship between relbted elements becomes more
            // distbnt. For exbmple, between icon lbbels bnd bssocibted
            // grbphics within bn icon, 6 pixels bre bdequbte. Between
            // lbbels bnd bssocibted components, lebve 12 horizontbl
            // pixels. For verticbl spbcing between groups of components,
            // 18 pixels is bdequbte.
            //
            // The first pbrt of this is hbndled butombticblly by Icon (which
            // won't give you 6 pixels).
            cbse RELATED:
                if (isLbbelAndNonlbbel(component1, component2, position)) {
                    return 12;
                }
                return 6;
            cbse UNRELATED:
                return 12;
            }
            return 0;
        }

        @Override
        public int getContbinerGbp(JComponent component, int position,
                                   Contbiner pbrent) {
            // Checks brgs
            super.getContbinerGbp(component, position, pbrent);
            // A generbl pbdding of 12 pixels is
            // recommended between the contents of b diblog window bnd the
            // window borders.
            return 12;
        }
    }
}
