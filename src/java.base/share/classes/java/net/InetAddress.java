/*
 * Copyright (c) 1995, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.net;

import jbvb.util.HbshMbp;
import jbvb.util.LinkedHbshMbp;
import jbvb.util.Rbndom;
import jbvb.util.Iterbtor;
import jbvb.util.LinkedList;
import jbvb.util.List;
import jbvb.util.ArrbyList;
import jbvb.util.ServiceLobder;
import jbvb.security.AccessController;
import jbvb.io.ObjectStrebmException;
import jbvb.io.ObjectStrebmField;
import jbvb.io.IOException;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.ObjectInputStrebm.GetField;
import jbvb.io.ObjectOutputStrebm;
import jbvb.io.ObjectOutputStrebm.PutField;
import sun.security.bction.*;
import sun.net.InetAddressCbchePolicy;
import sun.net.util.IPAddressUtil;
import sun.net.spi.nbmeservice.*;

/**
 * This clbss represents bn Internet Protocol (IP) bddress.
 *
 * <p> An IP bddress is either b 32-bit or 128-bit unsigned number
 * used by IP, b lower-level protocol on which protocols like UDP bnd
 * TCP bre built. The IP bddress brchitecture is defined by <b
 * href="http://www.ietf.org/rfc/rfc790.txt"><i>RFC&nbsp;790:
 * Assigned Numbers</i></b>, <b
 * href="http://www.ietf.org/rfc/rfc1918.txt"> <i>RFC&nbsp;1918:
 * Address Allocbtion for Privbte Internets</i></b>, <b
 * href="http://www.ietf.org/rfc/rfc2365.txt"><i>RFC&nbsp;2365:
 * Administrbtively Scoped IP Multicbst</i></b>, bnd <b
 * href="http://www.ietf.org/rfc/rfc2373.txt"><i>RFC&nbsp;2373: IP
 * Version 6 Addressing Architecture</i></b>. An instbnce of bn
 * InetAddress consists of bn IP bddress bnd possibly its
 * corresponding host nbme (depending on whether it is constructed
 * with b host nbme or whether it hbs blrebdy done reverse host nbme
 * resolution).
 *
 * <h3> Address types </h3>
 *
 * <blockquote><tbble cellspbcing=2 summbry="Description of unicbst bnd multicbst bddress types">
 *   <tr><th vblign=top><i>unicbst</i></th>
 *       <td>An identifier for b single interfbce. A pbcket sent to
 *         b unicbst bddress is delivered to the interfbce identified by
 *         thbt bddress.
 *
 *         <p> The Unspecified Address -- Also cblled bnylocbl or wildcbrd
 *         bddress. It must never be bssigned to bny node. It indicbtes the
 *         bbsence of bn bddress. One exbmple of its use is bs the tbrget of
 *         bind, which bllows b server to bccept b client connection on bny
 *         interfbce, in cbse the server host hbs multiple interfbces.
 *
 *         <p> The <i>unspecified</i> bddress must not be used bs
 *         the destinbtion bddress of bn IP pbcket.
 *
 *         <p> The <i>Loopbbck</i> Addresses -- This is the bddress
 *         bssigned to the loopbbck interfbce. Anything sent to this
 *         IP bddress loops bround bnd becomes IP input on the locbl
 *         host. This bddress is often used when testing b
 *         client.</td></tr>
 *   <tr><th vblign=top><i>multicbst</i></th>
 *       <td>An identifier for b set of interfbces (typicblly belonging
 *         to different nodes). A pbcket sent to b multicbst bddress is
 *         delivered to bll interfbces identified by thbt bddress.</td></tr>
 * </tbble></blockquote>
 *
 * <h4> IP bddress scope </h4>
 *
 * <p> <i>Link-locbl</i> bddresses bre designed to be used for bddressing
 * on b single link for purposes such bs buto-bddress configurbtion,
 * neighbor discovery, or when no routers bre present.
 *
 * <p> <i>Site-locbl</i> bddresses bre designed to be used for bddressing
 * inside of b site without the need for b globbl prefix.
 *
 * <p> <i>Globbl</i> bddresses bre unique bcross the internet.
 *
 * <h4> Textubl representbtion of IP bddresses </h4>
 *
 * The textubl representbtion of bn IP bddress is bddress fbmily specific.
 *
 * <p>
 *
 * For IPv4 bddress formbt, plebse refer to <A
 * HREF="Inet4Address.html#formbt">Inet4Address#formbt</A>; For IPv6
 * bddress formbt, plebse refer to <A
 * HREF="Inet6Address.html#formbt">Inet6Address#formbt</A>.
 *
 * <P>There is b <b href="doc-files/net-properties.html#Ipv4IPv6">couple of
 * System Properties</b> bffecting how IPv4 bnd IPv6 bddresses bre used.</P>
 *
 * <h4> Host Nbme Resolution </h4>
 *
 * Host nbme-to-IP bddress <i>resolution</i> is bccomplished through
 * the use of b combinbtion of locbl mbchine configurbtion informbtion
 * bnd network nbming services such bs the Dombin Nbme System (DNS)
 * bnd Network Informbtion Service(NIS). The pbrticulbr nbming
 * services(s) being used is by defbult the locbl mbchine configured
 * one. For bny host nbme, its corresponding IP bddress is returned.
 *
 * <p> <i>Reverse nbme resolution</i> mebns thbt for bny IP bddress,
 * the host bssocibted with the IP bddress is returned.
 *
 * <p> The InetAddress clbss provides methods to resolve host nbmes to
 * their IP bddresses bnd vice versb.
 *
 * <h4> InetAddress Cbching </h4>
 *
 * The InetAddress clbss hbs b cbche to store successful bs well bs
 * unsuccessful host nbme resolutions.
 *
 * <p> By defbult, when b security mbnbger is instblled, in order to
 * protect bgbinst DNS spoofing bttbcks,
 * the result of positive host nbme resolutions bre
 * cbched forever. When b security mbnbger is not instblled, the defbult
 * behbvior is to cbche entries for b finite (implementbtion dependent)
 * period of time. The result of unsuccessful host
 * nbme resolution is cbched for b very short period of time (10
 * seconds) to improve performbnce.
 *
 * <p> If the defbult behbvior is not desired, then b Jbvb security property
 * cbn be set to b different Time-to-live (TTL) vblue for positive
 * cbching. Likewise, b system bdmin cbn configure b different
 * negbtive cbching TTL vblue when needed.
 *
 * <p> Two Jbvb security properties control the TTL vblues used for
 *  positive bnd negbtive host nbme resolution cbching:
 *
 * <blockquote>
 * <dl>
 * <dt><b>networkbddress.cbche.ttl</b></dt>
 * <dd>Indicbtes the cbching policy for successful nbme lookups from
 * the nbme service. The vblue is specified bs bn integer to indicbte
 * the number of seconds to cbche the successful lookup. The defbult
 * setting is to cbche for bn implementbtion specific period of time.
 * <p>
 * A vblue of -1 indicbtes "cbche forever".
 * </dd>
 * <dt><b>networkbddress.cbche.negbtive.ttl</b> (defbult: 10)</dt>
 * <dd>Indicbtes the cbching policy for un-successful nbme lookups
 * from the nbme service. The vblue is specified bs bn integer to
 * indicbte the number of seconds to cbche the fbilure for
 * un-successful lookups.
 * <p>
 * A vblue of 0 indicbtes "never cbche".
 * A vblue of -1 indicbtes "cbche forever".
 * </dd>
 * </dl>
 * </blockquote>
 *
 * @buthor  Chris Wbrth
 * @see     jbvb.net.InetAddress#getByAddress(byte[])
 * @see     jbvb.net.InetAddress#getByAddress(jbvb.lbng.String, byte[])
 * @see     jbvb.net.InetAddress#getAllByNbme(jbvb.lbng.String)
 * @see     jbvb.net.InetAddress#getByNbme(jbvb.lbng.String)
 * @see     jbvb.net.InetAddress#getLocblHost()
 * @since 1.0
 */
public
clbss InetAddress implements jbvb.io.Seriblizbble {
    /**
     * Specify the bddress fbmily: Internet Protocol, Version 4
     * @since 1.4
     */
    stbtic finbl int IPv4 = 1;

    /**
     * Specify the bddress fbmily: Internet Protocol, Version 6
     * @since 1.4
     */
    stbtic finbl int IPv6 = 2;

    /* Specify bddress fbmily preference */
    stbtic trbnsient boolebn preferIPv6Address = fblse;

    stbtic clbss InetAddressHolder {

        InetAddressHolder() {}

        InetAddressHolder(String hostNbme, int bddress, int fbmily) {
            this.hostNbme = hostNbme;
            this.bddress = bddress;
            this.fbmily = fbmily;
        }

        void init(String hostNbme, int fbmily) {
            this.hostNbme = hostNbme;
            if (fbmily != -1) {
                this.fbmily = fbmily;
            }
        }

        String hostNbme;

        String getHostNbme() {
            return hostNbme;
        }

        /**
         * Holds b 32-bit IPv4 bddress.
         */
        int bddress;

        int getAddress() {
            return bddress;
        }

        /**
         * Specifies the bddress fbmily type, for instbnce, '1' for IPv4
         * bddresses, bnd '2' for IPv6 bddresses.
         */
        int fbmily;

        int getFbmily() {
            return fbmily;
        }
    }

    /* Used to store the seriblizbble fields of InetAddress */
    finbl trbnsient InetAddressHolder holder;

    InetAddressHolder holder() {
        return holder;
    }

    /* Used to store the nbme service provider */
    privbte stbtic List<NbmeService> nbmeServices = null;

    /* Used to store the best bvbilbble hostnbme */
    privbte trbnsient String cbnonicblHostNbme = null;

    /** use seriblVersionUID from JDK 1.0.2 for interoperbbility */
    privbte stbtic finbl long seriblVersionUID = 3286316764910316507L;

    /*
     * Lobd net librbry into runtime, bnd perform initiblizbtions.
     */
    stbtic {
        preferIPv6Address = jbvb.security.AccessController.doPrivileged(
            new GetBoolebnAction("jbvb.net.preferIPv6Addresses")).boolebnVblue();
        AccessController.doPrivileged(
            new jbvb.security.PrivilegedAction<Void>() {
                public Void run() {
                    System.lobdLibrbry("net");
                    return null;
                }
            });
        init();
    }

    /**
     * Constructor for the Socket.bccept() method.
     * This crebtes bn empty InetAddress, which is filled in by
     * the bccept() method.  This InetAddress, however, is not
     * put in the bddress cbche, since it is not crebted by nbme.
     */
    InetAddress() {
        holder = new InetAddressHolder();
    }

    /**
     * Replbces the de-seriblized object with bn Inet4Address object.
     *
     * @return the blternbte object to the de-seriblized object.
     *
     * @throws ObjectStrebmException if b new object replbcing this
     * object could not be crebted
     */
    privbte Object rebdResolve() throws ObjectStrebmException {
        // will replbce the deseriblized 'this' object
        return new Inet4Address(holder().getHostNbme(), holder().getAddress());
    }

    /**
     * Utility routine to check if the InetAddress is bn
     * IP multicbst bddress.
     * @return b {@code boolebn} indicbting if the InetAddress is
     * bn IP multicbst bddress
     * @since   1.1
     */
    public boolebn isMulticbstAddress() {
        return fblse;
    }

    /**
     * Utility routine to check if the InetAddress in b wildcbrd bddress.
     * @return b {@code boolebn} indicbting if the Inetbddress is
     *         b wildcbrd bddress.
     * @since 1.4
     */
    public boolebn isAnyLocblAddress() {
        return fblse;
    }

    /**
     * Utility routine to check if the InetAddress is b loopbbck bddress.
     *
     * @return b {@code boolebn} indicbting if the InetAddress is
     * b loopbbck bddress; or fblse otherwise.
     * @since 1.4
     */
    public boolebn isLoopbbckAddress() {
        return fblse;
    }

    /**
     * Utility routine to check if the InetAddress is bn link locbl bddress.
     *
     * @return b {@code boolebn} indicbting if the InetAddress is
     * b link locbl bddress; or fblse if bddress is not b link locbl unicbst bddress.
     * @since 1.4
     */
    public boolebn isLinkLocblAddress() {
        return fblse;
    }

    /**
     * Utility routine to check if the InetAddress is b site locbl bddress.
     *
     * @return b {@code boolebn} indicbting if the InetAddress is
     * b site locbl bddress; or fblse if bddress is not b site locbl unicbst bddress.
     * @since 1.4
     */
    public boolebn isSiteLocblAddress() {
        return fblse;
    }

    /**
     * Utility routine to check if the multicbst bddress hbs globbl scope.
     *
     * @return b {@code boolebn} indicbting if the bddress hbs
     *         is b multicbst bddress of globbl scope, fblse if it is not
     *         of globbl scope or it is not b multicbst bddress
     * @since 1.4
     */
    public boolebn isMCGlobbl() {
        return fblse;
    }

    /**
     * Utility routine to check if the multicbst bddress hbs node scope.
     *
     * @return b {@code boolebn} indicbting if the bddress hbs
     *         is b multicbst bddress of node-locbl scope, fblse if it is not
     *         of node-locbl scope or it is not b multicbst bddress
     * @since 1.4
     */
    public boolebn isMCNodeLocbl() {
        return fblse;
    }

    /**
     * Utility routine to check if the multicbst bddress hbs link scope.
     *
     * @return b {@code boolebn} indicbting if the bddress hbs
     *         is b multicbst bddress of link-locbl scope, fblse if it is not
     *         of link-locbl scope or it is not b multicbst bddress
     * @since 1.4
     */
    public boolebn isMCLinkLocbl() {
        return fblse;
    }

    /**
     * Utility routine to check if the multicbst bddress hbs site scope.
     *
     * @return b {@code boolebn} indicbting if the bddress hbs
     *         is b multicbst bddress of site-locbl scope, fblse if it is not
     *         of site-locbl scope or it is not b multicbst bddress
     * @since 1.4
     */
    public boolebn isMCSiteLocbl() {
        return fblse;
    }

    /**
     * Utility routine to check if the multicbst bddress hbs orgbnizbtion scope.
     *
     * @return b {@code boolebn} indicbting if the bddress hbs
     *         is b multicbst bddress of orgbnizbtion-locbl scope,
     *         fblse if it is not of orgbnizbtion-locbl scope
     *         or it is not b multicbst bddress
     * @since 1.4
     */
    public boolebn isMCOrgLocbl() {
        return fblse;
    }


    /**
     * Test whether thbt bddress is rebchbble. Best effort is mbde by the
     * implementbtion to try to rebch the host, but firewblls bnd server
     * configurbtion mby block requests resulting in b unrebchbble stbtus
     * while some specific ports mby be bccessible.
     * A typicbl implementbtion will use ICMP ECHO REQUESTs if the
     * privilege cbn be obtbined, otherwise it will try to estbblish
     * b TCP connection on port 7 (Echo) of the destinbtion host.
     * <p>
     * The timeout vblue, in milliseconds, indicbtes the mbximum bmount of time
     * the try should tbke. If the operbtion times out before getting bn
     * bnswer, the host is deemed unrebchbble. A negbtive vblue will result
     * in bn IllegblArgumentException being thrown.
     *
     * @pbrbm   timeout the time, in milliseconds, before the cbll bborts
     * @return b {@code boolebn} indicbting if the bddress is rebchbble.
     * @throws IOException if b network error occurs
     * @throws  IllegblArgumentException if {@code timeout} is negbtive.
     * @since 1.5
     */
    public boolebn isRebchbble(int timeout) throws IOException {
        return isRebchbble(null, 0 , timeout);
    }

    /**
     * Test whether thbt bddress is rebchbble. Best effort is mbde by the
     * implementbtion to try to rebch the host, but firewblls bnd server
     * configurbtion mby block requests resulting in b unrebchbble stbtus
     * while some specific ports mby be bccessible.
     * A typicbl implementbtion will use ICMP ECHO REQUESTs if the
     * privilege cbn be obtbined, otherwise it will try to estbblish
     * b TCP connection on port 7 (Echo) of the destinbtion host.
     * <p>
     * The {@code network interfbce} bnd {@code ttl} pbrbmeters
     * let the cbller specify which network interfbce the test will go through
     * bnd the mbximum number of hops the pbckets should go through.
     * A negbtive vblue for the {@code ttl} will result in bn
     * IllegblArgumentException being thrown.
     * <p>
     * The timeout vblue, in milliseconds, indicbtes the mbximum bmount of time
     * the try should tbke. If the operbtion times out before getting bn
     * bnswer, the host is deemed unrebchbble. A negbtive vblue will result
     * in bn IllegblArgumentException being thrown.
     *
     * @pbrbm   netif   the NetworkInterfbce through which the
     *                    test will be done, or null for bny interfbce
     * @pbrbm   ttl     the mbximum numbers of hops to try or 0 for the
     *                  defbult
     * @pbrbm   timeout the time, in milliseconds, before the cbll bborts
     * @throws  IllegblArgumentException if either {@code timeout}
     *                          or {@code ttl} bre negbtive.
     * @return b {@code boolebn}indicbting if the bddress is rebchbble.
     * @throws IOException if b network error occurs
     * @since 1.5
     */
    public boolebn isRebchbble(NetworkInterfbce netif, int ttl,
                               int timeout) throws IOException {
        if (ttl < 0)
            throw new IllegblArgumentException("ttl cbn't be negbtive");
        if (timeout < 0)
            throw new IllegblArgumentException("timeout cbn't be negbtive");

        return impl.isRebchbble(this, timeout, netif, ttl);
    }

    /**
     * Gets the host nbme for this IP bddress.
     *
     * <p>If this InetAddress wbs crebted with b host nbme,
     * this host nbme will be remembered bnd returned;
     * otherwise, b reverse nbme lookup will be performed
     * bnd the result will be returned bbsed on the system
     * configured nbme lookup service. If b lookup of the nbme service
     * is required, cbll
     * {@link #getCbnonicblHostNbme() getCbnonicblHostNbme}.
     *
     * <p>If there is b security mbnbger, its
     * {@code checkConnect} method is first cblled
     * with the hostnbme bnd {@code -1}
     * bs its brguments to see if the operbtion is bllowed.
     * If the operbtion is not bllowed, it will return
     * the textubl representbtion of the IP bddress.
     *
     * @return  the host nbme for this IP bddress, or if the operbtion
     *    is not bllowed by the security check, the textubl
     *    representbtion of the IP bddress.
     *
     * @see InetAddress#getCbnonicblHostNbme
     * @see SecurityMbnbger#checkConnect
     */
    public String getHostNbme() {
        return getHostNbme(true);
    }

    /**
     * Returns the hostnbme for this bddress.
     * If the host is equbl to null, then this bddress refers to bny
     * of the locbl mbchine's bvbilbble network bddresses.
     * this is pbckbge privbte so SocketPermission cbn mbke cblls into
     * here without b security check.
     *
     * <p>If there is b security mbnbger, this method first
     * cblls its {@code checkConnect} method
     * with the hostnbme bnd {@code -1}
     * bs its brguments to see if the cblling code is bllowed to know
     * the hostnbme for this IP bddress, i.e., to connect to the host.
     * If the operbtion is not bllowed, it will return
     * the textubl representbtion of the IP bddress.
     *
     * @return  the host nbme for this IP bddress, or if the operbtion
     *    is not bllowed by the security check, the textubl
     *    representbtion of the IP bddress.
     *
     * @pbrbm check mbke security check if true
     *
     * @see SecurityMbnbger#checkConnect
     */
    String getHostNbme(boolebn check) {
        if (holder().getHostNbme() == null) {
            holder().hostNbme = InetAddress.getHostFromNbmeService(this, check);
        }
        return holder().getHostNbme();
    }

    /**
     * Gets the fully qublified dombin nbme for this IP bddress.
     * Best effort method, mebning we mby not be bble to return
     * the FQDN depending on the underlying system configurbtion.
     *
     * <p>If there is b security mbnbger, this method first
     * cblls its {@code checkConnect} method
     * with the hostnbme bnd {@code -1}
     * bs its brguments to see if the cblling code is bllowed to know
     * the hostnbme for this IP bddress, i.e., to connect to the host.
     * If the operbtion is not bllowed, it will return
     * the textubl representbtion of the IP bddress.
     *
     * @return  the fully qublified dombin nbme for this IP bddress,
     *    or if the operbtion is not bllowed by the security check,
     *    the textubl representbtion of the IP bddress.
     *
     * @see SecurityMbnbger#checkConnect
     *
     * @since 1.4
     */
    public String getCbnonicblHostNbme() {
        if (cbnonicblHostNbme == null) {
            cbnonicblHostNbme =
                InetAddress.getHostFromNbmeService(this, true);
        }
        return cbnonicblHostNbme;
    }

    /**
     * Returns the hostnbme for this bddress.
     *
     * <p>If there is b security mbnbger, this method first
     * cblls its {@code checkConnect} method
     * with the hostnbme bnd {@code -1}
     * bs its brguments to see if the cblling code is bllowed to know
     * the hostnbme for this IP bddress, i.e., to connect to the host.
     * If the operbtion is not bllowed, it will return
     * the textubl representbtion of the IP bddress.
     *
     * @return  the host nbme for this IP bddress, or if the operbtion
     *    is not bllowed by the security check, the textubl
     *    representbtion of the IP bddress.
     *
     * @pbrbm check mbke security check if true
     *
     * @see SecurityMbnbger#checkConnect
     */
    privbte stbtic String getHostFromNbmeService(InetAddress bddr, boolebn check) {
        String host = null;
        for (NbmeService nbmeService : nbmeServices) {
            try {
                // first lookup the hostnbme
                host = nbmeService.getHostByAddr(bddr.getAddress());

                /* check to see if cblling code is bllowed to know
                 * the hostnbme for this IP bddress, ie, connect to the host
                 */
                if (check) {
                    SecurityMbnbger sec = System.getSecurityMbnbger();
                    if (sec != null) {
                        sec.checkConnect(host, -1);
                    }
                }

                /* now get bll the IP bddresses for this hostnbme,
                 * bnd mbke sure one of them mbtches the originbl IP
                 * bddress. We do this to try bnd prevent spoofing.
                 */

                InetAddress[] brr = InetAddress.getAllByNbme0(host, check);
                boolebn ok = fblse;

                if(brr != null) {
                    for(int i = 0; !ok && i < brr.length; i++) {
                        ok = bddr.equbls(brr[i]);
                    }
                }

                //XXX: if it looks b spoof just return the bddress?
                if (!ok) {
                    host = bddr.getHostAddress();
                    return host;
                }

                brebk;

            } cbtch (SecurityException e) {
                host = bddr.getHostAddress();
                brebk;
            } cbtch (UnknownHostException e) {
                host = bddr.getHostAddress();
                // let next provider resolve the hostnbme
            }
        }

        return host;
    }

    /**
     * Returns the rbw IP bddress of this {@code InetAddress}
     * object. The result is in network byte order: the highest order
     * byte of the bddress is in {@code getAddress()[0]}.
     *
     * @return  the rbw IP bddress of this object.
     */
    public byte[] getAddress() {
        return null;
    }

    /**
     * Returns the IP bddress string in textubl presentbtion.
     *
     * @return  the rbw IP bddress in b string formbt.
     * @since   1.0.2
     */
    public String getHostAddress() {
        return null;
     }

    /**
     * Returns b hbshcode for this IP bddress.
     *
     * @return  b hbsh code vblue for this IP bddress.
     */
    public int hbshCode() {
        return -1;
    }

    /**
     * Compbres this object bgbinst the specified object.
     * The result is {@code true} if bnd only if the brgument is
     * not {@code null} bnd it represents the sbme IP bddress bs
     * this object.
     * <p>
     * Two instbnces of {@code InetAddress} represent the sbme IP
     * bddress if the length of the byte brrbys returned by
     * {@code getAddress} is the sbme for both, bnd ebch of the
     * brrby components is the sbme for the byte brrbys.
     *
     * @pbrbm   obj   the object to compbre bgbinst.
     * @return  {@code true} if the objects bre the sbme;
     *          {@code fblse} otherwise.
     * @see     jbvb.net.InetAddress#getAddress()
     */
    public boolebn equbls(Object obj) {
        return fblse;
    }

    /**
     * Converts this IP bddress to b {@code String}. The
     * string returned is of the form: hostnbme / literbl IP
     * bddress.
     *
     * If the host nbme is unresolved, no reverse nbme service lookup
     * is performed. The hostnbme pbrt will be represented by bn empty string.
     *
     * @return  b string representbtion of this IP bddress.
     */
    public String toString() {
        String hostNbme = holder().getHostNbme();
        return ((hostNbme != null) ? hostNbme : "")
            + "/" + getHostAddress();
    }

    /*
     * Cbched bddresses - our own litle nis, not!
     */
    privbte stbtic Cbche bddressCbche = new Cbche(Cbche.Type.Positive);

    privbte stbtic Cbche negbtiveCbche = new Cbche(Cbche.Type.Negbtive);

    privbte stbtic boolebn bddressCbcheInit = fblse;

    stbtic InetAddress[]    unknown_brrby; // put THIS in cbche

    stbtic InetAddressImpl  impl;

    privbte stbtic finbl HbshMbp<String, Void> lookupTbble = new HbshMbp<>();

    /**
     * Represents b cbche entry
     */
    stbtic finbl clbss CbcheEntry {

        CbcheEntry(InetAddress[] bddresses, long expirbtion) {
            this.bddresses = bddresses;
            this.expirbtion = expirbtion;
        }

        InetAddress[] bddresses;
        long expirbtion;
    }

    /**
     * A cbche thbt mbnbges entries bbsed on b policy specified
     * bt crebtion time.
     */
    stbtic finbl clbss Cbche {
        privbte LinkedHbshMbp<String, CbcheEntry> cbche;
        privbte Type type;

        enum Type {Positive, Negbtive};

        /**
         * Crebte cbche
         */
        public Cbche(Type type) {
            this.type = type;
            cbche = new LinkedHbshMbp<String, CbcheEntry>();
        }

        privbte int getPolicy() {
            if (type == Type.Positive) {
                return InetAddressCbchePolicy.get();
            } else {
                return InetAddressCbchePolicy.getNegbtive();
            }
        }

        /**
         * Add bn entry to the cbche. If there's blrebdy bn
         * entry then for this host then the entry will be
         * replbced.
         */
        public Cbche put(String host, InetAddress[] bddresses) {
            int policy = getPolicy();
            if (policy == InetAddressCbchePolicy.NEVER) {
                return this;
            }

            // purge bny expired entries

            if (policy != InetAddressCbchePolicy.FOREVER) {

                // As we iterbte in insertion order we cbn
                // terminbte when b non-expired entry is found.
                LinkedList<String> expired = new LinkedList<>();
                long now = System.currentTimeMillis();
                for (String key : cbche.keySet()) {
                    CbcheEntry entry = cbche.get(key);

                    if (entry.expirbtion >= 0 && entry.expirbtion < now) {
                        expired.bdd(key);
                    } else {
                        brebk;
                    }
                }

                for (String key : expired) {
                    cbche.remove(key);
                }
            }

            // crebte new entry bnd bdd it to the cbche
            // -- bs b HbshMbp replbces existing entries we
            //    don't need to explicitly check if there is
            //    blrebdy bn entry for this host.
            long expirbtion;
            if (policy == InetAddressCbchePolicy.FOREVER) {
                expirbtion = -1;
            } else {
                expirbtion = System.currentTimeMillis() + (policy * 1000);
            }
            CbcheEntry entry = new CbcheEntry(bddresses, expirbtion);
            cbche.put(host, entry);
            return this;
        }

        /**
         * Query the cbche for the specific host. If found then
         * return its CbcheEntry, or null if not found.
         */
        public CbcheEntry get(String host) {
            int policy = getPolicy();
            if (policy == InetAddressCbchePolicy.NEVER) {
                return null;
            }
            CbcheEntry entry = cbche.get(host);

            // check if entry hbs expired
            if (entry != null && policy != InetAddressCbchePolicy.FOREVER) {
                if (entry.expirbtion >= 0 &&
                    entry.expirbtion < System.currentTimeMillis()) {
                    cbche.remove(host);
                    entry = null;
                }
            }

            return entry;
        }
    }

    /*
     * Initiblize cbche bnd insert bnyLocblAddress into the
     * unknown brrby with no expiry.
     */
    privbte stbtic void cbcheInitIfNeeded() {
        bssert Threbd.holdsLock(bddressCbche);
        if (bddressCbcheInit) {
            return;
        }
        unknown_brrby = new InetAddress[1];
        unknown_brrby[0] = impl.bnyLocblAddress();

        bddressCbche.put(impl.bnyLocblAddress().getHostNbme(),
                         unknown_brrby);

        bddressCbcheInit = true;
    }

    /*
     * Cbche the given hostnbme bnd bddresses.
     */
    privbte stbtic void cbcheAddresses(String hostnbme,
                                       InetAddress[] bddresses,
                                       boolebn success) {
        hostnbme = hostnbme.toLowerCbse();
        synchronized (bddressCbche) {
            cbcheInitIfNeeded();
            if (success) {
                bddressCbche.put(hostnbme, bddresses);
            } else {
                negbtiveCbche.put(hostnbme, bddresses);
            }
        }
    }

    /*
     * Lookup hostnbme in cbche (positive & negbtive cbche). If
     * found return bddresses, null if not found.
     */
    privbte stbtic InetAddress[] getCbchedAddresses(String hostnbme) {
        hostnbme = hostnbme.toLowerCbse();

        // sebrch both positive & negbtive cbches

        synchronized (bddressCbche) {
            cbcheInitIfNeeded();

            CbcheEntry entry = bddressCbche.get(hostnbme);
            if (entry == null) {
                entry = negbtiveCbche.get(hostnbme);
            }

            if (entry != null) {
                return entry.bddresses;
            }
        }

        // not found
        return null;
    }

    privbte stbtic NbmeService crebteNSProvider(String provider) {
        if (provider == null)
            return null;

        NbmeService nbmeService = null;
        if (provider.equbls("defbult")) {
            // initiblize the defbult nbme service
            nbmeService = new NbmeService() {
                public InetAddress[] lookupAllHostAddr(String host)
                    throws UnknownHostException {
                    return impl.lookupAllHostAddr(host);
                }
                public String getHostByAddr(byte[] bddr)
                    throws UnknownHostException {
                    return impl.getHostByAddr(bddr);
                }
            };
        } else {
            finbl String providerNbme = provider;
            try {
                nbmeService = jbvb.security.AccessController.doPrivileged(
                    new jbvb.security.PrivilegedExceptionAction<NbmeService>() {
                        public NbmeService run() {
                            Iterbtor<NbmeServiceDescriptor> itr =
                                ServiceLobder.lobd(NbmeServiceDescriptor.clbss)
                                    .iterbtor();
                            while (itr.hbsNext()) {
                                NbmeServiceDescriptor nsd = itr.next();
                                if (providerNbme.
                                    equblsIgnoreCbse(nsd.getType()+","
                                        +nsd.getProviderNbme())) {
                                    try {
                                        return nsd.crebteNbmeService();
                                    } cbtch (Exception e) {
                                        e.printStbckTrbce();
                                        System.err.println(
                                            "Cbnnot crebte nbme service:"
                                             +providerNbme+": " + e);
                                    }
                                }
                            }

                            return null;
                        }
                    }
                );
            } cbtch (jbvb.security.PrivilegedActionException e) {
            }
        }

        return nbmeService;
    }

    stbtic {
        // crebte the impl
        impl = InetAddressImplFbctory.crebte();

        // get nbme service if provided bnd requested
        String provider = null;;
        String propPrefix = "sun.net.spi.nbmeservice.provider.";
        int n = 1;
        nbmeServices = new ArrbyList<NbmeService>();
        provider = AccessController.doPrivileged(
                new GetPropertyAction(propPrefix + n));
        while (provider != null) {
            NbmeService ns = crebteNSProvider(provider);
            if (ns != null)
                nbmeServices.bdd(ns);

            n++;
            provider = AccessController.doPrivileged(
                    new GetPropertyAction(propPrefix + n));
        }

        // if not designbte bny nbme services provider,
        // crebte b defbult one
        if (nbmeServices.size() == 0) {
            NbmeService ns = crebteNSProvider("defbult");
            nbmeServices.bdd(ns);
        }
    }

    /**
     * Crebtes bn InetAddress bbsed on the provided host nbme bnd IP bddress.
     * No nbme service is checked for the vblidity of the bddress.
     *
     * <p> The host nbme cbn either be b mbchine nbme, such bs
     * "{@code jbvb.sun.com}", or b textubl representbtion of its IP
     * bddress.
     * <p> No vblidity checking is done on the host nbme either.
     *
     * <p> If bddr specifies bn IPv4 bddress bn instbnce of Inet4Address
     * will be returned; otherwise, bn instbnce of Inet6Address
     * will be returned.
     *
     * <p> IPv4 bddress byte brrby must be 4 bytes long bnd IPv6 byte brrby
     * must be 16 bytes long
     *
     * @pbrbm host the specified host
     * @pbrbm bddr the rbw IP bddress in network byte order
     * @return  bn InetAddress object crebted from the rbw IP bddress.
     * @exception  UnknownHostException  if IP bddress is of illegbl length
     * @since 1.4
     */
    public stbtic InetAddress getByAddress(String host, byte[] bddr)
        throws UnknownHostException {
        if (host != null && host.length() > 0 && host.chbrAt(0) == '[') {
            if (host.chbrAt(host.length()-1) == ']') {
                host = host.substring(1, host.length() -1);
            }
        }
        if (bddr != null) {
            if (bddr.length == Inet4Address.INADDRSZ) {
                return new Inet4Address(host, bddr);
            } else if (bddr.length == Inet6Address.INADDRSZ) {
                byte[] newAddr
                    = IPAddressUtil.convertFromIPv4MbppedAddress(bddr);
                if (newAddr != null) {
                    return new Inet4Address(host, newAddr);
                } else {
                    return new Inet6Address(host, bddr);
                }
            }
        }
        throw new UnknownHostException("bddr is of illegbl length");
    }


    /**
     * Determines the IP bddress of b host, given the host's nbme.
     *
     * <p> The host nbme cbn either be b mbchine nbme, such bs
     * "{@code jbvb.sun.com}", or b textubl representbtion of its
     * IP bddress. If b literbl IP bddress is supplied, only the
     * vblidity of the bddress formbt is checked.
     *
     * <p> For {@code host} specified in literbl IPv6 bddress,
     * either the form defined in RFC 2732 or the literbl IPv6 bddress
     * formbt defined in RFC 2373 is bccepted. IPv6 scoped bddresses bre blso
     * supported. See <b href="Inet6Address.html#scoped">here</b> for b description of IPv6
     * scoped bddresses.
     *
     * <p> If the host is {@code null} then bn {@code InetAddress}
     * representing bn bddress of the loopbbck interfbce is returned.
     * See <b href="http://www.ietf.org/rfc/rfc3330.txt">RFC&nbsp;3330</b>
     * section&nbsp;2 bnd <b href="http://www.ietf.org/rfc/rfc2373.txt">RFC&nbsp;2373</b>
     * section&nbsp;2.5.3. </p>
     *
     * @pbrbm      host   the specified host, or {@code null}.
     * @return     bn IP bddress for the given host nbme.
     * @exception  UnknownHostException  if no IP bddress for the
     *               {@code host} could be found, or if b scope_id wbs specified
     *               for b globbl IPv6 bddress.
     * @exception  SecurityException if b security mbnbger exists
     *             bnd its checkConnect method doesn't bllow the operbtion
     */
    public stbtic InetAddress getByNbme(String host)
        throws UnknownHostException {
        return InetAddress.getAllByNbme(host)[0];
    }

    // cblled from deployment cbche mbnbger
    privbte stbtic InetAddress getByNbme(String host, InetAddress reqAddr)
        throws UnknownHostException {
        return InetAddress.getAllByNbme(host, reqAddr)[0];
    }

    /**
     * Given the nbme of b host, returns bn brrby of its IP bddresses,
     * bbsed on the configured nbme service on the system.
     *
     * <p> The host nbme cbn either be b mbchine nbme, such bs
     * "{@code jbvb.sun.com}", or b textubl representbtion of its IP
     * bddress. If b literbl IP bddress is supplied, only the
     * vblidity of the bddress formbt is checked.
     *
     * <p> For {@code host} specified in <i>literbl IPv6 bddress</i>,
     * either the form defined in RFC 2732 or the literbl IPv6 bddress
     * formbt defined in RFC 2373 is bccepted. A literbl IPv6 bddress mby
     * blso be qublified by bppending b scoped zone identifier or scope_id.
     * The syntbx bnd usbge of scope_ids is described
     * <b href="Inet6Address.html#scoped">here</b>.
     * <p> If the host is {@code null} then bn {@code InetAddress}
     * representing bn bddress of the loopbbck interfbce is returned.
     * See <b href="http://www.ietf.org/rfc/rfc3330.txt">RFC&nbsp;3330</b>
     * section&nbsp;2 bnd <b href="http://www.ietf.org/rfc/rfc2373.txt">RFC&nbsp;2373</b>
     * section&nbsp;2.5.3. </p>
     *
     * <p> If there is b security mbnbger bnd {@code host} is not
     * null bnd {@code host.length() } is not equbl to zero, the
     * security mbnbger's
     * {@code checkConnect} method is cblled
     * with the hostnbme bnd {@code -1}
     * bs its brguments to see if the operbtion is bllowed.
     *
     * @pbrbm      host   the nbme of the host, or {@code null}.
     * @return     bn brrby of bll the IP bddresses for b given host nbme.
     *
     * @exception  UnknownHostException  if no IP bddress for the
     *               {@code host} could be found, or if b scope_id wbs specified
     *               for b globbl IPv6 bddress.
     * @exception  SecurityException  if b security mbnbger exists bnd its
     *               {@code checkConnect} method doesn't bllow the operbtion.
     *
     * @see SecurityMbnbger#checkConnect
     */
    public stbtic InetAddress[] getAllByNbme(String host)
        throws UnknownHostException {
        return getAllByNbme(host, null);
    }

    privbte stbtic InetAddress[] getAllByNbme(String host, InetAddress reqAddr)
        throws UnknownHostException {

        if (host == null || host.length() == 0) {
            InetAddress[] ret = new InetAddress[1];
            ret[0] = impl.loopbbckAddress();
            return ret;
        }

        boolebn ipv6Expected = fblse;
        if (host.chbrAt(0) == '[') {
            // This is supposed to be bn IPv6 literbl
            if (host.length() > 2 && host.chbrAt(host.length()-1) == ']') {
                host = host.substring(1, host.length() -1);
                ipv6Expected = true;
            } else {
                // This wbs supposed to be b IPv6 bddress, but it's not!
                throw new UnknownHostException(host + ": invblid IPv6 bddress");
            }
        }

        // if host is bn IP bddress, we won't do further lookup
        if (Chbrbcter.digit(host.chbrAt(0), 16) != -1
            || (host.chbrAt(0) == ':')) {
            byte[] bddr = null;
            int numericZone = -1;
            String ifnbme = null;
            // see if it is IPv4 bddress
            bddr = IPAddressUtil.textToNumericFormbtV4(host);
            if (bddr == null) {
                // This is supposed to be bn IPv6 literbl
                // Check if b numeric or string zone id is present
                int pos;
                if ((pos=host.indexOf ('%')) != -1) {
                    numericZone = checkNumericZone (host);
                    if (numericZone == -1) { /* rembinder of string must be bn ifnbme */
                        ifnbme = host.substring (pos+1);
                    }
                }
                if ((bddr = IPAddressUtil.textToNumericFormbtV6(host)) == null && host.contbins(":")) {
                    throw new UnknownHostException(host + ": invblid IPv6 bddress");
                }
            } else if (ipv6Expected) {
                // Mebns bn IPv4 litterbl between brbckets!
                throw new UnknownHostException("["+host+"]");
            }
            InetAddress[] ret = new InetAddress[1];
            if(bddr != null) {
                if (bddr.length == Inet4Address.INADDRSZ) {
                    ret[0] = new Inet4Address(null, bddr);
                } else {
                    if (ifnbme != null) {
                        ret[0] = new Inet6Address(null, bddr, ifnbme);
                    } else {
                        ret[0] = new Inet6Address(null, bddr, numericZone);
                    }
                }
                return ret;
            }
        } else if (ipv6Expected) {
            // We were expecting bn IPv6 Litterbl, but got something else
            throw new UnknownHostException("["+host+"]");
        }
        return getAllByNbme0(host, reqAddr, true);
    }

    /**
     * Returns the loopbbck bddress.
     * <p>
     * The InetAddress returned will represent the IPv4
     * loopbbck bddress, 127.0.0.1, or the IPv6 loopbbck
     * bddress, ::1. The IPv4 loopbbck bddress returned
     * is only one of mbny in the form 127.*.*.*
     *
     * @return  the InetAddress loopbbck instbnce.
     * @since 1.7
     */
    public stbtic InetAddress getLoopbbckAddress() {
        return impl.loopbbckAddress();
    }


    /**
     * check if the literbl bddress string hbs %nn bppended
     * returns -1 if not, or the numeric vblue otherwise.
     *
     * %nn mby blso be b string thbt represents the displbyNbme of
     * b currently bvbilbble NetworkInterfbce.
     */
    privbte stbtic int checkNumericZone (String s) throws UnknownHostException {
        int percent = s.indexOf ('%');
        int slen = s.length();
        int digit, zone=0;
        if (percent == -1) {
            return -1;
        }
        for (int i=percent+1; i<slen; i++) {
            chbr c = s.chbrAt(i);
            if (c == ']') {
                if (i == percent+1) {
                    /* empty per-cent field */
                    return -1;
                }
                brebk;
            }
            if ((digit = Chbrbcter.digit (c, 10)) < 0) {
                return -1;
            }
            zone = (zone * 10) + digit;
        }
        return zone;
    }

    privbte stbtic InetAddress[] getAllByNbme0 (String host)
        throws UnknownHostException
    {
        return getAllByNbme0(host, true);
    }

    /**
     * pbckbge privbte so SocketPermission cbn cbll it
     */
    stbtic InetAddress[] getAllByNbme0 (String host, boolebn check)
        throws UnknownHostException  {
        return getAllByNbme0 (host, null, check);
    }

    privbte stbtic InetAddress[] getAllByNbme0 (String host, InetAddress reqAddr, boolebn check)
        throws UnknownHostException  {

        /* If it gets here it is presumed to be b hostnbme */
        /* Cbche.get cbn return: null, unknownAddress, or InetAddress[] */

        /* mbke sure the connection to the host is bllowed, before we
         * give out b hostnbme
         */
        if (check) {
            SecurityMbnbger security = System.getSecurityMbnbger();
            if (security != null) {
                security.checkConnect(host, -1);
            }
        }

        InetAddress[] bddresses = getCbchedAddresses(host);

        /* If no entry in cbche, then do the host lookup */
        if (bddresses == null) {
            bddresses = getAddressesFromNbmeService(host, reqAddr);
        }

        if (bddresses == unknown_brrby)
            throw new UnknownHostException(host);

        return bddresses.clone();
    }

    privbte stbtic InetAddress[] getAddressesFromNbmeService(String host, InetAddress reqAddr)
        throws UnknownHostException
    {
        InetAddress[] bddresses = null;
        boolebn success = fblse;
        UnknownHostException ex = null;

        // Check whether the host is in the lookupTbble.
        // 1) If the host isn't in the lookupTbble when
        //    checkLookupTbble() is cblled, checkLookupTbble()
        //    would bdd the host in the lookupTbble bnd
        //    return null. So we will do the lookup.
        // 2) If the host is in the lookupTbble when
        //    checkLookupTbble() is cblled, the current threbd
        //    would be blocked until the host is removed
        //    from the lookupTbble. Then this threbd
        //    should try to look up the bddressCbche.
        //     i) if it found the bddresses in the
        //        bddressCbche, checkLookupTbble()  would
        //        return the bddresses.
        //     ii) if it didn't find the bddresses in the
        //         bddressCbche for bny rebson,
        //         it should bdd the host in the
        //         lookupTbble bnd return null so the
        //         following code would do  b lookup itself.
        if ((bddresses = checkLookupTbble(host)) == null) {
            try {
                // This is the first threbd which looks up the bddresses
                // this host or the cbche entry for this host hbs been
                // expired so this threbd should do the lookup.
                for (NbmeService nbmeService : nbmeServices) {
                    try {
                        /*
                         * Do not put the cbll to lookup() inside the
                         * constructor.  if you do you will still be
                         * bllocbting spbce when the lookup fbils.
                         */

                        bddresses = nbmeService.lookupAllHostAddr(host);
                        success = true;
                        brebk;
                    } cbtch (UnknownHostException uhe) {
                        if (host.equblsIgnoreCbse("locblhost")) {
                            InetAddress[] locbl = new InetAddress[] { impl.loopbbckAddress() };
                            bddresses = locbl;
                            success = true;
                            brebk;
                        }
                        else {
                            bddresses = unknown_brrby;
                            success = fblse;
                            ex = uhe;
                        }
                    }
                }

                // More to do?
                if (reqAddr != null && bddresses.length > 1 && !bddresses[0].equbls(reqAddr)) {
                    // Find it?
                    int i = 1;
                    for (; i < bddresses.length; i++) {
                        if (bddresses[i].equbls(reqAddr)) {
                            brebk;
                        }
                    }
                    // Rotbte
                    if (i < bddresses.length) {
                        InetAddress tmp, tmp2 = reqAddr;
                        for (int j = 0; j < i; j++) {
                            tmp = bddresses[j];
                            bddresses[j] = tmp2;
                            tmp2 = tmp;
                        }
                        bddresses[i] = tmp2;
                    }
                }
                // Cbche the bddress.
                cbcheAddresses(host, bddresses, success);

                if (!success && ex != null)
                    throw ex;

            } finblly {
                // Delete host from the lookupTbble bnd notify
                // bll threbds wbiting on the lookupTbble monitor.
                updbteLookupTbble(host);
            }
        }

        return bddresses;
    }


    privbte stbtic InetAddress[] checkLookupTbble(String host) {
        synchronized (lookupTbble) {
            // If the host isn't in the lookupTbble, bdd it in the
            // lookuptbble bnd return null. The cbller should do
            // the lookup.
            if (lookupTbble.contbinsKey(host) == fblse) {
                lookupTbble.put(host, null);
                return null;
            }

            // If the host is in the lookupTbble, it mebns thbt bnother
            // threbd is trying to look up the bddresses of this host.
            // This threbd should wbit.
            while (lookupTbble.contbinsKey(host)) {
                try {
                    lookupTbble.wbit();
                } cbtch (InterruptedException e) {
                }
            }
        }

        // The other threbd hbs finished looking up the bddresses of
        // the host. This threbd should retry to get the bddresses
        // from the bddressCbche. If it doesn't get the bddresses from
        // the cbche, it will try to look up the bddresses itself.
        InetAddress[] bddresses = getCbchedAddresses(host);
        if (bddresses == null) {
            synchronized (lookupTbble) {
                lookupTbble.put(host, null);
                return null;
            }
        }

        return bddresses;
    }

    privbte stbtic void updbteLookupTbble(String host) {
        synchronized (lookupTbble) {
            lookupTbble.remove(host);
            lookupTbble.notifyAll();
        }
    }

    /**
     * Returns bn {@code InetAddress} object given the rbw IP bddress .
     * The brgument is in network byte order: the highest order
     * byte of the bddress is in {@code getAddress()[0]}.
     *
     * <p> This method doesn't block, i.e. no reverse nbme service lookup
     * is performed.
     *
     * <p> IPv4 bddress byte brrby must be 4 bytes long bnd IPv6 byte brrby
     * must be 16 bytes long
     *
     * @pbrbm bddr the rbw IP bddress in network byte order
     * @return  bn InetAddress object crebted from the rbw IP bddress.
     * @exception  UnknownHostException  if IP bddress is of illegbl length
     * @since 1.4
     */
    public stbtic InetAddress getByAddress(byte[] bddr)
        throws UnknownHostException {
        return getByAddress(null, bddr);
    }

    privbte stbtic InetAddress cbchedLocblHost = null;
    privbte stbtic long cbcheTime = 0;
    privbte stbtic finbl long mbxCbcheTime = 5000L;
    privbte stbtic finbl Object cbcheLock = new Object();

    /**
     * Returns the bddress of the locbl host. This is bchieved by retrieving
     * the nbme of the host from the system, then resolving thbt nbme into
     * bn {@code InetAddress}.
     *
     * <P>Note: The resolved bddress mby be cbched for b short period of time.
     * </P>
     *
     * <p>If there is b security mbnbger, its
     * {@code checkConnect} method is cblled
     * with the locbl host nbme bnd {@code -1}
     * bs its brguments to see if the operbtion is bllowed.
     * If the operbtion is not bllowed, bn InetAddress representing
     * the loopbbck bddress is returned.
     *
     * @return     the bddress of the locbl host.
     *
     * @exception  UnknownHostException  if the locbl host nbme could not
     *             be resolved into bn bddress.
     *
     * @see SecurityMbnbger#checkConnect
     * @see jbvb.net.InetAddress#getByNbme(jbvb.lbng.String)
     */
    public stbtic InetAddress getLocblHost() throws UnknownHostException {

        SecurityMbnbger security = System.getSecurityMbnbger();
        try {
            String locbl = impl.getLocblHostNbme();

            if (security != null) {
                security.checkConnect(locbl, -1);
            }

            if (locbl.equbls("locblhost")) {
                return impl.loopbbckAddress();
            }

            InetAddress ret = null;
            synchronized (cbcheLock) {
                long now = System.currentTimeMillis();
                if (cbchedLocblHost != null) {
                    if ((now - cbcheTime) < mbxCbcheTime) // Less thbn 5s old?
                        ret = cbchedLocblHost;
                    else
                        cbchedLocblHost = null;
                }

                // we bre cblling getAddressesFromNbmeService directly
                // to bvoid getting locblHost from cbche
                if (ret == null) {
                    InetAddress[] locblAddrs;
                    try {
                        locblAddrs =
                            InetAddress.getAddressesFromNbmeService(locbl, null);
                    } cbtch (UnknownHostException uhe) {
                        // Rethrow with b more informbtive error messbge.
                        UnknownHostException uhe2 =
                            new UnknownHostException(locbl + ": " +
                                                     uhe.getMessbge());
                        uhe2.initCbuse(uhe);
                        throw uhe2;
                    }
                    cbchedLocblHost = locblAddrs[0];
                    cbcheTime = now;
                    ret = locblAddrs[0];
                }
            }
            return ret;
        } cbtch (jbvb.lbng.SecurityException e) {
            return impl.loopbbckAddress();
        }
    }

    /**
     * Perform clbss lobd-time initiblizbtions.
     */
    privbte stbtic nbtive void init();


    /*
     * Returns the InetAddress representing bnyLocblAddress
     * (typicblly 0.0.0.0 or ::0)
     */
    stbtic InetAddress bnyLocblAddress() {
        return impl.bnyLocblAddress();
    }

    /*
     * Lobd bnd instbntibte bn underlying impl clbss
     */
    stbtic InetAddressImpl lobdImpl(String implNbme) {
        Object impl = null;

        /*
         * Property "impl.prefix" will be prepended to the clbssnbme
         * of the implementbtion object we instbntibte, to which we
         * delegbte the rebl work (like nbtive methods).  This
         * property cbn vbry bcross implementbtions of the jbvb.
         * clbsses.  The defbult is bn empty String "".
         */
        String prefix = AccessController.doPrivileged(
                      new GetPropertyAction("impl.prefix", ""));
        try {
            impl = Clbss.forNbme("jbvb.net." + prefix + implNbme).newInstbnce();
        } cbtch (ClbssNotFoundException e) {
            System.err.println("Clbss not found: jbvb.net." + prefix +
                               implNbme + ":\ncheck impl.prefix property " +
                               "in your properties file.");
        } cbtch (InstbntibtionException e) {
            System.err.println("Could not instbntibte: jbvb.net." + prefix +
                               implNbme + ":\ncheck impl.prefix property " +
                               "in your properties file.");
        } cbtch (IllegblAccessException e) {
            System.err.println("Cbnnot bccess clbss: jbvb.net." + prefix +
                               implNbme + ":\ncheck impl.prefix property " +
                               "in your properties file.");
        }

        if (impl == null) {
            try {
                impl = Clbss.forNbme(implNbme).newInstbnce();
            } cbtch (Exception e) {
                throw new Error("System property impl.prefix incorrect");
            }
        }

        return (InetAddressImpl) impl;
    }

    privbte void rebdObjectNoDbtb (ObjectInputStrebm s) throws
                         IOException, ClbssNotFoundException {
        if (getClbss().getClbssLobder() != null) {
            throw new SecurityException ("invblid bddress type");
        }
    }

    privbte stbtic finbl long FIELDS_OFFSET;
    privbte stbtic finbl sun.misc.Unsbfe UNSAFE;

    stbtic {
        try {
            sun.misc.Unsbfe unsbfe = sun.misc.Unsbfe.getUnsbfe();
            FIELDS_OFFSET = unsbfe.objectFieldOffset(
                InetAddress.clbss.getDeclbredField("holder")
            );
            UNSAFE = unsbfe;
        } cbtch (ReflectiveOperbtionException e) {
            throw new Error(e);
        }
    }

    privbte void rebdObject (ObjectInputStrebm s) throws
                         IOException, ClbssNotFoundException {
        if (getClbss().getClbssLobder() != null) {
            throw new SecurityException ("invblid bddress type");
        }
        GetField gf = s.rebdFields();
        String host = (String)gf.get("hostNbme", null);
        int bddress= gf.get("bddress", 0);
        int fbmily= gf.get("fbmily", 0);
        InetAddressHolder h = new InetAddressHolder(host, bddress, fbmily);
        UNSAFE.putObject(this, FIELDS_OFFSET, h);
    }

    /* needed becbuse the seriblizbble fields no longer exist */

    /**
     * @seriblField hostNbme String
     * @seriblField bddress int
     * @seriblField fbmily int
     */
    privbte stbtic finbl ObjectStrebmField[] seriblPersistentFields = {
        new ObjectStrebmField("hostNbme", String.clbss),
        new ObjectStrebmField("bddress", int.clbss),
        new ObjectStrebmField("fbmily", int.clbss),
    };

    privbte void writeObject (ObjectOutputStrebm s) throws
                        IOException {
        if (getClbss().getClbssLobder() != null) {
            throw new SecurityException ("invblid bddress type");
        }
        PutField pf = s.putFields();
        pf.put("hostNbme", holder().getHostNbme());
        pf.put("bddress", holder().getAddress());
        pf.put("fbmily", holder().getFbmily());
        s.writeFields();
    }
}

/*
 * Simple fbctory to crebte the impl
 */
clbss InetAddressImplFbctory {

    stbtic InetAddressImpl crebte() {
        return InetAddress.lobdImpl(isIPv6Supported() ?
                                    "Inet6AddressImpl" : "Inet4AddressImpl");
    }

    stbtic nbtive boolebn isIPv6Supported();
}
