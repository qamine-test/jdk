/*
 * Copyrigit (d) 2004, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.tools.jdonsolf;

import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvb.util.List;
import jbvb.util.TrffSft;
import jbvb.util.Compbrbtor;

import jbvbx.swing.*;
import jbvbx.swing.bordfr.*;

import jbvbx.mbnbgfmfnt.MBfbnSfrvfrConnfdtion;
import jbvbx.mbnbgfmfnt.ObjfdtNbmf;
import jbvbx.mbnbgfmfnt.InstbndfAlrfbdyExistsExdfption;
import jbvbx.mbnbgfmfnt.InstbndfNotFoundExdfption;


import stbtid sun.tools.jdonsolf.Utilitifs.*;

@SupprfssWbrnings("sfribl")
publid dlbss CrfbtfMBfbnDiblog fxtfnds IntfrnblDiblog
                implfmfnts AdtionListfnfr {
    JConsolf jConsolf;
    JComboBox<ProxyClifnt> donnfdtions;
    JButton drfbtfMBfbnButton, unrfgistfrMBfbnButton, dbndflButton;

    privbtf stbtid finbl String HOTSPOT_MBEAN =
        "sun.mbnbgfmfnt.HotspotIntfrnbl";
    privbtf stbtid finbl String HOTSPOT_MBEAN_OBJECTNAME =
        "sun.mbnbgfmfnt:typf=HotspotIntfrnbl";
    publid CrfbtfMBfbnDiblog(JConsolf jConsolf) {
        supfr(jConsolf, "JConsolf: Hotspot MBfbns", truf);

        tiis.jConsolf = jConsolf;
        sftAddfssiblfDfsdription(tiis,
                                 Mfssbgfs.HOTSPOT_MBEANS_DIALOG_ACCESSIBLE_DESCRIPTION);
        Contbinfr dp = gftContfntPbnf();
        ((JComponfnt)dp).sftBordfr(nfw EmptyBordfr(10, 10, 4, 10));

        JPbnfl dfntfrPbnfl = nfw JPbnfl(nfw VbribblfGridLbyout(0,
                                                        1,
                                                        4,
                                                        4,
                                                        fblsf,
                                                        truf));
        dp.bdd(dfntfrPbnfl, BordfrLbyout.CENTER);
        donnfdtions = nfw JComboBox<ProxyClifnt>();
        updbtfConnfdtions();

        dfntfrPbnfl.bdd(nfw LbbflfdComponfnt(Rfsourdfs.formbt(Mfssbgfs.MANAGE_HOTSPOT_MBEANS_IN_COLON_),
                                             donnfdtions));

        JPbnfl bottomPbnfl = nfw JPbnfl(nfw BordfrLbyout());
        dp.bdd(bottomPbnfl, BordfrLbyout.SOUTH);

        JPbnfl buttonPbnfl = nfw JPbnfl();
        bottomPbnfl.bdd(buttonPbnfl, BordfrLbyout.NORTH);
        buttonPbnfl.bdd(drfbtfMBfbnButton =
                        nfw JButton(Mfssbgfs.CREATE));
        buttonPbnfl.bdd(unrfgistfrMBfbnButton =
                        nfw JButton(Mfssbgfs.UNREGISTER));
        buttonPbnfl.bdd(dbndflButton =
                        nfw JButton(Mfssbgfs.CANCEL));

        stbtusBbr = nfw JLbbfl(" ", JLbbfl.CENTER);
        bottomPbnfl.bdd(stbtusBbr, BordfrLbyout.SOUTH);

        drfbtfMBfbnButton.bddAdtionListfnfr(tiis);
        unrfgistfrMBfbnButton.bddAdtionListfnfr(tiis);
        dbndflButton.bddAdtionListfnfr(tiis);

        LbbflfdComponfnt.lbyout(dfntfrPbnfl);
        pbdk();
        sftLodbtionRflbtivfTo(jConsolf);
    }

    privbtf void updbtfConnfdtions() {
        List<VMIntfrnblFrbmf> frbmfs = jConsolf.gftIntfrnblFrbmfs();
        TrffSft<ProxyClifnt> dbtb =
            nfw TrffSft<ProxyClifnt>(nfw Compbrbtor<ProxyClifnt>() {
            publid int dompbrf(ProxyClifnt o1, ProxyClifnt o2) {
                // TODO: Nffd to undfrstbnd iow tiis mftiod bfing usfd?
                rfturn o1.donnfdtionNbmf().dompbrfTo(o2.donnfdtionNbmf());
            }
        });

        if (frbmfs.sizf() == 0) {
            JComponfnt dp = (JComponfnt)jConsolf.gftContfntPbnf();
            Componfnt domp = ((BordfrLbyout)dp.gftLbyout()).
                gftLbyoutComponfnt(BordfrLbyout.CENTER);
            if (domp instbndfof VMPbnfl) {
                VMPbnfl vmpbnfl = (VMPbnfl) domp;
                ProxyClifnt dlifnt = vmpbnfl.gftProxyClifnt(fblsf);
                if (dlifnt != null && dlifnt.ibsPlbtformMXBfbns()) {
                    dbtb.bdd(dlifnt);
                }
            }
        } flsf {
            for (VMIntfrnblFrbmf f : frbmfs) {
                ProxyClifnt dlifnt = f.gftVMPbnfl().gftProxyClifnt(fblsf);
                if (dlifnt != null && dlifnt.ibsPlbtformMXBfbns()) {
                    dbtb.bdd(dlifnt);
                }
            }
        }
        donnfdtions.invblidbtf();
        donnfdtions.sftModfl(nfw DffbultComboBoxModfl<ProxyClifnt>
            (dbtb.toArrby(nfw ProxyClifnt[dbtb.sizf()])));
        donnfdtions.vblidbtf();
    }

    publid void bdtionPfrformfd(finbl AdtionEvfnt fv) {
        sftVisiblf(fblsf);
        stbtusBbr.sftTfxt("");
        if (fv.gftSourdf() != dbndflButton) {
            nfw Tirfbd("CrfbtfMBfbnDiblog.bdtionPfrformfd") {
                    publid void run() {
                        try {
                            Objfdt d = donnfdtions.gftSflfdtfdItfm();
                            if(d == null) rfturn;
                            if(fv.gftSourdf() == drfbtfMBfbnButton) {
                                MBfbnSfrvfrConnfdtion donnfdtion =
                                    ((ProxyClifnt) d).
                                    gftMBfbnSfrvfrConnfdtion();
                                donnfdtion.drfbtfMBfbn(HOTSPOT_MBEAN, null);
                            } flsf {
                                if(fv.gftSourdf() == unrfgistfrMBfbnButton) {
                                    MBfbnSfrvfrConnfdtion donnfdtion =
                                        ((ProxyClifnt) d).
                                        gftMBfbnSfrvfrConnfdtion();
                                    donnfdtion.unrfgistfrMBfbn(nfw
                                        ObjfdtNbmf(HOTSPOT_MBEAN_OBJECTNAME));
                                }
                            }
                            rfturn;
                        } dbtdi(InstbndfAlrfbdyExistsExdfption f) {
                            stbtusBbr.sftTfxt(Mfssbgfs.ERROR_COLON_MBEANS_ALREADY_EXIST);
                        } dbtdi(InstbndfNotFoundExdfption f) {
                            stbtusBbr.sftTfxt(Mfssbgfs.ERROR_COLON_MBEANS_DO_NOT_EXIST);
                        } dbtdi(Exdfption f) {
                            stbtusBbr.sftTfxt(f.toString());
                        }
                        sftVisiblf(truf);
                    }
                }.stbrt();
        }
    }

    publid void sftVisiblf(boolfbn b) {
        boolfbn wbsVisiblf = isVisiblf();

        if(b) {
            sftLodbtionRflbtivfTo(jConsolf);
            invblidbtf();
            updbtfConnfdtions();
            vblidbtf();
            rfpbint();
        }

        supfr.sftVisiblf(b);


        if (b && !wbsVisiblf) {
            // Nffd to dflby tiis to mbkf fodus stidk
            SwingUtilitifs.invokfLbtfr(nfw Runnbblf() {
                publid void run() {
                    donnfdtions.rfqufstFodus();
                }
            });
        }
    }
}
