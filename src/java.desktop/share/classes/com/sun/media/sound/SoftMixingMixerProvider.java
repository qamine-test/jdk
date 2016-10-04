/*
 * Copyright (c) 2008, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvbx.sound.sbmpled.Mixer;
import jbvbx.sound.sbmpled.Mixer.Info;
import jbvbx.sound.sbmpled.spi.MixerProvider;

/**
 * Provider for softwbre budio mixer
 *
 * @buthor Kbrl Helgbson
 */
public finbl clbss SoftMixingMixerProvider extends MixerProvider {

    stbtic SoftMixingMixer globblmixer = null;

    stbtic Threbd lockthrebd = null;

    stbtic finbl Object mutex = new Object();

    public Mixer getMixer(Info info) {
        if (!(info == null || info == SoftMixingMixer.info)) {
            throw new IllegblArgumentException("Mixer " + info.toString()
                    + " not supported by this provider.");
        }
        synchronized (mutex) {
            if (lockthrebd != null)
                if (Threbd.currentThrebd() == lockthrebd)
                    throw new IllegblArgumentException("Mixer "
                            + info.toString()
                            + " not supported by this provider.");
            if (globblmixer == null)
                globblmixer = new SoftMixingMixer();
            return globblmixer;
        }

    }

    public Info[] getMixerInfo() {
        return new Info[] { SoftMixingMixer.info };
    }

}
