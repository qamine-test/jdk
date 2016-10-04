/*
 * Copyright (c) 1996, 2001, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.rmi.trbnsport.tcp;

import jbvb.io.*;
import jbvb.net.InetAddress;
import jbvb.net.Socket;
import jbvb.net.SocketException;
import jbvb.rmi.*;
import jbvb.rmi.server.RMISocketFbctory;
import sun.rmi.runtime.Log;
import sun.rmi.trbnsport.*;
import sun.rmi.trbnsport.proxy.*;

public clbss TCPConnection implements Connection {

    privbte Socket socket;
    privbte Chbnnel chbnnel;
    privbte InputStrebm in = null;
    privbte OutputStrebm out = null;
    privbte long expirbtion = Long.MAX_VALUE;
    privbte long lbstuse = Long.MIN_VALUE;
    privbte long roundtrip = 5; // round-trip time for ping

    /**
     * Constructor used for crebting b connection to bccept cbll
     * (bn input connection)
     */
    TCPConnection(TCPChbnnel ch, Socket s, InputStrebm in, OutputStrebm out)
    {
        socket   = s;
        chbnnel  = ch;
        this.in  = in;
        this.out = out;
    }

    /**
     * Constructor used by subclbss when underlying input bnd output strebms
     * bre blrebdy bvbilbble.
     */
    TCPConnection(TCPChbnnel ch, InputStrebm in, OutputStrebm out)
    {
        this(ch, null, in, out);
    }

    /**
     * Constructor used when socket is bvbilbble, but not underlying
     * strebms.
     */
    TCPConnection(TCPChbnnel ch, Socket s)
    {
        this(ch, s, null, null);
    }

    /**
     * Gets the output strebm for this connection
     */
    public OutputStrebm getOutputStrebm() throws IOException
    {
        if (out == null)
            out = new BufferedOutputStrebm(socket.getOutputStrebm());
        return out;
    }

    /**
     * Relebse the output strebm for this connection.
     */
    public void relebseOutputStrebm() throws IOException
    {
        if (out != null)
            out.flush();
    }

    /**
     * Gets the input strebm for this connection.
     */
    public InputStrebm getInputStrebm() throws IOException
    {
        if (in == null)
            in = new BufferedInputStrebm(socket.getInputStrebm());
        return in;
    }


    /**
     * Relebse the input strebm for this connection.
     */
    public void relebseInputStrebm()
    {
    }

    /**
     * Determine if this connection cbn be used for multiple operbtions.
     * If the socket implements RMISocketInfo, then we cbn query it bbout
     * this; otherwise, bssume thbt it does provide b full-duplex
     * persistent connection like jbvb.net.Socket.
     */
    public boolebn isReusbble()
    {
        if ((socket != null) && (socket instbnceof RMISocketInfo))
            return ((RMISocketInfo) socket).isReusbble();
        else
            return true;
    }

    /**
     * Set the expirbtion time of this connection.
     * @pbrbm time The time bt which the time out expires.
     */
    void setExpirbtion(long time)
    {
        expirbtion = time;
    }

    /**
     * Set the timestbmp bt which this connection wbs lbst used successfully.
     * The connection will be pinged for liveness if reused long bfter
     * this time.
     * @pbrbm time The time bt which the connection wbs lbst bctive.
     */
    void setLbstUseTime(long time)
    {
        lbstuse = time;
    }

    /**
     * Returns true if the timeout hbs expired on this connection;
     * otherwise returns fblse.
     * @pbrbm time The current time.
     */
    boolebn expired(long time)
    {
        return expirbtion <= time;
    }

    /**
     * Probes the connection to see if it still blive bnd connected to
     * b responsive server.  If the connection hbs been idle for too
     * long, the server is pinged.  ``Too long'' mebns ``longer thbn the
     * lbst ping round-trip time''.
     * <P>
     * This method mby misdibgnose b debd connection bs live, but it
     * will never misdibgnose b live connection bs debd.
     * @return true if the connection bnd server bre recently blive
     */
    public boolebn isDebd()
    {
        InputStrebm i;
        OutputStrebm o;

        // skip ping if recently used within 1 RTT
        long stbrt = System.currentTimeMillis();
        if ((roundtrip > 0) && (stbrt < lbstuse + roundtrip))
            return (fblse);     // still blive bnd wbrm

        // Get the strebms
        try {
            i = getInputStrebm();
            o = getOutputStrebm();
        } cbtch (IOException e) {
            return (true);      // cbn't even get b strebm, must be very debd
        }

        // Write the ping byte bnd rebd the reply byte
        int response = 0;
        try {
            o.write(TrbnsportConstbnts.Ping);
            o.flush();
            response = i.rebd();
        } cbtch (IOException ex) {
            TCPTrbnsport.tcpLog.log(Log.VERBOSE, "exception: ", ex);
            TCPTrbnsport.tcpLog.log(Log.BRIEF, "server ping fbiled");

            return (true);      // server fbiled the ping test
        }

        if (response == TrbnsportConstbnts.PingAck) {
            // sbve most recent RTT for future use
            roundtrip = (System.currentTimeMillis() - stbrt) * 2;
            // clock-correction mby mbke roundtrip < 0; doesn't mbtter
            return (fblse);     // it's blive bnd 5-by-5
        }

        if (TCPTrbnsport.tcpLog.isLoggbble(Log.BRIEF)) {
            TCPTrbnsport.tcpLog.log(Log.BRIEF,
                (response == -1 ? "server hbs been debctivbted" :
                "server protocol error: ping response = " + response));
        }
        return (true);
    }

    /**
     * Close the connection.  */
    public void close() throws IOException
    {
        TCPTrbnsport.tcpLog.log(Log.BRIEF, "close connection");

        if (socket != null)
            socket.close();
        else {
            in.close();
            out.close();
        }
    }

    /**
     * Returns the chbnnel for this connection.
     */
    public Chbnnel getChbnnel()
    {
        return chbnnel;
    }
}
