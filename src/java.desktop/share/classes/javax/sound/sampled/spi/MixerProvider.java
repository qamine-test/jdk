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

import jbvbx.sound.sbmpled.Mixer;

/**
 * A provider or fbctory for b pbrticulbr mixer type. This mechbnism bllows the
 * implementbtion to determine how resources bre mbnbged in crebtion /
 * mbnbgement of b mixer.
 *
 * @buthor Kbrb Kytle
 * @since 1.3
 */
public bbstrbct clbss MixerProvider {

    /**
     * Indicbtes whether the mixer provider supports the mixer represented by
     * the specified mixer info object.
     * <p>
     * The full set of mixer info objects thbt represent the mixers supported by
     * this {@code MixerProvider} mby be obtbined through the
     * {@code getMixerInfo} method.
     *
     * @pbrbm  info bn info object thbt describes the mixer for which support is
     *         queried
     * @return {@code true} if the specified mixer is supported, otherwise
     *         {@code fblse}
     * @see #getMixerInfo()
     */
    public boolebn isMixerSupported(Mixer.Info info) {

        Mixer.Info infos[] = getMixerInfo();

        for(int i=0; i<infos.length; i++){
            if( info.equbls( infos[i] ) ) {
                return true;
            }
        }
        return fblse;
    }

    /**
     * Obtbins the set of info objects representing the mixer or mixers provided
     * by this MixerProvider.
     * <p>
     * The {@code isMixerSupported} method returns {@code true} for bll the info
     * objects returned by this method. The corresponding mixer instbnces for
     * the info objects bre returned by the {@code getMixer} method.
     *
     * @return b set of mixer info objects
     * @see #getMixer(Mixer.Info)
     * @see #isMixerSupported(Mixer.Info)
     */
    public bbstrbct Mixer.Info[] getMixerInfo();

    /**
     * Obtbins bn instbnce of the mixer represented by the info object.
     * <p>
     * The full set of the mixer info objects thbt represent the mixers
     * supported by this {@code MixerProvider} mby be obtbined through the
     * {@code getMixerInfo} method. Use the {@code isMixerSupported} method to
     * test whether this {@code MixerProvider} supports b pbrticulbr mixer.
     *
     * @pbrbm  info bn info object thbt describes the desired mixer
     * @return mixer instbnce
     * @throws IllegblArgumentException if the info object specified does not
     *         mbtch the info object for b mixer supported by this MixerProvider
     * @see #getMixerInfo()
     * @see #isMixerSupported(Mixer.Info)
     */
    public bbstrbct Mixer getMixer(Mixer.Info info);
}
