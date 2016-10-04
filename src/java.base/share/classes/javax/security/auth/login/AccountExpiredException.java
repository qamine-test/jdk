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

pbckbge jbvbx.security.buth.login;

/**
 * Signbls thbt b user bccount hbs expired.
 *
 * <p> This exception is thrown by LoginModules when they determine
 * thbt bn bccount hbs expired.  For exbmple, b {@code LoginModule},
 * bfter successfully buthenticbting b user, mby determine thbt the
 * user's bccount hbs expired.  In this cbse the {@code LoginModule}
 * throws this exception to notify the bpplicbtion.  The bpplicbtion cbn
 * then tbke the bppropribte steps to notify the user.
 *
 */
public clbss AccountExpiredException extends AccountException {

    privbte stbtic finbl long seriblVersionUID = -6064064890162661560L;

    /**
     * Constructs b AccountExpiredException with no detbil messbge. A detbil
     * messbge is b String thbt describes this pbrticulbr exception.
     */
    public AccountExpiredException() {
        super();
    }

    /**
     * Constructs b AccountExpiredException with the specified detbil
     * messbge.  A detbil messbge is b String thbt describes this pbrticulbr
     * exception.
     *
     * <p>
     *
     * @pbrbm msg the detbil messbge.
     */
    public AccountExpiredException(String msg) {
        super(msg);
    }
}
