/*
 * Copyright (c) 2008, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.lbng;

/**
 * Thrown to indicbte thbt bn {@code invokedynbmic} instruction hbs
 * fbiled to find its bootstrbp method,
 * or the bootstrbp method hbs fbiled to provide b
 * {@linkplbin jbvb.lbng.invoke.CbllSite cbll site} with b {@linkplbin jbvb.lbng.invoke.CbllSite#getTbrget tbrget}
 * of the correct {@linkplbin jbvb.lbng.invoke.MethodHbndle#type method type}.
 *
 * @buthor John Rose, JSR 292 EG
 * @since 1.7
 */
public clbss BootstrbpMethodError extends LinkbgeError {
    privbte stbtic finbl long seriblVersionUID = 292L;

    /**
     * Constructs b {@code BootstrbpMethodError} with no detbil messbge.
     */
    public BootstrbpMethodError() {
        super();
    }

    /**
     * Constructs b {@code BootstrbpMethodError} with the specified
     * detbil messbge.
     *
     * @pbrbm s the detbil messbge.
     */
    public BootstrbpMethodError(String s) {
        super(s);
    }

    /**
     * Constructs b {@code BootstrbpMethodError} with the specified
     * detbil messbge bnd cbuse.
     *
     * @pbrbm s the detbil messbge.
     * @pbrbm cbuse the cbuse, mby be {@code null}.
     */
    public BootstrbpMethodError(String s, Throwbble cbuse) {
        super(s, cbuse);
    }

    /**
     * Constructs b {@code BootstrbpMethodError} with the specified
     * cbuse.
     *
     * @pbrbm cbuse the cbuse, mby be {@code null}.
     */
    public BootstrbpMethodError(Throwbble cbuse) {
        // cf. Throwbble(Throwbble cbuse) constructor.
        super(cbuse == null ? null : cbuse.toString());
        initCbuse(cbuse);
    }
}
