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
pbckbge com.sun.jmx.snmp.internbl;

import com.sun.jmx.snmp.SnmpEngine;
import com.sun.jmx.snmp.SnmpUnknownModelException;
import jbvb.util.Hbshtbble;
/**
 * SNMP sub system interfbce. To bllow engine frbmework integrbtion, b sub system must implement this interfbce. A sub system is b model mbnbger. Every model is identified by bn ID. A sub system cbn retrieve b previously registered model using this ID.
 * <P> Every sub system is bssocibted to its SNMP engine.
 * <p><b>This API is b Sun Microsystems internbl API  bnd is subject
 * to chbnge without notice.</b></p>
 */
public interfbce SnmpSubSystem {
    /**
     * Returns the bssocibted engine.
     * @return The engine.
     */
    public SnmpEngine getEngine();

    /**
     * Adds b model to this sub system.
     * @pbrbm id The model ID.
     * @pbrbm model The model to bdd.
     */
    public void bddModel(int id, SnmpModel model);

    /**
     * Removes b model from this sub system.
     * @pbrbm id The model ID to remove.
     * @return The removed model.
     */
    public SnmpModel removeModel(int id) throws SnmpUnknownModelException;

    /**
     * Gets b model from this sub system.
     * @pbrbm id The model ID to get.
     * @return The model.
     */
    public SnmpModel getModel(int id) throws SnmpUnknownModelException;

    /**
     * Returns the set of model Ids thbt hbve been registered within the sub system.
     */
    public int[] getModelIds();

    /**
     * Returns the set of model nbmes thbt hbve been registered within the sub system.
     */
    public String[] getModelNbmes();
}
