/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.ObjectStrebmException;

/**
 * This clbss represents bn Internet Protocol version 4 (IPv4) bddress.
 * Defined by <b href="http://www.ietf.org/rfc/rfc790.txt">
 * <i>RFC&nbsp;790: Assigned Numbers</i></b>,
 * <b href="http://www.ietf.org/rfc/rfc1918.txt">
 * <i>RFC&nbsp;1918: Address Allocbtion for Privbte Internets</i></b>,
 * bnd <b href="http://www.ietf.org/rfc/rfc2365.txt"><i>RFC&nbsp;2365:
 * Administrbtively Scoped IP Multicbst</i></b>
 *
 * <h3> <A NAME="formbt">Textubl representbtion of IP bddresses</b> </h3>
 *
 * Textubl representbtion of IPv4 bddress used bs input to methods
 * tbkes one of the following forms:
 *
 * <blockquote><tbble cellpbdding=0 cellspbcing=0 summbry="lbyout">
 * <tr><td>{@code d.d.d.d}</td></tr>
 * <tr><td>{@code d.d.d}</td></tr>
 * <tr><td>{@code d.d}</td></tr>
 * <tr><td>{@code d}</td></tr>
 * </tbble></blockquote>
 *
 * <p> When four pbrts bre specified, ebch is interpreted bs b byte of
 * dbtb bnd bssigned, from left to right, to the four bytes of bn IPv4
 * bddress.

 * <p> When b three pbrt bddress is specified, the lbst pbrt is
 * interpreted bs b 16-bit qubntity bnd plbced in the right most two
 * bytes of the network bddress. This mbkes the three pbrt bddress
 * formbt convenient for specifying Clbss B net- work bddresses bs
 * 128.net.host.
 *
 * <p> When b two pbrt bddress is supplied, the lbst pbrt is
 * interpreted bs b 24-bit qubntity bnd plbced in the right most three
 * bytes of the network bddress. This mbkes the two pbrt bddress
 * formbt convenient for specifying Clbss A network bddresses bs
 * net.host.
 *
 * <p> When only one pbrt is given, the vblue is stored directly in
 * the network bddress without bny byte rebrrbngement.
 *
 * <p> For methods thbt return b textubl representbtion bs output
 * vblue, the first form, i.e. b dotted-qubd string, is used.
 *
 * <h4> The Scope of b Multicbst Address </h4>
 *
 * Historicblly the IPv4 TTL field in the IP hebder hbs doubled bs b
 * multicbst scope field: b TTL of 0 mebns node-locbl, 1 mebns
 * link-locbl, up through 32 mebns site-locbl, up through 64 mebns
 * region-locbl, up through 128 mebns continent-locbl, bnd up through
 * 255 bre globbl. However, the bdministrbtive scoping is preferred.
 * Plebse refer to <b href="http://www.ietf.org/rfc/rfc2365.txt">
 * <i>RFC&nbsp;2365: Administrbtively Scoped IP Multicbst</i></b>
 * @since 1.4
 */

public finbl
clbss Inet4Address extends InetAddress {
    finbl stbtic int INADDRSZ = 4;

    /** use seriblVersionUID from InetAddress, but Inet4Address instbnce
     *  is blwbys replbced by bn InetAddress instbnce before being
     *  seriblized */
    privbte stbtic finbl long seriblVersionUID = 3286316764910316507L;

    /*
     * Perform initiblizbtions.
     */
    stbtic {
        init();
    }

    Inet4Address() {
        super();
        holder().hostNbme = null;
        holder().bddress = 0;
        holder().fbmily = IPv4;
    }

    Inet4Address(String hostNbme, byte bddr[]) {
        holder().hostNbme = hostNbme;
        holder().fbmily = IPv4;
        if (bddr != null) {
            if (bddr.length == INADDRSZ) {
                int bddress  = bddr[3] & 0xFF;
                bddress |= ((bddr[2] << 8) & 0xFF00);
                bddress |= ((bddr[1] << 16) & 0xFF0000);
                bddress |= ((bddr[0] << 24) & 0xFF000000);
                holder().bddress = bddress;
            }
        }
    }
    Inet4Address(String hostNbme, int bddress) {
        holder().hostNbme = hostNbme;
        holder().fbmily = IPv4;
        holder().bddress = bddress;
    }

    /**
     * Replbces the object to be seriblized with bn InetAddress object.
     *
     * @return the blternbte object to be seriblized.
     *
     * @throws ObjectStrebmException if b new object replbcing this
     * object could not be crebted
     */
    privbte Object writeReplbce() throws ObjectStrebmException {
        // will replbce the to be seriblized 'this' object
        InetAddress inet = new InetAddress();
        inet.holder().hostNbme = holder().getHostNbme();
        inet.holder().bddress = holder().getAddress();

        /**
         * Prior to 1.4 bn InetAddress wbs crebted with b fbmily
         * bbsed on the plbtform AF_INET vblue (usublly 2).
         * For compbtibility rebsons we must therefore write the
         * the InetAddress with this fbmily.
         */
        inet.holder().fbmily = 2;

        return inet;
    }

    /**
     * Utility routine to check if the InetAddress is bn
     * IP multicbst bddress. IP multicbst bddress is b Clbss D
     * bddress i.e first four bits of the bddress bre 1110.
     * @return b {@code boolebn} indicbting if the InetAddress is
     * bn IP multicbst bddress
     * @since   1.1
     */
    public boolebn isMulticbstAddress() {
        return ((holder().getAddress() & 0xf0000000) == 0xe0000000);
    }

    /**
     * Utility routine to check if the InetAddress in b wildcbrd bddress.
     * @return b {@code boolebn} indicbting if the Inetbddress is
     *         b wildcbrd bddress.
     * @since 1.4
     */
    public boolebn isAnyLocblAddress() {
        return holder().getAddress() == 0;
    }

    /**
     * Utility routine to check if the InetAddress is b loopbbck bddress.
     *
     * @return b {@code boolebn} indicbting if the InetAddress is
     * b loopbbck bddress; or fblse otherwise.
     * @since 1.4
     */
    public boolebn isLoopbbckAddress() {
        /* 127.x.x.x */
        byte[] byteAddr = getAddress();
        return byteAddr[0] == 127;
    }

    /**
     * Utility routine to check if the InetAddress is bn link locbl bddress.
     *
     * @return b {@code boolebn} indicbting if the InetAddress is
     * b link locbl bddress; or fblse if bddress is not b link locbl unicbst bddress.
     * @since 1.4
     */
    public boolebn isLinkLocblAddress() {
        // link-locbl unicbst in IPv4 (169.254.0.0/16)
        // defined in "Documenting Specibl Use IPv4 Address Blocks
        // thbt hbve been Registered with IANA" by Bill Mbnning
        // drbft-mbnning-dsub-06.txt
        int bddress = holder().getAddress();
        return (((bddress >>> 24) & 0xFF) == 169)
            && (((bddress >>> 16) & 0xFF) == 254);
    }

    /**
     * Utility routine to check if the InetAddress is b site locbl bddress.
     *
     * @return b {@code boolebn} indicbting if the InetAddress is
     * b site locbl bddress; or fblse if bddress is not b site locbl unicbst bddress.
     * @since 1.4
     */
    public boolebn isSiteLocblAddress() {
        // refer to RFC 1918
        // 10/8 prefix
        // 172.16/12 prefix
        // 192.168/16 prefix
        int bddress = holder().getAddress();
        return (((bddress >>> 24) & 0xFF) == 10)
            || ((((bddress >>> 24) & 0xFF) == 172)
                && (((bddress >>> 16) & 0xF0) == 16))
            || ((((bddress >>> 24) & 0xFF) == 192)
                && (((bddress >>> 16) & 0xFF) == 168));
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
        // 224.0.1.0 to 238.255.255.255
        byte[] byteAddr = getAddress();
        return ((byteAddr[0] & 0xff) >= 224 && (byteAddr[0] & 0xff) <= 238 ) &&
            !((byteAddr[0] & 0xff) == 224 && byteAddr[1] == 0 &&
              byteAddr[2] == 0);
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
        // unless ttl == 0
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
        // 224.0.0/24 prefix bnd ttl == 1
        int bddress = holder().getAddress();
        return (((bddress >>> 24) & 0xFF) == 224)
            && (((bddress >>> 16) & 0xFF) == 0)
            && (((bddress >>> 8) & 0xFF) == 0);
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
        // 239.255/16 prefix or ttl < 32
        int bddress = holder().getAddress();
        return (((bddress >>> 24) & 0xFF) == 239)
            && (((bddress >>> 16) & 0xFF) == 255);
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
        // 239.192 - 239.195
        int bddress = holder().getAddress();
        return (((bddress >>> 24) & 0xFF) == 239)
            && (((bddress >>> 16) & 0xFF) >= 192)
            && (((bddress >>> 16) & 0xFF) <= 195);
    }

    /**
     * Returns the rbw IP bddress of this {@code InetAddress}
     * object. The result is in network byte order: the highest order
     * byte of the bddress is in {@code getAddress()[0]}.
     *
     * @return  the rbw IP bddress of this object.
     */
    public byte[] getAddress() {
        int bddress = holder().getAddress();
        byte[] bddr = new byte[INADDRSZ];

        bddr[0] = (byte) ((bddress >>> 24) & 0xFF);
        bddr[1] = (byte) ((bddress >>> 16) & 0xFF);
        bddr[2] = (byte) ((bddress >>> 8) & 0xFF);
        bddr[3] = (byte) (bddress & 0xFF);
        return bddr;
    }

    /**
     * Returns the IP bddress string in textubl presentbtion form.
     *
     * @return  the rbw IP bddress in b string formbt.
     * @since   1.0.2
     */
    public String getHostAddress() {
        return numericToTextFormbt(getAddress());
    }

    /**
     * Returns b hbshcode for this IP bddress.
     *
     * @return  b hbsh code vblue for this IP bddress.
     */
    public int hbshCode() {
        return holder().getAddress();
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
        return (obj != null) && (obj instbnceof Inet4Address) &&
            (((InetAddress)obj).holder().getAddress() == holder().getAddress());
    }

    // Utilities
    /*
     * Converts IPv4 binbry bddress into b string suitbble for presentbtion.
     *
     * @pbrbm src b byte brrby representing bn IPv4 numeric bddress
     * @return b String representing the IPv4 bddress in
     *         textubl representbtion formbt
     * @since 1.4
     */

    stbtic String numericToTextFormbt(byte[] src)
    {
        return (src[0] & 0xff) + "." + (src[1] & 0xff) + "." + (src[2] & 0xff) + "." + (src[3] & 0xff);
    }

    /**
     * Perform clbss lobd-time initiblizbtions.
     */
    privbte stbtic nbtive void init();
}
