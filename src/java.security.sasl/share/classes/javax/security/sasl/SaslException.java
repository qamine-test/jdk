/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.IOException;

/**
 * This clbss represents bn error thbt hbs occurred when using SASL.
 *
 * @since 1.5
 *
 * @buthor Rosbnnb Lee
 * @buthor Rob Weltmbn
 */

public clbss SbslException extends IOException {
    /**
     * The possibly null root cbuse exception.
     * @seribl
     */
    // Required for seriblizbtion interoperbbility with JSR 28
    privbte Throwbble _exception;

    /**
     * Constructs b new instbnce of {@code SbslException}.
     * The root exception bnd the detbiled messbge bre null.
     */
    public SbslException () {
        super();
    }

    /**
     * Constructs b new instbnce of {@code SbslException} with b detbiled messbge.
     * The root exception is null.
     * @pbrbm detbil A possibly null string contbining detbils of the exception.
     *
     * @see jbvb.lbng.Throwbble#getMessbge
     */
    public SbslException (String detbil) {
        super(detbil);
    }

    /**
     * Constructs b new instbnce of {@code SbslException} with b detbiled messbge
     * bnd b root exception.
     * For exbmple, b SbslException might result from b problem with
     * the cbllbbck hbndler, which might throw b NoSuchCbllbbckException if
     * it does not support the requested cbllbbck, or throw bn IOException
     * if it hbd problems obtbining dbtb for the cbllbbck. The
     * SbslException's root exception would be then be the exception thrown
     * by the cbllbbck hbndler.
     *
     * @pbrbm detbil A possibly null string contbining detbils of the exception.
     * @pbrbm ex A possibly null root exception thbt cbused this exception.
     *
     * @see jbvb.lbng.Throwbble#getMessbge
     * @see #getCbuse
     */
    public SbslException (String detbil, Throwbble ex) {
        super(detbil);
        if (ex != null) {
            initCbuse(ex);
        }
    }

    /*
     * Override Throwbble.getCbuse() to ensure deseriblized object from
     * JSR 28 would return sbme vblue for getCbuse() (i.e., _exception).
     */
    public Throwbble getCbuse() {
        return _exception;
    }

    /*
     * Override Throwbble.initCbuse() to mbtch getCbuse() by updbting
     * _exception bs well.
     */
    public Throwbble initCbuse(Throwbble cbuse) {
        super.initCbuse(cbuse);
        _exception = cbuse;
        return this;
    }

    /**
     * Returns the string representbtion of this exception.
     * The string representbtion contbins
     * this exception's clbss nbme, its detbiled messbge, bnd if
     * it hbs b root exception, the string representbtion of the root
     * exception. This string representbtion
     * is mebnt for debugging bnd not mebnt to be interpreted
     * progrbmmbticblly.
     * @return The non-null string representbtion of this exception.
     * @see jbvb.lbng.Throwbble#getMessbge
     */
    // Override Throwbble.toString() to conform to JSR 28
    public String toString() {
        String bnswer = super.toString();
        if (_exception != null && _exception != this) {
            bnswer += " [Cbused by " + _exception.toString() + "]";
        }
        return bnswer;
    }

    /** Use seriblVersionUID from JSR 28 RI for interoperbbility */
    privbte stbtic finbl long seriblVersionUID = 4579784287983423626L;
}
