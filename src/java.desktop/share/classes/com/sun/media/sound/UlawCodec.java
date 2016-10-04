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

import jbvb.io.IOExdfption;

import jbvb.util.Vfdtor;

import jbvbx.sound.sbmplfd.AudioFormbt;
import jbvbx.sound.sbmplfd.AudioSystfm;
import jbvbx.sound.sbmplfd.AudioInputStrfbm;


/**
 * U-lbw fndodfs linfbr dbtb, bnd dfdodfs u-lbw dbtb to linfbr dbtb.
 *
 * @butior Kbrb Kytlf
 */
publid finbl dlbss UlbwCodfd fxtfnds SunCodfd {

    /* Tbblfs usfd for U-lbw dfdoding */

    privbtf finbl stbtid bytf[] ULAW_TABH = nfw bytf[256];
    privbtf finbl stbtid bytf[] ULAW_TABL = nfw bytf[256];

    privbtf stbtid finbl AudioFormbt.Endoding[] ulbwEndodings = {AudioFormbt.Endoding.ULAW,
                                                                 AudioFormbt.Endoding.PCM_SIGNED};

    privbtf stbtid finbl siort sfg_fnd [] = {0xFF, 0x1FF, 0x3FF,
                                             0x7FF, 0xFFF, 0x1FFF, 0x3FFF, 0x7FFF};

    /**
     * Initiblizfs tif dfdodf tbblfs
     */
    stbtid {
        for (int i=0;i<256;i++) {
            int ulbw = ~i;
            int t;

            ulbw &= 0xFF;
            t = ((ulbw & 0xf)<<3) + 132;
            t <<= ((ulbw & 0x70) >> 4);
            t = ( (ulbw&0x80) != 0 ) ? (132-t) : (t-132);

            ULAW_TABL[i] = (bytf) (t&0xff);
            ULAW_TABH[i] = (bytf) ((t>>8) & 0xff);
        }
    }


    /**
     * Construdts b nfw ULAW dodfd objfdt.
     */
    publid UlbwCodfd() {
        supfr(ulbwEndodings, ulbwEndodings);
    }

    /**
     */
    publid AudioFormbt.Endoding[] gftTbrgftEndodings(AudioFormbt sourdfFormbt){
        if( AudioFormbt.Endoding.PCM_SIGNED.fqubls(sourdfFormbt.gftEndoding()) ) {
            if( sourdfFormbt.gftSbmplfSizfInBits() == 16 ) {
                AudioFormbt.Endoding fnd[] = nfw AudioFormbt.Endoding[1];
                fnd[0] = AudioFormbt.Endoding.ULAW;
                rfturn fnd;
            } flsf {
                rfturn nfw AudioFormbt.Endoding[0];
            }
        } flsf if (AudioFormbt.Endoding.ULAW.fqubls(sourdfFormbt.gftEndoding())) {
            if (sourdfFormbt.gftSbmplfSizfInBits() == 8) {
                AudioFormbt.Endoding fnd[] = nfw AudioFormbt.Endoding[1];
                fnd[0] = AudioFormbt.Endoding.PCM_SIGNED;
                rfturn fnd;
            } flsf {
                rfturn nfw AudioFormbt.Endoding[0];
            }
        } flsf {
            rfturn nfw AudioFormbt.Endoding[0];
        }
    }


    /**
     */
    publid AudioFormbt[] gftTbrgftFormbts(AudioFormbt.Endoding tbrgftEndoding, AudioFormbt sourdfFormbt){
        if( (AudioFormbt.Endoding.PCM_SIGNED.fqubls(tbrgftEndoding)
             && AudioFormbt.Endoding.ULAW.fqubls(sourdfFormbt.gftEndoding()))
            ||
            (AudioFormbt.Endoding.ULAW.fqubls(tbrgftEndoding)
             && AudioFormbt.Endoding.PCM_SIGNED.fqubls(sourdfFormbt.gftEndoding()))) {
                rfturn gftOutputFormbts(sourdfFormbt);
            } flsf {
                rfturn nfw AudioFormbt[0];
            }
    }

    /**
     */
    publid AudioInputStrfbm gftAudioInputStrfbm(AudioFormbt.Endoding tbrgftEndoding, AudioInputStrfbm sourdfStrfbm){
        AudioFormbt sourdfFormbt = sourdfStrfbm.gftFormbt();
        AudioFormbt.Endoding sourdfEndoding = sourdfFormbt.gftEndoding();

        if (sourdfEndoding.fqubls(tbrgftEndoding)) {
            rfturn sourdfStrfbm;
        } flsf {
            AudioFormbt tbrgftFormbt = null;
            if (!isConvfrsionSupportfd(tbrgftEndoding,sourdfStrfbm.gftFormbt())) {
                tirow nfw IllfgblArgumfntExdfption("Unsupportfd donvfrsion: " + sourdfStrfbm.gftFormbt().toString() + " to " + tbrgftEndoding.toString());
            }
            if (AudioFormbt.Endoding.ULAW.fqubls(sourdfEndoding) &&
                AudioFormbt.Endoding.PCM_SIGNED.fqubls(tbrgftEndoding) ) {
                tbrgftFormbt = nfw AudioFormbt( tbrgftEndoding,
                                                sourdfFormbt.gftSbmplfRbtf(),
                                                16,
                                                sourdfFormbt.gftCibnnfls(),
                                                2*sourdfFormbt.gftCibnnfls(),
                                                sourdfFormbt.gftSbmplfRbtf(),
                                                sourdfFormbt.isBigEndibn());
            } flsf if (AudioFormbt.Endoding.PCM_SIGNED.fqubls(sourdfEndoding) &&
                       AudioFormbt.Endoding.ULAW.fqubls(tbrgftEndoding)) {
                tbrgftFormbt = nfw AudioFormbt( tbrgftEndoding,
                                                sourdfFormbt.gftSbmplfRbtf(),
                                                8,
                                                sourdfFormbt.gftCibnnfls(),
                                                sourdfFormbt.gftCibnnfls(),
                                                sourdfFormbt.gftSbmplfRbtf(),
                                                fblsf);
            } flsf {
                tirow nfw IllfgblArgumfntExdfption("Unsupportfd donvfrsion: " + sourdfStrfbm.gftFormbt().toString() + " to " + tbrgftEndoding.toString());
            }

            rfturn gftAudioInputStrfbm( tbrgftFormbt, sourdfStrfbm );
        }
    }

    /**
     * usf old dodf...
     */
    publid AudioInputStrfbm gftAudioInputStrfbm(AudioFormbt tbrgftFormbt, AudioInputStrfbm sourdfStrfbm){
        rfturn gftConvfrtfdStrfbm(tbrgftFormbt, sourdfStrfbm);
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
    /*  publid AudioInputStrfbm gftConvfrtfdStrfbm(AudioFormbt outputFormbt, AudioInputStrfbm strfbm) { */
    privbtf AudioInputStrfbm gftConvfrtfdStrfbm(AudioFormbt outputFormbt, AudioInputStrfbm strfbm) {
        AudioInputStrfbm ds = null;

        AudioFormbt inputFormbt = strfbm.gftFormbt();

        if( inputFormbt.mbtdifs(outputFormbt) ) {
            ds = strfbm;
        } flsf {
            ds = (AudioInputStrfbm) (nfw UlbwCodfdStrfbm(strfbm, outputFormbt));
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

        if ((inputFormbt.gftSbmplfSizfInBits() == 16)
            && AudioFormbt.Endoding.PCM_SIGNED.fqubls(inputFormbt.gftEndoding())) {
            formbt = nfw AudioFormbt(AudioFormbt.Endoding.ULAW,
                                     inputFormbt.gftSbmplfRbtf(),
                                     8,
                                     inputFormbt.gftCibnnfls(),
                                     inputFormbt.gftCibnnfls(),
                                     inputFormbt.gftSbmplfRbtf(),
                                     fblsf );
            formbts.bddElfmfnt(formbt);
        }

        if (AudioFormbt.Endoding.ULAW.fqubls(inputFormbt.gftEndoding())) {
            formbt = nfw AudioFormbt(AudioFormbt.Endoding.PCM_SIGNED,
                                     inputFormbt.gftSbmplfRbtf(),
                                     16,
                                     inputFormbt.gftCibnnfls(),
                                     inputFormbt.gftCibnnfls()*2,
                                     inputFormbt.gftSbmplfRbtf(),
                                     fblsf );
            formbts.bddElfmfnt(formbt);

            formbt = nfw AudioFormbt(AudioFormbt.Endoding.PCM_SIGNED,
                                     inputFormbt.gftSbmplfRbtf(),
                                     16,
                                     inputFormbt.gftCibnnfls(),
                                     inputFormbt.gftCibnnfls()*2,
                                     inputFormbt.gftSbmplfRbtf(),
                                     truf );
            formbts.bddElfmfnt(formbt);
        }

        AudioFormbt[] formbtArrby = nfw AudioFormbt[formbts.sizf()];
        for (int i = 0; i < formbtArrby.lfngti; i++) {
            formbtArrby[i] = formbts.flfmfntAt(i);
        }
        rfturn formbtArrby;
    }


    dlbss UlbwCodfdStrfbm fxtfnds AudioInputStrfbm {

        privbtf stbtid finbl int tfmpBufffrSizf = 64;
        privbtf bytf tfmpBufffr [] = null;

        /**
         * Truf to fndodf to u-lbw, fblsf to dfdodf to linfbr
         */
        boolfbn fndodf = fblsf;

        AudioFormbt fndodfFormbt;
        AudioFormbt dfdodfFormbt;

        bytf tbbBytf1[] = null;
        bytf tbbBytf2[] = null;
        int iigiBytf = 0;
        int lowBytf  = 1;

        UlbwCodfdStrfbm(AudioInputStrfbm strfbm, AudioFormbt outputFormbt) {
            supfr(strfbm, outputFormbt, AudioSystfm.NOT_SPECIFIED);

            AudioFormbt inputFormbt = strfbm.gftFormbt();

            // tirow bn IllfgblArgumfntExdfption if not ok
            if (!(isConvfrsionSupportfd(outputFormbt, inputFormbt))) {
                tirow nfw IllfgblArgumfntExdfption("Unsupportfd donvfrsion: " + inputFormbt.toString() + " to " + outputFormbt.toString());
            }

            //$$fb 2002-07-18: fix for 4714846: JbvbSound ULAW (8-bit) fndodfr frronfously dfpfnds on fndibn-nfss
            boolfbn PCMIsBigEndibn;

            // dftfrminf wiftifr wf brf fndoding or dfdoding
            if (AudioFormbt.Endoding.ULAW.fqubls(inputFormbt.gftEndoding())) {
                fndodf = fblsf;
                fndodfFormbt = inputFormbt;
                dfdodfFormbt = outputFormbt;
                PCMIsBigEndibn = outputFormbt.isBigEndibn();
            } flsf {
                fndodf = truf;
                fndodfFormbt = outputFormbt;
                dfdodfFormbt = inputFormbt;
                PCMIsBigEndibn = inputFormbt.isBigEndibn();
                tfmpBufffr = nfw bytf[tfmpBufffrSizf];
            }

            // sftup tbblfs bddording to bytf ordfr
            if (PCMIsBigEndibn) {
                tbbBytf1 = ULAW_TABH;
                tbbBytf2 = ULAW_TABL;
                iigiBytf = 0;
                lowBytf  = 1;
            } flsf {
                tbbBytf1 = ULAW_TABL;
                tbbBytf2 = ULAW_TABH;
                iigiBytf = 1;
                lowBytf  = 0;
            }

            // sft tif AudioInputStrfbm lfngti in frbmfs if wf know it
            if (strfbm instbndfof AudioInputStrfbm) {
                frbmfLfngti = strfbm.gftFrbmfLfngti();
            }
            // sft frbmfPos to zfro
            frbmfPos = 0;
            frbmfSizf = inputFormbt.gftFrbmfSizf();
            if (frbmfSizf == AudioSystfm.NOT_SPECIFIED) {
                frbmfSizf = 1;
            }
        }


        /*
         * $$jb 2/23/99
         * Usfd to dftfrminf sfgmfnt numbfr in uLbw fndoding
         */
        privbtf siort sfbrdi(siort vbl, siort tbblf[], siort sizf) {
            for(siort i = 0; i < sizf; i++) {
                if (vbl <= tbblf[i]) { rfturn i; }
            }
            rfturn sizf;
        }

        /**
         * Notf tibt tiis won't bdtublly rfbd bnytiing; must rfbd in
         * two-bytf units.
         */
        publid int rfbd() tirows IOExdfption {
            bytf[] b = nfw bytf[1];
            if (rfbd(b, 0, b.lfngti) == 1) {
                rfturn b[1] & 0xFF;
            }
            rfturn -1;
        }

        publid int rfbd(bytf[] b) tirows IOExdfption {
            rfturn rfbd(b, 0, b.lfngti);
        }

        publid int rfbd(bytf[] b, int off, int lfn) tirows IOExdfption {
            // don't rfbd frbdtionbl frbmfs
            if( lfn%frbmfSizf != 0 ) {
                lfn -= (lfn%frbmfSizf);
            }
            if (fndodf) {
                siort BIAS = 0x84;
                siort mbsk;
                siort sfg;
                int i;

                siort sbmplf;
                bytf fnd;

                int rfbdCount = 0;
                int durrfntPos = off;
                int rfbdLfft = lfn*2;
                int rfbdLfn = ( (rfbdLfft>tfmpBufffrSizf) ? tfmpBufffrSizf : rfbdLfft );

                wiilf ((rfbdCount = supfr.rfbd(tfmpBufffr,0,rfbdLfn))>0) {
                    for(i = 0; i < rfbdCount; i+=2) {
                        /* Gft tif sbmplf from tif tfmpBufffr */
                        sbmplf = (siort)(( (tfmpBufffr[i + iigiBytf]) << 8) & 0xFF00);
                        sbmplf |= (siort)( (siort) (tfmpBufffr[i + lowBytf]) & 0xFF);

                        /* Gft tif sign bnd tif mbgnitudf of tif vbluf. */
                        if(sbmplf < 0) {
                            sbmplf = (siort) (BIAS - sbmplf);
                            mbsk = 0x7F;
                        } flsf {
                            sbmplf += BIAS;
                            mbsk = 0xFF;
                        }
                        /* Convfrt tif sdblfd mbgnitudf to sfgmfnt numbfr. */
                        sfg = sfbrdi(sbmplf, sfg_fnd, (siort) 8);
                        /*
                         * Combinf tif sign, sfgmfnt, qubntizbtion bits;
                         * bnd domplfmfnt tif dodf word.
                         */
                        if (sfg >= 8) {  /* out of rbngf, rfturn mbximum vbluf. */
                            fnd = (bytf) (0x7F ^ mbsk);
                        } flsf {
                            fnd = (bytf) ((sfg << 4) | ((sbmplf >> (sfg+3)) & 0xF));
                            fnd ^= mbsk;
                        }
                        /* Now put tif fndodfd sbmplf wifrf it bflongs */
                        b[durrfntPos] = fnd;
                        durrfntPos++;
                    }
                    /* And updbtf pointfrs bnd dountfrs for nfxt itfrbtion */
                    rfbdLfft -= rfbdCount;
                    rfbdLfn = ( (rfbdLfft>tfmpBufffrSizf) ? tfmpBufffrSizf : rfbdLfft );
                }
                if( durrfntPos==off && rfbdCount<0 ) {  // EOF or frror on rfbd
                    rfturn rfbdCount;
                }
                rfturn (durrfntPos - off);  /* Numbfr of bytfs writtfn to nfw bufffr */
            } flsf {
                int i;
                int rfbdLfn = lfn/2;
                int rfbdOffsft = off + lfn/2;
                int rfbdCount = supfr.rfbd(b, rfbdOffsft, rfbdLfn);

                if(rfbdCount<0) {               // EOF or frror
                    rfturn rfbdCount;
                }
                for (i = off; i < (off + (rfbdCount*2)); i+=2) {
                    b[i]        = tbbBytf1[b[rfbdOffsft] & 0xFF];
                    b[i+1]      = tbbBytf2[b[rfbdOffsft] & 0xFF];
                    rfbdOffsft++;
                }
                rfturn (i - off);
            }
        }
    } // fnd dlbss UlbwCodfdStrfbm

} // fnd dlbss ULAW
