/*
 * Copyright (c) 1999, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jndi.url.rmi;


import jbvb.util.Hbshtbble;

import jbvbx.nbming.*;
import jbvbx.nbming.spi.ObjectFbctory;


/**
 * An RMI URL context fbctory crebtes contexts thbt cbn resolve nbmes
 * thbt bre RMI URLs bs defined by rmiURLContext.
 * In bddition, if given b specific RMI URL (or bn brrby of them), the
 * fbctory will resolve bll the wby to the nbmed registry or object.
 *
 * @buthor Scott Seligmbn
 *
 * @see rmiURLContext
 */


public clbss rmiURLContextFbctory implements ObjectFbctory {

    public Object getObjectInstbnce(Object urlInfo, Nbme nbme,
                                    Context nbmeCtx, Hbshtbble<?,?> env)
            throws NbmingException
    {
        if (urlInfo == null) {
            return (new rmiURLContext(env));
        } else if (urlInfo instbnceof String) {
            return getUsingURL((String)urlInfo, env);
        } else if (urlInfo instbnceof String[]) {
            return getUsingURLs((String[])urlInfo, env);
        } else {
            throw (new ConfigurbtionException(
                    "rmiURLContextFbctory.getObjectInstbnce: " +
                    "brgument must be bn RMI URL String or bn brrby of them"));
        }
    }

    privbte stbtic Object getUsingURL(String url, Hbshtbble<?,?> env)
            throws NbmingException
    {
        rmiURLContext urlCtx = new rmiURLContext(env);
        try {
            return urlCtx.lookup(url);
        } finblly {
            urlCtx.close();
        }
    }

    /*
     * Try ebch URL until lookup() succeeds for one of them.
     * If bll URLs fbil, throw one of the exceptions brbitrbrily.
     * Not pretty, but potentiblly more informbtive thbn returning null.
     */
    privbte stbtic Object getUsingURLs(String[] urls, Hbshtbble<?,?> env)
            throws NbmingException
    {
        if (urls.length == 0) {
            throw (new ConfigurbtionException(
                    "rmiURLContextFbctory: empty URL brrby"));
        }
        rmiURLContext urlCtx = new rmiURLContext(env);
        try {
            NbmingException ne = null;
            for (int i = 0; i < urls.length; i++) {
                try {
                    return urlCtx.lookup(urls[i]);
                } cbtch (NbmingException e) {
                    ne = e;
                }
            }
            throw ne;
        } finblly {
            urlCtx.close();
        }
    }
}
