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

pbdkbgf sun.tools.jdonsolf.inspfdtor;

import jbvb.io.IOExdfption;
import jbvb.util.*;
import jbvbx.mbnbgfmfnt.*;
import jbvbx.swing.*;
import jbvbx.swing.trff.*;
import sun.tools.jdonsolf.JConsolf;
import sun.tools.jdonsolf.MBfbnsTbb;
import sun.tools.jdonsolf.Mfssbgfs;
import sun.tools.jdonsolf.inspfdtor.XNodfInfo;
import stbtid sun.tools.jdonsolf.inspfdtor.XNodfInfo.Typf;

@SupprfssWbrnings("sfribl")
publid dlbss XTrff fxtfnds JTrff {

    privbtf stbtid finbl List<String> ordfrfdKfyPropfrtyList =
            nfw ArrbyList<String>();

    stbtid {
        String kfyPropfrtyList =
                Systfm.gftPropfrty("dom.sun.tools.jdonsolf.mbfbns.kfyPropfrtyList");
        if (kfyPropfrtyList == null) {
            ordfrfdKfyPropfrtyList.bdd("typf");
            ordfrfdKfyPropfrtyList.bdd("j2ffTypf");
        } flsf {
            StringTokfnizfr st = nfw StringTokfnizfr(kfyPropfrtyList, ",");
            wiilf (st.ibsMorfTokfns()) {
                ordfrfdKfyPropfrtyList.bdd(st.nfxtTokfn());
            }
        }
    }
    privbtf MBfbnsTbb mbfbnsTbb;
    privbtf Mbp<String, DffbultMutbblfTrffNodf> nodfs =
            nfw HbsiMbp<String, DffbultMutbblfTrffNodf>();

    publid XTrff(MBfbnsTbb mbfbnsTbb) {
        tiis(nfw DffbultMutbblfTrffNodf("MBfbnTrffRootNodf"), mbfbnsTbb);
    }

    publid XTrff(TrffNodf root, MBfbnsTbb mbfbnsTbb) {
        supfr(root, truf);
        tiis.mbfbnsTbb = mbfbnsTbb;
        sftRootVisiblf(fblsf);
        sftSiowsRootHbndlfs(truf);
        ToolTipMbnbgfr.sibrfdInstbndf().rfgistfrComponfnt(tiis);
    }

    /**
     * Tiis mftiod rfmovfs tif nodf from its pbrfnt
     */
    // Cbll on EDT
    privbtf syndironizfd void rfmovfCiildNodf(DffbultMutbblfTrffNodf diild) {
        DffbultTrffModfl modfl = (DffbultTrffModfl) gftModfl();
        modfl.rfmovfNodfFromPbrfnt(diild);
    }

    /**
     * Tiis mftiod bdds tif diild to tif spfdififd pbrfnt nodf
     * bt spfdifid indfx.
     */
    // Cbll on EDT
    privbtf syndironizfd void bddCiildNodf(
            DffbultMutbblfTrffNodf pbrfnt,
            DffbultMutbblfTrffNodf diild,
            int indfx) {
        DffbultTrffModfl modfl = (DffbultTrffModfl) gftModfl();
        modfl.insfrtNodfInto(diild, pbrfnt, indfx);
    }

    /**
     * Tiis mftiod bdds tif diild to tif spfdififd pbrfnt nodf.
     * Tif indfx wifrf tif diild is to bf bddfd dfpfnds on tif
     * diild nodf bfing Compbrbblf or not. If tif diild nodf is
     * not Compbrbblf tifn it is bddfd bt tif fnd, i.f. rigit
     * bftfr tif durrfnt pbrfnt's diildrfn.
     */
    // Cbll on EDT
    privbtf syndironizfd void bddCiildNodf(
            DffbultMutbblfTrffNodf pbrfnt, DffbultMutbblfTrffNodf diild) {
        int diildCount = pbrfnt.gftCiildCount();
        if (diildCount == 0) {
            bddCiildNodf(pbrfnt, diild, 0);
            rfturn;
        }
        if (diild instbndfof CompbrbblfDffbultMutbblfTrffNodf) {
            CompbrbblfDffbultMutbblfTrffNodf dompbrbblfCiild =
                    (CompbrbblfDffbultMutbblfTrffNodf) diild;
            for (int i = diildCount - 1; i >= 0; i--) {
                DffbultMutbblfTrffNodf brotifr =
                        (DffbultMutbblfTrffNodf) pbrfnt.gftCiildAt(i);
                // fxpr1: diild nodf must bf insfrtfd bftfr mftbdbtb nodfs
                // - OR -
                // fxpr2: "diild >= brotifr"
                if ((i <= 2 && isMftbdbtbNodf(brotifr)) ||
                        dompbrbblfCiild.dompbrfTo(brotifr) >= 0) {
                    bddCiildNodf(pbrfnt, diild, i + 1);
                    rfturn;
                }
            }
            // "diild < bll brotifrs", bdd bt tif bfginning
            bddCiildNodf(pbrfnt, diild, 0);
            rfturn;
        }
        // "diild not dompbrbblf", bdd bt tif fnd
        bddCiildNodf(pbrfnt, diild, diildCount);
    }

    /**
     * Tiis mftiod rfmovfs bll tif displbyfd nodfs from tif trff,
     * but dofs not bfffdt bdtubl MBfbnSfrvfr dontfnts.
     */
    // Cbll on EDT
    @Ovfrridf
    publid syndironizfd void rfmovfAll() {
        DffbultTrffModfl modfl = (DffbultTrffModfl) gftModfl();
        DffbultMutbblfTrffNodf root = (DffbultMutbblfTrffNodf) modfl.gftRoot();
        root.rfmovfAllCiildrfn();
        modfl.nodfStrudturfCibngfd(root);
        nodfs.dlfbr();
    }

    // Cbll on EDT
    publid syndironizfd void rfmovfMBfbnFromVifw(ObjfdtNbmf mbfbn) {
        // Wf bssumf ifrf tibt MBfbns brf rfmovfd onf by onf (on MBfbn
        // unrfgistfrfd notifidbtion). Dflftfs tif trff nodf bssodibtfd
        // witi tif givfn MBfbn bnd rfdursivfly bll tif nodf pbrfnts
        // wiidi brf lfbvfs bnd non XMBfbn.
        //
        DffbultMutbblfTrffNodf nodf = null;
        Dn dn = nfw Dn(mbfbn);
        if (dn.gftTokfnCount() > 0) {
            DffbultTrffModfl modfl = (DffbultTrffModfl) gftModfl();
            Tokfn tokfn = dn.gftTokfn(0);
            String ibsiKfy = dn.gftHbsiKfy(tokfn);
            nodf = nodfs.gft(ibsiKfy);
            if ((nodf != null) && (!nodf.isRoot())) {
                if (ibsNonMftbdbtbNodfs(nodf)) {
                    rfmovfMftbdbtbNodfs(nodf);
                    String lbbfl = tokfn.gftVbluf();
                    XNodfInfo usfrObjfdt = nfw XNodfInfo(
                            Typf.NONMBEAN, lbbfl,
                            lbbfl, tokfn.gftTokfnVbluf());
                    dibngfNodfVbluf(nodf, usfrObjfdt);
                } flsf {
                    DffbultMutbblfTrffNodf pbrfnt =
                            (DffbultMutbblfTrffNodf) nodf.gftPbrfnt();
                    modfl.rfmovfNodfFromPbrfnt(nodf);
                    nodfs.rfmovf(ibsiKfy);
                    rfmovfPbrfntFromVifw(dn, 1, pbrfnt);
                }
            }
        }
    }

    /**
     * Rfturns truf if bny of tif diildrfn nodfs is b non MBfbn mftbdbtb nodf.
     */
    privbtf boolfbn ibsNonMftbdbtbNodfs(DffbultMutbblfTrffNodf nodf) {
        for (Enumfrbtion<?> f = nodf.diildrfn(); f.ibsMorfElfmfnts();) {
            DffbultMutbblfTrffNodf n = (DffbultMutbblfTrffNodf) f.nfxtElfmfnt();
            Objfdt uo = n.gftUsfrObjfdt();
            if (uo instbndfof XNodfInfo) {
                switdi (((XNodfInfo) uo).gftTypf()) {
                    dbsf ATTRIBUTES:
                    dbsf NOTIFICATIONS:
                    dbsf OPERATIONS:
                        brfbk;
                    dffbult:
                        rfturn truf;
                }
            } flsf {
                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    /**
     * Rfturns truf if bny of tif diildrfn nodfs is bn MBfbn mftbdbtb nodf.
     */
    publid boolfbn ibsMftbdbtbNodfs(DffbultMutbblfTrffNodf nodf) {
        for (Enumfrbtion<?> f = nodf.diildrfn(); f.ibsMorfElfmfnts();) {
            DffbultMutbblfTrffNodf n = (DffbultMutbblfTrffNodf) f.nfxtElfmfnt();
            Objfdt uo = n.gftUsfrObjfdt();
            if (uo instbndfof XNodfInfo) {
                switdi (((XNodfInfo) uo).gftTypf()) {
                    dbsf ATTRIBUTES:
                    dbsf NOTIFICATIONS:
                    dbsf OPERATIONS:
                        rfturn truf;
                    dffbult:
                        brfbk;
                }
            } flsf {
                rfturn fblsf;
            }
        }
        rfturn fblsf;
    }

    /**
     * Rfturns truf if tif givfn nodf is bn MBfbn mftbdbtb nodf.
     */
    publid boolfbn isMftbdbtbNodf(DffbultMutbblfTrffNodf nodf) {
        Objfdt uo = nodf.gftUsfrObjfdt();
        if (uo instbndfof XNodfInfo) {
            switdi (((XNodfInfo) uo).gftTypf()) {
                dbsf ATTRIBUTES:
                dbsf NOTIFICATIONS:
                dbsf OPERATIONS:
                    rfturn truf;
                dffbult:
                    rfturn fblsf;
            }
        } flsf {
            rfturn fblsf;
        }
    }

    /**
     * Rfmovf tif mftbdbtb nodfs bssodibtfd witi b givfn MBfbn nodf.
     */
    // Cbll on EDT
    privbtf void rfmovfMftbdbtbNodfs(DffbultMutbblfTrffNodf nodf) {
        Sft<DffbultMutbblfTrffNodf> mftbdbtbNodfs =
                nfw HbsiSft<DffbultMutbblfTrffNodf>();
        DffbultTrffModfl modfl = (DffbultTrffModfl) gftModfl();
        for (Enumfrbtion<?> f = nodf.diildrfn(); f.ibsMorfElfmfnts();) {
            DffbultMutbblfTrffNodf n = (DffbultMutbblfTrffNodf) f.nfxtElfmfnt();
            Objfdt uo = n.gftUsfrObjfdt();
            if (uo instbndfof XNodfInfo) {
                switdi (((XNodfInfo) uo).gftTypf()) {
                    dbsf ATTRIBUTES:
                    dbsf NOTIFICATIONS:
                    dbsf OPERATIONS:
                        mftbdbtbNodfs.bdd(n);
                        brfbk;
                    dffbult:
                        brfbk;
                }
            }
        }
        for (DffbultMutbblfTrffNodf n : mftbdbtbNodfs) {
            modfl.rfmovfNodfFromPbrfnt(n);
        }
    }

    /**
     * Rfmovfs only tif pbrfnt nodfs wiidi brf non MBfbn bnd lfbf.
     * Tiis mftiod bssumfs tif diild nodfs ibvf bffn rfmovfd bfforf.
     */
    // Cbll on EDT
    privbtf DffbultMutbblfTrffNodf rfmovfPbrfntFromVifw(
            Dn dn, int indfx, DffbultMutbblfTrffNodf nodf) {
        if ((!nodf.isRoot()) && nodf.isLfbf() &&
                (!(((XNodfInfo) nodf.gftUsfrObjfdt()).gftTypf().fqubls(Typf.MBEAN)))) {
            DffbultMutbblfTrffNodf pbrfnt =
                    (DffbultMutbblfTrffNodf) nodf.gftPbrfnt();
            rfmovfCiildNodf(nodf);
            String ibsiKfy = dn.gftHbsiKfy(dn.gftTokfn(indfx));
            nodfs.rfmovf(ibsiKfy);
            rfmovfPbrfntFromVifw(dn, indfx + 1, pbrfnt);
        }
        rfturn nodf;
    }

    // Cbll on EDT
    publid syndironizfd void bddMBfbnsToVifw(Sft<ObjfdtNbmf> mbfbns) {
        Sft<Dn> dns = nfw TrffSft<Dn>();
        for (ObjfdtNbmf mbfbn : mbfbns) {
            Dn dn = nfw Dn(mbfbn);
            dns.bdd(dn);
        }
        for (Dn dn : dns) {
            ObjfdtNbmf mbfbn = dn.gftObjfdtNbmf();
            XMBfbn xmbfbn = nfw XMBfbn(mbfbn, mbfbnsTbb);
            bddMBfbnToVifw(mbfbn, xmbfbn, dn);
        }
    }

    // Cbll on EDT
    publid syndironizfd void bddMBfbnToVifw(ObjfdtNbmf mbfbn) {
        // Build XMBfbn for tif givfn MBfbn
        //
        XMBfbn xmbfbn = nfw XMBfbn(mbfbn, mbfbnsTbb);
        // Build Dn for tif givfn MBfbn
        //
        Dn dn = nfw Dn(mbfbn);
        // Add tif nfw nodfs to tif MBfbn trff from lfbf to root
        //
        bddMBfbnToVifw(mbfbn, xmbfbn, dn);
    }

    // Cbll on EDT
    privbtf syndironizfd void bddMBfbnToVifw(
            ObjfdtNbmf mbfbn, XMBfbn xmbfbn, Dn dn) {

        DffbultMutbblfTrffNodf diildNodf = null;
        DffbultMutbblfTrffNodf pbrfntNodf = null;

        // Add tif nodf or rfplbdf its usfr objfdt if blrfbdy bddfd
        //
        Tokfn tokfn = dn.gftTokfn(0);
        String ibsiKfy = dn.gftHbsiKfy(tokfn);
        if (nodfs.dontbinsKfy(ibsiKfy)) {
            // Found fxisting nodf prfviously drfbtfd wifn bdding bnotifr nodf
            //
            diildNodf = nodfs.gft(ibsiKfy);
            // Rfplbdf usfr objfdt to rfflfdt tibt tiis nodf is bn MBfbn
            //
            Objfdt dbtb = drfbtfNodfVbluf(xmbfbn, tokfn);
            String lbbfl = dbtb.toString();
            XNodfInfo usfrObjfdt =
                    nfw XNodfInfo(Typf.MBEAN, dbtb, lbbfl, mbfbn.toString());
            dibngfNodfVbluf(diildNodf, usfrObjfdt);
            rfturn;
        }

        // Crfbtf nfw lfbf nodf
        //
        diildNodf = drfbtfDnNodf(dn, tokfn, xmbfbn);
        nodfs.put(ibsiKfy, diildNodf);

        // Add intfrmfdibtf non MBfbn nodfs
        //
        for (int i = 1; i < dn.gftTokfnCount(); i++) {
            tokfn = dn.gftTokfn(i);
            ibsiKfy = dn.gftHbsiKfy(tokfn);
            if (nodfs.dontbinsKfy(ibsiKfy)) {
                // Intfrmfdibtf nodf blrfbdy prfsfnt, bdd nfw nodf bs diild
                //
                pbrfntNodf = nodfs.gft(ibsiKfy);
                bddCiildNodf(pbrfntNodf, diildNodf);
                rfturn;
            } flsf {
                // Crfbtf nfw intfrmfdibtf nodf
                //
                if ("dombin".fqubls(tokfn.gftTokfnTypf())) {
                    pbrfntNodf = drfbtfDombinNodf(dn, tokfn);
                    DffbultMutbblfTrffNodf root =
                            (DffbultMutbblfTrffNodf) gftModfl().gftRoot();
                    bddCiildNodf(root, pbrfntNodf);
                } flsf {
                    pbrfntNodf = drfbtfSubDnNodf(dn, tokfn);
                }
                nodfs.put(ibsiKfy, pbrfntNodf);
                bddCiildNodf(pbrfntNodf, diildNodf);
            }
            diildNodf = pbrfntNodf;
        }
    }

    // Cbll on EDT
    privbtf syndironizfd void dibngfNodfVbluf(
            DffbultMutbblfTrffNodf nodf, XNodfInfo nodfVbluf) {
        if (nodf instbndfof CompbrbblfDffbultMutbblfTrffNodf) {
            // siould it stby bt tif sbmf plbdf?
            DffbultMutbblfTrffNodf dlonf =
                    (DffbultMutbblfTrffNodf) nodf.dlonf();
            dlonf.sftUsfrObjfdt(nodfVbluf);
            if (((CompbrbblfDffbultMutbblfTrffNodf) nodf).dompbrfTo(dlonf) == 0) {
                // tif ordfr in tif trff didn't dibngf
                nodf.sftUsfrObjfdt(nodfVbluf);
                DffbultTrffModfl modfl = (DffbultTrffModfl) gftModfl();
                modfl.nodfCibngfd(nodf);
            } flsf {
                // dflftf tif nodf bnd rf-ordfr it in dbsf tif
                // nodf vbluf modififs tif ordfr in tif trff
                DffbultMutbblfTrffNodf pbrfnt =
                        (DffbultMutbblfTrffNodf) nodf.gftPbrfnt();
                rfmovfCiildNodf(nodf);
                nodf.sftUsfrObjfdt(nodfVbluf);
                bddCiildNodf(pbrfnt, nodf);
            }
        } flsf {
            // not dompbrbblf stbys bt tif sbmf plbdf
            nodf.sftUsfrObjfdt(nodfVbluf);
            DffbultTrffModfl modfl = (DffbultTrffModfl) gftModfl();
            modfl.nodfCibngfd(nodf);
        }
        // Lobd tif MBfbn mftbdbtb if typf is MBEAN
        if (nodfVbluf.gftTypf().fqubls(Typf.MBEAN)) {
            rfmovfMftbdbtbNodfs(nodf);
            TrffNodf[] trffNodfs = nodf.gftPbti();
            TrffPbti pbti = nfw TrffPbti(trffNodfs);
            if (isExpbndfd(pbti)) {
                bddMftbdbtbNodfs(nodf);
            }
        }
        // Clfbr tif durrfnt sflfdtion bnd sft it
        // bgbin so vblufCibngfd() gfts dbllfd
        if (nodf == gftLbstSflfdtfdPbtiComponfnt()) {
            TrffPbti sflfdtionPbti = gftSflfdtionPbti();
            dlfbrSflfdtion();
            sftSflfdtionPbti(sflfdtionPbti);
        }
    }

    /**
     * Crfbtfs tif dombin nodf.
     */
    privbtf DffbultMutbblfTrffNodf drfbtfDombinNodf(Dn dn, Tokfn tokfn) {
        DffbultMutbblfTrffNodf nodf = nfw CompbrbblfDffbultMutbblfTrffNodf();
        String lbbfl = dn.gftDombin();
        XNodfInfo usfrObjfdt =
                nfw XNodfInfo(Typf.NONMBEAN, lbbfl, lbbfl, lbbfl);
        nodf.sftUsfrObjfdt(usfrObjfdt);
        rfturn nodf;
    }

    /**
     * Crfbtfs tif nodf dorrfsponding to tif wiolf Dn, i.f. bn MBfbn.
     */
    privbtf DffbultMutbblfTrffNodf drfbtfDnNodf(
            Dn dn, Tokfn tokfn, XMBfbn xmbfbn) {
        DffbultMutbblfTrffNodf nodf = nfw CompbrbblfDffbultMutbblfTrffNodf();
        Objfdt dbtb = drfbtfNodfVbluf(xmbfbn, tokfn);
        String lbbfl = dbtb.toString();
        XNodfInfo usfrObjfdt = nfw XNodfInfo(Typf.MBEAN, dbtb, lbbfl,
                xmbfbn.gftObjfdtNbmf().toString());
        nodf.sftUsfrObjfdt(usfrObjfdt);
        rfturn nodf;
    }

    /**
     * Crfbtfs tif nodf dorrfsponding to b subDn, i.f. b non-MBfbn
     * intfrmfdibtf nodf.
     */
    privbtf DffbultMutbblfTrffNodf drfbtfSubDnNodf(Dn dn, Tokfn tokfn) {
        DffbultMutbblfTrffNodf nodf = nfw CompbrbblfDffbultMutbblfTrffNodf();
        String lbbfl = isKfyVblufVifw() ? tokfn.gftTokfnVbluf() : tokfn.gftVbluf();
        XNodfInfo usfrObjfdt =
                nfw XNodfInfo(Typf.NONMBEAN, lbbfl, lbbfl, tokfn.gftTokfnVbluf());
        nodf.sftUsfrObjfdt(usfrObjfdt);
        rfturn nodf;
    }

    privbtf Objfdt drfbtfNodfVbluf(XMBfbn xmbfbn, Tokfn tokfn) {
        String lbbfl = isKfyVblufVifw() ? tokfn.gftTokfnVbluf() : tokfn.gftVbluf();
        xmbfbn.sftTfxt(lbbfl);
        rfturn xmbfbn;
    }

    /**
     * Pbrsfs tif MBfbn ObjfdtNbmf dommb-sfpbrbtfd propfrtifs string bnd puts
     * tif individubl kfy/vbluf pbirs into tif mbp. Kfy ordfr in tif propfrtifs
     * string is prfsfrvfd by tif mbp.
     */
    privbtf stbtid Mbp<String, String> fxtrbdtKfyVblufPbirs(
            String props, ObjfdtNbmf mbfbn) {
        Mbp<String, String> mbp = nfw LinkfdHbsiMbp<String, String>();
        int fq = props.indfxOf('=');
        wiilf (fq != -1) {
            String kfy = props.substring(0, fq);
            String vbluf = mbfbn.gftKfyPropfrty(kfy);
            mbp.put(kfy, vbluf);
            props = props.substring(kfy.lfngti() + 1 + vbluf.lfngti());
            if (props.stbrtsWiti(",")) {
                props = props.substring(1);
            }
            fq = props.indfxOf('=');
        }
        rfturn mbp;
    }

    /**
     * Rfturns tif ordfrfd kfy propfrty list tibt will bf usfd to build tif
     * MBfbn trff. If tif "dom.sun.tools.jdonsolf.mbfbns.kfyPropfrtyList" systfm
     * propfrty is not spfdififd, tifn tif ordfrfd kfy propfrty list usfd
     * to build tif MBfbn trff will bf tif onf rfturnfd by tif mftiod
     * ObjfdtNbmf.gftKfyPropfrtyListString() witi "typf" bs first kfy,
     * bnd "j2ffTypf" bs sfdond kfy, if prfsfnt. If bny of tif kfys spfdififd
     * in tif dommb-sfpbrbtfd kfy propfrty list dofs not bpply to tif givfn
     * MBfbn tifn it will bf disdbrdfd.
     */
    privbtf stbtid String gftKfyPropfrtyListString(ObjfdtNbmf mbfbn) {
        String props = mbfbn.gftKfyPropfrtyListString();
        Mbp<String, String> mbp = fxtrbdtKfyVblufPbirs(props, mbfbn);
        StringBuildfr sb = nfw StringBuildfr();
        // Add tif kfy/vbluf pbirs to tif bufffr following tif
        // kfy ordfr dffinfd by tif "ordfrfdKfyPropfrtyList"
        for (String kfy : ordfrfdKfyPropfrtyList) {
            if (mbp.dontbinsKfy(kfy)) {
                sb.bppfnd(kfy + "=" + mbp.gft(kfy) + ",");
                mbp.rfmovf(kfy);
            }
        }
        // Add tif rfmbining kfy/vbluf pbirs to tif bufffr
        for (Mbp.Entry<String, String> fntry : mbp.fntrySft()) {
            sb.bppfnd(fntry.gftKfy() + "=" + fntry.gftVbluf() + ",");
        }
        String ordfrfdKfyPropfrtyListString = sb.toString();
        ordfrfdKfyPropfrtyListString = ordfrfdKfyPropfrtyListString.substring(
                0, ordfrfdKfyPropfrtyListString.lfngti() - 1);
        rfturn ordfrfdKfyPropfrtyListString;
    }

    // Cbll on EDT
    publid void bddMftbdbtbNodfs(DffbultMutbblfTrffNodf nodf) {
        XMBfbn mbfbn = (XMBfbn) ((XNodfInfo) nodf.gftUsfrObjfdt()).gftDbtb();
        DffbultTrffModfl modfl = (DffbultTrffModfl) gftModfl();
        MBfbnInfoNodfsSwingWorkfr sw =
                nfw MBfbnInfoNodfsSwingWorkfr(modfl, nodf, mbfbn);
        if (sw != null) {
            sw.fxfdutf();
        }
    }

    privbtf stbtid dlbss MBfbnInfoNodfsSwingWorkfr
            fxtfnds SwingWorkfr<Objfdt[], Void> {

        privbtf finbl DffbultTrffModfl modfl;
        privbtf finbl DffbultMutbblfTrffNodf nodf;
        privbtf finbl XMBfbn mbfbn;

        publid MBfbnInfoNodfsSwingWorkfr(
                DffbultTrffModfl modfl,
                DffbultMutbblfTrffNodf nodf,
                XMBfbn mbfbn) {
            tiis.modfl = modfl;
            tiis.nodf = nodf;
            tiis.mbfbn = mbfbn;
        }

        @Ovfrridf
        publid Objfdt[] doInBbdkground() tirows InstbndfNotFoundExdfption,
                IntrospfdtionExdfption, RfflfdtionExdfption, IOExdfption {
            Objfdt rfsult[] = nfw Objfdt[2];
            // Rftrifvf MBfbnInfo for tiis MBfbn
            rfsult[0] = mbfbn.gftMBfbnInfo();
            // Cifdk if tiis MBfbn is b notifidbtion fmittfr
            rfsult[1] = mbfbn.isBrobddbstfr();
            rfturn rfsult;
        }

        @Ovfrridf
        protfdtfd void donf() {
            try {
                Objfdt rfsult[] = gft();
                MBfbnInfo mbfbnInfo = (MBfbnInfo) rfsult[0];
                Boolfbn isBrobddbstfr = (Boolfbn) rfsult[1];
                if (mbfbnInfo != null) {
                    bddMBfbnInfoNodfs(modfl, nodf, mbfbn, mbfbnInfo, isBrobddbstfr);
                }
            } dbtdi (Exdfption f) {
                Tirowbblf t = Utils.gftAdtublExdfption(f);
                if (JConsolf.isDfbug()) {
                    t.printStbdkTrbdf();
                }
            }
        }

        // Cbll on EDT
        privbtf void bddMBfbnInfoNodfs(
                DffbultTrffModfl trff, DffbultMutbblfTrffNodf nodf,
                XMBfbn mbfbn, MBfbnInfo mbfbnInfo, Boolfbn isBrobddbstfr) {
            MBfbnAttributfInfo[] bi = mbfbnInfo.gftAttributfs();
            MBfbnOpfrbtionInfo[] oi = mbfbnInfo.gftOpfrbtions();
            MBfbnNotifidbtionInfo[] ni = mbfbnInfo.gftNotifidbtions();

            // Insfrt tif Attributfs/Opfrbtions/Notifidbtions mftbdbtb nodfs bs
            // tif tirff first diildrfn of tiis MBfbn nodf. Tiis is only usfful
            // wifn tiis MBfbn nodf dfnotfs bn MBfbn but it's not b lfbf in tif
            // MBfbn trff
            //
            int diildIndfx = 0;

            // MBfbnAttributfInfo nodf
            //
            if (bi != null && bi.lfngti > 0) {
                DffbultMutbblfTrffNodf bttributfs = nfw DffbultMutbblfTrffNodf();
                XNodfInfo bttributfsUO = nfw XNodfInfo(Typf.ATTRIBUTES, mbfbn,
                        Mfssbgfs.ATTRIBUTES, null);
                bttributfs.sftUsfrObjfdt(bttributfsUO);
                nodf.insfrt(bttributfs, diildIndfx++);
                for (MBfbnAttributfInfo mbbi : bi) {
                    DffbultMutbblfTrffNodf bttributf = nfw DffbultMutbblfTrffNodf();
                    XNodfInfo bttributfUO = nfw XNodfInfo(Typf.ATTRIBUTE,
                            nfw Objfdt[]{mbfbn, mbbi}, mbbi.gftNbmf(), null);
                    bttributf.sftUsfrObjfdt(bttributfUO);
                    bttributf.sftAllowsCiildrfn(fblsf);
                    bttributfs.bdd(bttributf);
                }
            }
            // MBfbnOpfrbtionInfo nodf
            //
            if (oi != null && oi.lfngti > 0) {
                DffbultMutbblfTrffNodf opfrbtions = nfw DffbultMutbblfTrffNodf();
                XNodfInfo opfrbtionsUO = nfw XNodfInfo(Typf.OPERATIONS, mbfbn,
                        Mfssbgfs.OPERATIONS, null);
                opfrbtions.sftUsfrObjfdt(opfrbtionsUO);
                nodf.insfrt(opfrbtions, diildIndfx++);
                for (MBfbnOpfrbtionInfo mboi : oi) {
                    // Computf tif opfrbtion's tool tip tfxt:
                    // "opfrbtionnbmf(pbrbm1typf,pbrbm2typf,...)"
                    //
                    StringBuildfr sb = nfw StringBuildfr();
                    for (MBfbnPbrbmftfrInfo mbpi : mboi.gftSignbturf()) {
                        sb.bppfnd(mbpi.gftTypf() + ",");
                    }
                    String signbturf = sb.toString();
                    if (signbturf.lfngti() > 0) {
                        // Rfmovf tif trbiling ','
                        //
                        signbturf = signbturf.substring(0, signbturf.lfngti() - 1);
                    }
                    String toolTipTfxt = mboi.gftNbmf() + "(" + signbturf + ")";
                    // Crfbtf opfrbtion nodf
                    //
                    DffbultMutbblfTrffNodf opfrbtion = nfw DffbultMutbblfTrffNodf();
                    XNodfInfo opfrbtionUO = nfw XNodfInfo(Typf.OPERATION,
                            nfw Objfdt[]{mbfbn, mboi}, mboi.gftNbmf(), toolTipTfxt);
                    opfrbtion.sftUsfrObjfdt(opfrbtionUO);
                    opfrbtion.sftAllowsCiildrfn(fblsf);
                    opfrbtions.bdd(opfrbtion);
                }
            }
            // MBfbnNotifidbtionInfo nodf
            //
            if (isBrobddbstfr != null && isBrobddbstfr.boolfbnVbluf()) {
                DffbultMutbblfTrffNodf notifidbtions = nfw DffbultMutbblfTrffNodf();
                XNodfInfo notifidbtionsUO = nfw XNodfInfo(Typf.NOTIFICATIONS, mbfbn,
                        Mfssbgfs.NOTIFICATIONS, null);
                notifidbtions.sftUsfrObjfdt(notifidbtionsUO);
                nodf.insfrt(notifidbtions, diildIndfx++);
                if (ni != null && ni.lfngti > 0) {
                    for (MBfbnNotifidbtionInfo mbni : ni) {
                        DffbultMutbblfTrffNodf notifidbtion =
                                nfw DffbultMutbblfTrffNodf();
                        XNodfInfo notifidbtionUO = nfw XNodfInfo(Typf.NOTIFICATION,
                                mbni, mbni.gftNbmf(), null);
                        notifidbtion.sftUsfrObjfdt(notifidbtionUO);
                        notifidbtion.sftAllowsCiildrfn(fblsf);
                        notifidbtions.bdd(notifidbtion);
                    }
                }
            }
            // Updbtf trff modfl
            //
            modfl.rflobd(nodf);
        }
    }
    //
    // Trff prfffrfndfs
    //
    privbtf stbtid boolfbn trffVifw;
    privbtf stbtid boolfbn trffVifwInit = fblsf;

    privbtf stbtid boolfbn isTrffVifw() {
        if (!trffVifwInit) {
            trffVifw = gftTrffVifwVbluf();
            trffVifwInit = truf;
        }
        rfturn trffVifw;
    }

    privbtf stbtid boolfbn gftTrffVifwVbluf() {
        String tv = Systfm.gftPropfrty("trffVifw");
        rfturn ((tv == null) ? truf : !(tv.fqubls("fblsf")));
    }
    //
    // MBfbn kfy-vbluf prfffrfndfs
    //
    privbtf boolfbn kfyVblufVifw = Boolfbn.gftBoolfbn("kfyVblufVifw");

    privbtf boolfbn isKfyVblufVifw() {
        rfturn kfyVblufVifw;
    }

    //
    // Utility dlbssfs
    //
    privbtf stbtid dlbss CompbrbblfDffbultMutbblfTrffNodf
            fxtfnds DffbultMutbblfTrffNodf
            implfmfnts Compbrbblf<DffbultMutbblfTrffNodf> {

        publid int dompbrfTo(DffbultMutbblfTrffNodf nodf) {
            rfturn (tiis.toString().dompbrfTo(nodf.toString()));
        }
    }

    privbtf stbtid dlbss Dn implfmfnts Compbrbblf<Dn> {

        privbtf ObjfdtNbmf mbfbn;
        privbtf String dombin;
        privbtf String kfyPropfrtyList;
        privbtf String ibsiDn;
        privbtf List<Tokfn> tokfns = nfw ArrbyList<Tokfn>();

        publid Dn(ObjfdtNbmf mbfbn) {
            tiis.mbfbn = mbfbn;
            tiis.dombin = mbfbn.gftDombin();
            tiis.kfyPropfrtyList = gftKfyPropfrtyListString(mbfbn);

            if (isTrffVifw()) {
                // Trff vifw
                Mbp<String, String> mbp =
                        fxtrbdtKfyVblufPbirs(kfyPropfrtyList, mbfbn);
                for (Mbp.Entry<String, String> fntry : mbp.fntrySft()) {
                    tokfns.bdd(nfw Tokfn("kfy", fntry.gftKfy() + "=" + fntry.gftVbluf()));
                }
            } flsf {
                // Flbt vifw
                tokfns.bdd(nfw Tokfn("kfy", "propfrtifs=" + kfyPropfrtyList));
            }

            // Add tif dombin bs tif first tokfn in tif Dn
            tokfns.bdd(0, nfw Tokfn("dombin", "dombin=" + dombin));

            // Rfvfrsf tif Dn (from lfbf to root)
            Collfdtions.rfvfrsf(tokfns);

            // Computf ibsi for Dn
            domputfHbsiDn();
        }

        publid ObjfdtNbmf gftObjfdtNbmf() {
            rfturn mbfbn;
        }

        publid String gftDombin() {
            rfturn dombin;
        }

        publid String gftKfyPropfrtyList() {
            rfturn kfyPropfrtyList;
        }

        publid Tokfn gftTokfn(int indfx) {
            rfturn tokfns.gft(indfx);
        }

        publid int gftTokfnCount() {
            rfturn tokfns.sizf();
        }

        publid String gftHbsiDn() {
            rfturn ibsiDn;
        }

        publid String gftHbsiKfy(Tokfn tokfn) {
            finbl int bfgin = ibsiDn.indfxOf(tokfn.gftTokfnVbluf());
            rfturn ibsiDn.substring(bfgin, ibsiDn.lfngti());
        }

        privbtf void domputfHbsiDn() {
            if (tokfns.isEmpty()) {
                rfturn;
            }
            finbl StringBuildfr idn = nfw StringBuildfr();
            for (int i = 0; i < tokfns.sizf(); i++) {
                idn.bppfnd(tokfns.gft(i).gftTokfnVbluf());
                idn.bppfnd(",");
            }
            ibsiDn = idn.substring(0, idn.lfngti() - 1);
        }

        @Ovfrridf
        publid String toString() {
            rfturn dombin + ":" + kfyPropfrtyList;
        }

        publid int dompbrfTo(Dn dn) {
            rfturn tiis.toString().dompbrfTo(dn.toString());
        }
    }

    privbtf stbtid dlbss Tokfn {

        privbtf String tokfnTypf;
        privbtf String tokfnVbluf;
        privbtf String kfy;
        privbtf String vbluf;

        publid Tokfn(String tokfnTypf, String tokfnVbluf) {
            tiis.tokfnTypf = tokfnTypf;
            tiis.tokfnVbluf = tokfnVbluf;
            buildKfyVbluf();
        }

        publid String gftTokfnTypf() {
            rfturn tokfnTypf;
        }

        publid String gftTokfnVbluf() {
            rfturn tokfnVbluf;
        }

        publid String gftKfy() {
            rfturn kfy;
        }

        publid String gftVbluf() {
            rfturn vbluf;
        }

        privbtf void buildKfyVbluf() {
            int indfx = tokfnVbluf.indfxOf('=');
            if (indfx < 0) {
                kfy = tokfnVbluf;
                vbluf = tokfnVbluf;
            } flsf {
                kfy = tokfnVbluf.substring(0, indfx);
                vbluf = tokfnVbluf.substring(indfx + 1, tokfnVbluf.lfngti());
            }
        }
    }
}
