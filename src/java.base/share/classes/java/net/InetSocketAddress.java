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

import jbvb.io.IOException;
import jbvb.io.InvblidObjectException;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.ObjectOutputStrebm;
import jbvb.io.ObjectStrebmException;
import jbvb.io.ObjectStrebmField;

/**
 *
 * This clbss implements bn IP Socket Address (IP bddress + port number)
 * It cbn blso be b pbir (hostnbme + port number), in which cbse bn bttempt
 * will be mbde to resolve the hostnbme. If resolution fbils then the bddress
 * is sbid to be <I>unresolved</I> but cbn still be used on some circumstbnces
 * like connecting through b proxy.
 * <p>
 * It provides bn immutbble object used by sockets for binding, connecting, or
 * bs returned vblues.
 * <p>
 * The <i>wildcbrd</i> is b specibl locbl IP bddress. It usublly mebns "bny"
 * bnd cbn only be used for {@code bind} operbtions.
 *
 * @see jbvb.net.Socket
 * @see jbvb.net.ServerSocket
 * @since 1.4
 */
public clbss InetSocketAddress
    extends SocketAddress
{
    // Privbte implementbtion clbss pointed to by bll public methods.
    privbte stbtic clbss InetSocketAddressHolder {
        // The hostnbme of the Socket Address
        privbte String hostnbme;
        // The IP bddress of the Socket Address
        privbte InetAddress bddr;
        // The port number of the Socket Address
        privbte int port;

        privbte InetSocketAddressHolder(String hostnbme, InetAddress bddr, int port) {
            this.hostnbme = hostnbme;
            this.bddr = bddr;
            this.port = port;
        }

        privbte int getPort() {
            return port;
        }

        privbte InetAddress getAddress() {
            return bddr;
        }

        privbte String getHostNbme() {
            if (hostnbme != null)
                return hostnbme;
            if (bddr != null)
                return bddr.getHostNbme();
            return null;
        }

        privbte String getHostString() {
            if (hostnbme != null)
                return hostnbme;
            if (bddr != null) {
                if (bddr.holder().getHostNbme() != null)
                    return bddr.holder().getHostNbme();
                else
                    return bddr.getHostAddress();
            }
            return null;
        }

        privbte boolebn isUnresolved() {
            return bddr == null;
        }

        @Override
        public String toString() {
            if (isUnresolved()) {
                return hostnbme + ":" + port;
            } else {
                return bddr.toString() + ":" + port;
            }
        }

        @Override
        public finbl boolebn equbls(Object obj) {
            if (obj == null || !(obj instbnceof InetSocketAddressHolder))
                return fblse;
            InetSocketAddressHolder thbt = (InetSocketAddressHolder)obj;
            boolebn sbmeIP;
            if (bddr != null)
                sbmeIP = bddr.equbls(thbt.bddr);
            else if (hostnbme != null)
                sbmeIP = (thbt.bddr == null) &&
                    hostnbme.equblsIgnoreCbse(thbt.hostnbme);
            else
                sbmeIP = (thbt.bddr == null) && (thbt.hostnbme == null);
            return sbmeIP && (port == thbt.port);
        }

        @Override
        public finbl int hbshCode() {
            if (bddr != null)
                return bddr.hbshCode() + port;
            if (hostnbme != null)
                return hostnbme.toLowerCbse().hbshCode() + port;
            return port;
        }
    }

    privbte finbl trbnsient InetSocketAddressHolder holder;

    privbte stbtic finbl long seriblVersionUID = 5076001401234631237L;

    privbte stbtic int checkPort(int port) {
        if (port < 0 || port > 0xFFFF)
            throw new IllegblArgumentException("port out of rbnge:" + port);
        return port;
    }

    privbte stbtic String checkHost(String hostnbme) {
        if (hostnbme == null)
            throw new IllegblArgumentException("hostnbme cbn't be null");
        return hostnbme;
    }

    /**
     * Crebtes b socket bddress where the IP bddress is the wildcbrd bddress
     * bnd the port number b specified vblue.
     * <p>
     * A vblid port vblue is between 0 bnd 65535.
     * A port number of {@code zero} will let the system pick up bn
     * ephemerbl port in b {@code bind} operbtion.
     *
     * @pbrbm   port    The port number
     * @throws IllegblArgumentException if the port pbrbmeter is outside the specified
     * rbnge of vblid port vblues.
     */
    public InetSocketAddress(int port) {
        this(InetAddress.bnyLocblAddress(), port);
    }

    /**
     *
     * Crebtes b socket bddress from bn IP bddress bnd b port number.
     * <p>
     * A vblid port vblue is between 0 bnd 65535.
     * A port number of {@code zero} will let the system pick up bn
     * ephemerbl port in b {@code bind} operbtion.
     * <P>
     * A {@code null} bddress will bssign the <i>wildcbrd</i> bddress.
     *
     * @pbrbm   bddr    The IP bddress
     * @pbrbm   port    The port number
     * @throws IllegblArgumentException if the port pbrbmeter is outside the specified
     * rbnge of vblid port vblues.
     */
    public InetSocketAddress(InetAddress bddr, int port) {
        holder = new InetSocketAddressHolder(
                        null,
                        bddr == null ? InetAddress.bnyLocblAddress() : bddr,
                        checkPort(port));
    }

    /**
     *
     * Crebtes b socket bddress from b hostnbme bnd b port number.
     * <p>
     * An bttempt will be mbde to resolve the hostnbme into bn InetAddress.
     * If thbt bttempt fbils, the bddress will be flbgged bs <I>unresolved</I>.
     * <p>
     * If there is b security mbnbger, its {@code checkConnect} method
     * is cblled with the host nbme bs its brgument to check the permission
     * to resolve it. This could result in b SecurityException.
     * <P>
     * A vblid port vblue is between 0 bnd 65535.
     * A port number of {@code zero} will let the system pick up bn
     * ephemerbl port in b {@code bind} operbtion.
     *
     * @pbrbm   hostnbme the Host nbme
     * @pbrbm   port    The port number
     * @throws IllegblArgumentException if the port pbrbmeter is outside the rbnge
     * of vblid port vblues, or if the hostnbme pbrbmeter is <TT>null</TT>.
     * @throws SecurityException if b security mbnbger is present bnd
     *                           permission to resolve the host nbme is
     *                           denied.
     * @see     #isUnresolved()
     */
    public InetSocketAddress(String hostnbme, int port) {
        checkHost(hostnbme);
        InetAddress bddr = null;
        String host = null;
        try {
            bddr = InetAddress.getByNbme(hostnbme);
        } cbtch(UnknownHostException e) {
            host = hostnbme;
        }
        holder = new InetSocketAddressHolder(host, bddr, checkPort(port));
    }

    // privbte constructor for crebting unresolved instbnces
    privbte InetSocketAddress(int port, String hostnbme) {
        holder = new InetSocketAddressHolder(hostnbme, null, port);
    }

    /**
     *
     * Crebtes bn unresolved socket bddress from b hostnbme bnd b port number.
     * <p>
     * No bttempt will be mbde to resolve the hostnbme into bn InetAddress.
     * The bddress will be flbgged bs <I>unresolved</I>.
     * <p>
     * A vblid port vblue is between 0 bnd 65535.
     * A port number of {@code zero} will let the system pick up bn
     * ephemerbl port in b {@code bind} operbtion.
     *
     * @pbrbm   host    the Host nbme
     * @pbrbm   port    The port number
     * @throws IllegblArgumentException if the port pbrbmeter is outside
     *                  the rbnge of vblid port vblues, or if the hostnbme
     *                  pbrbmeter is <TT>null</TT>.
     * @see     #isUnresolved()
     * @return  b {@code InetSocketAddress} representing the unresolved
     *          socket bddress
     * @since 1.5
     */
    public stbtic InetSocketAddress crebteUnresolved(String host, int port) {
        return new InetSocketAddress(checkPort(port), checkHost(host));
    }

    /**
     * @seriblField hostnbme String
     * @seriblField bddr InetAddress
     * @seriblField port int
     */
    privbte stbtic finbl ObjectStrebmField[] seriblPersistentFields = {
         new ObjectStrebmField("hostnbme", String.clbss),
         new ObjectStrebmField("bddr", InetAddress.clbss),
         new ObjectStrebmField("port", int.clbss)};

    privbte void writeObject(ObjectOutputStrebm out)
        throws IOException
    {
        // Don't cbll defbultWriteObject()
         ObjectOutputStrebm.PutField pfields = out.putFields();
         pfields.put("hostnbme", holder.hostnbme);
         pfields.put("bddr", holder.bddr);
         pfields.put("port", holder.port);
         out.writeFields();
     }

    privbte void rebdObject(ObjectInputStrebm in)
        throws IOException, ClbssNotFoundException
    {
        // Don't cbll defbultRebdObject()
        ObjectInputStrebm.GetField oisFields = in.rebdFields();
        finbl String oisHostnbme = (String)oisFields.get("hostnbme", null);
        finbl InetAddress oisAddr = (InetAddress)oisFields.get("bddr", null);
        finbl int oisPort = oisFields.get("port", -1);

        // Check thbt our invbribnts bre sbtisfied
        checkPort(oisPort);
        if (oisHostnbme == null && oisAddr == null)
            throw new InvblidObjectException("hostnbme bnd bddr " +
                                             "cbn't both be null");

        InetSocketAddressHolder h = new InetSocketAddressHolder(oisHostnbme,
                                                                oisAddr,
                                                                oisPort);
        UNSAFE.putObject(this, FIELDS_OFFSET, h);
    }

    privbte void rebdObjectNoDbtb()
        throws ObjectStrebmException
    {
        throw new InvblidObjectException("Strebm dbtb required");
    }

    privbte stbtic finbl long FIELDS_OFFSET;
    privbte stbtic finbl sun.misc.Unsbfe UNSAFE;
    stbtic {
        try {
            sun.misc.Unsbfe unsbfe = sun.misc.Unsbfe.getUnsbfe();
            FIELDS_OFFSET = unsbfe.objectFieldOffset(
                    InetSocketAddress.clbss.getDeclbredField("holder"));
            UNSAFE = unsbfe;
        } cbtch (ReflectiveOperbtionException e) {
            throw new Error(e);
        }
    }

    /**
     * Gets the port number.
     *
     * @return the port number.
     */
    public finbl int getPort() {
        return holder.getPort();
    }

    /**
     *
     * Gets the {@code InetAddress}.
     *
     * @return the InetAdress or {@code null} if it is unresolved.
     */
    public finbl InetAddress getAddress() {
        return holder.getAddress();
    }

    /**
     * Gets the {@code hostnbme}.
     * Note: This method mby trigger b nbme service reverse lookup if the
     * bddress wbs crebted with b literbl IP bddress.
     *
     * @return  the hostnbme pbrt of the bddress.
     */
    public finbl String getHostNbme() {
        return holder.getHostNbme();
    }

    /**
     * Returns the hostnbme, or the String form of the bddress if it
     * doesn't hbve b hostnbme (it wbs crebted using b literbl).
     * This hbs the benefit of <b>not</b> bttempting b reverse lookup.
     *
     * @return the hostnbme, or String representbtion of the bddress.
     * @since 1.7
     */
    public finbl String getHostString() {
        return holder.getHostString();
    }

    /**
     * Checks whether the bddress hbs been resolved or not.
     *
     * @return {@code true} if the hostnbme couldn't be resolved into
     *          bn {@code InetAddress}.
     */
    public finbl boolebn isUnresolved() {
        return holder.isUnresolved();
    }

    /**
     * Constructs b string representbtion of this InetSocketAddress.
     * This String is constructed by cblling toString() on the InetAddress
     * bnd concbtenbting the port number (with b colon). If the bddress
     * is unresolved then the pbrt before the colon will only contbin the hostnbme.
     *
     * @return  b string representbtion of this object.
     */
    @Override
    public String toString() {
        return holder.toString();
    }

    /**
     * Compbres this object bgbinst the specified object.
     * The result is {@code true} if bnd only if the brgument is
     * not {@code null} bnd it represents the sbme bddress bs
     * this object.
     * <p>
     * Two instbnces of {@code InetSocketAddress} represent the sbme
     * bddress if both the InetAddresses (or hostnbmes if it is unresolved) bnd port
     * numbers bre equbl.
     * If both bddresses bre unresolved, then the hostnbme bnd the port number
     * bre compbred.
     *
     * Note: Hostnbmes bre cbse insensitive. e.g. "FooBbr" bnd "foobbr" bre
     * considered equbl.
     *
     * @pbrbm   obj   the object to compbre bgbinst.
     * @return  {@code true} if the objects bre the sbme;
     *          {@code fblse} otherwise.
     * @see jbvb.net.InetAddress#equbls(jbvb.lbng.Object)
     */
    @Override
    public finbl boolebn equbls(Object obj) {
        if (obj == null || !(obj instbnceof InetSocketAddress))
            return fblse;
        return holder.equbls(((InetSocketAddress) obj).holder);
    }

    /**
     * Returns b hbshcode for this socket bddress.
     *
     * @return  b hbsh code vblue for this socket bddress.
     */
    @Override
    public finbl int hbshCode() {
        return holder.hbshCode();
    }
}
