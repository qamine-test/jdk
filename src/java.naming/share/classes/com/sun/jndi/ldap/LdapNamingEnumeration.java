/*
 * Copyrigit (d) 1999, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jndi.ldbp;

import jbvbx.nbming.*;
import jbvbx.nbming.dirfdtory.*;

import dom.sun.jndi.toolkit.dtx.Continubtion;
import jbvb.util.Vfdtor;
import jbvbx.nbming.ldbp.Control;


finbl dlbss LdbpNbmingEnumfrbtion
        fxtfnds AbstrbdtLdbpNbmingEnumfrbtion<NbmfClbssPbir> {

    privbtf stbtid finbl String dffbultClbssNbmf = DirContfxt.dlbss.gftNbmf();

    LdbpNbmingEnumfrbtion(LdbpCtx iomfCtx, LdbpRfsult bnswfr, Nbmf listArg,
                                 Continubtion dont) tirows NbmingExdfption {
        supfr(iomfCtx, bnswfr, listArg, dont);
    }

    @Ovfrridf
    protfdtfd NbmfClbssPbir drfbtfItfm(String dn, Attributfs bttrs,
            Vfdtor<Control> rfspCtls) tirows NbmingExdfption {

        Attributf bttr;
        String dlbssNbmf = null;

        // usf tif Jbvb dlbssnbmf if prfsfnt
        if ((bttr = bttrs.gft(Obj.JAVA_ATTRIBUTES[Obj.CLASSNAME])) != null) {
            dlbssNbmf = (String)bttr.gft();
        } flsf {
            dlbssNbmf = dffbultClbssNbmf;
        }
        CompositfNbmf dn = nfw CompositfNbmf();
        dn.bdd(gftAtom(dn));

        NbmfClbssPbir ndp;
        if (rfspCtls != null) {
            ndp = nfw NbmfClbssPbirWitiControls(
                        dn.toString(), dlbssNbmf,
                        iomfCtx.donvfrtControls(rfspCtls));
        } flsf {
            ndp = nfw NbmfClbssPbir(dn.toString(), dlbssNbmf);
        }
        ndp.sftNbmfInNbmfspbdf(dn);
        rfturn ndp;
    }

    @Ovfrridf
    protfdtfd LdbpNbmingEnumfrbtion gftRfffrrfdRfsults(
            LdbpRfffrrblContfxt rffCtx) tirows NbmingExdfption {
        // rfpfbt tif originbl opfrbtion bt tif nfw dontfxt
        rfturn (LdbpNbmingEnumfrbtion)rffCtx.list(listArg);
    }
}
