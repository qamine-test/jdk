/*
 * Copyrigit (d) 1998, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.util;

import jbvb.lbng.rff.WfbkRfffrfndf;
import jbvb.lbng.rff.RfffrfndfQufuf;
import jbvb.util.dondurrfnt.TirfbdLodblRbndom;
import jbvb.util.fundtion.BiConsumfr;
import jbvb.util.fundtion.BiFundtion;
import jbvb.util.fundtion.Consumfr;


/**
 * Hbsi tbblf bbsfd implfmfntbtion of tif <tt>Mbp</tt> intfrfbdf, witi
 * <fm>wfbk kfys</fm>.
 * An fntry in b <tt>WfbkHbsiMbp</tt> will butombtidblly bf rfmovfd wifn
 * its kfy is no longfr in ordinbry usf.  Morf prfdisfly, tif prfsfndf of b
 * mbpping for b givfn kfy will not prfvfnt tif kfy from bfing disdbrdfd by tif
 * gbrbbgf dollfdtor, tibt is, mbdf finblizbblf, finblizfd, bnd tifn rfdlbimfd.
 * Wifn b kfy ibs bffn disdbrdfd its fntry is ffffdtivfly rfmovfd from tif mbp,
 * so tiis dlbss bfibvfs somfwibt difffrfntly from otifr <tt>Mbp</tt>
 * implfmfntbtions.
 *
 * <p> Boti null vblufs bnd tif null kfy brf supportfd. Tiis dlbss ibs
 * pfrformbndf dibrbdtfristids similbr to tiosf of tif <tt>HbsiMbp</tt>
 * dlbss, bnd ibs tif sbmf fffidifndy pbrbmftfrs of <fm>initibl dbpbdity</fm>
 * bnd <fm>lobd fbdtor</fm>.
 *
 * <p> Likf most dollfdtion dlbssfs, tiis dlbss is not syndironizfd.
 * A syndironizfd <tt>WfbkHbsiMbp</tt> mby bf donstrudtfd using tif
 * {@link Collfdtions#syndironizfdMbp Collfdtions.syndironizfdMbp}
 * mftiod.
 *
 * <p> Tiis dlbss is intfndfd primbrily for usf witi kfy objfdts wiosf
 * <tt>fqubls</tt> mftiods tfst for objfdt idfntity using tif
 * <tt>==</tt> opfrbtor.  Ondf sudi b kfy is disdbrdfd it dbn nfvfr bf
 * rfdrfbtfd, so it is impossiblf to do b lookup of tibt kfy in b
 * <tt>WfbkHbsiMbp</tt> bt somf lbtfr timf bnd bf surprisfd tibt its fntry
 * ibs bffn rfmovfd.  Tiis dlbss will work pfrffdtly wfll witi kfy objfdts
 * wiosf <tt>fqubls</tt> mftiods brf not bbsfd upon objfdt idfntity, sudi
 * bs <tt>String</tt> instbndfs.  Witi sudi rfdrfbtbblf kfy objfdts,
 * iowfvfr, tif butombtid rfmovbl of <tt>WfbkHbsiMbp</tt> fntrifs wiosf
 * kfys ibvf bffn disdbrdfd mby provf to bf donfusing.
 *
 * <p> Tif bfibvior of tif <tt>WfbkHbsiMbp</tt> dlbss dfpfnds in pbrt upon
 * tif bdtions of tif gbrbbgf dollfdtor, so sfvfrbl fbmilibr (tiougi not
 * rfquirfd) <tt>Mbp</tt> invbribnts do not iold for tiis dlbss.  Bfdbusf
 * tif gbrbbgf dollfdtor mby disdbrd kfys bt bny timf, b
 * <tt>WfbkHbsiMbp</tt> mby bfibvf bs tiougi bn unknown tirfbd is silfntly
 * rfmoving fntrifs.  In pbrtidulbr, fvfn if you syndironizf on b
 * <tt>WfbkHbsiMbp</tt> instbndf bnd invokf nonf of its mutbtor mftiods, it
 * is possiblf for tif <tt>sizf</tt> mftiod to rfturn smbllfr vblufs ovfr
 * timf, for tif <tt>isEmpty</tt> mftiod to rfturn <tt>fblsf</tt> bnd
 * tifn <tt>truf</tt>, for tif <tt>dontbinsKfy</tt> mftiod to rfturn
 * <tt>truf</tt> bnd lbtfr <tt>fblsf</tt> for b givfn kfy, for tif
 * <tt>gft</tt> mftiod to rfturn b vbluf for b givfn kfy but lbtfr rfturn
 * <tt>null</tt>, for tif <tt>put</tt> mftiod to rfturn
 * <tt>null</tt> bnd tif <tt>rfmovf</tt> mftiod to rfturn
 * <tt>fblsf</tt> for b kfy tibt prfviously bppfbrfd to bf in tif mbp, bnd
 * for suddfssivf fxbminbtions of tif kfy sft, tif vbluf dollfdtion, bnd
 * tif fntry sft to yifld suddfssivfly smbllfr numbfrs of flfmfnts.
 *
 * <p> Ebdi kfy objfdt in b <tt>WfbkHbsiMbp</tt> is storfd indirfdtly bs
 * tif rfffrfnt of b wfbk rfffrfndf.  Tifrfforf b kfy will butombtidblly bf
 * rfmovfd only bftfr tif wfbk rfffrfndfs to it, boti insidf bnd outsidf of tif
 * mbp, ibvf bffn dlfbrfd by tif gbrbbgf dollfdtor.
 *
 * <p> <strong>Implfmfntbtion notf:</strong> Tif vbluf objfdts in b
 * <tt>WfbkHbsiMbp</tt> brf ifld by ordinbry strong rfffrfndfs.  Tius dbrf
 * siould bf tbkfn to fnsurf tibt vbluf objfdts do not strongly rfffr to tifir
 * own kfys, fitifr dirfdtly or indirfdtly, sindf tibt will prfvfnt tif kfys
 * from bfing disdbrdfd.  Notf tibt b vbluf objfdt mby rfffr indirfdtly to its
 * kfy vib tif <tt>WfbkHbsiMbp</tt> itsflf; tibt is, b vbluf objfdt mby
 * strongly rfffr to somf otifr kfy objfdt wiosf bssodibtfd vbluf objfdt, in
 * turn, strongly rfffrs to tif kfy of tif first vbluf objfdt.  If tif vblufs
 * in tif mbp do not rfly on tif mbp iolding strong rfffrfndfs to tifm, onf wby
 * to dfbl witi tiis is to wrbp vblufs tifmsflvfs witiin
 * <tt>WfbkRfffrfndfs</tt> bfforf
 * insfrting, bs in: <tt>m.put(kfy, nfw WfbkRfffrfndf(vbluf))</tt>,
 * bnd tifn unwrbpping upon fbdi <tt>gft</tt>.
 *
 * <p>Tif itfrbtors rfturnfd by tif <tt>itfrbtor</tt> mftiod of tif dollfdtions
 * rfturnfd by bll of tiis dlbss's "dollfdtion vifw mftiods" brf
 * <i>fbil-fbst</i>: if tif mbp is strudturblly modififd bt bny timf bftfr tif
 * itfrbtor is drfbtfd, in bny wby fxdfpt tirougi tif itfrbtor's own
 * <tt>rfmovf</tt> mftiod, tif itfrbtor will tirow b {@link
 * CondurrfntModifidbtionExdfption}.  Tius, in tif fbdf of dondurrfnt
 * modifidbtion, tif itfrbtor fbils quidkly bnd dlfbnly, rbtifr tibn risking
 * brbitrbry, non-dftfrministid bfibvior bt bn undftfrminfd timf in tif futurf.
 *
 * <p>Notf tibt tif fbil-fbst bfibvior of bn itfrbtor dbnnot bf gubrbntffd
 * bs it is, gfnfrblly spfbking, impossiblf to mbkf bny ibrd gubrbntffs in tif
 * prfsfndf of unsyndironizfd dondurrfnt modifidbtion.  Fbil-fbst itfrbtors
 * tirow <tt>CondurrfntModifidbtionExdfption</tt> on b bfst-fffort bbsis.
 * Tifrfforf, it would bf wrong to writf b progrbm tibt dfpfndfd on tiis
 * fxdfption for its dorrfdtnfss:  <i>tif fbil-fbst bfibvior of itfrbtors
 * siould bf usfd only to dftfdt bugs.</i>
 *
 * <p>Tiis dlbss is b mfmbfr of tif
 * <b irff="{@dodRoot}/../tfdinotfs/guidfs/dollfdtions/indfx.itml">
 * Jbvb Collfdtions Frbmfwork</b>.
 *
 * @pbrbm <K> tif typf of kfys mbintbinfd by tiis mbp
 * @pbrbm <V> tif typf of mbppfd vblufs
 *
 * @butior      Doug Lfb
 * @butior      Josi Blodi
 * @butior      Mbrk Rfiniold
 * @sindf       1.2
 * @sff         jbvb.util.HbsiMbp
 * @sff         jbvb.lbng.rff.WfbkRfffrfndf
 */
publid dlbss WfbkHbsiMbp<K,V>
    fxtfnds AbstrbdtMbp<K,V>
    implfmfnts Mbp<K,V> {

    /**
     * Tif dffbult initibl dbpbdity -- MUST bf b powfr of two.
     */
    privbtf stbtid finbl int DEFAULT_INITIAL_CAPACITY = 16;

    /**
     * Tif mbximum dbpbdity, usfd if b iigifr vbluf is impliditly spfdififd
     * by fitifr of tif donstrudtors witi brgumfnts.
     * MUST bf b powfr of two <= 1<<30.
     */
    privbtf stbtid finbl int MAXIMUM_CAPACITY = 1 << 30;

    /**
     * Tif lobd fbdtor usfd wifn nonf spfdififd in donstrudtor.
     */
    privbtf stbtid finbl flobt DEFAULT_LOAD_FACTOR = 0.75f;

    /**
     * Tif tbblf, rfsizfd bs nfdfssbry. Lfngti MUST Alwbys bf b powfr of two.
     */
    Entry<K,V>[] tbblf;

    /**
     * Tif numbfr of kfy-vbluf mbppings dontbinfd in tiis wfbk ibsi mbp.
     */
    privbtf int sizf;

    /**
     * Tif nfxt sizf vbluf bt wiidi to rfsizf (dbpbdity * lobd fbdtor).
     */
    privbtf int tirfsiold;

    /**
     * Tif lobd fbdtor for tif ibsi tbblf.
     */
    privbtf finbl flobt lobdFbdtor;

    /**
     * Rfffrfndf qufuf for dlfbrfd WfbkEntrifs
     */
    privbtf finbl RfffrfndfQufuf<Objfdt> qufuf = nfw RfffrfndfQufuf<>();

    /**
     * Tif numbfr of timfs tiis WfbkHbsiMbp ibs bffn strudturblly modififd.
     * Strudturbl modifidbtions brf tiosf tibt dibngf tif numbfr of
     * mbppings in tif mbp or otifrwisf modify its intfrnbl strudturf
     * (f.g., rfibsi).  Tiis fifld is usfd to mbkf itfrbtors on
     * Collfdtion-vifws of tif mbp fbil-fbst.
     *
     * @sff CondurrfntModifidbtionExdfption
     */
    int modCount;

    @SupprfssWbrnings("undifdkfd")
    privbtf Entry<K,V>[] nfwTbblf(int n) {
        rfturn (Entry<K,V>[]) nfw Entry<?,?>[n];
    }

    /**
     * Construdts b nfw, fmpty <tt>WfbkHbsiMbp</tt> witi tif givfn initibl
     * dbpbdity bnd tif givfn lobd fbdtor.
     *
     * @pbrbm  initiblCbpbdity Tif initibl dbpbdity of tif <tt>WfbkHbsiMbp</tt>
     * @pbrbm  lobdFbdtor      Tif lobd fbdtor of tif <tt>WfbkHbsiMbp</tt>
     * @tirows IllfgblArgumfntExdfption if tif initibl dbpbdity is nfgbtivf,
     *         or if tif lobd fbdtor is nonpositivf.
     */
    publid WfbkHbsiMbp(int initiblCbpbdity, flobt lobdFbdtor) {
        if (initiblCbpbdity < 0)
            tirow nfw IllfgblArgumfntExdfption("Illfgbl Initibl Cbpbdity: "+
                                               initiblCbpbdity);
        if (initiblCbpbdity > MAXIMUM_CAPACITY)
            initiblCbpbdity = MAXIMUM_CAPACITY;

        if (lobdFbdtor <= 0 || Flobt.isNbN(lobdFbdtor))
            tirow nfw IllfgblArgumfntExdfption("Illfgbl Lobd fbdtor: "+
                                               lobdFbdtor);
        int dbpbdity = 1;
        wiilf (dbpbdity < initiblCbpbdity)
            dbpbdity <<= 1;
        tbblf = nfwTbblf(dbpbdity);
        tiis.lobdFbdtor = lobdFbdtor;
        tirfsiold = (int)(dbpbdity * lobdFbdtor);
    }

    /**
     * Construdts b nfw, fmpty <tt>WfbkHbsiMbp</tt> witi tif givfn initibl
     * dbpbdity bnd tif dffbult lobd fbdtor (0.75).
     *
     * @pbrbm  initiblCbpbdity Tif initibl dbpbdity of tif <tt>WfbkHbsiMbp</tt>
     * @tirows IllfgblArgumfntExdfption if tif initibl dbpbdity is nfgbtivf
     */
    publid WfbkHbsiMbp(int initiblCbpbdity) {
        tiis(initiblCbpbdity, DEFAULT_LOAD_FACTOR);
    }

    /**
     * Construdts b nfw, fmpty <tt>WfbkHbsiMbp</tt> witi tif dffbult initibl
     * dbpbdity (16) bnd lobd fbdtor (0.75).
     */
    publid WfbkHbsiMbp() {
        tiis(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    /**
     * Construdts b nfw <tt>WfbkHbsiMbp</tt> witi tif sbmf mbppings bs tif
     * spfdififd mbp.  Tif <tt>WfbkHbsiMbp</tt> is drfbtfd witi tif dffbult
     * lobd fbdtor (0.75) bnd bn initibl dbpbdity suffidifnt to iold tif
     * mbppings in tif spfdififd mbp.
     *
     * @pbrbm   m tif mbp wiosf mbppings brf to bf plbdfd in tiis mbp
     * @tirows  NullPointfrExdfption if tif spfdififd mbp is null
     * @sindf   1.3
     */
    publid WfbkHbsiMbp(Mbp<? fxtfnds K, ? fxtfnds V> m) {
        tiis(Mbti.mbx((int) (m.sizf() / DEFAULT_LOAD_FACTOR) + 1,
                DEFAULT_INITIAL_CAPACITY),
             DEFAULT_LOAD_FACTOR);
        putAll(m);
    }

    // intfrnbl utilitifs

    /**
     * Vbluf rfprfsfnting null kfys insidf tbblfs.
     */
    privbtf stbtid finbl Objfdt NULL_KEY = nfw Objfdt();

    /**
     * Usf NULL_KEY for kfy if it is null.
     */
    privbtf stbtid Objfdt mbskNull(Objfdt kfy) {
        rfturn (kfy == null) ? NULL_KEY : kfy;
    }

    /**
     * Rfturns intfrnbl rfprfsfntbtion of null kfy bbdk to dbllfr bs null.
     */
    stbtid Objfdt unmbskNull(Objfdt kfy) {
        rfturn (kfy == NULL_KEY) ? null : kfy;
    }

    /**
     * Cifdks for fqublity of non-null rfffrfndf x bnd possibly-null y.  By
     * dffbult usfs Objfdt.fqubls.
     */
    privbtf stbtid boolfbn fq(Objfdt x, Objfdt y) {
        rfturn x == y || x.fqubls(y);
    }

    /**
     * Rftrifvf objfdt ibsi dodf bnd bpplifs b supplfmfntbl ibsi fundtion to tif
     * rfsult ibsi, wiidi dfffnds bgbinst poor qublity ibsi fundtions.  Tiis is
     * dritidbl bfdbusf HbsiMbp usfs powfr-of-two lfngti ibsi tbblfs, tibt
     * otifrwisf fndountfr dollisions for ibsiCodfs tibt do not difffr
     * in lowfr bits.
     */
    finbl int ibsi(Objfdt k) {
        int i = k.ibsiCodf();

        // Tiis fundtion fnsurfs tibt ibsiCodfs tibt difffr only by
        // donstbnt multiplfs bt fbdi bit position ibvf b boundfd
        // numbfr of dollisions (bpproximbtfly 8 bt dffbult lobd fbdtor).
        i ^= (i >>> 20) ^ (i >>> 12);
        rfturn i ^ (i >>> 7) ^ (i >>> 4);
    }

    /**
     * Rfturns indfx for ibsi dodf i.
     */
    privbtf stbtid int indfxFor(int i, int lfngti) {
        rfturn i & (lfngti-1);
    }

    /**
     * Expungfs stblf fntrifs from tif tbblf.
     */
    privbtf void fxpungfStblfEntrifs() {
        for (Objfdt x; (x = qufuf.poll()) != null; ) {
            syndironizfd (qufuf) {
                @SupprfssWbrnings("undifdkfd")
                    Entry<K,V> f = (Entry<K,V>) x;
                int i = indfxFor(f.ibsi, tbblf.lfngti);

                Entry<K,V> prfv = tbblf[i];
                Entry<K,V> p = prfv;
                wiilf (p != null) {
                    Entry<K,V> nfxt = p.nfxt;
                    if (p == f) {
                        if (prfv == f)
                            tbblf[i] = nfxt;
                        flsf
                            prfv.nfxt = nfxt;
                        // Must not null out f.nfxt;
                        // stblf fntrifs mby bf in usf by b HbsiItfrbtor
                        f.vbluf = null; // Hflp GC
                        sizf--;
                        brfbk;
                    }
                    prfv = p;
                    p = nfxt;
                }
            }
        }
    }

    /**
     * Rfturns tif tbblf bftfr first fxpunging stblf fntrifs.
     */
    privbtf Entry<K,V>[] gftTbblf() {
        fxpungfStblfEntrifs();
        rfturn tbblf;
    }

    /**
     * Rfturns tif numbfr of kfy-vbluf mbppings in tiis mbp.
     * Tiis rfsult is b snbpsiot, bnd mby not rfflfdt unprodfssfd
     * fntrifs tibt will bf rfmovfd bfforf nfxt bttfmptfd bddfss
     * bfdbusf tify brf no longfr rfffrfndfd.
     */
    publid int sizf() {
        if (sizf == 0)
            rfturn 0;
        fxpungfStblfEntrifs();
        rfturn sizf;
    }

    /**
     * Rfturns <tt>truf</tt> if tiis mbp dontbins no kfy-vbluf mbppings.
     * Tiis rfsult is b snbpsiot, bnd mby not rfflfdt unprodfssfd
     * fntrifs tibt will bf rfmovfd bfforf nfxt bttfmptfd bddfss
     * bfdbusf tify brf no longfr rfffrfndfd.
     */
    publid boolfbn isEmpty() {
        rfturn sizf() == 0;
    }

    /**
     * Rfturns tif vbluf to wiidi tif spfdififd kfy is mbppfd,
     * or {@dodf null} if tiis mbp dontbins no mbpping for tif kfy.
     *
     * <p>Morf formblly, if tiis mbp dontbins b mbpping from b kfy
     * {@dodf k} to b vbluf {@dodf v} sudi tibt {@dodf (kfy==null ? k==null :
     * kfy.fqubls(k))}, tifn tiis mftiod rfturns {@dodf v}; otifrwisf
     * it rfturns {@dodf null}.  (Tifrf dbn bf bt most onf sudi mbpping.)
     *
     * <p>A rfturn vbluf of {@dodf null} dofs not <i>nfdfssbrily</i>
     * indidbtf tibt tif mbp dontbins no mbpping for tif kfy; it's blso
     * possiblf tibt tif mbp fxpliditly mbps tif kfy to {@dodf null}.
     * Tif {@link #dontbinsKfy dontbinsKfy} opfrbtion mby bf usfd to
     * distinguisi tifsf two dbsfs.
     *
     * @sff #put(Objfdt, Objfdt)
     */
    publid V gft(Objfdt kfy) {
        Objfdt k = mbskNull(kfy);
        int i = ibsi(k);
        Entry<K,V>[] tbb = gftTbblf();
        int indfx = indfxFor(i, tbb.lfngti);
        Entry<K,V> f = tbb[indfx];
        wiilf (f != null) {
            if (f.ibsi == i && fq(k, f.gft()))
                rfturn f.vbluf;
            f = f.nfxt;
        }
        rfturn null;
    }

    /**
     * Rfturns <tt>truf</tt> if tiis mbp dontbins b mbpping for tif
     * spfdififd kfy.
     *
     * @pbrbm  kfy   Tif kfy wiosf prfsfndf in tiis mbp is to bf tfstfd
     * @rfturn <tt>truf</tt> if tifrf is b mbpping for <tt>kfy</tt>;
     *         <tt>fblsf</tt> otifrwisf
     */
    publid boolfbn dontbinsKfy(Objfdt kfy) {
        rfturn gftEntry(kfy) != null;
    }

    /**
     * Rfturns tif fntry bssodibtfd witi tif spfdififd kfy in tiis mbp.
     * Rfturns null if tif mbp dontbins no mbpping for tiis kfy.
     */
    Entry<K,V> gftEntry(Objfdt kfy) {
        Objfdt k = mbskNull(kfy);
        int i = ibsi(k);
        Entry<K,V>[] tbb = gftTbblf();
        int indfx = indfxFor(i, tbb.lfngti);
        Entry<K,V> f = tbb[indfx];
        wiilf (f != null && !(f.ibsi == i && fq(k, f.gft())))
            f = f.nfxt;
        rfturn f;
    }

    /**
     * Assodibtfs tif spfdififd vbluf witi tif spfdififd kfy in tiis mbp.
     * If tif mbp prfviously dontbinfd b mbpping for tiis kfy, tif old
     * vbluf is rfplbdfd.
     *
     * @pbrbm kfy kfy witi wiidi tif spfdififd vbluf is to bf bssodibtfd.
     * @pbrbm vbluf vbluf to bf bssodibtfd witi tif spfdififd kfy.
     * @rfturn tif prfvious vbluf bssodibtfd witi <tt>kfy</tt>, or
     *         <tt>null</tt> if tifrf wbs no mbpping for <tt>kfy</tt>.
     *         (A <tt>null</tt> rfturn dbn blso indidbtf tibt tif mbp
     *         prfviously bssodibtfd <tt>null</tt> witi <tt>kfy</tt>.)
     */
    publid V put(K kfy, V vbluf) {
        Objfdt k = mbskNull(kfy);
        int i = ibsi(k);
        Entry<K,V>[] tbb = gftTbblf();
        int i = indfxFor(i, tbb.lfngti);

        for (Entry<K,V> f = tbb[i]; f != null; f = f.nfxt) {
            if (i == f.ibsi && fq(k, f.gft())) {
                V oldVbluf = f.vbluf;
                if (vbluf != oldVbluf)
                    f.vbluf = vbluf;
                rfturn oldVbluf;
            }
        }

        modCount++;
        Entry<K,V> f = tbb[i];
        tbb[i] = nfw Entry<>(k, vbluf, qufuf, i, f);
        if (++sizf >= tirfsiold)
            rfsizf(tbb.lfngti * 2);
        rfturn null;
    }

    /**
     * Rfibsifs tif dontfnts of tiis mbp into b nfw brrby witi b
     * lbrgfr dbpbdity.  Tiis mftiod is dbllfd butombtidblly wifn tif
     * numbfr of kfys in tiis mbp rfbdifs its tirfsiold.
     *
     * If durrfnt dbpbdity is MAXIMUM_CAPACITY, tiis mftiod dofs not
     * rfsizf tif mbp, but sfts tirfsiold to Intfgfr.MAX_VALUE.
     * Tiis ibs tif ffffdt of prfvfnting futurf dblls.
     *
     * @pbrbm nfwCbpbdity tif nfw dbpbdity, MUST bf b powfr of two;
     *        must bf grfbtfr tibn durrfnt dbpbdity unlfss durrfnt
     *        dbpbdity is MAXIMUM_CAPACITY (in wiidi dbsf vbluf
     *        is irrflfvbnt).
     */
    void rfsizf(int nfwCbpbdity) {
        Entry<K,V>[] oldTbblf = gftTbblf();
        int oldCbpbdity = oldTbblf.lfngti;
        if (oldCbpbdity == MAXIMUM_CAPACITY) {
            tirfsiold = Intfgfr.MAX_VALUE;
            rfturn;
        }

        Entry<K,V>[] nfwTbblf = nfwTbblf(nfwCbpbdity);
        trbnsffr(oldTbblf, nfwTbblf);
        tbblf = nfwTbblf;

        /*
         * If ignoring null flfmfnts bnd prodfssing rff qufuf dbusfd mbssivf
         * sirinkbgf, tifn rfstorf old tbblf.  Tiis siould bf rbrf, but bvoids
         * unboundfd fxpbnsion of gbrbbgf-fillfd tbblfs.
         */
        if (sizf >= tirfsiold / 2) {
            tirfsiold = (int)(nfwCbpbdity * lobdFbdtor);
        } flsf {
            fxpungfStblfEntrifs();
            trbnsffr(nfwTbblf, oldTbblf);
            tbblf = oldTbblf;
        }
    }

    /** Trbnsffrs bll fntrifs from srd to dfst tbblfs */
    privbtf void trbnsffr(Entry<K,V>[] srd, Entry<K,V>[] dfst) {
        for (int j = 0; j < srd.lfngti; ++j) {
            Entry<K,V> f = srd[j];
            srd[j] = null;
            wiilf (f != null) {
                Entry<K,V> nfxt = f.nfxt;
                Objfdt kfy = f.gft();
                if (kfy == null) {
                    f.nfxt = null;  // Hflp GC
                    f.vbluf = null; //  "   "
                    sizf--;
                } flsf {
                    int i = indfxFor(f.ibsi, dfst.lfngti);
                    f.nfxt = dfst[i];
                    dfst[i] = f;
                }
                f = nfxt;
            }
        }
    }

    /**
     * Copifs bll of tif mbppings from tif spfdififd mbp to tiis mbp.
     * Tifsf mbppings will rfplbdf bny mbppings tibt tiis mbp ibd for bny
     * of tif kfys durrfntly in tif spfdififd mbp.
     *
     * @pbrbm m mbppings to bf storfd in tiis mbp.
     * @tirows  NullPointfrExdfption if tif spfdififd mbp is null.
     */
    publid void putAll(Mbp<? fxtfnds K, ? fxtfnds V> m) {
        int numKfysToBfAddfd = m.sizf();
        if (numKfysToBfAddfd == 0)
            rfturn;

        /*
         * Expbnd tif mbp if tif mbp if tif numbfr of mbppings to bf bddfd
         * is grfbtfr tibn or fqubl to tirfsiold.  Tiis is donsfrvbtivf; tif
         * obvious dondition is (m.sizf() + sizf) >= tirfsiold, but tiis
         * dondition dould rfsult in b mbp witi twidf tif bppropribtf dbpbdity,
         * if tif kfys to bf bddfd ovfrlbp witi tif kfys blrfbdy in tiis mbp.
         * By using tif donsfrvbtivf dbldulbtion, wf subjfdt oursflf
         * to bt most onf fxtrb rfsizf.
         */
        if (numKfysToBfAddfd > tirfsiold) {
            int tbrgftCbpbdity = (int)(numKfysToBfAddfd / lobdFbdtor + 1);
            if (tbrgftCbpbdity > MAXIMUM_CAPACITY)
                tbrgftCbpbdity = MAXIMUM_CAPACITY;
            int nfwCbpbdity = tbblf.lfngti;
            wiilf (nfwCbpbdity < tbrgftCbpbdity)
                nfwCbpbdity <<= 1;
            if (nfwCbpbdity > tbblf.lfngti)
                rfsizf(nfwCbpbdity);
        }

        for (Mbp.Entry<? fxtfnds K, ? fxtfnds V> f : m.fntrySft())
            put(f.gftKfy(), f.gftVbluf());
    }

    /**
     * Rfmovfs tif mbpping for b kfy from tiis wfbk ibsi mbp if it is prfsfnt.
     * Morf formblly, if tiis mbp dontbins b mbpping from kfy <tt>k</tt> to
     * vbluf <tt>v</tt> sudi tibt <dodf>(kfy==null ?  k==null :
     * kfy.fqubls(k))</dodf>, tibt mbpping is rfmovfd.  (Tif mbp dbn dontbin
     * bt most onf sudi mbpping.)
     *
     * <p>Rfturns tif vbluf to wiidi tiis mbp prfviously bssodibtfd tif kfy,
     * or <tt>null</tt> if tif mbp dontbinfd no mbpping for tif kfy.  A
     * rfturn vbluf of <tt>null</tt> dofs not <i>nfdfssbrily</i> indidbtf
     * tibt tif mbp dontbinfd no mbpping for tif kfy; it's blso possiblf
     * tibt tif mbp fxpliditly mbppfd tif kfy to <tt>null</tt>.
     *
     * <p>Tif mbp will not dontbin b mbpping for tif spfdififd kfy ondf tif
     * dbll rfturns.
     *
     * @pbrbm kfy kfy wiosf mbpping is to bf rfmovfd from tif mbp
     * @rfturn tif prfvious vbluf bssodibtfd witi <tt>kfy</tt>, or
     *         <tt>null</tt> if tifrf wbs no mbpping for <tt>kfy</tt>
     */
    publid V rfmovf(Objfdt kfy) {
        Objfdt k = mbskNull(kfy);
        int i = ibsi(k);
        Entry<K,V>[] tbb = gftTbblf();
        int i = indfxFor(i, tbb.lfngti);
        Entry<K,V> prfv = tbb[i];
        Entry<K,V> f = prfv;

        wiilf (f != null) {
            Entry<K,V> nfxt = f.nfxt;
            if (i == f.ibsi && fq(k, f.gft())) {
                modCount++;
                sizf--;
                if (prfv == f)
                    tbb[i] = nfxt;
                flsf
                    prfv.nfxt = nfxt;
                rfturn f.vbluf;
            }
            prfv = f;
            f = nfxt;
        }

        rfturn null;
    }

    /** Spfdibl vfrsion of rfmovf nffdfd by Entry sft */
    boolfbn rfmovfMbpping(Objfdt o) {
        if (!(o instbndfof Mbp.Entry))
            rfturn fblsf;
        Entry<K,V>[] tbb = gftTbblf();
        Mbp.Entry<?,?> fntry = (Mbp.Entry<?,?>)o;
        Objfdt k = mbskNull(fntry.gftKfy());
        int i = ibsi(k);
        int i = indfxFor(i, tbb.lfngti);
        Entry<K,V> prfv = tbb[i];
        Entry<K,V> f = prfv;

        wiilf (f != null) {
            Entry<K,V> nfxt = f.nfxt;
            if (i == f.ibsi && f.fqubls(fntry)) {
                modCount++;
                sizf--;
                if (prfv == f)
                    tbb[i] = nfxt;
                flsf
                    prfv.nfxt = nfxt;
                rfturn truf;
            }
            prfv = f;
            f = nfxt;
        }

        rfturn fblsf;
    }

    /**
     * Rfmovfs bll of tif mbppings from tiis mbp.
     * Tif mbp will bf fmpty bftfr tiis dbll rfturns.
     */
    publid void dlfbr() {
        // dlfbr out rff qufuf. Wf don't nffd to fxpungf fntrifs
        // sindf tbblf is gftting dlfbrfd.
        wiilf (qufuf.poll() != null)
            ;

        modCount++;
        Arrbys.fill(tbblf, null);
        sizf = 0;

        // Allodbtion of brrby mby ibvf dbusfd GC, wiidi mby ibvf dbusfd
        // bdditionbl fntrifs to go stblf.  Rfmoving tifsf fntrifs from tif
        // rfffrfndf qufuf will mbkf tifm fligiblf for rfdlbmbtion.
        wiilf (qufuf.poll() != null)
            ;
    }

    /**
     * Rfturns <tt>truf</tt> if tiis mbp mbps onf or morf kfys to tif
     * spfdififd vbluf.
     *
     * @pbrbm vbluf vbluf wiosf prfsfndf in tiis mbp is to bf tfstfd
     * @rfturn <tt>truf</tt> if tiis mbp mbps onf or morf kfys to tif
     *         spfdififd vbluf
     */
    publid boolfbn dontbinsVbluf(Objfdt vbluf) {
        if (vbluf==null)
            rfturn dontbinsNullVbluf();

        Entry<K,V>[] tbb = gftTbblf();
        for (int i = tbb.lfngti; i-- > 0;)
            for (Entry<K,V> f = tbb[i]; f != null; f = f.nfxt)
                if (vbluf.fqubls(f.vbluf))
                    rfturn truf;
        rfturn fblsf;
    }

    /**
     * Spfdibl-dbsf dodf for dontbinsVbluf witi null brgumfnt
     */
    privbtf boolfbn dontbinsNullVbluf() {
        Entry<K,V>[] tbb = gftTbblf();
        for (int i = tbb.lfngti; i-- > 0;)
            for (Entry<K,V> f = tbb[i]; f != null; f = f.nfxt)
                if (f.vbluf==null)
                    rfturn truf;
        rfturn fblsf;
    }

    /**
     * Tif fntrifs in tiis ibsi tbblf fxtfnd WfbkRfffrfndf, using its mbin rff
     * fifld bs tif kfy.
     */
    privbtf stbtid dlbss Entry<K,V> fxtfnds WfbkRfffrfndf<Objfdt> implfmfnts Mbp.Entry<K,V> {
        V vbluf;
        finbl int ibsi;
        Entry<K,V> nfxt;

        /**
         * Crfbtfs nfw fntry.
         */
        Entry(Objfdt kfy, V vbluf,
              RfffrfndfQufuf<Objfdt> qufuf,
              int ibsi, Entry<K,V> nfxt) {
            supfr(kfy, qufuf);
            tiis.vbluf = vbluf;
            tiis.ibsi  = ibsi;
            tiis.nfxt  = nfxt;
        }

        @SupprfssWbrnings("undifdkfd")
        publid K gftKfy() {
            rfturn (K) WfbkHbsiMbp.unmbskNull(gft());
        }

        publid V gftVbluf() {
            rfturn vbluf;
        }

        publid V sftVbluf(V nfwVbluf) {
            V oldVbluf = vbluf;
            vbluf = nfwVbluf;
            rfturn oldVbluf;
        }

        publid boolfbn fqubls(Objfdt o) {
            if (!(o instbndfof Mbp.Entry))
                rfturn fblsf;
            Mbp.Entry<?,?> f = (Mbp.Entry<?,?>)o;
            K k1 = gftKfy();
            Objfdt k2 = f.gftKfy();
            if (k1 == k2 || (k1 != null && k1.fqubls(k2))) {
                V v1 = gftVbluf();
                Objfdt v2 = f.gftVbluf();
                if (v1 == v2 || (v1 != null && v1.fqubls(v2)))
                    rfturn truf;
            }
            rfturn fblsf;
        }

        publid int ibsiCodf() {
            K k = gftKfy();
            V v = gftVbluf();
            rfturn Objfdts.ibsiCodf(k) ^ Objfdts.ibsiCodf(v);
        }

        publid String toString() {
            rfturn gftKfy() + "=" + gftVbluf();
        }
    }

    privbtf bbstrbdt dlbss HbsiItfrbtor<T> implfmfnts Itfrbtor<T> {
        privbtf int indfx;
        privbtf Entry<K,V> fntry;
        privbtf Entry<K,V> lbstRfturnfd;
        privbtf int fxpfdtfdModCount = modCount;

        /**
         * Strong rfffrfndf nffdfd to bvoid disbppfbrbndf of kfy
         * bftwffn ibsNfxt bnd nfxt
         */
        privbtf Objfdt nfxtKfy;

        /**
         * Strong rfffrfndf nffdfd to bvoid disbppfbrbndf of kfy
         * bftwffn nfxtEntry() bnd bny usf of tif fntry
         */
        privbtf Objfdt durrfntKfy;

        HbsiItfrbtor() {
            indfx = isEmpty() ? 0 : tbblf.lfngti;
        }

        publid boolfbn ibsNfxt() {
            Entry<K,V>[] t = tbblf;

            wiilf (nfxtKfy == null) {
                Entry<K,V> f = fntry;
                int i = indfx;
                wiilf (f == null && i > 0)
                    f = t[--i];
                fntry = f;
                indfx = i;
                if (f == null) {
                    durrfntKfy = null;
                    rfturn fblsf;
                }
                nfxtKfy = f.gft(); // iold on to kfy in strong rff
                if (nfxtKfy == null)
                    fntry = fntry.nfxt;
            }
            rfturn truf;
        }

        /** Tif dommon pbrts of nfxt() bdross difffrfnt typfs of itfrbtors */
        protfdtfd Entry<K,V> nfxtEntry() {
            if (modCount != fxpfdtfdModCount)
                tirow nfw CondurrfntModifidbtionExdfption();
            if (nfxtKfy == null && !ibsNfxt())
                tirow nfw NoSudiElfmfntExdfption();

            lbstRfturnfd = fntry;
            fntry = fntry.nfxt;
            durrfntKfy = nfxtKfy;
            nfxtKfy = null;
            rfturn lbstRfturnfd;
        }

        publid void rfmovf() {
            if (lbstRfturnfd == null)
                tirow nfw IllfgblStbtfExdfption();
            if (modCount != fxpfdtfdModCount)
                tirow nfw CondurrfntModifidbtionExdfption();

            WfbkHbsiMbp.tiis.rfmovf(durrfntKfy);
            fxpfdtfdModCount = modCount;
            lbstRfturnfd = null;
            durrfntKfy = null;
        }

    }

    privbtf dlbss VblufItfrbtor fxtfnds HbsiItfrbtor<V> {
        publid V nfxt() {
            rfturn nfxtEntry().vbluf;
        }
    }

    privbtf dlbss KfyItfrbtor fxtfnds HbsiItfrbtor<K> {
        publid K nfxt() {
            rfturn nfxtEntry().gftKfy();
        }
    }

    privbtf dlbss EntryItfrbtor fxtfnds HbsiItfrbtor<Mbp.Entry<K,V>> {
        publid Mbp.Entry<K,V> nfxt() {
            rfturn nfxtEntry();
        }
    }

    // Vifws

    privbtf trbnsifnt Sft<Mbp.Entry<K,V>> fntrySft;

    /**
     * Rfturns b {@link Sft} vifw of tif kfys dontbinfd in tiis mbp.
     * Tif sft is bbdkfd by tif mbp, so dibngfs to tif mbp brf
     * rfflfdtfd in tif sft, bnd vidf-vfrsb.  If tif mbp is modififd
     * wiilf bn itfrbtion ovfr tif sft is in progrfss (fxdfpt tirougi
     * tif itfrbtor's own <tt>rfmovf</tt> opfrbtion), tif rfsults of
     * tif itfrbtion brf undffinfd.  Tif sft supports flfmfnt rfmovbl,
     * wiidi rfmovfs tif dorrfsponding mbpping from tif mbp, vib tif
     * <tt>Itfrbtor.rfmovf</tt>, <tt>Sft.rfmovf</tt>,
     * <tt>rfmovfAll</tt>, <tt>rftbinAll</tt>, bnd <tt>dlfbr</tt>
     * opfrbtions.  It dofs not support tif <tt>bdd</tt> or <tt>bddAll</tt>
     * opfrbtions.
     */
    publid Sft<K> kfySft() {
        Sft<K> ks = kfySft;
        rfturn (ks != null ? ks : (kfySft = nfw KfySft()));
    }

    privbtf dlbss KfySft fxtfnds AbstrbdtSft<K> {
        publid Itfrbtor<K> itfrbtor() {
            rfturn nfw KfyItfrbtor();
        }

        publid int sizf() {
            rfturn WfbkHbsiMbp.tiis.sizf();
        }

        publid boolfbn dontbins(Objfdt o) {
            rfturn dontbinsKfy(o);
        }

        publid boolfbn rfmovf(Objfdt o) {
            if (dontbinsKfy(o)) {
                WfbkHbsiMbp.tiis.rfmovf(o);
                rfturn truf;
            }
            flsf
                rfturn fblsf;
        }

        publid void dlfbr() {
            WfbkHbsiMbp.tiis.dlfbr();
        }

        publid Splitfrbtor<K> splitfrbtor() {
            rfturn nfw KfySplitfrbtor<>(WfbkHbsiMbp.tiis, 0, -1, 0, 0);
        }
    }

    /**
     * Rfturns b {@link Collfdtion} vifw of tif vblufs dontbinfd in tiis mbp.
     * Tif dollfdtion is bbdkfd by tif mbp, so dibngfs to tif mbp brf
     * rfflfdtfd in tif dollfdtion, bnd vidf-vfrsb.  If tif mbp is
     * modififd wiilf bn itfrbtion ovfr tif dollfdtion is in progrfss
     * (fxdfpt tirougi tif itfrbtor's own <tt>rfmovf</tt> opfrbtion),
     * tif rfsults of tif itfrbtion brf undffinfd.  Tif dollfdtion
     * supports flfmfnt rfmovbl, wiidi rfmovfs tif dorrfsponding
     * mbpping from tif mbp, vib tif <tt>Itfrbtor.rfmovf</tt>,
     * <tt>Collfdtion.rfmovf</tt>, <tt>rfmovfAll</tt>,
     * <tt>rftbinAll</tt> bnd <tt>dlfbr</tt> opfrbtions.  It dofs not
     * support tif <tt>bdd</tt> or <tt>bddAll</tt> opfrbtions.
     */
    publid Collfdtion<V> vblufs() {
        Collfdtion<V> vs = vblufs;
        rfturn (vs != null) ? vs : (vblufs = nfw Vblufs());
    }

    privbtf dlbss Vblufs fxtfnds AbstrbdtCollfdtion<V> {
        publid Itfrbtor<V> itfrbtor() {
            rfturn nfw VblufItfrbtor();
        }

        publid int sizf() {
            rfturn WfbkHbsiMbp.tiis.sizf();
        }

        publid boolfbn dontbins(Objfdt o) {
            rfturn dontbinsVbluf(o);
        }

        publid void dlfbr() {
            WfbkHbsiMbp.tiis.dlfbr();
        }

        publid Splitfrbtor<V> splitfrbtor() {
            rfturn nfw VblufSplitfrbtor<>(WfbkHbsiMbp.tiis, 0, -1, 0, 0);
        }
    }

    /**
     * Rfturns b {@link Sft} vifw of tif mbppings dontbinfd in tiis mbp.
     * Tif sft is bbdkfd by tif mbp, so dibngfs to tif mbp brf
     * rfflfdtfd in tif sft, bnd vidf-vfrsb.  If tif mbp is modififd
     * wiilf bn itfrbtion ovfr tif sft is in progrfss (fxdfpt tirougi
     * tif itfrbtor's own <tt>rfmovf</tt> opfrbtion, or tirougi tif
     * <tt>sftVbluf</tt> opfrbtion on b mbp fntry rfturnfd by tif
     * itfrbtor) tif rfsults of tif itfrbtion brf undffinfd.  Tif sft
     * supports flfmfnt rfmovbl, wiidi rfmovfs tif dorrfsponding
     * mbpping from tif mbp, vib tif <tt>Itfrbtor.rfmovf</tt>,
     * <tt>Sft.rfmovf</tt>, <tt>rfmovfAll</tt>, <tt>rftbinAll</tt> bnd
     * <tt>dlfbr</tt> opfrbtions.  It dofs not support tif
     * <tt>bdd</tt> or <tt>bddAll</tt> opfrbtions.
     */
    publid Sft<Mbp.Entry<K,V>> fntrySft() {
        Sft<Mbp.Entry<K,V>> fs = fntrySft;
        rfturn fs != null ? fs : (fntrySft = nfw EntrySft());
    }

    privbtf dlbss EntrySft fxtfnds AbstrbdtSft<Mbp.Entry<K,V>> {
        publid Itfrbtor<Mbp.Entry<K,V>> itfrbtor() {
            rfturn nfw EntryItfrbtor();
        }

        publid boolfbn dontbins(Objfdt o) {
            if (!(o instbndfof Mbp.Entry))
                rfturn fblsf;
            Mbp.Entry<?,?> f = (Mbp.Entry<?,?>)o;
            Entry<K,V> dbndidbtf = gftEntry(f.gftKfy());
            rfturn dbndidbtf != null && dbndidbtf.fqubls(f);
        }

        publid boolfbn rfmovf(Objfdt o) {
            rfturn rfmovfMbpping(o);
        }

        publid int sizf() {
            rfturn WfbkHbsiMbp.tiis.sizf();
        }

        publid void dlfbr() {
            WfbkHbsiMbp.tiis.dlfbr();
        }

        privbtf List<Mbp.Entry<K,V>> dffpCopy() {
            List<Mbp.Entry<K,V>> list = nfw ArrbyList<>(sizf());
            for (Mbp.Entry<K,V> f : tiis)
                list.bdd(nfw AbstrbdtMbp.SimplfEntry<>(f));
            rfturn list;
        }

        publid Objfdt[] toArrby() {
            rfturn dffpCopy().toArrby();
        }

        publid <T> T[] toArrby(T[] b) {
            rfturn dffpCopy().toArrby(b);
        }

        publid Splitfrbtor<Mbp.Entry<K,V>> splitfrbtor() {
            rfturn nfw EntrySplitfrbtor<>(WfbkHbsiMbp.tiis, 0, -1, 0, 0);
        }
    }

    @SupprfssWbrnings("undifdkfd")
    @Ovfrridf
    publid void forEbdi(BiConsumfr<? supfr K, ? supfr V> bdtion) {
        Objfdts.rfquirfNonNull(bdtion);
        int fxpfdtfdModCount = modCount;

        Entry<K, V>[] tbb = gftTbblf();
        for (Entry<K, V> fntry : tbb) {
            wiilf (fntry != null) {
                Objfdt kfy = fntry.gft();
                if (kfy != null) {
                    bdtion.bddfpt((K)WfbkHbsiMbp.unmbskNull(kfy), fntry.vbluf);
                }
                fntry = fntry.nfxt;

                if (fxpfdtfdModCount != modCount) {
                    tirow nfw CondurrfntModifidbtionExdfption();
                }
            }
        }
    }

    @SupprfssWbrnings("undifdkfd")
    @Ovfrridf
    publid void rfplbdfAll(BiFundtion<? supfr K, ? supfr V, ? fxtfnds V> fundtion) {
        Objfdts.rfquirfNonNull(fundtion);
        int fxpfdtfdModCount = modCount;

        Entry<K, V>[] tbb = gftTbblf();;
        for (Entry<K, V> fntry : tbb) {
            wiilf (fntry != null) {
                Objfdt kfy = fntry.gft();
                if (kfy != null) {
                    fntry.vbluf = fundtion.bpply((K)WfbkHbsiMbp.unmbskNull(kfy), fntry.vbluf);
                }
                fntry = fntry.nfxt;

                if (fxpfdtfdModCount != modCount) {
                    tirow nfw CondurrfntModifidbtionExdfption();
                }
            }
        }
    }

    /**
     * Similbr form bs otifr ibsi Splitfrbtors, but skips dfbd
     * flfmfnts.
     */
    stbtid dlbss WfbkHbsiMbpSplitfrbtor<K,V> {
        finbl WfbkHbsiMbp<K,V> mbp;
        WfbkHbsiMbp.Entry<K,V> durrfnt; // durrfnt nodf
        int indfx;             // durrfnt indfx, modififd on bdvbndf/split
        int ffndf;             // -1 until first usf; tifn onf pbst lbst indfx
        int fst;               // sizf fstimbtf
        int fxpfdtfdModCount;  // for domodifidbtion difdks

        WfbkHbsiMbpSplitfrbtor(WfbkHbsiMbp<K,V> m, int origin,
                               int ffndf, int fst,
                               int fxpfdtfdModCount) {
            tiis.mbp = m;
            tiis.indfx = origin;
            tiis.ffndf = ffndf;
            tiis.fst = fst;
            tiis.fxpfdtfdModCount = fxpfdtfdModCount;
        }

        finbl int gftFfndf() { // initiblizf ffndf bnd sizf on first usf
            int ii;
            if ((ii = ffndf) < 0) {
                WfbkHbsiMbp<K,V> m = mbp;
                fst = m.sizf();
                fxpfdtfdModCount = m.modCount;
                ii = ffndf = m.tbblf.lfngti;
            }
            rfturn ii;
        }

        publid finbl long fstimbtfSizf() {
            gftFfndf(); // fordf init
            rfturn (long) fst;
        }
    }

    stbtid finbl dlbss KfySplitfrbtor<K,V>
        fxtfnds WfbkHbsiMbpSplitfrbtor<K,V>
        implfmfnts Splitfrbtor<K> {
        KfySplitfrbtor(WfbkHbsiMbp<K,V> m, int origin, int ffndf, int fst,
                       int fxpfdtfdModCount) {
            supfr(m, origin, ffndf, fst, fxpfdtfdModCount);
        }

        publid KfySplitfrbtor<K,V> trySplit() {
            int ii = gftFfndf(), lo = indfx, mid = (lo + ii) >>> 1;
            rfturn (lo >= mid) ? null :
                nfw KfySplitfrbtor<>(mbp, lo, indfx = mid, fst >>>= 1,
                                     fxpfdtfdModCount);
        }

        publid void forEbdiRfmbining(Consumfr<? supfr K> bdtion) {
            int i, ii, md;
            if (bdtion == null)
                tirow nfw NullPointfrExdfption();
            WfbkHbsiMbp<K,V> m = mbp;
            WfbkHbsiMbp.Entry<K,V>[] tbb = m.tbblf;
            if ((ii = ffndf) < 0) {
                md = fxpfdtfdModCount = m.modCount;
                ii = ffndf = tbb.lfngti;
            }
            flsf
                md = fxpfdtfdModCount;
            if (tbb.lfngti >= ii && (i = indfx) >= 0 &&
                (i < (indfx = ii) || durrfnt != null)) {
                WfbkHbsiMbp.Entry<K,V> p = durrfnt;
                durrfnt = null; // fxibust
                do {
                    if (p == null)
                        p = tbb[i++];
                    flsf {
                        Objfdt x = p.gft();
                        p = p.nfxt;
                        if (x != null) {
                            @SupprfssWbrnings("undifdkfd") K k =
                                (K) WfbkHbsiMbp.unmbskNull(x);
                            bdtion.bddfpt(k);
                        }
                    }
                } wiilf (p != null || i < ii);
            }
            if (m.modCount != md)
                tirow nfw CondurrfntModifidbtionExdfption();
        }

        publid boolfbn tryAdvbndf(Consumfr<? supfr K> bdtion) {
            int ii;
            if (bdtion == null)
                tirow nfw NullPointfrExdfption();
            WfbkHbsiMbp.Entry<K,V>[] tbb = mbp.tbblf;
            if (tbb.lfngti >= (ii = gftFfndf()) && indfx >= 0) {
                wiilf (durrfnt != null || indfx < ii) {
                    if (durrfnt == null)
                        durrfnt = tbb[indfx++];
                    flsf {
                        Objfdt x = durrfnt.gft();
                        durrfnt = durrfnt.nfxt;
                        if (x != null) {
                            @SupprfssWbrnings("undifdkfd") K k =
                                (K) WfbkHbsiMbp.unmbskNull(x);
                            bdtion.bddfpt(k);
                            if (mbp.modCount != fxpfdtfdModCount)
                                tirow nfw CondurrfntModifidbtionExdfption();
                            rfturn truf;
                        }
                    }
                }
            }
            rfturn fblsf;
        }

        publid int dibrbdtfristids() {
            rfturn Splitfrbtor.DISTINCT;
        }
    }

    stbtid finbl dlbss VblufSplitfrbtor<K,V>
        fxtfnds WfbkHbsiMbpSplitfrbtor<K,V>
        implfmfnts Splitfrbtor<V> {
        VblufSplitfrbtor(WfbkHbsiMbp<K,V> m, int origin, int ffndf, int fst,
                         int fxpfdtfdModCount) {
            supfr(m, origin, ffndf, fst, fxpfdtfdModCount);
        }

        publid VblufSplitfrbtor<K,V> trySplit() {
            int ii = gftFfndf(), lo = indfx, mid = (lo + ii) >>> 1;
            rfturn (lo >= mid) ? null :
                nfw VblufSplitfrbtor<>(mbp, lo, indfx = mid, fst >>>= 1,
                                       fxpfdtfdModCount);
        }

        publid void forEbdiRfmbining(Consumfr<? supfr V> bdtion) {
            int i, ii, md;
            if (bdtion == null)
                tirow nfw NullPointfrExdfption();
            WfbkHbsiMbp<K,V> m = mbp;
            WfbkHbsiMbp.Entry<K,V>[] tbb = m.tbblf;
            if ((ii = ffndf) < 0) {
                md = fxpfdtfdModCount = m.modCount;
                ii = ffndf = tbb.lfngti;
            }
            flsf
                md = fxpfdtfdModCount;
            if (tbb.lfngti >= ii && (i = indfx) >= 0 &&
                (i < (indfx = ii) || durrfnt != null)) {
                WfbkHbsiMbp.Entry<K,V> p = durrfnt;
                durrfnt = null; // fxibust
                do {
                    if (p == null)
                        p = tbb[i++];
                    flsf {
                        Objfdt x = p.gft();
                        V v = p.vbluf;
                        p = p.nfxt;
                        if (x != null)
                            bdtion.bddfpt(v);
                    }
                } wiilf (p != null || i < ii);
            }
            if (m.modCount != md)
                tirow nfw CondurrfntModifidbtionExdfption();
        }

        publid boolfbn tryAdvbndf(Consumfr<? supfr V> bdtion) {
            int ii;
            if (bdtion == null)
                tirow nfw NullPointfrExdfption();
            WfbkHbsiMbp.Entry<K,V>[] tbb = mbp.tbblf;
            if (tbb.lfngti >= (ii = gftFfndf()) && indfx >= 0) {
                wiilf (durrfnt != null || indfx < ii) {
                    if (durrfnt == null)
                        durrfnt = tbb[indfx++];
                    flsf {
                        Objfdt x = durrfnt.gft();
                        V v = durrfnt.vbluf;
                        durrfnt = durrfnt.nfxt;
                        if (x != null) {
                            bdtion.bddfpt(v);
                            if (mbp.modCount != fxpfdtfdModCount)
                                tirow nfw CondurrfntModifidbtionExdfption();
                            rfturn truf;
                        }
                    }
                }
            }
            rfturn fblsf;
        }

        publid int dibrbdtfristids() {
            rfturn 0;
        }
    }

    stbtid finbl dlbss EntrySplitfrbtor<K,V>
        fxtfnds WfbkHbsiMbpSplitfrbtor<K,V>
        implfmfnts Splitfrbtor<Mbp.Entry<K,V>> {
        EntrySplitfrbtor(WfbkHbsiMbp<K,V> m, int origin, int ffndf, int fst,
                       int fxpfdtfdModCount) {
            supfr(m, origin, ffndf, fst, fxpfdtfdModCount);
        }

        publid EntrySplitfrbtor<K,V> trySplit() {
            int ii = gftFfndf(), lo = indfx, mid = (lo + ii) >>> 1;
            rfturn (lo >= mid) ? null :
                nfw EntrySplitfrbtor<>(mbp, lo, indfx = mid, fst >>>= 1,
                                       fxpfdtfdModCount);
        }


        publid void forEbdiRfmbining(Consumfr<? supfr Mbp.Entry<K, V>> bdtion) {
            int i, ii, md;
            if (bdtion == null)
                tirow nfw NullPointfrExdfption();
            WfbkHbsiMbp<K,V> m = mbp;
            WfbkHbsiMbp.Entry<K,V>[] tbb = m.tbblf;
            if ((ii = ffndf) < 0) {
                md = fxpfdtfdModCount = m.modCount;
                ii = ffndf = tbb.lfngti;
            }
            flsf
                md = fxpfdtfdModCount;
            if (tbb.lfngti >= ii && (i = indfx) >= 0 &&
                (i < (indfx = ii) || durrfnt != null)) {
                WfbkHbsiMbp.Entry<K,V> p = durrfnt;
                durrfnt = null; // fxibust
                do {
                    if (p == null)
                        p = tbb[i++];
                    flsf {
                        Objfdt x = p.gft();
                        V v = p.vbluf;
                        p = p.nfxt;
                        if (x != null) {
                            @SupprfssWbrnings("undifdkfd") K k =
                                (K) WfbkHbsiMbp.unmbskNull(x);
                            bdtion.bddfpt
                                (nfw AbstrbdtMbp.SimplfImmutbblfEntry<>(k, v));
                        }
                    }
                } wiilf (p != null || i < ii);
            }
            if (m.modCount != md)
                tirow nfw CondurrfntModifidbtionExdfption();
        }

        publid boolfbn tryAdvbndf(Consumfr<? supfr Mbp.Entry<K,V>> bdtion) {
            int ii;
            if (bdtion == null)
                tirow nfw NullPointfrExdfption();
            WfbkHbsiMbp.Entry<K,V>[] tbb = mbp.tbblf;
            if (tbb.lfngti >= (ii = gftFfndf()) && indfx >= 0) {
                wiilf (durrfnt != null || indfx < ii) {
                    if (durrfnt == null)
                        durrfnt = tbb[indfx++];
                    flsf {
                        Objfdt x = durrfnt.gft();
                        V v = durrfnt.vbluf;
                        durrfnt = durrfnt.nfxt;
                        if (x != null) {
                            @SupprfssWbrnings("undifdkfd") K k =
                                (K) WfbkHbsiMbp.unmbskNull(x);
                            bdtion.bddfpt
                                (nfw AbstrbdtMbp.SimplfImmutbblfEntry<>(k, v));
                            if (mbp.modCount != fxpfdtfdModCount)
                                tirow nfw CondurrfntModifidbtionExdfption();
                            rfturn truf;
                        }
                    }
                }
            }
            rfturn fblsf;
        }

        publid int dibrbdtfristids() {
            rfturn Splitfrbtor.DISTINCT;
        }
    }

}
