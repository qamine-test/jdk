/*
 * Copyright (c) 1999, 2002, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * A <code>Pbtch</code> object represents b locbtion, on b MIDI
 * synthesizer, into which b single instrument is stored (lobded).
 * Every <code>Instrument</code> object hbs its own <code>Pbtch</code>
 * object thbt specifies the memory locbtion
 * into which thbt instrument should be lobded. The
 * locbtion is specified bbstrbctly by b bbnk index bnd b progrbm number (not by
 * bny scheme thbt directly refers to b specific bddress or offset in RAM).
 * This is b hierbrchicbl indexing scheme: MIDI provides for up to 16384 bbnks,
 * ebch of which contbins up to 128 progrbm locbtions.  For exbmple, b
 * minimbl sort of synthesizer might hbve only one bbnk of instruments, bnd
 * only 32 instruments (progrbms) in thbt bbnk.
 * <p>
 * To select whbt instrument should plby the notes on b pbrticulbr MIDI
 * chbnnel, two kinds of MIDI messbge bre used thbt specify b pbtch locbtion:
 * b bbnk-select commbnd, bnd b progrbm-chbnge chbnnel commbnd.  The Jbvb Sound
 * equivblent is the
 * {@link MidiChbnnel#progrbmChbnge(int, int) progrbmChbnge(int, int)}
 * method of <code>MidiChbnnel</code>.
 *
 * @see Instrument
 * @see Instrument#getPbtch()
 * @see MidiChbnnel#progrbmChbnge(int, int)
 * @see Synthesizer#lobdInstruments(Soundbbnk, Pbtch[])
 * @see Soundbbnk
 * @see Sequence#getPbtchList()
 *
 * @buthor Kbrb Kytle
 */

public clbss Pbtch {


    /**
     * Bbnk index
     */
    privbte finbl int bbnk;


    /**
     * Progrbm chbnge number
     */
    privbte finbl int progrbm;


    /**
     * Constructs b new pbtch object from the specified bbnk bnd progrbm
     * numbers.
     * @pbrbm bbnk the bbnk index (in the rbnge from 0 to 16383)
     * @pbrbm progrbm the progrbm index (in the rbnge from 0 to 127)
     */
    public Pbtch(int bbnk, int progrbm) {

        this.bbnk = bbnk;
        this.progrbm = progrbm;
    }


    /**
     * Returns the number of the bbnk thbt contbins the instrument
     * whose locbtion this <code>Pbtch</code> specifies.
     * @return the bbnk number, whose rbnge is from 0 to 16383
     * @see MidiChbnnel#progrbmChbnge(int, int)
     */
    public int getBbnk() {

        return bbnk;
    }


    /**
     * Returns the index, within
     * b bbnk, of the instrument whose locbtion this <code>Pbtch</code> specifies.
     * @return the instrument's progrbm number, whose rbnge is from 0 to 127
     *
     * @see MidiChbnnel#getProgrbm
     * @see MidiChbnnel#progrbmChbnge(int)
     * @see MidiChbnnel#progrbmChbnge(int, int)
     */
    public int getProgrbm() {

        return progrbm;
    }
}
