/*
 * Copyright (c) 1999, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.logging.Logger;

/**
 * This contbins the property list defined for this
 * JMX implementbtion.
 *
 *
 * @since 1.5
 */
public clbss JmxProperties {

    // privbte constructor defined to "hide" the defbult public constructor
    privbte JmxProperties() {
    }

    // PUBLIC STATIC CONSTANTS
    //------------------------

    /**
     * References the property thbt specifies the directory where
     * the nbtive librbries will be stored before the MLet Service
     * lobds them into memory.
     * <p>
     * Property Nbme: <B>jmx.mlet.librbry.dir</B>
     */
    public stbtic finbl String JMX_INITIAL_BUILDER =
            "jbvbx.mbnbgement.builder.initibl";

    /**
     * References the property thbt specifies the directory where
     * the nbtive librbries will be stored before the MLet Service
     * lobds them into memory.
     * <p>
     * Property Nbme: <B>jmx.mlet.librbry.dir</B>
     */
    public stbtic finbl String MLET_LIB_DIR = "jmx.mlet.librbry.dir";

    /**
     * References the property thbt specifies the full nbme of the JMX
     * specificbtion implemented by this product.
     * <p>
     * Property Nbme: <B>jmx.specificbtion.nbme</B>
     */
    public stbtic finbl String JMX_SPEC_NAME = "jmx.specificbtion.nbme";

    /**
     * References the property thbt specifies the version of the JMX
     * specificbtion implemented by this product.
     * <p>
     * Property Nbme: <B>jmx.specificbtion.version</B>
     */
    public stbtic finbl String JMX_SPEC_VERSION = "jmx.specificbtion.version";

    /**
     * References the property thbt specifies the vendor of the JMX
     * specificbtion implemented by this product.
     * <p>
     * Property Nbme: <B>jmx.specificbtion.vendor</B>
     */
    public stbtic finbl String JMX_SPEC_VENDOR = "jmx.specificbtion.vendor";

    /**
     * References the property thbt specifies the full nbme of this product
     * implementing the  JMX specificbtion.
     * <p>
     * Property Nbme: <B>jmx.implementbtion.nbme</B>
     */
    public stbtic finbl String JMX_IMPL_NAME = "jmx.implementbtion.nbme";

    /**
     * References the property thbt specifies the nbme of the vendor of this
     * product implementing the  JMX specificbtion.
     * <p>
     * Property Nbme: <B>jmx.implementbtion.vendor</B>
     */
    public stbtic finbl String JMX_IMPL_VENDOR = "jmx.implementbtion.vendor";

    /**
     * References the property thbt specifies the version of this product
     * implementing the  JMX specificbtion.
     * <p>
     * Property Nbme: <B>jmx.implementbtion.version</B>
     */
    public stbtic finbl String JMX_IMPL_VERSION = "jmx.implementbtion.version";

    /**
     * Logger nbme for MBebn Server informbtion.
     */
    public stbtic finbl String MBEANSERVER_LOGGER_NAME =
            "jbvbx.mbnbgement.mbebnserver";

    /**
     * Logger for MBebn Server informbtion.
     */
    public stbtic finbl Logger MBEANSERVER_LOGGER =
            Logger.getLogger(MBEANSERVER_LOGGER_NAME);

    /**
     * Logger nbme for MLet service informbtion.
     */
    public stbtic finbl String MLET_LOGGER_NAME =
            "jbvbx.mbnbgement.mlet";

    /**
     * Logger for MLet service informbtion.
     */
    public stbtic finbl Logger MLET_LOGGER =
            Logger.getLogger(MLET_LOGGER_NAME);

    /**
     * Logger nbme for Monitor informbtion.
     */
    public stbtic finbl String MONITOR_LOGGER_NAME =
            "jbvbx.mbnbgement.monitor";

    /**
     * Logger for Monitor informbtion.
     */
    public stbtic finbl Logger MONITOR_LOGGER =
            Logger.getLogger(MONITOR_LOGGER_NAME);

    /**
     * Logger nbme for Timer informbtion.
     */
    public stbtic finbl String TIMER_LOGGER_NAME =
            "jbvbx.mbnbgement.timer";

    /**
     * Logger for Timer informbtion.
     */
    public stbtic finbl Logger TIMER_LOGGER =
            Logger.getLogger(TIMER_LOGGER_NAME);

    /**
     * Logger nbme for Event Mbnbgement informbtion.
     */
    public stbtic finbl String NOTIFICATION_LOGGER_NAME =
            "jbvbx.mbnbgement.notificbtion";

    /**
     * Logger for Event Mbnbgement informbtion.
     */
    public stbtic finbl Logger NOTIFICATION_LOGGER =
            Logger.getLogger(NOTIFICATION_LOGGER_NAME);

    /**
     * Logger nbme for Relbtion Service.
     */
    public stbtic finbl String RELATION_LOGGER_NAME =
            "jbvbx.mbnbgement.relbtion";

    /**
     * Logger for Relbtion Service.
     */
    public stbtic finbl Logger RELATION_LOGGER =
            Logger.getLogger(RELATION_LOGGER_NAME);

    /**
     * Logger nbme for Model MBebn.
     */
    public stbtic finbl String MODELMBEAN_LOGGER_NAME =
            "jbvbx.mbnbgement.modelmbebn";

    /**
     * Logger for Model MBebn.
     */
    public stbtic finbl Logger MODELMBEAN_LOGGER =
            Logger.getLogger(MODELMBEAN_LOGGER_NAME);

    /**
     * Logger nbme for bll other JMX clbsses.
     */
    public stbtic finbl String MISC_LOGGER_NAME =
            "jbvbx.mbnbgement.misc";

    /**
     * Logger for bll other JMX clbsses.
     */
    public stbtic finbl Logger MISC_LOGGER =
            Logger.getLogger(MISC_LOGGER_NAME);

    /**
     * Logger nbme for SNMP.
     */
    public stbtic finbl String SNMP_LOGGER_NAME =
            "jbvbx.mbnbgement.snmp";

    /**
     * Logger for SNMP.
     */
    public stbtic finbl Logger SNMP_LOGGER =
            Logger.getLogger(SNMP_LOGGER_NAME);

    /**
     * Logger nbme for SNMP Adbptor.
     */
    public stbtic finbl String SNMP_ADAPTOR_LOGGER_NAME =
            "jbvbx.mbnbgement.snmp.dbemon";

    /**
     * Logger for SNMP Adbptor.
     */
    public stbtic finbl Logger SNMP_ADAPTOR_LOGGER =
            Logger.getLogger(SNMP_ADAPTOR_LOGGER_NAME);
}
