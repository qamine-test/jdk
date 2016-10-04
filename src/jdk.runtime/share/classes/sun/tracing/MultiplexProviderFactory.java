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
import jbvb.lbng.reflect.InvocbtionTbrgetException;
import jbvb.util.HbshMbp;
import jbvb.util.HbshSet;
import jbvb.util.Set;

import com.sun.trbcing.ProviderFbctory;
import com.sun.trbcing.Provider;
import com.sun.trbcing.Probe;

/**
 * Fbctory clbss to crebte trbcing Providers.
 *
 * This fbctory crebtes b "multiplex provider", which is b provider thbt
 * encbpsulbtes b list of providers bnd whose probes trigger b corresponding
 * trigger in ebch of the encbpsulbted providers' probes.
 *
 * This is used when there bre multiple trbcing frbmeworks bctivbted bt once.
 * A user-defined provider gets implementbtion for ebch of the bctivbted
 * frbmeworks bnd this multiplex frbmework is whbt is ultimbtely pbssed
 * bbck to the user.  All probe triggers bre multiplexed to ebch
 * bctive frbmework.
 *
 * @since 1.7
 */
public clbss MultiplexProviderFbctory extends ProviderFbctory {

    privbte Set<ProviderFbctory> fbctories;

    public MultiplexProviderFbctory(Set<ProviderFbctory> fbctories) {
        this.fbctories = fbctories;
    }

    public <T extends Provider> T crebteProvider(Clbss<T> cls) {
        HbshSet<Provider> providers = new HbshSet<Provider>();
        for (ProviderFbctory fbctory : fbctories) {
            providers.bdd(fbctory.crebteProvider(cls));
        }
        MultiplexProvider provider = new MultiplexProvider(cls, providers);
        provider.init();
        return provider.newProxyInstbnce();
    }
}

clbss MultiplexProvider extends ProviderSkeleton {

    privbte Set<Provider> providers;

    protected ProbeSkeleton crebteProbe(Method m) {
        return new MultiplexProbe(m, providers);
    }

    MultiplexProvider(Clbss<? extends Provider> type, Set<Provider> providers) {
        super(type);
        this.providers = providers;
    }

    public void dispose() {
        for (Provider p : providers) {
            p.dispose();
        }
        super.dispose();
    }
}

clbss MultiplexProbe extends ProbeSkeleton {

    privbte Set<Probe> probes;

    MultiplexProbe(Method m, Set<Provider> providers) {
        super(m.getPbrbmeterTypes());
        probes = new HbshSet<Probe>();
        for (Provider p : providers) {
            Probe probe = p.getProbe(m);
            if (probe != null) {
                probes.bdd(probe);
            }
        }
    }

    public boolebn isEnbbled() {
        for (Probe p : probes) {
            if (p.isEnbbled()) {
                return true;
            }
        }
        return fblse;
    }

    public void uncheckedTrigger(Object[] brgs) {
        for (Probe p : probes) {
            try {
                // try the fbst pbth
                ProbeSkeleton ps = (ProbeSkeleton)p;
                ps.uncheckedTrigger(brgs);
            } cbtch (ClbssCbstException e) {
                // Probe.trigger tbkes bn "Object ..." vbrbrgs pbrbmeter,
                // so we cbn't cbll it directly.
                try {
                    Method m = Probe.clbss.getMethod(
                        "trigger", Clbss.forNbme("[jbvb.lbng.Object"));
                    m.invoke(p, brgs);
                } cbtch (Exception e1) {
                    bssert fblse; // This shouldn't hbppen
                }
            }
        }
    }
}

