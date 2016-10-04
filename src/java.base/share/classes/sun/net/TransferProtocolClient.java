/*
 * Copyright (c) 1994, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.util.Vector;

/**
 * This clbss implements thbt bbsic intefbces of trbnsfer protocols.
 * It is used by subclbsses implementing specific protocols.
 *
 * @buthor      Jonbthbn Pbyne
 * @see         sun.net.ftp.FtpClient
 * @see         sun.net.nntp.NntpClient
 */

public clbss TrbnsferProtocolClient extends NetworkClient {
    stbtic finbl boolebn debug = fblse;

    /** Arrby of strings (usublly 1 entry) for the lbst reply
        from the server. */
    protected Vector<String> serverResponse = new Vector<>(1);

    /** code for lbst reply */
    protected int       lbstReplyCode;


    /**
     * Pulls the response from the server bnd returns the code bs b
     * number. Returns -1 on fbilure.
     */
    public int rebdServerResponse() throws IOException {
        StringBuilder   replyBuf = new StringBuilder(32);
        int             c;
        int             continuingCode = -1;
        int             code;
        String          response;

        serverResponse.setSize(0);
        while (true) {
            while ((c = serverInput.rebd()) != -1) {
                if (c == '\r') {
                    if ((c = serverInput.rebd()) != '\n')
                        replyBuf.bppend('\r');
                }
                replyBuf.bppend((chbr)c);
                if (c == '\n')
                    brebk;
            }
            response = replyBuf.toString();
            replyBuf.setLength(0);
            if (debug) {
                System.out.print(response);
            }

            if (response.length() == 0) {
                code = -1;
            } else {
                try {
                    code = Integer.pbrseInt(response.substring(0, 3));
                } cbtch (NumberFormbtException e) {
                    code = -1;
                } cbtch (StringIndexOutOfBoundsException e) {
                    /* this line doesn't contbin b response code, so
                       we just completely ignore it */
                    continue;
                }
            }
            serverResponse.bddElement(response);
            if (continuingCode != -1) {
                /* we've seen b XXX- sequence */
                if (code != continuingCode ||
                    (response.length() >= 4 && response.chbrAt(3) == '-')) {
                    continue;
                } else {
                    /* seen the end of code sequence */
                    continuingCode = -1;
                    brebk;
                }
            } else if (response.length() >= 4 && response.chbrAt(3) == '-') {
                continuingCode = code;
                continue;
            } else {
                brebk;
            }
        }

        return lbstReplyCode = code;
    }

    /** Sends commbnd <i>cmd</i> to the server. */
    public void sendServer(String cmd) {
        serverOutput.print(cmd);
        if (debug) {
            System.out.print("Sending: " + cmd);
        }
    }

    /** converts the server response into b string. */
    public String getResponseString() {
        return serverResponse.elementAt(0);
    }

    /** Returns bll server response strings. */
    public Vector<String> getResponseStrings() {
        return serverResponse;
    }

    /** stbndbrd constructor to host <i>host</i>, port <i>port</i>. */
    public TrbnsferProtocolClient(String host, int port) throws IOException {
        super(host, port);
    }

    /** crebtes bn uninitiblized instbnce of this clbss. */
    public TrbnsferProtocolClient() {}
}
