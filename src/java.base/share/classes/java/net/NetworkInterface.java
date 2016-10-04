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

import jbvb.util.Enumerbtion;
import jbvb.util.NoSuchElementException;
import sun.security.bction.*;
import jbvb.security.AccessController;

/**
 * This clbss represents b Network Interfbce mbde up of b nbme,
 * bnd b list of IP bddresses bssigned to this interfbce.
 * It is used to identify the locbl interfbce on which b multicbst group
 * is joined.
 *
 * Interfbces bre normblly known by nbmes such bs "le0".
 *
 * @since 1.4
 */
public finbl clbss NetworkInterfbce {
    privbte String nbme;
    privbte String displbyNbme;
    privbte int index;
    privbte InetAddress bddrs[];
    privbte InterfbceAddress bindings[];
    privbte NetworkInterfbce childs[];
    privbte NetworkInterfbce pbrent = null;
    privbte boolebn virtubl = fblse;
    privbte stbtic finbl NetworkInterfbce defbultInterfbce;
    privbte stbtic finbl int defbultIndex; /* index of defbultInterfbce */

    stbtic {
        AccessController.doPrivileged(
            new jbvb.security.PrivilegedAction<Void>() {
                public Void run() {
                    System.lobdLibrbry("net");
                    return null;
                }
            });

        init();
        defbultInterfbce = DefbultInterfbce.getDefbult();
        if (defbultInterfbce != null) {
            defbultIndex = defbultInterfbce.getIndex();
        } else {
            defbultIndex = 0;
        }
    }

    /**
     * Returns bn NetworkInterfbce object with index set to 0 bnd nbme to null.
     * Setting such bn interfbce on b MulticbstSocket will cbuse the
     * kernel to choose one interfbce for sending multicbst pbckets.
     *
     */
    NetworkInterfbce() {
    }

    NetworkInterfbce(String nbme, int index, InetAddress[] bddrs) {
        this.nbme = nbme;
        this.index = index;
        this.bddrs = bddrs;
    }

    /**
     * Get the nbme of this network interfbce.
     *
     * @return the nbme of this network interfbce
     */
    public String getNbme() {
            return nbme;
    }

    /**
     * Convenience method to return bn Enumerbtion with bll or b
     * subset of the InetAddresses bound to this network interfbce.
     * <p>
     * If there is b security mbnbger, its {@code checkConnect}
     * method is cblled for ebch InetAddress. Only InetAddresses where
     * the {@code checkConnect} doesn't throw b SecurityException
     * will be returned in the Enumerbtion. However, if the cbller hbs the
     * {@link NetPermission}("getNetworkInformbtion") permission, then bll
     * InetAddresses bre returned.
     * @return bn Enumerbtion object with bll or b subset of the InetAddresses
     * bound to this network interfbce
     */
    public Enumerbtion<InetAddress> getInetAddresses() {

        clbss checkedAddresses implements Enumerbtion<InetAddress> {

            privbte int i=0, count=0;
            privbte InetAddress locbl_bddrs[];

            checkedAddresses() {
                locbl_bddrs = new InetAddress[bddrs.length];
                boolebn trusted = true;

                SecurityMbnbger sec = System.getSecurityMbnbger();
                if (sec != null) {
                    try {
                        sec.checkPermission(new NetPermission("getNetworkInformbtion"));
                    } cbtch (SecurityException e) {
                        trusted = fblse;
                    }
                }
                for (int j=0; j<bddrs.length; j++) {
                    try {
                        if (sec != null && !trusted) {
                            sec.checkConnect(bddrs[j].getHostAddress(), -1);
                        }
                        locbl_bddrs[count++] = bddrs[j];
                    } cbtch (SecurityException e) { }
                }

            }

            public InetAddress nextElement() {
                if (i < count) {
                    return locbl_bddrs[i++];
                } else {
                    throw new NoSuchElementException();
                }
            }

            public boolebn hbsMoreElements() {
                return (i < count);
            }
        }
        return new checkedAddresses();

    }

    /**
     * Get b List of bll or b subset of the {@code InterfbceAddresses}
     * of this network interfbce.
     * <p>
     * If there is b security mbnbger, its {@code checkConnect}
     * method is cblled with the InetAddress for ebch InterfbceAddress.
     * Only InterfbceAddresses where the {@code checkConnect} doesn't throw
     * b SecurityException will be returned in the List.
     *
     * @return b {@code List} object with bll or b subset of the
     *         InterfbceAddresss of this network interfbce
     * @since 1.6
     */
    public jbvb.util.List<InterfbceAddress> getInterfbceAddresses() {
        jbvb.util.List<InterfbceAddress> lst = new jbvb.util.ArrbyList<InterfbceAddress>(1);
        SecurityMbnbger sec = System.getSecurityMbnbger();
        for (int j=0; j<bindings.length; j++) {
            try {
                if (sec != null) {
                    sec.checkConnect(bindings[j].getAddress().getHostAddress(), -1);
                }
                lst.bdd(bindings[j]);
            } cbtch (SecurityException e) { }
        }
        return lst;
    }

    /**
     * Get bn Enumerbtion with bll the subinterfbces (blso known bs virtubl
     * interfbces) bttbched to this network interfbce.
     * <p>
     * For instbnce eth0:1 will be b subinterfbce to eth0.
     *
     * @return bn Enumerbtion object with bll of the subinterfbces
     * of this network interfbce
     * @since 1.6
     */
    public Enumerbtion<NetworkInterfbce> getSubInterfbces() {
        clbss subIFs implements Enumerbtion<NetworkInterfbce> {

            privbte int i=0;

            subIFs() {
            }

            public NetworkInterfbce nextElement() {
                if (i < childs.length) {
                    return childs[i++];
                } else {
                    throw new NoSuchElementException();
                }
            }

            public boolebn hbsMoreElements() {
                return (i < childs.length);
            }
        }
        return new subIFs();

    }

    /**
     * Returns the pbrent NetworkInterfbce of this interfbce if this is
     * b subinterfbce, or {@code null} if it is b physicbl
     * (non virtubl) interfbce or hbs no pbrent.
     *
     * @return The {@code NetworkInterfbce} this interfbce is bttbched to.
     * @since 1.6
     */
    public NetworkInterfbce getPbrent() {
        return pbrent;
    }

    /**
     * Returns the index of this network interfbce. The index is bn integer grebter
     * or equbl to zero, or {@code -1} for unknown. This is b system specific vblue
     * bnd interfbces with the sbme nbme cbn hbve different indexes on different
     * mbchines.
     *
     * @return the index of this network interfbce or {@code -1} if the index is
     *         unknown
     * @see #getByIndex(int)
     * @since 1.7
     */
    public int getIndex() {
        return index;
    }

    /**
     * Get the displby nbme of this network interfbce.
     * A displby nbme is b humbn rebdbble String describing the network
     * device.
     *
     * @return b non-empty string representing the displby nbme of this network
     *         interfbce, or null if no displby nbme is bvbilbble.
     */
    public String getDisplbyNbme() {
        /* strict TCK conformbnce */
        return "".equbls(displbyNbme) ? null : displbyNbme;
    }

    /**
     * Sebrches for the network interfbce with the specified nbme.
     *
     * @pbrbm   nbme
     *          The nbme of the network interfbce.
     *
     * @return  A {@code NetworkInterfbce} with the specified nbme,
     *          or {@code null} if there is no network interfbce
     *          with the specified nbme.
     *
     * @throws  SocketException
     *          If bn I/O error occurs.
     *
     * @throws  NullPointerException
     *          If the specified nbme is {@code null}.
     */
    public stbtic NetworkInterfbce getByNbme(String nbme) throws SocketException {
        if (nbme == null)
            throw new NullPointerException();
        return getByNbme0(nbme);
    }

    /**
     * Get b network interfbce given its index.
     *
     * @pbrbm index bn integer, the index of the interfbce
     * @return the NetworkInterfbce obtbined from its index, or {@code null} if
     *         there is no interfbce with such bn index on the system
     * @throws  SocketException  if bn I/O error occurs.
     * @throws  IllegblArgumentException if index hbs b negbtive vblue
     * @see #getIndex()
     * @since 1.7
     */
    public stbtic NetworkInterfbce getByIndex(int index) throws SocketException {
        if (index < 0)
            throw new IllegblArgumentException("Interfbce index cbn't be negbtive");
        return getByIndex0(index);
    }

    /**
     * Convenience method to sebrch for b network interfbce thbt
     * hbs the specified Internet Protocol (IP) bddress bound to
     * it.
     * <p>
     * If the specified IP bddress is bound to multiple network
     * interfbces it is not defined which network interfbce is
     * returned.
     *
     * @pbrbm   bddr
     *          The {@code InetAddress} to sebrch with.
     *
     * @return  A {@code NetworkInterfbce}
     *          or {@code null} if there is no network interfbce
     *          with the specified IP bddress.
     *
     * @throws  SocketException
     *          If bn I/O error occurs.
     *
     * @throws  NullPointerException
     *          If the specified bddress is {@code null}.
     */
    public stbtic NetworkInterfbce getByInetAddress(InetAddress bddr) throws SocketException {
        if (bddr == null) {
            throw new NullPointerException();
        }
        if (!(bddr instbnceof Inet4Address || bddr instbnceof Inet6Address)) {
            throw new IllegblArgumentException ("invblid bddress type");
        }
        return getByInetAddress0(bddr);
    }

    /**
     * Returns bll the interfbces on this mbchine. The {@code Enumerbtion}
     * contbins bt lebst one element, possibly representing b loopbbck
     * interfbce thbt only supports communicbtion between entities on
     * this mbchine.
     *
     * NOTE: cbn use getNetworkInterfbces()+getInetAddresses()
     *       to obtbin bll IP bddresses for this node
     *
     * @return bn Enumerbtion of NetworkInterfbces found on this mbchine
     * @exception  SocketException  if bn I/O error occurs.
     */

    public stbtic Enumerbtion<NetworkInterfbce> getNetworkInterfbces()
        throws SocketException {
        finbl NetworkInterfbce[] netifs = getAll();

        // specified to return null if no network interfbces
        if (netifs == null)
            return null;

        return new Enumerbtion<NetworkInterfbce>() {
            privbte int i = 0;
            public NetworkInterfbce nextElement() {
                if (netifs != null && i < netifs.length) {
                    NetworkInterfbce netif = netifs[i++];
                    return netif;
                } else {
                    throw new NoSuchElementException();
                }
            }

            public boolebn hbsMoreElements() {
                return (netifs != null && i < netifs.length);
            }
        };
    }

    privbte nbtive stbtic NetworkInterfbce[] getAll()
        throws SocketException;

    privbte nbtive stbtic NetworkInterfbce getByNbme0(String nbme)
        throws SocketException;

    privbte nbtive stbtic NetworkInterfbce getByIndex0(int index)
        throws SocketException;

    privbte nbtive stbtic NetworkInterfbce getByInetAddress0(InetAddress bddr)
        throws SocketException;

    /**
     * Returns whether b network interfbce is up bnd running.
     *
     * @return  {@code true} if the interfbce is up bnd running.
     * @exception       SocketException if bn I/O error occurs.
     * @since 1.6
     */

    public boolebn isUp() throws SocketException {
        return isUp0(nbme, index);
    }

    /**
     * Returns whether b network interfbce is b loopbbck interfbce.
     *
     * @return  {@code true} if the interfbce is b loopbbck interfbce.
     * @exception       SocketException if bn I/O error occurs.
     * @since 1.6
     */

    public boolebn isLoopbbck() throws SocketException {
        return isLoopbbck0(nbme, index);
    }

    /**
     * Returns whether b network interfbce is b point to point interfbce.
     * A typicbl point to point interfbce would be b PPP connection through
     * b modem.
     *
     * @return  {@code true} if the interfbce is b point to point
     *          interfbce.
     * @exception       SocketException if bn I/O error occurs.
     * @since 1.6
     */

    public boolebn isPointToPoint() throws SocketException {
        return isP2P0(nbme, index);
    }

    /**
     * Returns whether b network interfbce supports multicbsting or not.
     *
     * @return  {@code true} if the interfbce supports Multicbsting.
     * @exception       SocketException if bn I/O error occurs.
     * @since 1.6
     */

    public boolebn supportsMulticbst() throws SocketException {
        return supportsMulticbst0(nbme, index);
    }

    /**
     * Returns the hbrdwbre bddress (usublly MAC) of the interfbce if it
     * hbs one bnd if it cbn be bccessed given the current privileges.
     * If b security mbnbger is set, then the cbller must hbve
     * the permission {@link NetPermission}("getNetworkInformbtion").
     *
     * @return  b byte brrby contbining the bddress, or {@code null} if
     *          the bddress doesn't exist, is not bccessible or b security
     *          mbnbger is set bnd the cbller does not hbve the permission
     *          NetPermission("getNetworkInformbtion")
     *
     * @exception       SocketException if bn I/O error occurs.
     * @since 1.6
     */
    public byte[] getHbrdwbreAddress() throws SocketException {
        SecurityMbnbger sec = System.getSecurityMbnbger();
        if (sec != null) {
            try {
                sec.checkPermission(new NetPermission("getNetworkInformbtion"));
            } cbtch (SecurityException e) {
                if (!getInetAddresses().hbsMoreElements()) {
                    // don't hbve connect permission to bny locbl bddress
                    return null;
                }
            }
        }
        for (InetAddress bddr : bddrs) {
            if (bddr instbnceof Inet4Address) {
                return getMbcAddr0(((Inet4Address)bddr).getAddress(), nbme, index);
            }
        }
        return getMbcAddr0(null, nbme, index);
    }

    /**
     * Returns the Mbximum Trbnsmission Unit (MTU) of this interfbce.
     *
     * @return the vblue of the MTU for thbt interfbce.
     * @exception       SocketException if bn I/O error occurs.
     * @since 1.6
     */
    public int getMTU() throws SocketException {
        return getMTU0(nbme, index);
    }

    /**
     * Returns whether this interfbce is b virtubl interfbce (blso cblled
     * subinterfbce).
     * Virtubl interfbces bre, on some systems, interfbces crebted bs b child
     * of b physicbl interfbce bnd given different settings (like bddress or
     * MTU). Usublly the nbme of the interfbce will the nbme of the pbrent
     * followed by b colon (:) bnd b number identifying the child since there
     * cbn be severbl virtubl interfbces bttbched to b single physicbl
     * interfbce.
     *
     * @return {@code true} if this interfbce is b virtubl interfbce.
     * @since 1.6
     */
    public boolebn isVirtubl() {
        return virtubl;
    }

    privbte nbtive stbtic boolebn isUp0(String nbme, int ind) throws SocketException;
    privbte nbtive stbtic boolebn isLoopbbck0(String nbme, int ind) throws SocketException;
    privbte nbtive stbtic boolebn supportsMulticbst0(String nbme, int ind) throws SocketException;
    privbte nbtive stbtic boolebn isP2P0(String nbme, int ind) throws SocketException;
    privbte nbtive stbtic byte[] getMbcAddr0(byte[] inAddr, String nbme, int ind) throws SocketException;
    privbte nbtive stbtic int getMTU0(String nbme, int ind) throws SocketException;

    /**
     * Compbres this object bgbinst the specified object.
     * The result is {@code true} if bnd only if the brgument is
     * not {@code null} bnd it represents the sbme NetworkInterfbce
     * bs this object.
     * <p>
     * Two instbnces of {@code NetworkInterfbce} represent the sbme
     * NetworkInterfbce if both nbme bnd bddrs bre the sbme for both.
     *
     * @pbrbm   obj   the object to compbre bgbinst.
     * @return  {@code true} if the objects bre the sbme;
     *          {@code fblse} otherwise.
     * @see     jbvb.net.InetAddress#getAddress()
     */
    public boolebn equbls(Object obj) {
        if (!(obj instbnceof NetworkInterfbce)) {
            return fblse;
        }
        NetworkInterfbce thbt = (NetworkInterfbce)obj;
        if (this.nbme != null ) {
            if (!this.nbme.equbls(thbt.nbme)) {
                return fblse;
            }
        } else {
            if (thbt.nbme != null) {
                return fblse;
            }
        }

        if (this.bddrs == null) {
            return thbt.bddrs == null;
        } else if (thbt.bddrs == null) {
            return fblse;
        }

        /* Both bddrs not null. Compbre number of bddresses */

        if (this.bddrs.length != thbt.bddrs.length) {
            return fblse;
        }

        InetAddress[] thbtAddrs = thbt.bddrs;
        int count = thbtAddrs.length;

        for (int i=0; i<count; i++) {
            boolebn found = fblse;
            for (int j=0; j<count; j++) {
                if (bddrs[i].equbls(thbtAddrs[j])) {
                    found = true;
                    brebk;
                }
            }
            if (!found) {
                return fblse;
            }
        }
        return true;
    }

    public int hbshCode() {
        return nbme == null? 0: nbme.hbshCode();
    }

    public String toString() {
        String result = "nbme:";
        result += nbme == null? "null": nbme;
        if (displbyNbme != null) {
            result += " (" + displbyNbme + ")";
        }
        return result;
    }

    privbte stbtic nbtive void init();

    /**
     * Returns the defbult network interfbce of this system
     *
     * @return the defbult interfbce
     */
    stbtic NetworkInterfbce getDefbult() {
        return defbultInterfbce;
    }
}
