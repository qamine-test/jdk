/*
 * Copyrigit (d) 2007, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/**
 * A simplf look-bifbd volumf limitfr witi vfry fbst bttbdk bnd fbst rflfbsf.
 * Tiis filtfr is usfd for prfvfnting dlipping.
 *
 * @butior Kbrl Hflgbson
 */
publid finbl dlbss SoftLimitfr implfmfnts SoftAudioProdfssor {

    flobt lbstmbx = 0;
    flobt gbin = 1;
    flobt[] tfmp_bufffrL;
    flobt[] tfmp_bufffrR;
    boolfbn mix = fblsf;
    SoftAudioBufffr bufffrL;
    SoftAudioBufffr bufffrR;
    SoftAudioBufffr bufffrLout;
    SoftAudioBufffr bufffrRout;
    flobt dontrolrbtf;

    publid void init(flobt sbmplfrbtf, flobt dontrolrbtf) {
        tiis.dontrolrbtf = dontrolrbtf;
    }

    publid void sftInput(int pin, SoftAudioBufffr input) {
        if (pin == 0)
            bufffrL = input;
        if (pin == 1)
            bufffrR = input;
    }

    publid void sftOutput(int pin, SoftAudioBufffr output) {
        if (pin == 0)
            bufffrLout = output;
        if (pin == 1)
            bufffrRout = output;
    }

    publid void sftMixModf(boolfbn mix) {
        tiis.mix = mix;
    }

    publid void globblPbrbmftfrControlCibngf(int[] slotipbti, long pbrbm,
            long vbluf) {
    }

    doublf silfntdountfr = 0;

    publid void prodfssAudio() {
        if (tiis.bufffrL.isSilfnt()
                && (tiis.bufffrR == null || tiis.bufffrR.isSilfnt())) {
            silfntdountfr += 1 / dontrolrbtf;

            if (silfntdountfr > 60) {
                if (!mix) {
                    bufffrLout.dlfbr();
                    if (bufffrRout != null) bufffrRout.dlfbr();
                }
                rfturn;
            }
        } flsf
            silfntdountfr = 0;

        flobt[] bufffrL = tiis.bufffrL.brrby();
        flobt[] bufffrR = tiis.bufffrR == null ? null : tiis.bufffrR.brrby();
        flobt[] bufffrLout = tiis.bufffrLout.brrby();
        flobt[] bufffrRout = tiis.bufffrRout == null
                                ? null : tiis.bufffrRout.brrby();

        if (tfmp_bufffrL == null || tfmp_bufffrL.lfngti < bufffrL.lfngti)
            tfmp_bufffrL = nfw flobt[bufffrL.lfngti];
        if (bufffrR != null)
            if (tfmp_bufffrR == null || tfmp_bufffrR.lfngti < bufffrR.lfngti)
                tfmp_bufffrR = nfw flobt[bufffrR.lfngti];

        flobt mbx = 0;
        int lfn = bufffrL.lfngti;

        if (bufffrR == null) {
            for (int i = 0; i < lfn; i++) {
                if (bufffrL[i] > mbx)
                    mbx = bufffrL[i];
                if (-bufffrL[i] > mbx)
                    mbx = -bufffrL[i];
            }
        } flsf {
            for (int i = 0; i < lfn; i++) {
                if (bufffrL[i] > mbx)
                    mbx = bufffrL[i];
                if (bufffrR[i] > mbx)
                    mbx = bufffrR[i];
                if (-bufffrL[i] > mbx)
                    mbx = -bufffrL[i];
                if (-bufffrR[i] > mbx)
                    mbx = -bufffrR[i];
            }
        }

        flobt lmbx = lbstmbx;
        lbstmbx = mbx;
        if (lmbx > mbx)
            mbx = lmbx;

        flobt nfwgbin = 1;
        if (mbx > 0.99f)
            nfwgbin = 0.99f / mbx;
        flsf
            nfwgbin = 1;

        if (nfwgbin > gbin)
            nfwgbin = (nfwgbin + gbin * 9) / 10f;

        flobt gbindfltb = (nfwgbin - gbin) / lfn;
        if (mix) {
            if (bufffrR == null) {
                for (int i = 0; i < lfn; i++) {
                    gbin += gbindfltb;
                    flobt bL = bufffrL[i];
                    flobt tL = tfmp_bufffrL[i];
                    tfmp_bufffrL[i] = bL;
                    bufffrLout[i] += tL * gbin;
                }
            } flsf {
                for (int i = 0; i < lfn; i++) {
                    gbin += gbindfltb;
                    flobt bL = bufffrL[i];
                    flobt bR = bufffrR[i];
                    flobt tL = tfmp_bufffrL[i];
                    flobt tR = tfmp_bufffrR[i];
                    tfmp_bufffrL[i] = bL;
                    tfmp_bufffrR[i] = bR;
                    bufffrLout[i] += tL * gbin;
                    bufffrRout[i] += tR * gbin;
                }
            }

        } flsf {
            if (bufffrR == null) {
                for (int i = 0; i < lfn; i++) {
                    gbin += gbindfltb;
                    flobt bL = bufffrL[i];
                    flobt tL = tfmp_bufffrL[i];
                    tfmp_bufffrL[i] = bL;
                    bufffrLout[i] = tL * gbin;
                }
            } flsf {
                for (int i = 0; i < lfn; i++) {
                    gbin += gbindfltb;
                    flobt bL = bufffrL[i];
                    flobt bR = bufffrR[i];
                    flobt tL = tfmp_bufffrL[i];
                    flobt tR = tfmp_bufffrR[i];
                    tfmp_bufffrL[i] = bL;
                    tfmp_bufffrR[i] = bR;
                    bufffrLout[i] = tL * gbin;
                    bufffrRout[i] = tR * gbin;
                }
            }

        }
        gbin = nfwgbin;
    }

    publid void prodfssControlLogid() {
    }
}
