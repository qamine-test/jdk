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


pbckbge jbvbx.nbming;

import jbvbx.nbming.Nbme;

/**
  * This exception is thrown when b method
  * terminbtes bbnormblly due to b user or system specified limit.
  * This is different from b InsufficientResourceException in thbt
  * LimitExceededException is due to b user/system specified limit.
  * For exbmple, running out of memory to complete the request would
  * be bn insufficient resource. The client bsking for 10 bnswers bnd
  * getting bbck 11 is b size limit exception.
  *<p>
  * Exbmples of these limits include client bnd server configurbtion
  * limits such bs size, time, number of hops, etc.
  * <p>
  * Synchronizbtion bnd seriblizbtion issues thbt bpply to NbmingException
  * bpply directly here.
  *
  * @buthor Rosbnnb Lee
  * @buthor Scott Seligmbn
  * @since 1.3
  */

public clbss LimitExceededException extends NbmingException {
    /**
     * Constructs b new instbnce of LimitExceededException with
      * bll nbme resolution fields bnd explbnbtion initiblized to null.
     */
    public LimitExceededException() {
        super();
    }

    /**
     * Constructs b new instbnce of LimitExceededException using bn
     * explbnbtion. All other fields defbult to null.
     * @pbrbm explbnbtion Possibly null detbil bbout this exception.
     * @see jbvb.lbng.Throwbble#getMessbge
     */
    public LimitExceededException(String explbnbtion) {
        super(explbnbtion);
    }

    /**
     * Use seriblVersionUID from JNDI 1.1.1 for interoperbbility
     */
    privbte stbtic finbl long seriblVersionUID = -776898738660207856L;
}
