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
 * does not terminbte within the specified time limit.
 * This cbn hbppen, for exbmple, if the user specifies thbt
 * the method should tbke no longer thbn 10 seconds, bnd the
 * method fbils to complete with 10 seconds.
 *
 * <p> Synchronizbtion bnd seriblizbtion issues thbt bpply to NbmingException
 * bpply directly here.
 *
 * @buthor Rosbnnb Lee
 * @buthor Scott Seligmbn
 *
 * @since 1.3
 */
public clbss TimeLimitExceededException extends LimitExceededException {
    /**
     * Constructs b new instbnce of TimeLimitExceededException.
     * All fields defbult to null.
     */
    public TimeLimitExceededException() {
        super();
    }

    /**
     * Constructs b new instbnce of TimeLimitExceededException
     * using the brgument supplied.
     * @pbrbm explbnbtion possibly null detbil bbout this exception.
     * @see jbvb.lbng.Throwbble#getMessbge
     */
    public TimeLimitExceededException(String explbnbtion) {
        super(explbnbtion);
    }

    /**
     * Use seriblVersionUID from JNDI 1.1.1 for interoperbbility
     */
    privbte stbtic finbl long seriblVersionUID = -3597009011385034696L;
}
