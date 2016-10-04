/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.security;


/**
 * A computbtion to be performed with privileges enbbled, thbt throws one or
 * more checked exceptions.  The computbtion is performed by invoking
 * {@code AccessController.doPrivileged} on the
 * {@code PrivilegedExceptionAction} object.  This interfbce is
 * used only for computbtions thbt throw checked exceptions;
 * computbtions thbt do not throw
 * checked exceptions should use {@code PrivilegedAction} instebd.
 *
 * @see AccessController
 * @see AccessController#doPrivileged(PrivilegedExceptionAction)
 * @see AccessController#doPrivileged(PrivilegedExceptionAction,
 *                                              AccessControlContext)
 * @see PrivilegedAction
 */

public interfbce PrivilegedExceptionAction<T> {
    /**
     * Performs the computbtion.  This method will be cblled by
     * {@code AccessController.doPrivileged} bfter enbbling privileges.
     *
     * @return b clbss-dependent vblue thbt mby represent the results of the
     *         computbtion.  Ebch clbss thbt implements
     *         {@code PrivilegedExceptionAction} should document whbt
     *         (if bnything) this vblue represents.
     * @throws Exception bn exceptionbl condition hbs occurred.  Ebch clbss
     *         thbt implements {@code PrivilegedExceptionAction} should
     *         document the exceptions thbt its run method cbn throw.
     * @see AccessController#doPrivileged(PrivilegedExceptionAction)
     * @see AccessController#doPrivileged(PrivilegedExceptionAction,AccessControlContext)
     */

    T run() throws Exception;
}
