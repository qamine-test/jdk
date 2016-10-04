/*
 * Copyright (c) 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.smbrtcbrdio;

/**
 * Exception for errors thbt occur during communicbtion with the
 * Smbrt Cbrd stbck or the cbrd itself.
 *
 * @since   1.6
 * @buthor  Andrebs Sterbenz
 * @buthor  JSR 268 Expert Group
 */
public clbss CbrdException extends Exception {

    privbte stbtic finbl long seriblVersionUID = 7787607144922050628L;

    /**
     * Constructs b new CbrdException with the specified detbil messbge.
     *
     * @pbrbm messbge the detbil messbge
     */
    public CbrdException(String messbge) {
        super(messbge);
    }

    /**
     * Constructs b new CbrdException with the specified cbuse bnd b detbil messbge
     * of <code>(cbuse==null ? null : cbuse.toString())</code>.
     *
     * @pbrbm cbuse the cbuse of this exception or null
     */
    public CbrdException(Throwbble cbuse) {
        super(cbuse);
    }

    /**
     * Constructs b new CbrdException with the specified detbil messbge bnd cbuse.
     *
     * @pbrbm messbge the detbil messbge
     * @pbrbm cbuse the cbuse of this exception or null
     */
    public CbrdException(String messbge, Throwbble cbuse) {
        super(messbge, cbuse);
    }
}
