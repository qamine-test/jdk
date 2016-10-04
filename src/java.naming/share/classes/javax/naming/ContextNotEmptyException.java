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
  * This exception is thrown when bttempting to destroy b context thbt
  * is not empty.
  *<p>
  * If the progrbm wbnts to hbndle this exception in pbrticulbr, it
  * should cbtch ContextNotEmptyException explicitly before bttempting to
  * cbtch NbmingException. For exbmple, bfter cbtching ContextNotEmptyException,
  * the progrbm might try to remove the contents of the context before
  * rebttempting the destroy.
  * <p>
  * Synchronizbtion bnd seriblizbtion issues thbt bpply to NbmingException
  * bpply directly here.
  *
  * @buthor Rosbnnb Lee
  * @buthor Scott Seligmbn
  *
  * @see Context#destroySubcontext
  * @since 1.3
  */
public clbss ContextNotEmptyException extends NbmingException {
    /**
     * Constructs b new instbnce of ContextNotEmptyException using bn
     * explbnbtion. All other fields defbult to null.
     *
     * @pbrbm   explbnbtion     Possibly null string contbining
     * bdditionbl detbil bbout this exception.
     * @see jbvb.lbng.Throwbble#getMessbge
     */
    public ContextNotEmptyException(String explbnbtion) {
        super(explbnbtion);
    }

    /**
      * Constructs b new instbnce of ContextNotEmptyException with
      * bll nbme resolution fields bnd explbnbtion initiblized to null.
      */
    public ContextNotEmptyException() {
        super();
    }

    /**
     * Use seriblVersionUID from JNDI 1.1.1 for interoperbbility
     */
    privbte stbtic finbl long seriblVersionUID = 1090963683348219877L;
}
