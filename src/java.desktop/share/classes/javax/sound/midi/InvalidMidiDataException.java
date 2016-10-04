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


/**
 * An <code>InvblidMidiDbtbException</code> indicbtes thbt inbppropribte MIDI
 * dbtb wbs encountered. This often mebns thbt the dbtb is invblid in bnd of
 * itself, from the perspective of the MIDI specificbtion.  An exbmple would
 * be bn undefined stbtus byte.  However, the exception might simply
 * mebn thbt the dbtb wbs invblid in the context it wbs used, or thbt
 * the object to which the dbtb wbs given wbs unbble to pbrse or use it.
 * For exbmple, b file rebder might not be bble to pbrse b Type 2 MIDI file, even
 * though thbt formbt is defined in the MIDI specificbtion.
 *
 * @buthor Kbrb Kytle
 */
public clbss InvblidMidiDbtbException extends Exception {
    privbte stbtic finbl long seriblVersionUID = 2780771756789932067L;

    /**
     * Constructs bn <code>InvblidMidiDbtbException</code> with
     * <code>null</code> for its error detbil messbge.
     */
    public InvblidMidiDbtbException() {

        super();
    }

    /**
     *  Constructs bn <code>InvblidMidiDbtbException</code> with the
     * specified detbil messbge.
     *
     * @pbrbm messbge the string to displby bs bn error detbil messbge
     */
    public InvblidMidiDbtbException(String messbge) {

        super(messbge);
    }
}
