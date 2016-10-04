/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.tools.nbtive2bscii.resources;

import jbvb.util.ListResourceBundle;

public clbss MsgNbtive2bscii_zh_CN extends ListResourceBundle {

    public Object[][] getContents() {
        Object[][] temp = new Object[][] {
        {"err.bbd.brg", "-encoding \u9700\u8981\u53C2\u6570"},
        {"err.cbnnot.rebd",  "\u65E0\u6CD5\u8BFB\u53D6{0}\u3002"},
        {"err.cbnnot.write", "\u65E0\u6CD5\u5199\u5165{0}\u3002"},
        {"usbge", "\u7528\u6CD5: nbtive2bscii [-reverse] [-encoding encoding] [inputfile [outputfile]]"},
        };

        return temp;
    }
}
