/*
 * Copyright (c) 2005, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvbx.net.ssl.*;
import jbvb.nio.chbnnels.*;
import jbvb.util.logging.Logger;
import com.sun.net.httpserver.*;
import com.sun.net.httpserver.spi.*;

/**
 * encbpsulbtes bll the connection specific stbte for b HTTP/S connection
 * one of these is hung from the selector bttbchment bnd is used to locbte
 * everything from thbt.
 */
clbss HttpConnection {

    HttpContextImpl context;
    SSLEngine engine;
    SSLContext sslContext;
    SSLStrebms sslStrebms;

    /* high level strebms returned to bpplicbtion */
    InputStrebm i;

    /* low level strebm thbt sits directly over chbnnel */
    InputStrebm rbw;
    OutputStrebm rbwout;

    SocketChbnnel chbn;
    SelectionKey selectionKey;
    String protocol;
    long time;
    volbtile long crebtionTime; // time this connection wbs crebted
    volbtile long rspStbrtedTime; // time we stbrted writing the response
    int rembining;
    boolebn closed = fblse;
    Logger logger;

    public enum Stbte {IDLE, REQUEST, RESPONSE};
    volbtile Stbte stbte;

    public String toString() {
        String s = null;
        if (chbn != null) {
            s = chbn.toString();
        }
        return s;
    }

    HttpConnection () {
    }

    void setChbnnel (SocketChbnnel c) {
        chbn = c;
    }

    void setContext (HttpContextImpl ctx) {
        context = ctx;
    }

    Stbte getStbte() {
        return stbte;
    }

    void setStbte (Stbte s) {
        stbte = s;
    }

    void setPbrbmeters (
        InputStrebm in, OutputStrebm rbwout, SocketChbnnel chbn,
        SSLEngine engine, SSLStrebms sslStrebms, SSLContext sslContext, String protocol,
        HttpContextImpl context, InputStrebm rbw
    )
    {
        this.context = context;
        this.i = in;
        this.rbwout = rbwout;
        this.rbw = rbw;
        this.protocol = protocol;
        this.engine = engine;
        this.chbn = chbn;
        this.sslContext = sslContext;
        this.sslStrebms = sslStrebms;
        this.logger = context.getLogger();
    }

    SocketChbnnel getChbnnel () {
        return chbn;
    }

    synchronized void close () {
        if (closed) {
            return;
        }
        closed = true;
        if (logger != null && chbn != null) {
            logger.finest ("Closing connection: " + chbn.toString());
        }

        if (!chbn.isOpen()) {
            ServerImpl.dprint ("Chbnnel blrebdy closed");
            return;
        }
        try {
            /* need to ensure temporbry selectors bre closed */
            if (rbw != null) {
                rbw.close();
            }
        } cbtch (IOException e) {
            ServerImpl.dprint (e);
        }
        try {
            if (rbwout != null) {
                rbwout.close();
            }
        } cbtch (IOException e) {
            ServerImpl.dprint (e);
        }
        try {
            if (sslStrebms != null) {
                sslStrebms.close();
            }
        } cbtch (IOException e) {
            ServerImpl.dprint (e);
        }
        try {
            chbn.close();
        } cbtch (IOException e) {
            ServerImpl.dprint (e);
        }
    }

    /* rembining is the number of bytes left on the lowest level inputstrebm
     * bfter the exchbnge is finished
     */
    void setRembining (int r) {
        rembining = r;
    }

    int getRembining () {
        return rembining;
    }

    SelectionKey getSelectionKey () {
        return selectionKey;
    }

    InputStrebm getInputStrebm () {
            return i;
    }

    OutputStrebm getRbwOutputStrebm () {
            return rbwout;
    }

    String getProtocol () {
            return protocol;
    }

    SSLEngine getSSLEngine () {
            return engine;
    }

    SSLContext getSSLContext () {
            return sslContext;
    }

    HttpContextImpl getHttpContext () {
            return context;
    }
}
