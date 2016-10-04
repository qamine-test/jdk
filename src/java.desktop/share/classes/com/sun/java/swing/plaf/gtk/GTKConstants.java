/*
 * Copyright (c) 2002, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * @buthor Scott Violet
 */
public interfbce GTKConstbnts {

    /**
     * Used to indicbte b constbnt is not defined.
     */
    public stbtic finbl int UNDEFINED = -100;

    /**
     * Jbvb representbtion of nbtive GtkIconSize enum
     */
    public enum IconSize {
        INVALID,
        MENU,
        SMALL_TOOLBAR,
        LARGE_TOOLBAR,
        BUTTON,
        DND,
        DIALOG
    }

    /**
     * Jbvb representbtion of nbtive GtkTextDirection enum
     */
    public enum TextDirection {
        NONE,
        LTR,
        RTL
    }

    /**
     * Jbvb representbtion of nbtive GtkShbdowType enum
     */
    public enum ShbdowType {
        NONE,
        IN,
        OUT,
        ETCHED_IN,
        ETCHED_OUT
    }

    /**
     * Jbvb representbtion of nbtive GtkStbteType enum
     */
    public enum StbteType {
        NORMAL,
        ACTIVE,
        PRELIGHT,
        SELECTED,
        INSENSITIVE
    }

    /**
     * Jbvb representbtion of nbtive GtkExpbnderStyle enum
     */
    public enum ExpbnderStyle {
        COLLAPSED,
        SEMI_COLLAPSED,
        SEMI_EXPANDED,
        EXPANDED,
    }

    /**
     * Jbvb representbtion of nbtive GtkPositionType enum
     */
    public enum PositionType {
        LEFT,
        RIGHT,
        TOP,
        BOTTOM
    }

    /**
     * Jbvb representbtion of nbtive GtkArrowType enum
     */
    public enum ArrowType {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    /**
     * Jbvb representbtion of nbtive GtkOrientbtion enum
     */
    public enum Orientbtion {
        HORIZONTAL,
        VERTICAL
    }
}
