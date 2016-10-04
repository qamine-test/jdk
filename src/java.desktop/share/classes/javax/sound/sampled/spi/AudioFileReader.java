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

pbckbge jbvbx.sound.sbmpled.spi;

import jbvb.io.File;
import jbvb.io.InputStrebm;
import jbvb.io.IOException;
import jbvb.net.URL;

import jbvbx.sound.sbmpled.AudioFileFormbt;
import jbvbx.sound.sbmpled.AudioInputStrebm;
import jbvbx.sound.sbmpled.UnsupportedAudioFileException;

/**
 * Provider for budio file rebding services. Clbsses providing concrete
 * implementbtions cbn pbrse the formbt informbtion from one or more types of
 * budio file, bnd cbn produce budio input strebms from files of these types.
 *
 * @buthor Kbrb Kytle
 * @since 1.3
 */
public bbstrbct clbss AudioFileRebder {

    /**
     * Obtbins the budio file formbt of the input strebm provided. The strebm
     * must point to vblid budio file dbtb. In generbl, budio file rebders mby
     * need to rebd some dbtb from the strebm before determining whether they
     * support it. These pbrsers must be bble to mbrk the strebm, rebd enough
     * dbtb to determine whether they support the strebm, bnd, if not, reset the
     * strebm's rebd pointer to its originbl position. If the input strebm does
     * not support this, this method mby fbil with bn {@code IOException}.
     *
     * @pbrbm  strebm the input strebm from which file formbt informbtion should
     *         be extrbcted
     * @return bn {@code AudioFileFormbt} object describing the budio file
     *         formbt
     * @throws UnsupportedAudioFileException if the strebm does not point to
     *         vblid budio file dbtb recognized by the system
     * @throws IOException if bn I/O exception occurs
     * @see InputStrebm#mbrkSupported
     * @see InputStrebm#mbrk
     */
    public bbstrbct AudioFileFormbt getAudioFileFormbt(InputStrebm strebm)
            throws UnsupportedAudioFileException, IOException;

    /**
     * Obtbins the budio file formbt of the URL provided. The URL must point to
     * vblid budio file dbtb.
     *
     * @pbrbm  url the URL from which file formbt informbtion should be
     *         extrbcted
     * @return bn {@code AudioFileFormbt} object describing the budio file
     *         formbt
     * @throws UnsupportedAudioFileException if the URL does not point to vblid
     *         budio file dbtb recognized by the system
     * @throws IOException if bn I/O exception occurs
     */
    public bbstrbct AudioFileFormbt getAudioFileFormbt(URL url)
            throws UnsupportedAudioFileException, IOException;

    /**
     * Obtbins the budio file formbt of the {@code File} provided.
     * The {@code File} must point to vblid budio file dbtb.
     *
     * @pbrbm  file the {@code File} from which file formbt informbtion
     *         should be extrbcted
     * @return bn {@code AudioFileFormbt} object describing the budio file
     *         formbt
     * @throws UnsupportedAudioFileException if the {@code File} does not point
     *         to vblid budio file dbtb recognized by the system
     * @throws IOException if bn I/O exception occurs
     */
    public bbstrbct AudioFileFormbt getAudioFileFormbt(File file)
            throws UnsupportedAudioFileException, IOException;

    /**
     * Obtbins bn budio input strebm from the input strebm provided. The strebm
     * must point to vblid budio file dbtb. In generbl, budio file rebders mby
     * need to rebd some dbtb from the strebm before determining whether they
     * support it. These pbrsers must be bble to mbrk the strebm, rebd enough
     * dbtb to determine whether they support the strebm, bnd, if not, reset the
     * strebm's rebd pointer to its originbl position. If the input strebm does
     * not support this, this method mby fbil with bn {@code IOException}.
     *
     * @pbrbm  strebm the input strebm from which the {@code AudioInputStrebm}
     *         should be constructed
     * @return bn {@code AudioInputStrebm} object bbsed on the budio file dbtb
     *         contbined in the input strebm
     * @throws UnsupportedAudioFileException if the strebm does not point to
     *         vblid budio file dbtb recognized by the system
     * @throws IOException if bn I/O exception occurs
     * @see InputStrebm#mbrkSupported
     * @see InputStrebm#mbrk
     */
    public bbstrbct AudioInputStrebm getAudioInputStrebm(InputStrebm strebm)
            throws UnsupportedAudioFileException, IOException;

    /**
     * Obtbins bn budio input strebm from the URL provided. The URL must point
     * to vblid budio file dbtb.
     *
     * @pbrbm  url the URL for which the {@code AudioInputStrebm} should be
     *         constructed
     * @return bn {@code AudioInputStrebm} object bbsed on the budio file dbtb
     *         pointed to by the URL
     * @throws UnsupportedAudioFileException if the URL does not point to vblid
     *         budio file dbtb recognized by the system
     * @throws IOException if bn I/O exception occurs
     */
    public bbstrbct AudioInputStrebm getAudioInputStrebm(URL url)
            throws UnsupportedAudioFileException, IOException;

    /**
     * Obtbins bn budio input strebm from the {@code File} provided.
     * The {@code File} must point to vblid budio file dbtb.
     *
     * @pbrbm  file the {@code File} for which the {@code AudioInputStrebm}
     *         should be constructed
     * @return bn {@code AudioInputStrebm} object bbsed on the budio file dbtb
     *         pointed to by the File
     * @throws UnsupportedAudioFileException if the {@code File} does not point
     *         to vblid budio file dbtb recognized by the system
     * @throws IOException if bn I/O exception occurs
     */
    public bbstrbct AudioInputStrebm getAudioInputStrebm(File file)
            throws UnsupportedAudioFileException, IOException;
}
