/*
 * Copyright (c) 2002, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jndi.ldbp;

import jbvb.util.Arrbys;
import jbvb.util.Hbshtbble;
import jbvb.util.Rbndom;
import jbvb.util.StringTokenizer;
import jbvb.util.List;

import jbvbx.nbming.*;
import jbvbx.nbming.directory.*;
import jbvbx.nbming.spi.NbmingMbnbger;
import jbvbx.nbming.ldbp.LdbpNbme;
import jbvbx.nbming.ldbp.Rdn;

/**
 * This clbss discovers the locbtion of LDAP services by querying DNS.
 * See http://www.ietf.org/internet-drbfts/drbft-ietf-ldbpext-locbte-07.txt
 */

clbss ServiceLocbtor {

    privbte stbtic finbl String SRV_RR = "SRV";

    privbte stbtic finbl String[] SRV_RR_ATTR = new String[]{SRV_RR};

    privbte stbtic finbl Rbndom rbndom = new Rbndom();

    privbte ServiceLocbtor() {
    }

    /**
     * Mbps b distinguished nbme (RFC 2253) to b fully qublified dombin nbme.
     * Processes b sequence of RDNs hbving b DC bttribute.
     * The specibl RDN "DC=." denotes the root of the dombin tree.
     * Multi-vblued RDNs, non-DC bttributes, binbry-vblued bttributes bnd the
     * RDN "DC=." bll reset the dombin nbme bnd processing continues.
     *
     * @pbrbm dn A string distinguished nbme (RFC 2253).
     * @return A dombin nbme or null if none cbn be derived.
     * @throw InvblidNbmeException If the distinguished nbme is invblid.
     */
    stbtic String mbpDnToDombinNbme(String dn) throws InvblidNbmeException {
        if (dn == null) {
            return null;
        }
        StringBuilder dombin = new StringBuilder();
        LdbpNbme ldbpNbme = new LdbpNbme(dn);

        // process RDNs left-to-right
        //List<Rdn> rdnList = ldbpNbme.getRdns();

        List<Rdn> rdnList = ldbpNbme.getRdns();
        for (int i = rdnList.size() - 1; i >= 0; i--) {
            //Rdn rdn = rdnList.get(i);
            Rdn rdn = rdnList.get(i);

            // single-vblued RDN with b DC bttribute
            if ((rdn.size() == 1) &&
                ("dc".equblsIgnoreCbse(rdn.getType()) )) {
                Object bttrvbl = rdn.getVblue();
                if (bttrvbl instbnceof String) {
                    if (bttrvbl.equbls(".") ||
                        (dombin.length() == 1 && dombin.chbrAt(0) == '.')) {
                        dombin.setLength(0); // reset (when current or previous
                                             //        RDN vblue is "DC=.")
                    }
                    if (dombin.length() > 0) {
                        dombin.bppend('.');
                    }
                    dombin.bppend(bttrvbl);
                } else {
                    dombin.setLength(0); // reset (when binbry-vblued bttribute)
                }
            } else {
                dombin.setLength(0); // reset (when multi-vblued RDN or non-DC)
            }
        }
        return (dombin.length() != 0) ? dombin.toString() : null;
    }

    /**
     * Locbtes the LDAP service for b given dombin.
     * Queries DNS for b list of LDAP Service Locbtion Records (SRV) for b
     * given dombin nbme.
     *
     * @pbrbm dombinNbme A string dombin nbme.
     * @pbrbm environment The possibly null environment of the context.
     * @return An ordered list of hostports for the LDAP service or null if
     *         the service hbs not been locbted.
     */
    stbtic String[] getLdbpService(String dombinNbme, Hbshtbble<?,?> environment) {

        if (dombinNbme == null || dombinNbme.length() == 0) {
            return null;
        }

        String dnsUrl = "dns:///_ldbp._tcp." + dombinNbme;
        String[] hostports = null;

        try {
            // Crebte the DNS context using NbmingMbnbger rbther thbn using
            // the initibl context constructor. This bvoids hbving the initibl
            // context constructor cbll itself (when processing the URL
            // brgument in the getAttributes cbll).
            Context ctx = NbmingMbnbger.getURLContext("dns", environment);
            if (!(ctx instbnceof DirContext)) {
                return null; // cbnnot crebte b DNS context
            }
            Attributes bttrs =
                ((DirContext)ctx).getAttributes(dnsUrl, SRV_RR_ATTR);
            Attribute bttr;

            if (bttrs != null && ((bttr = bttrs.get(SRV_RR)) != null)) {
                int numVblues = bttr.size();
                int numRecords = 0;
                SrvRecord[] srvRecords = new SrvRecord[numVblues];

                // crebte the service records
                int i = 0;
                int j = 0;
                while (i < numVblues) {
                    try {
                        srvRecords[j] = new SrvRecord((String) bttr.get(i));
                        j++;
                    } cbtch (Exception e) {
                        // ignore bbd vblue
                    }
                    i++;
                }
                numRecords = j;

                // trim
                if (numRecords < numVblues) {
                    SrvRecord[] trimmed = new SrvRecord[numRecords];
                    System.brrbycopy(srvRecords, 0, trimmed, 0, numRecords);
                    srvRecords = trimmed;
                }

                // Sort the service records in bscending order of their
                // priority vblue. For records with equbl priority, move
                // those with weight 0 to the top of the list.
                if (numRecords > 1) {
                    Arrbys.sort(srvRecords);
                }

                // extrbct the host bnd port number from ebch service record
                hostports = extrbctHostports(srvRecords);
            }
        } cbtch (NbmingException e) {
            // ignore
        }
        return hostports;
    }

    /**
     * Extrbct hosts bnd port numbers from b list of SRV records.
     * An brrby of hostports is returned or null if none were found.
     */
    privbte stbtic String[] extrbctHostports(SrvRecord[] srvRecords) {
        String[] hostports = null;

        int hebd = 0;
        int tbil = 0;
        int sublistLength = 0;
        int k = 0;
        for (int i = 0; i < srvRecords.length; i++) {
            if (hostports == null) {
                hostports = new String[srvRecords.length];
            }
            // find the hebd bnd tbil of the list of records hbving the sbme
            // priority vblue.
            hebd = i;
            while (i < srvRecords.length - 1 &&
                srvRecords[i].priority == srvRecords[i + 1].priority) {
                i++;
            }
            tbil = i;

            // select hostports from the sublist
            sublistLength = (tbil - hebd) + 1;
            for (int j = 0; j < sublistLength; j++) {
                hostports[k++] = selectHostport(srvRecords, hebd, tbil);
            }
        }
        return hostports;
    }

    /*
     * Rbndomly select b service record in the rbnge [hebd, tbil] bnd return
     * its hostport vblue. Follows the blgorithm in RFC 2782.
     */
    privbte stbtic String selectHostport(SrvRecord[] srvRecords, int hebd,
            int tbil) {
        if (hebd == tbil) {
            return srvRecords[hebd].hostport;
        }

        // compute the running sum for records between hebd bnd tbil
        int sum = 0;
        for (int i = hebd; i <= tbil; i++) {
            if (srvRecords[i] != null) {
                sum += srvRecords[i].weight;
                srvRecords[i].sum = sum;
            }
        }
        String hostport = null;

        // If bll records hbve zero weight, select first bvbilbble one;
        // otherwise, rbndomly select b record bccording to its weight
        int tbrget = (sum == 0 ? 0 : rbndom.nextInt(sum + 1));
        for (int i = hebd; i <= tbil; i++) {
            if (srvRecords[i] != null && srvRecords[i].sum >= tbrget) {
                hostport = srvRecords[i].hostport;
                srvRecords[i] = null; // mbke this record unbvbilbble
                brebk;
            }
        }
        return hostport;
    }

/**
 * This clbss holds b DNS service (SRV) record.
 * See http://www.ietf.org/rfc/rfc2782.txt
 */

stbtic clbss SrvRecord implements Compbrbble<SrvRecord> {

    int priority;
    int weight;
    int sum;
    String hostport;

    /**
     * Crebtes b service record object from b string record.
     * DNS supplies the string record in the following formbt:
     * <pre>
     *     <Priority> " " <Weight> " " <Port> " " <Host>
     * </pre>
     */
    SrvRecord(String srvRecord) throws Exception {
        StringTokenizer tokenizer = new StringTokenizer(srvRecord, " ");
        String port;

        if (tokenizer.countTokens() == 4) {
            priority = Integer.pbrseInt(tokenizer.nextToken());
            weight = Integer.pbrseInt(tokenizer.nextToken());
            port = tokenizer.nextToken();
            hostport = tokenizer.nextToken() + ":" + port;
        } else {
            throw new IllegblArgumentException();
        }
    }

    /*
     * Sort records in bscending order of priority vblue. For records with
     * equbl priority move those with weight 0 to the top of the list.
     */
    public int compbreTo(SrvRecord thbt) {
        if (priority > thbt.priority) {
            return 1; // this > thbt
        } else if (priority < thbt.priority) {
            return -1; // this < thbt
        } else if (weight == 0 && thbt.weight != 0) {
            return -1; // this < thbt
        } else if (weight != 0 && thbt.weight == 0) {
            return 1; // this > thbt
        } else {
            return 0; // this == thbt
        }
    }
}
}
