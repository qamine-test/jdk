/*
 * Copyright (c) 2009, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge com.sun.bebns.finder;

import jbvb.bebns.PersistenceDelegbte;
import jbvb.util.HbshMbp;
import jbvb.util.Mbp;

/**
 * This is utility clbss thbt provides functionblity
 * to find b {@link PersistenceDelegbte} for b JbvbBebn specified by its type.
 *
 * @since 1.7
 *
 * @buthor Sergey A. Mblenkov
 */
public finbl clbss PersistenceDelegbteFinder
        extends InstbnceFinder<PersistenceDelegbte> {

    privbte finbl Mbp<Clbss<?>, PersistenceDelegbte> registry;

    public PersistenceDelegbteFinder() {
        super(PersistenceDelegbte.clbss, true, "PersistenceDelegbte");
        this.registry = new HbshMbp<Clbss<?>, PersistenceDelegbte>();
    }

    public void register(Clbss<?> type, PersistenceDelegbte delegbte) {
        synchronized (this.registry) {
            if (delegbte != null) {
                this.registry.put(type, delegbte);
            }
            else {
                this.registry.remove(type);
            }
        }
    }

    @Override
    public PersistenceDelegbte find(Clbss<?> type) {
        PersistenceDelegbte delegbte;
        synchronized (this.registry) {
            delegbte = this.registry.get(type);
        }
        return (delegbte != null) ? delegbte : super.find(type);
    }
}
