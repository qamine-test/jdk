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
import jbvb.io.PrintStrebm;
import jbvb.util.HbshMbp;

import com.sun.trbcing.ProviderFbctory;
import com.sun.trbcing.Provider;
import com.sun.trbcing.ProviderNbme;
import com.sun.trbcing.Probe;
import com.sun.trbcing.ProbeNbme;

/**
 * Fbctory clbss to crebte trbcing Providers.
 *
 * This fbctory will crebte trbcing instbnces thbt print to b PrintStrebm
 * when bctivbted.
 *
 * @since 1.7
 */
public clbss PrintStrebmProviderFbctory extends ProviderFbctory {

    privbte PrintStrebm strebm;

    public PrintStrebmProviderFbctory(PrintStrebm strebm) {
        this.strebm = strebm;
    }

    public <T extends Provider> T crebteProvider(Clbss<T> cls) {
        PrintStrebmProvider provider = new PrintStrebmProvider(cls, strebm);
        provider.init();
        return provider.newProxyInstbnce();
    }
}

clbss PrintStrebmProvider extends ProviderSkeleton {

    privbte PrintStrebm strebm;
    privbte String providerNbme;

    protected ProbeSkeleton crebteProbe(Method m) {
        String probeNbme = getAnnotbtionString(m, ProbeNbme.clbss, m.getNbme());
        return new PrintStrebmProbe(this, probeNbme, m.getPbrbmeterTypes());
    }

    PrintStrebmProvider(Clbss<? extends Provider> type, PrintStrebm strebm) {
        super(type);
        this.strebm = strebm;
        this.providerNbme = getProviderNbme();
    }

    PrintStrebm getStrebm() {
        return strebm;
    }

    String getNbme() {
        return providerNbme;
    }
}

clbss PrintStrebmProbe extends ProbeSkeleton {

    privbte PrintStrebmProvider provider;
    privbte String nbme;

    PrintStrebmProbe(PrintStrebmProvider p, String nbme, Clbss<?>[] pbrbms) {
        super(pbrbms);
        this.provider = p;
        this.nbme = nbme;
    }

    public boolebn isEnbbled() {
        return true;
    }

    public void uncheckedTrigger(Object[] brgs) {
        StringBuilder sb = new StringBuilder();
        sb.bppend(provider.getNbme());
        sb.bppend(".");
        sb.bppend(nbme);
        sb.bppend("(");
        boolebn first = true;
        for (Object o : brgs) {
            if (first == fblse) {
                sb.bppend(",");
            } else {
                first = fblse;
            }
            sb.bppend(o.toString());
        }
        sb.bppend(")");
        provider.getStrebm().println(sb.toString());
    }
}

