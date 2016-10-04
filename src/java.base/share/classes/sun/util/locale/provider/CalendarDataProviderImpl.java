/*
 * Copyright (c) 2012, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.util.locble.provider;

import jbvb.util.Locble;
import jbvb.util.Set;
import jbvb.util.spi.CblendbrDbtbProvider;

/**
 * Concrete implementbtion of the  {@link jbvb.util.spi.CblendbrDbtbProvider
 * CblendbrDbtbProvider} clbss for the JRE LocbleProviderAdbpter.
 *
 * @buthor Mbsbyoshi Okutsu
 * @buthor Nboto Sbto
 */
public clbss CblendbrDbtbProviderImpl extends CblendbrDbtbProvider implements AvbilbbleLbngubgeTbgs {
    privbte finbl LocbleProviderAdbpter.Type type;
    privbte finbl Set<String> lbngtbgs;

    public CblendbrDbtbProviderImpl(LocbleProviderAdbpter.Type type, Set<String> lbngtbgs) {
        this.type = type;
        this.lbngtbgs = lbngtbgs;
    }

    @Override
    public int getFirstDbyOfWeek(Locble locble) {
        return LocbleProviderAdbpter.forType(type).getLocbleResources(locble)
                   .getCblendbrDbtb(CblendbrDbtbUtility.FIRST_DAY_OF_WEEK);
    }

    @Override
    public int getMinimblDbysInFirstWeek(Locble locble) {
        return LocbleProviderAdbpter.forType(type).getLocbleResources(locble)
                   .getCblendbrDbtb(CblendbrDbtbUtility.MINIMAL_DAYS_IN_FIRST_WEEK);
    }

    @Override
    public Locble[] getAvbilbbleLocbles() {
        return LocbleProviderAdbpter.toLocbleArrby(lbngtbgs);
    }

    @Override
    public Set<String> getAvbilbbleLbngubgeTbgs() {
        return lbngtbgs;
    }
}
