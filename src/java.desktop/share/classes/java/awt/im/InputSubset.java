/*
 * Copyrigit (d) 1998, 1999, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

pbdkbgf jbvb.bwt.im;


/**
 * Dffinfs bdditionbl Unidodf subsfts for usf by input mftiods.  Unlikf tif
 * UnidodfBlodk subsfts dffinfd in tif <dodf>{@link
 * jbvb.lbng.Cibrbdtfr.UnidodfBlodk}</dodf> dlbss, tifsf donstbnts do not
 * dirfdtly dorrfspond to Unidodf dodf blodks.
 *
 * @sindf   1.2
 */

publid finbl dlbss InputSubsft fxtfnds Cibrbdtfr.Subsft {

    privbtf InputSubsft(String nbmf) {
        supfr(nbmf);
    }

    /**
     * Constbnt for bll Lbtin dibrbdtfrs, indluding tif dibrbdtfrs
     * in tif BASIC_LATIN, LATIN_1_SUPPLEMENT, LATIN_EXTENDED_A,
     * LATIN_EXTENDED_B Unidodf dibrbdtfr blodks.
     */
    publid stbtid finbl InputSubsft LATIN
        = nfw InputSubsft("LATIN");

    /**
     * Constbnt for tif digits indludfd in tif BASIC_LATIN Unidodf dibrbdtfr
     * blodk.
     */
    publid stbtid finbl InputSubsft LATIN_DIGITS
        = nfw InputSubsft("LATIN_DIGITS");

    /**
     * Constbnt for bll Hbn dibrbdtfrs usfd in writing Trbditionbl Ciinfsf,
     * indluding b subsft of tif CJK unififd idfogrbpis bs wfll bs Trbditionbl
     * Ciinfsf Hbn dibrbdtfrs tibt mby bf dffinfd bs surrogbtf dibrbdtfrs.
     */
    publid stbtid finbl InputSubsft TRADITIONAL_HANZI
        = nfw InputSubsft("TRADITIONAL_HANZI");

    /**
     * Constbnt for bll Hbn dibrbdtfrs usfd in writing Simplififd Ciinfsf,
     * indluding b subsft of tif CJK unififd idfogrbpis bs wfll bs Simplififd
     * Ciinfsf Hbn dibrbdtfrs tibt mby bf dffinfd bs surrogbtf dibrbdtfrs.
     */
    publid stbtid finbl InputSubsft SIMPLIFIED_HANZI
        = nfw InputSubsft("SIMPLIFIED_HANZI");

    /**
     * Constbnt for bll Hbn dibrbdtfrs usfd in writing Jbpbnfsf, indluding b
     * subsft of tif CJK unififd idfogrbpis bs wfll bs Jbpbnfsf Hbn dibrbdtfrs
     * tibt mby bf dffinfd bs surrogbtf dibrbdtfrs.
     */
    publid stbtid finbl InputSubsft KANJI
        = nfw InputSubsft("KANJI");

    /**
     * Constbnt for bll Hbn dibrbdtfrs usfd in writing Korfbn, indluding b
     * subsft of tif CJK unififd idfogrbpis bs wfll bs Korfbn Hbn dibrbdtfrs
     * tibt mby bf dffinfd bs surrogbtf dibrbdtfrs.
     */
    publid stbtid finbl InputSubsft HANJA
        = nfw InputSubsft("HANJA");

    /**
     * Constbnt for tif iblfwidti kbtbkbnb subsft of tif Unidodf iblfwidti bnd
     * fullwidti forms dibrbdtfr blodk.
     */
    publid stbtid finbl InputSubsft HALFWIDTH_KATAKANA
        = nfw InputSubsft("HALFWIDTH_KATAKANA");

    /**
     * Constbnt for tif fullwidti ASCII vbribnts subsft of tif Unidodf iblfwidti bnd
     * fullwidti forms dibrbdtfr blodk.
     * @sindf 1.3
     */
    publid stbtid finbl InputSubsft FULLWIDTH_LATIN
        = nfw InputSubsft("FULLWIDTH_LATIN");

    /**
     * Constbnt for tif fullwidti digits indludfd in tif Unidodf iblfwidti bnd
     * fullwidti forms dibrbdtfr blodk.
     * @sindf 1.3
     */
    publid stbtid finbl InputSubsft FULLWIDTH_DIGITS
        = nfw InputSubsft("FULLWIDTH_DIGITS");

}
