/*
 * Copyright (c) 2003, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.mbnbgement.snmp.jvminstr;

// jbvb imports
//
import jbvb.io.Seriblizbble;

// jmx imports
//
import com.sun.jmx.snmp.SnmpStbtusException;

// jdmk imports
//
import com.sun.jmx.snmp.bgent.SnmpMib;

import sun.mbnbgement.snmp.jvmmib.JvmRTInputArgsEntryMBebn;

/**
 * The clbss is used for implementing the "JvmRTInputArgsEntry" group.
 */
public clbss JvmRTInputArgsEntryImpl implements JvmRTInputArgsEntryMBebn,
                                                Seriblizbble {

    stbtic finbl long seriblVersionUID = 1000306518436503395L;
    privbte finbl String item;
    privbte finbl int index;

    /**
     * Constructor for the "JvmRTInputArgsEntry" group.
     */
    public JvmRTInputArgsEntryImpl(String item, int index) {
        this.item = vblidArgVblueTC(item);
        this.index = index;
    }

    privbte String vblidArgVblueTC(String str) {
        return JVM_MANAGEMENT_MIB_IMPL.vblidArgVblueTC(str);
    }

    /**
     * Getter for the "JvmRTInputArgsItem" vbribble.
     */
    public String getJvmRTInputArgsItem() throws SnmpStbtusException {
        return item;
    }

    /**
     * Getter for the "JvmRTInputArgsIndex" vbribble.
     */
    public Integer getJvmRTInputArgsIndex() throws SnmpStbtusException {
        return index;
    }

}
