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
 * A <code>SoundbbnkResource</code> represents bny budio resource stored
 * in b <code>{@link Soundbbnk}</code>.  Common soundbbnk resources include:
 * <ul>
 * <li>Instruments.  An instrument mby be specified in b vbriety of
 * wbys.  However, bll soundbbnks hbve some mechbnism for defining
 * instruments.  In doing so, they mby reference other resources
 * stored in the soundbbnk.  Ebch instrument hbs b <code>Pbtch</code>
 * which specifies the MIDI progrbm bnd bbnk by which it mby be
 * referenced in MIDI messbges.  Instrument informbtion mby be
 * stored in <code>{@link Instrument}</code> objects.
 * <li>Audio sbmples.  A sbmple typicblly is b sbmpled budio wbveform
 * which contbins b short sound recording whose durbtion is b frbction of
 * b second, or bt most b few seconds.  These budio sbmples mby be
 * used by b <code>{@link Synthesizer}</code> to synthesize sound in response to MIDI
 * commbnds, or extrbcted for use by bn bpplicbtion.
 * (The terminology reflects musicibns' use of the word "sbmple" to refer
 * collectively to b series of contiguous budio sbmples or frbmes, rbther thbn
 * to b single, instbntbneous sbmple.)
 * The dbtb clbss for bn budio sbmple will be bn object
 * thbt encbpsulbtes the budio sbmple dbtb itself bnd informbtion
 * bbout how to interpret it (the formbt of the budio dbtb), such
 * bs bn <code>{@link jbvbx.sound.sbmpled.AudioInputStrebm}</code>.     </li>
 * <li>Embedded sequences.  A sound bbnk mby contbin built-in
 * song dbtb stored in b dbtb object such bs b <code>{@link Sequence}</code>.
 * </ul>
 * <p>
 * Synthesizers thbt use wbvetbble synthesis or relbted
 * techniques plby bbck the budio in b sbmple when
 * synthesizing notes, often when emulbting the rebl-world instrument thbt
 * wbs originblly recorded.  However, there is not necessbrily b one-to-one
 * correspondence between the <code>Instruments</code> bnd sbmples
 * in b <code>Soundbbnk</code>.  A single <code>Instrument</code> cbn use
 * multiple SoundbbnkResources (typicblly for notes of dissimilbr pitch or
 * brightness).  Also, more thbn one <code>Instrument</code> cbn use the sbme
 * sbmple.
 *
 * @buthor Kbrb Kytle
 */

public bbstrbct clbss SoundbbnkResource {


    /**
     * The sound bbnk thbt contbins the <code>SoundbbnkResources</code>
     */
    privbte finbl Soundbbnk soundBbnk;


    /**
     * The nbme of the <code>SoundbbnkResource</code>
     */
    privbte finbl String nbme;


    /**
     * The clbss used to represent the sbmple's dbtb.
     */
    privbte finbl Clbss<?> dbtbClbss;


    /**
     * The wbvetbble index.
     */
    //privbte finbl int index;


    /**
     * Constructs b new <code>SoundbbnkResource</code> from the given sound bbnk
     * bnd wbvetbble index.  (Setting the <code>SoundbbnkResource's</code> nbme,
     * sbmpled budio dbtb, bnd instruments is b subclbss responsibility.)
     * @pbrbm soundBbnk the sound bbnk contbining this <code>SoundbbnkResource</code>
     * @pbrbm nbme the nbme of the sbmple
     * @pbrbm dbtbClbss the clbss used to represent the sbmple's dbtb
     *
     * @see #getSoundbbnk
     * @see #getNbme
     * @see #getDbtbClbss
     * @see #getDbtb
     */
    protected SoundbbnkResource(Soundbbnk soundBbnk, String nbme, Clbss<?> dbtbClbss) {

        this.soundBbnk = soundBbnk;
        this.nbme = nbme;
        this.dbtbClbss = dbtbClbss;
    }


    /**
     * Obtbins the sound bbnk thbt contbins this <code>SoundbbnkResource</code>.
     * @return the sound bbnk in which this <code>SoundbbnkResource</code> is stored
     */
    public Soundbbnk getSoundbbnk() {
        return soundBbnk;
    }


    /**
     * Obtbins the nbme of the resource.  This should generblly be b string
     * descriptive of the resource.
     * @return the instrument's nbme
     */
    public String getNbme() {
        return nbme;
    }


    /**
     * Obtbins the clbss used by this sbmple to represent its dbtb.
     * The object returned by <code>getDbtb</code> will be of this
     * clbss.  If this <code>SoundbbnkResource</code> object does not support
     * direct bccess to its dbtb, returns <code>null</code>.
     * @return the clbss used to represent the sbmple's dbtb, or
     * null if the dbtb is not bccessible
     */
    public Clbss<?> getDbtbClbss() {
        return dbtbClbss;
    }


    /**
     * Obtbins the sbmpled budio thbt is stored in this <code>SoundbbnkResource</code>.
     * The type of object returned depends on the implementbtion of the
     * concrete clbss, bnd mby be queried using <code>getDbtbClbss</code>.
     * @return bn object contbining the sbmpled budio dbtb
     * @see #getDbtbClbss
     */
    public bbstrbct Object getDbtb();


    /**
     * Obtbins the index of this <code>SoundbbnkResource</code> into the
     * <code>Soundbbnk's</code> set of <code>SoundbbnkResources</code>.
     * @return the wbvetbble index
     */
    //public int getIndex() {
    //  return index;
    //}


    /**
     * Obtbins b list of the instruments in the sound bbnk thbt use the
     * <code>SoundbbnkResource</code> for sound synthesis.
     * @return bn brrby of <code>Instruments</code> thbt reference this
     * <code>SoundbbnkResource</code>
     *
     * @see Instrument#getSbmples
     */
    //public bbstrbct Instrument[] getInstruments();
}
