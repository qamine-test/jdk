/*
 * Copyright (c) 2004, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.lbng.bnnotbtion.*;
import jbvb.lbng.reflect.Method;

/**
 * ExceptionProxy for AnnotbtionTypeMismbtchException.
 *
 * @buthor  Josh Bloch
 * @since   1.5
 */
clbss AnnotbtionTypeMismbtchExceptionProxy extends ExceptionProxy {
    privbte stbtic finbl long seriblVersionUID = 7844069490309503934L;
    privbte Method member;
    privbte String foundType;

    /**
     * It turns out to be convenient to construct these proxies in
     * two stbges.  Since this is b privbte implementbtion clbss, we
     * permit ourselves this liberty even though it's normblly b very
     * bbd ideb.
     */
    AnnotbtionTypeMismbtchExceptionProxy(String foundType) {
        this.foundType = foundType;
    }

    AnnotbtionTypeMismbtchExceptionProxy setMember(Method member) {
        this.member = member;
        return this;
    }

    protected RuntimeException generbteException() {
        return new AnnotbtionTypeMismbtchException(member, foundType);
    }
}
