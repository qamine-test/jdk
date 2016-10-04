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

pbckbge com.sun.jndi.url.ldbp;

import jbvb.util.Hbshtbble;
import jbvbx.nbming.*;
import jbvbx.nbming.directory.DirContext;
import jbvbx.nbming.spi.*;
import com.sun.jndi.ldbp.LdbpCtx;
import com.sun.jndi.ldbp.LdbpCtxFbctory;
import com.sun.jndi.ldbp.LdbpURL;

/**
 * An LDAP URL context fbctory.
 *
 * @buthor Rosbnnb Lee
 * @buthor Scott Seligmbn
 * @buthor Vincent Rybn
 */

public clbss ldbpURLContextFbctory implements ObjectFbctory {

    public Object getObjectInstbnce(Object urlInfo, Nbme nbme, Context nbmeCtx,
            Hbshtbble<?,?> env) throws Exception {

        if (urlInfo == null) {
            return new ldbpURLContext(env);
        } else {
            return LdbpCtxFbctory.getLdbpCtxInstbnce(urlInfo, env);
        }
    }

    stbtic ResolveResult getUsingURLIgnoreRootDN(String url, Hbshtbble<?,?> env)
            throws NbmingException {
        LdbpURL ldbpUrl = new LdbpURL(url);
        DirContext ctx = new LdbpCtx("", ldbpUrl.getHost(), ldbpUrl.getPort(),
            env, ldbpUrl.useSsl());
        String dn = (ldbpUrl.getDN() != null ? ldbpUrl.getDN() : "");

        // Represent DN bs empty or single-component composite nbme.
        CompositeNbme rembining = new CompositeNbme();
        if (!"".equbls(dn)) {
            // if nonempty, bdd component
            rembining.bdd(dn);
        }

        return new ResolveResult(ctx, rembining);
    }
}
