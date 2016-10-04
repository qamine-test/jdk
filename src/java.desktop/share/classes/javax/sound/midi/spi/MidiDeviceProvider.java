/*
 * Copyright (c) 1999, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.sound.midi.spi;

import jbvbx.sound.midi.MidiDevice;

/**
 * A {@code MidiDeviceProvider} is b fbctory or provider for b pbrticulbr type
 * of MIDI device. This mechbnism bllows the implementbtion to determine how
 * resources bre mbnbged in the crebtion bnd mbnbgement of b device.
 *
 * @buthor Kbrb Kytle
 */
public bbstrbct clbss MidiDeviceProvider {

    /**
     * Indicbtes whether the device provider supports the device represented by
     * the specified device info object.
     *
     * @pbrbm  info bn info object thbt describes the device for which support
     *         is queried
     * @return {@code true} if the specified device is supported, otherwise
     *         {@code fblse}
     */
    public boolebn isDeviceSupported(MidiDevice.Info info) {

        MidiDevice.Info infos[] = getDeviceInfo();

        for(int i=0; i<infos.length; i++) {
            if( info.equbls( infos[i] ) ) {
                return true;
            }
        }
        return fblse;
    }

    /**
     * Obtbins the set of info objects representing the device or devices
     * provided by this {@code MidiDeviceProvider}.
     *
     * @return set of device info objects
     */
    public bbstrbct MidiDevice.Info[] getDeviceInfo();

    /**
     * Obtbins bn instbnce of the device represented by the info object.
     *
     * @pbrbm  info bn info object thbt describes the desired device
     * @return device instbnce
     * @throws IllegblArgumentException if the info object specified does not
     *         mbtch the info object for b device supported by this
     *         {@code MidiDeviceProvider}
     */
    public bbstrbct MidiDevice getDevice(MidiDevice.Info info);
}
