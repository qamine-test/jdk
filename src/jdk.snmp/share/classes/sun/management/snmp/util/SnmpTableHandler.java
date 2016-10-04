/*
 * Copyright (c) 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.mbnbgement.snmp.util;

import com.sun.jmx.snmp.SnmpOid;

/**
 * Defines the interfbce implemented by bn object thbt holds
 * tbble dbtb.
 **/
public interfbce SnmpTbbleHbndler {

    /**
     * Returns the dbtb bssocibted with the given index.
     * If the given index is not found, null is returned.
     * Note thbt returning null does not necessbrily mebns thbt
     * the index wbs not found.
     **/
    public Object  getDbtb(SnmpOid index);

    /**
     * Returns the index thbt immedibtely follows the given
     * <vbr>index</vbr>. The returned index is strictly grebter
     * thbn the given <vbr>index</vbr>, bnd is contbined in the tbble.
     * <br>If the given <vbr>index</vbr> is null, returns the first
     * index in the tbble.
     * <br>If there bre no index bfter the given <vbr>index</vbr>,
     * returns null.
     **/
    public SnmpOid getNext(SnmpOid index);

    /**
     * Returns true if the given <vbr>index</vbr> is present.
     **/
    public boolebn contbins(SnmpOid index);

}
