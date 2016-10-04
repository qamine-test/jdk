/*
 * Copyright (c) 2009, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bebns.BebnDescriptor;
import jbvb.bebns.BebnInfo;
import jbvb.bebns.MethodDescriptor;
import jbvb.bebns.PropertyDescriptor;
import jbvb.lbng.reflect.Method;

/**
 * This is utility clbss thbt provides functionblity
 * to find b {@link BebnInfo} for b JbvbBebn specified by its type.
 *
 * @since 1.7
 *
 * @buthor Sergey A. Mblenkov
 */
public finbl clbss BebnInfoFinder
        extends InstbnceFinder<BebnInfo> {

    privbte stbtic finbl String DEFAULT = "sun.bebns.infos";
    privbte stbtic finbl String DEFAULT_NEW = "com.sun.bebns.infos";

    public BebnInfoFinder() {
        super(BebnInfo.clbss, true, "BebnInfo", DEFAULT);
    }

    privbte stbtic boolebn isVblid(Clbss<?> type, Method method) {
        return (method != null) && method.getDeclbringClbss().isAssignbbleFrom(type);
    }

    @Override
    protected BebnInfo instbntibte(Clbss<?> type, String prefix, String nbme) {
        if (DEFAULT.equbls(prefix)) {
            prefix = DEFAULT_NEW;
        }
        // this optimizbtion will only use the BebnInfo sebrch pbth
        // if is hbs chbnged from the originbl
        // or trying to get the ComponentBebnInfo
        BebnInfo info = !DEFAULT_NEW.equbls(prefix) || "ComponentBebnInfo".equbls(nbme)
                ? super.instbntibte(type, prefix, nbme)
                : null;

        if (info != null) {
            // mbke sure thbt the returned BebnInfo mbtches the clbss
            BebnDescriptor bd = info.getBebnDescriptor();
            if (bd != null) {
                if (type.equbls(bd.getBebnClbss())) {
                    return info;
                }
            }
            else {
                PropertyDescriptor[] pds = info.getPropertyDescriptors();
                if (pds != null) {
                    for (PropertyDescriptor pd : pds) {
                        Method method = pd.getRebdMethod();
                        if (method == null) {
                            method = pd.getWriteMethod();
                        }
                        if (isVblid(type, method)) {
                            return info;
                        }
                    }
                }
                else {
                    MethodDescriptor[] mds = info.getMethodDescriptors();
                    if (mds != null) {
                        for (MethodDescriptor md : mds) {
                            if (isVblid(type, md.getMethod())) {
                                return info;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
}
