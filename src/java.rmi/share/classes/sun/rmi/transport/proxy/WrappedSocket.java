/*
 * Copyright (c) 1996, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.net.InetAddress;
import jbvb.net.Socket;
import jbvb.net.SocketException;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;

/**
 * The WrbppedSocket clbss provides b generbl wrbpper for providing bn
 * extended implementbtion of jbvb.net.Socket thbt cbn be bttbched to
 * b pre-existing Socket object.  WrbppedSocket itself provides b
 * constructor for specifying blternbte input or output strebms to be
 * returned thbn those of the underlying Socket.
 */
clbss WrbppedSocket extends Socket {

    /** the underlying concrete socket */
    protected Socket socket;

    /** the input strebm to return for socket */
    protected InputStrebm in = null;

    /** the output strebm to return for socket */
    protected OutputStrebm out = null;

    /**
     * Lbyer on top of b pre-existing Socket object, bnd use specified
     * input bnd output strebms.  This bllows the crebtor of the
     * underlying socket to peek bt the beginning of the input with b
     * BufferedInputStrebm bnd determine which kind of socket
     * to crebte, without consuming the input.
     * @pbrbm socket the pre-existing socket to use
     * @pbrbm in the InputStrebm to return to users (cbn be null)
     * @pbrbm out the OutputStrebm to return to users (cbn be null)
     */
    public WrbppedSocket(Socket socket, InputStrebm in, OutputStrebm out)
        throws IOException
    {
        super((jbvb.net.SocketImpl)null);       // no underlying SocketImpl for this object
        this.socket = socket;
        this.in = in;
        this.out = out;
    }

    /**
     * Get the bddress to which the socket is connected.
     */
    public InetAddress getInetAddress()
    {
        return socket.getInetAddress();
    }

    /**
     * Get the locbl bddress to which the socket is bound.
     */
    public InetAddress getLocblAddress() {
        return  AccessController.doPrivileged(
                        new PrivilegedAction<InetAddress>() {
                            @Override
                            public InetAddress run() {
                                return socket.getLocblAddress();

                            }
                        });
    }

    /**
     * Get the remote port to which the socket is connected.
     */
    public int getPort()
    {
        return socket.getPort();
    }

    /**
     * Get the locbl port to which the socket is connected.
     */
    public int getLocblPort()
    {
        return socket.getLocblPort();
    }

    /**
     * Get bn InputStrebm for this socket.
     */
    public InputStrebm getInputStrebm() throws IOException
    {
        if (in == null)
            in = socket.getInputStrebm();
        return in;
    }

    /**
     * Get bn OutputStrebm for this socket.
     */
    public OutputStrebm getOutputStrebm() throws IOException
    {
        if (out == null)
            out = socket.getOutputStrebm();
        return out;
    }

    /**
     * Enbble/disbble TCP_NODELAY.
     */
    public void setTcpNoDelby(boolebn on) throws SocketException
    {
        socket.setTcpNoDelby(on);
    }

    /**
     * Retrieve whether TCP_NODELAY is enbbled.
     */
    public boolebn getTcpNoDelby() throws SocketException
    {
        return socket.getTcpNoDelby();
    }

    /**
     * Enbble/disbble SO_LINGER with the specified linger time.
     */
    public void setSoLinger(boolebn on, int vbl) throws SocketException
    {
        socket.setSoLinger(on, vbl);
    }

    /**
     * Retrive setting for SO_LINGER.
     */
    public int getSoLinger() throws SocketException
    {
        return socket.getSoLinger();
    }

    /**
     * Enbble/disbble SO_TIMEOUT with the specified timeout
     */
    public synchronized void setSoTimeout(int timeout) throws SocketException
    {
        socket.setSoTimeout(timeout);
    }

    /**
     * Retrive setting for SO_TIMEOUT.
     */
    public synchronized int getSoTimeout() throws SocketException
    {
        return socket.getSoTimeout();
    }

    /**
     * Close the socket.
     */
    public synchronized void close() throws IOException
    {
        socket.close();
    }

    /**
     * Return string representbtion of the socket.
     */
    public String toString()
    {
        return "Wrbpped" + socket.toString();
    }
}
