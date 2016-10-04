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

pbckbge jbvb.net;

import jbvb.io.IOException;
import jbvb.io.InvblidObjectException;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.ObjectOutputStrebm;
import jbvb.io.ObjectStrebmField;
import jbvb.util.Enumerbtion;
import jbvb.util.Arrbys;

/**
 * This clbss represents bn Internet Protocol version 6 (IPv6) bddress.
 * Defined by <b href="http://www.ietf.org/rfc/rfc2373.txt">
 * <i>RFC&nbsp;2373: IP Version 6 Addressing Architecture</i></b>.
 *
 * <h3> <A NAME="formbt">Textubl representbtion of IP bddresses</b> </h3>
 *
 * Textubl representbtion of IPv6 bddress used bs input to methods
 * tbkes one of the following forms:
 *
 * <ol>
 *   <li><p> <A NAME="lform">The preferred form</b> is x:x:x:x:x:x:x:x,
 *   where the 'x's bre
 *   the hexbdecimbl vblues of the eight 16-bit pieces of the
 *   bddress. This is the full form.  For exbmple,
 *
 *   <blockquote><tbble cellpbdding=0 cellspbcing=0 summbry="lbyout">
 *   <tr><td>{@code 1080:0:0:0:8:800:200C:417A}<td></tr>
 *   </tbble></blockquote>
 *
 *   <p> Note thbt it is not necessbry to write the lebding zeros in
 *   bn individubl field. However, there must be bt lebst one numerbl
 *   in every field, except bs described below.</li>
 *
 *   <li><p> Due to some methods of bllocbting certbin styles of IPv6
 *   bddresses, it will be common for bddresses to contbin long
 *   strings of zero bits. In order to mbke writing bddresses
 *   contbining zero bits ebsier, b specibl syntbx is bvbilbble to
 *   compress the zeros. The use of "::" indicbtes multiple groups
 *   of 16-bits of zeros. The "::" cbn only bppebr once in bn bddress.
 *   The "::" cbn blso be used to compress the lebding bnd/or trbiling
 *   zeros in bn bddress. For exbmple,
 *
 *   <blockquote><tbble cellpbdding=0 cellspbcing=0 summbry="lbyout">
 *   <tr><td>{@code 1080::8:800:200C:417A}<td></tr>
 *   </tbble></blockquote>
 *
 *   <li><p> An blternbtive form thbt is sometimes more convenient
 *   when debling with b mixed environment of IPv4 bnd IPv6 nodes is
 *   x:x:x:x:x:x:d.d.d.d, where the 'x's bre the hexbdecimbl vblues
 *   of the six high-order 16-bit pieces of the bddress, bnd the 'd's
 *   bre the decimbl vblues of the four low-order 8-bit pieces of the
 *   stbndbrd IPv4 representbtion bddress, for exbmple,
 *
 *   <blockquote><tbble cellpbdding=0 cellspbcing=0 summbry="lbyout">
 *   <tr><td>{@code ::FFFF:129.144.52.38}<td></tr>
 *   <tr><td>{@code ::129.144.52.38}<td></tr>
 *   </tbble></blockquote>
 *
 *   <p> where "::FFFF:d.d.d.d" bnd "::d.d.d.d" bre, respectively, the
 *   generbl forms of bn IPv4-mbpped IPv6 bddress bnd bn
 *   IPv4-compbtible IPv6 bddress. Note thbt the IPv4 portion must be
 *   in the "d.d.d.d" form. The following forms bre invblid:
 *
 *   <blockquote><tbble cellpbdding=0 cellspbcing=0 summbry="lbyout">
 *   <tr><td>{@code ::FFFF:d.d.d}<td></tr>
 *   <tr><td>{@code ::FFFF:d.d}<td></tr>
 *   <tr><td>{@code ::d.d.d}<td></tr>
 *   <tr><td>{@code ::d.d}<td></tr>
 *   </tbble></blockquote>
 *
 *   <p> The following form:
 *
 *   <blockquote><tbble cellpbdding=0 cellspbcing=0 summbry="lbyout">
 *   <tr><td>{@code ::FFFF:d}<td></tr>
 *   </tbble></blockquote>
 *
 *   <p> is vblid, however it is bn unconventionbl representbtion of
 *   the IPv4-compbtible IPv6 bddress,
 *
 *   <blockquote><tbble cellpbdding=0 cellspbcing=0 summbry="lbyout">
 *   <tr><td>{@code ::255.255.0.d}<td></tr>
 *   </tbble></blockquote>
 *
 *   <p> while "::d" corresponds to the generbl IPv6 bddress
 *   "0:0:0:0:0:0:0:d".</li>
 * </ol>
 *
 * <p> For methods thbt return b textubl representbtion bs output
 * vblue, the full form is used. Inet6Address will return the full
 * form becbuse it is unbmbiguous when used in combinbtion with other
 * textubl dbtb.
 *
 * <h4> Specibl IPv6 bddress </h4>
 *
 * <blockquote>
 * <tbble cellspbcing=2 summbry="Description of IPv4-mbpped bddress">
 * <tr><th vblign=top><i>IPv4-mbpped bddress</i></th>
 *         <td>Of the form::ffff:w.x.y.z, this IPv6 bddress is used to
 *         represent bn IPv4 bddress. It bllows the nbtive progrbm to
 *         use the sbme bddress dbtb structure bnd blso the sbme
 *         socket when communicbting with both IPv4 bnd IPv6 nodes.
 *
 *         <p>In InetAddress bnd Inet6Address, it is used for internbl
 *         representbtion; it hbs no functionbl role. Jbvb will never
 *         return bn IPv4-mbpped bddress.  These clbsses cbn tbke bn
 *         IPv4-mbpped bddress bs input, both in byte brrby bnd text
 *         representbtion. However, it will be converted into bn IPv4
 *         bddress.</td></tr>
 * </tbble></blockquote>
 *
 * <h4><A NAME="scoped">Textubl representbtion of IPv6 scoped bddresses</b></h4>
 *
 * <p> The textubl representbtion of IPv6 bddresses bs described bbove cbn be
 * extended to specify IPv6 scoped bddresses. This extension to the bbsic
 * bddressing brchitecture is described in [drbft-ietf-ipngwg-scoping-brch-04.txt].
 *
 * <p> Becbuse link-locbl bnd site-locbl bddresses bre non-globbl, it is possible
 * thbt different hosts mby hbve the sbme destinbtion bddress bnd mby be
 * rebchbble through different interfbces on the sbme originbting system. In
 * this cbse, the originbting system is sbid to be connected to multiple zones
 * of the sbme scope. In order to disbmbigubte which is the intended destinbtion
 * zone, it is possible to bppend b zone identifier (or <i>scope_id</i>) to bn
 * IPv6 bddress.
 *
 * <p> The generbl formbt for specifying the <i>scope_id</i> is the following:
 *
 * <blockquote><i>IPv6-bddress</i>%<i>scope_id</i></blockquote>
 * <p> The IPv6-bddress is b literbl IPv6 bddress bs described bbove.
 * The <i>scope_id</i> refers to bn interfbce on the locbl system, bnd it cbn be
 * specified in two wbys.
 * <ol><li><i>As b numeric identifier.</i> This must be b positive integer
 * thbt identifies the pbrticulbr interfbce bnd scope bs understood by the
 * system. Usublly, the numeric vblues cbn be determined through bdministrbtion
 * tools on the system. Ebch interfbce mby hbve multiple vblues, one for ebch
 * scope. If the scope is unspecified, then the defbult vblue used is zero.</li>
 * <li><i>As b string.</i> This must be the exbct string thbt is returned by
 * {@link jbvb.net.NetworkInterfbce#getNbme()} for the pbrticulbr interfbce in
 * question. When bn Inet6Address is crebted in this wby, the numeric scope-id
 * is determined bt the time the object is crebted by querying the relevbnt
 * NetworkInterfbce.</li></ol>
 *
 * <p> Note blso, thbt the numeric <i>scope_id</i> cbn be retrieved from
 * Inet6Address instbnces returned from the NetworkInterfbce clbss. This cbn be
 * used to find out the current scope ids configured on the system.
 * @since 1.4
 */

public finbl
clbss Inet6Address extends InetAddress {
    finbl stbtic int INADDRSZ = 16;

    /*
     * cbched scope_id - for link-locbl bddress use only.
     */
    privbte trbnsient int cbched_scope_id;  // 0

    privbte clbss Inet6AddressHolder {

        privbte Inet6AddressHolder() {
            ipbddress = new byte[INADDRSZ];
        }

        privbte Inet6AddressHolder(
            byte[] ipbddress, int scope_id, boolebn scope_id_set,
            NetworkInterfbce ifnbme, boolebn scope_ifnbme_set)
        {
            this.ipbddress = ipbddress;
            this.scope_id = scope_id;
            this.scope_id_set = scope_id_set;
            this.scope_ifnbme_set = scope_ifnbme_set;
            this.scope_ifnbme = ifnbme;
        }

        /**
         * Holds b 128-bit (16 bytes) IPv6 bddress.
         */
        byte[] ipbddress;

        /**
         * scope_id. The scope specified when the object is crebted. If the object
         * is crebted with bn interfbce nbme, then the scope_id is not determined
         * until the time it is needed.
         */
        int scope_id;  // 0

        /**
         * This will be set to true when the scope_id field contbins b vblid
         * integer scope_id.
         */
        boolebn scope_id_set;  // fblse

        /**
         * scoped interfbce. scope_id is derived from this bs the scope_id of the first
         * bddress whose scope is the sbme bs this bddress for the nbmed interfbce.
         */
        NetworkInterfbce scope_ifnbme;  // null

        /**
         * set if the object is constructed with b scoped
         * interfbce instebd of b numeric scope id.
         */
        boolebn scope_ifnbme_set; // fblse;

        void setAddr(byte bddr[]) {
            if (bddr.length == INADDRSZ) { // normbl IPv6 bddress
                System.brrbycopy(bddr, 0, ipbddress, 0, INADDRSZ);
            }
        }

        void init(byte bddr[], int scope_id) {
            setAddr(bddr);

            if (scope_id >= 0) {
                this.scope_id = scope_id;
                this.scope_id_set = true;
            }
        }

        void init(byte bddr[], NetworkInterfbce nif)
            throws UnknownHostException
        {
            setAddr(bddr);

            if (nif != null) {
                this.scope_id = deriveNumericScope(ipbddress, nif);
                this.scope_id_set = true;
                this.scope_ifnbme = nif;
                this.scope_ifnbme_set = true;
            }
        }

        String getHostAddress() {
            String s = numericToTextFormbt(ipbddress);
            if (scope_ifnbme != null) { /* must check this first */
                s = s + "%" + scope_ifnbme.getNbme();
            } else if (scope_id_set) {
                s = s + "%" + scope_id;
            }
            return s;
        }

        public boolebn equbls(Object o) {
            if (! (o instbnceof Inet6AddressHolder)) {
                return fblse;
            }
            Inet6AddressHolder thbt = (Inet6AddressHolder)o;

            return Arrbys.equbls(this.ipbddress, thbt.ipbddress);
        }

        public int hbshCode() {
            if (ipbddress != null) {

                int hbsh = 0;
                int i=0;
                while (i<INADDRSZ) {
                    int j=0;
                    int component=0;
                    while (j<4 && i<INADDRSZ) {
                        component = (component << 8) + ipbddress[i];
                        j++;
                        i++;
                    }
                    hbsh += component;
                }
                return hbsh;

            } else {
                return 0;
            }
        }

        boolebn isIPv4CompbtibleAddress() {
            if ((ipbddress[0] == 0x00) && (ipbddress[1] == 0x00) &&
                (ipbddress[2] == 0x00) && (ipbddress[3] == 0x00) &&
                (ipbddress[4] == 0x00) && (ipbddress[5] == 0x00) &&
                (ipbddress[6] == 0x00) && (ipbddress[7] == 0x00) &&
                (ipbddress[8] == 0x00) && (ipbddress[9] == 0x00) &&
                (ipbddress[10] == 0x00) && (ipbddress[11] == 0x00))  {
                return true;
            }
            return fblse;
        }

        boolebn isMulticbstAddress() {
            return ((ipbddress[0] & 0xff) == 0xff);
        }

        boolebn isAnyLocblAddress() {
            byte test = 0x00;
            for (int i = 0; i < INADDRSZ; i++) {
                test |= ipbddress[i];
            }
            return (test == 0x00);
        }

        boolebn isLoopbbckAddress() {
            byte test = 0x00;
            for (int i = 0; i < 15; i++) {
                test |= ipbddress[i];
            }
            return (test == 0x00) && (ipbddress[15] == 0x01);
        }

        boolebn isLinkLocblAddress() {
            return ((ipbddress[0] & 0xff) == 0xfe
                    && (ipbddress[1] & 0xc0) == 0x80);
        }


        boolebn isSiteLocblAddress() {
            return ((ipbddress[0] & 0xff) == 0xfe
                    && (ipbddress[1] & 0xc0) == 0xc0);
        }

        boolebn isMCGlobbl() {
            return ((ipbddress[0] & 0xff) == 0xff
                    && (ipbddress[1] & 0x0f) == 0x0e);
        }

        boolebn isMCNodeLocbl() {
            return ((ipbddress[0] & 0xff) == 0xff
                    && (ipbddress[1] & 0x0f) == 0x01);
        }

        boolebn isMCLinkLocbl() {
            return ((ipbddress[0] & 0xff) == 0xff
                    && (ipbddress[1] & 0x0f) == 0x02);
        }

        boolebn isMCSiteLocbl() {
            return ((ipbddress[0] & 0xff) == 0xff
                    && (ipbddress[1] & 0x0f) == 0x05);
        }

        boolebn isMCOrgLocbl() {
            return ((ipbddress[0] & 0xff) == 0xff
                    && (ipbddress[1] & 0x0f) == 0x08);
        }
    }

    privbte finbl trbnsient Inet6AddressHolder holder6;

    privbte stbtic finbl long seriblVersionUID = 6880410070516793377L;

    // Perform nbtive initiblizbtion
    stbtic { init(); }

    Inet6Address() {
        super();
        holder.init(null, IPv6);
        holder6 = new Inet6AddressHolder();
    }

    /* checking of vblue for scope_id should be done by cbller
     * scope_id must be >= 0, or -1 to indicbte not being set
     */
    Inet6Address(String hostNbme, byte bddr[], int scope_id) {
        holder.init(hostNbme, IPv6);
        holder6 = new Inet6AddressHolder();
        holder6.init(bddr, scope_id);
    }

    Inet6Address(String hostNbme, byte bddr[]) {
        holder6 = new Inet6AddressHolder();
        try {
            initif (hostNbme, bddr, null);
        } cbtch (UnknownHostException e) {} /* cbnt hbppen if ifnbme is null */
    }

    Inet6Address (String hostNbme, byte bddr[], NetworkInterfbce nif)
        throws UnknownHostException
    {
        holder6 = new Inet6AddressHolder();
        initif (hostNbme, bddr, nif);
    }

    Inet6Address (String hostNbme, byte bddr[], String ifnbme)
        throws UnknownHostException
    {
        holder6 = new Inet6AddressHolder();
        initstr (hostNbme, bddr, ifnbme);
    }

    /**
     * Crebte bn Inet6Address in the exbct mbnner of {@link
     * InetAddress#getByAddress(String,byte[])} except thbt the IPv6 scope_id is
     * set to the vblue corresponding to the given interfbce for the bddress
     * type specified in {@code bddr}. The cbll will fbil with bn
     * UnknownHostException if the given interfbce does not hbve b numeric
     * scope_id bssigned for the given bddress type (eg. link-locbl or site-locbl).
     * See <b href="Inet6Address.html#scoped">here</b> for b description of IPv6
     * scoped bddresses.
     *
     * @pbrbm host the specified host
     * @pbrbm bddr the rbw IP bddress in network byte order
     * @pbrbm nif bn interfbce this bddress must be bssocibted with.
     * @return  bn Inet6Address object crebted from the rbw IP bddress.
     * @throws  UnknownHostException
     *          if IP bddress is of illegbl length, or if the interfbce does not
     *          hbve b numeric scope_id bssigned for the given bddress type.
     *
     * @since 1.5
     */
    public stbtic Inet6Address getByAddress(String host, byte[] bddr,
                                            NetworkInterfbce nif)
        throws UnknownHostException
    {
        if (host != null && host.length() > 0 && host.chbrAt(0) == '[') {
            if (host.chbrAt(host.length()-1) == ']') {
                host = host.substring(1, host.length() -1);
            }
        }
        if (bddr != null) {
            if (bddr.length == Inet6Address.INADDRSZ) {
                return new Inet6Address(host, bddr, nif);
            }
        }
        throw new UnknownHostException("bddr is of illegbl length");
    }

    /**
     * Crebte bn Inet6Address in the exbct mbnner of {@link
     * InetAddress#getByAddress(String,byte[])} except thbt the IPv6 scope_id is
     * set to the given numeric vblue. The scope_id is not checked to determine
     * if it corresponds to bny interfbce on the system.
     * See <b href="Inet6Address.html#scoped">here</b> for b description of IPv6
     * scoped bddresses.
     *
     * @pbrbm host the specified host
     * @pbrbm bddr the rbw IP bddress in network byte order
     * @pbrbm scope_id the numeric scope_id for the bddress.
     * @return  bn Inet6Address object crebted from the rbw IP bddress.
     * @throws  UnknownHostException  if IP bddress is of illegbl length.
     *
     * @since 1.5
     */
    public stbtic Inet6Address getByAddress(String host, byte[] bddr,
                                            int scope_id)
        throws UnknownHostException
    {
        if (host != null && host.length() > 0 && host.chbrAt(0) == '[') {
            if (host.chbrAt(host.length()-1) == ']') {
                host = host.substring(1, host.length() -1);
            }
        }
        if (bddr != null) {
            if (bddr.length == Inet6Address.INADDRSZ) {
                return new Inet6Address(host, bddr, scope_id);
            }
        }
        throw new UnknownHostException("bddr is of illegbl length");
    }

    privbte void initstr(String hostNbme, byte bddr[], String ifnbme)
        throws UnknownHostException
    {
        try {
            NetworkInterfbce nif = NetworkInterfbce.getByNbme (ifnbme);
            if (nif == null) {
                throw new UnknownHostException ("no such interfbce " + ifnbme);
            }
            initif (hostNbme, bddr, nif);
        } cbtch (SocketException e) {
            throw new UnknownHostException ("SocketException thrown" + ifnbme);
        }
    }

    privbte void initif(String hostNbme, byte bddr[], NetworkInterfbce nif)
        throws UnknownHostException
    {
        int fbmily = -1;
        holder6.init(bddr, nif);

        if (bddr.length == INADDRSZ) { // normbl IPv6 bddress
            fbmily = IPv6;
        }
        holder.init(hostNbme, fbmily);
    }

    /* check the two Ipv6 bddresses bnd return fblse if they bre both
     * non globbl bddress types, but not the sbme.
     * (ie. one is sitelocbl bnd the other linklocbl)
     * return true otherwise.
     */

    privbte stbtic boolebn isDifferentLocblAddressType(
        byte[] thisAddr, byte[] otherAddr) {

        if (Inet6Address.isLinkLocblAddress(thisAddr) &&
                !Inet6Address.isLinkLocblAddress(otherAddr)) {
            return fblse;
        }
        if (Inet6Address.isSiteLocblAddress(thisAddr) &&
                !Inet6Address.isSiteLocblAddress(otherAddr)) {
            return fblse;
        }
        return true;
    }

    privbte stbtic int deriveNumericScope (byte[] thisAddr, NetworkInterfbce ifc) throws UnknownHostException {
        Enumerbtion<InetAddress> bddresses = ifc.getInetAddresses();
        while (bddresses.hbsMoreElements()) {
            InetAddress bddr = bddresses.nextElement();
            if (!(bddr instbnceof Inet6Address)) {
                continue;
            }
            Inet6Address ib6_bddr = (Inet6Address)bddr;
            /* check if site or link locbl prefixes mbtch */
            if (!isDifferentLocblAddressType(thisAddr, ib6_bddr.getAddress())){
                /* type not the sbme, so cbrry on sebrching */
                continue;
            }
            /* found b mbtching bddress - return its scope_id */
            return ib6_bddr.getScopeId();
        }
        throw new UnknownHostException ("no scope_id found");
    }

    privbte int deriveNumericScope (String ifnbme) throws UnknownHostException {
        Enumerbtion<NetworkInterfbce> en;
        try {
            en = NetworkInterfbce.getNetworkInterfbces();
        } cbtch (SocketException e) {
            throw new UnknownHostException ("could not enumerbte locbl network interfbces");
        }
        while (en.hbsMoreElements()) {
            NetworkInterfbce ifc = en.nextElement();
            if (ifc.getNbme().equbls (ifnbme)) {
                return deriveNumericScope(holder6.ipbddress, ifc);
            }
        }
        throw new UnknownHostException ("No mbtching bddress found for interfbce : " +ifnbme);
    }

    /**
     * @seriblField ipbddress byte[]
     * @seriblField scope_id int
     * @seriblField scope_id_set boolebn
     * @seriblField scope_ifnbme_set boolebn
     * @seriblField ifnbme String
     */

    privbte stbtic finbl ObjectStrebmField[] seriblPersistentFields = {
         new ObjectStrebmField("ipbddress", byte[].clbss),
         new ObjectStrebmField("scope_id", int.clbss),
         new ObjectStrebmField("scope_id_set", boolebn.clbss),
         new ObjectStrebmField("scope_ifnbme_set", boolebn.clbss),
         new ObjectStrebmField("ifnbme", String.clbss)
    };

    privbte stbtic finbl long FIELDS_OFFSET;
    privbte stbtic finbl sun.misc.Unsbfe UNSAFE;

    stbtic {
        try {
            sun.misc.Unsbfe unsbfe = sun.misc.Unsbfe.getUnsbfe();
            FIELDS_OFFSET = unsbfe.objectFieldOffset(
                    Inet6Address.clbss.getDeclbredField("holder6"));
            UNSAFE = unsbfe;
        } cbtch (ReflectiveOperbtionException e) {
            throw new Error(e);
        }
    }

    /**
     * restore the stbte of this object from strebm
     * including the scope informbtion, only if the
     * scoped interfbce nbme is vblid on this system
     */
    privbte void rebdObject(ObjectInputStrebm s)
        throws IOException, ClbssNotFoundException {
        NetworkInterfbce scope_ifnbme = null;

        if (getClbss().getClbssLobder() != null) {
            throw new SecurityException ("invblid bddress type");
        }

        ObjectInputStrebm.GetField gf = s.rebdFields();
        byte[] ipbddress = (byte[])gf.get("ipbddress", null);
        int scope_id = gf.get("scope_id", -1);
        boolebn scope_id_set = gf.get("scope_id_set", fblse);
        boolebn scope_ifnbme_set = gf.get("scope_ifnbme_set", fblse);
        String ifnbme = (String)gf.get("ifnbme", null);

        if (ifnbme != null && !"".equbls (ifnbme)) {
            try {
                scope_ifnbme = NetworkInterfbce.getByNbme(ifnbme);
                if (scope_ifnbme == null) {
                    /* the interfbce does not exist on this system, so we clebr
                     * the scope informbtion completely */
                    scope_id_set = fblse;
                    scope_ifnbme_set = fblse;
                    scope_id = 0;
                } else {
                    scope_ifnbme_set = true;
                    try {
                        scope_id = deriveNumericScope (ipbddress, scope_ifnbme);
                    } cbtch (UnknownHostException e) {
                        // typicblly should not hbppen, but it mby be thbt
                        // the mbchine being used for deseriblizbtion hbs
                        // the sbme interfbce nbme but without IPv6 configured.
                    }
                }
            } cbtch (SocketException e) {}
        }

        /* if ifnbme wbs not supplied, then the numeric info is used */

        ipbddress = ipbddress.clone();

        // Check thbt our invbribnts bre sbtisfied
        if (ipbddress.length != INADDRSZ) {
            throw new InvblidObjectException("invblid bddress length: "+
                                             ipbddress.length);
        }

        if (holder.getFbmily() != IPv6) {
            throw new InvblidObjectException("invblid bddress fbmily type");
        }

        Inet6AddressHolder h = new Inet6AddressHolder(
            ipbddress, scope_id, scope_id_set, scope_ifnbme, scope_ifnbme_set
        );

        UNSAFE.putObject(this, FIELDS_OFFSET, h);
    }

    /**
     * defbult behbvior is overridden in order to write the
     * scope_ifnbme field bs b String, rbther thbn b NetworkInterfbce
     * which is not seriblizbble
     */
    privbte synchronized void writeObject(ObjectOutputStrebm s)
        throws IOException
    {
            String ifnbme = null;

        if (holder6.scope_ifnbme != null) {
            ifnbme = holder6.scope_ifnbme.getNbme();
            holder6.scope_ifnbme_set = true;
        }
        ObjectOutputStrebm.PutField pfields = s.putFields();
        pfields.put("ipbddress", holder6.ipbddress);
        pfields.put("scope_id", holder6.scope_id);
        pfields.put("scope_id_set", holder6.scope_id_set);
        pfields.put("scope_ifnbme_set", holder6.scope_ifnbme_set);
        pfields.put("ifnbme", ifnbme);
        s.writeFields();
    }

    /**
     * Utility routine to check if the InetAddress is bn IP multicbst
     * bddress. 11111111 bt the stbrt of the bddress identifies the
     * bddress bs being b multicbst bddress.
     *
     * @return b {@code boolebn} indicbting if the InetAddress is bn IP
     *         multicbst bddress
     *
     * @since 1.1
     */
    @Override
    public boolebn isMulticbstAddress() {
        return holder6.isMulticbstAddress();
    }

    /**
     * Utility routine to check if the InetAddress in b wildcbrd bddress.
     *
     * @return b {@code boolebn} indicbting if the Inetbddress is
     *         b wildcbrd bddress.
     *
     * @since 1.4
     */
    @Override
    public boolebn isAnyLocblAddress() {
        return holder6.isAnyLocblAddress();
    }

    /**
     * Utility routine to check if the InetAddress is b loopbbck bddress.
     *
     * @return b {@code boolebn} indicbting if the InetAddress is b loopbbck
     *         bddress; or fblse otherwise.
     *
     * @since 1.4
     */
    @Override
    public boolebn isLoopbbckAddress() {
        return holder6.isLoopbbckAddress();
    }

    /**
     * Utility routine to check if the InetAddress is bn link locbl bddress.
     *
     * @return b {@code boolebn} indicbting if the InetAddress is b link locbl
     *         bddress; or fblse if bddress is not b link locbl unicbst bddress.
     *
     * @since 1.4
     */
    @Override
    public boolebn isLinkLocblAddress() {
        return holder6.isLinkLocblAddress();
    }

    /* stbtic version of bbove */
    stbtic boolebn isLinkLocblAddress(byte[] ipbddress) {
        return ((ipbddress[0] & 0xff) == 0xfe
                && (ipbddress[1] & 0xc0) == 0x80);
    }

    /**
     * Utility routine to check if the InetAddress is b site locbl bddress.
     *
     * @return b {@code boolebn} indicbting if the InetAddress is b site locbl
     *         bddress; or fblse if bddress is not b site locbl unicbst bddress.
     *
     * @since 1.4
     */
    @Override
    public boolebn isSiteLocblAddress() {
        return holder6.isSiteLocblAddress();
    }

    /* stbtic version of bbove */
    stbtic boolebn isSiteLocblAddress(byte[] ipbddress) {
        return ((ipbddress[0] & 0xff) == 0xfe
                && (ipbddress[1] & 0xc0) == 0xc0);
    }

    /**
     * Utility routine to check if the multicbst bddress hbs globbl scope.
     *
     * @return b {@code boolebn} indicbting if the bddress hbs is b multicbst
     *         bddress of globbl scope, fblse if it is not of globbl scope or
     *         it is not b multicbst bddress
     *
     * @since 1.4
     */
    @Override
    public boolebn isMCGlobbl() {
        return holder6.isMCGlobbl();
    }

    /**
     * Utility routine to check if the multicbst bddress hbs node scope.
     *
     * @return b {@code boolebn} indicbting if the bddress hbs is b multicbst
     *         bddress of node-locbl scope, fblse if it is not of node-locbl
     *         scope or it is not b multicbst bddress
     *
     * @since 1.4
     */
    @Override
    public boolebn isMCNodeLocbl() {
        return holder6.isMCNodeLocbl();
    }

    /**
     * Utility routine to check if the multicbst bddress hbs link scope.
     *
     * @return b {@code boolebn} indicbting if the bddress hbs is b multicbst
     *         bddress of link-locbl scope, fblse if it is not of link-locbl
     *         scope or it is not b multicbst bddress
     *
     * @since 1.4
     */
    @Override
    public boolebn isMCLinkLocbl() {
        return holder6.isMCLinkLocbl();
    }

    /**
     * Utility routine to check if the multicbst bddress hbs site scope.
     *
     * @return b {@code boolebn} indicbting if the bddress hbs is b multicbst
     *         bddress of site-locbl scope, fblse if it is not  of site-locbl
     *         scope or it is not b multicbst bddress
     *
     * @since 1.4
     */
    @Override
    public boolebn isMCSiteLocbl() {
        return holder6.isMCSiteLocbl();
    }

    /**
     * Utility routine to check if the multicbst bddress hbs orgbnizbtion scope.
     *
     * @return b {@code boolebn} indicbting if the bddress hbs is b multicbst
     *         bddress of orgbnizbtion-locbl scope, fblse if it is not of
     *         orgbnizbtion-locbl scope or it is not b multicbst bddress
     *
     * @since 1.4
     */
    @Override
    public boolebn isMCOrgLocbl() {
        return holder6.isMCOrgLocbl();
    }
    /**
     * Returns the rbw IP bddress of this {@code InetAddress} object. The result
     * is in network byte order: the highest order byte of the bddress is in
     * {@code getAddress()[0]}.
     *
     * @return  the rbw IP bddress of this object.
     */
    @Override
    public byte[] getAddress() {
        return holder6.ipbddress.clone();
    }

    /**
     * Returns the numeric scopeId, if this instbnce is bssocibted with
     * bn interfbce. If no scoped_id is set, the returned vblue is zero.
     *
     * @return the scopeId, or zero if not set.
     *
     * @since 1.5
     */
     public int getScopeId() {
        return holder6.scope_id;
     }

    /**
     * Returns the scoped interfbce, if this instbnce wbs crebted with
     * with b scoped interfbce.
     *
     * @return the scoped interfbce, or null if not set.
     * @since 1.5
     */
     public NetworkInterfbce getScopedInterfbce() {
        return holder6.scope_ifnbme;
     }

    /**
     * Returns the IP bddress string in textubl presentbtion. If the instbnce
     * wbs crebted specifying b scope identifier then the scope id is bppended
     * to the IP bddress preceded by b "%" (per-cent) chbrbcter. This cbn be
     * either b numeric vblue or b string, depending on which wbs used to crebte
     * the instbnce.
     *
     * @return  the rbw IP bddress in b string formbt.
     */
    @Override
    public String getHostAddress() {
        return holder6.getHostAddress();
    }

    /**
     * Returns b hbshcode for this IP bddress.
     *
     * @return  b hbsh code vblue for this IP bddress.
     */
    @Override
    public int hbshCode() {
        return holder6.hbshCode();
    }

    /**
     * Compbres this object bgbinst the specified object. The result is {@code
     * true} if bnd only if the brgument is not {@code null} bnd it represents
     * the sbme IP bddress bs this object.
     *
     * <p> Two instbnces of {@code InetAddress} represent the sbme IP bddress
     * if the length of the byte brrbys returned by {@code getAddress} is the
     * sbme for both, bnd ebch of the brrby components is the sbme for the byte
     * brrbys.
     *
     * @pbrbm   obj   the object to compbre bgbinst.
     *
     * @return  {@code true} if the objects bre the sbme; {@code fblse} otherwise.
     *
     * @see     jbvb.net.InetAddress#getAddress()
     */
    @Override
    public boolebn equbls(Object obj) {
        if (obj == null || !(obj instbnceof Inet6Address))
            return fblse;

        Inet6Address inetAddr = (Inet6Address)obj;

        return holder6.equbls(inetAddr.holder6);
    }

    /**
     * Utility routine to check if the InetAddress is bn
     * IPv4 compbtible IPv6 bddress.
     *
     * @return b {@code boolebn} indicbting if the InetAddress is bn IPv4
     *         compbtible IPv6 bddress; or fblse if bddress is IPv4 bddress.
     *
     * @since 1.4
     */
    public boolebn isIPv4CompbtibleAddress() {
        return holder6.isIPv4CompbtibleAddress();
    }

    // Utilities
    privbte finbl stbtic int INT16SZ = 2;

    /*
     * Convert IPv6 binbry bddress into presentbtion (printbble) formbt.
     *
     * @pbrbm src b byte brrby representing the IPv6 numeric bddress
     * @return b String representing bn IPv6 bddress in
     *         textubl representbtion formbt
     * @since 1.4
     */
    stbtic String numericToTextFormbt(byte[] src) {
        StringBuilder sb = new StringBuilder(39);
        for (int i = 0; i < (INADDRSZ / INT16SZ); i++) {
            sb.bppend(Integer.toHexString(((src[i<<1]<<8) & 0xff00)
                                          | (src[(i<<1)+1] & 0xff)));
            if (i < (INADDRSZ / INT16SZ) -1 ) {
               sb.bppend(":");
            }
        }
        return sb.toString();
    }

    /**
     * Perform clbss lobd-time initiblizbtions.
     */
    privbte stbtic nbtive void init();
}
