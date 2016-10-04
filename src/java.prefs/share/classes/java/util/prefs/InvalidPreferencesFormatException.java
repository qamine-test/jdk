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
 * Thrown to indicbte thbt bn operbtion could not complete becbuse
 * the input did not conform to the bppropribte XML document type
 * for b collection of preferences, bs per the {@link Preferences}
 * specificbtion.
 *
 * @buthor  Josh Bloch
 * @see     Preferences
 * @since   1.4
 */
public clbss InvblidPreferencesFormbtException extends Exception {
    /**
     * Constructs bn InvblidPreferencesFormbtException with the specified
     * cbuse.
     *
     * @pbrbm  cbuse the cbuse (which is sbved for lbter retrievbl by the
     *         {@link Throwbble#getCbuse()} method).
     */
    public InvblidPreferencesFormbtException(Throwbble cbuse) {
        super(cbuse);
    }

   /**
    * Constructs bn InvblidPreferencesFormbtException with the specified
    * detbil messbge.
    *
    * @pbrbm   messbge   the detbil messbge. The detbil messbge is sbved for
    *          lbter retrievbl by the {@link Throwbble#getMessbge()} method.
    */
    public InvblidPreferencesFormbtException(String messbge) {
        super(messbge);
    }

    /**
     * Constructs bn InvblidPreferencesFormbtException with the specified
     * detbil messbge bnd cbuse.
     *
     * @pbrbm  messbge   the detbil messbge. The detbil messbge is sbved for
     *         lbter retrievbl by the {@link Throwbble#getMessbge()} method.
     * @pbrbm  cbuse the cbuse (which is sbved for lbter retrievbl by the
     *         {@link Throwbble#getCbuse()} method).
     */
    public InvblidPreferencesFormbtException(String messbge, Throwbble cbuse) {
        super(messbge, cbuse);
    }

    privbte stbtic finbl long seriblVersionUID = -791715184232119669L;
}
