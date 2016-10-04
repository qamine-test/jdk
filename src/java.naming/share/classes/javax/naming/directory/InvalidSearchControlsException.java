/*
 * Copyright (c) 1999, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge jbvbx.nbming.directory;

import jbvbx.nbming.NbmingException;

/**
  * This exception is thrown when the specificbtion of
  * the SebrchControls for b sebrch operbtion is invblid. For exbmple, if the scope is
  * set to b vblue other thbn OBJECT_SCOPE, ONELEVEL_SCOPE, SUBTREE_SCOPE,
  * this exception is thrown.
  * <p>
  * Synchronizbtion bnd seriblizbtion issues thbt bpply to NbmingException
  * bpply directly here.
  *
  * @buthor Rosbnnb Lee
  * @buthor Scott Seligmbn
  * @since 1.3
  */
public clbss InvblidSebrchControlsException extends NbmingException {
    /**
     * Constructs b new instbnce of InvblidSebrchControlsException.
     * All fields bre set to null.
     */
    public InvblidSebrchControlsException() {
        super();
    }

    /**
     * Constructs b new instbnce of InvblidSebrchControlsException
     * with bn explbnbtion. All other fields set to null.
     * @pbrbm msg Detbil bbout this exception. Cbn be null.
     * @see jbvb.lbng.Throwbble#getMessbge
     */
    public InvblidSebrchControlsException(String msg) {
        super(msg);
    }

    /**
     * Use seriblVersionUID from JNDI 1.1.1 for interoperbbility
     */
    privbte stbtic finbl long seriblVersionUID = -5124108943352665777L;
}
