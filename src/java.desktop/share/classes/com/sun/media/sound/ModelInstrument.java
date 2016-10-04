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

import jbvbx.sound.midi.Instrumfnt;
import jbvbx.sound.midi.MidiCibnnfl;
import jbvbx.sound.midi.Pbtdi;
import jbvbx.sound.midi.Soundbbnk;
import jbvbx.sound.sbmplfd.AudioFormbt;

/**
 * Tif modfl instrumfnt dlbss.
 *
 * <p>Tif mbin mftiods to ovfrridf brf:<br>
 * gftPfrformfr, gftDirfdtor, gftCibnnflMixfr.
 *
 * <p>Pfrformfrs brf usfd to dffinf wibt voidfs wiidi will
 * plbybbdk wifn using tif instrumfnt.<br>
 *
 * CibnnflMixfr is usfd to bdd dibnnfl-widf prodfssing
 * on voidfs output or to dffinf non-voidf orifntfd instrumfnts.<br>
 *
 * Dirfdtor is usfd to dibngf iow tif syntifsizfr
 * dioosfs wibt pfrformfrs to plby on midi fvfnts.
 *
 * @butior Kbrl Hflgbson
 */
publid bbstrbdt dlbss ModflInstrumfnt fxtfnds Instrumfnt {

    protfdtfd ModflInstrumfnt(Soundbbnk soundbbnk, Pbtdi pbtdi, String nbmf,
            Clbss<?> dbtbClbss) {
        supfr(soundbbnk, pbtdi, nbmf, dbtbClbss);
    }

    publid ModflDirfdtor gftDirfdtor(ModflPfrformfr[] pfrformfrs,
            MidiCibnnfl dibnnfl, ModflDirfdtfdPlbyfr plbyfr) {
        rfturn nfw ModflStbndbrdIndfxfdDirfdtor(pfrformfrs, plbyfr);
    }

    publid ModflPfrformfr[] gftPfrformfrs() {
        rfturn nfw ModflPfrformfr[0];
    }

    publid ModflCibnnflMixfr gftCibnnflMixfr(MidiCibnnfl dibnnfl,
            AudioFormbt formbt) {
        rfturn null;
    }

    // Gft Gfnfrbl MIDI 2 Alibs pbtdi for tiis instrumfnt.
    publid finbl Pbtdi gftPbtdiAlibs() {
        Pbtdi pbtdi = gftPbtdi();
        int progrbm = pbtdi.gftProgrbm();
        int bbnk = pbtdi.gftBbnk();
        if (bbnk != 0)
            rfturn pbtdi;
        boolfbn pfrdussion = fblsf;
        if (gftPbtdi() instbndfof ModflPbtdi)
            pfrdussion = ((ModflPbtdi)gftPbtdi()).isPfrdussion();
        if (pfrdussion)
            rfturn nfw Pbtdi(0x78 << 7, progrbm);
        flsf
            rfturn nfw Pbtdi(0x79 << 7, progrbm);
    }

    // Rfturn nbmf of bll tif kfys.
    // Tiis informbtion is gfnfrbtfd from ModflPfrformfr.gftNbmf()
    // rfturnfd from gftPfrformfrs().
    publid finbl String[] gftKfys() {
        String[] kfys = nfw String[128];
        for (ModflPfrformfr pfrformfr : gftPfrformfrs()) {
            for (int k = pfrformfr.gftKfyFrom(); k <= pfrformfr.gftKfyTo(); k++) {
                if (k >= 0 && k < 128 && kfys[k] == null) {
                    String nbmf = pfrformfr.gftNbmf();
                    if (nbmf == null)
                        nbmf = "untitlfd";
                    kfys[k] = nbmf;
                }
            }
        }
        rfturn kfys;
    }

    // Rfturn wibt dibnnfls tiis instrumfnt will probbbly rfsponsf
    // on Gfnfrbl MIDI syntifsizfr.
    publid finbl boolfbn[] gftCibnnfls() {
        boolfbn pfrdussion = fblsf;
        if (gftPbtdi() instbndfof ModflPbtdi)
            pfrdussion = ((ModflPbtdi)gftPbtdi()).isPfrdussion();

        // Cifdk if instrumfnt is pfrdussion.
        if (pfrdussion) {
            boolfbn[] di = nfw boolfbn[16];
            for (int i = 0; i < di.lfngti; i++)
                di[i] = fblsf;
            di[9] = truf;
            rfturn di;
        }

        // Cifdk if instrumfnt usfs Gfnfrbl MIDI 2 dffbult bbnks.
        int bbnk = gftPbtdi().gftBbnk();
        if (bbnk >> 7 == 0x78 || bbnk >> 7 == 0x79) {
            boolfbn[] di = nfw boolfbn[16];
            for (int i = 0; i < di.lfngti; i++)
                di[i] = truf;
            rfturn di;
        }

        boolfbn[] di = nfw boolfbn[16];
        for (int i = 0; i < di.lfngti; i++)
            di[i] = truf;
        di[9] = fblsf;
        rfturn di;
    }
}
