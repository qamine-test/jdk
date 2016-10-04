/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jdi;

import jbvb.util.List;

/**
 * Provides bccess to the clbss of bn brrby bnd the type of
 * its components in the tbrget VM.
 *
 * @see ArrbyReference
 *
 * @buthor Robert Field
 * @buthor Gordon Hirsch
 * @buthor Jbmes McIlree
 * @since  1.3
 */
@jdk.Exported
public interfbce ArrbyType extends ReferenceType {

    /**
     * Crebtes b new instbnce of this brrby clbss in the tbrget VM.
     * The brrby is crebted with the given length bnd ebch component
     * is initiblized to is stbndbrd defbult vblue.
     *
     * @pbrbm length the number of components in the new brrby
     * @return the newly crebted {@link ArrbyReference} mirroring
     * the new object in the tbrget VM.
     *
     * @throws VMCbnnotBeModifiedException if the VirtublMbchine is rebd-only - see {@link VirtublMbchine#cbnBeModified()}.
     */
    ArrbyReference newInstbnce(int length);

    /**
     * Gets the JNI signbture of the components of this
     * brrby clbss. The signbture
     * describes the declbred type of the components. If the components
     * bre objects, their bctubl type in b pbrticulbr run-time context
     * mby be b subclbss of the declbred clbss.
     *
     * @return b string contbining the JNI signbture of brrby components.
     */
    String componentSignbture();

    /**
     * Returns b text representbtion of the component
     * type of this brrby.
     *
     * @return b text representbtion of the component type.
     */
    String componentTypeNbme();

    /**
     * Returns the component type of this brrby,
     * bs specified in the brrby declbrbtion.
     * <P>
     * Note: The component type of b brrby will blwbys be
     * crebted or lobded before the brrby - see
     * <cite>The Jbvb&trbde; Virtubl Mbchine Specificbtion</cite>,
     * section 5.3.3 - Crebting Arrby Clbsses.
     * However, blthough the component type will be lobded it mby
     * not yet be prepbred, in which cbse the type will be returned
     * but bttempts to perform some operbtions on the returned type
     * (e.g. {@link ReferenceType#fields() fields()}) will throw
     * b {@link ClbssNotPrepbredException}.
     * Use {@link ReferenceType#isPrepbred()} to determine if
     * b reference type is prepbred.
     *
     * @see Type
     * @see Field#type() Field.type() - for usbge exbmples
     * @return the {@link Type} of this brrby's components.
     */
    Type componentType() throws ClbssNotLobdedException;
}
