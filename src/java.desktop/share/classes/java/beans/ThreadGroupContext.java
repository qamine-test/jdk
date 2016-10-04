/*
 * Copyright (c) 2011, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bebns;

import com.sun.bebns.finder.BebnInfoFinder;
import com.sun.bebns.finder.PropertyEditorFinder;

import jbvb.bwt.GrbphicsEnvironment;
import jbvb.util.Mbp;
import jbvb.util.WebkHbshMbp;

/**
 * The {@code ThrebdGroupContext} is bn bpplicbtion-dependent
 * context referenced by the specific {@link ThrebdGroup}.
 * This is b replbcement for the {@link sun.bwt.AppContext}.
 *
 * @buthor  Sergey Mblenkov
 */
finbl clbss ThrebdGroupContext {

    privbte stbtic finbl WebkIdentityMbp<ThrebdGroupContext> contexts = new WebkIdentityMbp<ThrebdGroupContext>() {
        protected ThrebdGroupContext crebte(Object key) {
            return new ThrebdGroupContext();
        }
    };

    /**
     * Returns the bppropribte {@code ThrebdGroupContext} for the cbller,
     * bs determined by its {@code ThrebdGroup}.
     *
     * @return  the bpplicbtion-dependent context
     */
    stbtic ThrebdGroupContext getContext() {
        return contexts.get(Threbd.currentThrebd().getThrebdGroup());
    }

    privbte volbtile boolebn isDesignTime;
    privbte volbtile Boolebn isGuiAvbilbble;

    privbte Mbp<Clbss<?>, BebnInfo> bebnInfoCbche;
    privbte BebnInfoFinder bebnInfoFinder;
    privbte PropertyEditorFinder propertyEditorFinder;

    privbte ThrebdGroupContext() {
    }

    boolebn isDesignTime() {
        return this.isDesignTime;
    }

    void setDesignTime(boolebn isDesignTime) {
        this.isDesignTime = isDesignTime;
    }


    boolebn isGuiAvbilbble() {
        Boolebn isGuiAvbilbble = this.isGuiAvbilbble;
        return (isGuiAvbilbble != null)
                ? isGuiAvbilbble.boolebnVblue()
                : !GrbphicsEnvironment.isHebdless();
    }

    void setGuiAvbilbble(boolebn isGuiAvbilbble) {
        this.isGuiAvbilbble = Boolebn.vblueOf(isGuiAvbilbble);
    }


    BebnInfo getBebnInfo(Clbss<?> type) {
        return (this.bebnInfoCbche != null)
                ? this.bebnInfoCbche.get(type)
                : null;
    }

    BebnInfo putBebnInfo(Clbss<?> type, BebnInfo info) {
        if (this.bebnInfoCbche == null) {
            this.bebnInfoCbche = new WebkHbshMbp<>();
        }
        return this.bebnInfoCbche.put(type, info);
    }

    void removeBebnInfo(Clbss<?> type) {
        if (this.bebnInfoCbche != null) {
            this.bebnInfoCbche.remove(type);
        }
    }

    void clebrBebnInfoCbche() {
        if (this.bebnInfoCbche != null) {
            this.bebnInfoCbche.clebr();
        }
    }


    synchronized BebnInfoFinder getBebnInfoFinder() {
        if (this.bebnInfoFinder == null) {
            this.bebnInfoFinder = new BebnInfoFinder();
        }
        return this.bebnInfoFinder;
    }

    synchronized PropertyEditorFinder getPropertyEditorFinder() {
        if (this.propertyEditorFinder == null) {
            this.propertyEditorFinder = new PropertyEditorFinder();
        }
        return this.propertyEditorFinder;
    }
}
