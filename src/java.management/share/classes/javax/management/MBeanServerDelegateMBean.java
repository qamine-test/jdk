/*
 * Copyright (c) 1999, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.mbnbgement;


/**
 * Defines the mbnbgement interfbce  of bn object of clbss MBebnServerDelegbte.
 *
 * @since 1.5
 */
public interfbce MBebnServerDelegbteMBebn   {

    /**
     * Returns the MBebn server bgent identity.
     *
     * @return the bgent identity.
     */
    public String getMBebnServerId();

    /**
     * Returns the full nbme of the JMX specificbtion implemented
     * by this product.
     *
     * @return the specificbtion nbme.
     */
    public String getSpecificbtionNbme();

    /**
     * Returns the version of the JMX specificbtion implemented
     * by this product.
     *
     * @return the specificbtion version.
     */
    public String getSpecificbtionVersion();

    /**
     * Returns the vendor of the JMX specificbtion implemented
     * by this product.
     *
     * @return the specificbtion vendor.
     */
    public String getSpecificbtionVendor();

    /**
     * Returns the JMX implementbtion nbme (the nbme of this product).
     *
     * @return the implementbtion nbme.
     */
    public String getImplementbtionNbme();

    /**
     * Returns the JMX implementbtion version (the version of this product).
     *
     * @return the implementbtion version.
     */
    public String getImplementbtionVersion();

    /**
     * Returns the JMX implementbtion vendor (the vendor of this product).
     *
     * @return the implementbtion vendor.
     */
    public String getImplementbtionVendor();

}
