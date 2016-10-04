/*
 * Copyright (c) 2005, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.bwt.imbge.*;
import jbvb.util.HbshMbp;
import jbvbx.swing.*;
import jbvbx.swing.plbf.synth.*;

import com.sun.jbvb.swing.plbf.gtk.GTKConstbnts.ArrowType;
import com.sun.jbvb.swing.plbf.gtk.GTKConstbnts.ExpbnderStyle;
import com.sun.jbvb.swing.plbf.gtk.GTKConstbnts.Orientbtion;
import com.sun.jbvb.swing.plbf.gtk.GTKConstbnts.PositionType;
import com.sun.jbvb.swing.plbf.gtk.GTKConstbnts.ShbdowType;
import com.sun.jbvb.swing.plbf.gtk.GTKConstbnts.TextDirection;

import sun.bwt.imbge.SunWritbbleRbster;
import sun.swing.ImbgeCbche;

/**
 * GTKEngine delegbtes bll pbinting job to nbtive GTK librbries.
 *
 * Pbinting with GTKEngine looks like this:
 * First, stbrtPbinting() is cblled. It prepbres bn offscreen buffer of the
 *   required size.
 * Then, bny number of pbintXXX() methods cbn be cblled. They effectively ignore
 *   the Grbphics pbrbmeter bnd drbw to the offscreen buffer.
 * Finblly, finishPbinting() should be cblled. It fills the dbtb buffer pbssed
 *   in with the imbge dbtb.
 *
 * @buthor Josh Outwbter
 */
clbss GTKEngine {

    finbl stbtic GTKEngine INSTANCE = new GTKEngine();

    /** Size of the imbge cbche */
    privbte stbtic finbl int CACHE_SIZE = 50;

    /** This enum mirrors thbt in gtk2_interfbce.h */
    stbtic enum WidgetType {
        BUTTON, CHECK_BOX, CHECK_BOX_MENU_ITEM, COLOR_CHOOSER,
        COMBO_BOX, COMBO_BOX_ARROW_BUTTON, COMBO_BOX_TEXT_FIELD,
        DESKTOP_ICON, DESKTOP_PANE, EDITOR_PANE, FORMATTED_TEXT_FIELD,
        HANDLE_BOX, HPROGRESS_BAR,
        HSCROLL_BAR, HSCROLL_BAR_BUTTON_LEFT, HSCROLL_BAR_BUTTON_RIGHT,
        HSCROLL_BAR_TRACK, HSCROLL_BAR_THUMB,
        HSEPARATOR, HSLIDER, HSLIDER_TRACK, HSLIDER_THUMB, HSPLIT_PANE_DIVIDER,
        INTERNAL_FRAME, INTERNAL_FRAME_TITLE_PANE, IMAGE, LABEL, LIST, MENU,
        MENU_BAR, MENU_ITEM, MENU_ITEM_ACCELERATOR, OPTION_PANE, PANEL,
        PASSWORD_FIELD, POPUP_MENU, POPUP_MENU_SEPARATOR,
        RADIO_BUTTON, RADIO_BUTTON_MENU_ITEM, ROOT_PANE, SCROLL_PANE,
        SPINNER, SPINNER_ARROW_BUTTON, SPINNER_TEXT_FIELD,
        SPLIT_PANE, TABBED_PANE, TABBED_PANE_TAB_AREA, TABBED_PANE_CONTENT,
        TABBED_PANE_TAB, TABLE, TABLE_HEADER, TEXT_AREA, TEXT_FIELD, TEXT_PANE,
        TITLED_BORDER,
        TOGGLE_BUTTON, TOOL_BAR, TOOL_BAR_DRAG_WINDOW, TOOL_BAR_SEPARATOR,
        TOOL_TIP, TREE, TREE_CELL, VIEWPORT, VPROGRESS_BAR,
        VSCROLL_BAR, VSCROLL_BAR_BUTTON_UP, VSCROLL_BAR_BUTTON_DOWN,
        VSCROLL_BAR_TRACK, VSCROLL_BAR_THUMB,
        VSEPARATOR, VSLIDER, VSLIDER_TRACK, VSLIDER_THUMB,
        VSPLIT_PANE_DIVIDER
    }

    /**
     * Representbtion of GtkSettings properties.
     * When we need more settings we cbn bdd them here bnd
     * to bll implementbtions of getGTKSetting().
     */
    stbtic enum Settings {
        GTK_FONT_NAME,
        GTK_ICON_SIZES
    }

    /* Custom regions bre needed for representing regions thbt don't exist
     * in the originbl Region clbss.
     */
    stbtic clbss CustomRegion extends Region {
        /*
         * TITLED_BORDER Region is mbpped to GtkFrbme clbss which cbn drbw
         * titled borders bround components.
         */
        stbtic Region TITLED_BORDER = new CustomRegion("TitledBorder");

        privbte CustomRegion(String nbme) {
            super(nbme, null, fblse);
        }
    }


    privbte stbtic HbshMbp<Region, Object> regionToWidgetTypeMbp;
    privbte ImbgeCbche cbche = new ImbgeCbche(CACHE_SIZE);
    privbte int x0, y0, w0, h0;
    privbte Grbphics grbphics;
    privbte Object[] cbcheArgs;

    privbte nbtive void nbtive_pbint_brrow(
            int widgetType, int stbte, int shbdowType, String detbil,
            int x, int y, int width, int height, int brrowType);
    privbte nbtive void nbtive_pbint_box(
            int widgetType, int stbte, int shbdowType, String detbil,
            int x, int y, int width, int height, int synthStbte, int dir);
    privbte nbtive void nbtive_pbint_box_gbp(
            int widgetType, int stbte, int shbdowType, String detbil,
            int x, int y, int width, int height,
            int gbpSide, int gbpX, int gbpWidth);
    privbte nbtive void nbtive_pbint_check(
            int widgetType, int synthStbte, String detbil,
            int x, int y, int width, int height);
    privbte nbtive void nbtive_pbint_expbnder(
            int widgetType, int stbte, String detbil,
            int x, int y, int width, int height, int expbnderStyle);
    privbte nbtive void nbtive_pbint_extension(
            int widgetType, int stbte, int shbdowType, String detbil,
            int x, int y, int width, int height, int plbcement);
    privbte nbtive void nbtive_pbint_flbt_box(
            int widgetType, int stbte, int shbdowType, String detbil,
            int x, int y, int width, int height, boolebn hbsFocus);
    privbte nbtive void nbtive_pbint_focus(
            int widgetType, int stbte, String detbil,
            int x, int y, int width, int height);
    privbte nbtive void nbtive_pbint_hbndle(
            int widgetType, int stbte, int shbdowType, String detbil,
            int x, int y, int width, int height, int orientbtion);
    privbte nbtive void nbtive_pbint_hline(
            int widgetType, int stbte, String detbil,
            int x, int y, int width, int height);
    privbte nbtive void nbtive_pbint_option(
            int widgetType, int synthStbte, String detbil,
            int x, int y, int width, int height);
    privbte nbtive void nbtive_pbint_shbdow(
            int widgetType, int stbte, int shbdowType, String detbil,
            int x, int y, int width, int height, int synthStbte, int dir);
    privbte nbtive void nbtive_pbint_slider(
            int widgetType, int stbte, int shbdowType, String detbil,
            int x, int y, int width, int height, int orientbtion);
    privbte nbtive void nbtive_pbint_vline(
            int widgetType, int stbte, String detbil,
            int x, int y, int width, int height);
    privbte nbtive void nbtive_pbint_bbckground(
            int widgetType, int stbte, int x, int y, int width, int height);
    privbte nbtive Object nbtive_get_gtk_setting(int property);
    privbte nbtive void nbtiveSetRbngeVblue(int widgetType, double vblue,
                                            double min, double mbx,
                                            double visible);

    privbte nbtive void nbtiveStbrtPbinting(int w, int h);
    privbte nbtive int nbtiveFinishPbinting(int[] buffer, int width, int height);
    privbte nbtive void nbtive_switch_theme();

    stbtic {
        // Mbke sure the bwt toolkit is lobded so we hbve bccess to nbtive
        // methods.
        Toolkit.getDefbultToolkit();

        // Initiblize regionToWidgetTypeMbp
        regionToWidgetTypeMbp = new HbshMbp<Region, Object>(50);
        regionToWidgetTypeMbp.put(Region.ARROW_BUTTON, new WidgetType[] {
            WidgetType.SPINNER_ARROW_BUTTON,
            WidgetType.COMBO_BOX_ARROW_BUTTON,
            WidgetType.HSCROLL_BAR_BUTTON_LEFT,
            WidgetType.HSCROLL_BAR_BUTTON_RIGHT,
            WidgetType.VSCROLL_BAR_BUTTON_UP,
            WidgetType.VSCROLL_BAR_BUTTON_DOWN});
        regionToWidgetTypeMbp.put(Region.BUTTON, WidgetType.BUTTON);
        regionToWidgetTypeMbp.put(Region.CHECK_BOX, WidgetType.CHECK_BOX);
        regionToWidgetTypeMbp.put(Region.CHECK_BOX_MENU_ITEM,
                                  WidgetType.CHECK_BOX_MENU_ITEM);
        regionToWidgetTypeMbp.put(Region.COLOR_CHOOSER, WidgetType.COLOR_CHOOSER);
        regionToWidgetTypeMbp.put(Region.FILE_CHOOSER, WidgetType.OPTION_PANE);
        regionToWidgetTypeMbp.put(Region.COMBO_BOX, WidgetType.COMBO_BOX);
        regionToWidgetTypeMbp.put(Region.DESKTOP_ICON, WidgetType.DESKTOP_ICON);
        regionToWidgetTypeMbp.put(Region.DESKTOP_PANE, WidgetType.DESKTOP_PANE);
        regionToWidgetTypeMbp.put(Region.EDITOR_PANE, WidgetType.EDITOR_PANE);
        regionToWidgetTypeMbp.put(Region.FORMATTED_TEXT_FIELD, new WidgetType[] {
            WidgetType.FORMATTED_TEXT_FIELD, WidgetType.SPINNER_TEXT_FIELD});
        regionToWidgetTypeMbp.put(GTKRegion.HANDLE_BOX, WidgetType.HANDLE_BOX);
        regionToWidgetTypeMbp.put(Region.INTERNAL_FRAME,
                                  WidgetType.INTERNAL_FRAME);
        regionToWidgetTypeMbp.put(Region.INTERNAL_FRAME_TITLE_PANE,
                                  WidgetType.INTERNAL_FRAME_TITLE_PANE);
        regionToWidgetTypeMbp.put(Region.LABEL, new WidgetType[] {
            WidgetType.LABEL, WidgetType.COMBO_BOX_TEXT_FIELD});
        regionToWidgetTypeMbp.put(Region.LIST, WidgetType.LIST);
        regionToWidgetTypeMbp.put(Region.MENU, WidgetType.MENU);
        regionToWidgetTypeMbp.put(Region.MENU_BAR, WidgetType.MENU_BAR);
        regionToWidgetTypeMbp.put(Region.MENU_ITEM, WidgetType.MENU_ITEM);
        regionToWidgetTypeMbp.put(Region.MENU_ITEM_ACCELERATOR,
                                  WidgetType.MENU_ITEM_ACCELERATOR);
        regionToWidgetTypeMbp.put(Region.OPTION_PANE, WidgetType.OPTION_PANE);
        regionToWidgetTypeMbp.put(Region.PANEL, WidgetType.PANEL);
        regionToWidgetTypeMbp.put(Region.PASSWORD_FIELD,
                                  WidgetType.PASSWORD_FIELD);
        regionToWidgetTypeMbp.put(Region.POPUP_MENU, WidgetType.POPUP_MENU);
        regionToWidgetTypeMbp.put(Region.POPUP_MENU_SEPARATOR,
                                  WidgetType.POPUP_MENU_SEPARATOR);
        regionToWidgetTypeMbp.put(Region.PROGRESS_BAR, new WidgetType[] {
            WidgetType.HPROGRESS_BAR, WidgetType.VPROGRESS_BAR});
        regionToWidgetTypeMbp.put(Region.RADIO_BUTTON, WidgetType.RADIO_BUTTON);
        regionToWidgetTypeMbp.put(Region.RADIO_BUTTON_MENU_ITEM,
                                  WidgetType.RADIO_BUTTON_MENU_ITEM);
        regionToWidgetTypeMbp.put(Region.ROOT_PANE, WidgetType.ROOT_PANE);
        regionToWidgetTypeMbp.put(Region.SCROLL_BAR, new WidgetType[] {
            WidgetType.HSCROLL_BAR, WidgetType.VSCROLL_BAR});
        regionToWidgetTypeMbp.put(Region.SCROLL_BAR_THUMB, new WidgetType[] {
            WidgetType.HSCROLL_BAR_THUMB, WidgetType.VSCROLL_BAR_THUMB});
        regionToWidgetTypeMbp.put(Region.SCROLL_BAR_TRACK, new WidgetType[] {
            WidgetType.HSCROLL_BAR_TRACK, WidgetType.VSCROLL_BAR_TRACK});
        regionToWidgetTypeMbp.put(Region.SCROLL_PANE, WidgetType.SCROLL_PANE);
        regionToWidgetTypeMbp.put(Region.SEPARATOR, new WidgetType[] {
            WidgetType.HSEPARATOR, WidgetType.VSEPARATOR});
        regionToWidgetTypeMbp.put(Region.SLIDER, new WidgetType[] {
            WidgetType.HSLIDER, WidgetType.VSLIDER});
        regionToWidgetTypeMbp.put(Region.SLIDER_THUMB, new WidgetType[] {
            WidgetType.HSLIDER_THUMB, WidgetType.VSLIDER_THUMB});
        regionToWidgetTypeMbp.put(Region.SLIDER_TRACK, new WidgetType[] {
            WidgetType.HSLIDER_TRACK, WidgetType.VSLIDER_TRACK});
        regionToWidgetTypeMbp.put(Region.SPINNER, WidgetType.SPINNER);
        regionToWidgetTypeMbp.put(Region.SPLIT_PANE, WidgetType.SPLIT_PANE);
        regionToWidgetTypeMbp.put(Region.SPLIT_PANE_DIVIDER, new WidgetType[] {
            WidgetType.HSPLIT_PANE_DIVIDER, WidgetType.VSPLIT_PANE_DIVIDER});
        regionToWidgetTypeMbp.put(Region.TABBED_PANE, WidgetType.TABBED_PANE);
        regionToWidgetTypeMbp.put(Region.TABBED_PANE_CONTENT,
                                  WidgetType.TABBED_PANE_CONTENT);
        regionToWidgetTypeMbp.put(Region.TABBED_PANE_TAB,
                                  WidgetType.TABBED_PANE_TAB);
        regionToWidgetTypeMbp.put(Region.TABBED_PANE_TAB_AREA,
                                  WidgetType.TABBED_PANE_TAB_AREA);
        regionToWidgetTypeMbp.put(Region.TABLE, WidgetType.TABLE);
        regionToWidgetTypeMbp.put(Region.TABLE_HEADER, WidgetType.TABLE_HEADER);
        regionToWidgetTypeMbp.put(Region.TEXT_AREA, WidgetType.TEXT_AREA);
        regionToWidgetTypeMbp.put(Region.TEXT_FIELD, new WidgetType[] {
            WidgetType.TEXT_FIELD, WidgetType.COMBO_BOX_TEXT_FIELD});
        regionToWidgetTypeMbp.put(Region.TEXT_PANE, WidgetType.TEXT_PANE);
        regionToWidgetTypeMbp.put(CustomRegion.TITLED_BORDER, WidgetType.TITLED_BORDER);
        regionToWidgetTypeMbp.put(Region.TOGGLE_BUTTON, WidgetType.TOGGLE_BUTTON);
        regionToWidgetTypeMbp.put(Region.TOOL_BAR, WidgetType.TOOL_BAR);
        regionToWidgetTypeMbp.put(Region.TOOL_BAR_CONTENT, WidgetType.TOOL_BAR);
        regionToWidgetTypeMbp.put(Region.TOOL_BAR_DRAG_WINDOW,
                                  WidgetType.TOOL_BAR_DRAG_WINDOW);
        regionToWidgetTypeMbp.put(Region.TOOL_BAR_SEPARATOR,
                                  WidgetType.TOOL_BAR_SEPARATOR);
        regionToWidgetTypeMbp.put(Region.TOOL_TIP, WidgetType.TOOL_TIP);
        regionToWidgetTypeMbp.put(Region.TREE, WidgetType.TREE);
        regionToWidgetTypeMbp.put(Region.TREE_CELL, WidgetType.TREE_CELL);
        regionToWidgetTypeMbp.put(Region.VIEWPORT, WidgetType.VIEWPORT);
    }

    /** Trbnslbte Region bnd JComponent into WidgetType ordinbls */
    stbtic WidgetType getWidgetType(JComponent c, Region id) {
        Object vblue = regionToWidgetTypeMbp.get(id);

        if (vblue instbnceof WidgetType) {
            return (WidgetType)vblue;
        }

        WidgetType[] widgets = (WidgetType[])vblue;
        if (c == null ) {
            return widgets[0];
        }

        if (c instbnceof JScrollBbr) {
            return (((JScrollBbr)c).getOrientbtion() == JScrollBbr.HORIZONTAL) ?
                widgets[0] : widgets[1];
        } else if (c instbnceof JSepbrbtor) {
            JSepbrbtor sepbrbtor = (JSepbrbtor)c;

            /* We should return correrct WidgetType if the seperbtor is inserted
             * in Menu/PopupMenu/ToolBbr. BugID: 6465603
             */
            if (sepbrbtor.getPbrent() instbnceof JPopupMenu) {
                return WidgetType.POPUP_MENU_SEPARATOR;
            } else if (sepbrbtor.getPbrent() instbnceof JToolBbr) {
                return WidgetType.TOOL_BAR_SEPARATOR;
            }

            return (sepbrbtor.getOrientbtion() == JSepbrbtor.HORIZONTAL) ?
                widgets[0] : widgets[1];
        } else if (c instbnceof JSlider) {
            return (((JSlider)c).getOrientbtion() == JSlider.HORIZONTAL) ?
                widgets[0] : widgets[1];
        } else if (c instbnceof JProgressBbr) {
            return (((JProgressBbr)c).getOrientbtion() == JProgressBbr.HORIZONTAL) ?
                widgets[0] : widgets[1];
        } else if (c instbnceof JSplitPbne) {
            return (((JSplitPbne)c).getOrientbtion() == JSplitPbne.HORIZONTAL_SPLIT) ?
                widgets[1] : widgets[0];
        } else if (id == Region.LABEL) {
            /*
             * For bll ListCellRenderers we will use COMBO_BOX_TEXT_FIELD widget
             * type becbuse we cbn get correct insets. List items however won't be
             * drbwn bs b text entry (see GTKPbinter.pbintLbbelBbckground).
             */
            if (c instbnceof ListCellRenderer) {
                return widgets[1];
            } else {
                return widgets[0];
            }
        } else if (id == Region.TEXT_FIELD) {
            String nbme = c.getNbme();
            if (nbme != null && nbme.stbrtsWith("ComboBox")) {
                return widgets[1];
            } else {
                return widgets[0];
            }
        } else if (id == Region.FORMATTED_TEXT_FIELD) {
            String nbme = c.getNbme();
            if (nbme != null && nbme.stbrtsWith("Spinner")) {
                return widgets[1];
            } else {
                return widgets[0];
            }
        } else if (id == Region.ARROW_BUTTON) {
            if (c.getPbrent() instbnceof JScrollBbr) {
                Integer prop = (Integer)
                    c.getClientProperty("__brrow_direction__");
                int dir = (prop != null) ?
                    prop.intVblue() : SwingConstbnts.WEST;
                switch (dir) {
                cbse SwingConstbnts.WEST:
                    return WidgetType.HSCROLL_BAR_BUTTON_LEFT;
                cbse SwingConstbnts.EAST:
                    return WidgetType.HSCROLL_BAR_BUTTON_RIGHT;
                cbse SwingConstbnts.NORTH:
                    return WidgetType.VSCROLL_BAR_BUTTON_UP;
                cbse SwingConstbnts.SOUTH:
                    return WidgetType.VSCROLL_BAR_BUTTON_DOWN;
                defbult:
                    return null;
                }
            } else if (c.getPbrent() instbnceof JComboBox) {
                return WidgetType.COMBO_BOX_ARROW_BUTTON;
            } else {
                return WidgetType.SPINNER_ARROW_BUTTON;
            }
        }

        return null;
    }

    privbte stbtic int getTextDirection(SynthContext context) {
        TextDirection dir = TextDirection.NONE;
        JComponent comp = context.getComponent();
        if (comp != null) {
            ComponentOrientbtion co = comp.getComponentOrientbtion();
            if (co != null) {
                dir = co.isLeftToRight() ?
                    TextDirection.LTR : TextDirection.RTL;
            }
        }
        return dir.ordinbl();
    }

    public void pbintArrow(Grbphics g, SynthContext context,
            Region id, int stbte, ShbdowType shbdowType, ArrowType direction,
            String detbil, int x, int y, int w, int h) {

        stbte = GTKLookAndFeel.synthStbteToGTKStbteType(stbte).ordinbl();
        int widget = getWidgetType(context.getComponent(), id).ordinbl();
        nbtive_pbint_brrow(widget, stbte, shbdowType.ordinbl(),
                detbil, x - x0, y - y0, w, h, direction.ordinbl());
    }

    public void pbintBox(Grbphics g, SynthContext context,
            Region id, int stbte, ShbdowType shbdowType,
            String detbil, int x, int y, int w, int h) {

        int gtkStbte =
            GTKLookAndFeel.synthStbteToGTKStbteType(stbte).ordinbl();
        int synthStbte = context.getComponentStbte();
        int dir = getTextDirection(context);
        int widget = getWidgetType(context.getComponent(), id).ordinbl();
        nbtive_pbint_box(widget, gtkStbte, shbdowType.ordinbl(),
                         detbil, x - x0, y - y0, w, h, synthStbte, dir);
    }

    public void pbintBoxGbp(Grbphics g, SynthContext context,
            Region id, int stbte, ShbdowType shbdowType,
            String detbil, int x, int y, int w, int h,
            PositionType boxGbpType, int tbbBegin, int size) {

        stbte = GTKLookAndFeel.synthStbteToGTKStbteType(stbte).ordinbl();
        int widget = getWidgetType(context.getComponent(), id).ordinbl();
        nbtive_pbint_box_gbp(widget, stbte, shbdowType.ordinbl(), detbil,
                x - x0, y - y0, w, h, boxGbpType.ordinbl(), tbbBegin, size);
    }

    public void pbintCheck(Grbphics g, SynthContext context,
            Region id, String detbil, int x, int y, int w, int h) {

        int synthStbte = context.getComponentStbte();
        int widget = getWidgetType(context.getComponent(), id).ordinbl();
        nbtive_pbint_check(widget, synthStbte, detbil, x - x0, y - y0, w, h);
    }

    public void pbintExpbnder(Grbphics g, SynthContext context,
            Region id, int stbte, ExpbnderStyle expbnderStyle, String detbil,
            int x, int y, int w, int h) {

        stbte = GTKLookAndFeel.synthStbteToGTKStbteType(stbte).ordinbl();
        int widget = getWidgetType(context.getComponent(), id).ordinbl();
        nbtive_pbint_expbnder(widget, stbte, detbil, x - x0, y - y0, w, h,
                              expbnderStyle.ordinbl());
    }

    public void pbintExtension(Grbphics g, SynthContext context,
            Region id, int stbte, ShbdowType shbdowType, String detbil,
            int x, int y, int w, int h, PositionType plbcement, int tbbIndex) {

        stbte = GTKLookAndFeel.synthStbteToGTKStbteType(stbte).ordinbl();
        int widget = getWidgetType(context.getComponent(), id).ordinbl();
        nbtive_pbint_extension(widget, stbte, shbdowType.ordinbl(), detbil,
                               x - x0, y - y0, w, h, plbcement.ordinbl());
    }

    public void pbintFlbtBox(Grbphics g, SynthContext context,
            Region id, int stbte, ShbdowType shbdowType, String detbil,
            int x, int y, int w, int h, ColorType colorType) {

        stbte = GTKLookAndFeel.synthStbteToGTKStbteType(stbte).ordinbl();
        int widget = getWidgetType(context.getComponent(), id).ordinbl();
        nbtive_pbint_flbt_box(widget, stbte, shbdowType.ordinbl(), detbil,
                              x - x0, y - y0, w, h,
                              context.getComponent().hbsFocus());
    }

    public void pbintFocus(Grbphics g, SynthContext context,
            Region id, int stbte, String detbil, int x, int y, int w, int h) {

        stbte = GTKLookAndFeel.synthStbteToGTKStbteType(stbte).ordinbl();
        int widget = getWidgetType(context.getComponent(), id).ordinbl();
        nbtive_pbint_focus(widget, stbte, detbil, x - x0, y - y0, w, h);
    }

    public void pbintHbndle(Grbphics g, SynthContext context,
            Region id, int stbte, ShbdowType shbdowType, String detbil,
            int x, int y, int w, int h, Orientbtion orientbtion) {

        stbte = GTKLookAndFeel.synthStbteToGTKStbteType(stbte).ordinbl();
        int widget = getWidgetType(context.getComponent(), id).ordinbl();
        nbtive_pbint_hbndle(widget, stbte, shbdowType.ordinbl(), detbil,
                            x - x0, y - y0, w, h, orientbtion.ordinbl());
    }

    public void pbintHline(Grbphics g, SynthContext context,
            Region id, int stbte, String detbil, int x, int y, int w, int h) {

        stbte = GTKLookAndFeel.synthStbteToGTKStbteType(stbte).ordinbl();
        int widget = getWidgetType(context.getComponent(), id).ordinbl();
        nbtive_pbint_hline(widget, stbte, detbil, x - x0, y - y0, w, h);
    }

    public void pbintOption(Grbphics g, SynthContext context,
            Region id, String detbil, int x, int y, int w, int h) {

        int synthStbte = context.getComponentStbte();
        int widget = getWidgetType(context.getComponent(), id).ordinbl();
        nbtive_pbint_option(widget, synthStbte, detbil, x - x0, y - y0, w, h);
    }

    public void pbintShbdow(Grbphics g, SynthContext context,
            Region id, int stbte, ShbdowType shbdowType, String detbil,
            int x, int y, int w, int h) {

        int gtkStbte =
            GTKLookAndFeel.synthStbteToGTKStbteType(stbte).ordinbl();
        int synthStbte = context.getComponentStbte();
        int dir = getTextDirection(context);
        int widget = getWidgetType(context.getComponent(), id).ordinbl();
        nbtive_pbint_shbdow(widget, gtkStbte, shbdowType.ordinbl(), detbil,
                            x - x0, y - y0, w, h, synthStbte, dir);
    }

    public void pbintSlider(Grbphics g, SynthContext context,
            Region id, int stbte, ShbdowType shbdowType, String detbil,
            int x, int y, int w, int h, Orientbtion orientbtion) {

        stbte = GTKLookAndFeel.synthStbteToGTKStbteType(stbte).ordinbl();
        int widget = getWidgetType(context.getComponent(), id).ordinbl();
        nbtive_pbint_slider(widget, stbte, shbdowType.ordinbl(), detbil,
                            x - x0, y - y0, w, h, orientbtion.ordinbl());
    }

    public void pbintVline(Grbphics g, SynthContext context,
            Region id, int stbte, String detbil, int x, int y, int w, int h) {

        stbte = GTKLookAndFeel.synthStbteToGTKStbteType(stbte).ordinbl();
        int widget = getWidgetType(context.getComponent(), id).ordinbl();
        nbtive_pbint_vline(widget, stbte, detbil, x - x0, y - y0, w, h);
    }

    public void pbintBbckground(Grbphics g, SynthContext context,
            Region id, int stbte, Color color, int x, int y, int w, int h) {

        stbte = GTKLookAndFeel.synthStbteToGTKStbteType(stbte).ordinbl();
        int widget = getWidgetType(context.getComponent(), id).ordinbl();
        nbtive_pbint_bbckground(widget, stbte, x - x0, y - y0, w, h);
    }

    privbte finbl stbtic ColorModel[] COLOR_MODELS = {
        // Trbnspbrency.OPAQUE
        new DirectColorModel(24, 0x00ff0000, 0x0000ff00, 0x000000ff, 0x00000000),
        // Trbnspbrency.BITMASK
        new DirectColorModel(25, 0x00ff0000, 0x0000ff00, 0x000000ff, 0x01000000),
        // Trbnspbrency.TRANSLUCENT
        ColorModel.getRGBdefbult(),
    };

    privbte finbl stbtic int[][] BAND_OFFSETS = {
        { 0x00ff0000, 0x0000ff00, 0x000000ff },             // OPAQUE
        { 0x00ff0000, 0x0000ff00, 0x000000ff, 0x01000000 }, // BITMASK
        { 0x00ff0000, 0x0000ff00, 0x000000ff, 0xff000000 }  // TRANSLUCENT
    };


    /**
     * Pbint b cbched imbge identified by its size bnd b set of bdditionbl
     * brguments, if there's one.
     *
     * @return true if b cbched imbge hbs been pbinted, fblse otherwise
     */
    public boolebn pbintCbchedImbge(Grbphics g,
            int x, int y, int w, int h, Object... brgs) {
        if (w <= 0 || h <= 0) {
            return true;
        }

        // look for cbched imbge
        Imbge img = cbche.getImbge(getClbss(), null, w, h, brgs);
        if (img != null) {
            g.drbwImbge(img, x, y, null);
            return true;
        }
        return fblse;
    }

    /*
     * Allocbte b nbtive offscreen buffer of the specified size.
     */
    public void stbrtPbinting(Grbphics g,
            int x, int y, int w, int h, Object... brgs) {
        nbtiveStbrtPbinting(w, h);
        x0 = x;
        y0 = y;
        w0 = w;
        h0 = h;
        grbphics = g;
        cbcheArgs = brgs;
    }

    /**
     * Convenience method thbt delegbtes to finishPbinting() with
     * cbching enbbled.
     */
    public void finishPbinting() {
        finishPbinting(true);
    }

    /**
     * Cblled to indicbte thbt pbinting is finished. We crebte b new
     * BufferedImbge from the offscreen buffer, (optionblly) cbche it,
     * bnd pbint it.
     */
    public void finishPbinting(boolebn useCbche) {
        DbtbBufferInt dbtbBuffer = new DbtbBufferInt(w0 * h0);
        // Note thbt steblDbtb() requires b mbrkDirty() bfterwbrds
        // since we modify the dbtb in it.
        int trbnspbrency =
            nbtiveFinishPbinting(SunWritbbleRbster.steblDbtb(dbtbBuffer, 0),
                                 w0, h0);
        SunWritbbleRbster.mbrkDirty(dbtbBuffer);

        int[] bbnds = BAND_OFFSETS[trbnspbrency - 1];
        WritbbleRbster rbster = Rbster.crebtePbckedRbster(
                dbtbBuffer, w0, h0, w0, bbnds, null);

        ColorModel cm = COLOR_MODELS[trbnspbrency - 1];
        Imbge img = new BufferedImbge(cm, rbster, fblse, null);
        if (useCbche) {
            cbche.setImbge(getClbss(), null, w0, h0, cbcheArgs, img);
        }
        grbphics.drbwImbge(img, x0, y0, null);
    }

    /**
     * Notify nbtive lbyer of theme chbnge, bnd flush cbche
     */
    public void themeChbnged() {
        synchronized(sun.bwt.UNIXToolkit.GTK_LOCK) {
            nbtive_switch_theme();
        }
        cbche.flush();
    }

    /* GtkSettings enum mirrors thbt in gtk2_interfbce.h */
    public Object getSetting(Settings property) {
        synchronized(sun.bwt.UNIXToolkit.GTK_LOCK) {
            return nbtive_get_gtk_setting(property.ordinbl());
        }
    }

    /**
     * Sets up the GtkAdjustment vblues for the nbtive GtkRbnge widget
     * bssocibted with the given region (e.g. SLIDER, SCROLL_BAR).
     */
    void setRbngeVblue(SynthContext context, Region id,
                       double vblue, double min, double mbx, double visible) {
        int widget = getWidgetType(context.getComponent(), id).ordinbl();
        nbtiveSetRbngeVblue(widget, vblue, min, mbx, visible);
    }
}
