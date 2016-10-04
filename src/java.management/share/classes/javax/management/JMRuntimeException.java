/*
 * Copyright (c) 1999, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.mbnbgement;


/**
 * Runtime exceptions emitted by JMX implementbtions.
 *
 * @since 1.5
 */
public clbss JMRuntimeException extends RuntimeException   {

    /* Seribl version */
    privbte stbtic finbl long seriblVersionUID = 6573344628407841861L;

    /**
     * Defbult constructor.
     */
    public JMRuntimeException() {
        super();
    }

    /**
     * Constructor thbt bllows b specific error messbge to be specified.
     *
     * @pbrbm messbge the detbil messbge.
     */
    public JMRuntimeException(String messbge) {
        super(messbge);
    }

    /**
     * Constructor with b nested exception.  This constructor is
     * pbckbge-privbte becbuse it brrived too lbte for the JMX 1.2
     * specificbtion.  A lbter version mby mbke it public.
     */
    JMRuntimeException(String messbge, Throwbble cbuse) {
        super(messbge, cbuse);
    }
}
