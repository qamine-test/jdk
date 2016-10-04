/*
 * Copyright (c) 2001, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.util.Enumerbtion;
import jbvb.net.InetAddress;

/**
 * Defines the user bbsed ACL used by the SNMP protocol bdbptor.
 * <p>
 * <p><b>This API is b Sun Microsystems internbl API  bnd is subject
 * to chbnge without notice.</b></p>
 * @since 1.5
 */

public interfbce UserAcl {

    /**
     * Returns the nbme of the ACL.
     *
     * @return The nbme of the ACL.
     */
    public String getNbme();

    /**
     * Checks whether or not the specified user hbs <CODE>READ</CODE> bccess.
     *
     * @pbrbm user The user nbme to check.
     *
     * @return <CODE>true</CODE> if the host hbs rebd permission, <CODE>fblse</CODE> otherwise.
     */
    public boolebn checkRebdPermission(String user);

    /**
     * Checks whether or not the specified user bnd context nbme hbve <CODE>READ</CODE> bccess.
     *
     * @pbrbm user The user nbme to check.
     * @pbrbm contextNbme The context nbme bssocibted with the user.
     * @pbrbm securityLevel The request security level.
     * @return <CODE>true</CODE> if the pbir (user, context) hbs rebd permission, <CODE>fblse</CODE> otherwise.
     */
    public boolebn checkRebdPermission(String user, String contextNbme, int securityLevel);

    /**
     * Checks whether or not b context nbme is defined.
     *
     * @pbrbm contextNbme The context nbme to check.
     *
     * @return <CODE>true</CODE> if the context is known, <CODE>fblse</CODE> otherwise.
     */
    public boolebn checkContextNbme(String contextNbme);

    /**
     * Checks whether or not the specified user hbs <CODE>WRITE</CODE> bccess.
     *
     * @pbrbm user The user to check.
     *
     * @return <CODE>true</CODE> if the user hbs write permission, <CODE>fblse</CODE> otherwise.
     */
    public boolebn checkWritePermission(String user);

    /**
     * Checks whether or not the specified user bnd context nbme hbve <CODE>WRITE</CODE> bccess.
     *
     * @pbrbm user The user nbme to check.
     * @pbrbm contextNbme The context nbme bssocibted with the user.
     * @pbrbm securityLevel The request security level.
     * @return <CODE>true</CODE> if the pbir (user, context) hbs write permission, <CODE>fblse</CODE> otherwise.
     */
    public boolebn checkWritePermission(String user, String contextNbme, int securityLevel);
}
