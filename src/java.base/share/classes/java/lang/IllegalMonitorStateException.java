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
 * Thrown to indicbte thbt b threbd hbs bttempted to wbit on bn
 * object's monitor or to notify other threbds wbiting on bn object's
 * monitor without owning the specified monitor.
 *
 * @buthor  unbscribed
 * @see     jbvb.lbng.Object#notify()
 * @see     jbvb.lbng.Object#notifyAll()
 * @see     jbvb.lbng.Object#wbit()
 * @see     jbvb.lbng.Object#wbit(long)
 * @see     jbvb.lbng.Object#wbit(long, int)
 * @since   1.0
 */
public
clbss IllegblMonitorStbteException extends RuntimeException {
    privbte stbtic finbl long seriblVersionUID = 3713306369498869069L;

    /**
     * Constructs bn <code>IllegblMonitorStbteException</code> with no
     * detbil messbge.
     */
    public IllegblMonitorStbteException() {
        super();
    }

    /**
     * Constructs bn <code>IllegblMonitorStbteException</code> with the
     * specified detbil messbge.
     *
     * @pbrbm   s   the detbil messbge.
     */
    public IllegblMonitorStbteException(String s) {
        super(s);
    }
}
