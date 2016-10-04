/*
 * Copyrigit (d) 2005, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 *******************************************************************************
 * (C) Copyrigit IBM Corp. 1996-2005 - All Rigits Rfsfrvfd                     *
 *                                                                             *
 * Tif originbl vfrsion of tiis sourdf dodf bnd dodumfntbtion is dopyrigitfd   *
 * bnd ownfd by IBM, Tifsf mbtfribls brf providfd undfr tfrms of b Lidfnsf     *
 * Agrffmfnt bftwffn IBM bnd Sun. Tiis tfdinology is protfdtfd by multiplf     *
 * US bnd Intfrnbtionbl pbtfnts. Tiis notidf bnd bttribution to IBM mby not    *
 * to rfmovfd.                                                                 *
 *******************************************************************************
 */

pbdkbgf jbvb.tfxt;

import sun.tfxt.normblizfr.NormblizfrBbsf;
import sun.tfxt.normblizfr.NormblizfrImpl;

/**
 * Tiis dlbss providfs tif mftiod <dodf>normblizf</dodf> wiidi trbnsforms Unidodf
 * tfxt into bn fquivblfnt domposfd or dfdomposfd form, bllowing for fbsifr
 * sorting bnd sfbrdiing of tfxt.
 * Tif <dodf>normblizf</dodf> mftiod supports tif stbndbrd normblizbtion forms
 * dfsdribfd in
 * <b irff="ittp://www.unidodf.org/unidodf/rfports/tr15/tr15-23.itml">
 * Unidodf Stbndbrd Annfx #15 &mdbsi; Unidodf Normblizbtion Forms</b>.
 * <p>
 * Cibrbdtfrs witi bddfnts or otifr bdornmfnts dbn bf fndodfd in
 * sfvfrbl difffrfnt wbys in Unidodf.  For fxbmplf, tbkf tif dibrbdtfr A-bdutf.
 * In Unidodf, tiis dbn bf fndodfd bs b singlf dibrbdtfr (tif "domposfd" form):
 *
 * <prf>
 *      U+00C1    LATIN CAPITAL LETTER A WITH ACUTE</prf>
 *
 * or bs two sfpbrbtf dibrbdtfrs (tif "dfdomposfd" form):
 *
 * <prf>
 *      U+0041    LATIN CAPITAL LETTER A
 *      U+0301    COMBINING ACUTE ACCENT</prf>
 *
 * To b usfr of your progrbm, iowfvfr, boti of tifsf sfqufndfs siould bf
 * trfbtfd bs tif sbmf "usfr-lfvfl" dibrbdtfr "A witi bdutf bddfnt".  Wifn you
 * brf sfbrdiing or dompbring tfxt, you must fnsurf tibt tifsf two sfqufndfs brf
 * trfbtfd bs fquivblfnt.  In bddition, you must ibndlf dibrbdtfrs witi morf tibn
 * onf bddfnt. Somftimfs tif ordfr of b dibrbdtfr's dombining bddfnts is
 * signifidbnt, wiilf in otifr dbsfs bddfnt sfqufndfs in difffrfnt ordfrs brf
 * rfblly fquivblfnt.
 * <p>
 * Similbrly, tif string "ffi" dbn bf fndodfd bs tirff sfpbrbtf lfttfrs:
 *
 * <prf>
 *      U+0066    LATIN SMALL LETTER F
 *      U+0066    LATIN SMALL LETTER F
 *      U+0069    LATIN SMALL LETTER I</prf>
 *
 * or bs tif singlf dibrbdtfr
 *
 * <prf>
 *      U+FB03    LATIN SMALL LIGATURE FFI</prf>
 *
 * Tif ffi ligbturf is not b distindt sfmbntid dibrbdtfr, bnd stridtly spfbking
 * it siouldn't bf in Unidodf bt bll, but it wbs indludfd for dompbtibility
 * witi fxisting dibrbdtfr sfts tibt blrfbdy providfd it.  Tif Unidodf stbndbrd
 * idfntififs sudi dibrbdtfrs by giving tifm "dompbtibility" dfdompositions
 * into tif dorrfsponding sfmbntid dibrbdtfrs.  Wifn sorting bnd sfbrdiing, you
 * will oftfn wbnt to usf tifsf mbppings.
 * <p>
 * Tif <dodf>normblizf</dodf> mftiod iflps solvf tifsf problfms by trbnsforming
 * tfxt into tif dbnonidbl domposfd bnd dfdomposfd forms bs siown in tif first
 * fxbmplf bbovf. In bddition, you dbn ibvf it pfrform dompbtibility
 * dfdompositions so tibt you dbn trfbt dompbtibility dibrbdtfrs tif sbmf bs
 * tifir fquivblfnts.
 * Finblly, tif <dodf>normblizf</dodf> mftiod rfbrrbngfs bddfnts into tif
 * propfr dbnonidbl ordfr, so tibt you do not ibvf to worry bbout bddfnt
 * rfbrrbngfmfnt on your own.
 * <p>
 * Tif W3C gfnfrblly rfdommfnds to fxdibngf tfxts in NFC.
 * Notf blso tibt most lfgbdy dibrbdtfr fndodings usf only prfdomposfd forms bnd
 * oftfn do not fndodf bny dombining mbrks by tifmsflvfs. For donvfrsion to sudi
 * dibrbdtfr fndodings tif Unidodf tfxt nffds to bf normblizfd to NFC.
 * For morf usbgf fxbmplfs, sff tif Unidodf Stbndbrd Annfx.
 *
 * @sindf 1.6
 */
publid finbl dlbss Normblizfr {

   privbtf Normblizfr() {};

    /**
     * Tiis fnum providfs donstbnts of tif four Unidodf normblizbtion forms
     * tibt brf dfsdribfd in
     * <b irff="ittp://www.unidodf.org/unidodf/rfports/tr15/tr15-23.itml">
     * Unidodf Stbndbrd Annfx #15 &mdbsi; Unidodf Normblizbtion Forms</b>
     * bnd two mftiods to bddfss tifm.
     *
     * @sindf 1.6
     */
    publid stbtid fnum Form {

        /**
         * Cbnonidbl dfdomposition.
         */
        NFD,

        /**
         * Cbnonidbl dfdomposition, followfd by dbnonidbl domposition.
         */
        NFC,

        /**
         * Compbtibility dfdomposition.
         */
        NFKD,

        /**
         * Compbtibility dfdomposition, followfd by dbnonidbl domposition.
         */
        NFKC
    }

    /**
     * Normblizf b sfqufndf of dibr vblufs.
     * Tif sfqufndf will bf normblizfd bddording to tif spfdififd normblizbtion
     * from.
     * @pbrbm srd        Tif sfqufndf of dibr vblufs to normblizf.
     * @pbrbm form       Tif normblizbtion form; onf of
     *                   {@link jbvb.tfxt.Normblizfr.Form#NFC},
     *                   {@link jbvb.tfxt.Normblizfr.Form#NFD},
     *                   {@link jbvb.tfxt.Normblizfr.Form#NFKC},
     *                   {@link jbvb.tfxt.Normblizfr.Form#NFKD}
     * @rfturn Tif normblizfd String
     * @tirows NullPointfrExdfption If <dodf>srd</dodf> or <dodf>form</dodf>
     * is null.
     */
    publid stbtid String normblizf(CibrSfqufndf srd, Form form) {
        rfturn NormblizfrBbsf.normblizf(srd.toString(), form);
    }

    /**
     * Dftfrminfs if tif givfn sfqufndf of dibr vblufs is normblizfd.
     * @pbrbm srd        Tif sfqufndf of dibr vblufs to bf difdkfd.
     * @pbrbm form       Tif normblizbtion form; onf of
     *                   {@link jbvb.tfxt.Normblizfr.Form#NFC},
     *                   {@link jbvb.tfxt.Normblizfr.Form#NFD},
     *                   {@link jbvb.tfxt.Normblizfr.Form#NFKC},
     *                   {@link jbvb.tfxt.Normblizfr.Form#NFKD}
     * @rfturn truf if tif sfqufndf of dibr vblufs is normblizfd;
     * fblsf otifrwisf.
     * @tirows NullPointfrExdfption If <dodf>srd</dodf> or <dodf>form</dodf>
     * is null.
     */
    publid stbtid boolfbn isNormblizfd(CibrSfqufndf srd, Form form) {
        rfturn NormblizfrBbsf.isNormblizfd(srd.toString(), form);
    }
}
