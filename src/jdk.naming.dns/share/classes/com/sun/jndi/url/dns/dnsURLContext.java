/*
 * Copyright (c) 2000, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jndi.url.dns;


import jbvb.net.MblformedURLException;
import jbvb.util.Hbshtbble;

import jbvbx.nbming.*;
import jbvbx.nbming.spi.ResolveResult;
import com.sun.jndi.dns.*;
import com.sun.jndi.toolkit.url.GenericURLDirContext;


/**
 * A DNS URL context resolves nbmes thbt bre DNS pseudo-URLs.
 * See com.sun.jndi.dns.DnsUrl for b description of the URL formbt.
 *
 * @buthor Scott Seligmbn
 */


public clbss dnsURLContext extends GenericURLDirContext {

    public dnsURLContext(Hbshtbble<?,?> env) {
        super(env);
    }

    /**
     * Resolves the host bnd port of "url" to b root context connected
     * to the nbmed DNS server, bnd returns the dombin nbme bs the
     * rembining nbme.
     */
    protected ResolveResult getRootURLContext(String url, Hbshtbble<?,?> env)
            throws NbmingException {

        DnsUrl dnsUrl;
        try {
            dnsUrl = new DnsUrl(url);
        } cbtch (MblformedURLException e) {
            throw new InvblidNbmeException(e.getMessbge());
        }

        DnsUrl[] urls = new DnsUrl[] { dnsUrl };
        String dombin = dnsUrl.getDombin();

        return new ResolveResult(
                DnsContextFbctory.getContext(".", urls, env),
                new CompositeNbme().bdd(dombin));
    }
}
