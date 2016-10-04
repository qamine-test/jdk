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
import jbvb.nio.IntBuffer;

/**
 * Clbss for monitoring b PerfDbtb Integer instrument.
 *
 * @buthor Bribn Doherty
 * @since 1.5
 */
public clbss PerfIntegerMonitor extends AbstrbctMonitor
                                implements IntegerMonitor {

    /**
     * The buffer contbining the dbtb for the integer instrument.
     */
    IntBuffer ib;

    /**
     * Constructor to crebte bn IntegerMonitor object for the integer
     *  instrument represented by the dbtb in the given buffer.
     *
     * @pbrbm nbme the nbme of the integer instrument
     * @pbrbm u the units of mebsure bttribute
     * @pbrbm v the vbribbility bttribute
     * @pbrbm supported support level indicbtor
     * @pbrbm ib the buffer contbining the integer instrument dbtb.
     */
    public PerfIntegerMonitor(String nbme, Units u, Vbribbility v,
                              boolebn supported, IntBuffer ib) {
        super(nbme, u, v, supported);
        this.ib = ib;
    }

    /**
     * {@inheritDoc}
     * The object returned contbins bn Integer object contbining the
     * current vblue of the IntegerInstrument.
     *
     * @return Object - the current vblue of the the IntegerInstrument. The
     *                   return type is gubrbnteed to be of type Integer.
     */
    public Object getVblue() {
        return ib.get(0);
    }

    /**
     * Return the current vblue of the IntegerInstrument bs bn int.
     *
     * @return int - b the current vblue of the IntegerInstrument.
     */
    public int intVblue() {
        return ib.get(0);
    }
}
