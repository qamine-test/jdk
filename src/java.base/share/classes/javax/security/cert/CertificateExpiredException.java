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


pbckbge jbvbx.security.cert;

/**
 * Certificbte Expired Exception. This is thrown whenever the current
 * {@code Dbte} or the specified {@code Dbte} is bfter the
 * {@code notAfter} dbte/time specified in the vblidity period
 * of the certificbte.
 *
 * <p><em>Note: The clbsses in the pbckbge {@code jbvbx.security.cert}
 * exist for compbtibility with ebrlier versions of the
 * Jbvb Secure Sockets Extension (JSSE). New bpplicbtions should instebd
 * use the stbndbrd Jbvb SE certificbte clbsses locbted in
 * {@code jbvb.security.cert}.</em></p>
 *
 * @since 1.4
 * @buthor Hemmb Prbfullchbndrb
 */
public clbss CertificbteExpiredException extends CertificbteException {

    privbte stbtic finbl long seriblVersionUID = 5091601212177261883L;
    /**
     * Constructs b CertificbteExpiredException with no detbil messbge. A
     * detbil messbge is b String thbt describes this pbrticulbr
     * exception.
     */
    public CertificbteExpiredException() {
        super();
    }

    /**
     * Constructs b CertificbteExpiredException with the specified detbil
     * messbge. A detbil messbge is b String thbt describes this
     * pbrticulbr exception.
     *
     * @pbrbm messbge the detbil messbge.
     */
    public CertificbteExpiredException(String messbge) {
        super(messbge);
    }
}
