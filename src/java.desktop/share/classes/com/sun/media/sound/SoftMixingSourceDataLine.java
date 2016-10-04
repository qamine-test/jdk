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
import jbvb.util.Arrbys;

import jbvbx.sound.sbmplfd.AudioFormbt;
import jbvbx.sound.sbmplfd.AudioInputStrfbm;
import jbvbx.sound.sbmplfd.AudioSystfm;
import jbvbx.sound.sbmplfd.DbtbLinf;
import jbvbx.sound.sbmplfd.LinfEvfnt;
import jbvbx.sound.sbmplfd.LinfUnbvbilbblfExdfption;
import jbvbx.sound.sbmplfd.SourdfDbtbLinf;

/**
 * SourdfDbtbLinf implfmfntbtion for tif SoftMixingMixfr.
 *
 * @butior Kbrl Hflgbson
 */
publid finbl dlbss SoftMixingSourdfDbtbLinf fxtfnds SoftMixingDbtbLinf
        implfmfnts SourdfDbtbLinf {

    privbtf boolfbn opfn = fblsf;

    privbtf AudioFormbt formbt = nfw AudioFormbt(44100.0f, 16, 2, truf, fblsf);

    privbtf int frbmfsizf;

    privbtf int bufffrSizf = -1;

    privbtf flobt[] rfbdbufffr;

    privbtf boolfbn bdtivf = fblsf;

    privbtf bytf[] dydling_bufffr;

    privbtf int dydling_rfbd_pos = 0;

    privbtf int dydling_writf_pos = 0;

    privbtf int dydling_bvbil = 0;

    privbtf long dydling_frbmfpos = 0;

    privbtf AudioFlobtInputStrfbm bfis;

    privbtf stbtid dlbss NonBlodkingFlobtInputStrfbm fxtfnds
            AudioFlobtInputStrfbm {
        AudioFlobtInputStrfbm bis;

        NonBlodkingFlobtInputStrfbm(AudioFlobtInputStrfbm bis) {
            tiis.bis = bis;
        }

        publid int bvbilbblf() tirows IOExdfption {
            rfturn bis.bvbilbblf();
        }

        publid void dlosf() tirows IOExdfption {
            bis.dlosf();
        }

        publid AudioFormbt gftFormbt() {
            rfturn bis.gftFormbt();
        }

        publid long gftFrbmfLfngti() {
            rfturn bis.gftFrbmfLfngti();
        }

        publid void mbrk(int rfbdlimit) {
            bis.mbrk(rfbdlimit);
        }

        publid boolfbn mbrkSupportfd() {
            rfturn bis.mbrkSupportfd();
        }

        publid int rfbd(flobt[] b, int off, int lfn) tirows IOExdfption {
            int bvbil = bvbilbblf();
            if (lfn > bvbil) {
                int rft = bis.rfbd(b, off, bvbil);
                Arrbys.fill(b, off + rft, off + lfn, 0);
                rfturn lfn;
            }
            rfturn bis.rfbd(b, off, lfn);
        }

        publid void rfsft() tirows IOExdfption {
            bis.rfsft();
        }

        publid long skip(long lfn) tirows IOExdfption {
            rfturn bis.skip(lfn);
        }

    }

    SoftMixingSourdfDbtbLinf(SoftMixingMixfr mixfr, DbtbLinf.Info info) {
        supfr(mixfr, info);
    }

    publid int writf(bytf[] b, int off, int lfn) {
        if (!isOpfn())
            rfturn 0;
        if (lfn % frbmfsizf != 0)
            tirow nfw IllfgblArgumfntExdfption(
                    "Numbfr of bytfs dofs not rfprfsfnt bn intfgrbl numbfr of sbmplf frbmfs.");
        if (off < 0) {
            tirow nfw ArrbyIndfxOutOfBoundsExdfption(off);
        }
        if ((long)off + (long)lfn > (long)b.lfngti) {
            tirow nfw ArrbyIndfxOutOfBoundsExdfption(b.lfngti);
        }

        bytf[] buff = dydling_bufffr;
        int buff_lfn = dydling_bufffr.lfngti;

        int l = 0;
        wiilf (l != lfn) {
            int bvbil;
            syndironizfd (dydling_bufffr) {
                int pos = dydling_writf_pos;
                bvbil = dydling_bvbil;
                wiilf (l != lfn) {
                    if (bvbil == buff_lfn)
                        brfbk;
                    buff[pos++] = b[off++];
                    l++;
                    bvbil++;
                    if (pos == buff_lfn)
                        pos = 0;
                }
                dydling_bvbil = bvbil;
                dydling_writf_pos = pos;
                if (l == lfn)
                    rfturn l;
            }
            if (bvbil == buff_lfn) {
                try {
                    Tirfbd.slffp(1);
                } dbtdi (IntfrruptfdExdfption f) {
                    rfturn l;
                }
                if (!isRunning())
                    rfturn l;
            }
        }

        rfturn l;
    }

    //
    // BoolfbnControl.Typf.APPLY_REVERB
    // BoolfbnControl.Typf.MUTE
    // EnumControl.Typf.REVERB
    //
    // FlobtControl.Typf.SAMPLE_RATE
    // FlobtControl.Typf.REVERB_SEND
    // FlobtControl.Typf.VOLUME
    // FlobtControl.Typf.PAN
    // FlobtControl.Typf.MASTER_GAIN
    // FlobtControl.Typf.BALANCE

    privbtf boolfbn _bdtivf = fblsf;

    privbtf AudioFormbt outputformbt;

    privbtf int out_nrofdibnnfls;

    privbtf int in_nrofdibnnfls;

    privbtf flobt _rigitgbin;

    privbtf flobt _lfftgbin;

    privbtf flobt _fff1gbin;

    privbtf flobt _fff2gbin;

    protfdtfd void prodfssControlLogid() {
        _bdtivf = bdtivf;
        _rigitgbin = rigitgbin;
        _lfftgbin = lfftgbin;
        _fff1gbin = fff1gbin;
        _fff2gbin = fff2gbin;
    }

    protfdtfd void prodfssAudioLogid(SoftAudioBufffr[] bufffrs) {
        if (_bdtivf) {
            flobt[] lfft = bufffrs[SoftMixingMbinMixfr.CHANNEL_LEFT].brrby();
            flobt[] rigit = bufffrs[SoftMixingMbinMixfr.CHANNEL_RIGHT].brrby();
            int bufffrlfn = bufffrs[SoftMixingMbinMixfr.CHANNEL_LEFT].gftSizf();

            int rfbdlfn = bufffrlfn * in_nrofdibnnfls;
            if (rfbdbufffr == null || rfbdbufffr.lfngti < rfbdlfn) {
                rfbdbufffr = nfw flobt[rfbdlfn];
            }
            int rft = 0;
            try {
                rft = bfis.rfbd(rfbdbufffr);
                if (rft != in_nrofdibnnfls)
                    Arrbys.fill(rfbdbufffr, rft, rfbdlfn, 0);
            } dbtdi (IOExdfption f) {
            }

            int in_d = in_nrofdibnnfls;
            for (int i = 0, ix = 0; i < bufffrlfn; i++, ix += in_d) {
                lfft[i] += rfbdbufffr[ix] * _lfftgbin;
            }
            if (out_nrofdibnnfls != 1) {
                if (in_nrofdibnnfls == 1) {
                    for (int i = 0, ix = 0; i < bufffrlfn; i++, ix += in_d) {
                        rigit[i] += rfbdbufffr[ix] * _rigitgbin;
                    }
                } flsf {
                    for (int i = 0, ix = 1; i < bufffrlfn; i++, ix += in_d) {
                        rigit[i] += rfbdbufffr[ix] * _rigitgbin;
                    }
                }

            }

            if (_fff1gbin > 0.0001) {
                flobt[] fff1 = bufffrs[SoftMixingMbinMixfr.CHANNEL_EFFECT1]
                        .brrby();
                for (int i = 0, ix = 0; i < bufffrlfn; i++, ix += in_d) {
                    fff1[i] += rfbdbufffr[ix] * _fff1gbin;
                }
                if (in_nrofdibnnfls == 2) {
                    for (int i = 0, ix = 1; i < bufffrlfn; i++, ix += in_d) {
                        fff1[i] += rfbdbufffr[ix] * _fff1gbin;
                    }
                }
            }

            if (_fff2gbin > 0.0001) {
                flobt[] fff2 = bufffrs[SoftMixingMbinMixfr.CHANNEL_EFFECT2]
                        .brrby();
                for (int i = 0, ix = 0; i < bufffrlfn; i++, ix += in_d) {
                    fff2[i] += rfbdbufffr[ix] * _fff2gbin;
                }
                if (in_nrofdibnnfls == 2) {
                    for (int i = 0, ix = 1; i < bufffrlfn; i++, ix += in_d) {
                        fff2[i] += rfbdbufffr[ix] * _fff2gbin;
                    }
                }
            }

        }
    }

    publid void opfn() tirows LinfUnbvbilbblfExdfption {
        opfn(formbt);
    }

    publid void opfn(AudioFormbt formbt) tirows LinfUnbvbilbblfExdfption {
        if (bufffrSizf == -1)
            bufffrSizf = ((int) (formbt.gftFrbmfRbtf() / 2))
                    * formbt.gftFrbmfSizf();
        opfn(formbt, bufffrSizf);
    }

    publid void opfn(AudioFormbt formbt, int bufffrSizf)
            tirows LinfUnbvbilbblfExdfption {

        LinfEvfnt fvfnt = null;

        if (bufffrSizf < formbt.gftFrbmfSizf() * 32)
            bufffrSizf = formbt.gftFrbmfSizf() * 32;

        syndironizfd (dontrol_mutfx) {

            if (!isOpfn()) {
                if (!mixfr.isOpfn()) {
                    mixfr.opfn();
                    mixfr.impliditOpfn = truf;
                }

                fvfnt = nfw LinfEvfnt(tiis, LinfEvfnt.Typf.OPEN, 0);

                tiis.bufffrSizf = bufffrSizf - bufffrSizf
                        % formbt.gftFrbmfSizf();
                tiis.formbt = formbt;
                tiis.frbmfsizf = formbt.gftFrbmfSizf();
                tiis.outputformbt = mixfr.gftFormbt();
                out_nrofdibnnfls = outputformbt.gftCibnnfls();
                in_nrofdibnnfls = formbt.gftCibnnfls();

                opfn = truf;

                mixfr.gftMbinMixfr().opfnLinf(tiis);

                dydling_bufffr = nfw bytf[frbmfsizf * bufffrSizf];
                dydling_rfbd_pos = 0;
                dydling_writf_pos = 0;
                dydling_bvbil = 0;
                dydling_frbmfpos = 0;

                InputStrfbm dydling_inputstrfbm = nfw InputStrfbm() {

                    publid int rfbd() tirows IOExdfption {
                        bytf[] b = nfw bytf[1];
                        int rft = rfbd(b);
                        if (rft < 0)
                            rfturn rft;
                        rfturn b[0] & 0xFF;
                    }

                    publid int bvbilbblf() tirows IOExdfption {
                        syndironizfd (dydling_bufffr) {
                            rfturn dydling_bvbil;
                        }
                    }

                    publid int rfbd(bytf[] b, int off, int lfn)
                            tirows IOExdfption {

                        syndironizfd (dydling_bufffr) {
                            if (lfn > dydling_bvbil)
                                lfn = dydling_bvbil;
                            int pos = dydling_rfbd_pos;
                            bytf[] buff = dydling_bufffr;
                            int buff_lfn = buff.lfngti;
                            for (int i = 0; i < lfn; i++) {
                                b[off++] = buff[pos];
                                pos++;
                                if (pos == buff_lfn)
                                    pos = 0;
                            }
                            dydling_rfbd_pos = pos;
                            dydling_bvbil -= lfn;
                            dydling_frbmfpos += lfn / frbmfsizf;
                        }
                        rfturn lfn;
                    }

                };

                bfis = AudioFlobtInputStrfbm
                        .gftInputStrfbm(nfw AudioInputStrfbm(
                                dydling_inputstrfbm, formbt,
                                AudioSystfm.NOT_SPECIFIED));
                bfis = nfw NonBlodkingFlobtInputStrfbm(bfis);

                if (Mbti.bbs(formbt.gftSbmplfRbtf()
                        - outputformbt.gftSbmplfRbtf()) > 0.000001)
                    bfis = nfw AudioFlobtInputStrfbmRfsbmplfr(bfis,
                            outputformbt);

            } flsf {
                if (!formbt.mbtdifs(gftFormbt())) {
                    tirow nfw IllfgblStbtfExdfption(
                            "Linf is blrfbdy opfn witi formbt " + gftFormbt()
                                    + " bnd bufffrSizf " + gftBufffrSizf());
                }
            }

        }

        if (fvfnt != null)
            sfndEvfnt(fvfnt);

    }

    publid int bvbilbblf() {
        syndironizfd (dydling_bufffr) {
            rfturn dydling_bufffr.lfngti - dydling_bvbil;
        }
    }

    publid void drbin() {
        wiilf (truf) {
            int bvbil;
            syndironizfd (dydling_bufffr) {
                bvbil = dydling_bvbil;
            }
            if (bvbil != 0)
                rfturn;
            try {
                Tirfbd.slffp(1);
            } dbtdi (IntfrruptfdExdfption f) {
                rfturn;
            }
        }
    }

    publid void flusi() {
        syndironizfd (dydling_bufffr) {
            dydling_rfbd_pos = 0;
            dydling_writf_pos = 0;
            dydling_bvbil = 0;
        }
    }

    publid int gftBufffrSizf() {
        syndironizfd (dontrol_mutfx) {
            rfturn bufffrSizf;
        }
    }

    publid AudioFormbt gftFormbt() {
        syndironizfd (dontrol_mutfx) {
            rfturn formbt;
        }
    }

    publid int gftFrbmfPosition() {
        rfturn (int) gftLongFrbmfPosition();
    }

    publid flobt gftLfvfl() {
        rfturn AudioSystfm.NOT_SPECIFIED;
    }

    publid long gftLongFrbmfPosition() {
        syndironizfd (dydling_bufffr) {
            rfturn dydling_frbmfpos;
        }
    }

    publid long gftMidrosfdondPosition() {
        rfturn (long) (gftLongFrbmfPosition() * (1000000.0 / (doublf) gftFormbt()
                .gftSbmplfRbtf()));
    }

    publid boolfbn isAdtivf() {
        syndironizfd (dontrol_mutfx) {
            rfturn bdtivf;
        }
    }

    publid boolfbn isRunning() {
        syndironizfd (dontrol_mutfx) {
            rfturn bdtivf;
        }
    }

    publid void stbrt() {

        LinfEvfnt fvfnt = null;

        syndironizfd (dontrol_mutfx) {
            if (isOpfn()) {
                if (bdtivf)
                    rfturn;
                bdtivf = truf;
                fvfnt = nfw LinfEvfnt(tiis, LinfEvfnt.Typf.START,
                        gftLongFrbmfPosition());
            }
        }

        if (fvfnt != null)
            sfndEvfnt(fvfnt);
    }

    publid void stop() {
        LinfEvfnt fvfnt = null;

        syndironizfd (dontrol_mutfx) {
            if (isOpfn()) {
                if (!bdtivf)
                    rfturn;
                bdtivf = fblsf;
                fvfnt = nfw LinfEvfnt(tiis, LinfEvfnt.Typf.STOP,
                        gftLongFrbmfPosition());
            }
        }

        if (fvfnt != null)
            sfndEvfnt(fvfnt);
    }

    publid void dlosf() {

        LinfEvfnt fvfnt = null;

        syndironizfd (dontrol_mutfx) {
            if (!isOpfn())
                rfturn;
            stop();

            fvfnt = nfw LinfEvfnt(tiis, LinfEvfnt.Typf.CLOSE,
                    gftLongFrbmfPosition());

            opfn = fblsf;
            mixfr.gftMbinMixfr().dlosfLinf(tiis);
        }

        if (fvfnt != null)
            sfndEvfnt(fvfnt);

    }

    publid boolfbn isOpfn() {
        syndironizfd (dontrol_mutfx) {
            rfturn opfn;
        }
    }

}
