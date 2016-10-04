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

public clbss Messbges_jb extends jbvb.util.ListResourceBundle {

    /**
     * Returns the contents of this <code>ResourceBundle</code>.
     * <p>
     * @return the contents of this <code>ResourceBundle</code>.
     */
    public Object[][] getContents() {
        return contents;
    }

    privbte stbtic finbl Object[][] contents = {
        { "optpkg.versionerror", "\u30A8\u30E9\u30FC: JAR\u30D5\u30A1\u30A4\u30EB{0}\u3067\u7121\u52B9\u306A\u30D0\u30FC\u30B8\u30E7\u30F3\u5F62\u5F0F\u304C\u4F7F\u7528\u3055\u308C\u3066\u3044\u307E\u3059\u3002\u30B5\u30DD\u30FC\u30C8\u3055\u308C\u308B\u30D0\u30FC\u30B8\u30E7\u30F3\u5F62\u5F0F\u306B\u3064\u3044\u3066\u306E\u30C9\u30AD\u30E5\u30E1\u30F3\u30C8\u3092\u53C2\u7167\u3057\u3066\u304F\u3060\u3055\u3044\u3002" },
        { "optpkg.bttributeerror", "\u30A8\u30E9\u30FC: \u5FC5\u8981\u306AJAR\u30DE\u30CB\u30D5\u30A7\u30B9\u30C8\u5C5E\u6027{0}\u304CJAR\u30D5\u30A1\u30A4\u30EB{1}\u306B\u8A2D\u5B9A\u3055\u308C\u3066\u3044\u307E\u305B\u3093\u3002" },
        { "optpkg.bttributeserror", "\u30A8\u30E9\u30FC: \u8907\u6570\u306E\u5FC5\u8981\u306AJAR\u30DE\u30CB\u30D5\u30A7\u30B9\u30C8\u5C5E\u6027\u304CJAR\u30D5\u30A1\u30A4\u30EB{0}\u306B\u8A2D\u5B9A\u3055\u308C\u3066\u3044\u307E\u305B\u3093\u3002" }
    };

}
