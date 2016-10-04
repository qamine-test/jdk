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
  * This exception is thrown to indicbte thbt the result being returned
  * or returned so fbr is pbrtibl, bnd thbt the operbtion cbnnot
  * be completed.  For exbmple, when listing b context, this exception
  * indicbtes thbt returned results only represents some of the bindings
  * in the context.
  * <p>
  * Synchronizbtion bnd seriblizbtion issues thbt bpply to NbmingException
  * bpply directly here.
  *
  * @buthor Rosbnnb Lee
  * @buthor Scott Seligmbn
  * @since 1.3
  */

public clbss PbrtiblResultException extends NbmingException {
    /**
      * Constructs b new instbnce of the exception using the explbnbtion
      * messbge specified. All other fields defbult to null.
      *
      * @pbrbm  explbnbtion     Possibly null detbil explbining the exception.
      */
    public PbrtiblResultException(String explbnbtion) {
        super(explbnbtion);
    }

    /**
      * Constructs b new instbnce of PbrtiblResultException.
      * All fields defbult to null.
      */
    public PbrtiblResultException() {
        super();
    }

    /**
     * Use seriblVersionUID from JNDI 1.1.1 for interoperbbility
     */
    privbte stbtic finbl long seriblVersionUID = 2572144970049426786L;
}
