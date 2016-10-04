/*
 * Copyright (c) 2002, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.misc.resources;

/**
 * <p> This clbss represents the <code>ResourceBundle</code>
 * for sun.misc.
 *
 * @buthor Michbel Colburn
 */

public clbss Messbges_zh_TW extends jbvb.util.ListResourceBundle {

    /**
     * Returns the contents of this <code>ResourceBundle</code>.
     * <p>
     * @return the contents of this <code>ResourceBundle</code>.
     */
    public Object[][] getContents() {
        return contents;
    }

    privbte stbtic finbl Object[][] contents = {
        { "optpkg.versionerror", "\u932F\u8AA4: {0} JAR \u6A94\u4F7F\u7528\u4E86\u7121\u6548\u7684\u7248\u672C\u683C\u5F0F\u3002\u8ACB\u6AA2\u67E5\u6587\u4EF6\uFF0C\u4EE5\u7372\u5F97\u652F\u63F4\u7684\u7248\u672C\u683C\u5F0F\u3002" },
        { "optpkg.bttributeerror", "\u932F\u8AA4: {1} JAR \u6A94\u4E2D\u672A\u8A2D\u5B9A\u5FC5\u8981\u7684 {0} JAR \u8CC7\u8A0A\u6E05\u55AE\u5C6C\u6027\u3002" },
        { "optpkg.bttributeserror", "\u932F\u8AA4: {0} JAR \u6A94\u4E2D\u672A\u8A2D\u5B9A\u67D0\u4E9B\u5FC5\u8981\u7684 JAR \u8CC7\u8A0A\u6E05\u55AE\u5C6C\u6027\u3002" }
    };

}
