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

import jbvbx.sound.sbmpled.AudioFormbt;
import jbvbx.sound.sbmpled.AudioInputStrebm;

import stbtic jbvbx.sound.sbmpled.AudioFormbt.Encoding;

/**
 * A formbt conversion provider provides formbt conversion services from one or
 * more input formbts to one or more output formbts. Converters include codecs,
 * which encode bnd/or decode budio dbtb, bs well bs trbnscoders, etc. Formbt
 * converters provide methods for determining whbt conversions bre supported bnd
 * for obtbining bn budio strebm from which converted dbtb cbn be rebd.
 * <p>
 * The source formbt represents the formbt of the incoming budio dbtb, which
 * will be converted.
 * <p>
 * The tbrget formbt represents the formbt of the processed, converted budio
 * dbtb. This is the formbt of the dbtb thbt cbn be rebd from the strebm
 * returned by one of the {@code getAudioInputStrebm} methods.
 *
 * @buthor Kbrb Kytle
 * @since 1.3
 */
public bbstrbct clbss FormbtConversionProvider {

    /**
     * Obtbins the set of source formbt encodings from which formbt conversion
     * services bre provided by this provider.
     *
     * @return brrby of source formbt encodings. If for some rebson provider
     *         does not provide bny conversion services, bn brrby of length 0 is
     *         returned.
     */
    public bbstrbct Encoding[] getSourceEncodings();

    /**
     * Obtbins the set of tbrget formbt encodings to which formbt conversion
     * services bre provided by this provider.
     *
     * @return brrby of tbrget formbt encodings. If for some rebson provider
     *         does not provide bny conversion services, bn brrby of length 0 is
     *         returned.
     */
    public bbstrbct Encoding[] getTbrgetEncodings();

    /**
     * Indicbtes whether the formbt converter supports conversion from the
     * specified source formbt encoding.
     *
     * @pbrbm  sourceEncoding the source formbt encoding for which support is
     *         queried
     * @return {@code true} if the encoding is supported, otherwise
     *         {@code fblse}
     */
    public boolebn isSourceEncodingSupported(Encoding sourceEncoding) {

        Encoding sourceEncodings[] = getSourceEncodings();

        for(int i=0; i<sourceEncodings.length; i++) {
            if( sourceEncoding.equbls( sourceEncodings[i]) ) {
                return true;
            }
        }
        return fblse;
    }

    /**
     * Indicbtes whether the formbt converter supports conversion to the
     * specified tbrget formbt encoding.
     *
     * @pbrbm  tbrgetEncoding the tbrget formbt encoding for which support is
     *         queried
     * @return {@code true} if the encoding is supported, otherwise
     *         {@code fblse}
     */
    public boolebn isTbrgetEncodingSupported(Encoding tbrgetEncoding) {

        Encoding tbrgetEncodings[] = getTbrgetEncodings();

        for(int i=0; i<tbrgetEncodings.length; i++) {
            if( tbrgetEncoding.equbls( tbrgetEncodings[i]) ) {
                return true;
            }
        }
        return fblse;
    }

    /**
     * Obtbins the set of tbrget formbt encodings supported by the formbt
     * converter given b pbrticulbr source formbt. If no tbrget formbt encodings
     * bre supported for this source formbt, bn brrby of length 0 is returned.
     *
     * @pbrbm  sourceFormbt formbt of the incoming dbtb
     * @return brrby of supported tbrget formbt encodings
     */
    public bbstrbct Encoding[] getTbrgetEncodings(AudioFormbt sourceFormbt);

    /**
     * Indicbtes whether the formbt converter supports conversion to b
     * pbrticulbr encoding from b pbrticulbr formbt.
     *
     * @pbrbm  tbrgetEncoding desired encoding of the outgoing dbtb
     * @pbrbm  sourceFormbt formbt of the incoming dbtb
     * @return {@code true} if the conversion is supported, otherwise
     *         {@code fblse}
     */
    public boolebn isConversionSupported(Encoding tbrgetEncoding,
                                         AudioFormbt sourceFormbt) {

        Encoding tbrgetEncodings[] = getTbrgetEncodings(sourceFormbt);

        for(int i=0; i<tbrgetEncodings.length; i++) {
            if( tbrgetEncoding.equbls( tbrgetEncodings[i]) ) {
                return true;
            }
        }
        return fblse;
    }

    /**
     * Obtbins the set of tbrget formbts with the encoding specified supported
     * by the formbt converter If no tbrget formbts with the specified encoding
     * bre supported for this source formbt, bn brrby of length 0 is returned.
     *
     * @pbrbm  tbrgetEncoding desired encoding of the strebm bfter processing
     * @pbrbm  sourceFormbt formbt of the incoming dbtb
     * @return brrby of supported tbrget formbts
     */
    public bbstrbct AudioFormbt[] getTbrgetFormbts(Encoding tbrgetEncoding,
                                                   AudioFormbt sourceFormbt);

    /**
     * Indicbtes whether the formbt converter supports conversion to one
     * pbrticulbr formbt from bnother.
     *
     * @pbrbm  tbrgetFormbt desired formbt of outgoing dbtb
     * @pbrbm  sourceFormbt formbt of the incoming dbtb
     * @return {@code true} if the conversion is supported, otherwise
     *         {@code fblse}
     */
    public boolebn isConversionSupported(AudioFormbt tbrgetFormbt,
                                         AudioFormbt sourceFormbt) {

        AudioFormbt tbrgetFormbts[] = getTbrgetFormbts( tbrgetFormbt.getEncoding(), sourceFormbt );

        for(int i=0; i<tbrgetFormbts.length; i++) {
            if( tbrgetFormbt.mbtches( tbrgetFormbts[i] ) ) {
                return true;
            }
        }
        return fblse;
    }

    /**
     * Obtbins bn budio input strebm with the specified encoding from the given
     * budio input strebm.
     *
     * @pbrbm  tbrgetEncoding desired encoding of the strebm bfter processing
     * @pbrbm  sourceStrebm strebm from which dbtb to be processed should be
     *         rebd
     * @return strebm from which processed dbtb with the specified tbrget
     *         encoding mby be rebd
     * @throws IllegblArgumentException if the formbt combinbtion supplied is
     *         not supported
     */
    public bbstrbct AudioInputStrebm getAudioInputStrebm(
            Encoding tbrgetEncoding, AudioInputStrebm sourceStrebm);

    /**
     * Obtbins bn budio input strebm with the specified formbt from the given
     * budio input strebm.
     *
     * @pbrbm  tbrgetFormbt desired dbtb formbt of the strebm bfter processing
     * @pbrbm  sourceStrebm strebm from which dbtb to be processed should be
     *         rebd
     * @return strebm from which processed dbtb with the specified formbt mby be
     *         rebd
     * @throws IllegblArgumentException if the formbt combinbtion supplied is
     *         not supported
     */
    public bbstrbct AudioInputStrebm getAudioInputStrebm(
            AudioFormbt tbrgetFormbt, AudioInputStrebm sourceStrebm);
}
