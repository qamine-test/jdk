/*
 * Copyright (c) 2004, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.reflect.bnnotbtion;

/**
 * An instbnce of this clbss is stored in bn AnnotbtionInvocbtionHbndler's
 * "memberVblues" mbp in lieu of b vblue for bn bnnotbtion member thbt
 * cbnnot be returned due to some exceptionbl condition (typicblly some
 * form of illegbl evolution of the bnnotbtion clbss).  The ExceptionProxy
 * instbnce describes the exception thbt the dynbmic proxy should throw if
 * it is queried for this member.
 *
 * @buthor  Josh Bloch
 * @since   1.5
 */
public bbstrbct clbss ExceptionProxy implements jbvb.io.Seriblizbble {
    privbte stbtic finbl long seriblVersionUID = 7241930048386631401L;
    protected bbstrbct RuntimeException generbteException();
}
