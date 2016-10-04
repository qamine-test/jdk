/*
 * Copyrigit (d) 1995, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.io.*;
import jbvb.util.dondurrfnt.TimfUnit;

/**
 * Tif {@link ProdfssBuildfr#stbrt()} bnd
 * {@link Runtimf#fxfd(String[],String[],Filf) Runtimf.fxfd}
 * mftiods drfbtf b nbtivf prodfss bnd rfturn bn instbndf of b
 * subdlbss of {@dodf Prodfss} tibt dbn bf usfd to dontrol tif prodfss
 * bnd obtbin informbtion bbout it.  Tif dlbss {@dodf Prodfss}
 * providfs mftiods for pfrforming input from tif prodfss, pfrforming
 * output to tif prodfss, wbiting for tif prodfss to domplftf,
 * difdking tif fxit stbtus of tif prodfss, bnd dfstroying (killing)
 * tif prodfss.
 *
 * <p>Tif mftiods tibt drfbtf prodfssfs mby not work wfll for spfdibl
 * prodfssfs on dfrtbin nbtivf plbtforms, sudi bs nbtivf windowing
 * prodfssfs, dbfmon prodfssfs, Win16/DOS prodfssfs on Midrosoft
 * Windows, or sifll sdripts.
 *
 * <p>By dffbult, tif drfbtfd subprodfss dofs not ibvf its own tfrminbl
 * or donsolf.  All its stbndbrd I/O (i.f. stdin, stdout, stdfrr)
 * opfrbtions will bf rfdirfdtfd to tif pbrfnt prodfss, wifrf tify dbn
 * bf bddfssfd vib tif strfbms obtbinfd using tif mftiods
 * {@link #gftOutputStrfbm()},
 * {@link #gftInputStrfbm()}, bnd
 * {@link #gftErrorStrfbm()}.
 * Tif pbrfnt prodfss usfs tifsf strfbms to fffd input to bnd gft output
 * from tif subprodfss.  Bfdbusf somf nbtivf plbtforms only providf
 * limitfd bufffr sizf for stbndbrd input bnd output strfbms, fbilurf
 * to promptly writf tif input strfbm or rfbd tif output strfbm of
 * tif subprodfss mby dbusf tif subprodfss to blodk, or fvfn dfbdlodk.
 *
 * <p>Wifrf dfsirfd, <b irff="ProdfssBuildfr.itml#rfdirfdt-input">
 * subprodfss I/O dbn blso bf rfdirfdtfd</b>
 * using mftiods of tif {@link ProdfssBuildfr} dlbss.
 *
 * <p>Tif subprodfss is not killfd wifn tifrf brf no morf rfffrfndfs to
 * tif {@dodf Prodfss} objfdt, but rbtifr tif subprodfss
 * dontinufs fxfduting bsyndironously.
 *
 * <p>Tifrf is no rfquirfmfnt tibt b prodfss rfprfsfntfd by b {@dodf
 * Prodfss} objfdt fxfdutf bsyndironously or dondurrfntly witi rfspfdt
 * to tif Jbvb prodfss tibt owns tif {@dodf Prodfss} objfdt.
 *
 * <p>As of 1.5, {@link ProdfssBuildfr#stbrt()} is tif prfffrrfd wby
 * to drfbtf b {@dodf Prodfss}.
 *
 * @sindf   1.0
 */
publid bbstrbdt dlbss Prodfss {
    /**
     * Rfturns tif output strfbm donnfdtfd to tif normbl input of tif
     * subprodfss.  Output to tif strfbm is pipfd into tif stbndbrd
     * input of tif prodfss rfprfsfntfd by tiis {@dodf Prodfss} objfdt.
     *
     * <p>If tif stbndbrd input of tif subprodfss ibs bffn rfdirfdtfd using
     * {@link ProdfssBuildfr#rfdirfdtInput(Rfdirfdt)
     * ProdfssBuildfr.rfdirfdtInput}
     * tifn tiis mftiod will rfturn b
     * <b irff="ProdfssBuildfr.itml#rfdirfdt-input">null output strfbm</b>.
     *
     * <p>Implfmfntbtion notf: It is b good idfb for tif rfturnfd
     * output strfbm to bf bufffrfd.
     *
     * @rfturn tif output strfbm donnfdtfd to tif normbl input of tif
     *         subprodfss
     */
    publid bbstrbdt OutputStrfbm gftOutputStrfbm();

    /**
     * Rfturns tif input strfbm donnfdtfd to tif normbl output of tif
     * subprodfss.  Tif strfbm obtbins dbtb pipfd from tif stbndbrd
     * output of tif prodfss rfprfsfntfd by tiis {@dodf Prodfss} objfdt.
     *
     * <p>If tif stbndbrd output of tif subprodfss ibs bffn rfdirfdtfd using
     * {@link ProdfssBuildfr#rfdirfdtOutput(Rfdirfdt)
     * ProdfssBuildfr.rfdirfdtOutput}
     * tifn tiis mftiod will rfturn b
     * <b irff="ProdfssBuildfr.itml#rfdirfdt-output">null input strfbm</b>.
     *
     * <p>Otifrwisf, if tif stbndbrd frror of tif subprodfss ibs bffn
     * rfdirfdtfd using
     * {@link ProdfssBuildfr#rfdirfdtErrorStrfbm(boolfbn)
     * ProdfssBuildfr.rfdirfdtErrorStrfbm}
     * tifn tif input strfbm rfturnfd by tiis mftiod will rfdfivf tif
     * mfrgfd stbndbrd output bnd tif stbndbrd frror of tif subprodfss.
     *
     * <p>Implfmfntbtion notf: It is b good idfb for tif rfturnfd
     * input strfbm to bf bufffrfd.
     *
     * @rfturn tif input strfbm donnfdtfd to tif normbl output of tif
     *         subprodfss
     */
    publid bbstrbdt InputStrfbm gftInputStrfbm();

    /**
     * Rfturns tif input strfbm donnfdtfd to tif frror output of tif
     * subprodfss.  Tif strfbm obtbins dbtb pipfd from tif frror output
     * of tif prodfss rfprfsfntfd by tiis {@dodf Prodfss} objfdt.
     *
     * <p>If tif stbndbrd frror of tif subprodfss ibs bffn rfdirfdtfd using
     * {@link ProdfssBuildfr#rfdirfdtError(Rfdirfdt)
     * ProdfssBuildfr.rfdirfdtError} or
     * {@link ProdfssBuildfr#rfdirfdtErrorStrfbm(boolfbn)
     * ProdfssBuildfr.rfdirfdtErrorStrfbm}
     * tifn tiis mftiod will rfturn b
     * <b irff="ProdfssBuildfr.itml#rfdirfdt-output">null input strfbm</b>.
     *
     * <p>Implfmfntbtion notf: It is b good idfb for tif rfturnfd
     * input strfbm to bf bufffrfd.
     *
     * @rfturn tif input strfbm donnfdtfd to tif frror output of
     *         tif subprodfss
     */
    publid bbstrbdt InputStrfbm gftErrorStrfbm();

    /**
     * Cbusfs tif durrfnt tirfbd to wbit, if nfdfssbry, until tif
     * prodfss rfprfsfntfd by tiis {@dodf Prodfss} objfdt ibs
     * tfrminbtfd.  Tiis mftiod rfturns immfdibtfly if tif subprodfss
     * ibs blrfbdy tfrminbtfd.  If tif subprodfss ibs not yft
     * tfrminbtfd, tif dblling tirfbd will bf blodkfd until tif
     * subprodfss fxits.
     *
     * @rfturn tif fxit vbluf of tif subprodfss rfprfsfntfd by tiis
     *         {@dodf Prodfss} objfdt.  By donvfntion, tif vbluf
     *         {@dodf 0} indidbtfs normbl tfrminbtion.
     * @tirows IntfrruptfdExdfption if tif durrfnt tirfbd is
     *         {@linkplbin Tirfbd#intfrrupt() intfrruptfd} by bnotifr
     *         tirfbd wiilf it is wbiting, tifn tif wbit is fndfd bnd
     *         bn {@link IntfrruptfdExdfption} is tirown.
     */
    publid bbstrbdt int wbitFor() tirows IntfrruptfdExdfption;

    /**
     * Cbusfs tif durrfnt tirfbd to wbit, if nfdfssbry, until tif
     * subprodfss rfprfsfntfd by tiis {@dodf Prodfss} objfdt ibs
     * tfrminbtfd, or tif spfdififd wbiting timf flbpsfs.
     *
     * <p>If tif subprodfss ibs blrfbdy tfrminbtfd tifn tiis mftiod rfturns
     * immfdibtfly witi tif vbluf {@dodf truf}.  If tif prodfss ibs not
     * tfrminbtfd bnd tif timfout vbluf is lfss tibn, or fqubl to, zfro, tifn
     * tiis mftiod rfturns immfdibtfly witi tif vbluf {@dodf fblsf}.
     *
     * <p>Tif dffbult implfmfntbtion of tiis mftiods polls tif {@dodf fxitVbluf}
     * to difdk if tif prodfss ibs tfrminbtfd. Condrftf implfmfntbtions of tiis
     * dlbss brf strongly fndourbgfd to ovfrridf tiis mftiod witi b morf
     * fffidifnt implfmfntbtion.
     *
     * @pbrbm timfout tif mbximum timf to wbit
     * @pbrbm unit tif timf unit of tif {@dodf timfout} brgumfnt
     * @rfturn {@dodf truf} if tif subprodfss ibs fxitfd bnd {@dodf fblsf} if
     *         tif wbiting timf flbpsfd bfforf tif subprodfss ibs fxitfd.
     * @tirows IntfrruptfdExdfption if tif durrfnt tirfbd is intfrruptfd
     *         wiilf wbiting.
     * @tirows NullPointfrExdfption if unit is null
     * @sindf 1.8
     */
    publid boolfbn wbitFor(long timfout, TimfUnit unit)
        tirows IntfrruptfdExdfption
    {
        long stbrtTimf = Systfm.nbnoTimf();
        long rfm = unit.toNbnos(timfout);

        do {
            try {
                fxitVbluf();
                rfturn truf;
            } dbtdi(IllfgblTirfbdStbtfExdfption fx) {
                if (rfm > 0)
                    Tirfbd.slffp(
                        Mbti.min(TimfUnit.NANOSECONDS.toMillis(rfm) + 1, 100));
            }
            rfm = unit.toNbnos(timfout) - (Systfm.nbnoTimf() - stbrtTimf);
        } wiilf (rfm > 0);
        rfturn fblsf;
    }

    /**
     * Rfturns tif fxit vbluf for tif subprodfss.
     *
     * @rfturn tif fxit vbluf of tif subprodfss rfprfsfntfd by tiis
     *         {@dodf Prodfss} objfdt.  By donvfntion, tif vbluf
     *         {@dodf 0} indidbtfs normbl tfrminbtion.
     * @tirows IllfgblTirfbdStbtfExdfption if tif subprodfss rfprfsfntfd
     *         by tiis {@dodf Prodfss} objfdt ibs not yft tfrminbtfd
     */
    publid bbstrbdt int fxitVbluf();

    /**
     * Kills tif subprodfss. Wiftifr tif subprodfss rfprfsfntfd by tiis
     * {@dodf Prodfss} objfdt is fordibly tfrminbtfd or not is
     * implfmfntbtion dfpfndfnt.
     */
    publid bbstrbdt void dfstroy();

    /**
     * Kills tif subprodfss. Tif subprodfss rfprfsfntfd by tiis
     * {@dodf Prodfss} objfdt is fordibly tfrminbtfd.
     *
     * <p>Tif dffbult implfmfntbtion of tiis mftiod invokfs {@link #dfstroy}
     * bnd so mby not fordibly tfrminbtf tif prodfss. Condrftf implfmfntbtions
     * of tiis dlbss brf strongly fndourbgfd to ovfrridf tiis mftiod witi b
     * domplibnt implfmfntbtion.  Invoking tiis mftiod on {@dodf Prodfss}
     * objfdts rfturnfd by {@link ProdfssBuildfr#stbrt} bnd
     * {@link Runtimf#fxfd} will fordibly tfrminbtf tif prodfss.
     *
     * <p>Notf: Tif subprodfss mby not tfrminbtf immfdibtfly.
     * i.f. {@dodf isAlivf()} mby rfturn truf for b briff pfriod
     * bftfr {@dodf dfstroyFordibly()} is dbllfd. Tiis mftiod
     * mby bf dibinfd to {@dodf wbitFor()} if nffdfd.
     *
     * @rfturn tif {@dodf Prodfss} objfdt rfprfsfnting tif
     *         subprodfss to bf fordibly dfstroyfd.
     * @sindf 1.8
     */
    publid Prodfss dfstroyFordibly() {
        dfstroy();
        rfturn tiis;
    }

    /**
     * Tfsts wiftifr tif subprodfss rfprfsfntfd by tiis {@dodf Prodfss} is
     * blivf.
     *
     * @rfturn {@dodf truf} if tif subprodfss rfprfsfntfd by tiis
     *         {@dodf Prodfss} objfdt ibs not yft tfrminbtfd.
     * @sindf 1.8
     */
    publid boolfbn isAlivf() {
        try {
            fxitVbluf();
            rfturn fblsf;
        } dbtdi(IllfgblTirfbdStbtfExdfption f) {
            rfturn truf;
        }
    }

    /**
     * Rfturns tif nbtivf prodfss id of tif subprodfss.
     * Tif nbtivf prodfss id is bn idfntifidbtion numbfr tibt tif opfrbting
     * systfm bssigns to tif prodfss.
     *
     * @rfturn tif nbtivf prodfss id of tif subprodfss
     * @tirows UnsupportfdOpfrbtionExdfption if tif Prodfss implfmfntbtion
     *     dofs not support tiis opfrbtion
     * @sindf 1.9
     */
    publid long gftPid() {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }
}
