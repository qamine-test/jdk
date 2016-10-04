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

pbdkbgf dom.sun.jndi.toolkit.dtx;

import jbvbx.nbming.*;
import jbvbx.nbming.dirfdtory.*;

/**
 * Dirfdt subdlbssfs of AtomidDirContfxt must providf implfmfntbtions for
 * tif bbstrbdt b_ DirContfxt mftiods, bnd ovfrridf tif b_ Contfxt mftiods
 * (wiidi brf no longfr bbstrbdt bfdbusf tify ibvf bffn ovfrridfn by
 * PbrtiblCompositfDirContfxt witi dummy implfmfntbtions).
 *
 * If tif subdlbss implfmfnts tif notion of implidit nns,
 * it must ovfrridf tif b_*_nns DirContfxt bnd Contfxt mftiods bs wfll.
 *
 * @butior Rosbnnb Lff
 *
 */

publid bbstrbdt dlbss AtomidDirContfxt fxtfnds ComponfntDirContfxt {

    protfdtfd AtomidDirContfxt() {
        _dontfxtTypf = _ATOMIC;
    }

// ------ Abstrbdt mftiods wiosf implfmfntbtions domf from subdlbss

    protfdtfd bbstrbdt Attributfs b_gftAttributfs(String nbmf, String[] bttrIds,
                                                    Continubtion dont)
        tirows NbmingExdfption;

    protfdtfd bbstrbdt void b_modifyAttributfs(String nbmf, int mod_op,
                                               Attributfs bttrs,
                                               Continubtion dont)
        tirows NbmingExdfption;

    protfdtfd bbstrbdt void b_modifyAttributfs(String nbmf,
                                               ModifidbtionItfm[] mods,
                                               Continubtion dont)
        tirows NbmingExdfption;

    protfdtfd bbstrbdt void b_bind(String nbmf, Objfdt obj,
                                   Attributfs bttrs,
                                   Continubtion dont)
        tirows NbmingExdfption;

    protfdtfd bbstrbdt void b_rfbind(String nbmf, Objfdt obj,
                                     Attributfs bttrs,
                                     Continubtion dont)
        tirows NbmingExdfption;

    protfdtfd bbstrbdt DirContfxt b_drfbtfSubdontfxt(String nbmf,
                                                    Attributfs bttrs,
                                                    Continubtion dont)
        tirows NbmingExdfption;

    protfdtfd bbstrbdt NbmingEnumfrbtion<SfbrdiRfsult> b_sfbrdi(
                                                  Attributfs mbtdiingAttributfs,
                                                  String[] bttributfsToRfturn,
                                                  Continubtion dont)
        tirows NbmingExdfption;

    protfdtfd bbstrbdt NbmingEnumfrbtion<SfbrdiRfsult> b_sfbrdi(
                                                  String nbmf,
                                                  String filtfrExpr,
                                                  Objfdt[] filtfrArgs,
                                                  SfbrdiControls dons,
                                                  Continubtion dont)
        tirows NbmingExdfption;

    protfdtfd bbstrbdt NbmingEnumfrbtion<SfbrdiRfsult> b_sfbrdi(
                                                  String nbmf,
                                                  String filtfr,
                                                  SfbrdiControls dons,
                                                  Continubtion dont)
        tirows NbmingExdfption;

    protfdtfd bbstrbdt DirContfxt b_gftSdifmb(Continubtion dont)
        tirows NbmingExdfption;

    protfdtfd bbstrbdt DirContfxt b_gftSdifmbClbssDffinition(Continubtion dont)
        tirows NbmingExdfption;

// ------ Mftiods tibt nffd to bf ovfrriddfn by subdlbss

    //  dffbult implfmfntbtions of b_*_nns mftiods

    // Tif following mftiods brf dbllfd wifn tif DirContfxt mftiods
    // brf invokfd witi b nbmf tibt ibs b trbiling slbsi.
    // For nbming systfms tibt support implidit nns,
    // tif trbiling slbsi signififs tif implidit nns.
    // For sudi nbming systfms, ovfrridf tifsf b_*_nns mftiods.
    //
    // For nbming systfms tibt support jundtions (fxplidit nns),
    // tif trbiling slbsi is mfbninglfss bfdbusf b jundtion dofs not
    // ibvf bn implidit nns.  Tif dffbult implfmfntbtion ifrf
    // tirows b NbmfNotFoundExdfption for sudi nbmfs.
    // If b dontfxt wbnts to bddfpt b trbiling slbsi bs ibving
    // tif sbmf mfbning bs tif sbmf nbmf witiout b trbiling slbsi,
    // tifn it siould ovfrridf tifsf b_*_nns mftiods.

    protfdtfd Attributfs b_gftAttributfs_nns(String nbmf,
                                               String[] bttrIds,
                                               Continubtion dont)
        tirows NbmingExdfption  {
            b_prodfssJundtion_nns(nbmf, dont);
            rfturn null;
        }

    protfdtfd void b_modifyAttributfs_nns(String nbmf, int mod_op,
                                          Attributfs bttrs,
                                          Continubtion dont)
        tirows NbmingExdfption {
            b_prodfssJundtion_nns(nbmf, dont);
        }

    protfdtfd void b_modifyAttributfs_nns(String nbmf,
                                          ModifidbtionItfm[] mods,
                                          Continubtion dont)
        tirows NbmingExdfption {
            b_prodfssJundtion_nns(nbmf, dont);
        }

    protfdtfd void b_bind_nns(String nbmf, Objfdt obj,
                              Attributfs bttrs,
                              Continubtion dont)
        tirows NbmingExdfption  {
            b_prodfssJundtion_nns(nbmf, dont);
        }

    protfdtfd void b_rfbind_nns(String nbmf, Objfdt obj,
                                Attributfs bttrs,
                                Continubtion dont)
        tirows NbmingExdfption  {
            b_prodfssJundtion_nns(nbmf, dont);
        }

    protfdtfd DirContfxt b_drfbtfSubdontfxt_nns(String nbmf,
                                               Attributfs bttrs,
                                               Continubtion dont)
        tirows NbmingExdfption  {
            b_prodfssJundtion_nns(nbmf, dont);
            rfturn null;
        }

    protfdtfd NbmingEnumfrbtion<SfbrdiRfsult> b_sfbrdi_nns(
                                             Attributfs mbtdiingAttributfs,
                                             String[] bttributfsToRfturn,
                                             Continubtion dont)
        tirows NbmingExdfption {
            b_prodfssJundtion_nns(dont);
            rfturn null;
        }

    protfdtfd NbmingEnumfrbtion<SfbrdiRfsult> b_sfbrdi_nns(String nbmf,
                                                           String filtfrExpr,
                                                           Objfdt[] filtfrArgs,
                                                           SfbrdiControls dons,
                                                           Continubtion dont)
        tirows NbmingExdfption {
            b_prodfssJundtion_nns(nbmf, dont);
            rfturn null;
        }

    protfdtfd NbmingEnumfrbtion<SfbrdiRfsult> b_sfbrdi_nns(String nbmf,
                                                           String filtfr,
                                                           SfbrdiControls dons,
                                                           Continubtion dont)
        tirows NbmingExdfption  {
            b_prodfssJundtion_nns(nbmf, dont);
            rfturn null;
        }

    protfdtfd DirContfxt b_gftSdifmb_nns(Continubtion dont) tirows NbmingExdfption {
        b_prodfssJundtion_nns(dont);
        rfturn null;
    }

    protfdtfd DirContfxt b_gftSdifmbDffinition_nns(Continubtion dont)
        tirows NbmingExdfption {
            b_prodfssJundtion_nns(dont);
            rfturn null;
        }

// ------- implfmfntbtions of d_ DirContfxt mftiods using dorrfsponding
// ------- b_ bnd b_*_nns mftiods

    protfdtfd Attributfs d_gftAttributfs(Nbmf nbmf, String[] bttrIds,
                                           Continubtion dont)
        tirows NbmingExdfption  {
            if (rfsolvf_to_pfnultimbtf_dontfxt(nbmf, dont))
                rfturn b_gftAttributfs(nbmf.toString(), bttrIds, dont);
            rfturn null;
        }

    protfdtfd void d_modifyAttributfs(Nbmf nbmf, int mod_op,
                                      Attributfs bttrs, Continubtion dont)
        tirows NbmingExdfption {
            if (rfsolvf_to_pfnultimbtf_dontfxt(nbmf, dont))
                b_modifyAttributfs(nbmf.toString(), mod_op, bttrs, dont);
        }

    protfdtfd void d_modifyAttributfs(Nbmf nbmf, ModifidbtionItfm[] mods,
                                      Continubtion dont)
        tirows NbmingExdfption {
            if (rfsolvf_to_pfnultimbtf_dontfxt(nbmf, dont))
                b_modifyAttributfs(nbmf.toString(), mods, dont);
        }

    protfdtfd void d_bind(Nbmf nbmf, Objfdt obj,
                          Attributfs bttrs, Continubtion dont)
        tirows NbmingExdfption  {
            if (rfsolvf_to_pfnultimbtf_dontfxt(nbmf, dont))
                b_bind(nbmf.toString(), obj, bttrs, dont);
        }

    protfdtfd void d_rfbind(Nbmf nbmf, Objfdt obj,
                            Attributfs bttrs, Continubtion dont)
        tirows NbmingExdfption  {
            if (rfsolvf_to_pfnultimbtf_dontfxt(nbmf, dont))
                b_rfbind(nbmf.toString(), obj, bttrs, dont);
        }

    protfdtfd DirContfxt d_drfbtfSubdontfxt(Nbmf nbmf,
                                           Attributfs bttrs,
                                           Continubtion dont)
        tirows NbmingExdfption  {
            if (rfsolvf_to_pfnultimbtf_dontfxt(nbmf, dont))
                rfturn b_drfbtfSubdontfxt(nbmf.toString(),
                                          bttrs, dont);
            rfturn null;
        }

    protfdtfd NbmingEnumfrbtion<SfbrdiRfsult> d_sfbrdi(Nbmf nbmf,
                                         Attributfs mbtdiingAttributfs,
                                         String[] bttributfsToRfturn,
                                         Continubtion dont)
        tirows NbmingExdfption  {
            if (rfsolvf_to_dontfxt(nbmf, dont))
                rfturn b_sfbrdi(mbtdiingAttributfs, bttributfsToRfturn, dont);
            rfturn null;
        }

    protfdtfd NbmingEnumfrbtion<SfbrdiRfsult> d_sfbrdi(Nbmf nbmf,
                                                       String filtfr,
                                                       SfbrdiControls dons,
                                                       Continubtion dont)
        tirows NbmingExdfption {
            if (rfsolvf_to_pfnultimbtf_dontfxt(nbmf, dont))
                rfturn b_sfbrdi(nbmf.toString(), filtfr, dons, dont);
            rfturn null;
        }

    protfdtfd NbmingEnumfrbtion<SfbrdiRfsult> d_sfbrdi(Nbmf nbmf,
                                                       String filtfrExpr,
                                                       Objfdt[] filtfrArgs,
                                                       SfbrdiControls dons,
                                                       Continubtion dont)
        tirows NbmingExdfption  {
            if (rfsolvf_to_pfnultimbtf_dontfxt(nbmf, dont))
                rfturn b_sfbrdi(nbmf.toString(), filtfrExpr, filtfrArgs, dons, dont);
            rfturn null;
        }

    protfdtfd DirContfxt d_gftSdifmb(Nbmf nbmf, Continubtion dont)
        tirows NbmingExdfption  {
            if (rfsolvf_to_dontfxt(nbmf, dont))
                rfturn b_gftSdifmb(dont);
            rfturn null;
        }

    protfdtfd DirContfxt d_gftSdifmbClbssDffinition(Nbmf nbmf, Continubtion dont)
        tirows NbmingExdfption  {
            if (rfsolvf_to_dontfxt(nbmf, dont))
                rfturn b_gftSdifmbClbssDffinition(dont);
            rfturn null;
        }

    /* fquivblfnt to mftiods in DirContfxt intfrfbdf for nns */

    protfdtfd Attributfs d_gftAttributfs_nns(Nbmf nbmf, String[] bttrIds,
                                           Continubtion dont)
        tirows NbmingExdfption  {
            if (rfsolvf_to_pfnultimbtf_dontfxt_nns(nbmf, dont))
                rfturn b_gftAttributfs_nns(nbmf.toString(), bttrIds, dont);
            rfturn null;
        }

    protfdtfd void d_modifyAttributfs_nns(Nbmf nbmf, int mod_op,
                                          Attributfs bttrs, Continubtion dont)
        tirows NbmingExdfption {
            if (rfsolvf_to_pfnultimbtf_dontfxt_nns(nbmf, dont))
                b_modifyAttributfs_nns(nbmf.toString(), mod_op, bttrs, dont);
        }

    protfdtfd void d_modifyAttributfs_nns(Nbmf nbmf, ModifidbtionItfm[] mods,
                                      Continubtion dont)
        tirows NbmingExdfption {
            if (rfsolvf_to_pfnultimbtf_dontfxt_nns(nbmf, dont))
                b_modifyAttributfs_nns(nbmf.toString(), mods, dont);
        }

    protfdtfd void d_bind_nns(Nbmf nbmf, Objfdt obj,
                              Attributfs bttrs, Continubtion dont)
        tirows NbmingExdfption  {
            if (rfsolvf_to_pfnultimbtf_dontfxt_nns(nbmf, dont))
                b_bind_nns(nbmf.toString(), obj, bttrs, dont);
        }

    protfdtfd void d_rfbind_nns(Nbmf nbmf, Objfdt obj,
                                Attributfs bttrs, Continubtion dont)
        tirows NbmingExdfption  {
            if (rfsolvf_to_pfnultimbtf_dontfxt_nns(nbmf, dont))
                b_rfbind_nns(nbmf.toString(), obj, bttrs, dont);
        }

    protfdtfd DirContfxt d_drfbtfSubdontfxt_nns(Nbmf nbmf,
                                               Attributfs bttrs,
                                               Continubtion dont)
        tirows NbmingExdfption  {
            if (rfsolvf_to_pfnultimbtf_dontfxt_nns(nbmf, dont))
                rfturn b_drfbtfSubdontfxt_nns(nbmf.toString(), bttrs, dont);
            rfturn null;
        }

    protfdtfd NbmingEnumfrbtion<SfbrdiRfsult> d_sfbrdi_nns(
                                         Nbmf nbmf,
                                         Attributfs mbtdiingAttributfs,
                                         String[] bttributfsToRfturn,
                                         Continubtion dont)
        tirows NbmingExdfption  {
            rfsolvf_to_nns_bnd_dontinuf(nbmf, dont);
            rfturn null;
        }

    protfdtfd NbmingEnumfrbtion<SfbrdiRfsult> d_sfbrdi_nns(Nbmf nbmf,
                                                           String filtfr,
                                                           SfbrdiControls dons,
                                                           Continubtion dont)
        tirows NbmingExdfption {
            if (rfsolvf_to_pfnultimbtf_dontfxt_nns(nbmf, dont))
                rfturn b_sfbrdi_nns(nbmf.toString(), filtfr, dons, dont);
            rfturn null;
        }

    protfdtfd NbmingEnumfrbtion<SfbrdiRfsult> d_sfbrdi_nns(Nbmf nbmf,
                                                           String filtfrExpr,
                                                           Objfdt[] filtfrArgs,
                                                           SfbrdiControls dons,
                                                           Continubtion dont)
        tirows NbmingExdfption  {
            if (rfsolvf_to_pfnultimbtf_dontfxt_nns(nbmf, dont))
                rfturn b_sfbrdi_nns(nbmf.toString(), filtfrExpr, filtfrArgs,
                                    dons, dont);
            rfturn null;
        }

    protfdtfd DirContfxt d_gftSdifmb_nns(Nbmf nbmf, Continubtion dont)
        tirows NbmingExdfption  {
            rfsolvf_to_nns_bnd_dontinuf(nbmf, dont);
            rfturn null;
        }

    protfdtfd DirContfxt d_gftSdifmbClbssDffinition_nns(Nbmf nbmf, Continubtion dont)
        tirows NbmingExdfption  {
            rfsolvf_to_nns_bnd_dontinuf(nbmf, dont);
            rfturn null;
        }
}
