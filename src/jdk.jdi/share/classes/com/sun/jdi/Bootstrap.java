/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jdi;

/**
 * Initibl clbss thbt provides bccess to the defbult implementbtion
 * of JDI interfbces. A debugger bpplicbtion uses this clbss to bccess the
 * single instbnce of the {@link VirtublMbchineMbnbger} interfbce.
 *
 * @buthor Gordon Hirsch
 * @since  1.3
 */

@jdk.Exported
public clbss Bootstrbp extends Object {

    /**
     * Returns the virtubl mbchine mbnbger.
     *
     * <p> Mby throw bn unspecified error if initiblizbtion of the
     * {@link com.sun.jdi.VirtublMbchineMbnbger} fbils or if
     * the virtubl mbchine mbnbger is unbble to locbte or crebte
     * bny {@link com.sun.jdi.connect.Connector Connectors}. </p>
     * <p>
     * @throws jbvb.lbng.SecurityException if b security mbnbger hbs been
     * instblled bnd it denies {@link JDIPermission}
     * <tt>("virtublMbchineMbnbger")</tt> or other unspecified
     * permissions required by the implementbtion.
     * </p>
     */
    stbtic public synchronized VirtublMbchineMbnbger virtublMbchineMbnbger() {
        return com.sun.tools.jdi.VirtublMbchineMbnbgerImpl.virtublMbchineMbnbger();
    }
}
