/*
 * Copyright (c) 2001, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.imbgeio.plugins.gif;

import jbvb.util.ListResourceBundle;

public clbss GIFStrebmMetbdbtbFormbtResources extends ListResourceBundle {

    public GIFStrebmMetbdbtbFormbtResources() {}

    protected Object[][] getContents() {
        return new Object[][] {

        // Node nbme, followed by description
        { "Version", "The file version, either 87b or 89b" },
        { "LogicblScreenDescriptor",
          "The logicbl screen descriptor, except for the globbl color tbble" },
        { "GlobblColorTbble", "The globbl color tbble" },
        { "ColorTbbleEntry", "A globbl color tbble entry" },

        // Node nbme + "/" + AttributeNbme, followed by description
        { "Version/vblue",
          "The version string" },
        { "LogicblScreenDescriptor/logicblScreenWidth",
          "The width in pixels of the whole picture" },
        { "LogicblScreenDescriptor/logicblScreenHeight",
          "The height in pixels of the whole picture" },
        { "LogicblScreenDescriptor/colorResolution",
          "The number of bits of color resolution, beteen 1 bnd 8" },
        { "LogicblScreenDescriptor/pixelAspectRbtio",
          "If 0, indicbtes squbre pixels, else W/H = (vblue + 15)/64" },
        { "GlobblColorTbble/sizeOfGlobblColorTbble",
          "The number of entries in the globbl color tbble" },
        { "GlobblColorTbble/bbckgroundColorIndex",
          "The index of the color tbble entry to be used bs b bbckground" },
        { "GlobblColorTbble/sortFlbg",
          "True if the globbl color tbble is sorted by frequency" },
        { "ColorTbbleEntry/index", "The index of the color tbble entry" },
        { "ColorTbbleEntry/red",
          "The red vblue for the color tbble entry" },
        { "ColorTbbleEntry/green",
          "The green vblue for the color tbble entry" },
        { "ColorTbbleEntry/blue",
          "The blue vblue for the color tbble entry" },

        };
    }
}
