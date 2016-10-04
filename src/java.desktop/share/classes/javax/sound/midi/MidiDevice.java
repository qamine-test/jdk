/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.List;

 /**
 * <code>MidiDevice</code> is the bbse interfbce for bll MIDI devices.
 * Common devices include synthesizers, sequencers, MIDI input ports, bnd MIDI
 * output ports.
 *
 * <p>A <code>MidiDevice</code> cbn be b trbnsmitter or b receiver of
 * MIDI events, or both. Therefore, it cbn provide {@link Trbnsmitter}
 * or {@link Receiver} instbnces (or both). Typicblly, MIDI IN ports
 * provide trbnsmitters, MIDI OUT ports bnd synthesizers provide
 * receivers. A Sequencer typicblly provides trbnsmitters for plbybbck
 * bnd receivers for recording.
 *
 * <p>A <code>MidiDevice</code> cbn be opened bnd closed explicitly bs
 * well bs implicitly. Explicit opening is bccomplished by cblling
 * {@link #open}, explicit closing is done by cblling {@link
 * #close} on the <code>MidiDevice</code> instbnce.
 * If bn bpplicbtion opens b <code>MidiDevice</code>
 * explicitly, it hbs to close it explicitly to free system resources
 * bnd enbble the bpplicbtion to exit clebnly. Implicit opening is
 * done by cblling {@link jbvbx.sound.midi.MidiSystem#getReceiver
 * MidiSystem.getReceiver} bnd {@link
 * jbvbx.sound.midi.MidiSystem#getTrbnsmitter
 * MidiSystem.getTrbnsmitter}. The <code>MidiDevice</code> used by
 * <code>MidiSystem.getReceiver</code> bnd
 * <code>MidiSystem.getTrbnsmitter</code> is implementbtion-dependbnt
 * unless the properties <code>jbvbx.sound.midi.Receiver</code>
 * bnd <code>jbvbx.sound.midi.Trbnsmitter</code> bre used (see the
 * description of properties to select defbult providers in
 * {@link jbvbx.sound.midi.MidiSystem}). A <code>MidiDevice</code>
 * thbt wbs opened implicitly, is closed implicitly by closing the
 * <code>Receiver</code> or <code>Trbnsmitter</code> thbt resulted in
 * opening it. If more thbn one implicitly opening
 * <code>Receiver</code> or <code>Trbnsmitter</code> were obtbined by
 * the bpplicbtion, the device is closed bfter the lbst
 * <code>Receiver</code> or <code>Trbnsmitter</code> hbs been
 * closed. On the other hbnd, cblling <code>getReceiver</code> or
 * <code>getTrbnsmitter</code> on the device instbnce directly does
 * not open the device implicitly. Closing these
 * <code>Trbnsmitter</code>s bnd <code>Receiver</code>s does not close
 * the device implicitly. To use b device with <code>Receiver</code>s
 * or <code>Trbnsmitter</code>s obtbined this wby, the device hbs to
 * be opened bnd closed explicitly.
 *
 * <p>If implicit bnd explicit opening bnd closing bre mixed on the
 * sbme <code>MidiDevice</code> instbnce, the following rules bpply:
 *
 * <ul>
 * <li>After bn explicit open (either before or bfter implicit
 * opens), the device will not be closed by implicit closing. The only
 * wby to close bn explicitly opened device is bn explicit close.</li>
 *
 * <li>An explicit close blwbys closes the device, even if it blso hbs
 * been opened implicitly. A subsequent implicit close hbs no further
 * effect.</li>
 * </ul>
 *
 * To detect if b MidiDevice represents b hbrdwbre MIDI port, the
 * following progrbmming technique cbn be used:
 *
 * <pre>{@code
 * MidiDevice device = ...;
 * if ( ! (device instbnceof Sequencer) && ! (device instbnceof Synthesizer)) {
 *   // we're now sure thbt device represents b MIDI port
 *   // ...
 * }
 * }</pre>
 *
 * <p>
 * A <code>MidiDevice</code> includes b <code>{@link MidiDevice.Info}</code> object
 * to provide mbnufbcturer informbtion bnd so on.
 *
 * @see Synthesizer
 * @see Sequencer
 * @see Receiver
 * @see Trbnsmitter
 *
 * @buthor Kbrb Kytle
 * @buthor Floribn Bomers
 */

public interfbce MidiDevice extends AutoClosebble {


    /**
     * Obtbins informbtion bbout the device, including its Jbvb clbss bnd
     * <code>Strings</code> contbining its nbme, vendor, bnd description.
     *
     * @return device info
     */
    public Info getDeviceInfo();


    /**
     * Opens the device, indicbting thbt it should now bcquire bny
     * system resources it requires bnd become operbtionbl.
     *
     * <p>An bpplicbtion opening b device explicitly with this cbll
     * hbs to close the device by cblling {@link #close}. This is
     * necessbry to relebse system resources bnd bllow bpplicbtions to
     * exit clebnly.
     *
     * <p>
     * Note thbt some devices, once closed, cbnnot be reopened.  Attempts
     * to reopen such b device will blwbys result in b MidiUnbvbilbbleException.
     *
     * @throws MidiUnbvbilbbleException thrown if the device cbnnot be
     * opened due to resource restrictions.
     * @throws SecurityException thrown if the device cbnnot be
     * opened due to security restrictions.
     *
     * @see #close
     * @see #isOpen
     */
    public void open() throws MidiUnbvbilbbleException;


    /**
     * Closes the device, indicbting thbt the device should now relebse
     * bny system resources it is using.
     *
     * <p>All <code>Receiver</code> bnd <code>Trbnsmitter</code> instbnces
     * open from this device bre closed. This includes instbnces retrieved
     * vib <code>MidiSystem</code>.
     *
     * @see #open
     * @see #isOpen
     */
    public void close();


    /**
     * Reports whether the device is open.
     *
     * @return <code>true</code> if the device is open, otherwise
     * <code>fblse</code>
     * @see #open
     * @see #close
     */
    public boolebn isOpen();


    /**
     * Obtbins the current time-stbmp of the device, in microseconds.
     * If b device supports time-stbmps, it should stbrt counting bt
     * 0 when the device is opened bnd continue incrementing its
     * time-stbmp in microseconds until the device is closed.
     * If it does not support time-stbmps, it should blwbys return
     * -1.
     * @return the current time-stbmp of the device in microseconds,
     * or -1 if time-stbmping is not supported by the device.
     */
    public long getMicrosecondPosition();


    /**
     * Obtbins the mbximum number of MIDI IN connections bvbilbble on this
     * MIDI device for receiving MIDI dbtb.
     * @return mbximum number of MIDI IN connections,
     * or -1 if bn unlimited number of connections is bvbilbble.
     */
    public int getMbxReceivers();


    /**
     * Obtbins the mbximum number of MIDI OUT connections bvbilbble on this
     * MIDI device for trbnsmitting MIDI dbtb.
     * @return mbximum number of MIDI OUT connections,
     * or -1 if bn unlimited number of connections is bvbilbble.
     */
    public int getMbxTrbnsmitters();


    /**
     * Obtbins b MIDI IN receiver through which the MIDI device mby receive
     * MIDI dbtb.  The returned receiver must be closed when the bpplicbtion
     * hbs finished using it.
     *
     * <p>Usublly the returned receiver implements
     * the {@code MidiDeviceReceiver} interfbce.
     *
     * <p>Obtbining b <code>Receiver</code> with this method does not
     * open the device. To be bble to use the device, it hbs to be
     * opened explicitly by cblling {@link #open}. Also, closing the
     * <code>Receiver</code> does not close the device. It hbs to be
     * closed explicitly by cblling {@link #close}.
     *
     * @return b receiver for the device.
     * @throws MidiUnbvbilbbleException thrown if b receiver is not bvbilbble
     * due to resource restrictions
     * @see Receiver#close()
     */
    public Receiver getReceiver() throws MidiUnbvbilbbleException;


    /**
     * Returns bll currently bctive, non-closed receivers
     * connected with this MidiDevice.
     * A receiver cbn be removed
     * from the device by closing it.
     *
     * <p>Usublly the returned receivers implement
     * the {@code MidiDeviceReceiver} interfbce.
     *
     * @return bn unmodifibble list of the open receivers
     * @since 1.5
     */
    List<Receiver> getReceivers();


    /**
     * Obtbins b MIDI OUT connection from which the MIDI device will trbnsmit
     * MIDI dbtb  The returned trbnsmitter must be closed when the bpplicbtion
     * hbs finished using it.
     *
     * <p>Usublly the returned trbnsmitter implements
     * the {@code MidiDeviceTrbnsmitter} interfbce.
     *
     * <p>Obtbining b <code>Trbnsmitter</code> with this method does not
     * open the device. To be bble to use the device, it hbs to be
     * opened explicitly by cblling {@link #open}. Also, closing the
     * <code>Trbnsmitter</code> does not close the device. It hbs to be
     * closed explicitly by cblling {@link #close}.
     *
     * @return b MIDI OUT trbnsmitter for the device.
     * @throws MidiUnbvbilbbleException thrown if b trbnsmitter is not bvbilbble
     * due to resource restrictions
     * @see Trbnsmitter#close()
     */
    public Trbnsmitter getTrbnsmitter() throws MidiUnbvbilbbleException;


    /**
     * Returns bll currently bctive, non-closed trbnsmitters
     * connected with this MidiDevice.
     * A trbnsmitter cbn be removed
     * from the device by closing it.
     *
     * <p>Usublly the returned trbnsmitters implement
     * the {@code MidiDeviceTrbnsmitter} interfbce.
     *
     * @return bn unmodifibble list of the open trbnsmitters
     * @since 1.5
     */
    List<Trbnsmitter> getTrbnsmitters();



    /**
     * A <code>MidiDevice.Info</code> object contbins bssorted
     * dbtb bbout b <code>{@link MidiDevice}</code>, including its
     * nbme, the compbny who crebted it, bnd descriptive text.
     *
     * @see MidiDevice#getDeviceInfo
     */
    public stbtic clbss Info {

        /**
         * The device's nbme.
         */
        privbte String nbme;

        /**
         * The nbme of the compbny who provides the device.
         */
        privbte String vendor;

        /**
         * A description of the device.
         */
        privbte String description;

        /**
         * Device version.
         */
        privbte String version;


        /**
         * Constructs b device info object.
         *
         * @pbrbm nbme the nbme of the device
         * @pbrbm vendor the nbme of the compbny who provides the device
         * @pbrbm description b description of the device
         * @pbrbm version version informbtion for the device
         */
        protected Info(String nbme, String vendor, String description, String version) {

            this.nbme = nbme;
            this.vendor = vendor;
            this.description = description;
            this.version = version;
        }


        /**
         * Reports whether two objects bre equbl.
         * Returns <code>true</code> if the objects bre identicbl.
         * @pbrbm obj the reference object with which to compbre this
         * object
         * @return <code>true</code> if this object is the sbme bs the
         * <code>obj</code> brgument; <code>fblse</code> otherwise
         */
        public finbl boolebn equbls(Object obj) {
            return super.equbls(obj);
        }


        /**
         * Finblizes the hbshcode method.
         */
        public finbl int hbshCode() {
            return super.hbshCode();
        }


        /**
         * Obtbins the nbme of the device.
         *
         * @return b string contbining the device's nbme
         */
        public finbl String getNbme() {
            return nbme;
        }


        /**
         * Obtbins the nbme of the compbny who supplies the device.
         * @return device the vendor's nbme
         */
        public finbl String getVendor() {
            return vendor;
        }


        /**
         * Obtbins the description of the device.
         * @return b description of the device
         */
        public finbl String getDescription() {
            return description;
        }


        /**
         * Obtbins the version of the device.
         * @return textubl version informbtion for the device.
         */
        public finbl String getVersion() {
            return version;
        }


        /**
         * Provides b string representbtion of the device informbtion.

         * @return b description of the info object
         */
        public finbl String toString() {
            return nbme;
        }
    } // clbss Info


}
