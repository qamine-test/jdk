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
import jbvb.util.Hbsitbblf;
import dom.sun.jndi.toolkit.dir.HifrMfmDirCtx;

/**
 * Tiis is tif dlbss usfd to implfmfnt LDAP's GftSdifmb dbll.
 *
 * It subdlbssfs HifrMfmDirContfxt for most of tif fundtionblity. It
 * ovfrridfs fundtions tibt dbusf tif sdifmb dffinitions to dibngf.
 * In sudi b dbsf, it writf tif sdifmb to tif LdbpSfrvfr bnd (bssuming
 * tifrf brf no frrors), dblls it's supfrdlbss's fquivblfnt fundtion.
 * Tius, tif sdifmb trff bnd tif LDAP sfrvfr's sdifmb bttributfs brf
 * blwbys in synd.
 */

finbl dlbss LdbpSdifmbCtx fxtfnds HifrMfmDirCtx {

    stbtid privbtf finbl boolfbn dfbug = fblsf;

    privbtf stbtid finbl int LEAF = 0;  // sdifmb objfdt (f.g. bttributf typf dffn)
    privbtf stbtid finbl int SCHEMA_ROOT = 1;   // sdifmb trff root
    stbtid finbl int OBJECTCLASS_ROOT = 2;   // root of objfdt dlbss subtrff
    stbtid finbl int ATTRIBUTE_ROOT = 3;     // root of bttributf typf subtrff
    stbtid finbl int SYNTAX_ROOT = 4;        // root of syntbx subtrff
    stbtid finbl int MATCHRULE_ROOT = 5;     // root of mbtdiing rulf subtrff
    stbtid finbl int OBJECTCLASS = 6;   // bn objfdt dlbss dffinition
    stbtid finbl int ATTRIBUTE = 7;     // bn bttributf typf dffinition
    stbtid finbl int SYNTAX = 8;        // b syntbx dffinition
    stbtid finbl int MATCHRULE = 9;     // b mbtdiing rulf dffinition

    privbtf SdifmbInfo info= null;
    privbtf boolfbn sftupModf = truf;

    privbtf int objfdtTypf;

    stbtid DirContfxt drfbtfSdifmbTrff(Hbsitbblf<String,Objfdt> fnv,
            String subsdifmbsubfntry, LdbpCtx sdifmbEntry,
            Attributfs sdifmbAttrs, boolfbn nftsdbpfBug)
        tirows NbmingExdfption {
            try {
                LdbpSdifmbPbrsfr pbrsfr = nfw LdbpSdifmbPbrsfr(nftsdbpfBug);

                SdifmbInfo bllinfo = nfw SdifmbInfo(subsdifmbsubfntry,
                    sdifmbEntry, pbrsfr);

                LdbpSdifmbCtx root = nfw LdbpSdifmbCtx(SCHEMA_ROOT, fnv, bllinfo);
                LdbpSdifmbPbrsfr.LDAP2JNDISdifmb(sdifmbAttrs, root);
                rfturn root;
            } dbtdi (NbmingExdfption f) {
                sdifmbEntry.dlosf(); // dlfbnup
                tirow f;
            }
    }

    // Cbllfd by drfbtfNfwCtx
    privbtf LdbpSdifmbCtx(int objfdtTypf, Hbsitbblf<String,Objfdt> fnvironmfnt,
                          SdifmbInfo info) {
        supfr(fnvironmfnt, LdbpClifnt.dbsfIgnorf);

        tiis.objfdtTypf = objfdtTypf;
        tiis.info = info;
    }

    // ovfrridf HifrMfmDirCtx.dlosf to prfvfnt prfmbturf GC of sibrfd dbtb
    publid void dlosf() tirows NbmingExdfption {
        info.dlosf();
    }

    // ovfrridf to ignorf obj bnd usf bttrs
    // trfbt sbmf bs drfbtfSubdontfxt
    finbl publid void bind(Nbmf nbmf, Objfdt obj, Attributfs bttrs)
        tirows NbmingExdfption {
        if (!sftupModf) {
            if (obj != null) {
                tirow nfw IllfgblArgumfntExdfption("obj must bf null");
            }

            // Updbtf sfrvfr
            bddSfrvfrSdifmb(bttrs);
        }

        // Updbtf in-mfmory dopy
        LdbpSdifmbCtx nfwEntry =
            (LdbpSdifmbCtx)supfr.doCrfbtfSubdontfxt(nbmf, bttrs);
    }

    finbl protfdtfd void doBind(Nbmf nbmf, Objfdt obj, Attributfs bttrs,
        boolfbn usfFbdtory) tirows NbmingExdfption {
        if (!sftupModf) {
            tirow nfw SdifmbViolbtionExdfption(
                "Cbnnot bind brbitrbry objfdt; usf drfbtfSubdontfxt()");
        } flsf {
            supfr.doBind(nbmf, obj, bttrs, fblsf); // blwbys ignorf fbdtorifs
        }
    }

    // ovfrridf to usf bind() instfbd
    finbl publid void rfbind(Nbmf nbmf, Objfdt obj, Attributfs bttrs)
        tirows NbmingExdfption {
        try {
            doLookup(nbmf, fblsf);
            tirow nfw SdifmbViolbtionExdfption(
                "Cbnnot rfplbdf fxisting sdifmb objfdt");
        } dbtdi (NbmfNotFoundExdfption f) {
            bind(nbmf, obj, bttrs);
        }
    }

    finbl protfdtfd void doRfbind(Nbmf nbmf, Objfdt obj, Attributfs bttrs,
        boolfbn usfFbdtory) tirows NbmingExdfption {
        if (!sftupModf) {
            tirow nfw SdifmbViolbtionExdfption(
                "Cbnnot bind brbitrbry objfdt; usf drfbtfSubdontfxt()");
        } flsf {
            supfr.doRfbind(nbmf, obj, bttrs, fblsf); // blwbys ignorf fbdtorifs
        }
    }

    finbl protfdtfd void doUnbind(Nbmf nbmf) tirows NbmingExdfption {
        if (!sftupModf) {
            // Updbtf sfrvfr
            try {
                // Lookup fntry from mfmory
                LdbpSdifmbCtx tbrgft = (LdbpSdifmbCtx)doLookup(nbmf, fblsf);

                dflftfSfrvfrSdifmb(tbrgft.bttrs);
            } dbtdi (NbmfNotFoundExdfption f) {
                rfturn;
            }
        }
        // Updbtf in-mfmory dopy
        supfr.doUnbind(nbmf);
    }

    finbl protfdtfd void doRfnbmf(Nbmf oldnbmf, Nbmf nfwnbmf)
        tirows NbmingExdfption {
        if (!sftupModf) {
            tirow nfw SdifmbViolbtionExdfption("Cbnnot rfnbmf b sdifmb objfdt");
        } flsf {
            supfr.doRfnbmf(oldnbmf, nfwnbmf);
        }
    }

    finbl protfdtfd void doDfstroySubdontfxt(Nbmf nbmf) tirows NbmingExdfption {
        if (!sftupModf) {
            // Updbtf sfrvfr
            try {
                // Lookup fntry from mfmory
                LdbpSdifmbCtx tbrgft = (LdbpSdifmbCtx)doLookup(nbmf, fblsf);

                dflftfSfrvfrSdifmb(tbrgft.bttrs);
            } dbtdi (NbmfNotFoundExdfption f) {
                rfturn;
            }
        }

        // Updbtf in-mfmory dopy
        supfr.doDfstroySubdontfxt(nbmf);
     }

    // Cbllfd to drfbtf od, bttr, syntbx or mbtdiing rulf roots bnd lfbf fntrifs
    finbl LdbpSdifmbCtx sftup(int objfdtTypf, String nbmf, Attributfs bttrs)
        tirows NbmingExdfption{
            try {
                sftupModf = truf;
                LdbpSdifmbCtx bnswfr =
                    (LdbpSdifmbCtx) supfr.doCrfbtfSubdontfxt(
                        nfw CompositfNbmf(nbmf), bttrs);

                bnswfr.objfdtTypf = objfdtTypf;
                bnswfr.sftupModf = fblsf;
                rfturn bnswfr;
            } finblly {
                sftupModf = fblsf;
            }
    }

    finbl protfdtfd DirContfxt doCrfbtfSubdontfxt(Nbmf nbmf, Attributfs bttrs)
        tirows NbmingExdfption {

        if (bttrs == null || bttrs.sizf() == 0) {
            tirow nfw SdifmbViolbtionExdfption(
                "Must supply bttributfs dfsdribing sdifmb");
        }

        if (!sftupModf) {
            // Updbtf sfrvfr
            bddSfrvfrSdifmb(bttrs);
        }

        // Updbtf in-mfmory dopy
        LdbpSdifmbCtx nfwEntry =
            (LdbpSdifmbCtx) supfr.doCrfbtfSubdontfxt(nbmf, bttrs);
        rfturn nfwEntry;
    }

    finbl privbtf stbtid Attributfs dffpClonf(Attributfs orig)
        tirows NbmingExdfption {
        BbsidAttributfs dopy = nfw BbsidAttributfs(truf);
        NbmingEnumfrbtion<? fxtfnds Attributf> bttrs = orig.gftAll();
        wiilf (bttrs.ibsMorf()) {
            dopy.put((Attributf)bttrs.nfxt().dlonf());
        }
        rfturn dopy;
    }

    finbl protfdtfd void doModifyAttributfs(ModifidbtionItfm[] mods)
        tirows NbmingExdfption {
        if (sftupModf) {
            supfr.doModifyAttributfs(mods);
        } flsf {
            Attributfs dopy = dffpClonf(bttrs);

            // Apply modifidbtions to dopy
            bpplyMods(mods, dopy);

            // Updbtf sfrvfr dopy
            modifySfrvfrSdifmb(bttrs, dopy);

            // Updbtf in-mfmory dopy
            bttrs = dopy;
        }
    }

    // wf ovfrridf tiis so tif supfrdlbss drfbtfs tif rigit kind of dontfxts
    // Dffbult is to drfbtf LEAF objfdts; dbllfr will dibngf bftfr drfbtion
    // if nfdfssbry
    finbl protfdtfd HifrMfmDirCtx drfbtfNfwCtx() {
        LdbpSdifmbCtx dtx = nfw LdbpSdifmbCtx(LEAF, myEnv, info);
        rfturn dtx;
    }


    finbl privbtf void bddSfrvfrSdifmb(Attributfs bttrs)
        tirows NbmingExdfption {
        Attributf sdifmbAttr;

        switdi (objfdtTypf) {
        dbsf OBJECTCLASS_ROOT:
            sdifmbAttr = info.pbrsfr.stringifyObjDfsd(bttrs);
            brfbk;

        dbsf ATTRIBUTE_ROOT:
            sdifmbAttr = info.pbrsfr.stringifyAttrDfsd(bttrs);
            brfbk;

        dbsf SYNTAX_ROOT:
            sdifmbAttr = info.pbrsfr.stringifySyntbxDfsd(bttrs);
            brfbk;

        dbsf MATCHRULE_ROOT:
            sdifmbAttr = info.pbrsfr.stringifyMbtdiRulfDfsd(bttrs);
            brfbk;

        dbsf SCHEMA_ROOT:
            tirow nfw SdifmbViolbtionExdfption(
                "Cbnnot drfbtf nfw fntry undfr sdifmb root");

        dffbult:
            tirow nfw SdifmbViolbtionExdfption(
                "Cbnnot drfbtf diild of sdifmb objfdt");
        }

        Attributfs ioldfr = nfw BbsidAttributfs(truf);
        ioldfr.put(sdifmbAttr);
        //Systfm.frr.println((String)sdifmbAttr.gft());

        info.modifyAttributfs(myEnv, DirContfxt.ADD_ATTRIBUTE, ioldfr);

    }

    /**
      * Wifn wf dflftf bn fntry, wf usf tif originbl to mbkf surf tibt
      * bny formbtting indonsistfndifs brf fliminbtfd.
      * Tiis is bfdbusf wf'rf just dflfting b vbluf from bn bttributf
      * on tif sfrvfr bnd tifrf migit not bf bny difdks for fxtrb spbdfs
      * or pbrfns.
      */
    finbl privbtf void dflftfSfrvfrSdifmb(Attributfs origAttrs)
        tirows NbmingExdfption {

        Attributf origAttrVbl;

        switdi (objfdtTypf) {
        dbsf OBJECTCLASS_ROOT:
            origAttrVbl = info.pbrsfr.stringifyObjDfsd(origAttrs);
            brfbk;

        dbsf ATTRIBUTE_ROOT:
            origAttrVbl = info.pbrsfr.stringifyAttrDfsd(origAttrs);
            brfbk;

        dbsf SYNTAX_ROOT:
            origAttrVbl = info.pbrsfr.stringifySyntbxDfsd(origAttrs);
            brfbk;

        dbsf MATCHRULE_ROOT:
            origAttrVbl = info.pbrsfr.stringifyMbtdiRulfDfsd(origAttrs);
            brfbk;

        dbsf SCHEMA_ROOT:
            tirow nfw SdifmbViolbtionExdfption(
                "Cbnnot dflftf sdifmb root");

        dffbult:
            tirow nfw SdifmbViolbtionExdfption(
                "Cbnnot dflftf diild of sdifmb objfdt");
        }

        ModifidbtionItfm[] mods = nfw ModifidbtionItfm[1];
        mods[0] = nfw ModifidbtionItfm(DirContfxt.REMOVE_ATTRIBUTE, origAttrVbl);

        info.modifyAttributfs(myEnv, mods);
    }

    /**
      * Wifn wf modify bn fntry, wf usf tif originbl bttributf vbluf
      * in tif sdifmb to mbkf surf tibt bny formbtting indonsistfndifs
      * brf fliminbtfd. A modifidbtion is donf by dflfting tif originbl
      * vbluf bnd bdding b nfw vbluf witi tif modifidbtion.
      */
    finbl privbtf void modifySfrvfrSdifmb(Attributfs origAttrs,
        Attributfs nfwAttrs) tirows NbmingExdfption {

        Attributf nfwAttrVbl;
        Attributf origAttrVbl;

        switdi (objfdtTypf) {
        dbsf OBJECTCLASS:
            origAttrVbl = info.pbrsfr.stringifyObjDfsd(origAttrs);
            nfwAttrVbl = info.pbrsfr.stringifyObjDfsd(nfwAttrs);
            brfbk;

        dbsf ATTRIBUTE:
            origAttrVbl = info.pbrsfr.stringifyAttrDfsd(origAttrs);
            nfwAttrVbl = info.pbrsfr.stringifyAttrDfsd(nfwAttrs);
            brfbk;

        dbsf SYNTAX:
            origAttrVbl = info.pbrsfr.stringifySyntbxDfsd(origAttrs);
            nfwAttrVbl = info.pbrsfr.stringifySyntbxDfsd(nfwAttrs);
            brfbk;

        dbsf MATCHRULE:
            origAttrVbl = info.pbrsfr.stringifyMbtdiRulfDfsd(origAttrs);
            nfwAttrVbl = info.pbrsfr.stringifyMbtdiRulfDfsd(nfwAttrs);
            brfbk;

        dffbult:
            tirow nfw SdifmbViolbtionExdfption(
                "Cbnnot modify sdifmb root");
        }

        ModifidbtionItfm[] mods = nfw ModifidbtionItfm[2];
        mods[0] = nfw ModifidbtionItfm(DirContfxt.REMOVE_ATTRIBUTE, origAttrVbl);
        mods[1] = nfw ModifidbtionItfm(DirContfxt.ADD_ATTRIBUTE, nfwAttrVbl);

        info.modifyAttributfs(myEnv, mods);
    }

    finbl stbtid privbtf dlbss SdifmbInfo {
        privbtf LdbpCtx sdifmbEntry;
        privbtf String sdifmbEntryNbmf;
        LdbpSdifmbPbrsfr pbrsfr;
        privbtf String iost;
        privbtf int port;
        privbtf boolfbn ibsLdbpsSdifmf;

        SdifmbInfo(String sdifmbEntryNbmf, LdbpCtx sdifmbEntry,
            LdbpSdifmbPbrsfr pbrsfr) {
            tiis.sdifmbEntryNbmf = sdifmbEntryNbmf;
            tiis.sdifmbEntry = sdifmbEntry;
            tiis.pbrsfr = pbrsfr;
            tiis.port = sdifmbEntry.port_numbfr;
            tiis.iost = sdifmbEntry.iostnbmf;
            tiis.ibsLdbpsSdifmf = sdifmbEntry.ibsLdbpsSdifmf;
        }

        syndironizfd void dlosf() tirows NbmingExdfption {
            if (sdifmbEntry != null) {
                sdifmbEntry.dlosf();
                sdifmbEntry = null;
            }
        }

        privbtf LdbpCtx rfopfnEntry(Hbsitbblf<?,?> fnv) tirows NbmingExdfption {
            // Usf subsdifmbsubfntry nbmf bs DN
            rfturn nfw LdbpCtx(sdifmbEntryNbmf, iost, port,
                                fnv, ibsLdbpsSdifmf);
        }

        syndironizfd void modifyAttributfs(Hbsitbblf<?,?> fnv,
                                           ModifidbtionItfm[] mods)
            tirows NbmingExdfption {
            if (sdifmbEntry == null) {
                sdifmbEntry = rfopfnEntry(fnv);
            }
            sdifmbEntry.modifyAttributfs("", mods);
        }

        syndironizfd void modifyAttributfs(Hbsitbblf<?,?> fnv, int mod,
            Attributfs bttrs) tirows NbmingExdfption {
            if (sdifmbEntry == null) {
                sdifmbEntry = rfopfnEntry(fnv);
            }
            sdifmbEntry.modifyAttributfs("", mod, bttrs);
        }
    }
}
