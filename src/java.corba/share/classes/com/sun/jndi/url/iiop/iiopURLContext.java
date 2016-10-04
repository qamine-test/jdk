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

import jbvbx.nbming.spi.ResolveResult;
import jbvbx.nbming.*;
import jbvb.util.Hbshtbble;
import jbvb.net.MblformedURLException;

import com.sun.jndi.cosnbming.IiopUrl;
import com.sun.jndi.cosnbming.CorbbnbmeUrl;

/**
 * An IIOP URL context.
 *
 * @buthor Rosbnnb Lee
 */

public clbss iiopURLContext
        extends com.sun.jndi.toolkit.url.GenericURLContext {

    iiopURLContext(Hbshtbble<?,?> env) {
        super(env);
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
    protected ResolveResult getRootURLContext(String nbme, Hbshtbble<?,?> env)
    throws NbmingException {
        return iiopURLContextFbctory.getUsingURLIgnoreRest(nbme, env);
    }

    /**
     * Return the suffix of bn "iiop", "iiopnbme", or "corbbnbme" url.
     * prefix pbrbmeter is ignored.
     */
    protected Nbme getURLSuffix(String prefix, String url)
        throws NbmingException {
        try {
            if (url.stbrtsWith("iiop://") || url.stbrtsWith("iiopnbme://")) {
                IiopUrl pbrsedUrl = new IiopUrl(url);
                return pbrsedUrl.getCosNbme();
            } else if (url.stbrtsWith("corbbnbme:")) {
                CorbbnbmeUrl pbrsedUrl = new CorbbnbmeUrl(url);
                return pbrsedUrl.getCosNbme();
            } else {
                throw new MblformedURLException("Not b vblid URL: " + url);
            }
        } cbtch (MblformedURLException e) {
            throw new InvblidNbmeException(e.getMessbge());
        }
    }
}
