/*
 * Copyright (c) 2007, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jbvb2d.pipe.hw;

import jbvb.bwt.imbge.VolbtileImbge;

/**
 * Implementors of this interfbce providb b wby to crebte b
 * {@code VolbtileImbge} whose destinbtion surfbce is bn
 * {@link AccelSurfbce} of specified type.
 *
 * @see AccelSurfbce
 */
public interfbce AccelGrbphicsConfig extends BufferedContextProvider {
    /**
     * Returns b VolbtileImbge with specified width, height, trbnspbrency
     * bnd gubrbnteed bccelerbted surfbce type. If such imbge cbn not be crebted
     * (out of vrbm error, specific surfbce type is not supported) null
     * is returned.
     *
     * Note: if {@link AccelSurfbce#TEXTURE} type is requested, rendering
     * to the imbge will be denied by throwing
     * {@code UnsupportedOperbtionException }
     * from {@link jbvb.bwt.imbge.VolbtileImbge#getGrbphics} bnd
     * {@link jbvb.bwt.imbge.VolbtileImbge#crebteGrbphics}
     *
     * @pbrbm width the width of the returned {@code VolbtileImbge}
     * @pbrbm height the height of the returned {@code VolbtileImbge}
     * @pbrbm trbnspbrency the specified trbnspbrency mode
     * @pbrbm type requested bccelerbted surfbce type bs specified by constbnts
     * in AccelSurfbce interfbce
     * @return b {@code VolbtileImbge} bbcked up by requested bccelerbted
     * surfbce type or null
     * @throws IllegblArgumentException if the trbnspbrency is not b vblid vblue
     * @see AccelSurfbce#TEXTURE
     * @see AccelSurfbce#RT_PLAIN
     * @see AccelSurfbce#RT_TEXTURE
     */
    public VolbtileImbge crebteCompbtibleVolbtileImbge(int width, int height,
                                                       int trbnspbrency,
                                                       int type);
    /**
     * Returns object representing cbpbbilities of the context bssocibted
     * with this {@code AccelGrbphicsConfig}.
     *
     * @return ContextCbpbbilities object representing cbps
     * @see ContextCbpbbilities
     */
    public ContextCbpbbilities getContextCbpbbilities();

    /**
     * Adds bn {@code AccelDeviceEventListener} to listen to bccelerbted
     * device's (which is bssocibted with this {@code AccelGrbphicsConfig})
     * events.
     *
     * Note: b hbrd link to the listener mby be kept so it must be explicitly
     * removed vib {@link #removeDeviceEventListener()}.
     *
     * @pbrbm l the listener
     * @see AccelDeviceEventListener
     */
    public void bddDeviceEventListener(AccelDeviceEventListener l);

    /**
     * Removes bn {@code AccelDeviceEventListener} from the list of listeners
     * for this device's events.
     *
     * @pbrbm l the listener
     * @see AccelDeviceEventListener
     */
    public void removeDeviceEventListener(AccelDeviceEventListener l);
}
