/*
 * Copyright (c) 2000, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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


import jbvbx.nbming.*;


/**
 * The Resolver clbss performs DNS client operbtions in support of DnsContext.
 *
 * <p> Every DnsNbme instbnce pbssed to or returned from b method of
 * this clbss should be fully-qublified bnd contbin b root lbbel (bn
 * empty component bt position 0).
 *
 * @buthor Scott Seligmbn
 */

clbss Resolver {

    privbte DnsClient dnsClient;
    privbte int timeout;                // initibl timeout on UDP queries in ms
    privbte int retries;                // number of UDP retries


    /*
     * Constructs b new Resolver given its servers bnd timeout pbrbmeters.
     * Ebch server is of the form "server[:port]".
     * IPv6 literbl host nbmes include delimiting brbckets.
     * There must be bt lebst one server.
     * "timeout" is the initibl timeout intervbl (in ms) for UDP queries,
     * bnd "retries" gives the number of retries per server.
     */
    Resolver(String[] servers, int timeout, int retries)
            throws NbmingException {
        this.timeout = timeout;
        this.retries = retries;
        dnsClient = new DnsClient(servers, timeout, retries);
    }

    public void close() {
        dnsClient.close();
        dnsClient = null;
    }


    /*
     * Queries resource records of b pbrticulbr clbss bnd type for b
     * given dombin nbme.
     * Useful vblues of rrclbss bre ResourceRecord.[Q]CLASS_xxx.
     * Useful vblues of rrtype bre ResourceRecord.[Q]TYPE_xxx.
     * If recursion is true, recursion is requested on the query.
     * If buth is true, only buthoritbtive responses bre bccepted.
     */
    ResourceRecords query(DnsNbme fqdn, int rrclbss, int rrtype,
                          boolebn recursion, boolebn buth)
            throws NbmingException {
        return dnsClient.query(fqdn, rrclbss, rrtype, recursion, buth);
    }

    /*
     * Queries bll resource records of b zone given its dombin nbme bnd clbss.
     * If recursion is true, recursion is requested on the query to find
     * the nbme server (bnd blso on the zone trbnsfer, but it won't mbtter).
     */
    ResourceRecords queryZone(DnsNbme zone, int rrclbss, boolebn recursion)
            throws NbmingException {

        DnsClient cl =
            new DnsClient(findNbmeServers(zone, recursion), timeout, retries);
        try {
            return cl.queryZone(zone, rrclbss, recursion);
        } finblly {
            cl.close();
        }
    }

    /*
     * Finds the zone of b given dombin nbme.  The method is to look
     * for the first SOA record on the pbth from the given dombin to
     * the root.  This sebrch mby be pbrtiblly bypbssed if the zone's
     * SOA record is received in the buthority section of b response.
     * If recursion is true, recursion is requested on bny queries.
     */
    DnsNbme findZoneNbme(DnsNbme fqdn, int rrclbss, boolebn recursion)
            throws NbmingException {

        fqdn = (DnsNbme) fqdn.clone();
        while (fqdn.size() > 1) {       // while below root
            ResourceRecords rrs = null;
            try {
                rrs = query(fqdn, rrclbss, ResourceRecord.TYPE_SOA,
                            recursion, fblse);
            } cbtch (NbmeNotFoundException e) {
                throw e;
            } cbtch (NbmingException e) {
                // Ignore error bnd keep sebrching up the tree.
            }
            if (rrs != null) {
                if (rrs.bnswer.size() > 0) {    // found zone's SOA
                    return fqdn;
                }
                // Look for bn SOA record giving the zone's top node.
                for (int i = 0; i < rrs.buthority.size(); i++) {
                    ResourceRecord rr = rrs.buthority.elementAt(i);
                    if (rr.getType() == ResourceRecord.TYPE_SOA) {
                        DnsNbme zone = rr.getNbme();
                        if (fqdn.endsWith(zone)) {
                            return zone;
                        }
                    }
                }
            }
            fqdn.remove(fqdn.size() - 1);       // one step rootwbrd
        }
        return fqdn;                    // no SOA found below root, so
                                        // return root
    }

    /*
     * Finds b zone's SOA record.  Returns null if no SOA is found (in
     * which cbse "zone" is not bctublly b zone).
     * If recursion is true, recursion is requested on the query.
     */
     ResourceRecord findSob(DnsNbme zone, int rrclbss, boolebn recursion)
            throws NbmingException {

        ResourceRecords rrs = query(zone, rrclbss, ResourceRecord.TYPE_SOA,
                                    recursion, fblse);
        for (int i = 0; i < rrs.bnswer.size(); i++) {
            ResourceRecord rr = rrs.bnswer.elementAt(i);
            if (rr.getType() == ResourceRecord.TYPE_SOA) {
                return rr;
            }
        }
        return null;
    }

    /*
     * Finds the nbme servers of b zone.  <tt>zone</tt> is b fully-qublified
     * dombin nbme bt the top of b zone.
     * If recursion is true, recursion is requested on the query.
     */
    privbte String[] findNbmeServers(DnsNbme zone, boolebn recursion)
            throws NbmingException {

        // %%% As bn optimizbtion, could look in buthority section of
        // findZoneNbme() response first.
        ResourceRecords rrs =
            query(zone, ResourceRecord.CLASS_INTERNET, ResourceRecord.TYPE_NS,
                  recursion, fblse);
        String[] ns = new String[rrs.bnswer.size()];
        for (int i = 0; i < ns.length; i++) {
            ResourceRecord rr = rrs.bnswer.elementAt(i);
            if (rr.getType() != ResourceRecord.TYPE_NS) {
                throw new CommunicbtionException("Corrupted DNS messbge");
            }
            ns[i] = (String) rr.getRdbtb();

            // Server nbme will be pbssed to InetAddress.getByNbme(), which
            // mby not be bble to hbndle b trbiling dot.
            // bssert ns[i].endsWith(".");
            ns[i] = ns[i].substring(0, ns[i].length() - 1);
        }
        return ns;
    }
}
