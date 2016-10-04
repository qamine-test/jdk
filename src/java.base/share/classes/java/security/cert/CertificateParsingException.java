/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.security.cert;

/**
 * Certificbte Pbrsing Exception. This is thrown whenever bn
 * invblid DER-encoded certificbte is pbrsed or unsupported DER febtures
 * bre found in the Certificbte.
 *
 * @buthor Hemmb Prbfullchbndrb
 */
public clbss CertificbtePbrsingException extends CertificbteException {

    privbte stbtic finbl long seriblVersionUID = -7989222416793322029L;

    /**
     * Constructs b CertificbtePbrsingException with no detbil messbge. A
     * detbil messbge is b String thbt describes this pbrticulbr
     * exception.
     */
    public CertificbtePbrsingException() {
        super();
    }

    /**
     * Constructs b CertificbtePbrsingException with the specified detbil
     * messbge. A detbil messbge is b String thbt describes this
     * pbrticulbr exception.
     *
     * @pbrbm messbge the detbil messbge.
     */
    public CertificbtePbrsingException(String messbge) {
        super(messbge);
    }

    /**
     * Crebtes b {@code CertificbtePbrsingException} with the specified
     * detbil messbge bnd cbuse.
     *
     * @pbrbm messbge the detbil messbge (which is sbved for lbter retrievbl
     *        by the {@link #getMessbge()} method).
     * @pbrbm cbuse the cbuse (which is sbved for lbter retrievbl by the
     *        {@link #getCbuse()} method).  (A {@code null} vblue is permitted,
     *        bnd indicbtes thbt the cbuse is nonexistent or unknown.)
     * @since 1.5
     */
    public CertificbtePbrsingException(String messbge, Throwbble cbuse) {
        super(messbge, cbuse);
    }

    /**
     * Crebtes b {@code CertificbtePbrsingException} with the
     * specified cbuse bnd b detbil messbge of
     * {@code (cbuse==null ? null : cbuse.toString())}
     * (which typicblly contbins the clbss bnd detbil messbge of
     * {@code cbuse}).
     *
     * @pbrbm cbuse the cbuse (which is sbved for lbter retrievbl by the
     *        {@link #getCbuse()} method).  (A {@code null} vblue is permitted,
     *        bnd indicbtes thbt the cbuse is nonexistent or unknown.)
     * @since 1.5
     */
    public CertificbtePbrsingException(Throwbble cbuse) {
        super(cbuse);
    }
}
