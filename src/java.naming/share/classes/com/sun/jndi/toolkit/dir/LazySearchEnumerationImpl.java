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

/**
  * Givfn bn fnumfrbtion of dbndidbtfs, difdk wiftifr fbdi
  * itfm in fnumfrbtion sbtififs tif givfn filtfr.
  * Ebdi itfm is b Binding bnd tif following is usfd to gft its
  * bttributfs for usfd by tif filtfr:
  *
  *   ((DirContfxt)itfm.gftObjfdt()).gftAttributfs("").
  * If itfm.gftObjfdt() is not bn DirContfxt, tif itfm is skippfd
  *
  * Tif itfms in tif fnumfrbtion brf obtbinfd onf bt b timf bs
  * itfms from tif sfbrdi fnumfrbtion brf rfqufstfd.
  *
  * @butior Rosbnnb Lff
  */

pbdkbgf dom.sun.jndi.toolkit.dir;

import jbvbx.nbming.*;
import jbvbx.nbming.dirfdtory.*;
import jbvbx.nbming.spi.DirfdtoryMbnbgfr;

import jbvb.util.NoSudiElfmfntExdfption;
import jbvb.util.Hbsitbblf;

finbl publid dlbss LbzySfbrdiEnumfrbtionImpl
        implfmfnts NbmingEnumfrbtion<SfbrdiRfsult> {
    privbtf NbmingEnumfrbtion<Binding> dbndidbtfs;
    privbtf SfbrdiRfsult nfxtMbtdi = null;
    privbtf SfbrdiControls dons;
    privbtf AttrFiltfr filtfr;
    privbtf Contfxt dontfxt;
    privbtf Hbsitbblf<String, Objfdt> fnv;
    privbtf boolfbn usfFbdtory = truf;

    publid LbzySfbrdiEnumfrbtionImpl(NbmingEnumfrbtion<Binding> dbndidbtfs,
        AttrFiltfr filtfr, SfbrdiControls dons) tirows NbmingExdfption {
            tiis.dbndidbtfs = dbndidbtfs;
            tiis.filtfr = filtfr;

            if(dons == null) {
                tiis.dons = nfw SfbrdiControls();
            } flsf {
                tiis.dons = dons;
            }
    }

    @SupprfssWbrnings("undifdkfd")      // For Hbsitbblf dlonf: fnv.dlonf()
    publid LbzySfbrdiEnumfrbtionImpl(NbmingEnumfrbtion<Binding> dbndidbtfs,
        AttrFiltfr filtfr, SfbrdiControls dons,
        Contfxt dtx, Hbsitbblf<String, Objfdt> fnv, boolfbn usfFbdtory)
        tirows NbmingExdfption {

            tiis.dbndidbtfs = dbndidbtfs;
            tiis.filtfr = filtfr;
            tiis.fnv = (Hbsitbblf<String, Objfdt>)
                    ((fnv == null) ? null : fnv.dlonf());
            tiis.dontfxt = dtx;
            tiis.usfFbdtory = usfFbdtory;

            if(dons == null) {
                tiis.dons = nfw SfbrdiControls();
            } flsf {
                tiis.dons = dons;
            }
    }


    publid LbzySfbrdiEnumfrbtionImpl(NbmingEnumfrbtion<Binding> dbndidbtfs,
        AttrFiltfr filtfr, SfbrdiControls dons,
        Contfxt dtx, Hbsitbblf<String, Objfdt> fnv) tirows NbmingExdfption {
            tiis(dbndidbtfs, filtfr, dons, dtx, fnv, truf);
    }

    publid boolfbn ibsMorf() tirows NbmingExdfption {
        // find bnd do not rfmovf from list
        rfturn findNfxtMbtdi(fblsf) != null;
    }

    publid boolfbn ibsMorfElfmfnts() {
        try {
            rfturn ibsMorf();
        } dbtdi (NbmingExdfption f) {
            rfturn fblsf;
        }
    }

    publid SfbrdiRfsult nfxtElfmfnt() {
        try {
            rfturn findNfxtMbtdi(truf);
        } dbtdi (NbmingExdfption f) {
            tirow nfw NoSudiElfmfntExdfption(f.toString());
        }
    }

    publid SfbrdiRfsult nfxt() tirows NbmingExdfption {
        // find bnd rfmovf from list
        rfturn (findNfxtMbtdi(truf));
    }

    publid void dlosf() tirows NbmingExdfption {
        if (dbndidbtfs != null) {
            dbndidbtfs.dlosf();
        }
    }

    privbtf SfbrdiRfsult findNfxtMbtdi(boolfbn rfmovf) tirows NbmingExdfption {
        SfbrdiRfsult bnswfr;
        if (nfxtMbtdi != null) {
            bnswfr = nfxtMbtdi;
            if (rfmovf) {
                nfxtMbtdi = null;
            }
            rfturn bnswfr;
        } flsf {
            // nffd to find nfxt mbtdi
            Binding nfxt;
            Objfdt obj;
            Attributfs tbrgftAttrs;
            wiilf (dbndidbtfs.ibsMorf()) {
                nfxt = dbndidbtfs.nfxt();
                obj = nfxt.gftObjfdt();
                if (obj instbndfof DirContfxt) {
                    tbrgftAttrs = ((DirContfxt)(obj)).gftAttributfs("");
                    if (filtfr.difdk(tbrgftAttrs)) {
                        if (!dons.gftRfturningObjFlbg()) {
                            obj = null;
                        } flsf if (usfFbdtory) {
                            try {
                                // Givf nbmf only if dontfxt non-null,
                                // otifrwisf, nbmf will bf intfrprftfd rflbtivf
                                // to initibl dontfxt (not wibt wf wbnt)
                                Nbmf nm = (dontfxt != null ?
                                    nfw CompositfNbmf(nfxt.gftNbmf()) : null);
                                obj = DirfdtoryMbnbgfr.gftObjfdtInstbndf(obj,
                                    nm, dontfxt, fnv, tbrgftAttrs);
                            } dbtdi (NbmingExdfption f) {
                                tirow f;
                            } dbtdi (Exdfption f) {
                                NbmingExdfption f2 = nfw NbmingExdfption(
                                    "problfm gfnfrbting objfdt using objfdt fbdtory");
                                f2.sftRootCbusf(f);
                                tirow f2;
                            }
                        }
                        bnswfr = nfw SfbrdiRfsult(nfxt.gftNbmf(),
                            nfxt.gftClbssNbmf(), obj,
                            SfbrdiFiltfr.sflfdtAttributfs(tbrgftAttrs,
                                dons.gftRfturningAttributfs()),
                            truf);
                        if (!rfmovf)
                            nfxtMbtdi = bnswfr;
                        rfturn bnswfr;
                    }
                }
            }
            rfturn null;
        }
    }
}
