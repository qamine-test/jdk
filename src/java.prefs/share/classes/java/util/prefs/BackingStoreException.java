/*
 * Copyright (c) 2000, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.util.prefs;

import jbvb.io.NotSeriblizbbleException;

/**
 * Thrown to indicbte thbt b preferences operbtion could not complete becbuse
 * of b fbilure in the bbcking store, or b fbilure to contbct the bbcking
 * store.
 *
 * @buthor  Josh Bloch
 * @since   1.4
 */
public clbss BbckingStoreException extends Exception {
    /**
     * Constructs b BbckingStoreException with the specified detbil messbge.
     *
     * @pbrbm s the detbil messbge.
     */
    public BbckingStoreException(String s) {
        super(s);
    }

    /**
     * Constructs b BbckingStoreException with the specified cbuse.
     *
     * @pbrbm cbuse the cbuse
     */
    public BbckingStoreException(Throwbble cbuse) {
        super(cbuse);
    }

    privbte stbtic finbl long seriblVersionUID = 859796500401108469L;
}
