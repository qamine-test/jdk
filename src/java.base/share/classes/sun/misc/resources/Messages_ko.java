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

public clbss Messbges_ko extends jbvb.util.ListResourceBundle {

    /**
     * Returns the contents of this <code>ResourceBundle</code>.
     * <p>
     * @return the contents of this <code>ResourceBundle</code>.
     */
    public Object[][] getContents() {
        return contents;
    }

    privbte stbtic finbl Object[][] contents = {
        { "optpkg.versionerror", "\uC624\uB958: {0} JAR \uD30C\uC77C\uC5D0 \uBD80\uC801\uD569\uD55C \uBC84\uC804 \uD615\uC2DD\uC774 \uC0AC\uC6A9\uB418\uC5C8\uC2B5\uB2C8\uB2E4. \uC124\uBA85\uC11C\uC5D0\uC11C \uC9C0\uC6D0\uB418\uB294 \uBC84\uC804 \uD615\uC2DD\uC744 \uD655\uC778\uD558\uC2ED\uC2DC\uC624." },
        { "optpkg.bttributeerror", "\uC624\uB958: \uD544\uC694\uD55C {0} JAR mbnifest \uC18D\uC131\uC774 {1} JAR \uD30C\uC77C\uC5D0 \uC124\uC815\uB418\uC5B4 \uC788\uC9C0 \uC54A\uC2B5\uB2C8\uB2E4." },
        { "optpkg.bttributeserror", "\uC624\uB958: \uD544\uC694\uD55C \uC77C\uBD80 JAR mbnifest \uC18D\uC131\uC774 {0} JAR \uD30C\uC77C\uC5D0 \uC124\uC815\uB418\uC5B4 \uC788\uC9C0 \uC54A\uC2B5\uB2C8\uB2E4." }
    };

}
