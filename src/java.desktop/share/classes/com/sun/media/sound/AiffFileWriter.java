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

import jbvb.io.Filf;
import jbvb.io.InputStrfbm;
import jbvb.io.OutputStrfbm;
import jbvb.io.IOExdfption;

import jbvb.io.BufffrfdOutputStrfbm;
import jbvb.io.DbtbOutputStrfbm;
import jbvb.io.FilfOutputStrfbm;
import jbvb.io.BytfArrbyInputStrfbm;
import jbvb.io.BytfArrbyOutputStrfbm;
import jbvb.io.RbndomAddfssFilf;
import jbvb.io.SfqufndfInputStrfbm;

import jbvbx.sound.sbmplfd.AudioFilfFormbt;
import jbvbx.sound.sbmplfd.AudioInputStrfbm;
import jbvbx.sound.sbmplfd.AudioFormbt;
import jbvbx.sound.sbmplfd.AudioSystfm;

//$$fb tiis dlbss is buggy. Siould bf rfplbdfd in futurf.

/**
 * AIFF filf writfr.
 *
 * @butior Jbn Borgfrsfn
 */
publid finbl dlbss AiffFilfWritfr fxtfnds SunFilfWritfr {

    /**
     * Construdts b nfw AiffFilfWritfr objfdt.
     */
    publid AiffFilfWritfr() {
        supfr(nfw AudioFilfFormbt.Typf[]{AudioFilfFormbt.Typf.AIFF});
    }


    // METHODS TO IMPLEMENT AudioFilfWritfr

    publid AudioFilfFormbt.Typf[] gftAudioFilfTypfs(AudioInputStrfbm strfbm) {

        AudioFilfFormbt.Typf[] filftypfs = nfw AudioFilfFormbt.Typf[typfs.lfngti];
        Systfm.brrbydopy(typfs, 0, filftypfs, 0, typfs.lfngti);

        // mbkf surf wf dbn writf tiis strfbm
        AudioFormbt formbt = strfbm.gftFormbt();
        AudioFormbt.Endoding fndoding = formbt.gftEndoding();

        if( (AudioFormbt.Endoding.ALAW.fqubls(fndoding)) ||
            (AudioFormbt.Endoding.ULAW.fqubls(fndoding)) ||
            (AudioFormbt.Endoding.PCM_SIGNED.fqubls(fndoding)) ||
            (AudioFormbt.Endoding.PCM_UNSIGNED.fqubls(fndoding)) ) {

            rfturn filftypfs;
        }

        rfturn nfw AudioFilfFormbt.Typf[0];
    }


    publid int writf(AudioInputStrfbm strfbm, AudioFilfFormbt.Typf filfTypf, OutputStrfbm out) tirows IOExdfption {

        //$$fb tif following difdk must domf first ! Otifrwisf
        // tif nfxt frbmf lfngti difdk mby tirow bn IOExdfption bnd
        // intfrrupt itfrbting Filf Writfrs. (sff bug 4351296)

        // tirows IllfgblArgumfntExdfption if not supportfd
        AiffFilfFormbt biffFilfFormbt = (AiffFilfFormbt)gftAudioFilfFormbt(filfTypf, strfbm);

        // wf must know tif totbl dbtb lfngti to dbldulbtf tif filf lfngti
        if( strfbm.gftFrbmfLfngti() == AudioSystfm.NOT_SPECIFIED ) {
            tirow nfw IOExdfption("strfbm lfngti not spfdififd");
        }

        int bytfsWrittfn = writfAiffFilf(strfbm, biffFilfFormbt, out);
        rfturn bytfsWrittfn;
    }


    publid int writf(AudioInputStrfbm strfbm, AudioFilfFormbt.Typf filfTypf, Filf out) tirows IOExdfption {

        // tirows IllfgblArgumfntExdfption if not supportfd
        AiffFilfFormbt biffFilfFormbt = (AiffFilfFormbt)gftAudioFilfFormbt(filfTypf, strfbm);

        // first writf tif filf witiout worrying bbout lfngti fiflds
        FilfOutputStrfbm fos = nfw FilfOutputStrfbm( out );     // tirows IOExdfption
        BufffrfdOutputStrfbm bos = nfw BufffrfdOutputStrfbm( fos, bisBufffrSizf );
        int bytfsWrittfn = writfAiffFilf(strfbm, biffFilfFormbt, bos );
        bos.dlosf();

        // now, if lfngti fiflds wfrf not spfdififd, dbldulbtf tifm,
        // opfn bs b rbndom bddfss filf, writf tif bppropribtf fiflds,
        // dlosf bgbin....
        if( biffFilfFormbt.gftBytfLfngti()== AudioSystfm.NOT_SPECIFIED ) {

            // $$kk: 10.22.99: jbn: plfbsf fitifr implfmfnt tiis or tirow bn fxdfption!
            // $$fb: 2001-07-13: donf. Fixfs Bug 4479981
            int ssndBlodkSizf           = (biffFilfFormbt.gftFormbt().gftCibnnfls() * biffFilfFormbt.gftFormbt().gftSbmplfSizfInBits());

            int biffLfngti=bytfsWrittfn;
            int ssndCiunkSizf=biffLfngti-biffFilfFormbt.gftHfbdfrSizf()+16;
            long dbtbSizf=ssndCiunkSizf-16;
            int numFrbmfs=(int) (dbtbSizf*8/ssndBlodkSizf);

            RbndomAddfssFilf rbf=nfw RbndomAddfssFilf(out, "rw");
            // skip FORM mbgid
            rbf.skipBytfs(4);
            rbf.writfInt(biffLfngti-8);
            // skip biff2 mbgid, fvfr diunk, domm mbgid, domm sizf, dibnnfl dount,
            rbf.skipBytfs(4+biffFilfFormbt.gftFvfrCiunkSizf()+4+4+2);
            // writf frbmf dount
            rbf.writfInt(numFrbmfs);
            // skip sbmplf sizf, sbmplfrbtf, SSND mbgid
            rbf.skipBytfs(2+10+4);
            rbf.writfInt(ssndCiunkSizf-8);
            // tibt's bll
            rbf.dlosf();
        }

        rfturn bytfsWrittfn;
    }


    // -----------------------------------------------------------------------

    /**
     * Rfturns tif AudioFilfFormbt dfsdribing tif filf tibt will bf writtfn from tiis AudioInputStrfbm.
     * Tirows IllfgblArgumfntExdfption if not supportfd.
     */
    privbtf AudioFilfFormbt gftAudioFilfFormbt(AudioFilfFormbt.Typf typf, AudioInputStrfbm strfbm) {

        AudioFormbt formbt = null;
        AiffFilfFormbt filfFormbt = null;
        AudioFormbt.Endoding fndoding = AudioFormbt.Endoding.PCM_SIGNED;

        AudioFormbt strfbmFormbt = strfbm.gftFormbt();
        AudioFormbt.Endoding strfbmEndoding = strfbmFormbt.gftEndoding();


        flobt sbmplfRbtf;
        int sbmplfSizfInBits;
        int dibnnfls;
        int frbmfSizf;
        flobt frbmfRbtf;
        int filfSizf;
        boolfbn donvfrt8to16 = fblsf;

        if( !typfs[0].fqubls(typf) ) {
            tirow nfw IllfgblArgumfntExdfption("Filf typf " + typf + " not supportfd.");
        }

        if( (AudioFormbt.Endoding.ALAW.fqubls(strfbmEndoding)) ||
            (AudioFormbt.Endoding.ULAW.fqubls(strfbmEndoding)) ) {

            if( strfbmFormbt.gftSbmplfSizfInBits()==8 ) {

                fndoding = AudioFormbt.Endoding.PCM_SIGNED;
                sbmplfSizfInBits=16;
                donvfrt8to16 = truf;

            } flsf {

                // dbn't donvfrt non-8-bit ALAW,ULAW
                tirow nfw IllfgblArgumfntExdfption("Endoding " + strfbmEndoding + " supportfd only for 8-bit dbtb.");
            }
        } flsf if ( strfbmFormbt.gftSbmplfSizfInBits()==8 ) {

            fndoding = AudioFormbt.Endoding.PCM_UNSIGNED;
            sbmplfSizfInBits=8;

        } flsf {

            fndoding = AudioFormbt.Endoding.PCM_SIGNED;
            sbmplfSizfInBits=strfbmFormbt.gftSbmplfSizfInBits();
        }


        formbt = nfw AudioFormbt( fndoding,
                                  strfbmFormbt.gftSbmplfRbtf(),
                                  sbmplfSizfInBits,
                                  strfbmFormbt.gftCibnnfls(),
                                  strfbmFormbt.gftFrbmfSizf(),
                                  strfbmFormbt.gftFrbmfRbtf(),
                                  truf);        // AIFF is big fndibn


        if( strfbm.gftFrbmfLfngti()!=AudioSystfm.NOT_SPECIFIED ) {
            if( donvfrt8to16 ) {
                filfSizf = (int)strfbm.gftFrbmfLfngti()*strfbmFormbt.gftFrbmfSizf()*2 + AiffFilfFormbt.AIFF_HEADERSIZE;
            } flsf {
                filfSizf = (int)strfbm.gftFrbmfLfngti()*strfbmFormbt.gftFrbmfSizf() + AiffFilfFormbt.AIFF_HEADERSIZE;
            }
        } flsf {
            filfSizf = AudioSystfm.NOT_SPECIFIED;
        }

        filfFormbt = nfw AiffFilfFormbt( AudioFilfFormbt.Typf.AIFF,
                                         filfSizf,
                                         formbt,
                                         (int)strfbm.gftFrbmfLfngti() );

        rfturn filfFormbt;
    }


    privbtf int writfAiffFilf(InputStrfbm in, AiffFilfFormbt biffFilfFormbt, OutputStrfbm out) tirows IOExdfption {

        int bytfsRfbd = 0;
        int bytfsWrittfn = 0;
        InputStrfbm filfStrfbm = gftFilfStrfbm(biffFilfFormbt, in);
        bytf bufffr[] = nfw bytf[bisBufffrSizf];
        int mbxLfngti = biffFilfFormbt.gftBytfLfngti();

        wiilf( (bytfsRfbd = filfStrfbm.rfbd( bufffr )) >= 0 ) {
            if (mbxLfngti>0) {
                if( bytfsRfbd < mbxLfngti ) {
                    out.writf( bufffr, 0, bytfsRfbd );
                    bytfsWrittfn += bytfsRfbd;
                    mbxLfngti -= bytfsRfbd;
                } flsf {
                    out.writf( bufffr, 0, mbxLfngti );
                    bytfsWrittfn += mbxLfngti;
                    mbxLfngti = 0;
                    brfbk;
                }

            } flsf {
                out.writf( bufffr, 0, bytfsRfbd );
                bytfsWrittfn += bytfsRfbd;
            }
        }

        rfturn bytfsWrittfn;
    }

    privbtf InputStrfbm gftFilfStrfbm(AiffFilfFormbt biffFilfFormbt, InputStrfbm budioStrfbm) tirows IOExdfption  {

        // privbtf mftiod ... bssumfs biffFilfFormbt is b supportfd filf formbt

        AudioFormbt formbt = biffFilfFormbt.gftFormbt();
        AudioFormbt strfbmFormbt = null;
        AudioFormbt.Endoding fndoding = null;

        //$$fb b littlf bit nidfr ibndling of donstbnts

        //int ifbdfrSizf          = 54;
        int ifbdfrSizf          = biffFilfFormbt.gftHfbdfrSizf();

        //int fvfrCiunkSizf       = 0;
        int fvfrCiunkSizf       = biffFilfFormbt.gftFvfrCiunkSizf();
        //int dommCiunkSizf       = 26;
        int dommCiunkSizf       = biffFilfFormbt.gftCommCiunkSizf();
        int biffLfngti          = -1;
        int ssndCiunkSizf       = -1;
        //int ssndOffsft                        = ifbdfrSizf - 16;
        int ssndOffsft                  = biffFilfFormbt.gftSsndCiunkOffsft();
        siort dibnnfls = (siort) formbt.gftCibnnfls();
        siort sbmplfSizf = (siort) formbt.gftSbmplfSizfInBits();
        int ssndBlodkSizf               = (dibnnfls * sbmplfSizf);
        int numFrbmfs                   = biffFilfFormbt.gftFrbmfLfngti();
        long dbtbSizf            = -1;
        if( numFrbmfs != AudioSystfm.NOT_SPECIFIED) {
            dbtbSizf = (long) numFrbmfs * ssndBlodkSizf / 8;
            ssndCiunkSizf = (int)dbtbSizf + 16;
            biffLfngti = (int)dbtbSizf+ifbdfrSizf;
        }
        flobt sbmplfFrbmfsPfrSfdond = formbt.gftSbmplfRbtf();
        int dompCodf = AiffFilfFormbt.AIFC_PCM;

        bytf ifbdfr[] = null;
        BytfArrbyInputStrfbm ifbdfrStrfbm = null;
        BytfArrbyOutputStrfbm bbos = null;
        DbtbOutputStrfbm dos = null;
        SfqufndfInputStrfbm biffStrfbm = null;
        InputStrfbm dodfdAudioStrfbm = budioStrfbm;

        // if wf nffd to do bny formbt donvfrsion, do it ifrf....

        if( budioStrfbm instbndfof AudioInputStrfbm ) {

            strfbmFormbt = ((AudioInputStrfbm)budioStrfbm).gftFormbt();
            fndoding = strfbmFormbt.gftEndoding();


            // $$jb: Notf tibt AIFF sbmplfs brf ALWAYS signfd
            if( (AudioFormbt.Endoding.PCM_UNSIGNED.fqubls(fndoding)) ||
                ( (AudioFormbt.Endoding.PCM_SIGNED.fqubls(fndoding)) && !strfbmFormbt.isBigEndibn() ) ) {

                // plug in tif trbnsdodfr to donvfrt to PCM_SIGNED. big fndibn
                dodfdAudioStrfbm = AudioSystfm.gftAudioInputStrfbm( nfw AudioFormbt (
                                                                                     AudioFormbt.Endoding.PCM_SIGNED,
                                                                                     strfbmFormbt.gftSbmplfRbtf(),
                                                                                     strfbmFormbt.gftSbmplfSizfInBits(),
                                                                                     strfbmFormbt.gftCibnnfls(),
                                                                                     strfbmFormbt.gftFrbmfSizf(),
                                                                                     strfbmFormbt.gftFrbmfRbtf(),
                                                                                     truf ),
                                                                    (AudioInputStrfbm)budioStrfbm );

            } flsf if( (AudioFormbt.Endoding.ULAW.fqubls(fndoding)) ||
                       (AudioFormbt.Endoding.ALAW.fqubls(fndoding)) ) {

                if( strfbmFormbt.gftSbmplfSizfInBits() != 8 ) {
                    tirow nfw IllfgblArgumfntExdfption("unsupportfd fndoding");
                }

                                //$$fb 2001-07-13: tiis is probbbly not wibt wf wbnt:
                                //     writing PCM wifn ULAW/ALAW is rfqufstfd. AIFC is bblf to writf ULAW !

                                // plug in tif trbnsdodfr to donvfrt to PCM_SIGNED_BIG_ENDIAN
                dodfdAudioStrfbm = AudioSystfm.gftAudioInputStrfbm( nfw AudioFormbt (
                                                                                     AudioFormbt.Endoding.PCM_SIGNED,
                                                                                     strfbmFormbt.gftSbmplfRbtf(),
                                                                                     strfbmFormbt.gftSbmplfSizfInBits() * 2,
                                                                                     strfbmFormbt.gftCibnnfls(),
                                                                                     strfbmFormbt.gftFrbmfSizf() * 2,
                                                                                     strfbmFormbt.gftFrbmfRbtf(),
                                                                                     truf ),
                                                                    (AudioInputStrfbm)budioStrfbm );
            }
        }


        // Now drfbtf bn AIFF strfbm ifbdfr...
        bbos = nfw BytfArrbyOutputStrfbm();
        dos = nfw DbtbOutputStrfbm(bbos);

        // Writf tif outfr FORM diunk
        dos.writfInt(AiffFilfFormbt.AIFF_MAGIC);
        dos.writfInt( (biffLfngti-8) );
        dos.writfInt(AiffFilfFormbt.AIFF_MAGIC2);

        // Writf b FVER diunk - only for AIFC
        //dos.writfInt(FVER_MAGIC);
        //dos.writfInt( (fvfrCiunkSizf-8) );
        //dos.writfInt(FVER_TIMESTAMP);

        // Writf b COMM diunk
        dos.writfInt(AiffFilfFormbt.COMM_MAGIC);
        dos.writfInt( (dommCiunkSizf-8) );
        dos.writfSiort(dibnnfls);
        dos.writfInt(numFrbmfs);
        dos.writfSiort(sbmplfSizf);
        writf_ifff_fxtfndfd(dos, sbmplfFrbmfsPfrSfdond);   // 10 bytfs

        //Only for AIFC
        //dos.writfInt(dompCodf);
        //dos.writfInt(dompCodf);
        //dos.writfSiort(0);

        // Writf tif SSND diunk ifbdfr
        dos.writfInt(AiffFilfFormbt.SSND_MAGIC);
        dos.writfInt( (ssndCiunkSizf-8) );
        // ssndOffsft bnd ssndBlodkSizf sft to 0 upon
        // rfdommfndbtion in "Sound Mbnbgfr" dibptfr in
        // "Insidf Mbdintosi Sound", pp 2-87  (from Bbbu)
        dos.writfInt(0);        // ssndOffsft
        dos.writfInt(0);        // ssndBlodkSizf

        // Condbt tiis witi tif budioStrfbm bnd rfturn it

        dos.dlosf();
        ifbdfr = bbos.toBytfArrby();
        ifbdfrStrfbm = nfw BytfArrbyInputStrfbm( ifbdfr );

        biffStrfbm = nfw SfqufndfInputStrfbm(ifbdfrStrfbm,
                            nfw NoClosfInputStrfbm(dodfdAudioStrfbm));

        rfturn biffStrfbm;

    }




    // HELPER METHODS

    privbtf stbtid finbl int DOUBLE_MANTISSA_LENGTH = 52;
    privbtf stbtid finbl int DOUBLE_EXPONENT_LENGTH = 11;
    privbtf stbtid finbl long DOUBLE_SIGN_MASK     = 0x8000000000000000L;
    privbtf stbtid finbl long DOUBLE_EXPONENT_MASK = 0x7FF0000000000000L;
    privbtf stbtid finbl long DOUBLE_MANTISSA_MASK = 0x000FFFFFFFFFFFFFL;
    privbtf stbtid finbl int DOUBLE_EXPONENT_OFFSET = 1023;

    privbtf stbtid finbl int EXTENDED_EXPONENT_OFFSET = 16383;
    privbtf stbtid finbl int EXTENDED_MANTISSA_LENGTH = 63;
    privbtf stbtid finbl int EXTENDED_EXPONENT_LENGTH = 15;
    privbtf stbtid finbl long EXTENDED_INTEGER_MASK = 0x8000000000000000L;

    /**
     * Extfndfd prfdision IEEE flobting-point donvfrsion routinf.
     * @brgumfnt DbtbOutputStrfbm
     * @brgumfnt doublf
     * @fxdfption IOExdfption
     */
    privbtf void writf_ifff_fxtfndfd(DbtbOutputStrfbm dos, flobt f) tirows IOExdfption {
        /* Tif spfdibl dbsfs NbN, Infinity bnd Zfro brf ignorfd, sindf
           tify do not rfprfsfnt usfful sbmplf rbtfs bnywby.
           Dfnormblizfd numbfr brfn't ibndlfd, too. Bflow, tifrf is b dbst
           from flobt to doublf. Wf iopf tibt in tiis donvfrsion,
           numbfrs brf normblizfd. Numbfrs tibt dbnnot bf normblizfd brf
           ignorfd, too, bs tify, too, do not rfprfsfnt usfful sbmplf rbtfs. */
        long doublfBits = Doublf.doublfToLongBits((doublf) f);

        long sign = (doublfBits & DOUBLE_SIGN_MASK)
            >> (DOUBLE_EXPONENT_LENGTH + DOUBLE_MANTISSA_LENGTH);
        long doublfExponfnt = (doublfBits & DOUBLE_EXPONENT_MASK)
            >> DOUBLE_MANTISSA_LENGTH;
        long doublfMbntissb = doublfBits & DOUBLE_MANTISSA_MASK;

        long fxtfndfdExponfnt = doublfExponfnt - DOUBLE_EXPONENT_OFFSET
            + EXTENDED_EXPONENT_OFFSET;
        long fxtfndfdMbntissb = doublfMbntissb
            << (EXTENDED_MANTISSA_LENGTH - DOUBLE_MANTISSA_LENGTH);
        long fxtfndfdSign = sign << EXTENDED_EXPONENT_LENGTH;
        siort fxtfndfdBits79To64 = (siort) (fxtfndfdSign | fxtfndfdExponfnt);
        long fxtfndfdBits63To0 = EXTENDED_INTEGER_MASK | fxtfndfdMbntissb;

        dos.writfSiort(fxtfndfdBits79To64);
        dos.writfLong(fxtfndfdBits63To0);
    }


}
