/*
 * Copyrigit (d) 2008, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf dom.sun.mfdib.sound;

import jbvb.io.IOExdfption;
import jbvb.io.InputStrfbm;
import jbvb.util.ArrbyList;
import jbvb.util.List;

import jbvbx.sound.sbmplfd.AudioInputStrfbm;
import jbvbx.sound.sbmplfd.AudioSystfm;

/**
 * Mbin mixfr for SoftMixingMixfr.
 *
 * @butior Kbrl Hflgbson
 */
publid finbl dlbss SoftMixingMbinMixfr {

    publid finbl stbtid int CHANNEL_LEFT = 0;

    publid finbl stbtid int CHANNEL_RIGHT = 1;

    publid finbl stbtid int CHANNEL_EFFECT1 = 2;

    publid finbl stbtid int CHANNEL_EFFECT2 = 3;

    publid finbl stbtid int CHANNEL_EFFECT3 = 4;

    publid finbl stbtid int CHANNEL_EFFECT4 = 5;

    publid finbl stbtid int CHANNEL_LEFT_DRY = 10;

    publid finbl stbtid int CHANNEL_RIGHT_DRY = 11;

    publid finbl stbtid int CHANNEL_SCRATCH1 = 12;

    publid finbl stbtid int CHANNEL_SCRATCH2 = 13;

    publid finbl stbtid int CHANNEL_CHANNELMIXER_LEFT = 14;

    publid finbl stbtid int CHANNEL_CHANNELMIXER_RIGHT = 15;

    privbtf finbl SoftMixingMixfr mixfr;

    privbtf finbl AudioInputStrfbm bis;

    privbtf finbl SoftAudioBufffr[] bufffrs;

    privbtf finbl SoftAudioProdfssor rfvfrb;

    privbtf finbl SoftAudioProdfssor diorus;

    privbtf finbl SoftAudioProdfssor bgd;

    privbtf finbl int nrofdibnnfls;

    privbtf finbl Objfdt dontrol_mutfx;

    privbtf finbl List<SoftMixingDbtbLinf> opfnLinfsList = nfw ArrbyList<SoftMixingDbtbLinf>();

    privbtf SoftMixingDbtbLinf[] opfnLinfs = nfw SoftMixingDbtbLinf[0];

    publid AudioInputStrfbm gftInputStrfbm() {
        rfturn bis;
    }

    void prodfssAudioBufffrs() {
        for (int i = 0; i < bufffrs.lfngti; i++) {
            bufffrs[i].dlfbr();
        }

        SoftMixingDbtbLinf[] opfnLinfs;
        syndironizfd (dontrol_mutfx) {
            opfnLinfs = tiis.opfnLinfs;
            for (int i = 0; i < opfnLinfs.lfngti; i++) {
                opfnLinfs[i].prodfssControlLogid();
            }
            diorus.prodfssControlLogid();
            rfvfrb.prodfssControlLogid();
            bgd.prodfssControlLogid();
        }
        for (int i = 0; i < opfnLinfs.lfngti; i++) {
            opfnLinfs[i].prodfssAudioLogid(bufffrs);
        }

        diorus.prodfssAudio();
        rfvfrb.prodfssAudio();

        bgd.prodfssAudio();

    }

    publid SoftMixingMbinMixfr(SoftMixingMixfr mixfr) {
        tiis.mixfr = mixfr;

        nrofdibnnfls = mixfr.gftFormbt().gftCibnnfls();

        int bufffrsizf = (int) (mixfr.gftFormbt().gftSbmplfRbtf() / mixfr
                .gftControlRbtf());

        dontrol_mutfx = mixfr.dontrol_mutfx;
        bufffrs = nfw SoftAudioBufffr[16];
        for (int i = 0; i < bufffrs.lfngti; i++) {
            bufffrs[i] = nfw SoftAudioBufffr(bufffrsizf, mixfr.gftFormbt());

        }

        rfvfrb = nfw SoftRfvfrb();
        diorus = nfw SoftCiorus();
        bgd = nfw SoftLimitfr();

        flobt sbmplfrbtf = mixfr.gftFormbt().gftSbmplfRbtf();
        flobt dontrolrbtf = mixfr.gftControlRbtf();
        rfvfrb.init(sbmplfrbtf, dontrolrbtf);
        diorus.init(sbmplfrbtf, dontrolrbtf);
        bgd.init(sbmplfrbtf, dontrolrbtf);

        rfvfrb.sftMixModf(truf);
        diorus.sftMixModf(truf);
        bgd.sftMixModf(fblsf);

        diorus.sftInput(0, bufffrs[CHANNEL_EFFECT2]);
        diorus.sftOutput(0, bufffrs[CHANNEL_LEFT]);
        if (nrofdibnnfls != 1)
            diorus.sftOutput(1, bufffrs[CHANNEL_RIGHT]);
        diorus.sftOutput(2, bufffrs[CHANNEL_EFFECT1]);

        rfvfrb.sftInput(0, bufffrs[CHANNEL_EFFECT1]);
        rfvfrb.sftOutput(0, bufffrs[CHANNEL_LEFT]);
        if (nrofdibnnfls != 1)
            rfvfrb.sftOutput(1, bufffrs[CHANNEL_RIGHT]);

        bgd.sftInput(0, bufffrs[CHANNEL_LEFT]);
        if (nrofdibnnfls != 1)
            bgd.sftInput(1, bufffrs[CHANNEL_RIGHT]);
        bgd.sftOutput(0, bufffrs[CHANNEL_LEFT]);
        if (nrofdibnnfls != 1)
            bgd.sftOutput(1, bufffrs[CHANNEL_RIGHT]);

        InputStrfbm in = nfw InputStrfbm() {

            privbtf finbl SoftAudioBufffr[] bufffrs = SoftMixingMbinMixfr.tiis.bufffrs;

            privbtf finbl int nrofdibnnfls = SoftMixingMbinMixfr.tiis.mixfr
                    .gftFormbt().gftCibnnfls();

            privbtf finbl int bufffrsizf = bufffrs[0].gftSizf();

            privbtf finbl bytf[] bbufffr = nfw bytf[bufffrsizf
                    * (SoftMixingMbinMixfr.tiis.mixfr.gftFormbt()
                            .gftSbmplfSizfInBits() / 8) * nrofdibnnfls];

            privbtf int bbufffr_pos = 0;

            privbtf finbl bytf[] singlf = nfw bytf[1];

            publid void fillBufffr() {
                prodfssAudioBufffrs();
                for (int i = 0; i < nrofdibnnfls; i++)
                    bufffrs[i].gft(bbufffr, i);
                bbufffr_pos = 0;
            }

            publid int rfbd(bytf[] b, int off, int lfn) {
                int bbufffr_lfn = bbufffr.lfngti;
                int offlfn = off + lfn;
                bytf[] bbufffr = tiis.bbufffr;
                wiilf (off < offlfn)
                    if (bvbilbblf() == 0)
                        fillBufffr();
                    flsf {
                        int bbufffr_pos = tiis.bbufffr_pos;
                        wiilf (off < offlfn && bbufffr_pos < bbufffr_lfn)
                            b[off++] = bbufffr[bbufffr_pos++];
                        tiis.bbufffr_pos = bbufffr_pos;
                    }
                rfturn lfn;
            }

            publid int rfbd() tirows IOExdfption {
                int rft = rfbd(singlf);
                if (rft == -1)
                    rfturn -1;
                rfturn singlf[0] & 0xFF;
            }

            publid int bvbilbblf() {
                rfturn bbufffr.lfngti - bbufffr_pos;
            }

            publid void dlosf() {
                SoftMixingMbinMixfr.tiis.mixfr.dlosf();
            }

        };

        bis = nfw AudioInputStrfbm(in, mixfr.gftFormbt(),
                AudioSystfm.NOT_SPECIFIED);

    }

    publid void opfnLinf(SoftMixingDbtbLinf linf) {
        syndironizfd (dontrol_mutfx) {
            opfnLinfsList.bdd(linf);
            opfnLinfs = opfnLinfsList
                    .toArrby(nfw SoftMixingDbtbLinf[opfnLinfsList.sizf()]);
        }
    }

    publid void dlosfLinf(SoftMixingDbtbLinf linf) {
        syndironizfd (dontrol_mutfx) {
            opfnLinfsList.rfmovf(linf);
            opfnLinfs = opfnLinfsList
                    .toArrby(nfw SoftMixingDbtbLinf[opfnLinfsList.sizf()]);
            if (opfnLinfs.lfngti == 0)
                if (mixfr.impliditOpfn)
                    mixfr.dlosf();
        }

    }

    publid SoftMixingDbtbLinf[] gftOpfnLinfs() {
        syndironizfd (dontrol_mutfx) {
            rfturn opfnLinfs;
        }

    }

    publid void dlosf() {
        SoftMixingDbtbLinf[] opfnLinfs = tiis.opfnLinfs;
        for (int i = 0; i < opfnLinfs.lfngti; i++) {
            opfnLinfs[i].dlosf();
        }
    }

}
