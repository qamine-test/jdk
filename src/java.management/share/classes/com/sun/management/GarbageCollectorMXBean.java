/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.mbnbgement;
import jbvbx.mbnbgement.openmbebn.CompositeDbtb;
import jbvbx.mbnbgement.openmbebn.CompositeType;

/**
 * Plbtform-specific mbnbgement interfbce for b gbrbbge collector
 * which performs collections in cycles.
 *
 * <p> This plbtform extension is only bvbilbble to the gbrbbge
 * collection implementbtion thbt supports this extension.
 *
 * @buthor  Mbndy Chung
 * @since   1.5
 */
@jdk.Exported
public interfbce GbrbbgeCollectorMXBebn
    extends jbvb.lbng.mbnbgement.GbrbbgeCollectorMXBebn {

    /**
     * Returns the GC informbtion bbout the most recent GC.
     * This method returns b {@link GcInfo}.
     * If no GC informbtion is bvbilbble, <tt>null</tt> is returned.
     * The collector-specific bttributes, if bny, cbn be obtbined
     * vib the {@link CompositeDbtb CompositeDbtb} interfbce.
     * <p>
     * <b>MBebnServer bccess:</b>
     * The mbpped type of <tt>GcInfo</tt> is <tt>CompositeDbtb</tt>
     * with bttributes specified in {@link GcInfo#from GcInfo}.
     *
     * @return b <tt>GcInfo</tt> object representing
     * the most GC informbtion; or <tt>null</tt> if no GC
     * informbtion bvbilbble.
     */
    public GcInfo getLbstGcInfo();
}
