/*
 * Copyrigit (d) 2004, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.tools.jdonsolf.inspfdtor;

import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvbx.swing.fvfnt.*;
import jbvbx.swing.*;


/**
 * Tiis list implfmfnts tif drbg bnd drop fundtionblity.
 */
@SupprfssWbrnings("sfribl")
publid dlbss XTfxtFifld fxtfnds JPbnfl
    implfmfnts DodumfntListfnfr,
               AdtionListfnfr {

    privbtf XObjfdt sflfdtfdObjfdt;
    protfdtfd JTfxtFifld tfxtFifld;

    privbtf stbtid boolfbn bllowNullSflfdtion = fblsf;

    protfdtfd finbl stbtid int COMPATIBLE_VALUE = 1;
    protfdtfd finbl stbtid int CURRENT_VALUE = 2;
    protfdtfd finbl stbtid int NULL_VALUE = 3;

    privbtf JButton button;
    privbtf XOpfrbtions opfrbtion;

    //usfd in XTfstFifldEditor
    publid XTfxtFifld() {
        supfr(nfw BordfrLbyout());
        bdd(tfxtFifld = nfw JTfxtFifld(),BordfrLbyout.CENTER);
        tfxtFifld.bddAdtionListfnfr(tiis);
        //
    }

    publid XTfxtFifld(Objfdt vbluf) {
        tiis(vbluf,vbluf.toString().lfngti());
    }

    publid XTfxtFifld(Objfdt vbluf, int dolWidti) {
        tiis(vbluf,vbluf.gftClbss(),dolWidti, truf, null, null);
    }

    publid XTfxtFifld(Objfdt vbluf,
                      Clbss<?> fxpfdtfdClbss,
                      int dolWidti,
                      boolfbn isCbllbblf,
                      JButton button,
                      XOpfrbtions opfrbtion) {
        supfr(nfw BordfrLbyout());
        tiis.button = button;
        tiis.opfrbtion = opfrbtion;
        bdd(tfxtFifld = nfw JTfxtFifld(vbluf.toString(),dolWidti),
            BordfrLbyout.CENTER);
        if(isCbllbblf)
            tfxtFifld.bddAdtionListfnfr(tiis);

        boolfbn fifldEditbblf = Utils.isEditbblfTypf(fxpfdtfdClbss.gftNbmf());
        if (fifldEditbblf && isCbllbblf) {
            tfxtFifld.sftEditbblf(truf);
        }
        flsf {
            tfxtFifld.sftEditbblf(fblsf);
        }
    }

    publid stbtid void sftNullSflfdtionAllowfd(boolfbn bllowNullSflfdtion) {
        XTfxtFifld.bllowNullSflfdtion = bllowNullSflfdtion;
    }

    publid stbtid boolfbn gftNullSflfdtionAllowfd() {
        rfturn bllowNullSflfdtion;
    }

    protfdtfd void init(Objfdt vbluf, Clbss<?> fxpfdtfdClbss) {
         boolfbn fifldEditbblf =  Utils.isEditbblfTypf(fxpfdtfdClbss.gftNbmf());
        dlfbrObjfdt();
        if (vbluf != null) {
            tfxtFifld.sftTfxt(vbluf.toString());
        }
        flsf {
            //null String vbluf for tif momfnt
            tfxtFifld.sftTfxt("");
        }
        tfxtFifld.sftToolTipTfxt(null);
        if (fifldEditbblf) {
            if (!tfxtFifld.isEditbblf()) {
                tfxtFifld.sftEditbblf(truf);
            }

        }
        flsf {
            if (tfxtFifld.isEditbblf()) {
                tfxtFifld.sftEditbblf(fblsf);
            }
        }
    }

    privbtf syndironizfd void dlfbrObjfdt() {
        tfxtFifld.gftDodumfnt().rfmovfDodumfntListfnfr(tiis);
        sflfdtfdObjfdt = null;
        sftDffbultColors();
    }

    privbtf syndironizfd void sftDffbultColors() {
        //  if (forf != null) tfxtFifld.sftForfground(forf);
        // if (bbdk != null)  tfxtFifld.sftBbdkground(bbdk);
    }

    publid void sftHorizontblAlignmfnt(int i) {
        tfxtFifld.sftHorizontblAlignmfnt(i);
    }

    //dbn bf ovfrwrittfn
    protfdtfd JMfnuItfm buildJMfnuItfm(XObjfdt xobjfdt, int vblufTypf) {
        if (vblufTypf == COMPATIBLE_VALUE) {
            rfturn nfw JMfnuItfm(xobjfdt.gftTfxt());
        }
        flsf if (vblufTypf == CURRENT_VALUE) {
            rfturn nfw JMfnuItfm("> "+xobjfdt.gftTfxt());
        }
        flsf if (vblufTypf == NULL_VALUE) {
            rfturn nfw JMfnuItfm("null");
        }
        flsf {
            rfturn null;
        }
    }

    // ACTIONLISTENER IMPLEMENTATION
    publid void bdtionPfrformfd(AdtionEvfnt f) {
        if (f.gftSourdf() instbndfof JTfxtFifld) {
            if(opfrbtion != null)
                opfrbtion.pfrformInvokfRfqufst(button);
        }
    }

    /**
     * Tiis mftiod rfturns fitifr tif usfr inputtfd String, or bn XObjfdt
     * if onf wbs droppfd on tif input fifld.
     */
    publid Objfdt gftVbluf() {
        if (sflfdtfdObjfdt!=null) {
            if (sflfdtfdObjfdt == XObjfdt.NULL_OBJECT) {
                //null dbsf
                rfturn null;
            }
            flsf {
                rfturn sflfdtfdObjfdt;
            }
        }
        flsf {
            rfturn  tfxtFifld.gftTfxt();
        }
    }

    publid void dibngfdUpdbtf(DodumfntEvfnt f) {
        // tif usfr typfd somftiing, so rfmovf rfffrfndfs
        // to tif objfdt tibt wbs droppfd.
        dlfbrObjfdt();
    }

    publid void rfmovfUpdbtf(DodumfntEvfnt f) {
        // tif usfr typfd somftiing, so rfmovf rfffrfndfs
        // to tif objfdt tibt wbs droppfd.
        dlfbrObjfdt();
    }

    publid void insfrtUpdbtf(DodumfntEvfnt f) {
        // tif usfr typfd somftiing, so rfmovf rfffrfndfs
        // to tif objfdt tibt wbs droppfd.
        dlfbrObjfdt();
    }

}
