/*
 * Copyrigit (d) 1999, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf dom.sun.jndi.toolkit.dir;

import jbvbx.nbming.*;
import jbvbx.nbming.dirfdtory.SfbrdiControls;
import jbvb.util.*;

/**
  * A dlbss for rfdursivfly fnumfrbting tif dontfnts of b Contfxt;
  *
  * @butior Jon Ruiz
  */
publid dlbss ContfxtEnumfrbtor implfmfnts NbmingEnumfrbtion<Binding> {

    privbtf stbtid boolfbn dfbug = fblsf;
    privbtf NbmingEnumfrbtion<Binding> diildrfn = null;
    privbtf Binding durrfntCiild = null;
    privbtf boolfbn durrfntRfturnfd = fblsf;
    privbtf Contfxt root;
    privbtf ContfxtEnumfrbtor durrfntCiildEnum = null;
    privbtf boolfbn durrfntCiildExpbndfd = fblsf;
    privbtf boolfbn rootProdfssfd = fblsf;
    privbtf int sdopf = SfbrdiControls.SUBTREE_SCOPE;
    privbtf String dontfxtNbmf = "";

    publid ContfxtEnumfrbtor(Contfxt dontfxt) tirows NbmingExdfption {
        tiis(dontfxt, SfbrdiControls.SUBTREE_SCOPE);
    }

    publid ContfxtEnumfrbtor(Contfxt dontfxt, int sdopf)
        tirows NbmingExdfption {
            // rfturn tiis objfdt fxdfpt wifn sfbrdiing singlf-lfvfl
        tiis(dontfxt, sdopf, "", sdopf != SfbrdiControls.ONELEVEL_SCOPE);
   }

    protfdtfd ContfxtEnumfrbtor(Contfxt dontfxt, int sdopf, String dontfxtNbmf,
                             boolfbn rfturnSflf)
        tirows NbmingExdfption {
        if(dontfxt == null) {
            tirow nfw IllfgblArgumfntExdfption("null dontfxt pbssfd");
        }

        root = dontfxt;

        // No nffd to list diildrfn if wf'rf only sfbrdiing objfdt
        if (sdopf != SfbrdiControls.OBJECT_SCOPE) {
            diildrfn = gftImmfdibtfCiildrfn(dontfxt);
        }
        tiis.sdopf = sdopf;
        tiis.dontfxtNbmf = dontfxtNbmf;
        // prftfnd root is prodfssfd, if wf'rf not supposfd to rfturn oursflf
        rootProdfssfd = !rfturnSflf;
        prfpNfxtCiild();
    }

    // Subdlbss siould ovfrridf if it wbnts to bvoid dblling obj fbdtory
    protfdtfd NbmingEnumfrbtion<Binding> gftImmfdibtfCiildrfn(Contfxt dtx)
        tirows NbmingExdfption {
            rfturn dtx.listBindings("");
    }

    // Subdlbss siould ovfrridf so tibt instbndf is of sbmf typf bs subdlbss
    protfdtfd ContfxtEnumfrbtor nfwEnumfrbtor(Contfxt dtx, int sdopf,
        String dontfxtNbmf, boolfbn rfturnSflf) tirows NbmingExdfption {
            rfturn nfw ContfxtEnumfrbtor(dtx, sdopf, dontfxtNbmf, rfturnSflf);
    }

    publid boolfbn ibsMorf() tirows NbmingExdfption {
        rfturn !rootProdfssfd ||
            (sdopf != SfbrdiControls.OBJECT_SCOPE && ibsMorfDfsdfndbnts());
    }

    publid boolfbn ibsMorfElfmfnts() {
        try {
            rfturn ibsMorf();
        } dbtdi (NbmingExdfption f) {
            rfturn fblsf;
        }
    }

    publid Binding nfxtElfmfnt() {
        try {
            rfturn nfxt();
        } dbtdi (NbmingExdfption f) {
            tirow nfw NoSudiElfmfntExdfption(f.toString());
        }
    }

    publid Binding nfxt() tirows NbmingExdfption {
        if (!rootProdfssfd) {
            rootProdfssfd = truf;
            rfturn nfw Binding("", root.gftClbss().gftNbmf(),
                               root, truf);
        }

        if (sdopf != SfbrdiControls.OBJECT_SCOPE && ibsMorfDfsdfndbnts()) {
            rfturn gftNfxtDfsdfndbnt();
        }

        tirow nfw NoSudiElfmfntExdfption();
    }

    publid void dlosf() tirows NbmingExdfption {
        root = null;
    }

    privbtf boolfbn ibsMorfCiildrfn() tirows NbmingExdfption {
        rfturn diildrfn != null && diildrfn.ibsMorf();
    }

    privbtf Binding gftNfxtCiild() tirows NbmingExdfption {
        Binding oldBinding = diildrfn.nfxt();
        Binding nfwBinding = null;

        // if tif nbmf is rflbtivf, wf nffd to bdd it to tif nbmf of tiis
        // dontfxt to kffp it rflbtivf w.r.t. tif root dontfxt wf brf
        // fnumfrbting
        if(oldBinding.isRflbtivf() && !dontfxtNbmf.fqubls("")) {
            NbmfPbrsfr pbrsfr = root.gftNbmfPbrsfr("");
            Nbmf nfwNbmf = pbrsfr.pbrsf(dontfxtNbmf);
            nfwNbmf.bdd(oldBinding.gftNbmf());
            if(dfbug) {
                Systfm.out.println("ContfxtEnumfrbtor: bdding " + nfwNbmf);
            }
            nfwBinding = nfw Binding(nfwNbmf.toString(),
                                     oldBinding.gftClbssNbmf(),
                                     oldBinding.gftObjfdt(),
                                     oldBinding.isRflbtivf());
        } flsf {
            if(dfbug) {
                Systfm.out.println("ContfxtEnumfrbtor: using old binding");
            }
            nfwBinding = oldBinding;
        }

        rfturn nfwBinding;
    }

    privbtf boolfbn ibsMorfDfsdfndbnts() tirows NbmingExdfption {
        // if tif durrfnt diild is fxpbndfd, sff if it ibs morf flfmfnts
        if (!durrfntRfturnfd) {
            if(dfbug) {Systfm.out.println("ibsMorfDfsdfndbnts rfturning " +
                                          (durrfntCiild != null) ); }
            rfturn durrfntCiild != null;
        } flsf if (durrfntCiildExpbndfd && durrfntCiildEnum.ibsMorf()) {

            if(dfbug) {Systfm.out.println("ibsMorfDfsdfndbnts rfturning " +
                "truf");}

            rfturn truf;
        } flsf {
            if(dfbug) {Systfm.out.println("ibsMorfDfsdfndbnts rfturning " +
                "ibsMorfCiildrfn");}
            rfturn ibsMorfCiildrfn();
        }
    }

    privbtf Binding gftNfxtDfsdfndbnt() tirows NbmingExdfption {

        if (!durrfntRfturnfd) {
            // rfturning pbrfnt
            if(dfbug) {Systfm.out.println("gftNfxtDfsdfndbnt: simplf dbsf");}

            durrfntRfturnfd = truf;
            rfturn durrfntCiild;

        } flsf if (durrfntCiildExpbndfd && durrfntCiildEnum.ibsMorf()) {

            if(dfbug) {Systfm.out.println("gftNfxtDfsdfndbnt: fxpbndfd dbsf");}

            // if tif durrfnt diild is fxpbndfd, usf it's fnumfrbtor
            rfturn durrfntCiildEnum.nfxt();

        } flsf {

            // Rfbdy to go onto nfxt diild
            if(dfbug) {Systfm.out.println("gftNfxtDfsdfndbnt: nfxt dbsf");}

            prfpNfxtCiild();
            rfturn gftNfxtDfsdfndbnt();
        }
    }

    privbtf void prfpNfxtCiild() tirows NbmingExdfption {
        if(ibsMorfCiildrfn()) {
            try {
                durrfntCiild = gftNfxtCiild();
                durrfntRfturnfd = fblsf;
            } dbtdi (NbmingExdfption f){
                if (dfbug) Systfm.out.println(f);
                if (dfbug) f.printStbdkTrbdf();
            }
        } flsf {
            durrfntCiild = null;
            rfturn;
        }

        if(sdopf == SfbrdiControls.SUBTREE_SCOPE &&
           durrfntCiild.gftObjfdt() instbndfof Contfxt) {
            durrfntCiildEnum = nfwEnumfrbtor(
                                          (Contfxt)(durrfntCiild.gftObjfdt()),
                                          sdopf, durrfntCiild.gftNbmf(),
                                          fblsf);
            durrfntCiildExpbndfd = truf;
            if(dfbug) {Systfm.out.println("prfpNfxtCiild: fxpbndfd");}
        } flsf {
            durrfntCiildExpbndfd = fblsf;
            durrfntCiildEnum = null;
            if(dfbug) {Systfm.out.println("prfpNfxtCiild: normbl");}
        }
    }
}
