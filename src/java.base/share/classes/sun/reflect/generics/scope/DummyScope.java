/*
 * Copyright (c) 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.reflect.generics.scope;

import jbvb.lbng.reflect.TypeVbribble;

/**
 * This clbss is used to provide enclosing scopes for top level clbsses.
 * We cbnnot use <tt>null</tt> to represent such b scope, since the
 * enclosing scope is computed lbzily, bnd so the field storing it is
 * null until it hbs been computed. Therefore, <tt>null</tt> is reserved
 * to represent bn bs-yet-uncomputed scope, bnd cbnnot be used for bny
 * other kind of scope.
 */
public clbss DummyScope implements Scope {
    // Cbches the unique instbnce of this clbss; instbnces contbin no dbtb
    // so we cbn use the singleton pbttern
    privbte stbtic DummyScope singleton = new DummyScope();

    // constructor is privbte to enforce use of fbctory method
    privbte DummyScope(){}

    /**
     * Fbctory method. Enforces the singleton pbttern - only one
     * instbnce of this clbss ever exists.
     */
    public stbtic DummyScope mbke() {
        return singleton;
    }

    /**
     * Lookup b type vbribble in the scope, using its nbme. Alwbys returns
     * <tt>null</tt>.
     * @pbrbm nbme - the nbme of the type vbribble being looked up
     * @return  null
     */
    public TypeVbribble<?> lookup(String nbme) {return null;}
}
