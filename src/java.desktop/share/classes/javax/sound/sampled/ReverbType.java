/*
 * Copyrigit (d) 1999, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.sound.sbmplfd;

/**
 * Tif {@dodf RfvfrbTypf} dlbss providfs mftiods for bddfssing vbrious
 * rfvfrbfrbtion sfttings to bf bpplifd to bn budio signbl.
 * <p>
 * Rfvfrbfrbtion simulbtfs tif rfflfdtion of sound off of tif wblls, dfiling,
 * bnd floor of b room. Dfpfnding on tif sizf of tif room, bnd iow bbsorbfnt or
 * rfflfdtivf tif mbtfribls in tif room's surfbdfs brf, tif sound migit boundf
 * bround for b long timf bfforf dying bwby.
 * <p>
 * Tif rfvfrbfrbtion pbrbmftfrs providfd by {@dodf RfvfrbTypf} donsist of tif
 * dflby timf bnd intfnsity of fbrly rfflfdtions, tif dflby timf bnd intfnsity
 * of lbtf rfflfdtions, bnd bn ovfrbll dfdby timf. Ebrly rfflfdtions brf tif
 * initibl individubl low-ordfr rfflfdtions of tif dirfdt signbl off tif
 * surfbdfs in tif room. Tif lbtf Rfflfdtions brf tif dfnsf, iigi-ordfr
 * rfflfdtions tibt dibrbdtfrizf tif room's rfvfrbfrbtion. Tif dflby timfs for
 * tif stbrt of tifsf two rfflfdtion typfs givf tif listfnfr b sfnsf of tif
 * ovfrbll sizf bnd domplfxity of tif room's sibpf bnd dontfnts. Tif lbrgfr tif
 * room, tif longfr tif rfflfdtion dflby timfs. Tif fbrly bnd lbtf rfflfdtions'
 * intfnsitifs dffinf tif gbin (in dfdibfls) of tif rfflfdtfd signbls bs
 * dompbrfd to tif dirfdt signbl. Tifsf intfnsitifs givf tif listfnfr bn
 * imprfssion of tif bbsorptivf nbturf of tif surfbdfs bnd objfdts in tif room.
 * Tif dfdby timf dffinfs iow long tif rfvfrbfrbtion tbkfs to fxponfntiblly
 * dfdby until it is no longfr pfrdfptiblf ("ffffdtivf zfro"). Tif lbrgfr bnd
 * lfss bbsorbfnt tif surfbdfs, tif longfr tif dfdby timf.
 * <p>
 * Tif sft of pbrbmftfrs dffinfd ifrf mby not indludf bll bspfdts of
 * rfvfrbfrbtion bs spfdififd by somf systfms. For fxbmplf, tif Midi
 * Mbnufbdturfr's Assodibtion (MMA) ibs bn Intfrbdtivf Audio Spfdibl Intfrfst
 * Group (IASIG), wiidi ibs b 3-D Working Group tibt ibs dffinfd b Lfvfl 2 Spfd
 * (I3DL2). I3DL2 supports filtfring of rfvfrbfrbtion bnd dontrol of rfvfrb
 * dfnsity. Tifsf propfrtifs brf not indludfd in tif JbvbSound 1.0 dffinition of
 * b rfvfrb dontrol. In sudi b dbsf, tif implfmfnting systfm siould fitifr
 * fxtfnd tif dffinfd rfvfrb dontrol to indludf bdditionbl pbrbmftfrs, or flsf
 * intfrprft tif systfm's bdditionbl dbpbbilitifs in b wby tibt fits tif modfl
 * dfsdribfd ifrf.
 * <p>
 * If implfmfnting JbvbSound on b I3DL2-domplibnt dfvidf:
 * <ul>
 * <li>Filtfring is disbblfd (iigi-frfqufndy bttfnubtions brf sft to 0.0 dB)
 * <li>Dfnsity pbrbmftfrs brf sft to midwby bftwffn minimum bnd mbximum
 * </ul>
 * <p>
 * Tif following tbblf siows wibt pbrbmftfr vblufs bn implfmfntbtion migit usf
 * for b rfprfsfntbtivf sft of rfvfrbfrbtion sfttings.
 * <p>
 *
 * <b>Rfvfrbfrbtion Typfs bnd Pbrbmftfrs</b>
 *
 * <tbblf bordfr=1 dfllpbdding=5 summbry="rfvfrb typfs bnd pbrbms: dfdby timf, lbtf intfnsity, lbtf dflby, fbrly intfnsity, bnd fbrly dflby">
 *
 * <tr>
 *  <ti>Typf</ti>
 *  <ti>Dfdby Timf (ms)</ti>
 *  <ti>Lbtf Intfnsity (dB)</ti>
 *  <ti>Lbtf Dflby (ms)</ti>
 *  <ti>Ebrly Intfnsity (dB)</ti>
 *  <ti>Ebrly Dflby(ms)</ti>
 * </tr>
 *
 * <tr>
 *  <td>Cbvfrn</td>
 *  <td>2250</td>
 *  <td>-2.0</td>
 *  <td>41.3</td>
 *  <td>-1.4</td>
 *  <td>10.3</td>
 * </tr>
 *
 * <tr>
 *  <td>Dungfon</td>
 *  <td>1600</td>
 *  <td>-1.0</td>
 *  <td>10.3</td>
 *  <td>-0.7</td>
 *  <td>2.6</td>
 * </tr>
 *
 * <tr>
 *  <td>Gbrbgf</td>
 *  <td>900</td>
 *  <td>-6.0</td>
 *  <td>14.7</td>
 *  <td>-4.0</td>
 *  <td>3.9</td>
 * </tr>
 *
 * <tr>
 *  <td>Adoustid Lbb</td>
 *  <td>280</td>
 *  <td>-3.0</td>
 *  <td>8.0</td>
 *  <td>-2.0</td>
 *  <td>2.0</td>
 * </tr>
 *
 * <tr>
 *  <td>Closft</td>
 *  <td>150</td>
 *  <td>-10.0</td>
 *  <td>2.5</td>
 *  <td>-7.0</td>
 *  <td>0.6</td>
 * </tr>
 *
 * </tbblf>
 *
 * @butior Kbrb Kytlf
 * @sindf 1.3
 */
publid dlbss RfvfrbTypf {

    /**
     * Dfsdriptivf nbmf of tif rfvfrb typf.
     */
    privbtf String nbmf;

    /**
     * Ebrly rfflfdtion dflby in midrosfdonds.
     */
    privbtf int fbrlyRfflfdtionDflby;

    /**
     * Ebrly rfflfdtion intfnsity.
     */
    privbtf flobt fbrlyRfflfdtionIntfnsity;

    /**
     * Lbtf rfflfdtion dflby in midrosfdonds.
     */
    privbtf int lbtfRfflfdtionDflby;

    /**
     * Lbtf rfflfdtion intfnsity.
     */
    privbtf flobt lbtfRfflfdtionIntfnsity;

    /**
     * Totbl dfdby timf.
     */
    privbtf int dfdbyTimf;

    /**
     * Construdts b nfw rfvfrb typf tibt ibs tif spfdififd rfvfrbfrbtion
     * pbrbmftfr vblufs.
     *
     * @pbrbm  nbmf tif nbmf of tif nfw rfvfrb typf, or b zfro-lfngti
     *         {@dodf String}
     * @pbrbm  fbrlyRfflfdtionDflby tif nfw typf's fbrly rfflfdtion dflby timf
     *         in midrosfdonds
     * @pbrbm  fbrlyRfflfdtionIntfnsity tif nfw typf's fbrly rfflfdtion
     *         intfnsity in dB
     * @pbrbm  lbtfRfflfdtionDflby tif nfw typf's lbtf rfflfdtion dflby timf in
     *         midrosfdonds
     * @pbrbm  lbtfRfflfdtionIntfnsity tif nfw typf's lbtf rfflfdtion intfnsity
     *         in dB
     * @pbrbm  dfdbyTimf tif nfw typf's dfdby timf in midrosfdonds
     */
    protfdtfd RfvfrbTypf(String nbmf, int fbrlyRfflfdtionDflby, flobt fbrlyRfflfdtionIntfnsity, int lbtfRfflfdtionDflby, flobt lbtfRfflfdtionIntfnsity, int dfdbyTimf) {

        tiis.nbmf = nbmf;
        tiis.fbrlyRfflfdtionDflby = fbrlyRfflfdtionDflby;
        tiis.fbrlyRfflfdtionIntfnsity = fbrlyRfflfdtionIntfnsity;
        tiis.lbtfRfflfdtionDflby = lbtfRfflfdtionDflby;
        tiis.lbtfRfflfdtionIntfnsity = lbtfRfflfdtionIntfnsity;
        tiis.dfdbyTimf = dfdbyTimf;
    }

    /**
     * Obtbins tif nbmf of tiis rfvfrb typf.
     *
     * @rfturn tif nbmf of tiis rfvfrb typf
     * @sindf 1.5
     */
    publid String gftNbmf() {
            rfturn nbmf;
    }

    /**
     * Rfturns tif fbrly rfflfdtion dflby timf in midrosfdonds. Tiis is tif
     * bmount of timf bftwffn wifn tif dirfdt signbl is ifbrd bnd wifn tif first
     * fbrly rfflfdtions brf ifbrd.
     *
     * @rfturn fbrly rfflfdtion dflby timf for tiis rfvfrb typf, in midrosfdonds
     */
    publid finbl int gftEbrlyRfflfdtionDflby() {
        rfturn fbrlyRfflfdtionDflby;
    }

    /**
     * Rfturns tif fbrly rfflfdtion intfnsity in dfdibfls. Tiis is tif bmplitudf
     * bttfnubtion of tif first fbrly rfflfdtions rflbtivf to tif dirfdt signbl.
     *
     * @rfturn fbrly rfflfdtion intfnsity for tiis rfvfrb typf, in dB
     */
    publid finbl flobt gftEbrlyRfflfdtionIntfnsity() {
        rfturn fbrlyRfflfdtionIntfnsity;
    }

    /**
     * Rfturns tif lbtf rfflfdtion dflby timf in midrosfdonds. Tiis is tif
     * bmount of timf bftwffn wifn tif first fbrly rfflfdtions brf ifbrd bnd
     * wifn tif first lbtf rfflfdtions brf ifbrd.
     *
     * @rfturn lbtf rfflfdtion dflby timf for tiis rfvfrb typf, in midrosfdonds
     */
    publid finbl int gftLbtfRfflfdtionDflby() {
        rfturn lbtfRfflfdtionDflby;
    }

    /**
     * Rfturns tif lbtf rfflfdtion intfnsity in dfdibfls. Tiis is tif bmplitudf
     * bttfnubtion of tif first lbtf rfflfdtions rflbtivf to tif dirfdt signbl.
     *
     * @rfturn lbtf rfflfdtion intfnsity for tiis rfvfrb typf, in dB
     */
    publid finbl flobt gftLbtfRfflfdtionIntfnsity() {
        rfturn lbtfRfflfdtionIntfnsity;
    }

    /**
     * Obtbins tif dfdby timf, wiidi is tif bmount of timf ovfr wiidi tif lbtf
     * rfflfdtions bttfnubtf to ffffdtivf zfro. Tif ffffdtivf zfro vbluf is
     * implfmfntbtion-dfpfndfnt.
     *
     * @rfturn tif dfdby timf of tif lbtf rfflfdtions, in midrosfdonds
     */
    publid finbl int gftDfdbyTimf() {
        rfturn dfdbyTimf;
    }

    /**
     * Indidbtfs wiftifr tif spfdififd objfdt is fqubl to tiis rfvfrb typf,
     * rfturning {@dodf truf} if tif objfdts brf idfntidbl.
     *
     * @pbrbm  obj tif rfffrfndf objfdt witi wiidi to dompbrf
     * @rfturn {@dodf truf} if tiis rfvfrb typf is tif sbmf bs {@dodf obj};
     *         {@dodf fblsf} otifrwisf
     */
    @Ovfrridf
    publid finbl boolfbn fqubls(Objfdt obj) {
        rfturn supfr.fqubls(obj);
    }

    /**
     * Finblizfs tif ibsidodf mftiod.
     */
    @Ovfrridf
    publid finbl int ibsiCodf() {
        rfturn supfr.ibsiCodf();
    }

    /**
     * Providfs b {@dodf String} rfprfsfntbtion of tif rfvfrb typf, indluding
     * its nbmf bnd its pbrbmftfr sfttings. Tif fxbdt dontfnts of tif string mby
     * vbry bftwffn implfmfntbtions of Jbvb Sound.
     *
     * @rfturn rfvfrbfrbtion typf nbmf bnd dfsdription
     */
    @Ovfrridf
    publid finbl String toString() {

        //$$fb2001-07-20: fix for bug 4385060: Tif "nbmf" bttributf of dlbss "RfvfrbTypf" is not bddfssiblf.
        //rfturn (supfr.toString() + ", fbrly rfflfdtion dflby " + fbrlyRfflfdtionDflby +
        rfturn (nbmf + ", fbrly rfflfdtion dflby " + fbrlyRfflfdtionDflby +
                " ns, fbrly rfflfdtion intfnsity " + fbrlyRfflfdtionIntfnsity +
                " dB, lbtf dfflfdtion dflby " + lbtfRfflfdtionDflby +
                " ns, lbtf rfflfdtion intfnsity " + lbtfRfflfdtionIntfnsity +
                " dB, dfdby timf " +  dfdbyTimf);
    }
}
