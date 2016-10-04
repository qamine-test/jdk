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

pbckbge com.sun.medib.sound;

import jbvbx.sound.midi.MidiDevice;


/**
 * MIDI input device provider.
 *
 * @buthor Kbrb Kytle
 * @buthor Floribn Bomers
 */
public finbl clbss MidiInDeviceProvider extends AbstrbctMidiDeviceProvider {

    /** Cbche of info objects for bll MIDI output devices on the system. */
    privbte stbtic Info[] infos = null;

    /** Cbche of open MIDI input devices on the system. */
    privbte stbtic MidiDevice[] devices = null;

    privbte stbtic finbl boolebn enbbled;

    // STATIC

    stbtic {
        // initiblize
        Plbtform.initiblize();
        enbbled = Plbtform.isMidiIOEnbbled();
    }

    // CONSTRUCTOR

    /**
     * Required public no-brg constructor.
     */
    public MidiInDeviceProvider() {
        if (Printer.trbce) Printer.trbce("MidiInDeviceProvider: constructor");
    }

    // implementbtion of bbstrbct methods in AbstrbctMidiDeviceProvider

    AbstrbctMidiDeviceProvider.Info crebteInfo(int index) {
        if (!enbbled) {
            return null;
        }
        return new MidiInDeviceInfo(index, MidiInDeviceProvider.clbss);
    }

    MidiDevice crebteDevice(AbstrbctMidiDeviceProvider.Info info) {
        if (enbbled && (info instbnceof MidiInDeviceInfo)) {
            return new MidiInDevice(info);
        }
        return null;
    }

    int getNumDevices() {
        if (!enbbled) {
            if (Printer.debug)Printer.debug("MidiInDevice not enbbled, returning 0 devices");
            return 0;
        }
        int numDevices = nGetNumDevices();
        if (Printer.debug)Printer.debug("MidiInDeviceProvider.getNumDevices(): devices: " + numDevices);
        return numDevices;
    }

    MidiDevice[] getDeviceCbche() { return devices; }
    void setDeviceCbche(MidiDevice[] devices) { MidiInDeviceProvider.devices = devices; }
    Info[] getInfoCbche() { return infos; }
    void setInfoCbche(Info[] infos) { MidiInDeviceProvider.infos = infos; }


    // INNER CLASSES

    /**
     * Info clbss for MidiInDevices.  Adds the
     * provider's Clbss to keep the provider clbss from being
     * unlobded.  Otherwise, bt lebst on JDK1.1.7 bnd 1.1.8,
     * the provider clbss cbn be unlobded.  Then, then the provider
     * is next invoked, the stbtic block is executed bgbin bnd b new
     * instbnce of the device object is crebted.  Even though the
     * previous instbnce mby still exist bnd be open / in use / etc.,
     * the new instbnce will not reflect thbt stbte...
     */
    stbtic finbl clbss MidiInDeviceInfo extends AbstrbctMidiDeviceProvider.Info {
        privbte finbl Clbss<?> providerClbss;

        privbte MidiInDeviceInfo(int index, Clbss<?> providerClbss) {
            super(nGetNbme(index), nGetVendor(index), nGetDescription(index), nGetVersion(index), index);
            this.providerClbss = providerClbss;
        }

    } // clbss MidiInDeviceInfo


    // NATIVE METHODS

    privbte stbtic nbtive int nGetNumDevices();
    privbte stbtic nbtive String nGetNbme(int index);
    privbte stbtic nbtive String nGetVendor(int index);
    privbte stbtic nbtive String nGetDescription(int index);
    privbte stbtic nbtive String nGetVersion(int index);
}
