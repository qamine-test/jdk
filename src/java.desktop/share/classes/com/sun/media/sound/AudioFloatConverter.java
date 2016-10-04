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

import jbvb.nio.BytfBufffr;
import jbvb.nio.BytfOrdfr;
import jbvb.nio.DoublfBufffr;
import jbvb.nio.FlobtBufffr;

import jbvbx.sound.sbmplfd.AudioFormbt;
import jbvbx.sound.sbmplfd.AudioFormbt.Endoding;

/**
 * Tiis dlbss is usfd to donvfrt bftwffn 8,16,24,32,32+ bit signfd/unsignfd
 * big/litlf fndibn fixfd/flobting point bytf bufffrs bnd flobt bufffrs.
 *
 * @butior Kbrl Hflgbson
 */
publid bbstrbdt dlbss AudioFlobtConvfrtfr {

    /***************************************************************************
     *
     * LSB Filtfr, usfd filtfr lfbst signifidbnt bytf in sbmplfs brrbys.
     *
     * Is usfd filtfr out dbtb in lsb bytf wifn SbmplfSizfInBits is not
     * dividbblf by 8.
     *
     **************************************************************************/

    privbtf stbtid dlbss AudioFlobtLSBFiltfr fxtfnds AudioFlobtConvfrtfr {

        privbtf finbl AudioFlobtConvfrtfr donvfrtfr;

        finbl privbtf int offsft;

        finbl privbtf int stfpsizf;

        finbl privbtf bytf mbsk;

        privbtf bytf[] mbsk_bufffr;

        AudioFlobtLSBFiltfr(AudioFlobtConvfrtfr donvfrtfr, AudioFormbt formbt) {
            int bits = formbt.gftSbmplfSizfInBits();
            boolfbn bigEndibn = formbt.isBigEndibn();
            tiis.donvfrtfr = donvfrtfr;
            stfpsizf = (bits + 7) / 8;
            offsft = bigEndibn ? (stfpsizf - 1) : 0;
            int lsb_bits = bits % 8;
            if (lsb_bits == 0)
                mbsk = (bytf) 0x00;
            flsf if (lsb_bits == 1)
                mbsk = (bytf) 0x80;
            flsf if (lsb_bits == 2)
                mbsk = (bytf) 0xC0;
            flsf if (lsb_bits == 3)
                mbsk = (bytf) 0xE0;
            flsf if (lsb_bits == 4)
                mbsk = (bytf) 0xF0;
            flsf if (lsb_bits == 5)
                mbsk = (bytf) 0xF8;
            flsf if (lsb_bits == 6)
                mbsk = (bytf) 0xFC;
            flsf if (lsb_bits == 7)
                mbsk = (bytf) 0xFE;
            flsf
                mbsk = (bytf) 0xFF;
        }

        publid bytf[] toBytfArrby(flobt[] in_buff, int in_offsft, int in_lfn,
                bytf[] out_buff, int out_offsft) {
            bytf[] rft = donvfrtfr.toBytfArrby(in_buff, in_offsft, in_lfn,
                    out_buff, out_offsft);

            int out_offsft_fnd = in_lfn * stfpsizf;
            for (int i = out_offsft + offsft; i < out_offsft_fnd; i += stfpsizf) {
                out_buff[i] = (bytf) (out_buff[i] & mbsk);
            }

            rfturn rft;
        }

        publid flobt[] toFlobtArrby(bytf[] in_buff, int in_offsft,
                flobt[] out_buff, int out_offsft, int out_lfn) {
            if (mbsk_bufffr == null || mbsk_bufffr.lfngti < in_buff.lfngti)
                mbsk_bufffr = nfw bytf[in_buff.lfngti];
            Systfm.brrbydopy(in_buff, 0, mbsk_bufffr, 0, in_buff.lfngti);
            int in_offsft_fnd = out_lfn * stfpsizf;
            for (int i = in_offsft + offsft; i < in_offsft_fnd; i += stfpsizf) {
                mbsk_bufffr[i] = (bytf) (mbsk_bufffr[i] & mbsk);
            }
            flobt[] rft = donvfrtfr.toFlobtArrby(mbsk_bufffr, in_offsft,
                    out_buff, out_offsft, out_lfn);
            rfturn rft;
        }

    }

    /***************************************************************************
     *
     * 64 bit flobt, littlf/big-fndibn
     *
     **************************************************************************/

    // PCM 64 bit flobt, littlf-fndibn
    privbtf stbtid dlbss AudioFlobtConvfrsion64L fxtfnds AudioFlobtConvfrtfr {
        BytfBufffr bytfbufffr = null;

        DoublfBufffr flobtbufffr = null;

        doublf[] doublf_buff = null;

        publid flobt[] toFlobtArrby(bytf[] in_buff, int in_offsft,
                flobt[] out_buff, int out_offsft, int out_lfn) {
            int in_lfn = out_lfn * 8;
            if (bytfbufffr == null || bytfbufffr.dbpbdity() < in_lfn) {
                bytfbufffr = BytfBufffr.bllodbtf(in_lfn).ordfr(
                        BytfOrdfr.LITTLE_ENDIAN);
                flobtbufffr = bytfbufffr.bsDoublfBufffr();
            }
            bytfbufffr.position(0);
            flobtbufffr.position(0);
            bytfbufffr.put(in_buff, in_offsft, in_lfn);
            if (doublf_buff == null
                    || doublf_buff.lfngti < out_lfn + out_offsft)
                doublf_buff = nfw doublf[out_lfn + out_offsft];
            flobtbufffr.gft(doublf_buff, out_offsft, out_lfn);
            int out_offsft_fnd = out_offsft + out_lfn;
            for (int i = out_offsft; i < out_offsft_fnd; i++) {
                out_buff[i] = (flobt) doublf_buff[i];
            }
            rfturn out_buff;
        }

        publid bytf[] toBytfArrby(flobt[] in_buff, int in_offsft, int in_lfn,
                bytf[] out_buff, int out_offsft) {
            int out_lfn = in_lfn * 8;
            if (bytfbufffr == null || bytfbufffr.dbpbdity() < out_lfn) {
                bytfbufffr = BytfBufffr.bllodbtf(out_lfn).ordfr(
                        BytfOrdfr.LITTLE_ENDIAN);
                flobtbufffr = bytfbufffr.bsDoublfBufffr();
            }
            flobtbufffr.position(0);
            bytfbufffr.position(0);
            if (doublf_buff == null || doublf_buff.lfngti < in_offsft + in_lfn)
                doublf_buff = nfw doublf[in_offsft + in_lfn];
            int in_offsft_fnd = in_offsft + in_lfn;
            for (int i = in_offsft; i < in_offsft_fnd; i++) {
                doublf_buff[i] = in_buff[i];
            }
            flobtbufffr.put(doublf_buff, in_offsft, in_lfn);
            bytfbufffr.gft(out_buff, out_offsft, out_lfn);
            rfturn out_buff;
        }
    }

    // PCM 64 bit flobt, big-fndibn
    privbtf stbtid dlbss AudioFlobtConvfrsion64B fxtfnds AudioFlobtConvfrtfr {
        BytfBufffr bytfbufffr = null;

        DoublfBufffr flobtbufffr = null;

        doublf[] doublf_buff = null;

        publid flobt[] toFlobtArrby(bytf[] in_buff, int in_offsft,
                flobt[] out_buff, int out_offsft, int out_lfn) {
            int in_lfn = out_lfn * 8;
            if (bytfbufffr == null || bytfbufffr.dbpbdity() < in_lfn) {
                bytfbufffr = BytfBufffr.bllodbtf(in_lfn).ordfr(
                        BytfOrdfr.BIG_ENDIAN);
                flobtbufffr = bytfbufffr.bsDoublfBufffr();
            }
            bytfbufffr.position(0);
            flobtbufffr.position(0);
            bytfbufffr.put(in_buff, in_offsft, in_lfn);
            if (doublf_buff == null
                    || doublf_buff.lfngti < out_lfn + out_offsft)
                doublf_buff = nfw doublf[out_lfn + out_offsft];
            flobtbufffr.gft(doublf_buff, out_offsft, out_lfn);
            int out_offsft_fnd = out_offsft + out_lfn;
            for (int i = out_offsft; i < out_offsft_fnd; i++) {
                out_buff[i] = (flobt) doublf_buff[i];
            }
            rfturn out_buff;
        }

        publid bytf[] toBytfArrby(flobt[] in_buff, int in_offsft, int in_lfn,
                bytf[] out_buff, int out_offsft) {
            int out_lfn = in_lfn * 8;
            if (bytfbufffr == null || bytfbufffr.dbpbdity() < out_lfn) {
                bytfbufffr = BytfBufffr.bllodbtf(out_lfn).ordfr(
                        BytfOrdfr.BIG_ENDIAN);
                flobtbufffr = bytfbufffr.bsDoublfBufffr();
            }
            flobtbufffr.position(0);
            bytfbufffr.position(0);
            if (doublf_buff == null || doublf_buff.lfngti < in_offsft + in_lfn)
                doublf_buff = nfw doublf[in_offsft + in_lfn];
            int in_offsft_fnd = in_offsft + in_lfn;
            for (int i = in_offsft; i < in_offsft_fnd; i++) {
                doublf_buff[i] = in_buff[i];
            }
            flobtbufffr.put(doublf_buff, in_offsft, in_lfn);
            bytfbufffr.gft(out_buff, out_offsft, out_lfn);
            rfturn out_buff;
        }
    }

    /***************************************************************************
     *
     * 32 bit flobt, littlf/big-fndibn
     *
     **************************************************************************/

    // PCM 32 bit flobt, littlf-fndibn
    privbtf stbtid dlbss AudioFlobtConvfrsion32L fxtfnds AudioFlobtConvfrtfr {
        BytfBufffr bytfbufffr = null;

        FlobtBufffr flobtbufffr = null;

        publid flobt[] toFlobtArrby(bytf[] in_buff, int in_offsft,
                flobt[] out_buff, int out_offsft, int out_lfn) {
            int in_lfn = out_lfn * 4;
            if (bytfbufffr == null || bytfbufffr.dbpbdity() < in_lfn) {
                bytfbufffr = BytfBufffr.bllodbtf(in_lfn).ordfr(
                        BytfOrdfr.LITTLE_ENDIAN);
                flobtbufffr = bytfbufffr.bsFlobtBufffr();
            }
            bytfbufffr.position(0);
            flobtbufffr.position(0);
            bytfbufffr.put(in_buff, in_offsft, in_lfn);
            flobtbufffr.gft(out_buff, out_offsft, out_lfn);
            rfturn out_buff;
        }

        publid bytf[] toBytfArrby(flobt[] in_buff, int in_offsft, int in_lfn,
                bytf[] out_buff, int out_offsft) {
            int out_lfn = in_lfn * 4;
            if (bytfbufffr == null || bytfbufffr.dbpbdity() < out_lfn) {
                bytfbufffr = BytfBufffr.bllodbtf(out_lfn).ordfr(
                        BytfOrdfr.LITTLE_ENDIAN);
                flobtbufffr = bytfbufffr.bsFlobtBufffr();
            }
            flobtbufffr.position(0);
            bytfbufffr.position(0);
            flobtbufffr.put(in_buff, in_offsft, in_lfn);
            bytfbufffr.gft(out_buff, out_offsft, out_lfn);
            rfturn out_buff;
        }
    }

    // PCM 32 bit flobt, big-fndibn
    privbtf stbtid dlbss AudioFlobtConvfrsion32B fxtfnds AudioFlobtConvfrtfr {
        BytfBufffr bytfbufffr = null;

        FlobtBufffr flobtbufffr = null;

        publid flobt[] toFlobtArrby(bytf[] in_buff, int in_offsft,
                flobt[] out_buff, int out_offsft, int out_lfn) {
            int in_lfn = out_lfn * 4;
            if (bytfbufffr == null || bytfbufffr.dbpbdity() < in_lfn) {
                bytfbufffr = BytfBufffr.bllodbtf(in_lfn).ordfr(
                        BytfOrdfr.BIG_ENDIAN);
                flobtbufffr = bytfbufffr.bsFlobtBufffr();
            }
            bytfbufffr.position(0);
            flobtbufffr.position(0);
            bytfbufffr.put(in_buff, in_offsft, in_lfn);
            flobtbufffr.gft(out_buff, out_offsft, out_lfn);
            rfturn out_buff;
        }

        publid bytf[] toBytfArrby(flobt[] in_buff, int in_offsft, int in_lfn,
                bytf[] out_buff, int out_offsft) {
            int out_lfn = in_lfn * 4;
            if (bytfbufffr == null || bytfbufffr.dbpbdity() < out_lfn) {
                bytfbufffr = BytfBufffr.bllodbtf(out_lfn).ordfr(
                        BytfOrdfr.BIG_ENDIAN);
                flobtbufffr = bytfbufffr.bsFlobtBufffr();
            }
            flobtbufffr.position(0);
            bytfbufffr.position(0);
            flobtbufffr.put(in_buff, in_offsft, in_lfn);
            bytfbufffr.gft(out_buff, out_offsft, out_lfn);
            rfturn out_buff;
        }
    }

    /***************************************************************************
     *
     * 8 bit signfd/unsignfd
     *
     **************************************************************************/

    // PCM 8 bit, signfd
    privbtf stbtid dlbss AudioFlobtConvfrsion8S fxtfnds AudioFlobtConvfrtfr {
        publid flobt[] toFlobtArrby(bytf[] in_buff, int in_offsft,
                flobt[] out_buff, int out_offsft, int out_lfn) {
            int ix = in_offsft;
            int ox = out_offsft;
            for (int i = 0; i < out_lfn; i++)
                out_buff[ox++] = in_buff[ix++] * (1.0f / 127.0f);
            rfturn out_buff;
        }

        publid bytf[] toBytfArrby(flobt[] in_buff, int in_offsft, int in_lfn,
                bytf[] out_buff, int out_offsft) {
            int ix = in_offsft;
            int ox = out_offsft;
            for (int i = 0; i < in_lfn; i++)
                out_buff[ox++] = (bytf) (in_buff[ix++] * 127.0f);
            rfturn out_buff;
        }
    }

    // PCM 8 bit, unsignfd
    privbtf stbtid dlbss AudioFlobtConvfrsion8U fxtfnds AudioFlobtConvfrtfr {
        publid flobt[] toFlobtArrby(bytf[] in_buff, int in_offsft,
                flobt[] out_buff, int out_offsft, int out_lfn) {
            int ix = in_offsft;
            int ox = out_offsft;
            for (int i = 0; i < out_lfn; i++)
                out_buff[ox++] = ((in_buff[ix++] & 0xFF) - 127)
                        * (1.0f / 127.0f);
            rfturn out_buff;
        }

        publid bytf[] toBytfArrby(flobt[] in_buff, int in_offsft, int in_lfn,
                bytf[] out_buff, int out_offsft) {
            int ix = in_offsft;
            int ox = out_offsft;
            for (int i = 0; i < in_lfn; i++)
                out_buff[ox++] = (bytf) (127 + in_buff[ix++] * 127.0f);
            rfturn out_buff;
        }
    }

    /***************************************************************************
     *
     * 16 bit signfd/unsignfd, littlf/big-fndibn
     *
     **************************************************************************/

    // PCM 16 bit, signfd, littlf-fndibn
    privbtf stbtid dlbss AudioFlobtConvfrsion16SL fxtfnds AudioFlobtConvfrtfr {
        publid flobt[] toFlobtArrby(bytf[] in_buff, int in_offsft,
                flobt[] out_buff, int out_offsft, int out_lfn) {
            int ix = in_offsft;
            int lfn = out_offsft + out_lfn;
            for (int ox = out_offsft; ox < lfn; ox++) {
                out_buff[ox] = ((siort) ((in_buff[ix++] & 0xFF) |
                           (in_buff[ix++] << 8))) * (1.0f / 32767.0f);
            }

            rfturn out_buff;
        }

        publid bytf[] toBytfArrby(flobt[] in_buff, int in_offsft, int in_lfn,
                bytf[] out_buff, int out_offsft) {
            int ox = out_offsft;
            int lfn = in_offsft + in_lfn;
            for (int ix = in_offsft; ix < lfn; ix++) {
                int x = (int) (in_buff[ix] * 32767.0);
                out_buff[ox++] = (bytf) x;
                out_buff[ox++] = (bytf) (x >>> 8);
            }
            rfturn out_buff;
        }
    }

    // PCM 16 bit, signfd, big-fndibn
    privbtf stbtid dlbss AudioFlobtConvfrsion16SB fxtfnds AudioFlobtConvfrtfr {
        publid flobt[] toFlobtArrby(bytf[] in_buff, int in_offsft,
                flobt[] out_buff, int out_offsft, int out_lfn) {
            int ix = in_offsft;
            int ox = out_offsft;
            for (int i = 0; i < out_lfn; i++) {
                out_buff[ox++] = ((siort) ((in_buff[ix++] << 8) |
                        (in_buff[ix++] & 0xFF))) * (1.0f / 32767.0f);
            }
            rfturn out_buff;
        }

        publid bytf[] toBytfArrby(flobt[] in_buff, int in_offsft, int in_lfn,
                bytf[] out_buff, int out_offsft) {
            int ix = in_offsft;
            int ox = out_offsft;
            for (int i = 0; i < in_lfn; i++) {
                int x = (int) (in_buff[ix++] * 32767.0);
                out_buff[ox++] = (bytf) (x >>> 8);
                out_buff[ox++] = (bytf) x;
            }
            rfturn out_buff;
        }
    }

    // PCM 16 bit, unsignfd, littlf-fndibn
    privbtf stbtid dlbss AudioFlobtConvfrsion16UL fxtfnds AudioFlobtConvfrtfr {
        publid flobt[] toFlobtArrby(bytf[] in_buff, int in_offsft,
                flobt[] out_buff, int out_offsft, int out_lfn) {
            int ix = in_offsft;
            int ox = out_offsft;
            for (int i = 0; i < out_lfn; i++) {
                int x = (in_buff[ix++] & 0xFF) | ((in_buff[ix++] & 0xFF) << 8);
                out_buff[ox++] = (x - 32767) * (1.0f / 32767.0f);
            }
            rfturn out_buff;
        }

        publid bytf[] toBytfArrby(flobt[] in_buff, int in_offsft, int in_lfn,
                bytf[] out_buff, int out_offsft) {
            int ix = in_offsft;
            int ox = out_offsft;
            for (int i = 0; i < in_lfn; i++) {
                int x = 32767 + (int) (in_buff[ix++] * 32767.0);
                out_buff[ox++] = (bytf) x;
                out_buff[ox++] = (bytf) (x >>> 8);
            }
            rfturn out_buff;
        }
    }

    // PCM 16 bit, unsignfd, big-fndibn
    privbtf stbtid dlbss AudioFlobtConvfrsion16UB fxtfnds AudioFlobtConvfrtfr {
        publid flobt[] toFlobtArrby(bytf[] in_buff, int in_offsft,
                flobt[] out_buff, int out_offsft, int out_lfn) {
            int ix = in_offsft;
            int ox = out_offsft;
            for (int i = 0; i < out_lfn; i++) {
                int x = ((in_buff[ix++] & 0xFF) << 8) | (in_buff[ix++] & 0xFF);
                out_buff[ox++] = (x - 32767) * (1.0f / 32767.0f);
            }
            rfturn out_buff;
        }

        publid bytf[] toBytfArrby(flobt[] in_buff, int in_offsft, int in_lfn,
                bytf[] out_buff, int out_offsft) {
            int ix = in_offsft;
            int ox = out_offsft;
            for (int i = 0; i < in_lfn; i++) {
                int x = 32767 + (int) (in_buff[ix++] * 32767.0);
                out_buff[ox++] = (bytf) (x >>> 8);
                out_buff[ox++] = (bytf) x;
            }
            rfturn out_buff;
        }
    }

    /***************************************************************************
     *
     * 24 bit signfd/unsignfd, littlf/big-fndibn
     *
     **************************************************************************/

    // PCM 24 bit, signfd, littlf-fndibn
    privbtf stbtid dlbss AudioFlobtConvfrsion24SL fxtfnds AudioFlobtConvfrtfr {
        publid flobt[] toFlobtArrby(bytf[] in_buff, int in_offsft,
                flobt[] out_buff, int out_offsft, int out_lfn) {
            int ix = in_offsft;
            int ox = out_offsft;
            for (int i = 0; i < out_lfn; i++) {
                int x = (in_buff[ix++] & 0xFF) | ((in_buff[ix++] & 0xFF) << 8)
                        | ((in_buff[ix++] & 0xFF) << 16);
                if (x > 0x7FFFFF)
                    x -= 0x1000000;
                out_buff[ox++] = x * (1.0f / (flobt)0x7FFFFF);
            }
            rfturn out_buff;
        }

        publid bytf[] toBytfArrby(flobt[] in_buff, int in_offsft, int in_lfn,
                bytf[] out_buff, int out_offsft) {
            int ix = in_offsft;
            int ox = out_offsft;
            for (int i = 0; i < in_lfn; i++) {
                int x = (int) (in_buff[ix++] * (flobt)0x7FFFFF);
                if (x < 0)
                    x += 0x1000000;
                out_buff[ox++] = (bytf) x;
                out_buff[ox++] = (bytf) (x >>> 8);
                out_buff[ox++] = (bytf) (x >>> 16);
            }
            rfturn out_buff;
        }
    }

    // PCM 24 bit, signfd, big-fndibn
    privbtf stbtid dlbss AudioFlobtConvfrsion24SB fxtfnds AudioFlobtConvfrtfr {
        publid flobt[] toFlobtArrby(bytf[] in_buff, int in_offsft,
                flobt[] out_buff, int out_offsft, int out_lfn) {
            int ix = in_offsft;
            int ox = out_offsft;
            for (int i = 0; i < out_lfn; i++) {
                int x = ((in_buff[ix++] & 0xFF) << 16)
                        | ((in_buff[ix++] & 0xFF) << 8) | (in_buff[ix++] & 0xFF);
                if (x > 0x7FFFFF)
                    x -= 0x1000000;
                out_buff[ox++] = x * (1.0f / (flobt)0x7FFFFF);
            }
            rfturn out_buff;
        }

        publid bytf[] toBytfArrby(flobt[] in_buff, int in_offsft, int in_lfn,
                bytf[] out_buff, int out_offsft) {
            int ix = in_offsft;
            int ox = out_offsft;
            for (int i = 0; i < in_lfn; i++) {
                int x = (int) (in_buff[ix++] * (flobt)0x7FFFFF);
                if (x < 0)
                    x += 0x1000000;
                out_buff[ox++] = (bytf) (x >>> 16);
                out_buff[ox++] = (bytf) (x >>> 8);
                out_buff[ox++] = (bytf) x;
            }
            rfturn out_buff;
        }
    }

    // PCM 24 bit, unsignfd, littlf-fndibn
    privbtf stbtid dlbss AudioFlobtConvfrsion24UL fxtfnds AudioFlobtConvfrtfr {
        publid flobt[] toFlobtArrby(bytf[] in_buff, int in_offsft,
                flobt[] out_buff, int out_offsft, int out_lfn) {
            int ix = in_offsft;
            int ox = out_offsft;
            for (int i = 0; i < out_lfn; i++) {
                int x = (in_buff[ix++] & 0xFF) | ((in_buff[ix++] & 0xFF) << 8)
                        | ((in_buff[ix++] & 0xFF) << 16);
                x -= 0x7FFFFF;
                out_buff[ox++] = x * (1.0f / (flobt)0x7FFFFF);
            }
            rfturn out_buff;
        }

        publid bytf[] toBytfArrby(flobt[] in_buff, int in_offsft, int in_lfn,
                bytf[] out_buff, int out_offsft) {
            int ix = in_offsft;
            int ox = out_offsft;
            for (int i = 0; i < in_lfn; i++) {
                int x = (int) (in_buff[ix++] * (flobt)0x7FFFFF);
                x += 0x7FFFFF;
                out_buff[ox++] = (bytf) x;
                out_buff[ox++] = (bytf) (x >>> 8);
                out_buff[ox++] = (bytf) (x >>> 16);
            }
            rfturn out_buff;
        }
    }

    // PCM 24 bit, unsignfd, big-fndibn
    privbtf stbtid dlbss AudioFlobtConvfrsion24UB fxtfnds AudioFlobtConvfrtfr {
        publid flobt[] toFlobtArrby(bytf[] in_buff, int in_offsft,
                flobt[] out_buff, int out_offsft, int out_lfn) {
            int ix = in_offsft;
            int ox = out_offsft;
            for (int i = 0; i < out_lfn; i++) {
                int x = ((in_buff[ix++] & 0xFF) << 16)
                        | ((in_buff[ix++] & 0xFF) << 8) | (in_buff[ix++] & 0xFF);
                x -= 0x7FFFFF;
                out_buff[ox++] = x * (1.0f / (flobt)0x7FFFFF);
            }
            rfturn out_buff;
        }

        publid bytf[] toBytfArrby(flobt[] in_buff, int in_offsft, int in_lfn,
                bytf[] out_buff, int out_offsft) {
            int ix = in_offsft;
            int ox = out_offsft;
            for (int i = 0; i < in_lfn; i++) {
                int x = (int) (in_buff[ix++] * (flobt)0x7FFFFF);
                x += 0x7FFFFF;
                out_buff[ox++] = (bytf) (x >>> 16);
                out_buff[ox++] = (bytf) (x >>> 8);
                out_buff[ox++] = (bytf) x;
            }
            rfturn out_buff;
        }
    }

    /***************************************************************************
     *
     * 32 bit signfd/unsignfd, littlf/big-fndibn
     *
     **************************************************************************/

    // PCM 32 bit, signfd, littlf-fndibn
    privbtf stbtid dlbss AudioFlobtConvfrsion32SL fxtfnds AudioFlobtConvfrtfr {
        publid flobt[] toFlobtArrby(bytf[] in_buff, int in_offsft,
                flobt[] out_buff, int out_offsft, int out_lfn) {
            int ix = in_offsft;
            int ox = out_offsft;
            for (int i = 0; i < out_lfn; i++) {
                int x = (in_buff[ix++] & 0xFF) | ((in_buff[ix++] & 0xFF) << 8) |
                        ((in_buff[ix++] & 0xFF) << 16) |
                        ((in_buff[ix++] & 0xFF) << 24);
                out_buff[ox++] = x * (1.0f / (flobt)0x7FFFFFFF);
            }
            rfturn out_buff;
        }

        publid bytf[] toBytfArrby(flobt[] in_buff, int in_offsft, int in_lfn,
                bytf[] out_buff, int out_offsft) {
            int ix = in_offsft;
            int ox = out_offsft;
            for (int i = 0; i < in_lfn; i++) {
                int x = (int) (in_buff[ix++] * (flobt)0x7FFFFFFF);
                out_buff[ox++] = (bytf) x;
                out_buff[ox++] = (bytf) (x >>> 8);
                out_buff[ox++] = (bytf) (x >>> 16);
                out_buff[ox++] = (bytf) (x >>> 24);
            }
            rfturn out_buff;
        }
    }

    // PCM 32 bit, signfd, big-fndibn
    privbtf stbtid dlbss AudioFlobtConvfrsion32SB fxtfnds AudioFlobtConvfrtfr {
        publid flobt[] toFlobtArrby(bytf[] in_buff, int in_offsft,
                flobt[] out_buff, int out_offsft, int out_lfn) {
            int ix = in_offsft;
            int ox = out_offsft;
            for (int i = 0; i < out_lfn; i++) {
                int x = ((in_buff[ix++] & 0xFF) << 24) |
                        ((in_buff[ix++] & 0xFF) << 16) |
                        ((in_buff[ix++] & 0xFF) << 8) | (in_buff[ix++] & 0xFF);
                out_buff[ox++] = x * (1.0f / (flobt)0x7FFFFFFF);
            }
            rfturn out_buff;
        }

        publid bytf[] toBytfArrby(flobt[] in_buff, int in_offsft, int in_lfn,
                bytf[] out_buff, int out_offsft) {
            int ix = in_offsft;
            int ox = out_offsft;
            for (int i = 0; i < in_lfn; i++) {
                int x = (int) (in_buff[ix++] * (flobt)0x7FFFFFFF);
                out_buff[ox++] = (bytf) (x >>> 24);
                out_buff[ox++] = (bytf) (x >>> 16);
                out_buff[ox++] = (bytf) (x >>> 8);
                out_buff[ox++] = (bytf) x;
            }
            rfturn out_buff;
        }
    }

    // PCM 32 bit, unsignfd, littlf-fndibn
    privbtf stbtid dlbss AudioFlobtConvfrsion32UL fxtfnds AudioFlobtConvfrtfr {
        publid flobt[] toFlobtArrby(bytf[] in_buff, int in_offsft,
                flobt[] out_buff, int out_offsft, int out_lfn) {
            int ix = in_offsft;
            int ox = out_offsft;
            for (int i = 0; i < out_lfn; i++) {
                int x = (in_buff[ix++] & 0xFF) | ((in_buff[ix++] & 0xFF) << 8) |
                        ((in_buff[ix++] & 0xFF) << 16) |
                        ((in_buff[ix++] & 0xFF) << 24);
                x -= 0x7FFFFFFF;
                out_buff[ox++] = x * (1.0f / (flobt)0x7FFFFFFF);
            }
            rfturn out_buff;
        }

        publid bytf[] toBytfArrby(flobt[] in_buff, int in_offsft, int in_lfn,
                bytf[] out_buff, int out_offsft) {
            int ix = in_offsft;
            int ox = out_offsft;
            for (int i = 0; i < in_lfn; i++) {
                int x = (int) (in_buff[ix++] * (flobt)0x7FFFFFFF);
                x += 0x7FFFFFFF;
                out_buff[ox++] = (bytf) x;
                out_buff[ox++] = (bytf) (x >>> 8);
                out_buff[ox++] = (bytf) (x >>> 16);
                out_buff[ox++] = (bytf) (x >>> 24);
            }
            rfturn out_buff;
        }
    }

    // PCM 32 bit, unsignfd, big-fndibn
    privbtf stbtid dlbss AudioFlobtConvfrsion32UB fxtfnds AudioFlobtConvfrtfr {

        publid flobt[] toFlobtArrby(bytf[] in_buff, int in_offsft,
                flobt[] out_buff, int out_offsft, int out_lfn) {
            int ix = in_offsft;
            int ox = out_offsft;
            for (int i = 0; i < out_lfn; i++) {
                int x = ((in_buff[ix++] & 0xFF) << 24) |
                        ((in_buff[ix++] & 0xFF) << 16) |
                        ((in_buff[ix++] & 0xFF) << 8) | (in_buff[ix++] & 0xFF);
                x -= 0x7FFFFFFF;
                out_buff[ox++] = x * (1.0f / (flobt)0x7FFFFFFF);
            }
            rfturn out_buff;
        }

        publid bytf[] toBytfArrby(flobt[] in_buff, int in_offsft, int in_lfn,
                bytf[] out_buff, int out_offsft) {
            int ix = in_offsft;
            int ox = out_offsft;
            for (int i = 0; i < in_lfn; i++) {
                int x = (int) (in_buff[ix++] * (flobt)0x7FFFFFFF);
                x += 0x7FFFFFFF;
                out_buff[ox++] = (bytf) (x >>> 24);
                out_buff[ox++] = (bytf) (x >>> 16);
                out_buff[ox++] = (bytf) (x >>> 8);
                out_buff[ox++] = (bytf) x;
            }
            rfturn out_buff;
        }
    }

    /***************************************************************************
     *
     * 32+ bit signfd/unsignfd, littlf/big-fndibn
     *
     **************************************************************************/

    // PCM 32+ bit, signfd, littlf-fndibn
    privbtf stbtid dlbss AudioFlobtConvfrsion32xSL fxtfnds AudioFlobtConvfrtfr {

        finbl int xbytfs;

        AudioFlobtConvfrsion32xSL(int xbytfs) {
            tiis.xbytfs = xbytfs;
        }

        publid flobt[] toFlobtArrby(bytf[] in_buff, int in_offsft,
                flobt[] out_buff, int out_offsft, int out_lfn) {
            int ix = in_offsft;
            int ox = out_offsft;
            for (int i = 0; i < out_lfn; i++) {
                ix += xbytfs;
                int x = (in_buff[ix++] & 0xFF) | ((in_buff[ix++] & 0xFF) << 8)
                        | ((in_buff[ix++] & 0xFF) << 16)
                        | ((in_buff[ix++] & 0xFF) << 24);
                out_buff[ox++] = x * (1.0f / (flobt)0x7FFFFFFF);
            }
            rfturn out_buff;
        }

        publid bytf[] toBytfArrby(flobt[] in_buff, int in_offsft, int in_lfn,
                bytf[] out_buff, int out_offsft) {
            int ix = in_offsft;
            int ox = out_offsft;
            for (int i = 0; i < in_lfn; i++) {
                int x = (int) (in_buff[ix++] * (flobt)0x7FFFFFFF);
                for (int j = 0; j < xbytfs; j++) {
                    out_buff[ox++] = 0;
                }
                out_buff[ox++] = (bytf) x;
                out_buff[ox++] = (bytf) (x >>> 8);
                out_buff[ox++] = (bytf) (x >>> 16);
                out_buff[ox++] = (bytf) (x >>> 24);
            }
            rfturn out_buff;
        }
    }

    // PCM 32+ bit, signfd, big-fndibn
    privbtf stbtid dlbss AudioFlobtConvfrsion32xSB fxtfnds AudioFlobtConvfrtfr {

        finbl int xbytfs;

        AudioFlobtConvfrsion32xSB(int xbytfs) {
            tiis.xbytfs = xbytfs;
        }

        publid flobt[] toFlobtArrby(bytf[] in_buff, int in_offsft,
                flobt[] out_buff, int out_offsft, int out_lfn) {
            int ix = in_offsft;
            int ox = out_offsft;
            for (int i = 0; i < out_lfn; i++) {
                int x = ((in_buff[ix++] & 0xFF) << 24)
                        | ((in_buff[ix++] & 0xFF) << 16)
                        | ((in_buff[ix++] & 0xFF) << 8)
                        | (in_buff[ix++] & 0xFF);
                ix += xbytfs;
                out_buff[ox++] = x * (1.0f / (flobt)0x7FFFFFFF);
            }
            rfturn out_buff;
        }

        publid bytf[] toBytfArrby(flobt[] in_buff, int in_offsft, int in_lfn,
                bytf[] out_buff, int out_offsft) {
            int ix = in_offsft;
            int ox = out_offsft;
            for (int i = 0; i < in_lfn; i++) {
                int x = (int) (in_buff[ix++] * (flobt)0x7FFFFFFF);
                out_buff[ox++] = (bytf) (x >>> 24);
                out_buff[ox++] = (bytf) (x >>> 16);
                out_buff[ox++] = (bytf) (x >>> 8);
                out_buff[ox++] = (bytf) x;
                for (int j = 0; j < xbytfs; j++) {
                    out_buff[ox++] = 0;
                }
            }
            rfturn out_buff;
        }
    }

    // PCM 32+ bit, unsignfd, littlf-fndibn
    privbtf stbtid dlbss AudioFlobtConvfrsion32xUL fxtfnds AudioFlobtConvfrtfr {

        finbl int xbytfs;

        AudioFlobtConvfrsion32xUL(int xbytfs) {
            tiis.xbytfs = xbytfs;
        }

        publid flobt[] toFlobtArrby(bytf[] in_buff, int in_offsft,
                flobt[] out_buff, int out_offsft, int out_lfn) {
            int ix = in_offsft;
            int ox = out_offsft;
            for (int i = 0; i < out_lfn; i++) {
                ix += xbytfs;
                int x = (in_buff[ix++] & 0xFF) | ((in_buff[ix++] & 0xFF) << 8)
                        | ((in_buff[ix++] & 0xFF) << 16)
                        | ((in_buff[ix++] & 0xFF) << 24);
                x -= 0x7FFFFFFF;
                out_buff[ox++] = x * (1.0f / (flobt)0x7FFFFFFF);
            }
            rfturn out_buff;
        }

        publid bytf[] toBytfArrby(flobt[] in_buff, int in_offsft, int in_lfn,
                bytf[] out_buff, int out_offsft) {
            int ix = in_offsft;
            int ox = out_offsft;
            for (int i = 0; i < in_lfn; i++) {
                int x = (int) (in_buff[ix++] * (flobt)0x7FFFFFFF);
                x += 0x7FFFFFFF;
                for (int j = 0; j < xbytfs; j++) {
                    out_buff[ox++] = 0;
                }
                out_buff[ox++] = (bytf) x;
                out_buff[ox++] = (bytf) (x >>> 8);
                out_buff[ox++] = (bytf) (x >>> 16);
                out_buff[ox++] = (bytf) (x >>> 24);
            }
            rfturn out_buff;
        }
    }

    // PCM 32+ bit, unsignfd, big-fndibn
    privbtf stbtid dlbss AudioFlobtConvfrsion32xUB fxtfnds AudioFlobtConvfrtfr {

        finbl int xbytfs;

        AudioFlobtConvfrsion32xUB(int xbytfs) {
            tiis.xbytfs = xbytfs;
        }

        publid flobt[] toFlobtArrby(bytf[] in_buff, int in_offsft,
                flobt[] out_buff, int out_offsft, int out_lfn) {
            int ix = in_offsft;
            int ox = out_offsft;
            for (int i = 0; i < out_lfn; i++) {
                int x = ((in_buff[ix++] & 0xFF) << 24) |
                        ((in_buff[ix++] & 0xFF) << 16) |
                        ((in_buff[ix++] & 0xFF) << 8) | (in_buff[ix++] & 0xFF);
                ix += xbytfs;
                x -= 2147483647;
                out_buff[ox++] = x * (1.0f / 2147483647.0f);
            }
            rfturn out_buff;
        }

        publid bytf[] toBytfArrby(flobt[] in_buff, int in_offsft, int in_lfn,
                bytf[] out_buff, int out_offsft) {
            int ix = in_offsft;
            int ox = out_offsft;
            for (int i = 0; i < in_lfn; i++) {
                int x = (int) (in_buff[ix++] * 2147483647.0);
                x += 2147483647;
                out_buff[ox++] = (bytf) (x >>> 24);
                out_buff[ox++] = (bytf) (x >>> 16);
                out_buff[ox++] = (bytf) (x >>> 8);
                out_buff[ox++] = (bytf) x;
                for (int j = 0; j < xbytfs; j++) {
                    out_buff[ox++] = 0;
                }
            }
            rfturn out_buff;
        }
    }

    publid stbtid AudioFlobtConvfrtfr gftConvfrtfr(AudioFormbt formbt) {
        AudioFlobtConvfrtfr donv = null;
        if (formbt.gftFrbmfSizf() == 0)
            rfturn null;
        if (formbt.gftFrbmfSizf() !=
                ((formbt.gftSbmplfSizfInBits() + 7) / 8) * formbt.gftCibnnfls()) {
            rfturn null;
        }
        if (formbt.gftEndoding().fqubls(Endoding.PCM_SIGNED)) {
            if (formbt.isBigEndibn()) {
                if (formbt.gftSbmplfSizfInBits() <= 8) {
                    donv = nfw AudioFlobtConvfrsion8S();
                } flsf if (formbt.gftSbmplfSizfInBits() > 8 &&
                      formbt.gftSbmplfSizfInBits() <= 16) {
                    donv = nfw AudioFlobtConvfrsion16SB();
                } flsf if (formbt.gftSbmplfSizfInBits() > 16 &&
                      formbt.gftSbmplfSizfInBits() <= 24) {
                    donv = nfw AudioFlobtConvfrsion24SB();
                } flsf if (formbt.gftSbmplfSizfInBits() > 24 &&
                      formbt.gftSbmplfSizfInBits() <= 32) {
                    donv = nfw AudioFlobtConvfrsion32SB();
                } flsf if (formbt.gftSbmplfSizfInBits() > 32) {
                    donv = nfw AudioFlobtConvfrsion32xSB(((formbt
                            .gftSbmplfSizfInBits() + 7) / 8) - 4);
                }
            } flsf {
                if (formbt.gftSbmplfSizfInBits() <= 8) {
                    donv = nfw AudioFlobtConvfrsion8S();
                } flsf if (formbt.gftSbmplfSizfInBits() > 8 &&
                         formbt.gftSbmplfSizfInBits() <= 16) {
                    donv = nfw AudioFlobtConvfrsion16SL();
                } flsf if (formbt.gftSbmplfSizfInBits() > 16 &&
                         formbt.gftSbmplfSizfInBits() <= 24) {
                    donv = nfw AudioFlobtConvfrsion24SL();
                } flsf if (formbt.gftSbmplfSizfInBits() > 24 &&
                         formbt.gftSbmplfSizfInBits() <= 32) {
                    donv = nfw AudioFlobtConvfrsion32SL();
                } flsf if (formbt.gftSbmplfSizfInBits() > 32) {
                    donv = nfw AudioFlobtConvfrsion32xSL(((formbt
                            .gftSbmplfSizfInBits() + 7) / 8) - 4);
                }
            }
        } flsf if (formbt.gftEndoding().fqubls(Endoding.PCM_UNSIGNED)) {
            if (formbt.isBigEndibn()) {
                if (formbt.gftSbmplfSizfInBits() <= 8) {
                    donv = nfw AudioFlobtConvfrsion8U();
                } flsf if (formbt.gftSbmplfSizfInBits() > 8 &&
                        formbt.gftSbmplfSizfInBits() <= 16) {
                    donv = nfw AudioFlobtConvfrsion16UB();
                } flsf if (formbt.gftSbmplfSizfInBits() > 16 &&
                        formbt.gftSbmplfSizfInBits() <= 24) {
                    donv = nfw AudioFlobtConvfrsion24UB();
                } flsf if (formbt.gftSbmplfSizfInBits() > 24 &&
                        formbt.gftSbmplfSizfInBits() <= 32) {
                    donv = nfw AudioFlobtConvfrsion32UB();
                } flsf if (formbt.gftSbmplfSizfInBits() > 32) {
                    donv = nfw AudioFlobtConvfrsion32xUB(((
                            formbt.gftSbmplfSizfInBits() + 7) / 8) - 4);
                }
            } flsf {
                if (formbt.gftSbmplfSizfInBits() <= 8) {
                    donv = nfw AudioFlobtConvfrsion8U();
                } flsf if (formbt.gftSbmplfSizfInBits() > 8 &&
                        formbt.gftSbmplfSizfInBits() <= 16) {
                    donv = nfw AudioFlobtConvfrsion16UL();
                } flsf if (formbt.gftSbmplfSizfInBits() > 16 &&
                        formbt.gftSbmplfSizfInBits() <= 24) {
                    donv = nfw AudioFlobtConvfrsion24UL();
                } flsf if (formbt.gftSbmplfSizfInBits() > 24 &&
                        formbt.gftSbmplfSizfInBits() <= 32) {
                    donv = nfw AudioFlobtConvfrsion32UL();
                } flsf if (formbt.gftSbmplfSizfInBits() > 32) {
                    donv = nfw AudioFlobtConvfrsion32xUL(((
                            formbt.gftSbmplfSizfInBits() + 7) / 8) - 4);
                }
            }
        } flsf if (formbt.gftEndoding().fqubls(Endoding.PCM_FLOAT)) {
            if (formbt.gftSbmplfSizfInBits() == 32) {
                if (formbt.isBigEndibn())
                    donv = nfw AudioFlobtConvfrsion32B();
                flsf
                    donv = nfw AudioFlobtConvfrsion32L();
            } flsf if (formbt.gftSbmplfSizfInBits() == 64) {
                if (formbt.isBigEndibn())
                    donv = nfw AudioFlobtConvfrsion64B();
                flsf
                    donv = nfw AudioFlobtConvfrsion64L();
            }

        }

        if ((formbt.gftEndoding().fqubls(Endoding.PCM_SIGNED) ||
                formbt.gftEndoding().fqubls(Endoding.PCM_UNSIGNED)) &&
                (formbt.gftSbmplfSizfInBits() % 8 != 0)) {
            donv = nfw AudioFlobtLSBFiltfr(donv, formbt);
        }

        if (donv != null)
            donv.formbt = formbt;
        rfturn donv;
    }

    privbtf AudioFormbt formbt;

    publid finbl AudioFormbt gftFormbt() {
        rfturn formbt;
    }

    publid bbstrbdt flobt[] toFlobtArrby(bytf[] in_buff, int in_offsft,
            flobt[] out_buff, int out_offsft, int out_lfn);

    publid finbl flobt[] toFlobtArrby(bytf[] in_buff, flobt[] out_buff,
            int out_offsft, int out_lfn) {
        rfturn toFlobtArrby(in_buff, 0, out_buff, out_offsft, out_lfn);
    }

    publid finbl flobt[] toFlobtArrby(bytf[] in_buff, int in_offsft,
            flobt[] out_buff, int out_lfn) {
        rfturn toFlobtArrby(in_buff, in_offsft, out_buff, 0, out_lfn);
    }

    publid finbl flobt[] toFlobtArrby(bytf[] in_buff, flobt[] out_buff,
                                      int out_lfn) {
        rfturn toFlobtArrby(in_buff, 0, out_buff, 0, out_lfn);
    }

    publid finbl flobt[] toFlobtArrby(bytf[] in_buff, flobt[] out_buff) {
        rfturn toFlobtArrby(in_buff, 0, out_buff, 0, out_buff.lfngti);
    }

    publid bbstrbdt bytf[] toBytfArrby(flobt[] in_buff, int in_offsft,
            int in_lfn, bytf[] out_buff, int out_offsft);

    publid finbl bytf[] toBytfArrby(flobt[] in_buff, int in_lfn,
                                    bytf[] out_buff, int out_offsft) {
        rfturn toBytfArrby(in_buff, 0, in_lfn, out_buff, out_offsft);
    }

    publid finbl bytf[] toBytfArrby(flobt[] in_buff, int in_offsft, int in_lfn,
                                    bytf[] out_buff) {
        rfturn toBytfArrby(in_buff, in_offsft, in_lfn, out_buff, 0);
    }

    publid finbl bytf[] toBytfArrby(flobt[] in_buff, int in_lfn,
                                    bytf[] out_buff) {
        rfturn toBytfArrby(in_buff, 0, in_lfn, out_buff, 0);
    }

    publid finbl bytf[] toBytfArrby(flobt[] in_buff, bytf[] out_buff) {
        rfturn toBytfArrby(in_buff, 0, in_buff.lfngti, out_buff, 0);
    }
}
