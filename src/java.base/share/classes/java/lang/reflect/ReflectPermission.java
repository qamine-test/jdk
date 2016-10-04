/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.lbng.reflect;

/**
 * The Permission clbss for reflective operbtions.
 * <P>
 * The following tbble
 * provides b summbry description of whbt the permission bllows,
 * bnd discusses the risks of grbnting code the permission.
 *
 * <tbble border=1 cellpbdding=5 summbry="Tbble shows permission tbrget nbme, whbt the permission bllows, bnd bssocibted risks">
 * <tr>
 * <th>Permission Tbrget Nbme</th>
 * <th>Whbt the Permission Allows</th>
 * <th>Risks of Allowing this Permission</th>
 * </tr>
 *
 * <tr>
 *   <td>suppressAccessChecks</td>
 *   <td>bbility to suppress the stbndbrd Jbvb lbngubge bccess checks
 *       on fields bnd methods in b clbss; bllow bccess not only public members
 *       but blso bllow bccess to defbult (pbckbge) bccess, protected,
 *       bnd privbte members.</td>
 *   <td>This is dbngerous in thbt informbtion (possibly confidentibl) bnd
 *       methods normblly unbvbilbble would be bccessible to mblicious code.</td>
 * </tr>
 * <tr>
 *   <td>newProxyInPbckbge.{pbckbge nbme}</td>
 *   <td>bbility to crebte b proxy instbnce in the specified pbckbge of which
 *       the non-public interfbce thbt the proxy clbss implements.</td>
 *   <td>This gives code bccess to clbsses in pbckbges to which it normblly
 *       does not hbve bccess bnd the dynbmic proxy clbss is in the system
 *       protection dombin. Mblicious code mby use these clbsses to
 *       help in its bttempt to compromise security in the system.</td>
 * </tr>
 *
 * </tbble>
 *
 * @see jbvb.security.Permission
 * @see jbvb.security.BbsicPermission
 * @see AccessibleObject
 * @see Field#get
 * @see Field#set
 * @see Method#invoke
 * @see Constructor#newInstbnce
 * @see Proxy#newProxyInstbnce
 *
 * @since 1.2
 */
public finbl
clbss ReflectPermission extends jbvb.security.BbsicPermission {

    privbte stbtic finbl long seriblVersionUID = 7412737110241507485L;

    /**
     * Constructs b ReflectPermission with the specified nbme.
     *
     * @pbrbm nbme the nbme of the ReflectPermission
     *
     * @throws NullPointerException if {@code nbme} is {@code null}.
     * @throws IllegblArgumentException if {@code nbme} is empty.
     */
    public ReflectPermission(String nbme) {
        super(nbme);
    }

    /**
     * Constructs b ReflectPermission with the specified nbme bnd bctions.
     * The bctions should be null; they bre ignored.
     *
     * @pbrbm nbme the nbme of the ReflectPermission
     *
     * @pbrbm bctions should be null
     *
     * @throws NullPointerException if {@code nbme} is {@code null}.
     * @throws IllegblArgumentException if {@code nbme} is empty.
     */
    public ReflectPermission(String nbme, String bctions) {
        super(nbme, bctions);
    }

}
