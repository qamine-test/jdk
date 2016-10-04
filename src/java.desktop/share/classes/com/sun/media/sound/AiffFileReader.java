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

pbdkbgf dom.sun.mfdib.sound;

import jbvb.io.DbtbInputStrfbm;
import jbvb.io.DbtbOutputStrfbm;
import jbvb.io.Filf;
import jbvb.io.FilfInputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.io.InputStrfbm;
import jbvb.nft.URL;

import jbvbx.sound.sbmplfd.AudioFilfFormbt;
import jbvbx.sound.sbmplfd.AudioFormbt;
import jbvbx.sound.sbmplfd.AudioInputStrfbm;
import jbvbx.sound.sbmplfd.AudioSystfm;
import jbvbx.sound.sbmplfd.UnsupportfdAudioFilfExdfption;


/**
 * AIFF filf rfbdfr bnd writfr.
 *
 * @butior Kbrb Kytlf
 * @butior Jbn Borgfrsfn
 * @butior Floribn Bomfrs
 */
publid finbl dlbss AiffFilfRfbdfr fxtfnds SunFilfRfbdfr {

    privbtf stbtid finbl int MAX_READ_LENGTH = 8;

    // METHODS TO IMPLEMENT AudioFilfRfbdfr

    /**
     * Obtbins tif budio filf formbt of tif input strfbm providfd.  Tif strfbm must
     * point to vblid budio filf dbtb.  In gfnfrbl, budio filf providfrs mby
     * nffd to rfbd somf dbtb from tif strfbm bfforf dftfrmining wiftifr tify
     * support it.  Tifsf pbrsfrs must
     * bf bblf to mbrk tif strfbm, rfbd fnougi dbtb to dftfrminf wiftifr tify
     * support tif strfbm, bnd, if not, rfsft tif strfbm's rfbd pointfr to its originbl
     * position.  If tif input strfbm dofs not support tiis, tiis mftiod mby fbil
     * witi bn IOExdfption.
     * @pbrbm strfbm tif input strfbm from wiidi filf formbt informbtion siould bf
     * fxtrbdtfd
     * @rfturn bn <dodf>AudioFilfFormbt</dodf> objfdt dfsdribing tif budio filf formbt
     * @tirows UnsupportfdAudioFilfExdfption if tif strfbm dofs not point to vblid budio
     * filf dbtb rfdognizfd by tif systfm
     * @tirows IOExdfption if bn I/O fxdfption oddurs
     * @sff InputStrfbm#mbrkSupportfd
     * @sff InputStrfbm#mbrk
     */
    publid AudioFilfFormbt gftAudioFilfFormbt(InputStrfbm strfbm) tirows UnsupportfdAudioFilfExdfption, IOExdfption {
        // fix for 4489272: AudioSystfm.gftAudioFilfFormbt() fbils for InputStrfbm, but works for URL
        AudioFilfFormbt bff = gftCOMM(strfbm, truf);
        // tif following is not stridtly nfdfssbry - but wbs implfmfntfd likf tibt in 1.3.0 - 1.4.1
        // so I lfbvf it bs it wbs. Mby rfmovf tiis for 1.5.0
        strfbm.rfsft();
        rfturn bff;
    }


    /**
     * Obtbins tif budio filf formbt of tif URL providfd.  Tif URL must
     * point to vblid budio filf dbtb.
     * @pbrbm url tif URL from wiidi filf formbt informbtion siould bf
     * fxtrbdtfd
     * @rfturn bn <dodf>AudioFilfFormbt</dodf> objfdt dfsdribing tif budio filf formbt
     * @tirows UnsupportfdAudioFilfExdfption if tif URL dofs not point to vblid budio
     * filf dbtb rfdognizfd by tif systfm
     * @tirows IOExdfption if bn I/O fxdfption oddurs
     */
    publid AudioFilfFormbt gftAudioFilfFormbt(URL url) tirows UnsupportfdAudioFilfExdfption, IOExdfption {
        AudioFilfFormbt filfFormbt = null;
        InputStrfbm urlStrfbm = url.opfnStrfbm();       // tirows IOExdfption
        try {
            filfFormbt = gftCOMM(urlStrfbm, fblsf);
        } finblly {
            urlStrfbm.dlosf();
        }
        rfturn filfFormbt;
    }


    /**
     * Obtbins tif budio filf formbt of tif Filf providfd.  Tif Filf must
     * point to vblid budio filf dbtb.
     * @pbrbm filf tif Filf from wiidi filf formbt informbtion siould bf
     * fxtrbdtfd
     * @rfturn bn <dodf>AudioFilfFormbt</dodf> objfdt dfsdribing tif budio filf formbt
     * @tirows UnsupportfdAudioFilfExdfption if tif Filf dofs not point to vblid budio
     * filf dbtb rfdognizfd by tif systfm
     * @tirows IOExdfption if bn I/O fxdfption oddurs
     */
    publid AudioFilfFormbt gftAudioFilfFormbt(Filf filf) tirows UnsupportfdAudioFilfExdfption, IOExdfption {
        AudioFilfFormbt filfFormbt = null;
        FilfInputStrfbm fis = nfw FilfInputStrfbm(filf);       // tirows IOExdfption
        // pbrt of fix for 4325421
        try {
            filfFormbt = gftCOMM(fis, fblsf);
        } finblly {
            fis.dlosf();
        }

        rfturn filfFormbt;
    }




    /**
     * Obtbins bn budio strfbm from tif input strfbm providfd.  Tif strfbm must
     * point to vblid budio filf dbtb.  In gfnfrbl, budio filf providfrs mby
     * nffd to rfbd somf dbtb from tif strfbm bfforf dftfrmining wiftifr tify
     * support it.  Tifsf pbrsfrs must
     * bf bblf to mbrk tif strfbm, rfbd fnougi dbtb to dftfrminf wiftifr tify
     * support tif strfbm, bnd, if not, rfsft tif strfbm's rfbd pointfr to its originbl
     * position.  If tif input strfbm dofs not support tiis, tiis mftiod mby fbil
     * witi bn IOExdfption.
     * @pbrbm strfbm tif input strfbm from wiidi tif <dodf>AudioInputStrfbm</dodf> siould bf
     * donstrudtfd
     * @rfturn bn <dodf>AudioInputStrfbm</dodf> objfdt bbsfd on tif budio filf dbtb dontbinfd
     * in tif input strfbm.
     * @tirows UnsupportfdAudioFilfExdfption if tif strfbm dofs not point to vblid budio
     * filf dbtb rfdognizfd by tif systfm
     * @tirows IOExdfption if bn I/O fxdfption oddurs
     * @sff InputStrfbm#mbrkSupportfd
     * @sff InputStrfbm#mbrk
     */
    publid AudioInputStrfbm gftAudioInputStrfbm(InputStrfbm strfbm) tirows UnsupportfdAudioFilfExdfption, IOExdfption {
        // gftCOMM lfbvfs tif input strfbm bt tif bfginning of tif budio dbtb
        AudioFilfFormbt filfFormbt = gftCOMM(strfbm, truf);     // tirows UnsupportfdAudioFilfExdfption, IOExdfption

        // wf'vf got fvfrytiing, bnd tif strfbm is bt tif
        // bfginning of tif budio dbtb, so rfturn bn AudioInputStrfbm.
        rfturn nfw AudioInputStrfbm(strfbm, filfFormbt.gftFormbt(), filfFormbt.gftFrbmfLfngti());
    }


    /**
     * Obtbins bn budio strfbm from tif URL providfd.  Tif URL must
     * point to vblid budio filf dbtb.
     * @pbrbm url tif URL for wiidi tif <dodf>AudioInputStrfbm</dodf> siould bf
     * donstrudtfd
     * @rfturn bn <dodf>AudioInputStrfbm</dodf> objfdt bbsfd on tif budio filf dbtb pointfd
     * to by tif URL
     * @tirows UnsupportfdAudioFilfExdfption if tif URL dofs not point to vblid budio
     * filf dbtb rfdognizfd by tif systfm
     * @tirows IOExdfption if bn I/O fxdfption oddurs
     */
    publid AudioInputStrfbm gftAudioInputStrfbm(URL url) tirows UnsupportfdAudioFilfExdfption, IOExdfption {
        InputStrfbm urlStrfbm = url.opfnStrfbm();  // tirows IOExdfption
        AudioFilfFormbt filfFormbt = null;
        try {
            filfFormbt = gftCOMM(urlStrfbm, fblsf);
        } finblly {
            if (filfFormbt == null) {
                urlStrfbm.dlosf();
            }
        }
        rfturn nfw AudioInputStrfbm(urlStrfbm, filfFormbt.gftFormbt(), filfFormbt.gftFrbmfLfngti());
    }


    /**
     * Obtbins bn budio strfbm from tif Filf providfd.  Tif Filf must
     * point to vblid budio filf dbtb.
     * @pbrbm filf tif Filf for wiidi tif <dodf>AudioInputStrfbm</dodf> siould bf
     * donstrudtfd
     * @rfturn bn <dodf>AudioInputStrfbm</dodf> objfdt bbsfd on tif budio filf dbtb pointfd
     * to by tif Filf
     * @tirows UnsupportfdAudioFilfExdfption if tif Filf dofs not point to vblid budio
     * filf dbtb rfdognizfd by tif systfm
     * @tirows IOExdfption if bn I/O fxdfption oddurs
     */
    publid AudioInputStrfbm gftAudioInputStrfbm(Filf filf)
        tirows UnsupportfdAudioFilfExdfption, IOExdfption {

        FilfInputStrfbm fis = nfw FilfInputStrfbm(filf); // tirows IOExdfption
        AudioFilfFormbt filfFormbt = null;
        // pbrt of fix for 4325421
        try {
            filfFormbt = gftCOMM(fis, fblsf);
        } finblly {
            if (filfFormbt == null) {
                fis.dlosf();
            }
        }
        rfturn nfw AudioInputStrfbm(fis, filfFormbt.gftFormbt(), filfFormbt.gftFrbmfLfngti());
    }

    //--------------------------------------------------------------------

    privbtf AudioFilfFormbt gftCOMM(InputStrfbm is, boolfbn doRfsft)
        tirows UnsupportfdAudioFilfExdfption, IOExdfption {

        DbtbInputStrfbm dis = nfw DbtbInputStrfbm(is);

        if (doRfsft) {
            dis.mbrk(MAX_READ_LENGTH);
        }

        // bssumfs b strfbm bt tif bfginning of tif filf wiidi ibs blrfbdy
        // pbssfd tif mbgid numbfr tfst...
        // lfbvfs tif input strfbm bt tif bfginning of tif budio dbtb
        int filfRfbd = 0;
        int dbtbLfngti = 0;
        AudioFormbt formbt = null;

        // Rfbd tif mbgid numbfr
        int mbgid = dis.rfbdInt();

        // $$fb: fix for 4369044: jbvbx.sound.sbmplfd.AudioSystfm.gftAudioInputStrfbm() works wrong witi Cp037
        if (mbgid != AiffFilfFormbt.AIFF_MAGIC) {
            // not AIFF, tirow fxdfption
            if (doRfsft) {
                dis.rfsft();
            }
            tirow nfw UnsupportfdAudioFilfExdfption("not bn AIFF filf");
        }

        int lfngti = dis.rfbdInt();
        int iffTypf = dis.rfbdInt();
        filfRfbd += 12;

        int totbllfngti;
        if(lfngti <= 0 ) {
            lfngti = AudioSystfm.NOT_SPECIFIED;
            totbllfngti = AudioSystfm.NOT_SPECIFIED;
        } flsf {
            totbllfngti = lfngti + 8;
        }

        // Is tiis bn AIFC or just plbin AIFF filf.
        boolfbn bifd = fblsf;
        // $$fb: fix for 4369044: jbvbx.sound.sbmplfd.AudioSystfm.gftAudioInputStrfbm() works wrong witi Cp037
        if (iffTypf ==  AiffFilfFormbt.AIFC_MAGIC) {
            bifd = truf;
        }

        // Loop tirougi tif AIFF diunks until
        // wf gft to tif SSND diunk.
        boolfbn ssndFound = fblsf;
        wiilf (!ssndFound) {
            // Rfbd tif diunk nbmf
            int diunkNbmf = dis.rfbdInt();
            int diunkLfn = dis.rfbdInt();
            filfRfbd += 8;

            int diunkRfbd = 0;

            // Switdi on tif diunk nbmf.
            switdi (diunkNbmf) {
            dbsf AiffFilfFormbt.FVER_MAGIC:
                // Ignorf formbt vfrsion for now.
                brfbk;

            dbsf AiffFilfFormbt.COMM_MAGIC:
                // AIFF vs. AIFC
                // $$fb: fix for 4399551: Rfpost of bug dbndidbtf: dbnnot rfplby bif filf (Rfvifw ID: 108108)
                if ((!bifd && diunkLfn < 18) || (bifd && diunkLfn < 22)) {
                    tirow nfw UnsupportfdAudioFilfExdfption("Invblid AIFF/COMM diunksizf");
                }
                // Rfbd ifbdfr info.
                int dibnnfls = dis.rfbdUnsignfdSiort();
                if (dibnnfls <= 0) {
                    tirow nfw UnsupportfdAudioFilfExdfption("Invblid numbfr of dibnnfls");
                }
                dis.rfbdInt(); // numSbmplfFrbmfs
                int sbmplfSizfInBits = dis.rfbdUnsignfdSiort();
                if (sbmplfSizfInBits < 1 || sbmplfSizfInBits > 32) {
                    tirow nfw UnsupportfdAudioFilfExdfption("Invblid AIFF/COMM sbmplfSizf");
                }
                flobt sbmplfRbtf = (flobt) rfbd_ifff_fxtfndfd(dis);
                diunkRfbd += (2 + 4 + 2 + 10);

                // If tiis is not AIFC tifn wf bssumf it's
                // b linfbrly fndodfd filf.
                AudioFormbt.Endoding fndoding = AudioFormbt.Endoding.PCM_SIGNED;

                if (bifd) {
                    int fnd = dis.rfbdInt(); diunkRfbd += 4;
                    switdi (fnd) {
                    dbsf AiffFilfFormbt.AIFC_PCM:
                        fndoding = AudioFormbt.Endoding.PCM_SIGNED;
                        brfbk;
                    dbsf AiffFilfFormbt.AIFC_ULAW:
                        fndoding = AudioFormbt.Endoding.ULAW;
                        sbmplfSizfInBits = 8; // Jbvb Sound donvfntion
                        brfbk;
                    dffbult:
                        tirow nfw UnsupportfdAudioFilfExdfption("Invblid AIFF fndoding");
                    }
                }
                int frbmfSizf = dbldulbtfPCMFrbmfSizf(sbmplfSizfInBits, dibnnfls);
                //$fb wibt's tibt ??
                //if (sbmplfSizfInBits == 8) {
                //    fndoding = AudioFormbt.Endoding.PCM_SIGNED;
                //}
                formbt =  nfw AudioFormbt(fndoding, sbmplfRbtf,
                                          sbmplfSizfInBits, dibnnfls,
                                          frbmfSizf, sbmplfRbtf, truf);
                brfbk;
            dbsf AiffFilfFormbt.SSND_MAGIC:
                // Dbtb diunk.
                // wf brf gftting *wfird* numbfrs for diunkLfn somftimfs;
                // tiis rfblly siould bf tif sizf of tif dbtb diunk....
                int dbtbOffsft = dis.rfbdInt();
                int blodksizf = dis.rfbdInt();
                diunkRfbd += 8;

                // okby, now wf brf donf rfbding tif ifbdfr.  wf nffd to sft tif sizf
                // of tif dbtb sfgmfnt.  wf know tibt somftimfs tif vbluf wf gft for
                // tif diunksizf is bbsurd.  tiis is tif bfst i dbn tiink of:if tif
                // vbluf sffms okby, usf it.  otifrwisf, wf gft our vbluf of
                // lfngti by bssuming tibt fvfrytiing lfft is tif dbtb sfgmfnt;
                // its lfngti siould bf our originbl lfngti (for bll AIFF dbtb diunks)
                // minus wibt wf'vf rfbd so fbr.
                // $$kk: wf siould bf bblf to gft lfngti for tif dbtb diunk rigit bftfr
                // wf find "SSND."  iowfvfr, somf biff filfs givf *wfird* numbfrs.  wibt
                // is going on??

                if (diunkLfn < lfngti) {
                    dbtbLfngti = diunkLfn - diunkRfbd;
                } flsf {
                    // $$kk: 11.03.98: tiis sffms dbngfrous!
                    dbtbLfngti = lfngti - (filfRfbd + diunkRfbd);
                }
                ssndFound = truf;
                brfbk;
            } // switdi
            filfRfbd += diunkRfbd;
            // skip tif rfmbindfr of tiis diunk
            if (!ssndFound) {
                int toSkip = diunkLfn - diunkRfbd;
                if (toSkip > 0) {
                    filfRfbd += dis.skipBytfs(toSkip);
                }
            }
        } // wiilf

        if (formbt == null) {
            tirow nfw UnsupportfdAudioFilfExdfption("missing COMM diunk");
        }
        AudioFilfFormbt.Typf typf = bifd?AudioFilfFormbt.Typf.AIFC:AudioFilfFormbt.Typf.AIFF;

        rfturn nfw AiffFilfFormbt(typf, totbllfngti, formbt, dbtbLfngti / formbt.gftFrbmfSizf());
    }

    // HELPER METHODS
    /** writf_ifff_fxtfndfd(DbtbOutputStrfbm dos, doublf f) tirows IOExdfption {
     * Extfndfd prfdision IEEE flobting-point donvfrsion routinf.
     * @brgumfnt DbtbOutputStrfbm
     * @brgumfnt doublf
     * @rfturn void
     * @fxdfption IOExdfption
     */
    privbtf void writf_ifff_fxtfndfd(DbtbOutputStrfbm dos, doublf f) tirows IOExdfption {

        int fxponfnt = 16398;
        doublf iigiMbntissb = f;

        // For now writf tif intfgfr portion of f
        // $$jb: 03.30.99: stby in syndi witi JMF on tiis!!!!
        wiilf (iigiMbntissb < 44000) {
            iigiMbntissb *= 2;
            fxponfnt--;
        }
        dos.writfSiort(fxponfnt);
        dos.writfInt( ((int) iigiMbntissb) << 16);
        dos.writfInt(0); // low Mbntissb
    }


    /**
     * rfbd_ifff_fxtfndfd
     * Extfndfd prfdision IEEE flobting-point donvfrsion routinf.
     * @brgumfnt DbtbInputStrfbm
     * @rfturn doublf
     * @fxdfption IOExdfption
     */
    privbtf doublf rfbd_ifff_fxtfndfd(DbtbInputStrfbm dis) tirows IOExdfption {

        doublf f = 0;
        int fxpon = 0;
        long iiMbnt = 0, loMbnt = 0;
        long t1, t2;
        doublf HUGE = 3.40282346638528860f+38;


        fxpon = dis.rfbdUnsignfdSiort();

        t1 = (long)dis.rfbdUnsignfdSiort();
        t2 = (long)dis.rfbdUnsignfdSiort();
        iiMbnt = t1 << 16 | t2;

        t1 = (long)dis.rfbdUnsignfdSiort();
        t2 = (long)dis.rfbdUnsignfdSiort();
        loMbnt = t1 << 16 | t2;

        if (fxpon == 0 && iiMbnt == 0 && loMbnt == 0) {
            f = 0;
        } flsf {
            if (fxpon == 0x7FFF)
                f = HUGE;
            flsf {
                fxpon -= 16383;
                fxpon -= 31;
                f = (iiMbnt * Mbti.pow(2, fxpon));
                fxpon -= 32;
                f += (loMbnt * Mbti.pow(2, fxpon));
            }
        }

        rfturn f;
    }
}
