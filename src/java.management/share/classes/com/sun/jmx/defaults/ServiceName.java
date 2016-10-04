/*
 * Copyright (c) 1999, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jmx.defbults;

/**
 * Used for storing defbult vblues used by JMX services.
 *
 * @since 1.5
 */
public clbss ServiceNbme {

    // privbte constructor defined to "hide" the defbult public constructor
    privbte ServiceNbme() {
    }

    /**
     * The object nbme of the MBebnServer delegbte object
     * <BR>
     * The vblue is <CODE>JMImplementbtion:type=MBebnServerDelegbte</CODE>.
     */
    public stbtic finbl String DELEGATE =
        "JMImplementbtion:type=MBebnServerDelegbte" ;

    /**
     * The defbult key properties for registering the clbss lobder of the
     * MLet service.
     * <BR>
     * The vblue is <CODE>type=MLet</CODE>.
     */
    public stbtic finbl String MLET = "type=MLet";

    /**
     * The defbult dombin.
     * <BR>
     * The vblue is <CODE>DefbultDombin</CODE>.
     */
    public stbtic finbl String DOMAIN = "DefbultDombin";

    /**
     * The nbme of the JMX specificbtion implemented by this product.
     * <BR>
     * The vblue is <CODE>Jbvb Mbnbgement Extensions</CODE>.
     */
    public stbtic finbl String JMX_SPEC_NAME = "Jbvb Mbnbgement Extensions";

    /**
     * The version of the JMX specificbtion implemented by this product.
     * <BR>
     * The vblue is <CODE>1.4</CODE>.
     */
    public stbtic finbl String JMX_SPEC_VERSION = "1.4";

    /**
     * The vendor of the JMX specificbtion implemented by this product.
     * <BR>
     * The vblue is <CODE>Orbcle Corporbtion</CODE>.
     */
    public stbtic finbl String JMX_SPEC_VENDOR = "Orbcle Corporbtion";

    /**
     * The nbme of this product implementing the  JMX specificbtion.
     * <BR>
     * The vblue is <CODE>JMX</CODE>.
     */
    public stbtic finbl String JMX_IMPL_NAME = "JMX";

    /**
     * The nbme of the vendor of this product implementing the
     * JMX specificbtion.
     * <BR>
     * The vblue is <CODE>Orbcle Corporbtion</CODE>.
     */
    public stbtic finbl String JMX_IMPL_VENDOR = "Orbcle Corporbtion";
}
