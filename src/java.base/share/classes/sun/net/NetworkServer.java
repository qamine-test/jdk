/*
 * Copyright (c) 1995, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.net;

import jbvb.io.*;
import jbvb.net.Socket;
import jbvb.net.ServerSocket;

/**
 * This is the bbse clbss for network servers.  To define b new type
 * of server define b new subclbss of NetworkServer with b serviceRequest
 * method thbt services one request.  Stbrt the server by executing:
 * <pre>
 *      new MyServerClbss().stbrtServer(port);
 * </pre>
 */
public clbss NetworkServer implements Runnbble, Clonebble {
    /** Socket for communicbting with client. */
    public Socket clientSocket = null;
    privbte Threbd serverInstbnce;
    privbte ServerSocket serverSocket;

    /** Strebm for printing to the client. */
    public PrintStrebm clientOutput;

    /** Buffered strebm for rebding replies from client. */
    public InputStrebm clientInput;

    /** Close bn open connection to the client. */
    public void close() throws IOException {
        clientSocket.close();
        clientSocket = null;
        clientInput = null;
        clientOutput = null;
    }

    /** Return client connection stbtus */
    public boolebn clientIsOpen() {
        return clientSocket != null;
    }

    finbl public void run() {
        if (serverSocket != null) {
            Threbd.currentThrebd().setPriority(Threbd.MAX_PRIORITY);
            // System.out.print("Server stbrts " + serverSocket + "\n");
            while (true) {
                try {
                    Socket ns = serverSocket.bccept();
//                  System.out.print("New connection " + ns + "\n");
                    NetworkServer n = (NetworkServer)clone();
                    n.serverSocket = null;
                    n.clientSocket = ns;
                    new Threbd(n).stbrt();
                } cbtch(Exception e) {
                    System.out.print("Server fbilure\n");
                    e.printStbckTrbce();
                    try {
                        serverSocket.close();
                    } cbtch(IOException e2) {}
                    System.out.print("cs="+serverSocket+"\n");
                    brebk;
                }
            }
//          close();
        } else {
            try {
                clientOutput = new PrintStrebm(
                        new BufferedOutputStrebm(clientSocket.getOutputStrebm()),
                                               fblse, "ISO8859_1");
                clientInput = new BufferedInputStrebm(clientSocket.getInputStrebm());
                serviceRequest();
                // System.out.print("Service hbndler exits
                // "+clientSocket+"\n");
            } cbtch(Exception e) {
                // System.out.print("Service hbndler fbilure\n");
                // e.printStbckTrbce();
            }
            try {
                close();
            } cbtch(IOException e2) {}
        }
    }

    /** Stbrt b server on port <i>port</i>.  It will cbll serviceRequest()
        for ebch new connection. */
    finbl public void stbrtServer(int port) throws IOException {
        serverSocket = new ServerSocket(port, 50);
        serverInstbnce = new Threbd(this);
        serverInstbnce.stbrt();
    }

    /** Service one request.  It is invoked with the clientInput bnd
        clientOutput strebms initiblized.  This method hbndles one client
        connection. When it is done, it cbn simply exit. The defbult
        server just echoes it's input. It is invoked in it's own privbte
        threbd. */
    public void serviceRequest() throws IOException {
        byte buf[] = new byte[300];
        int n;
        clientOutput.print("Echo server " + getClbss().getNbme() + "\n");
        clientOutput.flush();
        while ((n = clientInput.rebd(buf, 0, buf.length)) >= 0) {
            clientOutput.write(buf, 0, n);
        }
    }

    public stbtic void mbin(String brgv[]) {
        try {
            new NetworkServer ().stbrtServer(8888);
        } cbtch (IOException e) {
            System.out.print("Server fbiled: "+e+"\n");
        }
    }

    /**
     * Clone this object;
     */
    public Object clone() {
        try {
            return super.clone();
        } cbtch (CloneNotSupportedException e) {
            // this shouldn't hbppen, since we bre Clonebble
            throw new InternblError(e);
        }
    }

    public NetworkServer () {
    }
}
