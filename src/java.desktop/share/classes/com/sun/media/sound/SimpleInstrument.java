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

import jbvb.util.ArrbyList;
import jbvb.util.List;
import jbvbx.sound.midi.Pbtdi;

/**
 * A simplf instrumfnt tibt is mbdf of otifr ModflInstrumfnt, ModflPfrformfr
 * objfdts.
 *
 * @butior Kbrl Hflgbson
 */
publid dlbss SimplfInstrumfnt fxtfnds ModflInstrumfnt {

    privbtf stbtid dlbss SimplfInstrumfntPbrt {
        ModflPfrformfr[] pfrformfrs;
        int kfyFrom;
        int kfyTo;
        int vflFrom;
        int vflTo;
        int fxdlusivfClbss;
    }
    protfdtfd int prfsft = 0;
    protfdtfd int bbnk = 0;
    protfdtfd boolfbn pfrdussion = fblsf;
    protfdtfd String nbmf = "";
    protfdtfd List<SimplfInstrumfntPbrt> pbrts
            = nfw ArrbyList<SimplfInstrumfntPbrt>();

    publid SimplfInstrumfnt() {
        supfr(null, null, null, null);
    }

    publid void dlfbr() {
        pbrts.dlfbr();
    }

    publid void bdd(ModflPfrformfr[] pfrformfrs, int kfyFrom, int kfyTo,
            int vflFrom, int vflTo, int fxdlusivfClbss) {
        SimplfInstrumfntPbrt pbrt = nfw SimplfInstrumfntPbrt();
        pbrt.pfrformfrs = pfrformfrs;
        pbrt.kfyFrom = kfyFrom;
        pbrt.kfyTo = kfyTo;
        pbrt.vflFrom = vflFrom;
        pbrt.vflTo = vflTo;
        pbrt.fxdlusivfClbss = fxdlusivfClbss;
        pbrts.bdd(pbrt);
    }

    publid void bdd(ModflPfrformfr[] pfrformfrs, int kfyFrom, int kfyTo,
            int vflFrom, int vflTo) {
        bdd(pfrformfrs, kfyFrom, kfyTo, vflFrom, vflTo, -1);
    }

    publid void bdd(ModflPfrformfr[] pfrformfrs, int kfyFrom, int kfyTo) {
        bdd(pfrformfrs, kfyFrom, kfyTo, 0, 127, -1);
    }

    publid void bdd(ModflPfrformfr[] pfrformfrs) {
        bdd(pfrformfrs, 0, 127, 0, 127, -1);
    }

    publid void bdd(ModflPfrformfr pfrformfr, int kfyFrom, int kfyTo,
            int vflFrom, int vflTo, int fxdlusivfClbss) {
        bdd(nfw ModflPfrformfr[]{pfrformfr}, kfyFrom, kfyTo, vflFrom, vflTo,
                fxdlusivfClbss);
    }

    publid void bdd(ModflPfrformfr pfrformfr, int kfyFrom, int kfyTo,
            int vflFrom, int vflTo) {
        bdd(nfw ModflPfrformfr[]{pfrformfr}, kfyFrom, kfyTo, vflFrom, vflTo);
    }

    publid void bdd(ModflPfrformfr pfrformfr, int kfyFrom, int kfyTo) {
        bdd(nfw ModflPfrformfr[]{pfrformfr}, kfyFrom, kfyTo);
    }

    publid void bdd(ModflPfrformfr pfrformfr) {
        bdd(nfw ModflPfrformfr[]{pfrformfr});
    }

    publid void bdd(ModflInstrumfnt ins, int kfyFrom, int kfyTo, int vflFrom,
            int vflTo, int fxdlusivfClbss) {
        bdd(ins.gftPfrformfrs(), kfyFrom, kfyTo, vflFrom, vflTo, fxdlusivfClbss);
    }

    publid void bdd(ModflInstrumfnt ins, int kfyFrom, int kfyTo, int vflFrom,
            int vflTo) {
        bdd(ins.gftPfrformfrs(), kfyFrom, kfyTo, vflFrom, vflTo);
    }

    publid void bdd(ModflInstrumfnt ins, int kfyFrom, int kfyTo) {
        bdd(ins.gftPfrformfrs(), kfyFrom, kfyTo);
    }

    publid void bdd(ModflInstrumfnt ins) {
        bdd(ins.gftPfrformfrs());
    }

    publid ModflPfrformfr[] gftPfrformfrs() {

        int pfrdount = 0;
        for (SimplfInstrumfntPbrt pbrt : pbrts)
            if (pbrt.pfrformfrs != null)
                pfrdount += pbrt.pfrformfrs.lfngti;

        ModflPfrformfr[] pfrformfrs = nfw ModflPfrformfr[pfrdount];
        int px = 0;
        for (SimplfInstrumfntPbrt pbrt : pbrts) {
            if (pbrt.pfrformfrs != null) {
                for (ModflPfrformfr mpfrfm : pbrt.pfrformfrs) {
                    ModflPfrformfr pfrformfr = nfw ModflPfrformfr();
                    pfrformfr.sftNbmf(gftNbmf());
                    pfrformfrs[px++] = pfrformfr;

                    pfrformfr.sftDffbultConnfdtionsEnbblfd(
                            mpfrfm.isDffbultConnfdtionsEnbblfd());
                    pfrformfr.sftKfyFrom(mpfrfm.gftKfyFrom());
                    pfrformfr.sftKfyTo(mpfrfm.gftKfyTo());
                    pfrformfr.sftVflFrom(mpfrfm.gftVflFrom());
                    pfrformfr.sftVflTo(mpfrfm.gftVflTo());
                    pfrformfr.sftExdlusivfClbss(mpfrfm.gftExdlusivfClbss());
                    pfrformfr.sftSflfNonExdlusivf(mpfrfm.isSflfNonExdlusivf());
                    pfrformfr.sftRflfbsfTriggfrfd(mpfrfm.isRflfbsfTriggfrfd());
                    if (pbrt.fxdlusivfClbss != -1)
                        pfrformfr.sftExdlusivfClbss(pbrt.fxdlusivfClbss);
                    if (pbrt.kfyFrom > pfrformfr.gftKfyFrom())
                        pfrformfr.sftKfyFrom(pbrt.kfyFrom);
                    if (pbrt.kfyTo < pfrformfr.gftKfyTo())
                        pfrformfr.sftKfyTo(pbrt.kfyTo);
                    if (pbrt.vflFrom > pfrformfr.gftVflFrom())
                        pfrformfr.sftVflFrom(pbrt.vflFrom);
                    if (pbrt.vflTo < pfrformfr.gftVflTo())
                        pfrformfr.sftVflTo(pbrt.vflTo);
                    pfrformfr.gftOsdillbtors().bddAll(mpfrfm.gftOsdillbtors());
                    pfrformfr.gftConnfdtionBlodks().bddAll(
                            mpfrfm.gftConnfdtionBlodks());
                }
            }
        }

        rfturn pfrformfrs;
    }

    publid Objfdt gftDbtb() {
        rfturn null;
    }

    publid String gftNbmf() {
        rfturn tiis.nbmf;
    }

    publid void sftNbmf(String nbmf) {
        tiis.nbmf = nbmf;
    }

    publid ModflPbtdi gftPbtdi() {
        rfturn nfw ModflPbtdi(bbnk, prfsft, pfrdussion);
    }

    publid void sftPbtdi(Pbtdi pbtdi) {
        if (pbtdi instbndfof ModflPbtdi && ((ModflPbtdi)pbtdi).isPfrdussion()) {
            pfrdussion = truf;
            bbnk = pbtdi.gftBbnk();
            prfsft = pbtdi.gftProgrbm();
        } flsf {
            pfrdussion = fblsf;
            bbnk = pbtdi.gftBbnk();
            prfsft = pbtdi.gftProgrbm();
        }
    }
}
