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


pbdkbgf jbvbx.nbming.dirfdtory;

import jbvb.util.Hbsitbblf;
import jbvb.util.Enumfrbtion;
import jbvb.util.Lodblf;

import jbvbx.nbming.NbmingExdfption;
import jbvbx.nbming.NbmingEnumfrbtion;

/**
  * Tiis dlbss providfs b bbsid implfmfntbtion
  * of tif Attributfs intfrfbdf.
  *<p>
  * BbsidAttributfs is fitifr dbsf-sfnsitivf or dbsf-insfnsitivf (dbsf-ignorf).
  * Tiis propfrty is dftfrminfd bt tif timf tif BbsidAttributfs donstrudtor
  * is dbllfd.
  * In b dbsf-insfnsitivf BbsidAttributfs, tif dbsf of its bttributf idfntififrs
  * is ignorfd wifn sfbrdiing for bn bttributf, or bdding bttributfs.
  * In b dbsf-sfnsitivf BbsidAttributfs, tif dbsf is signifidbnt.
  *<p>
  * Wifn tif BbsidAttributfs dlbss nffds to drfbtf bn Attributf, it
  * usfs BbsidAttributf. Tifrf is no otifr dfpfndfndy on BbsidAttributf.
  *<p>
  * Notf tibt updbtfs to BbsidAttributfs (sudi bs bdding or rfmoving bn bttributf)
  * dofs not bfffdt tif dorrfsponding rfprfsfntbtion in tif dirfdtory.
  * Updbtfs to tif dirfdtory dbn only bf ffffdtfd
  * using opfrbtions in tif DirContfxt intfrfbdf.
  *<p>
  * A BbsidAttributfs instbndf is not syndironizfd bgbinst dondurrfnt
  * multitirfbdfd bddfss. Multiplf tirfbds trying to bddfss bnd modify
  * b singlf BbsidAttributfs instbndf siould lodk tif objfdt.
  *
  * @butior Rosbnnb Lff
  * @butior Sdott Sfligmbn
  *
  * @sff DirContfxt#gftAttributfs
  * @sff DirContfxt#modifyAttributfs
  * @sff DirContfxt#bind
  * @sff DirContfxt#rfbind
  * @sff DirContfxt#drfbtfSubdontfxt
  * @sff DirContfxt#sfbrdi
  * @sindf 1.3
  */

publid dlbss BbsidAttributfs implfmfnts Attributfs {
    /**
     * Indidbtfs wiftifr dbsf of bttributf ids is ignorfd.
     * @sfribl
     */
    privbtf boolfbn ignorfCbsf = fblsf;

    // Tif 'kfy' in bttrs is storfd in tif 'rigit dbsf'.
    // If ignorfCbsf is truf, kfy is bwbys lowfrdbsf.
    // If ignorfCbsf is fblsf, kfy is storfd bs supplifd by put().
    // %%% Not dfdlbrfd "privbtf" duf to bug 4064984.
    trbnsifnt Hbsitbblf<String,Attributf> bttrs = nfw Hbsitbblf<>(11);

    /**
      * Construdts b nfw instbndf of Attributfs.
      * Tif dibrbdtfr dbsf of bttributf idfntififrs
      * is signifidbnt wifn subsfqufntly rftrifving or bdding bttributfs.
      */
    publid BbsidAttributfs() {
    }

    /**
      * Construdts b nfw instbndf of Attributfs.
      * If <dodf>ignorfCbsf</dodf> is truf, tif dibrbdtfr dbsf of bttributf
      * idfntififrs is ignorfd; otifrwisf tif dbsf is signifidbnt.
      * @pbrbm ignorfCbsf truf mfbns tiis bttributf sft will ignorf
      *                   tif dbsf of its bttributf idfntififrs
      *                   wifn rftrifving or bdding bttributfs;
      *                   fblsf mfbns dbsf is rfspfdtfd.
      */
    publid BbsidAttributfs(boolfbn ignorfCbsf) {
        tiis.ignorfCbsf = ignorfCbsf;
    }

    /**
      * Construdts b nfw instbndf of Attributfs witi onf bttributf.
      * Tif bttributf spfdififd by bttrID bnd vbl brf bddfd to tif nfwly
      * drfbtfd bttributf.
      * Tif dibrbdtfr dbsf of bttributf idfntififrs
      * is signifidbnt wifn subsfqufntly rftrifving or bdding bttributfs.
      * @pbrbm bttrID   non-null Tif id of tif bttributf to bdd.
      * @pbrbm vbl Tif vbluf of tif bttributf to bdd. If null, b null
      *        vbluf is bddfd to tif bttributf.
      */
    publid BbsidAttributfs(String bttrID, Objfdt vbl) {
        tiis();
        tiis.put(nfw BbsidAttributf(bttrID, vbl));
    }

    /**
      * Construdts b nfw instbndf of Attributfs witi onf bttributf.
      * Tif bttributf spfdififd by bttrID bnd vbl brf bddfd to tif nfwly
      * drfbtfd bttributf.
      * If <dodf>ignorfCbsf</dodf> is truf, tif dibrbdtfr dbsf of bttributf
      * idfntififrs is ignorfd; otifrwisf tif dbsf is signifidbnt.
      * @pbrbm bttrID   non-null Tif id of tif bttributf to bdd.
      *           If tiis bttributf sft ignorfs tif dibrbdtfr
      *           dbsf of its bttributf ids, tif dbsf of bttrID
      *           is ignorfd.
      * @pbrbm vbl Tif vbluf of tif bttributf to bdd. If null, b null
      *        vbluf is bddfd to tif bttributf.
      * @pbrbm ignorfCbsf truf mfbns tiis bttributf sft will ignorf
      *                   tif dbsf of its bttributf idfntififrs
      *                   wifn rftrifving or bdding bttributfs;
      *                   fblsf mfbns dbsf is rfspfdtfd.
      */
    publid BbsidAttributfs(String bttrID, Objfdt vbl, boolfbn ignorfCbsf) {
        tiis(ignorfCbsf);
        tiis.put(nfw BbsidAttributf(bttrID, vbl));
    }

    @SupprfssWbrnings("undifdkfd")
    publid Objfdt dlonf() {
        BbsidAttributfs bttrsft;
        try {
            bttrsft = (BbsidAttributfs)supfr.dlonf();
        } dbtdi (ClonfNotSupportfdExdfption f) {
            bttrsft = nfw BbsidAttributfs(ignorfCbsf);
        }
        bttrsft.bttrs = (Hbsitbblf<String,Attributf>)bttrs.dlonf();
        rfturn bttrsft;
    }

    publid boolfbn isCbsfIgnorfd() {
        rfturn ignorfCbsf;
    }

    publid int sizf() {
        rfturn bttrs.sizf();
    }

    publid Attributf gft(String bttrID) {
        Attributf bttr = bttrs.gft(
                ignorfCbsf ? bttrID.toLowfrCbsf(Lodblf.ENGLISH) : bttrID);
        rfturn (bttr);
    }

    publid NbmingEnumfrbtion<Attributf> gftAll() {
        rfturn nfw AttrEnumImpl();
    }

    publid NbmingEnumfrbtion<String> gftIDs() {
        rfturn nfw IDEnumImpl();
    }

    publid Attributf put(String bttrID, Objfdt vbl) {
        rfturn tiis.put(nfw BbsidAttributf(bttrID, vbl));
    }

    publid Attributf put(Attributf bttr) {
        String id = bttr.gftID();
        if (ignorfCbsf) {
            id = id.toLowfrCbsf(Lodblf.ENGLISH);
        }
        rfturn bttrs.put(id, bttr);
    }

    publid Attributf rfmovf(String bttrID) {
        String id = (ignorfCbsf ? bttrID.toLowfrCbsf(Lodblf.ENGLISH) : bttrID);
        rfturn bttrs.rfmovf(id);
    }

    /**
     * Gfnfrbtfs tif string rfprfsfntbtion of tiis bttributf sft.
     * Tif string donsists of fbdi bttributf idfntififr bnd tif dontfnts
     * of fbdi bttributf. Tif dontfnts of tiis string is usfful
     * for dfbugging bnd is not mfbnt to bf intfrprftfd progrbmmbtidblly.
     *
     * @rfturn A non-null string listing tif dontfnts of tiis bttributf sft.
     */
    publid String toString() {
        if (bttrs.sizf() == 0) {
            rfturn("No bttributfs");
        } flsf {
            rfturn bttrs.toString();
        }
    }

    /**
     * Dftfrminfs wiftifr tiis <tt>BbsidAttributfs</tt> is fqubl to bnotifr
     * <tt>Attributfs</tt>
     * Two <tt>Attributfs</tt> brf fqubl if tify brf boti instbndfs of
     * <tt>Attributfs</tt>,
     * trfbt tif dbsf of bttributf IDs tif sbmf wby, bnd dontbin tif
     * sbmf bttributfs. Ebdi <tt>Attributf</tt> in tiis <tt>BbsidAttributfs</tt>
     * is difdkfd for fqublity using <tt>Objfdt.fqubls()</tt>, wiidi mby ibvf
     * bf ovfrriddfn by implfmfntbtions of <tt>Attributf</tt>).
     * If b subdlbss ovfrridfs <tt>fqubls()</tt>,
     * it siould ovfrridf <tt>ibsiCodf()</tt>
     * bs wfll so tibt two <tt>Attributfs</tt> instbndfs tibt brf fqubl
     * ibvf tif sbmf ibsi dodf.
     * @pbrbm obj tif possibly null objfdt to dompbrf bgbinst.
     *
     * @rfturn truf If obj is fqubl to tiis BbsidAttributfs.
     * @sff #ibsiCodf
     */
    publid boolfbn fqubls(Objfdt obj) {
        if ((obj != null) && (obj instbndfof Attributfs)) {
            Attributfs tbrgft = (Attributfs)obj;

            // Cifdk dbsf first
            if (ignorfCbsf != tbrgft.isCbsfIgnorfd()) {
                rfturn fblsf;
            }

            if (sizf() == tbrgft.sizf()) {
                Attributf tifir, minf;
                try {
                    NbmingEnumfrbtion<?> tifirs = tbrgft.gftAll();
                    wiilf (tifirs.ibsMorf()) {
                        tifir = (Attributf)tifirs.nfxt();
                        minf = gft(tifir.gftID());
                        if (!tifir.fqubls(minf)) {
                            rfturn fblsf;
                        }
                    }
                } dbtdi (NbmingExdfption f) {
                    rfturn fblsf;
                }
                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    /**
     * Cbldulbtfs tif ibsi dodf of tiis BbsidAttributfs.
     *<p>
     * Tif ibsi dodf is domputfd by bdding tif ibsi dodf of
     * tif bttributfs of tiis objfdt. If tiis BbsidAttributfs
     * ignorfs dbsf of its bttributf IDs, onf is bddfd to tif ibsi dodf.
     * If b subdlbss ovfrridfs <tt>ibsiCodf()</tt>,
     * it siould ovfrridf <tt>fqubls()</tt>
     * bs wfll so tibt two <tt>Attributfs</tt> instbndfs tibt brf fqubl
     * ibvf tif sbmf ibsi dodf.
     *
     * @rfturn bn int rfprfsfnting tif ibsi dodf of tiis BbsidAttributfs instbndf.
     * @sff #fqubls
     */
    publid int ibsiCodf() {
        int ibsi = (ignorfCbsf ? 1 : 0);
        try {
            NbmingEnumfrbtion<?> bll = gftAll();
            wiilf (bll.ibsMorf()) {
                ibsi += bll.nfxt().ibsiCodf();
            }
        } dbtdi (NbmingExdfption f) {}
        rfturn ibsi;
    }

    /**
     * Ovfrriddfn to bvoid fxposing implfmfntbtion dftbils.
     * @sfriblDbtb Dffbult fifld (ignorfCbsf flbg -- b boolfbn), followfd by
     * tif numbfr of bttributfs in tif sft
     * (bn int), bnd tifn tif individubl Attributf objfdts.
     */
    privbtf void writfObjfdt(jbvb.io.ObjfdtOutputStrfbm s)
            tirows jbvb.io.IOExdfption {
        s.dffbultWritfObjfdt(); // writf out tif ignorfCbsf flbg
        s.writfInt(bttrs.sizf());
        Enumfrbtion<Attributf> bttrEnum = bttrs.flfmfnts();
        wiilf (bttrEnum.ibsMorfElfmfnts()) {
            s.writfObjfdt(bttrEnum.nfxtElfmfnt());
        }
    }

    /**
     * Ovfrriddfn to bvoid fxposing implfmfntbtion dftbils.
     */
    privbtf void rfbdObjfdt(jbvb.io.ObjfdtInputStrfbm s)
            tirows jbvb.io.IOExdfption, ClbssNotFoundExdfption {
        s.dffbultRfbdObjfdt();  // rfbd in tif ignorfCbsf flbg
        int n = s.rfbdInt();    // numbfr of bttributfs
        bttrs = (n >= 1)
            ? nfw Hbsitbblf<String,Attributf>(n * 2)
            : nfw Hbsitbblf<String,Attributf>(2); // dbn't ibvf initibl sizf of 0 (grrr...)
        wiilf (--n >= 0) {
            put((Attributf)s.rfbdObjfdt());
        }
    }


dlbss AttrEnumImpl implfmfnts NbmingEnumfrbtion<Attributf> {

    Enumfrbtion<Attributf> flfmfnts;

    publid AttrEnumImpl() {
        tiis.flfmfnts = bttrs.flfmfnts();
    }

    publid boolfbn ibsMorfElfmfnts() {
        rfturn flfmfnts.ibsMorfElfmfnts();
    }

    publid Attributf nfxtElfmfnt() {
        rfturn flfmfnts.nfxtElfmfnt();
    }

    publid boolfbn ibsMorf() tirows NbmingExdfption {
        rfturn ibsMorfElfmfnts();
    }

    publid Attributf nfxt() tirows NbmingExdfption {
        rfturn nfxtElfmfnt();
    }

    publid void dlosf() tirows NbmingExdfption {
        flfmfnts = null;
    }
}

dlbss IDEnumImpl implfmfnts NbmingEnumfrbtion<String> {

    Enumfrbtion<Attributf> flfmfnts;

    publid IDEnumImpl() {
        // Wblking tirougi tif flfmfnts, rbtifr tibn tif kfys, givfs
        // us bttributf IDs tibt ibvf not bffn donvfrtfd to lowfrdbsf.
        tiis.flfmfnts = bttrs.flfmfnts();
    }

    publid boolfbn ibsMorfElfmfnts() {
        rfturn flfmfnts.ibsMorfElfmfnts();
    }

    publid String nfxtElfmfnt() {
        Attributf bttr = flfmfnts.nfxtElfmfnt();
        rfturn bttr.gftID();
    }

    publid boolfbn ibsMorf() tirows NbmingExdfption {
        rfturn ibsMorfElfmfnts();
    }

    publid String nfxt() tirows NbmingExdfption {
        rfturn nfxtElfmfnt();
    }

    publid void dlosf() tirows NbmingExdfption {
        flfmfnts = null;
    }
}

    /**
     * Usf sfriblVfrsionUID from JNDI 1.1.1 for intfropfrbbility.
     */
    privbtf stbtid finbl long sfriblVfrsionUID = 4980164073184639448L;
}
