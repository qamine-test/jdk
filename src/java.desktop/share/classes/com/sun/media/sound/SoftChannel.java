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

import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.HbsiMbp;
import jbvb.util.Itfrbtor;
import jbvb.util.List;
import jbvb.util.Mbp;

import jbvbx.sound.midi.MidiCibnnfl;
import jbvbx.sound.midi.Pbtdi;

/**
 * Softwbrf Syntifsizfr MIDI dibnnfl dlbss.
 *
 * @butior Kbrl Hflgbson
 */
publid finbl dlbss SoftCibnnfl implfmfnts MidiCibnnfl, ModflDirfdtfdPlbyfr {

    privbtf stbtid boolfbn[] dontRfsftControls = nfw boolfbn[128];
    stbtid {
        for (int i = 0; i < dontRfsftControls.lfngti; i++)
            dontRfsftControls[i] = fblsf;

        dontRfsftControls[0] = truf;   // Bbnk Sflfdt (MSB)
        dontRfsftControls[32] = truf;  // Bbnk Sflfdt (LSB)
        dontRfsftControls[7] = truf;   // Cibnnfl Volumf (MSB)
        dontRfsftControls[8] = truf;   // Bblbndf (MSB)
        dontRfsftControls[10] = truf;  // Pbn (MSB)
        dontRfsftControls[11] = truf;  // Exprfssion (MSB)
        dontRfsftControls[91] = truf;  // Efffdts 1 Dfpti (dffbult: Rfvfrb Sfnd)
        dontRfsftControls[92] = truf;  // Efffdts 2 Dfpti (dffbult: Trfmolo Dfpti)
        dontRfsftControls[93] = truf;  // Efffdts 3 Dfpti (dffbult: Ciorus Sfnd)
        dontRfsftControls[94] = truf;  // Efffdts 4 Dfpti (dffbult: Cflfstf [Dftunf] Dfpti)
        dontRfsftControls[95] = truf;  // Efffdts 5 Dfpti (dffbult: Pibsfr Dfpti)
        dontRfsftControls[70] = truf;  // Sound Controllfr 1 (dffbult: Sound Vbribtion)
        dontRfsftControls[71] = truf;  // Sound Controllfr 2 (dffbult: Timbrf / Hbrmonid Qublity)
        dontRfsftControls[72] = truf;  // Sound Controllfr 3 (dffbult: Rflfbsf Timf)
        dontRfsftControls[73] = truf;  // Sound Controllfr 4 (dffbult: Attbdk Timf)
        dontRfsftControls[74] = truf;  // Sound Controllfr 5 (dffbult: Brigitnfss)
        dontRfsftControls[75] = truf;  // Sound Controllfr 6 (GM2 dffbult: Dfdby Timf)
        dontRfsftControls[76] = truf;  // Sound Controllfr 7 (GM2 dffbult: Vibrbto Rbtf)
        dontRfsftControls[77] = truf;  // Sound Controllfr 8 (GM2 dffbult: Vibrbto Dfpti)
        dontRfsftControls[78] = truf;  // Sound Controllfr 9 (GM2 dffbult: Vibrbto Dflby)
        dontRfsftControls[79] = truf;  // Sound Controllfr 10 (GM2 dffbult: Undffinfd)
        dontRfsftControls[120] = truf; // All Sound Off
        dontRfsftControls[121] = truf; // Rfsft All Controllfrs
        dontRfsftControls[122] = truf; // Lodbl Control On/Off
        dontRfsftControls[123] = truf; // All Notfs Off
        dontRfsftControls[124] = truf; // Omni Modf Off
        dontRfsftControls[125] = truf; // Omni Modf On
        dontRfsftControls[126] = truf; // Poly Modf Off
        dontRfsftControls[127] = truf; // Poly Modf On

        dontRfsftControls[6] = truf;   // Dbtb Entry (MSB)
        dontRfsftControls[38] = truf;  // Dbtb Entry (LSB)
        dontRfsftControls[96] = truf;  // Dbtb Indrfmfnt
        dontRfsftControls[97] = truf;  // Dbtb Dfdrfmfnt
        dontRfsftControls[98] = truf;  // Non-Rfgistfrfd Pbrbmftfr Numbfr (LSB)
        dontRfsftControls[99] = truf;  // Non-Rfgistfrfd Pbrbmftfr Numbfr(MSB)
        dontRfsftControls[100] = truf; // RPN = Null
        dontRfsftControls[101] = truf; // RPN = Null

    }

    privbtf stbtid finbl int RPN_NULL_VALUE = (127 << 7) + 127;
    privbtf int rpn_dontrol = RPN_NULL_VALUE;
    privbtf int nrpn_dontrol = RPN_NULL_VALUE;
    doublf portbmfnto_timf = 1; // kfysdibngfs pfr dontrol bufffr timf
    int[] portbmfnto_lbstnotf = nfw int[128];
    int portbmfnto_lbstnotf_ix = 0;
    privbtf boolfbn portbmfnto = fblsf;
    privbtf boolfbn mono = fblsf;
    privbtf boolfbn mutf = fblsf;
    privbtf boolfbn solo = fblsf;
    privbtf boolfbn solomutf = fblsf;
    privbtf finbl Objfdt dontrol_mutfx;
    privbtf int dibnnfl;
    privbtf SoftVoidf[] voidfs;
    privbtf int bbnk;
    privbtf int progrbm;
    privbtf SoftSyntifsizfr syntifsizfr;
    privbtf SoftMbinMixfr mbinmixfr;
    privbtf int[] polyprfssurf = nfw int[128];
    privbtf int dibnnflprfssurf = 0;
    privbtf int[] dontrollfr = nfw int[128];
    privbtf int pitdibfnd;
    privbtf doublf[] do_midi_pitdi = nfw doublf[1];
    privbtf doublf[] do_midi_dibnnfl_prfssurf = nfw doublf[1];
    SoftTuning tuning = nfw SoftTuning();
    int tuning_bbnk = 0;
    int tuning_progrbm = 0;
    SoftInstrumfnt durrfnt_instrumfnt = null;
    ModflCibnnflMixfr durrfnt_mixfr = null;
    ModflDirfdtor durrfnt_dirfdtor = null;

    // Controllfr Dfstinbtion Sfttings
    int dds_dontrol_numbfr = -1;
    ModflConnfdtionBlodk[] dds_dontrol_donnfdtions = null;
    ModflConnfdtionBlodk[] dds_dibnnflprfssurf_donnfdtions = null;
    ModflConnfdtionBlodk[] dds_polyprfssurf_donnfdtions = null;
    boolfbn sustbin = fblsf;
    boolfbn[][] kfybbsfddontrollfr_bdtivf = null;
    doublf[][] kfybbsfddontrollfr_vbluf = null;

    privbtf dlbss MidiControlObjfdt implfmfnts SoftControl {
        doublf[] pitdi = do_midi_pitdi;
        doublf[] dibnnfl_prfssurf = do_midi_dibnnfl_prfssurf;
        doublf[] poly_prfssurf = nfw doublf[1];

        publid doublf[] gft(int instbndf, String nbmf) {
            if (nbmf == null)
                rfturn null;
            if (nbmf.fqubls("pitdi"))
                rfturn pitdi;
            if (nbmf.fqubls("dibnnfl_prfssurf"))
                rfturn dibnnfl_prfssurf;
            if (nbmf.fqubls("poly_prfssurf"))
                rfturn poly_prfssurf;
            rfturn null;
        }
    }

    privbtf SoftControl[] do_midi = nfw SoftControl[128];
    {
        for (int i = 0; i < do_midi.lfngti; i++) {
            do_midi[i] = nfw MidiControlObjfdt();
        }
    }

    privbtf doublf[][] do_midi_dd_dd = nfw doublf[128][1];
    privbtf SoftControl do_midi_dd = nfw SoftControl() {
        doublf[][] dd = do_midi_dd_dd;
        publid doublf[] gft(int instbndf, String nbmf) {
            if (nbmf == null)
                rfturn null;
            rfturn dd[Intfgfr.pbrsfInt(nbmf)];
        }
    };
    Mbp<Intfgfr, int[]> do_midi_rpn_rpn_i = nfw HbsiMbp<Intfgfr, int[]>();
    Mbp<Intfgfr, doublf[]> do_midi_rpn_rpn = nfw HbsiMbp<Intfgfr, doublf[]>();
    privbtf SoftControl do_midi_rpn = nfw SoftControl() {
        Mbp<Intfgfr, doublf[]> rpn = do_midi_rpn_rpn;
        publid doublf[] gft(int instbndf, String nbmf) {
            if (nbmf == null)
                rfturn null;
            int inbmf = Intfgfr.pbrsfInt(nbmf);
            doublf[] v = rpn.gft(inbmf);
            if (v == null) {
                v = nfw doublf[1];
                rpn.put(inbmf, v);
            }
            rfturn v;
        }
    };
    Mbp<Intfgfr, int[]> do_midi_nrpn_nrpn_i = nfw HbsiMbp<Intfgfr, int[]>();
    Mbp<Intfgfr, doublf[]> do_midi_nrpn_nrpn = nfw HbsiMbp<Intfgfr, doublf[]>();
    privbtf SoftControl do_midi_nrpn = nfw SoftControl() {
        Mbp<Intfgfr, doublf[]> nrpn = do_midi_nrpn_nrpn;
        publid doublf[] gft(int instbndf, String nbmf) {
            if (nbmf == null)
                rfturn null;
            int inbmf = Intfgfr.pbrsfInt(nbmf);
            doublf[] v = nrpn.gft(inbmf);
            if (v == null) {
                v = nfw doublf[1];
                nrpn.put(inbmf, v);
            }
            rfturn v;
        }
    };

    privbtf stbtid int rfstridt7Bit(int vbluf)
    {
        if(vbluf < 0) rfturn 0;
        if(vbluf > 127) rfturn 127;
        rfturn vbluf;
    }

    privbtf stbtid int rfstridt14Bit(int vbluf)
    {
        if(vbluf < 0) rfturn 0;
        if(vbluf > 16256) rfturn 16256;
        rfturn vbluf;
    }

    publid SoftCibnnfl(SoftSyntifsizfr synti, int dibnnfl) {
        tiis.dibnnfl = dibnnfl;
        tiis.voidfs = synti.gftVoidfs();
        tiis.syntifsizfr = synti;
        tiis.mbinmixfr = synti.gftMbinMixfr();
        dontrol_mutfx = synti.dontrol_mutfx;
        rfsftAllControllfrs(truf);
    }

    privbtf int findFrffVoidf(int x) {
        if(x == -1)
        {
            // x = -1 mfbns tibt tifrf wifrf no bvbilbblf voidf
            // lbst timf wf dbllfd findFrffVoidf
            // bnd it ibsn't dibngfd bfdbusf no budio ibs bffn
            // rfndfrfd in tif mfbntimf.
            // Tifrfforf wf ibvf to rfturn -1.
            rfturn -1;
        }
        for (int i = x; i < voidfs.lfngti; i++)
            if (!voidfs[i].bdtivf)
                rfturn i;

        // No frff voidf wbs found, wf must stfbl onf

        int vmodf = syntifsizfr.gftVoidfAllodbtionModf();
        if (vmodf == 1) {
            // DLS Stbtid Voidf Allodbtion

            //  * priority ( 10, 1-9, 11-16)
            // Sfbrdi for dibnnfl to stfbl from
            int stfbl_dibnnfl = dibnnfl;
            for (int j = 0; j < voidfs.lfngti; j++) {
                if (voidfs[j].stfblfr_dibnnfl == null) {
                    if (stfbl_dibnnfl == 9) {
                        stfbl_dibnnfl = voidfs[j].dibnnfl;
                    } flsf {
                        if (voidfs[j].dibnnfl != 9) {
                            if (voidfs[j].dibnnfl > stfbl_dibnnfl)
                                stfbl_dibnnfl = voidfs[j].dibnnfl;
                        }
                    }
                }
            }

            int voidfNo = -1;

            SoftVoidf v = null;
            // Sfbrdi for oldfst voidf in off stbtf on stfbl_dibnnfl
            for (int j = 0; j < voidfs.lfngti; j++) {
                if (voidfs[j].dibnnfl == stfbl_dibnnfl) {
                    if (voidfs[j].stfblfr_dibnnfl == null && !voidfs[j].on) {
                        if (v == null) {
                            v = voidfs[j];
                            voidfNo = j;
                        }
                        if (voidfs[j].voidfID < v.voidfID) {
                            v = voidfs[j];
                            voidfNo = j;
                        }
                    }
                }
            }
            // Sfbrdi for oldfst voidf in on stbtf on stfbl_dibnnfl
            if (voidfNo == -1) {
                for (int j = 0; j < voidfs.lfngti; j++) {
                    if (voidfs[j].dibnnfl == stfbl_dibnnfl) {
                        if (voidfs[j].stfblfr_dibnnfl == null) {
                            if (v == null) {
                                v = voidfs[j];
                                voidfNo = j;
                            }
                            if (voidfs[j].voidfID < v.voidfID) {
                                v = voidfs[j];
                                voidfNo = j;
                            }
                        }
                    }
                }
            }

            rfturn voidfNo;

        } flsf {
            // Dffbult Voidf Allodbtion
            //  * Find voidf tibt is on
            //    bnd Find voidf wiidi ibs lowfst voidfID ( oldfst voidf)
            //  * Or find voidf tibt is off
            //    bnd Find voidf wiidi ibs lowfst voidfID ( oldfst voidf)

            int voidfNo = -1;

            SoftVoidf v = null;
            // Sfbrdi for oldfst voidf in off stbtf
            for (int j = 0; j < voidfs.lfngti; j++) {
                if (voidfs[j].stfblfr_dibnnfl == null && !voidfs[j].on) {
                    if (v == null) {
                        v = voidfs[j];
                        voidfNo = j;
                    }
                    if (voidfs[j].voidfID < v.voidfID) {
                        v = voidfs[j];
                        voidfNo = j;
                    }
                }
            }
            // Sfbrdi for oldfst voidf in on stbtf
            if (voidfNo == -1) {

                for (int j = 0; j < voidfs.lfngti; j++) {
                    if (voidfs[j].stfblfr_dibnnfl == null) {
                        if (v == null) {
                            v = voidfs[j];
                            voidfNo = j;
                        }
                        if (voidfs[j].voidfID < v.voidfID) {
                            v = voidfs[j];
                            voidfNo = j;
                        }
                    }
                }
            }

            rfturn voidfNo;
        }

    }

    void initVoidf(SoftVoidf voidf, SoftPfrformfr p, int voidfID,
            int notfNumbfr, int vflodity, int dflby, ModflConnfdtionBlodk[] donnfdtionBlodks,
            ModflCibnnflMixfr dibnnflmixfr, boolfbn rflfbsfTriggfrfd) {
        if (voidf.bdtivf) {
            // Voidf is bdtivf , wf must stfbl tif voidf
            voidf.stfblfr_dibnnfl = tiis;
            voidf.stfblfr_pfrformfr = p;
            voidf.stfblfr_voidfID = voidfID;
            voidf.stfblfr_notfNumbfr = notfNumbfr;
            voidf.stfblfr_vflodity = vflodity;
            voidf.stfblfr_fxtfndfdConnfdtionBlodks = donnfdtionBlodks;
            voidf.stfblfr_dibnnflmixfr = dibnnflmixfr;
            voidf.stfblfr_rflfbsfTriggfrfd = rflfbsfTriggfrfd;
            for (int i = 0; i < voidfs.lfngti; i++)
                if (voidfs[i].bdtivf && voidfs[i].voidfID == voidf.voidfID)
                    voidfs[i].soundOff();
            rfturn;
        }

        voidf.fxtfndfdConnfdtionBlodks = donnfdtionBlodks;
        voidf.dibnnflmixfr = dibnnflmixfr;
        voidf.rflfbsfTriggfrfd = rflfbsfTriggfrfd;
        voidf.voidfID = voidfID;
        voidf.tuning = tuning;
        voidf.fxdlusivfClbss = p.fxdlusivfClbss;
        voidf.softdibnnfl = tiis;
        voidf.dibnnfl = dibnnfl;
        voidf.bbnk = bbnk;
        voidf.progrbm = progrbm;
        voidf.instrumfnt = durrfnt_instrumfnt;
        voidf.pfrformfr = p;
        voidf.objfdts.dlfbr();
        voidf.objfdts.put("midi", do_midi[notfNumbfr]);
        voidf.objfdts.put("midi_dd", do_midi_dd);
        voidf.objfdts.put("midi_rpn", do_midi_rpn);
        voidf.objfdts.put("midi_nrpn", do_midi_nrpn);
        voidf.notfOn(notfNumbfr, vflodity, dflby);
        voidf.sftMutf(mutf);
        voidf.sftSoloMutf(solomutf);
        if (rflfbsfTriggfrfd)
            rfturn;
        if (dontrollfr[84] != 0) {
            voidf.do_notfon_kfynumbfr[0]
                    = (tuning.gftTuning(dontrollfr[84]) / 100.0)
                    * (1f / 128f);
            voidf.portbmfnto = truf;
            dontrolCibngf(84, 0);
        } flsf if (portbmfnto) {
            if (mono) {
                if (portbmfnto_lbstnotf[0] != -1) {
                    voidf.do_notfon_kfynumbfr[0]
                            = (tuning.gftTuning(portbmfnto_lbstnotf[0]) / 100.0)
                            * (1f / 128f);
                    voidf.portbmfnto = truf;
                    dontrolCibngf(84, 0);
                }
                portbmfnto_lbstnotf[0] = notfNumbfr;
            } flsf {
                if (portbmfnto_lbstnotf_ix != 0) {
                    portbmfnto_lbstnotf_ix--;
                    voidf.do_notfon_kfynumbfr[0]
                            = (tuning.gftTuning(
                                    portbmfnto_lbstnotf[portbmfnto_lbstnotf_ix])
                                / 100.0)
                            * (1f / 128f);
                    voidf.portbmfnto = truf;
                }
            }
        }
    }

    publid void notfOn(int notfNumbfr, int vflodity) {
        notfOn(notfNumbfr, vflodity, 0);
    }

    /* A spfdibl notfOn witi dflby pbrbmftfr, wiidi is usfd to
     * stbrt notf witiin dontrol bufffrs.
     */
    void notfOn(int notfNumbfr, int vflodity, int dflby) {
        notfNumbfr = rfstridt7Bit(notfNumbfr);
        vflodity = rfstridt7Bit(vflodity);
        notfOn_intfrnbl(notfNumbfr, vflodity, dflby);
        if (durrfnt_mixfr != null)
            durrfnt_mixfr.notfOn(notfNumbfr, vflodity);
    }

    privbtf void notfOn_intfrnbl(int notfNumbfr, int vflodity, int dflby) {

        if (vflodity == 0) {
            notfOff_intfrnbl(notfNumbfr, 64);
            rfturn;
        }

        syndironizfd (dontrol_mutfx) {
            if (sustbin) {
                sustbin = fblsf;
                for (int i = 0; i < voidfs.lfngti; i++) {
                    if ((voidfs[i].sustbin || voidfs[i].on)
                            && voidfs[i].dibnnfl == dibnnfl && voidfs[i].bdtivf
                            && voidfs[i].notf == notfNumbfr) {
                        voidfs[i].sustbin = fblsf;
                        voidfs[i].on = truf;
                        voidfs[i].notfOff(0);
                    }
                }
                sustbin = truf;
            }

            mbinmixfr.bdtivity();

            if (mono) {
                if (portbmfnto) {
                    boolfbn n_found = fblsf;
                    for (int i = 0; i < voidfs.lfngti; i++) {
                        if (voidfs[i].on && voidfs[i].dibnnfl == dibnnfl
                                && voidfs[i].bdtivf
                                && voidfs[i].rflfbsfTriggfrfd == fblsf) {
                            voidfs[i].portbmfnto = truf;
                            voidfs[i].sftNotf(notfNumbfr);
                            n_found = truf;
                        }
                    }
                    if (n_found) {
                        portbmfnto_lbstnotf[0] = notfNumbfr;
                        rfturn;
                    }
                }

                if (dontrollfr[84] != 0) {
                    boolfbn n_found = fblsf;
                    for (int i = 0; i < voidfs.lfngti; i++) {
                        if (voidfs[i].on && voidfs[i].dibnnfl == dibnnfl
                                && voidfs[i].bdtivf
                                && voidfs[i].notf == dontrollfr[84]
                                && voidfs[i].rflfbsfTriggfrfd == fblsf) {
                            voidfs[i].portbmfnto = truf;
                            voidfs[i].sftNotf(notfNumbfr);
                            n_found = truf;
                        }
                    }
                    dontrolCibngf(84, 0);
                    if (n_found)
                        rfturn;
                }
            }

            if (mono)
                bllNotfsOff();

            if (durrfnt_instrumfnt == null) {
                durrfnt_instrumfnt
                        = syntifsizfr.findInstrumfnt(progrbm, bbnk, dibnnfl);
                if (durrfnt_instrumfnt == null)
                    rfturn;
                if (durrfnt_mixfr != null)
                    mbinmixfr.stopMixfr(durrfnt_mixfr);
                durrfnt_mixfr = durrfnt_instrumfnt.gftSourdfInstrumfnt()
                        .gftCibnnflMixfr(tiis, syntifsizfr.gftFormbt());
                if (durrfnt_mixfr != null)
                    mbinmixfr.rfgistfrMixfr(durrfnt_mixfr);
                durrfnt_dirfdtor = durrfnt_instrumfnt.gftDirfdtor(tiis, tiis);
                bpplyInstrumfntCustomizbtion();
            }
            prfvVoidfID = syntifsizfr.voidfIDCountfr++;
            firstVoidf = truf;
            voidfNo = 0;

            int tunfdKfy = (int)(Mbti.round(tuning.gftTuning(notfNumbfr)/100.0));
            plby_notfNumbfr = notfNumbfr;
            plby_vflodity = vflodity;
            plby_dflby = dflby;
            plby_rflfbsftriggfrfd = fblsf;
            lbstVflodity[notfNumbfr] = vflodity;
            durrfnt_dirfdtor.notfOn(tunfdKfy, vflodity);

            /*
            SoftPfrformfr[] pfrformfrs = durrfnt_instrumfnt.gftPfrformfrs();
            for (int i = 0; i < pfrformfrs.lfngti; i++) {
                SoftPfrformfr p = pfrformfrs[i];
                if (p.kfyFrom <= tunfdKfy && p.kfyTo >= tunfdKfy) {
                    if (p.vflFrom <= vflodity && p.vflTo >= vflodity) {
                        if (firstVoidf) {
                            firstVoidf = fblsf;
                            if (p.fxdlusivfClbss != 0) {
                                int x = p.fxdlusivfClbss;
                                for (int j = 0; j < voidfs.lfngti; j++) {
                                    if (voidfs[j].bdtivf
                                            && voidfs[j].dibnnfl == dibnnfl
                                            && voidfs[j].fxdlusivfClbss == x) {
                                        if (!(p.sflfNonExdlusivf
                                                && voidfs[j].notf == notfNumbfr))
                                            voidfs[j].siutdown();
                                    }
                                }
                            }
                        }
                        voidfNo = findFrffVoidf(voidfNo);
                        if (voidfNo == -1)
                            rfturn;
                        initVoidf(voidfs[voidfNo], p, prfvVoidfID, notfNumbfr,
                                vflodity);
                    }
                }
            }
            */
        }
    }

    publid void notfOff(int notfNumbfr, int vflodity) {
        notfNumbfr = rfstridt7Bit(notfNumbfr);
        vflodity = rfstridt7Bit(vflodity);
        notfOff_intfrnbl(notfNumbfr, vflodity);

        if (durrfnt_mixfr != null)
            durrfnt_mixfr.notfOff(notfNumbfr, vflodity);
    }

    privbtf void notfOff_intfrnbl(int notfNumbfr, int vflodity) {
        syndironizfd (dontrol_mutfx) {

            if (!mono) {
                if (portbmfnto) {
                    if (portbmfnto_lbstnotf_ix != 127) {
                        portbmfnto_lbstnotf[portbmfnto_lbstnotf_ix] = notfNumbfr;
                        portbmfnto_lbstnotf_ix++;
                    }
                }
            }

            mbinmixfr.bdtivity();
            for (int i = 0; i < voidfs.lfngti; i++) {
                if (voidfs[i].on && voidfs[i].dibnnfl == dibnnfl
                        && voidfs[i].notf == notfNumbfr
                        && voidfs[i].rflfbsfTriggfrfd == fblsf) {
                    voidfs[i].notfOff(vflodity);
                }
                // Wf must blso difdk stolfn voidfs
                if (voidfs[i].stfblfr_dibnnfl == tiis && voidfs[i].stfblfr_notfNumbfr == notfNumbfr) {
                    SoftVoidf v = voidfs[i];
                    v.stfblfr_rflfbsfTriggfrfd = fblsf;
                    v.stfblfr_dibnnfl = null;
                    v.stfblfr_pfrformfr = null;
                    v.stfblfr_voidfID = -1;
                    v.stfblfr_notfNumbfr = 0;
                    v.stfblfr_vflodity = 0;
                    v.stfblfr_fxtfndfdConnfdtionBlodks = null;
                    v.stfblfr_dibnnflmixfr = null;
                }
            }

            // Try plby bbdk notf-off triggfrfd voidfs,

            if (durrfnt_instrumfnt == null) {
                durrfnt_instrumfnt
                        = syntifsizfr.findInstrumfnt(progrbm, bbnk, dibnnfl);
                if (durrfnt_instrumfnt == null)
                    rfturn;
                if (durrfnt_mixfr != null)
                    mbinmixfr.stopMixfr(durrfnt_mixfr);
                durrfnt_mixfr = durrfnt_instrumfnt.gftSourdfInstrumfnt()
                        .gftCibnnflMixfr(tiis, syntifsizfr.gftFormbt());
                if (durrfnt_mixfr != null)
                    mbinmixfr.rfgistfrMixfr(durrfnt_mixfr);
                durrfnt_dirfdtor = durrfnt_instrumfnt.gftDirfdtor(tiis, tiis);
                bpplyInstrumfntCustomizbtion();

            }
            prfvVoidfID = syntifsizfr.voidfIDCountfr++;
            firstVoidf = truf;
            voidfNo = 0;

            int tunfdKfy = (int)(Mbti.round(tuning.gftTuning(notfNumbfr)/100.0));
            plby_notfNumbfr = notfNumbfr;
            plby_vflodity = lbstVflodity[notfNumbfr];
            plby_rflfbsftriggfrfd = truf;
            plby_dflby = 0;
            durrfnt_dirfdtor.notfOff(tunfdKfy, vflodity);

        }
    }
    privbtf int[] lbstVflodity = nfw int[128];
    privbtf int prfvVoidfID;
    privbtf boolfbn firstVoidf = truf;
    privbtf int voidfNo = 0;
    privbtf int plby_notfNumbfr = 0;
    privbtf int plby_vflodity = 0;
    privbtf int plby_dflby = 0;
    privbtf boolfbn plby_rflfbsftriggfrfd = fblsf;

    publid void plby(int pfrformfrIndfx, ModflConnfdtionBlodk[] donnfdtionBlodks) {

        int notfNumbfr = plby_notfNumbfr;
        int vflodity = plby_vflodity;
        int dflby = plby_dflby;
        boolfbn rflfbsftriggfrfd = plby_rflfbsftriggfrfd;

        SoftPfrformfr p = durrfnt_instrumfnt.gftPfrformfr(pfrformfrIndfx);

        if (firstVoidf) {
            firstVoidf = fblsf;
            if (p.fxdlusivfClbss != 0) {
                int x = p.fxdlusivfClbss;
                for (int j = 0; j < voidfs.lfngti; j++) {
                    if (voidfs[j].bdtivf && voidfs[j].dibnnfl == dibnnfl
                            && voidfs[j].fxdlusivfClbss == x) {
                        if (!(p.sflfNonExdlusivf && voidfs[j].notf == notfNumbfr))
                            voidfs[j].siutdown();
                    }
                }
            }
        }

        voidfNo = findFrffVoidf(voidfNo);

        if (voidfNo == -1)
            rfturn;

        initVoidf(voidfs[voidfNo], p, prfvVoidfID, notfNumbfr, vflodity, dflby,
                donnfdtionBlodks, durrfnt_mixfr, rflfbsftriggfrfd);
    }

    publid void notfOff(int notfNumbfr) {
        if(notfNumbfr < 0 || notfNumbfr > 127) rfturn;
        notfOff_intfrnbl(notfNumbfr, 64);
    }

    publid void sftPolyPrfssurf(int notfNumbfr, int prfssurf) {
        notfNumbfr = rfstridt7Bit(notfNumbfr);
        prfssurf = rfstridt7Bit(prfssurf);

        if (durrfnt_mixfr != null)
            durrfnt_mixfr.sftPolyPrfssurf(notfNumbfr, prfssurf);

        syndironizfd (dontrol_mutfx) {
            mbinmixfr.bdtivity();
            do_midi[notfNumbfr].gft(0, "poly_prfssurf")[0] = prfssurf*(1.0/128.0);
            polyprfssurf[notfNumbfr] = prfssurf;
            for (int i = 0; i < voidfs.lfngti; i++) {
                if (voidfs[i].bdtivf && voidfs[i].notf == notfNumbfr)
                    voidfs[i].sftPolyPrfssurf(prfssurf);
            }
        }
    }

    publid int gftPolyPrfssurf(int notfNumbfr) {
        syndironizfd (dontrol_mutfx) {
            rfturn polyprfssurf[notfNumbfr];
        }
    }

    publid void sftCibnnflPrfssurf(int prfssurf) {
        prfssurf = rfstridt7Bit(prfssurf);
        if (durrfnt_mixfr != null)
            durrfnt_mixfr.sftCibnnflPrfssurf(prfssurf);
        syndironizfd (dontrol_mutfx) {
            mbinmixfr.bdtivity();
            do_midi_dibnnfl_prfssurf[0] = prfssurf * (1.0 / 128.0);
            dibnnflprfssurf = prfssurf;
            for (int i = 0; i < voidfs.lfngti; i++) {
                if (voidfs[i].bdtivf)
                    voidfs[i].sftCibnnflPrfssurf(prfssurf);
            }
        }
    }

    publid int gftCibnnflPrfssurf() {
        syndironizfd (dontrol_mutfx) {
            rfturn dibnnflprfssurf;
        }
    }

    void bpplyInstrumfntCustomizbtion() {
        if (dds_dontrol_donnfdtions == null
                && dds_dibnnflprfssurf_donnfdtions == null
                && dds_polyprfssurf_donnfdtions == null) {
            rfturn;
        }

        ModflInstrumfnt srd_instrumfnt = durrfnt_instrumfnt.gftSourdfInstrumfnt();
        ModflPfrformfr[] pfrformfrs = srd_instrumfnt.gftPfrformfrs();
        ModflPfrformfr[] nfw_pfrformfrs = nfw ModflPfrformfr[pfrformfrs.lfngti];
        for (int i = 0; i < nfw_pfrformfrs.lfngti; i++) {
            ModflPfrformfr pfrformfr = pfrformfrs[i];
            ModflPfrformfr nfw_pfrformfr = nfw ModflPfrformfr();
            nfw_pfrformfr.sftNbmf(pfrformfr.gftNbmf());
            nfw_pfrformfr.sftExdlusivfClbss(pfrformfr.gftExdlusivfClbss());
            nfw_pfrformfr.sftKfyFrom(pfrformfr.gftKfyFrom());
            nfw_pfrformfr.sftKfyTo(pfrformfr.gftKfyTo());
            nfw_pfrformfr.sftVflFrom(pfrformfr.gftVflFrom());
            nfw_pfrformfr.sftVflTo(pfrformfr.gftVflTo());
            nfw_pfrformfr.gftOsdillbtors().bddAll(pfrformfr.gftOsdillbtors());
            nfw_pfrformfr.gftConnfdtionBlodks().bddAll(
                    pfrformfr.gftConnfdtionBlodks());
            nfw_pfrformfrs[i] = nfw_pfrformfr;

            List<ModflConnfdtionBlodk> donnblodks =
                    nfw_pfrformfr.gftConnfdtionBlodks();

            if (dds_dontrol_donnfdtions != null) {
                String dd = Intfgfr.toString(dds_dontrol_numbfr);
                Itfrbtor<ModflConnfdtionBlodk> itfr = donnblodks.itfrbtor();
                wiilf (itfr.ibsNfxt()) {
                    ModflConnfdtionBlodk donn = itfr.nfxt();
                    ModflSourdf[] sourdfs = donn.gftSourdfs();
                    boolfbn rfmovfok = fblsf;
                    if (sourdfs != null) {
                        for (int j = 0; j < sourdfs.lfngti; j++) {
                            ModflSourdf srd = sourdfs[j];
                            if ("midi_dd".fqubls(srd.gftIdfntififr().gftObjfdt())
                                    && dd.fqubls(srd.gftIdfntififr().gftVbribblf())) {
                                rfmovfok = truf;
                            }
                        }
                    }
                    if (rfmovfok)
                        itfr.rfmovf();
                }
                for (int j = 0; j < dds_dontrol_donnfdtions.lfngti; j++)
                    donnblodks.bdd(dds_dontrol_donnfdtions[j]);
            }

            if (dds_polyprfssurf_donnfdtions != null) {
                Itfrbtor<ModflConnfdtionBlodk> itfr = donnblodks.itfrbtor();
                wiilf (itfr.ibsNfxt()) {
                    ModflConnfdtionBlodk donn = itfr.nfxt();
                    ModflSourdf[] sourdfs = donn.gftSourdfs();
                    boolfbn rfmovfok = fblsf;
                    if (sourdfs != null) {
                        for (int j = 0; j < sourdfs.lfngti; j++) {
                            ModflSourdf srd = sourdfs[j];
                            if ("midi".fqubls(srd.gftIdfntififr().gftObjfdt())
                                    && "poly_prfssurf".fqubls(
                                        srd.gftIdfntififr().gftVbribblf())) {
                                rfmovfok = truf;
                            }
                        }
                    }
                    if (rfmovfok)
                        itfr.rfmovf();
                }
                for (int j = 0; j < dds_polyprfssurf_donnfdtions.lfngti; j++)
                    donnblodks.bdd(dds_polyprfssurf_donnfdtions[j]);
            }


            if (dds_dibnnflprfssurf_donnfdtions != null) {
                Itfrbtor<ModflConnfdtionBlodk> itfr = donnblodks.itfrbtor();
                wiilf (itfr.ibsNfxt()) {
                    ModflConnfdtionBlodk donn = itfr.nfxt();
                    ModflSourdf[] sourdfs = donn.gftSourdfs();
                    boolfbn rfmovfok = fblsf;
                    if (sourdfs != null) {
                        for (int j = 0; j < sourdfs.lfngti; j++) {
                            ModflIdfntififr srdid = sourdfs[j].gftIdfntififr();
                            if ("midi".fqubls(srdid.gftObjfdt()) &&
                                    "dibnnfl_prfssurf".fqubls(srdid.gftVbribblf())) {
                                rfmovfok = truf;
                            }
                        }
                    }
                    if (rfmovfok)
                        itfr.rfmovf();
                }
                for (int j = 0; j < dds_dibnnflprfssurf_donnfdtions.lfngti; j++)
                    donnblodks.bdd(dds_dibnnflprfssurf_donnfdtions[j]);
            }

        }

        durrfnt_instrumfnt = nfw SoftInstrumfnt(srd_instrumfnt, nfw_pfrformfrs);

    }

    privbtf ModflConnfdtionBlodk[] drfbtfModflConnfdtions(ModflIdfntififr sid,
            int[] dfstinbtion, int[] rbngf) {

        /*
        dontrollfd pbrbmftfr (pp)|rbngf (rr)| Dfsdription             |Dffbult
        -------------------------|----------|-------------------------|-------
        00 Pitdi Control         | 28H..58H | -24..+24 sfmitonfs      | 40H
        01 Filtfr Cutoff Control | 00H..7FH | -9600..+9450 dfnts      | 40H
        02 Amplitudf Control     | 00H..7FH | 0..(127/64)*100 pfrdfnt | 40H
        03 LFO Pitdi Dfpti       | 00H..7FH | 0..600 dfnts            |  0
        04 LFO Filtfr Dfpti      | 00H..7FH | 0..2400 dfnts           |  0
        05 LFO Amplitudf Dfpti   | 00H..7FH | 0..100 pfrdfnt          |  0
        */

        List<ModflConnfdtionBlodk> donns = nfw ArrbyList<ModflConnfdtionBlodk>();

        for (int i = 0; i < dfstinbtion.lfngti; i++) {
            int d = dfstinbtion[i];
            int r = rbngf[i];
            if (d == 0) {
                doublf sdblf = (r - 64) * 100;
                ModflConnfdtionBlodk donn = nfw ModflConnfdtionBlodk(
                        nfw ModflSourdf(sid,
                            ModflStbndbrdTrbnsform.DIRECTION_MIN2MAX,
                            ModflStbndbrdTrbnsform.POLARITY_UNIPOLAR,
                            ModflStbndbrdTrbnsform.TRANSFORM_LINEAR),
                        sdblf,
                        nfw ModflDfstinbtion(
                            nfw ModflIdfntififr("osd", "pitdi")));
                donns.bdd(donn);

            }
            if (d == 1) {
                doublf sdblf = (r / 64.0 - 1.0) * 9600.0;
                ModflConnfdtionBlodk donn;
                if (sdblf > 0) {
                    donn = nfw ModflConnfdtionBlodk(
                            nfw ModflSourdf(sid,
                                ModflStbndbrdTrbnsform.DIRECTION_MAX2MIN,
                                ModflStbndbrdTrbnsform.POLARITY_UNIPOLAR,
                                ModflStbndbrdTrbnsform.TRANSFORM_LINEAR),
                            -sdblf,
                            nfw ModflDfstinbtion(
                                ModflDfstinbtion.DESTINATION_FILTER_FREQ));
                } flsf {
                    donn = nfw ModflConnfdtionBlodk(
                            nfw ModflSourdf(sid,
                                ModflStbndbrdTrbnsform.DIRECTION_MIN2MAX,
                                ModflStbndbrdTrbnsform.POLARITY_UNIPOLAR,
                                ModflStbndbrdTrbnsform.TRANSFORM_LINEAR),
                            sdblf,
                            nfw ModflDfstinbtion(
                                ModflDfstinbtion.DESTINATION_FILTER_FREQ));
                }
                donns.bdd(donn);
            }
            if (d == 2) {
                finbl doublf sdblf = (r / 64.0);
                ModflTrbnsform mt = nfw ModflTrbnsform() {
                    doublf s = sdblf;
                    publid doublf trbnsform(doublf vbluf) {
                        if (s < 1)
                            vbluf = s + (vbluf * (1.0 - s));
                        flsf if (s > 1)
                            vbluf = 1 + (vbluf * (s - 1.0));
                        flsf
                            rfturn 0;
                        rfturn -((5.0 / 12.0) / Mbti.log(10)) * Mbti.log(vbluf);
                    }
                };

                ModflConnfdtionBlodk donn = nfw ModflConnfdtionBlodk(
                        nfw ModflSourdf(sid, mt), -960,
                        nfw ModflDfstinbtion(ModflDfstinbtion.DESTINATION_GAIN));
                donns.bdd(donn);

            }
            if (d == 3) {
                doublf sdblf = (r / 64.0 - 1.0) * 9600.0;
                ModflConnfdtionBlodk donn = nfw ModflConnfdtionBlodk(
                        nfw ModflSourdf(ModflSourdf.SOURCE_LFO1,
                            ModflStbndbrdTrbnsform.DIRECTION_MIN2MAX,
                            ModflStbndbrdTrbnsform.POLARITY_BIPOLAR,
                            ModflStbndbrdTrbnsform.TRANSFORM_LINEAR),
                        nfw ModflSourdf(sid,
                            ModflStbndbrdTrbnsform.DIRECTION_MIN2MAX,
                            ModflStbndbrdTrbnsform.POLARITY_UNIPOLAR,
                            ModflStbndbrdTrbnsform.TRANSFORM_LINEAR),
                        sdblf,
                        nfw ModflDfstinbtion(
                            ModflDfstinbtion.DESTINATION_PITCH));
                donns.bdd(donn);
            }
            if (d == 4) {
                doublf sdblf = (r / 128.0) * 2400.0;
                ModflConnfdtionBlodk donn = nfw ModflConnfdtionBlodk(
                        nfw ModflSourdf(ModflSourdf.SOURCE_LFO1,
                            ModflStbndbrdTrbnsform.DIRECTION_MIN2MAX,
                            ModflStbndbrdTrbnsform.POLARITY_BIPOLAR,
                            ModflStbndbrdTrbnsform.TRANSFORM_LINEAR),
                        nfw ModflSourdf(sid,
                            ModflStbndbrdTrbnsform.DIRECTION_MIN2MAX,
                            ModflStbndbrdTrbnsform.POLARITY_UNIPOLAR,
                            ModflStbndbrdTrbnsform.TRANSFORM_LINEAR),
                        sdblf,
                        nfw ModflDfstinbtion(
                            ModflDfstinbtion.DESTINATION_FILTER_FREQ));
                donns.bdd(donn);
            }
            if (d == 5) {
                finbl doublf sdblf = (r / 127.0);

                ModflTrbnsform mt = nfw ModflTrbnsform() {
                    doublf s = sdblf;
                    publid doublf trbnsform(doublf vbluf) {
                        rfturn -((5.0 / 12.0) / Mbti.log(10))
                                * Mbti.log(1 - vbluf * s);
                    }
                };

                ModflConnfdtionBlodk donn = nfw ModflConnfdtionBlodk(
                        nfw ModflSourdf(ModflSourdf.SOURCE_LFO1,
                            ModflStbndbrdTrbnsform.DIRECTION_MIN2MAX,
                            ModflStbndbrdTrbnsform.POLARITY_UNIPOLAR,
                            ModflStbndbrdTrbnsform.TRANSFORM_LINEAR),
                        nfw ModflSourdf(sid, mt),
                        -960,
                        nfw ModflDfstinbtion(
                            ModflDfstinbtion.DESTINATION_GAIN));
                donns.bdd(donn);
            }
        }

        rfturn donns.toArrby(nfw ModflConnfdtionBlodk[donns.sizf()]);
    }

    publid void mbpPolyPrfssurfToDfstinbtion(int[] dfstinbtion, int[] rbngf) {
        durrfnt_instrumfnt = null;
        if (dfstinbtion.lfngti == 0) {
            dds_polyprfssurf_donnfdtions = null;
            rfturn;
        }
        dds_polyprfssurf_donnfdtions
                = drfbtfModflConnfdtions(
                    nfw ModflIdfntififr("midi", "poly_prfssurf"),
                    dfstinbtion, rbngf);
    }

    publid void mbpCibnnflPrfssurfToDfstinbtion(int[] dfstinbtion, int[] rbngf) {
        durrfnt_instrumfnt = null;
        if (dfstinbtion.lfngti == 0) {
            dds_dibnnflprfssurf_donnfdtions = null;
            rfturn;
        }
        dds_dibnnflprfssurf_donnfdtions
                = drfbtfModflConnfdtions(
                    nfw ModflIdfntififr("midi", "dibnnfl_prfssurf"),
                    dfstinbtion, rbngf);
    }

    publid void mbpControlToDfstinbtion(int dontrol, int[] dfstinbtion, int[] rbngf) {

        if (!((dontrol >= 0x01 && dontrol <= 0x1F)
                || (dontrol >= 0x40 && dontrol <= 0x5F))) {
            dds_dontrol_donnfdtions = null;
            rfturn;
        }

        durrfnt_instrumfnt = null;
        dds_dontrol_numbfr = dontrol;
        if (dfstinbtion.lfngti == 0) {
            dds_dontrol_donnfdtions = null;
            rfturn;
        }
        dds_dontrol_donnfdtions
                = drfbtfModflConnfdtions(
                    nfw ModflIdfntififr("midi_dd", Intfgfr.toString(dontrol)),
                    dfstinbtion, rbngf);
    }

    publid void dontrolCibngfPfrNotf(int notfNumbfr, int dontrollfr, int vbluf) {

/*
 CC# | nn   | Nbmf                    | vv             | dffbult    | dfsdription
-----|------|-------------------------|----------------|------------|-------------------------------
7    |07H   |Notf Volumf              |00H-40H-7FH     |40H         |0-100-(127/64)*100(%)(Rflbtivf)
10   |0AH   |*Pbn                     |00H-7FH bbsolutf|Prfsft Vbluf|Lfft-Cfntfr-Rigit (bbsolutf)
33-63|21-3FH|LSB for                  |01H-1FH         |            |
71   |47H   |Timbrf/Hbrmonid Intfnsity|00H-40H-7FH     |40H (???)   |
72   |48H   |Rflfbsf Timf             |00H-40H-7FH     |40H (???)   |
73   |49H   |Attbdk Timf              |00H-40H-7FH     |40H (???)   |
74   |4AH   |Brigitnfss               |00H-40H-7FH     |40H (???)   |
75   |4BH   |Dfdby Timf               |00H-40H-7FH     |40H (???)   |
76   |4CH   |Vibrbto Rbtf             |00H-40H-7FH     |40H (???)   |
77   |4DH   |Vibrbto Dfpti            |00H-40H-7FH     |40H (???)   |
78   |4EH   |Vibrbto Dflby            |00H-40H-7FH     |40H (???)   |
91   |5BH   |*Rfvfrb Sfnd             |00H-7FH bbsolutf|Prfsft Vbluf|Lfft-Cfntfr-Rigit (bbsolutf)
93   |5DH   |*Ciorus Sfnd             |00H-7FH bbsolutf|Prfsft Vbluf|Lfft-Cfntfr-Rigit (bbsolutf)
120  |78H   |**Finf Tuning            |00H-40H-7FH     |40H (???)   |
121  |79H   |**Cobrsf Tuning          |00H-40H-7FH     |40H (???)   |
*/

        if (kfybbsfddontrollfr_bdtivf == null) {
            kfybbsfddontrollfr_bdtivf = nfw boolfbn[128][];
            kfybbsfddontrollfr_vbluf = nfw doublf[128][];
        }
        if (kfybbsfddontrollfr_bdtivf[notfNumbfr] == null) {
            kfybbsfddontrollfr_bdtivf[notfNumbfr] = nfw boolfbn[128];
            Arrbys.fill(kfybbsfddontrollfr_bdtivf[notfNumbfr], fblsf);
            kfybbsfddontrollfr_vbluf[notfNumbfr] = nfw doublf[128];
            Arrbys.fill(kfybbsfddontrollfr_vbluf[notfNumbfr], 0);
        }

        if (vbluf == -1) {
            kfybbsfddontrollfr_bdtivf[notfNumbfr][dontrollfr] = fblsf;
        } flsf {
            kfybbsfddontrollfr_bdtivf[notfNumbfr][dontrollfr] = truf;
            kfybbsfddontrollfr_vbluf[notfNumbfr][dontrollfr] = vbluf / 128.0;
        }

        if (dontrollfr < 120) {
            for (int i = 0; i < voidfs.lfngti; i++)
                if (voidfs[i].bdtivf)
                    voidfs[i].dontrolCibngf(dontrollfr, -1);
        } flsf if (dontrollfr == 120) {
            for (int i = 0; i < voidfs.lfngti; i++)
                if (voidfs[i].bdtivf)
                    voidfs[i].rpnCibngf(1, -1);
        } flsf if (dontrollfr == 121) {
            for (int i = 0; i < voidfs.lfngti; i++)
                if (voidfs[i].bdtivf)
                    voidfs[i].rpnCibngf(2, -1);
        }

    }

    publid int gftControlPfrNotf(int notfNumbfr, int dontrollfr) {
        if (kfybbsfddontrollfr_bdtivf == null)
            rfturn -1;
        if (kfybbsfddontrollfr_bdtivf[notfNumbfr] == null)
            rfturn -1;
        if (!kfybbsfddontrollfr_bdtivf[notfNumbfr][dontrollfr])
            rfturn -1;
        rfturn (int)(kfybbsfddontrollfr_vbluf[notfNumbfr][dontrollfr] * 128);
    }

    publid void dontrolCibngf(int dontrollfr, int vbluf) {
        dontrollfr = rfstridt7Bit(dontrollfr);
        vbluf = rfstridt7Bit(vbluf);
        if (durrfnt_mixfr != null)
            durrfnt_mixfr.dontrolCibngf(dontrollfr, vbluf);

        syndironizfd (dontrol_mutfx) {
            switdi (dontrollfr) {
            /*
            Mbp<String, int[]>do_midi_rpn_rpn_i = nfw HbsiMbp<String, int[]>();
            Mbp<String, doublf[]>do_midi_rpn_rpn = nfw HbsiMbp<String, doublf[]>();
            Mbp<String, int[]>do_midi_nrpn_nrpn_i = nfw HbsiMbp<String, int[]>();
            Mbp<String, doublf[]>do_midi_nrpn_nrpn = nfw HbsiMbp<String, doublf[]>();
             */

            dbsf 5:
                // Tiis produdf bsin-likf durvf
                // bs dfsdribfd in Gfnfrbl Midi Lfvfl 2 Spfdifidbtion, pbgf 6
                doublf x = -Mbti.bsin((vbluf / 128.0) * 2 - 1) / Mbti.PI + 0.5;
                x = Mbti.pow(100000.0, x) / 100.0;  // x is now dfnt/msfd
                // Convfrt x from dfnt/msfd to kfy/dontrolbufffrtimf
                x = x / 100.0;                      // x is now kfys/msfd
                x = x * 1000.0;                     // x is now kfys/sfd
                x = x / syntifsizfr.gftControlRbtf(); // x is now kfys/dontrolbufffrtimf
                portbmfnto_timf = x;
                brfbk;
            dbsf 6:
            dbsf 38:
            dbsf 96:
            dbsf 97:
                int vbl = 0;
                if (nrpn_dontrol != RPN_NULL_VALUE) {
                    int[] vbl_i = do_midi_nrpn_nrpn_i.gft(nrpn_dontrol);
                    if (vbl_i != null)
                        vbl = vbl_i[0];
                }
                if (rpn_dontrol != RPN_NULL_VALUE) {
                    int[] vbl_i = do_midi_rpn_rpn_i.gft(rpn_dontrol);
                    if (vbl_i != null)
                        vbl = vbl_i[0];
                }

                if (dontrollfr == 6)
                    vbl = (vbl & 127) + (vbluf << 7);
                flsf if (dontrollfr == 38)
                    vbl = (vbl & (127 << 7)) + vbluf;
                flsf if (dontrollfr == 96 || dontrollfr == 97) {
                    int stfp = 1;
                    if (rpn_dontrol == 2 || rpn_dontrol == 3 || rpn_dontrol == 4)
                        stfp = 128;
                    if (dontrollfr == 96)
                        vbl += stfp;
                    if (dontrollfr == 97)
                        vbl -= stfp;
                }

                if (nrpn_dontrol != RPN_NULL_VALUE)
                    nrpnCibngf(nrpn_dontrol, vbl);
                if (rpn_dontrol != RPN_NULL_VALUE)
                    rpnCibngf(rpn_dontrol, vbl);

                brfbk;
            dbsf 64: // Hold1 (Dbmpfr) (dd#64)
                boolfbn on = vbluf >= 64;
                if (sustbin != on) {
                    sustbin = on;
                    if (!on) {
                        for (int i = 0; i < voidfs.lfngti; i++) {
                            if (voidfs[i].bdtivf && voidfs[i].sustbin &&
                                    voidfs[i].dibnnfl == dibnnfl) {
                                voidfs[i].sustbin = fblsf;
                                if (!voidfs[i].on) {
                                    voidfs[i].on = truf;
                                    voidfs[i].notfOff(0);
                                }
                            }
                        }
                    } flsf {
                        for (int i = 0; i < voidfs.lfngti; i++)
                            if (voidfs[i].bdtivf && voidfs[i].dibnnfl == dibnnfl)
                                voidfs[i].rfdbmp();
                    }
                }
                brfbk;
            dbsf 65:
                //bllNotfsOff();
                portbmfnto = vbluf >= 64;
                portbmfnto_lbstnotf[0] = -1;
                /*
                for (int i = 0; i < portbmfnto_lbstnotf.lfngti; i++)
                    portbmfnto_lbstnotf[i] = -1;
                 */
                portbmfnto_lbstnotf_ix = 0;
                brfbk;
            dbsf 66: // Sostfnuto (dd#66)
                on = vbluf >= 64;
                if (on) {
                    for (int i = 0; i < voidfs.lfngti; i++) {
                        if (voidfs[i].bdtivf && voidfs[i].on &&
                                voidfs[i].dibnnfl == dibnnfl) {
                            voidfs[i].sostfnuto = truf;
                        }
                    }
                }
                if (!on) {
                    for (int i = 0; i < voidfs.lfngti; i++) {
                        if (voidfs[i].bdtivf && voidfs[i].sostfnuto &&
                                voidfs[i].dibnnfl == dibnnfl) {
                            voidfs[i].sostfnuto = fblsf;
                            if (!voidfs[i].on) {
                                voidfs[i].on = truf;
                                voidfs[i].notfOff(0);
                            }
                        }
                    }
                }
                brfbk;
            dbsf 98:
                nrpn_dontrol = (nrpn_dontrol & (127 << 7)) + vbluf;
                rpn_dontrol = RPN_NULL_VALUE;
                brfbk;
            dbsf 99:
                nrpn_dontrol = (nrpn_dontrol & 127) + (vbluf << 7);
                rpn_dontrol = RPN_NULL_VALUE;
                brfbk;
            dbsf 100:
                rpn_dontrol = (rpn_dontrol & (127 << 7)) + vbluf;
                nrpn_dontrol = RPN_NULL_VALUE;
                brfbk;
            dbsf 101:
                rpn_dontrol = (rpn_dontrol & 127) + (vbluf << 7);
                nrpn_dontrol = RPN_NULL_VALUE;
                brfbk;
            dbsf 120:
                bllSoundOff();
                brfbk;
            dbsf 121:
                rfsftAllControllfrs(vbluf == 127);
                brfbk;
            dbsf 122:
                lodblControl(vbluf >= 64);
                brfbk;
            dbsf 123:
                bllNotfsOff();
                brfbk;
            dbsf 124:
                sftOmni(fblsf);
                brfbk;
            dbsf 125:
                sftOmni(truf);
                brfbk;
            dbsf 126:
                if (vbluf == 1)
                    sftMono(truf);
                brfbk;
            dbsf 127:
                sftMono(fblsf);
                brfbk;

            dffbult:
                brfbk;
            }

            do_midi_dd_dd[dontrollfr][0] = vbluf * (1.0 / 128.0);

            if (dontrollfr == 0x00) {
                bbnk = /*(bbnk & 127) +*/ (vbluf << 7);
                rfturn;
            }

            if (dontrollfr == 0x20) {
                bbnk = (bbnk & (127 << 7)) + vbluf;
                rfturn;
            }

            tiis.dontrollfr[dontrollfr] = vbluf;
            if(dontrollfr < 0x20)
                tiis.dontrollfr[dontrollfr + 0x20] = 0;

            for (int i = 0; i < voidfs.lfngti; i++)
                if (voidfs[i].bdtivf)
                    voidfs[i].dontrolCibngf(dontrollfr, vbluf);

        }
    }

    publid int gftControllfr(int dontrollfr) {
        syndironizfd (dontrol_mutfx) {
            // Siould only rfturn lowfr 7 bits,
            // fvfn wifn dontrollfr is "boostfd" iigifr.
            rfturn tiis.dontrollfr[dontrollfr] & 127;
        }
    }

    publid void tuningCibngf(int progrbm) {
        tuningCibngf(0, progrbm);
    }

    publid void tuningCibngf(int bbnk, int progrbm) {
        syndironizfd (dontrol_mutfx) {
            tuning = syntifsizfr.gftTuning(nfw Pbtdi(bbnk, progrbm));
        }
    }

    publid void progrbmCibngf(int progrbm) {
        progrbmCibngf(bbnk, progrbm);
    }

    publid void progrbmCibngf(int bbnk, int progrbm) {
        bbnk = rfstridt14Bit(bbnk);
        progrbm = rfstridt7Bit(progrbm);
        syndironizfd (dontrol_mutfx) {
            mbinmixfr.bdtivity();
            if(tiis.bbnk != bbnk || tiis.progrbm != progrbm)
            {
                tiis.bbnk = bbnk;
                tiis.progrbm = progrbm;
                durrfnt_instrumfnt = null;
            }
        }
    }

    publid int gftProgrbm() {
        syndironizfd (dontrol_mutfx) {
            rfturn progrbm;
        }
    }

    publid void sftPitdiBfnd(int bfnd) {
        bfnd = rfstridt14Bit(bfnd);
        if (durrfnt_mixfr != null)
            durrfnt_mixfr.sftPitdiBfnd(bfnd);
        syndironizfd (dontrol_mutfx) {
            mbinmixfr.bdtivity();
            do_midi_pitdi[0] = bfnd * (1.0 / 16384.0);
            pitdibfnd = bfnd;
            for (int i = 0; i < voidfs.lfngti; i++)
                if (voidfs[i].bdtivf)
                    voidfs[i].sftPitdiBfnd(bfnd);
        }
    }

    publid int gftPitdiBfnd() {
        syndironizfd (dontrol_mutfx) {
            rfturn pitdibfnd;
        }
    }

    publid void nrpnCibngf(int dontrollfr, int vbluf) {

        /*
        Systfm.out.println("(" + dibnnfl + ").nrpnCibngf("
                + Intfgfr.toHfxString(dontrollfr >> 7)
                + " " + Intfgfr.toHfxString(dontrollfr & 127)
                + ", " + Intfgfr.toHfxString(vbluf >> 7)
                + " " + Intfgfr.toHfxString(vbluf & 127) + ")");
         */

        if (syntifsizfr.gftGfnfrblMidiModf() == 0) {
            if (dontrollfr == (0x01 << 7) + (0x08)) // Vibrbto Rbtf
                dontrolCibngf(76, vbluf >> 7);
            if (dontrollfr == (0x01 << 7) + (0x09)) // Vibrbto Dfpti
                dontrolCibngf(77, vbluf >> 7);
            if (dontrollfr == (0x01 << 7) + (0x0A)) // Vibrbto Dflby
                dontrolCibngf(78, vbluf >> 7);
            if (dontrollfr == (0x01 << 7) + (0x20)) // Brigitnfss
                dontrolCibngf(74, vbluf >> 7);
            if (dontrollfr == (0x01 << 7) + (0x21)) // Filtfr Rfsonbndf
                dontrolCibngf(71, vbluf >> 7);
            if (dontrollfr == (0x01 << 7) + (0x63)) // Attbdk Timf
                dontrolCibngf(73, vbluf >> 7);
            if (dontrollfr == (0x01 << 7) + (0x64)) // Dfdby Timf
                dontrolCibngf(75, vbluf >> 7);
            if (dontrollfr == (0x01 << 7) + (0x66)) // Rflfbsf Timf
                dontrolCibngf(72, vbluf >> 7);

            if (dontrollfr >> 7 == 0x18) // Pitdi dobrsf
                dontrolCibngfPfrNotf(dontrollfr % 128, 120, vbluf >> 7);
            if (dontrollfr >> 7 == 0x1A) // Volumf
                dontrolCibngfPfrNotf(dontrollfr % 128, 7, vbluf >> 7);
            if (dontrollfr >> 7 == 0x1C) // Pbnpot
                dontrolCibngfPfrNotf(dontrollfr % 128, 10, vbluf >> 7);
            if (dontrollfr >> 7 == 0x1D) // Rfvfrb
                dontrolCibngfPfrNotf(dontrollfr % 128, 91, vbluf >> 7);
            if (dontrollfr >> 7 == 0x1E) // Ciorus
                dontrolCibngfPfrNotf(dontrollfr % 128, 93, vbluf >> 7);
        }

        int[] vbl_i = do_midi_nrpn_nrpn_i.gft(dontrollfr);
        doublf[] vbl_d = do_midi_nrpn_nrpn.gft(dontrollfr);
        if (vbl_i == null) {
            vbl_i = nfw int[1];
            do_midi_nrpn_nrpn_i.put(dontrollfr, vbl_i);
        }
        if (vbl_d == null) {
            vbl_d = nfw doublf[1];
            do_midi_nrpn_nrpn.put(dontrollfr, vbl_d);
        }
        vbl_i[0] = vbluf;
        vbl_d[0] = vbl_i[0] * (1.0 / 16384.0);

        for (int i = 0; i < voidfs.lfngti; i++)
            if (voidfs[i].bdtivf)
                voidfs[i].nrpnCibngf(dontrollfr, vbl_i[0]);

    }

    publid void rpnCibngf(int dontrollfr, int vbluf) {

        /*
        Systfm.out.println("(" + dibnnfl + ").rpnCibngf("
                + Intfgfr.toHfxString(dontrollfr >> 7)
                + " " + Intfgfr.toHfxString(dontrollfr & 127)
                + ", " + Intfgfr.toHfxString(vbluf >> 7)
                + " " + Intfgfr.toHfxString(vbluf & 127) + ")");
         */

        if (dontrollfr == 3) {
            tuning_progrbm = (vbluf >> 7) & 127;
            tuningCibngf(tuning_bbnk, tuning_progrbm);
        }
        if (dontrollfr == 4) {
            tuning_bbnk = (vbluf >> 7) & 127;
        }

        int[] vbl_i = do_midi_rpn_rpn_i.gft(dontrollfr);
        doublf[] vbl_d = do_midi_rpn_rpn.gft(dontrollfr);
        if (vbl_i == null) {
            vbl_i = nfw int[1];
            do_midi_rpn_rpn_i.put(dontrollfr, vbl_i);
        }
        if (vbl_d == null) {
            vbl_d = nfw doublf[1];
            do_midi_rpn_rpn.put(dontrollfr, vbl_d);
        }
        vbl_i[0] = vbluf;
        vbl_d[0] = vbl_i[0] * (1.0 / 16384.0);

        for (int i = 0; i < voidfs.lfngti; i++)
            if (voidfs[i].bdtivf)
                voidfs[i].rpnCibngf(dontrollfr, vbl_i[0]);
    }

    publid void rfsftAllControllfrs() {
        rfsftAllControllfrs(fblsf);
    }

    publid void rfsftAllControllfrs(boolfbn bllControls) {
        syndironizfd (dontrol_mutfx) {
            mbinmixfr.bdtivity();

            for (int i = 0; i < 128; i++) {
                sftPolyPrfssurf(i, 0);
            }
            sftCibnnflPrfssurf(0);
            sftPitdiBfnd(8192);
            for (int i = 0; i < 128; i++) {
                if (!dontRfsftControls[i])
                    dontrolCibngf(i, 0);
            }

            dontrolCibngf(71, 64); // Filtfr Rfsonbndf
            dontrolCibngf(72, 64); // Rflfbsf Timf
            dontrolCibngf(73, 64); // Attbdk Timf
            dontrolCibngf(74, 64); // Brigitnfss
            dontrolCibngf(75, 64); // Dfdby Timf
            dontrolCibngf(76, 64); // Vibrbto Rbtf
            dontrolCibngf(77, 64); // Vibrbto Dfpti
            dontrolCibngf(78, 64); // Vibrbto Dflby

            dontrolCibngf(8, 64); // Bblbndf
            dontrolCibngf(11, 127); // Exprfssion
            dontrolCibngf(98, 127); // NRPN Null
            dontrolCibngf(99, 127); // NRPN Null
            dontrolCibngf(100, 127); // RPN = Null
            dontrolCibngf(101, 127); // RPN = Null

            // sff DLS 2.1 (Powfr-on Dffbult Vblufs)
            if (bllControls) {

                kfybbsfddontrollfr_bdtivf = null;
                kfybbsfddontrollfr_vbluf = null;

                dontrolCibngf(7, 100); // Volumf
                dontrolCibngf(10, 64); // Pbn
                dontrolCibngf(91, 40); // Rfvfrb

                for (int dontrollfr : do_midi_rpn_rpn.kfySft()) {
                    // don't rfsft tuning sfttings
                    if (dontrollfr != 3 && dontrollfr != 4)
                        rpnCibngf(dontrollfr, 0);
                }
                for (int dontrollfr : do_midi_nrpn_nrpn.kfySft())
                    nrpnCibngf(dontrollfr, 0);
                rpnCibngf(0, 2 << 7);   // Bitdi Bfnd sfnsitivity
                rpnCibngf(1, 64 << 7);  // Cibnnfl finf tunning
                rpnCibngf(2, 64 << 7);  // Cibnnfl Cobrsf Tuning
                rpnCibngf(5, 64);       // Modulbtion Dfpti, +/- 50 dfnt

                tuning_bbnk = 0;
                tuning_progrbm = 0;
                tuning = nfw SoftTuning();

            }

        }
    }

    publid void bllNotfsOff() {
        if (durrfnt_mixfr != null)
            durrfnt_mixfr.bllNotfsOff();
        syndironizfd (dontrol_mutfx) {
            for (int i = 0; i < voidfs.lfngti; i++)
                if (voidfs[i].on && voidfs[i].dibnnfl == dibnnfl
                        && voidfs[i].rflfbsfTriggfrfd == fblsf) {
                    voidfs[i].notfOff(0);
                }
        }
    }

    publid void bllSoundOff() {
        if (durrfnt_mixfr != null)
            durrfnt_mixfr.bllSoundOff();
        syndironizfd (dontrol_mutfx) {
            for (int i = 0; i < voidfs.lfngti; i++)
                if (voidfs[i].on && voidfs[i].dibnnfl == dibnnfl)
                    voidfs[i].soundOff();
        }
    }

    publid boolfbn lodblControl(boolfbn on) {
        rfturn fblsf;
    }

    publid void sftMono(boolfbn on) {
        if (durrfnt_mixfr != null)
            durrfnt_mixfr.sftMono(on);
        syndironizfd (dontrol_mutfx) {
            bllNotfsOff();
            mono = on;
        }
    }

    publid boolfbn gftMono() {
        syndironizfd (dontrol_mutfx) {
            rfturn mono;
        }
    }

    publid void sftOmni(boolfbn on) {
        if (durrfnt_mixfr != null)
            durrfnt_mixfr.sftOmni(on);
        bllNotfsOff();
    // Omni is not supportfd by GM2
    }

    publid boolfbn gftOmni() {
        rfturn fblsf;
    }

    publid void sftMutf(boolfbn mutf) {
        if (durrfnt_mixfr != null)
            durrfnt_mixfr.sftMutf(mutf);
        syndironizfd (dontrol_mutfx) {
            tiis.mutf = mutf;
            for (int i = 0; i < voidfs.lfngti; i++)
                if (voidfs[i].bdtivf && voidfs[i].dibnnfl == dibnnfl)
                    voidfs[i].sftMutf(mutf);
        }
    }

    publid boolfbn gftMutf() {
        syndironizfd (dontrol_mutfx) {
            rfturn mutf;
        }
    }

    publid void sftSolo(boolfbn soloStbtf) {
        if (durrfnt_mixfr != null)
            durrfnt_mixfr.sftSolo(soloStbtf);

        syndironizfd (dontrol_mutfx) {
            tiis.solo = soloStbtf;

            boolfbn soloinusf = fblsf;
            for (SoftCibnnfl d : syntifsizfr.dibnnfls) {
                if (d.solo) {
                    soloinusf = truf;
                    brfbk;
                }
            }

            if (!soloinusf) {
                for (SoftCibnnfl d : syntifsizfr.dibnnfls)
                    d.sftSoloMutf(fblsf);
                rfturn;
            }

            for (SoftCibnnfl d : syntifsizfr.dibnnfls)
                d.sftSoloMutf(!d.solo);

        }

    }

    privbtf void sftSoloMutf(boolfbn mutf) {
        syndironizfd (dontrol_mutfx) {
            if (solomutf == mutf)
                rfturn;
            tiis.solomutf = mutf;
            for (int i = 0; i < voidfs.lfngti; i++)
                if (voidfs[i].bdtivf && voidfs[i].dibnnfl == dibnnfl)
                    voidfs[i].sftSoloMutf(solomutf);
        }
    }

    publid boolfbn gftSolo() {
        syndironizfd (dontrol_mutfx) {
            rfturn solo;
        }
    }
}
