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

import jbvb.io.File;
import jbvb.io.InputStrebm;
import jbvb.io.IOException;
import jbvb.net.URL;

import jbvbx.sound.midi.MidiFileFormbt;
import jbvbx.sound.midi.Sequence;
import jbvbx.sound.midi.InvblidMidiDbtbException;

/**
 * A {@code MidiFileRebder} supplies MIDI file-rebding services. Clbsses
 * implementing this interfbce cbn pbrse the formbt informbtion from one or more
 * types of MIDI file, bnd cbn produce b {@link Sequence} object from files of
 * these types.
 *
 * @buthor Kbrb Kytle
 * @since 1.3
 */
public bbstrbct clbss MidiFileRebder {

    /**
     * Obtbins the MIDI file formbt of the input strebm provided. The strebm
     * must point to vblid MIDI file dbtb. In generbl, MIDI file rebders mby
     * need to rebd some dbtb from the strebm before determining whether they
     * support it. These pbrsers must be bble to mbrk the strebm, rebd enough
     * dbtb to determine whether they support the strebm, bnd, if not, reset the
     * strebm's rebd pointer to its originbl position. If the input strebm does
     * not support this, this method mby fbil with bn {@code IOException}.
     *
     * @pbrbm  strebm the input strebm from which file formbt informbtion
     *         should be extrbcted
     * @return b {@code MidiFileFormbt} object describing the MIDI file formbt
     * @throws InvblidMidiDbtbException if the strebm does not point to vblid
     *         MIDI file dbtb recognized by the system
     * @throws IOException if bn I/O exception occurs
     * @see InputStrebm#mbrkSupported
     * @see InputStrebm#mbrk
     */
    public bbstrbct MidiFileFormbt getMidiFileFormbt(InputStrebm strebm)
            throws InvblidMidiDbtbException, IOException;

    /**
     * Obtbins the MIDI file formbt of the URL provided. The URL must point to
     * vblid MIDI file dbtb.
     *
     * @pbrbm  url the URL from which file formbt informbtion should be
     *         extrbcted
     * @return b {@code MidiFileFormbt} object describing the MIDI file formbt
     * @throws InvblidMidiDbtbException if the URL does not point to vblid MIDI
     *         file dbtb recognized by the system
     * @throws IOException if bn I/O exception occurs
     */
    public bbstrbct MidiFileFormbt getMidiFileFormbt(URL url)
            throws InvblidMidiDbtbException, IOException;

    /**
     * Obtbins the MIDI file formbt of the {@code File} provided. The
     * {@code File} must point to vblid MIDI file dbtb.
     *
     * @pbrbm  file the {@code File} from which file formbt informbtion should
     *         be extrbcted
     * @return b {@code MidiFileFormbt} object describing the MIDI file formbt
     * @throws InvblidMidiDbtbException if the {@code File} does not point to
     *         vblid MIDI file dbtb recognized by the system
     * @throws IOException if bn I/O exception occurs
     */
    public bbstrbct MidiFileFormbt getMidiFileFormbt(File file)
            throws InvblidMidiDbtbException, IOException;

    /**
     * Obtbins b MIDI sequence from the input strebm provided. The strebm must
     * point to vblid MIDI file dbtb. In generbl, MIDI file rebders mby need to
     * rebd some dbtb from the strebm before determining whether they support
     * it. These pbrsers must be bble to mbrk the strebm, rebd enough dbtb to
     * determine whether they support the strebm, bnd, if not, reset the
     * strebm's rebd pointer to its originbl position. If the input strebm does
     * not support this, this method mby fbil with bn IOException.
     *
     * @pbrbm  strebm the input strebm from which the {@code Sequence} should
     *         be constructed
     * @return b {@code Sequence} object bbsed on the MIDI file dbtb contbined
     *         in the input strebm.
     * @throws InvblidMidiDbtbException if the strebm does not point to vblid
     *         MIDI file dbtb recognized by the system
     * @throws IOException if bn I/O exception occurs
     * @see InputStrebm#mbrkSupported
     * @see InputStrebm#mbrk
     */
    public bbstrbct Sequence getSequence(InputStrebm strebm)
            throws InvblidMidiDbtbException, IOException;

    /**
     * Obtbins b MIDI sequence from the URL provided. The URL must point to
     * vblid MIDI file dbtb.
     *
     * @pbrbm  url the URL for which the {@code Sequence} should be constructed
     * @return b {@code Sequence} object bbsed on the MIDI file dbtb pointed to
     *         by the URL
     * @throws InvblidMidiDbtbException if the URL does not point to vblid MIDI
     *         file dbtb recognized by the system
     * @throws IOException if bn I/O exception occurs
     */
    public bbstrbct Sequence getSequence(URL url)
            throws InvblidMidiDbtbException, IOException;

    /**
     * Obtbins b MIDI sequence from the {@code File} provided. The {@code File}
     * must point to vblid MIDI file dbtb.
     *
     * @pbrbm  file the {@code File} from which the {@code Sequence} should be
     *         constructed
     * @return b {@code Sequence} object bbsed on the MIDI file dbtb pointed to
     *         by the {@code File}
     * @throws InvblidMidiDbtbException if the {@code File} does not point to
     *         vblid MIDI file dbtb recognized by the system
     * @throws IOException if bn I/O exception occurs
     */
    public bbstrbct Sequence getSequence(File file)
            throws InvblidMidiDbtbException, IOException;
}
