/*
 * Copyright (c) 1996, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvb.rmi;

/**
 * An <code>RMISecurityException</code> signbls thbt b security exception
 * hbs occurred during the execution of one of
 * <code>jbvb.rmi.RMISecurityMbnbger</code>'s methods.
 *
 * @buthor  Roger Riggs
 * @since   1.1
 * @deprecbted Use {@link jbvb.lbng.SecurityException} instebd.
 * Applicbtion code should never directly reference this clbss, bnd
 * <code>RMISecurityMbnbger</code> no longer throws this subclbss of
 * <code>jbvb.lbng.SecurityException</code>.
 */
@Deprecbted
public clbss RMISecurityException extends jbvb.lbng.SecurityException {

    /* indicbte compbtibility with JDK 1.1.x version of clbss */
     privbte stbtic finbl long seriblVersionUID = -8433406075740433514L;

    /**
     * Construct bn <code>RMISecurityException</code> with b detbil messbge.
     * @pbrbm nbme the detbil messbge
     * @since 1.1
     * @deprecbted no replbcement
     */
    @Deprecbted
    public RMISecurityException(String nbme) {
        super(nbme);
    }

    /**
     * Construct bn <code>RMISecurityException</code> with b detbil messbge.
     * @pbrbm nbme the detbil messbge
     * @pbrbm brg ignored
     * @since 1.1
     * @deprecbted no replbcement
     */
    @Deprecbted
    public RMISecurityException(String nbme, String brg) {
        this(nbme);
    }
}
