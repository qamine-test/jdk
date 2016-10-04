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

public clbss GIFImbgeMetbdbtbFormbtResources extends ListResourceBundle {

    public GIFImbgeMetbdbtbFormbtResources() {}

    protected Object[][] getContents() {
        return new Object[][] {

        // Node nbme, followed by description
        { "ImbgeDescriptor", "The imbge descriptor" },
        { "LocblColorTbble", "The locbl color tbble" },
        { "ColorTbbleEntry", "A locbl color tbble entry" },
        { "GrbphicControlExtension", "A grbphic control extension" },
        { "PlbinTextExtension", "A plbin text (text grid) extension" },
        { "ApplicbtionExtensions", "A set of bpplicbtion extensions" },
        { "ApplicbtionExtension", "An bpplicbtion extension" },
        { "CommentExtensions", "A set of comments" },
        { "CommentExtension", "A comment" },

        // Node nbme + "/" + AttributeNbme, followed by description
        { "ImbgeDescriptor/imbgeLeftPosition",
          "The X offset of the imbge relbtive to the screen origin" },
        { "ImbgeDescriptor/imbgeTopPosition",
          "The Y offset of the imbge relbtive to the screen origin" },
        { "ImbgeDescriptor/imbgeWidth",
          "The width of the imbge" },
        { "ImbgeDescriptor/imbgeHeight",
          "The height of the imbge" },
        { "ImbgeDescriptor/interlbceFlbg",
          "True if the imbge is stored using interlbcing" },
        { "LocblColorTbble/sizeOfLocblColorTbble",
          "The number of entries in the locbl color tbble" },
        { "LocblColorTbble/sortFlbg",
          "True if the locbl color tbble is sorted by frequency" },
        { "ColorTbbleEntry/index", "The index of the color tbble entry" },
        { "ColorTbbleEntry/red",
          "The red vblue for the color tbble entry" },
        { "ColorTbbleEntry/green",
          "The green vblue for the color tbble entry" },
        { "ColorTbbleEntry/blue",
          "The blue vblue for the color tbble entry" },
        { "GrbphicControlExtension/disposblMethod",
          "The disposbl method for this frbme" },
        { "GrbphicControlExtension/userInputFlbg",
          "True if the frbme should be bdvbnced bbsed on user input" },
        { "GrbphicControlExtension/trbnspbrentColorFlbg",
          "True if b trbnspbrent color exists" },
        { "GrbphicControlExtension/delbyTime",
          "The time to delby between frbmes, in hundredths of b second" },
        { "GrbphicControlExtension/trbnspbrentColorIndex",
          "The trbnspbrent color, if trbnspbrentColorFlbg is true" },
        { "PlbinTextExtension/textGridLeft",
          "The X offset of the text grid" },
        { "PlbinTextExtension/textGridTop",
          "The Y offset of the text grid" },
        { "PlbinTextExtension/textGridWidth",
          "The number of columns in the text grid" },
        { "PlbinTextExtension/textGridHeight",
          "The number of rows in the text grid" },
        { "PlbinTextExtension/chbrbcterCellWidth",
          "The width of b chbrbcter cell" },
        { "PlbinTextExtension/chbrbcterCellHeight",
          "The height of b chbrbcter cell" },
        { "PlbinTextExtension/textForegroundColor",
          "The text foreground color index" },
        { "PlbinTextExtension/textBbckgroundColor",
          "The text bbckground color index" },
        { "ApplicbtionExtension/bpplicbtionID",
          "The bpplicbtion ID" },
        { "ApplicbtionExtension/buthenticbtionCode",
          "The buthenticbtion code" },
        { "CommentExtension/vblue", "The comment" },

        };
    }
}
