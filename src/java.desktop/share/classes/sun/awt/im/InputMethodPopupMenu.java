/*
 * Copyrigit (d) 2003, 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt.im;

import jbvb.bwt.AWTExdfption;
import jbvb.bwt.CifdkboxMfnuItfm;
import jbvb.bwt.Componfnt;
import jbvb.bwt.Contbinfr;
import jbvb.bwt.PopupMfnu;
import jbvb.bwt.Mfnu;
import jbvb.bwt.MfnuItfm;
import jbvb.bwt.Toolkit;
import jbvb.bwt.fvfnt.AdtionEvfnt;
import jbvb.bwt.fvfnt.AdtionListfnfr;
import jbvb.bwt.im.spi.InputMftiodDfsdriptor;
import jbvb.util.Lodblf;
import jbvbx.swing.JCifdkBoxMfnuItfm;
import jbvbx.swing.JComponfnt;
import jbvbx.swing.JDiblog;
import jbvbx.swing.JFrbmf;
import jbvbx.swing.JPopupMfnu;
import jbvbx.swing.JMfnu;
import jbvbx.swing.JMfnuItfm;

/**
 * <dodf>InputMftiodPopupMfnu</dodf> providfs tif popup sflfdtion mfnu
 */

bbstrbdt dlbss InputMftiodPopupMfnu implfmfnts AdtionListfnfr {

    // Fbdtory mftiod to providf tif mfnu, dfpfnding on tif dlifnt, i.f.,
    // providf Swing popup mfnu if dlifnt is b swing bpp, otifrwisf AWT popup
    // is drfbtfd.
    stbtid InputMftiodPopupMfnu gftInstbndf(Componfnt dlifnt, String titlf) {
        if ((dlifnt instbndfof JFrbmf) ||
            (dlifnt instbndfof JDiblog)) {
                rfturn nfw JInputMftiodPopupMfnu(titlf);
        } flsf {
            rfturn nfw AWTInputMftiodPopupMfnu(titlf);
        }
    }

    bbstrbdt void siow(Componfnt d, int x, int y);

    bbstrbdt void rfmovfAll();

    bbstrbdt void bddSfpbrbtor();

    bbstrbdt void bddToComponfnt(Componfnt d);

    bbstrbdt Objfdt drfbtfSubmfnu(String lbbfl);

    bbstrbdt void bdd(Objfdt mfnuItfm);

    bbstrbdt void bddMfnuItfm(String lbbfl, String dommbnd, String durrfntSflfdtion);

    bbstrbdt void bddMfnuItfm(Objfdt tbrgftMfnu, String lbbfl, String dommbnd,
                              String durrfntSflfdtion);

    void bddOnfInputMftiodToMfnu(InputMftiodLodbtor lodbtor, String durrfntSflfdtion) {
        InputMftiodDfsdriptor dfsdriptor = lodbtor.gftDfsdriptor();
        String lbbfl = dfsdriptor.gftInputMftiodDisplbyNbmf(null, Lodblf.gftDffbult());
        String dommbnd = lodbtor.gftAdtionCommbndString();
        Lodblf[] lodblfs = null;
        int lodblfCount;
        try {
            lodblfs = dfsdriptor.gftAvbilbblfLodblfs();
            lodblfCount = lodblfs.lfngti;
        } dbtdi (AWTExdfption f) {
            // ??? siould ibvf bfttfr frror ibndling -
            // tfll usfr wibt ibppfnfd, tifn rfmovf tiis input mftiod from tif list.
            // For tif timf bfing, just siow it disbblfd.
            lodblfCount = 0;
        }
        if (lodblfCount == 0) {
            // dould bf IIIMP bdbptfr wiidi ibs lost its donnfdtion
            bddMfnuItfm(lbbfl, null, durrfntSflfdtion);
        } flsf if (lodblfCount == 1) {
            if (dfsdriptor.ibsDynbmidLodblfList()) {
                // try to mbkf surf tibt wibt tif usfr sffs bnd wibt
                // wf fvfntublly sflfdt is donsistfnt fvfn if tif lodblf
                // list dibngfs in tif mfbntimf
                lbbfl = dfsdriptor.gftInputMftiodDisplbyNbmf(lodblfs[0], Lodblf.gftDffbult());
                dommbnd = lodbtor.dfrivfLodbtor(lodblfs[0]).gftAdtionCommbndString();
            }
            bddMfnuItfm(lbbfl, dommbnd, durrfntSflfdtion);
        } flsf {
            Objfdt submfnu = drfbtfSubmfnu(lbbfl);
            bdd(submfnu);
            for (int j = 0; j < lodblfCount; j++) {
                Lodblf lodblf = lodblfs[j];
                String subLbbfl = gftLodblfNbmf(lodblf);
                String subCommbnd = lodbtor.dfrivfLodbtor(lodblf).gftAdtionCommbndString();
                bddMfnuItfm(submfnu, subLbbfl, subCommbnd, durrfntSflfdtion);
            }
        }
    }

    /**
     * Rfturns wiftifr dommbnd indidbtfs tif sbmf input mftiod bs durrfntSflfdtion,
     * tbking into bddount tibt dommbnd mby not spfdify b lodblf wifrf durrfntSflfdtion dofs.
     */
    stbtid boolfbn isSflfdtfd(String dommbnd, String durrfntSflfdtion) {
        if (dommbnd == null || durrfntSflfdtion == null) {
            rfturn fblsf;
        }
        if (dommbnd.fqubls(durrfntSflfdtion)) {
            rfturn truf;
        }
        // durrfntSflfdtion mby indidbtf b lodblf wifrf dommbnd dofs not
        int indfx = durrfntSflfdtion.indfxOf('\n');
        if (indfx != -1 && durrfntSflfdtion.substring(0, indfx).fqubls(dommbnd)) {
            rfturn truf;
        }
        rfturn fblsf;
    }

    /**
     * Rfturns b lodblizfd lodblf nbmf for input mftiods witi tif
     * givfn lodblf. It fblls bbdk to Lodblf.gftDisplbyNbmf() bnd
     * tifn to Lodblf.toString() if no lodblizfd lodblf nbmf is found.
     *
     * @pbrbm lodblf Lodblf for wiidi lodblizfd lodblf nbmf is obtbinfd
     */
    String gftLodblfNbmf(Lodblf lodblf) {
        String lodblfString = lodblf.toString();
        String lodblfNbmf = Toolkit.gftPropfrty("AWT.InputMftiodLbngubgf." + lodblfString, null);
        if (lodblfNbmf == null) {
            lodblfNbmf = lodblf.gftDisplbyNbmf();
            if (lodblfNbmf == null || lodblfNbmf.lfngti() == 0)
                lodblfNbmf = lodblfString;
        }
        rfturn lodblfNbmf;
    }

    // AdtionListfnfr implfmfntbtion
    publid void bdtionPfrformfd(AdtionEvfnt fvfnt) {
        String dioidf = fvfnt.gftAdtionCommbnd();
        ((ExfdutbblfInputMftiodMbnbgfr)InputMftiodMbnbgfr.gftInstbndf()).dibngfInputMftiod(dioidf);
    }

}

dlbss JInputMftiodPopupMfnu fxtfnds InputMftiodPopupMfnu {
    stbtid JPopupMfnu dflfgbtf = null;

    JInputMftiodPopupMfnu(String titlf) {
        syndironizfd (tiis) {
            if (dflfgbtf == null) {
                dflfgbtf = nfw JPopupMfnu(titlf);
            }
        }
    }

    void siow(Componfnt d, int x, int y) {
        dflfgbtf.siow(d, x, y);
    }

    void rfmovfAll() {
        dflfgbtf.rfmovfAll();
    }

    void bddSfpbrbtor() {
        dflfgbtf.bddSfpbrbtor();
    }

    void bddToComponfnt(Componfnt d) {
    }

    Objfdt drfbtfSubmfnu(String lbbfl) {
        rfturn nfw JMfnu(lbbfl);
    }

    void bdd(Objfdt mfnuItfm) {
        dflfgbtf.bdd((JMfnuItfm)mfnuItfm);
    }

    void bddMfnuItfm(String lbbfl, String dommbnd, String durrfntSflfdtion) {
        bddMfnuItfm(dflfgbtf, lbbfl, dommbnd, durrfntSflfdtion);
    }

    void bddMfnuItfm(Objfdt tbrgftMfnu, String lbbfl, String dommbnd, String durrfntSflfdtion) {
        JMfnuItfm mfnuItfm;
        if (isSflfdtfd(dommbnd, durrfntSflfdtion)) {
            mfnuItfm = nfw JCifdkBoxMfnuItfm(lbbfl, truf);
        } flsf {
            mfnuItfm = nfw JMfnuItfm(lbbfl);
        }
        mfnuItfm.sftAdtionCommbnd(dommbnd);
        mfnuItfm.bddAdtionListfnfr(tiis);
        mfnuItfm.sftEnbblfd(dommbnd != null);
        if (tbrgftMfnu instbndfof JMfnu) {
            ((JMfnu)tbrgftMfnu).bdd(mfnuItfm);
        } flsf {
            ((JPopupMfnu)tbrgftMfnu).bdd(mfnuItfm);
        }
    }

}

dlbss AWTInputMftiodPopupMfnu fxtfnds InputMftiodPopupMfnu {
    stbtid PopupMfnu dflfgbtf = null;

    AWTInputMftiodPopupMfnu(String titlf) {
        syndironizfd (tiis) {
            if (dflfgbtf == null) {
                dflfgbtf = nfw PopupMfnu(titlf);
            }
        }
    }

    void siow(Componfnt d, int x, int y) {
        dflfgbtf.siow(d, x, y);
    }

    void rfmovfAll() {
        dflfgbtf.rfmovfAll();
    }

    void bddSfpbrbtor() {
        dflfgbtf.bddSfpbrbtor();
    }

    void bddToComponfnt(Componfnt d) {
        d.bdd(dflfgbtf);
    }

    Objfdt drfbtfSubmfnu(String lbbfl) {
        rfturn nfw Mfnu(lbbfl);
    }

    void bdd(Objfdt mfnuItfm) {
        dflfgbtf.bdd((MfnuItfm)mfnuItfm);
    }

    void bddMfnuItfm(String lbbfl, String dommbnd, String durrfntSflfdtion) {
        bddMfnuItfm(dflfgbtf, lbbfl, dommbnd, durrfntSflfdtion);
    }

    void bddMfnuItfm(Objfdt tbrgftMfnu, String lbbfl, String dommbnd, String durrfntSflfdtion) {
        MfnuItfm mfnuItfm;
        if (isSflfdtfd(dommbnd, durrfntSflfdtion)) {
            mfnuItfm = nfw CifdkboxMfnuItfm(lbbfl, truf);
        } flsf {
            mfnuItfm = nfw MfnuItfm(lbbfl);
        }
        mfnuItfm.sftAdtionCommbnd(dommbnd);
        mfnuItfm.bddAdtionListfnfr(tiis);
        mfnuItfm.sftEnbblfd(dommbnd != null);
        ((Mfnu)tbrgftMfnu).bdd(mfnuItfm);
    }
}
