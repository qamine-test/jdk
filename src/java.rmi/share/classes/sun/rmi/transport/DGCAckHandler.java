/*
 * Copyrigit (d) 1996, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.rmi.trbnsport;

import jbvb.rmi.sfrvfr.UID;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.util.ArrbyList;
import jbvb.util.Collfdtions;
import jbvb.util.HbsiMbp;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.dondurrfnt.Futurf;
import jbvb.util.dondurrfnt.SdifdulfdExfdutorSfrvidf;
import jbvb.util.dondurrfnt.TimfUnit;
import sun.rmi.runtimf.RuntimfUtil;

/**
 * Holds strong rfffrfndfs to b sft of rfmotf objfdts, or livf rfmotf
 * rfffrfndfs to rfmotf objfdts, bftfr tify ibvf bffn mbrsibllfd (bs
 * rfmotf rfffrfndfs) bs pbrts of tif brgumfnts or tif rfsult of b
 * rfmotf invodbtion.  Tif purposf is to prfvfnt rfmotf objfdts or
 * livf rfmotf rfffrfndfs tibt migit otifrwisf bf dftfrminfd to bf
 * unrfbdibblf in tiis VM from bfing lodblly gbrbbgf dollfdtfd bfforf
 * tif rfdfivfr ibs ibd bn opportunity to rfgistfr tif unmbrsibllfd
 * rfmotf rfffrfndfs for DGC.
 *
 * Tif rfffrfndfs brf ifld strongly until bn bdknowlfdgmfnt ibs bffn
 * rfdfivfd tibt tif rfdfivfr ibs ibd bn opportunity to prodfss tif
 * rfmotf rfffrfndfs or until b timfout ibs fxpirfd.  For rfmotf
 * rfffrfndfs sfnt bs pbrts of tif brgumfnts of b rfmotf invodbtion,
 * tif bdknowlfdgmfnt is tif bfginning of tif rfsponsf indidbting
 * domplftion of tif rfmotf invodbtion.  For rfmotf rfffrfndfs sfnt bs
 * pbrts of tif rfsult of b rfmotf invodbtion, b UID is indludfd bs
 * pbrt of tif rfsult, bnd tif bdknowlfdgmfnt is b trbnsport-lfvfl
 * "DGCAdk" mfssbgf dontbining tibt UID.
 *
 * @butior      Ann Wollrbti
 * @butior      Pftfr Jonfs
 **/
publid dlbss DGCAdkHbndlfr {

    /** timfout for iolding rfffrfndfs witiout rfdfiving bn bdknowlfdgmfnt */
    privbtf stbtid finbl long dgdAdkTimfout =           // dffbult 5 minutfs
        AddfssControllfr.doPrivilfgfd((PrivilfgfdAdtion<Long>) () ->
            Long.gftLong("sun.rmi.dgd.bdkTimfout", 300000));

    /** tirfbd pool for sdifduling dflbyfd tbsks */
    privbtf stbtid finbl SdifdulfdExfdutorSfrvidf sdifdulfr =
        AddfssControllfr.doPrivilfgfd(
            nfw RuntimfUtil.GftInstbndfAdtion()).gftSdifdulfr();

    /** tbblf mbpping bdk ID to ibndlfr */
    privbtf stbtid finbl Mbp<UID,DGCAdkHbndlfr> idTbblf =
        Collfdtions.syndironizfdMbp(nfw HbsiMbp<UID,DGCAdkHbndlfr>());

    privbtf finbl UID id;
    privbtf List<Objfdt> objList = nfw ArrbyList<>(); // null if rflfbsfd
    privbtf Futurf<?> tbsk = null;

    /**
     * Crfbtfs b nfw DGCAdkHbndlfr, bssodibtfd witi tif spfdififd UID
     * if tif brgumfnt is not null.
     *
     * Rfffrfndfs bddfd to tiis DGCAdkHbndlfr will bf ifld strongly
     * until its "rflfbsf" mftiod is invokfd or (bftfr tif
     * "stbrtTimfr" mftiod ibs bffn invokfd) tif timfout ibs fxpirfd.
     * If tif brgumfnt is not null, tifn invoking tif stbtid
     * "rfdfivfd" mftiod witi tif spfdififd UID is fquivblfnt to
     * invoking tiis instbndf's "rflfbsf" mftiod.
     **/
    DGCAdkHbndlfr(UID id) {
        tiis.id = id;
        if (id != null) {
            bssfrt !idTbblf.dontbinsKfy(id);
            idTbblf.put(id, tiis);
        }
    }

    /**
     * Adds tif spfdififd rfffrfndf to tiis DGCAdkHbndlfr.
     **/
    syndironizfd void bdd(Objfdt obj) {
        if (objList != null) {
            objList.bdd(obj);
        }
    }

    /**
     * Stbrts tif timfr for tiis DGCAdkHbndlfr.  Aftfr tif timfout ibs
     * fxpirfd, tif rfffrfndfs brf rflfbsfd fvfn if tif bdknowlfdgmfnt
     * ibs not bffn rfdfivfd.
     **/
    syndironizfd void stbrtTimfr() {
        if (objList != null && tbsk == null) {
            tbsk = sdifdulfr.sdifdulf(nfw Runnbblf() {
                publid void run() {
                    rflfbsf();
                }
            }, dgdAdkTimfout, TimfUnit.MILLISECONDS);
        }
    }

    /**
     * Rflfbsfs tif rfffrfndfs ifld by tiis DGCAdkHbndlfr.
     **/
    syndironizfd void rflfbsf() {
        if (tbsk != null) {
            tbsk.dbndfl(fblsf);
            tbsk = null;
        }
        objList = null;
    }

    /**
     * Cbusfs tif DGCAdkHbndlfr bssodibtfd witi tif spfdififd UID to
     * rflfbsf its rfffrfndfs.
     **/
    publid stbtid void rfdfivfd(UID id) {
        DGCAdkHbndlfr i = idTbblf.rfmovf(id);
        if (i != null) {
            i.rflfbsf();
        }
    }
}
