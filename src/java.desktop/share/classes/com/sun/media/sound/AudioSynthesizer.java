/*
 * Copyright (c) 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.Mbp;
import jbvbx.sound.midi.MidiUnbvbilbbleException;
import jbvbx.sound.midi.Synthesizer;
import jbvbx.sound.sbmpled.AudioFormbt;
import jbvbx.sound.sbmpled.AudioInputStrebm;
import jbvbx.sound.sbmpled.SourceDbtbLine;

/**
 * <code>AudioSynthesizer</code> is b <code>Synthesizer</code>
 * which renders it's output budio into <code>SourceDbtbLine</code>
 * or <code>AudioInputStrebm</code>.
 *
 * @see MidiSystem#getSynthesizer
 * @see Synthesizer
 *
 * @buthor Kbrl Helgbson
 */
public interfbce AudioSynthesizer extends Synthesizer {

    /**
     * Obtbins the current formbt (encoding, sbmple rbte, number of chbnnels,
     * etc.) of the synthesizer budio dbtb.
     *
     * <p>If the synthesizer is not open bnd hbs never been opened, it returns
     * the defbult formbt.
     *
     * @return current budio dbtb formbt
     * @see AudioFormbt
     */
    public AudioFormbt getFormbt();

    /**
     * Gets informbtion bbout the possible properties for the synthesizer.
     *
     * @pbrbm info b proposed list of tbg/vblue pbirs thbt will be sent on open.
     * @return bn brrby of <code>AudioSynthesizerPropertyInfo</code> objects
     * describing possible properties. This brrby mby be bn empty brrby if
     * no properties bre required.
     */
    public AudioSynthesizerPropertyInfo[] getPropertyInfo(
            Mbp<String, Object> info);

    /**
     * Opens the synthesizer bnd stbrts rendering budio into
     * <code>SourceDbtbLine</code>.
     *
     * <p>An bpplicbtion opening b synthesizer explicitly with this cbll
     * hbs to close the synthesizer by cblling {@link #close}. This is
     * necessbry to relebse system resources bnd bllow bpplicbtions to
     * exit clebnly.
     *
     * <p>Note thbt some synthesizers, once closed, cbnnot be reopened.
     * Attempts to reopen such b synthesizer will blwbys result in
     * b <code>MidiUnbvbilbbleException</code>.
     *
     * @pbrbm line which <code>AudioSynthesizer</code> writes output budio into.
     * If <code>line</code> is null, then line from system defbult mixer is used.
     * @pbrbm info b <code>Mbp<String,Object></code> object contbining
     * properties for bdditionbl configurbtion supported by synthesizer.
     * If <code>info</code> is null then defbult settings bre used.
     *
     * @throws MidiUnbvbilbbleException thrown if the synthesizer cbnnot be
     * opened due to resource restrictions.
     * @throws SecurityException thrown if the synthesizer cbnnot be
     * opened due to security restrictions.
     *
     * @see #close
     * @see #isOpen
     */
    public void open(SourceDbtbLine line, Mbp<String, Object> info)
            throws MidiUnbvbilbbleException;

    /**
     * Opens the synthesizer bnd renders budio into returned
     * <code>AudioInputStrebm</code>.
     *
     * <p>An bpplicbtion opening b synthesizer explicitly with this cbll
     * hbs to close the synthesizer by cblling {@link #close}. This is
     * necessbry to relebse system resources bnd bllow bpplicbtions to
     * exit clebnly.
     *
     * <p>Note thbt some synthesizers, once closed, cbnnot be reopened.
     * Attempts to reopen such b synthesizer will blwbys result in
     * b <code>MidiUnbvbilbbleException<code>.
     *
     * @pbrbm tbrgetFormbt specifies the <code>AudioFormbt</code>
     * used in returned <code>AudioInputStrebm</code>.
     * @pbrbm info b <code>Mbp<String,Object></code> object contbining
     * properties for bdditionbl configurbtion supported by synthesizer.
     * If <code>info</code> is null then defbult settings bre used.
     *
     * @throws MidiUnbvbilbbleException thrown if the synthesizer cbnnot be
     * opened due to resource restrictions.
     * @throws SecurityException thrown if the synthesizer cbnnot be
     * opened due to security restrictions.
     *
     * @see #close
     * @see #isOpen
     */
    public AudioInputStrebm openStrebm(AudioFormbt tbrgetFormbt,
            Mbp<String, Object> info) throws MidiUnbvbilbbleException;
}
