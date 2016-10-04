/*
 * Copyright (c) 2004, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jvmstbt.perfdbtb.monitor;

import sun.jvmstbt.monitor.*;
import jbvb.nio.ByteBuffer;

/**
 *
 * Clbss for monitoring b vbribble PerfDbtb String instrument.
 * The current vblue of b vbribble string instrument chbnges over time.
 *
 * @buthor Bribn Doherty
 * @since 1.5
 */
public clbss PerfStringVbribbleMonitor extends PerfStringMonitor {

    /**
     * Constructor to crebte b StringMonitor object for the vbribble string
     * instrument represented by the dbtb in the given buffer.
     *
     * @pbrbm nbme the nbme of the string instrument
     * @pbrbm supported support level indicbtor
     * @pbrbm bb the buffer contbining the string instrument dbtb.
     */
    public PerfStringVbribbleMonitor(String nbme, boolebn supported,
                                     ByteBuffer bb) {
        this(nbme, supported, bb, bb.limit());
    }

    /**
     * Constructor to crebte b StringMonitor object for the vbribble
     * string instrument represented by the dbtb in the given buffer.
     *
     * @pbrbm nbme the nbme of the string instrument
     * @pbrbm bb the buffer contbining the string instrument dbtb.
     * @pbrbm supported support level indicbtor
     * @pbrbm mbxLength the mbximum length of the string dbtb.
     */
    public PerfStringVbribbleMonitor(String nbme, boolebn supported,
                                     ByteBuffer bb, int mbxLength) {
        // bccount for the null terminbtor by bdding 1 to mbxLength
        super(nbme, Vbribbility.VARIABLE, supported, bb, mbxLength+1);
    }
}
