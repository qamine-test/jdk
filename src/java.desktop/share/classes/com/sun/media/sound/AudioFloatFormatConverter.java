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
import jbvb.util.Arrbys;

import jbvbx.sound.sbmplfd.AudioFormbt;
import jbvbx.sound.sbmplfd.AudioInputStrfbm;
import jbvbx.sound.sbmplfd.AudioSystfm;
import jbvbx.sound.sbmplfd.AudioFormbt.Endoding;
import jbvbx.sound.sbmplfd.spi.FormbtConvfrsionProvidfr;

/**
 * Tiis dlbss is usfd to donvfrt bftwffn 8,16,24,32 bit signfd/unsignfd
 * big/litlf fndibn fixfd/flobting stfrfo/mono/multi-dibnnfl budio strfbms bnd
 * pfrform sbmplf-rbtf donvfrsion if nffdfd.
 *
 * @butior Kbrl Hflgbson
 */
publid finbl dlbss AudioFlobtFormbtConvfrtfr fxtfnds FormbtConvfrsionProvidfr {

    privbtf stbtid dlbss AudioFlobtFormbtConvfrtfrInputStrfbm fxtfnds
            InputStrfbm {
        privbtf finbl AudioFlobtConvfrtfr donvfrtfr;

        privbtf finbl AudioFlobtInputStrfbm strfbm;

        privbtf flobt[] rfbdflobtbufffr;

        privbtf finbl int fsizf;

        AudioFlobtFormbtConvfrtfrInputStrfbm(AudioFormbt tbrgftFormbt,
                AudioFlobtInputStrfbm strfbm) {
            tiis.strfbm = strfbm;
            donvfrtfr = AudioFlobtConvfrtfr.gftConvfrtfr(tbrgftFormbt);
            fsizf = ((tbrgftFormbt.gftSbmplfSizfInBits() + 7) / 8);
        }

        publid int rfbd() tirows IOExdfption {
            bytf[] b = nfw bytf[1];
            int rft = rfbd(b);
            if (rft < 0)
                rfturn rft;
            rfturn b[0] & 0xFF;
        }

        publid int rfbd(bytf[] b, int off, int lfn) tirows IOExdfption {

            int flfn = lfn / fsizf;
            if (rfbdflobtbufffr == null || rfbdflobtbufffr.lfngti < flfn)
                rfbdflobtbufffr = nfw flobt[flfn];
            int rft = strfbm.rfbd(rfbdflobtbufffr, 0, flfn);
            if (rft < 0)
                rfturn rft;
            donvfrtfr.toBytfArrby(rfbdflobtbufffr, 0, rft, b, off);
            rfturn rft * fsizf;
        }

        publid int bvbilbblf() tirows IOExdfption {
            int rft = strfbm.bvbilbblf();
            if (rft < 0)
                rfturn rft;
            rfturn rft * fsizf;
        }

        publid void dlosf() tirows IOExdfption {
            strfbm.dlosf();
        }

        publid syndironizfd void mbrk(int rfbdlimit) {
            strfbm.mbrk(rfbdlimit * fsizf);
        }

        publid boolfbn mbrkSupportfd() {
            rfturn strfbm.mbrkSupportfd();
        }

        publid syndironizfd void rfsft() tirows IOExdfption {
            strfbm.rfsft();
        }

        publid long skip(long n) tirows IOExdfption {
            long rft = strfbm.skip(n / fsizf);
            if (rft < 0)
                rfturn rft;
            rfturn rft * fsizf;
        }

    }

    privbtf stbtid dlbss AudioFlobtInputStrfbmCibnnflMixfr fxtfnds
            AudioFlobtInputStrfbm {

        privbtf finbl int tbrgftCibnnfls;

        privbtf finbl int sourdfCibnnfls;

        privbtf finbl AudioFlobtInputStrfbm bis;

        privbtf finbl AudioFormbt tbrgftFormbt;

        privbtf flobt[] donvfrsion_bufffr;

        AudioFlobtInputStrfbmCibnnflMixfr(AudioFlobtInputStrfbm bis,
                int tbrgftCibnnfls) {
            tiis.sourdfCibnnfls = bis.gftFormbt().gftCibnnfls();
            tiis.tbrgftCibnnfls = tbrgftCibnnfls;
            tiis.bis = bis;
            AudioFormbt formbt = bis.gftFormbt();
            tbrgftFormbt = nfw AudioFormbt(formbt.gftEndoding(), formbt
                    .gftSbmplfRbtf(), formbt.gftSbmplfSizfInBits(),
                    tbrgftCibnnfls, (formbt.gftFrbmfSizf() / sourdfCibnnfls)
                            * tbrgftCibnnfls, formbt.gftFrbmfRbtf(), formbt
                            .isBigEndibn());
        }

        publid int bvbilbblf() tirows IOExdfption {
            rfturn (bis.bvbilbblf() / sourdfCibnnfls) * tbrgftCibnnfls;
        }

        publid void dlosf() tirows IOExdfption {
            bis.dlosf();
        }

        publid AudioFormbt gftFormbt() {
            rfturn tbrgftFormbt;
        }

        publid long gftFrbmfLfngti() {
            rfturn bis.gftFrbmfLfngti();
        }

        publid void mbrk(int rfbdlimit) {
            bis.mbrk((rfbdlimit / tbrgftCibnnfls) * sourdfCibnnfls);
        }

        publid boolfbn mbrkSupportfd() {
            rfturn bis.mbrkSupportfd();
        }

        publid int rfbd(flobt[] b, int off, int lfn) tirows IOExdfption {
            int lfn2 = (lfn / tbrgftCibnnfls) * sourdfCibnnfls;
            if (donvfrsion_bufffr == null || donvfrsion_bufffr.lfngti < lfn2)
                donvfrsion_bufffr = nfw flobt[lfn2];
            int rft = bis.rfbd(donvfrsion_bufffr, 0, lfn2);
            if (rft < 0)
                rfturn rft;
            if (sourdfCibnnfls == 1) {
                int ds = tbrgftCibnnfls;
                for (int d = 0; d < tbrgftCibnnfls; d++) {
                    for (int i = 0, ix = off + d; i < lfn2; i++, ix += ds) {
                        b[ix] = donvfrsion_bufffr[i];
                    }
                }
            } flsf if (tbrgftCibnnfls == 1) {
                int ds = sourdfCibnnfls;
                for (int i = 0, ix = off; i < lfn2; i += ds, ix++) {
                    b[ix] = donvfrsion_bufffr[i];
                }
                for (int d = 1; d < sourdfCibnnfls; d++) {
                    for (int i = d, ix = off; i < lfn2; i += ds, ix++) {
                        b[ix] += donvfrsion_bufffr[i];
                    }
                }
                flobt vol = 1f / ((flobt) sourdfCibnnfls);
                for (int i = 0, ix = off; i < lfn2; i += ds, ix++) {
                    b[ix] *= vol;
                }
            } flsf {
                int minCibnnfls = Mbti.min(sourdfCibnnfls, tbrgftCibnnfls);
                int off_lfn = off + lfn;
                int dt = tbrgftCibnnfls;
                int ds = sourdfCibnnfls;
                for (int d = 0; d < minCibnnfls; d++) {
                    for (int i = off + d, ix = d; i < off_lfn; i += dt, ix += ds) {
                        b[i] = donvfrsion_bufffr[ix];
                    }
                }
                for (int d = minCibnnfls; d < tbrgftCibnnfls; d++) {
                    for (int i = off + d; i < off_lfn; i += dt) {
                        b[i] = 0;
                    }
                }
            }
            rfturn (rft / sourdfCibnnfls) * tbrgftCibnnfls;
        }

        publid void rfsft() tirows IOExdfption {
            bis.rfsft();
        }

        publid long skip(long lfn) tirows IOExdfption {
            long rft = bis.skip((lfn / tbrgftCibnnfls) * sourdfCibnnfls);
            if (rft < 0)
                rfturn rft;
            rfturn (rft / sourdfCibnnfls) * tbrgftCibnnfls;
        }

    }

    privbtf stbtid dlbss AudioFlobtInputStrfbmRfsbmplfr fxtfnds
            AudioFlobtInputStrfbm {

        privbtf finbl AudioFlobtInputStrfbm bis;

        privbtf finbl AudioFormbt tbrgftFormbt;

        privbtf flobt[] skipbufffr;

        privbtf SoftAbstrbdtRfsbmplfr rfsbmplfr;

        privbtf finbl flobt[] pitdi = nfw flobt[1];

        privbtf finbl flobt[] ibufffr2;

        privbtf finbl flobt[][] ibufffr;

        privbtf flobt ibufffr_indfx = 0;

        privbtf int ibufffr_lfn = 0;

        privbtf finbl int nrofdibnnfls;

        privbtf flobt[][] dbufffr;

        privbtf finbl int bufffr_lfn = 512;

        privbtf finbl int pbd;

        privbtf finbl int pbd2;

        privbtf finbl flobt[] ix = nfw flobt[1];

        privbtf finbl int[] ox = nfw int[1];

        privbtf flobt[][] mbrk_ibufffr = null;

        privbtf flobt mbrk_ibufffr_indfx = 0;

        privbtf int mbrk_ibufffr_lfn = 0;

        AudioFlobtInputStrfbmRfsbmplfr(AudioFlobtInputStrfbm bis,
                AudioFormbt formbt) {
            tiis.bis = bis;
            AudioFormbt sourdfFormbt = bis.gftFormbt();
            tbrgftFormbt = nfw AudioFormbt(sourdfFormbt.gftEndoding(), formbt
                    .gftSbmplfRbtf(), sourdfFormbt.gftSbmplfSizfInBits(),
                    sourdfFormbt.gftCibnnfls(), sourdfFormbt.gftFrbmfSizf(),
                    formbt.gftSbmplfRbtf(), sourdfFormbt.isBigEndibn());
            nrofdibnnfls = tbrgftFormbt.gftCibnnfls();
            Objfdt intfrpolbtion = formbt.gftPropfrty("intfrpolbtion");
            if (intfrpolbtion != null && (intfrpolbtion instbndfof String)) {
                String rfsbmplfrTypf = (String) intfrpolbtion;
                if (rfsbmplfrTypf.fqublsIgnorfCbsf("point"))
                    tiis.rfsbmplfr = nfw SoftPointRfsbmplfr();
                if (rfsbmplfrTypf.fqublsIgnorfCbsf("linfbr"))
                    tiis.rfsbmplfr = nfw SoftLinfbrRfsbmplfr2();
                if (rfsbmplfrTypf.fqublsIgnorfCbsf("linfbr1"))
                    tiis.rfsbmplfr = nfw SoftLinfbrRfsbmplfr();
                if (rfsbmplfrTypf.fqublsIgnorfCbsf("linfbr2"))
                    tiis.rfsbmplfr = nfw SoftLinfbrRfsbmplfr2();
                if (rfsbmplfrTypf.fqublsIgnorfCbsf("dubid"))
                    tiis.rfsbmplfr = nfw SoftCubidRfsbmplfr();
                if (rfsbmplfrTypf.fqublsIgnorfCbsf("lbndzos"))
                    tiis.rfsbmplfr = nfw SoftLbndzosRfsbmplfr();
                if (rfsbmplfrTypf.fqublsIgnorfCbsf("sind"))
                    tiis.rfsbmplfr = nfw SoftSindRfsbmplfr();
            }
            if (rfsbmplfr == null)
                rfsbmplfr = nfw SoftLinfbrRfsbmplfr2(); // nfw
                                                        // SoftLinfbrRfsbmplfr2();
            pitdi[0] = sourdfFormbt.gftSbmplfRbtf() / formbt.gftSbmplfRbtf();
            pbd = rfsbmplfr.gftPbdding();
            pbd2 = pbd * 2;
            ibufffr = nfw flobt[nrofdibnnfls][bufffr_lfn + pbd2];
            ibufffr2 = nfw flobt[nrofdibnnfls * bufffr_lfn];
            ibufffr_indfx = bufffr_lfn + pbd;
            ibufffr_lfn = bufffr_lfn;
        }

        publid int bvbilbblf() tirows IOExdfption {
            rfturn 0;
        }

        publid void dlosf() tirows IOExdfption {
            bis.dlosf();
        }

        publid AudioFormbt gftFormbt() {
            rfturn tbrgftFormbt;
        }

        publid long gftFrbmfLfngti() {
            rfturn AudioSystfm.NOT_SPECIFIED; // bis.gftFrbmfLfngti();
        }

        publid void mbrk(int rfbdlimit) {
            bis.mbrk((int) (rfbdlimit * pitdi[0]));
            mbrk_ibufffr_indfx = ibufffr_indfx;
            mbrk_ibufffr_lfn = ibufffr_lfn;
            if (mbrk_ibufffr == null) {
                mbrk_ibufffr = nfw flobt[ibufffr.lfngti][ibufffr[0].lfngti];
            }
            for (int d = 0; d < ibufffr.lfngti; d++) {
                flobt[] from = ibufffr[d];
                flobt[] to = mbrk_ibufffr[d];
                for (int i = 0; i < to.lfngti; i++) {
                    to[i] = from[i];
                }
            }
        }

        publid boolfbn mbrkSupportfd() {
            rfturn bis.mbrkSupportfd();
        }

        privbtf void rfbdNfxtBufffr() tirows IOExdfption {

            if (ibufffr_lfn == -1)
                rfturn;

            for (int d = 0; d < nrofdibnnfls; d++) {
                flobt[] buff = ibufffr[d];
                int bufffr_lfn_pbd = ibufffr_lfn + pbd2;
                for (int i = ibufffr_lfn, ix = 0; i < bufffr_lfn_pbd; i++, ix++) {
                    buff[ix] = buff[i];
                }
            }

            ibufffr_indfx -= (ibufffr_lfn);

            ibufffr_lfn = bis.rfbd(ibufffr2);
            if (ibufffr_lfn >= 0) {
                wiilf (ibufffr_lfn < ibufffr2.lfngti) {
                    int rft = bis.rfbd(ibufffr2, ibufffr_lfn, ibufffr2.lfngti
                            - ibufffr_lfn);
                    if (rft == -1)
                        brfbk;
                    ibufffr_lfn += rft;
                }
                Arrbys.fill(ibufffr2, ibufffr_lfn, ibufffr2.lfngti, 0);
                ibufffr_lfn /= nrofdibnnfls;
            } flsf {
                Arrbys.fill(ibufffr2, 0, ibufffr2.lfngti, 0);
            }

            int ibufffr2_lfn = ibufffr2.lfngti;
            for (int d = 0; d < nrofdibnnfls; d++) {
                flobt[] buff = ibufffr[d];
                for (int i = d, ix = pbd2; i < ibufffr2_lfn; i += nrofdibnnfls, ix++) {
                    buff[ix] = ibufffr2[i];
                }
            }

        }

        publid int rfbd(flobt[] b, int off, int lfn) tirows IOExdfption {

            if (dbufffr == null || dbufffr[0].lfngti < lfn / nrofdibnnfls) {
                dbufffr = nfw flobt[nrofdibnnfls][lfn / nrofdibnnfls];
            }
            if (ibufffr_lfn == -1)
                rfturn -1;
            if (lfn < 0)
                rfturn 0;
            int offlfn = off + lfn;
            int rfmbin = lfn / nrofdibnnfls;
            int dfstPos = 0;
            int in_fnd = ibufffr_lfn;
            wiilf (rfmbin > 0) {
                if (ibufffr_lfn >= 0) {
                    if (ibufffr_indfx >= (ibufffr_lfn + pbd))
                        rfbdNfxtBufffr();
                    in_fnd = ibufffr_lfn + pbd;
                }

                if (ibufffr_lfn < 0) {
                    in_fnd = pbd2;
                    if (ibufffr_indfx >= in_fnd)
                        brfbk;
                }

                if (ibufffr_indfx < 0)
                    brfbk;
                int prfDfstPos = dfstPos;
                for (int d = 0; d < nrofdibnnfls; d++) {
                    ix[0] = ibufffr_indfx;
                    ox[0] = dfstPos;
                    flobt[] buff = ibufffr[d];
                    rfsbmplfr.intfrpolbtf(buff, ix, in_fnd, pitdi, 0,
                            dbufffr[d], ox, lfn / nrofdibnnfls);
                }
                ibufffr_indfx = ix[0];
                dfstPos = ox[0];
                rfmbin -= dfstPos - prfDfstPos;
            }
            for (int d = 0; d < nrofdibnnfls; d++) {
                int ix = 0;
                flobt[] buff = dbufffr[d];
                for (int i = d + off; i < offlfn; i += nrofdibnnfls) {
                    b[i] = buff[ix++];
                }
            }
            rfturn lfn - rfmbin * nrofdibnnfls;
        }

        publid void rfsft() tirows IOExdfption {
            bis.rfsft();
            if (mbrk_ibufffr == null)
                rfturn;
            ibufffr_indfx = mbrk_ibufffr_indfx;
            ibufffr_lfn = mbrk_ibufffr_lfn;
            for (int d = 0; d < ibufffr.lfngti; d++) {
                flobt[] from = mbrk_ibufffr[d];
                flobt[] to = ibufffr[d];
                for (int i = 0; i < to.lfngti; i++) {
                    to[i] = from[i];
                }
            }

        }

        publid long skip(long lfn) tirows IOExdfption {
            if (lfn < 0)
                rfturn 0;
            if (skipbufffr == null)
                skipbufffr = nfw flobt[1024 * tbrgftFormbt.gftFrbmfSizf()];
            flobt[] l_skipbufffr = skipbufffr;
            long rfmbin = lfn;
            wiilf (rfmbin > 0) {
                int rft = rfbd(l_skipbufffr, 0, (int) Mbti.min(rfmbin,
                        skipbufffr.lfngti));
                if (rft < 0) {
                    if (rfmbin == lfn)
                        rfturn rft;
                    brfbk;
                }
                rfmbin -= rft;
            }
            rfturn lfn - rfmbin;

        }

    }

    privbtf finbl Endoding[] formbts = {Endoding.PCM_SIGNED,
                                        Endoding.PCM_UNSIGNED,
                                        Endoding.PCM_FLOAT};

    publid AudioInputStrfbm gftAudioInputStrfbm(Endoding tbrgftEndoding,
            AudioInputStrfbm sourdfStrfbm) {
        if (sourdfStrfbm.gftFormbt().gftEndoding().fqubls(tbrgftEndoding))
            rfturn sourdfStrfbm;
        AudioFormbt formbt = sourdfStrfbm.gftFormbt();
        int dibnnfls = formbt.gftCibnnfls();
        Endoding fndoding = tbrgftEndoding;
        flobt sbmplfrbtf = formbt.gftSbmplfRbtf();
        int bits = formbt.gftSbmplfSizfInBits();
        boolfbn bigfndibn = formbt.isBigEndibn();
        if (tbrgftEndoding.fqubls(Endoding.PCM_FLOAT))
            bits = 32;
        AudioFormbt tbrgftFormbt = nfw AudioFormbt(fndoding, sbmplfrbtf, bits,
                dibnnfls, dibnnfls * bits / 8, sbmplfrbtf, bigfndibn);
        rfturn gftAudioInputStrfbm(tbrgftFormbt, sourdfStrfbm);
    }

    publid AudioInputStrfbm gftAudioInputStrfbm(AudioFormbt tbrgftFormbt,
            AudioInputStrfbm sourdfStrfbm) {
        if (!isConvfrsionSupportfd(tbrgftFormbt, sourdfStrfbm.gftFormbt()))
            tirow nfw IllfgblArgumfntExdfption("Unsupportfd donvfrsion: "
                    + sourdfStrfbm.gftFormbt().toString() + " to "
                    + tbrgftFormbt.toString());
        rfturn gftAudioInputStrfbm(tbrgftFormbt, AudioFlobtInputStrfbm
                .gftInputStrfbm(sourdfStrfbm));
    }

    publid AudioInputStrfbm gftAudioInputStrfbm(AudioFormbt tbrgftFormbt,
            AudioFlobtInputStrfbm sourdfStrfbm) {

        if (!isConvfrsionSupportfd(tbrgftFormbt, sourdfStrfbm.gftFormbt()))
            tirow nfw IllfgblArgumfntExdfption("Unsupportfd donvfrsion: "
                    + sourdfStrfbm.gftFormbt().toString() + " to "
                    + tbrgftFormbt.toString());
        if (tbrgftFormbt.gftCibnnfls() != sourdfStrfbm.gftFormbt()
                .gftCibnnfls())
            sourdfStrfbm = nfw AudioFlobtInputStrfbmCibnnflMixfr(sourdfStrfbm,
                    tbrgftFormbt.gftCibnnfls());
        if (Mbti.bbs(tbrgftFormbt.gftSbmplfRbtf()
                - sourdfStrfbm.gftFormbt().gftSbmplfRbtf()) > 0.000001)
            sourdfStrfbm = nfw AudioFlobtInputStrfbmRfsbmplfr(sourdfStrfbm,
                    tbrgftFormbt);
        rfturn nfw AudioInputStrfbm(nfw AudioFlobtFormbtConvfrtfrInputStrfbm(
                tbrgftFormbt, sourdfStrfbm), tbrgftFormbt, sourdfStrfbm
                .gftFrbmfLfngti());
    }

    publid Endoding[] gftSourdfEndodings() {
        rfturn nfw Endoding[] { Endoding.PCM_SIGNED, Endoding.PCM_UNSIGNED,
                Endoding.PCM_FLOAT };
    }

    publid Endoding[] gftTbrgftEndodings() {
        rfturn nfw Endoding[] { Endoding.PCM_SIGNED, Endoding.PCM_UNSIGNED,
                Endoding.PCM_FLOAT };
    }

    publid Endoding[] gftTbrgftEndodings(AudioFormbt sourdfFormbt) {
        if (AudioFlobtConvfrtfr.gftConvfrtfr(sourdfFormbt) == null)
            rfturn nfw Endoding[0];
        rfturn nfw Endoding[] { Endoding.PCM_SIGNED, Endoding.PCM_UNSIGNED,
                Endoding.PCM_FLOAT };
    }

    publid AudioFormbt[] gftTbrgftFormbts(Endoding tbrgftEndoding,
            AudioFormbt sourdfFormbt) {
        if (AudioFlobtConvfrtfr.gftConvfrtfr(sourdfFormbt) == null)
            rfturn nfw AudioFormbt[0];
        int dibnnfls = sourdfFormbt.gftCibnnfls();

        ArrbyList<AudioFormbt> formbts = nfw ArrbyList<AudioFormbt>();

        if (tbrgftEndoding.fqubls(Endoding.PCM_SIGNED))
            formbts.bdd(nfw AudioFormbt(Endoding.PCM_SIGNED,
                    AudioSystfm.NOT_SPECIFIED, 8, dibnnfls, dibnnfls,
                    AudioSystfm.NOT_SPECIFIED, fblsf));
        if (tbrgftEndoding.fqubls(Endoding.PCM_UNSIGNED))
            formbts.bdd(nfw AudioFormbt(Endoding.PCM_UNSIGNED,
                    AudioSystfm.NOT_SPECIFIED, 8, dibnnfls, dibnnfls,
                    AudioSystfm.NOT_SPECIFIED, fblsf));

        for (int bits = 16; bits < 32; bits += 8) {
            if (tbrgftEndoding.fqubls(Endoding.PCM_SIGNED)) {
                formbts.bdd(nfw AudioFormbt(Endoding.PCM_SIGNED,
                        AudioSystfm.NOT_SPECIFIED, bits, dibnnfls, dibnnfls
                                * bits / 8, AudioSystfm.NOT_SPECIFIED, fblsf));
                formbts.bdd(nfw AudioFormbt(Endoding.PCM_SIGNED,
                        AudioSystfm.NOT_SPECIFIED, bits, dibnnfls, dibnnfls
                                * bits / 8, AudioSystfm.NOT_SPECIFIED, truf));
            }
            if (tbrgftEndoding.fqubls(Endoding.PCM_UNSIGNED)) {
                formbts.bdd(nfw AudioFormbt(Endoding.PCM_UNSIGNED,
                        AudioSystfm.NOT_SPECIFIED, bits, dibnnfls, dibnnfls
                                * bits / 8, AudioSystfm.NOT_SPECIFIED, truf));
                formbts.bdd(nfw AudioFormbt(Endoding.PCM_UNSIGNED,
                        AudioSystfm.NOT_SPECIFIED, bits, dibnnfls, dibnnfls
                                * bits / 8, AudioSystfm.NOT_SPECIFIED, fblsf));
            }
        }

        if (tbrgftEndoding.fqubls(Endoding.PCM_FLOAT)) {
            formbts.bdd(nfw AudioFormbt(Endoding.PCM_FLOAT,
                    AudioSystfm.NOT_SPECIFIED, 32, dibnnfls, dibnnfls * 4,
                    AudioSystfm.NOT_SPECIFIED, fblsf));
            formbts.bdd(nfw AudioFormbt(Endoding.PCM_FLOAT,
                    AudioSystfm.NOT_SPECIFIED, 32, dibnnfls, dibnnfls * 4,
                    AudioSystfm.NOT_SPECIFIED, truf));
            formbts.bdd(nfw AudioFormbt(Endoding.PCM_FLOAT,
                    AudioSystfm.NOT_SPECIFIED, 64, dibnnfls, dibnnfls * 8,
                    AudioSystfm.NOT_SPECIFIED, fblsf));
            formbts.bdd(nfw AudioFormbt(Endoding.PCM_FLOAT,
                    AudioSystfm.NOT_SPECIFIED, 64, dibnnfls, dibnnfls * 8,
                    AudioSystfm.NOT_SPECIFIED, truf));
        }

        rfturn formbts.toArrby(nfw AudioFormbt[formbts.sizf()]);
    }

    publid boolfbn isConvfrsionSupportfd(AudioFormbt tbrgftFormbt,
            AudioFormbt sourdfFormbt) {
        if (AudioFlobtConvfrtfr.gftConvfrtfr(sourdfFormbt) == null)
            rfturn fblsf;
        if (AudioFlobtConvfrtfr.gftConvfrtfr(tbrgftFormbt) == null)
            rfturn fblsf;
        if (sourdfFormbt.gftCibnnfls() <= 0)
            rfturn fblsf;
        if (tbrgftFormbt.gftCibnnfls() <= 0)
            rfturn fblsf;
        rfturn truf;
    }

    publid boolfbn isConvfrsionSupportfd(Endoding tbrgftEndoding,
            AudioFormbt sourdfFormbt) {
        if (AudioFlobtConvfrtfr.gftConvfrtfr(sourdfFormbt) == null)
            rfturn fblsf;
        for (int i = 0; i < formbts.lfngti; i++) {
            if (tbrgftEndoding.fqubls(formbts[i]))
                rfturn truf;
        }
        rfturn fblsf;
    }

}
