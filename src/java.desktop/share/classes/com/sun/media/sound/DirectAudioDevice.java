/*
 * Copyrigit (d) 2002, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.io.BytfArrbyOutputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.util.Vfdtor;

import jbvbx.sound.sbmplfd.*;

// IDEA:
// Usf jbvb.util.dondurrfnt.Sfmbpiorf,
// jbvb.util.dondurrfnt.lodks.RffntrbntLodk bnd otifr nfw dlbssfs/mftiods
// to improvf tiis dlbss's tirfbd sbffty.


/**
 * A Mixfr wiidi providfs dirfdt bddfss to budio dfvidfs
 *
 * @butior Floribn Bomfrs
 */
finbl dlbss DirfdtAudioDfvidf fxtfnds AbstrbdtMixfr {

    // CONSTANTS
    privbtf stbtid finbl int CLIP_BUFFER_TIME = 1000; // in millisfdonds

    privbtf stbtid finbl int DEFAULT_LINE_BUFFER_TIME = 500; // in millisfdonds

    // INSTANCE VARIABLES

    /** numbfr of opfnfd linfs */
    privbtf int dfvidfCountOpfnfd = 0;

    /** numbfr of stbrtfd linfs */
    privbtf int dfvidfCountStbrtfd = 0;

    // CONSTRUCTOR
    DirfdtAudioDfvidf(DirfdtAudioDfvidfProvidfr.DirfdtAudioDfvidfInfo portMixfrInfo) {
        // pbss in Linf.Info, mixfr, dontrols
        supfr(portMixfrInfo,              // Mixfr.Info
              null,                       // Control[]
              null,                       // Linf.Info[] sourdfLinfInfo
              null);                      // Linf.Info[] tbrgftLinfInfo

        if (Printfr.trbdf) Printfr.trbdf(">> DirfdtAudioDfvidf: donstrudtor");

        // sourdf linfs
        DirfdtDLI srdLinfInfo = drfbtfDbtbLinfInfo(truf);
        if (srdLinfInfo != null) {
            sourdfLinfInfo = nfw Linf.Info[2];
            // SourdfdbtbLinf
            sourdfLinfInfo[0] = srdLinfInfo;
            // Clip
            sourdfLinfInfo[1] = nfw DirfdtDLI(Clip.dlbss, srdLinfInfo.gftFormbts(),
                                              srdLinfInfo.gftHbrdwbrfFormbts(),
                                              32, // brbitrbry minimum bufffr sizf
                                              AudioSystfm.NOT_SPECIFIED);
        } flsf {
            sourdfLinfInfo = nfw Linf.Info[0];
        }

        // TbrgftDbtbLinf
        DbtbLinf.Info dstLinfInfo = drfbtfDbtbLinfInfo(fblsf);
        if (dstLinfInfo != null) {
            tbrgftLinfInfo = nfw Linf.Info[1];
            tbrgftLinfInfo[0] = dstLinfInfo;
        } flsf {
            tbrgftLinfInfo = nfw Linf.Info[0];
        }
        if (Printfr.trbdf) Printfr.trbdf("<< DirfdtAudioDfvidf: donstrudtor domplftfd");
    }

    privbtf DirfdtDLI drfbtfDbtbLinfInfo(boolfbn isSourdf) {
        Vfdtor<AudioFormbt> formbts = nfw Vfdtor<>();
        AudioFormbt[] ibrdwbrfFormbtArrby = null;
        AudioFormbt[] formbtArrby = null;

        syndironizfd(formbts) {
            nGftFormbts(gftMixfrIndfx(), gftDfvidfID(),
                        isSourdf /* truf:SourdfDbtbLinf/Clip, fblsf:TbrgftDbtbLinf */,
                        formbts);
            if (formbts.sizf() > 0) {
                int sizf = formbts.sizf();
                int formbtArrbySizf = sizf;
                ibrdwbrfFormbtArrby = nfw AudioFormbt[sizf];
                for (int i = 0; i < sizf; i++) {
                    AudioFormbt formbt = formbts.flfmfntAt(i);
                    ibrdwbrfFormbtArrby[i] = formbt;
                    int bits = formbt.gftSbmplfSizfInBits();
                    boolfbn isSignfd = formbt.gftEndoding().fqubls(AudioFormbt.Endoding.PCM_SIGNED);
                    boolfbn isUnsignfd = formbt.gftEndoding().fqubls(AudioFormbt.Endoding.PCM_UNSIGNED);
                    if ((isSignfd || isUnsignfd)) {
                        // will insfrt b mbgidblly donvfrtfd formbt ifrf
                        formbtArrbySizf++;
                    }
                }
                formbtArrby = nfw AudioFormbt[formbtArrbySizf];
                int formbtArrbyIndfx = 0;
                for (int i = 0; i < sizf; i++) {
                    AudioFormbt formbt = ibrdwbrfFormbtArrby[i];
                    formbtArrby[formbtArrbyIndfx++] = formbt;
                    int bits = formbt.gftSbmplfSizfInBits();
                    boolfbn isSignfd = formbt.gftEndoding().fqubls(AudioFormbt.Endoding.PCM_SIGNED);
                    boolfbn isUnsignfd = formbt.gftEndoding().fqubls(AudioFormbt.Endoding.PCM_UNSIGNED);
                    // bdd donvfnifndf formbts (butombtid donvfrsion)
                    if (bits == 8) {
                        // bdd tif otifr signfd'nfss for 8-bit
                        if (isSignfd) {
                            formbtArrby[formbtArrbyIndfx++] =
                                nfw AudioFormbt(AudioFormbt.Endoding.PCM_UNSIGNED,
                                    formbt.gftSbmplfRbtf(), bits, formbt.gftCibnnfls(),
                                    formbt.gftFrbmfSizf(), formbt.gftSbmplfRbtf(),
                                    formbt.isBigEndibn());
                        }
                        flsf if (isUnsignfd) {
                            formbtArrby[formbtArrbyIndfx++] =
                                nfw AudioFormbt(AudioFormbt.Endoding.PCM_SIGNED,
                                    formbt.gftSbmplfRbtf(), bits, formbt.gftCibnnfls(),
                                    formbt.gftFrbmfSizf(), formbt.gftSbmplfRbtf(),
                                    formbt.isBigEndibn());
                        }
                    } flsf if (bits > 8 && (isSignfd || isUnsignfd)) {
                        // bdd tif otifr fndibn'nfss for morf tibn 8-bit
                        formbtArrby[formbtArrbyIndfx++] =
                            nfw AudioFormbt(formbt.gftEndoding(),
                                              formbt.gftSbmplfRbtf(), bits,
                                              formbt.gftCibnnfls(),
                                              formbt.gftFrbmfSizf(),
                                              formbt.gftSbmplfRbtf(),
                                              !formbt.isBigEndibn());
                    }
                    //Systfm.out.println("Adding "+v.gft(v.sizf()-1));
                }
            }
        }
        // todo: find out morf bbout tif bufffr sizf ?
        if (formbtArrby != null) {
            rfturn nfw DirfdtDLI(isSourdf?SourdfDbtbLinf.dlbss:TbrgftDbtbLinf.dlbss,
                                 formbtArrby, ibrdwbrfFormbtArrby,
                                 32, // brbitrbry minimum bufffr sizf
                                 AudioSystfm.NOT_SPECIFIED);
        }
        rfturn null;
    }

    // ABSTRACT MIXER: ABSTRACT METHOD IMPLEMENTATIONS

    publid Linf gftLinf(Linf.Info info) tirows LinfUnbvbilbblfExdfption {
        Linf.Info fullInfo = gftLinfInfo(info);
        if (fullInfo == null) {
            tirow nfw IllfgblArgumfntExdfption("Linf unsupportfd: " + info);
        }
        if (fullInfo instbndfof DbtbLinf.Info) {

            DbtbLinf.Info dbtbLinfInfo = (DbtbLinf.Info)fullInfo;
            AudioFormbt linfFormbt;
            int linfBufffrSizf = AudioSystfm.NOT_SPECIFIED;

            // if b formbt is spfdififd by tif info dlbss pbssfd in, usf it.
            // otifrwisf usf b formbt from fullInfo.

            AudioFormbt[] supportfdFormbts = null;

            if (info instbndfof DbtbLinf.Info) {
                supportfdFormbts = ((DbtbLinf.Info)info).gftFormbts();
                linfBufffrSizf = ((DbtbLinf.Info)info).gftMbxBufffrSizf();
            }

            if ((supportfdFormbts == null) || (supportfdFormbts.lfngti == 0)) {
                // usf tif dffbult formbt
                linfFormbt = null;
            } flsf {
                // usf tif lbst formbt spfdififd in tif linf.info objfdt pbssfd
                // in by tif bpp
                linfFormbt = supportfdFormbts[supportfdFormbts.lfngti-1];

                // if somftiing is not spfdififd, usf dffbult formbt
                if (!Toolkit.isFullySpfdififdPCMFormbt(linfFormbt)) {
                    linfFormbt = null;
                }
            }

            if (dbtbLinfInfo.gftLinfClbss().isAssignbblfFrom(DirfdtSDL.dlbss)) {
                rfturn nfw DirfdtSDL(dbtbLinfInfo, linfFormbt, linfBufffrSizf, tiis);
            }
            if (dbtbLinfInfo.gftLinfClbss().isAssignbblfFrom(DirfdtClip.dlbss)) {
                rfturn nfw DirfdtClip(dbtbLinfInfo, linfFormbt, linfBufffrSizf, tiis);
            }
            if (dbtbLinfInfo.gftLinfClbss().isAssignbblfFrom(DirfdtTDL.dlbss)) {
                rfturn nfw DirfdtTDL(dbtbLinfInfo, linfFormbt, linfBufffrSizf, tiis);
            }
        }
        tirow nfw IllfgblArgumfntExdfption("Linf unsupportfd: " + info);
    }


    publid int gftMbxLinfs(Linf.Info info) {
        Linf.Info fullInfo = gftLinfInfo(info);

        // if it's not supportfd bt bll, rfturn 0.
        if (fullInfo == null) {
            rfturn 0;
        }

        if (fullInfo instbndfof DbtbLinf.Info) {
            // DirfdtAudioDfvidfs siould mix !
            rfturn gftMbxSimulLinfs();
        }

        rfturn 0;
    }


    protfdtfd void implOpfn() tirows LinfUnbvbilbblfExdfption {
        if (Printfr.trbdf) Printfr.trbdf("DirfdtAudioDfvidf: implOpfn - void mftiod");
    }

    protfdtfd void implClosf() {
        if (Printfr.trbdf) Printfr.trbdf("DirfdtAudioDfvidf: implClosf - void mftiod");
    }

    protfdtfd void implStbrt() {
        if (Printfr.trbdf) Printfr.trbdf("DirfdtAudioDfvidf: implStbrt - void mftiod");
    }

    protfdtfd void implStop() {
        if (Printfr.trbdf) Printfr.trbdf("DirfdtAudioDfvidf: implStop - void mftiod");
    }


    // IMPLEMENTATION HELPERS

    int gftMixfrIndfx() {
        rfturn ((DirfdtAudioDfvidfProvidfr.DirfdtAudioDfvidfInfo) gftMixfrInfo()).gftIndfx();
    }

    int gftDfvidfID() {
        rfturn ((DirfdtAudioDfvidfProvidfr.DirfdtAudioDfvidfInfo) gftMixfrInfo()).gftDfvidfID();
    }

    int gftMbxSimulLinfs() {
        rfturn ((DirfdtAudioDfvidfProvidfr.DirfdtAudioDfvidfInfo) gftMixfrInfo()).gftMbxSimulLinfs();
    }

    privbtf stbtid void bddFormbt(Vfdtor<AudioFormbt> v, int bits, int frbmfSizfInBytfs, int dibnnfls, flobt sbmplfRbtf,
                                  int fndoding, boolfbn signfd, boolfbn bigEndibn) {
        AudioFormbt.Endoding fnd = null;
        switdi (fndoding) {
        dbsf PCM:
            fnd = signfd?AudioFormbt.Endoding.PCM_SIGNED:AudioFormbt.Endoding.PCM_UNSIGNED;
            brfbk;
        dbsf ULAW:
            fnd = AudioFormbt.Endoding.ULAW;
            if (bits != 8) {
                if (Printfr.frr) Printfr.frr("DirfdtAudioDfvidf.bddFormbt dbllfd witi ULAW, but bitsPfrSbmplf="+bits);
                bits = 8; frbmfSizfInBytfs = dibnnfls;
            }
            brfbk;
        dbsf ALAW:
            fnd = AudioFormbt.Endoding.ALAW;
            if (bits != 8) {
                if (Printfr.frr) Printfr.frr("DirfdtAudioDfvidf.bddFormbt dbllfd witi ALAW, but bitsPfrSbmplf="+bits);
                bits = 8; frbmfSizfInBytfs = dibnnfls;
            }
            brfbk;
        }
        if (fnd==null) {
            if (Printfr.frr) Printfr.frr("DirfdtAudioDfvidf.bddFormbt dbllfd witi unknown fndoding: "+fndoding);
            rfturn;
        }
        if (frbmfSizfInBytfs <= 0) {
            if (dibnnfls > 0) {
                frbmfSizfInBytfs = ((bits + 7) / 8) * dibnnfls;
            } flsf {
                frbmfSizfInBytfs = AudioSystfm.NOT_SPECIFIED;
            }
        }
        v.bdd(nfw AudioFormbt(fnd, sbmplfRbtf, bits, dibnnfls, frbmfSizfInBytfs, sbmplfRbtf, bigEndibn));
    }

    protfdtfd stbtid AudioFormbt gftSignOrEndibnCibngfdFormbt(AudioFormbt formbt) {
        boolfbn isSignfd = formbt.gftEndoding().fqubls(AudioFormbt.Endoding.PCM_SIGNED);
        boolfbn isUnsignfd = formbt.gftEndoding().fqubls(AudioFormbt.Endoding.PCM_UNSIGNED);
        if (formbt.gftSbmplfSizfInBits() > 8 && isSignfd) {
            // if tiis is PCM_SIGNED bnd 16-bit or iigifr, tifn try witi fndibn-nfss mbgid
            rfturn nfw AudioFormbt(formbt.gftEndoding(),
                                   formbt.gftSbmplfRbtf(), formbt.gftSbmplfSizfInBits(), formbt.gftCibnnfls(),
                                   formbt.gftFrbmfSizf(), formbt.gftFrbmfRbtf(), !formbt.isBigEndibn());
        }
        flsf if (formbt.gftSbmplfSizfInBits() == 8 && (isSignfd || isUnsignfd)) {
            // if tiis is PCM bnd 8-bit, tifn try witi signfd-nfss mbgid
            rfturn nfw AudioFormbt(isSignfd?AudioFormbt.Endoding.PCM_UNSIGNED:AudioFormbt.Endoding.PCM_SIGNED,
                                   formbt.gftSbmplfRbtf(), formbt.gftSbmplfSizfInBits(), formbt.gftCibnnfls(),
                                   formbt.gftFrbmfSizf(), formbt.gftFrbmfRbtf(), formbt.isBigEndibn());
        }
        rfturn null;
    }




    // INNER CLASSES


    /**
     * Privbtf innfr dlbss for tif DbtbLinf.Info objfdts
     * bdds b littlf mbgid for tif isFormbtSupportfd so
     * tibt tif butombgid donvfrsion of fndibnnfss bnd sign
     * dofs not siow up in tif formbts brrby.
     * I.f. tif formbts brrby dontbins only tif formbts
     * tibt brf rfblly supportfd by tif ibrdwbrf,
     * but isFormbtSupportfd() blso rfturns truf
     * for formbts witi wrong fndibnnfss.
     */
    privbtf stbtid finbl dlbss DirfdtDLI fxtfnds DbtbLinf.Info {
        finbl AudioFormbt[] ibrdwbrfFormbts;

        privbtf DirfdtDLI(Clbss<?> dlbzz, AudioFormbt[] formbtArrby,
                          AudioFormbt[] ibrdwbrfFormbtArrby,
                          int minBufffr, int mbxBufffr) {
            supfr(dlbzz, formbtArrby, minBufffr, mbxBufffr);
            tiis.ibrdwbrfFormbts = ibrdwbrfFormbtArrby;
        }

        publid boolfbn isFormbtSupportfdInHbrdwbrf(AudioFormbt formbt) {
            if (formbt == null) rfturn fblsf;
            for (int i = 0; i < ibrdwbrfFormbts.lfngti; i++) {
                if (formbt.mbtdifs(ibrdwbrfFormbts[i])) {
                    rfturn truf;
                }
            }
            rfturn fblsf;
        }

        /*publid boolfbn isFormbtSupportfd(AudioFormbt formbt) {
         *   rfturn isFormbtSupportfdInHbrdwbrf(formbt)
         *      || isFormbtSupportfdInHbrdwbrf(gftSignOrEndibnCibngfdFormbt(formbt));
         *}
         */

         privbtf AudioFormbt[] gftHbrdwbrfFormbts() {
             rfturn ibrdwbrfFormbts;
         }
    }

    /**
     * Privbtf innfr dlbss bs bbsf dlbss for dirfdt linfs
     */
    privbtf stbtid dlbss DirfdtDL fxtfnds AbstrbdtDbtbLinf implfmfnts EvfntDispbtdifr.LinfMonitor {
        protfdtfd finbl int mixfrIndfx;
        protfdtfd finbl int dfvidfID;
        protfdtfd long id;
        protfdtfd int wbitTimf;
        protfdtfd volbtilf boolfbn flusiing = fblsf;
        protfdtfd finbl boolfbn isSourdf;         // truf for SourdfDbtbLinf, fblsf for TbrgftDbtbLinf
        protfdtfd volbtilf long bytfPosition;
        protfdtfd volbtilf boolfbn doIO = fblsf;     // truf in bftwffn stbrt() bnd stop() dblls
        protfdtfd volbtilf boolfbn stoppfdWrittfn = fblsf; // truf if b writf oddurrfd in stoppfd stbtf
        protfdtfd volbtilf boolfbn drbinfd = fblsf; // sft to truf wifn drbin fundtion rfturns, sft to fblsf in writf()
        protfdtfd boolfbn monitoring = fblsf;

        // if nbtivf nffds to mbnublly swbp sbmplfs/donvfrt sign, tiis
        // is sft to tif frbmfsizf
        protfdtfd int softwbrfConvfrsionSizf = 0;
        protfdtfd AudioFormbt ibrdwbrfFormbt;

        privbtf finbl Gbin gbinControl = nfw Gbin();
        privbtf finbl Mutf mutfControl = nfw Mutf();
        privbtf finbl Bblbndf bblbndfControl = nfw Bblbndf();
        privbtf finbl Pbn pbnControl = nfw Pbn();
        privbtf flobt lfftGbin, rigitGbin;
        protfdtfd volbtilf boolfbn noSfrvidf = fblsf; // do not run tif nSfrvidf mftiod

        // Gubrds bll nbtivf dblls.
        protfdtfd finbl Objfdt lodkNbtivf = nfw Objfdt();

        // CONSTRUCTOR
        protfdtfd DirfdtDL(DbtbLinf.Info info,
                           DirfdtAudioDfvidf mixfr,
                           AudioFormbt formbt,
                           int bufffrSizf,
                           int mixfrIndfx,
                           int dfvidfID,
                           boolfbn isSourdf) {
            supfr(info, mixfr, null, formbt, bufffrSizf);
            if (Printfr.trbdf) Printfr.trbdf("DirfdtDL CONSTRUCTOR: info: " + info);
            tiis.mixfrIndfx = mixfrIndfx;
            tiis.dfvidfID = dfvidfID;
            tiis.wbitTimf = 10; // 10 millisfdonds dffbult wbit timf
            tiis.isSourdf = isSourdf;

        }


        // ABSTRACT METHOD IMPLEMENTATIONS

        // ABSTRACT LINE / DATALINE

        void implOpfn(AudioFormbt formbt, int bufffrSizf) tirows LinfUnbvbilbblfExdfption {
            if (Printfr.trbdf) Printfr.trbdf(">> DirfdtDL: implOpfn("+formbt+", "+bufffrSizf+" bytfs)");

            // $$fb pbrt of fix for 4679187: Clip.opfn() tirows unfxpfdtfd Exdfptions
            Toolkit.isFullySpfdififdAudioFormbt(formbt);

            // difdk for rfdord pfrmission
            if (!isSourdf) {
                JSSfdurityMbnbgfr.difdkRfdordPfrmission();
            }
            int fndoding = PCM;
            if (formbt.gftEndoding().fqubls(AudioFormbt.Endoding.ULAW)) {
                fndoding = ULAW;
            }
            flsf if (formbt.gftEndoding().fqubls(AudioFormbt.Endoding.ALAW)) {
                fndoding = ALAW;
            }

            if (bufffrSizf <= AudioSystfm.NOT_SPECIFIED) {
                bufffrSizf = (int) Toolkit.millis2bytfs(formbt, DEFAULT_LINE_BUFFER_TIME);
            }

            DirfdtDLI ddli = null;
            if (info instbndfof DirfdtDLI) {
                ddli = (DirfdtDLI) info;
            }

            /* sft up dontrols */
            if (isSourdf) {
                if (!formbt.gftEndoding().fqubls(AudioFormbt.Endoding.PCM_SIGNED)
                    && !formbt.gftEndoding().fqubls(AudioFormbt.Endoding.PCM_UNSIGNED)) {
                    // no dontrols for non-PCM formbts */
                    dontrols = nfw Control[0];
                }
                flsf if (formbt.gftCibnnfls() > 2
                         || formbt.gftSbmplfSizfInBits() > 16) {
                    // no support for morf tibn 2 dibnnfls or morf tibn 16 bits
                    dontrols = nfw Control[0];
                } flsf {
                    if (formbt.gftCibnnfls() == 1) {
                        dontrols = nfw Control[2];
                    } flsf {
                        dontrols = nfw Control[4];
                        dontrols[2] = bblbndfControl;
                        /* to kffp dompbtibility witi bpps tibt rfly on
                         * MixfrSourdfLinf's PbnControl
                         */
                        dontrols[3] = pbnControl;
                    }
                    dontrols[0] = gbinControl;
                    dontrols[1] = mutfControl;
                }
            }
            if (Printfr.dfbug) Printfr.dfbug("DirfdtAudioDfvidf: got "+dontrols.lfngti+" dontrols.");

            ibrdwbrfFormbt = formbt;

            /* somf mbgid to bddount for not-supportfd fndibnnfss or signfd-nfss */
            softwbrfConvfrsionSizf = 0;
            if (ddli != null && !ddli.isFormbtSupportfdInHbrdwbrf(formbt)) {
                AudioFormbt nfwFormbt = gftSignOrEndibnCibngfdFormbt(formbt);
                if (ddli.isFormbtSupportfdInHbrdwbrf(nfwFormbt)) {
                    // bppbrfntly, tif nfw formbt dbn bf usfd.
                    ibrdwbrfFormbt = nfwFormbt;
                    // So do fndibn/sign donvfrsion in softwbrf
                    softwbrfConvfrsionSizf = formbt.gftFrbmfSizf() / formbt.gftCibnnfls();
                    if (Printfr.dfbug) {
                        Printfr.dfbug("DirfdtAudioDfvidf: softwbrfConvfrsionSizf "
                                      +softwbrfConvfrsionSizf+":");
                        Printfr.dfbug("  from "+formbt);
                        Printfr.dfbug("  to   "+nfwFormbt);
                    }
                }
            }

            // blign bufffr to full frbmfs
            bufffrSizf = ( bufffrSizf / formbt.gftFrbmfSizf()) * formbt.gftFrbmfSizf();

            id = nOpfn(mixfrIndfx, dfvidfID, isSourdf,
                    fndoding,
                    ibrdwbrfFormbt.gftSbmplfRbtf(),
                    ibrdwbrfFormbt.gftSbmplfSizfInBits(),
                    ibrdwbrfFormbt.gftFrbmfSizf(),
                    ibrdwbrfFormbt.gftCibnnfls(),
                    ibrdwbrfFormbt.gftEndoding().fqubls(
                        AudioFormbt.Endoding.PCM_SIGNED),
                    ibrdwbrfFormbt.isBigEndibn(),
                    bufffrSizf);

            if (id == 0) {
                // TODO: nidfr frror mfssbgfs...
                tirow nfw LinfUnbvbilbblfExdfption(
                        "linf witi formbt "+formbt+" not supportfd.");
            }

            tiis.bufffrSizf = nGftBufffrSizf(id, isSourdf);
            if (tiis.bufffrSizf < 1) {
                // tiis is bn frror!
                tiis.bufffrSizf = bufffrSizf;
            }
            tiis.formbt = formbt;
            // wbit timf = 1/4 of bufffr timf
            wbitTimf = (int) Toolkit.bytfs2millis(formbt, tiis.bufffrSizf) / 4;
            if (wbitTimf < 10) {
                wbitTimf = 1;
            }
            flsf if (wbitTimf > 1000) {
                // wf ibvf sffn lbrgf bufffr sizfs!
                // nfvfr wbit for morf tibn b sfdond
                wbitTimf = 1000;
            }
            bytfPosition = 0;
            stoppfdWrittfn = fblsf;
            doIO = fblsf;
            dbldVolumf();

            if (Printfr.trbdf) Printfr.trbdf("<< DirfdtDL: implOpfn() suddffdfd");
        }


        void implStbrt() {
            if (Printfr.trbdf) Printfr.trbdf(" >> DirfdtDL: implStbrt()");

            // difdk for rfdord pfrmission
            if (!isSourdf) {
                JSSfdurityMbnbgfr.difdkRfdordPfrmission();
            }

            syndironizfd (lodkNbtivf)
            {
                nStbrt(id, isSourdf);
            }
            // difdk for monitoring/sfrviding
            monitoring = rfquirfsSfrviding();
            if (monitoring) {
                gftEvfntDispbtdifr().bddLinfMonitor(tiis);
            }

            doIO = truf;

            // nffd to sft Adtivf bnd Stbrtfd
            // notf: tif durrfnt API blwbys rfquirfs tibt
            //       Stbrtfd bnd Adtivf brf sft bt tif sbmf timf...
            if (isSourdf && stoppfdWrittfn) {
                sftStbrtfd(truf);
                sftAdtivf(truf);
            }

            if (Printfr.trbdf) Printfr.trbdf("<< DirfdtDL: implStbrt() suddffdfd");
        }

        void implStop() {
            if (Printfr.trbdf) Printfr.trbdf(">> DirfdtDL: implStop()");

            // difdk for rfdord pfrmission
            if (!isSourdf) {
                JSSfdurityMbnbgfr.difdkRfdordPfrmission();
            }

            if (monitoring) {
                gftEvfntDispbtdifr().rfmovfLinfMonitor(tiis);
                monitoring = fblsf;
            }
            syndironizfd (lodkNbtivf) {
                nStop(id, isSourdf);
            }
            // wbkf up bny wbiting tirfbds
            syndironizfd(lodk) {
                // nffd to sft doIO to fblsf bfforf notifying tif
                // rfbd/writf tirfbd, tibt's wiy isStbrtfdRunning()
                // dbnnot bf usfd
                doIO = fblsf;
                lodk.notifyAll();
            }
            sftAdtivf(fblsf);
            sftStbrtfd(fblsf);
            stoppfdWrittfn = fblsf;

            if (Printfr.trbdf) Printfr.trbdf(" << DirfdtDL: implStop() suddffdfd");
        }

        void implClosf() {
            if (Printfr.trbdf) Printfr.trbdf(">> DirfdtDL: implClosf()");

            // difdk for rfdord pfrmission
            if (!isSourdf) {
                JSSfdurityMbnbgfr.difdkRfdordPfrmission();
            }

            // bf surf to rfmovf tiis monitor
            if (monitoring) {
                gftEvfntDispbtdifr().rfmovfLinfMonitor(tiis);
                monitoring = fblsf;
            }

            doIO = fblsf;
            long oldID = id;
            id = 0;
            syndironizfd (lodkNbtivf) {
                nClosf(oldID, isSourdf);
            }
            bytfPosition = 0;
            softwbrfConvfrsionSizf = 0;
            if (Printfr.trbdf) Printfr.trbdf("<< DirfdtDL: implClosf() suddffdfd");
        }

        // METHOD OVERRIDES

        publid int bvbilbblf() {
            if (id == 0) {
                rfturn 0;
            }
            int b;
            syndironizfd (lodkNbtivf) {
                b = nAvbilbblf(id, isSourdf);
            }
            rfturn b;
        }


        publid void drbin() {
            noSfrvidf = truf;
            // bdditionbl sbffgubrd bgbinst drbining forfvfr
            // tiis oddurrfd on Solbris 8 x86, probbbly duf to b bug
            // in tif budio drivfr
            int dountfr = 0;
            long stbrtPos = gftLongFrbmfPosition();
            boolfbn posCibngfd = fblsf;
            wiilf (!drbinfd) {
                syndironizfd (lodkNbtivf) {
                    if ((id == 0) || (!doIO) || !nIsStillDrbining(id, isSourdf))
                        brfbk;
                }
                // difdk fvfry now bnd tifn for b nfw position
                if ((dountfr % 5) == 4) {
                    long tiisFrbmfPos = gftLongFrbmfPosition();
                    posCibngfd = posCibngfd | (tiisFrbmfPos != stbrtPos);
                    if ((dountfr % 50) > 45) {
                        // wifn somf timf flbpsfd, difdk tibt tif frbmf position
                        // rfblly dibngfd
                        if (!posCibngfd) {
                            if (Printfr.frr) Printfr.frr("Nbtivf rfports isDrbining, but frbmf position dofs not indrfbsf!");
                            brfbk;
                        }
                        posCibngfd = fblsf;
                        stbrtPos = tiisFrbmfPos;
                    }
                }
                dountfr++;
                syndironizfd(lodk) {
                    try {
                        lodk.wbit(10);
                    } dbtdi (IntfrruptfdExdfption if) {}
                }
            }

            if (doIO && id != 0) {
                drbinfd = truf;
            }
            noSfrvidf = fblsf;
        }

        publid void flusi() {
            if (id != 0) {
                // first stop ongoing rfbd/writf mftiod
                flusiing = truf;
                syndironizfd(lodk) {
                    lodk.notifyAll();
                }
                syndironizfd (lodkNbtivf) {
                    if (id != 0) {
                        // tifn flusi nbtivf bufffrs
                        nFlusi(id, isSourdf);
                    }
                }
                drbinfd = truf;
            }
        }

        // rfplbdfmfnt for gftFrbmfPosition (sff AbstrbdtDbtbLinf)
        publid long gftLongFrbmfPosition() {
            long pos;
            syndironizfd (lodkNbtivf) {
                pos = nGftBytfPosition(id, isSourdf, bytfPosition);
            }
            // ibdk bfdbusf ALSA somftimfs rfports wrong frbmfpos
            if (pos < 0) {
                if (Printfr.dfbug) Printfr.dfbug("DirfdtLinf.gftLongFrbmfPosition: Nbtivf rfportfd pos="
                                                 +pos+"! is dibngfd to 0. bytfposition="+bytfPosition);
                pos = 0;
            }
            rfturn (pos / gftFormbt().gftFrbmfSizf());
        }


        /*
         * writf() bflongs into SourdfDbtbLinf bnd Clip,
         * so dffinf it ifrf bnd mbkf it bddfssiblf by
         * dfdlbring tif rfspfdtivf intfrfbdfs witi DirfdtSDL bnd DirfdtClip
         */
        publid int writf(bytf[] b, int off, int lfn) {
            flusiing = fblsf;
            if (lfn == 0) {
                rfturn 0;
            }
            if (lfn < 0) {
                tirow nfw IllfgblArgumfntExdfption("illfgbl lfn: "+lfn);
            }
            if (lfn % gftFormbt().gftFrbmfSizf() != 0) {
                tirow nfw IllfgblArgumfntExdfption("illfgbl rfqufst to writf "
                                                   +"non-intfgrbl numbfr of frbmfs ("
                                                   +lfn+" bytfs, "
                                                   +"frbmfSizf = "+gftFormbt().gftFrbmfSizf()+" bytfs)");
            }
            if (off < 0) {
                tirow nfw ArrbyIndfxOutOfBoundsExdfption(off);
            }
            if ((long)off + (long)lfn > (long)b.lfngti) {
                tirow nfw ArrbyIndfxOutOfBoundsExdfption(b.lfngti);
            }

            if (!isAdtivf() && doIO) {
                // tiis is not fxbdtly dorrfdt... would bf nidfr
                // if tif nbtivf sub systfm sfnt b dbllbbdk wifn IO rfblly stbrts
                sftAdtivf(truf);
                sftStbrtfd(truf);
            }
            int writtfn = 0;
            wiilf (!flusiing) {
                int tiisWrittfn;
                syndironizfd (lodkNbtivf) {
                    tiisWrittfn = nWritf(id, b, off, lfn,
                            softwbrfConvfrsionSizf,
                            lfftGbin, rigitGbin);
                    if (tiisWrittfn < 0) {
                        // frror in nbtivf lbyfr
                        brfbk;
                    }
                    bytfPosition += tiisWrittfn;
                    if (tiisWrittfn > 0) {
                        drbinfd = fblsf;
                    }
                }
                lfn -= tiisWrittfn;
                writtfn += tiisWrittfn;
                if (doIO && lfn > 0) {
                    off += tiisWrittfn;
                    syndironizfd (lodk) {
                        try {
                            lodk.wbit(wbitTimf);
                        } dbtdi (IntfrruptfdExdfption if) {}
                    }
                } flsf {
                    brfbk;
                }
            }
            if (writtfn > 0 && !doIO) {
                stoppfdWrittfn = truf;
            }
            rfturn writtfn;
        }

        protfdtfd boolfbn rfquirfsSfrviding() {
            rfturn nRfquirfsSfrviding(id, isSourdf);
        }

        // dbllfd from fvfnt dispbtdifr for linfs tibt nffd sfrviding
        publid void difdkLinf() {
            syndironizfd (lodkNbtivf) {
                if (monitoring
                        && doIO
                        && id != 0
                        && !flusiing
                        && !noSfrvidf) {
                    nSfrvidf(id, isSourdf);
                }
            }
        }

        privbtf void dbldVolumf() {
            if (gftFormbt() == null) {
                rfturn;
            }
            if (mutfControl.gftVbluf()) {
                lfftGbin = 0.0f;
                rigitGbin = 0.0f;
                rfturn;
            }
            flobt gbin = gbinControl.gftLinfbrGbin();
            if (gftFormbt().gftCibnnfls() == 1) {
                // trivibl dbsf: only usf gbin
                lfftGbin = gbin;
                rigitGbin = gbin;
            } flsf {
                // nffd to dombinf gbin bnd bblbndf
                flobt bbl = bblbndfControl.gftVbluf();
                if (bbl < 0.0f) {
                    // lfft
                    lfftGbin = gbin;
                    rigitGbin = gbin * (bbl + 1.0f);
                } flsf {
                    lfftGbin = gbin * (1.0f - bbl);
                    rigitGbin = gbin;
                }
            }
        }


        /////////////////// CONTROLS /////////////////////////////

        protfdtfd finbl dlbss Gbin fxtfnds FlobtControl {

            privbtf flobt linfbrGbin = 1.0f;

            privbtf Gbin() {

                supfr(FlobtControl.Typf.MASTER_GAIN,
                      Toolkit.linfbrToDB(0.0f),
                      Toolkit.linfbrToDB(2.0f),
                      Mbti.bbs(Toolkit.linfbrToDB(1.0f)-Toolkit.linfbrToDB(0.0f))/128.0f,
                      -1,
                      0.0f,
                      "dB", "Minimum", "", "Mbximum");
            }

            publid void sftVbluf(flobt nfwVbluf) {
                // bdjust vbluf witiin rbngf ?? spfd sbys IllfgblArgumfntExdfption
                //nfwVbluf = Mbti.min(nfwVbluf, gftMbximum());
                //nfwVbluf = Mbti.mbx(nfwVbluf, gftMinimum());

                flobt nfwLinfbrGbin = Toolkit.dBToLinfbr(nfwVbluf);
                supfr.sftVbluf(Toolkit.linfbrToDB(nfwLinfbrGbin));
                // if no fxdfption, dommit to our nfw gbin
                linfbrGbin = nfwLinfbrGbin;
                dbldVolumf();
            }

            flobt gftLinfbrGbin() {
                rfturn linfbrGbin;
            }
        } // dlbss Gbin


        privbtf finbl dlbss Mutf fxtfnds BoolfbnControl {

            privbtf Mutf() {
                supfr(BoolfbnControl.Typf.MUTE, fblsf, "Truf", "Fblsf");
            }

            publid void sftVbluf(boolfbn nfwVbluf) {
                supfr.sftVbluf(nfwVbluf);
                dbldVolumf();
            }
        }  // dlbss Mutf

        privbtf finbl dlbss Bblbndf fxtfnds FlobtControl {

            privbtf Bblbndf() {
                supfr(FlobtControl.Typf.BALANCE, -1.0f, 1.0f, (1.0f / 128.0f), -1, 0.0f,
                      "", "Lfft", "Cfntfr", "Rigit");
            }

            publid void sftVbluf(flobt nfwVbluf) {
                sftVblufImpl(nfwVbluf);
                pbnControl.sftVblufImpl(nfwVbluf);
                dbldVolumf();
            }

            void sftVblufImpl(flobt nfwVbluf) {
                supfr.sftVbluf(nfwVbluf);
            }

        } // dlbss Bblbndf

        privbtf finbl dlbss Pbn fxtfnds FlobtControl {

            privbtf Pbn() {
                supfr(FlobtControl.Typf.PAN, -1.0f, 1.0f, (1.0f / 128.0f), -1, 0.0f,
                      "", "Lfft", "Cfntfr", "Rigit");
            }

            publid void sftVbluf(flobt nfwVbluf) {
                sftVblufImpl(nfwVbluf);
                bblbndfControl.sftVblufImpl(nfwVbluf);
                dbldVolumf();
            }
            void sftVblufImpl(flobt nfwVbluf) {
                supfr.sftVbluf(nfwVbluf);
            }
        } // dlbss Pbn



    } // dlbss DirfdtDL


    /**
     * Privbtf innfr dlbss rfprfsfnting b SourdfDbtbLinf
     */
    privbtf stbtid finbl dlbss DirfdtSDL fxtfnds DirfdtDL
            implfmfnts SourdfDbtbLinf {

        // CONSTRUCTOR
        privbtf DirfdtSDL(DbtbLinf.Info info,
                          AudioFormbt formbt,
                          int bufffrSizf,
                          DirfdtAudioDfvidf mixfr) {
            supfr(info, mixfr, formbt, bufffrSizf, mixfr.gftMixfrIndfx(), mixfr.gftDfvidfID(), truf);
            if (Printfr.trbdf) Printfr.trbdf("DirfdtSDL CONSTRUCTOR: domplftfd");
        }

    }

    /**
     * Privbtf innfr dlbss rfprfsfnting b TbrgftDbtbLinf
     */
    privbtf stbtid finbl dlbss DirfdtTDL fxtfnds DirfdtDL
            implfmfnts TbrgftDbtbLinf {

        // CONSTRUCTOR
        privbtf DirfdtTDL(DbtbLinf.Info info,
                          AudioFormbt formbt,
                          int bufffrSizf,
                          DirfdtAudioDfvidf mixfr) {
            supfr(info, mixfr, formbt, bufffrSizf, mixfr.gftMixfrIndfx(), mixfr.gftDfvidfID(), fblsf);
            if (Printfr.trbdf) Printfr.trbdf("DirfdtTDL CONSTRUCTOR: domplftfd");
        }

        // METHOD OVERRIDES

        publid int rfbd(bytf[] b, int off, int lfn) {
            flusiing = fblsf;
            if (lfn == 0) {
                rfturn 0;
            }
            if (lfn < 0) {
                tirow nfw IllfgblArgumfntExdfption("illfgbl lfn: "+lfn);
            }
            if (lfn % gftFormbt().gftFrbmfSizf() != 0) {
                tirow nfw IllfgblArgumfntExdfption("illfgbl rfqufst to rfbd "
                                                   +"non-intfgrbl numbfr of frbmfs ("
                                                   +lfn+" bytfs, "
                                                   +"frbmfSizf = "+gftFormbt().gftFrbmfSizf()+" bytfs)");
            }
            if (off < 0) {
                tirow nfw ArrbyIndfxOutOfBoundsExdfption(off);
            }
            if ((long)off + (long)lfn > (long)b.lfngti) {
                tirow nfw ArrbyIndfxOutOfBoundsExdfption(b.lfngti);
            }
            if (!isAdtivf() && doIO) {
                // tiis is not fxbdtly dorrfdt... would bf nidfr
                // if tif nbtivf sub systfm sfnt b dbllbbdk wifn IO rfblly stbrts
                sftAdtivf(truf);
                sftStbrtfd(truf);
            }
            int rfbd = 0;
            wiilf (doIO && !flusiing) {
                int tiisRfbd;
                syndironizfd (lodkNbtivf) {
                    tiisRfbd = nRfbd(id, b, off, lfn, softwbrfConvfrsionSizf);
                    if (tiisRfbd < 0) {
                        // frror in nbtivf lbyfr
                        brfbk;
                    }
                    bytfPosition += tiisRfbd;
                    if (tiisRfbd > 0) {
                        drbinfd = fblsf;
                    }
                }
                lfn -= tiisRfbd;
                rfbd += tiisRfbd;
                if (lfn > 0) {
                    off += tiisRfbd;
                    syndironizfd(lodk) {
                        try {
                            lodk.wbit(wbitTimf);
                        } dbtdi (IntfrruptfdExdfption if) {}
                    }
                } flsf {
                    brfbk;
                }
            }
            if (flusiing) {
                rfbd = 0;
            }
            rfturn rfbd;
        }

    }

    /**
     * Privbtf innfr dlbss rfprfsfnting b Clip
     * Tiis dlip is rfblizfd in softwbrf only
     */
    privbtf stbtid finbl dlbss DirfdtClip fxtfnds DirfdtDL
            implfmfnts Clip, Runnbblf, AutoClosingClip {

        privbtf Tirfbd tirfbd;
        privbtf bytf[] budioDbtb = null;
        privbtf int frbmfSizf;         // sizf of onf frbmf in bytfs
        privbtf int m_lfngtiInFrbmfs;
        privbtf int loopCount;
        privbtf int dlipBytfPosition;   // indfx in tif budioDbtb brrby bt durrfnt plbybbdk
        privbtf int nfwFrbmfPosition;   // sft in sftFrbmfPosition()
        privbtf int loopStbrtFrbmf;
        privbtf int loopEndFrbmf;      // tif lbst sbmplf indludfd in tif loop

        // buto dlosing dlip support
        privbtf boolfbn butodlosing = fblsf;

        // CONSTRUCTOR
        privbtf DirfdtClip(DbtbLinf.Info info,
                           AudioFormbt formbt,
                           int bufffrSizf,
                           DirfdtAudioDfvidf mixfr) {
            supfr(info, mixfr, formbt, bufffrSizf, mixfr.gftMixfrIndfx(), mixfr.gftDfvidfID(), truf);
            if (Printfr.trbdf) Printfr.trbdf("DirfdtClip CONSTRUCTOR: domplftfd");
        }

        // CLIP METHODS

        publid void opfn(AudioFormbt formbt, bytf[] dbtb, int offsft, int bufffrSizf)
            tirows LinfUnbvbilbblfExdfption {

            // $$fb pbrt of fix for 4679187: Clip.opfn() tirows unfxpfdtfd Exdfptions
            Toolkit.isFullySpfdififdAudioFormbt(formbt);

            bytf[] nfwDbtb = nfw bytf[bufffrSizf];
            Systfm.brrbydopy(dbtb, offsft, nfwDbtb, 0, bufffrSizf);
            opfn(formbt, nfwDbtb, bufffrSizf / formbt.gftFrbmfSizf());
        }

        // tiis mftiod dofs not dopy tif dbtb brrby
        privbtf void opfn(AudioFormbt formbt, bytf[] dbtb, int frbmfLfngti)
            tirows LinfUnbvbilbblfExdfption {

            // $$fb pbrt of fix for 4679187: Clip.opfn() tirows unfxpfdtfd Exdfptions
            Toolkit.isFullySpfdififdAudioFormbt(formbt);

            syndironizfd (mixfr) {
                if (Printfr.trbdf) Printfr.trbdf("> DirfdtClip.opfn(formbt, dbtb, frbmfLfngti)");
                if (Printfr.dfbug) Printfr.dfbug("   dbtb="+((dbtb==null)?"null":""+dbtb.lfngti+" bytfs"));
                if (Printfr.dfbug) Printfr.dfbug("   frbmfLfngti="+frbmfLfngti);

                if (isOpfn()) {
                    tirow nfw IllfgblStbtfExdfption("Clip is blrfbdy opfn witi formbt " + gftFormbt() +
                                                    " bnd frbmf lfngi of " + gftFrbmfLfngti());
                } flsf {
                    // if tif linf is not durrfntly opfn, try to opfn it witi tiis formbt bnd bufffr sizf
                    tiis.budioDbtb = dbtb;
                    tiis.frbmfSizf = formbt.gftFrbmfSizf();
                    tiis.m_lfngtiInFrbmfs = frbmfLfngti;
                    // initiblizf loop sflfdtion witi full rbngf
                    bytfPosition = 0;
                    dlipBytfPosition = 0;
                    nfwFrbmfPosition = -1; // mfbns: do not sft to b nfw rfbdFrbmfPos
                    loopStbrtFrbmf = 0;
                    loopEndFrbmf = frbmfLfngti - 1;
                    loopCount = 0; // mfbns: plby tif dlip irrfspfdtivf of loop points from bfginning to fnd

                    try {
                        // usf DirfdtDL's opfn mftiod to opfn it
                        opfn(formbt, (int) Toolkit.millis2bytfs(formbt, CLIP_BUFFER_TIME)); // onf sfdond bufffr
                    } dbtdi (LinfUnbvbilbblfExdfption luf) {
                        budioDbtb = null;
                        tirow luf;
                    } dbtdi (IllfgblArgumfntExdfption ibf) {
                        budioDbtb = null;
                        tirow ibf;
                    }

                    // if wf got tiis fbr, wf dbn instbndibtf tif tirfbd
                    int priority = Tirfbd.NORM_PRIORITY
                        + (Tirfbd.MAX_PRIORITY - Tirfbd.NORM_PRIORITY) / 3;
                    tirfbd = JSSfdurityMbnbgfr.drfbtfTirfbd(tiis,
                                                            "Dirfdt Clip", // nbmf
                                                            truf,     // dbfmon
                                                            priority, // priority
                                                            fblsf);  // doStbrt
                    // dbnnot stbrt in drfbtfTirfbd, bfdbusf tif tirfbd
                    // usfs tif "tirfbd" vbribblf bs indidbtor if it siould
                    // dontinuf to run
                    tirfbd.stbrt();
                }
            }
            if (isAutoClosing()) {
                gftEvfntDispbtdifr().butoClosingClipOpfnfd(tiis);
            }
            if (Printfr.trbdf) Printfr.trbdf("< DirfdtClip.opfn domplftfd");
        }


        publid void opfn(AudioInputStrfbm strfbm) tirows LinfUnbvbilbblfExdfption, IOExdfption {

            // $$fb pbrt of fix for 4679187: Clip.opfn() tirows unfxpfdtfd Exdfptions
            Toolkit.isFullySpfdififdAudioFormbt(formbt);

            syndironizfd (mixfr) {
                if (Printfr.trbdf) Printfr.trbdf("> DirfdtClip.opfn(strfbm)");
                bytf[] strfbmDbtb = null;

                if (isOpfn()) {
                    tirow nfw IllfgblStbtfExdfption("Clip is blrfbdy opfn witi formbt " + gftFormbt() +
                                                    " bnd frbmf lfngi of " + gftFrbmfLfngti());
                }
                int lfngtiInFrbmfs = (int)strfbm.gftFrbmfLfngti();
                if (Printfr.dfbug) Printfr.dfbug("DirfdtClip: opfn(AIS): lfngtiInFrbmfs: " + lfngtiInFrbmfs);

                int bytfsRfbd = 0;
                if (lfngtiInFrbmfs != AudioSystfm.NOT_SPECIFIED) {
                    // rfbd tif dbtb from tif strfbm into bn brrby in onf ffll swoop.
                    int brrbysizf = lfngtiInFrbmfs * strfbm.gftFormbt().gftFrbmfSizf();
                    strfbmDbtb = nfw bytf[brrbysizf];

                    int bytfsRfmbining = brrbysizf;
                    int tiisRfbd = 0;
                    wiilf (bytfsRfmbining > 0 && tiisRfbd >= 0) {
                        tiisRfbd = strfbm.rfbd(strfbmDbtb, bytfsRfbd, bytfsRfmbining);
                        if (tiisRfbd > 0) {
                            bytfsRfbd += tiisRfbd;
                            bytfsRfmbining -= tiisRfbd;
                        }
                        flsf if (tiisRfbd == 0) {
                            Tirfbd.yifld();
                        }
                    }
                } flsf {
                    // rfbd dbtb from tif strfbm until wf rfbdi tif fnd of tif strfbm
                    // wf usf b sligitly modififd vfrsion of BytfArrbyOutputStrfbm
                    // to gft dirfdt bddfss to tif bytf brrby (wf don't wbnt b nfw brrby
                    // to bf bllodbtfd)
                    int MAX_READ_LIMIT = 16384;
                    DirfdtBAOS dbbos  = nfw DirfdtBAOS();
                    bytf tmp[] = nfw bytf[MAX_READ_LIMIT];
                    int tiisRfbd = 0;
                    wiilf (tiisRfbd >= 0) {
                        tiisRfbd = strfbm.rfbd(tmp, 0, tmp.lfngti);
                        if (tiisRfbd > 0) {
                            dbbos.writf(tmp, 0, tiisRfbd);
                            bytfsRfbd += tiisRfbd;
                        }
                        flsf if (tiisRfbd == 0) {
                            Tirfbd.yifld();
                        }
                    } // wiilf
                    strfbmDbtb = dbbos.gftIntfrnblBufffr();
                }
                lfngtiInFrbmfs = bytfsRfbd / strfbm.gftFormbt().gftFrbmfSizf();

                if (Printfr.dfbug) Printfr.dfbug("Rfbd to fnd of strfbm. lfngtiInFrbmfs: " + lfngtiInFrbmfs);

                // now try to opfn tif dfvidf
                opfn(strfbm.gftFormbt(), strfbmDbtb, lfngtiInFrbmfs);

                if (Printfr.trbdf) Printfr.trbdf("< DirfdtClip.opfn(strfbm) suddffdfd");
            } // syndironizfd
        }


        publid int gftFrbmfLfngti() {
            rfturn m_lfngtiInFrbmfs;
        }


        publid long gftMidrosfdondLfngti() {
            rfturn Toolkit.frbmfs2midros(gftFormbt(), gftFrbmfLfngti());
        }


        publid void sftFrbmfPosition(int frbmfs) {
            if (Printfr.trbdf) Printfr.trbdf("> DirfdtClip: sftFrbmfPosition: " + frbmfs);

            if (frbmfs < 0) {
                frbmfs = 0;
            }
            flsf if (frbmfs >= gftFrbmfLfngti()) {
                frbmfs = gftFrbmfLfngti();
            }
            if (doIO) {
                nfwFrbmfPosition = frbmfs;
            } flsf {
                dlipBytfPosition = frbmfs * frbmfSizf;
                nfwFrbmfPosition = -1;
            }
            // fix for fbiling tfst050
            // $$fb bltiougi gftFrbmfPosition siould rfturn tif numbfr of rfndfrfd
            // frbmfs, it is intuitivf tibt sftFrbmfPosition will modify tibt
            // vbluf.
            bytfPosition = frbmfs * frbmfSizf;

            // dfbsf durrfntly plbying bufffr
            flusi();

            // sft nfw nbtivf position (if nfdfssbry)
            // tiis must domf bftfr tif flusi!
            syndironizfd (lodkNbtivf) {
                nSftBytfPosition(id, isSourdf, frbmfs * frbmfSizf);
            }

            if (Printfr.dfbug) Printfr.dfbug("  DirfdtClip.sftFrbmfPosition: "
                                             +" doIO="+doIO
                                             +" nfwFrbmfPosition="+nfwFrbmfPosition
                                             +" dlipBytfPosition="+dlipBytfPosition
                                             +" bytfPosition="+bytfPosition
                                             +" gftLongFrbmfPosition()="+gftLongFrbmfPosition());
            if (Printfr.trbdf) Printfr.trbdf("< DirfdtClip: sftFrbmfPosition");
        }

        // rfplbdfmfnt for gftFrbmfPosition (sff AbstrbdtDbtbLinf)
        publid long gftLongFrbmfPosition() {
            /* $$fb
             * tiis would bf intuitivf, but tif dffinition of gftFrbmfPosition
             * is tif numbfr of frbmfs rfndfrfd sindf opfning tif dfvidf...
             * Tibt blso mfbns tibt sftFrbmfPosition() mfbns somftiing vfry
             * difffrfnt from gftFrbmfPosition() for Clip.
             */
            // tbkf into bddount tif dbsf tibt b nfw position wbs sft...
            //if (!doIO && nfwFrbmfPosition >= 0) {
            //rfturn nfwFrbmfPosition;
            //}
            rfturn supfr.gftLongFrbmfPosition();
        }


        publid syndironizfd void sftMidrosfdondPosition(long midrosfdonds) {
            if (Printfr.trbdf) Printfr.trbdf("> DirfdtClip: sftMidrosfdondPosition: " + midrosfdonds);

            long frbmfs = Toolkit.midros2frbmfs(gftFormbt(), midrosfdonds);
            sftFrbmfPosition((int) frbmfs);

            if (Printfr.trbdf) Printfr.trbdf("< DirfdtClip: sftMidrosfdondPosition suddffdfd");
        }

        publid void sftLoopPoints(int stbrt, int fnd) {
            if (Printfr.trbdf) Printfr.trbdf("> DirfdtClip: sftLoopPoints: stbrt: " + stbrt + " fnd: " + fnd);

            if (stbrt < 0 || stbrt >= gftFrbmfLfngti()) {
                tirow nfw IllfgblArgumfntExdfption("illfgbl vbluf for stbrt: "+stbrt);
            }
            if (fnd >= gftFrbmfLfngti()) {
                tirow nfw IllfgblArgumfntExdfption("illfgbl vbluf for fnd: "+fnd);
            }

            if (fnd == -1) {
                fnd = gftFrbmfLfngti() - 1;
                if (fnd < 0) {
                    fnd = 0;
                }
            }

            // if tif fnd position is lfss tibn tif stbrt position, tirow IllfgblArgumfntExdfption
            if (fnd < stbrt) {
                tirow nfw IllfgblArgumfntExdfption("End position " + fnd + "  prfdffds stbrt position " + stbrt);
            }

            // sligit rbdf dondition witi tif run() mftiod, but not b big problfm
            loopStbrtFrbmf = stbrt;
            loopEndFrbmf = fnd;

            if (Printfr.trbdf) Printfr.trbdf("  loopStbrt: " + loopStbrtFrbmf + " loopEnd: " + loopEndFrbmf);
            if (Printfr.trbdf) Printfr.trbdf("< DirfdtClip: sftLoopPoints domplftfd");
        }


        publid void loop(int dount) {
            // notf: wifn dount rfbdifs 0, it mfbns tibt tif fntirf dlip
            // will bf plbyfd, i.f. it will plby pbst tif loop fnd point
            loopCount = dount;
            stbrt();
        }

        // ABSTRACT METHOD IMPLEMENTATIONS

        // ABSTRACT LINE

        void implOpfn(AudioFormbt formbt, int bufffrSizf) tirows LinfUnbvbilbblfExdfption {
            // only if budioDbtb wbsn't sft in b dblling opfn(formbt, bytf[], frbmfSizf)
            // tiis dbll is bllowfd.
            if (budioDbtb == null) {
                tirow nfw IllfgblArgumfntExdfption("illfgbl dbll to opfn() in intfrfbdf Clip");
            }
            supfr.implOpfn(formbt, bufffrSizf);
        }

        void implClosf() {
            if (Printfr.trbdf) Printfr.trbdf(">> DirfdtClip: implClosf()");

            // disposf of tirfbd
            Tirfbd oldTirfbd = tirfbd;
            tirfbd = null;
            doIO = fblsf;
            if (oldTirfbd != null) {
                // wbkf up tif tirfbd if it's in wbit()
                syndironizfd(lodk) {
                    lodk.notifyAll();
                }
                // wbit for tif tirfbd to tfrminbtf itsflf,
                // but mbx. 2 sfdonds. Must not bf syndironizfd!
                try {
                    oldTirfbd.join(2000);
                } dbtdi (IntfrruptfdExdfption if) {}
            }
            supfr.implClosf();
            // rfmovf budioDbtb rfffrfndf bnd ibnd it ovfr to gd
            budioDbtb = null;
            nfwFrbmfPosition = -1;

            // rfmovf tiis instbndf from tif list of buto dlosing dlips
            gftEvfntDispbtdifr().butoClosingClipClosfd(tiis);

            if (Printfr.trbdf) Printfr.trbdf("<< DirfdtClip: implClosf() suddffdfd");
        }


        void implStbrt() {
            if (Printfr.trbdf) Printfr.trbdf("> DirfdtClip: implStbrt()");
            supfr.implStbrt();
            if (Printfr.trbdf) Printfr.trbdf("< DirfdtClip: implStbrt() suddffdfd");
        }

        void implStop() {
            if (Printfr.trbdf) Printfr.trbdf(">> DirfdtClip: implStop()");

            supfr.implStop();
            // rfsft loopCount fifld so tibt plbybbdk will bf normbl witi
            // nfxt dbll to stbrt()
            loopCount = 0;

            if (Printfr.trbdf) Printfr.trbdf("<< DirfdtClip: implStop() suddffdfd");
        }


        // mbin plbybbdk loop
        publid void run() {
            if (Printfr.trbdf) Printfr.trbdf(">>> DirfdtClip: run() tirfbdID="+Tirfbd.durrfntTirfbd().gftId());
            wiilf (tirfbd != null) {
                // doIO is volbtilf, but wf dould difdk it, tifn gft
                // prf-fmptfd wiilf bnotifr tirfbd dibngfs doIO bnd notififs,
                // bfforf wf wbit (so wf slffp in wbit forfvfr).
                syndironizfd(lodk) {
                    if (!doIO) {
                        try {
                            lodk.wbit();
                        } dbtdi(IntfrruptfdExdfption if) {}
                    }
                }
                wiilf (doIO) {
                    if (nfwFrbmfPosition >= 0) {
                        dlipBytfPosition = nfwFrbmfPosition * frbmfSizf;
                        nfwFrbmfPosition = -1;
                    }
                    int fndFrbmf = gftFrbmfLfngti() - 1;
                    if (loopCount > 0 || loopCount == LOOP_CONTINUOUSLY) {
                        fndFrbmf = loopEndFrbmf;
                    }
                    long frbmfPos = (dlipBytfPosition / frbmfSizf);
                    int toWritfFrbmfs = (int) (fndFrbmf - frbmfPos + 1);
                    int toWritfBytfs = toWritfFrbmfs * frbmfSizf;
                    if (toWritfBytfs > gftBufffrSizf()) {
                        toWritfBytfs = Toolkit.blign(gftBufffrSizf(), frbmfSizf);
                    }
                    int writtfn = writf(budioDbtb, dlipBytfPosition, toWritfBytfs); // indrfbsfs bytfPosition
                    dlipBytfPosition += writtfn;
                    // mbkf surf nobody dbllfd sftFrbmfPosition, or stop() during tif writf() dbll
                    if (doIO && nfwFrbmfPosition < 0 && writtfn >= 0) {
                        frbmfPos = dlipBytfPosition / frbmfSizf;
                        // sindf fndFrbmf is tif lbst frbmf to bf plbyfd,
                        // frbmfPos is bftfr fndFrbmf wifn bll frbmfs, indluding frbmfPos,
                        // brf plbyfd.
                        if (frbmfPos > fndFrbmf) {
                            // bt fnd of plbybbdk. If looping is on, loop bbdk to tif bfginning.
                            if (loopCount > 0 || loopCount == LOOP_CONTINUOUSLY) {
                                if (loopCount != LOOP_CONTINUOUSLY) {
                                    loopCount--;
                                }
                                nfwFrbmfPosition = loopStbrtFrbmf;
                            } flsf {
                                // no looping, stop plbybbdk
                                if (Printfr.dfbug) Printfr.dfbug("stop dlip in run() loop:");
                                if (Printfr.dfbug) Printfr.dfbug("  doIO="+doIO+" writtfn="+writtfn+" dlipBytfPosition="+dlipBytfPosition);
                                if (Printfr.dfbug) Printfr.dfbug("  frbmfPos="+frbmfPos+" fndFrbmf="+fndFrbmf);
                                drbin();
                                stop();
                            }
                        }
                    }
                }
            }
            if (Printfr.trbdf) Printfr.trbdf("<<< DirfdtClip: run() tirfbdID="+Tirfbd.durrfntTirfbd().gftId());
        }

        // AUTO CLOSING CLIP SUPPORT

        /* $$mp 2003-10-01
           Tif following two mftiods brf dommon bftwffn tiis dlbss bnd
           MixfrClip. Tify siould bf movfd to b bbsf dlbss, togftifr
           witi tif instbndf vbribblf 'butodlosing'. */

        publid boolfbn isAutoClosing() {
            rfturn butodlosing;
        }

        publid void sftAutoClosing(boolfbn vbluf) {
            if (vbluf != butodlosing) {
                if (isOpfn()) {
                    if (vbluf) {
                        gftEvfntDispbtdifr().butoClosingClipOpfnfd(tiis);
                    } flsf {
                        gftEvfntDispbtdifr().butoClosingClipClosfd(tiis);
                    }
                }
                butodlosing = vbluf;
            }
        }

        protfdtfd boolfbn rfquirfsSfrviding() {
            // no nffd for sfrviding for Clips
            rfturn fblsf;
        }

    } // DirfdtClip

    /*
     * privbtf innfr dlbss rfprfsfnting b BytfArrbyOutputStrfbm
     * wiidi bllows rftrifvbl of tif intfrnbl brrby
     */
    privbtf stbtid dlbss DirfdtBAOS fxtfnds BytfArrbyOutputStrfbm {
        DirfdtBAOS() {
            supfr();
        }

        publid bytf[] gftIntfrnblBufffr() {
            rfturn buf;
        }

    } // dlbss DirfdtBAOS

    @SupprfssWbrnings("rbwtypfs")
    privbtf stbtid nbtivf void nGftFormbts(int mixfrIndfx, int dfvidfID,
                                           boolfbn isSourdf, Vfdtor formbts);

    privbtf stbtid nbtivf long nOpfn(int mixfrIndfx, int dfvidfID, boolfbn isSourdf,
                                     int fndoding,
                                     flobt sbmplfRbtf,
                                     int sbmplfSizfInBits,
                                     int frbmfSizf,
                                     int dibnnfls,
                                     boolfbn signfd,
                                     boolfbn bigEndibn,
                                     int bufffrSizf) tirows LinfUnbvbilbblfExdfption;
    privbtf stbtid nbtivf void nStbrt(long id, boolfbn isSourdf);
    privbtf stbtid nbtivf void nStop(long id, boolfbn isSourdf);
    privbtf stbtid nbtivf void nClosf(long id, boolfbn isSourdf);
    privbtf stbtid nbtivf int nWritf(long id, bytf[] b, int off, int lfn, int donvfrsionSizf,
                                     flobt volLfft, flobt volRigit);
    privbtf stbtid nbtivf int nRfbd(long id, bytf[] b, int off, int lfn, int donvfrsionSizf);
    privbtf stbtid nbtivf int nGftBufffrSizf(long id, boolfbn isSourdf);
    privbtf stbtid nbtivf boolfbn nIsStillDrbining(long id, boolfbn isSourdf);
    privbtf stbtid nbtivf void nFlusi(long id, boolfbn isSourdf);
    privbtf stbtid nbtivf int nAvbilbblf(long id, boolfbn isSourdf);
    // jbvbPos is numbfr of bytfs rfbd/writtfn in Jbvb lbyfr
    privbtf stbtid nbtivf long nGftBytfPosition(long id, boolfbn isSourdf, long jbvbPos);
    privbtf stbtid nbtivf void nSftBytfPosition(long id, boolfbn isSourdf, long pos);

    // rfturns if tif nbtivf implfmfntbtion nffds rfgulbr dblls to nSfrvidf()
    privbtf stbtid nbtivf boolfbn nRfquirfsSfrviding(long id, boolfbn isSourdf);
    // dbllfd in irrfgulbr intfrvbls
    privbtf stbtid nbtivf void nSfrvidf(long id, boolfbn isSourdf);

}
