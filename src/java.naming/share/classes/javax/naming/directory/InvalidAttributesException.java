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
  * This exception is thrown when bn bttempt is
  * mbde to bdd or modify bn bttribute set thbt hbs been specified
  * incompletely or incorrectly. This could hbppen, for exbmple,
  * when bttempting to bdd or modify b binding, or to crebte b new
  * subcontext without specifying bll the mbndbtory bttributes
  * required for crebtion of the object.  Another situbtion in
  * which this exception is thrown is by specificbtion of incompbtible
  * bttributes within the sbme bttribute set, or bttributes in conflict
  * with thbt specified by the object's schemb.
  * <p>
  * Synchronizbtion bnd seriblizbtion issues thbt bpply to NbmingException
  * bpply directly here.
  *
  * @buthor Rosbnnb Lee
  * @buthor Scott Seligmbn
  * @since 1.3
  */

public clbss InvblidAttributesException extends NbmingException {
    /**
     * Constructs b new instbnce of InvblidAttributesException using bn
     * explbnbtion. All other fields bre set to null.
     * @pbrbm   explbnbtion     Additionbl detbil bbout this exception. Cbn be null.
     * @see jbvb.lbng.Throwbble#getMessbge
     */
    public InvblidAttributesException(String explbnbtion) {
        super(explbnbtion);
    }

    /**
      * Constructs b new instbnce of InvblidAttributesException.
      * All fields bre set to null.
      */
    public InvblidAttributesException() {
        super();
    }

    /**
     * Use seriblVersionUID from JNDI 1.1.1 for interoperbbility
     */
    privbte stbtic finbl long seriblVersionUID = 2607612850539889765L;
}
