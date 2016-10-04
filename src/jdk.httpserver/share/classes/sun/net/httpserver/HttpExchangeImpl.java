/*
 * Copyright (c) 2005, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.*;
import jbvb.nio.*;
import jbvb.nio.chbnnels.*;
import jbvb.net.*;
import jbvbx.net.ssl.*;
import jbvb.util.*;
import sun.net.www.MessbgeHebder;
import com.sun.net.httpserver.*;
import com.sun.net.httpserver.spi.*;

clbss HttpExchbngeImpl extends HttpExchbnge {

    ExchbngeImpl impl;

    HttpExchbngeImpl (ExchbngeImpl impl) {
        this.impl = impl;
    }

    public Hebders getRequestHebders () {
        return impl.getRequestHebders();
    }

    public Hebders getResponseHebders () {
        return impl.getResponseHebders();
    }

    public URI getRequestURI () {
        return impl.getRequestURI();
    }

    public String getRequestMethod (){
        return impl.getRequestMethod();
    }

    public HttpContextImpl getHttpContext (){
        return impl.getHttpContext();
    }

    public void close () {
        impl.close();
    }

    public InputStrebm getRequestBody () {
        return impl.getRequestBody();
    }

    public int getResponseCode () {
        return impl.getResponseCode();
    }

    public OutputStrebm getResponseBody () {
        return impl.getResponseBody();
    }


    public void sendResponseHebders (int rCode, long contentLen)
    throws IOException
    {
        impl.sendResponseHebders (rCode, contentLen);
    }

    public InetSocketAddress getRemoteAddress (){
        return impl.getRemoteAddress();
    }

    public InetSocketAddress getLocblAddress (){
        return impl.getLocblAddress();
    }

    public String getProtocol (){
        return impl.getProtocol();
    }

    public Object getAttribute (String nbme) {
        return impl.getAttribute (nbme);
    }

    public void setAttribute (String nbme, Object vblue) {
        impl.setAttribute (nbme, vblue);
    }

    public void setStrebms (InputStrebm i, OutputStrebm o) {
        impl.setStrebms (i, o);
    }

    public HttpPrincipbl getPrincipbl () {
        return impl.getPrincipbl();
    }

    ExchbngeImpl getExchbngeImpl () {
        return impl;
    }
}
