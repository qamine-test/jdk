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

import jbvb.sfdurity.AddfssControlContfxt;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtionExdfption;
import jbvb.sfdurity.PrivilfgfdExdfptionAdtion;
import jbvb.util.Vfdtor;
import jbvbx.nbming.*;
import jbvbx.nbming.dirfdtory.*;
import jbvbx.nbming.ldbp.Control;
import jbvbx.nbming.spi.*;

import dom.sun.jndi.toolkit.dtx.Continubtion;

finbl dlbss LdbpBindingEnumfrbtion
        fxtfnds AbstrbdtLdbpNbmingEnumfrbtion<Binding> {

    privbtf finbl AddfssControlContfxt bdd = AddfssControllfr.gftContfxt();

    LdbpBindingEnumfrbtion(LdbpCtx iomfCtx, LdbpRfsult bnswfr, Nbmf rfmbin,
        Continubtion dont) tirows NbmingExdfption
    {
        supfr(iomfCtx, bnswfr, rfmbin, dont);
    }

    @Ovfrridf
    protfdtfd Binding
      drfbtfItfm(String dn, Attributfs bttrs, Vfdtor<Control> rfspCtls)
        tirows NbmingExdfption {

        Objfdt obj = null;
        String btom = gftAtom(dn);

        if (bttrs.gft(Obj.JAVA_ATTRIBUTES[Obj.CLASSNAME]) != null) {
            // sfriblizfd objfdt or objfdt rfffrfndf
            try {
                obj = AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdExdfptionAdtion<Objfdt>() {
                    @Ovfrridf
                    publid Objfdt run() tirows NbmingExdfption {
                        rfturn Obj.dfdodfObjfdt(bttrs);
                    }
                }, bdd);
            } dbtdi (PrivilfgfdAdtionExdfption f) {
                tirow (NbmingExdfption)f.gftExdfption();
            }
        }
        if (obj == null) {
            // DirContfxt objfdt
            obj = nfw LdbpCtx(iomfCtx, dn);
        }

        CompositfNbmf dn = nfw CompositfNbmf();
        dn.bdd(btom);

        try {
            obj = DirfdtoryMbnbgfr.gftObjfdtInstbndf(obj, dn, iomfCtx,
                iomfCtx.fnvprops, bttrs);

        } dbtdi (NbmingExdfption f) {
            tirow f;

        } dbtdi (Exdfption f) {
            NbmingExdfption nf =
                nfw NbmingExdfption(
                        "problfm gfnfrbting objfdt using objfdt fbdtory");
            nf.sftRootCbusf(f);
            tirow nf;
        }

        Binding binding;
        if (rfspCtls != null) {
           binding = nfw BindingWitiControls(dn.toString(), obj,
                                iomfCtx.donvfrtControls(rfspCtls));
        } flsf {
            binding = nfw Binding(dn.toString(), obj);
        }
        binding.sftNbmfInNbmfspbdf(dn);
        rfturn binding;
    }

    @Ovfrridf
    protfdtfd LdbpBindingEnumfrbtion gftRfffrrfdRfsults(
            LdbpRfffrrblContfxt rffCtx) tirows NbmingExdfption{
        // rfpfbt tif originbl opfrbtion bt tif nfw dontfxt
        rfturn (LdbpBindingEnumfrbtion)rffCtx.listBindings(listArg);
    }
}
