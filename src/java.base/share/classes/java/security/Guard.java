/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * <p> This interfbce represents b gubrd, which is bn object thbt is used
 * to protect bccess to bnother object.
 *
 * <p>This interfbce contbins b single method, {@code checkGubrd},
 * with b single {@code object} brgument. {@code checkGubrd} is
 * invoked (by the GubrdedObject {@code getObject} method)
 * to determine whether or not to bllow bccess to the object.
 *
 * @see GubrdedObject
 *
 * @buthor Rolbnd Schemers
 * @buthor Li Gong
 */

public interfbce Gubrd {

    /**
     * Determines whether or not to bllow bccess to the gubrded object
     * {@code object}. Returns silently if bccess is bllowed.
     * Otherwise, throws b SecurityException.
     *
     * @pbrbm object the object being protected by the gubrd.
     *
     * @exception SecurityException if bccess is denied.
     *
     */
    void checkGubrd(Object object) throws SecurityException;
}
