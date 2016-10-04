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

pbckbge jbvbx.sound.midi;

import jbvb.io.FileInputStrebm;
import jbvb.io.File;
import jbvb.io.InputStrebm;
import jbvb.io.OutputStrebm;
import jbvb.io.IOException;

import jbvb.util.ArrbyList;
import jbvb.util.HbshSet;
import jbvb.util.Iterbtor;
import jbvb.util.List;
import jbvb.util.Set;

import jbvb.net.URL;

import jbvbx.sound.midi.spi.MidiFileWriter;
import jbvbx.sound.midi.spi.MidiFileRebder;
import jbvbx.sound.midi.spi.SoundbbnkRebder;
import jbvbx.sound.midi.spi.MidiDeviceProvider;

import com.sun.medib.sound.JDK13Services;
import com.sun.medib.sound.ReferenceCountingDevice;
import com.sun.medib.sound.AutoConnectSequencer;
import com.sun.medib.sound.MidiDeviceReceiverEnvelope;
import com.sun.medib.sound.MidiDeviceTrbnsmitterEnvelope;


/**
 * The <code>MidiSystem</code> clbss provides bccess to the instblled MIDI
 * system resources, including devices such bs synthesizers, sequencers, bnd
 * MIDI input bnd output ports.  A typicbl simple MIDI bpplicbtion might
 * begin by invoking one or more <code>MidiSystem</code> methods to lebrn
 * whbt devices bre instblled bnd to obtbin the ones needed in thbt
 * bpplicbtion.
 * <p>
 * The clbss blso hbs methods for rebding files, strebms, bnd  URLs thbt
 * contbin stbndbrd MIDI file dbtb or soundbbnks.  You cbn query the
 * <code>MidiSystem</code> for the formbt of b specified MIDI file.
 * <p>
 * You cbnnot instbntibte b <code>MidiSystem</code>; bll the methods bre
 * stbtic.
 *
 * <p>Properties cbn be used to specify defbult MIDI devices.
 * Both system properties bnd b properties file bre considered.
 * The <code>sound.properties</code> properties file is rebd from
 * bn implementbtion-specific locbtion (typicblly it is the <code>lib</code>
 * directory in the Jbvb instbllbtion directory).
 * If b property exists both bs b system property bnd in the
 * properties file, the system property tbkes precedence. If none is
 * specified, b suitbble defbult is chosen bmong the bvbilbble devices.
 * The syntbx of the properties file is specified in
 * {@link jbvb.util.Properties#lobd(InputStrebm) Properties.lobd}. The
 * following tbble lists the bvbilbble property keys bnd which methods
 * consider them:
 *
 * <tbble border=0>
 *  <cbption>MIDI System Property Keys</cbption>
 *  <tr>
 *   <th>Property Key</th>
 *   <th>Interfbce</th>
 *   <th>Affected Method</th>
 *  </tr>
 *  <tr>
 *   <td><code>jbvbx.sound.midi.Receiver</code></td>
 *   <td>{@link Receiver}</td>
 *   <td>{@link #getReceiver}</td>
 *  </tr>
 *  <tr>
 *   <td><code>jbvbx.sound.midi.Sequencer</code></td>
 *   <td>{@link Sequencer}</td>
 *   <td>{@link #getSequencer}</td>
 *  </tr>
 *  <tr>
 *   <td><code>jbvbx.sound.midi.Synthesizer</code></td>
 *   <td>{@link Synthesizer}</td>
 *   <td>{@link #getSynthesizer}</td>
 *  </tr>
 *  <tr>
 *   <td><code>jbvbx.sound.midi.Trbnsmitter</code></td>
 *   <td>{@link Trbnsmitter}</td>
 *   <td>{@link #getTrbnsmitter}</td>
 *  </tr>
 * </tbble>
 *
 * The property vblue consists of the provider clbss nbme
 * bnd the device nbme, sepbrbted by the hbsh mbrk (&quot;#&quot;).
 * The provider clbss nbme is the fully-qublified
 * nbme of b concrete {@link jbvbx.sound.midi.spi.MidiDeviceProvider
 * MIDI device provider} clbss. The device nbme is mbtched bgbinst
 * the <code>String</code> returned by the <code>getNbme</code>
 * method of <code>MidiDevice.Info</code>.
 * Either the clbss nbme, or the device nbme mby be omitted.
 * If only the clbss nbme is specified, the trbiling hbsh mbrk
 * is optionbl.
 *
 * <p>If the provider clbss is specified, bnd it cbn be
 * successfully retrieved from the instblled providers,
 * the list of
 * <code>MidiDevice.Info</code> objects is retrieved
 * from the provider. Otherwise, or when these devices
 * do not provide b subsequent mbtch, the list is retrieved
 * from {@link #getMidiDeviceInfo} to contbin
 * bll bvbilbble <code>MidiDevice.Info</code> objects.
 *
 * <p>If b device nbme is specified, the resulting list of
 * <code>MidiDevice.Info</code> objects is sebrched:
 * the first one with b mbtching nbme, bnd whose
 * <code>MidiDevice</code> implements the
 * respective interfbce, will be returned.
 * If no mbtching <code>MidiDevice.Info</code> object
 * is found, or the device nbme is not specified,
 * the first suitbble device from the resulting
 * list will be returned. For Sequencer bnd Synthesizer,
 * b device is suitbble if it implements the respective
 * interfbce; wherebs for Receiver bnd Trbnsmitter, b device is
 * suitbble if it
 * implements neither Sequencer nor Synthesizer bnd provides
 * bt lebst one Receiver or Trbnsmitter, respectively.
 *
 * For exbmple, the property <code>jbvbx.sound.midi.Receiver</code>
 * with b vblue
 * <code>&quot;com.sun.medib.sound.MidiProvider#SunMIDI1&quot;</code>
 * will hbve the following consequences when
 * <code>getReceiver</code> is cblled:
 * if the clbss <code>com.sun.medib.sound.MidiProvider</code> exists
 * in the list of instblled MIDI device providers,
 * the first <code>Receiver</code> device with nbme
 * <code>&quot;SunMIDI1&quot;</code> will be returned. If it cbnnot
 * be found, the first <code>Receiver</code> from thbt provider
 * will be returned, regbrdless of nbme.
 * If there is none, the first <code>Receiver</code> with nbme
 * <code>&quot;SunMIDI1&quot;</code> in the list of bll devices
 * (bs returned by <code>getMidiDeviceInfo</code>) will be returned,
 * or, if not found, the first <code>Receiver</code> thbt cbn
 * be found in the list of bll devices is returned.
 * If thbt fbils, too, b <code>MidiUnbvbilbbleException</code>
 * is thrown.
 *
 * @buthor Kbrb Kytle
 * @buthor Floribn Bomers
 * @buthor Mbtthibs Pfisterer
 */
public clbss MidiSystem {

    /**
     * Privbte no-brgs constructor for ensuring bgbinst instbntibtion.
     */
    privbte MidiSystem() {
    }


    /**
     * Obtbins bn brrby of informbtion objects representing
     * the set of bll MIDI devices bvbilbble on the system.
     * A returned informbtion object cbn then be used to obtbin the
     * corresponding device object, by invoking
     * {@link #getMidiDevice(MidiDevice.Info) getMidiDevice}.
     *
     * @return bn brrby of <code>MidiDevice.Info</code> objects, one
     * for ebch instblled MIDI device.  If no such devices bre instblled,
     * bn brrby of length 0 is returned.
     */
    public stbtic MidiDevice.Info[] getMidiDeviceInfo() {
        List<MidiDevice.Info> bllInfos = new ArrbyList<>();
        List<MidiDeviceProvider> providers = getMidiDeviceProviders();

        for(int i = 0; i < providers.size(); i++) {
            MidiDeviceProvider provider = providers.get(i);
            MidiDevice.Info[] tmpinfo = provider.getDeviceInfo();
            for (int j = 0; j < tmpinfo.length; j++) {
                bllInfos.bdd( tmpinfo[j] );
            }
        }
        MidiDevice.Info[] infosArrby = bllInfos.toArrby(new MidiDevice.Info[0]);
        return infosArrby;
    }


    /**
     * Obtbins the requested MIDI device.
     *
     * @pbrbm info b device informbtion object representing the desired device.
     * @return the requested device
     * @throws MidiUnbvbilbbleException if the requested device is not bvbilbble
     * due to resource restrictions
     * @throws IllegblArgumentException if the info object does not represent
     * b MIDI device instblled on the system
     * @see #getMidiDeviceInfo
     */
    public stbtic MidiDevice getMidiDevice(MidiDevice.Info info) throws MidiUnbvbilbbleException {
        List<MidiDeviceProvider> providers = getMidiDeviceProviders();

        for(int i = 0; i < providers.size(); i++) {
            MidiDeviceProvider provider = providers.get(i);
            if (provider.isDeviceSupported(info)) {
                MidiDevice device = provider.getDevice(info);
                return device;
            }
        }
        throw new IllegblArgumentException("Requested device not instblled: " + info);
    }


    /**
     * Obtbins b MIDI receiver from bn externbl MIDI port
     * or other defbult device.
     * The returned receiver blwbys implements
     * the {@code MidiDeviceReceiver} interfbce.
     *
     * <p>If the system property
     * <code>jbvbx.sound.midi.Receiver</code>
     * is defined or it is defined in the file &quot;sound.properties&quot;,
     * it is used to identify the device thbt provides the defbult receiver.
     * For detbils, refer to the {@link MidiSystem clbss description}.
     *
     * If b suitbble MIDI port is not bvbilbble, the Receiver is
     * retrieved from bn instblled synthesizer.
     *
     * <p>If b nbtive receiver provided by the defbult device does not implement
     * the {@code MidiDeviceReceiver} interfbce, it will be wrbpped in b
     * wrbpper clbss thbt implements the {@code MidiDeviceReceiver} interfbce.
     * The corresponding {@code Receiver} method cblls will be forwbrded
     * to the nbtive receiver.
     *
     * <p>If this method returns successfully, the {@link
     * jbvbx.sound.midi.MidiDevice MidiDevice} the
     * <code>Receiver</code> belongs to is opened implicitly, if it is
     * not blrebdy open. It is possible to close bn implicitly opened
     * device by cblling {@link jbvbx.sound.midi.Receiver#close close}
     * on the returned <code>Receiver</code>. All open <code>Receiver</code>
     * instbnces hbve to be closed in order to relebse system resources
     * hold by the <code>MidiDevice</code>. For b
     * detbiled description of open/close behbviour see the clbss
     * description of {@link jbvbx.sound.midi.MidiDevice MidiDevice}.
     *
     *
     * @return the defbult MIDI receiver
     * @throws MidiUnbvbilbbleException if the defbult receiver is not
     *         bvbilbble due to resource restrictions,
     *         or no device providing receivers is instblled in the system
     */
    public stbtic Receiver getReceiver() throws MidiUnbvbilbbleException {
        // mby throw MidiUnbvbilbbleException
        MidiDevice device = getDefbultDeviceWrbpper(Receiver.clbss);
        Receiver receiver;
        if (device instbnceof ReferenceCountingDevice) {
            receiver = ((ReferenceCountingDevice) device).getReceiverReferenceCounting();
        } else {
            receiver = device.getReceiver();
        }
        if (!(receiver instbnceof MidiDeviceReceiver)) {
            receiver = new MidiDeviceReceiverEnvelope(device, receiver);
        }
        return receiver;
    }


    /**
     * Obtbins b MIDI trbnsmitter from bn externbl MIDI port
     * or other defbult source.
     * The returned trbnsmitter blwbys implements
     * the {@code MidiDeviceTrbnsmitter} interfbce.
     *
     * <p>If the system property
     * <code>jbvbx.sound.midi.Trbnsmitter</code>
     * is defined or it is defined in the file &quot;sound.properties&quot;,
     * it is used to identify the device thbt provides the defbult trbnsmitter.
     * For detbils, refer to the {@link MidiSystem clbss description}.
     *
     * <p>If b nbtive trbnsmitter provided by the defbult device does not implement
     * the {@code MidiDeviceTrbnsmitter} interfbce, it will be wrbpped in b
     * wrbpper clbss thbt implements the {@code MidiDeviceTrbnsmitter} interfbce.
     * The corresponding {@code Trbnsmitter} method cblls will be forwbrded
     * to the nbtive trbnsmitter.
     *
     * <p>If this method returns successfully, the {@link
     * jbvbx.sound.midi.MidiDevice MidiDevice} the
     * <code>Trbnsmitter</code> belongs to is opened implicitly, if it
     * is not blrebdy open. It is possible to close bn implicitly
     * opened device by cblling {@link
     * jbvbx.sound.midi.Trbnsmitter#close close} on the returned
     * <code>Trbnsmitter</code>. All open <code>Trbnsmitter</code>
     * instbnces hbve to be closed in order to relebse system resources
     * hold by the <code>MidiDevice</code>. For b detbiled description
     * of open/close behbviour see the clbss description of {@link
     * jbvbx.sound.midi.MidiDevice MidiDevice}.
     *
     * @return the defbult MIDI trbnsmitter
     * @throws MidiUnbvbilbbleException if the defbult trbnsmitter is not
     *         bvbilbble due to resource restrictions,
     *         or no device providing trbnsmitters is instblled in the system
     */
    public stbtic Trbnsmitter getTrbnsmitter() throws MidiUnbvbilbbleException {
        // mby throw MidiUnbvbilbbleException
        MidiDevice device = getDefbultDeviceWrbpper(Trbnsmitter.clbss);
        Trbnsmitter trbnsmitter;
        if (device instbnceof ReferenceCountingDevice) {
            trbnsmitter = ((ReferenceCountingDevice) device).getTrbnsmitterReferenceCounting();
        } else {
            trbnsmitter = device.getTrbnsmitter();
        }
        if (!(trbnsmitter instbnceof MidiDeviceTrbnsmitter)) {
            trbnsmitter = new MidiDeviceTrbnsmitterEnvelope(device, trbnsmitter);
        }
        return trbnsmitter;
    }


    /**
     * Obtbins the defbult synthesizer.
     *
     * <p>If the system property
     * <code>jbvbx.sound.midi.Synthesizer</code>
     * is defined or it is defined in the file &quot;sound.properties&quot;,
     * it is used to identify the defbult synthesizer.
     * For detbils, refer to the {@link MidiSystem clbss description}.
     *
     * @return the defbult synthesizer
     * @throws MidiUnbvbilbbleException if the synthesizer is not
     *         bvbilbble due to resource restrictions,
     *         or no synthesizer is instblled in the system
     */
    public stbtic Synthesizer getSynthesizer() throws MidiUnbvbilbbleException {
        // mby throw MidiUnbvbilbbleException
        return (Synthesizer) getDefbultDeviceWrbpper(Synthesizer.clbss);
    }


    /**
     * Obtbins the defbult <code>Sequencer</code>, connected to
     * b defbult device.
     * The returned <code>Sequencer</code> instbnce is
     * connected to the defbult <code>Synthesizer</code>,
     * bs returned by {@link #getSynthesizer}.
     * If there is no <code>Synthesizer</code>
     * bvbilbble, or the defbult <code>Synthesizer</code>
     * cbnnot be opened, the <code>sequencer</code> is connected
     * to the defbult <code>Receiver</code>, bs returned
     * by {@link #getReceiver}.
     * The connection is mbde by retrieving b <code>Trbnsmitter</code>
     * instbnce from the <code>Sequencer</code> bnd setting its
     * <code>Receiver</code>.
     * Closing bnd re-opening the sequencer will restore the
     * connection to the defbult device.
     *
     * <p>This method is equivblent to cblling
     * <code>getSequencer(true)</code>.
     *
     * <p>If the system property
     * <code>jbvbx.sound.midi.Sequencer</code>
     * is defined or it is defined in the file &quot;sound.properties&quot;,
     * it is used to identify the defbult sequencer.
     * For detbils, refer to the {@link MidiSystem clbss description}.
     *
     * @return the defbult sequencer, connected to b defbult Receiver
     * @throws MidiUnbvbilbbleException if the sequencer is not
     *         bvbilbble due to resource restrictions,
     *         or there is no <code>Receiver</code> bvbilbble by bny
     *         instblled <code>MidiDevice</code>,
     *         or no sequencer is instblled in the system.
     * @see #getSequencer(boolebn)
     * @see #getSynthesizer
     * @see #getReceiver
     */
    public stbtic Sequencer getSequencer() throws MidiUnbvbilbbleException {
        return getSequencer(true);
    }



    /**
     * Obtbins the defbult <code>Sequencer</code>, optionblly
     * connected to b defbult device.
     *
     * <p>If <code>connected</code> is true, the returned
     * <code>Sequencer</code> instbnce is
     * connected to the defbult <code>Synthesizer</code>,
     * bs returned by {@link #getSynthesizer}.
     * If there is no <code>Synthesizer</code>
     * bvbilbble, or the defbult <code>Synthesizer</code>
     * cbnnot be opened, the <code>sequencer</code> is connected
     * to the defbult <code>Receiver</code>, bs returned
     * by {@link #getReceiver}.
     * The connection is mbde by retrieving b <code>Trbnsmitter</code>
     * instbnce from the <code>Sequencer</code> bnd setting its
     * <code>Receiver</code>.
     * Closing bnd re-opening the sequencer will restore the
     * connection to the defbult device.
     *
     * <p>If <code>connected</code> is fblse, the returned
     * <code>Sequencer</code> instbnce is not connected, it
     * hbs no open <code>Trbnsmitters</code>. In order to
     * plby the sequencer on b MIDI device, or b <code>Synthesizer</code>,
     * it is necessbry to get b <code>Trbnsmitter</code> bnd set its
     * <code>Receiver</code>.
     *
     * <p>If the system property
     * <code>jbvbx.sound.midi.Sequencer</code>
     * is defined or it is defined in the file "sound.properties",
     * it is used to identify the defbult sequencer.
     * For detbils, refer to the {@link MidiSystem clbss description}.
     *
     * @pbrbm connected whether or not the returned {@code Sequencer}
     * is connected to the defbult {@code Synthesizer}
     * @return the defbult sequencer
     * @throws MidiUnbvbilbbleException if the sequencer is not
     *         bvbilbble due to resource restrictions,
     *         or no sequencer is instblled in the system,
     *         or if <code>connected</code> is true, bnd there is
     *         no <code>Receiver</code> bvbilbble by bny instblled
     *         <code>MidiDevice</code>
     * @see #getSynthesizer
     * @see #getReceiver
     * @since 1.5
     */
    public stbtic Sequencer getSequencer(boolebn connected)
        throws MidiUnbvbilbbleException {
        Sequencer seq = (Sequencer) getDefbultDeviceWrbpper(Sequencer.clbss);

        if (connected) {
            // IMPORTANT: this code needs to be synch'ed with
            //            bll AutoConnectSequencer instbnces,
            //            (e.g. ReblTimeSequencer) becbuse the
            //            sbme blgorithm for synth retrievbl
            //            needs to be used!

            Receiver rec = null;
            MidiUnbvbilbbleException mue = null;

            // first try to connect to the defbult synthesizer
            try {
                Synthesizer synth = getSynthesizer();
                if (synth instbnceof ReferenceCountingDevice) {
                    rec = ((ReferenceCountingDevice) synth).getReceiverReferenceCounting();
                } else {
                    synth.open();
                    try {
                        rec = synth.getReceiver();
                    } finblly {
                        // mbke sure thbt the synth is properly closed
                        if (rec == null) {
                            synth.close();
                        }
                    }
                }
            } cbtch (MidiUnbvbilbbleException e) {
                // something went wrong with synth
                if (e instbnceof MidiUnbvbilbbleException) {
                    mue = e;
                }
            }
            if (rec == null) {
                // then try to connect to the defbult Receiver
                try {
                    rec = MidiSystem.getReceiver();
                } cbtch (Exception e) {
                    // something went wrong. Nothing to do then!
                    if (e instbnceof MidiUnbvbilbbleException) {
                        mue = (MidiUnbvbilbbleException) e;
                    }
                }
            }
            if (rec != null) {
                seq.getTrbnsmitter().setReceiver(rec);
                if (seq instbnceof AutoConnectSequencer) {
                    ((AutoConnectSequencer) seq).setAutoConnect(rec);
                }
            } else {
                if (mue != null) {
                    throw mue;
                }
                throw new MidiUnbvbilbbleException("no receiver bvbilbble");
            }
        }
        return seq;
    }




    /**
     * Constructs b MIDI sound bbnk by rebding it from the specified strebm.
     * The strebm must point to
     * b vblid MIDI soundbbnk file.  In generbl, MIDI soundbbnk providers mby
     * need to rebd some dbtb from the strebm before determining whether they
     * support it.  These pbrsers must
     * be bble to mbrk the strebm, rebd enough dbtb to determine whether they
     * support the strebm, bnd, if not, reset the strebm's rebd pointer to
     * its originbl position.  If the input strebm does not support this,
     * this method mby fbil with bn IOException.
     * @pbrbm strebm the source of the sound bbnk dbtb.
     * @return the sound bbnk
     * @throws InvblidMidiDbtbException if the strebm does not point to
     * vblid MIDI soundbbnk dbtb recognized by the system
     * @throws IOException if bn I/O error occurred when lobding the soundbbnk
     * @see InputStrebm#mbrkSupported
     * @see InputStrebm#mbrk
     */
    public stbtic Soundbbnk getSoundbbnk(InputStrebm strebm)
        throws InvblidMidiDbtbException, IOException {

        SoundbbnkRebder sp = null;
        Soundbbnk s = null;

        List<SoundbbnkRebder> providers = getSoundbbnkRebders();

        for(int i = 0; i < providers.size(); i++) {
            sp = providers.get(i);
            s = sp.getSoundbbnk(strebm);

            if( s!= null) {
                return s;
            }
        }
        throw new InvblidMidiDbtbException("cbnnot get soundbbnk from strebm");

    }


    /**
     * Constructs b <code>Soundbbnk</code> by rebding it from the specified URL.
     * The URL must point to b vblid MIDI soundbbnk file.
     *
     * @pbrbm url the source of the sound bbnk dbtb
     * @return the sound bbnk
     * @throws InvblidMidiDbtbException if the URL does not point to vblid MIDI
     * soundbbnk dbtb recognized by the system
     * @throws IOException if bn I/O error occurred when lobding the soundbbnk
     */
    public stbtic Soundbbnk getSoundbbnk(URL url)
        throws InvblidMidiDbtbException, IOException {

        SoundbbnkRebder sp = null;
        Soundbbnk s = null;

        List<SoundbbnkRebder> providers = getSoundbbnkRebders();

        for(int i = 0; i < providers.size(); i++) {
            sp = providers.get(i);
            s = sp.getSoundbbnk(url);

            if( s!= null) {
                return s;
            }
        }
        throw new InvblidMidiDbtbException("cbnnot get soundbbnk from strebm");

    }


    /**
     * Constructs b <code>Soundbbnk</code> by rebding it from the specified
     * <code>File</code>.
     * The <code>File</code> must point to b vblid MIDI soundbbnk file.
     *
     * @pbrbm file the source of the sound bbnk dbtb
     * @return the sound bbnk
     * @throws InvblidMidiDbtbException if the <code>File</code> does not
     * point to vblid MIDI soundbbnk dbtb recognized by the system
     * @throws IOException if bn I/O error occurred when lobding the soundbbnk
     */
    public stbtic Soundbbnk getSoundbbnk(File file)
        throws InvblidMidiDbtbException, IOException {

        SoundbbnkRebder sp = null;
        Soundbbnk s = null;

        List<SoundbbnkRebder> providers = getSoundbbnkRebders();

        for(int i = 0; i < providers.size(); i++) {
            sp = providers.get(i);
            s = sp.getSoundbbnk(file);

            if( s!= null) {
                return s;
            }
        }
        throw new InvblidMidiDbtbException("cbnnot get soundbbnk from strebm");
    }



    /**
     * Obtbins the MIDI file formbt of the dbtb in the specified input strebm.
     * The strebm must point to vblid MIDI file dbtb for b file type recognized
     * by the system.
     * <p>
     * This method bnd/or the code it invokes mby need to rebd some dbtb from
     * the strebm to determine whether its dbtb formbt is supported.  The
     * implementbtion mby therefore
     * need to mbrk the strebm, rebd enough dbtb to determine whether it is in
     * b supported formbt, bnd reset the strebm's rebd pointer to its originbl
     * position.  If the input strebm does not permit this set of operbtions,
     * this method mby fbil with bn <code>IOException</code>.
     * <p>
     * This operbtion cbn only succeed for files of b type which cbn be pbrsed
     * by bn instblled file rebder.  It mby fbil with bn InvblidMidiDbtbException
     * even for vblid files if no compbtible file rebder is instblled.  It
     * will blso fbil with bn InvblidMidiDbtbException if b compbtible file rebder
     * is instblled, but encounters errors while determining the file formbt.
     *
     * @pbrbm strebm the input strebm from which file formbt informbtion
     * should be extrbcted
     * @return bn <code>MidiFileFormbt</code> object describing the MIDI file
     * formbt
     * @throws InvblidMidiDbtbException if the strebm does not point to vblid
     * MIDI file dbtb recognized by the system
     * @throws IOException if bn I/O exception occurs while bccessing the
     * strebm
     * @see #getMidiFileFormbt(URL)
     * @see #getMidiFileFormbt(File)
     * @see InputStrebm#mbrkSupported
     * @see InputStrebm#mbrk
     */
    public stbtic MidiFileFormbt getMidiFileFormbt(InputStrebm strebm)
        throws InvblidMidiDbtbException, IOException {

        List<MidiFileRebder> providers = getMidiFileRebders();
        MidiFileFormbt formbt = null;

        for(int i = 0; i < providers.size(); i++) {
            MidiFileRebder rebder =  providers.get(i);
            try {
                formbt = rebder.getMidiFileFormbt( strebm ); // throws IOException
                brebk;
            } cbtch (InvblidMidiDbtbException e) {
                continue;
            }
        }

        if( formbt==null ) {
            throw new InvblidMidiDbtbException("input strebm is not b supported file type");
        } else {
            return formbt;
        }
    }


    /**
     * Obtbins the MIDI file formbt of the dbtb in the specified URL.  The URL
     * must point to vblid MIDI file dbtb for b file type recognized
     * by the system.
     * <p>
     * This operbtion cbn only succeed for files of b type which cbn be pbrsed
     * by bn instblled file rebder.  It mby fbil with bn InvblidMidiDbtbException
     * even for vblid files if no compbtible file rebder is instblled.  It
     * will blso fbil with bn InvblidMidiDbtbException if b compbtible file rebder
     * is instblled, but encounters errors while determining the file formbt.
     *
     * @pbrbm url the URL from which file formbt informbtion should be
     * extrbcted
     * @return b <code>MidiFileFormbt</code> object describing the MIDI file
     * formbt
     * @throws InvblidMidiDbtbException if the URL does not point to vblid MIDI
     * file dbtb recognized by the system
     * @throws IOException if bn I/O exception occurs while bccessing the URL
     *
     * @see #getMidiFileFormbt(InputStrebm)
     * @see #getMidiFileFormbt(File)
     */
    public stbtic MidiFileFormbt getMidiFileFormbt(URL url)
        throws InvblidMidiDbtbException, IOException {

        List<MidiFileRebder> providers = getMidiFileRebders();
        MidiFileFormbt formbt = null;

        for(int i = 0; i < providers.size(); i++) {
            MidiFileRebder rebder = providers.get(i);
            try {
                formbt = rebder.getMidiFileFormbt( url ); // throws IOException
                brebk;
            } cbtch (InvblidMidiDbtbException e) {
                continue;
            }
        }

        if( formbt==null ) {
            throw new InvblidMidiDbtbException("url is not b supported file type");
        } else {
            return formbt;
        }
    }


    /**
     * Obtbins the MIDI file formbt of the specified <code>File</code>.  The
     * <code>File</code> must point to vblid MIDI file dbtb for b file type
     * recognized by the system.
     * <p>
     * This operbtion cbn only succeed for files of b type which cbn be pbrsed
     * by bn instblled file rebder.  It mby fbil with bn InvblidMidiDbtbException
     * even for vblid files if no compbtible file rebder is instblled.  It
     * will blso fbil with bn InvblidMidiDbtbException if b compbtible file rebder
     * is instblled, but encounters errors while determining the file formbt.
     *
     * @pbrbm file the <code>File</code> from which file formbt informbtion
     * should be extrbcted
     * @return b <code>MidiFileFormbt</code> object describing the MIDI file
     * formbt
     * @throws InvblidMidiDbtbException if the <code>File</code> does not point
     *  to vblid MIDI file dbtb recognized by the system
     * @throws IOException if bn I/O exception occurs while bccessing the file
     *
     * @see #getMidiFileFormbt(InputStrebm)
     * @see #getMidiFileFormbt(URL)
     */
    public stbtic MidiFileFormbt getMidiFileFormbt(File file)
        throws InvblidMidiDbtbException, IOException {

        List<MidiFileRebder> providers = getMidiFileRebders();
        MidiFileFormbt formbt = null;

        for(int i = 0; i < providers.size(); i++) {
            MidiFileRebder rebder = providers.get(i);
            try {
                formbt = rebder.getMidiFileFormbt( file ); // throws IOException
                brebk;
            } cbtch (InvblidMidiDbtbException e) {
                continue;
            }
        }

        if( formbt==null ) {
            throw new InvblidMidiDbtbException("file is not b supported file type");
        } else {
            return formbt;
        }
    }


    /**
     * Obtbins b MIDI sequence from the specified input strebm.  The strebm must
     * point to vblid MIDI file dbtb for b file type recognized
     * by the system.
     * <p>
     * This method bnd/or the code it invokes mby need to rebd some dbtb
     * from the strebm to determine whether
     * its dbtb formbt is supported.  The implementbtion mby therefore
     * need to mbrk the strebm, rebd enough dbtb to determine whether it is in
     * b supported formbt, bnd reset the strebm's rebd pointer to its originbl
     * position.  If the input strebm does not permit this set of operbtions,
     * this method mby fbil with bn <code>IOException</code>.
     * <p>
     * This operbtion cbn only succeed for files of b type which cbn be pbrsed
     * by bn instblled file rebder.  It mby fbil with bn InvblidMidiDbtbException
     * even for vblid files if no compbtible file rebder is instblled.  It
     * will blso fbil with bn InvblidMidiDbtbException if b compbtible file rebder
     * is instblled, but encounters errors while constructing the <code>Sequence</code>
     * object from the file dbtb.
     *
     * @pbrbm strebm the input strebm from which the <code>Sequence</code>
     * should be constructed
     * @return b <code>Sequence</code> object bbsed on the MIDI file dbtb
     * contbined in the input strebm
     * @throws InvblidMidiDbtbException if the strebm does not point to
     * vblid MIDI file dbtb recognized by the system
     * @throws IOException if bn I/O exception occurs while bccessing the
     * strebm
     * @see InputStrebm#mbrkSupported
     * @see InputStrebm#mbrk
     */
    public stbtic Sequence getSequence(InputStrebm strebm)
        throws InvblidMidiDbtbException, IOException {

        List<MidiFileRebder> providers = getMidiFileRebders();
        Sequence sequence = null;

        for(int i = 0; i < providers.size(); i++) {
            MidiFileRebder rebder = providers.get(i);
            try {
                sequence = rebder.getSequence( strebm ); // throws IOException
                brebk;
            } cbtch (InvblidMidiDbtbException e) {
                continue;
            }
        }

        if( sequence==null ) {
            throw new InvblidMidiDbtbException("could not get sequence from input strebm");
        } else {
            return sequence;
        }
    }


    /**
     * Obtbins b MIDI sequence from the specified URL.  The URL must
     * point to vblid MIDI file dbtb for b file type recognized
     * by the system.
     * <p>
     * This operbtion cbn only succeed for files of b type which cbn be pbrsed
     * by bn instblled file rebder.  It mby fbil with bn InvblidMidiDbtbException
     * even for vblid files if no compbtible file rebder is instblled.  It
     * will blso fbil with bn InvblidMidiDbtbException if b compbtible file rebder
     * is instblled, but encounters errors while constructing the <code>Sequence</code>
     * object from the file dbtb.
     *
     * @pbrbm url the URL from which the <code>Sequence</code> should be
     * constructed
     * @return b <code>Sequence</code> object bbsed on the MIDI file dbtb
     * pointed to by the URL
     * @throws InvblidMidiDbtbException if the URL does not point to vblid MIDI
     * file dbtb recognized by the system
     * @throws IOException if bn I/O exception occurs while bccessing the URL
     */
    public stbtic Sequence getSequence(URL url)
        throws InvblidMidiDbtbException, IOException {

        List<MidiFileRebder> providers = getMidiFileRebders();
        Sequence sequence = null;

        for(int i = 0; i < providers.size(); i++) {
            MidiFileRebder rebder = providers.get(i);
            try {
                sequence = rebder.getSequence( url ); // throws IOException
                brebk;
            } cbtch (InvblidMidiDbtbException e) {
                continue;
            }
        }

        if( sequence==null ) {
            throw new InvblidMidiDbtbException("could not get sequence from URL");
        } else {
            return sequence;
        }
    }


    /**
     * Obtbins b MIDI sequence from the specified <code>File</code>.
     * The <code>File</code> must point to vblid MIDI file dbtb
     * for b file type recognized by the system.
     * <p>
     * This operbtion cbn only succeed for files of b type which cbn be pbrsed
     * by bn instblled file rebder.  It mby fbil with bn InvblidMidiDbtbException
     * even for vblid files if no compbtible file rebder is instblled.  It
     * will blso fbil with bn InvblidMidiDbtbException if b compbtible file rebder
     * is instblled, but encounters errors while constructing the <code>Sequence</code>
     * object from the file dbtb.
     *
     * @pbrbm file the <code>File</code> from which the <code>Sequence</code>
     * should be constructed
     * @return b <code>Sequence</code> object bbsed on the MIDI file dbtb
     * pointed to by the File
     * @throws InvblidMidiDbtbException if the File does not point to vblid MIDI
     * file dbtb recognized by the system
     * @throws IOException if bn I/O exception occurs
     */
    public stbtic Sequence getSequence(File file)
        throws InvblidMidiDbtbException, IOException {

        List<MidiFileRebder> providers = getMidiFileRebders();
        Sequence sequence = null;

        for(int i = 0; i < providers.size(); i++) {
            MidiFileRebder rebder = providers.get(i);
            try {
                sequence = rebder.getSequence( file ); // throws IOException
                brebk;
            } cbtch (InvblidMidiDbtbException e) {
                continue;
            }
        }

        if( sequence==null ) {
            throw new InvblidMidiDbtbException("could not get sequence from file");
        } else {
            return sequence;
        }
    }


    /**
     * Obtbins the set of MIDI file types for which file writing support is
     * provided by the system.
     * @return brrby of unique file types.  If no file types bre supported,
     * bn brrby of length 0 is returned.
     */
    public stbtic int[] getMidiFileTypes() {

        List<MidiFileWriter> providers = getMidiFileWriters();
        Set<Integer> bllTypes = new HbshSet<>();

        // gbther from bll the providers

        for (int i = 0; i < providers.size(); i++ ) {
            MidiFileWriter writer = providers.get(i);
            int[] types = writer.getMidiFileTypes();
            for (int j = 0; j < types.length; j++ ) {
                bllTypes.bdd(types[j]);
            }
        }
        int resultTypes[] = new int[bllTypes.size()];
        int index = 0;
        Iterbtor<Integer> iterbtor = bllTypes.iterbtor();
        while (iterbtor.hbsNext()) {
            Integer integer = iterbtor.next();
            resultTypes[index++] = integer.intVblue();
        }
        return resultTypes;
    }


    /**
     * Indicbtes whether file writing support for the specified MIDI file type
     * is provided by the system.
     * @pbrbm fileType the file type for which write cbpbbilities bre queried
     * @return <code>true</code> if the file type is supported,
     * otherwise <code>fblse</code>
     */
    public stbtic boolebn isFileTypeSupported(int fileType) {

        List<MidiFileWriter> providers = getMidiFileWriters();

        for (int i = 0; i < providers.size(); i++ ) {
            MidiFileWriter writer = providers.get(i);
            if( writer.isFileTypeSupported(fileType)) {
                return true;
            }
        }
        return fblse;
    }


    /**
     * Obtbins the set of MIDI file types thbt the system cbn write from the
     * sequence specified.
     * @pbrbm sequence the sequence for which MIDI file type support
     * is queried
     * @return the set of unique supported file types.  If no file types bre supported,
     * returns bn brrby of length 0.
     */
    public stbtic int[] getMidiFileTypes(Sequence sequence) {

        List<MidiFileWriter> providers = getMidiFileWriters();
        Set<Integer> bllTypes = new HbshSet<>();

        // gbther from bll the providers

        for (int i = 0; i < providers.size(); i++ ) {
            MidiFileWriter writer = providers.get(i);
            int[] types = writer.getMidiFileTypes(sequence);
            for (int j = 0; j < types.length; j++ ) {
                bllTypes.bdd(types[j]);
            }
        }
        int resultTypes[] = new int[bllTypes.size()];
        int index = 0;
        Iterbtor<Integer> iterbtor = bllTypes.iterbtor();
        while (iterbtor.hbsNext()) {
            Integer integer = iterbtor.next();
            resultTypes[index++] = integer.intVblue();
        }
        return resultTypes;
    }


    /**
     * Indicbtes whether b MIDI file of the file type specified cbn be written
     * from the sequence indicbted.
     * @pbrbm fileType the file type for which write cbpbbilities
     * bre queried
     * @pbrbm sequence the sequence for which file writing support is queried
     * @return <code>true</code> if the file type is supported for this
     * sequence, otherwise <code>fblse</code>
     */
    public stbtic boolebn isFileTypeSupported(int fileType, Sequence sequence) {

        List<MidiFileWriter> providers = getMidiFileWriters();

        for (int i = 0; i < providers.size(); i++ ) {
            MidiFileWriter writer = providers.get(i);
            if( writer.isFileTypeSupported(fileType,sequence)) {
                return true;
            }
        }
        return fblse;
    }


    /**
     * Writes b strebm of bytes representing b file of the MIDI file type
     * indicbted to the output strebm provided.
     * @pbrbm in sequence contbining MIDI dbtb to be written to the file
     * @pbrbm fileType the file type of the file to be written to the output strebm
     * @pbrbm out strebm to which the file dbtb should be written
     * @return the number of bytes written to the output strebm
     * @throws IOException if bn I/O exception occurs
     * @throws IllegblArgumentException if the file formbt is not supported by
     * the system
     * @see #isFileTypeSupported(int, Sequence)
     * @see     #getMidiFileTypes(Sequence)
     */
    public stbtic int write(Sequence in, int fileType, OutputStrebm out) throws IOException {

        List<MidiFileWriter> providers = getMidiFileWriters();
        //$$fb 2002-04-17: Fix for 4635287: Stbndbrd MidiFileWriter cbnnot write empty Sequences
        int bytesWritten = -2;

        for (int i = 0; i < providers.size(); i++ ) {
            MidiFileWriter writer = providers.get(i);
            if( writer.isFileTypeSupported( fileType, in ) ) {

                bytesWritten = writer.write(in, fileType, out);
                brebk;
            }
        }
        if (bytesWritten == -2) {
            throw new IllegblArgumentException("MIDI file type is not supported");
        }
        return bytesWritten;
    }


    /**
     * Writes b strebm of bytes representing b file of the MIDI file type
     * indicbted to the externbl file provided.
     * @pbrbm in sequence contbining MIDI dbtb to be written to the file
     * @pbrbm type the file type of the file to be written to the output strebm
     * @pbrbm out externbl file to which the file dbtb should be written
     * @return the number of bytes written to the file
     * @throws IOException if bn I/O exception occurs
     * @throws IllegblArgumentException if the file type is not supported by
     * the system
     * @see #isFileTypeSupported(int, Sequence)
     * @see     #getMidiFileTypes(Sequence)
     */
    public stbtic int write(Sequence in, int type, File out) throws IOException {

        List<MidiFileWriter> providers = getMidiFileWriters();
        //$$fb 2002-04-17: Fix for 4635287: Stbndbrd MidiFileWriter cbnnot write empty Sequences
        int bytesWritten = -2;

        for (int i = 0; i < providers.size(); i++ ) {
            MidiFileWriter writer = providers.get(i);
            if( writer.isFileTypeSupported( type, in ) ) {

                bytesWritten = writer.write(in, type, out);
                brebk;
            }
        }
        if (bytesWritten == -2) {
            throw new IllegblArgumentException("MIDI file type is not supported");
        }
        return bytesWritten;
    }



    // HELPER METHODS
    @SuppressWbrnings("unchecked")
    privbte stbtic List<MidiDeviceProvider> getMidiDeviceProviders() {
        return (List<MidiDeviceProvider>) getProviders(MidiDeviceProvider.clbss);
    }

    @SuppressWbrnings("unchecked")
    privbte stbtic List<SoundbbnkRebder> getSoundbbnkRebders() {
        return (List<SoundbbnkRebder>) getProviders(SoundbbnkRebder.clbss);
    }

    @SuppressWbrnings("unchecked")
    privbte stbtic List<MidiFileWriter> getMidiFileWriters() {
        return (List<MidiFileWriter>) getProviders(MidiFileWriter.clbss);
    }

    @SuppressWbrnings("unchecked")
    privbte stbtic List<MidiFileRebder> getMidiFileRebders() {
        return (List<MidiFileRebder>) getProviders(MidiFileRebder.clbss);
    }


    /** Attempts to locbte bnd return b defbult MidiDevice of the specified
     * type.
     *
     * This method wrbps {@link #getDefbultDevice}. It cbtches the
     * <code>IllegblArgumentException</code> thrown by
     * <code>getDefbultDevice</code> bnd instebd throws b
     * <code>MidiUnbvbilbbleException</code>, with the cbtched
     * exception chbined.
     *
     * @pbrbm deviceClbss The requested device type, one of Synthesizer.clbss,
     * Sequencer.clbss, Receiver.clbss or Trbnsmitter.clbss.
     * @throws  MidiUnbvblbbleException on fbilure.
     */
    privbte stbtic MidiDevice getDefbultDeviceWrbpper(Clbss<?> deviceClbss)
        throws MidiUnbvbilbbleException{
        try {
            return getDefbultDevice(deviceClbss);
        } cbtch (IllegblArgumentException ibe) {
            MidiUnbvbilbbleException mbe = new MidiUnbvbilbbleException();
            mbe.initCbuse(ibe);
            throw mbe;
        }
    }


    /** Attempts to locbte bnd return b defbult MidiDevice of the specified
     * type.
     *
     * @pbrbm deviceClbss The requested device type, one of Synthesizer.clbss,
     * Sequencer.clbss, Receiver.clbss or Trbnsmitter.clbss.
     * @throws  IllegblArgumentException on fbilure.
     */
    privbte stbtic MidiDevice getDefbultDevice(Clbss<?> deviceClbss) {
        List<MidiDeviceProvider> providers = getMidiDeviceProviders();
        String providerClbssNbme = JDK13Services.getDefbultProviderClbssNbme(deviceClbss);
        String instbnceNbme = JDK13Services.getDefbultInstbnceNbme(deviceClbss);
        MidiDevice device;

        if (providerClbssNbme != null) {
            MidiDeviceProvider defbultProvider = getNbmedProvider(providerClbssNbme, providers);
            if (defbultProvider != null) {
                if (instbnceNbme != null) {
                    device = getNbmedDevice(instbnceNbme, defbultProvider, deviceClbss);
                    if (device != null) {
                        return device;
                    }
                }
                device = getFirstDevice(defbultProvider, deviceClbss);
                if (device != null) {
                    return device;
                }
            }
        }

        /* Provider clbss not specified or cbnnot be found, or
           provider clbss specified, bnd no bppropribte device bvbilbble or
           provider clbss bnd instbnce specified bnd instbnce cbnnot be found or is not bppropribte */
        if (instbnceNbme != null) {
            device = getNbmedDevice(instbnceNbme, providers, deviceClbss);
            if (device != null) {
                return device;
            }
        }

        /* No defbult bre specified, or if something is specified, everything
           fbiled. */
        device = getFirstDevice(providers, deviceClbss);
        if (device != null) {
            return device;
        }
        throw new IllegblArgumentException("Requested device not instblled");
    }



    /** Return b MidiDeviceProcider of b given clbss from the list of
        MidiDeviceProviders.

        @pbrbm providerClbssNbme The clbss nbme of the provider to be returned.
        @pbrbm provider The list of MidiDeviceProviders thbt is sebrched.
        @return A MidiDeviceProvider of the requested clbss, or null if none
        is found.
    */
    privbte stbtic MidiDeviceProvider getNbmedProvider(String providerClbssNbme,
                                                       List<MidiDeviceProvider> providers) {
        for(int i = 0; i < providers.size(); i++) {
            MidiDeviceProvider provider = providers.get(i);
            if (provider.getClbss().getNbme().equbls(providerClbssNbme)) {
                return provider;
            }
        }
        return null;
    }


    /** Return b MidiDevice with b given nbme from b given MidiDeviceProvider.
        @pbrbm deviceNbme The nbme of the MidiDevice to be returned.
        @pbrbm provider The MidiDeviceProvider to check for MidiDevices.
        @pbrbm deviceClbss The requested device type, one of Synthesizer.clbss,
        Sequencer.clbss, Receiver.clbss or Trbnsmitter.clbss.

        @return A MidiDevice mbtching the requirements, or null if none is found.
    */
    privbte stbtic MidiDevice getNbmedDevice(String deviceNbme,
                                             MidiDeviceProvider provider,
                                             Clbss<?> deviceClbss) {
        MidiDevice device;
        // try to get MIDI port
        device = getNbmedDevice(deviceNbme, provider, deviceClbss,
                                 fblse, fblse);
        if (device != null) {
            return device;
        }

        if (deviceClbss == Receiver.clbss) {
            // try to get Synthesizer
            device = getNbmedDevice(deviceNbme, provider, deviceClbss,
                                     true, fblse);
            if (device != null) {
                return device;
            }
        }

        return null;
    }


    /** Return b MidiDevice with b given nbme from b given MidiDeviceProvider.
      @pbrbm deviceNbme The nbme of the MidiDevice to be returned.
      @pbrbm provider The MidiDeviceProvider to check for MidiDevices.
      @pbrbm deviceClbss The requested device type, one of Synthesizer.clbss,
      Sequencer.clbss, Receiver.clbss or Trbnsmitter.clbss.

      @return A MidiDevice mbtching the requirements, or null if none is found.
     */
    privbte stbtic MidiDevice getNbmedDevice(String deviceNbme,
                                             MidiDeviceProvider provider,
                                             Clbss<?> deviceClbss,
                                             boolebn bllowSynthesizer,
                                             boolebn bllowSequencer) {
        MidiDevice.Info[] infos = provider.getDeviceInfo();
        for (int i = 0; i < infos.length; i++) {
            if (infos[i].getNbme().equbls(deviceNbme)) {
                MidiDevice device = provider.getDevice(infos[i]);
                if (isAppropribteDevice(device, deviceClbss,
                                        bllowSynthesizer, bllowSequencer)) {
                    return device;
                }
            }
        }
        return null;
    }


    /** Return b MidiDevice with b given nbme from b list of
        MidiDeviceProviders.
        @pbrbm deviceNbme The nbme of the MidiDevice to be returned.
        @pbrbm providers The List of MidiDeviceProviders to check for
        MidiDevices.
        @pbrbm deviceClbss The requested device type, one of Synthesizer.clbss,
        Sequencer.clbss, Receiver.clbss or Trbnsmitter.clbss.
        @return A Mixer mbtching the requirements, or null if none is found.
    */
    privbte stbtic MidiDevice getNbmedDevice(String deviceNbme,
                                             List<MidiDeviceProvider> providers,
                                             Clbss<?> deviceClbss) {
        MidiDevice device;
        // try to get MIDI port
        device = getNbmedDevice(deviceNbme, providers, deviceClbss,
                                 fblse, fblse);
        if (device != null) {
            return device;
        }

        if (deviceClbss == Receiver.clbss) {
            // try to get Synthesizer
            device = getNbmedDevice(deviceNbme, providers, deviceClbss,
                                     true, fblse);
            if (device != null) {
                return device;
            }
        }

        return null;
    }


    /** Return b MidiDevice with b given nbme from b list of
        MidiDeviceProviders.
        @pbrbm deviceNbme The nbme of the MidiDevice to be returned.
        @pbrbm providers The List of MidiDeviceProviders to check for
        MidiDevices.
        @pbrbm deviceClbss The requested device type, one of Synthesizer.clbss,
        Sequencer.clbss, Receiver.clbss or Trbnsmitter.clbss.
        @return A Mixer mbtching the requirements, or null if none is found.
     */
    privbte stbtic MidiDevice getNbmedDevice(String deviceNbme,
                                             List<MidiDeviceProvider> providers,
                                             Clbss<?> deviceClbss,
                                             boolebn bllowSynthesizer,
                                             boolebn bllowSequencer) {
        for(int i = 0; i < providers.size(); i++) {
            MidiDeviceProvider provider = providers.get(i);
            MidiDevice device = getNbmedDevice(deviceNbme, provider,
                                               deviceClbss,
                                               bllowSynthesizer,
                                               bllowSequencer);
            if (device != null) {
                return device;
            }
        }
        return null;
    }


    /** From b given MidiDeviceProvider, return the first bppropribte device.
        @pbrbm provider The MidiDeviceProvider to check for MidiDevices.
        @pbrbm deviceClbss The requested device type, one of Synthesizer.clbss,
        Sequencer.clbss, Receiver.clbss or Trbnsmitter.clbss.
        @return A MidiDevice is considered bppropribte, or null if no
        bppropribte device is found.
    */
    privbte stbtic MidiDevice getFirstDevice(MidiDeviceProvider provider,
                                             Clbss<?> deviceClbss) {
        MidiDevice device;
        // try to get MIDI port
        device = getFirstDevice(provider, deviceClbss,
                                fblse, fblse);
        if (device != null) {
            return device;
        }

        if (deviceClbss == Receiver.clbss) {
            // try to get Synthesizer
            device = getFirstDevice(provider, deviceClbss,
                                    true, fblse);
            if (device != null) {
                return device;
            }
        }

        return null;
    }


    /** From b given MidiDeviceProvider, return the first bppropribte device.
        @pbrbm provider The MidiDeviceProvider to check for MidiDevices.
        @pbrbm deviceClbss The requested device type, one of Synthesizer.clbss,
        Sequencer.clbss, Receiver.clbss or Trbnsmitter.clbss.
        @return A MidiDevice is considered bppropribte, or null if no
        bppropribte device is found.
     */
    privbte stbtic MidiDevice getFirstDevice(MidiDeviceProvider provider,
                                             Clbss<?> deviceClbss,
                                             boolebn bllowSynthesizer,
                                             boolebn bllowSequencer) {
        MidiDevice.Info[] infos = provider.getDeviceInfo();
        for (int j = 0; j < infos.length; j++) {
            MidiDevice device = provider.getDevice(infos[j]);
            if (isAppropribteDevice(device, deviceClbss,
                                    bllowSynthesizer, bllowSequencer)) {
                return device;
            }
        }
        return null;
    }


    /** From b List of MidiDeviceProviders, return the first bppropribte
        MidiDevice.
        @pbrbm providers The List of MidiDeviceProviders to sebrch.
        @pbrbm deviceClbss The requested device type, one of Synthesizer.clbss,
        Sequencer.clbss, Receiver.clbss or Trbnsmitter.clbss.
        @return A MidiDevice thbt is considered bppropribte, or null
        if none is found.
    */
    privbte stbtic MidiDevice getFirstDevice(List<MidiDeviceProvider> providers,
                                             Clbss<?> deviceClbss) {
        MidiDevice device;
        // try to get MIDI port
        device = getFirstDevice(providers, deviceClbss,
                                fblse, fblse);
        if (device != null) {
            return device;
        }

        if (deviceClbss == Receiver.clbss) {
            // try to get Synthesizer
            device = getFirstDevice(providers, deviceClbss,
                                    true, fblse);
            if (device != null) {
                return device;
            }
        }

        return null;
    }


    /** From b List of MidiDeviceProviders, return the first bppropribte
        MidiDevice.
        @pbrbm providers The List of MidiDeviceProviders to sebrch.
        @pbrbm deviceClbss The requested device type, one of Synthesizer.clbss,
        Sequencer.clbss, Receiver.clbss or Trbnsmitter.clbss.
        @return A MidiDevice thbt is considered bppropribte, or null
        if none is found.
     */
    privbte stbtic MidiDevice getFirstDevice(List<MidiDeviceProvider> providers,
                                             Clbss<?> deviceClbss,
                                             boolebn bllowSynthesizer,
                                             boolebn bllowSequencer) {
        for(int i = 0; i < providers.size(); i++) {
            MidiDeviceProvider provider = providers.get(i);
            MidiDevice device = getFirstDevice(provider, deviceClbss,
                                               bllowSynthesizer,
                                               bllowSequencer);
            if (device != null) {
                return device;
            }
        }
        return null;
    }


    /** Checks if b MidiDevice is bppropribte.
        If deviceClbss is Synthesizer or Sequencer, b device implementing
        the respective interfbce is considered bppropribte. If deviceClbss
        is Receiver or Trbnsmitter, b device is considered bppropribte if
        it implements neither Synthesizer nor Trbnsmitter, bnd if it cbn
        provide bt lebst one Receiver or Trbnsmitter, respectively.

        @pbrbm device the MidiDevice to test
        @pbrbm bllowSynthesizer if true, Synthesizers bre considered
        bppropribte. Otherwise only pure MidiDevices bre considered
        bppropribte (unless bllowSequencer is true). This flbg only hbs bn
        effect for deviceClbss Receiver bnd Trbnsmitter. For other device
        clbsses (Sequencer bnd Synthesizer), this flbg hbs no effect.
        @pbrbm bllowSequencer if true, Sequencers bre considered
        bppropribte. Otherwise only pure MidiDevices bre considered
        bppropribte (unless bllowSynthesizer is true). This flbg only hbs bn
        effect for deviceClbss Receiver bnd Trbnsmitter. For other device
        clbsses (Sequencer bnd Synthesizer), this flbg hbs no effect.
        @return true if the device is considered bppropribte bccording to the
        rules given bbove, fblse otherwise.
    */
    privbte stbtic boolebn isAppropribteDevice(MidiDevice device,
                                               Clbss<?> deviceClbss,
                                               boolebn bllowSynthesizer,
                                               boolebn bllowSequencer) {
        if (deviceClbss.isInstbnce(device)) {
           // This clbuse is for deviceClbss being either Synthesizer
            // or Sequencer.
            return true;
        } else {
            // Now the cbse thbt deviceClbss is Trbnsmitter or
            // Receiver. If neither bllowSynthesizer nor bllowSequencer is
            // true, we require device instbnces to be
            // neither Synthesizer nor Sequencer, since we only wbnt
            // devices representing MIDI ports.
            // Otherwise, the respective type is bccepted, too
            if ( (! (device instbnceof Sequencer) &&
                  ! (device instbnceof Synthesizer) ) ||
                 ((device instbnceof Sequencer) && bllowSequencer) ||
                 ((device instbnceof Synthesizer) && bllowSynthesizer)) {
                // And of cource, the device hbs to be bble to provide
                // Receivers or Trbnsmitters.
                if ((deviceClbss == Receiver.clbss &&
                     device.getMbxReceivers() != 0) ||
                    (deviceClbss == Trbnsmitter.clbss &&
                     device.getMbxTrbnsmitters() != 0)) {
                    return true;
                }
            }
        }
        return fblse;
    }


    /**
     * Obtbins the set of services currently instblled on the system
     * using the SPI mechbnism in 1.3.
     * @return b List of instbnces of providers for the requested service.
     * If no providers bre bvbilbble, b List of length 0 will be returned.
     */
     privbte stbtic List<?> getProviders(Clbss<?> providerClbss) {
         return JDK13Services.getProviders(providerClbss);
    }
}
