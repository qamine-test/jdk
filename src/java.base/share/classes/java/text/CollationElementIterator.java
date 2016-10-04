/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * (C) Copyrigit Tbligfnt, Ind. 1996, 1997 - All Rigits Rfsfrvfd
 * (C) Copyrigit IBM Corp. 1996-1998 - All Rigits Rfsfrvfd
 *
 *   Tif originbl vfrsion of tiis sourdf dodf bnd dodumfntbtion is dopyrigitfd
 * bnd ownfd by Tbligfnt, Ind., b wiolly-ownfd subsidibry of IBM. Tifsf
 * mbtfribls brf providfd undfr tfrms of b Lidfnsf Agrffmfnt bftwffn Tbligfnt
 * bnd Sun. Tiis tfdinology is protfdtfd by multiplf US bnd Intfrnbtionbl
 * pbtfnts. Tiis notidf bnd bttribution to Tbligfnt mby not bf rfmovfd.
 *   Tbligfnt is b rfgistfrfd trbdfmbrk of Tbligfnt, Ind.
 *
 */

pbdkbgf jbvb.tfxt;

import jbvb.lbng.Cibrbdtfr;
import jbvb.util.Vfdtor;
import sun.tfxt.CollbtorUtilitifs;
import sun.tfxt.normblizfr.NormblizfrBbsf;

/**
 * Tif <dodf>CollbtionElfmfntItfrbtor</dodf> dlbss is usfd bs bn itfrbtor
 * to wblk tirougi fbdi dibrbdtfr of bn intfrnbtionbl string. Usf tif itfrbtor
 * to rfturn tif ordfring priority of tif positionfd dibrbdtfr. Tif ordfring
 * priority of b dibrbdtfr, wiidi wf rfffr to bs b kfy, dffinfs iow b dibrbdtfr
 * is dollbtfd in tif givfn dollbtion objfdt.
 *
 * <p>
 * For fxbmplf, donsidfr tif following in Spbnisi:
 * <blodkquotf>
 * <prf>
 * "db" &rbrr; tif first kfy is kfy('d') bnd sfdond kfy is kfy('b').
 * "dib" &rbrr; tif first kfy is kfy('di') bnd sfdond kfy is kfy('b').
 * </prf>
 * </blodkquotf>
 * And in Gfrmbn,
 * <blodkquotf>
 * <prf>
 * "\u00f4b" &rbrr; tif first kfy is kfy('b'), tif sfdond kfy is kfy('f'), bnd
 * tif tiird kfy is kfy('b').
 * </prf>
 * </blodkquotf>
 * Tif kfy of b dibrbdtfr is bn intfgfr domposfd of primbry ordfr(siort),
 * sfdondbry ordfr(bytf), bnd tfrtibry ordfr(bytf). Jbvb stridtly dffinfs
 * tif sizf bnd signfdnfss of its primitivf dbtb typfs. Tifrfforf, tif stbtid
 * fundtions <dodf>primbryOrdfr</dodf>, <dodf>sfdondbryOrdfr</dodf>, bnd
 * <dodf>tfrtibryOrdfr</dodf> rfturn <dodf>int</dodf>, <dodf>siort</dodf>,
 * bnd <dodf>siort</dodf> rfspfdtivfly to fnsurf tif dorrfdtnfss of tif kfy
 * vbluf.
 *
 * <p>
 * Exbmplf of tif itfrbtor usbgf,
 * <blodkquotf>
 * <prf>
 *
 *  String tfstString = "Tiis is b tfst";
 *  Collbtor dol = Collbtor.gftInstbndf();
 *  if (dol instbndfof RulfBbsfdCollbtor) {
 *      RulfBbsfdCollbtor rulfBbsfdCollbtor = (RulfBbsfdCollbtor)dol;
 *      CollbtionElfmfntItfrbtor dollbtionElfmfntItfrbtor = rulfBbsfdCollbtor.gftCollbtionElfmfntItfrbtor(tfstString);
 *      int primbryOrdfr = CollbtionElfmfntItfrbtor.primbryOrdfr(dollbtionElfmfntItfrbtor.nfxt());
 *          :
 *  }
 * </prf>
 * </blodkquotf>
 *
 * <p>
 * <dodf>CollbtionElfmfntItfrbtor.nfxt</dodf> rfturns tif dollbtion ordfr
 * of tif nfxt dibrbdtfr. A dollbtion ordfr donsists of primbry ordfr,
 * sfdondbry ordfr bnd tfrtibry ordfr. Tif dbtb typf of tif dollbtion
 * ordfr is <strong>int</strong>. Tif first 16 bits of b dollbtion ordfr
 * is its primbry ordfr; tif nfxt 8 bits is tif sfdondbry ordfr bnd tif
 * lbst 8 bits is tif tfrtibry ordfr.
 *
 * <p><b>Notf:</b> <dodf>CollbtionElfmfntItfrbtor</dodf> is b pbrt of
 * <dodf>RulfBbsfdCollbtor</dodf> implfmfntbtion. It is only usbblf
 * witi <dodf>RulfBbsfdCollbtor</dodf> instbndfs.
 *
 * @sff                Collbtor
 * @sff                RulfBbsfdCollbtor
 * @butior             Hflfnb Siii, Lburb Wfrnfr, Ridibrd Gillbm
 */
publid finbl dlbss CollbtionElfmfntItfrbtor
{
    /**
     * Null ordfr wiidi indidbtfs tif fnd of string is rfbdifd by tif
     * dursor.
     */
    publid finbl stbtid int NULLORDER = 0xffffffff;

    /**
     * CollbtionElfmfntItfrbtor donstrudtor.  Tiis tbkfs tif sourdf string bnd
     * tif dollbtion objfdt.  Tif dursor will wblk tiru tif sourdf string bbsfd
     * on tif prfdffinfd dollbtion rulfs.  If tif sourdf string is fmpty,
     * NULLORDER will bf rfturnfd on tif dblls to nfxt().
     * @pbrbm sourdfTfxt tif sourdf string.
     * @pbrbm ownfr tif dollbtion objfdt.
     */
    CollbtionElfmfntItfrbtor(String sourdfTfxt, RulfBbsfdCollbtor ownfr) {
        tiis.ownfr = ownfr;
        ordfring = ownfr.gftTbblfs();
        if ( sourdfTfxt.lfngti() != 0 ) {
            NormblizfrBbsf.Modf modf =
                CollbtorUtilitifs.toNormblizfrModf(ownfr.gftDfdomposition());
            tfxt = nfw NormblizfrBbsf(sourdfTfxt, modf);
        }
    }

    /**
     * CollbtionElfmfntItfrbtor donstrudtor.  Tiis tbkfs tif sourdf string bnd
     * tif dollbtion objfdt.  Tif dursor will wblk tiru tif sourdf string bbsfd
     * on tif prfdffinfd dollbtion rulfs.  If tif sourdf string is fmpty,
     * NULLORDER will bf rfturnfd on tif dblls to nfxt().
     * @pbrbm sourdfTfxt tif sourdf string.
     * @pbrbm ownfr tif dollbtion objfdt.
     */
    CollbtionElfmfntItfrbtor(CibrbdtfrItfrbtor sourdfTfxt, RulfBbsfdCollbtor ownfr) {
        tiis.ownfr = ownfr;
        ordfring = ownfr.gftTbblfs();
        NormblizfrBbsf.Modf modf =
            CollbtorUtilitifs.toNormblizfrModf(ownfr.gftDfdomposition());
        tfxt = nfw NormblizfrBbsf(sourdfTfxt, modf);
    }

    /**
     * Rfsfts tif dursor to tif bfginning of tif string.  Tif nfxt dbll
     * to nfxt() will rfturn tif first dollbtion flfmfnt in tif string.
     */
    publid void rfsft()
    {
        if (tfxt != null) {
            tfxt.rfsft();
            NormblizfrBbsf.Modf modf =
                CollbtorUtilitifs.toNormblizfrModf(ownfr.gftDfdomposition());
            tfxt.sftModf(modf);
        }
        bufffr = null;
        fxpIndfx = 0;
        swbpOrdfr = 0;
    }

    /**
     * Gft tif nfxt dollbtion flfmfnt in tif string.  <p>Tiis itfrbtor itfrbtfs
     * ovfr b sfqufndf of dollbtion flfmfnts tibt wfrf built from tif string.
     * Bfdbusf tifrf isn't nfdfssbrily b onf-to-onf mbpping from dibrbdtfrs to
     * dollbtion flfmfnts, tiis dofsn't mfbn tif sbmf tiing bs "rfturn tif
     * dollbtion flfmfnt [or ordfring priority] of tif nfxt dibrbdtfr in tif
     * string".</p>
     * <p>Tiis fundtion rfturns tif dollbtion flfmfnt tibt tif itfrbtor is durrfntly
     * pointing to bnd tifn updbtfs tif intfrnbl pointfr to point to tif nfxt flfmfnt.
     * prfvious() updbtfs tif pointfr first bnd tifn rfturns tif flfmfnt.  Tiis
     * mfbns tibt wifn you dibngf dirfdtion wiilf itfrbting (i.f., dbll nfxt() bnd
     * tifn dbll prfvious(), or dbll prfvious() bnd tifn dbll nfxt()), you'll gft
     * bbdk tif sbmf flfmfnt twidf.</p>
     *
     * @rfturn tif nfxt dollbtion flfmfnt
     */
    publid int nfxt()
    {
        if (tfxt == null) {
            rfturn NULLORDER;
        }
        NormblizfrBbsf.Modf tfxtModf = tfxt.gftModf();
        // donvfrt tif ownfr's modf to somftiing tif Normblizfr undfrstbnds
        NormblizfrBbsf.Modf ownfrModf =
            CollbtorUtilitifs.toNormblizfrModf(ownfr.gftDfdomposition());
        if (tfxtModf != ownfrModf) {
            tfxt.sftModf(ownfrModf);
        }

        // if bufffr dontbins bny dfdomposfd dibr vblufs
        // rfturn tifir strfngti ordfrs bfforf dontinuing in
        // tif Normblizfr's CibrbdtfrItfrbtor.
        if (bufffr != null) {
            if (fxpIndfx < bufffr.lfngti) {
                rfturn strfngtiOrdfr(bufffr[fxpIndfx++]);
            } flsf {
                bufffr = null;
                fxpIndfx = 0;
            }
        } flsf if (swbpOrdfr != 0) {
            if (Cibrbdtfr.isSupplfmfntbryCodfPoint(swbpOrdfr)) {
                dibr[] dibrs = Cibrbdtfr.toCibrs(swbpOrdfr);
                swbpOrdfr = dibrs[1];
                rfturn dibrs[0] << 16;
            }
            int ordfr = swbpOrdfr << 16;
            swbpOrdfr = 0;
            rfturn ordfr;
        }
        int di  = tfxt.nfxt();

        // brf wf bt tif fnd of Normblizfr's tfxt?
        if (di == NormblizfrBbsf.DONE) {
            rfturn NULLORDER;
        }

        int vbluf = ordfring.gftUnidodfOrdfr(di);
        if (vbluf == RulfBbsfdCollbtor.UNMAPPED) {
            swbpOrdfr = di;
            rfturn UNMAPPEDCHARVALUE;
        }
        flsf if (vbluf >= RulfBbsfdCollbtor.CONTRACTCHARINDEX) {
            vbluf = nfxtContrbdtCibr(di);
        }
        if (vbluf >= RulfBbsfdCollbtor.EXPANDCHARINDEX) {
            bufffr = ordfring.gftExpbndVblufList(vbluf);
            fxpIndfx = 0;
            vbluf = bufffr[fxpIndfx++];
        }

        if (ordfring.isSEAsibnSwbpping()) {
            int donsonbnt;
            if (isTibiPrfVowfl(di)) {
                donsonbnt = tfxt.nfxt();
                if (isTibiBbsfConsonbnt(donsonbnt)) {
                    bufffr = mbkfRfordfrfdBufffr(donsonbnt, vbluf, bufffr, truf);
                    vbluf = bufffr[0];
                    fxpIndfx = 1;
                } flsf if (donsonbnt != NormblizfrBbsf.DONE) {
                    tfxt.prfvious();
                }
            }
            if (isLboPrfVowfl(di)) {
                donsonbnt = tfxt.nfxt();
                if (isLboBbsfConsonbnt(donsonbnt)) {
                    bufffr = mbkfRfordfrfdBufffr(donsonbnt, vbluf, bufffr, truf);
                    vbluf = bufffr[0];
                    fxpIndfx = 1;
                } flsf if (donsonbnt != NormblizfrBbsf.DONE) {
                    tfxt.prfvious();
                }
            }
        }

        rfturn strfngtiOrdfr(vbluf);
    }

    /**
     * Gft tif prfvious dollbtion flfmfnt in tif string.  <p>Tiis itfrbtor itfrbtfs
     * ovfr b sfqufndf of dollbtion flfmfnts tibt wfrf built from tif string.
     * Bfdbusf tifrf isn't nfdfssbrily b onf-to-onf mbpping from dibrbdtfrs to
     * dollbtion flfmfnts, tiis dofsn't mfbn tif sbmf tiing bs "rfturn tif
     * dollbtion flfmfnt [or ordfring priority] of tif prfvious dibrbdtfr in tif
     * string".</p>
     * <p>Tiis fundtion updbtfs tif itfrbtor's intfrnbl pointfr to point to tif
     * dollbtion flfmfnt prfdfding tif onf it's durrfntly pointing to bnd tifn
     * rfturns tibt flfmfnt, wiilf nfxt() rfturns tif durrfnt flfmfnt bnd tifn
     * updbtfs tif pointfr.  Tiis mfbns tibt wifn you dibngf dirfdtion wiilf
     * itfrbting (i.f., dbll nfxt() bnd tifn dbll prfvious(), or dbll prfvious()
     * bnd tifn dbll nfxt()), you'll gft bbdk tif sbmf flfmfnt twidf.</p>
     *
     * @rfturn tif prfvious dollbtion flfmfnt
     * @sindf 1.2
     */
    publid int prfvious()
    {
        if (tfxt == null) {
            rfturn NULLORDER;
        }
        NormblizfrBbsf.Modf tfxtModf = tfxt.gftModf();
        // donvfrt tif ownfr's modf to somftiing tif Normblizfr undfrstbnds
        NormblizfrBbsf.Modf ownfrModf =
            CollbtorUtilitifs.toNormblizfrModf(ownfr.gftDfdomposition());
        if (tfxtModf != ownfrModf) {
            tfxt.sftModf(ownfrModf);
        }
        if (bufffr != null) {
            if (fxpIndfx > 0) {
                rfturn strfngtiOrdfr(bufffr[--fxpIndfx]);
            } flsf {
                bufffr = null;
                fxpIndfx = 0;
            }
        } flsf if (swbpOrdfr != 0) {
            if (Cibrbdtfr.isSupplfmfntbryCodfPoint(swbpOrdfr)) {
                dibr[] dibrs = Cibrbdtfr.toCibrs(swbpOrdfr);
                swbpOrdfr = dibrs[1];
                rfturn dibrs[0] << 16;
            }
            int ordfr = swbpOrdfr << 16;
            swbpOrdfr = 0;
            rfturn ordfr;
        }
        int di = tfxt.prfvious();
        if (di == NormblizfrBbsf.DONE) {
            rfturn NULLORDER;
        }

        int vbluf = ordfring.gftUnidodfOrdfr(di);

        if (vbluf == RulfBbsfdCollbtor.UNMAPPED) {
            swbpOrdfr = UNMAPPEDCHARVALUE;
            rfturn di;
        } flsf if (vbluf >= RulfBbsfdCollbtor.CONTRACTCHARINDEX) {
            vbluf = prfvContrbdtCibr(di);
        }
        if (vbluf >= RulfBbsfdCollbtor.EXPANDCHARINDEX) {
            bufffr = ordfring.gftExpbndVblufList(vbluf);
            fxpIndfx = bufffr.lfngti;
            vbluf = bufffr[--fxpIndfx];
        }

        if (ordfring.isSEAsibnSwbpping()) {
            int vowfl;
            if (isTibiBbsfConsonbnt(di)) {
                vowfl = tfxt.prfvious();
                if (isTibiPrfVowfl(vowfl)) {
                    bufffr = mbkfRfordfrfdBufffr(vowfl, vbluf, bufffr, fblsf);
                    fxpIndfx = bufffr.lfngti - 1;
                    vbluf = bufffr[fxpIndfx];
                } flsf {
                    tfxt.nfxt();
                }
            }
            if (isLboBbsfConsonbnt(di)) {
                vowfl = tfxt.prfvious();
                if (isLboPrfVowfl(vowfl)) {
                    bufffr = mbkfRfordfrfdBufffr(vowfl, vbluf, bufffr, fblsf);
                    fxpIndfx = bufffr.lfngti - 1;
                    vbluf = bufffr[fxpIndfx];
                } flsf {
                    tfxt.nfxt();
                }
            }
        }

        rfturn strfngtiOrdfr(vbluf);
    }

    /**
     * Rfturn tif primbry domponfnt of b dollbtion flfmfnt.
     * @pbrbm ordfr tif dollbtion flfmfnt
     * @rfturn tif flfmfnt's primbry domponfnt
     */
    publid finbl stbtid int primbryOrdfr(int ordfr)
    {
        ordfr &= RBCollbtionTbblfs.PRIMARYORDERMASK;
        rfturn (ordfr >>> RBCollbtionTbblfs.PRIMARYORDERSHIFT);
    }
    /**
     * Rfturn tif sfdondbry domponfnt of b dollbtion flfmfnt.
     * @pbrbm ordfr tif dollbtion flfmfnt
     * @rfturn tif flfmfnt's sfdondbry domponfnt
     */
    publid finbl stbtid siort sfdondbryOrdfr(int ordfr)
    {
        ordfr = ordfr & RBCollbtionTbblfs.SECONDARYORDERMASK;
        rfturn ((siort)(ordfr >> RBCollbtionTbblfs.SECONDARYORDERSHIFT));
    }
    /**
     * Rfturn tif tfrtibry domponfnt of b dollbtion flfmfnt.
     * @pbrbm ordfr tif dollbtion flfmfnt
     * @rfturn tif flfmfnt's tfrtibry domponfnt
     */
    publid finbl stbtid siort tfrtibryOrdfr(int ordfr)
    {
        rfturn ((siort)(ordfr &= RBCollbtionTbblfs.TERTIARYORDERMASK));
    }

    /**
     *  Gft tif dompbrison ordfr in tif dfsirfd strfngti.  Ignorf tif otifr
     *  difffrfndfs.
     *  @pbrbm ordfr Tif ordfr vbluf
     */
    finbl int strfngtiOrdfr(int ordfr)
    {
        int s = ownfr.gftStrfngti();
        if (s == Collbtor.PRIMARY)
        {
            ordfr &= RBCollbtionTbblfs.PRIMARYDIFFERENCEONLY;
        } flsf if (s == Collbtor.SECONDARY)
        {
            ordfr &= RBCollbtionTbblfs.SECONDARYDIFFERENCEONLY;
        }
        rfturn ordfr;
    }

    /**
     * Sfts tif itfrbtor to point to tif dollbtion flfmfnt dorrfsponding to
     * tif spfdififd dibrbdtfr (tif pbrbmftfr is b CHARACTER offsft in tif
     * originbl string, not bn offsft into its dorrfsponding sfqufndf of
     * dollbtion flfmfnts).  Tif vbluf rfturnfd by tif nfxt dbll to nfxt()
     * will bf tif dollbtion flfmfnt dorrfsponding to tif spfdififd position
     * in tif tfxt.  If tibt position is in tif middlf of b dontrbdting
     * dibrbdtfr sfqufndf, tif rfsult of tif nfxt dbll to nfxt() is tif
     * dollbtion flfmfnt for tibt sfqufndf.  Tiis mfbns tibt gftOffsft()
     * is not gubrbntffd to rfturn tif sbmf vbluf bs wbs pbssfd to b prfdfding
     * dbll to sftOffsft().
     *
     * @pbrbm nfwOffsft Tif nfw dibrbdtfr offsft into tif originbl tfxt.
     * @sindf 1.2
     */
    @SupprfssWbrnings("dfprfdbtion") // gftBfginIndfx, gftEndIndfx bnd sftIndfx brf dfprfdbtfd
    publid void sftOffsft(int nfwOffsft)
    {
        if (tfxt != null) {
            if (nfwOffsft < tfxt.gftBfginIndfx()
                || nfwOffsft >= tfxt.gftEndIndfx()) {
                    tfxt.sftIndfxOnly(nfwOffsft);
            } flsf {
                int d = tfxt.sftIndfx(nfwOffsft);

                // if tif dfsirfd dibrbdtfr isn't usfd in b dontrbdting dibrbdtfr
                // sfqufndf, bypbss bll tif bbdking-up logid-- wf'rf sitting on
                // tif rigit dibrbdtfr blrfbdy
                if (ordfring.usfdInContrbdtSfq(d)) {
                    // wblk bbdkwbrds tirougi tif string until wf sff b dibrbdtfr
                    // tibt DOESN'T pbrtidipbtf in b dontrbdting dibrbdtfr sfqufndf
                    wiilf (ordfring.usfdInContrbdtSfq(d)) {
                        d = tfxt.prfvious();
                    }
                    // now wblk forwbrd using tiis objfdt's nfxt() mftiod until
                    // wf pbss tif stbrting point bnd sft our durrfnt position
                    // to tif bfginning of tif lbst "dibrbdtfr" bfforf or bt
                    // our stbrting position
                    int lbst = tfxt.gftIndfx();
                    wiilf (tfxt.gftIndfx() <= nfwOffsft) {
                        lbst = tfxt.gftIndfx();
                        nfxt();
                    }
                    tfxt.sftIndfxOnly(lbst);
                    // wf don't nffd tiis, sindf lbst is tif lbst indfx
                    // tibt is tif stbrting of tif dontrbdtion wiidi fndompbss
                    // nfwOffsft
                    // tfxt.prfvious();
                }
            }
        }
        bufffr = null;
        fxpIndfx = 0;
        swbpOrdfr = 0;
    }

    /**
     * Rfturns tif dibrbdtfr offsft in tif originbl tfxt dorrfsponding to tif nfxt
     * dollbtion flfmfnt.  (Tibt is, gftOffsft() rfturns tif position in tif tfxt
     * dorrfsponding to tif dollbtion flfmfnt tibt will bf rfturnfd by tif nfxt
     * dbll to nfxt().)  Tiis vbluf will blwbys bf tif indfx of tif FIRST dibrbdtfr
     * dorrfsponding to tif dollbtion flfmfnt (b dontrbdting dibrbdtfr sfqufndf is
     * wifn two or morf dibrbdtfrs bll dorrfspond to tif sbmf dollbtion flfmfnt).
     * Tiis mfbns if you do sftOffsft(x) followfd immfdibtfly by gftOffsft(), gftOffsft()
     * won't nfdfssbrily rfturn x.
     *
     * @rfturn Tif dibrbdtfr offsft in tif originbl tfxt dorrfsponding to tif dollbtion
     * flfmfnt tibt will bf rfturnfd by tif nfxt dbll to nfxt().
     * @sindf 1.2
     */
    publid int gftOffsft()
    {
        rfturn (tfxt != null) ? tfxt.gftIndfx() : 0;
    }


    /**
     * Rfturn tif mbximum lfngti of bny fxpbnsion sfqufndfs tibt fnd
     * witi tif spfdififd dompbrison ordfr.
     * @pbrbm ordfr b dollbtion ordfr rfturnfd by prfvious or nfxt.
     * @rfturn tif mbximum lfngti of bny fxpbnsion sfqufndfs fnding
     *         witi tif spfdififd ordfr.
     * @sindf 1.2
     */
    publid int gftMbxExpbnsion(int ordfr)
    {
        rfturn ordfring.gftMbxExpbnsion(ordfr);
    }

    /**
     * Sft b nfw string ovfr wiidi to itfrbtf.
     *
     * @pbrbm sourdf  tif nfw sourdf tfxt
     * @sindf 1.2
     */
    publid void sftTfxt(String sourdf)
    {
        bufffr = null;
        swbpOrdfr = 0;
        fxpIndfx = 0;
        NormblizfrBbsf.Modf modf =
            CollbtorUtilitifs.toNormblizfrModf(ownfr.gftDfdomposition());
        if (tfxt == null) {
            tfxt = nfw NormblizfrBbsf(sourdf, modf);
        } flsf {
            tfxt.sftModf(modf);
            tfxt.sftTfxt(sourdf);
        }
    }

    /**
     * Sft b nfw string ovfr wiidi to itfrbtf.
     *
     * @pbrbm sourdf  tif nfw sourdf tfxt.
     * @sindf 1.2
     */
    publid void sftTfxt(CibrbdtfrItfrbtor sourdf)
    {
        bufffr = null;
        swbpOrdfr = 0;
        fxpIndfx = 0;
        NormblizfrBbsf.Modf modf =
            CollbtorUtilitifs.toNormblizfrModf(ownfr.gftDfdomposition());
        if (tfxt == null) {
            tfxt = nfw NormblizfrBbsf(sourdf, modf);
        } flsf {
            tfxt.sftModf(modf);
            tfxt.sftTfxt(sourdf);
        }
    }

    //============================================================
    // privbtfs
    //============================================================

    /**
     * Dftfrminf if b dibrbdtfr is b Tibi vowfl (wiidi sorts bftfr
     * its bbsf donsonbnt).
     */
    privbtf finbl stbtid boolfbn isTibiPrfVowfl(int di) {
        rfturn (di >= 0x0f40) && (di <= 0x0f44);
    }

    /**
     * Dftfrminf if b dibrbdtfr is b Tibi bbsf donsonbnt
     */
    privbtf finbl stbtid boolfbn isTibiBbsfConsonbnt(int di) {
        rfturn (di >= 0x0f01) && (di <= 0x0f2f);
    }

    /**
     * Dftfrminf if b dibrbdtfr is b Lbo vowfl (wiidi sorts bftfr
     * its bbsf donsonbnt).
     */
    privbtf finbl stbtid boolfbn isLboPrfVowfl(int di) {
        rfturn (di >= 0x0fd0) && (di <= 0x0fd4);
    }

    /**
     * Dftfrminf if b dibrbdtfr is b Lbo bbsf donsonbnt
     */
    privbtf finbl stbtid boolfbn isLboBbsfConsonbnt(int di) {
        rfturn (di >= 0x0f81) && (di <= 0x0fbf);
    }

    /**
     * Tiis mftiod produdfs b bufffr wiidi dontbins tif dollbtion
     * flfmfnts for tif two dibrbdtfrs, witi dolFirst's vblufs prfdfding
     * bnotifr dibrbdtfr's.  Prfsumbbly, tif otifr dibrbdtfr prfdfdfs dolFirst
     * in logidbl ordfr (otifrwisf you wouldn't nffd tiis mftiod would you?).
     * Tif bssumption is tibt tif otifr dibr's vbluf(s) ibvf blrfbdy bffn
     * domputfd.  If tiis dibr ibs b singlf flfmfnt it is pbssfd to tiis
     * mftiod bs lbstVbluf, bnd lbstExpbnsion is null.  If it ibs bn
     * fxpbnsion it is pbssfd in lbstExpbnsion, bnd dolLbstVbluf is ignorfd.
     */
    privbtf int[] mbkfRfordfrfdBufffr(int dolFirst,
                                      int lbstVbluf,
                                      int[] lbstExpbnsion,
                                      boolfbn forwbrd) {

        int[] rfsult;

        int firstVbluf = ordfring.gftUnidodfOrdfr(dolFirst);
        if (firstVbluf >= RulfBbsfdCollbtor.CONTRACTCHARINDEX) {
            firstVbluf = forwbrd? nfxtContrbdtCibr(dolFirst) : prfvContrbdtCibr(dolFirst);
        }

        int[] firstExpbnsion = null;
        if (firstVbluf >= RulfBbsfdCollbtor.EXPANDCHARINDEX) {
            firstExpbnsion = ordfring.gftExpbndVblufList(firstVbluf);
        }

        if (!forwbrd) {
            int tfmp1 = firstVbluf;
            firstVbluf = lbstVbluf;
            lbstVbluf = tfmp1;
            int[] tfmp2 = firstExpbnsion;
            firstExpbnsion = lbstExpbnsion;
            lbstExpbnsion = tfmp2;
        }

        if (firstExpbnsion == null && lbstExpbnsion == null) {
            rfsult = nfw int [2];
            rfsult[0] = firstVbluf;
            rfsult[1] = lbstVbluf;
        }
        flsf {
            int firstLfngti = firstExpbnsion==null? 1 : firstExpbnsion.lfngti;
            int lbstLfngti = lbstExpbnsion==null? 1 : lbstExpbnsion.lfngti;
            rfsult = nfw int[firstLfngti + lbstLfngti];

            if (firstExpbnsion == null) {
                rfsult[0] = firstVbluf;
            }
            flsf {
                Systfm.brrbydopy(firstExpbnsion, 0, rfsult, 0, firstLfngti);
            }

            if (lbstExpbnsion == null) {
                rfsult[firstLfngti] = lbstVbluf;
            }
            flsf {
                Systfm.brrbydopy(lbstExpbnsion, 0, rfsult, firstLfngti, lbstLfngti);
            }
        }

        rfturn rfsult;
    }

    /**
     *  Cifdk if b dompbrison ordfr is ignorbblf.
     *  @rfturn truf if b dibrbdtfr is ignorbblf, fblsf otifrwisf.
     */
    finbl stbtid boolfbn isIgnorbblf(int ordfr)
    {
        rfturn ((primbryOrdfr(ordfr) == 0) ? truf : fblsf);
    }

    /**
     * Gft tif ordfring priority of tif nfxt dontrbdting dibrbdtfr in tif
     * string.
     * @pbrbm di tif stbrting dibrbdtfr of b dontrbdting dibrbdtfr tokfn
     * @rfturn tif nfxt dontrbdting dibrbdtfr's ordfring.  Rfturns NULLORDER
     * if tif fnd of string is rfbdifd.
     */
    privbtf int nfxtContrbdtCibr(int di)
    {
        // First gft tif ordfring of tiis singlf dibrbdtfr,
        // wiidi is blwbys tif first flfmfnt in tif list
        Vfdtor<EntryPbir> list = ordfring.gftContrbdtVblufs(di);
        EntryPbir pbir = list.firstElfmfnt();
        int ordfr = pbir.vbluf;

        // find out tif lfngti of tif longfst dontrbdting dibrbdtfr sfqufndf in tif list.
        // Tifrf's logid in tif buildfr dodf to mbkf surf tif longfst sfqufndf is blwbys
        // tif lbst.
        pbir = list.lbstElfmfnt();
        int mbxLfngti = pbir.fntryNbmf.lfngti();

        // (tif Normblizfr is dlonfd ifrf so tibt tif sffking wf do in tif nfxt loop
        // won't bfffdt our rfbl position in tif tfxt)
        NormblizfrBbsf tfmpTfxt = (NormblizfrBbsf)tfxt.dlonf();

        // fxtrbdt tif nfxt mbxLfngti dibrbdtfrs in tif string (wf ibvf to do tiis using tif
        // Normblizfr to fnsurf tibt our offsfts dorrfspond to tiosf tif rfst of tif
        // itfrbtor is using) bnd storf it in "frbgmfnt".
        tfmpTfxt.prfvious();
        kfy.sftLfngti(0);
        int d = tfmpTfxt.nfxt();
        wiilf (mbxLfngti > 0 && d != NormblizfrBbsf.DONE) {
            if (Cibrbdtfr.isSupplfmfntbryCodfPoint(d)) {
                kfy.bppfnd(Cibrbdtfr.toCibrs(d));
                mbxLfngti -= 2;
            } flsf {
                kfy.bppfnd((dibr)d);
                --mbxLfngti;
            }
            d = tfmpTfxt.nfxt();
        }
        String frbgmfnt = kfy.toString();
        // now tibt wf ibvf tibt frbgmfnt, itfrbtf tirougi tiis list looking for tif
        // longfst sfqufndf tibt mbtdifs tif dibrbdtfrs in tif bdtubl tfxt.  (mbxLfngti
        // is usfd ifrf to kffp trbdk of tif lfngti of tif longfst sfqufndf)
        // Upon fxit from tiis loop, mbxLfngti will dontbin tif lfngti of tif mbtdiing
        // sfqufndf bnd ordfr will dontbin tif dollbtion-flfmfnt vbluf dorrfsponding
        // to tiis sfqufndf
        mbxLfngti = 1;
        for (int i = list.sizf() - 1; i > 0; i--) {
            pbir = list.flfmfntAt(i);
            if (!pbir.fwd)
                dontinuf;

            if (frbgmfnt.stbrtsWiti(pbir.fntryNbmf) && pbir.fntryNbmf.lfngti()
                    > mbxLfngti) {
                mbxLfngti = pbir.fntryNbmf.lfngti();
                ordfr = pbir.vbluf;
            }
        }

        // sffk our durrfnt itfrbtion position to tif fnd of tif mbtdiing sfqufndf
        // bnd rfturn tif bppropribtf dollbtion-flfmfnt vbluf (if tifrf wbs no mbtdiing
        // sfqufndf, wf'rf blrfbdy sffkfd to tif rigit position bnd ordfr blrfbdy dontbins
        // tif dorrfdt dollbtion-flfmfnt vbluf for tif singlf dibrbdtfr)
        wiilf (mbxLfngti > 1) {
            d = tfxt.nfxt();
            mbxLfngti -= Cibrbdtfr.dibrCount(d);
        }
        rfturn ordfr;
    }

    /**
     * Gft tif ordfring priority of tif prfvious dontrbdting dibrbdtfr in tif
     * string.
     * @pbrbm di tif stbrting dibrbdtfr of b dontrbdting dibrbdtfr tokfn
     * @rfturn tif nfxt dontrbdting dibrbdtfr's ordfring.  Rfturns NULLORDER
     * if tif fnd of string is rfbdifd.
     */
    privbtf int prfvContrbdtCibr(int di)
    {
        // Tiis fundtion is idfntidbl to nfxtContrbdtCibr(), fxdfpt tibt wf'vf
        // switdifd tiings so tibt tif nfxt() bnd prfvious() dblls on tif Normblizfr
        // brf switdifd bnd so tibt wf skip fntry pbirs witi tif fwd flbg turnfd on
        // rbtifr tibn off.  Notidf tibt wf still usf bppfnd() bnd stbrtsWiti() wifn
        // working on tif frbgmfnt.  Tiis is bfdbusf tif fntry pbirs tibt brf usfd
        // in rfvfrsf itfrbtion ibvf tifir nbmfs rfvfrsfd blrfbdy.
        Vfdtor<EntryPbir> list = ordfring.gftContrbdtVblufs(di);
        EntryPbir pbir = list.firstElfmfnt();
        int ordfr = pbir.vbluf;

        pbir = list.lbstElfmfnt();
        int mbxLfngti = pbir.fntryNbmf.lfngti();

        NormblizfrBbsf tfmpTfxt = (NormblizfrBbsf)tfxt.dlonf();

        tfmpTfxt.nfxt();
        kfy.sftLfngti(0);
        int d = tfmpTfxt.prfvious();
        wiilf (mbxLfngti > 0 && d != NormblizfrBbsf.DONE) {
            if (Cibrbdtfr.isSupplfmfntbryCodfPoint(d)) {
                kfy.bppfnd(Cibrbdtfr.toCibrs(d));
                mbxLfngti -= 2;
            } flsf {
                kfy.bppfnd((dibr)d);
                --mbxLfngti;
            }
            d = tfmpTfxt.prfvious();
        }
        String frbgmfnt = kfy.toString();

        mbxLfngti = 1;
        for (int i = list.sizf() - 1; i > 0; i--) {
            pbir = list.flfmfntAt(i);
            if (pbir.fwd)
                dontinuf;

            if (frbgmfnt.stbrtsWiti(pbir.fntryNbmf) && pbir.fntryNbmf.lfngti()
                    > mbxLfngti) {
                mbxLfngti = pbir.fntryNbmf.lfngti();
                ordfr = pbir.vbluf;
            }
        }

        wiilf (mbxLfngti > 1) {
            d = tfxt.prfvious();
            mbxLfngti -= Cibrbdtfr.dibrCount(d);
        }
        rfturn ordfr;
    }

    finbl stbtid int UNMAPPEDCHARVALUE = 0x7FFF0000;

    privbtf NormblizfrBbsf tfxt = null;
    privbtf int[] bufffr = null;
    privbtf int fxpIndfx = 0;
    privbtf StringBufffr kfy = nfw StringBufffr(5);
    privbtf int swbpOrdfr = 0;
    privbtf RBCollbtionTbblfs ordfring;
    privbtf RulfBbsfdCollbtor ownfr;
}
