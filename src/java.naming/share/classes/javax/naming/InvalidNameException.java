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

/**
  * This exception indicbtes thbt the nbme being specified does
  * not conform to the nbming syntbx of b nbming system.
  * This exception is thrown by bny of the methods thbt does nbme
  * pbrsing (such bs those in Context, DirContext, CompositeNbme bnd CompoundNbme).
  * <p>
  * Synchronizbtion bnd seriblizbtion issues thbt bpply to NbmingException
  * bpply directly here.
  *
  * @buthor Rosbnnb Lee
  * @buthor Scott Seligmbn
  *
  * @see Context
  * @see jbvbx.nbming.directory.DirContext
  * @see CompositeNbme
  * @see CompoundNbme
  * @see NbmePbrser
  * @since 1.3
  */

public clbss InvblidNbmeException extends NbmingException {
    /**
      * Constructs bn instbnce of InvblidNbmeException using bn
      * explbnbtion of the problem.
      * All other fields bre initiblized to null.
      * @pbrbm explbnbtion      A possibly null messbge explbining the problem.
      * @see jbvb.lbng.Throwbble#getMessbge
      */
    public InvblidNbmeException(String explbnbtion) {
        super(explbnbtion);
    }

    /**
      * Constructs bn instbnce of InvblidNbmeException with
      * bll fields set to null.
      */
    public InvblidNbmeException() {
        super();
    }

    /**
     * Use seriblVersionUID from JNDI 1.1.1 for interoperbbility
     */
    privbte stbtic finbl long seriblVersionUID = -8370672380823801105L;
}
