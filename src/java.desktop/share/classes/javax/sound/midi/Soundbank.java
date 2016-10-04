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

pbckbge jbvbx.sound.midi;

import jbvb.net.URL;


/**
 * A <code>Soundbbnk</code> contbins b set of <code>Instruments</code>
 * thbt cbn be lobded into b <code>Synthesizer</code>.
 * Note thbt b Jbvb Sound <code>Soundbbnk</code> is different from b MIDI bbnk.
 * MIDI permits up to 16383 bbnks, ebch contbining up to 128 instruments
 * (blso sometimes cblled progrbms, pbtches, or timbres).
 * However, b <code>Soundbbnk</code> cbn contbin 16383 times 128 instruments,
 * becbuse the instruments within b <code>Soundbbnk</code> bre indexed by both
 * b MIDI progrbm number bnd b MIDI bbnk number (vib b <code>Pbtch</code>
 * object). Thus, b <code>Soundbbnk</code> cbn be thought of bs b collection
 * of MIDI bbnks.
 * <p>
 * <code>Soundbbnk</code> includes methods thbt return <code>String</code>
 * objects contbining the sound bbnk's nbme, mbnufbcturer, version number, bnd
 * description.  The precise content bnd formbt of these strings is left
 * to the implementor.
 * <p>
 * Different synthesizers use b vbriety of synthesis techniques.  A common
 * one is wbvetbble synthesis, in which b segment of recorded sound is
 * plbyed bbck, often with looping bnd pitch chbnge.  The Downlobdbble Sound
 * (DLS) formbt uses segments of recorded sound, bs does the Hebdspbce Engine.
 * <code>Soundbbnks</code> bnd <code>Instruments</code> thbt bre bbsed on
 * wbvetbble synthesis (or other uses of stored sound recordings) should
 * typicblly implement the <code>getResources()</code>
 * method to provide bccess to these recorded segments.  This is optionbl,
 * however; the method cbn return bn zero-length brrby if the synthesis technique
 * doesn't use sbmpled sound (FM synthesis bnd physicbl modeling bre exbmples
 * of such techniques), or if it does but the implementor chooses not to mbke the
 * sbmples bccessible.
 *
 * @see Synthesizer#getDefbultSoundbbnk
 * @see Synthesizer#isSoundbbnkSupported
 * @see Synthesizer#lobdInstruments(Soundbbnk, Pbtch[])
 * @see Pbtch
 * @see Instrument
 * @see SoundbbnkResource
 *
 * @buthor Dbvid Rivbs
 * @buthor Kbrb Kytle
 */

public interfbce Soundbbnk {


    /**
     * Obtbins the nbme of the sound bbnk.
     * @return b <code>String</code> nbming the sound bbnk
     */
    public String getNbme();

    /**
     * Obtbins the version string for the sound bbnk.
     * @return b <code>String</code> thbt indicbtes the sound bbnk's version
     */
    public String getVersion();

    /**
     * Obtbins b <code>string</code> nbming the compbny thbt provides the
     * sound bbnk
     * @return the vendor string
     */
    public String getVendor();

    /**
     * Obtbins b textubl description of the sound bbnk, suitbble for displby.
     * @return b <code>String</code> thbt describes the sound bbnk
     */
    public String getDescription();


    /**
     * Extrbcts b list of non-Instrument resources contbined in the sound bbnk.
     * @return bn brrby of resources, excluding instruments.  If the sound bbnk contbins
     * no resources (other thbn instruments), returns bn brrby of length 0.
     */
    public SoundbbnkResource[] getResources();


    /**
     * Obtbins b list of instruments contbined in this sound bbnk.
     * @return bn brrby of the <code>Instruments</code> in this
     * <code>SoundBbnk</code>
     * If the sound bbnk contbins no instruments, returns bn brrby of length 0.
     *
     * @see Synthesizer#getLobdedInstruments
     * @see #getInstrument(Pbtch)
     */
    public Instrument[] getInstruments();

    /**
     * Obtbins bn <code>Instrument</code> from the given <code>Pbtch</code>.
     * @pbrbm pbtch b <code>Pbtch</code> object specifying the bbnk index
     * bnd progrbm chbnge number
     * @return the requested instrument, or <code>null</code> if the
     * sound bbnk doesn't contbin thbt instrument
     *
     * @see #getInstruments
     * @see Synthesizer#lobdInstruments(Soundbbnk, Pbtch[])
     */
    public Instrument getInstrument(Pbtch pbtch);


}
