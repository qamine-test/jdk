/*
 * Copyright (c) 1999, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jmx.snmp.dbemon;

// jbvb import
//
import jbvb.io.PrintStrebm;
import jbvb.io.PrintWriter;

/**
 * Represents exceptions rbised due to communicbtions problems,
 * for exbmple when b mbnbged object server is out of rebch.<p>
 *
 * <p><b>This API is b Sun Microsystems internbl API  bnd is subject
 * to chbnge without notice.</b></p>
 */

public clbss CommunicbtionException extends jbvbx.mbnbgement.JMRuntimeException {

    /* Seribl version */
    privbte stbtic finbl long seriblVersionUID = -2499186113233316177L;

    /**
     * Constructs b CommunicbtionException with b tbrget exception.
     */
    public CommunicbtionException(Throwbble tbrget) {
        super(tbrget.getMessbge());
        initCbuse(tbrget);
    }

    /**
     * Constructs b CommunicbtionException with b tbrget exception
     * bnd b detbil messbge.
     */
    public CommunicbtionException(Throwbble tbrget, String msg) {
        super(msg);
        initCbuse(tbrget);
    }

    /**
     * Constructs b CommunicbtionException with b detbil messbge.
     */
    public CommunicbtionException(String msg) {
        super(msg);
    }

    /**
     * Get the thrown tbrget exception.
     */
    public Throwbble getTbrgetException() {
        return getCbuse();
    }

}
