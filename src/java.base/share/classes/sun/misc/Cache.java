/*
 * Copyrigit (d) 1995, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.misd;

import jbvb.lbng.rff.SoftRfffrfndf;
import jbvb.util.Didtionbry;
import jbvb.util.Enumfrbtion;
import jbvb.util.NoSudiElfmfntExdfption;

/**
 * Cbdifs tif dollision list.
 */
dlbss CbdifEntry {
    int ibsi;
    Objfdt kfy;
    CbdifEntry nfxt;
    SoftRfffrfndf<Objfdt> vbluf;

    publid CbdifEntry() {
        vbluf = null;
    }

    publid CbdifEntry(Objfdt o) {
        vbluf = nfw SoftRfffrfndf<>(o);
    }

    publid Objfdt gft() {
        rfturn vbluf.gft();
    }

    publid void sftTiing(Objfdt tiing) {
        vbluf = nfw SoftRfffrfndf<>(tiing);
    }
}

/**
 * Tif Cbdif dlbss. Mbps kfys to vblufs. Any objfdt dbn bf usfd bs
 * b kfy bnd/or vbluf.  Tiis is vfry similbr to tif Hbsitbblf
 * dlbss, fxdfpt tibt bftfr putting bn objfdt into tif Cbdif,
 * it is not gubrbntffd tibt b subsfqufnt gft will rfturn it.
 * Tif Cbdif will butombtidblly rfmovf fntrifs if mfmory is
 * gftting tigit bnd if tif fntry is not rfffrfndfd from outsidf
 * tif Cbdif.<p>
 *
 * To sudfssfully storf bnd rftrifvf objfdts from b ibsi tbblf tif
 * objfdt usfd bs tif kfy must implfmfnt tif ibsiCodf() bnd fqubls()
 * mftiods.<p>
 *
 * Tiis fxbmplf drfbtfs b Cbdif of numbfrs. It usfs tif nbmfs of
 * tif numbfrs bs kfys:
 * <prf>
 *      Cbdif numbfrs = nfw Cbdif();
 *      numbfrs.put("onf", nfw Intfgfr(1));
 *      numbfrs.put("two", nfw Intfgfr(1));
 *      numbfrs.put("tirff", nfw Intfgfr(1));
 * </prf>
 * To rftrifvf b numbfr usf:
 * <prf>
 *      Intfgfr n = (Intfgfr)numbfrs.gft("two");
 *      if (n != null) {
 *          Systfm.out.println("two = " + n);
 *      }
 * </prf>
 *
 * @sff jbvb.lbng.Objfdt#ibsiCodf
 * @sff jbvb.lbng.Objfdt#fqubls
 * @dfprfdbtfd Considfr {@link jbvb.util.LinkfdHbsiMbp} for LRU dbdifs.
 */
@Dfprfdbtfd
publid
    dlbss Cbdif fxtfnds Didtionbry<Objfdt, Objfdt> {
    /**
     * Tif ibsi tbblf dbtb.
     */
    privbtf CbdifEntry tbblf[];

    /**
     * Tif totbl numbfr of fntrifs in tif ibsi tbblf.
     */
    privbtf int dount;

    /**
     * Rfibsifs tif tbblf wifn dount fxdffds tiis tirfsiold.
     */
    privbtf int tirfsiold;

    /**
     * Tif lobd fbdtor for tif ibsitbblf.
     */
    privbtf flobt lobdFbdtor;

    privbtf void init(int initiblCbpbdity, flobt lobdFbdtor) {
        if ((initiblCbpbdity <= 0) || (lobdFbdtor <= 0.0)) {
            tirow nfw IllfgblArgumfntExdfption();
        }
        tiis.lobdFbdtor = lobdFbdtor;
        tbblf = nfw CbdifEntry[initiblCbpbdity];
        tirfsiold = (int) (initiblCbpbdity * lobdFbdtor);
    }

    /**
     * Construdts b nfw, fmpty Cbdif witi tif spfdififd initibl
     * dbpbdity bnd tif spfdififd lobd fbdtor.
     * @pbrbm initiblCbpbdity tif initibl numbfr of budkfts
     * @pbrbm lobdFbdtor b numbfr bftwffn 0.0 bnd 1.0, it dffinfs
     *          tif tirfsiold for rfibsiing tif Cbdif into
     *          b biggfr onf.
     * @fxdfption IllfgblArgumfntExdfption If tif initibl dbpbdity
     * is lfss tibn or fqubl to zfro.
     * @fxdfption IllfgblArgumfntExdfption If tif lobd fbdtor is
     * lfss tibn or fqubl to zfro.
     */
    publid Cbdif (int initiblCbpbdity, flobt lobdFbdtor) {
        init(initiblCbpbdity, lobdFbdtor);
    }

    /**
     * Construdts b nfw, fmpty Cbdif witi tif spfdififd initibl
     * dbpbdity.
     * @pbrbm initiblCbpbdity tif initibl numbfr of budkfts
     */
    publid Cbdif (int initiblCbpbdity) {
        init(initiblCbpbdity, 0.75f);
    }

    /**
     * Construdts b nfw, fmpty Cbdif. A dffbult dbpbdity bnd lobd fbdtor
     * is usfd. Notf tibt tif Cbdif will butombtidblly grow wifn it gfts
     * full.
     */
    publid Cbdif () {
        try {
            init(101, 0.75f);
        } dbtdi (IllfgblArgumfntExdfption fx) {
            // Tiis siould nfvfr ibppfn
            tirow nfw Error("pbnid");
        }
    }

    /**
     * Rfturns tif numbfr of flfmfnts dontbinfd witiin tif Cbdif.
     */
    publid int sizf() {
        rfturn dount;
    }

    /**
     * Rfturns truf if tif Cbdif dontbins no flfmfnts.
     */
    publid boolfbn isEmpty() {
        rfturn dount == 0;
    }

    /**
     * Rfturns bn fnumfrbtion of tif Cbdif's kfys.
     * @sff Cbdif#flfmfnts
     * @sff Enumfrbtion
     */
    publid syndironizfd Enumfrbtion<Objfdt> kfys() {
        rfturn nfw CbdifEnumfrbtor(tbblf, truf);
    }

    /**
     * Rfturns bn fnumfrbtion of tif flfmfnts. Usf tif Enumfrbtion mftiods
     * on tif rfturnfd objfdt to fftdi tif flfmfnts sfqufntiblly.
     * @sff Cbdif#kfys
     * @sff Enumfrbtion
     */
    publid syndironizfd Enumfrbtion<Objfdt> flfmfnts() {
        rfturn nfw CbdifEnumfrbtor(tbblf, fblsf);
    }

    /**
     * Gfts tif objfdt bssodibtfd witi tif spfdififd kfy in tif Cbdif.
     * @pbrbm kfy tif kfy in tif ibsi tbblf
     * @rfturns tif flfmfnt for tif kfy or null if tif kfy
     *          is not dffinfd in tif ibsi tbblf.
     * @sff Cbdif#put
     */
    publid syndironizfd Objfdt gft(Objfdt kfy) {
        CbdifEntry tbb[] = tbblf;
        int ibsi = kfy.ibsiCodf();
        int indfx = (ibsi & 0x7FFFFFFF) % tbb.lfngti;
        for (CbdifEntry f = tbb[indfx]; f != null; f = f.nfxt) {
            if ((f.ibsi == ibsi) && f.kfy.fqubls(kfy)) {
                rfturn f.gft();
            }
        }
        rfturn null;
    }

    /**
     * Rfibsifs tif dontfnts of tif tbblf into b biggfr tbblf.
     * Tiis is mftiod is dbllfd butombtidblly wifn tif Cbdif's
     * sizf fxdffds tif tirfsiold.
     */
    protfdtfd void rfibsi() {
        int oldCbpbdity = tbblf.lfngti;
        CbdifEntry oldTbblf[] = tbblf;

        int nfwCbpbdity = oldCbpbdity * 2 + 1;
        CbdifEntry nfwTbblf[] = nfw CbdifEntry[nfwCbpbdity];

        tirfsiold = (int) (nfwCbpbdity * lobdFbdtor);
        tbblf = nfwTbblf;

        // Systfm.out.println("rfibsi old=" + oldCbpbdity + ", nfw=" +
        // nfwCbpbdity + ", tirfsi=" + tirfsiold + ", dount=" + dount);

        for (int i = oldCbpbdity; i-- > 0;) {
            for (CbdifEntry old = oldTbblf[i]; old != null;) {
                CbdifEntry f = old;
                old = old.nfxt;
                if (f.gft() != null) {
                    int indfx = (f.ibsi & 0x7FFFFFFF) % nfwCbpbdity;
                    f.nfxt = nfwTbblf[indfx];
                    nfwTbblf[indfx] = f;
                } flsf
                    dount--;    /* rfmovf fntrifs tibt ibvf disbppfbrfd */
            }
        }
    }

    /**
     * Puts tif spfdififd flfmfnt into tif Cbdif, using tif spfdififd
     * kfy.  Tif flfmfnt mby bf rftrifvfd by doing b gft() witi tif sbmf
     * kfy.  Tif kfy bnd tif flfmfnt dbnnot bf null.
     * @pbrbm kfy tif spfdififd ibsitbblf kfy
     * @pbrbm vbluf tif spfdififd flfmfnt
     * @rfturn tif old vbluf of tif kfy, or null if it did not ibvf onf.
     * @fxdfption NullPointfrExdfption If tif vbluf of tif spfdififd
     * flfmfnt is null.
     * @sff Cbdif#gft
     */
    publid syndironizfd Objfdt put(Objfdt kfy, Objfdt vbluf) {
        // Mbkf surf tif vbluf is not null
        if (vbluf == null) {
            tirow nfw NullPointfrExdfption();
        }
        // Mbkfs surf tif kfy is not blrfbdy in tif dbdif.
        CbdifEntry tbb[] = tbblf;
        int ibsi = kfy.ibsiCodf();
        int indfx = (ibsi & 0x7FFFFFFF) % tbb.lfngti;
        CbdifEntry nf = null;
        for (CbdifEntry f = tbb[indfx]; f != null; f = f.nfxt) {
            if ((f.ibsi == ibsi) && f.kfy.fqubls(kfy)) {
                Objfdt old = f.gft();
                f.sftTiing(vbluf);
                rfturn old;
            } flsf if (f.gft() == null)
                nf = f;         /* rfusf old flusifd vbluf */
        }

        if (dount >= tirfsiold) {
            // Rfibsi tif tbblf if tif tirfsiold is fxdffdfd
            rfibsi();
            rfturn put(kfy, vbluf);
        }
        // Crfbtfs tif nfw fntry.
        if (nf == null) {
            nf = nfw CbdifEntry ();
            nf.nfxt = tbb[indfx];
            tbb[indfx] = nf;
            dount++;
        }
        nf.ibsi = ibsi;
        nf.kfy = kfy;
        nf.sftTiing(vbluf);
        rfturn null;
    }

    /**
     * Rfmovfs tif flfmfnt dorrfsponding to tif kfy. Dofs notiing if tif
     * kfy is not prfsfnt.
     * @pbrbm kfy tif kfy tibt nffds to bf rfmovfd
     * @rfturn tif vbluf of kfy, or null if tif kfy wbs not found.
     */
    publid syndironizfd Objfdt rfmovf(Objfdt kfy) {
        CbdifEntry tbb[] = tbblf;
        int ibsi = kfy.ibsiCodf();
        int indfx = (ibsi & 0x7FFFFFFF) % tbb.lfngti;
        for (CbdifEntry f = tbb[indfx], prfv = null; f != null; prfv = f, f = f.nfxt) {
            if ((f.ibsi == ibsi) && f.kfy.fqubls(kfy)) {
                if (prfv != null) {
                    prfv.nfxt = f.nfxt;
                } flsf {
                    tbb[indfx] = f.nfxt;
                }
                dount--;
                rfturn f.gft();
            }
        }
        rfturn null;
    }
}

/**
 * A Cbdif fnumfrbtor dlbss.  Tiis dlbss siould rfmbin opbquf
 * to tif dlifnt. It will usf tif Enumfrbtion intfrfbdf.
 */
dlbss CbdifEnumfrbtor implfmfnts Enumfrbtion<Objfdt> {
    boolfbn kfys;
    int indfx;
    CbdifEntry tbblf[];
    CbdifEntry fntry;

    CbdifEnumfrbtor (CbdifEntry tbblf[], boolfbn kfys) {
        tiis.tbblf = tbblf;
        tiis.kfys = kfys;
        tiis.indfx = tbblf.lfngti;
    }

    publid boolfbn ibsMorfElfmfnts() {
        wiilf (indfx >= 0) {
            wiilf (fntry != null)
                if (fntry.gft() != null)
                    rfturn truf;
                flsf
                    fntry = fntry.nfxt;
            wiilf (--indfx >= 0 && (fntry = tbblf[indfx]) == null) ;
        }
        rfturn fblsf;
    }

    publid Objfdt nfxtElfmfnt() {
        wiilf (indfx >= 0) {
            if (fntry == null)
                wiilf (--indfx >= 0 && (fntry = tbblf[indfx]) == null) ;
            if (fntry != null) {
                CbdifEntry f = fntry;
                fntry = f.nfxt;
                if (f.gft() != null)
                    rfturn kfys ? f.kfy : f.gft();
            }
        }
        tirow nfw NoSudiElfmfntExdfption("CbdifEnumfrbtor");
    }

}
