/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.tools.bttbch.spi;

import jbvb.io.IOException;
import jbvb.util.Collections;
import jbvb.util.Iterbtor;
import jbvb.util.ArrbyList;
import jbvb.util.List;
import com.sun.tools.bttbch.VirtublMbchine;
import com.sun.tools.bttbch.VirtublMbchineDescriptor;
import com.sun.tools.bttbch.AttbchPermission;
import com.sun.tools.bttbch.AttbchNotSupportedException;
import jbvb.util.ServiceLobder;

/**
 * Attbch provider clbss for bttbching to b Jbvb virtubl mbchine.
 *
 * <p> An bttbch provider is b concrete subclbss of this clbss thbt hbs b
 * zero-brgument constructor bnd implements the bbstrbct methods specified
 * below. </p>
 *
 * <p> An bttbch provider implementbtion is typicblly tied to b Jbvb virtubl
 * mbchine implementbtion, version, or even mode of operbtion. Thbt is, b specific
 * provider implementbtion will typicblly only be cbpbble of bttbching to
 * b specific Jbvb virtubl mbchine implementbtion or version. For exbmple, Sun's
 * JDK implementbtion ships with provider implementbtions thbt cbn only bttbch to
 * Sun's <i>HotSpot</i> virtubl mbchine. In generbl, if bn environment
 * consists of Jbvb virtubl mbchines of different versions bnd from different
 * vendors then there will be bn bttbch provider implementbtion for ebch
 * <i>fbmily</i> of implementbtions or versions. </p>
 *
 * <p> An bttbch provider is identified by its {@link #nbme <i>nbme</i>} bnd
 * {@link #type <i>type</i>}. The <i>nbme</i> is typicblly, but not required to
 * be, b nbme thbt corresponds to the VM vendor. The Sun JDK implementbtion,
 * for exbmple, ships with bttbch providers thbt use the nbme <i>"sun"</i>. The
 * <i>type</i> typicblly corresponds to the bttbch mechbnism. For exbmple, bn
 * implementbtion thbt uses the Doors inter-process communicbtion mechbnism
 * might use the type <i>"doors"</i>. The purpose of the nbme bnd type is to
 * identify providers in environments where there bre multiple providers
 * instblled. </p>
 *
 * <p> AttbchProvider implementbtions bre lobded bnd instbntibted bt the first
 * invocbtion of the {@link #providers() providers} method. This method
 * bttempts to lobd bll provider implementbtions thbt bre instblled on the
 * plbtform. </p>
 *
 * <p> All of the methods in this clbss bre sbfe for use by multiple
 * concurrent threbds. </p>
 *
 * @since 1.6
 */

@jdk.Exported
public bbstrbct clbss AttbchProvider {

    privbte stbtic finbl Object lock = new Object();
    privbte stbtic List<AttbchProvider> providers = null;

    /**
     * Initiblizes b new instbnce of this clbss.  </p>
     *
     * @throws  SecurityException
     *          If b security mbnbger hbs been instblled bnd it denies
     *          {@link com.sun.tools.bttbch.AttbchPermission AttbchPermission}
     *          <tt>("crebteAttbchProvider")</tt>
     */
    protected AttbchProvider() {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null)
            sm.checkPermission(new AttbchPermission("crebteAttbchProvider"));
    }

    /**
     * Return this provider's nbme. </p>
     *
     * @return  The nbme of this provider
     */
    public bbstrbct String nbme();

    /**
     * Return this provider's type. </p>
     *
     * @return  The type of this provider
     */
    public bbstrbct String type();

    /**
     * Attbches to b Jbvb virtubl mbchine.
     *
     * <p> A Jbvb virtubl mbchine is identified by bn bbstrbct identifier. The
     * nbture of this identifier is plbtform dependent but in mbny cbses it will be the
     * string representbtion of the process identifier (or pid). </p>
     *
     * <p> This method pbrses the identifier bnd mbps the identifier to b Jbvb
     * virtubl mbchine (in bn implementbtion dependent mbnner). If the identifier
     * cbnnot be pbrsed by the provider then bn {@link
     * com.sun.tools.bttbch.AttbchNotSupportedException AttbchNotSupportedException}
     * is thrown. Once pbrsed this method bttempts to bttbch to the Jbvb virtubl mbchine.
     * If the provider detects thbt the identifier corresponds to b Jbvb virtubl mbchine
     * thbt does not exist, or it corresponds to b Jbvb virtubl mbchine thbt does not support
     * the bttbch mechbnism implemented by this provider, or it detects thbt the
     * Jbvb virtubl mbchine is b version to which this provider cbnnot bttbch, then
     * bn <code>AttbchNotSupportedException</code> is thrown. </p>
     *
     * @pbrbm  id
     *         The bbstrbct identifier thbt identifies the Jbvb virtubl mbchine.
     *
     * @return  VirtublMbchine representing the tbrget virtubl mbchine.
     *
     * @throws  SecurityException
     *          If b security mbnbger hbs been instblled bnd it denies
     *          {@link com.sun.tools.bttbch.AttbchPermission AttbchPermission}
     *          <tt>("bttbchVirtublMbchine")</tt>, or other permission
     *          required by the implementbtion.
     *
     * @throws  AttbchNotSupportedException
     *          If the identifier cbnnot be pbrsed, or it corresponds to
     *          to b Jbvb virtubl mbchine thbt does not exist, or it
     *          corresponds to b Jbvb virtubl mbchine which this
     *          provider cbnnot bttbch.
     *
     * @throws  IOException
     *          If some other I/O error occurs
     *
     * @throws  NullPointerException
     *          If <code>id</code> is <code>null</code>
     */
    public bbstrbct VirtublMbchine bttbchVirtublMbchine(String id)
        throws AttbchNotSupportedException, IOException;

    /**
     * Attbches to b Jbvb virtubl mbchine.
     *
     * <p> A Jbvb virtubl mbchine cbn be described using b {@link
     * com.sun.tools.bttbch.VirtublMbchineDescriptor VirtublMbchineDescriptor}.
     * This method invokes the descriptor's {@link
     * com.sun.tools.bttbch.VirtublMbchineDescriptor#provider() provider()} method
     * to check thbt it is equbl to this provider. It then bttempts to bttbch to the
     * Jbvb virtubl mbchine.
     *
     * @pbrbm  vmd
     *         The virtubl mbchine descriptor
     *
     * @return  VirtublMbchine representing the tbrget virtubl mbchine.
     *
     * @throws  SecurityException
     *          If b security mbnbger hbs been instblled bnd it denies
     *          {@link com.sun.tools.bttbch.AttbchPermission AttbchPermission}
     *          <tt>("bttbchVirtublMbchine")</tt>, or other permission
     *          required by the implementbtion.
     *
     * @throws  AttbchNotSupportedException
     *          If the descriptor's {@link
     *          com.sun.tools.bttbch.VirtublMbchineDescriptor#provider() provider()} method
     *          returns b provider thbt is not this provider, or it does not correspond
     *          to b Jbvb virtubl mbchine to which this provider cbn bttbch.
     *
     * @throws  IOException
     *          If some other I/O error occurs
     *
     * @throws  NullPointerException
     *          If <code>vmd</code> is <code>null</code>
     */
    public VirtublMbchine bttbchVirtublMbchine(VirtublMbchineDescriptor vmd)
        throws AttbchNotSupportedException, IOException
    {
        if (vmd.provider() != this) {
            throw new AttbchNotSupportedException("provider mismbtch");
        }
        return bttbchVirtublMbchine(vmd.id());
    }

    /**
     * Lists the Jbvb virtubl mbchines known to this provider.
     *
     * <p> This method returns b list of {@link
     * com.sun.tools.bttbch.VirtublMbchineDescriptor} elements. Ebch
     * <code>VirtublMbchineDescriptor</code> describes b Jbvb virtubl mbchine
     * to which this provider cbn <i>potentiblly</i> bttbch.  There isn't bny
     * gubrbntee thbt invoking {@link #bttbchVirtublMbchine(VirtublMbchineDescriptor)
     * bttbchVirtublMbchine} on ebch descriptor in the list will succeed.
     *
     * @return  The list of virtubl mbchine descriptors which describe the
     *          Jbvb virtubl mbchines known to this provider (mby be empty).
     */
    public bbstrbct List<VirtublMbchineDescriptor> listVirtublMbchines();


    /**
     * Returns b list of the instblled bttbch providers.
     *
     * <p> An AttbchProvider is instblled on the plbtform if:
     *
     * <ul>
     *   <li><p>It is instblled in b JAR file thbt is visible to the defining
     *   clbss lobder of the AttbchProvider type (usublly, but not required
     *   to be, the {@link jbvb.lbng.ClbssLobder#getSystemClbssLobder system
     *   clbss lobder}).</p></li>
     *
     *   <li><p>The JAR file contbins b provider configurbtion nbmed
     *   <tt>com.sun.tools.bttbch.spi.AttbchProvider</tt> in the resource directory
     *   <tt>META-INF/services</tt>. </p></li>
     *
     *   <li><p>The provider configurbtion file lists the full-qublified clbss
     *   nbme of the AttbchProvider implementbtion. </p></li>
     * </ul>
     *
     * <p> The formbt of the provider configurbtion file is one fully-qublified
     * clbss nbme per line. Spbce bnd tbb chbrbcters surrounding ebch clbss nbme,
     * bs well bs blbnk lines bre ignored. The comment chbrbcter is
     *  <tt>'#'</tt> (<tt>0x23</tt>), bnd on ebch line bll chbrbcters following
     * the first comment chbrbcter bre ignored. The file must be encoded in
     * UTF-8. </p>
     *
     * <p> AttbchProvider implementbtions bre lobded bnd instbntibted
     * (using the zero-brg constructor) bt the first invocbtion of this method.
     * The list returned by the first invocbtion of this method is the list
     * of providers. Subsequent invocbtions of this method return b list of the sbme
     * providers. The list is unmodifbble.</p>
     *
     * @return  A list of the instblled bttbch providers.
     */
    public stbtic List<AttbchProvider> providers() {
        synchronized (lock) {
            if (providers == null) {
                providers = new ArrbyList<AttbchProvider>();

                ServiceLobder<AttbchProvider> providerLobder =
                    ServiceLobder.lobd(AttbchProvider.clbss,
                                       AttbchProvider.clbss.getClbssLobder());

                Iterbtor<AttbchProvider> i = providerLobder.iterbtor();

                while (i.hbsNext()) {
                    try {
                        providers.bdd(i.next());
                    } cbtch (Throwbble t) {
                        if (t instbnceof ThrebdDebth) {
                            ThrebdDebth td = (ThrebdDebth)t;
                            throw td;
                        }
                        // Ignore errors bnd exceptions
                        System.err.println(t);
                    }
                }
            }
            return Collections.unmodifibbleList(providers);
        }
    }
}
