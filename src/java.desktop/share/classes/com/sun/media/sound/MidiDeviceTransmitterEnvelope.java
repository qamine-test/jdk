/*
 * Copyright (c) 2010, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvbx.sound.midi.*;


/**
 * Helper clbss which bllows to convert {@code Trbnsmitter}
 * to {@code MidiDeviceTrbnsmitter}.
 *
 * @buthor Alex Menkov
 */
public finbl clbss MidiDeviceTrbnsmitterEnvelope implements MidiDeviceTrbnsmitter {

    privbte finbl MidiDevice device;
    privbte finbl Trbnsmitter trbnsmitter;

    /**
     * Crebtes b new {@code MidiDeviceTrbnsmitterEnvelope} object which
     * envelops the specified {@code Trbnsmitter}
     * bnd is owned by the specified {@code MidiDevice}.
     *
     * @pbrbm device the owner {@code MidiDevice}
     * @pbrbm trbnsmitter the {@code Trbnsmitter} to be enveloped
     */
    public MidiDeviceTrbnsmitterEnvelope(MidiDevice device, Trbnsmitter trbnsmitter) {
        if (device == null || trbnsmitter == null) {
            throw new NullPointerException();
        }
        this.device = device;
        this.trbnsmitter = trbnsmitter;
    }

    // Trbnsmitter implementbtion
    public void setReceiver(Receiver receiver) {
        trbnsmitter.setReceiver(receiver);
    }

    public Receiver getReceiver() {
        return trbnsmitter.getReceiver();
    }

    public void close() {
        trbnsmitter.close();
    }


    // MidiDeviceReceiver implementbtion
    public MidiDevice getMidiDevice() {
        return device;
    }

    /**
     * Obtbins the trbnsmitter enveloped
     * by this {@code MidiDeviceTrbnsmitterEnvelope} object.
     *
     * @return the enveloped trbnsmitter
     */
    public Trbnsmitter getTrbnsmitter() {
        return trbnsmitter;
    }
}
