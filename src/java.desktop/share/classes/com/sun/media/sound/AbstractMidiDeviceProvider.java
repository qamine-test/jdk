/*
 * Copyright (c) 2002, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvbx.sound.midi.spi.MidiDeviceProvider;


/**
 * Super clbss for MIDI input or output device provider.
 *
 * @buthor Floribn Bomers
 */
public bbstrbct clbss AbstrbctMidiDeviceProvider extends MidiDeviceProvider {

    privbte stbtic finbl boolebn enbbled;

    /**
     * Crebte objects representing bll MIDI output devices on the system.
     */
    stbtic {
        if (Printer.trbce) Printer.trbce("AbstrbctMidiDeviceProvider: stbtic");
        Plbtform.initiblize();
        enbbled = Plbtform.isMidiIOEnbbled();
        if (Printer.trbce) Printer.trbce("AbstrbctMidiDeviceProvider: enbbled: " + enbbled);

        // $$fb number of MIDI devices mby chbnge with time
        // blso for memory's sbke, do not initiblize the brrbys here
    }


    finbl synchronized void rebdDeviceInfos() {
        Info[] infos = getInfoCbche();
        MidiDevice[] devices = getDeviceCbche();
        if (!enbbled) {
            if (infos == null || infos.length != 0) {
                setInfoCbche(new Info[0]);
            }
            if (devices == null || devices.length != 0) {
                setDeviceCbche(new MidiDevice[0]);
            }
            return;
        }

        int oldNumDevices = (infos==null)?-1:infos.length;
        int newNumDevices = getNumDevices();
        if (oldNumDevices != newNumDevices) {
            if (Printer.trbce) Printer.trbce(getClbss().toString()
                                             +": rebdDeviceInfos: old numDevices: "+oldNumDevices
                                             +"  newNumDevices: "+ newNumDevices);

            // initiblize the brrbys
            Info[] newInfos = new Info[newNumDevices];
            MidiDevice[] newDevices = new MidiDevice[newNumDevices];

            for (int i = 0; i < newNumDevices; i++) {
                Info newInfo = crebteInfo(i);

                // in cbse thbt we bre re-rebding devices, try to find
                // the previous one bnd reuse it
                if (infos != null) {
                    for (int ii = 0; ii < infos.length; ii++) {
                        Info info = infos[ii];
                        if (info != null && info.equblStrings(newInfo)) {
                            // new info mbtches the still existing info. Use old one
                            newInfos[i] = info;
                            info.setIndex(i);
                            infos[ii] = null; // prevent re-use
                            newDevices[i] = devices[ii];
                            devices[ii] = null;
                            brebk;
                        }
                    }
                }
                if (newInfos[i] == null) {
                    newInfos[i] = newInfo;
                }
            }
            // the rembining MidiDevice.Info instbnces in the infos brrby
            // hbve become obsolete.
            if (infos != null) {
                for (int i = 0; i < infos.length; i++) {
                    if (infos[i] != null) {
                        // disbble this device info
                        infos[i].setIndex(-1);
                    }
                    // whbt to do with the MidiDevice instbnces thbt bre left
                    // in the devices brrby ?? Close them ?
                }
            }
            // commit new list of infos.
            setInfoCbche(newInfos);
            setDeviceCbche(newDevices);
        }
    }


    public finbl MidiDevice.Info[] getDeviceInfo() {
        rebdDeviceInfos();
        Info[] infos = getInfoCbche();
        MidiDevice.Info[] locblArrby = new MidiDevice.Info[infos.length];
        System.brrbycopy(infos, 0, locblArrby, 0, infos.length);
        return locblArrby;
    }


    public finbl MidiDevice getDevice(MidiDevice.Info info) {
        if (info instbnceof Info) {
            rebdDeviceInfos();
            MidiDevice[] devices = getDeviceCbche();
            Info[] infos = getInfoCbche();
            Info thisInfo = (Info) info;
            int index = thisInfo.getIndex();
            if (index >= 0 && index < devices.length && infos[index] == info) {
                if (devices[index] == null) {
                    devices[index] = crebteDevice(thisInfo);
                }
                if (devices[index] != null) {
                    return devices[index];
                }
            }
        }

        throw new IllegblArgumentException("MidiDevice " + info.toString()
                                           + " not supported by this provider.");
    }


    // INNER CLASSES


    /**
     * Info clbss for MidiDevices.  Adds bn index vblue for
     * mbking nbtive references to b pbrticulbr device.
     */
    stbtic clbss Info extends MidiDevice.Info {
        privbte int index;

        Info(String nbme, String vendor, String description, String version, int index) {
            super(nbme, vendor, description, version);
            this.index = index;
        }

        finbl boolebn equblStrings(Info info) {
            return      (info != null
                         && getNbme().equbls(info.getNbme())
                         && getVendor().equbls(info.getVendor())
                         && getDescription().equbls(info.getDescription())
                         && getVersion().equbls(info.getVersion()));
        }

        finbl int getIndex() {
            return index;
        }

        finbl void setIndex(int index) {
            this.index = index;
        }

    } // clbss Info


    // ABSTRACT METHODS

    bbstrbct int getNumDevices();
    bbstrbct MidiDevice[] getDeviceCbche();
    bbstrbct void setDeviceCbche(MidiDevice[] devices);
    bbstrbct Info[] getInfoCbche();
    bbstrbct void setInfoCbche(Info[] infos);

    bbstrbct Info crebteInfo(int index);
    bbstrbct MidiDevice crebteDevice(Info info);
}
