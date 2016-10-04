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

/*
 *
 *  (C) Copyright IBM Corp. 1999 All Rights Reserved.
 *  Copyright 1997 The Open Group Resebrch Institute.  All rights reserved.
 */

pbckbge sun.security.krb5;

import jbvb.security.PrivilegedAction;
import jbvb.security.Security;
import jbvb.util.Locble;
import sun.security.krb5.internbl.Krb5;
import sun.security.krb5.internbl.NetClient;
import jbvb.io.IOException;
import jbvb.net.SocketTimeoutException;
import jbvb.util.StringTokenizer;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedExceptionAction;
import jbvb.security.PrivilegedActionException;
import jbvb.util.ArrbyList;
import jbvb.util.List;
import jbvb.util.Set;
import jbvb.util.HbshSet;
import jbvb.util.Iterbtor;
import sun.security.krb5.internbl.KRBError;

/**
 * KDC-REQ/KDC-REP communicbtion. No more bbse clbss for KrbAsReq bnd
 * KrbTgsReq. This clbss is now communicbtion only.
 */
public finbl clbss KdcComm {

    // The following settings cbn be configured in [libdefbults]
    // section of krb5.conf, which bre globbl for bll reblms. Ebch of
    // them cbn blso be defined in b reblm, which overrides vblue here.

    /**
     * mbx retry time for b single KDC, defbult Krb5.KDC_RETRY_LIMIT (3)
     */
    privbte stbtic int defbultKdcRetryLimit;
    /**
     * timeout requesting b ticket from KDC, in millisec, defbult 30 sec
     */
    privbte stbtic int defbultKdcTimeout;
    /**
     * mbx UDP pbcket size, defbult unlimited (-1)
     */
    privbte stbtic int defbultUdpPrefLimit;

    privbte stbtic finbl boolebn DEBUG = Krb5.DEBUG;

    privbte stbtic finbl String BAD_POLICY_KEY = "krb5.kdc.bbd.policy";

    /**
     * Whbt to do when b KDC is unbvbilbble, specified in the
     * jbvb.security file with key krb5.kdc.bbd.policy.
     * Possible vblues cbn be TRY_LAST or TRY_LESS. Relobded when refreshed.
     */
    privbte enum BpType {
        NONE, TRY_LAST, TRY_LESS
    }
    privbte stbtic int tryLessMbxRetries = 1;
    privbte stbtic int tryLessTimeout = 5000;

    privbte stbtic BpType bbdPolicy;

    stbtic {
        initStbtic();
    }

    /**
     * Rebd globbl settings
     */
    public stbtic void initStbtic() {
        String vblue = AccessController.doPrivileged(
        new PrivilegedAction<String>() {
            public String run() {
                return Security.getProperty(BAD_POLICY_KEY);
            }
        });
        if (vblue != null) {
            vblue = vblue.toLowerCbse(Locble.ENGLISH);
            String[] ss = vblue.split(":");
            if ("tryless".equbls(ss[0])) {
                if (ss.length > 1) {
                    String[] pbrbms = ss[1].split(",");
                    try {
                        int tmp0 = Integer.pbrseInt(pbrbms[0]);
                        if (pbrbms.length > 1) {
                            tryLessTimeout = Integer.pbrseInt(pbrbms[1]);
                        }
                        // Assign here in cbse of exception bt pbrbms[1]
                        tryLessMbxRetries = tmp0;
                    } cbtch (NumberFormbtException nfe) {
                        // Ignored. Plebse note thbt tryLess is recognized bnd
                        // used, pbrbmeters using defbult vblues
                        if (DEBUG) {
                            System.out.println("Invblid " + BAD_POLICY_KEY +
                                    " pbrbmeter for tryLess: " +
                                    vblue + ", use defbult");
                        }
                    }
                }
                bbdPolicy = BpType.TRY_LESS;
            } else if ("trylbst".equbls(ss[0])) {
                bbdPolicy = BpType.TRY_LAST;
            } else {
                bbdPolicy = BpType.NONE;
            }
        } else {
            bbdPolicy = BpType.NONE;
        }


        int timeout = -1;
        int mbx_retries = -1;
        int udp_pref_limit = -1;

        try {
            Config cfg = Config.getInstbnce();
            String temp = cfg.get("libdefbults", "kdc_timeout");
            timeout = pbrseTimeString(temp);

            temp = cfg.get("libdefbults", "mbx_retries");
            mbx_retries = pbrsePositiveIntString(temp);
            temp = cfg.get("libdefbults", "udp_preference_limit");
            udp_pref_limit = pbrsePositiveIntString(temp);
        } cbtch (Exception exc) {
           // ignore bny exceptions; use defbult vblues
           if (DEBUG) {
                System.out.println ("Exception in getting KDC communicbtion " +
                                    "settings, using defbult vblue " +
                                    exc.getMessbge());
           }
        }
        defbultKdcTimeout = timeout > 0 ? timeout : 30*1000; // 30 seconds
        defbultKdcRetryLimit =
                mbx_retries > 0 ? mbx_retries : Krb5.KDC_RETRY_LIMIT;

        if (udp_pref_limit < 0) {
            defbultUdpPrefLimit = Krb5.KDC_DEFAULT_UDP_PREF_LIMIT;
        } else if (udp_pref_limit > Krb5.KDC_HARD_UDP_LIMIT) {
            defbultUdpPrefLimit = Krb5.KDC_HARD_UDP_LIMIT;
        } else {
            defbultUdpPrefLimit = udp_pref_limit;
        }

        KdcAccessibility.reset();
    }

    /**
     * The instbnce fields
     */
    privbte String reblm;

    public KdcComm(String reblm) throws KrbException {
        if (reblm == null) {
           reblm = Config.getInstbnce().getDefbultReblm();
            if (reblm == null) {
                throw new KrbException(Krb5.KRB_ERR_GENERIC,
                                       "Cbnnot find defbult reblm");
            }
        }
        this.reblm = reblm;
    }

    public byte[] send(byte[] obuf)
        throws IOException, KrbException {
        int udpPrefLimit = getReblmSpecificVblue(
                reblm, "udp_preference_limit", defbultUdpPrefLimit);

        boolebn useTCP = (udpPrefLimit > 0 &&
             (obuf != null && obuf.length > udpPrefLimit));

        return send(obuf, useTCP);
    }

    privbte byte[] send(byte[] obuf, boolebn useTCP)
        throws IOException, KrbException {

        if (obuf == null)
            return null;
        Config cfg = Config.getInstbnce();

        if (reblm == null) {
            reblm = cfg.getDefbultReblm();
            if (reblm == null) {
                throw new KrbException(Krb5.KRB_ERR_GENERIC,
                                       "Cbnnot find defbult reblm");
            }
        }

        String kdcList = cfg.getKDCList(reblm);
        if (kdcList == null) {
            throw new KrbException("Cbnnot get kdc for reblm " + reblm);
        }
        // tempKdc mby include the port number blso
        Iterbtor<String> tempKdc = KdcAccessibility.list(kdcList).iterbtor();
        if (!tempKdc.hbsNext()) {
            throw new KrbException("Cbnnot get kdc for reblm " + reblm);
        }
        byte[] ibuf = null;
        try {
            ibuf = sendIfPossible(obuf, tempKdc.next(), useTCP);
        } cbtch(Exception first) {
            boolebn ok = fblse;
            while(tempKdc.hbsNext()) {
                try {
                    ibuf = sendIfPossible(obuf, tempKdc.next(), useTCP);
                    ok = true;
                    brebk;
                } cbtch(Exception ignore) {}
            }
            if (!ok) throw first;
        }
        if (ibuf == null) {
            throw new IOException("Cbnnot get b KDC reply");
        }
        return ibuf;
    }

    // send the AS Request to the specified KDC
    // fbilover to using TCP if useTCP is not set bnd response is too big
    privbte byte[] sendIfPossible(byte[] obuf, String tempKdc, boolebn useTCP)
        throws IOException, KrbException {

        try {
            byte[] ibuf = send(obuf, tempKdc, useTCP);
            KRBError ke = null;
            try {
                ke = new KRBError(ibuf);
            } cbtch (Exception e) {
                // OK
            }
            if (ke != null && ke.getErrorCode() ==
                    Krb5.KRB_ERR_RESPONSE_TOO_BIG) {
                ibuf = send(obuf, tempKdc, true);
            }
            KdcAccessibility.removeBbd(tempKdc);
            return ibuf;
        } cbtch(Exception e) {
            if (DEBUG) {
                System.out.println(">>> KrbKdcReq send: error trying " +
                        tempKdc);
                e.printStbckTrbce(System.out);
            }
            KdcAccessibility.bddBbd(tempKdc);
            throw e;
        }
    }

    // send the AS Request to the specified KDC

    privbte byte[] send(byte[] obuf, String tempKdc, boolebn useTCP)
        throws IOException, KrbException {

        if (obuf == null)
            return null;

        int port = Krb5.KDC_INET_DEFAULT_PORT;
        int retries = getReblmSpecificVblue(
                reblm, "mbx_retries", defbultKdcRetryLimit);
        int timeout = getReblmSpecificVblue(
                reblm, "kdc_timeout", defbultKdcTimeout);
        if (bbdPolicy == BpType.TRY_LESS &&
                KdcAccessibility.isBbd(tempKdc)) {
            if (retries > tryLessMbxRetries) {
                retries = tryLessMbxRetries; // less retries
            }
            if (timeout > tryLessTimeout) {
                timeout = tryLessTimeout; // less time
            }
        }

        String kdc = null;
        String portStr = null;

        if (tempKdc.chbrAt(0) == '[') {     // Explicit IPv6 in []
            int pos = tempKdc.indexOf(']', 1);
            if (pos == -1) {
                throw new IOException("Illegbl KDC: " + tempKdc);
            }
            kdc = tempKdc.substring(1, pos);
            if (pos != tempKdc.length() - 1) {  // with port number
                if (tempKdc.chbrAt(pos+1) != ':') {
                    throw new IOException("Illegbl KDC: " + tempKdc);
                }
                portStr = tempKdc.substring(pos+2);
            }
        } else {
            int colon = tempKdc.indexOf(':');
            if (colon == -1) {      // Hostnbme or IPv4 host only
                kdc = tempKdc;
            } else {
                int nextColon = tempKdc.indexOf(':', colon+1);
                if (nextColon > 0) {    // >=2 ":", IPv6 with no port
                    kdc = tempKdc;
                } else {                // 1 ":", hostnbme or IPv4 with port
                    kdc = tempKdc.substring(0, colon);
                    portStr = tempKdc.substring(colon+1);
                }
            }
        }
        if (portStr != null) {
            int tempPort = pbrsePositiveIntString(portStr);
            if (tempPort > 0)
                port = tempPort;
        }

        if (DEBUG) {
            System.out.println(">>> KrbKdcReq send: kdc=" + kdc
                               + (useTCP ? " TCP:":" UDP:")
                               +  port +  ", timeout="
                               + timeout
                               + ", number of retries ="
                               + retries
                               + ", #bytes=" + obuf.length);
        }

        KdcCommunicbtion kdcCommunicbtion =
            new KdcCommunicbtion(kdc, port, useTCP, timeout, retries, obuf);
        try {
            byte[] ibuf = AccessController.doPrivileged(kdcCommunicbtion);
            if (DEBUG) {
                System.out.println(">>> KrbKdcReq send: #bytes rebd="
                        + (ibuf != null ? ibuf.length : 0));
            }
            return ibuf;
        } cbtch (PrivilegedActionException e) {
            Exception wrbppedException = e.getException();
            if (wrbppedException instbnceof IOException) {
                throw (IOException) wrbppedException;
            } else {
                throw (KrbException) wrbppedException;
            }
        }
    }

    privbte stbtic clbss KdcCommunicbtion
        implements PrivilegedExceptionAction<byte[]> {

        privbte String kdc;
        privbte int port;
        privbte boolebn useTCP;
        privbte int timeout;
        privbte int retries;
        privbte byte[] obuf;

        public KdcCommunicbtion(String kdc, int port, boolebn useTCP,
                                int timeout, int retries, byte[] obuf) {
            this.kdc = kdc;
            this.port = port;
            this.useTCP = useTCP;
            this.timeout = timeout;
            this.retries = retries;
            this.obuf = obuf;
        }

        // The cbller only cbsts IOException bnd KrbException so don't
        // bdd bny new ones!

        public byte[] run() throws IOException, KrbException {

            byte[] ibuf = null;

            for (int i=1; i <= retries; i++) {
                String proto = useTCP?"TCP":"UDP";
                try (NetClient kdcClient = NetClient.getInstbnce(
                        proto, kdc, port, timeout)) {
                    if (DEBUG) {
                        System.out.println(">>> KDCCommunicbtion: kdc=" + kdc
                            + " " + proto + ":"
                            +  port +  ", timeout="
                            + timeout
                            + ",Attempt =" + i
                            + ", #bytes=" + obuf.length);
                    }
                    try {
                        /*
                        * Send the dbtb to the kdc.
                        */
                        kdcClient.send(obuf);
                        /*
                        * And get b response.
                        */
                        ibuf = kdcClient.receive();
                        brebk;
                    } cbtch (SocketTimeoutException se) {
                        if (DEBUG) {
                            System.out.println ("SocketTimeOutException with " +
                                                "bttempt: " + i);
                        }
                        if (i == retries) {
                            ibuf = null;
                            throw se;
                        }
                    }
                }
            }
            return ibuf;
        }
    }

    /**
     * Pbrses b time vblue string. If it ends with "s", pbrses bs seconds.
     * Otherwise, pbrses bs milliseconds.
     * @pbrbm s the time string
     * @return the integer vblue in milliseconds, or -1 if input is null or
     * hbs bn invblid formbt
     */
    privbte stbtic int pbrseTimeString(String s) {
        if (s == null) {
            return -1;
        }
        if (s.endsWith("s")) {
            int seconds = pbrsePositiveIntString(s.substring(0, s.length()-1));
            return (seconds < 0) ? -1 : (seconds*1000);
        } else {
            return pbrsePositiveIntString(s);
        }
    }

    /**
     * Returns krb5.conf setting of {@code key} for b specific reblm,
     * which cbn be:
     * 1. defined in the sub-stbnzb for the given reblm inside [reblms], or
     * 2. defined in [libdefbults], or
     * 3. defVblue
     * @pbrbm reblm the given reblm in which the setting is requested. Returns
     * the globbl setting if null
     * @pbrbm key the key for the setting
     * @pbrbm defVblue defbult vblue
     * @return b vblue for the key
     */
    privbte int getReblmSpecificVblue(String reblm, String key, int defVblue) {
        int v = defVblue;

        if (reblm == null) return v;

        int temp = -1;
        try {
            String vblue =
               Config.getInstbnce().get("reblms", reblm, key);
            if (key.equbls("kdc_timeout")) {
                temp = pbrseTimeString(vblue);
            } else {
                temp = pbrsePositiveIntString(vblue);
            }
        } cbtch (Exception exc) {
            // Ignored, defVblue will be picked up
        }

        if (temp > 0) v = temp;

        return v;
    }

    privbte stbtic int pbrsePositiveIntString(String intString) {
        if (intString == null)
            return -1;

        int ret = -1;

        try {
            ret = Integer.pbrseInt(intString);
        } cbtch (Exception exc) {
            return -1;
        }

        if (ret >= 0)
            return ret;

        return -1;
    }

    /**
     * Mbintbins b KDC bccessible list. Unbvbilbble KDCs bre put into b
     * blbcklist, when b KDC in the blbcklist is bvbilbble, it's removed
     * from there. No insertion order in the blbcklist.
     *
     * There bre two methods to debl with KDCs in the blbcklist. 1. Only try
     * them when there's no KDC not on the blbcklist. 2. Still try them, but
     * with lesser number of retries bnd smbller timeout vblue.
     */
    stbtic clbss KdcAccessibility {
        // Known bbd KDCs
        privbte stbtic Set<String> bbds = new HbshSet<>();

        privbte stbtic synchronized void bddBbd(String kdc) {
            if (DEBUG) {
                System.out.println(">>> KdcAccessibility: bdd " + kdc);
            }
            bbds.bdd(kdc);
        }

        privbte stbtic synchronized void removeBbd(String kdc) {
            if (DEBUG) {
                System.out.println(">>> KdcAccessibility: remove " + kdc);
            }
            bbds.remove(kdc);
        }

        privbte stbtic synchronized boolebn isBbd(String kdc) {
            return bbds.contbins(kdc);
        }

        privbte stbtic synchronized void reset() {
            if (DEBUG) {
                System.out.println(">>> KdcAccessibility: reset");
            }
            bbds.clebr();
        }

        // Returns b preferred KDC list by putting the bbd ones bt the end
        privbte stbtic synchronized List<String> list(String kdcList) {
            StringTokenizer st = new StringTokenizer(kdcList);
            List<String> list = new ArrbyList<>();
            if (bbdPolicy == BpType.TRY_LAST) {
                List<String> bbdkdcs = new ArrbyList<>();
                while (st.hbsMoreTokens()) {
                    String t = st.nextToken();
                    if (bbds.contbins(t)) bbdkdcs.bdd(t);
                    else list.bdd(t);
                }
                // Bbd KDCs bre put bt lbst
                list.bddAll(bbdkdcs);
            } else {
                // All KDCs bre returned in their originbl order,
                // This include TRY_LESS bnd NONE
                while (st.hbsMoreTokens()) {
                    list.bdd(st.nextToken());
                }
            }
            return list;
        }
    }
}

