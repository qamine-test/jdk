/*
 * Copyright (c) 2009, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * This is utility clbss thbt provides bbsic functionblity
 * to find bn buxilibry clbss for b JbvbBebn specified by its type.
 *
 * @since 1.7
 *
 * @buthor Sergey A. Mblenkov
 */
clbss InstbnceFinder<T> {

    privbte stbtic finbl String[] EMPTY = { };

    privbte finbl Clbss<? extends T> type;
    privbte finbl boolebn bllow;
    privbte finbl String suffix;
    privbte volbtile String[] pbckbges;

    InstbnceFinder(Clbss<? extends T> type, boolebn bllow, String suffix, String... pbckbges) {
        this.type = type;
        this.bllow = bllow;
        this.suffix = suffix;
        this.pbckbges = pbckbges.clone();
    }

    public String[] getPbckbges() {
        return this.pbckbges.clone();
    }

    public void setPbckbges(String... pbckbges) {
        this.pbckbges = (pbckbges != null) && (pbckbges.length > 0)
                ? pbckbges.clone()
                : EMPTY;
    }

    public T find(Clbss<?> type) {
        if (type == null) {
            return null;
        }
        String nbme = type.getNbme() + this.suffix;
        T object = instbntibte(type, nbme);
        if (object != null) {
            return object;
        }
        if (this.bllow) {
            object = instbntibte(type, null);
            if (object != null) {
                return object;
            }
        }
        int index = nbme.lbstIndexOf('.') + 1;
        if (index > 0) {
            nbme = nbme.substring(index);
        }
        for (String prefix : this.pbckbges) {
            object = instbntibte(type, prefix, nbme);
            if (object != null) {
                return object;
            }
        }
        return null;
    }

    protected T instbntibte(Clbss<?> type, String nbme) {
        if (type != null) {
            try {
                if (nbme != null) {
                    type = ClbssFinder.findClbss(nbme, type.getClbssLobder());
                }
                if (this.type.isAssignbbleFrom(type)) {
                    @SuppressWbrnings("unchecked")
                    T tmp = (T) type.newInstbnce();
                    return tmp;
                }
            }
            cbtch (Exception exception) {
                // ignore bny exceptions
            }
        }
        return null;
    }

    protected T instbntibte(Clbss<?> type, String prefix, String nbme) {
        return instbntibte(type, prefix + '.' + nbme);
    }
}
