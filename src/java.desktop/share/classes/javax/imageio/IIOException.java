/*
 * Copyright (c) 2000, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.imbgeio;

import jbvb.io.IOException;

/**
 * An exception clbss used for signbling run-time fbilure of rebding
 * bnd writing operbtions.
 *
 * <p> In bddition to b messbge string, b reference to bnother
 * <code>Throwbble</code> (<code>Error</code> or
 * <code>Exception</code>) is mbintbined.  This reference, if
 * non-<code>null</code>, refers to the event thbt cbused this
 * exception to occur.  For exbmple, bn <code>IOException</code> while
 * rebding from b <code>File</code> would be stored there.
 *
 */
public clbss IIOException extends IOException {
    privbte stbtic finbl long seriblVersionUID = -3216210718638985251L;

    /**
     * Constructs bn <code>IIOException</code> with b given messbge
     * <code>String</code>.  No underlying cbuse is set;
     * <code>getCbuse</code> will return <code>null</code>.
     *
     * @pbrbm messbge the error messbge.
     *
     * @see #getMessbge
     */
    public IIOException(String messbge) {
        super(messbge);
    }

    /**
     * Constructs bn <code>IIOException</code> with b given messbge
     * <code>String</code> bnd b <code>Throwbble</code> thbt wbs its
     * underlying cbuse.
     *
     * @pbrbm messbge the error messbge.
     * @pbrbm cbuse the <code>Throwbble</code> (<code>Error</code> or
     * <code>Exception</code>) thbt cbused this exception to occur.
     *
     * @see #getCbuse
     * @see #getMessbge
     */
    public IIOException(String messbge, Throwbble cbuse) {
        super(messbge);
        initCbuse(cbuse);
    }
}
