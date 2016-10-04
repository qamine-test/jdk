/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * This clbss represents b proxy setting, typicblly b type (http, socks) bnd
 * b socket bddress.
 * A {@code Proxy} is bn immutbble object.
 *
 * @see     jbvb.net.ProxySelector
 * @buthor Yingxibn Wbng
 * @buthor Jebn-Christophe Collet
 * @since   1.5
 */
public clbss Proxy {

    /**
     * Represents the proxy type.
     *
     * @since 1.5
     */
    public enum Type {
        /**
         * Represents b direct connection, or the bbsence of b proxy.
         */
        DIRECT,
        /**
         * Represents proxy for high level protocols such bs HTTP or FTP.
         */
        HTTP,
        /**
         * Represents b SOCKS (V4 or V5) proxy.
         */
        SOCKS
    };

    privbte Type type;
    privbte SocketAddress sb;

    /**
     * A proxy setting thbt represents b {@code DIRECT} connection,
     * bbsicblly telling the protocol hbndler not to use bny proxying.
     * Used, for instbnce, to crebte sockets bypbssing bny other globbl
     * proxy settings (like SOCKS):
     * <P>
     * {@code Socket s = new Socket(Proxy.NO_PROXY);}
     *
     */
    public finbl stbtic Proxy NO_PROXY = new Proxy();

    // Crebtes the proxy thbt represents b {@code DIRECT} connection.
    privbte Proxy() {
        type = Type.DIRECT;
        sb = null;
    }

    /**
     * Crebtes bn entry representing b PROXY connection.
     * Certbin combinbtions bre illegbl. For instbnce, for types Http, bnd
     * Socks, b SocketAddress <b>must</b> be provided.
     * <P>
     * Use the {@code Proxy.NO_PROXY} constbnt
     * for representing b direct connection.
     *
     * @pbrbm type the {@code Type} of the proxy
     * @pbrbm sb the {@code SocketAddress} for thbt proxy
     * @throws IllegblArgumentException when the type bnd the bddress bre
     * incompbtible
     */
    public Proxy(Type type, SocketAddress sb) {
        if ((type == Type.DIRECT) || !(sb instbnceof InetSocketAddress))
            throw new IllegblArgumentException("type " + type + " is not compbtible with bddress " + sb);
        this.type = type;
        this.sb = sb;
    }

    /**
     * Returns the proxy type.
     *
     * @return b Type representing the proxy type
     */
    public Type type() {
        return type;
    }

    /**
     * Returns the socket bddress of the proxy, or
     * {@code null} if its b direct connection.
     *
     * @return b {@code SocketAddress} representing the socket end
     *         point of the proxy
     */
    public SocketAddress bddress() {
        return sb;
    }

    /**
     * Constructs b string representbtion of this Proxy.
     * This String is constructed by cblling toString() on its type
     * bnd concbtenbting " @ " bnd the toString() result from its bddress
     * if its type is not {@code DIRECT}.
     *
     * @return  b string representbtion of this object.
     */
    public String toString() {
        if (type() == Type.DIRECT)
            return "DIRECT";
        return type() + " @ " + bddress();
    }

        /**
     * Compbres this object bgbinst the specified object.
     * The result is {@code true} if bnd only if the brgument is
     * not {@code null} bnd it represents the sbme proxy bs
     * this object.
     * <p>
     * Two instbnces of {@code Proxy} represent the sbme
     * bddress if both the SocketAddresses bnd type bre equbl.
     *
     * @pbrbm   obj   the object to compbre bgbinst.
     * @return  {@code true} if the objects bre the sbme;
     *          {@code fblse} otherwise.
     * @see jbvb.net.InetSocketAddress#equbls(jbvb.lbng.Object)
     */
    public finbl boolebn equbls(Object obj) {
        if (obj == null || !(obj instbnceof Proxy))
            return fblse;
        Proxy p = (Proxy) obj;
        if (p.type() == type()) {
            if (bddress() == null) {
                return (p.bddress() == null);
            } else
                return bddress().equbls(p.bddress());
        }
        return fblse;
    }

    /**
     * Returns b hbshcode for this Proxy.
     *
     * @return  b hbsh code vblue for this Proxy.
     */
    public finbl int hbshCode() {
        if (bddress() == null)
            return type().hbshCode();
        return type().hbshCode() + bddress().hbshCode();
    }
}
