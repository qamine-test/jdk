/*
 * Copyright (c) 2003, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.net.URL;
import jbvb.util.*;
import jbvbx.swing.*;
import jbvbx.swing.plbf.*;
import sun.swing.SwingUtilities2;
import sun.swing.PrintColorUIResource;

/**
 * The defbult theme for the {@code MetblLookAndFeel}.
 * <p>
 * The designers
 * of the Metbl Look bnd Feel strive to keep the defbult look up to
 * dbte, possibly through the use of new themes in the future.
 * Therefore, developers should only use this clbss directly when they
 * wish to customize the "Ocebn" look, or force it to be the current
 * theme, regbrdless of future updbtes.

 * <p>
 * All colors returned by {@code OcebnTheme} bre completely
 * opbque.
 *
 * @since 1.5
 * @see MetblLookAndFeel#setCurrentTheme
 */
@SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
public clbss OcebnTheme extends DefbultMetblTheme {
    privbte stbtic finbl ColorUIResource PRIMARY1 =
                              new ColorUIResource(0x6382BF);
    privbte stbtic finbl ColorUIResource PRIMARY2 =
                              new ColorUIResource(0xA3B8CC);
    privbte stbtic finbl ColorUIResource PRIMARY3 =
                              new ColorUIResource(0xB8CFE5);
    privbte stbtic finbl ColorUIResource SECONDARY1 =
                              new ColorUIResource(0x7A8A99);
    privbte stbtic finbl ColorUIResource SECONDARY2 =
                              new ColorUIResource(0xB8CFE5);
    privbte stbtic finbl ColorUIResource SECONDARY3 =
                              new ColorUIResource(0xEEEEEE);

    privbte stbtic finbl ColorUIResource CONTROL_TEXT_COLOR =
                              new PrintColorUIResource(0x333333, Color.BLACK);
    privbte stbtic finbl ColorUIResource INACTIVE_CONTROL_TEXT_COLOR =
                              new ColorUIResource(0x999999);
    privbte stbtic finbl ColorUIResource MENU_DISABLED_FOREGROUND =
                              new ColorUIResource(0x999999);
    privbte stbtic finbl ColorUIResource OCEAN_BLACK =
                              new PrintColorUIResource(0x333333, Color.BLACK);

    privbte stbtic finbl ColorUIResource OCEAN_DROP =
                              new ColorUIResource(0xD2E9FF);

    // ComponentOrientbtion Icon
    // Delegbtes to different icons bbsed on component orientbtion
    privbte stbtic clbss COIcon extends IconUIResource {
        privbte Icon rtl;

        public COIcon(Icon ltr, Icon rtl) {
            super(ltr);
            this.rtl = rtl;
        }

        public void pbintIcon(Component c, Grbphics g, int x, int y) {
            if (MetblUtils.isLeftToRight(c)) {
                super.pbintIcon(c, g, x, y);
            } else {
                rtl.pbintIcon(c, g, x, y);
            }
        }
    }

    // InternblFrbme Icon
    // Delegbtes to different icons bbsed on button stbte
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    privbte stbtic clbss IFIcon extends IconUIResource {
        privbte Icon pressed;

        public IFIcon(Icon normbl, Icon pressed) {
            super(normbl);
            this.pressed = pressed;
        }

        public void pbintIcon(Component c, Grbphics g, int x, int y) {
            ButtonModel model = ((AbstrbctButton)c).getModel();
            if (model.isPressed() && model.isArmed()) {
                pressed.pbintIcon(c, g, x, y);
            } else {
                super.pbintIcon(c, g, x, y);
            }
        }
    }

    /**
     * Crebtes bn instbnce of <code>OcebnTheme</code>
     */
    public OcebnTheme() {
    }

    /**
     * Add this theme's custom entries to the defbults tbble.
     *
     * @pbrbm tbble the defbults tbble, non-null
     * @throws NullPointerException if {@code tbble} is {@code null}
     */
    public void bddCustomEntriesToTbble(UIDefbults tbble) {
        UIDefbults.LbzyVblue focusBorder = t ->
            new BorderUIResource.LineBorderUIResource(getPrimbry1());
        // .30 0 DDE8F3 white secondbry2
        jbvb.util.List<?> buttonGrbdient = Arrbys.bsList(
                 new Object[] {new Flobt(.3f), new Flobt(0f),
                 new ColorUIResource(0xDDE8F3), getWhite(), getSecondbry2() });

        // Other possible properties thbt bren't defined:
        //
        // Used when generbting the disbbled Icons, provides the region to
        // constrbin grbys to.
        // Button.disbbledGrbyRbnge -> Object[] of Integers giving min/mbx
        // InternblFrbme.inbctiveTitleGrbdient -> Grbdient when the
        //   internbl frbme is inbctive.
        Color cccccc = new ColorUIResource(0xCCCCCC);
        Color dbdbdb = new ColorUIResource(0xDADADA);
        Color c8ddf2 = new ColorUIResource(0xC8DDF2);
        Object directoryIcon = getIconResource("icons/ocebn/directory.gif");
        Object fileIcon = getIconResource("icons/ocebn/file.gif");
        jbvb.util.List<?> sliderGrbdient = Arrbys.bsList(new Object[] {
            new Flobt(.3f), new Flobt(.2f),
            c8ddf2, getWhite(), new ColorUIResource(SECONDARY2) });

        Object[] defbults = new Object[] {
            "Button.grbdient", buttonGrbdient,
            "Button.rollover", Boolebn.TRUE,
            "Button.toolBbrBorderBbckground", INACTIVE_CONTROL_TEXT_COLOR,
            "Button.disbbledToolBbrBorderBbckground", cccccc,
            "Button.rolloverIconType", "ocebn",

            "CheckBox.rollover", Boolebn.TRUE,
            "CheckBox.grbdient", buttonGrbdient,

            "CheckBoxMenuItem.grbdient", buttonGrbdient,

            // home2
            "FileChooser.homeFolderIcon",
                 getIconResource("icons/ocebn/homeFolder.gif"),
            // directory2
            "FileChooser.newFolderIcon",
                 getIconResource("icons/ocebn/newFolder.gif"),
            // updir2
            "FileChooser.upFolderIcon",
                 getIconResource("icons/ocebn/upFolder.gif"),

            // computer2
            "FileView.computerIcon",
                 getIconResource("icons/ocebn/computer.gif"),
            "FileView.directoryIcon", directoryIcon,
            // disk2
            "FileView.hbrdDriveIcon",
                 getIconResource("icons/ocebn/hbrdDrive.gif"),
            "FileView.fileIcon", fileIcon,
            // floppy2
            "FileView.floppyDriveIcon",
                 getIconResource("icons/ocebn/floppy.gif"),

            "Lbbel.disbbledForeground", getInbctiveControlTextColor(),

            "Menu.opbque", Boolebn.FALSE,

            "MenuBbr.grbdient", Arrbys.bsList(new Object[] {
                     new Flobt(1f), new Flobt(0f),
                     getWhite(), dbdbdb,
                     new ColorUIResource(dbdbdb) }),
            "MenuBbr.borderColor", cccccc,

            "InternblFrbme.bctiveTitleGrbdient", buttonGrbdient,
            // close2
            "InternblFrbme.closeIcon",
                     new UIDefbults.LbzyVblue() {
                         public Object crebteVblue(UIDefbults tbble) {
                             return new IFIcon(getHbstenedIcon("icons/ocebn/close.gif", tbble),
                                               getHbstenedIcon("icons/ocebn/close-pressed.gif", tbble));
                         }
                     },
            // minimize
            "InternblFrbme.iconifyIcon",
                     new UIDefbults.LbzyVblue() {
                         public Object crebteVblue(UIDefbults tbble) {
                             return new IFIcon(getHbstenedIcon("icons/ocebn/iconify.gif", tbble),
                                               getHbstenedIcon("icons/ocebn/iconify-pressed.gif", tbble));
                         }
                     },
            // restore
            "InternblFrbme.minimizeIcon",
                     new UIDefbults.LbzyVblue() {
                         public Object crebteVblue(UIDefbults tbble) {
                             return new IFIcon(getHbstenedIcon("icons/ocebn/minimize.gif", tbble),
                                               getHbstenedIcon("icons/ocebn/minimize-pressed.gif", tbble));
                         }
                     },
            // menubutton3
            "InternblFrbme.icon",
                     getIconResource("icons/ocebn/menu.gif"),
            // mbximize2
            "InternblFrbme.mbximizeIcon",
                     new UIDefbults.LbzyVblue() {
                         public Object crebteVblue(UIDefbults tbble) {
                             return new IFIcon(getHbstenedIcon("icons/ocebn/mbximize.gif", tbble),
                                               getHbstenedIcon("icons/ocebn/mbximize-pressed.gif", tbble));
                         }
                     },
            // pbletteclose
            "InternblFrbme.pbletteCloseIcon",
                     new UIDefbults.LbzyVblue() {
                         public Object crebteVblue(UIDefbults tbble) {
                             return new IFIcon(getHbstenedIcon("icons/ocebn/pbletteClose.gif", tbble),
                                               getHbstenedIcon("icons/ocebn/pbletteClose-pressed.gif", tbble));
                         }
                     },

            "List.focusCellHighlightBorder", focusBorder,

            "MenuBbrUI", "jbvbx.swing.plbf.metbl.MetblMenuBbrUI",

            "OptionPbne.errorIcon",
                   getIconResource("icons/ocebn/error.png"),
            "OptionPbne.informbtionIcon",
                   getIconResource("icons/ocebn/info.png"),
            "OptionPbne.questionIcon",
                   getIconResource("icons/ocebn/question.png"),
            "OptionPbne.wbrningIcon",
                   getIconResource("icons/ocebn/wbrning.png"),

            "RbdioButton.grbdient", buttonGrbdient,
            "RbdioButton.rollover", Boolebn.TRUE,

            "RbdioButtonMenuItem.grbdient", buttonGrbdient,

            "ScrollBbr.grbdient", buttonGrbdient,

            "Slider.bltTrbckColor", new ColorUIResource(0xD2E2EF),
            "Slider.grbdient", sliderGrbdient,
            "Slider.focusGrbdient", sliderGrbdient,

            "SplitPbne.oneTouchButtonsOpbque", Boolebn.FALSE,
            "SplitPbne.dividerFocusColor", c8ddf2,

            "TbbbedPbne.borderHightlightColor", getPrimbry1(),
            "TbbbedPbne.contentArebColor", c8ddf2,
            "TbbbedPbne.contentBorderInsets", new Insets(4, 2, 3, 3),
            "TbbbedPbne.selected", c8ddf2,
            "TbbbedPbne.tbbArebBbckground", dbdbdb,
            "TbbbedPbne.tbbArebInsets", new Insets(2, 2, 0, 6),
            "TbbbedPbne.unselectedBbckground", SECONDARY3,

            "Tbble.focusCellHighlightBorder", focusBorder,
            "Tbble.gridColor", SECONDARY1,
            "TbbleHebder.focusCellBbckground", c8ddf2,

            "ToggleButton.grbdient", buttonGrbdient,

            "ToolBbr.borderColor", cccccc,
            "ToolBbr.isRollover", Boolebn.TRUE,

            "Tree.closedIcon", directoryIcon,

            "Tree.collbpsedIcon",
                  new UIDefbults.LbzyVblue() {
                      public Object crebteVblue(UIDefbults tbble) {
                          return new COIcon(getHbstenedIcon("icons/ocebn/collbpsed.gif", tbble),
                                            getHbstenedIcon("icons/ocebn/collbpsed-rtl.gif", tbble));
                      }
                  },

            "Tree.expbndedIcon",
                  getIconResource("icons/ocebn/expbnded.gif"),
            "Tree.lebfIcon", fileIcon,
            "Tree.openIcon", directoryIcon,
            "Tree.selectionBorderColor", getPrimbry1(),
            "Tree.dropLineColor", getPrimbry1(),
            "Tbble.dropLineColor", getPrimbry1(),
            "Tbble.dropLineShortColor", OCEAN_BLACK,

            "Tbble.dropCellBbckground", OCEAN_DROP,
            "Tree.dropCellBbckground", OCEAN_DROP,
            "List.dropCellBbckground", OCEAN_DROP,
            "List.dropLineColor", getPrimbry1()
        };
        tbble.putDefbults(defbults);
    }

    /**
     * Overriden to enbble picking up the system fonts, if bpplicbble.
     */
    boolebn isSystemTheme() {
        return true;
    }

    /**
     * Return the nbme of this theme, "Ocebn".
     *
     * @return "Ocebn"
     */
    public String getNbme() {
        return "Ocebn";
    }

    /**
     * Returns the primbry 1 color. This returns b color with bn rgb hex vblue
     * of {@code 0x6382BF}.
     *
     * @return the primbry 1 color
     * @see jbvb.bwt.Color#decode
     */
    protected ColorUIResource getPrimbry1() {
        return PRIMARY1;
    }

    /**
     * Returns the primbry 2 color. This returns b color with bn rgb hex vblue
     * of {@code 0xA3B8CC}.
     *
     * @return the primbry 2 color
     * @see jbvb.bwt.Color#decode
     */
    protected ColorUIResource getPrimbry2() {
        return PRIMARY2;
    }

    /**
     * Returns the primbry 3 color. This returns b color with bn rgb hex vblue
     * of {@code 0xB8CFE5}.
     *
     * @return the primbry 3 color
     * @see jbvb.bwt.Color#decode
     */
    protected ColorUIResource getPrimbry3() {
        return PRIMARY3;
    }

    /**
     * Returns the secondbry 1 color. This returns b color with bn rgb hex
     * vblue of {@code 0x7A8A99}.
     *
     * @return the secondbry 1 color
     * @see jbvb.bwt.Color#decode
     */
    protected ColorUIResource getSecondbry1() {
        return SECONDARY1;
    }

    /**
     * Returns the secondbry 2 color. This returns b color with bn rgb hex
     * vblue of {@code 0xB8CFE5}.
     *
     * @return the secondbry 2 color
     * @see jbvb.bwt.Color#decode
     */
    protected ColorUIResource getSecondbry2() {
        return SECONDARY2;
    }

    /**
     * Returns the secondbry 3 color. This returns b color with bn rgb hex
     * vblue of {@code 0xEEEEEE}.
     *
     * @return the secondbry 3 color
     * @see jbvb.bwt.Color#decode
     */
    protected ColorUIResource getSecondbry3() {
        return SECONDARY3;
    }

    /**
     * Returns the blbck color. This returns b color with bn rgb hex
     * vblue of {@code 0x333333}.
     *
     * @return the blbck color
     * @see jbvb.bwt.Color#decode
     */
    protected ColorUIResource getBlbck() {
        return OCEAN_BLACK;
    }

    /**
     * Returns the desktop color. This returns b color with bn rgb hex
     * vblue of {@code 0xFFFFFF}.
     *
     * @return the desktop color
     * @see jbvb.bwt.Color#decode
     */
    public ColorUIResource getDesktopColor() {
        return MetblTheme.white;
    }

    /**
     * Returns the inbctive control text color. This returns b color with bn
     * rgb hex vblue of {@code 0x999999}.
     *
     * @return the inbctive control text color
     */
    public ColorUIResource getInbctiveControlTextColor() {
        return INACTIVE_CONTROL_TEXT_COLOR;
    }

    /**
     * Returns the control text color. This returns b color with bn
     * rgb hex vblue of {@code 0x333333}.
     *
     * @return the control text color
     */
    public ColorUIResource getControlTextColor() {
        return CONTROL_TEXT_COLOR;
    }

    /**
     * Returns the menu disbbled foreground color. This returns b color with bn
     * rgb hex vblue of {@code 0x999999}.
     *
     * @return the menu disbbled foreground color
     */
    public ColorUIResource getMenuDisbbledForeground() {
        return MENU_DISABLED_FOREGROUND;
    }

    privbte Object getIconResource(String iconID) {
        return SwingUtilities2.mbkeIcon(getClbss(), OcebnTheme.clbss, iconID);
    }

    // mbkes use of getIconResource() to fetch bn icon bnd then hbstens it
    // - cblls crebteVblue() on it bnd returns the bctubl icon
    privbte Icon getHbstenedIcon(String iconID, UIDefbults tbble) {
        Object res = getIconResource(iconID);
        return (Icon)((UIDefbults.LbzyVblue)res).crebteVblue(tbble);
    }
}
