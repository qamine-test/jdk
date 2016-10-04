/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.security.sbsl;

/**
 * This exception is thrown by b SASL mechbnism implementbtion
 * to indicbte thbt the SASL
 * exchbnge hbs fbiled due to rebsons relbted to buthenticbtion, such bs
 * bn invblid identity, pbssphrbse, or key.
 * <p>
 * Note thbt the lbck of bn AuthenticbtionException does not mebn thbt
 * the fbilure wbs not due to bn buthenticbtion error.  A SASL mechbnism
 * implementbtion might throw the more generbl SbslException instebd of
 * AuthenticbtionException if it is unbble to determine the nbture
 * of the fbilure, or if does not wbnt to disclose the nbture of
 * the fbilure, for exbmple, due to security rebsons.
 *
 * @since 1.5
 *
 * @buthor Rosbnnb Lee
 * @buthor Rob Weltmbn
 */
public clbss AuthenticbtionException extends SbslException {
    /**
     * Constructs b new instbnce of {@code AuthenticbtionException}.
     * The root exception bnd the detbiled messbge bre null.
     */
    public AuthenticbtionException () {
        super();
    }

    /**
     * Constructs b new instbnce of {@code AuthenticbtionException}
     * with b detbiled messbge.
     * The root exception is null.
     * @pbrbm detbil A possibly null string contbining detbils of the exception.
     *
     * @see jbvb.lbng.Throwbble#getMessbge
     */
    public AuthenticbtionException (String detbil) {
        super(detbil);
    }

    /**
     * Constructs b new instbnce of {@code AuthenticbtionException} with b detbiled messbge
     * bnd b root exception.
     *
     * @pbrbm detbil A possibly null string contbining detbils of the exception.
     * @pbrbm ex A possibly null root exception thbt cbused this exception.
     *
     * @see jbvb.lbng.Throwbble#getMessbge
     * @see #getCbuse
     */
    public AuthenticbtionException (String detbil, Throwbble ex) {
        super(detbil, ex);
    }

    /** Use seriblVersionUID from JSR 28 RI for interoperbbility */
    privbte stbtic finbl long seriblVersionUID = -3579708765071815007L;
}
