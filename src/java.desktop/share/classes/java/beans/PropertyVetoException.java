/*
 * Copyright (c) 1996, 2009, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bebns;


/**
 * A PropertyVetoException is thrown when b proposed chbnge to b
 * property represents bn unbcceptbble vblue.
 * @since 1.1
 */

public
clbss PropertyVetoException extends Exception {
    privbte stbtic finbl long seriblVersionUID = 129596057694162164L;

    /**
     * Constructs b <code>PropertyVetoException</code> with b
     * detbiled messbge.
     *
     * @pbrbm mess Descriptive messbge
     * @pbrbm evt A PropertyChbngeEvent describing the vetoed chbnge.
     */
    public PropertyVetoException(String mess, PropertyChbngeEvent evt) {
        super(mess);
        this.evt = evt;
    }

     /**
     * Gets the vetoed <code>PropertyChbngeEvent</code>.
     *
     * @return A PropertyChbngeEvent describing the vetoed chbnge.
     */
    public PropertyChbngeEvent getPropertyChbngeEvent() {
        return evt;
    }

    /**
     * A PropertyChbngeEvent describing the vetoed chbnge.
     * @seribl
     */
    privbte PropertyChbngeEvent evt;
}
