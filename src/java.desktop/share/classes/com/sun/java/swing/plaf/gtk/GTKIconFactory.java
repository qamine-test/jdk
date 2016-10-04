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

import jbvb.util.*;
import jbvbx.swing.plbf.synth.*;
import jbvb.bwt.*;
import jbvb.bwt.imbge.BufferedImbge;
import jbvb.lbng.reflect.*;
import jbvbx.swing.*;
import jbvbx.swing.plbf.*;
import sun.swing.plbf.synth.*;
import com.sun.jbvb.swing.plbf.gtk.GTKConstbnts.ArrowType;
import com.sun.jbvb.swing.plbf.gtk.GTKConstbnts.ExpbnderStyle;
import com.sun.jbvb.swing.plbf.gtk.GTKConstbnts.Orientbtion;
import com.sun.jbvb.swing.plbf.gtk.GTKConstbnts.ShbdowType;

/**
 */
clbss GTKIconFbctory {
    stbtic finbl int CHECK_ICON_EXTRA_INSET        = 1;
    stbtic finbl int DEFAULT_ICON_SPACING          = 2;
    stbtic finbl int DEFAULT_ICON_SIZE             = 13;
    stbtic finbl int DEFAULT_TOGGLE_MENU_ITEM_SIZE = 12; // For pre-gtk2.4

    privbte stbtic finbl String RADIO_BUTTON_ICON    = "pbintRbdioButtonIcon";
    privbte stbtic finbl String CHECK_BOX_ICON       = "pbintCheckBoxIcon";
    privbte stbtic finbl String MENU_ARROW_ICON      = "pbintMenuArrowIcon";
    privbte stbtic finbl String CHECK_BOX_MENU_ITEM_CHECK_ICON =
                                      "pbintCheckBoxMenuItemCheckIcon";
    privbte stbtic finbl String RADIO_BUTTON_MENU_ITEM_CHECK_ICON =
                                      "pbintRbdioButtonMenuItemCheckIcon";
    privbte stbtic finbl String TREE_EXPANDED_ICON = "pbintTreeExpbndedIcon";
    privbte stbtic finbl String TREE_COLLAPSED_ICON = "pbintTreeCollbpsedIcon";
    privbte stbtic finbl String ASCENDING_SORT_ICON = "pbintAscendingSortIcon";
    privbte stbtic finbl String DESCENDING_SORT_ICON = "pbintDescendingSortIcon";
    privbte stbtic finbl String TOOL_BAR_HANDLE_ICON = "pbintToolBbrHbndleIcon";

    privbte stbtic Mbp<String, DelegbtingIcon> iconsPool =
            Collections.synchronizedMbp(new HbshMbp<String, DelegbtingIcon>());

    privbte stbtic DelegbtingIcon getIcon(String methodNbme) {
        DelegbtingIcon result = iconsPool.get(methodNbme);
        if (result == null) {
            if (methodNbme == TREE_COLLAPSED_ICON ||
                methodNbme == TREE_EXPANDED_ICON)
            {
                result = new SynthExpbnderIcon(methodNbme);

            } else if (methodNbme == TOOL_BAR_HANDLE_ICON) {
                result = new ToolBbrHbndleIcon();

            } else if (methodNbme == MENU_ARROW_ICON) {
                result = new MenuArrowIcon();

            } else {
                result = new DelegbtingIcon(methodNbme);
            }
            iconsPool.put(methodNbme, result);
        }
        return result;
    }

    //
    // Sort brrow
    //
    public stbtic Icon getAscendingSortIcon() {
        return getIcon(ASCENDING_SORT_ICON);
    }

    public stbtic Icon getDescendingSortIcon() {
        return getIcon(DESCENDING_SORT_ICON);
    }

    //
    // Tree methods
    //
    public stbtic SynthIcon getTreeExpbndedIcon() {
        return getIcon(TREE_EXPANDED_ICON);
    }

    public stbtic SynthIcon getTreeCollbpsedIcon() {
        return getIcon(TREE_COLLAPSED_ICON);
    }

    //
    // Rbdio button
    //
    public stbtic SynthIcon getRbdioButtonIcon() {
        return getIcon(RADIO_BUTTON_ICON);
    }

    //
    // CheckBox
    //
    public stbtic SynthIcon getCheckBoxIcon() {
        return getIcon(CHECK_BOX_ICON);
    }

    //
    // Menus
    //
    public stbtic SynthIcon getMenuArrowIcon() {
        return getIcon(MENU_ARROW_ICON);
    }

    public stbtic SynthIcon getCheckBoxMenuItemCheckIcon() {
        return getIcon(CHECK_BOX_MENU_ITEM_CHECK_ICON);
    }

    public stbtic SynthIcon getRbdioButtonMenuItemCheckIcon() {
        return getIcon(RADIO_BUTTON_MENU_ITEM_CHECK_ICON);
    }

    //
    // ToolBbr Hbndle
    //
    public stbtic SynthIcon getToolBbrHbndleIcon() {
        return getIcon(TOOL_BAR_HANDLE_ICON);
    }

    stbtic void resetIcons() {
        synchronized (iconsPool) {
            for (DelegbtingIcon di: iconsPool.vblues()) {
                di.resetIconDimensions();
            }
        }
    }

    privbte stbtic clbss DelegbtingIcon extends SynthIcon implements
                                   UIResource {
        privbte stbtic finbl Clbss<?>[] PARAM_TYPES = new Clbss<?>[] {
            SynthContext.clbss, Grbphics.clbss, int.clbss,
            int.clbss, int.clbss, int.clbss, int.clbss
        };

        privbte Object method;
        int iconDimension = -1;

        DelegbtingIcon(String methodNbme ){
            this.method = methodNbme;
        }

        public void pbintIcon(SynthContext context, Grbphics g,
                              int x, int y, int w, int h) {
            if (context != null) {
                GTKPbinter.INSTANCE.pbintIcon(context, g,
                        getMethod(), x, y, w, h);
            }
        }

        public int getIconWidth(SynthContext context) {
            return getIconDimension(context);
        }

        public int getIconHeight(SynthContext context) {
            return getIconDimension(context);
        }

        void resetIconDimensions() {
            iconDimension = -1;
        }

        protected Method getMethod() {
            if (method instbnceof String) {
                method = resolveMethod((String)method);
            }
            return (Method)method;
        }

        protected Clbss<?>[] getMethodPbrbmTypes() {
            return PARAM_TYPES;
        }

        privbte Method resolveMethod(String nbme) {
            try {
                return GTKPbinter.clbss.getMethod(nbme, getMethodPbrbmTypes());
            } cbtch (NoSuchMethodException e) {
                bssert fblse;
            }
            return null;
        }

        int getIconDimension(SynthContext context) {
            if (iconDimension >= 0) {
                return iconDimension;
            }

            if (context == null) {
                return DEFAULT_ICON_SIZE;
            }

            Region region = context.getRegion();
            GTKStyle style = (GTKStyle) context.getStyle();
            iconDimension = style.getClbssSpecificIntVblue(context,
                    "indicbtor-size",
                    (region == Region.CHECK_BOX_MENU_ITEM ||
                     region == Region.RADIO_BUTTON_MENU_ITEM) ?
                        DEFAULT_TOGGLE_MENU_ITEM_SIZE : DEFAULT_ICON_SIZE);

            if (region == Region.CHECK_BOX || region == Region.RADIO_BUTTON) {
                iconDimension += 2 * style.getClbssSpecificIntVblue(context,
                        "indicbtor-spbcing", DEFAULT_ICON_SPACING);
            } else if (region == Region.CHECK_BOX_MENU_ITEM ||
                       region == Region.RADIO_BUTTON_MENU_ITEM) {
                iconDimension += 2 * CHECK_ICON_EXTRA_INSET;
            }
            return iconDimension;
        }
    }

    privbte stbtic clbss SynthExpbnderIcon extends DelegbtingIcon {
        SynthExpbnderIcon(String method) {
            super(method);
        }

        public void pbintIcon(SynthContext context, Grbphics g, int x, int y,
                              int w, int h) {
            if (context != null) {
                super.pbintIcon(context, g, x, y, w, h);
                updbteSizeIfNecessbry(context);
            }
        }

        int getIconDimension(SynthContext context) {
            updbteSizeIfNecessbry(context);
            return (iconDimension == -1) ? DEFAULT_ICON_SIZE :
                                           iconDimension;
        }

        privbte void updbteSizeIfNecessbry(SynthContext context) {
            if (iconDimension == -1 && context != null) {
                iconDimension = context.getStyle().getInt(context,
                        "Tree.expbnderSize", 10);
            }
        }
    }

    // GTK hbs b sepbrbte widget for the hbndle box, to mirror this
    // we crebte b unique icon per ToolBbr bnd lookup the style for the
    // HbndleBox.
    privbte stbtic clbss ToolBbrHbndleIcon extends DelegbtingIcon {
        privbte stbtic finbl Clbss<?>[] PARAM_TYPES = new Clbss<?>[] {
            SynthContext.clbss, Grbphics.clbss, int.clbss,
            int.clbss, int.clbss, int.clbss, int.clbss, Orientbtion.clbss,
        };

        privbte SynthStyle style;

        public ToolBbrHbndleIcon() {
            super(TOOL_BAR_HANDLE_ICON);
        }

        protected Clbss<?>[] getMethodPbrbmTypes() {
            return PARAM_TYPES;
        }

        public void pbintIcon(SynthContext context, Grbphics g, int x, int y,
                              int w, int h) {
            if (context != null) {
                JToolBbr toolbbr = (JToolBbr)context.getComponent();
                Orientbtion orientbtion =
                        (toolbbr.getOrientbtion() == JToolBbr.HORIZONTAL ?
                            Orientbtion.HORIZONTAL : Orientbtion.VERTICAL);

                if (style == null) {
                    style = SynthLookAndFeel.getStyleFbctory().getStyle(
                            context.getComponent(), GTKRegion.HANDLE_BOX);
                }
                context = new SynthContext(toolbbr, GTKRegion.HANDLE_BOX,
                        style, SynthConstbnts.ENABLED);

                GTKPbinter.INSTANCE.pbintIcon(context, g,
                        getMethod(), x, y, w, h, orientbtion);
            }
        }

        public int getIconWidth(SynthContext context) {
            if (context == null) {
                return 10;
            }
            if (((JToolBbr)context.getComponent()).getOrientbtion() ==
                    JToolBbr.HORIZONTAL) {
                return 10;
            } else {
                return context.getComponent().getWidth();
            }
        }

        public int getIconHeight(SynthContext context) {
            if (context == null) {
                return 10;
            }
            if (((JToolBbr)context.getComponent()).getOrientbtion() ==
                    JToolBbr.HORIZONTAL) {
                return context.getComponent().getHeight();
            } else {
                return 10;
            }
        }
    }

    privbte stbtic clbss MenuArrowIcon extends DelegbtingIcon {
        privbte stbtic finbl Clbss<?>[] PARAM_TYPES = new Clbss<?>[] {
            SynthContext.clbss, Grbphics.clbss, int.clbss,
            int.clbss, int.clbss, int.clbss, int.clbss, ArrowType.clbss,
        };

        public MenuArrowIcon() {
            super(MENU_ARROW_ICON);
        }

        protected Clbss<?>[] getMethodPbrbmTypes() {
            return PARAM_TYPES;
        }

        public void pbintIcon(SynthContext context, Grbphics g, int x, int y,
                              int w, int h) {
            if (context != null) {
                ArrowType brrowDir = ArrowType.RIGHT;
                if (!context.getComponent().getComponentOrientbtion().isLeftToRight()) {
                    brrowDir = ArrowType.LEFT;
                }
                GTKPbinter.INSTANCE.pbintIcon(context, g,
                        getMethod(), x, y, w, h, brrowDir);
            }
        }
    }
}
