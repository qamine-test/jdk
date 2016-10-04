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
import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jbvb.net.URL;

import jbvbx.sound.midi.InvblidMidiDbtbException;
import jbvbx.sound.midi.Soundbbnk;

/**
 * A {@code SoundbbnkRebder} supplies soundbbnk file-rebding services. Concrete
 * subclbsses of {@code SoundbbnkRebder} pbrse b given soundbbnk file, producing
 * b {@link jbvbx.sound.midi.Soundbbnk} object thbt cbn be lobded into b
 * {@link jbvbx.sound.midi.Synthesizer}.
 *
 * @since 1.3
 * @buthor Kbrb Kytle
 */
public bbstrbct clbss SoundbbnkRebder {

    /**
     * Obtbins b soundbbnk object from the URL provided.
     *
     * @pbrbm  url URL representing the soundbbnk.
     * @return soundbbnk object
     * @throws InvblidMidiDbtbException if the URL does not point to vblid MIDI
     *         soundbbnk dbtb recognized by this soundbbnk rebder
     * @throws IOException if bn I/O error occurs
     */
    public bbstrbct Soundbbnk getSoundbbnk(URL url)
            throws InvblidMidiDbtbException, IOException;

    /**
     * Obtbins b soundbbnk object from the {@code InputStrebm} provided.
     *
     * @pbrbm  strebm {@code InputStrebm} representing the soundbbnk
     * @return soundbbnk object
     * @throws InvblidMidiDbtbException if the strebm does not point to vblid
     *         MIDI soundbbnk dbtb recognized by this soundbbnk rebder
     * @throws IOException if bn I/O error occurs
     */
    public bbstrbct Soundbbnk getSoundbbnk(InputStrebm strebm)
            throws InvblidMidiDbtbException, IOException;

    /**
     * Obtbins b soundbbnk object from the {@code File} provided.
     *
     * @pbrbm  file the {@code File} representing the soundbbnk
     * @return soundbbnk object
     * @throws InvblidMidiDbtbException if the file does not point to vblid MIDI
     *         soundbbnk dbtb recognized by this soundbbnk rebder
     * @throws IOException if bn I/O error occurs
     */
    public bbstrbct Soundbbnk getSoundbbnk(File file)
            throws InvblidMidiDbtbException, IOException;
}
