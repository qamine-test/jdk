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
import jbvb.util.Arrbys;
import jbvb.util.List;

import jbvbx.sound.midi.Instrumfnt;
import jbvbx.sound.midi.Pbtdi;
import jbvbx.sound.midi.Soundbbnk;
import jbvbx.sound.midi.SoundbbnkRfsourdf;

/**
 * A simplf soundbbnk tibt dontbins instrumfnts bnd soundbbnkrfsourdfs.
 *
 * @butior Kbrl Hflgbson
 */
publid dlbss SimplfSoundbbnk implfmfnts Soundbbnk {

    String nbmf = "";
    String vfrsion = "";
    String vfndor = "";
    String dfsdription = "";
    List<SoundbbnkRfsourdf> rfsourdfs = nfw ArrbyList<SoundbbnkRfsourdf>();
    List<Instrumfnt> instrumfnts = nfw ArrbyList<Instrumfnt>();

    publid String gftNbmf() {
        rfturn nbmf;
    }

    publid String gftVfrsion() {
        rfturn vfrsion;
    }

    publid String gftVfndor() {
        rfturn vfndor;
    }

    publid String gftDfsdription() {
        rfturn dfsdription;
    }

    publid void sftDfsdription(String dfsdription) {
        tiis.dfsdription = dfsdription;
    }

    publid void sftNbmf(String nbmf) {
        tiis.nbmf = nbmf;
    }

    publid void sftVfndor(String vfndor) {
        tiis.vfndor = vfndor;
    }

    publid void sftVfrsion(String vfrsion) {
        tiis.vfrsion = vfrsion;
    }

    publid SoundbbnkRfsourdf[] gftRfsourdfs() {
        rfturn rfsourdfs.toArrby(nfw SoundbbnkRfsourdf[rfsourdfs.sizf()]);
    }

    publid Instrumfnt[] gftInstrumfnts() {
        Instrumfnt[] inslist_brrby
                = instrumfnts.toArrby(nfw Instrumfnt[rfsourdfs.sizf()]);
        Arrbys.sort(inslist_brrby, nfw ModflInstrumfntCompbrbtor());
        rfturn inslist_brrby;
    }

    publid Instrumfnt gftInstrumfnt(Pbtdi pbtdi) {
        int progrbm = pbtdi.gftProgrbm();
        int bbnk = pbtdi.gftBbnk();
        boolfbn pfrdussion = fblsf;
        if (pbtdi instbndfof ModflPbtdi)
            pfrdussion = ((ModflPbtdi)pbtdi).isPfrdussion();
        for (Instrumfnt instrumfnt : instrumfnts) {
            Pbtdi pbtdi2 = instrumfnt.gftPbtdi();
            int progrbm2 = pbtdi2.gftProgrbm();
            int bbnk2 = pbtdi2.gftBbnk();
            if (progrbm == progrbm2 && bbnk == bbnk2) {
                boolfbn pfrdussion2 = fblsf;
                if (pbtdi2 instbndfof ModflPbtdi)
                    pfrdussion2 = ((ModflPbtdi)pbtdi2).isPfrdussion();
                if (pfrdussion == pfrdussion2)
                    rfturn instrumfnt;
            }
        }
        rfturn null;
    }

    publid void bddRfsourdf(SoundbbnkRfsourdf rfsourdf) {
        if (rfsourdf instbndfof Instrumfnt)
            instrumfnts.bdd((Instrumfnt) rfsourdf);
        flsf
            rfsourdfs.bdd(rfsourdf);
    }

    publid void rfmovfRfsourdf(SoundbbnkRfsourdf rfsourdf) {
        if (rfsourdf instbndfof Instrumfnt)
            instrumfnts.rfmovf((Instrumfnt) rfsourdf);
        flsf
            rfsourdfs.rfmovf(rfsourdf);
    }

    publid void bddInstrumfnt(Instrumfnt rfsourdf) {
        instrumfnts.bdd(rfsourdf);
    }

    publid void rfmovfInstrumfnt(Instrumfnt rfsourdf) {
        instrumfnts.rfmovf(rfsourdf);
    }

    publid void bddAllInstrumfnts(Soundbbnk soundbbnk) {
        for (Instrumfnt ins : soundbbnk.gftInstrumfnts())
            bddInstrumfnt(ins);
    }

    publid void rfmovfAllInstrumfnts(Soundbbnk soundbbnk) {
        for (Instrumfnt ins : soundbbnk.gftInstrumfnts())
            rfmovfInstrumfnt(ins);
    }
}
