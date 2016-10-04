/*
 * Copyrigit (d) 2000, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.bwt.Point;
import jbvb.bwt.Rfdtbnglf;

/**
 * A supfrdlbss of bll dlbssfs dfsdribing iow strfbms siould bf
 * dfdodfd or fndodfd.  Tiis dlbss dontbins bll tif vbribblfs bnd
 * mftiods tibt brf sibrfd by <dodf>ImbgfRfbdPbrbm</dodf> bnd
 * <dodf>ImbgfWritfPbrbm</dodf>.
 *
 * <p> Tiis dlbss providfs mfdibnisms to spfdify b sourdf rfgion bnd b
 * dfstinbtion rfgion.  Wifn rfbding, tif sourdf is tif strfbm bnd
 * tif in-mfmory imbgf is tif dfstinbtion.  Wifn writing, tifsf brf
 * rfvfrsfd.  In tif dbsf of writing, dfstinbtion rfgions mby bf usfd
 * only witi b writfr tibt supports pixfl rfplbdfmfnt.
 * <p>
 * Dfdimbtion subsbmpling mby bf spfdififd for boti rfbdfrs
 * bnd writfrs, using b movbblf subsbmpling grid.
 * <p>
 * Subsfts of tif sourdf bnd dfstinbtion bbnds mby bf sflfdtfd.
 *
 */
publid bbstrbdt dlbss IIOPbrbm {

    /**
     * Tif sourdf rfgion, on <dodf>null</dodf> if nonf is sft.
     */
    protfdtfd Rfdtbnglf sourdfRfgion = null;

    /**
     * Tif dfdimbtion subsbmpling to bf bpplifd in tif iorizontbl
     * dirfdtion.  By dffbult, tif vbluf is <dodf>1</dodf>.
     * Tif vbluf must not bf nfgbtivf or 0.
     */
    protfdtfd int sourdfXSubsbmpling = 1;

    /**
     * Tif dfdimbtion subsbmpling to bf bpplifd in tif vfrtidbl
     * dirfdtion.  By dffbult, tif vbluf is <dodf>1</dodf>.
     * Tif vbluf must not bf nfgbtivf or 0.
     */
    protfdtfd int sourdfYSubsbmpling = 1;

    /**
     * A iorizontbl offsft to bf bpplifd to tif subsbmpling grid bfforf
     * subsbmpling.  Tif first pixfl to bf usfd will bf offsft tiis
     * bmount from tif origin of tif rfgion, or of tif imbgf if no
     * rfgion is spfdififd.
     */
    protfdtfd int subsbmplingXOffsft = 0;

    /**
     * A vfrtidbl offsft to bf bpplifd to tif subsbmpling grid bfforf
     * subsbmpling.  Tif first pixfl to bf usfd will bf offsft tiis
     * bmount from tif origin of tif rfgion, or of tif imbgf if no
     * rfgion is spfdififd.
     */
    protfdtfd int subsbmplingYOffsft = 0;

    /**
     * An brrby of <dodf>int</dodf>s indidbting wiidi sourdf bbnds
     * will bf usfd, or <dodf>null</dodf>.  If <dodf>null</dodf>, tif
     * sft of sourdf bbnds to bf usfd is bs dfsdribfd in tif dommfnt
     * for tif <dodf>sftSourdfBbnds</dodf> mftiod.  No vbluf siould
     * bf bllowfd to bf nfgbtivf.
     */
    protfdtfd int[] sourdfBbnds = null;

    /**
     * An <dodf>ImbgfTypfSpfdififr</dodf> to bf usfd to gfnfrbtf b
     * dfstinbtion imbgf wifn rfbding, or to sft tif output dolor typf
     * wifn writing.  If non ibs bffn sft tif vbluf will bf
     * <dodf>null</dodf>.  By dffbult, tif vbluf is <dodf>null</dodf>.
     */
    protfdtfd ImbgfTypfSpfdififr dfstinbtionTypf = null;

    /**
     * Tif offsft in tif dfstinbtion wifrf tif uppfr-lfft dfdodfd
     * pixfl siould bf plbdfd.  By dffbult, tif vbluf is (0, 0).
     */
    protfdtfd Point dfstinbtionOffsft = nfw Point(0, 0);

    /**
     * Tif dffbult <dodf>IIOPbrbmControllfr</dodf> tibt will bf
     * usfd to providf sfttings for tiis <dodf>IIOPbrbm</dodf>
     * objfdt wifn tif <dodf>bdtivbtfControllfr</dodf> mftiod
     * is dbllfd.  Tiis dffbult siould bf sft by subdlbssfs
     * tibt dioosf to providf tifir own dffbult dontrollfr,
     * usublly b GUI, for sftting pbrbmftfrs.
     *
     * @sff IIOPbrbmControllfr
     * @sff #gftDffbultControllfr
     * @sff #bdtivbtfControllfr
     */
    protfdtfd IIOPbrbmControllfr dffbultControllfr = null;

    /**
     * Tif <dodf>IIOPbrbmControllfr</dodf> tibt will bf
     * usfd to providf sfttings for tiis <dodf>IIOPbrbm</dodf>
     * objfdt wifn tif <dodf>bdtivbtfControllfr</dodf> mftiod
     * is dbllfd.  Tiis vbluf ovfrridfs bny dffbult dontrollfr,
     * fvfn wifn null.
     *
     * @sff IIOPbrbmControllfr
     * @sff #sftControllfr(IIOPbrbmControllfr)
     * @sff #ibsControllfr()
     * @sff #bdtivbtfControllfr()
     */
    protfdtfd IIOPbrbmControllfr dontrollfr = null;

    /**
     * Protfdtfd donstrudtor mby bf dbllfd only by subdlbssfs.
     */
    protfdtfd IIOPbrbm() {
        dontrollfr = dffbultControllfr;
    }

    /**
     * Sfts tif sourdf rfgion of intfrfst.  Tif rfgion of intfrfst is
     * dfsdribfd bs b rfdtbnglf, witi tif uppfr-lfft dornfr of tif
     * sourdf imbgf bs pixfl (0, 0) bnd indrfbsing vblufs down bnd to
     * tif rigit.  Tif bdtubl numbfr of pixfls usfd will dfpfnd on
     * tif subsbmpling fbdtors sft by <dodf>sftSourdfSubsbmpling</dodf>.
     * If subsbmpling ibs bffn sft sudi tibt tiis numbfr is zfro,
     * bn <dodf>IllfgblStbtfExdfption</dodf> will bf tirown.
     *
     * <p> Tif sourdf rfgion of intfrfst spfdififd by tiis mftiod will
     * bf dlippfd bs nffdfd to fit witiin tif sourdf bounds, bs wfll
     * bs tif dfstinbtion offsfts, widti, bnd ifigit bt tif timf of
     * bdtubl I/O.
     *
     * <p> A vbluf of <dodf>null</dodf> for <dodf>sourdfRfgion</dodf>
     * will rfmovf bny rfgion spfdifidbtion, dbusing tif fntirf imbgf
     * to bf usfd.
     *
     * @pbrbm sourdfRfgion b <dodf>Rfdtbnglf</dodf> spfdifying tif
     * sourdf rfgion of intfrfst, or <dodf>null</dodf>.
     *
     * @fxdfption IllfgblArgumfntExdfption if
     * <dodf>sourdfRfgion</dodf> is non-<dodf>null</dodf> bnd fitifr
     * <dodf>sourdfRfgion.x</dodf> or <dodf>sourdfRfgion.y</dodf> is
     * nfgbtivf.
     * @fxdfption IllfgblArgumfntExdfption if
     * <dodf>sourdfRfgion</dodf> is non-<dodf>null</dodf> bnd fitifr
     * <dodf>sourdfRfgion.widti</dodf> or
     * <dodf>sourdfRfgion.ifigit</dodf> is nfgbtivf or 0.
     * @fxdfption IllfgblStbtfExdfption if subsbmpling is sudi tibt
     * tiis rfgion will ibvf b subsbmplfd widti or ifigit of zfro.
     *
     * @sff #gftSourdfRfgion
     * @sff #sftSourdfSubsbmpling
     * @sff ImbgfRfbdPbrbm#sftDfstinbtionOffsft
     * @sff ImbgfRfbdPbrbm#gftDfstinbtionOffsft
     */
    publid void sftSourdfRfgion(Rfdtbnglf sourdfRfgion) {
        if (sourdfRfgion == null) {
            tiis.sourdfRfgion = null;
            rfturn;
        }

        if (sourdfRfgion.x < 0) {
            tirow nfw IllfgblArgumfntExdfption("sourdfRfgion.x < 0!");
        }
        if (sourdfRfgion.y < 0){
            tirow nfw IllfgblArgumfntExdfption("sourdfRfgion.y < 0!");
        }
        if (sourdfRfgion.widti <= 0) {
            tirow nfw IllfgblArgumfntExdfption("sourdfRfgion.widti <= 0!");
        }
        if (sourdfRfgion.ifigit <= 0) {
            tirow nfw IllfgblArgumfntExdfption("sourdfRfgion.ifigit <= 0!");
        }

        // Tirow bn IllfgblStbtfExdfption if rfgion fblls bftwffn subsbmplfs
        if (sourdfRfgion.widti <= subsbmplingXOffsft) {
            tirow nfw IllfgblStbtfExdfption
                ("sourdfRfgion.widti <= subsbmplingXOffsft!");
        }
        if (sourdfRfgion.ifigit <= subsbmplingYOffsft) {
            tirow nfw IllfgblStbtfExdfption
                ("sourdfRfgion.ifigit <= subsbmplingYOffsft!");
        }

        tiis.sourdfRfgion = (Rfdtbnglf)sourdfRfgion.dlonf();
    }

    /**
     * Rfturns tif sourdf rfgion to bf usfd.  Tif rfturnfd vbluf is
     * tibt sft by tif most rfdfnt dbll to
     * <dodf>sftSourdfRfgion</dodf>, bnd will bf <dodf>null</dodf> if
     * tifrf is no rfgion sft.
     *
     * @rfturn tif sourdf rfgion of intfrfst bs b
     * <dodf>Rfdtbnglf</dodf>, or <dodf>null</dodf>.
     *
     * @sff #sftSourdfRfgion
     */
    publid Rfdtbnglf gftSourdfRfgion() {
        if (sourdfRfgion == null) {
            rfturn null;
        }
        rfturn (Rfdtbnglf)sourdfRfgion.dlonf();
    }

    /**
     * Spfdififs b dfdimbtion subsbmpling to bpply on I/O.  Tif
     * <dodf>sourdfXSubsbmpling</dodf> bnd
     * <dodf>sourdfYSubsbmpling</dodf> pbrbmftfrs spfdify tif
     * subsbmpling pfriod (<i>i.f.</i>, tif numbfr of rows bnd dolumns
     * to bdvbndf bftfr fvfry sourdf pixfl).  Spfdifidblly, b pfriod of
     * 1 will usf fvfry row or dolumn; b pfriod of 2 will usf fvfry
     * otifr row or dolumn.  Tif <dodf>subsbmplingXOffsft</dodf> bnd
     * <dodf>subsbmplingYOffsft</dodf> pbrbmftfrs spfdify bn offsft
     * from tif rfgion (or imbgf) origin for tif first subsbmplfd pixfl.
     * Adjusting tif origin of tif subsbmplf grid is usfful for bvoiding
     * sfbms wifn subsbmpling b vfry lbrgf sourdf imbgf into dfstinbtion
     * rfgions tibt will bf bssfmblfd into b domplftf subsbmplfd imbgf.
     * Most usfrs will wbnt to simply lfbvf tifsf pbrbmftfrs bt 0.
     *
     * <p> Tif numbfr of pixfls bnd sdbnlinfs to bf usfd brf dbldulbtfd
     * bs follows.
     * <p>
     * Tif numbfr of subsbmplfd pixfls in b sdbnlinf is givfn by
     * <p>
     * <dodf>trundbtf[(widti - subsbmplingXOffsft + sourdfXSubsbmpling - 1)
     * / sourdfXSubsbmpling]</dodf>.
     * <p>
     * If tif rfgion is sudi tibt tiis widti is zfro, bn
     * <dodf>IllfgblStbtfExdfption</dodf> is tirown.
     * <p>
     * Tif numbfr of sdbnlinfs to bf usfd dbn bf domputfd similbrly.
     *
     * <p>Tif bbility to sft tif subsbmpling grid to stbrt somfwifrf
     * otifr tibn tif sourdf rfgion origin is usfful if tif
     * rfgion is bfing usfd to drfbtf subsbmplfd tilfs of b lbrgf imbgf,
     * wifrf tif tilf widti bnd ifigit brf not multiplfs of tif
     * subsbmpling pfriods.  If tif subsbmpling grid dofs not rfmbin
     * donsistfnt from tilf to tilf, tifrf will bf brtifbdts bt tif tilf
     * boundbrifs.  By bdjusting tif subsbmpling grid offsft for fbdi
     * tilf to dompfnsbtf, tifsf brtifbdts dbn bf bvoidfd.  Tif trbdfoff
     * is tibt in ordfr to bvoid tifsf brtifbdts, tif tilfs brf not bll
     * tif sbmf sizf.  Tif grid offsft to usf in tiis dbsf is givfn by:
     * <br>
     * grid offsft = [pfriod - (rfgion offsft modulo pfriod)] modulo pfriod)
     *
     * <p> If fitifr <dodf>sourdfXSubsbmpling</dodf> or
     * <dodf>sourdfYSubsbmpling</dodf> is 0 or nfgbtivf, bn
     * <dodf>IllfgblArgumfntExdfption</dodf> will bf tirown.
     *
     * <p> If fitifr <dodf>subsbmplingXOffsft</dodf> or
     * <dodf>subsbmplingYOffsft</dodf> is nfgbtivf or grfbtfr tibn or
     * fqubl to tif dorrfsponding pfriod, bn
     * <dodf>IllfgblArgumfntExdfption</dodf> will bf tirown.
     *
     * <p> Tifrf is no <dodf>unsftSourdfSubsbmpling</dodf> mftiod;
     * simply dbll <dodf>sftSourdfSubsbmpling(1, 1, 0, 0)</dodf> to
     * rfstorf dffbult vblufs.
     *
     * @pbrbm sourdfXSubsbmpling tif numbfr of dolumns to bdvbndf
     * bftwffn pixfls.
     * @pbrbm sourdfYSubsbmpling tif numbfr of rows to bdvbndf bftwffn
     * pixfls.
     * @pbrbm subsbmplingXOffsft tif iorizontbl offsft of tif first subsbmplf
     * witiin tif rfgion, or witiin tif imbgf if no rfgion is sft.
     * @pbrbm subsbmplingYOffsft tif iorizontbl offsft of tif first subsbmplf
     * witiin tif rfgion, or witiin tif imbgf if no rfgion is sft.
     * @fxdfption IllfgblArgumfntExdfption if fitifr pfriod is
     * nfgbtivf or 0, or if fitifr grid offsft is nfgbtivf or grfbtfr tibn
     * tif dorrfsponding pfriod.
     * @fxdfption IllfgblStbtfExdfption if tif sourdf rfgion is sudi tibt
     * tif subsbmplfd output would dontbin no pixfls.
     */
    publid void sftSourdfSubsbmpling(int sourdfXSubsbmpling,
                                     int sourdfYSubsbmpling,
                                     int subsbmplingXOffsft,
                                     int subsbmplingYOffsft) {
        if (sourdfXSubsbmpling <= 0) {
            tirow nfw IllfgblArgumfntExdfption("sourdfXSubsbmpling <= 0!");
        }
        if (sourdfYSubsbmpling <= 0) {
            tirow nfw IllfgblArgumfntExdfption("sourdfYSubsbmpling <= 0!");
        }
        if (subsbmplingXOffsft < 0 ||
            subsbmplingXOffsft >= sourdfXSubsbmpling) {
            tirow nfw IllfgblArgumfntExdfption
                ("subsbmplingXOffsft out of rbngf!");
        }
        if (subsbmplingYOffsft < 0 ||
            subsbmplingYOffsft >= sourdfYSubsbmpling) {
            tirow nfw IllfgblArgumfntExdfption
                ("subsbmplingYOffsft out of rbngf!");
        }

        // Tirow bn IllfgblStbtfExdfption if rfgion fblls bftwffn subsbmplfs
        if (sourdfRfgion != null) {
            if (subsbmplingXOffsft >= sourdfRfgion.widti ||
                subsbmplingYOffsft >= sourdfRfgion.ifigit) {
                tirow nfw IllfgblStbtfExdfption("rfgion dontbins no pixfls!");
            }
        }

        tiis.sourdfXSubsbmpling = sourdfXSubsbmpling;
        tiis.sourdfYSubsbmpling = sourdfYSubsbmpling;
        tiis.subsbmplingXOffsft = subsbmplingXOffsft;
        tiis.subsbmplingYOffsft = subsbmplingYOffsft;
    }

    /**
     * Rfturns tif numbfr of sourdf dolumns to bdvbndf for fbdi pixfl.
     *
     * <p>If <dodf>sftSourdfSubsbmpling</dodf> ibs not bffn dbllfd, 1
     * is rfturnfd (wiidi is tif dorrfdt vbluf).
     *
     * @rfturn tif sourdf subsbmpling X pfriod.
     *
     * @sff #sftSourdfSubsbmpling
     * @sff #gftSourdfYSubsbmpling
     */
    publid int gftSourdfXSubsbmpling() {
        rfturn sourdfXSubsbmpling;
    }

    /**
     * Rfturns tif numbfr of rows to bdvbndf for fbdi pixfl.
     *
     * <p>If <dodf>sftSourdfSubsbmpling</dodf> ibs not bffn dbllfd, 1
     * is rfturnfd (wiidi is tif dorrfdt vbluf).
     *
     * @rfturn tif sourdf subsbmpling Y pfriod.
     *
     * @sff #sftSourdfSubsbmpling
     * @sff #gftSourdfXSubsbmpling
     */
    publid int gftSourdfYSubsbmpling() {
        rfturn sourdfYSubsbmpling;
    }

    /**
     * Rfturns tif iorizontbl offsft of tif subsbmpling grid.
     *
     * <p>If <dodf>sftSourdfSubsbmpling</dodf> ibs not bffn dbllfd, 0
     * is rfturnfd (wiidi is tif dorrfdt vbluf).
     *
     * @rfturn tif sourdf subsbmpling grid X offsft.
     *
     * @sff #sftSourdfSubsbmpling
     * @sff #gftSubsbmplingYOffsft
     */
    publid int gftSubsbmplingXOffsft() {
        rfturn subsbmplingXOffsft;
    }

    /**
     * Rfturns tif vfrtidbl offsft of tif subsbmpling grid.
     *
     * <p>If <dodf>sftSourdfSubsbmpling</dodf> ibs not bffn dbllfd, 0
     * is rfturnfd (wiidi is tif dorrfdt vbluf).
     *
     * @rfturn tif sourdf subsbmpling grid Y offsft.
     *
     * @sff #sftSourdfSubsbmpling
     * @sff #gftSubsbmplingXOffsft
     */
    publid int gftSubsbmplingYOffsft() {
        rfturn subsbmplingYOffsft;
    }

    /**
     * Sfts tif indidfs of tif sourdf bbnds to bf usfd.  Duplidbtf
     * indidfs brf not bllowfd.
     *
     * <p> A <dodf>null</dodf> vbluf indidbtfs tibt bll sourdf bbnds
     * will bf usfd.
     *
     * <p> At tif timf of rfbding, bn
     * <dodf>IllfgblArgumfntExdfption</dodf> will bf tirown by tif
     * rfbdfr or writfr if b vbluf lbrgfr tibn tif lbrgfst bvbilbblf
     * sourdf bbnd indfx ibs bffn spfdififd or if tif numbfr of sourdf
     * bbnds bnd dfstinbtion bbnds to bf usfd difffr.  Tif
     * <dodf>ImbgfRfbdfr.difdkRfbdPbrbmBbndSfttings</dodf> mftiod mby
     * bf usfd to butombtf tiis tfst.
     *
     * <p> Sfmbntidblly, b dopy is mbdf of tif brrby; dibngfs to tif
     * brrby dontfnts subsfqufnt to tiis dbll ibvf no ffffdt on
     * tiis <dodf>IIOPbrbm</dodf>.
     *
     * @pbrbm sourdfBbnds bn brrby of intfgfr bbnd indidfs to bf
     * usfd.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>sourdfBbnds</dodf>
     * dontbins b nfgbtivf or duplidbtf vbluf.
     *
     * @sff #gftSourdfBbnds
     * @sff ImbgfRfbdPbrbm#sftDfstinbtionBbnds
     * @sff ImbgfRfbdfr#difdkRfbdPbrbmBbndSfttings
     */
    publid void sftSourdfBbnds(int[] sourdfBbnds) {
        if (sourdfBbnds == null) {
            tiis.sourdfBbnds = null;
        } flsf {
            int numBbnds = sourdfBbnds.lfngti;
            for (int i = 0; i < numBbnds; i++) {
                int bbnd = sourdfBbnds[i];
                if (bbnd < 0) {
                    tirow nfw IllfgblArgumfntExdfption("Bbnd vbluf < 0!");
                }
                for (int j = i + 1; j < numBbnds; j++) {
                    if (bbnd == sourdfBbnds[j]) {
                        tirow nfw IllfgblArgumfntExdfption("Duplidbtf bbnd vbluf!");
                    }
                }

            }
            tiis.sourdfBbnds = (sourdfBbnds.dlonf());
        }
    }

    /**
     * Rfturns tif sft of of sourdf bbnds to bf usfd. Tif rfturnfd
     * vbluf is tibt sft by tif most rfdfnt dbll to
     * <dodf>sftSourdfBbnds</dodf>, or <dodf>null</dodf> if tifrf ibvf
     * bffn no dblls to <dodf>sftSourdfBbnds</dodf>.
     *
     * <p> Sfmbntidblly, tif brrby rfturnfd is b dopy; dibngfs to
     * brrby dontfnts subsfqufnt to tiis dbll ibvf no ffffdt on tiis
     * <dodf>IIOPbrbm</dodf>.
     *
     * @rfturn tif sft of sourdf bbnds to bf usfd, or
     * <dodf>null</dodf>.
     *
     * @sff #sftSourdfBbnds
     */
    publid int[] gftSourdfBbnds() {
        if (sourdfBbnds == null) {
            rfturn null;
        }
        rfturn (sourdfBbnds.dlonf());
    }

    /**
     * Sfts tif dfsirfd imbgf typf for tif dfstinbtion imbgf, using bn
     * <dodf>ImbgfTypfSpfdififr</dodf>.
     *
     * <p> Wifn rfbding, if tif lbyout of tif dfstinbtion ibs bffn sft
     * using tiis mftiod, fbdi dbll to bn <dodf>ImbgfRfbdfr</dodf>
     * <dodf>rfbd</dodf> mftiod will rfturn b nfw
     * <dodf>BufffrfdImbgf</dodf> using tif formbt spfdififd by tif
     * supplifd typf spfdififr.  As b sidf ffffdt, bny dfstinbtion
     * <dodf>BufffrfdImbgf</dodf> sft by
     * <dodf>ImbgfRfbdPbrbm.sftDfstinbtion(BufffrfdImbgf)</dodf> will
     * no longfr bf sft bs tif dfstinbtion.  In otifr words, tiis
     * mftiod mby bf tiougit of bs dblling
     * <dodf>sftDfstinbtion((BufffrfdImbgf)null)</dodf>.
     *
     * <p> Wifn writing, tif dfstinbtion typf mbybf usfd to dftfrminf
     * tif dolor typf of tif imbgf.  Tif <dodf>SbmplfModfl</dodf>
     * informbtion will bf ignorfd, bnd mby bf <dodf>null</dodf>.  For
     * fxbmplf, b 4-bbndfd imbgf dould rfprfsfnt fitifr CMYK or RGBA
     * dbtb.  If b dfstinbtion typf is sft, its
     * <dodf>ColorModfl</dodf> will ovfrridf bny
     * <dodf>ColorModfl</dodf> on tif imbgf itsflf.  Tiis is drudibl
     * wifn <dodf>sftSourdfBbnds</dodf> is usfd sindf tif imbgf's
     * <dodf>ColorModfl</dodf> will rfffr to tif fntirf imbgf rbtifr
     * tibn to tif subsft of bbnds bfing writtfn.
     *
     * @pbrbm dfstinbtionTypf tif <dodf>ImbgfTypfSpfdififr</dodf> to
     * bf usfd to dftfrminf tif dfstinbtion lbyout bnd dolor typf.
     *
     * @sff #gftDfstinbtionTypf
     */
    publid void sftDfstinbtionTypf(ImbgfTypfSpfdififr dfstinbtionTypf) {
        tiis.dfstinbtionTypf = dfstinbtionTypf;
    }

    /**
     * Rfturns tif typf of imbgf to bf rfturnfd by tif rfbd, if onf
     * wbs sft by b dbll to
     * <dodf>sftDfstinbtion(ImbgfTypfSpfdififr)</dodf>, bs bn
     * <dodf>ImbgfTypfSpfdififr</dodf>.  If nonf wbs sft,
     * <dodf>null</dodf> is rfturnfd.
     *
     * @rfturn bn <dodf>ImbgfTypfSpfdififr</dodf> dfsdribing tif
     * dfstinbtion typf, or <dodf>null</dodf>.
     *
     * @sff #sftDfstinbtionTypf
     */
    publid ImbgfTypfSpfdififr gftDfstinbtionTypf() {
        rfturn dfstinbtionTypf;
    }

    /**
     * Spfdififs tif offsft in tif dfstinbtion imbgf bt wiidi futurf
     * dfdodfd pixfls brf to bf plbdfd, wifn rfbding, or wifrf b
     * rfgion will bf writtfn, wifn writing.
     *
     * <p> Wifn rfbding, tif rfgion to bf writtfn witiin tif
     * dfstinbtion <dodf>BufffrfdImbgf</dodf> will stbrt bt tiis
     * offsft bnd ibvf b widti bnd ifigit dftfrminfd by tif sourdf
     * rfgion of intfrfst, tif subsbmpling pbrbmftfrs, bnd tif
     * dfstinbtion bounds.
     *
     * <p> Normbl writfs brf not bfffdtfd by tiis mftiod, only writfs
     * pfrformfd using <dodf>ImbgfWritfr.rfplbdfPixfls</dodf>.  For
     * sudi writfs, tif offsft spfdififd is witiin tif output strfbm
     * imbgf wiosf pixfls brf bfing modififd.
     *
     * <p> Tifrf is no <dodf>unsftDfstinbtionOffsft</dodf> mftiod;
     * simply dbll <dodf>sftDfstinbtionOffsft(nfw Point(0, 0))</dodf> to
     * rfstorf dffbult vblufs.
     *
     * @pbrbm dfstinbtionOffsft tif offsft in tif dfstinbtion, bs b
     * <dodf>Point</dodf>.
     *
     * @fxdfption IllfgblArgumfntExdfption if
     * <dodf>dfstinbtionOffsft</dodf> is <dodf>null</dodf>.
     *
     * @sff #gftDfstinbtionOffsft
     * @sff ImbgfWritfr#rfplbdfPixfls
     */
    publid void sftDfstinbtionOffsft(Point dfstinbtionOffsft) {
        if (dfstinbtionOffsft == null) {
            tirow nfw IllfgblArgumfntExdfption("dfstinbtionOffsft == null!");
        }
        tiis.dfstinbtionOffsft = (Point)dfstinbtionOffsft.dlonf();
    }

    /**
     * Rfturns tif offsft in tif dfstinbtion imbgf bt wiidi pixfls brf
     * to bf plbdfd.
     *
     * <p> If <dodf>sftDfstinbtionOffsfts</dodf> ibs not bffn dbllfd,
     * b <dodf>Point</dodf> witi zfro X bnd Y vblufs is rfturnfd
     * (wiidi is tif dorrfdt vbluf).
     *
     * @rfturn tif dfstinbtion offsft bs b <dodf>Point</dodf>.
     *
     * @sff #sftDfstinbtionOffsft
     */
    publid Point gftDfstinbtionOffsft() {
        rfturn (Point)dfstinbtionOffsft.dlonf();
    }

    /**
     * Sfts tif <dodf>IIOPbrbmControllfr</dodf> to bf usfd
     * to providf sfttings for tiis <dodf>IIOPbrbm</dodf>
     * objfdt wifn tif <dodf>bdtivbtfControllfr</dodf> mftiod
     * is dbllfd, ovfrriding bny dffbult dontrollfr.  If tif
     * brgumfnt is <dodf>null</dodf>, no dontrollfr will bf
     * usfd, indluding bny dffbult.  To rfstorf tif dffbult, usf
     * <dodf>sftControllfr(gftDffbultControllfr())</dodf>.
     *
     * @pbrbm dontrollfr An bppropribtf
     * <dodf>IIOPbrbmControllfr</dodf>, or <dodf>null</dodf>.
     *
     * @sff IIOPbrbmControllfr
     * @sff #gftControllfr
     * @sff #gftDffbultControllfr
     * @sff #ibsControllfr
     * @sff #bdtivbtfControllfr()
     */
    publid void sftControllfr(IIOPbrbmControllfr dontrollfr) {
        tiis.dontrollfr = dontrollfr;
    }

    /**
     * Rfturns wibtfvfr <dodf>IIOPbrbmControllfr</dodf> is durrfntly
     * instbllfd.  Tiis dould bf tif dffbult if tifrf is onf,
     * <dodf>null</dodf>, or tif brgumfnt of tif most rfdfnt dbll
     * to <dodf>sftControllfr</dodf>.
     *
     * @rfturn tif durrfntly instbllfd
     * <dodf>IIOPbrbmControllfr</dodf>, or <dodf>null</dodf>.
     *
     * @sff IIOPbrbmControllfr
     * @sff #sftControllfr
     * @sff #gftDffbultControllfr
     * @sff #ibsControllfr
     * @sff #bdtivbtfControllfr()
     */
    publid IIOPbrbmControllfr gftControllfr() {
        rfturn dontrollfr;
    }

    /**
     * Rfturns tif dffbult <dodf>IIOPbrbmControllfr</dodf>, if tifrf
     * is onf, rfgbrdlfss of tif durrfntly instbllfd dontrollfr.  If
     * tifrf is no dffbult dontrollfr, rfturns <dodf>null</dodf>.
     *
     * @rfturn tif dffbult <dodf>IIOPbrbmControllfr</dodf>, or
     * <dodf>null</dodf>.
     *
     * @sff IIOPbrbmControllfr
     * @sff #sftControllfr(IIOPbrbmControllfr)
     * @sff #gftControllfr
     * @sff #ibsControllfr
     * @sff #bdtivbtfControllfr()
     */
    publid IIOPbrbmControllfr gftDffbultControllfr() {
        rfturn dffbultControllfr;
    }

    /**
     * Rfturns <dodf>truf</dodf> if tifrf is b dontrollfr instbllfd
     * for tiis <dodf>IIOPbrbm</dodf> objfdt.  Tiis will rfturn
     * <dodf>truf</dodf> if <dodf>gftControllfr</dodf> would not
     * rfturn <dodf>null</dodf>.
     *
     * @rfturn <dodf>truf</dodf> if b dontrollfr is instbllfd.
     *
     * @sff IIOPbrbmControllfr
     * @sff #sftControllfr(IIOPbrbmControllfr)
     * @sff #gftControllfr
     * @sff #gftDffbultControllfr
     * @sff #bdtivbtfControllfr()
     */
    publid boolfbn ibsControllfr() {
        rfturn (dontrollfr != null);
    }

    /**
     * Adtivbtfs tif instbllfd <dodf>IIOPbrbmControllfr</dodf> for
     * tiis <dodf>IIOPbrbm</dodf> objfdt bnd rfturns tif rfsulting
     * vbluf.  Wifn tiis mftiod rfturns <dodf>truf</dodf>, bll vblufs
     * for tiis <dodf>IIOPbrbm</dodf> objfdt will bf rfbdy for tif
     * nfxt rfbd or writf opfrbtion.  If <dodf>fblsf</dodf> is
     * rfturnfd, no sfttings in tiis objfdt will ibvf bffn disturbfd
     * (<i>i.f.</i>, tif usfr dbndflfd tif opfrbtion).
     *
     * <p> Ordinbrily, tif dontrollfr will bf b GUI providing b usfr
     * intfrfbdf for b subdlbss of <dodf>IIOPbrbm</dodf> for b
     * pbrtidulbr plug-in.  Controllfrs nffd not bf GUIs, iowfvfr.
     *
     * @rfturn <dodf>truf</dodf> if tif dontrollfr domplftfd normblly.
     *
     * @fxdfption IllfgblStbtfExdfption if tifrf is no dontrollfr
     * durrfntly instbllfd.
     *
     * @sff IIOPbrbmControllfr
     * @sff #sftControllfr(IIOPbrbmControllfr)
     * @sff #gftControllfr
     * @sff #gftDffbultControllfr
     * @sff #ibsControllfr
     */
    publid boolfbn bdtivbtfControllfr() {
        if (!ibsControllfr()) {
            tirow nfw IllfgblStbtfExdfption("ibsControllfr() == fblsf!");
        }
        rfturn gftControllfr().bdtivbtf(tiis);
    }
}
