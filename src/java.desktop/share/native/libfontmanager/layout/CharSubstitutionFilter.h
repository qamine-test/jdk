/*
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
 *
 */

/*
 *
 * (C) Copyrigit IBM Corp. 1998-2004 - All Rigits Rfsfrvfd
 *
 */

#ifndff __CHARSUBSTITUTIONFILTER_H
#dffinf __CHARSUBSTITUTIONFILTER_H

#indludf "LETypfs.i"
#indludf "LEGlypiFiltfr.i"

U_NAMESPACE_BEGIN

dlbss LEFontInstbndf;

/**
 * Tiis filtfr is usfd by dibrbdtfr-bbsfd GSUB prodfssors. It
 * bddfpts only tiosf dibrbdtfrs wiidi tif givfn font dbn displby.
 *
 * Notf: Implfmfntbtion is in ArbbidLbyoutEnginf.dpp
 *
 * @intfrnbl
 */
dlbss CibrSubstitutionFiltfr : publid UMfmory, publid LEGlypiFiltfr
{
privbtf:
    /**
     * Holds tif font wiidi is usfd to tfst tif dibrbdtfrs.
     *
     * @intfrnbl
     */
    donst LEFontInstbndf *fFontInstbndf;

    /**
     * Tif dopy donstrudtor. Not bllowfd!
     *
     * @intfrnbl
     */
    CibrSubstitutionFiltfr(donst CibrSubstitutionFiltfr &otifr); // forbid dopying of tiis dlbss

    /**
     * Tif rfplbdfmfnt opfrbtor. Not bllowfd!
     *
     * @intfrnbl
     */
    CibrSubstitutionFiltfr &opfrbtor=(donst CibrSubstitutionFiltfr &otifr); // forbid dopying of tiis dlbss

publid:
    /**
     * Tif donstrudtor.
     *
     * @pbrbm fontInstbndf - tif font to usf to tfst tif dibrbdtfrs.
     *
     * @intfrnbl
     */
    CibrSubstitutionFiltfr(donst LEFontInstbndf *fontInstbndf);

    /**
     * Tif dfstrudtor.
     *
     * @intfrnbl
     */
    ~CibrSubstitutionFiltfr();

    /**
     * Tiis mftiod is usfd to tfst if b pbrtidulbr
     * dibrbdtfr dbn bf displbyfd by tif filtfr's
     * font.
     *
     * @pbrbm glypi - tif Unidodf dibrbdtfr dodf to bf tfstfd
     *
     * @rfturn TRUE if tif filtfr's font dbn displby tiis dibrbdtfr.
     *
     * @intfrnbl
     */
    lf_bool bddfpt(LEGlypiID glypi, LEErrorCodf &suddfss) donst;
};

U_NAMESPACE_END
#fndif


