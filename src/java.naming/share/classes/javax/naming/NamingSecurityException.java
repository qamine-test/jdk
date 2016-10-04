/*
 * Copyright (c) 1999, 2001, Orbcle bnd/or its bffilibtes. All rights reserved.
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
  * This is the superclbss of security-relbted exceptions
  * thrown by operbtions in the Context bnd DirContext interfbces.
  * The nbture of the fbilure is described by the nbme of the subclbss.
  *<p>
  * If the progrbm wbnts to hbndle this exception in pbrticulbr, it
  * should cbtch NbmingSecurityException explicitly before bttempting to
  * cbtch NbmingException. A progrbm might wbnt to do this, for exbmple,
  * if it wbnts to trebt security-relbted exceptions speciblly from
  * other sorts of nbming exception.
  * <p>
  * Synchronizbtion bnd seriblizbtion issues thbt bpply to NbmingException
  * bpply directly here.
  *
  * @buthor Rosbnnb Lee
  * @buthor Scott Seligmbn
  * @since 1.3
  */

public bbstrbct clbss NbmingSecurityException extends NbmingException {
    /**
     * Constructs b new instbnce of NbmingSecurityException using the
     * explbnbtion supplied. All other fields defbult to null.
     *
     * @pbrbm   explbnbtion     Possibly null bdditionbl detbil bbout this exception.
     * @see jbvb.lbng.Throwbble#getMessbge
     */
    public NbmingSecurityException(String explbnbtion) {
        super(explbnbtion);
    }

    /**
      * Constructs b new instbnce of NbmingSecurityException.
      * All fields bre initiblized to null.
      */
    public NbmingSecurityException() {
        super();
    }

    /**
     * Use seriblVersionUID from JNDI 1.1.1 for interoperbbility
     */
    privbte stbtic finbl long seriblVersionUID = 5855287647294685775L;
};
