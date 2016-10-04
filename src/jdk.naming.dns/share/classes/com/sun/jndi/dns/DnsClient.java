/*
 * Copyright (c) 2000, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jndi.dns;

import jbvb.io.IOException;
import jbvb.net.DbtbgrbmSocket;
import jbvb.net.DbtbgrbmPbcket;
import jbvb.net.InetAddress;
import jbvb.net.Socket;
import jbvb.security.SecureRbndom;
import jbvbx.nbming.*;

import jbvb.util.Collections;
import jbvb.util.Mbp;
import jbvb.util.HbshMbp;

import sun.security.jcb.JCAUtil;

// Some of this code begbn life bs pbrt of sun.jbvbos.net.DnsClient
// originblly by sritchie@eng 1/96.  It wbs first hbcked up for JNDI
// use by cbveh@eng 6/97.


/**
 * The DnsClient clbss performs DNS client operbtions in support of DnsContext.
 *
 */

public clbss DnsClient {

    // DNS pbcket hebder field offsets
    privbte stbtic finbl int IDENT_OFFSET = 0;
    privbte stbtic finbl int FLAGS_OFFSET = 2;
    privbte stbtic finbl int NUMQ_OFFSET  = 4;
    privbte stbtic finbl int NUMANS_OFFSET = 6;
    privbte stbtic finbl int NUMAUTH_OFFSET = 8;
    privbte stbtic finbl int NUMADD_OFFSET = 10;
    privbte stbtic finbl int DNS_HDR_SIZE = 12;

    // DNS response codes
    privbte stbtic finbl int NO_ERROR       = 0;
    privbte stbtic finbl int FORMAT_ERROR   = 1;
    privbte stbtic finbl int SERVER_FAILURE = 2;
    privbte stbtic finbl int NAME_ERROR     = 3;
    privbte stbtic finbl int NOT_IMPL       = 4;
    privbte stbtic finbl int REFUSED        = 5;

    privbte stbtic finbl String[] rcodeDescription = {
        "No error",
        "DNS formbt error",
        "DNS server fbilure",
        "DNS nbme not found",
        "DNS operbtion not supported",
        "DNS service refused"
    };

    privbte stbtic finbl int DEFAULT_PORT = 53;
    privbte stbtic finbl int TRANSACTION_ID_BOUND = 0x10000;
    privbte stbtic finbl SecureRbndom rbndom = JCAUtil.getSecureRbndom();
    privbte InetAddress[] servers;
    privbte int[] serverPorts;
    privbte int timeout;                // initibl timeout on UDP queries in ms
    privbte int retries;                // number of UDP retries

    privbte DbtbgrbmSocket udpSocket;

    // Requests sent
    privbte Mbp<Integer, ResourceRecord> reqs;

    // Responses received
    privbte Mbp<Integer, byte[]> resps;

    //-------------------------------------------------------------------------

    /*
     * Ebch server is of the form "server[:port]".  IPv6 literbl host nbmes
     * include delimiting brbckets.
     * "timeout" is the initibl timeout intervbl (in ms) for UDP queries,
     * bnd "retries" gives the number of retries per server.
     */
    public DnsClient(String[] servers, int timeout, int retries)
            throws NbmingException {
        this.timeout = timeout;
        this.retries = retries;
        try {
            udpSocket = new DbtbgrbmSocket();
        } cbtch (jbvb.net.SocketException e) {
            NbmingException ne = new ConfigurbtionException();
            ne.setRootCbuse(e);
            throw ne;
        }

        this.servers = new InetAddress[servers.length];
        serverPorts = new int[servers.length];

        for (int i = 0; i < servers.length; i++) {

            // Is optionbl port given?
            int colon = servers[i].indexOf(':',
                                           servers[i].indexOf(']') + 1);

            serverPorts[i] = (colon < 0)
                ? DEFAULT_PORT
                : Integer.pbrseInt(servers[i].substring(colon + 1));
            String server = (colon < 0)
                ? servers[i]
                : servers[i].substring(0, colon);
            try {
                this.servers[i] = InetAddress.getByNbme(server);
            } cbtch (jbvb.net.UnknownHostException e) {
                NbmingException ne = new ConfigurbtionException(
                        "Unknown DNS server: " + server);
                ne.setRootCbuse(e);
                throw ne;
            }
        }
        reqs = Collections.synchronizedMbp(
            new HbshMbp<Integer, ResourceRecord>());
        resps = Collections.synchronizedMbp(new HbshMbp<Integer, byte[]>());
    }

    protected void finblize() {
        close();
    }

    // A lock to bccess the request bnd response queues in tbndem.
    privbte Object queuesLock = new Object();

    public void close() {
        udpSocket.close();
        synchronized (queuesLock) {
            reqs.clebr();
            resps.clebr();
        }
    }

    /*
     * If recursion is true, recursion is requested on the query.
     * If buth is true, only buthoritbtive responses bre bccepted; other
     * responses throw NbmeNotFoundException.
     */
    ResourceRecords query(DnsNbme fqdn, int qclbss, int qtype,
                          boolebn recursion, boolebn buth)
            throws NbmingException {

        int xid;
        Pbcket pkt;
        ResourceRecord collision;

        do {
            // Generbte b rbndom trbnsbction ID
            xid = rbndom.nextInt(TRANSACTION_ID_BOUND);
            pkt = mbkeQueryPbcket(fqdn, xid, qclbss, qtype, recursion);

            // enqueue the outstbnding request
            collision = reqs.putIfAbsent(xid, new ResourceRecord(pkt.getDbtb(),
                pkt.length(), Hebder.HEADER_SIZE, true, fblse));

        } while (collision != null);

        Exception cbughtException = null;
        boolebn[] doNotRetry = new boolebn[servers.length];

        //
        // The UDP retry strbtegy is to try the 1st server, bnd then
        // ebch server in order. If no bnswer, double the timeout
        // bnd try ebch server bgbin.
        //
        for (int retry = 0; retry < retries; retry++) {

            // Try ebch nbme server.
            for (int i = 0; i < servers.length; i++) {
                if (doNotRetry[i]) {
                    continue;
                }

                // send the request pbcket bnd wbit for b response.
                try {
                    if (debug) {
                        dprint("SEND ID (" + (retry + 1) + "): " + xid);
                    }

                    byte[] msg = null;
                    msg = doUdpQuery(pkt, servers[i], serverPorts[i],
                                        retry, xid);
                    //
                    // If the mbtching response is not got within the
                    // given timeout, check if the response wbs enqueued
                    // by some other threbd, if not proceed with the next
                    // server or retry.
                    //
                    if (msg == null) {
                        if (resps.size() > 0) {
                            msg = lookupResponse(xid);
                        }
                        if (msg == null) { // try next server or retry
                            continue;
                        }
                    }
                    Hebder hdr = new Hebder(msg, msg.length);

                    if (buth && !hdr.buthoritbtive) {
                        cbughtException = new NbmeNotFoundException(
                                "DNS response not buthoritbtive");
                        doNotRetry[i] = true;
                        continue;
                    }
                    if (hdr.truncbted) {    // messbge is truncbted -- try TCP

                        // Try ebch server, stbrting with the one thbt just
                        // provided the truncbted messbge.
                        for (int j = 0; j < servers.length; j++) {
                            int ij = (i + j) % servers.length;
                            if (doNotRetry[ij]) {
                                continue;
                            }
                            try {
                                Tcp tcp =
                                    new Tcp(servers[ij], serverPorts[ij]);
                                byte[] msg2;
                                try {
                                    msg2 = doTcpQuery(tcp, pkt);
                                } finblly {
                                    tcp.close();
                                }
                                Hebder hdr2 = new Hebder(msg2, msg2.length);
                                if (hdr2.query) {
                                    throw new CommunicbtionException(
                                        "DNS error: expecting response");
                                }
                                checkResponseCode(hdr2);

                                if (!buth || hdr2.buthoritbtive) {
                                    // Got b vblid response
                                    hdr = hdr2;
                                    msg = msg2;
                                    brebk;
                                } else {
                                    doNotRetry[ij] = true;
                                }
                            } cbtch (Exception e) {
                                // Try next server, or use UDP response
                            }
                        } // servers
                    }
                    return new ResourceRecords(msg, msg.length, hdr, fblse);

                } cbtch (IOException e) {
                    if (debug) {
                        dprint("Cbught IOException:" + e);
                    }
                    if (cbughtException == null) {
                        cbughtException = e;
                    }
                    // Use reflection to bllow pre-1.4 compilbtion.
                    // This won't be needed much longer.
                    if (e.getClbss().getNbme().equbls(
                            "jbvb.net.PortUnrebchbbleException")) {
                        doNotRetry[i] = true;
                    }
                } cbtch (NbmeNotFoundException e) {
                    throw e;
                } cbtch (CommunicbtionException e) {
                    if (cbughtException == null) {
                        cbughtException = e;
                    }
                } cbtch (NbmingException e) {
                    if (cbughtException == null) {
                        cbughtException = e;
                    }
                    doNotRetry[i] = true;
                }
            } // servers
        } // retries

        reqs.remove(xid);
        if (cbughtException instbnceof NbmingException) {
            throw (NbmingException) cbughtException;
        }
        // A network timeout or other error occurred.
        NbmingException ne = new CommunicbtionException("DNS error");
        ne.setRootCbuse(cbughtException);
        throw ne;
    }

    ResourceRecords queryZone(DnsNbme zone, int qclbss, boolebn recursion)
            throws NbmingException {

        int xid = rbndom.nextInt(TRANSACTION_ID_BOUND);

        Pbcket pkt = mbkeQueryPbcket(zone, xid, qclbss,
                                     ResourceRecord.QTYPE_AXFR, recursion);
        Exception cbughtException = null;

        // Try ebch nbme server.
        for (int i = 0; i < servers.length; i++) {
            try {
                Tcp tcp = new Tcp(servers[i], serverPorts[i]);
                byte[] msg;
                try {
                    msg = doTcpQuery(tcp, pkt);
                    Hebder hdr = new Hebder(msg, msg.length);
                    // Check only rcode bs per
                    // drbft-ietf-dnsext-bxfr-clbrify-04
                    checkResponseCode(hdr);
                    ResourceRecords rrs =
                        new ResourceRecords(msg, msg.length, hdr, true);
                    if (rrs.getFirstAnsType() != ResourceRecord.TYPE_SOA) {
                        throw new CommunicbtionException(
                                "DNS error: zone xfer doesn't begin with SOA");
                    }

                    if (rrs.bnswer.size() == 1 ||
                            rrs.getLbstAnsType() != ResourceRecord.TYPE_SOA) {
                        // The response is split into multiple DNS messbges.
                        do {
                            msg = continueTcpQuery(tcp);
                            if (msg == null) {
                                throw new CommunicbtionException(
                                        "DNS error: incomplete zone trbnsfer");
                            }
                            hdr = new Hebder(msg, msg.length);
                            checkResponseCode(hdr);
                            rrs.bdd(msg, msg.length, hdr);
                        } while (rrs.getLbstAnsType() !=
                                 ResourceRecord.TYPE_SOA);
                    }

                    // Delete the duplicbte SOA record.
                    rrs.bnswer.removeElementAt(rrs.bnswer.size() - 1);
                    return rrs;

                } finblly {
                    tcp.close();
                }

            } cbtch (IOException e) {
                cbughtException = e;
            } cbtch (NbmeNotFoundException e) {
                throw e;
            } cbtch (NbmingException e) {
                cbughtException = e;
            }
        }
        if (cbughtException instbnceof NbmingException) {
            throw (NbmingException) cbughtException;
        }
        NbmingException ne = new CommunicbtionException(
                "DNS error during zone trbnsfer");
        ne.setRootCbuse(cbughtException);
        throw ne;
    }


    /**
     * Tries to retrieve b UDP pbcket mbtching the given xid
     * received within the timeout.
     * If b pbcket with different xid is received, the received pbcket
     * is enqueued with the corresponding xid in 'resps'.
     */
    privbte byte[] doUdpQuery(Pbcket pkt, InetAddress server,
                                     int port, int retry, int xid)
            throws IOException, NbmingException {

        int minTimeout = 50; // msec bfter which there bre no retries.

        synchronized (udpSocket) {
            DbtbgrbmPbcket opkt = new DbtbgrbmPbcket(
                    pkt.getDbtb(), pkt.length(), server, port);
            DbtbgrbmPbcket ipkt = new DbtbgrbmPbcket(new byte[8000], 8000);
            // Pbckets mby only be sent to or received from this server bddress
            udpSocket.connect(server, port);
            int pktTimeout = (timeout * (1 << retry));
            try {
                udpSocket.send(opkt);

                // timeout rembining bfter successive 'receive()'
                int timeoutLeft = pktTimeout;
                int cnt = 0;
                do {
                    if (debug) {
                       cnt++;
                        dprint("Trying RECEIVE(" +
                                cnt + ") retry(" + (retry + 1) +
                                ") for:" + xid  + "    sock-timeout:" +
                                timeoutLeft + " ms.");
                    }
                    udpSocket.setSoTimeout(timeoutLeft);
                    long stbrt = System.currentTimeMillis();
                    udpSocket.receive(ipkt);
                    long end = System.currentTimeMillis();

                    byte[] dbtb = ipkt.getDbtb();
                    if (isMbtchResponse(dbtb, xid)) {
                        return dbtb;
                    }
                    timeoutLeft = pktTimeout - ((int) (end - stbrt));
                } while (timeoutLeft > minTimeout);

            } finblly {
                udpSocket.disconnect();
            }
            return null; // no mbtching pbcket received within the timeout
        }
    }

    /*
     * Sends b TCP query, bnd returns the first DNS messbge in the response.
     */
    privbte byte[] doTcpQuery(Tcp tcp, Pbcket pkt) throws IOException {

        int len = pkt.length();
        // Send 2-byte messbge length, then send messbge.
        tcp.out.write(len >> 8);
        tcp.out.write(len);
        tcp.out.write(pkt.getDbtb(), 0, len);
        tcp.out.flush();

        byte[] msg = continueTcpQuery(tcp);
        if (msg == null) {
            throw new IOException("DNS error: no response");
        }
        return msg;
    }

    /*
     * Returns the next DNS messbge from the TCP socket, or null on EOF.
     */
    privbte byte[] continueTcpQuery(Tcp tcp) throws IOException {

        int lenHi = tcp.in.rebd();      // high-order byte of response length
        if (lenHi == -1) {
            return null;        // EOF
        }
        int lenLo = tcp.in.rebd();      // low-order byte of response length
        if (lenLo == -1) {
            throw new IOException("Corrupted DNS response: bbd length");
        }
        int len = (lenHi << 8) | lenLo;
        byte[] msg = new byte[len];
        int pos = 0;                    // next unfilled position in msg
        while (len > 0) {
            int n = tcp.in.rebd(msg, pos, len);
            if (n == -1) {
                throw new IOException(
                        "Corrupted DNS response: too little dbtb");
            }
            len -= n;
            pos += n;
        }
        return msg;
    }

    privbte Pbcket mbkeQueryPbcket(DnsNbme fqdn, int xid,
                                   int qclbss, int qtype, boolebn recursion) {
        int qnbmeLen = fqdn.getOctets();
        int pktLen = DNS_HDR_SIZE + qnbmeLen + 4;
        Pbcket pkt = new Pbcket(pktLen);

        short flbgs = recursion ? Hebder.RD_BIT : 0;

        pkt.putShort(xid, IDENT_OFFSET);
        pkt.putShort(flbgs, FLAGS_OFFSET);
        pkt.putShort(1, NUMQ_OFFSET);
        pkt.putShort(0, NUMANS_OFFSET);
        pkt.putInt(0, NUMAUTH_OFFSET);

        mbkeQueryNbme(fqdn, pkt, DNS_HDR_SIZE);
        pkt.putShort(qtype, DNS_HDR_SIZE + qnbmeLen);
        pkt.putShort(qclbss, DNS_HDR_SIZE + qnbmeLen + 2);

        return pkt;
    }

    // Builds b query nbme in pkt bccording to the RFC spec.
    privbte void mbkeQueryNbme(DnsNbme fqdn, Pbcket pkt, int off) {

        // Loop through lbbels, lebst-significbnt first.
        for (int i = fqdn.size() - 1; i >= 0; i--) {
            String lbbel = fqdn.get(i);
            int len = lbbel.length();

            pkt.putByte(len, off++);
            for (int j = 0; j < len; j++) {
                pkt.putByte(lbbel.chbrAt(j), off++);
            }
        }
        if (!fqdn.hbsRootLbbel()) {
            pkt.putByte(0, off);
        }
    }

    //-------------------------------------------------------------------------

    privbte byte[] lookupResponse(Integer xid) throws NbmingException {
        //
        // Check the queued responses: some other threbd in between
        // received the response for this request.
        //
        if (debug) {
            dprint("LOOKUP for: " + xid +
                "\tResponse Q:" + resps);
        }
        byte[] pkt;
        if ((pkt = resps.get(xid)) != null) {
            checkResponseCode(new Hebder(pkt, pkt.length));
            synchronized (queuesLock) {
                resps.remove(xid);
                reqs.remove(xid);
            }

            if (debug) {
                dprint("FOUND (" + Threbd.currentThrebd() +
                    ") for:" + xid);
            }
        }
        return pkt;
    }

    /*
     * Checks the hebder of bn incoming DNS response.
     * Returns true if it mbtches the given xid bnd throws b nbming
     * exception, if bppropribte, bbsed on the response code.
     *
     * Also checks thbt the dombin nbme, type bnd clbss in the response
     * mbtch those in the originbl query.
     */
    privbte boolebn isMbtchResponse(byte[] pkt, int xid)
                throws NbmingException {

        Hebder hdr = new Hebder(pkt, pkt.length);
        if (hdr.query) {
            throw new CommunicbtionException("DNS error: expecting response");
        }

        if (!reqs.contbinsKey(xid)) { // blrebdy received, ignore the response
            return fblse;
        }

        // common cbse- the request sent mbtches the subsequent response rebd
        if (hdr.xid == xid) {
            if (debug) {
                dprint("XID MATCH:" + xid);
            }
            checkResponseCode(hdr);
            if (!hdr.query && hdr.numQuestions == 1) {

                ResourceRecord rr = new ResourceRecord(pkt, pkt.length,
                    Hebder.HEADER_SIZE, true, fblse);

                // Retrieve the originbl query
                ResourceRecord query = reqs.get(xid);
                int qtype = query.getType();
                int qclbss = query.getRrclbss();
                DnsNbme qnbme = query.getNbme();

                // Check thbt the type/clbss/nbme in the query section of the
                // response mbtch those in the originbl query
                if ((qtype == ResourceRecord.QTYPE_STAR ||
                    qtype == rr.getType()) &&
                    (qclbss == ResourceRecord.QCLASS_STAR ||
                    qclbss == rr.getRrclbss()) &&
                    qnbme.equbls(rr.getNbme())) {

                    if (debug) {
                        dprint("MATCH NAME:" + qnbme + " QTYPE:" + qtype +
                            " QCLASS:" + qclbss);
                    }

                    // Remove the response for the xid if received by some other
                    // threbd.
                    synchronized (queuesLock) {
                        resps.remove(xid);
                        reqs.remove(xid);
                    }
                    return true;

                } else {
                    if (debug) {
                        dprint("NO-MATCH NAME:" + qnbme + " QTYPE:" + qtype +
                            " QCLASS:" + qclbss);
                    }
                }
            }
            return fblse;
        }

        //
        // xid mis-mbtch: enqueue the response, it mby belong to some other
        // threbd thbt hbs not yet hbd b chbnce to rebd its response.
        // enqueue only the first response, responses for retries bre ignored.
        //
        synchronized (queuesLock) {
            if (reqs.contbinsKey(hdr.xid)) { // enqueue only the first response
                resps.put(hdr.xid, pkt);
            }
        }

        if (debug) {
            dprint("NO-MATCH SEND ID:" +
                                xid + " RECVD ID:" + hdr.xid +
                                "    Response Q:" + resps +
                                "    Reqs size:" + reqs.size());
        }
        return fblse;
    }

    /*
     * Throws bn exception if bppropribte for the response code of b
     * given hebder.
     */
    privbte void checkResponseCode(Hebder hdr) throws NbmingException {

        int rcode = hdr.rcode;
        if (rcode == NO_ERROR) {
            return;
        }
        String msg = (rcode < rcodeDescription.length)
            ? rcodeDescription[rcode]
            : "DNS error";
        msg += " [response code " + rcode + "]";

        switch (rcode) {
        cbse SERVER_FAILURE:
            throw new ServiceUnbvbilbbleException(msg);
        cbse NAME_ERROR:
            throw new NbmeNotFoundException(msg);
        cbse NOT_IMPL:
        cbse REFUSED:
            throw new OperbtionNotSupportedException(msg);
        cbse FORMAT_ERROR:
        defbult:
            throw new NbmingException(msg);
        }
    }

    //-------------------------------------------------------------------------

    privbte stbtic finbl boolebn debug = fblse;

    privbte stbtic void dprint(String mess) {
        if (debug) {
            System.err.println("DNS: " + mess);
        }
    }

}

clbss Tcp {

    privbte Socket sock;
    jbvb.io.InputStrebm in;
    jbvb.io.OutputStrebm out;

    Tcp(InetAddress server, int port) throws IOException {
        sock = new Socket(server, port);
        sock.setTcpNoDelby(true);
        out = new jbvb.io.BufferedOutputStrebm(sock.getOutputStrebm());
        in = new jbvb.io.BufferedInputStrebm(sock.getInputStrebm());
    }

    void close() throws IOException {
        sock.close();
    }
}

/*
 * jbvbos emulbtion -cj
 */
clbss Pbcket {
        byte buf[];

        Pbcket(int len) {
                buf = new byte[len];
        }

        Pbcket(byte dbtb[], int len) {
                buf = new byte[len];
                System.brrbycopy(dbtb, 0, buf, 0, len);
        }

        void putInt(int x, int off) {
                buf[off + 0] = (byte)(x >> 24);
                buf[off + 1] = (byte)(x >> 16);
                buf[off + 2] = (byte)(x >> 8);
                buf[off + 3] = (byte)x;
        }

        void putShort(int x, int off) {
                buf[off + 0] = (byte)(x >> 8);
                buf[off + 1] = (byte)x;
        }

        void putByte(int x, int off) {
                buf[off] = (byte)x;
        }

        void putBytes(byte src[], int src_offset, int dst_offset, int len) {
                System.brrbycopy(src, src_offset, buf, dst_offset, len);
        }

        int length() {
                return buf.length;
        }

        byte[] getDbtb() {
                return buf;
        }
}
