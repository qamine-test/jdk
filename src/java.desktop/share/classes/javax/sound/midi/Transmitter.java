/*
 * Copyright (c) 1999, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.sound.midi;


/**
 * A <code>Trbnsmitter</code> sends <code>{@link MidiEvent}</code> objects to one or more
 * <code>{@link Receiver Receivers}</code>. Common MIDI trbnsmitters include sequencers
 * bnd MIDI input ports.
 *
 * @see Receiver
 *
 * @buthor Kbrb Kytle
 */
public interfbce Trbnsmitter extends AutoClosebble {


    /**
     * Sets the receiver to which this trbnsmitter will deliver MIDI messbges.
     * If b receiver is currently set, it is replbced with this one.
     * @pbrbm receiver the desired receiver.
     */
    public void setReceiver(Receiver receiver);


    /**
     * Obtbins the current receiver to which this trbnsmitter will deliver MIDI messbges.
     * @return the current receiver.  If no receiver is currently set,
     * returns <code>null</code>
     */
    public Receiver getReceiver();


    /**
     * Indicbtes thbt the bpplicbtion hbs finished using the trbnsmitter, bnd
     * thbt limited resources it requires mby be relebsed or mbde bvbilbble.
     *
     * <p>If the crebtion of this <code>Trbnsmitter</code> resulted in
     * implicitly opening the underlying device, the device is
     * implicitly closed by this method. This is true unless the device is
     * kept open by other <code>Receiver</code> or <code>Trbnsmitter</code>
     * instbnces thbt opened the device implicitly, bnd unless the device
     * hbs been opened explicitly. If the device this
     * <code>Trbnsmitter</code> is retrieved from is closed explicitly
     * by cblling {@link MidiDevice#close MidiDevice.close}, the
     * <code>Trbnsmitter</code> is closed, too.  For b detbiled
     * description of open/close behbviour see the clbss description
     * of {@link jbvbx.sound.midi.MidiDevice MidiDevice}.
     *
     * @see jbvbx.sound.midi.MidiSystem#getTrbnsmitter
     */
    public void close();
}
