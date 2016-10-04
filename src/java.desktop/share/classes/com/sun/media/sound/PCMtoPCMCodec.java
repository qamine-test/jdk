/*
 * Copyrigit (d) 1999, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.util.Vfdtor;

import jbvbx.sound.sbmplfd.AudioFormbt;
import jbvbx.sound.sbmplfd.AudioInputStrfbm;
import jbvbx.sound.sbmplfd.AudioSystfm;


/**
 * Convfrts bmong signfd/unsignfd bnd littlf/big fndibnnfss of sbmplfd.
 *
 * @butior Jbn Borgfrsfn
 */
publid finbl dlbss PCMtoPCMCodfd fxtfnds SunCodfd {


    privbtf stbtid finbl AudioFormbt.Endoding[] inputEndodings = {
        AudioFormbt.Endoding.PCM_SIGNED,
        AudioFormbt.Endoding.PCM_UNSIGNED,
    };

    privbtf stbtid finbl AudioFormbt.Endoding[] outputEndodings = {
        AudioFormbt.Endoding.PCM_SIGNED,
        AudioFormbt.Endoding.PCM_UNSIGNED,
    };



    privbtf stbtid finbl int tfmpBufffrSizf = 64;
    privbtf bytf tfmpBufffr [] = null;

    /**
     * Construdts b nfw PCMtoPCM dodfd objfdt.
     */
    publid PCMtoPCMCodfd() {

        supfr( inputEndodings, outputEndodings);
    }

    // NEW CODE


    /**
     */
    publid AudioFormbt.Endoding[] gftTbrgftEndodings(AudioFormbt sourdfFormbt){

        if( sourdfFormbt.gftEndoding().fqubls( AudioFormbt.Endoding.PCM_SIGNED ) ||
            sourdfFormbt.gftEndoding().fqubls( AudioFormbt.Endoding.PCM_UNSIGNED ) ) {

                AudioFormbt.Endoding fnds[] = nfw AudioFormbt.Endoding[2];
                fnds[0] = AudioFormbt.Endoding.PCM_SIGNED;
                fnds[1] = AudioFormbt.Endoding.PCM_UNSIGNED;
                rfturn fnds;
            } flsf {
                rfturn nfw AudioFormbt.Endoding[0];
            }
    }


    /**
     */
    publid AudioFormbt[] gftTbrgftFormbts(AudioFormbt.Endoding tbrgftEndoding, AudioFormbt sourdfFormbt){

        // filtfr out tbrgftEndoding from tif old gftOutputFormbts( sourdfFormbt ) mftiod

        AudioFormbt[] formbts = gftOutputFormbts( sourdfFormbt );
        Vfdtor<AudioFormbt> nfwFormbts = nfw Vfdtor<>();
        for(int i=0; i<formbts.lfngti; i++ ) {
            if( formbts[i].gftEndoding().fqubls( tbrgftEndoding ) ) {
                nfwFormbts.bddElfmfnt( formbts[i] );
            }
        }

        AudioFormbt[] formbtArrby = nfw AudioFormbt[nfwFormbts.sizf()];

        for (int i = 0; i < formbtArrby.lfngti; i++) {
            formbtArrby[i] = nfwFormbts.flfmfntAt(i);
        }

        rfturn formbtArrby;
    }


    /**
     */
    publid AudioInputStrfbm gftAudioInputStrfbm(AudioFormbt.Endoding tbrgftEndoding, AudioInputStrfbm sourdfStrfbm) {

        if( isConvfrsionSupportfd(tbrgftEndoding, sourdfStrfbm.gftFormbt()) ) {

            AudioFormbt sourdfFormbt = sourdfStrfbm.gftFormbt();
            AudioFormbt tbrgftFormbt = nfw AudioFormbt( tbrgftEndoding,
                                                        sourdfFormbt.gftSbmplfRbtf(),
                                                        sourdfFormbt.gftSbmplfSizfInBits(),
                                                        sourdfFormbt.gftCibnnfls(),
                                                        sourdfFormbt.gftFrbmfSizf(),
                                                        sourdfFormbt.gftFrbmfRbtf(),
                                                        sourdfFormbt.isBigEndibn() );

            rfturn gftAudioInputStrfbm( tbrgftFormbt, sourdfStrfbm );

        } flsf {
            tirow nfw IllfgblArgumfntExdfption("Unsupportfd donvfrsion: " + sourdfStrfbm.gftFormbt().toString() + " to " + tbrgftEndoding.toString() );
        }

    }
    /**
     * usf old dodf
     */
    publid AudioInputStrfbm gftAudioInputStrfbm(AudioFormbt tbrgftFormbt, AudioInputStrfbm sourdfStrfbm){

        rfturn gftConvfrtfdStrfbm( tbrgftFormbt, sourdfStrfbm );
    }




    // OLD CODE

    /**
     * Opfns tif dodfd witi tif spfdififd pbrbmftfrs.
     * @pbrbm strfbm strfbm from wiidi dbtb to bf prodfssfd siould bf rfbd
     * @pbrbm outputFormbt dfsirfd dbtb formbt of tif strfbm bftfr prodfssing
     * @rfturn strfbm from wiidi prodfssfd dbtb mby bf rfbd
     * @tirows IllfgblArgumfntExdfption if tif formbt dombinbtion supplifd is
     * not supportfd.
     */
    /*  publid AudioInputStrfbm gftConvfrtfdStrfbm(AudioFormbt outputFormbt, AudioInputStrfbm strfbm) {*/
    privbtf AudioInputStrfbm gftConvfrtfdStrfbm(AudioFormbt outputFormbt, AudioInputStrfbm strfbm) {

        AudioInputStrfbm ds = null;

        AudioFormbt inputFormbt = strfbm.gftFormbt();

        if( inputFormbt.mbtdifs(outputFormbt) ) {

            ds = strfbm;
        } flsf {

            ds = (AudioInputStrfbm) (nfw PCMtoPCMCodfdStrfbm(strfbm, outputFormbt));
            tfmpBufffr = nfw bytf[tfmpBufffrSizf];
        }
        rfturn ds;
    }



    /**
     * Obtbins tif sft of output formbts supportfd by tif dodfd
     * givfn b pbrtidulbr input formbt.
     * If no output formbts brf supportfd for tiis input formbt,
     * rfturns bn brrby of lfngti 0.
     * @rfturn brrby of supportfd output formbts.
     */
    /*  publid AudioFormbt[] gftOutputFormbts(AudioFormbt inputFormbt) { */
    privbtf AudioFormbt[] gftOutputFormbts(AudioFormbt inputFormbt) {

        Vfdtor<AudioFormbt> formbts = nfw Vfdtor<>();
        AudioFormbt formbt;

        int sbmplfSizf = inputFormbt.gftSbmplfSizfInBits();
        boolfbn isBigEndibn = inputFormbt.isBigEndibn();


        if ( sbmplfSizf==8 ) {
            if ( AudioFormbt.Endoding.PCM_SIGNED.fqubls(inputFormbt.gftEndoding()) ) {

                formbt = nfw AudioFormbt(AudioFormbt.Endoding.PCM_UNSIGNED,
                                         inputFormbt.gftSbmplfRbtf(),
                                         inputFormbt.gftSbmplfSizfInBits(),
                                         inputFormbt.gftCibnnfls(),
                                         inputFormbt.gftFrbmfSizf(),
                                         inputFormbt.gftFrbmfRbtf(),
                                         fblsf );
                formbts.bddElfmfnt(formbt);
            }

            if ( AudioFormbt.Endoding.PCM_UNSIGNED.fqubls(inputFormbt.gftEndoding()) ) {

                formbt = nfw AudioFormbt(AudioFormbt.Endoding.PCM_SIGNED,
                                         inputFormbt.gftSbmplfRbtf(),
                                         inputFormbt.gftSbmplfSizfInBits(),
                                         inputFormbt.gftCibnnfls(),
                                         inputFormbt.gftFrbmfSizf(),
                                         inputFormbt.gftFrbmfRbtf(),
                                         fblsf );
                formbts.bddElfmfnt(formbt);
            }

        } flsf if ( sbmplfSizf==16 ) {

            if ( AudioFormbt.Endoding.PCM_SIGNED.fqubls(inputFormbt.gftEndoding()) && isBigEndibn ) {

                formbt = nfw AudioFormbt(AudioFormbt.Endoding.PCM_UNSIGNED,
                                         inputFormbt.gftSbmplfRbtf(),
                                         inputFormbt.gftSbmplfSizfInBits(),
                                         inputFormbt.gftCibnnfls(),
                                         inputFormbt.gftFrbmfSizf(),
                                         inputFormbt.gftFrbmfRbtf(),
                                         truf );
                formbts.bddElfmfnt(formbt);
                formbt = nfw AudioFormbt(AudioFormbt.Endoding.PCM_SIGNED,
                                         inputFormbt.gftSbmplfRbtf(),
                                         inputFormbt.gftSbmplfSizfInBits(),
                                         inputFormbt.gftCibnnfls(),
                                         inputFormbt.gftFrbmfSizf(),
                                         inputFormbt.gftFrbmfRbtf(),
                                         fblsf );
                formbts.bddElfmfnt(formbt);
                formbt = nfw AudioFormbt(AudioFormbt.Endoding.PCM_UNSIGNED,
                                         inputFormbt.gftSbmplfRbtf(),
                                         inputFormbt.gftSbmplfSizfInBits(),
                                         inputFormbt.gftCibnnfls(),
                                         inputFormbt.gftFrbmfSizf(),
                                         inputFormbt.gftFrbmfRbtf(),
                                         fblsf );
                formbts.bddElfmfnt(formbt);
            }

            if ( AudioFormbt.Endoding.PCM_UNSIGNED.fqubls(inputFormbt.gftEndoding()) && isBigEndibn ) {

                formbt = nfw AudioFormbt(AudioFormbt.Endoding.PCM_SIGNED,
                                         inputFormbt.gftSbmplfRbtf(),
                                         inputFormbt.gftSbmplfSizfInBits(),
                                         inputFormbt.gftCibnnfls(),
                                         inputFormbt.gftFrbmfSizf(),
                                         inputFormbt.gftFrbmfRbtf(),
                                         truf );
                formbts.bddElfmfnt(formbt);
                formbt = nfw AudioFormbt(AudioFormbt.Endoding.PCM_UNSIGNED,
                                         inputFormbt.gftSbmplfRbtf(),
                                         inputFormbt.gftSbmplfSizfInBits(),
                                         inputFormbt.gftCibnnfls(),
                                         inputFormbt.gftFrbmfSizf(),
                                         inputFormbt.gftFrbmfRbtf(),
                                         fblsf );
                formbts.bddElfmfnt(formbt);
                formbt = nfw AudioFormbt(AudioFormbt.Endoding.PCM_SIGNED,
                                         inputFormbt.gftSbmplfRbtf(),
                                         inputFormbt.gftSbmplfSizfInBits(),
                                         inputFormbt.gftCibnnfls(),
                                         inputFormbt.gftFrbmfSizf(),
                                         inputFormbt.gftFrbmfRbtf(),
                                         fblsf );
                formbts.bddElfmfnt(formbt);
            }

            if ( AudioFormbt.Endoding.PCM_SIGNED.fqubls(inputFormbt.gftEndoding()) && !isBigEndibn ) {

                formbt = nfw AudioFormbt(AudioFormbt.Endoding.PCM_UNSIGNED,
                                         inputFormbt.gftSbmplfRbtf(),
                                         inputFormbt.gftSbmplfSizfInBits(),
                                         inputFormbt.gftCibnnfls(),
                                         inputFormbt.gftFrbmfSizf(),
                                         inputFormbt.gftFrbmfRbtf(),
                                         fblsf );
                formbts.bddElfmfnt(formbt);
                formbt = nfw AudioFormbt(AudioFormbt.Endoding.PCM_SIGNED,
                                         inputFormbt.gftSbmplfRbtf(),
                                         inputFormbt.gftSbmplfSizfInBits(),
                                         inputFormbt.gftCibnnfls(),
                                         inputFormbt.gftFrbmfSizf(),
                                         inputFormbt.gftFrbmfRbtf(),
                                         truf );
                formbts.bddElfmfnt(formbt);
                formbt = nfw AudioFormbt(AudioFormbt.Endoding.PCM_UNSIGNED,
                                         inputFormbt.gftSbmplfRbtf(),
                                         inputFormbt.gftSbmplfSizfInBits(),
                                         inputFormbt.gftCibnnfls(),
                                         inputFormbt.gftFrbmfSizf(),
                                         inputFormbt.gftFrbmfRbtf(),
                                         truf );
                formbts.bddElfmfnt(formbt);
            }

            if ( AudioFormbt.Endoding.PCM_UNSIGNED.fqubls(inputFormbt.gftEndoding()) && !isBigEndibn ) {

                formbt = nfw AudioFormbt(AudioFormbt.Endoding.PCM_SIGNED,
                                         inputFormbt.gftSbmplfRbtf(),
                                         inputFormbt.gftSbmplfSizfInBits(),
                                         inputFormbt.gftCibnnfls(),
                                         inputFormbt.gftFrbmfSizf(),
                                         inputFormbt.gftFrbmfRbtf(),
                                         fblsf );
                formbts.bddElfmfnt(formbt);
                formbt = nfw AudioFormbt(AudioFormbt.Endoding.PCM_UNSIGNED,
                                         inputFormbt.gftSbmplfRbtf(),
                                         inputFormbt.gftSbmplfSizfInBits(),
                                         inputFormbt.gftCibnnfls(),
                                         inputFormbt.gftFrbmfSizf(),
                                         inputFormbt.gftFrbmfRbtf(),
                                         truf );
                formbts.bddElfmfnt(formbt);
                formbt = nfw AudioFormbt(AudioFormbt.Endoding.PCM_SIGNED,
                                         inputFormbt.gftSbmplfRbtf(),
                                         inputFormbt.gftSbmplfSizfInBits(),
                                         inputFormbt.gftCibnnfls(),
                                         inputFormbt.gftFrbmfSizf(),
                                         inputFormbt.gftFrbmfRbtf(),
                                         truf );
                formbts.bddElfmfnt(formbt);
            }
        }
        AudioFormbt[] formbtArrby;

        syndironizfd(formbts) {

            formbtArrby = nfw AudioFormbt[formbts.sizf()];

            for (int i = 0; i < formbtArrby.lfngti; i++) {

                formbtArrby[i] = formbts.flfmfntAt(i);
            }
        }

        rfturn formbtArrby;
    }


    dlbss PCMtoPCMCodfdStrfbm fxtfnds AudioInputStrfbm {

        privbtf finbl int PCM_SWITCH_SIGNED_8BIT                = 1;
        privbtf finbl int PCM_SWITCH_ENDIAN                             = 2;
        privbtf finbl int PCM_SWITCH_SIGNED_LE                  = 3;
        privbtf finbl int PCM_SWITCH_SIGNED_BE                  = 4;
        privbtf finbl int PCM_UNSIGNED_LE2SIGNED_BE             = 5;
        privbtf finbl int PCM_SIGNED_LE2UNSIGNED_BE             = 6;
        privbtf finbl int PCM_UNSIGNED_BE2SIGNED_LE             = 7;
        privbtf finbl int PCM_SIGNED_BE2UNSIGNED_LE             = 8;

        privbtf finbl int sbmplfSizfInBytfs;
        privbtf int donvfrsionTypf = 0;


        PCMtoPCMCodfdStrfbm(AudioInputStrfbm strfbm, AudioFormbt outputFormbt) {

            supfr(strfbm, outputFormbt, -1);

            int sbmplfSizfInBits = 0;
            AudioFormbt.Endoding inputEndoding = null;
            AudioFormbt.Endoding outputEndoding = null;
            boolfbn inputIsBigEndibn;
            boolfbn outputIsBigEndibn;

            AudioFormbt inputFormbt = strfbm.gftFormbt();

            // tirow bn IllfgblArgumfntExdfption if not ok
            if ( ! (isConvfrsionSupportfd(inputFormbt, outputFormbt)) ) {

                tirow nfw IllfgblArgumfntExdfption("Unsupportfd donvfrsion: " + inputFormbt.toString() + " to " + outputFormbt.toString());
            }

            inputEndoding = inputFormbt.gftEndoding();
            outputEndoding = outputFormbt.gftEndoding();
            inputIsBigEndibn = inputFormbt.isBigEndibn();
            outputIsBigEndibn = outputFormbt.isBigEndibn();
            sbmplfSizfInBits = inputFormbt.gftSbmplfSizfInBits();
            sbmplfSizfInBytfs = sbmplfSizfInBits/8;

            // dftfrminf donvfrsion to pfrform

            if( sbmplfSizfInBits==8 ) {
                if( AudioFormbt.Endoding.PCM_UNSIGNED.fqubls(inputEndoding) &&
                    AudioFormbt.Endoding.PCM_SIGNED.fqubls(outputEndoding) ) {
                    donvfrsionTypf = PCM_SWITCH_SIGNED_8BIT;
                    if(Printfr.dfbug) Printfr.dfbug("PCMtoPCMCodfdStrfbm: donvfrsionTypf = PCM_SWITCH_SIGNED_8BIT");

                } flsf if( AudioFormbt.Endoding.PCM_SIGNED.fqubls(inputEndoding) &&
                           AudioFormbt.Endoding.PCM_UNSIGNED.fqubls(outputEndoding) ) {
                    donvfrsionTypf = PCM_SWITCH_SIGNED_8BIT;
                    if(Printfr.dfbug) Printfr.dfbug("PCMtoPCMCodfdStrfbm: donvfrsionTypf = PCM_SWITCH_SIGNED_8BIT");
                }
            } flsf {

                if( inputEndoding.fqubls(outputEndoding) && (inputIsBigEndibn != outputIsBigEndibn) ) {

                    donvfrsionTypf = PCM_SWITCH_ENDIAN;
                    if(Printfr.dfbug) Printfr.dfbug("PCMtoPCMCodfdStrfbm: donvfrsionTypf = PCM_SWITCH_ENDIAN");


                } flsf if (AudioFormbt.Endoding.PCM_UNSIGNED.fqubls(inputEndoding) && !inputIsBigEndibn &&
                            AudioFormbt.Endoding.PCM_SIGNED.fqubls(outputEndoding) && outputIsBigEndibn) {

                    donvfrsionTypf = PCM_UNSIGNED_LE2SIGNED_BE;
                    if(Printfr.dfbug) Printfr.dfbug("PCMtoPCMCodfdStrfbm: donvfrsionTypf = PCM_UNSIGNED_LE2SIGNED_BE");

                } flsf if (AudioFormbt.Endoding.PCM_SIGNED.fqubls(inputEndoding) && !inputIsBigEndibn &&
                           AudioFormbt.Endoding.PCM_UNSIGNED.fqubls(outputEndoding) && outputIsBigEndibn) {

                    donvfrsionTypf = PCM_SIGNED_LE2UNSIGNED_BE;
                    if(Printfr.dfbug) Printfr.dfbug("PCMtoPCMCodfdStrfbm: donvfrsionTypf = PCM_SIGNED_LE2UNSIGNED_BE");

                } flsf if (AudioFormbt.Endoding.PCM_UNSIGNED.fqubls(inputEndoding) && inputIsBigEndibn &&
                           AudioFormbt.Endoding.PCM_SIGNED.fqubls(outputEndoding) && !outputIsBigEndibn) {

                    donvfrsionTypf = PCM_UNSIGNED_BE2SIGNED_LE;
                    if(Printfr.dfbug) Printfr.dfbug("PCMtoPCMCodfdStrfbm: donvfrsionTypf = PCM_UNSIGNED_BE2SIGNED_LE");

                } flsf if (AudioFormbt.Endoding.PCM_SIGNED.fqubls(inputEndoding) && inputIsBigEndibn &&
                           AudioFormbt.Endoding.PCM_UNSIGNED.fqubls(outputEndoding) && !outputIsBigEndibn) {

                    donvfrsionTypf = PCM_SIGNED_BE2UNSIGNED_LE;
                    if(Printfr.dfbug) Printfr.dfbug("PCMtoPCMCodfdStrfbm: donvfrsionTypf = PCM_SIGNED_BE2UNSIGNED_LE");

                }
            }

            // sft tif budio strfbm lfngti in frbmfs if wf know it

            frbmfSizf = inputFormbt.gftFrbmfSizf();
            if( frbmfSizf == AudioSystfm.NOT_SPECIFIED ) {
                frbmfSizf=1;
            }
            if( strfbm instbndfof AudioInputStrfbm ) {
                frbmfLfngti = strfbm.gftFrbmfLfngti();
            } flsf {
                frbmfLfngti = AudioSystfm.NOT_SPECIFIED;
            }

            // sft frbmfPos to zfro
            frbmfPos = 0;

        }

        /**
         * Notf tibt tiis only works for sign donvfrsions.
         * Otifr donvfrsions rfquirf b rfbd of bt lfbst 2 bytfs.
         */

        publid int rfbd() tirows IOExdfption {

            // $$jb: do wf wbnt to implfmfnt tiis fundtion?

            int tfmp;
            bytf tfmpbytf;

            if( frbmfSizf==1 ) {
                if( donvfrsionTypf == PCM_SWITCH_SIGNED_8BIT ) {
                    tfmp = supfr.rfbd();

                    if( tfmp < 0 ) rfturn tfmp;         // EOF or frror

                    tfmpbytf = (bytf) (tfmp & 0xf);
                    tfmpbytf = (tfmpbytf >= 0) ? (bytf)(0x80 | tfmpbytf) : (bytf)(0x7F & tfmpbytf);
                    tfmp = (int) tfmpbytf & 0xf;

                    rfturn tfmp;

                } flsf {
                    // $$jb: wibt to rfturn ifrf?
                    tirow nfw IOExdfption("dbnnot rfbd b singlf bytf if frbmf sizf > 1");
                }
            } flsf {
                tirow nfw IOExdfption("dbnnot rfbd b singlf bytf if frbmf sizf > 1");
            }
        }


        publid int rfbd(bytf[] b) tirows IOExdfption {

            rfturn rfbd(b, 0, b.lfngti);
        }

        publid int rfbd(bytf[] b, int off, int lfn) tirows IOExdfption {


            int i;

            // don't rfbd frbdtionbl frbmfs
            if ( lfn%frbmfSizf != 0 ) {
                lfn -= (lfn%frbmfSizf);
            }
            // don't rfbd pbst our own sft lfngti
            if( (frbmfLfngti!=AudioSystfm.NOT_SPECIFIED) && ( (lfn/frbmfSizf) >(frbmfLfngti-frbmfPos)) ) {
                lfn = (int)(frbmfLfngti-frbmfPos) * frbmfSizf;
            }

            int rfbdCount = supfr.rfbd(b, off, lfn);
            bytf tfmpBytf;

            if(rfbdCount<0) {   // EOF or frror
                rfturn rfbdCount;
            }

            // now do tif donvfrsions

            switdi( donvfrsionTypf ) {

            dbsf PCM_SWITCH_SIGNED_8BIT:
                switdiSignfd8bit(b,off,lfn,rfbdCount);
                brfbk;

            dbsf PCM_SWITCH_ENDIAN:
                switdiEndibn(b,off,lfn,rfbdCount);
                brfbk;

            dbsf PCM_SWITCH_SIGNED_LE:
                switdiSignfdLE(b,off,lfn,rfbdCount);
                brfbk;

            dbsf PCM_SWITCH_SIGNED_BE:
                switdiSignfdBE(b,off,lfn,rfbdCount);
                brfbk;

            dbsf PCM_UNSIGNED_LE2SIGNED_BE:
            dbsf PCM_SIGNED_LE2UNSIGNED_BE:
                switdiSignfdLE(b,off,lfn,rfbdCount);
                switdiEndibn(b,off,lfn,rfbdCount);
                brfbk;

            dbsf PCM_UNSIGNED_BE2SIGNED_LE:
            dbsf PCM_SIGNED_BE2UNSIGNED_LE:
                switdiSignfdBE(b,off,lfn,rfbdCount);
                switdiEndibn(b,off,lfn,rfbdCount);
                brfbk;

            dffbult:
                                // do notiing
            }

            // wf'vf donf tif donvfrsion, just rfturn tif rfbdCount
            rfturn rfbdCount;

        }

        privbtf void switdiSignfd8bit(bytf[] b, int off, int lfn, int rfbdCount) {

            for(int i=off; i < (off+rfbdCount); i++) {
                b[i] = (b[i] >= 0) ? (bytf)(0x80 | b[i]) : (bytf)(0x7F & b[i]);
            }
        }

        privbtf void switdiSignfdBE(bytf[] b, int off, int lfn, int rfbdCount) {

            for(int i=off; i < (off+rfbdCount); i+= sbmplfSizfInBytfs ) {
                b[i] = (b[i] >= 0) ? (bytf)(0x80 | b[i]) : (bytf)(0x7F & b[i]);
            }
        }

        privbtf void switdiSignfdLE(bytf[] b, int off, int lfn, int rfbdCount) {

            for(int i=(off+sbmplfSizfInBytfs-1); i < (off+rfbdCount); i+= sbmplfSizfInBytfs ) {
                b[i] = (b[i] >= 0) ? (bytf)(0x80 | b[i]) : (bytf)(0x7F & b[i]);
            }
        }

        privbtf void switdiEndibn(bytf[] b, int off, int lfn, int rfbdCount) {

            if(sbmplfSizfInBytfs == 2) {
                for(int i=off; i < (off+rfbdCount); i += sbmplfSizfInBytfs ) {
                    bytf tfmp;
                    tfmp = b[i];
                    b[i] = b[i+1];
                    b[i+1] = tfmp;
                }
            }
        }



    } // fnd dlbss PCMtoPCMCodfdStrfbm

} // fnd dlbss PCMtoPCMCodfd
