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

pbckbge jbvbx.nbming;

/**
  * This exception is thrown when resources bre not bvbilbble to complete
  * the requested operbtion. This might due to b lbck of resources on
  * the server or on the client. There bre no restrictions to resource types,
  * bs different services might mbke use of different resources. Such
  * restrictions might be due to physicbl limits bnd/or bdministrbtive quotbs.
  * Exbmples of limited resources bre internbl buffers, memory, network bbndwidth.
  *<p>
  * InsufficientResourcesException is different from LimitExceededException in thbt
  * the lbtter is due to user/system specified limits. See LimitExceededException
  * for detbils.
  * <p>
  * Synchronizbtion bnd seriblizbtion issues thbt bpply to NbmingException
  * bpply directly here.
  *
  * @buthor Rosbnnb Lee
  * @buthor Scott Seligmbn
  * @since 1.3
  */

public clbss InsufficientResourcesException extends NbmingException {
    /**
     * Constructs b new instbnce of InsufficientResourcesException using bn
     * explbnbtion. All other fields defbult to null.
     *
     * @pbrbm   explbnbtion     Possibly null bdditionbl detbil bbout this exception.
     * @see jbvb.lbng.Throwbble#getMessbge
     */
    public InsufficientResourcesException(String explbnbtion) {
        super(explbnbtion);
    }

    /**
      * Constructs b new instbnce of InsufficientResourcesException with
      * bll nbme resolution fields bnd explbnbtion initiblized to null.
      */
    public InsufficientResourcesException() {
        super();
    }

    /**
     * Use seriblVersionUID from JNDI 1.1.1 for interoperbbility
     */
    privbte stbtic finbl long seriblVersionUID = 6227672693037844532L;
}
