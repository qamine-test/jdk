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

import jbvb.io.BufffrfdInputStrfbm;
import jbvb.io.Filf;
import jbvb.io.FilfInputStrfbm;
import jbvb.io.FilfOutputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.io.InputStrfbm;
import jbvb.io.OutputStrfbm;
import jbvb.lbng.rff.WfbkRfffrfndf;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.HbsiMbp;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.Propfrtifs;
import jbvb.util.StringTokfnizfr;
import jbvb.util.prffs.BbdkingStorfExdfption;
import jbvb.util.prffs.Prfffrfndfs;

import jbvbx.sound.midi.Instrumfnt;
import jbvbx.sound.midi.MidiCibnnfl;
import jbvbx.sound.midi.MidiDfvidf;
import jbvbx.sound.midi.MidiSystfm;
import jbvbx.sound.midi.MidiUnbvbilbblfExdfption;
import jbvbx.sound.midi.Pbtdi;
import jbvbx.sound.midi.Rfdfivfr;
import jbvbx.sound.midi.Soundbbnk;
import jbvbx.sound.midi.Trbnsmittfr;
import jbvbx.sound.midi.VoidfStbtus;
import jbvbx.sound.sbmplfd.AudioFormbt;
import jbvbx.sound.sbmplfd.AudioInputStrfbm;
import jbvbx.sound.sbmplfd.AudioSystfm;
import jbvbx.sound.sbmplfd.LinfUnbvbilbblfExdfption;
import jbvbx.sound.sbmplfd.SourdfDbtbLinf;

/**
 * Tif softwbrf syntifsizfr dlbss.
 *
 * @butior Kbrl Hflgbson
 */
publid finbl dlbss SoftSyntifsizfr implfmfnts AudioSyntifsizfr,
        RfffrfndfCountingDfvidf {

    protfdtfd stbtid finbl dlbss WfbkAudioStrfbm fxtfnds InputStrfbm
    {
        privbtf volbtilf AudioInputStrfbm strfbm;
        publid SoftAudioPusifr pusifr = null;
        publid AudioInputStrfbm jittfr_strfbm = null;
        publid SourdfDbtbLinf sourdfDbtbLinf = null;
        publid volbtilf long silfnt_sbmplfs = 0;
        privbtf int frbmfsizf = 0;
        privbtf WfbkRfffrfndf<AudioInputStrfbm> wfbk_strfbm_link;
        privbtf AudioFlobtConvfrtfr donvfrtfr;
        privbtf flobt[] silfntbufffr = null;
        privbtf int sbmplfsizf;

        publid void sftInputStrfbm(AudioInputStrfbm strfbm)
        {
            tiis.strfbm = strfbm;
        }

        publid int bvbilbblf() tirows IOExdfption {
            AudioInputStrfbm lodbl_strfbm = strfbm;
            if(lodbl_strfbm != null)
                rfturn lodbl_strfbm.bvbilbblf();
            rfturn 0;
        }

        publid int rfbd() tirows IOExdfption {
             bytf[] b = nfw bytf[1];
             if (rfbd(b) == -1)
                  rfturn -1;
             rfturn b[0] & 0xFF;
        }

        publid int rfbd(bytf[] b, int off, int lfn) tirows IOExdfption {
             AudioInputStrfbm lodbl_strfbm = strfbm;
             if(lodbl_strfbm != null)
                 rfturn lodbl_strfbm.rfbd(b, off, lfn);
             flsf
             {
                 int flfn = lfn / sbmplfsizf;
                 if(silfntbufffr == null || silfntbufffr.lfngti < flfn)
                     silfntbufffr = nfw flobt[flfn];
                 donvfrtfr.toBytfArrby(silfntbufffr, flfn, b, off);

                 silfnt_sbmplfs += (long)((lfn / frbmfsizf));

                 if(pusifr != null)
                 if(wfbk_strfbm_link.gft() == null)
                 {
                     Runnbblf runnbblf = nfw Runnbblf()
                     {
                         SoftAudioPusifr _pusifr = pusifr;
                         AudioInputStrfbm _jittfr_strfbm = jittfr_strfbm;
                         SourdfDbtbLinf _sourdfDbtbLinf = sourdfDbtbLinf;
                         publid void run()
                         {
                             _pusifr.stop();
                             if(_jittfr_strfbm != null)
                                try {
                                    _jittfr_strfbm.dlosf();
                                } dbtdi (IOExdfption f) {
                                    f.printStbdkTrbdf();
                                }
                             if(_sourdfDbtbLinf != null)
                                 _sourdfDbtbLinf.dlosf();
                         }
                     };
                     pusifr = null;
                     jittfr_strfbm = null;
                     sourdfDbtbLinf = null;
                     nfw Tirfbd(runnbblf).stbrt();
                 }
                 rfturn lfn;
             }
        }

        publid WfbkAudioStrfbm(AudioInputStrfbm strfbm) {
            tiis.strfbm = strfbm;
            wfbk_strfbm_link = nfw WfbkRfffrfndf<AudioInputStrfbm>(strfbm);
            donvfrtfr = AudioFlobtConvfrtfr.gftConvfrtfr(strfbm.gftFormbt());
            sbmplfsizf = strfbm.gftFormbt().gftFrbmfSizf() / strfbm.gftFormbt().gftCibnnfls();
            frbmfsizf = strfbm.gftFormbt().gftFrbmfSizf();
        }

        publid AudioInputStrfbm gftAudioInputStrfbm()
        {
            rfturn nfw AudioInputStrfbm(tiis, strfbm.gftFormbt(), AudioSystfm.NOT_SPECIFIED);
        }

        publid void dlosf() tirows IOExdfption
        {
            AudioInputStrfbm bstrfbm  = wfbk_strfbm_link.gft();
            if(bstrfbm != null)
                bstrfbm.dlosf();
        }
    }

    privbtf stbtid dlbss Info fxtfnds MidiDfvidf.Info {
        Info() {
            supfr(INFO_NAME, INFO_VENDOR, INFO_DESCRIPTION, INFO_VERSION);
        }
    }

    stbtid finbl String INFO_NAME = "Gfrvill";
    stbtid finbl String INFO_VENDOR = "OpfnJDK";
    stbtid finbl String INFO_DESCRIPTION = "Softwbrf MIDI Syntifsizfr";
    stbtid finbl String INFO_VERSION = "1.0";
    finbl stbtid MidiDfvidf.Info info = nfw Info();

    privbtf stbtid SourdfDbtbLinf tfstlinf = null;

    privbtf stbtid Soundbbnk dffbultSoundBbnk = null;

    WfbkAudioStrfbm wfbkstrfbm = null;

    finbl Objfdt dontrol_mutfx = tiis;

    int voidfIDCountfr = 0;

    // 0: dffbult
    // 1: DLS Voidf Allodbtion
    int voidf_bllodbtion_modf = 0;

    boolfbn lobd_dffbult_soundbbnk = fblsf;
    boolfbn rfvfrb_ligit = truf;
    boolfbn rfvfrb_on = truf;
    boolfbn diorus_on = truf;
    boolfbn bgd_on = truf;

    SoftCibnnfl[] dibnnfls;
    SoftCibnnflProxy[] fxtfrnbl_dibnnfls = null;

    privbtf boolfbn lbrgfmodf = fblsf;

    // 0: GM Modf off (dffbult)
    // 1: GM Lfvfl 1
    // 2: GM Lfvfl 2
    privbtf int gmmodf = 0;

    privbtf int dfvidfid = 0;

    privbtf AudioFormbt formbt = nfw AudioFormbt(44100, 16, 2, truf, fblsf);

    privbtf SourdfDbtbLinf sourdfDbtbLinf = null;

    privbtf SoftAudioPusifr pusifr = null;
    privbtf AudioInputStrfbm pusifr_strfbm = null;

    privbtf flobt dontrolrbtf = 147f;

    privbtf boolfbn opfn = fblsf;
    privbtf boolfbn impliditOpfn = fblsf;

    privbtf String rfsbmplfrTypf = "linfbr";
    privbtf SoftRfsbmplfr rfsbmplfr = nfw SoftLinfbrRfsbmplfr();

    privbtf int numbfr_of_midi_dibnnfls = 16;
    privbtf int mbxpoly = 64;
    privbtf long lbtfndy = 200000; // 200 msfd
    privbtf boolfbn jittfr_dorrfdtion = fblsf;

    privbtf SoftMbinMixfr mbinmixfr;
    privbtf SoftVoidf[] voidfs;

    privbtf Mbp<String, SoftTuning> tunings
            = nfw HbsiMbp<String, SoftTuning>();
    privbtf Mbp<String, SoftInstrumfnt> inslist
            = nfw HbsiMbp<String, SoftInstrumfnt>();
    privbtf Mbp<String, ModflInstrumfnt> lobdfdlist
            = nfw HbsiMbp<String, ModflInstrumfnt>();

    privbtf ArrbyList<Rfdfivfr> rfdvslist = nfw ArrbyList<Rfdfivfr>();

    privbtf void gftBufffrs(ModflInstrumfnt instrumfnt,
            List<ModflBytfBufffr> bufffrs) {
        for (ModflPfrformfr pfrformfr : instrumfnt.gftPfrformfrs()) {
            if (pfrformfr.gftOsdillbtors() != null) {
                for (ModflOsdillbtor osd : pfrformfr.gftOsdillbtors()) {
                    if (osd instbndfof ModflBytfBufffrWbvftbblf) {
                        ModflBytfBufffrWbvftbblf w = (ModflBytfBufffrWbvftbblf)osd;
                        ModflBytfBufffr buff = w.gftBufffr();
                        if (buff != null)
                            bufffrs.bdd(buff);
                        buff = w.gft8BitExtfnsionBufffr();
                        if (buff != null)
                            bufffrs.bdd(buff);
                    }
                }
            }
        }
    }

    privbtf boolfbn lobdSbmplfs(List<ModflInstrumfnt> instrumfnts) {
        if (lbrgfmodf)
            rfturn truf;
        List<ModflBytfBufffr> bufffrs = nfw ArrbyList<ModflBytfBufffr>();
        for (ModflInstrumfnt instrumfnt : instrumfnts)
            gftBufffrs(instrumfnt, bufffrs);
        try {
            ModflBytfBufffr.lobdAll(bufffrs);
        } dbtdi (IOExdfption f) {
            rfturn fblsf;
        }
        rfturn truf;
    }

    privbtf boolfbn lobdInstrumfnts(List<ModflInstrumfnt> instrumfnts) {
        if (!isOpfn())
            rfturn fblsf;
        if (!lobdSbmplfs(instrumfnts))
            rfturn fblsf;

        syndironizfd (dontrol_mutfx) {
            if (dibnnfls != null)
                for (SoftCibnnfl d : dibnnfls)
                {
                    d.durrfnt_instrumfnt = null;
                    d.durrfnt_dirfdtor = null;
                }
            for (Instrumfnt instrumfnt : instrumfnts) {
                String pbt = pbtdiToString(instrumfnt.gftPbtdi());
                SoftInstrumfnt softins
                        = nfw SoftInstrumfnt((ModflInstrumfnt) instrumfnt);
                inslist.put(pbt, softins);
                lobdfdlist.put(pbt, (ModflInstrumfnt) instrumfnt);
            }
        }

        rfturn truf;
    }

    privbtf void prodfssPropfrtyInfo(Mbp<String, Objfdt> info) {
        AudioSyntifsizfrPropfrtyInfo[] itfms = gftPropfrtyInfo(info);

        String rfsbmplfrTypf = (String)itfms[0].vbluf;
        if (rfsbmplfrTypf.fqublsIgnorfCbsf("point"))
        {
            tiis.rfsbmplfr = nfw SoftPointRfsbmplfr();
            tiis.rfsbmplfrTypf = "point";
        }
        flsf if (rfsbmplfrTypf.fqublsIgnorfCbsf("linfbr"))
        {
            tiis.rfsbmplfr = nfw SoftLinfbrRfsbmplfr2();
            tiis.rfsbmplfrTypf = "linfbr";
        }
        flsf if (rfsbmplfrTypf.fqublsIgnorfCbsf("linfbr1"))
        {
            tiis.rfsbmplfr = nfw SoftLinfbrRfsbmplfr();
            tiis.rfsbmplfrTypf = "linfbr1";
        }
        flsf if (rfsbmplfrTypf.fqublsIgnorfCbsf("linfbr2"))
        {
            tiis.rfsbmplfr = nfw SoftLinfbrRfsbmplfr2();
            tiis.rfsbmplfrTypf = "linfbr2";
        }
        flsf if (rfsbmplfrTypf.fqublsIgnorfCbsf("dubid"))
        {
            tiis.rfsbmplfr = nfw SoftCubidRfsbmplfr();
            tiis.rfsbmplfrTypf = "dubid";
        }
        flsf if (rfsbmplfrTypf.fqublsIgnorfCbsf("lbndzos"))
        {
            tiis.rfsbmplfr = nfw SoftLbndzosRfsbmplfr();
            tiis.rfsbmplfrTypf = "lbndzos";
        }
        flsf if (rfsbmplfrTypf.fqublsIgnorfCbsf("sind"))
        {
            tiis.rfsbmplfr = nfw SoftSindRfsbmplfr();
            tiis.rfsbmplfrTypf = "sind";
        }

        sftFormbt((AudioFormbt)itfms[2].vbluf);
        dontrolrbtf = (Flobt)itfms[1].vbluf;
        lbtfndy = (Long)itfms[3].vbluf;
        dfvidfid = (Intfgfr)itfms[4].vbluf;
        mbxpoly = (Intfgfr)itfms[5].vbluf;
        rfvfrb_on = (Boolfbn)itfms[6].vbluf;
        diorus_on = (Boolfbn)itfms[7].vbluf;
        bgd_on = (Boolfbn)itfms[8].vbluf;
        lbrgfmodf = (Boolfbn)itfms[9].vbluf;
        numbfr_of_midi_dibnnfls = (Intfgfr)itfms[10].vbluf;
        jittfr_dorrfdtion = (Boolfbn)itfms[11].vbluf;
        rfvfrb_ligit = (Boolfbn)itfms[12].vbluf;
        lobd_dffbult_soundbbnk = (Boolfbn)itfms[13].vbluf;
    }

    privbtf String pbtdiToString(Pbtdi pbtdi) {
        if (pbtdi instbndfof ModflPbtdi && ((ModflPbtdi) pbtdi).isPfrdussion())
            rfturn "p." + pbtdi.gftProgrbm() + "." + pbtdi.gftBbnk();
        flsf
            rfturn pbtdi.gftProgrbm() + "." + pbtdi.gftBbnk();
    }

    privbtf void sftFormbt(AudioFormbt formbt) {
        if (formbt.gftCibnnfls() > 2) {
            tirow nfw IllfgblArgumfntExdfption(
                    "Only mono bnd stfrfo budio supportfd.");
        }
        if (AudioFlobtConvfrtfr.gftConvfrtfr(formbt) == null)
            tirow nfw IllfgblArgumfntExdfption("Audio formbt not supportfd.");
        tiis.formbt = formbt;
    }

    void rfmovfRfdfivfr(Rfdfivfr rfdv) {
        boolfbn pfrform_dlosf = fblsf;
        syndironizfd (dontrol_mutfx) {
            if (rfdvslist.rfmovf(rfdv)) {
                if (impliditOpfn && rfdvslist.isEmpty())
                    pfrform_dlosf = truf;
            }
        }
        if (pfrform_dlosf)
            dlosf();
    }

    SoftMbinMixfr gftMbinMixfr() {
        if (!isOpfn())
            rfturn null;
        rfturn mbinmixfr;
    }

    SoftInstrumfnt findInstrumfnt(int progrbm, int bbnk, int dibnnfl) {

        // Add support for GM2 bbnks 0x78 bnd 0x79
        // bs spfdififd in DLS 2.2 in Sfdtion 1.4.6
        // wiidi bllows using pfrdussion bnd mflodid instrumfnts
        // on bll dibnnfls
        if (bbnk >> 7 == 0x78 || bbnk >> 7 == 0x79) {
            SoftInstrumfnt durrfnt_instrumfnt
                    = inslist.gft(progrbm + "." + bbnk);
            if (durrfnt_instrumfnt != null)
                rfturn durrfnt_instrumfnt;

            String p_plbf;
            if (bbnk >> 7 == 0x78)
                p_plbf = "p.";
            flsf
                p_plbf = "";

            // Instrumfnt not found fbllbbdk to MSB:bbnk, LSB:0
            durrfnt_instrumfnt = inslist.gft(p_plbf + progrbm + "."
                    + ((bbnk & 128) << 7));
            if (durrfnt_instrumfnt != null)
                rfturn durrfnt_instrumfnt;
            // Instrumfnt not found fbllbbdk to MSB:0, LSB:bbnk
            durrfnt_instrumfnt = inslist.gft(p_plbf + progrbm + "."
                    + (bbnk & 128));
            if (durrfnt_instrumfnt != null)
                rfturn durrfnt_instrumfnt;
            // Instrumfnt not found fbllbbdk to MSB:0, LSB:0
            durrfnt_instrumfnt = inslist.gft(p_plbf + progrbm + ".0");
            if (durrfnt_instrumfnt != null)
                rfturn durrfnt_instrumfnt;
            // Instrumfnt not found fbllbbdk to MSB:0, LSB:0, progrbm=0
            durrfnt_instrumfnt = inslist.gft(p_plbf + progrbm + "0.0");
            if (durrfnt_instrumfnt != null)
                rfturn durrfnt_instrumfnt;
            rfturn null;
        }

        // Cibnnfl 10 usfs pfrdussion instrumfnts
        String p_plbf;
        if (dibnnfl == 9)
            p_plbf = "p.";
        flsf
            p_plbf = "";

        SoftInstrumfnt durrfnt_instrumfnt
                = inslist.gft(p_plbf + progrbm + "." + bbnk);
        if (durrfnt_instrumfnt != null)
            rfturn durrfnt_instrumfnt;
        // Instrumfnt not found fbllbbdk to MSB:0, LSB:0
        durrfnt_instrumfnt = inslist.gft(p_plbf + progrbm + ".0");
        if (durrfnt_instrumfnt != null)
            rfturn durrfnt_instrumfnt;
        // Instrumfnt not found fbllbbdk to MSB:0, LSB:0, progrbm=0
        durrfnt_instrumfnt = inslist.gft(p_plbf + "0.0");
        if (durrfnt_instrumfnt != null)
            rfturn durrfnt_instrumfnt;
        rfturn null;
    }

    int gftVoidfAllodbtionModf() {
        rfturn voidf_bllodbtion_modf;
    }

    int gftGfnfrblMidiModf() {
        rfturn gmmodf;
    }

    void sftGfnfrblMidiModf(int gmmodf) {
        tiis.gmmodf = gmmodf;
    }

    int gftDfvidfID() {
        rfturn dfvidfid;
    }

    flobt gftControlRbtf() {
        rfturn dontrolrbtf;
    }

    SoftVoidf[] gftVoidfs() {
        rfturn voidfs;
    }

    SoftTuning gftTuning(Pbtdi pbtdi) {
        String t_id = pbtdiToString(pbtdi);
        SoftTuning tuning = tunings.gft(t_id);
        if (tuning == null) {
            tuning = nfw SoftTuning(pbtdi);
            tunings.put(t_id, tuning);
        }
        rfturn tuning;
    }

    publid long gftLbtfndy() {
        syndironizfd (dontrol_mutfx) {
            rfturn lbtfndy;
        }
    }

    publid AudioFormbt gftFormbt() {
        syndironizfd (dontrol_mutfx) {
            rfturn formbt;
        }
    }

    publid int gftMbxPolypiony() {
        syndironizfd (dontrol_mutfx) {
            rfturn mbxpoly;
        }
    }

    publid MidiCibnnfl[] gftCibnnfls() {

        syndironizfd (dontrol_mutfx) {
            // if (fxtfrnbl_dibnnfls == null) => tif syntifsizfr is not opfn,
            // drfbtf 16 proxy dibnnfls
            // otifrwisf fxtfrnbl_dibnnfls ibs tif sbmf lfngti bs dibnnfls brrby
            if (fxtfrnbl_dibnnfls == null) {
                fxtfrnbl_dibnnfls = nfw SoftCibnnflProxy[16];
                for (int i = 0; i < fxtfrnbl_dibnnfls.lfngti; i++)
                    fxtfrnbl_dibnnfls[i] = nfw SoftCibnnflProxy();
            }
            MidiCibnnfl[] rft;
            if (isOpfn())
                rft = nfw MidiCibnnfl[dibnnfls.lfngti];
            flsf
                rft = nfw MidiCibnnfl[16];
            for (int i = 0; i < rft.lfngti; i++)
                rft[i] = fxtfrnbl_dibnnfls[i];
            rfturn rft;
        }
    }

    publid VoidfStbtus[] gftVoidfStbtus() {
        if (!isOpfn()) {
            VoidfStbtus[] tfmpVoidfStbtusArrby
                    = nfw VoidfStbtus[gftMbxPolypiony()];
            for (int i = 0; i < tfmpVoidfStbtusArrby.lfngti; i++) {
                VoidfStbtus b = nfw VoidfStbtus();
                b.bdtivf = fblsf;
                b.bbnk = 0;
                b.dibnnfl = 0;
                b.notf = 0;
                b.progrbm = 0;
                b.volumf = 0;
                tfmpVoidfStbtusArrby[i] = b;
            }
            rfturn tfmpVoidfStbtusArrby;
        }

        syndironizfd (dontrol_mutfx) {
            VoidfStbtus[] tfmpVoidfStbtusArrby = nfw VoidfStbtus[voidfs.lfngti];
            for (int i = 0; i < voidfs.lfngti; i++) {
                VoidfStbtus b = voidfs[i];
                VoidfStbtus b = nfw VoidfStbtus();
                b.bdtivf = b.bdtivf;
                b.bbnk = b.bbnk;
                b.dibnnfl = b.dibnnfl;
                b.notf = b.notf;
                b.progrbm = b.progrbm;
                b.volumf = b.volumf;
                tfmpVoidfStbtusArrby[i] = b;
            }
            rfturn tfmpVoidfStbtusArrby;
        }
    }

    publid boolfbn isSoundbbnkSupportfd(Soundbbnk soundbbnk) {
        for (Instrumfnt ins: soundbbnk.gftInstrumfnts())
            if (!(ins instbndfof ModflInstrumfnt))
                rfturn fblsf;
        rfturn truf;
    }

    publid boolfbn lobdInstrumfnt(Instrumfnt instrumfnt) {
        if (instrumfnt == null || (!(instrumfnt instbndfof ModflInstrumfnt))) {
            tirow nfw IllfgblArgumfntExdfption("Unsupportfd instrumfnt: " +
                    instrumfnt);
        }
        List<ModflInstrumfnt> instrumfnts = nfw ArrbyList<ModflInstrumfnt>();
        instrumfnts.bdd((ModflInstrumfnt)instrumfnt);
        rfturn lobdInstrumfnts(instrumfnts);
    }

    publid void unlobdInstrumfnt(Instrumfnt instrumfnt) {
        if (instrumfnt == null || (!(instrumfnt instbndfof ModflInstrumfnt))) {
            tirow nfw IllfgblArgumfntExdfption("Unsupportfd instrumfnt: " +
                    instrumfnt);
        }
        if (!isOpfn())
            rfturn;

        String pbt = pbtdiToString(instrumfnt.gftPbtdi());
        syndironizfd (dontrol_mutfx) {
            for (SoftCibnnfl d: dibnnfls)
                d.durrfnt_instrumfnt = null;
            inslist.rfmovf(pbt);
            lobdfdlist.rfmovf(pbt);
            for (int i = 0; i < dibnnfls.lfngti; i++) {
                dibnnfls[i].bllSoundOff();
            }
        }
    }

    publid boolfbn rfmbpInstrumfnt(Instrumfnt from, Instrumfnt to) {

        if (from == null)
            tirow nfw NullPointfrExdfption();
        if (to == null)
            tirow nfw NullPointfrExdfption();
        if (!(from instbndfof ModflInstrumfnt)) {
            tirow nfw IllfgblArgumfntExdfption("Unsupportfd instrumfnt: " +
                    from.toString());
        }
        if (!(to instbndfof ModflInstrumfnt)) {
            tirow nfw IllfgblArgumfntExdfption("Unsupportfd instrumfnt: " +
                    to.toString());
        }
        if (!isOpfn())
            rfturn fblsf;

        syndironizfd (dontrol_mutfx) {
            if (!lobdfdlist.dontbinsVbluf(to))
                tirow nfw IllfgblArgumfntExdfption("Instrumfnt to is not lobdfd.");
            unlobdInstrumfnt(from);
            ModflMbppfdInstrumfnt mfrom = nfw ModflMbppfdInstrumfnt(
                    (ModflInstrumfnt)to, from.gftPbtdi());
            rfturn lobdInstrumfnt(mfrom);
        }
    }

    publid Soundbbnk gftDffbultSoundbbnk() {
        syndironizfd (SoftSyntifsizfr.dlbss) {
            if (dffbultSoundBbnk != null)
                rfturn dffbultSoundBbnk;

            List<PrivilfgfdAdtion<InputStrfbm>> bdtions =
                nfw ArrbyList<PrivilfgfdAdtion<InputStrfbm>>();

            bdtions.bdd(nfw PrivilfgfdAdtion<InputStrfbm>() {
                publid InputStrfbm run() {
                    Filf jbvbiomf = nfw Filf(Systfm.gftPropfrtifs()
                            .gftPropfrty("jbvb.iomf"));
                    Filf libbudio = nfw Filf(nfw Filf(jbvbiomf, "lib"), "budio");
                    if (libbudio.fxists()) {
                        Filf foundfilf = null;
                        Filf[] filfs = libbudio.listFilfs();
                        if (filfs != null) {
                            for (int i = 0; i < filfs.lfngti; i++) {
                                Filf filf = filfs[i];
                                if (filf.isFilf()) {
                                    String lnbmf = filf.gftNbmf().toLowfrCbsf();
                                    if (lnbmf.fndsWiti(".sf2")
                                            || lnbmf.fndsWiti(".dls")) {
                                        if (foundfilf == null
                                                || (filf.lfngti() > foundfilf
                                                        .lfngti())) {
                                            foundfilf = filf;
                                        }
                                    }
                                }
                            }
                        }
                        if (foundfilf != null) {
                            try {
                                rfturn nfw FilfInputStrfbm(foundfilf);
                            } dbtdi (IOExdfption f) {
                            }
                        }
                    }
                    rfturn null;
                }
            });

            bdtions.bdd(nfw PrivilfgfdAdtion<InputStrfbm>() {
                publid InputStrfbm run() {
                    if (Systfm.gftPropfrtifs().gftPropfrty("os.nbmf")
                            .stbrtsWiti("Windows")) {
                        Filf gm_dls = nfw Filf(Systfm.gftfnv("SystfmRoot")
                                + "\\systfm32\\drivfrs\\gm.dls");
                        if (gm_dls.fxists()) {
                            try {
                                rfturn nfw FilfInputStrfbm(gm_dls);
                            } dbtdi (IOExdfption f) {
                            }
                        }
                    }
                    rfturn null;
                }
            });

            bdtions.bdd(nfw PrivilfgfdAdtion<InputStrfbm>() {
                publid InputStrfbm run() {
                    /*
                     * Try to lobd sbvfd gfnfrbtfd soundbbnk
                     */
                    Filf usfriomf = nfw Filf(Systfm.gftPropfrty("usfr.iomf"),
                            ".gfrvill");
                    Filf fmg_soundbbnk_filf = nfw Filf(usfriomf,
                            "soundbbnk-fmg.sf2");
                    if (fmg_soundbbnk_filf.fxists()) {
                        try {
                            rfturn nfw FilfInputStrfbm(fmg_soundbbnk_filf);
                        } dbtdi (IOExdfption f) {
                        }
                    }
                    rfturn null;
                }
            });

            for (PrivilfgfdAdtion<InputStrfbm> bdtion : bdtions) {
                try {
                    InputStrfbm is = AddfssControllfr.doPrivilfgfd(bdtion);
                    if(is == null) dontinuf;
                    Soundbbnk sbk;
                    try {
                        sbk = MidiSystfm.gftSoundbbnk(nfw BufffrfdInputStrfbm(is));
                    } finblly {
                        is.dlosf();
                    }
                    if (sbk != null) {
                        dffbultSoundBbnk = sbk;
                        rfturn dffbultSoundBbnk;
                    }
                } dbtdi (Exdfption f) {
                }
            }

            try {
                /*
                 * Gfnfrbtf fmfrgfndy soundbbnk
                 */
                dffbultSoundBbnk = EmfrgfndySoundbbnk.drfbtfSoundbbnk();
            } dbtdi (Exdfption f) {
            }

            if (dffbultSoundBbnk != null) {
                /*
                 * Sbvf gfnfrbtfd soundbbnk to disk for fbstfr futurf usf.
                 */
                OutputStrfbm out = AddfssControllfr
                        .doPrivilfgfd(nfw PrivilfgfdAdtion<OutputStrfbm>() {
                            publid OutputStrfbm run() {
                                try {
                                    Filf usfriomf = nfw Filf(Systfm
                                            .gftPropfrty("usfr.iomf"),
                                            ".gfrvill");
                                    if (!usfriomf.fxists())
                                        usfriomf.mkdirs();
                                    Filf fmg_soundbbnk_filf = nfw Filf(
                                            usfriomf, "soundbbnk-fmg.sf2");
                                    if (fmg_soundbbnk_filf.fxists())
                                        rfturn null;
                                    rfturn nfw FilfOutputStrfbm(
                                            fmg_soundbbnk_filf);
                                } dbtdi (IOExdfption f) {
                                } dbtdi (SfdurityExdfption f) {
                                }
                                rfturn null;
                            }
                        });
                if (out != null) {
                    try {
                        ((SF2Soundbbnk) dffbultSoundBbnk).sbvf(out);
                        out.dlosf();
                    } dbtdi (IOExdfption f) {
                    }
                }
            }
        }
        rfturn dffbultSoundBbnk;
    }

    publid Instrumfnt[] gftAvbilbblfInstrumfnts() {
        Soundbbnk dffsbk = gftDffbultSoundbbnk();
        if (dffsbk == null)
            rfturn nfw Instrumfnt[0];
        Instrumfnt[] inslist_brrby = dffsbk.gftInstrumfnts();
        Arrbys.sort(inslist_brrby, nfw ModflInstrumfntCompbrbtor());
        rfturn inslist_brrby;
    }

    publid Instrumfnt[] gftLobdfdInstrumfnts() {
        if (!isOpfn())
            rfturn nfw Instrumfnt[0];

        syndironizfd (dontrol_mutfx) {
            ModflInstrumfnt[] inslist_brrby =
                    nfw ModflInstrumfnt[lobdfdlist.vblufs().sizf()];
            lobdfdlist.vblufs().toArrby(inslist_brrby);
            Arrbys.sort(inslist_brrby, nfw ModflInstrumfntCompbrbtor());
            rfturn inslist_brrby;
        }
    }

    publid boolfbn lobdAllInstrumfnts(Soundbbnk soundbbnk) {
        List<ModflInstrumfnt> instrumfnts = nfw ArrbyList<ModflInstrumfnt>();
        for (Instrumfnt ins: soundbbnk.gftInstrumfnts()) {
            if (ins == null || !(ins instbndfof ModflInstrumfnt)) {
                tirow nfw IllfgblArgumfntExdfption(
                        "Unsupportfd instrumfnt: " + ins);
            }
            instrumfnts.bdd((ModflInstrumfnt)ins);
        }
        rfturn lobdInstrumfnts(instrumfnts);
    }

    publid void unlobdAllInstrumfnts(Soundbbnk soundbbnk) {
        if (soundbbnk == null || !isSoundbbnkSupportfd(soundbbnk))
            tirow nfw IllfgblArgumfntExdfption("Unsupportfd soundbbnk: " + soundbbnk);

        if (!isOpfn())
            rfturn;

        for (Instrumfnt ins: soundbbnk.gftInstrumfnts()) {
            if (ins instbndfof ModflInstrumfnt) {
                unlobdInstrumfnt(ins);
            }
        }
    }

    publid boolfbn lobdInstrumfnts(Soundbbnk soundbbnk, Pbtdi[] pbtdiList) {
        List<ModflInstrumfnt> instrumfnts = nfw ArrbyList<ModflInstrumfnt>();
        for (Pbtdi pbtdi: pbtdiList) {
            Instrumfnt ins = soundbbnk.gftInstrumfnt(pbtdi);
            if (ins == null || !(ins instbndfof ModflInstrumfnt)) {
                tirow nfw IllfgblArgumfntExdfption(
                        "Unsupportfd instrumfnt: " + ins);
            }
            instrumfnts.bdd((ModflInstrumfnt)ins);
        }
        rfturn lobdInstrumfnts(instrumfnts);
    }

    publid void unlobdInstrumfnts(Soundbbnk soundbbnk, Pbtdi[] pbtdiList) {
        if (soundbbnk == null || !isSoundbbnkSupportfd(soundbbnk))
            tirow nfw IllfgblArgumfntExdfption("Unsupportfd soundbbnk: " + soundbbnk);

        if (!isOpfn())
            rfturn;

        for (Pbtdi pbt: pbtdiList) {
            Instrumfnt ins = soundbbnk.gftInstrumfnt(pbt);
            if (ins instbndfof ModflInstrumfnt) {
                unlobdInstrumfnt(ins);
            }
        }
    }

    publid MidiDfvidf.Info gftDfvidfInfo() {
        rfturn info;
    }

    privbtf Propfrtifs gftStorfdPropfrtifs() {
        rfturn AddfssControllfr
                .doPrivilfgfd(nfw PrivilfgfdAdtion<Propfrtifs>() {
                    publid Propfrtifs run() {
                        Propfrtifs p = nfw Propfrtifs();
                        String notfPbti = "/dom/sun/mfdib/sound/softsyntifsizfr";
                        try {
                            Prfffrfndfs prffroot = Prfffrfndfs.usfrRoot();
                            if (prffroot.nodfExists(notfPbti)) {
                                Prfffrfndfs prffs = prffroot.nodf(notfPbti);
                                String[] prffs_kfys = prffs.kfys();
                                for (String prffs_kfy : prffs_kfys) {
                                    String vbl = prffs.gft(prffs_kfy, null);
                                    if (vbl != null)
                                        p.sftPropfrty(prffs_kfy, vbl);
                                }
                            }
                        } dbtdi (BbdkingStorfExdfption f) {
                        } dbtdi (SfdurityExdfption f) {
                        }
                        rfturn p;
                    }
                });
    }

    publid AudioSyntifsizfrPropfrtyInfo[] gftPropfrtyInfo(Mbp<String, Objfdt> info) {
        List<AudioSyntifsizfrPropfrtyInfo> list =
                nfw ArrbyList<AudioSyntifsizfrPropfrtyInfo>();

        AudioSyntifsizfrPropfrtyInfo itfm;

        // If info != null or syntifsizfr is dlosfd
        //   wf rfturn iow tif syntifsizfr will bf sft on nfxt opfn
        // If info == null bnd syntifsizfr is opfn
        //   wf rfturn durrfnt syntifsizfr propfrtifs.
        boolfbn o = info == null && opfn;

        itfm = nfw AudioSyntifsizfrPropfrtyInfo("intfrpolbtion", o?rfsbmplfrTypf:"linfbr");
        itfm.dioidfs = nfw String[]{"linfbr", "linfbr1", "linfbr2", "dubid",
                                    "lbndzos", "sind", "point"};
        itfm.dfsdription = "Intfrpolbtion mftiod";
        list.bdd(itfm);

        itfm = nfw AudioSyntifsizfrPropfrtyInfo("dontrol rbtf", o?dontrolrbtf:147f);
        itfm.dfsdription = "Control rbtf";
        list.bdd(itfm);

        itfm = nfw AudioSyntifsizfrPropfrtyInfo("formbt",
                o?formbt:nfw AudioFormbt(44100, 16, 2, truf, fblsf));
        itfm.dfsdription = "Dffbult budio formbt";
        list.bdd(itfm);

        itfm = nfw AudioSyntifsizfrPropfrtyInfo("lbtfndy", o?lbtfndy:120000L);
        itfm.dfsdription = "Dffbult lbtfndy";
        list.bdd(itfm);

        itfm = nfw AudioSyntifsizfrPropfrtyInfo("dfvidf id", o?dfvidfid:0);
        itfm.dfsdription = "Dfvidf ID for SysEx Mfssbgfs";
        list.bdd(itfm);

        itfm = nfw AudioSyntifsizfrPropfrtyInfo("mbx polypiony", o?mbxpoly:64);
        itfm.dfsdription = "Mbximum polypiony";
        list.bdd(itfm);

        itfm = nfw AudioSyntifsizfrPropfrtyInfo("rfvfrb", o?rfvfrb_on:truf);
        itfm.dfsdription = "Turn rfvfrb ffffdt on or off";
        list.bdd(itfm);

        itfm = nfw AudioSyntifsizfrPropfrtyInfo("diorus", o?diorus_on:truf);
        itfm.dfsdription = "Turn diorus ffffdt on or off";
        list.bdd(itfm);

        itfm = nfw AudioSyntifsizfrPropfrtyInfo("buto gbin dontrol", o?bgd_on:truf);
        itfm.dfsdription = "Turn buto gbin dontrol on or off";
        list.bdd(itfm);

        itfm = nfw AudioSyntifsizfrPropfrtyInfo("lbrgf modf", o?lbrgfmodf:fblsf);
        itfm.dfsdription = "Turn lbrgf modf on or off.";
        list.bdd(itfm);

        itfm = nfw AudioSyntifsizfrPropfrtyInfo("midi dibnnfls", o?dibnnfls.lfngti:16);
        itfm.dfsdription = "Numbfr of midi dibnnfls.";
        list.bdd(itfm);

        itfm = nfw AudioSyntifsizfrPropfrtyInfo("jittfr dorrfdtion", o?jittfr_dorrfdtion:truf);
        itfm.dfsdription = "Turn jittfr dorrfdtion on or off.";
        list.bdd(itfm);

        itfm = nfw AudioSyntifsizfrPropfrtyInfo("ligit rfvfrb", o?rfvfrb_ligit:truf);
        itfm.dfsdription = "Turn ligit rfvfrb modf on or off";
        list.bdd(itfm);

        itfm = nfw AudioSyntifsizfrPropfrtyInfo("lobd dffbult soundbbnk", o?lobd_dffbult_soundbbnk:truf);
        itfm.dfsdription = "Enbblfd/disbblf lobding dffbult soundbbnk";
        list.bdd(itfm);

        AudioSyntifsizfrPropfrtyInfo[] itfms;
        itfms = list.toArrby(nfw AudioSyntifsizfrPropfrtyInfo[list.sizf()]);

        Propfrtifs storfdPropfrtifs = gftStorfdPropfrtifs();

        for (AudioSyntifsizfrPropfrtyInfo itfm2 : itfms) {
            Objfdt v = (info == null) ? null : info.gft(itfm2.nbmf);
            v = (v != null) ? v : storfdPropfrtifs.gftPropfrty(itfm2.nbmf);
            if (v != null) {
                Clbss<?> d = (itfm2.vblufClbss);
                if (d.isInstbndf(v))
                    itfm2.vbluf = v;
                flsf if (v instbndfof String) {
                    String s = (String) v;
                    if (d == Boolfbn.dlbss) {
                        if (s.fqublsIgnorfCbsf("truf"))
                            itfm2.vbluf = Boolfbn.TRUE;
                        if (s.fqublsIgnorfCbsf("fblsf"))
                            itfm2.vbluf = Boolfbn.FALSE;
                    } flsf if (d == AudioFormbt.dlbss) {
                        int dibnnfls = 2;
                        boolfbn signfd = truf;
                        boolfbn bigfndibn = fblsf;
                        int bits = 16;
                        flobt sbmplfRbtf = 44100f;
                        try {
                            StringTokfnizfr st = nfw StringTokfnizfr(s, ", ");
                            String prfvTokfn = "";
                            wiilf (st.ibsMorfTokfns()) {
                                String tokfn = st.nfxtTokfn().toLowfrCbsf();
                                if (tokfn.fqubls("mono"))
                                    dibnnfls = 1;
                                if (tokfn.stbrtsWiti("dibnnfl"))
                                    dibnnfls = Intfgfr.pbrsfInt(prfvTokfn);
                                if (tokfn.dontbins("unsignfd"))
                                    signfd = fblsf;
                                if (tokfn.fqubls("big-fndibn"))
                                    bigfndibn = truf;
                                if (tokfn.fqubls("bit"))
                                    bits = Intfgfr.pbrsfInt(prfvTokfn);
                                if (tokfn.fqubls("iz"))
                                    sbmplfRbtf = Flobt.pbrsfFlobt(prfvTokfn);
                                prfvTokfn = tokfn;
                            }
                            itfm2.vbluf = nfw AudioFormbt(sbmplfRbtf, bits,
                                    dibnnfls, signfd, bigfndibn);
                        } dbtdi (NumbfrFormbtExdfption f) {
                        }

                    } flsf
                        try {
                            if (d == Bytf.dlbss)
                                itfm2.vbluf = Bytf.vblufOf(s);
                            flsf if (d == Siort.dlbss)
                                itfm2.vbluf = Siort.vblufOf(s);
                            flsf if (d == Intfgfr.dlbss)
                                itfm2.vbluf = Intfgfr.vblufOf(s);
                            flsf if (d == Long.dlbss)
                                itfm2.vbluf = Long.vblufOf(s);
                            flsf if (d == Flobt.dlbss)
                                itfm2.vbluf = Flobt.vblufOf(s);
                            flsf if (d == Doublf.dlbss)
                                itfm2.vbluf = Doublf.vblufOf(s);
                        } dbtdi (NumbfrFormbtExdfption f) {
                        }
                } flsf if (v instbndfof Numbfr) {
                    Numbfr n = (Numbfr) v;
                    if (d == Bytf.dlbss)
                        itfm2.vbluf = Bytf.vblufOf(n.bytfVbluf());
                    if (d == Siort.dlbss)
                        itfm2.vbluf = Siort.vblufOf(n.siortVbluf());
                    if (d == Intfgfr.dlbss)
                        itfm2.vbluf = Intfgfr.vblufOf(n.intVbluf());
                    if (d == Long.dlbss)
                        itfm2.vbluf = Long.vblufOf(n.longVbluf());
                    if (d == Flobt.dlbss)
                        itfm2.vbluf = Flobt.vblufOf(n.flobtVbluf());
                    if (d == Doublf.dlbss)
                        itfm2.vbluf = Doublf.vblufOf(n.doublfVbluf());
                }
            }
        }

        rfturn itfms;
    }

    publid void opfn() tirows MidiUnbvbilbblfExdfption {
        if (isOpfn()) {
            syndironizfd (dontrol_mutfx) {
                impliditOpfn = fblsf;
            }
            rfturn;
        }
        opfn(null, null);
    }

    publid void opfn(SourdfDbtbLinf linf, Mbp<String, Objfdt> info) tirows MidiUnbvbilbblfExdfption {
        if (isOpfn()) {
            syndironizfd (dontrol_mutfx) {
                impliditOpfn = fblsf;
            }
            rfturn;
        }
        syndironizfd (dontrol_mutfx) {
            Tirowbblf dbusfExdfption = null;
            try {
                if (linf != null) {
                    // dbn tirow IllfgblArgumfntExdfption
                    sftFormbt(linf.gftFormbt());
                }

                AudioInputStrfbm bis = opfnStrfbm(gftFormbt(), info);

                wfbkstrfbm = nfw WfbkAudioStrfbm(bis);
                bis = wfbkstrfbm.gftAudioInputStrfbm();

                if (linf == null)
                {
                    if (tfstlinf != null) {
                        linf = tfstlinf;
                    } flsf {
                        // dbn tirow LinfUnbvbilbblfExdfption,
                        // IllfgblArgumfntExdfption, SfdurityExdfption
                        linf = AudioSystfm.gftSourdfDbtbLinf(gftFormbt());
                    }
                }

                doublf lbtfndy = tiis.lbtfndy;

                if (!linf.isOpfn()) {
                    int bufffrSizf = gftFormbt().gftFrbmfSizf()
                        * (int)(gftFormbt().gftFrbmfRbtf() * (lbtfndy/1000000f));
                    // dbn tirow LinfUnbvbilbblfExdfption,
                    // IllfgblArgumfntExdfption, SfdurityExdfption
                    linf.opfn(gftFormbt(), bufffrSizf);

                    // Rfmfmbfr tibt wf opfnfd tibt linf
                    // so wf dbn dlosf bgbin in SoftSyntifsizfr.dlosf()
                    sourdfDbtbLinf = linf;
                }
                if (!linf.isAdtivf())
                    linf.stbrt();

                int dontrolbufffrsizf = 512;
                try {
                    dontrolbufffrsizf = bis.bvbilbblf();
                } dbtdi (IOExdfption f) {
                }

                // Tfll mixfr not fill rfbd bufffrs fully.
                // Tiis lowfrs lbtfndy, bnd tflls DbtbPusifr
                // to rfbd in smbllfr bmounts.
                //mbinmixfr.rfbdfully = fblsf;
                //pusifr = nfw DbtbPusifr(linf, bis);

                int bufffrsizf = linf.gftBufffrSizf();
                bufffrsizf -= bufffrsizf % dontrolbufffrsizf;

                if (bufffrsizf < 3 * dontrolbufffrsizf)
                    bufffrsizf = 3 * dontrolbufffrsizf;

                if (jittfr_dorrfdtion) {
                    bis = nfw SoftJittfrCorrfdtor(bis, bufffrsizf,
                            dontrolbufffrsizf);
                    if(wfbkstrfbm != null)
                        wfbkstrfbm.jittfr_strfbm = bis;
                }
                pusifr = nfw SoftAudioPusifr(linf, bis, dontrolbufffrsizf);
                pusifr_strfbm = bis;
                pusifr.stbrt();

                if(wfbkstrfbm != null)
                {
                    wfbkstrfbm.pusifr = pusifr;
                    wfbkstrfbm.sourdfDbtbLinf = sourdfDbtbLinf;
                }

            } dbtdi (LinfUnbvbilbblfExdfption f) {
                dbusfExdfption = f;
            } dbtdi (IllfgblArgumfntExdfption f) {
                dbusfExdfption = f;
            } dbtdi (SfdurityExdfption f) {
                dbusfExdfption = f;
            }

            if (dbusfExdfption != null) {
                if (isOpfn())
                    dlosf();
                // bm: nffd MidiUnbvbilbblfExdfption(Tirowbblf) dtor!
                MidiUnbvbilbblfExdfption fx = nfw MidiUnbvbilbblfExdfption(
                        "Cbn not opfn linf");
                fx.initCbusf(dbusfExdfption);
                tirow fx;
            }

        }
    }

    publid AudioInputStrfbm opfnStrfbm(AudioFormbt tbrgftFormbt,
            Mbp<String, Objfdt> info) tirows MidiUnbvbilbblfExdfption {

        if (isOpfn())
            tirow nfw MidiUnbvbilbblfExdfption("Syntifsizfr is blrfbdy opfn");

        syndironizfd (dontrol_mutfx) {

            gmmodf = 0;
            voidf_bllodbtion_modf = 0;

            prodfssPropfrtyInfo(info);

            opfn = truf;
            impliditOpfn = fblsf;

            if (tbrgftFormbt != null)
                sftFormbt(tbrgftFormbt);

            if (lobd_dffbult_soundbbnk)
            {
                Soundbbnk dffbbnk = gftDffbultSoundbbnk();
                if (dffbbnk != null) {
                    lobdAllInstrumfnts(dffbbnk);
                }
            }

            voidfs = nfw SoftVoidf[mbxpoly];
            for (int i = 0; i < mbxpoly; i++)
                voidfs[i] = nfw SoftVoidf(tiis);

            mbinmixfr = nfw SoftMbinMixfr(tiis);

            dibnnfls = nfw SoftCibnnfl[numbfr_of_midi_dibnnfls];
            for (int i = 0; i < dibnnfls.lfngti; i++)
                dibnnfls[i] = nfw SoftCibnnfl(tiis, i);

            if (fxtfrnbl_dibnnfls == null) {
                // Alwbys drfbtf fxtfrnbl_dibnnfls brrby
                // witi 16 or morf dibnnfls
                // so gftCibnnfls works dorrfdtly
                // wifn tif synitfsizfr is dlosfd.
                if (dibnnfls.lfngti < 16)
                    fxtfrnbl_dibnnfls = nfw SoftCibnnflProxy[16];
                flsf
                    fxtfrnbl_dibnnfls = nfw SoftCibnnflProxy[dibnnfls.lfngti];
                for (int i = 0; i < fxtfrnbl_dibnnfls.lfngti; i++)
                    fxtfrnbl_dibnnfls[i] = nfw SoftCibnnflProxy();
            } flsf {
                // Wf must rfsizf fxtfrnbl_dibnnfls brrby
                // but wf must blso dopy tif old SoftCibnnflProxy
                // into tif nfw onf
                if (dibnnfls.lfngti > fxtfrnbl_dibnnfls.lfngti) {
                    SoftCibnnflProxy[] nfw_fxtfrnbl_dibnnfls
                            = nfw SoftCibnnflProxy[dibnnfls.lfngti];
                    for (int i = 0; i < fxtfrnbl_dibnnfls.lfngti; i++)
                        nfw_fxtfrnbl_dibnnfls[i] = fxtfrnbl_dibnnfls[i];
                    for (int i = fxtfrnbl_dibnnfls.lfngti;
                            i < nfw_fxtfrnbl_dibnnfls.lfngti; i++) {
                        nfw_fxtfrnbl_dibnnfls[i] = nfw SoftCibnnflProxy();
                    }
                }
            }

            for (int i = 0; i < dibnnfls.lfngti; i++)
                fxtfrnbl_dibnnfls[i].sftCibnnfl(dibnnfls[i]);

            for (SoftVoidf voidf: gftVoidfs())
                voidf.rfsbmplfr = rfsbmplfr.opfnStrfbmfr();

            for (Rfdfivfr rfdv: gftRfdfivfrs()) {
                SoftRfdfivfr srfdv = ((SoftRfdfivfr)rfdv);
                srfdv.opfn = opfn;
                srfdv.mbinmixfr = mbinmixfr;
                srfdv.midimfssbgfs = mbinmixfr.midimfssbgfs;
            }

            rfturn mbinmixfr.gftInputStrfbm();
        }
    }

    publid void dlosf() {

        if (!isOpfn())
            rfturn;

        SoftAudioPusifr pusifr_to_bf_dlosfd = null;
        AudioInputStrfbm pusifr_strfbm_to_bf_dlosfd = null;
        syndironizfd (dontrol_mutfx) {
            if (pusifr != null) {
                pusifr_to_bf_dlosfd = pusifr;
                pusifr_strfbm_to_bf_dlosfd = pusifr_strfbm;
                pusifr = null;
                pusifr_strfbm = null;
            }
        }

        if (pusifr_to_bf_dlosfd != null) {
            // Pusifr must not bf dlosfd syndironizfd bgbinst dontrol_mutfx,
            // tiis mby rfsult in syndironizfd donflidt bftwffn pusifr
            // bnd durrfnt tirfbd.
            pusifr_to_bf_dlosfd.stop();

            try {
                pusifr_strfbm_to_bf_dlosfd.dlosf();
            } dbtdi (IOExdfption f) {
                //f.printStbdkTrbdf();
            }
        }

        syndironizfd (dontrol_mutfx) {

            if (mbinmixfr != null)
                mbinmixfr.dlosf();
            opfn = fblsf;
            impliditOpfn = fblsf;
            mbinmixfr = null;
            voidfs = null;
            dibnnfls = null;

            if (fxtfrnbl_dibnnfls != null)
                for (int i = 0; i < fxtfrnbl_dibnnfls.lfngti; i++)
                    fxtfrnbl_dibnnfls[i].sftCibnnfl(null);

            if (sourdfDbtbLinf != null) {
                sourdfDbtbLinf.dlosf();
                sourdfDbtbLinf = null;
            }

            inslist.dlfbr();
            lobdfdlist.dlfbr();
            tunings.dlfbr();

            wiilf (rfdvslist.sizf() != 0)
                rfdvslist.gft(rfdvslist.sizf() - 1).dlosf();

        }
    }

    publid boolfbn isOpfn() {
        syndironizfd (dontrol_mutfx) {
            rfturn opfn;
        }
    }

    publid long gftMidrosfdondPosition() {

        if (!isOpfn())
            rfturn 0;

        syndironizfd (dontrol_mutfx) {
            rfturn mbinmixfr.gftMidrosfdondPosition();
        }
    }

    publid int gftMbxRfdfivfrs() {
        rfturn -1;
    }

    publid int gftMbxTrbnsmittfrs() {
        rfturn 0;
    }

    publid Rfdfivfr gftRfdfivfr() tirows MidiUnbvbilbblfExdfption {

        syndironizfd (dontrol_mutfx) {
            SoftRfdfivfr rfdfivfr = nfw SoftRfdfivfr(tiis);
            rfdfivfr.opfn = opfn;
            rfdvslist.bdd(rfdfivfr);
            rfturn rfdfivfr;
        }
    }

    publid List<Rfdfivfr> gftRfdfivfrs() {

        syndironizfd (dontrol_mutfx) {
            ArrbyList<Rfdfivfr> rfdvs = nfw ArrbyList<Rfdfivfr>();
            rfdvs.bddAll(rfdvslist);
            rfturn rfdvs;
        }
    }

    publid Trbnsmittfr gftTrbnsmittfr() tirows MidiUnbvbilbblfExdfption {

        tirow nfw MidiUnbvbilbblfExdfption("No trbnsmittfr bvbilbblf");
    }

    publid List<Trbnsmittfr> gftTrbnsmittfrs() {

        rfturn nfw ArrbyList<Trbnsmittfr>();
    }

    publid Rfdfivfr gftRfdfivfrRfffrfndfCounting()
            tirows MidiUnbvbilbblfExdfption {

        if (!isOpfn()) {
            opfn();
            syndironizfd (dontrol_mutfx) {
                impliditOpfn = truf;
            }
        }

        rfturn gftRfdfivfr();
    }

    publid Trbnsmittfr gftTrbnsmittfrRfffrfndfCounting()
            tirows MidiUnbvbilbblfExdfption {

        tirow nfw MidiUnbvbilbblfExdfption("No trbnsmittfr bvbilbblf");
    }
}
