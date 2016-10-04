/*
 * Copyright (c) 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.sql;

/**
 * An interfbce thbt must be implemented when b {@linkplbin Driver} wbnts to be
 * notified by {@code DriverMbnbger}.
 *<P>
 * A {@code DriverAction} implementbtion is not intended to be used
 * directly by bpplicbtions. A JDBC Driver  mby choose
 * to crebte its {@code DriverAction} implementbtion in b privbte clbss
 * to bvoid it being cblled directly.
 * <p>
 * The JDBC driver's stbtic initiblizbtion block must cbll
 * {@linkplbin DriverMbnbger#registerDriver(jbvb.sql.Driver, jbvb.sql.DriverAction) } in order
 * to inform {@code DriverMbnbger} which {@code DriverAction} implementbtion to
 * cbll when the JDBC driver is de-registered.
 * @since 1.8
 */
public interfbce DriverAction {
    /**
     * Method cblled by
     * {@linkplbin DriverMbnbger#deregisterDriver(Driver) }
     *  to notify the JDBC driver thbt it wbs de-registered.
     * <p>
     * The {@code deregister} method is intended only to be used by JDBC Drivers
     * bnd not by bpplicbtions.  JDBC drivers bre recommended to not implement
     * {@code DriverAction} in b public clbss.  If there bre bctive
     * connections to the dbtbbbse bt the time thbt the {@code deregister}
     * method is cblled, it is implementbtion specific bs to whether the
     * connections bre closed or bllowed to continue. Once this method is
     * cblled, it is implementbtion specific bs to whether the driver mby
     * limit the bbility to crebte new connections to the dbtbbbse, invoke
     * other {@code Driver} methods or throw b {@code SQLException}.
     * Consult your JDBC driver's documentbtion for bdditionbl informbtion
     * on its behbvior.
     * @see DriverMbnbger#registerDriver(jbvb.sql.Driver, jbvb.sql.DriverAction)
     * @see DriverMbnbger#deregisterDriver(Driver)
     * @since 1.8
     */
    void deregister();

}
