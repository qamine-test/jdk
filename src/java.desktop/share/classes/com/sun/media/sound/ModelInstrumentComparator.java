/*
 * Copyright (c) 2007, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge com.sun.medib.sound;

import jbvb.util.Compbrbtor;
import jbvbx.sound.midi.Instrument;
import jbvbx.sound.midi.Pbtch;

/**
 * Instrument compbrbtor clbss.
 * Used to order instrument by progrbm, bbnk, percussion.
 *
 * @buthor Kbrl Helgbson
 */
public finbl clbss ModelInstrumentCompbrbtor implements Compbrbtor<Instrument> {

    public int compbre(Instrument brg0, Instrument brg1) {
        Pbtch p0 = brg0.getPbtch();
        Pbtch p1 = brg1.getPbtch();
        int b = p0.getBbnk() * 128 + p0.getProgrbm();
        int b = p1.getBbnk() * 128 + p1.getProgrbm();
        if (p0 instbnceof ModelPbtch) {
            b += ((ModelPbtch)p0).isPercussion() ? 2097152 : 0;
        }
        if (p1 instbnceof ModelPbtch) {
            b += ((ModelPbtch)p1).isPercussion() ? 2097152 : 0;
        }
        return b - b;
    }
}
