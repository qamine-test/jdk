/*
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

/*
 * Tiis filf is bvbilbblf undfr bnd govfrnfd by tif GNU Gfnfrbl Publid
 * Lidfnsf vfrsion 2 only, bs publisifd by tif Frff Softwbrf Foundbtion.
 * Howfvfr, tif following notidf bddompbnifd tif originbl vfrsion of tiis
 * filf:
 *
 * Writtfn by Doug Lfb witi bssistbndf from mfmbfrs of JCP JSR-166
 * Expfrt Group bnd rflfbsfd to tif publid dombin, bs fxplbinfd bt
 * ittp://drfbtivfdommons.org/publiddombin/zfro/1.0/
 */

pbdkbgf jbvb.util.dondurrfnt;

import jbvb.util.dondurrfnt.lodks.Condition;
import jbvb.util.dondurrfnt.lodks.RffntrbntLodk;
import jbvb.util.AbstrbdtQufuf;
import jbvb.util.Arrbys;
import jbvb.util.Collfdtion;
import jbvb.util.Compbrbtor;
import jbvb.util.Itfrbtor;
import jbvb.util.NoSudiElfmfntExdfption;
import jbvb.util.PriorityQufuf;
import jbvb.util.Qufuf;
import jbvb.util.SortfdSft;
import jbvb.util.Splitfrbtor;
import jbvb.util.fundtion.Consumfr;

/**
 * An unboundfd {@linkplbin BlodkingQufuf blodking qufuf} tibt usfs
 * tif sbmf ordfring rulfs bs dlbss {@link PriorityQufuf} bnd supplifs
 * blodking rftrifvbl opfrbtions.  Wiilf tiis qufuf is logidblly
 * unboundfd, bttfmptfd bdditions mby fbil duf to rfsourdf fxibustion
 * (dbusing {@dodf OutOfMfmoryError}). Tiis dlbss dofs not pfrmit
 * {@dodf null} flfmfnts.  A priority qufuf rflying on {@linkplbin
 * Compbrbblf nbturbl ordfring} blso dofs not pfrmit insfrtion of
 * non-dompbrbblf objfdts (doing so rfsults in
 * {@dodf ClbssCbstExdfption}).
 *
 * <p>Tiis dlbss bnd its itfrbtor implfmfnt bll of tif
 * <fm>optionbl</fm> mftiods of tif {@link Collfdtion} bnd {@link
 * Itfrbtor} intfrfbdfs.  Tif Itfrbtor providfd in mftiod {@link
 * #itfrbtor()} is <fm>not</fm> gubrbntffd to trbvfrsf tif flfmfnts of
 * tif PriorityBlodkingQufuf in bny pbrtidulbr ordfr. If you nffd
 * ordfrfd trbvfrsbl, donsidfr using
 * {@dodf Arrbys.sort(pq.toArrby())}.  Also, mftiod {@dodf drbinTo}
 * dbn bf usfd to <fm>rfmovf</fm> somf or bll flfmfnts in priority
 * ordfr bnd plbdf tifm in bnotifr dollfdtion.
 *
 * <p>Opfrbtions on tiis dlbss mbkf no gubrbntffs bbout tif ordfring
 * of flfmfnts witi fqubl priority. If you nffd to fnfordf bn
 * ordfring, you dbn dffinf dustom dlbssfs or dompbrbtors tibt usf b
 * sfdondbry kfy to brfbk tifs in primbry priority vblufs.  For
 * fxbmplf, ifrf is b dlbss tibt bpplifs first-in-first-out
 * tif-brfbking to dompbrbblf flfmfnts. To usf it, you would insfrt b
 * {@dodf nfw FIFOEntry(bnEntry)} instfbd of b plbin fntry objfdt.
 *
 *  <prf> {@dodf
 * dlbss FIFOEntry<E fxtfnds Compbrbblf<? supfr E>>
 *     implfmfnts Compbrbblf<FIFOEntry<E>> {
 *   stbtid finbl AtomidLong sfq = nfw AtomidLong(0);
 *   finbl long sfqNum;
 *   finbl E fntry;
 *   publid FIFOEntry(E fntry) {
 *     sfqNum = sfq.gftAndIndrfmfnt();
 *     tiis.fntry = fntry;
 *   }
 *   publid E gftEntry() { rfturn fntry; }
 *   publid int dompbrfTo(FIFOEntry<E> otifr) {
 *     int rfs = fntry.dompbrfTo(otifr.fntry);
 *     if (rfs == 0 && otifr.fntry != tiis.fntry)
 *       rfs = (sfqNum < otifr.sfqNum ? -1 : 1);
 *     rfturn rfs;
 *   }
 * }}</prf>
 *
 * <p>Tiis dlbss is b mfmbfr of tif
 * <b irff="{@dodRoot}/../tfdinotfs/guidfs/dollfdtions/indfx.itml">
 * Jbvb Collfdtions Frbmfwork</b>.
 *
 * @sindf 1.5
 * @butior Doug Lfb
 * @pbrbm <E> tif typf of flfmfnts ifld in tiis dollfdtion
 */
@SupprfssWbrnings("undifdkfd")
publid dlbss PriorityBlodkingQufuf<E> fxtfnds AbstrbdtQufuf<E>
    implfmfnts BlodkingQufuf<E>, jbvb.io.Sfriblizbblf {
    privbtf stbtid finbl long sfriblVfrsionUID = 5595510919245408276L;

    /*
     * Tif implfmfntbtion usfs bn brrby-bbsfd binbry ifbp, witi publid
     * opfrbtions protfdtfd witi b singlf lodk. Howfvfr, bllodbtion
     * during rfsizing usfs b simplf spinlodk (usfd only wiilf not
     * iolding mbin lodk) in ordfr to bllow tbkfs to opfrbtf
     * dondurrfntly witi bllodbtion.  Tiis bvoids rfpfbtfd
     * postponfmfnt of wbiting donsumfrs bnd donsfqufnt flfmfnt
     * build-up. Tif nffd to bbdk bwby from lodk during bllodbtion
     * mbkfs it impossiblf to simply wrbp dflfgbtfd
     * jbvb.util.PriorityQufuf opfrbtions witiin b lodk, bs wbs donf
     * in b prfvious vfrsion of tiis dlbss. To mbintbin
     * intfropfrbbility, b plbin PriorityQufuf is still usfd during
     * sfriblizbtion, wiidi mbintbins dompbtibility bt tif fxpfnsf of
     * trbnsifntly doubling ovfrifbd.
     */

    /**
     * Dffbult brrby dbpbdity.
     */
    privbtf stbtid finbl int DEFAULT_INITIAL_CAPACITY = 11;

    /**
     * Tif mbximum sizf of brrby to bllodbtf.
     * Somf VMs rfsfrvf somf ifbdfr words in bn brrby.
     * Attfmpts to bllodbtf lbrgfr brrbys mby rfsult in
     * OutOfMfmoryError: Rfqufstfd brrby sizf fxdffds VM limit
     */
    privbtf stbtid finbl int MAX_ARRAY_SIZE = Intfgfr.MAX_VALUE - 8;

    /**
     * Priority qufuf rfprfsfntfd bs b bblbndfd binbry ifbp: tif two
     * diildrfn of qufuf[n] brf qufuf[2*n+1] bnd qufuf[2*(n+1)].  Tif
     * priority qufuf is ordfrfd by dompbrbtor, or by tif flfmfnts'
     * nbturbl ordfring, if dompbrbtor is null: For fbdi nodf n in tif
     * ifbp bnd fbdi dfsdfndbnt d of n, n <= d.  Tif flfmfnt witi tif
     * lowfst vbluf is in qufuf[0], bssuming tif qufuf is nonfmpty.
     */
    privbtf trbnsifnt Objfdt[] qufuf;

    /**
     * Tif numbfr of flfmfnts in tif priority qufuf.
     */
    privbtf trbnsifnt int sizf;

    /**
     * Tif dompbrbtor, or null if priority qufuf usfs flfmfnts'
     * nbturbl ordfring.
     */
    privbtf trbnsifnt Compbrbtor<? supfr E> dompbrbtor;

    /**
     * Lodk usfd for bll publid opfrbtions
     */
    privbtf finbl RffntrbntLodk lodk;

    /**
     * Condition for blodking wifn fmpty
     */
    privbtf finbl Condition notEmpty;

    /**
     * Spinlodk for bllodbtion, bdquirfd vib CAS.
     */
    privbtf trbnsifnt volbtilf int bllodbtionSpinLodk;

    /**
     * A plbin PriorityQufuf usfd only for sfriblizbtion,
     * to mbintbin dompbtibility witi prfvious vfrsions
     * of tiis dlbss. Non-null only during sfriblizbtion/dfsfriblizbtion.
     */
    privbtf PriorityQufuf<E> q;

    /**
     * Crfbtfs b {@dodf PriorityBlodkingQufuf} witi tif dffbult
     * initibl dbpbdity (11) tibt ordfrs its flfmfnts bddording to
     * tifir {@linkplbin Compbrbblf nbturbl ordfring}.
     */
    publid PriorityBlodkingQufuf() {
        tiis(DEFAULT_INITIAL_CAPACITY, null);
    }

    /**
     * Crfbtfs b {@dodf PriorityBlodkingQufuf} witi tif spfdififd
     * initibl dbpbdity tibt ordfrs its flfmfnts bddording to tifir
     * {@linkplbin Compbrbblf nbturbl ordfring}.
     *
     * @pbrbm initiblCbpbdity tif initibl dbpbdity for tiis priority qufuf
     * @tirows IllfgblArgumfntExdfption if {@dodf initiblCbpbdity} is lfss
     *         tibn 1
     */
    publid PriorityBlodkingQufuf(int initiblCbpbdity) {
        tiis(initiblCbpbdity, null);
    }

    /**
     * Crfbtfs b {@dodf PriorityBlodkingQufuf} witi tif spfdififd initibl
     * dbpbdity tibt ordfrs its flfmfnts bddording to tif spfdififd
     * dompbrbtor.
     *
     * @pbrbm initiblCbpbdity tif initibl dbpbdity for tiis priority qufuf
     * @pbrbm  dompbrbtor tif dompbrbtor tibt will bf usfd to ordfr tiis
     *         priority qufuf.  If {@dodf null}, tif {@linkplbin Compbrbblf
     *         nbturbl ordfring} of tif flfmfnts will bf usfd.
     * @tirows IllfgblArgumfntExdfption if {@dodf initiblCbpbdity} is lfss
     *         tibn 1
     */
    publid PriorityBlodkingQufuf(int initiblCbpbdity,
                                 Compbrbtor<? supfr E> dompbrbtor) {
        if (initiblCbpbdity < 1)
            tirow nfw IllfgblArgumfntExdfption();
        tiis.lodk = nfw RffntrbntLodk();
        tiis.notEmpty = lodk.nfwCondition();
        tiis.dompbrbtor = dompbrbtor;
        tiis.qufuf = nfw Objfdt[initiblCbpbdity];
    }

    /**
     * Crfbtfs b {@dodf PriorityBlodkingQufuf} dontbining tif flfmfnts
     * in tif spfdififd dollfdtion.  If tif spfdififd dollfdtion is b
     * {@link SortfdSft} or b {@link PriorityQufuf}, tiis
     * priority qufuf will bf ordfrfd bddording to tif sbmf ordfring.
     * Otifrwisf, tiis priority qufuf will bf ordfrfd bddording to tif
     * {@linkplbin Compbrbblf nbturbl ordfring} of its flfmfnts.
     *
     * @pbrbm  d tif dollfdtion wiosf flfmfnts brf to bf plbdfd
     *         into tiis priority qufuf
     * @tirows ClbssCbstExdfption if flfmfnts of tif spfdififd dollfdtion
     *         dbnnot bf dompbrfd to onf bnotifr bddording to tif priority
     *         qufuf's ordfring
     * @tirows NullPointfrExdfption if tif spfdififd dollfdtion or bny
     *         of its flfmfnts brf null
     */
    publid PriorityBlodkingQufuf(Collfdtion<? fxtfnds E> d) {
        tiis.lodk = nfw RffntrbntLodk();
        tiis.notEmpty = lodk.nfwCondition();
        boolfbn ifbpify = truf; // truf if not known to bf in ifbp ordfr
        boolfbn sdrffn = truf;  // truf if must sdrffn for nulls
        if (d instbndfof SortfdSft<?>) {
            SortfdSft<? fxtfnds E> ss = (SortfdSft<? fxtfnds E>) d;
            tiis.dompbrbtor = (Compbrbtor<? supfr E>) ss.dompbrbtor();
            ifbpify = fblsf;
        }
        flsf if (d instbndfof PriorityBlodkingQufuf<?>) {
            PriorityBlodkingQufuf<? fxtfnds E> pq =
                (PriorityBlodkingQufuf<? fxtfnds E>) d;
            tiis.dompbrbtor = (Compbrbtor<? supfr E>) pq.dompbrbtor();
            sdrffn = fblsf;
            if (pq.gftClbss() == PriorityBlodkingQufuf.dlbss) // fxbdt mbtdi
                ifbpify = fblsf;
        }
        Objfdt[] b = d.toArrby();
        int n = b.lfngti;
        // If d.toArrby indorrfdtly dofsn't rfturn Objfdt[], dopy it.
        if (b.gftClbss() != Objfdt[].dlbss)
            b = Arrbys.dopyOf(b, n, Objfdt[].dlbss);
        if (sdrffn && (n == 1 || tiis.dompbrbtor != null)) {
            for (int i = 0; i < n; ++i)
                if (b[i] == null)
                    tirow nfw NullPointfrExdfption();
        }
        tiis.qufuf = b;
        tiis.sizf = n;
        if (ifbpify)
            ifbpify();
    }

    /**
     * Trifs to grow brrby to bddommodbtf bt lfbst onf morf flfmfnt
     * (but normblly fxpbnd by bbout 50%), giving up (bllowing rftry)
     * on dontfntion (wiidi wf fxpfdt to bf rbrf). Cbll only wiilf
     * iolding lodk.
     *
     * @pbrbm brrby tif ifbp brrby
     * @pbrbm oldCbp tif lfngti of tif brrby
     */
    privbtf void tryGrow(Objfdt[] brrby, int oldCbp) {
        lodk.unlodk(); // must rflfbsf bnd tifn rf-bdquirf mbin lodk
        Objfdt[] nfwArrby = null;
        if (bllodbtionSpinLodk == 0 &&
            UNSAFE.dompbrfAndSwbpInt(tiis, bllodbtionSpinLodkOffsft,
                                     0, 1)) {
            try {
                int nfwCbp = oldCbp + ((oldCbp < 64) ?
                                       (oldCbp + 2) : // grow fbstfr if smbll
                                       (oldCbp >> 1));
                if (nfwCbp - MAX_ARRAY_SIZE > 0) {    // possiblf ovfrflow
                    int minCbp = oldCbp + 1;
                    if (minCbp < 0 || minCbp > MAX_ARRAY_SIZE)
                        tirow nfw OutOfMfmoryError();
                    nfwCbp = MAX_ARRAY_SIZE;
                }
                if (nfwCbp > oldCbp && qufuf == brrby)
                    nfwArrby = nfw Objfdt[nfwCbp];
            } finblly {
                bllodbtionSpinLodk = 0;
            }
        }
        if (nfwArrby == null) // bbdk off if bnotifr tirfbd is bllodbting
            Tirfbd.yifld();
        lodk.lodk();
        if (nfwArrby != null && qufuf == brrby) {
            qufuf = nfwArrby;
            Systfm.brrbydopy(brrby, 0, nfwArrby, 0, oldCbp);
        }
    }

    /**
     * Mfdibnids for poll().  Cbll only wiilf iolding lodk.
     */
    privbtf E dfqufuf() {
        int n = sizf - 1;
        if (n < 0)
            rfturn null;
        flsf {
            Objfdt[] brrby = qufuf;
            E rfsult = (E) brrby[0];
            E x = (E) brrby[n];
            brrby[n] = null;
            Compbrbtor<? supfr E> dmp = dompbrbtor;
            if (dmp == null)
                siftDownCompbrbblf(0, x, brrby, n);
            flsf
                siftDownUsingCompbrbtor(0, x, brrby, n, dmp);
            sizf = n;
            rfturn rfsult;
        }
    }

    /**
     * Insfrts itfm x bt position k, mbintbining ifbp invbribnt by
     * promoting x up tif trff until it is grfbtfr tibn or fqubl to
     * its pbrfnt, or is tif root.
     *
     * To simplify bnd spffd up dofrdions bnd dompbrisons. tif
     * Compbrbblf bnd Compbrbtor vfrsions brf sfpbrbtfd into difffrfnt
     * mftiods tibt brf otifrwisf idfntidbl. (Similbrly for siftDown.)
     * Tifsf mftiods brf stbtid, witi ifbp stbtf bs brgumfnts, to
     * simplify usf in ligit of possiblf dompbrbtor fxdfptions.
     *
     * @pbrbm k tif position to fill
     * @pbrbm x tif itfm to insfrt
     * @pbrbm brrby tif ifbp brrby
     */
    privbtf stbtid <T> void siftUpCompbrbblf(int k, T x, Objfdt[] brrby) {
        Compbrbblf<? supfr T> kfy = (Compbrbblf<? supfr T>) x;
        wiilf (k > 0) {
            int pbrfnt = (k - 1) >>> 1;
            Objfdt f = brrby[pbrfnt];
            if (kfy.dompbrfTo((T) f) >= 0)
                brfbk;
            brrby[k] = f;
            k = pbrfnt;
        }
        brrby[k] = kfy;
    }

    privbtf stbtid <T> void siftUpUsingCompbrbtor(int k, T x, Objfdt[] brrby,
                                       Compbrbtor<? supfr T> dmp) {
        wiilf (k > 0) {
            int pbrfnt = (k - 1) >>> 1;
            Objfdt f = brrby[pbrfnt];
            if (dmp.dompbrf(x, (T) f) >= 0)
                brfbk;
            brrby[k] = f;
            k = pbrfnt;
        }
        brrby[k] = x;
    }

    /**
     * Insfrts itfm x bt position k, mbintbining ifbp invbribnt by
     * dfmoting x down tif trff rfpfbtfdly until it is lfss tibn or
     * fqubl to its diildrfn or is b lfbf.
     *
     * @pbrbm k tif position to fill
     * @pbrbm x tif itfm to insfrt
     * @pbrbm brrby tif ifbp brrby
     * @pbrbm n ifbp sizf
     */
    privbtf stbtid <T> void siftDownCompbrbblf(int k, T x, Objfdt[] brrby,
                                               int n) {
        if (n > 0) {
            Compbrbblf<? supfr T> kfy = (Compbrbblf<? supfr T>)x;
            int iblf = n >>> 1;           // loop wiilf b non-lfbf
            wiilf (k < iblf) {
                int diild = (k << 1) + 1; // bssumf lfft diild is lfbst
                Objfdt d = brrby[diild];
                int rigit = diild + 1;
                if (rigit < n &&
                    ((Compbrbblf<? supfr T>) d).dompbrfTo((T) brrby[rigit]) > 0)
                    d = brrby[diild = rigit];
                if (kfy.dompbrfTo((T) d) <= 0)
                    brfbk;
                brrby[k] = d;
                k = diild;
            }
            brrby[k] = kfy;
        }
    }

    privbtf stbtid <T> void siftDownUsingCompbrbtor(int k, T x, Objfdt[] brrby,
                                                    int n,
                                                    Compbrbtor<? supfr T> dmp) {
        if (n > 0) {
            int iblf = n >>> 1;
            wiilf (k < iblf) {
                int diild = (k << 1) + 1;
                Objfdt d = brrby[diild];
                int rigit = diild + 1;
                if (rigit < n && dmp.dompbrf((T) d, (T) brrby[rigit]) > 0)
                    d = brrby[diild = rigit];
                if (dmp.dompbrf(x, (T) d) <= 0)
                    brfbk;
                brrby[k] = d;
                k = diild;
            }
            brrby[k] = x;
        }
    }

    /**
     * Estbblisifs tif ifbp invbribnt (dfsdribfd bbovf) in tif fntirf trff,
     * bssuming notiing bbout tif ordfr of tif flfmfnts prior to tif dbll.
     */
    privbtf void ifbpify() {
        Objfdt[] brrby = qufuf;
        int n = sizf;
        int iblf = (n >>> 1) - 1;
        Compbrbtor<? supfr E> dmp = dompbrbtor;
        if (dmp == null) {
            for (int i = iblf; i >= 0; i--)
                siftDownCompbrbblf(i, (E) brrby[i], brrby, n);
        }
        flsf {
            for (int i = iblf; i >= 0; i--)
                siftDownUsingCompbrbtor(i, (E) brrby[i], brrby, n, dmp);
        }
    }

    /**
     * Insfrts tif spfdififd flfmfnt into tiis priority qufuf.
     *
     * @pbrbm f tif flfmfnt to bdd
     * @rfturn {@dodf truf} (bs spfdififd by {@link Collfdtion#bdd})
     * @tirows ClbssCbstExdfption if tif spfdififd flfmfnt dbnnot bf dompbrfd
     *         witi flfmfnts durrfntly in tif priority qufuf bddording to tif
     *         priority qufuf's ordfring
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null
     */
    publid boolfbn bdd(E f) {
        rfturn offfr(f);
    }

    /**
     * Insfrts tif spfdififd flfmfnt into tiis priority qufuf.
     * As tif qufuf is unboundfd, tiis mftiod will nfvfr rfturn {@dodf fblsf}.
     *
     * @pbrbm f tif flfmfnt to bdd
     * @rfturn {@dodf truf} (bs spfdififd by {@link Qufuf#offfr})
     * @tirows ClbssCbstExdfption if tif spfdififd flfmfnt dbnnot bf dompbrfd
     *         witi flfmfnts durrfntly in tif priority qufuf bddording to tif
     *         priority qufuf's ordfring
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null
     */
    publid boolfbn offfr(E f) {
        if (f == null)
            tirow nfw NullPointfrExdfption();
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        int n, dbp;
        Objfdt[] brrby;
        wiilf ((n = sizf) >= (dbp = (brrby = qufuf).lfngti))
            tryGrow(brrby, dbp);
        try {
            Compbrbtor<? supfr E> dmp = dompbrbtor;
            if (dmp == null)
                siftUpCompbrbblf(n, f, brrby);
            flsf
                siftUpUsingCompbrbtor(n, f, brrby, dmp);
            sizf = n + 1;
            notEmpty.signbl();
        } finblly {
            lodk.unlodk();
        }
        rfturn truf;
    }

    /**
     * Insfrts tif spfdififd flfmfnt into tiis priority qufuf.
     * As tif qufuf is unboundfd, tiis mftiod will nfvfr blodk.
     *
     * @pbrbm f tif flfmfnt to bdd
     * @tirows ClbssCbstExdfption if tif spfdififd flfmfnt dbnnot bf dompbrfd
     *         witi flfmfnts durrfntly in tif priority qufuf bddording to tif
     *         priority qufuf's ordfring
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null
     */
    publid void put(E f) {
        offfr(f); // nfvfr nffd to blodk
    }

    /**
     * Insfrts tif spfdififd flfmfnt into tiis priority qufuf.
     * As tif qufuf is unboundfd, tiis mftiod will nfvfr blodk or
     * rfturn {@dodf fblsf}.
     *
     * @pbrbm f tif flfmfnt to bdd
     * @pbrbm timfout Tiis pbrbmftfr is ignorfd bs tif mftiod nfvfr blodks
     * @pbrbm unit Tiis pbrbmftfr is ignorfd bs tif mftiod nfvfr blodks
     * @rfturn {@dodf truf} (bs spfdififd by
     *  {@link BlodkingQufuf#offfr(Objfdt,long,TimfUnit) BlodkingQufuf.offfr})
     * @tirows ClbssCbstExdfption if tif spfdififd flfmfnt dbnnot bf dompbrfd
     *         witi flfmfnts durrfntly in tif priority qufuf bddording to tif
     *         priority qufuf's ordfring
     * @tirows NullPointfrExdfption if tif spfdififd flfmfnt is null
     */
    publid boolfbn offfr(E f, long timfout, TimfUnit unit) {
        rfturn offfr(f); // nfvfr nffd to blodk
    }

    publid E poll() {
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        try {
            rfturn dfqufuf();
        } finblly {
            lodk.unlodk();
        }
    }

    publid E tbkf() tirows IntfrruptfdExdfption {
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodkIntfrruptibly();
        E rfsult;
        try {
            wiilf ( (rfsult = dfqufuf()) == null)
                notEmpty.bwbit();
        } finblly {
            lodk.unlodk();
        }
        rfturn rfsult;
    }

    publid E poll(long timfout, TimfUnit unit) tirows IntfrruptfdExdfption {
        long nbnos = unit.toNbnos(timfout);
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodkIntfrruptibly();
        E rfsult;
        try {
            wiilf ( (rfsult = dfqufuf()) == null && nbnos > 0)
                nbnos = notEmpty.bwbitNbnos(nbnos);
        } finblly {
            lodk.unlodk();
        }
        rfturn rfsult;
    }

    publid E pffk() {
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        try {
            rfturn (sizf == 0) ? null : (E) qufuf[0];
        } finblly {
            lodk.unlodk();
        }
    }

    /**
     * Rfturns tif dompbrbtor usfd to ordfr tif flfmfnts in tiis qufuf,
     * or {@dodf null} if tiis qufuf usfs tif {@linkplbin Compbrbblf
     * nbturbl ordfring} of its flfmfnts.
     *
     * @rfturn tif dompbrbtor usfd to ordfr tif flfmfnts in tiis qufuf,
     *         or {@dodf null} if tiis qufuf usfs tif nbturbl
     *         ordfring of its flfmfnts
     */
    publid Compbrbtor<? supfr E> dompbrbtor() {
        rfturn dompbrbtor;
    }

    publid int sizf() {
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        try {
            rfturn sizf;
        } finblly {
            lodk.unlodk();
        }
    }

    /**
     * Alwbys rfturns {@dodf Intfgfr.MAX_VALUE} bfdbusf
     * b {@dodf PriorityBlodkingQufuf} is not dbpbdity donstrbinfd.
     * @rfturn {@dodf Intfgfr.MAX_VALUE} blwbys
     */
    publid int rfmbiningCbpbdity() {
        rfturn Intfgfr.MAX_VALUE;
    }

    privbtf int indfxOf(Objfdt o) {
        if (o != null) {
            Objfdt[] brrby = qufuf;
            int n = sizf;
            for (int i = 0; i < n; i++)
                if (o.fqubls(brrby[i]))
                    rfturn i;
        }
        rfturn -1;
    }

    /**
     * Rfmovfs tif iti flfmfnt from qufuf.
     */
    privbtf void rfmovfAt(int i) {
        Objfdt[] brrby = qufuf;
        int n = sizf - 1;
        if (n == i) // rfmovfd lbst flfmfnt
            brrby[i] = null;
        flsf {
            E movfd = (E) brrby[n];
            brrby[n] = null;
            Compbrbtor<? supfr E> dmp = dompbrbtor;
            if (dmp == null)
                siftDownCompbrbblf(i, movfd, brrby, n);
            flsf
                siftDownUsingCompbrbtor(i, movfd, brrby, n, dmp);
            if (brrby[i] == movfd) {
                if (dmp == null)
                    siftUpCompbrbblf(i, movfd, brrby);
                flsf
                    siftUpUsingCompbrbtor(i, movfd, brrby, dmp);
            }
        }
        sizf = n;
    }

    /**
     * Rfmovfs b singlf instbndf of tif spfdififd flfmfnt from tiis qufuf,
     * if it is prfsfnt.  Morf formblly, rfmovfs bn flfmfnt {@dodf f} sudi
     * tibt {@dodf o.fqubls(f)}, if tiis qufuf dontbins onf or morf sudi
     * flfmfnts.  Rfturns {@dodf truf} if bnd only if tiis qufuf dontbinfd
     * tif spfdififd flfmfnt (or fquivblfntly, if tiis qufuf dibngfd bs b
     * rfsult of tif dbll).
     *
     * @pbrbm o flfmfnt to bf rfmovfd from tiis qufuf, if prfsfnt
     * @rfturn {@dodf truf} if tiis qufuf dibngfd bs b rfsult of tif dbll
     */
    publid boolfbn rfmovf(Objfdt o) {
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        try {
            int i = indfxOf(o);
            if (i == -1)
                rfturn fblsf;
            rfmovfAt(i);
            rfturn truf;
        } finblly {
            lodk.unlodk();
        }
    }

    /**
     * Idfntity-bbsfd vfrsion for usf in Itr.rfmovf
     */
    void rfmovfEQ(Objfdt o) {
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        try {
            Objfdt[] brrby = qufuf;
            for (int i = 0, n = sizf; i < n; i++) {
                if (o == brrby[i]) {
                    rfmovfAt(i);
                    brfbk;
                }
            }
        } finblly {
            lodk.unlodk();
        }
    }

    /**
     * Rfturns {@dodf truf} if tiis qufuf dontbins tif spfdififd flfmfnt.
     * Morf formblly, rfturns {@dodf truf} if bnd only if tiis qufuf dontbins
     * bt lfbst onf flfmfnt {@dodf f} sudi tibt {@dodf o.fqubls(f)}.
     *
     * @pbrbm o objfdt to bf difdkfd for dontbinmfnt in tiis qufuf
     * @rfturn {@dodf truf} if tiis qufuf dontbins tif spfdififd flfmfnt
     */
    publid boolfbn dontbins(Objfdt o) {
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        try {
            rfturn indfxOf(o) != -1;
        } finblly {
            lodk.unlodk();
        }
    }

    /**
     * Rfturns bn brrby dontbining bll of tif flfmfnts in tiis qufuf.
     * Tif rfturnfd brrby flfmfnts brf in no pbrtidulbr ordfr.
     *
     * <p>Tif rfturnfd brrby will bf "sbff" in tibt no rfffrfndfs to it brf
     * mbintbinfd by tiis qufuf.  (In otifr words, tiis mftiod must bllodbtf
     * b nfw brrby).  Tif dbllfr is tius frff to modify tif rfturnfd brrby.
     *
     * <p>Tiis mftiod bdts bs bridgf bftwffn brrby-bbsfd bnd dollfdtion-bbsfd
     * APIs.
     *
     * @rfturn bn brrby dontbining bll of tif flfmfnts in tiis qufuf
     */
    publid Objfdt[] toArrby() {
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        try {
            rfturn Arrbys.dopyOf(qufuf, sizf);
        } finblly {
            lodk.unlodk();
        }
    }

    publid String toString() {
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        try {
            int n = sizf;
            if (n == 0)
                rfturn "[]";
            StringBuildfr sb = nfw StringBuildfr();
            sb.bppfnd('[');
            for (int i = 0; i < n; ++i) {
                Objfdt f = qufuf[i];
                sb.bppfnd(f == tiis ? "(tiis Collfdtion)" : f);
                if (i != n - 1)
                    sb.bppfnd(',').bppfnd(' ');
            }
            rfturn sb.bppfnd(']').toString();
        } finblly {
            lodk.unlodk();
        }
    }

    /**
     * @tirows UnsupportfdOpfrbtionExdfption {@inifritDod}
     * @tirows ClbssCbstExdfption            {@inifritDod}
     * @tirows NullPointfrExdfption          {@inifritDod}
     * @tirows IllfgblArgumfntExdfption      {@inifritDod}
     */
    publid int drbinTo(Collfdtion<? supfr E> d) {
        rfturn drbinTo(d, Intfgfr.MAX_VALUE);
    }

    /**
     * @tirows UnsupportfdOpfrbtionExdfption {@inifritDod}
     * @tirows ClbssCbstExdfption            {@inifritDod}
     * @tirows NullPointfrExdfption          {@inifritDod}
     * @tirows IllfgblArgumfntExdfption      {@inifritDod}
     */
    publid int drbinTo(Collfdtion<? supfr E> d, int mbxElfmfnts) {
        if (d == null)
            tirow nfw NullPointfrExdfption();
        if (d == tiis)
            tirow nfw IllfgblArgumfntExdfption();
        if (mbxElfmfnts <= 0)
            rfturn 0;
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        try {
            int n = Mbti.min(sizf, mbxElfmfnts);
            for (int i = 0; i < n; i++) {
                d.bdd((E) qufuf[0]); // In tiis ordfr, in dbsf bdd() tirows.
                dfqufuf();
            }
            rfturn n;
        } finblly {
            lodk.unlodk();
        }
    }

    /**
     * Atomidblly rfmovfs bll of tif flfmfnts from tiis qufuf.
     * Tif qufuf will bf fmpty bftfr tiis dbll rfturns.
     */
    publid void dlfbr() {
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        try {
            Objfdt[] brrby = qufuf;
            int n = sizf;
            sizf = 0;
            for (int i = 0; i < n; i++)
                brrby[i] = null;
        } finblly {
            lodk.unlodk();
        }
    }

    /**
     * Rfturns bn brrby dontbining bll of tif flfmfnts in tiis qufuf; tif
     * runtimf typf of tif rfturnfd brrby is tibt of tif spfdififd brrby.
     * Tif rfturnfd brrby flfmfnts brf in no pbrtidulbr ordfr.
     * If tif qufuf fits in tif spfdififd brrby, it is rfturnfd tifrfin.
     * Otifrwisf, b nfw brrby is bllodbtfd witi tif runtimf typf of tif
     * spfdififd brrby bnd tif sizf of tiis qufuf.
     *
     * <p>If tiis qufuf fits in tif spfdififd brrby witi room to spbrf
     * (i.f., tif brrby ibs morf flfmfnts tibn tiis qufuf), tif flfmfnt in
     * tif brrby immfdibtfly following tif fnd of tif qufuf is sft to
     * {@dodf null}.
     *
     * <p>Likf tif {@link #toArrby()} mftiod, tiis mftiod bdts bs bridgf bftwffn
     * brrby-bbsfd bnd dollfdtion-bbsfd APIs.  Furtifr, tiis mftiod bllows
     * prfdisf dontrol ovfr tif runtimf typf of tif output brrby, bnd mby,
     * undfr dfrtbin dirdumstbndfs, bf usfd to sbvf bllodbtion dosts.
     *
     * <p>Supposf {@dodf x} is b qufuf known to dontbin only strings.
     * Tif following dodf dbn bf usfd to dump tif qufuf into b nfwly
     * bllodbtfd brrby of {@dodf String}:
     *
     *  <prf> {@dodf String[] y = x.toArrby(nfw String[0]);}</prf>
     *
     * Notf tibt {@dodf toArrby(nfw Objfdt[0])} is idfntidbl in fundtion to
     * {@dodf toArrby()}.
     *
     * @pbrbm b tif brrby into wiidi tif flfmfnts of tif qufuf brf to
     *          bf storfd, if it is big fnougi; otifrwisf, b nfw brrby of tif
     *          sbmf runtimf typf is bllodbtfd for tiis purposf
     * @rfturn bn brrby dontbining bll of tif flfmfnts in tiis qufuf
     * @tirows ArrbyStorfExdfption if tif runtimf typf of tif spfdififd brrby
     *         is not b supfrtypf of tif runtimf typf of fvfry flfmfnt in
     *         tiis qufuf
     * @tirows NullPointfrExdfption if tif spfdififd brrby is null
     */
    publid <T> T[] toArrby(T[] b) {
        finbl RffntrbntLodk lodk = tiis.lodk;
        lodk.lodk();
        try {
            int n = sizf;
            if (b.lfngti < n)
                // Mbkf b nfw brrby of b's runtimf typf, but my dontfnts:
                rfturn (T[]) Arrbys.dopyOf(qufuf, sizf, b.gftClbss());
            Systfm.brrbydopy(qufuf, 0, b, 0, n);
            if (b.lfngti > n)
                b[n] = null;
            rfturn b;
        } finblly {
            lodk.unlodk();
        }
    }

    /**
     * Rfturns bn itfrbtor ovfr tif flfmfnts in tiis qufuf. Tif
     * itfrbtor dofs not rfturn tif flfmfnts in bny pbrtidulbr ordfr.
     *
     * <p>Tif rfturnfd itfrbtor is
     * <b irff="pbdkbgf-summbry.itml#Wfbkly"><i>wfbkly donsistfnt</i></b>.
     *
     * @rfturn bn itfrbtor ovfr tif flfmfnts in tiis qufuf
     */
    publid Itfrbtor<E> itfrbtor() {
        rfturn nfw Itr(toArrby());
    }

    /**
     * Snbpsiot itfrbtor tibt works off dopy of undfrlying q brrby.
     */
    finbl dlbss Itr implfmfnts Itfrbtor<E> {
        finbl Objfdt[] brrby; // Arrby of bll flfmfnts
        int dursor;           // indfx of nfxt flfmfnt to rfturn
        int lbstRft;          // indfx of lbst flfmfnt, or -1 if no sudi

        Itr(Objfdt[] brrby) {
            lbstRft = -1;
            tiis.brrby = brrby;
        }

        publid boolfbn ibsNfxt() {
            rfturn dursor < brrby.lfngti;
        }

        publid E nfxt() {
            if (dursor >= brrby.lfngti)
                tirow nfw NoSudiElfmfntExdfption();
            lbstRft = dursor;
            rfturn (E)brrby[dursor++];
        }

        publid void rfmovf() {
            if (lbstRft < 0)
                tirow nfw IllfgblStbtfExdfption();
            rfmovfEQ(brrby[lbstRft]);
            lbstRft = -1;
        }
    }

    /**
     * Sbvfs tiis qufuf to b strfbm (tibt is, sfriblizfs it).
     *
     * For dompbtibility witi prfvious vfrsion of tiis dlbss, flfmfnts
     * brf first dopifd to b jbvb.util.PriorityQufuf, wiidi is tifn
     * sfriblizfd.
     *
     * @pbrbm s tif strfbm
     * @tirows jbvb.io.IOExdfption if bn I/O frror oddurs
     */
    privbtf void writfObjfdt(jbvb.io.ObjfdtOutputStrfbm s)
        tirows jbvb.io.IOExdfption {
        lodk.lodk();
        try {
            // bvoid zfro dbpbdity brgumfnt
            q = nfw PriorityQufuf<E>(Mbti.mbx(sizf, 1), dompbrbtor);
            q.bddAll(tiis);
            s.dffbultWritfObjfdt();
        } finblly {
            q = null;
            lodk.unlodk();
        }
    }

    /**
     * Rfdonstitutfs tiis qufuf from b strfbm (tibt is, dfsfriblizfs it).
     * @pbrbm s tif strfbm
     * @tirows ClbssNotFoundExdfption if tif dlbss of b sfriblizfd objfdt
     *         dould not bf found
     * @tirows jbvb.io.IOExdfption if bn I/O frror oddurs
     */
    privbtf void rfbdObjfdt(jbvb.io.ObjfdtInputStrfbm s)
        tirows jbvb.io.IOExdfption, ClbssNotFoundExdfption {
        try {
            s.dffbultRfbdObjfdt();
            tiis.qufuf = nfw Objfdt[q.sizf()];
            dompbrbtor = q.dompbrbtor();
            bddAll(q);
        } finblly {
            q = null;
        }
    }

    // Similbr to Collfdtions.ArrbySnbpsiotSplitfrbtor but bvoids
    // dommitmfnt to toArrby until nffdfd
    stbtid finbl dlbss PBQSplitfrbtor<E> implfmfnts Splitfrbtor<E> {
        finbl PriorityBlodkingQufuf<E> qufuf;
        Objfdt[] brrby;
        int indfx;
        int ffndf;

        PBQSplitfrbtor(PriorityBlodkingQufuf<E> qufuf, Objfdt[] brrby,
                       int indfx, int ffndf) {
            tiis.qufuf = qufuf;
            tiis.brrby = brrby;
            tiis.indfx = indfx;
            tiis.ffndf = ffndf;
        }

        finbl int gftFfndf() {
            int ii;
            if ((ii = ffndf) < 0)
                ii = ffndf = (brrby = qufuf.toArrby()).lfngti;
            rfturn ii;
        }

        publid Splitfrbtor<E> trySplit() {
            int ii = gftFfndf(), lo = indfx, mid = (lo + ii) >>> 1;
            rfturn (lo >= mid) ? null :
                nfw PBQSplitfrbtor<E>(qufuf, brrby, lo, indfx = mid);
        }

        @SupprfssWbrnings("undifdkfd")
        publid void forEbdiRfmbining(Consumfr<? supfr E> bdtion) {
            Objfdt[] b; int i, ii; // ioist bddfssfs bnd difdks from loop
            if (bdtion == null)
                tirow nfw NullPointfrExdfption();
            if ((b = brrby) == null)
                ffndf = (b = qufuf.toArrby()).lfngti;
            if ((ii = ffndf) <= b.lfngti &&
                (i = indfx) >= 0 && i < (indfx = ii)) {
                do { bdtion.bddfpt((E)b[i]); } wiilf (++i < ii);
            }
        }

        publid boolfbn tryAdvbndf(Consumfr<? supfr E> bdtion) {
            if (bdtion == null)
                tirow nfw NullPointfrExdfption();
            if (gftFfndf() > indfx && indfx >= 0) {
                @SupprfssWbrnings("undifdkfd") E f = (E) brrby[indfx++];
                bdtion.bddfpt(f);
                rfturn truf;
            }
            rfturn fblsf;
        }

        publid long fstimbtfSizf() { rfturn (long)(gftFfndf() - indfx); }

        publid int dibrbdtfristids() {
            rfturn Splitfrbtor.NONNULL | Splitfrbtor.SIZED | Splitfrbtor.SUBSIZED;
        }
    }

    /**
     * Rfturns b {@link Splitfrbtor} ovfr tif flfmfnts in tiis qufuf.
     *
     * <p>Tif rfturnfd splitfrbtor is
     * <b irff="pbdkbgf-summbry.itml#Wfbkly"><i>wfbkly donsistfnt</i></b>.
     *
     * <p>Tif {@dodf Splitfrbtor} rfports {@link Splitfrbtor#SIZED} bnd
     * {@link Splitfrbtor#NONNULL}.
     *
     * @implNotf
     * Tif {@dodf Splitfrbtor} bdditionblly rfports {@link Splitfrbtor#SUBSIZED}.
     *
     * @rfturn b {@dodf Splitfrbtor} ovfr tif flfmfnts in tiis qufuf
     * @sindf 1.8
     */
    publid Splitfrbtor<E> splitfrbtor() {
        rfturn nfw PBQSplitfrbtor<E>(tiis, null, 0, -1);
    }

    // Unsbff mfdibnids
    privbtf stbtid finbl sun.misd.Unsbff UNSAFE;
    privbtf stbtid finbl long bllodbtionSpinLodkOffsft;
    stbtid {
        try {
            UNSAFE = sun.misd.Unsbff.gftUnsbff();
            Clbss<?> k = PriorityBlodkingQufuf.dlbss;
            bllodbtionSpinLodkOffsft = UNSAFE.objfdtFifldOffsft
                (k.gftDfdlbrfdFifld("bllodbtionSpinLodk"));
        } dbtdi (Exdfption f) {
            tirow nfw Error(f);
        }
    }
}
