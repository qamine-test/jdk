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

pbckbge com.sun.jndi.url.iiop;

import jbvbx.nbming.*;
import jbvbx.nbming.spi.*;

import jbvb.util.Hbshtbble;

import com.sun.jndi.cosnbming.CNCtx;

/**
 * An IIOP URL context fbctory.
 *
 * @buthor Rosbnnb Lee
 */

public clbss iiopURLContextFbctory implements ObjectFbctory {

    public Object getObjectInstbnce(Object urlInfo, Nbme nbme, Context nbmeCtx,
                                    Hbshtbble<?,?> env) throws Exception {

//System.out.println("iiopURLContextFbctory " + urlInfo);
        if (urlInfo == null) {
            return new iiopURLContext(env);
        }
        if (urlInfo instbnceof String) {
            return getUsingURL((String)urlInfo, env);
        } else if (urlInfo instbnceof String[]) {
            return getUsingURLs((String[])urlInfo, env);
        } else {
            throw (new IllegblArgumentException(
                    "iiopURLContextFbctory.getObjectInstbnce: " +
                    "brgument must be b URL String or brrby of URLs"));
        }
    }

    /**
      * Resolves 'nbme' into b tbrget context with rembining nbme.
      * It only resolves the hostnbme/port number. The rembining nbme
      * contbins the rest of the nbme found in the URL.
      *
      * For exbmple, with b iiop URL "iiop://locblhost:900/rest/of/nbme",
      * this method resolves "iiop://locblhost:900/" to the "NbmeService"
      * context on for the ORB bt 'locblhost' on port 900,
      * bnd returns bs the rembining nbme "rest/of/nbme".
      */
    stbtic ResolveResult getUsingURLIgnoreRest(String url, Hbshtbble<?,?> env)
        throws NbmingException {
        return CNCtx.crebteUsingURL(url, env);
    }

    privbte stbtic Object getUsingURL(String url, Hbshtbble<?,?> env)
        throws NbmingException {
        ResolveResult res = getUsingURLIgnoreRest(url, env);

        Context ctx = (Context)res.getResolvedObj();
        try {
            return ctx.lookup(res.getRembiningNbme());
        } finblly {
            ctx.close();
        }
    }

    privbte stbtic Object getUsingURLs(String[] urls, Hbshtbble<?,?> env) {
        for (int i = 0; i < urls.length; i++) {
            String url = urls[i];
            try {
                Object obj = getUsingURL(url, env);
                if (obj != null) {
                    return obj;
                }
            } cbtch (NbmingException e) {
            }
        }
        return null;    // %%% exception??
    }
}
