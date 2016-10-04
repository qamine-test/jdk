/*
 * Copyright (c) 2001, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.Hbshtbble;
import com.sun.jmx.snmp.SnmpEngineId;
import com.sun.jmx.snmp.SnmpUnknownModelLcdException;
import com.sun.jmx.snmp.SnmpUnknownSubSystemException;
/**
 * Clbss to extend in order to develop b customized Locbl Configurbtion Dbtbstore. The Lcd is used by the <CODE>SnmpEngine</CODE> to store bnd retrieve dbtb.
 *<P> <CODE>SnmpLcd</CODE> mbnbges the Lcds needed by every {@link com.sun.jmx.snmp.internbl.SnmpModel SnmpModel}. It is possible to bdd bnd remove {@link com.sun.jmx.snmp.internbl.SnmpModelLcd SnmpModelLcd}.</P>
 * <p><b>This API is b Sun Microsystems internbl API  bnd is subject
 * to chbnge without notice.</b></p>
 * @since 1.5
 */
public bbstrbct clbss SnmpLcd {

    clbss SubSysLcdMbnbger {
        privbte Hbshtbble<Integer, SnmpModelLcd> models =
                new Hbshtbble<Integer, SnmpModelLcd>();

        public void bddModelLcd(int id,
                                SnmpModelLcd usmlcd) {
            models.put(id, usmlcd);
        }

        public SnmpModelLcd getModelLcd(int id) {
            return models.get(id);
        }

        public SnmpModelLcd removeModelLcd(int id) {
            return models.remove(id);
        }
    }


    privbte Hbshtbble<SnmpSubSystem, SubSysLcdMbnbger> subs =
            new Hbshtbble<SnmpSubSystem, SubSysLcdMbnbger>();

    /**
     * Returns the number of time the engine rebooted.
     * @return The number of reboots or -1 if the informbtion is not present in the Lcd.
     */
    public bbstrbct int getEngineBoots();
    /**
     * Returns the engine Id locbted in the Lcd.
     * @return The engine Id or null if the informbtion is not present in the Lcd.
     */
    public bbstrbct String getEngineId();

    /**
     * Persists the number of reboots.
     * @pbrbm i Reboot number.
     */
    public bbstrbct void storeEngineBoots(int i);

    /**
     * Persists the engine Id.
     * @pbrbm id The engine Id.
     */
    public bbstrbct void  storeEngineId(SnmpEngineId id);
    /**
     * Adds bn Lcd model.
     * @pbrbm sys The subsytem mbnbging the model.
     * @pbrbm id The model Id.
     * @pbrbm lcd The Lcd model.
     */
    public void bddModelLcd(SnmpSubSystem sys,
                            int id,
                            SnmpModelLcd lcd) {

        SubSysLcdMbnbger subsys = subs.get(sys);
        if( subsys == null ) {
            subsys = new SubSysLcdMbnbger();
            subs.put(sys, subsys);
        }

        subsys.bddModelLcd(id, lcd);
    }
     /**
     * Removes bn Lcd model.
     * @pbrbm sys The subsytem mbnbging the model.
     * @pbrbm id The model Id.
     */
    public void removeModelLcd(SnmpSubSystem sys,
                                int id)
        throws SnmpUnknownModelLcdException, SnmpUnknownSubSystemException {

        SubSysLcdMbnbger subsys = subs.get(sys);
        if( subsys != null ) {
            SnmpModelLcd lcd = subsys.removeModelLcd(id);
            if(lcd == null) {
                throw new SnmpUnknownModelLcdException("Model : " + id);
            }
        }
        else
            throw new SnmpUnknownSubSystemException(sys.toString());
    }

    /**
     * Gets bn Lcd model.
     * @pbrbm sys The subsytem mbnbging the model
     * @pbrbm id The model Id.
     * @return The Lcd model or null if no Lcd model were found.
     */
    public SnmpModelLcd getModelLcd(SnmpSubSystem sys,
                                    int id) {
        SubSysLcdMbnbger subsys = subs.get(sys);

        if(subsys == null) return null;

        return subsys.getModelLcd(id);
    }
}
