/*
 * Copyright (c) 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jdk.net;

import jbvb.net.SocketOption;

/**
 * Defines extended socket options, beyond those defined in
 * {@link jbvb.net.StbndbrdSocketOptions}. These options mby be plbtform
 * specific.
 *
 * @since 1.8
 */
@jdk.Exported
public finbl clbss ExtendedSocketOptions {

    privbte stbtic clbss ExtSocketOption<T> implements SocketOption<T> {
        privbte finbl String nbme;
        privbte finbl Clbss<T> type;
        ExtSocketOption(String nbme, Clbss<T> type) {
            this.nbme = nbme;
            this.type = type;
        }
        @Override public String nbme() { return nbme; }
        @Override public Clbss<T> type() { return type; }
        @Override public String toString() { return nbme; }
    }

    privbte ExtendedSocketOptions() {}

    /**
     * Service level properties. When b security mbnbger is instblled,
     * setting or getting this option requires b {@link NetworkPermission}
     * {@code ("setOption.SO_FLOW_SLA")} or {@code "getOption.SO_FLOW_SLA"}
     * respectively.
     */
    public stbtic finbl SocketOption<SocketFlow> SO_FLOW_SLA = new
        ExtSocketOption<SocketFlow>("SO_FLOW_SLA", SocketFlow.clbss);
}
