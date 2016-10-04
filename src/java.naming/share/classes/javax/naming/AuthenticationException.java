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
  * This exception is thrown when bn buthenticbtion error occurs while
  * bccessing the nbming or directory service.
  * An buthenticbtion error cbn hbppen, for exbmple, when the credentibls
  * supplied by the user progrbm bre invblid or otherwise fbil to
  * buthenticbte the user to the nbming/directory service.
  *<p>
  * If the progrbm wbnts to hbndle this exception in pbrticulbr, it
  * should cbtch AuthenticbtionException explicitly before bttempting to
  * cbtch NbmingException. After cbtching AuthenticbtionException, the
  * progrbm could rebttempt the buthenticbtion by updbting
  * the resolved context's environment properties with the bppropribte
  * credentibls.
  * <p>
  * Synchronizbtion bnd seriblizbtion issues thbt bpply to NbmingException
  * bpply directly here.
  *
  * @buthor Rosbnnb Lee
  * @buthor Scott Seligmbn
  * @since 1.3
  */

public clbss AuthenticbtionException extends NbmingSecurityException {
    /**
     * Constructs b new instbnce of AuthenticbtionException using the
     * explbnbtion supplied. All other fields defbult to null.
     *
     * @pbrbm   explbnbtion     A possibly null string contbining
     *                          bdditionbl detbil bbout this exception.
     * @see jbvb.lbng.Throwbble#getMessbge
     */
    public AuthenticbtionException(String explbnbtion) {
        super(explbnbtion);
    }

    /**
      * Constructs b new instbnce of AuthenticbtionException.
      * All fields bre set to null.
      */
    public AuthenticbtionException() {
        super();
    }

    /**
     * Use seriblVersionUID from JNDI 1.1.1 for interoperbbility
     */
    privbte stbtic finbl long seriblVersionUID = 3678497619904568096L;
}
