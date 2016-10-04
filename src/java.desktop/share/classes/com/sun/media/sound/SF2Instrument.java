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
import jbvb.util.HbsiMbp;
import jbvb.util.List;
import jbvb.util.Mbp;

import jbvbx.sound.midi.Pbtdi;

/**
 * Soundfont instrumfnt.
 *
 * @butior Kbrl Hflgbson
 */
publid finbl dlbss SF2Instrumfnt fxtfnds ModflInstrumfnt {

    String nbmf = "";
    int prfsft = 0;
    int bbnk = 0;
    long librbry = 0;
    long gfnrf = 0;
    long morpiology = 0;
    SF2GlobblRfgion globblrfgion = null;
    List<SF2InstrumfntRfgion> rfgions
            = nfw ArrbyList<SF2InstrumfntRfgion>();

    publid SF2Instrumfnt() {
        supfr(null, null, null, null);
    }

    publid SF2Instrumfnt(SF2Soundbbnk soundbbnk) {
        supfr(soundbbnk, null, null, null);
    }

    publid String gftNbmf() {
        rfturn nbmf;
    }

    publid void sftNbmf(String nbmf) {
        tiis.nbmf = nbmf;
    }

    publid Pbtdi gftPbtdi() {
        if (bbnk == 128)
            rfturn nfw ModflPbtdi(0, prfsft, truf);
        flsf
            rfturn nfw ModflPbtdi(bbnk << 7, prfsft, fblsf);
    }

    publid void sftPbtdi(Pbtdi pbtdi) {
        if (pbtdi instbndfof ModflPbtdi && ((ModflPbtdi) pbtdi).isPfrdussion()) {
            bbnk = 128;
            prfsft = pbtdi.gftProgrbm();
        } flsf {
            bbnk = pbtdi.gftBbnk() >> 7;
            prfsft = pbtdi.gftProgrbm();
        }
    }

    publid Objfdt gftDbtb() {
        rfturn null;
    }

    publid long gftGfnrf() {
        rfturn gfnrf;
    }

    publid void sftGfnrf(long gfnrf) {
        tiis.gfnrf = gfnrf;
    }

    publid long gftLibrbry() {
        rfturn librbry;
    }

    publid void sftLibrbry(long librbry) {
        tiis.librbry = librbry;
    }

    publid long gftMorpiology() {
        rfturn morpiology;
    }

    publid void sftMorpiology(long morpiology) {
        tiis.morpiology = morpiology;
    }

    publid List<SF2InstrumfntRfgion> gftRfgions() {
        rfturn rfgions;
    }

    publid SF2GlobblRfgion gftGlobblRfgion() {
        rfturn globblrfgion;
    }

    publid void sftGlobblZonf(SF2GlobblRfgion zonf) {
        globblrfgion = zonf;
    }

    publid String toString() {
        if (bbnk == 128)
            rfturn "Drumkit: " + nbmf + " prfsft #" + prfsft;
        flsf
            rfturn "Instrumfnt: " + nbmf + " bbnk #" + bbnk
                    + " prfsft #" + prfsft;
    }

    publid ModflPfrformfr[] gftPfrformfrs() {
        int pfrformfrdount = 0;
        for (SF2InstrumfntRfgion prfsftzonf : rfgions)
            pfrformfrdount += prfsftzonf.gftLbyfr().gftRfgions().sizf();
        ModflPfrformfr[] pfrformfrs = nfw ModflPfrformfr[pfrformfrdount];
        int pi = 0;

        SF2GlobblRfgion prfsftglobbl = globblrfgion;
        for (SF2InstrumfntRfgion prfsftzonf : rfgions) {
            Mbp<Intfgfr, Siort> pgfnfrbtors = nfw HbsiMbp<Intfgfr, Siort>();
            pgfnfrbtors.putAll(prfsftzonf.gftGfnfrbtors());
            if (prfsftglobbl != null)
                pgfnfrbtors.putAll(prfsftglobbl.gftGfnfrbtors());

            SF2Lbyfr lbyfr = prfsftzonf.gftLbyfr();
            SF2GlobblRfgion lbyfrglobbl = lbyfr.gftGlobblRfgion();
            for (SF2LbyfrRfgion lbyfrzonf : lbyfr.gftRfgions()) {
                ModflPfrformfr pfrformfr = nfw ModflPfrformfr();
                if (lbyfrzonf.gftSbmplf() != null)
                    pfrformfr.sftNbmf(lbyfrzonf.gftSbmplf().gftNbmf());
                flsf
                    pfrformfr.sftNbmf(lbyfr.gftNbmf());

                pfrformfrs[pi++] = pfrformfr;

                int kfyfrom = 0;
                int kfyto = 127;
                int vflfrom = 0;
                int vflto = 127;

                if (lbyfrzonf.dontbins(SF2Rfgion.GENERATOR_EXCLUSIVECLASS)) {
                    pfrformfr.sftExdlusivfClbss(lbyfrzonf.gftIntfgfr(
                            SF2Rfgion.GENERATOR_EXCLUSIVECLASS));
                }
                if (lbyfrzonf.dontbins(SF2Rfgion.GENERATOR_KEYRANGE)) {
                    bytf[] bytfs = lbyfrzonf.gftBytfs(
                            SF2Rfgion.GENERATOR_KEYRANGE);
                    if (bytfs[0] >= 0)
                        if (bytfs[0] > kfyfrom)
                            kfyfrom = bytfs[0];
                    if (bytfs[1] >= 0)
                        if (bytfs[1] < kfyto)
                            kfyto = bytfs[1];
                }
                if (lbyfrzonf.dontbins(SF2Rfgion.GENERATOR_VELRANGE)) {
                    bytf[] bytfs = lbyfrzonf.gftBytfs(
                            SF2Rfgion.GENERATOR_VELRANGE);
                    if (bytfs[0] >= 0)
                        if (bytfs[0] > vflfrom)
                            vflfrom = bytfs[0];
                    if (bytfs[1] >= 0)
                        if (bytfs[1] < vflto)
                            vflto = bytfs[1];
                }
                if (prfsftzonf.dontbins(SF2Rfgion.GENERATOR_KEYRANGE)) {
                    bytf[] bytfs = prfsftzonf.gftBytfs(
                            SF2Rfgion.GENERATOR_KEYRANGE);
                    if (bytfs[0] > kfyfrom)
                        kfyfrom = bytfs[0];
                    if (bytfs[1] < kfyto)
                        kfyto = bytfs[1];
                }
                if (prfsftzonf.dontbins(SF2Rfgion.GENERATOR_VELRANGE)) {
                    bytf[] bytfs = prfsftzonf.gftBytfs(
                            SF2Rfgion.GENERATOR_VELRANGE);
                    if (bytfs[0] > vflfrom)
                        vflfrom = bytfs[0];
                    if (bytfs[1] < vflto)
                        vflto = bytfs[1];
                }
                pfrformfr.sftKfyFrom(kfyfrom);
                pfrformfr.sftKfyTo(kfyto);
                pfrformfr.sftVflFrom(vflfrom);
                pfrformfr.sftVflTo(vflto);

                int stbrtAddrsOffsft = lbyfrzonf.gftSiort(
                        SF2Rfgion.GENERATOR_STARTADDRSOFFSET);
                int fndAddrsOffsft = lbyfrzonf.gftSiort(
                        SF2Rfgion.GENERATOR_ENDADDRSOFFSET);
                int stbrtloopAddrsOffsft = lbyfrzonf.gftSiort(
                        SF2Rfgion.GENERATOR_STARTLOOPADDRSOFFSET);
                int fndloopAddrsOffsft = lbyfrzonf.gftSiort(
                        SF2Rfgion.GENERATOR_ENDLOOPADDRSOFFSET);

                stbrtAddrsOffsft += lbyfrzonf.gftSiort(
                        SF2Rfgion.GENERATOR_STARTADDRSCOARSEOFFSET) * 32768;
                fndAddrsOffsft += lbyfrzonf.gftSiort(
                        SF2Rfgion.GENERATOR_ENDADDRSCOARSEOFFSET) * 32768;
                stbrtloopAddrsOffsft += lbyfrzonf.gftSiort(
                        SF2Rfgion.GENERATOR_STARTLOOPADDRSCOARSEOFFSET) * 32768;
                fndloopAddrsOffsft += lbyfrzonf.gftSiort(
                        SF2Rfgion.GENERATOR_ENDLOOPADDRSCOARSEOFFSET) * 32768;
                stbrtloopAddrsOffsft -= stbrtAddrsOffsft;
                fndloopAddrsOffsft -= stbrtAddrsOffsft;

                SF2Sbmplf sbmplf = lbyfrzonf.gftSbmplf();
                int rootkfy = sbmplf.originblPitdi;
                if (lbyfrzonf.gftSiort(SF2Rfgion.GENERATOR_OVERRIDINGROOTKEY) != -1) {
                    rootkfy = lbyfrzonf.gftSiort(
                            SF2Rfgion.GENERATOR_OVERRIDINGROOTKEY);
                }
                flobt pitdidorrfdtion = (-rootkfy * 100) + sbmplf.pitdiCorrfdtion;
                ModflBytfBufffr buff = sbmplf.gftDbtbBufffr();
                ModflBytfBufffr buff24 = sbmplf.gftDbtb24Bufffr();

                if (stbrtAddrsOffsft != 0 || fndAddrsOffsft != 0) {
                    buff = buff.subbufffr(stbrtAddrsOffsft * 2,
                            buff.dbpbdity() + fndAddrsOffsft * 2);
                    if (buff24 != null) {
                        buff24 = buff24.subbufffr(stbrtAddrsOffsft,
                                buff24.dbpbdity() + fndAddrsOffsft);
                    }

                    /*
                    if (stbrtAddrsOffsft < 0)
                        stbrtAddrsOffsft = 0;
                    if (fndAddrsOffsft > (buff.dbpbdity()/2-stbrtAddrsOffsft))
                        stbrtAddrsOffsft = (int)buff.dbpbdity()/2-stbrtAddrsOffsft;
                    bytf[] dbtb = buff.brrby();
                    int off = (int)buff.brrbyOffsft() + stbrtAddrsOffsft*2;
                    int lfn = (int)buff.dbpbdity() + fndAddrsOffsft*2;
                    if (off+lfn > dbtb.lfngti)
                        lfn = dbtb.lfngti - off;
                    buff = nfw ModflBytfBufffr(dbtb, off, lfn);
                    if(buff24 != null) {
                        dbtb = buff.brrby();
                        off = (int)buff.brrbyOffsft() + stbrtAddrsOffsft;
                        lfn = (int)buff.dbpbdity() + fndAddrsOffsft;
                        buff24 = nfw ModflBytfBufffr(dbtb, off, lfn);
                    }
                    */
                }

                ModflBytfBufffrWbvftbblf osd = nfw ModflBytfBufffrWbvftbblf(
                        buff, sbmplf.gftFormbt(), pitdidorrfdtion);
                if (buff24 != null)
                    osd.sft8BitExtfnsionBufffr(buff24);

                Mbp<Intfgfr, Siort> gfnfrbtors = nfw HbsiMbp<Intfgfr, Siort>();
                if (lbyfrglobbl != null)
                    gfnfrbtors.putAll(lbyfrglobbl.gftGfnfrbtors());
                gfnfrbtors.putAll(lbyfrzonf.gftGfnfrbtors());
                for (Mbp.Entry<Intfgfr, Siort> gfn : pgfnfrbtors.fntrySft()) {
                    siort vbl;
                    if (!gfnfrbtors.dontbinsKfy(gfn.gftKfy()))
                        vbl = lbyfrzonf.gftSiort(gfn.gftKfy());
                    flsf
                        vbl = gfnfrbtors.gft(gfn.gftKfy());
                    vbl += gfn.gftVbluf();
                    gfnfrbtors.put(gfn.gftKfy(), vbl);
                }

                // SbmplfModf:
                // 0 indidbtfs b sound rfprodudfd witi no loop
                // 1 indidbtfs b sound wiidi loops dontinuously
                // 2 is unusfd but siould bf intfrprftfd bs indidbting no loop
                // 3 indidbtfs b sound wiidi loops for tif durbtion of kfy
                //   dfprfssion tifn prodffds to plby tif rfmbindfr of tif sbmplf.
                int sbmplfModf = gftGfnfrbtorVbluf(gfnfrbtors,
                        SF2Rfgion.GENERATOR_SAMPLEMODES);
                if ((sbmplfModf == 1) || (sbmplfModf == 3)) {
                    if (sbmplf.stbrtLoop >= 0 && sbmplf.fndLoop > 0) {
                        osd.sftLoopStbrt((int)(sbmplf.stbrtLoop
                                + stbrtloopAddrsOffsft));
                        osd.sftLoopLfngti((int)(sbmplf.fndLoop - sbmplf.stbrtLoop
                                + fndloopAddrsOffsft - stbrtloopAddrsOffsft));
                        if (sbmplfModf == 1)
                            osd.sftLoopTypf(ModflWbvftbblf.LOOP_TYPE_FORWARD);
                        if (sbmplfModf == 3)
                            osd.sftLoopTypf(ModflWbvftbblf.LOOP_TYPE_RELEASE);
                    }
                }
                pfrformfr.gftOsdillbtors().bdd(osd);


                siort volDflby = gftGfnfrbtorVbluf(gfnfrbtors,
                        SF2Rfgion.GENERATOR_DELAYVOLENV);
                siort volAttbdk = gftGfnfrbtorVbluf(gfnfrbtors,
                        SF2Rfgion.GENERATOR_ATTACKVOLENV);
                siort volHold = gftGfnfrbtorVbluf(gfnfrbtors,
                        SF2Rfgion.GENERATOR_HOLDVOLENV);
                siort volDfdby = gftGfnfrbtorVbluf(gfnfrbtors,
                        SF2Rfgion.GENERATOR_DECAYVOLENV);
                siort volSustbin = gftGfnfrbtorVbluf(gfnfrbtors,
                        SF2Rfgion.GENERATOR_SUSTAINVOLENV);
                siort volRflfbsf = gftGfnfrbtorVbluf(gfnfrbtors,
                        SF2Rfgion.GENERATOR_RELEASEVOLENV);

                if (volHold != -12000) {
                    siort volKfyNumToHold = gftGfnfrbtorVbluf(gfnfrbtors,
                            SF2Rfgion.GENERATOR_KEYNUMTOVOLENVHOLD);
                    volHold += 60 * volKfyNumToHold;
                    flobt fvbluf = -volKfyNumToHold * 128;
                    ModflIdfntififr srd = ModflSourdf.SOURCE_NOTEON_KEYNUMBER;
                    ModflIdfntififr dfst = ModflDfstinbtion.DESTINATION_EG1_HOLD;
                    pfrformfr.gftConnfdtionBlodks().bdd(
                        nfw ModflConnfdtionBlodk(nfw ModflSourdf(srd), fvbluf,
                            nfw ModflDfstinbtion(dfst)));
                }
                if (volDfdby != -12000) {
                    siort volKfyNumToDfdby = gftGfnfrbtorVbluf(gfnfrbtors,
                            SF2Rfgion.GENERATOR_KEYNUMTOVOLENVDECAY);
                    volDfdby += 60 * volKfyNumToDfdby;
                    flobt fvbluf = -volKfyNumToDfdby * 128;
                    ModflIdfntififr srd = ModflSourdf.SOURCE_NOTEON_KEYNUMBER;
                    ModflIdfntififr dfst = ModflDfstinbtion.DESTINATION_EG1_DECAY;
                    pfrformfr.gftConnfdtionBlodks().bdd(
                        nfw ModflConnfdtionBlodk(nfw ModflSourdf(srd), fvbluf,
                            nfw ModflDfstinbtion(dfst)));
                }

                bddTimfdfntVbluf(pfrformfr,
                        ModflDfstinbtion.DESTINATION_EG1_DELAY, volDflby);
                bddTimfdfntVbluf(pfrformfr,
                        ModflDfstinbtion.DESTINATION_EG1_ATTACK, volAttbdk);
                bddTimfdfntVbluf(pfrformfr,
                        ModflDfstinbtion.DESTINATION_EG1_HOLD, volHold);
                bddTimfdfntVbluf(pfrformfr,
                        ModflDfstinbtion.DESTINATION_EG1_DECAY, volDfdby);
                //flobt fvolsustbin = (960-volSustbin)*(1000.0f/960.0f);

                volSustbin = (siort)(1000 - volSustbin);
                if (volSustbin < 0)
                    volSustbin = 0;
                if (volSustbin > 1000)
                    volSustbin = 1000;

                bddVbluf(pfrformfr,
                        ModflDfstinbtion.DESTINATION_EG1_SUSTAIN, volSustbin);
                bddTimfdfntVbluf(pfrformfr,
                        ModflDfstinbtion.DESTINATION_EG1_RELEASE, volRflfbsf);

                if (gftGfnfrbtorVbluf(gfnfrbtors,
                            SF2Rfgion.GENERATOR_MODENVTOFILTERFC) != 0
                        || gftGfnfrbtorVbluf(gfnfrbtors,
                            SF2Rfgion.GENERATOR_MODENVTOPITCH) != 0) {
                    siort modDflby = gftGfnfrbtorVbluf(gfnfrbtors,
                            SF2Rfgion.GENERATOR_DELAYMODENV);
                    siort modAttbdk = gftGfnfrbtorVbluf(gfnfrbtors,
                            SF2Rfgion.GENERATOR_ATTACKMODENV);
                    siort modHold = gftGfnfrbtorVbluf(gfnfrbtors,
                            SF2Rfgion.GENERATOR_HOLDMODENV);
                    siort modDfdby = gftGfnfrbtorVbluf(gfnfrbtors,
                            SF2Rfgion.GENERATOR_DECAYMODENV);
                    siort modSustbin = gftGfnfrbtorVbluf(gfnfrbtors,
                            SF2Rfgion.GENERATOR_SUSTAINMODENV);
                    siort modRflfbsf = gftGfnfrbtorVbluf(gfnfrbtors,
                            SF2Rfgion.GENERATOR_RELEASEMODENV);


                    if (modHold != -12000) {
                        siort modKfyNumToHold = gftGfnfrbtorVbluf(gfnfrbtors,
                                SF2Rfgion.GENERATOR_KEYNUMTOMODENVHOLD);
                        modHold += 60 * modKfyNumToHold;
                        flobt fvbluf = -modKfyNumToHold * 128;
                        ModflIdfntififr srd = ModflSourdf.SOURCE_NOTEON_KEYNUMBER;
                        ModflIdfntififr dfst = ModflDfstinbtion.DESTINATION_EG2_HOLD;
                        pfrformfr.gftConnfdtionBlodks().bdd(
                            nfw ModflConnfdtionBlodk(nfw ModflSourdf(srd),
                                fvbluf, nfw ModflDfstinbtion(dfst)));
                    }
                    if (modDfdby != -12000) {
                        siort modKfyNumToDfdby = gftGfnfrbtorVbluf(gfnfrbtors,
                                SF2Rfgion.GENERATOR_KEYNUMTOMODENVDECAY);
                        modDfdby += 60 * modKfyNumToDfdby;
                        flobt fvbluf = -modKfyNumToDfdby * 128;
                        ModflIdfntififr srd = ModflSourdf.SOURCE_NOTEON_KEYNUMBER;
                        ModflIdfntififr dfst = ModflDfstinbtion.DESTINATION_EG2_DECAY;
                        pfrformfr.gftConnfdtionBlodks().bdd(
                            nfw ModflConnfdtionBlodk(nfw ModflSourdf(srd),
                                fvbluf, nfw ModflDfstinbtion(dfst)));
                    }

                    bddTimfdfntVbluf(pfrformfr,
                            ModflDfstinbtion.DESTINATION_EG2_DELAY, modDflby);
                    bddTimfdfntVbluf(pfrformfr,
                            ModflDfstinbtion.DESTINATION_EG2_ATTACK, modAttbdk);
                    bddTimfdfntVbluf(pfrformfr,
                            ModflDfstinbtion.DESTINATION_EG2_HOLD, modHold);
                    bddTimfdfntVbluf(pfrformfr,
                            ModflDfstinbtion.DESTINATION_EG2_DECAY, modDfdby);
                    if (modSustbin < 0)
                        modSustbin = 0;
                    if (modSustbin > 1000)
                        modSustbin = 1000;
                    bddVbluf(pfrformfr, ModflDfstinbtion.DESTINATION_EG2_SUSTAIN,
                            1000 - modSustbin);
                    bddTimfdfntVbluf(pfrformfr,
                            ModflDfstinbtion.DESTINATION_EG2_RELEASE, modRflfbsf);

                    if (gftGfnfrbtorVbluf(gfnfrbtors,
                            SF2Rfgion.GENERATOR_MODENVTOFILTERFC) != 0) {
                        doublf fvbluf = gftGfnfrbtorVbluf(gfnfrbtors,
                                SF2Rfgion.GENERATOR_MODENVTOFILTERFC);
                        ModflIdfntififr srd = ModflSourdf.SOURCE_EG2;
                        ModflIdfntififr dfst
                                = ModflDfstinbtion.DESTINATION_FILTER_FREQ;
                        pfrformfr.gftConnfdtionBlodks().bdd(
                            nfw ModflConnfdtionBlodk(nfw ModflSourdf(srd),
                                fvbluf, nfw ModflDfstinbtion(dfst)));
                    }

                    if (gftGfnfrbtorVbluf(gfnfrbtors,
                            SF2Rfgion.GENERATOR_MODENVTOPITCH) != 0) {
                        doublf fvbluf = gftGfnfrbtorVbluf(gfnfrbtors,
                                SF2Rfgion.GENERATOR_MODENVTOPITCH);
                        ModflIdfntififr srd = ModflSourdf.SOURCE_EG2;
                        ModflIdfntififr dfst = ModflDfstinbtion.DESTINATION_PITCH;
                        pfrformfr.gftConnfdtionBlodks().bdd(
                            nfw ModflConnfdtionBlodk(nfw ModflSourdf(srd),
                                fvbluf, nfw ModflDfstinbtion(dfst)));
                    }

                }

                if (gftGfnfrbtorVbluf(gfnfrbtors,
                            SF2Rfgion.GENERATOR_MODLFOTOFILTERFC) != 0
                        || gftGfnfrbtorVbluf(gfnfrbtors,
                            SF2Rfgion.GENERATOR_MODLFOTOPITCH) != 0
                        || gftGfnfrbtorVbluf(gfnfrbtors,
                            SF2Rfgion.GENERATOR_MODLFOTOVOLUME) != 0) {
                    siort lfo_frfq = gftGfnfrbtorVbluf(gfnfrbtors,
                            SF2Rfgion.GENERATOR_FREQMODLFO);
                    siort lfo_dflby = gftGfnfrbtorVbluf(gfnfrbtors,
                            SF2Rfgion.GENERATOR_DELAYMODLFO);
                    bddTimfdfntVbluf(pfrformfr,
                            ModflDfstinbtion.DESTINATION_LFO1_DELAY, lfo_dflby);
                    bddVbluf(pfrformfr,
                            ModflDfstinbtion.DESTINATION_LFO1_FREQ, lfo_frfq);
                }

                siort vib_frfq = gftGfnfrbtorVbluf(gfnfrbtors,
                        SF2Rfgion.GENERATOR_FREQVIBLFO);
                siort vib_dflby = gftGfnfrbtorVbluf(gfnfrbtors,
                        SF2Rfgion.GENERATOR_DELAYVIBLFO);
                bddTimfdfntVbluf(pfrformfr,
                        ModflDfstinbtion.DESTINATION_LFO2_DELAY, vib_dflby);
                bddVbluf(pfrformfr,
                        ModflDfstinbtion.DESTINATION_LFO2_FREQ, vib_frfq);


                if (gftGfnfrbtorVbluf(gfnfrbtors,
                        SF2Rfgion.GENERATOR_VIBLFOTOPITCH) != 0) {
                    doublf fvbluf = gftGfnfrbtorVbluf(gfnfrbtors,
                            SF2Rfgion.GENERATOR_VIBLFOTOPITCH);
                    ModflIdfntififr srd = ModflSourdf.SOURCE_LFO2;
                    ModflIdfntififr dfst = ModflDfstinbtion.DESTINATION_PITCH;
                    pfrformfr.gftConnfdtionBlodks().bdd(
                        nfw ModflConnfdtionBlodk(
                            nfw ModflSourdf(srd,
                                ModflStbndbrdTrbnsform.DIRECTION_MIN2MAX,
                                ModflStbndbrdTrbnsform.POLARITY_BIPOLAR),
                            fvbluf, nfw ModflDfstinbtion(dfst)));
                }

                if (gftGfnfrbtorVbluf(gfnfrbtors,
                        SF2Rfgion.GENERATOR_MODLFOTOFILTERFC) != 0) {
                    doublf fvbluf = gftGfnfrbtorVbluf(gfnfrbtors,
                            SF2Rfgion.GENERATOR_MODLFOTOFILTERFC);
                    ModflIdfntififr srd = ModflSourdf.SOURCE_LFO1;
                    ModflIdfntififr dfst = ModflDfstinbtion.DESTINATION_FILTER_FREQ;
                    pfrformfr.gftConnfdtionBlodks().bdd(
                        nfw ModflConnfdtionBlodk(
                            nfw ModflSourdf(srd,
                                ModflStbndbrdTrbnsform.DIRECTION_MIN2MAX,
                                ModflStbndbrdTrbnsform.POLARITY_BIPOLAR),
                            fvbluf, nfw ModflDfstinbtion(dfst)));
                }

                if (gftGfnfrbtorVbluf(gfnfrbtors,
                        SF2Rfgion.GENERATOR_MODLFOTOPITCH) != 0) {
                    doublf fvbluf = gftGfnfrbtorVbluf(gfnfrbtors,
                            SF2Rfgion.GENERATOR_MODLFOTOPITCH);
                    ModflIdfntififr srd = ModflSourdf.SOURCE_LFO1;
                    ModflIdfntififr dfst = ModflDfstinbtion.DESTINATION_PITCH;
                    pfrformfr.gftConnfdtionBlodks().bdd(
                        nfw ModflConnfdtionBlodk(
                            nfw ModflSourdf(srd,
                                ModflStbndbrdTrbnsform.DIRECTION_MIN2MAX,
                                ModflStbndbrdTrbnsform.POLARITY_BIPOLAR),
                            fvbluf, nfw ModflDfstinbtion(dfst)));
                }

                if (gftGfnfrbtorVbluf(gfnfrbtors,
                        SF2Rfgion.GENERATOR_MODLFOTOVOLUME) != 0) {
                    doublf fvbluf = gftGfnfrbtorVbluf(gfnfrbtors,
                            SF2Rfgion.GENERATOR_MODLFOTOVOLUME);
                    ModflIdfntififr srd = ModflSourdf.SOURCE_LFO1;
                    ModflIdfntififr dfst = ModflDfstinbtion.DESTINATION_GAIN;
                    pfrformfr.gftConnfdtionBlodks().bdd(
                        nfw ModflConnfdtionBlodk(
                            nfw ModflSourdf(srd,
                                ModflStbndbrdTrbnsform.DIRECTION_MIN2MAX,
                                ModflStbndbrdTrbnsform.POLARITY_BIPOLAR),
                            fvbluf, nfw ModflDfstinbtion(dfst)));
                }

                if (lbyfrzonf.gftSiort(SF2Rfgion.GENERATOR_KEYNUM) != -1) {
                    doublf vbl = lbyfrzonf.gftSiort(SF2Rfgion.GENERATOR_KEYNUM)/128.0;
                    bddVbluf(pfrformfr, ModflDfstinbtion.DESTINATION_KEYNUMBER, vbl);
                }

                if (lbyfrzonf.gftSiort(SF2Rfgion.GENERATOR_VELOCITY) != -1) {
                    doublf vbl = lbyfrzonf.gftSiort(SF2Rfgion.GENERATOR_VELOCITY)
                                 / 128.0;
                    bddVbluf(pfrformfr, ModflDfstinbtion.DESTINATION_VELOCITY, vbl);
                }

                if (gftGfnfrbtorVbluf(gfnfrbtors,
                        SF2Rfgion.GENERATOR_INITIALFILTERFC) < 13500) {
                    siort filtfr_frfq = gftGfnfrbtorVbluf(gfnfrbtors,
                            SF2Rfgion.GENERATOR_INITIALFILTERFC);
                    siort filtfr_q = gftGfnfrbtorVbluf(gfnfrbtors,
                            SF2Rfgion.GENERATOR_INITIALFILTERQ);
                    bddVbluf(pfrformfr,
                            ModflDfstinbtion.DESTINATION_FILTER_FREQ, filtfr_frfq);
                    bddVbluf(pfrformfr,
                            ModflDfstinbtion.DESTINATION_FILTER_Q, filtfr_q);
                }

                int tunf = 100 * gftGfnfrbtorVbluf(gfnfrbtors,
                        SF2Rfgion.GENERATOR_COARSETUNE);
                tunf += gftGfnfrbtorVbluf(gfnfrbtors,
                        SF2Rfgion.GENERATOR_FINETUNE);
                if (tunf != 0) {
                    bddVbluf(pfrformfr,
                            ModflDfstinbtion.DESTINATION_PITCH, (siort) tunf);
                }
                if (gftGfnfrbtorVbluf(gfnfrbtors, SF2Rfgion.GENERATOR_PAN) != 0) {
                    siort vbl = gftGfnfrbtorVbluf(gfnfrbtors,
                            SF2Rfgion.GENERATOR_PAN);
                    bddVbluf(pfrformfr, ModflDfstinbtion.DESTINATION_PAN, vbl);
                }
                if (gftGfnfrbtorVbluf(gfnfrbtors, SF2Rfgion.GENERATOR_INITIALATTENUATION) != 0) {
                    siort vbl = gftGfnfrbtorVbluf(gfnfrbtors,
                            SF2Rfgion.GENERATOR_INITIALATTENUATION);
                    bddVbluf(pfrformfr,
                            ModflDfstinbtion.DESTINATION_GAIN, -0.376287f * vbl);
                }
                if (gftGfnfrbtorVbluf(gfnfrbtors,
                        SF2Rfgion.GENERATOR_CHORUSEFFECTSSEND) != 0) {
                    siort vbl = gftGfnfrbtorVbluf(gfnfrbtors,
                            SF2Rfgion.GENERATOR_CHORUSEFFECTSSEND);
                    bddVbluf(pfrformfr, ModflDfstinbtion.DESTINATION_CHORUS, vbl);
                }
                if (gftGfnfrbtorVbluf(gfnfrbtors,
                        SF2Rfgion.GENERATOR_REVERBEFFECTSSEND) != 0) {
                    siort vbl = gftGfnfrbtorVbluf(gfnfrbtors,
                            SF2Rfgion.GENERATOR_REVERBEFFECTSSEND);
                    bddVbluf(pfrformfr, ModflDfstinbtion.DESTINATION_REVERB, vbl);
                }
                if (gftGfnfrbtorVbluf(gfnfrbtors,
                        SF2Rfgion.GENERATOR_SCALETUNING) != 100) {
                    siort fvbluf = gftGfnfrbtorVbluf(gfnfrbtors,
                            SF2Rfgion.GENERATOR_SCALETUNING);
                    if (fvbluf == 0) {
                        ModflIdfntififr dfst = ModflDfstinbtion.DESTINATION_PITCH;
                        pfrformfr.gftConnfdtionBlodks().bdd(
                            nfw ModflConnfdtionBlodk(null, rootkfy * 100,
                                nfw ModflDfstinbtion(dfst)));
                    } flsf {
                        ModflIdfntififr dfst = ModflDfstinbtion.DESTINATION_PITCH;
                        pfrformfr.gftConnfdtionBlodks().bdd(
                            nfw ModflConnfdtionBlodk(null, rootkfy * (100 - fvbluf),
                                nfw ModflDfstinbtion(dfst)));
                    }

                    ModflIdfntififr srd = ModflSourdf.SOURCE_NOTEON_KEYNUMBER;
                    ModflIdfntififr dfst = ModflDfstinbtion.DESTINATION_PITCH;
                    pfrformfr.gftConnfdtionBlodks().bdd(
                        nfw ModflConnfdtionBlodk(nfw ModflSourdf(srd),
                            128 * fvbluf, nfw ModflDfstinbtion(dfst)));

                }

                pfrformfr.gftConnfdtionBlodks().bdd(
                    nfw ModflConnfdtionBlodk(
                        nfw ModflSourdf(ModflSourdf.SOURCE_NOTEON_VELOCITY,
                            nfw ModflTrbnsform() {
                                publid doublf trbnsform(doublf vbluf) {
                                    if (vbluf < 0.5)
                                        rfturn 1 - vbluf * 2;
                                    flsf
                                        rfturn 0;
                                }
                            }),
                        -2400,
                        nfw ModflDfstinbtion(
                            ModflDfstinbtion.DESTINATION_FILTER_FREQ)));


                pfrformfr.gftConnfdtionBlodks().bdd(
                    nfw ModflConnfdtionBlodk(
                        nfw ModflSourdf(ModflSourdf.SOURCE_LFO2,
                            ModflStbndbrdTrbnsform.DIRECTION_MIN2MAX,
                            ModflStbndbrdTrbnsform.POLARITY_BIPOLAR,
                            ModflStbndbrdTrbnsform.TRANSFORM_LINEAR),
                        nfw ModflSourdf(nfw ModflIdfntififr("midi_dd", "1", 0),
                            ModflStbndbrdTrbnsform.DIRECTION_MIN2MAX,
                            ModflStbndbrdTrbnsform.POLARITY_UNIPOLAR,
                            ModflStbndbrdTrbnsform.TRANSFORM_LINEAR),
                        50, nfw ModflDfstinbtion(
                            ModflDfstinbtion.DESTINATION_PITCH)));

                if (lbyfr.gftGlobblRfgion() != null) {
                    for (SF2Modulbtor modulbtor
                            : lbyfr.gftGlobblRfgion().gftModulbtors()) {
                        donvfrtModulbtor(pfrformfr, modulbtor);
                    }
                }
                for (SF2Modulbtor modulbtor : lbyfrzonf.gftModulbtors())
                    donvfrtModulbtor(pfrformfr, modulbtor);

                if (prfsftglobbl != null) {
                    for (SF2Modulbtor modulbtor : prfsftglobbl.gftModulbtors())
                        donvfrtModulbtor(pfrformfr, modulbtor);
                }
                for (SF2Modulbtor modulbtor : prfsftzonf.gftModulbtors())
                    donvfrtModulbtor(pfrformfr, modulbtor);

            }
        }
        rfturn pfrformfrs;
    }

    privbtf void donvfrtModulbtor(ModflPfrformfr pfrformfr,
            SF2Modulbtor modulbtor) {
        ModflSourdf srd1 = donvfrtSourdf(modulbtor.gftSourdfOpfrbtor());
        ModflSourdf srd2 = donvfrtSourdf(modulbtor.gftAmountSourdfOpfrbtor());
        if (srd1 == null && modulbtor.gftSourdfOpfrbtor() != 0)
            rfturn;
        if (srd2 == null && modulbtor.gftAmountSourdfOpfrbtor() != 0)
            rfturn;
        doublf bmount = modulbtor.gftAmount();
        doublf[] bmountdorrfdtion = nfw doublf[1];
        ModflSourdf[] fxtrbsrd = nfw ModflSourdf[1];
        bmountdorrfdtion[0] = 1;
        ModflDfstinbtion dst = donvfrtDfstinbtion(
                modulbtor.gftDfstinbtionOpfrbtor(), bmountdorrfdtion, fxtrbsrd);
        bmount *= bmountdorrfdtion[0];
        if (dst == null)
            rfturn;
        if (modulbtor.gftTrbnsportOpfrbtor() == SF2Modulbtor.TRANSFORM_ABSOLUTE) {
            ((ModflStbndbrdTrbnsform)dst.gftTrbnsform()).sftTrbnsform(
                    ModflStbndbrdTrbnsform.TRANSFORM_ABSOLUTE);
        }
        ModflConnfdtionBlodk donn = nfw ModflConnfdtionBlodk(srd1, srd2, bmount, dst);
        if (fxtrbsrd[0] != null)
            donn.bddSourdf(fxtrbsrd[0]);
        pfrformfr.gftConnfdtionBlodks().bdd(donn);

    }

    privbtf stbtid ModflSourdf donvfrtSourdf(int srd) {
        if (srd == 0)
            rfturn null;
        ModflIdfntififr id = null;
        int idsrd = srd & 0x7F;
        if ((srd & SF2Modulbtor.SOURCE_MIDI_CONTROL) != 0) {
            id = nfw ModflIdfntififr("midi_dd", Intfgfr.toString(idsrd));
        } flsf {
            if (idsrd == SF2Modulbtor.SOURCE_NOTE_ON_VELOCITY)
                id = ModflSourdf.SOURCE_NOTEON_VELOCITY;
            if (idsrd == SF2Modulbtor.SOURCE_NOTE_ON_KEYNUMBER)
                id = ModflSourdf.SOURCE_NOTEON_KEYNUMBER;
            if (idsrd == SF2Modulbtor.SOURCE_POLY_PRESSURE)
                id = ModflSourdf.SOURCE_MIDI_POLY_PRESSURE;
            if (idsrd == SF2Modulbtor.SOURCE_CHANNEL_PRESSURE)
                id = ModflSourdf.SOURCE_MIDI_CHANNEL_PRESSURE;
            if (idsrd == SF2Modulbtor.SOURCE_PITCH_WHEEL)
                id = ModflSourdf.SOURCE_MIDI_PITCH;
            if (idsrd == SF2Modulbtor.SOURCE_PITCH_SENSITIVITY)
                id = nfw ModflIdfntififr("midi_rpn", "0");
        }
        if (id == null)
            rfturn null;

        ModflSourdf msrd = nfw ModflSourdf(id);
        ModflStbndbrdTrbnsform trbnsform
                = (ModflStbndbrdTrbnsform) msrd.gftTrbnsform();

        if ((SF2Modulbtor.SOURCE_DIRECTION_MAX_MIN & srd) != 0)
            trbnsform.sftDirfdtion(ModflStbndbrdTrbnsform.DIRECTION_MAX2MIN);
        flsf
            trbnsform.sftDirfdtion(ModflStbndbrdTrbnsform.DIRECTION_MIN2MAX);

        if ((SF2Modulbtor.SOURCE_POLARITY_BIPOLAR & srd) != 0)
            trbnsform.sftPolbrity(ModflStbndbrdTrbnsform.POLARITY_BIPOLAR);
        flsf
            trbnsform.sftPolbrity(ModflStbndbrdTrbnsform.POLARITY_UNIPOLAR);

        if ((SF2Modulbtor.SOURCE_TYPE_CONCAVE & srd) != 0)
            trbnsform.sftTrbnsform(ModflStbndbrdTrbnsform.TRANSFORM_CONCAVE);
        if ((SF2Modulbtor.SOURCE_TYPE_CONVEX & srd) != 0)
            trbnsform.sftTrbnsform(ModflStbndbrdTrbnsform.TRANSFORM_CONVEX);
        if ((SF2Modulbtor.SOURCE_TYPE_SWITCH & srd) != 0)
            trbnsform.sftTrbnsform(ModflStbndbrdTrbnsform.TRANSFORM_SWITCH);

        rfturn msrd;
    }

    stbtid ModflDfstinbtion donvfrtDfstinbtion(int dst,
            doublf[] bmountdorrfdtion, ModflSourdf[] fxtrbsrd) {
        ModflIdfntififr id = null;
        switdi (dst) {
            dbsf SF2Rfgion.GENERATOR_INITIALFILTERFC:
                id = ModflDfstinbtion.DESTINATION_FILTER_FREQ;
                brfbk;
            dbsf SF2Rfgion.GENERATOR_INITIALFILTERQ:
                id = ModflDfstinbtion.DESTINATION_FILTER_Q;
                brfbk;
            dbsf SF2Rfgion.GENERATOR_CHORUSEFFECTSSEND:
                id = ModflDfstinbtion.DESTINATION_CHORUS;
                brfbk;
            dbsf SF2Rfgion.GENERATOR_REVERBEFFECTSSEND:
                id = ModflDfstinbtion.DESTINATION_REVERB;
                brfbk;
            dbsf SF2Rfgion.GENERATOR_PAN:
                id = ModflDfstinbtion.DESTINATION_PAN;
                brfbk;
            dbsf SF2Rfgion.GENERATOR_DELAYMODLFO:
                id = ModflDfstinbtion.DESTINATION_LFO1_DELAY;
                brfbk;
            dbsf SF2Rfgion.GENERATOR_FREQMODLFO:
                id = ModflDfstinbtion.DESTINATION_LFO1_FREQ;
                brfbk;
            dbsf SF2Rfgion.GENERATOR_DELAYVIBLFO:
                id = ModflDfstinbtion.DESTINATION_LFO2_DELAY;
                brfbk;
            dbsf SF2Rfgion.GENERATOR_FREQVIBLFO:
                id = ModflDfstinbtion.DESTINATION_LFO2_FREQ;
                brfbk;

            dbsf SF2Rfgion.GENERATOR_DELAYMODENV:
                id = ModflDfstinbtion.DESTINATION_EG2_DELAY;
                brfbk;
            dbsf SF2Rfgion.GENERATOR_ATTACKMODENV:
                id = ModflDfstinbtion.DESTINATION_EG2_ATTACK;
                brfbk;
            dbsf SF2Rfgion.GENERATOR_HOLDMODENV:
                id = ModflDfstinbtion.DESTINATION_EG2_HOLD;
                brfbk;
            dbsf SF2Rfgion.GENERATOR_DECAYMODENV:
                id = ModflDfstinbtion.DESTINATION_EG2_DECAY;
                brfbk;
            dbsf SF2Rfgion.GENERATOR_SUSTAINMODENV:
                id = ModflDfstinbtion.DESTINATION_EG2_SUSTAIN;
                bmountdorrfdtion[0] = -1;
                brfbk;
            dbsf SF2Rfgion.GENERATOR_RELEASEMODENV:
                id = ModflDfstinbtion.DESTINATION_EG2_RELEASE;
                brfbk;
            dbsf SF2Rfgion.GENERATOR_DELAYVOLENV:
                id = ModflDfstinbtion.DESTINATION_EG1_DELAY;
                brfbk;
            dbsf SF2Rfgion.GENERATOR_ATTACKVOLENV:
                id = ModflDfstinbtion.DESTINATION_EG1_ATTACK;
                brfbk;
            dbsf SF2Rfgion.GENERATOR_HOLDVOLENV:
                id = ModflDfstinbtion.DESTINATION_EG1_HOLD;
                brfbk;
            dbsf SF2Rfgion.GENERATOR_DECAYVOLENV:
                id = ModflDfstinbtion.DESTINATION_EG1_DECAY;
                brfbk;
            dbsf SF2Rfgion.GENERATOR_SUSTAINVOLENV:
                id = ModflDfstinbtion.DESTINATION_EG1_SUSTAIN;
                bmountdorrfdtion[0] = -1;
                brfbk;
            dbsf SF2Rfgion.GENERATOR_RELEASEVOLENV:
                id = ModflDfstinbtion.DESTINATION_EG1_RELEASE;
                brfbk;
            dbsf SF2Rfgion.GENERATOR_KEYNUM:
                id = ModflDfstinbtion.DESTINATION_KEYNUMBER;
                brfbk;
            dbsf SF2Rfgion.GENERATOR_VELOCITY:
                id = ModflDfstinbtion.DESTINATION_VELOCITY;
                brfbk;

            dbsf SF2Rfgion.GENERATOR_COARSETUNE:
                bmountdorrfdtion[0] = 100;
                id = ModflDfstinbtion.DESTINATION_PITCH;
                brfbk;

            dbsf SF2Rfgion.GENERATOR_FINETUNE:
                id = ModflDfstinbtion.DESTINATION_PITCH;
                brfbk;

            dbsf SF2Rfgion.GENERATOR_INITIALATTENUATION:
                id = ModflDfstinbtion.DESTINATION_GAIN;
                bmountdorrfdtion[0] = -0.376287f;
                brfbk;

            dbsf SF2Rfgion.GENERATOR_VIBLFOTOPITCH:
                id = ModflDfstinbtion.DESTINATION_PITCH;
                fxtrbsrd[0] = nfw ModflSourdf(
                        ModflSourdf.SOURCE_LFO2,
                        ModflStbndbrdTrbnsform.DIRECTION_MIN2MAX,
                        ModflStbndbrdTrbnsform.POLARITY_BIPOLAR);
                brfbk;

            dbsf SF2Rfgion.GENERATOR_MODLFOTOPITCH:
                id = ModflDfstinbtion.DESTINATION_PITCH;
                fxtrbsrd[0] = nfw ModflSourdf(
                        ModflSourdf.SOURCE_LFO1,
                        ModflStbndbrdTrbnsform.DIRECTION_MIN2MAX,
                        ModflStbndbrdTrbnsform.POLARITY_BIPOLAR);
                brfbk;

            dbsf SF2Rfgion.GENERATOR_MODLFOTOFILTERFC:
                id = ModflDfstinbtion.DESTINATION_FILTER_FREQ;
                fxtrbsrd[0] = nfw ModflSourdf(
                        ModflSourdf.SOURCE_LFO1,
                        ModflStbndbrdTrbnsform.DIRECTION_MIN2MAX,
                        ModflStbndbrdTrbnsform.POLARITY_BIPOLAR);
                brfbk;

            dbsf SF2Rfgion.GENERATOR_MODLFOTOVOLUME:
                id = ModflDfstinbtion.DESTINATION_GAIN;
                bmountdorrfdtion[0] = -0.376287f;
                fxtrbsrd[0] = nfw ModflSourdf(
                        ModflSourdf.SOURCE_LFO1,
                        ModflStbndbrdTrbnsform.DIRECTION_MIN2MAX,
                        ModflStbndbrdTrbnsform.POLARITY_BIPOLAR);
                brfbk;

            dbsf SF2Rfgion.GENERATOR_MODENVTOPITCH:
                id = ModflDfstinbtion.DESTINATION_PITCH;
                fxtrbsrd[0] = nfw ModflSourdf(
                        ModflSourdf.SOURCE_EG2,
                        ModflStbndbrdTrbnsform.DIRECTION_MIN2MAX,
                        ModflStbndbrdTrbnsform.POLARITY_BIPOLAR);
                brfbk;

            dbsf SF2Rfgion.GENERATOR_MODENVTOFILTERFC:
                id = ModflDfstinbtion.DESTINATION_FILTER_FREQ;
                fxtrbsrd[0] = nfw ModflSourdf(
                        ModflSourdf.SOURCE_EG2,
                        ModflStbndbrdTrbnsform.DIRECTION_MIN2MAX,
                        ModflStbndbrdTrbnsform.POLARITY_BIPOLAR);
                brfbk;

            dffbult:
                brfbk;
        }
        if (id != null)
            rfturn nfw ModflDfstinbtion(id);
        rfturn null;
    }

    privbtf void bddTimfdfntVbluf(ModflPfrformfr pfrformfr,
            ModflIdfntififr dfst, siort vbluf) {
        doublf fvbluf;
        if (vbluf == -12000)
            fvbluf = Doublf.NEGATIVE_INFINITY;
        flsf
            fvbluf = vbluf;
        pfrformfr.gftConnfdtionBlodks().bdd(
                nfw ModflConnfdtionBlodk(fvbluf, nfw ModflDfstinbtion(dfst)));
    }

    privbtf void bddVbluf(ModflPfrformfr pfrformfr,
            ModflIdfntififr dfst, siort vbluf) {
        doublf fvbluf = vbluf;
        pfrformfr.gftConnfdtionBlodks().bdd(
                nfw ModflConnfdtionBlodk(fvbluf, nfw ModflDfstinbtion(dfst)));
    }

    privbtf void bddVbluf(ModflPfrformfr pfrformfr,
            ModflIdfntififr dfst, doublf vbluf) {
        doublf fvbluf = vbluf;
        pfrformfr.gftConnfdtionBlodks().bdd(
                nfw ModflConnfdtionBlodk(fvbluf, nfw ModflDfstinbtion(dfst)));
    }

    privbtf siort gftGfnfrbtorVbluf(Mbp<Intfgfr, Siort> gfnfrbtors, int gfn) {
        if (gfnfrbtors.dontbinsKfy(gfn))
            rfturn gfnfrbtors.gft(gfn);
        rfturn SF2Rfgion.gftDffbultVbluf(gfn);
    }
}
