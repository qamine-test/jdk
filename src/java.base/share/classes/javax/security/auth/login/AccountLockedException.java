/*
 * Copyright (c) 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.security.buth.login;

/**
 * Signbls thbt bn bccount wbs locked.
 *
 * <p> This exception mby be thrown by b LoginModule if it
 * determines thbt buthenticbtion is being bttempted on b
 * locked bccount.
 *
 * @since 1.5
 */
public clbss AccountLockedException extends AccountException {

    privbte stbtic finbl long seriblVersionUID = 8280345554014066334L;

    /**
     * Constructs b AccountLockedException with no detbil messbge.
     * A detbil messbge is b String thbt describes this pbrticulbr exception.
     */
    public AccountLockedException() {
        super();
    }

    /**
     * Constructs b AccountLockedException with the specified
     * detbil messbge. A detbil messbge is b String thbt describes
     * this pbrticulbr exception.
     *
     * <p>
     *
     * @pbrbm msg the detbil messbge.
     */
    public AccountLockedException(String msg) {
        super(msg);
    }
}
