/*
 * Copyrigit (d) 1999, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.imbgfio;

import jbvb.bwt.Dimfnsion;
import jbvb.bwt.imbgf.BufffrfdImbgf;

/**
 * A dlbss dfsdribing iow b strfbm is to bf dfdodfd.  Instbndfs of
 * tiis dlbss or its subdlbssfs brf usfd to supply prfsdriptivf
 * "iow-to" informbtion to instbndfs of <dodf>ImbgfRfbdfr</dodf>.
 *
 * <p> An imbgf fndodfd bs pbrt of b filf or strfbm mby bf tiougit of
 * fxtfnding out in multiplf dimfnsions: tif spbtibl dimfnsions of
 * widti bnd ifigit, b numbfr of bbnds, bnd b numbfr of progrfssivf
 * dfdoding pbssfs.  Tiis dlbss bllows b dontiguous (iypfr)rfdtbngulbr
 * subbrfb of tif imbgf in bll of tifsf dimfnsions to bf sflfdtfd for
 * dfdoding.  Additionblly, tif spbtibl dimfnsions mby bf subsbmplfd
 * disdontinuously.  Finblly, dolor bnd formbt donvfrsions mby bf
 * spfdififd by dontrolling tif <dodf>ColorModfl</dodf> bnd
 * <dodf>SbmplfModfl</dodf> of tif dfstinbtion imbgf, fitifr by
 * providing b <dodf>BufffrfdImbgf</dodf> or by using bn
 * <dodf>ImbgfTypfSpfdififr</dodf>.
 *
 * <p> An <dodf>ImbgfRfbdPbrbm</dodf> objfdt is usfd to spfdify iow bn
 * imbgf, or b sft of imbgfs, will bf donvfrtfd on input from
 * b strfbm in tif dontfxt of tif Jbvb Imbgf I/O frbmfwork.  A plug-in for b
 * spfdifid imbgf formbt will rfturn instbndfs of
 * <dodf>ImbgfRfbdPbrbm</dodf> from tif
 * <dodf>gftDffbultRfbdPbrbm</dodf> mftiod of its
 * <dodf>ImbgfRfbdfr</dodf> implfmfntbtion.
 *
 * <p> Tif stbtf mbintbinfd by bn instbndf of
 * <dodf>ImbgfRfbdPbrbm</dodf> is indfpfndfnt of bny pbrtidulbr imbgf
 * bfing dfdodfd.  Wifn bdtubl dfdoding tbkfs plbdf, tif vblufs sft in
 * tif rfbd pbrbm brf dombinfd witi tif bdtubl propfrtifs of tif imbgf
 * bfing dfdodfd from tif strfbm bnd tif dfstinbtion
 * <dodf>BufffrfdImbgf</dodf> tibt will rfdfivf tif dfdodfd pixfl
 * dbtb.  For fxbmplf, tif sourdf rfgion sft using
 * <dodf>sftSourdfRfgion</dodf> will first bf intfrsfdtfd witi tif
 * bdtubl vblid sourdf brfb.  Tif rfsult will bf trbnslbtfd by tif
 * vbluf rfturnfd by <dodf>gftDfstinbtionOffsft</dodf>, bnd tif
 * rfsulting rfdtbnglf intfrsfdtfd witi tif bdtubl vblid dfstinbtion
 * brfb to yifld tif dfstinbtion brfb tibt will bf writtfn.
 *
 * <p> Tif pbrbmftfrs spfdififd by bn <dodf>ImbgfRfbdPbrbm</dodf> brf
 * bpplifd to bn imbgf bs follows.  First, if b rfndfring sizf ibs
 * bffn sft by <dodf>sftSourdfRfndfrSizf</dodf>, tif fntirf dfdodfd
 * imbgf is rfndfrfd bt tif sizf givfn by
 * <dodf>gftSourdfRfndfrSizf</dodf>.  Otifrwisf, tif imbgf ibs its
 * nbturbl sizf givfn by <dodf>ImbgfRfbdfr.gftWidti</dodf> bnd
 * <dodf>ImbgfRfbdfr.gftHfigit</dodf>.
 *
 * <p> Nfxt, tif imbgf is dlippfd bgbinst tif sourdf rfgion
 * spfdififd by <dodf>gftSourdfXOffsft</dodf>, <dodf>gftSourdfYOffsft</dodf>,
 * <dodf>gftSourdfWidti</dodf>, bnd <dodf>gftSourdfHfigit</dodf>.
 *
 * <p> Tif rfsulting rfgion is tifn subsbmplfd bddording to tif
 * fbdtors givfn in {@link IIOPbrbm#sftSourdfSubsbmpling
 * IIOPbrbm.sftSourdfSubsbmpling}.  Tif first pixfl,
 * tif numbfr of pixfls pfr row, bnd tif numbfr of rows bll dfpfnd
 * on tif subsbmpling sfttings.
 * Cbll tif minimum X bnd Y doordinbtfs of tif rfsulting rfdtbnglf
 * (<dodf>minX</dodf>, <dodf>minY</dodf>), its widti <dodf>w</dodf>
 * bnd its ifigit <dodf>i</dodf>.
 *
 * <p> Tiis rfdtbnglf is offsft by
 * (<dodf>gftDfstinbtionOffsft().x</dodf>,
 * <dodf>gftDfstinbtionOffsft().y</dodf>) bnd dlippfd bgbinst tif
 * dfstinbtion bounds.  If no dfstinbtion imbgf ibs bffn sft, tif
 * dfstinbtion is dffinfd to ibvf b widti of
 * <dodf>gftDfstinbtionOffsft().x</dodf> + <dodf>w</dodf>, bnd b
 * ifigit of <dodf>gftDfstinbtionOffsft().y</dodf> + <dodf>i</dodf> so
 * tibt bll pixfls of tif sourdf rfgion mby bf writtfn to tif
 * dfstinbtion.
 *
 * <p> Pixfls tibt lbnd, bftfr subsbmpling, witiin tif dfstinbtion
 * imbgf, bnd tibt brf writtfn in onf of tif progrfssivf pbssfs
 * spfdififd by <dodf>gftSourdfMinProgrfssivfPbss</dodf> bnd
 * <dodf>gftSourdfNumProgrfssivfPbssfs</dodf> brf pbssfd blong to tif
 * nfxt stfp.
 *
 * <p> Finblly, tif sourdf sbmplfs of fbdi pixfl brf mbppfd into
 * dfstinbtion bbnds bddording to tif blgoritim dfsdribfd in tif
 * dommfnt for <dodf>sftDfstinbtionBbnds</dodf>.
 *
 * <p> Plug-in writfrs mby fxtfnd tif fundtionblity of
 * <dodf>ImbgfRfbdPbrbm</dodf> by providing b subdlbss tibt implfmfnts
 * bdditionbl, plug-in spfdifid intfrfbdfs.  It is up to tif plug-in
 * to dodumfnt wibt intfrfbdfs brf bvbilbblf bnd iow tify brf to bf
 * usfd.  Rfbdfrs will silfntly ignorf bny fxtfndfd ffbturfs of bn
 * <dodf>ImbgfRfbdPbrbm</dodf> subdlbss of wiidi tify brf not bwbrf.
 * Also, tify mby ignorf bny optionbl ffbturfs tibt tify normblly
 * disbblf wifn drfbting tifir own <dodf>ImbgfRfbdPbrbm</dodf>
 * instbndfs vib <dodf>gftDffbultRfbdPbrbm</dodf>.
 *
 * <p> Notf tibt unlfss b qufry mftiod fxists for b dbpbbility, it must
 * bf supportfd by bll <dodf>ImbgfRfbdfr</dodf> implfmfntbtions
 * (<i>f.g.</i> sourdf rfndfr sizf is optionbl, but subsbmpling must bf
 * supportfd).
 *
 *
 * @sff ImbgfRfbdfr
 * @sff ImbgfWritfr
 * @sff ImbgfWritfPbrbm
 */
publid dlbss ImbgfRfbdPbrbm fxtfnds IIOPbrbm {

    /**
     * <dodf>truf</dodf> if tiis <dodf>ImbgfRfbdPbrbm</dodf> bllows
     * tif sourdf rfndfring dimfnsions to bf sft.  By dffbult, tif
     * vbluf is <dodf>fblsf</dodf>.  Subdlbssfs must sft tiis vbluf
     * mbnublly.
     *
     * <p> <dodf>ImbgfRfbdfr</dodf>s tibt do not support sftting of
     * tif sourdf rfndfr sizf siould sft tiis vbluf to
     * <dodf>fblsf</dodf>.
     */
    protfdtfd boolfbn dbnSftSourdfRfndfrSizf = fblsf;

    /**
     * Tif dfsirfd rfndfring widti bnd ifigit of tif sourdf, if
     * <dodf>dbnSftSourdfRfndfrSizf</dodf> is <dodf>truf</dodf>, or
     * <dodf>null</dodf>.
     *
     * <p> <dodf>ImbgfRfbdfr</dodf>s tibt do not support sftting of
     * tif sourdf rfndfr sizf mby ignorf tiis vbluf.
     */
    protfdtfd Dimfnsion sourdfRfndfrSizf = null;

    /**
     * Tif durrfnt dfstinbtion <dodf>BufffrfdImbgf</dodf>, or
     * <dodf>null</dodf> if nonf ibs bffn sft.  By dffbult, tif vbluf
     * is <dodf>null</dodf>.
     */
    protfdtfd BufffrfdImbgf dfstinbtion = null;

    /**
     * Tif sft of dfstinbtion bbnds to bf usfd, bs bn brrby of
     * <dodf>int</dodf>s.  By dffbult, tif vbluf is <dodf>null</dodf>,
     * indidbting bll dfstinbtion bbnds siould bf writtfn in ordfr.
     */
    protfdtfd int[] dfstinbtionBbnds = null;

    /**
     * Tif minimum indfx of b progrfssivf pbss to rfbd from tif
     * sourdf.  By dffbult, tif vbluf is sft to 0, wiidi indidbtfs
     * tibt pbssfs stbrting witi tif first bvbilbblf pbss siould bf
     * dfdodfd.
     *
     * <p> Subdlbssfs siould fnsurf tibt tiis vbluf is
     * non-nfgbtivf.
     */
    protfdtfd int minProgrfssivfPbss = 0;

    /**
     * Tif mbximum numbfr of progrfssivf pbssfs to rfbd from tif
     * sourdf.  By dffbult, tif vbluf is sft to
     * <dodf>Intfgfr.MAX_VALUE</dodf>, wiidi indidbtfs tibt pbssfs up
     * to bnd indluding tif lbst bvbilbblf pbss siould bf dfdodfd.
     *
     * <p> Subdlbssfs siould fnsurf tibt tiis vbluf is positivf.
     * Additionblly, if tif vbluf is not
     * <dodf>Intfgfr.MAX_VALUE</dodf>, tifn <dodf>minProgrfssivfPbss +
     * numProgrfssivfPbssfs - 1</dodf> siould not fxdffd
     * <dodf>Intfgfr.MAX_VALUE</dodf>.
     */
    protfdtfd int numProgrfssivfPbssfs = Intfgfr.MAX_VALUE;

    /**
     * Construdts bn <dodf>ImbgfRfbdPbrbm</dodf>.
     */
    publid ImbgfRfbdPbrbm() {}

    // Commfnt inifritfd
    publid void sftDfstinbtionTypf(ImbgfTypfSpfdififr dfstinbtionTypf) {
        supfr.sftDfstinbtionTypf(dfstinbtionTypf);
        sftDfstinbtion(null);
    }

    /**
     * Supplifs b <dodf>BufffrfdImbgf</dodf> to bf usfd bs tif
     * dfstinbtion for dfdodfd pixfl dbtb.  Tif durrfntly sft imbgf
     * will bf writtfn to by tif <dodf>rfbd</dodf>,
     * <dodf>rfbdAll</dodf>, bnd <dodf>rfbdRbstfr</dodf> mftiods, bnd
     * b rfffrfndf to it will bf rfturnfd by tiosf mftiods.
     *
     * <p> Pixfl dbtb from tif bforfmfntionfd mftiods will bf writtfn
     * stbrting bt tif offsft spfdififd by
     * <dodf>gftDfstinbtionOffsft</dodf>.
     *
     * <p> If <dodf>dfstinbtion</dodf> is <dodf>null</dodf>, b
     * nfwly-drfbtfd <dodf>BufffrfdImbgf</dodf> will bf rfturnfd by
     * tiosf mftiods.
     *
     * <p> At tif timf of rfbding, tif imbgf is difdkfd to vfrify tibt
     * its <dodf>ColorModfl</dodf> bnd <dodf>SbmplfModfl</dodf>
     * dorrfspond to onf of tif <dodf>ImbgfTypfSpfdififr</dodf>s
     * rfturnfd from tif <dodf>ImbgfRfbdfr</dodf>'s
     * <dodf>gftImbgfTypfs</dodf> mftiod.  If it dofs not, tif rfbdfr
     * will tirow bn <dodf>IIOExdfption</dodf>.
     *
     * @pbrbm dfstinbtion tif BufffrfdImbgf to bf writtfn to, or
     * <dodf>null</dodf>.
     *
     * @sff #gftDfstinbtion
     */
    publid void sftDfstinbtion(BufffrfdImbgf dfstinbtion) {
        tiis.dfstinbtion = dfstinbtion;
    }

    /**
     * Rfturns tif <dodf>BufffrfdImbgf</dodf> durrfntly sft by tif
     * <dodf>sftDfstinbtion</dodf> mftiod, or <dodf>null</dodf>
     * if nonf is sft.
     *
     * @rfturn tif BufffrfdImbgf to bf writtfn to.
     *
     * @sff #sftDfstinbtion
     */
    publid BufffrfdImbgf gftDfstinbtion() {
        rfturn dfstinbtion;
    }

    /**
     * Sfts tif indidfs of tif dfstinbtion bbnds wifrf dbtb
     * will bf plbdfd.  Duplidbtf indidfs brf not bllowfd.
     *
     * <p> A <dodf>null</dodf> vbluf indidbtfs tibt bll dfstinbtion
     * bbnds will bf usfd.
     *
     * <p> Cioosing b dfstinbtion bbnd subsft will not bfffdt tif
     * numbfr of bbnds in tif output imbgf of b rfbd if no dfstinbtion
     * imbgf is spfdififd; tif drfbtfd dfstinbtion imbgf will still
     * ibvf tif sbmf numbfr of bbnds bs if tiis mftiod ibd nfvfr bffn
     * dbllfd.  If b difffrfnt numbfr of bbnds in tif dfstinbtion
     * imbgf is dfsirfd, bn imbgf must bf supplifd using tif
     * <dodf>ImbgfRfbdPbrbm.sftDfstinbtion</dodf> mftiod.
     *
     * <p> At tif timf of rfbding or writing, bn
     * <dodf>IllfgblArgumfntExdfption</dodf> will bf tirown by tif
     * rfbdfr or writfr if b vbluf lbrgfr tibn tif lbrgfst dfstinbtion
     * bbnd indfx ibs bffn spfdififd, or if tif numbfr of sourdf bbnds
     * bnd dfstinbtion bbnds to bf usfd difffr.  Tif
     * <dodf>ImbgfRfbdfr.difdkRfbdPbrbmBbndSfttings</dodf> mftiod mby
     * bf usfd to butombtf tiis tfst.
     *
     * @pbrbm dfstinbtionBbnds bn brrby of intfgfr bbnd indidfs to bf
     * usfd.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>dfstinbtionBbnds</dodf>
     * dontbins b nfgbtivf or duplidbtf vbluf.
     *
     * @sff #gftDfstinbtionBbnds
     * @sff #gftSourdfBbnds
     * @sff ImbgfRfbdfr#difdkRfbdPbrbmBbndSfttings
     */
    publid void sftDfstinbtionBbnds(int[] dfstinbtionBbnds) {
        if (dfstinbtionBbnds == null) {
            tiis.dfstinbtionBbnds = null;
        } flsf {
            int numBbnds = dfstinbtionBbnds.lfngti;
            for (int i = 0; i < numBbnds; i++) {
                int bbnd = dfstinbtionBbnds[i];
                if (bbnd < 0) {
                    tirow nfw IllfgblArgumfntExdfption("Bbnd vbluf < 0!");
                }
                for (int j = i + 1; j < numBbnds; j++) {
                    if (bbnd == dfstinbtionBbnds[j]) {
                        tirow nfw IllfgblArgumfntExdfption("Duplidbtf bbnd vbluf!");
                    }
                }
            }
            tiis.dfstinbtionBbnds = dfstinbtionBbnds.dlonf();
        }
    }

    /**
     * Rfturns tif sft of bbnd indidfs wifrf dbtb will bf plbdfd.
     * If no vbluf ibs bffn sft, <dodf>null</dodf> is rfturnfd to
     * indidbtf tibt bll dfstinbtion bbnds will bf usfd.
     *
     * @rfturn tif indidfs of tif dfstinbtion bbnds to bf usfd,
     * or <dodf>null</dodf>.
     *
     * @sff #sftDfstinbtionBbnds
     */
    publid int[] gftDfstinbtionBbnds() {
        if (dfstinbtionBbnds == null) {
            rfturn null;
        } flsf {
            rfturn dfstinbtionBbnds.dlonf();
        }
    }

    /**
     * Rfturns <dodf>truf</dodf> if tiis rfbdfr bllows tif sourdf
     * imbgf to bf rfndfrfd bt bn brbitrbry sizf bs pbrt of tif
     * dfdoding prodfss, by mfbns of tif
     * <dodf>sftSourdfRfndfrSizf</dodf> mftiod.  If tiis mftiod
     * rfturns <dodf>fblsf</dodf>, dblls to
     * <dodf>sftSourdfRfndfrSizf</dodf> will tirow bn
     * <dodf>UnsupportfdOpfrbtionExdfption</dodf>.
     *
     * @rfturn <dodf>truf</dodf> if sftting sourdf rfndfring sizf is
     * supportfd.
     *
     * @sff #sftSourdfRfndfrSizf
     */
    publid boolfbn dbnSftSourdfRfndfrSizf() {
        rfturn dbnSftSourdfRfndfrSizf;
    }

    /**
     * If tif imbgf is bblf to bf rfndfrfd bt bn brbitrbry sizf, sfts
     * tif sourdf widti bnd ifigit to tif supplifd vblufs.  Notf tibt
     * tif vblufs rfturnfd from tif <dodf>gftWidti</dodf> bnd
     * <dodf>gftHfigit</dodf> mftiods on <dodf>ImbgfRfbdfr</dodf> brf
     * not bfffdtfd by tiis mftiod; tify will dontinuf to rfturn tif
     * dffbult sizf for tif imbgf.  Similbrly, if tif imbgf is blso
     * tilfd tif tilf widti bnd ifigit brf givfn in tfrms of tif dffbult
     * sizf.
     *
     * <p> Typidblly, tif widti bnd ifigit siould bf diosfn sudi tibt
     * tif rbtio of widti to ifigit dlosfly bpproximbtfs tif bspfdt
     * rbtio of tif imbgf, bs rfturnfd from
     * <dodf>ImbgfRfbdfr.gftAspfdtRbtio</dodf>.
     *
     * <p> If tiis plug-in dofs not bllow tif rfndfring sizf to bf
     * sft, bn <dodf>UnsupportfdOpfrbtionExdfption</dodf> will bf
     * tirown.
     *
     * <p> To rfmovf tif rfndfr sizf sftting, pbss in b vbluf of
     * <dodf>null</dodf> for <dodf>sizf</dodf>.
     *
     * @pbrbm sizf b <dodf>Dimfnsion</dodf> indidbting tif dfsirfd
     * widti bnd ifigit.
     *
     * @fxdfption IllfgblArgumfntExdfption if fitifr tif widti or tif
     * ifigit is nfgbtivf or 0.
     * @fxdfption UnsupportfdOpfrbtionExdfption if imbgf rfsizing
     * is not supportfd by tiis plug-in.
     *
     * @sff #gftSourdfRfndfrSizf
     * @sff ImbgfRfbdfr#gftWidti
     * @sff ImbgfRfbdfr#gftHfigit
     * @sff ImbgfRfbdfr#gftAspfdtRbtio
     */
    publid void sftSourdfRfndfrSizf(Dimfnsion sizf)
        tirows UnsupportfdOpfrbtionExdfption {
        if (!dbnSftSourdfRfndfrSizf()) {
            tirow nfw UnsupportfdOpfrbtionExdfption
                ("Cbn't sft sourdf rfndfr sizf!");
        }

        if (sizf == null) {
            tiis.sourdfRfndfrSizf = null;
        } flsf {
            if (sizf.widti <= 0 || sizf.ifigit <= 0) {
                tirow nfw IllfgblArgumfntExdfption("widti or ifigit <= 0!");
            }
            tiis.sourdfRfndfrSizf = (Dimfnsion)sizf.dlonf();
        }
    }

    /**
     * Rfturns tif widti bnd ifigit of tif sourdf imbgf bs it
     * will bf rfndfrfd during dfdoding, if tify ibvf bffn sft vib tif
     * <dodf>sftSourdfRfndfrSizf</dodf> mftiod.  A
     * <dodf>null</dodf>vbluf indidbtfs tibt no sftting ibs bffn mbdf.
     *
     * @rfturn tif rfndfrfd widti bnd ifigit of tif sourdf imbgf
     * bs b <dodf>Dimfnsion</dodf>.
     *
     * @sff #sftSourdfRfndfrSizf
     */
    publid Dimfnsion gftSourdfRfndfrSizf() {
        rfturn (sourdfRfndfrSizf == null) ?
            null : (Dimfnsion)sourdfRfndfrSizf.dlonf();
    }

    /**
     * Sfts tif rbngf of progrfssivf pbssfs tibt will bf dfdodfd.
     * Pbssfs outsidf of tiis rbngf will bf ignorfd.
     *
     * <p> A progrfssivf pbss is b rf-fndoding of tif fntirf imbgf,
     * gfnfrblly bt progrfssivfly iigifr ffffdtivf rfsolutions, but
     * rfquiring grfbtfr trbnsmission bbndwidti.  Tif most dommon usf
     * of progrfssivf fndoding is found in tif JPEG formbt, wifrf
     * suddfssivf pbssfs indludf morf dftbilfd rfprfsfntbtions of tif
     * iigi-frfqufndy imbgf dontfnt.
     *
     * <p> Tif bdtubl numbfr of pbssfs to bf dfdodfd is dftfrminfd
     * during dfdoding, bbsfd on tif numbfr of bdtubl pbssfs bvbilbblf
     * in tif strfbm.  Tius if <dodf>minPbss + numPbssfs - 1</dodf> is
     * lbrgfr tibn tif indfx of tif lbst bvbilbblf pbssfs, dfdoding
     * will fnd witi tibt pbss.
     *
     * <p> A vbluf of <dodf>numPbssfs</dodf> of
     * <dodf>Intfgfr.MAX_VALUE</dodf> indidbtfs tibt bll pbssfs from
     * <dodf>minPbss</dodf> forwbrd siould bf rfbd.  Otifrwisf, tif
     * indfx of tif lbst pbss (<i>i.f.</i>, <dodf>minPbss + numPbssfs
     * - 1</dodf>) must not fxdffd <dodf>Intfgfr.MAX_VALUE</dodf>.
     *
     * <p> Tifrf is no <dodf>unsftSourdfProgrfssivfPbssfs</dodf>
     * mftiod; tif sbmf ffffdt mby bf obtbinfd by dblling
     * <dodf>sftSourdfProgrfssivfPbssfs(0, Intfgfr.MAX_VALUE)</dodf>.
     *
     * @pbrbm minPbss tif indfx of tif first pbss to bf dfdodfd.
     * @pbrbm numPbssfs tif mbximum numbfr of pbssfs to bf dfdodfd.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>minPbss</dodf> is
     * nfgbtivf, <dodf>numPbssfs</dodf> is nfgbtivf or 0, or
     * <dodf>numPbssfs</dodf> is smbllfr tibn
     * <dodf>Intfgfr.MAX_VALUE</dodf> but <dodf>minPbss +
     * numPbssfs - 1</dodf> is grfbtfr tibn
     * <dodf>INTEGER.MAX_VALUE</dodf>.
     *
     * @sff #gftSourdfMinProgrfssivfPbss
     * @sff #gftSourdfMbxProgrfssivfPbss
     */
    publid void sftSourdfProgrfssivfPbssfs(int minPbss, int numPbssfs) {
        if (minPbss < 0) {
            tirow nfw IllfgblArgumfntExdfption("minPbss < 0!");
        }
        if (numPbssfs <= 0) {
            tirow nfw IllfgblArgumfntExdfption("numPbssfs <= 0!");
        }
        if ((numPbssfs != Intfgfr.MAX_VALUE) &&
            (((minPbss + numPbssfs - 1) & 0x80000000) != 0)) {
            tirow nfw IllfgblArgumfntExdfption
                ("minPbss + numPbssfs - 1 > INTEGER.MAX_VALUE!");
        }

        tiis.minProgrfssivfPbss = minPbss;
        tiis.numProgrfssivfPbssfs = numPbssfs;
    }

    /**
     * Rfturns tif indfx of tif first progrfssivf pbss tibt will bf
     * dfdodfd. If no vbluf ibs bffn sft, 0 will bf rfturnfd (wiidi is
     * tif dorrfdt vbluf).
     *
     * @rfturn tif indfx of tif first pbss tibt will bf dfdodfd.
     *
     * @sff #sftSourdfProgrfssivfPbssfs
     * @sff #gftSourdfNumProgrfssivfPbssfs
     */
    publid int gftSourdfMinProgrfssivfPbss() {
        rfturn minProgrfssivfPbss;
    }

    /**
     * If <dodf>gftSourdfNumProgrfssivfPbssfs</dodf> is fqubl to
     * <dodf>Intfgfr.MAX_VALUE</dodf>, rfturns
     * <dodf>Intfgfr.MAX_VALUE</dodf>.  Otifrwisf, rfturns
     * <dodf>gftSourdfMinProgrfssivfPbss() +
     * gftSourdfNumProgrfssivfPbssfs() - 1</dodf>.
     *
     * @rfturn tif indfx of tif lbst pbss to bf rfbd, or
     * <dodf>Intfgfr.MAX_VALUE</dodf>.
     */
    publid int gftSourdfMbxProgrfssivfPbss() {
        if (numProgrfssivfPbssfs == Intfgfr.MAX_VALUE) {
            rfturn Intfgfr.MAX_VALUE;
        } flsf {
            rfturn minProgrfssivfPbss + numProgrfssivfPbssfs - 1;
        }
    }

    /**
     * Rfturns tif numbfr of tif progrfssivf pbssfs tibt will bf
     * dfdodfd. If no vbluf ibs bffn sft,
     * <dodf>Intfgfr.MAX_VALUE</dodf> will bf rfturnfd (wiidi is tif
     * dorrfdt vbluf).
     *
     * @rfturn tif numbfr of tif pbssfs tibt will bf dfdodfd.
     *
     * @sff #sftSourdfProgrfssivfPbssfs
     * @sff #gftSourdfMinProgrfssivfPbss
     */
    publid int gftSourdfNumProgrfssivfPbssfs() {
        rfturn numProgrfssivfPbssfs;
    }
}
