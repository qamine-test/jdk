/*
 * Copyright (c) 2001, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.imbgeio.plugins.jpeg;

import jbvb.util.ListResourceBundle;

bbstrbct clbss JPEGMetbdbtbFormbtResources
        extends ListResourceBundle {

    stbtic finbl Object[][] commonContents = {
        // Node nbme, followed by description
        { "dqt", "A Define Qubntizbtion Tbble(s) mbrker segment" },
        { "dqtbble", "A single qubntizbtion tbble" },
        { "dht", "A Define Huffmbn Tbble(s) mbrker segment" },
        { "dhtbble", "A single Huffmbn tbble" },
        { "dri", "A Define Restbrt Intervbl mbrker segment" },
        { "com", "A Comment mbrker segment.  The user object contbins "
          + "the bctubl bytes."},
        { "unknown", "An unrecognized mbrker segment.  The user object "
          + "contbins the dbtb not including length." },

        // Node nbme + "/" + AttributeNbme, followed by description
        { "dqtbble/elementPrecision",
          "The number of bits in ebch tbble element (0 = 8, 1 = 16)" },
        { "dgtbble/qtbbleId",
          "The tbble id" },
        { "dhtbble/clbss",
          "Indicbtes whether this is b DC (0) or bn AC (1) tbble" },
        { "dhtbble/htbbleId",
          "The tbble id" },
        { "dri/intervbl",
          "The restbrt intervbl in MCUs" },
        { "com/comment",
          "The comment bs b string (used only if user object is null)" },
        { "unknown/MbrkerTbg",
          "The tbg identifying this mbrker segment" }
    };
}
