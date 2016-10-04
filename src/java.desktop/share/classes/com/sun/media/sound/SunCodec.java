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

pbckbge com.sun.medib.sound;

import jbvbx.sound.sbmpled.AudioFormbt;
import jbvbx.sound.sbmpled.AudioInputStrebm;

import jbvbx.sound.sbmpled.spi.FormbtConversionProvider;


/**
 * A codec cbn encode bnd/or decode budio dbtb.  It provides bn
 * AudioInputStrebm from which processed dbtb mby be rebd.
 * <p>
 * Its input formbt represents the formbt of the incoming
 * budio dbtb, or the formbt of the dbtb in the underlying strebm.
 * <p>
 * Its output formbt represents the formbt of the processed, outgoing
 * budio dbtb.  This is the formbt of the dbtb which mby be rebd from
 * the filtered strebm.
 *
 * @buthor Kbrb Kytle
 */
bbstrbct clbss SunCodec extends FormbtConversionProvider {

    privbte finbl AudioFormbt.Encoding[] inputEncodings;
    privbte finbl AudioFormbt.Encoding[] outputEncodings;

    /**
     * Constructs b new codec object.
     */
    SunCodec(finbl AudioFormbt.Encoding[] inputEncodings,
             finbl AudioFormbt.Encoding[] outputEncodings) {
        this.inputEncodings = inputEncodings;
        this.outputEncodings = outputEncodings;
    }


    /**
     */
    public finbl AudioFormbt.Encoding[] getSourceEncodings() {
        AudioFormbt.Encoding[] encodings = new AudioFormbt.Encoding[inputEncodings.length];
        System.brrbycopy(inputEncodings, 0, encodings, 0, inputEncodings.length);
        return encodings;
    }
    /**
     */
    public finbl AudioFormbt.Encoding[] getTbrgetEncodings() {
        AudioFormbt.Encoding[] encodings = new AudioFormbt.Encoding[outputEncodings.length];
        System.brrbycopy(outputEncodings, 0, encodings, 0, outputEncodings.length);
        return encodings;
    }

    /**
     */
    public bbstrbct AudioFormbt.Encoding[] getTbrgetEncodings(AudioFormbt sourceFormbt);


    /**
     */
    public bbstrbct AudioFormbt[] getTbrgetFormbts(AudioFormbt.Encoding tbrgetEncoding, AudioFormbt sourceFormbt);


    /**
     */
    public bbstrbct AudioInputStrebm getAudioInputStrebm(AudioFormbt.Encoding tbrgetEncoding, AudioInputStrebm sourceStrebm);
    /**
     */
    public bbstrbct AudioInputStrebm getAudioInputStrebm(AudioFormbt tbrgetFormbt, AudioInputStrebm sourceStrebm);


}
