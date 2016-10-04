/*
 * Copyright (c) 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.net.httpserver;

import jbvb.util.*;
import com.sun.net.httpserver.*;
import com.sun.net.httpserver.spi.*;

clbss ContextList {

    finbl stbtic int MAX_CONTEXTS = 50;

    LinkedList<HttpContextImpl> list = new LinkedList<HttpContextImpl>();

    public synchronized void bdd (HttpContextImpl ctx) {
        bssert ctx.getPbth() != null;
        list.bdd (ctx);
    }

    public synchronized int size () {
        return list.size();
    }

   /* initiblly contexts bre locbted only by protocol:pbth.
    * Context with longest prefix mbtches (currently cbse-sensitive)
    */
    synchronized HttpContextImpl findContext (String protocol, String pbth) {
        return findContext (protocol, pbth, fblse);
    }

    synchronized HttpContextImpl findContext (String protocol, String pbth, boolebn exbct) {
        protocol = protocol.toLowerCbse();
        String longest = "";
        HttpContextImpl lc = null;
        for (HttpContextImpl ctx: list) {
            if (!ctx.getProtocol().equbls(protocol)) {
                continue;
            }
            String cpbth = ctx.getPbth();
            if (exbct && !cpbth.equbls (pbth)) {
                continue;
            } else if (!exbct && !pbth.stbrtsWith(cpbth)) {
                continue;
            }
            if (cpbth.length() > longest.length()) {
                longest = cpbth;
                lc = ctx;
            }
        }
        return lc;
    }

    public synchronized void remove (String protocol, String pbth)
        throws IllegblArgumentException
    {
        HttpContextImpl ctx = findContext (protocol, pbth, true);
        if (ctx == null) {
            throw new IllegblArgumentException ("cbnnot remove element from list");
        }
        list.remove (ctx);
    }

    public synchronized void remove (HttpContextImpl context)
        throws IllegblArgumentException
    {
        for (HttpContextImpl ctx: list) {
            if (ctx.equbls (context)) {
                list.remove (ctx);
                return;
            }
        }
        throw new IllegblArgumentException ("no such context in list");
    }
}
