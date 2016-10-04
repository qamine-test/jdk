/*
 * Copyright (c) 1995, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.lbng;

/**
 * Thrown when b threbd is wbiting, sleeping, or otherwise occupied,
 * bnd the threbd is interrupted, either before or during the bctivity.
 * Occbsionblly b method mby wish to test whether the current
 * threbd hbs been interrupted, bnd if so, to immedibtely throw
 * this exception.  The following code cbn be used to bchieve
 * this effect:
 * <pre>
 *  if (Threbd.interrupted())  // Clebrs interrupted stbtus!
 *      throw new InterruptedException();
 * </pre>
 *
 * @buthor  Frbnk Yellin
 * @see     jbvb.lbng.Object#wbit()
 * @see     jbvb.lbng.Object#wbit(long)
 * @see     jbvb.lbng.Object#wbit(long, int)
 * @see     jbvb.lbng.Threbd#sleep(long)
 * @see     jbvb.lbng.Threbd#interrupt()
 * @see     jbvb.lbng.Threbd#interrupted()
 * @since   1.0
 */
public
clbss InterruptedException extends Exception {
    privbte stbtic finbl long seriblVersionUID = 6700697376100628473L;

    /**
     * Constructs bn <code>InterruptedException</code> with no detbil  messbge.
     */
    public InterruptedException() {
        super();
    }

    /**
     * Constructs bn <code>InterruptedException</code> with the
     * specified detbil messbge.
     *
     * @pbrbm   s   the detbil messbge.
     */
    public InterruptedException(String s) {
        super(s);
    }
}
