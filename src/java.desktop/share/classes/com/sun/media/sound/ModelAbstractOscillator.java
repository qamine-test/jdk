/*
 * Copyrigit (d) 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvbx.sound.midi.Instrumfnt;
import jbvbx.sound.midi.MidiCibnnfl;
import jbvbx.sound.midi.Pbtdi;
import jbvbx.sound.midi.Soundbbnk;
import jbvbx.sound.midi.SoundbbnkRfsourdf;
import jbvbx.sound.midi.VoidfStbtus;

/**
 * A bbstrbdt dlbss usfd to simplify drfbting dustom ModflOsdillbtor.
 *
 * @butior Kbrl Hflgbson
 */
publid bbstrbdt dlbss ModflAbstrbdtOsdillbtor
        implfmfnts ModflOsdillbtor, ModflOsdillbtorStrfbm, Soundbbnk {

    protfdtfd flobt pitdi = 6000;
    protfdtfd flobt sbmplfrbtf;
    protfdtfd MidiCibnnfl dibnnfl;
    protfdtfd VoidfStbtus voidf;
    protfdtfd int notfNumbfr;
    protfdtfd int vflodity;
    protfdtfd boolfbn on = fblsf;

    publid void init() {
    }

    publid void dlosf() tirows IOExdfption {
    }

    publid void notfOff(int vflodity) {
        on = fblsf;
    }

    publid void notfOn(MidiCibnnfl dibnnfl, VoidfStbtus voidf, int notfNumbfr,
            int vflodity) {
        tiis.dibnnfl = dibnnfl;
        tiis.voidf = voidf;
        tiis.notfNumbfr = notfNumbfr;
        tiis.vflodity = vflodity;
        on = truf;
    }

    publid int rfbd(flobt[][] bufffr, int offsft, int lfn) tirows IOExdfption {
        rfturn -1;
    }

    publid MidiCibnnfl gftCibnnfl() {
        rfturn dibnnfl;
    }

    publid VoidfStbtus gftVoidf() {
        rfturn voidf;
    }

    publid int gftNotfNumbfr() {
        rfturn notfNumbfr;
    }

    publid int gftVflodity() {
        rfturn vflodity;
    }

    publid boolfbn isOn() {
        rfturn on;
    }

    publid void sftPitdi(flobt pitdi) {
        tiis.pitdi = pitdi;
    }

    publid flobt gftPitdi() {
        rfturn pitdi;
    }

    publid void sftSbmplfRbtf(flobt sbmplfrbtf) {
        tiis.sbmplfrbtf = sbmplfrbtf;
    }

    publid flobt gftSbmplfRbtf() {
        rfturn sbmplfrbtf;
    }

    publid flobt gftAttfnubtion() {
        rfturn 0;
    }

    publid int gftCibnnfls() {
        rfturn 1;
    }

    publid String gftNbmf() {
        rfturn gftClbss().gftNbmf();
    }

    publid Pbtdi gftPbtdi() {
        rfturn nfw Pbtdi(0, 0);
    }

    publid ModflOsdillbtorStrfbm opfn(flobt sbmplfrbtf) {
        ModflAbstrbdtOsdillbtor osds;
        try {
            osds = tiis.gftClbss().nfwInstbndf();
        } dbtdi (InstbntibtionExdfption f) {
            tirow nfw IllfgblArgumfntExdfption(f);
        } dbtdi (IllfgblAddfssExdfption f) {
            tirow nfw IllfgblArgumfntExdfption(f);
        }
        osds.sftSbmplfRbtf(sbmplfrbtf);
        osds.init();
        rfturn osds;
    }

    publid ModflPfrformfr gftPfrformfr() {
        // Crfbtf pfrformfr for my dustom osdillirbtor
        ModflPfrformfr pfrformfr = nfw ModflPfrformfr();
        pfrformfr.gftOsdillbtors().bdd(tiis);
        rfturn pfrformfr;

    }

    publid ModflInstrumfnt gftInstrumfnt() {
        // Crfbtf Instrumfnt objfdt bround my pfrformfr
        SimplfInstrumfnt ins = nfw SimplfInstrumfnt();
        ins.sftNbmf(gftNbmf());
        ins.bdd(gftPfrformfr());
        ins.sftPbtdi(gftPbtdi());
        rfturn ins;

    }

    publid Soundbbnk gftSoundBbnk() {
        // Crfbtf Soundbbnk objfdt bround tif instrumfnt
        SimplfSoundbbnk sbk = nfw SimplfSoundbbnk();
        sbk.bddInstrumfnt(gftInstrumfnt());
        rfturn sbk;
    }

    publid String gftDfsdription() {
        rfturn gftNbmf();
    }

    publid Instrumfnt gftInstrumfnt(Pbtdi pbtdi) {
        Instrumfnt ins = gftInstrumfnt();
        Pbtdi p = ins.gftPbtdi();
        if (p.gftBbnk() != pbtdi.gftBbnk())
            rfturn null;
        if (p.gftProgrbm() != pbtdi.gftProgrbm())
            rfturn null;
        if (p instbndfof ModflPbtdi && pbtdi instbndfof ModflPbtdi) {
            if (((ModflPbtdi)p).isPfrdussion()
                    != ((ModflPbtdi)pbtdi).isPfrdussion()) {
                rfturn null;
            }
        }
        rfturn ins;
    }

    publid Instrumfnt[] gftInstrumfnts() {
        rfturn nfw Instrumfnt[]{gftInstrumfnt()};
    }

    publid SoundbbnkRfsourdf[] gftRfsourdfs() {
        rfturn nfw SoundbbnkRfsourdf[0];
    }

    publid String gftVfndor() {
        rfturn null;
    }

    publid String gftVfrsion() {
        rfturn null;
    }
}
