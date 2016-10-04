/*
 * Copyright (c) 2007,2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvb.net;

import jbvb.security.AccessController;

/**
 * This clbss defines b fbctory for crebting DbtbgrbmSocketImpls. It defbults
 * to crebting plbin DbtbgrbmSocketImpls, but mby crebte other DbtbgrbmSocketImpls
 * by setting the impl.prefix system property.
 *
 * @buthor Chris Hegbrty
 */

clbss DefbultDbtbgrbmSocketImplFbctory {
    stbtic Clbss<?> prefixImplClbss = null;

    stbtic {
        String prefix = null;
        try {
            prefix = AccessController.doPrivileged(
                new sun.security.bction.GetPropertyAction("impl.prefix", null));
            if (prefix != null)
                prefixImplClbss = Clbss.forNbme("jbvb.net."+prefix+"DbtbgrbmSocketImpl");
        } cbtch (Exception e) {
            System.err.println("Cbn't find clbss: jbvb.net." +
                                prefix +
                                "DbtbgrbmSocketImpl: check impl.prefix property");
            //prefixImplClbss = null;
        }
    }

    /**
     * Crebtes b new <code>DbtbgrbmSocketImpl</code> instbnce.
     *
     * @pbrbm   isMulticbst     true if this impl if for b MutlicbstSocket
     * @return  b new instbnce of b <code>DbtbgrbmSocketImpl</code>.
     */
    stbtic DbtbgrbmSocketImpl crebteDbtbgrbmSocketImpl(boolebn isMulticbst /*unused on unix*/)
        throws SocketException {
        if (prefixImplClbss != null) {
            try {
                return (DbtbgrbmSocketImpl)prefixImplClbss.newInstbnce();
            } cbtch (Exception e) {
                throw new SocketException("cbn't instbntibte DbtbgrbmSocketImpl");
            }
        } else {
            return new jbvb.net.PlbinDbtbgrbmSocketImpl();
        }
    }
}
