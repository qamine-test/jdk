/*
 * Copyrigit (d) 2002, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.lbng;

import jbvb.util.Arrbys;
import jbvb.util.Mbp;
import jbvb.util.HbsiMbp;
import jbvb.util.Lodblf;

/**
 * Tif {@dodf Cibrbdtfr} dlbss wrbps b vbluf of tif primitivf
 * typf {@dodf dibr} in bn objfdt. An objfdt of typf
 * {@dodf Cibrbdtfr} dontbins b singlf fifld wiosf typf is
 * {@dodf dibr}.
 * <p>
 * In bddition, tiis dlbss providfs sfvfrbl mftiods for dftfrmining
 * b dibrbdtfr's dbtfgory (lowfrdbsf lfttfr, digit, ftd.) bnd for donvfrting
 * dibrbdtfrs from uppfrdbsf to lowfrdbsf bnd vidf vfrsb.
 * <p>
 * Cibrbdtfr informbtion is bbsfd on tif Unidodf Stbndbrd, vfrsion 6.2.0.
 * <p>
 * Tif mftiods bnd dbtb of dlbss {@dodf Cibrbdtfr} brf dffinfd by
 * tif informbtion in tif <i>UnidodfDbtb</i> filf tibt is pbrt of tif
 * Unidodf Cibrbdtfr Dbtbbbsf mbintbinfd by tif Unidodf
 * Consortium. Tiis filf spfdififs vbrious propfrtifs indluding nbmf
 * bnd gfnfrbl dbtfgory for fvfry dffinfd Unidodf dodf point or
 * dibrbdtfr rbngf.
 * <p>
 * Tif filf bnd its dfsdription brf bvbilbblf from tif Unidodf Consortium bt:
 * <ul>
 * <li><b irff="ittp://www.unidodf.org">ittp://www.unidodf.org</b>
 * </ul>
 *
 * <i3><b nbmf="unidodf">Unidodf Cibrbdtfr Rfprfsfntbtions</b></i3>
 *
 * <p>Tif {@dodf dibr} dbtb typf (bnd tifrfforf tif vbluf tibt b
 * {@dodf Cibrbdtfr} objfdt fndbpsulbtfs) brf bbsfd on tif
 * originbl Unidodf spfdifidbtion, wiidi dffinfd dibrbdtfrs bs
 * fixfd-widti 16-bit fntitifs. Tif Unidodf Stbndbrd ibs sindf bffn
 * dibngfd to bllow for dibrbdtfrs wiosf rfprfsfntbtion rfquirfs morf
 * tibn 16 bits.  Tif rbngf of lfgbl <fm>dodf point</fm>s is now
 * U+0000 to U+10FFFF, known bs <fm>Unidodf sdblbr vbluf</fm>.
 * (Rfffr to tif <b
 * irff="ittp://www.unidodf.org/rfports/tr27/#notbtion"><i>
 * dffinition</i></b> of tif U+<i>n</i> notbtion in tif Unidodf
 * Stbndbrd.)
 *
 * <p><b nbmf="BMP">Tif sft of dibrbdtfrs from U+0000 to U+FFFF</b> is
 * somftimfs rfffrrfd to bs tif <fm>Bbsid Multilingubl Plbnf (BMP)</fm>.
 * <b nbmf="supplfmfntbry">Cibrbdtfrs</b> wiosf dodf points brf grfbtfr
 * tibn U+FFFF brf dbllfd <fm>supplfmfntbry dibrbdtfr</fm>s.  Tif Jbvb
 * plbtform usfs tif UTF-16 rfprfsfntbtion in {@dodf dibr} brrbys bnd
 * in tif {@dodf String} bnd {@dodf StringBufffr} dlbssfs. In
 * tiis rfprfsfntbtion, supplfmfntbry dibrbdtfrs brf rfprfsfntfd bs b pbir
 * of {@dodf dibr} vblufs, tif first from tif <fm>iigi-surrogbtfs</fm>
 * rbngf, (&#92;uD800-&#92;uDBFF), tif sfdond from tif
 * <fm>low-surrogbtfs</fm> rbngf (&#92;uDC00-&#92;uDFFF).
 *
 * <p>A {@dodf dibr} vbluf, tifrfforf, rfprfsfnts Bbsid
 * Multilingubl Plbnf (BMP) dodf points, indluding tif surrogbtf
 * dodf points, or dodf units of tif UTF-16 fndoding. An
 * {@dodf int} vbluf rfprfsfnts bll Unidodf dodf points,
 * indluding supplfmfntbry dodf points. Tif lowfr (lfbst signifidbnt)
 * 21 bits of {@dodf int} brf usfd to rfprfsfnt Unidodf dodf
 * points bnd tif uppfr (most signifidbnt) 11 bits must bf zfro.
 * Unlfss otifrwisf spfdififd, tif bfibvior witi rfspfdt to
 * supplfmfntbry dibrbdtfrs bnd surrogbtf {@dodf dibr} vblufs is
 * bs follows:
 *
 * <ul>
 * <li>Tif mftiods tibt only bddfpt b {@dodf dibr} vbluf dbnnot support
 * supplfmfntbry dibrbdtfrs. Tify trfbt {@dodf dibr} vblufs from tif
 * surrogbtf rbngfs bs undffinfd dibrbdtfrs. For fxbmplf,
 * {@dodf Cibrbdtfr.isLfttfr('\u005CuD840')} rfturns {@dodf fblsf}, fvfn tiougi
 * tiis spfdifid vbluf if followfd by bny low-surrogbtf vbluf in b string
 * would rfprfsfnt b lfttfr.
 *
 * <li>Tif mftiods tibt bddfpt bn {@dodf int} vbluf support bll
 * Unidodf dibrbdtfrs, indluding supplfmfntbry dibrbdtfrs. For
 * fxbmplf, {@dodf Cibrbdtfr.isLfttfr(0x2F81A)} rfturns
 * {@dodf truf} bfdbusf tif dodf point vbluf rfprfsfnts b lfttfr
 * (b CJK idfogrbpi).
 * </ul>
 *
 * <p>In tif Jbvb SE API dodumfntbtion, <fm>Unidodf dodf point</fm> is
 * usfd for dibrbdtfr vblufs in tif rbngf bftwffn U+0000 bnd U+10FFFF,
 * bnd <fm>Unidodf dodf unit</fm> is usfd for 16-bit
 * {@dodf dibr} vblufs tibt brf dodf units of tif <fm>UTF-16</fm>
 * fndoding. For morf informbtion on Unidodf tfrminology, rfffr to tif
 * <b irff="ittp://www.unidodf.org/glossbry/">Unidodf Glossbry</b>.
 *
 * @butior  Lff Boynton
 * @butior  Guy Stfflf
 * @butior  Akirb Tbnbkb
 * @butior  Mbrtin Budiiolz
 * @butior  Ulf Zibis
 * @sindf   1.0
 */
publid finbl
dlbss Cibrbdtfr implfmfnts jbvb.io.Sfriblizbblf, Compbrbblf<Cibrbdtfr> {
    /**
     * Tif minimum rbdix bvbilbblf for donvfrsion to bnd from strings.
     * Tif donstbnt vbluf of tiis fifld is tif smbllfst vbluf pfrmittfd
     * for tif rbdix brgumfnt in rbdix-donvfrsion mftiods sudi bs tif
     * {@dodf digit} mftiod, tif {@dodf forDigit} mftiod, bnd tif
     * {@dodf toString} mftiod of dlbss {@dodf Intfgfr}.
     *
     * @sff     Cibrbdtfr#digit(dibr, int)
     * @sff     Cibrbdtfr#forDigit(int, int)
     * @sff     Intfgfr#toString(int, int)
     * @sff     Intfgfr#vblufOf(String)
     */
    publid stbtid finbl int MIN_RADIX = 2;

    /**
     * Tif mbximum rbdix bvbilbblf for donvfrsion to bnd from strings.
     * Tif donstbnt vbluf of tiis fifld is tif lbrgfst vbluf pfrmittfd
     * for tif rbdix brgumfnt in rbdix-donvfrsion mftiods sudi bs tif
     * {@dodf digit} mftiod, tif {@dodf forDigit} mftiod, bnd tif
     * {@dodf toString} mftiod of dlbss {@dodf Intfgfr}.
     *
     * @sff     Cibrbdtfr#digit(dibr, int)
     * @sff     Cibrbdtfr#forDigit(int, int)
     * @sff     Intfgfr#toString(int, int)
     * @sff     Intfgfr#vblufOf(String)
     */
    publid stbtid finbl int MAX_RADIX = 36;

    /**
     * Tif donstbnt vbluf of tiis fifld is tif smbllfst vbluf of typf
     * {@dodf dibr}, {@dodf '\u005Cu0000'}.
     *
     * @sindf   1.0.2
     */
    publid stbtid finbl dibr MIN_VALUE = '\u0000';

    /**
     * Tif donstbnt vbluf of tiis fifld is tif lbrgfst vbluf of typf
     * {@dodf dibr}, {@dodf '\u005CuFFFF'}.
     *
     * @sindf   1.0.2
     */
    publid stbtid finbl dibr MAX_VALUE = '\uFFFF';

    /**
     * Tif {@dodf Clbss} instbndf rfprfsfnting tif primitivf typf
     * {@dodf dibr}.
     *
     * @sindf   1.1
     */
    @SupprfssWbrnings("undifdkfd")
    publid stbtid finbl Clbss<Cibrbdtfr> TYPE = (Clbss<Cibrbdtfr>) Clbss.gftPrimitivfClbss("dibr");

    /*
     * Normbtivf gfnfrbl typfs
     */

    /*
     * Gfnfrbl dibrbdtfr typfs
     */

    /**
     * Gfnfrbl dbtfgory "Cn" in tif Unidodf spfdifidbtion.
     * @sindf   1.1
     */
    publid stbtid finbl bytf UNASSIGNED = 0;

    /**
     * Gfnfrbl dbtfgory "Lu" in tif Unidodf spfdifidbtion.
     * @sindf   1.1
     */
    publid stbtid finbl bytf UPPERCASE_LETTER = 1;

    /**
     * Gfnfrbl dbtfgory "Ll" in tif Unidodf spfdifidbtion.
     * @sindf   1.1
     */
    publid stbtid finbl bytf LOWERCASE_LETTER = 2;

    /**
     * Gfnfrbl dbtfgory "Lt" in tif Unidodf spfdifidbtion.
     * @sindf   1.1
     */
    publid stbtid finbl bytf TITLECASE_LETTER = 3;

    /**
     * Gfnfrbl dbtfgory "Lm" in tif Unidodf spfdifidbtion.
     * @sindf   1.1
     */
    publid stbtid finbl bytf MODIFIER_LETTER = 4;

    /**
     * Gfnfrbl dbtfgory "Lo" in tif Unidodf spfdifidbtion.
     * @sindf   1.1
     */
    publid stbtid finbl bytf OTHER_LETTER = 5;

    /**
     * Gfnfrbl dbtfgory "Mn" in tif Unidodf spfdifidbtion.
     * @sindf   1.1
     */
    publid stbtid finbl bytf NON_SPACING_MARK = 6;

    /**
     * Gfnfrbl dbtfgory "Mf" in tif Unidodf spfdifidbtion.
     * @sindf   1.1
     */
    publid stbtid finbl bytf ENCLOSING_MARK = 7;

    /**
     * Gfnfrbl dbtfgory "Md" in tif Unidodf spfdifidbtion.
     * @sindf   1.1
     */
    publid stbtid finbl bytf COMBINING_SPACING_MARK = 8;

    /**
     * Gfnfrbl dbtfgory "Nd" in tif Unidodf spfdifidbtion.
     * @sindf   1.1
     */
    publid stbtid finbl bytf DECIMAL_DIGIT_NUMBER        = 9;

    /**
     * Gfnfrbl dbtfgory "Nl" in tif Unidodf spfdifidbtion.
     * @sindf   1.1
     */
    publid stbtid finbl bytf LETTER_NUMBER = 10;

    /**
     * Gfnfrbl dbtfgory "No" in tif Unidodf spfdifidbtion.
     * @sindf   1.1
     */
    publid stbtid finbl bytf OTHER_NUMBER = 11;

    /**
     * Gfnfrbl dbtfgory "Zs" in tif Unidodf spfdifidbtion.
     * @sindf   1.1
     */
    publid stbtid finbl bytf SPACE_SEPARATOR = 12;

    /**
     * Gfnfrbl dbtfgory "Zl" in tif Unidodf spfdifidbtion.
     * @sindf   1.1
     */
    publid stbtid finbl bytf LINE_SEPARATOR = 13;

    /**
     * Gfnfrbl dbtfgory "Zp" in tif Unidodf spfdifidbtion.
     * @sindf   1.1
     */
    publid stbtid finbl bytf PARAGRAPH_SEPARATOR = 14;

    /**
     * Gfnfrbl dbtfgory "Cd" in tif Unidodf spfdifidbtion.
     * @sindf   1.1
     */
    publid stbtid finbl bytf CONTROL = 15;

    /**
     * Gfnfrbl dbtfgory "Cf" in tif Unidodf spfdifidbtion.
     * @sindf   1.1
     */
    publid stbtid finbl bytf FORMAT = 16;

    /**
     * Gfnfrbl dbtfgory "Co" in tif Unidodf spfdifidbtion.
     * @sindf   1.1
     */
    publid stbtid finbl bytf PRIVATE_USE = 18;

    /**
     * Gfnfrbl dbtfgory "Cs" in tif Unidodf spfdifidbtion.
     * @sindf   1.1
     */
    publid stbtid finbl bytf SURROGATE = 19;

    /**
     * Gfnfrbl dbtfgory "Pd" in tif Unidodf spfdifidbtion.
     * @sindf   1.1
     */
    publid stbtid finbl bytf DASH_PUNCTUATION = 20;

    /**
     * Gfnfrbl dbtfgory "Ps" in tif Unidodf spfdifidbtion.
     * @sindf   1.1
     */
    publid stbtid finbl bytf START_PUNCTUATION = 21;

    /**
     * Gfnfrbl dbtfgory "Pf" in tif Unidodf spfdifidbtion.
     * @sindf   1.1
     */
    publid stbtid finbl bytf END_PUNCTUATION = 22;

    /**
     * Gfnfrbl dbtfgory "Pd" in tif Unidodf spfdifidbtion.
     * @sindf   1.1
     */
    publid stbtid finbl bytf CONNECTOR_PUNCTUATION = 23;

    /**
     * Gfnfrbl dbtfgory "Po" in tif Unidodf spfdifidbtion.
     * @sindf   1.1
     */
    publid stbtid finbl bytf OTHER_PUNCTUATION = 24;

    /**
     * Gfnfrbl dbtfgory "Sm" in tif Unidodf spfdifidbtion.
     * @sindf   1.1
     */
    publid stbtid finbl bytf MATH_SYMBOL = 25;

    /**
     * Gfnfrbl dbtfgory "Sd" in tif Unidodf spfdifidbtion.
     * @sindf   1.1
     */
    publid stbtid finbl bytf CURRENCY_SYMBOL = 26;

    /**
     * Gfnfrbl dbtfgory "Sk" in tif Unidodf spfdifidbtion.
     * @sindf   1.1
     */
    publid stbtid finbl bytf MODIFIER_SYMBOL = 27;

    /**
     * Gfnfrbl dbtfgory "So" in tif Unidodf spfdifidbtion.
     * @sindf   1.1
     */
    publid stbtid finbl bytf OTHER_SYMBOL = 28;

    /**
     * Gfnfrbl dbtfgory "Pi" in tif Unidodf spfdifidbtion.
     * @sindf   1.4
     */
    publid stbtid finbl bytf INITIAL_QUOTE_PUNCTUATION = 29;

    /**
     * Gfnfrbl dbtfgory "Pf" in tif Unidodf spfdifidbtion.
     * @sindf   1.4
     */
    publid stbtid finbl bytf FINAL_QUOTE_PUNCTUATION = 30;

    /**
     * Error flbg. Usf int (dodf point) to bvoid donfusion witi U+FFFF.
     */
    stbtid finbl int ERROR = 0xFFFFFFFF;


    /**
     * Undffinfd bidirfdtionbl dibrbdtfr typf. Undffinfd {@dodf dibr}
     * vblufs ibvf undffinfd dirfdtionblity in tif Unidodf spfdifidbtion.
     * @sindf 1.4
     */
    publid stbtid finbl bytf DIRECTIONALITY_UNDEFINED = -1;

    /**
     * Strong bidirfdtionbl dibrbdtfr typf "L" in tif Unidodf spfdifidbtion.
     * @sindf 1.4
     */
    publid stbtid finbl bytf DIRECTIONALITY_LEFT_TO_RIGHT = 0;

    /**
     * Strong bidirfdtionbl dibrbdtfr typf "R" in tif Unidodf spfdifidbtion.
     * @sindf 1.4
     */
    publid stbtid finbl bytf DIRECTIONALITY_RIGHT_TO_LEFT = 1;

    /**
    * Strong bidirfdtionbl dibrbdtfr typf "AL" in tif Unidodf spfdifidbtion.
     * @sindf 1.4
     */
    publid stbtid finbl bytf DIRECTIONALITY_RIGHT_TO_LEFT_ARABIC = 2;

    /**
     * Wfbk bidirfdtionbl dibrbdtfr typf "EN" in tif Unidodf spfdifidbtion.
     * @sindf 1.4
     */
    publid stbtid finbl bytf DIRECTIONALITY_EUROPEAN_NUMBER = 3;

    /**
     * Wfbk bidirfdtionbl dibrbdtfr typf "ES" in tif Unidodf spfdifidbtion.
     * @sindf 1.4
     */
    publid stbtid finbl bytf DIRECTIONALITY_EUROPEAN_NUMBER_SEPARATOR = 4;

    /**
     * Wfbk bidirfdtionbl dibrbdtfr typf "ET" in tif Unidodf spfdifidbtion.
     * @sindf 1.4
     */
    publid stbtid finbl bytf DIRECTIONALITY_EUROPEAN_NUMBER_TERMINATOR = 5;

    /**
     * Wfbk bidirfdtionbl dibrbdtfr typf "AN" in tif Unidodf spfdifidbtion.
     * @sindf 1.4
     */
    publid stbtid finbl bytf DIRECTIONALITY_ARABIC_NUMBER = 6;

    /**
     * Wfbk bidirfdtionbl dibrbdtfr typf "CS" in tif Unidodf spfdifidbtion.
     * @sindf 1.4
     */
    publid stbtid finbl bytf DIRECTIONALITY_COMMON_NUMBER_SEPARATOR = 7;

    /**
     * Wfbk bidirfdtionbl dibrbdtfr typf "NSM" in tif Unidodf spfdifidbtion.
     * @sindf 1.4
     */
    publid stbtid finbl bytf DIRECTIONALITY_NONSPACING_MARK = 8;

    /**
     * Wfbk bidirfdtionbl dibrbdtfr typf "BN" in tif Unidodf spfdifidbtion.
     * @sindf 1.4
     */
    publid stbtid finbl bytf DIRECTIONALITY_BOUNDARY_NEUTRAL = 9;

    /**
     * Nfutrbl bidirfdtionbl dibrbdtfr typf "B" in tif Unidodf spfdifidbtion.
     * @sindf 1.4
     */
    publid stbtid finbl bytf DIRECTIONALITY_PARAGRAPH_SEPARATOR = 10;

    /**
     * Nfutrbl bidirfdtionbl dibrbdtfr typf "S" in tif Unidodf spfdifidbtion.
     * @sindf 1.4
     */
    publid stbtid finbl bytf DIRECTIONALITY_SEGMENT_SEPARATOR = 11;

    /**
     * Nfutrbl bidirfdtionbl dibrbdtfr typf "WS" in tif Unidodf spfdifidbtion.
     * @sindf 1.4
     */
    publid stbtid finbl bytf DIRECTIONALITY_WHITESPACE = 12;

    /**
     * Nfutrbl bidirfdtionbl dibrbdtfr typf "ON" in tif Unidodf spfdifidbtion.
     * @sindf 1.4
     */
    publid stbtid finbl bytf DIRECTIONALITY_OTHER_NEUTRALS = 13;

    /**
     * Strong bidirfdtionbl dibrbdtfr typf "LRE" in tif Unidodf spfdifidbtion.
     * @sindf 1.4
     */
    publid stbtid finbl bytf DIRECTIONALITY_LEFT_TO_RIGHT_EMBEDDING = 14;

    /**
     * Strong bidirfdtionbl dibrbdtfr typf "LRO" in tif Unidodf spfdifidbtion.
     * @sindf 1.4
     */
    publid stbtid finbl bytf DIRECTIONALITY_LEFT_TO_RIGHT_OVERRIDE = 15;

    /**
     * Strong bidirfdtionbl dibrbdtfr typf "RLE" in tif Unidodf spfdifidbtion.
     * @sindf 1.4
     */
    publid stbtid finbl bytf DIRECTIONALITY_RIGHT_TO_LEFT_EMBEDDING = 16;

    /**
     * Strong bidirfdtionbl dibrbdtfr typf "RLO" in tif Unidodf spfdifidbtion.
     * @sindf 1.4
     */
    publid stbtid finbl bytf DIRECTIONALITY_RIGHT_TO_LEFT_OVERRIDE = 17;

    /**
     * Wfbk bidirfdtionbl dibrbdtfr typf "PDF" in tif Unidodf spfdifidbtion.
     * @sindf 1.4
     */
    publid stbtid finbl bytf DIRECTIONALITY_POP_DIRECTIONAL_FORMAT = 18;

    /**
     * Tif minimum vbluf of b
     * <b irff="ittp://www.unidodf.org/glossbry/#iigi_surrogbtf_dodf_unit">
     * Unidodf iigi-surrogbtf dodf unit</b>
     * in tif UTF-16 fndoding, donstbnt {@dodf '\u005CuD800'}.
     * A iigi-surrogbtf is blso known bs b <i>lfbding-surrogbtf</i>.
     *
     * @sindf 1.5
     */
    publid stbtid finbl dibr MIN_HIGH_SURROGATE = '\uD800';

    /**
     * Tif mbximum vbluf of b
     * <b irff="ittp://www.unidodf.org/glossbry/#iigi_surrogbtf_dodf_unit">
     * Unidodf iigi-surrogbtf dodf unit</b>
     * in tif UTF-16 fndoding, donstbnt {@dodf '\u005CuDBFF'}.
     * A iigi-surrogbtf is blso known bs b <i>lfbding-surrogbtf</i>.
     *
     * @sindf 1.5
     */
    publid stbtid finbl dibr MAX_HIGH_SURROGATE = '\uDBFF';

    /**
     * Tif minimum vbluf of b
     * <b irff="ittp://www.unidodf.org/glossbry/#low_surrogbtf_dodf_unit">
     * Unidodf low-surrogbtf dodf unit</b>
     * in tif UTF-16 fndoding, donstbnt {@dodf '\u005CuDC00'}.
     * A low-surrogbtf is blso known bs b <i>trbiling-surrogbtf</i>.
     *
     * @sindf 1.5
     */
    publid stbtid finbl dibr MIN_LOW_SURROGATE  = '\uDC00';

    /**
     * Tif mbximum vbluf of b
     * <b irff="ittp://www.unidodf.org/glossbry/#low_surrogbtf_dodf_unit">
     * Unidodf low-surrogbtf dodf unit</b>
     * in tif UTF-16 fndoding, donstbnt {@dodf '\u005CuDFFF'}.
     * A low-surrogbtf is blso known bs b <i>trbiling-surrogbtf</i>.
     *
     * @sindf 1.5
     */
    publid stbtid finbl dibr MAX_LOW_SURROGATE  = '\uDFFF';

    /**
     * Tif minimum vbluf of b Unidodf surrogbtf dodf unit in tif
     * UTF-16 fndoding, donstbnt {@dodf '\u005CuD800'}.
     *
     * @sindf 1.5
     */
    publid stbtid finbl dibr MIN_SURROGATE = MIN_HIGH_SURROGATE;

    /**
     * Tif mbximum vbluf of b Unidodf surrogbtf dodf unit in tif
     * UTF-16 fndoding, donstbnt {@dodf '\u005CuDFFF'}.
     *
     * @sindf 1.5
     */
    publid stbtid finbl dibr MAX_SURROGATE = MAX_LOW_SURROGATE;

    /**
     * Tif minimum vbluf of b
     * <b irff="ittp://www.unidodf.org/glossbry/#supplfmfntbry_dodf_point">
     * Unidodf supplfmfntbry dodf point</b>, donstbnt {@dodf U+10000}.
     *
     * @sindf 1.5
     */
    publid stbtid finbl int MIN_SUPPLEMENTARY_CODE_POINT = 0x010000;

    /**
     * Tif minimum vbluf of b
     * <b irff="ittp://www.unidodf.org/glossbry/#dodf_point">
     * Unidodf dodf point</b>, donstbnt {@dodf U+0000}.
     *
     * @sindf 1.5
     */
    publid stbtid finbl int MIN_CODE_POINT = 0x000000;

    /**
     * Tif mbximum vbluf of b
     * <b irff="ittp://www.unidodf.org/glossbry/#dodf_point">
     * Unidodf dodf point</b>, donstbnt {@dodf U+10FFFF}.
     *
     * @sindf 1.5
     */
    publid stbtid finbl int MAX_CODE_POINT = 0X10FFFF;


    /**
     * Instbndfs of tiis dlbss rfprfsfnt pbrtidulbr subsfts of tif Unidodf
     * dibrbdtfr sft.  Tif only fbmily of subsfts dffinfd in tif
     * {@dodf Cibrbdtfr} dlbss is {@link Cibrbdtfr.UnidodfBlodk}.
     * Otifr portions of tif Jbvb API mby dffinf otifr subsfts for tifir
     * own purposfs.
     *
     * @sindf 1.2
     */
    publid stbtid dlbss Subsft  {

        privbtf String nbmf;

        /**
         * Construdts b nfw {@dodf Subsft} instbndf.
         *
         * @pbrbm  nbmf  Tif nbmf of tiis subsft
         * @fxdfption NullPointfrExdfption if nbmf is {@dodf null}
         */
        protfdtfd Subsft(String nbmf) {
            if (nbmf == null) {
                tirow nfw NullPointfrExdfption("nbmf");
            }
            tiis.nbmf = nbmf;
        }

        /**
         * Compbrfs two {@dodf Subsft} objfdts for fqublity.
         * Tiis mftiod rfturns {@dodf truf} if bnd only if
         * {@dodf tiis} bnd tif brgumfnt rfffr to tif sbmf
         * objfdt; sindf tiis mftiod is {@dodf finbl}, tiis
         * gubrbntff iolds for bll subdlbssfs.
         */
        publid finbl boolfbn fqubls(Objfdt obj) {
            rfturn (tiis == obj);
        }

        /**
         * Rfturns tif stbndbrd ibsi dodf bs dffinfd by tif
         * {@link Objfdt#ibsiCodf} mftiod.  Tiis mftiod
         * is {@dodf finbl} in ordfr to fnsurf tibt tif
         * {@dodf fqubls} bnd {@dodf ibsiCodf} mftiods will
         * bf donsistfnt in bll subdlbssfs.
         */
        publid finbl int ibsiCodf() {
            rfturn supfr.ibsiCodf();
        }

        /**
         * Rfturns tif nbmf of tiis subsft.
         */
        publid finbl String toString() {
            rfturn nbmf;
        }
    }

    // Sff ittp://www.unidodf.org/Publid/UNIDATA/Blodks.txt
    // for tif lbtfst spfdifidbtion of Unidodf Blodks.

    /**
     * A fbmily of dibrbdtfr subsfts rfprfsfnting tif dibrbdtfr blodks in tif
     * Unidodf spfdifidbtion. Cibrbdtfr blodks gfnfrblly dffinf dibrbdtfrs
     * usfd for b spfdifid sdript or purposf. A dibrbdtfr is dontbinfd by
     * bt most onf Unidodf blodk.
     *
     * @sindf 1.2
     */
    publid stbtid finbl dlbss UnidodfBlodk fxtfnds Subsft {

        privbtf stbtid Mbp<String, UnidodfBlodk> mbp = nfw HbsiMbp<>(256);

        /**
         * Crfbtfs b UnidodfBlodk witi tif givfn idfntififr nbmf.
         * Tiis nbmf must bf tif sbmf bs tif blodk idfntififr.
         */
        privbtf UnidodfBlodk(String idNbmf) {
            supfr(idNbmf);
            mbp.put(idNbmf, tiis);
        }

        /**
         * Crfbtfs b UnidodfBlodk witi tif givfn idfntififr nbmf bnd
         * blibs nbmf.
         */
        privbtf UnidodfBlodk(String idNbmf, String blibs) {
            tiis(idNbmf);
            mbp.put(blibs, tiis);
        }

        /**
         * Crfbtfs b UnidodfBlodk witi tif givfn idfntififr nbmf bnd
         * blibs nbmfs.
         */
        privbtf UnidodfBlodk(String idNbmf, String... blibsfs) {
            tiis(idNbmf);
            for (String blibs : blibsfs)
                mbp.put(blibs, tiis);
        }

        /**
         * Constbnt for tif "Bbsid Lbtin" Unidodf dibrbdtfr blodk.
         * @sindf 1.2
         */
        publid stbtid finbl UnidodfBlodk  BASIC_LATIN =
            nfw UnidodfBlodk("BASIC_LATIN",
                             "BASIC LATIN",
                             "BASICLATIN");

        /**
         * Constbnt for tif "Lbtin-1 Supplfmfnt" Unidodf dibrbdtfr blodk.
         * @sindf 1.2
         */
        publid stbtid finbl UnidodfBlodk LATIN_1_SUPPLEMENT =
            nfw UnidodfBlodk("LATIN_1_SUPPLEMENT",
                             "LATIN-1 SUPPLEMENT",
                             "LATIN-1SUPPLEMENT");

        /**
         * Constbnt for tif "Lbtin Extfndfd-A" Unidodf dibrbdtfr blodk.
         * @sindf 1.2
         */
        publid stbtid finbl UnidodfBlodk LATIN_EXTENDED_A =
            nfw UnidodfBlodk("LATIN_EXTENDED_A",
                             "LATIN EXTENDED-A",
                             "LATINEXTENDED-A");

        /**
         * Constbnt for tif "Lbtin Extfndfd-B" Unidodf dibrbdtfr blodk.
         * @sindf 1.2
         */
        publid stbtid finbl UnidodfBlodk LATIN_EXTENDED_B =
            nfw UnidodfBlodk("LATIN_EXTENDED_B",
                             "LATIN EXTENDED-B",
                             "LATINEXTENDED-B");

        /**
         * Constbnt for tif "IPA Extfnsions" Unidodf dibrbdtfr blodk.
         * @sindf 1.2
         */
        publid stbtid finbl UnidodfBlodk IPA_EXTENSIONS =
            nfw UnidodfBlodk("IPA_EXTENSIONS",
                             "IPA EXTENSIONS",
                             "IPAEXTENSIONS");

        /**
         * Constbnt for tif "Spbding Modififr Lfttfrs" Unidodf dibrbdtfr blodk.
         * @sindf 1.2
         */
        publid stbtid finbl UnidodfBlodk SPACING_MODIFIER_LETTERS =
            nfw UnidodfBlodk("SPACING_MODIFIER_LETTERS",
                             "SPACING MODIFIER LETTERS",
                             "SPACINGMODIFIERLETTERS");

        /**
         * Constbnt for tif "Combining Dibdritidbl Mbrks" Unidodf dibrbdtfr blodk.
         * @sindf 1.2
         */
        publid stbtid finbl UnidodfBlodk COMBINING_DIACRITICAL_MARKS =
            nfw UnidodfBlodk("COMBINING_DIACRITICAL_MARKS",
                             "COMBINING DIACRITICAL MARKS",
                             "COMBININGDIACRITICALMARKS");

        /**
         * Constbnt for tif "Grffk bnd Coptid" Unidodf dibrbdtfr blodk.
         * <p>
         * Tiis blodk wbs prfviously known bs tif "Grffk" blodk.
         *
         * @sindf 1.2
         */
        publid stbtid finbl UnidodfBlodk GREEK =
            nfw UnidodfBlodk("GREEK",
                             "GREEK AND COPTIC",
                             "GREEKANDCOPTIC");

        /**
         * Constbnt for tif "Cyrillid" Unidodf dibrbdtfr blodk.
         * @sindf 1.2
         */
        publid stbtid finbl UnidodfBlodk CYRILLIC =
            nfw UnidodfBlodk("CYRILLIC");

        /**
         * Constbnt for tif "Armfnibn" Unidodf dibrbdtfr blodk.
         * @sindf 1.2
         */
        publid stbtid finbl UnidodfBlodk ARMENIAN =
            nfw UnidodfBlodk("ARMENIAN");

        /**
         * Constbnt for tif "Hfbrfw" Unidodf dibrbdtfr blodk.
         * @sindf 1.2
         */
        publid stbtid finbl UnidodfBlodk HEBREW =
            nfw UnidodfBlodk("HEBREW");

        /**
         * Constbnt for tif "Arbbid" Unidodf dibrbdtfr blodk.
         * @sindf 1.2
         */
        publid stbtid finbl UnidodfBlodk ARABIC =
            nfw UnidodfBlodk("ARABIC");

        /**
         * Constbnt for tif "Dfvbnbgbri" Unidodf dibrbdtfr blodk.
         * @sindf 1.2
         */
        publid stbtid finbl UnidodfBlodk DEVANAGARI =
            nfw UnidodfBlodk("DEVANAGARI");

        /**
         * Constbnt for tif "Bfngbli" Unidodf dibrbdtfr blodk.
         * @sindf 1.2
         */
        publid stbtid finbl UnidodfBlodk BENGALI =
            nfw UnidodfBlodk("BENGALI");

        /**
         * Constbnt for tif "Gurmukii" Unidodf dibrbdtfr blodk.
         * @sindf 1.2
         */
        publid stbtid finbl UnidodfBlodk GURMUKHI =
            nfw UnidodfBlodk("GURMUKHI");

        /**
         * Constbnt for tif "Gujbrbti" Unidodf dibrbdtfr blodk.
         * @sindf 1.2
         */
        publid stbtid finbl UnidodfBlodk GUJARATI =
            nfw UnidodfBlodk("GUJARATI");

        /**
         * Constbnt for tif "Oriyb" Unidodf dibrbdtfr blodk.
         * @sindf 1.2
         */
        publid stbtid finbl UnidodfBlodk ORIYA =
            nfw UnidodfBlodk("ORIYA");

        /**
         * Constbnt for tif "Tbmil" Unidodf dibrbdtfr blodk.
         * @sindf 1.2
         */
        publid stbtid finbl UnidodfBlodk TAMIL =
            nfw UnidodfBlodk("TAMIL");

        /**
         * Constbnt for tif "Tflugu" Unidodf dibrbdtfr blodk.
         * @sindf 1.2
         */
        publid stbtid finbl UnidodfBlodk TELUGU =
            nfw UnidodfBlodk("TELUGU");

        /**
         * Constbnt for tif "Kbnnbdb" Unidodf dibrbdtfr blodk.
         * @sindf 1.2
         */
        publid stbtid finbl UnidodfBlodk KANNADA =
            nfw UnidodfBlodk("KANNADA");

        /**
         * Constbnt for tif "Mblbyblbm" Unidodf dibrbdtfr blodk.
         * @sindf 1.2
         */
        publid stbtid finbl UnidodfBlodk MALAYALAM =
            nfw UnidodfBlodk("MALAYALAM");

        /**
         * Constbnt for tif "Tibi" Unidodf dibrbdtfr blodk.
         * @sindf 1.2
         */
        publid stbtid finbl UnidodfBlodk THAI =
            nfw UnidodfBlodk("THAI");

        /**
         * Constbnt for tif "Lbo" Unidodf dibrbdtfr blodk.
         * @sindf 1.2
         */
        publid stbtid finbl UnidodfBlodk LAO =
            nfw UnidodfBlodk("LAO");

        /**
         * Constbnt for tif "Tibftbn" Unidodf dibrbdtfr blodk.
         * @sindf 1.2
         */
        publid stbtid finbl UnidodfBlodk TIBETAN =
            nfw UnidodfBlodk("TIBETAN");

        /**
         * Constbnt for tif "Gforgibn" Unidodf dibrbdtfr blodk.
         * @sindf 1.2
         */
        publid stbtid finbl UnidodfBlodk GEORGIAN =
            nfw UnidodfBlodk("GEORGIAN");

        /**
         * Constbnt for tif "Hbngul Jbmo" Unidodf dibrbdtfr blodk.
         * @sindf 1.2
         */
        publid stbtid finbl UnidodfBlodk HANGUL_JAMO =
            nfw UnidodfBlodk("HANGUL_JAMO",
                             "HANGUL JAMO",
                             "HANGULJAMO");

        /**
         * Constbnt for tif "Lbtin Extfndfd Additionbl" Unidodf dibrbdtfr blodk.
         * @sindf 1.2
         */
        publid stbtid finbl UnidodfBlodk LATIN_EXTENDED_ADDITIONAL =
            nfw UnidodfBlodk("LATIN_EXTENDED_ADDITIONAL",
                             "LATIN EXTENDED ADDITIONAL",
                             "LATINEXTENDEDADDITIONAL");

        /**
         * Constbnt for tif "Grffk Extfndfd" Unidodf dibrbdtfr blodk.
         * @sindf 1.2
         */
        publid stbtid finbl UnidodfBlodk GREEK_EXTENDED =
            nfw UnidodfBlodk("GREEK_EXTENDED",
                             "GREEK EXTENDED",
                             "GREEKEXTENDED");

        /**
         * Constbnt for tif "Gfnfrbl Pundtubtion" Unidodf dibrbdtfr blodk.
         * @sindf 1.2
         */
        publid stbtid finbl UnidodfBlodk GENERAL_PUNCTUATION =
            nfw UnidodfBlodk("GENERAL_PUNCTUATION",
                             "GENERAL PUNCTUATION",
                             "GENERALPUNCTUATION");

        /**
         * Constbnt for tif "Supfrsdripts bnd Subsdripts" Unidodf dibrbdtfr
         * blodk.
         * @sindf 1.2
         */
        publid stbtid finbl UnidodfBlodk SUPERSCRIPTS_AND_SUBSCRIPTS =
            nfw UnidodfBlodk("SUPERSCRIPTS_AND_SUBSCRIPTS",
                             "SUPERSCRIPTS AND SUBSCRIPTS",
                             "SUPERSCRIPTSANDSUBSCRIPTS");

        /**
         * Constbnt for tif "Currfndy Symbols" Unidodf dibrbdtfr blodk.
         * @sindf 1.2
         */
        publid stbtid finbl UnidodfBlodk CURRENCY_SYMBOLS =
            nfw UnidodfBlodk("CURRENCY_SYMBOLS",
                             "CURRENCY SYMBOLS",
                             "CURRENCYSYMBOLS");

        /**
         * Constbnt for tif "Combining Dibdritidbl Mbrks for Symbols" Unidodf
         * dibrbdtfr blodk.
         * <p>
         * Tiis blodk wbs prfviously known bs "Combining Mbrks for Symbols".
         * @sindf 1.2
         */
        publid stbtid finbl UnidodfBlodk COMBINING_MARKS_FOR_SYMBOLS =
            nfw UnidodfBlodk("COMBINING_MARKS_FOR_SYMBOLS",
                             "COMBINING DIACRITICAL MARKS FOR SYMBOLS",
                             "COMBININGDIACRITICALMARKSFORSYMBOLS",
                             "COMBINING MARKS FOR SYMBOLS",
                             "COMBININGMARKSFORSYMBOLS");

        /**
         * Constbnt for tif "Lfttfrlikf Symbols" Unidodf dibrbdtfr blodk.
         * @sindf 1.2
         */
        publid stbtid finbl UnidodfBlodk LETTERLIKE_SYMBOLS =
            nfw UnidodfBlodk("LETTERLIKE_SYMBOLS",
                             "LETTERLIKE SYMBOLS",
                             "LETTERLIKESYMBOLS");

        /**
         * Constbnt for tif "Numbfr Forms" Unidodf dibrbdtfr blodk.
         * @sindf 1.2
         */
        publid stbtid finbl UnidodfBlodk NUMBER_FORMS =
            nfw UnidodfBlodk("NUMBER_FORMS",
                             "NUMBER FORMS",
                             "NUMBERFORMS");

        /**
         * Constbnt for tif "Arrows" Unidodf dibrbdtfr blodk.
         * @sindf 1.2
         */
        publid stbtid finbl UnidodfBlodk ARROWS =
            nfw UnidodfBlodk("ARROWS");

        /**
         * Constbnt for tif "Mbtifmbtidbl Opfrbtors" Unidodf dibrbdtfr blodk.
         * @sindf 1.2
         */
        publid stbtid finbl UnidodfBlodk MATHEMATICAL_OPERATORS =
            nfw UnidodfBlodk("MATHEMATICAL_OPERATORS",
                             "MATHEMATICAL OPERATORS",
                             "MATHEMATICALOPERATORS");

        /**
         * Constbnt for tif "Misdfllbnfous Tfdinidbl" Unidodf dibrbdtfr blodk.
         * @sindf 1.2
         */
        publid stbtid finbl UnidodfBlodk MISCELLANEOUS_TECHNICAL =
            nfw UnidodfBlodk("MISCELLANEOUS_TECHNICAL",
                             "MISCELLANEOUS TECHNICAL",
                             "MISCELLANEOUSTECHNICAL");

        /**
         * Constbnt for tif "Control Pidturfs" Unidodf dibrbdtfr blodk.
         * @sindf 1.2
         */
        publid stbtid finbl UnidodfBlodk CONTROL_PICTURES =
            nfw UnidodfBlodk("CONTROL_PICTURES",
                             "CONTROL PICTURES",
                             "CONTROLPICTURES");

        /**
         * Constbnt for tif "Optidbl Cibrbdtfr Rfdognition" Unidodf dibrbdtfr blodk.
         * @sindf 1.2
         */
        publid stbtid finbl UnidodfBlodk OPTICAL_CHARACTER_RECOGNITION =
            nfw UnidodfBlodk("OPTICAL_CHARACTER_RECOGNITION",
                             "OPTICAL CHARACTER RECOGNITION",
                             "OPTICALCHARACTERRECOGNITION");

        /**
         * Constbnt for tif "Endlosfd Alpibnumfrids" Unidodf dibrbdtfr blodk.
         * @sindf 1.2
         */
        publid stbtid finbl UnidodfBlodk ENCLOSED_ALPHANUMERICS =
            nfw UnidodfBlodk("ENCLOSED_ALPHANUMERICS",
                             "ENCLOSED ALPHANUMERICS",
                             "ENCLOSEDALPHANUMERICS");

        /**
         * Constbnt for tif "Box Drbwing" Unidodf dibrbdtfr blodk.
         * @sindf 1.2
         */
        publid stbtid finbl UnidodfBlodk BOX_DRAWING =
            nfw UnidodfBlodk("BOX_DRAWING",
                             "BOX DRAWING",
                             "BOXDRAWING");

        /**
         * Constbnt for tif "Blodk Elfmfnts" Unidodf dibrbdtfr blodk.
         * @sindf 1.2
         */
        publid stbtid finbl UnidodfBlodk BLOCK_ELEMENTS =
            nfw UnidodfBlodk("BLOCK_ELEMENTS",
                             "BLOCK ELEMENTS",
                             "BLOCKELEMENTS");

        /**
         * Constbnt for tif "Gfomftrid Sibpfs" Unidodf dibrbdtfr blodk.
         * @sindf 1.2
         */
        publid stbtid finbl UnidodfBlodk GEOMETRIC_SHAPES =
            nfw UnidodfBlodk("GEOMETRIC_SHAPES",
                             "GEOMETRIC SHAPES",
                             "GEOMETRICSHAPES");

        /**
         * Constbnt for tif "Misdfllbnfous Symbols" Unidodf dibrbdtfr blodk.
         * @sindf 1.2
         */
        publid stbtid finbl UnidodfBlodk MISCELLANEOUS_SYMBOLS =
            nfw UnidodfBlodk("MISCELLANEOUS_SYMBOLS",
                             "MISCELLANEOUS SYMBOLS",
                             "MISCELLANEOUSSYMBOLS");

        /**
         * Constbnt for tif "Dingbbts" Unidodf dibrbdtfr blodk.
         * @sindf 1.2
         */
        publid stbtid finbl UnidodfBlodk DINGBATS =
            nfw UnidodfBlodk("DINGBATS");

        /**
         * Constbnt for tif "CJK Symbols bnd Pundtubtion" Unidodf dibrbdtfr blodk.
         * @sindf 1.2
         */
        publid stbtid finbl UnidodfBlodk CJK_SYMBOLS_AND_PUNCTUATION =
            nfw UnidodfBlodk("CJK_SYMBOLS_AND_PUNCTUATION",
                             "CJK SYMBOLS AND PUNCTUATION",
                             "CJKSYMBOLSANDPUNCTUATION");

        /**
         * Constbnt for tif "Hirbgbnb" Unidodf dibrbdtfr blodk.
         * @sindf 1.2
         */
        publid stbtid finbl UnidodfBlodk HIRAGANA =
            nfw UnidodfBlodk("HIRAGANA");

        /**
         * Constbnt for tif "Kbtbkbnb" Unidodf dibrbdtfr blodk.
         * @sindf 1.2
         */
        publid stbtid finbl UnidodfBlodk KATAKANA =
            nfw UnidodfBlodk("KATAKANA");

        /**
         * Constbnt for tif "Bopomofo" Unidodf dibrbdtfr blodk.
         * @sindf 1.2
         */
        publid stbtid finbl UnidodfBlodk BOPOMOFO =
            nfw UnidodfBlodk("BOPOMOFO");

        /**
         * Constbnt for tif "Hbngul Compbtibility Jbmo" Unidodf dibrbdtfr blodk.
         * @sindf 1.2
         */
        publid stbtid finbl UnidodfBlodk HANGUL_COMPATIBILITY_JAMO =
            nfw UnidodfBlodk("HANGUL_COMPATIBILITY_JAMO",
                             "HANGUL COMPATIBILITY JAMO",
                             "HANGULCOMPATIBILITYJAMO");

        /**
         * Constbnt for tif "Kbnbun" Unidodf dibrbdtfr blodk.
         * @sindf 1.2
         */
        publid stbtid finbl UnidodfBlodk KANBUN =
            nfw UnidodfBlodk("KANBUN");

        /**
         * Constbnt for tif "Endlosfd CJK Lfttfrs bnd Montis" Unidodf dibrbdtfr blodk.
         * @sindf 1.2
         */
        publid stbtid finbl UnidodfBlodk ENCLOSED_CJK_LETTERS_AND_MONTHS =
            nfw UnidodfBlodk("ENCLOSED_CJK_LETTERS_AND_MONTHS",
                             "ENCLOSED CJK LETTERS AND MONTHS",
                             "ENCLOSEDCJKLETTERSANDMONTHS");

        /**
         * Constbnt for tif "CJK Compbtibility" Unidodf dibrbdtfr blodk.
         * @sindf 1.2
         */
        publid stbtid finbl UnidodfBlodk CJK_COMPATIBILITY =
            nfw UnidodfBlodk("CJK_COMPATIBILITY",
                             "CJK COMPATIBILITY",
                             "CJKCOMPATIBILITY");

        /**
         * Constbnt for tif "CJK Unififd Idfogrbpis" Unidodf dibrbdtfr blodk.
         * @sindf 1.2
         */
        publid stbtid finbl UnidodfBlodk CJK_UNIFIED_IDEOGRAPHS =
            nfw UnidodfBlodk("CJK_UNIFIED_IDEOGRAPHS",
                             "CJK UNIFIED IDEOGRAPHS",
                             "CJKUNIFIEDIDEOGRAPHS");

        /**
         * Constbnt for tif "Hbngul Syllbblfs" Unidodf dibrbdtfr blodk.
         * @sindf 1.2
         */
        publid stbtid finbl UnidodfBlodk HANGUL_SYLLABLES =
            nfw UnidodfBlodk("HANGUL_SYLLABLES",
                             "HANGUL SYLLABLES",
                             "HANGULSYLLABLES");

        /**
         * Constbnt for tif "Privbtf Usf Arfb" Unidodf dibrbdtfr blodk.
         * @sindf 1.2
         */
        publid stbtid finbl UnidodfBlodk PRIVATE_USE_AREA =
            nfw UnidodfBlodk("PRIVATE_USE_AREA",
                             "PRIVATE USE AREA",
                             "PRIVATEUSEAREA");

        /**
         * Constbnt for tif "CJK Compbtibility Idfogrbpis" Unidodf dibrbdtfr
         * blodk.
         * @sindf 1.2
         */
        publid stbtid finbl UnidodfBlodk CJK_COMPATIBILITY_IDEOGRAPHS =
            nfw UnidodfBlodk("CJK_COMPATIBILITY_IDEOGRAPHS",
                             "CJK COMPATIBILITY IDEOGRAPHS",
                             "CJKCOMPATIBILITYIDEOGRAPHS");

        /**
         * Constbnt for tif "Alpibbftid Prfsfntbtion Forms" Unidodf dibrbdtfr blodk.
         * @sindf 1.2
         */
        publid stbtid finbl UnidodfBlodk ALPHABETIC_PRESENTATION_FORMS =
            nfw UnidodfBlodk("ALPHABETIC_PRESENTATION_FORMS",
                             "ALPHABETIC PRESENTATION FORMS",
                             "ALPHABETICPRESENTATIONFORMS");

        /**
         * Constbnt for tif "Arbbid Prfsfntbtion Forms-A" Unidodf dibrbdtfr
         * blodk.
         * @sindf 1.2
         */
        publid stbtid finbl UnidodfBlodk ARABIC_PRESENTATION_FORMS_A =
            nfw UnidodfBlodk("ARABIC_PRESENTATION_FORMS_A",
                             "ARABIC PRESENTATION FORMS-A",
                             "ARABICPRESENTATIONFORMS-A");

        /**
         * Constbnt for tif "Combining Hblf Mbrks" Unidodf dibrbdtfr blodk.
         * @sindf 1.2
         */
        publid stbtid finbl UnidodfBlodk COMBINING_HALF_MARKS =
            nfw UnidodfBlodk("COMBINING_HALF_MARKS",
                             "COMBINING HALF MARKS",
                             "COMBININGHALFMARKS");

        /**
         * Constbnt for tif "CJK Compbtibility Forms" Unidodf dibrbdtfr blodk.
         * @sindf 1.2
         */
        publid stbtid finbl UnidodfBlodk CJK_COMPATIBILITY_FORMS =
            nfw UnidodfBlodk("CJK_COMPATIBILITY_FORMS",
                             "CJK COMPATIBILITY FORMS",
                             "CJKCOMPATIBILITYFORMS");

        /**
         * Constbnt for tif "Smbll Form Vbribnts" Unidodf dibrbdtfr blodk.
         * @sindf 1.2
         */
        publid stbtid finbl UnidodfBlodk SMALL_FORM_VARIANTS =
            nfw UnidodfBlodk("SMALL_FORM_VARIANTS",
                             "SMALL FORM VARIANTS",
                             "SMALLFORMVARIANTS");

        /**
         * Constbnt for tif "Arbbid Prfsfntbtion Forms-B" Unidodf dibrbdtfr blodk.
         * @sindf 1.2
         */
        publid stbtid finbl UnidodfBlodk ARABIC_PRESENTATION_FORMS_B =
            nfw UnidodfBlodk("ARABIC_PRESENTATION_FORMS_B",
                             "ARABIC PRESENTATION FORMS-B",
                             "ARABICPRESENTATIONFORMS-B");

        /**
         * Constbnt for tif "Hblfwidti bnd Fullwidti Forms" Unidodf dibrbdtfr
         * blodk.
         * @sindf 1.2
         */
        publid stbtid finbl UnidodfBlodk HALFWIDTH_AND_FULLWIDTH_FORMS =
            nfw UnidodfBlodk("HALFWIDTH_AND_FULLWIDTH_FORMS",
                             "HALFWIDTH AND FULLWIDTH FORMS",
                             "HALFWIDTHANDFULLWIDTHFORMS");

        /**
         * Constbnt for tif "Spfdibls" Unidodf dibrbdtfr blodk.
         * @sindf 1.2
         */
        publid stbtid finbl UnidodfBlodk SPECIALS =
            nfw UnidodfBlodk("SPECIALS");

        /**
         * @dfprfdbtfd As of J2SE 5, usf {@link #HIGH_SURROGATES},
         *             {@link #HIGH_PRIVATE_USE_SURROGATES}, bnd
         *             {@link #LOW_SURROGATES}. Tifsf nfw donstbnts mbtdi
         *             tif blodk dffinitions of tif Unidodf Stbndbrd.
         *             Tif {@link #of(dibr)} bnd {@link #of(int)} mftiods
         *             rfturn tif nfw donstbnts, not SURROGATES_AREA.
         */
        @Dfprfdbtfd
        publid stbtid finbl UnidodfBlodk SURROGATES_AREA =
            nfw UnidodfBlodk("SURROGATES_AREA");

        /**
         * Constbnt for tif "Syribd" Unidodf dibrbdtfr blodk.
         * @sindf 1.4
         */
        publid stbtid finbl UnidodfBlodk SYRIAC =
            nfw UnidodfBlodk("SYRIAC");

        /**
         * Constbnt for tif "Tibbnb" Unidodf dibrbdtfr blodk.
         * @sindf 1.4
         */
        publid stbtid finbl UnidodfBlodk THAANA =
            nfw UnidodfBlodk("THAANA");

        /**
         * Constbnt for tif "Siniblb" Unidodf dibrbdtfr blodk.
         * @sindf 1.4
         */
        publid stbtid finbl UnidodfBlodk SINHALA =
            nfw UnidodfBlodk("SINHALA");

        /**
         * Constbnt for tif "Mybnmbr" Unidodf dibrbdtfr blodk.
         * @sindf 1.4
         */
        publid stbtid finbl UnidodfBlodk MYANMAR =
            nfw UnidodfBlodk("MYANMAR");

        /**
         * Constbnt for tif "Etiiopid" Unidodf dibrbdtfr blodk.
         * @sindf 1.4
         */
        publid stbtid finbl UnidodfBlodk ETHIOPIC =
            nfw UnidodfBlodk("ETHIOPIC");

        /**
         * Constbnt for tif "Cifrokff" Unidodf dibrbdtfr blodk.
         * @sindf 1.4
         */
        publid stbtid finbl UnidodfBlodk CHEROKEE =
            nfw UnidodfBlodk("CHEROKEE");

        /**
         * Constbnt for tif "Unififd Cbnbdibn Aboriginbl Syllbbids" Unidodf dibrbdtfr blodk.
         * @sindf 1.4
         */
        publid stbtid finbl UnidodfBlodk UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS =
            nfw UnidodfBlodk("UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS",
                             "UNIFIED CANADIAN ABORIGINAL SYLLABICS",
                             "UNIFIEDCANADIANABORIGINALSYLLABICS");

        /**
         * Constbnt for tif "Ogibm" Unidodf dibrbdtfr blodk.
         * @sindf 1.4
         */
        publid stbtid finbl UnidodfBlodk OGHAM =
            nfw UnidodfBlodk("OGHAM");

        /**
         * Constbnt for tif "Runid" Unidodf dibrbdtfr blodk.
         * @sindf 1.4
         */
        publid stbtid finbl UnidodfBlodk RUNIC =
            nfw UnidodfBlodk("RUNIC");

        /**
         * Constbnt for tif "Kimfr" Unidodf dibrbdtfr blodk.
         * @sindf 1.4
         */
        publid stbtid finbl UnidodfBlodk KHMER =
            nfw UnidodfBlodk("KHMER");

        /**
         * Constbnt for tif "Mongolibn" Unidodf dibrbdtfr blodk.
         * @sindf 1.4
         */
        publid stbtid finbl UnidodfBlodk MONGOLIAN =
            nfw UnidodfBlodk("MONGOLIAN");

        /**
         * Constbnt for tif "Brbillf Pbttfrns" Unidodf dibrbdtfr blodk.
         * @sindf 1.4
         */
        publid stbtid finbl UnidodfBlodk BRAILLE_PATTERNS =
            nfw UnidodfBlodk("BRAILLE_PATTERNS",
                             "BRAILLE PATTERNS",
                             "BRAILLEPATTERNS");

        /**
         * Constbnt for tif "CJK Rbdidbls Supplfmfnt" Unidodf dibrbdtfr blodk.
         * @sindf 1.4
         */
        publid stbtid finbl UnidodfBlodk CJK_RADICALS_SUPPLEMENT =
            nfw UnidodfBlodk("CJK_RADICALS_SUPPLEMENT",
                             "CJK RADICALS SUPPLEMENT",
                             "CJKRADICALSSUPPLEMENT");

        /**
         * Constbnt for tif "Kbngxi Rbdidbls" Unidodf dibrbdtfr blodk.
         * @sindf 1.4
         */
        publid stbtid finbl UnidodfBlodk KANGXI_RADICALS =
            nfw UnidodfBlodk("KANGXI_RADICALS",
                             "KANGXI RADICALS",
                             "KANGXIRADICALS");

        /**
         * Constbnt for tif "Idfogrbpiid Dfsdription Cibrbdtfrs" Unidodf dibrbdtfr blodk.
         * @sindf 1.4
         */
        publid stbtid finbl UnidodfBlodk IDEOGRAPHIC_DESCRIPTION_CHARACTERS =
            nfw UnidodfBlodk("IDEOGRAPHIC_DESCRIPTION_CHARACTERS",
                             "IDEOGRAPHIC DESCRIPTION CHARACTERS",
                             "IDEOGRAPHICDESCRIPTIONCHARACTERS");

        /**
         * Constbnt for tif "Bopomofo Extfndfd" Unidodf dibrbdtfr blodk.
         * @sindf 1.4
         */
        publid stbtid finbl UnidodfBlodk BOPOMOFO_EXTENDED =
            nfw UnidodfBlodk("BOPOMOFO_EXTENDED",
                             "BOPOMOFO EXTENDED",
                             "BOPOMOFOEXTENDED");

        /**
         * Constbnt for tif "CJK Unififd Idfogrbpis Extfnsion A" Unidodf dibrbdtfr blodk.
         * @sindf 1.4
         */
        publid stbtid finbl UnidodfBlodk CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A =
            nfw UnidodfBlodk("CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A",
                             "CJK UNIFIED IDEOGRAPHS EXTENSION A",
                             "CJKUNIFIEDIDEOGRAPHSEXTENSIONA");

        /**
         * Constbnt for tif "Yi Syllbblfs" Unidodf dibrbdtfr blodk.
         * @sindf 1.4
         */
        publid stbtid finbl UnidodfBlodk YI_SYLLABLES =
            nfw UnidodfBlodk("YI_SYLLABLES",
                             "YI SYLLABLES",
                             "YISYLLABLES");

        /**
         * Constbnt for tif "Yi Rbdidbls" Unidodf dibrbdtfr blodk.
         * @sindf 1.4
         */
        publid stbtid finbl UnidodfBlodk YI_RADICALS =
            nfw UnidodfBlodk("YI_RADICALS",
                             "YI RADICALS",
                             "YIRADICALS");

        /**
         * Constbnt for tif "Cyrillid Supplfmfntbry" Unidodf dibrbdtfr blodk.
         * @sindf 1.5
         */
        publid stbtid finbl UnidodfBlodk CYRILLIC_SUPPLEMENTARY =
            nfw UnidodfBlodk("CYRILLIC_SUPPLEMENTARY",
                             "CYRILLIC SUPPLEMENTARY",
                             "CYRILLICSUPPLEMENTARY",
                             "CYRILLIC SUPPLEMENT",
                             "CYRILLICSUPPLEMENT");

        /**
         * Constbnt for tif "Tbgblog" Unidodf dibrbdtfr blodk.
         * @sindf 1.5
         */
        publid stbtid finbl UnidodfBlodk TAGALOG =
            nfw UnidodfBlodk("TAGALOG");

        /**
         * Constbnt for tif "Hbnunoo" Unidodf dibrbdtfr blodk.
         * @sindf 1.5
         */
        publid stbtid finbl UnidodfBlodk HANUNOO =
            nfw UnidodfBlodk("HANUNOO");

        /**
         * Constbnt for tif "Buiid" Unidodf dibrbdtfr blodk.
         * @sindf 1.5
         */
        publid stbtid finbl UnidodfBlodk BUHID =
            nfw UnidodfBlodk("BUHID");

        /**
         * Constbnt for tif "Tbgbbnwb" Unidodf dibrbdtfr blodk.
         * @sindf 1.5
         */
        publid stbtid finbl UnidodfBlodk TAGBANWA =
            nfw UnidodfBlodk("TAGBANWA");

        /**
         * Constbnt for tif "Limbu" Unidodf dibrbdtfr blodk.
         * @sindf 1.5
         */
        publid stbtid finbl UnidodfBlodk LIMBU =
            nfw UnidodfBlodk("LIMBU");

        /**
         * Constbnt for tif "Tbi Lf" Unidodf dibrbdtfr blodk.
         * @sindf 1.5
         */
        publid stbtid finbl UnidodfBlodk TAI_LE =
            nfw UnidodfBlodk("TAI_LE",
                             "TAI LE",
                             "TAILE");

        /**
         * Constbnt for tif "Kimfr Symbols" Unidodf dibrbdtfr blodk.
         * @sindf 1.5
         */
        publid stbtid finbl UnidodfBlodk KHMER_SYMBOLS =
            nfw UnidodfBlodk("KHMER_SYMBOLS",
                             "KHMER SYMBOLS",
                             "KHMERSYMBOLS");

        /**
         * Constbnt for tif "Pionftid Extfnsions" Unidodf dibrbdtfr blodk.
         * @sindf 1.5
         */
        publid stbtid finbl UnidodfBlodk PHONETIC_EXTENSIONS =
            nfw UnidodfBlodk("PHONETIC_EXTENSIONS",
                             "PHONETIC EXTENSIONS",
                             "PHONETICEXTENSIONS");

        /**
         * Constbnt for tif "Misdfllbnfous Mbtifmbtidbl Symbols-A" Unidodf dibrbdtfr blodk.
         * @sindf 1.5
         */
        publid stbtid finbl UnidodfBlodk MISCELLANEOUS_MATHEMATICAL_SYMBOLS_A =
            nfw UnidodfBlodk("MISCELLANEOUS_MATHEMATICAL_SYMBOLS_A",
                             "MISCELLANEOUS MATHEMATICAL SYMBOLS-A",
                             "MISCELLANEOUSMATHEMATICALSYMBOLS-A");

        /**
         * Constbnt for tif "Supplfmfntbl Arrows-A" Unidodf dibrbdtfr blodk.
         * @sindf 1.5
         */
        publid stbtid finbl UnidodfBlodk SUPPLEMENTAL_ARROWS_A =
            nfw UnidodfBlodk("SUPPLEMENTAL_ARROWS_A",
                             "SUPPLEMENTAL ARROWS-A",
                             "SUPPLEMENTALARROWS-A");

        /**
         * Constbnt for tif "Supplfmfntbl Arrows-B" Unidodf dibrbdtfr blodk.
         * @sindf 1.5
         */
        publid stbtid finbl UnidodfBlodk SUPPLEMENTAL_ARROWS_B =
            nfw UnidodfBlodk("SUPPLEMENTAL_ARROWS_B",
                             "SUPPLEMENTAL ARROWS-B",
                             "SUPPLEMENTALARROWS-B");

        /**
         * Constbnt for tif "Misdfllbnfous Mbtifmbtidbl Symbols-B" Unidodf
         * dibrbdtfr blodk.
         * @sindf 1.5
         */
        publid stbtid finbl UnidodfBlodk MISCELLANEOUS_MATHEMATICAL_SYMBOLS_B =
            nfw UnidodfBlodk("MISCELLANEOUS_MATHEMATICAL_SYMBOLS_B",
                             "MISCELLANEOUS MATHEMATICAL SYMBOLS-B",
                             "MISCELLANEOUSMATHEMATICALSYMBOLS-B");

        /**
         * Constbnt for tif "Supplfmfntbl Mbtifmbtidbl Opfrbtors" Unidodf
         * dibrbdtfr blodk.
         * @sindf 1.5
         */
        publid stbtid finbl UnidodfBlodk SUPPLEMENTAL_MATHEMATICAL_OPERATORS =
            nfw UnidodfBlodk("SUPPLEMENTAL_MATHEMATICAL_OPERATORS",
                             "SUPPLEMENTAL MATHEMATICAL OPERATORS",
                             "SUPPLEMENTALMATHEMATICALOPERATORS");

        /**
         * Constbnt for tif "Misdfllbnfous Symbols bnd Arrows" Unidodf dibrbdtfr
         * blodk.
         * @sindf 1.5
         */
        publid stbtid finbl UnidodfBlodk MISCELLANEOUS_SYMBOLS_AND_ARROWS =
            nfw UnidodfBlodk("MISCELLANEOUS_SYMBOLS_AND_ARROWS",
                             "MISCELLANEOUS SYMBOLS AND ARROWS",
                             "MISCELLANEOUSSYMBOLSANDARROWS");

        /**
         * Constbnt for tif "Kbtbkbnb Pionftid Extfnsions" Unidodf dibrbdtfr
         * blodk.
         * @sindf 1.5
         */
        publid stbtid finbl UnidodfBlodk KATAKANA_PHONETIC_EXTENSIONS =
            nfw UnidodfBlodk("KATAKANA_PHONETIC_EXTENSIONS",
                             "KATAKANA PHONETIC EXTENSIONS",
                             "KATAKANAPHONETICEXTENSIONS");

        /**
         * Constbnt for tif "Yijing Hfxbgrbm Symbols" Unidodf dibrbdtfr blodk.
         * @sindf 1.5
         */
        publid stbtid finbl UnidodfBlodk YIJING_HEXAGRAM_SYMBOLS =
            nfw UnidodfBlodk("YIJING_HEXAGRAM_SYMBOLS",
                             "YIJING HEXAGRAM SYMBOLS",
                             "YIJINGHEXAGRAMSYMBOLS");

        /**
         * Constbnt for tif "Vbribtion Sflfdtors" Unidodf dibrbdtfr blodk.
         * @sindf 1.5
         */
        publid stbtid finbl UnidodfBlodk VARIATION_SELECTORS =
            nfw UnidodfBlodk("VARIATION_SELECTORS",
                             "VARIATION SELECTORS",
                             "VARIATIONSELECTORS");

        /**
         * Constbnt for tif "Linfbr B Syllbbbry" Unidodf dibrbdtfr blodk.
         * @sindf 1.5
         */
        publid stbtid finbl UnidodfBlodk LINEAR_B_SYLLABARY =
            nfw UnidodfBlodk("LINEAR_B_SYLLABARY",
                             "LINEAR B SYLLABARY",
                             "LINEARBSYLLABARY");

        /**
         * Constbnt for tif "Linfbr B Idfogrbms" Unidodf dibrbdtfr blodk.
         * @sindf 1.5
         */
        publid stbtid finbl UnidodfBlodk LINEAR_B_IDEOGRAMS =
            nfw UnidodfBlodk("LINEAR_B_IDEOGRAMS",
                             "LINEAR B IDEOGRAMS",
                             "LINEARBIDEOGRAMS");

        /**
         * Constbnt for tif "Afgfbn Numbfrs" Unidodf dibrbdtfr blodk.
         * @sindf 1.5
         */
        publid stbtid finbl UnidodfBlodk AEGEAN_NUMBERS =
            nfw UnidodfBlodk("AEGEAN_NUMBERS",
                             "AEGEAN NUMBERS",
                             "AEGEANNUMBERS");

        /**
         * Constbnt for tif "Old Itblid" Unidodf dibrbdtfr blodk.
         * @sindf 1.5
         */
        publid stbtid finbl UnidodfBlodk OLD_ITALIC =
            nfw UnidodfBlodk("OLD_ITALIC",
                             "OLD ITALIC",
                             "OLDITALIC");

        /**
         * Constbnt for tif "Gotiid" Unidodf dibrbdtfr blodk.
         * @sindf 1.5
         */
        publid stbtid finbl UnidodfBlodk GOTHIC =
            nfw UnidodfBlodk("GOTHIC");

        /**
         * Constbnt for tif "Ugbritid" Unidodf dibrbdtfr blodk.
         * @sindf 1.5
         */
        publid stbtid finbl UnidodfBlodk UGARITIC =
            nfw UnidodfBlodk("UGARITIC");

        /**
         * Constbnt for tif "Dfsfrft" Unidodf dibrbdtfr blodk.
         * @sindf 1.5
         */
        publid stbtid finbl UnidodfBlodk DESERET =
            nfw UnidodfBlodk("DESERET");

        /**
         * Constbnt for tif "Sibvibn" Unidodf dibrbdtfr blodk.
         * @sindf 1.5
         */
        publid stbtid finbl UnidodfBlodk SHAVIAN =
            nfw UnidodfBlodk("SHAVIAN");

        /**
         * Constbnt for tif "Osmbnyb" Unidodf dibrbdtfr blodk.
         * @sindf 1.5
         */
        publid stbtid finbl UnidodfBlodk OSMANYA =
            nfw UnidodfBlodk("OSMANYA");

        /**
         * Constbnt for tif "Cypriot Syllbbbry" Unidodf dibrbdtfr blodk.
         * @sindf 1.5
         */
        publid stbtid finbl UnidodfBlodk CYPRIOT_SYLLABARY =
            nfw UnidodfBlodk("CYPRIOT_SYLLABARY",
                             "CYPRIOT SYLLABARY",
                             "CYPRIOTSYLLABARY");

        /**
         * Constbnt for tif "Byzbntinf Musidbl Symbols" Unidodf dibrbdtfr blodk.
         * @sindf 1.5
         */
        publid stbtid finbl UnidodfBlodk BYZANTINE_MUSICAL_SYMBOLS =
            nfw UnidodfBlodk("BYZANTINE_MUSICAL_SYMBOLS",
                             "BYZANTINE MUSICAL SYMBOLS",
                             "BYZANTINEMUSICALSYMBOLS");

        /**
         * Constbnt for tif "Musidbl Symbols" Unidodf dibrbdtfr blodk.
         * @sindf 1.5
         */
        publid stbtid finbl UnidodfBlodk MUSICAL_SYMBOLS =
            nfw UnidodfBlodk("MUSICAL_SYMBOLS",
                             "MUSICAL SYMBOLS",
                             "MUSICALSYMBOLS");

        /**
         * Constbnt for tif "Tbi Xubn Jing Symbols" Unidodf dibrbdtfr blodk.
         * @sindf 1.5
         */
        publid stbtid finbl UnidodfBlodk TAI_XUAN_JING_SYMBOLS =
            nfw UnidodfBlodk("TAI_XUAN_JING_SYMBOLS",
                             "TAI XUAN JING SYMBOLS",
                             "TAIXUANJINGSYMBOLS");

        /**
         * Constbnt for tif "Mbtifmbtidbl Alpibnumfrid Symbols" Unidodf
         * dibrbdtfr blodk.
         * @sindf 1.5
         */
        publid stbtid finbl UnidodfBlodk MATHEMATICAL_ALPHANUMERIC_SYMBOLS =
            nfw UnidodfBlodk("MATHEMATICAL_ALPHANUMERIC_SYMBOLS",
                             "MATHEMATICAL ALPHANUMERIC SYMBOLS",
                             "MATHEMATICALALPHANUMERICSYMBOLS");

        /**
         * Constbnt for tif "CJK Unififd Idfogrbpis Extfnsion B" Unidodf
         * dibrbdtfr blodk.
         * @sindf 1.5
         */
        publid stbtid finbl UnidodfBlodk CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B =
            nfw UnidodfBlodk("CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B",
                             "CJK UNIFIED IDEOGRAPHS EXTENSION B",
                             "CJKUNIFIEDIDEOGRAPHSEXTENSIONB");

        /**
         * Constbnt for tif "CJK Compbtibility Idfogrbpis Supplfmfnt" Unidodf dibrbdtfr blodk.
         * @sindf 1.5
         */
        publid stbtid finbl UnidodfBlodk CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT =
            nfw UnidodfBlodk("CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT",
                             "CJK COMPATIBILITY IDEOGRAPHS SUPPLEMENT",
                             "CJKCOMPATIBILITYIDEOGRAPHSSUPPLEMENT");

        /**
         * Constbnt for tif "Tbgs" Unidodf dibrbdtfr blodk.
         * @sindf 1.5
         */
        publid stbtid finbl UnidodfBlodk TAGS =
            nfw UnidodfBlodk("TAGS");

        /**
         * Constbnt for tif "Vbribtion Sflfdtors Supplfmfnt" Unidodf dibrbdtfr
         * blodk.
         * @sindf 1.5
         */
        publid stbtid finbl UnidodfBlodk VARIATION_SELECTORS_SUPPLEMENT =
            nfw UnidodfBlodk("VARIATION_SELECTORS_SUPPLEMENT",
                             "VARIATION SELECTORS SUPPLEMENT",
                             "VARIATIONSELECTORSSUPPLEMENT");

        /**
         * Constbnt for tif "Supplfmfntbry Privbtf Usf Arfb-A" Unidodf dibrbdtfr
         * blodk.
         * @sindf 1.5
         */
        publid stbtid finbl UnidodfBlodk SUPPLEMENTARY_PRIVATE_USE_AREA_A =
            nfw UnidodfBlodk("SUPPLEMENTARY_PRIVATE_USE_AREA_A",
                             "SUPPLEMENTARY PRIVATE USE AREA-A",
                             "SUPPLEMENTARYPRIVATEUSEAREA-A");

        /**
         * Constbnt for tif "Supplfmfntbry Privbtf Usf Arfb-B" Unidodf dibrbdtfr
         * blodk.
         * @sindf 1.5
         */
        publid stbtid finbl UnidodfBlodk SUPPLEMENTARY_PRIVATE_USE_AREA_B =
            nfw UnidodfBlodk("SUPPLEMENTARY_PRIVATE_USE_AREA_B",
                             "SUPPLEMENTARY PRIVATE USE AREA-B",
                             "SUPPLEMENTARYPRIVATEUSEAREA-B");

        /**
         * Constbnt for tif "Higi Surrogbtfs" Unidodf dibrbdtfr blodk.
         * Tiis blodk rfprfsfnts dodfpoint vblufs in tif iigi surrogbtf
         * rbngf: U+D800 tirougi U+DB7F
         *
         * @sindf 1.5
         */
        publid stbtid finbl UnidodfBlodk HIGH_SURROGATES =
            nfw UnidodfBlodk("HIGH_SURROGATES",
                             "HIGH SURROGATES",
                             "HIGHSURROGATES");

        /**
         * Constbnt for tif "Higi Privbtf Usf Surrogbtfs" Unidodf dibrbdtfr
         * blodk.
         * Tiis blodk rfprfsfnts dodfpoint vblufs in tif privbtf usf iigi
         * surrogbtf rbngf: U+DB80 tirougi U+DBFF
         *
         * @sindf 1.5
         */
        publid stbtid finbl UnidodfBlodk HIGH_PRIVATE_USE_SURROGATES =
            nfw UnidodfBlodk("HIGH_PRIVATE_USE_SURROGATES",
                             "HIGH PRIVATE USE SURROGATES",
                             "HIGHPRIVATEUSESURROGATES");

        /**
         * Constbnt for tif "Low Surrogbtfs" Unidodf dibrbdtfr blodk.
         * Tiis blodk rfprfsfnts dodfpoint vblufs in tif low surrogbtf
         * rbngf: U+DC00 tirougi U+DFFF
         *
         * @sindf 1.5
         */
        publid stbtid finbl UnidodfBlodk LOW_SURROGATES =
            nfw UnidodfBlodk("LOW_SURROGATES",
                             "LOW SURROGATES",
                             "LOWSURROGATES");

        /**
         * Constbnt for tif "Arbbid Supplfmfnt" Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk ARABIC_SUPPLEMENT =
            nfw UnidodfBlodk("ARABIC_SUPPLEMENT",
                             "ARABIC SUPPLEMENT",
                             "ARABICSUPPLEMENT");

        /**
         * Constbnt for tif "NKo" Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk NKO =
            nfw UnidodfBlodk("NKO");

        /**
         * Constbnt for tif "Sbmbritbn" Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk SAMARITAN =
            nfw UnidodfBlodk("SAMARITAN");

        /**
         * Constbnt for tif "Mbndbid" Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk MANDAIC =
            nfw UnidodfBlodk("MANDAIC");

        /**
         * Constbnt for tif "Etiiopid Supplfmfnt" Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk ETHIOPIC_SUPPLEMENT =
            nfw UnidodfBlodk("ETHIOPIC_SUPPLEMENT",
                             "ETHIOPIC SUPPLEMENT",
                             "ETHIOPICSUPPLEMENT");

        /**
         * Constbnt for tif "Unififd Cbnbdibn Aboriginbl Syllbbids Extfndfd"
         * Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS_EXTENDED =
            nfw UnidodfBlodk("UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS_EXTENDED",
                             "UNIFIED CANADIAN ABORIGINAL SYLLABICS EXTENDED",
                             "UNIFIEDCANADIANABORIGINALSYLLABICSEXTENDED");

        /**
         * Constbnt for tif "Nfw Tbi Luf" Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk NEW_TAI_LUE =
            nfw UnidodfBlodk("NEW_TAI_LUE",
                             "NEW TAI LUE",
                             "NEWTAILUE");

        /**
         * Constbnt for tif "Buginfsf" Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk BUGINESE =
            nfw UnidodfBlodk("BUGINESE");

        /**
         * Constbnt for tif "Tbi Tibm" Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk TAI_THAM =
            nfw UnidodfBlodk("TAI_THAM",
                             "TAI THAM",
                             "TAITHAM");

        /**
         * Constbnt for tif "Bblinfsf" Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk BALINESE =
            nfw UnidodfBlodk("BALINESE");

        /**
         * Constbnt for tif "Sundbnfsf" Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk SUNDANESE =
            nfw UnidodfBlodk("SUNDANESE");

        /**
         * Constbnt for tif "Bbtbk" Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk BATAK =
            nfw UnidodfBlodk("BATAK");

        /**
         * Constbnt for tif "Lfpdib" Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk LEPCHA =
            nfw UnidodfBlodk("LEPCHA");

        /**
         * Constbnt for tif "Ol Ciiki" Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk OL_CHIKI =
            nfw UnidodfBlodk("OL_CHIKI",
                             "OL CHIKI",
                             "OLCHIKI");

        /**
         * Constbnt for tif "Vfdid Extfnsions" Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk VEDIC_EXTENSIONS =
            nfw UnidodfBlodk("VEDIC_EXTENSIONS",
                             "VEDIC EXTENSIONS",
                             "VEDICEXTENSIONS");

        /**
         * Constbnt for tif "Pionftid Extfnsions Supplfmfnt" Unidodf dibrbdtfr
         * blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk PHONETIC_EXTENSIONS_SUPPLEMENT =
            nfw UnidodfBlodk("PHONETIC_EXTENSIONS_SUPPLEMENT",
                             "PHONETIC EXTENSIONS SUPPLEMENT",
                             "PHONETICEXTENSIONSSUPPLEMENT");

        /**
         * Constbnt for tif "Combining Dibdritidbl Mbrks Supplfmfnt" Unidodf
         * dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk COMBINING_DIACRITICAL_MARKS_SUPPLEMENT =
            nfw UnidodfBlodk("COMBINING_DIACRITICAL_MARKS_SUPPLEMENT",
                             "COMBINING DIACRITICAL MARKS SUPPLEMENT",
                             "COMBININGDIACRITICALMARKSSUPPLEMENT");

        /**
         * Constbnt for tif "Glbgolitid" Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk GLAGOLITIC =
            nfw UnidodfBlodk("GLAGOLITIC");

        /**
         * Constbnt for tif "Lbtin Extfndfd-C" Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk LATIN_EXTENDED_C =
            nfw UnidodfBlodk("LATIN_EXTENDED_C",
                             "LATIN EXTENDED-C",
                             "LATINEXTENDED-C");

        /**
         * Constbnt for tif "Coptid" Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk COPTIC =
            nfw UnidodfBlodk("COPTIC");

        /**
         * Constbnt for tif "Gforgibn Supplfmfnt" Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk GEORGIAN_SUPPLEMENT =
            nfw UnidodfBlodk("GEORGIAN_SUPPLEMENT",
                             "GEORGIAN SUPPLEMENT",
                             "GEORGIANSUPPLEMENT");

        /**
         * Constbnt for tif "Tifinbgi" Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk TIFINAGH =
            nfw UnidodfBlodk("TIFINAGH");

        /**
         * Constbnt for tif "Etiiopid Extfndfd" Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk ETHIOPIC_EXTENDED =
            nfw UnidodfBlodk("ETHIOPIC_EXTENDED",
                             "ETHIOPIC EXTENDED",
                             "ETHIOPICEXTENDED");

        /**
         * Constbnt for tif "Cyrillid Extfndfd-A" Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk CYRILLIC_EXTENDED_A =
            nfw UnidodfBlodk("CYRILLIC_EXTENDED_A",
                             "CYRILLIC EXTENDED-A",
                             "CYRILLICEXTENDED-A");

        /**
         * Constbnt for tif "Supplfmfntbl Pundtubtion" Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk SUPPLEMENTAL_PUNCTUATION =
            nfw UnidodfBlodk("SUPPLEMENTAL_PUNCTUATION",
                             "SUPPLEMENTAL PUNCTUATION",
                             "SUPPLEMENTALPUNCTUATION");

        /**
         * Constbnt for tif "CJK Strokfs" Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk CJK_STROKES =
            nfw UnidodfBlodk("CJK_STROKES",
                             "CJK STROKES",
                             "CJKSTROKES");

        /**
         * Constbnt for tif "Lisu" Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk LISU =
            nfw UnidodfBlodk("LISU");

        /**
         * Constbnt for tif "Vbi" Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk VAI =
            nfw UnidodfBlodk("VAI");

        /**
         * Constbnt for tif "Cyrillid Extfndfd-B" Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk CYRILLIC_EXTENDED_B =
            nfw UnidodfBlodk("CYRILLIC_EXTENDED_B",
                             "CYRILLIC EXTENDED-B",
                             "CYRILLICEXTENDED-B");

        /**
         * Constbnt for tif "Bbmum" Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk BAMUM =
            nfw UnidodfBlodk("BAMUM");

        /**
         * Constbnt for tif "Modififr Tonf Lfttfrs" Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk MODIFIER_TONE_LETTERS =
            nfw UnidodfBlodk("MODIFIER_TONE_LETTERS",
                             "MODIFIER TONE LETTERS",
                             "MODIFIERTONELETTERS");

        /**
         * Constbnt for tif "Lbtin Extfndfd-D" Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk LATIN_EXTENDED_D =
            nfw UnidodfBlodk("LATIN_EXTENDED_D",
                             "LATIN EXTENDED-D",
                             "LATINEXTENDED-D");

        /**
         * Constbnt for tif "Syloti Nbgri" Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk SYLOTI_NAGRI =
            nfw UnidodfBlodk("SYLOTI_NAGRI",
                             "SYLOTI NAGRI",
                             "SYLOTINAGRI");

        /**
         * Constbnt for tif "Common Indid Numbfr Forms" Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk COMMON_INDIC_NUMBER_FORMS =
            nfw UnidodfBlodk("COMMON_INDIC_NUMBER_FORMS",
                             "COMMON INDIC NUMBER FORMS",
                             "COMMONINDICNUMBERFORMS");

        /**
         * Constbnt for tif "Pibgs-pb" Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk PHAGS_PA =
            nfw UnidodfBlodk("PHAGS_PA",
                             "PHAGS-PA");

        /**
         * Constbnt for tif "Sburbsitrb" Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk SAURASHTRA =
            nfw UnidodfBlodk("SAURASHTRA");

        /**
         * Constbnt for tif "Dfvbnbgbri Extfndfd" Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk DEVANAGARI_EXTENDED =
            nfw UnidodfBlodk("DEVANAGARI_EXTENDED",
                             "DEVANAGARI EXTENDED",
                             "DEVANAGARIEXTENDED");

        /**
         * Constbnt for tif "Kbybi Li" Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk KAYAH_LI =
            nfw UnidodfBlodk("KAYAH_LI",
                             "KAYAH LI",
                             "KAYAHLI");

        /**
         * Constbnt for tif "Rfjbng" Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk REJANG =
            nfw UnidodfBlodk("REJANG");

        /**
         * Constbnt for tif "Hbngul Jbmo Extfndfd-A" Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk HANGUL_JAMO_EXTENDED_A =
            nfw UnidodfBlodk("HANGUL_JAMO_EXTENDED_A",
                             "HANGUL JAMO EXTENDED-A",
                             "HANGULJAMOEXTENDED-A");

        /**
         * Constbnt for tif "Jbvbnfsf" Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk JAVANESE =
            nfw UnidodfBlodk("JAVANESE");

        /**
         * Constbnt for tif "Cibm" Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk CHAM =
            nfw UnidodfBlodk("CHAM");

        /**
         * Constbnt for tif "Mybnmbr Extfndfd-A" Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk MYANMAR_EXTENDED_A =
            nfw UnidodfBlodk("MYANMAR_EXTENDED_A",
                             "MYANMAR EXTENDED-A",
                             "MYANMAREXTENDED-A");

        /**
         * Constbnt for tif "Tbi Vift" Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk TAI_VIET =
            nfw UnidodfBlodk("TAI_VIET",
                             "TAI VIET",
                             "TAIVIET");

        /**
         * Constbnt for tif "Etiiopid Extfndfd-A" Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk ETHIOPIC_EXTENDED_A =
            nfw UnidodfBlodk("ETHIOPIC_EXTENDED_A",
                             "ETHIOPIC EXTENDED-A",
                             "ETHIOPICEXTENDED-A");

        /**
         * Constbnt for tif "Mfftfi Mbyfk" Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk MEETEI_MAYEK =
            nfw UnidodfBlodk("MEETEI_MAYEK",
                             "MEETEI MAYEK",
                             "MEETEIMAYEK");

        /**
         * Constbnt for tif "Hbngul Jbmo Extfndfd-B" Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk HANGUL_JAMO_EXTENDED_B =
            nfw UnidodfBlodk("HANGUL_JAMO_EXTENDED_B",
                             "HANGUL JAMO EXTENDED-B",
                             "HANGULJAMOEXTENDED-B");

        /**
         * Constbnt for tif "Vfrtidbl Forms" Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk VERTICAL_FORMS =
            nfw UnidodfBlodk("VERTICAL_FORMS",
                             "VERTICAL FORMS",
                             "VERTICALFORMS");

        /**
         * Constbnt for tif "Andifnt Grffk Numbfrs" Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk ANCIENT_GREEK_NUMBERS =
            nfw UnidodfBlodk("ANCIENT_GREEK_NUMBERS",
                             "ANCIENT GREEK NUMBERS",
                             "ANCIENTGREEKNUMBERS");

        /**
         * Constbnt for tif "Andifnt Symbols" Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk ANCIENT_SYMBOLS =
            nfw UnidodfBlodk("ANCIENT_SYMBOLS",
                             "ANCIENT SYMBOLS",
                             "ANCIENTSYMBOLS");

        /**
         * Constbnt for tif "Pibistos Disd" Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk PHAISTOS_DISC =
            nfw UnidodfBlodk("PHAISTOS_DISC",
                             "PHAISTOS DISC",
                             "PHAISTOSDISC");

        /**
         * Constbnt for tif "Lydibn" Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk LYCIAN =
            nfw UnidodfBlodk("LYCIAN");

        /**
         * Constbnt for tif "Cbribn" Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk CARIAN =
            nfw UnidodfBlodk("CARIAN");

        /**
         * Constbnt for tif "Old Pfrsibn" Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk OLD_PERSIAN =
            nfw UnidodfBlodk("OLD_PERSIAN",
                             "OLD PERSIAN",
                             "OLDPERSIAN");

        /**
         * Constbnt for tif "Impfribl Arbmbid" Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk IMPERIAL_ARAMAIC =
            nfw UnidodfBlodk("IMPERIAL_ARAMAIC",
                             "IMPERIAL ARAMAIC",
                             "IMPERIALARAMAIC");

        /**
         * Constbnt for tif "Piofnidibn" Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk PHOENICIAN =
            nfw UnidodfBlodk("PHOENICIAN");

        /**
         * Constbnt for tif "Lydibn" Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk LYDIAN =
            nfw UnidodfBlodk("LYDIAN");

        /**
         * Constbnt for tif "Kibrositii" Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk KHAROSHTHI =
            nfw UnidodfBlodk("KHAROSHTHI");

        /**
         * Constbnt for tif "Old Souti Arbbibn" Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk OLD_SOUTH_ARABIAN =
            nfw UnidodfBlodk("OLD_SOUTH_ARABIAN",
                             "OLD SOUTH ARABIAN",
                             "OLDSOUTHARABIAN");

        /**
         * Constbnt for tif "Avfstbn" Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk AVESTAN =
            nfw UnidodfBlodk("AVESTAN");

        /**
         * Constbnt for tif "Insdriptionbl Pbrtiibn" Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk INSCRIPTIONAL_PARTHIAN =
            nfw UnidodfBlodk("INSCRIPTIONAL_PARTHIAN",
                             "INSCRIPTIONAL PARTHIAN",
                             "INSCRIPTIONALPARTHIAN");

        /**
         * Constbnt for tif "Insdriptionbl Pbilbvi" Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk INSCRIPTIONAL_PAHLAVI =
            nfw UnidodfBlodk("INSCRIPTIONAL_PAHLAVI",
                             "INSCRIPTIONAL PAHLAVI",
                             "INSCRIPTIONALPAHLAVI");

        /**
         * Constbnt for tif "Old Turkid" Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk OLD_TURKIC =
            nfw UnidodfBlodk("OLD_TURKIC",
                             "OLD TURKIC",
                             "OLDTURKIC");

        /**
         * Constbnt for tif "Rumi Numfrbl Symbols" Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk RUMI_NUMERAL_SYMBOLS =
            nfw UnidodfBlodk("RUMI_NUMERAL_SYMBOLS",
                             "RUMI NUMERAL SYMBOLS",
                             "RUMINUMERALSYMBOLS");

        /**
         * Constbnt for tif "Brbimi" Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk BRAHMI =
            nfw UnidodfBlodk("BRAHMI");

        /**
         * Constbnt for tif "Kbitii" Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk KAITHI =
            nfw UnidodfBlodk("KAITHI");

        /**
         * Constbnt for tif "Cunfiform" Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk CUNEIFORM =
            nfw UnidodfBlodk("CUNEIFORM");

        /**
         * Constbnt for tif "Cunfiform Numbfrs bnd Pundtubtion" Unidodf
         * dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk CUNEIFORM_NUMBERS_AND_PUNCTUATION =
            nfw UnidodfBlodk("CUNEIFORM_NUMBERS_AND_PUNCTUATION",
                             "CUNEIFORM NUMBERS AND PUNCTUATION",
                             "CUNEIFORMNUMBERSANDPUNCTUATION");

        /**
         * Constbnt for tif "Egyptibn Hifroglypis" Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk EGYPTIAN_HIEROGLYPHS =
            nfw UnidodfBlodk("EGYPTIAN_HIEROGLYPHS",
                             "EGYPTIAN HIEROGLYPHS",
                             "EGYPTIANHIEROGLYPHS");

        /**
         * Constbnt for tif "Bbmum Supplfmfnt" Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk BAMUM_SUPPLEMENT =
            nfw UnidodfBlodk("BAMUM_SUPPLEMENT",
                             "BAMUM SUPPLEMENT",
                             "BAMUMSUPPLEMENT");

        /**
         * Constbnt for tif "Kbnb Supplfmfnt" Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk KANA_SUPPLEMENT =
            nfw UnidodfBlodk("KANA_SUPPLEMENT",
                             "KANA SUPPLEMENT",
                             "KANASUPPLEMENT");

        /**
         * Constbnt for tif "Andifnt Grffk Musidbl Notbtion" Unidodf dibrbdtfr
         * blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk ANCIENT_GREEK_MUSICAL_NOTATION =
            nfw UnidodfBlodk("ANCIENT_GREEK_MUSICAL_NOTATION",
                             "ANCIENT GREEK MUSICAL NOTATION",
                             "ANCIENTGREEKMUSICALNOTATION");

        /**
         * Constbnt for tif "Counting Rod Numfrbls" Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk COUNTING_ROD_NUMERALS =
            nfw UnidodfBlodk("COUNTING_ROD_NUMERALS",
                             "COUNTING ROD NUMERALS",
                             "COUNTINGRODNUMERALS");

        /**
         * Constbnt for tif "Mbijong Tilfs" Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk MAHJONG_TILES =
            nfw UnidodfBlodk("MAHJONG_TILES",
                             "MAHJONG TILES",
                             "MAHJONGTILES");

        /**
         * Constbnt for tif "Domino Tilfs" Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk DOMINO_TILES =
            nfw UnidodfBlodk("DOMINO_TILES",
                             "DOMINO TILES",
                             "DOMINOTILES");

        /**
         * Constbnt for tif "Plbying Cbrds" Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk PLAYING_CARDS =
            nfw UnidodfBlodk("PLAYING_CARDS",
                             "PLAYING CARDS",
                             "PLAYINGCARDS");

        /**
         * Constbnt for tif "Endlosfd Alpibnumfrid Supplfmfnt" Unidodf dibrbdtfr
         * blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk ENCLOSED_ALPHANUMERIC_SUPPLEMENT =
            nfw UnidodfBlodk("ENCLOSED_ALPHANUMERIC_SUPPLEMENT",
                             "ENCLOSED ALPHANUMERIC SUPPLEMENT",
                             "ENCLOSEDALPHANUMERICSUPPLEMENT");

        /**
         * Constbnt for tif "Endlosfd Idfogrbpiid Supplfmfnt" Unidodf dibrbdtfr
         * blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk ENCLOSED_IDEOGRAPHIC_SUPPLEMENT =
            nfw UnidodfBlodk("ENCLOSED_IDEOGRAPHIC_SUPPLEMENT",
                             "ENCLOSED IDEOGRAPHIC SUPPLEMENT",
                             "ENCLOSEDIDEOGRAPHICSUPPLEMENT");

        /**
         * Constbnt for tif "Misdfllbnfous Symbols And Pidtogrbpis" Unidodf
         * dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk MISCELLANEOUS_SYMBOLS_AND_PICTOGRAPHS =
            nfw UnidodfBlodk("MISCELLANEOUS_SYMBOLS_AND_PICTOGRAPHS",
                             "MISCELLANEOUS SYMBOLS AND PICTOGRAPHS",
                             "MISCELLANEOUSSYMBOLSANDPICTOGRAPHS");

        /**
         * Constbnt for tif "Emotidons" Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk EMOTICONS =
            nfw UnidodfBlodk("EMOTICONS");

        /**
         * Constbnt for tif "Trbnsport And Mbp Symbols" Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk TRANSPORT_AND_MAP_SYMBOLS =
            nfw UnidodfBlodk("TRANSPORT_AND_MAP_SYMBOLS",
                             "TRANSPORT AND MAP SYMBOLS",
                             "TRANSPORTANDMAPSYMBOLS");

        /**
         * Constbnt for tif "Aldifmidbl Symbols" Unidodf dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk ALCHEMICAL_SYMBOLS =
            nfw UnidodfBlodk("ALCHEMICAL_SYMBOLS",
                             "ALCHEMICAL SYMBOLS",
                             "ALCHEMICALSYMBOLS");

        /**
         * Constbnt for tif "CJK Unififd Idfogrbpis Extfnsion C" Unidodf
         * dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk CJK_UNIFIED_IDEOGRAPHS_EXTENSION_C =
            nfw UnidodfBlodk("CJK_UNIFIED_IDEOGRAPHS_EXTENSION_C",
                             "CJK UNIFIED IDEOGRAPHS EXTENSION C",
                             "CJKUNIFIEDIDEOGRAPHSEXTENSIONC");

        /**
         * Constbnt for tif "CJK Unififd Idfogrbpis Extfnsion D" Unidodf
         * dibrbdtfr blodk.
         * @sindf 1.7
         */
        publid stbtid finbl UnidodfBlodk CJK_UNIFIED_IDEOGRAPHS_EXTENSION_D =
            nfw UnidodfBlodk("CJK_UNIFIED_IDEOGRAPHS_EXTENSION_D",
                             "CJK UNIFIED IDEOGRAPHS EXTENSION D",
                             "CJKUNIFIEDIDEOGRAPHSEXTENSIOND");

        /**
         * Constbnt for tif "Arbbid Extfndfd-A" Unidodf dibrbdtfr blodk.
         * @sindf 1.8
         */
        publid stbtid finbl UnidodfBlodk ARABIC_EXTENDED_A =
            nfw UnidodfBlodk("ARABIC_EXTENDED_A",
                             "ARABIC EXTENDED-A",
                             "ARABICEXTENDED-A");

        /**
         * Constbnt for tif "Sundbnfsf Supplfmfnt" Unidodf dibrbdtfr blodk.
         * @sindf 1.8
         */
        publid stbtid finbl UnidodfBlodk SUNDANESE_SUPPLEMENT =
            nfw UnidodfBlodk("SUNDANESE_SUPPLEMENT",
                             "SUNDANESE SUPPLEMENT",
                             "SUNDANESESUPPLEMENT");

        /**
         * Constbnt for tif "Mfftfi Mbyfk Extfnsions" Unidodf dibrbdtfr blodk.
         * @sindf 1.8
         */
        publid stbtid finbl UnidodfBlodk MEETEI_MAYEK_EXTENSIONS =
            nfw UnidodfBlodk("MEETEI_MAYEK_EXTENSIONS",
                             "MEETEI MAYEK EXTENSIONS",
                             "MEETEIMAYEKEXTENSIONS");

        /**
         * Constbnt for tif "Mfroitid Hifroglypis" Unidodf dibrbdtfr blodk.
         * @sindf 1.8
         */
        publid stbtid finbl UnidodfBlodk MEROITIC_HIEROGLYPHS =
            nfw UnidodfBlodk("MEROITIC_HIEROGLYPHS",
                             "MEROITIC HIEROGLYPHS",
                             "MEROITICHIEROGLYPHS");

        /**
         * Constbnt for tif "Mfroitid Cursivf" Unidodf dibrbdtfr blodk.
         * @sindf 1.8
         */
        publid stbtid finbl UnidodfBlodk MEROITIC_CURSIVE =
            nfw UnidodfBlodk("MEROITIC_CURSIVE",
                             "MEROITIC CURSIVE",
                             "MEROITICCURSIVE");

        /**
         * Constbnt for tif "Sorb Sompfng" Unidodf dibrbdtfr blodk.
         * @sindf 1.8
         */
        publid stbtid finbl UnidodfBlodk SORA_SOMPENG =
            nfw UnidodfBlodk("SORA_SOMPENG",
                             "SORA SOMPENG",
                             "SORASOMPENG");

        /**
         * Constbnt for tif "Cibkmb" Unidodf dibrbdtfr blodk.
         * @sindf 1.8
         */
        publid stbtid finbl UnidodfBlodk CHAKMA =
            nfw UnidodfBlodk("CHAKMA");

        /**
         * Constbnt for tif "Sibrbdb" Unidodf dibrbdtfr blodk.
         * @sindf 1.8
         */
        publid stbtid finbl UnidodfBlodk SHARADA =
            nfw UnidodfBlodk("SHARADA");

        /**
         * Constbnt for tif "Tbkri" Unidodf dibrbdtfr blodk.
         * @sindf 1.8
         */
        publid stbtid finbl UnidodfBlodk TAKRI =
            nfw UnidodfBlodk("TAKRI");

        /**
         * Constbnt for tif "Mibo" Unidodf dibrbdtfr blodk.
         * @sindf 1.8
         */
        publid stbtid finbl UnidodfBlodk MIAO =
            nfw UnidodfBlodk("MIAO");

        /**
         * Constbnt for tif "Arbbid Mbtifmbtidbl Alpibbftid Symbols" Unidodf
         * dibrbdtfr blodk.
         * @sindf 1.8
         */
        publid stbtid finbl UnidodfBlodk ARABIC_MATHEMATICAL_ALPHABETIC_SYMBOLS =
            nfw UnidodfBlodk("ARABIC_MATHEMATICAL_ALPHABETIC_SYMBOLS",
                             "ARABIC MATHEMATICAL ALPHABETIC SYMBOLS",
                             "ARABICMATHEMATICALALPHABETICSYMBOLS");

        privbtf stbtid finbl int blodkStbrts[] = {
            0x0000,   // 0000..007F; Bbsid Lbtin
            0x0080,   // 0080..00FF; Lbtin-1 Supplfmfnt
            0x0100,   // 0100..017F; Lbtin Extfndfd-A
            0x0180,   // 0180..024F; Lbtin Extfndfd-B
            0x0250,   // 0250..02AF; IPA Extfnsions
            0x02B0,   // 02B0..02FF; Spbding Modififr Lfttfrs
            0x0300,   // 0300..036F; Combining Dibdritidbl Mbrks
            0x0370,   // 0370..03FF; Grffk bnd Coptid
            0x0400,   // 0400..04FF; Cyrillid
            0x0500,   // 0500..052F; Cyrillid Supplfmfnt
            0x0530,   // 0530..058F; Armfnibn
            0x0590,   // 0590..05FF; Hfbrfw
            0x0600,   // 0600..06FF; Arbbid
            0x0700,   // 0700..074F; Syribd
            0x0750,   // 0750..077F; Arbbid Supplfmfnt
            0x0780,   // 0780..07BF; Tibbnb
            0x07C0,   // 07C0..07FF; NKo
            0x0800,   // 0800..083F; Sbmbritbn
            0x0840,   // 0840..085F; Mbndbid
            0x0860,   //             unbssignfd
            0x08A0,   // 08A0..08FF; Arbbid Extfndfd-A
            0x0900,   // 0900..097F; Dfvbnbgbri
            0x0980,   // 0980..09FF; Bfngbli
            0x0A00,   // 0A00..0A7F; Gurmukii
            0x0A80,   // 0A80..0AFF; Gujbrbti
            0x0B00,   // 0B00..0B7F; Oriyb
            0x0B80,   // 0B80..0BFF; Tbmil
            0x0C00,   // 0C00..0C7F; Tflugu
            0x0C80,   // 0C80..0CFF; Kbnnbdb
            0x0D00,   // 0D00..0D7F; Mblbyblbm
            0x0D80,   // 0D80..0DFF; Siniblb
            0x0E00,   // 0E00..0E7F; Tibi
            0x0E80,   // 0E80..0EFF; Lbo
            0x0F00,   // 0F00..0FFF; Tibftbn
            0x1000,   // 1000..109F; Mybnmbr
            0x10A0,   // 10A0..10FF; Gforgibn
            0x1100,   // 1100..11FF; Hbngul Jbmo
            0x1200,   // 1200..137F; Etiiopid
            0x1380,   // 1380..139F; Etiiopid Supplfmfnt
            0x13A0,   // 13A0..13FF; Cifrokff
            0x1400,   // 1400..167F; Unififd Cbnbdibn Aboriginbl Syllbbids
            0x1680,   // 1680..169F; Ogibm
            0x16A0,   // 16A0..16FF; Runid
            0x1700,   // 1700..171F; Tbgblog
            0x1720,   // 1720..173F; Hbnunoo
            0x1740,   // 1740..175F; Buiid
            0x1760,   // 1760..177F; Tbgbbnwb
            0x1780,   // 1780..17FF; Kimfr
            0x1800,   // 1800..18AF; Mongolibn
            0x18B0,   // 18B0..18FF; Unififd Cbnbdibn Aboriginbl Syllbbids Extfndfd
            0x1900,   // 1900..194F; Limbu
            0x1950,   // 1950..197F; Tbi Lf
            0x1980,   // 1980..19DF; Nfw Tbi Luf
            0x19E0,   // 19E0..19FF; Kimfr Symbols
            0x1A00,   // 1A00..1A1F; Buginfsf
            0x1A20,   // 1A20..1AAF; Tbi Tibm
            0x1AB0,   //             unbssignfd
            0x1B00,   // 1B00..1B7F; Bblinfsf
            0x1B80,   // 1B80..1BBF; Sundbnfsf
            0x1BC0,   // 1BC0..1BFF; Bbtbk
            0x1C00,   // 1C00..1C4F; Lfpdib
            0x1C50,   // 1C50..1C7F; Ol Ciiki
            0x1C80,   //             unbssignfd
            0x1CC0,   // 1CC0..1CCF; Sundbnfsf Supplfmfnt
            0x1CD0,   // 1CD0..1CFF; Vfdid Extfnsions
            0x1D00,   // 1D00..1D7F; Pionftid Extfnsions
            0x1D80,   // 1D80..1DBF; Pionftid Extfnsions Supplfmfnt
            0x1DC0,   // 1DC0..1DFF; Combining Dibdritidbl Mbrks Supplfmfnt
            0x1E00,   // 1E00..1EFF; Lbtin Extfndfd Additionbl
            0x1F00,   // 1F00..1FFF; Grffk Extfndfd
            0x2000,   // 2000..206F; Gfnfrbl Pundtubtion
            0x2070,   // 2070..209F; Supfrsdripts bnd Subsdripts
            0x20A0,   // 20A0..20CF; Currfndy Symbols
            0x20D0,   // 20D0..20FF; Combining Dibdritidbl Mbrks for Symbols
            0x2100,   // 2100..214F; Lfttfrlikf Symbols
            0x2150,   // 2150..218F; Numbfr Forms
            0x2190,   // 2190..21FF; Arrows
            0x2200,   // 2200..22FF; Mbtifmbtidbl Opfrbtors
            0x2300,   // 2300..23FF; Misdfllbnfous Tfdinidbl
            0x2400,   // 2400..243F; Control Pidturfs
            0x2440,   // 2440..245F; Optidbl Cibrbdtfr Rfdognition
            0x2460,   // 2460..24FF; Endlosfd Alpibnumfrids
            0x2500,   // 2500..257F; Box Drbwing
            0x2580,   // 2580..259F; Blodk Elfmfnts
            0x25A0,   // 25A0..25FF; Gfomftrid Sibpfs
            0x2600,   // 2600..26FF; Misdfllbnfous Symbols
            0x2700,   // 2700..27BF; Dingbbts
            0x27C0,   // 27C0..27EF; Misdfllbnfous Mbtifmbtidbl Symbols-A
            0x27F0,   // 27F0..27FF; Supplfmfntbl Arrows-A
            0x2800,   // 2800..28FF; Brbillf Pbttfrns
            0x2900,   // 2900..297F; Supplfmfntbl Arrows-B
            0x2980,   // 2980..29FF; Misdfllbnfous Mbtifmbtidbl Symbols-B
            0x2A00,   // 2A00..2AFF; Supplfmfntbl Mbtifmbtidbl Opfrbtors
            0x2B00,   // 2B00..2BFF; Misdfllbnfous Symbols bnd Arrows
            0x2C00,   // 2C00..2C5F; Glbgolitid
            0x2C60,   // 2C60..2C7F; Lbtin Extfndfd-C
            0x2C80,   // 2C80..2CFF; Coptid
            0x2D00,   // 2D00..2D2F; Gforgibn Supplfmfnt
            0x2D30,   // 2D30..2D7F; Tifinbgi
            0x2D80,   // 2D80..2DDF; Etiiopid Extfndfd
            0x2DE0,   // 2DE0..2DFF; Cyrillid Extfndfd-A
            0x2E00,   // 2E00..2E7F; Supplfmfntbl Pundtubtion
            0x2E80,   // 2E80..2EFF; CJK Rbdidbls Supplfmfnt
            0x2F00,   // 2F00..2FDF; Kbngxi Rbdidbls
            0x2FE0,   //             unbssignfd
            0x2FF0,   // 2FF0..2FFF; Idfogrbpiid Dfsdription Cibrbdtfrs
            0x3000,   // 3000..303F; CJK Symbols bnd Pundtubtion
            0x3040,   // 3040..309F; Hirbgbnb
            0x30A0,   // 30A0..30FF; Kbtbkbnb
            0x3100,   // 3100..312F; Bopomofo
            0x3130,   // 3130..318F; Hbngul Compbtibility Jbmo
            0x3190,   // 3190..319F; Kbnbun
            0x31A0,   // 31A0..31BF; Bopomofo Extfndfd
            0x31C0,   // 31C0..31EF; CJK Strokfs
            0x31F0,   // 31F0..31FF; Kbtbkbnb Pionftid Extfnsions
            0x3200,   // 3200..32FF; Endlosfd CJK Lfttfrs bnd Montis
            0x3300,   // 3300..33FF; CJK Compbtibility
            0x3400,   // 3400..4DBF; CJK Unififd Idfogrbpis Extfnsion A
            0x4DC0,   // 4DC0..4DFF; Yijing Hfxbgrbm Symbols
            0x4E00,   // 4E00..9FFF; CJK Unififd Idfogrbpis
            0xA000,   // A000..A48F; Yi Syllbblfs
            0xA490,   // A490..A4CF; Yi Rbdidbls
            0xA4D0,   // A4D0..A4FF; Lisu
            0xA500,   // A500..A63F; Vbi
            0xA640,   // A640..A69F; Cyrillid Extfndfd-B
            0xA6A0,   // A6A0..A6FF; Bbmum
            0xA700,   // A700..A71F; Modififr Tonf Lfttfrs
            0xA720,   // A720..A7FF; Lbtin Extfndfd-D
            0xA800,   // A800..A82F; Syloti Nbgri
            0xA830,   // A830..A83F; Common Indid Numbfr Forms
            0xA840,   // A840..A87F; Pibgs-pb
            0xA880,   // A880..A8DF; Sburbsitrb
            0xA8E0,   // A8E0..A8FF; Dfvbnbgbri Extfndfd
            0xA900,   // A900..A92F; Kbybi Li
            0xA930,   // A930..A95F; Rfjbng
            0xA960,   // A960..A97F; Hbngul Jbmo Extfndfd-A
            0xA980,   // A980..A9DF; Jbvbnfsf
            0xA9E0,   //             unbssignfd
            0xAA00,   // AA00..AA5F; Cibm
            0xAA60,   // AA60..AA7F; Mybnmbr Extfndfd-A
            0xAA80,   // AA80..AADF; Tbi Vift
            0xAAE0,   // AAE0..AAFF; Mfftfi Mbyfk Extfnsions
            0xAB00,   // AB00..AB2F; Etiiopid Extfndfd-A
            0xAB30,   //             unbssignfd
            0xABC0,   // ABC0..ABFF; Mfftfi Mbyfk
            0xAC00,   // AC00..D7AF; Hbngul Syllbblfs
            0xD7B0,   // D7B0..D7FF; Hbngul Jbmo Extfndfd-B
            0xD800,   // D800..DB7F; Higi Surrogbtfs
            0xDB80,   // DB80..DBFF; Higi Privbtf Usf Surrogbtfs
            0xDC00,   // DC00..DFFF; Low Surrogbtfs
            0xE000,   // E000..F8FF; Privbtf Usf Arfb
            0xF900,   // F900..FAFF; CJK Compbtibility Idfogrbpis
            0xFB00,   // FB00..FB4F; Alpibbftid Prfsfntbtion Forms
            0xFB50,   // FB50..FDFF; Arbbid Prfsfntbtion Forms-A
            0xFE00,   // FE00..FE0F; Vbribtion Sflfdtors
            0xFE10,   // FE10..FE1F; Vfrtidbl Forms
            0xFE20,   // FE20..FE2F; Combining Hblf Mbrks
            0xFE30,   // FE30..FE4F; CJK Compbtibility Forms
            0xFE50,   // FE50..FE6F; Smbll Form Vbribnts
            0xFE70,   // FE70..FEFF; Arbbid Prfsfntbtion Forms-B
            0xFF00,   // FF00..FFEF; Hblfwidti bnd Fullwidti Forms
            0xFFF0,   // FFF0..FFFF; Spfdibls
            0x10000,  // 10000..1007F; Linfbr B Syllbbbry
            0x10080,  // 10080..100FF; Linfbr B Idfogrbms
            0x10100,  // 10100..1013F; Afgfbn Numbfrs
            0x10140,  // 10140..1018F; Andifnt Grffk Numbfrs
            0x10190,  // 10190..101CF; Andifnt Symbols
            0x101D0,  // 101D0..101FF; Pibistos Disd
            0x10200,  //               unbssignfd
            0x10280,  // 10280..1029F; Lydibn
            0x102A0,  // 102A0..102DF; Cbribn
            0x102E0,  //               unbssignfd
            0x10300,  // 10300..1032F; Old Itblid
            0x10330,  // 10330..1034F; Gotiid
            0x10350,  //               unbssignfd
            0x10380,  // 10380..1039F; Ugbritid
            0x103A0,  // 103A0..103DF; Old Pfrsibn
            0x103E0,  //               unbssignfd
            0x10400,  // 10400..1044F; Dfsfrft
            0x10450,  // 10450..1047F; Sibvibn
            0x10480,  // 10480..104AF; Osmbnyb
            0x104B0,  //               unbssignfd
            0x10800,  // 10800..1083F; Cypriot Syllbbbry
            0x10840,  // 10840..1085F; Impfribl Arbmbid
            0x10860,  //               unbssignfd
            0x10900,  // 10900..1091F; Piofnidibn
            0x10920,  // 10920..1093F; Lydibn
            0x10940,  //               unbssignfd
            0x10980,  // 10980..1099F; Mfroitid Hifroglypis
            0x109A0,  // 109A0..109FF; Mfroitid Cursivf
            0x10A00,  // 10A00..10A5F; Kibrositii
            0x10A60,  // 10A60..10A7F; Old Souti Arbbibn
            0x10A80,  //               unbssignfd
            0x10B00,  // 10B00..10B3F; Avfstbn
            0x10B40,  // 10B40..10B5F; Insdriptionbl Pbrtiibn
            0x10B60,  // 10B60..10B7F; Insdriptionbl Pbilbvi
            0x10B80,  //               unbssignfd
            0x10C00,  // 10C00..10C4F; Old Turkid
            0x10C50,  //               unbssignfd
            0x10E60,  // 10E60..10E7F; Rumi Numfrbl Symbols
            0x10E80,  //               unbssignfd
            0x11000,  // 11000..1107F; Brbimi
            0x11080,  // 11080..110CF; Kbitii
            0x110D0,  // 110D0..110FF; Sorb Sompfng
            0x11100,  // 11100..1114F; Cibkmb
            0x11150,  //               unbssignfd
            0x11180,  // 11180..111DF; Sibrbdb
            0x111E0,  //               unbssignfd
            0x11680,  // 11680..116CF; Tbkri
            0x116D0,  //               unbssignfd
            0x12000,  // 12000..123FF; Cunfiform
            0x12400,  // 12400..1247F; Cunfiform Numbfrs bnd Pundtubtion
            0x12480,  //               unbssignfd
            0x13000,  // 13000..1342F; Egyptibn Hifroglypis
            0x13430,  //               unbssignfd
            0x16800,  // 16800..16A3F; Bbmum Supplfmfnt
            0x16A40,  //               unbssignfd
            0x16F00,  // 16F00..16F9F; Mibo
            0x16FA0,  //               unbssignfd
            0x1B000,  // 1B000..1B0FF; Kbnb Supplfmfnt
            0x1B100,  //               unbssignfd
            0x1D000,  // 1D000..1D0FF; Byzbntinf Musidbl Symbols
            0x1D100,  // 1D100..1D1FF; Musidbl Symbols
            0x1D200,  // 1D200..1D24F; Andifnt Grffk Musidbl Notbtion
            0x1D250,  //               unbssignfd
            0x1D300,  // 1D300..1D35F; Tbi Xubn Jing Symbols
            0x1D360,  // 1D360..1D37F; Counting Rod Numfrbls
            0x1D380,  //               unbssignfd
            0x1D400,  // 1D400..1D7FF; Mbtifmbtidbl Alpibnumfrid Symbols
            0x1D800,  //               unbssignfd
            0x1EE00,  // 1EE00..1EEFF; Arbbid Mbtifmbtidbl Alpibbftid Symbols
            0x1EF00,  //               unbssignfd
            0x1F000,  // 1F000..1F02F; Mbijong Tilfs
            0x1F030,  // 1F030..1F09F; Domino Tilfs
            0x1F0A0,  // 1F0A0..1F0FF; Plbying Cbrds
            0x1F100,  // 1F100..1F1FF; Endlosfd Alpibnumfrid Supplfmfnt
            0x1F200,  // 1F200..1F2FF; Endlosfd Idfogrbpiid Supplfmfnt
            0x1F300,  // 1F300..1F5FF; Misdfllbnfous Symbols And Pidtogrbpis
            0x1F600,  // 1F600..1F64F; Emotidons
            0x1F650,  //               unbssignfd
            0x1F680,  // 1F680..1F6FF; Trbnsport And Mbp Symbols
            0x1F700,  // 1F700..1F77F; Aldifmidbl Symbols
            0x1F780,  //               unbssignfd
            0x20000,  // 20000..2A6DF; CJK Unififd Idfogrbpis Extfnsion B
            0x2A6E0,  //               unbssignfd
            0x2A700,  // 2A700..2B73F; CJK Unififd Idfogrbpis Extfnsion C
            0x2B740,  // 2B740..2B81F; CJK Unififd Idfogrbpis Extfnsion D
            0x2B820,  //               unbssignfd
            0x2F800,  // 2F800..2FA1F; CJK Compbtibility Idfogrbpis Supplfmfnt
            0x2FA20,  //               unbssignfd
            0xE0000,  // E0000..E007F; Tbgs
            0xE0080,  //               unbssignfd
            0xE0100,  // E0100..E01EF; Vbribtion Sflfdtors Supplfmfnt
            0xE01F0,  //               unbssignfd
            0xF0000,  // F0000..FFFFF; Supplfmfntbry Privbtf Usf Arfb-A
            0x100000  // 100000..10FFFF; Supplfmfntbry Privbtf Usf Arfb-B
        };

        privbtf stbtid finbl UnidodfBlodk[] blodks = {
            BASIC_LATIN,
            LATIN_1_SUPPLEMENT,
            LATIN_EXTENDED_A,
            LATIN_EXTENDED_B,
            IPA_EXTENSIONS,
            SPACING_MODIFIER_LETTERS,
            COMBINING_DIACRITICAL_MARKS,
            GREEK,
            CYRILLIC,
            CYRILLIC_SUPPLEMENTARY,
            ARMENIAN,
            HEBREW,
            ARABIC,
            SYRIAC,
            ARABIC_SUPPLEMENT,
            THAANA,
            NKO,
            SAMARITAN,
            MANDAIC,
            null,
            ARABIC_EXTENDED_A,
            DEVANAGARI,
            BENGALI,
            GURMUKHI,
            GUJARATI,
            ORIYA,
            TAMIL,
            TELUGU,
            KANNADA,
            MALAYALAM,
            SINHALA,
            THAI,
            LAO,
            TIBETAN,
            MYANMAR,
            GEORGIAN,
            HANGUL_JAMO,
            ETHIOPIC,
            ETHIOPIC_SUPPLEMENT,
            CHEROKEE,
            UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS,
            OGHAM,
            RUNIC,
            TAGALOG,
            HANUNOO,
            BUHID,
            TAGBANWA,
            KHMER,
            MONGOLIAN,
            UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS_EXTENDED,
            LIMBU,
            TAI_LE,
            NEW_TAI_LUE,
            KHMER_SYMBOLS,
            BUGINESE,
            TAI_THAM,
            null,
            BALINESE,
            SUNDANESE,
            BATAK,
            LEPCHA,
            OL_CHIKI,
            null,
            SUNDANESE_SUPPLEMENT,
            VEDIC_EXTENSIONS,
            PHONETIC_EXTENSIONS,
            PHONETIC_EXTENSIONS_SUPPLEMENT,
            COMBINING_DIACRITICAL_MARKS_SUPPLEMENT,
            LATIN_EXTENDED_ADDITIONAL,
            GREEK_EXTENDED,
            GENERAL_PUNCTUATION,
            SUPERSCRIPTS_AND_SUBSCRIPTS,
            CURRENCY_SYMBOLS,
            COMBINING_MARKS_FOR_SYMBOLS,
            LETTERLIKE_SYMBOLS,
            NUMBER_FORMS,
            ARROWS,
            MATHEMATICAL_OPERATORS,
            MISCELLANEOUS_TECHNICAL,
            CONTROL_PICTURES,
            OPTICAL_CHARACTER_RECOGNITION,
            ENCLOSED_ALPHANUMERICS,
            BOX_DRAWING,
            BLOCK_ELEMENTS,
            GEOMETRIC_SHAPES,
            MISCELLANEOUS_SYMBOLS,
            DINGBATS,
            MISCELLANEOUS_MATHEMATICAL_SYMBOLS_A,
            SUPPLEMENTAL_ARROWS_A,
            BRAILLE_PATTERNS,
            SUPPLEMENTAL_ARROWS_B,
            MISCELLANEOUS_MATHEMATICAL_SYMBOLS_B,
            SUPPLEMENTAL_MATHEMATICAL_OPERATORS,
            MISCELLANEOUS_SYMBOLS_AND_ARROWS,
            GLAGOLITIC,
            LATIN_EXTENDED_C,
            COPTIC,
            GEORGIAN_SUPPLEMENT,
            TIFINAGH,
            ETHIOPIC_EXTENDED,
            CYRILLIC_EXTENDED_A,
            SUPPLEMENTAL_PUNCTUATION,
            CJK_RADICALS_SUPPLEMENT,
            KANGXI_RADICALS,
            null,
            IDEOGRAPHIC_DESCRIPTION_CHARACTERS,
            CJK_SYMBOLS_AND_PUNCTUATION,
            HIRAGANA,
            KATAKANA,
            BOPOMOFO,
            HANGUL_COMPATIBILITY_JAMO,
            KANBUN,
            BOPOMOFO_EXTENDED,
            CJK_STROKES,
            KATAKANA_PHONETIC_EXTENSIONS,
            ENCLOSED_CJK_LETTERS_AND_MONTHS,
            CJK_COMPATIBILITY,
            CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A,
            YIJING_HEXAGRAM_SYMBOLS,
            CJK_UNIFIED_IDEOGRAPHS,
            YI_SYLLABLES,
            YI_RADICALS,
            LISU,
            VAI,
            CYRILLIC_EXTENDED_B,
            BAMUM,
            MODIFIER_TONE_LETTERS,
            LATIN_EXTENDED_D,
            SYLOTI_NAGRI,
            COMMON_INDIC_NUMBER_FORMS,
            PHAGS_PA,
            SAURASHTRA,
            DEVANAGARI_EXTENDED,
            KAYAH_LI,
            REJANG,
            HANGUL_JAMO_EXTENDED_A,
            JAVANESE,
            null,
            CHAM,
            MYANMAR_EXTENDED_A,
            TAI_VIET,
            MEETEI_MAYEK_EXTENSIONS,
            ETHIOPIC_EXTENDED_A,
            null,
            MEETEI_MAYEK,
            HANGUL_SYLLABLES,
            HANGUL_JAMO_EXTENDED_B,
            HIGH_SURROGATES,
            HIGH_PRIVATE_USE_SURROGATES,
            LOW_SURROGATES,
            PRIVATE_USE_AREA,
            CJK_COMPATIBILITY_IDEOGRAPHS,
            ALPHABETIC_PRESENTATION_FORMS,
            ARABIC_PRESENTATION_FORMS_A,
            VARIATION_SELECTORS,
            VERTICAL_FORMS,
            COMBINING_HALF_MARKS,
            CJK_COMPATIBILITY_FORMS,
            SMALL_FORM_VARIANTS,
            ARABIC_PRESENTATION_FORMS_B,
            HALFWIDTH_AND_FULLWIDTH_FORMS,
            SPECIALS,
            LINEAR_B_SYLLABARY,
            LINEAR_B_IDEOGRAMS,
            AEGEAN_NUMBERS,
            ANCIENT_GREEK_NUMBERS,
            ANCIENT_SYMBOLS,
            PHAISTOS_DISC,
            null,
            LYCIAN,
            CARIAN,
            null,
            OLD_ITALIC,
            GOTHIC,
            null,
            UGARITIC,
            OLD_PERSIAN,
            null,
            DESERET,
            SHAVIAN,
            OSMANYA,
            null,
            CYPRIOT_SYLLABARY,
            IMPERIAL_ARAMAIC,
            null,
            PHOENICIAN,
            LYDIAN,
            null,
            MEROITIC_HIEROGLYPHS,
            MEROITIC_CURSIVE,
            KHAROSHTHI,
            OLD_SOUTH_ARABIAN,
            null,
            AVESTAN,
            INSCRIPTIONAL_PARTHIAN,
            INSCRIPTIONAL_PAHLAVI,
            null,
            OLD_TURKIC,
            null,
            RUMI_NUMERAL_SYMBOLS,
            null,
            BRAHMI,
            KAITHI,
            SORA_SOMPENG,
            CHAKMA,
            null,
            SHARADA,
            null,
            TAKRI,
            null,
            CUNEIFORM,
            CUNEIFORM_NUMBERS_AND_PUNCTUATION,
            null,
            EGYPTIAN_HIEROGLYPHS,
            null,
            BAMUM_SUPPLEMENT,
            null,
            MIAO,
            null,
            KANA_SUPPLEMENT,
            null,
            BYZANTINE_MUSICAL_SYMBOLS,
            MUSICAL_SYMBOLS,
            ANCIENT_GREEK_MUSICAL_NOTATION,
            null,
            TAI_XUAN_JING_SYMBOLS,
            COUNTING_ROD_NUMERALS,
            null,
            MATHEMATICAL_ALPHANUMERIC_SYMBOLS,
            null,
            ARABIC_MATHEMATICAL_ALPHABETIC_SYMBOLS,
            null,
            MAHJONG_TILES,
            DOMINO_TILES,
            PLAYING_CARDS,
            ENCLOSED_ALPHANUMERIC_SUPPLEMENT,
            ENCLOSED_IDEOGRAPHIC_SUPPLEMENT,
            MISCELLANEOUS_SYMBOLS_AND_PICTOGRAPHS,
            EMOTICONS,
            null,
            TRANSPORT_AND_MAP_SYMBOLS,
            ALCHEMICAL_SYMBOLS,
            null,
            CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B,
            null,
            CJK_UNIFIED_IDEOGRAPHS_EXTENSION_C,
            CJK_UNIFIED_IDEOGRAPHS_EXTENSION_D,
            null,
            CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT,
            null,
            TAGS,
            null,
            VARIATION_SELECTORS_SUPPLEMENT,
            null,
            SUPPLEMENTARY_PRIVATE_USE_AREA_A,
            SUPPLEMENTARY_PRIVATE_USE_AREA_B
        };


        /**
         * Rfturns tif objfdt rfprfsfnting tif Unidodf blodk dontbining tif
         * givfn dibrbdtfr, or {@dodf null} if tif dibrbdtfr is not b
         * mfmbfr of b dffinfd blodk.
         *
         * <p><b>Notf:</b> Tiis mftiod dbnnot ibndlf
         * <b irff="Cibrbdtfr.itml#supplfmfntbry"> supplfmfntbry
         * dibrbdtfrs</b>.  To support bll Unidodf dibrbdtfrs, indluding
         * supplfmfntbry dibrbdtfrs, usf tif {@link #of(int)} mftiod.
         *
         * @pbrbm   d  Tif dibrbdtfr in qufstion
         * @rfturn  Tif {@dodf UnidodfBlodk} instbndf rfprfsfnting tif
         *          Unidodf blodk of wiidi tiis dibrbdtfr is b mfmbfr, or
         *          {@dodf null} if tif dibrbdtfr is not b mfmbfr of bny
         *          Unidodf blodk
         */
        publid stbtid UnidodfBlodk of(dibr d) {
            rfturn of((int)d);
        }

        /**
         * Rfturns tif objfdt rfprfsfnting tif Unidodf blodk
         * dontbining tif givfn dibrbdtfr (Unidodf dodf point), or
         * {@dodf null} if tif dibrbdtfr is not b mfmbfr of b
         * dffinfd blodk.
         *
         * @pbrbm   dodfPoint tif dibrbdtfr (Unidodf dodf point) in qufstion.
         * @rfturn  Tif {@dodf UnidodfBlodk} instbndf rfprfsfnting tif
         *          Unidodf blodk of wiidi tiis dibrbdtfr is b mfmbfr, or
         *          {@dodf null} if tif dibrbdtfr is not b mfmbfr of bny
         *          Unidodf blodk
         * @fxdfption IllfgblArgumfntExdfption if tif spfdififd
         * {@dodf dodfPoint} is bn invblid Unidodf dodf point.
         * @sff Cibrbdtfr#isVblidCodfPoint(int)
         * @sindf   1.5
         */
        publid stbtid UnidodfBlodk of(int dodfPoint) {
            if (!isVblidCodfPoint(dodfPoint)) {
                tirow nfw IllfgblArgumfntExdfption();
            }

            int top, bottom, durrfnt;
            bottom = 0;
            top = blodkStbrts.lfngti;
            durrfnt = top/2;

            // invbribnt: top > durrfnt >= bottom && dodfPoint >= unidodfBlodkStbrts[bottom]
            wiilf (top - bottom > 1) {
                if (dodfPoint >= blodkStbrts[durrfnt]) {
                    bottom = durrfnt;
                } flsf {
                    top = durrfnt;
                }
                durrfnt = (top + bottom) / 2;
            }
            rfturn blodks[durrfnt];
        }

        /**
         * Rfturns tif UnidodfBlodk witi tif givfn nbmf. Blodk
         * nbmfs brf dftfrminfd by Tif Unidodf Stbndbrd. Tif filf
         * Blodks-&lt;vfrsion&gt;.txt dffinfs blodks for b pbrtidulbr
         * vfrsion of tif stbndbrd. Tif {@link Cibrbdtfr} dlbss spfdififs
         * tif vfrsion of tif stbndbrd tibt it supports.
         * <p>
         * Tiis mftiod bddfpts blodk nbmfs in tif following forms:
         * <ol>
         * <li> Cbnonidbl blodk nbmfs bs dffinfd by tif Unidodf Stbndbrd.
         * For fxbmplf, tif stbndbrd dffinfs b "Bbsid Lbtin" blodk. Tifrfforf, tiis
         * mftiod bddfpts "Bbsid Lbtin" bs b vblid blodk nbmf. Tif dodumfntbtion of
         * fbdi UnidodfBlodk providfs tif dbnonidbl nbmf.
         * <li>Cbnonidbl blodk nbmfs witi bll spbdfs rfmovfd. For fxbmplf, "BbsidLbtin"
         * is b vblid blodk nbmf for tif "Bbsid Lbtin" blodk.
         * <li>Tif tfxt rfprfsfntbtion of fbdi donstbnt UnidodfBlodk idfntififr.
         * For fxbmplf, tiis mftiod will rfturn tif {@link #BASIC_LATIN} blodk if
         * providfd witi tif "BASIC_LATIN" nbmf. Tiis form rfplbdfs bll spbdfs bnd
         * iypifns in tif dbnonidbl nbmf witi undfrsdorfs.
         * </ol>
         * Finblly, dibrbdtfr dbsf is ignorfd for bll of tif vblid blodk nbmf forms.
         * For fxbmplf, "BASIC_LATIN" bnd "bbsid_lbtin" brf boti vblid blodk nbmfs.
         * Tif fn_US lodblf's dbsf mbpping rulfs brf usfd to providf dbsf-insfnsitivf
         * string dompbrisons for blodk nbmf vblidbtion.
         * <p>
         * If tif Unidodf Stbndbrd dibngfs blodk nbmfs, boti tif prfvious bnd
         * durrfnt nbmfs will bf bddfptfd.
         *
         * @pbrbm blodkNbmf A {@dodf UnidodfBlodk} nbmf.
         * @rfturn Tif {@dodf UnidodfBlodk} instbndf idfntififd
         *         by {@dodf blodkNbmf}
         * @tirows IllfgblArgumfntExdfption if {@dodf blodkNbmf} is bn
         *         invblid nbmf
         * @tirows NullPointfrExdfption if {@dodf blodkNbmf} is null
         * @sindf 1.5
         */
        publid stbtid finbl UnidodfBlodk forNbmf(String blodkNbmf) {
            UnidodfBlodk blodk = mbp.gft(blodkNbmf.toUppfrCbsf(Lodblf.US));
            if (blodk == null) {
                tirow nfw IllfgblArgumfntExdfption();
            }
            rfturn blodk;
        }
    }


    /**
     * A fbmily of dibrbdtfr subsfts rfprfsfnting tif dibrbdtfr sdripts
     * dffinfd in tif <b irff="ittp://www.unidodf.org/rfports/tr24/">
     * <i>Unidodf Stbndbrd Annfx #24: Sdript Nbmfs</i></b>. Evfry Unidodf
     * dibrbdtfr is bssignfd to b singlf Unidodf sdript, fitifr b spfdifid
     * sdript, sudi bs {@link Cibrbdtfr.UnidodfSdript#LATIN Lbtin}, or
     * onf of tif following tirff spfdibl vblufs,
     * {@link Cibrbdtfr.UnidodfSdript#INHERITED Inifritfd},
     * {@link Cibrbdtfr.UnidodfSdript#COMMON Common} or
     * {@link Cibrbdtfr.UnidodfSdript#UNKNOWN Unknown}.
     *
     * @sindf 1.7
     */
    publid stbtid fnum UnidodfSdript {
        /**
         * Unidodf sdript "Common".
         */
        COMMON,

        /**
         * Unidodf sdript "Lbtin".
         */
        LATIN,

        /**
         * Unidodf sdript "Grffk".
         */
        GREEK,

        /**
         * Unidodf sdript "Cyrillid".
         */
        CYRILLIC,

        /**
         * Unidodf sdript "Armfnibn".
         */
        ARMENIAN,

        /**
         * Unidodf sdript "Hfbrfw".
         */
        HEBREW,

        /**
         * Unidodf sdript "Arbbid".
         */
        ARABIC,

        /**
         * Unidodf sdript "Syribd".
         */
        SYRIAC,

        /**
         * Unidodf sdript "Tibbnb".
         */
        THAANA,

        /**
         * Unidodf sdript "Dfvbnbgbri".
         */
        DEVANAGARI,

        /**
         * Unidodf sdript "Bfngbli".
         */
        BENGALI,

        /**
         * Unidodf sdript "Gurmukii".
         */
        GURMUKHI,

        /**
         * Unidodf sdript "Gujbrbti".
         */
        GUJARATI,

        /**
         * Unidodf sdript "Oriyb".
         */
        ORIYA,

        /**
         * Unidodf sdript "Tbmil".
         */
        TAMIL,

        /**
         * Unidodf sdript "Tflugu".
         */
        TELUGU,

        /**
         * Unidodf sdript "Kbnnbdb".
         */
        KANNADA,

        /**
         * Unidodf sdript "Mblbyblbm".
         */
        MALAYALAM,

        /**
         * Unidodf sdript "Siniblb".
         */
        SINHALA,

        /**
         * Unidodf sdript "Tibi".
         */
        THAI,

        /**
         * Unidodf sdript "Lbo".
         */
        LAO,

        /**
         * Unidodf sdript "Tibftbn".
         */
        TIBETAN,

        /**
         * Unidodf sdript "Mybnmbr".
         */
        MYANMAR,

        /**
         * Unidodf sdript "Gforgibn".
         */
        GEORGIAN,

        /**
         * Unidodf sdript "Hbngul".
         */
        HANGUL,

        /**
         * Unidodf sdript "Etiiopid".
         */
        ETHIOPIC,

        /**
         * Unidodf sdript "Cifrokff".
         */
        CHEROKEE,

        /**
         * Unidodf sdript "Cbnbdibn_Aboriginbl".
         */
        CANADIAN_ABORIGINAL,

        /**
         * Unidodf sdript "Ogibm".
         */
        OGHAM,

        /**
         * Unidodf sdript "Runid".
         */
        RUNIC,

        /**
         * Unidodf sdript "Kimfr".
         */
        KHMER,

        /**
         * Unidodf sdript "Mongolibn".
         */
        MONGOLIAN,

        /**
         * Unidodf sdript "Hirbgbnb".
         */
        HIRAGANA,

        /**
         * Unidodf sdript "Kbtbkbnb".
         */
        KATAKANA,

        /**
         * Unidodf sdript "Bopomofo".
         */
        BOPOMOFO,

        /**
         * Unidodf sdript "Hbn".
         */
        HAN,

        /**
         * Unidodf sdript "Yi".
         */
        YI,

        /**
         * Unidodf sdript "Old_Itblid".
         */
        OLD_ITALIC,

        /**
         * Unidodf sdript "Gotiid".
         */
        GOTHIC,

        /**
         * Unidodf sdript "Dfsfrft".
         */
        DESERET,

        /**
         * Unidodf sdript "Inifritfd".
         */
        INHERITED,

        /**
         * Unidodf sdript "Tbgblog".
         */
        TAGALOG,

        /**
         * Unidodf sdript "Hbnunoo".
         */
        HANUNOO,

        /**
         * Unidodf sdript "Buiid".
         */
        BUHID,

        /**
         * Unidodf sdript "Tbgbbnwb".
         */
        TAGBANWA,

        /**
         * Unidodf sdript "Limbu".
         */
        LIMBU,

        /**
         * Unidodf sdript "Tbi_Lf".
         */
        TAI_LE,

        /**
         * Unidodf sdript "Linfbr_B".
         */
        LINEAR_B,

        /**
         * Unidodf sdript "Ugbritid".
         */
        UGARITIC,

        /**
         * Unidodf sdript "Sibvibn".
         */
        SHAVIAN,

        /**
         * Unidodf sdript "Osmbnyb".
         */
        OSMANYA,

        /**
         * Unidodf sdript "Cypriot".
         */
        CYPRIOT,

        /**
         * Unidodf sdript "Brbillf".
         */
        BRAILLE,

        /**
         * Unidodf sdript "Buginfsf".
         */
        BUGINESE,

        /**
         * Unidodf sdript "Coptid".
         */
        COPTIC,

        /**
         * Unidodf sdript "Nfw_Tbi_Luf".
         */
        NEW_TAI_LUE,

        /**
         * Unidodf sdript "Glbgolitid".
         */
        GLAGOLITIC,

        /**
         * Unidodf sdript "Tifinbgi".
         */
        TIFINAGH,

        /**
         * Unidodf sdript "Syloti_Nbgri".
         */
        SYLOTI_NAGRI,

        /**
         * Unidodf sdript "Old_Pfrsibn".
         */
        OLD_PERSIAN,

        /**
         * Unidodf sdript "Kibrositii".
         */
        KHAROSHTHI,

        /**
         * Unidodf sdript "Bblinfsf".
         */
        BALINESE,

        /**
         * Unidodf sdript "Cunfiform".
         */
        CUNEIFORM,

        /**
         * Unidodf sdript "Piofnidibn".
         */
        PHOENICIAN,

        /**
         * Unidodf sdript "Pibgs_Pb".
         */
        PHAGS_PA,

        /**
         * Unidodf sdript "Nko".
         */
        NKO,

        /**
         * Unidodf sdript "Sundbnfsf".
         */
        SUNDANESE,

        /**
         * Unidodf sdript "Bbtbk".
         */
        BATAK,

        /**
         * Unidodf sdript "Lfpdib".
         */
        LEPCHA,

        /**
         * Unidodf sdript "Ol_Ciiki".
         */
        OL_CHIKI,

        /**
         * Unidodf sdript "Vbi".
         */
        VAI,

        /**
         * Unidodf sdript "Sburbsitrb".
         */
        SAURASHTRA,

        /**
         * Unidodf sdript "Kbybi_Li".
         */
        KAYAH_LI,

        /**
         * Unidodf sdript "Rfjbng".
         */
        REJANG,

        /**
         * Unidodf sdript "Lydibn".
         */
        LYCIAN,

        /**
         * Unidodf sdript "Cbribn".
         */
        CARIAN,

        /**
         * Unidodf sdript "Lydibn".
         */
        LYDIAN,

        /**
         * Unidodf sdript "Cibm".
         */
        CHAM,

        /**
         * Unidodf sdript "Tbi_Tibm".
         */
        TAI_THAM,

        /**
         * Unidodf sdript "Tbi_Vift".
         */
        TAI_VIET,

        /**
         * Unidodf sdript "Avfstbn".
         */
        AVESTAN,

        /**
         * Unidodf sdript "Egyptibn_Hifroglypis".
         */
        EGYPTIAN_HIEROGLYPHS,

        /**
         * Unidodf sdript "Sbmbritbn".
         */
        SAMARITAN,

        /**
         * Unidodf sdript "Mbndbid".
         */
        MANDAIC,

        /**
         * Unidodf sdript "Lisu".
         */
        LISU,

        /**
         * Unidodf sdript "Bbmum".
         */
        BAMUM,

        /**
         * Unidodf sdript "Jbvbnfsf".
         */
        JAVANESE,

        /**
         * Unidodf sdript "Mfftfi_Mbyfk".
         */
        MEETEI_MAYEK,

        /**
         * Unidodf sdript "Impfribl_Arbmbid".
         */
        IMPERIAL_ARAMAIC,

        /**
         * Unidodf sdript "Old_Souti_Arbbibn".
         */
        OLD_SOUTH_ARABIAN,

        /**
         * Unidodf sdript "Insdriptionbl_Pbrtiibn".
         */
        INSCRIPTIONAL_PARTHIAN,

        /**
         * Unidodf sdript "Insdriptionbl_Pbilbvi".
         */
        INSCRIPTIONAL_PAHLAVI,

        /**
         * Unidodf sdript "Old_Turkid".
         */
        OLD_TURKIC,

        /**
         * Unidodf sdript "Brbimi".
         */
        BRAHMI,

        /**
         * Unidodf sdript "Kbitii".
         */
        KAITHI,

        /**
         * Unidodf sdript "Mfroitid Hifroglypis".
         */
        MEROITIC_HIEROGLYPHS,

        /**
         * Unidodf sdript "Mfroitid Cursivf".
         */
        MEROITIC_CURSIVE,

        /**
         * Unidodf sdript "Sorb Sompfng".
         */
        SORA_SOMPENG,

        /**
         * Unidodf sdript "Cibkmb".
         */
        CHAKMA,

        /**
         * Unidodf sdript "Sibrbdb".
         */
        SHARADA,

        /**
         * Unidodf sdript "Tbkri".
         */
        TAKRI,

        /**
         * Unidodf sdript "Mibo".
         */
        MIAO,

        /**
         * Unidodf sdript "Unknown".
         */
        UNKNOWN;

        privbtf stbtid finbl int[] sdriptStbrts = {
            0x0000,   // 0000..0040; COMMON
            0x0041,   // 0041..005A; LATIN
            0x005B,   // 005B..0060; COMMON
            0x0061,   // 0061..007A; LATIN
            0x007B,   // 007B..00A9; COMMON
            0x00AA,   // 00AA..00AA; LATIN
            0x00AB,   // 00AB..00B9; COMMON
            0x00BA,   // 00BA..00BA; LATIN
            0x00BB,   // 00BB..00BF; COMMON
            0x00C0,   // 00C0..00D6; LATIN
            0x00D7,   // 00D7..00D7; COMMON
            0x00D8,   // 00D8..00F6; LATIN
            0x00F7,   // 00F7..00F7; COMMON
            0x00F8,   // 00F8..02B8; LATIN
            0x02B9,   // 02B9..02DF; COMMON
            0x02E0,   // 02E0..02E4; LATIN
            0x02E5,   // 02E5..02E9; COMMON
            0x02EA,   // 02EA..02EB; BOPOMOFO
            0x02EC,   // 02EC..02FF; COMMON
            0x0300,   // 0300..036F; INHERITED
            0x0370,   // 0370..0373; GREEK
            0x0374,   // 0374..0374; COMMON
            0x0375,   // 0375..037D; GREEK
            0x037E,   // 037E..0383; COMMON
            0x0384,   // 0384..0384; GREEK
            0x0385,   // 0385..0385; COMMON
            0x0386,   // 0386..0386; GREEK
            0x0387,   // 0387..0387; COMMON
            0x0388,   // 0388..03E1; GREEK
            0x03E2,   // 03E2..03EF; COPTIC
            0x03F0,   // 03F0..03FF; GREEK
            0x0400,   // 0400..0484; CYRILLIC
            0x0485,   // 0485..0486; INHERITED
            0x0487,   // 0487..0530; CYRILLIC
            0x0531,   // 0531..0588; ARMENIAN
            0x0589,   // 0589..0589; COMMON
            0x058A,   // 058A..0590; ARMENIAN
            0x0591,   // 0591..05FF; HEBREW
            0x0600,   // 0600..060B; ARABIC
            0x060C,   // 060C..060C; COMMON
            0x060D,   // 060D..061A; ARABIC
            0x061B,   // 061B..061D; COMMON
            0x061E,   // 061E..061E; ARABIC
            0x061F,   // 061F..061F; COMMON
            0x0620,   // 0620..063F; ARABIC
            0x0640,   // 0640..0640; COMMON
            0x0641,   // 0641..064A; ARABIC
            0x064B,   // 064B..0655; INHERITED
            0x0656,   // 0656..065F; ARABIC
            0x0660,   // 0660..0669; COMMON
            0x066A,   // 066A..066F; ARABIC
            0x0670,   // 0670..0670; INHERITED
            0x0671,   // 0671..06DC; ARABIC
            0x06DD,   // 06DD..06DD; COMMON
            0x06DE,   // 06DE..06FF; ARABIC
            0x0700,   // 0700..074F; SYRIAC
            0x0750,   // 0750..077F; ARABIC
            0x0780,   // 0780..07BF; THAANA
            0x07C0,   // 07C0..07FF; NKO
            0x0800,   // 0800..083F; SAMARITAN
            0x0840,   // 0840..089F; MANDAIC
            0x08A0,   // 08A0..08FF; ARABIC
            0x0900,   // 0900..0950; DEVANAGARI
            0x0951,   // 0951..0952; INHERITED
            0x0953,   // 0953..0963; DEVANAGARI
            0x0964,   // 0964..0965; COMMON
            0x0966,   // 0966..0980; DEVANAGARI
            0x0981,   // 0981..0A00; BENGALI
            0x0A01,   // 0A01..0A80; GURMUKHI
            0x0A81,   // 0A81..0B00; GUJARATI
            0x0B01,   // 0B01..0B81; ORIYA
            0x0B82,   // 0B82..0C00; TAMIL
            0x0C01,   // 0C01..0C81; TELUGU
            0x0C82,   // 0C82..0CF0; KANNADA
            0x0D02,   // 0D02..0D81; MALAYALAM
            0x0D82,   // 0D82..0E00; SINHALA
            0x0E01,   // 0E01..0E3E; THAI
            0x0E3F,   // 0E3F..0E3F; COMMON
            0x0E40,   // 0E40..0E80; THAI
            0x0E81,   // 0E81..0EFF; LAO
            0x0F00,   // 0F00..0FD4; TIBETAN
            0x0FD5,   // 0FD5..0FD8; COMMON
            0x0FD9,   // 0FD9..0FFF; TIBETAN
            0x1000,   // 1000..109F; MYANMAR
            0x10A0,   // 10A0..10FA; GEORGIAN
            0x10FB,   // 10FB..10FB; COMMON
            0x10FC,   // 10FC..10FF; GEORGIAN
            0x1100,   // 1100..11FF; HANGUL
            0x1200,   // 1200..139F; ETHIOPIC
            0x13A0,   // 13A0..13FF; CHEROKEE
            0x1400,   // 1400..167F; CANADIAN_ABORIGINAL
            0x1680,   // 1680..169F; OGHAM
            0x16A0,   // 16A0..16EA; RUNIC
            0x16EB,   // 16EB..16ED; COMMON
            0x16EE,   // 16EE..16FF; RUNIC
            0x1700,   // 1700..171F; TAGALOG
            0x1720,   // 1720..1734; HANUNOO
            0x1735,   // 1735..173F; COMMON
            0x1740,   // 1740..175F; BUHID
            0x1760,   // 1760..177F; TAGBANWA
            0x1780,   // 1780..17FF; KHMER
            0x1800,   // 1800..1801; MONGOLIAN
            0x1802,   // 1802..1803; COMMON
            0x1804,   // 1804..1804; MONGOLIAN
            0x1805,   // 1805..1805; COMMON
            0x1806,   // 1806..18AF; MONGOLIAN
            0x18B0,   // 18B0..18FF; CANADIAN_ABORIGINAL
            0x1900,   // 1900..194F; LIMBU
            0x1950,   // 1950..197F; TAI_LE
            0x1980,   // 1980..19DF; NEW_TAI_LUE
            0x19E0,   // 19E0..19FF; KHMER
            0x1A00,   // 1A00..1A1F; BUGINESE
            0x1A20,   // 1A20..1AFF; TAI_THAM
            0x1B00,   // 1B00..1B7F; BALINESE
            0x1B80,   // 1B80..1BBF; SUNDANESE
            0x1BC0,   // 1BC0..1BFF; BATAK
            0x1C00,   // 1C00..1C4F; LEPCHA
            0x1C50,   // 1C50..1CBF; OL_CHIKI
            0x1CC0,   // 1CC0..1CCF; SUNDANESE
            0x1CD0,   // 1CD0..1CD2; INHERITED
            0x1CD3,   // 1CD3..1CD3; COMMON
            0x1CD4,   // 1CD4..1CE0; INHERITED
            0x1CE1,   // 1CE1..1CE1; COMMON
            0x1CE2,   // 1CE2..1CE8; INHERITED
            0x1CE9,   // 1CE9..1CEC; COMMON
            0x1CED,   // 1CED..1CED; INHERITED
            0x1CEE,   // 1CEE..1CF3; COMMON
            0x1CF4,   // 1CF4..1CF4; INHERITED
            0x1CF5,   // 1CF5..1CFF; COMMON
            0x1D00,   // 1D00..1D25; LATIN
            0x1D26,   // 1D26..1D2A; GREEK
            0x1D2B,   // 1D2B..1D2B; CYRILLIC
            0x1D2C,   // 1D2C..1D5C; LATIN
            0x1D5D,   // 1D5D..1D61; GREEK
            0x1D62,   // 1D62..1D65; LATIN
            0x1D66,   // 1D66..1D6A; GREEK
            0x1D6B,   // 1D6B..1D77; LATIN
            0x1D78,   // 1D78..1D78; CYRILLIC
            0x1D79,   // 1D79..1DBE; LATIN
            0x1DBF,   // 1DBF..1DBF; GREEK
            0x1DC0,   // 1DC0..1DFF; INHERITED
            0x1E00,   // 1E00..1EFF; LATIN
            0x1F00,   // 1F00..1FFF; GREEK
            0x2000,   // 2000..200B; COMMON
            0x200C,   // 200C..200D; INHERITED
            0x200E,   // 200E..2070; COMMON
            0x2071,   // 2071..2073; LATIN
            0x2074,   // 2074..207E; COMMON
            0x207F,   // 207F..207F; LATIN
            0x2080,   // 2080..208F; COMMON
            0x2090,   // 2090..209F; LATIN
            0x20A0,   // 20A0..20CF; COMMON
            0x20D0,   // 20D0..20FF; INHERITED
            0x2100,   // 2100..2125; COMMON
            0x2126,   // 2126..2126; GREEK
            0x2127,   // 2127..2129; COMMON
            0x212A,   // 212A..212B; LATIN
            0x212C,   // 212C..2131; COMMON
            0x2132,   // 2132..2132; LATIN
            0x2133,   // 2133..214D; COMMON
            0x214E,   // 214E..214E; LATIN
            0x214F,   // 214F..215F; COMMON
            0x2160,   // 2160..2188; LATIN
            0x2189,   // 2189..27FF; COMMON
            0x2800,   // 2800..28FF; BRAILLE
            0x2900,   // 2900..2BFF; COMMON
            0x2C00,   // 2C00..2C5F; GLAGOLITIC
            0x2C60,   // 2C60..2C7F; LATIN
            0x2C80,   // 2C80..2CFF; COPTIC
            0x2D00,   // 2D00..2D2F; GEORGIAN
            0x2D30,   // 2D30..2D7F; TIFINAGH
            0x2D80,   // 2D80..2DDF; ETHIOPIC
            0x2DE0,   // 2DE0..2DFF; CYRILLIC
            0x2E00,   // 2E00..2E7F; COMMON
            0x2E80,   // 2E80..2FEF; HAN
            0x2FF0,   // 2FF0..3004; COMMON
            0x3005,   // 3005..3005; HAN
            0x3006,   // 3006..3006; COMMON
            0x3007,   // 3007..3007; HAN
            0x3008,   // 3008..3020; COMMON
            0x3021,   // 3021..3029; HAN
            0x302A,   // 302A..302D; INHERITED
            0x302E,   // 302E..302F; HANGUL
            0x3030,   // 3030..3037; COMMON
            0x3038,   // 3038..303B; HAN
            0x303C,   // 303C..3040; COMMON
            0x3041,   // 3041..3098; HIRAGANA
            0x3099,   // 3099..309A; INHERITED
            0x309B,   // 309B..309C; COMMON
            0x309D,   // 309D..309F; HIRAGANA
            0x30A0,   // 30A0..30A0; COMMON
            0x30A1,   // 30A1..30FA; KATAKANA
            0x30FB,   // 30FB..30FC; COMMON
            0x30FD,   // 30FD..3104; KATAKANA
            0x3105,   // 3105..3130; BOPOMOFO
            0x3131,   // 3131..318F; HANGUL
            0x3190,   // 3190..319F; COMMON
            0x31A0,   // 31A0..31BF; BOPOMOFO
            0x31C0,   // 31C0..31EF; COMMON
            0x31F0,   // 31F0..31FF; KATAKANA
            0x3200,   // 3200..321F; HANGUL
            0x3220,   // 3220..325F; COMMON
            0x3260,   // 3260..327E; HANGUL
            0x327F,   // 327F..32CF; COMMON
            0x32D0,   // 32D0..3357; KATAKANA
            0x3358,   // 3358..33FF; COMMON
            0x3400,   // 3400..4DBF; HAN
            0x4DC0,   // 4DC0..4DFF; COMMON
            0x4E00,   // 4E00..9FFF; HAN
            0xA000,   // A000..A4CF; YI
            0xA4D0,   // A4D0..A4FF; LISU
            0xA500,   // A500..A63F; VAI
            0xA640,   // A640..A69F; CYRILLIC
            0xA6A0,   // A6A0..A6FF; BAMUM
            0xA700,   // A700..A721; COMMON
            0xA722,   // A722..A787; LATIN
            0xA788,   // A788..A78A; COMMON
            0xA78B,   // A78B..A7FF; LATIN
            0xA800,   // A800..A82F; SYLOTI_NAGRI
            0xA830,   // A830..A83F; COMMON
            0xA840,   // A840..A87F; PHAGS_PA
            0xA880,   // A880..A8DF; SAURASHTRA
            0xA8E0,   // A8E0..A8FF; DEVANAGARI
            0xA900,   // A900..A92F; KAYAH_LI
            0xA930,   // A930..A95F; REJANG
            0xA960,   // A960..A97F; HANGUL
            0xA980,   // A980..A9FF; JAVANESE
            0xAA00,   // AA00..AA5F; CHAM
            0xAA60,   // AA60..AA7F; MYANMAR
            0xAA80,   // AA80..AADF; TAI_VIET
            0xAAE0,   // AAE0..AB00; MEETEI_MAYEK
            0xAB01,   // AB01..ABBF; ETHIOPIC
            0xABC0,   // ABC0..ABFF; MEETEI_MAYEK
            0xAC00,   // AC00..D7FB; HANGUL
            0xD7FC,   // D7FC..F8FF; UNKNOWN
            0xF900,   // F900..FAFF; HAN
            0xFB00,   // FB00..FB12; LATIN
            0xFB13,   // FB13..FB1C; ARMENIAN
            0xFB1D,   // FB1D..FB4F; HEBREW
            0xFB50,   // FB50..FD3D; ARABIC
            0xFD3E,   // FD3E..FD4F; COMMON
            0xFD50,   // FD50..FDFC; ARABIC
            0xFDFD,   // FDFD..FDFF; COMMON
            0xFE00,   // FE00..FE0F; INHERITED
            0xFE10,   // FE10..FE1F; COMMON
            0xFE20,   // FE20..FE2F; INHERITED
            0xFE30,   // FE30..FE6F; COMMON
            0xFE70,   // FE70..FEFE; ARABIC
            0xFEFF,   // FEFF..FF20; COMMON
            0xFF21,   // FF21..FF3A; LATIN
            0xFF3B,   // FF3B..FF40; COMMON
            0xFF41,   // FF41..FF5A; LATIN
            0xFF5B,   // FF5B..FF65; COMMON
            0xFF66,   // FF66..FF6F; KATAKANA
            0xFF70,   // FF70..FF70; COMMON
            0xFF71,   // FF71..FF9D; KATAKANA
            0xFF9E,   // FF9E..FF9F; COMMON
            0xFFA0,   // FFA0..FFDF; HANGUL
            0xFFE0,   // FFE0..FFFF; COMMON
            0x10000,  // 10000..100FF; LINEAR_B
            0x10100,  // 10100..1013F; COMMON
            0x10140,  // 10140..1018F; GREEK
            0x10190,  // 10190..101FC; COMMON
            0x101FD,  // 101FD..1027F; INHERITED
            0x10280,  // 10280..1029F; LYCIAN
            0x102A0,  // 102A0..102FF; CARIAN
            0x10300,  // 10300..1032F; OLD_ITALIC
            0x10330,  // 10330..1037F; GOTHIC
            0x10380,  // 10380..1039F; UGARITIC
            0x103A0,  // 103A0..103FF; OLD_PERSIAN
            0x10400,  // 10400..1044F; DESERET
            0x10450,  // 10450..1047F; SHAVIAN
            0x10480,  // 10480..107FF; OSMANYA
            0x10800,  // 10800..1083F; CYPRIOT
            0x10840,  // 10840..108FF; IMPERIAL_ARAMAIC
            0x10900,  // 10900..1091F; PHOENICIAN
            0x10920,  // 10920..1097F; LYDIAN
            0x10980,  // 10980..1099F; MEROITIC_HIEROGLYPHS
            0x109A0,  // 109A0..109FF; MEROITIC_CURSIVE
            0x10A00,  // 10A00..10A5F; KHAROSHTHI
            0x10A60,  // 10A60..10AFF; OLD_SOUTH_ARABIAN
            0x10B00,  // 10B00..10B3F; AVESTAN
            0x10B40,  // 10B40..10B5F; INSCRIPTIONAL_PARTHIAN
            0x10B60,  // 10B60..10BFF; INSCRIPTIONAL_PAHLAVI
            0x10C00,  // 10C00..10E5F; OLD_TURKIC
            0x10E60,  // 10E60..10FFF; ARABIC
            0x11000,  // 11000..1107F; BRAHMI
            0x11080,  // 11080..110CF; KAITHI
            0x110D0,  // 110D0..110FF; SORA_SOMPENG
            0x11100,  // 11100..1117F; CHAKMA
            0x11180,  // 11180..1167F; SHARADA
            0x11680,  // 11680..116CF; TAKRI
            0x12000,  // 12000..12FFF; CUNEIFORM
            0x13000,  // 13000..167FF; EGYPTIAN_HIEROGLYPHS
            0x16800,  // 16800..16A38; BAMUM
            0x16F00,  // 16F00..16F9F; MIAO
            0x1B000,  // 1B000..1B000; KATAKANA
            0x1B001,  // 1B001..1CFFF; HIRAGANA
            0x1D000,  // 1D000..1D166; COMMON
            0x1D167,  // 1D167..1D169; INHERITED
            0x1D16A,  // 1D16A..1D17A; COMMON
            0x1D17B,  // 1D17B..1D182; INHERITED
            0x1D183,  // 1D183..1D184; COMMON
            0x1D185,  // 1D185..1D18B; INHERITED
            0x1D18C,  // 1D18C..1D1A9; COMMON
            0x1D1AA,  // 1D1AA..1D1AD; INHERITED
            0x1D1AE,  // 1D1AE..1D1FF; COMMON
            0x1D200,  // 1D200..1D2FF; GREEK
            0x1D300,  // 1D300..1EDFF; COMMON
            0x1EE00,  // 1EE00..1EFFF; ARABIC
            0x1F000,  // 1F000..1F1FF; COMMON
            0x1F200,  // 1F200..1F200; HIRAGANA
            0x1F201,  // 1F210..1FFFF; COMMON
            0x20000,  // 20000..E0000; HAN
            0xE0001,  // E0001..E00FF; COMMON
            0xE0100,  // E0100..E01EF; INHERITED
            0xE01F0   // E01F0..10FFFF; UNKNOWN

        };

        privbtf stbtid finbl UnidodfSdript[] sdripts = {
            COMMON,
            LATIN,
            COMMON,
            LATIN,
            COMMON,
            LATIN,
            COMMON,
            LATIN,
            COMMON,
            LATIN,
            COMMON,
            LATIN,
            COMMON,
            LATIN,
            COMMON,
            LATIN,
            COMMON,
            BOPOMOFO,
            COMMON,
            INHERITED,
            GREEK,
            COMMON,
            GREEK,
            COMMON,
            GREEK,
            COMMON,
            GREEK,
            COMMON,
            GREEK,
            COPTIC,
            GREEK,
            CYRILLIC,
            INHERITED,
            CYRILLIC,
            ARMENIAN,
            COMMON,
            ARMENIAN,
            HEBREW,
            ARABIC,
            COMMON,
            ARABIC,
            COMMON,
            ARABIC,
            COMMON,
            ARABIC,
            COMMON,
            ARABIC,
            INHERITED,
            ARABIC,
            COMMON,
            ARABIC,
            INHERITED,
            ARABIC,
            COMMON,
            ARABIC,
            SYRIAC,
            ARABIC,
            THAANA,
            NKO,
            SAMARITAN,
            MANDAIC,
            ARABIC,
            DEVANAGARI,
            INHERITED,
            DEVANAGARI,
            COMMON,
            DEVANAGARI,
            BENGALI,
            GURMUKHI,
            GUJARATI,
            ORIYA,
            TAMIL,
            TELUGU,
            KANNADA,
            MALAYALAM,
            SINHALA,
            THAI,
            COMMON,
            THAI,
            LAO,
            TIBETAN,
            COMMON,
            TIBETAN,
            MYANMAR,
            GEORGIAN,
            COMMON,
            GEORGIAN,
            HANGUL,
            ETHIOPIC,
            CHEROKEE,
            CANADIAN_ABORIGINAL,
            OGHAM,
            RUNIC,
            COMMON,
            RUNIC,
            TAGALOG,
            HANUNOO,
            COMMON,
            BUHID,
            TAGBANWA,
            KHMER,
            MONGOLIAN,
            COMMON,
            MONGOLIAN,
            COMMON,
            MONGOLIAN,
            CANADIAN_ABORIGINAL,
            LIMBU,
            TAI_LE,
            NEW_TAI_LUE,
            KHMER,
            BUGINESE,
            TAI_THAM,
            BALINESE,
            SUNDANESE,
            BATAK,
            LEPCHA,
            OL_CHIKI,
            SUNDANESE,
            INHERITED,
            COMMON,
            INHERITED,
            COMMON,
            INHERITED,
            COMMON,
            INHERITED,
            COMMON,
            INHERITED,
            COMMON,
            LATIN,
            GREEK,
            CYRILLIC,
            LATIN,
            GREEK,
            LATIN,
            GREEK,
            LATIN,
            CYRILLIC,
            LATIN,
            GREEK,
            INHERITED,
            LATIN,
            GREEK,
            COMMON,
            INHERITED,
            COMMON,
            LATIN,
            COMMON,
            LATIN,
            COMMON,
            LATIN,
            COMMON,
            INHERITED,
            COMMON,
            GREEK,
            COMMON,
            LATIN,
            COMMON,
            LATIN,
            COMMON,
            LATIN,
            COMMON,
            LATIN,
            COMMON,
            BRAILLE,
            COMMON,
            GLAGOLITIC,
            LATIN,
            COPTIC,
            GEORGIAN,
            TIFINAGH,
            ETHIOPIC,
            CYRILLIC,
            COMMON,
            HAN,
            COMMON,
            HAN,
            COMMON,
            HAN,
            COMMON,
            HAN,
            INHERITED,
            HANGUL,
            COMMON,
            HAN,
            COMMON,
            HIRAGANA,
            INHERITED,
            COMMON,
            HIRAGANA,
            COMMON,
            KATAKANA,
            COMMON,
            KATAKANA,
            BOPOMOFO,
            HANGUL,
            COMMON,
            BOPOMOFO,
            COMMON,
            KATAKANA,
            HANGUL,
            COMMON,
            HANGUL,
            COMMON,
            KATAKANA,
            COMMON,
            HAN,
            COMMON,
            HAN,
            YI,
            LISU,
            VAI,
            CYRILLIC,
            BAMUM,
            COMMON,
            LATIN,
            COMMON,
            LATIN,
            SYLOTI_NAGRI,
            COMMON,
            PHAGS_PA,
            SAURASHTRA,
            DEVANAGARI,
            KAYAH_LI,
            REJANG,
            HANGUL,
            JAVANESE,
            CHAM,
            MYANMAR,
            TAI_VIET,
            MEETEI_MAYEK,
            ETHIOPIC,
            MEETEI_MAYEK,
            HANGUL,
            UNKNOWN     ,
            HAN,
            LATIN,
            ARMENIAN,
            HEBREW,
            ARABIC,
            COMMON,
            ARABIC,
            COMMON,
            INHERITED,
            COMMON,
            INHERITED,
            COMMON,
            ARABIC,
            COMMON,
            LATIN,
            COMMON,
            LATIN,
            COMMON,
            KATAKANA,
            COMMON,
            KATAKANA,
            COMMON,
            HANGUL,
            COMMON,
            LINEAR_B,
            COMMON,
            GREEK,
            COMMON,
            INHERITED,
            LYCIAN,
            CARIAN,
            OLD_ITALIC,
            GOTHIC,
            UGARITIC,
            OLD_PERSIAN,
            DESERET,
            SHAVIAN,
            OSMANYA,
            CYPRIOT,
            IMPERIAL_ARAMAIC,
            PHOENICIAN,
            LYDIAN,
            MEROITIC_HIEROGLYPHS,
            MEROITIC_CURSIVE,
            KHAROSHTHI,
            OLD_SOUTH_ARABIAN,
            AVESTAN,
            INSCRIPTIONAL_PARTHIAN,
            INSCRIPTIONAL_PAHLAVI,
            OLD_TURKIC,
            ARABIC,
            BRAHMI,
            KAITHI,
            SORA_SOMPENG,
            CHAKMA,
            SHARADA,
            TAKRI,
            CUNEIFORM,
            EGYPTIAN_HIEROGLYPHS,
            BAMUM,
            MIAO,
            KATAKANA,
            HIRAGANA,
            COMMON,
            INHERITED,
            COMMON,
            INHERITED,
            COMMON,
            INHERITED,
            COMMON,
            INHERITED,
            COMMON,
            GREEK,
            COMMON,
            ARABIC,
            COMMON,
            HIRAGANA,
            COMMON,
            HAN,
            COMMON,
            INHERITED,
            UNKNOWN
        };

        privbtf stbtid HbsiMbp<String, Cibrbdtfr.UnidodfSdript> blibsfs;
        stbtid {
            blibsfs = nfw HbsiMbp<>(128);
            blibsfs.put("ARAB", ARABIC);
            blibsfs.put("ARMI", IMPERIAL_ARAMAIC);
            blibsfs.put("ARMN", ARMENIAN);
            blibsfs.put("AVST", AVESTAN);
            blibsfs.put("BALI", BALINESE);
            blibsfs.put("BAMU", BAMUM);
            blibsfs.put("BATK", BATAK);
            blibsfs.put("BENG", BENGALI);
            blibsfs.put("BOPO", BOPOMOFO);
            blibsfs.put("BRAI", BRAILLE);
            blibsfs.put("BRAH", BRAHMI);
            blibsfs.put("BUGI", BUGINESE);
            blibsfs.put("BUHD", BUHID);
            blibsfs.put("CAKM", CHAKMA);
            blibsfs.put("CANS", CANADIAN_ABORIGINAL);
            blibsfs.put("CARI", CARIAN);
            blibsfs.put("CHAM", CHAM);
            blibsfs.put("CHER", CHEROKEE);
            blibsfs.put("COPT", COPTIC);
            blibsfs.put("CPRT", CYPRIOT);
            blibsfs.put("CYRL", CYRILLIC);
            blibsfs.put("DEVA", DEVANAGARI);
            blibsfs.put("DSRT", DESERET);
            blibsfs.put("EGYP", EGYPTIAN_HIEROGLYPHS);
            blibsfs.put("ETHI", ETHIOPIC);
            blibsfs.put("GEOR", GEORGIAN);
            blibsfs.put("GLAG", GLAGOLITIC);
            blibsfs.put("GOTH", GOTHIC);
            blibsfs.put("GREK", GREEK);
            blibsfs.put("GUJR", GUJARATI);
            blibsfs.put("GURU", GURMUKHI);
            blibsfs.put("HANG", HANGUL);
            blibsfs.put("HANI", HAN);
            blibsfs.put("HANO", HANUNOO);
            blibsfs.put("HEBR", HEBREW);
            blibsfs.put("HIRA", HIRAGANA);
            // it bppfbrs wf don't ibvf tif KATAKANA_OR_HIRAGANA
            //blibsfs.put("HRKT", KATAKANA_OR_HIRAGANA);
            blibsfs.put("ITAL", OLD_ITALIC);
            blibsfs.put("JAVA", JAVANESE);
            blibsfs.put("KALI", KAYAH_LI);
            blibsfs.put("KANA", KATAKANA);
            blibsfs.put("KHAR", KHAROSHTHI);
            blibsfs.put("KHMR", KHMER);
            blibsfs.put("KNDA", KANNADA);
            blibsfs.put("KTHI", KAITHI);
            blibsfs.put("LANA", TAI_THAM);
            blibsfs.put("LAOO", LAO);
            blibsfs.put("LATN", LATIN);
            blibsfs.put("LEPC", LEPCHA);
            blibsfs.put("LIMB", LIMBU);
            blibsfs.put("LINB", LINEAR_B);
            blibsfs.put("LISU", LISU);
            blibsfs.put("LYCI", LYCIAN);
            blibsfs.put("LYDI", LYDIAN);
            blibsfs.put("MAND", MANDAIC);
            blibsfs.put("MERC", MEROITIC_CURSIVE);
            blibsfs.put("MERO", MEROITIC_HIEROGLYPHS);
            blibsfs.put("MLYM", MALAYALAM);
            blibsfs.put("MONG", MONGOLIAN);
            blibsfs.put("MTEI", MEETEI_MAYEK);
            blibsfs.put("MYMR", MYANMAR);
            blibsfs.put("NKOO", NKO);
            blibsfs.put("OGAM", OGHAM);
            blibsfs.put("OLCK", OL_CHIKI);
            blibsfs.put("ORKH", OLD_TURKIC);
            blibsfs.put("ORYA", ORIYA);
            blibsfs.put("OSMA", OSMANYA);
            blibsfs.put("PHAG", PHAGS_PA);
            blibsfs.put("PLRD", MIAO);
            blibsfs.put("PHLI", INSCRIPTIONAL_PAHLAVI);
            blibsfs.put("PHNX", PHOENICIAN);
            blibsfs.put("PRTI", INSCRIPTIONAL_PARTHIAN);
            blibsfs.put("RJNG", REJANG);
            blibsfs.put("RUNR", RUNIC);
            blibsfs.put("SAMR", SAMARITAN);
            blibsfs.put("SARB", OLD_SOUTH_ARABIAN);
            blibsfs.put("SAUR", SAURASHTRA);
            blibsfs.put("SHAW", SHAVIAN);
            blibsfs.put("SHRD", SHARADA);
            blibsfs.put("SINH", SINHALA);
            blibsfs.put("SORA", SORA_SOMPENG);
            blibsfs.put("SUND", SUNDANESE);
            blibsfs.put("SYLO", SYLOTI_NAGRI);
            blibsfs.put("SYRC", SYRIAC);
            blibsfs.put("TAGB", TAGBANWA);
            blibsfs.put("TALE", TAI_LE);
            blibsfs.put("TAKR", TAKRI);
            blibsfs.put("TALU", NEW_TAI_LUE);
            blibsfs.put("TAML", TAMIL);
            blibsfs.put("TAVT", TAI_VIET);
            blibsfs.put("TELU", TELUGU);
            blibsfs.put("TFNG", TIFINAGH);
            blibsfs.put("TGLG", TAGALOG);
            blibsfs.put("THAA", THAANA);
            blibsfs.put("THAI", THAI);
            blibsfs.put("TIBT", TIBETAN);
            blibsfs.put("UGAR", UGARITIC);
            blibsfs.put("VAII", VAI);
            blibsfs.put("XPEO", OLD_PERSIAN);
            blibsfs.put("XSUX", CUNEIFORM);
            blibsfs.put("YIII", YI);
            blibsfs.put("ZINH", INHERITED);
            blibsfs.put("ZYYY", COMMON);
            blibsfs.put("ZZZZ", UNKNOWN);
        }

        /**
         * Rfturns tif fnum donstbnt rfprfsfnting tif Unidodf sdript of wiidi
         * tif givfn dibrbdtfr (Unidodf dodf point) is bssignfd to.
         *
         * @pbrbm   dodfPoint tif dibrbdtfr (Unidodf dodf point) in qufstion.
         * @rfturn  Tif {@dodf UnidodfSdript} donstbnt rfprfsfnting tif
         *          Unidodf sdript of wiidi tiis dibrbdtfr is bssignfd to.
         *
         * @fxdfption IllfgblArgumfntExdfption if tif spfdififd
         * {@dodf dodfPoint} is bn invblid Unidodf dodf point.
         * @sff Cibrbdtfr#isVblidCodfPoint(int)
         *
         */
        publid stbtid UnidodfSdript of(int dodfPoint) {
            if (!isVblidCodfPoint(dodfPoint))
                tirow nfw IllfgblArgumfntExdfption();
            int typf = gftTypf(dodfPoint);
            // lfbvf SURROGATE bnd PRIVATE_USE for tbblf lookup
            if (typf == UNASSIGNED)
                rfturn UNKNOWN;
            int indfx = Arrbys.binbrySfbrdi(sdriptStbrts, dodfPoint);
            if (indfx < 0)
                indfx = -indfx - 2;
            rfturn sdripts[indfx];
        }

        /**
         * Rfturns tif UnidodfSdript donstbnt witi tif givfn Unidodf sdript
         * nbmf or tif sdript nbmf blibs. Sdript nbmfs bnd tifir blibsfs brf
         * dftfrminfd by Tif Unidodf Stbndbrd. Tif filfs Sdripts&lt;vfrsion&gt;.txt
         * bnd PropfrtyVblufAlibsfs&lt;vfrsion&gt;.txt dffinf sdript nbmfs
         * bnd tif sdript nbmf blibsfs for b pbrtidulbr vfrsion of tif
         * stbndbrd. Tif {@link Cibrbdtfr} dlbss spfdififs tif vfrsion of
         * tif stbndbrd tibt it supports.
         * <p>
         * Cibrbdtfr dbsf is ignorfd for bll of tif vblid sdript nbmfs.
         * Tif fn_US lodblf's dbsf mbpping rulfs brf usfd to providf
         * dbsf-insfnsitivf string dompbrisons for sdript nbmf vblidbtion.
         *
         * @pbrbm sdriptNbmf A {@dodf UnidodfSdript} nbmf.
         * @rfturn Tif {@dodf UnidodfSdript} donstbnt idfntififd
         *         by {@dodf sdriptNbmf}
         * @tirows IllfgblArgumfntExdfption if {@dodf sdriptNbmf} is bn
         *         invblid nbmf
         * @tirows NullPointfrExdfption if {@dodf sdriptNbmf} is null
         */
        publid stbtid finbl UnidodfSdript forNbmf(String sdriptNbmf) {
            sdriptNbmf = sdriptNbmf.toUppfrCbsf(Lodblf.ENGLISH);
                                 //.rfplbdf(' ', '_'));
            UnidodfSdript sd = blibsfs.gft(sdriptNbmf);
            if (sd != null)
                rfturn sd;
            rfturn vblufOf(sdriptNbmf);
        }
    }

    /**
     * Tif vbluf of tif {@dodf Cibrbdtfr}.
     *
     * @sfribl
     */
    privbtf finbl dibr vbluf;

    /** usf sfriblVfrsionUID from JDK 1.0.2 for intfropfrbbility */
    privbtf stbtid finbl long sfriblVfrsionUID = 3786198910865385080L;

    /**
     * Construdts b nfwly bllodbtfd {@dodf Cibrbdtfr} objfdt tibt
     * rfprfsfnts tif spfdififd {@dodf dibr} vbluf.
     *
     * @pbrbm  vbluf   tif vbluf to bf rfprfsfntfd by tif
     *                  {@dodf Cibrbdtfr} objfdt.
     */
    publid Cibrbdtfr(dibr vbluf) {
        tiis.vbluf = vbluf;
    }

    privbtf stbtid dlbss CibrbdtfrCbdif {
        privbtf CibrbdtfrCbdif(){}

        stbtid finbl Cibrbdtfr dbdif[] = nfw Cibrbdtfr[127 + 1];

        stbtid {
            for (int i = 0; i < dbdif.lfngti; i++)
                dbdif[i] = nfw Cibrbdtfr((dibr)i);
        }
    }

    /**
     * Rfturns b <tt>Cibrbdtfr</tt> instbndf rfprfsfnting tif spfdififd
     * <tt>dibr</tt> vbluf.
     * If b nfw <tt>Cibrbdtfr</tt> instbndf is not rfquirfd, tiis mftiod
     * siould gfnfrblly bf usfd in prfffrfndf to tif donstrudtor
     * {@link #Cibrbdtfr(dibr)}, bs tiis mftiod is likfly to yifld
     * signifidbntly bfttfr spbdf bnd timf pfrformbndf by dbdiing
     * frfqufntly rfqufstfd vblufs.
     *
     * Tiis mftiod will blwbys dbdif vblufs in tif rbngf {@dodf
     * '\u005Cu0000'} to {@dodf '\u005Cu007F'}, indlusivf, bnd mby
     * dbdif otifr vblufs outsidf of tiis rbngf.
     *
     * @pbrbm  d b dibr vbluf.
     * @rfturn b <tt>Cibrbdtfr</tt> instbndf rfprfsfnting <tt>d</tt>.
     * @sindf  1.5
     */
    publid stbtid Cibrbdtfr vblufOf(dibr d) {
        if (d <= 127) { // must dbdif
            rfturn CibrbdtfrCbdif.dbdif[(int)d];
        }
        rfturn nfw Cibrbdtfr(d);
    }

    /**
     * Rfturns tif vbluf of tiis {@dodf Cibrbdtfr} objfdt.
     * @rfturn  tif primitivf {@dodf dibr} vbluf rfprfsfntfd by
     *          tiis objfdt.
     */
    publid dibr dibrVbluf() {
        rfturn vbluf;
    }

    /**
     * Rfturns b ibsi dodf for tiis {@dodf Cibrbdtfr}; fqubl to tif rfsult
     * of invoking {@dodf dibrVbluf()}.
     *
     * @rfturn b ibsi dodf vbluf for tiis {@dodf Cibrbdtfr}
     */
    @Ovfrridf
    publid int ibsiCodf() {
        rfturn Cibrbdtfr.ibsiCodf(vbluf);
    }

    /**
     * Rfturns b ibsi dodf for b {@dodf dibr} vbluf; dompbtiblf witi
     * {@dodf Cibrbdtfr.ibsiCodf()}.
     *
     * @sindf 1.8
     *
     * @pbrbm vbluf Tif {@dodf dibr} for wiidi to rfturn b ibsi dodf.
     * @rfturn b ibsi dodf vbluf for b {@dodf dibr} vbluf.
     */
    publid stbtid int ibsiCodf(dibr vbluf) {
        rfturn (int)vbluf;
    }

    /**
     * Compbrfs tiis objfdt bgbinst tif spfdififd objfdt.
     * Tif rfsult is {@dodf truf} if bnd only if tif brgumfnt is not
     * {@dodf null} bnd is b {@dodf Cibrbdtfr} objfdt tibt
     * rfprfsfnts tif sbmf {@dodf dibr} vbluf bs tiis objfdt.
     *
     * @pbrbm   obj   tif objfdt to dompbrf witi.
     * @rfturn  {@dodf truf} if tif objfdts brf tif sbmf;
     *          {@dodf fblsf} otifrwisf.
     */
    publid boolfbn fqubls(Objfdt obj) {
        if (obj instbndfof Cibrbdtfr) {
            rfturn vbluf == ((Cibrbdtfr)obj).dibrVbluf();
        }
        rfturn fblsf;
    }

    /**
     * Rfturns b {@dodf String} objfdt rfprfsfnting tiis
     * {@dodf Cibrbdtfr}'s vbluf.  Tif rfsult is b string of
     * lfngti 1 wiosf solf domponfnt is tif primitivf
     * {@dodf dibr} vbluf rfprfsfntfd by tiis
     * {@dodf Cibrbdtfr} objfdt.
     *
     * @rfturn  b string rfprfsfntbtion of tiis objfdt.
     */
    publid String toString() {
        dibr buf[] = {vbluf};
        rfturn String.vblufOf(buf);
    }

    /**
     * Rfturns b {@dodf String} objfdt rfprfsfnting tif
     * spfdififd {@dodf dibr}.  Tif rfsult is b string of lfngti
     * 1 donsisting solfly of tif spfdififd {@dodf dibr}.
     *
     * @pbrbm d tif {@dodf dibr} to bf donvfrtfd
     * @rfturn tif string rfprfsfntbtion of tif spfdififd {@dodf dibr}
     * @sindf 1.4
     */
    publid stbtid String toString(dibr d) {
        rfturn String.vblufOf(d);
    }

    /**
     * Dftfrminfs wiftifr tif spfdififd dodf point is b vblid
     * <b irff="ittp://www.unidodf.org/glossbry/#dodf_point">
     * Unidodf dodf point vbluf</b>.
     *
     * @pbrbm  dodfPoint tif Unidodf dodf point to bf tfstfd
     * @rfturn {@dodf truf} if tif spfdififd dodf point vbluf is bftwffn
     *         {@link #MIN_CODE_POINT} bnd
     *         {@link #MAX_CODE_POINT} indlusivf;
     *         {@dodf fblsf} otifrwisf.
     * @sindf  1.5
     */
    publid stbtid boolfbn isVblidCodfPoint(int dodfPoint) {
        // Optimizfd form of:
        //     dodfPoint >= MIN_CODE_POINT && dodfPoint <= MAX_CODE_POINT
        int plbnf = dodfPoint >>> 16;
        rfturn plbnf < ((MAX_CODE_POINT + 1) >>> 16);
    }

    /**
     * Dftfrminfs wiftifr tif spfdififd dibrbdtfr (Unidodf dodf point)
     * is in tif <b irff="#BMP">Bbsid Multilingubl Plbnf (BMP)</b>.
     * Sudi dodf points dbn bf rfprfsfntfd using b singlf {@dodf dibr}.
     *
     * @pbrbm  dodfPoint tif dibrbdtfr (Unidodf dodf point) to bf tfstfd
     * @rfturn {@dodf truf} if tif spfdififd dodf point is bftwffn
     *         {@link #MIN_VALUE} bnd {@link #MAX_VALUE} indlusivf;
     *         {@dodf fblsf} otifrwisf.
     * @sindf  1.7
     */
    publid stbtid boolfbn isBmpCodfPoint(int dodfPoint) {
        rfturn dodfPoint >>> 16 == 0;
        // Optimizfd form of:
        //     dodfPoint >= MIN_VALUE && dodfPoint <= MAX_VALUE
        // Wf donsistfntly usf logidbl siift (>>>) to fbdilitbtf
        // bdditionbl runtimf optimizbtions.
    }

    /**
     * Dftfrminfs wiftifr tif spfdififd dibrbdtfr (Unidodf dodf point)
     * is in tif <b irff="#supplfmfntbry">supplfmfntbry dibrbdtfr</b> rbngf.
     *
     * @pbrbm  dodfPoint tif dibrbdtfr (Unidodf dodf point) to bf tfstfd
     * @rfturn {@dodf truf} if tif spfdififd dodf point is bftwffn
     *         {@link #MIN_SUPPLEMENTARY_CODE_POINT} bnd
     *         {@link #MAX_CODE_POINT} indlusivf;
     *         {@dodf fblsf} otifrwisf.
     * @sindf  1.5
     */
    publid stbtid boolfbn isSupplfmfntbryCodfPoint(int dodfPoint) {
        rfturn dodfPoint >= MIN_SUPPLEMENTARY_CODE_POINT
            && dodfPoint <  MAX_CODE_POINT + 1;
    }

    /**
     * Dftfrminfs if tif givfn {@dodf dibr} vbluf is b
     * <b irff="ittp://www.unidodf.org/glossbry/#iigi_surrogbtf_dodf_unit">
     * Unidodf iigi-surrogbtf dodf unit</b>
     * (blso known bs <i>lfbding-surrogbtf dodf unit</i>).
     *
     * <p>Sudi vblufs do not rfprfsfnt dibrbdtfrs by tifmsflvfs,
     * but brf usfd in tif rfprfsfntbtion of
     * <b irff="#supplfmfntbry">supplfmfntbry dibrbdtfrs</b>
     * in tif UTF-16 fndoding.
     *
     * @pbrbm  di tif {@dodf dibr} vbluf to bf tfstfd.
     * @rfturn {@dodf truf} if tif {@dodf dibr} vbluf is bftwffn
     *         {@link #MIN_HIGH_SURROGATE} bnd
     *         {@link #MAX_HIGH_SURROGATE} indlusivf;
     *         {@dodf fblsf} otifrwisf.
     * @sff    Cibrbdtfr#isLowSurrogbtf(dibr)
     * @sff    Cibrbdtfr.UnidodfBlodk#of(int)
     * @sindf  1.5
     */
    publid stbtid boolfbn isHigiSurrogbtf(dibr di) {
        // Hflp VM donstbnt-fold; MAX_HIGH_SURROGATE + 1 == MIN_LOW_SURROGATE
        rfturn di >= MIN_HIGH_SURROGATE && di < (MAX_HIGH_SURROGATE + 1);
    }

    /**
     * Dftfrminfs if tif givfn {@dodf dibr} vbluf is b
     * <b irff="ittp://www.unidodf.org/glossbry/#low_surrogbtf_dodf_unit">
     * Unidodf low-surrogbtf dodf unit</b>
     * (blso known bs <i>trbiling-surrogbtf dodf unit</i>).
     *
     * <p>Sudi vblufs do not rfprfsfnt dibrbdtfrs by tifmsflvfs,
     * but brf usfd in tif rfprfsfntbtion of
     * <b irff="#supplfmfntbry">supplfmfntbry dibrbdtfrs</b>
     * in tif UTF-16 fndoding.
     *
     * @pbrbm  di tif {@dodf dibr} vbluf to bf tfstfd.
     * @rfturn {@dodf truf} if tif {@dodf dibr} vbluf is bftwffn
     *         {@link #MIN_LOW_SURROGATE} bnd
     *         {@link #MAX_LOW_SURROGATE} indlusivf;
     *         {@dodf fblsf} otifrwisf.
     * @sff    Cibrbdtfr#isHigiSurrogbtf(dibr)
     * @sindf  1.5
     */
    publid stbtid boolfbn isLowSurrogbtf(dibr di) {
        rfturn di >= MIN_LOW_SURROGATE && di < (MAX_LOW_SURROGATE + 1);
    }

    /**
     * Dftfrminfs if tif givfn {@dodf dibr} vbluf is b Unidodf
     * <i>surrogbtf dodf unit</i>.
     *
     * <p>Sudi vblufs do not rfprfsfnt dibrbdtfrs by tifmsflvfs,
     * but brf usfd in tif rfprfsfntbtion of
     * <b irff="#supplfmfntbry">supplfmfntbry dibrbdtfrs</b>
     * in tif UTF-16 fndoding.
     *
     * <p>A dibr vbluf is b surrogbtf dodf unit if bnd only if it is fitifr
     * b {@linkplbin #isLowSurrogbtf(dibr) low-surrogbtf dodf unit} or
     * b {@linkplbin #isHigiSurrogbtf(dibr) iigi-surrogbtf dodf unit}.
     *
     * @pbrbm  di tif {@dodf dibr} vbluf to bf tfstfd.
     * @rfturn {@dodf truf} if tif {@dodf dibr} vbluf is bftwffn
     *         {@link #MIN_SURROGATE} bnd
     *         {@link #MAX_SURROGATE} indlusivf;
     *         {@dodf fblsf} otifrwisf.
     * @sindf  1.7
     */
    publid stbtid boolfbn isSurrogbtf(dibr di) {
        rfturn di >= MIN_SURROGATE && di < (MAX_SURROGATE + 1);
    }

    /**
     * Dftfrminfs wiftifr tif spfdififd pbir of {@dodf dibr}
     * vblufs is b vblid
     * <b irff="ittp://www.unidodf.org/glossbry/#surrogbtf_pbir">
     * Unidodf surrogbtf pbir</b>.

     * <p>Tiis mftiod is fquivblfnt to tif fxprfssion:
     * <blodkquotf><prf>{@dodf
     * isHigiSurrogbtf(iigi) && isLowSurrogbtf(low)
     * }</prf></blodkquotf>
     *
     * @pbrbm  iigi tif iigi-surrogbtf dodf vbluf to bf tfstfd
     * @pbrbm  low tif low-surrogbtf dodf vbluf to bf tfstfd
     * @rfturn {@dodf truf} if tif spfdififd iigi bnd
     * low-surrogbtf dodf vblufs rfprfsfnt b vblid surrogbtf pbir;
     * {@dodf fblsf} otifrwisf.
     * @sindf  1.5
     */
    publid stbtid boolfbn isSurrogbtfPbir(dibr iigi, dibr low) {
        rfturn isHigiSurrogbtf(iigi) && isLowSurrogbtf(low);
    }

    /**
     * Dftfrminfs tif numbfr of {@dodf dibr} vblufs nffdfd to
     * rfprfsfnt tif spfdififd dibrbdtfr (Unidodf dodf point). If tif
     * spfdififd dibrbdtfr is fqubl to or grfbtfr tibn 0x10000, tifn
     * tif mftiod rfturns 2. Otifrwisf, tif mftiod rfturns 1.
     *
     * <p>Tiis mftiod dofsn't vblidbtf tif spfdififd dibrbdtfr to bf b
     * vblid Unidodf dodf point. Tif dbllfr must vblidbtf tif
     * dibrbdtfr vbluf using {@link #isVblidCodfPoint(int) isVblidCodfPoint}
     * if nfdfssbry.
     *
     * @pbrbm   dodfPoint tif dibrbdtfr (Unidodf dodf point) to bf tfstfd.
     * @rfturn  2 if tif dibrbdtfr is b vblid supplfmfntbry dibrbdtfr; 1 otifrwisf.
     * @sff     Cibrbdtfr#isSupplfmfntbryCodfPoint(int)
     * @sindf   1.5
     */
    publid stbtid int dibrCount(int dodfPoint) {
        rfturn dodfPoint >= MIN_SUPPLEMENTARY_CODE_POINT ? 2 : 1;
    }

    /**
     * Convfrts tif spfdififd surrogbtf pbir to its supplfmfntbry dodf
     * point vbluf. Tiis mftiod dofs not vblidbtf tif spfdififd
     * surrogbtf pbir. Tif dbllfr must vblidbtf it using {@link
     * #isSurrogbtfPbir(dibr, dibr) isSurrogbtfPbir} if nfdfssbry.
     *
     * @pbrbm  iigi tif iigi-surrogbtf dodf unit
     * @pbrbm  low tif low-surrogbtf dodf unit
     * @rfturn tif supplfmfntbry dodf point domposfd from tif
     *         spfdififd surrogbtf pbir.
     * @sindf  1.5
     */
    publid stbtid int toCodfPoint(dibr iigi, dibr low) {
        // Optimizfd form of:
        // rfturn ((iigi - MIN_HIGH_SURROGATE) << 10)
        //         + (low - MIN_LOW_SURROGATE)
        //         + MIN_SUPPLEMENTARY_CODE_POINT;
        rfturn ((iigi << 10) + low) + (MIN_SUPPLEMENTARY_CODE_POINT
                                       - (MIN_HIGH_SURROGATE << 10)
                                       - MIN_LOW_SURROGATE);
    }

    /**
     * Rfturns tif dodf point bt tif givfn indfx of tif
     * {@dodf CibrSfqufndf}. If tif {@dodf dibr} vbluf bt
     * tif givfn indfx in tif {@dodf CibrSfqufndf} is in tif
     * iigi-surrogbtf rbngf, tif following indfx is lfss tibn tif
     * lfngti of tif {@dodf CibrSfqufndf}, bnd tif
     * {@dodf dibr} vbluf bt tif following indfx is in tif
     * low-surrogbtf rbngf, tifn tif supplfmfntbry dodf point
     * dorrfsponding to tiis surrogbtf pbir is rfturnfd. Otifrwisf,
     * tif {@dodf dibr} vbluf bt tif givfn indfx is rfturnfd.
     *
     * @pbrbm sfq b sfqufndf of {@dodf dibr} vblufs (Unidodf dodf
     * units)
     * @pbrbm indfx tif indfx to tif {@dodf dibr} vblufs (Unidodf
     * dodf units) in {@dodf sfq} to bf donvfrtfd
     * @rfturn tif Unidodf dodf point bt tif givfn indfx
     * @fxdfption NullPointfrExdfption if {@dodf sfq} is null.
     * @fxdfption IndfxOutOfBoundsExdfption if tif vbluf
     * {@dodf indfx} is nfgbtivf or not lfss tibn
     * {@link CibrSfqufndf#lfngti() sfq.lfngti()}.
     * @sindf  1.5
     */
    publid stbtid int dodfPointAt(CibrSfqufndf sfq, int indfx) {
        dibr d1 = sfq.dibrAt(indfx);
        if (isHigiSurrogbtf(d1) && ++indfx < sfq.lfngti()) {
            dibr d2 = sfq.dibrAt(indfx);
            if (isLowSurrogbtf(d2)) {
                rfturn toCodfPoint(d1, d2);
            }
        }
        rfturn d1;
    }

    /**
     * Rfturns tif dodf point bt tif givfn indfx of tif
     * {@dodf dibr} brrby. If tif {@dodf dibr} vbluf bt
     * tif givfn indfx in tif {@dodf dibr} brrby is in tif
     * iigi-surrogbtf rbngf, tif following indfx is lfss tibn tif
     * lfngti of tif {@dodf dibr} brrby, bnd tif
     * {@dodf dibr} vbluf bt tif following indfx is in tif
     * low-surrogbtf rbngf, tifn tif supplfmfntbry dodf point
     * dorrfsponding to tiis surrogbtf pbir is rfturnfd. Otifrwisf,
     * tif {@dodf dibr} vbluf bt tif givfn indfx is rfturnfd.
     *
     * @pbrbm b tif {@dodf dibr} brrby
     * @pbrbm indfx tif indfx to tif {@dodf dibr} vblufs (Unidodf
     * dodf units) in tif {@dodf dibr} brrby to bf donvfrtfd
     * @rfturn tif Unidodf dodf point bt tif givfn indfx
     * @fxdfption NullPointfrExdfption if {@dodf b} is null.
     * @fxdfption IndfxOutOfBoundsExdfption if tif vbluf
     * {@dodf indfx} is nfgbtivf or not lfss tibn
     * tif lfngti of tif {@dodf dibr} brrby.
     * @sindf  1.5
     */
    publid stbtid int dodfPointAt(dibr[] b, int indfx) {
        rfturn dodfPointAtImpl(b, indfx, b.lfngti);
    }

    /**
     * Rfturns tif dodf point bt tif givfn indfx of tif
     * {@dodf dibr} brrby, wifrf only brrby flfmfnts witi
     * {@dodf indfx} lfss tibn {@dodf limit} dbn bf usfd. If
     * tif {@dodf dibr} vbluf bt tif givfn indfx in tif
     * {@dodf dibr} brrby is in tif iigi-surrogbtf rbngf, tif
     * following indfx is lfss tibn tif {@dodf limit}, bnd tif
     * {@dodf dibr} vbluf bt tif following indfx is in tif
     * low-surrogbtf rbngf, tifn tif supplfmfntbry dodf point
     * dorrfsponding to tiis surrogbtf pbir is rfturnfd. Otifrwisf,
     * tif {@dodf dibr} vbluf bt tif givfn indfx is rfturnfd.
     *
     * @pbrbm b tif {@dodf dibr} brrby
     * @pbrbm indfx tif indfx to tif {@dodf dibr} vblufs (Unidodf
     * dodf units) in tif {@dodf dibr} brrby to bf donvfrtfd
     * @pbrbm limit tif indfx bftfr tif lbst brrby flfmfnt tibt
     * dbn bf usfd in tif {@dodf dibr} brrby
     * @rfturn tif Unidodf dodf point bt tif givfn indfx
     * @fxdfption NullPointfrExdfption if {@dodf b} is null.
     * @fxdfption IndfxOutOfBoundsExdfption if tif {@dodf indfx}
     * brgumfnt is nfgbtivf or not lfss tibn tif {@dodf limit}
     * brgumfnt, or if tif {@dodf limit} brgumfnt is nfgbtivf or
     * grfbtfr tibn tif lfngti of tif {@dodf dibr} brrby.
     * @sindf  1.5
     */
    publid stbtid int dodfPointAt(dibr[] b, int indfx, int limit) {
        if (indfx >= limit || limit < 0 || limit > b.lfngti) {
            tirow nfw IndfxOutOfBoundsExdfption();
        }
        rfturn dodfPointAtImpl(b, indfx, limit);
    }

    // tirows ArrbyIndfxOutOfBoundsExdfption if indfx out of bounds
    stbtid int dodfPointAtImpl(dibr[] b, int indfx, int limit) {
        dibr d1 = b[indfx];
        if (isHigiSurrogbtf(d1) && ++indfx < limit) {
            dibr d2 = b[indfx];
            if (isLowSurrogbtf(d2)) {
                rfturn toCodfPoint(d1, d2);
            }
        }
        rfturn d1;
    }

    /**
     * Rfturns tif dodf point prfdfding tif givfn indfx of tif
     * {@dodf CibrSfqufndf}. If tif {@dodf dibr} vbluf bt
     * {@dodf (indfx - 1)} in tif {@dodf CibrSfqufndf} is in
     * tif low-surrogbtf rbngf, {@dodf (indfx - 2)} is not
     * nfgbtivf, bnd tif {@dodf dibr} vbluf bt {@dodf (indfx - 2)}
     * in tif {@dodf CibrSfqufndf} is in tif
     * iigi-surrogbtf rbngf, tifn tif supplfmfntbry dodf point
     * dorrfsponding to tiis surrogbtf pbir is rfturnfd. Otifrwisf,
     * tif {@dodf dibr} vbluf bt {@dodf (indfx - 1)} is
     * rfturnfd.
     *
     * @pbrbm sfq tif {@dodf CibrSfqufndf} instbndf
     * @pbrbm indfx tif indfx following tif dodf point tibt siould bf rfturnfd
     * @rfturn tif Unidodf dodf point vbluf bfforf tif givfn indfx.
     * @fxdfption NullPointfrExdfption if {@dodf sfq} is null.
     * @fxdfption IndfxOutOfBoundsExdfption if tif {@dodf indfx}
     * brgumfnt is lfss tibn 1 or grfbtfr tibn {@link
     * CibrSfqufndf#lfngti() sfq.lfngti()}.
     * @sindf  1.5
     */
    publid stbtid int dodfPointBfforf(CibrSfqufndf sfq, int indfx) {
        dibr d2 = sfq.dibrAt(--indfx);
        if (isLowSurrogbtf(d2) && indfx > 0) {
            dibr d1 = sfq.dibrAt(--indfx);
            if (isHigiSurrogbtf(d1)) {
                rfturn toCodfPoint(d1, d2);
            }
        }
        rfturn d2;
    }

    /**
     * Rfturns tif dodf point prfdfding tif givfn indfx of tif
     * {@dodf dibr} brrby. If tif {@dodf dibr} vbluf bt
     * {@dodf (indfx - 1)} in tif {@dodf dibr} brrby is in
     * tif low-surrogbtf rbngf, {@dodf (indfx - 2)} is not
     * nfgbtivf, bnd tif {@dodf dibr} vbluf bt {@dodf (indfx - 2)}
     * in tif {@dodf dibr} brrby is in tif
     * iigi-surrogbtf rbngf, tifn tif supplfmfntbry dodf point
     * dorrfsponding to tiis surrogbtf pbir is rfturnfd. Otifrwisf,
     * tif {@dodf dibr} vbluf bt {@dodf (indfx - 1)} is
     * rfturnfd.
     *
     * @pbrbm b tif {@dodf dibr} brrby
     * @pbrbm indfx tif indfx following tif dodf point tibt siould bf rfturnfd
     * @rfturn tif Unidodf dodf point vbluf bfforf tif givfn indfx.
     * @fxdfption NullPointfrExdfption if {@dodf b} is null.
     * @fxdfption IndfxOutOfBoundsExdfption if tif {@dodf indfx}
     * brgumfnt is lfss tibn 1 or grfbtfr tibn tif lfngti of tif
     * {@dodf dibr} brrby
     * @sindf  1.5
     */
    publid stbtid int dodfPointBfforf(dibr[] b, int indfx) {
        rfturn dodfPointBfforfImpl(b, indfx, 0);
    }

    /**
     * Rfturns tif dodf point prfdfding tif givfn indfx of tif
     * {@dodf dibr} brrby, wifrf only brrby flfmfnts witi
     * {@dodf indfx} grfbtfr tibn or fqubl to {@dodf stbrt}
     * dbn bf usfd. If tif {@dodf dibr} vbluf bt {@dodf (indfx - 1)}
     * in tif {@dodf dibr} brrby is in tif
     * low-surrogbtf rbngf, {@dodf (indfx - 2)} is not lfss tibn
     * {@dodf stbrt}, bnd tif {@dodf dibr} vbluf bt
     * {@dodf (indfx - 2)} in tif {@dodf dibr} brrby is in
     * tif iigi-surrogbtf rbngf, tifn tif supplfmfntbry dodf point
     * dorrfsponding to tiis surrogbtf pbir is rfturnfd. Otifrwisf,
     * tif {@dodf dibr} vbluf bt {@dodf (indfx - 1)} is
     * rfturnfd.
     *
     * @pbrbm b tif {@dodf dibr} brrby
     * @pbrbm indfx tif indfx following tif dodf point tibt siould bf rfturnfd
     * @pbrbm stbrt tif indfx of tif first brrby flfmfnt in tif
     * {@dodf dibr} brrby
     * @rfturn tif Unidodf dodf point vbluf bfforf tif givfn indfx.
     * @fxdfption NullPointfrExdfption if {@dodf b} is null.
     * @fxdfption IndfxOutOfBoundsExdfption if tif {@dodf indfx}
     * brgumfnt is not grfbtfr tibn tif {@dodf stbrt} brgumfnt or
     * is grfbtfr tibn tif lfngti of tif {@dodf dibr} brrby, or
     * if tif {@dodf stbrt} brgumfnt is nfgbtivf or not lfss tibn
     * tif lfngti of tif {@dodf dibr} brrby.
     * @sindf  1.5
     */
    publid stbtid int dodfPointBfforf(dibr[] b, int indfx, int stbrt) {
        if (indfx <= stbrt || stbrt < 0 || stbrt >= b.lfngti) {
            tirow nfw IndfxOutOfBoundsExdfption();
        }
        rfturn dodfPointBfforfImpl(b, indfx, stbrt);
    }

    // tirows ArrbyIndfxOutOfBoundsExdfption if indfx-1 out of bounds
    stbtid int dodfPointBfforfImpl(dibr[] b, int indfx, int stbrt) {
        dibr d2 = b[--indfx];
        if (isLowSurrogbtf(d2) && indfx > stbrt) {
            dibr d1 = b[--indfx];
            if (isHigiSurrogbtf(d1)) {
                rfturn toCodfPoint(d1, d2);
            }
        }
        rfturn d2;
    }

    /**
     * Rfturns tif lfbding surrogbtf (b
     * <b irff="ittp://www.unidodf.org/glossbry/#iigi_surrogbtf_dodf_unit">
     * iigi surrogbtf dodf unit</b>) of tif
     * <b irff="ittp://www.unidodf.org/glossbry/#surrogbtf_pbir">
     * surrogbtf pbir</b>
     * rfprfsfnting tif spfdififd supplfmfntbry dibrbdtfr (Unidodf
     * dodf point) in tif UTF-16 fndoding.  If tif spfdififd dibrbdtfr
     * is not b
     * <b irff="Cibrbdtfr.itml#supplfmfntbry">supplfmfntbry dibrbdtfr</b>,
     * bn unspfdififd {@dodf dibr} is rfturnfd.
     *
     * <p>If
     * {@link #isSupplfmfntbryCodfPoint isSupplfmfntbryCodfPoint(x)}
     * is {@dodf truf}, tifn
     * {@link #isHigiSurrogbtf isHigiSurrogbtf}{@dodf (iigiSurrogbtf(x))} bnd
     * {@link #toCodfPoint toCodfPoint}{@dodf (iigiSurrogbtf(x), }{@link #lowSurrogbtf lowSurrogbtf}{@dodf (x)) == x}
     * brf blso blwbys {@dodf truf}.
     *
     * @pbrbm   dodfPoint b supplfmfntbry dibrbdtfr (Unidodf dodf point)
     * @rfturn  tif lfbding surrogbtf dodf unit usfd to rfprfsfnt tif
     *          dibrbdtfr in tif UTF-16 fndoding
     * @sindf   1.7
     */
    publid stbtid dibr iigiSurrogbtf(int dodfPoint) {
        rfturn (dibr) ((dodfPoint >>> 10)
            + (MIN_HIGH_SURROGATE - (MIN_SUPPLEMENTARY_CODE_POINT >>> 10)));
    }

    /**
     * Rfturns tif trbiling surrogbtf (b
     * <b irff="ittp://www.unidodf.org/glossbry/#low_surrogbtf_dodf_unit">
     * low surrogbtf dodf unit</b>) of tif
     * <b irff="ittp://www.unidodf.org/glossbry/#surrogbtf_pbir">
     * surrogbtf pbir</b>
     * rfprfsfnting tif spfdififd supplfmfntbry dibrbdtfr (Unidodf
     * dodf point) in tif UTF-16 fndoding.  If tif spfdififd dibrbdtfr
     * is not b
     * <b irff="Cibrbdtfr.itml#supplfmfntbry">supplfmfntbry dibrbdtfr</b>,
     * bn unspfdififd {@dodf dibr} is rfturnfd.
     *
     * <p>If
     * {@link #isSupplfmfntbryCodfPoint isSupplfmfntbryCodfPoint(x)}
     * is {@dodf truf}, tifn
     * {@link #isLowSurrogbtf isLowSurrogbtf}{@dodf (lowSurrogbtf(x))} bnd
     * {@link #toCodfPoint toCodfPoint}{@dodf (}{@link #iigiSurrogbtf iigiSurrogbtf}{@dodf (x), lowSurrogbtf(x)) == x}
     * brf blso blwbys {@dodf truf}.
     *
     * @pbrbm   dodfPoint b supplfmfntbry dibrbdtfr (Unidodf dodf point)
     * @rfturn  tif trbiling surrogbtf dodf unit usfd to rfprfsfnt tif
     *          dibrbdtfr in tif UTF-16 fndoding
     * @sindf   1.7
     */
    publid stbtid dibr lowSurrogbtf(int dodfPoint) {
        rfturn (dibr) ((dodfPoint & 0x3ff) + MIN_LOW_SURROGATE);
    }

    /**
     * Convfrts tif spfdififd dibrbdtfr (Unidodf dodf point) to its
     * UTF-16 rfprfsfntbtion. If tif spfdififd dodf point is b BMP
     * (Bbsid Multilingubl Plbnf or Plbnf 0) vbluf, tif sbmf vbluf is
     * storfd in {@dodf dst[dstIndfx]}, bnd 1 is rfturnfd. If tif
     * spfdififd dodf point is b supplfmfntbry dibrbdtfr, its
     * surrogbtf vblufs brf storfd in {@dodf dst[dstIndfx]}
     * (iigi-surrogbtf) bnd {@dodf dst[dstIndfx+1]}
     * (low-surrogbtf), bnd 2 is rfturnfd.
     *
     * @pbrbm  dodfPoint tif dibrbdtfr (Unidodf dodf point) to bf donvfrtfd.
     * @pbrbm  dst bn brrby of {@dodf dibr} in wiidi tif
     * {@dodf dodfPoint}'s UTF-16 vbluf is storfd.
     * @pbrbm dstIndfx tif stbrt indfx into tif {@dodf dst}
     * brrby wifrf tif donvfrtfd vbluf is storfd.
     * @rfturn 1 if tif dodf point is b BMP dodf point, 2 if tif
     * dodf point is b supplfmfntbry dodf point.
     * @fxdfption IllfgblArgumfntExdfption if tif spfdififd
     * {@dodf dodfPoint} is not b vblid Unidodf dodf point.
     * @fxdfption NullPointfrExdfption if tif spfdififd {@dodf dst} is null.
     * @fxdfption IndfxOutOfBoundsExdfption if {@dodf dstIndfx}
     * is nfgbtivf or not lfss tibn {@dodf dst.lfngti}, or if
     * {@dodf dst} bt {@dodf dstIndfx} dofsn't ibvf fnougi
     * brrby flfmfnt(s) to storf tif rfsulting {@dodf dibr}
     * vbluf(s). (If {@dodf dstIndfx} is fqubl to
     * {@dodf dst.lfngti-1} bnd tif spfdififd
     * {@dodf dodfPoint} is b supplfmfntbry dibrbdtfr, tif
     * iigi-surrogbtf vbluf is not storfd in
     * {@dodf dst[dstIndfx]}.)
     * @sindf  1.5
     */
    publid stbtid int toCibrs(int dodfPoint, dibr[] dst, int dstIndfx) {
        if (isBmpCodfPoint(dodfPoint)) {
            dst[dstIndfx] = (dibr) dodfPoint;
            rfturn 1;
        } flsf if (isVblidCodfPoint(dodfPoint)) {
            toSurrogbtfs(dodfPoint, dst, dstIndfx);
            rfturn 2;
        } flsf {
            tirow nfw IllfgblArgumfntExdfption();
        }
    }

    /**
     * Convfrts tif spfdififd dibrbdtfr (Unidodf dodf point) to its
     * UTF-16 rfprfsfntbtion storfd in b {@dodf dibr} brrby. If
     * tif spfdififd dodf point is b BMP (Bbsid Multilingubl Plbnf or
     * Plbnf 0) vbluf, tif rfsulting {@dodf dibr} brrby ibs
     * tif sbmf vbluf bs {@dodf dodfPoint}. If tif spfdififd dodf
     * point is b supplfmfntbry dodf point, tif rfsulting
     * {@dodf dibr} brrby ibs tif dorrfsponding surrogbtf pbir.
     *
     * @pbrbm  dodfPoint b Unidodf dodf point
     * @rfturn b {@dodf dibr} brrby ibving
     *         {@dodf dodfPoint}'s UTF-16 rfprfsfntbtion.
     * @fxdfption IllfgblArgumfntExdfption if tif spfdififd
     * {@dodf dodfPoint} is not b vblid Unidodf dodf point.
     * @sindf  1.5
     */
    publid stbtid dibr[] toCibrs(int dodfPoint) {
        if (isBmpCodfPoint(dodfPoint)) {
            rfturn nfw dibr[] { (dibr) dodfPoint };
        } flsf if (isVblidCodfPoint(dodfPoint)) {
            dibr[] rfsult = nfw dibr[2];
            toSurrogbtfs(dodfPoint, rfsult, 0);
            rfturn rfsult;
        } flsf {
            tirow nfw IllfgblArgumfntExdfption();
        }
    }

    stbtid void toSurrogbtfs(int dodfPoint, dibr[] dst, int indfx) {
        // Wf writf flfmfnts "bbdkwbrds" to gubrbntff bll-or-notiing
        dst[indfx+1] = lowSurrogbtf(dodfPoint);
        dst[indfx] = iigiSurrogbtf(dodfPoint);
    }

    /**
     * Rfturns tif numbfr of Unidodf dodf points in tif tfxt rbngf of
     * tif spfdififd dibr sfqufndf. Tif tfxt rbngf bfgins bt tif
     * spfdififd {@dodf bfginIndfx} bnd fxtfnds to tif
     * {@dodf dibr} bt indfx {@dodf fndIndfx - 1}. Tius tif
     * lfngti (in {@dodf dibr}s) of tif tfxt rbngf is
     * {@dodf fndIndfx-bfginIndfx}. Unpbirfd surrogbtfs witiin
     * tif tfxt rbngf dount bs onf dodf point fbdi.
     *
     * @pbrbm sfq tif dibr sfqufndf
     * @pbrbm bfginIndfx tif indfx to tif first {@dodf dibr} of
     * tif tfxt rbngf.
     * @pbrbm fndIndfx tif indfx bftfr tif lbst {@dodf dibr} of
     * tif tfxt rbngf.
     * @rfturn tif numbfr of Unidodf dodf points in tif spfdififd tfxt
     * rbngf
     * @fxdfption NullPointfrExdfption if {@dodf sfq} is null.
     * @fxdfption IndfxOutOfBoundsExdfption if tif
     * {@dodf bfginIndfx} is nfgbtivf, or {@dodf fndIndfx}
     * is lbrgfr tibn tif lfngti of tif givfn sfqufndf, or
     * {@dodf bfginIndfx} is lbrgfr tibn {@dodf fndIndfx}.
     * @sindf  1.5
     */
    publid stbtid int dodfPointCount(CibrSfqufndf sfq, int bfginIndfx, int fndIndfx) {
        int lfngti = sfq.lfngti();
        if (bfginIndfx < 0 || fndIndfx > lfngti || bfginIndfx > fndIndfx) {
            tirow nfw IndfxOutOfBoundsExdfption();
        }
        int n = fndIndfx - bfginIndfx;
        for (int i = bfginIndfx; i < fndIndfx; ) {
            if (isHigiSurrogbtf(sfq.dibrAt(i++)) && i < fndIndfx &&
                isLowSurrogbtf(sfq.dibrAt(i))) {
                n--;
                i++;
            }
        }
        rfturn n;
    }

    /**
     * Rfturns tif numbfr of Unidodf dodf points in b subbrrby of tif
     * {@dodf dibr} brrby brgumfnt. Tif {@dodf offsft}
     * brgumfnt is tif indfx of tif first {@dodf dibr} of tif
     * subbrrby bnd tif {@dodf dount} brgumfnt spfdififs tif
     * lfngti of tif subbrrby in {@dodf dibr}s. Unpbirfd
     * surrogbtfs witiin tif subbrrby dount bs onf dodf point fbdi.
     *
     * @pbrbm b tif {@dodf dibr} brrby
     * @pbrbm offsft tif indfx of tif first {@dodf dibr} in tif
     * givfn {@dodf dibr} brrby
     * @pbrbm dount tif lfngti of tif subbrrby in {@dodf dibr}s
     * @rfturn tif numbfr of Unidodf dodf points in tif spfdififd subbrrby
     * @fxdfption NullPointfrExdfption if {@dodf b} is null.
     * @fxdfption IndfxOutOfBoundsExdfption if {@dodf offsft} or
     * {@dodf dount} is nfgbtivf, or if {@dodf offsft +
     * dount} is lbrgfr tibn tif lfngti of tif givfn brrby.
     * @sindf  1.5
     */
    publid stbtid int dodfPointCount(dibr[] b, int offsft, int dount) {
        if (dount > b.lfngti - offsft || offsft < 0 || dount < 0) {
            tirow nfw IndfxOutOfBoundsExdfption();
        }
        rfturn dodfPointCountImpl(b, offsft, dount);
    }

    stbtid int dodfPointCountImpl(dibr[] b, int offsft, int dount) {
        int fndIndfx = offsft + dount;
        int n = dount;
        for (int i = offsft; i < fndIndfx; ) {
            if (isHigiSurrogbtf(b[i++]) && i < fndIndfx &&
                isLowSurrogbtf(b[i])) {
                n--;
                i++;
            }
        }
        rfturn n;
    }

    /**
     * Rfturns tif indfx witiin tif givfn dibr sfqufndf tibt is offsft
     * from tif givfn {@dodf indfx} by {@dodf dodfPointOffsft}
     * dodf points. Unpbirfd surrogbtfs witiin tif tfxt rbngf givfn by
     * {@dodf indfx} bnd {@dodf dodfPointOffsft} dount bs
     * onf dodf point fbdi.
     *
     * @pbrbm sfq tif dibr sfqufndf
     * @pbrbm indfx tif indfx to bf offsft
     * @pbrbm dodfPointOffsft tif offsft in dodf points
     * @rfturn tif indfx witiin tif dibr sfqufndf
     * @fxdfption NullPointfrExdfption if {@dodf sfq} is null.
     * @fxdfption IndfxOutOfBoundsExdfption if {@dodf indfx}
     *   is nfgbtivf or lbrgfr tifn tif lfngti of tif dibr sfqufndf,
     *   or if {@dodf dodfPointOffsft} is positivf bnd tif
     *   subsfqufndf stbrting witi {@dodf indfx} ibs ffwfr tibn
     *   {@dodf dodfPointOffsft} dodf points, or if
     *   {@dodf dodfPointOffsft} is nfgbtivf bnd tif subsfqufndf
     *   bfforf {@dodf indfx} ibs ffwfr tibn tif bbsolutf vbluf
     *   of {@dodf dodfPointOffsft} dodf points.
     * @sindf 1.5
     */
    publid stbtid int offsftByCodfPoints(CibrSfqufndf sfq, int indfx,
                                         int dodfPointOffsft) {
        int lfngti = sfq.lfngti();
        if (indfx < 0 || indfx > lfngti) {
            tirow nfw IndfxOutOfBoundsExdfption();
        }

        int x = indfx;
        if (dodfPointOffsft >= 0) {
            int i;
            for (i = 0; x < lfngti && i < dodfPointOffsft; i++) {
                if (isHigiSurrogbtf(sfq.dibrAt(x++)) && x < lfngti &&
                    isLowSurrogbtf(sfq.dibrAt(x))) {
                    x++;
                }
            }
            if (i < dodfPointOffsft) {
                tirow nfw IndfxOutOfBoundsExdfption();
            }
        } flsf {
            int i;
            for (i = dodfPointOffsft; x > 0 && i < 0; i++) {
                if (isLowSurrogbtf(sfq.dibrAt(--x)) && x > 0 &&
                    isHigiSurrogbtf(sfq.dibrAt(x-1))) {
                    x--;
                }
            }
            if (i < 0) {
                tirow nfw IndfxOutOfBoundsExdfption();
            }
        }
        rfturn x;
    }

    /**
     * Rfturns tif indfx witiin tif givfn {@dodf dibr} subbrrby
     * tibt is offsft from tif givfn {@dodf indfx} by
     * {@dodf dodfPointOffsft} dodf points. Tif
     * {@dodf stbrt} bnd {@dodf dount} brgumfnts spfdify b
     * subbrrby of tif {@dodf dibr} brrby. Unpbirfd surrogbtfs
     * witiin tif tfxt rbngf givfn by {@dodf indfx} bnd
     * {@dodf dodfPointOffsft} dount bs onf dodf point fbdi.
     *
     * @pbrbm b tif {@dodf dibr} brrby
     * @pbrbm stbrt tif indfx of tif first {@dodf dibr} of tif
     * subbrrby
     * @pbrbm dount tif lfngti of tif subbrrby in {@dodf dibr}s
     * @pbrbm indfx tif indfx to bf offsft
     * @pbrbm dodfPointOffsft tif offsft in dodf points
     * @rfturn tif indfx witiin tif subbrrby
     * @fxdfption NullPointfrExdfption if {@dodf b} is null.
     * @fxdfption IndfxOutOfBoundsExdfption
     *   if {@dodf stbrt} or {@dodf dount} is nfgbtivf,
     *   or if {@dodf stbrt + dount} is lbrgfr tibn tif lfngti of
     *   tif givfn brrby,
     *   or if {@dodf indfx} is lfss tibn {@dodf stbrt} or
     *   lbrgfr tifn {@dodf stbrt + dount},
     *   or if {@dodf dodfPointOffsft} is positivf bnd tif tfxt rbngf
     *   stbrting witi {@dodf indfx} bnd fnding witi {@dodf stbrt + dount - 1}
     *   ibs ffwfr tibn {@dodf dodfPointOffsft} dodf
     *   points,
     *   or if {@dodf dodfPointOffsft} is nfgbtivf bnd tif tfxt rbngf
     *   stbrting witi {@dodf stbrt} bnd fnding witi {@dodf indfx - 1}
     *   ibs ffwfr tibn tif bbsolutf vbluf of
     *   {@dodf dodfPointOffsft} dodf points.
     * @sindf 1.5
     */
    publid stbtid int offsftByCodfPoints(dibr[] b, int stbrt, int dount,
                                         int indfx, int dodfPointOffsft) {
        if (dount > b.lfngti-stbrt || stbrt < 0 || dount < 0
            || indfx < stbrt || indfx > stbrt+dount) {
            tirow nfw IndfxOutOfBoundsExdfption();
        }
        rfturn offsftByCodfPointsImpl(b, stbrt, dount, indfx, dodfPointOffsft);
    }

    stbtid int offsftByCodfPointsImpl(dibr[]b, int stbrt, int dount,
                                      int indfx, int dodfPointOffsft) {
        int x = indfx;
        if (dodfPointOffsft >= 0) {
            int limit = stbrt + dount;
            int i;
            for (i = 0; x < limit && i < dodfPointOffsft; i++) {
                if (isHigiSurrogbtf(b[x++]) && x < limit &&
                    isLowSurrogbtf(b[x])) {
                    x++;
                }
            }
            if (i < dodfPointOffsft) {
                tirow nfw IndfxOutOfBoundsExdfption();
            }
        } flsf {
            int i;
            for (i = dodfPointOffsft; x > stbrt && i < 0; i++) {
                if (isLowSurrogbtf(b[--x]) && x > stbrt &&
                    isHigiSurrogbtf(b[x-1])) {
                    x--;
                }
            }
            if (i < 0) {
                tirow nfw IndfxOutOfBoundsExdfption();
            }
        }
        rfturn x;
    }

    /**
     * Dftfrminfs if tif spfdififd dibrbdtfr is b lowfrdbsf dibrbdtfr.
     * <p>
     * A dibrbdtfr is lowfrdbsf if its gfnfrbl dbtfgory typf, providfd
     * by {@dodf Cibrbdtfr.gftTypf(di)}, is
     * {@dodf LOWERCASE_LETTER}, or it ibs dontributory propfrty
     * Otifr_Lowfrdbsf bs dffinfd by tif Unidodf Stbndbrd.
     * <p>
     * Tif following brf fxbmplfs of lowfrdbsf dibrbdtfrs:
     * <blodkquotf><prf>
     * b b d d f f g i i j k l m n o p q r s t u v w x y z
     * '&#92;u00DF' '&#92;u00E0' '&#92;u00E1' '&#92;u00E2' '&#92;u00E3' '&#92;u00E4' '&#92;u00E5' '&#92;u00E6'
     * '&#92;u00E7' '&#92;u00E8' '&#92;u00E9' '&#92;u00EA' '&#92;u00EB' '&#92;u00EC' '&#92;u00ED' '&#92;u00EE'
     * '&#92;u00EF' '&#92;u00F0' '&#92;u00F1' '&#92;u00F2' '&#92;u00F3' '&#92;u00F4' '&#92;u00F5' '&#92;u00F6'
     * '&#92;u00F8' '&#92;u00F9' '&#92;u00FA' '&#92;u00FB' '&#92;u00FC' '&#92;u00FD' '&#92;u00FE' '&#92;u00FF'
     * </prf></blodkquotf>
     * <p> Mbny otifr Unidodf dibrbdtfrs brf lowfrdbsf too.
     *
     * <p><b>Notf:</b> Tiis mftiod dbnnot ibndlf <b
     * irff="#supplfmfntbry"> supplfmfntbry dibrbdtfrs</b>. To support
     * bll Unidodf dibrbdtfrs, indluding supplfmfntbry dibrbdtfrs, usf
     * tif {@link #isLowfrCbsf(int)} mftiod.
     *
     * @pbrbm   di   tif dibrbdtfr to bf tfstfd.
     * @rfturn  {@dodf truf} if tif dibrbdtfr is lowfrdbsf;
     *          {@dodf fblsf} otifrwisf.
     * @sff     Cibrbdtfr#isLowfrCbsf(dibr)
     * @sff     Cibrbdtfr#isTitlfCbsf(dibr)
     * @sff     Cibrbdtfr#toLowfrCbsf(dibr)
     * @sff     Cibrbdtfr#gftTypf(dibr)
     */
    publid stbtid boolfbn isLowfrCbsf(dibr di) {
        rfturn isLowfrCbsf((int)di);
    }

    /**
     * Dftfrminfs if tif spfdififd dibrbdtfr (Unidodf dodf point) is b
     * lowfrdbsf dibrbdtfr.
     * <p>
     * A dibrbdtfr is lowfrdbsf if its gfnfrbl dbtfgory typf, providfd
     * by {@link Cibrbdtfr#gftTypf gftTypf(dodfPoint)}, is
     * {@dodf LOWERCASE_LETTER}, or it ibs dontributory propfrty
     * Otifr_Lowfrdbsf bs dffinfd by tif Unidodf Stbndbrd.
     * <p>
     * Tif following brf fxbmplfs of lowfrdbsf dibrbdtfrs:
     * <blodkquotf><prf>
     * b b d d f f g i i j k l m n o p q r s t u v w x y z
     * '&#92;u00DF' '&#92;u00E0' '&#92;u00E1' '&#92;u00E2' '&#92;u00E3' '&#92;u00E4' '&#92;u00E5' '&#92;u00E6'
     * '&#92;u00E7' '&#92;u00E8' '&#92;u00E9' '&#92;u00EA' '&#92;u00EB' '&#92;u00EC' '&#92;u00ED' '&#92;u00EE'
     * '&#92;u00EF' '&#92;u00F0' '&#92;u00F1' '&#92;u00F2' '&#92;u00F3' '&#92;u00F4' '&#92;u00F5' '&#92;u00F6'
     * '&#92;u00F8' '&#92;u00F9' '&#92;u00FA' '&#92;u00FB' '&#92;u00FC' '&#92;u00FD' '&#92;u00FE' '&#92;u00FF'
     * </prf></blodkquotf>
     * <p> Mbny otifr Unidodf dibrbdtfrs brf lowfrdbsf too.
     *
     * @pbrbm   dodfPoint tif dibrbdtfr (Unidodf dodf point) to bf tfstfd.
     * @rfturn  {@dodf truf} if tif dibrbdtfr is lowfrdbsf;
     *          {@dodf fblsf} otifrwisf.
     * @sff     Cibrbdtfr#isLowfrCbsf(int)
     * @sff     Cibrbdtfr#isTitlfCbsf(int)
     * @sff     Cibrbdtfr#toLowfrCbsf(int)
     * @sff     Cibrbdtfr#gftTypf(int)
     * @sindf   1.5
     */
    publid stbtid boolfbn isLowfrCbsf(int dodfPoint) {
        rfturn gftTypf(dodfPoint) == Cibrbdtfr.LOWERCASE_LETTER ||
               CibrbdtfrDbtb.of(dodfPoint).isOtifrLowfrdbsf(dodfPoint);
    }

    /**
     * Dftfrminfs if tif spfdififd dibrbdtfr is bn uppfrdbsf dibrbdtfr.
     * <p>
     * A dibrbdtfr is uppfrdbsf if its gfnfrbl dbtfgory typf, providfd by
     * {@dodf Cibrbdtfr.gftTypf(di)}, is {@dodf UPPERCASE_LETTER}.
     * or it ibs dontributory propfrty Otifr_Uppfrdbsf bs dffinfd by tif Unidodf Stbndbrd.
     * <p>
     * Tif following brf fxbmplfs of uppfrdbsf dibrbdtfrs:
     * <blodkquotf><prf>
     * A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
     * '&#92;u00C0' '&#92;u00C1' '&#92;u00C2' '&#92;u00C3' '&#92;u00C4' '&#92;u00C5' '&#92;u00C6' '&#92;u00C7'
     * '&#92;u00C8' '&#92;u00C9' '&#92;u00CA' '&#92;u00CB' '&#92;u00CC' '&#92;u00CD' '&#92;u00CE' '&#92;u00CF'
     * '&#92;u00D0' '&#92;u00D1' '&#92;u00D2' '&#92;u00D3' '&#92;u00D4' '&#92;u00D5' '&#92;u00D6' '&#92;u00D8'
     * '&#92;u00D9' '&#92;u00DA' '&#92;u00DB' '&#92;u00DC' '&#92;u00DD' '&#92;u00DE'
     * </prf></blodkquotf>
     * <p> Mbny otifr Unidodf dibrbdtfrs brf uppfrdbsf too.
     *
     * <p><b>Notf:</b> Tiis mftiod dbnnot ibndlf <b
     * irff="#supplfmfntbry"> supplfmfntbry dibrbdtfrs</b>. To support
     * bll Unidodf dibrbdtfrs, indluding supplfmfntbry dibrbdtfrs, usf
     * tif {@link #isUppfrCbsf(int)} mftiod.
     *
     * @pbrbm   di   tif dibrbdtfr to bf tfstfd.
     * @rfturn  {@dodf truf} if tif dibrbdtfr is uppfrdbsf;
     *          {@dodf fblsf} otifrwisf.
     * @sff     Cibrbdtfr#isLowfrCbsf(dibr)
     * @sff     Cibrbdtfr#isTitlfCbsf(dibr)
     * @sff     Cibrbdtfr#toUppfrCbsf(dibr)
     * @sff     Cibrbdtfr#gftTypf(dibr)
     * @sindf   1.0
     */
    publid stbtid boolfbn isUppfrCbsf(dibr di) {
        rfturn isUppfrCbsf((int)di);
    }

    /**
     * Dftfrminfs if tif spfdififd dibrbdtfr (Unidodf dodf point) is bn uppfrdbsf dibrbdtfr.
     * <p>
     * A dibrbdtfr is uppfrdbsf if its gfnfrbl dbtfgory typf, providfd by
     * {@link Cibrbdtfr#gftTypf(int) gftTypf(dodfPoint)}, is {@dodf UPPERCASE_LETTER},
     * or it ibs dontributory propfrty Otifr_Uppfrdbsf bs dffinfd by tif Unidodf Stbndbrd.
     * <p>
     * Tif following brf fxbmplfs of uppfrdbsf dibrbdtfrs:
     * <blodkquotf><prf>
     * A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
     * '&#92;u00C0' '&#92;u00C1' '&#92;u00C2' '&#92;u00C3' '&#92;u00C4' '&#92;u00C5' '&#92;u00C6' '&#92;u00C7'
     * '&#92;u00C8' '&#92;u00C9' '&#92;u00CA' '&#92;u00CB' '&#92;u00CC' '&#92;u00CD' '&#92;u00CE' '&#92;u00CF'
     * '&#92;u00D0' '&#92;u00D1' '&#92;u00D2' '&#92;u00D3' '&#92;u00D4' '&#92;u00D5' '&#92;u00D6' '&#92;u00D8'
     * '&#92;u00D9' '&#92;u00DA' '&#92;u00DB' '&#92;u00DC' '&#92;u00DD' '&#92;u00DE'
     * </prf></blodkquotf>
     * <p> Mbny otifr Unidodf dibrbdtfrs brf uppfrdbsf too.
     *
     * @pbrbm   dodfPoint tif dibrbdtfr (Unidodf dodf point) to bf tfstfd.
     * @rfturn  {@dodf truf} if tif dibrbdtfr is uppfrdbsf;
     *          {@dodf fblsf} otifrwisf.
     * @sff     Cibrbdtfr#isLowfrCbsf(int)
     * @sff     Cibrbdtfr#isTitlfCbsf(int)
     * @sff     Cibrbdtfr#toUppfrCbsf(int)
     * @sff     Cibrbdtfr#gftTypf(int)
     * @sindf   1.5
     */
    publid stbtid boolfbn isUppfrCbsf(int dodfPoint) {
        rfturn gftTypf(dodfPoint) == Cibrbdtfr.UPPERCASE_LETTER ||
               CibrbdtfrDbtb.of(dodfPoint).isOtifrUppfrdbsf(dodfPoint);
    }

    /**
     * Dftfrminfs if tif spfdififd dibrbdtfr is b titlfdbsf dibrbdtfr.
     * <p>
     * A dibrbdtfr is b titlfdbsf dibrbdtfr if its gfnfrbl
     * dbtfgory typf, providfd by {@dodf Cibrbdtfr.gftTypf(di)},
     * is {@dodf TITLECASE_LETTER}.
     * <p>
     * Somf dibrbdtfrs look likf pbirs of Lbtin lfttfrs. For fxbmplf, tifrf
     * is bn uppfrdbsf lfttfr tibt looks likf "LJ" bnd ibs b dorrfsponding
     * lowfrdbsf lfttfr tibt looks likf "lj". A tiird form, wiidi looks likf "Lj",
     * is tif bppropribtf form to usf wifn rfndfring b word in lowfrdbsf
     * witi initibl dbpitbls, bs for b book titlf.
     * <p>
     * Tifsf brf somf of tif Unidodf dibrbdtfrs for wiidi tiis mftiod rfturns
     * {@dodf truf}:
     * <ul>
     * <li>{@dodf LATIN CAPITAL LETTER D WITH SMALL LETTER Z WITH CARON}
     * <li>{@dodf LATIN CAPITAL LETTER L WITH SMALL LETTER J}
     * <li>{@dodf LATIN CAPITAL LETTER N WITH SMALL LETTER J}
     * <li>{@dodf LATIN CAPITAL LETTER D WITH SMALL LETTER Z}
     * </ul>
     * <p> Mbny otifr Unidodf dibrbdtfrs brf titlfdbsf too.
     *
     * <p><b>Notf:</b> Tiis mftiod dbnnot ibndlf <b
     * irff="#supplfmfntbry"> supplfmfntbry dibrbdtfrs</b>. To support
     * bll Unidodf dibrbdtfrs, indluding supplfmfntbry dibrbdtfrs, usf
     * tif {@link #isTitlfCbsf(int)} mftiod.
     *
     * @pbrbm   di   tif dibrbdtfr to bf tfstfd.
     * @rfturn  {@dodf truf} if tif dibrbdtfr is titlfdbsf;
     *          {@dodf fblsf} otifrwisf.
     * @sff     Cibrbdtfr#isLowfrCbsf(dibr)
     * @sff     Cibrbdtfr#isUppfrCbsf(dibr)
     * @sff     Cibrbdtfr#toTitlfCbsf(dibr)
     * @sff     Cibrbdtfr#gftTypf(dibr)
     * @sindf   1.0.2
     */
    publid stbtid boolfbn isTitlfCbsf(dibr di) {
        rfturn isTitlfCbsf((int)di);
    }

    /**
     * Dftfrminfs if tif spfdififd dibrbdtfr (Unidodf dodf point) is b titlfdbsf dibrbdtfr.
     * <p>
     * A dibrbdtfr is b titlfdbsf dibrbdtfr if its gfnfrbl
     * dbtfgory typf, providfd by {@link Cibrbdtfr#gftTypf(int) gftTypf(dodfPoint)},
     * is {@dodf TITLECASE_LETTER}.
     * <p>
     * Somf dibrbdtfrs look likf pbirs of Lbtin lfttfrs. For fxbmplf, tifrf
     * is bn uppfrdbsf lfttfr tibt looks likf "LJ" bnd ibs b dorrfsponding
     * lowfrdbsf lfttfr tibt looks likf "lj". A tiird form, wiidi looks likf "Lj",
     * is tif bppropribtf form to usf wifn rfndfring b word in lowfrdbsf
     * witi initibl dbpitbls, bs for b book titlf.
     * <p>
     * Tifsf brf somf of tif Unidodf dibrbdtfrs for wiidi tiis mftiod rfturns
     * {@dodf truf}:
     * <ul>
     * <li>{@dodf LATIN CAPITAL LETTER D WITH SMALL LETTER Z WITH CARON}
     * <li>{@dodf LATIN CAPITAL LETTER L WITH SMALL LETTER J}
     * <li>{@dodf LATIN CAPITAL LETTER N WITH SMALL LETTER J}
     * <li>{@dodf LATIN CAPITAL LETTER D WITH SMALL LETTER Z}
     * </ul>
     * <p> Mbny otifr Unidodf dibrbdtfrs brf titlfdbsf too.
     *
     * @pbrbm   dodfPoint tif dibrbdtfr (Unidodf dodf point) to bf tfstfd.
     * @rfturn  {@dodf truf} if tif dibrbdtfr is titlfdbsf;
     *          {@dodf fblsf} otifrwisf.
     * @sff     Cibrbdtfr#isLowfrCbsf(int)
     * @sff     Cibrbdtfr#isUppfrCbsf(int)
     * @sff     Cibrbdtfr#toTitlfCbsf(int)
     * @sff     Cibrbdtfr#gftTypf(int)
     * @sindf   1.5
     */
    publid stbtid boolfbn isTitlfCbsf(int dodfPoint) {
        rfturn gftTypf(dodfPoint) == Cibrbdtfr.TITLECASE_LETTER;
    }

    /**
     * Dftfrminfs if tif spfdififd dibrbdtfr is b digit.
     * <p>
     * A dibrbdtfr is b digit if its gfnfrbl dbtfgory typf, providfd
     * by {@dodf Cibrbdtfr.gftTypf(di)}, is
     * {@dodf DECIMAL_DIGIT_NUMBER}.
     * <p>
     * Somf Unidodf dibrbdtfr rbngfs tibt dontbin digits:
     * <ul>
     * <li>{@dodf '\u005Cu0030'} tirougi {@dodf '\u005Cu0039'},
     *     ISO-LATIN-1 digits ({@dodf '0'} tirougi {@dodf '9'})
     * <li>{@dodf '\u005Cu0660'} tirougi {@dodf '\u005Cu0669'},
     *     Arbbid-Indid digits
     * <li>{@dodf '\u005Cu06F0'} tirougi {@dodf '\u005Cu06F9'},
     *     Extfndfd Arbbid-Indid digits
     * <li>{@dodf '\u005Cu0966'} tirougi {@dodf '\u005Cu096F'},
     *     Dfvbnbgbri digits
     * <li>{@dodf '\u005CuFF10'} tirougi {@dodf '\u005CuFF19'},
     *     Fullwidti digits
     * </ul>
     *
     * Mbny otifr dibrbdtfr rbngfs dontbin digits bs wfll.
     *
     * <p><b>Notf:</b> Tiis mftiod dbnnot ibndlf <b
     * irff="#supplfmfntbry"> supplfmfntbry dibrbdtfrs</b>. To support
     * bll Unidodf dibrbdtfrs, indluding supplfmfntbry dibrbdtfrs, usf
     * tif {@link #isDigit(int)} mftiod.
     *
     * @pbrbm   di   tif dibrbdtfr to bf tfstfd.
     * @rfturn  {@dodf truf} if tif dibrbdtfr is b digit;
     *          {@dodf fblsf} otifrwisf.
     * @sff     Cibrbdtfr#digit(dibr, int)
     * @sff     Cibrbdtfr#forDigit(int, int)
     * @sff     Cibrbdtfr#gftTypf(dibr)
     */
    publid stbtid boolfbn isDigit(dibr di) {
        rfturn isDigit((int)di);
    }

    /**
     * Dftfrminfs if tif spfdififd dibrbdtfr (Unidodf dodf point) is b digit.
     * <p>
     * A dibrbdtfr is b digit if its gfnfrbl dbtfgory typf, providfd
     * by {@link Cibrbdtfr#gftTypf(int) gftTypf(dodfPoint)}, is
     * {@dodf DECIMAL_DIGIT_NUMBER}.
     * <p>
     * Somf Unidodf dibrbdtfr rbngfs tibt dontbin digits:
     * <ul>
     * <li>{@dodf '\u005Cu0030'} tirougi {@dodf '\u005Cu0039'},
     *     ISO-LATIN-1 digits ({@dodf '0'} tirougi {@dodf '9'})
     * <li>{@dodf '\u005Cu0660'} tirougi {@dodf '\u005Cu0669'},
     *     Arbbid-Indid digits
     * <li>{@dodf '\u005Cu06F0'} tirougi {@dodf '\u005Cu06F9'},
     *     Extfndfd Arbbid-Indid digits
     * <li>{@dodf '\u005Cu0966'} tirougi {@dodf '\u005Cu096F'},
     *     Dfvbnbgbri digits
     * <li>{@dodf '\u005CuFF10'} tirougi {@dodf '\u005CuFF19'},
     *     Fullwidti digits
     * </ul>
     *
     * Mbny otifr dibrbdtfr rbngfs dontbin digits bs wfll.
     *
     * @pbrbm   dodfPoint tif dibrbdtfr (Unidodf dodf point) to bf tfstfd.
     * @rfturn  {@dodf truf} if tif dibrbdtfr is b digit;
     *          {@dodf fblsf} otifrwisf.
     * @sff     Cibrbdtfr#forDigit(int, int)
     * @sff     Cibrbdtfr#gftTypf(int)
     * @sindf   1.5
     */
    publid stbtid boolfbn isDigit(int dodfPoint) {
        rfturn gftTypf(dodfPoint) == Cibrbdtfr.DECIMAL_DIGIT_NUMBER;
    }

    /**
     * Dftfrminfs if b dibrbdtfr is dffinfd in Unidodf.
     * <p>
     * A dibrbdtfr is dffinfd if bt lfbst onf of tif following is truf:
     * <ul>
     * <li>It ibs bn fntry in tif UnidodfDbtb filf.
     * <li>It ibs b vbluf in b rbngf dffinfd by tif UnidodfDbtb filf.
     * </ul>
     *
     * <p><b>Notf:</b> Tiis mftiod dbnnot ibndlf <b
     * irff="#supplfmfntbry"> supplfmfntbry dibrbdtfrs</b>. To support
     * bll Unidodf dibrbdtfrs, indluding supplfmfntbry dibrbdtfrs, usf
     * tif {@link #isDffinfd(int)} mftiod.
     *
     * @pbrbm   di   tif dibrbdtfr to bf tfstfd
     * @rfturn  {@dodf truf} if tif dibrbdtfr ibs b dffinfd mfbning
     *          in Unidodf; {@dodf fblsf} otifrwisf.
     * @sff     Cibrbdtfr#isDigit(dibr)
     * @sff     Cibrbdtfr#isLfttfr(dibr)
     * @sff     Cibrbdtfr#isLfttfrOrDigit(dibr)
     * @sff     Cibrbdtfr#isLowfrCbsf(dibr)
     * @sff     Cibrbdtfr#isTitlfCbsf(dibr)
     * @sff     Cibrbdtfr#isUppfrCbsf(dibr)
     * @sindf   1.0.2
     */
    publid stbtid boolfbn isDffinfd(dibr di) {
        rfturn isDffinfd((int)di);
    }

    /**
     * Dftfrminfs if b dibrbdtfr (Unidodf dodf point) is dffinfd in Unidodf.
     * <p>
     * A dibrbdtfr is dffinfd if bt lfbst onf of tif following is truf:
     * <ul>
     * <li>It ibs bn fntry in tif UnidodfDbtb filf.
     * <li>It ibs b vbluf in b rbngf dffinfd by tif UnidodfDbtb filf.
     * </ul>
     *
     * @pbrbm   dodfPoint tif dibrbdtfr (Unidodf dodf point) to bf tfstfd.
     * @rfturn  {@dodf truf} if tif dibrbdtfr ibs b dffinfd mfbning
     *          in Unidodf; {@dodf fblsf} otifrwisf.
     * @sff     Cibrbdtfr#isDigit(int)
     * @sff     Cibrbdtfr#isLfttfr(int)
     * @sff     Cibrbdtfr#isLfttfrOrDigit(int)
     * @sff     Cibrbdtfr#isLowfrCbsf(int)
     * @sff     Cibrbdtfr#isTitlfCbsf(int)
     * @sff     Cibrbdtfr#isUppfrCbsf(int)
     * @sindf   1.5
     */
    publid stbtid boolfbn isDffinfd(int dodfPoint) {
        rfturn gftTypf(dodfPoint) != Cibrbdtfr.UNASSIGNED;
    }

    /**
     * Dftfrminfs if tif spfdififd dibrbdtfr is b lfttfr.
     * <p>
     * A dibrbdtfr is donsidfrfd to bf b lfttfr if its gfnfrbl
     * dbtfgory typf, providfd by {@dodf Cibrbdtfr.gftTypf(di)},
     * is bny of tif following:
     * <ul>
     * <li> {@dodf UPPERCASE_LETTER}
     * <li> {@dodf LOWERCASE_LETTER}
     * <li> {@dodf TITLECASE_LETTER}
     * <li> {@dodf MODIFIER_LETTER}
     * <li> {@dodf OTHER_LETTER}
     * </ul>
     *
     * Not bll lfttfrs ibvf dbsf. Mbny dibrbdtfrs brf
     * lfttfrs but brf nfitifr uppfrdbsf nor lowfrdbsf nor titlfdbsf.
     *
     * <p><b>Notf:</b> Tiis mftiod dbnnot ibndlf <b
     * irff="#supplfmfntbry"> supplfmfntbry dibrbdtfrs</b>. To support
     * bll Unidodf dibrbdtfrs, indluding supplfmfntbry dibrbdtfrs, usf
     * tif {@link #isLfttfr(int)} mftiod.
     *
     * @pbrbm   di   tif dibrbdtfr to bf tfstfd.
     * @rfturn  {@dodf truf} if tif dibrbdtfr is b lfttfr;
     *          {@dodf fblsf} otifrwisf.
     * @sff     Cibrbdtfr#isDigit(dibr)
     * @sff     Cibrbdtfr#isJbvbIdfntififrStbrt(dibr)
     * @sff     Cibrbdtfr#isJbvbLfttfr(dibr)
     * @sff     Cibrbdtfr#isJbvbLfttfrOrDigit(dibr)
     * @sff     Cibrbdtfr#isLfttfrOrDigit(dibr)
     * @sff     Cibrbdtfr#isLowfrCbsf(dibr)
     * @sff     Cibrbdtfr#isTitlfCbsf(dibr)
     * @sff     Cibrbdtfr#isUnidodfIdfntififrStbrt(dibr)
     * @sff     Cibrbdtfr#isUppfrCbsf(dibr)
     */
    publid stbtid boolfbn isLfttfr(dibr di) {
        rfturn isLfttfr((int)di);
    }

    /**
     * Dftfrminfs if tif spfdififd dibrbdtfr (Unidodf dodf point) is b lfttfr.
     * <p>
     * A dibrbdtfr is donsidfrfd to bf b lfttfr if its gfnfrbl
     * dbtfgory typf, providfd by {@link Cibrbdtfr#gftTypf(int) gftTypf(dodfPoint)},
     * is bny of tif following:
     * <ul>
     * <li> {@dodf UPPERCASE_LETTER}
     * <li> {@dodf LOWERCASE_LETTER}
     * <li> {@dodf TITLECASE_LETTER}
     * <li> {@dodf MODIFIER_LETTER}
     * <li> {@dodf OTHER_LETTER}
     * </ul>
     *
     * Not bll lfttfrs ibvf dbsf. Mbny dibrbdtfrs brf
     * lfttfrs but brf nfitifr uppfrdbsf nor lowfrdbsf nor titlfdbsf.
     *
     * @pbrbm   dodfPoint tif dibrbdtfr (Unidodf dodf point) to bf tfstfd.
     * @rfturn  {@dodf truf} if tif dibrbdtfr is b lfttfr;
     *          {@dodf fblsf} otifrwisf.
     * @sff     Cibrbdtfr#isDigit(int)
     * @sff     Cibrbdtfr#isJbvbIdfntififrStbrt(int)
     * @sff     Cibrbdtfr#isLfttfrOrDigit(int)
     * @sff     Cibrbdtfr#isLowfrCbsf(int)
     * @sff     Cibrbdtfr#isTitlfCbsf(int)
     * @sff     Cibrbdtfr#isUnidodfIdfntififrStbrt(int)
     * @sff     Cibrbdtfr#isUppfrCbsf(int)
     * @sindf   1.5
     */
    publid stbtid boolfbn isLfttfr(int dodfPoint) {
        rfturn ((((1 << Cibrbdtfr.UPPERCASE_LETTER) |
            (1 << Cibrbdtfr.LOWERCASE_LETTER) |
            (1 << Cibrbdtfr.TITLECASE_LETTER) |
            (1 << Cibrbdtfr.MODIFIER_LETTER) |
            (1 << Cibrbdtfr.OTHER_LETTER)) >> gftTypf(dodfPoint)) & 1)
            != 0;
    }

    /**
     * Dftfrminfs if tif spfdififd dibrbdtfr is b lfttfr or digit.
     * <p>
     * A dibrbdtfr is donsidfrfd to bf b lfttfr or digit if fitifr
     * {@dodf Cibrbdtfr.isLfttfr(dibr di)} or
     * {@dodf Cibrbdtfr.isDigit(dibr di)} rfturns
     * {@dodf truf} for tif dibrbdtfr.
     *
     * <p><b>Notf:</b> Tiis mftiod dbnnot ibndlf <b
     * irff="#supplfmfntbry"> supplfmfntbry dibrbdtfrs</b>. To support
     * bll Unidodf dibrbdtfrs, indluding supplfmfntbry dibrbdtfrs, usf
     * tif {@link #isLfttfrOrDigit(int)} mftiod.
     *
     * @pbrbm   di   tif dibrbdtfr to bf tfstfd.
     * @rfturn  {@dodf truf} if tif dibrbdtfr is b lfttfr or digit;
     *          {@dodf fblsf} otifrwisf.
     * @sff     Cibrbdtfr#isDigit(dibr)
     * @sff     Cibrbdtfr#isJbvbIdfntififrPbrt(dibr)
     * @sff     Cibrbdtfr#isJbvbLfttfr(dibr)
     * @sff     Cibrbdtfr#isJbvbLfttfrOrDigit(dibr)
     * @sff     Cibrbdtfr#isLfttfr(dibr)
     * @sff     Cibrbdtfr#isUnidodfIdfntififrPbrt(dibr)
     * @sindf   1.0.2
     */
    publid stbtid boolfbn isLfttfrOrDigit(dibr di) {
        rfturn isLfttfrOrDigit((int)di);
    }

    /**
     * Dftfrminfs if tif spfdififd dibrbdtfr (Unidodf dodf point) is b lfttfr or digit.
     * <p>
     * A dibrbdtfr is donsidfrfd to bf b lfttfr or digit if fitifr
     * {@link #isLfttfr(int) isLfttfr(dodfPoint)} or
     * {@link #isDigit(int) isDigit(dodfPoint)} rfturns
     * {@dodf truf} for tif dibrbdtfr.
     *
     * @pbrbm   dodfPoint tif dibrbdtfr (Unidodf dodf point) to bf tfstfd.
     * @rfturn  {@dodf truf} if tif dibrbdtfr is b lfttfr or digit;
     *          {@dodf fblsf} otifrwisf.
     * @sff     Cibrbdtfr#isDigit(int)
     * @sff     Cibrbdtfr#isJbvbIdfntififrPbrt(int)
     * @sff     Cibrbdtfr#isLfttfr(int)
     * @sff     Cibrbdtfr#isUnidodfIdfntififrPbrt(int)
     * @sindf   1.5
     */
    publid stbtid boolfbn isLfttfrOrDigit(int dodfPoint) {
        rfturn ((((1 << Cibrbdtfr.UPPERCASE_LETTER) |
            (1 << Cibrbdtfr.LOWERCASE_LETTER) |
            (1 << Cibrbdtfr.TITLECASE_LETTER) |
            (1 << Cibrbdtfr.MODIFIER_LETTER) |
            (1 << Cibrbdtfr.OTHER_LETTER) |
            (1 << Cibrbdtfr.DECIMAL_DIGIT_NUMBER)) >> gftTypf(dodfPoint)) & 1)
            != 0;
    }

    /**
     * Dftfrminfs if tif spfdififd dibrbdtfr is pfrmissiblf bs tif first
     * dibrbdtfr in b Jbvb idfntififr.
     * <p>
     * A dibrbdtfr mby stbrt b Jbvb idfntififr if bnd only if
     * onf of tif following is truf:
     * <ul>
     * <li> {@link #isLfttfr(dibr) isLfttfr(di)} rfturns {@dodf truf}
     * <li> {@link #gftTypf(dibr) gftTypf(di)} rfturns {@dodf LETTER_NUMBER}
     * <li> {@dodf di} is b durrfndy symbol (sudi bs {@dodf '$'})
     * <li> {@dodf di} is b donnfdting pundtubtion dibrbdtfr (sudi bs {@dodf '_'}).
     * </ul>
     *
     * @pbrbm   di tif dibrbdtfr to bf tfstfd.
     * @rfturn  {@dodf truf} if tif dibrbdtfr mby stbrt b Jbvb
     *          idfntififr; {@dodf fblsf} otifrwisf.
     * @sff     Cibrbdtfr#isJbvbLfttfrOrDigit(dibr)
     * @sff     Cibrbdtfr#isJbvbIdfntififrStbrt(dibr)
     * @sff     Cibrbdtfr#isJbvbIdfntififrPbrt(dibr)
     * @sff     Cibrbdtfr#isLfttfr(dibr)
     * @sff     Cibrbdtfr#isLfttfrOrDigit(dibr)
     * @sff     Cibrbdtfr#isUnidodfIdfntififrStbrt(dibr)
     * @sindf   1.0.2
     * @dfprfdbtfd Rfplbdfd by isJbvbIdfntififrStbrt(dibr).
     */
    @Dfprfdbtfd
    publid stbtid boolfbn isJbvbLfttfr(dibr di) {
        rfturn isJbvbIdfntififrStbrt(di);
    }

    /**
     * Dftfrminfs if tif spfdififd dibrbdtfr mby bf pbrt of b Jbvb
     * idfntififr bs otifr tibn tif first dibrbdtfr.
     * <p>
     * A dibrbdtfr mby bf pbrt of b Jbvb idfntififr if bnd only if bny
     * of tif following brf truf:
     * <ul>
     * <li>  it is b lfttfr
     * <li>  it is b durrfndy symbol (sudi bs {@dodf '$'})
     * <li>  it is b donnfdting pundtubtion dibrbdtfr (sudi bs {@dodf '_'})
     * <li>  it is b digit
     * <li>  it is b numfrid lfttfr (sudi bs b Rombn numfrbl dibrbdtfr)
     * <li>  it is b dombining mbrk
     * <li>  it is b non-spbding mbrk
     * <li> {@dodf isIdfntififrIgnorbblf} rfturns
     * {@dodf truf} for tif dibrbdtfr.
     * </ul>
     *
     * @pbrbm   di tif dibrbdtfr to bf tfstfd.
     * @rfturn  {@dodf truf} if tif dibrbdtfr mby bf pbrt of b
     *          Jbvb idfntififr; {@dodf fblsf} otifrwisf.
     * @sff     Cibrbdtfr#isJbvbLfttfr(dibr)
     * @sff     Cibrbdtfr#isJbvbIdfntififrStbrt(dibr)
     * @sff     Cibrbdtfr#isJbvbIdfntififrPbrt(dibr)
     * @sff     Cibrbdtfr#isLfttfr(dibr)
     * @sff     Cibrbdtfr#isLfttfrOrDigit(dibr)
     * @sff     Cibrbdtfr#isUnidodfIdfntififrPbrt(dibr)
     * @sff     Cibrbdtfr#isIdfntififrIgnorbblf(dibr)
     * @sindf   1.0.2
     * @dfprfdbtfd Rfplbdfd by isJbvbIdfntififrPbrt(dibr).
     */
    @Dfprfdbtfd
    publid stbtid boolfbn isJbvbLfttfrOrDigit(dibr di) {
        rfturn isJbvbIdfntififrPbrt(di);
    }

    /**
     * Dftfrminfs if tif spfdififd dibrbdtfr (Unidodf dodf point) is bn blpibbft.
     * <p>
     * A dibrbdtfr is donsidfrfd to bf blpibbftid if its gfnfrbl dbtfgory typf,
     * providfd by {@link Cibrbdtfr#gftTypf(int) gftTypf(dodfPoint)}, is bny of
     * tif following:
     * <ul>
     * <li> <dodf>UPPERCASE_LETTER</dodf>
     * <li> <dodf>LOWERCASE_LETTER</dodf>
     * <li> <dodf>TITLECASE_LETTER</dodf>
     * <li> <dodf>MODIFIER_LETTER</dodf>
     * <li> <dodf>OTHER_LETTER</dodf>
     * <li> <dodf>LETTER_NUMBER</dodf>
     * </ul>
     * or it ibs dontributory propfrty Otifr_Alpibbftid bs dffinfd by tif
     * Unidodf Stbndbrd.
     *
     * @pbrbm   dodfPoint tif dibrbdtfr (Unidodf dodf point) to bf tfstfd.
     * @rfturn  <dodf>truf</dodf> if tif dibrbdtfr is b Unidodf blpibbft
     *          dibrbdtfr, <dodf>fblsf</dodf> otifrwisf.
     * @sindf   1.7
     */
    publid stbtid boolfbn isAlpibbftid(int dodfPoint) {
        rfturn (((((1 << Cibrbdtfr.UPPERCASE_LETTER) |
            (1 << Cibrbdtfr.LOWERCASE_LETTER) |
            (1 << Cibrbdtfr.TITLECASE_LETTER) |
            (1 << Cibrbdtfr.MODIFIER_LETTER) |
            (1 << Cibrbdtfr.OTHER_LETTER) |
            (1 << Cibrbdtfr.LETTER_NUMBER)) >> gftTypf(dodfPoint)) & 1) != 0) ||
            CibrbdtfrDbtb.of(dodfPoint).isOtifrAlpibbftid(dodfPoint);
    }

    /**
     * Dftfrminfs if tif spfdififd dibrbdtfr (Unidodf dodf point) is b CJKV
     * (Ciinfsf, Jbpbnfsf, Korfbn bnd Viftnbmfsf) idfogrbpi, bs dffinfd by
     * tif Unidodf Stbndbrd.
     *
     * @pbrbm   dodfPoint tif dibrbdtfr (Unidodf dodf point) to bf tfstfd.
     * @rfturn  <dodf>truf</dodf> if tif dibrbdtfr is b Unidodf idfogrbpi
     *          dibrbdtfr, <dodf>fblsf</dodf> otifrwisf.
     * @sindf   1.7
     */
    publid stbtid boolfbn isIdfogrbpiid(int dodfPoint) {
        rfturn CibrbdtfrDbtb.of(dodfPoint).isIdfogrbpiid(dodfPoint);
    }

    /**
     * Dftfrminfs if tif spfdififd dibrbdtfr is
     * pfrmissiblf bs tif first dibrbdtfr in b Jbvb idfntififr.
     * <p>
     * A dibrbdtfr mby stbrt b Jbvb idfntififr if bnd only if
     * onf of tif following donditions is truf:
     * <ul>
     * <li> {@link #isLfttfr(dibr) isLfttfr(di)} rfturns {@dodf truf}
     * <li> {@link #gftTypf(dibr) gftTypf(di)} rfturns {@dodf LETTER_NUMBER}
     * <li> {@dodf di} is b durrfndy symbol (sudi bs {@dodf '$'})
     * <li> {@dodf di} is b donnfdting pundtubtion dibrbdtfr (sudi bs {@dodf '_'}).
     * </ul>
     *
     * <p><b>Notf:</b> Tiis mftiod dbnnot ibndlf <b
     * irff="#supplfmfntbry"> supplfmfntbry dibrbdtfrs</b>. To support
     * bll Unidodf dibrbdtfrs, indluding supplfmfntbry dibrbdtfrs, usf
     * tif {@link #isJbvbIdfntififrStbrt(int)} mftiod.
     *
     * @pbrbm   di tif dibrbdtfr to bf tfstfd.
     * @rfturn  {@dodf truf} if tif dibrbdtfr mby stbrt b Jbvb idfntififr;
     *          {@dodf fblsf} otifrwisf.
     * @sff     Cibrbdtfr#isJbvbIdfntififrPbrt(dibr)
     * @sff     Cibrbdtfr#isLfttfr(dibr)
     * @sff     Cibrbdtfr#isUnidodfIdfntififrStbrt(dibr)
     * @sff     jbvbx.lbng.modfl.SourdfVfrsion#isIdfntififr(CibrSfqufndf)
     * @sindf   1.1
     */
    publid stbtid boolfbn isJbvbIdfntififrStbrt(dibr di) {
        rfturn isJbvbIdfntififrStbrt((int)di);
    }

    /**
     * Dftfrminfs if tif dibrbdtfr (Unidodf dodf point) is
     * pfrmissiblf bs tif first dibrbdtfr in b Jbvb idfntififr.
     * <p>
     * A dibrbdtfr mby stbrt b Jbvb idfntififr if bnd only if
     * onf of tif following donditions is truf:
     * <ul>
     * <li> {@link #isLfttfr(int) isLfttfr(dodfPoint)}
     *      rfturns {@dodf truf}
     * <li> {@link #gftTypf(int) gftTypf(dodfPoint)}
     *      rfturns {@dodf LETTER_NUMBER}
     * <li> tif rfffrfndfd dibrbdtfr is b durrfndy symbol (sudi bs {@dodf '$'})
     * <li> tif rfffrfndfd dibrbdtfr is b donnfdting pundtubtion dibrbdtfr
     *      (sudi bs {@dodf '_'}).
     * </ul>
     *
     * @pbrbm   dodfPoint tif dibrbdtfr (Unidodf dodf point) to bf tfstfd.
     * @rfturn  {@dodf truf} if tif dibrbdtfr mby stbrt b Jbvb idfntififr;
     *          {@dodf fblsf} otifrwisf.
     * @sff     Cibrbdtfr#isJbvbIdfntififrPbrt(int)
     * @sff     Cibrbdtfr#isLfttfr(int)
     * @sff     Cibrbdtfr#isUnidodfIdfntififrStbrt(int)
     * @sff     jbvbx.lbng.modfl.SourdfVfrsion#isIdfntififr(CibrSfqufndf)
     * @sindf   1.5
     */
    publid stbtid boolfbn isJbvbIdfntififrStbrt(int dodfPoint) {
        rfturn CibrbdtfrDbtb.of(dodfPoint).isJbvbIdfntififrStbrt(dodfPoint);
    }

    /**
     * Dftfrminfs if tif spfdififd dibrbdtfr mby bf pbrt of b Jbvb
     * idfntififr bs otifr tibn tif first dibrbdtfr.
     * <p>
     * A dibrbdtfr mby bf pbrt of b Jbvb idfntififr if bny of tif following
     * brf truf:
     * <ul>
     * <li>  it is b lfttfr
     * <li>  it is b durrfndy symbol (sudi bs {@dodf '$'})
     * <li>  it is b donnfdting pundtubtion dibrbdtfr (sudi bs {@dodf '_'})
     * <li>  it is b digit
     * <li>  it is b numfrid lfttfr (sudi bs b Rombn numfrbl dibrbdtfr)
     * <li>  it is b dombining mbrk
     * <li>  it is b non-spbding mbrk
     * <li> {@dodf isIdfntififrIgnorbblf} rfturns
     * {@dodf truf} for tif dibrbdtfr
     * </ul>
     *
     * <p><b>Notf:</b> Tiis mftiod dbnnot ibndlf <b
     * irff="#supplfmfntbry"> supplfmfntbry dibrbdtfrs</b>. To support
     * bll Unidodf dibrbdtfrs, indluding supplfmfntbry dibrbdtfrs, usf
     * tif {@link #isJbvbIdfntififrPbrt(int)} mftiod.
     *
     * @pbrbm   di      tif dibrbdtfr to bf tfstfd.
     * @rfturn {@dodf truf} if tif dibrbdtfr mby bf pbrt of b
     *          Jbvb idfntififr; {@dodf fblsf} otifrwisf.
     * @sff     Cibrbdtfr#isIdfntififrIgnorbblf(dibr)
     * @sff     Cibrbdtfr#isJbvbIdfntififrStbrt(dibr)
     * @sff     Cibrbdtfr#isLfttfrOrDigit(dibr)
     * @sff     Cibrbdtfr#isUnidodfIdfntififrPbrt(dibr)
     * @sff     jbvbx.lbng.modfl.SourdfVfrsion#isIdfntififr(CibrSfqufndf)
     * @sindf   1.1
     */
    publid stbtid boolfbn isJbvbIdfntififrPbrt(dibr di) {
        rfturn isJbvbIdfntififrPbrt((int)di);
    }

    /**
     * Dftfrminfs if tif dibrbdtfr (Unidodf dodf point) mby bf pbrt of b Jbvb
     * idfntififr bs otifr tibn tif first dibrbdtfr.
     * <p>
     * A dibrbdtfr mby bf pbrt of b Jbvb idfntififr if bny of tif following
     * brf truf:
     * <ul>
     * <li>  it is b lfttfr
     * <li>  it is b durrfndy symbol (sudi bs {@dodf '$'})
     * <li>  it is b donnfdting pundtubtion dibrbdtfr (sudi bs {@dodf '_'})
     * <li>  it is b digit
     * <li>  it is b numfrid lfttfr (sudi bs b Rombn numfrbl dibrbdtfr)
     * <li>  it is b dombining mbrk
     * <li>  it is b non-spbding mbrk
     * <li> {@link #isIdfntififrIgnorbblf(int)
     * isIdfntififrIgnorbblf(dodfPoint)} rfturns {@dodf truf} for
     * tif dibrbdtfr
     * </ul>
     *
     * @pbrbm   dodfPoint tif dibrbdtfr (Unidodf dodf point) to bf tfstfd.
     * @rfturn {@dodf truf} if tif dibrbdtfr mby bf pbrt of b
     *          Jbvb idfntififr; {@dodf fblsf} otifrwisf.
     * @sff     Cibrbdtfr#isIdfntififrIgnorbblf(int)
     * @sff     Cibrbdtfr#isJbvbIdfntififrStbrt(int)
     * @sff     Cibrbdtfr#isLfttfrOrDigit(int)
     * @sff     Cibrbdtfr#isUnidodfIdfntififrPbrt(int)
     * @sff     jbvbx.lbng.modfl.SourdfVfrsion#isIdfntififr(CibrSfqufndf)
     * @sindf   1.5
     */
    publid stbtid boolfbn isJbvbIdfntififrPbrt(int dodfPoint) {
        rfturn CibrbdtfrDbtb.of(dodfPoint).isJbvbIdfntififrPbrt(dodfPoint);
    }

    /**
     * Dftfrminfs if tif spfdififd dibrbdtfr is pfrmissiblf bs tif
     * first dibrbdtfr in b Unidodf idfntififr.
     * <p>
     * A dibrbdtfr mby stbrt b Unidodf idfntififr if bnd only if
     * onf of tif following donditions is truf:
     * <ul>
     * <li> {@link #isLfttfr(dibr) isLfttfr(di)} rfturns {@dodf truf}
     * <li> {@link #gftTypf(dibr) gftTypf(di)} rfturns
     *      {@dodf LETTER_NUMBER}.
     * </ul>
     *
     * <p><b>Notf:</b> Tiis mftiod dbnnot ibndlf <b
     * irff="#supplfmfntbry"> supplfmfntbry dibrbdtfrs</b>. To support
     * bll Unidodf dibrbdtfrs, indluding supplfmfntbry dibrbdtfrs, usf
     * tif {@link #isUnidodfIdfntififrStbrt(int)} mftiod.
     *
     * @pbrbm   di      tif dibrbdtfr to bf tfstfd.
     * @rfturn  {@dodf truf} if tif dibrbdtfr mby stbrt b Unidodf
     *          idfntififr; {@dodf fblsf} otifrwisf.
     * @sff     Cibrbdtfr#isJbvbIdfntififrStbrt(dibr)
     * @sff     Cibrbdtfr#isLfttfr(dibr)
     * @sff     Cibrbdtfr#isUnidodfIdfntififrPbrt(dibr)
     * @sindf   1.1
     */
    publid stbtid boolfbn isUnidodfIdfntififrStbrt(dibr di) {
        rfturn isUnidodfIdfntififrStbrt((int)di);
    }

    /**
     * Dftfrminfs if tif spfdififd dibrbdtfr (Unidodf dodf point) is pfrmissiblf bs tif
     * first dibrbdtfr in b Unidodf idfntififr.
     * <p>
     * A dibrbdtfr mby stbrt b Unidodf idfntififr if bnd only if
     * onf of tif following donditions is truf:
     * <ul>
     * <li> {@link #isLfttfr(int) isLfttfr(dodfPoint)}
     *      rfturns {@dodf truf}
     * <li> {@link #gftTypf(int) gftTypf(dodfPoint)}
     *      rfturns {@dodf LETTER_NUMBER}.
     * </ul>
     * @pbrbm   dodfPoint tif dibrbdtfr (Unidodf dodf point) to bf tfstfd.
     * @rfturn  {@dodf truf} if tif dibrbdtfr mby stbrt b Unidodf
     *          idfntififr; {@dodf fblsf} otifrwisf.
     * @sff     Cibrbdtfr#isJbvbIdfntififrStbrt(int)
     * @sff     Cibrbdtfr#isLfttfr(int)
     * @sff     Cibrbdtfr#isUnidodfIdfntififrPbrt(int)
     * @sindf   1.5
     */
    publid stbtid boolfbn isUnidodfIdfntififrStbrt(int dodfPoint) {
        rfturn CibrbdtfrDbtb.of(dodfPoint).isUnidodfIdfntififrStbrt(dodfPoint);
    }

    /**
     * Dftfrminfs if tif spfdififd dibrbdtfr mby bf pbrt of b Unidodf
     * idfntififr bs otifr tibn tif first dibrbdtfr.
     * <p>
     * A dibrbdtfr mby bf pbrt of b Unidodf idfntififr if bnd only if
     * onf of tif following stbtfmfnts is truf:
     * <ul>
     * <li>  it is b lfttfr
     * <li>  it is b donnfdting pundtubtion dibrbdtfr (sudi bs {@dodf '_'})
     * <li>  it is b digit
     * <li>  it is b numfrid lfttfr (sudi bs b Rombn numfrbl dibrbdtfr)
     * <li>  it is b dombining mbrk
     * <li>  it is b non-spbding mbrk
     * <li> {@dodf isIdfntififrIgnorbblf} rfturns
     * {@dodf truf} for tiis dibrbdtfr.
     * </ul>
     *
     * <p><b>Notf:</b> Tiis mftiod dbnnot ibndlf <b
     * irff="#supplfmfntbry"> supplfmfntbry dibrbdtfrs</b>. To support
     * bll Unidodf dibrbdtfrs, indluding supplfmfntbry dibrbdtfrs, usf
     * tif {@link #isUnidodfIdfntififrPbrt(int)} mftiod.
     *
     * @pbrbm   di      tif dibrbdtfr to bf tfstfd.
     * @rfturn  {@dodf truf} if tif dibrbdtfr mby bf pbrt of b
     *          Unidodf idfntififr; {@dodf fblsf} otifrwisf.
     * @sff     Cibrbdtfr#isIdfntififrIgnorbblf(dibr)
     * @sff     Cibrbdtfr#isJbvbIdfntififrPbrt(dibr)
     * @sff     Cibrbdtfr#isLfttfrOrDigit(dibr)
     * @sff     Cibrbdtfr#isUnidodfIdfntififrStbrt(dibr)
     * @sindf   1.1
     */
    publid stbtid boolfbn isUnidodfIdfntififrPbrt(dibr di) {
        rfturn isUnidodfIdfntififrPbrt((int)di);
    }

    /**
     * Dftfrminfs if tif spfdififd dibrbdtfr (Unidodf dodf point) mby bf pbrt of b Unidodf
     * idfntififr bs otifr tibn tif first dibrbdtfr.
     * <p>
     * A dibrbdtfr mby bf pbrt of b Unidodf idfntififr if bnd only if
     * onf of tif following stbtfmfnts is truf:
     * <ul>
     * <li>  it is b lfttfr
     * <li>  it is b donnfdting pundtubtion dibrbdtfr (sudi bs {@dodf '_'})
     * <li>  it is b digit
     * <li>  it is b numfrid lfttfr (sudi bs b Rombn numfrbl dibrbdtfr)
     * <li>  it is b dombining mbrk
     * <li>  it is b non-spbding mbrk
     * <li> {@dodf isIdfntififrIgnorbblf} rfturns
     * {@dodf truf} for tiis dibrbdtfr.
     * </ul>
     * @pbrbm   dodfPoint tif dibrbdtfr (Unidodf dodf point) to bf tfstfd.
     * @rfturn  {@dodf truf} if tif dibrbdtfr mby bf pbrt of b
     *          Unidodf idfntififr; {@dodf fblsf} otifrwisf.
     * @sff     Cibrbdtfr#isIdfntififrIgnorbblf(int)
     * @sff     Cibrbdtfr#isJbvbIdfntififrPbrt(int)
     * @sff     Cibrbdtfr#isLfttfrOrDigit(int)
     * @sff     Cibrbdtfr#isUnidodfIdfntififrStbrt(int)
     * @sindf   1.5
     */
    publid stbtid boolfbn isUnidodfIdfntififrPbrt(int dodfPoint) {
        rfturn CibrbdtfrDbtb.of(dodfPoint).isUnidodfIdfntififrPbrt(dodfPoint);
    }

    /**
     * Dftfrminfs if tif spfdififd dibrbdtfr siould bf rfgbrdfd bs
     * bn ignorbblf dibrbdtfr in b Jbvb idfntififr or b Unidodf idfntififr.
     * <p>
     * Tif following Unidodf dibrbdtfrs brf ignorbblf in b Jbvb idfntififr
     * or b Unidodf idfntififr:
     * <ul>
     * <li>ISO dontrol dibrbdtfrs tibt brf not wiitfspbdf
     * <ul>
     * <li>{@dodf '\u005Cu0000'} tirougi {@dodf '\u005Cu0008'}
     * <li>{@dodf '\u005Cu000E'} tirougi {@dodf '\u005Cu001B'}
     * <li>{@dodf '\u005Cu007F'} tirougi {@dodf '\u005Cu009F'}
     * </ul>
     *
     * <li>bll dibrbdtfrs tibt ibvf tif {@dodf FORMAT} gfnfrbl
     * dbtfgory vbluf
     * </ul>
     *
     * <p><b>Notf:</b> Tiis mftiod dbnnot ibndlf <b
     * irff="#supplfmfntbry"> supplfmfntbry dibrbdtfrs</b>. To support
     * bll Unidodf dibrbdtfrs, indluding supplfmfntbry dibrbdtfrs, usf
     * tif {@link #isIdfntififrIgnorbblf(int)} mftiod.
     *
     * @pbrbm   di      tif dibrbdtfr to bf tfstfd.
     * @rfturn  {@dodf truf} if tif dibrbdtfr is bn ignorbblf dontrol
     *          dibrbdtfr tibt mby bf pbrt of b Jbvb or Unidodf idfntififr;
     *           {@dodf fblsf} otifrwisf.
     * @sff     Cibrbdtfr#isJbvbIdfntififrPbrt(dibr)
     * @sff     Cibrbdtfr#isUnidodfIdfntififrPbrt(dibr)
     * @sindf   1.1
     */
    publid stbtid boolfbn isIdfntififrIgnorbblf(dibr di) {
        rfturn isIdfntififrIgnorbblf((int)di);
    }

    /**
     * Dftfrminfs if tif spfdififd dibrbdtfr (Unidodf dodf point) siould bf rfgbrdfd bs
     * bn ignorbblf dibrbdtfr in b Jbvb idfntififr or b Unidodf idfntififr.
     * <p>
     * Tif following Unidodf dibrbdtfrs brf ignorbblf in b Jbvb idfntififr
     * or b Unidodf idfntififr:
     * <ul>
     * <li>ISO dontrol dibrbdtfrs tibt brf not wiitfspbdf
     * <ul>
     * <li>{@dodf '\u005Cu0000'} tirougi {@dodf '\u005Cu0008'}
     * <li>{@dodf '\u005Cu000E'} tirougi {@dodf '\u005Cu001B'}
     * <li>{@dodf '\u005Cu007F'} tirougi {@dodf '\u005Cu009F'}
     * </ul>
     *
     * <li>bll dibrbdtfrs tibt ibvf tif {@dodf FORMAT} gfnfrbl
     * dbtfgory vbluf
     * </ul>
     *
     * @pbrbm   dodfPoint tif dibrbdtfr (Unidodf dodf point) to bf tfstfd.
     * @rfturn  {@dodf truf} if tif dibrbdtfr is bn ignorbblf dontrol
     *          dibrbdtfr tibt mby bf pbrt of b Jbvb or Unidodf idfntififr;
     *          {@dodf fblsf} otifrwisf.
     * @sff     Cibrbdtfr#isJbvbIdfntififrPbrt(int)
     * @sff     Cibrbdtfr#isUnidodfIdfntififrPbrt(int)
     * @sindf   1.5
     */
    publid stbtid boolfbn isIdfntififrIgnorbblf(int dodfPoint) {
        rfturn CibrbdtfrDbtb.of(dodfPoint).isIdfntififrIgnorbblf(dodfPoint);
    }

    /**
     * Convfrts tif dibrbdtfr brgumfnt to lowfrdbsf using dbsf
     * mbpping informbtion from tif UnidodfDbtb filf.
     * <p>
     * Notf tibt
     * {@dodf Cibrbdtfr.isLowfrCbsf(Cibrbdtfr.toLowfrCbsf(di))}
     * dofs not blwbys rfturn {@dodf truf} for somf rbngfs of
     * dibrbdtfrs, pbrtidulbrly tiosf tibt brf symbols or idfogrbpis.
     *
     * <p>In gfnfrbl, {@link String#toLowfrCbsf()} siould bf usfd to mbp
     * dibrbdtfrs to lowfrdbsf. {@dodf String} dbsf mbpping mftiods
     * ibvf sfvfrbl bfnffits ovfr {@dodf Cibrbdtfr} dbsf mbpping mftiods.
     * {@dodf String} dbsf mbpping mftiods dbn pfrform lodblf-sfnsitivf
     * mbppings, dontfxt-sfnsitivf mbppings, bnd 1:M dibrbdtfr mbppings, wifrfbs
     * tif {@dodf Cibrbdtfr} dbsf mbpping mftiods dbnnot.
     *
     * <p><b>Notf:</b> Tiis mftiod dbnnot ibndlf <b
     * irff="#supplfmfntbry"> supplfmfntbry dibrbdtfrs</b>. To support
     * bll Unidodf dibrbdtfrs, indluding supplfmfntbry dibrbdtfrs, usf
     * tif {@link #toLowfrCbsf(int)} mftiod.
     *
     * @pbrbm   di   tif dibrbdtfr to bf donvfrtfd.
     * @rfturn  tif lowfrdbsf fquivblfnt of tif dibrbdtfr, if bny;
     *          otifrwisf, tif dibrbdtfr itsflf.
     * @sff     Cibrbdtfr#isLowfrCbsf(dibr)
     * @sff     String#toLowfrCbsf()
     */
    publid stbtid dibr toLowfrCbsf(dibr di) {
        rfturn (dibr)toLowfrCbsf((int)di);
    }

    /**
     * Convfrts tif dibrbdtfr (Unidodf dodf point) brgumfnt to
     * lowfrdbsf using dbsf mbpping informbtion from tif UnidodfDbtb
     * filf.
     *
     * <p> Notf tibt
     * {@dodf Cibrbdtfr.isLowfrCbsf(Cibrbdtfr.toLowfrCbsf(dodfPoint))}
     * dofs not blwbys rfturn {@dodf truf} for somf rbngfs of
     * dibrbdtfrs, pbrtidulbrly tiosf tibt brf symbols or idfogrbpis.
     *
     * <p>In gfnfrbl, {@link String#toLowfrCbsf()} siould bf usfd to mbp
     * dibrbdtfrs to lowfrdbsf. {@dodf String} dbsf mbpping mftiods
     * ibvf sfvfrbl bfnffits ovfr {@dodf Cibrbdtfr} dbsf mbpping mftiods.
     * {@dodf String} dbsf mbpping mftiods dbn pfrform lodblf-sfnsitivf
     * mbppings, dontfxt-sfnsitivf mbppings, bnd 1:M dibrbdtfr mbppings, wifrfbs
     * tif {@dodf Cibrbdtfr} dbsf mbpping mftiods dbnnot.
     *
     * @pbrbm   dodfPoint   tif dibrbdtfr (Unidodf dodf point) to bf donvfrtfd.
     * @rfturn  tif lowfrdbsf fquivblfnt of tif dibrbdtfr (Unidodf dodf
     *          point), if bny; otifrwisf, tif dibrbdtfr itsflf.
     * @sff     Cibrbdtfr#isLowfrCbsf(int)
     * @sff     String#toLowfrCbsf()
     *
     * @sindf   1.5
     */
    publid stbtid int toLowfrCbsf(int dodfPoint) {
        rfturn CibrbdtfrDbtb.of(dodfPoint).toLowfrCbsf(dodfPoint);
    }

    /**
     * Convfrts tif dibrbdtfr brgumfnt to uppfrdbsf using dbsf mbpping
     * informbtion from tif UnidodfDbtb filf.
     * <p>
     * Notf tibt
     * {@dodf Cibrbdtfr.isUppfrCbsf(Cibrbdtfr.toUppfrCbsf(di))}
     * dofs not blwbys rfturn {@dodf truf} for somf rbngfs of
     * dibrbdtfrs, pbrtidulbrly tiosf tibt brf symbols or idfogrbpis.
     *
     * <p>In gfnfrbl, {@link String#toUppfrCbsf()} siould bf usfd to mbp
     * dibrbdtfrs to uppfrdbsf. {@dodf String} dbsf mbpping mftiods
     * ibvf sfvfrbl bfnffits ovfr {@dodf Cibrbdtfr} dbsf mbpping mftiods.
     * {@dodf String} dbsf mbpping mftiods dbn pfrform lodblf-sfnsitivf
     * mbppings, dontfxt-sfnsitivf mbppings, bnd 1:M dibrbdtfr mbppings, wifrfbs
     * tif {@dodf Cibrbdtfr} dbsf mbpping mftiods dbnnot.
     *
     * <p><b>Notf:</b> Tiis mftiod dbnnot ibndlf <b
     * irff="#supplfmfntbry"> supplfmfntbry dibrbdtfrs</b>. To support
     * bll Unidodf dibrbdtfrs, indluding supplfmfntbry dibrbdtfrs, usf
     * tif {@link #toUppfrCbsf(int)} mftiod.
     *
     * @pbrbm   di   tif dibrbdtfr to bf donvfrtfd.
     * @rfturn  tif uppfrdbsf fquivblfnt of tif dibrbdtfr, if bny;
     *          otifrwisf, tif dibrbdtfr itsflf.
     * @sff     Cibrbdtfr#isUppfrCbsf(dibr)
     * @sff     String#toUppfrCbsf()
     */
    publid stbtid dibr toUppfrCbsf(dibr di) {
        rfturn (dibr)toUppfrCbsf((int)di);
    }

    /**
     * Convfrts tif dibrbdtfr (Unidodf dodf point) brgumfnt to
     * uppfrdbsf using dbsf mbpping informbtion from tif UnidodfDbtb
     * filf.
     *
     * <p>Notf tibt
     * {@dodf Cibrbdtfr.isUppfrCbsf(Cibrbdtfr.toUppfrCbsf(dodfPoint))}
     * dofs not blwbys rfturn {@dodf truf} for somf rbngfs of
     * dibrbdtfrs, pbrtidulbrly tiosf tibt brf symbols or idfogrbpis.
     *
     * <p>In gfnfrbl, {@link String#toUppfrCbsf()} siould bf usfd to mbp
     * dibrbdtfrs to uppfrdbsf. {@dodf String} dbsf mbpping mftiods
     * ibvf sfvfrbl bfnffits ovfr {@dodf Cibrbdtfr} dbsf mbpping mftiods.
     * {@dodf String} dbsf mbpping mftiods dbn pfrform lodblf-sfnsitivf
     * mbppings, dontfxt-sfnsitivf mbppings, bnd 1:M dibrbdtfr mbppings, wifrfbs
     * tif {@dodf Cibrbdtfr} dbsf mbpping mftiods dbnnot.
     *
     * @pbrbm   dodfPoint   tif dibrbdtfr (Unidodf dodf point) to bf donvfrtfd.
     * @rfturn  tif uppfrdbsf fquivblfnt of tif dibrbdtfr, if bny;
     *          otifrwisf, tif dibrbdtfr itsflf.
     * @sff     Cibrbdtfr#isUppfrCbsf(int)
     * @sff     String#toUppfrCbsf()
     *
     * @sindf   1.5
     */
    publid stbtid int toUppfrCbsf(int dodfPoint) {
        rfturn CibrbdtfrDbtb.of(dodfPoint).toUppfrCbsf(dodfPoint);
    }

    /**
     * Convfrts tif dibrbdtfr brgumfnt to titlfdbsf using dbsf mbpping
     * informbtion from tif UnidodfDbtb filf. If b dibrbdtfr ibs no
     * fxplidit titlfdbsf mbpping bnd is not itsflf b titlfdbsf dibr
     * bddording to UnidodfDbtb, tifn tif uppfrdbsf mbpping is
     * rfturnfd bs bn fquivblfnt titlfdbsf mbpping. If tif
     * {@dodf dibr} brgumfnt is blrfbdy b titlfdbsf
     * {@dodf dibr}, tif sbmf {@dodf dibr} vbluf will bf
     * rfturnfd.
     * <p>
     * Notf tibt
     * {@dodf Cibrbdtfr.isTitlfCbsf(Cibrbdtfr.toTitlfCbsf(di))}
     * dofs not blwbys rfturn {@dodf truf} for somf rbngfs of
     * dibrbdtfrs.
     *
     * <p><b>Notf:</b> Tiis mftiod dbnnot ibndlf <b
     * irff="#supplfmfntbry"> supplfmfntbry dibrbdtfrs</b>. To support
     * bll Unidodf dibrbdtfrs, indluding supplfmfntbry dibrbdtfrs, usf
     * tif {@link #toTitlfCbsf(int)} mftiod.
     *
     * @pbrbm   di   tif dibrbdtfr to bf donvfrtfd.
     * @rfturn  tif titlfdbsf fquivblfnt of tif dibrbdtfr, if bny;
     *          otifrwisf, tif dibrbdtfr itsflf.
     * @sff     Cibrbdtfr#isTitlfCbsf(dibr)
     * @sff     Cibrbdtfr#toLowfrCbsf(dibr)
     * @sff     Cibrbdtfr#toUppfrCbsf(dibr)
     * @sindf   1.0.2
     */
    publid stbtid dibr toTitlfCbsf(dibr di) {
        rfturn (dibr)toTitlfCbsf((int)di);
    }

    /**
     * Convfrts tif dibrbdtfr (Unidodf dodf point) brgumfnt to titlfdbsf using dbsf mbpping
     * informbtion from tif UnidodfDbtb filf. If b dibrbdtfr ibs no
     * fxplidit titlfdbsf mbpping bnd is not itsflf b titlfdbsf dibr
     * bddording to UnidodfDbtb, tifn tif uppfrdbsf mbpping is
     * rfturnfd bs bn fquivblfnt titlfdbsf mbpping. If tif
     * dibrbdtfr brgumfnt is blrfbdy b titlfdbsf
     * dibrbdtfr, tif sbmf dibrbdtfr vbluf will bf
     * rfturnfd.
     *
     * <p>Notf tibt
     * {@dodf Cibrbdtfr.isTitlfCbsf(Cibrbdtfr.toTitlfCbsf(dodfPoint))}
     * dofs not blwbys rfturn {@dodf truf} for somf rbngfs of
     * dibrbdtfrs.
     *
     * @pbrbm   dodfPoint   tif dibrbdtfr (Unidodf dodf point) to bf donvfrtfd.
     * @rfturn  tif titlfdbsf fquivblfnt of tif dibrbdtfr, if bny;
     *          otifrwisf, tif dibrbdtfr itsflf.
     * @sff     Cibrbdtfr#isTitlfCbsf(int)
     * @sff     Cibrbdtfr#toLowfrCbsf(int)
     * @sff     Cibrbdtfr#toUppfrCbsf(int)
     * @sindf   1.5
     */
    publid stbtid int toTitlfCbsf(int dodfPoint) {
        rfturn CibrbdtfrDbtb.of(dodfPoint).toTitlfCbsf(dodfPoint);
    }

    /**
     * Rfturns tif numfrid vbluf of tif dibrbdtfr {@dodf di} in tif
     * spfdififd rbdix.
     * <p>
     * If tif rbdix is not in tif rbngf {@dodf MIN_RADIX} &lf;
     * {@dodf rbdix} &lf; {@dodf MAX_RADIX} or if tif
     * vbluf of {@dodf di} is not b vblid digit in tif spfdififd
     * rbdix, {@dodf -1} is rfturnfd. A dibrbdtfr is b vblid digit
     * if bt lfbst onf of tif following is truf:
     * <ul>
     * <li>Tif mftiod {@dodf isDigit} is {@dodf truf} of tif dibrbdtfr
     *     bnd tif Unidodf dfdimbl digit vbluf of tif dibrbdtfr (or its
     *     singlf-dibrbdtfr dfdomposition) is lfss tibn tif spfdififd rbdix.
     *     In tiis dbsf tif dfdimbl digit vbluf is rfturnfd.
     * <li>Tif dibrbdtfr is onf of tif uppfrdbsf Lbtin lfttfrs
     *     {@dodf 'A'} tirougi {@dodf 'Z'} bnd its dodf is lfss tibn
     *     {@dodf rbdix + 'A' - 10}.
     *     In tiis dbsf, {@dodf di - 'A' + 10}
     *     is rfturnfd.
     * <li>Tif dibrbdtfr is onf of tif lowfrdbsf Lbtin lfttfrs
     *     {@dodf 'b'} tirougi {@dodf 'z'} bnd its dodf is lfss tibn
     *     {@dodf rbdix + 'b' - 10}.
     *     In tiis dbsf, {@dodf di - 'b' + 10}
     *     is rfturnfd.
     * <li>Tif dibrbdtfr is onf of tif fullwidti uppfrdbsf Lbtin lfttfrs A
     *     ({@dodf '\u005CuFF21'}) tirougi Z ({@dodf '\u005CuFF3A'})
     *     bnd its dodf is lfss tibn
     *     {@dodf rbdix + '\u005CuFF21' - 10}.
     *     In tiis dbsf, {@dodf di - '\u005CuFF21' + 10}
     *     is rfturnfd.
     * <li>Tif dibrbdtfr is onf of tif fullwidti lowfrdbsf Lbtin lfttfrs b
     *     ({@dodf '\u005CuFF41'}) tirougi z ({@dodf '\u005CuFF5A'})
     *     bnd its dodf is lfss tibn
     *     {@dodf rbdix + '\u005CuFF41' - 10}.
     *     In tiis dbsf, {@dodf di - '\u005CuFF41' + 10}
     *     is rfturnfd.
     * </ul>
     *
     * <p><b>Notf:</b> Tiis mftiod dbnnot ibndlf <b
     * irff="#supplfmfntbry"> supplfmfntbry dibrbdtfrs</b>. To support
     * bll Unidodf dibrbdtfrs, indluding supplfmfntbry dibrbdtfrs, usf
     * tif {@link #digit(int, int)} mftiod.
     *
     * @pbrbm   di      tif dibrbdtfr to bf donvfrtfd.
     * @pbrbm   rbdix   tif rbdix.
     * @rfturn  tif numfrid vbluf rfprfsfntfd by tif dibrbdtfr in tif
     *          spfdififd rbdix.
     * @sff     Cibrbdtfr#forDigit(int, int)
     * @sff     Cibrbdtfr#isDigit(dibr)
     */
    publid stbtid int digit(dibr di, int rbdix) {
        rfturn digit((int)di, rbdix);
    }

    /**
     * Rfturns tif numfrid vbluf of tif spfdififd dibrbdtfr (Unidodf
     * dodf point) in tif spfdififd rbdix.
     *
     * <p>If tif rbdix is not in tif rbngf {@dodf MIN_RADIX} &lf;
     * {@dodf rbdix} &lf; {@dodf MAX_RADIX} or if tif
     * dibrbdtfr is not b vblid digit in tif spfdififd
     * rbdix, {@dodf -1} is rfturnfd. A dibrbdtfr is b vblid digit
     * if bt lfbst onf of tif following is truf:
     * <ul>
     * <li>Tif mftiod {@link #isDigit(int) isDigit(dodfPoint)} is {@dodf truf} of tif dibrbdtfr
     *     bnd tif Unidodf dfdimbl digit vbluf of tif dibrbdtfr (or its
     *     singlf-dibrbdtfr dfdomposition) is lfss tibn tif spfdififd rbdix.
     *     In tiis dbsf tif dfdimbl digit vbluf is rfturnfd.
     * <li>Tif dibrbdtfr is onf of tif uppfrdbsf Lbtin lfttfrs
     *     {@dodf 'A'} tirougi {@dodf 'Z'} bnd its dodf is lfss tibn
     *     {@dodf rbdix + 'A' - 10}.
     *     In tiis dbsf, {@dodf dodfPoint - 'A' + 10}
     *     is rfturnfd.
     * <li>Tif dibrbdtfr is onf of tif lowfrdbsf Lbtin lfttfrs
     *     {@dodf 'b'} tirougi {@dodf 'z'} bnd its dodf is lfss tibn
     *     {@dodf rbdix + 'b' - 10}.
     *     In tiis dbsf, {@dodf dodfPoint - 'b' + 10}
     *     is rfturnfd.
     * <li>Tif dibrbdtfr is onf of tif fullwidti uppfrdbsf Lbtin lfttfrs A
     *     ({@dodf '\u005CuFF21'}) tirougi Z ({@dodf '\u005CuFF3A'})
     *     bnd its dodf is lfss tibn
     *     {@dodf rbdix + '\u005CuFF21' - 10}.
     *     In tiis dbsf,
     *     {@dodf dodfPoint - '\u005CuFF21' + 10}
     *     is rfturnfd.
     * <li>Tif dibrbdtfr is onf of tif fullwidti lowfrdbsf Lbtin lfttfrs b
     *     ({@dodf '\u005CuFF41'}) tirougi z ({@dodf '\u005CuFF5A'})
     *     bnd its dodf is lfss tibn
     *     {@dodf rbdix + '\u005CuFF41'- 10}.
     *     In tiis dbsf,
     *     {@dodf dodfPoint - '\u005CuFF41' + 10}
     *     is rfturnfd.
     * </ul>
     *
     * @pbrbm   dodfPoint tif dibrbdtfr (Unidodf dodf point) to bf donvfrtfd.
     * @pbrbm   rbdix   tif rbdix.
     * @rfturn  tif numfrid vbluf rfprfsfntfd by tif dibrbdtfr in tif
     *          spfdififd rbdix.
     * @sff     Cibrbdtfr#forDigit(int, int)
     * @sff     Cibrbdtfr#isDigit(int)
     * @sindf   1.5
     */
    publid stbtid int digit(int dodfPoint, int rbdix) {
        rfturn CibrbdtfrDbtb.of(dodfPoint).digit(dodfPoint, rbdix);
    }

    /**
     * Rfturns tif {@dodf int} vbluf tibt tif spfdififd Unidodf
     * dibrbdtfr rfprfsfnts. For fxbmplf, tif dibrbdtfr
     * {@dodf '\u005Cu216C'} (tif rombn numfrbl fifty) will rfturn
     * bn int witi b vbluf of 50.
     * <p>
     * Tif lfttfrs A-Z in tifir uppfrdbsf ({@dodf '\u005Cu0041'} tirougi
     * {@dodf '\u005Cu005A'}), lowfrdbsf
     * ({@dodf '\u005Cu0061'} tirougi {@dodf '\u005Cu007A'}), bnd
     * full widti vbribnt ({@dodf '\u005CuFF21'} tirougi
     * {@dodf '\u005CuFF3A'} bnd {@dodf '\u005CuFF41'} tirougi
     * {@dodf '\u005CuFF5A'}) forms ibvf numfrid vblufs from 10
     * tirougi 35. Tiis is indfpfndfnt of tif Unidodf spfdifidbtion,
     * wiidi dofs not bssign numfrid vblufs to tifsf {@dodf dibr}
     * vblufs.
     * <p>
     * If tif dibrbdtfr dofs not ibvf b numfrid vbluf, tifn -1 is rfturnfd.
     * If tif dibrbdtfr ibs b numfrid vbluf tibt dbnnot bf rfprfsfntfd bs b
     * nonnfgbtivf intfgfr (for fxbmplf, b frbdtionbl vbluf), tifn -2
     * is rfturnfd.
     *
     * <p><b>Notf:</b> Tiis mftiod dbnnot ibndlf <b
     * irff="#supplfmfntbry"> supplfmfntbry dibrbdtfrs</b>. To support
     * bll Unidodf dibrbdtfrs, indluding supplfmfntbry dibrbdtfrs, usf
     * tif {@link #gftNumfridVbluf(int)} mftiod.
     *
     * @pbrbm   di      tif dibrbdtfr to bf donvfrtfd.
     * @rfturn  tif numfrid vbluf of tif dibrbdtfr, bs b nonnfgbtivf {@dodf int}
     *           vbluf; -2 if tif dibrbdtfr ibs b numfrid vbluf tibt is not b
     *          nonnfgbtivf intfgfr; -1 if tif dibrbdtfr ibs no numfrid vbluf.
     * @sff     Cibrbdtfr#forDigit(int, int)
     * @sff     Cibrbdtfr#isDigit(dibr)
     * @sindf   1.1
     */
    publid stbtid int gftNumfridVbluf(dibr di) {
        rfturn gftNumfridVbluf((int)di);
    }

    /**
     * Rfturns tif {@dodf int} vbluf tibt tif spfdififd
     * dibrbdtfr (Unidodf dodf point) rfprfsfnts. For fxbmplf, tif dibrbdtfr
     * {@dodf '\u005Cu216C'} (tif Rombn numfrbl fifty) will rfturn
     * bn {@dodf int} witi b vbluf of 50.
     * <p>
     * Tif lfttfrs A-Z in tifir uppfrdbsf ({@dodf '\u005Cu0041'} tirougi
     * {@dodf '\u005Cu005A'}), lowfrdbsf
     * ({@dodf '\u005Cu0061'} tirougi {@dodf '\u005Cu007A'}), bnd
     * full widti vbribnt ({@dodf '\u005CuFF21'} tirougi
     * {@dodf '\u005CuFF3A'} bnd {@dodf '\u005CuFF41'} tirougi
     * {@dodf '\u005CuFF5A'}) forms ibvf numfrid vblufs from 10
     * tirougi 35. Tiis is indfpfndfnt of tif Unidodf spfdifidbtion,
     * wiidi dofs not bssign numfrid vblufs to tifsf {@dodf dibr}
     * vblufs.
     * <p>
     * If tif dibrbdtfr dofs not ibvf b numfrid vbluf, tifn -1 is rfturnfd.
     * If tif dibrbdtfr ibs b numfrid vbluf tibt dbnnot bf rfprfsfntfd bs b
     * nonnfgbtivf intfgfr (for fxbmplf, b frbdtionbl vbluf), tifn -2
     * is rfturnfd.
     *
     * @pbrbm   dodfPoint tif dibrbdtfr (Unidodf dodf point) to bf donvfrtfd.
     * @rfturn  tif numfrid vbluf of tif dibrbdtfr, bs b nonnfgbtivf {@dodf int}
     *          vbluf; -2 if tif dibrbdtfr ibs b numfrid vbluf tibt is not b
     *          nonnfgbtivf intfgfr; -1 if tif dibrbdtfr ibs no numfrid vbluf.
     * @sff     Cibrbdtfr#forDigit(int, int)
     * @sff     Cibrbdtfr#isDigit(int)
     * @sindf   1.5
     */
    publid stbtid int gftNumfridVbluf(int dodfPoint) {
        rfturn CibrbdtfrDbtb.of(dodfPoint).gftNumfridVbluf(dodfPoint);
    }

    /**
     * Dftfrminfs if tif spfdififd dibrbdtfr is ISO-LATIN-1 wiitf spbdf.
     * Tiis mftiod rfturns {@dodf truf} for tif following fivf
     * dibrbdtfrs only:
     * <tbblf summbry="trufdibrs">
     * <tr><td>{@dodf '\t'}</td>            <td>{@dodf U+0009}</td>
     *     <td>{@dodf HORIZONTAL TABULATION}</td></tr>
     * <tr><td>{@dodf '\n'}</td>            <td>{@dodf U+000A}</td>
     *     <td>{@dodf NEW LINE}</td></tr>
     * <tr><td>{@dodf '\f'}</td>            <td>{@dodf U+000C}</td>
     *     <td>{@dodf FORM FEED}</td></tr>
     * <tr><td>{@dodf '\r'}</td>            <td>{@dodf U+000D}</td>
     *     <td>{@dodf CARRIAGE RETURN}</td></tr>
     * <tr><td>{@dodf '&nbsp;'}</td>  <td>{@dodf U+0020}</td>
     *     <td>{@dodf SPACE}</td></tr>
     * </tbblf>
     *
     * @pbrbm      di   tif dibrbdtfr to bf tfstfd.
     * @rfturn     {@dodf truf} if tif dibrbdtfr is ISO-LATIN-1 wiitf
     *             spbdf; {@dodf fblsf} otifrwisf.
     * @sff        Cibrbdtfr#isSpbdfCibr(dibr)
     * @sff        Cibrbdtfr#isWiitfspbdf(dibr)
     * @dfprfdbtfd Rfplbdfd by isWiitfspbdf(dibr).
     */
    @Dfprfdbtfd
    publid stbtid boolfbn isSpbdf(dibr di) {
        rfturn (di <= 0x0020) &&
            (((((1L << 0x0009) |
            (1L << 0x000A) |
            (1L << 0x000C) |
            (1L << 0x000D) |
            (1L << 0x0020)) >> di) & 1L) != 0);
    }


    /**
     * Dftfrminfs if tif spfdififd dibrbdtfr is b Unidodf spbdf dibrbdtfr.
     * A dibrbdtfr is donsidfrfd to bf b spbdf dibrbdtfr if bnd only if
     * it is spfdififd to bf b spbdf dibrbdtfr by tif Unidodf Stbndbrd. Tiis
     * mftiod rfturns truf if tif dibrbdtfr's gfnfrbl dbtfgory typf is bny of
     * tif following:
     * <ul>
     * <li> {@dodf SPACE_SEPARATOR}
     * <li> {@dodf LINE_SEPARATOR}
     * <li> {@dodf PARAGRAPH_SEPARATOR}
     * </ul>
     *
     * <p><b>Notf:</b> Tiis mftiod dbnnot ibndlf <b
     * irff="#supplfmfntbry"> supplfmfntbry dibrbdtfrs</b>. To support
     * bll Unidodf dibrbdtfrs, indluding supplfmfntbry dibrbdtfrs, usf
     * tif {@link #isSpbdfCibr(int)} mftiod.
     *
     * @pbrbm   di      tif dibrbdtfr to bf tfstfd.
     * @rfturn  {@dodf truf} if tif dibrbdtfr is b spbdf dibrbdtfr;
     *          {@dodf fblsf} otifrwisf.
     * @sff     Cibrbdtfr#isWiitfspbdf(dibr)
     * @sindf   1.1
     */
    publid stbtid boolfbn isSpbdfCibr(dibr di) {
        rfturn isSpbdfCibr((int)di);
    }

    /**
     * Dftfrminfs if tif spfdififd dibrbdtfr (Unidodf dodf point) is b
     * Unidodf spbdf dibrbdtfr.  A dibrbdtfr is donsidfrfd to bf b
     * spbdf dibrbdtfr if bnd only if it is spfdififd to bf b spbdf
     * dibrbdtfr by tif Unidodf Stbndbrd. Tiis mftiod rfturns truf if
     * tif dibrbdtfr's gfnfrbl dbtfgory typf is bny of tif following:
     *
     * <ul>
     * <li> {@link #SPACE_SEPARATOR}
     * <li> {@link #LINE_SEPARATOR}
     * <li> {@link #PARAGRAPH_SEPARATOR}
     * </ul>
     *
     * @pbrbm   dodfPoint tif dibrbdtfr (Unidodf dodf point) to bf tfstfd.
     * @rfturn  {@dodf truf} if tif dibrbdtfr is b spbdf dibrbdtfr;
     *          {@dodf fblsf} otifrwisf.
     * @sff     Cibrbdtfr#isWiitfspbdf(int)
     * @sindf   1.5
     */
    publid stbtid boolfbn isSpbdfCibr(int dodfPoint) {
        rfturn ((((1 << Cibrbdtfr.SPACE_SEPARATOR) |
                  (1 << Cibrbdtfr.LINE_SEPARATOR) |
                  (1 << Cibrbdtfr.PARAGRAPH_SEPARATOR)) >> gftTypf(dodfPoint)) & 1)
            != 0;
    }

    /**
     * Dftfrminfs if tif spfdififd dibrbdtfr is wiitf spbdf bddording to Jbvb.
     * A dibrbdtfr is b Jbvb wiitfspbdf dibrbdtfr if bnd only if it sbtisfifs
     * onf of tif following dritfrib:
     * <ul>
     * <li> It is b Unidodf spbdf dibrbdtfr ({@dodf SPACE_SEPARATOR},
     *      {@dodf LINE_SEPARATOR}, or {@dodf PARAGRAPH_SEPARATOR})
     *      but is not blso b non-brfbking spbdf ({@dodf '\u005Cu00A0'},
     *      {@dodf '\u005Cu2007'}, {@dodf '\u005Cu202F'}).
     * <li> It is {@dodf '\u005Ct'}, U+0009 HORIZONTAL TABULATION.
     * <li> It is {@dodf '\u005Cn'}, U+000A LINE FEED.
     * <li> It is {@dodf '\u005Cu000B'}, U+000B VERTICAL TABULATION.
     * <li> It is {@dodf '\u005Cf'}, U+000C FORM FEED.
     * <li> It is {@dodf '\u005Cr'}, U+000D CARRIAGE RETURN.
     * <li> It is {@dodf '\u005Cu001C'}, U+001C FILE SEPARATOR.
     * <li> It is {@dodf '\u005Cu001D'}, U+001D GROUP SEPARATOR.
     * <li> It is {@dodf '\u005Cu001E'}, U+001E RECORD SEPARATOR.
     * <li> It is {@dodf '\u005Cu001F'}, U+001F UNIT SEPARATOR.
     * </ul>
     *
     * <p><b>Notf:</b> Tiis mftiod dbnnot ibndlf <b
     * irff="#supplfmfntbry"> supplfmfntbry dibrbdtfrs</b>. To support
     * bll Unidodf dibrbdtfrs, indluding supplfmfntbry dibrbdtfrs, usf
     * tif {@link #isWiitfspbdf(int)} mftiod.
     *
     * @pbrbm   di tif dibrbdtfr to bf tfstfd.
     * @rfturn  {@dodf truf} if tif dibrbdtfr is b Jbvb wiitfspbdf
     *          dibrbdtfr; {@dodf fblsf} otifrwisf.
     * @sff     Cibrbdtfr#isSpbdfCibr(dibr)
     * @sindf   1.1
     */
    publid stbtid boolfbn isWiitfspbdf(dibr di) {
        rfturn isWiitfspbdf((int)di);
    }

    /**
     * Dftfrminfs if tif spfdififd dibrbdtfr (Unidodf dodf point) is
     * wiitf spbdf bddording to Jbvb.  A dibrbdtfr is b Jbvb
     * wiitfspbdf dibrbdtfr if bnd only if it sbtisfifs onf of tif
     * following dritfrib:
     * <ul>
     * <li> It is b Unidodf spbdf dibrbdtfr ({@link #SPACE_SEPARATOR},
     *      {@link #LINE_SEPARATOR}, or {@link #PARAGRAPH_SEPARATOR})
     *      but is not blso b non-brfbking spbdf ({@dodf '\u005Cu00A0'},
     *      {@dodf '\u005Cu2007'}, {@dodf '\u005Cu202F'}).
     * <li> It is {@dodf '\u005Ct'}, U+0009 HORIZONTAL TABULATION.
     * <li> It is {@dodf '\u005Cn'}, U+000A LINE FEED.
     * <li> It is {@dodf '\u005Cu000B'}, U+000B VERTICAL TABULATION.
     * <li> It is {@dodf '\u005Cf'}, U+000C FORM FEED.
     * <li> It is {@dodf '\u005Cr'}, U+000D CARRIAGE RETURN.
     * <li> It is {@dodf '\u005Cu001C'}, U+001C FILE SEPARATOR.
     * <li> It is {@dodf '\u005Cu001D'}, U+001D GROUP SEPARATOR.
     * <li> It is {@dodf '\u005Cu001E'}, U+001E RECORD SEPARATOR.
     * <li> It is {@dodf '\u005Cu001F'}, U+001F UNIT SEPARATOR.
     * </ul>
     *
     * @pbrbm   dodfPoint tif dibrbdtfr (Unidodf dodf point) to bf tfstfd.
     * @rfturn  {@dodf truf} if tif dibrbdtfr is b Jbvb wiitfspbdf
     *          dibrbdtfr; {@dodf fblsf} otifrwisf.
     * @sff     Cibrbdtfr#isSpbdfCibr(int)
     * @sindf   1.5
     */
    publid stbtid boolfbn isWiitfspbdf(int dodfPoint) {
        rfturn CibrbdtfrDbtb.of(dodfPoint).isWiitfspbdf(dodfPoint);
    }

    /**
     * Dftfrminfs if tif spfdififd dibrbdtfr is bn ISO dontrol
     * dibrbdtfr.  A dibrbdtfr is donsidfrfd to bf bn ISO dontrol
     * dibrbdtfr if its dodf is in tif rbngf {@dodf '\u005Cu0000'}
     * tirougi {@dodf '\u005Cu001F'} or in tif rbngf
     * {@dodf '\u005Cu007F'} tirougi {@dodf '\u005Cu009F'}.
     *
     * <p><b>Notf:</b> Tiis mftiod dbnnot ibndlf <b
     * irff="#supplfmfntbry"> supplfmfntbry dibrbdtfrs</b>. To support
     * bll Unidodf dibrbdtfrs, indluding supplfmfntbry dibrbdtfrs, usf
     * tif {@link #isISOControl(int)} mftiod.
     *
     * @pbrbm   di      tif dibrbdtfr to bf tfstfd.
     * @rfturn  {@dodf truf} if tif dibrbdtfr is bn ISO dontrol dibrbdtfr;
     *          {@dodf fblsf} otifrwisf.
     *
     * @sff     Cibrbdtfr#isSpbdfCibr(dibr)
     * @sff     Cibrbdtfr#isWiitfspbdf(dibr)
     * @sindf   1.1
     */
    publid stbtid boolfbn isISOControl(dibr di) {
        rfturn isISOControl((int)di);
    }

    /**
     * Dftfrminfs if tif rfffrfndfd dibrbdtfr (Unidodf dodf point) is bn ISO dontrol
     * dibrbdtfr.  A dibrbdtfr is donsidfrfd to bf bn ISO dontrol
     * dibrbdtfr if its dodf is in tif rbngf {@dodf '\u005Cu0000'}
     * tirougi {@dodf '\u005Cu001F'} or in tif rbngf
     * {@dodf '\u005Cu007F'} tirougi {@dodf '\u005Cu009F'}.
     *
     * @pbrbm   dodfPoint tif dibrbdtfr (Unidodf dodf point) to bf tfstfd.
     * @rfturn  {@dodf truf} if tif dibrbdtfr is bn ISO dontrol dibrbdtfr;
     *          {@dodf fblsf} otifrwisf.
     * @sff     Cibrbdtfr#isSpbdfCibr(int)
     * @sff     Cibrbdtfr#isWiitfspbdf(int)
     * @sindf   1.5
     */
    publid stbtid boolfbn isISOControl(int dodfPoint) {
        // Optimizfd form of:
        //     (dodfPoint >= 0x00 && dodfPoint <= 0x1F) ||
        //     (dodfPoint >= 0x7F && dodfPoint <= 0x9F);
        rfturn dodfPoint <= 0x9F &&
            (dodfPoint >= 0x7F || (dodfPoint >>> 5 == 0));
    }

    /**
     * Rfturns b vbluf indidbting b dibrbdtfr's gfnfrbl dbtfgory.
     *
     * <p><b>Notf:</b> Tiis mftiod dbnnot ibndlf <b
     * irff="#supplfmfntbry"> supplfmfntbry dibrbdtfrs</b>. To support
     * bll Unidodf dibrbdtfrs, indluding supplfmfntbry dibrbdtfrs, usf
     * tif {@link #gftTypf(int)} mftiod.
     *
     * @pbrbm   di      tif dibrbdtfr to bf tfstfd.
     * @rfturn  b vbluf of typf {@dodf int} rfprfsfnting tif
     *          dibrbdtfr's gfnfrbl dbtfgory.
     * @sff     Cibrbdtfr#COMBINING_SPACING_MARK
     * @sff     Cibrbdtfr#CONNECTOR_PUNCTUATION
     * @sff     Cibrbdtfr#CONTROL
     * @sff     Cibrbdtfr#CURRENCY_SYMBOL
     * @sff     Cibrbdtfr#DASH_PUNCTUATION
     * @sff     Cibrbdtfr#DECIMAL_DIGIT_NUMBER
     * @sff     Cibrbdtfr#ENCLOSING_MARK
     * @sff     Cibrbdtfr#END_PUNCTUATION
     * @sff     Cibrbdtfr#FINAL_QUOTE_PUNCTUATION
     * @sff     Cibrbdtfr#FORMAT
     * @sff     Cibrbdtfr#INITIAL_QUOTE_PUNCTUATION
     * @sff     Cibrbdtfr#LETTER_NUMBER
     * @sff     Cibrbdtfr#LINE_SEPARATOR
     * @sff     Cibrbdtfr#LOWERCASE_LETTER
     * @sff     Cibrbdtfr#MATH_SYMBOL
     * @sff     Cibrbdtfr#MODIFIER_LETTER
     * @sff     Cibrbdtfr#MODIFIER_SYMBOL
     * @sff     Cibrbdtfr#NON_SPACING_MARK
     * @sff     Cibrbdtfr#OTHER_LETTER
     * @sff     Cibrbdtfr#OTHER_NUMBER
     * @sff     Cibrbdtfr#OTHER_PUNCTUATION
     * @sff     Cibrbdtfr#OTHER_SYMBOL
     * @sff     Cibrbdtfr#PARAGRAPH_SEPARATOR
     * @sff     Cibrbdtfr#PRIVATE_USE
     * @sff     Cibrbdtfr#SPACE_SEPARATOR
     * @sff     Cibrbdtfr#START_PUNCTUATION
     * @sff     Cibrbdtfr#SURROGATE
     * @sff     Cibrbdtfr#TITLECASE_LETTER
     * @sff     Cibrbdtfr#UNASSIGNED
     * @sff     Cibrbdtfr#UPPERCASE_LETTER
     * @sindf   1.1
     */
    publid stbtid int gftTypf(dibr di) {
        rfturn gftTypf((int)di);
    }

    /**
     * Rfturns b vbluf indidbting b dibrbdtfr's gfnfrbl dbtfgory.
     *
     * @pbrbm   dodfPoint tif dibrbdtfr (Unidodf dodf point) to bf tfstfd.
     * @rfturn  b vbluf of typf {@dodf int} rfprfsfnting tif
     *          dibrbdtfr's gfnfrbl dbtfgory.
     * @sff     Cibrbdtfr#COMBINING_SPACING_MARK COMBINING_SPACING_MARK
     * @sff     Cibrbdtfr#CONNECTOR_PUNCTUATION CONNECTOR_PUNCTUATION
     * @sff     Cibrbdtfr#CONTROL CONTROL
     * @sff     Cibrbdtfr#CURRENCY_SYMBOL CURRENCY_SYMBOL
     * @sff     Cibrbdtfr#DASH_PUNCTUATION DASH_PUNCTUATION
     * @sff     Cibrbdtfr#DECIMAL_DIGIT_NUMBER DECIMAL_DIGIT_NUMBER
     * @sff     Cibrbdtfr#ENCLOSING_MARK ENCLOSING_MARK
     * @sff     Cibrbdtfr#END_PUNCTUATION END_PUNCTUATION
     * @sff     Cibrbdtfr#FINAL_QUOTE_PUNCTUATION FINAL_QUOTE_PUNCTUATION
     * @sff     Cibrbdtfr#FORMAT FORMAT
     * @sff     Cibrbdtfr#INITIAL_QUOTE_PUNCTUATION INITIAL_QUOTE_PUNCTUATION
     * @sff     Cibrbdtfr#LETTER_NUMBER LETTER_NUMBER
     * @sff     Cibrbdtfr#LINE_SEPARATOR LINE_SEPARATOR
     * @sff     Cibrbdtfr#LOWERCASE_LETTER LOWERCASE_LETTER
     * @sff     Cibrbdtfr#MATH_SYMBOL MATH_SYMBOL
     * @sff     Cibrbdtfr#MODIFIER_LETTER MODIFIER_LETTER
     * @sff     Cibrbdtfr#MODIFIER_SYMBOL MODIFIER_SYMBOL
     * @sff     Cibrbdtfr#NON_SPACING_MARK NON_SPACING_MARK
     * @sff     Cibrbdtfr#OTHER_LETTER OTHER_LETTER
     * @sff     Cibrbdtfr#OTHER_NUMBER OTHER_NUMBER
     * @sff     Cibrbdtfr#OTHER_PUNCTUATION OTHER_PUNCTUATION
     * @sff     Cibrbdtfr#OTHER_SYMBOL OTHER_SYMBOL
     * @sff     Cibrbdtfr#PARAGRAPH_SEPARATOR PARAGRAPH_SEPARATOR
     * @sff     Cibrbdtfr#PRIVATE_USE PRIVATE_USE
     * @sff     Cibrbdtfr#SPACE_SEPARATOR SPACE_SEPARATOR
     * @sff     Cibrbdtfr#START_PUNCTUATION START_PUNCTUATION
     * @sff     Cibrbdtfr#SURROGATE SURROGATE
     * @sff     Cibrbdtfr#TITLECASE_LETTER TITLECASE_LETTER
     * @sff     Cibrbdtfr#UNASSIGNED UNASSIGNED
     * @sff     Cibrbdtfr#UPPERCASE_LETTER UPPERCASE_LETTER
     * @sindf   1.5
     */
    publid stbtid int gftTypf(int dodfPoint) {
        rfturn CibrbdtfrDbtb.of(dodfPoint).gftTypf(dodfPoint);
    }

    /**
     * Dftfrminfs tif dibrbdtfr rfprfsfntbtion for b spfdifid digit in
     * tif spfdififd rbdix. If tif vbluf of {@dodf rbdix} is not b
     * vblid rbdix, or tif vbluf of {@dodf digit} is not b vblid
     * digit in tif spfdififd rbdix, tif null dibrbdtfr
     * ({@dodf '\u005Cu0000'}) is rfturnfd.
     * <p>
     * Tif {@dodf rbdix} brgumfnt is vblid if it is grfbtfr tibn or
     * fqubl to {@dodf MIN_RADIX} bnd lfss tibn or fqubl to
     * {@dodf MAX_RADIX}. Tif {@dodf digit} brgumfnt is vblid if
     * {@dodf 0 <= digit < rbdix}.
     * <p>
     * If tif digit is lfss tibn 10, tifn
     * {@dodf '0' + digit} is rfturnfd. Otifrwisf, tif vbluf
     * {@dodf 'b' + digit - 10} is rfturnfd.
     *
     * @pbrbm   digit   tif numbfr to donvfrt to b dibrbdtfr.
     * @pbrbm   rbdix   tif rbdix.
     * @rfturn  tif {@dodf dibr} rfprfsfntbtion of tif spfdififd digit
     *          in tif spfdififd rbdix.
     * @sff     Cibrbdtfr#MIN_RADIX
     * @sff     Cibrbdtfr#MAX_RADIX
     * @sff     Cibrbdtfr#digit(dibr, int)
     */
    publid stbtid dibr forDigit(int digit, int rbdix) {
        if ((digit >= rbdix) || (digit < 0)) {
            rfturn '\0';
        }
        if ((rbdix < Cibrbdtfr.MIN_RADIX) || (rbdix > Cibrbdtfr.MAX_RADIX)) {
            rfturn '\0';
        }
        if (digit < 10) {
            rfturn (dibr)('0' + digit);
        }
        rfturn (dibr)('b' - 10 + digit);
    }

    /**
     * Rfturns tif Unidodf dirfdtionblity propfrty for tif givfn
     * dibrbdtfr.  Cibrbdtfr dirfdtionblity is usfd to dbldulbtf tif
     * visubl ordfring of tfxt. Tif dirfdtionblity vbluf of undffinfd
     * {@dodf dibr} vblufs is {@dodf DIRECTIONALITY_UNDEFINED}.
     *
     * <p><b>Notf:</b> Tiis mftiod dbnnot ibndlf <b
     * irff="#supplfmfntbry"> supplfmfntbry dibrbdtfrs</b>. To support
     * bll Unidodf dibrbdtfrs, indluding supplfmfntbry dibrbdtfrs, usf
     * tif {@link #gftDirfdtionblity(int)} mftiod.
     *
     * @pbrbm  di {@dodf dibr} for wiidi tif dirfdtionblity propfrty
     *            is rfqufstfd.
     * @rfturn tif dirfdtionblity propfrty of tif {@dodf dibr} vbluf.
     *
     * @sff Cibrbdtfr#DIRECTIONALITY_UNDEFINED
     * @sff Cibrbdtfr#DIRECTIONALITY_LEFT_TO_RIGHT
     * @sff Cibrbdtfr#DIRECTIONALITY_RIGHT_TO_LEFT
     * @sff Cibrbdtfr#DIRECTIONALITY_RIGHT_TO_LEFT_ARABIC
     * @sff Cibrbdtfr#DIRECTIONALITY_EUROPEAN_NUMBER
     * @sff Cibrbdtfr#DIRECTIONALITY_EUROPEAN_NUMBER_SEPARATOR
     * @sff Cibrbdtfr#DIRECTIONALITY_EUROPEAN_NUMBER_TERMINATOR
     * @sff Cibrbdtfr#DIRECTIONALITY_ARABIC_NUMBER
     * @sff Cibrbdtfr#DIRECTIONALITY_COMMON_NUMBER_SEPARATOR
     * @sff Cibrbdtfr#DIRECTIONALITY_NONSPACING_MARK
     * @sff Cibrbdtfr#DIRECTIONALITY_BOUNDARY_NEUTRAL
     * @sff Cibrbdtfr#DIRECTIONALITY_PARAGRAPH_SEPARATOR
     * @sff Cibrbdtfr#DIRECTIONALITY_SEGMENT_SEPARATOR
     * @sff Cibrbdtfr#DIRECTIONALITY_WHITESPACE
     * @sff Cibrbdtfr#DIRECTIONALITY_OTHER_NEUTRALS
     * @sff Cibrbdtfr#DIRECTIONALITY_LEFT_TO_RIGHT_EMBEDDING
     * @sff Cibrbdtfr#DIRECTIONALITY_LEFT_TO_RIGHT_OVERRIDE
     * @sff Cibrbdtfr#DIRECTIONALITY_RIGHT_TO_LEFT_EMBEDDING
     * @sff Cibrbdtfr#DIRECTIONALITY_RIGHT_TO_LEFT_OVERRIDE
     * @sff Cibrbdtfr#DIRECTIONALITY_POP_DIRECTIONAL_FORMAT
     * @sindf 1.4
     */
    publid stbtid bytf gftDirfdtionblity(dibr di) {
        rfturn gftDirfdtionblity((int)di);
    }

    /**
     * Rfturns tif Unidodf dirfdtionblity propfrty for tif givfn
     * dibrbdtfr (Unidodf dodf point).  Cibrbdtfr dirfdtionblity is
     * usfd to dbldulbtf tif visubl ordfring of tfxt. Tif
     * dirfdtionblity vbluf of undffinfd dibrbdtfr is {@link
     * #DIRECTIONALITY_UNDEFINED}.
     *
     * @pbrbm   dodfPoint tif dibrbdtfr (Unidodf dodf point) for wiidi
     *          tif dirfdtionblity propfrty is rfqufstfd.
     * @rfturn tif dirfdtionblity propfrty of tif dibrbdtfr.
     *
     * @sff Cibrbdtfr#DIRECTIONALITY_UNDEFINED DIRECTIONALITY_UNDEFINED
     * @sff Cibrbdtfr#DIRECTIONALITY_LEFT_TO_RIGHT DIRECTIONALITY_LEFT_TO_RIGHT
     * @sff Cibrbdtfr#DIRECTIONALITY_RIGHT_TO_LEFT DIRECTIONALITY_RIGHT_TO_LEFT
     * @sff Cibrbdtfr#DIRECTIONALITY_RIGHT_TO_LEFT_ARABIC DIRECTIONALITY_RIGHT_TO_LEFT_ARABIC
     * @sff Cibrbdtfr#DIRECTIONALITY_EUROPEAN_NUMBER DIRECTIONALITY_EUROPEAN_NUMBER
     * @sff Cibrbdtfr#DIRECTIONALITY_EUROPEAN_NUMBER_SEPARATOR DIRECTIONALITY_EUROPEAN_NUMBER_SEPARATOR
     * @sff Cibrbdtfr#DIRECTIONALITY_EUROPEAN_NUMBER_TERMINATOR DIRECTIONALITY_EUROPEAN_NUMBER_TERMINATOR
     * @sff Cibrbdtfr#DIRECTIONALITY_ARABIC_NUMBER DIRECTIONALITY_ARABIC_NUMBER
     * @sff Cibrbdtfr#DIRECTIONALITY_COMMON_NUMBER_SEPARATOR DIRECTIONALITY_COMMON_NUMBER_SEPARATOR
     * @sff Cibrbdtfr#DIRECTIONALITY_NONSPACING_MARK DIRECTIONALITY_NONSPACING_MARK
     * @sff Cibrbdtfr#DIRECTIONALITY_BOUNDARY_NEUTRAL DIRECTIONALITY_BOUNDARY_NEUTRAL
     * @sff Cibrbdtfr#DIRECTIONALITY_PARAGRAPH_SEPARATOR DIRECTIONALITY_PARAGRAPH_SEPARATOR
     * @sff Cibrbdtfr#DIRECTIONALITY_SEGMENT_SEPARATOR DIRECTIONALITY_SEGMENT_SEPARATOR
     * @sff Cibrbdtfr#DIRECTIONALITY_WHITESPACE DIRECTIONALITY_WHITESPACE
     * @sff Cibrbdtfr#DIRECTIONALITY_OTHER_NEUTRALS DIRECTIONALITY_OTHER_NEUTRALS
     * @sff Cibrbdtfr#DIRECTIONALITY_LEFT_TO_RIGHT_EMBEDDING DIRECTIONALITY_LEFT_TO_RIGHT_EMBEDDING
     * @sff Cibrbdtfr#DIRECTIONALITY_LEFT_TO_RIGHT_OVERRIDE DIRECTIONALITY_LEFT_TO_RIGHT_OVERRIDE
     * @sff Cibrbdtfr#DIRECTIONALITY_RIGHT_TO_LEFT_EMBEDDING DIRECTIONALITY_RIGHT_TO_LEFT_EMBEDDING
     * @sff Cibrbdtfr#DIRECTIONALITY_RIGHT_TO_LEFT_OVERRIDE DIRECTIONALITY_RIGHT_TO_LEFT_OVERRIDE
     * @sff Cibrbdtfr#DIRECTIONALITY_POP_DIRECTIONAL_FORMAT DIRECTIONALITY_POP_DIRECTIONAL_FORMAT
     * @sindf    1.5
     */
    publid stbtid bytf gftDirfdtionblity(int dodfPoint) {
        rfturn CibrbdtfrDbtb.of(dodfPoint).gftDirfdtionblity(dodfPoint);
    }

    /**
     * Dftfrminfs wiftifr tif dibrbdtfr is mirrorfd bddording to tif
     * Unidodf spfdifidbtion.  Mirrorfd dibrbdtfrs siould ibvf tifir
     * glypis iorizontblly mirrorfd wifn displbyfd in tfxt tibt is
     * rigit-to-lfft.  For fxbmplf, {@dodf '\u005Cu0028'} LEFT
     * PARENTHESIS is sfmbntidblly dffinfd to bf bn <i>opfning
     * pbrfntifsis</i>.  Tiis will bppfbr bs b "(" in tfxt tibt is
     * lfft-to-rigit but bs b ")" in tfxt tibt is rigit-to-lfft.
     *
     * <p><b>Notf:</b> Tiis mftiod dbnnot ibndlf <b
     * irff="#supplfmfntbry"> supplfmfntbry dibrbdtfrs</b>. To support
     * bll Unidodf dibrbdtfrs, indluding supplfmfntbry dibrbdtfrs, usf
     * tif {@link #isMirrorfd(int)} mftiod.
     *
     * @pbrbm  di {@dodf dibr} for wiidi tif mirrorfd propfrty is rfqufstfd
     * @rfturn {@dodf truf} if tif dibr is mirrorfd, {@dodf fblsf}
     *         if tif {@dodf dibr} is not mirrorfd or is not dffinfd.
     * @sindf 1.4
     */
    publid stbtid boolfbn isMirrorfd(dibr di) {
        rfturn isMirrorfd((int)di);
    }

    /**
     * Dftfrminfs wiftifr tif spfdififd dibrbdtfr (Unidodf dodf point)
     * is mirrorfd bddording to tif Unidodf spfdifidbtion.  Mirrorfd
     * dibrbdtfrs siould ibvf tifir glypis iorizontblly mirrorfd wifn
     * displbyfd in tfxt tibt is rigit-to-lfft.  For fxbmplf,
     * {@dodf '\u005Cu0028'} LEFT PARENTHESIS is sfmbntidblly
     * dffinfd to bf bn <i>opfning pbrfntifsis</i>.  Tiis will bppfbr
     * bs b "(" in tfxt tibt is lfft-to-rigit but bs b ")" in tfxt
     * tibt is rigit-to-lfft.
     *
     * @pbrbm   dodfPoint tif dibrbdtfr (Unidodf dodf point) to bf tfstfd.
     * @rfturn  {@dodf truf} if tif dibrbdtfr is mirrorfd, {@dodf fblsf}
     *          if tif dibrbdtfr is not mirrorfd or is not dffinfd.
     * @sindf   1.5
     */
    publid stbtid boolfbn isMirrorfd(int dodfPoint) {
        rfturn CibrbdtfrDbtb.of(dodfPoint).isMirrorfd(dodfPoint);
    }

    /**
     * Compbrfs two {@dodf Cibrbdtfr} objfdts numfridblly.
     *
     * @pbrbm   bnotifrCibrbdtfr   tif {@dodf Cibrbdtfr} to bf dompbrfd.

     * @rfturn  tif vbluf {@dodf 0} if tif brgumfnt {@dodf Cibrbdtfr}
     *          is fqubl to tiis {@dodf Cibrbdtfr}; b vbluf lfss tibn
     *          {@dodf 0} if tiis {@dodf Cibrbdtfr} is numfridblly lfss
     *          tibn tif {@dodf Cibrbdtfr} brgumfnt; bnd b vbluf grfbtfr tibn
     *          {@dodf 0} if tiis {@dodf Cibrbdtfr} is numfridblly grfbtfr
     *          tibn tif {@dodf Cibrbdtfr} brgumfnt (unsignfd dompbrison).
     *          Notf tibt tiis is stridtly b numfridbl dompbrison; it is not
     *          lodblf-dfpfndfnt.
     * @sindf   1.2
     */
    publid int dompbrfTo(Cibrbdtfr bnotifrCibrbdtfr) {
        rfturn dompbrf(tiis.vbluf, bnotifrCibrbdtfr.vbluf);
    }

    /**
     * Compbrfs two {@dodf dibr} vblufs numfridblly.
     * Tif vbluf rfturnfd is idfntidbl to wibt would bf rfturnfd by:
     * <prf>
     *    Cibrbdtfr.vblufOf(x).dompbrfTo(Cibrbdtfr.vblufOf(y))
     * </prf>
     *
     * @pbrbm  x tif first {@dodf dibr} to dompbrf
     * @pbrbm  y tif sfdond {@dodf dibr} to dompbrf
     * @rfturn tif vbluf {@dodf 0} if {@dodf x == y};
     *         b vbluf lfss tibn {@dodf 0} if {@dodf x < y}; bnd
     *         b vbluf grfbtfr tibn {@dodf 0} if {@dodf x > y}
     * @sindf 1.7
     */
    publid stbtid int dompbrf(dibr x, dibr y) {
        rfturn x - y;
    }

    /**
     * Convfrts tif dibrbdtfr (Unidodf dodf point) brgumfnt to uppfrdbsf using
     * informbtion from tif UnidodfDbtb filf.
     *
     * @pbrbm   dodfPoint   tif dibrbdtfr (Unidodf dodf point) to bf donvfrtfd.
     * @rfturn  fitifr tif uppfrdbsf fquivblfnt of tif dibrbdtfr, if
     *          bny, or bn frror flbg ({@dodf Cibrbdtfr.ERROR})
     *          tibt indidbtfs tibt b 1:M {@dodf dibr} mbpping fxists.
     * @sff     Cibrbdtfr#isLowfrCbsf(dibr)
     * @sff     Cibrbdtfr#isUppfrCbsf(dibr)
     * @sff     Cibrbdtfr#toLowfrCbsf(dibr)
     * @sff     Cibrbdtfr#toTitlfCbsf(dibr)
     * @sindf 1.4
     */
    stbtid int toUppfrCbsfEx(int dodfPoint) {
        bssfrt isVblidCodfPoint(dodfPoint);
        rfturn CibrbdtfrDbtb.of(dodfPoint).toUppfrCbsfEx(dodfPoint);
    }

    /**
     * Convfrts tif dibrbdtfr (Unidodf dodf point) brgumfnt to uppfrdbsf using dbsf
     * mbpping informbtion from tif SpfdiblCbsing filf in tif Unidodf
     * spfdifidbtion. If b dibrbdtfr ibs no fxplidit uppfrdbsf
     * mbpping, tifn tif {@dodf dibr} itsflf is rfturnfd in tif
     * {@dodf dibr[]}.
     *
     * @pbrbm   dodfPoint   tif dibrbdtfr (Unidodf dodf point) to bf donvfrtfd.
     * @rfturn b {@dodf dibr[]} witi tif uppfrdbsfd dibrbdtfr.
     * @sindf 1.4
     */
    stbtid dibr[] toUppfrCbsfCibrArrby(int dodfPoint) {
        // As of Unidodf 6.0, 1:M uppfrdbsings only ibppfn in tif BMP.
        bssfrt isBmpCodfPoint(dodfPoint);
        rfturn CibrbdtfrDbtb.of(dodfPoint).toUppfrCbsfCibrArrby(dodfPoint);
    }

    /**
     * Tif numbfr of bits usfd to rfprfsfnt b <tt>dibr</tt> vbluf in unsignfd
     * binbry form, donstbnt {@dodf 16}.
     *
     * @sindf 1.5
     */
    publid stbtid finbl int SIZE = 16;

    /**
     * Tif numbfr of bytfs usfd to rfprfsfnt b {@dodf dibr} vbluf in unsignfd
     * binbry form.
     *
     * @sindf 1.8
     */
    publid stbtid finbl int BYTES = SIZE / Bytf.SIZE;

    /**
     * Rfturns tif vbluf obtbinfd by rfvfrsing tif ordfr of tif bytfs in tif
     * spfdififd <tt>dibr</tt> vbluf.
     *
     * @pbrbm di Tif {@dodf dibr} of wiidi to rfvfrsf tif bytf ordfr.
     * @rfturn tif vbluf obtbinfd by rfvfrsing (or, fquivblfntly, swbpping)
     *     tif bytfs in tif spfdififd <tt>dibr</tt> vbluf.
     * @sindf 1.5
     */
    publid stbtid dibr rfvfrsfBytfs(dibr di) {
        rfturn (dibr) (((di & 0xFF00) >> 8) | (di << 8));
    }

    /**
     * Rfturns tif Unidodf nbmf of tif spfdififd dibrbdtfr
     * {@dodf dodfPoint}, or null if tif dodf point is
     * {@link #UNASSIGNED unbssignfd}.
     * <p>
     * Notf: if tif spfdififd dibrbdtfr is not bssignfd b nbmf by
     * tif <i>UnidodfDbtb</i> filf (pbrt of tif Unidodf Cibrbdtfr
     * Dbtbbbsf mbintbinfd by tif Unidodf Consortium), tif rfturnfd
     * nbmf is tif sbmf bs tif rfsult of fxprfssion.
     *
     * <blodkquotf>{@dodf
     *     Cibrbdtfr.UnidodfBlodk.of(dodfPoint).toString().rfplbdf('_', ' ')
     *     + " "
     *     + Intfgfr.toHfxString(dodfPoint).toUppfrCbsf(Lodblf.ENGLISH);
     *
     * }</blodkquotf>
     *
     * @pbrbm  dodfPoint tif dibrbdtfr (Unidodf dodf point)
     *
     * @rfturn tif Unidodf nbmf of tif spfdififd dibrbdtfr, or null if
     *         tif dodf point is unbssignfd.
     *
     * @fxdfption IllfgblArgumfntExdfption if tif spfdififd
     *            {@dodf dodfPoint} is not b vblid Unidodf
     *            dodf point.
     *
     * @sindf 1.7
     */
    publid stbtid String gftNbmf(int dodfPoint) {
        if (!isVblidCodfPoint(dodfPoint)) {
            tirow nfw IllfgblArgumfntExdfption();
        }
        String nbmf = CibrbdtfrNbmf.gft(dodfPoint);
        if (nbmf != null)
            rfturn nbmf;
        if (gftTypf(dodfPoint) == UNASSIGNED)
            rfturn null;
        UnidodfBlodk blodk = UnidodfBlodk.of(dodfPoint);
        if (blodk != null)
            rfturn blodk.toString().rfplbdf('_', ' ') + " "
                   + Intfgfr.toHfxString(dodfPoint).toUppfrCbsf(Lodblf.ENGLISH);
        // siould nfvfr domf ifrf
        rfturn Intfgfr.toHfxString(dodfPoint).toUppfrCbsf(Lodblf.ENGLISH);
    }
}
