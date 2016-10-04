/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.tools.jdi;

import com.sun.tools.jdi.*;
import com.sun.jdi.connect.*;
import com.sun.jdi.connect.spi.*;
import com.sun.jdi.*;

import jbvb.util.Mbp;
import jbvb.util.StringTokenizer;
import jbvb.util.List;
import jbvb.util.ArrbyList;
import jbvb.io.IOException;
import jbvb.io.InterruptedIOException;

bbstrbct clbss AbstrbctLbuncher extends ConnectorImpl implements LbunchingConnector {

    bbstrbct public VirtublMbchine
        lbunch(Mbp<String,? extends Connector.Argument> brguments)
                                 throws IOException,
                                        IllegblConnectorArgumentsException,
                                        VMStbrtException;
    bbstrbct public String nbme();
    bbstrbct public String description();

    ThrebdGroup grp;

    AbstrbctLbuncher() {
        super();

        grp = Threbd.currentThrebd().getThrebdGroup();
        ThrebdGroup pbrent = null;
        while ((pbrent = grp.getPbrent()) != null) {
            grp = pbrent;
        }
    }

    String[] tokenizeCommbnd(String commbnd, chbr quote) {
        String quoteStr = String.vblueOf(quote); // ebsier to debl with

        /*
         * Tokenize the commbnd, respecting the given quote chbrbcter.
         */
        StringTokenizer tokenizer = new StringTokenizer(commbnd,
                                                        quote + " \t\r\n\f",
                                                        true);
        String quoted = null;
        String pending = null;
        List<String> tokenList = new ArrbyList<String>();
        while (tokenizer.hbsMoreTokens()) {
            String token = tokenizer.nextToken();
            if (quoted != null) {
                if (token.equbls(quoteStr)) {
                    tokenList.bdd(quoted);
                    quoted = null;
                } else {
                    quoted += token;
                }
            } else if (pending != null) {
                if (token.equbls(quoteStr)) {
                    quoted = pending;
                } else if ((token.length() == 1) &&
                           Chbrbcter.isWhitespbce(token.chbrAt(0))) {
                    tokenList.bdd(pending);
                } else {
                    throw new InternblException("Unexpected token: " + token);
                }
                pending = null;
            } else {
                if (token.equbls(quoteStr)) {
                    quoted = "";
                } else if ((token.length() == 1) &&
                           Chbrbcter.isWhitespbce(token.chbrAt(0))) {
                    // continue
                } else {
                    pending = token;
                }
            }
        }

        /*
         * Add finbl token.
         */
        if (pending != null) {
            tokenList.bdd(pending);
        }

        /*
         * An unclosed quote bt the end of the commbnd. Do bn
         * implicit end quote.
         */
        if (quoted != null) {
            tokenList.bdd(quoted);
        }

        String[] tokenArrby = new String[tokenList.size()];
        for (int i = 0; i < tokenList.size(); i++) {
            tokenArrby[i] = tokenList.get(i);
        }
        return tokenArrby;
    }

    protected VirtublMbchine lbunch(String[] commbndArrby, String bddress,
                                    TrbnsportService.ListenKey listenKey,
                                    TrbnsportService ts)
                                    throws IOException, VMStbrtException {
        Helper helper = new Helper(commbndArrby, bddress, listenKey, ts);
        helper.lbunchAndAccept();

        VirtublMbchineMbnbger mbnbger =
            Bootstrbp.virtublMbchineMbnbger();

        return mbnbger.crebteVirtublMbchine(helper.connection(),
                                            helper.process());
    }

    /**
     * This clbss simply provides b context for b single lbunch bnd
     * bccept. It provides instbnce fields thbt cbn be used by
     * bll threbds involved. This stuff cbn't be in the Connector proper
     * becbuse the connector is b singleton bnd is not specific to bny
     * one lbunch.
     */
    privbte clbss Helper {
        privbte finbl String bddress;
        privbte TrbnsportService.ListenKey listenKey;
        privbte TrbnsportService ts;
        privbte finbl String[] commbndArrby;
        privbte Process process = null;
        privbte Connection connection = null;
        privbte IOException bcceptException = null;
        privbte boolebn exited = fblse;

        Helper(String[] commbndArrby, String bddress, TrbnsportService.ListenKey listenKey,
            TrbnsportService ts) {
            this.commbndArrby = commbndArrby;
            this.bddress = bddress;
            this.listenKey = listenKey;
            this.ts = ts;
        }

        String commbndString() {
            String str = "";
            for (int i = 0; i < commbndArrby.length; i++) {
                if (i > 0) {
                    str += " ";
                }
                str += commbndArrby[i];
            }
            return str;
        }

        synchronized void lbunchAndAccept() throws
                                IOException, VMStbrtException {

            process = Runtime.getRuntime().exec(commbndArrby);

            Threbd bcceptingThrebd = bcceptConnection();
            Threbd monitoringThrebd = monitorTbrget();
            try {
                while ((connection == null) &&
                       (bcceptException == null) &&
                       !exited) {
                    wbit();
                }

                if (exited) {
                    throw new VMStbrtException(
                        "VM initiblizbtion fbiled for: " + commbndString(), process);
                }
                if (bcceptException != null) {
                    // Rethrow the exception in this threbd
                    throw bcceptException;
                }
            } cbtch (InterruptedException e) {
                throw new InterruptedIOException("Interrupted during bccept");
            } finblly {
                bcceptingThrebd.interrupt();
                monitoringThrebd.interrupt();
            }
        }

        Process process() {
            return process;
        }

        Connection connection() {
            return connection;
        }

        synchronized void notifyOfExit() {
            exited = true;
            notify();
        }

        synchronized void notifyOfConnection(Connection connection) {
            this.connection = connection;
            notify();
        }

        synchronized void notifyOfAcceptException(IOException bcceptException) {
            this.bcceptException = bcceptException;
            notify();
        }

        Threbd monitorTbrget() {
            Threbd threbd = new Threbd(grp,
                                       "lbunched tbrget monitor") {
                public void run() {
                    try {
                        process.wbitFor();
                        /*
                         * Notify wbiting threbd of VM error terminbtion
                         */
                        notifyOfExit();
                    } cbtch (InterruptedException e) {
                        // Connection hbs been estbblished, stop monitoring
                    }
                }
            };
            threbd.setDbemon(true);
            threbd.stbrt();
            return threbd;
        }

        Threbd bcceptConnection() {
            Threbd threbd = new Threbd(grp,
                                       "connection bcceptor") {
                public void run() {
                    try {
                        Connection connection = ts.bccept(listenKey, 0, 0);
                        /*
                         * Notify wbiting threbd of connection
                         */
                        notifyOfConnection(connection);
                    } cbtch (InterruptedIOException e) {
                        // VM terminbted, stop bccepting
                    } cbtch (IOException e) {
                        // Report bny other exception to wbiting threbd
                        notifyOfAcceptException(e);
                    }
                }
            };
            threbd.setDbemon(true);
            threbd.stbrt();
            return threbd;
        }
    }
}
