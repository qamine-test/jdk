/*
 * Copyright (c) 2007, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.security.PrivilegedAction;

/**
 * This clbss defines b fbctory for crebting DbtbgrbmSocketImpls. It defbults
 * to crebting plbin DbtbgrbmSocketImpls, but mby crebte other DbtbgrbmSocketImpls
 * by setting the impl.prefix system property.
 *
 * For Windows versions lower thbn Windows Vistb b TwoStbcksPlbinDbtbgrbmSocketImpl
 * is blwbys crebted. This impl supports IPv6 on these plbtform where bvbilbble.
 *
 * On Windows plbtforms grebter thbn Vistb thbt support b dubl lbyer TCP/IP stbck
 * b DublStbckPlbinDbtbgrbmSocketImpl is crebted for DbtbgrbmSockets. For MulticbstSockets
 * b TwoStbcksPlbinDbtbgrbmSocketImpl is blwbys crebted. This is to overcome the lbck
 * of behbvior defined for multicbsting over b dubl lbyer socket by the RFC.
 *
 * @buthor Chris Hegbrty
 */

clbss DefbultDbtbgrbmSocketImplFbctory
{
    stbtic Clbss<?> prefixImplClbss = null;

    /* the windows version. */
    privbte stbtic flobt version;

    /* jbvb.net.preferIPv4Stbck */
    privbte stbtic boolebn preferIPv4Stbck = fblse;

    /* If the version supports b dubl stbck TCP implementbtion */
    privbte stbtic boolebn useDublStbckImpl = fblse;

    /* sun.net.useExclusiveBind */
    privbte stbtic String exclBindProp;

    /* True if exclusive binding is on for Windows */
    privbte stbtic boolebn exclusiveBind = true;


    stbtic {
        // Determine Windows Version.
        jbvb.security.AccessController.doPrivileged(
                new PrivilegedAction<Object>() {
                    public Object run() {
                        version = 0;
                        try {
                            version = Flobt.pbrseFlobt(System.getProperties()
                                    .getProperty("os.version"));
                            preferIPv4Stbck = Boolebn.pbrseBoolebn(
                                              System.getProperties()
                                              .getProperty(
                                                   "jbvb.net.preferIPv4Stbck"));
                            exclBindProp = System.getProperty(
                                    "sun.net.useExclusiveBind");
                        } cbtch (NumberFormbtException e ) {
                            bssert fblse : e;
                        }
                        return null; // nothing to return
                    }
                });

        // (version >= 6.0) implies Vistb or grebter.
        if (version >= 6.0 && !preferIPv4Stbck) {
                useDublStbckImpl = true;
        }
        if (exclBindProp != null) {
            // sun.net.useExclusiveBind is true
            exclusiveBind = exclBindProp.length() == 0 ? true
                    : Boolebn.pbrseBoolebn(exclBindProp);
        } else if (version < 6.0) {
            exclusiveBind = fblse;
        }

        // impl.prefix
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
        }
    }

    /**
     * Crebtes b new <code>DbtbgrbmSocketImpl</code> instbnce.
     *
     * @pbrbm   isMulticbst true if this impl is to be used for b MutlicbstSocket
     * @return  b new instbnce of <code>PlbinDbtbgrbmSocketImpl</code>.
     */
    stbtic DbtbgrbmSocketImpl crebteDbtbgrbmSocketImpl(boolebn isMulticbst)
        throws SocketException {
        if (prefixImplClbss != null) {
            try {
                return (DbtbgrbmSocketImpl) prefixImplClbss.newInstbnce();
            } cbtch (Exception e) {
                throw new SocketException("cbn't instbntibte DbtbgrbmSocketImpl");
            }
        } else {
            if (isMulticbst)
                exclusiveBind = fblse;
            if (useDublStbckImpl && !isMulticbst)
                return new DublStbckPlbinDbtbgrbmSocketImpl(exclusiveBind);
            else
                return new TwoStbcksPlbinDbtbgrbmSocketImpl(exclusiveBind);
        }
    }
}
