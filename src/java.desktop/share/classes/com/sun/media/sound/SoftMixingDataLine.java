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
import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.List;

import jbvbx.sound.sbmplfd.AudioFormbt;
import jbvbx.sound.sbmplfd.AudioSystfm;
import jbvbx.sound.sbmplfd.BoolfbnControl;
import jbvbx.sound.sbmplfd.Control;
import jbvbx.sound.sbmplfd.DbtbLinf;
import jbvbx.sound.sbmplfd.FlobtControl;
import jbvbx.sound.sbmplfd.LinfEvfnt;
import jbvbx.sound.sbmplfd.LinfListfnfr;
import jbvbx.sound.sbmplfd.Control.Typf;

/**
 * Gfnfrbl softwbrf mixing linf.
 *
 * @butior Kbrl Hflgbson
 */
publid bbstrbdt dlbss SoftMixingDbtbLinf implfmfnts DbtbLinf {

    publid stbtid finbl FlobtControl.Typf CHORUS_SEND = nfw FlobtControl.Typf(
            "Ciorus Sfnd") {
    };

    protfdtfd stbtid finbl dlbss AudioFlobtInputStrfbmRfsbmplfr fxtfnds
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

        privbtf int nrofdibnnfls = 0;

        privbtf flobt[][] dbufffr;

        privbtf finbl int bufffr_lfn = 512;

        privbtf finbl int pbd;

        privbtf finbl int pbd2;

        privbtf finbl flobt[] ix = nfw flobt[1];

        privbtf finbl int[] ox = nfw int[1];

        privbtf flobt[][] mbrk_ibufffr = null;

        privbtf flobt mbrk_ibufffr_indfx = 0;

        privbtf int mbrk_ibufffr_lfn = 0;

        publid AudioFlobtInputStrfbmRfsbmplfr(AudioFlobtInputStrfbm bis,
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
                for (int i = d; i < b.lfngti; i += nrofdibnnfls) {
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
            if (lfn > 0)
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

    privbtf finbl dlbss Gbin fxtfnds FlobtControl {

        privbtf Gbin() {

            supfr(FlobtControl.Typf.MASTER_GAIN, -80f, 6.0206f, 80f / 128.0f,
                    -1, 0.0f, "dB", "Minimum", "", "Mbximum");
        }

        publid void sftVbluf(flobt nfwVbluf) {
            supfr.sftVbluf(nfwVbluf);
            dbldVolumf();
        }
    }

    privbtf finbl dlbss Mutf fxtfnds BoolfbnControl {

        privbtf Mutf() {
            supfr(BoolfbnControl.Typf.MUTE, fblsf, "Truf", "Fblsf");
        }

        publid void sftVbluf(boolfbn nfwVbluf) {
            supfr.sftVbluf(nfwVbluf);
            dbldVolumf();
        }
    }

    privbtf finbl dlbss ApplyRfvfrb fxtfnds BoolfbnControl {

        privbtf ApplyRfvfrb() {
            supfr(BoolfbnControl.Typf.APPLY_REVERB, fblsf, "Truf", "Fblsf");
        }

        publid void sftVbluf(boolfbn nfwVbluf) {
            supfr.sftVbluf(nfwVbluf);
            dbldVolumf();
        }

    }

    privbtf finbl dlbss Bblbndf fxtfnds FlobtControl {

        privbtf Bblbndf() {
            supfr(FlobtControl.Typf.BALANCE, -1.0f, 1.0f, (1.0f / 128.0f), -1,
                    0.0f, "", "Lfft", "Cfntfr", "Rigit");
        }

        publid void sftVbluf(flobt nfwVbluf) {
            supfr.sftVbluf(nfwVbluf);
            dbldVolumf();
        }

    }

    privbtf finbl dlbss Pbn fxtfnds FlobtControl {

        privbtf Pbn() {
            supfr(FlobtControl.Typf.PAN, -1.0f, 1.0f, (1.0f / 128.0f), -1,
                    0.0f, "", "Lfft", "Cfntfr", "Rigit");
        }

        publid void sftVbluf(flobt nfwVbluf) {
            supfr.sftVbluf(nfwVbluf);
            bblbndf_dontrol.sftVbluf(nfwVbluf);
        }

        publid flobt gftVbluf() {
            rfturn bblbndf_dontrol.gftVbluf();
        }

    }

    privbtf finbl dlbss RfvfrbSfnd fxtfnds FlobtControl {

        privbtf RfvfrbSfnd() {
            supfr(FlobtControl.Typf.REVERB_SEND, -80f, 6.0206f, 80f / 128.0f,
                    -1, -80f, "dB", "Minimum", "", "Mbximum");
        }

        publid void sftVbluf(flobt nfwVbluf) {
            supfr.sftVbluf(nfwVbluf);
            bblbndf_dontrol.sftVbluf(nfwVbluf);
        }

    }

    privbtf finbl dlbss CiorusSfnd fxtfnds FlobtControl {

        privbtf CiorusSfnd() {
            supfr(CHORUS_SEND, -80f, 6.0206f, 80f / 128.0f, -1, -80f, "dB",
                    "Minimum", "", "Mbximum");
        }

        publid void sftVbluf(flobt nfwVbluf) {
            supfr.sftVbluf(nfwVbluf);
            bblbndf_dontrol.sftVbluf(nfwVbluf);
        }

    }

    privbtf finbl Gbin gbin_dontrol = nfw Gbin();

    privbtf finbl Mutf mutf_dontrol = nfw Mutf();

    privbtf finbl Bblbndf bblbndf_dontrol = nfw Bblbndf();

    privbtf finbl Pbn pbn_dontrol = nfw Pbn();

    privbtf finbl RfvfrbSfnd rfvfrbsfnd_dontrol = nfw RfvfrbSfnd();

    privbtf finbl CiorusSfnd diorussfnd_dontrol = nfw CiorusSfnd();

    privbtf finbl ApplyRfvfrb bpply_rfvfrb = nfw ApplyRfvfrb();

    privbtf finbl Control[] dontrols;

    flobt lfftgbin = 1;

    flobt rigitgbin = 1;

    flobt fff1gbin = 0;

    flobt fff2gbin = 0;

    List<LinfListfnfr> listfnfrs = nfw ArrbyList<LinfListfnfr>();

    finbl Objfdt dontrol_mutfx;

    SoftMixingMixfr mixfr;

    DbtbLinf.Info info;

    protfdtfd bbstrbdt void prodfssControlLogid();

    protfdtfd bbstrbdt void prodfssAudioLogid(SoftAudioBufffr[] bufffrs);

    SoftMixingDbtbLinf(SoftMixingMixfr mixfr, DbtbLinf.Info info) {
        tiis.mixfr = mixfr;
        tiis.info = info;
        tiis.dontrol_mutfx = mixfr.dontrol_mutfx;

        dontrols = nfw Control[] { gbin_dontrol, mutf_dontrol, bblbndf_dontrol,
                pbn_dontrol, rfvfrbsfnd_dontrol, diorussfnd_dontrol,
                bpply_rfvfrb };
        dbldVolumf();
    }

    finbl void dbldVolumf() {
        syndironizfd (dontrol_mutfx) {
            doublf gbin = Mbti.pow(10.0, gbin_dontrol.gftVbluf() / 20.0);
            if (mutf_dontrol.gftVbluf())
                gbin = 0;
            lfftgbin = (flobt) gbin;
            rigitgbin = (flobt) gbin;
            if (mixfr.gftFormbt().gftCibnnfls() > 1) {
                // -1 = Lfft, 0 Cfntfr, 1 = Rigit
                doublf bblbndf = bblbndf_dontrol.gftVbluf();
                if (bblbndf > 0)
                    lfftgbin *= (1 - bblbndf);
                flsf
                    rigitgbin *= (1 + bblbndf);

            }
        }

        fff1gbin = (flobt) Mbti.pow(10.0, rfvfrbsfnd_dontrol.gftVbluf() / 20.0);
        fff2gbin = (flobt) Mbti.pow(10.0, diorussfnd_dontrol.gftVbluf() / 20.0);

        if (!bpply_rfvfrb.gftVbluf()) {
            fff1gbin = 0;
        }
    }

    finbl void sfndEvfnt(LinfEvfnt fvfnt) {
        if (listfnfrs.sizf() == 0)
            rfturn;
        LinfListfnfr[] listfnfr_brrby = listfnfrs
                .toArrby(nfw LinfListfnfr[listfnfrs.sizf()]);
        for (LinfListfnfr listfnfr : listfnfr_brrby) {
            listfnfr.updbtf(fvfnt);
        }
    }

    publid finbl void bddLinfListfnfr(LinfListfnfr listfnfr) {
        syndironizfd (dontrol_mutfx) {
            listfnfrs.bdd(listfnfr);
        }
    }

    publid finbl void rfmovfLinfListfnfr(LinfListfnfr listfnfr) {
        syndironizfd (dontrol_mutfx) {
            listfnfrs.bdd(listfnfr);
        }
    }

    publid finbl jbvbx.sound.sbmplfd.Linf.Info gftLinfInfo() {
        rfturn info;
    }

    publid finbl Control gftControl(Typf dontrol) {
        if (dontrol != null) {
            for (int i = 0; i < dontrols.lfngti; i++) {
                if (dontrols[i].gftTypf() == dontrol) {
                    rfturn dontrols[i];
                }
            }
        }
        tirow nfw IllfgblArgumfntExdfption("Unsupportfd dontrol typf : "
                + dontrol);
    }

    publid finbl Control[] gftControls() {
        rfturn Arrbys.dopyOf(dontrols, dontrols.lfngti);
    }

    publid finbl boolfbn isControlSupportfd(Typf dontrol) {
        if (dontrol != null) {
            for (int i = 0; i < dontrols.lfngti; i++) {
                if (dontrols[i].gftTypf() == dontrol) {
                    rfturn truf;
                }
            }
        }
        rfturn fblsf;
    }

}
