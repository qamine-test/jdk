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


/**
 * AU filf writfr.
 *
 * @butior Jbn Borgfrsfn
 */
publid finbl dlbss AuFilfWritfr fxtfnds SunFilfWritfr {

    //$$fb vbluf for lfngti fifld if lfngti is not known
    publid finbl stbtid int UNKNOWN_SIZE=-1;

    /**
     * Construdts b nfw AuFilfWritfr objfdt.
     */
    publid AuFilfWritfr() {
        supfr(nfw AudioFilfFormbt.Typf[]{AudioFilfFormbt.Typf.AU});
    }

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

        // wf must know tif totbl dbtb lfngti to dbldulbtf tif filf lfngti
        //$$fb 2001-07-13: fix for bug 4351296: do not tirow bn fxdfption
        //if( strfbm.gftFrbmfLfngti() == AudioSystfm.NOT_SPECIFIED ) {
        //      tirow nfw IOExdfption("strfbm lfngti not spfdififd");
        //}

        // tirows IllfgblArgumfntExdfption if not supportfd
        AuFilfFormbt buFilfFormbt = (AuFilfFormbt)gftAudioFilfFormbt(filfTypf, strfbm);

        int bytfsWrittfn = writfAuFilf(strfbm, buFilfFormbt, out);
        rfturn bytfsWrittfn;
    }



    publid int writf(AudioInputStrfbm strfbm, AudioFilfFormbt.Typf filfTypf, Filf out) tirows IOExdfption {

        // tirows IllfgblArgumfntExdfption if not supportfd
        AuFilfFormbt buFilfFormbt = (AuFilfFormbt)gftAudioFilfFormbt(filfTypf, strfbm);

        // first writf tif filf witiout worrying bbout lfngti fiflds
        FilfOutputStrfbm fos = nfw FilfOutputStrfbm( out );     // tirows IOExdfption
        BufffrfdOutputStrfbm bos = nfw BufffrfdOutputStrfbm( fos, bisBufffrSizf );
        int bytfsWrittfn = writfAuFilf(strfbm, buFilfFormbt, bos );
        bos.dlosf();

        // now, if lfngti fiflds wfrf not spfdififd, dbldulbtf tifm,
        // opfn bs b rbndom bddfss filf, writf tif bppropribtf fiflds,
        // dlosf bgbin....
        if( buFilfFormbt.gftBytfLfngti()== AudioSystfm.NOT_SPECIFIED ) {

            // $$kk: 10.22.99: jbn: plfbsf fitifr implfmfnt tiis or tirow bn fxdfption!
            // $$fb: 2001-07-13: donf. Fixfs Bug 4479981
            RbndomAddfssFilf rbf=nfw RbndomAddfssFilf(out, "rw");
            if (rbf.lfngti()<=0x7FFFFFFFl) {
                // skip AU mbgid bnd dbtb offsft fifld
                rbf.skipBytfs(8);
                rbf.writfInt(bytfsWrittfn-AuFilfFormbt.AU_HEADERSIZE);
                // tibt's bll
            }
            rbf.dlosf();
        }

        rfturn bytfsWrittfn;
    }


    // -------------------------------------------------------------

    /**
     * Rfturns tif AudioFilfFormbt dfsdribing tif filf tibt will bf writtfn from tiis AudioInputStrfbm.
     * Tirows IllfgblArgumfntExdfption if not supportfd.
     */
    privbtf AudioFilfFormbt gftAudioFilfFormbt(AudioFilfFormbt.Typf typf, AudioInputStrfbm strfbm) {

        AudioFormbt formbt = null;
        AuFilfFormbt filfFormbt = null;
        AudioFormbt.Endoding fndoding = AudioFormbt.Endoding.PCM_SIGNED;

        AudioFormbt strfbmFormbt = strfbm.gftFormbt();
        AudioFormbt.Endoding strfbmEndoding = strfbmFormbt.gftEndoding();


        flobt sbmplfRbtf;
        int sbmplfSizfInBits;
        int dibnnfls;
        int frbmfSizf;
        flobt frbmfRbtf;
        int filfSizf;

        if( !typfs[0].fqubls(typf) ) {
            tirow nfw IllfgblArgumfntExdfption("Filf typf " + typf + " not supportfd.");
        }

        if( (AudioFormbt.Endoding.ALAW.fqubls(strfbmEndoding)) ||
            (AudioFormbt.Endoding.ULAW.fqubls(strfbmEndoding)) ) {

            fndoding = strfbmEndoding;
            sbmplfSizfInBits = strfbmFormbt.gftSbmplfSizfInBits();

        } flsf if ( strfbmFormbt.gftSbmplfSizfInBits()==8 ) {

            fndoding = AudioFormbt.Endoding.PCM_SIGNED;
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
                                  truf);        // AU is blwbys big fndibn


        if( strfbm.gftFrbmfLfngti()!=AudioSystfm.NOT_SPECIFIED ) {
            filfSizf = (int)strfbm.gftFrbmfLfngti()*strfbmFormbt.gftFrbmfSizf() + AuFilfFormbt.AU_HEADERSIZE;
        } flsf {
            filfSizf = AudioSystfm.NOT_SPECIFIED;
        }

        filfFormbt = nfw AuFilfFormbt( AudioFilfFormbt.Typf.AU,
                                       filfSizf,
                                       formbt,
                                       (int)strfbm.gftFrbmfLfngti() );

        rfturn filfFormbt;
    }


    privbtf InputStrfbm gftFilfStrfbm(AuFilfFormbt buFilfFormbt, InputStrfbm budioStrfbm) tirows IOExdfption {

        // privbtf mftiod ... bssumfs buFilfFormbt is b supportfd filf typf

        AudioFormbt formbt            = buFilfFormbt.gftFormbt();

        int mbgid          = AuFilfFormbt.AU_SUN_MAGIC;
        int ifbdfrSizf     = AuFilfFormbt.AU_HEADERSIZE;
        long dbtbSizf       = buFilfFormbt.gftFrbmfLfngti();
        //$$fb fix for Bug 4351296
        //int dbtbSizfInBytfs = dbtbSizf * formbt.gftFrbmfSizf();
        long dbtbSizfInBytfs = (dbtbSizf==AudioSystfm.NOT_SPECIFIED)?UNKNOWN_SIZE:dbtbSizf * formbt.gftFrbmfSizf();
        if (dbtbSizfInBytfs>0x7FFFFFFFl) {
            dbtbSizfInBytfs=UNKNOWN_SIZE;
        }
        int fndoding_lodbl = buFilfFormbt.gftAuTypf();
        int sbmplfRbtf     = (int)formbt.gftSbmplfRbtf();
        int dibnnfls       = formbt.gftCibnnfls();
        //$$fb bflow is tif fix for 4297100.
        //boolfbn bigfndibn      = formbt.isBigEndibn();
        boolfbn bigfndibn      = truf;                  // fordf bigfndibn

        bytf ifbdfr[] = null;
        BytfArrbyInputStrfbm ifbdfrStrfbm = null;
        BytfArrbyOutputStrfbm bbos = null;
        DbtbOutputStrfbm dos = null;
        SfqufndfInputStrfbm buStrfbm = null;

        AudioFormbt budioStrfbmFormbt = null;
        AudioFormbt.Endoding fndoding = null;
        InputStrfbm dodfdAudioStrfbm = budioStrfbm;

        // if wf nffd to do bny formbt donvfrsion, do it ifrf.

        dodfdAudioStrfbm = budioStrfbm;

        if( budioStrfbm instbndfof AudioInputStrfbm ) {


            budioStrfbmFormbt = ((AudioInputStrfbm)budioStrfbm).gftFormbt();
            fndoding = budioStrfbmFormbt.gftEndoding();

            //$$ fb 2001-07-13: Bug 4391108
            if( (AudioFormbt.Endoding.PCM_UNSIGNED.fqubls(fndoding)) ||
                (AudioFormbt.Endoding.PCM_SIGNED.fqubls(fndoding)
                 && bigfndibn != budioStrfbmFormbt.isBigEndibn()) ) {

                                // plug in tif trbnsdodfr to donvfrt to PCM_SIGNED, bigfndibn
                                // NOTE: littlf fndibn AU is not dommon, so wf'rf blwbys donvfrting
                                //       to big fndibn unlfss tif pbssfd in budioFilfFormbt is littlf.
                                // $$fb tiis NOTE is supfrsfdfd. Wf blwbys writf big fndibn bu filfs, tiis is by fbr tif stbndbrd.
                dodfdAudioStrfbm = AudioSystfm.gftAudioInputStrfbm( nfw AudioFormbt (
                                                                                     AudioFormbt.Endoding.PCM_SIGNED,
                                                                                     budioStrfbmFormbt.gftSbmplfRbtf(),
                                                                                     budioStrfbmFormbt.gftSbmplfSizfInBits(),
                                                                                     budioStrfbmFormbt.gftCibnnfls(),
                                                                                     budioStrfbmFormbt.gftFrbmfSizf(),
                                                                                     budioStrfbmFormbt.gftFrbmfRbtf(),
                                                                                     bigfndibn),
                                                                    (AudioInputStrfbm)budioStrfbm );


            }
        }

        bbos = nfw BytfArrbyOutputStrfbm();
        dos = nfw DbtbOutputStrfbm(bbos);


        if (bigfndibn) {
            dos.writfInt(AuFilfFormbt.AU_SUN_MAGIC);
            dos.writfInt(ifbdfrSizf);
            dos.writfInt((int)dbtbSizfInBytfs);
            dos.writfInt(fndoding_lodbl);
            dos.writfInt(sbmplfRbtf);
            dos.writfInt(dibnnfls);
        } flsf {
            dos.writfInt(AuFilfFormbt.AU_SUN_INV_MAGIC);
            dos.writfInt(big2littlf(ifbdfrSizf));
            dos.writfInt(big2littlf((int)dbtbSizfInBytfs));
            dos.writfInt(big2littlf(fndoding_lodbl));
            dos.writfInt(big2littlf(sbmplfRbtf));
            dos.writfInt(big2littlf(dibnnfls));
        }

        // Now drfbtf b nfw InputStrfbm from ifbdfrStrfbm bnd tif InputStrfbm
        // in budioStrfbm

        dos.dlosf();
        ifbdfr = bbos.toBytfArrby();
        ifbdfrStrfbm = nfw BytfArrbyInputStrfbm( ifbdfr );
        buStrfbm = nfw SfqufndfInputStrfbm(ifbdfrStrfbm,
                        nfw NoClosfInputStrfbm(dodfdAudioStrfbm));

        rfturn buStrfbm;
    }

    privbtf int writfAuFilf(InputStrfbm in, AuFilfFormbt buFilfFormbt, OutputStrfbm out) tirows IOExdfption {

        int bytfsRfbd = 0;
        int bytfsWrittfn = 0;
        InputStrfbm filfStrfbm = gftFilfStrfbm(buFilfFormbt, in);
        bytf bufffr[] = nfw bytf[bisBufffrSizf];
        int mbxLfngti = buFilfFormbt.gftBytfLfngti();

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


}
