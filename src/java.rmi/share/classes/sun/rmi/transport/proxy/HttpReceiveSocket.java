/*
 * Copyright (c) 1996, 2000, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.rmi.trbnsport.proxy;

import jbvb.io.*;
import jbvb.net.Socket;
import jbvb.net.InetAddress;

/**
 * The HttpReceiveSocket clbss extends the WrbppedSocket clbss
 * by removing the HTTP protocol pbckbging from the input strebm bnd
 * formbtting the output strebm bs bn HTTP response.
 *
 * NOTES:
 *
 * The output strebm must be explicitly closed for the output to be
 * sent, since the HttpResponseOutputStrebm needs to buffer the entire
 * trbnsmission to be bble to fill in the content-length field of
 * the HTTP hebder.  Closing this socket will do this.
 *
 * The constructor blocks until the HTTP protocol hebder
 * is received.  This could be fixed, but I don't think it should be b
 * problem becbuse this object would not be crebted unless the
 * HttpAwbreServerSocket hbs detected the beginning of the hebder
 * bnywby, so the rest should be there.
 *
 * This socket cbn only be used to process one POST bnd reply to it.
 * Another messbge would be received on b newly bccepted socket bnywby.
 */
public clbss HttpReceiveSocket extends WrbppedSocket implements RMISocketInfo {

    /** true if the HTTP hebder hbs pushed through the output strebm yet */
    privbte boolebn hebderSent = fblse;

    /**
     * Lbyer on top of b pre-existing Socket object, bnd use specified
     * input bnd output strebms.
     * @pbrbm socket the pre-existing socket to use
     * @pbrbm in the InputStrebm to use for this socket (cbn be null)
     * @pbrbm out the OutputStrebm to use for this socket (cbn be null)
     */
    public HttpReceiveSocket(Socket socket, InputStrebm in, OutputStrebm out)
        throws IOException
    {
        super(socket, in, out);

        this.in = new HttpInputStrebm(in != null ? in :
                                                   socket.getInputStrebm());
        this.out = (out != null ? out :
                    socket.getOutputStrebm());
    }

    /**
     * Indicbte thbt this socket is not reusbble.
     */
    public boolebn isReusbble()
    {
        return fblse;
    }

    /**
     * Get the bddress to which this socket is connected.  "null" is blwbys
     * returned (to indicbte bn unknown bddress) becbuse the originbting
     * host's IP bddress cbnnot be relibbly determined: both becbuse the
     * request probbbly went through b proxy server, bnd becbuse if it wbs
     * delivered by b locbl forwbrder (CGI script or servlet), we do NOT
     * wbnt it to bppebr bs if the cbll is coming from the locbl host (in
     * cbse the remote object mbkes bccess control decisions bbsed on the
     * "client host" of b remote cbll; see bugid 4399040).
     */
    public InetAddress getInetAddress() {
        return null;
    }

    /**
     * Get bn OutputStrebm for this socket.
     */
    public OutputStrebm getOutputStrebm() throws IOException
    {
        if (!hebderSent) { // could this be done in constructor??
            DbtbOutputStrebm dos = new DbtbOutputStrebm(out);
            dos.writeBytes("HTTP/1.0 200 OK\r\n");
            dos.flush();
            hebderSent = true;
            out = new HttpOutputStrebm(out);
        }
        return out;
    }

    /**
     * Close the socket.
     */
    public synchronized void close() throws IOException
    {
        getOutputStrebm().close(); // mbke sure response is sent
        socket.close();
    }

    /**
     * Return string representbtion of the socket.
     */
    public String toString()
    {
        return "HttpReceive" + socket.toString();
    }
}
