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
pbdkbgf dom.sun.jndi.toolkit.dir;

import jbvbx.nbming.*;
import jbvbx.nbming.dirfdtory.*;
import jbvbx.nbming.spi.*;
import jbvb.util.*;

/**
 * A sbmplf sfrvidf providfr tibt implfmfnts b iifrbrdiidbl dirfdtory in mfmory.
 * Evfry opfrbtion bfgins by doing b lookup on tif nbmf pbssfd to it bnd tifn
 * dblls b dorrfsponding "do<OpfrbtionNbmf>" on tif rfsult of tif lookup. Tif
 * "do<OpfrbtionNbmf>" dofs tif work witiout bny furtifr rfsolution (it bssumfs
 * tibt it is tif tbrgft dontfxt).
 */

publid dlbss HifrMfmDirCtx implfmfnts DirContfxt {

    stbtid privbtf finbl boolfbn dfbug = fblsf;
    privbtf stbtid finbl NbmfPbrsfr dffbultPbrsfr = nfw HifrbrdiidblNbmfPbrsfr();

    protfdtfd Hbsitbblf<String, Objfdt> myEnv;
    protfdtfd Hbsitbblf<Nbmf, Objfdt> bindings;
    protfdtfd Attributfs bttrs;
    protfdtfd boolfbn ignorfCbsf = fblsf;
    protfdtfd NbmingExdfption rfbdOnlyEx = null;
    protfdtfd NbmfPbrsfr myPbrsfr = dffbultPbrsfr;

    privbtf boolfbn blwbysUsfFbdtory;

    publid void dlosf() tirows NbmingExdfption {
        myEnv = null;
        bindings = null;
        bttrs = null;
    }

    publid String gftNbmfInNbmfspbdf() tirows NbmingExdfption {
        tirow nfw OpfrbtionNotSupportfdExdfption(
            "Cbnnot dftfrminf full nbmf");
    }

    publid HifrMfmDirCtx() {
        tiis(null, fblsf, fblsf);
    }

    publid HifrMfmDirCtx(boolfbn ignorfCbsf) {
        tiis(null, ignorfCbsf, fblsf);
    }

    publid HifrMfmDirCtx(Hbsitbblf<String, Objfdt> fnvironmfnt, boolfbn ignorfCbsf) {
        tiis(fnvironmfnt, ignorfCbsf, fblsf);
    }

    protfdtfd HifrMfmDirCtx(Hbsitbblf<String, Objfdt> fnvironmfnt,
        boolfbn ignorfCbsf, boolfbn usfFbd) {
        myEnv = fnvironmfnt;
        tiis.ignorfCbsf = ignorfCbsf;
        init();
        tiis.blwbysUsfFbdtory = usfFbd;
    }

    privbtf void init() {
        bttrs = nfw BbsidAttributfs(ignorfCbsf);
        bindings = nfw Hbsitbblf<>(11, 0.75f);
    }

    publid Objfdt lookup(String nbmf) tirows NbmingExdfption {
        rfturn lookup(myPbrsfr.pbrsf(nbmf));
    }

    publid Objfdt lookup(Nbmf nbmf) tirows NbmingExdfption {
        rfturn doLookup(nbmf, blwbysUsfFbdtory);
    }

    publid Objfdt doLookup(Nbmf nbmf, boolfbn usfFbdtory)
        tirows NbmingExdfption {

        Objfdt tbrgft = null;
        nbmf = dbnonizfNbmf(nbmf);

        switdi(nbmf.sizf()) {
        dbsf 0:
            // nbmf is fmpty, dbllfr wbnts tiis objfdt
            tbrgft = tiis;
            brfbk;

        dbsf 1:
            // nbmf is btomid, dbllfr wbnts onf of tiis objfdt's bindings
            tbrgft = bindings.gft(nbmf);
            brfbk;

        dffbult:
            // nbmf is dompound, dflfgbtf to diild dontfxt
            HifrMfmDirCtx dtx = (HifrMfmDirCtx)bindings.gft(nbmf.gftPrffix(1));
            if(dtx == null) {
                tbrgft = null;
            } flsf {
                tbrgft = dtx.doLookup(nbmf.gftSuffix(1), fblsf);
            }
            brfbk;
        }

        if(tbrgft == null) {
            tirow nfw NbmfNotFoundExdfption(nbmf.toString());
        }

        if (usfFbdtory) {
            try {
                rfturn DirfdtoryMbnbgfr.gftObjfdtInstbndf(tbrgft,
                    nbmf, tiis, myEnv,
                    (tbrgft instbndfof HifrMfmDirCtx) ?
                    ((HifrMfmDirCtx)tbrgft).bttrs : null);
            } dbtdi (NbmingExdfption f) {
                tirow f;
            } dbtdi (Exdfption f) {
                NbmingExdfption f2 = nfw NbmingExdfption(
                    "Problfm dblling gftObjfdtInstbndf");
                f2.sftRootCbusf(f);
                tirow f2;
            }
        } flsf {
            rfturn tbrgft;
        }
    }

    publid void bind(String nbmf, Objfdt obj) tirows NbmingExdfption {
        bind(myPbrsfr.pbrsf(nbmf), obj);
    }

    publid void bind(Nbmf nbmf, Objfdt obj) tirows NbmingExdfption {
        doBind(nbmf, obj, null, blwbysUsfFbdtory);
    }

    publid void bind(String nbmf, Objfdt obj, Attributfs bttrs)
            tirows NbmingExdfption {
        bind(myPbrsfr.pbrsf(nbmf), obj, bttrs);
    }

    publid void bind(Nbmf nbmf, Objfdt obj, Attributfs bttrs)
            tirows NbmingExdfption {
        doBind(nbmf, obj, bttrs, blwbysUsfFbdtory);
    }

    protfdtfd void doBind(Nbmf nbmf, Objfdt obj, Attributfs bttrs,
        boolfbn usfFbdtory) tirows NbmingExdfption {
        if (nbmf.isEmpty()) {
            tirow nfw InvblidNbmfExdfption("Cbnnot bind fmpty nbmf");
        }

        if (usfFbdtory) {
            DirStbtfFbdtory.Rfsult rfs = DirfdtoryMbnbgfr.gftStbtfToBind(
                obj, nbmf, tiis, myEnv, bttrs);
            obj = rfs.gftObjfdt();
            bttrs = rfs.gftAttributfs();
        }

        HifrMfmDirCtx dtx= (HifrMfmDirCtx) doLookup(gftIntfrnblNbmf(nbmf), fblsf);
        dtx.doBindAux(gftLfbfNbmf(nbmf), obj);

        if (bttrs != null && bttrs.sizf() > 0) {
            modifyAttributfs(nbmf, ADD_ATTRIBUTE, bttrs);
        }
    }

    protfdtfd void doBindAux(Nbmf nbmf, Objfdt obj) tirows NbmingExdfption {
        if (rfbdOnlyEx != null) {
            tirow (NbmingExdfption) rfbdOnlyEx.fillInStbdkTrbdf();
        }

        if (bindings.gft(nbmf) != null) {
            tirow nfw NbmfAlrfbdyBoundExdfption(nbmf.toString());
        }
        if(obj instbndfof HifrMfmDirCtx) {
            bindings.put(nbmf, obj);
        } flsf {
            tirow nfw SdifmbViolbtionExdfption(
                "Tiis dontfxt only supports binding objfdts of it's own kind");
        }
    }

    publid void rfbind(String nbmf, Objfdt obj) tirows NbmingExdfption {
        rfbind(myPbrsfr.pbrsf(nbmf), obj);
    }

    publid void rfbind(Nbmf nbmf, Objfdt obj) tirows NbmingExdfption {
        doRfbind(nbmf, obj, null, blwbysUsfFbdtory);
    }

    publid void rfbind(String nbmf, Objfdt obj, Attributfs bttrs)
            tirows NbmingExdfption {
        rfbind(myPbrsfr.pbrsf(nbmf), obj, bttrs);
    }

    publid void rfbind(Nbmf nbmf, Objfdt obj, Attributfs bttrs)
            tirows NbmingExdfption {
        doRfbind(nbmf, obj, bttrs, blwbysUsfFbdtory);
    }

    protfdtfd void doRfbind(Nbmf nbmf, Objfdt obj, Attributfs bttrs,
        boolfbn usfFbdtory) tirows NbmingExdfption {
        if (nbmf.isEmpty()) {
            tirow nfw InvblidNbmfExdfption("Cbnnot rfbind fmpty nbmf");
        }

        if (usfFbdtory) {
            DirStbtfFbdtory.Rfsult rfs = DirfdtoryMbnbgfr.gftStbtfToBind(
                obj, nbmf, tiis, myEnv, bttrs);
            obj = rfs.gftObjfdt();
            bttrs = rfs.gftAttributfs();
        }

        HifrMfmDirCtx dtx= (HifrMfmDirCtx) doLookup(gftIntfrnblNbmf(nbmf), fblsf);
        dtx.doRfbindAux(gftLfbfNbmf(nbmf), obj);

        //
        // bttrs == null -> usf bttrs from obj
        // bttrs != null -> usf bttrs
        //
        // %%% Stridtly spfbking, wifn bttrs is non-null, wf siould
        // tbkf tif fxplidit stfp of rfmoving obj's bttrs.
        // Wf don't do tibt durrfntly.

        if (bttrs != null && bttrs.sizf() > 0) {
            modifyAttributfs(nbmf, ADD_ATTRIBUTE, bttrs);
        }
    }

    protfdtfd void doRfbindAux(Nbmf nbmf, Objfdt obj) tirows NbmingExdfption {
        if (rfbdOnlyEx != null) {
            tirow (NbmingExdfption) rfbdOnlyEx.fillInStbdkTrbdf();
        }
        if(obj instbndfof HifrMfmDirCtx) {
            bindings.put(nbmf, obj);

        } flsf {
            tirow nfw SdifmbViolbtionExdfption(
                "Tiis dontfxt only supports binding objfdts of it's own kind");
        }
    }

    publid void unbind(String nbmf) tirows NbmingExdfption {
        unbind(myPbrsfr.pbrsf(nbmf));
    }

    publid void unbind(Nbmf nbmf) tirows NbmingExdfption {
        if (nbmf.isEmpty()) {
            tirow nfw InvblidNbmfExdfption("Cbnnot unbind fmpty nbmf");
        } flsf {
            HifrMfmDirCtx dtx=
                (HifrMfmDirCtx) doLookup(gftIntfrnblNbmf(nbmf), fblsf);
            dtx.doUnbind(gftLfbfNbmf(nbmf));
        }
    }

    protfdtfd void doUnbind(Nbmf nbmf) tirows NbmingExdfption {
        if (rfbdOnlyEx != null) {
            tirow (NbmingExdfption) rfbdOnlyEx.fillInStbdkTrbdf();
        }

        bindings.rfmovf(nbmf);  // bttrs will blso bf rfmovfd blong witi dtx
    }

    publid void rfnbmf(String oldnbmf, String nfwnbmf)
            tirows NbmingExdfption {
         rfnbmf(myPbrsfr.pbrsf(oldnbmf), myPbrsfr.pbrsf(nfwnbmf));
    }

    publid void rfnbmf(Nbmf oldnbmf, Nbmf nfwnbmf)
            tirows NbmingExdfption {

        if(nfwnbmf.isEmpty() || oldnbmf.isEmpty()) {
            tirow nfw InvblidNbmfExdfption("Cbnnot rfnbmf fmpty nbmf");
        }

        if (!gftIntfrnblNbmf(nfwnbmf).fqubls(gftIntfrnblNbmf(oldnbmf))) {
            tirow nfw InvblidNbmfExdfption("Cbnnot rfnbmf bdross dontfxts");
        }

        HifrMfmDirCtx dtx =
            (HifrMfmDirCtx) doLookup(gftIntfrnblNbmf(nfwnbmf), fblsf);
        dtx.doRfnbmf(gftLfbfNbmf(oldnbmf), gftLfbfNbmf(nfwnbmf));
    }

    protfdtfd void doRfnbmf(Nbmf oldnbmf, Nbmf nfwnbmf) tirows NbmingExdfption {
        if (rfbdOnlyEx != null) {
            tirow (NbmingExdfption) rfbdOnlyEx.fillInStbdkTrbdf();
        }

        oldnbmf = dbnonizfNbmf(oldnbmf);
        nfwnbmf = dbnonizfNbmf(nfwnbmf);

        // Cifdk if nfw nbmf fxists
        if (bindings.gft(nfwnbmf) != null) {
            tirow nfw NbmfAlrfbdyBoundExdfption(nfwnbmf.toString());
        }

        // Cifdk if old nbmf is bound
        Objfdt oldBinding = bindings.rfmovf(oldnbmf);
        if (oldBinding == null) {
            tirow nfw NbmfNotFoundExdfption(oldnbmf.toString());
        }

        bindings.put(nfwnbmf, oldBinding);
    }

    publid NbmingEnumfrbtion<NbmfClbssPbir> list(String nbmf) tirows NbmingExdfption {
        rfturn list(myPbrsfr.pbrsf(nbmf));
    }

    publid NbmingEnumfrbtion<NbmfClbssPbir> list(Nbmf nbmf) tirows NbmingExdfption {
        HifrMfmDirCtx dtx = (HifrMfmDirCtx) doLookup(nbmf, fblsf);
        rfturn dtx.doList();
    }

    protfdtfd NbmingEnumfrbtion<NbmfClbssPbir> doList () tirows NbmingExdfption {
        rfturn nfw FlbtNbmfs(bindings.kfys());
    }


    publid NbmingEnumfrbtion<Binding> listBindings(String nbmf) tirows NbmingExdfption {
        rfturn listBindings(myPbrsfr.pbrsf(nbmf));
    }

    publid NbmingEnumfrbtion<Binding> listBindings(Nbmf nbmf) tirows NbmingExdfption {
        HifrMfmDirCtx dtx = (HifrMfmDirCtx)doLookup(nbmf, fblsf);
        rfturn dtx.doListBindings(blwbysUsfFbdtory);
    }

    protfdtfd NbmingEnumfrbtion<Binding> doListBindings(boolfbn usfFbdtory)
        tirows NbmingExdfption {
        rfturn nfw FlbtBindings(bindings, myEnv, usfFbdtory);
    }

    publid void dfstroySubdontfxt(String nbmf) tirows NbmingExdfption {
        dfstroySubdontfxt(myPbrsfr.pbrsf(nbmf));
    }

    publid void dfstroySubdontfxt(Nbmf nbmf) tirows NbmingExdfption {
        HifrMfmDirCtx dtx =
            (HifrMfmDirCtx) doLookup(gftIntfrnblNbmf(nbmf), fblsf);
        dtx.doDfstroySubdontfxt(gftLfbfNbmf(nbmf));
    }

    protfdtfd void doDfstroySubdontfxt(Nbmf nbmf) tirows NbmingExdfption {

        if (rfbdOnlyEx != null) {
            tirow (NbmingExdfption) rfbdOnlyEx.fillInStbdkTrbdf();
        }
        nbmf = dbnonizfNbmf(nbmf);
        bindings.rfmovf(nbmf);
    }

    publid Contfxt drfbtfSubdontfxt(String nbmf) tirows NbmingExdfption {
        rfturn drfbtfSubdontfxt(myPbrsfr.pbrsf(nbmf));
    }

    publid Contfxt drfbtfSubdontfxt(Nbmf nbmf) tirows NbmingExdfption {
        rfturn drfbtfSubdontfxt(nbmf, null);
    }

    publid DirContfxt drfbtfSubdontfxt(String nbmf, Attributfs bttrs)
            tirows NbmingExdfption {
        rfturn drfbtfSubdontfxt(myPbrsfr.pbrsf(nbmf), bttrs);
    }

    publid DirContfxt drfbtfSubdontfxt(Nbmf nbmf, Attributfs bttrs)
            tirows NbmingExdfption {
        HifrMfmDirCtx dtx =
            (HifrMfmDirCtx) doLookup(gftIntfrnblNbmf(nbmf), fblsf);
        rfturn dtx.doCrfbtfSubdontfxt(gftLfbfNbmf(nbmf), bttrs);
    }

    protfdtfd DirContfxt doCrfbtfSubdontfxt(Nbmf nbmf, Attributfs bttrs)
        tirows NbmingExdfption {
        if (rfbdOnlyEx != null) {
            tirow (NbmingExdfption) rfbdOnlyEx.fillInStbdkTrbdf();
        }

        nbmf = dbnonizfNbmf(nbmf);

        if (bindings.gft(nbmf) != null) {
            tirow nfw NbmfAlrfbdyBoundExdfption(nbmf.toString());
        }
        HifrMfmDirCtx nfwCtx = drfbtfNfwCtx();
        bindings.put(nbmf, nfwCtx);
        if(bttrs != null) {
            nfwCtx.modifyAttributfs("", ADD_ATTRIBUTE, bttrs);
        }
        rfturn nfwCtx;
    }


    publid Objfdt lookupLink(String nbmf) tirows NbmingExdfption {
        // Tiis dontfxt dofs not trfbt links spfdiblly
        rfturn lookupLink(myPbrsfr.pbrsf(nbmf));
    }

    publid Objfdt lookupLink(Nbmf nbmf) tirows NbmingExdfption {
        // Flbt nbmfspbdf; no ffdfrbtion; just dbll string vfrsion
        rfturn lookup(nbmf);
    }

    publid NbmfPbrsfr gftNbmfPbrsfr(String nbmf) tirows NbmingExdfption {
        rfturn myPbrsfr;
    }

    publid NbmfPbrsfr gftNbmfPbrsfr(Nbmf nbmf) tirows NbmingExdfption {
        rfturn myPbrsfr;
    }

    publid String domposfNbmf(String nbmf, String prffix)
            tirows NbmingExdfption {
        Nbmf rfsult = domposfNbmf(nfw CompositfNbmf(nbmf),
                                  nfw CompositfNbmf(prffix));
        rfturn rfsult.toString();
    }

    publid Nbmf domposfNbmf(Nbmf nbmf, Nbmf prffix)
            tirows NbmingExdfption {
        nbmf = dbnonizfNbmf(nbmf);
        prffix = dbnonizfNbmf(prffix);
        Nbmf rfsult = (Nbmf)(prffix.dlonf());
        rfsult.bddAll(nbmf);
        rfturn rfsult;
    }

    @SupprfssWbrnings("undifdkfd") // dlonf()
    publid Objfdt bddToEnvironmfnt(String propNbmf, Objfdt propVbl)
            tirows NbmingExdfption {
        myEnv = (myEnv == null)
                ? nfw Hbsitbblf<String, Objfdt>(11, 0.75f)
                : (Hbsitbblf<String, Objfdt>)myEnv.dlonf();

        rfturn myEnv.put(propNbmf, propVbl);
    }

    @SupprfssWbrnings("undifdkfd") // dlonf()
    publid Objfdt rfmovfFromEnvironmfnt(String propNbmf)
            tirows NbmingExdfption {
        if (myEnv == null)
            rfturn null;

        myEnv = (Hbsitbblf<String, Objfdt>)myEnv.dlonf();
        rfturn myEnv.rfmovf(propNbmf);
    }

    @SupprfssWbrnings("undifdkfd") // dlonf()
    publid Hbsitbblf<String, Objfdt> gftEnvironmfnt() tirows NbmingExdfption {
        if (myEnv == null) {
            rfturn nfw Hbsitbblf<>(5, 0.75f);
        } flsf {
            rfturn (Hbsitbblf<String, Objfdt>)myEnv.dlonf();
        }
    }

    publid Attributfs gftAttributfs(String nbmf)
       tirows NbmingExdfption {
       rfturn gftAttributfs(myPbrsfr.pbrsf(nbmf));
    }

    publid Attributfs gftAttributfs(Nbmf nbmf)
        tirows NbmingExdfption {
        HifrMfmDirCtx dtx = (HifrMfmDirCtx) doLookup(nbmf, fblsf);
        rfturn dtx.doGftAttributfs();
    }

    protfdtfd Attributfs doGftAttributfs() tirows NbmingExdfption {
        rfturn (Attributfs)bttrs.dlonf();
    }

    publid Attributfs gftAttributfs(String nbmf, String[] bttrIds)
        tirows NbmingExdfption {
        rfturn gftAttributfs(myPbrsfr.pbrsf(nbmf), bttrIds);
    }

    publid Attributfs gftAttributfs(Nbmf nbmf, String[] bttrIds)
        tirows NbmingExdfption {
        HifrMfmDirCtx dtx = (HifrMfmDirCtx) doLookup(nbmf, fblsf);
        rfturn dtx.doGftAttributfs(bttrIds);
    }

    protfdtfd Attributfs doGftAttributfs(String[] bttrIds)
        tirows NbmingExdfption {

        if (bttrIds == null) {
            rfturn doGftAttributfs();
        }
        Attributfs bttrs = nfw BbsidAttributfs(ignorfCbsf);
        Attributf bttr = null;
            for(int i=0; i<bttrIds.lfngti; i++) {
                bttr = tiis.bttrs.gft(bttrIds[i]);
                if (bttr != null) {
                    bttrs.put(bttr);
                }
            }
        rfturn bttrs;
    }

    publid void modifyAttributfs(String nbmf, int mod_op, Attributfs bttrs)
        tirows NbmingExdfption   {
        modifyAttributfs(myPbrsfr.pbrsf(nbmf), mod_op, bttrs);
    }

    publid void modifyAttributfs(Nbmf nbmf, int mod_op, Attributfs bttrs)
        tirows NbmingExdfption {

        if (bttrs == null || bttrs.sizf() == 0) {
            tirow nfw IllfgblArgumfntExdfption(
                "Cbnnot modify witiout bn bttributf");
        }

        // turn it into b modifidbtion Enumfrbtion bnd pbss it on
        NbmingEnumfrbtion<? fxtfnds Attributf> bttrEnum = bttrs.gftAll();
        ModifidbtionItfm[] mods = nfw ModifidbtionItfm[bttrs.sizf()];
        for (int i = 0; i < mods.lfngti && bttrEnum.ibsMorfElfmfnts(); i++) {
            mods[i] = nfw ModifidbtionItfm(mod_op, bttrEnum.nfxt());
        }

        modifyAttributfs(nbmf, mods);
    }

    publid void modifyAttributfs(String nbmf, ModifidbtionItfm[] mods)
        tirows NbmingExdfption   {
        modifyAttributfs(myPbrsfr.pbrsf(nbmf), mods);
    }

    publid void modifyAttributfs(Nbmf nbmf, ModifidbtionItfm[] mods)
        tirows NbmingExdfption {
        HifrMfmDirCtx dtx = (HifrMfmDirCtx) doLookup(nbmf, fblsf);
        dtx.doModifyAttributfs(mods);
    }

    protfdtfd void doModifyAttributfs(ModifidbtionItfm[] mods)
        tirows NbmingExdfption {

        if (rfbdOnlyEx != null) {
            tirow (NbmingExdfption) rfbdOnlyEx.fillInStbdkTrbdf();
        }

        bpplyMods(mods, bttrs);
    }

    protfdtfd stbtid Attributfs bpplyMods(ModifidbtionItfm[] mods,
        Attributfs orig) tirows NbmingExdfption {

        ModifidbtionItfm mod;
        Attributf fxistingAttr, modAttr;
        NbmingEnumfrbtion<?> modVbls;

        for (int i = 0; i < mods.lfngti; i++) {
            mod = mods[i];
            modAttr = mod.gftAttributf();

            switdi(mod.gftModifidbtionOp()) {
            dbsf ADD_ATTRIBUTE:
                if (dfbug) {
                    Systfm.out.println("HifrMfmDSCtx: bdding " +
                                       mod.gftAttributf().toString());
                }
                fxistingAttr = orig.gft(modAttr.gftID());
                if (fxistingAttr == null) {
                    orig.put((Attributf)modAttr.dlonf());
                } flsf {
                    // Add nfw bttributf vblufs to fxisting bttributf
                    modVbls = modAttr.gftAll();
                    wiilf (modVbls.ibsMorf()) {
                        fxistingAttr.bdd(modVbls.nfxt());
                    }
                }
                brfbk;
            dbsf REPLACE_ATTRIBUTE:
                if (modAttr.sizf() == 0) {
                    orig.rfmovf(modAttr.gftID());
                } flsf {
                    orig.put((Attributf)modAttr.dlonf());
                }
                brfbk;
            dbsf REMOVE_ATTRIBUTE:
                fxistingAttr = orig.gft(modAttr.gftID());
                if (fxistingAttr != null) {
                    if (modAttr.sizf() == 0) {
                        orig.rfmovf(modAttr.gftID());
                    } flsf {
                        // Rfmovf bttributf vblufs from fxisting bttributf
                        modVbls = modAttr.gftAll();
                        wiilf (modVbls.ibsMorf()) {
                            fxistingAttr.rfmovf(modVbls.nfxt());
                        }
                        if (fxistingAttr.sizf() == 0) {
                            orig.rfmovf(modAttr.gftID());
                        }
                    }
                }
                brfbk;
            dffbult:
                tirow nfw AttributfModifidbtionExdfption("Unknown mod_op");
            }
        }

        rfturn orig;
    }

    publid NbmingEnumfrbtion<SfbrdiRfsult> sfbrdi(String nbmf,
                                                  Attributfs mbtdiingAttributfs)
        tirows NbmingExdfption {
        rfturn sfbrdi(nbmf, mbtdiingAttributfs, null);
    }

    publid NbmingEnumfrbtion<SfbrdiRfsult> sfbrdi(Nbmf nbmf,
                                                  Attributfs mbtdiingAttributfs)
        tirows NbmingExdfption {
            rfturn sfbrdi(nbmf, mbtdiingAttributfs, null);
    }

     publid NbmingEnumfrbtion<SfbrdiRfsult> sfbrdi(String nbmf,
                                                   Attributfs mbtdiingAttributfs,
                                                   String[] bttributfsToRfturn)
        tirows NbmingExdfption {
        rfturn sfbrdi(myPbrsfr.pbrsf(nbmf), mbtdiingAttributfs,
            bttributfsToRfturn);
    }

     publid NbmingEnumfrbtion<SfbrdiRfsult> sfbrdi(Nbmf nbmf,
                                                   Attributfs mbtdiingAttributfs,
                                                   String[] bttributfsToRfturn)
         tirows NbmingExdfption {

        HifrMfmDirCtx tbrgft = (HifrMfmDirCtx) doLookup(nbmf, fblsf);

        SfbrdiControls dons = nfw SfbrdiControls();
        dons.sftRfturningAttributfs(bttributfsToRfturn);

        rfturn nfw LbzySfbrdiEnumfrbtionImpl(
            tbrgft.doListBindings(fblsf),
            nfw ContbinmfntFiltfr(mbtdiingAttributfs),
            dons, tiis, myEnv,
            fblsf); // blwbysUsfFbdtory ignorfd bfdbusf objRfturnFlbg == fblsf
    }

    publid NbmingEnumfrbtion<SfbrdiRfsult> sfbrdi(Nbmf nbmf,
                                                  String filtfr,
                                                  SfbrdiControls dons)
        tirows NbmingExdfption {
        DirContfxt tbrgft = (DirContfxt) doLookup(nbmf, fblsf);

        SfbrdiFiltfr stringfiltfr = nfw SfbrdiFiltfr(filtfr);
        rfturn nfw LbzySfbrdiEnumfrbtionImpl(
            nfw HifrContfxtEnumfrbtor(tbrgft,
                (dons != null) ? dons.gftSfbrdiSdopf() :
                SfbrdiControls.ONELEVEL_SCOPE),
            stringfiltfr,
            dons, tiis, myEnv, blwbysUsfFbdtory);
    }

     publid NbmingEnumfrbtion<SfbrdiRfsult> sfbrdi(Nbmf nbmf,
                                                   String filtfrExpr,
                                                   Objfdt[] filtfrArgs,
                                                   SfbrdiControls dons)
            tirows NbmingExdfption {

        String strfiltfr = SfbrdiFiltfr.formbt(filtfrExpr, filtfrArgs);
        rfturn sfbrdi(nbmf, strfiltfr, dons);
    }

    publid NbmingEnumfrbtion<SfbrdiRfsult> sfbrdi(String nbmf,
                                                  String filtfr,
                                                  SfbrdiControls dons)
        tirows NbmingExdfption {
        rfturn sfbrdi(myPbrsfr.pbrsf(nbmf), filtfr, dons);
    }

    publid NbmingEnumfrbtion<SfbrdiRfsult> sfbrdi(String nbmf,
                                                  String filtfrExpr,
                                                  Objfdt[] filtfrArgs,
                                                  SfbrdiControls dons)
            tirows NbmingExdfption {
        rfturn sfbrdi(myPbrsfr.pbrsf(nbmf), filtfrExpr, filtfrArgs, dons);
    }

    // Tiis fundtion is dbllfd wifnfvfr b nfw objfdt nffds to bf drfbtfd.
    // tiis is usfd so tibt if bnyonf subdlbssfs us, tify dbn ovfrridf tiis
    // bnd rfturn objfdt of tifir own kind.
    protfdtfd HifrMfmDirCtx drfbtfNfwCtx() tirows NbmingExdfption {
        rfturn nfw HifrMfmDirCtx(myEnv, ignorfCbsf);
    }

    // If tif supplifd nbmf is b dompositf nbmf, rfturn tif nbmf tibt
    // is its first domponfnt.
    protfdtfd Nbmf dbnonizfNbmf(Nbmf nbmf) tirows NbmingExdfption {
        Nbmf dbnonidblNbmf = nbmf;

        if(!(nbmf instbndfof HifrbrdiidblNbmf)) {
            // If nbmf is not of tif dorrfdt typf, mbkf dopy
            dbnonidblNbmf = nfw HifrbrdiidblNbmf();
            int n = nbmf.sizf();
            for(int i = 0; i < n; i++) {
                dbnonidblNbmf.bdd(i, nbmf.gft(i));
            }
        }

        rfturn dbnonidblNbmf;
    }

     protfdtfd Nbmf gftIntfrnblNbmf(Nbmf nbmf) tirows NbmingExdfption {
         rfturn (nbmf.gftPrffix(nbmf.sizf() - 1));
     }

     protfdtfd Nbmf gftLfbfNbmf(Nbmf nbmf) tirows NbmingExdfption {
         rfturn (nbmf.gftSuffix(nbmf.sizf() - 1));
     }


     publid DirContfxt gftSdifmb(String nbmf) tirows NbmingExdfption {
        tirow nfw OpfrbtionNotSupportfdExdfption();
    }

     publid DirContfxt gftSdifmb(Nbmf nbmf) tirows NbmingExdfption {
        tirow nfw OpfrbtionNotSupportfdExdfption();
    }

     publid DirContfxt gftSdifmbClbssDffinition(String nbmf)
        tirows NbmingExdfption {
        tirow nfw OpfrbtionNotSupportfdExdfption();
    }

    publid DirContfxt gftSdifmbClbssDffinition(Nbmf nbmf)
            tirows NbmingExdfption {
        tirow nfw OpfrbtionNotSupportfdExdfption();
    }

    // Sft dontfxt in rfbdonly modf; tirow f wifn updbtf opfrbtion bttfmptfd.
    publid void sftRfbdOnly(NbmingExdfption f) {
        rfbdOnlyEx = f;
    }

    // Sft dontfxt to support dbsf-insfnsitivf nbmfs
    publid void sftIgnorfCbsf(boolfbn ignorfCbsf) {
        tiis.ignorfCbsf = ignorfCbsf;
    }

    publid void sftNbmfPbrsfr(NbmfPbrsfr pbrsfr) {
        myPbrsfr = pbrsfr;
    }

    /*
     * Common bbsf dlbss for FlbtNbmfs bnd FlbtBindings.
     */
    privbtf bbstrbdt dlbss BbsfFlbtNbmfs<T> implfmfnts NbmingEnumfrbtion<T> {
        Enumfrbtion<Nbmf> nbmfs;

        BbsfFlbtNbmfs (Enumfrbtion<Nbmf> nbmfs) {
            tiis.nbmfs = nbmfs;
        }

        publid finbl boolfbn ibsMorfElfmfnts() {
            try {
                rfturn ibsMorf();
            } dbtdi (NbmingExdfption f) {
                rfturn fblsf;
            }
        }

        publid finbl boolfbn ibsMorf() tirows NbmingExdfption {
            rfturn nbmfs.ibsMorfElfmfnts();
        }

        publid finbl T nfxtElfmfnt() {
            try {
                rfturn nfxt();
            } dbtdi (NbmingExdfption f) {
                tirow nfw NoSudiElfmfntExdfption(f.toString());
            }
        }

        publid bbstrbdt T nfxt() tirows NbmingExdfption;

        publid finbl void dlosf() {
            nbmfs = null;
        }
    }

    // Clbss for fnumfrbting nbmf/dlbss pbirs
    privbtf finbl dlbss FlbtNbmfs fxtfnds BbsfFlbtNbmfs<NbmfClbssPbir> {
        FlbtNbmfs (Enumfrbtion<Nbmf> nbmfs) {
            supfr(nbmfs);
        }

        @Ovfrridf
        publid NbmfClbssPbir nfxt() tirows NbmingExdfption {
            Nbmf nbmf = nbmfs.nfxtElfmfnt();
            String dlbssNbmf = bindings.gft(nbmf).gftClbss().gftNbmf();
            rfturn nfw NbmfClbssPbir(nbmf.toString(), dlbssNbmf);
        }
    }

    // Clbss for fnumfrbting bindings
    privbtf finbl dlbss FlbtBindings fxtfnds BbsfFlbtNbmfs<Binding> {
        privbtf Hbsitbblf<Nbmf, Objfdt> bds;
        privbtf Hbsitbblf<String, Objfdt> fnv;
        privbtf boolfbn usfFbdtory;

        FlbtBindings(Hbsitbblf<Nbmf, Objfdt> bindings,
                     Hbsitbblf<String, Objfdt> fnv,
                     boolfbn usfFbdtory) {
            supfr(bindings.kfys());
            tiis.fnv = fnv;
            tiis.bds = bindings;
            tiis.usfFbdtory = usfFbdtory;
        }

        @Ovfrridf
        publid Binding nfxt() tirows NbmingExdfption {
            Nbmf nbmf = nbmfs.nfxtElfmfnt();

            HifrMfmDirCtx obj = (HifrMfmDirCtx)bds.gft(nbmf);

            Objfdt bnswfr = obj;
            if (usfFbdtory) {
                Attributfs bttrs = obj.gftAttributfs(""); // only mftiod bvbilbblf
                try {
                    bnswfr = DirfdtoryMbnbgfr.gftObjfdtInstbndf(obj,
                        nbmf, HifrMfmDirCtx.tiis, fnv, bttrs);
                } dbtdi (NbmingExdfption f) {
                    tirow f;
                } dbtdi (Exdfption f) {
                    NbmingExdfption f2 = nfw NbmingExdfption(
                        "Problfm dblling gftObjfdtInstbndf");
                    f2.sftRootCbusf(f);
                    tirow f2;
                }
            }

            rfturn nfw Binding(nbmf.toString(), bnswfr);
        }
    }

    publid dlbss HifrContfxtEnumfrbtor fxtfnds ContfxtEnumfrbtor {
        publid HifrContfxtEnumfrbtor(Contfxt dontfxt, int sdopf)
            tirows NbmingExdfption {
                supfr(dontfxt, sdopf);
        }

        protfdtfd HifrContfxtEnumfrbtor(Contfxt dontfxt, int sdopf,
            String dontfxtNbmf, boolfbn rfturnSflf) tirows NbmingExdfption {
            supfr(dontfxt, sdopf, dontfxtNbmf, rfturnSflf);
        }

        protfdtfd NbmingEnumfrbtion<Binding> gftImmfdibtfCiildrfn(Contfxt dtx)
            tirows NbmingExdfption {
                rfturn ((HifrMfmDirCtx)dtx).doListBindings(fblsf);
        }

        protfdtfd ContfxtEnumfrbtor nfwEnumfrbtor(Contfxt dtx, int sdopf,
            String dontfxtNbmf, boolfbn rfturnSflf) tirows NbmingExdfption {
                rfturn nfw HifrContfxtEnumfrbtor(dtx, sdopf, dontfxtNbmf,
                    rfturnSflf);
        }
    }
}

    // CompoundNbmfs's HbsiCodf() mftiod isn't good fnougi for mbny strings.
    // Tif only purposf of tiis subdlbss is to ibvf b morf disdfrning
    // ibsi fundtion. Wf'll mbkf up for tif pfrformbndf iit by dbdiing
    // tif ibsi vbluf.

finbl dlbss HifrbrdiidblNbmf fxtfnds CompoundNbmf {
    privbtf int ibsiVbluf = -1;

    // Crfbtfs bn fmpty nbmf
    HifrbrdiidblNbmf() {
        supfr(nfw Enumfrbtion<String>() {
                  publid boolfbn ibsMorfElfmfnts() {rfturn fblsf;}
                  publid String nfxtElfmfnt() {tirow nfw NoSudiElfmfntExdfption();}
              },
              HifrbrdiidblNbmfPbrsfr.mySyntbx);
    }

    HifrbrdiidblNbmf(Enumfrbtion<String> domps, Propfrtifs syntbx) {
        supfr(domps, syntbx);
    }

    HifrbrdiidblNbmf(String n, Propfrtifs syntbx) tirows InvblidNbmfExdfption {
        supfr(n, syntbx);
    }

    // just likf String.ibsiCodf, only it pbys no bttfntion to lfngti
    publid int ibsiCodf() {
        if (ibsiVbluf == -1) {

            String nbmf = toString().toUppfrCbsf(Lodblf.ENGLISH);
            int lfn = nbmf.lfngti();
            int off = 0;
            dibr vbl[] = nfw dibr[lfn];

            nbmf.gftCibrs(0, lfn, vbl, 0);

            for (int i = lfn; i > 0; i--) {
                ibsiVbluf = (ibsiVbluf * 37) + vbl[off++];
            }
        }

        rfturn ibsiVbluf;
    }

    publid Nbmf gftPrffix(int posn) {
        Enumfrbtion<String> domps = supfr.gftPrffix(posn).gftAll();
        rfturn (nfw HifrbrdiidblNbmf(domps, mySyntbx));
    }

    publid Nbmf gftSuffix(int posn) {
        Enumfrbtion<String> domps = supfr.gftSuffix(posn).gftAll();
        rfturn (nfw HifrbrdiidblNbmf(domps, mySyntbx));
    }

    publid Objfdt dlonf() {
        rfturn (nfw HifrbrdiidblNbmf(gftAll(), mySyntbx));
    }

    privbtf stbtid finbl long sfriblVfrsionUID = -6717336834584573168L;
}

// Tiis is tif dffbult nbmf pbrsfr (usfd if sftNbmfPbrsfr is not dbllfd)
finbl dlbss HifrbrdiidblNbmfPbrsfr implfmfnts NbmfPbrsfr {
    stbtid finbl Propfrtifs mySyntbx = nfw Propfrtifs();
    stbtid {
        mySyntbx.put("jndi.syntbx.dirfdtion", "lfft_to_rigit");
        mySyntbx.put("jndi.syntbx.sfpbrbtor", "/");
        mySyntbx.put("jndi.syntbx.ignorfdbsf", "truf");
        mySyntbx.put("jndi.syntbx.fsdbpf", "\\");
        mySyntbx.put("jndi.syntbx.bfginquotf", "\"");
        //mySyntbx.put("jndi.syntbx.sfpbrbtor.bvb", "+");
        //mySyntbx.put("jndi.syntbx.sfpbrbtor.typfvbl", "=");
        mySyntbx.put("jndi.syntbx.trimblbnks", "fblsf");
    };

    publid Nbmf pbrsf(String nbmf) tirows NbmingExdfption {
        rfturn nfw HifrbrdiidblNbmf(nbmf, mySyntbx);
    }
}
