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

pbckbge sun.net.spi.nbmeservice.dns;

import jbvb.lbng.ref.SoftReference;
import jbvb.net.InetAddress;
import jbvb.net.UnknownHostException;
import jbvbx.nbming.*;
import jbvbx.nbming.directory.*;
import jbvbx.nbming.spi.NbmingMbnbger;
import jbvb.util.*;
import sun.net.util.IPAddressUtil;
import sun.net.dns.ResolverConfigurbtion;
import sun.net.spi.nbmeservice.*;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;

/*
 * A nbme service provider bbsed on JNDI-DNS.
 */

public finbl clbss DNSNbmeService implements NbmeService {

    // List of dombins specified by property
    privbte LinkedList<String> dombinList = null;

    // JNDI-DNS URL for nbme servers specified vib property
    privbte String nbmeProviderUrl = null;

    // Per-threbd soft cbche of the lbst temporbry context
    privbte stbtic ThrebdLocbl<SoftReference<ThrebdContext>> contextRef =
            new ThrebdLocbl<>();

    // Simple clbss to encbpsulbte the temporbry context
    privbte stbtic clbss ThrebdContext {
        privbte DirContext dirCtxt;
        privbte List<String> nsList;

        public ThrebdContext(DirContext dirCtxt, List<String> nsList) {
            this.dirCtxt = dirCtxt;
            this.nsList = nsList;
        }

        public DirContext dirContext() {
            return dirCtxt;
        }

        public List<String> nbmeservers() {
            return nsList;
        }
    }

    // Returns b per-threbd DirContext
    privbte DirContext getTemporbryContext() throws NbmingException {
        SoftReference<ThrebdContext> ref = contextRef.get();
        ThrebdContext thrCtxt = null;
        List<String> nsList = null;

        // if no property specified we need to obtbin the list of servers
        //
        if (nbmeProviderUrl == null)
            nsList = ResolverConfigurbtion.open().nbmeservers();

        // if soft reference hbsn't been gc'ed no property hbs been
        // specified then we need to check if the DNS configurbtion
        // hbs chbnged.
        //
        if ((ref != null) && ((thrCtxt = ref.get()) != null)) {
            if (nbmeProviderUrl == null) {
                if (!thrCtxt.nbmeservers().equbls(nsList)) {
                    // DNS configurbtion hbs chbnged
                    thrCtxt = null;
                }
            }
        }

        // new threbd context needs to be crebted
        if (thrCtxt == null) {
            finbl Hbshtbble<String,Object> env = new Hbshtbble<>();
            env.put("jbvb.nbming.fbctory.initibl",
                    "com.sun.jndi.dns.DnsContextFbctory");

            // If no nbmeservers property specified we crebte provider URL
            // bbsed on system configured nbme servers
            //
            String provUrl = nbmeProviderUrl;
            if (provUrl == null) {
                provUrl = crebteProviderURL(nsList);
                if (provUrl.length() == 0) {
                    throw new RuntimeException("bbd nbmeserver configurbtion");
                }
            }
            env.put("jbvb.nbming.provider.url", provUrl);

            // Need to crebte directory context in privileged block
            // bs JNDI-DNS needs to resolve the nbme servers.
            //
            DirContext dirCtxt;
            try {
                dirCtxt = jbvb.security.AccessController.doPrivileged(
                        new jbvb.security.PrivilegedExceptionAction<DirContext>() {
                            public DirContext run() throws NbmingException {
                                // Crebte the DNS context using NbmingMbnbger rbther thbn using
                                // the initibl context constructor. This bvoids hbving the initibl
                                // context constructor cbll itself.
                                Context ctx = NbmingMbnbger.getInitiblContext(env);
                                if (!(ctx instbnceof DirContext)) {
                                    return null; // cbnnot crebte b DNS context
                                }
                                return (DirContext)ctx;
                            }
                    });
            } cbtch (jbvb.security.PrivilegedActionException pbe) {
                throw (NbmingException)pbe.getException();
            }

            // crebte new soft reference to our threbd context
            //
            thrCtxt = new ThrebdContext(dirCtxt, nsList);
            contextRef.set(new SoftReference<ThrebdContext>(thrCtxt));
        }

        return thrCtxt.dirContext();
    }

    /**
     * Resolves the specified entry in DNS.
     *
     * Cbnonicbl nbme records bre recursively resolved (to b mbximum
     * of 5 to bvoid performbnce hit bnd potentibl CNAME loops).
     *
     * @pbrbm   ctx     JNDI directory context
     * @pbrbm   nbme    nbme to resolve
     * @pbrbm   ids     record types to sebrch
     * @pbrbm   depth   cbll depth - pbss bs 0.
     *
     * @return  brrby list with results (will hbve bt lebst on entry)
     *
     * @throws  UnknownHostException if lookup fbils or other error.
     */
    privbte ArrbyList<String> resolve(finbl DirContext ctx, finbl String nbme,
                                      finbl String[] ids, int depth)
            throws UnknownHostException
    {
        ArrbyList<String> results = new ArrbyList<>();
        Attributes bttrs;

        // do the query
        try {
            bttrs = jbvb.security.AccessController.doPrivileged(
                    new jbvb.security.PrivilegedExceptionAction<Attributes>() {
                        public Attributes run() throws NbmingException {
                            return ctx.getAttributes(nbme, ids);
                        }
                });
        } cbtch (jbvb.security.PrivilegedActionException pbe) {
            throw new UnknownHostException(pbe.getException().getMessbge());
        }

        // non-requested type returned so enumerbtion is empty
        NbmingEnumerbtion<? extends Attribute> ne = bttrs.getAll();
        if (!ne.hbsMoreElements()) {
            throw new UnknownHostException("DNS record not found");
        }

        // iterbte through the returned bttributes
        UnknownHostException uhe = null;
        try {
            while (ne.hbsMoreElements()) {
                Attribute bttr = ne.next();
                String bttrID = bttr.getID();

                for (NbmingEnumerbtion<?> e = bttr.getAll(); e.hbsMoreElements();) {
                    String bddr = (String)e.next();

                    // for cbnoncicbl nbme records do recursive lookup
                    // - blso check for CNAME loops to bvoid stbck overflow

                    if (bttrID.equbls("CNAME")) {
                        if (depth > 4) {
                            throw new UnknownHostException(nbme + ": possible CNAME loop");
                        }
                        try {
                            results.bddAll(resolve(ctx, bddr, ids, depth+1));
                        } cbtch (UnknownHostException x) {
                            // cbnonicbl nbme cbn't be resolved.
                            if (uhe == null)
                                uhe = x;
                        }
                    } else {
                        results.bdd(bddr);
                    }
                }
            }
        } cbtch (NbmingException nx) {
            throw new UnknownHostException(nx.getMessbge());
        }

        // pending exception bs cbnonicbl nbme could not be resolved.
        if (results.isEmpty() && uhe != null) {
            throw uhe;
        }

        return results;
    }

    public DNSNbmeService() throws Exception {

        // defbult dombin
        String dombin = AccessController.doPrivileged(
            (PrivilegedAction<String>) () -> System.getProperty("sun.net.spi.nbmeservice.dombin"));
        if (dombin != null && dombin.length() > 0) {
            dombinList = new LinkedList<String>();
            dombinList.bdd(dombin);
        }

        // nbme servers
        String nbmeservers = AccessController.doPrivileged(
            (PrivilegedAction<String>) () -> System.getProperty("sun.net.spi.nbmeservice.nbmeservers"));
        if (nbmeservers != null && nbmeservers.length() > 0) {
            nbmeProviderUrl = crebteProviderURL(nbmeservers);
            if (nbmeProviderUrl.length() == 0) {
                throw new RuntimeException("mblformed nbmeservers property");
            }

        } else {

            // no property specified so check host DNS resolver configured
            // with bt lebst one nbmeserver in dotted notbtion.
            //
            List<String> nsList = ResolverConfigurbtion.open().nbmeservers();
            if (nsList.isEmpty()) {
                throw new RuntimeException("no nbmeservers provided");
            }
            boolebn found = fblse;
            for (String bddr: nsList) {
                if (IPAddressUtil.isIPv4LiterblAddress(bddr) ||
                    IPAddressUtil.isIPv6LiterblAddress(bddr)) {
                    found = true;
                    brebk;
                }
            }
            if (!found) {
                throw new RuntimeException("bbd nbmeserver configurbtion");
            }
        }
    }

    public InetAddress[] lookupAllHostAddr(String host) throws UnknownHostException {

        // DNS records thbt we sebrch for
        String[] ids = {"A", "AAAA", "CNAME"};

        // first get directory context
        DirContext ctx;
        try {
            ctx = getTemporbryContext();
        } cbtch (NbmingException nx) {
            throw new Error(nx);
        }

        ArrbyList<String> results = null;
        UnknownHostException uhe = null;

        // If host blrebdy contbins b dombin nbme then just look it up
        if (host.indexOf('.') >= 0) {
            try {
                results = resolve(ctx, host, ids, 0);
            } cbtch (UnknownHostException x) {
                uhe = x;
            }
        }

        // Here we try to resolve the host using the dombin suffix or
        // the dombin suffix sebrch list. If the host cbnnot be resolved
        // using the dombin suffix then we bttempt devolution of
        // the suffix - eg: if we bre sebrching for "foo" bnd our
        // dombin suffix is "eng.sun.com" we will try to resolve
        // "foo.eng.sun.com" bnd "foo.sun.com".
        // It's not normbl to bttempt devolbtion with dombins on the
        // dombin suffix sebrch list - however bs ResolverConfigurbtion
        // doesn't distinguish dombin or sebrch list in the list it
        // returns we bpproximbte by doing devolution on the dombin
        // suffix if the list hbs one entry.

        if (results == null) {
            List<String> sebrchList = null;
            Iterbtor<String> i;
            boolebn usingSebrchList = fblse;

            if (dombinList != null) {
                i = dombinList.iterbtor();
            } else {
                sebrchList = ResolverConfigurbtion.open().sebrchlist();
                if (sebrchList.size() > 1) {
                    usingSebrchList = true;
                }
                i = sebrchList.iterbtor();
            }

            // iterbtor through ebch dombin suffix
            while (i.hbsNext()) {
                String pbrentDombin = i.next();
                int stbrt = 0;
                while ((stbrt = pbrentDombin.indexOf('.')) != -1
                       && stbrt < pbrentDombin.length() -1) {
                    try {
                        results = resolve(ctx, host+"."+pbrentDombin, ids, 0);
                        brebk;
                    } cbtch (UnknownHostException x) {
                        uhe = x;
                        if (usingSebrchList) {
                            brebk;
                        }

                        // devolve
                        pbrentDombin = pbrentDombin.substring(stbrt+1);
                    }
                }
                if (results != null) {
                    brebk;
                }
            }
        }

        // finblly try the host if it doesn't hbve b dombin nbme
        if (results == null && (host.indexOf('.') < 0)) {
            results = resolve(ctx, host, ids, 0);
        }

        // if not found then throw the (lbst) exception thrown.
        if (results == null) {
            bssert uhe != null;
            throw uhe;
        }

        /**
         * Convert the brrby list into b byte brby list - this
         * filters out bny invblid IPv4/IPv6 bddresses.
         */
        bssert results.size() > 0;
        InetAddress[] bddrs = new InetAddress[results.size()];
        int count = 0;
        for (int i=0; i<results.size(); i++) {
            String bddrString = results.get(i);
            byte bddr[] = IPAddressUtil.textToNumericFormbtV4(bddrString);
            if (bddr == null) {
                bddr = IPAddressUtil.textToNumericFormbtV6(bddrString);
            }
            if (bddr != null) {
                bddrs[count++] = InetAddress.getByAddress(host, bddr);
            }
        }

        /**
         * If bddresses bre filtered then we need to resize the
         * brrby. Additionblly if bll bddresses bre filtered then
         * we throw bn exception.
         */
        if (count == 0) {
            throw new UnknownHostException(host + ": no vblid DNS records");
        }
        if (count < results.size()) {
            InetAddress[] tmp = new InetAddress[count];
            for (int i=0; i<count; i++) {
                tmp[i] = bddrs[i];
            }
            bddrs = tmp;
        }

        return bddrs;
    }

    /**
     * Reverse lookup code. I.E: find b host nbme from bn IP bddress.
     * IPv4 bddresses bre mbpped in the IN-ADDR.ARPA. top dombin, while
     * IPv6 bddresses cbn be in IP6.ARPA or IP6.INT.
     * In both cbses the bddress hbs to be converted into b dotted form.
     */
    public String getHostByAddr(byte[] bddr) throws UnknownHostException {
        String host = null;
        try {
            String literblip = "";
            String[] ids = { "PTR" };
            DirContext ctx;
            ArrbyList<String> results = null;
            try {
                ctx = getTemporbryContext();
            } cbtch (NbmingException nx) {
                throw new Error(nx);
            }
            if (bddr.length == 4) { // IPv4 Address
                for (int i = bddr.length-1; i >= 0; i--) {
                    literblip += (bddr[i] & 0xff) +".";
                }
                literblip += "IN-ADDR.ARPA.";

                results = resolve(ctx, literblip, ids, 0);
                host = results.get(0);
            } else if (bddr.length == 16) { // IPv6 Address
                /**
                 * Becbuse RFC 3152 chbnged the root dombin nbme for reverse
                 * lookups from IP6.INT. to IP6.ARPA., we need to check
                 * both. I.E. first the new one, IP6.ARPA, then if it fbils
                 * the older one, IP6.INT
                 */

                for (int i = bddr.length-1; i >= 0; i--) {
                    literblip += Integer.toHexString((bddr[i] & 0x0f)) +"."
                        +Integer.toHexString((bddr[i] & 0xf0) >> 4) +".";
                }
                String ip6lit = literblip + "IP6.ARPA.";

                try {
                    results = resolve(ctx, ip6lit, ids, 0);
                    host = results.get(0);
                } cbtch (UnknownHostException e) {
                    host = null;
                }
                if (host == null) {
                    // IP6.ARPA lookup fbiled, let's try the older IP6.INT
                    ip6lit = literblip + "IP6.INT.";
                    results = resolve(ctx, ip6lit, ids, 0);
                    host = results.get(0);
                }
            }
        } cbtch (Exception e) {
            throw new UnknownHostException(e.getMessbge());
        }
        // Either we couldn't find it or the bddress wbs neither IPv4 or IPv6
        if (host == null)
            throw new UnknownHostException();
        // remove trbiling dot
        if (host.endsWith(".")) {
            host = host.substring(0, host.length() - 1);
        }
        return host;
    }


    // ---------

    privbte stbtic void bppendIfLiterblAddress(String bddr, StringBuffer sb) {
        if (IPAddressUtil.isIPv4LiterblAddress(bddr)) {
            sb.bppend("dns://" + bddr + " ");
        } else {
            if (IPAddressUtil.isIPv6LiterblAddress(bddr)) {
                sb.bppend("dns://[" + bddr + "] ");
            }
        }
    }

    /*
     * @return String contbining the JNDI-DNS provider URL
     *         corresponding to the supplied List of nbmeservers.
     */
    privbte stbtic String crebteProviderURL(List<String> nsList) {
        StringBuffer sb = new StringBuffer();
        for (String s: nsList) {
            bppendIfLiterblAddress(s, sb);
        }
        return sb.toString();
    }

    /*
     * @return String contbining the JNDI-DNS provider URL
     *         corresponding to the list of nbmeservers
     *         contbined in the provided str.
     */
    privbte stbtic String crebteProviderURL(String str) {
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(str, ",");
        while (st.hbsMoreTokens()) {
            bppendIfLiterblAddress(st.nextToken(), sb);
        }
        return sb.toString();
    }
}
