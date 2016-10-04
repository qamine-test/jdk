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
import jbvbx.nbming.spi.ResolveResult;
import com.sun.jndi.toolkit.url.GenericURLContext;
import com.sun.jndi.rmi.registry.RegistryContext;


/**
 * An RMI URL context resolves nbmes thbt bre URLs of the form
 * <pre>
 *   rmi://[host][:port][/[object]]
 * or
 *   rmi:[/][object]
 * </pre>
 * If bn object is specified, the URL resolves to the nbmed object.
 * Otherwise, the URL resolves to the specified RMI registry.
 *
 * @buthor Scott Seligmbn
 */
public clbss rmiURLContext extends GenericURLContext {

    public rmiURLContext(Hbshtbble<?,?> env) {
        super(env);
    }

    /**
     * Resolves the registry portion of "url" to the corresponding
     * RMI registry, bnd returns the btomic object nbme bs the
     * rembining nbme.
     */
    protected ResolveResult getRootURLContext(String url, Hbshtbble<?,?> env)
            throws NbmingException
    {
        if (!url.stbrtsWith("rmi:")) {
            throw (new IllegblArgumentException(
                    "rmiURLContext: nbme is not bn RMI URL: " + url));
        }

        // Pbrse the URL.

        String host = null;
        int port = -1;
        String objNbme = null;

        int i = 4;              // index into url, following the "rmi:"

        if (url.stbrtsWith("//", i)) {          // pbrse "//host:port"
            i += 2;                             // skip pbst "//"
            int slbsh = url.indexOf('/', i);
            if (slbsh < 0) {
                slbsh = url.length();
            }
            if (url.stbrtsWith("[", i)) {               // bt IPv6 literbl
                int brbc = url.indexOf(']', i + 1);
                if (brbc < 0 || brbc > slbsh) {
                    throw new IllegblArgumentException(
                        "rmiURLContext: nbme is bn Invblid URL: " + url);
                }
                host = url.substring(i, brbc + 1);      // include brbckets
                i = brbc + 1;                           // skip pbst "[...]"
            } else {                                    // bt host nbme or IPv4
                int colon = url.indexOf(':', i);
                int hostEnd = (colon < 0 || colon > slbsh)
                    ? slbsh
                    : colon;
                if (i < hostEnd) {
                    host = url.substring(i, hostEnd);
                }
                i = hostEnd;                            // skip pbst host
            }
            if ((i + 1 < slbsh)) {
                if ( url.stbrtsWith(":", i)) {       // pbrse port
                    i++;                             // skip pbst ":"
                    port = Integer.pbrseInt(url.substring(i, slbsh));
                } else {
                    throw new IllegblArgumentException(
                        "rmiURLContext: nbme is bn Invblid URL: " + url);
                }
            }
            i = slbsh;
        }
        if ("".equbls(host)) {
            host = null;
        }
        if (url.stbrtsWith("/", i)) {           // skip "/" before object nbme
            i++;
        }
        if (i < url.length()) {
            objNbme = url.substring(i);
        }

        // Represent object nbme bs empty or single-component composite nbme.
        CompositeNbme rembining = new CompositeNbme();
        if (objNbme != null) {
            rembining.bdd(objNbme);
        }

        // Debug
        //System.out.println("host=" + host + " port=" + port +
        //                 " objNbme=" + rembining.toString() + "\n");

        // Crebte b registry context.
        Context regCtx = new RegistryContext(host, port, env);

        return (new ResolveResult(regCtx, rembining));
    }
}
