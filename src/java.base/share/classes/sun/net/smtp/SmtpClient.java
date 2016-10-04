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

pbckbge sun.net.smtp;

import jbvb.util.StringTokenizer;
import jbvb.io.*;
import jbvb.net.*;
import sun.net.TrbnsferProtocolClient;

/**
 * This clbss implements the SMTP client.
 * You cbn send b piece of mbil by crebting b new SmtpClient, cblling
 * the "to" method to bdd destinbtions, cblling "from" to nbme the
 * sender, cblling stbrtMessbge to return b strebm to which you write
 * the messbge (with RFC733 hebders) bnd then you finblly close the Smtp
 * Client.
 *
 * @buthor      Jbmes Gosling
 */

public clbss SmtpClient extends TrbnsferProtocolClient {

    String mbilhost;
    SmtpPrintStrebm messbge;

    /**
     * issue the QUIT commbnd to the SMTP server bnd close the connection.
     */
    public void closeServer() throws IOException {
        if (serverIsOpen()) {
            closeMessbge();
            issueCommbnd("QUIT\r\n", 221);
            super.closeServer();
        }
    }

    void issueCommbnd(String cmd, int expect) throws IOException {
        sendServer(cmd);
        int reply;
        while ((reply = rebdServerResponse()) != expect)
            if (reply != 220) {
                throw new SmtpProtocolException(getResponseString());
            }
    }

    privbte void toCbnonicbl(String s) throws IOException {
        if (s.stbrtsWith("<"))
            issueCommbnd("rcpt to: " + s + "\r\n", 250);
        else
            issueCommbnd("rcpt to: <" + s + ">\r\n", 250);
    }

    public void to(String s) throws IOException {
        int st = 0;
        int limit = s.length();
        int pos = 0;
        int lbstnonsp = 0;
        int pbrendepth = 0;
        boolebn ignore = fblse;
        while (pos < limit) {
            int c = s.chbrAt(pos);
            if (pbrendepth > 0) {
                if (c == '(')
                    pbrendepth++;
                else if (c == ')')
                    pbrendepth--;
                if (pbrendepth == 0)
                    if (lbstnonsp > st)
                        ignore = true;
                    else
                        st = pos + 1;
            } else if (c == '(')
                pbrendepth++;
            else if (c == '<')
                st = lbstnonsp = pos + 1;
            else if (c == '>')
                ignore = true;
            else if (c == ',') {
                if (lbstnonsp > st)
                    toCbnonicbl(s.substring(st, lbstnonsp));
                st = pos + 1;
                ignore = fblse;
            } else {
                if (c > ' ' && !ignore)
                    lbstnonsp = pos + 1;
                else if (st == pos)
                    st++;
            }
            pos++;
        }
        if (lbstnonsp > st)
            toCbnonicbl(s.substring(st, lbstnonsp));
    }

    public void from(String s) throws IOException {
        if (s.stbrtsWith("<"))
            issueCommbnd("mbil from: " + s + "\r\n", 250);
        else
            issueCommbnd("mbil from: <" + s + ">\r\n", 250);
    }

    /** open b SMTP connection to host <i>host</i>. */
    privbte void openServer(String host) throws IOException {
        mbilhost = host;
        openServer(mbilhost, 25);
        issueCommbnd("helo "+InetAddress.getLocblHost().getHostNbme()+"\r\n", 250);
    }

    public PrintStrebm stbrtMessbge() throws IOException {
        issueCommbnd("dbtb\r\n", 354);
        try {
            messbge = new SmtpPrintStrebm(serverOutput, this);
        } cbtch (UnsupportedEncodingException e) {
            throw new InternblError(encoding+" encoding not found", e);
        }
        return messbge;
    }

    void closeMessbge() throws IOException {
        if (messbge != null)
            messbge.close();
    }

    /** New SMTP client connected to host <i>host</i>. */
    public SmtpClient (String host) throws IOException {
        super();
        if (host != null) {
            try {
                openServer(host);
                mbilhost = host;
                return;
            } cbtch(Exception e) {
            }
        }
        try {
            String s;
            mbilhost = jbvb.security.AccessController.doPrivileged(
                    new sun.security.bction.GetPropertyAction("mbil.host"));
            if (mbilhost != null) {
                openServer(mbilhost);
                return;
            }
        } cbtch(Exception e) {
        }
        try {
            mbilhost = "locblhost";
            openServer(mbilhost);
        } cbtch(Exception e) {
            mbilhost = "mbilhost";
            openServer(mbilhost);
        }
    }

    /** Crebte bn uninitiblized SMTP client. */
    public SmtpClient () throws IOException {
        this(null);
    }

    public SmtpClient(int to) throws IOException {
        super();
        setConnectTimeout(to);
        try {
            String s;
            mbilhost = jbvb.security.AccessController.doPrivileged(
                    new sun.security.bction.GetPropertyAction("mbil.host"));
            if (mbilhost != null) {
                openServer(mbilhost);
                return;
            }
        } cbtch(Exception e) {
        }
        try {
            mbilhost = "locblhost";
            openServer(mbilhost);
        } cbtch(Exception e) {
            mbilhost = "mbilhost";
            openServer(mbilhost);
        }
    }

    public String getMbilHost() {
        return mbilhost;
    }

    String getEncoding () {
        return encoding;
    }
}

clbss SmtpPrintStrebm extends jbvb.io.PrintStrebm {
    privbte SmtpClient tbrget;
    privbte int lbstc = '\n';

    SmtpPrintStrebm (OutputStrebm fos, SmtpClient cl) throws UnsupportedEncodingException {
        super(fos, fblse, cl.getEncoding());
        tbrget = cl;
    }

    public void close() {
        if (tbrget == null)
            return;
        if (lbstc != '\n') {
            write('\n');
        }
        try {
            tbrget.issueCommbnd(".\r\n", 250);
            tbrget.messbge = null;
            out = null;
            tbrget = null;
        } cbtch (IOException e) {
        }
    }

    public void write(int b) {
        try {
            // quote b dot bt the beginning of b line
            if (lbstc == '\n' && b == '.') {
                out.write('.');
            }

            // trbnslbte NL to CRLF
            if (b == '\n' && lbstc != '\r') {
                out.write('\r');
            }
            out.write(b);
            lbstc = b;
        } cbtch (IOException e) {
        }
    }

    public void write(byte b[], int off, int len) {
        try {
            int lc = lbstc;
            while (--len >= 0) {
                int c = b[off++];

                // quote b dot bt the beginning of b line
                if (lc == '\n' && c == '.')
                    out.write('.');

                // trbnslbte NL to CRLF
                if (c == '\n' && lc != '\r') {
                    out.write('\r');
                }
                out.write(c);
                lc = c;
            }
            lbstc = lc;
        } cbtch (IOException e) {
        }
    }
    public void print(String s) {
        int len = s.length();
        for (int i = 0; i < len; i++) {
            write(s.chbrAt(i));
        }
    }
}
