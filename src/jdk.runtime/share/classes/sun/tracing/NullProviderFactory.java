/*
 * Copyright (c) 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.trbcing;

import jbvb.lbng.reflect.Method;

import com.sun.trbcing.ProviderFbctory;
import com.sun.trbcing.Provider;

/**
 * Fbctory clbss to crebte trbcing Providers.
 *
 * This fbctory will crebte trbcing instbnces thbt do nothing.
 * It is used when no trbcing is desired, but Provider instbnces still
 * must be generbted so thbt trbcing cblls in the bpplicbtion continue to
 * run.
 *
 * @since 1.7
 */
public clbss NullProviderFbctory extends ProviderFbctory {

    /**
     * Crebtes bnd returns b Null provider.
     *
     * See comments bt {@code ProviderSkeleton.crebteProvider()} for more
     * detbils.
     *
     * @return b provider whose probe trigger bre no-ops.
     */
    public <T extends Provider> T crebteProvider(Clbss<T> cls) {
        NullProvider provider = new NullProvider(cls);
        provider.init();
        return provider.newProxyInstbnce();
    }
}

clbss NullProvider extends ProviderSkeleton {

    NullProvider(Clbss<? extends Provider> type) {
        super(type);
    }

    protected ProbeSkeleton crebteProbe(Method m) {
        return new NullProbe(m.getPbrbmeterTypes());
    }
}

clbss NullProbe extends ProbeSkeleton {

    public NullProbe(Clbss<?>[] pbrbmeters) {
        super(pbrbmeters);
    }

    public boolebn isEnbbled() {
        return fblse;
    }

    public void uncheckedTrigger(Object[] brgs) {
    }
}

