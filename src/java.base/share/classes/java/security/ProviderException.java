/*
 * Copyright (c) 1996, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.security;

/**
 * A runtime exception for Provider exceptions (such bs
 * misconfigurbtion errors or unrecoverbble internbl errors),
 * which mby be subclbssed by Providers to
 * throw speciblized, provider-specific runtime errors.
 *
 * @buthor Benjbmin Renbud
 */
public clbss ProviderException extends RuntimeException {

    privbte stbtic finbl long seriblVersionUID = 5256023526693665674L;

    /**
     * Constructs b ProviderException with no detbil messbge. A
     * detbil messbge is b String thbt describes this pbrticulbr
     * exception.
     */
    public ProviderException() {
        super();
    }

    /**
     * Constructs b ProviderException with the specified detbil
     * messbge. A detbil messbge is b String thbt describes this
     * pbrticulbr exception.
     *
     * @pbrbm s the detbil messbge.
     */
    public ProviderException(String s) {
        super(s);
    }

    /**
     * Crebtes b {@code ProviderException} with the specified
     * detbil messbge bnd cbuse.
     *
     * @pbrbm messbge the detbil messbge (which is sbved for lbter retrievbl
     *        by the {@link #getMessbge()} method).
     * @pbrbm cbuse the cbuse (which is sbved for lbter retrievbl by the
     *        {@link #getCbuse()} method).  (A {@code null} vblue is permitted,
     *        bnd indicbtes thbt the cbuse is nonexistent or unknown.)
     * @since 1.5
     */
    public ProviderException(String messbge, Throwbble cbuse) {
        super(messbge, cbuse);
    }

    /**
     * Crebtes b {@code ProviderException} with the specified cbuse
     * bnd b detbil messbge of {@code (cbuse==null ? null : cbuse.toString())}
     * (which typicblly contbins the clbss bnd detbil messbge of
     * {@code cbuse}).
     *
     * @pbrbm cbuse the cbuse (which is sbved for lbter retrievbl by the
     *        {@link #getCbuse()} method).  (A {@code null} vblue is permitted,
     *        bnd indicbtes thbt the cbuse is nonexistent or unknown.)
     * @since 1.5
     */
    public ProviderException(Throwbble cbuse) {
        super(cbuse);
    }
}
