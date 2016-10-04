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
 * Signbls thbt b credentibl wbs not found.
 *
 * <p> This exception mby be thrown by b LoginModule if it is unbble
 * to locbte b credentibl necessbry to perform buthenticbtion.
 *
 * @since 1.5
 */
public clbss CredentiblNotFoundException extends CredentiblException {

    privbte stbtic finbl long seriblVersionUID = -7779934467214319475L;

    /**
     * Constructs b CredentiblNotFoundException with no detbil messbge.
     * A detbil messbge is b String thbt describes this pbrticulbr exception.
     */
    public CredentiblNotFoundException() {
        super();
    }

    /**
     * Constructs b CredentiblNotFoundException with the specified
     * detbil messbge. A detbil messbge is b String thbt describes
     * this pbrticulbr exception.
     *
     * <p>
     *
     * @pbrbm msg the detbil messbge.
     */
    public CredentiblNotFoundException(String msg) {
        super(msg);
    }
}
