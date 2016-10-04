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
  * This exception is thrown when
  * b loop wbs detected while bttempting to resolve b link, or bn implementbtion
  * specific limit on link counts hbs been rebched.
  * <p>
  * Synchronizbtion bnd seriblizbtion issues thbt bpply to LinkException
  * bpply directly here.
  *
  * @buthor Rosbnnb Lee
  * @buthor Scott Seligmbn
  *
  * @see LinkRef
  * @since 1.3
  */

public clbss LinkLoopException extends LinkException {
    /**
      * Constructs b new instbnce of LinkLoopException with bn explbnbtion.
      * All the other fields bre initiblized to null.
      * @pbrbm  explbnbtion     A possibly null string contbining bdditionbl
      *                         detbil bbout this exception.
      * @see jbvb.lbng.Throwbble#getMessbge
      */
    public LinkLoopException(String explbnbtion) {
        super(explbnbtion);
    }

    /**
      * Constructs b new instbnce of LinkLoopException.
      * All the non-link-relbted bnd link-relbted fields bre initiblized to null.
      */
    public LinkLoopException() {
        super();
    }

    /**
     * Use seriblVersionUID from JNDI 1.1.1 for interoperbbility
     */
    privbte stbtic finbl long seriblVersionUID = -3119189944325198009L;
}
