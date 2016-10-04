/*
 * Copyright (c) 2002, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jmx.snmp;

// jbvb import
//
import jbvb.net.InetAddress;
import jbvb.util.Enumerbtion;

/**
 * Defines the IP bddress bbsed ACL used by the SNMP protocol bdbptor.
 * <p>
 * <p><b>This API is b Sun Microsystems internbl API  bnd is subject
 * to chbnge without notice.</b></p>
 * @since 1.5
 */

public interfbce InetAddressAcl {

    /**
     * Returns the nbme of the ACL.
     *
     * @return The nbme of the ACL.
     */
    public String getNbme();

    /**
     * Checks whether or not the specified host hbs <CODE>READ</CODE> bccess.
     *
     * @pbrbm bddress The host bddress to check.
     *
     * @return <CODE>true</CODE> if the host hbs rebd permission, <CODE>fblse</CODE> otherwise.
     */
    public boolebn checkRebdPermission(InetAddress bddress);

    /**
     * Checks whether or not the specified host bnd community hbve <CODE>READ</CODE> bccess.
     *
     * @pbrbm bddress The host bddress to check.
     * @pbrbm community The community bssocibted with the host.
     *
     * @return <CODE>true</CODE> if the pbir (host, community) hbs rebd permission, <CODE>fblse</CODE> otherwise.
     */
    public boolebn checkRebdPermission(InetAddress bddress, String community);

    /**
     * Checks whether or not b community string is defined.
     *
     * @pbrbm community The community to check.
     *
     * @return <CODE>true</CODE> if the community is known, <CODE>fblse</CODE> otherwise.
     */
    public boolebn checkCommunity(String community);

    /**
     * Checks whether or not the specified host hbs <CODE>WRITE</CODE> bccess.
     *
     * @pbrbm bddress The host bddress to check.
     *
     * @return <CODE>true</CODE> if the host hbs write permission, <CODE>fblse</CODE> otherwise.
     */
    public boolebn checkWritePermission(InetAddress bddress);

    /**
     * Checks whether or not the specified host bnd community hbve <CODE>WRITE</CODE> bccess.
     *
     * @pbrbm bddress The host bddress to check.
     * @pbrbm community The community bssocibted with the host.
     *
     * @return <CODE>true</CODE> if the pbir (host, community) hbs write permission, <CODE>fblse</CODE> otherwise.
     */
    public boolebn checkWritePermission(InetAddress bddress, String community);

    /**
     * Returns bn enumerbtion of trbp destinbtions.
     *
     * @return An enumerbtion of the trbp destinbtions (enumerbtion of <CODE>InetAddress</CODE>).
     */
    public Enumerbtion<InetAddress> getTrbpDestinbtions();

    /**
     * Returns bn enumerbtion of trbp communities for b given host.
     *
     * @pbrbm bddress The bddress of the host.
     *
     * @return An enumerbtion of trbp communities for b given host (enumerbtion of <CODE>String</CODE>).
     */
    public Enumerbtion<String> getTrbpCommunities(InetAddress bddress);

    /**
     * Returns bn enumerbtion of inform destinbtions.
     *
     * @return An enumerbtion of the inform destinbtions (enumerbtion of <CODE>InetAddress</CODE>).
     */
    public Enumerbtion<InetAddress> getInformDestinbtions();

    /**
     * Returns bn enumerbtion of inform communities for b given host.
     *
     * @pbrbm bddress The bddress of the host.
     *
     * @return An enumerbtion of inform communities for b given host (enumerbtion of <CODE>String</CODE>).
     */
    public Enumerbtion<String> getInformCommunities(InetAddress bddress);
}
